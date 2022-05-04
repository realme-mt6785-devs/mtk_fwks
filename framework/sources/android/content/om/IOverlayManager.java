package android.content.om;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public interface IOverlayManager extends IInterface {
    void commit(OverlayManagerTransaction overlayManagerTransaction) throws RemoteException;

    Map getAllOverlays(int i) throws RemoteException;

    String[] getDefaultOverlayPackages() throws RemoteException;

    OverlayInfo getOverlayInfo(String str, int i) throws RemoteException;

    OverlayInfo getOverlayInfoByIdentifier(OverlayIdentifier overlayIdentifier, int i) throws RemoteException;

    List getOverlayInfosForTarget(String str, int i) throws RemoteException;

    void invalidateCachesForOverlay(String str, int i) throws RemoteException;

    boolean setEnabled(String str, boolean z, int i) throws RemoteException;

    boolean setEnabledExclusive(String str, boolean z, int i) throws RemoteException;

    boolean setEnabledExclusiveInCategory(String str, int i) throws RemoteException;

    boolean setHighestPriority(String str, int i) throws RemoteException;

    boolean setLowestPriority(String str, int i) throws RemoteException;

    boolean setPriority(String str, String str2, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IOverlayManager {
        @Override // android.content.om.IOverlayManager
        public Map getAllOverlays(int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public List getOverlayInfosForTarget(String targetPackageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public OverlayInfo getOverlayInfo(String packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public OverlayInfo getOverlayInfoByIdentifier(OverlayIdentifier packageName, int userId) throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setEnabled(String packageName, boolean enable, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setEnabledExclusive(String packageName, boolean enable, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setEnabledExclusiveInCategory(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setPriority(String packageName, String newParentPackageName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setHighestPriority(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public boolean setLowestPriority(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.content.om.IOverlayManager
        public String[] getDefaultOverlayPackages() throws RemoteException {
            return null;
        }

        @Override // android.content.om.IOverlayManager
        public void invalidateCachesForOverlay(String packageName, int userId) throws RemoteException {
        }

        @Override // android.content.om.IOverlayManager
        public void commit(OverlayManagerTransaction transaction) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IOverlayManager {
        public static final String DESCRIPTOR = "android.content.om.IOverlayManager";
        static final int TRANSACTION_commit = 13;
        static final int TRANSACTION_getAllOverlays = 1;
        static final int TRANSACTION_getDefaultOverlayPackages = 11;
        static final int TRANSACTION_getOverlayInfo = 3;
        static final int TRANSACTION_getOverlayInfoByIdentifier = 4;
        static final int TRANSACTION_getOverlayInfosForTarget = 2;
        static final int TRANSACTION_invalidateCachesForOverlay = 12;
        static final int TRANSACTION_setEnabled = 5;
        static final int TRANSACTION_setEnabledExclusive = 6;
        static final int TRANSACTION_setEnabledExclusiveInCategory = 7;
        static final int TRANSACTION_setHighestPriority = 9;
        static final int TRANSACTION_setLowestPriority = 10;
        static final int TRANSACTION_setPriority = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOverlayManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IOverlayManager)) {
                return new Proxy(obj);
            }
            return (IOverlayManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getAllOverlays";
                case 2:
                    return "getOverlayInfosForTarget";
                case 3:
                    return "getOverlayInfo";
                case 4:
                    return "getOverlayInfoByIdentifier";
                case 5:
                    return "setEnabled";
                case 6:
                    return "setEnabledExclusive";
                case 7:
                    return "setEnabledExclusiveInCategory";
                case 8:
                    return "setPriority";
                case 9:
                    return "setHighestPriority";
                case 10:
                    return "setLowestPriority";
                case 11:
                    return "getDefaultOverlayPackages";
                case 12:
                    return "invalidateCachesForOverlay";
                case 13:
                    return "commit";
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
            OverlayIdentifier _arg0;
            OverlayManagerTransaction _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            Map _result = getAllOverlays(_arg03);
                            reply.writeNoException();
                            reply.writeMap(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            List _result2 = getOverlayInfosForTarget(_arg04, data.readInt());
                            reply.writeNoException();
                            reply.writeList(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            OverlayInfo _result3 = getOverlayInfo(_arg05, data.readInt());
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OverlayIdentifier.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            OverlayInfo _result4 = getOverlayInfoByIdentifier(_arg0, data.readInt());
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            int _arg2 = data.readInt();
                            boolean enabled = setEnabled(_arg06, _arg1, _arg2);
                            reply.writeNoException();
                            reply.writeInt(enabled ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            int _arg22 = data.readInt();
                            boolean enabledExclusive = setEnabledExclusive(_arg07, _arg1, _arg22);
                            reply.writeNoException();
                            reply.writeInt(enabledExclusive ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            boolean enabledExclusiveInCategory = setEnabledExclusiveInCategory(_arg08, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(enabledExclusiveInCategory ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _arg12 = data.readString();
                            int _arg23 = data.readInt();
                            boolean priority = setPriority(_arg09, _arg12, _arg23);
                            reply.writeNoException();
                            reply.writeInt(priority ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            boolean highestPriority = setHighestPriority(_arg010, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(highestPriority ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            boolean lowestPriority = setLowestPriority(_arg011, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(lowestPriority ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result5 = getDefaultOverlayPackages();
                            reply.writeNoException();
                            reply.writeStringArray(_result5);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            invalidateCachesForOverlay(_arg012, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = OverlayManagerTransaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            commit(_arg02);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IOverlayManager {
            public static IOverlayManager sDefaultImpl;
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

            @Override // android.content.om.IOverlayManager
            public Map getAllOverlays(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllOverlays(userId);
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public List getOverlayInfosForTarget(String targetPackageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(targetPackageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverlayInfosForTarget(targetPackageName, userId);
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    List _result = _reply.readArrayList(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public OverlayInfo getOverlayInfo(String packageName, int userId) throws RemoteException {
                OverlayInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverlayInfo(packageName, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = OverlayInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public OverlayInfo getOverlayInfoByIdentifier(OverlayIdentifier packageName, int userId) throws RemoteException {
                OverlayInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (packageName != null) {
                        _data.writeInt(1);
                        packageName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverlayInfoByIdentifier(packageName, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = OverlayInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public boolean setEnabled(String packageName, boolean enable, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = true;
                    _data.writeInt(enable ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnabled(packageName, enable, userId);
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

            @Override // android.content.om.IOverlayManager
            public boolean setEnabledExclusive(String packageName, boolean enable, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = true;
                    _data.writeInt(enable ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnabledExclusive(packageName, enable, userId);
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

            @Override // android.content.om.IOverlayManager
            public boolean setEnabledExclusiveInCategory(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnabledExclusiveInCategory(packageName, userId);
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

            @Override // android.content.om.IOverlayManager
            public boolean setPriority(String packageName, String newParentPackageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(newParentPackageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPriority(packageName, newParentPackageName, userId);
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

            @Override // android.content.om.IOverlayManager
            public boolean setHighestPriority(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setHighestPriority(packageName, userId);
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

            @Override // android.content.om.IOverlayManager
            public boolean setLowestPriority(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLowestPriority(packageName, userId);
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

            @Override // android.content.om.IOverlayManager
            public String[] getDefaultOverlayPackages() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultOverlayPackages();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public void invalidateCachesForOverlay(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().invalidateCachesForOverlay(packageName, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.om.IOverlayManager
            public void commit(OverlayManagerTransaction transaction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (transaction != null) {
                        _data.writeInt(1);
                        transaction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().commit(transaction);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOverlayManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOverlayManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
