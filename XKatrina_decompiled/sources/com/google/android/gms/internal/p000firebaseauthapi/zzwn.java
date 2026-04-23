package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwn  reason: invalid package */
/* loaded from: classes.dex */
public final class zzwn extends zzakk implements zzalq {
    private static final zzwn zzb;
    private String zzd = "";
    private zzajf zze = zzajf.zzb;
    private int zzf;

    static {
        zzwn zzwnVar = new zzwn();
        zzb = zzwnVar;
        zzakk.zzH(zzwn.class, zzwnVar);
    }

    private zzwn() {
    }

    public static zzwm zza() {
        return (zzwm) zzb.zzt();
    }

    public static zzwn zzc() {
        return zzb;
    }

    public static zzwn zzd(byte[] bArr, zzajx zzajxVar) throws zzaks {
        return (zzwn) zzakk.zzz(zzb, bArr, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzwn zzwnVar, String str) {
        str.getClass();
        zzwnVar.zzd = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzwn zzwnVar, zzajf zzajfVar) {
        zzajfVar.getClass();
        zzwnVar.zze = zzajfVar;
    }

    public final zzxo zze() {
        zzxo zzb2 = zzxo.zzb(this.zzf);
        return zzb2 == null ? zzxo.UNRECOGNIZED : zzb2;
    }

    public final zzajf zzf() {
        return this.zze;
    }

    public final String zzg() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakk
    public final Object zzj(int i, Object obj, Object obj2) {
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
                    return new zzwm(null);
                }
                return new zzwn();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
