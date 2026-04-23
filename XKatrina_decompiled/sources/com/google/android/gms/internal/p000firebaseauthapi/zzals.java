package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzals  reason: invalid package */
/* loaded from: classes.dex */
public final class zzals<T> implements zzamb<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzanf.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzalp zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzald zzm;
    private final zzamv zzn;
    private final zzajy zzo;
    private final zzalu zzp;
    private final zzalk zzq;

    private zzals(int[] iArr, Object[] objArr, int i, int i2, zzalp zzalpVar, int i3, boolean z, int[] iArr2, int i4, int i5, zzalu zzaluVar, zzald zzaldVar, zzamv zzamvVar, zzajy zzajyVar, zzalk zzalkVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzalpVar instanceof zzakk;
        boolean z2 = false;
        if (zzajyVar != null && zzajyVar.zzh(zzalpVar)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzj = iArr2;
        this.zzk = i4;
        this.zzl = i5;
        this.zzp = zzaluVar;
        this.zzm = zzaldVar;
        this.zzn = zzamvVar;
        this.zzo = zzajyVar;
        this.zzg = zzalpVar;
        this.zzq = zzalkVar;
    }

    private final Object zzA(Object obj, int i) {
        zzamb zzx = zzx(i);
        int zzu = zzu(i) & 1048575;
        if (!zzN(obj, i)) {
            return zzx.zze();
        }
        Object object = zzb.getObject(obj, zzu);
        if (zzQ(object)) {
            return object;
        }
        Object zze = zzx.zze();
        if (object != null) {
            zzx.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzB(Object obj, int i, int i2) {
        zzamb zzx = zzx(i2);
        if (!zzR(obj, i, i2)) {
            return zzx.zze();
        }
        Object object = zzb.getObject(obj, zzu(i2) & 1048575);
        if (zzQ(object)) {
            return object;
        }
        Object zze = zzx.zze();
        if (object != null) {
            zzx.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzD(Object obj) {
        if (!zzQ(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzE(Object obj, Object obj2, int i) {
        if (zzN(obj2, i)) {
            Unsafe unsafe = zzb;
            long zzu = zzu(i) & 1048575;
            Object object = unsafe.getObject(obj2, zzu);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzamb zzx = zzx(i);
            if (!zzN(obj, i)) {
                if (!zzQ(object)) {
                    unsafe.putObject(obj, zzu, object);
                } else {
                    Object zze = zzx.zze();
                    zzx.zzg(zze, object);
                    unsafe.putObject(obj, zzu, zze);
                }
                zzH(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzu);
            if (!zzQ(object2)) {
                Object zze2 = zzx.zze();
                zzx.zzg(zze2, object2);
                unsafe.putObject(obj, zzu, zze2);
                object2 = zze2;
            }
            zzx.zzg(object2, object);
        }
    }

    private final void zzF(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzR(obj2, i2, i)) {
            Unsafe unsafe = zzb;
            long zzu = zzu(i) & 1048575;
            Object object = unsafe.getObject(obj2, zzu);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzamb zzx = zzx(i);
            if (!zzR(obj, i2, i)) {
                if (!zzQ(object)) {
                    unsafe.putObject(obj, zzu, object);
                } else {
                    Object zze = zzx.zze();
                    zzx.zzg(zze, object);
                    unsafe.putObject(obj, zzu, zze);
                }
                zzI(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzu);
            if (!zzQ(object2)) {
                Object zze2 = zzx.zze();
                zzx.zzg(zze2, object2);
                unsafe.putObject(obj, zzu, zze2);
                object2 = zze2;
            }
            zzx.zzg(object2, object);
        }
    }

    private final void zzG(Object obj, int i, zzama zzamaVar) throws IOException {
        long j = i & 1048575;
        if (zzM(i)) {
            zzanf.zzs(obj, j, zzamaVar.zzs());
        } else if (!this.zzi) {
            zzanf.zzs(obj, j, zzamaVar.zzp());
        } else {
            zzanf.zzs(obj, j, zzamaVar.zzr());
        }
    }

    private final void zzH(Object obj, int i) {
        int zzr = zzr(i);
        long j = 1048575 & zzr;
        if (j == 1048575) {
            return;
        }
        zzanf.zzq(obj, j, (1 << (zzr >>> 20)) | zzanf.zzc(obj, j));
    }

    private final void zzI(Object obj, int i, int i2) {
        zzanf.zzq(obj, zzr(i2) & 1048575, i);
    }

    private final void zzJ(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzu(i) & 1048575, obj2);
        zzH(obj, i);
    }

    private final void zzK(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzu(i2) & 1048575, obj2);
        zzI(obj, i, i2);
    }

    private final boolean zzL(Object obj, Object obj2, int i) {
        return zzN(obj, i) == zzN(obj2, i);
    }

    private static boolean zzM(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzN(Object obj, int i) {
        int zzr = zzr(i);
        long j = zzr & 1048575;
        if (j != 1048575) {
            return (zzanf.zzc(obj, j) & (1 << (zzr >>> 20))) != 0;
        }
        int zzu = zzu(i);
        long j2 = zzu & 1048575;
        switch (zzt(zzu)) {
            case 0:
                return Double.doubleToRawLongBits(zzanf.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzanf.zzb(obj, j2)) != 0;
            case 2:
                return zzanf.zzd(obj, j2) != 0;
            case 3:
                return zzanf.zzd(obj, j2) != 0;
            case 4:
                return zzanf.zzc(obj, j2) != 0;
            case 5:
                return zzanf.zzd(obj, j2) != 0;
            case 6:
                return zzanf.zzc(obj, j2) != 0;
            case 7:
                return zzanf.zzw(obj, j2);
            case 8:
                Object zzf = zzanf.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzajf) {
                    return !zzajf.zzb.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzanf.zzf(obj, j2) != null;
            case 10:
                return !zzajf.zzb.equals(zzanf.zzf(obj, j2));
            case 11:
                return zzanf.zzc(obj, j2) != 0;
            case 12:
                return zzanf.zzc(obj, j2) != 0;
            case 13:
                return zzanf.zzc(obj, j2) != 0;
            case 14:
                return zzanf.zzd(obj, j2) != 0;
            case 15:
                return zzanf.zzc(obj, j2) != 0;
            case 16:
                return zzanf.zzd(obj, j2) != 0;
            case 17:
                return zzanf.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzO(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzN(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzP(Object obj, int i, zzamb zzambVar) {
        return zzambVar.zzk(zzanf.zzf(obj, i & 1048575));
    }

    private static boolean zzQ(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzakk) {
            return ((zzakk) obj).zzL();
        }
        return true;
    }

    private final boolean zzR(Object obj, int i, int i2) {
        return zzanf.zzc(obj, (long) (zzr(i2) & 1048575)) == i;
    }

    private static boolean zzS(Object obj, long j) {
        return ((Boolean) zzanf.zzf(obj, j)).booleanValue();
    }

    private static final void zzT(int i, Object obj, zzajt zzajtVar) throws IOException {
        if (obj instanceof String) {
            zzajtVar.zzF(i, (String) obj);
        } else {
            zzajtVar.zzd(i, (zzajf) obj);
        }
    }

    static zzamw zzd(Object obj) {
        zzakk zzakkVar = (zzakk) obj;
        zzamw zzamwVar = zzakkVar.zzc;
        if (zzamwVar == zzamw.zzc()) {
            zzamw zzf = zzamw.zzf();
            zzakkVar.zzc = zzf;
            return zzf;
        }
        return zzamwVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0281  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.internal.p000firebaseauthapi.zzals zzl(java.lang.Class r33, com.google.android.gms.internal.p000firebaseauthapi.zzalm r34, com.google.android.gms.internal.p000firebaseauthapi.zzalu r35, com.google.android.gms.internal.p000firebaseauthapi.zzald r36, com.google.android.gms.internal.p000firebaseauthapi.zzamv r37, com.google.android.gms.internal.p000firebaseauthapi.zzajy r38, com.google.android.gms.internal.p000firebaseauthapi.zzalk r39) {
        /*
            Method dump skipped, instructions count: 1029
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p000firebaseauthapi.zzals.zzl(java.lang.Class, com.google.android.gms.internal.firebase-auth-api.zzalm, com.google.android.gms.internal.firebase-auth-api.zzalu, com.google.android.gms.internal.firebase-auth-api.zzald, com.google.android.gms.internal.firebase-auth-api.zzamv, com.google.android.gms.internal.firebase-auth-api.zzajy, com.google.android.gms.internal.firebase-auth-api.zzalk):com.google.android.gms.internal.firebase-auth-api.zzals");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzanf.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzanf.zzf(obj, j)).floatValue();
    }

    private static int zzp(Object obj, long j) {
        return ((Integer) zzanf.zzf(obj, j)).intValue();
    }

    private final int zzq(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzs(i, 0);
    }

    private final int zzr(int i) {
        return this.zzc[i + 2];
    }

    private final int zzs(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzt(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzu(int i) {
        return this.zzc[i + 1];
    }

    private static long zzv(Object obj, long j) {
        return ((Long) zzanf.zzf(obj, j)).longValue();
    }

    private final zzako zzw(int i) {
        int i2 = i / 3;
        return (zzako) this.zzd[i2 + i2 + 1];
    }

    private final zzamb zzx(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzamb zzambVar = (zzamb) objArr[i3];
        if (zzambVar != null) {
            return zzambVar;
        }
        zzamb zzb2 = zzalx.zza().zzb((Class) objArr[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzy(Object obj, int i, Object obj2, zzamv zzamvVar, Object obj3) {
        int i2 = this.zzc[i];
        Object zzf = zzanf.zzf(obj, zzu(i) & 1048575);
        if (zzf == null || zzw(i) == null) {
            return obj2;
        }
        zzalj zzaljVar = (zzalj) zzf;
        zzali zzaliVar = (zzali) zzz(i);
        throw null;
    }

    private final Object zzz(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final int zza(Object obj) {
        int i;
        int i2;
        int i3;
        int zzA;
        int zzA2;
        int zzB;
        int zzA3;
        int zzA4;
        int zzA5;
        int zzA6;
        int zzh;
        int zzg;
        int size;
        int zzA7;
        int zzA8;
        int zzA9;
        int zze;
        int zzA10;
        int zzA11;
        int i4;
        Unsafe unsafe = zzb;
        int i5 = 1048575;
        boolean z = false;
        int i6 = 1048575;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i8 < this.zzc.length) {
            int zzu = zzu(i8);
            int zzt = zzt(zzu);
            int[] iArr = this.zzc;
            int i10 = iArr[i8];
            int i11 = iArr[i8 + 2];
            int i12 = i11 & i5;
            if (zzt <= 17) {
                if (i12 != i6) {
                    i7 = i12 == i5 ? 0 : unsafe.getInt(obj, i12);
                    i6 = i12;
                }
                i3 = 1 << (i11 >>> 20);
                i = i6;
                i2 = i7;
            } else {
                i = i6;
                i2 = i7;
                i3 = 0;
            }
            int i13 = zzu & i5;
            if (zzt >= zzakd.DOUBLE_LIST_PACKED.zza()) {
                zzakd.SINT64_LIST_PACKED.zza();
            }
            long j = i13;
            switch (zzt) {
                case 0:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzA = zzajs.zzA(i10 << 3);
                        zzh = zzA + 8;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzA2 = zzajs.zzA(i10 << 3);
                        zzh = zzA2 + 4;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzB = zzajs.zzB(unsafe.getLong(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzB = zzajs.zzB(unsafe.getLong(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzB = zzajs.zzx(unsafe.getInt(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzA = zzajs.zzA(i10 << 3);
                        zzh = zzA + 8;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzA2 = zzajs.zzA(i10 << 3);
                        zzh = zzA2 + 4;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzA4 = zzajs.zzA(i10 << 3);
                        zzh = zzA4 + 1;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzO(obj, i8, i, i2, i3)) {
                        break;
                    } else {
                        int i14 = i10 << 3;
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzajf) {
                            int i15 = zzajs.zzf;
                            int zzd = ((zzajf) object).zzd();
                            zzA5 = zzajs.zzA(zzd) + zzd;
                            zzA6 = zzajs.zzA(i14);
                            zzh = zzA6 + zzA5;
                            i9 += zzh;
                            break;
                        } else {
                            zzB = zzajs.zzz((String) object);
                            zzA3 = zzajs.zzA(i14);
                            zzh = zzA3 + zzB;
                            i9 += zzh;
                        }
                    }
                case 9:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzh = zzamd.zzh(i10, unsafe.getObject(obj, j), zzx(i8));
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzO(obj, i8, i, i2, i3)) {
                        int i16 = zzajs.zzf;
                        int zzd2 = ((zzajf) unsafe.getObject(obj, j)).zzd();
                        zzA5 = zzajs.zzA(zzd2) + zzd2;
                        zzA6 = zzajs.zzA(i10 << 3);
                        zzh = zzA6 + zzA5;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzB = zzajs.zzA(unsafe.getInt(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzB = zzajs.zzx(unsafe.getInt(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzA2 = zzajs.zzA(i10 << 3);
                        zzh = zzA2 + 4;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzA = zzajs.zzA(i10 << 3);
                        zzh = zzA + 8;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzO(obj, i8, i, i2, i3)) {
                        int i17 = unsafe.getInt(obj, j);
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzB = zzajs.zzA((i17 >> 31) ^ (i17 + i17));
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzO(obj, i8, i, i2, i3)) {
                        long j2 = unsafe.getLong(obj, j);
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzB = zzajs.zzB((j2 >> 63) ^ (j2 + j2));
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzO(obj, i8, i, i2, i3)) {
                        zzh = zzajs.zzw(i10, (zzalp) unsafe.getObject(obj, j), zzx(i8));
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzh = zzamd.zzd(i10, (List) unsafe.getObject(obj, j), z);
                    i9 += zzh;
                    break;
                case 19:
                    zzh = zzamd.zzb(i10, (List) unsafe.getObject(obj, j), z);
                    i9 += zzh;
                    break;
                case 20:
                    List list = (List) unsafe.getObject(obj, j);
                    int i18 = zzamd.zza;
                    if (list.size() != 0) {
                        zzg = zzamd.zzg(list) + (list.size() * zzajs.zzA(i10 << 3));
                        i9 += zzg;
                        break;
                    }
                    zzg = 0;
                    i9 += zzg;
                case 21:
                    List list2 = (List) unsafe.getObject(obj, j);
                    int i19 = zzamd.zza;
                    size = list2.size();
                    if (size != 0) {
                        zzA3 = zzamd.zzl(list2);
                        zzA7 = zzajs.zzA(i10 << 3);
                        zzB = size * zzA7;
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    }
                    zzh = 0;
                    i9 += zzh;
                case 22:
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i20 = zzamd.zza;
                    size = list3.size();
                    if (size != 0) {
                        zzA3 = zzamd.zzf(list3);
                        zzA7 = zzajs.zzA(i10 << 3);
                        zzB = size * zzA7;
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    }
                    zzh = 0;
                    i9 += zzh;
                case 23:
                    zzh = zzamd.zzd(i10, (List) unsafe.getObject(obj, j), z);
                    i9 += zzh;
                    break;
                case 24:
                    zzh = zzamd.zzb(i10, (List) unsafe.getObject(obj, j), z);
                    i9 += zzh;
                    break;
                case 25:
                    int i21 = zzamd.zza;
                    int size2 = ((List) unsafe.getObject(obj, j)).size();
                    if (size2 != 0) {
                        zzh = size2 * (zzajs.zzA(i10 << 3) + 1);
                        i9 += zzh;
                        break;
                    }
                    zzh = 0;
                    i9 += zzh;
                case 26:
                    List list4 = (List) unsafe.getObject(obj, j);
                    int i22 = zzamd.zza;
                    int size3 = list4.size();
                    if (size3 != 0) {
                        boolean z2 = list4 instanceof zzakx;
                        zzg = zzajs.zzA(i10 << 3) * size3;
                        if (z2) {
                            zzakx zzakxVar = (zzakx) list4;
                            for (int i23 = 0; i23 < size3; i23++) {
                                Object zzf = zzakxVar.zzf(i23);
                                if (zzf instanceof zzajf) {
                                    int zzd3 = ((zzajf) zzf).zzd();
                                    zzg += zzajs.zzA(zzd3) + zzd3;
                                } else {
                                    zzg += zzajs.zzz((String) zzf);
                                }
                            }
                        } else {
                            for (int i24 = 0; i24 < size3; i24++) {
                                Object obj2 = list4.get(i24);
                                if (obj2 instanceof zzajf) {
                                    int zzd4 = ((zzajf) obj2).zzd();
                                    zzg += zzajs.zzA(zzd4) + zzd4;
                                } else {
                                    zzg += zzajs.zzz((String) obj2);
                                }
                            }
                        }
                        i9 += zzg;
                        break;
                    }
                    zzg = 0;
                    i9 += zzg;
                case 27:
                    List list5 = (List) unsafe.getObject(obj, j);
                    zzamb zzx = zzx(i8);
                    int i25 = zzamd.zza;
                    int size4 = list5.size();
                    if (size4 == 0) {
                        zzA8 = 0;
                    } else {
                        zzA8 = zzajs.zzA(i10 << 3) * size4;
                        for (int i26 = 0; i26 < size4; i26++) {
                            Object obj3 = list5.get(i26);
                            if (obj3 instanceof zzakv) {
                                int zza2 = ((zzakv) obj3).zza();
                                zzA8 += zzajs.zzA(zza2) + zza2;
                            } else {
                                zzA8 += zzajs.zzy((zzalp) obj3, zzx);
                            }
                        }
                    }
                    i9 += zzA8;
                    break;
                case 28:
                    List list6 = (List) unsafe.getObject(obj, j);
                    int i27 = zzamd.zza;
                    int size5 = list6.size();
                    if (size5 == 0) {
                        zzA9 = 0;
                    } else {
                        zzA9 = size5 * zzajs.zzA(i10 << 3);
                        for (int i28 = 0; i28 < list6.size(); i28++) {
                            int zzd5 = ((zzajf) list6.get(i28)).zzd();
                            zzA9 += zzajs.zzA(zzd5) + zzd5;
                        }
                    }
                    i9 += zzA9;
                    break;
                case 29:
                    List list7 = (List) unsafe.getObject(obj, j);
                    int i29 = zzamd.zza;
                    size = list7.size();
                    if (size != 0) {
                        zzA3 = zzamd.zzk(list7);
                        zzA7 = zzajs.zzA(i10 << 3);
                        zzB = size * zzA7;
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    }
                    zzh = 0;
                    i9 += zzh;
                case 30:
                    List list8 = (List) unsafe.getObject(obj, j);
                    int i30 = zzamd.zza;
                    size = list8.size();
                    if (size != 0) {
                        zzA3 = zzamd.zza(list8);
                        zzA7 = zzajs.zzA(i10 << 3);
                        zzB = size * zzA7;
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    }
                    zzh = 0;
                    i9 += zzh;
                case 31:
                    zzh = zzamd.zzb(i10, (List) unsafe.getObject(obj, j), z);
                    i9 += zzh;
                    break;
                case 32:
                    zzh = zzamd.zzd(i10, (List) unsafe.getObject(obj, j), z);
                    i9 += zzh;
                    break;
                case 33:
                    List list9 = (List) unsafe.getObject(obj, j);
                    int i31 = zzamd.zza;
                    size = list9.size();
                    if (size != 0) {
                        zzA3 = zzamd.zzi(list9);
                        zzA7 = zzajs.zzA(i10 << 3);
                        zzB = size * zzA7;
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    }
                    zzh = 0;
                    i9 += zzh;
                case 34:
                    List list10 = (List) unsafe.getObject(obj, j);
                    int i32 = zzamd.zza;
                    size = list10.size();
                    if (size != 0) {
                        zzA3 = zzamd.zzj(list10);
                        zzA7 = zzajs.zzA(i10 << 3);
                        zzB = size * zzA7;
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    }
                    zzh = 0;
                    i9 += zzh;
                case 35:
                    zze = zzamd.zze((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zze = zzamd.zzc((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zze = zzamd.zzg((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zze = zzamd.zzl((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zze = zzamd.zzf((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zze = zzamd.zze((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zze = zzamd.zzc((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    int i33 = zzamd.zza;
                    zze = ((List) unsafe.getObject(obj, j)).size();
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zze = zzamd.zzk((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zze = zzamd.zza((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zze = zzamd.zzc((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zze = zzamd.zze((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zze = zzamd.zzi((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zze = zzamd.zzj((List) unsafe.getObject(obj, j));
                    if (zze > 0) {
                        zzA10 = zzajs.zzA(zze);
                        zzA11 = zzajs.zzA(i10 << 3);
                        zzA9 = zzA11 + zzA10 + zze;
                        i9 += zzA9;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    List list11 = (List) unsafe.getObject(obj, j);
                    zzamb zzx2 = zzx(i8);
                    int i34 = zzamd.zza;
                    int size6 = list11.size();
                    if (size6 == 0) {
                        i4 = 0;
                    } else {
                        i4 = 0;
                        for (int i35 = 0; i35 < size6; i35++) {
                            i4 += zzajs.zzw(i10, (zzalp) list11.get(i35), zzx2);
                        }
                    }
                    i9 += i4;
                    break;
                case 50:
                    zzalj zzaljVar = (zzalj) unsafe.getObject(obj, j);
                    zzali zzaliVar = (zzali) zzz(i8);
                    if (zzaljVar.isEmpty()) {
                        continue;
                    } else {
                        Iterator it = zzaljVar.entrySet().iterator();
                        if (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            entry.getKey();
                            entry.getValue();
                            throw null;
                        }
                        break;
                    }
                case 51:
                    if (zzR(obj, i10, i8)) {
                        zzA = zzajs.zzA(i10 << 3);
                        zzh = zzA + 8;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, i10, i8)) {
                        zzA2 = zzajs.zzA(i10 << 3);
                        zzh = zzA2 + 4;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, i10, i8)) {
                        zzB = zzajs.zzB(zzv(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, i10, i8)) {
                        zzB = zzajs.zzB(zzv(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, i10, i8)) {
                        zzB = zzajs.zzx(zzp(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, i10, i8)) {
                        zzA = zzajs.zzA(i10 << 3);
                        zzh = zzA + 8;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, i10, i8)) {
                        zzA2 = zzajs.zzA(i10 << 3);
                        zzh = zzA2 + 4;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, i10, i8)) {
                        zzA4 = zzajs.zzA(i10 << 3);
                        zzh = zzA4 + 1;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzR(obj, i10, i8)) {
                        break;
                    } else {
                        int i36 = i10 << 3;
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzajf) {
                            int i37 = zzajs.zzf;
                            int zzd6 = ((zzajf) object2).zzd();
                            zzA5 = zzajs.zzA(zzd6) + zzd6;
                            zzA6 = zzajs.zzA(i36);
                            zzh = zzA6 + zzA5;
                            i9 += zzh;
                            break;
                        } else {
                            zzB = zzajs.zzz((String) object2);
                            zzA3 = zzajs.zzA(i36);
                            zzh = zzA3 + zzB;
                            i9 += zzh;
                        }
                    }
                case 60:
                    if (zzR(obj, i10, i8)) {
                        zzh = zzamd.zzh(i10, unsafe.getObject(obj, j), zzx(i8));
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, i10, i8)) {
                        int i38 = zzajs.zzf;
                        int zzd7 = ((zzajf) unsafe.getObject(obj, j)).zzd();
                        zzA5 = zzajs.zzA(zzd7) + zzd7;
                        zzA6 = zzajs.zzA(i10 << 3);
                        zzh = zzA6 + zzA5;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, i10, i8)) {
                        zzB = zzajs.zzA(zzp(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, i10, i8)) {
                        zzB = zzajs.zzx(zzp(obj, j));
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, i10, i8)) {
                        zzA2 = zzajs.zzA(i10 << 3);
                        zzh = zzA2 + 4;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, i10, i8)) {
                        zzA = zzajs.zzA(i10 << 3);
                        zzh = zzA + 8;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, i10, i8)) {
                        int zzp = zzp(obj, j);
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzB = zzajs.zzA((zzp >> 31) ^ (zzp + zzp));
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, i10, i8)) {
                        long zzv = zzv(obj, j);
                        zzA3 = zzajs.zzA(i10 << 3);
                        zzB = zzajs.zzB((zzv >> 63) ^ (zzv + zzv));
                        zzh = zzA3 + zzB;
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, i10, i8)) {
                        zzh = zzajs.zzw(i10, (zzalp) unsafe.getObject(obj, j), zzx(i8));
                        i9 += zzh;
                        break;
                    } else {
                        break;
                    }
            }
            i8 += 3;
            i6 = i;
            i7 = i2;
            i5 = 1048575;
            z = false;
        }
        zzamv zzamvVar = this.zzn;
        int zza3 = i9 + zzamvVar.zza(zzamvVar.zzd(obj));
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return zza3;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final int zzb(Object obj) {
        int i;
        long doubleToLongBits;
        int i2;
        int floatToIntBits;
        int i3;
        int i4 = 0;
        for (int i5 = 0; i5 < this.zzc.length; i5 += 3) {
            int zzu = zzu(i5);
            int[] iArr = this.zzc;
            int i6 = 1048575 & zzu;
            int zzt = zzt(zzu);
            int i7 = iArr[i5];
            long j = i6;
            int i8 = 37;
            switch (zzt) {
                case 0:
                    i = i4 * 53;
                    doubleToLongBits = Double.doubleToLongBits(zzanf.zza(obj, j));
                    byte[] bArr = zzakq.zzd;
                    i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 1:
                    i2 = i4 * 53;
                    floatToIntBits = Float.floatToIntBits(zzanf.zzb(obj, j));
                    i4 = i2 + floatToIntBits;
                    break;
                case 2:
                    i = i4 * 53;
                    doubleToLongBits = zzanf.zzd(obj, j);
                    byte[] bArr2 = zzakq.zzd;
                    i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 3:
                    i = i4 * 53;
                    doubleToLongBits = zzanf.zzd(obj, j);
                    byte[] bArr3 = zzakq.zzd;
                    i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 4:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzc(obj, j);
                    i4 = i2 + floatToIntBits;
                    break;
                case 5:
                    i = i4 * 53;
                    doubleToLongBits = zzanf.zzd(obj, j);
                    byte[] bArr4 = zzakq.zzd;
                    i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 6:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzc(obj, j);
                    i4 = i2 + floatToIntBits;
                    break;
                case 7:
                    i2 = i4 * 53;
                    floatToIntBits = zzakq.zza(zzanf.zzw(obj, j));
                    i4 = i2 + floatToIntBits;
                    break;
                case 8:
                    i2 = i4 * 53;
                    floatToIntBits = ((String) zzanf.zzf(obj, j)).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 9:
                    i3 = i4 * 53;
                    Object zzf = zzanf.zzf(obj, j);
                    if (zzf != null) {
                        i8 = zzf.hashCode();
                    }
                    i4 = i3 + i8;
                    break;
                case 10:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzf(obj, j).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 11:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzc(obj, j);
                    i4 = i2 + floatToIntBits;
                    break;
                case 12:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzc(obj, j);
                    i4 = i2 + floatToIntBits;
                    break;
                case 13:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzc(obj, j);
                    i4 = i2 + floatToIntBits;
                    break;
                case 14:
                    i = i4 * 53;
                    doubleToLongBits = zzanf.zzd(obj, j);
                    byte[] bArr5 = zzakq.zzd;
                    i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 15:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzc(obj, j);
                    i4 = i2 + floatToIntBits;
                    break;
                case 16:
                    i = i4 * 53;
                    doubleToLongBits = zzanf.zzd(obj, j);
                    byte[] bArr6 = zzakq.zzd;
                    i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 17:
                    i3 = i4 * 53;
                    Object zzf2 = zzanf.zzf(obj, j);
                    if (zzf2 != null) {
                        i8 = zzf2.hashCode();
                    }
                    i4 = i3 + i8;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzf(obj, j).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 50:
                    i2 = i4 * 53;
                    floatToIntBits = zzanf.zzf(obj, j).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 51:
                    if (zzR(obj, i7, i5)) {
                        i = i4 * 53;
                        doubleToLongBits = Double.doubleToLongBits(zzn(obj, j));
                        byte[] bArr7 = zzakq.zzd;
                        i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = Float.floatToIntBits(zzo(obj, j));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, i7, i5)) {
                        i = i4 * 53;
                        doubleToLongBits = zzv(obj, j);
                        byte[] bArr8 = zzakq.zzd;
                        i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, i7, i5)) {
                        i = i4 * 53;
                        doubleToLongBits = zzv(obj, j);
                        byte[] bArr9 = zzakq.zzd;
                        i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzp(obj, j);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, i7, i5)) {
                        i = i4 * 53;
                        doubleToLongBits = zzv(obj, j);
                        byte[] bArr10 = zzakq.zzd;
                        i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzp(obj, j);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzakq.zza(zzS(obj, j));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = ((String) zzanf.zzf(obj, j)).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzanf.zzf(obj, j).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzanf.zzf(obj, j).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzp(obj, j);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzp(obj, j);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzp(obj, j);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, i7, i5)) {
                        i = i4 * 53;
                        doubleToLongBits = zzv(obj, j);
                        byte[] bArr11 = zzakq.zzd;
                        i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzp(obj, j);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, i7, i5)) {
                        i = i4 * 53;
                        doubleToLongBits = zzv(obj, j);
                        byte[] bArr12 = zzakq.zzd;
                        i4 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zzanf.zzf(obj, j).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i4 * 53) + this.zzn.zzd(obj).hashCode();
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x0b5e, code lost:
        if (r5 == r0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:486:0x0b60, code lost:
        r14.putInt(r7, r5, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:487:0x0b64, code lost:
        r11 = r12.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:489:0x0b69, code lost:
        if (r11 >= r12.zzl) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x0b6b, code lost:
        zzy(r34, r12.zzj[r11], null, r12.zzn, r34);
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:491:0x0b7e, code lost:
        if (r9 != 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:492:0x0b80, code lost:
        if (r6 != r10) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x0b87, code lost:
        throw com.google.android.gms.internal.p000firebaseauthapi.zzaks.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x0b88, code lost:
        if (r6 > r10) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x0b8a, code lost:
        if (r8 != r9) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x0b8c, code lost:
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:500:0x0b91, code lost:
        throw com.google.android.gms.internal.p000firebaseauthapi.zzaks.zzg();
     */
    /* JADX WARN: Removed duplicated region for block: B:192:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0564  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x05b4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:190:0x04c1 -> B:191:0x04c2). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:223:0x0561 -> B:224:0x0562). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:243:0x05b1 -> B:244:0x05b2). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzc(java.lang.Object r34, byte[] r35, int r36, int r37, int r38, com.google.android.gms.internal.p000firebaseauthapi.zzais r39) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 3106
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p000firebaseauthapi.zzals.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase-auth-api.zzais):int");
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final Object zze() {
        return ((zzakk) this.zzg).zzw();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzf(Object obj) {
        if (zzQ(obj)) {
            if (obj instanceof zzakk) {
                zzakk zzakkVar = (zzakk) obj;
                zzakkVar.zzI(Integer.MAX_VALUE);
                zzakkVar.zza = 0;
                zzakkVar.zzG();
            }
            int[] iArr = this.zzc;
            for (int i = 0; i < iArr.length; i += 3) {
                int zzu = zzu(i);
                int i2 = 1048575 & zzu;
                int zzt = zzt(zzu);
                long j = i2;
                if (zzt != 9) {
                    if (zzt == 60 || zzt == 68) {
                        if (zzR(obj, this.zzc[i], i)) {
                            zzx(i).zzf(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzt) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.zzm.zzb(obj, j);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzalj) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (zzN(obj, i)) {
                    zzx(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzn.zzm(obj);
            if (this.zzh) {
                this.zzo.zze(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzg(Object obj, Object obj2) {
        zzD(obj);
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzu = zzu(i);
            int i2 = 1048575 & zzu;
            int[] iArr = this.zzc;
            int zzt = zzt(zzu);
            int i3 = iArr[i];
            long j = i2;
            switch (zzt) {
                case 0:
                    if (zzN(obj2, i)) {
                        zzanf.zzo(obj, j, zzanf.zza(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzN(obj2, i)) {
                        zzanf.zzp(obj, j, zzanf.zzb(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzN(obj2, i)) {
                        zzanf.zzr(obj, j, zzanf.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzN(obj2, i)) {
                        zzanf.zzr(obj, j, zzanf.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzN(obj2, i)) {
                        zzanf.zzq(obj, j, zzanf.zzc(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzN(obj2, i)) {
                        zzanf.zzr(obj, j, zzanf.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzN(obj2, i)) {
                        zzanf.zzq(obj, j, zzanf.zzc(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzN(obj2, i)) {
                        zzanf.zzm(obj, j, zzanf.zzw(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzN(obj2, i)) {
                        zzanf.zzs(obj, j, zzanf.zzf(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzE(obj, obj2, i);
                    break;
                case 10:
                    if (zzN(obj2, i)) {
                        zzanf.zzs(obj, j, zzanf.zzf(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzN(obj2, i)) {
                        zzanf.zzq(obj, j, zzanf.zzc(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzN(obj2, i)) {
                        zzanf.zzq(obj, j, zzanf.zzc(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzN(obj2, i)) {
                        zzanf.zzq(obj, j, zzanf.zzc(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzN(obj2, i)) {
                        zzanf.zzr(obj, j, zzanf.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzN(obj2, i)) {
                        zzanf.zzq(obj, j, zzanf.zzc(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzN(obj2, i)) {
                        zzanf.zzr(obj, j, zzanf.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzE(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzc(obj, obj2, j);
                    break;
                case 50:
                    int i4 = zzamd.zza;
                    zzanf.zzs(obj, j, zzalk.zzb(zzanf.zzf(obj, j), zzanf.zzf(obj2, j)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzR(obj2, i3, i)) {
                        zzanf.zzs(obj, j, zzanf.zzf(obj2, j));
                        zzI(obj, i3, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzF(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzR(obj2, i3, i)) {
                        zzanf.zzs(obj, j, zzanf.zzf(obj2, j));
                        zzI(obj, i3, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzF(obj, obj2, i);
                    break;
            }
        }
        zzamd.zzq(this.zzn, obj, obj2);
        if (this.zzh) {
            this.zzo.zza(obj2);
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:169:0x061d A[Catch: all -> 0x0612, TryCatch #0 {all -> 0x0612, blocks: (B:153:0x05eb, B:167:0x0618, B:169:0x061d, B:170:0x0622), top: B:193:0x05eb }] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0655 A[LOOP:2: B:185:0x0651->B:187:0x0655, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0628 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzh(java.lang.Object r18, com.google.android.gms.internal.p000firebaseauthapi.zzama r19, com.google.android.gms.internal.p000firebaseauthapi.zzajx r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1788
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p000firebaseauthapi.zzals.zzh(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzama, com.google.android.gms.internal.firebase-auth-api.zzajx):void");
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzais zzaisVar) throws IOException {
        zzc(obj, bArr, i, i2, 0, zzaisVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final boolean zzj(Object obj, Object obj2) {
        boolean zzs;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzu = zzu(i);
            long j = zzu & 1048575;
            switch (zzt(zzu)) {
                case 0:
                    if (zzL(obj, obj2, i) && Double.doubleToLongBits(zzanf.zza(obj, j)) == Double.doubleToLongBits(zzanf.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzL(obj, obj2, i) && Float.floatToIntBits(zzanf.zzb(obj, j)) == Float.floatToIntBits(zzanf.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzL(obj, obj2, i) && zzanf.zzd(obj, j) == zzanf.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzL(obj, obj2, i) && zzanf.zzd(obj, j) == zzanf.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzL(obj, obj2, i) && zzanf.zzc(obj, j) == zzanf.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzL(obj, obj2, i) && zzanf.zzd(obj, j) == zzanf.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzL(obj, obj2, i) && zzanf.zzc(obj, j) == zzanf.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzL(obj, obj2, i) && zzanf.zzw(obj, j) == zzanf.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzL(obj, obj2, i) && zzamd.zzs(zzanf.zzf(obj, j), zzanf.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzL(obj, obj2, i) && zzamd.zzs(zzanf.zzf(obj, j), zzanf.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzL(obj, obj2, i) && zzamd.zzs(zzanf.zzf(obj, j), zzanf.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzL(obj, obj2, i) && zzanf.zzc(obj, j) == zzanf.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzL(obj, obj2, i) && zzanf.zzc(obj, j) == zzanf.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzL(obj, obj2, i) && zzanf.zzc(obj, j) == zzanf.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzL(obj, obj2, i) && zzanf.zzd(obj, j) == zzanf.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzL(obj, obj2, i) && zzanf.zzc(obj, j) == zzanf.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzL(obj, obj2, i) && zzanf.zzd(obj, j) == zzanf.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzL(obj, obj2, i) && zzamd.zzs(zzanf.zzf(obj, j), zzanf.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zzs = zzamd.zzs(zzanf.zzf(obj, j), zzanf.zzf(obj2, j));
                    break;
                case 50:
                    zzs = zzamd.zzs(zzanf.zzf(obj, j), zzanf.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzr = zzr(i) & 1048575;
                    if (zzanf.zzc(obj, zzr) == zzanf.zzc(obj2, zzr) && zzamd.zzs(zzanf.zzf(obj, j), zzanf.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzs) {
                return false;
            }
        }
        if (this.zzn.zzd(obj).equals(this.zzn.zzd(obj2))) {
            if (this.zzh) {
                this.zzo.zza(obj);
                this.zzo.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzk) {
            int[] iArr = this.zzj;
            int[] iArr2 = this.zzc;
            int i6 = iArr[i5];
            int i7 = iArr2[i6];
            int zzu = zzu(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzu) != 0 && !zzO(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzt = zzt(zzu);
            if (zzt != 9 && zzt != 17) {
                if (zzt != 27) {
                    if (zzt == 60 || zzt == 68) {
                        if (zzR(obj, i7, i6) && !zzP(obj, zzu, zzx(i6))) {
                            return false;
                        }
                    } else if (zzt != 49) {
                        if (zzt == 50 && !((zzalj) zzanf.zzf(obj, zzu & 1048575)).isEmpty()) {
                            zzali zzaliVar = (zzali) zzz(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzanf.zzf(obj, zzu & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzamb zzx = zzx(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzx.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzO(obj, i6, i, i2, i10) && !zzP(obj, zzu, zzx(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamb
    public final void zzm(Object obj, zzajt zzajtVar) throws IOException {
        int i;
        int i2;
        int i3;
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int[] iArr = this.zzc;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        while (i7 < iArr.length) {
            int zzu = zzu(i7);
            int[] iArr2 = this.zzc;
            int zzt = zzt(zzu);
            int i8 = iArr2[i7];
            if (zzt <= 17) {
                int i9 = iArr2[i7 + 2];
                int i10 = i9 & i4;
                if (i10 != i5) {
                    i6 = i10 == i4 ? 0 : unsafe.getInt(obj, i10);
                    i5 = i10;
                }
                i = i5;
                i2 = i6;
                i3 = 1 << (i9 >>> 20);
            } else {
                i = i5;
                i2 = i6;
                i3 = 0;
            }
            long j = zzu & i4;
            switch (zzt) {
                case 0:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzf(i8, zzanf.zza(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzo(i8, zzanf.zzb(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzt(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzJ(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzr(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzm(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzk(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzb(i8, zzanf.zzw(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzT(i8, unsafe.getObject(obj, j), zzajtVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzv(i8, unsafe.getObject(obj, j), zzx(i7));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzd(i8, (zzajf) unsafe.getObject(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzH(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzi(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzw(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzy(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzA(i8, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzC(i8, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzO(obj, i7, i, i2, i3)) {
                        zzajtVar.zzq(i8, unsafe.getObject(obj, j), zzx(i7));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzamd.zzu(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 19:
                    zzamd.zzy(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 20:
                    zzamd.zzA(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 21:
                    zzamd.zzG(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 22:
                    zzamd.zzz(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 23:
                    zzamd.zzx(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 24:
                    zzamd.zzw(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 25:
                    zzamd.zzt(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 26:
                    int i11 = this.zzc[i7];
                    List list = (List) unsafe.getObject(obj, j);
                    int i12 = zzamd.zza;
                    if (list != null && !list.isEmpty()) {
                        zzajtVar.zzG(i11, list);
                        break;
                    }
                    break;
                case 27:
                    int i13 = this.zzc[i7];
                    List list2 = (List) unsafe.getObject(obj, j);
                    zzamb zzx = zzx(i7);
                    int i14 = zzamd.zza;
                    if (list2 != null && !list2.isEmpty()) {
                        for (int i15 = 0; i15 < list2.size(); i15++) {
                            zzajtVar.zzv(i13, list2.get(i15), zzx);
                        }
                        break;
                    }
                    break;
                case 28:
                    int i16 = this.zzc[i7];
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i17 = zzamd.zza;
                    if (list3 != null && !list3.isEmpty()) {
                        zzajtVar.zze(i16, list3);
                        break;
                    }
                    break;
                case 29:
                    zzamd.zzF(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 30:
                    zzamd.zzv(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 31:
                    zzamd.zzB(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 32:
                    zzamd.zzC(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 33:
                    zzamd.zzD(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 34:
                    zzamd.zzE(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, false);
                    break;
                case 35:
                    zzamd.zzu(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 36:
                    zzamd.zzy(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 37:
                    zzamd.zzA(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 38:
                    zzamd.zzG(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 39:
                    zzamd.zzz(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 40:
                    zzamd.zzx(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 41:
                    zzamd.zzw(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 42:
                    zzamd.zzt(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 43:
                    zzamd.zzF(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 44:
                    zzamd.zzv(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 45:
                    zzamd.zzB(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 46:
                    zzamd.zzC(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 47:
                    zzamd.zzD(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 48:
                    zzamd.zzE(this.zzc[i7], (List) unsafe.getObject(obj, j), zzajtVar, true);
                    break;
                case 49:
                    int i18 = this.zzc[i7];
                    List list4 = (List) unsafe.getObject(obj, j);
                    zzamb zzx2 = zzx(i7);
                    int i19 = zzamd.zza;
                    if (list4 != null && !list4.isEmpty()) {
                        for (int i20 = 0; i20 < list4.size(); i20++) {
                            zzajtVar.zzq(i18, list4.get(i20), zzx2);
                        }
                        break;
                    }
                    break;
                case 50:
                    if (unsafe.getObject(obj, j) != null) {
                        zzali zzaliVar = (zzali) zzz(i7);
                        throw null;
                    }
                    break;
                case 51:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzf(i8, zzn(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzo(i8, zzo(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzt(i8, zzv(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzJ(i8, zzv(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzr(i8, zzp(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzm(i8, zzv(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzk(i8, zzp(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzb(i8, zzS(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzR(obj, i8, i7)) {
                        zzT(i8, unsafe.getObject(obj, j), zzajtVar);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzv(i8, unsafe.getObject(obj, j), zzx(i7));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzd(i8, (zzajf) unsafe.getObject(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzH(i8, zzp(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzi(i8, zzp(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzw(i8, zzp(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzy(i8, zzv(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzA(i8, zzp(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzC(i8, zzv(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, i8, i7)) {
                        zzajtVar.zzq(i8, unsafe.getObject(obj, j), zzx(i7));
                        break;
                    } else {
                        break;
                    }
            }
            i7 += 3;
            i5 = i;
            i6 = i2;
            i4 = 1048575;
        }
        zzamv zzamvVar = this.zzn;
        zzamvVar.zzr(zzamvVar.zzd(obj), zzajtVar);
    }
}
