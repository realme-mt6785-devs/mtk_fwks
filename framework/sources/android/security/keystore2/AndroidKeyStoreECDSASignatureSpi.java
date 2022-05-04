package android.security.keystore2;

import android.hardware.security.keymint.KeyParameter;
import android.security.KeyStoreException;
import android.security.KeyStoreOperation;
import android.security.keystore.KeyProperties;
import android.system.keystore2.Authorization;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.util.List;
import libcore.util.EmptyArray;
/* loaded from: classes2.dex */
abstract class AndroidKeyStoreECDSASignatureSpi extends AndroidKeyStoreSignatureSpiBase {
    private int mGroupSizeBits = -1;
    private final int mKeymasterDigest;

    /* loaded from: classes2.dex */
    public static final class NONE extends AndroidKeyStoreECDSASignatureSpi {
        public NONE() {
            super(0);
        }

        @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
        protected String getAlgorithm() {
            return "NONEwithECDSA";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
        public KeyStoreCryptoOperationStreamer createMainDataStreamer(KeyStoreOperation operation) {
            return new TruncateToFieldSizeMessageStreamer(AndroidKeyStoreECDSASignatureSpi.super.createMainDataStreamer(operation), getGroupSizeBits());
        }

        /* loaded from: classes2.dex */
        private static class TruncateToFieldSizeMessageStreamer implements KeyStoreCryptoOperationStreamer {
            private long mConsumedInputSizeBytes;
            private final KeyStoreCryptoOperationStreamer mDelegate;
            private final int mGroupSizeBits;
            private final ByteArrayOutputStream mInputBuffer;

            private TruncateToFieldSizeMessageStreamer(KeyStoreCryptoOperationStreamer delegate, int groupSizeBits) {
                this.mInputBuffer = new ByteArrayOutputStream();
                this.mDelegate = delegate;
                this.mGroupSizeBits = groupSizeBits;
            }

            @Override // android.security.keystore2.KeyStoreCryptoOperationStreamer
            public byte[] update(byte[] input, int inputOffset, int inputLength) throws KeyStoreException {
                if (inputLength > 0) {
                    this.mInputBuffer.write(input, inputOffset, inputLength);
                    this.mConsumedInputSizeBytes += inputLength;
                }
                return EmptyArray.BYTE;
            }

            @Override // android.security.keystore2.KeyStoreCryptoOperationStreamer
            public byte[] doFinal(byte[] input, int inputOffset, int inputLength, byte[] signature) throws KeyStoreException {
                if (inputLength > 0) {
                    this.mConsumedInputSizeBytes += inputLength;
                    this.mInputBuffer.write(input, inputOffset, inputLength);
                }
                byte[] bufferedInput = this.mInputBuffer.toByteArray();
                this.mInputBuffer.reset();
                return this.mDelegate.doFinal(bufferedInput, 0, Math.min(bufferedInput.length, (this.mGroupSizeBits + 7) / 8), signature);
            }

            @Override // android.security.keystore2.KeyStoreCryptoOperationStreamer
            public long getConsumedInputSizeBytes() {
                return this.mConsumedInputSizeBytes;
            }

            @Override // android.security.keystore2.KeyStoreCryptoOperationStreamer
            public long getProducedOutputSizeBytes() {
                return this.mDelegate.getProducedOutputSizeBytes();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class SHA1 extends AndroidKeyStoreECDSASignatureSpi {
        public SHA1() {
            super(2);
        }

        @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
        protected String getAlgorithm() {
            return "SHA1withECDSA";
        }
    }

    /* loaded from: classes2.dex */
    public static final class SHA224 extends AndroidKeyStoreECDSASignatureSpi {
        public SHA224() {
            super(3);
        }

        @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
        protected String getAlgorithm() {
            return "SHA224withECDSA";
        }
    }

    /* loaded from: classes2.dex */
    public static final class SHA256 extends AndroidKeyStoreECDSASignatureSpi {
        public SHA256() {
            super(4);
        }

        @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
        protected String getAlgorithm() {
            return "SHA256withECDSA";
        }
    }

    /* loaded from: classes2.dex */
    public static final class SHA384 extends AndroidKeyStoreECDSASignatureSpi {
        public SHA384() {
            super(5);
        }

        @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
        protected String getAlgorithm() {
            return "SHA384withECDSA";
        }
    }

    /* loaded from: classes2.dex */
    public static final class SHA512 extends AndroidKeyStoreECDSASignatureSpi {
        public SHA512() {
            super(6);
        }

        @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
        protected String getAlgorithm() {
            return "SHA512withECDSA";
        }
    }

    AndroidKeyStoreECDSASignatureSpi(int keymasterDigest) {
        this.mKeymasterDigest = keymasterDigest;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
    public final void initKey(AndroidKeyStoreKey key) throws InvalidKeyException {
        Authorization[] authorizations;
        if (KeyProperties.KEY_ALGORITHM_EC.equalsIgnoreCase(key.getAlgorithm())) {
            long keySizeBits = -1;
            for (Authorization a : key.getAuthorizations()) {
                if (a.keyParameter.tag == 805306371) {
                    keySizeBits = KeyStore2ParameterUtils.getUnsignedInt(a);
                }
            }
            if (keySizeBits == -1) {
                throw new InvalidKeyException("Size of key not known");
            } else if (keySizeBits <= 2147483647L) {
                this.mGroupSizeBits = (int) keySizeBits;
                super.initKey(key);
            } else {
                throw new InvalidKeyException("Key too large: " + keySizeBits + " bits");
            }
        } else {
            throw new InvalidKeyException("Unsupported key algorithm: " + key.getAlgorithm() + ". Only" + KeyProperties.KEY_ALGORITHM_EC + " supported");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
    public final void resetAll() {
        this.mGroupSizeBits = -1;
        super.resetAll();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
    public final void resetWhilePreservingInitState() {
        super.resetWhilePreservingInitState();
    }

    @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
    protected final void addAlgorithmSpecificParametersToBegin(List<KeyParameter> parameters) {
        parameters.add(KeyStore2ParameterUtils.makeEnum(268435458, 3));
        parameters.add(KeyStore2ParameterUtils.makeEnum(536870917, this.mKeymasterDigest));
    }

    @Override // android.security.keystore2.AndroidKeyStoreSignatureSpiBase
    protected final int getAdditionalEntropyAmountForSign() {
        return (this.mGroupSizeBits + 7) / 8;
    }

    protected final int getGroupSizeBits() {
        int i = this.mGroupSizeBits;
        if (i != -1) {
            return i;
        }
        throw new IllegalStateException("Not initialized");
    }
}
