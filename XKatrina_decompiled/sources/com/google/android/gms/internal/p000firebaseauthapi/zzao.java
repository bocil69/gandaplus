package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzao  reason: invalid package */
/* loaded from: classes.dex */
public final class zzao {
    Object[] zza;
    int zzb;
    zzan zzc;

    public zzao() {
        this(4);
    }

    private final void zzb(int i) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i2 = i + i;
        if (i2 > length) {
            this.zza = Arrays.copyOf(objArr, zzah.zza(length, i2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzao zza(Iterable iterable) {
        if (iterable instanceof Collection) {
            zzb(this.zzb + iterable.size());
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            zzb(this.zzb + 1);
            zzae.zza(key, value);
            Object[] objArr = this.zza;
            int i = this.zzb;
            int i2 = i + i;
            objArr[i2] = key;
            objArr[i2 + 1] = value;
            this.zzb = i + 1;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzao(int i) {
        this.zza = new Object[i + i];
        this.zzb = 0;
    }
}
