package android.content.pm;

import android.content.IntentSender;
import android.content.pm.IPackageInstallerCallback;
import android.content.pm.IPackageInstallerSession;
import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IPackageInstaller extends IInterface {
    void abandonSession(int i) throws RemoteException;

    void bypassNextAllowedApexUpdateCheck(boolean z) throws RemoteException;

    void bypassNextStagedInstallerCheck(boolean z) throws RemoteException;

    int createSession(PackageInstaller.SessionParams sessionParams, String str, String str2, int i) throws RemoteException;

    ParceledListSlice getAllSessions(int i) throws RemoteException;

    ParceledListSlice getMySessions(String str, int i) throws RemoteException;

    PackageInstaller.SessionInfo getSessionInfo(int i) throws RemoteException;

    ParceledListSlice getStagedSessions() throws RemoteException;

    void installExistingPackage(String str, int i, int i2, IntentSender intentSender, int i3, List<String> list) throws RemoteException;

    IPackageInstallerSession openSession(int i) throws RemoteException;

    void registerCallback(IPackageInstallerCallback iPackageInstallerCallback, int i) throws RemoteException;

    void setAllowUnlimitedSilentUpdates(String str) throws RemoteException;

    void setPermissionsResult(int i, boolean z) throws RemoteException;

    void setSilentUpdatesThrottleTime(long j) throws RemoteException;

    void uninstall(VersionedPackage versionedPackage, String str, int i, IntentSender intentSender, int i2) throws RemoteException;

    void uninstallExistingPackage(VersionedPackage versionedPackage, String str, IntentSender intentSender, int i) throws RemoteException;

    void unregisterCallback(IPackageInstallerCallback iPackageInstallerCallback) throws RemoteException;

    void updateSessionAppIcon(int i, Bitmap bitmap) throws RemoteException;

    void updateSessionAppLabel(int i, String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPackageInstaller {
        @Override // android.content.pm.IPackageInstaller
        public int createSession(PackageInstaller.SessionParams params, String installerPackageName, String installerAttributionTag, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageInstaller
        public void updateSessionAppIcon(int sessionId, Bitmap appIcon) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void updateSessionAppLabel(int sessionId, String appLabel) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void abandonSession(int sessionId) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public IPackageInstallerSession openSession(int sessionId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageInstaller
        public PackageInstaller.SessionInfo getSessionInfo(int sessionId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageInstaller
        public ParceledListSlice getAllSessions(int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageInstaller
        public ParceledListSlice getMySessions(String installerPackageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageInstaller
        public ParceledListSlice getStagedSessions() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageInstaller
        public void registerCallback(IPackageInstallerCallback callback, int userId) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void unregisterCallback(IPackageInstallerCallback callback) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void uninstall(VersionedPackage versionedPackage, String callerPackageName, int flags, IntentSender statusReceiver, int userId) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void uninstallExistingPackage(VersionedPackage versionedPackage, String callerPackageName, IntentSender statusReceiver, int userId) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void installExistingPackage(String packageName, int installFlags, int installReason, IntentSender statusReceiver, int userId, List<String> whiteListedPermissions) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void setPermissionsResult(int sessionId, boolean accepted) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void bypassNextStagedInstallerCheck(boolean value) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void bypassNextAllowedApexUpdateCheck(boolean value) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void setAllowUnlimitedSilentUpdates(String installerPackageName) throws RemoteException {
        }

        @Override // android.content.pm.IPackageInstaller
        public void setSilentUpdatesThrottleTime(long throttleTimeInSeconds) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPackageInstaller {
        public static final String DESCRIPTOR = "android.content.pm.IPackageInstaller";
        static final int TRANSACTION_abandonSession = 4;
        static final int TRANSACTION_bypassNextAllowedApexUpdateCheck = 17;
        static final int TRANSACTION_bypassNextStagedInstallerCheck = 16;
        static final int TRANSACTION_createSession = 1;
        static final int TRANSACTION_getAllSessions = 7;
        static final int TRANSACTION_getMySessions = 8;
        static final int TRANSACTION_getSessionInfo = 6;
        static final int TRANSACTION_getStagedSessions = 9;
        static final int TRANSACTION_installExistingPackage = 14;
        static final int TRANSACTION_openSession = 5;
        static final int TRANSACTION_registerCallback = 10;
        static final int TRANSACTION_setAllowUnlimitedSilentUpdates = 18;
        static final int TRANSACTION_setPermissionsResult = 15;
        static final int TRANSACTION_setSilentUpdatesThrottleTime = 19;
        static final int TRANSACTION_uninstall = 12;
        static final int TRANSACTION_uninstallExistingPackage = 13;
        static final int TRANSACTION_unregisterCallback = 11;
        static final int TRANSACTION_updateSessionAppIcon = 2;
        static final int TRANSACTION_updateSessionAppLabel = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPackageInstaller asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPackageInstaller)) {
                return new Proxy(obj);
            }
            return (IPackageInstaller) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createSession";
                case 2:
                    return "updateSessionAppIcon";
                case 3:
                    return "updateSessionAppLabel";
                case 4:
                    return "abandonSession";
                case 5:
                    return "openSession";
                case 6:
                    return "getSessionInfo";
                case 7:
                    return "getAllSessions";
                case 8:
                    return "getMySessions";
                case 9:
                    return "getStagedSessions";
                case 10:
                    return "registerCallback";
                case 11:
                    return "unregisterCallback";
                case 12:
                    return "uninstall";
                case 13:
                    return "uninstallExistingPackage";
                case 14:
                    return "installExistingPackage";
                case 15:
                    return "setPermissionsResult";
                case 16:
                    return "bypassNextStagedInstallerCheck";
                case 17:
                    return "bypassNextAllowedApexUpdateCheck";
                case 18:
                    return "setAllowUnlimitedSilentUpdates";
                case 19:
                    return "setSilentUpdatesThrottleTime";
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
            PackageInstaller.SessionParams _arg0;
            Bitmap _arg1;
            VersionedPackage _arg02;
            IntentSender _arg3;
            VersionedPackage _arg03;
            IntentSender _arg2;
            IntentSender _arg32;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg04 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PackageInstaller.SessionParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg12 = data.readString();
                            String _arg22 = data.readString();
                            int _arg33 = data.readInt();
                            int _result = createSession(_arg0, _arg12, _arg22, _arg33);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Bitmap.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            updateSessionAppIcon(_arg05, _arg1);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            String _arg13 = data.readString();
                            updateSessionAppLabel(_arg06, _arg13);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            abandonSession(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            IPackageInstallerSession _result2 = openSession(data.readInt());
                            reply.writeNoException();
                            reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            PackageInstaller.SessionInfo _result3 = getSessionInfo(data.readInt());
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            ParceledListSlice _result4 = getAllSessions(data.readInt());
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg14 = data.readInt();
                            ParceledListSlice _result5 = getMySessions(_arg07, _arg14);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            ParceledListSlice _result6 = getStagedSessions();
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            IPackageInstallerCallback _arg08 = IPackageInstallerCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg15 = data.readInt();
                            registerCallback(_arg08, _arg15);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            unregisterCallback(IPackageInstallerCallback.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = VersionedPackage.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _arg16 = data.readString();
                            int _arg23 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            int _arg4 = data.readInt();
                            uninstall(_arg02, _arg16, _arg23, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = VersionedPackage.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            String _arg17 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _arg34 = data.readInt();
                            uninstallExistingPackage(_arg03, _arg17, _arg2, _arg34);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            int _arg18 = data.readInt();
                            int _arg24 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg32 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            int _arg42 = data.readInt();
                            List<String> _arg5 = data.createStringArrayList();
                            installExistingPackage(_arg09, _arg18, _arg24, _arg32, _arg42, _arg5);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            setPermissionsResult(_arg010, _arg04);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            bypassNextStagedInstallerCheck(_arg04);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            bypassNextAllowedApexUpdateCheck(_arg04);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            setAllowUnlimitedSilentUpdates(data.readString());
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            setSilentUpdatesThrottleTime(data.readLong());
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPackageInstaller {
            public static IPackageInstaller sDefaultImpl;
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

            @Override // android.content.pm.IPackageInstaller
            public int createSession(PackageInstaller.SessionParams params, String installerPackageName, String installerAttributionTag, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(installerPackageName);
                    _data.writeString(installerAttributionTag);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createSession(params, installerPackageName, installerAttributionTag, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void updateSessionAppIcon(int sessionId, Bitmap appIcon) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    if (appIcon != null) {
                        _data.writeInt(1);
                        appIcon.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateSessionAppIcon(sessionId, appIcon);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void updateSessionAppLabel(int sessionId, String appLabel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeString(appLabel);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateSessionAppLabel(sessionId, appLabel);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void abandonSession(int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().abandonSession(sessionId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public IPackageInstallerSession openSession(int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openSession(sessionId);
                    }
                    _reply.readException();
                    IPackageInstallerSession _result = IPackageInstallerSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public PackageInstaller.SessionInfo getSessionInfo(int sessionId) throws RemoteException {
                PackageInstaller.SessionInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSessionInfo(sessionId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PackageInstaller.SessionInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public ParceledListSlice getAllSessions(int userId) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllSessions(userId);
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

            @Override // android.content.pm.IPackageInstaller
            public ParceledListSlice getMySessions(String installerPackageName, int userId) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(installerPackageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMySessions(installerPackageName, userId);
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

            @Override // android.content.pm.IPackageInstaller
            public ParceledListSlice getStagedSessions() throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStagedSessions();
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

            @Override // android.content.pm.IPackageInstaller
            public void registerCallback(IPackageInstallerCallback callback, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerCallback(callback, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void unregisterCallback(IPackageInstallerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void uninstall(VersionedPackage versionedPackage, String callerPackageName, int flags, IntentSender statusReceiver, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (versionedPackage != null) {
                        _data.writeInt(1);
                        versionedPackage.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callerPackageName);
                    _data.writeInt(flags);
                    if (statusReceiver != null) {
                        _data.writeInt(1);
                        statusReceiver.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().uninstall(versionedPackage, callerPackageName, flags, statusReceiver, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void uninstallExistingPackage(VersionedPackage versionedPackage, String callerPackageName, IntentSender statusReceiver, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (versionedPackage != null) {
                        _data.writeInt(1);
                        versionedPackage.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callerPackageName);
                    if (statusReceiver != null) {
                        _data.writeInt(1);
                        statusReceiver.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().uninstallExistingPackage(versionedPackage, callerPackageName, statusReceiver, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void installExistingPackage(String packageName, int installFlags, int installReason, IntentSender statusReceiver, int userId, List<String> whiteListedPermissions) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(packageName);
                        try {
                            _data.writeInt(installFlags);
                            try {
                                _data.writeInt(installReason);
                                if (statusReceiver != null) {
                                    _data.writeInt(1);
                                    statusReceiver.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
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
                try {
                    _data.writeInt(userId);
                    try {
                        _data.writeStringList(whiteListedPermissions);
                        boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().installExistingPackage(packageName, installFlags, installReason, statusReceiver, userId, whiteListedPermissions);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void setPermissionsResult(int sessionId, boolean accepted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeInt(accepted ? 1 : 0);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPermissionsResult(sessionId, accepted);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void bypassNextStagedInstallerCheck(boolean value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value ? 1 : 0);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().bypassNextStagedInstallerCheck(value);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void bypassNextAllowedApexUpdateCheck(boolean value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value ? 1 : 0);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().bypassNextAllowedApexUpdateCheck(value);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void setAllowUnlimitedSilentUpdates(String installerPackageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(installerPackageName);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAllowUnlimitedSilentUpdates(installerPackageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IPackageInstaller
            public void setSilentUpdatesThrottleTime(long throttleTimeInSeconds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(throttleTimeInSeconds);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSilentUpdatesThrottleTime(throttleTimeInSeconds);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPackageInstaller impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPackageInstaller getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
