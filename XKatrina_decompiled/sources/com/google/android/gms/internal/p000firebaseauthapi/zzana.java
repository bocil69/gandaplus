package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzana  reason: invalid package */
/* loaded from: classes.dex */
public final class zzana extends AbstractList implements RandomAccess, zzakx {
    private final zzakx zza;

    public zzana(zzakx zzakxVar) {
        this.zza = zzakxVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzakw) this.zza).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzamz(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new zzamy(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final zzakx zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final List zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final void zzi(zzajf zzajfVar) {
        throw new UnsupportedOperationException();
    }
}
