package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzgi;
import com.google.android.recaptcha.internal.zzgo;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public abstract class zzgo<MessageType extends zzgo<MessageType, BuilderType>, BuilderType extends zzgi<MessageType, BuilderType>> extends zzei<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzjg zzc = zzjg.zzc();

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzC(Class cls, zzgo zzgoVar) {
        zzgoVar.zzB();
        zzb.put(cls, zzgoVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean zzE(zzgo zzgoVar, boolean z) {
        byte byteValue = ((Byte) zzgoVar.zzh(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = zzih.zza().zzb(zzgoVar.getClass()).zzl(zzgoVar);
        if (z) {
            zzgoVar.zzh(2, true != zzl ? null : zzgoVar, null);
        }
        return zzl;
    }

    private final int zzf(zzil zzilVar) {
        if (zzilVar == null) {
            return zzih.zza().zzb(getClass()).zza(this);
        }
        return zzilVar.zza(this);
    }

    private static zzgo zzg(zzgo zzgoVar) throws zzgy {
        if (zzgoVar == null || zzgoVar.zzo()) {
            return zzgoVar;
        }
        zzgy zza = new zzje(zzgoVar).zza();
        zza.zzh(zzgoVar);
        throw zza;
    }

    private static zzgo zzi(zzgo zzgoVar, byte[] bArr, int i, int i2, zzfz zzfzVar) throws zzgy {
        zzgo zzs = zzgoVar.zzs();
        try {
            zzil zzb2 = zzih.zza().zzb(zzs.getClass());
            zzb2.zzi(zzs, bArr, 0, i2, new zzem(zzfzVar));
            zzb2.zzf(zzs);
            return zzs;
        } catch (zzgy e) {
            e = e;
            if (e.zzl()) {
                e = new zzgy(e);
            }
            e.zzh(zzs);
            throw e;
        } catch (zzje e2) {
            zzgy zza = e2.zza();
            zza.zzh(zzs);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzgy) {
                throw ((zzgy) e3.getCause());
            }
            zzgy zzgyVar = new zzgy(e3);
            zzgyVar.zzh(zzs);
            throw zzgyVar;
        } catch (IndexOutOfBoundsException unused) {
            zzgy zzj = zzgy.zzj();
            zzj.zzh(zzs);
            throw zzj;
        }
    }

    public static zzgm zzq(zzhy zzhyVar, Object obj, zzhy zzhyVar2, zzgr zzgrVar, int i, zzjv zzjvVar, Class cls) {
        return new zzgm(zzhyVar, "", null, new zzgl(null, i, zzjvVar, false, false), cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgo zzr(Class cls) {
        Map map = zzb;
        zzgo zzgoVar = (zzgo) map.get(cls);
        if (zzgoVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzgoVar = (zzgo) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzgoVar == null) {
            zzgoVar = (zzgo) ((zzgo) zzjp.zze(cls)).zzh(6, null, null);
            if (zzgoVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzgoVar);
        }
        return zzgoVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgo zzt(zzgo zzgoVar, InputStream inputStream) throws zzgy {
        zzff zzfdVar;
        int i = zzff.zzd;
        if (inputStream == null) {
            byte[] bArr = zzgw.zzd;
            int length = bArr.length;
            zzfdVar = zzff.zzH(bArr, 0, 0, false);
        } else {
            zzfdVar = new zzfd(inputStream, 4096, null);
        }
        zzfz zzfzVar = zzfz.zza;
        zzgo zzs = zzgoVar.zzs();
        try {
            zzil zzb2 = zzih.zza().zzb(zzs.getClass());
            zzb2.zzh(zzs, zzfg.zzq(zzfdVar), zzfzVar);
            zzb2.zzf(zzs);
            zzg(zzs);
            return zzs;
        } catch (zzgy e) {
            e = e;
            if (e.zzl()) {
                e = new zzgy(e);
            }
            e.zzh(zzs);
            throw e;
        } catch (zzje e2) {
            zzgy zza = e2.zza();
            zza.zzh(zzs);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzgy) {
                throw ((zzgy) e3.getCause());
            }
            zzgy zzgyVar = new zzgy(e3);
            zzgyVar.zzh(zzs);
            throw zzgyVar;
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzgy) {
                throw ((zzgy) e4.getCause());
            }
            throw e4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgo zzu(zzgo zzgoVar, byte[] bArr) throws zzgy {
        zzgo zzi = zzi(zzgoVar, bArr, 0, bArr.length, zzfz.zza);
        zzg(zzi);
        return zzi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgt zzv() {
        return zzgp.zzf();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgv zzw() {
        return zzii.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgv zzx(zzgv zzgvVar) {
        int size = zzgvVar.size();
        return zzgvVar.zzd(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzy(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zzz(zzhy zzhyVar, String str, Object[] objArr) {
        return new zzij(zzhyVar, str, objArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzih.zza().zzb(getClass()).zzk(this, (zzgo) obj);
        }
        return false;
    }

    public final int hashCode() {
        if (zzF()) {
            return zzm();
        }
        int i = this.zza;
        if (i == 0) {
            int zzm = zzm();
            this.zza = zzm;
            return zzm;
        }
        return i;
    }

    public final String toString() {
        return zzia.zza(this, super.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzA() {
        zzih.zza().zzb(getClass()).zzf(this);
        zzB();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzB() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzD(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzF() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    @Override // com.google.android.recaptcha.internal.zzhy
    public final /* synthetic */ zzhx zzV() {
        return (zzgi) zzh(5, null, null);
    }

    @Override // com.google.android.recaptcha.internal.zzhy
    public final /* synthetic */ zzhx zzW() {
        zzgi zzgiVar = (zzgi) zzh(5, null, null);
        zzgiVar.zzg(this);
        return zzgiVar;
    }

    @Override // com.google.android.recaptcha.internal.zzhz
    public final /* synthetic */ zzhy zzX() {
        return (zzgo) zzh(6, null, null);
    }

    @Override // com.google.android.recaptcha.internal.zzhy
    public final void zze(zzfk zzfkVar) throws IOException {
        zzih.zza().zzb(getClass()).zzj(this, zzfl.zza(zzfkVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzh(int i, Object obj, Object obj2);

    final int zzm() {
        return zzih.zza().zzb(getClass()).zzb(this);
    }

    @Override // com.google.android.recaptcha.internal.zzhz
    public final boolean zzo() {
        Boolean bool = Boolean.TRUE;
        return zzE(this, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzgi zzp() {
        return (zzgi) zzh(5, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgo zzs() {
        return (zzgo) zzh(4, null, null);
    }

    @Override // com.google.android.recaptcha.internal.zzei
    final int zza(zzil zzilVar) {
        if (zzF()) {
            int zzf = zzf(zzilVar);
            if (zzf >= 0) {
                return zzf;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zzf);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zzf2 = zzf(zzilVar);
        if (zzf2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zzf2;
            return zzf2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zzf2);
    }

    @Override // com.google.android.recaptcha.internal.zzhy
    public final int zzn() {
        int i;
        if (zzF()) {
            i = zzf(null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzf(null);
                if (i >= 0) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }
}
