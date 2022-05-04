package com.android.internal.jank;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.os.SystemProperties;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.View;
import com.android.internal.jank.FrameTracker;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.util.PerfettoTrigger;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
/* loaded from: classes4.dex */
public class InteractionJankMonitor {
    public static final String ACTION_METRICS_LOGGED;
    private static final String ACTION_PREFIX;
    public static final String ACTION_SESSION_BEGIN;
    public static final String ACTION_SESSION_CANCEL;
    public static final String ACTION_SESSION_END;
    public static final String BUNDLE_KEY_CUJ_NAME;
    public static final String BUNDLE_KEY_TIMESTAMP;
    public static final int CUJ_LAUNCHER_ALL_APPS_SCROLL = 26;
    public static final int CUJ_LAUNCHER_APP_CLOSE_TO_HOME = 9;
    public static final int CUJ_LAUNCHER_APP_CLOSE_TO_PIP = 10;
    public static final int CUJ_LAUNCHER_APP_LAUNCH_FROM_ICON = 8;
    public static final int CUJ_LAUNCHER_APP_LAUNCH_FROM_RECENTS = 7;
    public static final int CUJ_LAUNCHER_APP_LAUNCH_FROM_WIDGET = 27;
    public static final int CUJ_LAUNCHER_OPEN_ALL_APPS = 25;
    public static final int CUJ_LAUNCHER_QUICK_SWITCH = 11;
    public static final int CUJ_LOCKSCREEN_PASSWORD_APPEAR = 17;
    public static final int CUJ_LOCKSCREEN_PASSWORD_DISAPPEAR = 20;
    public static final int CUJ_LOCKSCREEN_PATTERN_APPEAR = 18;
    public static final int CUJ_LOCKSCREEN_PATTERN_DISAPPEAR = 21;
    public static final int CUJ_LOCKSCREEN_PIN_APPEAR = 19;
    public static final int CUJ_LOCKSCREEN_PIN_DISAPPEAR = 22;
    public static final int CUJ_LOCKSCREEN_TRANSITION_FROM_AOD = 23;
    public static final int CUJ_LOCKSCREEN_TRANSITION_TO_AOD = 24;
    public static final int CUJ_LOCKSCREEN_UNLOCK_ANIMATION = 29;
    public static final int CUJ_NOTIFICATION_ADD = 14;
    public static final int CUJ_NOTIFICATION_APP_START = 16;
    public static final int CUJ_NOTIFICATION_HEADS_UP_APPEAR = 12;
    public static final int CUJ_NOTIFICATION_HEADS_UP_DISAPPEAR = 13;
    public static final int CUJ_NOTIFICATION_REMOVE = 15;
    public static final int CUJ_NOTIFICATION_SHADE_EXPAND_COLLAPSE = 0;
    public static final int CUJ_NOTIFICATION_SHADE_EXPAND_COLLAPSE_LOCK = 1;
    public static final int CUJ_NOTIFICATION_SHADE_QS_EXPAND_COLLAPSE = 5;
    public static final int CUJ_NOTIFICATION_SHADE_QS_SCROLL_SWIPE = 6;
    public static final int CUJ_NOTIFICATION_SHADE_ROW_EXPAND = 3;
    public static final int CUJ_NOTIFICATION_SHADE_ROW_SWIPE = 4;
    public static final int CUJ_NOTIFICATION_SHADE_SCROLL_FLING = 2;
    public static final int CUJ_SETTINGS_PAGE_SCROLL = 28;
    public static final int CUJ_SHADE_APP_LAUNCH_FROM_HISTORY_BUTTON = 30;
    public static final int CUJ_SHADE_APP_LAUNCH_FROM_MEDIA_PLAYER = 31;
    public static final int CUJ_SHADE_APP_LAUNCH_FROM_QS_TILE = 32;
    public static final int CUJ_SHADE_APP_LAUNCH_FROM_SETTINGS_BUTTON = 33;
    public static final int CUJ_STATUS_BAR_APP_LAUNCH_FROM_CALL_CHIP = 34;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_SAMPLING_INTERVAL = 1;
    private static final int DEFAULT_TRACE_THRESHOLD_FRAME_TIME_MILLIS = 64;
    private static final int DEFAULT_TRACE_THRESHOLD_MISSED_FRAMES = 3;
    private static final String DEFAULT_WORKER_NAME;
    private static final int NO_STATSD_LOGGING = -1;
    public static final String PROP_NOTIFY_CUJ_EVENT = "debug.jank.notify_cuj_events";
    private static final String SETTINGS_ENABLED_KEY = "enabled";
    private static final String SETTINGS_SAMPLING_INTERVAL_KEY = "sampling_interval";
    private static final String SETTINGS_THRESHOLD_FRAME_TIME_MILLIS_KEY = "trace_threshold_frame_time_millis";
    private static final String SETTINGS_THRESHOLD_MISSED_FRAMES_KEY = "trace_threshold_missed_frames";
    private static final String TAG;
    private static volatile InteractionJankMonitor sInstance;
    private boolean mEnabled;
    private final DeviceConfig.OnPropertiesChangedListener mPropertiesChangedListener;
    private int mSamplingInterval;
    private HandlerThread mWorker;
    private static final long DEFAULT_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(5);
    private static final boolean DEFAULT_ENABLED = Build.IS_DEBUGGABLE;
    public static final int[] CUJ_TO_STATSD_INTERACTION_TYPE = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
    private int mTraceThresholdMissedFrames = 3;
    private int mTraceThresholdFrameTimeMillis = 64;
    private SparseArray<FrameTracker> mRunningTrackers = new SparseArray<>();
    private SparseArray<Runnable> mTimeoutActions = new SparseArray<>();
    private FrameTracker.FrameMetricsWrapper mMetrics = new FrameTracker.FrameMetricsWrapper();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface CujType {
    }

