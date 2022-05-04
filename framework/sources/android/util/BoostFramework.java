package android.util;

import android.content.Context;
import android.os.SystemProperties;
import android.view.Surface;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
/* loaded from: classes3.dex */
public class BoostFramework {
    public static final int MPCTLV3_GPU_IS_APP_BG = 1115832320;
    public static final int MPCTLV3_GPU_IS_APP_FG = 1115815936;
    private static final String PERFORMANCE_CLASS = "com.qualcomm.qti.Performance";
    private static final String PERFORMANCE_JAR = "/system/framework/QPerformance.jar";
    private static final String TAG = "BoostFramework";
    public static final int UXE_EVENT_BINDAPP = 2;
    public static final int UXE_EVENT_DISPLAYED_ACT = 3;
    public static final int UXE_EVENT_GAME = 5;
    public static final int UXE_EVENT_KILL = 4;
    public static final int UXE_EVENT_PKG_INSTALL = 8;
    public static final int UXE_EVENT_PKG_UNINSTALL = 7;
    public static final int UXE_EVENT_SUB_LAUNCH = 6;
    public static final int UXE_TRIGGER = 1;
    private static final String UXPERFORMANCE_CLASS = "com.qualcomm.qti.UxPerformance";
    private static final String UXPERFORMANCE_JAR = "/system/framework/UxPerformance.jar";
    public static final int VENDOR_FEEDBACK_LAUNCH_END_POINT = 5634;
    public static final int VENDOR_FEEDBACK_WORKLOAD_TYPE = 5633;
    public static final int VENDOR_HINT_ACTIVITY_BOOST = 4228;
    public static final int VENDOR_HINT_ANIM_BOOST = 4227;
    public static final int VENDOR_HINT_APP_UPDATE = 4242;
    public static final int VENDOR_HINT_BOOST_RENDERTHREAD = 4246;
    public static final int VENDOR_HINT_DRAG_BOOST = 4231;
    public static final int VENDOR_HINT_FIRST_DRAW = 4162;
    public static final int VENDOR_HINT_FIRST_LAUNCH_BOOST = 4225;
    public static final int VENDOR_HINT_KILL = 4243;
    public static final int VENDOR_HINT_MTP_BOOST = 4230;
    public static final int VENDOR_HINT_PACKAGE_INSTALL_BOOST = 4232;
    public static final int VENDOR_HINT_PERFORMANCE_MODE = 4241;
    public static final int VENDOR_HINT_ROTATION_ANIM_BOOST = 4240;
    public static final int VENDOR_HINT_ROTATION_LATENCY_BOOST = 4233;
    public static final int VENDOR_HINT_SCROLL_BOOST = 4224;
    public static final int VENDOR_HINT_SUBSEQ_LAUNCH_BOOST = 4226;
    public static final int VENDOR_HINT_TAP_EVENT = 4163;
    public static final int VENDOR_HINT_TOUCH_BOOST = 4229;
    private Object mPerf;
    private Object mUxPerf;
    private static boolean sIsLoaded = false;
    private static Class<?> sPerfClass = null;
    private static Method sAcquireFunc = null;
    private static Method sPerfHintFunc = null;
    private static Method sReleaseFunc = null;
    private static Method sReleaseHandlerFunc = null;
    private static Method sFeedbackFunc = null;
    private static Method sPerfGetPropFunc = null;
    private static Method sAcqAndReleaseFunc = null;
    private static Method sIOPStart = null;
    private static Method sIOPStop = null;
    private static Method sUXEngineEvents = null;
    private static Method sUXEngineTrigger = null;
    private static boolean sUxIsLoaded = false;
    private static Class<?> sUxPerfClass = null;
    private static Method sUxIOPStart = null;

    /* loaded from: classes3.dex */
    public class Scroll {
        public static final int HORIZONTAL = 2;
        public static final int PANEL_VIEW = 3;
        public static final int PREFILING = 4;
        public static final int VERTICAL = 1;

        public Scroll() {
        }
    }

