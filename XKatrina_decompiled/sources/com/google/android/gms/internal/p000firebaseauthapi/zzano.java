package com.google.android.gms.internal.p000firebaseauthapi;

import com.airbnb.lottie.utils.Utils;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzano  reason: invalid package */
/* loaded from: classes.dex */
public final class zzano {
    public static final zzamt zza;
    public static final zzamt zzb;
    public static final zzamt zzc;
    private static final ThreadLocal zzd;
    @Nullable
    private static final Method zze;
    @Nullable
    private static final Method zzf;
    @Nullable
    private static final Method zzg;

    static {
        zzams zzc2 = zzamt.zzc();
        zzc2.zzb(-62135596800L);
        zzc2.zza(0);
        zza = (zzamt) zzc2.zzi();
        zzams zzc3 = zzamt.zzc();
        zzc3.zzb(253402300799L);
        zzc3.zza(999999999);
        zzb = (zzamt) zzc3.zzi();
        zzams zzc4 = zzamt.zzc();
        zzc4.zzb(0L);
        zzc4.zza(0);
        zzc = (zzamt) zzc4.zzi();
        zzd = new zzann();
        zze = zzc("now");
        zzf = zzc("getEpochSecond");
        zzg = zzc("getNano");
    }

    public static zzamt zza(zzamt zzamtVar) {
        long zzb2 = zzamtVar.zzb();
        int i = (zzb2 > (-62135596800L) ? 1 : (zzb2 == (-62135596800L) ? 0 : -1));
        int zza2 = zzamtVar.zza();
        if (i < 0 || zzb2 > 253402300799L || zza2 < 0 || zza2 >= 1000000000) {
            throw new IllegalArgumentException(String.format("Timestamp is not valid. See proto definition for valid values. Seconds (%s) must be in range [-62,135,596,800, +253,402,300,799]. Nanos (%s) must be in range [0, +999,999,999].", Long.valueOf(zzb2), Integer.valueOf(zza2)));
        }
        return zzamtVar;
    }

    public static zzamt zzb(String str) throws ParseException {
        String str2;
        int i;
        int indexOf = str.indexOf(84);
        if (indexOf == -1) {
            throw new ParseException("Failed to parse timestamp: invalid timestamp \"" + str + "\"", 0);
        }
        int indexOf2 = str.indexOf(90, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.indexOf(43, indexOf);
        }
        if (indexOf2 == -1) {
            indexOf2 = str.indexOf(45, indexOf);
        }
        if (indexOf2 == -1) {
            throw new ParseException("Failed to parse timestamp: missing valid timezone offset.", 0);
        }
        String substring = str.substring(0, indexOf2);
        int indexOf3 = substring.indexOf(46);
        if (indexOf3 != -1) {
            String substring2 = substring.substring(0, indexOf3);
            str2 = substring.substring(indexOf3 + 1);
            substring = substring2;
        } else {
            str2 = "";
        }
        long time = ((SimpleDateFormat) zzd.get()).parse(substring).getTime() / 1000;
        if (str2.isEmpty()) {
            i = 0;
        } else {
            i = 0;
            for (int i2 = 0; i2 < 9; i2++) {
                i *= 10;
                if (i2 < str2.length()) {
                    if (str2.charAt(i2) >= '0' && str2.charAt(i2) <= '9') {
                        i += str2.charAt(i2) - '0';
                    } else {
                        throw new ParseException("Invalid nanoseconds.", 0);
                    }
                }
            }
        }
        int i3 = indexOf2 + 1;
        if (str.charAt(indexOf2) == 'Z') {
            if (str.length() != i3) {
                String substring3 = str.substring(indexOf2);
                throw new ParseException("Failed to parse timestamp: invalid trailing data \"" + substring3 + "\"", 0);
            }
        } else {
            String substring4 = str.substring(i3);
            int indexOf4 = substring4.indexOf(58);
            if (indexOf4 == -1) {
                throw new ParseException("Invalid offset value: ".concat(String.valueOf(substring4)), 0);
            }
            String substring5 = substring4.substring(0, indexOf4);
            String substring6 = substring4.substring(indexOf4 + 1);
            char charAt = str.charAt(indexOf2);
            long parseLong = ((Long.parseLong(substring5) * 60) + Long.parseLong(substring6)) * 60;
            time = charAt == '+' ? time - parseLong : time + parseLong;
        }
        if (i <= -1000000000 || i >= 1000000000) {
            try {
                time = zzbb.zza(time, i / Utils.SECOND_IN_NANOS);
                i %= Utils.SECOND_IN_NANOS;
            } catch (IllegalArgumentException e) {
                ParseException parseException = new ParseException("Failed to parse timestamp " + str + " Timestamp is out of range.", 0);
                parseException.initCause(e);
                throw parseException;
            }
        }
        if (i < 0) {
            i += Utils.SECOND_IN_NANOS;
            time = zzbb.zzb(time, 1L);
        }
        zzams zzc2 = zzamt.zzc();
        zzc2.zzb(time);
        zzc2.zza(i);
        zzamt zzamtVar = (zzamt) zzc2.zzi();
        zza(zzamtVar);
        return zzamtVar;
    }

    @Nullable
    private static Method zzc(String str) {
        try {
            return Class.forName("java.time.Instant").getMethod(str, new Class[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
