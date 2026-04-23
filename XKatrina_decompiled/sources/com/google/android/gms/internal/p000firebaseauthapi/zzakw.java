package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzakw extends zzaiq implements RandomAccess, zzakx {
    @Deprecated
    public static final zzakx zza;
    private static final zzakw zzb;
    private final List zzc;

    static {
        zzakw zzakwVar = new zzakw(false);
        zzb = zzakwVar;
        zza = zzakwVar;
    }

    public zzakw() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzajf) {
            return ((zzajf) obj).zzo(zzakq.zzb);
        }
        return zzakq.zzd((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaiq, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaiq, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzakx) {
            collection = ((zzakx) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaiq, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaiq, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzj(remove);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaiq, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzj(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakp
    public final /* bridge */ /* synthetic */ zzakp zzd(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzakw(arrayList);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final zzakx zze() {
        return zzc() ? new zzana(this) : this;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzajf) {
            zzajf zzajfVar = (zzajf) obj;
            String zzo = zzajfVar.zzo(zzakq.zzb);
            if (zzajfVar.zzk()) {
                this.zzc.set(i, zzo);
            }
            return zzo;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzakq.zzd(bArr);
        if (zzank.zze(bArr)) {
            this.zzc.set(i, zzd);
        }
        return zzd;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakx
    public final void zzi(zzajf zzajfVar) {
        zza();
        this.zzc.add(zzajfVar);
        this.modCount++;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzakw(int i) {
        super(true);
        ArrayList arrayList = new ArrayList(i);
        this.zzc = arrayList;
    }

    private zzakw(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzakw(boolean z) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaiq, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
