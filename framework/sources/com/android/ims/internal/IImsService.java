package com.android.ims.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.ImsCallProfile;
import android.telephony.ims.RcsContactPresenceTuple;
import com.android.ims.internal.IImsCallSession;
import com.android.ims.internal.IImsCallSessionListener;
import com.android.ims.internal.IImsConfig;
import com.android.ims.internal.IImsEcbm;
import com.android.ims.internal.IImsMultiEndpoint;
import com.android.ims.internal.IImsRegistrationListener;
import com.android.ims.internal.IImsUt;
/* loaded from: classes3.dex */
public interface IImsService extends IInterface {
    void addRegistrationListener(int i, int i2, IImsRegistrationListener iImsRegistrationListener) throws RemoteException;

    void close(int i) throws RemoteException;

    ImsCallProfile createCallProfile(int i, int i2, int i3) throws RemoteException;

    IImsCallSession createCallSession(int i, ImsCallProfile imsCallProfile, IImsCallSessionListener iImsCallSessionListener) throws RemoteException;

    IImsConfig getConfigInterface(int i) throws RemoteException;

    IImsEcbm getEcbmInterface(int i) throws RemoteException;

    IImsMultiEndpoint getMultiEndpointInterface(int i) throws RemoteException;

    IImsCallSession getPendingCallSession(int i, String str) throws RemoteException;

    IImsUt getUtInterface(int i) throws RemoteException;

    boolean isConnected(int i, int i2, int i3) throws RemoteException;

    boolean isOpened(int i) throws RemoteException;

    int open(int i, int i2, PendingIntent pendingIntent, IImsRegistrationListener iImsRegistrationListener) throws RemoteException;

    void setRegistrationListener(int i, IImsRegistrationListener iImsRegistrationListener) throws RemoteException;

    void setUiTTYMode(int i, int i2, Message message) throws RemoteException;

    void turnOffIms(int i) throws RemoteException;

