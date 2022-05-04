package android.os;

import android.annotation.SystemApi;
import android.os.StrictMode;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Pair;
import android.webkit.WebViewZygote;
import dalvik.system.VMRuntime;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;
import libcore.io.IoUtils;
/* loaded from: classes2.dex */
public class Process {
    public static final int AUDIOSERVER_UID = 1041;
    public static final int BLUETOOTH_UID = 1002;
    public static final int CAMERASERVER_UID = 1047;
    public static final int CLAT_UID = 1029;
    public static final int CREDSTORE_UID = 1076;
    public static final int DNS_TETHER_UID = 1052;
    public static final int DRM_UID = 1019;
    public static final int EXTERNAL_STORAGE_GID = 1077;
    public static final int EXT_DATA_RW_GID = 1078;
    public static final int EXT_OBB_RW_GID = 1079;
    public static final int FIRST_APPLICATION_CACHE_GID = 20000;
    public static final int FIRST_APPLICATION_UID = 10000;
    public static final int FIRST_APP_ZYGOTE_ISOLATED_UID = 90000;
    public static final int FIRST_ISOLATED_UID = 99000;
    public static final int FIRST_SHARED_APPLICATION_GID = 50000;
    public static final int FSVERITY_CERT_UID = 1075;
    public static final int INCIDENTD_UID = 1067;
    public static final int INET_GID = 3003;
    public static final int INVALID_UID = -1;
    public static final int IORAPD_UID = 1071;
    public static final int KEYSTORE_UID = 1017;
    public static final int LAST_APPLICATION_CACHE_GID = 29999;
    public static final int LAST_APPLICATION_UID = 19999;
    public static final int LAST_APP_ZYGOTE_ISOLATED_UID = 98999;
    public static final int LAST_ISOLATED_UID = 99999;
    public static final int LAST_SHARED_APPLICATION_GID = 59999;
    private static final String LOG_TAG = "Process";
    public static final int LOG_UID = 1007;
    public static final int MEDIA_RW_GID = 1023;
    public static final int MEDIA_UID = 1013;
    public static final int NETWORK_STACK_UID = 1073;
    public static final int NFC_UID = 1027;
    public static final int NOBODY_UID = 9999;
    public static final int NUM_UIDS_PER_APP_ZYGOTE = 100;
    public static final int OTA_UPDATE_UID = 1061;
    public static final int PACKAGE_INFO_GID = 1032;
    public static final int PHONE_UID = 1001;
    private static final int PIDFD_SUPPORTED = 1;
    private static final int PIDFD_UNKNOWN = 0;
    private static final int PIDFD_UNSUPPORTED = 2;
    public static final int PROC_CHAR = 2048;
    public static final int PROC_COMBINE = 256;
    public static final int PROC_NEWLINE_TERM = 10;
    public static final int PROC_OUT_FLOAT = 16384;
    public static final int PROC_OUT_LONG = 8192;
    public static final int PROC_OUT_STRING = 4096;
    public static final int PROC_PARENS = 512;
    public static final int PROC_QUOTES = 1024;
    public static final int PROC_SPACE_TERM = 32;
    public static final int PROC_TAB_TERM = 9;
    public static final int PROC_TERM_MASK = 255;
    public static final int PROC_ZERO_TERM = 0;
    public static final int ROOT_UID = 0;
    public static final int SCHED_BATCH = 3;
    public static final int SCHED_FIFO = 1;
    public static final int SCHED_IDLE = 5;
    public static final int SCHED_OTHER = 0;
    public static final int SCHED_RESET_ON_FORK = 1073741824;
    public static final int SCHED_RR = 2;
    public static final int SDCARD_RW_GID = 1015;
    public static final int SE_UID = 1068;
    public static final int SHARED_RELRO_UID = 1037;
    public static final int SHARED_USER_GID = 9997;
    public static final int SHELL_UID = 2000;
    public static final int SIGNAL_KILL = 9;
    public static final int SIGNAL_QUIT = 3;
    public static final int SIGNAL_USR1 = 10;
    public static final int STATSD_UID = 1066;
    public static final int SYSTEM_UID = 1000;
    public static final int THREAD_GROUP_AUDIO_APP = 3;
    public static final int THREAD_GROUP_AUDIO_SYS = 4;
    public static final int THREAD_GROUP_BACKGROUND = 0;
    public static final int THREAD_GROUP_DEFAULT = -1;
    private static final int THREAD_GROUP_FOREGROUND = 1;
    public static final int THREAD_GROUP_RESTRICTED = 7;
    public static final int THREAD_GROUP_RT_APP = 6;
    public static final int THREAD_GROUP_SYSTEM = 2;
    public static final int THREAD_GROUP_TOP_APP = 5;
    public static final int THREAD_PRIORITY_AUDIO = -16;
    public static final int THREAD_PRIORITY_BACKGROUND = 10;
    public static final int THREAD_PRIORITY_DEFAULT = 0;
    public static final int THREAD_PRIORITY_DISPLAY = -4;
    public static final int THREAD_PRIORITY_FOREGROUND = -2;
    public static final int THREAD_PRIORITY_LESS_FAVORABLE = 1;
    public static final int THREAD_PRIORITY_LOWEST = 19;
    public static final int THREAD_PRIORITY_MORE_FAVORABLE = -1;
    public static final int THREAD_PRIORITY_TOP_APP_BOOST = -10;
    public static final int THREAD_PRIORITY_URGENT_AUDIO = -19;
    public static final int THREAD_PRIORITY_URGENT_DISPLAY = -8;
    public static final int THREAD_PRIORITY_VIDEO = -10;
    public static final int UWB_UID = 1083;
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public static final int VPN_UID = 1016;
    public static final int WEBVIEW_ZYGOTE_UID = 1053;
    public static final int WIFI_UID = 1010;
    public static final int ZYGOTE_POLICY_FLAG_BATCH_LAUNCH = 2;
    public static final int ZYGOTE_POLICY_FLAG_EMPTY = 0;
    public static final int ZYGOTE_POLICY_FLAG_LATENCY_SENSITIVE = 1;
    public static final int ZYGOTE_POLICY_FLAG_SYSTEM_PROCESS = 4;
    private static long sStartElapsedRealtime;
    private static long sStartUptimeMillis;
    private static int sPidFdSupported = 0;
    public static final ZygoteProcess ZYGOTE_PROCESS = new ZygoteProcess();

