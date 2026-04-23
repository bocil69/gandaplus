package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzait  reason: invalid package */
/* loaded from: classes.dex */
public final class zzait {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i, zzais zzaisVar) throws zzaks {
        int zzh = zzh(bArr, i, zzaisVar);
        int i2 = zzaisVar.zza;
        if (i2 < 0) {
            throw zzaks.zzf();
        }
        if (i2 <= bArr.length - zzh) {
            if (i2 == 0) {
                zzaisVar.zzc = zzajf.zzb;
                return zzh;
            }
            zzaisVar.zzc = zzajf.zzn(bArr, zzh, i2);
            return zzh + i2;
        }
        throw zzaks.zzj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << 24) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(zzamb zzambVar, byte[] bArr, int i, int i2, int i3, zzais zzaisVar) throws IOException {
        Object zze = zzambVar.zze();
        int zzl = zzl(zze, zzambVar, bArr, i, i2, i3, zzaisVar);
        zzambVar.zzf(zze);
        zzaisVar.zzc = zze;
        return zzl;
    }

    static int zzd(zzamb zzambVar, byte[] bArr, int i, int i2, zzais zzaisVar) throws IOException {
        Object zze = zzambVar.zze();
        int zzm = zzm(zze, zzambVar, bArr, i, i2, zzaisVar);
        zzambVar.zzf(zze);
        zzaisVar.zzc = zze;
        return zzm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(zzamb zzambVar, int i, byte[] bArr, int i2, int i3, zzakp zzakpVar, zzais zzaisVar) throws IOException {
        int zzd = zzd(zzambVar, bArr, i2, i3, zzaisVar);
        zzakpVar.add(zzaisVar.zzc);
        while (zzd < i3) {
            int zzh = zzh(bArr, zzd, zzaisVar);
            if (i != zzaisVar.zza) {
                break;
            }
            zzd = zzd(zzambVar, bArr, zzh, i3, zzaisVar);
            zzakpVar.add(zzaisVar.zzc);
        }
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(byte[] bArr, int i, zzakp zzakpVar, zzais zzaisVar) throws IOException {
        zzakl zzaklVar = (zzakl) zzakpVar;
        int zzh = zzh(bArr, i, zzaisVar);
        int i2 = zzaisVar.zza + zzh;
        while (zzh < i2) {
            zzh = zzh(bArr, zzh, zzaisVar);
            zzaklVar.zzf(zzaisVar.zza);
        }
        if (zzh == i2) {
            return zzh;
        }
        throw zzaks.zzj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(int i, byte[] bArr, int i2, int i3, zzamw zzamwVar, zzais zzaisVar) throws zzaks {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzk = zzk(bArr, i2, zzaisVar);
                zzamwVar.zzj(i, Long.valueOf(zzaisVar.zzb));
                return zzk;
            } else if (i4 == 1) {
                zzamwVar.zzj(i, Long.valueOf(zzn(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzh = zzh(bArr, i2, zzaisVar);
                int i5 = zzaisVar.zza;
                if (i5 < 0) {
                    throw zzaks.zzf();
                }
                if (i5 <= bArr.length - zzh) {
                    if (i5 == 0) {
                        zzamwVar.zzj(i, zzajf.zzb);
                    } else {
                        zzamwVar.zzj(i, zzajf.zzn(bArr, zzh, i5));
                    }
                    return zzh + i5;
                }
                throw zzaks.zzj();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzamwVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                    return i2 + 4;
                }
                throw zzaks.zzc();
            } else {
                int i6 = (i & (-8)) | 4;
                zzamw zzf = zzamw.zzf();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzh2 = zzh(bArr, i2, zzaisVar);
                    int i8 = zzaisVar.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzh2;
                        break;
                    }
                    int zzg = zzg(i7, bArr, zzh2, i3, zzf, zzaisVar);
                    i7 = i8;
                    i2 = zzg;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzaks.zzg();
                }
                zzamwVar.zzj(i, zzf);
                return i2;
            }
        }
        throw zzaks.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(byte[] bArr, int i, zzais zzaisVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzaisVar.zza = b;
            return i2;
        }
        return zzi(b, bArr, i2, zzaisVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int i, byte[] bArr, int i2, zzais zzaisVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzaisVar.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzaisVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzaisVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzaisVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzaisVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i, byte[] bArr, int i2, int i3, zzakp zzakpVar, zzais zzaisVar) {
        zzakl zzaklVar = (zzakl) zzakpVar;
        int zzh = zzh(bArr, i2, zzaisVar);
        zzaklVar.zzf(zzaisVar.zza);
        while (zzh < i3) {
            int zzh2 = zzh(bArr, zzh, zzaisVar);
            if (i != zzaisVar.zza) {
                break;
            }
            zzh = zzh(bArr, zzh2, zzaisVar);
            zzaklVar.zzf(zzaisVar.zza);
        }
        return zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(byte[] bArr, int i, zzais zzaisVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzaisVar.zzb = j;
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
        zzaisVar.zzb = j2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(Object obj, zzamb zzambVar, byte[] bArr, int i, int i2, int i3, zzais zzaisVar) throws IOException {
        int zzc = ((zzals) zzambVar).zzc(obj, bArr, i, i2, i3, zzaisVar);
        zzaisVar.zzc = obj;
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(Object obj, zzamb zzambVar, byte[] bArr, int i, int i2, zzais zzaisVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzi(i4, bArr, i3, zzaisVar);
            i4 = zzaisVar.zza;
        }
        int i5 = i3;
        if (i4 < 0 || i4 > i2 - i5) {
            throw zzaks.zzj();
        }
        int i6 = i4 + i5;
        zzambVar.zzi(obj, bArr, i5, i6, zzaisVar);
        zzaisVar.zzc = obj;
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzn(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 7] & 255) << 56);
    }
}
