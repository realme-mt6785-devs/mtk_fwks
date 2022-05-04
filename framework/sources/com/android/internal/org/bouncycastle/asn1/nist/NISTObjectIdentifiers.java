package com.android.internal.org.bouncycastle.asn1.nist;

import com.android.internal.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.oplus.uifirst.IOplusUIFirstManager;
/* loaded from: classes4.dex */
public interface NISTObjectIdentifiers {
    public static final ASN1ObjectIdentifier aes;
    public static final ASN1ObjectIdentifier dsa_with_sha224;
    public static final ASN1ObjectIdentifier dsa_with_sha256;
    public static final ASN1ObjectIdentifier dsa_with_sha384;
    public static final ASN1ObjectIdentifier dsa_with_sha512;
    public static final ASN1ObjectIdentifier hashAlgs;
    public static final ASN1ObjectIdentifier id_KmacWithSHAKE128;
    public static final ASN1ObjectIdentifier id_KmacWithSHAKE256;
    public static final ASN1ObjectIdentifier id_aes128_CBC;
    public static final ASN1ObjectIdentifier id_aes128_CCM;
    public static final ASN1ObjectIdentifier id_aes128_CFB;
    public static final ASN1ObjectIdentifier id_aes128_ECB;
    public static final ASN1ObjectIdentifier id_aes128_GCM;
    public static final ASN1ObjectIdentifier id_aes128_OFB;
    public static final ASN1ObjectIdentifier id_aes128_wrap;
    public static final ASN1ObjectIdentifier id_aes128_wrap_pad;
    public static final ASN1ObjectIdentifier id_aes192_CBC;
    public static final ASN1ObjectIdentifier id_aes192_CCM;
    public static final ASN1ObjectIdentifier id_aes192_CFB;
    public static final ASN1ObjectIdentifier id_aes192_ECB;
    public static final ASN1ObjectIdentifier id_aes192_GCM;
    public static final ASN1ObjectIdentifier id_aes192_OFB;
    public static final ASN1ObjectIdentifier id_aes192_wrap;
    public static final ASN1ObjectIdentifier id_aes192_wrap_pad;
    public static final ASN1ObjectIdentifier id_aes256_CBC;
    public static final ASN1ObjectIdentifier id_aes256_CCM;
    public static final ASN1ObjectIdentifier id_aes256_CFB;
    public static final ASN1ObjectIdentifier id_aes256_ECB;
    public static final ASN1ObjectIdentifier id_aes256_GCM;
    public static final ASN1ObjectIdentifier id_aes256_OFB;
    public static final ASN1ObjectIdentifier id_aes256_wrap;
    public static final ASN1ObjectIdentifier id_aes256_wrap_pad;
    public static final ASN1ObjectIdentifier id_dsa_with_sha2;
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_224;
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_256;
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_384;
    public static final ASN1ObjectIdentifier id_dsa_with_sha3_512;
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_224;
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_256;
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_384;
    public static final ASN1ObjectIdentifier id_ecdsa_with_sha3_512;
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_224;
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_256;
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_384;
    public static final ASN1ObjectIdentifier id_hmacWithSHA3_512;
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_224;
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_256;
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_384;
    public static final ASN1ObjectIdentifier id_rsassa_pkcs1_v1_5_with_sha3_512;
    public static final ASN1ObjectIdentifier id_sha224;
    public static final ASN1ObjectIdentifier id_sha256;
    public static final ASN1ObjectIdentifier id_sha384;
    public static final ASN1ObjectIdentifier id_sha3_224;
    public static final ASN1ObjectIdentifier id_sha3_256;
    public static final ASN1ObjectIdentifier id_sha3_384;
    public static final ASN1ObjectIdentifier id_sha3_512;
    public static final ASN1ObjectIdentifier id_sha512;
    public static final ASN1ObjectIdentifier id_sha512_224;
    public static final ASN1ObjectIdentifier id_sha512_256;
    public static final ASN1ObjectIdentifier id_shake128;
    public static final ASN1ObjectIdentifier id_shake128_len;
    public static final ASN1ObjectIdentifier id_shake256;
    public static final ASN1ObjectIdentifier id_shake256_len;
    public static final ASN1ObjectIdentifier nistAlgorithm;
    public static final ASN1ObjectIdentifier sigAlgs;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("2.16.840.1.101.3.4");
        nistAlgorithm = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch(IOplusUIFirstManager.APP_EXIT_ANIMATION);
        hashAlgs = branch;
        id_sha256 = branch.branch(IOplusUIFirstManager.APP_START_ANIMATION);
        id_sha384 = branch.branch(IOplusUIFirstManager.APP_EXIT_ANIMATION);
        id_sha512 = branch.branch("3");
        id_sha224 = branch.branch(IOplusUIFirstManager.LAUNCHER_SI_START);
        id_sha512_224 = branch.branch("5");
        id_sha512_256 = branch.branch("6");
        id_sha3_224 = branch.branch("7");
        id_sha3_256 = branch.branch("8");
        id_sha3_384 = branch.branch("9");
        id_sha3_512 = branch.branch("10");
        id_shake128 = branch.branch("11");
        id_shake256 = branch.branch("12");
        id_hmacWithSHA3_224 = branch.branch("13");
        id_hmacWithSHA3_256 = branch.branch("14");
        id_hmacWithSHA3_384 = branch.branch("15");
        id_hmacWithSHA3_512 = branch.branch("16");
        id_shake128_len = branch.branch("17");
        id_shake256_len = branch.branch("18");
        id_KmacWithSHAKE128 = branch.branch("19");
        id_KmacWithSHAKE256 = branch.branch("20");
        ASN1ObjectIdentifier branch2 = aSN1ObjectIdentifier.branch(IOplusUIFirstManager.APP_START_ANIMATION);
        aes = branch2;
        id_aes128_ECB = branch2.branch(IOplusUIFirstManager.APP_START_ANIMATION);
        id_aes128_CBC = branch2.branch(IOplusUIFirstManager.APP_EXIT_ANIMATION);
        id_aes128_OFB = branch2.branch("3");
        id_aes128_CFB = branch2.branch(IOplusUIFirstManager.LAUNCHER_SI_START);
        id_aes128_wrap = branch2.branch("5");
        id_aes128_GCM = branch2.branch("6");
        id_aes128_CCM = branch2.branch("7");
        id_aes128_wrap_pad = branch2.branch("8");
        id_aes192_ECB = branch2.branch("21");
        id_aes192_CBC = branch2.branch("22");
        id_aes192_OFB = branch2.branch("23");
        id_aes192_CFB = branch2.branch("24");
        id_aes192_wrap = branch2.branch("25");
        id_aes192_GCM = branch2.branch("26");
        id_aes192_CCM = branch2.branch("27");
        id_aes192_wrap_pad = branch2.branch("28");
        id_aes256_ECB = branch2.branch("41");
        id_aes256_CBC = branch2.branch("42");
        id_aes256_OFB = branch2.branch("43");
        id_aes256_CFB = branch2.branch("44");
        id_aes256_wrap = branch2.branch("45");
        id_aes256_GCM = branch2.branch("46");
        id_aes256_CCM = branch2.branch("47");
        id_aes256_wrap_pad = branch2.branch("48");
        ASN1ObjectIdentifier branch3 = aSN1ObjectIdentifier.branch("3");
        sigAlgs = branch3;
        id_dsa_with_sha2 = branch3;
        dsa_with_sha224 = branch3.branch(IOplusUIFirstManager.APP_START_ANIMATION);
        dsa_with_sha256 = branch3.branch(IOplusUIFirstManager.APP_EXIT_ANIMATION);
        dsa_with_sha384 = branch3.branch("3");
        dsa_with_sha512 = branch3.branch(IOplusUIFirstManager.LAUNCHER_SI_START);
        id_dsa_with_sha3_224 = branch3.branch("5");
        id_dsa_with_sha3_256 = branch3.branch("6");
        id_dsa_with_sha3_384 = branch3.branch("7");
        id_dsa_with_sha3_512 = branch3.branch("8");
        id_ecdsa_with_sha3_224 = branch3.branch("9");
        id_ecdsa_with_sha3_256 = branch3.branch("10");
        id_ecdsa_with_sha3_384 = branch3.branch("11");
        id_ecdsa_with_sha3_512 = branch3.branch("12");
        id_rsassa_pkcs1_v1_5_with_sha3_224 = branch3.branch("13");
        id_rsassa_pkcs1_v1_5_with_sha3_256 = branch3.branch("14");
        id_rsassa_pkcs1_v1_5_with_sha3_384 = branch3.branch("15");
        id_rsassa_pkcs1_v1_5_with_sha3_512 = branch3.branch("16");
    }
}