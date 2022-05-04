package android.view.contentcapture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IDataShareWriteAdapter extends IInterface {
    public static final String DESCRIPTOR = "android.view.contentcapture.IDataShareWriteAdapter";

    void error(int i) throws RemoteException;

    void finish() throws RemoteException;

    void rejected() throws RemoteException;

    void write(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDataShareWriteAdapter {
        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void write(ParcelFileDescriptor destination) throws RemoteException {
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void error(int errorCode) throws RemoteException {
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void rejected() throws RemoteException {
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void finish() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDataShareWriteAdapter {
        static final int TRANSACTION_error = 2;
        static final int TRANSACTION_finish = 4;
        static final int TRANSACTION_rejected = 3;
        static final int TRANSACTION_write = 1;

        public Stub() {
            attachInterface(this, IDataShareWriteAdapter.DESCRIPTOR);
        }

        public static IDataShareWriteAdapter asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDataShareWriteAdapter.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDataShareWriteAdapter)) {
                return new Proxy(obj);
            }
            return (IDataShareWriteAdapter) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "write";
                case 2:
                    return "error";
                case 3:
                    return "rejected";
                case 4:
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
                    reply.writeString(IDataShareWriteAdapter.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDataShareWriteAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            write(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IDataShareWriteAdapter.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            error(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IDataShareWriteAdapter.DESCRIPTOR);
                            rejected();
                            return true;
                        case 4:
                            data.enforceInterface(IDataShareWriteAdapter.DESCRIPTOR);
                            finish();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDataShareWriteAdapter {
            public static IDataShareWriteAdapter sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDataShareWriteAdapter.DESCRIPTOR;
            }

            @Override // android.view.contentcapture.IDataShareWriteAdapter
            public void write(ParcelFileDescriptor destination) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareWriteAdapter.DESCRIPTOR);
                    if (destination != null) {
                        _data.writeInt(1);
                        destination.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().write(destination);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IDataShareWriteAdapter
            public void error(int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareWriteAdapter.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().error(errorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IDataShareWriteAdapter
            public void rejected() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareWriteAdapter.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rejected();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IDataShareWriteAdapter
            public void finish() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareWriteAdapter.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finish();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataShareWriteAdapter impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDataShareWriteAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
