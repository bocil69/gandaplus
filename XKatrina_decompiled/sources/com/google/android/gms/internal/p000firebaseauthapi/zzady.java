package com.google.android.gms.internal.p000firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.TotpSecret;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.ScheduledExecutorService;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzady  reason: invalid package */
/* loaded from: classes.dex */
public final class zzady {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzabz zzb;
    private final zzaft zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzady(FirebaseApp firebaseApp, ScheduledExecutorService scheduledExecutorService) {
        Preconditions.checkNotNull(firebaseApp);
        Context applicationContext = firebaseApp.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzb = new zzabz(new zzaem(firebaseApp, zzael.zza(), null, null, null));
        this.zzc = new zzaft(applicationContext, scheduledExecutorService);
    }

    private static boolean zzJ(long j, boolean z) {
        if (j <= 0 || !z) {
            zza.w("App hash will not be appended to the request.", new Object[0]);
            return false;
        }
        return true;
    }

    public final void zzA(zzaab zzaabVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzadwVar);
        Preconditions.checkNotNull(zzaabVar);
        this.zzb.zzG(zzafj.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzaabVar.zza())), new zzadx(zzadwVar, zza));
    }

    public final void zzB(String str, String str2, String str3, long j, boolean z, boolean z2, String str4, String str5, boolean z3, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str, "idToken should not be empty.");
        Preconditions.checkNotNull(zzadwVar);
        zzadx zzadxVar = new zzadx(zzadwVar, zza);
        if (this.zzc.zzk(str2)) {
            if (z) {
                this.zzc.zzi(str2);
            } else {
                this.zzc.zzh(zzadxVar, str2);
                return;
            }
        }
        zzahv zzb = zzahv.zzb(str, str2, str3, str4, str5, null);
        if (zzJ(j, z3)) {
            zzb.zzd(new zzafy(this.zzc.zzb()));
        }
        this.zzc.zzj(str2, zzadxVar, j, z3);
        this.zzb.zzH(zzb, new zzafq(this.zzc, zzadxVar, str2));
    }

    public final void zzC(zzaac zzaacVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzaacVar);
        Preconditions.checkNotNull(zzadwVar);
        String phoneNumber = zzaacVar.zzb().getPhoneNumber();
        zzadx zzadxVar = new zzadx(zzadwVar, zza);
        if (this.zzc.zzk(phoneNumber)) {
            if (zzaacVar.zzg()) {
                this.zzc.zzi(phoneNumber);
            } else {
                this.zzc.zzh(zzadxVar, phoneNumber);
                return;
            }
        }
        long zza2 = zzaacVar.zza();
        boolean zzh = zzaacVar.zzh();
        zzaht zzb = zzaht.zzb(zzaacVar.zzd(), zzaacVar.zzb().getUid(), zzaacVar.zzb().getPhoneNumber(), zzaacVar.zzc(), zzaacVar.zzf(), zzaacVar.zze());
        if (zzJ(zza2, zzh)) {
            zzb.zzd(new zzafy(this.zzc.zzb()));
        }
        this.zzc.zzj(phoneNumber, zzadxVar, zza2, zzh);
        this.zzb.zzI(zzb, new zzafq(this.zzc, zzadxVar, phoneNumber));
    }

    public final void zzD(zzahx zzahxVar, zzadw zzadwVar) {
        this.zzb.zzH(zzahxVar, new zzadx((zzadw) Preconditions.checkNotNull(zzadwVar), zza));
    }

    public final void zzE(String str, String str2, String str3, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotEmpty(str2, "uid should not be empty.");
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzJ(str, str2, str3, new zzadx(zzadwVar, zza));
    }

    public final void zzF(String str, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzK(str, new zzadx(zzadwVar, zza));
    }

    public final void zzG(String str, String str2, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzL(str, str2, new zzadx(zzadwVar, zza));
    }

    public final void zzH(String str, UserProfileChangeRequest userProfileChangeRequest, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzM(str, userProfileChangeRequest, new zzadx(zzadwVar, zza));
    }

    public final void zzI(zzaad zzaadVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzaadVar);
        this.zzb.zzN(zzagu.zzc(zzaadVar.zza(), zzaadVar.zzb(), zzaadVar.zzc()), new zzadx(zzadwVar, zza));
    }

    public final void zza(String str, String str2, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzg(str, str2, new zzadx(zzadwVar, zza));
    }

    public final void zzb(String str, String str2, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzh(str, str2, new zzadx(zzadwVar, zza));
    }

    public final void zzc(String str, String str2, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzi(str, str2, new zzadx(zzadwVar, zza));
    }

    public final void zzd(String str, String str2, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzj(str, str2, new zzadx(zzadwVar, zza));
    }

    public final void zze(zzzs zzzsVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzzsVar);
        Preconditions.checkNotEmpty(zzzsVar.zza());
        Preconditions.checkNotEmpty(zzzsVar.zzb());
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzk(zzzsVar.zza(), zzzsVar.zzb(), zzzsVar.zzc(), new zzadx(zzadwVar, zza));
    }

    public final void zzf(String str, String str2, String str3, String str4, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzl(str, str2, str3, str4, new zzadx(zzadwVar, zza));
    }

    public final void zzg(String str, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzm(str, new zzadx(zzadwVar, zza));
    }

    public final void zzh(MultiFactorAssertion multiFactorAssertion, String str, String str2, String str3, zzadw zzadwVar) {
        zzagg zzc;
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotNull(zzadwVar);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential zza2 = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            zzc = zzagk.zzc(str, (String) Preconditions.checkNotNull(zza2.zzg()), (String) Preconditions.checkNotNull(zza2.getSmsCode()), str2, str3);
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            zzc = zzagm.zzc(str, Preconditions.checkNotEmpty(str2), Preconditions.checkNotEmpty(((TotpSecret) Preconditions.checkNotNull(totpMultiFactorAssertion.zza())).getSessionInfo()), Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str3);
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
        this.zzb.zzn(zzc, str, new zzadx(zzadwVar, zza));
    }

    public final void zzi(String str, MultiFactorAssertion multiFactorAssertion, String str2, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzadwVar);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential zza2 = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            this.zzb.zzo(zzagl.zzb(str, (String) Preconditions.checkNotNull(zza2.zzg()), (String) Preconditions.checkNotNull(zza2.getSmsCode()), str2), new zzadx(zzadwVar, zza));
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            this.zzb.zzo(zzagn.zzb(str, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str2, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzb())), new zzadx(zzadwVar, zza));
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
    }

    public final void zzj(String str, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzp(str, new zzadx(zzadwVar, zza));
    }

    public final void zzk(zzzt zzztVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzztVar);
        this.zzb.zzq(zzagw.zzb(), new zzadx(zzadwVar, zza));
    }

    public final void zzl(String str, String str2, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        this.zzb.zzr(str, str2, new zzadx(zzadwVar, zza));
    }

    public final void zzm(zzzu zzzuVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzzuVar);
        this.zzb.zzs(zzagz.zzb(zzzuVar.zzb(), zzzuVar.zza()), new zzadx(zzadwVar, zza));
    }

    public final void zzn(String str, String str2, String str3, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzt(str, str2, str3, new zzadx(zzadwVar, zza));
    }

    public final void zzo(String str, zzaic zzaicVar, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaicVar);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzu(str, zzaicVar, new zzadx(zzadwVar, zza));
    }

    public final void zzp(zzzv zzzvVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzadwVar);
        Preconditions.checkNotNull(zzzvVar);
        this.zzb.zzv(Preconditions.checkNotEmpty(zzzvVar.zzb()), zzafj.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzzvVar.zza())), new zzadx(zzadwVar, zza));
    }

    public final void zzq(String str, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzw(str, new zzadx(zzadwVar, zza));
    }

    public final void zzr(zzzw zzzwVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzzwVar);
        Preconditions.checkNotEmpty(zzzwVar.zzb());
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzx(zzzwVar.zzb(), zzzwVar.zza(), new zzadx(zzadwVar, zza));
    }

    public final void zzs(zzzx zzzxVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzzxVar);
        Preconditions.checkNotEmpty(zzzxVar.zzc());
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzy(zzzxVar.zzc(), zzzxVar.zza(), zzzxVar.zzd(), zzzxVar.zzb(), new zzadx(zzadwVar, zza));
    }

    public final void zzt(zzzy zzzyVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzadwVar);
        Preconditions.checkNotNull(zzzyVar);
        zzahl zzahlVar = (zzahl) Preconditions.checkNotNull(zzzyVar.zza());
        String zzd = zzahlVar.zzd();
        zzadx zzadxVar = new zzadx(zzadwVar, zza);
        if (this.zzc.zzk(zzd)) {
            if (zzahlVar.zzf()) {
                this.zzc.zzi(zzd);
            } else {
                this.zzc.zzh(zzadxVar, zzd);
                return;
            }
        }
        long zzb = zzahlVar.zzb();
        boolean zzg = zzahlVar.zzg();
        if (zzJ(zzb, zzg)) {
            zzahlVar.zze(new zzafy(this.zzc.zzb()));
        }
        this.zzc.zzj(zzd, zzadxVar, zzb, zzg);
        this.zzb.zzz(zzahlVar, new zzafq(this.zzc, zzadxVar, zzd));
    }

    public final void zzu(zzzz zzzzVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzzzVar);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzA(zzzzVar.zza(), new zzadx(zzadwVar, zza));
    }

    public final void zzv(String str, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzB(str, new zzadx(zzadwVar, zza));
    }

    public final void zzw(zzaic zzaicVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzaicVar);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzC(zzaicVar, new zzadx(zzadwVar, zza));
    }

    public final void zzx(zzaif zzaifVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzaifVar);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzD(zzaifVar, new zzadx(zzadwVar, zza));
    }

    public final void zzy(String str, String str2, String str3, String str4, zzadw zzadwVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadwVar);
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzE(str, str2, str3, str4, new zzadx(zzadwVar, zza));
    }

    public final void zzz(zzaaa zzaaaVar, zzadw zzadwVar) {
        Preconditions.checkNotNull(zzaaaVar);
        Preconditions.checkNotNull(zzaaaVar.zza());
        Preconditions.checkNotNull(zzadwVar);
        this.zzb.zzF(zzaaaVar.zza(), zzaaaVar.zzb(), new zzadx(zzadwVar, zza));
    }
}
