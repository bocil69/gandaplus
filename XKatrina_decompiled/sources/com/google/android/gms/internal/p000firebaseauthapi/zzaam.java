package com.google.android.gms.internal.p000firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.zze;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaam  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaam implements zzafe {
    final /* synthetic */ zzafd zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ Boolean zzd;
    final /* synthetic */ zze zze;
    final /* synthetic */ zzadx zzf;
    final /* synthetic */ zzahb zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaam(zzabz zzabzVar, zzafd zzafdVar, String str, String str2, Boolean bool, zze zzeVar, zzadx zzadxVar, zzahb zzahbVar) {
        this.zza = zzafdVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zzeVar;
        this.zzf = zzadxVar;
        this.zzg = zzahbVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb = ((zzagr) obj).zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        int i = 0;
        zzags zzagsVar = (zzags) zzb.get(0);
        zzahh zzl = zzagsVar.zzl();
        List zzc = zzl != null ? zzl.zzc() : null;
        if (zzc != null && !zzc.isEmpty()) {
            if (TextUtils.isEmpty(this.zzb)) {
                ((zzahg) zzc.get(0)).zzh(this.zzc);
            } else {
                while (true) {
                    if (i >= zzc.size()) {
                        break;
                    } else if (((zzahg) zzc.get(i)).zzf().equals(this.zzb)) {
                        ((zzahg) zzc.get(i)).zzh(this.zzc);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        zzagsVar.zzh(this.zzd.booleanValue());
        zzagsVar.zze(this.zze);
        this.zzf.zzk(this.zzg, zzagsVar);
    }
}
