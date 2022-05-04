package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IBluetoothTbsCallback extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothTbsCallback";

    void onAcceptCall(int i, ParcelUuid parcelUuid) throws RemoteException;

    void onBearerRegistered(int i) throws RemoteException;

    void onHoldCall(int i, ParcelUuid parcelUuid) throws RemoteException;

    void onJoinCalls(int i, List<ParcelUuid> list) throws RemoteException;

    void onPlaceCall(int i, ParcelUuid parcelUuid, String str) throws RemoteException;

    void onTerminateCall(int i, ParcelUuid parcelUuid) throws RemoteException;

    void onUnholdCall(int i, ParcelUuid parcelUuid) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothTbsCallback {
        @Override // android.bluetooth.IBluetoothTbsCallback
        public void onBearerRegistered(int ccid) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbsCallback
        public void onAcceptCall(int requestId, ParcelUuid uuid) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbsCallback
        public void onTerminateCall(int requestId, ParcelUuid uuid) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbsCallback
        public void onHoldCall(int requestId, ParcelUuid uuid) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbsCallback
        public void onUnholdCall(int requestId, ParcelUuid uuid) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbsCallback
        public void onPlaceCall(int requestId, ParcelUuid uuid, String uri) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbsCallback
        public void onJoinCalls(int requestId, List<ParcelUuid> uuids) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothTbsCallback {
        static final int TRANSACTION_onAcceptCall = 2;
        static final int TRANSACTION_onBearerRegistered = 1;
        static final int TRANSACTION_onHoldCall = 4;
        static final int TRANSACTION_onJoinCalls = 7;
        static final int TRANSACTION_onPlaceCall = 6;
        static final int TRANSACTION_onTerminateCall = 3;
        static final int TRANSACTION_onUnholdCall = 5;

        public Stub() {
            attachInterface(this, IBluetoothTbsCallback.DESCRIPTOR);
        }

        public static IBluetoothTbsCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothTbsCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothTbsCallback)) {
                return new Proxy(obj);
            }
            return (IBluetoothTbsCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onBearerRegistered";
                case 2:
                    return "onAcceptCall";
                case 3:
                    return "onTerminateCall";
                case 4:
                    return "onHoldCall";
                case 5:
                    return "onUnholdCall";
                case 6:
                    return "onPlaceCall";
                case 7:
                    return "onJoinCalls";
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
            ParcelUuid _arg1;
            ParcelUuid _arg12;
            ParcelUuid _arg13;
            ParcelUuid _arg14;
            ParcelUuid _arg15;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothTbsCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothTbsCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onBearerRegistered(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothTbsCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onAcceptCall(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IBluetoothTbsCallback.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onTerminateCall(_arg03, _arg12);
                            return true;
                        case 4:
                            data.enforceInterface(IBluetoothTbsCallback.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            onHoldCall(_arg04, _arg13);
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothTbsCallback.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            onUnholdCall(_arg05, _arg14);
                            return true;
                        case 6:
                            data.enforceInterface(IBluetoothTbsCallback.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            String _arg2 = data.readString();
                            onPlaceCall(_arg06, _arg15, _arg2);
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothTbsCallback.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            List<ParcelUuid> _arg16 = data.createTypedArrayList(ParcelUuid.CREATOR);
                            onJoinCalls(_arg07, _arg16);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothTbsCallback {
            public static IBluetoothTbsCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothTbsCallback.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothTbsCallback
            public void onBearerRegistered(int ccid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbsCallback.DESCRIPTOR);
                    _data.writeInt(ccid);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBearerRegistered(ccid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbsCallback
            public void onAcceptCall(int requestId, ParcelUuid uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbsCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAcceptCall(requestId, uuid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbsCallback
            public void onTerminateCall(int requestId, ParcelUuid uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbsCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTerminateCall(requestId, uuid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbsCallback
            public void onHoldCall(int requestId, ParcelUuid uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbsCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHoldCall(requestId, uuid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbsCallback
            public void onUnholdCall(int requestId, ParcelUuid uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbsCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnholdCall(requestId, uuid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbsCallback
            public void onPlaceCall(int requestId, ParcelUuid uuid, String uri) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbsCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(uri);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaceCall(requestId, uuid, uri);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbsCallback
            public void onJoinCalls(int requestId, List<ParcelUuid> uuids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbsCallback.DESCRIPTOR);
                    _data.writeInt(requestId);
                    _data.writeTypedList(uuids);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJoinCalls(requestId, uuids);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothTbsCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothTbsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
