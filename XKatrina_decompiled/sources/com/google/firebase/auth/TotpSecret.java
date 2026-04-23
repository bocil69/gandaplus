package com.google.firebase.auth;

import android.app.Activity;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public interface TotpSecret {
    String generateQrCodeUrl();

    String generateQrCodeUrl(String str, String str2);

    int getCodeIntervalSeconds();

    int getCodeLength();

    long getEnrollmentCompletionDeadline();

    String getHashAlgorithm();

    String getSessionInfo();

    String getSharedSecretKey();

    void openInOtpApp(String str);

    void openInOtpApp(String str, String str2, Activity activity);
}
