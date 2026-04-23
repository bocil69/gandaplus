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
public final class zzz extends zzbl {
    final /* synthetic */ String zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ FirebaseUser zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ FirebaseAuth zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(FirebaseAuth firebaseAuth, String str, boolean z, FirebaseUser firebaseUser, String str2, String str3) {
        this.zzf = firebaseAuth;
        this.zza = str;
        this.zzb = z;
        this.zzc = firebaseUser;
        this.zzd = str2;
        this.zze = str3;
    }

    @Override // com.google.firebase.auth.internal.zzbl
    public final Task zza(String str) {
        zzadv zzadvVar;
        FirebaseApp firebaseApp;
        zzadv zzadvVar2;
        FirebaseApp firebaseApp2;
        if (TextUtils.isEmpty(str)) {
            String str2 = this.zza;
            Log.i("FirebaseAuth", "Logging in as " + str2 + " with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for login with email ".concat(String.valueOf(this.zza)));
        }
        if (this.zzb) {
            FirebaseAuth firebaseAuth = this.zzf;
            FirebaseUser firebaseUser = this.zzc;
            zzadvVar2 = firebaseAuth.zzf;
            firebaseApp2 = firebaseAuth.zzb;
            return zzadvVar2.zzt(firebaseApp2, (FirebaseUser) Preconditions.checkNotNull(firebaseUser), this.zza, this.zzd, this.zze, str, new zzad(this.zzf));
        }
        FirebaseAuth firebaseAuth2 = this.zzf;
        String str3 = this.zza;
        String str4 = this.zzd;
        String str5 = this.zze;
        zzadvVar = firebaseAuth2.zzf;
        firebaseApp = firebaseAuth2.zzb;
        return zzadvVar.zzE(firebaseApp, str3, str4, str5, str, new zzac(firebaseAuth2));
    }
}
