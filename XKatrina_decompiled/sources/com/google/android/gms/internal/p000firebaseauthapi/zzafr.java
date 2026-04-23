package com.google.android.gms.internal.p000firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafr  reason: invalid package */
/* loaded from: classes.dex */
public final class zzafr extends BroadcastReceiver {
    final /* synthetic */ zzaft zza;
    private final String zzb;

    public zzafr(zzaft zzaftVar, String str) {
        this.zza = zzaftVar;
        this.zzb = str;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        HashMap hashMap;
        Logger logger;
        Logger logger2;
        if ("com.google.android.gms.auth.api.phone.SMS_RETRIEVED".equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode() == 0) {
                String str = (String) extras.get("com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE");
                zzaft zzaftVar = this.zza;
                String str2 = this.zzb;
                hashMap = zzaftVar.zzd;
                zzafs zzafsVar = (zzafs) hashMap.get(str2);
                if (zzafsVar == null) {
                    logger2 = zzaft.zza;
                    logger2.e("Verification code received with no active retrieval session.", new Object[0]);
                } else {
                    Matcher matcher = Pattern.compile("(?<!\\d)\\d{6}(?!\\d)").matcher(str);
                    zzafsVar.zze = matcher.find() ? matcher.group() : null;
                    if (zzafsVar.zze == null) {
                        logger = zzaft.zza;
                        logger.e("Unable to extract verification code.", new Object[0]);
                    } else if (!zzac.zzd(zzafsVar.zzd)) {
                        zzaft.zzd(this.zza, this.zzb);
                    }
                }
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
