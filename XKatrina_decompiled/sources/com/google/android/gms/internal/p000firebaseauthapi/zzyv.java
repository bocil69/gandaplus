package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzyv {
    public static final zzyv zza = new zzyv(new zzyw());
    public static final zzyv zzb = new zzyv(new zzza());
    public static final zzyv zzc = new zzyv(new zzzc());
    public static final zzyv zzd = new zzyv(new zzzb());
    public static final zzyv zze = new zzyv(new zzyx());
    public static final zzyv zzf = new zzyv(new zzyz());
    public static final zzyv zzg = new zzyv(new zzyy());
    private final zzyu zzh;

    public zzyv(zzzd zzzdVar) {
        if (zzik.zzb()) {
            this.zzh = new zzyt(zzzdVar, null);
        } else if (zzzk.zza()) {
            this.zzh = new zzyp(zzzdVar, null);
        } else {
            this.zzh = new zzyr(zzzdVar, null);
        }
    }

    public static List zzb(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            }
        }
        return arrayList;
    }

    public final Object zza(String str) throws GeneralSecurityException {
        return this.zzh.zza(str);
    }
}
