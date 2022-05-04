package android.security.keystore2;

import android.security.KeyStoreSecurityLevel;
import android.system.keystore2.Authorization;
import android.system.keystore2.KeyDescriptor;
import java.security.Key;
/* loaded from: classes2.dex */
public class AndroidKeyStoreKey implements Key {
    private final String mAlgorithm;
    private final Authorization[] mAuthorizations;
    private final KeyDescriptor mDescriptor;
    private final long mKeyId;
    private final KeyStoreSecurityLevel mSecurityLevel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndroidKeyStoreKey(KeyDescriptor descriptor, long keyId, Authorization[] authorizations, String algorithm, KeyStoreSecurityLevel securityLevel) {
        this.mDescriptor = descriptor;
        this.mKeyId = keyId;
        this.mAuthorizations = authorizations;
        this.mAlgorithm = algorithm;
        this.mSecurityLevel = securityLevel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyDescriptor getUserKeyDescriptor() {
        return this.mDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyDescriptor getKeyIdDescriptor() {
        KeyDescriptor descriptor = new KeyDescriptor();
        descriptor.nspace = this.mKeyId;
        descriptor.domain = 4;
        descriptor.alias = null;
        descriptor.blob = null;
        return descriptor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Authorization[] getAuthorizations() {
        return this.mAuthorizations;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyStoreSecurityLevel getSecurityLevel() {
        return this.mSecurityLevel;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.mAlgorithm;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    public int hashCode() {
        int i = 1 * 31;
        KeyDescriptor keyDescriptor = this.mDescriptor;
        int i2 = 0;
        int result = i + (keyDescriptor == null ? 0 : keyDescriptor.hashCode());
        long j = this.mKeyId;
        int result2 = ((((result * 31) + ((int) (j >>> 32))) * 31) + ((int) (j & (-1)))) * 31;
        Authorization[] authorizationArr = this.mAuthorizations;
        int result3 = (result2 + (authorizationArr == null ? 0 : authorizationArr.hashCode())) * 31;
        String str = this.mAlgorithm;
        if (str != null) {
            i2 = str.hashCode();
        }
        return result3 + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AndroidKeyStoreKey other = (AndroidKeyStoreKey) obj;
        if (this.mKeyId != other.mKeyId) {
            return false;
        }
        return true;
    }
}
