package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.bt.BluetoothMcsMediaControlRequest;
import com.mediatek.bt.BluetoothMcsSearchRequest;
/* loaded from: classes.dex */
public interface IBluetoothMcsCallbacks extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothMcsCallbacks";

    void onCurrentGroupObjectIdSet(long j) throws RemoteException;

    void onCurrentTrackMetadataRequest() throws RemoteException;

    void onCurrentTrackObjectIdSet(long j) throws RemoteException;

    long onGetCurrentTrackPosition() throws RemoteException;

    long onGetFeatureFlags() throws RemoteException;

    void onMediaControlRequest(BluetoothMcsMediaControlRequest bluetoothMcsMediaControlRequest, BluetoothDevice bluetoothDevice) throws RemoteException;

    void onNextTrackObjectIdSet(long j) throws RemoteException;

    void onPlaybackSpeedSetRequest(float f) throws RemoteException;

    void onPlayerStateRequest(int[] iArr) throws RemoteException;

    void onPlayingOrderSetRequest(int i) throws RemoteException;

    void onSearchRequest(BluetoothMcsSearchRequest bluetoothMcsSearchRequest) throws RemoteException;

    void onServiceInstanceRegistered(int i, IBinder iBinder) throws RemoteException;

    void onServiceInstanceUnregistered(int i) throws RemoteException;

    void onSetObjectIdRequest(int i, long j) throws RemoteException;

    void onTrackPositionSetRequest(long j) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothMcsCallbacks {
        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onServiceInstanceRegistered(int status, IBinder service_proxy) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onServiceInstanceUnregistered(int status) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onMediaControlRequest(BluetoothMcsMediaControlRequest request, BluetoothDevice device) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onSearchRequest(BluetoothMcsSearchRequest request) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onSetObjectIdRequest(int obj_field, long object_id) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onTrackPositionSetRequest(long position) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onPlaybackSpeedSetRequest(float speed) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onPlayingOrderSetRequest(int order) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onCurrentTrackObjectIdSet(long object_id) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onNextTrackObjectIdSet(long object_id) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onCurrentGroupObjectIdSet(long object_id) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onCurrentTrackMetadataRequest() throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public void onPlayerStateRequest(int[] state_fields) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public long onGetFeatureFlags() throws RemoteException {
            return 0L;
        }

        @Override // android.bluetooth.IBluetoothMcsCallbacks
        public long onGetCurrentTrackPosition() throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothMcsCallbacks {
        static final int TRANSACTION_onCurrentGroupObjectIdSet = 11;
        static final int TRANSACTION_onCurrentTrackMetadataRequest = 12;
        static final int TRANSACTION_onCurrentTrackObjectIdSet = 9;
        static final int TRANSACTION_onGetCurrentTrackPosition = 15;
        static final int TRANSACTION_onGetFeatureFlags = 14;
        static final int TRANSACTION_onMediaControlRequest = 3;
        static final int TRANSACTION_onNextTrackObjectIdSet = 10;
        static final int TRANSACTION_onPlaybackSpeedSetRequest = 7;
        static final int TRANSACTION_onPlayerStateRequest = 13;
        static final int TRANSACTION_onPlayingOrderSetRequest = 8;
        static final int TRANSACTION_onSearchRequest = 4;
        static final int TRANSACTION_onServiceInstanceRegistered = 1;
        static final int TRANSACTION_onServiceInstanceUnregistered = 2;
        static final int TRANSACTION_onSetObjectIdRequest = 5;
        static final int TRANSACTION_onTrackPositionSetRequest = 6;

        public Stub() {
            attachInterface(this, IBluetoothMcsCallbacks.DESCRIPTOR);
        }

        public static IBluetoothMcsCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothMcsCallbacks)) {
                return new Proxy(obj);
            }
            return (IBluetoothMcsCallbacks) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onServiceInstanceRegistered";
                case 2:
                    return "onServiceInstanceUnregistered";
                case 3:
                    return "onMediaControlRequest";
                case 4:
                    return "onSearchRequest";
                case 5:
                    return "onSetObjectIdRequest";
                case 6:
                    return "onTrackPositionSetRequest";
                case 7:
                    return "onPlaybackSpeedSetRequest";
                case 8:
                    return "onPlayingOrderSetRequest";
                case 9:
                    return "onCurrentTrackObjectIdSet";
                case 10:
                    return "onNextTrackObjectIdSet";
                case 11:
                    return "onCurrentGroupObjectIdSet";
                case 12:
                    return "onCurrentTrackMetadataRequest";
                case 13:
                    return "onPlayerStateRequest";
                case 14:
                    return "onGetFeatureFlags";
                case 15:
                    return "onGetCurrentTrackPosition";
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
            BluetoothDevice _arg1;
            BluetoothMcsSearchRequest _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothMcsCallbacks.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            IBinder _arg12 = data.readStrongBinder();
                            onServiceInstanceRegistered(_arg03, _arg12);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            onServiceInstanceUnregistered(_arg04);
                            return true;
                        case 3:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BluetoothMcsMediaControlRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onMediaControlRequest(_arg0, _arg1);
                            return true;
                        case 4:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = BluetoothMcsSearchRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onSearchRequest(_arg02);
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            long _arg13 = data.readLong();
                            onSetObjectIdRequest(_arg05, _arg13);
                            return true;
                        case 6:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            long _arg06 = data.readLong();
                            onTrackPositionSetRequest(_arg06);
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            float _arg07 = data.readFloat();
                            onPlaybackSpeedSetRequest(_arg07);
                            return true;
                        case 8:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            onPlayingOrderSetRequest(_arg08);
                            return true;
                        case 9:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            long _arg09 = data.readLong();
                            onCurrentTrackObjectIdSet(_arg09);
                            return true;
                        case 10:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            long _arg010 = data.readLong();
                            onNextTrackObjectIdSet(_arg010);
                            return true;
                        case 11:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            long _arg011 = data.readLong();
                            onCurrentGroupObjectIdSet(_arg011);
                            return true;
                        case 12:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            onCurrentTrackMetadataRequest();
                            return true;
                        case 13:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            int[] _arg012 = data.createIntArray();
                            onPlayerStateRequest(_arg012);
                            return true;
                        case 14:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            long _result = onGetFeatureFlags();
                            reply.writeNoException();
                            reply.writeLong(_result);
                            return true;
                        case 15:
                            data.enforceInterface(IBluetoothMcsCallbacks.DESCRIPTOR);
                            long _result2 = onGetCurrentTrackPosition();
                            reply.writeNoException();
                            reply.writeLong(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothMcsCallbacks {
            public static IBluetoothMcsCallbacks sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothMcsCallbacks.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onServiceInstanceRegistered(int status, IBinder service_proxy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeInt(status);
                    _data.writeStrongBinder(service_proxy);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onServiceInstanceRegistered(status, service_proxy);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onServiceInstanceUnregistered(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onServiceInstanceUnregistered(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onMediaControlRequest(BluetoothMcsMediaControlRequest request, BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaControlRequest(request, device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onSearchRequest(BluetoothMcsSearchRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSearchRequest(request);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onSetObjectIdRequest(int obj_field, long object_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeInt(obj_field);
                    _data.writeLong(object_id);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSetObjectIdRequest(obj_field, object_id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onTrackPositionSetRequest(long position) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeLong(position);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTrackPositionSetRequest(position);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onPlaybackSpeedSetRequest(float speed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeFloat(speed);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaybackSpeedSetRequest(speed);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onPlayingOrderSetRequest(int order) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeInt(order);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayingOrderSetRequest(order);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onCurrentTrackObjectIdSet(long object_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeLong(object_id);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrentTrackObjectIdSet(object_id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onNextTrackObjectIdSet(long object_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeLong(object_id);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNextTrackObjectIdSet(object_id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onCurrentGroupObjectIdSet(long object_id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeLong(object_id);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrentGroupObjectIdSet(object_id);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onCurrentTrackMetadataRequest() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrentTrackMetadataRequest();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public void onPlayerStateRequest(int[] state_fields) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    _data.writeIntArray(state_fields);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayerStateRequest(state_fields);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public long onGetFeatureFlags() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onGetFeatureFlags();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsCallbacks
            public long onGetCurrentTrackPosition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsCallbacks.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onGetCurrentTrackPosition();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothMcsCallbacks impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothMcsCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
