package android.service.quickaccesswallet;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IQuickAccessWalletServiceCallbacks extends IInterface {
    public static final String DESCRIPTOR = "android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks";

    void onGetWalletCardsFailure(GetWalletCardsError getWalletCardsError) throws RemoteException;

    void onGetWalletCardsSuccess(GetWalletCardsResponse getWalletCardsResponse) throws RemoteException;

    void onWalletServiceEvent(WalletServiceEvent walletServiceEvent) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IQuickAccessWalletServiceCallbacks {
        @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsSuccess(GetWalletCardsResponse response) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsFailure(GetWalletCardsError error) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onWalletServiceEvent(WalletServiceEvent event) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IQuickAccessWalletServiceCallbacks {
        static final int TRANSACTION_onGetWalletCardsFailure = 2;
        static final int TRANSACTION_onGetWalletCardsSuccess = 1;
        static final int TRANSACTION_onWalletServiceEvent = 3;

        public Stub() {
            attachInterface(this, IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
        }

        public static IQuickAccessWalletServiceCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
            if (iin == null || !(iin instanceof IQuickAccessWalletServiceCallbacks)) {
                return new Proxy(obj);
            }
            return (IQuickAccessWalletServiceCallbacks) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onGetWalletCardsSuccess";
                case 2:
                    return "onGetWalletCardsFailure";
                case 3:
                    return "onWalletServiceEvent";
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
            GetWalletCardsResponse _arg0;
            GetWalletCardsError _arg02;
            WalletServiceEvent _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = GetWalletCardsResponse.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onGetWalletCardsSuccess(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = GetWalletCardsError.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onGetWalletCardsFailure(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = WalletServiceEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onWalletServiceEvent(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IQuickAccessWalletServiceCallbacks {
            public static IQuickAccessWalletServiceCallbacks sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IQuickAccessWalletServiceCallbacks.DESCRIPTOR;
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
            public void onGetWalletCardsSuccess(GetWalletCardsResponse response) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
                    if (response != null) {
                        _data.writeInt(1);
                        response.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetWalletCardsSuccess(response);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
            public void onGetWalletCardsFailure(GetWalletCardsError error) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
                    if (error != null) {
                        _data.writeInt(1);
                        error.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetWalletCardsFailure(error);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
            public void onWalletServiceEvent(WalletServiceEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletServiceCallbacks.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWalletServiceEvent(event);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQuickAccessWalletServiceCallbacks impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IQuickAccessWalletServiceCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
