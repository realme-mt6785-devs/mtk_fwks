package com.oplus.app;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusGameSpaceController extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusGameSpaceController";

    void dispatchGameDock(Bundle bundle) throws RemoteException;

    void gameExiting(String str) throws RemoteException;

    void gameStarting(Intent intent, String str, boolean z) throws RemoteException;

    boolean isGameDockAllowed() throws RemoteException;

    void videoStarting(Intent intent, String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusGameSpaceController {
        @Override // com.oplus.app.IOplusGameSpaceController
        public void gameStarting(Intent intent, String pkg, boolean isResume) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusGameSpaceController
        public void gameExiting(String pkg) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusGameSpaceController
        public void videoStarting(Intent intent, String pkg) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusGameSpaceController
        public void dispatchGameDock(Bundle bundle) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusGameSpaceController
        public boolean isGameDockAllowed() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusGameSpaceController {
        static final int TRANSACTION_dispatchGameDock = 4;
        static final int TRANSACTION_gameExiting = 2;
        static final int TRANSACTION_gameStarting = 1;
        static final int TRANSACTION_isGameDockAllowed = 5;
        static final int TRANSACTION_videoStarting = 3;

        public Stub() {
            attachInterface(this, IOplusGameSpaceController.DESCRIPTOR);
        }

        public static IOplusGameSpaceController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusGameSpaceController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusGameSpaceController)) {
                return new Proxy(obj);
            }
            return (IOplusGameSpaceController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "gameStarting";
                case 2:
                    return "gameExiting";
                case 3:
                    return "videoStarting";
                case 4:
                    return "dispatchGameDock";
                case 5:
                    return "isGameDockAllowed";
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
            Intent _arg0;
            Intent _arg02;
            Bundle _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusGameSpaceController.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusGameSpaceController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg1 = data.readString();
                            boolean _arg2 = data.readInt() != 0;
                            gameStarting(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusGameSpaceController.DESCRIPTOR);
                            String _arg04 = data.readString();
                            gameExiting(_arg04);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusGameSpaceController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _arg12 = data.readString();
                            videoStarting(_arg02, _arg12);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusGameSpaceController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            dispatchGameDock(_arg03);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusGameSpaceController.DESCRIPTOR);
                            boolean isGameDockAllowed = isGameDockAllowed();
                            reply.writeNoException();
                            reply.writeInt(isGameDockAllowed ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusGameSpaceController {
            public static IOplusGameSpaceController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusGameSpaceController.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusGameSpaceController
            public void gameStarting(Intent intent, String pkg, boolean isResume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGameSpaceController.DESCRIPTOR);
                    int i = 0;
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(pkg);
                    if (isResume) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().gameStarting(intent, pkg, isResume);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusGameSpaceController
            public void gameExiting(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGameSpaceController.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().gameExiting(pkg);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusGameSpaceController
            public void videoStarting(Intent intent, String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGameSpaceController.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().videoStarting(intent, pkg);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusGameSpaceController
            public void dispatchGameDock(Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGameSpaceController.DESCRIPTOR);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchGameDock(bundle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusGameSpaceController
            public boolean isGameDockAllowed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGameSpaceController.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGameDockAllowed();
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

        public static boolean setDefaultImpl(IOplusGameSpaceController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusGameSpaceController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
