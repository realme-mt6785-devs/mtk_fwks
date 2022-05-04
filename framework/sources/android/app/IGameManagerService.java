package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IGameManagerService extends IInterface {
    public static final String DESCRIPTOR = "android.app.IGameManagerService";

    int[] getAvailableGameModes(String str) throws RemoteException;

    int getGameMode(String str, int i) throws RemoteException;

    void setGameMode(String str, int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IGameManagerService {
        @Override // android.app.IGameManagerService
        public int getGameMode(String packageName, int userId) throws RemoteException {
            return 0;
        }

        @Override // android.app.IGameManagerService
        public void setGameMode(String packageName, int gameMode, int userId) throws RemoteException {
        }

        @Override // android.app.IGameManagerService
        public int[] getAvailableGameModes(String packageName) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IGameManagerService {
        static final int TRANSACTION_getAvailableGameModes = 3;
        static final int TRANSACTION_getGameMode = 1;
        static final int TRANSACTION_setGameMode = 2;

        public Stub() {
            attachInterface(this, IGameManagerService.DESCRIPTOR);
        }

        public static IGameManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGameManagerService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGameManagerService)) {
                return new Proxy(obj);
            }
            return (IGameManagerService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getGameMode";
                case 2:
                    return "setGameMode";
                case 3:
                    return "getAvailableGameModes";
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
                    reply.writeString(IGameManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGameManagerService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _arg1 = data.readInt();
                            int _result = getGameMode(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IGameManagerService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg12 = data.readInt();
                            int _arg2 = data.readInt();
                            setGameMode(_arg02, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IGameManagerService.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int[] _result2 = getAvailableGameModes(_arg03);
                            reply.writeNoException();
                            reply.writeIntArray(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IGameManagerService {
            public static IGameManagerService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGameManagerService.DESCRIPTOR;
            }

            @Override // android.app.IGameManagerService
            public int getGameMode(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGameManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGameMode(packageName, userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IGameManagerService
            public void setGameMode(String packageName, int gameMode, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGameManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(gameMode);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setGameMode(packageName, gameMode, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.IGameManagerService
            public int[] getAvailableGameModes(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGameManagerService.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableGameModes(packageName);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGameManagerService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGameManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
