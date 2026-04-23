package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxa  reason: invalid package */
/* loaded from: classes.dex */
public final class zzxa extends zzakk implements zzalq {
    private static final zzxa zzb;
    private int zzd;
    private zzakp zze = zzA();

    static {
        zzxa zzxaVar = new zzxa();
        zzb = zzxaVar;
        zzakk.zzH(zzxa.class, zzxaVar);
    }

    private zzxa() {
    }

    public static zzwx zza() {
        return (zzwx) zzb.zzt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzxa zzxaVar, zzwz zzwzVar) {
        zzwzVar.getClass();
        zzakp zzakpVar = zzxaVar.zze;
        if (!zzakpVar.zzc()) {
            zzxaVar.zze = zzakk.zzB(zzakpVar);
        }
        zzxaVar.zze.add(zzwzVar);
    }

    public final zzwz zzb(int i) {
        return (zzwz) this.zze.get(0);
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
                    return new zzwx(null);
                }
                return new zzxa();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzd", "zze", zzwz.class});
        }
        return (byte) 1;
    }
}
