package com.google.android.recaptcha.internal;

import kotlin.UInt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final /* synthetic */ class zzbh {
    public static String zza(zzbi zzbiVar, String str, byte b) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) UInt.constructor-impl(UInt.constructor-impl(str.charAt(i)) ^ UInt.constructor-impl(b)));
        }
        return sb.toString();
    }
}
