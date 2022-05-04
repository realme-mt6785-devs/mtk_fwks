package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ITestSession extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.biometrics.ITestSession";

    void acceptAuthentication(int i) throws RemoteException;

    void cleanupInternalState(int i) throws RemoteException;

    void finishEnroll(int i) throws RemoteException;

    void notifyAcquired(int i, int i2) throws RemoteException;

    void notifyError(int i, int i2) throws RemoteException;

    void rejectAuthentication(int i) throws RemoteException;

    void setTestHalEnabled(boolean z) throws RemoteException;

    void startEnroll(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ITestSession {
        @Override // android.hardware.biometrics.ITestSession
        public void setTestHalEnabled(boolean enableTestHal) throws RemoteException {
        }

        @Override // android.hardware.biometrics.ITestSession
        public void startEnroll(int userId) throws RemoteException {
        }

        @Override // android.hardware.biometrics.ITestSession
        public void finishEnroll(int userId) throws RemoteException {
        }

        @Override // android.hardware.biometrics.ITestSession
        public void acceptAuthentication(int userId) throws RemoteException {
        }

        @Override // android.hardware.biometrics.ITestSession
        public void rejectAuthentication(int userId) throws RemoteException {
        }

        @Override // android.hardware.biometrics.ITestSession
        public void notifyAcquired(int userId, int acquireInfo) throws RemoteException {
        }

        @Override // android.hardware.biometrics.ITestSession
        public void notifyError(int userId, int errorCode) throws RemoteException {
        }

        @Override // android.hardware.biometrics.ITestSession
        public void cleanupInternalState(int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ITestSession {
        static final int TRANSACTION_acceptAuthentication = 4;
        static final int TRANSACTION_cleanupInternalState = 8;
        static final int TRANSACTION_finishEnroll = 3;
        static final int TRANSACTION_notifyAcquired = 6;
        static final int TRANSACTION_notifyError = 7;
        static final int TRANSACTION_rejectAuthentication = 5;
        static final int TRANSACTION_setTestHalEnabled = 1;
        static final int TRANSACTION_startEnroll = 2;

        public Stub() {
            attachInterface(this, ITestSession.DESCRIPTOR);
        }

        public static ITestSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITestSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITestSession)) {
                return new Proxy(obj);
            }
            return (ITestSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setTestHalEnabled";
                case 2:
                    return "startEnroll";
                case 3:
                    return "finishEnroll";
                case 4:
                    return "acceptAuthentication";
                case 5:
                    return "rejectAuthentication";
                case 6:
                    return "notifyAcquired";
                case 7:
                    return "notifyError";
                case 8:
                    return "cleanupInternalState";
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
                    reply.writeString(ITestSession.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            setTestHalEnabled(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            startEnroll(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            finishEnroll(_arg03);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            acceptAuthentication(_arg04);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            rejectAuthentication(_arg05);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg1 = data.readInt();
                            notifyAcquired(_arg06, _arg1);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg12 = data.readInt();
                            notifyError(_arg07, _arg12);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(ITestSession.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            cleanupInternalState(_arg08);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ITestSession {
            public static ITestSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITestSession.DESCRIPTOR;
            }

            @Override // android.hardware.biometrics.ITestSession
            public void setTestHalEnabled(boolean enableTestHal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(enableTestHal ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestHalEnabled(enableTestHal);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.ITestSession
            public void startEnroll(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startEnroll(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.ITestSession
            public void finishEnroll(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finishEnroll(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.ITestSession
            public void acceptAuthentication(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().acceptAuthentication(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.ITestSession
            public void rejectAuthentication(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().rejectAuthentication(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.ITestSession
            public void notifyAcquired(int userId, int acquireInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(acquireInfo);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyAcquired(userId, acquireInfo);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.ITestSession
            public void notifyError(int userId, int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyError(userId, errorCode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.ITestSession
            public void cleanupInternalState(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITestSession.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cleanupInternalState(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITestSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITestSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
