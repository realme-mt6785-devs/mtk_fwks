package com.mediatek.server.dx;

import android.app.AppGlobals;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManagerInternal;
import android.content.pm.dex.ArtManagerInternal;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Slog;
import com.android.server.LocalServices;
import com.android.server.pm.InstructionSets;
import com.android.server.pm.PackageManagerService;
import com.android.server.pm.dex.DexoptOptions;
import com.android.server.pm.parsing.pkg.AndroidPackage;
import com.mediatek.dx.DexOptExt;
import java.lang.Thread;
import java.util.HashSet;
/* loaded from: classes.dex */
public class DexOptExtImpl extends DexOptExt {
    private static final String BLOCK_CHECK_POINT = "performDexOpt";
    private static final int[] CMDLINE_OUT;
    private static final String COMPILERFILTER_SPEED_PROFILE = "speed-profile";
    private static final int DEX_OPT_INTERVAL_MS = 50;
    private static final int MAX_TRY_COUNTS = 4;
    private static final int MSG_BASE = 10000;
    private static final int MSG_DO_DEXOPT = 10002;
    private static final int MSG_ON_PROCESS_START = 10001;
    private static final String[] PIDS_OF_INTREST;
    private static final String PROPERTY_FEATURE_ENABLE = "pm.dexopt.aggressive_dex2oat.enable";
    private static final String PROPERTY_TRY_INTERVAL = "pm.dexopt.aggressive_dex2oat.interval";
    private static final String TAG = "DexOptExtImpl";
    private static final String THREAD_NAME_SPEEDUP = "DexoptExtSpeedup";
    private static final int TRY_DEX2OAT_INTERVAL_MS = 180000;
    private static Object lock;
    private static DexOptExtImpl sInstance;
    private static boolean sIsEnable;
    private Handler mDexoptExtHandler;
    private HandlerThread mHandlerThread;
    private Thread mRcvNotifyThread = null;
    private Thread mCurDex2oatThread = null;
    private PackageManagerService mPm = null;
    private int mTryDex2oatInterval = TRY_DEX2OAT_INTERVAL_MS;
    private long mLastDex2oatTime = 0;
    private long mLastKillDex2oatTime = 0;
    private HashSet<String> mMointorPkgs = new HashSet<>();
    private Object mMointorPkgsLock = new Object();

    static {
        sIsEnable = SystemProperties.getBoolean(PROPERTY_FEATURE_ENABLE, false) && !Build.IS_ENG;
        CMDLINE_OUT = new int[]{4096};
        sInstance = null;
        lock = new Object();
        PIDS_OF_INTREST = new String[]{"/system/bin/installd", "/apex/com.android.art/bin/dex2oat32", "/apex/com.android.art/bin/dex2oat64"};
    }

    private DexOptExtImpl() {
        setTryDex2oatInterval(SystemProperties.getInt(PROPERTY_TRY_INTERVAL, (int) TRY_DEX2OAT_INTERVAL_MS));
        if (isDexOptExtEnable()) {
            initHandlerAndStartHandlerThread();
        }
    }

    private void initHandlerAndStartHandlerThread() {
        HandlerThread handlerThread = new HandlerThread("DexOptExt");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mDexoptExtHandler = new Handler(this.mHandlerThread.getLooper(), new DexOptExtHandler());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class DexOptExtHandler implements Handler.Callback {
        DexOptExtHandler() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            String pkg = (String) msg.obj;
            switch (msg.what) {
                case DexOptExtImpl.MSG_ON_PROCESS_START /* 10001 */:
                    DexOptExtImpl.this.handleProcessStart(pkg);
                    return true;
                case DexOptExtImpl.MSG_DO_DEXOPT /* 10002 */:
                    DexOptExtImpl.this.handleDoDexopt(msg);
                    return true;
                default:
                    return true;
            }
        }
    }

    public void setTryDex2oatInterval(int durationMillionSeconds) {
        if (durationMillionSeconds >= 0) {
            this.mTryDex2oatInterval = durationMillionSeconds;
        }
    }

