package android.bluetooth;
/* loaded from: classes.dex */
public interface IBluetoothAdapterExt {
    default boolean hookEnable(String permission, int pid, int uid) {
        return false;
    }

    default boolean hookDisable(String permission, int pid, int uid) {
        return false;
    }
}
