package android.content.pm;

import android.os.Parcel;
import android.util.Printer;
/* loaded from: classes.dex */
public interface IActivityInfoExt {
    default void setOplusFlags(int flags) {
    }

    default int getOplusFlags() {
        return 0;
    }

    default Object getActivityInfo() {
        return null;
    }

    default void copy(IActivityInfoExt other) {
    }

    default void readFromParcel(Parcel in) {
    }

    default void writeToParcel(Parcel dest) {
    }

    default void dumpFront(Printer pw, String prefix) {
    }

    default int getRealConfigChanged(String name, int changes) {
        return changes;
    }
}
