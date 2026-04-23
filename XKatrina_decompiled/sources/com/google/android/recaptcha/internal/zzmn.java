package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzmn extends zzgo implements zzhz {
    private static final zzmn zzb;
    private zzgv zzd = zzw();
    private int zze;

    static {
        zzmn zzmnVar = new zzmn();
        zzb = zzmnVar;
        zzgo.zzC(zzmn.class, zzmnVar);
    }

    private zzmn() {
    }

    public static zzmk zzf() {
        return (zzmk) zzb.zzp();
    }

    public static /* synthetic */ void zzi(zzmn zzmnVar, zzmm zzmmVar) {
        zzmmVar.getClass();
        zzmnVar.zzk();
        zzmnVar.zzd.add(zzmmVar);
    }

    public static /* synthetic */ void zzj(zzmn zzmnVar, Iterable iterable) {
        zzmnVar.zzk();
        zzei.zzc(iterable, zzmnVar.zzd);
    }

    private final void zzk() {
        zzgv zzgvVar = this.zzd;
        if (zzgvVar.zzc()) {
            return;
        }
        this.zzd = zzgo.zzx(zzgvVar);
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
                    return new zzmk(null);
                }
                return new zzmn();
            }
            return zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u000b", new Object[]{"zzd", zzmm.class, "zze"});
        }
        return (byte) 1;
    }
}
