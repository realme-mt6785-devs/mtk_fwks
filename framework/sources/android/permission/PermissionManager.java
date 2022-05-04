package android.permission;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.AppGlobals;
import android.app.IActivityManager;
import android.app.PropertyInvalidatedCache;
import android.content.AttributionSource;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.permission.SplitPermissionInfoParcelable;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.permission.IOnPermissionsChangeListener;
import android.permission.IPermissionManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Slog;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
@SystemApi
/* loaded from: classes2.dex */
public final class PermissionManager {
    public static final long CANNOT_INSTALL_WITH_BAD_PERMISSION_GROUPS = 146211400;
    public static final boolean DEBUG_TRACE_GRANTS = false;
    public static final boolean DEBUG_TRACE_PERMISSION_UPDATES = false;
    private static final long EXEMPTED_INDICATOR_ROLE_UPDATE_FREQUENCY_MS = 15000;
    private static final int[] EXEMPTED_ROLES;
    private static final String[] INDICATOR_EXEMPTED_PACKAGES;
    public static final String KILL_APP_REASON_GIDS_CHANGED = "permission grant or revoke changed gids";
    public static final String KILL_APP_REASON_PERMISSIONS_REVOKED = "permissions revoked";
    public static final String LOG_TAG_TRACE_GRANTS = "PermissionGrantTrace";
    private static final String SYSTEM_PKG = "android";
    private final Context mContext;
    private final LegacyPermissionManager mLegacyPermissionManager;
    private List<SplitPermissionInfo> mSplitPermissionInfos;
    private PermissionUsageHelper mUsageHelper;
    private static final String LOG_TAG = PermissionManager.class.getName();
    private static long sLastIndicatorUpdateTime = -1;
    public static final String CACHE_KEY_PACKAGE_INFO = "cache_key.package_info";
    private static final PropertyInvalidatedCache<PermissionQuery, Integer> sPermissionCache = new PropertyInvalidatedCache<PermissionQuery, Integer>(2048, CACHE_KEY_PACKAGE_INFO, "checkPermission") { // from class: android.permission.PermissionManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        public Integer recompute(PermissionQuery query) {
            return Integer.valueOf(PermissionManager.checkPermissionUncached(query.permission, query.pid, query.uid));
        }
    };
    private static PropertyInvalidatedCache<PackageNamePermissionQuery, Integer> sPackageNamePermissionCache = new PropertyInvalidatedCache<PackageNamePermissionQuery, Integer>(16, CACHE_KEY_PACKAGE_INFO, "checkPackageNamePermission") { // from class: android.permission.PermissionManager.2
        /* JADX INFO: Access modifiers changed from: protected */
        public Integer recompute(PackageNamePermissionQuery query) {
            return Integer.valueOf(PermissionManager.checkPackageNamePermissionUncached(query.permName, query.pkgName, query.userId));
        }
    };
    private final ArrayMap<PackageManager.OnPermissionsChangedListener, IOnPermissionsChangeListener> mPermissionListeners = new ArrayMap<>();
    private final IPackageManager mPackageManager = AppGlobals.getPackageManager();
    private final IPermissionManager mPermissionManager = IPermissionManager.Stub.asInterface(ServiceManager.getServiceOrThrow("permissionmgr"));

    static {
        int[] iArr = {17039411, 17039410, 17039412, 17039413, 17039414, 17039415};
        EXEMPTED_ROLES = iArr;
        INDICATOR_EXEMPTED_PACKAGES = new String[iArr.length];
    }

    public PermissionManager(Context context) throws ServiceManager.ServiceNotFoundException {
        this.mContext = context;
        this.mLegacyPermissionManager = (LegacyPermissionManager) context.getSystemService(LegacyPermissionManager.class);
    }

    public PermissionInfo getPermissionInfo(String permissionName, int flags) {
        try {
            String packageName = this.mContext.getOpPackageName();
            return this.mPermissionManager.getPermissionInfo(permissionName, packageName, flags);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<PermissionInfo> queryPermissionsByGroup(String groupName, int flags) {
        try {
            ParceledListSlice<PermissionInfo> parceledList = this.mPermissionManager.queryPermissionsByGroup(groupName, flags);
            if (parceledList == null) {
                return null;
            }
            return parceledList.getList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean addPermission(PermissionInfo permissionInfo, boolean async) {
        try {
            return this.mPermissionManager.addPermission(permissionInfo, async);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removePermission(String permissionName) {
        try {
            this.mPermissionManager.removePermission(permissionName);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public PermissionGroupInfo getPermissionGroupInfo(String groupName, int flags) {
        try {
            return this.mPermissionManager.getPermissionGroupInfo(groupName, flags);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
        try {
            ParceledListSlice<PermissionGroupInfo> parceledList = this.mPermissionManager.getAllPermissionGroups(flags);
            if (parceledList == null) {
                return Collections.emptyList();
            }
            return parceledList.getList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isPermissionRevokedByPolicy(String packageName, String permissionName) {
        try {
            return this.mPermissionManager.isPermissionRevokedByPolicy(packageName, permissionName, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static boolean shouldTraceGrant(String packageName, String permissionName, int userId) {
        return false;
    }

    public void grantRuntimePermission(String packageName, String permissionName, UserHandle user) {
        try {
            this.mPermissionManager.grantRuntimePermission(packageName, permissionName, user.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void revokeRuntimePermission(String packageName, String permName, UserHandle user, String reason) {
        try {
            this.mPermissionManager.revokeRuntimePermission(packageName, permName, user.getIdentifier(), reason);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getPermissionFlags(String packageName, String permissionName, UserHandle user) {
        try {
            return this.mPermissionManager.getPermissionFlags(packageName, permissionName, user.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updatePermissionFlags(String packageName, String permissionName, int flagMask, int flagValues, UserHandle user) {
        try {
            boolean checkAdjustPolicyFlagPermission = this.mContext.getApplicationInfo().targetSdkVersion >= 29;
            this.mPermissionManager.updatePermissionFlags(packageName, permissionName, flagMask, flagValues, checkAdjustPolicyFlagPermission, user.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Set<String> getAllowlistedRestrictedPermissions(String packageName, int allowlistFlag) {
        try {
            List<String> allowlist = this.mPermissionManager.getAllowlistedRestrictedPermissions(packageName, allowlistFlag, this.mContext.getUserId());
            if (allowlist == null) {
                return Collections.emptySet();
            }
            return new ArraySet(allowlist);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean addAllowlistedRestrictedPermission(String packageName, String permissionName, int allowlistFlags) {
        try {
            return this.mPermissionManager.addAllowlistedRestrictedPermission(packageName, permissionName, allowlistFlags, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean removeAllowlistedRestrictedPermission(String packageName, String permissionName, int allowlistFlags) {
        try {
            return this.mPermissionManager.removeAllowlistedRestrictedPermission(packageName, permissionName, allowlistFlags, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isAutoRevokeExempted(String packageName) {
        try {
            return this.mPermissionManager.isAutoRevokeExempted(packageName, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean setAutoRevokeExempted(String packageName, boolean exempted) {
        try {
            return this.mPermissionManager.setAutoRevokeExempted(packageName, exempted, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean shouldShowRequestPermissionRationale(String permissionName) {
        try {
            String packageName = this.mContext.getPackageName();
            return this.mPermissionManager.shouldShowRequestPermissionRationale(packageName, permissionName, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addOnPermissionsChangeListener(PackageManager.OnPermissionsChangedListener listener) {
        synchronized (this.mPermissionListeners) {
            if (this.mPermissionListeners.get(listener) == null) {
                OnPermissionsChangeListenerDelegate delegate = new OnPermissionsChangeListenerDelegate(listener, Looper.getMainLooper());
                try {
                    this.mPermissionManager.addOnPermissionsChangeListener(delegate);
                    this.mPermissionListeners.put(listener, delegate);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    public void removeOnPermissionsChangeListener(PackageManager.OnPermissionsChangedListener listener) {
        synchronized (this.mPermissionListeners) {
            IOnPermissionsChangeListener delegate = this.mPermissionListeners.get(listener);
            if (delegate != null) {
                try {
                    this.mPermissionManager.removeOnPermissionsChangeListener(delegate);
                    this.mPermissionListeners.remove(listener);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    @SystemApi
    public int getRuntimePermissionsVersion() {
        try {
            return this.mPackageManager.getRuntimePermissionsVersion(this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setRuntimePermissionsVersion(int version) {
        try {
            this.mPackageManager.setRuntimePermissionsVersion(version, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<SplitPermissionInfo> getSplitPermissions() {
        List<SplitPermissionInfo> list = this.mSplitPermissionInfos;
        if (list != null) {
            return list;
        }
        try {
            List<SplitPermissionInfoParcelable> parcelableList = ActivityThread.getPermissionManager().getSplitPermissions();
            List<SplitPermissionInfo> splitPermissionInfoListToNonParcelableList = splitPermissionInfoListToNonParcelableList(parcelableList);
            this.mSplitPermissionInfos = splitPermissionInfoListToNonParcelableList;
            return splitPermissionInfoListToNonParcelableList;
        } catch (RemoteException e) {
            Slog.e(LOG_TAG, "Error getting split permissions", e);
            return Collections.emptyList();
        }
    }

    public void initializeUsageHelper() {
        if (this.mUsageHelper == null) {
            this.mUsageHelper = new PermissionUsageHelper(this.mContext);
        }
    }

    public void tearDownUsageHelper() {
        PermissionUsageHelper permissionUsageHelper = this.mUsageHelper;
        if (permissionUsageHelper != null) {
            permissionUsageHelper.tearDown();
            this.mUsageHelper = null;
        }
    }

    public List<PermGroupUsage> getIndicatorAppOpUsageData() {
        return getIndicatorAppOpUsageData(new AudioManager().isMicrophoneMute());
    }

    public List<PermGroupUsage> getIndicatorAppOpUsageData(boolean micMuted) {
        initializeUsageHelper();
        return this.mUsageHelper.getOpUsageData(micMuted);
    }

    public static boolean shouldShowPackageForIndicatorCached(Context context, String packageName) {
        return !getIndicatorExemptedPackages(context).contains(packageName);
    }

    public static Set<String> getIndicatorExemptedPackages(Context context) {
        updateIndicatorExemptedPackages(context);
        ArraySet<String> pkgNames = new ArraySet<>();
        pkgNames.add("android");
        int i = 0;
        while (true) {
            String[] strArr = INDICATOR_EXEMPTED_PACKAGES;
            if (i >= strArr.length) {
                return pkgNames;
            }
            String exemptedPackage = strArr[i];
            if (exemptedPackage != null) {
                pkgNames.add(exemptedPackage);
            }
            i++;
        }
    }

    public static void updateIndicatorExemptedPackages(Context context) {
        long now = SystemClock.elapsedRealtime();
        long j = sLastIndicatorUpdateTime;
        if (j == -1 || now - j > EXEMPTED_INDICATOR_ROLE_UPDATE_FREQUENCY_MS) {
            sLastIndicatorUpdateTime = now;
            int i = 0;
            while (true) {
                int[] iArr = EXEMPTED_ROLES;
                if (i < iArr.length) {
                    INDICATOR_EXEMPTED_PACKAGES[i] = context.getString(iArr[i]);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    @SystemApi
    public Set<String> getAutoRevokeExemptionRequestedPackages() {
        try {
            return CollectionUtils.toSet(this.mPermissionManager.getAutoRevokeExemptionRequestedPackages(this.mContext.getUser().getIdentifier()));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public Set<String> getAutoRevokeExemptionGrantedPackages() {
        try {
            return CollectionUtils.toSet(this.mPermissionManager.getAutoRevokeExemptionGrantedPackages(this.mContext.getUser().getIdentifier()));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private List<SplitPermissionInfo> splitPermissionInfoListToNonParcelableList(List<SplitPermissionInfoParcelable> parcelableList) {
        int size = parcelableList.size();
        List<SplitPermissionInfo> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new SplitPermissionInfo(parcelableList.get(i)));
        }
        return list;
    }

    public static List<SplitPermissionInfoParcelable> splitPermissionInfoListToParcelableList(List<SplitPermissionInfo> splitPermissionsList) {
        int size = splitPermissionsList.size();
        List<SplitPermissionInfoParcelable> outList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            SplitPermissionInfo info = splitPermissionsList.get(i);
            outList.add(new SplitPermissionInfoParcelable(info.getSplitPermission(), info.getNewPermissions(), info.getTargetSdk()));
        }
        return outList;
    }

    /* loaded from: classes2.dex */
    public static final class SplitPermissionInfo {
        private final SplitPermissionInfoParcelable mSplitPermissionInfoParcelable;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SplitPermissionInfo that = (SplitPermissionInfo) o;
            return this.mSplitPermissionInfoParcelable.equals(that.mSplitPermissionInfoParcelable);
        }

        public int hashCode() {
            return this.mSplitPermissionInfoParcelable.hashCode();
        }

        public String getSplitPermission() {
            return this.mSplitPermissionInfoParcelable.getSplitPermission();
        }

        public List<String> getNewPermissions() {
            return this.mSplitPermissionInfoParcelable.getNewPermissions();
        }

        public int getTargetSdk() {
            return this.mSplitPermissionInfoParcelable.getTargetSdk();
        }

        public SplitPermissionInfo(String splitPerm, List<String> newPerms, int targetSdk) {
            this(new SplitPermissionInfoParcelable(splitPerm, newPerms, targetSdk));
        }

        private SplitPermissionInfo(SplitPermissionInfoParcelable parcelable) {
            this.mSplitPermissionInfoParcelable = parcelable;
        }
    }

    @SystemApi
    public void startOneTimePermissionSession(String packageName, long timeoutMillis, int importanceToResetTimer, int importanceToKeepSessionAlive) {
        try {
            this.mPermissionManager.startOneTimePermissionSession(packageName, this.mContext.getUserId(), timeoutMillis, importanceToResetTimer, importanceToKeepSessionAlive);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void stopOneTimePermissionSession(String packageName) {
        try {
            this.mPermissionManager.stopOneTimePermissionSession(packageName, this.mContext.getUserId());
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int checkDeviceIdentifierAccess(String packageName, String message, String callingFeatureId, int pid, int uid) {
        return this.mLegacyPermissionManager.checkDeviceIdentifierAccess(packageName, message, callingFeatureId, pid, uid);
    }

    public AttributionSource registerAttributionSource(AttributionSource source) {
        AttributionSource registeredSource = source.withToken(new Binder());
        try {
            this.mPermissionManager.registerAttributionSource(registeredSource.asState());
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
        return registeredSource;
    }

    public boolean isRegisteredAttributionSource(AttributionSource source) {
        try {
            return this.mPermissionManager.isRegisteredAttributionSource(source.asState());
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int checkPermissionUncached(String permission, int pid, int uid) {
        IActivityManager am = ActivityManager.getService();
        if (am == null) {
            int appId = UserHandle.getAppId(uid);
            if (appId == 0 || appId == 1000) {
                String str = LOG_TAG;
                Slog.w(str, "Missing ActivityManager; assuming " + uid + " holds " + permission);
                return 0;
            }
            String str2 = LOG_TAG;
            Slog.w(str2, "Missing ActivityManager; assuming " + uid + " does not hold " + permission);
            return -1;
        }
        try {
            return am.checkPermission(permission, pid, uid);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class PermissionQuery {
        final String permission;
        final int pid;
        final int uid;

        PermissionQuery(String permission, int pid, int uid) {
            this.permission = permission;
            this.pid = pid;
            this.uid = uid;
        }

        public String toString() {
            return String.format("PermissionQuery(permission=\"%s\", pid=%s, uid=%s)", this.permission, Integer.valueOf(this.pid), Integer.valueOf(this.uid));
        }

        public int hashCode() {
            int hash = Objects.hashCode(this.permission);
            return (hash * 13) + Objects.hashCode(Integer.valueOf(this.uid));
        }

        public boolean equals(Object rval) {
            if (rval == null) {
                return false;
            }
            try {
                PermissionQuery other = (PermissionQuery) rval;
                return this.uid == other.uid && Objects.equals(this.permission, other.permission);
            } catch (ClassCastException e) {
                return false;
            }
        }
    }

    public static int checkPermission(String permission, int pid, int uid) {
        return sPermissionCache.query(new PermissionQuery(permission, pid, uid)).intValue();
    }

    public static void disablePermissionCache() {
        sPermissionCache.disableLocal();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class PackageNamePermissionQuery {
        final String permName;
        final String pkgName;
        final int userId;

        PackageNamePermissionQuery(String permName, String pkgName, int userId) {
            this.permName = permName;
            this.pkgName = pkgName;
            this.userId = userId;
        }

        public String toString() {
            return String.format("PackageNamePermissionQuery(pkgName=\"%s\", permName=\"%s, userId=%s\")", this.pkgName, this.permName, Integer.valueOf(this.userId));
        }

        public int hashCode() {
            return Objects.hash(this.permName, this.pkgName, Integer.valueOf(this.userId));
        }

        public boolean equals(Object rval) {
            if (rval == null) {
                return false;
            }
            try {
                PackageNamePermissionQuery other = (PackageNamePermissionQuery) rval;
                return Objects.equals(this.permName, other.permName) && Objects.equals(this.pkgName, other.pkgName) && this.userId == other.userId;
            } catch (ClassCastException e) {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int checkPackageNamePermissionUncached(String permName, String pkgName, int userId) {
        try {
            return ActivityThread.getPackageManager().checkPermission(permName, pkgName, userId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static int checkPackageNamePermission(String permName, String pkgName, int userId) {
        return sPackageNamePermissionCache.query(new PackageNamePermissionQuery(permName, pkgName, userId)).intValue();
    }

    public static void disablePackageNamePermissionCache() {
        sPackageNamePermissionCache.disableLocal();
    }

    /* loaded from: classes2.dex */
    private final class OnPermissionsChangeListenerDelegate extends IOnPermissionsChangeListener.Stub implements Handler.Callback {
        private static final int MSG_PERMISSIONS_CHANGED = 1;
        private final Handler mHandler;
        private final PackageManager.OnPermissionsChangedListener mListener;

        public OnPermissionsChangeListenerDelegate(PackageManager.OnPermissionsChangedListener listener, Looper looper) {
            this.mListener = listener;
            this.mHandler = new Handler(looper, this);
        }

        @Override // android.permission.IOnPermissionsChangeListener
        public void onPermissionsChanged(int uid) {
            this.mHandler.obtainMessage(1, uid, 0).sendToTarget();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int uid = msg.arg1;
                    this.mListener.onPermissionsChanged(uid);
                    return true;
                default:
                    return false;
            }
        }
    }
}
