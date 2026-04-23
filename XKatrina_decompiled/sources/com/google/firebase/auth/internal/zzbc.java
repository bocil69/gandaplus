package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzahf;
import com.google.android.gms.internal.p000firebaseauthapi.zzaia;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.TotpMultiFactorInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbc {
    @Nullable
    public static MultiFactorInfo zza(zzahf zzahfVar) {
        if (zzahfVar == null) {
            return null;
        }
        if (TextUtils.isEmpty(zzahfVar.zzf())) {
            if (zzahfVar.zzc() != null) {
                return new TotpMultiFactorInfo(zzahfVar.zze(), zzahfVar.zzd(), zzahfVar.zza(), (zzaia) Preconditions.checkNotNull(zzahfVar.zzc(), "totpInfo cannot be null."));
            }
            return null;
        }
        return new PhoneMultiFactorInfo(zzahfVar.zze(), zzahfVar.zzd(), zzahfVar.zza(), Preconditions.checkNotEmpty(zzahfVar.zzf()));
    }

    public static List zzb(List list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MultiFactorInfo zza = zza((zzahf) it.next());
            if (zza != null) {
                arrayList.add(zza);
            }
        }
        return arrayList;
    }
}
