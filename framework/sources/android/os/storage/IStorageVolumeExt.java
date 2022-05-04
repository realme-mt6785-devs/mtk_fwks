package android.os.storage;

import android.os.Parcel;
/* loaded from: classes2.dex */
public interface IStorageVolumeExt {
    default void init(int readonlyType) {
    }

    default void initFromParcel(Parcel in) {
    }

    default void dump(Object pw) {
    }

    default void writeToParcel(Parcel parcel, int flags) {
    }

    default int getReadOnlyType() {
        return 0;
    }

    default void setReadOnlyType(int readonlyType) {
    }
}
