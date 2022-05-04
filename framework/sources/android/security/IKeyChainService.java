package android.security;

import android.content.pm.StringParceledListSlice;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.keystore.ParcelableKeyGenParameterSpec;
import java.util.List;
/* loaded from: classes2.dex */
public interface IKeyChainService extends IInterface {
    boolean containsCaAlias(String str) throws RemoteException;

    boolean containsKeyPair(String str) throws RemoteException;

    boolean deleteCaCertificate(String str) throws RemoteException;

    int generateKeyPair(String str, ParcelableKeyGenParameterSpec parcelableKeyGenParameterSpec) throws RemoteException;

    List<String> getCaCertificateChainAliases(String str, boolean z) throws RemoteException;

    byte[] getCaCertificates(String str) throws RemoteException;

    byte[] getCertificate(String str) throws RemoteException;

    String getCredentialManagementAppPackageName() throws RemoteException;

    AppUriAuthenticationPolicy getCredentialManagementAppPolicy() throws RemoteException;

    byte[] getEncodedCaCertificate(String str, boolean z) throws RemoteException;

    int[] getGrants(String str) throws RemoteException;

    String getPredefinedAliasForPackageAndUri(String str, Uri uri) throws RemoteException;

    StringParceledListSlice getSystemCaAliases() throws RemoteException;

    StringParceledListSlice getUserCaAliases() throws RemoteException;

    String getWifiKeyGrantAsUser(String str) throws RemoteException;

    boolean hasCredentialManagementApp() throws RemoteException;

    boolean hasGrant(int i, String str) throws RemoteException;

    String installCaCertificate(byte[] bArr) throws RemoteException;

