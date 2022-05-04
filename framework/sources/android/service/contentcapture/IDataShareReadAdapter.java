package android.service.contentcapture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IDataShareReadAdapter extends IInterface {
    public static final String DESCRIPTOR = "android.service.contentcapture.IDataShareReadAdapter";

    void error(int i) throws RemoteException;

    void finish() throws RemoteException;

    void start(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDataShareReadAdapter {
        @Override // android.service.contentcapture.IDataShareReadAdapter
        public void start(ParcelFileDescriptor fd) throws RemoteException {
        }

        @Override // android.service.contentcapture.IDataShareReadAdapter
        public void error(int errorCode) throws RemoteException {
        }

        @Override // android.service.contentcapture.IDataShareReadAdapter
        public void finish() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDataShareReadAdapter {
        static final int TRANSACTION_error = 2;
        static final int TRANSACTION_finish = 3;
        static final int TRANSACTION_start = 1;

        public Stub() {
            attachInterface(this, IDataShareReadAdapter.DESCRIPTOR);
        }

        public static IDataShareReadAdapter asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDataShareReadAdapter.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDataShareReadAdapter)) {
                return new Proxy(obj);
            }
            return (IDataShareReadAdapter) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "start";
                case 2:
                    return "error";
                case 3:
                    return "finish";
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
            ParcelFileDescriptor _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDataShareReadAdapter.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDataShareReadAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            start(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IDataShareReadAdapter.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            error(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IDataShareReadAdapter.DESCRIPTOR);
                            finish();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDataShareReadAdapter {
            public static IDataShareReadAdapter sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDataShareReadAdapter.DESCRIPTOR;
            }

            @Override // android.service.contentcapture.IDataShareReadAdapter
            public void start(ParcelFileDescriptor fd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareReadAdapter.DESCRIPTOR);
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().start(fd);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.contentcapture.IDataShareReadAdapter
            public void error(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareReadAdapter.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().error(errorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.contentcapture.IDataShareReadAdapter
            public void finish() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareReadAdapter.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finish();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataShareReadAdapter impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDataShareReadAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
