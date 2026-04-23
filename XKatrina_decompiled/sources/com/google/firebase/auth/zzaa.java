package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzadv;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.zzbl;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzaa extends zzbl {
    final /* synthetic */ boolean zza;
    final /* synthetic */ FirebaseUser zzb;
    final /* synthetic */ EmailAuthCredential zzc;
    final /* synthetic */ FirebaseAuth zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(FirebaseAuth firebaseAuth, boolean z, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential) {
        this.zzd = firebaseAuth;
        this.zza = z;
        this.zzb = firebaseUser;
        this.zzc = emailAuthCredential;
    }

    @Override // com.google.firebase.auth.internal.zzbl
    public final Task zza(String str) {
        zzadv zzadvVar;
        FirebaseApp firebaseApp;
        zzadv zzadvVar2;
        FirebaseApp firebaseApp2;
        if (TextUtils.isEmpty(str)) {
            Log.i("FirebaseAuth", "Email link login/reauth with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for login/reauth with email link");
        }
        if (this.zza) {
            FirebaseAuth firebaseAuth = this.zzd;
            FirebaseUser firebaseUser = this.zzb;
            zzadvVar2 = firebaseAuth.zzf;
            firebaseApp2 = firebaseAuth.zzb;
            return zzadvVar2.zzr(firebaseApp2, (FirebaseUser) Preconditions.checkNotNull(firebaseUser), this.zzc, str, new zzad(this.zzd));
        }
        FirebaseAuth firebaseAuth2 = this.zzd;
        EmailAuthCredential emailAuthCredential = this.zzc;
        zzadvVar = firebaseAuth2.zzf;
        firebaseApp = firebaseAuth2.zzb;
        return zzadvVar.zzF(firebaseApp, emailAuthCredential, str, new zzac(firebaseAuth2));
    }
}
