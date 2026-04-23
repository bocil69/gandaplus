package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzug */
/* loaded from: classes.dex */
public final class zzug extends zzakk implements zzalq {
    private static final zzug zzb;
    private int zzd;
    private zzwn zze;

    static {
        zzug zzugVar = new zzug();
        zzb = zzugVar;
        zzakk.zzH(zzug.class, zzugVar);
    }

    private zzug() {
    }

    public static zzuf zza() {
        return (zzuf) zzb.zzt();
    }

    public static zzug zzc() {
        return zzb;
    }

    public static /* synthetic */ void zze(zzug zzugVar, zzwn zzwnVar) {
        zzwnVar.getClass();
        zzugVar.zze = zzwnVar;
        zzugVar.zzd |= 1;
    }

    public final zzwn zzd() {
        zzwn zzwnVar = this.zze;
        return zzwnVar == null ? zzwn.zzc() : zzwnVar;
    }

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
                    return new zzuf(null);
                }
                return new zzug();
            }
            return zzE(zzb, "\u0000\u0001\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0000\u0002ဉ\u0000", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
