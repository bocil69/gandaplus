package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzib<T> implements zzil<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzjp.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzhy zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzhm zzn;
    private final zzjf zzo;
    private final zzga zzp;
    private final zzie zzq;
    private final zzht zzr;

    private zzib(int[] iArr, Object[] objArr, int i, int i2, zzhy zzhyVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzie zzieVar, zzhm zzhmVar, zzjf zzjfVar, zzga zzgaVar, zzht zzhtVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzhyVar instanceof zzgo;
        this.zzj = z;
        boolean z3 = false;
        if (zzgaVar != null && zzgaVar.zzj(zzhyVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzq = zzieVar;
        this.zzn = zzhmVar;
        this.zzo = zzjfVar;
        this.zzp = zzgaVar;
        this.zzg = zzhyVar;
        this.zzr = zzhtVar;
    }

    private static long zzA(Object obj, long j) {
        return ((Long) zzjp.zzf(obj, j)).longValue();
    }

    private final zzgs zzB(int i) {
        int i2 = i / 3;
        return (zzgs) this.zzd[i2 + i2 + 1];
    }

    private final zzil zzC(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzil zzilVar = (zzil) this.zzd[i3];
        if (zzilVar != null) {
            return zzilVar;
        }
        zzil zzb2 = zzih.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzD(Object obj, int i, Object obj2, zzjf zzjfVar, Object obj3) {
        int i2 = this.zzc[i];
        Object zzf = zzjp.zzf(obj, zzz(i) & 1048575);
        if (zzf == null || zzB(i) == null) {
            return obj2;
        }
        zzhs zzhsVar = (zzhs) zzf;
        zzhr zzhrVar = (zzhr) zzE(i);
        throw null;
    }

    private final Object zzE(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzF(Object obj, int i) {
        zzil zzC = zzC(i);
        int zzz = zzz(i) & 1048575;
        if (!zzT(obj, i)) {
            return zzC.zze();
        }
        Object object = zzb.getObject(obj, zzz);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzC.zze();
        if (object != null) {
            zzC.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzG(Object obj, int i, int i2) {
        zzil zzC = zzC(i2);
        if (!zzX(obj, i, i2)) {
            return zzC.zze();
        }
        Object object = zzb.getObject(obj, zzz(i2) & 1048575);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzC.zze();
        if (object != null) {
            zzC.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzH(Class cls, String str) {
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

    private static void zzI(Object obj) {
        if (!zzW(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzJ(Object obj, Object obj2, int i) {
        if (zzT(obj2, i)) {
            Unsafe unsafe = zzb;
            long zzz = zzz(i) & 1048575;
            Object object = unsafe.getObject(obj2, zzz);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzil zzC = zzC(i);
            if (!zzT(obj, i)) {
                if (!zzW(object)) {
                    unsafe.putObject(obj, zzz, object);
                } else {
                    Object zze = zzC.zze();
                    zzC.zzg(zze, object);
                    unsafe.putObject(obj, zzz, zze);
                }
                zzM(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzz);
            if (!zzW(object2)) {
                Object zze2 = zzC.zze();
                zzC.zzg(zze2, object2);
                unsafe.putObject(obj, zzz, zze2);
                object2 = zze2;
            }
            zzC.zzg(object2, object);
        }
    }

    private final void zzK(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzX(obj2, i2, i)) {
            Unsafe unsafe = zzb;
            long zzz = zzz(i) & 1048575;
            Object object = unsafe.getObject(obj2, zzz);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzil zzC = zzC(i);
            if (!zzX(obj, i2, i)) {
                if (!zzW(object)) {
                    unsafe.putObject(obj, zzz, object);
                } else {
                    Object zze = zzC.zze();
                    zzC.zzg(zze, object);
                    unsafe.putObject(obj, zzz, zze);
                }
                zzN(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzz);
            if (!zzW(object2)) {
                Object zze2 = zzC.zze();
                zzC.zzg(zze2, object2);
                unsafe.putObject(obj, zzz, zze2);
                object2 = zze2;
            }
            zzC.zzg(object2, object);
        }
    }

    private final void zzL(Object obj, int i, zzik zzikVar) throws IOException {
        if (zzS(i)) {
            zzjp.zzs(obj, i & 1048575, zzikVar.zzs());
        } else if (!this.zzi) {
            zzjp.zzs(obj, i & 1048575, zzikVar.zzp());
        } else {
            zzjp.zzs(obj, i & 1048575, zzikVar.zzr());
        }
    }

    private final void zzM(Object obj, int i) {
        int zzw = zzw(i);
        long j = 1048575 & zzw;
        if (j == 1048575) {
            return;
        }
        zzjp.zzq(obj, j, (1 << (zzw >>> 20)) | zzjp.zzc(obj, j));
    }

    private final void zzN(Object obj, int i, int i2) {
        zzjp.zzq(obj, zzw(i2) & 1048575, i);
    }

    private final void zzO(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzz(i) & 1048575, obj2);
        zzM(obj, i);
    }

    private final void zzP(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzz(i2) & 1048575, obj2);
        zzN(obj, i, i2);
    }

    private final void zzQ(zzjx zzjxVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        zzhr zzhrVar = (zzhr) zzE(i2);
        throw null;
    }

    private final boolean zzR(Object obj, Object obj2, int i) {
        return zzT(obj, i) == zzT(obj2, i);
    }

    private static boolean zzS(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzT(Object obj, int i) {
        int zzw = zzw(i);
        long j = zzw & 1048575;
        if (j != 1048575) {
            return (zzjp.zzc(obj, j) & (1 << (zzw >>> 20))) != 0;
        }
        int zzz = zzz(i);
        long j2 = zzz & 1048575;
        switch (zzy(zzz)) {
            case 0:
                return Double.doubleToRawLongBits(zzjp.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzjp.zzb(obj, j2)) != 0;
            case 2:
                return zzjp.zzd(obj, j2) != 0;
            case 3:
                return zzjp.zzd(obj, j2) != 0;
            case 4:
                return zzjp.zzc(obj, j2) != 0;
            case 5:
                return zzjp.zzd(obj, j2) != 0;
            case 6:
                return zzjp.zzc(obj, j2) != 0;
            case 7:
                return zzjp.zzw(obj, j2);
            case 8:
                Object zzf = zzjp.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzez) {
                    return !zzez.zzb.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzjp.zzf(obj, j2) != null;
            case 10:
                return !zzez.zzb.equals(zzjp.zzf(obj, j2));
            case 11:
                return zzjp.zzc(obj, j2) != 0;
            case 12:
                return zzjp.zzc(obj, j2) != 0;
            case 13:
                return zzjp.zzc(obj, j2) != 0;
            case 14:
                return zzjp.zzd(obj, j2) != 0;
            case 15:
                return zzjp.zzc(obj, j2) != 0;
            case 16:
                return zzjp.zzd(obj, j2) != 0;
            case 17:
                return zzjp.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzU(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzT(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzV(Object obj, int i, zzil zzilVar) {
        return zzilVar.zzl(zzjp.zzf(obj, i & 1048575));
    }

    private static boolean zzW(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzgo) {
            return ((zzgo) obj).zzF();
        }
        return true;
    }

    private final boolean zzX(Object obj, int i, int i2) {
        return zzjp.zzc(obj, (long) (zzw(i2) & 1048575)) == i;
    }

    private static boolean zzY(Object obj, long j) {
        return ((Boolean) zzjp.zzf(obj, j)).booleanValue();
    }

    private static final void zzZ(int i, Object obj, zzjx zzjxVar) throws IOException {
        if (obj instanceof String) {
            zzjxVar.zzG(i, (String) obj);
        } else {
            zzjxVar.zzd(i, (zzez) obj);
        }
    }

    static zzjg zzd(Object obj) {
        zzgo zzgoVar = (zzgo) obj;
        zzjg zzjgVar = zzgoVar.zzc;
        if (zzjgVar == zzjg.zzc()) {
            zzjg zzf = zzjg.zzf();
            zzgoVar.zzc = zzf;
            return zzf;
        }
        return zzjgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:124:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0287  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.recaptcha.internal.zzib zzm(java.lang.Class r31, com.google.android.recaptcha.internal.zzhv r32, com.google.android.recaptcha.internal.zzie r33, com.google.android.recaptcha.internal.zzhm r34, com.google.android.recaptcha.internal.zzjf r35, com.google.android.recaptcha.internal.zzga r36, com.google.android.recaptcha.internal.zzht r37) {
        /*
            Method dump skipped, instructions count: 988
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzm(java.lang.Class, com.google.android.recaptcha.internal.zzhv, com.google.android.recaptcha.internal.zzie, com.google.android.recaptcha.internal.zzhm, com.google.android.recaptcha.internal.zzjf, com.google.android.recaptcha.internal.zzga, com.google.android.recaptcha.internal.zzht):com.google.android.recaptcha.internal.zzib");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzjp.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzjp.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzp(Object obj) {
        int i;
        int zzy;
        int zzy2;
        int zzz;
        int zzy3;
        int zzy4;
        int zzy5;
        int zzy6;
        int zzt;
        int zzh;
        int zzy7;
        int zzy8;
        int i2;
        int zzy9;
        int zzy10;
        int zzy11;
        int zzy12;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        int i7 = 0;
        while (i4 < this.zzc.length) {
            int zzz2 = zzz(i4);
            int[] iArr = this.zzc;
            int i8 = iArr[i4];
            int zzy13 = zzy(zzz2);
            if (zzy13 <= 17) {
                int i9 = iArr[i4 + 2];
                int i10 = i9 & i3;
                int i11 = i9 >>> 20;
                if (i10 != i6) {
                    i7 = unsafe.getInt(obj, i10);
                    i6 = i10;
                }
                i = 1 << i11;
            } else {
                i = 0;
            }
            long j = zzz2 & i3;
            switch (zzy13) {
                case 0:
                    if ((i7 & i) != 0) {
                        zzy = zzfk.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if ((i7 & i) != 0) {
                        zzy2 = zzfk.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if ((i7 & i) != 0) {
                        zzz = zzfk.zzz(unsafe.getLong(obj, j));
                        zzy3 = zzfk.zzy(i8 << 3);
                        i5 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ((i7 & i) != 0) {
                        zzz = zzfk.zzz(unsafe.getLong(obj, j));
                        zzy3 = zzfk.zzy(i8 << 3);
                        i5 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if ((i7 & i) != 0) {
                        zzz = zzfk.zzu(unsafe.getInt(obj, j));
                        zzy3 = zzfk.zzy(i8 << 3);
                        i5 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i7 & i) != 0) {
                        zzy = zzfk.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if ((i7 & i) != 0) {
                        zzy2 = zzfk.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if ((i7 & i) != 0) {
                        zzy4 = zzfk.zzy(i8 << 3) + 1;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i7 & i) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzez) {
                            int i12 = zzfk.zzb;
                            int zzd = ((zzez) object).zzd();
                            zzy5 = zzfk.zzy(zzd) + zzd;
                            zzy6 = zzfk.zzy(i8 << 3);
                            zzy4 = zzy6 + zzy5;
                            i5 += zzy4;
                            break;
                        } else {
                            zzz = zzfk.zzx((String) object);
                            zzy3 = zzfk.zzy(i8 << 3);
                            i5 += zzy3 + zzz;
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if ((i7 & i) != 0) {
                        zzy4 = zzin.zzn(i8, unsafe.getObject(obj, j), zzC(i4));
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i7 & i) != 0) {
                        int i13 = zzfk.zzb;
                        int zzd2 = ((zzez) unsafe.getObject(obj, j)).zzd();
                        zzy5 = zzfk.zzy(zzd2) + zzd2;
                        zzy6 = zzfk.zzy(i8 << 3);
                        zzy4 = zzy6 + zzy5;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i7 & i) != 0) {
                        zzz = zzfk.zzy(unsafe.getInt(obj, j));
                        zzy3 = zzfk.zzy(i8 << 3);
                        i5 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i7 & i) != 0) {
                        zzz = zzfk.zzu(unsafe.getInt(obj, j));
                        zzy3 = zzfk.zzy(i8 << 3);
                        i5 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if ((i7 & i) != 0) {
                        zzy2 = zzfk.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if ((i7 & i) != 0) {
                        zzy = zzfk.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if ((i7 & i) != 0) {
                        int i14 = unsafe.getInt(obj, j);
                        zzy3 = zzfk.zzy(i8 << 3);
                        zzz = zzfk.zzy((i14 >> 31) ^ (i14 + i14));
                        i5 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i & i7) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        i5 += zzfk.zzy(i8 << 3) + zzfk.zzz((j2 >> 63) ^ (j2 + j2));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i7 & i) != 0) {
                        zzy4 = zzfk.zzt(i8, (zzhy) unsafe.getObject(obj, j), zzC(i4));
                        i5 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzy4 = zzin.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 19:
                    zzy4 = zzin.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 20:
                    zzy4 = zzin.zzl(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 21:
                    zzy4 = zzin.zzw(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 22:
                    zzy4 = zzin.zzj(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 23:
                    zzy4 = zzin.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 24:
                    zzy4 = zzin.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 25:
                    zzy4 = zzin.zza(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzy4;
                    break;
                case 26:
                    zzt = zzin.zzt(i8, (List) unsafe.getObject(obj, j));
                    i5 += zzt;
                    break;
                case 27:
                    zzt = zzin.zzo(i8, (List) unsafe.getObject(obj, j), zzC(i4));
                    i5 += zzt;
                    break;
                case 28:
                    zzt = zzin.zzb(i8, (List) unsafe.getObject(obj, j));
                    i5 += zzt;
                    break;
                case 29:
                    zzt = zzin.zzu(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 30:
                    zzt = zzin.zzc(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 31:
                    zzt = zzin.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 32:
                    zzt = zzin.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 33:
                    zzt = zzin.zzp(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 34:
                    zzt = zzin.zzr(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 35:
                    zzh = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 36:
                    zzh = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 37:
                    zzh = zzin.zzm((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 38:
                    zzh = zzin.zzx((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 39:
                    zzh = zzin.zzk((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 40:
                    zzh = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 41:
                    zzh = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 42:
                    int i15 = zzin.zza;
                    zzh = ((List) unsafe.getObject(obj, j)).size();
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 43:
                    zzh = zzin.zzv((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 44:
                    zzh = zzin.zzd((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 45:
                    zzh = zzin.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 46:
                    zzh = zzin.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 47:
                    zzh = zzin.zzq((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 48:
                    zzh = zzin.zzs((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzy7 = zzfk.zzy(zzh);
                        zzy8 = zzfk.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 49:
                    zzt = zzin.zzi(i8, (List) unsafe.getObject(obj, j), zzC(i4));
                    i5 += zzt;
                    break;
                case 50:
                    zzht.zza(i8, unsafe.getObject(obj, j), zzE(i4));
                    break;
                case 51:
                    if (zzX(obj, i8, i4)) {
                        zzy9 = zzfk.zzy(i8 << 3);
                        zzt = zzy9 + 8;
                        i5 += zzt;
                    }
                    break;
                case 52:
                    if (zzX(obj, i8, i4)) {
                        zzy10 = zzfk.zzy(i8 << 3);
                        zzt = zzy10 + 4;
                        i5 += zzt;
                    }
                    break;
                case 53:
                    if (zzX(obj, i8, i4)) {
                        zzh = zzfk.zzz(zzA(obj, j));
                        i2 = zzfk.zzy(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 54:
                    if (zzX(obj, i8, i4)) {
                        zzh = zzfk.zzz(zzA(obj, j));
                        i2 = zzfk.zzy(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 55:
                    if (zzX(obj, i8, i4)) {
                        zzh = zzfk.zzu(zzq(obj, j));
                        i2 = zzfk.zzy(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 56:
                    if (zzX(obj, i8, i4)) {
                        zzy9 = zzfk.zzy(i8 << 3);
                        zzt = zzy9 + 8;
                        i5 += zzt;
                    }
                    break;
                case 57:
                    if (zzX(obj, i8, i4)) {
                        zzy10 = zzfk.zzy(i8 << 3);
                        zzt = zzy10 + 4;
                        i5 += zzt;
                    }
                    break;
                case 58:
                    if (zzX(obj, i8, i4)) {
                        zzt = zzfk.zzy(i8 << 3) + 1;
                        i5 += zzt;
                    }
                    break;
                case 59:
                    if (zzX(obj, i8, i4)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzez) {
                            int i16 = zzfk.zzb;
                            int zzd3 = ((zzez) object2).zzd();
                            zzy11 = zzfk.zzy(zzd3) + zzd3;
                            zzy12 = zzfk.zzy(i8 << 3);
                            zzt = zzy12 + zzy11;
                            i5 += zzt;
                        } else {
                            zzh = zzfk.zzx((String) object2);
                            i2 = zzfk.zzy(i8 << 3);
                            i5 += i2 + zzh;
                        }
                    }
                    break;
                case 60:
                    if (zzX(obj, i8, i4)) {
                        zzt = zzin.zzn(i8, unsafe.getObject(obj, j), zzC(i4));
                        i5 += zzt;
                    }
                    break;
                case 61:
                    if (zzX(obj, i8, i4)) {
                        int i17 = zzfk.zzb;
                        int zzd4 = ((zzez) unsafe.getObject(obj, j)).zzd();
                        zzy11 = zzfk.zzy(zzd4) + zzd4;
                        zzy12 = zzfk.zzy(i8 << 3);
                        zzt = zzy12 + zzy11;
                        i5 += zzt;
                    }
                    break;
                case 62:
                    if (zzX(obj, i8, i4)) {
                        zzh = zzfk.zzy(zzq(obj, j));
                        i2 = zzfk.zzy(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 63:
                    if (zzX(obj, i8, i4)) {
                        zzh = zzfk.zzu(zzq(obj, j));
                        i2 = zzfk.zzy(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 64:
                    if (zzX(obj, i8, i4)) {
                        zzy10 = zzfk.zzy(i8 << 3);
                        zzt = zzy10 + 4;
                        i5 += zzt;
                    }
                    break;
                case 65:
                    if (zzX(obj, i8, i4)) {
                        zzy9 = zzfk.zzy(i8 << 3);
                        zzt = zzy9 + 8;
                        i5 += zzt;
                    }
                    break;
                case 66:
                    if (zzX(obj, i8, i4)) {
                        int zzq = zzq(obj, j);
                        i2 = zzfk.zzy(i8 << 3);
                        zzh = zzfk.zzy((zzq >> 31) ^ (zzq + zzq));
                        i5 += i2 + zzh;
                    }
                    break;
                case 67:
                    if (zzX(obj, i8, i4)) {
                        long zzA = zzA(obj, j);
                        i5 += zzfk.zzy(i8 << 3) + zzfk.zzz((zzA >> 63) ^ (zzA + zzA));
                    }
                    break;
                case 68:
                    if (zzX(obj, i8, i4)) {
                        zzt = zzfk.zzt(i8, (zzhy) unsafe.getObject(obj, j), zzC(i4));
                        i5 += zzt;
                    }
                    break;
            }
            i4 += 3;
            i3 = 1048575;
        }
        int i18 = 0;
        zzjf zzjfVar = this.zzo;
        int zza2 = i5 + zzjfVar.zza(zzjfVar.zzd(obj));
        if (this.zzh) {
            zzge zzb2 = this.zzp.zzb(obj);
            for (int i19 = 0; i19 < zzb2.zza.zzb(); i19++) {
                Map.Entry zzg = zzb2.zza.zzg(i19);
                i18 += zzge.zza((zzgd) zzg.getKey(), zzg.getValue());
            }
            for (Map.Entry entry : zzb2.zza.zzc()) {
                i18 += zzge.zza((zzgd) entry.getKey(), entry.getValue());
            }
            return zza2 + i18;
        }
        return zza2;
    }

    private static int zzq(Object obj, long j) {
        return ((Integer) zzjp.zzf(obj, j)).intValue();
    }

    private final int zzr(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzem zzemVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzE = zzE(i3);
        Object object = unsafe.getObject(obj, j);
        if (zzht.zzb(object)) {
            zzhs zzb2 = zzhs.zza().zzb();
            zzht.zzc(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzhr zzhrVar = (zzhr) zzE;
        throw null;
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzem zzemVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzen.zzq(bArr, i))));
                    int i9 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i9;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzen.zzb(bArr, i))));
                    int i10 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i10;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int zzm = zzen.zzm(bArr, i, zzemVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzemVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int zzj = zzen.zzj(bArr, i, zzemVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzemVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzen.zzq(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i11;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzen.zzb(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i12;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzen.zzm(bArr, i, zzemVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzemVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzen.zzj(bArr, i, zzemVar);
                    int i13 = zzemVar.zza;
                    if (i13 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & 536870912) == 0 || zzju.zzf(bArr, zzj2, zzj2 + i13)) {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i13, zzgw.zzb));
                        zzj2 += i13;
                    } else {
                        throw zzgy.zzd();
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object zzG = zzG(obj, i4, i8);
                    int zzo = zzen.zzo(zzG, zzC(i8), bArr, i, i2, zzemVar);
                    zzP(obj, i4, i8, zzG);
                    return zzo;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int zza2 = zzen.zza(bArr, i, zzemVar);
                    unsafe.putObject(obj, j, zzemVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int zzj3 = zzen.zzj(bArr, i, zzemVar);
                    int i14 = zzemVar.zza;
                    zzgs zzB = zzB(i8);
                    if (zzB == null || zzB.zza()) {
                        unsafe.putObject(obj, j, Integer.valueOf(i14));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzd(obj).zzj(i3, Long.valueOf(i14));
                    }
                    return zzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int zzj4 = zzen.zzj(bArr, i, zzemVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzff.zzF(zzemVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int zzm3 = zzen.zzm(bArr, i, zzemVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzff.zzG(zzemVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object zzG2 = zzG(obj, i4, i8);
                    int zzn = zzen.zzn(zzG2, zzC(i8), bArr, i, i2, (i3 & (-8)) | 4, zzemVar);
                    zzP(obj, i4, i8, zzG2);
                    return zzn;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01c6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:112:0x0211 -> B:113:0x0212). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0148 -> B:61:0x0149). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:92:0x01c3 -> B:93:0x01c4). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzt(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.recaptcha.internal.zzem r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1160
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzt(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.recaptcha.internal.zzem):int");
    }

    private final int zzu(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzx(i, 0);
    }

    private final int zzv(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzx(i, i2);
    }

    private final int zzw(int i) {
        return this.zzc[i + 2];
    }

    private final int zzx(int i, int i2) {
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

    private static int zzy(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzz(int i) {
        return this.zzc[i + 1];
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final int zza(Object obj) {
        int zzy;
        int zzy2;
        int zzz;
        int zzy3;
        int zzy4;
        int zzy5;
        int zzy6;
        int zzn;
        int zzy7;
        int zzz2;
        int zzy8;
        int zzy9;
        if (this.zzj) {
            Unsafe unsafe = zzb;
            int i = 0;
            for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
                int zzz3 = zzz(i2);
                int zzy10 = zzy(zzz3);
                int i3 = this.zzc[i2];
                int i4 = zzz3 & 1048575;
                if (zzy10 >= zzgf.DOUBLE_LIST_PACKED.zza() && zzy10 <= zzgf.SINT64_LIST_PACKED.zza()) {
                    int i5 = this.zzc[i2 + 2];
                }
                long j = i4;
                switch (zzy10) {
                    case 0:
                        if (zzT(obj, i2)) {
                            zzy = zzfk.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzT(obj, i2)) {
                            zzy2 = zzfk.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzT(obj, i2)) {
                            zzz = zzfk.zzz(zzjp.zzd(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzT(obj, i2)) {
                            zzz = zzfk.zzz(zzjp.zzd(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzT(obj, i2)) {
                            zzz = zzfk.zzu(zzjp.zzc(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzT(obj, i2)) {
                            zzy = zzfk.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzT(obj, i2)) {
                            zzy2 = zzfk.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzT(obj, i2)) {
                            zzy4 = zzfk.zzy(i3 << 3);
                            zzn = zzy4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzT(obj, i2)) {
                            Object zzf = zzjp.zzf(obj, j);
                            if (zzf instanceof zzez) {
                                int i6 = i3 << 3;
                                int i7 = zzfk.zzb;
                                int zzd = ((zzez) zzf).zzd();
                                zzy5 = zzfk.zzy(zzd) + zzd;
                                zzy6 = zzfk.zzy(i6);
                                zzn = zzy6 + zzy5;
                                i += zzn;
                                break;
                            } else {
                                zzz = zzfk.zzx((String) zzf);
                                zzy3 = zzfk.zzy(i3 << 3);
                                i += zzy3 + zzz;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zzT(obj, i2)) {
                            zzn = zzin.zzn(i3, zzjp.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzT(obj, i2)) {
                            int i8 = i3 << 3;
                            int i9 = zzfk.zzb;
                            int zzd2 = ((zzez) zzjp.zzf(obj, j)).zzd();
                            zzy5 = zzfk.zzy(zzd2) + zzd2;
                            zzy6 = zzfk.zzy(i8);
                            zzn = zzy6 + zzy5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzT(obj, i2)) {
                            zzz = zzfk.zzy(zzjp.zzc(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzT(obj, i2)) {
                            zzz = zzfk.zzu(zzjp.zzc(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzT(obj, i2)) {
                            zzy2 = zzfk.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzT(obj, i2)) {
                            zzy = zzfk.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzT(obj, i2)) {
                            int zzc = zzjp.zzc(obj, j);
                            zzy3 = zzfk.zzy(i3 << 3);
                            zzz = zzfk.zzy((zzc >> 31) ^ (zzc + zzc));
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzT(obj, i2)) {
                            long zzd3 = zzjp.zzd(obj, j);
                            zzy7 = zzfk.zzy(i3 << 3);
                            zzz2 = zzfk.zzz((zzd3 + zzd3) ^ (zzd3 >> 63));
                            zzn = zzy7 + zzz2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzT(obj, i2)) {
                            zzn = zzfk.zzt(i3, (zzhy) zzjp.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzn = zzin.zzg(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 19:
                        zzn = zzin.zze(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 20:
                        zzn = zzin.zzl(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 21:
                        zzn = zzin.zzw(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 22:
                        zzn = zzin.zzj(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 23:
                        zzn = zzin.zzg(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 24:
                        zzn = zzin.zze(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 25:
                        zzn = zzin.zza(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 26:
                        zzn = zzin.zzt(i3, (List) zzjp.zzf(obj, j));
                        i += zzn;
                        break;
                    case 27:
                        zzn = zzin.zzo(i3, (List) zzjp.zzf(obj, j), zzC(i2));
                        i += zzn;
                        break;
                    case 28:
                        zzn = zzin.zzb(i3, (List) zzjp.zzf(obj, j));
                        i += zzn;
                        break;
                    case 29:
                        zzn = zzin.zzu(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 30:
                        zzn = zzin.zzc(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 31:
                        zzn = zzin.zze(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 32:
                        zzn = zzin.zzg(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 33:
                        zzn = zzin.zzp(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 34:
                        zzn = zzin.zzr(i3, (List) zzjp.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 35:
                        zzz = zzin.zzh((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i10 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i10);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        zzz = zzin.zzf((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i11 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i11);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        zzz = zzin.zzm((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i12 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i12);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        zzz = zzin.zzx((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i13 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i13);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        zzz = zzin.zzk((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i14 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i14);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        zzz = zzin.zzh((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i15 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i15);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        zzz = zzin.zzf((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i16 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i16);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int i17 = zzin.zza;
                        zzz = ((List) unsafe.getObject(obj, j)).size();
                        if (zzz > 0) {
                            int i18 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i18);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        zzz = zzin.zzv((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i19 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i19);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        zzz = zzin.zzd((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i20 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i20);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        zzz = zzin.zzf((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i21 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i21);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        zzz = zzin.zzh((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i22 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i22);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        zzz = zzin.zzq((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i23 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i23);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        zzz = zzin.zzs((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i24 = i3 << 3;
                            zzy8 = zzfk.zzy(zzz);
                            zzy9 = zzfk.zzy(i24);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        zzn = zzin.zzi(i3, (List) zzjp.zzf(obj, j), zzC(i2));
                        i += zzn;
                        break;
                    case 50:
                        zzht.zza(i3, zzjp.zzf(obj, j), zzE(i2));
                        break;
                    case 51:
                        if (zzX(obj, i3, i2)) {
                            zzy = zzfk.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzX(obj, i3, i2)) {
                            zzy2 = zzfk.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzfk.zzz(zzA(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzfk.zzz(zzA(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzfk.zzu(zzq(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzX(obj, i3, i2)) {
                            zzy = zzfk.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzX(obj, i3, i2)) {
                            zzy2 = zzfk.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzX(obj, i3, i2)) {
                            zzy4 = zzfk.zzy(i3 << 3);
                            zzn = zzy4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzX(obj, i3, i2)) {
                            Object zzf2 = zzjp.zzf(obj, j);
                            if (zzf2 instanceof zzez) {
                                int i25 = i3 << 3;
                                int i26 = zzfk.zzb;
                                int zzd4 = ((zzez) zzf2).zzd();
                                zzy5 = zzfk.zzy(zzd4) + zzd4;
                                zzy6 = zzfk.zzy(i25);
                                zzn = zzy6 + zzy5;
                                i += zzn;
                                break;
                            } else {
                                zzz = zzfk.zzx((String) zzf2);
                                zzy3 = zzfk.zzy(i3 << 3);
                                i += zzy3 + zzz;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zzX(obj, i3, i2)) {
                            zzn = zzin.zzn(i3, zzjp.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzX(obj, i3, i2)) {
                            int i27 = i3 << 3;
                            int i28 = zzfk.zzb;
                            int zzd5 = ((zzez) zzjp.zzf(obj, j)).zzd();
                            zzy5 = zzfk.zzy(zzd5) + zzd5;
                            zzy6 = zzfk.zzy(i27);
                            zzn = zzy6 + zzy5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzfk.zzy(zzq(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzX(obj, i3, i2)) {
                            zzz = zzfk.zzu(zzq(obj, j));
                            zzy3 = zzfk.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzX(obj, i3, i2)) {
                            zzy2 = zzfk.zzy(i3 << 3);
                            zzn = zzy2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzX(obj, i3, i2)) {
                            zzy = zzfk.zzy(i3 << 3);
                            zzn = zzy + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzX(obj, i3, i2)) {
                            int zzq = zzq(obj, j);
                            zzy3 = zzfk.zzy(i3 << 3);
                            zzz = zzfk.zzy((zzq >> 31) ^ (zzq + zzq));
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzX(obj, i3, i2)) {
                            long zzA = zzA(obj, j);
                            zzy7 = zzfk.zzy(i3 << 3);
                            zzz2 = zzfk.zzz((zzA + zzA) ^ (zzA >> 63));
                            zzn = zzy7 + zzz2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzX(obj, i3, i2)) {
                            zzn = zzfk.zzt(i3, (zzhy) zzjp.zzf(obj, j), zzC(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzjf zzjfVar = this.zzo;
            return i + zzjfVar.zza(zzjfVar.zzd(obj));
        }
        return zzp(obj);
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final int zzb(Object obj) {
        int i;
        long doubleToLongBits;
        int i2;
        int floatToIntBits;
        int length = this.zzc.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int zzz = zzz(i4);
            int i5 = this.zzc[i4];
            long j = 1048575 & zzz;
            int i6 = 37;
            switch (zzy(zzz)) {
                case 0:
                    i = i3 * 53;
                    doubleToLongBits = Double.doubleToLongBits(zzjp.zza(obj, j));
                    byte[] bArr = zzgw.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 1:
                    i2 = i3 * 53;
                    floatToIntBits = Float.floatToIntBits(zzjp.zzb(obj, j));
                    i3 = i2 + floatToIntBits;
                    break;
                case 2:
                    i = i3 * 53;
                    doubleToLongBits = zzjp.zzd(obj, j);
                    byte[] bArr2 = zzgw.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 3:
                    i = i3 * 53;
                    doubleToLongBits = zzjp.zzd(obj, j);
                    byte[] bArr3 = zzgw.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 4:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 5:
                    i = i3 * 53;
                    doubleToLongBits = zzjp.zzd(obj, j);
                    byte[] bArr4 = zzgw.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 6:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 7:
                    i2 = i3 * 53;
                    floatToIntBits = zzgw.zza(zzjp.zzw(obj, j));
                    i3 = i2 + floatToIntBits;
                    break;
                case 8:
                    i2 = i3 * 53;
                    floatToIntBits = ((String) zzjp.zzf(obj, j)).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 9:
                    Object zzf = zzjp.zzf(obj, j);
                    if (zzf != null) {
                        i6 = zzf.hashCode();
                    }
                    i3 = (i3 * 53) + i6;
                    break;
                case 10:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzf(obj, j).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 11:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 12:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 13:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 14:
                    i = i3 * 53;
                    doubleToLongBits = zzjp.zzd(obj, j);
                    byte[] bArr5 = zzgw.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 15:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 16:
                    i = i3 * 53;
                    doubleToLongBits = zzjp.zzd(obj, j);
                    byte[] bArr6 = zzgw.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 17:
                    Object zzf2 = zzjp.zzf(obj, j);
                    if (zzf2 != null) {
                        i6 = zzf2.hashCode();
                    }
                    i3 = (i3 * 53) + i6;
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
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzf(obj, j).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 50:
                    i2 = i3 * 53;
                    floatToIntBits = zzjp.zzf(obj, j).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 51:
                    if (zzX(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = Double.doubleToLongBits(zzn(obj, j));
                        byte[] bArr7 = zzgw.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = Float.floatToIntBits(zzo(obj, j));
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzX(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr8 = zzgw.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzX(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr9 = zzgw.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzq(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzX(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr10 = zzgw.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzq(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzgw.zza(zzY(obj, j));
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = ((String) zzjp.zzf(obj, j)).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzjp.zzf(obj, j).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzjp.zzf(obj, j).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzq(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzq(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzq(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzX(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr11 = zzgw.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzq(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzX(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzA(obj, j);
                        byte[] bArr12 = zzgw.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzX(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzjp.zzf(obj, j).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i3 * 53) + this.zzo.zzd(obj).hashCode();
        return this.zzh ? (hashCode * 53) + this.zzp.zzb(obj).zza.hashCode() : hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x04ec, code lost:
        if (r0 == r1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x04ee, code lost:
        r31.putInt(r12, r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x04f4, code lost:
        r10 = r9.zzl;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x04f9, code lost:
        if (r10 >= r9.zzm) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x04fb, code lost:
        zzD(r34, r9.zzk[r10], null, r9.zzo, r34);
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x050e, code lost:
        if (r7 != 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0512, code lost:
        if (r6 != r37) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0519, code lost:
        throw com.google.android.recaptcha.internal.zzgy.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x051c, code lost:
        if (r6 > r37) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x051e, code lost:
        if (r8 != r7) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0520, code lost:
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0525, code lost:
        throw com.google.android.recaptcha.internal.zzgy.zzg();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzc(java.lang.Object r34, byte[] r35, int r36, int r37, int r38, com.google.android.recaptcha.internal.zzem r39) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzc(java.lang.Object, byte[], int, int, int, com.google.android.recaptcha.internal.zzem):int");
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final Object zze() {
        return ((zzgo) this.zzg).zzs();
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzf(Object obj) {
        if (zzW(obj)) {
            if (obj instanceof zzgo) {
                zzgo zzgoVar = (zzgo) obj;
                zzgoVar.zzD(Integer.MAX_VALUE);
                zzgoVar.zza = 0;
                zzgoVar.zzB();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzz = zzz(i);
                int i2 = 1048575 & zzz;
                int zzy = zzy(zzz);
                long j = i2;
                if (zzy != 9) {
                    if (zzy == 60 || zzy == 68) {
                        if (zzX(obj, this.zzc[i], i)) {
                            zzC(i).zzf(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzy) {
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
                                this.zzn.zzb(obj, j);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzhs) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (zzT(obj, i)) {
                    zzC(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzo.zzm(obj);
            if (this.zzh) {
                this.zzp.zzf(obj);
            }
        }
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final void zzg(Object obj, Object obj2) {
        zzI(obj);
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzz = zzz(i);
            int i2 = this.zzc[i];
            long j = 1048575 & zzz;
            switch (zzy(zzz)) {
                case 0:
                    if (zzT(obj2, i)) {
                        zzjp.zzo(obj, j, zzjp.zza(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzT(obj2, i)) {
                        zzjp.zzp(obj, j, zzjp.zzb(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzT(obj2, i)) {
                        zzjp.zzm(obj, j, zzjp.zzw(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzT(obj2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzJ(obj, obj2, i);
                    break;
                case 10:
                    if (zzT(obj2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzT(obj2, i)) {
                        zzjp.zzq(obj, j, zzjp.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzT(obj2, i)) {
                        zzjp.zzr(obj, j, zzjp.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzJ(obj, obj2, i);
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
                    this.zzn.zzc(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzin.zza;
                    zzjp.zzs(obj, j, zzht.zzc(zzjp.zzf(obj, j), zzjp.zzf(obj2, j)));
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
                    if (zzX(obj2, i2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzK(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzX(obj2, i2, i)) {
                        zzjp.zzs(obj, j, zzjp.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzK(obj, obj2, i);
                    break;
            }
        }
        zzin.zzE(this.zzo, obj, obj2);
        if (this.zzh) {
            zzin.zzD(this.zzp, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:179:0x064e A[LOOP:3: B:177:0x064a->B:179:0x064e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0662  */
    @Override // com.google.android.recaptcha.internal.zzil
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzh(java.lang.Object r18, com.google.android.recaptcha.internal.zzik r19, com.google.android.recaptcha.internal.zzfz r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1780
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzh(java.lang.Object, com.google.android.recaptcha.internal.zzik, com.google.android.recaptcha.internal.zzfz):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x02ed, code lost:
        if (r0 != r24) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02ef, code lost:
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r15;
        r1 = r23;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0300, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x032c, code lost:
        if (r0 != r14) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x034f, code lost:
        if (r0 != r14) goto L44;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    @Override // com.google.android.recaptcha.internal.zzil
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.recaptcha.internal.zzem r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 974
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzi(java.lang.Object, byte[], int, int, com.google.android.recaptcha.internal.zzem):void");
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final boolean zzk(Object obj, Object obj2) {
        boolean zzY;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzz = zzz(i);
            long j = zzz & 1048575;
            switch (zzy(zzz)) {
                case 0:
                    if (zzR(obj, obj2, i) && Double.doubleToLongBits(zzjp.zza(obj, j)) == Double.doubleToLongBits(zzjp.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzR(obj, obj2, i) && Float.floatToIntBits(zzjp.zzb(obj, j)) == Float.floatToIntBits(zzjp.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzR(obj, obj2, i) && zzjp.zzd(obj, j) == zzjp.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzR(obj, obj2, i) && zzjp.zzd(obj, j) == zzjp.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzR(obj, obj2, i) && zzjp.zzc(obj, j) == zzjp.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzR(obj, obj2, i) && zzjp.zzd(obj, j) == zzjp.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzR(obj, obj2, i) && zzjp.zzc(obj, j) == zzjp.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzR(obj, obj2, i) && zzjp.zzw(obj, j) == zzjp.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzR(obj, obj2, i) && zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzR(obj, obj2, i) && zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzR(obj, obj2, i) && zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzR(obj, obj2, i) && zzjp.zzc(obj, j) == zzjp.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzR(obj, obj2, i) && zzjp.zzc(obj, j) == zzjp.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzR(obj, obj2, i) && zzjp.zzc(obj, j) == zzjp.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzR(obj, obj2, i) && zzjp.zzd(obj, j) == zzjp.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzR(obj, obj2, i) && zzjp.zzc(obj, j) == zzjp.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzR(obj, obj2, i) && zzjp.zzd(obj, j) == zzjp.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzR(obj, obj2, i) && zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
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
                    zzY = zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j));
                    break;
                case 50:
                    zzY = zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j));
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
                    long zzw = zzw(i) & 1048575;
                    if (zzjp.zzc(obj, zzw) == zzjp.zzc(obj2, zzw) && zzin.zzY(zzjp.zzf(obj, j), zzjp.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzY) {
                return false;
            }
        }
        if (this.zzo.zzd(obj).equals(this.zzo.zzd(obj2))) {
            if (this.zzh) {
                return this.zzp.zzb(obj).equals(this.zzp.zzb(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzil
    public final boolean zzl(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzz = zzz(i6);
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
            if ((268435456 & zzz) != 0 && !zzU(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzy = zzy(zzz);
            if (zzy != 9 && zzy != 17) {
                if (zzy != 27) {
                    if (zzy == 60 || zzy == 68) {
                        if (zzX(obj, i7, i6) && !zzV(obj, zzz, zzC(i6))) {
                            return false;
                        }
                    } else if (zzy != 49) {
                        if (zzy == 50 && !((zzhs) zzjp.zzf(obj, zzz & 1048575)).isEmpty()) {
                            zzhr zzhrVar = (zzhr) zzE(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzjp.zzf(obj, zzz & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzil zzC = zzC(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzC.zzl(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzU(obj, i6, i, i2, i10) && !zzV(obj, zzz, zzC(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.zzh || this.zzp.zzb(obj).zzk();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x09e8  */
    @Override // com.google.android.recaptcha.internal.zzil
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzj(java.lang.Object r18, com.google.android.recaptcha.internal.zzjx r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2852
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzib.zzj(java.lang.Object, com.google.android.recaptcha.internal.zzjx):void");
    }
}
