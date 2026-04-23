package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.collections.CollectionsKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbf implements zzbi {
    private final zzdo zza;

    public zzbf() {
        this(1);
    }

    public final List zwk() {
        return zza();
    }

    public final List zza() {
        return CollectionsKt.toList(this.zza);
    }

    public final boolean zzb(List list) {
        this.zza.add(list);
        return true;
    }

    public zzbf(int i) {
        this.zza = zzdo.zza(i);
    }
}
