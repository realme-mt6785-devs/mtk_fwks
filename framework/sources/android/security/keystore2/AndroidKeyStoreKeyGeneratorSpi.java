package android.security.keystore2;

import android.hardware.security.keymint.KeyParameter;
import android.security.KeyStore2;
import android.security.KeyStoreException;
import android.security.KeyStoreSecurityLevel;
import android.security.keystore.ArrayUtils;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.security.keystore.StrongBoxUnavailableException;
import android.system.keystore2.KeyDescriptor;
import android.system.keystore2.KeyMetadata;
import android.util.Log;
import java.security.InvalidAlgorithmParameterException;
import java.security.ProviderException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import libcore.util.EmptyArray;
/* loaded from: classes2.dex */
public abstract class AndroidKeyStoreKeyGeneratorSpi extends KeyGeneratorSpi {
    private static final String TAG = "AndroidKeyStoreKeyGeneratorSpi";
    private final int mDefaultKeySizeBits;
    protected int mKeySizeBits;
    private final KeyStore2 mKeyStore;
    private final int mKeymasterAlgorithm;
    private int[] mKeymasterBlockModes;
    private final int mKeymasterDigest;
    private int[] mKeymasterDigests;
    private int[] mKeymasterPaddings;
    private int[] mKeymasterPurposes;
    private SecureRandom mRng;
    private KeyGenParameterSpec mSpec;

    /* loaded from: classes2.dex */
    public static class AES extends AndroidKeyStoreKeyGeneratorSpi {
        public AES() {
            super(32, 128);
        }

