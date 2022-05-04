package android.hardware.devicestate;

import android.hardware.devicestate.IDeviceStateManagerCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDeviceStateManager extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.devicestate.IDeviceStateManager";

    void cancelRequest(IBinder iBinder) throws RemoteException;

    DeviceStateInfo getDeviceStateInfo() throws RemoteException;

    void registerCallback(IDeviceStateManagerCallback iDeviceStateManagerCallback) throws RemoteException;

    void requestState(IBinder iBinder, int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDeviceStateManager {
        @Override // android.hardware.devicestate.IDeviceStateManager
        public DeviceStateInfo getDeviceStateInfo() throws RemoteException {
            return null;
        }

        @Override // android.hardware.devicestate.IDeviceStateManager
        public void registerCallback(IDeviceStateManagerCallback callback) throws RemoteException {
        }

        @Override // android.hardware.devicestate.IDeviceStateManager
        public void requestState(IBinder token, int state, int flags) throws RemoteException {
        }

        @Override // android.hardware.devicestate.IDeviceStateManager
        public void cancelRequest(IBinder token) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDeviceStateManager {
        static final int TRANSACTION_cancelRequest = 4;
        static final int TRANSACTION_getDeviceStateInfo = 1;
        static final int TRANSACTION_registerCallback = 2;
        static final int TRANSACTION_requestState = 3;

        public Stub() {
            attachInterface(this, IDeviceStateManager.DESCRIPTOR);
        }

        public static IDeviceStateManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDeviceStateManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDeviceStateManager)) {
                return new Proxy(obj);
            }
            return (IDeviceStateManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getDeviceStateInfo";
                case 2:
                    return "registerCallback";
                case 3:
                    return "requestState";
                case 4:
                    return "cancelRequest";
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
                    reply.writeString(IDeviceStateManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDeviceStateManager.DESCRIPTOR);
                            DeviceStateInfo _result = getDeviceStateInfo();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IDeviceStateManager.DESCRIPTOR);
                            IDeviceStateManagerCallback _arg0 = IDeviceStateManagerCallback.Stub.asInterface(data.readStrongBinder());
                            registerCallback(_arg0);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IDeviceStateManager.DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            requestState(_arg02, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IDeviceStateManager.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            cancelRequest(_arg03);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDeviceStateManager {
            public static IDeviceStateManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDeviceStateManager.DESCRIPTOR;
            }

            @Override // android.hardware.devicestate.IDeviceStateManager
            public DeviceStateInfo getDeviceStateInfo() throws RemoteException {
                DeviceStateInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceStateInfo();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DeviceStateInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.devicestate.IDeviceStateManager
            public void registerCallback(IDeviceStateManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManager.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.devicestate.IDeviceStateManager
            public void requestState(IBinder token, int state, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManager.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(state);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestState(token, state, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.devicestate.IDeviceStateManager
            public void cancelRequest(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManager.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelRequest(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceStateManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDeviceStateManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
