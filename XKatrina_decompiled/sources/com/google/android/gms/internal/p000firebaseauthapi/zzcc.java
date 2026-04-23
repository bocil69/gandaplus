package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcc {
    private static final CopyOnWriteArrayList zza = new CopyOnWriteArrayList();

    public static zzcb zza(String str) throws GeneralSecurityException {
        Iterator it = zza.iterator();
        while (it.hasNext()) {
            zzcb zzcbVar = (zzcb) it.next();
            if (zzcbVar.zzb(str)) {
                return zzcbVar;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: ".concat(String.valueOf(str)));
    }
}
