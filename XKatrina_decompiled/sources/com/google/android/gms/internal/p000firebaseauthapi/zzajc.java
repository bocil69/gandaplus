package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajc  reason: invalid package */
/* loaded from: classes.dex */
public class zzajc extends zzajb {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzajc(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzajf) && zzd() == ((zzajf) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzajc) {
                zzajc zzajcVar = (zzajc) obj;
                int zzm = zzm();
                int zzm2 = zzajcVar.zzm();
                if (zzm == 0 || zzm2 == 0 || zzm == zzm2) {
                    int zzd = zzd();
                    if (zzd > zzajcVar.zzd()) {
                        int zzd2 = zzd();
                        throw new IllegalArgumentException("Length too large: " + zzd + zzd2);
                    } else if (zzd <= zzajcVar.zzd()) {
                        if (zzajcVar instanceof zzajc) {
                            byte[] bArr = this.zza;
                            byte[] bArr2 = zzajcVar.zza;
                            zzajcVar.zzc();
                            int i = 0;
                            int i2 = 0;
                            while (i < zzd) {
                                if (bArr[i] != bArr2[i2]) {
                                    return false;
                                }
                                i++;
                                i2++;
                            }
                            return true;
                        }
                        return zzajcVar.zzg(0, zzd).equals(zzg(0, zzd));
                    } else {
                        int zzd3 = zzajcVar.zzd();
                        throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzd3);
                    }
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public byte zza(int i) {
        return this.zza[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    protected void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    protected final int zzf(int i, int i2, int i3) {
        return zzakq.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final zzajf zzg(int i, int i2) {
        int zzl = zzl(0, i2, zzd());
        return zzl == 0 ? zzajf.zzb : new zzaiz(this.zza, 0, zzl);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final zzajl zzh() {
        return zzajl.zzH(this.zza, 0, zzd(), true);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    protected final String zzi(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final void zzj(zzaiv zzaivVar) throws IOException {
        zzaivVar.zza(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajf
    public final boolean zzk() {
        return zzank.zzf(this.zza, 0, zzd());
    }
}
