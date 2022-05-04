package android.hardware.fingerprint;

import android.hardware.fingerprint.IFingerprintStateListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public abstract class FingerprintStateListener extends IFingerprintStateListener.Stub {
    public static final int STATE_AUTH_OTHER = 4;
    public static final int STATE_BP_AUTH = 3;
    public static final int STATE_ENROLLING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_KEYGUARD_AUTH = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface State {
    }

    @Override // android.hardware.fingerprint.IFingerprintStateListener
    public abstract void onStateChanged(int i);
}
