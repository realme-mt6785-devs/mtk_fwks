package com.oplus.eventhub.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IEventCallback extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.eventhub.sdk.aidl.IEventCallback";

    void onEventStateChanged(DeviceEventResult deviceEventResult) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IEventCallback {
        @Override // com.oplus.eventhub.sdk.aidl.IEventCallback
        public void onEventStateChanged(DeviceEventResult deviceEventResult) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IEventCallback {
        static final int TRANSACTION_onEventStateChanged = 1;

        public Stub() {
            attachInterface(this, IEventCallback.DESCRIPTOR);
        }

        public static IEventCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEventCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEventCallback)) {
                return new Proxy(obj);
            }
            return (IEventCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onEventStateChanged";
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
            DeviceEventResult _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IEventCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEventCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = DeviceEventResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onEventStateChanged(_arg0);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IEventCallback {
            public static IEventCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEventCallback.DESCRIPTOR;
            }

            @Override // com.oplus.eventhub.sdk.aidl.IEventCallback
            public void onEventStateChanged(DeviceEventResult deviceEventResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEventCallback.DESCRIPTOR);
                    if (deviceEventResult != null) {
                        _data.writeInt(1);
                        deviceEventResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onEventStateChanged(deviceEventResult);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEventCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
