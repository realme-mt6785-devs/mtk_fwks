package com.android.internal.os;

import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.ApplicationErrorReport;
import android.app.IActivityManager;
import android.ddm.DdmRegister;
import android.os.Build;
import android.os.DeadObjectException;
import android.os.Debug;
import android.os.IBinder;
import android.os.Process;
import android.os.Trace;
import android.system.Os;
import android.util.Log;
import android.util.Slog;
import com.android.internal.logging.AndroidConfig;
import com.android.server.NetworkManagementSocketTagger;
import dalvik.system.RuntimeHooks;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.logging.LogManager;
import libcore.content.type.MimeMap;
/* loaded from: classes4.dex */
public class RuntimeInit {
    static final boolean DEBUG = false;
    static final String TAG = "AndroidRuntime";
    private static boolean initialized;
    private static IBinder mApplicationObject;
    private static volatile boolean mCrashing = false;
    public static IRuntimeInitExt mRuntimeInitExt = RuntimeInitExtPlugin.constructor.newInstance();
    private static volatile ApplicationWtfHandler sDefaultApplicationWtfHandler;

    /* loaded from: classes4.dex */
    public interface ApplicationWtfHandler {
        boolean handleApplicationWtf(IBinder iBinder, String str, boolean z, ApplicationErrorReport.ParcelableCrashInfo parcelableCrashInfo, int i);
    }

    private static final native void nativeFinishInit();

