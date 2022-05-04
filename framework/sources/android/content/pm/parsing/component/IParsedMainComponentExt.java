package android.content.pm.parsing.component;

import android.os.Parcel;
/* loaded from: classes.dex */
public interface IParsedMainComponentExt {
    default void init(IParsedMainComponentExt parsedMainComponentExt) {
    }

    default void init(Parcel in) {
    }

    default void writeToParcel(Parcel dest, int flags) {
    }

    default void setFlags(int flags) {
    }

    default int getFlags() {
        return 0;
    }
}
