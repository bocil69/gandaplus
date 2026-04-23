package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzin {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzjf zzc;
    private static final zzjf zzd;
    private static final zzjf zze;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        zzc = zzZ(false);
        zzd = zzZ(true);
        zze = new zzjh();
    }

    public static zzjf zzA() {
        return zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzB(Object obj, int i, List list, zzgs zzgsVar, Object obj2, zzjf zzjfVar) {
        if (zzgsVar == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzgsVar.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj2 = zzC(obj, i, intValue, obj2, zzjfVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzgsVar.zza()) {
                    obj2 = zzC(obj, i, intValue2, obj2, zzjfVar);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzC(Object obj, int i, int i2, Object obj2, zzjf zzjfVar) {
        if (obj2 == null) {
            obj2 = zzjfVar.zzc(obj);
        }
        zzjfVar.zzl(obj2, i, i2);
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzD(zzga zzgaVar, Object obj, Object obj2) {
        zzge zzb2 = zzgaVar.zzb(obj2);
        if (zzb2.zza.isEmpty()) {
            return;
        }
        zzgaVar.zzc(obj).zzh(zzb2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzE(zzjf zzjfVar, Object obj, Object obj2) {
        zzjfVar.zzo(obj, zzjfVar.zze(zzjfVar.zzd(obj), zzjfVar.zzd(obj2)));
    }

    public static void zzF(Class cls) {
        Class cls2;
        if (!zzgo.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzG(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzc(i, list, z);
    }

    public static void zzH(int i, List list, zzjx zzjxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zze(i, list);
    }

    public static void zzI(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzg(i, list, z);
    }

    public static void zzJ(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzj(i, list, z);
    }

    public static void zzK(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzl(i, list, z);
    }

    public static void zzL(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzn(i, list, z);
    }

    public static void zzM(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzp(i, list, z);
    }

    public static void zzN(int i, List list, zzjx zzjxVar, zzil zzilVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzfl) zzjxVar).zzq(i, list.get(i2), zzilVar);
        }
    }

    public static void zzO(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzs(i, list, z);
    }

    public static void zzP(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzu(i, list, z);
    }

    public static void zzQ(int i, List list, zzjx zzjxVar, zzil zzilVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzfl) zzjxVar).zzv(i, list.get(i2), zzilVar);
        }
    }

    public static void zzR(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzy(i, list, z);
    }

    public static void zzS(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzA(i, list, z);
    }

    public static void zzT(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzC(i, list, z);
    }

    public static void zzU(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzE(i, list, z);
    }

    public static void zzV(int i, List list, zzjx zzjxVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzH(i, list);
    }

    public static void zzW(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzJ(i, list, z);
    }

    public static void zzX(int i, List list, zzjx zzjxVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzjxVar.zzL(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzY(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static zzjf zzZ(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzjf) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzfk.zzy(i << 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = size * zzfk.zzy(i << 3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            int zzd2 = ((zzez) list.get(i2)).zzd();
            zzy += zzfk.zzy(zzd2) + zzd2;
        }
        return zzy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzfk.zzy(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            i = 0;
            while (i2 < size) {
                i += zzfk.zzu(zzgpVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzfk.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzfk.zzy(i << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzfk.zzy(i << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int i, List list, zzil zzilVar) {
        int size = list.size();
        if (size != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i2 += zzfk.zzt(i, (zzhy) list.get(i3), zzilVar);
            }
            return i2;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzfk.zzy(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            i = 0;
            while (i2 < size) {
                i += zzfk.zzu(zzgpVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzfk.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzm(list) + (list.size() * zzfk.zzy(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhn) {
            zzhn zzhnVar = (zzhn) list;
            i = 0;
            while (i2 < size) {
                i += zzfk.zzz(zzhnVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzfk.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(int i, Object obj, zzil zzilVar) {
        if (obj instanceof zzhe) {
            int i2 = zzfk.zzb;
            int zza2 = ((zzhe) obj).zza();
            return zzfk.zzy(i << 3) + zzfk.zzy(zza2) + zza2;
        }
        return zzfk.zzy(i << 3) + zzfk.zzw((zzhy) obj, zzilVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int i, List list, zzil zzilVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = zzfk.zzy(i << 3) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzhe) {
                int zza2 = ((zzhe) obj).zza();
                zzy += zzfk.zzy(zza2) + zza2;
            } else {
                zzy += zzfk.zzw((zzhy) obj, zzilVar);
            }
        }
        return zzy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzq(list) + (size * zzfk.zzy(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            i = 0;
            while (i2 < size) {
                int zze2 = zzgpVar.zze(i2);
                i += zzfk.zzy((zze2 >> 31) ^ (zze2 + zze2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i += zzfk.zzy((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzfk.zzy(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhn) {
            zzhn zzhnVar = (zzhn) list;
            i = 0;
            while (i2 < size) {
                long zze2 = zzhnVar.zze(i2);
                i += zzfk.zzz((zze2 >> 63) ^ (zze2 + zze2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i += zzfk.zzz((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int i3 = zzfk.zzb;
        boolean z = list instanceof zzhg;
        int zzy = zzfk.zzy(i << 3) * size;
        if (z) {
            zzhg zzhgVar = (zzhg) list;
            while (i2 < size) {
                Object zzf = zzhgVar.zzf(i2);
                if (zzf instanceof zzez) {
                    int zzd2 = ((zzez) zzf).zzd();
                    zzy += zzfk.zzy(zzd2) + zzd2;
                } else {
                    zzy += zzfk.zzx((String) zzf);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzez) {
                    int zzd3 = ((zzez) obj).zzd();
                    zzy += zzfk.zzy(zzd3) + zzd3;
                } else {
                    zzy += zzfk.zzx((String) obj);
                }
                i2++;
            }
        }
        return zzy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzfk.zzy(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgp) {
            zzgp zzgpVar = (zzgp) list;
            i = 0;
            while (i2 < size) {
                i += zzfk.zzy(zzgpVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzfk.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzfk.zzy(i << 3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhn) {
            zzhn zzhnVar = (zzhn) list;
            i = 0;
            while (i2 < size) {
                i += zzfk.zzz(zzhnVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzfk.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzjf zzy() {
        return zzc;
    }

    public static zzjf zzz() {
        return zzd;
    }
}
