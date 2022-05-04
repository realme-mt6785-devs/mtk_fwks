package android.view;

import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IDisplayWindowListener extends IInterface {
    public static final String DESCRIPTOR = "android.view.IDisplayWindowListener";

    void onDisplayAdded(int i) throws RemoteException;

    void onDisplayConfigurationChanged(int i, Configuration configuration) throws RemoteException;

    void onDisplayRemoved(int i) throws RemoteException;

    void onFixedRotationFinished(int i) throws RemoteException;

    void onFixedRotationStarted(int i, int i2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDisplayWindowListener {
        @Override // android.view.IDisplayWindowListener
        public void onDisplayAdded(int displayId) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onDisplayConfigurationChanged(int displayId, Configuration newConfig) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onDisplayRemoved(int displayId) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onFixedRotationStarted(int displayId, int newRotation) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowListener
        public void onFixedRotationFinished(int displayId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDisplayWindowListener {
        static final int TRANSACTION_onDisplayAdded = 1;
        static final int TRANSACTION_onDisplayConfigurationChanged = 2;
        static final int TRANSACTION_onDisplayRemoved = 3;
        static final int TRANSACTION_onFixedRotationFinished = 5;
        static final int TRANSACTION_onFixedRotationStarted = 4;

        public Stub() {
            attachInterface(this, IDisplayWindowListener.DESCRIPTOR);
        }

        public static IDisplayWindowListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDisplayWindowListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDisplayWindowListener)) {
                return new Proxy(obj);
            }
            return (IDisplayWindowListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDisplayAdded";
                case 2:
                    return "onDisplayConfigurationChanged";
                case 3:
                    return "onDisplayRemoved";
                case 4:
                    return "onFixedRotationStarted";
                case 5:
                    return "onFixedRotationFinished";
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
            Configuration _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDisplayWindowListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDisplayWindowListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onDisplayAdded(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IDisplayWindowListener.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Configuration.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onDisplayConfigurationChanged(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IDisplayWindowListener.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onDisplayRemoved(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IDisplayWindowListener.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg12 = data.readInt();
                            onFixedRotationStarted(_arg04, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(IDisplayWindowListener.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onFixedRotationFinished(_arg05);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDisplayWindowListener {
            public static IDisplayWindowListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDisplayWindowListener.DESCRIPTOR;
            }

            @Override // android.view.IDisplayWindowListener
            public void onDisplayAdded(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowListener.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayAdded(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onDisplayConfigurationChanged(int displayId, Configuration newConfig) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowListener.DESCRIPTOR);
                    _data.writeInt(displayId);
                    if (newConfig != null) {
                        _data.writeInt(1);
                        newConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayConfigurationChanged(displayId, newConfig);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onDisplayRemoved(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowListener.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayRemoved(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onFixedRotationStarted(int displayId, int newRotation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowListener.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(newRotation);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFixedRotationStarted(displayId, newRotation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowListener
            public void onFixedRotationFinished(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowListener.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFixedRotationFinished(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayWindowListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDisplayWindowListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
