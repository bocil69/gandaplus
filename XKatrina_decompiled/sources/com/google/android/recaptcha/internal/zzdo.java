package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;
import java.util.Queue;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzdo extends zzds implements Serializable {
    final int zza;
    private final Queue zzb;

    public static zzdo zza(int i) {
        return new zzdo(i);
    }

    @Override // com.google.android.recaptcha.internal.zzdq, java.util.Collection
    public final boolean addAll(Collection collection) {
        int size = collection.size();
        if (size < this.zza) {
            return zzdv.zza(this, collection.iterator());
        }
        clear();
        int i = size - this.zza;
        Objects.requireNonNull(collection);
        zzdi.zzb(i >= 0, "number to skip cannot be negative");
        return zzdv.zza(this, new zzdu(collection, i).iterator());
    }

    @Override // com.google.android.recaptcha.internal.zzds, java.util.Queue
    public final boolean offer(Object obj) {
        add(obj);
        return true;
    }

    @Override // com.google.android.recaptcha.internal.zzdq, com.google.android.recaptcha.internal.zzdr
    protected final /* synthetic */ Object zzb() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzds, com.google.android.recaptcha.internal.zzdq
    protected final /* synthetic */ Collection zzc() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzds
    protected final Queue zzd() {
        return this.zzb;
    }

    private zzdo(int i) {
        if (i >= 0) {
            this.zzb = new ArrayDeque(i);
            this.zza = i;
            return;
        }
        throw new IllegalArgumentException(zzdl.zza("maxSize (%s) must >= 0", Integer.valueOf(i)));
    }

    @Override // com.google.android.recaptcha.internal.zzdq, java.util.Collection, java.util.Queue
    public final boolean add(Object obj) {
        Objects.requireNonNull(obj);
        if (this.zza == 0) {
            return true;
        }
        if (size() == this.zza) {
            this.zzb.remove();
        }
        this.zzb.add(obj);
        return true;
    }
}
