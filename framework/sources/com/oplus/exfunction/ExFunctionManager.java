package com.oplus.exfunction;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oplus.exfunction.IOplusExFunction;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes4.dex */
public class ExFunctionManager {
    public static final int REPAIR_MODE_BIND_SERVICE_ERROR = -4;
    public static final int REPAIR_MODE_CREATE_USER_ERROR = -3;
    public static final int REPAIR_MODE_END_ERROR = -6;
    public static final int REPAIR_MODE_END_IOPLUS_ERROR = -8;
    public static final int REPAIR_MODE_FLAG_USERID_OTHER = 3;
    public static final int REPAIR_MODE_FLAG_USERID_OWNER = 1;
    public static final int REPAIR_MODE_FLAG_USERID_REPAIR = 2;
    public static final int REPAIR_MODE_REBOOT_ERROR = -2;
    public static final int REPAIR_MODE_START_ERROR = -5;
    public static final int REPAIR_MODE_START_IOPLUS_ERROR = -7;
    public static final int REPAIR_MODE_SUCCESS = 0;
    public static final int REPAIR_MODE_WRITE_ERROR = -1;
    private static final String TAG = "ExFunctionManager";
    public static final int USER_FLAG_REPAIR_MODE = 134217728;
    public static final int USER_ID_REPAIR_MODE = 888;

    public int startRepairMode(Context context) {
        int result;
        ServiceConnectionWrapper service = bindRepairService(context);
        if (service == null) {
            return -4;
        }
        try {
            result = service.startRepairMode();
        } catch (RemoteException e) {
            Log.e(TAG, "startRepairMode: ", e);
        }
        if (result == -1) {
            return -1;
        }
        if (result == -2) {
            return -2;
        }
        if (result == -3) {
            return -3;
        }
        if (result == -7) {
            return -7;
        }
        context.unbindService(service);
        return -5;
    }

    public int endRepairMode(Context context) {
        ServiceConnectionWrapper service = bindRepairService(context);
        if (service == null) {
            return -4;
        }
        try {
            int result = service.endRepairMode();
            if (result == -1) {
                return -1;
            }
            if (result == -2) {
                return -2;
            }
            if (result == -3) {
                return -3;
            }
            if (result == -8) {
                return -8;
            }
            context.unbindService(service);
            return -6;
        } catch (RemoteException e) {
            Log.e(TAG, "endRepairMode: ", e);
            return -6;
        }
    }

    public boolean isEnableRepairMode() {
        return true;
    }

    public int getRepairModeFlag(Context context) {
        int userId = context.getUserId();
        if (userId == 0) {
            return 1;
        }
        if (userId == 888) {
            return 2;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class ServiceConnectionWrapper implements ServiceConnection {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        IOplusExFunction iOplusExFunction;

        ServiceConnectionWrapper() {
        }

        boolean waitForFinish(long timeoutMillis) {
            try {
                return this.countDownLatch.await(timeoutMillis, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.e(ExFunctionManager.TAG, "waitForFinish: ", e);
                return false;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            this.iOplusExFunction = IOplusExFunction.Stub.asInterface(service);
            this.countDownLatch.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            this.iOplusExFunction = null;
        }

        int startRepairMode() throws RemoteException {
            IOplusExFunction iOplusExFunction = this.iOplusExFunction;
            if (iOplusExFunction != null) {
                return iOplusExFunction.startRepairMode();
            }
            return -7;
        }

        int endRepairMode() throws RemoteException {
            IOplusExFunction iOplusExFunction = this.iOplusExFunction;
            if (iOplusExFunction != null) {
                return iOplusExFunction.endRepairMode();
            }
            return -8;
        }
    }

    private ServiceConnectionWrapper bindRepairService(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.oplus.exsystemservice", "com.oplus.exfunction.ExFunctionService");
        ServiceConnectionWrapper connection = new ServiceConnectionWrapper();
        boolean ret = context.bindService(intent, connection, 1);
        if (ret && connection.waitForFinish(JobInfo.MIN_BACKOFF_MILLIS)) {
            return connection;
        }
        return null;
    }
}
