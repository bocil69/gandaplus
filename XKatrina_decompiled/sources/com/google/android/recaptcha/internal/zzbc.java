package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbc {
    private final WebView zza;
    private final CoroutineScope zzb;

    public zzbc(WebView webView, CoroutineScope coroutineScope) {
        this.zza = webView;
        this.zzb = coroutineScope;
    }

    public final void zzb(String str, String... strArr) {
        BuildersKt.launch$default(this.zzb, (CoroutineContext) null, (CoroutineStart) null, new zzbb(strArr, this, str, null), 3, (Object) null);
    }
}
