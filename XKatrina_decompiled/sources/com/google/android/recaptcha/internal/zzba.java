package com.google.android.recaptcha.internal;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzba implements zzas, zzbi {
    public static final zzat zza = new zzat(null);
    private final zzbc zzb;
    private final CoroutineScope zzc;
    private final zzbn zzd;
    private final Map zze;
    private final Map zzf;

    public zzba(zzbc zzbcVar, CoroutineScope coroutineScope, Context context) {
        this.zzb = zzbcVar;
        this.zzc = coroutineScope;
        zzbn zzbnVar = new zzbn();
        this.zzd = zzbnVar;
        this.zze = zzbnVar.zzb().zzc();
        this.zzf = MapsKt.mapOf(new Pair[]{TuplesKt.to(39, zzbt.zza), TuplesKt.to(34, zzcg.zza), TuplesKt.to(35, zzco.zza), TuplesKt.to(25, zzbz.zza), TuplesKt.to(37, zzcn.zza), TuplesKt.to(21, zzbo.zza), TuplesKt.to(22, zzcm.zza), TuplesKt.to(23, zzch.zza), TuplesKt.to(24, zzbw.zza), TuplesKt.to(1, zzcj.zza), TuplesKt.to(2, zzbs.zza), TuplesKt.to(38, zzcl.zza), TuplesKt.to(3, zzca.zza), TuplesKt.to(4, zzcb.zza), TuplesKt.to(17, zzbv.zza), TuplesKt.to(32, zzbp.zza), TuplesKt.to(5, zzcd.zza), TuplesKt.to(31, zzbq.zza), TuplesKt.to(36, zzbr.zza), TuplesKt.to(16, zzbu.zza), TuplesKt.to(26, zzck.zza), TuplesKt.to(6, zzcc.zza), TuplesKt.to(27, zzci.zza), TuplesKt.to(8, zzce.zza), TuplesKt.to(9, zzcf.zza)});
        zzcr zzcrVar = zzcr.zza;
        zzcr.zzb(new int[0]);
        zzbnVar.zze(3, context);
    }

    public static final /* synthetic */ void zzh(zzba zzbaVar, int i, List list) {
        if (!list.isEmpty()) {
            if (!zzx(list)) {
                throw new zzt(4, 5, null);
            }
            zzmk zzf = zzmn.zzf();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzml zzf2 = zzmm.zzf();
                Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) it.next());
                if (zza2 != null) {
                    if (zza2 instanceof Integer) {
                        zzf2.zzt(((Number) zza2).intValue());
                    } else if (zza2 instanceof Short) {
                        zzf2.zzs(((Number) zza2).shortValue());
                    } else if (zza2 instanceof Byte) {
                        zzf2.zze(zzez.zzm(new byte[]{((Number) zza2).byteValue()}, 0, 1));
                    } else if (zza2 instanceof Long) {
                        zzf2.zzu(((Number) zza2).longValue());
                    } else if (zza2 instanceof Double) {
                        zzf2.zzq(((Number) zza2).doubleValue());
                    } else if (zza2 instanceof Float) {
                        zzf2.zzr(((Number) zza2).floatValue());
                    } else if (zza2 instanceof Boolean) {
                        zzf2.zzd(((Boolean) zza2).booleanValue());
                    } else if (zza2 instanceof Character) {
                        zzf2.zzp(zza2.toString());
                    } else if (zza2 instanceof String) {
                        zzf2.zzv((String) zza2);
                    } else {
                        zzf2.zzv(zza2.toString());
                    }
                    zzf.zze((zzmm) zzf2.zzj());
                } else {
                    throw new zzt(4, 4, null);
                }
            }
            zzbm zzb = zzbaVar.zzd.zzb();
            byte[] zzd = ((zzmn) zzf.zzj()).zzd();
            zzb.zzf(i, zzeb.zzh().zzi(zzd, 0, zzd.length));
            return;
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzi(zzba zzbaVar, List list) {
        if (list.size() == 2) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str == null) {
                throw new zzt(4, 5, null);
            }
            Object zza3 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
            if (zza3 == null) {
                throw new zzt(4, 4, null);
            }
            if ((zza3 instanceof Integer) || (zza3 instanceof Short) || (zza3 instanceof Byte) || (zza3 instanceof Long) || (zza3 instanceof Double) || (zza3 instanceof Float) || (zza3 instanceof Boolean) || (zza3 instanceof Character) || (zza3 instanceof String)) {
                zzbaVar.zzv(str, zza3.toString());
                return;
            }
            throw new zzt(4, 7, null);
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzj(zzba zzbaVar, List list) {
        if (!zzx(list)) {
            throw new zzt(4, 5, null);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzbaVar.zzd.zzb().zzb(((zzmu) it.next()).zzi());
        }
    }

    public static final /* synthetic */ void zzk(zzba zzbaVar, int i, List list) {
        int i2;
        if (list.size() == 4 || list.size() == 5) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str == null) {
                throw new zzt(4, 5, null);
            }
            Object zza3 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
            if (true != (zza3 instanceof Object)) {
                zza3 = null;
            }
            if (zza3 == null) {
                throw new zzt(4, 5, null);
            }
            Object zza4 = zzbaVar.zzd.zzb().zza((zzmu) list.get(2));
            if (true != (zza4 instanceof String)) {
                zza4 = null;
            }
            String str2 = (String) zza4;
            if (str2 == null) {
                throw new zzt(4, 5, null);
            }
            String zza5 = zzbh.zza(zzbaVar, str2, zzbaVar.zzd.zza());
            Object zza6 = zzbaVar.zzd.zzb().zza((zzmu) list.get(3));
            if (list.size() == 5) {
                Object zza7 = zzbaVar.zzd.zzb().zza((zzmu) list.get(4));
                if (true != (zza7 instanceof Integer)) {
                    zza7 = null;
                }
                Integer num = (Integer) zza7;
                if (num == null) {
                    throw new zzt(4, 5, null);
                }
                i2 = num.intValue();
            } else {
                i2 = -1;
            }
            try {
                byte zza8 = zzbaVar.zzd.zza();
                if (zza3 instanceof String) {
                    zza3 = zzbh.zza(zzbaVar, (String) zza3, zza8);
                }
                Class zza9 = zzbk.zza(zza3);
                zzbaVar.zzd.zzb().zzf(i, Proxy.newProxyInstance(zza9.getClassLoader(), new Class[]{zza9}, new zzbe(new zzau(zzbaVar, str, i2), zza5, zza6)));
                return;
            } catch (Exception e) {
                throw new zzt(6, 20, e);
            }
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzl(zzba zzbaVar, int i, List list) {
        if (list.size() == 4 || list.size() == 5) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof Integer)) {
                zza2 = null;
            }
            Integer num = (Integer) zza2;
            if (num != null) {
                int intValue = num.intValue();
                Object zza3 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
                if (true != (zza3 instanceof Integer)) {
                    zza3 = null;
                }
                Integer num2 = (Integer) zza3;
                if (num2 != null) {
                    int intValue2 = num2.intValue();
                    Object zza4 = zzbaVar.zzd.zzb().zza((zzmu) list.get(2));
                    if (true != (zza4 instanceof String)) {
                        zza4 = null;
                    }
                    String str = (String) zza4;
                    if (str == null) {
                        throw new zzt(4, 5, null);
                    }
                    String zza5 = zzbh.zza(zzbaVar, str, zzbaVar.zzd.zza());
                    Object zza6 = zzbaVar.zzd.zzb().zza((zzmu) list.get(3));
                    if (true != (zza6 instanceof String)) {
                        zza6 = null;
                    }
                    String str2 = (String) zza6;
                    if (str2 == null) {
                        throw new zzt(4, 5, null);
                    }
                    String zza7 = zzbh.zza(zzbaVar, str2, zzbaVar.zzd.zza());
                    Object zza8 = list.size() == 5 ? zzbaVar.zzd.zzb().zza((zzmu) list.get(4)) : null;
                    zzbf zzbfVar = new zzbf(intValue2);
                    try {
                        Class zza9 = zzbk.zza(zza5);
                        zzbaVar.zzd.zzb().zzf(intValue, Proxy.newProxyInstance(zza9.getClassLoader(), new Class[]{zza9}, new zzbg(zzbfVar, zza7, zza8)));
                        zzbaVar.zzd.zzb().zzf(i, zzbfVar);
                        return;
                    } catch (Exception e) {
                        throw new zzt(6, 20, e);
                    }
                }
                throw new zzt(4, 5, null);
            }
            throw new zzt(4, 5, null);
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzm(zzba zzbaVar, int i, List list) {
        if (list.size() == 2) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof Field)) {
                zza2 = null;
            }
            Field field = (Field) zza2;
            if (field == null) {
                throw new zzt(4, 5, null);
            }
            try {
                zzbaVar.zzd.zzb().zzf(i, field.get(zzbaVar.zzd.zzb().zza((zzmu) list.get(1))));
                return;
            } catch (Exception e) {
                throw new zzt(6, 16, e);
            }
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzn(zzba zzbaVar, int i, List list) {
        if (list.size() == 1) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof Field)) {
                zza2 = null;
            }
            Field field = (Field) zza2;
            if (field != null) {
                try {
                    zzbaVar.zzd.zzb().zzf(i, field.get(null));
                    return;
                } catch (Exception e) {
                    throw new zzt(6, 16, e);
                }
            }
            throw new zzt(4, 5, null);
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzo(zzba zzbaVar, int i, List list) {
        if (!list.isEmpty()) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof Constructor)) {
                zza2 = null;
            }
            Constructor constructor = (Constructor) zza2;
            if (constructor == null) {
                throw new zzt(4, 5, null);
            }
            Object[] zzg = zzbaVar.zzd.zzb().zzg(list.subList(1, list.size()));
            try {
                zzbaVar.zzd.zzb().zzf(i, constructor.newInstance(Arrays.copyOf(zzg, zzg.length)));
                return;
            } catch (Exception e) {
                throw new zzt(6, 14, e);
            }
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzp(zzba zzbaVar, List list) {
        if (list.size() == 3) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof Field)) {
                zza2 = null;
            }
            Field field = (Field) zza2;
            if (field == null) {
                throw new zzt(4, 5, null);
            }
            try {
                field.set(zzbaVar.zzd.zzb().zza((zzmu) list.get(1)), zzbaVar.zzd.zzb().zza((zzmu) list.get(2)));
                return;
            } catch (Exception e) {
                throw new zzt(6, 11, e);
            }
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzq(zzba zzbaVar, List list) {
        if (list.size() == 2) {
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof Field)) {
                zza2 = null;
            }
            Field field = (Field) zza2;
            if (field == null) {
                throw new zzt(4, 5, null);
            }
            try {
                field.set(null, zzbaVar.zzd.zzb().zza((zzmu) list.get(1)));
                return;
            } catch (Exception e) {
                throw new zzt(6, 11, e);
            }
        }
        throw new zzt(4, 3, null);
    }

    public static final /* synthetic */ void zzr(zzba zzbaVar, zzn zznVar, int i, List list) {
        if (list.size() == 2 || list.size() == 0) {
            if (list.size() == 0) {
                zzbaVar.zzd.zzb().zzf(i, new zzn());
                return;
            }
            Object zza2 = zzbaVar.zzd.zzb().zza((zzmu) list.get(0));
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str == null) {
                throw new zzt(4, 5, null);
            }
            Object zza3 = zzbaVar.zzd.zzb().zza((zzmu) list.get(1));
            if (true != (zza3 instanceof zzn)) {
                zza3 = null;
            }
            zzn zznVar2 = (zzn) zza3;
            if (zznVar2 == null) {
                throw new zzt(4, 5, null);
            }
            byte[] zzd = zzar.zza(zznVar, zznVar2).zzd();
            zzbaVar.zzv(str, zzeb.zzh().zzi(zzd, 0, zzd.length));
            return;
        }
        throw new zzt(4, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzt(List list, zzn zznVar, zzn zznVar2, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzax(this, list, zznVar, zznVar2, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzu(Exception exc, String str, zzn zznVar, zzn zznVar2, int i, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzay(exc, i, zznVar, zznVar2, str, this, null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzv(String str, String... strArr) {
        this.zzb.zzb(str, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzw(zzmv zzmvVar, zzbl zzblVar) throws zzt {
        zzdk zzb = zzdk.zzb();
        int zzb2 = zzblVar.zzb();
        zzby zzbyVar = (zzby) this.zzf.get(Integer.valueOf(zzmvVar.zzf()));
        if (zzbyVar == null) {
            return false;
        }
        Object[] zzg = this.zzd.zzb().zzg(zzmvVar.zzj());
        zzbyVar.zza(zzmvVar.zzg(), zzblVar, Arrays.copyOf(zzg, zzg.length));
        if (zzb2 == zzblVar.zzb()) {
            zzblVar.zzg(zzblVar.zzb() + 1);
        }
        zzb.zzf();
        long zza2 = zzb.zza(TimeUnit.MICROSECONDS);
        zzj zzjVar = zzj.zza;
        zzj.zza(zzms.zza(zzmvVar.zzk()), zza2);
        zzmvVar.zzk();
        CollectionsKt.joinToString$default(zzmvVar.zzj(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new zzav(this), 31, (Object) null);
        return true;
    }

    private static final boolean zzx(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((zzmu) it.next()).zzM()));
        }
        return !arrayList.contains(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final zzmh zzy(String str, List list) throws zzt {
        if (str.length() != 0) {
            try {
                zzcq zzcqVar = new zzcq((short) zzcr.zza(CollectionsKt.toIntArray(list)), (short) 255);
                StringBuilder sb = new StringBuilder(str.length());
                for (int i = 0; i < str.length(); i++) {
                    sb.append((char) UInt.constructor-impl(UInt.constructor-impl(str.charAt(i)) ^ UInt.constructor-impl(zzcqVar.zza())));
                }
                return zzmh.zzg(zzeb.zzh().zzj(sb.toString()));
            } catch (Exception e) {
                throw new zzt(3, 18, e);
            }
        }
        throw new zzt(3, 17, null);
    }

    @Override // com.google.android.recaptcha.internal.zzas
    public final void zza(String str) {
        BuildersKt.launch$default(this.zzc, (CoroutineContext) null, (CoroutineStart) null, new zzaz(this, str, new zzn(), null), 3, (Object) null);
    }

    public final zzbn zzb() {
        return this.zzd;
    }
}
