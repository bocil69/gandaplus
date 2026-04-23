package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Iterator;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamz  reason: invalid package */
/* loaded from: classes.dex */
final class zzamz implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzana zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzamz(zzana zzanaVar) {
        zzakx zzakxVar;
        this.zzb = zzanaVar;
        zzakxVar = zzanaVar.zza;
        this.zza = zzakxVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