    static {
        String simpleName = InteractionJankMonitor.class.getSimpleName();
        TAG = simpleName;
        String canonicalName = InteractionJankMonitor.class.getCanonicalName();
        ACTION_PREFIX = canonicalName;
        DEFAULT_WORKER_NAME = simpleName + "-Worker";
        ACTION_SESSION_BEGIN = canonicalName + ".ACTION_SESSION_BEGIN";
        ACTION_SESSION_END = canonicalName + ".ACTION_SESSION_END";
        ACTION_SESSION_CANCEL = canonicalName + ".ACTION_SESSION_CANCEL";
        ACTION_METRICS_LOGGED = canonicalName + ".ACTION_METRICS_LOGGED";
        BUNDLE_KEY_CUJ_NAME = canonicalName + ".CUJ_NAME";
        BUNDLE_KEY_TIMESTAMP = canonicalName + ".TIMESTAMP";
    }

    public static InteractionJankMonitor getInstance() {
        if (sInstance == null) {
            synchronized (InteractionJankMonitor.class) {
                if (sInstance == null) {
                    sInstance = new InteractionJankMonitor(new HandlerThread(DEFAULT_WORKER_NAME));
                }
            }
        }
        return sInstance;
    }

    public InteractionJankMonitor(HandlerThread worker) {
        DeviceConfig.OnPropertiesChangedListener interactionJankMonitor$$ExternalSyntheticLambda0 = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.internal.jank.InteractionJankMonitor$$ExternalSyntheticLambda0
            @Override // android.provider.DeviceConfig.OnPropertiesChangedListener
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                InteractionJankMonitor.this.updateProperties(properties);
            }
        };
        this.mPropertiesChangedListener = interactionJankMonitor$$ExternalSyntheticLambda0;
        boolean z = DEFAULT_ENABLED;
        this.mEnabled = z;
        this.mSamplingInterval = 1;
        this.mWorker = worker;
        this.mWorker.start();
        this.mEnabled = z;
        this.mSamplingInterval = 1;
        this.mWorker.getThreadHandler().post(new Runnable() { // from class: com.android.internal.jank.InteractionJankMonitor$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                InteractionJankMonitor.this.lambda$new$0$InteractionJankMonitor();
            }
        });
        DeviceConfig.addOnPropertiesChangedListener(DeviceConfig.NAMESPACE_INTERACTION_JANK_MONITOR, new HandlerExecutor(this.mWorker.getThreadHandler()), interactionJankMonitor$$ExternalSyntheticLambda0);
    }

    public /* synthetic */ void lambda$new$0$InteractionJankMonitor() {
        this.mPropertiesChangedListener.onPropertiesChanged(DeviceConfig.getProperties(DeviceConfig.NAMESPACE_INTERACTION_JANK_MONITOR, new String[0]));
    }

    public FrameTracker createFrameTracker(Configuration conf, Session session) {
        FrameTracker frameTracker;
        View v = conf.mView;
        final Context c = v.getContext().getApplicationContext();
        FrameTracker.ThreadedRendererWrapper r = new FrameTracker.ThreadedRendererWrapper(v.getThreadedRenderer());
        FrameTracker.ViewRootWrapper vr = new FrameTracker.ViewRootWrapper(v.getViewRootImpl());
        FrameTracker.SurfaceControlWrapper sc = new FrameTracker.SurfaceControlWrapper();
        FrameTracker.ChoreographerWrapper cg = new FrameTracker.ChoreographerWrapper(Choreographer.getInstance());
        synchronized (this) {
            FrameTracker.FrameTrackerListener eventsListener = new FrameTracker.FrameTrackerListener() { // from class: com.android.internal.jank.InteractionJankMonitor$$ExternalSyntheticLambda1
                @Override // com.android.internal.jank.FrameTracker.FrameTrackerListener
                public final void onCujEvents(InteractionJankMonitor.Session session2, String str) {
                    InteractionJankMonitor.this.lambda$createFrameTracker$1$InteractionJankMonitor(c, session2, str);
                }
            };
            frameTracker = new FrameTracker(session, this.mWorker.getThreadHandler(), r, vr, sc, cg, this.mMetrics, this.mTraceThresholdMissedFrames, this.mTraceThresholdFrameTimeMillis, eventsListener);
        }
        return frameTracker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleCujEvents */
    public void lambda$createFrameTracker$1$InteractionJankMonitor(Context context, String action, Session session) {
        if (needRemoveTasks(action, session)) {
            removeTimeout(session.getCuj());
            removeTracker(session.getCuj());
        }
        if (session.shouldNotify()) {
            notifyEvents(context, action, session);
        }
    }

    private boolean needRemoveTasks(String action, Session session) {
        boolean badEnd = action.equals(ACTION_SESSION_END) && session.getReason() != 0;
        boolean badCancel = action.equals(ACTION_SESSION_CANCEL) && session.getReason() != 16;
        return badEnd || badCancel;
    }

    private void notifyEvents(Context context, String action, Session session) {
        if (!action.equals(ACTION_SESSION_CANCEL) || session.getReason() != 17) {
            Intent intent = new Intent(action);
            intent.putExtra(BUNDLE_KEY_CUJ_NAME, getNameOfCuj(session.getCuj()));
            intent.putExtra(BUNDLE_KEY_TIMESTAMP, session.getTimeStamp());
            intent.addFlags(1073741824);
            context.sendBroadcast(intent);
        }
    }

    private void removeTimeout(int cujType) {
        synchronized (this) {
            Runnable timeout = this.mTimeoutActions.get(cujType);
            if (timeout != null) {
                this.mWorker.getThreadHandler().removeCallbacks(timeout);
                this.mTimeoutActions.remove(cujType);
            }
        }
    }

    public boolean begin(View v, int cujType) {
        try {
            return beginInternal(new Configuration.Builder(cujType).setView(v).build());
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "Build configuration failed!", ex);
            return false;
        }
    }

    public boolean begin(Configuration.Builder builder) {
        try {
            return beginInternal(builder.build());
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "Build configuration failed!", ex);
            return false;
        }
    }

    private boolean beginInternal(Configuration conf) {
        synchronized (this) {
            final int cujType = conf.mCujType;
            boolean shouldSample = ThreadLocalRandom.current().nextInt() % this.mSamplingInterval == 0;
            if (this.mEnabled && shouldSample) {
                if (getTracker(cujType) != null) {
                    return false;
                }
                FrameTracker tracker = createFrameTracker(conf, new Session(cujType, conf.mTag));
                this.mRunningTrackers.put(cujType, tracker);
                tracker.begin();
                Runnable timeoutAction = new Runnable() { // from class: com.android.internal.jank.InteractionJankMonitor$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        InteractionJankMonitor.this.lambda$beginInternal$2$InteractionJankMonitor(cujType);
                    }
                };
                this.mTimeoutActions.put(cujType, timeoutAction);
                this.mWorker.getThreadHandler().postDelayed(timeoutAction, conf.mTimeout);
                return true;
            }
            return false;
        }
    }

    public boolean end(int cujType) {
        synchronized (this) {
            removeTimeout(cujType);
            FrameTracker tracker = getTracker(cujType);
            if (tracker == null) {
                return false;
            }
            tracker.end(0);
            removeTracker(cujType);
            return true;
        }
    }

    /* renamed from: cancel */
    public boolean lambda$beginInternal$2$InteractionJankMonitor(int cujType) {
        synchronized (this) {
            removeTimeout(cujType);
            FrameTracker tracker = getTracker(cujType);
            if (tracker == null) {
                return false;
            }
            tracker.cancel(16);
            removeTracker(cujType);
            return true;
        }
    }

    private FrameTracker getTracker(int cuj) {
        FrameTracker frameTracker;
        synchronized (this) {
            frameTracker = this.mRunningTrackers.get(cuj);
        }
        return frameTracker;
    }

    private void removeTracker(int cuj) {
        synchronized (this) {
            this.mRunningTrackers.remove(cuj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProperties(DeviceConfig.Properties properties) {
        synchronized (this) {
            this.mSamplingInterval = properties.getInt("sampling_interval", 1);
            this.mEnabled = properties.getBoolean("enabled", DEFAULT_ENABLED);
            this.mTraceThresholdMissedFrames = properties.getInt(SETTINGS_THRESHOLD_MISSED_FRAMES_KEY, 3);
            this.mTraceThresholdFrameTimeMillis = properties.getInt(SETTINGS_THRESHOLD_FRAME_TIME_MILLIS_KEY, 64);
        }
    }

    public DeviceConfig.OnPropertiesChangedListener getPropertiesChangedListener() {
        return this.mPropertiesChangedListener;
    }

    public void trigger(final Session session) {
        synchronized (this) {
            this.mWorker.getThreadHandler().post(new Runnable() { // from class: com.android.internal.jank.InteractionJankMonitor$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    PerfettoTrigger.trigger(InteractionJankMonitor.Session.this.getPerfettoTrigger());
                }
            });
        }
    }

    public static String getNameOfInteraction(int interactionType) {
        return getNameOfCuj(interactionType - 1);
    }

    public static String getNameOfCuj(int cujType) {
        switch (cujType) {
            case 0:
                return "SHADE_EXPAND_COLLAPSE";
            case 1:
                return "SHADE_EXPAND_COLLAPSE_LOCK";
            case 2:
                return "SHADE_SCROLL_FLING";
            case 3:
                return "SHADE_ROW_EXPAND";
            case 4:
                return "SHADE_ROW_SWIPE";
            case 5:
                return "SHADE_QS_EXPAND_COLLAPSE";
            case 6:
                return "SHADE_QS_SCROLL_SWIPE";
            case 7:
                return "LAUNCHER_APP_LAUNCH_FROM_RECENTS";
            case 8:
                return "LAUNCHER_APP_LAUNCH_FROM_ICON";
            case 9:
                return "LAUNCHER_APP_CLOSE_TO_HOME";
            case 10:
                return "LAUNCHER_APP_CLOSE_TO_PIP";
            case 11:
                return "LAUNCHER_QUICK_SWITCH";
            case 12:
                return "NOTIFICATION_HEADS_UP_APPEAR";
            case 13:
                return "NOTIFICATION_HEADS_UP_DISAPPEAR";
            case 14:
                return "NOTIFICATION_ADD";
            case 15:
                return "NOTIFICATION_REMOVE";
            case 16:
                return "NOTIFICATION_APP_START";
            case 17:
                return "LOCKSCREEN_PASSWORD_APPEAR";
            case 18:
                return "LOCKSCREEN_PATTERN_APPEAR";
            case 19:
                return "LOCKSCREEN_PIN_APPEAR";
            case 20:
                return "LOCKSCREEN_PASSWORD_DISAPPEAR";
            case 21:
                return "LOCKSCREEN_PATTERN_DISAPPEAR";
            case 22:
                return "LOCKSCREEN_PIN_DISAPPEAR";
            case 23:
                return "LOCKSCREEN_TRANSITION_FROM_AOD";
            case 24:
                return "LOCKSCREEN_TRANSITION_TO_AOD";
            case 25:
                return "LAUNCHER_OPEN_ALL_APPS";
            case 26:
                return "LAUNCHER_ALL_APPS_SCROLL";
            case 27:
                return "LAUNCHER_APP_LAUNCH_FROM_WIDGET";
            case 28:
                return "SETTINGS_PAGE_SCROLL";
            case 29:
                return "LOCKSCREEN_UNLOCK_ANIMATION";
            case 30:
                return "SHADE_APP_LAUNCH_FROM_HISTORY_BUTTON";
            case 31:
                return "SHADE_APP_LAUNCH_FROM_MEDIA_PLAYER";
            case 32:
                return "SHADE_APP_LAUNCH_FROM_QS_TILE";
            case 33:
                return "SHADE_APP_LAUNCH_FROM_SETTINGS_BUTTON";
            case 34:
                return "STATUS_BAR_APP_LAUNCH_FROM_CALL_CHIP";
            default:
                return "UNKNOWN";
        }
    }

    /* loaded from: classes4.dex */
    public static class Configuration {
        private final int mCujType;
        private final String mTag;
        private final long mTimeout;
        private final View mView;

        /* loaded from: classes4.dex */
        public static class Builder {
            private int mAttrCujType;
            private View mAttrView = null;
            private long mAttrTimeout = InteractionJankMonitor.DEFAULT_TIMEOUT_MS;
            private String mAttrTag = "";

            public Builder(int cuj) {
                this.mAttrCujType = cuj;
            }

            public Builder setView(View view) {
                this.mAttrView = view;
                return this;
            }

            public Builder setTimeout(long timeout) {
                this.mAttrTimeout = timeout;
                return this;
            }

            public Builder setTag(String tag) {
                this.mAttrTag = tag;
                return this;
            }

            public Configuration build() throws IllegalArgumentException {
                return new Configuration(this.mAttrCujType, this.mAttrView, this.mAttrTag, this.mAttrTimeout);
            }
        }

        private Configuration(int cuj, View view, String tag, long timeout) {
            this.mCujType = cuj;
            this.mTag = tag;
            this.mTimeout = timeout;
            this.mView = view;
            validate();
        }

        private void validate() {
            boolean shouldThrow = false;
            StringBuilder msg = new StringBuilder();
            if (this.mTag == null) {
                shouldThrow = true;
                msg.append("Invalid tag; ");
            }
            if (this.mTimeout < 0) {
                shouldThrow = true;
                msg.append("Invalid timeout value; ");
            }
            View view = this.mView;
            if (view == null || !view.isAttachedToWindow()) {
                shouldThrow = true;
                msg.append("Null view or view is not attached yet; ");
            }
            if (shouldThrow) {
                throw new IllegalArgumentException(msg.toString());
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class Session {
        private final int mCujType;
        private final String mName;
        private int mReason = -1;
        private final long mTimeStamp = System.nanoTime();
        private final boolean mShouldNotify = SystemProperties.getBoolean(InteractionJankMonitor.PROP_NOTIFY_CUJ_EVENT, false);

        public Session(int cujType, String postfix) {
            this.mCujType = cujType;
            this.mName = TextUtils.isEmpty(postfix) ? String.format("J<%s>", InteractionJankMonitor.getNameOfCuj(cujType)) : String.format("J<%s::%s>", InteractionJankMonitor.getNameOfCuj(cujType), postfix);
        }

        public int getCuj() {
            return this.mCujType;
        }

        public int getStatsdInteractionType() {
            return InteractionJankMonitor.CUJ_TO_STATSD_INTERACTION_TYPE[this.mCujType];
        }

        public boolean logToStatsd() {
            return getStatsdInteractionType() != -1;
        }

        public String getPerfettoTrigger() {
            return String.format(Locale.US, "com.android.telemetry.interaction-jank-monitor-%d", Integer.valueOf(this.mCujType));
        }

        public String getName() {
            return this.mName;
        }

        public long getTimeStamp() {
            return this.mTimeStamp;
        }

        public void setReason(int reason) {
            this.mReason = reason;
        }

        public int getReason() {
            return this.mReason;
        }

        public boolean shouldNotify() {
            return this.mShouldNotify;
        }
    }
}
