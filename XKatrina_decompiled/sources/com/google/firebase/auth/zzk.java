package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzadv;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.PhoneAuthProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzk implements OnCompleteListener {
    final /* synthetic */ PhoneAuthOptions zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ FirebaseAuth zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, String str) {
        this.zzc = firebaseAuth;
        this.zza = phoneAuthOptions;
        this.zzb = str;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        String zza;
        String str;
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zzab;
        zzadv zzadvVar;
        String str2;
        zzadv zzadvVar2;
        String str3;
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            Log.e("FirebaseAuth", exception != null ? "Error while validating application identity: ".concat(String.valueOf(exception.getMessage())) : "Error while validating application identity: ");
            if (exception instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                FirebaseAuth.zzX((FirebaseAuthMissingActivityForRecaptchaException) exception, this.zza, this.zzb);
                return;
            }
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            str = null;
            zza = null;
        } else {
            String zzb = ((com.google.firebase.auth.internal.zze) task.getResult()).zzb();
            zza = ((com.google.firebase.auth.internal.zze) task.getResult()).zza();
            str = zzb;
        }
        long longValue = this.zza.zzg().longValue();
        FirebaseAuth firebaseAuth = this.zzc;
        PhoneAuthOptions phoneAuthOptions = this.zza;
        zzab = firebaseAuth.zzab(phoneAuthOptions.zzh(), phoneAuthOptions.zze());
        if (TextUtils.isEmpty(str)) {
            zzab = this.zzc.zzy(this.zza, zzab);
        }
        PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks = zzab;
        com.google.firebase.auth.internal.zzag zzagVar = (com.google.firebase.auth.internal.zzag) Preconditions.checkNotNull(this.zza.zzc());
        if (zzagVar.zzf()) {
            FirebaseAuth firebaseAuth2 = this.zzc;
            PhoneAuthOptions phoneAuthOptions2 = this.zza;
            zzadvVar2 = firebaseAuth2.zzf;
            String str4 = (String) Preconditions.checkNotNull(phoneAuthOptions2.zzh());
            FirebaseAuth firebaseAuth3 = this.zzc;
            PhoneAuthOptions phoneAuthOptions3 = this.zza;
            str3 = firebaseAuth3.zzj;
            boolean z = phoneAuthOptions3.zzd() != null;
            PhoneAuthOptions phoneAuthOptions4 = this.zza;
            zzadvVar2.zzH(zzagVar, str4, str3, longValue, z, phoneAuthOptions4.zzl(), str, zza, this.zzc.zzW(), onVerificationStateChangedCallbacks, phoneAuthOptions4.zzi(), phoneAuthOptions4.zza());
            return;
        }
        FirebaseAuth firebaseAuth4 = this.zzc;
        PhoneAuthOptions phoneAuthOptions5 = this.zza;
        zzadvVar = firebaseAuth4.zzf;
        PhoneMultiFactorInfo phoneMultiFactorInfo = (PhoneMultiFactorInfo) Preconditions.checkNotNull(phoneAuthOptions5.zzf());
        FirebaseAuth firebaseAuth5 = this.zzc;
        PhoneAuthOptions phoneAuthOptions6 = this.zza;
        str2 = firebaseAuth5.zzj;
        boolean z2 = phoneAuthOptions6.zzd() != null;
        PhoneAuthOptions phoneAuthOptions7 = this.zza;
        zzadvVar.zzJ(zzagVar, phoneMultiFactorInfo, str2, longValue, z2, phoneAuthOptions7.zzl(), str, zza, this.zzc.zzW(), onVerificationStateChangedCallbacks, phoneAuthOptions7.zzi(), phoneAuthOptions7.zza());
    }
}
