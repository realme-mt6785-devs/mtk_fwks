package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusAppStartController extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusAppStartController";

    void appStartMonitor(String str, String str2, String str3, String str4, String str5) throws RemoteException;

    void notifyPreventIndulge(String str) throws RemoteException;

    void preventStartMonitor(String str, String str2, String str3, String str4, String str5) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusAppStartController {
        @Override // com.oplus.app.IOplusAppStartController
        public void appStartMonitor(String pkgName, String exceptionClass, String exceptionMsg, String exceptionTrace, String monitorType) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAppStartController
        public void preventStartMonitor(String callerPkg, String calledPkg, String startMode, String preventMode, String reason) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAppStartController
        public void notifyPreventIndulge(String pkgName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusAppStartController {
        static final int TRANSACTION_appStartMonitor = 1;
        static final int TRANSACTION_notifyPreventIndulge = 3;
        static final int TRANSACTION_preventStartMonitor = 2;

        public Stub() {
            attachInterface(this, IOplusAppStartController.DESCRIPTOR);
        }

        public static IOplusAppStartController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusAppStartController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusAppStartController)) {
                return new Proxy(obj);
            }
            return (IOplusAppStartController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "appStartMonitor";
                case 2:
                    return "preventStartMonitor";
                case 3:
                    return "notifyPreventIndulge";
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
                    reply.writeString(IOplusAppStartController.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusAppStartController.DESCRIPTOR);
                            String _arg0 = data.readString();
                            String _arg1 = data.readString();
                            String _arg2 = data.readString();
                            String _arg3 = data.readString();
                            String _arg4 = data.readString();
                            appStartMonitor(_arg0, _arg1, _arg2, _arg3, _arg4);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusAppStartController.DESCRIPTOR);
                            String _arg02 = data.readString();
                            String _arg12 = data.readString();
                            String _arg22 = data.readString();
                            String _arg32 = data.readString();
                            String _arg42 = data.readString();
                            preventStartMonitor(_arg02, _arg12, _arg22, _arg32, _arg42);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusAppStartController.DESCRIPTOR);
                            String _arg03 = data.readString();
                            notifyPreventIndulge(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusAppStartController {
            public static IOplusAppStartController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusAppStartController.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusAppStartController
            public void appStartMonitor(String pkgName, String exceptionClass, String exceptionMsg, String exceptionTrace, String monitorType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAppStartController.DESCRIPTOR);
                    _data.writeString(pkgName);
                    _data.writeString(exceptionClass);
                    _data.writeString(exceptionMsg);
                    _data.writeString(exceptionTrace);
                    _data.writeString(monitorType);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().appStartMonitor(pkgName, exceptionClass, exceptionMsg, exceptionTrace, monitorType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAppStartController
            public void preventStartMonitor(String callerPkg, String calledPkg, String startMode, String preventMode, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAppStartController.DESCRIPTOR);
                    _data.writeString(callerPkg);
                    _data.writeString(calledPkg);
                    _data.writeString(startMode);
                    _data.writeString(preventMode);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().preventStartMonitor(callerPkg, calledPkg, startMode, preventMode, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAppStartController
            public void notifyPreventIndulge(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAppStartController.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPreventIndulge(pkgName);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusAppStartController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusAppStartController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
