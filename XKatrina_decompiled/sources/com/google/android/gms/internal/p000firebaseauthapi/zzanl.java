package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzanl  reason: invalid package */
/* loaded from: classes.dex */
public enum zzanl {
    DOUBLE(zzanm.DOUBLE, 1),
    FLOAT(zzanm.FLOAT, 5),
    INT64(zzanm.LONG, 0),
    UINT64(zzanm.LONG, 0),
    INT32(zzanm.INT, 0),
    FIXED64(zzanm.LONG, 1),
    FIXED32(zzanm.INT, 5),
    BOOL(zzanm.BOOLEAN, 0),
    STRING(zzanm.STRING, 2),
    GROUP(zzanm.MESSAGE, 3),
    MESSAGE(zzanm.MESSAGE, 2),
    BYTES(zzanm.BYTE_STRING, 2),
    UINT32(zzanm.INT, 0),
    ENUM(zzanm.ENUM, 0),
    SFIXED32(zzanm.INT, 5),
    SFIXED64(zzanm.LONG, 1),
    SINT32(zzanm.INT, 0),
    SINT64(zzanm.LONG, 0);
    
    private final zzanm zzt;

    zzanl(zzanm zzanmVar, int i) {
        this.zzt = zzanmVar;
    }

    public final zzanm zza() {
        return this.zzt;
    }
}
