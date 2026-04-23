package com.topjohnwu.superuser.internal;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.os.FileObserver;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.internal.IRootServiceManager;
import com.topjohnwu.superuser.ipc.RootService;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class RootServiceServer extends IRootServiceManager.Stub implements Runnable {
    private static RootServiceServer mInstance;
    private final boolean isDaemon;
    private final FileObserver observer;
    private final Map<ComponentName, ServiceRecord> services = new ArrayMap();
    private final SparseArray<ClientProcess> clients = new SparseArray<>();

    public static RootServiceServer getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RootServiceServer(context);
        }
        return mInstance;
    }

    private RootServiceServer(Context context) {
        Shell.enableVerboseLogging = System.getenv("LIBSU_VERBOSE_LOGGING") != null;
        Utils.context = context;
        if (System.getenv("LIBSU_DEBUGGER") != null) {
            HiddenAPIs.setAppName(context.getPackageName() + ":root");
            Utils.log("IPC", "Waiting for debugger to be attached...");
            while (!Debug.isDebuggerConnected()) {
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException unused) {
                }
            }
            Utils.log("IPC", "Debugger attached!");
        }
        AppObserver appObserver = new AppObserver(new File(context.getPackageCodePath()));
        this.observer = appObserver;
        appObserver.startWatching();
        if (context instanceof Callable) {
            try {
                Object[] objArr = (Object[]) ((Callable) context).call();
                boolean booleanValue = ((Boolean) objArr[1]).booleanValue();
                this.isDaemon = booleanValue;
                if (booleanValue) {
                    HiddenAPIs.addService(RootServerMain.getServiceName(context.getPackageName()), this);
                }
                broadcast(((Integer) objArr[0]).intValue());
                if (booleanValue) {
                    return;
                }
                UiThreadHandler.handler.postDelayed(this, 10000L);
                return;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("Expected Context to be Callable");
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.clients.size() == 0) {
            exit("No active clients");
        }
    }

    @Override // com.topjohnwu.superuser.internal.IRootServiceManager
    public void connect(final IBinder iBinder) {
        final int callingUid = getCallingUid();
        UiThreadHandler.run(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceServer$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                RootServiceServer.this.m141x3a33d973(callingUid, iBinder);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: connectInternal */
    public void m141x3a33d973(int i, IBinder iBinder) {
        if (this.clients.get(i) != null) {
            return;
        }
        try {
            this.clients.put(i, new ClientProcess(iBinder, i));
            UiThreadHandler.handler.removeCallbacks(this);
        } catch (RemoteException e) {
            Utils.err("IPC", e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IRootServiceManager
    @SuppressLint({"MissingPermission"})
    public void broadcast(int i) {
        if (getCallingUid() != 0) {
            i = getCallingUid();
        }
        Utils.log("IPC", "broadcast to uid=" + i);
        Intent broadcastIntent = RootServiceManager.getBroadcastIntent(this, this.isDaemon);
        if (Build.VERSION.SDK_INT >= 24) {
            Utils.context.sendBroadcastAsUser(broadcastIntent, UserHandle.getUserHandleForUid(i));
            return;
        }
        Utils.context.sendBroadcast(broadcastIntent);
    }

    @Override // com.topjohnwu.superuser.internal.IRootServiceManager
    public IBinder bind(final Intent intent) {
        final IBinder[] iBinderArr = new IBinder[1];
        final int callingUid = getCallingUid();
        UiThreadHandler.runAndWait(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceServer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RootServiceServer.this.m140lambda$bind$1$comtopjohnwusuperuserinternalRootServiceServer(iBinderArr, callingUid, intent);
            }
        });
        return iBinderArr[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$bind$1$com-topjohnwu-superuser-internal-RootServiceServer  reason: not valid java name */
    public /* synthetic */ void m140lambda$bind$1$comtopjohnwusuperuserinternalRootServiceServer(IBinder[] iBinderArr, int i, Intent intent) {
        try {
            iBinderArr[0] = bindInternal(i, intent);
        } catch (Exception e) {
            Utils.err("IPC", e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IRootServiceManager
    public void unbind(final ComponentName componentName) {
        final int callingUid = getCallingUid();
        UiThreadHandler.run(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceServer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                RootServiceServer.this.m144xcd49212b(componentName, callingUid);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$unbind$2$com-topjohnwu-superuser-internal-RootServiceServer  reason: not valid java name */
    public /* synthetic */ void m144xcd49212b(ComponentName componentName, int i) {
        Utils.log("IPC", componentName.getClassName() + " unbind");
        unbindService(i, componentName);
    }

    @Override // com.topjohnwu.superuser.internal.IRootServiceManager
    public void stop(final ComponentName componentName, final int i) {
        if (getCallingUid() != 0) {
            i = getCallingUid();
        }
        UiThreadHandler.run(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceServer$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                RootServiceServer.this.m143lambda$stop$3$comtopjohnwusuperuserinternalRootServiceServer(componentName, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$stop$3$com-topjohnwu-superuser-internal-RootServiceServer  reason: not valid java name */
    public /* synthetic */ void m143lambda$stop$3$comtopjohnwusuperuserinternalRootServiceServer(ComponentName componentName, int i) {
        Utils.log("IPC", componentName.getClassName() + " stop");
        unbindService(-1, componentName);
        broadcast(i);
    }

    public void selfStop(final ComponentName componentName) {
        UiThreadHandler.run(new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceServer$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RootServiceServer.this.m142x90c17111(componentName);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$selfStop$4$com-topjohnwu-superuser-internal-RootServiceServer  reason: not valid java name */
    public /* synthetic */ void m142x90c17111(ComponentName componentName) {
        Utils.log("IPC", componentName.getClassName() + " selfStop");
        unbindService(-1, componentName);
    }

    public void register(RootService rootService) {
        this.services.put(rootService.getComponentName(), new ServiceRecord(rootService));
    }

    private IBinder bindInternal(int i, Intent intent) throws Exception {
        if (this.clients.get(i) == null) {
            return null;
        }
        ComponentName component = intent.getComponent();
        ServiceRecord serviceRecord = this.services.get(component);
        if (serviceRecord == null) {
            Context context = Utils.context;
            Constructor<?> declaredConstructor = context.getClassLoader().loadClass(component.getClassName()).getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            HiddenAPIs.attachBaseContext(declaredConstructor.newInstance(new Object[0]), context);
            serviceRecord = this.services.get(component);
            if (serviceRecord == null) {
                return null;
            }
        }
        if (serviceRecord.binder != null) {
            Utils.log("IPC", component.getClassName() + " rebind");
            if (serviceRecord.rebind) {
                serviceRecord.service.onRebind(serviceRecord.intent);
            }
        } else {
            Utils.log("IPC", component.getClassName() + " bind");
            serviceRecord.binder = serviceRecord.service.onBind(intent);
            serviceRecord.intent = intent.cloneFilter();
        }
        serviceRecord.users.add(Integer.valueOf(i));
        return serviceRecord.binder;
    }

    private void unbindInternal(ServiceRecord serviceRecord, int i, Runnable runnable) {
        boolean z = !serviceRecord.users.isEmpty();
        serviceRecord.users.remove(Integer.valueOf(i));
        if (i < 0 || serviceRecord.users.isEmpty()) {
            if (z) {
                serviceRecord.rebind = serviceRecord.service.onUnbind(serviceRecord.intent);
            }
            if (i < 0 || !this.isDaemon) {
                serviceRecord.service.onDestroy();
                runnable.run();
                for (Integer num : serviceRecord.users) {
                    ClientProcess clientProcess = this.clients.get(num.intValue());
                    if (clientProcess != null) {
                        Message obtain = Message.obtain();
                        obtain.what = 1;
                        obtain.arg1 = this.isDaemon ? 1 : 0;
                        obtain.obj = serviceRecord.intent.getComponent();
                        try {
                            try {
                                clientProcess.m.send(obtain);
                            } catch (RemoteException e) {
                                Utils.err("IPC", e);
                            }
                        } finally {
                            obtain.recycle();
                        }
                    }
                }
            }
        }
        if (this.services.isEmpty()) {
            exit("No active services");
        }
    }

    private void unbindService(int i, final ComponentName componentName) {
        ServiceRecord serviceRecord = this.services.get(componentName);
        if (serviceRecord == null) {
            return;
        }
        unbindInternal(serviceRecord, i, new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceServer$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                RootServiceServer.this.m145x7735419(componentName);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$unbindService$5$com-topjohnwu-superuser-internal-RootServiceServer  reason: not valid java name */
    public /* synthetic */ void m145x7735419(ComponentName componentName) {
        this.services.remove(componentName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbindServices(int i) {
        final Iterator<Map.Entry<ComponentName, ServiceRecord>> it = this.services.entrySet().iterator();
        while (it.hasNext()) {
            ServiceRecord value = it.next().getValue();
            if (i < 0) {
                value.users.clear();
            }
            it.getClass();
            unbindInternal(value, i, new Runnable() { // from class: com.topjohnwu.superuser.internal.RootServiceServer$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    it.remove();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exit(String str) {
        Utils.log("IPC", "Terminate process: " + str);
        System.exit(0);
    }

    /* loaded from: classes2.dex */
    private class AppObserver extends FileObserver {
        private final String name;

        AppObserver(File file) {
            super(file.getParent(), 1984);
            Utils.log("IPC", "Start monitoring: " + file.getParent());
            this.name = file.getName();
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, @Nullable String str) {
            if (i == 1024 || this.name.equals(str)) {
                RootServiceServer.this.exit("Package updated");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ClientProcess extends BinderHolder {
        final Messenger m;
        final int uid;

        ClientProcess(IBinder iBinder, int i) throws RemoteException {
            super(iBinder);
            this.m = new Messenger(iBinder);
            this.uid = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.topjohnwu.superuser.internal.BinderHolder
        public void onBinderDied() {
            Utils.log("IPC", "Client process terminated, uid=" + this.uid);
            RootServiceServer.this.clients.remove(this.uid);
            RootServiceServer.this.unbindServices(this.uid);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ServiceRecord {
        IBinder binder;
        Intent intent;
        boolean rebind;
        final RootService service;
        final Set<Integer> users = Utils.newArraySet();

        ServiceRecord(RootService rootService) {
            this.service = rootService;
        }
    }
}
