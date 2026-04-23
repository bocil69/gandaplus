package com.google.android.recaptcha.internal;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.recaptcha.RecaptchaAction;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzcw extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ RecaptchaAction zze;
    final /* synthetic */ zzda zzf;
    final /* synthetic */ String zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcw(RecaptchaAction recaptchaAction, zzda zzdaVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zze = recaptchaAction;
        this.zzf = zzdaVar;
        this.zzg = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzcw(this.zze, this.zzf, this.zzg, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Map map;
        String str;
        String str2;
        Context context;
        zzr zzrVar;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzd;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            zzda zzdaVar = this.zzf;
            String str3 = this.zzg;
            RecaptchaAction recaptchaAction = this.zze;
            this.zza = zzdaVar;
            this.zzb = str3;
            this.zzc = recaptchaAction;
            this.zzd = 1;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            map = zzdaVar.zzk;
            map.put(str3, cancellableContinuationImpl);
            zzma zzf = zzmb.zzf();
            zzf.zze(str3);
            zzf.zzd(recaptchaAction.getAction());
            byte[] zzd = ((zzmb) zzf.zzj()).zzd();
            String zzi = zzeb.zzh().zzi(zzd, 0, zzd.length);
            zzai zzaiVar = zzai.zza;
            zzkw zzkwVar = zzkw.EXECUTE_NATIVE;
            str = zzdaVar.zzg;
            str2 = zzdaVar.zzh;
            zzaf zzafVar = new zzaf(zzkwVar, str, str2, str3, null);
            context = zzdaVar.zze;
            zzrVar = zzdaVar.zzf;
            zzai.zzc(zzafVar, context, zzrVar);
            WebView zzb = zzdaVar.zzb();
            zzb.evaluateJavascript("recaptcha.m.Main.execute(\"" + zzi + "\")", null);
            obj = cancellableContinuationImpl.getResult();
            if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return obj;
    }
}
