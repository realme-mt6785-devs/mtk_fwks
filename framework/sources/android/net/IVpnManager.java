package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.android.internal.net.LegacyVpnInfo;
import com.android.internal.net.VpnConfig;
import com.android.internal.net.VpnProfile;
import java.util.List;
/* loaded from: classes2.dex */
public interface IVpnManager extends IInterface {
    public static final String DESCRIPTOR = "android.net.IVpnManager";

    boolean addVpnAddress(String str, int i) throws RemoteException;

    void deleteVpnProfile(String str) throws RemoteException;

    ParcelFileDescriptor establishVpn(VpnConfig vpnConfig) throws RemoteException;

    void factoryReset() throws RemoteException;

    String getAlwaysOnVpnPackage(int i) throws RemoteException;

    LegacyVpnInfo getLegacyVpnInfo(int i) throws RemoteException;

    VpnConfig getVpnConfig(int i) throws RemoteException;

    List<String> getVpnLockdownAllowlist(int i) throws RemoteException;

    boolean isAlwaysOnVpnPackageSupported(int i, String str) throws RemoteException;

    boolean isCallerCurrentAlwaysOnVpnApp() throws RemoteException;

    boolean isCallerCurrentAlwaysOnVpnLockdownApp() throws RemoteException;

    boolean isVpnLockdownEnabled(int i) throws RemoteException;

    boolean prepareVpn(String str, String str2, int i) throws RemoteException;

    boolean provisionVpnProfile(VpnProfile vpnProfile, String str) throws RemoteException;

    boolean removeVpnAddress(String str, int i) throws RemoteException;

    boolean setAlwaysOnVpnPackage(int i, String str, boolean z, List<String> list) throws RemoteException;

    boolean setUnderlyingNetworksForVpn(Network[] networkArr) throws RemoteException;

    void setVpnPackageAuthorization(String str, int i, int i2) throws RemoteException;

    void startLegacyVpn(VpnProfile vpnProfile) throws RemoteException;

    void startVpnProfile(String str) throws RemoteException;

    void stopVpnProfile(String str) throws RemoteException;