    public static DexOptExtImpl getInstance() {
        if (sInstance == null) {
            synchronized (lock) {
                if (sInstance == null) {
                    sInstance = new DexOptExtImpl();
                }
            }
        }
        return sInstance;
    }

    public void onStartProcess(String hostingType, String pkg) {
        if (shouldSendProcessStartMessage(hostingType, pkg)) {
            synchronized (this) {
                if (!isInMonitorList(pkg)) {
                    Message msg = Message.obtain();
                    msg.what = MSG_ON_PROCESS_START;
                    msg.obj = pkg;
                    this.mDexoptExtHandler.sendMessage(msg);
                }
            }
        }
    }

    private boolean shouldSendProcessStartMessage(String hostingType, String pkg) {
        if (!isDexOptExtEnable() || hostingType == null || !hostingType.contains("activity") || getPackageManager() == null) {
            return false;
        }
        String bootcomplete = SystemProperties.get("dev.bootcomplete");
        return bootcomplete.equals("1");
    }

    private PackageManagerService getPackageManager() {
        PackageManagerService packageManagerService = this.mPm;
        if (packageManagerService != null) {
            return packageManagerService;
        }
        PackageManagerService packageManager = AppGlobals.getPackageManager();
        this.mPm = packageManager;
        return packageManager;
    }

