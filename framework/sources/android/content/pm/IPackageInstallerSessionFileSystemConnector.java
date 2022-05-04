package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IPackageInstallerSessionFileSystemConnector extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.IPackageInstallerSessionFileSystemConnector";

    void writeData(String str, long j, long j2, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPackageInstallerSessionFileSystemConnector {
        @Override // android.content.pm.IPackageInstallerSessionFileSystemConnector
        public void writeData(String name, long offsetBytes, long lengthBytes, ParcelFileDescriptor fd) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPackageInstallerSessionFileSystemConnector {
        static final int TRANSACTION_writeData = 1;

        public Stub() {
            attachInterface(this, IPackageInstallerSessionFileSystemConnector.DESCRIPTOR);
        }

        public static IPackageInstallerSessionFileSystemConnector asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPackageInstallerSessionFileSystemConnector.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPackageInstallerSessionFileSystemConnector)) {
                return new Proxy(obj);
            }
            return (IPackageInstallerSessionFileSystemConnector) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "writeData";
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
            ParcelFileDescriptor _arg3;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPackageInstallerSessionFileSystemConnector.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPackageInstallerSessionFileSystemConnector.DESCRIPTOR);
                            String _arg0 = data.readString();
                            long _arg1 = data.readLong();
                            long _arg2 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg3 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            writeData(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPackageInstallerSessionFileSystemConnector {
            public static IPackageInstallerSessionFileSystemConnector sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPackageInstallerSessionFileSystemConnector.DESCRIPTOR;
            }

            @Override // android.content.pm.IPackageInstallerSessionFileSystemConnector
            public void writeData(String name, long offsetBytes, long lengthBytes, ParcelFileDescriptor fd) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPackageInstallerSessionFileSystemConnector.DESCRIPTOR);
                    try {
                        _data.writeString(name);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeLong(offsetBytes);
                    try {
                        _data.writeLong(lengthBytes);
                        if (fd != null) {
                            _data.writeInt(1);
                            fd.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().writeData(name, offsetBytes, lengthBytes, fd);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }
        }

        public static boolean setDefaultImpl(IPackageInstallerSessionFileSystemConnector impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPackageInstallerSessionFileSystemConnector getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
