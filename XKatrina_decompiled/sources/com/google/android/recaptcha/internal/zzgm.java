package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzgm extends zzfx {
    final zzhy zza;
    final zzgl zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgm(zzhy zzhyVar, Object obj, zzhy zzhyVar2, zzgl zzglVar, Class cls) {
        if (zzhyVar == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
        if (zzglVar.zzb != zzjv.MESSAGE) {
            this.zza = zzhyVar;
            this.zzb = zzglVar;
            return;
        }
        throw new IllegalArgumentException("Null messageDefaultInstance");
    }
}
