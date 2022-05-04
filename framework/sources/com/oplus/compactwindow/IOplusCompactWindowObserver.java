package com.oplus.compactwindow;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusCompactWindowObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.compactwindow.IOplusCompactWindowObserver";

    void onCompactWindowDied(String str) throws RemoteException;

    void onCompactWindowExit(OplusCompactWindowInfo oplusCompactWindowInfo) throws RemoteException;

    void onCompactWindowStart(OplusCompactWindowInfo oplusCompactWindowInfo) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusCompactWindowObserver {
        @Override // com.oplus.compactwindow.IOplusCompactWindowObserver
        public void onCompactWindowStart(OplusCompactWindowInfo info) throws RemoteException {
        }

        @Override // com.oplus.compactwindow.IOplusCompactWindowObserver
        public void onCompactWindowExit(OplusCompactWindowInfo info) throws RemoteException {
        }

        @Override // com.oplus.compactwindow.IOplusCompactWindowObserver
        public void onCompactWindowDied(String appName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusCompactWindowObserver {
        static final int TRANSACTION_onCompactWindowDied = 3;
        static final int TRANSACTION_onCompactWindowExit = 2;
        static final int TRANSACTION_onCompactWindowStart = 1;

        public Stub() {
            attachInterface(this, IOplusCompactWindowObserver.DESCRIPTOR);
        }

        public static IOplusCompactWindowObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusCompactWindowObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusCompactWindowObserver)) {
                return new Proxy(obj);
            }
            return (IOplusCompactWindowObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCompactWindowStart";
                case 2:
                    return "onCompactWindowExit";
                case 3:
                    return "onCompactWindowDied";
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
            OplusCompactWindowInfo _arg0;
            OplusCompactWindowInfo _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusCompactWindowObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusCompactWindowObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusCompactWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onCompactWindowStart(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusCompactWindowObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = OplusCompactWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onCompactWindowExit(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusCompactWindowObserver.DESCRIPTOR);
                            String _arg03 = data.readString();
                            onCompactWindowDied(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusCompactWindowObserver {
            public static IOplusCompactWindowObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusCompactWindowObserver.DESCRIPTOR;
            }

            @Override // com.oplus.compactwindow.IOplusCompactWindowObserver
            public void onCompactWindowStart(OplusCompactWindowInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusCompactWindowObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCompactWindowStart(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.compactwindow.IOplusCompactWindowObserver
            public void onCompactWindowExit(OplusCompactWindowInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusCompactWindowObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCompactWindowExit(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.compactwindow.IOplusCompactWindowObserver
            public void onCompactWindowDied(String appName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusCompactWindowObserver.DESCRIPTOR);
                    _data.writeString(appName);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCompactWindowDied(appName);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusCompactWindowObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusCompactWindowObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
