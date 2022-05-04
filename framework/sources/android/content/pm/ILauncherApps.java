package android.content.pm;

import android.app.IApplicationThread;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.IntentSender;
import android.content.pm.IOnAppsChangedListener;
import android.content.pm.IPackageInstallerCallback;
import android.content.pm.IShortcutChangeCallback;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInstaller;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;
/* loaded from: classes.dex */
public interface ILauncherApps extends IInterface {
    void addOnAppsChangedListener(String str, IOnAppsChangedListener iOnAppsChangedListener) throws RemoteException;

    void cacheShortcuts(String str, String str2, List<String> list, UserHandle userHandle, int i) throws RemoteException;

    PendingIntent getActivityLaunchIntent(ComponentName componentName, Bundle bundle, UserHandle userHandle) throws RemoteException;

    ParceledListSlice getAllSessions(String str) throws RemoteException;

    LauncherApps.AppUsageLimit getAppUsageLimit(String str, String str2, UserHandle userHandle) throws RemoteException;

    ApplicationInfo getApplicationInfo(String str, String str2, int i, UserHandle userHandle) throws RemoteException;

    ParceledListSlice getLauncherActivities(String str, String str2, UserHandle userHandle) throws RemoteException;

    ParceledListSlice getShortcutConfigActivities(String str, String str2, UserHandle userHandle) throws RemoteException;

    IntentSender getShortcutConfigActivityIntent(String str, ComponentName componentName, UserHandle userHandle) throws RemoteException;

    ParcelFileDescriptor getShortcutIconFd(String str, String str2, String str3, int i) throws RemoteException;

    int getShortcutIconResId(String str, String str2, String str3, int i) throws RemoteException;

    String getShortcutIconUri(String str, String str2, String str3, int i) throws RemoteException;

    PendingIntent getShortcutIntent(String str, String str2, String str3, Bundle bundle, UserHandle userHandle) throws RemoteException;

    ParceledListSlice getShortcuts(String str, ShortcutQueryWrapper shortcutQueryWrapper, UserHandle userHandle) throws RemoteException;

    Bundle getSuspendedPackageLauncherExtras(String str, UserHandle userHandle) throws RemoteException;

    boolean hasShortcutHostPermission(String str) throws RemoteException;

    boolean isActivityEnabled(String str, ComponentName componentName, UserHandle userHandle) throws RemoteException;

    boolean isPackageEnabled(String str, String str2, UserHandle userHandle) throws RemoteException;

    void pinShortcuts(String str, String str2, List<String> list, UserHandle userHandle) throws RemoteException;

    void registerPackageInstallerCallback(String str, IPackageInstallerCallback iPackageInstallerCallback) throws RemoteException;

    void registerShortcutChangeCallback(String str, ShortcutQueryWrapper shortcutQueryWrapper, IShortcutChangeCallback iShortcutChangeCallback) throws RemoteException;

    void removeOnAppsChangedListener(IOnAppsChangedListener iOnAppsChangedListener) throws RemoteException;

    LauncherActivityInfoInternal resolveLauncherActivityInternal(String str, ComponentName componentName, UserHandle userHandle) throws RemoteException;

    boolean shouldHideFromSuggestions(String str, UserHandle userHandle) throws RemoteException;

    void showAppDetailsAsUser(IApplicationThread iApplicationThread, String str, String str2, ComponentName componentName, Rect rect, Bundle bundle, UserHandle userHandle) throws RemoteException;

    void startActivityAsUser(IApplicationThread iApplicationThread, String str, String str2, ComponentName componentName, Rect rect, Bundle bundle, UserHandle userHandle) throws RemoteException;

    void startSessionDetailsActivityAsUser(IApplicationThread iApplicationThread, String str, String str2, PackageInstaller.SessionInfo sessionInfo, Rect rect, Bundle bundle, UserHandle userHandle) throws RemoteException;

    boolean startShortcut(String str, String str2, String str3, String str4, Rect rect, Bundle bundle, int i) throws RemoteException;

    void uncacheShortcuts(String str, String str2, List<String> list, UserHandle userHandle, int i) throws RemoteException;

