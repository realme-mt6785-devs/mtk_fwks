package com.android.internal.statusbar;

import android.app.ITransientNotificationCallback;
import android.content.ComponentName;
import android.hardware.biometrics.IBiometricSysuiReceiver;
import android.hardware.biometrics.PromptInfo;
import android.hardware.fingerprint.IUdfpsHbmListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.internal.view.AppearanceRegion;
/* loaded from: classes4.dex */
public interface IStatusBar extends IInterface {
    void abortTransient(int i, int[] iArr) throws RemoteException;

    void addQsTile(ComponentName componentName) throws RemoteException;

    void animateCollapsePanels() throws RemoteException;

    void animateExpandNotificationsPanel() throws RemoteException;

    void animateExpandSettingsPanel(String str) throws RemoteException;

    void appTransitionCancelled(int i) throws RemoteException;

    void appTransitionFinished(int i) throws RemoteException;

    void appTransitionPending(int i) throws RemoteException;

    void appTransitionStarting(int i, long j, long j2) throws RemoteException;

    void cancelPreloadRecentApps() throws RemoteException;

    void clickQsTile(ComponentName componentName) throws RemoteException;

    void disable(int i, int i2, int i3) throws RemoteException;

    void dismissInattentiveSleepWarning(boolean z) throws RemoteException;

    void dismissKeyboardShortcutsMenu() throws RemoteException;

    void handleSystemKey(int i) throws RemoteException;

    void handleWindowManagerLoggingCommand(String[] strArr, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void hideAuthenticationDialog() throws RemoteException;

    void hideRecentApps(boolean z, boolean z2) throws RemoteException;

    void hideToast(String str, IBinder iBinder) throws RemoteException;

    void onBiometricAuthenticated() throws RemoteException;

    void onBiometricError(int i, int i2, int i3) throws RemoteException;

    void onBiometricHelp(int i, String str) throws RemoteException;

    void onCameraLaunchGestureDetected(int i) throws RemoteException;

    void onDisplayReady(int i) throws RemoteException;

    void onEmergencyActionLaunchGestureDetected() throws RemoteException;

    void onProposedRotationChanged(int i, boolean z) throws RemoteException;

    void onRecentsAnimationStateChanged(boolean z) throws RemoteException;

    void onSystemBarAttributesChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z, int i3, boolean z2) throws RemoteException;

    void passThroughShellCommand(String[] strArr, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void preloadRecentApps() throws RemoteException;

    void remQsTile(ComponentName componentName) throws RemoteException;

    void removeIcon(String str) throws RemoteException;

    void requestWindowMagnificationConnection(boolean z) throws RemoteException;

    void runGcForTest() throws RemoteException;

    void setIcon(String str, StatusBarIcon statusBarIcon) throws RemoteException;

    void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z, boolean z2) throws RemoteException;

    void setNavigationBarLumaSamplingEnabled(int i, boolean z) throws RemoteException;

    void setTopAppHidesStatusBar(boolean z) throws RemoteException;

    void setUdfpsHbmListener(IUdfpsHbmListener iUdfpsHbmListener) throws RemoteException;

    void setWindowState(int i, int i2, int i3) throws RemoteException;

    void showAssistDisclosure() throws RemoteException;

    void showAuthenticationDialog(PromptInfo promptInfo, IBiometricSysuiReceiver iBiometricSysuiReceiver, int[] iArr, boolean z, boolean z2, int i, String str, long j, int i2) throws RemoteException;

    void showGlobalActionsMenu() throws RemoteException;

    void showInattentiveSleepWarning() throws RemoteException;

    void showPictureInPictureMenu() throws RemoteException;

    void showPinningEnterExitToast(boolean z) throws RemoteException;

    void showPinningEscapeToast() throws RemoteException;

    void showRecentApps(boolean z) throws RemoteException;

    void showScreenPinningRequest(int i) throws RemoteException;

    void showShutdownUi(boolean z, String str) throws RemoteException;