    private void checkAndWait() {
        long duration;
        long now = SystemClock.uptimeMillis();
        long j = this.mLastKillDex2oatTime;
        if (j != 0) {
            duration = 610000 - (now - j);
            Slog.d(TAG, "last killed at:" + this.mLastKillDex2oatTime + " now:" + now + " duration:" + duration);
            this.mLastKillDex2oatTime = 0L;
        } else {
            duration = 50 - (now - this.mLastDex2oatTime);
        }
        if (duration > 0) {
            SystemClock.sleep(duration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDoDexopt(Message msg) {
        String pkg = (String) msg.obj;
        checkAndWait();
        int result = this.mPm.performDexOptWithStatusByOption(new DexoptOptions(pkg, (int) MSG_BASE, COMPILERFILTER_SPEED_PROFILE, (String) null, 5));
        if (this.mCurDex2oatThread == this.mHandlerThread) {
            this.mCurDex2oatThread = null;
        }
        this.mLastDex2oatTime = SystemClock.uptimeMillis();
        Slog.d(TAG, "try dex2oat for " + pkg + " result=" + result + " cnt = " + msg.arg1);
        if (result != 0 || msg.arg1 >= MAX_TRY_COUNTS) {
            removeMonitorPkg(pkg);
            return;
        }
        Message againMsg = Message.obtain(msg);
        againMsg.arg1++;
        this.mDexoptExtHandler.sendMessageDelayed(againMsg, this.mTryDex2oatInterval / 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProcessStart(String pkg) {
        if (isDexoptReasonInstall(pkg)) {
            addPkgToMonitor(pkg);
            Message msg = Message.obtain();
            msg.what = MSG_DO_DEXOPT;
            msg.obj = pkg;
            msg.arg1 = 0;
            this.mDexoptExtHandler.sendMessageDelayed(msg, this.mTryDex2oatInterval);
        }
    }

    private boolean isDexoptReasonInstall(String pkg) {
        ApplicationInfo appInfo;
        AndroidPackage androidPkg = ((PackageManagerInternal) LocalServices.getService(PackageManagerInternal.class)).getPackage(pkg);
        if (androidPkg == null || (appInfo = androidPkg.toAppInfoWithoutState()) == null) {
            return false;
        }
        String abi = appInfo.primaryCpuAbi;
        if (abi == null) {
            abi = Build.SUPPORTED_ABIS[0];
        }
        ArtManagerInternal artManager = (ArtManagerInternal) LocalServices.getService(ArtManagerInternal.class);
        int reason = artManager.getPackageOptimizationInfo(appInfo, abi, "fakeactivity").getCompilationReason();
        Slog.d(TAG, pkg + " reason is " + reason + " abi is " + abi);
        switch (reason) {
            case MAX_TRY_COUNTS /* 4 */:
            case 9:
                return true;
            default:
                return false;
        }
    }

    private String getFirstCodeIsa(ApplicationInfo info) {
        String[] instructionSets = InstructionSets.getAppDexInstructionSets(info.primaryCpuAbi, info.secondaryCpuAbi);
        String[] dexCodeInstructionSets = InstructionSets.getDexCodeInstructionSets(instructionSets);
        if (dexCodeInstructionSets.length <= 0) {
            return null;
        }
        String isa = dexCodeInstructionSets[0];
        return isa;
    }

    private void addPkgToMonitor(String pkg) {
        synchronized (this.mMointorPkgsLock) {
            this.mMointorPkgs.add(pkg);
        }
    }

    private boolean isInMonitorList(String pkg) {
        boolean result;
        synchronized (this.mMointorPkgsLock) {
            result = this.mMointorPkgs.contains(pkg);
        }
        return result;
    }

    private void removeMonitorPkg(String pkg) {
        synchronized (this.mMointorPkgsLock) {
            this.mMointorPkgs.remove(pkg);
        }
    }

    public boolean isDexOptExtEnable() {
        return sIsEnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBlockedInDexopt() {
        Thread thread = this.mCurDex2oatThread;
        if (thread == null || thread != this.mHandlerThread) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String readCmdlineFromProcfs(int pid) {
        String[] cmdline = new String[1];
        if (!Process.readProcFile("/proc/" + pid + "/cmdline", CMDLINE_OUT, cmdline, null, null)) {
            return "";
        }
        return cmdline[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void killIfIsDex2oat(int pid, String cmdline, int installPid) {
        if (pid != installPid) {
            int ppid = Process.getParentPid(pid);
            if (ppid == installPid) {
                Slog.d(TAG, "kill dex2oat,pid " + pid + " cmdline is " + cmdline);
                this.mLastKillDex2oatTime = SystemClock.uptimeMillis();
                Process.killProcess(pid);
            }
        }
    }

    private void tryToStopDex2oat() {
        Thread thread = this.mRcvNotifyThread;
        if (thread == null || thread.getState() == Thread.State.TERMINATED) {
            Thread thread2 = new Thread(new Runnable() { // from class: com.mediatek.server.dx.DexOptExtImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DexOptExtImpl.this.isBlockedInDexopt()) {
                        int[] pids = Process.getPidsForCommands(DexOptExtImpl.PIDS_OF_INTREST);
                        String[] cmdlines = new String[pids.length];
                        int install_idx = -1;
                        for (int i = 0; i < pids.length; i++) {
                            cmdlines[i] = DexOptExtImpl.readCmdlineFromProcfs(pids[i]);
                            if (cmdlines[i].equals(DexOptExtImpl.PIDS_OF_INTREST[0])) {
                                install_idx = i;
                            }
                        }
                        if (install_idx != -1) {
                            for (int i2 = 0; i2 < pids.length; i2++) {
                                if (i2 != install_idx) {
                                    DexOptExtImpl.this.killIfIsDex2oat(pids[i2], cmdlines[i2], pids[install_idx]);
                                }
                            }
                        }
                    }
                }
            });
            this.mRcvNotifyThread = thread2;
            thread2.setPriority(10);
            this.mRcvNotifyThread.setName(THREAD_NAME_SPEEDUP);
            this.mRcvNotifyThread.start();
        }
    }

    public void notifySpeedUp() {
        if (!isBlockedInDexopt()) {
            Slog.d(TAG, "receive speed up notify,do nothing!");
            return;
        }
        Slog.d(TAG, "we are doing dex2oat, stop it now");
        tryToStopDex2oat();
    }

    public void notifyBeginDexopt(String pkg) {
        this.mCurDex2oatThread = Thread.currentThread();
    }
}
