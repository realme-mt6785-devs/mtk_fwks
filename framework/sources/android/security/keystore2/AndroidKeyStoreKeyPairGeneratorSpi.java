package android.security.keystore2;

import android.app.ActivityThread;
import android.app.AppGlobals;
import android.hardware.security.keymint.KeyParameter;
import android.hardware.security.keymint.Tag;
import android.os.Build;
import android.os.RemoteException;
import android.security.GenerateRkpKey;
import android.security.GenerateRkpKeyException;
import android.security.KeyPairGeneratorSpec;
import android.security.KeyStore2;
import android.security.KeyStoreException;
import android.security.KeyStoreSecurityLevel;
import android.security.keymaster.KeymasterArguments;
import android.security.keystore.ArrayUtils;
import android.security.keystore.DeviceIdAttestationException;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.security.keystore.SecureKeyImportUnavailableException;
import android.security.keystore.StrongBoxUnavailableException;
import android.system.keystore2.Authorization;
import android.system.keystore2.KeyDescriptor;
import android.system.keystore2.KeyEntryResponse;
import android.system.keystore2.KeyMetadata;
import android.telephony.TelephonyManager;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.logging.nano.MetricsProto;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGeneratorSpi;
import java.security.ProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import libcore.util.EmptyArray;
/* loaded from: classes2.dex */
public abstract class AndroidKeyStoreKeyPairGeneratorSpi extends KeyPairGeneratorSpi {
    private static final int EC_DEFAULT_KEY_SIZE = 256;
    private static final int RSA_DEFAULT_KEY_SIZE = 2048;
    private static final int RSA_MAX_KEY_SIZE = 8192;
    private static final int RSA_MIN_KEY_SIZE = 512;
    private static final List<String> SUPPORTED_EC_NIST_CURVE_NAMES;
    private static final Map<String, Integer> SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE;
    private static final List<Integer> SUPPORTED_EC_NIST_CURVE_SIZES;
    private static final String TAG = "AndroidKeyStoreKeyPairGeneratorSpi";
    private KeyDescriptor mAttestKeyDescriptor;
    private String mEntryAlias;
    private int mEntryNamespace;
    private String mJcaKeyAlgorithm;
    private int mKeySizeBits;
    private KeyStore2 mKeyStore;
    private int mKeymasterAlgorithm = -1;
    private int[] mKeymasterBlockModes;
    private int[] mKeymasterDigests;
    private int[] mKeymasterEncryptionPaddings;
    private int[] mKeymasterPurposes;
    private int[] mKeymasterSignaturePaddings;
    private final int mOriginalKeymasterAlgorithm;
    private Long mRSAPublicExponent;
    private SecureRandom mRng;
    private KeyGenParameterSpec mSpec;

    /* loaded from: classes2.dex */
    public static class RSA extends AndroidKeyStoreKeyPairGeneratorSpi {
        public RSA() {
            super(1);
        }
    }

    /* loaded from: classes2.dex */
    public static class EC extends AndroidKeyStoreKeyPairGeneratorSpi {
        public EC() {
            super(3);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE = hashMap;
        ArrayList arrayList = new ArrayList();
        SUPPORTED_EC_NIST_CURVE_NAMES = arrayList;
        ArrayList arrayList2 = new ArrayList();
        SUPPORTED_EC_NIST_CURVE_SIZES = arrayList2;
        hashMap.put("p-224", 224);
        hashMap.put("secp224r1", 224);
        hashMap.put("p-256", 256);
        hashMap.put("secp256r1", 256);
        hashMap.put("prime256v1", 256);
        Integer valueOf = Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SHOW_SETTINGS_SUGGESTION);
        hashMap.put("p-384", valueOf);
        hashMap.put("secp384r1", valueOf);
        hashMap.put("p-521", 521);
        hashMap.put("secp521r1", 521);
        arrayList.addAll(hashMap.keySet());
        Collections.sort(arrayList);
        arrayList2.addAll(new HashSet(hashMap.values()));
        Collections.sort(arrayList2);
    }

    protected AndroidKeyStoreKeyPairGeneratorSpi(int keymasterAlgorithm) {
        this.mOriginalKeymasterAlgorithm = keymasterAlgorithm;
    }

    private int keySize2EcCurve(int keySizeBits) throws InvalidAlgorithmParameterException {
        switch (keySizeBits) {
            case 224:
                return 0;
            case 256:
                return 1;
            case MetricsProto.MetricsEvent.ACTION_SHOW_SETTINGS_SUGGESTION /* 384 */:
                return 2;
            case 521:
                return 3;
            default:
                throw new InvalidAlgorithmParameterException("Unsupported EC curve keysize: " + keySizeBits);
        }
    }