    void unregisterShortcutChangeCallback(String str, IShortcutChangeCallback iShortcutChangeCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ILauncherApps {
        @Override // android.content.pm.ILauncherApps
        public void addOnAppsChangedListener(String callingPackage, IOnAppsChangedListener listener) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public void removeOnAppsChangedListener(IOnAppsChangedListener listener) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public ParceledListSlice getLauncherActivities(String callingPackage, String packageName, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public LauncherActivityInfoInternal resolveLauncherActivityInternal(String callingPackage, ComponentName component, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public void startSessionDetailsActivityAsUser(IApplicationThread caller, String callingPackage, String callingFeatureId, PackageInstaller.SessionInfo sessionInfo, Rect sourceBounds, Bundle opts, UserHandle user) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public void startActivityAsUser(IApplicationThread caller, String callingPackage, String callingFeatureId, ComponentName component, Rect sourceBounds, Bundle opts, UserHandle user) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public PendingIntent getActivityLaunchIntent(ComponentName component, Bundle opts, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public void showAppDetailsAsUser(IApplicationThread caller, String callingPackage, String callingFeatureId, ComponentName component, Rect sourceBounds, Bundle opts, UserHandle user) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public boolean isPackageEnabled(String callingPackage, String packageName, UserHandle user) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ILauncherApps
        public Bundle getSuspendedPackageLauncherExtras(String packageName, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public boolean isActivityEnabled(String callingPackage, ComponentName component, UserHandle user) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ILauncherApps
        public ApplicationInfo getApplicationInfo(String callingPackage, String packageName, int flags, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public LauncherApps.AppUsageLimit getAppUsageLimit(String callingPackage, String packageName, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public ParceledListSlice getShortcuts(String callingPackage, ShortcutQueryWrapper query, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public void pinShortcuts(String callingPackage, String packageName, List<String> shortcutIds, UserHandle user) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public boolean startShortcut(String callingPackage, String packageName, String featureId, String id, Rect sourceBounds, Bundle startActivityOptions, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ILauncherApps
        public int getShortcutIconResId(String callingPackage, String packageName, String id, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.ILauncherApps
        public ParcelFileDescriptor getShortcutIconFd(String callingPackage, String packageName, String id, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public boolean hasShortcutHostPermission(String callingPackage) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ILauncherApps
        public boolean shouldHideFromSuggestions(String packageName, UserHandle user) throws RemoteException {
            return false;
        }

        @Override // android.content.pm.ILauncherApps
        public ParceledListSlice getShortcutConfigActivities(String callingPackage, String packageName, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public IntentSender getShortcutConfigActivityIntent(String callingPackage, ComponentName component, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public PendingIntent getShortcutIntent(String callingPackage, String packageName, String shortcutId, Bundle opts, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public void registerPackageInstallerCallback(String callingPackage, IPackageInstallerCallback callback) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public ParceledListSlice getAllSessions(String callingPackage) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.ILauncherApps
        public void registerShortcutChangeCallback(String callingPackage, ShortcutQueryWrapper query, IShortcutChangeCallback callback) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public void unregisterShortcutChangeCallback(String callingPackage, IShortcutChangeCallback callback) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public void cacheShortcuts(String callingPackage, String packageName, List<String> shortcutIds, UserHandle user, int cacheFlags) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public void uncacheShortcuts(String callingPackage, String packageName, List<String> shortcutIds, UserHandle user, int cacheFlags) throws RemoteException {
        }

        @Override // android.content.pm.ILauncherApps
        public String getShortcutIconUri(String callingPackage, String packageName, String shortcutId, int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ILauncherApps {
        public static final String DESCRIPTOR = "android.content.pm.ILauncherApps";
        static final int TRANSACTION_addOnAppsChangedListener = 1;
        static final int TRANSACTION_cacheShortcuts = 28;
        static final int TRANSACTION_getActivityLaunchIntent = 7;
        static final int TRANSACTION_getAllSessions = 25;
        static final int TRANSACTION_getAppUsageLimit = 13;
        static final int TRANSACTION_getApplicationInfo = 12;
        static final int TRANSACTION_getLauncherActivities = 3;
        static final int TRANSACTION_getShortcutConfigActivities = 21;
        static final int TRANSACTION_getShortcutConfigActivityIntent = 22;
        static final int TRANSACTION_getShortcutIconFd = 18;
        static final int TRANSACTION_getShortcutIconResId = 17;
        static final int TRANSACTION_getShortcutIconUri = 30;
        static final int TRANSACTION_getShortcutIntent = 23;
        static final int TRANSACTION_getShortcuts = 14;
        static final int TRANSACTION_getSuspendedPackageLauncherExtras = 10;
        static final int TRANSACTION_hasShortcutHostPermission = 19;
        static final int TRANSACTION_isActivityEnabled = 11;
        static final int TRANSACTION_isPackageEnabled = 9;
        static final int TRANSACTION_pinShortcuts = 15;
        static final int TRANSACTION_registerPackageInstallerCallback = 24;
        static final int TRANSACTION_registerShortcutChangeCallback = 26;
        static final int TRANSACTION_removeOnAppsChangedListener = 2;
        static final int TRANSACTION_resolveLauncherActivityInternal = 4;
        static final int TRANSACTION_shouldHideFromSuggestions = 20;
        static final int TRANSACTION_showAppDetailsAsUser = 8;
        static final int TRANSACTION_startActivityAsUser = 6;
        static final int TRANSACTION_startSessionDetailsActivityAsUser = 5;
        static final int TRANSACTION_startShortcut = 16;
        static final int TRANSACTION_uncacheShortcuts = 29;
        static final int TRANSACTION_unregisterShortcutChangeCallback = 27;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILauncherApps asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILauncherApps)) {
                return new Proxy(obj);
            }
            return (ILauncherApps) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addOnAppsChangedListener";
                case 2:
                    return "removeOnAppsChangedListener";
                case 3:
                    return "getLauncherActivities";
                case 4:
                    return "resolveLauncherActivityInternal";
                case 5:
                    return "startSessionDetailsActivityAsUser";
                case 6:
                    return "startActivityAsUser";
                case 7:
                    return "getActivityLaunchIntent";
                case 8:
                    return "showAppDetailsAsUser";
                case 9:
                    return "isPackageEnabled";
                case 10:
                    return "getSuspendedPackageLauncherExtras";
                case 11:
                    return "isActivityEnabled";
                case 12:
                    return "getApplicationInfo";
                case 13:
                    return "getAppUsageLimit";
                case 14:
                    return "getShortcuts";
                case 15:
                    return "pinShortcuts";
                case 16:
                    return "startShortcut";
                case 17:
                    return "getShortcutIconResId";
                case 18:
                    return "getShortcutIconFd";
                case 19:
                    return "hasShortcutHostPermission";
                case 20:
                    return "shouldHideFromSuggestions";
                case 21:
                    return "getShortcutConfigActivities";
                case 22:
                    return "getShortcutConfigActivityIntent";
                case 23:
                    return "getShortcutIntent";
                case 24:
                    return "registerPackageInstallerCallback";
                case 25:
                    return "getAllSessions";
                case 26:
                    return "registerShortcutChangeCallback";
                case 27:
                    return "unregisterShortcutChangeCallback";
                case 28:
                    return "cacheShortcuts";
                case 29:
                    return "uncacheShortcuts";
                case 30:
                    return "getShortcutIconUri";
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
            UserHandle _arg2;
            ComponentName _arg1;
            UserHandle _arg22;
            PackageInstaller.SessionInfo _arg3;
            Rect _arg4;
            Bundle _arg5;
            UserHandle _arg6;
            ComponentName _arg32;
            Rect _arg42;
            Bundle _arg52;
            UserHandle _arg62;
            ComponentName _arg0;
            Bundle _arg12;
            UserHandle _arg23;
            ComponentName _arg33;
            Rect _arg43;
            Bundle _arg53;
            UserHandle _arg63;
            UserHandle _arg24;
            UserHandle _arg13;
            ComponentName _arg14;
            UserHandle _arg25;
            UserHandle _arg34;
            UserHandle _arg26;
            ShortcutQueryWrapper _arg15;
            UserHandle _arg27;
            UserHandle _arg35;
            Rect _arg44;
            Bundle _arg54;
            UserHandle _arg16;
            UserHandle _arg28;
            ComponentName _arg17;
            UserHandle _arg29;
            Bundle _arg36;
            UserHandle _arg45;
            ShortcutQueryWrapper _arg18;
            UserHandle _arg37;
            UserHandle _arg38;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            IOnAppsChangedListener _arg19 = IOnAppsChangedListener.Stub.asInterface(data.readStrongBinder());
                            addOnAppsChangedListener(_arg02, _arg19);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IOnAppsChangedListener _arg03 = IOnAppsChangedListener.Stub.asInterface(data.readStrongBinder());
                            removeOnAppsChangedListener(_arg03);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            String _arg110 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            ParceledListSlice _result = getLauncherActivities(_arg04, _arg110, _arg2);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            LauncherActivityInfoInternal _result2 = resolveLauncherActivityInternal(_arg05, _arg1, _arg22);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            IApplicationThread _arg06 = IApplicationThread.Stub.asInterface(data.readStrongBinder());
                            String _arg111 = data.readString();
                            String _arg210 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = PackageInstaller.SessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg6 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            startSessionDetailsActivityAsUser(_arg06, _arg111, _arg210, _arg3, _arg4, _arg5, _arg6);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            IApplicationThread _arg07 = IApplicationThread.Stub.asInterface(data.readStrongBinder());
                            String _arg112 = data.readString();
                            String _arg211 = data.readString();
                            if (data.readInt() != 0) {
                                _arg32 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg42 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg52 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg52 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg62 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg62 = null;
                            }
                            startActivityAsUser(_arg07, _arg112, _arg211, _arg32, _arg42, _arg52, _arg62);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg23 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            PendingIntent _result3 = getActivityLaunchIntent(_arg0, _arg12, _arg23);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            IApplicationThread _arg08 = IApplicationThread.Stub.asInterface(data.readStrongBinder());
                            String _arg113 = data.readString();
                            String _arg212 = data.readString();
                            if (data.readInt() != 0) {
                                _arg33 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg43 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg43 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg53 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg53 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg63 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg63 = null;
                            }
                            showAppDetailsAsUser(_arg08, _arg113, _arg212, _arg33, _arg43, _arg53, _arg63);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _arg114 = data.readString();
                            if (data.readInt() != 0) {
                                _arg24 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            boolean isPackageEnabled = isPackageEnabled(_arg09, _arg114, _arg24);
                            reply.writeNoException();
                            reply.writeInt(isPackageEnabled ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            Bundle _result4 = getSuspendedPackageLauncherExtras(_arg010, _arg13);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg25 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            boolean isActivityEnabled = isActivityEnabled(_arg011, _arg14, _arg25);
                            reply.writeNoException();
                            reply.writeInt(isActivityEnabled ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            String _arg115 = data.readString();
                            int _arg213 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg34 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg34 = null;
                            }
                            ApplicationInfo _result5 = getApplicationInfo(_arg012, _arg115, _arg213, _arg34);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            String _arg116 = data.readString();
                            if (data.readInt() != 0) {
                                _arg26 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg26 = null;
                            }
                            LauncherApps.AppUsageLimit _result6 = getAppUsageLimit(_arg013, _arg116, _arg26);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = ShortcutQueryWrapper.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg27 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg27 = null;
                            }
                            ParceledListSlice _result7 = getShortcuts(_arg014, _arg15, _arg27);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            String _arg117 = data.readString();
                            List<String> _arg214 = data.createStringArrayList();
                            if (data.readInt() != 0) {
                                _arg35 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg35 = null;
                            }
                            pinShortcuts(_arg015, _arg117, _arg214, _arg35);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            String _arg118 = data.readString();
                            String _arg215 = data.readString();
                            String _arg39 = data.readString();
                            if (data.readInt() != 0) {
                                _arg44 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg44 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg54 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg54 = null;
                            }
                            int _arg64 = data.readInt();
                            boolean startShortcut = startShortcut(_arg016, _arg118, _arg215, _arg39, _arg44, _arg54, _arg64);
                            reply.writeNoException();
                            reply.writeInt(startShortcut ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            String _arg119 = data.readString();
                            String _arg216 = data.readString();
                            int _arg310 = data.readInt();
                            int _result8 = getShortcutIconResId(_arg017, _arg119, _arg216, _arg310);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            String _arg120 = data.readString();
                            String _arg217 = data.readString();
                            int _arg311 = data.readInt();
                            ParcelFileDescriptor _result9 = getShortcutIconFd(_arg018, _arg120, _arg217, _arg311);
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            boolean hasShortcutHostPermission = hasShortcutHostPermission(_arg019);
                            reply.writeNoException();
                            reply.writeInt(hasShortcutHostPermission ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            if (data.readInt() != 0) {
                                _arg16 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            boolean shouldHideFromSuggestions = shouldHideFromSuggestions(_arg020, _arg16);
                            reply.writeNoException();
                            reply.writeInt(shouldHideFromSuggestions ? 1 : 0);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            String _arg121 = data.readString();
                            if (data.readInt() != 0) {
                                _arg28 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg28 = null;
                            }
                            ParceledListSlice _result10 = getShortcutConfigActivities(_arg021, _arg121, _arg28);
                            reply.writeNoException();
                            if (_result10 != null) {
                                reply.writeInt(1);
                                _result10.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg29 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg29 = null;
                            }
                            IntentSender _result11 = getShortcutConfigActivityIntent(_arg022, _arg17, _arg29);
                            reply.writeNoException();
                            if (_result11 != null) {
                                reply.writeInt(1);
                                _result11.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            String _arg122 = data.readString();
                            String _arg218 = data.readString();
                            if (data.readInt() != 0) {
                                _arg36 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg36 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg45 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg45 = null;
                            }
                            PendingIntent _result12 = getShortcutIntent(_arg023, _arg122, _arg218, _arg36, _arg45);
                            reply.writeNoException();
                            if (_result12 != null) {
                                reply.writeInt(1);
                                _result12.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            IPackageInstallerCallback _arg123 = IPackageInstallerCallback.Stub.asInterface(data.readStrongBinder());
                            registerPackageInstallerCallback(_arg024, _arg123);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg025 = data.readString();
                            ParceledListSlice _result13 = getAllSessions(_arg025);
                            reply.writeNoException();
                            if (_result13 != null) {
                                reply.writeInt(1);
                                _result13.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg026 = data.readString();
                            if (data.readInt() != 0) {
                                _arg18 = ShortcutQueryWrapper.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            IShortcutChangeCallback _arg219 = IShortcutChangeCallback.Stub.asInterface(data.readStrongBinder());
                            registerShortcutChangeCallback(_arg026, _arg18, _arg219);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg027 = data.readString();
                            IShortcutChangeCallback _arg124 = IShortcutChangeCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterShortcutChangeCallback(_arg027, _arg124);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            String _arg125 = data.readString();
                            List<String> _arg220 = data.createStringArrayList();
                            if (data.readInt() != 0) {
                                _arg37 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg37 = null;
                            }
                            int _arg46 = data.readInt();
                            cacheShortcuts(_arg028, _arg125, _arg220, _arg37, _arg46);
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg029 = data.readString();
                            String _arg126 = data.readString();
                            List<String> _arg221 = data.createStringArrayList();
                            if (data.readInt() != 0) {
                                _arg38 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg38 = null;
                            }
                            int _arg47 = data.readInt();
                            uncacheShortcuts(_arg029, _arg126, _arg221, _arg38, _arg47);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            String _arg127 = data.readString();
                            String _arg222 = data.readString();
                            int _arg312 = data.readInt();
                            String _result14 = getShortcutIconUri(_arg030, _arg127, _arg222, _arg312);
                            reply.writeNoException();
                            reply.writeString(_result14);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ILauncherApps {
            public static ILauncherApps sDefaultImpl;
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

            @Override // android.content.pm.ILauncherApps
            public void addOnAppsChangedListener(String callingPackage, IOnAppsChangedListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addOnAppsChangedListener(callingPackage, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void removeOnAppsChangedListener(IOnAppsChangedListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeOnAppsChangedListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public ParceledListSlice getLauncherActivities(String callingPackage, String packageName, UserHandle user) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLauncherActivities(callingPackage, packageName, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public LauncherActivityInfoInternal resolveLauncherActivityInternal(String callingPackage, ComponentName component, UserHandle user) throws RemoteException {
                LauncherActivityInfoInternal _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resolveLauncherActivityInternal(callingPackage, component, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LauncherActivityInfoInternal.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void startSessionDetailsActivityAsUser(IApplicationThread caller, String callingPackage, String callingFeatureId, PackageInstaller.SessionInfo sessionInfo, Rect sourceBounds, Bundle opts, UserHandle user) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(caller != null ? caller.asBinder() : null);
                    try {
                        _data.writeString(callingPackage);
                        _data.writeString(callingFeatureId);
                        if (sessionInfo != null) {
                            _data.writeInt(1);
                            sessionInfo.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (sourceBounds != null) {
                            _data.writeInt(1);
                            sourceBounds.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (opts != null) {
                            _data.writeInt(1);
                            opts.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (user != null) {
                            _data.writeInt(1);
                            user.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().startSessionDetailsActivityAsUser(caller, callingPackage, callingFeatureId, sessionInfo, sourceBounds, opts, user);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void startActivityAsUser(IApplicationThread caller, String callingPackage, String callingFeatureId, ComponentName component, Rect sourceBounds, Bundle opts, UserHandle user) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(caller != null ? caller.asBinder() : null);
                    try {
                        _data.writeString(callingPackage);
                        _data.writeString(callingFeatureId);
                        if (component != null) {
                            _data.writeInt(1);
                            component.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (sourceBounds != null) {
                            _data.writeInt(1);
                            sourceBounds.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (opts != null) {
                            _data.writeInt(1);
                            opts.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (user != null) {
                            _data.writeInt(1);
                            user.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().startActivityAsUser(caller, callingPackage, callingFeatureId, component, sourceBounds, opts, user);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            @Override // android.content.pm.ILauncherApps
            public PendingIntent getActivityLaunchIntent(ComponentName component, Bundle opts, UserHandle user) throws RemoteException {
                PendingIntent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (opts != null) {
                        _data.writeInt(1);
                        opts.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivityLaunchIntent(component, opts, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PendingIntent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void showAppDetailsAsUser(IApplicationThread caller, String callingPackage, String callingFeatureId, ComponentName component, Rect sourceBounds, Bundle opts, UserHandle user) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(caller != null ? caller.asBinder() : null);
                    try {
                        _data.writeString(callingPackage);
                        _data.writeString(callingFeatureId);
                        if (component != null) {
                            _data.writeInt(1);
                            component.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (sourceBounds != null) {
                            _data.writeInt(1);
                            sourceBounds.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (opts != null) {
                            _data.writeInt(1);
                            opts.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (user != null) {
                            _data.writeInt(1);
                            user.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().showAppDetailsAsUser(caller, callingPackage, callingFeatureId, component, sourceBounds, opts, user);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }

            @Override // android.content.pm.ILauncherApps
            public boolean isPackageEnabled(String callingPackage, String packageName, UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    boolean _result = true;
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageEnabled(callingPackage, packageName, user);
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

            @Override // android.content.pm.ILauncherApps
            public Bundle getSuspendedPackageLauncherExtras(String packageName, UserHandle user) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSuspendedPackageLauncherExtras(packageName, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public boolean isActivityEnabled(String callingPackage, ComponentName component, UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = true;
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isActivityEnabled(callingPackage, component, user);
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

            @Override // android.content.pm.ILauncherApps
            public ApplicationInfo getApplicationInfo(String callingPackage, String packageName, int flags, UserHandle user) throws RemoteException {
                ApplicationInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeInt(flags);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApplicationInfo(callingPackage, packageName, flags, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ApplicationInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public LauncherApps.AppUsageLimit getAppUsageLimit(String callingPackage, String packageName, UserHandle user) throws RemoteException {
                LauncherApps.AppUsageLimit _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppUsageLimit(callingPackage, packageName, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LauncherApps.AppUsageLimit.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public ParceledListSlice getShortcuts(String callingPackage, ShortcutQueryWrapper query, UserHandle user) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    if (query != null) {
                        _data.writeInt(1);
                        query.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcuts(callingPackage, query, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void pinShortcuts(String callingPackage, String packageName, List<String> shortcutIds, UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeStringList(shortcutIds);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().pinShortcuts(callingPackage, packageName, shortcutIds, user);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public boolean startShortcut(String callingPackage, String packageName, String featureId, String id, Rect sourceBounds, Bundle startActivityOptions, int userId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(callingPackage);
                        try {
                            _data.writeString(packageName);
                            try {
                                _data.writeString(featureId);
                                _data.writeString(id);
                                boolean _result = true;
                                if (sourceBounds != null) {
                                    _data.writeInt(1);
                                    sourceBounds.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                if (startActivityOptions != null) {
                                    _data.writeInt(1);
                                    startActivityOptions.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                _data.writeInt(userId);
                                boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() == 0) {
                                        _result = false;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                boolean startShortcut = Stub.getDefaultImpl().startShortcut(callingPackage, packageName, featureId, id, sourceBounds, startActivityOptions, userId);
                                _reply.recycle();
                                _data.recycle();
                                return startShortcut;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.content.pm.ILauncherApps
            public int getShortcutIconResId(String callingPackage, String packageName, String id, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeString(id);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcutIconResId(callingPackage, packageName, id, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public ParcelFileDescriptor getShortcutIconFd(String callingPackage, String packageName, String id, int userId) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeString(id);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcutIconFd(callingPackage, packageName, id, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public boolean hasShortcutHostPermission(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasShortcutHostPermission(callingPackage);
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

            @Override // android.content.pm.ILauncherApps
            public boolean shouldHideFromSuggestions(String packageName, UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = true;
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldHideFromSuggestions(packageName, user);
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

            @Override // android.content.pm.ILauncherApps
            public ParceledListSlice getShortcutConfigActivities(String callingPackage, String packageName, UserHandle user) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcutConfigActivities(callingPackage, packageName, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public IntentSender getShortcutConfigActivityIntent(String callingPackage, ComponentName component, UserHandle user) throws RemoteException {
                IntentSender _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcutConfigActivityIntent(callingPackage, component, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IntentSender.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public PendingIntent getShortcutIntent(String callingPackage, String packageName, String shortcutId, Bundle opts, UserHandle user) throws RemoteException {
                PendingIntent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeString(shortcutId);
                    if (opts != null) {
                        _data.writeInt(1);
                        opts.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcutIntent(callingPackage, packageName, shortcutId, opts, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PendingIntent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void registerPackageInstallerCallback(String callingPackage, IPackageInstallerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerPackageInstallerCallback(callingPackage, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public ParceledListSlice getAllSessions(String callingPackage) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllSessions(callingPackage);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void registerShortcutChangeCallback(String callingPackage, ShortcutQueryWrapper query, IShortcutChangeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    if (query != null) {
                        _data.writeInt(1);
                        query.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerShortcutChangeCallback(callingPackage, query, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void unregisterShortcutChangeCallback(String callingPackage, IShortcutChangeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterShortcutChangeCallback(callingPackage, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void cacheShortcuts(String callingPackage, String packageName, List<String> shortcutIds, UserHandle user, int cacheFlags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeStringList(shortcutIds);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(cacheFlags);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cacheShortcuts(callingPackage, packageName, shortcutIds, user, cacheFlags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public void uncacheShortcuts(String callingPackage, String packageName, List<String> shortcutIds, UserHandle user, int cacheFlags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeStringList(shortcutIds);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(cacheFlags);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().uncacheShortcuts(callingPackage, packageName, shortcutIds, user, cacheFlags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.ILauncherApps
            public String getShortcutIconUri(String callingPackage, String packageName, String shortcutId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(packageName);
                    _data.writeString(shortcutId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortcutIconUri(callingPackage, packageName, shortcutId, userId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILauncherApps impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILauncherApps getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
