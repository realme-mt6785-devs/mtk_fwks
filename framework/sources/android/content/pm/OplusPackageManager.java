package android.content.pm;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.PackageManager;
import android.content.res.IUxIconPackageManagerExt;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.oplus.app.OplusAppDynamicFeatureData;
import com.oplus.content.OplusRemovableAppInfo;
import com.oplus.content.OplusRuleInfo;
import com.oplus.ota.OplusSystemUpdateInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class OplusPackageManager extends OplusBasePackageManager implements IOplusPackageManager, IUxIconPackageManagerExt {
    public static final int FLAG_APP_DATA_CE = 2;
    public static final int FLAG_APP_DATA_DE = 1;
    public static final int FLAG_APP_DATA_EXTERNAL_DATA = 16;
    public static final int FLAG_APP_DATA_EXTERNAL_MEDIA = 32;
    public static final int FLAG_APP_DATA_EXTERNAL_OBB = 64;
    public static final int INSTALL_FROM_OPLUS_ADB_INSTALLER = 268435456;
    public static final int INSTALL_SPEED_BACKGROUND = Integer.MIN_VALUE;
    public static final int INSTALL_SPEED_CPU_HIGH = 1073741824;
    public static final int INSTALL_SPEED_CPU_MID = 536870912;
    public static final int MATCH_OPLUS_FREEZE_APP = 1073741824;
    public static final int OPLUS_DONT_KILL_APP = 268435456;
    public static final int OPLUS_FREEZE_FLAG_AUTO = 2;
    public static final int OPLUS_FREEZE_FLAG_MANUAL = 1;
    public static final int OPLUS_UNFREEZE_FLAG_NORMAL = 1;
    public static final int OPLUS_UNFREEZE_FLAG_TEMP = 2;
    public static final int RE_INSTALL_DUPLICATE_PERMISSION = 1;
    public static final int STATE_OPLUS_FREEZE_FREEZED = 2;
    public static final int STATE_OPLUS_FREEZE_NORMAL = 0;
    public static final int STATE_OPLUS_FREEZE_TEMP_UNFREEZED = 1;
    private static final String TAG = "OplusPackageManager";
    private static OplusPackageManager mOplusPackageManager = null;
    private static final int sDefaultFlags = 1024;
    private final Context mContext;
    private final PackageDeleteObserver mPackageDeleleteObserver;
    private static HashMap<String, Bitmap> mAppIconsCache = new HashMap<>();
    private static HashMap<String, Bitmap> mActivityIconsCache = new HashMap<>();
    private static boolean mIconCacheDirty = false;

    public OplusPackageManager(Context context) {
        this.mPackageDeleleteObserver = new PackageDeleteObserver();
        this.mContext = context;
    }

    public OplusPackageManager() {
        this.mPackageDeleleteObserver = new PackageDeleteObserver();
        this.mContext = null;
    }

    public static OplusPackageManager getOplusPackageManager(Context context) {
        OplusPackageManager oplusPackageManager = mOplusPackageManager;
        if (oplusPackageManager != null) {
            return oplusPackageManager;
        }
        OplusPackageManager oplusPackageManager2 = new OplusPackageManager(context);
        mOplusPackageManager = oplusPackageManager2;
        return oplusPackageManager2;
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean isClosedSuperFirewall() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        boolean closed = false;
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                this.mRemote.transact(10002, data, reply, 0);
                reply.readException();
                closed = Boolean.valueOf(reply.readString()).booleanValue();
            } catch (RemoteException e) {
                Log.e(TAG, "isClosedSuperFirewall failed");
            }
            return closed;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public Bitmap getAppIconBitmap(String packageName) throws RemoteException {
        Bitmap result;
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(packageName);
            this.mRemote.transact(10004, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = Bitmap.CREATOR.createFromParcel(reply);
            } else {
                result = null;
            }
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public Map getAppIconsCache(boolean compress) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeInt(compress ? 1 : 0);
            this.mRemote.transact(10005, data, reply, 0);
            reply.readException();
            ClassLoader cl = getClass().getClassLoader();
            Map result = reply.readHashMap(cl);
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public Map getActivityIconsCache(IPackageDeleteObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeStrongBinder(observer != null ? observer.asBinder() : null);
            this.mRemote.transact(10006, data, reply, 0);
            reply.readException();
            ClassLoader cl = getClass().getClassLoader();
            Map result = reply.readHashMap(cl);
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean prohibitChildInstallation(int userId, boolean isInstall) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeInt(userId);
            data.writeBoolean(isInstall);
            boolean result = false;
            this.mRemote.transact(10007, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = true;
            }
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public int oplusFreezePackage(String pkgName, int userId, int freezeFlag, int flag, String callingPkg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(pkgName);
            data.writeInt(userId);
            data.writeInt(freezeFlag);
            data.writeInt(flag);
            data.writeString(callingPkg);
            this.mRemote.transact(10008, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public int oplusUnFreezePackage(String pkgName, int userId, int freezeFlag, int flag, String callingPkg) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(pkgName);
            data.writeInt(userId);
            data.writeInt(freezeFlag);
            data.writeInt(flag);
            data.writeString(callingPkg);
            this.mRemote.transact(10009, data, reply, 0);
            reply.readException();
            int result = reply.readInt();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public int getOplusFreezePackageState(String pkgName, int userId) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            _data.writeString(pkgName);
            _data.writeInt(userId);
            this.mRemote.transact(10010, _data, _reply, 0);
            _reply.readException();
            int _result = _reply.readInt();
            return _result;
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean inOplusFreezePackageList(String pkgName, int userId) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            _data.writeString(pkgName);
            _data.writeInt(userId);
            boolean _result = false;
            this.mRemote.transact(10011, _data, _reply, 0);
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

    @Override // android.content.pm.IOplusPackageManager
    public List<String> getOplusFreezedPackageList(int userId) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            _data.writeInt(userId);
            this.mRemote.transact(10012, _data, _reply, 0);
            _reply.readException();
            List<String> _result = _reply.createStringArrayList();
            return _result;
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public int getOplusPackageFreezeFlag(String pkgName, int userId) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            _data.writeString(pkgName);
            _data.writeInt(userId);
            this.mRemote.transact(10013, _data, _reply, 0);
            _reply.readException();
            int _result = _reply.readInt();
            return _result;
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean loadRegionFeature(String name) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(name);
            boolean result = false;
            this.mRemote.transact(10014, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = true;
            }
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public FeatureInfo[] getOplusSystemAvailableFeatures() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10015, data, reply, 0);
            reply.readException();
            FeatureInfo[] result = (FeatureInfo[]) reply.createTypedArray(FeatureInfo.CREATOR);
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean isSecurePayApp(String name) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            _data.writeString(name);
            boolean _result = false;
            this.mRemote.transact(10016, _data, _reply, 0);
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

    @Override // android.content.pm.IOplusPackageManager
    public boolean isSystemDataApp(String packageName) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            _data.writeString(packageName);
            boolean _result = false;
            this.mRemote.transact(10017, _data, _reply, 0);
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

    @Override // android.content.pm.IOplusPackageManager
    public boolean inPmsWhiteList(int type, String verifyStr, List<String> defaultList) {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        boolean _result = false;
        try {
            try {
                _data.writeInterfaceToken("android.app.IPackageManager");
                _data.writeInt(type);
                _data.writeString(verifyStr);
                _data.writeStringList(defaultList);
                _result = false;
                this.mRemote.transact(10020, _data, _reply, 0);
                _reply.readException();
                if (_reply.readInt() != 0) {
                    _result = true;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "inPmsWhiteList failed");
            }
            return _result;
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean setInterceptRuleInfos(List<OplusRuleInfo> infos) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            _data.writeTypedList(infos);
            boolean _result = false;
            this.mRemote.transact(10018, _data, _reply, 0);
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

    @Override // android.content.pm.IOplusPackageManager
    public List<OplusRuleInfo> getInterceptRuleInfos() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10019, _data, _reply, 0);
            _reply.readException();
            List<OplusRuleInfo> _result = _reply.createTypedArrayList(OplusRuleInfo.CREATOR);
            return _result;
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean isFullFunctionMode() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10002, _data, _reply, 0);
            _reply.readException();
            boolean _result = Boolean.valueOf(_reply.readString()).booleanValue();
            return _result;
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public Drawable getApplicationIconCacheAll(ApplicationInfo info) {
        Context context = this.mContext;
        if (context != null) {
            return info.loadIcon(context.getPackageManager());
        }
        Log.e(TAG, "App must create OplusPackageManager with context parameter when using this method");
        return null;
    }

    @Override // android.content.pm.IOplusPackageManager
    public Drawable getApplicationIconCache(ApplicationInfo info) {
        Context context = this.mContext;
        if (context != null) {
            return info.loadIcon(context.getPackageManager());
        }
        Log.e(TAG, "App must create OplusPackageManager with context parameter when using this method");
        return null;
    }

    @Override // android.content.pm.IOplusPackageManager
    public Drawable getApplicationIconCache(String packageName) throws PackageManager.NameNotFoundException {
        Context context = this.mContext;
        if (context != null) {
            return context.getPackageManager().getApplicationIcon(this.mContext.getPackageManager().getApplicationInfoAsUser(packageName, 1024, ActivityManager.getCurrentUser()));
        }
        Log.e(TAG, "App must create OplusPackageManager with context parameter when using this method");
        return null;
    }

    @Override // android.content.pm.IOplusPackageManager
    public Drawable getApplicationIconCacheOrignal(ApplicationInfo info) {
        Context context = this.mContext;
        if (context != null) {
            return info.loadIcon(context.getPackageManager());
        }
        Log.e(TAG, "App must create OplusPackageManager with context parameter when using this method");
        return null;
    }

    @Override // android.content.pm.IOplusPackageManager
    public Drawable getApplicationIconCacheOrignal(String packageName) throws PackageManager.NameNotFoundException {
        Context context = this.mContext;
        if (context != null) {
            return context.getPackageManager().getApplicationIcon(this.mContext.getPackageManager().getApplicationInfoAsUser(packageName, 1024, ActivityManager.getCurrentUser()));
        }
        Log.e(TAG, "App must create OplusPackageManager with context parameter when using this method");
        return null;
    }

    /* loaded from: classes.dex */
    private class PackageDeleteObserver extends IPackageDeleteObserver.Stub {
        private PackageDeleteObserver() {
        }

        @Override // android.content.pm.IPackageDeleteObserver
        public void packageDeleted(String packageName, int returnCode) {
            if (packageName != null) {
                try {
                    if (OplusPackageManager.mAppIconsCache.get(packageName) != null) {
                        OplusPackageManager.mAppIconsCache.remove(packageName);
                    }
                    ArrayList<String> deleteList = new ArrayList<>();
                    for (Map.Entry entry : OplusPackageManager.mActivityIconsCache.entrySet()) {
                        String key = (String) entry.getKey();
                        if (packageName.equals(key.split("/")[0])) {
                            deleteList.add(key);
                        }
                    }
                    Iterator<String> it = deleteList.iterator();
                    while (it.hasNext()) {
                        String deleteName = it.next();
                        OplusPackageManager.mActivityIconsCache.remove(deleteName);
                    }
                    boolean unused = OplusPackageManager.mIconCacheDirty = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public Drawable getActivityIconCache(ComponentName componentName) throws PackageManager.NameNotFoundException {
        Context context = this.mContext;
        if (context != null) {
            return context.getPackageManager().getActivityInfo(componentName, 1024).loadIcon(this.mContext.getPackageManager());
        }
        Log.e(TAG, "App must create OplusPackageManager with context parameter when using this method");
        return null;
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<String> getRemovableAppList() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10021, data, reply, 0);
            reply.readException();
            List<String> result = reply.createStringArrayList();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<OplusRemovableAppInfo> getRemovedAppInfos() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10022, data, reply, 0);
            reply.readException();
            List<OplusRemovableAppInfo> _result = reply.createTypedArrayList(OplusRemovableAppInfo.CREATOR);
            return _result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<OplusRemovableAppInfo> getRemovableAppInfos() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10023, data, reply, 0);
            reply.readException();
            List<OplusRemovableAppInfo> result = reply.createTypedArrayList(OplusRemovableAppInfo.CREATOR);
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public OplusRemovableAppInfo getRemovableAppInfo(String packageName) throws RemoteException {
        OplusRemovableAppInfo result;
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(packageName);
            this.mRemote.transact(10024, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = OplusRemovableAppInfo.CREATOR.createFromParcel(reply);
            } else {
                result = null;
            }
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean restoreRemovableApp(String packageName, IntentSender sender, Bundle bundle) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(packageName);
            boolean result = true;
            if (sender != null) {
                data.writeInt(1);
                sender.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            if (bundle != null) {
                data.writeInt(1);
                bundle.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            this.mRemote.transact(10025, data, reply, 0);
            reply.readException();
            if (reply.readInt() == 0) {
                result = false;
            }
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public void checkEMMApkRuntimePermission(ComponentName cn) throws SecurityException {
        String packageName = cn.getPackageName();
        if (packageName != null) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                try {
                    data.writeInterfaceToken("android.app.IPackageManager");
                    data.writeString(packageName);
                    this.mRemote.transact(10026, data, reply, 0);
                    reply.readException();
                    String ret = reply.readString();
                    Log.d(TAG, "check EMM apk runtime permission:" + ret);
                    if (!"".equals(ret)) {
                        throw new SecurityException(ret);
                    }
                } catch (RemoteException e) {
                    throw new SecurityException(e.getMessage());
                }
            } finally {
                reply.recycle();
                data.recycle();
            }
        } else {
            throw new SecurityException("Package name is null");
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean isSupportSessionWrite() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10027, data, reply, 0);
            reply.readException();
            boolean suppoert = Boolean.valueOf(reply.readString()).booleanValue();
            return suppoert;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<String> getCptListByType(int tag) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeInt(tag);
            this.mRemote.transact(10028, data, reply, 0);
            reply.readException();
            List<String> result = reply.createStringArrayList();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public void sendCptUpload(String pkgName, String point) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(pkgName);
            data.writeString(point);
            this.mRemote.transact(10029, data, reply, 0);
            reply.readException();
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean inCptWhiteList(int type, String verifyStr) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        boolean result = false;
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                data.writeInt(type);
                data.writeString(verifyStr);
                result = false;
                this.mRemote.transact(10030, data, reply, 0);
                reply.readException();
                if (reply.readInt() != 0) {
                    result = true;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "inCptWhiteList failed");
            }
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean inOplusStandardWhiteList(String filterName, int type, String verifyStr) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(filterName);
            data.writeInt(type);
            data.writeString(verifyStr);
            boolean result = false;
            this.mRemote.transact(10033, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = true;
            }
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public void sendMapCommonDcsUpload(String logTag, String eventId, Map map) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(logTag);
            data.writeString(eventId);
            data.writeMap(map);
            this.mRemote.transact(10031, data, reply, 0);
            reply.readException();
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<ApplicationInfo> getIconPackList() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<ApplicationInfo> list = null;
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                this.mRemote.transact(10032, data, reply, 0);
                reply.readException();
                list = reply.createTypedArrayList(ApplicationInfo.CREATOR);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
            return list;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public void dynamicDetectApp(OplusAppDynamicFeatureData featureData) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            featureData.writeToParcel(data, 0);
            this.mRemote.transact(10034, data, reply, 1);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean isDetectApp(String packageName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(packageName);
            boolean result = false;
            this.mRemote.transact(10035, data, reply, 0);
            reply.readException();
            if (reply.readInt() != 0) {
                result = true;
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<String> getDetectAppList() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10036, data, reply, 0);
            reply.readException();
            List<String> result = reply.createStringArrayList();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean isCrossVersionUpdate() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10037, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<String> getNotInstalledSystemApps() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10038, data, reply, 0);
            reply.readException();
            List<String> result = reply.createStringArrayList();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<String> getValidAppList() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10039, data, reply, 0);
            reply.readException();
            List<String> result = reply.createStringArrayList();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public List<String> getAppListFromPartition(String partition) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(partition);
            this.mRemote.transact(10040, data, reply, 0);
            reply.readException();
            List<String> result = reply.createStringArrayList();
            return result;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public void deletePackageDelegated(String packageName, int callUid, int callPid, int flags, int userId, IPackageDeleteObserver observer) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                data.writeString(packageName);
                data.writeInt(callUid);
                data.writeInt(callPid);
                data.writeInt(flags);
                data.writeInt(userId);
                data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                this.mRemote.transact(10041, data, reply, 0);
                reply.readException();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public OplusSystemUpdateInfo getSystemUpdateInfo() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            this.mRemote.transact(10042, data, reply, 0);
            reply.readException();
            OplusSystemUpdateInfo result = (OplusSystemUpdateInfo) reply.readTypedObject(OplusSystemUpdateInfo.CREATOR);
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean fixupAppData(String pkgName, int flags) throws RuntimeException {
        return fixupAppData(pkgName, null, flags);
    }

    public boolean fixupAppData(String pkgName, String relativePath, int flags) throws RuntimeException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                data.writeString(pkgName);
                data.writeString(relativePath);
                data.writeInt(flags);
                this.mRemote.transact(10043, data, reply, 0);
                reply.readException();
                boolean result = reply.readBoolean();
                return result;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public String getMigMappingPkgName(boolean findOldNameByNew, String refPkgName) throws RuntimeException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        String result = null;
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                data.writeBoolean(findOldNameByNew);
                data.writeString(refPkgName);
                this.mRemote.transact(10044, data, reply, 0);
                reply.readException();
                result = reply.readString();
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public List<String> getUninstallableAppConfig(int type) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        List<String> result = new ArrayList<>();
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                data.writeInt(type);
                this.mRemote.transact(10048, data, reply, 0);
                if (reply.dataSize() > 0) {
                    reply.readException();
                    result = reply.createStringArrayList();
                } else {
                    Log.d(TAG, "getUninstallableAppConfig no reply");
                }
                return result;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public boolean inUninstallableAppConfig(int type, String pkgName) {
        if (TextUtils.isEmpty(pkgName)) {
            return false;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        boolean result = false;
        try {
            try {
                data.writeInterfaceToken("android.app.IPackageManager");
                data.writeInt(type);
                data.writeString(pkgName);
                this.mRemote.transact(10049, data, reply, 0);
                if (reply.dataSize() > 0) {
                    reply.readException();
                    result = reply.readBoolean();
                } else {
                    Log.d(TAG, "inUninstallableAppConfig no reply");
                }
                return result;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public boolean setCustomizeDefaultApp(String roleName, String packageName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(roleName);
            data.writeString(packageName);
            this.mRemote.transact(10045, data, reply, 0);
            reply.readException();
            boolean result = Boolean.valueOf(reply.readString()).booleanValue();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public void removeCustomizeDefaultApp(String roleName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(roleName);
            this.mRemote.transact(10046, data, reply, 0);
            reply.readException();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override // android.content.pm.IOplusPackageManager
    public String getCustomizeDefaultApp(String roleName) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.app.IPackageManager");
            data.writeString(roleName);
            this.mRemote.transact(10047, data, reply, 0);
            reply.readException();
            String result = reply.readString();
            return result;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }
}
