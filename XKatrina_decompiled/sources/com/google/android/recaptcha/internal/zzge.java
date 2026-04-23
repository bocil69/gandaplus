package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzge {
    private static final zzge zzb = new zzge(true);
    final zziy zza = new zzio(16);
    private boolean zzc;
    private boolean zzd;

    private zzge() {
    }

    public static int zza(zzgd zzgdVar, Object obj) {
        int zzd;
        int zzy;
        zzjv zzd2 = zzgdVar.zzd();
        int zza = zzgdVar.zza();
        zzgdVar.zzg();
        int i = zzfk.zzb;
        int zzy2 = zzfk.zzy(zza << 3);
        if (zzd2 == zzjv.GROUP) {
            zzhy zzhyVar = (zzhy) obj;
            byte[] bArr = zzgw.zzd;
            if (zzhyVar instanceof zzej) {
                zzej zzejVar = (zzej) zzhyVar;
                throw null;
            }
            zzy2 += zzy2;
        }
        zzjw zzjwVar = zzjw.INT;
        int i2 = 4;
        switch (zzd2.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                i2 = 8;
                break;
            case 1:
                ((Float) obj).floatValue();
                break;
            case 2:
                i2 = zzfk.zzz(((Long) obj).longValue());
                break;
            case 3:
                i2 = zzfk.zzz(((Long) obj).longValue());
                break;
            case 4:
                i2 = zzfk.zzu(((Integer) obj).intValue());
                break;
            case 5:
                ((Long) obj).longValue();
                i2 = 8;
                break;
            case 6:
                ((Integer) obj).intValue();
                break;
            case 7:
                ((Boolean) obj).booleanValue();
                i2 = 1;
                break;
            case 8:
                if (obj instanceof zzez) {
                    zzd = ((zzez) obj).zzd();
                    zzy = zzfk.zzy(zzd);
                    i2 = zzy + zzd;
                    break;
                } else {
                    i2 = zzfk.zzx((String) obj);
                    break;
                }
            case 9:
                i2 = ((zzhy) obj).zzn();
                break;
            case 10:
                if (obj instanceof zzhd) {
                    zzd = ((zzhd) obj).zza();
                    zzy = zzfk.zzy(zzd);
                    i2 = zzy + zzd;
                    break;
                } else {
                    i2 = zzfk.zzv((zzhy) obj);
                    break;
                }
            case 11:
                if (obj instanceof zzez) {
                    zzd = ((zzez) obj).zzd();
                    zzy = zzfk.zzy(zzd);
                } else {
                    zzd = ((byte[]) obj).length;
                    zzy = zzfk.zzy(zzd);
                }
                i2 = zzy + zzd;
                break;
            case 12:
                i2 = zzfk.zzy(((Integer) obj).intValue());
                break;
            case 13:
                if (obj instanceof zzgq) {
                    i2 = zzfk.zzu(((zzgq) obj).zza());
                    break;
                } else {
                    i2 = zzfk.zzu(((Integer) obj).intValue());
                    break;
                }
            case 14:
                ((Integer) obj).intValue();
                break;
            case 15:
                ((Long) obj).longValue();
                i2 = 8;
                break;
            case 16:
                int intValue = ((Integer) obj).intValue();
                i2 = zzfk.zzy((intValue >> 31) ^ (intValue + intValue));
                break;
            case 17:
                long longValue = ((Long) obj).longValue();
                i2 = zzfk.zzz((longValue >> 63) ^ (longValue + longValue));
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return zzy2 + i2;
    }

    public static zzge zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzid) {
            return ((zzid) obj).zzd();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            return bArr2;
        }
        return obj;
    }

    private final void zzm(Map.Entry entry) {
        zzhy zzj;
        zzgd zzgdVar = (zzgd) entry.getKey();
        Object value = entry.getValue();
        if (!(value instanceof zzhd)) {
            zzgdVar.zzg();
            if (zzgdVar.zze() == zzjw.MESSAGE) {
                Object zze = zze(zzgdVar);
                if (zze == null) {
                    this.zza.put(zzgdVar, zzl(value));
                    return;
                }
                if (zze instanceof zzid) {
                    zzj = zzgdVar.zzc((zzid) zze, (zzid) value);
                } else {
                    zzhx zzW = ((zzhy) zze).zzW();
                    zzgdVar.zzb(zzW, (zzhy) value);
                    zzj = zzW.zzj();
                }
                this.zza.put(zzgdVar, zzj);
                return;
            }
            this.zza.put(zzgdVar, zzl(value));
            return;
        }
        zzhd zzhdVar = (zzhd) value;
        throw null;
    }

    private static boolean zzn(Map.Entry entry) {
        zzgd zzgdVar = (zzgd) entry.getKey();
        if (zzgdVar.zze() == zzjw.MESSAGE) {
            zzgdVar.zzg();
            Object value = entry.getValue();
            if (value instanceof zzhz) {
                return ((zzhz) value).zzo();
            }
            if (value instanceof zzhd) {
                return true;
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        return true;
    }

    private static final int zzo(Map.Entry entry) {
        zzgd zzgdVar = (zzgd) entry.getKey();
        Object value = entry.getValue();
        if (zzgdVar.zze() != zzjw.MESSAGE) {
            return zza(zzgdVar, value);
        }
        zzgdVar.zzg();
        zzgdVar.zzf();
        if (value instanceof zzhd) {
            int zzy = zzfk.zzy(((zzgd) entry.getKey()).zza());
            int zza = ((zzhd) value).zza();
            int zzy2 = zzfk.zzy(zza) + zza;
            int zzy3 = zzfk.zzy(24);
            int zzy4 = zzfk.zzy(16);
            int zzy5 = zzfk.zzy(8);
            return zzy5 + zzy5 + zzy4 + zzy + zzy3 + zzy2;
        }
        int zzy6 = zzfk.zzy(((zzgd) entry.getKey()).zza());
        int zzy7 = zzfk.zzy(24) + zzfk.zzv((zzhy) value);
        int zzy8 = zzfk.zzy(16);
        int zzy9 = zzfk.zzy(8);
        return zzy9 + zzy9 + zzy8 + zzy6 + zzy7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzge) {
            return this.zza.equals(((zzge) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            i += zzo(this.zza.zzg(i2));
        }
        for (Map.Entry entry : this.zza.zzc()) {
            i += zzo(entry);
        }
        return i;
    }

    /* renamed from: zzc */
    public final zzge clone() {
        zzge zzgeVar = new zzge();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzgeVar.zzi((zzgd) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzgeVar.zzi((zzgd) entry.getKey(), entry.getValue());
        }
        zzgeVar.zzd = this.zzd;
        return zzgeVar;
    }

    public final Object zze(zzgd zzgdVar) {
        Object obj = this.zza.get(zzgdVar);
        if (obj instanceof zzhd) {
            zzhd zzhdVar = (zzhd) obj;
            throw null;
        }
        return obj;
    }

    public final Iterator zzf() {
        return this.zzd ? new zzhc(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            if (zzg.getValue() instanceof zzgo) {
                ((zzgo) zzg.getValue()).zzA();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzh(zzge zzgeVar) {
        for (int i = 0; i < zzgeVar.zza.zzb(); i++) {
            zzm(zzgeVar.zza.zzg(i));
        }
        for (Map.Entry entry : zzgeVar.zza.zzc()) {
            zzm(entry);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        if ((r7 instanceof com.google.android.recaptcha.internal.zzgq) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0035, code lost:
        if ((r7 instanceof byte[]) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r0 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
        if ((r7 instanceof com.google.android.recaptcha.internal.zzhd) == false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(com.google.android.recaptcha.internal.zzgd r6, java.lang.Object r7) {
        /*
            r5 = this;
            r6.zzg()
            com.google.android.recaptcha.internal.zzjv r0 = r6.zzd()
            byte[] r1 = com.google.android.recaptcha.internal.zzgw.zzd
            java.util.Objects.requireNonNull(r7)
            com.google.android.recaptcha.internal.zzjv r1 = com.google.android.recaptcha.internal.zzjv.DOUBLE
            com.google.android.recaptcha.internal.zzjw r1 = com.google.android.recaptcha.internal.zzjw.INT
            com.google.android.recaptcha.internal.zzjw r0 = r0.zza()
            int r0 = r0.ordinal()
            r1 = 1
            switch(r0) {
                case 0: goto L47;
                case 1: goto L44;
                case 2: goto L41;
                case 3: goto L3e;
                case 4: goto L3b;
                case 5: goto L38;
                case 6: goto L2f;
                case 7: goto L26;
                case 8: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L57
        L1d:
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzhy
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzhd
            if (r0 == 0) goto L57
            goto L4b
        L26:
            boolean r0 = r7 instanceof java.lang.Integer
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzgq
            if (r0 == 0) goto L57
            goto L4b
        L2f:
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzez
            if (r0 != 0) goto L4b
            boolean r0 = r7 instanceof byte[]
            if (r0 == 0) goto L57
            goto L4b
        L38:
            boolean r0 = r7 instanceof java.lang.String
            goto L49
        L3b:
            boolean r0 = r7 instanceof java.lang.Boolean
            goto L49
        L3e:
            boolean r0 = r7 instanceof java.lang.Double
            goto L49
        L41:
            boolean r0 = r7 instanceof java.lang.Float
            goto L49
        L44:
            boolean r0 = r7 instanceof java.lang.Long
            goto L49
        L47:
            boolean r0 = r7 instanceof java.lang.Integer
        L49:
            if (r0 == 0) goto L57
        L4b:
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzhd
            if (r0 == 0) goto L51
            r5.zzd = r1
        L51:
            com.google.android.recaptcha.internal.zziy r0 = r5.zza
            r0.put(r6, r7)
            return
        L57:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            int r4 = r6.zza()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            com.google.android.recaptcha.internal.zzjv r6 = r6.zzd()
            com.google.android.recaptcha.internal.zzjw r6 = r6.zza()
            r2[r1] = r6
            r6 = 2
            java.lang.Class r7 = r7.getClass()
            java.lang.String r7 = r7.getName()
            r2[r6] = r7
            java.lang.String r6 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r6 = java.lang.String.format(r6, r2)
            r0.<init>(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzge.zzi(com.google.android.recaptcha.internal.zzgd, java.lang.Object):void");
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (int i = 0; i < this.zza.zzb(); i++) {
            if (!zzn(this.zza.zzg(i))) {
                return false;
            }
        }
        for (Map.Entry entry : this.zza.zzc()) {
            if (!zzn(entry)) {
                return false;
            }
        }
        return true;
    }

    private zzge(boolean z) {
        zzg();
        zzg();
    }
}
