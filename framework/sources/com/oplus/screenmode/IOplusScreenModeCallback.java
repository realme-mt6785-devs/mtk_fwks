package com.oplus.screenmode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusScreenModeCallback extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.screenmode.IOplusScreenModeCallback";

    void OnNotificationChange(boolean z) throws RemoteException;

    void notifyMemcStatus(boolean z) throws RemoteException;

    void notifyRequestNewRefresh(int i, boolean z, int i2) throws RemoteException;

    void requestRefreshRate(String str, int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusScreenModeCallback {
        @Override // com.oplus.screenmode.IOplusScreenModeCallback
        public void requestRefreshRate(String pkg, int rate) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenModeCallback
        public void OnNotificationChange(boolean enable) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenModeCallback
        public void notifyRequestNewRefresh(int priority, boolean open, int rate) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenModeCallback
        public void notifyMemcStatus(boolean memc) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusScreenModeCallback {
        static final int TRANSACTION_OnNotificationChange = 2;
        static final int TRANSACTION_notifyMemcStatus = 4;
        static final int TRANSACTION_notifyRequestNewRefresh = 3;
        static final int TRANSACTION_requestRefreshRate = 1;

        public Stub() {
            attachInterface(this, IOplusScreenModeCallback.DESCRIPTOR);
        }

        public static IOplusScreenModeCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusScreenModeCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusScreenModeCallback)) {
                return new Proxy(obj);
            }
            return (IOplusScreenModeCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "requestRefreshRate";
                case 2:
                    return "OnNotificationChange";
                case 3:
                    return "notifyRequestNewRefresh";
                case 4:
                    return "notifyMemcStatus";
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
                    reply.writeString(IOplusScreenModeCallback.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg0 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusScreenModeCallback.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg1 = data.readInt();
                            requestRefreshRate(_arg02, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusScreenModeCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            OnNotificationChange(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusScreenModeCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            int _arg2 = data.readInt();
                            notifyRequestNewRefresh(_arg03, _arg0, _arg2);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusScreenModeCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            notifyMemcStatus(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusScreenModeCallback {
            public static IOplusScreenModeCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusScreenModeCallback.DESCRIPTOR;
            }

            @Override // com.oplus.screenmode.IOplusScreenModeCallback
            public void requestRefreshRate(String pkg, int rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenModeCallback.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(rate);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestRefreshRate(pkg, rate);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenModeCallback
            public void OnNotificationChange(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenModeCallback.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnNotificationChange(enable);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenModeCallback
            public void notifyRequestNewRefresh(int priority, boolean open, int rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenModeCallback.DESCRIPTOR);
                    _data.writeInt(priority);
                    _data.writeInt(open ? 1 : 0);
                    _data.writeInt(rate);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRequestNewRefresh(priority, open, rate);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenModeCallback
            public void notifyMemcStatus(boolean memc) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenModeCallback.DESCRIPTOR);
                    _data.writeInt(memc ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMemcStatus(memc);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusScreenModeCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusScreenModeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
