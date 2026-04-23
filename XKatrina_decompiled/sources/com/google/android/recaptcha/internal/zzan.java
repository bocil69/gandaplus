package com.google.android.recaptcha.internal;

import android.content.ContentValues;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzan extends SuspendLambda implements Function2 {
    final /* synthetic */ zzkx zza;
    final /* synthetic */ zzao zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzan(zzkx zzkxVar, zzao zzaoVar, Continuation continuation) {
        super(2, continuation);
        this.zza = zzkxVar;
        this.zzb = zzaoVar;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzan(this.zza, this.zzb, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        zzad zzadVar;
        zzad zzadVar2;
        zzad zzadVar3;
        zzad zzadVar4;
        zzad zzadVar5;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzkx zzkxVar = this.zza;
        zzao zzaoVar = this.zzb;
        synchronized (zzaj.class) {
            byte[] zzd = zzkxVar.zzd();
            zzae zzaeVar = new zzae(zzeb.zzg().zzi(zzd, 0, zzd.length), System.currentTimeMillis(), 0);
            zzadVar = zzaoVar.zze;
            ContentValues contentValues = new ContentValues();
            contentValues.put("ss", zzaeVar.zzc());
            contentValues.put("ts", Long.valueOf(zzaeVar.zzb()));
            zzadVar.getWritableDatabase().insert("ce", null, contentValues);
            zzadVar2 = zzaoVar.zze;
            int zzb = zzadVar2.zzb() - 500;
            if (zzb > 0) {
                zzadVar4 = zzaoVar.zze;
                List take = CollectionsKt.take(zzadVar4.zzd(), zzb);
                zzadVar5 = zzaoVar.zze;
                zzadVar5.zza(take);
            }
            zzadVar3 = zzaoVar.zze;
            if (zzadVar3.zzb() >= 20) {
                zzaoVar.zzg();
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
