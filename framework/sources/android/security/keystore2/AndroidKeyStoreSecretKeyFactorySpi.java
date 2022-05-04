package android.security.keystore2;

import android.security.GateKeeper;
import android.security.KeyStore;
import android.security.keymaster.KeymasterArguments;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyInfo;
import android.security.keystore.KeyProperties;
import android.system.keystore2.Authorization;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.ProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes2.dex */
public class AndroidKeyStoreSecretKeyFactorySpi extends SecretKeyFactorySpi {
    private final KeyStore mKeyStore = KeyStore.getInstance();

    @Override // javax.crypto.SecretKeyFactorySpi
    protected KeySpec engineGetKeySpec(SecretKey key, Class keySpecClass) throws InvalidKeySpecException {
        if (keySpecClass == null) {
            throw new InvalidKeySpecException("keySpecClass == null");
        } else if (!(key instanceof AndroidKeyStoreSecretKey)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Only Android KeyStore secret keys supported: ");
            sb.append(key != null ? key.getClass().getName() : "null");
            throw new InvalidKeySpecException(sb.toString());
        } else if (SecretKeySpec.class.isAssignableFrom(keySpecClass)) {
            throw new InvalidKeySpecException("Key material export of Android KeyStore keys is not supported");
        } else if (KeyInfo.class.equals(keySpecClass)) {
            AndroidKeyStoreKey keystoreKey = (AndroidKeyStoreKey) key;
            return getKeyInfo(keystoreKey);
        } else {
            throw new InvalidKeySpecException("Unsupported key spec: " + keySpecClass.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyInfo getKeyInfo(AndroidKeyStoreKey key) {
        IllegalArgumentException e;
        int i;
        Date keyValidityStart;
        Date keyValidityForOriginationEnd;
        Date keyValidityForConsumptionEnd;
        StringBuilder sb;
        int origin = -1;
        int keySize = -1;
        int purposes = 0;
        List<String> digestsList = new ArrayList<>();
        List<String> blockModesList = new ArrayList<>();
        int keymasterSwEnforcedUserAuthenticators = 0;
        int keymasterHwEnforcedUserAuthenticators = 0;
        List<BigInteger> keymasterSecureUserIds = new ArrayList<>();
        List<String> encryptionPaddingsList = new ArrayList<>();
        List<String> signaturePaddingsList = new ArrayList<>();
        Date keyValidityStart2 = null;
        Date keyValidityForOriginationEnd2 = null;
        Date keyValidityForConsumptionEnd2 = null;
        boolean userAuthenticationRequired = true;
        boolean userAuthenticationValidWhileOnBody = false;
        boolean trustedUserPresenceRequired = false;
        boolean trustedUserConfirmationRequired = false;
        int remainingUsageCount = -1;
        try {
            Authorization[] authorizations = key.getAuthorizations();
            int securityLevel = 0;
            try {
                int securityLevel2 = authorizations.length;
                boolean insideSecureHardware = false;
                long userAuthenticationValidityDurationSeconds = 0;
                int i2 = 0;
                while (i2 < securityLevel2) {
                    try {
                        Authorization a = authorizations[i2];
                        switch (a.keyParameter.tag) {
                            case -1610612234:
                                keyValidityStart = keyValidityStart2;
                                keyValidityForOriginationEnd = keyValidityForOriginationEnd2;
                                keyValidityForConsumptionEnd = keyValidityForConsumptionEnd2;
                                keymasterSecureUserIds.add(KeymasterArguments.toUint64(a.keyParameter.value.getLongInteger()));
                                break;
                            case 268435960:
                                int authenticatorType = a.keyParameter.value.getHardwareAuthenticatorType();
                                if (KeyStore2ParameterUtils.isSecureHardware(a.securityLevel)) {
                                    keymasterHwEnforcedUserAuthenticators = authenticatorType;
                                    keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd2;
                                    keyValidityForOriginationEnd2 = keyValidityForOriginationEnd2;
                                    keyValidityStart2 = keyValidityStart2;
                                    continue;
                                } else {
                                    keymasterSwEnforcedUserAuthenticators = authenticatorType;
                                    keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd2;
                                    keyValidityForOriginationEnd2 = keyValidityForOriginationEnd2;
                                    keyValidityStart2 = keyValidityStart2;
                                }
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 268436158:
                                boolean insideSecureHardware2 = KeyStore2ParameterUtils.isSecureHardware(a.securityLevel);
                                try {
                                    int securityLevel3 = a.securityLevel;
                                    try {
                                        int origin2 = KeyProperties.Origin.fromKeymaster(a.keyParameter.value.getOrigin());
                                        origin = origin2;
                                        insideSecureHardware = insideSecureHardware2;
                                        securityLevel = securityLevel3;
                                        keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd2;
                                        keyValidityForOriginationEnd2 = keyValidityForOriginationEnd2;
                                        keyValidityStart2 = keyValidityStart2;
                                        continue;
                                        i2++;
                                        securityLevel2 = securityLevel2;
                                        authorizations = authorizations;
                                    } catch (IllegalArgumentException e2) {
                                        e = e2;
                                        throw new ProviderException("Unsupported key characteristic", e);
                                    }
                                } catch (IllegalArgumentException e3) {
                                    e = e3;
                                }
                            case 536870913:
                                purposes = KeyProperties.Purpose.fromKeymaster(a.keyParameter.value.getKeyPurpose()) | purposes;
                                keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd2;
                                keyValidityForOriginationEnd2 = keyValidityForOriginationEnd2;
                                keyValidityStart2 = keyValidityStart2;
                                continue;
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 536870916:
                                keyValidityStart = keyValidityStart2;
                                keyValidityForOriginationEnd = keyValidityForOriginationEnd2;
                                keyValidityForConsumptionEnd = keyValidityForConsumptionEnd2;
                                blockModesList.add(KeyProperties.BlockMode.fromKeymaster(a.keyParameter.value.getBlockMode()));
                                break;
                            case 536870917:
                                keyValidityStart = keyValidityStart2;
                                keyValidityForOriginationEnd = keyValidityForOriginationEnd2;
                                keyValidityForConsumptionEnd = keyValidityForConsumptionEnd2;
                                digestsList.add(KeyProperties.Digest.fromKeymaster(a.keyParameter.value.getDigest()));
                                break;
                            case 536870918:
                                keyValidityStart = keyValidityStart2;
                                keyValidityForOriginationEnd = keyValidityForOriginationEnd2;
                                keyValidityForConsumptionEnd = keyValidityForConsumptionEnd2;
                                int paddingMode = a.keyParameter.value.getPaddingMode();
                                if (paddingMode == 5 || paddingMode == 3) {
                                    String padding = KeyProperties.SignaturePadding.fromKeymaster(paddingMode);
                                    signaturePaddingsList.add(padding);
                                    break;
                                } else {
                                    try {
                                        String jcaPadding = KeyProperties.EncryptionPadding.fromKeymaster(paddingMode);
                                        encryptionPaddingsList.add(jcaPadding);
                                        break;
                                    } catch (IllegalArgumentException e4) {
                                        throw new ProviderException("Unsupported padding: " + paddingMode);
                                    }
                                }
                                break;
                            case 805306371:
                                long keySizeUnsigned = KeyStore2ParameterUtils.getUnsignedInt(a);
                                if (keySizeUnsigned <= 2147483647L) {
                                    int keySize2 = (int) keySizeUnsigned;
                                    keySize = keySize2;
                                    keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd2;
                                    keyValidityForOriginationEnd2 = keyValidityForOriginationEnd2;
                                    keyValidityStart2 = keyValidityStart2;
                                    continue;
                                    i2++;
                                    securityLevel2 = securityLevel2;
                                    authorizations = authorizations;
                                } else {
                                    throw new ProviderException("Key too large: " + keySizeUnsigned + " bits");
                                }
                            case 805306773:
                                keyValidityStart = keyValidityStart2;
                                keyValidityForOriginationEnd = keyValidityForOriginationEnd2;
                                keyValidityForConsumptionEnd = keyValidityForConsumptionEnd2;
                                try {
                                    long remainingUsageCountUnsigned = KeyStore2ParameterUtils.getUnsignedInt(a);
                                    if (remainingUsageCountUnsigned <= 2147483647L) {
                                        remainingUsageCount = (int) remainingUsageCountUnsigned;
                                        keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd;
                                        keyValidityForOriginationEnd2 = keyValidityForOriginationEnd;
                                        keyValidityStart2 = keyValidityStart;
                                        continue;
                                        i2++;
                                        securityLevel2 = securityLevel2;
                                        authorizations = authorizations;
                                    } else {
                                        throw new ProviderException("Usage count of limited use key too long: " + remainingUsageCountUnsigned);
                                    }
                                } catch (IllegalArgumentException e5) {
                                    e = e5;
                                    throw new ProviderException("Unsupported key characteristic", e);
                                }
                            case 805306873:
                                long userAuthenticationValidityDurationSeconds2 = KeyStore2ParameterUtils.getUnsignedInt(a);
                                if (userAuthenticationValidityDurationSeconds2 <= 2147483647L) {
                                    userAuthenticationValidityDurationSeconds = userAuthenticationValidityDurationSeconds2;
                                    keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd2;
                                    keyValidityForOriginationEnd2 = keyValidityForOriginationEnd2;
                                    continue;
                                    i2++;
                                    securityLevel2 = securityLevel2;
                                    authorizations = authorizations;
                                } else {
                                    try {
                                        sb = new StringBuilder();
                                    } catch (IllegalArgumentException e6) {
                                        e = e6;
                                    }
                                    try {
                                        sb.append("User authentication timeout validity too long: ");
                                        sb.append(userAuthenticationValidityDurationSeconds2);
                                        sb.append(" seconds");
                                        throw new ProviderException(sb.toString());
                                    } catch (IllegalArgumentException e7) {
                                        e = e7;
                                        throw new ProviderException("Unsupported key characteristic", e);
                                    }
                                }
                            case 1610613136:
                                Date keyValidityStart3 = KeyStore2ParameterUtils.getDate(a);
                                keyValidityStart2 = keyValidityStart3;
                                continue;
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 1610613137:
                                Date keyValidityForOriginationEnd3 = KeyStore2ParameterUtils.getDate(a);
                                keyValidityForOriginationEnd2 = keyValidityForOriginationEnd3;
                                continue;
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 1610613138:
                                Date keyValidityForConsumptionEnd3 = KeyStore2ParameterUtils.getDate(a);
                                keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd3;
                                continue;
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 1879048695:
                                userAuthenticationRequired = false;
                                continue;
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 1879048698:
                                boolean userAuthenticationValidWhileOnBody2 = KeyStore2ParameterUtils.isSecureHardware(a.securityLevel);
                                userAuthenticationValidWhileOnBody = userAuthenticationValidWhileOnBody2;
                                continue;
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 1879048699:
                                boolean trustedUserPresenceRequired2 = KeyStore2ParameterUtils.isSecureHardware(a.securityLevel);
                                trustedUserPresenceRequired = trustedUserPresenceRequired2;
                                continue;
                                i2++;
                                securityLevel2 = securityLevel2;
                                authorizations = authorizations;
                            case 1879048700:
                                try {
                                    boolean trustedUserConfirmationRequired2 = KeyStore2ParameterUtils.isSecureHardware(a.securityLevel);
                                    trustedUserConfirmationRequired = trustedUserConfirmationRequired2;
                                    continue;
                                    i2++;
                                    securityLevel2 = securityLevel2;
                                    authorizations = authorizations;
                                } catch (IllegalArgumentException e8) {
                                    e = e8;
                                    throw new ProviderException("Unsupported key characteristic", e);
                                }
                            default:
                                keyValidityStart = keyValidityStart2;
                                keyValidityForOriginationEnd = keyValidityForOriginationEnd2;
                                keyValidityForConsumptionEnd = keyValidityForConsumptionEnd2;
                                break;
                        }
                        keyValidityForConsumptionEnd2 = keyValidityForConsumptionEnd;
                        keyValidityForOriginationEnd2 = keyValidityForOriginationEnd;
                        keyValidityStart2 = keyValidityStart;
                        i2++;
                        securityLevel2 = securityLevel2;
                        authorizations = authorizations;
                    } catch (IllegalArgumentException e9) {
                        e = e9;
                    }
                }
                if (keySize == -1) {
                    throw new ProviderException("Key size not available");
                } else if (origin != -1) {
                    String[] encryptionPaddings = (String[]) encryptionPaddingsList.toArray(new String[0]);
                    String[] signaturePaddings = (String[]) signaturePaddingsList.toArray(new String[0]);
                    boolean userAuthenticationRequirementEnforcedBySecureHardware = userAuthenticationRequired && keymasterHwEnforcedUserAuthenticators != 0 && keymasterSwEnforcedUserAuthenticators == 0;
                    String[] digests = (String[]) digestsList.toArray(new String[0]);
                    String[] blockModes = (String[]) blockModesList.toArray(new String[0]);
                    boolean invalidatedByBiometricEnrollment = false;
                    if (keymasterSwEnforcedUserAuthenticators == 2 || keymasterHwEnforcedUserAuthenticators == 2) {
                        invalidatedByBiometricEnrollment = !keymasterSecureUserIds.isEmpty() && !keymasterSecureUserIds.contains(getGateKeeperSecureUserId());
                    }
                    String str = key.getUserKeyDescriptor().alias;
                    int keymasterHwEnforcedUserAuthenticators2 = (int) userAuthenticationValidityDurationSeconds;
                    if (userAuthenticationRequirementEnforcedBySecureHardware) {
                        i = keymasterHwEnforcedUserAuthenticators;
                    } else {
                        i = keymasterSwEnforcedUserAuthenticators;
                    }
                    return new KeyInfo(str, insideSecureHardware, origin, keySize, keyValidityStart2, keyValidityForOriginationEnd2, keyValidityForConsumptionEnd2, purposes, encryptionPaddings, signaturePaddings, digests, blockModes, userAuthenticationRequired, keymasterHwEnforcedUserAuthenticators2, i, userAuthenticationRequirementEnforcedBySecureHardware, userAuthenticationValidWhileOnBody, trustedUserPresenceRequired, invalidatedByBiometricEnrollment, trustedUserConfirmationRequired, securityLevel, remainingUsageCount);
                } else {
                    throw new ProviderException("Key origin not available");
                }
            } catch (IllegalArgumentException e10) {
                e = e10;
            }
        } catch (IllegalArgumentException e11) {
            e = e11;
        }
    }

    private static BigInteger getGateKeeperSecureUserId() throws ProviderException {
        try {
            return BigInteger.valueOf(GateKeeper.getSecureUserId());
        } catch (IllegalStateException e) {
            throw new ProviderException("Failed to get GateKeeper secure user ID", e);
        }
    }

    @Override // javax.crypto.SecretKeyFactorySpi
    protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
        throw new InvalidKeySpecException("To generate secret key in Android Keystore, use KeyGenerator initialized with " + KeyGenParameterSpec.class.getName());
    }

    @Override // javax.crypto.SecretKeyFactorySpi
    protected SecretKey engineTranslateKey(SecretKey key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        } else if (key instanceof AndroidKeyStoreSecretKey) {
            return key;
        } else {
            throw new InvalidKeyException("To import a secret key into Android Keystore, use KeyStore.setEntry");
        }
    }
}
