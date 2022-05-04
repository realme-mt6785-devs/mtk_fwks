package com.oplus.os;

import android.content.Context;
import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;
import com.oplus.app.OplusAlarmInfo;
import com.oplus.app.OplusWakeLockInfo;
import com.oplus.os.IOplusPowerMonitor;
import java.lang.reflect.Method;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusPowerMonitor {
    public static final String POWER_MONITOR_SERVICE_NAME = "power_monitor";
    private static final String TAG = "OplusPowerMonitor";
    private static OplusPowerMonitor mInstance = null;
    private Context mContext;
    private IOplusPowerMonitor mOplusPowerMonitorService;

    private OplusPowerMonitor(Context context) {
        this.mOplusPowerMonitorService = null;
        this.mContext = null;
        IBinder serviceBinder = ServiceManager.getService(POWER_MONITOR_SERVICE_NAME);
        this.mOplusPowerMonitorService = IOplusPowerMonitor.Stub.asInterface(serviceBinder);
        this.mContext = context;
        StringBuilder sb = new StringBuilder();
        sb.append("mOplusPowerMonitorService = ");
        String str = this.mOplusPowerMonitorService;
        sb.append(str == null ? "null" : str);
        Log.d(TAG, sb.toString());
    }

    public static synchronized OplusPowerMonitor getInstance(Context context) {
        OplusPowerMonitor oplusPowerMonitor;
        synchronized (OplusPowerMonitor.class) {
            OplusPowerMonitor oplusPowerMonitor2 = mInstance;
            if (oplusPowerMonitor2 == null || !oplusPowerMonitor2.isOplusPowerMonitorServiceInited()) {
                mInstance = new OplusPowerMonitor(context);
            }
            oplusPowerMonitor = mInstance;
        }
        return oplusPowerMonitor;
    }

    public boolean isOplusPowerMonitorServiceInited() {
        return this.mOplusPowerMonitorService != null;
    }

    public void recordAlarmWakeupEvent() {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.recordAlarmWakeupEvent();
            } catch (Exception e) {
                Log.e(TAG, "recordAlarmWakeupEvent failed.", e);
            }
        } else {
            Log.e(TAG, "recordAlarmWakeupEvent failed: service unavailable");
        }
    }

    public void recordAppWakeupEvent(int alarmType, String alarmPackageName) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.recordAppWakeupEvent(alarmType, alarmPackageName);
            } catch (Exception e) {
                Log.e(TAG, "recordAppWakeupEvent failed.", e);
            }
        } else {
            Log.e(TAG, "recordAppWakeupEvent failed: service unavailable");
        }
    }

    public void recordAppWakeupInfoEvent(OplusAlarmInfo oplusAlarmInfo) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.recordAppWakeupInfoEvent(oplusAlarmInfo);
            } catch (Exception e) {
                Log.e(TAG, "recordAppWakeupInfoEvent failed.", e);
            }
        } else {
            Log.e(TAG, "recordAppWakeupInfoEvent failed: service unavailable");
        }
    }

    public void resetWakeupEventRecords() {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.resetWakeupEventRecords();
            } catch (Exception e) {
                Log.e(TAG, "resetWakeupEventRecords failed.", e);
            }
        } else {
            Log.e(TAG, "resetWakeupEventRecords failed: service unavailable");
        }
    }

    public List<OplusAlarmInfo> getAlarmWakeUpInfo(long starttime, long endtime) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                return iOplusPowerMonitor.getAlarmWakeUpInfo(starttime, endtime);
            } catch (Exception e) {
                Log.e(TAG, "getAlarmWakeUpInfo failed.", e);
                return null;
            }
        } else {
            Log.e(TAG, "getAlarmWakeUpInfo failed: service unavailable");
            return null;
        }
    }

    public void recordWakeLockAcquireEvent(OplusWakeLockInfo wakeLockInfo) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.recordWakeLockAcquireEvent(wakeLockInfo);
            } catch (Exception e) {
                Log.e(TAG, "recordWakeLockAcquireEvent failed.", e);
            }
        } else {
            Log.e(TAG, "recordWakeLockAcquireEvent failed: service unavailable");
        }
    }

    public void recordWakeLockReleaseEvent(OplusWakeLockInfo wakeLockInfo) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.recordWakeLockReleaseEvent(wakeLockInfo);
            } catch (Exception e) {
                Log.e(TAG, "recordWakeLockEvent failed.", e);
            }
        } else {
            Log.e(TAG, "recordWakeLockEvent failed: service unavailable");
        }
    }

    public List<OplusWakeLockInfo> getWakeLockInfo(long starttime, long endtime) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                return iOplusPowerMonitor.getWakeLockInfo(starttime, endtime);
            } catch (Exception e) {
                Log.e(TAG, "getWakeLockInfo failed.", e);
                return null;
            }
        } else {
            Log.e(TAG, "getWakeLockInfo failed: service unavailable");
            return null;
        }
    }

    public String getRpmStatsFilePath() {
        String path = null;
        if (this.mOplusPowerMonitorService != null) {
            try {
                Class<?> powerMonitorClass = Class.forName(IOplusPowerMonitor.DESCRIPTOR);
                Method getRpmStatsFilePathMethod = powerMonitorClass.getMethod("getRpmStatsFilePath", new Class[0]);
                if (getRpmStatsFilePathMethod != null) {
                    getRpmStatsFilePathMethod.setAccessible(true);
                    Object result = getRpmStatsFilePathMethod.invoke(this.mOplusPowerMonitorService, new Object[0]);
                    path = (String) result;
                }
            } catch (Exception e) {
                Log.e(TAG, "getRpmStatsFilePath failed.", e);
            }
        } else {
            Log.e(TAG, "getRpmStatsFilePath failed: service unavailable");
        }
        Log.d(TAG, "getRpmStatsFilePath:" + path);
        return path;
    }

    public String getRpmMasterStatsFilePath() {
        String path = null;
        if (this.mOplusPowerMonitorService != null) {
            try {
                Class<?> powerMonitorClass = Class.forName(IOplusPowerMonitor.DESCRIPTOR);
                Method getRpmMasterStatsFilePathMethod = powerMonitorClass.getMethod("getRpmMasterStatsFilePath", new Class[0]);
                if (getRpmMasterStatsFilePathMethod != null) {
                    getRpmMasterStatsFilePathMethod.setAccessible(true);
                    Object result = getRpmMasterStatsFilePathMethod.invoke(this.mOplusPowerMonitorService, new Object[0]);
                    path = (String) result;
                }
            } catch (Exception e) {
                Log.e(TAG, "getRpmMasterStatsFilePath failed.", e);
            }
        } else {
            Log.e(TAG, "getRpmMasterStatsFilePath failed: service unavailable");
        }
        Log.d(TAG, "getRpmMasterStatsFilePath:" + path);
        return path;
    }

    public void acquireSuspendBlocker(String name) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.acquireSuspendBlocker(name);
            } catch (Exception e) {
                Log.e(TAG, "acquireSuspendBlocker failed.", e);
            }
        } else {
            Log.e(TAG, "acquireSuspendBlocker failed: service unavailable");
        }
    }

    public void releaseSuspendBlocker(String name) {
        IOplusPowerMonitor iOplusPowerMonitor = this.mOplusPowerMonitorService;
        if (iOplusPowerMonitor != null) {
            try {
                iOplusPowerMonitor.releaseSuspendBlocker(name);
            } catch (Exception e) {
                Log.e(TAG, "releaseSuspendBlocker failed.", e);
            }
        } else {
            Log.e(TAG, "releaseSuspendBlocker failed: service unavailable");
        }
    }
}
