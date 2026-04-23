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
public final class zzn extends zzbl {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ FirebaseAuth zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(FirebaseAuth firebaseAuth, String str, String str2) {
        this.zzc = firebaseAuth;
        this.zza = str;
        this.zzb = str2;
    }

    @Override // com.google.firebase.auth.internal.zzbl
    public final Task zza(String str) {
        zzadv zzadvVar;
        FirebaseApp firebaseApp;
        String str2;
        if (TextUtils.isEmpty(str)) {
            String str3 = this.zza;
            Log.i("FirebaseAuth", "Creating user with " + str3 + " with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for sign up with email ".concat(String.valueOf(this.zza)));
        }
        FirebaseAuth firebaseAuth = this.zzc;
        String str4 = this.zza;
        String str5 = this.zzb;
        zzadvVar = firebaseAuth.zzf;
        firebaseApp = firebaseAuth.zzb;
        str2 = firebaseAuth.zzl;
        return zzadvVar.zzd(firebaseApp, str4, str5, str2, str, new zzac(firebaseAuth));
    }
}
