package com.oplus.splitscreen;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusStackDividerConnection extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.splitscreen.IOplusStackDividerConnection";

    void notifyPairRemoteAnimationStart(boolean z) throws RemoteException;

    void prepareEnterSplitParams(Bundle bundle) throws RemoteException;

    void setDividerVisible(boolean z) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusStackDividerConnection {
        @Override // com.oplus.splitscreen.IOplusStackDividerConnection
        public void prepareEnterSplitParams(Bundle bundle) throws RemoteException {
        }

        @Override // com.oplus.splitscreen.IOplusStackDividerConnection
        public void setDividerVisible(boolean visible) throws RemoteException {
        }

        @Override // com.oplus.splitscreen.IOplusStackDividerConnection
        public void notifyPairRemoteAnimationStart(boolean isOpening) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusStackDividerConnection {
        static final int TRANSACTION_notifyPairRemoteAnimationStart = 3;
        static final int TRANSACTION_prepareEnterSplitParams = 1;
        static final int TRANSACTION_setDividerVisible = 2;

        public Stub() {
            attachInterface(this, IOplusStackDividerConnection.DESCRIPTOR);
        }

        public static IOplusStackDividerConnection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusStackDividerConnection.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusStackDividerConnection)) {
                return new Proxy(obj);
            }
            return (IOplusStackDividerConnection) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "prepareEnterSplitParams";
                case 2:
                    return "setDividerVisible";
                case 3:
                    return "notifyPairRemoteAnimationStart";
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
            Bundle _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusStackDividerConnection.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg02 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusStackDividerConnection.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            prepareEnterSplitParams(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IOplusStackDividerConnection.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = true;
                            }
                            setDividerVisible(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IOplusStackDividerConnection.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = true;
                            }
                            notifyPairRemoteAnimationStart(_arg02);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusStackDividerConnection {
            public static IOplusStackDividerConnection sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusStackDividerConnection.DESCRIPTOR;
            }

            @Override // com.oplus.splitscreen.IOplusStackDividerConnection
            public void prepareEnterSplitParams(Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStackDividerConnection.DESCRIPTOR);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().prepareEnterSplitParams(bundle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.splitscreen.IOplusStackDividerConnection
            public void setDividerVisible(boolean visible) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStackDividerConnection.DESCRIPTOR);
                    _data.writeInt(visible ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDividerVisible(visible);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.splitscreen.IOplusStackDividerConnection
            public void notifyPairRemoteAnimationStart(boolean isOpening) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStackDividerConnection.DESCRIPTOR);
                    _data.writeInt(isOpening ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyPairRemoteAnimationStart(isOpening);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusStackDividerConnection impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusStackDividerConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
