package com.google.android.recaptcha.internal;

import android.webkit.JavascriptInterface;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzcu {
    final /* synthetic */ zzda zza;
    private Long zzb;
    private final zzdk zzc = zzdk.zzb();

    public zzcu(zzda zzdaVar) {
        this.zza = zzdaVar;
    }

    private final void zzb() {
        if (this.zzb == null) {
            this.zzc.zzf();
            this.zzb = Long.valueOf(this.zzc.zza(TimeUnit.MILLISECONDS));
        }
    }

    public final Long zza() {
        return this.zzb;
    }

    @JavascriptInterface
    public final void zzoed(String str) {
        Map map;
        zzb();
        zzlz zzg = zzlz.zzg(zzeb.zzh().zzj(str));
        zzg.zzi().name();
        zzg.zzk();
        map = this.zza.zzk;
        CancellableContinuation cancellableContinuation = (CancellableContinuation) map.remove(zzg.zzj());
        String zzk = zzg.zzk();
        if (zzk != null && zzk.length() != 0) {
            if (cancellableContinuation != null) {
                cancellableContinuation.resumeWith(Result.constructor-impl(zzg.zzk()));
                return;
            }
            return;
        }
        zzg.zzi().name();
        zzg zzgVar = zzh.zza;
        zzh zza = zzg.zza(zzg.zzi());
        zzg.zzj();
        if (cancellableContinuation != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(zza)));
        }
    }

    @JavascriptInterface
    public final void zzoid(String str) {
        zzb();
        zzmd zzg = zzmd.zzg(zzeb.zzh().zzj(str));
        zzg.zzi().name();
        if (zzg.zzi() == zzmf.JS_CODE_SUCCESS) {
            this.zza.zzm().hashCode();
            if (this.zza.zzm().complete(Unit.INSTANCE)) {
                return;
            }
            this.zza.zzm().hashCode();
            return;
        }
        zzg.zzi().name();
        zzg zzgVar = zzh.zza;
        zzh zza = zzg.zza(zzg.zzi());
        this.zza.zzm().hashCode();
        this.zza.zzm().completeExceptionally(zza);
    }

    @JavascriptInterface
    public final void zzrp(String str) {
        zzb();
        this.zza.zzd().zza(str);
    }
}
