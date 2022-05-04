package android.mtp;
/* loaded from: classes2.dex */
public interface IMtpDatabaseExt {
    default void rescanFile(String path, int handle, int format) {
    }

    default boolean getMtpDeviceProperty(int length, int property, char[] outStringValue) {
        return true;
    }

    default void quitSafely() {
    }

    default void releaseScanThread() {
    }
}
