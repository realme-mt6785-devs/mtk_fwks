package android.content.pm;

import android.os.Parcel;
/* loaded from: classes.dex */
public interface ISessionParamsExt {
    default void setExtraSessionInfo(String extraSessionInfo) {
    }

    default String getExtraSessionInfo() {
        return null;
    }

    default void setExtraInstallFlags(int installFlags) {
    }

    default int getExtraInstallFlags() {
        return 0;
    }

    default void initFromParcel(Parcel source) {
    }

    default void baseWriteToParcel(Parcel dest) {
    }

    default void copyFrom(ISessionParamsExt sessionParamsExt) {
    }

    default void setDexoptFlag(int flag) {
    }

    default int getDexoptFlag() {
        return 0;
    }
}
