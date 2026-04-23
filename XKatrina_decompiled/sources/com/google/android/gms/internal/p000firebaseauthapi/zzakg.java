package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.internal.p000firebaseauthapi.zzakg;
import com.google.android.gms.internal.p000firebaseauthapi.zzakk;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakg  reason: invalid package */
/* loaded from: classes.dex */
public class zzakg<MessageType extends zzakk<MessageType, BuilderType>, BuilderType extends zzakg<MessageType, BuilderType>> extends zzaio<MessageType, BuilderType> {
    protected zzakk zza;
    private final zzakk zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzakg(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzL()) {
            this.zza = messagetype.zzw();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zzalx.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzalq
    public final /* synthetic */ zzalp zzM() {
        throw null;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaio
    /* renamed from: zzg */
    public final zzakg zzf() {
        zzakg zzakgVar = (zzakg) this.zzb.zzj(5, null, null);
        zzakgVar.zza = zzk();
        return zzakgVar;
    }

    public final zzakg zzh(zzakk zzakkVar) {
        if (!this.zzb.equals(zzakkVar)) {
            if (!this.zza.zzL()) {
                zzn();
            }
            zza(this.zza, zzakkVar);
        }
        return this;
    }

    public final MessageType zzi() {
        MessageType zzk = zzk();
        if (zzk.zzK()) {
            return zzk;
        }
        throw new zzamu(zzk);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzalo
    /* renamed from: zzj */
    public MessageType zzk() {
        if (this.zza.zzL()) {
            this.zza.zzF();
            return (MessageType) this.zza;
        }
        return (MessageType) this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzm() {
        if (this.zza.zzL()) {
            return;
        }
        zzn();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzn() {
        zzakk zzw = this.zzb.zzw();
        zza(zzw, this.zza);
        this.zza = zzw;
    }
}
