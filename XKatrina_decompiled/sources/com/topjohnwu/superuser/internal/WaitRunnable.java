package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public final class WaitRunnable implements Runnable {
    private Runnable r;

    public WaitRunnable(@NonNull Runnable runnable) {
        this.r = runnable;
    }

    public synchronized void waitUntilDone() {
        while (this.r != null) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    @Override // java.lang.Runnable
    public synchronized void run() {
        this.r.run();
        this.r = null;
        notifyAll();
    }
}
