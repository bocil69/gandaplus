package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzamw {
    private static final zzamw zza = new zzamw(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzamw() {
        this(0, new int[8], new Object[8], true);
    }

    private zzamw(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzamw zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzamw zze(zzamw zzamwVar, zzamw zzamwVar2) {
        int i = zzamwVar.zzb + zzamwVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzamwVar.zzc, i);
        System.arraycopy(zzamwVar2.zzc, 0, copyOf, zzamwVar.zzb, zzamwVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzamwVar.zzd, i);
        System.arraycopy(zzamwVar2.zzd, 0, copyOf2, zzamwVar.zzb, zzamwVar2.zzb);
        return new zzamw(i, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzamw zzf() {
        return new zzamw(0, new int[8], new Object[8], true);
    }

    private final void zzl(int i) {
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
        if (obj != null && (obj instanceof zzamw)) {
            zzamw zzamwVar = (zzamw) obj;
            int i = this.zzb;
            if (i == zzamwVar.zzb) {
                int[] iArr = this.zzc;
                int[] iArr2 = zzamwVar.zzc;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        Object[] objArr = this.zzd;
                        Object[] objArr2 = zzamwVar.zzd;
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
        int i6 = ((i2 * 31) + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int zzB;
        int zzA;
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
                        i = zzajs.zzA(i6 << 3) + 8;
                    } else if (i7 == 2) {
                        int i8 = i6 << 3;
                        int i9 = zzajs.zzf;
                        int zzd = ((zzajf) this.zzd[i4]).zzd();
                        i = zzajs.zzA(i8) + zzajs.zzA(zzd) + zzd;
                    } else if (i7 == 3) {
                        int i10 = i6 << 3;
                        int i11 = zzajs.zzf;
                        zzB = ((zzamw) this.zzd[i4]).zza();
                        int zzA2 = zzajs.zzA(i10);
                        zzA = zzA2 + zzA2;
                    } else if (i7 == 5) {
                        ((Integer) this.zzd[i4]).intValue();
                        i = zzajs.zzA(i6 << 3) + 4;
                    } else {
                        throw new IllegalStateException(zzaks.zza());
                    }
                    i3 += i;
                } else {
                    int i12 = i6 << 3;
                    zzB = zzajs.zzB(((Long) this.zzd[i4]).longValue());
                    zzA = zzajs.zzA(i12);
                }
                i = zzA + zzB;
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
                int i4 = zzajs.zzf;
                int zzd = ((zzajf) this.zzd[i3]).zzd();
                int zzA = zzajs.zzA(zzd) + zzd;
                int zzA2 = zzajs.zzA(16);
                int zzA3 = zzajs.zzA(this.zzc[i3] >>> 3);
                int zzA4 = zzajs.zzA(8);
                i2 += zzA4 + zzA4 + zzA2 + zzA3 + zzajs.zzA(24) + zzA;
            }
            this.zze = i2;
            return i2;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzamw zzd(zzamw zzamwVar) {
        if (zzamwVar.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzamwVar.zzb;
        zzl(i);
        System.arraycopy(zzamwVar.zzc, 0, this.zzc, this.zzb, zzamwVar.zzb);
        System.arraycopy(zzamwVar.zzd, 0, this.zzd, this.zzb, zzamwVar.zzb);
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
            zzalr.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(int i, Object obj) {
        zzg();
        zzl(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    public final void zzk(zzajt zzajtVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzajtVar.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzajtVar.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzajtVar.zzd(i4, (zzajf) obj);
                } else if (i3 == 3) {
                    zzajtVar.zzE(i4);
                    ((zzamw) obj).zzk(zzajtVar);
                    zzajtVar.zzh(i4);
                } else if (i3 == 5) {
                    zzajtVar.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzaks.zza());
                }
            }
        }
    }
}
