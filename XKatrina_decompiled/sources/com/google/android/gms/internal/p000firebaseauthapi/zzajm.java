package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzajm implements zzama {
    private final zzajl zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzajm(zzajl zzajlVar) {
        byte[] bArr = zzakq.zzd;
        this.zza = zzajlVar;
        zzajlVar.zzc = this;
    }

    private final void zzP(Object obj, zzamb zzambVar, zzajx zzajxVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzambVar.zzh(obj, this, zzajxVar);
            if (this.zzb == this.zzc) {
                return;
            }
            throw zzaks.zzg();
        } finally {
            this.zzc = i;
        }
    }

    private final void zzQ(Object obj, zzamb zzambVar, zzajx zzajxVar) throws IOException {
        zzajl zzajlVar;
        zzajl zzajlVar2 = this.zza;
        int zzn = zzajlVar2.zzn();
        if (zzajlVar2.zza >= zzajlVar2.zzb) {
            throw new zzaks("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zze = this.zza.zze(zzn);
        this.zza.zza++;
        zzambVar.zzh(obj, this, zzajxVar);
        this.zza.zzz(0);
        zzajlVar.zza--;
        this.zza.zzA(zze);
    }

    private final void zzR(int i) throws IOException {
        if (this.zza.zzd() != i) {
            throw zzaks.zzj();
        }
    }

    private final void zzS(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzaks.zza();
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzaks.zzg();
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzaks.zzg();
        }
    }

    public static zzajm zzq(zzajl zzajlVar) {
        zzajm zzajmVar = zzajlVar.zzc;
        return zzajmVar != null ? zzajmVar : new zzajm(zzajlVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzA(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzale)) {
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzd() < zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzale zzaleVar = (zzale) list;
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                zzaleVar.zzf(this.zza.zzo());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzU(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzaleVar.zzf(this.zza.zzo());
            } while (this.zza.zzd() < zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzB(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzake)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                } while (this.zza.zzd() < zzd);
                return;
            } else if (i != 5) {
                throw zzaks.zza();
            } else {
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            }
        }
        zzake zzakeVar = (zzake) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzT(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzakeVar.zze(this.zza.zzc());
            } while (this.zza.zzd() < zzd2);
        } else if (i2 != 5) {
            throw zzaks.zza();
        } else {
            do {
                zzakeVar.zze(this.zza.zzc());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    @Deprecated
    public final void zzC(List list, zzamb zzambVar, zzajx zzajxVar) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) != 3) {
            throw zzaks.zza();
        }
        do {
            Object zze = zzambVar.zze();
            zzP(zze, zzambVar, zzajxVar);
            zzambVar.zzf(zze);
            list.add(zze);
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            }
            zzm = this.zza.zzm();
        } while (zzm == i);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzD(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzakl)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzakl zzaklVar = (zzakl) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaklVar.zzf(this.zza.zzh());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaklVar.zzf(this.zza.zzh());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzE(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzale)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzale zzaleVar = (zzale) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaleVar.zzf(this.zza.zzp());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaleVar.zzf(this.zza.zzp());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzF(List list, zzamb zzambVar, zzajx zzajxVar) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) != 2) {
            throw zzaks.zza();
        }
        do {
            Object zze = zzambVar.zze();
            zzQ(zze, zzambVar, zzajxVar);
            zzambVar.zzf(zze);
            list.add(zze);
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            }
            zzm = this.zza.zzm();
        } while (zzm == i);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzG(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzakl)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                } while (this.zza.zzd() < zzd);
                return;
            } else if (i != 5) {
                throw zzaks.zza();
            } else {
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            }
        }
        zzakl zzaklVar = (zzakl) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzT(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzaklVar.zzf(this.zza.zzk());
            } while (this.zza.zzd() < zzd2);
        } else if (i2 != 5) {
            throw zzaks.zza();
        } else {
            do {
                zzaklVar.zzf(this.zza.zzk());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzH(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzale)) {
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                } while (this.zza.zzd() < zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzale zzaleVar = (zzale) list;
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                zzaleVar.zzf(this.zza.zzt());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzU(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzaleVar.zzf(this.zza.zzt());
            } while (this.zza.zzd() < zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzI(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzakl)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzl()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzl()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzakl zzaklVar = (zzakl) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaklVar.zzf(this.zza.zzl());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaklVar.zzf(this.zza.zzl());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzJ(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzale)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzale zzaleVar = (zzale) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaleVar.zzf(this.zza.zzu());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaleVar.zzf(this.zza.zzu());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    public final void zzK(List list, boolean z) throws IOException {
        int zzm;
        int zzm2;
        if ((this.zzb & 7) != 2) {
            throw zzaks.zza();
        }
        if (!(list instanceof zzakx) || z) {
            do {
                list.add(z ? zzs() : zzr());
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        zzakx zzakxVar = (zzakx) list;
        do {
            zzakxVar.zzi(zzp());
            if (this.zza.zzC()) {
                return;
            }
            zzm2 = this.zza.zzm();
        } while (zzm2 == this.zzb);
        this.zzd = zzm2;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzL(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzakl)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzakl zzaklVar = (zzakl) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaklVar.zzf(this.zza.zzn());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaklVar.zzf(this.zza.zzn());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzM(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzale)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzv()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzv()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzale zzaleVar = (zzale) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaleVar.zzf(this.zza.zzv());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaleVar.zzf(this.zza.zzv());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzD();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final boolean zzO() throws IOException {
        int i;
        if (this.zza.zzC() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzE(i);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final double zza() throws IOException {
        zzS(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final float zzb() throws IOException {
        zzS(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzm();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zze() throws IOException {
        zzS(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zzf() throws IOException {
        zzS(5);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zzg() throws IOException {
        zzS(0);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zzh() throws IOException {
        zzS(5);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zzi() throws IOException {
        zzS(0);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final int zzj() throws IOException {
        zzS(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final long zzk() throws IOException {
        zzS(1);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final long zzl() throws IOException {
        zzS(0);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final long zzm() throws IOException {
        zzS(1);
        return this.zza.zzt();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final long zzn() throws IOException {
        zzS(0);
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final long zzo() throws IOException {
        zzS(0);
        return this.zza.zzv();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final zzajf zzp() throws IOException {
        zzS(2);
        return this.zza.zzw();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final String zzr() throws IOException {
        zzS(2);
        return this.zza.zzx();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final String zzs() throws IOException {
        zzS(2);
        return this.zza.zzy();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzt(Object obj, zzamb zzambVar, zzajx zzajxVar) throws IOException {
        zzS(3);
        zzP(obj, zzambVar, zzajxVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzu(Object obj, zzamb zzambVar, zzajx zzajxVar) throws IOException {
        zzS(2);
        zzQ(obj, zzambVar, zzajxVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzv(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzaiu)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzD()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Boolean.valueOf(this.zza.zzD()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzaiu zzaiuVar = (zzaiu) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaiuVar.zze(this.zza.zzD());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaiuVar.zze(this.zza.zzD());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzw(List list) throws IOException {
        int zzm;
        if ((this.zzb & 7) != 2) {
            throw zzaks.zza();
        }
        do {
            list.add(zzp());
            if (this.zza.zzC()) {
                return;
            }
            zzm = this.zza.zzm();
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzx(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzaju)) {
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                } while (this.zza.zzd() < zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzaju zzajuVar = (zzaju) list;
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                zzajuVar.zze(this.zza.zzb());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzU(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzajuVar.zze(this.zza.zzb());
            } while (this.zza.zzd() < zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzy(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzakl)) {
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            } else if (i == 2) {
                zzajl zzajlVar = this.zza;
                int zzd = zzajlVar.zzd() + zzajlVar.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzaks.zza();
            }
        }
        zzakl zzaklVar = (zzakl) list;
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                zzaklVar.zzf(this.zza.zzf());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        } else if (i2 == 2) {
            zzajl zzajlVar2 = this.zza;
            int zzd2 = zzajlVar2.zzd() + zzajlVar2.zzn();
            do {
                zzaklVar.zzf(this.zza.zzf());
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzaks.zza();
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzama
    public final void zzz(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzakl)) {
            int i = this.zzb & 7;
            if (i == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzd() < zzd);
                return;
            } else if (i != 5) {
                throw zzaks.zza();
            } else {
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            }
        }
        zzakl zzaklVar = (zzakl) list;
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int zzn2 = this.zza.zzn();
            zzT(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzaklVar.zzf(this.zza.zzg());
            } while (this.zza.zzd() < zzd2);
        } else if (i2 != 5) {
            throw zzaks.zza();
        } else {
            do {
                zzaklVar.zzf(this.zza.zzg());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        }
    }
}
