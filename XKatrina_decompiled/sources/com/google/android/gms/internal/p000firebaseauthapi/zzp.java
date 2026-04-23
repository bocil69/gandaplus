package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzp  reason: invalid package */
/* loaded from: classes.dex */
final class zzp extends zzm implements Serializable {
    private final Pattern zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(Pattern pattern) {
        Objects.requireNonNull(pattern);
        this.zza = pattern;
    }

    public final String toString() {
        return this.zza.toString();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzm
    public final zzl zza(CharSequence charSequence) {
        return new zzo(this.zza.matcher(charSequence));
    }
}
