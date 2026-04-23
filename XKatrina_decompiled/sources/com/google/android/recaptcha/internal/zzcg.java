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
public final class zzcg implements zzby {
    public static final zzcg zza = new zzcg();

    private zzcg() {
    }

    private final Object zzb(Object obj, Object obj2) throws zzt, ArithmeticException {
        boolean z = obj instanceof Byte;
        if (z && (obj2 instanceof Byte)) {
            return Integer.valueOf(((Number) obj).intValue() % ((Number) obj2).intValue());
        }
        boolean z2 = obj instanceof Short;
        if (z2 && (obj2 instanceof Short)) {
            return Integer.valueOf(((Number) obj).intValue() % ((Number) obj2).intValue());
        }
        boolean z3 = obj instanceof Integer;
        if (z3 && (obj2 instanceof Integer)) {
            return Integer.valueOf(((Number) obj).intValue() % ((Number) obj2).intValue());
        }
        boolean z4 = obj instanceof Long;
        if (z4 && (obj2 instanceof Long)) {
            return Long.valueOf(((Number) obj).longValue() % ((Number) obj2).longValue());
        }
        boolean z5 = obj instanceof Float;
        if (z5 && (obj2 instanceof Float)) {
            return Float.valueOf(((Number) obj).floatValue() % ((Number) obj2).floatValue());
        }
        boolean z6 = obj instanceof Double;
        if (z6 && (obj2 instanceof Double)) {
            return Double.valueOf(((Number) obj).doubleValue() % ((Number) obj2).doubleValue());
        }
        int i = 0;
        if (obj instanceof String) {
            if (!(obj2 instanceof Byte)) {
                if (obj2 instanceof Integer) {
                    char[] charArray = ((String) obj).toCharArray();
                    int length = charArray.length;
                    ArrayList arrayList = new ArrayList(length);
                    while (i < length) {
                        arrayList.add(Integer.valueOf(charArray[i] % ((Number) obj2).intValue()));
                        i++;
                    }
                    return (Serializable) CollectionsKt.toIntArray(arrayList);
                }
            } else {
                byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
                int length2 = bytes.length;
                ArrayList arrayList2 = new ArrayList(length2);
                while (i < length2) {
                    arrayList2.add(Byte.valueOf((byte) (bytes[i] % ((Number) obj2).intValue())));
                    i++;
                }
                return new String(CollectionsKt.toByteArray(arrayList2), Charsets.UTF_8);
            }
        }
        if (!z || !(obj2 instanceof byte[])) {
            if (!z2 || !(obj2 instanceof short[])) {
                if (!z3 || !(obj2 instanceof int[])) {
                    if (!z4 || !(obj2 instanceof long[])) {
                        if (!z5 || !(obj2 instanceof float[])) {
                            if (!z6 || !(obj2 instanceof double[])) {
                                boolean z7 = obj instanceof byte[];
                                if (!z7 || !(obj2 instanceof Byte)) {
                                    boolean z8 = obj instanceof short[];
                                    if (!z8 || !(obj2 instanceof Short)) {
                                        boolean z9 = obj instanceof int[];
                                        if (!z9 || !(obj2 instanceof Integer)) {
                                            boolean z10 = obj instanceof long[];
                                            if (!z10 || !(obj2 instanceof Long)) {
                                                boolean z11 = obj instanceof float[];
                                                if (!z11 || !(obj2 instanceof Float)) {
                                                    boolean z12 = obj instanceof double[];
                                                    if (!z12 || !(obj2 instanceof Double)) {
                                                        if (!z7 || !(obj2 instanceof byte[])) {
                                                            if (!z8 || !(obj2 instanceof short[])) {
                                                                if (!z9 || !(obj2 instanceof int[])) {
                                                                    if (!z10 || !(obj2 instanceof long[])) {
                                                                        if (!z11 || !(obj2 instanceof float[])) {
                                                                            if (!z12 || !(obj2 instanceof double[])) {
                                                                                throw new zzt(4, 5, null);
                                                                            }
                                                                            double[] dArr = (double[]) obj;
                                                                            int length3 = dArr.length;
                                                                            double[] dArr2 = (double[]) obj2;
                                                                            zzbx.zzb(this, length3, dArr2.length);
                                                                            IntRange until = RangesKt.until(0, length3);
                                                                            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                                                                            IntIterator it = until.iterator();
                                                                            while (it.hasNext()) {
                                                                                int nextInt = it.nextInt();
                                                                                arrayList3.add(Double.valueOf(dArr[nextInt] % dArr2[nextInt]));
                                                                            }
                                                                            return (Serializable) arrayList3.toArray(new Double[0]);
                                                                        }
                                                                        float[] fArr = (float[]) obj;
                                                                        int length4 = fArr.length;
                                                                        float[] fArr2 = (float[]) obj2;
                                                                        zzbx.zzb(this, length4, fArr2.length);
                                                                        IntRange until2 = RangesKt.until(0, length4);
                                                                        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until2, 10));
                                                                        IntIterator it2 = until2.iterator();
                                                                        while (it2.hasNext()) {
                                                                            int nextInt2 = it2.nextInt();
                                                                            arrayList4.add(Float.valueOf(fArr[nextInt2] % fArr2[nextInt2]));
                                                                        }
                                                                        return (Serializable) arrayList4.toArray(new Float[0]);
                                                                    }
                                                                    long[] jArr = (long[]) obj;
                                                                    int length5 = jArr.length;
                                                                    long[] jArr2 = (long[]) obj2;
                                                                    zzbx.zzb(this, length5, jArr2.length);
                                                                    IntRange until3 = RangesKt.until(0, length5);
                                                                    ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until3, 10));
                                                                    IntIterator it3 = until3.iterator();
                                                                    while (it3.hasNext()) {
                                                                        int nextInt3 = it3.nextInt();
                                                                        arrayList5.add(Long.valueOf(jArr[nextInt3] % jArr2[nextInt3]));
                                                                    }
                                                                    return (Serializable) arrayList5.toArray(new Long[0]);
                                                                }
                                                                int[] iArr = (int[]) obj;
                                                                int length6 = iArr.length;
                                                                int[] iArr2 = (int[]) obj2;
                                                                zzbx.zzb(this, length6, iArr2.length);
                                                                IntRange until4 = RangesKt.until(0, length6);
                                                                ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until4, 10));
                                                                IntIterator it4 = until4.iterator();
                                                                while (it4.hasNext()) {
                                                                    int nextInt4 = it4.nextInt();
                                                                    arrayList6.add(Integer.valueOf(iArr[nextInt4] % iArr2[nextInt4]));
                                                                }
                                                                return (Serializable) arrayList6.toArray(new Integer[0]);
                                                            }
                                                            short[] sArr = (short[]) obj;
                                                            int length7 = sArr.length;
                                                            short[] sArr2 = (short[]) obj2;
                                                            zzbx.zzb(this, length7, sArr2.length);
                                                            IntRange until5 = RangesKt.until(0, length7);
                                                            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until5, 10));
                                                            IntIterator it5 = until5.iterator();
                                                            while (it5.hasNext()) {
                                                                int nextInt5 = it5.nextInt();
                                                                arrayList7.add(Integer.valueOf(sArr[nextInt5] % sArr2[nextInt5]));
                                                            }
                                                            return (Serializable) arrayList7.toArray(new Integer[0]);
                                                        }
                                                        byte[] bArr = (byte[]) obj;
                                                        int length8 = bArr.length;
                                                        byte[] bArr2 = (byte[]) obj2;
                                                        zzbx.zzb(this, length8, bArr2.length);
                                                        IntRange until6 = RangesKt.until(0, length8);
                                                        ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until6, 10));
                                                        IntIterator it6 = until6.iterator();
                                                        while (it6.hasNext()) {
                                                            int nextInt6 = it6.nextInt();
                                                            arrayList8.add(Integer.valueOf(bArr[nextInt6] % bArr2[nextInt6]));
                                                        }
                                                        return (Serializable) arrayList8.toArray(new Integer[0]);
                                                    }
                                                    double[] dArr3 = (double[]) obj;
                                                    ArrayList arrayList9 = new ArrayList(dArr3.length);
                                                    for (double d : dArr3) {
                                                        arrayList9.add(Double.valueOf(d % ((Number) obj2).doubleValue()));
                                                    }
                                                    return (Serializable) arrayList9.toArray(new Double[0]);
                                                }
                                                float[] fArr3 = (float[]) obj;
                                                ArrayList arrayList10 = new ArrayList(fArr3.length);
                                                for (float f : fArr3) {
                                                    arrayList10.add(Float.valueOf(f % ((Number) obj2).floatValue()));
                                                }
                                                return (Serializable) arrayList10.toArray(new Float[0]);
                                            }
                                            long[] jArr3 = (long[]) obj;
                                            ArrayList arrayList11 = new ArrayList(jArr3.length);
                                            for (long j : jArr3) {
                                                arrayList11.add(Long.valueOf(j % ((Number) obj2).longValue()));
                                            }
                                            return (Serializable) arrayList11.toArray(new Long[0]);
                                        }
                                        int[] iArr3 = (int[]) obj;
                                        int length9 = iArr3.length;
                                        ArrayList arrayList12 = new ArrayList(length9);
                                        while (i < length9) {
                                            arrayList12.add(Integer.valueOf(iArr3[i] % ((Number) obj2).intValue()));
                                            i++;
                                        }
                                        return (Serializable) CollectionsKt.toIntArray(arrayList12);
                                    }
                                    short[] sArr3 = (short[]) obj;
                                    ArrayList arrayList13 = new ArrayList(sArr3.length);
                                    for (short s : sArr3) {
                                        arrayList13.add(Integer.valueOf(s % ((Number) obj2).intValue()));
                                    }
                                    return (Serializable) arrayList13.toArray(new Integer[0]);
                                }
                                byte[] bArr3 = (byte[]) obj;
                                ArrayList arrayList14 = new ArrayList(bArr3.length);
                                for (byte b : bArr3) {
                                    arrayList14.add(Integer.valueOf(b % ((Number) obj2).intValue()));
                                }
                                return (Serializable) arrayList14.toArray(new Integer[0]);
                            }
                            double[] dArr4 = (double[]) obj2;
                            ArrayList arrayList15 = new ArrayList(dArr4.length);
                            for (double d2 : dArr4) {
                                arrayList15.add(Double.valueOf(d2 % ((Number) obj).doubleValue()));
                            }
                            return (Serializable) arrayList15.toArray(new Double[0]);
                        }
                        float[] fArr4 = (float[]) obj2;
                        ArrayList arrayList16 = new ArrayList(fArr4.length);
                        for (float f2 : fArr4) {
                            arrayList16.add(Float.valueOf(f2 % ((Number) obj).floatValue()));
                        }
                        return (Serializable) arrayList16.toArray(new Float[0]);
                    }
                    long[] jArr4 = (long[]) obj2;
                    ArrayList arrayList17 = new ArrayList(jArr4.length);
                    for (long j2 : jArr4) {
                        arrayList17.add(Long.valueOf(j2 % ((Number) obj).longValue()));
                    }
                    return (Serializable) arrayList17.toArray(new Long[0]);
                }
                int[] iArr4 = (int[]) obj2;
                ArrayList arrayList18 = new ArrayList(iArr4.length);
                for (int i2 : iArr4) {
                    arrayList18.add(Integer.valueOf(i2 % ((Number) obj).intValue()));
                }
                return (Serializable) arrayList18.toArray(new Integer[0]);
            }
            short[] sArr4 = (short[]) obj2;
            ArrayList arrayList19 = new ArrayList(sArr4.length);
            for (short s2 : sArr4) {
                arrayList19.add(Integer.valueOf(s2 % ((Number) obj).intValue()));
            }
            return (Serializable) arrayList19.toArray(new Integer[0]);
        }
        byte[] bArr4 = (byte[]) obj2;
        ArrayList arrayList20 = new ArrayList(bArr4.length);
        for (byte b2 : bArr4) {
            arrayList20.add(Integer.valueOf(b2 % ((Number) obj).intValue()));
        }
        return (Serializable) arrayList20.toArray(new Integer[0]);
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
            try {
                zzblVar.zzc().zzf(i, zzb(obj, obj2));
                return;
            } catch (ArithmeticException e) {
                throw new zzt(4, 6, e);
            }
        }
        throw new zzt(4, 3, null);
    }
}
