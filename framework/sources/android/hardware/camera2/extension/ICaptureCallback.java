package android.hardware.camera2.extension;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICaptureCallback extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.ICaptureCallback";

    void onCaptureFailed(int i) throws RemoteException;

    void onCaptureProcessStarted(int i) throws RemoteException;

    void onCaptureSequenceAborted(int i) throws RemoteException;

    void onCaptureSequenceCompleted(int i) throws RemoteException;

    void onCaptureStarted(int i, long j) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICaptureCallback {
        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureStarted(int captureSequenceId, long timestamp) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureProcessStarted(int captureSequenceId) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureFailed(int captureSequenceId) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureSequenceCompleted(int captureSequenceId) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureSequenceAborted(int captureSequenceId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICaptureCallback {
        static final int TRANSACTION_onCaptureFailed = 3;
        static final int TRANSACTION_onCaptureProcessStarted = 2;
        static final int TRANSACTION_onCaptureSequenceAborted = 5;
        static final int TRANSACTION_onCaptureSequenceCompleted = 4;
        static final int TRANSACTION_onCaptureStarted = 1;

        public Stub() {
            attachInterface(this, ICaptureCallback.DESCRIPTOR);
        }

        public static ICaptureCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICaptureCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICaptureCallback)) {
                return new Proxy(obj);
            }
            return (ICaptureCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCaptureStarted";
                case 2:
                    return "onCaptureProcessStarted";
                case 3:
                    return "onCaptureFailed";
                case 4:
                    return "onCaptureSequenceCompleted";
                case 5:
                    return "onCaptureSequenceAborted";
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
                    reply.writeString(ICaptureCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICaptureCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            long _arg1 = data.readLong();
                            onCaptureStarted(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(ICaptureCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onCaptureProcessStarted(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ICaptureCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onCaptureFailed(_arg03);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ICaptureCallback.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            onCaptureSequenceCompleted(_arg04);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ICaptureCallback.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onCaptureSequenceAborted(_arg05);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICaptureCallback {
            public static ICaptureCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICaptureCallback.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.ICaptureCallback
            public void onCaptureStarted(int captureSequenceId, long timestamp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICaptureCallback.DESCRIPTOR);
                    _data.writeInt(captureSequenceId);
                    _data.writeLong(timestamp);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureStarted(captureSequenceId, timestamp);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICaptureCallback
            public void onCaptureProcessStarted(int captureSequenceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICaptureCallback.DESCRIPTOR);
                    _data.writeInt(captureSequenceId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureProcessStarted(captureSequenceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICaptureCallback
            public void onCaptureFailed(int captureSequenceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICaptureCallback.DESCRIPTOR);
                    _data.writeInt(captureSequenceId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureFailed(captureSequenceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICaptureCallback
            public void onCaptureSequenceCompleted(int captureSequenceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICaptureCallback.DESCRIPTOR);
                    _data.writeInt(captureSequenceId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureSequenceCompleted(captureSequenceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICaptureCallback
            public void onCaptureSequenceAborted(int captureSequenceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICaptureCallback.DESCRIPTOR);
                    _data.writeInt(captureSequenceId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureSequenceAborted(captureSequenceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICaptureCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICaptureCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
