package android.bluetooth;

import android.app.ActivityThread;
import android.bluetooth.IBluetoothManager;
import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class OplusBluetoothMonitor {
    public static final String AVRCPCONTROL_MONIT_EVENT = "avrcp_control";
    public static final String BLUETOOTH_MANAGER_SERVICE = "bluetooth_manager";
    public static final String CODECSWITCH_MONIT_EVENT = "codec_switch";
    private static final boolean DBG;
    public static final String DESCRIPTOR_SYSTEM_SERVER = "android.bluetooth.IBluetoothManager";
    public static final String MEDIAAUDIO_MONIT_EVENT = "media_audio";
    public static final int OPLUS_CALL_TRANSACTION_INDEX = 30000;
    public static final int OPLUS_FIRST_CALL_TRANSACTION = 30001;
    public static final String OPPTRANS_MONIT_EVENT = "opp_trans";
    public static final String PAIRCONN_MONIT_EVENT = "pair_conn";
    public static final String PHONEAUDIO_MONIT_EVENT = "phone_audio";
    public static final String SAUUPDATE_MONIT_EVENT = "sau_update";
    public static final String SCAN_MONIT_EVENT = "scan";
    public static final String SWITCH_MONIT_EVENT = "swicth";
    private static final String TAG = "OplusBluetoothMonitor";
    public static final int TRANSACTION_REGISTER_BTMONITSTATE_CALLBACK = 30002;
    public static final int TRANSACTION_UNREGISTER_BTMONITSTATE_CALLBACK = 30003;
    public static final String UNKNOWN_MONIT_EVENT = "unknown_event";
    private static OplusBluetoothMonitor sBluetoothMonitor;
    private final IBluetoothManager mBluetoothManager;
    private final Map<OplusBluetoothMonitorCallback, OplusBtMonitorCallbackWrapper> mBluetoothMonitorClients;
    private Context mContext;

    static {
        boolean z = false;
        if (!SystemProperties.getBoolean("ro.build.release_type", false) || SystemProperties.getBoolean("persist.sys.assert.panic", false)) {
            z = true;
        }
        DBG = z;
        sBluetoothMonitor = null;
    }

    public static synchronized OplusBluetoothMonitor getDefaultBluetoothMonitor(Context context) {
        OplusBluetoothMonitor oplusBluetoothMonitor;
        synchronized (OplusBluetoothMonitor.class) {
            if (DBG) {
                Log.d(TAG, "enter getDefaultBluetoothMonitor()");
            }
            if (sBluetoothMonitor == null) {
                IBinder b = ServiceManager.getService("bluetooth_manager");
                if (b != null) {
                    IBluetoothManager bluetoothManager = IBluetoothManager.Stub.asInterface(b);
                    sBluetoothMonitor = new OplusBluetoothMonitor(context, bluetoothManager);
                } else {
                    Log.e(TAG, "Bluetooth binder is null");
                }
            }
            oplusBluetoothMonitor = sBluetoothMonitor;
        }
        return oplusBluetoothMonitor;
    }

    OplusBluetoothMonitor(Context context, IBluetoothManager bluetoothManager) {
        this.mContext = context;
        if (bluetoothManager != null) {
            this.mBluetoothManager = bluetoothManager;
            this.mBluetoothMonitorClients = new HashMap();
            return;
        }
        throw new IllegalArgumentException("bluetooth manager service is null");
    }

    public boolean openBtAbnomalMonitor(List<String> monitorEvents, OplusBluetoothMonitorCallback callback, int... params) {
        if (DBG) {
            Log.d(TAG, "enter openBtAbnomalMonitor");
        }
        String packageName = ActivityThread.currentPackageName();
        if (callback != null) {
            return setOplusBluetoothMonitorCallback(packageName, monitorEvents, callback, true);
        }
        Log.w(TAG, "callback is null!");
        return false;
    }

    public boolean closeBtAbnomalMonitor(List<String> monitorEvents, OplusBluetoothMonitorCallback callback, int... params) {
        if (DBG) {
            Log.d(TAG, "enter closeSwitchMonitor");
        }
        String packageName = ActivityThread.currentPackageName();
        if (callback != null) {
            return setOplusBluetoothMonitorCallback(packageName, monitorEvents, callback, false);
        }
        Log.w(TAG, "callback is null!");
        return false;
    }

    public boolean setOplusBluetoothMonitorCallback(String callAppName, List<String> monitorEvents, OplusBluetoothMonitorCallback callback, boolean reg) {
        OplusBtMonitorCallbackWrapper mCallback;
        boolean z = DBG;
        if (z) {
            Log.d(TAG, "setOplusBluetoothMonitorCallback: reg = " + reg);
        }
        boolean result = false;
        if (this.mBluetoothManager == null) {
            Log.w(TAG, "mBluetoothManager null!");
            return false;
        } else if (monitorEvents.size() == 0) {
            Log.w(TAG, "params flags is null");
            return false;
        } else {
            synchronized (this.mBluetoothMonitorClients) {
                if (!reg) {
                    OplusBtMonitorCallbackWrapper mCallback2 = this.mBluetoothMonitorClients.remove(callback);
                    if (mCallback2 == null) {
                        if (z) {
                            Log.d(TAG, "could not find callback wrapper");
                        }
                        return false;
                    }
                    mCallback = mCallback2;
                } else if (this.mBluetoothMonitorClients.containsKey(callback)) {
                    Log.w(TAG, "callback has been registered");
                    return false;
                } else {
                    mCallback = new OplusBtMonitorCallbackWrapper(callback);
                    mCallback.startRegistration();
                }
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                result = false;
                try {
                    data.writeInterfaceToken("android.bluetooth.IBluetoothManager");
                    data.writeString(callAppName);
                    data.writeStringList(monitorEvents);
                    data.writeStrongBinder(mCallback);
                    this.mBluetoothManager.asBinder().transact(reg ? 30002 : 30003, data, reply, 0);
                    reply.readException();
                    if (reply.readInt() != 0) {
                        result = true;
                    }
                    reply.recycle();
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                    reply.recycle();
                }
                data.recycle();
                return result;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class OplusBtMonitorCallbackWrapper extends OplusBluetoothExtendCallback {
        private OplusBluetoothMonitorCallback mCallback;

        public OplusBtMonitorCallbackWrapper(OplusBluetoothMonitorCallback callback) {
            this.mCallback = callback;
        }

        public void startRegistration() {
            OplusBluetoothMonitor.this.mBluetoothMonitorClients.put(this.mCallback, this);
        }

        @Override // android.bluetooth.OplusBluetoothExtendCallback
        public void oplusBluetoothExtendCb(int code, Parcel data) {
            if (code == 200004) {
                if (OplusBluetoothMonitor.DBG) {
                    Log.d(OplusBluetoothMonitor.TAG, "enter oplusBluetoothExtendCb");
                }
                String mMonitorEvent = data.readString();
                Map mMonitResult = new HashMap();
                data.readMap(mMonitResult, Map.class.getClassLoader());
                this.mCallback.onBluetoothMonitor(mMonitorEvent, mMonitResult);
            }
        }

        public OplusBluetoothMonitorCallback getCallback() {
            return this.mCallback;
        }
    }
}