    void turnOnIms(int i) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IImsService {
        @Override // com.android.ims.internal.IImsService
        public int open(int phoneId, int serviceClass, PendingIntent incomingCallIntent, IImsRegistrationListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.android.ims.internal.IImsService
        public void close(int serviceId) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsService
        public boolean isConnected(int serviceId, int serviceType, int callType) throws RemoteException {
            return false;
        }

        @Override // com.android.ims.internal.IImsService
        public boolean isOpened(int serviceId) throws RemoteException {
            return false;
        }

        @Override // com.android.ims.internal.IImsService
        public void setRegistrationListener(int serviceId, IImsRegistrationListener listener) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsService
        public void addRegistrationListener(int phoneId, int serviceClass, IImsRegistrationListener listener) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsService
        public ImsCallProfile createCallProfile(int serviceId, int serviceType, int callType) throws RemoteException {
            return null;
        }

        @Override // com.android.ims.internal.IImsService
        public IImsCallSession createCallSession(int serviceId, ImsCallProfile profile, IImsCallSessionListener listener) throws RemoteException {
            return null;
        }

        @Override // com.android.ims.internal.IImsService
        public IImsCallSession getPendingCallSession(int serviceId, String callId) throws RemoteException {
            return null;
        }

        @Override // com.android.ims.internal.IImsService
        public IImsUt getUtInterface(int serviceId) throws RemoteException {
            return null;
        }

        @Override // com.android.ims.internal.IImsService
        public IImsConfig getConfigInterface(int phoneId) throws RemoteException {
            return null;
        }

        @Override // com.android.ims.internal.IImsService
        public void turnOnIms(int phoneId) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsService
        public void turnOffIms(int phoneId) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsService
        public IImsEcbm getEcbmInterface(int serviceId) throws RemoteException {
            return null;
        }

        @Override // com.android.ims.internal.IImsService
        public void setUiTTYMode(int serviceId, int uiTtyMode, Message onComplete) throws RemoteException {
        }

        @Override // com.android.ims.internal.IImsService
        public IImsMultiEndpoint getMultiEndpointInterface(int serviceId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IImsService {
        public static final String DESCRIPTOR = "com.android.ims.internal.IImsService";
        static final int TRANSACTION_addRegistrationListener = 6;
        static final int TRANSACTION_close = 2;
        static final int TRANSACTION_createCallProfile = 7;
        static final int TRANSACTION_createCallSession = 8;
        static final int TRANSACTION_getConfigInterface = 11;
        static final int TRANSACTION_getEcbmInterface = 14;
        static final int TRANSACTION_getMultiEndpointInterface = 16;
        static final int TRANSACTION_getPendingCallSession = 9;
        static final int TRANSACTION_getUtInterface = 10;
        static final int TRANSACTION_isConnected = 3;
        static final int TRANSACTION_isOpened = 4;
        static final int TRANSACTION_open = 1;
        static final int TRANSACTION_setRegistrationListener = 5;
        static final int TRANSACTION_setUiTTYMode = 15;
        static final int TRANSACTION_turnOffIms = 13;
        static final int TRANSACTION_turnOnIms = 12;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IImsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IImsService)) {
                return new Proxy(obj);
            }
            return (IImsService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return RcsContactPresenceTuple.TUPLE_BASIC_STATUS_OPEN;
                case 2:
                    return "close";
                case 3:
                    return "isConnected";
                case 4:
                    return "isOpened";
                case 5:
                    return "setRegistrationListener";
                case 6:
                    return "addRegistrationListener";
                case 7:
                    return "createCallProfile";
                case 8:
                    return "createCallSession";
                case 9:
                    return "getPendingCallSession";
                case 10:
                    return "getUtInterface";
                case 11:
                    return "getConfigInterface";
                case 12:
                    return "turnOnIms";
                case 13:
                    return "turnOffIms";
                case 14:
                    return "getEcbmInterface";
                case 15:
                    return "setUiTTYMode";
                case 16:
                    return "getMultiEndpointInterface";
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
            PendingIntent _arg2;
            ImsCallProfile _arg1;
            Message _arg22;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg12 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            IImsRegistrationListener _arg3 = IImsRegistrationListener.Stub.asInterface(data.readStrongBinder());
                            int _result = open(_arg0, _arg12, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            close(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _arg13 = data.readInt();
                            int _arg23 = data.readInt();
                            boolean isConnected = isConnected(_arg03, _arg13, _arg23);
                            reply.writeNoException();
                            reply.writeInt(isConnected ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            boolean isOpened = isOpened(_arg04);
                            reply.writeNoException();
                            reply.writeInt(isOpened ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            IImsRegistrationListener _arg14 = IImsRegistrationListener.Stub.asInterface(data.readStrongBinder());
                            setRegistrationListener(_arg05, _arg14);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg15 = data.readInt();
                            IImsRegistrationListener _arg24 = IImsRegistrationListener.Stub.asInterface(data.readStrongBinder());
                            addRegistrationListener(_arg06, _arg15, _arg24);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg16 = data.readInt();
                            int _arg25 = data.readInt();
                            ImsCallProfile _result2 = createCallProfile(_arg07, _arg16, _arg25);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IImsCallSessionListener _arg26 = IImsCallSessionListener.Stub.asInterface(data.readStrongBinder());
                            IImsCallSession _result3 = createCallSession(_arg08, _arg1, _arg26);
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            String _arg17 = data.readString();
                            IImsCallSession _result4 = getPendingCallSession(_arg09, _arg17);
                            reply.writeNoException();
                            if (_result4 != null) {
                                iBinder = _result4.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            IImsUt _result5 = getUtInterface(_arg010);
                            reply.writeNoException();
                            if (_result5 != null) {
                                iBinder = _result5.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            IImsConfig _result6 = getConfigInterface(_arg011);
                            reply.writeNoException();
                            if (_result6 != null) {
                                iBinder = _result6.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            turnOnIms(_arg012);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            turnOffIms(_arg013);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            IImsEcbm _result7 = getEcbmInterface(_arg014);
                            reply.writeNoException();
                            if (_result7 != null) {
                                iBinder = _result7.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            int _arg18 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = Message.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            setUiTTYMode(_arg015, _arg18, _arg22);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            IImsMultiEndpoint _result8 = getMultiEndpointInterface(_arg016);
                            reply.writeNoException();
                            if (_result8 != null) {
                                iBinder = _result8.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IImsService {
            public static IImsService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.android.ims.internal.IImsService
            public int open(int phoneId, int serviceClass, PendingIntent incomingCallIntent, IImsRegistrationListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    _data.writeInt(serviceClass);
                    if (incomingCallIntent != null) {
                        _data.writeInt(1);
                        incomingCallIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().open(phoneId, serviceClass, incomingCallIntent, listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public void close(int serviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().close(serviceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public boolean isConnected(int serviceId, int serviceType, int callType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    _data.writeInt(serviceType);
                    _data.writeInt(callType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConnected(serviceId, serviceType, callType);
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

            @Override // com.android.ims.internal.IImsService
            public boolean isOpened(int serviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOpened(serviceId);
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

            @Override // com.android.ims.internal.IImsService
            public void setRegistrationListener(int serviceId, IImsRegistrationListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRegistrationListener(serviceId, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public void addRegistrationListener(int phoneId, int serviceClass, IImsRegistrationListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    _data.writeInt(serviceClass);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addRegistrationListener(phoneId, serviceClass, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public ImsCallProfile createCallProfile(int serviceId, int serviceType, int callType) throws RemoteException {
                ImsCallProfile _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    _data.writeInt(serviceType);
                    _data.writeInt(callType);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createCallProfile(serviceId, serviceType, callType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ImsCallProfile.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public IImsCallSession createCallSession(int serviceId, ImsCallProfile profile, IImsCallSessionListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createCallSession(serviceId, profile, listener);
                    }
                    _reply.readException();
                    IImsCallSession _result = IImsCallSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public IImsCallSession getPendingCallSession(int serviceId, String callId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    _data.writeString(callId);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPendingCallSession(serviceId, callId);
                    }
                    _reply.readException();
                    IImsCallSession _result = IImsCallSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public IImsUt getUtInterface(int serviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUtInterface(serviceId);
                    }
                    _reply.readException();
                    IImsUt _result = IImsUt.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public IImsConfig getConfigInterface(int phoneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigInterface(phoneId);
                    }
                    _reply.readException();
                    IImsConfig _result = IImsConfig.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public void turnOnIms(int phoneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().turnOnIms(phoneId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public void turnOffIms(int phoneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().turnOffIms(phoneId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public IImsEcbm getEcbmInterface(int serviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEcbmInterface(serviceId);
                    }
                    _reply.readException();
                    IImsEcbm _result = IImsEcbm.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public void setUiTTYMode(int serviceId, int uiTtyMode, Message onComplete) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    _data.writeInt(uiTtyMode);
                    if (onComplete != null) {
                        _data.writeInt(1);
                        onComplete.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUiTTYMode(serviceId, uiTtyMode, onComplete);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.ims.internal.IImsService
            public IImsMultiEndpoint getMultiEndpointInterface(int serviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceId);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMultiEndpointInterface(serviceId);
                    }
                    _reply.readException();
                    IImsMultiEndpoint _result = IImsMultiEndpoint.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
