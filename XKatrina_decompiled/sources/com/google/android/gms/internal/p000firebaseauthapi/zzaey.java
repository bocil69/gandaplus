package com.google.android.gms.internal.p000firebaseauthapi;

import android.os.Parcelable;
import android.util.Pair;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import com.google.firebase.auth.internal.zzae;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzx;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaey  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaey implements zzaep {
    private final zzaez zza;
    private final TaskCompletionSource zzb;

    public zzaey(zzaez zzaezVar, TaskCompletionSource taskCompletionSource) {
        this.zza = zzaezVar;
        this.zzb = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaep
    public final void zza(Object obj, Status status) {
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status == null) {
            this.zzb.setResult(obj);
            return;
        }
        zzaez zzaezVar = this.zza;
        if (zzaezVar.zzw == null) {
            AuthCredential authCredential = zzaezVar.zzt;
            if (authCredential != null) {
                this.zzb.setException(zzadz.zzb(status, authCredential, zzaezVar.zzu, zzaezVar.zzv));
                return;
            } else {
                this.zzb.setException(zzadz.zza(status));
                return;
            }
        }
        TaskCompletionSource taskCompletionSource = this.zzb;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(zzaezVar.zzg);
        zzaez zzaezVar2 = this.zza;
        zzaaf zzaafVar = zzaezVar2.zzw;
        FirebaseUser firebaseUser = ("reauthenticateWithCredential".equals(zzaezVar2.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) ? this.zza.zzh : null;
        int i = zzadz.zzb;
        Objects.requireNonNull(firebaseAuth);
        Objects.requireNonNull(zzaafVar);
        Pair pair = (Pair) zzadz.zza.get(17078);
        String str = (String) pair.first;
        String str2 = (String) pair.second;
        Parcelable.Creator<zzae> creator = zzae.CREATOR;
        List<MultiFactorInfo> zzc = zzaafVar.zzc();
        ArrayList arrayList = new ArrayList();
        for (MultiFactorInfo multiFactorInfo : zzc) {
            if (multiFactorInfo instanceof PhoneMultiFactorInfo) {
                arrayList.add((PhoneMultiFactorInfo) multiFactorInfo);
            }
        }
        List<MultiFactorInfo> zzc2 = zzaafVar.zzc();
        ArrayList arrayList2 = new ArrayList();
        for (MultiFactorInfo multiFactorInfo2 : zzc2) {
            if (multiFactorInfo2 instanceof TotpMultiFactorInfo) {
                arrayList2.add((TotpMultiFactorInfo) multiFactorInfo2);
            }
        }
        taskCompletionSource.setException(new FirebaseAuthMultiFactorException(str, str2, new zzae(arrayList, zzag.zzc(zzaafVar.zzc(), zzaafVar.zzb()), firebaseAuth.getApp().getName(), zzaafVar.zza(), (zzx) firebaseUser, arrayList2)));
    }
}
