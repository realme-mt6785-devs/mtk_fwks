package android.service.quickaccesswallet;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks;
/* loaded from: classes3.dex */
public interface IQuickAccessWalletService extends IInterface {
    public static final String DESCRIPTOR = "android.service.quickaccesswallet.IQuickAccessWalletService";

    void onWalletCardSelected(SelectWalletCardRequest selectWalletCardRequest) throws RemoteException;

    void onWalletCardsRequested(GetWalletCardsRequest getWalletCardsRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException;

    void onWalletDismissed() throws RemoteException;

    void registerWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException;

    void unregisterWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IQuickAccessWalletService {
        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardsRequested(GetWalletCardsRequest request, IQuickAccessWalletServiceCallbacks callback) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardSelected(SelectWalletCardRequest request) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletDismissed() throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void registerWalletServiceEventListener(WalletServiceEventListenerRequest request, IQuickAccessWalletServiceCallbacks callback) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void unregisterWalletServiceEventListener(WalletServiceEventListenerRequest request) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IQuickAccessWalletService {
        static final int TRANSACTION_onWalletCardSelected = 2;
        static final int TRANSACTION_onWalletCardsRequested = 1;
        static final int TRANSACTION_onWalletDismissed = 3;
        static final int TRANSACTION_registerWalletServiceEventListener = 4;
        static final int TRANSACTION_unregisterWalletServiceEventListener = 5;

        public Stub() {
            attachInterface(this, IQuickAccessWalletService.DESCRIPTOR);
        }

        public static IQuickAccessWalletService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IQuickAccessWalletService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IQuickAccessWalletService)) {
                return new Proxy(obj);
            }
            return (IQuickAccessWalletService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onWalletCardsRequested";
                case 2:
                    return "onWalletCardSelected";
                case 3:
                    return "onWalletDismissed";
                case 4:
                    return "registerWalletServiceEventListener";
                case 5:
                    return "unregisterWalletServiceEventListener";
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
            GetWalletCardsRequest _arg0;
            SelectWalletCardRequest _arg02;
            WalletServiceEventListenerRequest _arg03;
            WalletServiceEventListenerRequest _arg04;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IQuickAccessWalletService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IQuickAccessWalletService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = GetWalletCardsRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IQuickAccessWalletServiceCallbacks _arg1 = IQuickAccessWalletServiceCallbacks.Stub.asInterface(data.readStrongBinder());
                            onWalletCardsRequested(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IQuickAccessWalletService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SelectWalletCardRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onWalletCardSelected(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IQuickAccessWalletService.DESCRIPTOR);
                            onWalletDismissed();
                            return true;
                        case 4:
                            data.enforceInterface(IQuickAccessWalletService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = WalletServiceEventListenerRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            IQuickAccessWalletServiceCallbacks _arg12 = IQuickAccessWalletServiceCallbacks.Stub.asInterface(data.readStrongBinder());
                            registerWalletServiceEventListener(_arg03, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(IQuickAccessWalletService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = WalletServiceEventListenerRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            unregisterWalletServiceEventListener(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IQuickAccessWalletService {
            public static IQuickAccessWalletService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IQuickAccessWalletService.DESCRIPTOR;
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void onWalletCardsRequested(GetWalletCardsRequest request, IQuickAccessWalletServiceCallbacks callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletService.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWalletCardsRequested(request, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void onWalletCardSelected(SelectWalletCardRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletService.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWalletCardSelected(request);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void onWalletDismissed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWalletDismissed();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void registerWalletServiceEventListener(WalletServiceEventListenerRequest request, IQuickAccessWalletServiceCallbacks callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletService.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerWalletServiceEventListener(request, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void unregisterWalletServiceEventListener(WalletServiceEventListenerRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQuickAccessWalletService.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterWalletServiceEventListener(request);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQuickAccessWalletService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IQuickAccessWalletService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
