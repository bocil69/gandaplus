package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajq  reason: invalid package */
/* loaded from: classes.dex */
public final class zzajq extends zzajn {
    private final OutputStream zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzajq(OutputStream outputStream, int i) {
        super(i);
        this.zzg = outputStream;
    }

    private final void zzG() throws IOException {
        this.zzg.write(this.zza, 0, this.zzc);
        this.zzc = 0;
    }

    private final void zzH(int i) throws IOException {
        if (this.zzb - this.zzc < i) {
            zzG();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzI() throws IOException {
        if (this.zzc > 0) {
            zzG();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzJ(byte b) throws IOException {
        if (this.zzc == this.zzb) {
            zzG();
        }
        zzc(b);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzK(int i, boolean z) throws IOException {
        zzH(11);
        zzf(i << 3);
        zzc(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzL(int i, zzajf zzajfVar) throws IOException {
        zzs((i << 3) | 2);
        zzs(zzajfVar.zzd());
        zzajfVar.zzj(this);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs, com.google.android.gms.internal.p000firebaseauthapi.zzaiv
    public final void zza(byte[] bArr, int i, int i2) throws IOException {
        zzp(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzh(int i, int i2) throws IOException {
        zzH(14);
        zzf((i << 3) | 5);
        zzd(i2);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzi(int i) throws IOException {
        zzH(4);
        zzd(i);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzj(int i, long j) throws IOException {
        zzH(18);
        zzf((i << 3) | 1);
        zze(j);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzk(long j) throws IOException {
        zzH(8);
        zze(j);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzl(int i, int i2) throws IOException {
        zzH(20);
        zzf(i << 3);
        if (i2 >= 0) {
            zzf(i2);
        } else {
            zzg(i2);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzm(int i) throws IOException {
        if (i >= 0) {
            zzs(i);
        } else {
            zzu(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzn(int i, zzalp zzalpVar, zzamb zzambVar) throws IOException {
        zzs((i << 3) | 2);
        zzs(((zzaip) zzalpVar).zzn(zzambVar));
        zzambVar.zzm(zzalpVar, this.zze);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzo(int i, String str) throws IOException {
        zzs((i << 3) | 2);
        zzv(str);
    }

    public final void zzp(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.zzb;
        int i4 = this.zzc;
        int i5 = i3 - i4;
        if (i5 >= i2) {
            System.arraycopy(bArr, 0, this.zza, i4, i2);
            this.zzc += i2;
            this.zzd += i2;
            return;
        }
        System.arraycopy(bArr, 0, this.zza, i4, i5);
        this.zzc = this.zzb;
        this.zzd += i5;
        zzG();
        int i6 = i2 - i5;
        if (i6 <= this.zzb) {
            System.arraycopy(bArr, i5, this.zza, 0, i6);
            this.zzc = i6;
        } else {
            this.zzg.write(bArr, i5, i6);
        }
        this.zzd += i6;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzq(int i, int i2) throws IOException {
        zzs((i << 3) | i2);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzr(int i, int i2) throws IOException {
        zzH(20);
        zzf(i << 3);
        zzf(i2);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzs(int i) throws IOException {
        zzH(5);
        zzf(i);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzt(int i, long j) throws IOException {
        zzH(20);
        zzf(i << 3);
        zzg(j);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzajs
    public final void zzu(long j) throws IOException {
        zzH(10);
        zzg(j);
    }

    public final void zzv(String str) throws IOException {
        int zzc;
        try {
            int length = str.length() * 3;
            int zzA = zzA(length);
            int i = zzA + length;
            int i2 = this.zzb;
            if (i <= i2) {
                if (i > i2 - this.zzc) {
                    zzG();
                }
                int zzA2 = zzA(str.length());
                int i3 = this.zzc;
                try {
                    if (zzA2 == zzA) {
                        int i4 = i3 + zzA2;
                        this.zzc = i4;
                        int zzb = zzank.zzb(str, this.zza, i4, this.zzb - i4);
                        this.zzc = i3;
                        zzc = (zzb - i3) - zzA2;
                        zzf(zzc);
                        this.zzc = zzb;
                    } else {
                        zzc = zzank.zzc(str);
                        zzf(zzc);
                        this.zzc = zzank.zzb(str, this.zza, this.zzc, zzc);
                    }
                    this.zzd += zzc;
                    return;
                } catch (zzanj e) {
                    this.zzd -= this.zzc - i3;
                    this.zzc = i3;
                    throw e;
                } catch (ArrayIndexOutOfBoundsException e2) {
                    throw new zzajp(e2);
                }
            }
            byte[] bArr = new byte[length];
            int zzb2 = zzank.zzb(str, bArr, 0, length);
            zzs(zzb2);
            zzp(bArr, 0, zzb2);
        } catch (zzanj e3) {
            zzE(str, e3);
        }
    }
}
