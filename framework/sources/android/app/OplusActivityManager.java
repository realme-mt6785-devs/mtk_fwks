package android.app;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.GraphicBuffer;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IOplusKeyEventObserver;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Surface;
import android.window.TaskSnapshot;
import com.oplus.app.IOplusAppStartController;
import com.oplus.app.IOplusAppSwitchObserver;
import com.oplus.app.IOplusFreeformConfigChangedListener;
import com.oplus.app.IOplusGameSpaceController;
import com.oplus.app.IOplusHansListener;
import com.oplus.app.IOplusPermissionRecordController;
import com.oplus.app.IOplusProtectConnection;
import com.oplus.app.IOplusSplitScreenObserver;
import com.oplus.app.IOplusZoomWindowConfigChangedListener;
import com.oplus.app.ISecurityPageController;
import com.oplus.app.OplusAppInfo;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.bracket.IOplusBracketModeChangedListener;
import com.oplus.compactwindow.IOplusCompactWindowObserver;
import com.oplus.confinemode.IOplusConfineModeObserver;
import com.oplus.darkmode.OplusDarkModeData;
import com.oplus.datasync.ISysStateChangeCallback;
import com.oplus.eap.IOplusEapDataCallback;
import com.oplus.eventhub.sdk.aidl.IEventCallback;
import com.oplus.favorite.IOplusFavoriteQueryCallback;
import com.oplus.globaldrag.OplusGlobalDragAndDropRUSConfig;
import com.oplus.lockscreen.IOplusLockScreenCallback;
import com.oplus.miragewindow.IOplusMirageDisplayObserver;
import com.oplus.miragewindow.IOplusMirageSessionCallback;
import com.oplus.miragewindow.IOplusMirageWindowObserver;
import com.oplus.miragewindow.IOplusMirageWindowSession;
import com.oplus.miragewindow.OplusMirageWindowInfo;
import com.oplus.multiapp.OplusMultiAppConfig;
import com.oplus.splitscreen.IOplusSplitScreenSession;
import com.oplus.util.OplusAccidentallyTouchData;
import com.oplus.util.OplusDisplayCompatData;
import com.oplus.util.OplusDisplayOptimizationData;
import com.oplus.util.OplusPackageFreezeData;
import com.oplus.util.OplusProcDependData;
import com.oplus.util.OplusReflectData;
import com.oplus.util.OplusResolveData;
import com.oplus.util.OplusSecureKeyboardData;
import com.oplus.util.OplusUXIconData;
import com.oplus.verifycode.IOplusVerifyCodeListener;
import com.oplus.zoomwindow.IOplusZoomAppObserver;
import com.oplus.zoomwindow.IOplusZoomWindowObserver;
import com.oplus.zoomwindow.OplusZoomControlViewInfo;
import com.oplus.zoomwindow.OplusZoomFloatHandleViewInfo;
import com.oplus.zoomwindow.OplusZoomInputEventInfo;
import com.oplus.zoomwindow.OplusZoomWindowInfo;
import com.oplus.zoomwindow.OplusZoomWindowRUSConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class OplusActivityManager extends OplusBaseActivityManager implements IOplusActivityManager, IOplusActivityTaskManager, IOplusDirectActivityManager {
    private static final String TAG = "OplusActivityManager";
    private final OplusActivityTaskManager mOplusAtm = OplusActivityTaskManager.getInstance();

    /* loaded from: classes.dex */
    public interface ITaskStackListenerWrapper {
        void onActivityPinned(String str, int i, int i2, int i3);

        void onActivityUnpinned();

        void onTaskSnapshotChanged(int i, TaskSnapshotWrapper taskSnapshotWrapper);
    }

    public static OplusActivityManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {
        private static final OplusActivityManager INSTANCE = new OplusActivityManager();

        private LazyHolder() {
        }
    }

    public void swapDockedFullscreenStack() throws RemoteException {
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setSecureController(IActivityController controller) throws RemoteException {
        this.mOplusAtm.setSecureController(controller);
    }

    @Override // android.app.IOplusActivityTaskManager
    public ComponentName getTopActivityComponentName() throws RemoteException {
        return this.mOplusAtm.getTopActivityComponentName();
    }

    @Override // android.app.IOplusActivityTaskManager
    public ApplicationInfo getTopApplicationInfo() throws RemoteException {
        return this.mOplusAtm.getTopApplicationInfo();
    }

    @Override // android.app.IOplusActivityTaskManager
    public List<OplusAppInfo> getAllTopAppInfos() throws RemoteException {
        return this.mOplusAtm.getAllTopAppInfos();
    }

    @Override // android.app.IOplusActivityTaskManager
    public List<String> getFreeformConfigList(int type) {
        return null;
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isFreeformEnabled() {
        return false;
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean addFreeformConfigChangedListener(IOplusFreeformConfigChangedListener listener) {
        return false;
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean removeFreeformConfigChangedListener(IOplusFreeformConfigChangedListener listener) {
        return false;
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerAppSwitchObserver(String pkgName, IOplusAppSwitchObserver observer, OplusAppSwitchConfig config) throws RemoteException {
        return this.mOplusAtm.registerAppSwitchObserver(pkgName, observer, config);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterAppSwitchObserver(String pkgName, OplusAppSwitchConfig config) throws RemoteException {
        return this.mOplusAtm.unregisterAppSwitchObserver(pkgName, config);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getSplitScreenState(Intent intent) throws RemoteException {
        return this.mOplusAtm.getSplitScreenState(intent);
    }

    public ComponentName getDockTopAppName() {
        return null;
    }

    public List<String> getAllTopPkgName() {
        return null;
    }

    public ApplicationInfo getFreeFormAppInfo() {
        return null;
    }

    public final int startActivityForFreeform(Intent intent, Bundle bOptions, int userId, String callPkg) {
        return -1;
    }

    public final void exitOplusosFreeform(Bundle bOptions) {
    }

    @Override // android.app.IOplusActivityTaskManager
    public int startZoomWindow(Intent intent, Bundle options, int userId, String callPkg) throws RemoteException {
        return this.mOplusAtm.startZoomWindow(intent, options, userId, callPkg);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerZoomWindowObserver(IOplusZoomWindowObserver observer) throws RemoteException {
        return this.mOplusAtm.registerZoomWindowObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterZoomWindowObserver(IOplusZoomWindowObserver observer) throws RemoteException {
        return this.mOplusAtm.unregisterZoomWindowObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerZoomAppObserver(IOplusZoomAppObserver observer) throws RemoteException {
        return this.mOplusAtm.registerZoomAppObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterZoomAppObserver(IOplusZoomAppObserver observer) throws RemoteException {
        return this.mOplusAtm.unregisterZoomAppObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusZoomWindowInfo getCurrentZoomWindowState() throws RemoteException {
        return this.mOplusAtm.getCurrentZoomWindowState();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void hideZoomWindow(int flag) throws RemoteException {
        this.mOplusAtm.hideZoomWindow(flag);
    }

    @Override // android.app.IOplusActivityTaskManager
    public List<String> getZoomAppConfigList(int type) throws RemoteException {
        return this.mOplusAtm.getZoomAppConfigList(type);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void onInputEvent(OplusZoomInputEventInfo inputEventInfo) throws RemoteException {
        this.mOplusAtm.onInputEvent(inputEventInfo);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void onControlViewChanged(OplusZoomControlViewInfo cvInfo) throws RemoteException {
        this.mOplusAtm.onControlViewChanged(cvInfo);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void onFloatHandleViewChanged(OplusZoomFloatHandleViewInfo floatHandleInfo) throws RemoteException {
        this.mOplusAtm.onFloatHandleViewChanged(floatHandleInfo);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportZoomWindowMode() throws RemoteException {
        return this.mOplusAtm.isSupportZoomWindowMode();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportZoomMode(String target, int userId, String callPkg, Bundle extension) throws RemoteException {
        return this.mOplusAtm.isSupportZoomMode(target, userId, callPkg, extension);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean handleShowCompatibilityToast(String target, int userId, String callPkg, Bundle extension, int type) throws RemoteException {
        return this.mOplusAtm.handleShowCompatibilityToast(target, userId, callPkg, extension, type);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void startMiniZoomFromZoom(int startWay) throws RemoteException {
        this.mOplusAtm.startMiniZoomFromZoom(startWay);
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusZoomWindowRUSConfig getZoomWindowConfig() throws RemoteException {
        return this.mOplusAtm.getZoomWindowConfig();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setZoomWindowConfig(OplusZoomWindowRUSConfig config) throws RemoteException {
        this.mOplusAtm.setZoomWindowConfig(config);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean addZoomWindowConfigChangedListener(IOplusZoomWindowConfigChangedListener listener) throws RemoteException {
        return this.mOplusAtm.addZoomWindowConfigChangedListener(listener);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean removeZoomWindowConfigChangedListener(IOplusZoomWindowConfigChangedListener listener) throws RemoteException {
        return this.mOplusAtm.removeZoomWindowConfigChangedListener(listener);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isZoomSimpleModeEnable() throws RemoteException {
        return this.mOplusAtm.isZoomSimpleModeEnable();
    }

    /* loaded from: classes.dex */
    public static class TaskSnapshotWrapper {
        TaskSnapshot mTaskSnapshot;

        public TaskSnapshotWrapper(TaskSnapshot taskSnapshot) {
            this.mTaskSnapshot = taskSnapshot;
        }

        public void destroy() {
            GraphicBuffer snapshotInfo;
            try {
                TaskSnapshot taskSnapshot = this.mTaskSnapshot;
                if (taskSnapshot != null && (snapshotInfo = taskSnapshot.getSnapshot()) != null) {
                    snapshotInfo.destroy();
                }
            } catch (Exception e) {
                System.gc();
            }
        }

        public Bitmap getSnapshotBitmap() {
            return null;
        }
    }

    public static void registerTaskStackListener(final ITaskStackListenerWrapper listener) {
        TaskStackListener taskStackListener = new TaskStackListener() { // from class: android.app.OplusActivityManager.1
            @Override // android.app.TaskStackListener, android.app.ITaskStackListener
            public void onTaskSnapshotChanged(int taskId, TaskSnapshot snapshot) {
                ITaskStackListenerWrapper.this.onTaskSnapshotChanged(taskId, new TaskSnapshotWrapper(snapshot));
            }

            @Override // android.app.TaskStackListener, android.app.ITaskStackListener
            public void onActivityUnpinned() {
                ITaskStackListenerWrapper.this.onActivityUnpinned();
            }

            @Override // android.app.TaskStackListener, android.app.ITaskStackListener
            public void onActivityPinned(String packageName, int userId, int taskId, int stackId) {
                ITaskStackListenerWrapper.this.onActivityPinned(packageName, userId, taskId, stackId);
            }
        };
        try {
            ActivityTaskManager.getService().registerTaskStackListener(taskStackListener);
        } catch (RemoteException e) {
            Log.w(TAG, "registerTaskStackListener failed.");
        }
    }

    public static List<ActivityManager.RunningTaskInfo> getFilteredTasks(int num, boolean filterOnlyVisibleRecents) {
        return null;
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean writeEdgeTouchPreventParam(String callPkg, String scenePkg, List<String> paramCmdList) throws RemoteException {
        return this.mOplusAtm.writeEdgeTouchPreventParam(callPkg, scenePkg, paramCmdList);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setDefaultEdgeTouchPreventParam(String callPkg, List<String> paramCmdList) throws RemoteException {
        this.mOplusAtm.setDefaultEdgeTouchPreventParam(callPkg, paramCmdList);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean resetDefaultEdgeTouchPreventParam(String callPkg) throws RemoteException {
        return this.mOplusAtm.resetDefaultEdgeTouchPreventParam(callPkg);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportEdgeTouchPrevent() throws RemoteException {
        return this.mOplusAtm.isSupportEdgeTouchPrevent();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setEdgeTouchCallRules(String callPkg, Map<String, List<String>> rulesMap) throws RemoteException {
        this.mOplusAtm.setEdgeTouchCallRules(callPkg, rulesMap);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int splitScreenForEdgePanel(Intent intent, int userId) throws RemoteException {
        return this.mOplusAtm.splitScreenForEdgePanel(intent, userId);
    }

    public boolean isAppCallRefuseMode() throws RemoteException {
        return (getConfineMode() & 1) != 0;
    }

    public void setAppCallRefuseMode(boolean enable) throws RemoteException {
        setConfineMode(1, enable);
    }

    public void setChildSpaceMode(boolean mode) throws RemoteException {
        setConfineMode(2, mode);
    }

    public void setAllowLaunchApps(List<String> allowLaunchApps) throws RemoteException {
        setPermitList(2, 5, allowLaunchApps, false);
        setPermitList(2, 5, allowLaunchApps, true);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setConfineMode(int mode, boolean on) throws RemoteException {
        this.mOplusAtm.setConfineMode(mode, on);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getConfineMode() throws RemoteException {
        return this.mOplusAtm.getConfineMode();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setPermitList(int mode, int type, List<String> permits, boolean isMultiApp) throws RemoteException {
        this.mOplusAtm.setPermitList(mode, type, permits, isMultiApp);
    }

    public int getFontVariationAdaption(String packagename) throws RemoteException {
        return getFontVariationAdaptionData(packagename);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setGimbalLaunchPkg(String pkgName) throws RemoteException {
        this.mOplusAtm.setGimbalLaunchPkg(pkgName);
    }

    public void setPackagesState(Map<String, Integer> packageMap) throws RemoteException {
        this.mOplusAtm.setPackagesState(packageMap);
    }

    public boolean registerLockScreenCallback(IOplusLockScreenCallback callback) throws RemoteException {
        return this.mOplusAtm.registerLockScreenCallback(callback);
    }

    public boolean unregisterLockScreenCallback(IOplusLockScreenCallback callback) throws RemoteException {
        return this.mOplusAtm.unregisterLockScreenCallback(callback);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void notifySplitScreenStateChanged(String event, Bundle bundle, boolean broadcast) throws RemoteException {
        this.mOplusAtm.notifySplitScreenStateChanged(event, bundle, broadcast);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean setSplitScreenObserver(IOplusSplitScreenObserver observer) throws RemoteException {
        return this.mOplusAtm.setSplitScreenObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isInSplitScreenMode() throws RemoteException {
        return this.mOplusAtm.isInSplitScreenMode();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean dismissSplitScreenMode(int type) throws RemoteException {
        return this.mOplusAtm.dismissSplitScreenMode(type);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerSplitScreenObserver(int observerId, IOplusSplitScreenObserver observer) throws RemoteException {
        return this.mOplusAtm.registerSplitScreenObserver(observerId, observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterSplitScreenObserver(int observerId, IOplusSplitScreenObserver observer) throws RemoteException {
        return this.mOplusAtm.unregisterSplitScreenObserver(observerId, observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public Bundle getSplitScreenStatus(String event) throws RemoteException {
        return this.mOplusAtm.getSplitScreenStatus(event);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean splitScreenForTopApp(int type) throws RemoteException {
        return this.mOplusAtm.splitScreenForTopApp(type);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean splitScreenForRecentTasks(int taskId) throws RemoteException {
        return this.mOplusAtm.splitScreenForRecentTasks(taskId);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean setTaskWindowingModeSplitScreen(int taskId) throws RemoteException {
        return this.mOplusAtm.setTaskWindowingModeSplitScreen(taskId);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean splitScreenForEdgePanel(Intent intent, boolean launchToPrimary, int launchArea) throws RemoteException {
        return this.mOplusAtm.splitScreenForEdgePanel(intent, launchToPrimary, launchArea);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean hasFolderFeature() throws RemoteException {
        return this.mOplusAtm.hasFolderFeature();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isFolderInnerScreen() throws RemoteException {
        return this.mOplusAtm.isFolderInnerScreen();
    }

    @Override // android.app.IOplusActivityTaskManager
    public Rect getMinimizedBounds(int dockedSide) throws RemoteException {
        return this.mOplusAtm.getMinimizedBounds(dockedSide);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setHandleRegion(int windowingMode, Rect handleRegion) throws RemoteException {
        this.mOplusAtm.setHandleRegion(windowingMode, handleRegion);
    }

    @Override // android.app.IOplusActivityTaskManager
    public IOplusSplitScreenSession getSplitScreenSession() throws RemoteException {
        return this.mOplusAtm.getSplitScreenSession();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerKeyEventObserver(String observerFingerPrint, IOplusKeyEventObserver observer, int listenFlag) throws RemoteException {
        return this.mOplusAtm.registerKeyEventObserver(observerFingerPrint, observer, listenFlag);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterKeyEventObserver(String observerFingerPrint) throws RemoteException {
        return this.mOplusAtm.unregisterKeyEventObserver(observerFingerPrint);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerKeyEventInterceptor(String interceptorFingerPrint, IOplusKeyEventObserver observer, Map<Integer, Integer> configs) throws RemoteException {
        return this.mOplusAtm.registerKeyEventInterceptor(interceptorFingerPrint, observer, configs);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterKeyEventInterceptor(String interceptorFingerPrint) throws RemoteException {
        return this.mOplusAtm.unregisterKeyEventInterceptor(interceptorFingerPrint);
    }

    public boolean updateAppData(String module, Bundle bundle) throws RemoteException {
        return this.mOplusAtm.updateAppData(module, bundle);
    }

    public boolean registerSysStateChangeObserver(String module, ISysStateChangeCallback callback) throws RemoteException {
        return this.mOplusAtm.registerSysStateChangeObserver(module, callback);
    }

    public boolean unregisterSysStateChangeObserver(String module, ISysStateChangeCallback callback) throws RemoteException {
        return this.mOplusAtm.unregisterSysStateChangeObserver(module, callback);
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusGlobalDragAndDropRUSConfig getGlobalDragAndDropConfig() throws RemoteException {
        return this.mOplusAtm.getGlobalDragAndDropConfig();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setGlobalDragAndDropConfig(OplusGlobalDragAndDropRUSConfig config) throws RemoteException {
        this.mOplusAtm.setGlobalDragAndDropConfig(config);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerMirageWindowObserver(IOplusMirageWindowObserver observer) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager registerMirageWindowObserver");
        return this.mOplusAtm.registerMirageWindowObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterMirageWindowObserver(IOplusMirageWindowObserver observer) throws RemoteException {
        return this.mOplusAtm.unregisterMirageWindowObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerMirageDisplayObserver(IOplusMirageDisplayObserver observer) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager registerMirageDisplayObserver");
        return this.mOplusAtm.registerMirageDisplayObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterMirageDisplayObserver(IOplusMirageDisplayObserver observer) throws RemoteException {
        return this.mOplusAtm.unregisterMirageDisplayObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int createMirageDisplay(Surface surface) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager createMirageDisplay");
        return this.mOplusAtm.createMirageDisplay(surface);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void startMirageWindowMode(ComponentName cpnName, int taskId, int flags, Bundle options) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager startMirageWindowMode");
        this.mOplusAtm.startMirageWindowMode(cpnName, taskId, flags, options);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int startMirageWindowMode(Intent intent, Bundle options) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager startMirageWindowMode");
        return this.mOplusAtm.startMirageWindowMode(intent, options);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isMirageWindowShow() throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager isMirageWindowShow");
        return this.mOplusAtm.isMirageWindowShow();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void stopMirageWindowMode() throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager stopMirageWindowMode");
        this.mOplusAtm.stopMirageWindowMode();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void stopMirageWindowMode(Bundle options) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager stopMirageWindowMode");
        this.mOplusAtm.stopMirageWindowMode(options);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setMirageDisplaySurfaceById(int displayId, Surface surface) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager setMirageDisplaySurfaceById");
        this.mOplusAtm.setMirageDisplaySurfaceById(displayId, surface);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setMirageDisplaySurfaceByMode(int mode, Surface surface) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager setMirageDisplaySurfaceByMode");
        this.mOplusAtm.setMirageDisplaySurfaceByMode(mode, surface);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getMirageDisplayCastMode(int displayId) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager getMirageDisplayCastMode");
        return this.mOplusAtm.getMirageDisplayCastMode(displayId);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void expandToFullScreen() throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager expandTofullscreen");
        this.mOplusAtm.expandToFullScreen();
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setMirageWindowSilent(String pkgName) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager setMirageWindowSilent");
        this.mOplusAtm.setMirageWindowSilent(pkgName);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportMirageWindowMode() throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager isSupportMirageWindowMode");
        return this.mOplusAtm.isSupportMirageWindowMode();
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusMirageWindowInfo getMirageWindowInfo() throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager getMirageWindowInfo");
        return this.mOplusAtm.getMirageWindowInfo();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updateMirageWindowCastFlag(int castFlag, Bundle options) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager updateMirageWindowCastFlag");
        return this.mOplusAtm.updateMirageWindowCastFlag(castFlag, options);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updatePrivacyProtectionList(List<String> name, boolean append) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager updatePrivacyProtectionList");
        return this.mOplusAtm.updatePrivacyProtectionList(name, append);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updatePrivacyProtectionList(List<String> name, boolean append, boolean isDefault, Bundle options) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager default updatePrivacyProtectionList");
        return this.mOplusAtm.updatePrivacyProtectionList(name, append, isDefault, options);
    }

    @Override // android.app.IOplusActivityTaskManager
    public IOplusMirageWindowSession createMirageWindowSession(IOplusMirageSessionCallback callback) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager createMirageWindowSession");
        return this.mOplusAtm.createMirageWindowSession(callback);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getGetDisplayIdForPackageName(String packageName) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager getGetDisplayIdForPackageName");
        return this.mOplusAtm.getGetDisplayIdForPackageName(packageName);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void feedbackUserSelection(int eventId, int selection, Bundle extension) throws RemoteException {
        Slog.d("MirageDisplayWindow", "OplusActivityManager feedbackUserSelection");
        this.mOplusAtm.feedbackUserSelection(eventId, selection, extension);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updateCarModeMultiLaunchWhiteList(String list) throws RemoteException {
        Slog.d("MirageDisplayWindow", "OplusActivityManager updateCarModeMultiLaunchWhiteList");
        return this.mOplusAtm.updateCarModeMultiLaunchWhiteList(list);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean rebindDisplayIfNeeded(int castDisplayId, int mirageDisplayId) throws RemoteException {
        Slog.d("MirageDisplayWindow", "OplusActivityManager rebindDisplayIfNeeded");
        return this.mOplusAtm.rebindDisplayIfNeeded(castDisplayId, mirageDisplayId);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerConfineModeObserver(IOplusConfineModeObserver observer) throws RemoteException {
        return this.mOplusAtm.registerConfineModeObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterConfineModeObserver(IOplusConfineModeObserver observer) throws RemoteException {
        return this.mOplusAtm.unregisterConfineModeObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public String readNodeFile(int nodeFlag) throws RemoteException {
        return this.mOplusAtm.readNodeFile(nodeFlag);
    }

    @Override // android.app.IOplusActivityTaskManager
    public String readNodeFileByDevice(int deviceId, int nodeFlag) throws RemoteException {
        return this.mOplusAtm.readNodeFileByDevice(deviceId, nodeFlag);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean writeNodeFile(int nodeFlag, String info) throws RemoteException {
        return this.mOplusAtm.writeNodeFile(nodeFlag, info);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean writeNodeFileByDevice(int deviceId, int nodeFlag, String info) throws RemoteException {
        return this.mOplusAtm.writeNodeFileByDevice(deviceId, nodeFlag, info);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isTouchNodeSupport(int deviceId, int nodeFlag) throws RemoteException {
        return this.mOplusAtm.isTouchNodeSupport(deviceId, nodeFlag);
    }

    @Override // android.app.IOplusActivityManager
    public void updatePermissionChoice(String packageName, String permission, int choice) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packageName);
            data.writeString(permission);
            data.writeInt(choice);
            this.mRemote.transact(10003, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void setPermissionInterceptEnable(boolean enabled) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(String.valueOf(enabled));
            this.mRemote.transact(10004, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean isPermissionInterceptEnabled() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10005, data, reply, 0);
            reply.readException();
            boolean enabled = Boolean.valueOf(reply.readString()).booleanValue();
            return enabled;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void setSystemProperties(String properties, String value) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(properties);
            data.writeString(value);
            this.mRemote.transact(10006, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void killPidForce(int pid) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(pid);
            this.mRemote.transact(10008, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void grantOplusPermissionByGroup(String packageName, String permission) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packageName);
            data.writeString(permission);
            this.mRemote.transact(10012, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void revokeOplusPermissionByGroup(String packageName, String permission) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packageName);
            data.writeString(permission);
            this.mRemote.transact(10013, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void handleAppForNotification(String pkgName, int uid, int otherInfo) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            data.writeInt(uid);
            data.writeInt(otherInfo);
            this.mRemote.transact(10014, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusAccidentallyTouchData getAccidentallyTouchData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10015, data, reply, 0);
            reply.readException();
            OplusAccidentallyTouchData info = OplusAccidentallyTouchData.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void setGameSpaceController(IOplusGameSpaceController watcher) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
            this.mRemote.transact(10016, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public ArrayList<String> getGlobalPkgWhiteList(int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(type);
            this.mRemote.transact(10019, data, reply, 0);
            reply.readException();
            ArrayList<String> list = new ArrayList<>();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public ArrayList<String> getGlobalProcessWhiteList() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10020, data, reply, 0);
            reply.readException();
            ArrayList<String> list = new ArrayList<>();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Deprecated
    public void addStageProtectInfo(String pkg, String fromPkg, long timeout) throws RemoteException {
        Slog.i("OplusSelfProtectManager", "failed to add self-protect, pkg: " + pkg + " fromPkg: " + fromPkg);
    }

    @Override // android.app.IOplusActivityManager
    public void addStageProtectInfo(String callerPkg, String protectPkg, List<String> processList, String reason, long timeout, IOplusProtectConnection connection) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeString(protectPkg);
            data.writeStringList(processList);
            data.writeString(reason);
            data.writeLong(timeout);
            data.writeStrongBinder(connection != null ? connection.asBinder() : null);
            this.mRemote.transact(10021, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void removeStageProtectInfo(String protectPkg, String callerPkg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(protectPkg);
            data.writeString(callerPkg);
            this.mRemote.transact(10022, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusDisplayOptimizationData getDisplayOptimizationData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10023, data, reply, 0);
            reply.readException();
            OplusDisplayOptimizationData info = OplusDisplayOptimizationData.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusSecureKeyboardData getSecureKeyboardData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10024, data, reply, 0);
            reply.readException();
            OplusSecureKeyboardData info = OplusSecureKeyboardData.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public ArrayList<String> getStageProtectListFromPkg(String pkg, int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkg);
            data.writeInt(type);
            this.mRemote.transact(10025, data, reply, 0);
            reply.readException();
            ArrayList<String> list = new ArrayList<>();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void handleAppFromControlCenter(String pkgName, int uid) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            data.writeInt(uid);
            this.mRemote.transact(10026, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusDisplayCompatData getDisplayCompatData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10027, data, reply, 0);
            reply.readException();
            OplusDisplayCompatData info = OplusDisplayCompatData.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean getIsSupportMultiApp() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10094, data, reply, 0);
            reply.readException();
            boolean enabled = Boolean.valueOf(reply.readString()).booleanValue();
            return enabled;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<String> getMultiAppList(int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(type);
            this.mRemote.transact(10091, data, reply, 0);
            reply.readException();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public String getMultiAppAlias(String pkgName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            this.mRemote.transact(10090, data, reply, 0);
            reply.readException();
            String alias = reply.readString();
            return alias;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public int setMultiAppConfig(OplusMultiAppConfig config) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeParcelable(config, 0);
            this.mRemote.transact(10087, data, reply, 0);
            reply.readException();
            int ret = reply.readInt();
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusMultiAppConfig getMultiAppConfig() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10088, data, reply, 0);
            reply.readException();
            OplusMultiAppConfig config = (OplusMultiAppConfig) reply.readParcelable(OplusMultiAppConfig.class.getClassLoader());
            return config;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public int setMultiAppAlias(String pkgName, String alias) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            data.writeString(alias);
            this.mRemote.transact(10089, data, reply, 0);
            reply.readException();
            int ret = reply.readInt();
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public int setMultiAppAccessMode(String pkgName, int accessMode) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            data.writeInt(accessMode);
            this.mRemote.transact(10097, data, reply, 0);
            reply.readException();
            int ret = reply.readInt();
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public int getMultiAppAccessMode(String pkgName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            this.mRemote.transact(10096, data, reply, 0);
            reply.readException();
            int accessMode = reply.readInt();
            return accessMode;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public Map<String, Integer> getMultiAppAllAccessMode() throws RemoteException {
        new HashMap();
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10098, data, reply, 0);
            reply.readException();
            Map<String, Integer> resMap = reply.readHashMap(HashMap.class.getClassLoader());
            return resMap;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean isMultiApp(int userId, String pkgName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(userId);
            data.writeString(pkgName);
            this.mRemote.transact(10099, data, reply, 0);
            reply.readException();
            boolean ret = reply.readBoolean();
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public int setMultiAppStatus(String pkgName, int status) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            data.writeInt(status);
            this.mRemote.transact(10092, data, reply, 0);
            reply.readException();
            int ret = reply.readInt();
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public int getMultiAppMaxCreateNum() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10093, data, reply, 0);
            reply.readException();
            int ret = reply.readInt();
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void scanFileIfNeed(int userId, String path) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(userId);
            data.writeString(path);
            this.mRemote.transact(10095, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void addMiniProgramShare(String shareAppPkgName, String miniProgramPkgName, String miniProgramSignature) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(shareAppPkgName);
            data.writeString(miniProgramPkgName);
            data.writeString(miniProgramSignature);
            this.mRemote.transact(10031, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void removeMiniProgramShare(String shareAppPkgName, String miniProgramPkgName, String miniProgramSignature) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(shareAppPkgName);
            data.writeString(miniProgramPkgName);
            data.writeString(miniProgramSignature);
            this.mRemote.transact(10032, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusResolveData getResolveData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10034, data, reply, 0);
            reply.readException();
            OplusResolveData info = OplusResolveData.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusReflectData getReflectData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10037, data, reply, 0);
            reply.readException();
            OplusReflectData info = OplusReflectData.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void addFastAppWCPay(String originAppCpn, String fastAppCpn) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(originAppCpn);
            data.writeString(fastAppCpn);
            this.mRemote.transact(10038, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void removeFastAppWCPay(String originAppCpn, String fastAppCpn) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(originAppCpn);
            data.writeString(fastAppCpn);
            this.mRemote.transact(10039, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<OplusPackageFreezeData> getRunningProcesses() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10086, data, reply, 0);
            reply.readException();
            List<OplusPackageFreezeData> list = reply.createTypedArrayList(OplusPackageFreezeData.CREATOR);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void setAppStartMonitorController(IOplusAppStartController watcher) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
            this.mRemote.transact(10043, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void addFastAppThirdLogin(String callerPkg, String replacePkg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeString(replacePkg);
            this.mRemote.transact(10046, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void removeFastAppThirdLogin(String callerPkg, String replacePkg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeString(replacePkg);
            this.mRemote.transact(10047, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusDirectActivityManager
    public void favoriteQueryRule(String packageName, IOplusFavoriteQueryCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packageName);
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10048, data, reply, 1);
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void addBackgroundRestrictedInfo(String callerPkg, List<String> targetPkgList) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeStringList(targetPkgList);
            this.mRemote.transact(10049, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void setPreventIndulgeController(IOplusAppStartController controller) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(controller != null ? controller.asBinder() : null);
            this.mRemote.transact(10050, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void addPreventIndulgeList(List<String> pkgNames) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStringList(pkgNames);
            this.mRemote.transact(10051, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean putConfigInfo(String configName, Bundle bundle, int flag, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(configName);
            data.writeBundle(bundle);
            data.writeInt(flag);
            data.writeInt(userId);
            this.mRemote.transact(10062, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public Bundle getConfigInfo(String configName, int flag, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new Bundle();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(configName);
            data.writeInt(flag);
            data.writeInt(userId);
            this.mRemote.transact(10063, data, reply, 0);
            reply.readException();
            Bundle bundle = reply.readBundle();
            return bundle;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public float updateCpuTracker(long lastUpdateTime) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeLong(lastUpdateTime);
            this.mRemote.transact(10060, data, reply, 0);
            reply.readException();
            float percent = Float.valueOf(reply.readFloat()).floatValue();
            return percent;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public List<String> getCpuWorkingStats() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10061, data, reply, 0);
            reply.readException();
            ArrayList<String> list = new ArrayList<>();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public SharedMemory getCpuLimitLatestInfos(String pkgName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            this.mRemote.transact(10140, data, reply, 0);
            reply.readException();
            SharedMemory infos = (SharedMemory) reply.readParcelable(SharedMemory.class.getClassLoader());
            return infos;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public long getCpuTotalLoad() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10138, data, reply, 0);
            reply.readException();
            long percent = Long.valueOf(reply.readLong()).longValue();
            return percent;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public List<String> getTopPidsLoadInfos(int topNum) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(topNum);
            this.mRemote.transact(10139, data, reply, 0);
            reply.readException();
            ArrayList<String> list = new ArrayList<>();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void forceTrimAppMemory(int level) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(level);
            this.mRemote.transact(10067, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void setPermissionRecordController(IOplusPermissionRecordController watcher) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
            this.mRemote.transact(10066, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean isNightMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10068, data, reply, 0);
            reply.readException();
            boolean success = reply.readBoolean();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public OplusDarkModeData getDarkModeData(String packageName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packageName);
            this.mRemote.transact(10077, data, reply, 0);
            reply.readException();
            OplusDarkModeData darkModeData = (OplusDarkModeData) reply.readParcelable(OplusDarkModeData.class.getClassLoader());
            return darkModeData;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean dumpProcPerfData(Bundle bundle) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeBundle(bundle);
            this.mRemote.transact(10069, data, reply, 0);
            reply.readException();
            boolean success = reply.readBoolean();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<String> getProcCommonInfoList(int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(type);
            this.mRemote.transact(10070, data, reply, 0);
            reply.readException();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<OplusProcDependData> getProcDependency(int pid) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(pid);
            this.mRemote.transact(10071, data, reply, 0);
            reply.readException();
            List<OplusProcDependData> list = reply.createTypedArrayList(OplusProcDependData.CREATOR);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<OplusProcDependData> getProcDependency(String packageName, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packageName);
            data.writeInt(userId);
            this.mRemote.transact(10072, data, reply, 0);
            reply.readException();
            List<OplusProcDependData> list = reply.createTypedArrayList(OplusProcDependData.CREATOR);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<String> getTaskPkgList(int taskId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(taskId);
            this.mRemote.transact(10073, data, reply, 0);
            reply.readException();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void syncPermissionRecord() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10074, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void updateUidCpuTracker() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10075, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public List<String> getUidCpuWorkingStats() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10076, data, reply, 0);
            reply.readException();
            ArrayList<String> list = new ArrayList<>();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<String> getProcCmdline(int[] pids) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(pids.length);
            data.writeIntArray(pids);
            this.mRemote.transact(10079, data, reply, 0);
            reply.readException();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void activeGc(int[] pids) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            if (pids != null) {
                data.writeInt(pids.length);
                data.writeIntArray(pids);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(10080, data, reply, 1);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void finishNotOrderReceiver(IBinder who, int hasCode, int resultCode, String resultData, Bundle resultExtras, boolean resultAbort) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(who);
            data.writeInt(hasCode);
            data.writeInt(resultCode);
            data.writeString(resultData);
            data.writeBundle(resultExtras);
            data.writeInt(resultAbort ? 1 : 0);
            this.mRemote.transact(10081, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void reportSkippedFrames(long currentTime, long skippedFrames) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeLong(currentTime);
            data.writeLong(skippedFrames);
            this.mRemote.transact(10082, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void reportSkippedFrames(long currentTime, boolean isAnimation, boolean isForeground, long skippedFrames) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeLong(currentTime);
            int i = 1;
            data.writeInt(isAnimation ? 1 : 0);
            if (!isForeground) {
                i = 0;
            }
            data.writeInt(i);
            data.writeLong(skippedFrames);
            this.mRemote.transact(10109, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void reportSkippedFrames(long currentTime, boolean isAnimation, boolean isForeground, long skippedFrames, String pckName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeLong(currentTime);
            int i = 1;
            data.writeInt(isAnimation ? 1 : 0);
            if (!isForeground) {
                i = 0;
            }
            data.writeInt(i);
            data.writeLong(skippedFrames);
            data.writeString(pckName);
            this.mRemote.transact(10137, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public String queryProcessNameFromPid(int pid) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(pid);
            this.mRemote.transact(10083, data, reply, 0);
            reply.readException();
            String processName = reply.readString();
            return processName;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void notifyAppKillReason(int pid, int uid, int reason, int subReason, String msg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(pid);
            data.writeInt(uid);
            data.writeInt(reason);
            data.writeInt(subReason);
            data.writeString(msg);
            this.mRemote.transact(10084, data, reply, 1);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public int getFontVariationAdaptionData(String packagename) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packagename);
            this.mRemote.transact(10085, data, reply, 0);
            int info = reply.readInt();
            reply.readException();
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean registerHansListener(String callerPkg, IOplusHansListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeStrongBinder(listener != null ? listener.asBinder() : null);
            this.mRemote.transact(10101, data, reply, 0);
            reply.readException();
            boolean success = reply.readBoolean();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean unregisterHansListener(String callerPkg, IOplusHansListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeStrongBinder(listener != null ? listener.asBinder() : null);
            this.mRemote.transact(10102, data, reply, 0);
            reply.readException();
            boolean success = reply.readBoolean();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean setAppFreeze(String callerPkg, Bundle bundle) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeBundle(bundle);
            this.mRemote.transact(10078, data, reply, 0);
            reply.readException();
            boolean success = reply.readBoolean();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void notifyAthenaOnekeyClearRunning(int state) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(state);
            this.mRemote.transact(10103, data, reply, 1);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void executeResPreload(String pkgName, int userId, int preloadType, String preloadReason) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            data.writeInt(userId);
            data.writeInt(preloadType);
            data.writeString(preloadReason);
            this.mRemote.transact(10104, data, reply, 1);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public Bundle getResPreloadInfo(int days, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new Bundle();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(days);
            data.writeInt(userId);
            this.mRemote.transact(10110, data, reply, 0);
            reply.readException();
            Bundle bundle = reply.readBundle();
            return bundle;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public long getPreloadIOSize() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10112, data, reply, 0);
            reply.readException();
            long size = reply.readLong();
            return size;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean getPreloadStatus(String pkgName, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            data.writeInt(userId);
            this.mRemote.transact(10118, data, reply, 0);
            reply.readException();
            boolean isPreloaded = reply.readBoolean();
            return isPreloaded;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public Bundle getPreloadPkgList() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new Bundle();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10117, data, reply, 0);
            reply.readException();
            Bundle bundle = reply.readBundle();
            return bundle;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public List<String> getPkgPreloadFiles(String pkgName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkgName);
            this.mRemote.transact(10119, data, reply, 0);
            reply.readException();
            int size = reply.readInt();
            for (int i = 0; i < size; i++) {
                list.add(reply.readString());
            }
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void enterFastFreezer(String callerPkg, int[] uids, long timeout, String reason) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            if (uids != null) {
                data.writeInt(uids.length);
                data.writeIntArray(uids);
            } else {
                data.writeInt(0);
            }
            data.writeLong(timeout);
            data.writeString(reason);
            this.mRemote.transact(10105, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void exitFastFreezer(String callerPkg, String reason) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(callerPkg);
            data.writeString(reason);
            this.mRemote.transact(10106, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean isFrozenByHans(String packageName, int uid) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(packageName);
            data.writeInt(uid);
            this.mRemote.transact(10113, data, reply, 0);
            reply.readException();
            return reply.readBoolean();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public SparseArray<Long> getTrafficBytesList(ArrayList<Integer> uids) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeList(uids);
            this.mRemote.transact(10115, data, reply, 0);
            reply.readException();
            SparseArray<Long> list = reply.readSparseArray(null);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public SparseArray<Long> getTrafficPacketList(ArrayList<Integer> uids) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeList(uids);
            this.mRemote.transact(10116, data, reply, 0);
            reply.readException();
            SparseArray<Long> list = reply.readSparseArray(null);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void registerEapDataCallback(IOplusEapDataCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10107, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void unregisterEapDataCallback(IOplusEapDataCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10108, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void updateANRDumpState(SharedMemory sharedMemory) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            sharedMemory.writeToParcel(data, 0);
            this.mRemote.transact(10126, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void registerErrorInfoCallback(IEventCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10135, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void unregisterErrorInfoCallback(IEventCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10136, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void clientTransactionComplete(IBinder token, int seq) throws RemoteException {
        this.mOplusAtm.clientTransactionComplete(token, seq);
    }

    @Override // android.app.IOplusActivityTaskManager
    public void updateUntrustedTouchConfig(String configData, boolean isRus) throws RemoteException {
        this.mOplusAtm.updateUntrustedTouchConfig(configData, isRus);
    }

    @Override // android.app.IOplusActivityManager
    public List<ActivityManager.RecentTaskInfo> getAllVisibleTasksInfo(int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(userId);
            this.mRemote.transact(10111, data, reply, 0);
            reply.readException();
            List<ActivityManager.RecentTaskInfo> recentTaskInfoList = reply.createTypedArrayList(ActivityManager.RecentTaskInfo.CREATOR);
            return recentTaskInfoList;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void anrViaTheiaEvent(int pid, String reason) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(pid);
            data.writeString(reason);
            this.mRemote.transact(10114, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean registerSecurityPageCallback(ISecurityPageController observer) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager registerSecurityPageCallback");
        return this.mOplusAtm.registerSecurityPageCallback(observer);
    }

    public boolean unregisterSecurityPageCallback(ISecurityPageController observer) throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager unregisterSecurityPageCallback");
        return this.mOplusAtm.unregisterSecurityPageCallback(observer);
    }

    public boolean getSecurityFlagCurrentPage() throws RemoteException {
        Slog.i("MirageDisplayWindow", "OplusActivityManager getSecurityFlagCurrentPage");
        return this.mOplusAtm.getSecurityFlagCurrentPage();
    }

    @Override // android.app.IOplusActivityManager
    public List<String> getStageProtectListFromPkgAsUser(String pkg, int type, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(pkg);
            data.writeInt(type);
            data.writeInt(userId);
            this.mRemote.transact(10123, data, reply, 0);
            reply.readException();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<String> getStageProtectList(int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(type);
            this.mRemote.transact(10124, data, reply, 0);
            reply.readException();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<String> getStageProtectListAsUser(int type, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> list = new ArrayList<>();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(type);
            data.writeInt(userId);
            this.mRemote.transact(10125, data, reply, 0);
            reply.readException();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public OplusUXIconData getUXIconData() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            this.mRemote.transact(10120, data, reply, 0);
            reply.readException();
            OplusUXIconData info = OplusUXIconData.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void updateDeferStartingWindowApps(List<String> packages, boolean removeImmdiately) throws RemoteException {
        this.mOplusAtm.updateDeferStartingWindowApps(packages, removeImmdiately);
    }

    @Override // android.app.IOplusActivityTaskManager
    public String getAppThemeVersion(String pkgName, boolean change) throws RemoteException {
        return this.mOplusAtm.getAppThemeVersion(pkgName, change);
    }

    @Override // android.app.IOplusActivityManager
    public void compactProcess(List<Integer> pids, int compactionFlags, int advice) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeList(pids);
            data.writeInt(compactionFlags);
            data.writeInt(advice);
            this.mRemote.transact(10127, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean inDownloading(int uid, int thresholdSpeed, boolean rough) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(uid);
            data.writeInt(thresholdSpeed);
            data.writeBoolean(rough);
            this.mRemote.transact(10128, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<OplusPackageFreezeData> getDownloadingList(int thresholdSpeed, boolean rough) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(thresholdSpeed);
            data.writeBoolean(rough);
            this.mRemote.transact(10129, data, reply, 0);
            reply.readException();
            List<OplusPackageFreezeData> list = reply.createTypedArrayList(OplusPackageFreezeData.CREATOR);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<UsageStats> queryUsageStats(int intervalType, long beginTime, long endTime) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeInt(intervalType);
            data.writeLong(beginTime);
            data.writeLong(endTime);
            this.mRemote.transact(10130, data, reply, 0);
            reply.readException();
            List<UsageStats> statsList = reply.createTypedArrayList(UsageStats.CREATOR);
            return statsList;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessInfos(List<Integer> pids) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeList(pids);
            this.mRemote.transact(10131, data, reply, 0);
            reply.readException();
            List<ActivityManager.RunningAppProcessInfo> appInfos = reply.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
            return appInfos;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public List<OplusPackageFreezeData> getPackageFreezeDataInfos(List<Integer> pids) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeList(pids);
            this.mRemote.transact(10132, data, reply, 0);
            reply.readException();
            List<OplusPackageFreezeData> freezeDataList = reply.createTypedArrayList(OplusPackageFreezeData.CREATOR);
            return freezeDataList;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public void notifyUiSwitched(String uiInfo, int status) throws RemoteException {
        Parcel data = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeString(uiInfo);
            data.writeInt(status);
            this.mRemote.transact(10133, data, null, 1);
        } finally {
            data.recycle();
        }
    }

    @Override // android.app.IOplusActivityManager
    public boolean addOrRemoveOplusVerifyCodeListener(boolean add, IOplusVerifyCodeListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityManager");
            data.writeStrongBinder(listener != null ? listener.asBinder() : null);
            data.writeBoolean(add);
            this.mRemote.transact(10134, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getFocusMode() throws RemoteException {
        return this.mOplusAtm.getFocusMode();
    }

    @Override // android.app.IOplusActivityTaskManager
    public Rect getFocusBounds(boolean isPrimary) throws RemoteException {
        return this.mOplusAtm.getFocusBounds(isPrimary);
    }

    @Override // android.app.IOplusActivityTaskManager
    public ComponentName getFocusComponent(boolean isPrimary) throws RemoteException {
        return this.mOplusAtm.getFocusComponent(isPrimary);
    }

    @Override // android.app.IOplusActivityTaskManager
    public Point getRealSize() throws RemoteException {
        return this.mOplusAtm.getRealSize();
    }

    @Override // android.app.IOplusActivityTaskManager
    public Bundle callMethod(String method, String packageName, int param1, boolean param2, String param3, Bundle object) throws RemoteException {
        return this.mOplusAtm.callMethod(method, packageName, param1, param2, param3, object);
    }

    @Override // android.app.IOplusActivityTaskManager
    public Bundle invokeSync(String packageName, String method, String params, Bundle object) throws RemoteException {
        return this.mOplusAtm.invokeSync(packageName, method, params, object);
    }

    @Override // android.app.IOplusActivityTaskManager
    public int startCompactWindow() throws RemoteException {
        return this.mOplusAtm.startCompactWindow();
    }

    @Override // android.app.IOplusActivityTaskManager
    public int exitCompactWindow() throws RemoteException {
        return this.mOplusAtm.exitCompactWindow();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isCurrentAppSupportCompactMode() throws RemoteException {
        return this.mOplusAtm.isCurrentAppSupportCompactMode();
    }

    @Override // android.app.IOplusActivityTaskManager
    public int moveCompactWindowToLeft() throws RemoteException {
        return this.mOplusAtm.moveCompactWindowToLeft();
    }

    @Override // android.app.IOplusActivityTaskManager
    public int moveCompactWindowToRight() throws RemoteException {
        return this.mOplusAtm.moveCompactWindowToRight();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerCompactWindowObserver(IOplusCompactWindowObserver observer) throws RemoteException {
        return this.mOplusAtm.registerCompactWindowObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterCompactWindowObserver(IOplusCompactWindowObserver observer) throws RemoteException {
        return this.mOplusAtm.unregisterCompactWindowObserver(observer);
    }

    @Override // android.app.IOplusActivityTaskManager
    public Map<String, ArrayList<String>> getPWAppInfo() throws RemoteException {
        return this.mOplusAtm.getPWAppInfo();
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean onProtocolUpdated(String content) throws RemoteException {
        return this.mOplusAtm.onProtocolUpdated(content);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean addBracketWindowConfigChangedListener(IOplusBracketModeChangedListener listener) throws RemoteException {
        return this.mOplusAtm.addBracketWindowConfigChangedListener(listener);
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean removeBracketWindowConfigChangedListener(IOplusBracketModeChangedListener listener) throws RemoteException {
        return this.mOplusAtm.removeBracketWindowConfigChangedListener(listener);
    }
}
