package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public enum zzjv {
    DOUBLE(zzjw.DOUBLE, 1),
    FLOAT(zzjw.FLOAT, 5),
    INT64(zzjw.LONG, 0),
    UINT64(zzjw.LONG, 0),
    INT32(zzjw.INT, 0),
    FIXED64(zzjw.LONG, 1),
    FIXED32(zzjw.INT, 5),
    BOOL(zzjw.BOOLEAN, 0),
    STRING(zzjw.STRING, 2),
    GROUP(zzjw.MESSAGE, 3),
    MESSAGE(zzjw.MESSAGE, 2),
    BYTES(zzjw.BYTE_STRING, 2),
    UINT32(zzjw.INT, 0),
    ENUM(zzjw.ENUM, 0),
    SFIXED32(zzjw.INT, 5),
    SFIXED64(zzjw.LONG, 1),
    SINT32(zzjw.INT, 0),
    SINT64(zzjw.LONG, 0);
    
    private final zzjw zzt;

    zzjv(zzjw zzjwVar, int i) {
        this.zzt = zzjwVar;
    }

    public final zzjw zza() {
        return this.zzt;
    }
}
