package com.oplus.verifycode;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oplus.verifycode.IOplusVerifyCodeService;
/* loaded from: classes4.dex */
public interface IOplusVerifyCodeListener extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.verifycode.IOplusVerifyCodeListener";

    void notifyIMELayoutChanged(boolean z, int i, int i2) throws RemoteException;

    void notifyImeAttributeChanged(int i, boolean z) throws RemoteException;

    void onBindService(IOplusVerifyCodeService iOplusVerifyCodeService) throws RemoteException;

    void onUnBindService(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusVerifyCodeListener {
        @Override // com.oplus.verifycode.IOplusVerifyCodeListener
        public void notifyImeAttributeChanged(int condType, boolean flag) throws RemoteException {
        }

        @Override // com.oplus.verifycode.IOplusVerifyCodeListener
        public void notifyIMELayoutChanged(boolean imeVisible, int imeTop, int imeBottom) throws RemoteException {
        }

        @Override // com.oplus.verifycode.IOplusVerifyCodeListener
        public void onBindService(IOplusVerifyCodeService oplusVerifyCodeService) throws RemoteException {
        }

        @Override // com.oplus.verifycode.IOplusVerifyCodeListener
        public void onUnBindService(int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusVerifyCodeListener {
        static final int TRANSACTION_notifyIMELayoutChanged = 2;
        static final int TRANSACTION_notifyImeAttributeChanged = 1;
        static final int TRANSACTION_onBindService = 3;
        static final int TRANSACTION_onUnBindService = 4;

        public Stub() {
            attachInterface(this, IOplusVerifyCodeListener.DESCRIPTOR);
        }

        public static IOplusVerifyCodeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusVerifyCodeListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusVerifyCodeListener)) {
                return new Proxy(obj);
            }
            return (IOplusVerifyCodeListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifyImeAttributeChanged";
                case 2:
                    return "notifyIMELayoutChanged";
                case 3:
                    return "onBindService";
                case 4:
                    return "onUnBindService";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusVerifyCodeListener.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg0 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusVerifyCodeListener.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            notifyImeAttributeChanged(_arg02, _arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusVerifyCodeListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            notifyIMELayoutChanged(_arg0, _arg1, _arg2);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusVerifyCodeListener.DESCRIPTOR);
                            onBindService(IOplusVerifyCodeService.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 4:
                            data.enforceInterface(IOplusVerifyCodeListener.DESCRIPTOR);
                            onUnBindService(data.readInt());
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusVerifyCodeListener {
            public static IOplusVerifyCodeListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusVerifyCodeListener.DESCRIPTOR;
            }

            @Override // com.oplus.verifycode.IOplusVerifyCodeListener
            public void notifyImeAttributeChanged(int condType, boolean flag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusVerifyCodeListener.DESCRIPTOR);
                    _data.writeInt(condType);
                    _data.writeInt(flag ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyImeAttributeChanged(condType, flag);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.verifycode.IOplusVerifyCodeListener
            public void notifyIMELayoutChanged(boolean imeVisible, int imeTop, int imeBottom) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusVerifyCodeListener.DESCRIPTOR);
                    _data.writeInt(imeVisible ? 1 : 0);
                    _data.writeInt(imeTop);
                    _data.writeInt(imeBottom);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyIMELayoutChanged(imeVisible, imeTop, imeBottom);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.verifycode.IOplusVerifyCodeListener
            public void onBindService(IOplusVerifyCodeService oplusVerifyCodeService) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusVerifyCodeListener.DESCRIPTOR);
                    _data.writeStrongBinder(oplusVerifyCodeService != null ? oplusVerifyCodeService.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBindService(oplusVerifyCodeService);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.verifycode.IOplusVerifyCodeListener
            public void onUnBindService(int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusVerifyCodeListener.DESCRIPTOR);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnBindService(reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusVerifyCodeListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusVerifyCodeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
