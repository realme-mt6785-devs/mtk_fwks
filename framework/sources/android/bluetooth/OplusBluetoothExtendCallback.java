package android.bluetooth;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public abstract class OplusBluetoothExtendCallback extends Binder {
    private static final int OPLUS_CALL_TRANSACTION_INDEX = 20000;
    public static final int TRANSACTION_ADAPTER_SERVICE_ERROR_REPORT_CB = 200002;
    public static final int TRANSACTION_BLUETOOTH_ABNORMAL_DETECT_APK_CB = 200005;
    public static final int TRANSACTION_BLUETOOTH_ABNORMAL_DETECT_FRAME_CB = 200004;
    public static final int TRANSACTION_BLUETOOTH_DIAGNOSE_TOOL_REPORT_CB = 200003;
    public static final int TRANSACTION_RSSI_DETECT_CB = 200001;

    public abstract void oplusBluetoothExtendCb(int i, Parcel parcel);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.Binder
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        if (super.onTransact(code, data, reply, flags)) {
            return true;
        }
        switch (code) {
            case TRANSACTION_RSSI_DETECT_CB /* 200001 */:
            case TRANSACTION_ADAPTER_SERVICE_ERROR_REPORT_CB /* 200002 */:
            case TRANSACTION_BLUETOOTH_DIAGNOSE_TOOL_REPORT_CB /* 200003 */:
            case TRANSACTION_BLUETOOTH_ABNORMAL_DETECT_APK_CB /* 200005 */:
                data.enforceInterface("android.bluetooth.IBluetooth");
                oplusBluetoothExtendCb(code, data);
                reply.writeNoException();
                return true;
            case TRANSACTION_BLUETOOTH_ABNORMAL_DETECT_FRAME_CB /* 200004 */:
                data.enforceInterface("android.bluetooth.IBluetoothManager");
                oplusBluetoothExtendCb(code, data);
                reply.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
