package com.google.firebase.auth.internal;

import com.google.android.gms.internal.p000firebaseauthapi.zzahk;
import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzo implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final ActionCodeInfo zzd;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public zzo(zzahk zzahkVar) {
        char c;
        this.zzb = zzahkVar.zzh() ? zzahkVar.zzd() : zzahkVar.zzc();
        this.zzc = zzahkVar.zzc();
        ActionCodeInfo actionCodeInfo = null;
        if (!zzahkVar.zzi()) {
            this.zza = 3;
            this.zzd = null;
            return;
        }
        String zze = zzahkVar.zze();
        switch (zze.hashCode()) {
            case -1874510116:
                if (zze.equals("REVERT_SECOND_FACTOR_ADDITION")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1452371317:
                if (zze.equals("PASSWORD_RESET")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1341836234:
                if (zze.equals("VERIFY_EMAIL")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1099157829:
                if (zze.equals("VERIFY_AND_CHANGE_EMAIL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 870738373:
                if (zze.equals("EMAIL_SIGNIN")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 970484929:
                if (zze.equals("RECOVER_EMAIL")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        int i = c != 0 ? c != 1 ? c != 2 ? c != 3 ? c != 4 ? c != 5 ? 3 : 6 : 2 : 5 : 4 : 1 : 0;
        this.zza = i;
        if (i == 4 || i == 3) {
            this.zzd = null;
            return;
        }
        if (zzahkVar.zzg()) {
            actionCodeInfo = new zzn(zzahkVar.zzc(), zzbc.zza(zzahkVar.zzb()));
        } else if (zzahkVar.zzh()) {
            actionCodeInfo = new zzl(zzahkVar.zzd(), zzahkVar.zzc());
        } else if (zzahkVar.zzf()) {
            actionCodeInfo = new zzm(zzahkVar.zzc());
        }
        this.zzd = actionCodeInfo;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final String getData(int i) {
        if (this.zza == 4) {
            return null;
        }
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return this.zzc;
        }
        return this.zzb;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final ActionCodeInfo getInfo() {
        return this.zzd;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final int getOperation() {
        return this.zza;
    }
}
