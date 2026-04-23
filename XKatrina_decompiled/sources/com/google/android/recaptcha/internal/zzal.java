package com.google.android.recaptcha.internal;

import java.util.TimerTask;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzal extends TimerTask {
    final /* synthetic */ zzao zza;

    public zzal(zzao zzaoVar) {
        this.zza = zzaoVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        CoroutineScope coroutineScope;
        coroutineScope = this.zza.zzd;
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new zzam(this.zza, null), 3, (Object) null);
    }
}
