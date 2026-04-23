package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzahz {
    private List zza;

    public zzahz() {
        this(null);
    }

    public final List zza() {
        return this.zza;
    }

    public zzahz(int i, List list) {
        if (!list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                list.set(i2, Strings.emptyToNull((String) list.get(i2)));
            }
            this.zza = Collections.unmodifiableList(list);
            return;
        }
        this.zza = Collections.emptyList();
    }

    public zzahz(List list) {
        this.zza = new ArrayList();
    }
}
