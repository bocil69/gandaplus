package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzax extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzba zzb;
    final /* synthetic */ List zzc;
    final /* synthetic */ zzn zzd;
    final /* synthetic */ zzn zze;
    private /* synthetic */ Object zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzax(zzba zzbaVar, List list, zzn zznVar, zzn zznVar2, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzbaVar;
        this.zzc = list;
        this.zzd = zznVar;
        this.zze = zznVar2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        zzax zzaxVar = new zzax(this.zzb, this.zzc, this.zzd, this.zze, continuation);
        zzaxVar.zzf = obj;
        return zzaxVar;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object zzu;
        boolean zzw;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            CoroutineScope coroutineScope = (CoroutineScope) this.zzf;
            zzbl zzblVar = new zzbl(this.zzb.zzb());
            zzdk zzb = zzdk.zzb();
            while (zzblVar.zzb() >= 0 && zzblVar.zzb() < this.zzc.size() && CoroutineScopeKt.isActive(coroutineScope)) {
                zzmv zzmvVar = (zzmv) this.zzc.get(zzblVar.zzb());
                try {
                    int zzk = zzmvVar.zzk();
                    int zzg = zzmvVar.zzg();
                    List zzj = zzmvVar.zzj();
                    zzw = this.zzb.zzw(zzmvVar, zzblVar);
                    if (!zzw) {
                        zzdk zzb2 = zzdk.zzb();
                        int i2 = zzk - 2;
                        if (i2 == 7) {
                            zzba.zzo(this.zzb, zzg, zzj);
                        } else if (i2 == 15) {
                            zzba.zzi(this.zzb, zzj);
                        } else if (i2 == 30) {
                            zzba.zzh(this.zzb, zzg, zzj);
                        } else if (i2 == 40) {
                            zzba.zzr(this.zzb, this.zzd, zzg, zzj);
                        } else {
                            switch (i2) {
                                case 10:
                                    zzba.zzm(this.zzb, zzg, zzj);
                                    break;
                                case 11:
                                    zzba.zzn(this.zzb, zzg, zzj);
                                    break;
                                case 12:
                                    zzba.zzp(this.zzb, zzj);
                                    break;
                                case 13:
                                    zzba.zzq(this.zzb, zzj);
                                    break;
                                default:
                                    switch (i2) {
                                        case 18:
                                            zzba.zzk(this.zzb, zzg, zzj);
                                            break;
                                        case 19:
                                            zzba.zzl(this.zzb, zzg, zzj);
                                            break;
                                        case 20:
                                            zzba.zzj(this.zzb, zzj);
                                            break;
                                        default:
                                            throw new zzt(5, 2, null);
                                    }
                            }
                        }
                        zzb2.zzf();
                        long zza = zzb2.zza(TimeUnit.MICROSECONDS);
                        zzj zzjVar = zzj.zza;
                        zzj.zza(zzms.zza(zzk), zza);
                        Boxing.boxLong(zza);
                        Boxing.boxInt(zzg);
                        CollectionsKt.joinToString$default(zzj, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new zzaw(this.zzb), 31, (Object) null);
                        zzblVar.zzg(zzblVar.zzb() + 1);
                    }
                } catch (Exception e) {
                    zzba zzbaVar = this.zzb;
                    String zzd = zzblVar.zzd();
                    zzn zznVar = this.zzd;
                    zzn zznVar2 = this.zze;
                    int zzb3 = zzblVar.zzb();
                    this.zza = 1;
                    zzu = zzbaVar.zzu(e, zzd, zznVar, zznVar2, zzb3, this);
                    if (zzu == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            zzb.zzf();
            Boxing.boxLong(zzb.zza(TimeUnit.MICROSECONDS));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
