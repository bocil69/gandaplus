package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzae implements ServiceConnection {
    @GuardedBy("this")
    int state;
    final Messenger zzcd;
    zzah zzce;
    @GuardedBy("this")
    final Queue<zzaj<?>> zzcf;
    @GuardedBy("this")
    final SparseArray<zzaj<?>> zzcg;
    final /* synthetic */ zzac zzch;

    private zzae(zzac zzacVar) {
        this.zzch = zzacVar;
        this.state = 0;
        this.zzcd = new Messenger(new com.google.android.gms.internal.firebase_messaging.zze(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.zzad
            private final zzae zzcc;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzcc = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zzcc.zza(message);
            }
        }));
        this.zzcf = new ArrayDeque();
        this.zzcg = new SparseArray<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zzb(zzaj zzajVar) {
        Context context;
        ScheduledExecutorService scheduledExecutorService;
        boolean z = true;
        synchronized (this) {
            switch (this.state) {
                case 0:
                    this.zzcf.add(zzajVar);
                    Preconditions.checkState(this.state == 0);
                    if (Log.isLoggable("MessengerIpcClient", 2)) {
                        Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                    }
                    this.state = 1;
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.setPackage("com.google.android.gms");
                    ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
                    context = this.zzch.zzag;
                    if (!connectionTracker.bindService(context, intent, this, 1)) {
                        zza(0, "Unable to bind to service");
                        break;
                    } else {
                        scheduledExecutorService = this.zzch.zzbz;
                        scheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.firebase.iid.zzag
                            private final zzae zzcc;

                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                this.zzcc = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzcc.zzaa();
                            }
                        }, 30L, TimeUnit.SECONDS);
                        break;
                    }
                case 1:
                    this.zzcf.add(zzajVar);
                    break;
                case 2:
                    this.zzcf.add(zzajVar);
                    zzy();
                    break;
                case 3:
                case 4:
                    z = false;
                    break;
                default:
                    throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.state).toString());
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", new StringBuilder(41).append("Received response to request: ").append(i).toString());
        }
        synchronized (this) {
            zzaj<?> zzajVar = this.zzcg.get(i);
            if (zzajVar == null) {
                Log.w("MessengerIpcClient", new StringBuilder(50).append("Received response for unknown request: ").append(i).toString());
            } else {
                this.zzcg.remove(i);
                zzz();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    zzajVar.zza(new zzam(4, "Not supported by GmsCore"));
                } else {
                    zzajVar.zzb(data);
                }
            }
        }
        return true;
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zza(0, "Null service connection");
        } else {
            try {
                this.zzce = new zzah(iBinder);
                this.state = 2;
                zzy();
            } catch (RemoteException e) {
                zza(0, e.getMessage());
            }
        }
    }

    private final void zzy() {
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = this.zzch.zzbz;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzaf
            private final zzae zzcc;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzcc = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzaj<?> poll;
                ScheduledExecutorService scheduledExecutorService2;
                Context context;
                final zzae zzaeVar = this.zzcc;
                while (true) {
                    synchronized (zzaeVar) {
                        if (zzaeVar.state == 2) {
                            if (zzaeVar.zzcf.isEmpty()) {
                                zzaeVar.zzz();
                                return;
                            }
                            poll = zzaeVar.zzcf.poll();
                            zzaeVar.zzcg.put(poll.zzck, poll);
                            scheduledExecutorService2 = zzaeVar.zzch.zzbz;
                            scheduledExecutorService2.schedule(new Runnable(zzaeVar, poll) { // from class: com.google.firebase.iid.zzai
                                private final zzae zzcc;
                                private final zzaj zzcj;

                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    this.zzcc = zzaeVar;
                                    this.zzcj = poll;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.zzcc.zza(this.zzcj.zzck);
                                }
                            }, 30L, TimeUnit.SECONDS);
                        } else {
                            return;
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String valueOf = String.valueOf(poll);
                        Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf).toString());
                    }
                    context = zzaeVar.zzch.zzag;
                    Messenger messenger = zzaeVar.zzcd;
                    Message obtain = Message.obtain();
                    obtain.what = poll.what;
                    obtain.arg1 = poll.zzck;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", poll.zzab());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle("data", poll.zzcm);
                    obtain.setData(bundle);
                    try {
                        zzaeVar.zzce.send(obtain);
                    } catch (RemoteException e) {
                        zzaeVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zza(2, "Service disconnected");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        Context context;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        switch (this.state) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
                context = this.zzch.zzag;
                connectionTracker.unbindService(context, this);
                zzam zzamVar = new zzam(i, str);
                for (zzaj<?> zzajVar : this.zzcf) {
                    zzajVar.zza(zzamVar);
                }
                this.zzcf.clear();
                for (int i2 = 0; i2 < this.zzcg.size(); i2++) {
                    this.zzcg.valueAt(i2).zza(zzamVar);
                }
                this.zzcg.clear();
                break;
            case 3:
                this.state = 4;
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException(new StringBuilder(26).append("Unknown state: ").append(this.state).toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzz() {
        Context context;
        if (this.state == 2 && this.zzcf.isEmpty() && this.zzcg.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzch.zzag;
            connectionTracker.unbindService(context, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzaa() {
        if (this.state == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i) {
        zzaj<?> zzajVar = this.zzcg.get(i);
        if (zzajVar != null) {
            Log.w("MessengerIpcClient", new StringBuilder(31).append("Timing out request: ").append(i).toString());
            this.zzcg.remove(i);
            zzajVar.zza(new zzam(3, "Timed out waiting for response"));
            zzz();
        }
    }
}
