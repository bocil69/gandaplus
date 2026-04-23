package com.lody.virtual.client.hook.secondary;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.VClient;
import com.lody.virtual.helper.utils.VLog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.IdentityHashMap;

final class GmsBridgeServiceBinder extends StubBinder {

    private static final String TAG = "GmsBridgeBinder";
    private static final String GMS_SERVICE_BROKER_DESCRIPTOR = "com.google.android.gms.common.internal.IGmsServiceBroker";
    private static final int GMS_GET_SERVICE_TRANSACTION = 46;
    private static final String[] GET_SERVICE_REQUEST_CLASS_NAMES = {
            "com.google.android.gms.common.internal.GetServiceRequest",
            "sf.f"
    };

    private GmsBridgeServiceBinder(Application application, ClassLoader classLoader, IBinder base) {
        super(application, classLoader, base);
    }

    static IBinder maybeWrap(ComponentName componentName, IBinder service) {
        if (service == null || !isGmsComponent(componentName)) {
            return service;
        }
        Application application = VClient.get().getCurrentApplication();
        ClassLoader classLoader = VClient.get().getClassLoader();
        if (application == null || classLoader == null) {
            VLog.w(TAG, "skip wrap component=%s app=%s cl=%s", componentName, application, classLoader);
            return service;
        }
        try {
            VLog.w(TAG, "wrap component=%s descriptor=%s", componentName, service.getInterfaceDescriptor());
        } catch (Throwable e) {
            VLog.w(TAG, "wrap component=%s descriptor_error=%s", componentName, e.getMessage());
        }
        return new GmsBridgeServiceBinder(application, classLoader, service);
    }

    private static boolean isGmsComponent(ComponentName componentName) {
        return componentName != null && GmsSupport.isGmsPackage(componentName.getPackageName());
    }

    @Override
    public InvocationHandler createHandler(Class<?> interfaceClass, IInterface targetInterface) {
        return new Handler(interfaceClass, targetInterface);
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        String descriptor;
        try {
            descriptor = getBaseBinder().getInterfaceDescriptor();
        } catch (Throwable e) {
            descriptor = null;
        }
        if (GMS_SERVICE_BROKER_DESCRIPTOR.equals(descriptor)) {
            VLog.w(TAG, "broker transact code=%d flags=%d size=%d", code, flags, data != null ? data.dataSize() : -1);
        }
        if (!GMS_SERVICE_BROKER_DESCRIPTOR.equals(descriptor) || code != GMS_GET_SERVICE_TRANSACTION) {
            return super.onTransact(code, data, reply, flags);
        }
        String appPkg = getAppPkg();
        String hostPkg = getHostPkg();
        if (appPkg == null || hostPkg == null || hostPkg.equals(appPkg)) {
            return super.onTransact(code, data, reply, flags);
        }

        Parcel input = Parcel.obtain();
        Parcel rewritten = Parcel.obtain();
        try {
            input.appendFrom(data, 0, data.dataSize());
            input.setDataPosition(0);

            try {
                input.enforceInterface(GMS_SERVICE_BROKER_DESCRIPTOR);
            } catch (Throwable e) {
                VLog.w(TAG, "broker transact enforceInterface failed: %s", e.getMessage());
                return super.onTransact(code, data, reply, flags);
            }
            rewritten.writeInterfaceToken(GMS_SERVICE_BROKER_DESCRIPTOR);
            rewritten.writeStrongBinder(input.readStrongBinder());

            int present = input.readInt();
            VLog.w(TAG, "broker transact token=%s present=%d remaining=%d", GMS_SERVICE_BROKER_DESCRIPTOR, present, input.dataAvail());
            rewritten.writeInt(present);
            if (present == 0) {
                rewritten.setDataPosition(0);
                return getBaseBinder().transact(code, rewritten, reply, flags);
            }

            Parcelable request = readGetServiceRequest(input);
            if (request == null) {
                VLog.w(TAG, "broker transact request decode returned null");
                return super.onTransact(code, data, reply, flags);
            }

            sanitizeBrokerRequest(request, appPkg, hostPkg);
            request.writeToParcel(rewritten, 0);
            rewritten.setDataPosition(0);
            VLog.w(TAG, "rewrite broker transact code=%d request=%s", code, request.getClass().getName());
            return getBaseBinder().transact(code, rewritten, reply, flags);
        } catch (Throwable e) {
            VLog.w(TAG, "broker transact rewrite failed: %s", e.getMessage());
            return super.onTransact(code, data, reply, flags);
        } finally {
            input.recycle();
            rewritten.recycle();
        }
    }

