package com.google.firebase.iid;
/* loaded from: classes2.dex */
final class zzx implements InstanceIdResult {
    private final String zzbu;
    private final String zzbv;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(String str, String str2) {
        this.zzbu = str;
        this.zzbv = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getId() {
        return this.zzbu;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getToken() {
        return this.zzbv;
    }
}
