package android.hardware.face;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IFaceServiceReceiver extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.face.IFaceServiceReceiver";

    void onAcquired(int i, int i2) throws RemoteException;

    void onAuthenticationFailed() throws RemoteException;

    void onAuthenticationFrame(FaceAuthenticationFrame faceAuthenticationFrame) throws RemoteException;

    void onAuthenticationSucceeded(Face face, int i, boolean z) throws RemoteException;

    void onChallengeGenerated(int i, int i2, long j) throws RemoteException;

    void onEnrollResult(Face face, int i) throws RemoteException;

    void onEnrollmentFrame(FaceEnrollFrame faceEnrollFrame) throws RemoteException;

    void onError(int i, int i2) throws RemoteException;

    void onFaceDetected(int i, int i2, boolean z) throws RemoteException;

    void onFeatureGet(boolean z, int[] iArr, boolean[] zArr) throws RemoteException;

    void onFeatureSet(boolean z, int i) throws RemoteException;

    void onRemoved(Face face, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IFaceServiceReceiver {
        @Override // android.hardware.face.IFaceServiceReceiver
        public void onEnrollResult(Face face, int remaining) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAcquired(int acquiredInfo, int vendorCode) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAuthenticationSucceeded(Face face, int userId, boolean isStrongBiometric) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onFaceDetected(int sensorId, int userId, boolean isStrongBiometric) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAuthenticationFailed() throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onError(int error, int vendorCode) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onRemoved(Face face, int remaining) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onFeatureSet(boolean success, int feature) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onFeatureGet(boolean success, int[] features, boolean[] featureState) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onChallengeGenerated(int sensorId, int userId, long challenge) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onAuthenticationFrame(FaceAuthenticationFrame frame) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceServiceReceiver
        public void onEnrollmentFrame(FaceEnrollFrame frame) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IFaceServiceReceiver {
        static final int TRANSACTION_onAcquired = 2;
        static final int TRANSACTION_onAuthenticationFailed = 5;
        static final int TRANSACTION_onAuthenticationFrame = 11;
        static final int TRANSACTION_onAuthenticationSucceeded = 3;
        static final int TRANSACTION_onChallengeGenerated = 10;
        static final int TRANSACTION_onEnrollResult = 1;
        static final int TRANSACTION_onEnrollmentFrame = 12;
        static final int TRANSACTION_onError = 6;
        static final int TRANSACTION_onFaceDetected = 4;
        static final int TRANSACTION_onFeatureGet = 9;
        static final int TRANSACTION_onFeatureSet = 8;
        static final int TRANSACTION_onRemoved = 7;

        public Stub() {
            attachInterface(this, IFaceServiceReceiver.DESCRIPTOR);
        }

        public static IFaceServiceReceiver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IFaceServiceReceiver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IFaceServiceReceiver)) {
                return new Proxy(obj);
            }
            return (IFaceServiceReceiver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onEnrollResult";
                case 2:
                    return "onAcquired";
                case 3:
                    return "onAuthenticationSucceeded";
                case 4:
                    return "onFaceDetected";
                case 5:
                    return "onAuthenticationFailed";
                case 6:
                    return "onError";
                case 7:
                    return "onRemoved";
                case 8:
                    return "onFeatureSet";
                case 9:
                    return "onFeatureGet";
                case 10:
                    return "onChallengeGenerated";
                case 11:
                    return "onAuthenticationFrame";
                case 12:
                    return "onEnrollmentFrame";
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
            Face _arg0;
            Face _arg02;
            Face _arg03;
            FaceAuthenticationFrame _arg04;
            FaceEnrollFrame _arg05;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IFaceServiceReceiver.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg06 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Face.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            onEnrollResult(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg12 = data.readInt();
                            onAcquired(_arg07, _arg12);
                            return true;
                        case 3:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Face.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg13 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg06 = true;
                            }
                            onAuthenticationSucceeded(_arg02, _arg13, _arg06);
                            return true;
                        case 4:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg14 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg06 = true;
                            }
                            onFaceDetected(_arg08, _arg14, _arg06);
                            return true;
                        case 5:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            onAuthenticationFailed();
                            return true;
                        case 6:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            int _arg15 = data.readInt();
                            onError(_arg09, _arg15);
                            return true;
                        case 7:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = Face.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _arg16 = data.readInt();
                            onRemoved(_arg03, _arg16);
                            return true;
                        case 8:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = true;
                            }
                            int _arg17 = data.readInt();
                            onFeatureSet(_arg06, _arg17);
                            return true;
                        case 9:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = true;
                            }
                            int[] _arg18 = data.createIntArray();
                            boolean[] _arg2 = data.createBooleanArray();
                            onFeatureGet(_arg06, _arg18, _arg2);
                            return true;
                        case 10:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg19 = data.readInt();
                            long _arg22 = data.readLong();
                            onChallengeGenerated(_arg010, _arg19, _arg22);
                            return true;
                        case 11:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = FaceAuthenticationFrame.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            onAuthenticationFrame(_arg04);
                            return true;
                        case 12:
                            data.enforceInterface(IFaceServiceReceiver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = FaceEnrollFrame.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            onEnrollmentFrame(_arg05);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IFaceServiceReceiver {
            public static IFaceServiceReceiver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IFaceServiceReceiver.DESCRIPTOR;
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onEnrollResult(Face face, int remaining) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    if (face != null) {
                        _data.writeInt(1);
                        face.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(remaining);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnrollResult(face, remaining);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onAcquired(int acquiredInfo, int vendorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    _data.writeInt(acquiredInfo);
                    _data.writeInt(vendorCode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAcquired(acquiredInfo, vendorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onAuthenticationSucceeded(Face face, int userId, boolean isStrongBiometric) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    int i = 0;
                    if (face != null) {
                        _data.writeInt(1);
                        face.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    if (isStrongBiometric) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuthenticationSucceeded(face, userId, isStrongBiometric);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onFaceDetected(int sensorId, int userId, boolean isStrongBiometric) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    _data.writeInt(isStrongBiometric ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFaceDetected(sensorId, userId, isStrongBiometric);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onAuthenticationFailed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuthenticationFailed();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onError(int error, int vendorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    _data.writeInt(error);
                    _data.writeInt(vendorCode);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(error, vendorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onRemoved(Face face, int remaining) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    if (face != null) {
                        _data.writeInt(1);
                        face.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(remaining);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRemoved(face, remaining);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onFeatureSet(boolean success, int feature) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    _data.writeInt(success ? 1 : 0);
                    _data.writeInt(feature);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFeatureSet(success, feature);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onFeatureGet(boolean success, int[] features, boolean[] featureState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    _data.writeInt(success ? 1 : 0);
                    _data.writeIntArray(features);
                    _data.writeBooleanArray(featureState);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFeatureGet(success, features, featureState);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onChallengeGenerated(int sensorId, int userId, long challenge) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    _data.writeInt(sensorId);
                    _data.writeInt(userId);
                    _data.writeLong(challenge);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onChallengeGenerated(sensorId, userId, challenge);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onAuthenticationFrame(FaceAuthenticationFrame frame) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    if (frame != null) {
                        _data.writeInt(1);
                        frame.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuthenticationFrame(frame);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.face.IFaceServiceReceiver
            public void onEnrollmentFrame(FaceEnrollFrame frame) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFaceServiceReceiver.DESCRIPTOR);
                    if (frame != null) {
                        _data.writeInt(1);
                        frame.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEnrollmentFrame(frame);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFaceServiceReceiver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IFaceServiceReceiver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
