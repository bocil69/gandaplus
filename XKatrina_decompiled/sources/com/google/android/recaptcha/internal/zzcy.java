package com.google.android.recaptcha.internal;

import android.content.Context;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzcy extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzda zze;
    final /* synthetic */ String zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcy(zzda zzdaVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zze = zzdaVar;
        this.zzf = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzcy(this.zze, this.zzf, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public final Object invokeSuspend(Object obj) {
        Mutex mutex;
        String str;
        Object obj2;
        Mutex mutex2;
        zzr zzrVar;
        String str2;
        String str3;
        String str4;
        Context context;
        zzr zzrVar2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzd;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                zzda zzdaVar = this.zze;
                mutex = zzdaVar.zzo;
                str = this.zzf;
                this.zza = mutex;
                this.zzb = zzdaVar;
                this.zzc = str;
                this.zzd = 1;
                if (mutex.lock((Object) null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = zzdaVar;
            } else if (i == 1) {
                Object obj3 = this.zzc;
                obj2 = this.zzb;
                Object obj4 = this.zza;
                ResultKt.throwOnFailure(obj);
                str = obj3;
                mutex = obj4;
            } else {
                mutex2 = this.zza;
                try {
                    ResultKt.throwOnFailure(obj);
                    mutex2 = mutex2;
                    Unit unit = Unit.INSTANCE;
                    mutex2.unlock((Object) null);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    mutex2.unlock((Object) null);
                    throw th;
                }
            }
            zzrVar = ((zzda) obj2).zzf;
            String zzb = zzrVar.zzb();
            str2 = ((zzda) obj2).zzd;
            str3 = ((zzda) obj2).zzh;
            str4 = ((zzda) obj2).zzg;
            context = ((zzda) obj2).zze;
            zzrVar2 = ((zzda) obj2).zzf;
            this.zza = mutex;
            this.zzb = null;
            this.zzc = null;
            this.zzd = 2;
            if (zzbj.zzb(zzb, str2, str, str3, str4, context, zzrVar2, this) != coroutine_suspended) {
                mutex2 = mutex;
                Unit unit2 = Unit.INSTANCE;
                mutex2.unlock((Object) null);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        } catch (Throwable th2) {
            th = th2;
            mutex2 = mutex;
            mutex2.unlock((Object) null);
            throw th;
        }
    }
}
