package android.hardware.fingerprint;

import android.content.Context;
/* loaded from: classes.dex */
public interface IFingerprintManagerExt {
    default String getAcquiredString(int acquireInfo, int vendorCode) {
        return "";
    }

    default boolean isKeyguard(Context context, String clientPackage) {
        return false;
    }
}
