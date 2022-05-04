package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.mmsdk.IMemoryHeap;
/* loaded from: classes.dex */
public interface IMemory extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IMemory";

    IMemoryHeap getMemory(int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IMemory {
        @Override // com.mediatek.mmsdk.IMemory
        public IMemoryHeap getMemory(int offset, int size) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMemory {
        static final int TRANSACTION_getMemory = 1;

        public Stub() {
            attachInterface(this, IMemory.DESCRIPTOR);
        }

        public static IMemory asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMemory.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMemory)) {
                return new Proxy(obj);
            }
            return (IMemory) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IMemory.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMemory.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            IMemoryHeap _result = getMemory(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IMemory {
            public static IMemory sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMemory.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IMemory
            public IMemoryHeap getMemory(int offset, int size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMemory.DESCRIPTOR);
                    _data.writeInt(offset);
                    _data.writeInt(size);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMemory(offset, size);
                    }
                    _reply.readException();
                    IMemoryHeap _result = IMemoryHeap.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMemory impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMemory getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
