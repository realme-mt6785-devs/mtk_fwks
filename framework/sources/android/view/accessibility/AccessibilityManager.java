package android.view.accessibility;

import android.Manifest;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.AccessibilityShortcutInfo;
import android.annotation.SystemApi;
import android.app.RemoteAction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.IWindow;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.IAccessibilityManager;
import android.view.accessibility.IAccessibilityManagerClient;
import com.android.internal.R;
import com.android.internal.util.IntPair;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes3.dex */
public final class AccessibilityManager {
    public static final int ACCESSIBILITY_BUTTON = 0;
    public static final int ACCESSIBILITY_SHORTCUT_KEY = 1;
    public static final String ACTION_CHOOSE_ACCESSIBILITY_BUTTON = "com.android.internal.intent.action.CHOOSE_ACCESSIBILITY_BUTTON";
    public static final int AUTOCLICK_DELAY_DEFAULT = 600;
    public static final int DALTONIZER_CORRECT_DEUTERANOMALY = 12;
    public static final int DALTONIZER_DISABLED = -1;
    public static final int DALTONIZER_SIMULATE_MONOCHROMACY = 0;
    private static final boolean DEBUG = false;
    public static final int FLAG_CONTENT_CONTROLS = 4;
    public static final int FLAG_CONTENT_ICONS = 1;
    public static final int FLAG_CONTENT_TEXT = 2;
    private static final String LOG_TAG = "AccessibilityManager";
    public static final int STATE_FLAG_ACCESSIBILITY_ENABLED = 1;
    public static final int STATE_FLAG_ACCESSIBILITY_TRACING_ENABLED = 32;
    public static final int STATE_FLAG_DISPATCH_DOUBLE_TAP = 8;
    public static final int STATE_FLAG_HIGH_TEXT_CONTRAST_ENABLED = 4;
    public static final int STATE_FLAG_REQUEST_MULTI_FINGER_GESTURES = 16;
    public static final int STATE_FLAG_TOUCH_EXPLORATION_ENABLED = 2;
    private static AccessibilityManager sInstance;
    static final Object sInstanceSync = new Object();
    public IAccessibilityManagerExt mAccessibilityManagerExt;
    AccessibilityPolicy mAccessibilityPolicy;
    private final ArrayMap<AccessibilityStateChangeListener, Handler> mAccessibilityStateChangeListeners;
    final Handler.Callback mCallback;
    private final IAccessibilityManagerClient.Stub mClient;
    private int mFocusColor;
    private int mFocusStrokeWidth;
    final Handler mHandler;
    private final ArrayMap<HighTextContrastChangeListener, Handler> mHighTextContrastStateChangeListeners;
    int mInteractiveUiTimeout;
    boolean mIsAccessibilityTracingEnabled;
    boolean mIsEnabled;
    boolean mIsHighTextContrastEnabled;
    boolean mIsTouchExplorationEnabled;
    private final Object mLock;
    int mNonInteractiveUiTimeout;
    private int mPerformingAction;
    int mRelevantEventTypes;
    private SparseArray<List<AccessibilityRequestPreparer>> mRequestPreparerLists;
    private IAccessibilityManager mService;
    private final ArrayMap<AccessibilityServicesStateChangeListener, Handler> mServicesStateChangeListeners;
    private final ArrayMap<TouchExplorationStateChangeListener, Handler> mTouchExplorationStateChangeListeners;
    final int mUserId;

    /* loaded from: classes3.dex */
    public interface AccessibilityPolicy {
        List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(int i, List<AccessibilityServiceInfo> list);

        List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(List<AccessibilityServiceInfo> list);

        int getRelevantEventTypes(int i);

        boolean isEnabled(boolean z);

        AccessibilityEvent onAccessibilityEvent(AccessibilityEvent accessibilityEvent, boolean z, int i);
    }

    /* loaded from: classes3.dex */
    public interface AccessibilityServicesStateChangeListener {
        void onAccessibilityServicesStateChanged(AccessibilityManager accessibilityManager);
    }

