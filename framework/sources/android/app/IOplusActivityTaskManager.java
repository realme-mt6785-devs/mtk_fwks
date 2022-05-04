package android.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IOplusKeyEventObserver;
import android.os.RemoteException;
import android.view.Surface;
import com.oplus.app.IOplusAppSwitchObserver;
import com.oplus.app.IOplusFreeformConfigChangedListener;
import com.oplus.app.IOplusSplitScreenObserver;
import com.oplus.app.IOplusZoomWindowConfigChangedListener;
import com.oplus.app.OplusAppInfo;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.bracket.IOplusBracketModeChangedListener;
import com.oplus.compactwindow.IOplusCompactWindowObserver;
import com.oplus.confinemode.IOplusConfineModeObserver;
import com.oplus.globaldrag.OplusGlobalDragAndDropRUSConfig;
import com.oplus.miragewindow.IOplusMirageDisplayObserver;
import com.oplus.miragewindow.IOplusMirageSessionCallback;
import com.oplus.miragewindow.IOplusMirageWindowObserver;
import com.oplus.miragewindow.IOplusMirageWindowSession;
import com.oplus.miragewindow.OplusMirageWindowInfo;
import com.oplus.splitscreen.IOplusSplitScreenSession;
import com.oplus.zoomwindow.IOplusZoomAppObserver;
import com.oplus.zoomwindow.IOplusZoomWindowObserver;
import com.oplus.zoomwindow.OplusZoomControlViewInfo;
import com.oplus.zoomwindow.OplusZoomFloatHandleViewInfo;
import com.oplus.zoomwindow.OplusZoomInputEventInfo;
import com.oplus.zoomwindow.OplusZoomWindowInfo;
import com.oplus.zoomwindow.OplusZoomWindowRUSConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public interface IOplusActivityTaskManager extends IOplusBaseActivityTaskManager {
    public static final int ADD_BRACKET_WINDOW_CONFIG_CHANGED_LISTENER = 10179;
    public static final int ADD_FEEFORM_CONFIG_CHANGED_LISTENER = 10056;
    public static final int ADD_ZOOM_WINDOW_CONFIG_CHANGED_LISTENER = 10079;
    public static final int ATM_TANSACTION_DONE = 10130;
    public static final int CALL_METHOD = 10211;
    public static final int CREATE_MIRAGE_DISPLAY = 10114;
    public static final int CREATE_MIRAGE_WINDOW_SESSION = 10137;
    public static final int EXIT_COMPACT_WINDOW = 10214;
    public static final int EXPAND_TO_FULL_SCREEN = 10118;
    public static final int FEEDBACK_USER_SELECTION = 10203;
    public static final int GET_ALL_TOP_APP_INFOS = 10053;
    public static final int GET_CONFINE_MODE = 10090;
    public static final int GET_DISPLAY_ID_FOR_PACKAGE_NAME = 10202;
    public static final int GET_FOCUS_BOUNDS = 10208;
    public static final int GET_FOCUS_COMPONENT = 10209;
    public static final int GET_FOCUS_MODE = 10207;
    public static final int GET_FREEFORM_CONFIG_LIST = 10054;
    public static final int GET_MIRAGE_DISPLAY_CAST_MODE = 10153;
    public static final int GET_MIRAGE_WINDOW_INFO = 10121;
    public static final int GET_PW_APP_INFO = 10221;
    public static final int GET_REAL_SIZE = 10210;
    public static final int GET_SECURITY_FLAG_CURRENT_PAGE = 10142;
    public static final int GET_SPLIT_WINDOW_STATE = 10074;
    public static final int GET_TOP_ACTIVITY_COMPONENTNAME_TRANSACTION = 10007;
    public static final int GET_TOP_APPLICATION_INFO = 10011;
    public static final int INVOKE_SYNC = 10212;
    public static final int IS_APP_SUPPORT_COMPACT_MODE = 10215;
    public static final int IS_FREEFORM_ENABLED = 10055;
    public static final int IS_LOCK_DEVICE_MODE_TRANSACTION = 10145;
    public static final int IS_MIRAGE_WINDOW_SHOW = 10116;
    public static final int IS_SHOW_ZOOM_COMPATIBILITY_TOAST = 10136;
    public static final int IS_SUPPORT_EDGE_TOUCH_PREVENT = 10086;
    public static final int IS_SUPPORT_MIRAGE_WINDOW_MODE = 10120;
    public static final int IS_TARGET_SUPPORT_ZOOM_MODE = 10135;
    public static final int IS_TOUCH_NODE = 10156;
    public static final int IS_ZOOM_WINDOW_ENABLED = 10078;
    public static final int LOCK_GAME_ROTATION = 10162;
    public static final int MOVE_COMPACT_WINDOW_TO_LEFT = 10216;
    public static final int MOVE_COMPACT_WINDOW_TO_RIGHT = 10217;
    public static final int ON_PROTOCOL_UPDATED = 10220;
    public static final int ON_ZOOM_CONTROL_VIEW_CHANGED = 10132;
    public static final int ON_ZOOM_FLOAT_HANDLE_VIEW_CHANGED = 10139;
    public static final int OPLUS_CUSTOM_THEME_PACKAGES = 10159;
    public static final int OPLUS_GLOBAL_DRAG_SHARE_GET_RUS_CONFIG = 10110;
    public static final int OPLUS_GLOBAL_DRAG_SHARE_SET_RUS_CONFIG = 10111;
    public static final int OPLUS_START_ZOOM_WINDOW = 10068;
    public static final int OPLUS_ZOOM_WINDOW_BUBBLE = 10072;
    public static final int OPLUS_ZOOM_WINDOW_CONFIG = 10075;
    public static final int OPLUS_ZOOM_WINDOW_GET_RUS_CONFIG = 10076;
    public static final int OPLUS_ZOOM_WINDOW_HIDE = 10073;
    public static final int OPLUS_ZOOM_WINDOW_INPUT = 10131;
    public static final int OPLUS_ZOOM_WINDOW_SET_RUS_CONFIG = 10077;
    public static final int OPLUS_ZOOM_WINDOW_STATE = 10071;
    public static final int READ_EXTRA_NODE_FILE = 10154;
    public static final int READ_NODE_FILE = 10126;
    public static final int REBIND_DISPLAY_IF_NEEDED = 10205;
    public static final int REGISTER_APP_SWITCH_OBSERVER = 10064;
    public static final int REGISTER_COMPACT_WINDOW_OBSERVER = 10218;
    public static final int REGISTER_CONFINE_MODE_OBSERVER = 10122;
    public static final int REGISTER_DATA_SYNC_CALLBACK = 10108;
    public static final int REGISTER_KEY_EVENT_INTERCEPTOR = 10105;
    public static final int REGISTER_KEY_EVENT_OBSERVER = 10103;
    public static final int REGISTER_LOCKSCREEN_CALLBACK = 10094;
    public static final int REGISTER_MIRAGE_DISPLAY_OBSERVER = 10151;
    public static final int REGISTER_MIRAGE_WINDOW_OBSERVER = 10112;
    public static final int REGISTER_SECURITY_PAGE_CALL_BACK = 10140;
    public static final int REGISTER_ZOOM_APP_OBSERVER = 10133;
    public static final int REGISTER_ZOOM_OBSERVER = 10069;
    public static final int REMOVE_BRACKET_WINDOW_CONFIG_CHANGED_LISTENER = 10180;
    public static final int REMOVE_FREEFORM_CONFIG_CHANGED_LISTENER = 10057;
    public static final int REMOVE_ZOOM_WINDOW_CONFIG_CHANGED_LISTENER = 10080;
    public static final int RESET_DEFAULT_EDGE_TOUCH_PREVENT_PARAM = 10085;
    public static final int SET_CONFINE_MODE = 10089;
    public static final int SET_DEFAULT_EDGE_TOUCH_PREVENT_PARAM = 10084;
    public static final int SET_EDGE_TOUCH_CALL_RULES = 10087;
    public static final int SET_EDGE_TOUCH_PREVENT_PARAM = 10083;
    public static final int SET_GIMBAL_LAUNCH_PKG = 10092;
    public static final int SET_MIRAGE_DISPLAY_SURFACE_BY_ID = 10149;
    public static final int SET_MIRAGE_DISPLAY_SURFACE_BY_MODE = 10150;
    public static final int SET_MIRAGE_WINDOW_SILENT = 10119;
    public static final int SET_PERMIT_LIST = 10091;
    public static final int SET_SECURE_CONTROLLER_TRANSACTION = 10002;
    public static final int SPLITS_SCREEN_HAS_NEW_DRAG_SPLIT_FEATURE = 10201;
    public static final int SPLIT_SCREEN_DISMISS = 10099;
    public static final int SPLIT_SCREEN_FOR_EDGE_PANEL = 10163;
    public static final int SPLIT_SCREEN_FOR_FLOAT_ASSISTENT = 10088;
    public static final int SPLIT_SCREEN_FOR_RECENT_TASKS = 10129;
    public static final int SPLIT_SCREEN_FOR_TOP_APP = 10128;
    public static final int SPLIT_SCREEN_GET_DESIGNATED_STATUS = 10102;
    public static final int SPLIT_SCREEN_GET_MINIMIZED_BOUNDS = 10166;
    public static final int SPLIT_SCREEN_GET_MODE_STATUS = 10098;
    public static final int SPLIT_SCREEN_GET_SESSION = 10206;
    public static final int SPLIT_SCREEN_HAS_FOLDER_FEATURE = 10164;
    public static final int SPLIT_SCREEN_IS_FOLDER_INNER_SCREEN = 10165;
    public static final int SPLIT_SCREEN_NOTIFY_STATE_CHANGED = 10096;
    public static final int SPLIT_SCREEN_REGISTER_OBSERVER = 10100;
    public static final int SPLIT_SCREEN_SET_HANDLE_REGION = 10167;
    public static final int SPLIT_SCREEN_SET_OBSERVER = 10097;
    public static final int SPLIT_SCREEN_SET_TASK_WINDOWING_MODE = 10157;
    public static final int SPLIT_SCREEN_UNREGISTER_OBSERVER = 10101;
    public static final int START_COMPACT_WINDOW = 10213;
    public static final int START_LOCK_DEVICE_MODE_TRANSACTION = 10081;
    public static final int START_MINI_FROM_ZOOM = 10146;
    public static final int START_MIRAGE_WINDOW_MODE = 10115;
    public static final int START_MIRAGE_WINDOW_MODE_NEW = 10147;
    public static final int STOP_LOCK_DEVICE_MODE_TRANSACTION = 10082;
    public static final int STOP_MIRAGE_WINDOW_MODE = 10117;
    public static final int STOP_MIRAGE_WINDOW_MODE_NEW = 10148;
    public static final int SWAP_STACK = 10052;
    public static final int UNREGISTER_APP_SWITCH_OBSERVER = 10065;
    public static final int UNREGISTER_COMPACT_WINDOW_OBSERVER = 10219;
    public static final int UNREGISTER_CONFINE_MODE_OBSERVER = 10123;
    public static final int UNREGISTER_DATA_SYNC_CALLBACK = 10109;
    public static final int UNREGISTER_KEY_EVENT_INTERCEPTOR = 10106;
    public static final int UNREGISTER_KEY_EVENT_OBSERVER = 10104;
    public static final int UNREGISTER_LOCKSCREEN_CALLBACK = 10095;
    public static final int UNREGISTER_MIRAGE_DISPLAY_OBSERVER = 10152;
    public static final int UNREGISTER_MIRAGE_WINDOW_OBSERVER = 10113;
    public static final int UNREGISTER_SECURITY_PAGE_CALL_BACK = 10141;
    public static final int UNREGISTER_ZOOM_APP_OBSERVER = 10134;
    public static final int UNREGISTER_ZOOM_OBSERVER = 10070;
    public static final int UPDATE_APP_STATE = 10107;
    public static final int UPDATE_APP_STATE_FOR_INTERCEPT = 10093;
    public static final int UPDATE_CAR_MODE_MULTI_LAUNCH_WHITE_LIST = 10204;
    public static final int UPDATE_DEFER_STARTING_WINDOWS_APPS = 10190;
    public static final int UPDATE_MIRAGE_CAST_FLAG = 10124;
    public static final int UPDATE_MIRAGE_PRIVACY_LIST = 10138;
    public static final int UPDATE_PRIVACY_PROTECTION_LIST = 10125;
    public static final int UPDATE_UNTRUSTED_TOUCH_CONFIG = 10161;
    public static final int WRITE_EXTRA_NODE_FILE = 10155;
    public static final int WRITE_NODE_FILE = 10127;
    public static final int ZOOM_SIMPLE_MODE_ENABLE = 10158;

    boolean addBracketWindowConfigChangedListener(IOplusBracketModeChangedListener iOplusBracketModeChangedListener) throws RemoteException;

    boolean addFreeformConfigChangedListener(IOplusFreeformConfigChangedListener iOplusFreeformConfigChangedListener) throws RemoteException;

    boolean addZoomWindowConfigChangedListener(IOplusZoomWindowConfigChangedListener iOplusZoomWindowConfigChangedListener) throws RemoteException;

    Bundle callMethod(String str, String str2, int i, boolean z, String str3, Bundle bundle) throws RemoteException;

    void clientTransactionComplete(IBinder iBinder, int i) throws RemoteException;

    int createMirageDisplay(Surface surface) throws RemoteException;

    IOplusMirageWindowSession createMirageWindowSession(IOplusMirageSessionCallback iOplusMirageSessionCallback) throws RemoteException;

    boolean dismissSplitScreenMode(int i) throws RemoteException;

    int exitCompactWindow() throws RemoteException;

    void expandToFullScreen() throws RemoteException;

    void feedbackUserSelection(int i, int i2, Bundle bundle) throws RemoteException;

    List<OplusAppInfo> getAllTopAppInfos() throws RemoteException;

    String getAppThemeVersion(String str, boolean z) throws RemoteException;

    int getConfineMode() throws RemoteException;

    OplusZoomWindowInfo getCurrentZoomWindowState() throws RemoteException;

    Rect getFocusBounds(boolean z) throws RemoteException;

    ComponentName getFocusComponent(boolean z) throws RemoteException;

    int getFocusMode() throws RemoteException;

    List<String> getFreeformConfigList(int i) throws RemoteException;

    int getGetDisplayIdForPackageName(String str) throws RemoteException;

    OplusGlobalDragAndDropRUSConfig getGlobalDragAndDropConfig() throws RemoteException;

    int getMirageDisplayCastMode(int i) throws RemoteException;

    OplusMirageWindowInfo getMirageWindowInfo() throws RemoteException;

    Map<String, ArrayList<String>> getPWAppInfo() throws RemoteException;

    Point getRealSize() throws RemoteException;

    int getSplitScreenState(Intent intent) throws RemoteException;

    Bundle getSplitScreenStatus(String str) throws RemoteException;

    ComponentName getTopActivityComponentName() throws RemoteException;

    ApplicationInfo getTopApplicationInfo() throws RemoteException;

    List<String> getZoomAppConfigList(int i) throws RemoteException;

    OplusZoomWindowRUSConfig getZoomWindowConfig() throws RemoteException;

    boolean handleShowCompatibilityToast(String str, int i, String str2, Bundle bundle, int i2) throws RemoteException;

    void hideZoomWindow(int i) throws RemoteException;

    Bundle invokeSync(String str, String str2, String str3, Bundle bundle) throws RemoteException;

    boolean isCurrentAppSupportCompactMode() throws RemoteException;

    boolean isFreeformEnabled() throws RemoteException;

    boolean isInSplitScreenMode() throws RemoteException;

    boolean isMirageWindowShow() throws RemoteException;

    boolean isSupportEdgeTouchPrevent() throws RemoteException;

    boolean isSupportMirageWindowMode() throws RemoteException;

    boolean isSupportZoomMode(String str, int i, String str2, Bundle bundle) throws RemoteException;

    boolean isSupportZoomWindowMode() throws RemoteException;

    boolean isTouchNodeSupport(int i, int i2) throws RemoteException;

    int moveCompactWindowToLeft() throws RemoteException;

    int moveCompactWindowToRight() throws RemoteException;

    void notifySplitScreenStateChanged(String str, Bundle bundle, boolean z) throws RemoteException;

    void onControlViewChanged(OplusZoomControlViewInfo oplusZoomControlViewInfo) throws RemoteException;

    void onFloatHandleViewChanged(OplusZoomFloatHandleViewInfo oplusZoomFloatHandleViewInfo) throws RemoteException;

    void onInputEvent(OplusZoomInputEventInfo oplusZoomInputEventInfo) throws RemoteException;

    boolean onProtocolUpdated(String str) throws RemoteException;

    String readNodeFile(int i) throws RemoteException;

    String readNodeFileByDevice(int i, int i2) throws RemoteException;

    boolean registerAppSwitchObserver(String str, IOplusAppSwitchObserver iOplusAppSwitchObserver, OplusAppSwitchConfig oplusAppSwitchConfig) throws RemoteException;

    boolean registerCompactWindowObserver(IOplusCompactWindowObserver iOplusCompactWindowObserver) throws RemoteException;

    boolean registerConfineModeObserver(IOplusConfineModeObserver iOplusConfineModeObserver) throws RemoteException;

    boolean registerKeyEventInterceptor(String str, IOplusKeyEventObserver iOplusKeyEventObserver, Map<Integer, Integer> map) throws RemoteException;

    boolean registerKeyEventObserver(String str, IOplusKeyEventObserver iOplusKeyEventObserver, int i) throws RemoteException;

    boolean registerMirageDisplayObserver(IOplusMirageDisplayObserver iOplusMirageDisplayObserver) throws RemoteException;

    boolean registerMirageWindowObserver(IOplusMirageWindowObserver iOplusMirageWindowObserver) throws RemoteException;

    boolean registerSplitScreenObserver(int i, IOplusSplitScreenObserver iOplusSplitScreenObserver) throws RemoteException;

    boolean registerZoomAppObserver(IOplusZoomAppObserver iOplusZoomAppObserver) throws RemoteException;

    boolean registerZoomWindowObserver(IOplusZoomWindowObserver iOplusZoomWindowObserver) throws RemoteException;

    boolean removeBracketWindowConfigChangedListener(IOplusBracketModeChangedListener iOplusBracketModeChangedListener) throws RemoteException;

    boolean removeFreeformConfigChangedListener(IOplusFreeformConfigChangedListener iOplusFreeformConfigChangedListener) throws RemoteException;

    boolean removeZoomWindowConfigChangedListener(IOplusZoomWindowConfigChangedListener iOplusZoomWindowConfigChangedListener) throws RemoteException;

    boolean resetDefaultEdgeTouchPreventParam(String str) throws RemoteException;

    void setConfineMode(int i, boolean z) throws RemoteException;

    void setDefaultEdgeTouchPreventParam(String str, List<String> list) throws RemoteException;

    void setEdgeTouchCallRules(String str, Map<String, List<String>> map) throws RemoteException;

    void setGimbalLaunchPkg(String str) throws RemoteException;

    void setGlobalDragAndDropConfig(OplusGlobalDragAndDropRUSConfig oplusGlobalDragAndDropRUSConfig) throws RemoteException;

    void setMirageDisplaySurfaceById(int i, Surface surface) throws RemoteException;

    void setMirageDisplaySurfaceByMode(int i, Surface surface) throws RemoteException;

    void setMirageWindowSilent(String str) throws RemoteException;

    void setPermitList(int i, int i2, List<String> list, boolean z) throws RemoteException;

    void setSecureController(IActivityController iActivityController) throws RemoteException;

    boolean setSplitScreenObserver(IOplusSplitScreenObserver iOplusSplitScreenObserver) throws RemoteException;

    boolean setTaskWindowingModeSplitScreen(int i) throws RemoteException;

    void setZoomWindowConfig(OplusZoomWindowRUSConfig oplusZoomWindowRUSConfig) throws RemoteException;

    boolean splitScreenForRecentTasks(int i) throws RemoteException;

    boolean splitScreenForTopApp(int i) throws RemoteException;

    int startCompactWindow() throws RemoteException;

    void startMiniZoomFromZoom(int i) throws RemoteException;

    int startMirageWindowMode(Intent intent, Bundle bundle) throws RemoteException;

    void startMirageWindowMode(ComponentName componentName, int i, int i2, Bundle bundle) throws RemoteException;

    int startZoomWindow(Intent intent, Bundle bundle, int i, String str) throws RemoteException;

    void stopMirageWindowMode() throws RemoteException;

    void stopMirageWindowMode(Bundle bundle) throws RemoteException;

    boolean unregisterAppSwitchObserver(String str, OplusAppSwitchConfig oplusAppSwitchConfig) throws RemoteException;

    boolean unregisterCompactWindowObserver(IOplusCompactWindowObserver iOplusCompactWindowObserver) throws RemoteException;

    boolean unregisterConfineModeObserver(IOplusConfineModeObserver iOplusConfineModeObserver) throws RemoteException;

    boolean unregisterKeyEventInterceptor(String str) throws RemoteException;

    boolean unregisterKeyEventObserver(String str) throws RemoteException;

    boolean unregisterMirageDisplayObserver(IOplusMirageDisplayObserver iOplusMirageDisplayObserver) throws RemoteException;

    boolean unregisterMirageWindowObserver(IOplusMirageWindowObserver iOplusMirageWindowObserver) throws RemoteException;

    boolean unregisterSplitScreenObserver(int i, IOplusSplitScreenObserver iOplusSplitScreenObserver) throws RemoteException;

    boolean unregisterZoomAppObserver(IOplusZoomAppObserver iOplusZoomAppObserver) throws RemoteException;

    boolean unregisterZoomWindowObserver(IOplusZoomWindowObserver iOplusZoomWindowObserver) throws RemoteException;

    boolean updateCarModeMultiLaunchWhiteList(String str) throws RemoteException;

    boolean updateMirageWindowCastFlag(int i, Bundle bundle) throws RemoteException;

    boolean updatePrivacyProtectionList(List<String> list, boolean z) throws RemoteException;

    boolean updatePrivacyProtectionList(List<String> list, boolean z, boolean z2, Bundle bundle) throws RemoteException;

    void updateUntrustedTouchConfig(String str, boolean z) throws RemoteException;

    boolean writeEdgeTouchPreventParam(String str, String str2, List<String> list) throws RemoteException;

    boolean writeNodeFile(int i, String str) throws RemoteException;

    boolean writeNodeFileByDevice(int i, int i2, String str) throws RemoteException;

    default boolean isZoomSimpleModeEnable() throws RemoteException {
        return false;
    }

    default int splitScreenForEdgePanel(Intent intent, int userId) throws RemoteException {
        return 0;
    }

    default boolean splitScreenForEdgePanel(Intent intent, boolean launchToPrimary, int launchArea) throws RemoteException {
        return false;
    }

    default boolean hasFolderFeature() throws RemoteException {
        return false;
    }

    default boolean isFolderInnerScreen() throws RemoteException {
        return false;
    }

    default Rect getMinimizedBounds(int dockedSide) throws RemoteException {
        return new Rect();
    }

    default void setHandleRegion(int windowingMode, Rect handleRegion) throws RemoteException {
    }

    default IOplusSplitScreenSession getSplitScreenSession() throws RemoteException {
        return null;
    }

    default boolean rebindDisplayIfNeeded(int castDisplayId, int mirageDisplayId) throws RemoteException {
        return false;
    }
}
