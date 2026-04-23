package com.topjohnwu.superuser.internal;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.ShellUtils;
import com.topjohnwu.superuser.internal.IRootServiceManager;
import com.topjohnwu.superuser.internal.RootServiceManager;
import com.topjohnwu.superuser.ipc.RootService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class RootServiceManager implements Handler.Callback {
    private static final String API_27_DEBUG = "-Xrunjdwp:transport=dt_android_adb,suspend=n,server=y -Xcompiler-option --debuggable";
    private static final String API_28_DEBUG = "-XjdwpProvider:adbconnection -XjdwpOptions:suspend=n,server=y -Xcompiler-option --debuggable";
    private static final String BUNDLE_BINDER_KEY = "binder";
    private static final int DAEMON_EN_ROUTE = 2;
    static final String DEBUG_ENV = "LIBSU_DEBUGGER";
    private static final String INTENT_BUNDLE_KEY = "extra.bundle";
    private static final String INTENT_DAEMON_KEY = "extra.daemon";
    private static final String JVMTI_ERROR = " \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n! Warning: JVMTI agent is enabled. Please enable the !\n! 'Always install with package manager' option in    !\n! Android Studio. For more details and information,  !\n! check out RootService's Javadoc.                   !\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
    static final String LOGGING_ENV = "LIBSU_VERBOSE_LOGGING";
    static final int MSG_STOP = 1;
    private static final String RECEIVER_BROADCAST = "com.topjohnwu.superuser.RECEIVER_BROADCAST";
    private static final int RECEIVER_REGISTERED = 4;
    private static final int REMOTE_EN_ROUTE = 1;
    static final String TAG = "IPC";
    private static RootServiceManager mInstance;
    private RemoteProcess mDaemon;
    private RemoteProcess mRemote;
    private int flags = 0;
    private final List<BindTask> pendingTasks = new ArrayList();
    private final Map<ServiceKey, RemoteServiceRecord> services = new ArrayMap();
    private final Map<ServiceConnection, ConnectionRecord> connections = new ArrayMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface BindTask {
        boolean run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface Predicate {
        boolean eval(RemoteServiceRecord remoteServiceRecord);
    }

    static /* synthetic */ int access$472(RootServiceManager rootServiceManager, int i) {
        int i2 = i & rootServiceManager.flags;
        rootServiceManager.flags = i2;
        return i2;
    }

    public static RootServiceManager getInstance() {
        if (mInstance == null) {
            mInstance = new RootServiceManager();
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"WrongConstant"})
    public static Intent getBroadcastIntent(IBinder iBinder, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBinder(BUNDLE_BINDER_KEY, iBinder);
        return new Intent(RECEIVER_BROADCAST).setPackage(Utils.getContext().getPackageName()).addFlags(HiddenAPIs.FLAG_RECEIVER_FROM_SHELL).putExtra(INTENT_DAEMON_KEY, z).putExtra(INTENT_BUNDLE_KEY, bundle);
    }

    private static void enforceMainThread() {
        if (!ShellUtils.onMainThread()) {
            throw new IllegalStateException("This method can only be called on the main thread");
        }
    }

    @NonNull
    private static ServiceKey parseIntent(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            throw new IllegalArgumentException("The intent does not have a component set");
        }
        if (!component.getPackageName().equals(Utils.getContext().getPackageName())) {
            throw new IllegalArgumentException("RootServices outside of the app are not supported");
        }
        return new ServiceKey(component, intent.hasCategory(RootService.CATEGORY_DAEMON_MODE));
    }

    private RootServiceManager() {
    }

    @SuppressLint({"InlinedApi"})
    private Shell.Task startRootProcess(final ComponentName componentName, final String str) {
        Context context = Utils.getContext();
        if (Utils.hasStartupAgents(context)) {
            Log.e(TAG, JVMTI_ERROR);
        }
        if ((this.flags & 4) == 0) {
            IntentFilter intentFilter = new IntentFilter(RECEIVER_BROADCAST);
            if (Build.VERSION.SDK_INT >= 26) {
                context.registerReceiver(new ServiceReceiver(), intentFilter, "android.permission.BROADCAST_PACKAGE_REMOVED", null, 4);
            } else {
                context.registerReceiver(new ServiceReceiver(), intentFilter, "android.permission.BROADCAST_PACKAGE_REMOVED", null);
            }
            this.flags |= 4;
        }
        return new Shell.Task() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$$ExternalSyntheticLambda1
            @Override // com.topjohnwu.superuser.Shell.Task
            public final void run(OutputStream outputStream, InputStream inputStream, InputStream inputStream2) {
                RootServiceManager.lambda$startRootProcess$0(str, componentName, outputStream, inputStream, inputStream2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$startRootProcess$0(String str, ComponentName componentName, OutputStream outputStream, InputStream inputStream, InputStream inputStream2) throws IOException {
        String str2;
        Context deContext = Utils.getDeContext();
        File file = new File(deContext.getCacheDir(), "main.jar");
        InputStream open = deContext.getResources().getAssets().open("main.jar");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Utils.pump(open, fileOutputStream);
            fileOutputStream.close();
            if (open != null) {
                open.close();
            }
            String str3 = "";
            String str4 = Utils.vLog() ? "LIBSU_VERBOSE_LOGGING=1 " : "";
            if (Build.VERSION.SDK_INT < 27 || !Debug.isDebuggerConnected()) {
                str2 = "";
            } else {
                str4 = str4 + "LIBSU_DEBUGGER=1 ";
                str2 = Build.VERSION.SDK_INT == 27 ? API_27_DEBUG : API_28_DEBUG;
            }
            str.hashCode();
            if (str.equals("daemon")) {
                str3 = "--nice-name=" + deContext.getPackageName() + ":root:daemon";
            } else if (str.equals("start")) {
                str3 = String.format(Locale.ROOT, "--nice-name=%s:root:%d", deContext.getPackageName(), Integer.valueOf(Process.myUid() / 100000));
            }
            String str5 = "/system/bin/app_process";
            if (Build.VERSION.SDK_INT >= 21) {
                StringBuilder sb = new StringBuilder();
                sb.append("/system/bin/app_process");
                sb.append(Utils.isProcess64Bit() ? "64" : "32");
                str5 = sb.toString();
            }
            String format = String.format(Locale.ROOT, "(%s CLASSPATH=%s %s %s /system/bin %s com.topjohnwu.superuser.internal.RootServerMain '%s' %d %s >/dev/null 2>&1)&", str4, file, str5, str2, str3, componentName.flattenToString(), Integer.valueOf(Process.myUid()), str);
            Utils.log(TAG, format);
            outputStream.write(format.getBytes(StandardCharsets.UTF_8));
            outputStream.write(10);
            outputStream.flush();
        } catch (Throwable th) {
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    private ServiceKey bindInternal(Intent intent, Executor executor, final ServiceConnection serviceConnection) {
        enforceMainThread();
        final ServiceKey parseIntent = parseIntent(intent);
        RemoteServiceRecord remoteServiceRecord = this.services.get(parseIntent);
        if (remoteServiceRecord != null) {
            this.connections.put(serviceConnection, new ConnectionRecord(remoteServiceRecord, executor));
            remoteServiceRecord.refCount++;
            final IBinder iBinder = remoteServiceRecord.binder;
            executor.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    serviceConnection.onServiceConnected(parseIntent.getName(), iBinder);
                }
            });
            return null;
        }
        RemoteProcess remoteProcess = parseIntent.isDaemon() ? this.mDaemon : this.mRemote;
        if (remoteProcess == null) {
            return parseIntent;
        }
        try {
            final IBinder bind = remoteProcess.mgr.bind(intent);
            if (bind != null) {
                RemoteServiceRecord remoteServiceRecord2 = new RemoteServiceRecord(parseIntent, bind, remoteProcess);
                this.connections.put(serviceConnection, new ConnectionRecord(remoteServiceRecord2, executor));
                this.services.put(parseIntent, remoteServiceRecord2);
                executor.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        serviceConnection.onServiceConnected(parseIntent.getName(), bind);
                    }
                });
            } else if (Build.VERSION.SDK_INT >= 28) {
                executor.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        serviceConnection.onNullBinding(parseIntent.getName());
                    }
                });
            }
            return null;
        } catch (RemoteException e) {
            Utils.err(TAG, e);
            remoteProcess.binderDied();
            return parseIntent;
        }
    }

    public Shell.Task createBindTask(final Intent intent, final Executor executor, final ServiceConnection serviceConnection) {
        ServiceKey bindInternal = bindInternal(intent, executor, serviceConnection);
        if (bindInternal != null) {
            this.pendingTasks.add(new BindTask() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$$ExternalSyntheticLambda5
                @Override // com.topjohnwu.superuser.internal.RootServiceManager.BindTask
                public final boolean run() {
                    return RootServiceManager.this.m137xa045f4ef(intent, executor, serviceConnection);
                }
            });
            int i = bindInternal.isDaemon() ? 2 : 1;
            int i2 = this.flags;
            if ((i2 & i) == 0) {
                this.flags = i | i2;
                return startRootProcess(bindInternal.getName(), bindInternal.isDaemon() ? "daemon" : "start");
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createBindTask$4$com-topjohnwu-superuser-internal-RootServiceManager  reason: not valid java name */
    public /* synthetic */ boolean m137xa045f4ef(Intent intent, Executor executor, ServiceConnection serviceConnection) {
        return bindInternal(intent, executor, serviceConnection) == null;
    }

    public void unbind(@NonNull ServiceConnection serviceConnection) {
        enforceMainThread();
        ConnectionRecord remove = this.connections.remove(serviceConnection);
        if (remove != null) {
            RemoteServiceRecord service = remove.getService();
            service.refCount--;
            if (service.refCount == 0) {
                this.services.remove(service.key);
                try {
                    service.host.mgr.unbind(service.key.getName());
                } catch (RemoteException e) {
                    Utils.err(TAG, e);
                }
            }
            remove.disconnect(serviceConnection);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dropConnections(Predicate predicate) {
        Iterator<Map.Entry<ServiceConnection, ConnectionRecord>> it = this.connections.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<ServiceConnection, ConnectionRecord> next = it.next();
            ConnectionRecord value = next.getValue();
            if (predicate.eval(value.getService())) {
                value.disconnect(next.getKey());
                it.remove();
            }
        }
    }

    private void onServiceStopped(ServiceKey serviceKey) {
        final RemoteServiceRecord remove = this.services.remove(serviceKey);
        if (remove != null) {
            remove.getClass();
            dropConnections(new Predicate() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$$ExternalSyntheticLambda6
                @Override // com.topjohnwu.superuser.internal.RootServiceManager.Predicate
                public final boolean eval(RootServiceManager.RemoteServiceRecord remoteServiceRecord) {
                    return RootServiceManager.RemoteServiceRecord.this.equals(remoteServiceRecord);
                }
            });
        }
    }

    public Shell.Task createStopTask(Intent intent) {
        enforceMainThread();
        ServiceKey parseIntent = parseIntent(intent);
        RemoteProcess remoteProcess = parseIntent.isDaemon() ? this.mDaemon : this.mRemote;
        if (remoteProcess == null) {
            if (parseIntent.isDaemon()) {
                return startRootProcess(parseIntent.getName(), "stop");
            }
            return null;
        }
        try {
            remoteProcess.mgr.stop(parseIntent.getName(), -1);
        } catch (RemoteException e) {
            Utils.err(TAG, e);
        }
        onServiceStopped(parseIntent);
        return null;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 1) {
            onServiceStopped(new ServiceKey((ComponentName) message.obj, message.arg1 != 0));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ServiceKey extends Pair<ComponentName, Boolean> {
        ServiceKey(ComponentName componentName, boolean z) {
            super(componentName, Boolean.valueOf(z));
        }

        ComponentName getName() {
            return (ComponentName) this.first;
        }

        boolean isDaemon() {
            return ((Boolean) this.second).booleanValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ConnectionRecord extends Pair<RemoteServiceRecord, Executor> {
        ConnectionRecord(RemoteServiceRecord remoteServiceRecord, Executor executor) {
            super(remoteServiceRecord, executor);
        }

        RemoteServiceRecord getService() {
            return (RemoteServiceRecord) this.first;
        }

        void disconnect(final ServiceConnection serviceConnection) {
            ((Executor) this.second).execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$ConnectionRecord$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RootServiceManager.ConnectionRecord.this.m138xb85bcd2a(serviceConnection);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$disconnect$0$com-topjohnwu-superuser-internal-RootServiceManager$ConnectionRecord  reason: not valid java name */
        public /* synthetic */ void m138xb85bcd2a(ServiceConnection serviceConnection) {
            serviceConnection.onServiceDisconnected(((RemoteServiceRecord) this.first).key.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class RemoteProcess extends BinderHolder {
        final IRootServiceManager mgr;

        RemoteProcess(IRootServiceManager iRootServiceManager) throws RemoteException {
            super(iRootServiceManager.asBinder());
            this.mgr = iRootServiceManager;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.topjohnwu.superuser.internal.BinderHolder
        public void onBinderDied() {
            if (RootServiceManager.this.mRemote == this) {
                RootServiceManager.this.mRemote = null;
            }
            if (RootServiceManager.this.mDaemon == this) {
                RootServiceManager.this.mDaemon = null;
            }
            Iterator it = RootServiceManager.this.services.values().iterator();
            while (it.hasNext()) {
                if (((RemoteServiceRecord) it.next()).host == this) {
                    it.remove();
                }
            }
            RootServiceManager.this.dropConnections(new Predicate() { // from class: com.topjohnwu.superuser.internal.RootServiceManager$RemoteProcess$$ExternalSyntheticLambda0
                @Override // com.topjohnwu.superuser.internal.RootServiceManager.Predicate
                public final boolean eval(RootServiceManager.RemoteServiceRecord remoteServiceRecord) {
                    return RootServiceManager.RemoteProcess.this.m139x23acb85f(remoteServiceRecord);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onBinderDied$0$com-topjohnwu-superuser-internal-RootServiceManager$RemoteProcess  reason: not valid java name */
        public /* synthetic */ boolean m139x23acb85f(RemoteServiceRecord remoteServiceRecord) {
            return remoteServiceRecord.host == this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class RemoteServiceRecord {
        final IBinder binder;
        final RemoteProcess host;
        final ServiceKey key;
        int refCount = 1;

        RemoteServiceRecord(ServiceKey serviceKey, IBinder iBinder, RemoteProcess remoteProcess) {
            this.key = serviceKey;
            this.binder = iBinder;
            this.host = remoteProcess;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ServiceReceiver extends BroadcastReceiver {
        private final Messenger m;

        ServiceReceiver() {
            this.m = new Messenger(new Handler(Looper.getMainLooper(), RootServiceManager.this));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            IBinder binder;
            Bundle bundleExtra = intent.getBundleExtra(RootServiceManager.INTENT_BUNDLE_KEY);
            if (bundleExtra == null || (binder = bundleExtra.getBinder(RootServiceManager.BUNDLE_BINDER_KEY)) == null) {
                return;
            }
            IRootServiceManager asInterface = IRootServiceManager.Stub.asInterface(binder);
            try {
                asInterface.connect(this.m.getBinder());
                RemoteProcess remoteProcess = new RemoteProcess(asInterface);
                if (intent.getBooleanExtra(RootServiceManager.INTENT_DAEMON_KEY, false)) {
                    RootServiceManager.this.mDaemon = remoteProcess;
                    RootServiceManager.access$472(RootServiceManager.this, -3);
                } else {
                    RootServiceManager.this.mRemote = remoteProcess;
                    RootServiceManager.access$472(RootServiceManager.this, -2);
                }
                for (int size = RootServiceManager.this.pendingTasks.size() - 1; size >= 0; size--) {
                    if (((BindTask) RootServiceManager.this.pendingTasks.get(size)).run()) {
                        RootServiceManager.this.pendingTasks.remove(size);
                    }
                }
            } catch (RemoteException e) {
                Utils.err(RootServiceManager.TAG, e);
            }
        }
    }
}
