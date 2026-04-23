package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzcl implements zzby {
    public static final zzcl zza = new zzcl();

    private zzcl() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length != 1) {
            throw new zzt(4, 3, null);
        }
        Object obj = objArr[0];
        if (true != (obj instanceof Byte)) {
            obj = null;
        }
        Byte b = (Byte) obj;
        if (b == null) {
            throw new zzt(4, 5, null);
        }
        zzblVar.zzh(b.byteValue());
    }
}