    boolean installKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3, String str, int i) throws RemoteException;

    boolean isCredentialManagementApp(String str) throws RemoteException;

    boolean isUserSelectable(String str) throws RemoteException;

    void removeCredentialManagementApp() throws RemoteException;

    boolean removeKeyPair(String str) throws RemoteException;

    String requestPrivateKey(String str) throws RemoteException;

    boolean reset() throws RemoteException;

    void setCredentialManagementApp(String str, AppUriAuthenticationPolicy appUriAuthenticationPolicy) throws RemoteException;

    boolean setGrant(int i, String str, boolean z) throws RemoteException;

    boolean setKeyPairCertificate(String str, byte[] bArr, byte[] bArr2) throws RemoteException;

    void setUserSelectable(String str, boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IKeyChainService {
        @Override // android.security.IKeyChainService
        public String requestPrivateKey(String alias) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public byte[] getCertificate(String alias) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public byte[] getCaCertificates(String alias) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public boolean isUserSelectable(String alias) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public void setUserSelectable(String alias, boolean isUserSelectable) throws RemoteException {
        }

        @Override // android.security.IKeyChainService
        public int generateKeyPair(String algorithm, ParcelableKeyGenParameterSpec spec) throws RemoteException {
            return 0;
        }

        @Override // android.security.IKeyChainService
        public boolean setKeyPairCertificate(String alias, byte[] userCert, byte[] certChain) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public String installCaCertificate(byte[] caCertificate) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public boolean installKeyPair(byte[] privateKey, byte[] userCert, byte[] certChain, String alias, int uid) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public boolean removeKeyPair(String alias) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public boolean containsKeyPair(String alias) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public int[] getGrants(String alias) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public boolean deleteCaCertificate(String alias) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public boolean reset() throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public StringParceledListSlice getUserCaAliases() throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public StringParceledListSlice getSystemCaAliases() throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public boolean containsCaAlias(String alias) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public byte[] getEncodedCaCertificate(String alias, boolean includeDeletedSystem) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public List<String> getCaCertificateChainAliases(String rootAlias, boolean includeDeletedSystem) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public void setCredentialManagementApp(String packageName, AppUriAuthenticationPolicy policy) throws RemoteException {
        }

        @Override // android.security.IKeyChainService
        public boolean hasCredentialManagementApp() throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public String getCredentialManagementAppPackageName() throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public AppUriAuthenticationPolicy getCredentialManagementAppPolicy() throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public String getPredefinedAliasForPackageAndUri(String packageName, Uri uri) throws RemoteException {
            return null;
        }

        @Override // android.security.IKeyChainService
        public void removeCredentialManagementApp() throws RemoteException {
        }

        @Override // android.security.IKeyChainService
        public boolean isCredentialManagementApp(String packageName) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public boolean setGrant(int uid, String alias, boolean value) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public boolean hasGrant(int uid, String alias) throws RemoteException {
            return false;
        }

        @Override // android.security.IKeyChainService
        public String getWifiKeyGrantAsUser(String alias) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IKeyChainService {
        public static final String DESCRIPTOR = "android.security.IKeyChainService";
        static final int TRANSACTION_containsCaAlias = 17;
        static final int TRANSACTION_containsKeyPair = 11;
        static final int TRANSACTION_deleteCaCertificate = 13;
        static final int TRANSACTION_generateKeyPair = 6;
        static final int TRANSACTION_getCaCertificateChainAliases = 19;
        static final int TRANSACTION_getCaCertificates = 3;
        static final int TRANSACTION_getCertificate = 2;
        static final int TRANSACTION_getCredentialManagementAppPackageName = 22;
        static final int TRANSACTION_getCredentialManagementAppPolicy = 23;
        static final int TRANSACTION_getEncodedCaCertificate = 18;
        static final int TRANSACTION_getGrants = 12;
        static final int TRANSACTION_getPredefinedAliasForPackageAndUri = 24;
        static final int TRANSACTION_getSystemCaAliases = 16;
        static final int TRANSACTION_getUserCaAliases = 15;
        static final int TRANSACTION_getWifiKeyGrantAsUser = 29;
        static final int TRANSACTION_hasCredentialManagementApp = 21;
        static final int TRANSACTION_hasGrant = 28;
        static final int TRANSACTION_installCaCertificate = 8;
        static final int TRANSACTION_installKeyPair = 9;
        static final int TRANSACTION_isCredentialManagementApp = 26;
        static final int TRANSACTION_isUserSelectable = 4;
        static final int TRANSACTION_removeCredentialManagementApp = 25;
        static final int TRANSACTION_removeKeyPair = 10;
        static final int TRANSACTION_requestPrivateKey = 1;
        static final int TRANSACTION_reset = 14;
        static final int TRANSACTION_setCredentialManagementApp = 20;
        static final int TRANSACTION_setGrant = 27;
        static final int TRANSACTION_setKeyPairCertificate = 7;
        static final int TRANSACTION_setUserSelectable = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyChainService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeyChainService)) {
                return new Proxy(obj);
            }
            return (IKeyChainService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "requestPrivateKey";
                case 2:
                    return "getCertificate";
                case 3:
                    return "getCaCertificates";
                case 4:
                    return "isUserSelectable";
                case 5:
                    return "setUserSelectable";
                case 6:
                    return "generateKeyPair";
                case 7:
                    return "setKeyPairCertificate";
                case 8:
                    return "installCaCertificate";
                case 9:
                    return "installKeyPair";
                case 10:
                    return "removeKeyPair";
                case 11:
                    return "containsKeyPair";
                case 12:
                    return "getGrants";
                case 13:
                    return "deleteCaCertificate";
                case 14:
                    return "reset";
                case 15:
                    return "getUserCaAliases";
                case 16:
                    return "getSystemCaAliases";
                case 17:
                    return "containsCaAlias";
                case 18:
                    return "getEncodedCaCertificate";
                case 19:
                    return "getCaCertificateChainAliases";
                case 20:
                    return "setCredentialManagementApp";
                case 21:
                    return "hasCredentialManagementApp";
                case 22:
                    return "getCredentialManagementAppPackageName";
                case 23:
                    return "getCredentialManagementAppPolicy";
                case 24:
                    return "getPredefinedAliasForPackageAndUri";
                case 25:
                    return "removeCredentialManagementApp";
                case 26:
                    return "isCredentialManagementApp";
                case 27:
                    return "setGrant";
                case 28:
                    return "hasGrant";
                case 29:
                    return "getWifiKeyGrantAsUser";
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
            ParcelableKeyGenParameterSpec _arg1;
            AppUriAuthenticationPolicy _arg12;
            Uri _arg13;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg2 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            String _result = requestPrivateKey(_arg0);
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            byte[] _result2 = getCertificate(_arg02);
                            reply.writeNoException();
                            reply.writeByteArray(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            byte[] _result3 = getCaCertificates(_arg03);
                            reply.writeNoException();
                            reply.writeByteArray(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            boolean isUserSelectable = isUserSelectable(_arg04);
                            reply.writeNoException();
                            reply.writeInt(isUserSelectable ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            setUserSelectable(_arg05, _arg2);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ParcelableKeyGenParameterSpec.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _result4 = generateKeyPair(_arg06, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            byte[] _arg14 = data.createByteArray();
                            boolean keyPairCertificate = setKeyPairCertificate(_arg07, _arg14, data.createByteArray());
                            reply.writeNoException();
                            reply.writeInt(keyPairCertificate ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _arg08 = data.createByteArray();
                            String _result5 = installCaCertificate(_arg08);
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _arg09 = data.createByteArray();
                            byte[] _arg15 = data.createByteArray();
                            byte[] _arg22 = data.createByteArray();
                            String _arg3 = data.readString();
                            int _arg4 = data.readInt();
                            boolean installKeyPair = installKeyPair(_arg09, _arg15, _arg22, _arg3, _arg4);
                            reply.writeNoException();
                            reply.writeInt(installKeyPair ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            boolean removeKeyPair = removeKeyPair(_arg010);
                            reply.writeNoException();
                            reply.writeInt(removeKeyPair ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            boolean containsKeyPair = containsKeyPair(_arg011);
                            reply.writeNoException();
                            reply.writeInt(containsKeyPair ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            int[] _result6 = getGrants(_arg012);
                            reply.writeNoException();
                            reply.writeIntArray(_result6);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            boolean deleteCaCertificate = deleteCaCertificate(_arg013);
                            reply.writeNoException();
                            reply.writeInt(deleteCaCertificate ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            boolean reset = reset();
                            reply.writeNoException();
                            reply.writeInt(reset ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            StringParceledListSlice _result7 = getUserCaAliases();
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            StringParceledListSlice _result8 = getSystemCaAliases();
                            reply.writeNoException();
                            if (_result8 != null) {
                                reply.writeInt(1);
                                _result8.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            boolean containsCaAlias = containsCaAlias(_arg014);
                            reply.writeNoException();
                            reply.writeInt(containsCaAlias ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            byte[] _result9 = getEncodedCaCertificate(_arg015, _arg2);
                            reply.writeNoException();
                            reply.writeByteArray(_result9);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            List<String> _result10 = getCaCertificateChainAliases(_arg016, _arg2);
                            reply.writeNoException();
                            reply.writeStringList(_result10);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = AppUriAuthenticationPolicy.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            setCredentialManagementApp(_arg017, _arg12);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            boolean hasCredentialManagementApp = hasCredentialManagementApp();
                            reply.writeNoException();
                            reply.writeInt(hasCredentialManagementApp ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _result11 = getCredentialManagementAppPackageName();
                            reply.writeNoException();
                            reply.writeString(_result11);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            AppUriAuthenticationPolicy _result12 = getCredentialManagementAppPolicy();
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
                            String _arg018 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            String _result13 = getPredefinedAliasForPackageAndUri(_arg018, _arg13);
                            reply.writeNoException();
                            reply.writeString(_result13);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            removeCredentialManagementApp();
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            boolean isCredentialManagementApp = isCredentialManagementApp(_arg019);
                            reply.writeNoException();
                            reply.writeInt(isCredentialManagementApp ? 1 : 0);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            String _arg16 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            boolean grant = setGrant(_arg020, _arg16, _arg2);
                            reply.writeNoException();
                            reply.writeInt(grant ? 1 : 0);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            String _arg17 = data.readString();
                            boolean hasGrant = hasGrant(_arg021, _arg17);
                            reply.writeNoException();
                            reply.writeInt(hasGrant ? 1 : 0);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            String _result14 = getWifiKeyGrantAsUser(_arg022);
                            reply.writeNoException();
                            reply.writeString(_result14);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IKeyChainService {
            public static IKeyChainService sDefaultImpl;
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

            @Override // android.security.IKeyChainService
            public String requestPrivateKey(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestPrivateKey(alias);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public byte[] getCertificate(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCertificate(alias);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public byte[] getCaCertificates(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCaCertificates(alias);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public boolean isUserSelectable(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserSelectable(alias);
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

            @Override // android.security.IKeyChainService
            public void setUserSelectable(String alias, boolean isUserSelectable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    _data.writeInt(isUserSelectable ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserSelectable(alias, isUserSelectable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public int generateKeyPair(String algorithm, ParcelableKeyGenParameterSpec spec) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(algorithm);
                    if (spec != null) {
                        _data.writeInt(1);
                        spec.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateKeyPair(algorithm, spec);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public boolean setKeyPairCertificate(String alias, byte[] userCert, byte[] certChain) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    _data.writeByteArray(userCert);
                    _data.writeByteArray(certChain);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setKeyPairCertificate(alias, userCert, certChain);
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

            @Override // android.security.IKeyChainService
            public String installCaCertificate(byte[] caCertificate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(caCertificate);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().installCaCertificate(caCertificate);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public boolean installKeyPair(byte[] privateKey, byte[] userCert, byte[] certChain, String alias, int uid) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeByteArray(privateKey);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeByteArray(userCert);
                    try {
                        _data.writeByteArray(certChain);
                        try {
                            _data.writeString(alias);
                            try {
                                _data.writeInt(uid);
                                try {
                                    boolean _result = false;
                                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = true;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    boolean installKeyPair = Stub.getDefaultImpl().installKeyPair(privateKey, userCert, certChain, alias, uid);
                                    _reply.recycle();
                                    _data.recycle();
                                    return installKeyPair;
                                } catch (Throwable th4) {
                                    th = th4;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
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
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.security.IKeyChainService
            public boolean removeKeyPair(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeKeyPair(alias);
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

            @Override // android.security.IKeyChainService
            public boolean containsKeyPair(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().containsKeyPair(alias);
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

            @Override // android.security.IKeyChainService
            public int[] getGrants(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGrants(alias);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public boolean deleteCaCertificate(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteCaCertificate(alias);
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

            @Override // android.security.IKeyChainService
            public boolean reset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reset();
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

            @Override // android.security.IKeyChainService
            public StringParceledListSlice getUserCaAliases() throws RemoteException {
                StringParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserCaAliases();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = StringParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public StringParceledListSlice getSystemCaAliases() throws RemoteException {
                StringParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemCaAliases();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = StringParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public boolean containsCaAlias(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().containsCaAlias(alias);
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

            @Override // android.security.IKeyChainService
            public byte[] getEncodedCaCertificate(String alias, boolean includeDeletedSystem) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    _data.writeInt(includeDeletedSystem ? 1 : 0);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEncodedCaCertificate(alias, includeDeletedSystem);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public List<String> getCaCertificateChainAliases(String rootAlias, boolean includeDeletedSystem) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(rootAlias);
                    _data.writeInt(includeDeletedSystem ? 1 : 0);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCaCertificateChainAliases(rootAlias, includeDeletedSystem);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public void setCredentialManagementApp(String packageName, AppUriAuthenticationPolicy policy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (policy != null) {
                        _data.writeInt(1);
                        policy.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCredentialManagementApp(packageName, policy);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public boolean hasCredentialManagementApp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasCredentialManagementApp();
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

            @Override // android.security.IKeyChainService
            public String getCredentialManagementAppPackageName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialManagementAppPackageName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public AppUriAuthenticationPolicy getCredentialManagementAppPolicy() throws RemoteException {
                AppUriAuthenticationPolicy _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialManagementAppPolicy();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AppUriAuthenticationPolicy.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public String getPredefinedAliasForPackageAndUri(String packageName, Uri uri) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPredefinedAliasForPackageAndUri(packageName, uri);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public void removeCredentialManagementApp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeCredentialManagementApp();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.IKeyChainService
            public boolean isCredentialManagementApp(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCredentialManagementApp(packageName);
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

            @Override // android.security.IKeyChainService
            public boolean setGrant(int uid, String alias, boolean value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(alias);
                    boolean _result = true;
                    _data.writeInt(value ? 1 : 0);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setGrant(uid, alias, value);
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

            @Override // android.security.IKeyChainService
            public boolean hasGrant(int uid, String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(alias);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasGrant(uid, alias);
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

            @Override // android.security.IKeyChainService
            public String getWifiKeyGrantAsUser(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWifiKeyGrantAsUser(alias);
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

        public static boolean setDefaultImpl(IKeyChainService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeyChainService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
