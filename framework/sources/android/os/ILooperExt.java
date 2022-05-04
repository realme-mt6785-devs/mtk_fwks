package android.os;
/* loaded from: classes2.dex */
public interface ILooperExt {
    default void initLoop(String threadName) {
    }

    default void startLooperMessageMonitor(Message msg, int pid, boolean threadExist) {
    }

    default void stopLooperMessageMonitor(Message msg, int pid, boolean threadExist) {
    }
}