    /* loaded from: classes3.dex */
    public class Launch {
        public static final int BOOST_GAME = 4;
        public static final int BOOST_V1 = 1;
        public static final int BOOST_V2 = 2;
        public static final int BOOST_V3 = 3;
        public static final int RESERVED_1 = 5;
        public static final int RESERVED_2 = 6;
        public static final int TYPE_ATTACH_APPLICATION = 103;
        public static final int TYPE_SERVICE_START = 100;
        public static final int TYPE_START_APP_FROM_BG = 102;
        public static final int TYPE_START_PROC = 101;

        public Launch() {
        }
    }

    /* loaded from: classes3.dex */
    public class Draw {
        public static final int EVENT_TYPE_V1 = 1;

        public Draw() {
        }
    }

    /* loaded from: classes3.dex */
    public class WorkloadType {
        public static final int APP = 1;
        public static final int BROWSER = 3;
        public static final int GAME = 2;
        public static final int NOT_KNOWN = 0;
        public static final int PREPROAPP = 4;

        public WorkloadType() {
        }
    }

    public BoostFramework() {
        this.mPerf = null;
        this.mUxPerf = null;
        initFunctions();
        try {
            Class<?> cls = sPerfClass;
            if (cls != null) {
                this.mPerf = cls.newInstance();
            }
            Class<?> cls2 = sUxPerfClass;
            if (cls2 != null) {
                this.mUxPerf = cls2.newInstance();
            }
        } catch (Exception e) {
            Log.e(TAG, "BoostFramework() : Exception_2 = " + e);
        }
    }

    public BoostFramework(Context context) {
        this(context, false);
    }

    public BoostFramework(Context context, boolean isTrusted) {
        Constructor cons;
        this.mPerf = null;
        this.mUxPerf = null;
        initFunctions();
        try {
            Class<?> cls = sPerfClass;
            if (!(cls == null || (cons = cls.getConstructor(Context.class)) == null)) {
                this.mPerf = cons.newInstance(context);
            }
            Class<?> cls2 = sUxPerfClass;
            if (cls2 == null) {
                return;
            }
            if (isTrusted) {
                Constructor cons2 = cls2.getConstructor(Context.class);
                if (cons2 != null) {
                    this.mUxPerf = cons2.newInstance(context);
                }
                return;
            }
            this.mUxPerf = cls2.newInstance();
        } catch (Exception e) {
            Log.e(TAG, "BoostFramework() : Exception_3 = " + e);
        }
    }

    public BoostFramework(boolean isUntrustedDomain) {
        Constructor cons;
        this.mPerf = null;
        this.mUxPerf = null;
        initFunctions();
        try {
            Class<?> cls = sPerfClass;
            if (!(cls == null || (cons = cls.getConstructor(Boolean.TYPE)) == null)) {
                this.mPerf = cons.newInstance(Boolean.valueOf(isUntrustedDomain));
            }
            Class<?> cls2 = sUxPerfClass;
            if (cls2 != null) {
                this.mUxPerf = cls2.newInstance();
            }
        } catch (Exception e) {
            Log.e(TAG, "BoostFramework() : Exception_5 = " + e);
        }
    }

