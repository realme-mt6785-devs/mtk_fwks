package android.app.contentsuggestions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface ISelectionsCallback extends IInterface {
    public static final String DESCRIPTOR = "android.app.contentsuggestions.ISelectionsCallback";

    void onContentSelectionsAvailable(int i, List<ContentSelection> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISelectionsCallback {
        @Override // android.app.contentsuggestions.ISelectionsCallback
        public void onContentSelectionsAvailable(int statusCode, List<ContentSelection> selections) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISelectionsCallback {
        static final int TRANSACTION_onContentSelectionsAvailable = 1;

        public Stub() {
            attachInterface(this, ISelectionsCallback.DESCRIPTOR);
        }

        public static ISelectionsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISelectionsCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISelectionsCallback)) {
                return new Proxy(obj);
            }
            return (ISelectionsCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onContentSelectionsAvailable";
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
                    reply.writeString(ISelectionsCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISelectionsCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            List<ContentSelection> _arg1 = data.createTypedArrayList(ContentSelection.CREATOR);
                            onContentSelectionsAvailable(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISelectionsCallback {
            public static ISelectionsCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISelectionsCallback.DESCRIPTOR;
            }

            @Override // android.app.contentsuggestions.ISelectionsCallback
            public void onContentSelectionsAvailable(int statusCode, List<ContentSelection> selections) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISelectionsCallback.DESCRIPTOR);
                    _data.writeInt(statusCode);
                    _data.writeTypedList(selections);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onContentSelectionsAvailable(statusCode, selections);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISelectionsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISelectionsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