    @Override // java.security.KeyPairGeneratorSpi
    public void initialize(int keysize, SecureRandom random) {
        throw new IllegalArgumentException(KeyGenParameterSpec.class.getName() + " or " + KeyPairGeneratorSpec.class.getName() + " required to initialize this KeyPairGenerator");
    }

    @Override // java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec params, SecureRandom random) throws InvalidAlgorithmParameterException {
        KeyGenParameterSpec spec;
        int[] iArr;
        resetAll();
        try {
            if (params != null) {
                int keymasterAlgorithm = this.mOriginalKeymasterAlgorithm;
                if (params instanceof KeyGenParameterSpec) {
                    spec = (KeyGenParameterSpec) params;
                } else if (params instanceof KeyPairGeneratorSpec) {
                    KeyPairGeneratorSpec legacySpec = (KeyPairGeneratorSpec) params;
                    try {
                        keymasterAlgorithm = getKeymasterAlgorithmFromLegacy(keymasterAlgorithm, legacySpec);
                        spec = buildKeyGenParameterSpecFromLegacy(legacySpec, keymasterAlgorithm);
                    } catch (IllegalArgumentException | NullPointerException e) {
                        throw new InvalidAlgorithmParameterException(e);
                    }
                } else {
                    throw new InvalidAlgorithmParameterException("Unsupported params class: " + params.getClass().getName() + ". Supported: " + KeyGenParameterSpec.class.getName() + ", " + KeyPairGeneratorSpec.class.getName());
                }
                this.mEntryAlias = spec.getKeystoreAlias();
                this.mEntryNamespace = spec.getNamespace();
                this.mSpec = spec;
                this.mKeymasterAlgorithm = keymasterAlgorithm;
                this.mKeySizeBits = spec.getKeySize();
                initAlgorithmSpecificParameters();
                if (this.mKeySizeBits == -1) {
                    this.mKeySizeBits = getDefaultKeySize(keymasterAlgorithm);
                }
                checkValidKeySize(keymasterAlgorithm, this.mKeySizeBits, this.mSpec.isStrongBoxBacked());
                if (spec.getKeystoreAlias() != null) {
                    try {
                        String jcaKeyAlgorithm = KeyProperties.KeyAlgorithm.fromKeymasterAsymmetricKeyAlgorithm(keymasterAlgorithm);
                        this.mKeymasterPurposes = KeyProperties.Purpose.allToKeymaster(spec.getPurposes());
                        this.mKeymasterBlockModes = KeyProperties.BlockMode.allToKeymaster(spec.getBlockModes());
                        this.mKeymasterEncryptionPaddings = KeyProperties.EncryptionPadding.allToKeymaster(spec.getEncryptionPaddings());
                        if ((spec.getPurposes() & 1) != 0 && spec.isRandomizedEncryptionRequired()) {
                            for (int keymasterPadding : this.mKeymasterEncryptionPaddings) {
                                if (!KeymasterUtils.isKeymasterPaddingSchemeIndCpaCompatibleWithAsymmetricCrypto(keymasterPadding)) {
                                    throw new InvalidAlgorithmParameterException("Randomized encryption (IND-CPA) required but may be violated by padding scheme: " + KeyProperties.EncryptionPadding.fromKeymaster(keymasterPadding) + ". See " + KeyGenParameterSpec.class.getName() + " documentation.");
                                }
                            }
                        }
                        this.mKeymasterSignaturePaddings = KeyProperties.SignaturePadding.allToKeymaster(spec.getSignaturePaddings());
                        if (spec.isDigestsSpecified()) {
                            this.mKeymasterDigests = KeyProperties.Digest.allToKeymaster(spec.getDigests());
                        } else {
                            this.mKeymasterDigests = EmptyArray.INT;
                        }
                        KeyStore2ParameterUtils.addUserAuthArgs(new ArrayList(), this.mSpec);
                        this.mJcaKeyAlgorithm = jcaKeyAlgorithm;
                        this.mRng = random;
                        this.mKeyStore = KeyStore2.getInstance();
                        this.mAttestKeyDescriptor = buildAndCheckAttestKeyDescriptor(spec);
                        checkAttestKeyPurpose(spec);
                        if (1 == 0) {
                            resetAll();
                        }
                    } catch (IllegalArgumentException | IllegalStateException e2) {
                        throw new InvalidAlgorithmParameterException(e2);
                    }
                } else {
                    throw new InvalidAlgorithmParameterException("KeyStore entry alias not provided");
                }
            } else {
                throw new InvalidAlgorithmParameterException("Must supply params of type " + KeyGenParameterSpec.class.getName() + " or " + KeyPairGeneratorSpec.class.getName());
            }
        } catch (Throwable th) {
            if (0 == 0) {
                resetAll();
            }
            throw th;
        }
    }

    private void checkAttestKeyPurpose(KeyGenParameterSpec spec) throws InvalidAlgorithmParameterException {
        if ((spec.getPurposes() & 128) != 0 && spec.getPurposes() != 128) {
            throw new InvalidAlgorithmParameterException("PURPOSE_ATTEST_KEY may not be specified with any other purposes");
        }
    }

    private KeyDescriptor buildAndCheckAttestKeyDescriptor(KeyGenParameterSpec spec) throws InvalidAlgorithmParameterException {
        if (spec.getAttestKeyAlias() == null) {
            return null;
        }
        KeyDescriptor attestKeyDescriptor = new KeyDescriptor();
        attestKeyDescriptor.domain = 0;
        attestKeyDescriptor.alias = spec.getAttestKeyAlias();
        try {
            KeyEntryResponse attestKey = this.mKeyStore.getKeyEntry(attestKeyDescriptor);
            checkAttestKeyChallenge(spec);
            checkAttestKeyPurpose(attestKey.metadata.authorizations);
            checkAttestKeySecurityLevel(spec, attestKey);
            return attestKeyDescriptor;
        } catch (KeyStoreException e) {
            throw new InvalidAlgorithmParameterException("Invalid attestKeyAlias", e);
        }
    }

    private void checkAttestKeyChallenge(KeyGenParameterSpec spec) throws InvalidAlgorithmParameterException {
        if (spec.getAttestationChallenge() == null) {
            throw new InvalidAlgorithmParameterException("AttestKey specified but no attestation challenge provided");
        }
    }

    private void checkAttestKeyPurpose(Authorization[] keyAuths) throws InvalidAlgorithmParameterException {
        if (Arrays.stream(keyAuths).noneMatch(AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda5.INSTANCE)) {
            throw new InvalidAlgorithmParameterException("Invalid attestKey, does not have PURPOSE_ATTEST_KEY");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$checkAttestKeyPurpose$0(Authorization x) {
        return x.keyParameter.tag == 536870913 && x.keyParameter.value.getKeyPurpose() == 7;
    }

    private void checkAttestKeySecurityLevel(KeyGenParameterSpec spec, KeyEntryResponse key) throws InvalidAlgorithmParameterException {
        boolean attestKeyInStrongBox = key.metadata.keySecurityLevel == 2;
        if (spec.isStrongBoxBacked() == attestKeyInStrongBox) {
            return;
        }
        if (attestKeyInStrongBox) {
            throw new InvalidAlgorithmParameterException("Invalid security level: Cannot sign non-StrongBox key with StrongBox attestKey");
        }
        throw new InvalidAlgorithmParameterException("Invalid security level: Cannot sign StrongBox key with non-StrongBox attestKey");
    }

    private int getKeymasterAlgorithmFromLegacy(int keymasterAlgorithm, KeyPairGeneratorSpec legacySpec) throws InvalidAlgorithmParameterException {
        String specKeyAlgorithm = legacySpec.getKeyType();
        if (specKeyAlgorithm == null) {
            return keymasterAlgorithm;
        }
        try {
            int keymasterAlgorithm2 = KeyProperties.KeyAlgorithm.toKeymasterAsymmetricKeyAlgorithm(specKeyAlgorithm);
            return keymasterAlgorithm2;
        } catch (IllegalArgumentException e) {
            throw new InvalidAlgorithmParameterException("Invalid key type in parameters", e);
        }
    }

    private KeyGenParameterSpec buildKeyGenParameterSpecFromLegacy(KeyPairGeneratorSpec legacySpec, int keymasterAlgorithm) {
        KeyGenParameterSpec.Builder specBuilder;
        switch (keymasterAlgorithm) {
            case 1:
                specBuilder = new KeyGenParameterSpec.Builder(legacySpec.getKeystoreAlias(), 15);
                specBuilder.setDigests(KeyProperties.DIGEST_NONE, KeyProperties.DIGEST_MD5, KeyProperties.DIGEST_SHA1, KeyProperties.DIGEST_SHA224, "SHA-256", KeyProperties.DIGEST_SHA384, KeyProperties.DIGEST_SHA512);
                specBuilder.setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE, KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1, KeyProperties.ENCRYPTION_PADDING_RSA_OAEP);
                specBuilder.setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1, KeyProperties.SIGNATURE_PADDING_RSA_PSS);
                specBuilder.setRandomizedEncryptionRequired(false);
                break;
            case 2:
            default:
                throw new ProviderException("Unsupported algorithm: " + this.mKeymasterAlgorithm);
            case 3:
                specBuilder = new KeyGenParameterSpec.Builder(legacySpec.getKeystoreAlias(), 12);
                specBuilder.setDigests(KeyProperties.DIGEST_NONE, KeyProperties.DIGEST_SHA1, KeyProperties.DIGEST_SHA224, "SHA-256", KeyProperties.DIGEST_SHA384, KeyProperties.DIGEST_SHA512);
                break;
        }
        if (legacySpec.getKeySize() != -1) {
            specBuilder.setKeySize(legacySpec.getKeySize());
        }
        if (legacySpec.getAlgorithmParameterSpec() != null) {
            specBuilder.setAlgorithmParameterSpec(legacySpec.getAlgorithmParameterSpec());
        }
        specBuilder.setCertificateSubject(legacySpec.getSubjectDN());
        specBuilder.setCertificateSerialNumber(legacySpec.getSerialNumber());
        specBuilder.setCertificateNotBefore(legacySpec.getStartDate());
        specBuilder.setCertificateNotAfter(legacySpec.getEndDate());
        specBuilder.setUserAuthenticationRequired(false);
        return specBuilder.build();
    }

    private void resetAll() {
        this.mEntryAlias = null;
        this.mEntryNamespace = -1;
        this.mJcaKeyAlgorithm = null;
        this.mKeymasterAlgorithm = -1;
        this.mKeymasterPurposes = null;
        this.mKeymasterBlockModes = null;
        this.mKeymasterEncryptionPaddings = null;
        this.mKeymasterSignaturePaddings = null;
        this.mKeymasterDigests = null;
        this.mKeySizeBits = 0;
        this.mSpec = null;
        this.mRSAPublicExponent = null;
        this.mRng = null;
        this.mKeyStore = null;
    }

    private void initAlgorithmSpecificParameters() throws InvalidAlgorithmParameterException {
        AlgorithmParameterSpec algSpecificSpec = this.mSpec.getAlgorithmParameterSpec();
        switch (this.mKeymasterAlgorithm) {
            case 1:
                BigInteger publicExponent = null;
                if (algSpecificSpec instanceof RSAKeyGenParameterSpec) {
                    RSAKeyGenParameterSpec rsaSpec = (RSAKeyGenParameterSpec) algSpecificSpec;
                    int i = this.mKeySizeBits;
                    if (i == -1) {
                        this.mKeySizeBits = rsaSpec.getKeysize();
                    } else if (i != rsaSpec.getKeysize()) {
                        throw new InvalidAlgorithmParameterException("RSA key size must match  between " + this.mSpec + " and " + algSpecificSpec + ": " + this.mKeySizeBits + " vs " + rsaSpec.getKeysize());
                    }
                    publicExponent = rsaSpec.getPublicExponent();
                } else if (algSpecificSpec != null) {
                    throw new InvalidAlgorithmParameterException("RSA may only use RSAKeyGenParameterSpec");
                }
                if (publicExponent == null) {
                    publicExponent = RSAKeyGenParameterSpec.F4;
                }
                if (publicExponent.compareTo(BigInteger.ZERO) < 1) {
                    throw new InvalidAlgorithmParameterException("RSA public exponent must be positive: " + publicExponent);
                } else if (publicExponent.signum() == -1 || publicExponent.compareTo(KeymasterArguments.UINT64_MAX_VALUE) > 0) {
                    throw new InvalidAlgorithmParameterException("Unsupported RSA public exponent: " + publicExponent + ". Maximum supported value: " + KeymasterArguments.UINT64_MAX_VALUE);
                } else {
                    this.mRSAPublicExponent = Long.valueOf(publicExponent.longValue());
                    return;
                }
            case 2:
            default:
                throw new ProviderException("Unsupported algorithm: " + this.mKeymasterAlgorithm);
            case 3:
                if (algSpecificSpec instanceof ECGenParameterSpec) {
                    ECGenParameterSpec ecSpec = (ECGenParameterSpec) algSpecificSpec;
                    String curveName = ecSpec.getName();
                    Integer ecSpecKeySizeBits = SUPPORTED_EC_NIST_CURVE_NAME_TO_SIZE.get(curveName.toLowerCase(Locale.US));
                    if (ecSpecKeySizeBits != null) {
                        int i2 = this.mKeySizeBits;
                        if (i2 == -1) {
                            this.mKeySizeBits = ecSpecKeySizeBits.intValue();
                            return;
                        } else if (i2 != ecSpecKeySizeBits.intValue()) {
                            throw new InvalidAlgorithmParameterException("EC key size must match  between " + this.mSpec + " and " + algSpecificSpec + ": " + this.mKeySizeBits + " vs " + ecSpecKeySizeBits);
                        } else {
                            return;
                        }
                    } else {
                        throw new InvalidAlgorithmParameterException("Unsupported EC curve name: " + curveName + ". Supported: " + SUPPORTED_EC_NIST_CURVE_NAMES);
                    }
                } else if (algSpecificSpec != null) {
                    throw new InvalidAlgorithmParameterException("EC may only use ECGenParameterSpec");
                } else {
                    return;
                }
        }
    }

    @Override // java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        try {
            return generateKeyPairHelper();
        } catch (GenerateRkpKeyException e) {
            try {
                return generateKeyPairHelper();
            } catch (GenerateRkpKeyException e2) {
                throw new ProviderException("Failed to provision new attestation keys.");
            }
        }
    }

    private KeyPair generateKeyPairHelper() throws GenerateRkpKeyException {
        KeyGenParameterSpec keyGenParameterSpec;
        int securityLevel;
        int flags;
        if (this.mKeyStore == null || (keyGenParameterSpec = this.mSpec) == null) {
            throw new IllegalStateException("Not initialized");
        }
        int i = 2;
        if (keyGenParameterSpec.isStrongBoxBacked()) {
            securityLevel = 2;
        } else {
            securityLevel = 1;
        }
        if (this.mSpec.isCriticalToDeviceEncryption()) {
            flags = 1;
        } else {
            flags = 0;
        }
        byte[] additionalEntropy = KeyStoreCryptoOperationUtils.getRandomBytesToMixIntoKeystoreRng(this.mRng, (this.mKeySizeBits + 7) / 8);
        KeyDescriptor descriptor = new KeyDescriptor();
        descriptor.alias = this.mEntryAlias;
        if (this.mEntryNamespace == -1) {
            i = 0;
        }
        descriptor.domain = i;
        descriptor.nspace = this.mEntryNamespace;
        descriptor.blob = null;
        boolean success = false;
        try {
            try {
                try {
                    KeyStoreSecurityLevel iSecurityLevel = this.mKeyStore.getSecurityLevel(securityLevel);
                    KeyMetadata metadata = iSecurityLevel.generateKey(descriptor, this.mAttestKeyDescriptor, constructKeyGenerationArguments(), flags, additionalEntropy);
                    AndroidKeyStorePublicKey publicKey = AndroidKeyStoreProvider.makeAndroidKeyStorePublicKeyFromKeyEntryResponse(descriptor, metadata, iSecurityLevel, this.mKeymasterAlgorithm);
                    GenerateRkpKey keyGen = new GenerateRkpKey(ActivityThread.currentApplication());
                    try {
                        if (this.mSpec.getAttestationChallenge() != null) {
                            keyGen.notifyKeyGenerated(securityLevel);
                        }
                    } catch (RemoteException e) {
                        Log.d(TAG, "Couldn't connect to the RemoteProvisioner backend.", e);
                    }
                    success = true;
                    return new KeyPair(publicKey, publicKey.getPrivateKey());
                } catch (DeviceIdAttestationException | IllegalArgumentException | InvalidAlgorithmParameterException | UnrecoverableKeyException e2) {
                    throw new ProviderException("Failed to construct key object from newly generated key pair.", e2);
                }
            } catch (KeyStoreException e3) {
                switch (e3.getErrorCode()) {
                    case -68:
                        throw new StrongBoxUnavailableException("Failed to generated key pair.", e3);
                    case 22:
                        GenerateRkpKey keyGen2 = new GenerateRkpKey(ActivityThread.currentApplication());
                        try {
                            keyGen2.notifyEmpty(securityLevel);
                            throw new GenerateRkpKeyException();
                        } catch (RemoteException f) {
                            throw new ProviderException("Failed to talk to RemoteProvisioner", f);
                        }
                    default:
                        ProviderException p = new ProviderException("Failed to generate key pair.", e3);
                        if ((this.mSpec.getPurposes() & 32) != 0) {
                            throw new SecureKeyImportUnavailableException(p);
                        }
                        throw p;
                }
            }
        } finally {
            if (!success) {
                try {
                    this.mKeyStore.deleteKey(descriptor);
                } catch (KeyStoreException e4) {
                    if (e4.getErrorCode() != 7) {
                        Log.e(TAG, "Failed to delete newly generated key after generation failed unexpectedly.", e4);
                    }
                }
            }
        }
    }

    private void addAttestationParameters(List<KeyParameter> params) throws ProviderException, IllegalArgumentException, DeviceIdAttestationException {
        byte[] challenge = this.mSpec.getAttestationChallenge();
        if (challenge != null) {
            params.add(KeyStore2ParameterUtils.makeBytes(-1879047484, challenge));
            if (this.mSpec.isDevicePropertiesAttestationIncluded()) {
                params.add(KeyStore2ParameterUtils.makeBytes(-1879047482, Build.BRAND.getBytes(StandardCharsets.UTF_8)));
                params.add(KeyStore2ParameterUtils.makeBytes(-1879047481, Build.DEVICE.getBytes(StandardCharsets.UTF_8)));
                params.add(KeyStore2ParameterUtils.makeBytes(-1879047480, Build.PRODUCT.getBytes(StandardCharsets.UTF_8)));
                params.add(KeyStore2ParameterUtils.makeBytes(-1879047476, Build.MANUFACTURER.getBytes(StandardCharsets.UTF_8)));
                params.add(KeyStore2ParameterUtils.makeBytes(-1879047475, Build.MODEL.getBytes(StandardCharsets.UTF_8)));
            }
            int[] idTypes = this.mSpec.getAttestationIds();
            if (idTypes.length != 0) {
                Set<Integer> idTypesSet = new ArraySet<>(idTypes.length);
                for (int idType : idTypes) {
                    idTypesSet.add(Integer.valueOf(idType));
                }
                TelephonyManager telephonyService = null;
                if ((idTypesSet.contains(2) || idTypesSet.contains(3)) && (telephonyService = (TelephonyManager) AppGlobals.getInitialApplication().getSystemService("phone")) == null) {
                    throw new DeviceIdAttestationException("Unable to access telephony service");
                }
                for (Integer idType2 : idTypesSet) {
                    switch (idType2.intValue()) {
                        case 1:
                            params.add(KeyStore2ParameterUtils.makeBytes(-1879047479, Build.getSerial().getBytes(StandardCharsets.UTF_8)));
                            break;
                        case 2:
                            String imei = telephonyService.getImei(0);
                            if (imei != null) {
                                params.add(KeyStore2ParameterUtils.makeBytes(-1879047478, imei.getBytes(StandardCharsets.UTF_8)));
                                break;
                            } else {
                                throw new DeviceIdAttestationException("Unable to retrieve IMEI");
                            }
                        case 3:
                            String meid = telephonyService.getMeid(0);
                            if (meid != null) {
                                params.add(KeyStore2ParameterUtils.makeBytes(-1879047477, meid.getBytes(StandardCharsets.UTF_8)));
                                break;
                            } else {
                                throw new DeviceIdAttestationException("Unable to retrieve MEID");
                            }
                        case 4:
                            params.add(KeyStore2ParameterUtils.makeBool(1879048912));
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown device ID type " + idType2);
                    }
                }
            }
        }
    }

    private Collection<KeyParameter> constructKeyGenerationArguments() throws DeviceIdAttestationException, IllegalArgumentException, InvalidAlgorithmParameterException {
        final List<KeyParameter> params = new ArrayList<>();
        params.add(KeyStore2ParameterUtils.makeInt(805306371, this.mKeySizeBits));
        params.add(KeyStore2ParameterUtils.makeEnum(268435458, this.mKeymasterAlgorithm));
        if (this.mKeymasterAlgorithm == 3) {
            params.add(KeyStore2ParameterUtils.makeEnum(Tag.EC_CURVE, keySize2EcCurve(this.mKeySizeBits)));
        }
        ArrayUtils.forEach(this.mKeymasterPurposes, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                params.add(KeyStore2ParameterUtils.makeEnum(536870913, ((Integer) obj).intValue()));
            }
        });
        ArrayUtils.forEach(this.mKeymasterBlockModes, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                params.add(KeyStore2ParameterUtils.makeEnum(536870916, ((Integer) obj).intValue()));
            }
        });
        ArrayUtils.forEach(this.mKeymasterEncryptionPaddings, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                params.add(KeyStore2ParameterUtils.makeEnum(536870918, ((Integer) obj).intValue()));
            }
        });
        ArrayUtils.forEach(this.mKeymasterSignaturePaddings, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                params.add(KeyStore2ParameterUtils.makeEnum(536870918, ((Integer) obj).intValue()));
            }
        });
        ArrayUtils.forEach(this.mKeymasterDigests, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyPairGeneratorSpi$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                params.add(KeyStore2ParameterUtils.makeEnum(536870917, ((Integer) obj).intValue()));
            }
        });
        KeyStore2ParameterUtils.addUserAuthArgs(params, this.mSpec);
        if (this.mSpec.getKeyValidityStart() != null) {
            params.add(KeyStore2ParameterUtils.makeDate(1610613136, this.mSpec.getKeyValidityStart()));
        }
        if (this.mSpec.getKeyValidityForOriginationEnd() != null) {
            params.add(KeyStore2ParameterUtils.makeDate(1610613137, this.mSpec.getKeyValidityForOriginationEnd()));
        }
        if (this.mSpec.getKeyValidityForConsumptionEnd() != null) {
            params.add(KeyStore2ParameterUtils.makeDate(1610613138, this.mSpec.getKeyValidityForConsumptionEnd()));
        }
        if (this.mSpec.getCertificateNotAfter() != null) {
            params.add(KeyStore2ParameterUtils.makeDate(1610613745, this.mSpec.getCertificateNotAfter()));
        }
        if (this.mSpec.getCertificateNotBefore() != null) {
            params.add(KeyStore2ParameterUtils.makeDate(1610613744, this.mSpec.getCertificateNotBefore()));
        }
        if (this.mSpec.getCertificateSerialNumber() != null) {
            params.add(KeyStore2ParameterUtils.makeBignum(-2147482642, this.mSpec.getCertificateSerialNumber()));
        }
        if (this.mSpec.getCertificateSubject() != null) {
            params.add(KeyStore2ParameterUtils.makeBytes(-1879047185, this.mSpec.getCertificateSubject().getEncoded()));
        }
        if (this.mSpec.getMaxUsageCount() != -1) {
            params.add(KeyStore2ParameterUtils.makeInt(805306773, this.mSpec.getMaxUsageCount()));
        }
        addAlgorithmSpecificParameters(params);
        if (this.mSpec.isUniqueIdIncluded()) {
            params.add(KeyStore2ParameterUtils.makeBool(1879048394));
        }
        addAttestationParameters(params);
        return params;
    }

    private void addAlgorithmSpecificParameters(List<KeyParameter> params) {
        switch (this.mKeymasterAlgorithm) {
            case 1:
                params.add(KeyStore2ParameterUtils.makeLong(1342177480, this.mRSAPublicExponent.longValue()));
                return;
            case 2:
            default:
                throw new ProviderException("Unsupported algorithm: " + this.mKeymasterAlgorithm);
            case 3:
                return;
        }
    }

    private static int getDefaultKeySize(int keymasterAlgorithm) {
        switch (keymasterAlgorithm) {
            case 1:
                return 2048;
            case 2:
            default:
                throw new ProviderException("Unsupported algorithm: " + keymasterAlgorithm);
            case 3:
                return 256;
        }
    }

    private static void checkValidKeySize(int keymasterAlgorithm, int keySize, boolean isStrongBoxBacked) throws InvalidAlgorithmParameterException {
        switch (keymasterAlgorithm) {
            case 1:
                if (keySize < 512 || keySize > 8192) {
                    throw new InvalidAlgorithmParameterException("RSA key size must be >= 512 and <= 8192");
                }
                return;
            case 2:
            default:
                throw new ProviderException("Unsupported algorithm: " + keymasterAlgorithm);
            case 3:
                if (!isStrongBoxBacked || keySize == 256) {
                    List<Integer> list = SUPPORTED_EC_NIST_CURVE_SIZES;
                    if (!list.contains(Integer.valueOf(keySize))) {
                        throw new InvalidAlgorithmParameterException("Unsupported EC key size: " + keySize + " bits. Supported: " + list);
                    }
                    return;
                }
                throw new InvalidAlgorithmParameterException("Unsupported StrongBox EC key size: " + keySize + " bits. Supported: 256");
        }
    }

    private static String getCertificateSignatureAlgorithm(int keymasterAlgorithm, int keySizeBits, KeyGenParameterSpec spec) {
        if ((spec.getPurposes() & 4) == 0 || spec.isUserAuthenticationRequired() || !spec.isDigestsSpecified()) {
            return null;
        }
        switch (keymasterAlgorithm) {
            case 1:
                boolean pkcs1SignaturePaddingSupported = com.android.internal.util.ArrayUtils.contains(KeyProperties.SignaturePadding.allToKeymaster(spec.getSignaturePaddings()), 5);
                if (!pkcs1SignaturePaddingSupported) {
                    return null;
                }
                Set<Integer> availableKeymasterDigests = getAvailableKeymasterSignatureDigests(spec.getDigests(), AndroidKeyStoreBCWorkaroundProvider.getSupportedEcdsaSignatureDigests());
                int maxDigestOutputSizeBits = keySizeBits - 240;
                int bestKeymasterDigest = -1;
                int bestDigestOutputSizeBits = -1;
                for (Integer num : availableKeymasterDigests) {
                    int keymasterDigest = num.intValue();
                    int outputSizeBits = KeymasterUtils.getDigestOutputSizeBits(keymasterDigest);
                    if (outputSizeBits <= maxDigestOutputSizeBits) {
                        if (bestKeymasterDigest == -1) {
                            bestKeymasterDigest = keymasterDigest;
                            bestDigestOutputSizeBits = outputSizeBits;
                        } else if (outputSizeBits > bestDigestOutputSizeBits) {
                            bestKeymasterDigest = keymasterDigest;
                            bestDigestOutputSizeBits = outputSizeBits;
                        }
                    }
                }
                if (bestKeymasterDigest == -1) {
                    return null;
                }
                return KeyProperties.Digest.fromKeymasterToSignatureAlgorithmDigest(bestKeymasterDigest) + "WithRSA";
            case 2:
            default:
                throw new ProviderException("Unsupported algorithm: " + keymasterAlgorithm);
            case 3:
                Set<Integer> availableKeymasterDigests2 = getAvailableKeymasterSignatureDigests(spec.getDigests(), AndroidKeyStoreBCWorkaroundProvider.getSupportedEcdsaSignatureDigests());
                int bestKeymasterDigest2 = -1;
                int bestDigestOutputSizeBits2 = -1;
                Iterator<Integer> it = availableKeymasterDigests2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        int keymasterDigest2 = it.next().intValue();
                        int outputSizeBits2 = KeymasterUtils.getDigestOutputSizeBits(keymasterDigest2);
                        if (outputSizeBits2 == keySizeBits) {
                            bestKeymasterDigest2 = keymasterDigest2;
                        } else if (bestKeymasterDigest2 == -1) {
                            bestKeymasterDigest2 = keymasterDigest2;
                            bestDigestOutputSizeBits2 = outputSizeBits2;
                        } else if (bestDigestOutputSizeBits2 < keySizeBits) {
                            if (outputSizeBits2 > bestDigestOutputSizeBits2) {
                                bestKeymasterDigest2 = keymasterDigest2;
                                bestDigestOutputSizeBits2 = outputSizeBits2;
                            }
                        } else if (outputSizeBits2 < bestDigestOutputSizeBits2 && outputSizeBits2 >= keySizeBits) {
                            bestKeymasterDigest2 = keymasterDigest2;
                            bestDigestOutputSizeBits2 = outputSizeBits2;
                        }
                    }
                }
                if (bestKeymasterDigest2 == -1) {
                    return null;
                }
                return KeyProperties.Digest.fromKeymasterToSignatureAlgorithmDigest(bestKeymasterDigest2) + "WithECDSA";
        }
    }

    private static Set<Integer> getAvailableKeymasterSignatureDigests(String[] authorizedKeyDigests, String[] supportedSignatureDigests) {
        int[] allToKeymaster;
        int[] allToKeymaster2;
        Set<Integer> authorizedKeymasterKeyDigests = new HashSet<>();
        for (int keymasterDigest : KeyProperties.Digest.allToKeymaster(authorizedKeyDigests)) {
            authorizedKeymasterKeyDigests.add(Integer.valueOf(keymasterDigest));
        }
        Set<Integer> supportedKeymasterSignatureDigests = new HashSet<>();
        for (int keymasterDigest2 : KeyProperties.Digest.allToKeymaster(supportedSignatureDigests)) {
            supportedKeymasterSignatureDigests.add(Integer.valueOf(keymasterDigest2));
        }
        Set<Integer> result = new HashSet<>(supportedKeymasterSignatureDigests);
        result.retainAll(authorizedKeymasterKeyDigests);
        return result;
    }
}
