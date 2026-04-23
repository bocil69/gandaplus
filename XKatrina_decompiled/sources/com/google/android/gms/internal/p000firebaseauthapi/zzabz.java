package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.zze;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzabz {
    private final zzaff zza;

    public zzabz(zzaff zzaffVar) {
        this.zza = (zzaff) Preconditions.checkNotNull(zzaffVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String zzO(zzaew zzaewVar, String str) {
        return str + zzaewVar.zza.zze;
    }

    private final void zzP(String str, zzafe zzafeVar) {
        Preconditions.checkNotNull(zzafeVar);
        Preconditions.checkNotEmpty(str);
        zzahb zzd = zzahb.zzd(str);
        if (zzd.zzj()) {
            zzafeVar.zzb(zzd);
            return;
        }
        this.zza.zzf(new zzagp(zzd.zzf()), new zzaby(this, zzafeVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzQ(zzage zzageVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzageVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzc(zzageVar, new zzaaj(this, zzadxVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzR(zzahb zzahbVar, String str, String str2, Boolean bool, zze zzeVar, zzadx zzadxVar, zzafd zzafdVar) {
        Preconditions.checkNotNull(zzahbVar);
        Preconditions.checkNotNull(zzafdVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzg(new zzagq(zzahbVar.zze()), new zzaam(this, zzafdVar, str2, str, bool, zzeVar, zzadxVar, zzahbVar));
    }

    private final void zzS(zzagu zzaguVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzaguVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzh(zzaguVar, new zzabr(this, zzadxVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzd(zzabz zzabzVar, zzaie zzaieVar, zzadx zzadxVar, zzafd zzafdVar) {
        Status zza;
        if (zzaieVar.zzp()) {
            zze zzc = zzaieVar.zzc();
            String zzd = zzaieVar.zzd();
            String zzk = zzaieVar.zzk();
            if (zzaieVar.zzn()) {
                zza = new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
            } else {
                zza = zzai.zza(zzaieVar.zze());
            }
            zzadxVar.zze(new zzaae(zza, zzc, zzd, zzk));
            return;
        }
        zzabzVar.zzR(new zzahb(zzaieVar.zzj(), zzaieVar.zzf(), Long.valueOf(zzaieVar.zzb()), "Bearer"), zzaieVar.zzi(), zzaieVar.zzh(), Boolean.valueOf(zzaieVar.zzo()), zzaieVar.zzc(), zzadxVar, zzafdVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zze(zzabz zzabzVar, zzadx zzadxVar, zzahb zzahbVar, zzahn zzahnVar, zzafd zzafdVar) {
        Preconditions.checkNotNull(zzadxVar);
        Preconditions.checkNotNull(zzahbVar);
        Preconditions.checkNotNull(zzahnVar);
        Preconditions.checkNotNull(zzafdVar);
        zzabzVar.zza.zzg(new zzagq(zzahbVar.zze()), new zzaak(zzabzVar, zzafdVar, zzadxVar, zzahbVar, zzahnVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzf(zzabz zzabzVar, zzadx zzadxVar, zzahb zzahbVar, zzags zzagsVar, zzahn zzahnVar, zzafd zzafdVar) {
        Preconditions.checkNotNull(zzadxVar);
        Preconditions.checkNotNull(zzahbVar);
        Preconditions.checkNotNull(zzagsVar);
        Preconditions.checkNotNull(zzahnVar);
        Preconditions.checkNotNull(zzafdVar);
        zzabzVar.zza.zzn(zzahnVar, new zzaal(zzabzVar, zzahnVar, zzagsVar, zzadxVar, zzahbVar, zzafdVar));
    }

    public final void zzA(String str, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzo(str, new zzabq(this, zzadxVar));
    }

    public final void zzB(String str, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzp(new zzahp(str), new zzabt(this, zzadxVar));
    }

    public final void zzC(zzaic zzaicVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzaicVar);
        Preconditions.checkNotNull(zzadxVar);
        zzaicVar.zzd(true);
        this.zza.zzs(zzaicVar, new zzabs(this, zzadxVar));
    }

    public final void zzD(zzaif zzaifVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzaifVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzt(zzaifVar, new zzabf(this, zzadxVar));
    }

    public final void zzE(String str, String str2, String str3, String str4, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzu(new zzaih(str, str2, str3, str4), new zzaah(this, zzadxVar));
    }

    public final void zzF(EmailAuthCredential emailAuthCredential, String str, zzadx zzadxVar) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zzadxVar);
        if (emailAuthCredential.zzh()) {
            zzP(emailAuthCredential.zzc(), new zzaai(this, emailAuthCredential, str, zzadxVar));
        } else {
            zzQ(new zzage(emailAuthCredential, null, str), zzadxVar);
        }
    }

    public final void zzG(zzaij zzaijVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzaijVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzv(zzaijVar, new zzaat(this, zzadxVar));
    }

    public final void zzH(zzahr zzahrVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzahrVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzq(zzahrVar, new zzabe(this, zzahrVar, zzadxVar));
    }

    public final void zzI(zzaht zzahtVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzahtVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzr(zzahtVar, new zzabj(this, zzadxVar));
    }

    public final void zzJ(String str, String str2, String str3, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzabd(this, str2, str3, zzadxVar));
    }

    public final void zzK(String str, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzaaz(this, zzadxVar));
    }

    public final void zzL(String str, String str2, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str2, new zzabb(this, str, zzadxVar));
    }

    public final void zzM(String str, UserProfileChangeRequest userProfileChangeRequest, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzabu(this, userProfileChangeRequest, zzadxVar));
    }

    public final void zzN(zzagu zzaguVar, zzadx zzadxVar) {
        zzS(zzaguVar, zzadxVar);
    }

    public final void zzg(String str, String str2, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        zzahn zzahnVar = new zzahn();
        zzahnVar.zzf(str);
        zzahnVar.zzi(str2);
        this.zza.zzn(zzahnVar, new zzabx(this, zzadxVar));
    }

    public final void zzh(String str, String str2, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzabv(this, str2, zzadxVar));
    }

    public final void zzi(String str, String str2, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzabw(this, str2, zzadxVar));
    }

    public final void zzj(String str, String str2, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzl(new zzahj(str, null, str2), new zzaap(this, zzadxVar));
    }

    public final void zzk(String str, String str2, String str3, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzl(new zzahj(str, str2, str3), new zzaar(this, zzadxVar));
    }

    public final void zzl(String str, String str2, String str3, String str4, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzp(new zzahp(str, str2, null, str3, str4), new zzaag(this, zzadxVar));
    }

    public final void zzm(String str, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzabp(this, zzadxVar));
    }

    public final void zzn(zzagg zzaggVar, String str, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzaggVar);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzabh(this, zzaggVar, zzadxVar));
    }

    public final void zzo(zzagi zzagiVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzagiVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zze(zzagiVar, new zzabi(this, zzadxVar));
    }

    public final void zzp(String str, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzf(new zzagp(str), new zzaaq(this, zzadxVar));
    }

    public final void zzq(zzagw zzagwVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzagwVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzi(zzagwVar, new zzabl(this, zzadxVar));
    }

    public final void zzr(String str, String str2, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zza(new zzagb(str, str2), new zzaan(this, zzadxVar));
    }

    public final void zzs(zzagz zzagzVar, zzadx zzadxVar) {
        Preconditions.checkNotNull(zzagzVar);
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzj(zzagzVar, new zzabk(this, zzadxVar));
    }

    public final void zzt(String str, String str2, String str3, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str3, new zzaau(this, str, str2, zzadxVar));
    }

    public final void zzu(String str, zzaic zzaicVar, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaicVar);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzaay(this, zzaicVar, zzadxVar));
    }

    public final void zzv(String str, zzaij zzaijVar, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzaijVar);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzaaw(this, zzaijVar, zzadxVar));
    }

    public final void zzw(String str, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        zzP(str, new zzabn(this, zzadxVar));
    }

    public final void zzx(String str, ActionCodeSettings actionCodeSettings, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        zzagu zzaguVar = new zzagu(4);
        zzaguVar.zzh(str);
        if (actionCodeSettings != null) {
            zzaguVar.zzd(actionCodeSettings);
        }
        zzS(zzaguVar, zzadxVar);
    }

    public final void zzy(String str, ActionCodeSettings actionCodeSettings, String str2, String str3, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzadxVar);
        zzagu zzaguVar = new zzagu(actionCodeSettings.zza());
        zzaguVar.zzf(str);
        zzaguVar.zzd(actionCodeSettings);
        zzaguVar.zzg(str2);
        zzaguVar.zze(str3);
        this.zza.zzh(zzaguVar, new zzaao(this, zzadxVar));
    }

    public final void zzz(zzahl zzahlVar, zzadx zzadxVar) {
        Preconditions.checkNotEmpty(zzahlVar.zzd());
        Preconditions.checkNotNull(zzadxVar);
        this.zza.zzm(zzahlVar, new zzaas(this, zzadxVar));
    }
}