    private void initFunctions() {
        synchronized (BoostFramework.class) {
            if (!sIsLoaded) {
                try {
                    sPerfClass = Class.forName(PERFORMANCE_CLASS);
                    Class[] argClasses = {Integer.TYPE, int[].class};
                    sAcquireFunc = sPerfClass.getMethod("perfLockAcquire", argClasses);
                    Class[] argClasses2 = {Integer.TYPE, String.class, Integer.TYPE, Integer.TYPE};
                    sPerfHintFunc = sPerfClass.getMethod("perfHint", argClasses2);
                    sReleaseFunc = sPerfClass.getMethod("perfLockRelease", new Class[0]);
                    Class[] argClasses3 = {Integer.TYPE};
                    sReleaseHandlerFunc = sPerfClass.getDeclaredMethod("perfLockReleaseHandler", argClasses3);
                    Class[] argClasses4 = {Integer.TYPE, String.class};
                    sFeedbackFunc = sPerfClass.getMethod("perfGetFeedback", argClasses4);
                    Class[] argClasses5 = {Integer.TYPE, String.class, String.class};
                    sIOPStart = sPerfClass.getDeclaredMethod("perfIOPrefetchStart", argClasses5);
                    sIOPStop = sPerfClass.getDeclaredMethod("perfIOPrefetchStop", new Class[0]);
                    Class[] argClasses6 = {String.class, String.class};
                    sPerfGetPropFunc = sPerfClass.getMethod("perfGetProp", argClasses6);
                    Class[] argClasses7 = {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, int[].class};
                    sAcqAndReleaseFunc = sPerfClass.getMethod("perfLockAcqAndRelease", argClasses7);
                    try {
                        Class[] argClasses8 = {Integer.TYPE, Integer.TYPE, String.class, Integer.TYPE, String.class};
                        sUXEngineEvents = sPerfClass.getDeclaredMethod("perfUXEngine_events", argClasses8);
                        Class[] argClasses9 = {Integer.TYPE};
                        sUXEngineTrigger = sPerfClass.getDeclaredMethod("perfUXEngine_trigger", argClasses9);
                    } catch (Exception e) {
                        Log.i(TAG, "BoostFramework() : Exception_4 = PreferredApps not supported");
                    }
                    sIsLoaded = true;
                } catch (Exception e2) {
                    Log.e(TAG, "BoostFramework() : Exception_1 = " + e2);
                }
                try {
                    sUxPerfClass = Class.forName(UXPERFORMANCE_CLASS);
                    Class[] argUxClasses = {Integer.TYPE, String.class, String.class};
                    sUxIOPStart = sUxPerfClass.getDeclaredMethod("perfIOPrefetchStart", argUxClasses);
                    sUxIsLoaded = true;
                } catch (Exception e3) {
                    Log.e(TAG, "BoostFramework() Ux Perf: Exception = " + e3);
                }
            }
        }
    }

