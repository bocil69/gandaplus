package com.google.android.gms.internal.p000firebaseauthapi;

import androidx.collection.ArrayMap;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafx  reason: invalid package */
/* loaded from: classes.dex */
public final class zzafx {
    private static final Map zza = new ArrayMap();
    private static final Map zzb = new ArrayMap();

    public static String zza(String str) {
        zzafv zzafvVar;
        Map map = zza;
        synchronized (map) {
            zzafvVar = (zzafv) map.get(str);
        }
        if (zzafvVar == null) {
            throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
        }
        return zzh(zzafvVar.zzb(), zzafvVar.zza(), zzafvVar.zzb().contains(":")).concat("emulator/auth/handler");
    }

    public static String zzb(String str) {
        zzafv zzafvVar;
        Map map = zza;
        synchronized (map) {
            zzafvVar = (zzafv) map.get(str);
        }
        return (zzafvVar != null ? "".concat(zzh(zzafvVar.zzb(), zzafvVar.zza(), zzafvVar.zzb().contains(":"))) : "https://").concat("www.googleapis.com/identitytoolkit/v3/relyingparty");
    }

    public static String zzc(String str) {
        zzafv zzafvVar;
        Map map = zza;
        synchronized (map) {
            zzafvVar = (zzafv) map.get(str);
        }
        return (zzafvVar != null ? "".concat(zzh(zzafvVar.zzb(), zzafvVar.zza(), zzafvVar.zzb().contains(":"))) : "https://").concat("identitytoolkit.googleapis.com/v2");
    }

    public static String zzd(String str) {
        zzafv zzafvVar;
        Map map = zza;
        synchronized (map) {
            zzafvVar = (zzafv) map.get(str);
        }
        return (zzafvVar != null ? "".concat(zzh(zzafvVar.zzb(), zzafvVar.zza(), zzafvVar.zzb().contains(":"))) : "https://").concat("securetoken.googleapis.com/v1");
    }

    public static void zze(String str, zzafw zzafwVar) {
        Map map = zzb;
        synchronized (map) {
            if (map.containsKey(str)) {
                ((List) map.get(str)).add(new WeakReference(zzafwVar));
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new WeakReference(zzafwVar));
                map.put(str, arrayList);
            }
        }
    }

    public static void zzf(FirebaseApp firebaseApp, String str, int i) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        Map map = zza;
        synchronized (map) {
            map.put(apiKey, new zzafv(str, i));
        }
        Map map2 = zzb;
        synchronized (map2) {
            if (map2.containsKey(apiKey)) {
                boolean z = false;
                for (WeakReference weakReference : (List) map2.get(apiKey)) {
                    zzafw zzafwVar = (zzafw) weakReference.get();
                    if (zzafwVar != null) {
                        zzafwVar.zzk();
                        z = true;
                    }
                }
                if (!z) {
                    zza.remove(apiKey);
                }
            }
        }
    }

    public static boolean zzg(FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }

    private static String zzh(String str, int i, boolean z) {
        if (z) {
            return "http://[" + str + "]:" + i + "/";
        }
        return "http://" + str + ":" + i + "/";
    }
}
