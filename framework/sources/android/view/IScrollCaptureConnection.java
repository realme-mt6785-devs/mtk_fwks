package android.view;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IScrollCaptureCallbacks;
/* loaded from: classes3.dex */
public interface IScrollCaptureConnection extends IInterface {
    public static final String DESCRIPTOR = "android.view.IScrollCaptureConnection";

    void close() throws RemoteException;

    ICancellationSignal endCapture() throws RemoteException;

    ICancellationSignal requestImage(Rect rect) throws RemoteException;

    ICancellationSignal startCapture(Surface surface, IScrollCaptureCallbacks iScrollCaptureCallbacks) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IScrollCaptureConnection {
        @Override // android.view.IScrollCaptureConnection
        public ICancellationSignal startCapture(Surface surface, IScrollCaptureCallbacks callbacks) throws RemoteException {
            return null;
        }

        @Override // android.view.IScrollCaptureConnection
        public ICancellationSignal requestImage(Rect captureArea) throws RemoteException {
            return null;
        }

        @Override // android.view.IScrollCaptureConnection
        public ICancellationSignal endCapture() throws RemoteException {
            return null;
        }

        @Override // android.view.IScrollCaptureConnection
        public void close() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IScrollCaptureConnection {
        static final int TRANSACTION_close = 4;
        static final int TRANSACTION_endCapture = 3;
        static final int TRANSACTION_requestImage = 2;
        static final int TRANSACTION_startCapture = 1;

        public Stub() {
            attachInterface(this, IScrollCaptureConnection.DESCRIPTOR);
        }

        public static IScrollCaptureConnection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IScrollCaptureConnection.DESCRIPTOR);
            if (iin == null || !(iin instanceof IScrollCaptureConnection)) {
                return new Proxy(obj);
            }
            return (IScrollCaptureConnection) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startCapture";
                case 2:
                    return "requestImage";
                case 3:
                    return "endCapture";
                case 4:
                    return "close";
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
            Surface _arg0;
            Rect _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IScrollCaptureConnection.DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IScrollCaptureConnection.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Surface.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IScrollCaptureCallbacks _arg1 = IScrollCaptureCallbacks.Stub.asInterface(data.readStrongBinder());
                            ICancellationSignal _result = startCapture(_arg0, _arg1);
                            reply.writeNoException();
                            if (_result != null) {
                                iBinder = _result.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 2:
                            data.enforceInterface(IScrollCaptureConnection.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            ICancellationSignal _result2 = requestImage(_arg02);
                            reply.writeNoException();
                            if (_result2 != null) {
                                iBinder = _result2.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 3:
                            data.enforceInterface(IScrollCaptureConnection.DESCRIPTOR);
                            ICancellationSignal _result3 = endCapture();
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 4:
                            data.enforceInterface(IScrollCaptureConnection.DESCRIPTOR);
                            close();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IScrollCaptureConnection {
            public static IScrollCaptureConnection sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IScrollCaptureConnection.DESCRIPTOR;
            }

            @Override // android.view.IScrollCaptureConnection
            public ICancellationSignal startCapture(Surface surface, IScrollCaptureCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureConnection.DESCRIPTOR);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startCapture(surface, callbacks);
                    }
                    _reply.readException();
                    ICancellationSignal _result = ICancellationSignal.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IScrollCaptureConnection
            public ICancellationSignal requestImage(Rect captureArea) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureConnection.DESCRIPTOR);
                    if (captureArea != null) {
                        _data.writeInt(1);
                        captureArea.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestImage(captureArea);
                    }
                    _reply.readException();
                    ICancellationSignal _result = ICancellationSignal.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IScrollCaptureConnection
            public ICancellationSignal endCapture() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureConnection.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().endCapture();
                    }
                    _reply.readException();
                    ICancellationSignal _result = ICancellationSignal.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IScrollCaptureConnection
            public void close() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureConnection.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().close();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScrollCaptureConnection impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IScrollCaptureConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
