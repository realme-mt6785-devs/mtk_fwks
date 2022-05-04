package android.view;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IScrollCaptureCallbacks extends IInterface {
    public static final String DESCRIPTOR = "android.view.IScrollCaptureCallbacks";

    void onCaptureEnded() throws RemoteException;

    void onCaptureStarted() throws RemoteException;

    void onImageRequestCompleted(int i, Rect rect) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IScrollCaptureCallbacks {
        @Override // android.view.IScrollCaptureCallbacks
        public void onCaptureStarted() throws RemoteException {
        }

        @Override // android.view.IScrollCaptureCallbacks
        public void onImageRequestCompleted(int flags, Rect capturedArea) throws RemoteException {
        }

        @Override // android.view.IScrollCaptureCallbacks
        public void onCaptureEnded() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IScrollCaptureCallbacks {
        static final int TRANSACTION_onCaptureEnded = 3;
        static final int TRANSACTION_onCaptureStarted = 1;
        static final int TRANSACTION_onImageRequestCompleted = 2;

        public Stub() {
            attachInterface(this, IScrollCaptureCallbacks.DESCRIPTOR);
        }

        public static IScrollCaptureCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IScrollCaptureCallbacks.DESCRIPTOR);
            if (iin == null || !(iin instanceof IScrollCaptureCallbacks)) {
                return new Proxy(obj);
            }
            return (IScrollCaptureCallbacks) iin;
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
                    return "onImageRequestCompleted";
                case 3:
                    return "onCaptureEnded";
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
            Rect _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IScrollCaptureCallbacks.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IScrollCaptureCallbacks.DESCRIPTOR);
                            onCaptureStarted();
                            return true;
                        case 2:
                            data.enforceInterface(IScrollCaptureCallbacks.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onImageRequestCompleted(_arg0, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IScrollCaptureCallbacks.DESCRIPTOR);
                            onCaptureEnded();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IScrollCaptureCallbacks {
            public static IScrollCaptureCallbacks sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IScrollCaptureCallbacks.DESCRIPTOR;
            }

            @Override // android.view.IScrollCaptureCallbacks
            public void onCaptureStarted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureCallbacks.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCaptureStarted();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IScrollCaptureCallbacks
            public void onImageRequestCompleted(int flags, Rect capturedArea) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureCallbacks.DESCRIPTOR);
                    _data.writeInt(flags);
                    if (capturedArea != null) {
                        _data.writeInt(1);
                        capturedArea.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onImageRequestCompleted(flags, capturedArea);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IScrollCaptureCallbacks
            public void onCaptureEnded() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureCallbacks.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCaptureEnded();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScrollCaptureCallbacks impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IScrollCaptureCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
