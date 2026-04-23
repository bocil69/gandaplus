package com.google.android.gms.internal.p000firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzao;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaez  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzaez implements zzafb {
    Object zzA;
    Status zzB;
    private boolean zza;
    protected final int zze;
    protected FirebaseApp zzg;
    protected FirebaseUser zzh;
    protected Object zzi;
    protected zzao zzj;
    protected zzaep zzk;
    protected Executor zzm;
    protected zzahb zzn;
    protected zzags zzo;
    protected zzagc zzp;
    protected zzahk zzq;
    protected String zzr;
    protected String zzs;
    protected AuthCredential zzt;
    protected String zzu;
    protected String zzv;
    protected zzaaf zzw;
    protected zzaha zzx;
    protected zzagx zzy;
    protected zzahs zzz;
    protected final zzaew zzf = new zzaew(this);
    protected final List zzl = new ArrayList();

    public zzaez(int i) {
        this.zze = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzj(zzaez zzaezVar) {
        zzaezVar.zzb();
        Preconditions.checkState(zzaezVar.zza, "no success or failure set on method implementation");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzk(zzaez zzaezVar, Status status) {
        zzao zzaoVar = zzaezVar.zzj;
        if (zzaoVar != null) {
            zzaoVar.zzb(status);
        }
    }

    public abstract void zzb();

    public final zzaez zzd(Object obj) {
        this.zzi = Preconditions.checkNotNull(obj, "external callback cannot be null");
        return this;
    }

    public final zzaez zze(zzao zzaoVar) {
        this.zzj = (zzao) Preconditions.checkNotNull(zzaoVar, "external failure callback cannot be null");
        return this;
    }

    public final zzaez zzf(FirebaseApp firebaseApp) {
        this.zzg = (FirebaseApp) Preconditions.checkNotNull(firebaseApp, "firebaseApp cannot be null");
        return this;
    }

    public final zzaez zzg(FirebaseUser firebaseUser) {
        this.zzh = (FirebaseUser) Preconditions.checkNotNull(firebaseUser, "firebaseUser cannot be null");
        return this;
    }

    public final zzaez zzh(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, String str) {
        List list = this.zzl;
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zza = zzafn.zza(str, onVerificationStateChangedCallbacks, this);
        synchronized (list) {
            this.zzl.add((PhoneAuthProvider.OnVerificationStateChangedCallbacks) Preconditions.checkNotNull(zza));
        }
        if (activity != null) {
            zzaeq.zza(activity, this.zzl);
        }
        this.zzm = (Executor) Preconditions.checkNotNull(executor);
        return this;
    }

    public final void zzl(Status status) {
        this.zza = true;
        this.zzB = status;
        this.zzk.zza(null, status);
    }

    public final void zzm(Object obj) {
        this.zza = true;
        this.zzA = obj;
        this.zzk.zza(obj, null);
    }
}
