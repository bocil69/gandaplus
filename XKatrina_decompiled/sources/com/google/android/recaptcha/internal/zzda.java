package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;
import android.webkit.WebView;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzda {
    public static final zzct zza = new zzct(null);
    public CompletableDeferred zzb;
    private final WebView zzc;
    private final String zzd;
    private final Context zze;
    private final zzr zzf;
    private final String zzg;
    private final String zzh;
    private final zzaj zzi;
    private final Map zzj;
    private final Map zzk;
    private final Map zzl;
    private final zzas zzm;
    private final zzdk zzn;
    private final Mutex zzo;
    private final zzcu zzp;

    public zzda(WebView webView, String str, Context context, zzr zzrVar, String str2, String str3, zzaj zzajVar, CoroutineScope coroutineScope) {
        this.zzc = webView;
        this.zzd = str;
        this.zze = context;
        this.zzf = zzrVar;
        this.zzg = str2;
        this.zzh = str3;
        this.zzi = zzajVar;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zzk = linkedHashMap;
        this.zzl = linkedHashMap;
        zzbc zzbcVar = new zzbc(webView, coroutineScope);
        zzp zzpVar = zzp.zza;
        this.zzm = new zzba(zzbcVar, zzp.zzc(), context);
        this.zzn = zzdk.zzc();
        this.zzo = MutexKt.Mutex$default(false, 1, (Object) null);
        zzcu zzcuVar = new zzcu(this);
        this.zzp = zzcuVar;
        this.zzj = zzq();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(zzcuVar, "RN");
        webView.setWebViewClient(new zzcs(this));
    }

    private final zzh zzo(Exception exc) {
        if (exc instanceof TimeoutCancellationException) {
            return new zzh(zzf.zzc, zzd.zzj);
        }
        return exc instanceof zzh ? (zzh) exc : new zzh(zzf.zzc, zzd.zzu);
    }

    private final void zzp(List list, zzh zzhVar) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzkw zzkwVar = (zzkw) it.next();
            zzai zzaiVar = zzai.zza;
            String str = this.zzg;
            String str2 = this.zzh;
            zzai.zzd(new zzaf(zzkwVar, str, str2, str2, null), String.valueOf(zzhVar.zzb().zza()), zzhVar.zza().zza(), this.zze, this.zzf, null);
        }
    }

    private static final Map zzq() {
        Map mutableMapOf = MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(-4, zzd.zzA), TuplesKt.to(-12, zzd.zzB), TuplesKt.to(-6, zzd.zzw), TuplesKt.to(-11, zzd.zzy), TuplesKt.to(-13, zzd.zzC), TuplesKt.to(-14, zzd.zzD), TuplesKt.to(-2, zzd.zzx), TuplesKt.to(-7, zzd.zzE), TuplesKt.to(-5, zzd.zzF), TuplesKt.to(-9, zzd.zzG), TuplesKt.to(-8, zzd.zzQ), TuplesKt.to(-15, zzd.zzz), TuplesKt.to(-1, zzd.zzH), TuplesKt.to(-3, zzd.zzJ), TuplesKt.to(-10, zzd.zzK)});
        if (Build.VERSION.SDK_INT >= 26) {
            mutableMapOf.put(-16, zzd.zzI);
        }
        if (Build.VERSION.SDK_INT >= 27) {
            mutableMapOf.put(1, zzd.zzM);
            mutableMapOf.put(2, zzd.zzN);
            mutableMapOf.put(0, zzd.zzO);
            mutableMapOf.put(3, zzd.zzP);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            mutableMapOf.put(4, zzd.zzL);
        }
        return mutableMapOf;
    }

    public final WebView zzb() {
        return this.zzc;
    }

    public final zzas zzd() {
        return this.zzm;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object zzf(com.google.android.recaptcha.RecaptchaAction r12, kotlin.coroutines.Continuation r13) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzda.zzf(com.google.android.recaptcha.RecaptchaAction, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b4, code lost:
        if (r3 > 8000) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object zzg(kotlin.coroutines.Continuation r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.google.android.recaptcha.internal.zzcx
            if (r0 == 0) goto L13
            r0 = r11
            com.google.android.recaptcha.internal.zzcx r0 = (com.google.android.recaptcha.internal.zzcx) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.zzc = r1
            goto L18
        L13:
            com.google.android.recaptcha.internal.zzcx r0 = new com.google.android.recaptcha.internal.zzcx
            r0.<init>(r10, r11)
        L18:
            java.lang.Object r11 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            com.google.android.recaptcha.internal.zzda r0 = r0.zzd
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Exception -> L2b
            goto L76
        L2b:
            r11 = move-exception
            goto L80
        L2d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L35:
            kotlin.ResultKt.throwOnFailure(r11)
            com.google.android.recaptcha.internal.zzai r11 = com.google.android.recaptcha.internal.zzai.zza
            com.google.android.recaptcha.internal.zzaf r11 = new com.google.android.recaptcha.internal.zzaf
            com.google.android.recaptcha.internal.zzkw r5 = com.google.android.recaptcha.internal.zzkw.INIT_NATIVE
            java.lang.String r6 = r10.zzg
            java.lang.String r8 = r10.zzh
            r9 = 0
            r4 = r11
            r7 = r8
            r4.<init>(r5, r6, r7, r8, r9)
            java.lang.String r2 = r10.zzd
            com.google.android.recaptcha.internal.zzs r4 = new com.google.android.recaptcha.internal.zzs
            r4.<init>()
            com.google.android.recaptcha.internal.zzai.zzb(r11, r2, r4)
            r11 = 0
            kotlinx.coroutines.CompletableDeferred r2 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r11, r3, r11)
            r10.zzb = r2
            kotlinx.coroutines.CompletableDeferred r2 = r10.zzm()
            int r2 = r2.hashCode()
            kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            com.google.android.recaptcha.internal.zzcz r2 = new com.google.android.recaptcha.internal.zzcz     // Catch: java.lang.Exception -> L7e
            r2.<init>(r10, r11)     // Catch: java.lang.Exception -> L7e
            r0.zzd = r10     // Catch: java.lang.Exception -> L7e
            r0.zzc = r3     // Catch: java.lang.Exception -> L7e
            r4 = 10000(0x2710, double:4.9407E-320)
            java.lang.Object r11 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r2, r0)     // Catch: java.lang.Exception -> L7e
            if (r11 == r1) goto L7d
            r0 = r10
        L76:
            kotlin.Result r11 = (kotlin.Result) r11     // Catch: java.lang.Exception -> L2b
            java.lang.Object r11 = r11.unbox-impl()     // Catch: java.lang.Exception -> L2b
            goto Ld5
        L7d:
            return r1
        L7e:
            r11 = move-exception
            r0 = r10
        L80:
            r11.getMessage()
            boolean r1 = r11 instanceof kotlinx.coroutines.TimeoutCancellationException
            if (r1 == 0) goto L98
            r2 = 2
            com.google.android.recaptcha.internal.zzkw[] r2 = new com.google.android.recaptcha.internal.zzkw[r2]
            r4 = 0
            com.google.android.recaptcha.internal.zzkw r5 = com.google.android.recaptcha.internal.zzkw.INIT_TOTAL
            r2[r4] = r5
            com.google.android.recaptcha.internal.zzkw r4 = com.google.android.recaptcha.internal.zzkw.INIT_NETWORK
            r2[r3] = r4
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            goto L9e
        L98:
            com.google.android.recaptcha.internal.zzkw r2 = com.google.android.recaptcha.internal.zzkw.INIT_TOTAL
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
        L9e:
            com.google.android.recaptcha.internal.zzcu r3 = r0.zzp
            java.lang.Long r3 = r3.zza()
            if (r1 != 0) goto La7
            goto Lc0
        La7:
            if (r3 != 0) goto Laa
            goto Lb6
        Laa:
            long r3 = r3.longValue()
            com.google.android.recaptcha.internal.zzr r1 = r0.zzf
            r5 = 8000(0x1f40, double:3.9525E-320)
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto Lc0
        Lb6:
            com.google.android.recaptcha.internal.zzh r11 = new com.google.android.recaptcha.internal.zzh
            com.google.android.recaptcha.internal.zzf r1 = com.google.android.recaptcha.internal.zzf.zze
            com.google.android.recaptcha.internal.zzd r3 = com.google.android.recaptcha.internal.zzd.zzT
            r11.<init>(r1, r3)
            goto Lc4
        Lc0:
            com.google.android.recaptcha.internal.zzh r11 = r0.zzo(r11)
        Lc4:
            r0.zzp(r2, r11)
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            com.google.android.recaptcha.RecaptchaException r11 = r11.zzc()
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.constructor-impl(r11)
        Ld5:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzda.zzg(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final CompletableDeferred zzm() {
        CompletableDeferred completableDeferred = this.zzb;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }
}
