package android.app;

import android.app.ActivityManager;
import android.app.IRequestFinishCallback;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.RemoteAnimationDefinition;
import android.window.SizeConfigurationBuckets;
import com.android.internal.policy.IKeyguardDismissCallback;
/* loaded from: classes.dex */
public interface IActivityClientController extends IInterface {
    public static final String DESCRIPTOR = "android.app.IActivityClientController";

    void activityDestroyed(IBinder iBinder) throws RemoteException;

    void activityIdle(IBinder iBinder, Configuration configuration, boolean z) throws RemoteException;

    void activityPaused(IBinder iBinder) throws RemoteException;

    void activityRelaunched(IBinder iBinder) throws RemoteException;

    void activityResumed(IBinder iBinder, boolean z) throws RemoteException;

    void activityStopped(IBinder iBinder, Bundle bundle, PersistableBundle persistableBundle, CharSequence charSequence) throws RemoteException;

    void activityTopResumedStateLost() throws RemoteException;

    boolean convertFromTranslucent(IBinder iBinder) throws RemoteException;

    boolean convertToTranslucent(IBinder iBinder, Bundle bundle) throws RemoteException;

    void dismissKeyguard(IBinder iBinder, IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException;

    boolean enterPictureInPictureMode(IBinder iBinder, PictureInPictureParams pictureInPictureParams) throws RemoteException;

    boolean finishActivity(IBinder iBinder, int i, Intent intent, int i2) throws RemoteException;

    boolean finishActivityAffinity(IBinder iBinder) throws RemoteException;

    void finishSubActivity(IBinder iBinder, String str, int i) throws RemoteException;

    ComponentName getCallingActivity(IBinder iBinder) throws RemoteException;

    String getCallingPackage(IBinder iBinder) throws RemoteException;

    int getDisplayId(IBinder iBinder) throws RemoteException;

    String getLaunchedFromPackage(IBinder iBinder) throws RemoteException;

    int getLaunchedFromUid(IBinder iBinder) throws RemoteException;

    int getRequestedOrientation(IBinder iBinder) throws RemoteException;

    int getTaskForActivity(IBinder iBinder, boolean z) throws RemoteException;

    void invalidateHomeTaskSnapshot(IBinder iBinder) throws RemoteException;

    boolean isImmersive(IBinder iBinder) throws RemoteException;

    boolean isRootVoiceInteraction(IBinder iBinder) throws RemoteException;

    boolean isTopOfTask(IBinder iBinder) throws RemoteException;

    boolean moveActivityTaskToBack(IBinder iBinder, boolean z) throws RemoteException;

    boolean navigateUpTo(IBinder iBinder, Intent intent, int i, Intent intent2) throws RemoteException;

    void onBackPressedOnTaskRoot(IBinder iBinder, IRequestFinishCallback iRequestFinishCallback) throws RemoteException;

    void overridePendingTransition(IBinder iBinder, String str, int i, int i2) throws RemoteException;

    void registerRemoteAnimations(IBinder iBinder, RemoteAnimationDefinition remoteAnimationDefinition) throws RemoteException;

    boolean releaseActivityInstance(IBinder iBinder) throws RemoteException;

    void reportActivityFullyDrawn(IBinder iBinder, boolean z) throws RemoteException;

    void reportSizeConfigurations(IBinder iBinder, SizeConfigurationBuckets sizeConfigurationBuckets) throws RemoteException;

    void setDisablePreviewScreenshots(IBinder iBinder, boolean z) throws RemoteException;

    void setImmersive(IBinder iBinder, boolean z) throws RemoteException;

    void setInheritShowWhenLocked(IBinder iBinder, boolean z) throws RemoteException;

    void setPictureInPictureParams(IBinder iBinder, PictureInPictureParams pictureInPictureParams) throws RemoteException;

    void setRequestedOrientation(IBinder iBinder, int i) throws RemoteException;

    void setShowWhenLocked(IBinder iBinder, boolean z) throws RemoteException;

    void setTaskDescription(IBinder iBinder, ActivityManager.TaskDescription taskDescription) throws RemoteException;

    void setTurnScreenOn(IBinder iBinder, boolean z) throws RemoteException;

    int setVrMode(IBinder iBinder, boolean z, ComponentName componentName) throws RemoteException;

    boolean shouldUpRecreateTask(IBinder iBinder, String str) throws RemoteException;

    boolean showAssistFromActivity(IBinder iBinder, Bundle bundle) throws RemoteException;

    void showLockTaskEscapeMessage(IBinder iBinder) throws RemoteException;

    void splashScreenAttached(IBinder iBinder) throws RemoteException;

    void startLocalVoiceInteraction(IBinder iBinder, Bundle bundle) throws RemoteException;

    void startLockTaskModeByToken(IBinder iBinder) throws RemoteException;

    void stopLocalVoiceInteraction(IBinder iBinder) throws RemoteException;

    void stopLockTaskModeByToken(IBinder iBinder) throws RemoteException;

    void toggleFreeformWindowingMode(IBinder iBinder) throws RemoteException;

    void unregisterRemoteAnimations(IBinder iBinder) throws RemoteException;

    boolean willActivityBeVisible(IBinder iBinder) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IActivityClientController {
        @Override // android.app.IActivityClientController
        public void activityIdle(IBinder token, Configuration config, boolean stopProfiling) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void activityResumed(IBinder token, boolean handleSplashScreenExit) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void activityTopResumedStateLost() throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void activityPaused(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void activityStopped(IBinder token, Bundle state, PersistableBundle persistentState, CharSequence description) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void activityDestroyed(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void activityRelaunched(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void reportSizeConfigurations(IBinder token, SizeConfigurationBuckets sizeConfigurations) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public boolean moveActivityTaskToBack(IBinder token, boolean nonRoot) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean shouldUpRecreateTask(IBinder token, String destAffinity) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean navigateUpTo(IBinder token, Intent target, int resultCode, Intent resultData) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean releaseActivityInstance(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean finishActivity(IBinder token, int code, Intent data, int finishTask) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean finishActivityAffinity(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public void finishSubActivity(IBinder token, String resultWho, int requestCode) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public boolean isTopOfTask(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean willActivityBeVisible(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public int getDisplayId(IBinder activityToken) throws RemoteException {
            return 0;
        }

        @Override // android.app.IActivityClientController
        public int getTaskForActivity(IBinder token, boolean onlyRoot) throws RemoteException {
            return 0;
        }

        @Override // android.app.IActivityClientController
        public ComponentName getCallingActivity(IBinder token) throws RemoteException {
            return null;
        }

        @Override // android.app.IActivityClientController
        public String getCallingPackage(IBinder token) throws RemoteException {
            return null;
        }

        @Override // android.app.IActivityClientController
        public int getLaunchedFromUid(IBinder token) throws RemoteException {
            return 0;
        }

        @Override // android.app.IActivityClientController
        public String getLaunchedFromPackage(IBinder token) throws RemoteException {
            return null;
        }

        @Override // android.app.IActivityClientController
        public void setRequestedOrientation(IBinder token, int requestedOrientation) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public int getRequestedOrientation(IBinder token) throws RemoteException {
            return 0;
        }

        @Override // android.app.IActivityClientController
        public boolean convertFromTranslucent(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean convertToTranslucent(IBinder token, Bundle options) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean isImmersive(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public void setImmersive(IBinder token, boolean immersive) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public boolean enterPictureInPictureMode(IBinder token, PictureInPictureParams params) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public void setPictureInPictureParams(IBinder token, PictureInPictureParams params) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void toggleFreeformWindowingMode(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void startLockTaskModeByToken(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void stopLockTaskModeByToken(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void showLockTaskEscapeMessage(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void setTaskDescription(IBinder token, ActivityManager.TaskDescription values) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public boolean showAssistFromActivity(IBinder token, Bundle args) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public boolean isRootVoiceInteraction(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.app.IActivityClientController
        public void startLocalVoiceInteraction(IBinder token, Bundle options) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void stopLocalVoiceInteraction(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void setShowWhenLocked(IBinder token, boolean showWhenLocked) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void setInheritShowWhenLocked(IBinder token, boolean setInheritShownWhenLocked) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void setTurnScreenOn(IBinder token, boolean turnScreenOn) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void reportActivityFullyDrawn(IBinder token, boolean restoredFromBundle) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void overridePendingTransition(IBinder token, String packageName, int enterAnim, int exitAnim) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public int setVrMode(IBinder token, boolean enabled, ComponentName packageName) throws RemoteException {
            return 0;
        }

        @Override // android.app.IActivityClientController
        public void setDisablePreviewScreenshots(IBinder token, boolean disable) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void invalidateHomeTaskSnapshot(IBinder homeToken) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void dismissKeyguard(IBinder token, IKeyguardDismissCallback callback, CharSequence message) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void registerRemoteAnimations(IBinder token, RemoteAnimationDefinition definition) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void unregisterRemoteAnimations(IBinder token) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void onBackPressedOnTaskRoot(IBinder activityToken, IRequestFinishCallback callback) throws RemoteException {
        }

        @Override // android.app.IActivityClientController
        public void splashScreenAttached(IBinder token) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IActivityClientController {
        static final int TRANSACTION_activityDestroyed = 6;
        static final int TRANSACTION_activityIdle = 1;
        static final int TRANSACTION_activityPaused = 4;
        static final int TRANSACTION_activityRelaunched = 7;
        static final int TRANSACTION_activityResumed = 2;
        static final int TRANSACTION_activityStopped = 5;
        static final int TRANSACTION_activityTopResumedStateLost = 3;
        static final int TRANSACTION_convertFromTranslucent = 26;
        static final int TRANSACTION_convertToTranslucent = 27;
        static final int TRANSACTION_dismissKeyguard = 49;
        static final int TRANSACTION_enterPictureInPictureMode = 30;
        static final int TRANSACTION_finishActivity = 13;
        static final int TRANSACTION_finishActivityAffinity = 14;
        static final int TRANSACTION_finishSubActivity = 15;
        static final int TRANSACTION_getCallingActivity = 20;
        static final int TRANSACTION_getCallingPackage = 21;
        static final int TRANSACTION_getDisplayId = 18;
        static final int TRANSACTION_getLaunchedFromPackage = 23;
        static final int TRANSACTION_getLaunchedFromUid = 22;
        static final int TRANSACTION_getRequestedOrientation = 25;
        static final int TRANSACTION_getTaskForActivity = 19;
        static final int TRANSACTION_invalidateHomeTaskSnapshot = 48;
        static final int TRANSACTION_isImmersive = 28;
        static final int TRANSACTION_isRootVoiceInteraction = 38;
        static final int TRANSACTION_isTopOfTask = 16;
        static final int TRANSACTION_moveActivityTaskToBack = 9;
        static final int TRANSACTION_navigateUpTo = 11;
        static final int TRANSACTION_onBackPressedOnTaskRoot = 52;
        static final int TRANSACTION_overridePendingTransition = 45;
        static final int TRANSACTION_registerRemoteAnimations = 50;
        static final int TRANSACTION_releaseActivityInstance = 12;
        static final int TRANSACTION_reportActivityFullyDrawn = 44;
        static final int TRANSACTION_reportSizeConfigurations = 8;
        static final int TRANSACTION_setDisablePreviewScreenshots = 47;
        static final int TRANSACTION_setImmersive = 29;
        static final int TRANSACTION_setInheritShowWhenLocked = 42;
        static final int TRANSACTION_setPictureInPictureParams = 31;
        static final int TRANSACTION_setRequestedOrientation = 24;
        static final int TRANSACTION_setShowWhenLocked = 41;
        static final int TRANSACTION_setTaskDescription = 36;
        static final int TRANSACTION_setTurnScreenOn = 43;
        static final int TRANSACTION_setVrMode = 46;
        static final int TRANSACTION_shouldUpRecreateTask = 10;
        static final int TRANSACTION_showAssistFromActivity = 37;
        static final int TRANSACTION_showLockTaskEscapeMessage = 35;
        static final int TRANSACTION_splashScreenAttached = 53;
        static final int TRANSACTION_startLocalVoiceInteraction = 39;
        static final int TRANSACTION_startLockTaskModeByToken = 33;
        static final int TRANSACTION_stopLocalVoiceInteraction = 40;
        static final int TRANSACTION_stopLockTaskModeByToken = 34;
        static final int TRANSACTION_toggleFreeformWindowingMode = 32;
        static final int TRANSACTION_unregisterRemoteAnimations = 51;
        static final int TRANSACTION_willActivityBeVisible = 17;

        public Stub() {
            attachInterface(this, IActivityClientController.DESCRIPTOR);
        }

        public static IActivityClientController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IActivityClientController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IActivityClientController)) {
                return new Proxy(obj);
            }
            return (IActivityClientController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "activityIdle";
                case 2:
                    return "activityResumed";
                case 3:
                    return "activityTopResumedStateLost";
                case 4:
                    return "activityPaused";
                case 5:
                    return "activityStopped";
                case 6:
                    return "activityDestroyed";
                case 7:
                    return "activityRelaunched";
                case 8:
                    return "reportSizeConfigurations";
                case 9:
                    return "moveActivityTaskToBack";
                case 10:
                    return "shouldUpRecreateTask";
                case 11:
                    return "navigateUpTo";
                case 12:
                    return "releaseActivityInstance";
                case 13:
                    return "finishActivity";
                case 14:
                    return "finishActivityAffinity";
                case 15:
                    return "finishSubActivity";
                case 16:
                    return "isTopOfTask";
                case 17:
                    return "willActivityBeVisible";
                case 18:
                    return "getDisplayId";
                case 19:
                    return "getTaskForActivity";
                case 20:
                    return "getCallingActivity";
                case 21:
                    return "getCallingPackage";
                case 22:
                    return "getLaunchedFromUid";
                case 23:
                    return "getLaunchedFromPackage";
                case 24:
                    return "setRequestedOrientation";
                case 25:
                    return "getRequestedOrientation";
                case 26:
                    return "convertFromTranslucent";
                case 27:
                    return "convertToTranslucent";
                case 28:
                    return "isImmersive";
                case 29:
                    return "setImmersive";
                case 30:
                    return "enterPictureInPictureMode";
                case 31:
                    return "setPictureInPictureParams";
                case 32:
                    return "toggleFreeformWindowingMode";
                case 33:
                    return "startLockTaskModeByToken";
                case 34:
                    return "stopLockTaskModeByToken";
                case 35:
                    return "showLockTaskEscapeMessage";
                case 36:
                    return "setTaskDescription";
                case 37:
                    return "showAssistFromActivity";
                case 38:
                    return "isRootVoiceInteraction";
                case 39:
                    return "startLocalVoiceInteraction";
                case 40:
                    return "stopLocalVoiceInteraction";
                case 41:
                    return "setShowWhenLocked";
                case 42:
                    return "setInheritShowWhenLocked";
                case 43:
                    return "setTurnScreenOn";
                case 44:
                    return "reportActivityFullyDrawn";
                case 45:
                    return "overridePendingTransition";
                case 46:
                    return "setVrMode";
                case 47:
                    return "setDisablePreviewScreenshots";
                case 48:
                    return "invalidateHomeTaskSnapshot";
                case 49:
                    return "dismissKeyguard";
                case 50:
                    return "registerRemoteAnimations";
                case 51:
                    return "unregisterRemoteAnimations";
                case 52:
                    return "onBackPressedOnTaskRoot";
                case 53:
                    return "splashScreenAttached";
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
            Configuration _arg1;
            Bundle _arg12;
            PersistableBundle _arg2;
            CharSequence _arg3;
            SizeConfigurationBuckets _arg13;
            Intent _arg14;
            Intent _arg32;
            Intent _arg22;
            Bundle _arg15;
            PictureInPictureParams _arg16;
            PictureInPictureParams _arg17;
            ActivityManager.TaskDescription _arg18;
            Bundle _arg19;
            Bundle _arg110;
            ComponentName _arg23;
            CharSequence _arg24;
            RemoteAnimationDefinition _arg111;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IActivityClientController.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg112 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg0 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg1 = Configuration.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            activityIdle(_arg0, _arg1, _arg112);
                            return true;
                        case 2:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            activityResumed(_arg02, _arg112);
                            return true;
                        case 3:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            activityTopResumedStateLost();
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            activityPaused(_arg03);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg12 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            activityStopped(_arg04, _arg12, _arg2, _arg3);
                            return true;
                        case 6:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg05 = data.readStrongBinder();
                            activityDestroyed(_arg05);
                            return true;
                        case 7:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg06 = data.readStrongBinder();
                            activityRelaunched(_arg06);
                            return true;
                        case 8:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg07 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg13 = SizeConfigurationBuckets.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            reportSizeConfigurations(_arg07, _arg13);
                            return true;
                        case 9:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg08 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            boolean moveActivityTaskToBack = moveActivityTaskToBack(_arg08, _arg112);
                            reply.writeNoException();
                            reply.writeInt(moveActivityTaskToBack ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg09 = data.readStrongBinder();
                            boolean shouldUpRecreateTask = shouldUpRecreateTask(_arg09, data.readString());
                            reply.writeNoException();
                            reply.writeInt(shouldUpRecreateTask ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg010 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg14 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            int _arg25 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg32 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            boolean navigateUpTo = navigateUpTo(_arg010, _arg14, _arg25, _arg32);
                            reply.writeNoException();
                            reply.writeInt(navigateUpTo ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg011 = data.readStrongBinder();
                            boolean releaseActivityInstance = releaseActivityInstance(_arg011);
                            reply.writeNoException();
                            reply.writeInt(releaseActivityInstance ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg012 = data.readStrongBinder();
                            int _arg113 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            int _arg33 = data.readInt();
                            boolean finishActivity = finishActivity(_arg012, _arg113, _arg22, _arg33);
                            reply.writeNoException();
                            reply.writeInt(finishActivity ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg013 = data.readStrongBinder();
                            boolean finishActivityAffinity = finishActivityAffinity(_arg013);
                            reply.writeNoException();
                            reply.writeInt(finishActivityAffinity ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg014 = data.readStrongBinder();
                            String _arg114 = data.readString();
                            int _arg26 = data.readInt();
                            finishSubActivity(_arg014, _arg114, _arg26);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg015 = data.readStrongBinder();
                            boolean isTopOfTask = isTopOfTask(_arg015);
                            reply.writeNoException();
                            reply.writeInt(isTopOfTask ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg016 = data.readStrongBinder();
                            boolean willActivityBeVisible = willActivityBeVisible(_arg016);
                            reply.writeNoException();
                            reply.writeInt(willActivityBeVisible ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg017 = data.readStrongBinder();
                            int _result = getDisplayId(_arg017);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 19:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg018 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            int _result2 = getTaskForActivity(_arg018, _arg112);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 20:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg019 = data.readStrongBinder();
                            ComponentName _result3 = getCallingActivity(_arg019);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 21:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg020 = data.readStrongBinder();
                            String _result4 = getCallingPackage(_arg020);
                            reply.writeNoException();
                            reply.writeString(_result4);
                            return true;
                        case 22:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg021 = data.readStrongBinder();
                            int _result5 = getLaunchedFromUid(_arg021);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 23:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg022 = data.readStrongBinder();
                            String _result6 = getLaunchedFromPackage(_arg022);
                            reply.writeNoException();
                            reply.writeString(_result6);
                            return true;
                        case 24:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg023 = data.readStrongBinder();
                            setRequestedOrientation(_arg023, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg024 = data.readStrongBinder();
                            int _result7 = getRequestedOrientation(_arg024);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 26:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg025 = data.readStrongBinder();
                            boolean convertFromTranslucent = convertFromTranslucent(_arg025);
                            reply.writeNoException();
                            reply.writeInt(convertFromTranslucent ? 1 : 0);
                            return true;
                        case 27:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg026 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg15 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            boolean convertToTranslucent = convertToTranslucent(_arg026, _arg15);
                            reply.writeNoException();
                            reply.writeInt(convertToTranslucent ? 1 : 0);
                            return true;
                        case 28:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg027 = data.readStrongBinder();
                            boolean isImmersive = isImmersive(_arg027);
                            reply.writeNoException();
                            reply.writeInt(isImmersive ? 1 : 0);
                            return true;
                        case 29:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg028 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            setImmersive(_arg028, _arg112);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg029 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg16 = PictureInPictureParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            boolean enterPictureInPictureMode = enterPictureInPictureMode(_arg029, _arg16);
                            reply.writeNoException();
                            reply.writeInt(enterPictureInPictureMode ? 1 : 0);
                            return true;
                        case 31:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg030 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg17 = PictureInPictureParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            setPictureInPictureParams(_arg030, _arg17);
                            reply.writeNoException();
                            return true;
                        case 32:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg031 = data.readStrongBinder();
                            toggleFreeformWindowingMode(_arg031);
                            reply.writeNoException();
                            return true;
                        case 33:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg032 = data.readStrongBinder();
                            startLockTaskModeByToken(_arg032);
                            return true;
                        case 34:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg033 = data.readStrongBinder();
                            stopLockTaskModeByToken(_arg033);
                            return true;
                        case 35:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg034 = data.readStrongBinder();
                            showLockTaskEscapeMessage(_arg034);
                            return true;
                        case 36:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg035 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg18 = ActivityManager.TaskDescription.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            setTaskDescription(_arg035, _arg18);
                            reply.writeNoException();
                            return true;
                        case 37:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg036 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg19 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            boolean showAssistFromActivity = showAssistFromActivity(_arg036, _arg19);
                            reply.writeNoException();
                            reply.writeInt(showAssistFromActivity ? 1 : 0);
                            return true;
                        case 38:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg037 = data.readStrongBinder();
                            boolean isRootVoiceInteraction = isRootVoiceInteraction(_arg037);
                            reply.writeNoException();
                            reply.writeInt(isRootVoiceInteraction ? 1 : 0);
                            return true;
                        case 39:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg038 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg110 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            startLocalVoiceInteraction(_arg038, _arg110);
                            reply.writeNoException();
                            return true;
                        case 40:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg039 = data.readStrongBinder();
                            stopLocalVoiceInteraction(_arg039);
                            reply.writeNoException();
                            return true;
                        case 41:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg040 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            setShowWhenLocked(_arg040, _arg112);
                            return true;
                        case 42:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg041 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            setInheritShowWhenLocked(_arg041, _arg112);
                            return true;
                        case 43:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg042 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            setTurnScreenOn(_arg042, _arg112);
                            return true;
                        case 44:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg043 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            reportActivityFullyDrawn(_arg043, _arg112);
                            return true;
                        case 45:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg044 = data.readStrongBinder();
                            String _arg115 = data.readString();
                            int _arg27 = data.readInt();
                            int _arg34 = data.readInt();
                            overridePendingTransition(_arg044, _arg115, _arg27, _arg34);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg045 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg23 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            int _result8 = setVrMode(_arg045, _arg112, _arg23);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 47:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg046 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            }
                            setDisablePreviewScreenshots(_arg046, _arg112);
                            return true;
                        case 48:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg047 = data.readStrongBinder();
                            invalidateHomeTaskSnapshot(_arg047);
                            reply.writeNoException();
                            return true;
                        case 49:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg048 = data.readStrongBinder();
                            IKeyguardDismissCallback _arg116 = IKeyguardDismissCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg24 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            dismissKeyguard(_arg048, _arg116, _arg24);
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg049 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg111 = RemoteAnimationDefinition.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            registerRemoteAnimations(_arg049, _arg111);
                            reply.writeNoException();
                            return true;
                        case 51:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg050 = data.readStrongBinder();
                            unregisterRemoteAnimations(_arg050);
                            reply.writeNoException();
                            return true;
                        case 52:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg051 = data.readStrongBinder();
                            onBackPressedOnTaskRoot(_arg051, IRequestFinishCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 53:
                            data.enforceInterface(IActivityClientController.DESCRIPTOR);
                            IBinder _arg052 = data.readStrongBinder();
                            splashScreenAttached(_arg052);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IActivityClientController {
            public static IActivityClientController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IActivityClientController.DESCRIPTOR;
            }

            @Override // android.app.IActivityClientController
            public void activityIdle(IBinder token, Configuration config, boolean stopProfiling) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    int i = 0;
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (stopProfiling) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activityIdle(token, config, stopProfiling);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void activityResumed(IBinder token, boolean handleSplashScreenExit) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(handleSplashScreenExit ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activityResumed(token, handleSplashScreenExit);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void activityTopResumedStateLost() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().activityTopResumedStateLost();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void activityPaused(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().activityPaused(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void activityStopped(IBinder token, Bundle state, PersistableBundle persistentState, CharSequence description) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (persistentState != null) {
                        _data.writeInt(1);
                        persistentState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (description != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(description, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activityStopped(token, state, persistentState, description);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void activityDestroyed(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activityDestroyed(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void activityRelaunched(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activityRelaunched(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void reportSizeConfigurations(IBinder token, SizeConfigurationBuckets sizeConfigurations) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (sizeConfigurations != null) {
                        _data.writeInt(1);
                        sizeConfigurations.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportSizeConfigurations(token, sizeConfigurations);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean moveActivityTaskToBack(IBinder token, boolean nonRoot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = true;
                    _data.writeInt(nonRoot ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().moveActivityTaskToBack(token, nonRoot);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean shouldUpRecreateTask(IBinder token, String destAffinity) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(destAffinity);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldUpRecreateTask(token, destAffinity);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean navigateUpTo(IBinder token, Intent target, int resultCode, Intent resultData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = true;
                    if (target != null) {
                        _data.writeInt(1);
                        target.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(resultCode);
                    if (resultData != null) {
                        _data.writeInt(1);
                        resultData.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().navigateUpTo(token, target, resultCode, resultData);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean releaseActivityInstance(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().releaseActivityInstance(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean finishActivity(IBinder token, int code, Intent data, int finishTask) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(code);
                    boolean _result = true;
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(finishTask);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().finishActivity(token, code, data, finishTask);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean finishActivityAffinity(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().finishActivityAffinity(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void finishSubActivity(IBinder token, String resultWho, int requestCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(resultWho);
                    _data.writeInt(requestCode);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finishSubActivity(token, resultWho, requestCode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean isTopOfTask(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTopOfTask(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean willActivityBeVisible(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().willActivityBeVisible(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public int getDisplayId(IBinder activityToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(activityToken);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayId(activityToken);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public int getTaskForActivity(IBinder token, boolean onlyRoot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(onlyRoot ? 1 : 0);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTaskForActivity(token, onlyRoot);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public ComponentName getCallingActivity(IBinder token) throws RemoteException {
                ComponentName _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallingActivity(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ComponentName.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public String getCallingPackage(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallingPackage(token);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public int getLaunchedFromUid(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaunchedFromUid(token);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public String getLaunchedFromPackage(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLaunchedFromPackage(token);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setRequestedOrientation(IBinder token, int requestedOrientation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(requestedOrientation);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRequestedOrientation(token, requestedOrientation);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public int getRequestedOrientation(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRequestedOrientation(token);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean convertFromTranslucent(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().convertFromTranslucent(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean convertToTranslucent(IBinder token, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = true;
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().convertToTranslucent(token, options);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean isImmersive(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isImmersive(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setImmersive(IBinder token, boolean immersive) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(immersive ? 1 : 0);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setImmersive(token, immersive);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean enterPictureInPictureMode(IBinder token, PictureInPictureParams params) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = true;
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enterPictureInPictureMode(token, params);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setPictureInPictureParams(IBinder token, PictureInPictureParams params) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPictureInPictureParams(token, params);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void toggleFreeformWindowingMode(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().toggleFreeformWindowingMode(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void startLockTaskModeByToken(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(33, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startLockTaskModeByToken(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void stopLockTaskModeByToken(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopLockTaskModeByToken(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void showLockTaskEscapeMessage(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(35, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showLockTaskEscapeMessage(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setTaskDescription(IBinder token, ActivityManager.TaskDescription values) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (values != null) {
                        _data.writeInt(1);
                        values.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTaskDescription(token, values);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean showAssistFromActivity(IBinder token, Bundle args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = true;
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showAssistFromActivity(token, args);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public boolean isRootVoiceInteraction(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRootVoiceInteraction(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void startLocalVoiceInteraction(IBinder token, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startLocalVoiceInteraction(token, options);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void stopLocalVoiceInteraction(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopLocalVoiceInteraction(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setShowWhenLocked(IBinder token, boolean showWhenLocked) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(showWhenLocked ? 1 : 0);
                    boolean _status = this.mRemote.transact(41, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShowWhenLocked(token, showWhenLocked);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setInheritShowWhenLocked(IBinder token, boolean setInheritShownWhenLocked) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(setInheritShownWhenLocked ? 1 : 0);
                    boolean _status = this.mRemote.transact(42, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInheritShowWhenLocked(token, setInheritShownWhenLocked);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setTurnScreenOn(IBinder token, boolean turnScreenOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(turnScreenOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(43, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTurnScreenOn(token, turnScreenOn);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void reportActivityFullyDrawn(IBinder token, boolean restoredFromBundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(restoredFromBundle ? 1 : 0);
                    boolean _status = this.mRemote.transact(44, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportActivityFullyDrawn(token, restoredFromBundle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void overridePendingTransition(IBinder token, String packageName, int enterAnim, int exitAnim) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(packageName);
                    _data.writeInt(enterAnim);
                    _data.writeInt(exitAnim);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().overridePendingTransition(token, packageName, enterAnim, exitAnim);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public int setVrMode(IBinder token, boolean enabled, ComponentName packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(enabled ? 1 : 0);
                    if (packageName != null) {
                        _data.writeInt(1);
                        packageName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVrMode(token, enabled, packageName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void setDisablePreviewScreenshots(IBinder token, boolean disable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(disable ? 1 : 0);
                    boolean _status = this.mRemote.transact(47, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisablePreviewScreenshots(token, disable);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void invalidateHomeTaskSnapshot(IBinder homeToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(homeToken);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().invalidateHomeTaskSnapshot(homeToken);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void dismissKeyguard(IBinder token, IKeyguardDismissCallback callback, CharSequence message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (message != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(message, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().dismissKeyguard(token, callback, message);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void registerRemoteAnimations(IBinder token, RemoteAnimationDefinition definition) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (definition != null) {
                        _data.writeInt(1);
                        definition.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerRemoteAnimations(token, definition);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void unregisterRemoteAnimations(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterRemoteAnimations(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void onBackPressedOnTaskRoot(IBinder activityToken, IRequestFinishCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(activityToken);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(52, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBackPressedOnTaskRoot(activityToken, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IActivityClientController
            public void splashScreenAttached(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IActivityClientController.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(53, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().splashScreenAttached(token);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IActivityClientController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IActivityClientController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
