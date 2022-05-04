package com.mediatek.aee;

import android.os.Process;
import android.os.SystemProperties;
import android.util.Log;
import com.mediatek.dx.DexOptExtFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class ExceptionLogImpl extends ExceptionLog {
    public static final byte AEE_EXCEPTION_JNI = 1;
    public static final byte AEE_WARNING_JNI = 0;
    public static final String TAG = "AES";
    private static int[] mZygotePids = null;
    private final String SEND_NON_PROTECTED_BROADCAST = "Sending non-protected broadcast";
    private final String[] protectedBroadcastFilter = {"android.intent.action.CALL_EMERGENCY", "com.debug.loggerui.ADB_CMD", "com.mediatek.log2server.EXCEPTION_HAPPEND", "com.mediatek.omacp.capability.result", "com.mediatek.autounlock", "com.mtk.autotest.heartset.stop", "com.mtk.fts.ACTION", "com.android.systemui.demo", "ATG_MQTT_MqttService.pingSender", "AUTO_SUBMIT_STATUS"};
    private final String[] FalseAlarmCases = {"Process: system_server", "Subject: LazyAlarmStore", "TerribleFailure: Removed TIME_TICK alarm", "android.util.Log.wtf", "android.util.Slog.wtf", "com.android.server.alarm.LazyAlarmStore.remove", "=====case end=====", "Process: system_server", "Subject: ActivityManager", "TerribleFailure: Background started FGS", "=====case end====="};
    private final String FILE_OBSERVER_NULL_PATH = "Unhandled exception in FileObserver com.android.server.BootReceiver";

    private static native long SFMatter(long j, long j2);

    private static native void WDTMatter(long j);

    private static native boolean getNativeExceptionPidListImpl(int[] iArr);

    private static native void report(String str, String str2, String str3, String str4, String str5, long j);

    private static native void switchFtraceImpl(int i);

    private static native void systemreportImpl(byte b, String str, String str2, String str3, String str4);

    static {
        Log.i(TAG, "load Exception Log jni");
        System.loadLibrary("mediatek_exceptionlog");
    }

    public void handle(String type, String info, String pid) {
        long lpid;
        boolean z;
        Log.w(TAG, "Exception Log handling...");
        if (!type.startsWith("data_app") || info.contains("com.android.development") || SystemProperties.getInt("persist.vendor.mtk.aee.filter", 1) != 1) {
            String pkgs = "";
            String detail = "";
            long lpid2 = 0;
            String[] splitInfo = info.split("\n+");
            Pattern procMatcher = Pattern.compile("^Process:\\s+(.*)");
            Pattern pkgMatcher = Pattern.compile("^Package:\\s+(.*)");
            int length = splitInfo.length;
            int i = 0;
            String proc = "";
            while (i < length) {
                String s = splitInfo[i];
                Matcher m = procMatcher.matcher(s);
                if (m.matches()) {
                    lpid = lpid2;
                    proc = m.group(1);
                } else {
                    lpid = lpid2;
                }
                Matcher m2 = pkgMatcher.matcher(s);
                if (m2.matches()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(pkgs);
                    z = true;
                    String pkgs2 = m2.group(1);
                    sb.append(pkgs2);
                    sb.append("\n");
                    pkgs = sb.toString();
                } else {
                    z = true;
                }
                i++;
                detail = detail;
                lpid2 = lpid;
            }
            long lpid3 = lpid2;
            if (!pid.equals("")) {
                lpid3 = Long.parseLong(pid);
            }
            if (type.equals("system_server_wtf") && isSkipSystemWtfReport(info)) {
                return;
            }
            report(proc, pkgs, info, "Backtrace of all threads:\n\n", type, lpid3);
            return;
        }
        Log.w(TAG, "Skipped - do not care third party apk");
    }

    public void systemreport(byte Type, String Module, String Msg, String Path) {
        String Backtrace = getThreadStackTrace();
        systemreportImpl(Type, Module, Backtrace, Msg, Path);
    }

    public boolean getNativeExceptionPidList(int[] pidList) {
        return getNativeExceptionPidListImpl(pidList);
    }

    public void switchFtrace(int config) {
        if (config == 3) {
            DexOptExtFactory.getInstance().makeDexOpExt().notifySpeedUp();
        }
        switchFtraceImpl(config);
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
        Log.w(TAG, "pid: " + pid + " is not a Java process");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011e A[Catch: IOException -> 0x00fd, TRY_ENTER, TRY_LEAVE, TryCatch #10 {IOException -> 0x00fd, blocks: (B:45:0x00f9, B:60:0x011e, B:66:0x012f), top: B:78:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x012f A[Catch: IOException -> 0x00fd, TRY_ENTER, TRY_LEAVE, TryCatch #10 {IOException -> 0x00fd, blocks: (B:45:0x00f9, B:60:0x011e, B:66:0x012f), top: B:78:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void readTransactionInfoFromFile(int r17, java.util.ArrayList<java.lang.Integer> r18) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.aee.ExceptionLogImpl.readTransactionInfoFromFile(int, java.util.ArrayList):void");
    }

    private static String getThreadStackTrace() {
        Writer traces = new StringWriter();
        try {
            Thread th = Thread.currentThread();
            StackTraceElement[] st = th.getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(th.getName());
            sb.append("\" ");
            sb.append(th.isDaemon() ? "daemon" : "");
            sb.append(" prio=");
            sb.append(th.getPriority());
            sb.append(" Thread id=");
            sb.append(th.getId());
            sb.append(" ");
            sb.append(th.getState());
            sb.append("\n");
            traces.write(sb.toString());
            for (StackTraceElement line : st) {
                traces.write("\t" + line + "\n");
            }
            traces.write("\n");
            String ret_trace = traces.toString();
            return ret_trace;
        } catch (IOException e) {
            return "IOException";
        } catch (OutOfMemoryError e2) {
            return "java.lang.OutOfMemoryError";
        }
    }

    private static String getAllThreadStackTraces() {
        Map<Thread, StackTraceElement[]> st = Thread.getAllStackTraces();
        Writer traces = new StringWriter();
        try {
            for (Map.Entry<Thread, StackTraceElement[]> e : st.entrySet()) {
                StackTraceElement[] el = e.getValue();
                Thread th = e.getKey();
                StringBuilder sb = new StringBuilder();
                sb.append("\"");
                sb.append(th.getName());
                sb.append("\" ");
                sb.append(th.isDaemon() ? "daemon" : "");
                sb.append(" prio=");
                sb.append(th.getPriority());
                sb.append(" Thread id=");
                sb.append(th.getId());
                sb.append(" ");
                sb.append(th.getState());
                sb.append("\n");
                traces.write(sb.toString());
                for (StackTraceElement line : el) {
                    traces.write("\t" + line + "\n");
                }
                traces.write("\n");
            }
            String ret_traces = traces.toString();
            return ret_traces;
        } catch (IOException e2) {
            return "IOException";
        } catch (OutOfMemoryError e3) {
            return "java.lang.OutOfMemoryError";
        }
    }

    public void WDTMatterJava(long lParam) {
        WDTMatter(lParam);
    }

    public long SFMatterJava(long setorget, long lParam) {
        return SFMatter(setorget, lParam);
    }

    private boolean isSkipSystemWtfReport(String info) {
        return isSkipReportFromProtectedBroadcast(info) || isSkipReportFromNullFilePath(info) || isSkipFalseAlarmCases(info);
    }

    private boolean isSkipReportFromProtectedBroadcast(String info) {
        if (!info.contains("Sending non-protected broadcast")) {
            return false;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.protectedBroadcastFilter;
            if (i >= strArr.length) {
                return false;
            }
            if (info.contains(strArr[i])) {
                return true;
            }
            i++;
        }
    }

    private boolean isSkipFalseAlarmCases(String info) {
        boolean case_match = true;
        int i = 0;
        while (true) {
            String[] strArr = this.FalseAlarmCases;
            if (i >= strArr.length) {
                return false;
            }
            if (!strArr[i].equals("=====case end=====")) {
                if (!info.contains(this.FalseAlarmCases[i])) {
                    case_match = false;
                }
            } else if (case_match) {
                return true;
            } else {
                case_match = true;
            }
            i++;
        }
    }

    private boolean isSkipReportFromNullFilePath(String info) {
        if (info.contains("Unhandled exception in FileObserver com.android.server.BootReceiver")) {
            return true;
        }
        return false;
    }
}
