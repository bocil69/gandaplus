package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p000firebaseauthapi.zzaic;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zze extends OAuthCredential {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final zzaic zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(String str, String str2, String str3, zzaic zzaicVar, String str4, String str5, String str6) {
        this.zza = com.google.android.gms.internal.p000firebaseauthapi.zzac.zzc(str);
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzaicVar;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
    }

    public static zze zzb(zzaic zzaicVar) {
        Preconditions.checkNotNull(zzaicVar, "Must specify a non-null webSignInCredential");
        return new zze(null, null, null, zzaicVar, null, null, null);
    }

    public static zze zzc(String str, String str2, String str3, String str4, String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zze(str, str2, str3, null, str4, str5, null);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public static zzaic zzd(zze zzeVar, String str) {
        Preconditions.checkNotNull(zzeVar);
        zzaic zzaicVar = zzeVar.zzd;
        return zzaicVar != null ? zzaicVar : new zzaic(zzeVar.zzb, zzeVar.zzc, zzeVar.zza, null, zzeVar.zzf, null, str, zzeVar.zze, zzeVar.zzg);
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public final String getAccessToken() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public final String getIdToken() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.AuthCredential
    public final String getProvider() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public final String getSecret() {
        return this.zzf;
    }

    @Override // com.google.firebase.auth.AuthCredential
    public final String getSignInMethod() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.firebase.auth.AuthCredential
    public final AuthCredential zza() {
        return new zze(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }
}
