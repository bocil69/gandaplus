package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.List;
import java.util.Timer;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzao implements zzaj {
    public static final zzak zza = new zzak(null);
    private static Timer zzb;
    private final zzap zzc;
    private final CoroutineScope zzd;
    private final zzad zze;

    public /* synthetic */ zzao(Context context, zzap zzapVar, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        zzad zzadVar;
        zzp zzpVar = zzp.zza;
        CoroutineScope zza2 = zzp.zza();
        this.zzc = zzapVar;
        this.zzd = zza2;
        zzadVar = zzad.zzb;
        zzadVar = zzadVar == null ? new zzad(context, null) : zzadVar;
        zzad.zzb = zzadVar;
        this.zze = zzadVar;
        zzh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        for (List<zzae> list : CollectionsKt.windowed(this.zze.zzd(), 20, 20, true)) {
            zzkz zzf = zzla.zzf();
            for (zzae zzaeVar : list) {
                zzf.zzd(zzkx.zzG(zzeb.zzg().zzj(zzaeVar.zzc())));
            }
            if (this.zzc.zza(((zzla) zzf.zzj()).zzd())) {
                for (zzae zzaeVar2 : list) {
                    this.zze.zzf(zzaeVar2);
                }
            }
        }
    }

    private final void zzh() {
        if (zzb == null) {
            Timer timer = new Timer();
            zzb = timer;
            timer.schedule(new zzal(this), 120000L, 120000L);
        }
    }

    public final void zzf(zzkx zzkxVar) {
        BuildersKt.launch$default(this.zzd, (CoroutineContext) null, (CoroutineStart) null, new zzan(zzkxVar, this, null), 3, (Object) null);
        zzh();
    }
}
