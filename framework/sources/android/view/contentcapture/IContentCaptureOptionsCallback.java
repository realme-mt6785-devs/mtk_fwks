package android.view.contentcapture;

import android.content.ContentCaptureOptions;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IContentCaptureOptionsCallback extends IInterface {
    public static final String DESCRIPTOR = "android.view.contentcapture.IContentCaptureOptionsCallback";

    void setContentCaptureOptions(ContentCaptureOptions contentCaptureOptions) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IContentCaptureOptionsCallback {
        @Override // android.view.contentcapture.IContentCaptureOptionsCallback
        public void setContentCaptureOptions(ContentCaptureOptions options) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IContentCaptureOptionsCallback {
        static final int TRANSACTION_setContentCaptureOptions = 1;

        public Stub() {
            attachInterface(this, IContentCaptureOptionsCallback.DESCRIPTOR);
        }

        public static IContentCaptureOptionsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IContentCaptureOptionsCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IContentCaptureOptionsCallback)) {
                return new Proxy(obj);
            }
            return (IContentCaptureOptionsCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setContentCaptureOptions";
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
            ContentCaptureOptions _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IContentCaptureOptionsCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IContentCaptureOptionsCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ContentCaptureOptions.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            setContentCaptureOptions(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IContentCaptureOptionsCallback {
            public static IContentCaptureOptionsCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IContentCaptureOptionsCallback.DESCRIPTOR;
            }

            @Override // android.view.contentcapture.IContentCaptureOptionsCallback
            public void setContentCaptureOptions(ContentCaptureOptions options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureOptionsCallback.DESCRIPTOR);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setContentCaptureOptions(options);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentCaptureOptionsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IContentCaptureOptionsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
