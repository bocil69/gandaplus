package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzwv extends zzakk implements zzalq {
    private static final zzwv zzb;
    private int zzd;
    private zzakp zze = zzA();

    static {
        zzwv zzwvVar = new zzwv();
        zzb = zzwvVar;
        zzakk.zzH(zzwv.class, zzwvVar);
    }

    private zzwv() {
    }

    public static zzws zzc() {
        return (zzws) zzb.zzt();
    }

    public static zzwv zzf(InputStream inputStream, zzajx zzajxVar) throws IOException {
        return (zzwv) zzakk.zzy(zzb, inputStream, zzajxVar);
    }

    public static zzwv zzg(byte[] bArr, zzajx zzajxVar) throws zzaks {
        return (zzwv) zzakk.zzz(zzb, bArr, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzwv zzwvVar, zzwu zzwuVar) {
        zzwuVar.getClass();
        zzakp zzakpVar = zzwvVar.zze;
        if (!zzakpVar.zzc()) {
            zzwvVar.zze = zzakk.zzB(zzakpVar);
        }
        zzwvVar.zze.add(zzwuVar);
    }

    public final int zza() {
        return this.zze.size();
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzwu zzd(int i) {
        return (zzwu) this.zze.get(i);
    }

    public final List zzh() {
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
                    return new zzws(null);
                }
                return new zzwv();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzd", "zze", zzwu.class});
        }
        return (byte) 1;
    }
}
