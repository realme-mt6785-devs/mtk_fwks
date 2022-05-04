package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IOnProjectionStateChangedListener extends IInterface {
    public static final String DESCRIPTOR = "android.app.IOnProjectionStateChangedListener";

    void onProjectionStateChanged(int i, List<String> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IOnProjectionStateChangedListener {
        @Override // android.app.IOnProjectionStateChangedListener
        public void onProjectionStateChanged(int activeProjectionTypes, List<String> projectingPackages) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IOnProjectionStateChangedListener {
        static final int TRANSACTION_onProjectionStateChanged = 1;

        public Stub() {
            attachInterface(this, IOnProjectionStateChangedListener.DESCRIPTOR);
        }

        public static IOnProjectionStateChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOnProjectionStateChangedListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOnProjectionStateChangedListener)) {
                return new Proxy(obj);
            }
            return (IOnProjectionStateChangedListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onProjectionStateChanged";
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
                    reply.writeString(IOnProjectionStateChangedListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOnProjectionStateChangedListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            List<String> _arg1 = data.createStringArrayList();
                            onProjectionStateChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IOnProjectionStateChangedListener {
            public static IOnProjectionStateChangedListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnProjectionStateChangedListener.DESCRIPTOR;
            }

            @Override // android.app.IOnProjectionStateChangedListener
            public void onProjectionStateChanged(int activeProjectionTypes, List<String> projectingPackages) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOnProjectionStateChangedListener.DESCRIPTOR);
                    _data.writeInt(activeProjectionTypes);
                    _data.writeStringList(projectingPackages);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProjectionStateChanged(activeProjectionTypes, projectingPackages);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOnProjectionStateChangedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOnProjectionStateChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
