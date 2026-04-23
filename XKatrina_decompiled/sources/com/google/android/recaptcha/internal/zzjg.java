package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Arrays;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzjg {
    private static final zzjg zza = new zzjg(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzjg() {
        this(0, new int[8], new Object[8], true);
    }

    private zzjg(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzjg zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjg zze(zzjg zzjgVar, zzjg zzjgVar2) {
        int i = zzjgVar.zzb + zzjgVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzjgVar.zzc, i);
        System.arraycopy(zzjgVar2.zzc, 0, copyOf, zzjgVar.zzb, zzjgVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzjgVar.zzd, i);
        System.arraycopy(zzjgVar2.zzd, 0, copyOf2, zzjgVar.zzb, zzjgVar2.zzb);
        return new zzjg(i, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjg zzf() {
        return new zzjg(0, new int[8], new Object[8], true);
    }

    private final void zzm(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzjg)) {
            zzjg zzjgVar = (zzjg) obj;
            int i = this.zzb;
            if (i == zzjgVar.zzb) {
                int[] iArr = this.zzc;
                int[] iArr2 = zzjgVar.zzc;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        Object[] objArr = this.zzd;
                        Object[] objArr2 = zzjgVar.zzd;
                        int i3 = this.zzb;
                        for (int i4 = 0; i4 < i3; i4++) {
                            if (objArr[i4].equals(objArr2[i4])) {
                            }
                        }
                        return true;
                    } else if (iArr[i2] != iArr2[i2]) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 * 31) + i4;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return (i6 * 31) + i3;
    }

    public final int zza() {
        int zzz;
        int zzy;
        int i;
        int i2 = this.zze;
        if (i2 == -1) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.zzb; i4++) {
                int i5 = this.zzc[i4];
                int i6 = i5 >>> 3;
                int i7 = i5 & 7;
                if (i7 != 0) {
                    if (i7 == 1) {
                        ((Long) this.zzd[i4]).longValue();
                        i = zzfk.zzy(i6 << 3) + 8;
                    } else if (i7 == 2) {
                        int i8 = zzfk.zzb;
                        int zzd = ((zzez) this.zzd[i4]).zzd();
                        i = zzfk.zzy(i6 << 3) + zzfk.zzy(zzd) + zzd;
                    } else if (i7 == 3) {
                        int i9 = i6 << 3;
                        int i10 = zzfk.zzb;
                        zzz = ((zzjg) this.zzd[i4]).zza();
                        int zzy2 = zzfk.zzy(i9);
                        zzy = zzy2 + zzy2;
                    } else if (i7 == 5) {
                        ((Integer) this.zzd[i4]).intValue();
                        i = zzfk.zzy(i6 << 3) + 4;
                    } else {
                        throw new IllegalStateException(zzgy.zza());
                    }
                    i3 += i;
                } else {
                    int i11 = i6 << 3;
                    zzz = zzfk.zzz(((Long) this.zzd[i4]).longValue());
                    zzy = zzfk.zzy(i11);
                }
                i = zzy + zzz;
                i3 += i;
            }
            this.zze = i3;
            return i3;
        }
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i == -1) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.zzb; i3++) {
                int i4 = zzfk.zzb;
                int zzd = ((zzez) this.zzd[i3]).zzd();
                int zzy = zzfk.zzy(zzd) + zzd;
                int zzy2 = zzfk.zzy(16);
                int zzy3 = zzfk.zzy(this.zzc[i3] >>> 3);
                int zzy4 = zzfk.zzy(8);
                i2 += zzy4 + zzy4 + zzy2 + zzy3 + zzfk.zzy(24) + zzy;
            }
            this.zze = i2;
            return i2;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzjg zzd(zzjg zzjgVar) {
        if (zzjgVar.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzjgVar.zzb;
        zzm(i);
        System.arraycopy(zzjgVar.zzc, 0, this.zzc, this.zzb, zzjgVar.zzb);
        System.arraycopy(zzjgVar.zzd, 0, this.zzd, this.zzb, zzjgVar.zzb);
        this.zzb = i;
        return this;
    }

    final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzia.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(int i, Object obj) {
        zzg();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzk(zzjx zzjxVar) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzjxVar.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzjx zzjxVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzjxVar.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzjxVar.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzjxVar.zzd(i4, (zzez) obj);
                } else if (i3 == 3) {
                    zzjxVar.zzF(i4);
                    ((zzjg) obj).zzl(zzjxVar);
                    zzjxVar.zzh(i4);
                } else if (i3 == 5) {
                    zzjxVar.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzgy.zza());
                }
            }
        }
    }
}
