package android.hardware.fingerprint;

import android.os.Parcel;
/* loaded from: classes.dex */
public interface IFingerprintExt {
    default void init(int groupId) {
    }

    default void readFromParcel(Parcel in) {
    }

    default void writeToParcel(Parcel out, int flags) {
    }

    default void setFingerFlags(int fingerFlags) {
    }

    default int getFingerFlags() {
        return 0;
    }
}
