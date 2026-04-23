package com.google.android.recaptcha.internal;

import java.util.Map;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzio extends zziy {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzio(int i) {
        super(i, null);
    }

    @Override // com.google.android.recaptcha.internal.zziy
    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzb(); i++) {
                ((zzgd) zzg(i).getKey()).zzg();
            }
            for (Map.Entry entry : zzc()) {
                ((zzgd) entry.getKey()).zzg();
            }
        }
        super.zza();
    }
}
