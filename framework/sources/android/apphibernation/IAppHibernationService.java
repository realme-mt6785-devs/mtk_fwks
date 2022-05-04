package android.apphibernation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IAppHibernationService extends IInterface {
    public static final String DESCRIPTOR = "android.apphibernation.IAppHibernationService";

    List<String> getHibernatingPackagesForUser(int i) throws RemoteException;

    boolean isHibernatingForUser(String str, int i) throws RemoteException;

    boolean isHibernatingGlobally(String str) throws RemoteException;

    void setHibernatingForUser(String str, int i, boolean z) throws RemoteException;

    void setHibernatingGlobally(String str, boolean z) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAppHibernationService {
        @Override // android.apphibernation.IAppHibernationService
        public boolean isHibernatingForUser(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.apphibernation.IAppHibernationService
        public void setHibernatingForUser(String packageName, int userId, boolean isHibernating) throws RemoteException {
        }

        @Override // android.apphibernation.IAppHibernationService
        public boolean isHibernatingGlobally(String packageName) throws RemoteException {
            return false;
        }

        @Override // android.apphibernation.IAppHibernationService
        public void setHibernatingGlobally(String packageName, boolean isHibernating) throws RemoteException {
        }

        @Override // android.apphibernation.IAppHibernationService
        public List<String> getHibernatingPackagesForUser(int userId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAppHibernationService {
        static final int TRANSACTION_getHibernatingPackagesForUser = 5;
        static final int TRANSACTION_isHibernatingForUser = 1;
        static final int TRANSACTION_isHibernatingGlobally = 3;
        static final int TRANSACTION_setHibernatingForUser = 2;
        static final int TRANSACTION_setHibernatingGlobally = 4;

        public Stub() {
            attachInterface(this, IAppHibernationService.DESCRIPTOR);
        }

        public static IAppHibernationService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAppHibernationService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAppHibernationService)) {
                return new Proxy(obj);
            }
            return (IAppHibernationService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "isHibernatingForUser";
                case 2:
                    return "setHibernatingForUser";
                case 3:
                    return "isHibernatingGlobally";
                case 4:
                    return "setHibernatingGlobally";
                case 5:
                    return "getHibernatingPackagesForUser";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IAppHibernationService.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAppHibernationService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            boolean isHibernatingForUser = isHibernatingForUser(_arg0, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isHibernatingForUser ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IAppHibernationService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg12 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            setHibernatingForUser(_arg02, _arg12, _arg1);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IAppHibernationService.DESCRIPTOR);
                            String _arg03 = data.readString();
                            boolean isHibernatingGlobally = isHibernatingGlobally(_arg03);
                            reply.writeNoException();
                            reply.writeInt(isHibernatingGlobally ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IAppHibernationService.DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            setHibernatingGlobally(_arg04, _arg1);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IAppHibernationService.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            List<String> _result = getHibernatingPackagesForUser(_arg05);
                            reply.writeNoException();
                            reply.writeStringList(_result);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAppHibernationService {
            public static IAppHibernationService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAppHibernationService.DESCRIPTOR;
            }

            @Override // android.apphibernation.IAppHibernationService
            public boolean isHibernatingForUser(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppHibernationService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHibernatingForUser(packageName, userId);
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

            @Override // android.apphibernation.IAppHibernationService
            public void setHibernatingForUser(String packageName, int userId, boolean isHibernating) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppHibernationService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeInt(isHibernating ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setHibernatingForUser(packageName, userId, isHibernating);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apphibernation.IAppHibernationService
            public boolean isHibernatingGlobally(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppHibernationService.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHibernatingGlobally(packageName);
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

            @Override // android.apphibernation.IAppHibernationService
            public void setHibernatingGlobally(String packageName, boolean isHibernating) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppHibernationService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(isHibernating ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setHibernatingGlobally(packageName, isHibernating);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.apphibernation.IAppHibernationService
            public List<String> getHibernatingPackagesForUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppHibernationService.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHibernatingPackagesForUser(userId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppHibernationService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAppHibernationService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
