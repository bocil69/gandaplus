package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzca implements zzby {
    public static final zzca zza = new zzca();

    private zzca() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length != 1) {
            throw new zzt(4, 3, null);
        }
        Object obj = objArr[0];
        if (true != (obj instanceof Object)) {
            obj = null;
        }
        if (obj == null) {
            throw new zzt(4, 5, null);
        }
        try {
            if (obj instanceof String) {
                obj = zzbx.zza(this, (String) obj, zzblVar.zza());
            }
            zzblVar.zzc().zzf(i, zzbk.zza(obj));
        } catch (Exception e) {
            throw new zzt(6, 8, e);
        }
    }
}
