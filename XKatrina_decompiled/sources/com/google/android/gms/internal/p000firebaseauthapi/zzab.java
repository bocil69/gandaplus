package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzab  reason: invalid package */
/* loaded from: classes.dex */
public final class zzab {
    private final zzj zza;
    private final zzaa zzb;

    private zzab(zzaa zzaaVar) {
        zzi zziVar = zzi.zza;
        this.zzb = zzaaVar;
        this.zza = zziVar;
    }

    public static zzab zzb(zzj zzjVar) {
        return new zzab(new zzw(zzjVar));
    }

    public static zzab zzc(String str) {
        zzp zzpVar = new zzp(Pattern.compile("[.-]"));
        if (!(!((zzo) zzpVar.zza("")).zza.matches())) {
            throw new IllegalArgumentException(zzac.zzb("The pattern may not match the empty string: %s", zzpVar));
        }
        return new zzab(new zzy(zzpVar));
    }

    public final List zzd(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        Iterator zza = this.zzb.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza.hasNext()) {
            arrayList.add((String) zza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
