package android.security.identity;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class IdentityCredential {
    public abstract KeyPair createEphemeralKeyPair();

    public abstract byte[] decryptMessageFromReader(byte[] bArr) throws MessageDecryptionException;

    public abstract byte[] encryptMessageToReader(byte[] bArr);

    public abstract Collection<X509Certificate> getAuthKeysNeedingCertification();

    public abstract int[] getAuthenticationDataUsageCount();

    public abstract Collection<X509Certificate> getCredentialKeyCertificateChain();

    public abstract long getCredstoreOperationHandle();

    public abstract ResultData getEntries(byte[] bArr, Map<String, Collection<String>> map, byte[] bArr2, byte[] bArr3) throws SessionTranscriptMismatchException, NoAuthenticationKeyAvailableException, InvalidReaderSignatureException, EphemeralPublicKeyNotFoundException, InvalidRequestMessageException;

    public abstract void setAllowUsingExhaustedKeys(boolean z);

    public abstract void setAvailableAuthenticationKeys(int i, int i2);

    public abstract void setReaderEphemeralPublicKey(PublicKey publicKey) throws InvalidKeyException;

    @Deprecated
    public abstract void storeStaticAuthenticationData(X509Certificate x509Certificate, byte[] bArr) throws UnknownAuthenticationKeyException;

    public void setAllowUsingExpiredKeys(boolean allowUsingExpiredKeys) {
        throw new UnsupportedOperationException();
    }

    public void storeStaticAuthenticationData(X509Certificate authenticationKey, Instant expirationDate, byte[] staticAuthData) throws UnknownAuthenticationKeyException {
        throw new UnsupportedOperationException();
    }

    public byte[] proveOwnership(byte[] challenge) {
        throw new UnsupportedOperationException();
    }

    public byte[] delete(byte[] challenge) {
        throw new UnsupportedOperationException();
    }

    public byte[] update(PersonalizationData personalizationData) {
        throw new UnsupportedOperationException();
    }
}
