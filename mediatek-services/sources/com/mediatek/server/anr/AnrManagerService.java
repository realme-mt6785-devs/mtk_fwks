package com.mediatek.server.anr;

import android.content.pm.ApplicationInfo;
import android.hidl.base.V1_0.DebugInfo;
import android.os.Build;
import android.os.FileUtils;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SELinux;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.incremental.IncrementalMetrics;
import android.util.Slog;
import android.util.SparseArray;
import com.android.internal.os.ProcessCpuTracker;
import com.android.server.am.ActivityManagerService;
import com.android.server.am.ProcessRecord;
import com.mediatek.aee.ExceptionLog;
import com.mediatek.anr.AnrManagerNative;
import com.mediatek.net.connectivity.MtkPacketMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public final class AnrManagerService extends AnrManagerNative {
    private static final String ACTIVE_SERVICES = "com.android.server.am.ActiveServices";
    private static final String ACTIVITY_MANAGER = "com.android.server.am.ActivityManagerService";
    private static final String ACTIVITY_RECORD = "com.android.server.wm.ActivityRecord";
    private static final long ANR_BOOT_DEFER_TIME = 30000;
    private static final long ANR_CPU_DEFER_TIME = 8000;
    private static final float ANR_CPU_THRESHOLD = 90.0f;
    private static final String APP_ERRORS = "com.android.server.am.AppErrors";
    private static final String APP_PROFILER = "com.android.server.am.AppProfiler";
    private static final String ATM_SERVICE = "com.android.server.wm.ActivityTaskManagerService";
    private static final String BATTERY_STATS = "com.android.server.am.BatteryStatsService";
    private static final String BINDERINFO_PATH = "/dev/binderfs/binder_logs";
    private static final int DISABLE_ALL_ANR_MECHANISM = 0;
    private static final int DISABLE_ANR_DUMP_FOR_3RD_APP = 0;
    private static final int DISABLE_PARTIAL_ANR_MECHANISM = 1;
    private static final int ENABLE_ALL_ANR_MECHANISM = 2;
    private static final int ENABLE_ANR_DUMP_FOR_3RD_APP = 1;
    private static final int EVENT_BOOT_COMPLETED = 9001;
    private static final int INVALID_ANR_FLOW = -1;
    private static final int INVALID_ANR_OPTION = -1;
    private static final boolean IS_USER_BUILD;
    private static final boolean IS_USER_LOAD;
    private static final int MAX_MTK_TRACE_COUNT = 10;
    private static final int MESSAGE_MAP_BUFFER_COUNT_MAX = 5;
    private static final int MESSAGE_MAP_BUFFER_SIZE_MAX = 50000;
    private static final long MONITOR_CPU_MIN_TIME = 2500;
    private static String[] NATIVE_STACKS_OF_INTEREST = null;
    private static final int NORMAL_ANR_FLOW = 0;
    private static final String PROCESS_ERROR_STATE_RECORD = "com.android.server.am.ProcessErrorStateRecord";
    private static final String PROCESS_LIST = "com.android.server.am.ProcessList";
    private static final String PROCESS_RECORD = "com.android.server.am.ProcessRecord";
    private static final int REMOVE_KEYDISPATCHING_TIMEOUT_MSG = 1005;
    private static final int SERVICE_TIMEOUT = 20000;
    private static final int SKIP_ANR_FLOW = 1;
    private static final int SKIP_ANR_FLOW_AND_KILL = 2;
    private static final int START_ANR_DUMP_MSG = 1003;
    private static final int START_MONITOR_BROADCAST_TIMEOUT_MSG = 1001;
    private static final int START_MONITOR_KEYDISPATCHING_TIMEOUT_MSG = 1004;
    private static final int START_MONITOR_SERVICE_TIMEOUT_MSG = 1002;
    private static final String TAG = "AnrManager";
    private static Object lock;
    private static final ProcessCpuTracker mAnrProcessStats;
    private static final Object mDumpStackTraces;
    private static ConcurrentHashMap<Integer, String> mMessageMap;
    private static int[] mZygotePids;
    private static boolean sEnhanceEnable = true;
    private static AnrManagerService sInstance;
    private ApplicationInfo aInfo;
    private int mAmsPid;
    private AnrDumpManager mAnrDumpManager;
    private AnrMonitorHandler mAnrHandler;
    private ActivityManagerService mService;
    private final AtomicLong mLastCpuUpdateTime = new AtomicLong(0);
    private long mEventBootCompleted = 0;
    private long mCpuDeferred = 0;
    private int mAnrFlow = -1;
    private int mAnrOption = -1;
    private ExceptionLog exceptionLog = null;
    private File mTracesFile = null;
    private final long[] offsets = new long[2];
    private float loadingProgress = 1.0f;
    private long anrDialogDelayMs = 0;
    private IncrementalMetrics incrementalMetrics = null;
    private boolean isAgingVersion = "1".equals(SystemProperties.get("persist.sys.agingtest", "0"));
    private Class<?> mProcessRecord = getProcessRecord();
    private Class<?> mProcessErrorStateRecord = getProcessErrorStateRecord();
    private Class<?> mAMS = getActivityManagerService();
    private Class<?> mAppProfiler = getAppProfiler();
    private Method mKill = getProcessRecordMethod("killLocked", new Class[]{String.class, Integer.TYPE, Boolean.TYPE});
    private Method mUpdateCpuStatsNow = getAMSMethod("updateCpuStatsNow");
    private Method mNoteProcessANR = getBatteryStatsServiceMethod("noteProcessAnr", new Class[]{String.class, Integer.TYPE});
    private Method mScheduleServiceTimeoutLocked = getActiveServicesMethod("scheduleServiceTimeoutLocked", new Class[]{ProcessRecord.class});
    private Method mMakeAppNotRespondingLocked = getProcessErrorStateRecordMethod("makeAppNotRespondingLSP", new Class[]{String.class, String.class, String.class});
    private Field mPidField = getProcessRecordField("mPid");
    private Field mProcessNameField = getProcessRecordField("processName");
    private Field mThreadField = getProcessRecordField("mThread");
    private Field mNotRespondingField = getProcessErrorStateRecordField("mNotResponding");
    private Field mCrashingField = getProcessErrorStateRecordField("mCrashing");
    private Field mUserIdField = getProcessRecordField("userId");
    private Field mUidField = getProcessRecordField("uid");
    private Field mInfoField = getProcessRecordField("info");
    private Field mPkgListField = getProcessRecordField("mPkgList");
    private Field mPersistentField = getProcessRecordField("mPersistent");
    private Field mParentPidField = getProcessRecordField("mPid");
    private Field mParentAppField = getActivityRecordField("app");
    private Field mLruProcessesField = getPLField("mLruProcesses");
    private Field mProcessListField = getAMSField("mProcessList");
    private Field mProcessCpuTrackerField = getAppProfilerField("mProcessCpuTracker");
    private Field mMonitorCpuUsageField = getAppProfilerField("MONITOR_CPU_USAGE");
    private Field mShowNotRespondingUiMsgField = getAMSField("SHOW_NOT_RESPONDING_UI_MSG");
    private Field mBatteryStatsServiceField = getAMSField("mBatteryStatsService");
    private Field mActiveServicesField = getAMSField("mServices");
    private Field mUiHandlerField = getAMSField("mUiHandler");
    private Field mControllerField = getATMField("mController");

    static {
        boolean z = true;
        if (!"user".equals(Build.TYPE) && !"userdebug".equals(Build.TYPE)) {
            z = false;
        }
        IS_USER_BUILD = z;
        IS_USER_LOAD = "user".equals(Build.TYPE);
        mZygotePids = null;
        mDumpStackTraces = new Object();
        NATIVE_STACKS_OF_INTEREST = new String[]{"/system/bin/netd", "/system/bin/audioserver", "/system/bin/cameraserver", "/system/bin/drmserver", "/system/bin/mediadrmserver", "/system/bin/mediaserver", "/system/bin/sdcard", "/system/bin/surfaceflinger", "vendor/bin/hw/camerahalserver", "/system/bin/vold", "media.extractor", "media.metrics", "media.codec", "media.swcodec", "media.transcoding", "com.android.bluetooth", "/apex/com.android.os.statsd/bin/statsd"};
        mAnrProcessStats = new ProcessCpuTracker(false);
        mMessageMap = new ConcurrentHashMap<>();
        lock = new Object();
        sInstance = null;
    }

    private Class<?> getProcessRecord() {
        try {
            return Class.forName(PROCESS_RECORD);
        } catch (Exception e) {
            return null;
        }
    }

    private Class<?> getProcessErrorStateRecord() {
        try {
            return Class.forName(PROCESS_ERROR_STATE_RECORD);
        } catch (Exception e) {
            return null;
        }
    }

    private Class<?> getActivityManagerService() {
        try {
            return Class.forName(ACTIVITY_MANAGER);
        } catch (Exception e) {
            return null;
        }
    }

    private Class<?> getAppProfiler() {
        try {
            return Class.forName(APP_PROFILER);
        } catch (Exception e) {
            return null;
        }
    }

    private Method getProcessRecordMethod(String func, Class[] cls) {
        try {
            Method method = this.mProcessRecord.getDeclaredMethod(func, cls);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            Slog.w(TAG, "getProcessRecordMethod Exception: " + e);
            return null;
        }
    }

    private Method getProcessErrorStateRecordMethod(String func, Class[] cls) {
        try {
            Method method = this.mProcessErrorStateRecord.getDeclaredMethod(func, cls);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            Slog.w(TAG, "getProcessErrorStateRecordMethod Exception: " + e);
            return null;
        }
    }

    private Method getAMSMethod(String func) {
        try {
            Method method = this.mAMS.getDeclaredMethod(func, new Class[0]);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            return null;
        }
    }

    private Method getBatteryStatsServiceMethod(String func, Class[] cls) {
        try {
            Class<?> batteryStatsService = Class.forName(BATTERY_STATS);
            Method method = batteryStatsService.getDeclaredMethod(func, cls);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            return null;
        }
    }

    private Method getActiveServicesMethod(String func, Class[] cls) {
        try {
            Class<?> activeServices = Class.forName(ACTIVE_SERVICES);
            return activeServices.getDeclaredMethod(func, cls);
        } catch (Exception e) {
            return null;
        }
    }

    private Method getAppErrorsMethod(String func, Class[] cls) {
        try {
            Class<?> appErrors = Class.forName(APP_ERRORS);
            Method method = appErrors.getDeclaredMethod(func, cls);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            return null;
        }
    }

    private Field getProcessRecordField(String var) {
        try {
            Field field = this.mProcessRecord.getDeclaredField(var);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    private Field getProcessErrorStateRecordField(String var) {
        try {
            Field field = this.mProcessErrorStateRecord.getDeclaredField(var);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    private Field getActivityRecordField(String var) {
        try {
            Class<?> mActivityRecord = Class.forName(ACTIVITY_RECORD);
            Field field = mActivityRecord.getDeclaredField(var);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    private Field getAMSField(String var) {
        try {
            Field field = this.mAMS.getDeclaredField(var);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    private Field getAppProfilerField(String var) {
        try {
            Field field = this.mAppProfiler.getDeclaredField(var);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    private Field getATMField(String var) {
        try {
            Class<?> mATM = Class.forName(ATM_SERVICE);
            Field field = mATM.getDeclaredField(var);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    private Field getPLField(String var) {
        try {
            Class<?> mATM = Class.forName(PROCESS_LIST);
            Field field = mATM.getDeclaredField(var);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    public static AnrManagerService getInstance() {
        if (sInstance == null) {
            synchronized (lock) {
                if (sInstance == null) {
                    sInstance = new AnrManagerService();
                }
            }
        }
        return sInstance;
    }

    public void startAnrManagerService(int pid) {
        Slog.i(TAG, "startAnrManagerService");
        this.mAmsPid = pid;
        HandlerThread handlerThread = new HandlerThread("AnrMonitorThread");
        handlerThread.start();
        this.mAnrHandler = new AnrMonitorHandler(handlerThread.getLooper());
        this.mAnrDumpManager = new AnrDumpManager();
        mAnrProcessStats.init();
        prepareStackTraceFile(SystemProperties.get("dalvik.vm.stack-trace-file", (String) null));
        File traceFile = new File(SystemProperties.get("dalvik.vm.stack-trace-file", (String) null));
        File traceDir = traceFile.getParentFile();
        if (traceDir != null && !SELinux.restoreconRecursive(traceDir)) {
            Slog.i(TAG, "startAnrManagerService SELinux.restoreconRecursive fail dir = " + traceDir.toString());
        }
        if (SystemProperties.get("ro.vendor.have_aee_feature").equals("1")) {
            this.exceptionLog = ExceptionLog.getInstance();
        }
        this.mKill.setAccessible(true);
        this.mUpdateCpuStatsNow.setAccessible(true);
        this.mNoteProcessANR.setAccessible(true);
        this.mScheduleServiceTimeoutLocked.setAccessible(true);
        this.mMakeAppNotRespondingLocked.setAccessible(true);
    }

    public void sendBroadcastMonitorMessage(long timeoutTime, long mTimeoutPeriod) {
        if (2 == checkAnrDebugMechanism()) {
            Message broadcastMonitor = this.mAnrHandler.obtainMessage(START_MONITOR_BROADCAST_TIMEOUT_MSG);
            this.mAnrHandler.sendMessageAtTime(broadcastMonitor, timeoutTime - (mTimeoutPeriod / 2));
        }
    }

    public void removeBroadcastMonitorMessage() {
        if (2 == checkAnrDebugMechanism()) {
            this.mAnrHandler.removeMessages(START_MONITOR_BROADCAST_TIMEOUT_MSG);
        }
    }

    public void sendServiceMonitorMessage() {
        long now = SystemClock.uptimeMillis();
        if (2 == checkAnrDebugMechanism()) {
            Message serviceMonitor = this.mAnrHandler.obtainMessage(START_MONITOR_SERVICE_TIMEOUT_MSG);
            this.mAnrHandler.sendMessageAtTime(serviceMonitor, 13333 + now);
        }
    }

    public void removeServiceMonitorMessage() {
        if (2 == checkAnrDebugMechanism()) {
            this.mAnrHandler.removeMessages(START_MONITOR_SERVICE_TIMEOUT_MSG);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:157:0x03e9
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public boolean startAnrDump(com.android.server.am.ActivityManagerService r50, com.android.server.am.ProcessErrorStateRecord r51, java.lang.String r52, android.content.pm.ApplicationInfo r53, java.lang.String r54, com.android.server.am.ProcessRecord r55, boolean r56, java.lang.String r57, boolean r58, long r59, boolean r61, java.util.UUID r62) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.server.anr.AnrManagerService.startAnrDump(com.android.server.am.ActivityManagerService, com.android.server.am.ProcessErrorStateRecord, java.lang.String, android.content.pm.ApplicationInfo, java.lang.String, com.android.server.am.ProcessRecord, boolean, java.lang.String, boolean, long, boolean, java.util.UUID):boolean");
    }

    /* loaded from: classes.dex */
    public class AnrMonitorHandler extends Handler {
        public AnrMonitorHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AnrManagerService.START_MONITOR_BROADCAST_TIMEOUT_MSG /* 1001 */:
                case AnrManagerService.START_MONITOR_SERVICE_TIMEOUT_MSG /* 1002 */:
                case AnrManagerService.START_MONITOR_KEYDISPATCHING_TIMEOUT_MSG /* 1004 */:
                    AnrManagerService.this.updateProcessStats();
                    return;
                case AnrManagerService.START_ANR_DUMP_MSG /* 1003 */:
                    AnrDumpRecord adp = (AnrDumpRecord) msg.obj;
                    boolean isSilentANR = msg.arg1 == 1;
                    Slog.i(AnrManagerService.TAG, "START_ANR_DUMP_MSG: " + adp + ", isSilentANR = " + isSilentANR);
                    AnrManagerService.this.mAnrDumpManager.dumpAnrDebugInfo(adp, true, isSilentANR);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class BinderWatchdog {
        private static final int MAX_LINES = 64;
        private static final int MAX_TIMEOUT_PIDS = 5;

        protected BinderWatchdog() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes.dex */
        public static class BinderInfo {
            protected static final int INDEX_FROM = 1;
            protected static final int INDEX_TO = 3;
            protected int mDstPid;
            protected int mDstTid;
            protected int mSrcPid;
            protected int mSrcTid;
            protected String mText;

            protected BinderInfo(String text) {
                if (text != null && text.length() > 0) {
                    this.mText = new String(text);
                    String[] tokens = text.split(" ");
                    String[] from = tokens[1].split(":");
                    if (from != null && from.length == 2) {
                        this.mSrcPid = Integer.parseInt(from[0]);
                        this.mSrcTid = Integer.parseInt(from[1]);
                    }
                    String[] to = tokens[INDEX_TO].split(":");
                    if (to != null && to.length == 2) {
                        this.mDstPid = Integer.parseInt(to[0]);
                        this.mDstTid = Integer.parseInt(to[1]);
                    }
                }
            }
        }

        public static final ArrayList<Integer> getTimeoutBinderPidList(int pid, int tid) {
            if (pid <= 0) {
                return null;
            }
            ArrayList<BinderInfo> binderList = readTimeoutBinderListFromFile();
            int count = 0;
            ArrayList<Integer> pidList = new ArrayList<>();
            for (BinderInfo next = getBinderInfo(pid, tid, binderList); next != null; next = getBinderInfo(next.mDstPid, next.mDstTid, binderList)) {
                if (next.mDstPid > 0) {
                    count++;
                    if (!pidList.contains(Integer.valueOf(next.mDstPid))) {
                        Slog.i(AnrManagerService.TAG, "getTimeoutBinderPidList pid added: " + next.mDstPid + " " + next.mText);
                        pidList.add(Integer.valueOf(next.mDstPid));
                    } else {
                        Slog.i(AnrManagerService.TAG, "getTimeoutBinderPidList pid existed: " + next.mDstPid + " " + next.mText);
                    }
                    if (count >= MAX_TIMEOUT_PIDS) {
                        break;
                    }
                }
            }
            if (pidList.size() == 0) {
                return getTimeoutBinderFromPid(pid, binderList);
            }
            return pidList;
        }

        public static final ArrayList<Integer> getTimeoutBinderFromPid(int pid, ArrayList<BinderInfo> binderList) {
            if (pid <= 0 || binderList == null) {
                return null;
            }
            Slog.i(AnrManagerService.TAG, "getTimeoutBinderFromPid " + pid + " list size: " + binderList.size());
            int count = 0;
            ArrayList<Integer> pidList = new ArrayList<>();
            Iterator<BinderInfo> it = binderList.iterator();
            while (it.hasNext()) {
                BinderInfo bi = it.next();
                if (bi != null && bi.mSrcPid == pid) {
                    count++;
                    if (!pidList.contains(Integer.valueOf(bi.mDstPid))) {
                        Slog.i(AnrManagerService.TAG, "getTimeoutBinderFromPid pid added: " + bi.mDstPid + " " + bi.mText);
                        pidList.add(Integer.valueOf(bi.mDstPid));
                    } else {
                        Slog.i(AnrManagerService.TAG, "getTimeoutBinderFromPid pid existed: " + bi.mDstPid + " " + bi.mText);
                    }
                    if (count >= MAX_TIMEOUT_PIDS) {
                        break;
                    }
                }
            }
            return pidList;
        }

        private static BinderInfo getBinderInfo(int pid, int tid, ArrayList<BinderInfo> binderList) {
            if (binderList == null || binderList.size() == 0 || pid == 0) {
                return null;
            }
            binderList.size();
            Iterator<BinderInfo> it = binderList.iterator();
            while (it.hasNext()) {
                BinderInfo bi = it.next();
                if (bi.mSrcPid == pid && bi.mSrcTid == tid) {
                    Slog.i(AnrManagerService.TAG, "Timeout binder pid found: " + bi.mDstPid + " " + bi.mText);
                    return bi;
                }
            }
            return null;
        }

        private static final ArrayList<BinderInfo> readTimeoutBinderListFromFile() {
            BufferedReader br = null;
            ArrayList<BinderInfo> binderList = null;
            try {
                try {
                    File file = new File("/dev/binderfs/binder_logs/timeout_log");
                    if (!file.exists()) {
                        if (0 != 0) {
                            try {
                                br.close();
                            } catch (IOException ioe) {
                                Slog.e(AnrManagerService.TAG, "IOException when close buffer reader:", ioe);
                            }
                        }
                        return null;
                    }
                    br = new BufferedReader(new FileReader(file));
                    binderList = new ArrayList<>();
                    try {
                        do {
                            String line = br.readLine();
                            if (line == null) {
                                break;
                            }
                            BinderInfo bi = new BinderInfo(line);
                            if (bi.mSrcPid > 0) {
                                binderList.add(bi);
                            }
                        } while (binderList.size() <= MAX_LINES);
                        break;
                        br.close();
                    } catch (IOException ioe2) {
                        Slog.e(AnrManagerService.TAG, "IOException when close buffer reader:", ioe2);
                    }
                    return binderList;
                } catch (Throwable th) {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException ioe3) {
                            Slog.e(AnrManagerService.TAG, "IOException when close buffer reader:", ioe3);
                        }
                    }
                    return binderList;
                }
            } catch (FileNotFoundException e) {
                Slog.e(AnrManagerService.TAG, "FileNotFoundException", e);
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ioe4) {
                        Slog.e(AnrManagerService.TAG, "IOException when close buffer reader:", ioe4);
                    }
                }
                return binderList;
            } catch (IOException e2) {
                Slog.e(AnrManagerService.TAG, "IOException when gettting Binder. ", e2);
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ioe5) {
                        Slog.e(AnrManagerService.TAG, "IOException when close buffer reader:", ioe5);
                    }
                }
                return binderList;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* loaded from: classes.dex */
        public static class TransactionInfo {
            protected String atime;
            protected String direction;
            protected String ktime;
            protected String rcv_pid;
            protected String rcv_tid;
            protected String snd_pid;
            protected String snd_tid;
            protected long spent_time;

            protected TransactionInfo() {
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0207 A[Catch: IOException -> 0x01e8, TRY_ENTER, TRY_LEAVE, TryCatch #9 {IOException -> 0x01e8, blocks: (B:55:0x01e4, B:66:0x0207, B:71:0x0218), top: B:81:0x001b }] */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0218 A[Catch: IOException -> 0x01e8, TRY_ENTER, TRY_LEAVE, TryCatch #9 {IOException -> 0x01e8, blocks: (B:55:0x01e4, B:66:0x0207, B:71:0x0218), top: B:81:0x001b }] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0221 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static final void readTransactionInfoFromFile(int r19, java.util.ArrayList<java.lang.Integer> r20) {
            /*
                Method dump skipped, instructions count: 556
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mediatek.server.anr.AnrManagerService.BinderWatchdog.readTransactionInfoFromFile(int, java.util.ArrayList):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void setTransactionTimeoutPids(int pid, ArrayList<Integer> desList, SparseArray<Boolean> lastPids) {
            int pidValue;
            ArrayList<Integer> tmpPidList = new ArrayList<>();
            readTransactionInfoFromFile(pid, tmpPidList);
            if (tmpPidList.size() > 0) {
                Iterator<Integer> it = tmpPidList.iterator();
                while (it.hasNext()) {
                    Integer bpid = it.next();
                    if (!(bpid == null || (pidValue = bpid.intValue()) == pid || desList.contains(Integer.valueOf(pidValue)))) {
                        desList.add(Integer.valueOf(pidValue));
                        if (lastPids != null) {
                            lastPids.remove(pidValue);
                        }
                    }
                }
            }
        }
    }

    public void prepareStackTraceFile(String filePath) {
        Slog.i(TAG, "prepareStackTraceFile: " + filePath);
        if (filePath != null && filePath.length() != 0) {
            File traceFile = new File(filePath);
            try {
                File traceDir = traceFile.getParentFile();
                if (traceDir != null) {
                    if (!traceDir.exists()) {
                        traceDir.mkdirs();
                    }
                    FileUtils.setPermissions(traceDir.getPath(), 509, -1, -1);
                }
                if (!traceFile.exists()) {
                    traceFile.createNewFile();
                }
                FileUtils.setPermissions(traceFile.getPath(), 438, -1, -1);
            } catch (IOException e) {
                Slog.e(TAG, "Unable to prepare stack trace file: " + filePath, e);
            }
        }
    }

    /* loaded from: classes.dex */
    public class AnrDumpRecord {
        protected String mAnnotation;
        protected long mAnrTime;
        protected boolean mAppCrashing;
        protected ApplicationInfo mAppInfo;
        protected int mAppPid;
        protected String mAppString;
        protected int mAppUid;
        protected int mAppUserid;
        public String mCpuInfo = null;
        public StringBuilder mInfo = new StringBuilder(256);
        protected boolean mIsCancelled;
        protected boolean mIsCompleted;
        protected int mParentAppPid;
        protected String mParentShortComponentName;
        protected String mProcessName;
        protected String mShortComponentName;

        public AnrDumpRecord(int appPid, int appUid, int appUserid, ApplicationInfo appInfo, boolean appCrashing, String processName, String appString, String shortComponentName, int parentAppPid, String parentShortComponentName, String annotation, long anrTime) {
            this.mAppPid = appPid;
            this.mAppUid = appUid;
            this.mAppUserid = appUserid;
            this.mAppInfo = appInfo;
            this.mAppCrashing = appCrashing;
            this.mProcessName = processName;
            this.mAppString = appString;
            this.mShortComponentName = shortComponentName;
            this.mParentAppPid = parentAppPid;
            this.mParentShortComponentName = parentShortComponentName;
            this.mAnnotation = annotation;
            this.mAnrTime = anrTime;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isValid() {
            if (this.mAppPid > 0 && !this.mIsCancelled && !this.mIsCompleted) {
                return true;
            }
            Slog.e(AnrManagerService.TAG, "isValid! mAppPid: " + this.mAppPid + "mIsCancelled: " + this.mIsCancelled + "mIsCompleted: " + this.mIsCompleted);
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("AnrDumpRecord{ ");
            sb.append(this.mAnnotation);
            sb.append(" ");
            sb.append(this.mAppString);
            sb.append(" IsCompleted:" + this.mIsCompleted);
            sb.append(" IsCancelled:" + this.mIsCancelled);
            sb.append(" }");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public class AnrDumpManager {
        public HashMap<Integer, AnrDumpRecord> mDumpList = new HashMap<>();

        public AnrDumpManager() {
        }

        public void cancelDump(AnrDumpRecord dumpRecord) {
            if (dumpRecord != null && dumpRecord.mAppPid != -1) {
                synchronized (this.mDumpList) {
                    AnrDumpRecord value = this.mDumpList.remove(Integer.valueOf(dumpRecord.mAppPid));
                    if (value != null) {
                        value.mIsCancelled = true;
                    }
                }
            }
        }

        public void removeDumpRecord(AnrDumpRecord dumpRecord) {
            if (dumpRecord != null && dumpRecord.mAppPid != -1) {
                synchronized (this.mDumpList) {
                    this.mDumpList.remove(Integer.valueOf(dumpRecord.mAppPid));
                }
            }
        }

        public void startAsyncDump(AnrDumpRecord dumpRecord, boolean isSilentANR) {
            Slog.i(AnrManagerService.TAG, "startAsyncDump: " + dumpRecord + ", isSilentANR = " + isSilentANR);
            if (dumpRecord != null && dumpRecord.mAppPid != -1) {
                int appPid = dumpRecord.mAppPid;
                synchronized (this.mDumpList) {
                    if (!this.mDumpList.containsKey(Integer.valueOf(appPid))) {
                        this.mDumpList.put(Integer.valueOf(appPid), dumpRecord);
                        Message msg = AnrManagerService.this.mAnrHandler.obtainMessage(AnrManagerService.START_ANR_DUMP_MSG, dumpRecord);
                        msg.arg1 = isSilentANR ? 1 : 0;
                        AnrManagerService.this.mAnrHandler.sendMessageAtTime(msg, SystemClock.uptimeMillis() + 500);
                    }
                }
            }
        }

        private boolean isDumpable(AnrDumpRecord dumpRecord) {
            synchronized (this.mDumpList) {
                if (dumpRecord != null) {
                    if (this.mDumpList.containsKey(Integer.valueOf(dumpRecord.mAppPid)) && dumpRecord.isValid()) {
                        return true;
                    }
                }
                return false;
            }
        }

        public void dumpAnrDebugInfo(AnrDumpRecord dumpRecord, boolean onlyDumpSelf, boolean isSilentANR) {
            Slog.i(AnrManagerService.TAG, "dumpAnrDebugInfo begin: " + dumpRecord + ", onlyDumpSelf = " + onlyDumpSelf + ", isSilentANR = " + isSilentANR);
            if (dumpRecord != null) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!isDumpable(dumpRecord)) {
                    Slog.i(AnrManagerService.TAG, "dumpAnrDebugInfo dump stopped: " + dumpRecord);
                    return;
                }
                dumpAnrDebugInfoLocked(dumpRecord, onlyDumpSelf, isSilentANR);
                Slog.i(AnrManagerService.TAG, "dumpAnrDebugInfo end: " + dumpRecord + ", onlyDumpSelf = " + onlyDumpSelf + " , isSilentANR = " + isSilentANR);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:115:0x031d  */
        /* JADX WARN: Removed duplicated region for block: B:116:0x031f A[Catch: all -> 0x0471, TryCatch #5 {, blocks: (B:4:0x0009, B:6:0x0035, B:8:0x0037, B:15:0x006e, B:16:0x0075, B:18:0x007d, B:20:0x0085, B:21:0x0092, B:22:0x0098, B:57:0x0155, B:61:0x015a, B:63:0x0163, B:64:0x0166, B:66:0x016d, B:68:0x0173, B:70:0x017d, B:71:0x0186, B:73:0x0192, B:75:0x01ce, B:79:0x01eb, B:81:0x0200, B:82:0x020f, B:84:0x0220, B:85:0x022d, B:87:0x0232, B:89:0x0238, B:90:0x0247, B:92:0x024f, B:93:0x0266, B:95:0x027b, B:97:0x02cc, B:99:0x02e6, B:105:0x02ee, B:107:0x02f7, B:109:0x02fe, B:111:0x030c, B:112:0x0316, B:116:0x031f, B:119:0x0327, B:121:0x0332, B:122:0x033f, B:123:0x0345, B:125:0x034b, B:127:0x035d, B:129:0x0363, B:131:0x0369, B:133:0x0373, B:135:0x0379, B:138:0x037f, B:145:0x039c, B:147:0x03c7, B:149:0x03c9, B:151:0x03e2, B:152:0x03f7, B:155:0x0428, B:156:0x044b, B:158:0x046a, B:160:0x046c, B:161:0x046f, B:153:0x03f8, B:154:0x0427), top: B:171:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:119:0x0327 A[Catch: all -> 0x0471, TryCatch #5 {, blocks: (B:4:0x0009, B:6:0x0035, B:8:0x0037, B:15:0x006e, B:16:0x0075, B:18:0x007d, B:20:0x0085, B:21:0x0092, B:22:0x0098, B:57:0x0155, B:61:0x015a, B:63:0x0163, B:64:0x0166, B:66:0x016d, B:68:0x0173, B:70:0x017d, B:71:0x0186, B:73:0x0192, B:75:0x01ce, B:79:0x01eb, B:81:0x0200, B:82:0x020f, B:84:0x0220, B:85:0x022d, B:87:0x0232, B:89:0x0238, B:90:0x0247, B:92:0x024f, B:93:0x0266, B:95:0x027b, B:97:0x02cc, B:99:0x02e6, B:105:0x02ee, B:107:0x02f7, B:109:0x02fe, B:111:0x030c, B:112:0x0316, B:116:0x031f, B:119:0x0327, B:121:0x0332, B:122:0x033f, B:123:0x0345, B:125:0x034b, B:127:0x035d, B:129:0x0363, B:131:0x0369, B:133:0x0373, B:135:0x0379, B:138:0x037f, B:145:0x039c, B:147:0x03c7, B:149:0x03c9, B:151:0x03e2, B:152:0x03f7, B:155:0x0428, B:156:0x044b, B:158:0x046a, B:160:0x046c, B:161:0x046f, B:153:0x03f8, B:154:0x0427), top: B:171:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:125:0x034b A[Catch: all -> 0x0471, TryCatch #5 {, blocks: (B:4:0x0009, B:6:0x0035, B:8:0x0037, B:15:0x006e, B:16:0x0075, B:18:0x007d, B:20:0x0085, B:21:0x0092, B:22:0x0098, B:57:0x0155, B:61:0x015a, B:63:0x0163, B:64:0x0166, B:66:0x016d, B:68:0x0173, B:70:0x017d, B:71:0x0186, B:73:0x0192, B:75:0x01ce, B:79:0x01eb, B:81:0x0200, B:82:0x020f, B:84:0x0220, B:85:0x022d, B:87:0x0232, B:89:0x0238, B:90:0x0247, B:92:0x024f, B:93:0x0266, B:95:0x027b, B:97:0x02cc, B:99:0x02e6, B:105:0x02ee, B:107:0x02f7, B:109:0x02fe, B:111:0x030c, B:112:0x0316, B:116:0x031f, B:119:0x0327, B:121:0x0332, B:122:0x033f, B:123:0x0345, B:125:0x034b, B:127:0x035d, B:129:0x0363, B:131:0x0369, B:133:0x0373, B:135:0x0379, B:138:0x037f, B:145:0x039c, B:147:0x03c7, B:149:0x03c9, B:151:0x03e2, B:152:0x03f7, B:155:0x0428, B:156:0x044b, B:158:0x046a, B:160:0x046c, B:161:0x046f, B:153:0x03f8, B:154:0x0427), top: B:171:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:140:0x0392  */
        /* JADX WARN: Removed duplicated region for block: B:141:0x0394  */
        /* JADX WARN: Removed duplicated region for block: B:143:0x0397  */
        /* JADX WARN: Removed duplicated region for block: B:144:0x039a  */
        /* JADX WARN: Removed duplicated region for block: B:147:0x03c7 A[Catch: all -> 0x0471, DONT_GENERATE, TryCatch #5 {, blocks: (B:4:0x0009, B:6:0x0035, B:8:0x0037, B:15:0x006e, B:16:0x0075, B:18:0x007d, B:20:0x0085, B:21:0x0092, B:22:0x0098, B:57:0x0155, B:61:0x015a, B:63:0x0163, B:64:0x0166, B:66:0x016d, B:68:0x0173, B:70:0x017d, B:71:0x0186, B:73:0x0192, B:75:0x01ce, B:79:0x01eb, B:81:0x0200, B:82:0x020f, B:84:0x0220, B:85:0x022d, B:87:0x0232, B:89:0x0238, B:90:0x0247, B:92:0x024f, B:93:0x0266, B:95:0x027b, B:97:0x02cc, B:99:0x02e6, B:105:0x02ee, B:107:0x02f7, B:109:0x02fe, B:111:0x030c, B:112:0x0316, B:116:0x031f, B:119:0x0327, B:121:0x0332, B:122:0x033f, B:123:0x0345, B:125:0x034b, B:127:0x035d, B:129:0x0363, B:131:0x0369, B:133:0x0373, B:135:0x0379, B:138:0x037f, B:145:0x039c, B:147:0x03c7, B:149:0x03c9, B:151:0x03e2, B:152:0x03f7, B:155:0x0428, B:156:0x044b, B:158:0x046a, B:160:0x046c, B:161:0x046f, B:153:0x03f8, B:154:0x0427), top: B:171:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:149:0x03c9 A[Catch: all -> 0x0471, TryCatch #5 {, blocks: (B:4:0x0009, B:6:0x0035, B:8:0x0037, B:15:0x006e, B:16:0x0075, B:18:0x007d, B:20:0x0085, B:21:0x0092, B:22:0x0098, B:57:0x0155, B:61:0x015a, B:63:0x0163, B:64:0x0166, B:66:0x016d, B:68:0x0173, B:70:0x017d, B:71:0x0186, B:73:0x0192, B:75:0x01ce, B:79:0x01eb, B:81:0x0200, B:82:0x020f, B:84:0x0220, B:85:0x022d, B:87:0x0232, B:89:0x0238, B:90:0x0247, B:92:0x024f, B:93:0x0266, B:95:0x027b, B:97:0x02cc, B:99:0x02e6, B:105:0x02ee, B:107:0x02f7, B:109:0x02fe, B:111:0x030c, B:112:0x0316, B:116:0x031f, B:119:0x0327, B:121:0x0332, B:122:0x033f, B:123:0x0345, B:125:0x034b, B:127:0x035d, B:129:0x0363, B:131:0x0369, B:133:0x0373, B:135:0x0379, B:138:0x037f, B:145:0x039c, B:147:0x03c7, B:149:0x03c9, B:151:0x03e2, B:152:0x03f7, B:155:0x0428, B:156:0x044b, B:158:0x046a, B:160:0x046c, B:161:0x046f, B:153:0x03f8, B:154:0x0427), top: B:171:0x0009 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected void dumpAnrDebugInfoLocked(com.mediatek.server.anr.AnrManagerService.AnrDumpRecord r30, boolean r31, boolean r32) throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 1140
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mediatek.server.anr.AnrManagerService.AnrDumpManager.dumpAnrDebugInfoLocked(com.mediatek.server.anr.AnrManagerService$AnrDumpRecord, boolean, boolean):void");
        }
    }

    public boolean isJavaProcess(int pid) {
        int[] iArr;
        if (pid <= 0) {
            return false;
        }
        if (mZygotePids == null) {
            String[] commands = {"zygote64", "zygote"};
            mZygotePids = Process.getPidsForCommands(commands);
        }
        if (mZygotePids != null) {
            int parentPid = Process.getParentPid(pid);
            for (int zygotePid : mZygotePids) {
                if (parentPid == zygotePid) {
                    return true;
                }
            }
        }
        Slog.i(TAG, "pid: " + pid + " is not a Java process");
        return false;
    }

    private Boolean isException() {
        try {
            if ("free".equals(SystemProperties.get("vendor.debug.mtk.aee.status", "free")) && "free".equals(SystemProperties.get("vendor.debug.mtk.aee.status64", "free")) && "free".equals(SystemProperties.get("vendor.debug.mtk.aee.vstatus", "free")) && "free".equals(SystemProperties.get("vendor.debug.mtk.aee.vstatus64", "free"))) {
                return false;
            }
        } catch (Exception e) {
            Slog.e(TAG, "isException: " + e.toString());
        }
        return true;
    }

    public void informMessageDump(String MessageInfo, int pid) {
        if (mMessageMap.containsKey(Integer.valueOf(pid))) {
            String tmpString = mMessageMap.get(Integer.valueOf(pid));
            if (tmpString.length() > MESSAGE_MAP_BUFFER_SIZE_MAX) {
                tmpString = "";
            }
            mMessageMap.put(Integer.valueOf(pid), tmpString + MessageInfo);
        } else {
            if (mMessageMap.size() > MESSAGE_MAP_BUFFER_COUNT_MAX) {
                mMessageMap.clear();
            }
            mMessageMap.put(Integer.valueOf(pid), MessageInfo);
        }
        Slog.i(TAG, "informMessageDump pid= " + pid);
    }

    public int checkAnrDebugMechanism() {
        if (!sEnhanceEnable) {
            return 0;
        }
        if (-1 == this.mAnrOption) {
            int option = 2;
            if (IS_USER_LOAD) {
                option = 1;
            }
            this.mAnrOption = SystemProperties.getInt("persist.vendor.anr.enhancement", option);
            if (!this.isAgingVersion) {
                this.mAnrOption = 0;
            }
        }
        int option2 = this.mAnrOption;
        switch (option2) {
            case MtkPacketMessage.NF_DROP /* 0 */:
                return 0;
            case 1:
                return 1;
            case DebugInfo.Architecture.IS_32BIT /* 2 */:
                return 2;
            default:
                return 2;
        }
    }

    public void writeEvent(int event) {
        switch (event) {
            case EVENT_BOOT_COMPLETED /* 9001 */:
                this.mEventBootCompleted = SystemClock.uptimeMillis();
                return;
            default:
                return;
        }
    }

    public boolean isAnrDeferrable() {
        if (checkAnrDebugMechanism() == 0) {
            return false;
        }
        if ("dexopt".equals(SystemProperties.get("vendor.anr.autotest"))) {
            Slog.i(TAG, "We are doing TestDexOptSkipANR; return true in this case");
            return true;
        } else if ("enable".equals(SystemProperties.get("vendor.anr.autotest"))) {
            Slog.i(TAG, "Do Auto Test, don't skip ANR");
            return false;
        } else {
            long now = SystemClock.uptimeMillis();
            if (!IS_USER_BUILD) {
                long j = this.mEventBootCompleted;
                if (j == 0 || now - j < ANR_BOOT_DEFER_TIME) {
                    Slog.i(TAG, "isAnrDeferrable(): true since mEventBootCompleted = " + this.mEventBootCompleted + " now = " + now);
                    return true;
                } else if (isException().booleanValue()) {
                    Slog.i(TAG, "isAnrDeferrable(): true since exception");
                    return true;
                } else {
                    ProcessCpuTracker processCpuTracker = mAnrProcessStats;
                    float lastCpuUsage = processCpuTracker.getTotalCpuPercent();
                    updateProcessStats();
                    float currentCpuUsage = processCpuTracker.getTotalCpuPercent();
                    if (lastCpuUsage > ANR_CPU_THRESHOLD && currentCpuUsage > ANR_CPU_THRESHOLD) {
                        long j2 = this.mCpuDeferred;
                        if (j2 == 0) {
                            this.mCpuDeferred = now;
                            Slog.i(TAG, "isAnrDeferrable(): true since CpuUsage = " + currentCpuUsage + ", mCpuDeferred = " + this.mCpuDeferred);
                            return true;
                        } else if (now - j2 < ANR_CPU_DEFER_TIME) {
                            Slog.i(TAG, "isAnrDeferrable(): true since CpuUsage = " + currentCpuUsage + ", mCpuDeferred = " + this.mCpuDeferred + ", now = " + now);
                            return true;
                        }
                    }
                    this.mCpuDeferred = 0L;
                }
            }
            return false;
        }
    }

    public boolean isAnrFlowSkipped(int appPid, String appProcessName, String annotation) {
        if (-1 == this.mAnrFlow) {
            this.mAnrFlow = SystemProperties.getInt("persist.vendor.dbg.anrflow", 0);
        }
        Slog.i(TAG, "isANRFlowSkipped() AnrFlow = " + this.mAnrFlow);
        switch (this.mAnrFlow) {
            case MtkPacketMessage.NF_DROP /* 0 */:
                return false;
            case 1:
                Slog.i(TAG, "Skipping ANR flow: " + appPid + " " + appProcessName + " " + annotation);
                return true;
            case DebugInfo.Architecture.IS_32BIT /* 2 */:
                if (appPid != Process.myPid()) {
                    Slog.i(TAG, "Skipping ANR flow: " + appPid + " " + appProcessName + " " + annotation);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Kill process (");
                    sb.append(appPid);
                    sb.append(") due to ANR");
                    Slog.w(TAG, sb.toString());
                    Process.killProcess(appPid);
                }
                return true;
            default:
                return false;
        }
    }

    public void updateProcessStats() {
        ProcessCpuTracker processCpuTracker = mAnrProcessStats;
        synchronized (processCpuTracker) {
            long now = SystemClock.uptimeMillis();
            if (now - this.mLastCpuUpdateTime.get() > MONITOR_CPU_MIN_TIME) {
                this.mLastCpuUpdateTime.set(now);
                processCpuTracker.update();
            }
        }
    }

    public String getProcessState() {
        String printCurrentState;
        ProcessCpuTracker processCpuTracker = mAnrProcessStats;
        synchronized (processCpuTracker) {
            printCurrentState = processCpuTracker.printCurrentState(SystemClock.uptimeMillis());
        }
        return printCurrentState;
    }

    public String getAndroidTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        Date date = new Date(System.currentTimeMillis());
        Formatter formatter = new Formatter();
        return "Android time :[" + simpleDateFormat.format(date) + "] [" + formatter.format("%.3f", Float.valueOf(((float) SystemClock.uptimeMillis()) / 1000.0f)) + "]\n";
    }

    public File createFile(String filepath) {
        File file = new File(filepath);
        if (file.exists()) {
            return file;
        }
        Slog.i(TAG, filepath + " isn't exist");
        return null;
    }

    public boolean copyFile(File srcFile, File destFile) {
        try {
            if (!srcFile.exists()) {
                return false;
            }
            if (!destFile.exists()) {
                destFile.createNewFile();
                FileUtils.setPermissions(destFile.getPath(), 438, -1, -1);
            }
            InputStream in = new FileInputStream(srcFile);
            boolean result = copyToFile(in, destFile);
            in.close();
            return result;
        } catch (IOException e) {
            Slog.e(TAG, "createFile fail");
            return false;
        }
    }

    public boolean copyToFile(InputStream inputStream, File destFile) {
        FileOutputStream out = null;
        try {
            try {
                out = new FileOutputStream(destFile, true);
                byte[] buffer = new byte[4096];
                while (true) {
                    int bytesRead = inputStream.read(buffer);
                    if (bytesRead < 0) {
                        break;
                    }
                    out.write(buffer, 0, bytesRead);
                }
                out.flush();
                out.getFD().sync();
                try {
                    out.close();
                } catch (IOException e) {
                    Slog.w(TAG, "close failed..");
                }
                return true;
            } catch (Throwable th) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e2) {
                        Slog.w(TAG, "close failed..");
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            Slog.w(TAG, "copyToFile fail", e3);
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e4) {
                    Slog.w(TAG, "close failed..");
                }
            }
            return false;
        }
    }

    public void stringToFile(String filename, String string) throws IOException {
        FileWriter out = new FileWriter(filename, true);
        try {
            out.write(string);
        } finally {
            out.close();
        }
    }

    /* loaded from: classes.dex */
    public class BinderDumpThread extends Thread {
        private int mPid;

        public BinderDumpThread(int pid) {
            this.mPid = pid;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AnrManagerService.this.dumpBinderInfo(this.mPid);
        }
    }

    public void dumpBinderInfo(int pid) {
        try {
            File binderinfo = new File("/data/anr/binderinfo");
            if (binderinfo.exists()) {
                if (!binderinfo.delete()) {
                    Slog.e(TAG, "dumpBinderInfo fail due to file likely to be locked by others");
                    return;
                } else if (!binderinfo.createNewFile()) {
                    Slog.e(TAG, "dumpBinderInfo fail due to file cannot be created");
                    return;
                } else {
                    FileUtils.setPermissions(binderinfo.getPath(), 438, -1, -1);
                }
            }
            File file = createFile("/dev/binderfs/binder_logs/failed_transaction_log");
            if (file != null) {
                stringToFile("/data/anr/binderinfo", "------ BINDER FAILED TRANSACTION LOG ------\n");
                copyFile(file, binderinfo);
            }
            File file2 = createFile("/dev/binderfs/binder_logs/timeout_log");
            if (file2 != null) {
                stringToFile("/data/anr/binderinfo", "------ BINDER TIMEOUT LOG ------\n");
                copyFile(file2, binderinfo);
            }
            File file3 = createFile("/dev/binderfs/binder_logs/transaction_log");
            if (file3 != null) {
                stringToFile("/data/anr/binderinfo", "------ BINDER TRANSACTION LOG ------\n");
                copyFile(file3, binderinfo);
            }
            File file4 = createFile("/dev/binderfs/binder_logs/transactions");
            if (file4 != null) {
                stringToFile("/data/anr/binderinfo", "------ BINDER TRANSACTIONS ------\n");
                copyFile(file4, binderinfo);
            }
            File file5 = createFile("/dev/binderfs/binder_logs/stats");
            if (file5 != null) {
                stringToFile("/data/anr/binderinfo", "------ BINDER STATS ------\n");
                copyFile(file5, binderinfo);
            }
            String filepath = "/dev/binderfs/binder_logs/proc/" + Integer.toString(pid);
            File file6 = new File(filepath);
            stringToFile("/data/anr/binderinfo", "------ BINDER PROCESS STATE: $i ------\n");
            copyFile(file6, binderinfo);
        } catch (IOException e) {
            Slog.e(TAG, "dumpBinderInfo fail");
        }
    }

    public void enableTraceLog(boolean enable) {
        Slog.i(TAG, "enableTraceLog: " + enable);
        ExceptionLog exceptionLog = this.exceptionLog;
        if (exceptionLog != null) {
            exceptionLog.switchFtrace(enable ? 1 : 0);
        }
    }

    private void writeStringToFile(String filepath, String string) {
        StringBuilder sb;
        if (filepath != null) {
            File file = new File(filepath);
            FileOutputStream out = null;
            StrictMode.ThreadPolicy oldPolicy = StrictMode.allowThreadDiskReads();
            StrictMode.allowThreadDiskWrites();
            try {
                try {
                    out = new FileOutputStream(file);
                    out.write(string.getBytes());
                    out.flush();
                } catch (IOException e) {
                    Slog.e(TAG, "writeStringToFile error: " + filepath + " " + e.toString());
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e2) {
                            ioe = e2;
                            sb = new StringBuilder();
                            sb.append("writeStringToFile close error: ");
                            sb.append(filepath);
                            sb.append(" ");
                            sb.append(ioe.toString());
                            Slog.e(TAG, sb.toString());
                            StrictMode.setThreadPolicy(oldPolicy);
                        }
                    }
                }
                try {
                    out.close();
                } catch (IOException e3) {
                    ioe = e3;
                    sb = new StringBuilder();
                    sb.append("writeStringToFile close error: ");
                    sb.append(filepath);
                    sb.append(" ");
                    sb.append(ioe.toString());
                    Slog.e(TAG, sb.toString());
                    StrictMode.setThreadPolicy(oldPolicy);
                }
                StrictMode.setThreadPolicy(oldPolicy);
            } catch (Throwable th) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ioe) {
                        Slog.e(TAG, "writeStringToFile close error: " + filepath + " " + ioe.toString());
                    }
                }
                StrictMode.setThreadPolicy(oldPolicy);
                throw th;
            }
        }
    }

    private boolean isBuiltinApp(ApplicationInfo appInfo) {
        return ((appInfo.flags & 1) == 0 && (appInfo.flags & 128) == 0) ? false : true;
    }

    private boolean needAnrDump(ApplicationInfo appInfo) {
        return isBuiltinApp(appInfo) || SystemProperties.getInt("persist.vendor.anr.dumpthr", 1) != 0;
    }
}
