package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.ImsCallProfile;
import android.telephony.ims.RtpHeaderExtensionType;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsMmTelListener;
import android.telephony.ims.aidl.IImsSmsListener;
import android.telephony.ims.feature.CapabilityChangeRequest;
import com.android.ims.internal.IImsCallSession;
import com.android.ims.internal.IImsEcbm;
import com.android.ims.internal.IImsMultiEndpoint;
import com.android.ims.internal.IImsUt;
import java.util.List;
/* loaded from: classes3.dex */
public interface IImsMmTelFeature extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsMmTelFeature";

    void acknowledgeSms(int i, int i2, int i3) throws RemoteException;

    void acknowledgeSmsReport(int i, int i2, int i3) throws RemoteException;

    void addCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void changeCapabilitiesConfiguration(CapabilityChangeRequest capabilityChangeRequest, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void changeOfferedRtpHeaderExtensionTypes(List<RtpHeaderExtensionType> list) throws RemoteException;

    ImsCallProfile createCallProfile(int i, int i2) throws RemoteException;

    IImsCallSession createCallSession(ImsCallProfile imsCallProfile) throws RemoteException;

    IImsEcbm getEcbmInterface() throws RemoteException;

    int getFeatureState() throws RemoteException;

    IImsMultiEndpoint getMultiEndpointInterface() throws RemoteException;

    String getSmsFormat() throws RemoteException;

    IImsUt getUtInterface() throws RemoteException;

    void onSmsReady() throws RemoteException;

    void queryCapabilityConfiguration(int i, int i2, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    int queryCapabilityStatus() throws RemoteException;

    void removeCapabilityCallback(IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void sendSms(int i, int i2, String str, String str2, boolean z, byte[] bArr) throws RemoteException;

    void setListener(IImsMmTelListener iImsMmTelListener) throws RemoteException;

    void setSmsListener(IImsSmsListener iImsSmsListener) throws RemoteException;

    void setUiTtyMode(int i, Message message) throws RemoteException;

    int shouldProcessCall(String[] strArr) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IImsMmTelFeature {
        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void setListener(IImsMmTelListener l) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public int getFeatureState() throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public ImsCallProfile createCallProfile(int callSessionType, int callType) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void changeOfferedRtpHeaderExtensionTypes(List<RtpHeaderExtensionType> types) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsCallSession createCallSession(ImsCallProfile profile) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public int shouldProcessCall(String[] uris) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsUt getUtInterface() throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsEcbm getEcbmInterface() throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void setUiTtyMode(int uiTtyMode, Message onCompleteMessage) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public IImsMultiEndpoint getMultiEndpointInterface() throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public int queryCapabilityStatus() throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void addCapabilityCallback(IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void removeCapabilityCallback(IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void changeCapabilitiesConfiguration(CapabilityChangeRequest request, IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void queryCapabilityConfiguration(int capability, int radioTech, IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void setSmsListener(IImsSmsListener l) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void sendSms(int token, int messageRef, String format, String smsc, boolean retry, byte[] pdu) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void acknowledgeSms(int token, int messageRef, int result) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void acknowledgeSmsReport(int token, int messageRef, int result) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public String getSmsFormat() throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsMmTelFeature
        public void onSmsReady() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IImsMmTelFeature {
        static final int TRANSACTION_acknowledgeSms = 18;
        static final int TRANSACTION_acknowledgeSmsReport = 19;
        static final int TRANSACTION_addCapabilityCallback = 12;
        static final int TRANSACTION_changeCapabilitiesConfiguration = 14;
        static final int TRANSACTION_changeOfferedRtpHeaderExtensionTypes = 4;
        static final int TRANSACTION_createCallProfile = 3;
        static final int TRANSACTION_createCallSession = 5;
        static final int TRANSACTION_getEcbmInterface = 8;
        static final int TRANSACTION_getFeatureState = 2;
        static final int TRANSACTION_getMultiEndpointInterface = 10;
        static final int TRANSACTION_getSmsFormat = 20;
        static final int TRANSACTION_getUtInterface = 7;
        static final int TRANSACTION_onSmsReady = 21;
        static final int TRANSACTION_queryCapabilityConfiguration = 15;
        static final int TRANSACTION_queryCapabilityStatus = 11;
        static final int TRANSACTION_removeCapabilityCallback = 13;
        static final int TRANSACTION_sendSms = 17;
        static final int TRANSACTION_setListener = 1;
        static final int TRANSACTION_setSmsListener = 16;
        static final int TRANSACTION_setUiTtyMode = 9;
        static final int TRANSACTION_shouldProcessCall = 6;

        public Stub() {
            attachInterface(this, IImsMmTelFeature.DESCRIPTOR);
        }

        public static IImsMmTelFeature asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImsMmTelFeature.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImsMmTelFeature)) {
                return new Proxy(obj);
            }
            return (IImsMmTelFeature) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setListener";
                case 2:
                    return "getFeatureState";
                case 3:
                    return "createCallProfile";
                case 4:
                    return "changeOfferedRtpHeaderExtensionTypes";
                case 5:
                    return "createCallSession";
                case 6:
                    return "shouldProcessCall";
                case 7:
                    return "getUtInterface";
                case 8:
                    return "getEcbmInterface";
                case 9:
                    return "setUiTtyMode";
                case 10:
                    return "getMultiEndpointInterface";
                case 11:
                    return "queryCapabilityStatus";
                case 12:
                    return "addCapabilityCallback";
                case 13:
                    return "removeCapabilityCallback";
                case 14:
                    return "changeCapabilitiesConfiguration";
                case 15:
                    return "queryCapabilityConfiguration";
                case 16:
                    return "setSmsListener";
                case 17:
                    return "sendSms";
                case 18:
                    return "acknowledgeSms";
                case 19:
                    return "acknowledgeSmsReport";
                case 20:
                    return "getSmsFormat";
                case 21:
                    return "onSmsReady";
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
            ImsCallProfile _arg0;
            Message _arg1;
            CapabilityChangeRequest _arg02;
            boolean _arg4;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IImsMmTelFeature.DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            IImsMmTelListener _arg03 = IImsMmTelListener.Stub.asInterface(data.readStrongBinder());
                            setListener(_arg03);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _result = getFeatureState();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 3:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg12 = data.readInt();
                            ImsCallProfile _result2 = createCallProfile(_arg04, _arg12);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            List<RtpHeaderExtensionType> _arg05 = data.createTypedArrayList(RtpHeaderExtensionType.CREATOR);
                            changeOfferedRtpHeaderExtensionTypes(_arg05);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ImsCallProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IImsCallSession _result3 = createCallSession(_arg0);
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 6:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            String[] _arg06 = data.createStringArray();
                            int _result4 = shouldProcessCall(_arg06);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            IImsUt _result5 = getUtInterface();
                            reply.writeNoException();
                            if (_result5 != null) {
                                iBinder = _result5.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 8:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            IImsEcbm _result6 = getEcbmInterface();
                            reply.writeNoException();
                            if (_result6 != null) {
                                iBinder = _result6.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 9:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Message.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            setUiTtyMode(_arg07, _arg1);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            IImsMultiEndpoint _result7 = getMultiEndpointInterface();
                            reply.writeNoException();
                            if (_result7 != null) {
                                iBinder = _result7.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 11:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _result8 = queryCapabilityStatus();
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 12:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            IImsCapabilityCallback _arg08 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            addCapabilityCallback(_arg08);
                            return true;
                        case 13:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            IImsCapabilityCallback _arg09 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            removeCapabilityCallback(_arg09);
                            return true;
                        case 14:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = CapabilityChangeRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            IImsCapabilityCallback _arg13 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            changeCapabilitiesConfiguration(_arg02, _arg13);
                            return true;
                        case 15:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg14 = data.readInt();
                            IImsCapabilityCallback _arg2 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            queryCapabilityConfiguration(_arg010, _arg14, _arg2);
                            return true;
                        case 16:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            IImsSmsListener _arg011 = IImsSmsListener.Stub.asInterface(data.readStrongBinder());
                            setSmsListener(_arg011);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg15 = data.readInt();
                            String _arg22 = data.readString();
                            String _arg3 = data.readString();
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            byte[] _arg5 = data.createByteArray();
                            sendSms(_arg012, _arg15, _arg22, _arg3, _arg4, _arg5);
                            return true;
                        case 18:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _arg16 = data.readInt();
                            int _arg23 = data.readInt();
                            acknowledgeSms(_arg013, _arg16, _arg23);
                            return true;
                        case 19:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            int _arg17 = data.readInt();
                            int _arg24 = data.readInt();
                            acknowledgeSmsReport(_arg014, _arg17, _arg24);
                            return true;
                        case 20:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            String _result9 = getSmsFormat();
                            reply.writeNoException();
                            reply.writeString(_result9);
                            return true;
                        case 21:
                            data.enforceInterface(IImsMmTelFeature.DESCRIPTOR);
                            onSmsReady();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IImsMmTelFeature {
            public static IImsMmTelFeature sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImsMmTelFeature.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void setListener(IImsMmTelListener l) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeStrongBinder(l != null ? l.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setListener(l);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public int getFeatureState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFeatureState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public ImsCallProfile createCallProfile(int callSessionType, int callType) throws RemoteException {
                ImsCallProfile _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeInt(callSessionType);
                    _data.writeInt(callType);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createCallProfile(callSessionType, callType);
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

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void changeOfferedRtpHeaderExtensionTypes(List<RtpHeaderExtensionType> types) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeTypedList(types);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().changeOfferedRtpHeaderExtensionTypes(types);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public IImsCallSession createCallSession(ImsCallProfile profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    if (profile != null) {
                        _data.writeInt(1);
                        profile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createCallSession(profile);
                    }
                    _reply.readException();
                    IImsCallSession _result = IImsCallSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public int shouldProcessCall(String[] uris) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeStringArray(uris);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldProcessCall(uris);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public IImsUt getUtInterface() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUtInterface();
                    }
                    _reply.readException();
                    IImsUt _result = IImsUt.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public IImsEcbm getEcbmInterface() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEcbmInterface();
                    }
                    _reply.readException();
                    IImsEcbm _result = IImsEcbm.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void setUiTtyMode(int uiTtyMode, Message onCompleteMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeInt(uiTtyMode);
                    if (onCompleteMessage != null) {
                        _data.writeInt(1);
                        onCompleteMessage.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUiTtyMode(uiTtyMode, onCompleteMessage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public IImsMultiEndpoint getMultiEndpointInterface() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMultiEndpointInterface();
                    }
                    _reply.readException();
                    IImsMultiEndpoint _result = IImsMultiEndpoint.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public int queryCapabilityStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryCapabilityStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void addCapabilityCallback(IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCapabilityCallback(c);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void removeCapabilityCallback(IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeCapabilityCallback(c);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void changeCapabilitiesConfiguration(CapabilityChangeRequest request, IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().changeCapabilitiesConfiguration(request, c);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void queryCapabilityConfiguration(int capability, int radioTech, IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeInt(capability);
                    _data.writeInt(radioTech);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryCapabilityConfiguration(capability, radioTech, c);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void setSmsListener(IImsSmsListener l) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeStrongBinder(l != null ? l.asBinder() : null);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSmsListener(l);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void sendSms(int token, int messageRef, String format, String smsc, boolean retry, byte[] pdu) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    try {
                        _data.writeInt(token);
                        try {
                            _data.writeInt(messageRef);
                            try {
                                _data.writeString(format);
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
                }
                try {
                    _data.writeString(smsc);
                    _data.writeInt(retry ? 1 : 0);
                    try {
                        _data.writeByteArray(pdu);
                        try {
                            boolean _status = this.mRemote.transact(17, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().sendSms(token, messageRef, format, smsc, retry, pdu);
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
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void acknowledgeSms(int token, int messageRef, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeInt(token);
                    _data.writeInt(messageRef);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acknowledgeSms(token, messageRef, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void acknowledgeSmsReport(int token, int messageRef, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    _data.writeInt(token);
                    _data.writeInt(messageRef);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acknowledgeSmsReport(token, messageRef, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public String getSmsFormat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmsFormat();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsMmTelFeature
            public void onSmsReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsMmTelFeature.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSmsReady();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsMmTelFeature impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImsMmTelFeature getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
