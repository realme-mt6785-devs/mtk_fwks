package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IMemoryHeap extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IMemoryHeap";

    int getBase() throws RemoteException;

    int getFlags() throws RemoteException;

    int getHeapID(int i, int i2) throws RemoteException;

    int getOffset() throws RemoteException;

    int getSize() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IMemoryHeap {
        @Override // com.mediatek.mmsdk.IMemoryHeap
        public int getHeapID(int offset, int size) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IMemoryHeap
        public int getBase() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IMemoryHeap
        public int getSize() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IMemoryHeap
        public int getFlags() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IMemoryHeap
        public int getOffset() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMemoryHeap {
        static final int TRANSACTION_getBase = 2;
        static final int TRANSACTION_getFlags = 4;
        static final int TRANSACTION_getHeapID = 1;
        static final int TRANSACTION_getOffset = 5;
        static final int TRANSACTION_getSize = 3;

        public Stub() {
            attachInterface(this, IMemoryHeap.DESCRIPTOR);
        }

        public static IMemoryHeap asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMemoryHeap.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMemoryHeap)) {
                return new Proxy(obj);
            }
            return (IMemoryHeap) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IMemoryHeap.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMemoryHeap.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            int _result = getHeapID(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IMemoryHeap.DESCRIPTOR);
                            int _result2 = getBase();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IMemoryHeap.DESCRIPTOR);
                            int _result3 = getSize();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IMemoryHeap.DESCRIPTOR);
                            int _result4 = getFlags();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IMemoryHeap.DESCRIPTOR);
                            int _result5 = getOffset();
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IMemoryHeap {
            public static IMemoryHeap sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMemoryHeap.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IMemoryHeap
            public int getHeapID(int offset, int size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMemoryHeap.DESCRIPTOR);
                    _data.writeInt(offset);
                    _data.writeInt(size);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHeapID(offset, size);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IMemoryHeap
            public int getBase() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMemoryHeap.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBase();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IMemoryHeap
            public int getSize() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMemoryHeap.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSize();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IMemoryHeap
            public int getFlags() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMemoryHeap.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFlags();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IMemoryHeap
            public int getOffset() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMemoryHeap.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOffset();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMemoryHeap impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMemoryHeap getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
