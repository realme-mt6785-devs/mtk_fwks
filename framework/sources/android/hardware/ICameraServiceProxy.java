package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICameraServiceProxy extends IInterface {
    boolean isRotateAndCropOverrideNeeded(String str, int i, int i2) throws RemoteException;

    void notifyCameraState(CameraSessionStats cameraSessionStats) throws RemoteException;

    void pingForUserUpdate() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICameraServiceProxy {
        @Override // android.hardware.ICameraServiceProxy
        public void pingForUserUpdate() throws RemoteException {
        }

        @Override // android.hardware.ICameraServiceProxy
        public void notifyCameraState(CameraSessionStats cameraSessionStats) throws RemoteException {
        }

        @Override // android.hardware.ICameraServiceProxy
        public boolean isRotateAndCropOverrideNeeded(String packageName, int sensorOrientation, int lensFacing) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICameraServiceProxy {
        public static final String DESCRIPTOR = "android.hardware.ICameraServiceProxy";
        static final int TRANSACTION_isRotateAndCropOverrideNeeded = 3;
        static final int TRANSACTION_notifyCameraState = 2;
        static final int TRANSACTION_pingForUserUpdate = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICameraServiceProxy asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICameraServiceProxy)) {
                return new Proxy(obj);
            }
            return (ICameraServiceProxy) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "pingForUserUpdate";
                case 2:
                    return "notifyCameraState";
                case 3:
                    return "isRotateAndCropOverrideNeeded";
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
            CameraSessionStats _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            pingForUserUpdate();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = CameraSessionStats.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            notifyCameraState(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            boolean isRotateAndCropOverrideNeeded = isRotateAndCropOverrideNeeded(_arg02, _arg1, _arg2);
                            reply.writeNoException();
                            reply.writeInt(isRotateAndCropOverrideNeeded ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICameraServiceProxy {
            public static ICameraServiceProxy sDefaultImpl;
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

            @Override // android.hardware.ICameraServiceProxy
            public void pingForUserUpdate() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pingForUserUpdate();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraServiceProxy
            public void notifyCameraState(CameraSessionStats cameraSessionStats) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cameraSessionStats != null) {
                        _data.writeInt(1);
                        cameraSessionStats.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCameraState(cameraSessionStats);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraServiceProxy
            public boolean isRotateAndCropOverrideNeeded(String packageName, int sensorOrientation, int lensFacing) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(sensorOrientation);
                    _data.writeInt(lensFacing);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRotateAndCropOverrideNeeded(packageName, sensorOrientation, lensFacing);
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
        }

        public static boolean setDefaultImpl(ICameraServiceProxy impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICameraServiceProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
