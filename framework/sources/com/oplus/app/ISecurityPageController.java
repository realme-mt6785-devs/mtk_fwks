package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface ISecurityPageController extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.ISecurityPageController";

    void onSecurityPageFlagChanged(boolean z) throws RemoteException;

    void onSecurityPageFlagChangedForDisplay(boolean z, int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ISecurityPageController {
        @Override // com.oplus.app.ISecurityPageController
        public void onSecurityPageFlagChanged(boolean isSecurity) throws RemoteException {
        }

        @Override // com.oplus.app.ISecurityPageController
        public void onSecurityPageFlagChangedForDisplay(boolean isSecurity, int displayId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ISecurityPageController {
        static final int TRANSACTION_onSecurityPageFlagChanged = 1;
        static final int TRANSACTION_onSecurityPageFlagChangedForDisplay = 2;

        public Stub() {
            attachInterface(this, ISecurityPageController.DESCRIPTOR);
        }

        public static ISecurityPageController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISecurityPageController.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISecurityPageController)) {
                return new Proxy(obj);
            }
            return (ISecurityPageController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onSecurityPageFlagChanged";
                case 2:
                    return "onSecurityPageFlagChangedForDisplay";
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
                    reply.writeString(ISecurityPageController.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg0 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISecurityPageController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            onSecurityPageFlagChanged(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ISecurityPageController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            int _arg1 = data.readInt();
                            onSecurityPageFlagChangedForDisplay(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ISecurityPageController {
            public static ISecurityPageController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISecurityPageController.DESCRIPTOR;
            }

            @Override // com.oplus.app.ISecurityPageController
            public void onSecurityPageFlagChanged(boolean isSecurity) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISecurityPageController.DESCRIPTOR);
                    _data.writeInt(isSecurity ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSecurityPageFlagChanged(isSecurity);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.ISecurityPageController
            public void onSecurityPageFlagChangedForDisplay(boolean isSecurity, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISecurityPageController.DESCRIPTOR);
                    _data.writeInt(isSecurity ? 1 : 0);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSecurityPageFlagChangedForDisplay(isSecurity, displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISecurityPageController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISecurityPageController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