    /* loaded from: classes3.dex */
    public interface AccessibilityStateChangeListener {
        void onAccessibilityStateChanged(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ContentFlag {
    }

    /* loaded from: classes3.dex */
    public interface HighTextContrastChangeListener {
        void onHighTextContrastStateChanged(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ShortcutType {
    }

    /* loaded from: classes3.dex */
    public interface TouchExplorationStateChangeListener {
        void onTouchExplorationStateChanged(boolean z);
    }

    /* renamed from: android.view.accessibility.AccessibilityManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends IAccessibilityManagerClient.Stub {
        AnonymousClass1() {
        }

        @Override // android.view.accessibility.IAccessibilityManagerClient
        public void setState(int state) {
            AccessibilityManager.this.mHandler.obtainMessage(1, state, 0).sendToTarget();
        }

        @Override // android.view.accessibility.IAccessibilityManagerClient
        public void notifyServicesStateChanged(long updatedUiTimeout) {
            AccessibilityManager.this.updateUiTimeout(updatedUiTimeout);
            synchronized (AccessibilityManager.this.mLock) {
                if (!AccessibilityManager.this.mServicesStateChangeListeners.isEmpty()) {
                    ArrayMap<AccessibilityServicesStateChangeListener, Handler> listeners = new ArrayMap<>(AccessibilityManager.this.mServicesStateChangeListeners);
                    int numListeners = listeners.size();
                    for (int i = 0; i < numListeners; i++) {
                        final AccessibilityServicesStateChangeListener listener = (AccessibilityServicesStateChangeListener) AccessibilityManager.this.mServicesStateChangeListeners.keyAt(i);
                        ((Handler) AccessibilityManager.this.mServicesStateChangeListeners.valueAt(i)).post(new Runnable() { // from class: android.view.accessibility.AccessibilityManager$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                AccessibilityManager.AnonymousClass1.this.lambda$notifyServicesStateChanged$0$AccessibilityManager$1(listener);
                            }
                        });
                    }
                }
            }
        }

        public /* synthetic */ void lambda$notifyServicesStateChanged$0$AccessibilityManager$1(AccessibilityServicesStateChangeListener listener) {
            listener.onAccessibilityServicesStateChanged(AccessibilityManager.this);
        }

        @Override // android.view.accessibility.IAccessibilityManagerClient
        public void setRelevantEventTypes(int eventTypes) {
            AccessibilityManager.this.mRelevantEventTypes = eventTypes;
        }

        @Override // android.view.accessibility.IAccessibilityManagerClient
        public void setFocusAppearance(int strokeWidth, int color) {
            synchronized (AccessibilityManager.this.mLock) {
                AccessibilityManager.this.updateFocusAppearanceLocked(strokeWidth, color);
            }
        }
    }

    public static AccessibilityManager getInstance(Context context) {
        int userId;
        synchronized (sInstanceSync) {
            if (sInstance == null) {
                if (!(Binder.getCallingUid() == 1000 || context.checkCallingOrSelfPermission(Manifest.permission.INTERACT_ACROSS_USERS) == 0 || context.checkCallingOrSelfPermission(Manifest.permission.INTERACT_ACROSS_USERS_FULL) == 0)) {
                    userId = context.getUserId();
                    sInstance = new AccessibilityManager(context, null, userId);
                }
                userId = -2;
                sInstance = new AccessibilityManager(context, null, userId);
            }
        }
        return sInstance;
    }

    public AccessibilityManager(Context context, IAccessibilityManager service, int userId) {
        Object obj = new Object();
        this.mLock = obj;
        this.mRelevantEventTypes = -1;
        this.mIsAccessibilityTracingEnabled = false;
        this.mPerformingAction = 0;
        this.mAccessibilityStateChangeListeners = new ArrayMap<>();
        this.mTouchExplorationStateChangeListeners = new ArrayMap<>();
        this.mHighTextContrastStateChangeListeners = new ArrayMap<>();
        this.mServicesStateChangeListeners = new ArrayMap<>();
        this.mClient = new AnonymousClass1();
        this.mAccessibilityManagerExt = AccessibilityManagerExtPlugin.constructor.newInstance();
        MyCallback myCallback = new MyCallback(this, null);
        this.mCallback = myCallback;
        this.mHandler = new Handler(context.getMainLooper(), myCallback);
        this.mUserId = userId;
        synchronized (obj) {
            initialFocusAppearanceLocked(context.getResources());
            tryConnectToServiceLocked(service);
        }
    }

    public AccessibilityManager(Context context, Handler handler, IAccessibilityManager service, int userId, boolean serviceConnect) {
        Object obj = new Object();
        this.mLock = obj;
        this.mRelevantEventTypes = -1;
        this.mIsAccessibilityTracingEnabled = false;
        this.mPerformingAction = 0;
        this.mAccessibilityStateChangeListeners = new ArrayMap<>();
        this.mTouchExplorationStateChangeListeners = new ArrayMap<>();
        this.mHighTextContrastStateChangeListeners = new ArrayMap<>();
        this.mServicesStateChangeListeners = new ArrayMap<>();
        this.mClient = new AnonymousClass1();
        this.mCallback = new MyCallback(this, null);
        this.mHandler = handler;
        this.mUserId = userId;
        synchronized (obj) {
            initialFocusAppearanceLocked(context.getResources());
            if (serviceConnect) {
                tryConnectToServiceLocked(service);
            }
        }
    }

    public IAccessibilityManagerClient getClient() {
        return this.mClient;
    }

    public boolean removeClient() {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return false;
            }
            try {
                return service.removeClient(this.mClient, this.mUserId);
            } catch (RemoteException re) {
                Log.e(LOG_TAG, "AccessibilityManagerService is dead", re);
                return false;
            }
        }
    }

