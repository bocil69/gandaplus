package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbr  reason: invalid package */
/* loaded from: classes.dex */
final class zzbr implements zzbs {
    final /* synthetic */ zzon zza;
    final /* synthetic */ zzng zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbr(zzon zzonVar, zzng zzngVar) {
        this.zza = zzonVar;
        this.zzb = zzngVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final zzbo zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzco(this.zza, this.zzb, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final zzbo zzb() {
        zzon zzonVar = this.zza;
        return new zzco(zzonVar, this.zzb, zzonVar.zzi());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final Class zzc() {
        return this.zza.getClass();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final Class zzd() {
        return this.zzb.getClass();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbs
    public final Set zze() {
        return this.zza.zzl();
    }
}
