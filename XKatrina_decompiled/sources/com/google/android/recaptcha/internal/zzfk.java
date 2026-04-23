package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public abstract class zzfk extends zzep {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzfk.class.getName());
    private static final boolean zzd = zzjp.zzx();
    zzfl zza;

    private zzfk() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfk(zzfj zzfjVar) {
    }

    public static zzfk zzA(byte[] bArr, int i, int i2) {
        return new zzfh(bArr, 0, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzt(int i, zzhy zzhyVar, zzil zzilVar) {
        int zza = ((zzei) zzhyVar).zza(zzilVar);
        int zzy = zzy(i << 3);
        return zzy + zzy + zza;
    }

    public static int zzu(int i) {
        if (i >= 0) {
            return zzy(i);
        }
        return 10;
    }

    public static int zzv(zzhy zzhyVar) {
        int zzn = zzhyVar.zzn();
        return zzy(zzn) + zzn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(zzhy zzhyVar, zzil zzilVar) {
        int zza = ((zzei) zzhyVar).zza(zzilVar);
        return zzy(zza) + zza;
    }

    public static int zzx(String str) {
        int length;
        try {
            length = zzju.zzc(str);
        } catch (zzjt unused) {
            length = str.getBytes(zzgw.zzb).length;
        }
        return zzy(length) + length;
    }

    public static int zzy(int i) {
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

    public static int zzz(long j) {
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

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzC(String str, zzjt zzjtVar) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzjtVar);
        byte[] bytes = str.getBytes(zzgw.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzfi(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzez zzezVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
