package android.service.rotationresolver;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.rotationresolver.IRotationResolverCallback;
/* loaded from: classes3.dex */
public interface IRotationResolverService extends IInterface {
    public static final String DESCRIPTOR = "android.service.rotationresolver.IRotationResolverService";

    void resolveRotation(IRotationResolverCallback iRotationResolverCallback, RotationResolutionRequest rotationResolutionRequest) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRotationResolverService {
        @Override // android.service.rotationresolver.IRotationResolverService
        public void resolveRotation(IRotationResolverCallback callback, RotationResolutionRequest request) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRotationResolverService {
        static final int TRANSACTION_resolveRotation = 1;

        public Stub() {
            attachInterface(this, IRotationResolverService.DESCRIPTOR);
        }

        public static IRotationResolverService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRotationResolverService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRotationResolverService)) {
                return new Proxy(obj);
            }
            return (IRotationResolverService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "resolveRotation";
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
            RotationResolutionRequest _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRotationResolverService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRotationResolverService.DESCRIPTOR);
                            IRotationResolverCallback _arg0 = IRotationResolverCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = RotationResolutionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            resolveRotation(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRotationResolverService {
            public static IRotationResolverService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRotationResolverService.DESCRIPTOR;
            }

            @Override // android.service.rotationresolver.IRotationResolverService
            public void resolveRotation(IRotationResolverCallback callback, RotationResolutionRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRotationResolverService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resolveRotation(callback, request);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRotationResolverService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRotationResolverService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
