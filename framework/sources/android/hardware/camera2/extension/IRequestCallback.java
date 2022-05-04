package android.hardware.camera2.extension;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IRequestCallback extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.IRequestCallback";

    void onCaptureBufferLost(int i, long j, int i2) throws RemoteException;

    void onCaptureCompleted(int i, ParcelTotalCaptureResult parcelTotalCaptureResult) throws RemoteException;

    void onCaptureFailed(int i, CaptureFailure captureFailure) throws RemoteException;

    void onCaptureProgressed(int i, ParcelCaptureResult parcelCaptureResult) throws RemoteException;

    void onCaptureSequenceAborted(int i) throws RemoteException;

    void onCaptureSequenceCompleted(int i, long j) throws RemoteException;

    void onCaptureStarted(int i, long j, long j2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IRequestCallback {
        @Override // android.hardware.camera2.extension.IRequestCallback
        public void onCaptureStarted(int requestId, long frameNumber, long timestamp) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestCallback
        public void onCaptureProgressed(int requestId, ParcelCaptureResult partialResult) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestCallback
        public void onCaptureCompleted(int requestId, ParcelTotalCaptureResult totalCaptureResult) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestCallback
        public void onCaptureFailed(int requestId, CaptureFailure captureFailure) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestCallback
        public void onCaptureBufferLost(int requestId, long frameNumber, int outputStreamId) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestCallback
        public void onCaptureSequenceCompleted(int sequenceId, long frameNumber) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestCallback
        public void onCaptureSequenceAborted(int sequenceId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRequestCallback {
        static final int TRANSACTION_onCaptureBufferLost = 5;
        static final int TRANSACTION_onCaptureCompleted = 3;
        static final int TRANSACTION_onCaptureFailed = 4;
        static final int TRANSACTION_onCaptureProgressed = 2;
        static final int TRANSACTION_onCaptureSequenceAborted = 7;
        static final int TRANSACTION_onCaptureSequenceCompleted = 6;
        static final int TRANSACTION_onCaptureStarted = 1;

        public Stub() {
            attachInterface(this, IRequestCallback.DESCRIPTOR);
        }

        public static IRequestCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRequestCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRequestCallback)) {
                return new Proxy(obj);
            }
            return (IRequestCallback) iin;
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
                    return "onCaptureProgressed";
                case 3:
                    return "onCaptureCompleted";
                case 4:
                    return "onCaptureFailed";
                case 5:
                    return "onCaptureBufferLost";
                case 6:
                    return "onCaptureSequenceCompleted";
                case 7:
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
            ParcelCaptureResult _arg1;
            ParcelTotalCaptureResult _arg12;
            CaptureFailure _arg13;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRequestCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRequestCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            long _arg14 = data.readLong();
                            long _arg2 = data.readLong();
                            onCaptureStarted(_arg0, _arg14, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IRequestCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = ParcelCaptureResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onCaptureProgressed(_arg02, _arg1);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IRequestCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = ParcelTotalCaptureResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onCaptureCompleted(_arg03, _arg12);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IRequestCallback.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = CaptureFailure.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            onCaptureFailed(_arg04, _arg13);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IRequestCallback.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            long _arg15 = data.readLong();
                            int _arg22 = data.readInt();
                            onCaptureBufferLost(_arg05, _arg15, _arg22);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IRequestCallback.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            long _arg16 = data.readLong();
                            onCaptureSequenceCompleted(_arg06, _arg16);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IRequestCallback.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            onCaptureSequenceAborted(_arg07);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IRequestCallback {
            public static IRequestCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRequestCallback.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.IRequestCallback
            public void onCaptureStarted(int requestId, long frameNumber, long timestamp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    _data.writeLong(frameNumber);
                    _data.writeLong(timestamp);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureStarted(requestId, frameNumber, timestamp);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestCallback
            public void onCaptureProgressed(int requestId, ParcelCaptureResult partialResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (partialResult != null) {
                        _data.writeInt(1);
                        partialResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureProgressed(requestId, partialResult);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestCallback
            public void onCaptureCompleted(int requestId, ParcelTotalCaptureResult totalCaptureResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (totalCaptureResult != null) {
                        _data.writeInt(1);
                        totalCaptureResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureCompleted(requestId, totalCaptureResult);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestCallback
            public void onCaptureFailed(int requestId, CaptureFailure captureFailure) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (captureFailure != null) {
                        _data.writeInt(1);
                        captureFailure.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureFailed(requestId, captureFailure);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestCallback
            public void onCaptureBufferLost(int requestId, long frameNumber, int outputStreamId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    _data.writeLong(frameNumber);
                    _data.writeInt(outputStreamId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureBufferLost(requestId, frameNumber, outputStreamId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestCallback
            public void onCaptureSequenceCompleted(int sequenceId, long frameNumber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestCallback.DESCRIPTOR);
                    _data.writeInt(sequenceId);
                    _data.writeLong(frameNumber);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureSequenceCompleted(sequenceId, frameNumber);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestCallback
            public void onCaptureSequenceAborted(int sequenceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestCallback.DESCRIPTOR);
                    _data.writeInt(sequenceId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureSequenceAborted(sequenceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRequestCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRequestCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
