package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IFingerprintAuthenticatorsRegisteredCallback extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintAuthenticatorsRegisteredCallback";

    void onAllAuthenticatorsRegistered(List<FingerprintSensorPropertiesInternal> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IFingerprintAuthenticatorsRegisteredCallback {
        @Override // android.hardware.fingerprint.IFingerprintAuthenticatorsRegisteredCallback
        public void onAllAuthenticatorsRegistered(List<FingerprintSensorPropertiesInternal> sensors) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IFingerprintAuthenticatorsRegisteredCallback {
        static final int TRANSACTION_onAllAuthenticatorsRegistered = 1;

        public Stub() {
            attachInterface(this, IFingerprintAuthenticatorsRegisteredCallback.DESCRIPTOR);
        }

        public static IFingerprintAuthenticatorsRegisteredCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IFingerprintAuthenticatorsRegisteredCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IFingerprintAuthenticatorsRegisteredCallback)) {
                return new Proxy(obj);
            }
            return (IFingerprintAuthenticatorsRegisteredCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAllAuthenticatorsRegistered";
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
                    reply.writeString(IFingerprintAuthenticatorsRegisteredCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IFingerprintAuthenticatorsRegisteredCallback.DESCRIPTOR);
                            List<FingerprintSensorPropertiesInternal> _arg0 = data.createTypedArrayList(FingerprintSensorPropertiesInternal.CREATOR);
                            onAllAuthenticatorsRegistered(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IFingerprintAuthenticatorsRegisteredCallback {
            public static IFingerprintAuthenticatorsRegisteredCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IFingerprintAuthenticatorsRegisteredCallback.DESCRIPTOR;
            }

            @Override // android.hardware.fingerprint.IFingerprintAuthenticatorsRegisteredCallback
            public void onAllAuthenticatorsRegistered(List<FingerprintSensorPropertiesInternal> sensors) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFingerprintAuthenticatorsRegisteredCallback.DESCRIPTOR);
                    _data.writeTypedList(sensors);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAllAuthenticatorsRegistered(sensors);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFingerprintAuthenticatorsRegisteredCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IFingerprintAuthenticatorsRegisteredCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
