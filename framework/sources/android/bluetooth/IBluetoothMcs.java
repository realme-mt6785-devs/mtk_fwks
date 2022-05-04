package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.bt.BluetoothMcsMediaControlRequest;
import com.mediatek.bt.BluetoothMcsSearchRequest;
import java.util.Map;
/* loaded from: classes.dex */
public interface IBluetoothMcs extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothMcs";

    void destroy() throws RemoteException;

    int getCcid() throws RemoteException;

    void setMediaControlRequestResult(BluetoothMcsMediaControlRequest bluetoothMcsMediaControlRequest, int i) throws RemoteException;

    void setSearchRequestResult(BluetoothMcsSearchRequest bluetoothMcsSearchRequest, int i, long j) throws RemoteException;

    void updateObjectID(int i, long j) throws RemoteException;

    void updatePlaybackState(int i) throws RemoteException;

    void updatePlayerState(Map map) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothMcs {
        @Override // android.bluetooth.IBluetoothMcs
        public void updatePlaybackState(int state) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcs
        public void updatePlayerState(Map state_fields) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcs
        public void updateObjectID(int obj_field, long object_id) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcs
        public void setMediaControlRequestResult(BluetoothMcsMediaControlRequest request, int result_status) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcs
        public void setSearchRequestResult(BluetoothMcsSearchRequest request, int result_status, long result_object_id) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcs
        public int getCcid() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothMcs
        public void destroy() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothMcs {
        static final int TRANSACTION_destroy = 7;
        static final int TRANSACTION_getCcid = 6;
        static final int TRANSACTION_setMediaControlRequestResult = 4;
        static final int TRANSACTION_setSearchRequestResult = 5;
        static final int TRANSACTION_updateObjectID = 3;
        static final int TRANSACTION_updatePlaybackState = 1;
        static final int TRANSACTION_updatePlayerState = 2;

        public Stub() {
            attachInterface(this, IBluetoothMcs.DESCRIPTOR);
        }

        public static IBluetoothMcs asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothMcs.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothMcs)) {
                return new Proxy(obj);
            }
            return (IBluetoothMcs) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "updatePlaybackState";
                case 2:
                    return "updatePlayerState";
                case 3:
                    return "updateObjectID";
                case 4:
                    return "setMediaControlRequestResult";
                case 5:
                    return "setSearchRequestResult";
                case 6:
                    return "getCcid";
                case 7:
                    return "destroy";
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
            BluetoothMcsMediaControlRequest _arg0;
            BluetoothMcsSearchRequest _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothMcs.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothMcs.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            updatePlaybackState(_arg03);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothMcs.DESCRIPTOR);
                            ClassLoader cl = getClass().getClassLoader();
                            Map _arg04 = data.readHashMap(cl);
                            updatePlayerState(_arg04);
                            return true;
                        case 3:
                            data.enforceInterface(IBluetoothMcs.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            long _arg1 = data.readLong();
                            updateObjectID(_arg05, _arg1);
                            return true;
                        case 4:
                            data.enforceInterface(IBluetoothMcs.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BluetoothMcsMediaControlRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg12 = data.readInt();
                            setMediaControlRequestResult(_arg0, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothMcs.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = BluetoothMcsSearchRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg13 = data.readInt();
                            long _arg2 = data.readLong();
                            setSearchRequestResult(_arg02, _arg13, _arg2);
                            return true;
                        case 6:
                            data.enforceInterface(IBluetoothMcs.DESCRIPTOR);
                            int _result = getCcid();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothMcs.DESCRIPTOR);
                            destroy();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothMcs {
            public static IBluetoothMcs sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothMcs.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothMcs
            public void updatePlaybackState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcs.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePlaybackState(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcs
            public void updatePlayerState(Map state_fields) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcs.DESCRIPTOR);
                    _data.writeMap(state_fields);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePlayerState(state_fields);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcs
            public void updateObjectID(int obj_field, long object_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcs.DESCRIPTOR);
                    _data.writeInt(obj_field);
                    _data.writeLong(object_id);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateObjectID(obj_field, object_id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcs
            public void setMediaControlRequestResult(BluetoothMcsMediaControlRequest request, int result_status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcs.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(result_status);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMediaControlRequestResult(request, result_status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcs
            public void setSearchRequestResult(BluetoothMcsSearchRequest request, int result_status, long result_object_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcs.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(result_status);
                    _data.writeLong(result_object_id);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSearchRequestResult(request, result_status, result_object_id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcs
            public int getCcid() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcs.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCcid();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcs
            public void destroy() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcs.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroy();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothMcs impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothMcs getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
