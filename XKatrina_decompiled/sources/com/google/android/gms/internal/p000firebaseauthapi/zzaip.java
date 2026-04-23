package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.internal.p000firebaseauthapi.zzaio;
import com.google.android.gms.internal.p000firebaseauthapi.zzaip;
import java.io.IOException;
import java.io.OutputStream;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaip  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzaip<MessageType extends zzaip<MessageType, BuilderType>, BuilderType extends zzaio<MessageType, BuilderType>> implements zzalp {
    protected int zza = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzn(zzamb zzambVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzalp
    public final zzajf zzo() {
        try {
            int zzs = zzs();
            zzajf zzajfVar = zzajf.zzb;
            byte[] bArr = new byte[zzs];
            zzajs zzC = zzajs.zzC(bArr, 0, zzs);
            zzJ(zzC);
            zzC.zzD();
            return new zzajc(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final void zzp(OutputStream outputStream) throws IOException {
        int zzs = zzs();
        int i = zzajs.zzf;
        if (zzs > 4096) {
            zzs = 4096;
        }
        zzajq zzajqVar = new zzajq(outputStream, zzs);
        zzJ(zzajqVar);
        zzajqVar.zzI();
    }

    public final byte[] zzq() {
        try {
            int zzs = zzs();
            byte[] bArr = new byte[zzs];
            zzajs zzC = zzajs.zzC(bArr, 0, zzs);
            zzJ(zzC);
            zzC.zzD();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
