package android.service.autofill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.ISurfacePackageResultCallback;
/* loaded from: classes3.dex */
public interface IInlineSuggestionUi extends IInterface {
    public static final String DESCRIPTOR = "android.service.autofill.IInlineSuggestionUi";

    void getSurfacePackage(ISurfacePackageResultCallback iSurfacePackageResultCallback) throws RemoteException;

    void releaseSurfaceControlViewHost() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IInlineSuggestionUi {
        @Override // android.service.autofill.IInlineSuggestionUi
        public void getSurfacePackage(ISurfacePackageResultCallback callback) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionUi
        public void releaseSurfaceControlViewHost() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IInlineSuggestionUi {
        static final int TRANSACTION_getSurfacePackage = 1;
        static final int TRANSACTION_releaseSurfaceControlViewHost = 2;

        public Stub() {
            attachInterface(this, IInlineSuggestionUi.DESCRIPTOR);
        }

        public static IInlineSuggestionUi asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInlineSuggestionUi.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInlineSuggestionUi)) {
                return new Proxy(obj);
            }
            return (IInlineSuggestionUi) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getSurfacePackage";
                case 2:
                    return "releaseSurfaceControlViewHost";
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
                    reply.writeString(IInlineSuggestionUi.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInlineSuggestionUi.DESCRIPTOR);
                            ISurfacePackageResultCallback _arg0 = ISurfacePackageResultCallback.Stub.asInterface(data.readStrongBinder());
                            getSurfacePackage(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IInlineSuggestionUi.DESCRIPTOR);
                            releaseSurfaceControlViewHost();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IInlineSuggestionUi {
            public static IInlineSuggestionUi sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInlineSuggestionUi.DESCRIPTOR;
            }

            @Override // android.service.autofill.IInlineSuggestionUi
            public void getSurfacePackage(ISurfacePackageResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUi.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getSurfacePackage(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionUi
            public void releaseSurfaceControlViewHost() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionUi.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseSurfaceControlViewHost();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionUi impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInlineSuggestionUi getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
