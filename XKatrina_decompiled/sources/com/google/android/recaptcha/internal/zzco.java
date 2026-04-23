package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzco implements zzby {
    public static final zzco zza = new zzco();

    private zzco() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length == 2) {
            Object obj = objArr[0];
            if (true != (obj instanceof Object)) {
                obj = null;
            }
            if (obj == null) {
                throw new zzt(4, 5, null);
            }
            Object obj2 = objArr[1];
            if (true != (obj2 instanceof Object)) {
                obj2 = null;
            }
            if (obj2 == null) {
                throw new zzt(4, 5, null);
            }
            zzblVar.zzc().zzf(i, zzb(obj, obj2));
            return;
        }
        throw new zzt(4, 3, null);
    }

    public final Object zzb(Object obj, Object obj2) throws zzt {
        boolean z = obj instanceof Byte;
        if (z && (obj2 instanceof Byte)) {
            return Byte.valueOf((byte) (((Number) obj).byteValue() ^ ((Number) obj2).byteValue()));
        }
        boolean z2 = obj instanceof Short;
        if (z2 && (obj2 instanceof Short)) {
            return Short.valueOf((short) (((Number) obj).shortValue() ^ ((Number) obj2).shortValue()));
        }
        boolean z3 = obj instanceof Integer;
        if (z3 && (obj2 instanceof Integer)) {
            return Integer.valueOf(((Number) obj).intValue() ^ ((Number) obj2).intValue());
        }
        boolean z4 = obj instanceof Long;
        if (z4 && (obj2 instanceof Long)) {
            return Long.valueOf(((Number) obj2).longValue() ^ ((Number) obj).longValue());
        }
        int i = 0;
        if (obj instanceof String) {
            if (!(obj2 instanceof Byte)) {
                if (obj2 instanceof Integer) {
                    char[] charArray = ((String) obj).toCharArray();
                    int length = charArray.length;
                    ArrayList arrayList = new ArrayList(length);
                    while (i < length) {
                        arrayList.add(Integer.valueOf(charArray[i] ^ ((Number) obj2).intValue()));
                        i++;
                    }
                    return (Serializable) CollectionsKt.toIntArray(arrayList);
                }
            } else {
                byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
                int length2 = bytes.length;
                ArrayList arrayList2 = new ArrayList(length2);
                while (i < length2) {
                    arrayList2.add(Byte.valueOf((byte) (bytes[i] ^ ((Number) obj2).byteValue())));
                    i++;
                }
                return (Serializable) CollectionsKt.toByteArray(arrayList2);
            }
        }
        if (!z || !(obj2 instanceof byte[])) {
            if (!z2 || !(obj2 instanceof short[])) {
                if (!z3 || !(obj2 instanceof int[])) {
                    if (!z4 || !(obj2 instanceof long[])) {
                        boolean z5 = obj instanceof byte[];
                        if (!z5 || !(obj2 instanceof Byte)) {
                            boolean z6 = obj instanceof short[];
                            if (!z6 || !(obj2 instanceof Short)) {
                                boolean z7 = obj instanceof int[];
                                if (!z7 || !(obj2 instanceof Integer)) {
                                    boolean z8 = obj instanceof long[];
                                    if (!z8 || !(obj2 instanceof Long)) {
                                        if (!z5 || !(obj2 instanceof byte[])) {
                                            if (!z6 || !(obj2 instanceof short[])) {
                                                if (!z7 || !(obj2 instanceof int[])) {
                                                    if (!z8 || !(obj2 instanceof long[])) {
                                                        throw new zzt(4, 5, null);
                                                    }
                                                    long[] jArr = (long[]) obj;
                                                    int length3 = jArr.length;
                                                    long[] jArr2 = (long[]) obj2;
                                                    zzbx.zzb(this, length3, jArr2.length);
                                                    IntRange until = RangesKt.until(0, length3);
                                                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                                                    IntIterator it = until.iterator();
                                                    while (it.hasNext()) {
                                                        int nextInt = it.nextInt();
                                                        arrayList3.add(Long.valueOf(jArr[nextInt] ^ jArr2[nextInt]));
                                                    }
                                                    return (Serializable) arrayList3.toArray(new Long[0]);
                                                }
                                                int[] iArr = (int[]) obj;
                                                int length4 = iArr.length;
                                                int[] iArr2 = (int[]) obj2;
                                                zzbx.zzb(this, length4, iArr2.length);
                                                IntRange until2 = RangesKt.until(0, length4);
                                                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until2, 10));
                                                IntIterator it2 = until2.iterator();
                                                while (it2.hasNext()) {
                                                    int nextInt2 = it2.nextInt();
                                                    arrayList4.add(Integer.valueOf(iArr2[nextInt2] ^ iArr[nextInt2]));
                                                }
                                                return (Serializable) arrayList4.toArray(new Integer[0]);
                                            }
                                            short[] sArr = (short[]) obj;
                                            int length5 = sArr.length;
                                            short[] sArr2 = (short[]) obj2;
                                            zzbx.zzb(this, length5, sArr2.length);
                                            IntRange until3 = RangesKt.until(0, length5);
                                            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until3, 10));
                                            IntIterator it3 = until3.iterator();
                                            while (it3.hasNext()) {
                                                int nextInt3 = it3.nextInt();
                                                arrayList5.add(Short.valueOf((short) (sArr2[nextInt3] ^ sArr[nextInt3])));
                                            }
                                            return (Serializable) arrayList5.toArray(new Short[0]);
                                        }
                                        byte[] bArr = (byte[]) obj;
                                        int length6 = bArr.length;
                                        byte[] bArr2 = (byte[]) obj2;
                                        zzbx.zzb(this, length6, bArr2.length);
                                        IntRange until4 = RangesKt.until(0, length6);
                                        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until4, 10));
                                        IntIterator it4 = until4.iterator();
                                        while (it4.hasNext()) {
                                            int nextInt4 = it4.nextInt();
                                            arrayList6.add(Byte.valueOf((byte) (bArr2[nextInt4] ^ bArr[nextInt4])));
                                        }
                                        return (Serializable) arrayList6.toArray(new Byte[0]);
                                    }
                                    long[] jArr3 = (long[]) obj;
                                    ArrayList arrayList7 = new ArrayList(jArr3.length);
                                    for (long j : jArr3) {
                                        arrayList7.add(Long.valueOf(j ^ ((Number) obj2).longValue()));
                                    }
                                    return (Serializable) arrayList7.toArray(new Long[0]);
                                }
                                int[] iArr3 = (int[]) obj;
                                ArrayList arrayList8 = new ArrayList(iArr3.length);
                                for (int i2 : iArr3) {
                                    arrayList8.add(Integer.valueOf(i2 ^ ((Number) obj2).intValue()));
                                }
                                return (Serializable) arrayList8.toArray(new Integer[0]);
                            }
                            short[] sArr3 = (short[]) obj;
                            ArrayList arrayList9 = new ArrayList(sArr3.length);
                            for (short s : sArr3) {
                                arrayList9.add(Short.valueOf((short) (s ^ ((Number) obj2).shortValue())));
                            }
                            return (Serializable) arrayList9.toArray(new Short[0]);
                        }
                        byte[] bArr3 = (byte[]) obj;
                        ArrayList arrayList10 = new ArrayList(bArr3.length);
                        for (byte b : bArr3) {
                            arrayList10.add(Byte.valueOf((byte) (b ^ ((Number) obj2).byteValue())));
                        }
                        return (Serializable) arrayList10.toArray(new Byte[0]);
                    }
                    long[] jArr4 = (long[]) obj2;
                    ArrayList arrayList11 = new ArrayList(jArr4.length);
                    for (long j2 : jArr4) {
                        arrayList11.add(Long.valueOf(j2 ^ ((Number) obj).longValue()));
                    }
                    return (Serializable) arrayList11.toArray(new Long[0]);
                }
                int[] iArr4 = (int[]) obj2;
                ArrayList arrayList12 = new ArrayList(iArr4.length);
                for (int i3 : iArr4) {
                    arrayList12.add(Integer.valueOf(i3 ^ ((Number) obj).intValue()));
                }
                return (Serializable) arrayList12.toArray(new Integer[0]);
            }
            short[] sArr4 = (short[]) obj2;
            ArrayList arrayList13 = new ArrayList(sArr4.length);
            for (short s2 : sArr4) {
                arrayList13.add(Short.valueOf((short) (s2 ^ ((Number) obj).shortValue())));
            }
            return (Serializable) arrayList13.toArray(new Short[0]);
        }
        byte[] bArr4 = (byte[]) obj2;
        ArrayList arrayList14 = new ArrayList(bArr4.length);
        for (byte b2 : bArr4) {
            arrayList14.add(Byte.valueOf((byte) (b2 ^ ((Number) obj).byteValue())));
        }
        return (Serializable) arrayList14.toArray(new Byte[0]);
    }
}
