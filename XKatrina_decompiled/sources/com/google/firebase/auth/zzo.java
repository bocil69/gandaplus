package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.p000firebaseauthapi.zzadv;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.zzbl;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzo extends zzbl {
    final /* synthetic */ String zza;
    final /* synthetic */ ActionCodeSettings zzb;
    final /* synthetic */ FirebaseAuth zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(FirebaseAuth firebaseAuth, String str, ActionCodeSettings actionCodeSettings) {
        this.zzc = firebaseAuth;
        this.zza = str;
        this.zzb = actionCodeSettings;
    }

    @Override // com.google.firebase.auth.internal.zzbl
    public final Task zza(String str) {
        zzadv zzadvVar;
        FirebaseApp firebaseApp;
        String str2;
        if (TextUtils.isEmpty(str)) {
            String str3 = this.zza;
            Log.i("FirebaseAuth", "Password reset request " + str3 + " with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for password reset of email ".concat(String.valueOf(this.zza)));
        }
        FirebaseAuth firebaseAuth = this.zzc;
        String str4 = this.zza;
        ActionCodeSettings actionCodeSettings = this.zzb;
        zzadvVar = firebaseAuth.zzf;
        firebaseApp = firebaseAuth.zzb;
        str2 = firebaseAuth.zzl;
        return zzadvVar.zzy(firebaseApp, str4, actionCodeSettings, str2, str);
    }
}
