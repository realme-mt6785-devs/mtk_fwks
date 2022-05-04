package android.service.displayhash;

import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.view.displayhash.DisplayHash;
/* loaded from: classes3.dex */
public interface IDisplayHashingService extends IInterface {
    public static final String DESCRIPTOR = "android.service.displayhash.IDisplayHashingService";

    void generateDisplayHash(byte[] bArr, HardwareBuffer hardwareBuffer, Rect rect, String str, RemoteCallback remoteCallback) throws RemoteException;

    void getDisplayHashAlgorithms(RemoteCallback remoteCallback) throws RemoteException;

    void getIntervalBetweenRequestsMillis(RemoteCallback remoteCallback) throws RemoteException;

    void verifyDisplayHash(byte[] bArr, DisplayHash displayHash, RemoteCallback remoteCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDisplayHashingService {
        @Override // android.service.displayhash.IDisplayHashingService
        public void generateDisplayHash(byte[] salt, HardwareBuffer buffer, Rect bounds, String hashAlgorithm, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.displayhash.IDisplayHashingService
        public void verifyDisplayHash(byte[] salt, DisplayHash displayHash, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.displayhash.IDisplayHashingService
        public void getDisplayHashAlgorithms(RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.displayhash.IDisplayHashingService
        public void getIntervalBetweenRequestsMillis(RemoteCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDisplayHashingService {
        static final int TRANSACTION_generateDisplayHash = 1;
        static final int TRANSACTION_getDisplayHashAlgorithms = 3;
        static final int TRANSACTION_getIntervalBetweenRequestsMillis = 4;
        static final int TRANSACTION_verifyDisplayHash = 2;

        public Stub() {
            attachInterface(this, IDisplayHashingService.DESCRIPTOR);
        }

        public static IDisplayHashingService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDisplayHashingService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDisplayHashingService)) {
                return new Proxy(obj);
            }
            return (IDisplayHashingService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "generateDisplayHash";
                case 2:
                    return "verifyDisplayHash";
                case 3:
                    return "getDisplayHashAlgorithms";
                case 4:
                    return "getIntervalBetweenRequestsMillis";
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
            HardwareBuffer _arg1;
            Rect _arg2;
            RemoteCallback _arg4;
            DisplayHash _arg12;
            RemoteCallback _arg22;
            RemoteCallback _arg0;
            RemoteCallback _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDisplayHashingService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDisplayHashingService.DESCRIPTOR);
                            byte[] _arg03 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg1 = HardwareBuffer.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            String _arg3 = data.readString();
                            if (data.readInt() != 0) {
                                _arg4 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            generateDisplayHash(_arg03, _arg1, _arg2, _arg3, _arg4);
                            return true;
                        case 2:
                            data.enforceInterface(IDisplayHashingService.DESCRIPTOR);
                            byte[] _arg04 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg12 = DisplayHash.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            verifyDisplayHash(_arg04, _arg12, _arg22);
                            return true;
                        case 3:
                            data.enforceInterface(IDisplayHashingService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            getDisplayHashAlgorithms(_arg0);
                            return true;
                        case 4:
                            data.enforceInterface(IDisplayHashingService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            getIntervalBetweenRequestsMillis(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDisplayHashingService {
            public static IDisplayHashingService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDisplayHashingService.DESCRIPTOR;
            }

            @Override // android.service.displayhash.IDisplayHashingService
            public void generateDisplayHash(byte[] salt, HardwareBuffer buffer, Rect bounds, String hashAlgorithm, RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayHashingService.DESCRIPTOR);
                    _data.writeByteArray(salt);
                    if (buffer != null) {
                        _data.writeInt(1);
                        buffer.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (bounds != null) {
                        _data.writeInt(1);
                        bounds.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(hashAlgorithm);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().generateDisplayHash(salt, buffer, bounds, hashAlgorithm, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.displayhash.IDisplayHashingService
            public void verifyDisplayHash(byte[] salt, DisplayHash displayHash, RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayHashingService.DESCRIPTOR);
                    _data.writeByteArray(salt);
                    if (displayHash != null) {
                        _data.writeInt(1);
                        displayHash.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().verifyDisplayHash(salt, displayHash, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.displayhash.IDisplayHashingService
            public void getDisplayHashAlgorithms(RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayHashingService.DESCRIPTOR);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDisplayHashAlgorithms(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.displayhash.IDisplayHashingService
            public void getIntervalBetweenRequestsMillis(RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayHashingService.DESCRIPTOR);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getIntervalBetweenRequestsMillis(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayHashingService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDisplayHashingService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