        @Override // android.security.keystore2.AndroidKeyStoreKeyGeneratorSpi, javax.crypto.KeyGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec params, SecureRandom random) throws InvalidAlgorithmParameterException {
            AndroidKeyStoreKeyGeneratorSpi.super.engineInit(params, random);
            if (this.mKeySizeBits != 128 && this.mKeySizeBits != 192 && this.mKeySizeBits != 256) {
                throw new InvalidAlgorithmParameterException("Unsupported key size: " + this.mKeySizeBits + ". Supported: 128, 192, 256.");
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DESede extends AndroidKeyStoreKeyGeneratorSpi {
        public DESede() {
            super(33, 168);
        }
    }

    /* loaded from: classes2.dex */
    protected static abstract class HmacBase extends AndroidKeyStoreKeyGeneratorSpi {
        protected HmacBase(int keymasterDigest) {
            super(128, keymasterDigest, KeymasterUtils.getDigestOutputSizeBits(keymasterDigest));
        }
    }

    /* loaded from: classes2.dex */
    public static class HmacSHA1 extends HmacBase {
        public HmacSHA1() {
            super(2);
        }
    }

    /* loaded from: classes2.dex */
    public static class HmacSHA224 extends HmacBase {
        public HmacSHA224() {
            super(3);
        }
    }

    /* loaded from: classes2.dex */
    public static class HmacSHA256 extends HmacBase {
        public HmacSHA256() {
            super(4);
        }
    }

    /* loaded from: classes2.dex */
    public static class HmacSHA384 extends HmacBase {
        public HmacSHA384() {
            super(5);
        }
    }

    /* loaded from: classes2.dex */
    public static class HmacSHA512 extends HmacBase {
        public HmacSHA512() {
            super(6);
        }
    }

    protected AndroidKeyStoreKeyGeneratorSpi(int keymasterAlgorithm, int defaultKeySizeBits) {
        this(keymasterAlgorithm, -1, defaultKeySizeBits);
    }

    protected AndroidKeyStoreKeyGeneratorSpi(int keymasterAlgorithm, int keymasterDigest, int defaultKeySizeBits) {
        this.mKeyStore = KeyStore2.getInstance();
        this.mKeymasterAlgorithm = keymasterAlgorithm;
        this.mKeymasterDigest = keymasterDigest;
        this.mDefaultKeySizeBits = defaultKeySizeBits;
        if (defaultKeySizeBits <= 0) {
            throw new IllegalArgumentException("Default key size must be positive");
        } else if (keymasterAlgorithm == 128 && keymasterDigest == -1) {
            throw new IllegalArgumentException("Digest algorithm must be specified for HMAC key");
        }
    }

    @Override // javax.crypto.KeyGeneratorSpi
    protected void engineInit(SecureRandom random) {
        throw new UnsupportedOperationException("Cannot initialize without a " + KeyGenParameterSpec.class.getName() + " parameter");
    }

    @Override // javax.crypto.KeyGeneratorSpi
    protected void engineInit(int keySize, SecureRandom random) {
        throw new UnsupportedOperationException("Cannot initialize without a " + KeyGenParameterSpec.class.getName() + " parameter");
    }

    @Override // javax.crypto.KeyGeneratorSpi
    protected void engineInit(AlgorithmParameterSpec params, SecureRandom random) throws InvalidAlgorithmParameterException {
        int[] iArr;
        resetAll();
        boolean success = false;
        if (params != null) {
            try {
                if (params instanceof KeyGenParameterSpec) {
                    KeyGenParameterSpec spec = (KeyGenParameterSpec) params;
                    if (spec.getKeystoreAlias() != null) {
                        this.mRng = random;
                        this.mSpec = spec;
                        int keySize = spec.getKeySize() != -1 ? spec.getKeySize() : this.mDefaultKeySizeBits;
                        this.mKeySizeBits = keySize;
                        if (keySize <= 0) {
                            throw new InvalidAlgorithmParameterException("Key size must be positive: " + this.mKeySizeBits);
                        } else if (keySize % 8 == 0) {
                            try {
                                this.mKeymasterPurposes = KeyProperties.Purpose.allToKeymaster(spec.getPurposes());
                                this.mKeymasterPaddings = KeyProperties.EncryptionPadding.allToKeymaster(spec.getEncryptionPaddings());
                                if (spec.getSignaturePaddings().length <= 0) {
                                    this.mKeymasterBlockModes = KeyProperties.BlockMode.allToKeymaster(spec.getBlockModes());
                                    if ((spec.getPurposes() & 1) != 0 && spec.isRandomizedEncryptionRequired()) {
                                        for (int keymasterBlockMode : this.mKeymasterBlockModes) {
                                            if (!KeymasterUtils.isKeymasterBlockModeIndCpaCompatibleWithSymmetricCrypto(keymasterBlockMode)) {
                                                throw new InvalidAlgorithmParameterException("Randomized encryption (IND-CPA) required but may be violated by block mode: " + KeyProperties.BlockMode.fromKeymaster(keymasterBlockMode) + ". See " + KeyGenParameterSpec.class.getName() + " documentation.");
                                            }
                                        }
                                    }
                                    int i = this.mKeymasterAlgorithm;
                                    if (i == 33 && this.mKeySizeBits != 168) {
                                        throw new InvalidAlgorithmParameterException("3DES key size must be 168 bits.");
                                    }
                                    if (i == 128) {
                                        int i2 = this.mKeySizeBits;
                                        if (i2 < 64 || i2 > 512) {
                                            throw new InvalidAlgorithmParameterException("HMAC key sizes must be within 64-512 bits, inclusive.");
                                        }
                                        this.mKeymasterDigests = new int[]{this.mKeymasterDigest};
                                        if (spec.isDigestsSpecified()) {
                                            int[] keymasterDigestsFromSpec = KeyProperties.Digest.allToKeymaster(spec.getDigests());
                                            if (!(keymasterDigestsFromSpec.length == 1 && keymasterDigestsFromSpec[0] == this.mKeymasterDigest)) {
                                                throw new InvalidAlgorithmParameterException("Unsupported digests specification: " + Arrays.asList(spec.getDigests()) + ". Only " + KeyProperties.Digest.fromKeymaster(this.mKeymasterDigest) + " supported for this HMAC key algorithm");
                                            }
                                        }
                                    } else if (spec.isDigestsSpecified()) {
                                        this.mKeymasterDigests = KeyProperties.Digest.allToKeymaster(spec.getDigests());
                                    } else {
                                        this.mKeymasterDigests = EmptyArray.INT;
                                    }
                                    KeyStore2ParameterUtils.addUserAuthArgs(new ArrayList(), spec);
                                    success = true;
                                    if (success) {
                                        return;
                                    }
                                    return;
                                }
                                throw new InvalidAlgorithmParameterException("Signature paddings not supported for symmetric key algorithms");
                            } catch (IllegalArgumentException | IllegalStateException e) {
                                throw new InvalidAlgorithmParameterException(e);
                            }
                        } else {
                            throw new InvalidAlgorithmParameterException("Key size must be a multiple of 8: " + this.mKeySizeBits);
                        }
                    } else {
                        throw new InvalidAlgorithmParameterException("KeyStore entry alias not provided");
                    }
                }
            } finally {
                if (!success) {
                    resetAll();
                }
            }
        }
        throw new InvalidAlgorithmParameterException("Cannot initialize without a " + KeyGenParameterSpec.class.getName() + " parameter");
    }

    private void resetAll() {
        this.mSpec = null;
        this.mRng = null;
        this.mKeySizeBits = -1;
        this.mKeymasterPurposes = null;
        this.mKeymasterPaddings = null;
        this.mKeymasterBlockModes = null;
    }

    @Override // javax.crypto.KeyGeneratorSpi
    protected SecretKey engineGenerateKey() {
        int securityLevel;
        int flags;
        KeyStoreException e;
        KeyStoreSecurityLevel iSecurityLevel;
        KeyGenParameterSpec spec = this.mSpec;
        if (spec != null) {
            final List<KeyParameter> params = new ArrayList<>();
            params.add(KeyStore2ParameterUtils.makeInt(805306371, this.mKeySizeBits));
            params.add(KeyStore2ParameterUtils.makeEnum(268435458, this.mKeymasterAlgorithm));
            ArrayUtils.forEach(this.mKeymasterPurposes, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyGeneratorSpi$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    params.add(KeyStore2ParameterUtils.makeEnum(536870913, ((Integer) obj).intValue()));
                }
            });
            ArrayUtils.forEach(this.mKeymasterBlockModes, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyGeneratorSpi$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AndroidKeyStoreKeyGeneratorSpi.this.lambda$engineGenerateKey$1$AndroidKeyStoreKeyGeneratorSpi(params, (Integer) obj);
                }
            });
            ArrayUtils.forEach(this.mKeymasterPaddings, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyGeneratorSpi$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    params.add(KeyStore2ParameterUtils.makeEnum(536870918, ((Integer) obj).intValue()));
                }
            });
            ArrayUtils.forEach(this.mKeymasterDigests, new Consumer() { // from class: android.security.keystore2.AndroidKeyStoreKeyGeneratorSpi$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    params.add(KeyStore2ParameterUtils.makeEnum(536870917, ((Integer) obj).intValue()));
                }
            });
            int i = 0;
            if (this.mKeymasterAlgorithm == 128) {
                int[] iArr = this.mKeymasterDigests;
                if (iArr.length != 0) {
                    int digestOutputSizeBits = KeymasterUtils.getDigestOutputSizeBits(iArr[0]);
                    if (digestOutputSizeBits != -1) {
                        params.add(KeyStore2ParameterUtils.makeInt(805306376, digestOutputSizeBits));
                    } else {
                        throw new ProviderException("HMAC key authorized for unsupported digest: " + KeyProperties.Digest.fromKeymaster(this.mKeymasterDigests[0]));
                    }
                }
            }
            KeyStore2ParameterUtils.addUserAuthArgs(params, spec);
            if (spec.getKeyValidityStart() != null) {
                params.add(KeyStore2ParameterUtils.makeDate(1610613136, spec.getKeyValidityStart()));
            }
            if (spec.getKeyValidityForOriginationEnd() != null) {
                params.add(KeyStore2ParameterUtils.makeDate(1610613137, spec.getKeyValidityForOriginationEnd()));
            }
            if (spec.getKeyValidityForConsumptionEnd() != null) {
                params.add(KeyStore2ParameterUtils.makeDate(1610613138, spec.getKeyValidityForConsumptionEnd()));
            }
            if ((spec.getPurposes() & 1) != 0 && !spec.isRandomizedEncryptionRequired()) {
                params.add(KeyStore2ParameterUtils.makeBool(1879048199));
            }
            if (spec.getMaxUsageCount() != -1) {
                params.add(KeyStore2ParameterUtils.makeInt(805306773, spec.getMaxUsageCount()));
            }
            byte[] additionalEntropy = KeyStoreCryptoOperationUtils.getRandomBytesToMixIntoKeystoreRng(this.mRng, (this.mKeySizeBits + 7) / 8);
            if (spec.isStrongBoxBacked()) {
                securityLevel = 2;
            } else {
                securityLevel = 1;
            }
            if (spec.isCriticalToDeviceEncryption()) {
                int flags2 = 0 | 1;
                flags = flags2;
            } else {
                flags = 0;
            }
            KeyDescriptor descriptor = new KeyDescriptor();
            descriptor.alias = spec.getKeystoreAlias();
            descriptor.nspace = spec.getNamespace();
            if (descriptor.nspace != -1) {
                i = 2;
            }
            descriptor.domain = i;
            descriptor.blob = null;
            try {
                iSecurityLevel = this.mKeyStore.getSecurityLevel(securityLevel);
            } catch (KeyStoreException e2) {
                e = e2;
            }
            try {
                KeyMetadata metadata = iSecurityLevel.generateKey(descriptor, null, params, flags, additionalEntropy);
                try {
                    String keyAlgorithmJCA = KeyProperties.KeyAlgorithm.fromKeymasterSecretKeyAlgorithm(this.mKeymasterAlgorithm, this.mKeymasterDigest);
                    SecretKey result = new AndroidKeyStoreSecretKey(descriptor, metadata, keyAlgorithmJCA, iSecurityLevel);
                    return result;
                } catch (IllegalArgumentException e3) {
                    try {
                        this.mKeyStore.deleteKey(descriptor);
                    } catch (KeyStoreException kse) {
                        Log.e(TAG, "Failed to delete key after generating successfully but failed to get the algorithm string.", kse);
                    }
                    throw new ProviderException("Failed to obtain JCA secret key algorithm name", e3);
                }
            } catch (KeyStoreException e4) {
                e = e4;
                switch (e.getErrorCode()) {
                    case -68:
                        throw new StrongBoxUnavailableException("Failed to generate key");
                    default:
                        throw new ProviderException("Keystore key generation failed", e);
                }
            }
        } else {
            throw new IllegalStateException("Not initialized");
        }
    }

    public /* synthetic */ void lambda$engineGenerateKey$1$AndroidKeyStoreKeyGeneratorSpi(List params, Integer blockMode) {
        if (blockMode.intValue() == 32 && this.mKeymasterAlgorithm == 32) {
            params.add(KeyStore2ParameterUtils.makeInt(805306376, 96));
        }
        params.add(KeyStore2ParameterUtils.makeEnum(536870916, blockMode.intValue()));
    }
}
