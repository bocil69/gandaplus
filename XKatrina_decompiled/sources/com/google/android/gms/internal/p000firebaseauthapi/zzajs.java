package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajs  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzajs extends zzaiv {
    private static final Logger zza = Logger.getLogger(zzajs.class.getName());
    private static final boolean zzb = zzanf.zzx();
    public static final /* synthetic */ int zzf = 0;
    zzajt zze;

    private zzajs() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzajs(zzajr zzajrVar) {
    }

    public static int zzA(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzB(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            j >>>= 14;
            i += 2;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzajs zzC(byte[] bArr, int i, int i2) {
        return new zzajo(bArr, 0, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzw(int i, zzalp zzalpVar, zzamb zzambVar) {
        int zzn = ((zzaip) zzalpVar).zzn(zzambVar);
        int zzA = zzA(i << 3);
        return zzA + zzA + zzn;
    }

    public static int zzx(int i) {
        if (i >= 0) {
            return zzA(i);
        }
        return 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(zzalp zzalpVar, zzamb zzambVar) {
        int zzn = ((zzaip) zzalpVar).zzn(zzambVar);
        return zzA(zzn) + zzn;
    }

    public static int zzz(String str) {
        int length;
        try {
            length = zzank.zzc(str);
        } catch (zzanj unused) {
            length = str.getBytes(zzakq.zzb).length;
        }
        return zzA(length) + length;
    }

    public final void zzD() {
        if (zzb() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzE(String str, zzanj zzanjVar) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzanjVar);
        byte[] bytes = str.getBytes(zzakq.zzb);
        try {
            int length = bytes.length;
            zzs(length);
            zza(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzajp(e);
        }
    }

    public abstract void zzI() throws IOException;

    public abstract void zzJ(byte b) throws IOException;

    public abstract void zzK(int i, boolean z) throws IOException;

    public abstract void zzL(int i, zzajf zzajfVar) throws IOException;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaiv
    public abstract void zza(byte[] bArr, int i, int i2) throws IOException;

    public abstract int zzb();

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzi(int i) throws IOException;

    public abstract void zzj(int i, long j) throws IOException;

    public abstract void zzk(long j) throws IOException;

    public abstract void zzl(int i, int i2) throws IOException;

    public abstract void zzm(int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzn(int i, zzalp zzalpVar, zzamb zzambVar) throws IOException;

    public abstract void zzo(int i, String str) throws IOException;

    public abstract void zzq(int i, int i2) throws IOException;

    public abstract void zzr(int i, int i2) throws IOException;

    public abstract void zzs(int i) throws IOException;

    public abstract void zzt(int i, long j) throws IOException;

    public abstract void zzu(long j) throws IOException;
}
