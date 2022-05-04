package com.android.internal.compat;

import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.compat.IOverrideValidator;
/* loaded from: classes4.dex */
public interface IPlatformCompat extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.compat.IPlatformCompat";

    boolean clearOverride(long j, String str) throws RemoteException;

    boolean clearOverrideForTest(long j, String str) throws RemoteException;

    void clearOverrides(String str) throws RemoteException;

    void clearOverridesForTest(String str) throws RemoteException;

    int disableTargetSdkChanges(String str, int i) throws RemoteException;

    int enableTargetSdkChanges(String str, int i) throws RemoteException;

    CompatibilityChangeConfig getAppConfig(ApplicationInfo applicationInfo) throws RemoteException;

    IOverrideValidator getOverrideValidator() throws RemoteException;

    boolean isChangeEnabled(long j, ApplicationInfo applicationInfo) throws RemoteException;

    boolean isChangeEnabledByPackageName(long j, String str, int i) throws RemoteException;

    boolean isChangeEnabledByUid(long j, int i) throws RemoteException;

    CompatibilityChangeInfo[] listAllChanges() throws RemoteException;

    CompatibilityChangeInfo[] listUIChanges() throws RemoteException;

    void putOverridesOnReleaseBuilds(CompatibilityOverrideConfig compatibilityOverrideConfig, String str) throws RemoteException;

    void removeOverridesOnReleaseBuilds(CompatibilityOverridesToRemoveConfig compatibilityOverridesToRemoveConfig, String str) throws RemoteException;

    void reportChange(long j, ApplicationInfo applicationInfo) throws RemoteException;

    void reportChangeByPackageName(long j, String str, int i) throws RemoteException;

    void reportChangeByUid(long j, int i) throws RemoteException;

    void setOverrides(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException;

    void setOverridesForTest(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IPlatformCompat {
        @Override // com.android.internal.compat.IPlatformCompat
        public void reportChange(long changeId, ApplicationInfo appInfo) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void reportChangeByPackageName(long changeId, String packageName, int userId) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void reportChangeByUid(long changeId, int uid) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean isChangeEnabled(long changeId, ApplicationInfo appInfo) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean isChangeEnabledByPackageName(long changeId, String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean isChangeEnabledByUid(long changeId, int uid) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void setOverrides(CompatibilityChangeConfig overrides, String packageName) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void putOverridesOnReleaseBuilds(CompatibilityOverrideConfig overrides, String packageName) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void setOverridesForTest(CompatibilityChangeConfig overrides, String packageName) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean clearOverride(long changeId, String packageName) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean clearOverrideForTest(long changeId, String packageName) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void removeOverridesOnReleaseBuilds(CompatibilityOverridesToRemoveConfig overridesToRemove, String packageName) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public int enableTargetSdkChanges(String packageName, int targetSdkVersion) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public int disableTargetSdkChanges(String packageName, int targetSdkVersion) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void clearOverrides(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void clearOverridesForTest(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public CompatibilityChangeConfig getAppConfig(ApplicationInfo appInfo) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public CompatibilityChangeInfo[] listAllChanges() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public CompatibilityChangeInfo[] listUIChanges() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public IOverrideValidator getOverrideValidator() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IPlatformCompat {
        static final int TRANSACTION_clearOverride = 10;
        static final int TRANSACTION_clearOverrideForTest = 11;
        static final int TRANSACTION_clearOverrides = 15;
        static final int TRANSACTION_clearOverridesForTest = 16;
        static final int TRANSACTION_disableTargetSdkChanges = 14;
        static final int TRANSACTION_enableTargetSdkChanges = 13;
        static final int TRANSACTION_getAppConfig = 17;
        static final int TRANSACTION_getOverrideValidator = 20;
        static final int TRANSACTION_isChangeEnabled = 4;
        static final int TRANSACTION_isChangeEnabledByPackageName = 5;
        static final int TRANSACTION_isChangeEnabledByUid = 6;
        static final int TRANSACTION_listAllChanges = 18;
        static final int TRANSACTION_listUIChanges = 19;
        static final int TRANSACTION_putOverridesOnReleaseBuilds = 8;
        static final int TRANSACTION_removeOverridesOnReleaseBuilds = 12;
        static final int TRANSACTION_reportChange = 1;
        static final int TRANSACTION_reportChangeByPackageName = 2;
        static final int TRANSACTION_reportChangeByUid = 3;
        static final int TRANSACTION_setOverrides = 7;
        static final int TRANSACTION_setOverridesForTest = 9;

        public Stub() {
            attachInterface(this, IPlatformCompat.DESCRIPTOR);
        }

        public static IPlatformCompat asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPlatformCompat.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPlatformCompat)) {
                return new Proxy(obj);
            }
            return (IPlatformCompat) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "reportChange";
                case 2:
                    return "reportChangeByPackageName";
                case 3:
                    return "reportChangeByUid";
                case 4:
                    return "isChangeEnabled";
                case 5:
                    return "isChangeEnabledByPackageName";
                case 6:
                    return "isChangeEnabledByUid";
                case 7:
                    return "setOverrides";
                case 8:
                    return "putOverridesOnReleaseBuilds";
                case 9:
                    return "setOverridesForTest";
                case 10:
                    return "clearOverride";
                case 11:
                    return "clearOverrideForTest";
                case 12:
                    return "removeOverridesOnReleaseBuilds";
                case 13:
                    return "enableTargetSdkChanges";
                case 14:
                    return "disableTargetSdkChanges";
                case 15:
                    return "clearOverrides";
                case 16:
                    return "clearOverridesForTest";
                case 17:
                    return "getAppConfig";
                case 18:
                    return "listAllChanges";
                case 19:
                    return "listUIChanges";
                case 20:
                    return "getOverrideValidator";
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
            ApplicationInfo _arg1;
            ApplicationInfo _arg12;
            CompatibilityChangeConfig _arg0;
            CompatibilityOverrideConfig _arg02;
            CompatibilityChangeConfig _arg03;
            CompatibilityOverridesToRemoveConfig _arg04;
            ApplicationInfo _arg05;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPlatformCompat.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg06 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg1 = ApplicationInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            reportChange(_arg06, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg07 = data.readLong();
                            String _arg13 = data.readString();
                            int _arg2 = data.readInt();
                            reportChangeByPackageName(_arg07, _arg13, _arg2);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg08 = data.readLong();
                            int _arg14 = data.readInt();
                            reportChangeByUid(_arg08, _arg14);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg09 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg12 = ApplicationInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            boolean isChangeEnabled = isChangeEnabled(_arg09, _arg12);
                            reply.writeNoException();
                            reply.writeInt(isChangeEnabled ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg010 = data.readLong();
                            String _arg15 = data.readString();
                            int _arg22 = data.readInt();
                            boolean isChangeEnabledByPackageName = isChangeEnabledByPackageName(_arg010, _arg15, _arg22);
                            reply.writeNoException();
                            reply.writeInt(isChangeEnabledByPackageName ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg011 = data.readLong();
                            int _arg16 = data.readInt();
                            boolean isChangeEnabledByUid = isChangeEnabledByUid(_arg011, _arg16);
                            reply.writeNoException();
                            reply.writeInt(isChangeEnabledByUid ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = CompatibilityChangeConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg17 = data.readString();
                            setOverrides(_arg0, _arg17);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = CompatibilityOverrideConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _arg18 = data.readString();
                            putOverridesOnReleaseBuilds(_arg02, _arg18);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = CompatibilityChangeConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            String _arg19 = data.readString();
                            setOverridesForTest(_arg03, _arg19);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg012 = data.readLong();
                            String _arg110 = data.readString();
                            boolean clearOverride = clearOverride(_arg012, _arg110);
                            reply.writeNoException();
                            reply.writeInt(clearOverride ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            long _arg013 = data.readLong();
                            String _arg111 = data.readString();
                            boolean clearOverrideForTest = clearOverrideForTest(_arg013, _arg111);
                            reply.writeNoException();
                            reply.writeInt(clearOverrideForTest ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = CompatibilityOverridesToRemoveConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            String _arg112 = data.readString();
                            removeOverridesOnReleaseBuilds(_arg04, _arg112);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            String _arg014 = data.readString();
                            int _arg113 = data.readInt();
                            int _result = enableTargetSdkChanges(_arg014, _arg113);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 14:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            String _arg015 = data.readString();
                            int _arg114 = data.readInt();
                            int _result2 = disableTargetSdkChanges(_arg015, _arg114);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 15:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            String _arg016 = data.readString();
                            clearOverrides(_arg016);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            String _arg017 = data.readString();
                            clearOverridesForTest(_arg017);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = ApplicationInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            CompatibilityChangeConfig _result3 = getAppConfig(_arg05);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 18:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            CompatibilityChangeInfo[] _result4 = listAllChanges();
                            reply.writeNoException();
                            reply.writeTypedArray(_result4, 1);
                            return true;
                        case 19:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            CompatibilityChangeInfo[] _result5 = listUIChanges();
                            reply.writeNoException();
                            reply.writeTypedArray(_result5, 1);
                            return true;
                        case 20:
                            data.enforceInterface(IPlatformCompat.DESCRIPTOR);
                            IOverrideValidator _result6 = getOverrideValidator();
                            reply.writeNoException();
                            reply.writeStrongBinder(_result6 != null ? _result6.asBinder() : null);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IPlatformCompat {
            public static IPlatformCompat sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPlatformCompat.DESCRIPTOR;
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void reportChange(long changeId, ApplicationInfo appInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    if (appInfo != null) {
                        _data.writeInt(1);
                        appInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportChange(changeId, appInfo);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void reportChangeByPackageName(long changeId, String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportChangeByPackageName(changeId, packageName, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void reportChangeByUid(long changeId, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportChangeByUid(changeId, uid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean isChangeEnabled(long changeId, ApplicationInfo appInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    boolean _result = true;
                    if (appInfo != null) {
                        _data.writeInt(1);
                        appInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabled(changeId, appInfo);
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

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean isChangeEnabledByPackageName(long changeId, String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabledByPackageName(changeId, packageName, userId);
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

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean isChangeEnabledByUid(long changeId, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabledByUid(changeId, uid);
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

            @Override // com.android.internal.compat.IPlatformCompat
            public void setOverrides(CompatibilityChangeConfig overrides, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    if (overrides != null) {
                        _data.writeInt(1);
                        overrides.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setOverrides(overrides, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void putOverridesOnReleaseBuilds(CompatibilityOverrideConfig overrides, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    if (overrides != null) {
                        _data.writeInt(1);
                        overrides.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().putOverridesOnReleaseBuilds(overrides, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void setOverridesForTest(CompatibilityChangeConfig overrides, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    if (overrides != null) {
                        _data.writeInt(1);
                        overrides.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setOverridesForTest(overrides, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean clearOverride(long changeId, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearOverride(changeId, packageName);
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

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean clearOverrideForTest(long changeId, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeLong(changeId);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearOverrideForTest(changeId, packageName);
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

            @Override // com.android.internal.compat.IPlatformCompat
            public void removeOverridesOnReleaseBuilds(CompatibilityOverridesToRemoveConfig overridesToRemove, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    if (overridesToRemove != null) {
                        _data.writeInt(1);
                        overridesToRemove.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeOverridesOnReleaseBuilds(overridesToRemove, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public int enableTargetSdkChanges(String packageName, int targetSdkVersion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(targetSdkVersion);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableTargetSdkChanges(packageName, targetSdkVersion);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public int disableTargetSdkChanges(String packageName, int targetSdkVersion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(targetSdkVersion);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableTargetSdkChanges(packageName, targetSdkVersion);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void clearOverrides(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearOverrides(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void clearOverridesForTest(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearOverridesForTest(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public CompatibilityChangeConfig getAppConfig(ApplicationInfo appInfo) throws RemoteException {
                CompatibilityChangeConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    if (appInfo != null) {
                        _data.writeInt(1);
                        appInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppConfig(appInfo);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CompatibilityChangeConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public CompatibilityChangeInfo[] listAllChanges() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listAllChanges();
                    }
                    _reply.readException();
                    CompatibilityChangeInfo[] _result = (CompatibilityChangeInfo[]) _reply.createTypedArray(CompatibilityChangeInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public CompatibilityChangeInfo[] listUIChanges() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listUIChanges();
                    }
                    _reply.readException();
                    CompatibilityChangeInfo[] _result = (CompatibilityChangeInfo[]) _reply.createTypedArray(CompatibilityChangeInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public IOverrideValidator getOverrideValidator() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPlatformCompat.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverrideValidator();
                    }
                    _reply.readException();
                    IOverrideValidator _result = IOverrideValidator.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlatformCompat impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPlatformCompat getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
