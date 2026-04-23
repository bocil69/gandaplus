package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzahb;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public class zzac implements com.google.firebase.auth.internal.zzg {
    final /* synthetic */ FirebaseAuth zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    @Override // com.google.firebase.auth.internal.zzg
    public final void zza(zzahb zzahbVar, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(zzahbVar);
        Preconditions.checkNotNull(firebaseUser);
        firebaseUser.zzh(zzahbVar);
        this.zza.zzQ(firebaseUser, zzahbVar, true);
    }
}