    private Parcelable readGetServiceRequest(Parcel input) {
        ClassLoader classLoader = getStubClassLoader();
        if (classLoader == null) {
            return null;
        }
        for (String className : GET_SERVICE_REQUEST_CLASS_NAMES) {
            try {
                Class<?> requestClass = classLoader.loadClass(className);
                Object creatorObject = requestClass.getField("CREATOR").get(null);
                if (!(creatorObject instanceof Parcelable.Creator)) {
                    VLog.w(TAG, "broker request class %s has non-parcelable creator %s", className, creatorObject);
                    continue;
                }
                Parcelable.Creator<?> creator = (Parcelable.Creator<?>) creatorObject;
                Parcel requestParcel = Parcel.obtain();
                try {
                    int start = input.dataPosition();
                    requestParcel.appendFrom(input, start, input.dataSize() - start);
                    requestParcel.setDataPosition(0);
                    Object created = creator.createFromParcel(requestParcel);
                    if (created instanceof Parcelable) {
                        VLog.w(TAG, "decoded broker request with %s", className);
                        input.setDataPosition(input.dataSize());
                        return (Parcelable) created;
                    }
                    VLog.w(TAG, "creator for %s returned non-parcelable %s", className,
                            created != null ? created.getClass().getName() : "null");
                } finally {
                    requestParcel.recycle();
                }
            } catch (Throwable e) {
                VLog.w(TAG, "decode %s failed: %s", className, e.getMessage());
            }
        }
        return null;
    }

    private void sanitizeBrokerRequest(Parcelable request, String appPkg, String hostPkg) {
        IdentityHashMap<Object, Boolean> visited = new IdentityHashMap<>();
        sanitizeBrokerObject(request, request.getClass(), appPkg, hostPkg, visited, "broker");
    }

    private void sanitizeBrokerObject(Object value, Class<?> valueClass, String appPkg, String hostPkg,
                                      IdentityHashMap<Object, Boolean> visited, String path) {
        if (value == null || valueClass == null || visited.containsKey(value)) {
            return;
        }
        visited.put(value, Boolean.TRUE);
        Class<?> current = valueClass;
        while (current != null && current != Object.class) {
            Field[] fields = current.getDeclaredFields();
            for (Field field : fields) {
                if ((field.getModifiers() & java.lang.reflect.Modifier.STATIC) != 0) {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    Object child = field.get(value);
                    if (child instanceof String) {
                        if (appPkg.equals(child)) {
                            VLog.w(TAG, "replace %s.%s %s -> %s", path, field.getName(), appPkg, hostPkg);
                            field.set(value, hostPkg);
                        }
                    } else if (child instanceof Bundle) {
                        sanitizeBundle((Bundle) child, appPkg, hostPkg, visited, path + "." + field.getName());
                    } else {
                        sanitizeValue(child, appPkg, hostPkg, visited, path + "." + field.getName());
                    }
                } catch (Throwable e) {
                    VLog.w(TAG, "Unable to sanitize broker %s.%s: %s", current.getName(), field.getName(), e.getMessage());
                }
            }
            current = current.getSuperclass();
        }
    }

    private final class Handler implements InvocationHandler {
        private final Class<?> interfaceClass;
        private final IInterface targetInterface;

