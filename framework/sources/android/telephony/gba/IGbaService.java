package android.telephony.gba;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IGbaService extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.gba.IGbaService";

    void authenticationRequest(GbaAuthRequest gbaAuthRequest) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IGbaService {
        @Override // android.telephony.gba.IGbaService
        public void authenticationRequest(GbaAuthRequest request) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IGbaService {
        static final int TRANSACTION_authenticationRequest = 1;

        public Stub() {
            attachInterface(this, IGbaService.DESCRIPTOR);
        }

        public static IGbaService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGbaService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGbaService)) {
                return new Proxy(obj);
            }
            return (IGbaService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "authenticationRequest";
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
            GbaAuthRequest _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IGbaService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGbaService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = GbaAuthRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            authenticationRequest(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IGbaService {
            public static IGbaService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGbaService.DESCRIPTOR;
            }

            @Override // android.telephony.gba.IGbaService
            public void authenticationRequest(GbaAuthRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGbaService.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().authenticationRequest(request);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGbaService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGbaService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
