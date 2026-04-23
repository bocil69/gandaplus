package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzgh implements zzhw {
    private static final zzgh zza = new zzgh();

    private zzgh() {
    }

    public static zzgh zza() {
        return zza;
    }

    @Override // com.google.android.recaptcha.internal.zzhw
    public final zzhv zzb(Class cls) {
        if (!zzgo.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
        try {
            return (zzhv) zzgo.zzr(cls.asSubclass(zzgo.class)).zzh(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzhw
    public final boolean zzc(Class cls) {
        return zzgo.class.isAssignableFrom(cls);
    }
}