    /* loaded from: classes2.dex */
    public static final class ProcessStartResult {
        public int pid;
        public boolean usingWrapper;
    }

    public static final native int createProcessGroup(int i, int i2);

    public static final native void enableFreezer(boolean z);

    public static final native int[] getCpuClusters(int[] iArr);

    public static final native long getElapsedCpuTime();

    public static final native int[] getExclusiveCores();

    public static final native long getFreeMemory();

    public static final native int getGidForName(String str);

    public static final native int[] getPids(String str, int[] iArr);

    public static final native int[] getPidsForCommands(String[] strArr);

    public static final native int getProcessGroup(int i) throws IllegalArgumentException, SecurityException;

    public static final native int getProcessState(int i);

    public static final native long getPss(int i);

    public static final native long[] getRss(int i);

    public static final native byte[] getThreadAffinity(int i);

    public static final native int getThreadPriority(int i) throws IllegalArgumentException;

    public static final native int getThreadScheduler(int i) throws IllegalArgumentException;

    public static final native long getTotalMemory();

    public static final native int getUidForName(String str);

    public static final native int killProcessGroup(int i, int i2);

    private static native int nativePidFdOpen(int i, int i2) throws ErrnoException;

    public static final native boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, String[] strArr, long[] jArr, float[] fArr);

    public static final native boolean readProcFile(String str, int[] iArr, String[] strArr, long[] jArr, float[] fArr);

    public static final native void readProcLines(String str, String[] strArr, long[] jArr);

    public static final native void removeAllProcessGroups();

    public static final native void sendSignal(int i, int i2);

    public static final native void sendSignalQuiet(int i, int i2);

    public static final native void setArgV0(String str);

    public static final native void setCanSelfBackground(boolean z);

    public static final native int setGid(int i);

    public static final native void setProcessFrozen(int i, int i2, boolean z);

    public static final native void setProcessGroup(int i, int i2) throws IllegalArgumentException, SecurityException;

    public static final native boolean setSwappiness(int i, boolean z);

    public static final native void setThreadAffinity(int i, byte[] bArr);

    public static final native void setThreadGroup(int i, int i2) throws IllegalArgumentException, SecurityException;

    public static final native void setThreadGroupAndCpuset(int i, int i2) throws IllegalArgumentException, SecurityException;

    public static final native void setThreadPriority(int i) throws IllegalArgumentException, SecurityException;

    public static final native void setThreadPriority(int i, int i2) throws IllegalArgumentException, SecurityException;

    public static final native void setThreadScheduler(int i, int i2, int i3) throws IllegalArgumentException;

    public static final native int setUid(int i);

    public static ProcessStartResult start(String processClass, String niceName, int uid, int gid, int[] gids, int runtimeFlags, int mountExternal, int targetSdkVersion, String seInfo, String abi, String instructionSet, String appDataDir, String invokeWith, String packageName, int zygotePolicyFlags, boolean isTopApp, long[] disabledCompatChanges, Map<String, Pair<String, Long>> pkgDataInfoMap, Map<String, Pair<String, Long>> whitelistedDataInfoMap, boolean bindMountAppsData, boolean bindMountAppStorageDirs, String[] zygoteArgs) {
        return ZYGOTE_PROCESS.start(processClass, niceName, uid, gid, gids, runtimeFlags, mountExternal, targetSdkVersion, seInfo, abi, instructionSet, appDataDir, invokeWith, packageName, zygotePolicyFlags, isTopApp, disabledCompatChanges, pkgDataInfoMap, whitelistedDataInfoMap, bindMountAppsData, bindMountAppStorageDirs, zygoteArgs);
    }

    public static ProcessStartResult startWebView(String processClass, String niceName, int uid, int gid, int[] gids, int runtimeFlags, int mountExternal, int targetSdkVersion, String seInfo, String abi, String instructionSet, String appDataDir, String invokeWith, String packageName, long[] disabledCompatChanges, String[] zygoteArgs) {
        return WebViewZygote.getProcess().start(processClass, niceName, uid, gid, gids, runtimeFlags, mountExternal, targetSdkVersion, seInfo, abi, instructionSet, appDataDir, invokeWith, packageName, 0, false, disabledCompatChanges, null, null, false, false, zygoteArgs);
    }

    public static final long getStartElapsedRealtime() {
        return sStartElapsedRealtime;
    }

    public static final long getStartUptimeMillis() {
        return sStartUptimeMillis;
    }

    public static final void setStartTimes(long elapsedRealtime, long uptimeMillis) {
        sStartElapsedRealtime = elapsedRealtime;
        sStartUptimeMillis = uptimeMillis;
    }

    public static final boolean is64Bit() {
        return VMRuntime.getRuntime().is64Bit();
    }

    public static final int myPid() {
        return Os.getpid();
    }

    public static final int myPpid() {
        return Os.getppid();
    }

    public static final int myTid() {
        return Os.gettid();
    }

    public static final int myUid() {
        return Os.getuid();
    }

    public static UserHandle myUserHandle() {
        return UserHandle.of(UserHandle.getUserId(myUid()));
    }

    public static boolean isCoreUid(int uid) {
        return UserHandle.isCore(uid);
    }

    public static boolean isApplicationUid(int uid) {
        return UserHandle.isApp(uid);
    }

    public static final boolean isIsolated() {
        return isIsolated(myUid());
    }

    public static final boolean isIsolated(int uid) {
        int uid2 = UserHandle.getAppId(uid);
        return (uid2 >= 99000 && uid2 <= 99999) || (uid2 >= 90000 && uid2 <= 98999);
    }

    public static final int getUidForPid(int pid) {
        String[] procStatusLabels = {"Uid:"};
        long[] procStatusValues = {-1};
        readProcLines("/proc/" + pid + "/status", procStatusLabels, procStatusValues);
        return (int) procStatusValues[0];
    }

    public static final int getParentPid(int pid) {
        String[] procStatusLabels = {"PPid:"};
        long[] procStatusValues = {-1};
        readProcLines("/proc/" + pid + "/status", procStatusLabels, procStatusValues);
        return (int) procStatusValues[0];
    }

    public static final int getThreadGroupLeader(int tid) {
        String[] procStatusLabels = {"Tgid:"};
        long[] procStatusValues = {-1};
        readProcLines("/proc/" + tid + "/status", procStatusLabels, procStatusValues);
        return (int) procStatusValues[0];
    }

    @Deprecated
    public static final boolean supportsProcesses() {
        return true;
    }

    public static final void killProcess(int pid) {
        sendSignal(pid, 9);
    }

    public static final void killProcessQuiet(int pid) {
        sendSignalQuiet(pid, 9);
    }

    public static final boolean isThreadInProcess(int tid, int pid) {
        StrictMode.ThreadPolicy oldPolicy = StrictMode.allowThreadDiskReads();
        try {
            if (!Os.access("/proc/" + tid + "/task/" + pid, OsConstants.F_OK)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            StrictMode.setThreadPolicy(oldPolicy);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
        if (r3 != null) goto L_0x0050;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void waitForProcessDeath(int r10, int r11) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            boolean r0 = supportsPidFd()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x006d
            r3 = 0
            int r4 = nativePidFdOpen(r10, r2)     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            if (r4 < 0) goto L_0x0019
            java.io.FileDescriptor r5 = new java.io.FileDescriptor     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r5.<init>()     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r3 = r5
            r3.setInt$(r4)     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            goto L_0x001a
        L_0x0019:
            r0 = 1
        L_0x001a:
            if (r3 == 0) goto L_0x004e
            android.system.StructPollfd[] r5 = new android.system.StructPollfd[r1]     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            android.system.StructPollfd r6 = new android.system.StructPollfd     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6.<init>()     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r5[r2] = r6     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6 = r5[r2]     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6.fd = r3     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6 = r5[r2]     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            int r7 = android.system.OsConstants.POLLIN     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            short r7 = (short) r7     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6.events = r7     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6 = r5[r2]     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6.revents = r2     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r6 = r5[r2]     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r7 = 0
            r6.userData = r7     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            int r6 = android.system.Os.poll(r5, r11)     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            if (r6 <= 0) goto L_0x0045
            if (r3 == 0) goto L_0x0044
            libcore.io.IoUtils.closeQuietly(r3)
        L_0x0044:
            return
        L_0x0045:
            if (r6 == 0) goto L_0x0048
            goto L_0x004e
        L_0x0048:
            java.util.concurrent.TimeoutException r7 = new java.util.concurrent.TimeoutException     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            r7.<init>()     // Catch: all -> 0x0054, ErrnoException -> 0x0056
            throw r7     // Catch: all -> 0x0054, ErrnoException -> 0x0056
        L_0x004e:
            if (r3 == 0) goto L_0x006d
        L_0x0050:
            libcore.io.IoUtils.closeQuietly(r3)
            goto L_0x006d
        L_0x0054:
            r1 = move-exception
            goto L_0x0067
        L_0x0056:
            r4 = move-exception
            int r5 = r4.errno     // Catch: all -> 0x0054
            int r6 = android.system.OsConstants.EINTR     // Catch: all -> 0x0054
            if (r5 == r6) goto L_0x0061
            r0 = 1
            if (r3 == 0) goto L_0x006d
            goto L_0x0050
        L_0x0061:
            java.lang.InterruptedException r1 = new java.lang.InterruptedException     // Catch: all -> 0x0054
            r1.<init>()     // Catch: all -> 0x0054
            throw r1     // Catch: all -> 0x0054
        L_0x0067:
            if (r3 == 0) goto L_0x006c
            libcore.io.IoUtils.closeQuietly(r3)
        L_0x006c:
            throw r1
        L_0x006d:
            if (r0 == 0) goto L_0x0095
            if (r11 >= 0) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r1 = r2
        L_0x0073:
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = (long) r11
            long r5 = r5 + r3
        L_0x0079:
            if (r1 != 0) goto L_0x007f
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x0095
        L_0x007f:
            android.system.Os.kill(r10, r2)     // Catch: ErrnoException -> 0x0083
            goto L_0x008b
        L_0x0083:
            r7 = move-exception
            int r8 = r7.errno
            int r9 = android.system.OsConstants.ESRCH
            if (r8 != r9) goto L_0x008b
            return
        L_0x008b:
            r7 = 1
            java.lang.Thread.sleep(r7)
            long r3 = java.lang.System.currentTimeMillis()
            goto L_0x0079
        L_0x0095:
            java.util.concurrent.TimeoutException r1 = new java.util.concurrent.TimeoutException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.Process.waitForProcessDeath(int, int):void");
    }

    public static boolean supportsPidFd() {
        FileDescriptor f;
        if (sPidFdSupported == 0) {
            int fd = -1;
            try {
                try {
                    fd = nativePidFdOpen(myPid(), 0);
                    sPidFdSupported = 1;
                } catch (ErrnoException e) {
                    sPidFdSupported = e.errno != OsConstants.ENOSYS ? 1 : 2;
                    if (fd >= 0) {
                        f = new FileDescriptor();
                    }
                }
                if (fd >= 0) {
                    f = new FileDescriptor();
                    f.setInt$(fd);
                    IoUtils.closeQuietly(f);
                }
            } catch (Throwable th) {
                if (fd >= 0) {
                    FileDescriptor f2 = new FileDescriptor();
                    f2.setInt$(fd);
                    IoUtils.closeQuietly(f2);
                }
                throw th;
            }
        }
        return sPidFdSupported == 1;
    }

    public static FileDescriptor openPidFd(int pid, int flags) throws IOException {
        if (!supportsPidFd()) {
            return null;
        }
        if (flags == 0) {
            try {
                FileDescriptor pidfd = new FileDescriptor();
                pidfd.setInt$(nativePidFdOpen(pid, flags));
                return pidfd;
            } catch (ErrnoException e) {
                IOException ex = new IOException();
                ex.initCause(e);
                throw ex;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
