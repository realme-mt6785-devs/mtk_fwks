package android.security.keystore2;

import android.security.KeyStoreSecurityLevel;
import android.security.keystore.ArrayUtils;
import android.system.keystore2.KeyDescriptor;
import android.system.keystore2.KeyMetadata;
import java.security.PublicKey;
/* loaded from: classes2.dex */
public abstract class AndroidKeyStorePublicKey extends AndroidKeyStoreKey implements PublicKey {
    private final byte[] mCertificate;
    private final byte[] mCertificateChain;
    private final byte[] mEncoded;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract AndroidKeyStorePrivateKey getPrivateKey();

    public AndroidKeyStorePublicKey(KeyDescriptor descriptor, KeyMetadata metadata, byte[] x509EncodedForm, String algorithm, KeyStoreSecurityLevel securityLevel) {
        super(descriptor, metadata.key.nspace, metadata.authorizations, algorithm, securityLevel);
        this.mCertificate = metadata.certificate;
        this.mCertificateChain = metadata.certificateChain;
        this.mEncoded = x509EncodedForm;
    }

    @Override // android.security.keystore2.AndroidKeyStoreKey, java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // android.security.keystore2.AndroidKeyStoreKey, java.security.Key
    public byte[] getEncoded() {
        return ArrayUtils.cloneIfNotEmpty(this.mEncoded);
    }

    @Override // android.security.keystore2.AndroidKeyStoreKey
    public int hashCode() {
        int result = (1 * 31) + super.hashCode();
        int result2 = result * 31;
        byte[] bArr = this.mCertificate;
        int i = 0;
        int result3 = (result2 + (bArr == null ? 0 : bArr.hashCode())) * 31;
        byte[] bArr2 = this.mCertificateChain;
        if (bArr2 != null) {
            i = bArr2.hashCode();
        }
        return result3 + i;
    }

    @Override // android.security.keystore2.AndroidKeyStoreKey
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj) && getClass() == obj.getClass();
    }
}
