package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamd  reason: invalid package */
/* loaded from: classes.dex */
public final class zzamd {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzamv zzc;
    private static final zzamv zzd;

    static {
        Class<?> cls;
        Class<?> cls2;
        zzamv zzamvVar = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzamvVar = (zzamv) cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzamvVar;
        zzd = new zzamx();
    }

    public static void zzA(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzu(i, list, z);
    }

    public static void zzB(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzx(i, list, z);
    }

    public static void zzC(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzz(i, list, z);
    }

    public static void zzD(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzB(i, list, z);
    }

    public static void zzE(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzD(i, list, z);
    }

    public static void zzF(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzI(i, list, z);
    }

    public static void zzG(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzK(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakl) {
            zzakl zzaklVar = (zzakl) list;
            i = 0;
            while (i2 < size) {
                i += zzajs.zzx(zzaklVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzajs.zzx(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzajs.zzA(i << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzajs.zzA(i << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakl) {
            zzakl zzaklVar = (zzakl) list;
            i = 0;
            while (i2 < size) {
                i += zzajs.zzx(zzaklVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzajs.zzx(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzale) {
            zzale zzaleVar = (zzale) list;
            i = 0;
            while (i2 < size) {
                i += zzajs.zzB(zzaleVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzajs.zzB(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int i, Object obj, zzamb zzambVar) {
        int i2 = i << 3;
        if (obj instanceof zzakv) {
            int i3 = zzajs.zzf;
            int zza2 = ((zzakv) obj).zza();
            return zzajs.zzA(i2) + zzajs.zzA(zza2) + zza2;
        }
        return zzajs.zzA(i2) + zzajs.zzy((zzalp) obj, zzambVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakl) {
            zzakl zzaklVar = (zzakl) list;
            i = 0;
            while (i2 < size) {
                int zze = zzaklVar.zze(i2);
                i += zzajs.zzA((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i += zzajs.zzA((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzale) {
            zzale zzaleVar = (zzale) list;
            i = 0;
            while (i2 < size) {
                long zze = zzaleVar.zze(i2);
                i += zzajs.zzB((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i += zzajs.zzB((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakl) {
            zzakl zzaklVar = (zzakl) list;
            i = 0;
            while (i2 < size) {
                i += zzajs.zzA(zzaklVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzajs.zzA(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzale) {
            zzale zzaleVar = (zzale) list;
            i = 0;
            while (i2 < size) {
                i += zzajs.zzB(zzaleVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzajs.zzB(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzamv zzm() {
        return zzc;
    }

    public static zzamv zzn() {
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzo(Object obj, int i, List list, zzako zzakoVar, Object obj2, zzamv zzamvVar) {
        if (zzakoVar == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzakoVar.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj2 = zzp(obj, i, intValue, obj2, zzamvVar);
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
                if (!zzakoVar.zza()) {
                    obj2 = zzp(obj, i, intValue2, obj2, zzamvVar);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzp(Object obj, int i, int i2, Object obj2, zzamv zzamvVar) {
        if (obj2 == null) {
            obj2 = zzamvVar.zzc(obj);
        }
        zzamvVar.zzl(obj2, i, i2);
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzq(zzamv zzamvVar, Object obj, Object obj2) {
        zzamvVar.zzo(obj, zzamvVar.zze(zzamvVar.zzd(obj), zzamvVar.zzd(obj2)));
    }

    public static void zzr(Class cls) {
        Class cls2;
        if (!zzakk.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzs(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void zzt(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzc(i, list, z);
    }

    public static void zzu(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzg(i, list, z);
    }

    public static void zzv(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzj(i, list, z);
    }

    public static void zzw(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzl(i, list, z);
    }

    public static void zzx(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzn(i, list, z);
    }

    public static void zzy(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzp(i, list, z);
    }

    public static void zzz(int i, List list, zzajt zzajtVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzajtVar.zzs(i, list, z);
    }
}
