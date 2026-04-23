package com.google.android.recaptcha.internal;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzp {
    public static final zzp zza = new zzp();
    private static final CoroutineScope zzb = CoroutineScopeKt.MainScope();
    private static final CoroutineScope zzc;
    private static final CoroutineScope zzd;

    static {
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(ThreadPoolDispatcherKt.newSingleThreadContext("reCaptcha"));
        BuildersKt.launch$default(CoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new zzo(null), 3, (Object) null);
        zzc = CoroutineScope;
        zzd = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    private zzp() {
    }

    public static final CoroutineScope zza() {
        return zzd;
    }

    public static final CoroutineScope zzb() {
        return zzb;
    }

    public static final CoroutineScope zzc() {
        return zzc;
    }
}
