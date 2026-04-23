package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.internal.p000firebaseauthapi.zzahb;
import com.google.firebase.FirebaseApp;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbv {
    private volatile int zza;
    private final zzam zzb;
    private volatile boolean zzc;

    public zzbv(FirebaseApp firebaseApp) {
        Context applicationContext = firebaseApp.getApplicationContext();
        zzam zzamVar = new zzam(firebaseApp);
        this.zzc = false;
        this.zza = 0;
        this.zzb = zzamVar;
        BackgroundDetector.initialize((Application) applicationContext.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zzbu(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzg() {
        return this.zza > 0 && !this.zzc;
    }

    public final void zzc() {
        this.zzb.zzb();
    }

    public final void zze(zzahb zzahbVar) {
        if (zzahbVar == null) {
            return;
        }
        long zzb = zzahbVar.zzb();
        if (zzb <= 0) {
            zzb = 3600;
        }
        zzam zzamVar = this.zzb;
        zzamVar.zza = zzahbVar.zzc() + (zzb * 1000);
        zzamVar.zzb = -1L;
        if (zzg()) {
            this.zzb.zzc();
        }
    }

    public final void zzd(int i) {
        if (i > 0 && this.zza == 0) {
            this.zza = i;
            if (zzg()) {
                this.zzb.zzc();
            }
        } else if (i == 0 && this.zza != 0) {
            this.zzb.zzb();
        }
        this.zza = i;
    }
}
