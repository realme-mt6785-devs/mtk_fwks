package android.hardware.biometrics;

import android.security.identity.IdentityCredential;
import android.security.keystore2.AndroidKeyStoreProvider;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;
/* loaded from: classes.dex */
public class CryptoObject {
    private final Object mCrypto;

    public CryptoObject(Signature signature) {
        this.mCrypto = signature;
    }

    public CryptoObject(Cipher cipher) {
        this.mCrypto = cipher;
    }

    public CryptoObject(Mac mac) {
        this.mCrypto = mac;
    }

    public CryptoObject(IdentityCredential credential) {
        this.mCrypto = credential;
    }

    public Signature getSignature() {
        Object obj = this.mCrypto;
        if (obj instanceof Signature) {
            return (Signature) obj;
        }
        return null;
    }

    public Cipher getCipher() {
        Object obj = this.mCrypto;
        if (obj instanceof Cipher) {
            return (Cipher) obj;
        }
        return null;
    }

    public Mac getMac() {
        Object obj = this.mCrypto;
        if (obj instanceof Mac) {
            return (Mac) obj;
        }
        return null;
    }

    public IdentityCredential getIdentityCredential() {
        Object obj = this.mCrypto;
        if (obj instanceof IdentityCredential) {
            return (IdentityCredential) obj;
        }
        return null;
    }

    public final long getOpId() {
        Object obj = this.mCrypto;
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof IdentityCredential) {
            return ((IdentityCredential) obj).getCredstoreOperationHandle();
        }
        return AndroidKeyStoreProvider.getKeyStoreOperationHandle(obj);
    }
}
