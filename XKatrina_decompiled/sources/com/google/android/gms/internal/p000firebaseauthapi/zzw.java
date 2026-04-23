package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzw implements zzaa {
    final /* synthetic */ zzj zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zzj zzjVar) {
        this.zza = zzjVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaa
    public final /* synthetic */ Iterator zza(zzab zzabVar, CharSequence charSequence) {
        return new zzv(this, zzabVar, charSequence);
    }
}
