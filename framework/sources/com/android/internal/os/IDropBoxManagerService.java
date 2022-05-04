package com.android.internal.os;

import android.os.Binder;
import android.os.DropBoxManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IDropBoxManagerService extends IInterface {
    void addData(String str, byte[] bArr, int i) throws RemoteException;

    void addFile(String str, ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException;

    DropBoxManager.Entry getNextEntry(String str, long j, String str2) throws RemoteException;

    DropBoxManager.Entry getNextEntryWithAttribution(String str, long j, String str2, String str3) throws RemoteException;

    boolean isTagEnabled(String str) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IDropBoxManagerService {
        @Override // com.android.internal.os.IDropBoxManagerService
        public void addData(String tag, byte[] data, int flags) throws RemoteException {
        }

        @Override // com.android.internal.os.IDropBoxManagerService
        public void addFile(String tag, ParcelFileDescriptor fd, int flags) throws RemoteException {
        }

        @Override // com.android.internal.os.IDropBoxManagerService
        public boolean isTagEnabled(String tag) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.os.IDropBoxManagerService
        public DropBoxManager.Entry getNextEntry(String tag, long millis, String packageName) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.os.IDropBoxManagerService
        public DropBoxManager.Entry getNextEntryWithAttribution(String tag, long millis, String packageName, String attributionTag) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IDropBoxManagerService {
        public static final String DESCRIPTOR = "com.android.internal.os.IDropBoxManagerService";
        static final int TRANSACTION_addData = 1;
        static final int TRANSACTION_addFile = 2;
        static final int TRANSACTION_getNextEntry = 4;
        static final int TRANSACTION_getNextEntryWithAttribution = 5;
        static final int TRANSACTION_isTagEnabled = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDropBoxManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDropBoxManagerService)) {
                return new Proxy(obj);
            }
            return (IDropBoxManagerService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addData";
                case 2:
                    return "addFile";
                case 3:
                    return "isTagEnabled";
                case 4:
                    return "getNextEntry";
                case 5:
                    return "getNextEntryWithAttribution";
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
            ParcelFileDescriptor _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            byte[] _arg12 = data.createByteArray();
                            int _arg2 = data.readInt();
                            addData(_arg0, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg22 = data.readInt();
                            addFile(_arg02, _arg1, _arg22);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            boolean isTagEnabled = isTagEnabled(_arg03);
                            reply.writeNoException();
                            reply.writeInt(isTagEnabled ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            long _arg13 = data.readLong();
                            String _arg23 = data.readString();
                            DropBoxManager.Entry _result = getNextEntry(_arg04, _arg13, _arg23);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            long _arg14 = data.readLong();
                            String _arg24 = data.readString();
                            String _arg3 = data.readString();
                            DropBoxManager.Entry _result2 = getNextEntryWithAttribution(_arg05, _arg14, _arg24, _arg3);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IDropBoxManagerService {
            public static IDropBoxManagerService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.android.internal.os.IDropBoxManagerService
            public void addData(String tag, byte[] data, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(tag);
                    _data.writeByteArray(data);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addData(tag, data, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.os.IDropBoxManagerService
            public void addFile(String tag, ParcelFileDescriptor fd, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(tag);
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addFile(tag, fd, flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.os.IDropBoxManagerService
            public boolean isTagEnabled(String tag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(tag);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTagEnabled(tag);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.os.IDropBoxManagerService
            public DropBoxManager.Entry getNextEntry(String tag, long millis, String packageName) throws RemoteException {
                DropBoxManager.Entry _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(tag);
                    _data.writeLong(millis);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNextEntry(tag, millis, packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DropBoxManager.Entry.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.os.IDropBoxManagerService
            public DropBoxManager.Entry getNextEntryWithAttribution(String tag, long millis, String packageName, String attributionTag) throws RemoteException {
                DropBoxManager.Entry _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(tag);
                    _data.writeLong(millis);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNextEntryWithAttribution(tag, millis, packageName, attributionTag);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DropBoxManager.Entry.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDropBoxManagerService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDropBoxManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
