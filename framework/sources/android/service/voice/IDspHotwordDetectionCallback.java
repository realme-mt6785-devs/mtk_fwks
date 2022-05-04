package android.service.voice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IDspHotwordDetectionCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.voice.IDspHotwordDetectionCallback";

    void onDetected(HotwordDetectedResult hotwordDetectedResult) throws RemoteException;

    void onRejected(HotwordRejectedResult hotwordRejectedResult) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDspHotwordDetectionCallback {
        @Override // android.service.voice.IDspHotwordDetectionCallback
        public void onDetected(HotwordDetectedResult hotwordDetectedResult) throws RemoteException {
        }

        @Override // android.service.voice.IDspHotwordDetectionCallback
        public void onRejected(HotwordRejectedResult result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDspHotwordDetectionCallback {
        static final int TRANSACTION_onDetected = 1;
        static final int TRANSACTION_onRejected = 2;

        public Stub() {
            attachInterface(this, IDspHotwordDetectionCallback.DESCRIPTOR);
        }

        public static IDspHotwordDetectionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDspHotwordDetectionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDspHotwordDetectionCallback)) {
                return new Proxy(obj);
            }
            return (IDspHotwordDetectionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDetected";
                case 2:
                    return "onRejected";
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
            HotwordDetectedResult _arg0;
            HotwordRejectedResult _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDspHotwordDetectionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDspHotwordDetectionCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = HotwordDetectedResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onDetected(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IDspHotwordDetectionCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = HotwordRejectedResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onRejected(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDspHotwordDetectionCallback {
            public static IDspHotwordDetectionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDspHotwordDetectionCallback.DESCRIPTOR;
            }

            @Override // android.service.voice.IDspHotwordDetectionCallback
            public void onDetected(HotwordDetectedResult hotwordDetectedResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDspHotwordDetectionCallback.DESCRIPTOR);
                    if (hotwordDetectedResult != null) {
                        _data.writeInt(1);
                        hotwordDetectedResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDetected(hotwordDetectedResult);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.voice.IDspHotwordDetectionCallback
            public void onRejected(HotwordRejectedResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDspHotwordDetectionCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRejected(result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDspHotwordDetectionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDspHotwordDetectionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
