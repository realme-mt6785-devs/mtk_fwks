package android.content.pm;

import android.app.time.LocationTimeZoneManager;
import android.content.pm.IDataLoaderStatusListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDataLoader extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.IDataLoader";

    void create(int i, DataLoaderParamsParcel dataLoaderParamsParcel, FileSystemControlParcel fileSystemControlParcel, IDataLoaderStatusListener iDataLoaderStatusListener) throws RemoteException;

    void destroy(int i) throws RemoteException;

    void prepareImage(int i, InstallationFileParcel[] installationFileParcelArr, String[] strArr) throws RemoteException;

    void start(int i) throws RemoteException;

    void stop(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDataLoader {
        @Override // android.content.pm.IDataLoader
        public void create(int id, DataLoaderParamsParcel params, FileSystemControlParcel control, IDataLoaderStatusListener listener) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void start(int id) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void stop(int id) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void destroy(int id) throws RemoteException {
        }

        @Override // android.content.pm.IDataLoader
        public void prepareImage(int id, InstallationFileParcel[] addedFiles, String[] removedFiles) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDataLoader {
        static final int TRANSACTION_create = 1;
        static final int TRANSACTION_destroy = 4;
        static final int TRANSACTION_prepareImage = 5;
        static final int TRANSACTION_start = 2;
        static final int TRANSACTION_stop = 3;

        public Stub() {
            attachInterface(this, IDataLoader.DESCRIPTOR);
        }

        public static IDataLoader asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDataLoader.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDataLoader)) {
                return new Proxy(obj);
            }
            return (IDataLoader) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "create";
                case 2:
                    return "start";
                case 3:
                    return LocationTimeZoneManager.SHELL_COMMAND_STOP;
                case 4:
                    return "destroy";
                case 5:
                    return "prepareImage";
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
            DataLoaderParamsParcel _arg1;
            FileSystemControlParcel _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDataLoader.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDataLoader.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = DataLoaderParamsParcel.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = FileSystemControlParcel.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            IDataLoaderStatusListener _arg3 = IDataLoaderStatusListener.Stub.asInterface(data.readStrongBinder());
                            create(_arg0, _arg1, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(IDataLoader.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            start(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IDataLoader.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            stop(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IDataLoader.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            destroy(_arg04);
                            return true;
                        case 5:
                            data.enforceInterface(IDataLoader.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            InstallationFileParcel[] _arg12 = (InstallationFileParcel[]) data.createTypedArray(InstallationFileParcel.CREATOR);
                            String[] _arg22 = data.createStringArray();
                            prepareImage(_arg05, _arg12, _arg22);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDataLoader {
            public static IDataLoader sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDataLoader.DESCRIPTOR;
            }

            @Override // android.content.pm.IDataLoader
            public void create(int id, DataLoaderParamsParcel params, FileSystemControlParcel control, IDataLoaderStatusListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoader.DESCRIPTOR);
                    _data.writeInt(id);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (control != null) {
                        _data.writeInt(1);
                        control.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().create(id, params, control, listener);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void start(int id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoader.DESCRIPTOR);
                    _data.writeInt(id);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().start(id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void stop(int id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoader.DESCRIPTOR);
                    _data.writeInt(id);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stop(id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void destroy(int id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoader.DESCRIPTOR);
                    _data.writeInt(id);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroy(id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IDataLoader
            public void prepareImage(int id, InstallationFileParcel[] addedFiles, String[] removedFiles) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataLoader.DESCRIPTOR);
                    _data.writeInt(id);
                    _data.writeTypedArray(addedFiles, 0);
                    _data.writeStringArray(removedFiles);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepareImage(id, addedFiles, removedFiles);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataLoader impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDataLoader getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
