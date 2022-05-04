package android.security;
/* loaded from: classes2.dex */
public class KeyStoreException extends Exception {
    private final int mErrorCode;

    public KeyStoreException(int errorCode, String message) {
        super(message);
        this.mErrorCode = errorCode;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }
}
