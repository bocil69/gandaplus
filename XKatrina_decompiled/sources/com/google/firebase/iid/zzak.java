package com.google.firebase.iid;

import android.os.Bundle;
/* loaded from: classes2.dex */
final class zzak extends zzaj<Void> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzak(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.zzaj
    public final boolean zzab() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.zzaj
    public final void zzb(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            finish(null);
        } else {
            zza(new zzam(4, "Invalid response to one way request"));
        }
    }
}
