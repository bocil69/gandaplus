package com.google.firebase.iid;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzt implements Continuation<Bundle, String> {
    private final /* synthetic */ zzs zzbs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(zzs zzsVar) {
        this.zzbs = zzsVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ String then(@NonNull Task<Bundle> task) throws Exception {
        String zza;
        zzs zzsVar = this.zzbs;
        zza = zzs.zza(task.getResult(IOException.class));
        return zza;
    }
}
