package com.oplus.miragewindow;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusMirageWindowObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.miragewindow.IOplusMirageWindowObserver";

    void onMirageDisplayTopActivityUidChanged(int i) throws RemoteException;

    void onMirageWindConfigChanged(OplusMirageWindowInfo oplusMirageWindowInfo) throws RemoteException;

    void onMirageWindowDied(String str) throws RemoteException;

    void onMirageWindowExit(OplusMirageWindowInfo oplusMirageWindowInfo) throws RemoteException;

    void onMirageWindowShow(OplusMirageWindowInfo oplusMirageWindowInfo) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusMirageWindowObserver {
        @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
        public void onMirageWindowShow(OplusMirageWindowInfo info) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
        public void onMirageWindowExit(OplusMirageWindowInfo info) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
        public void onMirageWindowDied(String cpnName) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
        public void onMirageWindConfigChanged(OplusMirageWindowInfo info) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
        public void onMirageDisplayTopActivityUidChanged(int uid) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusMirageWindowObserver {
        static final int TRANSACTION_onMirageDisplayTopActivityUidChanged = 5;
        static final int TRANSACTION_onMirageWindConfigChanged = 4;
        static final int TRANSACTION_onMirageWindowDied = 3;
        static final int TRANSACTION_onMirageWindowExit = 2;
        static final int TRANSACTION_onMirageWindowShow = 1;

        public Stub() {
            attachInterface(this, IOplusMirageWindowObserver.DESCRIPTOR);
        }

        public static IOplusMirageWindowObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusMirageWindowObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusMirageWindowObserver)) {
                return new Proxy(obj);
            }
            return (IOplusMirageWindowObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onMirageWindowShow";
                case 2:
                    return "onMirageWindowExit";
                case 3:
                    return "onMirageWindowDied";
                case 4:
                    return "onMirageWindConfigChanged";
                case 5:
                    return "onMirageDisplayTopActivityUidChanged";
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
            OplusMirageWindowInfo _arg0;
            OplusMirageWindowInfo _arg02;
            OplusMirageWindowInfo _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusMirageWindowObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusMirageWindowObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusMirageWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onMirageWindowShow(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusMirageWindowObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = OplusMirageWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onMirageWindowExit(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusMirageWindowObserver.DESCRIPTOR);
                            String _arg04 = data.readString();
                            onMirageWindowDied(_arg04);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusMirageWindowObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = OplusMirageWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onMirageWindConfigChanged(_arg03);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusMirageWindowObserver.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onMirageDisplayTopActivityUidChanged(_arg05);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusMirageWindowObserver {
            public static IOplusMirageWindowObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusMirageWindowObserver.DESCRIPTOR;
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
            public void onMirageWindowShow(OplusMirageWindowInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageWindowShow(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
            public void onMirageWindowExit(OplusMirageWindowInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageWindowExit(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
            public void onMirageWindowDied(String cpnName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowObserver.DESCRIPTOR);
                    _data.writeString(cpnName);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageWindowDied(cpnName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
            public void onMirageWindConfigChanged(OplusMirageWindowInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageWindConfigChanged(info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageWindowObserver
            public void onMirageDisplayTopActivityUidChanged(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageWindowObserver.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageDisplayTopActivityUidChanged(uid);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusMirageWindowObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusMirageWindowObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
