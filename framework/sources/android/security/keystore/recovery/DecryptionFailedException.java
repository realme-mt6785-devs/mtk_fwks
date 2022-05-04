package android.security.keystore.recovery;

import android.annotation.SystemApi;
import java.security.GeneralSecurityException;
@SystemApi
/* loaded from: classes2.dex */
public class DecryptionFailedException extends GeneralSecurityException {
    public DecryptionFailedException(String msg) {
        super(msg);
    }
}
