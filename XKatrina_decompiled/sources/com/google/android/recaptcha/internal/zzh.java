package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaErrorCode;
import com.google.android.recaptcha.RecaptchaException;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzh extends Exception {
    public static final zzg zza = new zzg(null);
    private static final Map zzb = MapsKt.mapOf(new Pair[]{TuplesKt.to(zzmf.JS_NETWORK_ERROR, new zzh(zzf.zze, zzd.zzm)), TuplesKt.to(zzmf.JS_INTERNAL_ERROR, new zzh(zzf.zzc, zzd.zzk)), TuplesKt.to(zzmf.JS_INVALID_SITE_KEY, new zzh(zzf.zzf, zzd.zzn)), TuplesKt.to(zzmf.JS_INVALID_SITE_KEY_TYPE, new zzh(zzf.zzg, zzd.zzo)), TuplesKt.to(zzmf.JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED, new zzh(zzf.zzh, zzd.zzp)), TuplesKt.to(zzmf.JS_INVALID_ACTION, new zzh(zzf.zzi, zzd.zzq)), TuplesKt.to(zzmf.JS_PROGRAM_ERROR, new zzh(zzf.zzc, zzd.zzv))});
    private final zzf zzc;
    private final zzd zzd;
    private final Map zze = MapsKt.mapOf(new Pair[]{TuplesKt.to(zzf.zze, new RecaptchaException(RecaptchaErrorCode.NETWORK_ERROR, null, 2, null)), TuplesKt.to(zzf.zzf, new RecaptchaException(RecaptchaErrorCode.INVALID_SITEKEY, null, 2, null)), TuplesKt.to(zzf.zzg, new RecaptchaException(RecaptchaErrorCode.INVALID_KEYTYPE, null, 2, null)), TuplesKt.to(zzf.zzh, new RecaptchaException(RecaptchaErrorCode.INVALID_PACKAGE_NAME, null, 2, null)), TuplesKt.to(zzf.zzi, new RecaptchaException(RecaptchaErrorCode.INVALID_ACTION, null, 2, null)), TuplesKt.to(zzf.zzc, new RecaptchaException(RecaptchaErrorCode.INTERNAL_ERROR, null, 2, null))});

    public zzh(zzf zzfVar, zzd zzdVar) {
        this.zzc = zzfVar;
        this.zzd = zzdVar;
    }

    public final zzd zza() {
        return this.zzd;
    }

    public final zzf zzb() {
        return this.zzc;
    }

    public final RecaptchaException zzc() {
        RecaptchaException recaptchaException = (RecaptchaException) this.zze.get(this.zzc);
        return recaptchaException == null ? new RecaptchaException(RecaptchaErrorCode.UNKNOWN_ERROR, null, 2, null) : recaptchaException;
    }
}
