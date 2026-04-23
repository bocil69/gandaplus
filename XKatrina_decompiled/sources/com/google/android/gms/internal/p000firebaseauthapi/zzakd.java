package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakd  reason: invalid package */
/* loaded from: classes.dex */
public enum zzakd {
    DOUBLE(0, 1, zzakt.DOUBLE),
    FLOAT(1, 1, zzakt.FLOAT),
    INT64(2, 1, zzakt.LONG),
    UINT64(3, 1, zzakt.LONG),
    INT32(4, 1, zzakt.INT),
    FIXED64(5, 1, zzakt.LONG),
    FIXED32(6, 1, zzakt.INT),
    BOOL(7, 1, zzakt.BOOLEAN),
    STRING(8, 1, zzakt.STRING),
    MESSAGE(9, 1, zzakt.MESSAGE),
    BYTES(10, 1, zzakt.BYTE_STRING),
    UINT32(11, 1, zzakt.INT),
    ENUM(12, 1, zzakt.ENUM),
    SFIXED32(13, 1, zzakt.INT),
    SFIXED64(14, 1, zzakt.LONG),
    SINT32(15, 1, zzakt.INT),
    SINT64(16, 1, zzakt.LONG),
    GROUP(17, 1, zzakt.MESSAGE),
    DOUBLE_LIST(18, 2, zzakt.DOUBLE),
    FLOAT_LIST(19, 2, zzakt.FLOAT),
    INT64_LIST(20, 2, zzakt.LONG),
    UINT64_LIST(21, 2, zzakt.LONG),
    INT32_LIST(22, 2, zzakt.INT),
    FIXED64_LIST(23, 2, zzakt.LONG),
    FIXED32_LIST(24, 2, zzakt.INT),
    BOOL_LIST(25, 2, zzakt.BOOLEAN),
    STRING_LIST(26, 2, zzakt.STRING),
    MESSAGE_LIST(27, 2, zzakt.MESSAGE),
    BYTES_LIST(28, 2, zzakt.BYTE_STRING),
    UINT32_LIST(29, 2, zzakt.INT),
    ENUM_LIST(30, 2, zzakt.ENUM),
    SFIXED32_LIST(31, 2, zzakt.INT),
    SFIXED64_LIST(32, 2, zzakt.LONG),
    SINT32_LIST(33, 2, zzakt.INT),
    SINT64_LIST(34, 2, zzakt.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzakt.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzakt.FLOAT),
    INT64_LIST_PACKED(37, 3, zzakt.LONG),
    UINT64_LIST_PACKED(38, 3, zzakt.LONG),
    INT32_LIST_PACKED(39, 3, zzakt.INT),
    FIXED64_LIST_PACKED(40, 3, zzakt.LONG),
    FIXED32_LIST_PACKED(41, 3, zzakt.INT),
    BOOL_LIST_PACKED(42, 3, zzakt.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzakt.INT),
    ENUM_LIST_PACKED(44, 3, zzakt.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzakt.INT),
    SFIXED64_LIST_PACKED(46, 3, zzakt.LONG),
    SINT32_LIST_PACKED(47, 3, zzakt.INT),
    SINT64_LIST_PACKED(48, 3, zzakt.LONG),
    GROUP_LIST(49, 2, zzakt.MESSAGE),
    MAP(50, 4, zzakt.VOID);
    
    private static final zzakd[] zzZ;
    private final zzakt zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzakd[] values = values();
        zzZ = new zzakd[values.length];
        for (zzakd zzakdVar : values) {
            zzZ[zzakdVar.zzac] = zzakdVar;
        }
    }

    zzakd(int i, int i2, zzakt zzaktVar) {
        this.zzac = i;
        this.zzab = zzaktVar;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzaktVar.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzaktVar.zza();
        }
        if (i2 == 1) {
            zzakt zzaktVar2 = zzakt.VOID;
            zzaktVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
