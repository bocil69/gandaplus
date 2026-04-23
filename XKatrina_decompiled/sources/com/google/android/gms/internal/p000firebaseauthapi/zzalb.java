package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalb  reason: invalid package */
/* loaded from: classes.dex */
final class zzalb extends zzald {
    private zzalb() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzalb(zzala zzalaVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzald
    public final List zza(Object obj, long j) {
        zzakp zzakpVar = (zzakp) zzanf.zzf(obj, j);
        if (zzakpVar.zzc()) {
            return zzakpVar;
        }
        int size = zzakpVar.size();
        zzakp zzd = zzakpVar.zzd(size == 0 ? 10 : size + size);
        zzanf.zzs(obj, j, zzd);
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzald
    public final void zzb(Object obj, long j) {
        ((zzakp) zzanf.zzf(obj, j)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzald
    public final void zzc(Object obj, Object obj2, long j) {
        zzakp zzakpVar = (zzakp) zzanf.zzf(obj, j);
        zzakp zzakpVar2 = (zzakp) zzanf.zzf(obj2, j);
        int size = zzakpVar.size();
        int size2 = zzakpVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzakpVar.zzc()) {
                zzakpVar = zzakpVar.zzd(size2 + size);
            }
            zzakpVar.addAll(zzakpVar2);
        }
        if (size > 0) {
            zzakpVar2 = zzakpVar;
        }
        zzanf.zzs(obj, j, zzakpVar2);
    }
}
