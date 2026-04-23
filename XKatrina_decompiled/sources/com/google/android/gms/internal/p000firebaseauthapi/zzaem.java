package com.google.android.gms.internal.p000firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaem  reason: invalid package */
/* loaded from: classes.dex */
final class zzaem extends zzaff implements zzafw {
    zzaen zza;
    private zzaeg zzb;
    private zzaeh zzc;
    private zzafk zzd;
    private final zzael zze;
    private final FirebaseApp zzf;
    private final String zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaem(FirebaseApp firebaseApp, zzael zzaelVar, zzafk zzafkVar, zzaeg zzaegVar, zzaeh zzaehVar) {
        this.zzf = firebaseApp;
        String apiKey = firebaseApp.getOptions().getApiKey();
        this.zzg = apiKey;
        this.zze = (zzael) Preconditions.checkNotNull(zzaelVar);
        zzy(null, null, null);
        zzafx.zze(apiKey, this);
    }

    private final zzaen zzx() {
        if (this.zza == null) {
            FirebaseApp firebaseApp = this.zzf;
            this.zza = new zzaen(firebaseApp.getApplicationContext(), firebaseApp, this.zze.zzb());
        }
        return this.zza;
    }

    private final void zzy(zzafk zzafkVar, zzaeg zzaegVar, zzaeh zzaehVar) {
        this.zzd = null;
        this.zzb = null;
        this.zzc = null;
        String zza = zzafu.zza("firebear.secureToken");
        if (TextUtils.isEmpty(zza)) {
            zza = zzafx.zzd(this.zzg);
        } else {
            Log.e("LocalClient", "Found hermetic configuration for secureToken URL: ".concat(String.valueOf(zza)));
        }
        if (this.zzd == null) {
            this.zzd = new zzafk(zza, zzx());
        }
        String zza2 = zzafu.zza("firebear.identityToolkit");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = zzafx.zzb(this.zzg);
        } else {
            Log.e("LocalClient", "Found hermetic configuration for identityToolkit URL: ".concat(String.valueOf(zza2)));
        }
        if (this.zzb == null) {
            this.zzb = new zzaeg(zza2, zzx());
        }
        String zza3 = zzafu.zza("firebear.identityToolkitV2");
        if (TextUtils.isEmpty(zza3)) {
            zza3 = zzafx.zzc(this.zzg);
        } else {
            Log.e("LocalClient", "Found hermetic configuration for identityToolkitV2 URL: ".concat(String.valueOf(zza3)));
        }
        if (this.zzc == null) {
            this.zzc = new zzaeh(zza3, zzx());
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zza(zzagb zzagbVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzagbVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/createAuthUri", this.zzg), zzagbVar, zzafeVar, zzagc.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzb(zzagd zzagdVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzagdVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/deleteAccount", this.zzg), zzagdVar, zzafeVar, Void.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzc(zzage zzageVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzageVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/emailLinkSignin", this.zzg), zzageVar, zzafeVar, zzagf.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzd(zzagg zzaggVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzaggVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeh zzaehVar = this.zzc;
        zzafh.zzb(zzaehVar.zza("/accounts/mfaEnrollment:finalize", this.zzg), zzaggVar, zzafeVar, zzagh.class, zzaehVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zze(zzagi zzagiVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzagiVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeh zzaehVar = this.zzc;
        zzafh.zzb(zzaehVar.zza("/accounts/mfaSignIn:finalize", this.zzg), zzagiVar, zzafeVar, zzagj.class, zzaehVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzf(zzagp zzagpVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzagpVar);
        Preconditions.checkNotNull(zzafeVar);
        zzafk zzafkVar = this.zzd;
        zzafh.zzb(zzafkVar.zza("/token", this.zzg), zzagpVar, zzafeVar, zzahb.class, zzafkVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzg(zzagq zzagqVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzagqVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/getAccountInfo", this.zzg), zzagqVar, zzafeVar, zzagr.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzh(zzagu zzaguVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzaguVar);
        Preconditions.checkNotNull(zzafeVar);
        if (zzaguVar.zzb() != null) {
            zzx().zzc(zzaguVar.zzb().zze());
        }
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/getOobConfirmationCode", this.zzg), zzaguVar, zzafeVar, zzagv.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzi(zzagw zzagwVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzagwVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zza(zzaegVar.zza("/getRecaptchaParam", this.zzg), zzafeVar, zzagx.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzj(zzagz zzagzVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzagzVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeh zzaehVar = this.zzc;
        String zza = zzaehVar.zza("/recaptchaConfig", this.zzg);
        String zzc = zzagzVar.zzc();
        String zzd = zzagzVar.zzd();
        zzafh.zza(zza + "&clientType=" + zzc + "&version=" + zzd, zzafeVar, zzaha.class, zzaehVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafw
    public final void zzk() {
        zzy(null, null, null);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzl(zzahj zzahjVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzahjVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/resetPassword", this.zzg), zzahjVar, zzafeVar, zzahk.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzm(zzahl zzahlVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzahlVar);
        Preconditions.checkNotNull(zzafeVar);
        if (!TextUtils.isEmpty(zzahlVar.zzc())) {
            zzx().zzc(zzahlVar.zzc());
        }
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/sendVerificationCode", this.zzg), zzahlVar, zzafeVar, zzahm.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzn(zzahn zzahnVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzahnVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/setAccountInfo", this.zzg), zzahnVar, zzafeVar, zzaho.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzo(String str, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzafeVar);
        zzx().zzb(str);
        ((zzabq) zzafeVar).zza.zzo();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzp(zzahp zzahpVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzahpVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/signupNewUser", this.zzg), zzahpVar, zzafeVar, zzahq.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzq(zzahr zzahrVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzahrVar);
        Preconditions.checkNotNull(zzafeVar);
        if (zzahrVar instanceof zzahv) {
            zzahv zzahvVar = (zzahv) zzahrVar;
            if (!TextUtils.isEmpty(zzahvVar.zzc())) {
                zzx().zzc(zzahvVar.zzc());
            }
        }
        zzaeh zzaehVar = this.zzc;
        zzafh.zzb(zzaehVar.zza("/accounts/mfaEnrollment:start", this.zzg), zzahrVar, zzafeVar, zzahs.class, zzaehVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzr(zzaht zzahtVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzahtVar);
        Preconditions.checkNotNull(zzafeVar);
        if (!TextUtils.isEmpty(zzahtVar.zzc())) {
            zzx().zzc(zzahtVar.zzc());
        }
        zzaeh zzaehVar = this.zzc;
        zzafh.zzb(zzaehVar.zza("/accounts/mfaSignIn:start", this.zzg), zzahtVar, zzafeVar, zzahu.class, zzaehVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzs(zzaic zzaicVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzaicVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/verifyAssertion", this.zzg), zzaicVar, zzafeVar, zzaie.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzt(zzaif zzaifVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzaifVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/verifyCustomToken", this.zzg), zzaifVar, zzafeVar, zzaig.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzu(zzaih zzaihVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzaihVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/verifyPassword", this.zzg), zzaihVar, zzafeVar, zzaii.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzv(zzaij zzaijVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzaijVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeg zzaegVar = this.zzb;
        zzafh.zzb(zzaegVar.zza("/verifyPhoneNumber", this.zzg), zzaijVar, zzafeVar, zzaik.class, zzaegVar.zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaff
    public final void zzw(zzail zzailVar, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzailVar);
        Preconditions.checkNotNull(zzafeVar);
        zzaeh zzaehVar = this.zzc;
        zzafh.zzb(zzaehVar.zza("/accounts/mfaEnrollment:withdraw", this.zzg), zzailVar, zzafeVar, zzaim.class, zzaehVar.zzb);
    }
}
