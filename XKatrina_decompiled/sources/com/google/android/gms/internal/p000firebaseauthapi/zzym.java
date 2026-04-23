package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import javax.crypto.KeyAgreement;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzym  reason: invalid package */
/* loaded from: classes.dex */
public final class zzym {
    public static int zza(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (zzmq.zzd(ellipticCurve).subtract(BigInteger.ONE).bitLength() + 7) / 8;
    }

    public static BigInteger zzb(BigInteger bigInteger, boolean z, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger bigInteger2;
        BigInteger zzd = zzmq.zzd(ellipticCurve);
        BigInteger mod = bigInteger.multiply(bigInteger).add(ellipticCurve.getA()).multiply(bigInteger).add(ellipticCurve.getB()).mod(zzd);
        if (zzd.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        BigInteger mod2 = mod.mod(zzd);
        if (mod2.equals(BigInteger.ZERO)) {
            bigInteger2 = BigInteger.ZERO;
        } else {
            BigInteger bigInteger3 = null;
            if (!zzd.testBit(0) || !zzd.testBit(1)) {
                if (zzd.testBit(0) && !zzd.testBit(1)) {
                    BigInteger bigInteger4 = BigInteger.ONE;
                    BigInteger shiftRight = zzd.subtract(BigInteger.ONE).shiftRight(1);
                    int i = 0;
                    while (true) {
                        BigInteger mod3 = bigInteger4.multiply(bigInteger4).subtract(mod2).mod(zzd);
                        if (mod3.equals(BigInteger.ZERO)) {
                            bigInteger2 = bigInteger4;
                            break;
                        }
                        BigInteger modPow = mod3.modPow(shiftRight, zzd);
                        if (!modPow.add(BigInteger.ONE).equals(zzd)) {
                            if (modPow.equals(BigInteger.ONE)) {
                                bigInteger4 = bigInteger4.add(BigInteger.ONE);
                                i++;
                                if (i == 128 && !zzd.isProbablePrime(80)) {
                                    throw new InvalidAlgorithmParameterException("p is not prime");
                                }
                            } else {
                                throw new InvalidAlgorithmParameterException("p is not prime");
                            }
                        } else {
                            BigInteger shiftRight2 = zzd.add(BigInteger.ONE).shiftRight(1);
                            BigInteger bigInteger5 = BigInteger.ONE;
                            BigInteger bigInteger6 = bigInteger4;
                            for (int bitLength = shiftRight2.bitLength() - 2; bitLength >= 0; bitLength--) {
                                BigInteger multiply = bigInteger6.multiply(bigInteger5);
                                BigInteger mod4 = bigInteger6.multiply(bigInteger6).add(bigInteger5.multiply(bigInteger5).mod(zzd).multiply(mod3)).mod(zzd);
                                BigInteger mod5 = multiply.add(multiply).mod(zzd);
                                if (shiftRight2.testBit(bitLength)) {
                                    BigInteger mod6 = mod4.multiply(bigInteger4).add(mod5.multiply(mod3)).mod(zzd);
                                    bigInteger5 = bigInteger4.multiply(mod5).add(mod4).mod(zzd);
                                    bigInteger6 = mod6;
                                } else {
                                    bigInteger6 = mod4;
                                    bigInteger5 = mod5;
                                }
                            }
                            bigInteger3 = bigInteger6;
                        }
                    }
                }
            } else {
                bigInteger3 = mod2.modPow(zzd.add(BigInteger.ONE).shiftRight(2), zzd);
            }
            if (bigInteger3 != null && bigInteger3.multiply(bigInteger3).mod(zzd).compareTo(mod2) != 0) {
                throw new GeneralSecurityException("Could not find a modular square root");
            }
            bigInteger2 = bigInteger3;
        }
        return z != bigInteger2.testBit(0) ? zzd.subtract(bigInteger2).mod(zzd) : bigInteger2;
    }

    public static KeyPair zzc(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator = (KeyPairGenerator) zzyv.zzf.zza("EC");
        keyPairGenerator.initialize(eCParameterSpec);
        return keyPairGenerator.generateKeyPair();
    }

    public static void zzd(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        zze(eCPublicKey, eCPrivateKey);
        zzmq.zzf(eCPublicKey.getW(), eCPrivateKey.getParams().getCurve());
    }

    static void zze(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        try {
            if (zzmq.zzg(eCPublicKey.getParams(), eCPrivateKey.getParams())) {
                return;
            }
            throw new GeneralSecurityException("invalid public key spec");
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static byte[] zzf(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws GeneralSecurityException {
        zze(eCPublicKey, eCPrivateKey);
        ECPoint w = eCPublicKey.getW();
        zzmq.zzf(w, eCPrivateKey.getParams().getCurve());
        PublicKey generatePublic = ((KeyFactory) zzyv.zzg.zza("EC")).generatePublic(new ECPublicKeySpec(w, eCPrivateKey.getParams()));
        KeyAgreement keyAgreement = (KeyAgreement) zzyv.zze.zza("ECDH");
        keyAgreement.init(eCPrivateKey);
        try {
            keyAgreement.doPhase(generatePublic, true);
            byte[] generateSecret = keyAgreement.generateSecret();
            EllipticCurve curve = eCPrivateKey.getParams().getCurve();
            BigInteger bigInteger = new BigInteger(1, generateSecret);
            if (bigInteger.signum() == -1 || bigInteger.compareTo(zzmq.zzd(curve)) >= 0) {
                throw new GeneralSecurityException("shared secret is out of range");
            }
            zzb(bigInteger, true, curve);
            return generateSecret;
        } catch (IllegalStateException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static ECPrivateKey zzg(int i, byte[] bArr) throws GeneralSecurityException {
        return (ECPrivateKey) ((KeyFactory) zzyv.zzg.zza("EC")).generatePrivate(new ECPrivateKeySpec(zzmn.zza(bArr), zzi(i)));
    }

    public static ECPublicKey zzh(ECParameterSpec eCParameterSpec, int i, byte[] bArr) throws GeneralSecurityException {
        return (ECPublicKey) ((KeyFactory) zzyv.zzg.zza("EC")).generatePublic(new ECPublicKeySpec(zzj(eCParameterSpec.getCurve(), i, bArr), eCParameterSpec));
    }

    public static ECPoint zzj(EllipticCurve ellipticCurve, int i, byte[] bArr) throws GeneralSecurityException {
        int zza = zza(ellipticCurve);
        int i2 = i - 1;
        boolean z = false;
        if (i2 == 0) {
            int length = bArr.length;
            if (length != zza + zza + 1) {
                throw new GeneralSecurityException("invalid point size");
            }
            if (bArr[0] != 4) {
                throw new GeneralSecurityException("invalid point format");
            }
            int i3 = zza + 1;
            ECPoint eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 1, i3)), new BigInteger(1, Arrays.copyOfRange(bArr, i3, length)));
            zzmq.zzf(eCPoint, ellipticCurve);
            return eCPoint;
        } else if (i2 == 1) {
            int i4 = zza + 1;
            BigInteger zzd = zzmq.zzd(ellipticCurve);
            int length2 = bArr.length;
            if (length2 != i4) {
                throw new GeneralSecurityException("compressed point has wrong length");
            }
            byte b = bArr[0];
            if (b != 2) {
                if (b != 3) {
                    throw new GeneralSecurityException("invalid format");
                }
                z = true;
            }
            BigInteger bigInteger = new BigInteger(1, Arrays.copyOfRange(bArr, 1, length2));
            if (bigInteger.signum() == -1 || bigInteger.compareTo(zzd) >= 0) {
                throw new GeneralSecurityException("x is out of range");
            }
            return new ECPoint(bigInteger, zzb(bigInteger, z, ellipticCurve));
        } else {
            int i5 = zza + zza;
            int length3 = bArr.length;
            if (length3 != i5) {
                throw new GeneralSecurityException("invalid point size");
            }
            ECPoint eCPoint2 = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 0, zza)), new BigInteger(1, Arrays.copyOfRange(bArr, zza, length3)));
            zzmq.zzf(eCPoint2, ellipticCurve);
            return eCPoint2;
        }
    }

    public static ECParameterSpec zzi(int i) throws NoSuchAlgorithmException {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 == 1) {
                return zzmq.zzb;
            }
            return zzmq.zzc;
        }
        return zzmq.zza;
    }
}
