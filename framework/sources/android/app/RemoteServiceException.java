package android.app;

import android.util.AndroidRuntimeException;
/* loaded from: classes.dex */
public class RemoteServiceException extends AndroidRuntimeException {
    public static final int TYPE_ID = 0;

    public RemoteServiceException(String msg) {
        super(msg);
    }
}
