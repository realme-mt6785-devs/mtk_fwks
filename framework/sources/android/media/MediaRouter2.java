package android.media;

import android.annotation.SystemApi;
import android.app.job.JobInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.IMediaRouter2;
import android.media.IMediaRouterService;
import android.media.MediaRouter2;
import android.media.MediaRouter2Manager;
import android.media.RouteDiscoveryPreference;
import android.media.RoutingSessionInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/* loaded from: classes2.dex */
public final class MediaRouter2 {
    private static final long MANAGER_REQUEST_ID_NONE = 0;
    private static final int TRANSFER_TIMEOUT_MS = 30000;
    private static MediaRouter2 sInstance;
    private static MediaRouter2Manager sManager;
    private final String mClientPackageName;
    private final Context mContext;
    private final CopyOnWriteArrayList<ControllerCallbackRecord> mControllerCallbackRecords;
    private final CopyOnWriteArrayList<ControllerCreationRequest> mControllerCreationRequests;
    private RouteDiscoveryPreference mDiscoveryPreference;
    private volatile List<MediaRoute2Info> mFilteredRoutes;
    final Handler mHandler;
    private final Object mLock;
    final ManagerCallback mManagerCallback;
    private final IMediaRouterService mMediaRouterService;
    private final AtomicInteger mNextRequestId;
    private final Map<String, RoutingController> mNonSystemRoutingControllers;
    private volatile OnGetControllerHintsListener mOnGetControllerHintsListener;
    private final String mPackageName;
    private final CopyOnWriteArrayList<RouteCallbackRecord> mRouteCallbackRecords;
    final Map<String, MediaRoute2Info> mRoutes;
    private boolean mShouldUpdateRoutes;
    MediaRouter2Stub mStub;
    final RoutingController mSystemController;
    private final CopyOnWriteArrayList<TransferCallbackRecord> mTransferCallbackRecords;
    private static final String TAG = "MR2";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final Object sSystemRouterLock = new Object();
    private static final Object sRouterLock = new Object();
    private static Map<String, MediaRouter2> sSystemMediaRouter2Map = new ArrayMap();

    /* loaded from: classes2.dex */
    public interface OnGetControllerHintsListener {
        Bundle onGetControllerHints(MediaRoute2Info mediaRoute2Info);
    }

    public static MediaRouter2 getInstance(Context context) {
        MediaRouter2 mediaRouter2;
        Objects.requireNonNull(context, "context must not be null");
        synchronized (sRouterLock) {
            if (sInstance == null) {
                sInstance = new MediaRouter2(context.getApplicationContext());
            }
            mediaRouter2 = sInstance;
        }
        return mediaRouter2;
    }

