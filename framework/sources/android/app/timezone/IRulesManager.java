package android.app.timezone;

import android.app.timezone.ICallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IRulesManager extends IInterface {
    public static final String DESCRIPTOR = "android.app.timezone.IRulesManager";

    RulesState getRulesState() throws RemoteException;

    int requestInstall(ParcelFileDescriptor parcelFileDescriptor, byte[] bArr, ICallback iCallback) throws RemoteException;

    void requestNothing(byte[] bArr, boolean z) throws RemoteException;

    int requestUninstall(byte[] bArr, ICallback iCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IRulesManager {
        @Override // android.app.timezone.IRulesManager
        public RulesState getRulesState() throws RemoteException {
            return null;
        }

        @Override // android.app.timezone.IRulesManager
        public int requestInstall(ParcelFileDescriptor distroFileDescriptor, byte[] checkToken, ICallback callback) throws RemoteException {
            return 0;
        }

        @Override // android.app.timezone.IRulesManager
        public int requestUninstall(byte[] checkToken, ICallback callback) throws RemoteException {
            return 0;
        }

        @Override // android.app.timezone.IRulesManager
        public void requestNothing(byte[] token, boolean success) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRulesManager {
        static final int TRANSACTION_getRulesState = 1;
        static final int TRANSACTION_requestInstall = 2;
        static final int TRANSACTION_requestNothing = 4;
        static final int TRANSACTION_requestUninstall = 3;

        public Stub() {
            attachInterface(this, IRulesManager.DESCRIPTOR);
        }

        public static IRulesManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRulesManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRulesManager)) {
                return new Proxy(obj);
            }
            return (IRulesManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getRulesState";
                case 2:
                    return "requestInstall";
                case 3:
                    return "requestUninstall";
                case 4:
                    return "requestNothing";
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
            ParcelFileDescriptor _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRulesManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRulesManager.DESCRIPTOR);
                            RulesState _result = getRulesState();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IRulesManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            byte[] _arg12 = data.createByteArray();
                            ICallback _arg2 = ICallback.Stub.asInterface(data.readStrongBinder());
                            int _result2 = requestInstall(_arg0, _arg12, _arg2);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IRulesManager.DESCRIPTOR);
                            byte[] _arg02 = data.createByteArray();
                            ICallback _arg13 = ICallback.Stub.asInterface(data.readStrongBinder());
                            int _result3 = requestUninstall(_arg02, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IRulesManager.DESCRIPTOR);
                            byte[] _arg03 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            requestNothing(_arg03, _arg1);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IRulesManager {
            public static IRulesManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRulesManager.DESCRIPTOR;
            }

            @Override // android.app.timezone.IRulesManager
            public RulesState getRulesState() throws RemoteException {
                RulesState _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRulesManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRulesState();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RulesState.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timezone.IRulesManager
            public int requestInstall(ParcelFileDescriptor distroFileDescriptor, byte[] checkToken, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRulesManager.DESCRIPTOR);
                    if (distroFileDescriptor != null) {
                        _data.writeInt(1);
                        distroFileDescriptor.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(checkToken);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestInstall(distroFileDescriptor, checkToken, callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timezone.IRulesManager
            public int requestUninstall(byte[] checkToken, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRulesManager.DESCRIPTOR);
                    _data.writeByteArray(checkToken);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestUninstall(checkToken, callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.timezone.IRulesManager
            public void requestNothing(byte[] token, boolean success) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRulesManager.DESCRIPTOR);
                    _data.writeByteArray(token);
                    _data.writeInt(success ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestNothing(token, success);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRulesManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRulesManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
