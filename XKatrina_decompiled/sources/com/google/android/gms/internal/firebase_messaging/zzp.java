package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
final class zzp {
    private final ConcurrentHashMap<zzo, List<Throwable>> zzn = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzo = new ReferenceQueue<>();

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> poll = this.zzo.poll();
        while (poll != null) {
            this.zzn.remove(poll);
            poll = this.zzo.poll();
        }
        List<Throwable> list = this.zzn.get(new zzo(th, null));
        if (list == null) {
            Vector vector = new Vector(2);
            List<Throwable> putIfAbsent = this.zzn.putIfAbsent(new zzo(th, this.zzo), vector);
            return putIfAbsent == null ? vector : putIfAbsent;
        }
        return list;
    }
}
