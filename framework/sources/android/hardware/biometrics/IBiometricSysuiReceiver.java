package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBiometricSysuiReceiver extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricSysuiReceiver";

    void onDeviceCredentialPressed() throws RemoteException;

    void onDialogAnimatedIn() throws RemoteException;

    void onDialogDismissed(int i, byte[] bArr) throws RemoteException;

    void onStartFingerprintNow() throws RemoteException;

    void onSystemEvent(int i) throws RemoteException;

    void onTryAgainPressed() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBiometricSysuiReceiver {
        @Override // android.hardware.biometrics.IBiometricSysuiReceiver
        public void onDialogDismissed(int reason, byte[] credentialAttestation) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricSysuiReceiver
        public void onTryAgainPressed() throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricSysuiReceiver
        public void onDeviceCredentialPressed() throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricSysuiReceiver
        public void onSystemEvent(int event) throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricSysuiReceiver
        public void onDialogAnimatedIn() throws RemoteException {
        }

        @Override // android.hardware.biometrics.IBiometricSysuiReceiver
        public void onStartFingerprintNow() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBiometricSysuiReceiver {
        static final int TRANSACTION_onDeviceCredentialPressed = 3;
        static final int TRANSACTION_onDialogAnimatedIn = 5;
        static final int TRANSACTION_onDialogDismissed = 1;
        static final int TRANSACTION_onStartFingerprintNow = 6;
        static final int TRANSACTION_onSystemEvent = 4;
        static final int TRANSACTION_onTryAgainPressed = 2;

        public Stub() {
            attachInterface(this, IBiometricSysuiReceiver.DESCRIPTOR);
        }

        public static IBiometricSysuiReceiver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBiometricSysuiReceiver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBiometricSysuiReceiver)) {
                return new Proxy(obj);
            }
            return (IBiometricSysuiReceiver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDialogDismissed";
                case 2:
                    return "onTryAgainPressed";
                case 3:
                    return "onDeviceCredentialPressed";
                case 4:
                    return "onSystemEvent";
                case 5:
                    return "onDialogAnimatedIn";
                case 6:
                    return "onStartFingerprintNow";
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
                    reply.writeString(IBiometricSysuiReceiver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBiometricSysuiReceiver.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            byte[] _arg1 = data.createByteArray();
                            onDialogDismissed(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IBiometricSysuiReceiver.DESCRIPTOR);
                            onTryAgainPressed();
                            return true;
                        case 3:
                            data.enforceInterface(IBiometricSysuiReceiver.DESCRIPTOR);
                            onDeviceCredentialPressed();
                            return true;
                        case 4:
                            data.enforceInterface(IBiometricSysuiReceiver.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onSystemEvent(_arg02);
                            return true;
                        case 5:
                            data.enforceInterface(IBiometricSysuiReceiver.DESCRIPTOR);
                            onDialogAnimatedIn();
                            return true;
                        case 6:
                            data.enforceInterface(IBiometricSysuiReceiver.DESCRIPTOR);
                            onStartFingerprintNow();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBiometricSysuiReceiver {
            public static IBiometricSysuiReceiver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBiometricSysuiReceiver.DESCRIPTOR;
            }

            @Override // android.hardware.biometrics.IBiometricSysuiReceiver
            public void onDialogDismissed(int reason, byte[] credentialAttestation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricSysuiReceiver.DESCRIPTOR);
                    _data.writeInt(reason);
                    _data.writeByteArray(credentialAttestation);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDialogDismissed(reason, credentialAttestation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricSysuiReceiver
            public void onTryAgainPressed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricSysuiReceiver.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTryAgainPressed();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricSysuiReceiver
            public void onDeviceCredentialPressed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricSysuiReceiver.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceCredentialPressed();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricSysuiReceiver
            public void onSystemEvent(int event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricSysuiReceiver.DESCRIPTOR);
                    _data.writeInt(event);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSystemEvent(event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricSysuiReceiver
            public void onDialogAnimatedIn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricSysuiReceiver.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDialogAnimatedIn();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.biometrics.IBiometricSysuiReceiver
            public void onStartFingerprintNow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBiometricSysuiReceiver.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStartFingerprintNow();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBiometricSysuiReceiver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBiometricSysuiReceiver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
