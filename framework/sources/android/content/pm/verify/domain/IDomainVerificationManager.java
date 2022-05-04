package android.content.pm.verify.domain;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IDomainVerificationManager extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.verify.domain.IDomainVerificationManager";

    DomainVerificationInfo getDomainVerificationInfo(String str) throws RemoteException;

    DomainVerificationUserState getDomainVerificationUserState(String str, int i) throws RemoteException;

    List<DomainOwner> getOwnersForDomain(String str, int i) throws RemoteException;

    List<String> queryValidVerificationPackageNames() throws RemoteException;

    void setDomainVerificationLinkHandlingAllowed(String str, boolean z, int i) throws RemoteException;

    int setDomainVerificationStatus(String str, DomainSet domainSet, int i) throws RemoteException;

    int setDomainVerificationUserSelection(String str, DomainSet domainSet, boolean z, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDomainVerificationManager {
        @Override // android.content.pm.verify.domain.IDomainVerificationManager
        public List<String> queryValidVerificationPackageNames() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.verify.domain.IDomainVerificationManager
        public DomainVerificationInfo getDomainVerificationInfo(String packageName) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.verify.domain.IDomainVerificationManager
        public DomainVerificationUserState getDomainVerificationUserState(String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.verify.domain.IDomainVerificationManager
        public List<DomainOwner> getOwnersForDomain(String domain, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.verify.domain.IDomainVerificationManager
        public int setDomainVerificationStatus(String domainSetId, DomainSet domains, int state) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.verify.domain.IDomainVerificationManager
        public void setDomainVerificationLinkHandlingAllowed(String packageName, boolean allowed, int userId) throws RemoteException {
        }

        @Override // android.content.pm.verify.domain.IDomainVerificationManager
        public int setDomainVerificationUserSelection(String domainSetId, DomainSet domains, boolean enabled, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDomainVerificationManager {
        static final int TRANSACTION_getDomainVerificationInfo = 2;
        static final int TRANSACTION_getDomainVerificationUserState = 3;
        static final int TRANSACTION_getOwnersForDomain = 4;
        static final int TRANSACTION_queryValidVerificationPackageNames = 1;
        static final int TRANSACTION_setDomainVerificationLinkHandlingAllowed = 6;
        static final int TRANSACTION_setDomainVerificationStatus = 5;
        static final int TRANSACTION_setDomainVerificationUserSelection = 7;

        public Stub() {
            attachInterface(this, IDomainVerificationManager.DESCRIPTOR);
        }

        public static IDomainVerificationManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDomainVerificationManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDomainVerificationManager)) {
                return new Proxy(obj);
            }
            return (IDomainVerificationManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "queryValidVerificationPackageNames";
                case 2:
                    return "getDomainVerificationInfo";
                case 3:
                    return "getDomainVerificationUserState";
                case 4:
                    return "getOwnersForDomain";
                case 5:
                    return "setDomainVerificationStatus";
                case 6:
                    return "setDomainVerificationLinkHandlingAllowed";
                case 7:
                    return "setDomainVerificationUserSelection";
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
            DomainSet _arg1;
            DomainSet _arg12;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDomainVerificationManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg2 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDomainVerificationManager.DESCRIPTOR);
                            List<String> _result = queryValidVerificationPackageNames();
                            reply.writeNoException();
                            reply.writeStringList(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IDomainVerificationManager.DESCRIPTOR);
                            String _arg0 = data.readString();
                            DomainVerificationInfo _result2 = getDomainVerificationInfo(_arg0);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(IDomainVerificationManager.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg13 = data.readInt();
                            DomainVerificationUserState _result3 = getDomainVerificationUserState(_arg02, _arg13);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(IDomainVerificationManager.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg14 = data.readInt();
                            List<DomainOwner> _result4 = getOwnersForDomain(_arg03, _arg14);
                            reply.writeNoException();
                            reply.writeTypedList(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IDomainVerificationManager.DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = DomainSet.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _result5 = setDomainVerificationStatus(_arg04, _arg1, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 6:
                            data.enforceInterface(IDomainVerificationManager.DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            setDomainVerificationLinkHandlingAllowed(_arg05, _arg2, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IDomainVerificationManager.DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = DomainSet.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            int _arg3 = data.readInt();
                            int _result6 = setDomainVerificationUserSelection(_arg06, _arg12, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDomainVerificationManager {
            public static IDomainVerificationManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDomainVerificationManager.DESCRIPTOR;
            }

            @Override // android.content.pm.verify.domain.IDomainVerificationManager
            public List<String> queryValidVerificationPackageNames() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDomainVerificationManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryValidVerificationPackageNames();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.verify.domain.IDomainVerificationManager
            public DomainVerificationInfo getDomainVerificationInfo(String packageName) throws RemoteException {
                DomainVerificationInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDomainVerificationManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDomainVerificationInfo(packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DomainVerificationInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.verify.domain.IDomainVerificationManager
            public DomainVerificationUserState getDomainVerificationUserState(String packageName, int userId) throws RemoteException {
                DomainVerificationUserState _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDomainVerificationManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDomainVerificationUserState(packageName, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DomainVerificationUserState.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.verify.domain.IDomainVerificationManager
            public List<DomainOwner> getOwnersForDomain(String domain, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDomainVerificationManager.DESCRIPTOR);
                    _data.writeString(domain);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOwnersForDomain(domain, userId);
                    }
                    _reply.readException();
                    List<DomainOwner> _result = _reply.createTypedArrayList(DomainOwner.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.verify.domain.IDomainVerificationManager
            public int setDomainVerificationStatus(String domainSetId, DomainSet domains, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDomainVerificationManager.DESCRIPTOR);
                    _data.writeString(domainSetId);
                    if (domains != null) {
                        _data.writeInt(1);
                        domains.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDomainVerificationStatus(domainSetId, domains, state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.verify.domain.IDomainVerificationManager
            public void setDomainVerificationLinkHandlingAllowed(String packageName, boolean allowed, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDomainVerificationManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(allowed ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDomainVerificationLinkHandlingAllowed(packageName, allowed, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.pm.verify.domain.IDomainVerificationManager
            public int setDomainVerificationUserSelection(String domainSetId, DomainSet domains, boolean enabled, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDomainVerificationManager.DESCRIPTOR);
                    _data.writeString(domainSetId);
                    int i = 1;
                    if (domains != null) {
                        _data.writeInt(1);
                        domains.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!enabled) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDomainVerificationUserSelection(domainSetId, domains, enabled, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDomainVerificationManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDomainVerificationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
