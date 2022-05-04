package com.oplus.customize.coreapp.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusCoreAppService extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.customize.coreapp.aidl.IOplusCoreAppService";

    IBinder getManager(String str) throws RemoteException;

    boolean isPackageContainsOplusCertificates(String str) throws RemoteException;

    void onBootPhase(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusCoreAppService {
        @Override // com.oplus.customize.coreapp.aidl.IOplusCoreAppService
        public IBinder getManager(String strManagerName) throws RemoteException {
            return null;
        }

        @Override // com.oplus.customize.coreapp.aidl.IOplusCoreAppService
        public boolean isPackageContainsOplusCertificates(String packageName) throws RemoteException {
            return false;
        }

        @Override // com.oplus.customize.coreapp.aidl.IOplusCoreAppService
        public void onBootPhase(int phase) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusCoreAppService {
        static final int TRANSACTION_getManager = 1;
        static final int TRANSACTION_isPackageContainsOplusCertificates = 2;
        static final int TRANSACTION_onBootPhase = 3;

        public Stub() {
            attachInterface(this, IOplusCoreAppService.DESCRIPTOR);
        }

        public static IOplusCoreAppService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusCoreAppService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusCoreAppService)) {
                return new Proxy(obj);
            }
            return (IOplusCoreAppService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getManager";
                case 2:
                    return "isPackageContainsOplusCertificates";
                case 3:
                    return "onBootPhase";
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
                    reply.writeString(IOplusCoreAppService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusCoreAppService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            IBinder _result = getManager(_arg0);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusCoreAppService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            boolean isPackageContainsOplusCertificates = isPackageContainsOplusCertificates(_arg02);
                            reply.writeNoException();
                            reply.writeInt(isPackageContainsOplusCertificates ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusCoreAppService.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onBootPhase(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusCoreAppService {
            public static IOplusCoreAppService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusCoreAppService.DESCRIPTOR;
            }

            @Override // com.oplus.customize.coreapp.aidl.IOplusCoreAppService
            public IBinder getManager(String strManagerName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusCoreAppService.DESCRIPTOR);
                    _data.writeString(strManagerName);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getManager(strManagerName);
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.customize.coreapp.aidl.IOplusCoreAppService
            public boolean isPackageContainsOplusCertificates(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusCoreAppService.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageContainsOplusCertificates(packageName);
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

            @Override // com.oplus.customize.coreapp.aidl.IOplusCoreAppService
            public void onBootPhase(int phase) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusCoreAppService.DESCRIPTOR);
                    _data.writeInt(phase);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBootPhase(phase);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusCoreAppService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusCoreAppService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
