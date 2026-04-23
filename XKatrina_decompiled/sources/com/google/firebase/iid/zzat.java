package com.google.firebase.iid;

import android.os.Looper;
import android.os.Message;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzat extends com.google.android.gms.internal.firebase_messaging.zze {
    private final /* synthetic */ zzau zzcw;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzat(zzau zzauVar, Looper looper) {
        super(looper);
        this.zzcw = zzauVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zzcw.zzb(message);
    }
}