    void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException;

    void showTransient(int i, int[] iArr) throws RemoteException;

    void showWirelessChargingAnimation(int i) throws RemoteException;

    void startAssist(Bundle bundle) throws RemoteException;

    void startTracing() throws RemoteException;

    void stopTracing() throws RemoteException;

    void suppressAmbientDisplay(boolean z) throws RemoteException;

    void toggleKeyboardShortcutsMenu(int i) throws RemoteException;

    void togglePanel() throws RemoteException;

    void toggleRecentApps() throws RemoteException;

    void toggleSplitScreen() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IStatusBar {
        @Override // com.android.internal.statusbar.IStatusBar
        public void setIcon(String slot, StatusBarIcon icon) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void removeIcon(String slot) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void disable(int displayId, int state1, int state2) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void animateExpandNotificationsPanel() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void animateExpandSettingsPanel(String subPanel) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void animateCollapsePanels() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void togglePanel() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showWirelessChargingAnimation(int batteryLevel) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setImeWindowStatus(int displayId, IBinder token, int vis, int backDisposition, boolean showImeSwitcher, boolean isMultiClientImeEnabled) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setWindowState(int display, int window, int state) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showRecentApps(boolean triggeredFromAltTab) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void hideRecentApps(boolean triggeredFromAltTab, boolean triggeredFromHomeKey) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void toggleRecentApps() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void toggleSplitScreen() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void preloadRecentApps() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void cancelPreloadRecentApps() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showScreenPinningRequest(int taskId) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void dismissKeyboardShortcutsMenu() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void toggleKeyboardShortcutsMenu(int deviceId) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionPending(int displayId) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionCancelled(int displayId) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionStarting(int displayId, long statusBarAnimationsStartTime, long statusBarAnimationsDuration) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void appTransitionFinished(int displayId) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showAssistDisclosure() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void startAssist(Bundle args) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onCameraLaunchGestureDetected(int source) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onEmergencyActionLaunchGestureDetected() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showPictureInPictureMenu() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showGlobalActionsMenu() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onProposedRotationChanged(int rotation, boolean isValid) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setTopAppHidesStatusBar(boolean hidesStatusBar) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void addQsTile(ComponentName tile) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void remQsTile(ComponentName tile) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void clickQsTile(ComponentName tile) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void handleSystemKey(int key) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showPinningEnterExitToast(boolean entering) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showPinningEscapeToast() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showShutdownUi(boolean isReboot, String reason) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showAuthenticationDialog(PromptInfo promptInfo, IBiometricSysuiReceiver sysuiReceiver, int[] sensorIds, boolean credentialAllowed, boolean requireConfirmation, int userId, String opPackageName, long operationId, int multiSensorConfig) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onBiometricAuthenticated() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onBiometricHelp(int modality, String message) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onBiometricError(int modality, int error, int vendorCode) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void hideAuthenticationDialog() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setUdfpsHbmListener(IUdfpsHbmListener listener) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onDisplayReady(int displayId) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onRecentsAnimationStateChanged(boolean running) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void onSystemBarAttributesChanged(int displayId, int appearance, AppearanceRegion[] appearanceRegions, boolean navbarColorManagedByIme, int behavior, boolean isFullscreen) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showTransient(int displayId, int[] types) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void abortTransient(int displayId, int[] types) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showInattentiveSleepWarning() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void dismissInattentiveSleepWarning(boolean animated) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void showToast(int uid, String packageName, IBinder token, CharSequence text, IBinder windowToken, int duration, ITransientNotificationCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void hideToast(String packageName, IBinder token) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void startTracing() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void stopTracing() throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void handleWindowManagerLoggingCommand(String[] args, ParcelFileDescriptor outFd) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void suppressAmbientDisplay(boolean suppress) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void requestWindowMagnificationConnection(boolean connect) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void passThroughShellCommand(String[] args, ParcelFileDescriptor pfd) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void setNavigationBarLumaSamplingEnabled(int displayId, boolean enable) throws RemoteException {
        }

        @Override // com.android.internal.statusbar.IStatusBar
        public void runGcForTest() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IStatusBar {
        public static final String DESCRIPTOR = "com.android.internal.statusbar.IStatusBar";
        static final int TRANSACTION_abortTransient = 49;
        static final int TRANSACTION_addQsTile = 32;
        static final int TRANSACTION_animateCollapsePanels = 6;
        static final int TRANSACTION_animateExpandNotificationsPanel = 4;
        static final int TRANSACTION_animateExpandSettingsPanel = 5;
        static final int TRANSACTION_appTransitionCancelled = 21;
        static final int TRANSACTION_appTransitionFinished = 23;
        static final int TRANSACTION_appTransitionPending = 20;
        static final int TRANSACTION_appTransitionStarting = 22;
        static final int TRANSACTION_cancelPreloadRecentApps = 16;
        static final int TRANSACTION_clickQsTile = 34;
        static final int TRANSACTION_disable = 3;
        static final int TRANSACTION_dismissInattentiveSleepWarning = 51;
        static final int TRANSACTION_dismissKeyboardShortcutsMenu = 18;
        static final int TRANSACTION_handleSystemKey = 35;
        static final int TRANSACTION_handleWindowManagerLoggingCommand = 56;
        static final int TRANSACTION_hideAuthenticationDialog = 43;
        static final int TRANSACTION_hideRecentApps = 12;
        static final int TRANSACTION_hideToast = 53;
        static final int TRANSACTION_onBiometricAuthenticated = 40;
        static final int TRANSACTION_onBiometricError = 42;
        static final int TRANSACTION_onBiometricHelp = 41;
        static final int TRANSACTION_onCameraLaunchGestureDetected = 26;
        static final int TRANSACTION_onDisplayReady = 45;
        static final int TRANSACTION_onEmergencyActionLaunchGestureDetected = 27;
        static final int TRANSACTION_onProposedRotationChanged = 30;
        static final int TRANSACTION_onRecentsAnimationStateChanged = 46;
        static final int TRANSACTION_onSystemBarAttributesChanged = 47;
        static final int TRANSACTION_passThroughShellCommand = 59;
        static final int TRANSACTION_preloadRecentApps = 15;
        static final int TRANSACTION_remQsTile = 33;
        static final int TRANSACTION_removeIcon = 2;
        static final int TRANSACTION_requestWindowMagnificationConnection = 58;
        static final int TRANSACTION_runGcForTest = 61;
        static final int TRANSACTION_setIcon = 1;
        static final int TRANSACTION_setImeWindowStatus = 9;
        static final int TRANSACTION_setNavigationBarLumaSamplingEnabled = 60;
        static final int TRANSACTION_setTopAppHidesStatusBar = 31;
        static final int TRANSACTION_setUdfpsHbmListener = 44;
        static final int TRANSACTION_setWindowState = 10;
        static final int TRANSACTION_showAssistDisclosure = 24;
        static final int TRANSACTION_showAuthenticationDialog = 39;
        static final int TRANSACTION_showGlobalActionsMenu = 29;
        static final int TRANSACTION_showInattentiveSleepWarning = 50;
        static final int TRANSACTION_showPictureInPictureMenu = 28;
        static final int TRANSACTION_showPinningEnterExitToast = 36;
        static final int TRANSACTION_showPinningEscapeToast = 37;
        static final int TRANSACTION_showRecentApps = 11;
        static final int TRANSACTION_showScreenPinningRequest = 17;
        static final int TRANSACTION_showShutdownUi = 38;
        static final int TRANSACTION_showToast = 52;
        static final int TRANSACTION_showTransient = 48;
        static final int TRANSACTION_showWirelessChargingAnimation = 8;
        static final int TRANSACTION_startAssist = 25;
        static final int TRANSACTION_startTracing = 54;
        static final int TRANSACTION_stopTracing = 55;
        static final int TRANSACTION_suppressAmbientDisplay = 57;
        static final int TRANSACTION_toggleKeyboardShortcutsMenu = 19;
        static final int TRANSACTION_togglePanel = 7;
        static final int TRANSACTION_toggleRecentApps = 13;
        static final int TRANSACTION_toggleSplitScreen = 14;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStatusBar asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IStatusBar)) {
                return new Proxy(obj);
            }
            return (IStatusBar) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setIcon";
                case 2:
                    return "removeIcon";
                case 3:
                    return "disable";
                case 4:
                    return "animateExpandNotificationsPanel";
                case 5:
                    return "animateExpandSettingsPanel";
                case 6:
                    return "animateCollapsePanels";
                case 7:
                    return "togglePanel";
                case 8:
                    return "showWirelessChargingAnimation";
                case 9:
                    return "setImeWindowStatus";
                case 10:
                    return "setWindowState";
                case 11:
                    return "showRecentApps";
                case 12:
                    return "hideRecentApps";
                case 13:
                    return "toggleRecentApps";
                case 14:
                    return "toggleSplitScreen";
                case 15:
                    return "preloadRecentApps";
                case 16:
                    return "cancelPreloadRecentApps";
                case 17:
                    return "showScreenPinningRequest";
                case 18:
                    return "dismissKeyboardShortcutsMenu";
                case 19:
                    return "toggleKeyboardShortcutsMenu";
                case 20:
                    return "appTransitionPending";
                case 21:
                    return "appTransitionCancelled";
                case 22:
                    return "appTransitionStarting";
                case 23:
                    return "appTransitionFinished";
                case 24:
                    return "showAssistDisclosure";
                case 25:
                    return "startAssist";
                case 26:
                    return "onCameraLaunchGestureDetected";
                case 27:
                    return "onEmergencyActionLaunchGestureDetected";
                case 28:
                    return "showPictureInPictureMenu";
                case 29:
                    return "showGlobalActionsMenu";
                case 30:
                    return "onProposedRotationChanged";
                case 31:
                    return "setTopAppHidesStatusBar";
                case 32:
                    return "addQsTile";
                case 33:
                    return "remQsTile";
                case 34:
                    return "clickQsTile";
                case 35:
                    return "handleSystemKey";
                case 36:
                    return "showPinningEnterExitToast";
                case 37:
                    return "showPinningEscapeToast";
                case 38:
                    return "showShutdownUi";
                case 39:
                    return "showAuthenticationDialog";
                case 40:
                    return "onBiometricAuthenticated";
                case 41:
                    return "onBiometricHelp";
                case 42:
                    return "onBiometricError";
                case 43:
                    return "hideAuthenticationDialog";
                case 44:
                    return "setUdfpsHbmListener";
                case 45:
                    return "onDisplayReady";
                case 46:
                    return "onRecentsAnimationStateChanged";
                case 47:
                    return "onSystemBarAttributesChanged";
                case 48:
                    return "showTransient";
                case 49:
                    return "abortTransient";
                case 50:
                    return "showInattentiveSleepWarning";
                case 51:
                    return "dismissInattentiveSleepWarning";
                case 52:
                    return "showToast";
                case 53:
                    return "hideToast";
                case 54:
                    return "startTracing";
                case 55:
                    return "stopTracing";
                case 56:
                    return "handleWindowManagerLoggingCommand";
                case 57:
                    return "suppressAmbientDisplay";
                case 58:
                    return "requestWindowMagnificationConnection";
                case 59:
                    return "passThroughShellCommand";
                case 60:
                    return "setNavigationBarLumaSamplingEnabled";
                case 61:
                    return "runGcForTest";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            StatusBarIcon _arg1;
            Bundle _arg0;
            ComponentName _arg02;
            ComponentName _arg03;
            ComponentName _arg04;
            PromptInfo _arg05;
            CharSequence _arg3;
            ParcelFileDescriptor _arg12;
            ParcelFileDescriptor _arg13;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg14 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = StatusBarIcon.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            setIcon(_arg06, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            removeIcon(_arg07);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg15 = data.readInt();
                            int _arg2 = data.readInt();
                            disable(_arg08, _arg15, _arg2);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            animateExpandNotificationsPanel();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            animateExpandSettingsPanel(_arg09);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            animateCollapsePanels();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            togglePanel();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            showWirelessChargingAnimation(_arg010);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            IBinder _arg16 = data.readStrongBinder();
                            int _arg22 = data.readInt();
                            int _arg32 = data.readInt();
                            boolean _arg4 = data.readInt() != 0;
                            boolean _arg5 = data.readInt() != 0;
                            setImeWindowStatus(_arg011, _arg16, _arg22, _arg32, _arg4, _arg5);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg17 = data.readInt();
                            int _arg23 = data.readInt();
                            setWindowState(_arg012, _arg17, _arg23);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            showRecentApps(_arg14);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            boolean _arg013 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            hideRecentApps(_arg013, _arg14);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            toggleRecentApps();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            toggleSplitScreen();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            preloadRecentApps();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            cancelPreloadRecentApps();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            showScreenPinningRequest(_arg014);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            dismissKeyboardShortcutsMenu();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            toggleKeyboardShortcutsMenu(_arg015);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            appTransitionPending(_arg016);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            appTransitionCancelled(_arg017);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            long _arg18 = data.readLong();
                            long _arg24 = data.readLong();
                            appTransitionStarting(_arg018, _arg18, _arg24);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            appTransitionFinished(_arg019);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            showAssistDisclosure();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            startAssist(_arg0);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            onCameraLaunchGestureDetected(_arg020);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            onEmergencyActionLaunchGestureDetected();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            showPictureInPictureMenu();
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            showGlobalActionsMenu();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            onProposedRotationChanged(_arg021, _arg14);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            setTopAppHidesStatusBar(_arg14);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            addQsTile(_arg02);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            remQsTile(_arg03);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            clickQsTile(_arg04);
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg022 = data.readInt();
                            handleSystemKey(_arg022);
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            showPinningEnterExitToast(_arg14);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            showPinningEscapeToast();
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            showShutdownUi(_arg14, data.readString());
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = PromptInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            IBiometricSysuiReceiver _arg19 = IBiometricSysuiReceiver.Stub.asInterface(data.readStrongBinder());
                            int[] _arg25 = data.createIntArray();
                            boolean _arg33 = data.readInt() != 0;
                            boolean _arg42 = data.readInt() != 0;
                            int _arg52 = data.readInt();
                            String _arg6 = data.readString();
                            long _arg7 = data.readLong();
                            int _arg8 = data.readInt();
                            showAuthenticationDialog(_arg05, _arg19, _arg25, _arg33, _arg42, _arg52, _arg6, _arg7, _arg8);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            onBiometricAuthenticated();
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg023 = data.readInt();
                            onBiometricHelp(_arg023, data.readString());
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg024 = data.readInt();
                            int _arg110 = data.readInt();
                            int _arg26 = data.readInt();
                            onBiometricError(_arg024, _arg110, _arg26);
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            hideAuthenticationDialog();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            IUdfpsHbmListener _arg025 = IUdfpsHbmListener.Stub.asInterface(data.readStrongBinder());
                            setUdfpsHbmListener(_arg025);
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            onDisplayReady(_arg026);
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            onRecentsAnimationStateChanged(_arg14);
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            int _arg111 = data.readInt();
                            AppearanceRegion[] _arg27 = (AppearanceRegion[]) data.createTypedArray(AppearanceRegion.CREATOR);
                            boolean _arg34 = data.readInt() != 0;
                            int _arg43 = data.readInt();
                            boolean _arg53 = data.readInt() != 0;
                            onSystemBarAttributesChanged(_arg027, _arg111, _arg27, _arg34, _arg43, _arg53);
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg028 = data.readInt();
                            showTransient(_arg028, data.createIntArray());
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            abortTransient(_arg029, data.createIntArray());
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            showInattentiveSleepWarning();
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            dismissInattentiveSleepWarning(_arg14);
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg030 = data.readInt();
                            String _arg112 = data.readString();
                            IBinder _arg28 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg3 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            IBinder _arg44 = data.readStrongBinder();
                            int _arg54 = data.readInt();
                            ITransientNotificationCallback _arg62 = ITransientNotificationCallback.Stub.asInterface(data.readStrongBinder());
                            showToast(_arg030, _arg112, _arg28, _arg3, _arg44, _arg54, _arg62);
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg031 = data.readString();
                            hideToast(_arg031, data.readStrongBinder());
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            startTracing();
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            stopTracing();
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _arg032 = data.createStringArray();
                            if (data.readInt() != 0) {
                                _arg12 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            handleWindowManagerLoggingCommand(_arg032, _arg12);
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            suppressAmbientDisplay(_arg14);
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            requestWindowMagnificationConnection(_arg14);
                            return true;
                        case 59:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _arg033 = data.createStringArray();
                            if (data.readInt() != 0) {
                                _arg13 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            passThroughShellCommand(_arg033, _arg13);
                            return true;
                        case 60:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg034 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            setNavigationBarLumaSamplingEnabled(_arg034, _arg14);
                            return true;
                        case 61:
                            data.enforceInterface(DESCRIPTOR);
                            runGcForTest();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IStatusBar {
            public static IStatusBar sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setIcon(String slot, StatusBarIcon icon) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(slot);
                    if (icon != null) {
                        _data.writeInt(1);
                        icon.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIcon(slot, icon);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void removeIcon(String slot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(slot);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeIcon(slot);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void disable(int displayId, int state1, int state2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(state1);
                    _data.writeInt(state2);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disable(displayId, state1, state2);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void animateExpandNotificationsPanel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().animateExpandNotificationsPanel();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void animateExpandSettingsPanel(String subPanel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(subPanel);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().animateExpandSettingsPanel(subPanel);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void animateCollapsePanels() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().animateCollapsePanels();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void togglePanel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().togglePanel();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showWirelessChargingAnimation(int batteryLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(batteryLevel);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showWirelessChargingAnimation(batteryLevel);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setImeWindowStatus(int displayId, IBinder token, int vis, int backDisposition, boolean showImeSwitcher, boolean isMultiClientImeEnabled) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(displayId);
                        try {
                            _data.writeStrongBinder(token);
                        } catch (Throwable th2) {
                            th = th2;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    _data.writeInt(vis);
                    try {
                        _data.writeInt(backDisposition);
                        int i = 0;
                        _data.writeInt(showImeSwitcher ? 1 : 0);
                        if (isMultiClientImeEnabled) {
                            i = 1;
                        }
                        _data.writeInt(i);
                        try {
                            boolean _status = this.mRemote.transact(9, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().setImeWindowStatus(displayId, token, vis, backDisposition, showImeSwitcher, isMultiClientImeEnabled);
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setWindowState(int display, int window, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(display);
                    _data.writeInt(window);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWindowState(display, window, state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showRecentApps(boolean triggeredFromAltTab) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(triggeredFromAltTab ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showRecentApps(triggeredFromAltTab);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void hideRecentApps(boolean triggeredFromAltTab, boolean triggeredFromHomeKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    _data.writeInt(triggeredFromAltTab ? 1 : 0);
                    if (triggeredFromHomeKey) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideRecentApps(triggeredFromAltTab, triggeredFromHomeKey);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void toggleRecentApps() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().toggleRecentApps();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void toggleSplitScreen() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().toggleSplitScreen();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void preloadRecentApps() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().preloadRecentApps();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void cancelPreloadRecentApps() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelPreloadRecentApps();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showScreenPinningRequest(int taskId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showScreenPinningRequest(taskId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void dismissKeyboardShortcutsMenu() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissKeyboardShortcutsMenu();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void toggleKeyboardShortcutsMenu(int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().toggleKeyboardShortcutsMenu(deviceId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionPending(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().appTransitionPending(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionCancelled(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().appTransitionCancelled(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionStarting(int displayId, long statusBarAnimationsStartTime, long statusBarAnimationsDuration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeLong(statusBarAnimationsStartTime);
                    _data.writeLong(statusBarAnimationsDuration);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().appTransitionStarting(displayId, statusBarAnimationsStartTime, statusBarAnimationsDuration);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void appTransitionFinished(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().appTransitionFinished(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showAssistDisclosure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showAssistDisclosure();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void startAssist(Bundle args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startAssist(args);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onCameraLaunchGestureDetected(int source) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(source);
                    boolean _status = this.mRemote.transact(26, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCameraLaunchGestureDetected(source);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onEmergencyActionLaunchGestureDetected() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(27, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEmergencyActionLaunchGestureDetected();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showPictureInPictureMenu() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showPictureInPictureMenu();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showGlobalActionsMenu() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(29, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showGlobalActionsMenu();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onProposedRotationChanged(int rotation, boolean isValid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(rotation);
                    _data.writeInt(isValid ? 1 : 0);
                    boolean _status = this.mRemote.transact(30, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProposedRotationChanged(rotation, isValid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setTopAppHidesStatusBar(boolean hidesStatusBar) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(hidesStatusBar ? 1 : 0);
                    boolean _status = this.mRemote.transact(31, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTopAppHidesStatusBar(hidesStatusBar);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void addQsTile(ComponentName tile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tile != null) {
                        _data.writeInt(1);
                        tile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(32, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addQsTile(tile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void remQsTile(ComponentName tile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tile != null) {
                        _data.writeInt(1);
                        tile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(33, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().remQsTile(tile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void clickQsTile(ComponentName tile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tile != null) {
                        _data.writeInt(1);
                        tile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clickQsTile(tile);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void handleSystemKey(int key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(key);
                    boolean _status = this.mRemote.transact(35, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleSystemKey(key);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showPinningEnterExitToast(boolean entering) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(entering ? 1 : 0);
                    boolean _status = this.mRemote.transact(36, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showPinningEnterExitToast(entering);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showPinningEscapeToast() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showPinningEscapeToast();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showShutdownUi(boolean isReboot, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isReboot ? 1 : 0);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showShutdownUi(isReboot, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showAuthenticationDialog(PromptInfo promptInfo, IBiometricSysuiReceiver sysuiReceiver, int[] sensorIds, boolean credentialAllowed, boolean requireConfirmation, int userId, String opPackageName, long operationId, int multiSensorConfig) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (promptInfo != null) {
                        _data.writeInt(1);
                        promptInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(sysuiReceiver != null ? sysuiReceiver.asBinder() : null);
                    try {
                        _data.writeIntArray(sensorIds);
                        _data.writeInt(credentialAllowed ? 1 : 0);
                        if (requireConfirmation) {
                            i = 1;
                        }
                        _data.writeInt(i);
                        try {
                            _data.writeInt(userId);
                            _data.writeString(opPackageName);
                            _data.writeLong(operationId);
                            _data.writeInt(multiSensorConfig);
                            boolean _status = this.mRemote.transact(39, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().showAuthenticationDialog(promptInfo, sysuiReceiver, sensorIds, credentialAllowed, requireConfirmation, userId, opPackageName, operationId, multiSensorConfig);
                            _data.recycle();
                        } catch (Throwable th2) {
                            th = th2;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onBiometricAuthenticated() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(40, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBiometricAuthenticated();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onBiometricHelp(int modality, String message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(modality);
                    _data.writeString(message);
                    boolean _status = this.mRemote.transact(41, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBiometricHelp(modality, message);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onBiometricError(int modality, int error, int vendorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(modality);
                    _data.writeInt(error);
                    _data.writeInt(vendorCode);
                    boolean _status = this.mRemote.transact(42, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBiometricError(modality, error, vendorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void hideAuthenticationDialog() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(43, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideAuthenticationDialog();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setUdfpsHbmListener(IUdfpsHbmListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(44, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUdfpsHbmListener(listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onDisplayReady(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(45, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayReady(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onRecentsAnimationStateChanged(boolean running) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(running ? 1 : 0);
                    boolean _status = this.mRemote.transact(46, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecentsAnimationStateChanged(running);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void onSystemBarAttributesChanged(int displayId, int appearance, AppearanceRegion[] appearanceRegions, boolean navbarColorManagedByIme, int behavior, boolean isFullscreen) throws RemoteException {
                Throwable th;
                int i;
                int i2;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(displayId);
                        try {
                            _data.writeInt(appearance);
                            i = 0;
                        } catch (Throwable th2) {
                            th = th2;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    _data.writeTypedArray(appearanceRegions, 0);
                    if (navbarColorManagedByIme) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    _data.writeInt(i2);
                    try {
                        _data.writeInt(behavior);
                        if (isFullscreen) {
                            i = 1;
                        }
                        _data.writeInt(i);
                        try {
                            boolean _status = this.mRemote.transact(47, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().onSystemBarAttributesChanged(displayId, appearance, appearanceRegions, navbarColorManagedByIme, behavior, isFullscreen);
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showTransient(int displayId, int[] types) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeIntArray(types);
                    boolean _status = this.mRemote.transact(48, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showTransient(displayId, types);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void abortTransient(int displayId, int[] types) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeIntArray(types);
                    boolean _status = this.mRemote.transact(49, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abortTransient(displayId, types);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showInattentiveSleepWarning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(50, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInattentiveSleepWarning();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void dismissInattentiveSleepWarning(boolean animated) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(animated ? 1 : 0);
                    boolean _status = this.mRemote.transact(51, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissInattentiveSleepWarning(animated);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void showToast(int uid, String packageName, IBinder token, CharSequence text, IBinder windowToken, int duration, ITransientNotificationCallback callback) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(uid);
                        try {
                            _data.writeString(packageName);
                            try {
                                _data.writeStrongBinder(token);
                                if (text != null) {
                                    _data.writeInt(1);
                                    TextUtils.writeToParcel(text, _data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                try {
                                    _data.writeStrongBinder(windowToken);
                                } catch (Throwable th2) {
                                    th = th2;
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
                try {
                    _data.writeInt(duration);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(52, _data, null, 1);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().showToast(uid, packageName, token, text, windowToken, duration, callback);
                    _data.recycle();
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void hideToast(String packageName, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(53, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideToast(packageName, token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void startTracing() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(54, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startTracing();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void stopTracing() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(55, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopTracing();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void handleWindowManagerLoggingCommand(String[] args, ParcelFileDescriptor outFd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringArray(args);
                    if (outFd != null) {
                        _data.writeInt(1);
                        outFd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(56, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleWindowManagerLoggingCommand(args, outFd);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void suppressAmbientDisplay(boolean suppress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(suppress ? 1 : 0);
                    boolean _status = this.mRemote.transact(57, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suppressAmbientDisplay(suppress);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void requestWindowMagnificationConnection(boolean connect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(connect ? 1 : 0);
                    boolean _status = this.mRemote.transact(58, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestWindowMagnificationConnection(connect);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void passThroughShellCommand(String[] args, ParcelFileDescriptor pfd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringArray(args);
                    if (pfd != null) {
                        _data.writeInt(1);
                        pfd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(59, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().passThroughShellCommand(args, pfd);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void setNavigationBarLumaSamplingEnabled(int displayId, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(60, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNavigationBarLumaSamplingEnabled(displayId, enable);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.statusbar.IStatusBar
            public void runGcForTest() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(61, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().runGcForTest();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStatusBar impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IStatusBar getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
