package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcg {
    private final Class zza;
    private zzch zzd;
    private ConcurrentMap zzb = new ConcurrentHashMap();
    private final List zzc = new ArrayList();
    private zzro zze = zzro.zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcg(Class cls, zzcf zzcfVar) {
        this.zza = cls;
    }

    private final zzcg zze(@Nullable Object obj, @Nullable Object obj2, zzwu zzwuVar, boolean z) throws GeneralSecurityException {
        byte[] array;
        if (this.zzb != null) {
            if (obj != null || obj2 != null) {
                if (zzwuVar.zzk() == 3) {
                    Integer valueOf = Integer.valueOf(zzwuVar.zza());
                    if (zzwuVar.zze() == zzxo.RAW) {
                        valueOf = null;
                    }
                    zzbn zza = zznt.zzc().zza(zzoo.zza(zzwuVar.zzb().zzf(), zzwuVar.zzb().zze(), zzwuVar.zzb().zzb(), zzwuVar.zze(), valueOf), zzcr.zza());
                    int ordinal = zzwuVar.zze().ordinal();
                    if (ordinal == 1) {
                        array = ByteBuffer.allocate(5).put((byte) 1).putInt(zzwuVar.zza()).array();
                    } else {
                        if (ordinal != 2) {
                            if (ordinal == 3) {
                                array = zzbi.zza;
                            } else if (ordinal != 4) {
                                throw new GeneralSecurityException("unknown output prefix type");
                            }
                        }
                        array = ByteBuffer.allocate(5).put((byte) 0).putInt(zzwuVar.zza()).array();
                    }
                    zzch zzchVar = new zzch(obj, obj2, array, zzwuVar.zzk(), zzwuVar.zze(), zzwuVar.zza(), zzwuVar.zzb().zzf(), zza);
                    ConcurrentMap concurrentMap = this.zzb;
                    List list = this.zzc;
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(zzchVar);
                    zzcj zzcjVar = new zzcj(zzchVar.zzg(), null);
                    List list2 = (List) concurrentMap.put(zzcjVar, Collections.unmodifiableList(arrayList));
                    if (list2 != null) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.addAll(list2);
                        arrayList2.add(zzchVar);
                        concurrentMap.put(zzcjVar, Collections.unmodifiableList(arrayList2));
                    }
                    list.add(zzchVar);
                    if (z) {
                        if (this.zzd == null) {
                            this.zzd = zzchVar;
                        } else {
                            throw new IllegalStateException("you cannot set two primary primitives");
                        }
                    }
                    return this;
                }
                throw new GeneralSecurityException("only ENABLED key is allowed");
            }
            throw new GeneralSecurityException("at least one of the `fullPrimitive` or `primitive` must be set");
        }
        throw new IllegalStateException("addPrimitive cannot be called after build");
    }

    public final zzcg zza(@Nullable Object obj, @Nullable Object obj2, zzwu zzwuVar) throws GeneralSecurityException {
        zze(obj, obj2, zzwuVar, false);
        return this;
    }

    public final zzcg zzb(@Nullable Object obj, @Nullable Object obj2, zzwu zzwuVar) throws GeneralSecurityException {
        zze(obj, obj2, zzwuVar, true);
        return this;
    }

    public final zzcg zzc(zzro zzroVar) {
        if (this.zzb != null) {
            this.zze = zzroVar;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build");
    }

    public final zzcl zzd() throws GeneralSecurityException {
        ConcurrentMap concurrentMap = this.zzb;
        if (concurrentMap != null) {
            zzcl zzclVar = new zzcl(concurrentMap, this.zzc, this.zzd, this.zze, this.zza, null);
            this.zzb = null;
            return zzclVar;
        }
        throw new IllegalStateException("build cannot be called twice");
    }
}
