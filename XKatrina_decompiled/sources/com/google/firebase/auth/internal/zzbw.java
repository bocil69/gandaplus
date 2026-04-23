package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TotpSecret;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbw implements TotpSecret {
    final String zza;
    final FirebaseAuth zzb;
    private final String zzc;
    private final String zzd;
    private final int zze;
    private final int zzf;
    private final long zzg;

    public zzbw(String str, String str2, int i, int i2, long j, String str3, FirebaseAuth firebaseAuth) {
        Preconditions.checkNotEmpty(str3, "sessionInfo cannot be empty.");
        Preconditions.checkNotNull(firebaseAuth, "firebaseAuth cannot be null.");
        this.zzc = Preconditions.checkNotEmpty(str, "sharedSecretKey cannot be empty. This is required to generate QR code URL.");
        this.zzd = Preconditions.checkNotEmpty(str2, "hashAlgorithm cannot be empty.");
        this.zze = i;
        this.zzf = i2;
        this.zzg = j;
        this.zza = str3;
        this.zzb = firebaseAuth;
    }

    private final void zza(String str) {
        this.zzb.getApp().getApplicationContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)).addFlags(268435456));
    }

    private static final void zzb(String str, Activity activity) {
        Preconditions.checkNotNull(activity, "Activity cannot be null.");
        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)).addFlags(268435456));
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final String generateQrCodeUrl() {
        String checkNotEmpty = Preconditions.checkNotEmpty(((FirebaseUser) Preconditions.checkNotNull(this.zzb.getCurrentUser(), "Current user cannot be null, since user is required to be logged in to enroll for TOTP MFA.")).getEmail(), "Email cannot be empty, since verified email is required to use MFA.");
        String name = this.zzb.getApp().getName();
        Preconditions.checkNotEmpty(checkNotEmpty, "accountName cannot be empty.");
        Preconditions.checkNotEmpty(name, "issuer cannot be empty.");
        return String.format(null, "otpauth://totp/%s:%s?secret=%s&issuer=%s&algorithm=%s&digits=%d", name, checkNotEmpty, this.zzc, name, this.zzd, Integer.valueOf(this.zze));
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final int getCodeIntervalSeconds() {
        return this.zzf;
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final int getCodeLength() {
        return this.zze;
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final long getEnrollmentCompletionDeadline() {
        return this.zzg;
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final String getHashAlgorithm() {
        return this.zzd;
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final String getSessionInfo() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final String getSharedSecretKey() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final void openInOtpApp(String str) {
        Preconditions.checkNotEmpty(str, "qrCodeUrl cannot be empty.");
        try {
            zza(str);
        } catch (ActivityNotFoundException unused) {
            zza("https://play.google.com/store/search?q=otpauth&c=apps");
        }
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final void openInOtpApp(String str, String str2, Activity activity) {
        Preconditions.checkNotEmpty(str, "QrCodeUrl cannot be empty.");
        Preconditions.checkNotEmpty(str2, "FallbackUrl cannot be empty.");
        Preconditions.checkNotNull(activity, "Activity cannot be null.");
        try {
            zzb(str, activity);
        } catch (ActivityNotFoundException unused) {
            zzb(str2, activity);
        }
    }

    @Override // com.google.firebase.auth.TotpSecret
    public final String generateQrCodeUrl(String str, String str2) {
        Preconditions.checkNotEmpty(str, "accountName cannot be empty.");
        Preconditions.checkNotEmpty(str2, "issuer cannot be empty.");
        return String.format(null, "otpauth://totp/%s:%s?secret=%s&issuer=%s&algorithm=%s&digits=%d", str2, str, this.zzc, str2, this.zzd, Integer.valueOf(this.zze));
    }
}
