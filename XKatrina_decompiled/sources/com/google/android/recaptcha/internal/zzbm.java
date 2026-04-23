package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbm {
    private final Map zza;
    private final Set zzb;
    private final Map zzc;

    public zzbm() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zza = linkedHashMap;
        this.zzb = new LinkedHashSet();
        this.zzc = linkedHashMap;
    }

    private final List zzh(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(zza((zzmu) it.next()));
        }
        return arrayList;
    }

    public final Object zza(zzmu zzmuVar) throws zzt {
        int zzN = zzmuVar.zzN();
        int i = zzN - 1;
        if (zzN == 0) {
            throw null;
        }
        switch (i) {
            case 0:
                return this.zza.get(Integer.valueOf(zzmuVar.zzi()));
            case 1:
                return Boolean.valueOf(zzmuVar.zzL());
            case 2:
                byte[] zzo = zzmuVar.zzH().zzo();
                if (zzo.length != 1) {
                    throw new zzt(4, 6, null);
                }
                return Byte.valueOf(zzo[0]);
            case 3:
                String zzJ = zzmuVar.zzJ();
                if (zzJ.length() != 1) {
                    throw new zzt(4, 6, null);
                }
                return Character.valueOf(zzJ.charAt(0));
            case 4:
                int zzj = zzmuVar.zzj();
                if (zzj < -32768 || zzj > 32767) {
                    throw new zzt(4, 6, null);
                }
                return Short.valueOf((short) zzj);
            case 5:
                return Integer.valueOf(zzmuVar.zzk());
            case 6:
            case 8:
                throw new zzt(4, 6, null);
            case 7:
                return Long.valueOf(zzmuVar.zzG());
            case 9:
                return Float.valueOf(zzmuVar.zzg());
            case 10:
                return Double.valueOf(zzmuVar.zzf());
            case 11:
                return zzmuVar.zzK();
            case 12:
                return null;
            default:
                throw new zzt(4, 5, null);
        }
    }

    public final Object zzb(int i) {
        return this.zza.remove(Integer.valueOf(i));
    }

    public final Map zzc() {
        return this.zzc;
    }

    public final void zzd() {
        this.zza.clear();
    }

    public final void zze(int i, Object obj) {
        zzf(173, obj);
        this.zzb.add(173);
    }

    public final void zzf(int i, Object obj) {
        this.zza.put(Integer.valueOf(i), obj);
    }

    public final Object[] zzg(List list) {
        return zzh(list).toArray(new Object[0]);
    }
}
