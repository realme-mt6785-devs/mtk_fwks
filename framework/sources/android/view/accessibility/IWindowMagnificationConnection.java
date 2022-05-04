package android.view.accessibility;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.accessibility.IRemoteMagnificationAnimationCallback;
import android.view.accessibility.IWindowMagnificationConnectionCallback;
/* loaded from: classes3.dex */
public interface IWindowMagnificationConnection extends IInterface {
    public static final String DESCRIPTOR = "android.view.accessibility.IWindowMagnificationConnection";

    void disableWindowMagnification(int i, IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback) throws RemoteException;

    void enableWindowMagnification(int i, float f, float f2, float f3, IRemoteMagnificationAnimationCallback iRemoteMagnificationAnimationCallback) throws RemoteException;

    void moveWindowMagnifier(int i, float f, float f2) throws RemoteException;

    void removeMagnificationButton(int i) throws RemoteException;

    void setConnectionCallback(IWindowMagnificationConnectionCallback iWindowMagnificationConnectionCallback) throws RemoteException;

    void setScale(int i, float f) throws RemoteException;

    void showMagnificationButton(int i, int i2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindowMagnificationConnection {
        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void enableWindowMagnification(int displayId, float scale, float centerX, float centerY, IRemoteMagnificationAnimationCallback callback) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void setScale(int displayId, float scale) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void disableWindowMagnification(int displayId, IRemoteMagnificationAnimationCallback callback) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void moveWindowMagnifier(int displayId, float offsetX, float offsetY) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void showMagnificationButton(int displayId, int magnificationMode) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void removeMagnificationButton(int displayId) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void setConnectionCallback(IWindowMagnificationConnectionCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindowMagnificationConnection {
        static final int TRANSACTION_disableWindowMagnification = 3;
        static final int TRANSACTION_enableWindowMagnification = 1;
        static final int TRANSACTION_moveWindowMagnifier = 4;
        static final int TRANSACTION_removeMagnificationButton = 6;
        static final int TRANSACTION_setConnectionCallback = 7;
        static final int TRANSACTION_setScale = 2;
        static final int TRANSACTION_showMagnificationButton = 5;

        public Stub() {
            attachInterface(this, IWindowMagnificationConnection.DESCRIPTOR);
        }

        public static IWindowMagnificationConnection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IWindowMagnificationConnection.DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowMagnificationConnection)) {
                return new Proxy(obj);
            }
            return (IWindowMagnificationConnection) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "enableWindowMagnification";
                case 2:
                    return "setScale";
                case 3:
                    return "disableWindowMagnification";
                case 4:
                    return "moveWindowMagnifier";
                case 5:
                    return "showMagnificationButton";
                case 6:
                    return "removeMagnificationButton";
                case 7:
                    return "setConnectionCallback";
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
                    reply.writeString(IWindowMagnificationConnection.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IWindowMagnificationConnection.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            float _arg1 = data.readFloat();
                            float _arg2 = data.readFloat();
                            float _arg3 = data.readFloat();
                            IRemoteMagnificationAnimationCallback _arg4 = IRemoteMagnificationAnimationCallback.Stub.asInterface(data.readStrongBinder());
                            enableWindowMagnification(_arg0, _arg1, _arg2, _arg3, _arg4);
                            return true;
                        case 2:
                            data.enforceInterface(IWindowMagnificationConnection.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            float _arg12 = data.readFloat();
                            setScale(_arg02, _arg12);
                            return true;
                        case 3:
                            data.enforceInterface(IWindowMagnificationConnection.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            IRemoteMagnificationAnimationCallback _arg13 = IRemoteMagnificationAnimationCallback.Stub.asInterface(data.readStrongBinder());
                            disableWindowMagnification(_arg03, _arg13);
                            return true;
                        case 4:
                            data.enforceInterface(IWindowMagnificationConnection.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            float _arg14 = data.readFloat();
                            float _arg22 = data.readFloat();
                            moveWindowMagnifier(_arg04, _arg14, _arg22);
                            return true;
                        case 5:
                            data.enforceInterface(IWindowMagnificationConnection.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _arg15 = data.readInt();
                            showMagnificationButton(_arg05, _arg15);
                            return true;
                        case 6:
                            data.enforceInterface(IWindowMagnificationConnection.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            removeMagnificationButton(_arg06);
                            return true;
                        case 7:
                            data.enforceInterface(IWindowMagnificationConnection.DESCRIPTOR);
                            IWindowMagnificationConnectionCallback _arg07 = IWindowMagnificationConnectionCallback.Stub.asInterface(data.readStrongBinder());
                            setConnectionCallback(_arg07);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindowMagnificationConnection {
            public static IWindowMagnificationConnection sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IWindowMagnificationConnection.DESCRIPTOR;
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void enableWindowMagnification(int displayId, float scale, float centerX, float centerY, IRemoteMagnificationAnimationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnection.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeFloat(scale);
                    _data.writeFloat(centerX);
                    _data.writeFloat(centerY);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableWindowMagnification(displayId, scale, centerX, centerY, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void setScale(int displayId, float scale) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnection.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeFloat(scale);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setScale(displayId, scale);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void disableWindowMagnification(int displayId, IRemoteMagnificationAnimationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnection.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableWindowMagnification(displayId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void moveWindowMagnifier(int displayId, float offsetX, float offsetY) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnection.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeFloat(offsetX);
                    _data.writeFloat(offsetY);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().moveWindowMagnifier(displayId, offsetX, offsetY);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void showMagnificationButton(int displayId, int magnificationMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnection.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(magnificationMode);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showMagnificationButton(displayId, magnificationMode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void removeMagnificationButton(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnection.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeMagnificationButton(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void setConnectionCallback(IWindowMagnificationConnectionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowMagnificationConnection.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setConnectionCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowMagnificationConnection impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowMagnificationConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
