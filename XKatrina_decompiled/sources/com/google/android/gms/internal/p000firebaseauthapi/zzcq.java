package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcq  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcq {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = Logger.getLogger(zzcq.class.getName());
    private static final AtomicReference zzc = new AtomicReference(new zzbt());
    private static final ConcurrentMap zzd = new ConcurrentHashMap();
    private static final ConcurrentMap zze = new ConcurrentHashMap();
    private static final ConcurrentMap zzf = new ConcurrentHashMap();
    private static final ConcurrentMap zzg = new ConcurrentHashMap();

    private zzcq() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static zzbo zza(String str) throws GeneralSecurityException {
        return ((zzbt) zzc.get()).zzb(str);
    }

    public static synchronized zzwi zzb(zzwn zzwnVar) throws GeneralSecurityException {
        zzwi zza2;
        synchronized (zzcq.class) {
            zzbo zzb2 = ((zzbt) zzc.get()).zzb(zzwnVar.zzg());
            if (((Boolean) zze.get(zzwnVar.zzg())).booleanValue()) {
                zza2 = zzb2.zza(zzwnVar.zzf());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type ".concat(String.valueOf(zzwnVar.zzg())));
            }
        }
        return zza2;
    }

    @Nullable
    public static Class zzc(Class cls) {
        try {
            return zznq.zza().zzb(cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    public static Object zzd(zzwi zzwiVar, Class cls) throws GeneralSecurityException {
        return zze(zzwiVar.zzf(), zzwiVar.zze(), cls);
    }

    public static Object zze(String str, zzajf zzajfVar, Class cls) throws GeneralSecurityException {
        return ((zzbt) zzc.get()).zza(str, cls).zzb(zzajfVar);
    }

    public static synchronized void zzf(zzon zzonVar, zzng zzngVar, boolean z) throws GeneralSecurityException {
        synchronized (zzcq.class) {
            AtomicReference atomicReference = zzc;
            zzbt zzbtVar = new zzbt((zzbt) atomicReference.get());
            zzbtVar.zzc(zzonVar, zzngVar);
            Map zzc2 = zzonVar.zza().zzc();
            String zzd2 = zzonVar.zzd();
            zzi(zzd2, zzc2, true);
            String zzd3 = zzngVar.zzd();
            zzi(zzd3, Collections.emptyMap(), false);
            if (!((zzbt) atomicReference.get()).zze(zzd2)) {
                zzd.put(zzd2, new zzcp(zzonVar));
                zzj(zzonVar.zza().zzc());
            }
            ConcurrentMap concurrentMap = zze;
            concurrentMap.put(zzd2, true);
            concurrentMap.put(zzd3, false);
            atomicReference.set(zzbtVar);
        }
    }

    public static synchronized void zzg(zzng zzngVar, boolean z) throws GeneralSecurityException {
        synchronized (zzcq.class) {
            AtomicReference atomicReference = zzc;
            zzbt zzbtVar = new zzbt((zzbt) atomicReference.get());
            zzbtVar.zzd(zzngVar);
            Map zzc2 = zzngVar.zza().zzc();
            String zzd2 = zzngVar.zzd();
            zzi(zzd2, zzc2, true);
            if (!((zzbt) atomicReference.get()).zze(zzd2)) {
                zzd.put(zzd2, new zzcp(zzngVar));
                zzj(zzngVar.zza().zzc());
            }
            zze.put(zzd2, true);
            atomicReference.set(zzbtVar);
        }
    }

    public static synchronized void zzh(zzcm zzcmVar) throws GeneralSecurityException {
        synchronized (zzcq.class) {
            zznq.zza().zzf(zzcmVar);
        }
    }

    private static synchronized void zzi(String str, Map map, boolean z) throws GeneralSecurityException {
        synchronized (zzcq.class) {
            if (z) {
                ConcurrentMap concurrentMap = zze;
                if (concurrentMap.containsKey(str) && !((Boolean) concurrentMap.get(str)).booleanValue()) {
                    throw new GeneralSecurityException("New keys are already disallowed for key type ".concat(str));
                }
                if (((zzbt) zzc.get()).zze(str)) {
                    for (Map.Entry entry : map.entrySet()) {
                        if (!zzg.containsKey(entry.getKey())) {
                            throw new GeneralSecurityException("Attempted to register a new key template " + ((String) entry.getKey()) + " from an existing key manager of type " + str);
                        }
                    }
                } else {
                    for (Map.Entry entry2 : map.entrySet()) {
                        if (zzg.containsKey(entry2.getKey())) {
                            throw new GeneralSecurityException("Attempted overwrite of a registered key template ".concat(String.valueOf((String) entry2.getKey())));
                        }
                    }
                }
            }
        }
    }

    private static void zzj(Map map) throws GeneralSecurityException {
        for (Map.Entry entry : map.entrySet()) {
            zzg.put((String) entry.getKey(), (zzce) entry.getValue());
        }
    }
}
