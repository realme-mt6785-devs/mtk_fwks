package android.app;

import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IWindowToken extends IInterface {
    public static final String DESCRIPTOR = "android.app.IWindowToken";

    void onConfigurationChanged(Configuration configuration, int i) throws RemoteException;

    void onWindowTokenRemoved() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IWindowToken {
        @Override // android.app.IWindowToken
        public void onConfigurationChanged(Configuration newConfig, int newDisplayId) throws RemoteException {
        }

        @Override // android.app.IWindowToken
        public void onWindowTokenRemoved() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IWindowToken {
        static final int TRANSACTION_onConfigurationChanged = 1;
        static final int TRANSACTION_onWindowTokenRemoved = 2;

        public Stub() {
            attachInterface(this, IWindowToken.DESCRIPTOR);
        }

        public static IWindowToken asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IWindowToken.DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowToken)) {
                return new Proxy(obj);
            }
            return (IWindowToken) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onConfigurationChanged";
                case 2:
                    return "onWindowTokenRemoved";
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
            Configuration _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IWindowToken.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IWindowToken.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Configuration.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            onConfigurationChanged(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IWindowToken.DESCRIPTOR);
                            onWindowTokenRemoved();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IWindowToken {
            public static IWindowToken sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IWindowToken.DESCRIPTOR;
            }

            @Override // android.app.IWindowToken
            public void onConfigurationChanged(Configuration newConfig, int newDisplayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowToken.DESCRIPTOR);
                    if (newConfig != null) {
                        _data.writeInt(1);
                        newConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(newDisplayId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigurationChanged(newConfig, newDisplayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.IWindowToken
            public void onWindowTokenRemoved() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowToken.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWindowTokenRemoved();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowToken impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowToken getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
