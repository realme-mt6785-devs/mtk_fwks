package android.security.identity;
/* loaded from: classes2.dex */
public class NoAuthenticationKeyAvailableException extends IdentityCredentialException {
    public NoAuthenticationKeyAvailableException(String message) {
        super(message);
    }

    public NoAuthenticationKeyAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
