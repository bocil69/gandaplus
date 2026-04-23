package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzcc implements zzby {
    public static final zzcc zza = new zzcc();

    private zzcc() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length == 2) {
            Class<?> cls = objArr[0];
            if (true != (cls instanceof Object)) {
                cls = null;
            }
            if (cls == null) {
                throw new zzt(4, 5, null);
            }
            Class<?> cls2 = cls instanceof Class ? cls : cls.getClass();
            Object obj = objArr[1];
            if (true != (obj instanceof String)) {
                obj = null;
            }
            String str = (String) obj;
            if (str == null) {
                throw new zzt(4, 5, null);
            }
            try {
                zzblVar.zzc().zzf(i, cls2.getField(zzbx.zza(this, str, zzblVar.zza())));
                return;
            } catch (Exception e) {
                throw new zzt(6, 10, e);
            }
        }
        throw new zzt(4, 3, null);
    }
}
