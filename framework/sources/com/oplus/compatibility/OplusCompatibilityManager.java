package com.oplus.compatibility;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
/* loaded from: classes4.dex */
public class OplusCompatibilityManager {
    private static final String DESCRIPTOR = "com.oplus.compatibility.IOplusCompatibilityService";
    private static final int HANDLE_COMPATIBILITY = 1;
    public static final int OPLUS_ALARM_PERMISSION = 256;
    public static final int OPLUS_BLUETOOTH_PERMISSION = 16;
    public static final int OPLUS_GPS_STATUS = 32;
    public static final int OPLUS_INSTALLER_PACKAGE_NAME = 4;
    public static final int OPLUS_LOCATION_API = 64;
    public static final int OPLUS_LOCK_DOWN_CLOSE_SYSTEM_DIALOG = 1;
    public static final int OPLUS_NOTIFICATION_TRAMPOLINES = 128;
    public static final int OPLUS_PENDINGINTENT_MUTABILITY = 8;
    public static final int OPLUS_PERMISSION_PACKAGE_VISIBILITY = 2;
    private static final String SERVICE_NAME = "app_compatibility";
    private static final String TAG = "OplusCompatibilityManager";
    private IBinder mRemote = ServiceManager.getService(SERVICE_NAME);

    public void handleCompatibilityException(int changeId, String packageName) {
        if (isInChangeIdList(changeId) && packageName != null) {
            if (!connectBinder()) {
                Log.e(TAG, "mRemote is null");
                return;
            }
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();
            try {
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(changeId);
                    _data.writeString(packageName);
                    this.mRemote.transact(1, _data, _reply, 1);
                    _reply.readException();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

    private boolean isInChangeIdList(int id) {
        switch (id) {
            case 1:
            case 2:
            case 4:
            case 8:
            case 16:
            case 32:
            case 64:
            case 128:
            case 256:
                return true;
            default:
                return false;
        }
    }

    private boolean connectBinder() {
        if (this.mRemote != null) {
            return true;
        }
        IBinder service = ServiceManager.getService(SERVICE_NAME);
        this.mRemote = service;
        if (service == null) {
            return false;
        }
        return true;
    }
}
