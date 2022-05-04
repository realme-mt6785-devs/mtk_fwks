package com.oplus.eap;

import android.app.ApplicationExitInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SharedMemory;
/* loaded from: classes4.dex */
public interface IOplusEapDataCallback extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.eap.IOplusEapDataCallback";

    void onAppCrashed(SharedMemory sharedMemory) throws RemoteException;

    void onExitInfoRecordAdded(ApplicationExitInfo applicationExitInfo) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusEapDataCallback {
        @Override // com.oplus.eap.IOplusEapDataCallback
        public void onAppCrashed(SharedMemory data) throws RemoteException {
        }

        @Override // com.oplus.eap.IOplusEapDataCallback
        public void onExitInfoRecordAdded(ApplicationExitInfo exitInfo) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusEapDataCallback {
        static final int TRANSACTION_onAppCrashed = 1;
        static final int TRANSACTION_onExitInfoRecordAdded = 2;

        public Stub() {
            attachInterface(this, IOplusEapDataCallback.DESCRIPTOR);
        }

        public static IOplusEapDataCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusEapDataCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusEapDataCallback)) {
                return new Proxy(obj);
            }
            return (IOplusEapDataCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAppCrashed";
                case 2:
                    return "onExitInfoRecordAdded";
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
            SharedMemory _arg0;
            ApplicationExitInfo _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusEapDataCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusEapDataCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SharedMemory.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onAppCrashed(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusEapDataCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ApplicationExitInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onExitInfoRecordAdded(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusEapDataCallback {
            public static IOplusEapDataCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusEapDataCallback.DESCRIPTOR;
            }

            @Override // com.oplus.eap.IOplusEapDataCallback
            public void onAppCrashed(SharedMemory data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusEapDataCallback.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppCrashed(data);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.eap.IOplusEapDataCallback
            public void onExitInfoRecordAdded(ApplicationExitInfo exitInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusEapDataCallback.DESCRIPTOR);
                    if (exitInfo != null) {
                        _data.writeInt(1);
                        exitInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onExitInfoRecordAdded(exitInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusEapDataCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusEapDataCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
