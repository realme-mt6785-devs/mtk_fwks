package com.mediatek.server.am;

import android.app.ActivityManager;
import android.app.ActivityManagerInternal;
import android.app.AppGlobals;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManagerInternal;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Slog;
import com.android.server.am.ActivityManagerDebugConfig;
import com.android.server.am.ProcessRecord;
import com.android.server.wm.ActivityRecord;
import com.android.server.wm.ActivityTaskManagerDebugConfig;
import com.mediatek.amsAal.AalUtils;
import com.mediatek.boostfwk.BoostFwkFactory;
import com.mediatek.boostfwk.scenario.launch.LaunchScenario;
import com.mediatek.cta.CtaManager;
import com.mediatek.cta.CtaManagerFactory;
import com.mediatek.duraspeed.manager.IDuraSpeedNative;
import com.mediatek.duraspeed.suppress.ISuppressAction;
import com.mediatek.dx.DexOptExt;
import com.mediatek.dx.DexOptExtFactory;
import com.mediatek.server.powerhal.PowerHalManagerImpl;
import dalvik.system.PathClassLoader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class AmsExtImpl extends AmsExt {
    private static final int MTKPOWER_STATE_PAUSED = 0;
    private static final int MTKPOWER_STATE_RESUMED = 1;
    private static final String TAG = "AmsExtImpl";
    public static PathClassLoader sClassLoader;
    private AalUtils mAalUtils;
    private ActivityManagerInternal mActivityManagerInternal;
    private Context mContext;
    private IDuraSpeedNative mDuraSpeedService;
    private PackageManagerInternal mPackageManagerInternal;
    public PowerHalManagerImpl mPowerHalManagerImpl;
    private Field mProcessNamesField;
    private Method mStartProcessMethod;
    private ISuppressAction mSuppressAction;
    private boolean isDebug = false;
    private boolean isDuraSpeedSupport = "1".equals(SystemProperties.get("persist.vendor.duraspeed.support"));
    private final String amsLogProp = "persist.vendor.sys.activitylog";
    private CtaManager mCtaManager = null;
    private ActivityManager mAm = null;
    private DexOptExt mDexOptExt = DexOptExtFactory.getInstance().makeDexOpExt();

    public AmsExtImpl() {
        this.mPowerHalManagerImpl = null;
        this.mAalUtils = null;
        this.mPowerHalManagerImpl = new PowerHalManagerImpl();
        if (this.isDuraSpeedSupport) {
            try {
                PathClassLoader pathClassLoader = new PathClassLoader("/system/framework/duraspeed.jar", AmsExtImpl.class.getClassLoader());
                sClassLoader = pathClassLoader;
                Class<?> clazz = Class.forName("com.mediatek.duraspeed.manager.DuraSpeedService", false, pathClassLoader);
                this.mDuraSpeedService = (IDuraSpeedNative) clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
                Class<?> clazz2 = Class.forName("com.mediatek.duraspeed.suppress.SuppressAction", false, sClassLoader);
                this.mSuppressAction = (ISuppressAction) clazz2.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                Slog.e(TAG, e.toString());
            }
        }
        if (this.mAalUtils == null && AalUtils.isSupported()) {
            this.mAalUtils = AalUtils.getInstance();
        }
    }

    public void onAddErrorToDropBox(String dropboxTag, String info, int pid) {
        if (this.isDebug) {
            Slog.d(TAG, "onAddErrorToDropBox, dropboxTag=" + dropboxTag + ", info=" + info + ", pid=" + pid);
        }
    }

    public void onSystemReady(Context context) {
        IDuraSpeedNative iDuraSpeedNative;
        Slog.d(TAG, "onSystemReady");
        if (this.isDuraSpeedSupport && (iDuraSpeedNative = this.mDuraSpeedService) != null) {
            iDuraSpeedNative.onSystemReady();
        }
        this.mContext = context;
    }

    public void onBeforeActivitySwitch(ActivityRecord lastResumedActivity, ActivityRecord nextResumedActivity, boolean pausing, int nextResumedActivityType, boolean isKeyguardShowing) {
        IDuraSpeedNative iDuraSpeedNative;
        if (nextResumedActivity != null && nextResumedActivity.info != null) {
            if (lastResumedActivity == null && !isKeyguardShowing) {
                return;
            }
            if (lastResumedActivity == null || nextResumedActivity.packageName != lastResumedActivity.packageName || nextResumedActivity.info.name != lastResumedActivity.info.name) {
                String lastResumedPackageName = null;
                if (lastResumedActivity != null) {
                    lastResumedPackageName = lastResumedActivity.packageName;
                }
                String nextResumedPackageName = nextResumedActivity.packageName;
                if (this.isDebug) {
                    Slog.d(TAG, "onBeforeActivitySwitch, lastResumedPackageName=" + lastResumedPackageName + ", nextResumedPackageName=" + nextResumedPackageName);
                }
                PowerHalManagerImpl powerHalManagerImpl = this.mPowerHalManagerImpl;
                if (powerHalManagerImpl != null) {
                    powerHalManagerImpl.amsBoostResume(lastResumedPackageName, nextResumedPackageName);
                }
                if (this.isDuraSpeedSupport && (iDuraSpeedNative = this.mDuraSpeedService) != null && lastResumedActivity != null) {
                    iDuraSpeedNative.onBeforeActivitySwitch(lastResumedActivity, nextResumedActivity, pausing, nextResumedActivityType);
                }
            }
        }
    }

    public void onAfterActivityResumed(ActivityRecord resumedActivity) {
        if (resumedActivity.app != null) {
            int pid = resumedActivity.app.mPid;
            int i = resumedActivity.app.mUid;
            String activityName = resumedActivity.info.name;
            String packageName = resumedActivity.info.packageName;
            if (this.isDebug) {
                Slog.d(TAG, "onAfterActivityResumed, pid=" + pid + ", activityName=" + activityName + ", packageName=" + packageName);
            }
            AalUtils aalUtils = this.mAalUtils;
            if (aalUtils != null) {
                aalUtils.onAfterActivityResumed(packageName, activityName);
            }
            BoostFwkFactory.getInstance().makeBoostFwkManager().perfHint(new LaunchScenario(3, 1, 1, packageName, activityName, false));
        }
    }

    public void onActivityStateChanged(ActivityRecord activity, boolean onTop) {
        if (activity.app != null) {
            int pid = activity.app.mPid;
            int uid = activity.app.mUid;
            String activityName = activity.info.name;
            String packageName = activity.info.packageName;
            if (this.isDebug) {
                Slog.d(TAG, "onActivityStateChanged(onTop=" + onTop + "), pid=" + pid + ", activityName=" + activityName + ", packageName=" + packageName);
            }
            if (onTop) {
                amsBoostNotify(pid, activityName, packageName, uid, 1);
            } else {
                amsBoostNotify(pid, activityName, packageName, uid, 0);
            }
        }
    }

    private void amsBoostNotify(int pid, String activityName, String packageName, int uid, int state) {
        PowerHalManagerImpl powerHalManagerImpl = this.mPowerHalManagerImpl;
        if (powerHalManagerImpl != null) {
            powerHalManagerImpl.amsBoostNotify(pid, activityName, packageName, uid, state);
        } else {
            Slog.w(TAG, "amsBoostNotify mPowerHalManagerImpl is null.");
        }
    }

    public void onUpdateSleep(boolean wasSleeping, boolean isSleepingAfterUpdate) {
        if (this.isDebug) {
            Slog.d(TAG, "onUpdateSleep, wasSleeping=" + wasSleeping + ", isSleepingAfterUpdate=" + isSleepingAfterUpdate);
        }
        AalUtils aalUtils = this.mAalUtils;
        if (aalUtils != null) {
            aalUtils.onUpdateSleep(wasSleeping, isSleepingAfterUpdate);
        }
    }

    public void setAalMode(int mode) {
        AalUtils aalUtils = this.mAalUtils;
        if (aalUtils != null) {
            aalUtils.setAalMode(mode);
        }
    }

    public void setAalEnabled(boolean enabled) {
        AalUtils aalUtils = this.mAalUtils;
        if (aalUtils != null) {
            aalUtils.setEnabled(enabled);
        }
    }

    public int amsAalDump(PrintWriter pw, String[] args, int opti) {
        AalUtils aalUtils = this.mAalUtils;
        if (aalUtils != null) {
            return aalUtils.dump(pw, args, opti);
        }
        return opti;
    }

    public void onStartProcess(String hostingType, String packageName) {
        if (this.isDebug) {
            Slog.d(TAG, "onStartProcess, hostingType=" + hostingType + ", packageName=" + packageName);
        }
        PowerHalManagerImpl powerHalManagerImpl = this.mPowerHalManagerImpl;
        if (powerHalManagerImpl != null) {
            powerHalManagerImpl.amsBoostProcessCreate(hostingType, packageName);
        }
        DexOptExt dexOptExt = this.mDexOptExt;
        if (dexOptExt != null) {
            dexOptExt.onStartProcess(hostingType, packageName);
        }
        BoostFwkFactory.getInstance().makeBoostFwkManager().perfHint(new LaunchScenario(3, 1, hostingType, 0, packageName));
    }

    public void onNotifyAppCrash(int pid, int uid, String packageName) {
        if (this.isDebug) {
            Slog.d(TAG, "onNotifyAppCrash, packageName=" + packageName + ", pid=" + pid);
        }
        PowerHalManagerImpl powerHalManagerImpl = this.mPowerHalManagerImpl;
        if (powerHalManagerImpl != null) {
            powerHalManagerImpl.NotifyAppCrash(pid, uid, packageName);
        }
    }

    public void onEndOfActivityIdle(Context context, ActivityRecord activityRecord) {
        IDuraSpeedNative iDuraSpeedNative;
        if (this.isDebug) {
            Slog.d(TAG, "onEndOfActivityIdle, activityRecord=" + activityRecord);
        }
        PowerHalManagerImpl powerHalManagerImpl = this.mPowerHalManagerImpl;
        if (powerHalManagerImpl != null) {
            powerHalManagerImpl.amsBoostStop();
        }
        if (this.isDuraSpeedSupport && (iDuraSpeedNative = this.mDuraSpeedService) != null) {
            iDuraSpeedNative.onActivityIdle(context, activityRecord.intent);
        }
        BoostFwkFactory.getInstance().makeBoostFwkManager().perfHint(new LaunchScenario(3, 1, 1, activityRecord.packageName, activityRecord.findMainWindow(false).getAttrs(), true));
    }

    public void enableAmsLog(ArrayList<ProcessRecord> lruProcesses) {
        String activitylog = SystemProperties.get("persist.vendor.sys.activitylog", (String) null);
        if (activitylog != null && !activitylog.equals("")) {
            if (activitylog.indexOf(" ") == -1 || activitylog.indexOf(" ") + 1 > activitylog.length()) {
                SystemProperties.set("persist.vendor.sys.activitylog", "");
                return;
            }
            String[] args = {activitylog.substring(0, activitylog.indexOf(" ")), activitylog.substring(activitylog.indexOf(" ") + 1, activitylog.length())};
            enableAmsLog(null, args, 0, lruProcesses);
        }
    }

    public void enableAmsLog(PrintWriter pw, String[] args, int opti, ArrayList<ProcessRecord> lruProcesses) {
        int indexLast = opti + 1;
        if (indexLast >= args.length) {
            if (pw != null) {
                pw.println("  Invalid argument!");
            }
            SystemProperties.set("persist.vendor.sys.activitylog", "");
            return;
        }
        String option = args[opti];
        boolean isEnable = "on".equals(args[indexLast]);
        SystemProperties.set("persist.vendor.sys.activitylog", args[opti] + " " + args[indexLast]);
        if (option.equals("x")) {
            enableAmsLog(isEnable, lruProcesses);
            return;
        }
        if (pw != null) {
            pw.println("  Invalid argument!");
        }
        SystemProperties.set("persist.vendor.sys.activitylog", "");
    }

    private void enableAmsLog(boolean isEnable, ArrayList<ProcessRecord> lruProcesses) {
        this.isDebug = isEnable;
        ActivityManagerDebugConfig.APPEND_CATEGORY_NAME = isEnable;
        ActivityManagerDebugConfig.DEBUG_ALL = isEnable;
        ActivityManagerDebugConfig.DEBUG_ANR = isEnable;
        ActivityManagerDebugConfig.DEBUG_BACKGROUND_CHECK = isEnable;
        ActivityManagerDebugConfig.DEBUG_BACKUP = isEnable;
        ActivityManagerDebugConfig.DEBUG_BROADCAST = isEnable;
        ActivityManagerDebugConfig.DEBUG_BROADCAST_BACKGROUND = isEnable;
        ActivityManagerDebugConfig.DEBUG_BROADCAST_LIGHT = isEnable;
        ActivityManagerDebugConfig.DEBUG_BROADCAST_DEFERRAL = isEnable;
        ActivityManagerDebugConfig.DEBUG_LRU = isEnable;
        ActivityManagerDebugConfig.DEBUG_MU = isEnable;
        ActivityManagerDebugConfig.DEBUG_NETWORK = isEnable;
        ActivityManagerDebugConfig.DEBUG_POWER = isEnable;
        ActivityManagerDebugConfig.DEBUG_POWER_QUICK = isEnable;
        ActivityManagerDebugConfig.DEBUG_PROCESS_OBSERVERS = isEnable;
        ActivityManagerDebugConfig.DEBUG_PROCESSES = isEnable;
        ActivityManagerDebugConfig.DEBUG_PROVIDER = isEnable;
        ActivityManagerDebugConfig.DEBUG_PSS = isEnable;
        ActivityManagerDebugConfig.DEBUG_SERVICE = isEnable;
        ActivityManagerDebugConfig.DEBUG_FOREGROUND_SERVICE = isEnable;
        ActivityManagerDebugConfig.DEBUG_SERVICE_EXECUTING = isEnable;
        ActivityManagerDebugConfig.DEBUG_UID_OBSERVERS = isEnable;
        ActivityManagerDebugConfig.DEBUG_USAGE_STATS = isEnable;
        ActivityManagerDebugConfig.DEBUG_PERMISSIONS_REVIEW = isEnable;
        ActivityManagerDebugConfig.DEBUG_ALLOWLISTS = isEnable;
        ActivityTaskManagerDebugConfig.APPEND_CATEGORY_NAME = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_ALL = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_RECENTS = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_RECENTS_TRIM_TASKS = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_ROOT_TASK = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_SWITCH = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_TRANSITION = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_VISIBILITY = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_APP = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_IDLE = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_RELEASE = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_USER_LEAVING = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_PERMISSIONS_REVIEW = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_RESULTS = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_ACTIVITY_STARTS = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_CLEANUP = isEnable;
        ActivityTaskManagerDebugConfig.DEBUG_METRICS = isEnable;
        for (int i = 0; i < lruProcesses.size(); i++) {
            ProcessRecord app = lruProcesses.get(i);
            if (!(app == null || app.mThread == null)) {
                try {
                    app.mThread.enableActivityThreadLog(isEnable);
                } catch (Exception e) {
                    Slog.e(TAG, "Error happens when enableActivityThreadLog", e);
                }
            }
        }
    }

    public void enableProcessMainThreadLooperLog(PrintWriter pw, String[] args, int opti, ArrayList<ProcessRecord> lruProcesses) {
        String processName = null;
        boolean isSucess = false;
        if (args.length >= 1) {
            processName = args[opti];
            for (int i = 0; i < lruProcesses.size(); i++) {
                ProcessRecord app = lruProcesses.get(i);
                if (!(app == null || app.mThread == null || app.processName == null || !app.processName.equals(processName))) {
                    try {
                        app.mThread.enableProcessMainThreadLooperLog();
                        isSucess = true;
                        pw.println("Sucess enalbe " + processName + " main thread looper log.");
                    } catch (Exception e) {
                        Slog.e(TAG, "Error happens when enableProcessMainThreadLooperLog", e);
                    }
                }
            }
        } else if (pw != null) {
            pw.println("Invalid argument!");
        }
        if (!isSucess) {
            pw.println("Canntot find prcess: " + processName);
        }
    }

    public void onWakefulnessChanged(int wakefulness) {
        IDuraSpeedNative iDuraSpeedNative;
        if (this.isDuraSpeedSupport && (iDuraSpeedNative = this.mDuraSpeedService) != null) {
            iDuraSpeedNative.onWakefulnessChanged(wakefulness);
        }
    }

    public void addDuraSpeedService() {
        IDuraSpeedNative iDuraSpeedNative;
        if (this.isDuraSpeedSupport && (iDuraSpeedNative = this.mDuraSpeedService) != null) {
            ServiceManager.addService("duraspeed", (IBinder) iDuraSpeedNative, true);
        }
    }

    public void startDuraSpeedService(Context context) {
        IDuraSpeedNative iDuraSpeedNative;
        if (this.isDuraSpeedSupport && (iDuraSpeedNative = this.mDuraSpeedService) != null) {
            iDuraSpeedNative.startDuraSpeedService(context);
        }
    }

    public String onReadyToStartComponent(String packageName, int uid, String suppressReason, String className) {
        IDuraSpeedNative iDuraSpeedNative = this.mDuraSpeedService;
        if (iDuraSpeedNative == null || !iDuraSpeedNative.isDuraSpeedEnabled()) {
            return null;
        }
        return this.mSuppressAction.onReadyToStartComponent(this.mContext, packageName, uid, suppressReason, className);
    }

    public boolean onBeforeStartProcessForStaticReceiver(String packageName) {
        IDuraSpeedNative iDuraSpeedNative = this.mDuraSpeedService;
        if (iDuraSpeedNative == null || !iDuraSpeedNative.isDuraSpeedEnabled()) {
            return false;
        }
        return this.mSuppressAction.onBeforeStartProcessForStaticReceiver(packageName);
    }

    public void addToSuppressRestartList(String packageName) {
        Context context;
        IDuraSpeedNative iDuraSpeedNative = this.mDuraSpeedService;
        if (iDuraSpeedNative != null && iDuraSpeedNative.isDuraSpeedEnabled() && (context = this.mContext) != null) {
            this.mSuppressAction.addToSuppressRestartList(context, packageName);
        }
    }

    public boolean notRemoveAlarm(String packageName) {
        IDuraSpeedNative iDuraSpeedNative = this.mDuraSpeedService;
        if (iDuraSpeedNative == null || !iDuraSpeedNative.isDuraSpeedEnabled()) {
            return false;
        }
        return this.mSuppressAction.notRemoveAlarm(packageName);
    }

    public boolean IsBuildInApp() {
        IPackageManager pm = AppGlobals.getPackageManager();
        try {
            String pkgName = pm.getNameForUid(Binder.getCallingUid());
            ApplicationInfo appInfo = pm.getApplicationInfo(pkgName, 0, UserHandle.getCallingUserId());
            if (appInfo != null) {
                if ((appInfo.flags & 1) == 0) {
                    if ((appInfo.flags & 128) != 0) {
                    }
                }
                return true;
            }
        } catch (RemoteException e) {
            Slog.e(TAG, "getCallerProcessName exception :" + e);
        }
        return false;
    }

    public boolean checkAutoBootPermission(Context context, String packageName, int userId, ArrayList<ProcessRecord> runningProcess, int callingPid) {
        if (this.mCtaManager == null) {
            this.mCtaManager = CtaManagerFactory.getInstance().makeCtaManager();
        }
        if (!this.mCtaManager.isCtaSupported()) {
            return true;
        }
        for (int i = runningProcess.size() - 1; i >= 0; i--) {
            ProcessRecord processRecord = runningProcess.get(i);
            if (processRecord.mPid == callingPid && processRecord.mState.mCurAdj > 200) {
                boolean result = this.mCtaManager.checkAutoBootPermission(context, packageName, userId);
                Slog.e(TAG, "check result:" + result);
                if (!result) {
                    Slog.e(TAG, "can't start procss because auto boot permission. calling package:" + processRecord.processName + " start process:" + packageName);
                    if (this.mAm == null) {
                        this.mAm = (ActivityManager) context.getSystemService("activity");
                    }
                    this.mAm.forceStopPackageAsUser(packageName, userId);
                    return false;
                }
            }
        }
        return true;
    }

    public void onAppProcessDied(Context context, ProcessRecord app, ApplicationInfo appInfo, int userId, ArrayList<ProcessRecord> lruProcesses, String reason) {
        IDuraSpeedNative iDuraSpeedNative;
        if (this.isDuraSpeedSupport && (iDuraSpeedNative = this.mDuraSpeedService) != null) {
            iDuraSpeedNative.onAppProcessDied(app, appInfo.packageName, reason);
        }
    }
}
