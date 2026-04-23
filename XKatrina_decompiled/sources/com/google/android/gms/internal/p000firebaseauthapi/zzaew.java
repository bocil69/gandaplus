package com.google.android.gms.internal.p000firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzai;
import com.google.firebase.auth.internal.zzao;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaew  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaew implements zzadw {
    final /* synthetic */ zzaez zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaew(zzaez zzaezVar) {
        this.zza = zzaezVar;
    }

    private final void zzs(zzaex zzaexVar) {
        this.zza.zzm.execute(new zzaev(this, zzaexVar));
    }

    private final void zzt(Status status, AuthCredential authCredential, String str, String str2) {
        zzaez.zzk(this.zza, status);
        zzaez zzaezVar = this.zza;
        zzaezVar.zzt = authCredential;
        zzaezVar.zzu = str;
        zzaezVar.zzv = str2;
        zzao zzaoVar = zzaezVar.zzj;
        if (zzaoVar != null) {
            zzaoVar.zzb(status);
        }
        this.zza.zzl(status);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zza(String str) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 8, zzabz.zzO(this, "Unexpected response type "));
        zzaez zzaezVar = this.zza;
        zzaezVar.zzs = str;
        zzaezVar.zza = true;
        zzs(new zzaet(this, str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzb(String str) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 8, zzabz.zzO(this, "Unexpected response type "));
        this.zza.zzs = str;
        zzs(new zzaer(this, str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzc(zzagc zzagcVar) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 3, zzabz.zzO(this, "Unexpected response type "));
        zzaez zzaezVar = this.zza;
        zzaezVar.zzp = zzagcVar;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzd() throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 5, zzabz.zzO(this, "Unexpected response type "));
        zzaez.zzj(this.zza);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zze(zzaae zzaaeVar) {
        zzt(zzaaeVar.zza(), zzaaeVar.zzb(), zzaaeVar.zzc(), zzaaeVar.zzd());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzf(zzaaf zzaafVar) {
        zzaez zzaezVar = this.zza;
        zzaezVar.zzw = zzaafVar;
        zzaezVar.zzl(zzai.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzg(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected response type ");
        int i = this.zza.zze;
        sb.append(i);
        Preconditions.checkState(i == 2, sb.toString());
        zzt(status, phoneAuthCredential, null, null);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzh(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        zzaez zzaezVar = this.zza;
        if (zzaezVar.zze == 8) {
            zzaezVar.zza = true;
            zzs(new zzaeu(this, status));
            return;
        }
        zzaez.zzk(zzaezVar, status);
        this.zza.zzl(status);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzi(zzagx zzagxVar) throws RemoteException {
        zzaez zzaezVar = this.zza;
        zzaezVar.zzy = zzagxVar;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzj(zzaha zzahaVar) throws RemoteException {
        zzaez zzaezVar = this.zza;
        zzaezVar.zzx = zzahaVar;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzk(zzahb zzahbVar, zzags zzagsVar) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 2, zzabz.zzO(this, "Unexpected response type: "));
        zzaez zzaezVar = this.zza;
        zzaezVar.zzn = zzahbVar;
        zzaezVar.zzo = zzagsVar;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzl(zzahk zzahkVar) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 4, zzabz.zzO(this, "Unexpected response type "));
        zzaez zzaezVar = this.zza;
        zzaezVar.zzq = zzahkVar;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzm() throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 6, zzabz.zzO(this, "Unexpected response type "));
        zzaez.zzj(this.zza);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzn(String str) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 7, zzabz.zzO(this, "Unexpected response type "));
        zzaez zzaezVar = this.zza;
        zzaezVar.zzr = str;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzo() throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 9, zzabz.zzO(this, "Unexpected response type "));
        zzaez.zzj(this.zza);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzp(zzahs zzahsVar) throws RemoteException {
        zzaez zzaezVar = this.zza;
        zzaezVar.zzz = zzahsVar;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzq(zzahb zzahbVar) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 1, zzabz.zzO(this, "Unexpected response type: "));
        zzaez zzaezVar = this.zza;
        zzaezVar.zzn = zzahbVar;
        zzaez.zzj(zzaezVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadw
    public final void zzr(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        int i = this.zza.zze;
        Preconditions.checkState(i == 8, zzabz.zzO(this, "Unexpected response type "));
        this.zza.zza = true;
        zzs(new zzaes(this, phoneAuthCredential));
    }
}
