package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;
/* loaded from: classes2.dex */
final class zzy {
    private final KeyPair zzbw;
    private final long zzbx;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public zzy(KeyPair keyPair, long j) {
        this.zzbw = keyPair;
        this.zzbx = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final KeyPair getKeyPair() {
        return this.zzbw;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long getCreationTime() {
        return this.zzbx;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzy) {
            zzy zzyVar = (zzy) obj;
            return this.zzbx == zzyVar.zzbx && this.zzbw.getPublic().equals(zzyVar.zzbw.getPublic()) && this.zzbw.getPrivate().equals(zzyVar.zzbw.getPrivate());
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbw.getPublic(), this.zzbw.getPrivate(), Long.valueOf(this.zzbx));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzv() {
        return Base64.encodeToString(this.zzbw.getPublic().getEncoded(), 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzw() {
        return Base64.encodeToString(this.zzbw.getPrivate().getEncoded(), 11);
    }
}