        private Handler(Class<?> interfaceClass, IInterface targetInterface) {
            this.interfaceClass = interfaceClass;
            this.targetInterface = targetInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String appPkg = getAppPkg();
            String hostPkg = getHostPkg();
            VLog.w(TAG, "invoke iface=%s method=%s", interfaceClass.getName(), method.getName());
            if (args != null && appPkg != null && hostPkg != null && !hostPkg.equals(appPkg)) {
                sanitizeArgs(args, appPkg, hostPkg);
            }
            try {
                Object result = method.invoke(targetInterface, args);
                if (result != null && appPkg != null && hostPkg != null && !hostPkg.equals(appPkg)) {
                    result = translateInboundResult(result, hostPkg, appPkg);
                }
                return result;
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }

    private Object translateInboundResult(Object result, String hostPkg, String appPkg) {
        if (result instanceof String && hostPkg.equals(result)) {
            VLog.w(TAG, "replace result %s -> %s", hostPkg, appPkg);
            return appPkg;
        }
        IdentityHashMap<Object, Boolean> visited = new IdentityHashMap<>();
        sanitizeValue(result, hostPkg, appPkg, visited, "result");
        return result;
    }

    private void sanitizeArgs(Object[] args, String appPkg, String hostPkg) {
        IdentityHashMap<Object, Boolean> visited = new IdentityHashMap<>();
        for (int i = 0; i < args.length; i++) {
            Object value = args[i];
            if (value instanceof String) {
                if (appPkg.equals(value)) {
                    VLog.w(TAG, "replace arg[%d] %s -> %s", i, appPkg, hostPkg);
                    args[i] = hostPkg;
                }
                continue;
            }
            sanitizeValue(value, appPkg, hostPkg, visited, "arg[" + i + "]");
        }
    }

    private void sanitizeValue(Object value, String appPkg, String hostPkg, IdentityHashMap<Object, Boolean> visited,
                               String path) {
        if (value == null || visited.containsKey(value)) {
            return;
        }
        visited.put(value, Boolean.TRUE);
        if (value instanceof Bundle) {
            sanitizeBundle((Bundle) value, appPkg, hostPkg, visited, path);
            return;
        }
        if (value instanceof Intent) {
            Intent intent = (Intent) value;
            sanitizeBundle(intent.getExtras(), appPkg, hostPkg, visited, path + ".extras");
            return;
        }
        if (value instanceof Message) {
            Message message = (Message) value;
            VLog.w(TAG, "message %s what=%d arg1=%d arg2=%d obj=%s replyTo=%s data=%s", path,
                    message.what,
                    message.arg1,
                    message.arg2,
                    message.obj != null ? message.obj.getClass().getName() : "null",
                    message.replyTo,
                    describeBundle(message.getData()));
            sanitizeBundle(message.getData(), appPkg, hostPkg, visited, path + ".data");
            sanitizeValue(message.obj, appPkg, hostPkg, visited, path + ".obj");
            sanitizeValue(message.peekData(), appPkg, hostPkg, visited, path + ".peekData");
            return;
        }
        Class<?> valueClass = value.getClass();
        if (valueClass.isArray()) {
            int length = java.lang.reflect.Array.getLength(value);
            for (int i = 0; i < length; i++) {
                Object item = java.lang.reflect.Array.get(value, i);
                if (item instanceof String && appPkg.equals(item)) {
                    VLog.w(TAG, "replace %s[%d] %s -> %s", path, i, appPkg, hostPkg);
                    java.lang.reflect.Array.set(value, i, hostPkg);
                } else {
                    sanitizeValue(item, appPkg, hostPkg, visited, path + "[" + i + "]");
                }
            }
            return;
        }
        if (value instanceof Iterable) {
            int index = 0;
            for (Object item : (Iterable<?>) value) {
                sanitizeValue(item, appPkg, hostPkg, visited, path + "#" + index);
                index++;
            }
            return;
        }
        if (!shouldInspectObject(valueClass)) {
            return;
        }
        sanitizeObjectFields(value, valueClass, appPkg, hostPkg, visited, path);
    }

    private void sanitizeBundle(Bundle bundle, String appPkg, String hostPkg, IdentityHashMap<Object, Boolean> visited,
                                 String path) {
        if (bundle == null) {
            return;
        }
        VLog.w(TAG, "bundle %s => %s", path, describeBundle(bundle));
        for (String key : bundle.keySet()) {
            Object child = bundle.get(key);
            if (child instanceof String) {
                if (appPkg.equals(child)) {
                    VLog.w(TAG, "replace %s[%s] %s -> %s", path, key, appPkg, hostPkg);
                    bundle.putString(key, hostPkg);
                }
            } else if (child instanceof String[]) {
                String[] values = (String[]) child;
                for (int i = 0; i < values.length; i++) {
                    if (appPkg.equals(values[i])) {
                        VLog.w(TAG, "replace %s[%s][%d] %s -> %s", path, key, i, appPkg, hostPkg);
                        values[i] = hostPkg;
                    }
                }
                bundle.putStringArray(key, values);
            } else {
                sanitizeValue(child, appPkg, hostPkg, visited, path + "[" + key + "]");
            }
        }
    }

    private String describeBundle(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        StringBuilder builder = new StringBuilder("{");
        boolean first = true;
        for (String key : bundle.keySet()) {
            if (!first) {
                builder.append(", ");
            }
            Object value = bundle.get(key);
            builder.append(key).append('=');
            if (value == null) {
                builder.append("null");
            } else if (value instanceof String) {
                builder.append(value);
            } else if (value instanceof String[]) {
                builder.append(java.util.Arrays.toString((String[]) value));
            } else {
                builder.append(value.getClass().getName());
            }
            first = false;
        }
        builder.append('}');
        return builder.toString();
    }

    private boolean shouldInspectObject(Class<?> valueClass) {
        if (valueClass == null) {
            return false;
        }
        String name = valueClass.getName();
        return name.startsWith("com.google.android.gms.")
                || name.startsWith("com.google.android.gsf.")
                || name.startsWith("com.google.firebase.")
                || name.contains("GetServiceRequest")
                || name.contains("ClientIdentity")
                || name.contains("ResolveAccountRequest")
                || name.contains("AuthAccountRequest");
    }

    private void sanitizeObjectFields(Object value, Class<?> valueClass, String appPkg, String hostPkg,
                                      IdentityHashMap<Object, Boolean> visited, String path) {
        Class<?> current = valueClass;
        while (current != null && current != Object.class) {
            Field[] fields = current.getDeclaredFields();
            for (Field field : fields) {
                if ((field.getModifiers() & java.lang.reflect.Modifier.STATIC) != 0) {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    Object child = field.get(value);
                    if (child instanceof String) {
                        if (appPkg.equals(child)) {
                            VLog.w(TAG, "replace %s.%s %s -> %s", current.getSimpleName(), field.getName(), appPkg, hostPkg);
                            field.set(value, hostPkg);
                        }
                    } else {
                        sanitizeValue(child, appPkg, hostPkg, visited, path + "." + field.getName());
                    }
                } catch (Throwable e) {
                    VLog.w(TAG, "Unable to sanitize %s.%s: %s", current.getName(), field.getName(), e.getMessage());
                }
            }
            current = current.getSuperclass();
        }
    }
}
