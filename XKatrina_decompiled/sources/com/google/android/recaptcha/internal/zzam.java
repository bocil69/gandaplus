package com.google.android.recaptcha.internal;

import java.util.Timer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzam extends SuspendLambda implements Function2 {
    final /* synthetic */ zzao zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzam(zzao zzaoVar, Continuation continuation) {
        super(2, continuation);
        this.zza = zzaoVar;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzam(this.zza, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        zzad zzadVar;
        Timer timer;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzao zzaoVar = this.zza;
        synchronized (zzaj.class) {
            zzadVar = zzaoVar.zze;
            if (zzadVar.zzb() == 0) {
                timer = zzao.zzb;
                if (timer != null) {
                    timer.cancel();
                }
                zzao.zzb = null;
            }
            zzaoVar.zzg();
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
