package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusAppSwitchObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusAppSwitchObserver";

    void onActivityEnter(OplusAppEnterInfo oplusAppEnterInfo) throws RemoteException;

    void onActivityExit(OplusAppExitInfo oplusAppExitInfo) throws RemoteException;

    void onAppEnter(OplusAppEnterInfo oplusAppEnterInfo) throws RemoteException;

    void onAppExit(OplusAppExitInfo oplusAppExitInfo) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusAppSwitchObserver {
        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onAppEnter(OplusAppEnterInfo info) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onAppExit(OplusAppExitInfo info) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onActivityEnter(OplusAppEnterInfo info) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onActivityExit(OplusAppExitInfo info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusAppSwitchObserver {
        static final int TRANSACTION_onActivityEnter = 3;
        static final int TRANSACTION_onActivityExit = 4;
        static final int TRANSACTION_onAppEnter = 1;
        static final int TRANSACTION_onAppExit = 2;

        public Stub() {
            attachInterface(this, IOplusAppSwitchObserver.DESCRIPTOR);
        }

        public static IOplusAppSwitchObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusAppSwitchObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusAppSwitchObserver)) {
                return new Proxy(obj);
            }
            return (IOplusAppSwitchObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAppEnter";
                case 2:
                    return "onAppExit";
                case 3:
                    return "onActivityEnter";
                case 4:
                    return "onActivityExit";
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
            OplusAppEnterInfo _arg0;
            OplusAppExitInfo _arg02;
            OplusAppEnterInfo _arg03;
            OplusAppExitInfo _arg04;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusAppSwitchObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusAppSwitchObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusAppEnterInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onAppEnter(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusAppSwitchObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = OplusAppExitInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onAppExit(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusAppSwitchObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = OplusAppEnterInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onActivityEnter(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusAppSwitchObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = OplusAppExitInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            onActivityExit(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusAppSwitchObserver {
            public static IOplusAppSwitchObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusAppSwitchObserver.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusAppSwitchObserver
            public void onAppEnter(OplusAppEnterInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAppSwitchObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppEnter(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAppSwitchObserver
            public void onAppExit(OplusAppExitInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAppSwitchObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppExit(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAppSwitchObserver
            public void onActivityEnter(OplusAppEnterInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAppSwitchObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityEnter(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAppSwitchObserver
            public void onActivityExit(OplusAppExitInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAppSwitchObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityExit(info);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusAppSwitchObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusAppSwitchObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
