package com.google.android.gms.internal.p000firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzan;
import com.google.firebase.auth.internal.zzbc;
import com.google.firebase.auth.internal.zzbx;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.auth.internal.zzz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzadv extends zzafc {
    public zzadv(FirebaseApp firebaseApp, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this.zza = new zzady(firebaseApp, scheduledExecutorService);
        this.zzb = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzx zzS(FirebaseApp firebaseApp, zzags zzagsVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzagsVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzt(zzagsVar, FirebaseAuthProvider.PROVIDER_ID));
        List zzr = zzagsVar.zzr();
        if (zzr != null && !zzr.isEmpty()) {
            for (int i = 0; i < zzr.size(); i++) {
                arrayList.add(new zzt((zzahg) zzr.get(i)));
            }
        }
        zzx zzxVar = new zzx(firebaseApp, arrayList);
        zzxVar.zzr(new zzz(zzagsVar.zzb(), zzagsVar.zza()));
        zzxVar.zzq(zzagsVar.zzt());
        zzxVar.zzp(zzagsVar.zzd());
        zzxVar.zzi(zzbc.zzb(zzagsVar.zzq()));
        return zzxVar;
    }

    public final Task zzA(String str) {
        return zzU(new zzadb(str));
    }

    public final Task zzB(FirebaseApp firebaseApp, zzg zzgVar, String str) {
        zzadc zzadcVar = new zzadc(str);
        zzadcVar.zzf(firebaseApp);
        zzadcVar.zzd(zzgVar);
        return zzU(zzadcVar);
    }

    public final Task zzC(FirebaseApp firebaseApp, AuthCredential authCredential, String str, zzg zzgVar) {
        zzadd zzaddVar = new zzadd(authCredential, str);
        zzaddVar.zzf(firebaseApp);
        zzaddVar.zzd(zzgVar);
        return zzU(zzaddVar);
    }

    public final Task zzD(FirebaseApp firebaseApp, String str, String str2, zzg zzgVar) {
        zzade zzadeVar = new zzade(str, str2);
        zzadeVar.zzf(firebaseApp);
        zzadeVar.zzd(zzgVar);
        return zzU(zzadeVar);
    }

    public final Task zzE(FirebaseApp firebaseApp, String str, String str2, String str3, String str4, zzg zzgVar) {
        zzadf zzadfVar = new zzadf(str, str2, str3, str4);
        zzadfVar.zzf(firebaseApp);
        zzadfVar.zzd(zzgVar);
        return zzU(zzadfVar);
    }

    public final Task zzF(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, String str, zzg zzgVar) {
        zzadg zzadgVar = new zzadg(emailAuthCredential, str);
        zzadgVar.zzf(firebaseApp);
        zzadgVar.zzd(zzgVar);
        return zzU(zzadgVar);
    }

    public final Task zzG(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, String str, zzg zzgVar) {
        zzafn.zzc();
        zzadh zzadhVar = new zzadh(phoneAuthCredential, str);
        zzadhVar.zzf(firebaseApp);
        zzadhVar.zzd(zzgVar);
        return zzU(zzadhVar);
    }

    public final Task zzH(zzag zzagVar, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzadi zzadiVar = new zzadi(zzagVar, str, str2, j, z, z2, str3, str4, z3);
        zzadiVar.zzh(onVerificationStateChangedCallbacks, activity, executor, str);
        return zzU(zzadiVar);
    }

    public final Task zzI(zzag zzagVar, String str) {
        return zzU(new zzadj(zzagVar, str));
    }

    public final Task zzJ(zzag zzagVar, PhoneMultiFactorInfo phoneMultiFactorInfo, String str, long j, boolean z, boolean z2, String str2, String str3, boolean z3, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzadk zzadkVar = new zzadk(phoneMultiFactorInfo, Preconditions.checkNotEmpty(zzagVar.zze()), str, j, z, z2, str2, str3, z3);
        zzadkVar.zzh(onVerificationStateChangedCallbacks, activity, executor, phoneMultiFactorInfo.getUid());
        return zzU(zzadkVar);
    }

    public final Task zzK(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, zzbx zzbxVar) {
        zzadl zzadlVar = new zzadl(firebaseUser.zzf(), str, str2);
        zzadlVar.zzf(firebaseApp);
        zzadlVar.zzg(firebaseUser);
        zzadlVar.zzd(zzbxVar);
        zzadlVar.zze(zzbxVar);
        return zzU(zzadlVar);
    }

    public final Task zzL(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbx zzbxVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbxVar);
        List zzg = firebaseUser.zzg();
        if ((zzg != null && !zzg.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzadz.zza(new Status((int) FirebaseError.ERROR_NO_SUCH_PROVIDER, str)));
        }
        if (((str.hashCode() == 1216985755 && str.equals("password")) ? (char) 0 : (char) 65535) != 0) {
            zzadn zzadnVar = new zzadn(str);
            zzadnVar.zzf(firebaseApp);
            zzadnVar.zzg(firebaseUser);
            zzadnVar.zzd(zzbxVar);
            zzadnVar.zze(zzbxVar);
            return zzU(zzadnVar);
        }
        zzadm zzadmVar = new zzadm();
        zzadmVar.zzf(firebaseApp);
        zzadmVar.zzg(firebaseUser);
        zzadmVar.zzd(zzbxVar);
        zzadmVar.zze(zzbxVar);
        return zzU(zzadmVar);
    }

    public final Task zzM(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbx zzbxVar) {
        zzado zzadoVar = new zzado(str);
        zzadoVar.zzf(firebaseApp);
        zzadoVar.zzg(firebaseUser);
        zzadoVar.zzd(zzbxVar);
        zzadoVar.zze(zzbxVar);
        return zzU(zzadoVar);
    }

    public final Task zzN(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbx zzbxVar) {
        zzadp zzadpVar = new zzadp(str);
        zzadpVar.zzf(firebaseApp);
        zzadpVar.zzg(firebaseUser);
        zzadpVar.zzd(zzbxVar);
        zzadpVar.zze(zzbxVar);
        return zzU(zzadpVar);
    }

    public final Task zzO(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzbx zzbxVar) {
        zzafn.zzc();
        zzadq zzadqVar = new zzadq(phoneAuthCredential);
        zzadqVar.zzf(firebaseApp);
        zzadqVar.zzg(firebaseUser);
        zzadqVar.zzd(zzbxVar);
        zzadqVar.zze(zzbxVar);
        return zzU(zzadqVar);
    }

    public final Task zzP(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzbx zzbxVar) {
        zzadr zzadrVar = new zzadr(userProfileChangeRequest);
        zzadrVar.zzf(firebaseApp);
        zzadrVar.zzg(firebaseUser);
        zzadrVar.zzd(zzbxVar);
        zzadrVar.zze(zzbxVar);
        return zzU(zzadrVar);
    }

    public final Task zzQ(String str, String str2, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zzg(7);
        return zzU(new zzads(str, str2, actionCodeSettings));
    }

    public final Task zzR(FirebaseApp firebaseApp, String str, String str2) {
        zzadt zzadtVar = new zzadt(str, str2);
        zzadtVar.zzf(firebaseApp);
        return zzU(zzadtVar);
    }

    public final void zzT(FirebaseApp firebaseApp, zzahl zzahlVar, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        zzadu zzaduVar = new zzadu(zzahlVar);
        zzaduVar.zzf(firebaseApp);
        zzaduVar.zzh(onVerificationStateChangedCallbacks, activity, executor, zzahlVar.zzd());
        zzU(zzaduVar);
    }

    public final Task zza(FirebaseApp firebaseApp, String str, String str2) {
        zzacb zzacbVar = new zzacb(str, str2);
        zzacbVar.zzf(firebaseApp);
        return zzU(zzacbVar);
    }

    public final Task zzb(FirebaseApp firebaseApp, String str, String str2) {
        zzacc zzaccVar = new zzacc(str, str2);
        zzaccVar.zzf(firebaseApp);
        return zzU(zzaccVar);
    }

    public final Task zzc(FirebaseApp firebaseApp, String str, String str2, String str3) {
        zzacd zzacdVar = new zzacd(str, str2, str3);
        zzacdVar.zzf(firebaseApp);
        return zzU(zzacdVar);
    }

    public final Task zzd(FirebaseApp firebaseApp, String str, String str2, String str3, String str4, zzg zzgVar) {
        zzace zzaceVar = new zzace(str, str2, str3, str4);
        zzaceVar.zzf(firebaseApp);
        zzaceVar.zzd(zzgVar);
        return zzU(zzaceVar);
    }

    public final Task zze(FirebaseUser firebaseUser, zzan zzanVar) {
        zzacf zzacfVar = new zzacf();
        zzacfVar.zzg(firebaseUser);
        zzacfVar.zzd(zzanVar);
        zzacfVar.zze(zzanVar);
        return zzU(zzacfVar);
    }

    public final Task zzf(FirebaseApp firebaseApp, String str, String str2) {
        zzacg zzacgVar = new zzacg(str, str2);
        zzacgVar.zzf(firebaseApp);
        return zzU(zzacgVar);
    }

    public final Task zzg(FirebaseApp firebaseApp, PhoneMultiFactorAssertion phoneMultiFactorAssertion, FirebaseUser firebaseUser, String str, zzg zzgVar) {
        zzafn.zzc();
        zzach zzachVar = new zzach(phoneMultiFactorAssertion, firebaseUser.zzf(), str, null);
        zzachVar.zzf(firebaseApp);
        zzachVar.zzd(zzgVar);
        return zzU(zzachVar);
    }

    public final Task zzh(FirebaseApp firebaseApp, TotpMultiFactorAssertion totpMultiFactorAssertion, FirebaseUser firebaseUser, String str, String str2, zzg zzgVar) {
        zzach zzachVar = new zzach(totpMultiFactorAssertion, firebaseUser.zzf(), str, str2);
        zzachVar.zzf(firebaseApp);
        zzachVar.zzd(zzgVar);
        return zzU(zzachVar);
    }

    public final Task zzi(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str, zzg zzgVar) {
        zzafn.zzc();
        zzaci zzaciVar = new zzaci(phoneMultiFactorAssertion, str, null);
        zzaciVar.zzf(firebaseApp);
        zzaciVar.zzd(zzgVar);
        if (firebaseUser != null) {
            zzaciVar.zzg(firebaseUser);
        }
        return zzU(zzaciVar);
    }

    public final Task zzj(FirebaseApp firebaseApp, FirebaseUser firebaseUser, TotpMultiFactorAssertion totpMultiFactorAssertion, String str, String str2, zzg zzgVar) {
        zzaci zzaciVar = new zzaci(totpMultiFactorAssertion, str, str2);
        zzaciVar.zzf(firebaseApp);
        zzaciVar.zzd(zzgVar);
        if (firebaseUser != null) {
            zzaciVar.zzg(firebaseUser);
        }
        return zzU(zzaciVar);
    }

    public final Task zzk(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbx zzbxVar) {
        zzacj zzacjVar = new zzacj(str);
        zzacjVar.zzf(firebaseApp);
        zzacjVar.zzg(firebaseUser);
        zzacjVar.zzd(zzbxVar);
        zzacjVar.zze(zzbxVar);
        return zzU(zzacjVar);
    }

    public final Task zzl() {
        return zzU(new zzack());
    }

    public final Task zzm(String str, String str2) {
        return zzU(new zzacl(str, "RECAPTCHA_ENTERPRISE"));
    }

    public final Task zzn(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzbx zzbxVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbxVar);
        List zzg = firebaseUser.zzg();
        if (zzg == null || !zzg.contains(authCredential.getProvider())) {
            if (authCredential instanceof EmailAuthCredential) {
                EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
                if (!emailAuthCredential.zzg()) {
                    zzacm zzacmVar = new zzacm(emailAuthCredential);
                    zzacmVar.zzf(firebaseApp);
                    zzacmVar.zzg(firebaseUser);
                    zzacmVar.zzd(zzbxVar);
                    zzacmVar.zze(zzbxVar);
                    return zzU(zzacmVar);
                }
                zzacp zzacpVar = new zzacp(emailAuthCredential);
                zzacpVar.zzf(firebaseApp);
                zzacpVar.zzg(firebaseUser);
                zzacpVar.zzd(zzbxVar);
                zzacpVar.zze(zzbxVar);
                return zzU(zzacpVar);
            } else if (!(authCredential instanceof PhoneAuthCredential)) {
                Preconditions.checkNotNull(firebaseApp);
                Preconditions.checkNotNull(authCredential);
                Preconditions.checkNotNull(firebaseUser);
                Preconditions.checkNotNull(zzbxVar);
                zzacn zzacnVar = new zzacn(authCredential);
                zzacnVar.zzf(firebaseApp);
                zzacnVar.zzg(firebaseUser);
                zzacnVar.zzd(zzbxVar);
                zzacnVar.zze(zzbxVar);
                return zzU(zzacnVar);
            } else {
                zzafn.zzc();
                zzaco zzacoVar = new zzaco((PhoneAuthCredential) authCredential);
                zzacoVar.zzf(firebaseApp);
                zzacoVar.zzg(firebaseUser);
                zzacoVar.zzd(zzbxVar);
                zzacoVar.zze(zzbxVar);
                return zzU(zzacoVar);
            }
        }
        return Tasks.forException(zzadz.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
    }

    public final Task zzo(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbx zzbxVar) {
        zzacq zzacqVar = new zzacq(authCredential, str);
        zzacqVar.zzf(firebaseApp);
        zzacqVar.zzg(firebaseUser);
        zzacqVar.zzd(zzbxVar);
        zzacqVar.zze(zzbxVar);
        return zzU(zzacqVar);
    }

    public final Task zzp(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzbx zzbxVar) {
        zzacr zzacrVar = new zzacr(authCredential, str);
        zzacrVar.zzf(firebaseApp);
        zzacrVar.zzg(firebaseUser);
        zzacrVar.zzd(zzbxVar);
        zzacrVar.zze(zzbxVar);
        return zzU(zzacrVar);
    }

    public final Task zzq(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, String str, zzbx zzbxVar) {
        zzacs zzacsVar = new zzacs(emailAuthCredential, str);
        zzacsVar.zzf(firebaseApp);
        zzacsVar.zzg(firebaseUser);
        zzacsVar.zzd(zzbxVar);
        zzacsVar.zze(zzbxVar);
        return zzU(zzacsVar);
    }

    public final Task zzr(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, String str, zzbx zzbxVar) {
        zzact zzactVar = new zzact(emailAuthCredential, str);
        zzactVar.zzf(firebaseApp);
        zzactVar.zzg(firebaseUser);
        zzactVar.zzd(zzbxVar);
        zzactVar.zze(zzbxVar);
        return zzU(zzactVar);
    }

    public final Task zzs(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, String str4, zzbx zzbxVar) {
        zzacu zzacuVar = new zzacu(str, str2, str3, str4);
        zzacuVar.zzf(firebaseApp);
        zzacuVar.zzg(firebaseUser);
        zzacuVar.zzd(zzbxVar);
        zzacuVar.zze(zzbxVar);
        return zzU(zzacuVar);
    }

    public final Task zzt(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, String str4, zzbx zzbxVar) {
        zzacv zzacvVar = new zzacv(str, str2, str3, str4);
        zzacvVar.zzf(firebaseApp);
        zzacvVar.zzg(firebaseUser);
        zzacvVar.zzd(zzbxVar);
        zzacvVar.zze(zzbxVar);
        return zzU(zzacvVar);
    }

    public final Task zzu(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbx zzbxVar) {
        zzafn.zzc();
        zzacw zzacwVar = new zzacw(phoneAuthCredential, str);
        zzacwVar.zzf(firebaseApp);
        zzacwVar.zzg(firebaseUser);
        zzacwVar.zzd(zzbxVar);
        zzacwVar.zze(zzbxVar);
        return zzU(zzacwVar);
    }

    public final Task zzv(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzbx zzbxVar) {
        zzafn.zzc();
        zzacx zzacxVar = new zzacx(phoneAuthCredential, str);
        zzacxVar.zzf(firebaseApp);
        zzacxVar.zzg(firebaseUser);
        zzacxVar.zzd(zzbxVar);
        zzacxVar.zze(zzbxVar);
        return zzU(zzacxVar);
    }

    public final Task zzw(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzbx zzbxVar) {
        zzacy zzacyVar = new zzacy();
        zzacyVar.zzf(firebaseApp);
        zzacyVar.zzg(firebaseUser);
        zzacyVar.zzd(zzbxVar);
        zzacyVar.zze(zzbxVar);
        return zzU(zzacyVar);
    }

    public final Task zzx(FirebaseApp firebaseApp, ActionCodeSettings actionCodeSettings, String str) {
        zzacz zzaczVar = new zzacz(str, actionCodeSettings);
        zzaczVar.zzf(firebaseApp);
        return zzU(zzaczVar);
    }

    public final Task zzy(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2, String str3) {
        actionCodeSettings.zzg(1);
        zzada zzadaVar = new zzada(str, actionCodeSettings, str2, str3, "sendPasswordResetEmail");
        zzadaVar.zzf(firebaseApp);
        return zzU(zzadaVar);
    }

    public final Task zzz(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2, String str3) {
        actionCodeSettings.zzg(6);
        zzada zzadaVar = new zzada(str, actionCodeSettings, str2, str3, "sendSignInLinkToEmail");
        zzadaVar.zzf(firebaseApp);
        return zzU(zzadaVar);
    }
}
