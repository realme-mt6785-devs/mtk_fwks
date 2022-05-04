package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBiometricEnabledOnKeyguardCallback extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback";

    void onChanged(boolean z, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBiometricEnabledOnKeyguardCallback {
        @Override // android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback
        public void onChanged(boolean enabled, int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBiometricEnabledOnKeyguardCallback {
        static final int TRANSACTION_onChanged = 1;

        public Stub() {
            attachInterface(this, IBiometricEnabledOnKeyguardCallback.DESCRIPTOR);
        }

        public static IBiometricEnabledOnKeyguardCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBiometricEnabledOnKeyguardCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBiometricEnabledOnKeyguardCallback)) {
                return new Proxy(obj);
            }
            return (IBiometricEnabledOnKeyguardCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onChanged";
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
                    reply.writeString(IBiometricEnabledOnKeyguardCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBiometricEnabledOnKeyguardCallback.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            int _arg1 = data.readInt();
                            onChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBiometricEnabledOnKeyguardCallback {
            public static IBiometricEnabledOnKeyguardCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBiometricEnabledOnKeyguardCallback.DESCRIPTOR;
            }

            @Override // android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback
            public void onChanged(boolean enabled, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricEnabledOnKeyguardCallback.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChanged(enabled, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBiometricEnabledOnKeyguardCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBiometricEnabledOnKeyguardCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
