package android.hardware.fingerprint;

import android.hardware.fingerprint.IUdfpsOverlayControllerCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IUdfpsOverlayController extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.fingerprint.IUdfpsOverlayController";
    public static final int REASON_AUTH_BP = 3;
    public static final int REASON_AUTH_FPM_KEYGUARD = 4;
    public static final int REASON_AUTH_FPM_OTHER = 5;
    public static final int REASON_ENROLL_ENROLLING = 2;
    public static final int REASON_ENROLL_FIND_SENSOR = 1;
    public static final int REASON_UNKNOWN = 0;

    void hideUdfpsOverlay(int i) throws RemoteException;

    void onAcquiredGood(int i) throws RemoteException;

    void onEnrollmentHelp(int i) throws RemoteException;

    void onEnrollmentProgress(int i, int i2) throws RemoteException;

    void setDebugMessage(int i, String str) throws RemoteException;

    void showUdfpsOverlay(int i, int i2, IUdfpsOverlayControllerCallback iUdfpsOverlayControllerCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IUdfpsOverlayController {
        @Override // android.hardware.fingerprint.IUdfpsOverlayController
        public void showUdfpsOverlay(int sensorId, int reason, IUdfpsOverlayControllerCallback callback) throws RemoteException {
        }

        @Override // android.hardware.fingerprint.IUdfpsOverlayController
        public void hideUdfpsOverlay(int sensorId) throws RemoteException {
        }

        @Override // android.hardware.fingerprint.IUdfpsOverlayController
        public void onAcquiredGood(int sensorId) throws RemoteException {
        }

        @Override // android.hardware.fingerprint.IUdfpsOverlayController
        public void onEnrollmentProgress(int sensorId, int remaining) throws RemoteException {
        }

        @Override // android.hardware.fingerprint.IUdfpsOverlayController
        public void onEnrollmentHelp(int sensorId) throws RemoteException {
        }

        @Override // android.hardware.fingerprint.IUdfpsOverlayController
        public void setDebugMessage(int sensorId, String message) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IUdfpsOverlayController {
        static final int TRANSACTION_hideUdfpsOverlay = 2;
        static final int TRANSACTION_onAcquiredGood = 3;
        static final int TRANSACTION_onEnrollmentHelp = 5;
        static final int TRANSACTION_onEnrollmentProgress = 4;
        static final int TRANSACTION_setDebugMessage = 6;
        static final int TRANSACTION_showUdfpsOverlay = 1;

        public Stub() {
            attachInterface(this, IUdfpsOverlayController.DESCRIPTOR);
        }

        public static IUdfpsOverlayController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IUdfpsOverlayController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IUdfpsOverlayController)) {
                return new Proxy(obj);
            }
            return (IUdfpsOverlayController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "showUdfpsOverlay";
                case 2:
                    return "hideUdfpsOverlay";
                case 3:
                    return "onAcquiredGood";
                case 4:
                    return "onEnrollmentProgress";
                case 5:
                    return "onEnrollmentHelp";
                case 6:
                    return "setDebugMessage";
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
                    reply.writeString(IUdfpsOverlayController.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IUdfpsOverlayController.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            IUdfpsOverlayControllerCallback _arg2 = IUdfpsOverlayControllerCallback.Stub.asInterface(data.readStrongBinder());
                            showUdfpsOverlay(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IUdfpsOverlayController.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            hideUdfpsOverlay(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IUdfpsOverlayController.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onAcquiredGood(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IUdfpsOverlayController.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg12 = data.readInt();
                            onEnrollmentProgress(_arg04, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(IUdfpsOverlayController.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onEnrollmentHelp(_arg05);
                            return true;
                        case 6:
                            data.enforceInterface(IUdfpsOverlayController.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            String _arg13 = data.readString();
                            setDebugMessage(_arg06, _arg13);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IUdfpsOverlayController {
            public static IUdfpsOverlayController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IUdfpsOverlayController.DESCRIPTOR;
            }

            @Override // android.hardware.fingerprint.IUdfpsOverlayController
            public void showUdfpsOverlay(int sensorId, int reason, IUdfpsOverlayControllerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsOverlayController.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(reason);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showUdfpsOverlay(sensorId, reason, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.fingerprint.IUdfpsOverlayController
            public void hideUdfpsOverlay(int sensorId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsOverlayController.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideUdfpsOverlay(sensorId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.fingerprint.IUdfpsOverlayController
            public void onAcquiredGood(int sensorId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsOverlayController.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAcquiredGood(sensorId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.fingerprint.IUdfpsOverlayController
            public void onEnrollmentProgress(int sensorId, int remaining) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsOverlayController.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(remaining);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnrollmentProgress(sensorId, remaining);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.fingerprint.IUdfpsOverlayController
            public void onEnrollmentHelp(int sensorId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsOverlayController.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnrollmentHelp(sensorId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.fingerprint.IUdfpsOverlayController
            public void setDebugMessage(int sensorId, String message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUdfpsOverlayController.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeString(message);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDebugMessage(sensorId, message);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUdfpsOverlayController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IUdfpsOverlayController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
