package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public class zzew extends zzev {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzew(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.recaptcha.internal.zzez
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzez) && zzd() == ((zzez) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzew) {
                zzew zzewVar = (zzew) obj;
                int zzl = zzl();
                int zzl2 = zzewVar.zzl();
                if (zzl == 0 || zzl2 == 0 || zzl == zzl2) {
                    int zzd = zzd();
                    if (zzd > zzewVar.zzd()) {
                        int zzd2 = zzd();
                        throw new IllegalArgumentException("Length too large: " + zzd + zzd2);
                    } else if (zzd > zzewVar.zzd()) {
                        int zzd3 = zzewVar.zzd();
                        throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzd3);
                    } else if (zzewVar instanceof zzew) {
                        byte[] bArr = this.zza;
                        byte[] bArr2 = zzewVar.zza;
                        zzewVar.zzc();
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
                    } else {
                        return zzewVar.zzg(0, zzd).equals(zzg(0, zzd));
                    }
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzez
    public byte zza(int i) {
        return this.zza[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzez
    public byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzez
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.recaptcha.internal.zzez
    protected void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.recaptcha.internal.zzez
    protected final int zzf(int i, int i2, int i3) {
        return zzgw.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.recaptcha.internal.zzez
    public final zzez zzg(int i, int i2) {
        int zzk = zzk(0, i2, zzd());
        return zzk == 0 ? zzez.zzb : new zzet(this.zza, 0, zzk);
    }

    @Override // com.google.android.recaptcha.internal.zzez
    protected final String zzh(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzez
    public final void zzi(zzep zzepVar) throws IOException {
        ((zzfh) zzepVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.recaptcha.internal.zzez
    public final boolean zzj() {
        return zzju.zzf(this.zza, 0, zzd());
    }
}
