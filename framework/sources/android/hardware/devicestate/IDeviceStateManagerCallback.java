package android.hardware.devicestate;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDeviceStateManagerCallback extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.devicestate.IDeviceStateManagerCallback";

    void onDeviceStateInfoChanged(DeviceStateInfo deviceStateInfo) throws RemoteException;

    void onRequestActive(IBinder iBinder) throws RemoteException;

    void onRequestCanceled(IBinder iBinder) throws RemoteException;

    void onRequestSuspended(IBinder iBinder) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDeviceStateManagerCallback {
        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onDeviceStateInfoChanged(DeviceStateInfo info) throws RemoteException {
        }

        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onRequestActive(IBinder token) throws RemoteException {
        }

        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onRequestSuspended(IBinder token) throws RemoteException {
        }

        @Override // android.hardware.devicestate.IDeviceStateManagerCallback
        public void onRequestCanceled(IBinder token) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDeviceStateManagerCallback {
        static final int TRANSACTION_onDeviceStateInfoChanged = 1;
        static final int TRANSACTION_onRequestActive = 2;
        static final int TRANSACTION_onRequestCanceled = 4;
        static final int TRANSACTION_onRequestSuspended = 3;

        public Stub() {
            attachInterface(this, IDeviceStateManagerCallback.DESCRIPTOR);
        }

        public static IDeviceStateManagerCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDeviceStateManagerCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDeviceStateManagerCallback)) {
                return new Proxy(obj);
            }
            return (IDeviceStateManagerCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDeviceStateInfoChanged";
                case 2:
                    return "onRequestActive";
                case 3:
                    return "onRequestSuspended";
                case 4:
                    return "onRequestCanceled";
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
            DeviceStateInfo _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDeviceStateManagerCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDeviceStateManagerCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = DeviceStateInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onDeviceStateInfoChanged(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IDeviceStateManagerCallback.DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            onRequestActive(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IDeviceStateManagerCallback.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            onRequestSuspended(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IDeviceStateManagerCallback.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            onRequestCanceled(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDeviceStateManagerCallback {
            public static IDeviceStateManagerCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDeviceStateManagerCallback.DESCRIPTOR;
            }

            @Override // android.hardware.devicestate.IDeviceStateManagerCallback
            public void onDeviceStateInfoChanged(DeviceStateInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManagerCallback.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceStateInfoChanged(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.devicestate.IDeviceStateManagerCallback
            public void onRequestActive(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManagerCallback.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestActive(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.devicestate.IDeviceStateManagerCallback
            public void onRequestSuspended(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManagerCallback.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestSuspended(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.devicestate.IDeviceStateManagerCallback
            public void onRequestCanceled(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDeviceStateManagerCallback.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestCanceled(token);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceStateManagerCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDeviceStateManagerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
