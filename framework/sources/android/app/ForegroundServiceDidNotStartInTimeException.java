package android.app;
/* loaded from: classes.dex */
public class ForegroundServiceDidNotStartInTimeException extends RemoteServiceException {
    public static final int TYPE_ID = 1;

    public ForegroundServiceDidNotStartInTimeException(String msg) {
        super(msg);
    }
}
