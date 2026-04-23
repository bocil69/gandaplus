package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzaf {
    private final zzkw zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze = null;

    public zzaf(zzkw zzkwVar, String str, String str2, String str3, String str4) {
        this.zza = zzkwVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzaf) {
            zzaf zzafVar = (zzaf) obj;
            if (zzafVar.zza == this.zza && Intrinsics.areEqual(zzafVar.zzb, this.zzb) && Intrinsics.areEqual(zzafVar.zzc, this.zzc) && Intrinsics.areEqual(zzafVar.zzd, this.zzd)) {
                String str = zzafVar.zze;
                return Intrinsics.areEqual((Object) null, (Object) null);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, null});
    }

    public final zzkw zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }
}
