package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.io.InputStream;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzva  reason: invalid package */
/* loaded from: classes.dex */
public final class zzva extends zzakk implements zzalq {
    private static final zzva zzb;
    private int zzd;
    private zzajf zze = zzajf.zzb;
    private zzxa zzf;

    static {
        zzva zzvaVar = new zzva();
        zzb = zzvaVar;
        zzakk.zzH(zzva.class, zzvaVar);
    }

    private zzva() {
    }

    public static zzuz zza() {
        return (zzuz) zzb.zzt();
    }

    public static zzva zzc(InputStream inputStream, zzajx zzajxVar) throws IOException {
        return (zzva) zzakk.zzy(zzb, inputStream, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzva zzvaVar, zzxa zzxaVar) {
        zzxaVar.getClass();
        zzvaVar.zzf = zzxaVar;
        zzvaVar.zzd |= 1;
    }

    public final zzajf zzd() {
        return this.zze;
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
                    return new zzuz(null);
                }
                return new zzva();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003ဉ\u0000", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
