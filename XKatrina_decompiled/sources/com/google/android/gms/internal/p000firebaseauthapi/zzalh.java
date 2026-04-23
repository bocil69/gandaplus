package com.google.android.gms.internal.p000firebaseauthapi;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzalh implements zzamc {
    private static final zzaln zza = new zzalf();
    private final zzaln zzb;

    public zzalh() {
        zzaln zzalnVar;
        zzaln[] zzalnVarArr = new zzaln[2];
        zzalnVarArr[0] = zzakf.zza();
        try {
            zzalnVar = (zzaln) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzalnVar = zza;
        }
        zzalnVarArr[1] = zzalnVar;
        zzalg zzalgVar = new zzalg(zzalnVarArr);
        byte[] bArr = zzakq.zzd;
        this.zzb = zzalgVar;
    }

    private static boolean zzb(zzalm zzalmVar) {
        return zzalmVar.zzc() + (-1) != 1;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzamc
    public final zzamb zza(Class cls) {
        zzamd.zzr(cls);
        zzalm zzb = this.zzb.zzb(cls);
        if (!zzb.zzb()) {
            if (zzakk.class.isAssignableFrom(cls)) {
                if (zzb(zzb)) {
                    return zzals.zzl(cls, zzb, zzalv.zzb(), zzald.zze(), zzamd.zzn(), zzaka.zzb(), zzall.zzb());
                }
                return zzals.zzl(cls, zzb, zzalv.zzb(), zzald.zze(), zzamd.zzn(), null, zzall.zzb());
            } else if (zzb(zzb)) {
                return zzals.zzl(cls, zzb, zzalv.zza(), zzald.zzd(), zzamd.zzm(), zzaka.zza(), zzall.zza());
            } else {
                return zzals.zzl(cls, zzb, zzalv.zza(), zzald.zzd(), zzamd.zzm(), null, zzall.zza());
            }
        } else if (zzakk.class.isAssignableFrom(cls)) {
            return zzalt.zzc(zzamd.zzn(), zzaka.zzb(), zzb.zza());
        } else {
            return zzalt.zzc(zzamd.zzm(), zzaka.zza(), zzb.zza());
        }
    }
}