    public int perfLockAcquire(int duration, int... list) {
        try {
            Method method = sAcquireFunc;
            if (method == null) {
                return -1;
            }
            Object retVal = method.invoke(this.mPerf, Integer.valueOf(duration), list);
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    public int perfLockRelease() {
        try {
            Method method = sReleaseFunc;
            if (method == null) {
                return -1;
            }
            Object retVal = method.invoke(this.mPerf, new Object[0]);
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    public int perfLockReleaseHandler(int handle) {
        try {
            Method method = sReleaseHandlerFunc;
            if (method == null) {
                return -1;
            }
            Object retVal = method.invoke(this.mPerf, Integer.valueOf(handle));
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    public int perfHint(int hint, String userDataStr) {
        return perfHint(hint, userDataStr, -1, -1);
    }

    public int perfHint(int hint, String userDataStr, int userData) {
        return perfHint(hint, userDataStr, userData, -1);
    }

    public int perfHint(int hint, String userDataStr, int userData1, int userData2) {
        try {
            Method method = sPerfHintFunc;
            if (method == null) {
                return -1;
            }
            Object retVal = method.invoke(this.mPerf, Integer.valueOf(hint), userDataStr, Integer.valueOf(userData1), Integer.valueOf(userData2));
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    public int perfGetFeedback(int req, String userDataStr) {
        try {
            Method method = sFeedbackFunc;
            if (method == null) {
                return -1;
            }
            Object retVal = method.invoke(this.mPerf, Integer.valueOf(req), userDataStr);
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    public int perfIOPrefetchStart(int pid, String pkgName, String codePath) {
        int ret = -1;
        try {
            Object retVal = sIOPStart.invoke(this.mPerf, Integer.valueOf(pid), pkgName, codePath);
            ret = ((Integer) retVal).intValue();
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
        }
        try {
            Object retVal2 = sUxIOPStart.invoke(this.mUxPerf, Integer.valueOf(pid), pkgName, codePath);
            int ret2 = ((Integer) retVal2).intValue();
            return ret2;
        } catch (Exception e2) {
            Log.e(TAG, "Ux Perf Exception " + e2);
            return ret;
        }
    }

    public int perfIOPrefetchStop() {
        try {
            Object retVal = sIOPStop.invoke(this.mPerf, new Object[0]);
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    public int perfUXEngine_events(int opcode, int pid, String pkgName, int lat) {
        return perfUXEngine_events(opcode, pid, pkgName, lat, null);
    }

    public int perfUXEngine_events(int opcode, int pid, String pkgName, int lat, String codePath) {
        try {
            Method method = sUXEngineEvents;
            if (method == null) {
                return -1;
            }
            Object retVal = method.invoke(this.mPerf, Integer.valueOf(opcode), Integer.valueOf(pid), pkgName, Integer.valueOf(lat), codePath);
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    public String perfUXEngine_trigger(int opcode) {
        try {
            Method method = sUXEngineTrigger;
            if (method == null) {
                return null;
            }
            Object retVal = method.invoke(this.mPerf, Integer.valueOf(opcode));
            String ret = (String) retVal;
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return null;
        }
    }

    public String perfGetProp(String prop_name, String def_val) {
        try {
            Method method = sPerfGetPropFunc;
            if (method == null) {
                return def_val;
            }
            Object retVal = method.invoke(this.mPerf, prop_name, def_val);
            String ret = (String) retVal;
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return "";
        }
    }

    public int perfLockAcqAndRelease(int handle, int duration, int numArgs, int reserveNumArgs, int... list) {
        try {
            Method method = sAcqAndReleaseFunc;
            if (method == null) {
                return -1;
            }
            Object retVal = method.invoke(this.mPerf, Integer.valueOf(handle), Integer.valueOf(duration), Integer.valueOf(numArgs), Integer.valueOf(reserveNumArgs), list);
            int ret = ((Integer) retVal).intValue();
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "Exception " + e);
            return -1;
        }
    }

    /* loaded from: classes3.dex */
    public static class ScrollOptimizer {
        public static final int FLING_END = 0;
        public static final int FLING_START = 1;
        private static final String QXPERFORMANCE_JAR = "/system/framework/QXPerformance.jar";
        private static final String SCROLL_OPT_CLASS = "com.qualcomm.qti.QXPerformance.ScrollOptimizer";
        private static final String SCROLL_OPT_PROP = "ro.vendor.perf.scroll_opt";
        private static boolean sScrollOptProp = false;
        private static boolean sScrollOptEnable = false;
        private static boolean sQXIsLoaded = false;
        private static Class<?> sQXPerfClass = null;
        private static Method sSetFrameInterval = null;
        private static Method sSetSurface = null;
        private static Method sSetMotionType = null;
        private static Method sSetVsyncTime = null;
        private static Method sSetUITaskStatus = null;
        private static Method sSetFlingFlag = null;
        private static Method sShouldUseVsync = null;
        private static Method sGetFrameDelay = null;
        private static Method sGetAdjustedAnimationClock = null;

        /* JADX INFO: Access modifiers changed from: private */
        public static void initQXPerfFuncs() {
            if (!sQXIsLoaded) {
                try {
                    boolean z = SystemProperties.getBoolean(SCROLL_OPT_PROP, false);
                    sScrollOptProp = z;
                    if (!z) {
                        sScrollOptEnable = false;
                        sQXIsLoaded = true;
                        return;
                    }
                    PathClassLoader qXPerfClassLoader = new PathClassLoader(QXPERFORMANCE_JAR, ClassLoader.getSystemClassLoader());
                    sQXPerfClass = qXPerfClassLoader.loadClass(SCROLL_OPT_CLASS);
                    Class[] argClasses = {Long.TYPE};
                    sSetFrameInterval = sQXPerfClass.getMethod("setFrameInterval", argClasses);
                    Class[] argClasses2 = {Surface.class};
                    sSetSurface = sQXPerfClass.getMethod("setSurface", argClasses2);
                    Class[] argClasses3 = {Integer.TYPE};
                    sSetMotionType = sQXPerfClass.getMethod("setMotionType", argClasses3);
                    Class[] argClasses4 = {Long.TYPE};
                    sSetVsyncTime = sQXPerfClass.getMethod("setVsyncTime", argClasses4);
                    Class[] argClasses5 = {Boolean.TYPE};
                    sSetUITaskStatus = sQXPerfClass.getMethod("setUITaskStatus", argClasses5);
                    Class[] argClasses6 = {Integer.TYPE};
                    sSetFlingFlag = sQXPerfClass.getMethod("setFlingFlag", argClasses6);
                    sShouldUseVsync = sQXPerfClass.getMethod("shouldUseVsync", new Class[0]);
                    Class[] argClasses7 = {Long.TYPE};
                    sGetFrameDelay = sQXPerfClass.getMethod("getFrameDelay", argClasses7);
                    Class[] argClasses8 = {Long.TYPE};
                    sGetAdjustedAnimationClock = sQXPerfClass.getMethod("getAdjustedAnimationClock", argClasses8);
                    sQXIsLoaded = true;
                } catch (Exception e) {
                    Log.e(BoostFramework.TAG, "initQXPerfFuncs failed");
                    e.printStackTrace();
                }
            }
        }

        public static void setFrameInterval(final long frameIntervalNanos) {
            Thread initThread = new Thread(new Runnable() { // from class: android.util.BoostFramework.ScrollOptimizer.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ScrollOptimizer.initQXPerfFuncs();
                        if (ScrollOptimizer.sScrollOptProp && ScrollOptimizer.sSetFrameInterval != null) {
                            ScrollOptimizer.sSetFrameInterval.invoke(null, Long.valueOf(frameIntervalNanos));
                            boolean unused = ScrollOptimizer.sScrollOptEnable = true;
                        }
                    } catch (Exception e) {
                        Log.e(BoostFramework.TAG, "Failed to run initThread.");
                        e.printStackTrace();
                    }
                }
            });
            initThread.start();
        }

        public static void setSurface(Surface surface) {
            Method method;
            if (sScrollOptEnable && (method = sSetSurface) != null) {
                try {
                    method.invoke(null, surface);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public static void setMotionType(int eventType) {
            Method method;
            if (sScrollOptEnable && (method = sSetMotionType) != null) {
                try {
                    method.invoke(null, Integer.valueOf(eventType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public static void setVsyncTime(long vsyncTimeNanos) {
            Method method;
            if (sScrollOptEnable && (method = sSetVsyncTime) != null) {
                try {
                    method.invoke(null, Long.valueOf(vsyncTimeNanos));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public static void setUITaskStatus(boolean running) {
            Method method;
            if (sScrollOptEnable && (method = sSetUITaskStatus) != null) {
                try {
                    method.invoke(null, Boolean.valueOf(running));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public static void setFlingFlag(int flag) {
            Method method;
            if (sScrollOptEnable && (method = sSetFlingFlag) != null) {
                try {
                    method.invoke(null, Integer.valueOf(flag));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public static boolean shouldUseVsync(boolean defaultVsyncFlag) {
            Method method;
            if (!sScrollOptEnable || (method = sShouldUseVsync) == null) {
                return defaultVsyncFlag;
            }
            try {
                Object retVal = method.invoke(null, new Object[0]);
                boolean useVsync = ((Boolean) retVal).booleanValue();
                return useVsync;
            } catch (Exception e) {
                e.printStackTrace();
                return defaultVsyncFlag;
            }
        }

        public static long getFrameDelay(long defaultDelay, long lastFrameTimeNanos) {
            Method method;
            if (!sScrollOptEnable || (method = sGetFrameDelay) == null) {
                return defaultDelay;
            }
            try {
                Object retVal = method.invoke(null, Long.valueOf(lastFrameTimeNanos));
                long frameDelay = ((Long) retVal).longValue();
                return frameDelay;
            } catch (Exception e) {
                e.printStackTrace();
                return defaultDelay;
            }
        }

        public static long getAdjustedAnimationClock(long frameTimeNanos) {
            Method method;
            if (!sScrollOptEnable || (method = sGetAdjustedAnimationClock) == null) {
                return frameTimeNanos;
            }
            try {
                Object retVal = method.invoke(null, Long.valueOf(frameTimeNanos));
                long newFrameTimeNanos = ((Long) retVal).longValue();
                return newFrameTimeNanos;
            } catch (Exception e) {
                e.printStackTrace();
                return frameTimeNanos;
            }
        }
    }
}
