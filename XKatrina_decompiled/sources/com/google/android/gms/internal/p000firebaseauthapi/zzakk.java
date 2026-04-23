package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.internal.p000firebaseauthapi.zzakg;
import com.google.android.gms.internal.p000firebaseauthapi.zzakk;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakk  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzakk<MessageType extends zzakk<MessageType, BuilderType>, BuilderType extends zzakg<MessageType, BuilderType>> extends zzaip<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzamw zzc = zzamw.zzc();

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzakp zzA() {
        return zzaly.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzakp zzB(zzakp zzakpVar) {
        int size = zzakpVar.size();
        return zzakpVar.zzd(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzD(Method method, Object obj, Object... objArr) {
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
    public static Object zzE(zzalp zzalpVar, String str, Object[] objArr) {
        return new zzalz(zzalpVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzH(Class cls, zzakk zzakkVar) {
        zzakkVar.zzG();
        zzb.put(cls, zzakkVar);
    }

    private final int zza(zzamb zzambVar) {
        return zzalx.zza().zzb(getClass()).zza(this);
    }

    private static zzakk zzb(zzakk zzakkVar) throws zzaks {
        if (zzakkVar == null || zzakkVar.zzK()) {
            return zzakkVar;
        }
        zzaks zza = new zzamu(zzakkVar).zza();
        zza.zzh(zzakkVar);
        throw zza;
    }

    private static zzakk zzc(zzakk zzakkVar, byte[] bArr, int i, int i2, zzajx zzajxVar) throws zzaks {
        zzakk zzw = zzakkVar.zzw();
        try {
            zzamb zzb2 = zzalx.zza().zzb(zzw.getClass());
            zzb2.zzi(zzw, bArr, 0, i2, new zzais(zzajxVar));
            zzb2.zzf(zzw);
            return zzw;
        } catch (zzaks e) {
            e = e;
            if (e.zzl()) {
                e = new zzaks(e);
            }
            e.zzh(zzw);
            throw e;
        } catch (zzamu e2) {
            zzaks zza = e2.zza();
            zza.zzh(zzw);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzaks) {
                throw ((zzaks) e3.getCause());
            }
            zzaks zzaksVar = new zzaks(e3);
            zzaksVar.zzh(zzw);
            throw zzaksVar;
        } catch (IndexOutOfBoundsException unused) {
            zzaks zzj = zzaks.zzj();
            zzj.zzh(zzw);
            throw zzj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzakk zzv(Class cls) {
        Map map = zzb;
        zzakk zzakkVar = (zzakk) map.get(cls);
        if (zzakkVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzakkVar = (zzakk) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzakkVar == null) {
            zzakkVar = (zzakk) ((zzakk) zzanf.zze(cls)).zzj(6, null, null);
            if (zzakkVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzakkVar);
        }
        return zzakkVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzakk zzx(zzakk zzakkVar, zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        zzajl zzh = zzajfVar.zzh();
        zzakk zzw = zzakkVar.zzw();
        try {
            zzamb zzb2 = zzalx.zza().zzb(zzw.getClass());
            zzb2.zzh(zzw, zzajm.zzq(zzh), zzajxVar);
            zzb2.zzf(zzw);
            try {
                zzh.zzz(0);
                zzb(zzw);
                return zzw;
            } catch (zzaks e) {
                e.zzh(zzw);
                throw e;
            }
        } catch (zzaks e2) {
            e = e2;
            if (e.zzl()) {
                e = new zzaks(e);
            }
            e.zzh(zzw);
            throw e;
        } catch (zzamu e3) {
            zzaks zza = e3.zza();
            zza.zzh(zzw);
            throw zza;
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzaks) {
                throw ((zzaks) e4.getCause());
            }
            zzaks zzaksVar = new zzaks(e4);
            zzaksVar.zzh(zzw);
            throw zzaksVar;
        } catch (RuntimeException e5) {
            if (e5.getCause() instanceof zzaks) {
                throw ((zzaks) e5.getCause());
            }
            throw e5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzakk zzy(zzakk zzakkVar, InputStream inputStream, zzajx zzajxVar) throws zzaks {
        zzajj zzajjVar = new zzajj(inputStream, 4096, null);
        zzakk zzw = zzakkVar.zzw();
        try {
            zzamb zzb2 = zzalx.zza().zzb(zzw.getClass());
            zzb2.zzh(zzw, zzajm.zzq(zzajjVar), zzajxVar);
            zzb2.zzf(zzw);
            zzb(zzw);
            return zzw;
        } catch (zzaks e) {
            e = e;
            if (e.zzl()) {
                e = new zzaks(e);
            }
            e.zzh(zzw);
            throw e;
        } catch (zzamu e2) {
            zzaks zza = e2.zza();
            zza.zzh(zzw);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzaks) {
                throw ((zzaks) e3.getCause());
            }
            zzaks zzaksVar = new zzaks(e3);
            zzaksVar.zzh(zzw);
            throw zzaksVar;
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzaks) {
                throw ((zzaks) e4.getCause());
            }
            throw e4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzakk zzz(zzakk zzakkVar, byte[] bArr, zzajx zzajxVar) throws zzaks {
        zzakk zzc = zzc(zzakkVar, bArr, 0, bArr.length, zzajxVar);
        zzb(zzc);
        return zzc;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzalx.zza().zzb(getClass()).zzj(this, (zzakk) obj);
        }
        return false;
    }

    public final int hashCode() {
        if (zzL()) {
            return zzr();
        }
        int i = this.zza;
        if (i == 0) {
            int zzr = zzr();
            this.zza = zzr;
            return zzr;
        }
        return i;
    }

    public final String toString() {
        return zzalr.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzalp
    public final /* synthetic */ zzalo zzC() {
        return (zzakg) zzj(5, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzF() {
        zzalx.zza().zzb(getClass()).zzf(this);
        zzG();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzG() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzI(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzalp
    public final void zzJ(zzajs zzajsVar) throws IOException {
        zzalx.zza().zzb(getClass()).zzm(this, zzajt.zza(zzajsVar));
    }

    public final boolean zzK() {
        Boolean bool = Boolean.TRUE;
        byte byteValue = ((Byte) zzj(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzalx.zza().zzb(getClass()).zzk(this);
        zzj(2, true != zzk ? null : this, null);
        return zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzL() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzalq
    public final /* synthetic */ zzalp zzM() {
        return (zzakk) zzj(6, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzj(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaip
    final int zzn(zzamb zzambVar) {
        if (zzL()) {
            int zza = zzambVar.zza(this);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i == Integer.MAX_VALUE) {
            int zza2 = zzambVar.zza(this);
            if (zza2 >= 0) {
                this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
                return zza2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
        }
        return i;
    }

    final int zzr() {
        return zzalx.zza().zzb(getClass()).zzb(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzakg zzt() {
        return (zzakg) zzj(5, null, null);
    }

    public final zzakg zzu() {
        zzakg zzakgVar = (zzakg) zzj(5, null, null);
        zzakgVar.zzh(this);
        return zzakgVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzakk zzw() {
        return (zzakk) zzj(4, null, null);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzalp
    public final int zzs() {
        int i;
        if (zzL()) {
            i = zza(null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zza(null);
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
