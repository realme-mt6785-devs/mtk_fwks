package android.bluetooth;

import android.bluetooth.IBluetoothTbsCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.mediatek.bt.BluetoothTbsCall;
import java.util.List;
/* loaded from: classes.dex */
public interface IBluetoothTbs extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothTbs";

    void callAdded(int i, BluetoothTbsCall bluetoothTbsCall) throws RemoteException;

    void callRemoved(int i, ParcelUuid parcelUuid, int i2) throws RemoteException;

    void callStateChanged(int i, ParcelUuid parcelUuid, int i2) throws RemoteException;

    boolean connectAudio() throws RemoteException;

    void currentCallsList(int i, List<BluetoothTbsCall> list) throws RemoteException;

    boolean disconnectAudio() throws RemoteException;

    int getAudioState(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean isAudioOn() throws RemoteException;

    boolean isInbandRingingEnabled() throws RemoteException;

    void networkStateChanged(int i, String str, int i2) throws RemoteException;

    void phoneStateChanged(int i, int i2, int i3, String str, int i4, String str2) throws RemoteException;

    void registerBearer(String str, IBluetoothTbsCallback iBluetoothTbsCallback, String str2, List<String> list, int i, String str3, int i2) throws RemoteException;

    void requestResult(int i, int i2, int i3) throws RemoteException;

    boolean startIsoUsingVirtualVoiceCall() throws RemoteException;

    boolean startVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean stopIsoUsingVirtualVoiceCall() throws RemoteException;

    boolean stopVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    void unregisterBearer(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothTbs {
        @Override // android.bluetooth.IBluetoothTbs
        public void registerBearer(String token, IBluetoothTbsCallback callback, String uci, List<String> uriSchemes, int capabilities, String provider, int technology) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void unregisterBearer(String token) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void requestResult(int ccid, int requestId, int result) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void callAdded(int ccid, BluetoothTbsCall call) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void callRemoved(int ccid, ParcelUuid callId, int reason) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void callStateChanged(int ccid, ParcelUuid callId, int state) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void currentCallsList(int ccid, List<BluetoothTbsCall> calls) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void networkStateChanged(int ccid, String provider, int technology) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public void phoneStateChanged(int numActive, int numHeld, int callState, String number, int type, String name) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean isInbandRingingEnabled() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean startVoiceRecognition(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean stopVoiceRecognition(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean isAudioOn() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean connectAudio() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean disconnectAudio() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean startIsoUsingVirtualVoiceCall() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public boolean stopIsoUsingVirtualVoiceCall() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothTbs
        public int getAudioState(BluetoothDevice device) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothTbs {
        static final int TRANSACTION_callAdded = 4;
        static final int TRANSACTION_callRemoved = 5;
        static final int TRANSACTION_callStateChanged = 6;
        static final int TRANSACTION_connectAudio = 14;
        static final int TRANSACTION_currentCallsList = 7;
        static final int TRANSACTION_disconnectAudio = 15;
        static final int TRANSACTION_getAudioState = 18;
        static final int TRANSACTION_isAudioOn = 13;
        static final int TRANSACTION_isInbandRingingEnabled = 10;
        static final int TRANSACTION_networkStateChanged = 8;
        static final int TRANSACTION_phoneStateChanged = 9;
        static final int TRANSACTION_registerBearer = 1;
        static final int TRANSACTION_requestResult = 3;
        static final int TRANSACTION_startIsoUsingVirtualVoiceCall = 16;
        static final int TRANSACTION_startVoiceRecognition = 11;
        static final int TRANSACTION_stopIsoUsingVirtualVoiceCall = 17;
        static final int TRANSACTION_stopVoiceRecognition = 12;
        static final int TRANSACTION_unregisterBearer = 2;

        public Stub() {
            attachInterface(this, IBluetoothTbs.DESCRIPTOR);
        }

        public static IBluetoothTbs asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothTbs.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothTbs)) {
                return new Proxy(obj);
            }
            return (IBluetoothTbs) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerBearer";
                case 2:
                    return "unregisterBearer";
                case 3:
                    return "requestResult";
                case 4:
                    return "callAdded";
                case 5:
                    return "callRemoved";
                case 6:
                    return "callStateChanged";
                case 7:
                    return "currentCallsList";
                case 8:
                    return "networkStateChanged";
                case 9:
                    return "phoneStateChanged";
                case 10:
                    return "isInbandRingingEnabled";
                case 11:
                    return "startVoiceRecognition";
                case 12:
                    return "stopVoiceRecognition";
                case 13:
                    return "isAudioOn";
                case 14:
                    return "connectAudio";
                case 15:
                    return "disconnectAudio";
                case 16:
                    return "startIsoUsingVirtualVoiceCall";
                case 17:
                    return "stopIsoUsingVirtualVoiceCall";
                case 18:
                    return "getAudioState";
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
            BluetoothTbsCall _arg1;
            ParcelUuid _arg12;
            ParcelUuid _arg13;
            BluetoothDevice _arg0;
            BluetoothDevice _arg02;
            BluetoothDevice _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBluetoothTbs.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            String _arg04 = data.readString();
                            IBluetoothTbsCallback _arg14 = IBluetoothTbsCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg2 = data.readString();
                            List<String> _arg3 = data.createStringArrayList();
                            int _arg4 = data.readInt();
                            String _arg5 = data.readString();
                            int _arg6 = data.readInt();
                            registerBearer(_arg04, _arg14, _arg2, _arg3, _arg4, _arg5, _arg6);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            String _arg05 = data.readString();
                            unregisterBearer(_arg05);
                            return true;
                        case 3:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg15 = data.readInt();
                            int _arg22 = data.readInt();
                            requestResult(_arg06, _arg15, _arg22);
                            return true;
                        case 4:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = BluetoothTbsCall.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            callAdded(_arg07, _arg1);
                            return true;
                        case 5:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            int _arg23 = data.readInt();
                            callRemoved(_arg08, _arg12, _arg23);
                            return true;
                        case 6:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            int _arg24 = data.readInt();
                            callStateChanged(_arg09, _arg13, _arg24);
                            return true;
                        case 7:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            List<BluetoothTbsCall> _arg16 = data.createTypedArrayList(BluetoothTbsCall.CREATOR);
                            currentCallsList(_arg010, _arg16);
                            return true;
                        case 8:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            String _arg17 = data.readString();
                            int _arg25 = data.readInt();
                            networkStateChanged(_arg011, _arg17, _arg25);
                            return true;
                        case 9:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg18 = data.readInt();
                            int _arg26 = data.readInt();
                            String _arg32 = data.readString();
                            int _arg42 = data.readInt();
                            String _arg52 = data.readString();
                            phoneStateChanged(_arg012, _arg18, _arg26, _arg32, _arg42, _arg52);
                            return true;
                        case 10:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            boolean isInbandRingingEnabled = isInbandRingingEnabled();
                            reply.writeNoException();
                            reply.writeInt(isInbandRingingEnabled ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean startVoiceRecognition = startVoiceRecognition(_arg0);
                            reply.writeNoException();
                            reply.writeInt(startVoiceRecognition ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            boolean stopVoiceRecognition = stopVoiceRecognition(_arg02);
                            reply.writeNoException();
                            reply.writeInt(stopVoiceRecognition ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            boolean isAudioOn = isAudioOn();
                            reply.writeNoException();
                            reply.writeInt(isAudioOn ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            boolean connectAudio = connectAudio();
                            reply.writeNoException();
                            reply.writeInt(connectAudio ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            boolean disconnectAudio = disconnectAudio();
                            reply.writeNoException();
                            reply.writeInt(disconnectAudio ? 1 : 0);
                            return true;
                        case 16:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            boolean startIsoUsingVirtualVoiceCall = startIsoUsingVirtualVoiceCall();
                            reply.writeNoException();
                            reply.writeInt(startIsoUsingVirtualVoiceCall ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            boolean stopIsoUsingVirtualVoiceCall = stopIsoUsingVirtualVoiceCall();
                            reply.writeNoException();
                            reply.writeInt(stopIsoUsingVirtualVoiceCall ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(IBluetoothTbs.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _result = getAudioState(_arg03);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothTbs {
            public static IBluetoothTbs sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothTbs.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void registerBearer(String token, IBluetoothTbsCallback callback, String uci, List<String> uriSchemes, int capabilities, String provider, int technology) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    try {
                        _data.writeString(token);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        try {
                            _data.writeString(uci);
                            try {
                                _data.writeStringList(uriSchemes);
                                try {
                                    _data.writeInt(capabilities);
                                    try {
                                        _data.writeString(provider);
                                        try {
                                            _data.writeInt(technology);
                                            boolean _status = this.mRemote.transact(1, _data, null, 1);
                                            if (_status || Stub.getDefaultImpl() == null) {
                                                _data.recycle();
                                                return;
                                            }
                                            Stub.getDefaultImpl().registerBearer(token, callback, uci, uriSchemes, capabilities, provider, technology);
                                            _data.recycle();
                                        } catch (Throwable th2) {
                                            th = th2;
                                            _data.recycle();
                                            throw th;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        _data.recycle();
                                        throw th;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void unregisterBearer(String token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    _data.writeString(token);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterBearer(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void requestResult(int ccid, int requestId, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    _data.writeInt(ccid);
                    _data.writeInt(requestId);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestResult(ccid, requestId, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void callAdded(int ccid, BluetoothTbsCall call) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    _data.writeInt(ccid);
                    if (call != null) {
                        _data.writeInt(1);
                        call.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callAdded(ccid, call);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void callRemoved(int ccid, ParcelUuid callId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    _data.writeInt(ccid);
                    if (callId != null) {
                        _data.writeInt(1);
                        callId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callRemoved(ccid, callId, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void callStateChanged(int ccid, ParcelUuid callId, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    _data.writeInt(ccid);
                    if (callId != null) {
                        _data.writeInt(1);
                        callId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callStateChanged(ccid, callId, state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void currentCallsList(int ccid, List<BluetoothTbsCall> calls) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    _data.writeInt(ccid);
                    _data.writeTypedList(calls);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().currentCallsList(ccid, calls);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void networkStateChanged(int ccid, String provider, int technology) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    _data.writeInt(ccid);
                    _data.writeString(provider);
                    _data.writeInt(technology);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkStateChanged(ccid, provider, technology);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public void phoneStateChanged(int numActive, int numHeld, int callState, String number, int type, String name) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeInt(numActive);
                    try {
                        _data.writeInt(numHeld);
                        try {
                            _data.writeInt(callState);
                            try {
                                _data.writeString(number);
                            } catch (Throwable th3) {
                                th = th3;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(type);
                        try {
                            _data.writeString(name);
                            try {
                                boolean _status = this.mRemote.transact(9, _data, null, 1);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().phoneStateChanged(numActive, numHeld, callState, number, type, name);
                                _data.recycle();
                            } catch (Throwable th6) {
                                th = th6;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th9) {
                    th = th9;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean isInbandRingingEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInbandRingingEnabled();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean startVoiceRecognition(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startVoiceRecognition(device);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean stopVoiceRecognition(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopVoiceRecognition(device);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean isAudioOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAudioOn();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean connectAudio() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectAudio();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean disconnectAudio() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectAudio();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean startIsoUsingVirtualVoiceCall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startIsoUsingVirtualVoiceCall();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public boolean stopIsoUsingVirtualVoiceCall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopIsoUsingVirtualVoiceCall();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothTbs
            public int getAudioState(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothTbs.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioState(device);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothTbs impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothTbs getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