    public Handler.Callback getCallback() {
        return this.mCallback;
    }

    public boolean isEnabled() {
        boolean z;
        AccessibilityPolicy accessibilityPolicy;
        synchronized (this.mLock) {
            boolean z2 = this.mIsEnabled;
            if (!z2 && ((accessibilityPolicy = this.mAccessibilityPolicy) == null || !accessibilityPolicy.isEnabled(z2))) {
                z = false;
            }
            z = true;
        }
        return z;
    }

    public boolean isTouchExplorationEnabled() {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return false;
            }
            return this.mIsTouchExplorationEnabled;
        }
    }

    public boolean isHighTextContrastEnabled() {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return false;
            }
            return this.mIsHighTextContrastEnabled;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0096, code lost:
        if (r8 == r2) goto L_0x006d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0099, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendAccessibilityEvent(android.view.accessibility.AccessibilityEvent r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            android.view.accessibility.IAccessibilityManager r1 = r7.getServiceLocked()     // Catch: all -> 0x00a3
            if (r1 != 0) goto L_0x000b
            monitor-exit(r0)     // Catch: all -> 0x00a3
            return
        L_0x000b:
            long r2 = android.os.SystemClock.uptimeMillis()     // Catch: all -> 0x00a3
            r8.setEventTime(r2)     // Catch: all -> 0x00a3
            int r2 = r8.getAction()     // Catch: all -> 0x00a3
            if (r2 != 0) goto L_0x001d
            int r2 = r7.mPerformingAction     // Catch: all -> 0x00a3
            r8.setAction(r2)     // Catch: all -> 0x00a3
        L_0x001d:
            android.view.accessibility.AccessibilityManager$AccessibilityPolicy r2 = r7.mAccessibilityPolicy     // Catch: all -> 0x00a3
            if (r2 == 0) goto L_0x002d
            boolean r3 = r7.mIsEnabled     // Catch: all -> 0x00a3
            int r4 = r7.mRelevantEventTypes     // Catch: all -> 0x00a3
            android.view.accessibility.AccessibilityEvent r2 = r2.onAccessibilityEvent(r8, r3, r4)     // Catch: all -> 0x00a3
            if (r2 != 0) goto L_0x002e
            monitor-exit(r0)     // Catch: all -> 0x00a3
            return
        L_0x002d:
            r2 = r8
        L_0x002e:
            boolean r3 = r7.isEnabled()     // Catch: all -> 0x00a3
            if (r3 != 0) goto L_0x004f
            android.os.Looper r3 = android.os.Looper.myLooper()     // Catch: all -> 0x00a3
            android.os.Looper r4 = android.os.Looper.getMainLooper()     // Catch: all -> 0x00a3
            if (r3 == r4) goto L_0x0047
            java.lang.String r4 = "AccessibilityManager"
            java.lang.String r5 = "AccessibilityEvent sent with accessibility disabled"
            android.util.Log.e(r4, r5)     // Catch: all -> 0x00a3
            monitor-exit(r0)     // Catch: all -> 0x00a3
            return
        L_0x0047:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: all -> 0x00a3
            java.lang.String r5 = "Accessibility off. Did you forget to check that?"
            r4.<init>(r5)     // Catch: all -> 0x00a3
            throw r4     // Catch: all -> 0x00a3
        L_0x004f:
            int r3 = r2.getEventType()     // Catch: all -> 0x00a3
            int r4 = r7.mRelevantEventTypes     // Catch: all -> 0x00a3
            r3 = r3 & r4
            if (r3 != 0) goto L_0x005a
            monitor-exit(r0)     // Catch: all -> 0x00a3
            return
        L_0x005a:
            int r3 = r7.mUserId     // Catch: all -> 0x00a3
            monitor-exit(r0)     // Catch: all -> 0x00a3
            long r4 = android.os.Binder.clearCallingIdentity()     // Catch: all -> 0x0077, RemoteException -> 0x0079
            r1.sendAccessibilityEvent(r2, r3)     // Catch: all -> 0x0071
            android.os.Binder.restoreCallingIdentity(r4)     // Catch: all -> 0x0077, RemoteException -> 0x0079
            if (r8 == r2) goto L_0x006d
        L_0x006a:
            r8.recycle()
        L_0x006d:
            r2.recycle()
            goto L_0x0099
        L_0x0071:
            r0 = move-exception
            android.os.Binder.restoreCallingIdentity(r4)     // Catch: all -> 0x0077, RemoteException -> 0x0079
            throw r0     // Catch: all -> 0x0077, RemoteException -> 0x0079
        L_0x0077:
            r0 = move-exception
            goto L_0x009a
        L_0x0079:
            r0 = move-exception
            java.lang.String r4 = "AccessibilityManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0077
            r5.<init>()     // Catch: all -> 0x0077
            java.lang.String r6 = "Error during sending "
            r5.append(r6)     // Catch: all -> 0x0077
            r5.append(r2)     // Catch: all -> 0x0077
            java.lang.String r6 = " "
            r5.append(r6)     // Catch: all -> 0x0077
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x0077
            android.util.Log.e(r4, r5, r0)     // Catch: all -> 0x0077
            if (r8 == r2) goto L_0x006d
            goto L_0x006a
        L_0x0099:
            return
        L_0x009a:
            if (r8 == r2) goto L_0x009f
            r8.recycle()
        L_0x009f:
            r2.recycle()
            throw r0
        L_0x00a3:
            r1 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x00a3
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.accessibility.AccessibilityManager.sendAccessibilityEvent(android.view.accessibility.AccessibilityEvent):void");
    }

    public void interrupt() {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                if (!isEnabled()) {
                    Looper myLooper = Looper.myLooper();
                    if (myLooper != Looper.getMainLooper()) {
                        Log.e(LOG_TAG, "Interrupt called with accessibility disabled");
                        return;
                    }
                    throw new IllegalStateException("Accessibility off. Did you forget to check that?");
                }
                int userId = this.mUserId;
                try {
                    service.interrupt(userId);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error while requesting interrupt from all services. ", re);
                }
            }
        }
    }

    @Deprecated
    public List<ServiceInfo> getAccessibilityServiceList() {
        List<AccessibilityServiceInfo> infos = getInstalledAccessibilityServiceList();
        List<ServiceInfo> services = new ArrayList<>();
        int infoCount = infos.size();
        for (int i = 0; i < infoCount; i++) {
            AccessibilityServiceInfo info = infos.get(i);
            services.add(info.getResolveInfo().serviceInfo);
        }
        return Collections.unmodifiableList(services);
    }

    public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList() {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return Collections.emptyList();
            }
            int userId = this.mUserId;
            List<AccessibilityServiceInfo> services = null;
            try {
                services = service.getInstalledAccessibilityServiceList(userId);
            } catch (RemoteException re) {
                Log.e(LOG_TAG, "Error while obtaining the installed AccessibilityServices. ", re);
            }
            AccessibilityPolicy accessibilityPolicy = this.mAccessibilityPolicy;
            if (accessibilityPolicy != null) {
                services = accessibilityPolicy.getInstalledAccessibilityServiceList(services);
            }
            if (services != null) {
                return Collections.unmodifiableList(services);
            }
            return Collections.emptyList();
        }
    }

    public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(int feedbackTypeFlags) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return Collections.emptyList();
            }
            int userId = this.mUserId;
            List<AccessibilityServiceInfo> services = null;
            try {
                services = service.getEnabledAccessibilityServiceList(feedbackTypeFlags, userId);
            } catch (RemoteException re) {
                Log.e(LOG_TAG, "Error while obtaining the enabled AccessibilityServices. ", re);
            }
            AccessibilityPolicy accessibilityPolicy = this.mAccessibilityPolicy;
            if (accessibilityPolicy != null) {
                services = accessibilityPolicy.getEnabledAccessibilityServiceList(feedbackTypeFlags, services);
            }
            if (services != null) {
                return Collections.unmodifiableList(services);
            }
            return Collections.emptyList();
        }
    }

    public boolean addAccessibilityStateChangeListener(AccessibilityStateChangeListener listener) {
        addAccessibilityStateChangeListener(listener, null);
        return true;
    }

    public void addAccessibilityStateChangeListener(AccessibilityStateChangeListener listener, Handler handler) {
        synchronized (this.mLock) {
            this.mAccessibilityStateChangeListeners.put(listener, handler == null ? this.mHandler : handler);
        }
    }

    public boolean removeAccessibilityStateChangeListener(AccessibilityStateChangeListener listener) {
        boolean z;
        synchronized (this.mLock) {
            int index = this.mAccessibilityStateChangeListeners.indexOfKey(listener);
            this.mAccessibilityStateChangeListeners.remove(listener);
            z = index >= 0;
        }
        return z;
    }

    public boolean addTouchExplorationStateChangeListener(TouchExplorationStateChangeListener listener) {
        addTouchExplorationStateChangeListener(listener, null);
        return true;
    }

    public void addTouchExplorationStateChangeListener(TouchExplorationStateChangeListener listener, Handler handler) {
        synchronized (this.mLock) {
            this.mTouchExplorationStateChangeListeners.put(listener, handler == null ? this.mHandler : handler);
        }
    }

    public boolean removeTouchExplorationStateChangeListener(TouchExplorationStateChangeListener listener) {
        boolean z;
        synchronized (this.mLock) {
            int index = this.mTouchExplorationStateChangeListeners.indexOfKey(listener);
            this.mTouchExplorationStateChangeListeners.remove(listener);
            z = index >= 0;
        }
        return z;
    }

    public void addAccessibilityServicesStateChangeListener(AccessibilityServicesStateChangeListener listener, Handler handler) {
        synchronized (this.mLock) {
            this.mServicesStateChangeListeners.put(listener, handler == null ? this.mHandler : handler);
        }
    }

    public void removeAccessibilityServicesStateChangeListener(AccessibilityServicesStateChangeListener listener) {
        synchronized (this.mLock) {
            this.mServicesStateChangeListeners.remove(listener);
        }
    }

    public void addAccessibilityRequestPreparer(AccessibilityRequestPreparer preparer) {
        if (this.mRequestPreparerLists == null) {
            this.mRequestPreparerLists = new SparseArray<>(1);
        }
        int id = preparer.getAccessibilityViewId();
        List<AccessibilityRequestPreparer> requestPreparerList = this.mRequestPreparerLists.get(id);
        if (requestPreparerList == null) {
            requestPreparerList = new ArrayList(1);
            this.mRequestPreparerLists.put(id, requestPreparerList);
        }
        requestPreparerList.add(preparer);
    }

    public void removeAccessibilityRequestPreparer(AccessibilityRequestPreparer preparer) {
        int viewId;
        List<AccessibilityRequestPreparer> requestPreparerList;
        if (this.mRequestPreparerLists != null && (requestPreparerList = this.mRequestPreparerLists.get((viewId = preparer.getAccessibilityViewId()))) != null) {
            requestPreparerList.remove(preparer);
            if (requestPreparerList.isEmpty()) {
                this.mRequestPreparerLists.remove(viewId);
            }
        }
    }

    public int getRecommendedTimeoutMillis(int originalTimeout, int uiContentFlags) {
        boolean hasIconsOrText = false;
        boolean hasControls = (uiContentFlags & 4) != 0;
        if (!((uiContentFlags & 1) == 0 && (uiContentFlags & 2) == 0)) {
            hasIconsOrText = true;
        }
        int recommendedTimeout = originalTimeout;
        if (hasControls) {
            recommendedTimeout = Math.max(recommendedTimeout, this.mInteractiveUiTimeout);
        }
        if (hasIconsOrText) {
            return Math.max(recommendedTimeout, this.mNonInteractiveUiTimeout);
        }
        return recommendedTimeout;
    }

    public int getAccessibilityFocusStrokeWidth() {
        int i;
        synchronized (this.mLock) {
            i = this.mFocusStrokeWidth;
        }
        return i;
    }

    public int getAccessibilityFocusColor() {
        int i;
        synchronized (this.mLock) {
            i = this.mFocusColor;
        }
        return i;
    }

    public boolean isAccessibilityTracingEnabled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mIsAccessibilityTracingEnabled;
        }
        return z;
    }

    public List<AccessibilityRequestPreparer> getRequestPreparersForAccessibilityId(int id) {
        SparseArray<List<AccessibilityRequestPreparer>> sparseArray = this.mRequestPreparerLists;
        if (sparseArray == null) {
            return null;
        }
        return sparseArray.get(id);
    }

    public void notifyPerformingAction(int actionId) {
        this.mPerformingAction = actionId;
    }

    public void addHighTextContrastStateChangeListener(HighTextContrastChangeListener listener, Handler handler) {
        synchronized (this.mLock) {
            this.mHighTextContrastStateChangeListeners.put(listener, handler == null ? this.mHandler : handler);
        }
    }

    public void removeHighTextContrastStateChangeListener(HighTextContrastChangeListener listener) {
        synchronized (this.mLock) {
            this.mHighTextContrastStateChangeListeners.remove(listener);
        }
    }

    public void setAccessibilityPolicy(AccessibilityPolicy policy) {
        synchronized (this.mLock) {
            this.mAccessibilityPolicy = policy;
        }
    }

    public boolean isAccessibilityVolumeStreamActive() {
        List<AccessibilityServiceInfo> serviceInfos = getEnabledAccessibilityServiceList(-1);
        for (int i = 0; i < serviceInfos.size(); i++) {
            if ((serviceInfos.get(i).flags & 128) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean sendFingerprintGesture(int keyCode) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return false;
            }
            try {
                return service.sendFingerprintGesture(keyCode);
            } catch (RemoteException e) {
                return false;
            }
        }
    }

    @SystemApi
    public int getAccessibilityWindowId(IBinder windowToken) {
        if (windowToken == null) {
            return -1;
        }
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return -1;
            }
            try {
                return service.getAccessibilityWindowId(windowToken);
            } catch (RemoteException e) {
                return -1;
            }
        }
    }

    public void associateEmbeddedHierarchy(IBinder host, IBinder embedded) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.associateEmbeddedHierarchy(host, embedded);
                } catch (RemoteException e) {
                }
            }
        }
    }

    public void disassociateEmbeddedHierarchy(IBinder token) {
        if (token != null) {
            synchronized (this.mLock) {
                IAccessibilityManager service = getServiceLocked();
                if (service != null) {
                    try {
                        service.disassociateEmbeddedHierarchy(token);
                    } catch (RemoteException e) {
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStateLocked(int stateFlags) {
        boolean accessibilityTracingEnabled = false;
        boolean enabled = (stateFlags & 1) != 0;
        boolean touchExplorationEnabled = (stateFlags & 2) != 0;
        boolean highTextContrastEnabled = (stateFlags & 4) != 0;
        if ((stateFlags & 32) != 0) {
            accessibilityTracingEnabled = true;
        }
        boolean wasEnabled = isEnabled();
        boolean wasTouchExplorationEnabled = this.mIsTouchExplorationEnabled;
        boolean wasHighTextContrastEnabled = this.mIsHighTextContrastEnabled;
        this.mAccessibilityManagerExt.setDirectEnabledState(stateFlags);
        this.mIsEnabled = enabled;
        this.mIsTouchExplorationEnabled = touchExplorationEnabled;
        this.mIsHighTextContrastEnabled = highTextContrastEnabled;
        if (wasEnabled != isEnabled()) {
            notifyAccessibilityStateChanged();
        }
        if (wasTouchExplorationEnabled != touchExplorationEnabled) {
            notifyTouchExplorationStateChanged();
        }
        if (wasHighTextContrastEnabled != highTextContrastEnabled) {
            notifyHighTextContrastStateChanged();
        }
        updateAccessibilityTracingState(accessibilityTracingEnabled);
    }

    public AccessibilityServiceInfo getInstalledServiceInfoWithComponentName(ComponentName componentName) {
        List<AccessibilityServiceInfo> installedServiceInfos = getInstalledAccessibilityServiceList();
        if (installedServiceInfos == null || componentName == null) {
            return null;
        }
        for (int i = 0; i < installedServiceInfos.size(); i++) {
            if (componentName.equals(installedServiceInfos.get(i).getComponentName())) {
                return installedServiceInfos.get(i);
            }
        }
        return null;
    }

    public int addAccessibilityInteractionConnection(IWindow windowToken, IBinder leashToken, String packageName, IAccessibilityInteractionConnection connection) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service == null) {
                return -1;
            }
            int userId = this.mUserId;
            try {
                return service.addAccessibilityInteractionConnection(windowToken, leashToken, connection, packageName, userId);
            } catch (RemoteException re) {
                Log.e(LOG_TAG, "Error while adding an accessibility interaction connection. ", re);
                return -1;
            }
        }
    }

    public void removeAccessibilityInteractionConnection(IWindow windowToken) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.removeAccessibilityInteractionConnection(windowToken);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error while removing an accessibility interaction connection. ", re);
                }
            }
        }
    }

    @SystemApi
    public void performAccessibilityShortcut() {
        performAccessibilityShortcut(null);
    }

    public void performAccessibilityShortcut(String targetName) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.performAccessibilityShortcut(targetName);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error performing accessibility shortcut. ", re);
                }
            }
        }
    }

    @SystemApi
    public void registerSystemAction(RemoteAction action, int actionId) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.registerSystemAction(action, actionId);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error registering system action " + ((Object) action.getTitle()) + " ", re);
                }
            }
        }
    }

    @SystemApi
    public void unregisterSystemAction(int actionId) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.unregisterSystemAction(actionId);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error unregistering system action with actionId " + actionId + " ", re);
                }
            }
        }
    }

    public void notifyAccessibilityButtonClicked(int displayId) {
        notifyAccessibilityButtonClicked(displayId, null);
    }

    public void notifyAccessibilityButtonClicked(int displayId, String targetName) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.notifyAccessibilityButtonClicked(displayId, targetName);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error while dispatching accessibility button click", re);
                }
            }
        }
    }

    public void notifyAccessibilityButtonVisibilityChanged(boolean shown) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.notifyAccessibilityButtonVisibilityChanged(shown);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error while dispatching accessibility button visibility change", re);
                }
            }
        }
    }

    public void setPictureInPictureActionReplacingConnection(IAccessibilityInteractionConnection connection) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.setPictureInPictureActionReplacingConnection(connection);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error setting picture in picture action replacement", re);
                }
            }
        }
    }

    public List<String> getAccessibilityShortcutTargets(int shortcutType) {
        IAccessibilityManager service;
        synchronized (this.mLock) {
            service = getServiceLocked();
        }
        if (service != null) {
            try {
                return service.getAccessibilityShortcutTargets(shortcutType);
            } catch (RemoteException re) {
                re.rethrowFromSystemServer();
            }
        }
        return Collections.emptyList();
    }

    public List<AccessibilityShortcutInfo> getInstalledAccessibilityShortcutListAsUser(Context context, int userId) {
        List<AccessibilityShortcutInfo> shortcutInfos = new ArrayList<>();
        Intent actionMain = new Intent(Intent.ACTION_MAIN);
        actionMain.addCategory(Intent.CATEGORY_ACCESSIBILITY_SHORTCUT_TARGET);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> installedShortcutList = packageManager.queryIntentActivitiesAsUser(actionMain, 819329, userId);
        for (int i = 0; i < installedShortcutList.size(); i++) {
            AccessibilityShortcutInfo shortcutInfo = getShortcutInfo(context, installedShortcutList.get(i));
            if (shortcutInfo != null) {
                shortcutInfos.add(shortcutInfo);
            }
        }
        return shortcutInfos;
    }

    private AccessibilityShortcutInfo getShortcutInfo(Context context, ResolveInfo resolveInfo) {
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        if (activityInfo == null || activityInfo.metaData == null || activityInfo.metaData.getInt(AccessibilityShortcutInfo.META_DATA) == 0) {
            return null;
        }
        try {
            return new AccessibilityShortcutInfo(context, activityInfo);
        } catch (IOException | XmlPullParserException exp) {
            Log.e(LOG_TAG, "Error while initializing AccessibilityShortcutInfo", exp);
            return null;
        }
    }

    public void setWindowMagnificationConnection(IWindowMagnificationConnection connection) {
        synchronized (this.mLock) {
            IAccessibilityManager service = getServiceLocked();
            if (service != null) {
                try {
                    service.setWindowMagnificationConnection(connection);
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error setting window magnfication connection", re);
                }
            }
        }
    }

    private IAccessibilityManager getServiceLocked() {
        if (this.mService == null) {
            tryConnectToServiceLocked(null);
        }
        return this.mService;
    }

    private void tryConnectToServiceLocked(IAccessibilityManager service) {
        if (service == null) {
            IBinder iBinder = ServiceManager.getService(Context.ACCESSIBILITY_SERVICE);
            if (iBinder != null) {
                service = IAccessibilityManager.Stub.asInterface(iBinder);
            } else {
                return;
            }
        }
        try {
            long userStateAndRelevantEvents = service.addClient(this.mClient, this.mUserId);
            setStateLocked(IntPair.first(userStateAndRelevantEvents));
            this.mRelevantEventTypes = IntPair.second(userStateAndRelevantEvents);
            updateUiTimeout(service.getRecommendedTimeoutMillis());
            updateFocusAppearanceLocked(service.getFocusStrokeWidth(), service.getFocusColor());
            this.mService = service;
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "AccessibilityManagerService is dead", re);
        }
    }

    private void notifyAccessibilityStateChanged() {
        synchronized (this.mLock) {
            if (!this.mAccessibilityStateChangeListeners.isEmpty()) {
                final boolean isEnabled = isEnabled();
                ArrayMap<AccessibilityStateChangeListener, Handler> listeners = new ArrayMap<>(this.mAccessibilityStateChangeListeners);
                int numListeners = listeners.size();
                for (int i = 0; i < numListeners; i++) {
                    final AccessibilityStateChangeListener listener = listeners.keyAt(i);
                    listeners.valueAt(i).post(new Runnable() { // from class: android.view.accessibility.AccessibilityManager$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            AccessibilityManager.AccessibilityStateChangeListener.this.onAccessibilityStateChanged(isEnabled);
                        }
                    });
                }
            }
        }
    }

    private void notifyTouchExplorationStateChanged() {
        synchronized (this.mLock) {
            if (!this.mTouchExplorationStateChangeListeners.isEmpty()) {
                final boolean isTouchExplorationEnabled = this.mIsTouchExplorationEnabled;
                ArrayMap<TouchExplorationStateChangeListener, Handler> listeners = new ArrayMap<>(this.mTouchExplorationStateChangeListeners);
                int numListeners = listeners.size();
                for (int i = 0; i < numListeners; i++) {
                    final TouchExplorationStateChangeListener listener = listeners.keyAt(i);
                    listeners.valueAt(i).post(new Runnable() { // from class: android.view.accessibility.AccessibilityManager$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            AccessibilityManager.TouchExplorationStateChangeListener.this.onTouchExplorationStateChanged(isTouchExplorationEnabled);
                        }
                    });
                }
            }
        }
    }

    private void notifyHighTextContrastStateChanged() {
        synchronized (this.mLock) {
            if (!this.mHighTextContrastStateChangeListeners.isEmpty()) {
                final boolean isHighTextContrastEnabled = this.mIsHighTextContrastEnabled;
                ArrayMap<HighTextContrastChangeListener, Handler> listeners = new ArrayMap<>(this.mHighTextContrastStateChangeListeners);
                int numListeners = listeners.size();
                for (int i = 0; i < numListeners; i++) {
                    final HighTextContrastChangeListener listener = listeners.keyAt(i);
                    listeners.valueAt(i).post(new Runnable() { // from class: android.view.accessibility.AccessibilityManager$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            AccessibilityManager.HighTextContrastChangeListener.this.onHighTextContrastStateChanged(isHighTextContrastEnabled);
                        }
                    });
                }
            }
        }
    }

    private void updateAccessibilityTracingState(boolean enabled) {
        synchronized (this.mLock) {
            this.mIsAccessibilityTracingEnabled = enabled;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUiTimeout(long uiTimeout) {
        this.mInteractiveUiTimeout = IntPair.first(uiTimeout);
        this.mNonInteractiveUiTimeout = IntPair.second(uiTimeout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFocusAppearanceLocked(int strokeWidth, int color) {
        if (this.mFocusStrokeWidth != strokeWidth || this.mFocusColor != color) {
            this.mFocusStrokeWidth = strokeWidth;
            this.mFocusColor = color;
        }
    }

    private void initialFocusAppearanceLocked(Resources resource) {
        try {
            this.mFocusStrokeWidth = resource.getDimensionPixelSize(R.dimen.accessibility_focus_highlight_stroke_width);
            this.mFocusColor = resource.getColor(R.color.accessibility_focus_highlight_color);
        } catch (Resources.NotFoundException re) {
            this.mFocusStrokeWidth = (int) (resource.getDisplayMetrics().density * 4.0f);
            this.mFocusColor = -1086737152;
            Log.e(LOG_TAG, "Error while initialing the focus appearance data then setting to default value by hardcoded", re);
        }
    }

    public static boolean isAccessibilityButtonSupported() {
        Resources res = Resources.getSystem();
        return res.getBoolean(R.bool.config_showNavigationBar);
    }

    /* loaded from: classes3.dex */
    private final class MyCallback implements Handler.Callback {
        public static final int MSG_SET_STATE = 1;

        private MyCallback() {
        }

        /* synthetic */ MyCallback(AccessibilityManager x0, AnonymousClass1 x1) {
            this();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    int state = message.arg1;
                    synchronized (AccessibilityManager.this.mLock) {
                        AccessibilityManager.this.setStateLocked(state);
                    }
                    return true;
                default:
                    return true;
            }
        }
    }
}
