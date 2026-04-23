package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public enum zzgf {
    DOUBLE(0, 1, zzgz.DOUBLE),
    FLOAT(1, 1, zzgz.FLOAT),
    INT64(2, 1, zzgz.LONG),
    UINT64(3, 1, zzgz.LONG),
    INT32(4, 1, zzgz.INT),
    FIXED64(5, 1, zzgz.LONG),
    FIXED32(6, 1, zzgz.INT),
    BOOL(7, 1, zzgz.BOOLEAN),
    STRING(8, 1, zzgz.STRING),
    MESSAGE(9, 1, zzgz.MESSAGE),
    BYTES(10, 1, zzgz.BYTE_STRING),
    UINT32(11, 1, zzgz.INT),
    ENUM(12, 1, zzgz.ENUM),
    SFIXED32(13, 1, zzgz.INT),
    SFIXED64(14, 1, zzgz.LONG),
    SINT32(15, 1, zzgz.INT),
    SINT64(16, 1, zzgz.LONG),
    GROUP(17, 1, zzgz.MESSAGE),
    DOUBLE_LIST(18, 2, zzgz.DOUBLE),
    FLOAT_LIST(19, 2, zzgz.FLOAT),
    INT64_LIST(20, 2, zzgz.LONG),
    UINT64_LIST(21, 2, zzgz.LONG),
    INT32_LIST(22, 2, zzgz.INT),
    FIXED64_LIST(23, 2, zzgz.LONG),
    FIXED32_LIST(24, 2, zzgz.INT),
    BOOL_LIST(25, 2, zzgz.BOOLEAN),
    STRING_LIST(26, 2, zzgz.STRING),
    MESSAGE_LIST(27, 2, zzgz.MESSAGE),
    BYTES_LIST(28, 2, zzgz.BYTE_STRING),
    UINT32_LIST(29, 2, zzgz.INT),
    ENUM_LIST(30, 2, zzgz.ENUM),
    SFIXED32_LIST(31, 2, zzgz.INT),
    SFIXED64_LIST(32, 2, zzgz.LONG),
    SINT32_LIST(33, 2, zzgz.INT),
    SINT64_LIST(34, 2, zzgz.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzgz.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzgz.FLOAT),
    INT64_LIST_PACKED(37, 3, zzgz.LONG),
    UINT64_LIST_PACKED(38, 3, zzgz.LONG),
    INT32_LIST_PACKED(39, 3, zzgz.INT),
    FIXED64_LIST_PACKED(40, 3, zzgz.LONG),
    FIXED32_LIST_PACKED(41, 3, zzgz.INT),
    BOOL_LIST_PACKED(42, 3, zzgz.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzgz.INT),
    ENUM_LIST_PACKED(44, 3, zzgz.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzgz.INT),
    SFIXED64_LIST_PACKED(46, 3, zzgz.LONG),
    SINT32_LIST_PACKED(47, 3, zzgz.INT),
    SINT64_LIST_PACKED(48, 3, zzgz.LONG),
    GROUP_LIST(49, 2, zzgz.MESSAGE),
    MAP(50, 4, zzgz.VOID);
    
    private static final zzgf[] zzZ;
    private final zzgz zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzgf[] values = values();
        zzZ = new zzgf[values.length];
        for (zzgf zzgfVar : values) {
            zzZ[zzgfVar.zzac] = zzgfVar;
        }
    }

    zzgf(int i, int i2, zzgz zzgzVar) {
        this.zzac = i;
        this.zzab = zzgzVar;
        zzgz zzgzVar2 = zzgz.VOID;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzgzVar.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzgzVar.zza();
        }
        if (i2 == 1) {
            zzgzVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