    boolean updateLockdownVpn() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IVpnManager {
        @Override // android.net.IVpnManager
        public boolean prepareVpn(String oldPackage, String newPackage, int userId) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public void setVpnPackageAuthorization(String packageName, int userId, int vpnType) throws RemoteException {
        }

        @Override // android.net.IVpnManager
        public ParcelFileDescriptor establishVpn(VpnConfig config) throws RemoteException {
            return null;
        }

        @Override // android.net.IVpnManager
        public boolean addVpnAddress(String address, int prefixLength) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public boolean removeVpnAddress(String address, int prefixLength) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public boolean setUnderlyingNetworksForVpn(Network[] networks) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public boolean provisionVpnProfile(VpnProfile profile, String packageName) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public void deleteVpnProfile(String packageName) throws RemoteException {
        }

        @Override // android.net.IVpnManager
        public void startVpnProfile(String packageName) throws RemoteException {
        }

        @Override // android.net.IVpnManager
        public void stopVpnProfile(String packageName) throws RemoteException {
        }

        @Override // android.net.IVpnManager
        public boolean isAlwaysOnVpnPackageSupported(int userId, String packageName) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public boolean setAlwaysOnVpnPackage(int userId, String packageName, boolean lockdown, List<String> lockdownAllowlist) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public String getAlwaysOnVpnPackage(int userId) throws RemoteException {
            return null;
        }

        @Override // android.net.IVpnManager
        public boolean isVpnLockdownEnabled(int userId) throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public List<String> getVpnLockdownAllowlist(int userId) throws RemoteException {
            return null;
        }

        @Override // android.net.IVpnManager
        public boolean isCallerCurrentAlwaysOnVpnApp() throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public boolean isCallerCurrentAlwaysOnVpnLockdownApp() throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public void startLegacyVpn(VpnProfile profile) throws RemoteException {
        }

        @Override // android.net.IVpnManager
        public LegacyVpnInfo getLegacyVpnInfo(int userId) throws RemoteException {
            return null;
        }

        @Override // android.net.IVpnManager
        public boolean updateLockdownVpn() throws RemoteException {
            return false;
        }

        @Override // android.net.IVpnManager
        public VpnConfig getVpnConfig(int userId) throws RemoteException {
            return null;
        }

        @Override // android.net.IVpnManager
        public void factoryReset() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVpnManager {
        static final int TRANSACTION_addVpnAddress = 4;
        static final int TRANSACTION_deleteVpnProfile = 8;
        static final int TRANSACTION_establishVpn = 3;
        static final int TRANSACTION_factoryReset = 22;
        static final int TRANSACTION_getAlwaysOnVpnPackage = 13;
        static final int TRANSACTION_getLegacyVpnInfo = 19;
        static final int TRANSACTION_getVpnConfig = 21;
        static final int TRANSACTION_getVpnLockdownAllowlist = 15;
        static final int TRANSACTION_isAlwaysOnVpnPackageSupported = 11;
        static final int TRANSACTION_isCallerCurrentAlwaysOnVpnApp = 16;
        static final int TRANSACTION_isCallerCurrentAlwaysOnVpnLockdownApp = 17;
        static final int TRANSACTION_isVpnLockdownEnabled = 14;
        static final int TRANSACTION_prepareVpn = 1;
        static final int TRANSACTION_provisionVpnProfile = 7;
        static final int TRANSACTION_removeVpnAddress = 5;
        static final int TRANSACTION_setAlwaysOnVpnPackage = 12;
        static final int TRANSACTION_setUnderlyingNetworksForVpn = 6;
        static final int TRANSACTION_setVpnPackageAuthorization = 2;
        static final int TRANSACTION_startLegacyVpn = 18;
        static final int TRANSACTION_startVpnProfile = 9;
        static final int TRANSACTION_stopVpnProfile = 10;
        static final int TRANSACTION_updateLockdownVpn = 20;

        public Stub() {
            attachInterface(this, IVpnManager.DESCRIPTOR);
        }

        public static IVpnManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IVpnManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IVpnManager)) {
                return new Proxy(obj);
            }
            return (IVpnManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "prepareVpn";
                case 2:
                    return "setVpnPackageAuthorization";
                case 3:
                    return "establishVpn";
                case 4:
                    return "addVpnAddress";
                case 5:
                    return "removeVpnAddress";
                case 6:
                    return "setUnderlyingNetworksForVpn";
                case 7:
                    return "provisionVpnProfile";
                case 8:
                    return "deleteVpnProfile";
                case 9:
                    return "startVpnProfile";
                case 10:
                    return "stopVpnProfile";
                case 11:
                    return "isAlwaysOnVpnPackageSupported";
                case 12:
                    return "setAlwaysOnVpnPackage";
                case 13:
                    return "getAlwaysOnVpnPackage";
                case 14:
                    return "isVpnLockdownEnabled";
                case 15:
                    return "getVpnLockdownAllowlist";
                case 16:
                    return "isCallerCurrentAlwaysOnVpnApp";
                case 17:
                    return "isCallerCurrentAlwaysOnVpnLockdownApp";
                case 18:
                    return "startLegacyVpn";
                case 19:
                    return "getLegacyVpnInfo";
                case 20:
                    return "updateLockdownVpn";
                case 21:
                    return "getVpnConfig";
                case 22:
                    return "factoryReset";
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
            VpnConfig _arg0;
            VpnProfile _arg02;
            VpnProfile _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IVpnManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg2 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            String _arg04 = data.readString();
                            String _arg1 = data.readString();
                            int _arg22 = data.readInt();
                            boolean prepareVpn = prepareVpn(_arg04, _arg1, _arg22);
                            reply.writeNoException();
                            reply.writeInt(prepareVpn ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg12 = data.readInt();
                            int _arg23 = data.readInt();
                            setVpnPackageAuthorization(_arg05, _arg12, _arg23);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = VpnConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            ParcelFileDescriptor _result = establishVpn(_arg0);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            String _arg06 = data.readString();
                            int _arg13 = data.readInt();
                            boolean addVpnAddress = addVpnAddress(_arg06, _arg13);
                            reply.writeNoException();
                            reply.writeInt(addVpnAddress ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg14 = data.readInt();
                            boolean removeVpnAddress = removeVpnAddress(_arg07, _arg14);
                            reply.writeNoException();
                            reply.writeInt(removeVpnAddress ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            Network[] _arg08 = (Network[]) data.createTypedArray(Network.CREATOR);
                            boolean underlyingNetworksForVpn = setUnderlyingNetworksForVpn(_arg08);
                            reply.writeNoException();
                            reply.writeInt(underlyingNetworksForVpn ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = VpnProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _arg15 = data.readString();
                            boolean provisionVpnProfile = provisionVpnProfile(_arg02, _arg15);
                            reply.writeNoException();
                            reply.writeInt(provisionVpnProfile ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            String _arg09 = data.readString();
                            deleteVpnProfile(_arg09);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            String _arg010 = data.readString();
                            startVpnProfile(_arg010);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            String _arg011 = data.readString();
                            stopVpnProfile(_arg011);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            String _arg16 = data.readString();
                            boolean isAlwaysOnVpnPackageSupported = isAlwaysOnVpnPackageSupported(_arg012, _arg16);
                            reply.writeNoException();
                            reply.writeInt(isAlwaysOnVpnPackageSupported ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            String _arg17 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            List<String> _arg3 = data.createStringArrayList();
                            boolean alwaysOnVpnPackage = setAlwaysOnVpnPackage(_arg013, _arg17, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeInt(alwaysOnVpnPackage ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            String _result2 = getAlwaysOnVpnPackage(_arg014);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 14:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            int _arg015 = data.readInt();
                            boolean isVpnLockdownEnabled = isVpnLockdownEnabled(_arg015);
                            reply.writeNoException();
                            reply.writeInt(isVpnLockdownEnabled ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            int _arg016 = data.readInt();
                            List<String> _result3 = getVpnLockdownAllowlist(_arg016);
                            reply.writeNoException();
                            reply.writeStringList(_result3);
                            return true;
                        case 16:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            boolean isCallerCurrentAlwaysOnVpnApp = isCallerCurrentAlwaysOnVpnApp();
                            reply.writeNoException();
                            reply.writeInt(isCallerCurrentAlwaysOnVpnApp ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            boolean isCallerCurrentAlwaysOnVpnLockdownApp = isCallerCurrentAlwaysOnVpnLockdownApp();
                            reply.writeNoException();
                            reply.writeInt(isCallerCurrentAlwaysOnVpnLockdownApp ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = VpnProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            startLegacyVpn(_arg03);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            LegacyVpnInfo _result4 = getLegacyVpnInfo(_arg017);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 20:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            boolean updateLockdownVpn = updateLockdownVpn();
                            reply.writeNoException();
                            reply.writeInt(updateLockdownVpn ? 1 : 0);
                            return true;
                        case 21:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            VpnConfig _result5 = getVpnConfig(_arg018);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 22:
                            data.enforceInterface(IVpnManager.DESCRIPTOR);
                            factoryReset();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVpnManager {
            public static IVpnManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IVpnManager.DESCRIPTOR;
            }

            @Override // android.net.IVpnManager
            public boolean prepareVpn(String oldPackage, String newPackage, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeString(oldPackage);
                    _data.writeString(newPackage);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().prepareVpn(oldPackage, newPackage, userId);
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

            @Override // android.net.IVpnManager
            public void setVpnPackageAuthorization(String packageName, int userId, int vpnType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeInt(vpnType);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVpnPackageAuthorization(packageName, userId, vpnType);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public ParcelFileDescriptor establishVpn(VpnConfig config) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().establishVpn(config);
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

            @Override // android.net.IVpnManager
            public boolean addVpnAddress(String address, int prefixLength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prefixLength);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addVpnAddress(address, prefixLength);
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

            @Override // android.net.IVpnManager
            public boolean removeVpnAddress(String address, int prefixLength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prefixLength);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeVpnAddress(address, prefixLength);
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

            @Override // android.net.IVpnManager
            public boolean setUnderlyingNetworksForVpn(Network[] networks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    boolean _result = false;
                    _data.writeTypedArray(networks, 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUnderlyingNetworksForVpn(networks);
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

            @Override // android.net.IVpnManager
            public boolean provisionVpnProfile(VpnProfile profile, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    boolean _result = true;
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().provisionVpnProfile(profile, packageName);
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

            @Override // android.net.IVpnManager
            public void deleteVpnProfile(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteVpnProfile(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public void startVpnProfile(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startVpnProfile(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public void stopVpnProfile(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopVpnProfile(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public boolean isAlwaysOnVpnPackageSupported(int userId, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAlwaysOnVpnPackageSupported(userId, packageName);
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

            @Override // android.net.IVpnManager
            public boolean setAlwaysOnVpnPackage(int userId, String packageName, boolean lockdown, List<String> lockdownAllowlist) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(packageName);
                    boolean _result = true;
                    _data.writeInt(lockdown ? 1 : 0);
                    _data.writeStringList(lockdownAllowlist);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAlwaysOnVpnPackage(userId, packageName, lockdown, lockdownAllowlist);
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

            @Override // android.net.IVpnManager
            public String getAlwaysOnVpnPackage(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlwaysOnVpnPackage(userId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public boolean isVpnLockdownEnabled(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVpnLockdownEnabled(userId);
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

            @Override // android.net.IVpnManager
            public List<String> getVpnLockdownAllowlist(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVpnLockdownAllowlist(userId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public boolean isCallerCurrentAlwaysOnVpnApp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCallerCurrentAlwaysOnVpnApp();
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

            @Override // android.net.IVpnManager
            public boolean isCallerCurrentAlwaysOnVpnLockdownApp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCallerCurrentAlwaysOnVpnLockdownApp();
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

            @Override // android.net.IVpnManager
            public void startLegacyVpn(VpnProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startLegacyVpn(profile);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public LegacyVpnInfo getLegacyVpnInfo(int userId) throws RemoteException {
                LegacyVpnInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLegacyVpnInfo(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LegacyVpnInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public boolean updateLockdownVpn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateLockdownVpn();
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

            @Override // android.net.IVpnManager
            public VpnConfig getVpnConfig(int userId) throws RemoteException {
                VpnConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVpnConfig(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VpnConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IVpnManager
            public void factoryReset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVpnManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().factoryReset();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVpnManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVpnManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
