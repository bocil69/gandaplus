package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzby  reason: invalid package */
/* loaded from: classes.dex */
public final class zzby {
    private final zzwv zza;
    private final List zzb;
    private final zzro zzc = zzro.zza;

    private zzby(zzwv zzwvVar, List list) {
        this.zza = zzwvVar;
        this.zzb = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final zzby zza(zzwv zzwvVar) throws GeneralSecurityException {
        zzl(zzwvVar);
        return new zzby(zzwvVar, zzk(zzwvVar));
    }

    public static final zzby zzh(zzbe zzbeVar, zzbd zzbdVar) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[0];
        zzva zza = zzbeVar.zza();
        if (zza == null || zza.zzd().zzd() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        try {
            zzwv zzg = zzwv.zzg(zzbdVar.zza(zza.zzd().zzq(), bArr), zzajx.zza());
            zzl(zzg);
            return zza(zzg);
        } catch (zzaks unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static zzoo zzi(zzwu zzwuVar) {
        try {
            return zzoo.zza(zzwuVar.zzb().zzf(), zzwuVar.zzb().zze(), zzwuVar.zzb().zzb(), zzwuVar.zze(), zzwuVar.zze() == zzxo.RAW ? null : Integer.valueOf(zzwuVar.zza()));
        } catch (GeneralSecurityException e) {
            throw new zzpc("Creating a protokey serialization failed", e);
        }
    }

    @Nullable
    private static Object zzj(zzmw zzmwVar, zzwu zzwuVar, Class cls) throws GeneralSecurityException {
        try {
            return zzcq.zzd(zzwuVar.zzb(), cls);
        } catch (UnsupportedOperationException unused) {
            return null;
        } catch (GeneralSecurityException e) {
            if (e.getMessage().contains("No key manager found for key type ") || e.getMessage().contains(" not supported by key manager of type ")) {
                return null;
            }
            throw e;
        }
    }

    private static List zzk(zzwv zzwvVar) {
        zzbu zzbuVar;
        ArrayList arrayList = new ArrayList(zzwvVar.zza());
        for (zzwu zzwuVar : zzwvVar.zzh()) {
            int zza = zzwuVar.zza();
            try {
                zzbn zza2 = zznt.zzc().zza(zzi(zzwuVar), zzcr.zza());
                int zzk = zzwuVar.zzk() - 2;
                if (zzk != 1) {
                    if (zzk != 2) {
                        if (zzk == 3) {
                            zzbuVar = zzbu.zzc;
                        } else {
                            throw new GeneralSecurityException("Unknown key status");
                            break;
                        }
                    } else {
                        zzbuVar = zzbu.zzb;
                    }
                } else {
                    zzbuVar = zzbu.zza;
                }
                arrayList.add(new zzbx(zza2, zzbuVar, zza, zza == zzwvVar.zzb(), null));
            } catch (GeneralSecurityException unused) {
                arrayList.add(null);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static void zzl(zzwv zzwvVar) throws GeneralSecurityException {
        if (zzwvVar == null || zzwvVar.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    @Nullable
    private static final Object zzm(zzmw zzmwVar, zzbn zzbnVar, Class cls) throws GeneralSecurityException {
        try {
            return zznq.zza().zzc(zzbnVar, cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    public final String toString() {
        return zzct.zza(this.zza).toString();
    }

    public final zzby zzb() throws GeneralSecurityException {
        zzwv zzwvVar = this.zza;
        if (zzwvVar == null) {
            throw new GeneralSecurityException("cleartext keyset is not available");
        }
        zzws zzc = zzwv.zzc();
        for (zzwu zzwuVar : zzwvVar.zzh()) {
            zzwi zzb = zzwuVar.zzb();
            if (zzb.zzb() == zzwh.ASYMMETRIC_PRIVATE) {
                String zzf = zzb.zzf();
                zzajf zze = zzb.zze();
                zzbo zza = zzcq.zza(zzf);
                if (zza instanceof zzcn) {
                    zzwi zzd = ((zzcn) zza).zzd(zze);
                    String zzf2 = zzd.zzf();
                    zzcq.zza(zzf2).zzb(zzd.zze());
                    zzwt zzwtVar = (zzwt) zzwuVar.zzu();
                    zzwtVar.zza(zzd);
                    zzc.zzb((zzwu) zzwtVar.zzi());
                } else {
                    throw new GeneralSecurityException("manager for key type " + zzf + " is not a PrivateKeyManager");
                }
            } else {
                throw new GeneralSecurityException("The keyset contains a non-private key");
            }
        }
        zzc.zzc(this.zza.zzb());
        return zza((zzwv) zzc.zzi());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzwv zzc() {
        return this.zza;
    }

    public final zzxa zzd() {
        return zzct.zza(this.zza);
    }

    public final Object zze(zzbh zzbhVar, Class cls) throws GeneralSecurityException {
        Class zzc = zzcq.zzc(cls);
        if (zzc != null) {
            zzwv zzwvVar = this.zza;
            Charset charset = zzct.zza;
            int zzb = zzwvVar.zzb();
            int i = 0;
            boolean z = false;
            boolean z2 = true;
            for (zzwu zzwuVar : zzwvVar.zzh()) {
                if (zzwuVar.zzk() == 3) {
                    if (zzwuVar.zzi()) {
                        if (zzwuVar.zze() != zzxo.UNKNOWN_PREFIX) {
                            if (zzwuVar.zzk() != 2) {
                                if (zzwuVar.zza() == zzb) {
                                    if (z) {
                                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                                    }
                                    z = true;
                                }
                                z2 &= zzwuVar.zzb().zzb() == zzwh.ASYMMETRIC_PUBLIC;
                                i++;
                            } else {
                                throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(zzwuVar.zza())));
                            }
                        } else {
                            throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(zzwuVar.zza())));
                        }
                    } else {
                        throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(zzwuVar.zza())));
                    }
                }
            }
            if (i != 0) {
                if (z || z2) {
                    zzcg zzcgVar = new zzcg(zzc, null);
                    zzcgVar.zzc(this.zzc);
                    for (int i2 = 0; i2 < this.zza.zza(); i2++) {
                        zzwu zzd = this.zza.zzd(i2);
                        if (zzd.zzk() == 3) {
                            zzmw zzmwVar = (zzmw) zzbhVar;
                            Object zzj = zzj(zzmwVar, zzd, zzc);
                            Object zzm = this.zzb.get(i2) != null ? zzm(zzmwVar, ((zzbx) this.zzb.get(i2)).zza(), zzc) : null;
                            if (zzm != null || zzj != null) {
                                if (zzd.zza() == this.zza.zzb()) {
                                    zzcgVar.zzb(zzm, zzj, zzd);
                                } else {
                                    zzcgVar.zza(zzm, zzj, zzd);
                                }
                            } else {
                                String obj = zzc.toString();
                                String zzf = zzd.zzb().zzf();
                                throw new GeneralSecurityException("Unable to get primitive " + obj + " for key of type " + zzf);
                            }
                        }
                    }
                    return zznq.zza().zzd(zzcgVar.zzd(), cls);
                }
                throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
            }
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(cls.getName())));
    }

    public final void zzf(zzca zzcaVar, zzbd zzbdVar) throws GeneralSecurityException, IOException {
        zzwv zzwvVar = this.zza;
        byte[] bArr = new byte[0];
        byte[] zzb = zzbdVar.zzb(zzwvVar.zzq(), bArr);
        try {
            if (!zzwv.zzg(zzbdVar.zza(zzb, bArr), zzajx.zza()).equals(zzwvVar)) {
                throw new GeneralSecurityException("cannot encrypt keyset");
            }
            int length = zzb.length;
            zzuz zza = zzva.zza();
            zza.zza(zzajf.zzn(zzb, 0, length));
            zza.zzb(zzct.zza(zzwvVar));
            zzcaVar.zzb((zzva) zza.zzi());
        } catch (zzaks unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzg(com.google.android.gms.internal.p000firebaseauthapi.zzca r5) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r4 = this;
            com.google.android.gms.internal.firebase-auth-api.zzwv r0 = r4.zza
            java.util.List r0 = r0.zzh()
            java.util.Iterator r0 = r0.iterator()
        La:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L64
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.firebase-auth-api.zzwu r1 = (com.google.android.gms.internal.p000firebaseauthapi.zzwu) r1
            com.google.android.gms.internal.firebase-auth-api.zzwi r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwh r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwh r3 = com.google.android.gms.internal.p000firebaseauthapi.zzwh.UNKNOWN_KEYMATERIAL
            if (r2 == r3) goto L3b
            com.google.android.gms.internal.firebase-auth-api.zzwi r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwh r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwh r3 = com.google.android.gms.internal.p000firebaseauthapi.zzwh.SYMMETRIC
            if (r2 == r3) goto L3b
            com.google.android.gms.internal.firebase-auth-api.zzwi r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwh r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwh r3 = com.google.android.gms.internal.p000firebaseauthapi.zzwh.ASYMMETRIC_PRIVATE
            if (r2 == r3) goto L3b
            goto La
        L3b:
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r2 = 0
            com.google.android.gms.internal.firebase-auth-api.zzwi r3 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzwh r3 = r3.zzb()
            java.lang.String r3 = r3.name()
            r0[r2] = r3
            r2 = 1
            com.google.android.gms.internal.firebase-auth-api.zzwi r1 = r1.zzb()
            java.lang.String r1 = r1.zzf()
            r0[r2] = r1
            java.lang.String r1 = "keyset contains key material of type %s for type url %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            r5.<init>(r0)
            throw r5
        L64:
            com.google.android.gms.internal.firebase-auth-api.zzwv r0 = r4.zza
            r5.zzc(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p000firebaseauthapi.zzby.zzg(com.google.android.gms.internal.firebase-auth-api.zzca):void");
    }
}
