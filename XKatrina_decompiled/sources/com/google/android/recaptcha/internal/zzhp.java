package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzhp implements zzhw {
    private final zzhw[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhp(zzhw... zzhwVarArr) {
        this.zza = zzhwVarArr;
    }

    @Override // com.google.android.recaptcha.internal.zzhw
    public final zzhv zzb(Class cls) {
        zzhw[] zzhwVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzhw zzhwVar = zzhwVarArr[i];
            if (zzhwVar.zzc(cls)) {
                return zzhwVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.recaptcha.internal.zzhw
    public final boolean zzc(Class cls) {
        zzhw[] zzhwVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzhwVarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
