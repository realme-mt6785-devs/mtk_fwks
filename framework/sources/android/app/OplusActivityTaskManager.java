package android.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IOplusKeyEventObserver;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.oplus.app.IOplusAppSwitchObserver;
import com.oplus.app.IOplusFreeformConfigChangedListener;
import com.oplus.app.IOplusSplitScreenObserver;
import com.oplus.app.IOplusZoomWindowConfigChangedListener;
import com.oplus.app.ISecurityPageController;
import com.oplus.app.OplusAppInfo;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.bracket.IOplusBracketModeChangedListener;
import com.oplus.compactwindow.IOplusCompactWindowObserver;
import com.oplus.confinemode.IOplusConfineModeObserver;
import com.oplus.datasync.ISysStateChangeCallback;
import com.oplus.globaldrag.OplusGlobalDragAndDropRUSConfig;
import com.oplus.lockscreen.IOplusLockScreenCallback;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class OplusActivityTaskManager extends OplusBaseActivityTaskManager implements IOplusActivityTaskManager {
    public static OplusActivityTaskManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {
        private static final OplusActivityTaskManager INSTANCE = new OplusActivityTaskManager();

        private LazyHolder() {
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setSecureController(IActivityController watcher) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
            this.mRemote.transact(10002, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public ComponentName getTopActivityComponentName() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10007, data, reply, 0);
            reply.readException();
            ComponentName name = ComponentName.readFromParcel(reply);
            return name;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public ApplicationInfo getTopApplicationInfo() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10011, data, reply, 0);
            reply.readException();
            ApplicationInfo info = ApplicationInfo.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void swapDockedFullscreenStack() throws RemoteException {
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getSplitScreenState(Intent intent) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            if (intent != null) {
                data.writeInt(1);
                intent.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(10074, data, reply, 0);
            reply.readException();
            int result = Integer.valueOf(reply.readString()).intValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public List<OplusAppInfo> getAllTopAppInfos() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10053, data, reply, 0);
            reply.readException();
            List<OplusAppInfo> list = reply.createTypedArrayList(OplusAppInfo.CREATOR);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
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
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(pkgName);
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            config.writeToParcel(data, 0);
            this.mRemote.transact(10064, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterAppSwitchObserver(String pkgName, OplusAppSwitchConfig config) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(pkgName);
            config.writeToParcel(data, 0);
            this.mRemote.transact(10065, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int startZoomWindow(Intent intent, Bundle options, int userId, String callPkg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            if (intent != null) {
                data.writeInt(1);
                intent.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            if (options != null) {
                data.writeInt(1);
                data.writeBundle(options);
            } else {
                data.writeInt(0);
            }
            data.writeInt(userId);
            data.writeString(callPkg);
            this.mRemote.transact(10068, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerZoomWindowObserver(IOplusZoomWindowObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10069, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterZoomWindowObserver(IOplusZoomWindowObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10070, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusZoomWindowInfo getCurrentZoomWindowState() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10071, data, reply, 0);
            reply.readException();
            OplusZoomWindowInfo info = OplusZoomWindowInfo.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void hideZoomWindow(int flag) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(flag);
            this.mRemote.transact(10073, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public List<String> getZoomAppConfigList(int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new ArrayList();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(type);
            this.mRemote.transact(10075, data, reply, 0);
            reply.readException();
            List<String> list = new ArrayList<>();
            reply.readStringList(list);
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void onInputEvent(OplusZoomInputEventInfo inputEventInfo) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeParcelable(inputEventInfo, 0);
            this.mRemote.transact(10131, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void onControlViewChanged(OplusZoomControlViewInfo cvInfo) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeParcelable(cvInfo, 0);
            this.mRemote.transact(10132, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportZoomWindowMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10078, data, reply, 0);
            reply.readException();
            boolean isZoomWindowEnabled = reply.readBoolean();
            return isZoomWindowEnabled;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportZoomMode(String target, int userId, String callPkg, Bundle extension) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(target);
            data.writeInt(userId);
            data.writeString(callPkg);
            if (extension != null) {
                data.writeInt(1);
                data.writeBundle(extension);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(10135, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusZoomWindowRUSConfig getZoomWindowConfig() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new OplusZoomWindowRUSConfig();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10076, data, reply, 0);
            reply.readException();
            OplusZoomWindowRUSConfig config = (OplusZoomWindowRUSConfig) reply.readParcelable(OplusZoomWindowRUSConfig.class.getClassLoader());
            return config;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setZoomWindowConfig(OplusZoomWindowRUSConfig config) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeParcelable(config, 0);
            this.mRemote.transact(10077, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void onFloatHandleViewChanged(OplusZoomFloatHandleViewInfo floatHandleInfo) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeParcelable(floatHandleInfo, 0);
            this.mRemote.transact(10139, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void updateUntrustedTouchConfig(String configData, boolean isRus) throws RemoteException {
        Parcel data = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(configData);
            data.writeBoolean(isRus);
            this.mRemote.transact(IOplusActivityTaskManager.UPDATE_UNTRUSTED_TOUCH_CONFIG, data, null, 1);
        } finally {
            data.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean handleShowCompatibilityToast(String target, int userId, String callPkg, Bundle extension, int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(target);
            data.writeInt(userId);
            data.writeString(callPkg);
            if (extension != null) {
                data.writeInt(1);
                data.writeBundle(extension);
            } else {
                data.writeInt(0);
            }
            data.writeInt(type);
            this.mRemote.transact(10136, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void startMiniZoomFromZoom(int startWay) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(startWay);
            this.mRemote.transact(IOplusActivityTaskManager.START_MINI_FROM_ZOOM, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isZoomSimpleModeEnable() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.ZOOM_SIMPLE_MODE_ENABLE, data, reply, 0);
            reply.readException();
            boolean isZoomSimpleModeEnable = reply.readBoolean();
            return isZoomSimpleModeEnable;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean addZoomWindowConfigChangedListener(IOplusZoomWindowConfigChangedListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(listener != null ? listener.asBinder() : null);
            this.mRemote.transact(10079, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean removeZoomWindowConfigChangedListener(IOplusZoomWindowConfigChangedListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(listener != null ? listener.asBinder() : null);
            this.mRemote.transact(10080, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void startLockDeviceMode(String rootPkg, String[] packages) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(rootPkg);
            data.writeStringArray(packages);
            this.mRemote.transact(10081, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void stopLockDeviceMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10082, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean isLockDeviceMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.IS_LOCK_DEVICE_MODE_TRANSACTION, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean writeEdgeTouchPreventParam(String callPkg, String scenePkg, List<String> paramCmdList) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(callPkg);
            data.writeString(scenePkg);
            data.writeStringList(paramCmdList);
            this.mRemote.transact(10083, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setDefaultEdgeTouchPreventParam(String callPkg, List<String> paramCmdList) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(callPkg);
            data.writeStringList(paramCmdList);
            this.mRemote.transact(10084, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean resetDefaultEdgeTouchPreventParam(String callPkg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(callPkg);
            this.mRemote.transact(10085, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportEdgeTouchPrevent() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10086, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setEdgeTouchCallRules(String callPkg, Map<String, List<String>> rulesMap) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(callPkg);
            data.writeMap(rulesMap);
            this.mRemote.transact(10087, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int splitScreenForEdgePanel(Intent intent, int userId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            intent.writeToParcel(data, 0);
            data.writeInt(userId);
            this.mRemote.transact(10088, data, reply, 0);
            reply.readException();
            int result = Integer.valueOf(reply.readString()).intValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setConfineMode(int mode, boolean on) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(mode);
            data.writeString(String.valueOf(on));
            this.mRemote.transact(10089, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getConfineMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10090, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setPermitList(int mode, int type, List<String> permits, boolean isMultiApp) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(mode);
            data.writeInt(type);
            data.writeStringList(permits);
            data.writeString(String.valueOf(isMultiApp));
            this.mRemote.transact(10091, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setGimbalLaunchPkg(String pkgName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(pkgName);
            this.mRemote.transact(10092, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void setPackagesState(Map<String, Integer> packageMap) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeMap(packageMap);
            this.mRemote.transact(10093, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean registerLockScreenCallback(IOplusLockScreenCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10094, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean unregisterLockScreenCallback(IOplusLockScreenCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10095, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void notifySplitScreenStateChanged(String event, Bundle bundle, boolean broadcast) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(event);
            data.writeBundle(bundle);
            data.writeBoolean(broadcast);
            this.mRemote.transact(10096, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean setSplitScreenObserver(IOplusSplitScreenObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10097, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isInSplitScreenMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10098, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean dismissSplitScreenMode(int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(type);
            this.mRemote.transact(10099, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerSplitScreenObserver(int observerId, IOplusSplitScreenObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(observerId);
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10100, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterSplitScreenObserver(int observerId, IOplusSplitScreenObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(observerId);
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10101, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public Bundle getSplitScreenStatus(String event) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(event);
            this.mRemote.transact(10102, data, reply, 0);
            reply.readException();
            Bundle result = reply.readBundle();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean splitScreenForTopApp(int type) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(type);
            this.mRemote.transact(10128, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean splitScreenForRecentTasks(int taskId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(taskId);
            this.mRemote.transact(10129, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean setTaskWindowingModeSplitScreen(int taskId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(taskId);
            this.mRemote.transact(IOplusActivityTaskManager.SPLIT_SCREEN_SET_TASK_WINDOWING_MODE, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean splitScreenForEdgePanel(Intent intent, boolean launchToPrimary, int launchArea) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            intent.writeToParcel(data, 0);
            data.writeBoolean(launchToPrimary);
            data.writeInt(launchArea);
            this.mRemote.transact(IOplusActivityTaskManager.SPLIT_SCREEN_FOR_EDGE_PANEL, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean hasFolderFeature() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.SPLIT_SCREEN_HAS_FOLDER_FEATURE, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isFolderInnerScreen() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.SPLIT_SCREEN_IS_FOLDER_INNER_SCREEN, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public Rect getMinimizedBounds(int dockedSide) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(dockedSide);
            this.mRemote.transact(IOplusActivityTaskManager.SPLIT_SCREEN_GET_MINIMIZED_BOUNDS, data, reply, 0);
            reply.readException();
            Rect result = Rect.CREATOR.createFromParcel(reply);
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setHandleRegion(int windowingMode, Rect handleRegion) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(windowingMode);
            handleRegion.writeToParcel(data, 1);
            this.mRemote.transact(IOplusActivityTaskManager.SPLIT_SCREEN_SET_HANDLE_REGION, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public IOplusSplitScreenSession getSplitScreenSession() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.SPLIT_SCREEN_GET_SESSION, data, reply, 0);
            reply.readException();
            IOplusSplitScreenSession ret = IOplusSplitScreenSession.Stub.asInterface(reply.readStrongBinder());
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean hasNewDragSplitFeature() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.SPLITS_SCREEN_HAS_NEW_DRAG_SPLIT_FEATURE, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerKeyEventObserver(String observerFingerPrint, IOplusKeyEventObserver observer, int listenFlag) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(observerFingerPrint);
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            data.writeInt(listenFlag);
            this.mRemote.transact(10103, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterKeyEventObserver(String observerFingerPrint) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(observerFingerPrint);
            this.mRemote.transact(10104, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean updateAppData(String module, Bundle bundle) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(module);
            data.writeBundle(bundle);
            this.mRemote.transact(10107, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean registerSysStateChangeObserver(String module, ISysStateChangeCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(module);
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10108, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean unregisterSysStateChangeObserver(String module, ISysStateChangeCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(module);
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10109, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerKeyEventInterceptor(String interceptorFingerPrint, IOplusKeyEventObserver observer, Map<Integer, Integer> configs) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(interceptorFingerPrint);
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            data.writeMap(configs);
            this.mRemote.transact(10105, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterKeyEventInterceptor(String interceptorFingerPrint) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(interceptorFingerPrint);
            this.mRemote.transact(10106, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusGlobalDragAndDropRUSConfig getGlobalDragAndDropConfig() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new OplusGlobalDragAndDropRUSConfig();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10110, data, reply, 0);
            reply.readException();
            OplusGlobalDragAndDropRUSConfig config = (OplusGlobalDragAndDropRUSConfig) reply.readParcelable(OplusGlobalDragAndDropRUSConfig.class.getClassLoader());
            return config;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setGlobalDragAndDropConfig(OplusGlobalDragAndDropRUSConfig config) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeParcelable(config, 0);
            this.mRemote.transact(10111, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerMirageWindowObserver(IOplusMirageWindowObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10112, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterMirageWindowObserver(IOplusMirageWindowObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10113, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerMirageDisplayObserver(IOplusMirageDisplayObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(IOplusActivityTaskManager.REGISTER_MIRAGE_DISPLAY_OBSERVER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterMirageDisplayObserver(IOplusMirageDisplayObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(IOplusActivityTaskManager.UNREGISTER_MIRAGE_DISPLAY_OBSERVER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void feedbackUserSelection(int eventId, int selection, Bundle extension) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(eventId);
            data.writeInt(selection);
            data.writeBundle(extension);
            this.mRemote.transact(IOplusActivityTaskManager.FEEDBACK_USER_SELECTION, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updateCarModeMultiLaunchWhiteList(String list) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(list);
            this.mRemote.transact(IOplusActivityTaskManager.UPDATE_CAR_MODE_MULTI_LAUNCH_WHITE_LIST, data, reply, 0);
            reply.readException();
            boolean success = reply.readBoolean();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int createMirageDisplay(Surface surface) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeParcelable(surface, 0);
            this.mRemote.transact(10114, data, reply, 0);
            reply.readException();
            int result = Integer.valueOf(reply.readString()).intValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void startMirageWindowMode(ComponentName cpnName, int taskId, int flags, Bundle options) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            ComponentName.writeToParcel(cpnName, data);
            data.writeInt(taskId);
            data.writeInt(flags);
            if (options != null) {
                data.writeInt(1);
                data.writeBundle(options);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(10115, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int startMirageWindowMode(Intent intent, Bundle options) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            if (intent != null) {
                data.writeInt(1);
                intent.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            if (options != null) {
                data.writeInt(1);
                data.writeBundle(options);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(IOplusActivityTaskManager.START_MIRAGE_WINDOW_MODE_NEW, data, reply, 0);
            reply.readException();
            int sessionId = reply.readInt();
            return sessionId;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isMirageWindowShow() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10116, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void stopMirageWindowMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10117, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void stopMirageWindowMode(Bundle options) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            if (options != null) {
                data.writeInt(1);
                data.writeBundle(options);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(IOplusActivityTaskManager.STOP_MIRAGE_WINDOW_MODE_NEW, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setMirageDisplaySurfaceById(int displayId, Surface surface) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(displayId);
            if (surface != null) {
                data.writeInt(1);
                data.writeParcelable(surface, 0);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(IOplusActivityTaskManager.SET_MIRAGE_DISPLAY_SURFACE_BY_ID, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setMirageDisplaySurfaceByMode(int mode, Surface surface) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(mode);
            if (surface != null) {
                data.writeInt(1);
                data.writeParcelable(surface, 0);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(IOplusActivityTaskManager.SET_MIRAGE_DISPLAY_SURFACE_BY_MODE, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getMirageDisplayCastMode(int displayId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(displayId);
            this.mRemote.transact(IOplusActivityTaskManager.GET_MIRAGE_DISPLAY_CAST_MODE, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void expandToFullScreen() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10118, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void setMirageWindowSilent(String pkgName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(pkgName);
            this.mRemote.transact(10119, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isSupportMirageWindowMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10120, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public OplusMirageWindowInfo getMirageWindowInfo() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        new OplusMirageWindowInfo();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(10121, data, reply, 0);
            reply.readException();
            OplusMirageWindowInfo info = OplusMirageWindowInfo.CREATOR.createFromParcel(reply);
            return info;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updateMirageWindowCastFlag(int castFlag, Bundle options) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(castFlag);
            if (options != null) {
                data.writeInt(1);
                data.writeBundle(options);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(10124, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updatePrivacyProtectionList(List<String> name, boolean replace) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStringList(name);
            data.writeBoolean(replace);
            this.mRemote.transact(10125, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean updatePrivacyProtectionList(List<String> name, boolean append, boolean isDefault, Bundle options) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStringList(name);
            data.writeBoolean(append);
            data.writeBoolean(isDefault);
            if (options != null) {
                data.writeInt(1);
                data.writeBundle(options);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(10138, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public IOplusMirageWindowSession createMirageWindowSession(IOplusMirageSessionCallback callback) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(callback != null ? callback.asBinder() : null);
            this.mRemote.transact(10137, data, reply, 0);
            reply.readException();
            IOplusMirageWindowSession session = IOplusMirageWindowSession.Stub.asInterface(reply.readStrongBinder());
            return session;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getGetDisplayIdForPackageName(String packageName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(packageName);
            this.mRemote.transact(IOplusActivityTaskManager.GET_DISPLAY_ID_FOR_PACKAGE_NAME, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean rebindDisplayIfNeeded(int castDisplayId, int mirageDisplayId) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(castDisplayId);
            data.writeInt(mirageDisplayId);
            this.mRemote.transact(IOplusActivityTaskManager.REBIND_DISPLAY_IF_NEEDED, data, reply, 0);
            reply.readException();
            boolean result = reply.readBoolean();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerConfineModeObserver(IOplusConfineModeObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10122, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterConfineModeObserver(IOplusConfineModeObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10123, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public String readNodeFile(int nodeFlag) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(nodeFlag);
            this.mRemote.transact(10126, data, reply, 0);
            reply.readException();
            String result = reply.readString();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public String readNodeFileByDevice(int deviceId, int nodeFlag) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(deviceId);
            data.writeInt(nodeFlag);
            this.mRemote.transact(IOplusActivityTaskManager.READ_EXTRA_NODE_FILE, data, reply, 0);
            reply.readException();
            String result = reply.readString();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean writeNodeFile(int nodeFlag, String info) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(nodeFlag);
            data.writeString(info);
            this.mRemote.transact(10127, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean writeNodeFileByDevice(int deviceId, int nodeFlag, String info) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(deviceId);
            data.writeInt(nodeFlag);
            data.writeString(info);
            this.mRemote.transact(IOplusActivityTaskManager.WRITE_EXTRA_NODE_FILE, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isTouchNodeSupport(int deviceId, int nodeFlag) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(deviceId);
            data.writeInt(nodeFlag);
            this.mRemote.transact(IOplusActivityTaskManager.IS_TOUCH_NODE, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public void clientTransactionComplete(IBinder token, int seq) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(token);
            data.writeInt(seq);
            this.mRemote.transact(10130, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerZoomAppObserver(IOplusZoomAppObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10133, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterZoomAppObserver(IOplusZoomAppObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10134, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean registerSecurityPageCallback(ISecurityPageController observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10140, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean unregisterSecurityPageCallback(ISecurityPageController observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(IOplusActivityTaskManager.UNREGISTER_SECURITY_PAGE_CALL_BACK, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean getSecurityFlagCurrentPage() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.GET_SECURITY_FLAG_CURRENT_PAGE, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void updateDeferStartingWindowApps(List<String> packages, boolean removeImmdiately) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStringList(packages);
            data.writeBoolean(removeImmdiately);
            this.mRemote.transact(IOplusActivityTaskManager.UPDATE_DEFER_STARTING_WINDOWS_APPS, data, reply, 1);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public String getAppThemeVersion(String pkgName, boolean change) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(pkgName);
            data.writeBoolean(change);
            this.mRemote.transact(IOplusActivityTaskManager.OPLUS_CUSTOM_THEME_PACKAGES, data, reply, 0);
            reply.readException();
            String result = reply.readString();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean lockRotationInGame(int state, String[] packages) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeInt(state);
            data.writeStringArray(packages);
            this.mRemote.transact(IOplusActivityTaskManager.LOCK_GAME_ROTATION, data, reply, 0);
            reply.readException();
            boolean success = reply.readBoolean();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int startCompactWindow() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.START_COMPACT_WINDOW, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int exitCompactWindow() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.EXIT_COMPACT_WINDOW, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean isCurrentAppSupportCompactMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.IS_APP_SUPPORT_COMPACT_MODE, data, reply, 0);
            reply.readException();
            boolean success = Boolean.parseBoolean(reply.readString());
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int moveCompactWindowToLeft() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.MOVE_COMPACT_WINDOW_TO_LEFT, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int moveCompactWindowToRight() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.MOVE_COMPACT_WINDOW_TO_RIGHT, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean registerCompactWindowObserver(IOplusCompactWindowObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(IOplusActivityTaskManager.REGISTER_COMPACT_WINDOW_OBSERVER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.parseBoolean(reply.readString());
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean unregisterCompactWindowObserver(IOplusCompactWindowObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(IOplusActivityTaskManager.UNREGISTER_COMPACT_WINDOW_OBSERVER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.parseBoolean(reply.readString());
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public Map<String, ArrayList<String>> getPWAppInfo() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        Map<String, ArrayList<String>> result = new HashMap<>();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.GET_PW_APP_INFO, data, reply, 0);
            reply.readException();
            reply.readMap(result, Map.class.getClassLoader());
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean onProtocolUpdated(String content) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(content);
            this.mRemote.transact(IOplusActivityTaskManager.ON_PROTOCOL_UPDATED, data, reply, 0);
            reply.readException();
            boolean success = Boolean.parseBoolean(reply.readString());
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public int getFocusMode() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.GET_FOCUS_MODE, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public Rect getFocusBounds(boolean isPrimary) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        Rect result = null;
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeBoolean(isPrimary);
            this.mRemote.transact(IOplusActivityTaskManager.GET_FOCUS_BOUNDS, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = Rect.CREATOR.createFromParcel(reply);
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public ComponentName getFocusComponent(boolean isPrimary) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        ComponentName result = null;
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeBoolean(isPrimary);
            this.mRemote.transact(IOplusActivityTaskManager.GET_FOCUS_COMPONENT, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = ComponentName.CREATOR.createFromParcel(reply);
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public Point getRealSize() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        Point result = null;
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            this.mRemote.transact(IOplusActivityTaskManager.GET_REAL_SIZE, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = Point.CREATOR.createFromParcel(reply);
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public Bundle callMethod(String method, String packageName, int param1, boolean param2, String param3, Bundle object) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        Bundle result = null;
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeString(method);
            data.writeString(packageName);
            data.writeInt(param1);
            data.writeBoolean(param2);
            data.writeString(param3);
            if (object != null) {
                data.writeInt(1);
                object.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(IOplusActivityTaskManager.CALL_METHOD, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = Bundle.CREATOR.createFromParcel(reply);
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public Bundle invokeSync(String packageName, String method, String params, Bundle objects) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        Bundle invokeResult = new Bundle();
        try {
            try {
                data.writeInterfaceToken("android.app.IActivityTaskManager");
                data.writeString(packageName);
                data.writeString(method);
                data.writeString(params);
                if (objects != null) {
                    data.writeInt(1);
                    objects.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                this.mRemote.transact(IOplusActivityTaskManager.INVOKE_SYNC, data, reply, 0);
                reply.readException();
                if (reply.readInt() != 0) {
                    invokeResult = Bundle.CREATOR.createFromParcel(reply);
                }
                invokeResult.putInt("errCode", 0);
            } catch (RemoteException e) {
                invokeResult.putInt("errCode", 523);
            } catch (Exception e2) {
                invokeResult.putInt("errCode", 525);
            }
            return invokeResult;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean addBracketWindowConfigChangedListener(IOplusBracketModeChangedListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(listener != null ? listener.asBinder() : null);
            this.mRemote.transact(IOplusActivityTaskManager.ADD_BRACKET_WINDOW_CONFIG_CHANGED_LISTENER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.app.IOplusActivityTaskManager
    public boolean removeBracketWindowConfigChangedListener(IOplusBracketModeChangedListener listener) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IActivityTaskManager");
            data.writeStrongBinder(listener != null ? listener.asBinder() : null);
            this.mRemote.transact(IOplusActivityTaskManager.REMOVE_BRACKET_WINDOW_CONFIG_CHANGED_LISTENER, data, reply, 0);
            reply.readException();
            boolean success = Boolean.valueOf(reply.readString()).booleanValue();
            return success;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }
}
