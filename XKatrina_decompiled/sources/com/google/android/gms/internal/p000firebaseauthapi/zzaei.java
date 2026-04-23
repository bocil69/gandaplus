package com.google.android.gms.internal.p000firebaseauthapi;

import java.lang.reflect.Type;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaei  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaei {
    private static final String zza = "com.google.android.gms.internal.firebase-auth-api.zzaei";

    private zzaei() {
    }

    public static Object zza(String str, Type type) throws zzaca {
        if (type == String.class) {
            try {
                zzaga zzagaVar = new zzaga();
                zzagaVar.zzb(str);
                if (zzagaVar.zzd()) {
                    return zzagaVar.zzc();
                }
                throw new zzaca("No error message: " + str);
            } catch (Exception e) {
                throw new zzaca("Json conversion failed! ".concat(String.valueOf(e.getMessage())), e);
            }
        } else if (type != Void.class) {
            try {
                try {
                    return ((zzaek) ((Class) type).getConstructor(new Class[0]).newInstance(new Object[0])).zza(str);
                } catch (Exception e2) {
                    throw new zzaca("Json conversion failed! ".concat(String.valueOf(e2.getMessage())), e2);
                }
            } catch (Exception e3) {
                throw new zzaca("Instantiation of JsonResponse failed! ".concat(type.toString()), e3);
            }
        } else {
            return null;
        }
    }
}
