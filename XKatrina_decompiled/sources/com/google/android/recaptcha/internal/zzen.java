package com.google.android.recaptcha.internal;

import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzen {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i, zzem zzemVar) throws zzgy {
        int zzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza;
        if (i2 < 0) {
            throw zzgy.zzf();
        }
        if (i2 <= bArr.length - zzj) {
            if (i2 == 0) {
                zzemVar.zzc = zzez.zzb;
                return zzj;
            }
            zzemVar.zzc = zzez.zzm(bArr, zzj, i2);
            return zzj + i2;
        }
        throw zzgy.zzj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << 24) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(zzil zzilVar, byte[] bArr, int i, int i2, int i3, zzem zzemVar) throws IOException {
        Object zze = zzilVar.zze();
        int zzn = zzn(zze, zzilVar, bArr, i, i2, i3, zzemVar);
        zzilVar.zzf(zze);
        zzemVar.zzc = zze;
        return zzn;
    }

    static int zzd(zzil zzilVar, byte[] bArr, int i, int i2, zzem zzemVar) throws IOException {
        Object zze = zzilVar.zze();
        int zzo = zzo(zze, zzilVar, bArr, i, i2, zzemVar);
        zzilVar.zzf(zze);
        zzemVar.zzc = zze;
        return zzo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(zzil zzilVar, int i, byte[] bArr, int i2, int i3, zzgv zzgvVar, zzem zzemVar) throws IOException {
        int zzd = zzd(zzilVar, bArr, i2, i3, zzemVar);
        zzgvVar.add(zzemVar.zzc);
        while (zzd < i3) {
            int zzj = zzj(bArr, zzd, zzemVar);
            if (i != zzemVar.zza) {
                break;
            }
            zzd = zzd(zzilVar, bArr, zzj, i3, zzemVar);
            zzgvVar.add(zzemVar.zzc);
        }
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(byte[] bArr, int i, zzgv zzgvVar, zzem zzemVar) throws IOException {
        zzgp zzgpVar = (zzgp) zzgvVar;
        int zzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza + zzj;
        while (zzj < i2) {
            zzj = zzj(bArr, zzj, zzemVar);
            zzgpVar.zzg(zzemVar.zza);
        }
        if (zzj == i2) {
            return zzj;
        }
        throw zzgy.zzj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(byte[] bArr, int i, zzem zzemVar) throws zzgy {
        int zzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzemVar.zzc = "";
                return zzj;
            }
            zzemVar.zzc = new String(bArr, zzj, i2, zzgw.zzb);
            return zzj + i2;
        }
        throw zzgy.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(byte[] bArr, int i, zzem zzemVar) throws zzgy {
        int zzj = zzj(bArr, i, zzemVar);
        int i2 = zzemVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzemVar.zzc = "";
                return zzj;
            }
            zzemVar.zzc = zzju.zzd(bArr, zzj, i2);
            return zzj + i2;
        }
        throw zzgy.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int i, byte[] bArr, int i2, int i3, zzjg zzjgVar, zzem zzemVar) throws zzgy {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzm = zzm(bArr, i2, zzemVar);
                zzjgVar.zzj(i, Long.valueOf(zzemVar.zzb));
                return zzm;
            } else if (i4 == 1) {
                zzjgVar.zzj(i, Long.valueOf(zzq(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzj = zzj(bArr, i2, zzemVar);
                int i5 = zzemVar.zza;
                if (i5 < 0) {
                    throw zzgy.zzf();
                }
                if (i5 <= bArr.length - zzj) {
                    if (i5 == 0) {
                        zzjgVar.zzj(i, zzez.zzb);
                    } else {
                        zzjgVar.zzj(i, zzez.zzm(bArr, zzj, i5));
                    }
                    return zzj + i5;
                }
                throw zzgy.zzj();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzjgVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                    return i2 + 4;
                }
                throw zzgy.zzc();
            } else {
                int i6 = (i & (-8)) | 4;
                zzjg zzf = zzjg.zzf();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzj2 = zzj(bArr, i2, zzemVar);
                    int i8 = zzemVar.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzj2;
                        break;
                    }
                    int zzi = zzi(i7, bArr, zzj2, i3, zzf, zzemVar);
                    i7 = i8;
                    i2 = zzi;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzgy.zzg();
                }
                zzjgVar.zzj(i, zzf);
                return i2;
            }
        }
        throw zzgy.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(byte[] bArr, int i, zzem zzemVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzemVar.zza = b;
            return i2;
        }
        return zzk(b, bArr, i2, zzemVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int i, byte[] bArr, int i2, zzem zzemVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzemVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzemVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzemVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzemVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzemVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(int i, byte[] bArr, int i2, int i3, zzgv zzgvVar, zzem zzemVar) {
        zzgp zzgpVar = (zzgp) zzgvVar;
        int zzj = zzj(bArr, i2, zzemVar);
        zzgpVar.zzg(zzemVar.zza);
        while (zzj < i3) {
            int zzj2 = zzj(bArr, zzj, zzemVar);
            if (i != zzemVar.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzemVar);
            zzgpVar.zzg(zzemVar.zza);
        }
        return zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(byte[] bArr, int i, zzem zzemVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzemVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= (b2 & Byte.MAX_VALUE) << i4;
            i3 = i5;
            b = b2;
        }
        zzemVar.zzb = j2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(Object obj, zzil zzilVar, byte[] bArr, int i, int i2, int i3, zzem zzemVar) throws IOException {
        int zzc = ((zzib) zzilVar).zzc(obj, bArr, i, i2, i3, zzemVar);
        zzemVar.zzc = obj;
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(Object obj, zzil zzilVar, byte[] bArr, int i, int i2, zzem zzemVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzk(i4, bArr, i3, zzemVar);
            i4 = zzemVar.zza;
        }
        int i5 = i3;
        if (i4 < 0 || i4 > i2 - i5) {
            throw zzgy.zzj();
        }
        int i6 = i4 + i5;
        zzilVar.zzi(obj, bArr, i5, i6, zzemVar);
        zzemVar.zzc = obj;
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzq(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 7] & 255) << 56);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, byte[] bArr, int i2, int i3, zzem zzemVar) throws zzgy {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 5) {
                                return i2 + 4;
                            }
                            throw zzgy.zzc();
                        }
                        int i5 = (i & (-8)) | 4;
                        int i6 = 0;
                        while (i2 < i3) {
                            i2 = zzj(bArr, i2, zzemVar);
                            i6 = zzemVar.zza;
                            if (i6 == i5) {
                                break;
                            }
                            i2 = zzp(i6, bArr, i2, i3, zzemVar);
                        }
                        if (i2 > i3 || i6 != i5) {
                            throw zzgy.zzg();
                        }
                        return i2;
                    }
                    return zzj(bArr, i2, zzemVar) + zzemVar.zza;
                }
                return i2 + 8;
            }
            return zzm(bArr, i2, zzemVar);
        }
        throw zzgy.zzc();
    }
}
