package com.google.android.recaptcha.internal;

import java.util.List;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzla extends zzgo implements zzhz {
    private static final zzla zzb;
    private zzgv zzd = zzw();

    static {
        zzla zzlaVar = new zzla();
        zzb = zzlaVar;
        zzgo.zzC(zzla.class, zzlaVar);
    }

    private zzla() {
    }

    public static zzkz zzf() {
        return (zzkz) zzb.zzp();
    }

    public static /* synthetic */ zzla zzg() {
        return zzb;
    }

    public static zzla zzi(byte[] bArr) throws zzgy {
        return (zzla) zzgo.zzu(zzb, bArr);
    }

    public static /* synthetic */ void zzk(zzla zzlaVar, zzkx zzkxVar) {
        zzkxVar.getClass();
        zzgv zzgvVar = zzlaVar.zzd;
        if (!zzgvVar.zzc()) {
            zzlaVar.zzd = zzgo.zzx(zzgvVar);
        }
        zzlaVar.zzd.add(zzkxVar);
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzkz(null);
                }
                return new zzla();
            }
            return zzz(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzkx.class});
        }
        return (byte) 1;
    }

    public final List zzj() {
        return this.zzd;
    }
}
