package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzum  reason: invalid package */
/* loaded from: classes.dex */
public final class zzum extends zzakk implements zzalq {
    private static final zzum zzb;
    private int zzd;
    private zzuv zze;
    private zzug zzf;
    private int zzg;

    static {
        zzum zzumVar = new zzum();
        zzb = zzumVar;
        zzakk.zzH(zzum.class, zzumVar);
    }

    private zzum() {
    }

    public static zzul zzc() {
        return (zzul) zzb.zzt();
    }

    public static zzum zze() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzum zzumVar, zzuv zzuvVar) {
        zzuvVar.getClass();
        zzumVar.zze = zzuvVar;
        zzumVar.zzd |= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzum zzumVar, zzug zzugVar) {
        zzugVar.getClass();
        zzumVar.zzf = zzugVar;
        zzumVar.zzd |= 2;
    }

    public final zzud zza() {
        int i = this.zzg;
        zzud zzudVar = zzud.UNKNOWN_FORMAT;
        zzud zzudVar2 = i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : zzud.DO_NOT_USE_CRUNCHY_UNCOMPRESSED : zzud.COMPRESSED : zzud.UNCOMPRESSED : zzud.UNKNOWN_FORMAT;
        return zzudVar2 == null ? zzud.UNRECOGNIZED : zzudVar2;
    }

    public final zzug zzb() {
        zzug zzugVar = this.zzf;
        return zzugVar == null ? zzug.zzc() : zzugVar;
    }

    public final zzuv zzf() {
        zzuv zzuvVar = this.zze;
        return zzuvVar == null ? zzuv.zzc() : zzuvVar;
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
                    return new zzul(null);
                }
                return new zzum();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\f", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
