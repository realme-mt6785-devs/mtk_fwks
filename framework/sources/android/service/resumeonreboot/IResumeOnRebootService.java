package android.service.resumeonreboot;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IResumeOnRebootService extends IInterface {
    public static final String DESCRIPTOR = "android.service.resumeonreboot.IResumeOnRebootService";

    void unwrap(byte[] bArr, RemoteCallback remoteCallback) throws RemoteException;

    void wrapSecret(byte[] bArr, long j, RemoteCallback remoteCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IResumeOnRebootService {
        @Override // android.service.resumeonreboot.IResumeOnRebootService
        public void wrapSecret(byte[] unwrappedBlob, long lifeTimeInMillis, RemoteCallback resultCallback) throws RemoteException {
        }

        @Override // android.service.resumeonreboot.IResumeOnRebootService
        public void unwrap(byte[] wrappedBlob, RemoteCallback resultCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IResumeOnRebootService {
        static final int TRANSACTION_unwrap = 2;
        static final int TRANSACTION_wrapSecret = 1;

        public Stub() {
            attachInterface(this, IResumeOnRebootService.DESCRIPTOR);
        }

        public static IResumeOnRebootService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IResumeOnRebootService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IResumeOnRebootService)) {
                return new Proxy(obj);
            }
            return (IResumeOnRebootService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "wrapSecret";
                case 2:
                    return "unwrap";
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
            RemoteCallback _arg2;
            RemoteCallback _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IResumeOnRebootService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IResumeOnRebootService.DESCRIPTOR);
                            byte[] _arg0 = data.createByteArray();
                            long _arg12 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg2 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            wrapSecret(_arg0, _arg12, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IResumeOnRebootService.DESCRIPTOR);
                            byte[] _arg02 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg1 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            unwrap(_arg02, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IResumeOnRebootService {
            public static IResumeOnRebootService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IResumeOnRebootService.DESCRIPTOR;
            }

            @Override // android.service.resumeonreboot.IResumeOnRebootService
            public void wrapSecret(byte[] unwrappedBlob, long lifeTimeInMillis, RemoteCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResumeOnRebootService.DESCRIPTOR);
                    _data.writeByteArray(unwrappedBlob);
                    _data.writeLong(lifeTimeInMillis);
                    if (resultCallback != null) {
                        _data.writeInt(1);
                        resultCallback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wrapSecret(unwrappedBlob, lifeTimeInMillis, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.resumeonreboot.IResumeOnRebootService
            public void unwrap(byte[] wrappedBlob, RemoteCallback resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResumeOnRebootService.DESCRIPTOR);
                    _data.writeByteArray(wrappedBlob);
                    if (resultCallback != null) {
                        _data.writeInt(1);
                        resultCallback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unwrap(wrappedBlob, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IResumeOnRebootService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IResumeOnRebootService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
