package android.security.keystore2;

import android.hardware.security.keymint.KeyParameter;
import android.security.KeyStoreException;
import android.security.KeyStoreOperation;
import android.security.keystore.KeyStoreCryptoOperation;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.KeyAgreementSpi;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes2.dex */
public class AndroidKeyStoreKeyAgreementSpi extends KeyAgreementSpi implements KeyStoreCryptoOperation {
    private static final String TAG = "AndroidKeyStoreKeyAgreementSpi";
    private AndroidKeyStorePrivateKey mKey;
    private final int mKeymintAlgorithm;
    private KeyStoreOperation mOperation;
    private long mOperationHandle;
    private PublicKey mOtherPartyKey;

    /* loaded from: classes2.dex */
    public static class ECDH extends AndroidKeyStoreKeyAgreementSpi {
        public ECDH() {
            super(3);
        }
    }

    protected AndroidKeyStoreKeyAgreementSpi(int keymintAlgorithm) {
        resetAll();
        this.mKeymintAlgorithm = keymintAlgorithm;
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, SecureRandom random) throws InvalidKeyException {
        resetAll();
        if (key == null) {
            throw new InvalidKeyException("key == null");
        } else if (key instanceof AndroidKeyStorePrivateKey) {
            this.mKey = (AndroidKeyStorePrivateKey) key;
            boolean success = false;
            try {
                ensureKeystoreOperationInitialized();
                success = true;
            } finally {
                if (!success) {
                    resetAll();
                }
            }
        } else {
            throw new InvalidKeyException("Only Android KeyStore private keys supported. Key: " + key);
        }
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, AlgorithmParameterSpec params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (params == null) {
            engineInit(key, random);
            return;
        }
        throw new InvalidAlgorithmParameterException("Unsupported algorithm parameters: " + params);
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected Key engineDoPhase(Key key, boolean lastPhase) throws InvalidKeyException, IllegalStateException {
        ensureKeystoreOperationInitialized();
        if (key == null) {
            throw new InvalidKeyException("key == null");
        } else if (!(key instanceof PublicKey)) {
            throw new InvalidKeyException("Only public keys supported. Key: " + key);
        } else if (!lastPhase) {
            throw new IllegalStateException("Only one other party supported. lastPhase must be set to true.");
        } else if (this.mOtherPartyKey == null) {
            this.mOtherPartyKey = (PublicKey) key;
            return null;
        } else {
            throw new IllegalStateException("Only one other party supported. doPhase() must only be called exactly once.");
        }
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected byte[] engineGenerateSecret() throws IllegalStateException {
        try {
            ensureKeystoreOperationInitialized();
            PublicKey publicKey = this.mOtherPartyKey;
            if (publicKey != null) {
                byte[] otherPartyKeyEncoded = publicKey.getEncoded();
                try {
                    try {
                        return this.mOperation.finish(otherPartyKeyEncoded, null);
                    } catch (KeyStoreException e) {
                        throw new ProviderException("Keystore operation failed", e);
                    }
                } finally {
                    resetWhilePreservingInitState();
                }
            } else {
                throw new IllegalStateException("Other party key not provided. Call doPhase() first.");
            }
        } catch (InvalidKeyException e2) {
            throw new IllegalStateException("Not initialized", e2);
        }
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected SecretKey engineGenerateSecret(String algorithm) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] generatedSecret = engineGenerateSecret();
        return new SecretKeySpec(generatedSecret, algorithm);
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected int engineGenerateSecret(byte[] sharedSecret, int offset) throws IllegalStateException, ShortBufferException {
        byte[] generatedSecret = engineGenerateSecret();
        if (generatedSecret.length <= sharedSecret.length - offset) {
            System.arraycopy(generatedSecret, 0, sharedSecret, offset, generatedSecret.length);
            return generatedSecret.length;
        }
        throw new ShortBufferException("Needed: " + generatedSecret.length);
    }

    @Override // android.security.keystore.KeyStoreCryptoOperation
    public long getOperationHandle() {
        return this.mOperationHandle;
    }

    protected void finalize() throws Throwable {
        try {
            resetAll();
        } finally {
            super.finalize();
        }
    }

    private void resetWhilePreservingInitState() {
        KeyStoreCryptoOperationUtils.abortOperation(this.mOperation);
        this.mOperationHandle = 0L;
        this.mOperation = null;
        this.mOtherPartyKey = null;
    }

    private void resetAll() {
        resetWhilePreservingInitState();
        this.mKey = null;
    }

    private void ensureKeystoreOperationInitialized() throws InvalidKeyException, IllegalStateException {
        if (this.mKey == null) {
            throw new IllegalStateException("Not initialized");
        } else if (this.mOperation == null) {
            List<KeyParameter> parameters = new ArrayList<>();
            parameters.add(KeyStore2ParameterUtils.makeEnum(536870913, 6));
            try {
                this.mOperation = this.mKey.getSecurityLevel().createOperation(this.mKey.getKeyIdDescriptor(), parameters);
            } catch (KeyStoreException keyStoreException) {
                InvalidKeyException e = KeyStoreCryptoOperationUtils.getInvalidKeyException(this.mKey, keyStoreException);
                if (e != null) {
                    throw e;
                }
            }
            this.mOperationHandle = KeyStoreCryptoOperationUtils.getOrMakeOperationChallenge(this.mOperation, this.mKey);
        }
    }
}