    @SystemApi
    public static MediaRouter2 getInstance(Context context, String clientPackageName) {
        MediaRouter2 instance;
        Objects.requireNonNull(context, "context must not be null");
        Objects.requireNonNull(clientPackageName, "clientPackageName must not be null");
        IMediaRouterService serviceBinder = IMediaRouterService.Stub.asInterface(ServiceManager.getService(Context.MEDIA_ROUTER_SERVICE));
        try {
            serviceBinder.enforceMediaContentControlPermission();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(clientPackageName, 0);
            synchronized (sSystemRouterLock) {
                instance = sSystemMediaRouter2Map.get(clientPackageName);
                if (instance == null) {
                    if (sManager == null) {
                        sManager = MediaRouter2Manager.getInstance(context.getApplicationContext());
                    }
                    instance = new MediaRouter2(context, clientPackageName);
                    sSystemMediaRouter2Map.put(clientPackageName, instance);
                    sManager.registerCallback(MediaRouter2$$ExternalSyntheticLambda8.INSTANCE, instance.mManagerCallback);
                }
            }
            return instance;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "Package " + clientPackageName + " not found. Ignoring.");
            return null;
        }
    }

    @SystemApi
    public void startScan() {
        if (isSystemRouter()) {
            sManager.startScan();
        }
    }

    @SystemApi
    public void stopScan() {
        if (isSystemRouter()) {
            sManager.stopScan();
        }
    }

    private MediaRouter2(Context appContext) {
        this.mLock = new Object();
        this.mRouteCallbackRecords = new CopyOnWriteArrayList<>();
        this.mTransferCallbackRecords = new CopyOnWriteArrayList<>();
        this.mControllerCallbackRecords = new CopyOnWriteArrayList<>();
        this.mControllerCreationRequests = new CopyOnWriteArrayList<>();
        this.mRoutes = new ArrayMap();
        this.mDiscoveryPreference = RouteDiscoveryPreference.EMPTY;
        this.mNonSystemRoutingControllers = new ArrayMap();
        this.mNextRequestId = new AtomicInteger(1);
        this.mShouldUpdateRoutes = true;
        this.mFilteredRoutes = Collections.emptyList();
        this.mContext = appContext;
        IMediaRouterService asInterface = IMediaRouterService.Stub.asInterface(ServiceManager.getService(Context.MEDIA_ROUTER_SERVICE));
        this.mMediaRouterService = asInterface;
        this.mPackageName = appContext.getPackageName();
        this.mHandler = new Handler(Looper.getMainLooper());
        List<MediaRoute2Info> currentSystemRoutes = null;
        RoutingSessionInfo currentSystemSessionInfo = null;
        try {
            currentSystemRoutes = asInterface.getSystemRoutes();
            currentSystemSessionInfo = asInterface.getSystemSessionInfo();
        } catch (RemoteException ex) {
            Log.e(TAG, "Unable to get current system's routes / session info", ex);
        }
        if (currentSystemRoutes == null || currentSystemRoutes.isEmpty()) {
            throw new RuntimeException("Null or empty currentSystemRoutes. Something is wrong.");
        } else if (currentSystemSessionInfo != null) {
            for (MediaRoute2Info route : currentSystemRoutes) {
                this.mRoutes.put(route.getId(), route);
            }
            this.mSystemController = new SystemRoutingController(currentSystemSessionInfo);
            this.mClientPackageName = null;
            this.mManagerCallback = null;
        } else {
            throw new RuntimeException("Null currentSystemSessionInfo. Something is wrong.");
        }
    }

    private MediaRouter2(Context context, String clientPackageName) {
        this.mLock = new Object();
        this.mRouteCallbackRecords = new CopyOnWriteArrayList<>();
        this.mTransferCallbackRecords = new CopyOnWriteArrayList<>();
        this.mControllerCallbackRecords = new CopyOnWriteArrayList<>();
        this.mControllerCreationRequests = new CopyOnWriteArrayList<>();
        this.mRoutes = new ArrayMap();
        this.mDiscoveryPreference = RouteDiscoveryPreference.EMPTY;
        this.mNonSystemRoutingControllers = new ArrayMap();
        this.mNextRequestId = new AtomicInteger(1);
        this.mShouldUpdateRoutes = true;
        this.mFilteredRoutes = Collections.emptyList();
        this.mContext = context;
        this.mClientPackageName = clientPackageName;
        this.mManagerCallback = new ManagerCallback();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mSystemController = new SystemRoutingController(ensureClientPackageNameForSystemSession(sManager.getSystemRoutingSession()));
        this.mDiscoveryPreference = new RouteDiscoveryPreference.Builder(sManager.getPreferredFeatures(clientPackageName), true).build();
        updateAllRoutesFromManager();
        this.mMediaRouterService = null;
        this.mPackageName = null;
    }

    static boolean checkRouteListContainsRouteId(List<MediaRoute2Info> routeList, String routeId) {
        for (MediaRoute2Info info : routeList) {
            if (TextUtils.equals(routeId, info.getId())) {
                return true;
            }
        }
        return false;
    }

    @SystemApi
    public String getClientPackageName() {
        return this.mClientPackageName;
    }

    public void registerRouteCallback(Executor executor, RouteCallback routeCallback, RouteDiscoveryPreference preference) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(routeCallback, "callback must not be null");
        Objects.requireNonNull(preference, "preference must not be null");
        if (isSystemRouter()) {
            preference = RouteDiscoveryPreference.EMPTY;
        }
        RouteCallbackRecord record = new RouteCallbackRecord(executor, routeCallback, preference);
        this.mRouteCallbackRecords.remove(record);
        this.mRouteCallbackRecords.addIfAbsent(record);
        if (!isSystemRouter()) {
            synchronized (this.mLock) {
                if (this.mStub == null) {
                    MediaRouter2Stub stub = new MediaRouter2Stub();
                    try {
                        this.mMediaRouterService.registerRouter2(stub, this.mPackageName);
                        this.mStub = stub;
                    } catch (RemoteException ex) {
                        Log.e(TAG, "registerRouteCallback: Unable to register MediaRouter2.", ex);
                    }
                }
                if (this.mStub != null && updateDiscoveryPreferenceIfNeededLocked()) {
                    try {
                        this.mMediaRouterService.setDiscoveryRequestWithRouter2(this.mStub, this.mDiscoveryPreference);
                    } catch (RemoteException ex2) {
                        Log.e(TAG, "registerRouteCallback: Unable to set discovery request.", ex2);
                    }
                }
            }
        }
    }

    public void unregisterRouteCallback(RouteCallback routeCallback) {
        Objects.requireNonNull(routeCallback, "callback must not be null");
        if (!this.mRouteCallbackRecords.remove(new RouteCallbackRecord(null, routeCallback, null))) {
            Log.w(TAG, "unregisterRouteCallback: Ignoring unknown callback");
        } else if (!isSystemRouter()) {
            synchronized (this.mLock) {
                if (this.mStub != null) {
                    if (updateDiscoveryPreferenceIfNeededLocked()) {
                        try {
                            this.mMediaRouterService.setDiscoveryRequestWithRouter2(this.mStub, this.mDiscoveryPreference);
                        } catch (RemoteException ex) {
                            Log.e(TAG, "unregisterRouteCallback: Unable to set discovery request.", ex);
                        }
                    }
                    if (this.mRouteCallbackRecords.isEmpty() && this.mNonSystemRoutingControllers.isEmpty()) {
                        try {
                            this.mMediaRouterService.unregisterRouter2(this.mStub);
                        } catch (RemoteException ex2) {
                            Log.e(TAG, "Unable to unregister media router.", ex2);
                        }
                        this.mStub = null;
                    }
                    this.mShouldUpdateRoutes = true;
                }
            }
        }
    }

    private boolean updateDiscoveryPreferenceIfNeededLocked() {
        RouteDiscoveryPreference newDiscoveryPreference = new RouteDiscoveryPreference.Builder((Collection) this.mRouteCallbackRecords.stream().map(MediaRouter2$$ExternalSyntheticLambda10.INSTANCE).collect(Collectors.toList())).build();
        if (Objects.equals(this.mDiscoveryPreference, newDiscoveryPreference)) {
            return false;
        }
        this.mDiscoveryPreference = newDiscoveryPreference;
        this.mShouldUpdateRoutes = true;
        return true;
    }

    @SystemApi
    public List<MediaRoute2Info> getAllRoutes() {
        if (isSystemRouter()) {
            return sManager.getAllRoutes();
        }
        return Collections.emptyList();
    }

    public List<MediaRoute2Info> getRoutes() {
        synchronized (this.mLock) {
            if (this.mShouldUpdateRoutes) {
                this.mShouldUpdateRoutes = false;
                List<MediaRoute2Info> filteredRoutes = new ArrayList<>();
                for (MediaRoute2Info route : this.mRoutes.values()) {
                    if (route.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                        filteredRoutes.add(route);
                    }
                }
                this.mFilteredRoutes = Collections.unmodifiableList(filteredRoutes);
            }
        }
        return this.mFilteredRoutes;
    }

    public void registerTransferCallback(Executor executor, TransferCallback callback) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(callback, "callback must not be null");
        TransferCallbackRecord record = new TransferCallbackRecord(executor, callback);
        if (!this.mTransferCallbackRecords.addIfAbsent(record)) {
            Log.w(TAG, "registerTransferCallback: Ignoring the same callback");
        }
    }

    public void unregisterTransferCallback(TransferCallback callback) {
        Objects.requireNonNull(callback, "callback must not be null");
        if (!this.mTransferCallbackRecords.remove(new TransferCallbackRecord(null, callback))) {
            Log.w(TAG, "unregisterTransferCallback: Ignoring an unknown callback");
        }
    }

    public void registerControllerCallback(Executor executor, ControllerCallback callback) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(callback, "callback must not be null");
        ControllerCallbackRecord record = new ControllerCallbackRecord(executor, callback);
        if (!this.mControllerCallbackRecords.addIfAbsent(record)) {
            Log.w(TAG, "registerControllerCallback: Ignoring the same callback");
        }
    }

    public void unregisterControllerCallback(ControllerCallback callback) {
        Objects.requireNonNull(callback, "callback must not be null");
        if (!this.mControllerCallbackRecords.remove(new ControllerCallbackRecord(null, callback))) {
            Log.w(TAG, "unregisterControllerCallback: Ignoring an unknown callback");
        }
    }

    public void setOnGetControllerHintsListener(OnGetControllerHintsListener listener) {
        if (!isSystemRouter()) {
            this.mOnGetControllerHintsListener = listener;
        }
    }

    public void transferTo(MediaRoute2Info route) {
        boolean routeFound;
        if (isSystemRouter()) {
            sManager.selectRoute(this.mClientPackageName, route);
            return;
        }
        Log.v(TAG, "Transferring to route: " + route);
        synchronized (this.mLock) {
            routeFound = this.mRoutes.containsKey(route.getId());
        }
        if (!routeFound) {
            notifyTransferFailure(route);
            return;
        }
        RoutingController controller = getCurrentController();
        if (controller.getRoutingSessionInfo().getTransferableRoutes().contains(route.getId())) {
            controller.transferToRoute(route);
        } else {
            requestCreateController(controller, route, 0L);
        }
    }

    public void stop() {
        if (isSystemRouter()) {
            List<RoutingSessionInfo> sessionInfos = sManager.getRoutingSessions(this.mClientPackageName);
            RoutingSessionInfo sessionToRelease = sessionInfos.get(sessionInfos.size() - 1);
            sManager.releaseSession(sessionToRelease);
            return;
        }
        getCurrentController().release();
    }

    @SystemApi
    public void transfer(RoutingController controller, MediaRoute2Info route) {
        if (isSystemRouter()) {
            sManager.transfer(controller.getRoutingSessionInfo(), route);
        }
    }

    void requestCreateController(RoutingController controller, MediaRoute2Info route, long managerRequestId) {
        Bundle controllerHints;
        MediaRouter2Stub stub;
        int requestId = this.mNextRequestId.getAndIncrement();
        ControllerCreationRequest request = new ControllerCreationRequest(requestId, managerRequestId, route, controller);
        this.mControllerCreationRequests.add(request);
        OnGetControllerHintsListener listener = this.mOnGetControllerHintsListener;
        if (listener != null) {
            Bundle controllerHints2 = listener.onGetControllerHints(route);
            if (controllerHints2 != null) {
                controllerHints = new Bundle(controllerHints2);
            } else {
                controllerHints = controllerHints2;
            }
        } else {
            controllerHints = null;
        }
        synchronized (this.mLock) {
            stub = this.mStub;
        }
        if (stub != null) {
            try {
                this.mMediaRouterService.requestCreateSessionWithRouter2(stub, requestId, managerRequestId, controller.getRoutingSessionInfo(), route, controllerHints);
            } catch (RemoteException ex) {
                Log.e(TAG, "createControllerForTransfer: Failed to request for creating a controller.", ex);
                this.mControllerCreationRequests.remove(request);
                if (managerRequestId == 0) {
                    notifyTransferFailure(route);
                }
            }
        }
    }

    private RoutingController getCurrentController() {
        List<RoutingController> controllers = getControllers();
        return controllers.get(controllers.size() - 1);
    }

    public RoutingController getSystemController() {
        return this.mSystemController;
    }

    public RoutingController getController(String id) {
        Objects.requireNonNull(id, "id must not be null");
        for (RoutingController controller : getControllers()) {
            if (TextUtils.equals(id, controller.getId())) {
                return controller;
            }
        }
        return null;
    }

    public List<RoutingController> getControllers() {
        RoutingController controller;
        List<RoutingController> result = new ArrayList<>();
        if (isSystemRouter()) {
            List<RoutingSessionInfo> sessions = sManager.getRoutingSessions(this.mClientPackageName);
            for (RoutingSessionInfo session : sessions) {
                if (session.isSystemSession()) {
                    this.mSystemController.setRoutingSessionInfo(ensureClientPackageNameForSystemSession(session));
                    controller = this.mSystemController;
                } else {
                    controller = new RoutingController(session);
                }
                result.add(controller);
            }
            return result;
        }
        result.add(0, this.mSystemController);
        synchronized (this.mLock) {
            result.addAll(this.mNonSystemRoutingControllers.values());
        }
        return result;
    }

    @SystemApi
    public void setRouteVolume(MediaRoute2Info route, int volume) {
        Objects.requireNonNull(route, "route must not be null");
        if (isSystemRouter()) {
            sManager.setRouteVolume(route, volume);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void syncRoutesOnHandler(List<MediaRoute2Info> currentRoutes, RoutingSessionInfo currentSystemSessionInfo) {
        if (currentRoutes == null || currentRoutes.isEmpty() || currentSystemSessionInfo == null) {
            Log.e(TAG, "syncRoutesOnHandler: Received wrong data. currentRoutes=" + currentRoutes + ", currentSystemSessionInfo=" + currentSystemSessionInfo);
            return;
        }
        List<MediaRoute2Info> addedRoutes = new ArrayList<>();
        List<MediaRoute2Info> removedRoutes = new ArrayList<>();
        List<MediaRoute2Info> changedRoutes = new ArrayList<>();
        synchronized (this.mLock) {
            List<String> currentRoutesIds = (List) currentRoutes.stream().map(MediaRouter2$$ExternalSyntheticLambda9.INSTANCE).collect(Collectors.toList());
            for (String routeId : this.mRoutes.keySet()) {
                if (!currentRoutesIds.contains(routeId) && this.mRoutes.get(routeId).hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    removedRoutes.add(this.mRoutes.get(routeId));
                }
            }
            for (MediaRoute2Info route : currentRoutes) {
                if (this.mRoutes.containsKey(route.getId())) {
                    if (!route.equals(this.mRoutes.get(route.getId())) && route.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                        changedRoutes.add(route);
                    }
                } else if (route.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    addedRoutes.add(route);
                }
            }
            this.mRoutes.clear();
            for (MediaRoute2Info route2 : currentRoutes) {
                this.mRoutes.put(route2.getId(), route2);
            }
            this.mShouldUpdateRoutes = true;
        }
        if (!addedRoutes.isEmpty()) {
            notifyRoutesAdded(addedRoutes);
        }
        if (!removedRoutes.isEmpty()) {
            notifyRoutesRemoved(removedRoutes);
        }
        if (!changedRoutes.isEmpty()) {
            notifyRoutesChanged(changedRoutes);
        }
        RoutingSessionInfo oldInfo = this.mSystemController.getRoutingSessionInfo();
        this.mSystemController.setRoutingSessionInfo(currentSystemSessionInfo);
        if (!oldInfo.equals(currentSystemSessionInfo)) {
            notifyControllerUpdated(this.mSystemController);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addRoutesOnHandler(List<MediaRoute2Info> routes) {
        List<MediaRoute2Info> addedRoutes = new ArrayList<>();
        synchronized (this.mLock) {
            for (MediaRoute2Info route : routes) {
                this.mRoutes.put(route.getId(), route);
                if (route.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    addedRoutes.add(route);
                }
            }
            this.mShouldUpdateRoutes = true;
        }
        if (!addedRoutes.isEmpty()) {
            notifyRoutesAdded(addedRoutes);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeRoutesOnHandler(List<MediaRoute2Info> routes) {
        List<MediaRoute2Info> removedRoutes = new ArrayList<>();
        synchronized (this.mLock) {
            for (MediaRoute2Info route : routes) {
                this.mRoutes.remove(route.getId());
                if (route.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    removedRoutes.add(route);
                }
            }
            this.mShouldUpdateRoutes = true;
        }
        if (!removedRoutes.isEmpty()) {
            notifyRoutesRemoved(removedRoutes);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void changeRoutesOnHandler(List<MediaRoute2Info> routes) {
        List<MediaRoute2Info> changedRoutes = new ArrayList<>();
        synchronized (this.mLock) {
            for (MediaRoute2Info route : routes) {
                this.mRoutes.put(route.getId(), route);
                if (route.hasAnyFeatures(this.mDiscoveryPreference.getPreferredFeatures())) {
                    changedRoutes.add(route);
                }
            }
            this.mShouldUpdateRoutes = true;
        }
        if (!changedRoutes.isEmpty()) {
            notifyRoutesChanged(changedRoutes);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createControllerOnHandler(int requestId, RoutingSessionInfo sessionInfo) {
        RoutingController newController;
        ControllerCreationRequest matchingRequest = null;
        Iterator<ControllerCreationRequest> it = this.mControllerCreationRequests.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ControllerCreationRequest request = it.next();
            if (request.mRequestId == requestId) {
                matchingRequest = request;
                break;
            }
        }
        if (matchingRequest == null) {
            Log.w(TAG, "createControllerOnHandler: Ignoring an unknown request.");
            return;
        }
        this.mControllerCreationRequests.remove(matchingRequest);
        MediaRoute2Info requestedRoute = matchingRequest.mRoute;
        if (sessionInfo == null) {
            notifyTransferFailure(requestedRoute);
        } else if (!TextUtils.equals(requestedRoute.getProviderId(), sessionInfo.getProviderId())) {
            Log.w(TAG, "The session's provider ID does not match the requested route's. (requested route's providerId=" + requestedRoute.getProviderId() + ", actual providerId=" + sessionInfo.getProviderId() + ")");
            notifyTransferFailure(requestedRoute);
        } else {
            RoutingController oldController = matchingRequest.mOldController;
            if (!oldController.scheduleRelease()) {
                Log.w(TAG, "createControllerOnHandler: Ignoring controller creation for released old controller. oldController=" + oldController);
                if (!sessionInfo.isSystemSession()) {
                    new RoutingController(sessionInfo).release();
                }
                notifyTransferFailure(requestedRoute);
                return;
            }
            if (sessionInfo.isSystemSession()) {
                newController = getSystemController();
                newController.setRoutingSessionInfo(sessionInfo);
            } else {
                newController = new RoutingController(sessionInfo);
                synchronized (this.mLock) {
                    this.mNonSystemRoutingControllers.put(newController.getId(), newController);
                }
            }
            notifyTransfer(oldController, newController);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateControllerOnHandler(RoutingSessionInfo sessionInfo) {
        RoutingController matchingController;
        if (sessionInfo == null) {
            Log.w(TAG, "updateControllerOnHandler: Ignoring null sessionInfo.");
        } else if (sessionInfo.isSystemSession()) {
            RoutingController systemController = getSystemController();
            systemController.setRoutingSessionInfo(sessionInfo);
            notifyControllerUpdated(systemController);
        } else {
            synchronized (this.mLock) {
                matchingController = this.mNonSystemRoutingControllers.get(sessionInfo.getId());
            }
            if (matchingController == null) {
                Log.w(TAG, "updateControllerOnHandler: Matching controller not found. uniqueSessionId=" + sessionInfo.getId());
                return;
            }
            RoutingSessionInfo oldInfo = matchingController.getRoutingSessionInfo();
            if (!TextUtils.equals(oldInfo.getProviderId(), sessionInfo.getProviderId())) {
                Log.w(TAG, "updateControllerOnHandler: Provider IDs are not matched. old=" + oldInfo.getProviderId() + ", new=" + sessionInfo.getProviderId());
                return;
            }
            matchingController.setRoutingSessionInfo(sessionInfo);
            notifyControllerUpdated(matchingController);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseControllerOnHandler(RoutingSessionInfo sessionInfo) {
        RoutingController matchingController;
        if (sessionInfo == null) {
            Log.w(TAG, "releaseControllerOnHandler: Ignoring null sessionInfo.");
            return;
        }
        synchronized (this.mLock) {
            matchingController = this.mNonSystemRoutingControllers.get(sessionInfo.getId());
        }
        if (matchingController != null) {
            RoutingSessionInfo oldInfo = matchingController.getRoutingSessionInfo();
            if (!TextUtils.equals(oldInfo.getProviderId(), sessionInfo.getProviderId())) {
                Log.w(TAG, "releaseControllerOnHandler: Provider IDs are not matched. old=" + oldInfo.getProviderId() + ", new=" + sessionInfo.getProviderId());
                return;
            }
            matchingController.releaseInternal(false);
        } else if (DEBUG) {
            Log.d(TAG, "releaseControllerOnHandler: Matching controller not found. uniqueSessionId=" + sessionInfo.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onRequestCreateControllerByManagerOnHandler(RoutingSessionInfo oldSession, MediaRoute2Info route, long managerRequestId) {
        RoutingController controller;
        RoutingController controller2;
        if (oldSession.isSystemSession()) {
            controller = getSystemController();
        } else {
            synchronized (this.mLock) {
                controller2 = this.mNonSystemRoutingControllers.get(oldSession.getId());
            }
            controller = controller2;
        }
        if (controller != null) {
            requestCreateController(controller, route, managerRequestId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSystemRouter() {
        return this.mClientPackageName != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RoutingSessionInfo ensureClientPackageNameForSystemSession(RoutingSessionInfo sessionInfo) {
        if (!sessionInfo.isSystemSession() || !TextUtils.isEmpty(sessionInfo.getClientPackageName())) {
            return sessionInfo;
        }
        return new RoutingSessionInfo.Builder(sessionInfo).setClientPackageName(this.mClientPackageName).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MediaRoute2Info> filterRoutes(List<MediaRoute2Info> routes, final RouteDiscoveryPreference discoveryRequest) {
        return (List) routes.stream().filter(new Predicate() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean hasAnyFeatures;
                hasAnyFeatures = ((MediaRoute2Info) obj).hasAnyFeatures(RouteDiscoveryPreference.this.getPreferredFeatures());
                return hasAnyFeatures;
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllRoutesFromManager() {
        if (isSystemRouter()) {
            synchronized (this.mLock) {
                this.mRoutes.clear();
                for (MediaRoute2Info route : sManager.getAllRoutes()) {
                    this.mRoutes.put(route.getId(), route);
                }
                this.mShouldUpdateRoutes = true;
            }
        }
    }

    private void notifyRoutesAdded(List<MediaRoute2Info> routes) {
        Iterator<RouteCallbackRecord> it = this.mRouteCallbackRecords.iterator();
        while (it.hasNext()) {
            final RouteCallbackRecord record = it.next();
            final List<MediaRoute2Info> filteredRoutes = filterRoutes(routes, record.mPreference);
            if (!filteredRoutes.isEmpty()) {
                record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaRouter2.RouteCallbackRecord.this.mRouteCallback.onRoutesAdded(filteredRoutes);
                    }
                });
            }
        }
    }

    private void notifyRoutesRemoved(List<MediaRoute2Info> routes) {
        Iterator<RouteCallbackRecord> it = this.mRouteCallbackRecords.iterator();
        while (it.hasNext()) {
            final RouteCallbackRecord record = it.next();
            final List<MediaRoute2Info> filteredRoutes = filterRoutes(routes, record.mPreference);
            if (!filteredRoutes.isEmpty()) {
                record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaRouter2.RouteCallbackRecord.this.mRouteCallback.onRoutesRemoved(filteredRoutes);
                    }
                });
            }
        }
    }

    private void notifyRoutesChanged(List<MediaRoute2Info> routes) {
        Iterator<RouteCallbackRecord> it = this.mRouteCallbackRecords.iterator();
        while (it.hasNext()) {
            final RouteCallbackRecord record = it.next();
            final List<MediaRoute2Info> filteredRoutes = filterRoutes(routes, record.mPreference);
            if (!filteredRoutes.isEmpty()) {
                record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaRouter2.RouteCallbackRecord.this.mRouteCallback.onRoutesChanged(filteredRoutes);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPreferredFeaturesChanged(final List<String> features) {
        Iterator<RouteCallbackRecord> it = this.mRouteCallbackRecords.iterator();
        while (it.hasNext()) {
            final RouteCallbackRecord record = it.next();
            record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    MediaRouter2.RouteCallbackRecord.this.mRouteCallback.onPreferredFeaturesChanged(features);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTransfer(final RoutingController oldController, final RoutingController newController) {
        Iterator<TransferCallbackRecord> it = this.mTransferCallbackRecords.iterator();
        while (it.hasNext()) {
            final TransferCallbackRecord record = it.next();
            record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    MediaRouter2.TransferCallbackRecord.this.mTransferCallback.onTransfer(oldController, newController);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTransferFailure(final MediaRoute2Info route) {
        Iterator<TransferCallbackRecord> it = this.mTransferCallbackRecords.iterator();
        while (it.hasNext()) {
            final TransferCallbackRecord record = it.next();
            record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    MediaRouter2.TransferCallbackRecord.this.mTransferCallback.onTransferFailure(route);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStop(final RoutingController controller) {
        Iterator<TransferCallbackRecord> it = this.mTransferCallbackRecords.iterator();
        while (it.hasNext()) {
            final TransferCallbackRecord record = it.next();
            record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    MediaRouter2.TransferCallbackRecord.this.mTransferCallback.onStop(controller);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyControllerUpdated(final RoutingController controller) {
        Iterator<ControllerCallbackRecord> it = this.mControllerCallbackRecords.iterator();
        while (it.hasNext()) {
            final ControllerCallbackRecord record = it.next();
            record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MediaRouter2.ControllerCallbackRecord.this.mCallback.onControllerUpdated(controller);
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class RouteCallback {
        public void onRoutesAdded(List<MediaRoute2Info> routes) {
        }

        public void onRoutesRemoved(List<MediaRoute2Info> routes) {
        }

        public void onRoutesChanged(List<MediaRoute2Info> routes) {
        }

        @SystemApi
        public void onPreferredFeaturesChanged(List<String> preferredFeatures) {
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class TransferCallback {
        public void onTransfer(RoutingController oldController, RoutingController newController) {
        }

        public void onTransferFailure(MediaRoute2Info requestedRoute) {
        }

        public void onStop(RoutingController controller) {
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class ControllerCallback {
        public void onControllerUpdated(RoutingController controller) {
        }
    }

    /* loaded from: classes2.dex */
    public class RoutingController {
        private static final int CONTROLLER_STATE_ACTIVE = 1;
        private static final int CONTROLLER_STATE_RELEASED = 3;
        private static final int CONTROLLER_STATE_RELEASING = 2;
        private static final int CONTROLLER_STATE_UNKNOWN = 0;
        private final Object mControllerLock;
        private RoutingSessionInfo mSessionInfo;
        private int mState;

        RoutingController(RoutingSessionInfo sessionInfo) {
            this.mControllerLock = new Object();
            this.mSessionInfo = sessionInfo;
            this.mState = 1;
        }

        RoutingController(RoutingSessionInfo sessionInfo, int state) {
            this.mControllerLock = new Object();
            this.mSessionInfo = sessionInfo;
            this.mState = state;
        }

        public String getId() {
            String id;
            synchronized (this.mControllerLock) {
                id = this.mSessionInfo.getId();
            }
            return id;
        }

        public String getOriginalId() {
            String originalId;
            synchronized (this.mControllerLock) {
                originalId = this.mSessionInfo.getOriginalId();
            }
            return originalId;
        }

        public Bundle getControlHints() {
            Bundle controlHints;
            synchronized (this.mControllerLock) {
                controlHints = this.mSessionInfo.getControlHints();
            }
            return controlHints;
        }

        public List<MediaRoute2Info> getSelectedRoutes() {
            List<String> selectedRouteIds;
            synchronized (this.mControllerLock) {
                selectedRouteIds = this.mSessionInfo.getSelectedRoutes();
            }
            return getRoutesWithIds(selectedRouteIds);
        }

        public List<MediaRoute2Info> getSelectableRoutes() {
            List<String> selectableRouteIds;
            synchronized (this.mControllerLock) {
                selectableRouteIds = this.mSessionInfo.getSelectableRoutes();
            }
            return getRoutesWithIds(selectableRouteIds);
        }

        public List<MediaRoute2Info> getDeselectableRoutes() {
            List<String> deselectableRouteIds;
            synchronized (this.mControllerLock) {
                deselectableRouteIds = this.mSessionInfo.getDeselectableRoutes();
            }
            return getRoutesWithIds(deselectableRouteIds);
        }

        public int getVolumeHandling() {
            int volumeHandling;
            synchronized (this.mControllerLock) {
                volumeHandling = this.mSessionInfo.getVolumeHandling();
            }
            return volumeHandling;
        }

        public int getVolumeMax() {
            int volumeMax;
            synchronized (this.mControllerLock) {
                volumeMax = this.mSessionInfo.getVolumeMax();
            }
            return volumeMax;
        }

        public int getVolume() {
            int volume;
            synchronized (this.mControllerLock) {
                volume = this.mSessionInfo.getVolume();
            }
            return volume;
        }

        public boolean isReleased() {
            boolean z;
            synchronized (this.mControllerLock) {
                z = this.mState == 3;
            }
            return z;
        }

        public void selectRoute(MediaRoute2Info route) {
            MediaRouter2Stub stub;
            Objects.requireNonNull(route, "route must not be null");
            if (isReleased()) {
                Log.w(MediaRouter2.TAG, "selectRoute: Called on released controller. Ignoring.");
                return;
            }
            List<MediaRoute2Info> selectedRoutes = getSelectedRoutes();
            if (MediaRouter2.checkRouteListContainsRouteId(selectedRoutes, route.getId())) {
                Log.w(MediaRouter2.TAG, "Ignoring selecting a route that is already selected. route=" + route);
                return;
            }
            List<MediaRoute2Info> selectableRoutes = getSelectableRoutes();
            if (!MediaRouter2.checkRouteListContainsRouteId(selectableRoutes, route.getId())) {
                Log.w(MediaRouter2.TAG, "Ignoring selecting a non-selectable route=" + route);
            } else if (MediaRouter2.this.isSystemRouter()) {
                MediaRouter2.sManager.selectRoute(getRoutingSessionInfo(), route);
            } else {
                synchronized (MediaRouter2.this.mLock) {
                    stub = MediaRouter2.this.mStub;
                }
                if (stub != null) {
                    try {
                        MediaRouter2.this.mMediaRouterService.selectRouteWithRouter2(stub, getId(), route);
                    } catch (RemoteException ex) {
                        Log.e(MediaRouter2.TAG, "Unable to select route for session.", ex);
                    }
                }
            }
        }

        public void deselectRoute(MediaRoute2Info route) {
            MediaRouter2Stub stub;
            Objects.requireNonNull(route, "route must not be null");
            if (isReleased()) {
                Log.w(MediaRouter2.TAG, "deselectRoute: called on released controller. Ignoring.");
                return;
            }
            List<MediaRoute2Info> selectedRoutes = getSelectedRoutes();
            if (!MediaRouter2.checkRouteListContainsRouteId(selectedRoutes, route.getId())) {
                Log.w(MediaRouter2.TAG, "Ignoring deselecting a route that is not selected. route=" + route);
                return;
            }
            List<MediaRoute2Info> deselectableRoutes = getDeselectableRoutes();
            if (!MediaRouter2.checkRouteListContainsRouteId(deselectableRoutes, route.getId())) {
                Log.w(MediaRouter2.TAG, "Ignoring deselecting a non-deselectable route=" + route);
            } else if (MediaRouter2.this.isSystemRouter()) {
                MediaRouter2.sManager.deselectRoute(getRoutingSessionInfo(), route);
            } else {
                synchronized (MediaRouter2.this.mLock) {
                    stub = MediaRouter2.this.mStub;
                }
                if (stub != null) {
                    try {
                        MediaRouter2.this.mMediaRouterService.deselectRouteWithRouter2(stub, getId(), route);
                    } catch (RemoteException ex) {
                        Log.e(MediaRouter2.TAG, "Unable to deselect route from session.", ex);
                    }
                }
            }
        }

        void transferToRoute(MediaRoute2Info route) {
            MediaRouter2Stub stub;
            Objects.requireNonNull(route, "route must not be null");
            synchronized (this.mControllerLock) {
                if (isReleased()) {
                    Log.w(MediaRouter2.TAG, "transferToRoute: Called on released controller. Ignoring.");
                } else if (!this.mSessionInfo.getTransferableRoutes().contains(route.getId())) {
                    Log.w(MediaRouter2.TAG, "Ignoring transferring to a non-transferable route=" + route);
                } else {
                    synchronized (MediaRouter2.this.mLock) {
                        stub = MediaRouter2.this.mStub;
                    }
                    if (stub != null) {
                        try {
                            MediaRouter2.this.mMediaRouterService.transferToRouteWithRouter2(stub, getId(), route);
                        } catch (RemoteException ex) {
                            Log.e(MediaRouter2.TAG, "Unable to transfer to route for session.", ex);
                        }
                    }
                }
            }
        }

        public void setVolume(int volume) {
            MediaRouter2Stub stub;
            if (getVolumeHandling() == 0) {
                Log.w(MediaRouter2.TAG, "setVolume: The routing session has fixed volume. Ignoring.");
            } else if (volume < 0 || volume > getVolumeMax()) {
                Log.w(MediaRouter2.TAG, "setVolume: The target volume is out of range. Ignoring");
            } else if (isReleased()) {
                Log.w(MediaRouter2.TAG, "setVolume: Called on released controller. Ignoring.");
            } else if (MediaRouter2.this.isSystemRouter()) {
                MediaRouter2.sManager.setSessionVolume(getRoutingSessionInfo(), volume);
            } else {
                synchronized (MediaRouter2.this.mLock) {
                    stub = MediaRouter2.this.mStub;
                }
                if (stub != null) {
                    try {
                        MediaRouter2.this.mMediaRouterService.setSessionVolumeWithRouter2(stub, getId(), volume);
                    } catch (RemoteException ex) {
                        Log.e(MediaRouter2.TAG, "setVolume: Failed to deliver request.", ex);
                    }
                }
            }
        }

        public void release() {
            releaseInternal(true);
        }

        boolean scheduleRelease() {
            synchronized (this.mControllerLock) {
                if (this.mState != 1) {
                    return false;
                }
                this.mState = 2;
                synchronized (MediaRouter2.this.mLock) {
                    if (!MediaRouter2.this.mNonSystemRoutingControllers.remove(getId(), this)) {
                        return true;
                    }
                    MediaRouter2.this.mHandler.postDelayed(new Runnable() { // from class: android.media.MediaRouter2$RoutingController$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            MediaRouter2.RoutingController.this.release();
                        }
                    }, JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
                    return true;
                }
            }
        }

        void releaseInternal(boolean shouldReleaseSession) {
            synchronized (this.mControllerLock) {
                int i = this.mState;
                if (i == 3) {
                    if (MediaRouter2.DEBUG) {
                        Log.d(MediaRouter2.TAG, "releaseInternal: Called on released controller. Ignoring.");
                    }
                    return;
                }
                boolean shouldNotifyStop = true;
                if (i != 1) {
                    shouldNotifyStop = false;
                }
                this.mState = 3;
                if (MediaRouter2.this.isSystemRouter()) {
                    MediaRouter2.sManager.releaseSession(getRoutingSessionInfo());
                    return;
                }
                synchronized (MediaRouter2.this.mLock) {
                    MediaRouter2.this.mNonSystemRoutingControllers.remove(getId(), this);
                    if (shouldReleaseSession && MediaRouter2.this.mStub != null) {
                        try {
                            MediaRouter2.this.mMediaRouterService.releaseSessionWithRouter2(MediaRouter2.this.mStub, getId());
                        } catch (RemoteException ex) {
                            Log.e(MediaRouter2.TAG, "Unable to release session", ex);
                        }
                    }
                    if (shouldNotifyStop) {
                        MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$RoutingController$$ExternalSyntheticLambda1.INSTANCE, MediaRouter2.this, this));
                    }
                    if (MediaRouter2.this.mRouteCallbackRecords.isEmpty() && MediaRouter2.this.mNonSystemRoutingControllers.isEmpty() && MediaRouter2.this.mStub != null) {
                        try {
                            MediaRouter2.this.mMediaRouterService.unregisterRouter2(MediaRouter2.this.mStub);
                        } catch (RemoteException ex2) {
                            Log.e(MediaRouter2.TAG, "releaseInternal: Unable to unregister media router.", ex2);
                        }
                        MediaRouter2.this.mStub = null;
                    }
                }
            }
        }

        public String toString() {
            List<String> selectedRoutes = (List) getSelectedRoutes().stream().map(MediaRouter2$$ExternalSyntheticLambda9.INSTANCE).collect(Collectors.toList());
            List<String> selectableRoutes = (List) getSelectableRoutes().stream().map(MediaRouter2$$ExternalSyntheticLambda9.INSTANCE).collect(Collectors.toList());
            List<String> deselectableRoutes = (List) getDeselectableRoutes().stream().map(MediaRouter2$$ExternalSyntheticLambda9.INSTANCE).collect(Collectors.toList());
            StringBuilder sb = new StringBuilder();
            sb.append("RoutingController{ ");
            sb.append("id=");
            sb.append(getId());
            sb.append(", selectedRoutes={");
            sb.append(selectedRoutes);
            sb.append("}");
            sb.append(", selectableRoutes={");
            sb.append(selectableRoutes);
            sb.append("}");
            sb.append(", deselectableRoutes={");
            sb.append(deselectableRoutes);
            sb.append("}");
            StringBuilder result = sb.append(" }");
            return result.toString();
        }

        RoutingSessionInfo getRoutingSessionInfo() {
            RoutingSessionInfo routingSessionInfo;
            synchronized (this.mControllerLock) {
                routingSessionInfo = this.mSessionInfo;
            }
            return routingSessionInfo;
        }

        void setRoutingSessionInfo(RoutingSessionInfo info) {
            synchronized (this.mControllerLock) {
                this.mSessionInfo = info;
            }
        }

        private List<MediaRoute2Info> getRoutesWithIds(final List<String> routeIds) {
            List<MediaRoute2Info> list;
            if (MediaRouter2.this.isSystemRouter()) {
                return (List) MediaRouter2.this.getRoutes().stream().filter(new Predicate() { // from class: android.media.MediaRouter2$RoutingController$$ExternalSyntheticLambda3
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean contains;
                        contains = routeIds.contains(((MediaRoute2Info) obj).getId());
                        return contains;
                    }
                }).collect(Collectors.toList());
            }
            synchronized (MediaRouter2.this.mLock) {
                Stream<String> stream = routeIds.stream();
                Map<String, MediaRoute2Info> map = MediaRouter2.this.mRoutes;
                Objects.requireNonNull(map);
                list = (List) stream.map(new MediaRouter2$RoutingController$$ExternalSyntheticLambda2(map)).filter(MediaRouter2$RoutingController$$ExternalSyntheticLambda4.INSTANCE).collect(Collectors.toList());
            }
            return list;
        }
    }

    /* loaded from: classes2.dex */
    class SystemRoutingController extends RoutingController {
        SystemRoutingController(RoutingSessionInfo sessionInfo) {
            super(sessionInfo);
        }

        @Override // android.media.MediaRouter2.RoutingController
        public boolean isReleased() {
            return false;
        }

        @Override // android.media.MediaRouter2.RoutingController
        boolean scheduleRelease() {
            return true;
        }

        @Override // android.media.MediaRouter2.RoutingController
        void releaseInternal(boolean shouldReleaseSession) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class RouteCallbackRecord {
        public final Executor mExecutor;
        public final RouteDiscoveryPreference mPreference;
        public final RouteCallback mRouteCallback;

        RouteCallbackRecord(Executor executor, RouteCallback routeCallback, RouteDiscoveryPreference preference) {
            this.mRouteCallback = routeCallback;
            this.mExecutor = executor;
            this.mPreference = preference;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RouteCallbackRecord) && this.mRouteCallback == ((RouteCallbackRecord) obj).mRouteCallback;
        }

        public int hashCode() {
            return this.mRouteCallback.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class TransferCallbackRecord {
        public final Executor mExecutor;
        public final TransferCallback mTransferCallback;

        TransferCallbackRecord(Executor executor, TransferCallback transferCallback) {
            this.mTransferCallback = transferCallback;
            this.mExecutor = executor;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TransferCallbackRecord) && this.mTransferCallback == ((TransferCallbackRecord) obj).mTransferCallback;
        }

        public int hashCode() {
            return this.mTransferCallback.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ControllerCallbackRecord {
        public final ControllerCallback mCallback;
        public final Executor mExecutor;

        ControllerCallbackRecord(Executor executor, ControllerCallback callback) {
            this.mCallback = callback;
            this.mExecutor = executor;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ControllerCallbackRecord) && this.mCallback == ((ControllerCallbackRecord) obj).mCallback;
        }

        public int hashCode() {
            return this.mCallback.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ControllerCreationRequest {
        public final long mManagerRequestId;
        public final RoutingController mOldController;
        public final int mRequestId;
        public final MediaRoute2Info mRoute;

        ControllerCreationRequest(int requestId, long managerRequestId, MediaRoute2Info route, RoutingController oldController) {
            this.mRequestId = requestId;
            this.mManagerRequestId = managerRequestId;
            Objects.requireNonNull(route, "route must not be null");
            this.mRoute = route;
            Objects.requireNonNull(oldController, "oldController must not be null");
            this.mOldController = oldController;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class MediaRouter2Stub extends IMediaRouter2.Stub {
        MediaRouter2Stub() {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRouterRegistered(List<MediaRoute2Info> currentRoutes, RoutingSessionInfo currentSystemSessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda2.INSTANCE, MediaRouter2.this, currentRoutes, currentSystemSessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesAdded(List<MediaRoute2Info> routes) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda5.INSTANCE, MediaRouter2.this, routes));
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesRemoved(List<MediaRoute2Info> routes) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda7.INSTANCE, MediaRouter2.this, routes));
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesChanged(List<MediaRoute2Info> routes) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda6.INSTANCE, MediaRouter2.this, routes));
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionCreated(int requestId, RoutingSessionInfo sessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda1.INSTANCE, MediaRouter2.this, Integer.valueOf(requestId), sessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionInfoChanged(RoutingSessionInfo sessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda4.INSTANCE, MediaRouter2.this, sessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionReleased(RoutingSessionInfo sessionInfo) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda3.INSTANCE, MediaRouter2.this, sessionInfo));
        }

        @Override // android.media.IMediaRouter2
        public void requestCreateSessionByManager(long managerRequestId, RoutingSessionInfo oldSession, MediaRoute2Info route) {
            MediaRouter2.this.mHandler.sendMessage(PooledLambda.obtainMessage(MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda0.INSTANCE, MediaRouter2.this, oldSession, route, Long.valueOf(managerRequestId)));
        }
    }

    /* loaded from: classes2.dex */
    class ManagerCallback implements MediaRouter2Manager.Callback {
        ManagerCallback() {
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onRoutesAdded(List<MediaRoute2Info> routes) {
            final List<MediaRoute2Info> filteredRoutes;
            MediaRouter2.this.updateAllRoutesFromManager();
            synchronized (MediaRouter2.this.mLock) {
                MediaRouter2 mediaRouter2 = MediaRouter2.this;
                filteredRoutes = mediaRouter2.filterRoutes(routes, mediaRouter2.mDiscoveryPreference);
            }
            if (!filteredRoutes.isEmpty()) {
                Iterator it = MediaRouter2.this.mRouteCallbackRecords.iterator();
                while (it.hasNext()) {
                    final RouteCallbackRecord record = (RouteCallbackRecord) it.next();
                    record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$ManagerCallback$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            MediaRouter2.RouteCallbackRecord.this.mRouteCallback.onRoutesAdded(filteredRoutes);
                        }
                    });
                }
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onRoutesRemoved(List<MediaRoute2Info> routes) {
            final List<MediaRoute2Info> filteredRoutes;
            MediaRouter2.this.updateAllRoutesFromManager();
            synchronized (MediaRouter2.this.mLock) {
                MediaRouter2 mediaRouter2 = MediaRouter2.this;
                filteredRoutes = mediaRouter2.filterRoutes(routes, mediaRouter2.mDiscoveryPreference);
            }
            if (!filteredRoutes.isEmpty()) {
                Iterator it = MediaRouter2.this.mRouteCallbackRecords.iterator();
                while (it.hasNext()) {
                    final RouteCallbackRecord record = (RouteCallbackRecord) it.next();
                    record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$ManagerCallback$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            MediaRouter2.RouteCallbackRecord.this.mRouteCallback.onRoutesRemoved(filteredRoutes);
                        }
                    });
                }
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onRoutesChanged(List<MediaRoute2Info> routes) {
            final List<MediaRoute2Info> filteredRoutes;
            MediaRouter2.this.updateAllRoutesFromManager();
            synchronized (MediaRouter2.this.mLock) {
                MediaRouter2 mediaRouter2 = MediaRouter2.this;
                filteredRoutes = mediaRouter2.filterRoutes(routes, mediaRouter2.mDiscoveryPreference);
            }
            if (!filteredRoutes.isEmpty()) {
                Iterator it = MediaRouter2.this.mRouteCallbackRecords.iterator();
                while (it.hasNext()) {
                    final RouteCallbackRecord record = (RouteCallbackRecord) it.next();
                    record.mExecutor.execute(new Runnable() { // from class: android.media.MediaRouter2$ManagerCallback$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MediaRouter2.RouteCallbackRecord.this.mRouteCallback.onRoutesChanged(filteredRoutes);
                        }
                    });
                }
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onTransferred(RoutingSessionInfo oldSession, RoutingSessionInfo newSession) {
            RoutingController oldController;
            RoutingController newController;
            if (!oldSession.isSystemSession() && !TextUtils.equals(MediaRouter2.this.mClientPackageName, oldSession.getClientPackageName())) {
                return;
            }
            if ((newSession.isSystemSession() || TextUtils.equals(MediaRouter2.this.mClientPackageName, newSession.getClientPackageName())) && !TextUtils.equals(oldSession.getId(), newSession.getId())) {
                if (oldSession.isSystemSession()) {
                    MediaRouter2.this.mSystemController.setRoutingSessionInfo(MediaRouter2.this.ensureClientPackageNameForSystemSession(oldSession));
                    oldController = MediaRouter2.this.mSystemController;
                } else {
                    oldController = new RoutingController(oldSession);
                }
                if (newSession.isSystemSession()) {
                    MediaRouter2.this.mSystemController.setRoutingSessionInfo(MediaRouter2.this.ensureClientPackageNameForSystemSession(newSession));
                    newController = MediaRouter2.this.mSystemController;
                } else {
                    newController = new RoutingController(newSession);
                }
                MediaRouter2.this.notifyTransfer(oldController, newController);
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onTransferFailed(RoutingSessionInfo session, MediaRoute2Info route) {
            if (session.isSystemSession() || TextUtils.equals(MediaRouter2.this.mClientPackageName, session.getClientPackageName())) {
                MediaRouter2.this.notifyTransferFailure(route);
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onSessionUpdated(RoutingSessionInfo session) {
            RoutingController controller;
            if (session.isSystemSession() || TextUtils.equals(MediaRouter2.this.mClientPackageName, session.getClientPackageName())) {
                if (session.isSystemSession()) {
                    MediaRouter2.this.mSystemController.setRoutingSessionInfo(MediaRouter2.this.ensureClientPackageNameForSystemSession(session));
                    controller = MediaRouter2.this.mSystemController;
                } else {
                    controller = new RoutingController(session);
                }
                MediaRouter2.this.notifyControllerUpdated(controller);
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onSessionReleased(RoutingSessionInfo session) {
            if (session.isSystemSession()) {
                Log.e(MediaRouter2.TAG, "onSessionReleased: Called on system session. Ignoring.");
            } else if (TextUtils.equals(MediaRouter2.this.mClientPackageName, session.getClientPackageName())) {
                MediaRouter2 mediaRouter2 = MediaRouter2.this;
                mediaRouter2.notifyStop(new RoutingController(session, 3));
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onPreferredFeaturesChanged(String packageName, List<String> preferredFeatures) {
            if (TextUtils.equals(MediaRouter2.this.mClientPackageName, packageName)) {
                synchronized (MediaRouter2.this.mLock) {
                    MediaRouter2.this.mDiscoveryPreference = new RouteDiscoveryPreference.Builder(preferredFeatures, true).build();
                }
                MediaRouter2.this.updateAllRoutesFromManager();
                MediaRouter2.this.notifyPreferredFeaturesChanged(preferredFeatures);
            }
        }

        @Override // android.media.MediaRouter2Manager.Callback
        public void onRequestFailed(int reason) {
        }
    }
}
