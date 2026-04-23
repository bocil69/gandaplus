package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmo {
    static final byte[][] zza = {new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-32, -21, 122, 124, 59, 65, -72, -82, 22, 86, -29, -6, -15, -97, -60, 106, -38, 9, -115, -21, -100, 50, -79, -3, -122, 98, 5, 22, 95, 73, -72, 0}, new byte[]{95, -100, -107, -68, -93, 80, -116, 36, -79, -48, -79, 85, -100, -125, -17, 91, 4, 68, 92, -60, 88, 28, -114, -122, -40, 34, 78, -35, -48, -97, 17, 87}, new byte[]{-20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}};

    public static void zza(long[] jArr, byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        int i = 32;
        if (bArr2.length != 32) {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
        byte[] copyOf = Arrays.copyOf(bArr2, 32);
        copyOf[31] = (byte) (copyOf[31] & Byte.MAX_VALUE);
        int i2 = 0;
        for (int i3 = 0; i3 < 7; i3++) {
            byte[][] bArr3 = zza;
            if (MessageDigest.isEqual(bArr3[i3], copyOf)) {
                throw new InvalidKeyException("Banned public key: ".concat(zzze.zza(bArr3[i3])));
            }
        }
        long[] zzk = zzmv.zzk(copyOf);
        long[] jArr2 = new long[19];
        long[] jArr3 = new long[19];
        jArr3[0] = 1;
        long[] jArr4 = new long[19];
        jArr4[0] = 1;
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        jArr7[0] = 1;
        long[] jArr8 = new long[19];
        long[] jArr9 = new long[19];
        jArr9[0] = 1;
        int i4 = 10;
        System.arraycopy(zzk, 0, jArr2, 0, 10);
        int i5 = 0;
        while (i5 < i) {
            int i6 = bArr[(32 - i5) - 1] & 255;
            while (i2 < 8) {
                int i7 = (i6 >> (7 - i2)) & 1;
                zzb(jArr4, jArr2, i7);
                zzb(jArr5, jArr3, i7);
                long[] copyOf2 = Arrays.copyOf(jArr4, i4);
                int i8 = i6;
                long[] jArr10 = new long[19];
                long[] jArr11 = new long[19];
                int i9 = i5;
                long[] jArr12 = new long[19];
                int i10 = i2;
                long[] jArr13 = new long[19];
                long[] jArr14 = new long[19];
                long[] jArr15 = jArr9;
                long[] jArr16 = new long[19];
                long[] jArr17 = new long[19];
                zzmv.zzi(jArr4, jArr4, jArr5);
                zzmv.zzh(jArr5, copyOf2, jArr5);
                long[] copyOf3 = Arrays.copyOf(jArr2, 10);
                zzmv.zzi(jArr2, jArr2, jArr3);
                zzmv.zzh(jArr3, copyOf3, jArr3);
                zzmv.zzb(jArr13, jArr2, jArr5);
                zzmv.zzb(jArr14, jArr4, jArr3);
                zzmv.zze(jArr13);
                zzmv.zzd(jArr13);
                zzmv.zze(jArr14);
                zzmv.zzd(jArr14);
                long[] jArr18 = jArr2;
                System.arraycopy(jArr13, 0, copyOf3, 0, 10);
                zzmv.zzi(jArr13, jArr13, jArr14);
                zzmv.zzh(jArr14, copyOf3, jArr14);
                zzmv.zzg(jArr17, jArr13);
                zzmv.zzg(jArr16, jArr14);
                zzmv.zzb(jArr14, jArr16, zzk);
                zzmv.zze(jArr14);
                zzmv.zzd(jArr14);
                System.arraycopy(jArr17, 0, jArr6, 0, 10);
                System.arraycopy(jArr14, 0, jArr7, 0, 10);
                zzmv.zzg(jArr11, jArr4);
                zzmv.zzg(jArr12, jArr5);
                zzmv.zzb(jArr8, jArr11, jArr12);
                zzmv.zze(jArr8);
                zzmv.zzd(jArr8);
                zzmv.zzh(jArr12, jArr11, jArr12);
                Arrays.fill(jArr10, 10, 18, 0L);
                zzmv.zzf(jArr10, jArr12, 121665L);
                zzmv.zzd(jArr10);
                zzmv.zzi(jArr10, jArr10, jArr11);
                zzmv.zzb(jArr15, jArr12, jArr10);
                zzmv.zze(jArr15);
                zzmv.zzd(jArr15);
                zzb(jArr8, jArr6, i7);
                zzb(jArr15, jArr7, i7);
                i2 = i10 + 1;
                jArr2 = jArr6;
                i6 = i8;
                i5 = i9;
                jArr6 = jArr18;
                i4 = 10;
                long[] jArr19 = jArr7;
                jArr7 = jArr3;
                jArr3 = jArr19;
                long[] jArr20 = jArr8;
                jArr8 = jArr4;
                jArr4 = jArr20;
                jArr9 = jArr5;
                jArr5 = jArr15;
            }
            i5++;
            i = 32;
            i2 = 0;
            i4 = 10;
        }
        long[] jArr21 = new long[10];
        long[] jArr22 = new long[10];
        long[] jArr23 = new long[10];
        long[] jArr24 = new long[10];
        long[] jArr25 = new long[10];
        long[] jArr26 = new long[10];
        long[] jArr27 = new long[10];
        long[] jArr28 = new long[10];
        long[] jArr29 = new long[10];
        long[] jArr30 = new long[10];
        long[] jArr31 = jArr2;
        long[] jArr32 = new long[10];
        zzmv.zzg(jArr22, jArr5);
        zzmv.zzg(jArr32, jArr22);
        zzmv.zzg(jArr30, jArr32);
        zzmv.zza(jArr23, jArr30, jArr5);
        zzmv.zza(jArr24, jArr23, jArr22);
        zzmv.zzg(jArr30, jArr24);
        zzmv.zza(jArr25, jArr30, jArr23);
        zzmv.zzg(jArr30, jArr25);
        zzmv.zzg(jArr32, jArr30);
        zzmv.zzg(jArr30, jArr32);
        zzmv.zzg(jArr32, jArr30);
        zzmv.zzg(jArr30, jArr32);
        zzmv.zza(jArr26, jArr30, jArr25);
        zzmv.zzg(jArr30, jArr26);
        zzmv.zzg(jArr32, jArr30);
        for (int i11 = 2; i11 < 10; i11 += 2) {
            zzmv.zzg(jArr30, jArr32);
            zzmv.zzg(jArr32, jArr30);
        }
        zzmv.zza(jArr27, jArr32, jArr26);
        zzmv.zzg(jArr30, jArr27);
        zzmv.zzg(jArr32, jArr30);
        for (int i12 = 2; i12 < 20; i12 += 2) {
            zzmv.zzg(jArr30, jArr32);
            zzmv.zzg(jArr32, jArr30);
        }
        zzmv.zza(jArr30, jArr32, jArr27);
        zzmv.zzg(jArr32, jArr30);
        zzmv.zzg(jArr30, jArr32);
        for (int i13 = 2; i13 < 10; i13 += 2) {
            zzmv.zzg(jArr32, jArr30);
            zzmv.zzg(jArr30, jArr32);
        }
        zzmv.zza(jArr28, jArr30, jArr26);
        zzmv.zzg(jArr30, jArr28);
        zzmv.zzg(jArr32, jArr30);
        for (int i14 = 2; i14 < 50; i14 += 2) {
            zzmv.zzg(jArr30, jArr32);
            zzmv.zzg(jArr32, jArr30);
        }
        zzmv.zza(jArr29, jArr32, jArr28);
        zzmv.zzg(jArr32, jArr29);
        zzmv.zzg(jArr30, jArr32);
        for (int i15 = 2; i15 < 100; i15 += 2) {
            zzmv.zzg(jArr32, jArr30);
            zzmv.zzg(jArr30, jArr32);
        }
        zzmv.zza(jArr32, jArr30, jArr29);
        zzmv.zzg(jArr30, jArr32);
        zzmv.zzg(jArr32, jArr30);
        for (int i16 = 2; i16 < 50; i16 += 2) {
            zzmv.zzg(jArr30, jArr32);
            zzmv.zzg(jArr32, jArr30);
        }
        zzmv.zza(jArr30, jArr32, jArr28);
        zzmv.zzg(jArr32, jArr30);
        zzmv.zzg(jArr30, jArr32);
        zzmv.zzg(jArr32, jArr30);
        zzmv.zzg(jArr30, jArr32);
        zzmv.zzg(jArr32, jArr30);
        zzmv.zza(jArr21, jArr32, jArr24);
        zzmv.zza(jArr, jArr4, jArr21);
        long[] jArr33 = new long[10];
        long[] jArr34 = new long[10];
        long[] jArr35 = new long[11];
        long[] jArr36 = new long[11];
        long[] jArr37 = new long[11];
        zzmv.zza(jArr33, zzk, jArr);
        zzmv.zzi(jArr34, zzk, jArr);
        long[] jArr38 = new long[10];
        jArr38[0] = 486662;
        zzmv.zzi(jArr36, jArr34, jArr38);
        zzmv.zza(jArr36, jArr36, jArr3);
        zzmv.zzi(jArr36, jArr36, jArr31);
        zzmv.zza(jArr36, jArr36, jArr33);
        zzmv.zza(jArr36, jArr36, jArr31);
        zzmv.zzf(jArr35, jArr36, 4L);
        zzmv.zzd(jArr35);
        zzmv.zza(jArr36, jArr33, jArr3);
        zzmv.zzh(jArr36, jArr36, jArr3);
        zzmv.zza(jArr37, jArr34, jArr31);
        zzmv.zzi(jArr36, jArr36, jArr37);
        zzmv.zzg(jArr36, jArr36);
        if (!MessageDigest.isEqual(zzmv.zzj(jArr35), zzmv.zzj(jArr36))) {
            throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: ".concat(zzze.zza(bArr2)));
        }
    }

    static void zzb(long[] jArr, long[] jArr2, int i) {
        for (int i2 = 0; i2 < 10; i2++) {
            int i3 = (int) jArr[i2];
            int i4 = (-i) & (((int) jArr2[i2]) ^ i3);
            jArr[i2] = i3 ^ i4;
            jArr2[i2] = i4 ^ ((int) jArr2[i2]);
        }
    }
}
