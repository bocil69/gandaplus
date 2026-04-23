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
public final class zzab extends zzbl {
    final /* synthetic */ boolean zza;
    final /* synthetic */ FirebaseUser zzb;
    final /* synthetic */ EmailAuthCredential zzc;
    final /* synthetic */ FirebaseAuth zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(FirebaseAuth firebaseAuth, boolean z, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential) {
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
        if (this.zza) {
            if (TextUtils.isEmpty(str)) {
                Log.i("FirebaseAuth", " Email link reauth with empty reCAPTCHA token");
            } else {
                Log.i("FirebaseAuth", "Got reCAPTCHA token for reauth with email link");
            }
            FirebaseAuth firebaseAuth = this.zzd;
            FirebaseUser firebaseUser = this.zzb;
            EmailAuthCredential emailAuthCredential = this.zzc;
            zzadvVar2 = firebaseAuth.zzf;
            firebaseApp2 = firebaseAuth.zzb;
            return zzadvVar2.zzq(firebaseApp2, firebaseUser, emailAuthCredential, str, new zzad(firebaseAuth));
        }
        EmailAuthCredential emailAuthCredential2 = this.zzc;
        String zzd = emailAuthCredential2.zzd();
        String zze = emailAuthCredential2.zze();
        if (TextUtils.isEmpty(str)) {
            Log.i("FirebaseAuth", "Reauthenticating " + zzd + " with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for reauth with ".concat(String.valueOf(zzd)));
        }
        FirebaseAuth firebaseAuth2 = this.zzd;
        FirebaseUser firebaseUser2 = this.zzb;
        zzadvVar = firebaseAuth2.zzf;
        firebaseApp = firebaseAuth2.zzb;
        return zzadvVar.zzs(firebaseApp, firebaseUser2, zzd, Preconditions.checkNotEmpty(zze), this.zzb.getTenantId(), str, new zzad(this.zzd));
    }
}
