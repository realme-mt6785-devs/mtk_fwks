package com.oplus.zoomwindow;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusZoomWindowObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.zoomwindow.IOplusZoomWindowObserver";

    void onInputMethodChanged(boolean z) throws RemoteException;

    void onZoomWindowDied(String str) throws RemoteException;

    void onZoomWindowHide(OplusZoomWindowInfo oplusZoomWindowInfo) throws RemoteException;

    void onZoomWindowShow(OplusZoomWindowInfo oplusZoomWindowInfo) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusZoomWindowObserver {
        @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
        public void onZoomWindowShow(OplusZoomWindowInfo info) throws RemoteException {
        }

        @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
        public void onZoomWindowHide(OplusZoomWindowInfo info) throws RemoteException {
        }

        @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
        public void onZoomWindowDied(String appName) throws RemoteException {
        }

        @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
        public void onInputMethodChanged(boolean isShown) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusZoomWindowObserver {
        static final int TRANSACTION_onInputMethodChanged = 4;
        static final int TRANSACTION_onZoomWindowDied = 3;
        static final int TRANSACTION_onZoomWindowHide = 2;
        static final int TRANSACTION_onZoomWindowShow = 1;

        public Stub() {
            attachInterface(this, IOplusZoomWindowObserver.DESCRIPTOR);
        }

        public static IOplusZoomWindowObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusZoomWindowObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusZoomWindowObserver)) {
                return new Proxy(obj);
            }
            return (IOplusZoomWindowObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onZoomWindowShow";
                case 2:
                    return "onZoomWindowHide";
                case 3:
                    return "onZoomWindowDied";
                case 4:
                    return "onInputMethodChanged";
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
            OplusZoomWindowInfo _arg0;
            OplusZoomWindowInfo _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusZoomWindowObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusZoomWindowObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusZoomWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onZoomWindowShow(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusZoomWindowObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = OplusZoomWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onZoomWindowHide(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusZoomWindowObserver.DESCRIPTOR);
                            String _arg03 = data.readString();
                            onZoomWindowDied(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusZoomWindowObserver.DESCRIPTOR);
                            boolean _arg04 = data.readInt() != 0;
                            onInputMethodChanged(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusZoomWindowObserver {
            public static IOplusZoomWindowObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusZoomWindowObserver.DESCRIPTOR;
            }

            @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
            public void onZoomWindowShow(OplusZoomWindowInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusZoomWindowObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onZoomWindowShow(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
            public void onZoomWindowHide(OplusZoomWindowInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusZoomWindowObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onZoomWindowHide(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
            public void onZoomWindowDied(String appName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusZoomWindowObserver.DESCRIPTOR);
                    _data.writeString(appName);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onZoomWindowDied(appName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.zoomwindow.IOplusZoomWindowObserver
            public void onInputMethodChanged(boolean isShown) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusZoomWindowObserver.DESCRIPTOR);
                    _data.writeInt(isShown ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputMethodChanged(isShown);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusZoomWindowObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusZoomWindowObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
