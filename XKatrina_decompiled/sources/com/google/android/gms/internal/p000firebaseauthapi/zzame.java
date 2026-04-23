package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzame  reason: invalid package */
/* loaded from: classes.dex */
final class zzame extends zzamo {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzame(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamo
    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzb(); i++) {
                Map.Entry zzg = zzg(i);
                if (((zzakb) zzg.getKey()).zzc()) {
                    zzg.setValue(Collections.unmodifiableList((List) zzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzakb) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