    private static final native void nativeSetExitWithoutCleanup(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static int Clog_e(String tag, String msg, Throwable tr) {
        return Log.printlns(4, 6, tag, msg, tr);
    }

    public static void logUncaught(String threadName, String processName, int pid, Throwable e) {
        StringBuilder message = new StringBuilder();
        message.append("FATAL EXCEPTION: ");
        message.append(threadName);
        message.append("\n");
        if (processName != null) {
            message.append("Process: ");
            message.append(processName);
            message.append(", ");
        }
        message.append("PID: ");
        message.append(pid);
        Clog_e(TAG, message.toString(), e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class LoggingHandler implements Thread.UncaughtExceptionHandler {
        public volatile boolean mTriggered;

        private LoggingHandler() {
            this.mTriggered = false;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread t, Throwable e) {
            this.mTriggered = true;
            if (!RuntimeInit.mCrashing) {
                if (RuntimeInit.mApplicationObject == null && 1000 == Process.myUid()) {
                    RuntimeInit.Clog_e(RuntimeInit.TAG, "*** FATAL EXCEPTION IN SYSTEM PROCESS: " + t.getName(), e);
                    String FILE_NAME = Os.getpid() + ".hprof";
                    ApplicationErrorReport.CrashInfo mCrashInfo = new ApplicationErrorReport.CrashInfo(e);
                    if ("java.lang.OutOfMemoryError".equals(mCrashInfo.exceptionClassName) && Build.IS_DEBUGGABLE) {
                        String absolutePath = new File("/data/anr", FILE_NAME).getAbsolutePath();
                        try {
                            Debug.dumpHprofData(absolutePath);
                        } catch (IOException err) {
                            err.printStackTrace();
                        } catch (RuntimeException err2) {
                            err2.printStackTrace();
                        }
                        RuntimeInit.Clog_e(RuntimeInit.TAG, "OutOfMemoryError IN SYSTEM PROCESS: Already dump hprof!", e);
                    }
                } else {
                    RuntimeInit.logUncaught(t.getName(), ActivityThread.currentProcessName(), Process.myPid(), e);
                }
                RuntimeInit.mRuntimeInitExt.uncaughtExceptionExt(t, e, new KillApplicationHandler(this));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class KillApplicationHandler implements Thread.UncaughtExceptionHandler {
        private final LoggingHandler mLoggingHandler;

        public KillApplicationHandler(LoggingHandler loggingHandler) {
            Objects.requireNonNull(loggingHandler);
            this.mLoggingHandler = loggingHandler;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread t, Throwable e) {
            try {
                ensureLogging(t, e);
            } catch (Throwable t2) {
                try {
                    if (!(t2 instanceof DeadObjectException)) {
                        try {
                            RuntimeInit.Clog_e(RuntimeInit.TAG, "Error reporting crash", t2);
                        } catch (Throwable th) {
                        }
                    }
                } finally {
                    Process.killProcess(Process.myPid());
                    System.exit(10);
                }
            }
            if (!RuntimeInit.mCrashing) {
                boolean unused = RuntimeInit.mCrashing = true;
                if (ActivityThread.currentActivityThread() != null) {
                    ActivityThread.currentActivityThread().stopProfiling();
                }
                ActivityManager.getService().handleApplicationCrash(RuntimeInit.mApplicationObject, new ApplicationErrorReport.ParcelableCrashInfo(e));
            }
        }

        private void ensureLogging(Thread t, Throwable e) {
            if (!this.mLoggingHandler.mTriggered) {
                try {
                    this.mLoggingHandler.uncaughtException(t, e);
                } catch (Throwable th) {
                }
            }
        }
    }

    public static void preForkInit() {
        RuntimeInitExtPlugin.preload.call(ZygoteInit.class.getClassLoader());
        enableDdms();
        MimeMap.setDefaultSupplier(RuntimeInit$$ExternalSyntheticLambda0.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final void commonInit() {
        LoggingHandler loggingHandler = new LoggingHandler();
        RuntimeHooks.setUncaughtExceptionPreHandler(loggingHandler);
        Thread.setDefaultUncaughtExceptionHandler(new KillApplicationHandler(loggingHandler));
        RuntimeHooks.setTimeZoneIdSupplier(RuntimeInit$$ExternalSyntheticLambda1.INSTANCE);
        LogManager.getLogManager().reset();
        new AndroidConfig();
        String userAgent = getDefaultUserAgent();
        System.setProperty("http.agent", userAgent);
        NetworkManagementSocketTagger.install();
        initialized = true;
    }

    private static String getDefaultUserAgent() {
        StringBuilder result = new StringBuilder(64);
        result.append("Dalvik/");
        result.append(System.getProperty("java.vm.version"));
        result.append(" (Linux; U; Android ");
        String version = Build.VERSION.RELEASE_OR_CODENAME;
        result.append(version.length() > 0 ? version : "1.0");
        if ("REL".equals(Build.VERSION.CODENAME)) {
            String model = Build.MODEL;
            if (model.length() > 0) {
                result.append("; ");
                result.append(model);
            }
        }
        String model2 = Build.ID;
        if (model2.length() > 0) {
            result.append(" Build/");
            result.append(model2);
        }
        result.append(")");
        return result.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Runnable findStaticMain(String className, String[] argv, ClassLoader classLoader) {
        try {
            Class<?> cl = Class.forName(className, true, classLoader);
            try {
                Method m = cl.getMethod("main", String[].class);
                int modifiers = m.getModifiers();
                if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                    return new MethodAndArgsCaller(m, argv);
                }
                throw new RuntimeException("Main method is not public and static on " + className);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException("Missing static main on " + className, ex);
            } catch (SecurityException ex2) {
                throw new RuntimeException("Problem getting static main on " + className, ex2);
            }
        } catch (ClassNotFoundException ex3) {
            throw new RuntimeException("Missing class when invoking static main " + className, ex3);
        }
    }

    public static final void main(String[] argv) {
        preForkInit();
        if (argv.length == 2 && argv[1].equals("application")) {
            redirectLogStreams();
        }
        commonInit();
        nativeFinishInit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Runnable applicationInit(int targetSdkVersion, long[] disabledCompatChanges, String[] argv, ClassLoader classLoader) {
        nativeSetExitWithoutCleanup(true);
        VMRuntime.getRuntime().setTargetSdkVersion(targetSdkVersion);
        VMRuntime.getRuntime().setDisabledCompatChanges(disabledCompatChanges);
        Arguments args = new Arguments(argv);
        Trace.traceEnd(64L);
        return findStaticMain(args.startClass, args.startArgs, classLoader);
    }

    public static void redirectLogStreams() {
        System.out.close();
        System.setOut(new AndroidPrintStream(4, "System.out"));
        System.err.close();
        System.setErr(new AndroidPrintStream(5, "System.err"));
    }

    public static void wtf(String tag, Throwable t, boolean system2) {
        boolean exit = false;
        try {
            IActivityManager am = ActivityManager.getService();
            if (am != null) {
                exit = am.handleApplicationWtf(mApplicationObject, tag, system2, new ApplicationErrorReport.ParcelableCrashInfo(t), Process.myPid());
            } else {
                ApplicationWtfHandler handler = sDefaultApplicationWtfHandler;
                if (handler != null) {
                    exit = handler.handleApplicationWtf(mApplicationObject, tag, system2, new ApplicationErrorReport.ParcelableCrashInfo(t), Process.myPid());
                } else {
                    Slog.e(TAG, "Original WTF:", t);
                }
            }
            if (exit) {
                Process.killProcess(Process.myPid());
                System.exit(10);
            }
        } catch (Throwable t2) {
            if (!(t2 instanceof DeadObjectException)) {
                Slog.e(TAG, "Error reporting WTF", t2);
                Slog.e(TAG, "Original WTF:", t);
            }
        }
    }

    public static void setDefaultApplicationWtfHandler(ApplicationWtfHandler handler) {
        sDefaultApplicationWtfHandler = handler;
    }

    public static final void setApplicationObject(IBinder app) {
        mApplicationObject = app;
    }

    public static final IBinder getApplicationObject() {
        return mApplicationObject;
    }

    private static void enableDdms() {
        DdmRegister.registerHandlers();
    }

    /* loaded from: classes4.dex */
    static class Arguments {
        String[] startArgs;
        String startClass;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Arguments(String[] args) throws IllegalArgumentException {
            parseArgs(args);
        }

        private void parseArgs(String[] args) throws IllegalArgumentException {
            int curArg = 0;
            while (true) {
                if (curArg >= args.length) {
                    break;
                }
                String arg = args[curArg];
                if (arg.equals("--")) {
                    curArg++;
                    break;
                } else if (!arg.startsWith("--")) {
                    break;
                } else {
                    curArg++;
                }
            }
            if (curArg != args.length) {
                int curArg2 = curArg + 1;
                this.startClass = args[curArg];
                String[] strArr = new String[args.length - curArg2];
                this.startArgs = strArr;
                System.arraycopy(args, curArg2, strArr, 0, strArr.length);
                return;
            }
            throw new IllegalArgumentException("Missing classname argument to RuntimeInit!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class MethodAndArgsCaller implements Runnable {
        private final String[] mArgs;
        private final Method mMethod;

        public MethodAndArgsCaller(Method method, String[] args) {
            this.mMethod = method;
            this.mArgs = args;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.mMethod.invoke(null, this.mArgs);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex2) {
                Throwable cause = ex2.getCause();
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                } else if (cause instanceof Error) {
                    throw ((Error) cause);
                } else {
                    throw new RuntimeException(ex2);
                }
            }
        }
    }
}
