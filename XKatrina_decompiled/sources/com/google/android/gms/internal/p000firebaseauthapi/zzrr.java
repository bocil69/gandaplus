package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrr  reason: invalid package */
/* loaded from: classes.dex */
public final class zzrr {
    @Nullable
    private ArrayList zza = new ArrayList();
    private zzro zzb = zzro.zza;
    @Nullable
    private Integer zzc = null;

    public final zzrr zza(zzbu zzbuVar, int i, String str, String str2) {
        ArrayList arrayList = this.zza;
        if (arrayList == null) {
            throw new IllegalStateException("addEntry cannot be called after build()");
        }
        arrayList.add(new zzrt(zzbuVar, i, str, str2, null));
        return this;
    }

    public final zzrr zzb(zzro zzroVar) {
        if (this.zza != null) {
            this.zzb = zzroVar;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build()");
    }

    public final zzrr zzc(int i) {
        if (this.zza == null) {
            throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
        }
        this.zzc = Integer.valueOf(i);
        return this;
    }

    public final zzrv zzd() throws GeneralSecurityException {
        if (this.zza != null) {
            Integer num = this.zzc;
            if (num != null) {
                int intValue = num.intValue();
                ArrayList arrayList = this.zza;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    int zza = ((zzrt) arrayList.get(i)).zza();
                    i++;
                    if (zza == intValue) {
                        zzrv zzrvVar = new zzrv(this.zzb, Collections.unmodifiableList(this.zza), this.zzc, null);
                        this.zza = null;
                        return zzrvVar;
                    }
                }
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            zzrv zzrvVar2 = new zzrv(this.zzb, Collections.unmodifiableList(this.zza), this.zzc, null);
            this.zza = null;
            return zzrvVar2;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
