package android.view.accessibility;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IWindowMagnificationConnectionCallback extends IInterface {
    public static final String DESCRIPTOR = "android.view.accessibility.IWindowMagnificationConnectionCallback";

    void onAccessibilityActionPerformed(int i) throws RemoteException;

    void onChangeMagnificationMode(int i, int i2) throws RemoteException;

    void onPerformScaleAction(int i, float f) throws RemoteException;

    void onSourceBoundsChanged(int i, Rect rect) throws RemoteException;

    void onWindowMagnifierBoundsChanged(int i, Rect rect) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindowMagnificationConnectionCallback {
        @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
        public void onWindowMagnifierBoundsChanged(int displayId, Rect bounds) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
        public void onChangeMagnificationMode(int displayId, int magnificationMode) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
        public void onSourceBoundsChanged(int displayId, Rect sourceBounds) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
        public void onPerformScaleAction(int displayId, float scale) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
        public void onAccessibilityActionPerformed(int displayId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindowMagnificationConnectionCallback {
        static final int TRANSACTION_onAccessibilityActionPerformed = 5;
        static final int TRANSACTION_onChangeMagnificationMode = 2;
        static final int TRANSACTION_onPerformScaleAction = 4;
        static final int TRANSACTION_onSourceBoundsChanged = 3;
        static final int TRANSACTION_onWindowMagnifierBoundsChanged = 1;

        public Stub() {
            attachInterface(this, IWindowMagnificationConnectionCallback.DESCRIPTOR);
        }

        public static IWindowMagnificationConnectionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IWindowMagnificationConnectionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowMagnificationConnectionCallback)) {
                return new Proxy(obj);
            }
            return (IWindowMagnificationConnectionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onWindowMagnifierBoundsChanged";
                case 2:
                    return "onChangeMagnificationMode";
                case 3:
                    return "onSourceBoundsChanged";
                case 4:
                    return "onPerformScaleAction";
                case 5:
                    return "onAccessibilityActionPerformed";
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
            Rect _arg12;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onWindowMagnifierBoundsChanged(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg13 = data.readInt();
                            onChangeMagnificationMode(_arg02, _arg13);
                            return true;
                        case 3:
                            data.enforceInterface(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onSourceBoundsChanged(_arg03, _arg12);
                            return true;
                        case 4:
                            data.enforceInterface(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            float _arg14 = data.readFloat();
                            onPerformScaleAction(_arg04, _arg14);
                            return true;
                        case 5:
                            data.enforceInterface(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onAccessibilityActionPerformed(_arg05);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindowMagnificationConnectionCallback {
            public static IWindowMagnificationConnectionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IWindowMagnificationConnectionCallback.DESCRIPTOR;
            }

            @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
            public void onWindowMagnifierBoundsChanged(int displayId, Rect bounds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                    _data.writeInt(displayId);
                    if (bounds != null) {
                        _data.writeInt(1);
                        bounds.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWindowMagnifierBoundsChanged(displayId, bounds);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
            public void onChangeMagnificationMode(int displayId, int magnificationMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(magnificationMode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChangeMagnificationMode(displayId, magnificationMode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
            public void onSourceBoundsChanged(int displayId, Rect sourceBounds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                    _data.writeInt(displayId);
                    if (sourceBounds != null) {
                        _data.writeInt(1);
                        sourceBounds.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSourceBoundsChanged(displayId, sourceBounds);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
            public void onPerformScaleAction(int displayId, float scale) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeFloat(scale);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPerformScaleAction(displayId, scale);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnectionCallback
            public void onAccessibilityActionPerformed(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnectionCallback.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAccessibilityActionPerformed(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowMagnificationConnectionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowMagnificationConnectionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
