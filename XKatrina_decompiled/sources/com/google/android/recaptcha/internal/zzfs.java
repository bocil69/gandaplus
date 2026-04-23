package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzfs extends zzgo implements zzhz {
    private static final zzfs zzb;
    private int zzd;
    private long zzg;
    private long zzh;
    private double zzi;
    private byte zzl = 2;
    private zzgv zze = zzii.zze();
    private String zzf = "";
    private zzez zzj = zzez.zzb;
    private String zzk = "";

    static {
        zzfs zzfsVar = new zzfs();
        zzb = zzfsVar;
        zzgo.zzC(zzfs.class, zzfsVar);
    }

    private zzfs() {
    }

    @Override // com.google.android.recaptcha.internal.zzgo
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            return zzb;
                        }
                        this.zzl = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return new zzfp(null);
                }
                return new zzfs();
            }
            return new zzij(zzb, "\u0001\u0007\u0000\u0001\u0002\b\u0007\u0000\u0001\u0001\u0002Л\u0003ဈ\u0000\u0004ဃ\u0001\u0005ဂ\u0002\u0006က\u0003\u0007ည\u0004\bဈ\u0005", new Object[]{"zzd", "zze", zzfr.class, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        return Byte.valueOf(this.zzl);
    }
}
