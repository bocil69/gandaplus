package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzrv {
    private final zzro zza;
    private final List zzb;
    @Nullable
    private final Integer zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzrv(zzro zzroVar, List list, Integer num, zzru zzruVar) {
        this.zza = zzroVar;
        this.zzb = list;
        this.zzc = num;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzrv) {
            zzrv zzrvVar = (zzrv) obj;
            if (this.zza.equals(zzrvVar.zza) && this.zzb.equals(zzrvVar.zzb)) {
                Integer num = this.zzc;
                Integer num2 = zzrvVar.zzc;
                if (num == num2) {
                    return true;
                }
                if (num != null && num.equals(num2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", this.zza, this.zzb, this.zzc);
    }
}
