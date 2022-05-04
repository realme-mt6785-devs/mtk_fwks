package android.security.keystore;

import java.security.ProviderException;
/* loaded from: classes2.dex */
public class BackendBusyException extends ProviderException {
    private final long mBackOffHintMillis;

    public BackendBusyException(long backOffHintMillis) {
        super("The keystore backend has no operation slots available. Retry later.");
        if (backOffHintMillis >= 0) {
            this.mBackOffHintMillis = backOffHintMillis;
            return;
        }
        throw new IllegalArgumentException("Back-off hint cannot be negative.");
    }

    public BackendBusyException(long backOffHintMillis, String message) {
        super(message);
        if (backOffHintMillis >= 0) {
            this.mBackOffHintMillis = backOffHintMillis;
            return;
        }
        throw new IllegalArgumentException("Back-off hint cannot be negative.");
    }

    public BackendBusyException(long backOffHintMillis, String message, Throwable cause) {
        super(message, cause);
        if (backOffHintMillis >= 0) {
            this.mBackOffHintMillis = backOffHintMillis;
            return;
        }
        throw new IllegalArgumentException("Back-off hint cannot be negative.");
    }

    public long getBackOffHintMillis() {
        return this.mBackOffHintMillis;
    }
}
