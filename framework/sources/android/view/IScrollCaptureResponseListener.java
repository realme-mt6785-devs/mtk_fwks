package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IScrollCaptureResponseListener extends IInterface {
    public static final String DESCRIPTOR = "android.view.IScrollCaptureResponseListener";

    void onScrollCaptureResponse(ScrollCaptureResponse scrollCaptureResponse) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IScrollCaptureResponseListener {
        @Override // android.view.IScrollCaptureResponseListener
        public void onScrollCaptureResponse(ScrollCaptureResponse response) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IScrollCaptureResponseListener {
        static final int TRANSACTION_onScrollCaptureResponse = 1;

        public Stub() {
            attachInterface(this, IScrollCaptureResponseListener.DESCRIPTOR);
        }

        public static IScrollCaptureResponseListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IScrollCaptureResponseListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IScrollCaptureResponseListener)) {
                return new Proxy(obj);
            }
            return (IScrollCaptureResponseListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onScrollCaptureResponse";
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
            ScrollCaptureResponse _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IScrollCaptureResponseListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IScrollCaptureResponseListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ScrollCaptureResponse.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onScrollCaptureResponse(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IScrollCaptureResponseListener {
            public static IScrollCaptureResponseListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IScrollCaptureResponseListener.DESCRIPTOR;
            }

            @Override // android.view.IScrollCaptureResponseListener
            public void onScrollCaptureResponse(ScrollCaptureResponse response) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IScrollCaptureResponseListener.DESCRIPTOR);
                    if (response != null) {
                        _data.writeInt(1);
                        response.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScrollCaptureResponse(response);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScrollCaptureResponseListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IScrollCaptureResponseListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
