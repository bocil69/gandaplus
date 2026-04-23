package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.Mac;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzh extends ThreadLocal {
    final /* synthetic */ zzzi zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzzh(zzzi zzziVar) {
        this.zza = zzziVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    /* renamed from: zza */
    public final Mac initialValue() {
        String str;
        Key key;
        try {
            zzyv zzyvVar = zzyv.zzb;
            str = this.zza.zzb;
            Mac mac = (Mac) zzyvVar.zza(str);
            key = this.zza.zzc;
            mac.init(key);
            return mac;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
