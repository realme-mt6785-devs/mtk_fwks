package android.telephony.ims.aidl;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.DelegateRequest;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.telephony.ims.aidl.IRcsUceControllerCallback;
import android.telephony.ims.aidl.IRcsUcePublishStateCallback;
import android.telephony.ims.aidl.ISipDelegate;
import android.telephony.ims.aidl.ISipDelegateConnectionStateCallback;
import android.telephony.ims.aidl.ISipDelegateMessageCallback;
import com.android.ims.internal.IImsServiceFeatureCallback;
import com.android.internal.telephony.IIntegerConsumer;
import java.util.List;
/* loaded from: classes3.dex */
public interface IImsRcsController extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsRcsController";

    void createSipDelegate(int i, DelegateRequest delegateRequest, String str, ISipDelegateConnectionStateCallback iSipDelegateConnectionStateCallback, ISipDelegateMessageCallback iSipDelegateMessageCallback) throws RemoteException;

    void destroySipDelegate(int i, ISipDelegate iSipDelegate, int i2) throws RemoteException;

    void getImsRcsRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void getImsRcsRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    int getUcePublishState(int i) throws RemoteException;

    boolean isAvailable(int i, int i2, int i3) throws RemoteException;

    boolean isCapable(int i, int i2, int i3) throws RemoteException;

    boolean isSipDelegateSupported(int i) throws RemoteException;

    boolean isUceSettingEnabled(int i, String str, String str2) throws RemoteException;

    void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void registerRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void registerRcsFeatureCallback(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException;

    void registerUcePublishStateCallback(int i, IRcsUcePublishStateCallback iRcsUcePublishStateCallback) throws RemoteException;

    void requestAvailability(int i, String str, String str2, Uri uri, IRcsUceControllerCallback iRcsUceControllerCallback) throws RemoteException;

    void requestCapabilities(int i, String str, String str2, List<Uri> list, IRcsUceControllerCallback iRcsUceControllerCallback) throws RemoteException;

    void setUceSettingEnabled(int i, boolean z) throws RemoteException;

    void triggerNetworkRegistration(int i, ISipDelegate iSipDelegate, int i2, String str) throws RemoteException;

    void unregisterImsFeatureCallback(IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException;

    void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void unregisterRcsAvailabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void unregisterUcePublishStateCallback(int i, IRcsUcePublishStateCallback iRcsUcePublishStateCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IImsRcsController {
        @Override // android.telephony.ims.aidl.IImsRcsController
        public void registerImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void unregisterImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void getImsRcsRegistrationState(int subId, IIntegerConsumer consumer) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void getImsRcsRegistrationTransportType(int subId, IIntegerConsumer consumer) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void registerRcsAvailabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void unregisterRcsAvailabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public boolean isCapable(int subId, int capability, int radioTech) throws RemoteException {
            return false;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public boolean isAvailable(int subId, int capability, int radioTech) throws RemoteException {
            return false;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void requestCapabilities(int subId, String callingPackage, String callingFeatureId, List<Uri> contactNumbers, IRcsUceControllerCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void requestAvailability(int subId, String callingPackage, String callingFeatureId, Uri contactNumber, IRcsUceControllerCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public int getUcePublishState(int subId) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public boolean isUceSettingEnabled(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void setUceSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void registerUcePublishStateCallback(int subId, IRcsUcePublishStateCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void unregisterUcePublishStateCallback(int subId, IRcsUcePublishStateCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public boolean isSipDelegateSupported(int subId) throws RemoteException {
            return false;
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void createSipDelegate(int subId, DelegateRequest request, String packageName, ISipDelegateConnectionStateCallback delegateState, ISipDelegateMessageCallback delegateMessage) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void destroySipDelegate(int subId, ISipDelegate connection, int reason) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void triggerNetworkRegistration(int subId, ISipDelegate connection, int sipCode, String sipReason) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void registerRcsFeatureCallback(int slotId, IImsServiceFeatureCallback callback) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRcsController
        public void unregisterImsFeatureCallback(IImsServiceFeatureCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IImsRcsController {
        static final int TRANSACTION_createSipDelegate = 17;
        static final int TRANSACTION_destroySipDelegate = 18;
        static final int TRANSACTION_getImsRcsRegistrationState = 3;
        static final int TRANSACTION_getImsRcsRegistrationTransportType = 4;
        static final int TRANSACTION_getUcePublishState = 11;
        static final int TRANSACTION_isAvailable = 8;
        static final int TRANSACTION_isCapable = 7;
        static final int TRANSACTION_isSipDelegateSupported = 16;
        static final int TRANSACTION_isUceSettingEnabled = 12;
        static final int TRANSACTION_registerImsRegistrationCallback = 1;
        static final int TRANSACTION_registerRcsAvailabilityCallback = 5;
        static final int TRANSACTION_registerRcsFeatureCallback = 20;
        static final int TRANSACTION_registerUcePublishStateCallback = 14;
        static final int TRANSACTION_requestAvailability = 10;
        static final int TRANSACTION_requestCapabilities = 9;
        static final int TRANSACTION_setUceSettingEnabled = 13;
        static final int TRANSACTION_triggerNetworkRegistration = 19;
        static final int TRANSACTION_unregisterImsFeatureCallback = 21;
        static final int TRANSACTION_unregisterImsRegistrationCallback = 2;
        static final int TRANSACTION_unregisterRcsAvailabilityCallback = 6;
        static final int TRANSACTION_unregisterUcePublishStateCallback = 15;

        public Stub() {
            attachInterface(this, IImsRcsController.DESCRIPTOR);
        }

        public static IImsRcsController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImsRcsController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImsRcsController)) {
                return new Proxy(obj);
            }
            return (IImsRcsController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerImsRegistrationCallback";
                case 2:
                    return "unregisterImsRegistrationCallback";
                case 3:
                    return "getImsRcsRegistrationState";
                case 4:
                    return "getImsRcsRegistrationTransportType";
                case 5:
                    return "registerRcsAvailabilityCallback";
                case 6:
                    return "unregisterRcsAvailabilityCallback";
                case 7:
                    return "isCapable";
                case 8:
                    return "isAvailable";
                case 9:
                    return "requestCapabilities";
                case 10:
                    return "requestAvailability";
                case 11:
                    return "getUcePublishState";
                case 12:
                    return "isUceSettingEnabled";
                case 13:
                    return "setUceSettingEnabled";
                case 14:
                    return "registerUcePublishStateCallback";
                case 15:
                    return "unregisterUcePublishStateCallback";
                case 16:
                    return "isSipDelegateSupported";
                case 17:
                    return "createSipDelegate";
                case 18:
                    return "destroySipDelegate";
                case 19:
                    return "triggerNetworkRegistration";
                case 20:
                    return "registerRcsFeatureCallback";
                case 21:
                    return "unregisterImsFeatureCallback";
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
            Uri _arg3;
            DelegateRequest _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IImsRcsController.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            IImsRegistrationCallback _arg12 = IImsRegistrationCallback.Stub.asInterface(data.readStrongBinder());
                            registerImsRegistrationCallback(_arg0, _arg12);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            IImsRegistrationCallback _arg13 = IImsRegistrationCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterImsRegistrationCallback(_arg02, _arg13);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            IIntegerConsumer _arg14 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
                            getImsRcsRegistrationState(_arg03, _arg14);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            IIntegerConsumer _arg15 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
                            getImsRcsRegistrationTransportType(_arg04, _arg15);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            IImsCapabilityCallback _arg16 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            registerRcsAvailabilityCallback(_arg05, _arg16);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            IImsCapabilityCallback _arg17 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterRcsAvailabilityCallback(_arg06, _arg17);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg18 = data.readInt();
                            int _arg2 = data.readInt();
                            boolean isCapable = isCapable(_arg07, _arg18, _arg2);
                            reply.writeNoException();
                            reply.writeInt(isCapable ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg19 = data.readInt();
                            int _arg22 = data.readInt();
                            boolean isAvailable = isAvailable(_arg08, _arg19, _arg22);
                            reply.writeNoException();
                            reply.writeInt(isAvailable ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            String _arg110 = data.readString();
                            String _arg23 = data.readString();
                            List<Uri> _arg32 = data.createTypedArrayList(Uri.CREATOR);
                            IRcsUceControllerCallback _arg4 = IRcsUceControllerCallback.Stub.asInterface(data.readStrongBinder());
                            requestCapabilities(_arg09, _arg110, _arg23, _arg32, _arg4);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            String _arg111 = data.readString();
                            String _arg24 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            IRcsUceControllerCallback _arg42 = IRcsUceControllerCallback.Stub.asInterface(data.readStrongBinder());
                            requestAvailability(_arg010, _arg111, _arg24, _arg3, _arg42);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _result = getUcePublishState(_arg011);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 12:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            String _arg112 = data.readString();
                            String _arg25 = data.readString();
                            boolean isUceSettingEnabled = isUceSettingEnabled(_arg012, _arg112, _arg25);
                            reply.writeNoException();
                            reply.writeInt(isUceSettingEnabled ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            boolean _arg113 = data.readInt() != 0;
                            setUceSettingEnabled(_arg013, _arg113);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            IRcsUcePublishStateCallback _arg114 = IRcsUcePublishStateCallback.Stub.asInterface(data.readStrongBinder());
                            registerUcePublishStateCallback(_arg014, _arg114);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg015 = data.readInt();
                            IRcsUcePublishStateCallback _arg115 = IRcsUcePublishStateCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterUcePublishStateCallback(_arg015, _arg115);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg016 = data.readInt();
                            boolean isSipDelegateSupported = isSipDelegateSupported(_arg016);
                            reply.writeNoException();
                            reply.writeInt(isSipDelegateSupported ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg017 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = DelegateRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg26 = data.readString();
                            ISipDelegateConnectionStateCallback _arg33 = ISipDelegateConnectionStateCallback.Stub.asInterface(data.readStrongBinder());
                            ISipDelegateMessageCallback _arg43 = ISipDelegateMessageCallback.Stub.asInterface(data.readStrongBinder());
                            createSipDelegate(_arg017, _arg1, _arg26, _arg33, _arg43);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg018 = data.readInt();
                            ISipDelegate _arg116 = ISipDelegate.Stub.asInterface(data.readStrongBinder());
                            int _arg27 = data.readInt();
                            destroySipDelegate(_arg018, _arg116, _arg27);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg019 = data.readInt();
                            ISipDelegate _arg117 = ISipDelegate.Stub.asInterface(data.readStrongBinder());
                            int _arg28 = data.readInt();
                            String _arg34 = data.readString();
                            triggerNetworkRegistration(_arg019, _arg117, _arg28, _arg34);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            int _arg020 = data.readInt();
                            IImsServiceFeatureCallback _arg118 = IImsServiceFeatureCallback.Stub.asInterface(data.readStrongBinder());
                            registerRcsFeatureCallback(_arg020, _arg118);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(IImsRcsController.DESCRIPTOR);
                            IImsServiceFeatureCallback _arg021 = IImsServiceFeatureCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterImsFeatureCallback(_arg021);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IImsRcsController {
            public static IImsRcsController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImsRcsController.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void registerImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerImsRegistrationCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void unregisterImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterImsRegistrationCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void getImsRcsRegistrationState(int subId, IIntegerConsumer consumer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(consumer != null ? consumer.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getImsRcsRegistrationState(subId, consumer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void getImsRcsRegistrationTransportType(int subId, IIntegerConsumer consumer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(consumer != null ? consumer.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getImsRcsRegistrationTransportType(subId, consumer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void registerRcsAvailabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerRcsAvailabilityCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void unregisterRcsAvailabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterRcsAvailabilityCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public boolean isCapable(int subId, int capability, int radioTech) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(radioTech);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCapable(subId, capability, radioTech);
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

            @Override // android.telephony.ims.aidl.IImsRcsController
            public boolean isAvailable(int subId, int capability, int radioTech) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(radioTech);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAvailable(subId, capability, radioTech);
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

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void requestCapabilities(int subId, String callingPackage, String callingFeatureId, List<Uri> contactNumbers, IRcsUceControllerCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    _data.writeTypedList(contactNumbers);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestCapabilities(subId, callingPackage, callingFeatureId, contactNumbers, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void requestAvailability(int subId, String callingPackage, String callingFeatureId, Uri contactNumber, IRcsUceControllerCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    if (contactNumber != null) {
                        _data.writeInt(1);
                        contactNumber.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestAvailability(subId, callingPackage, callingFeatureId, contactNumber, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public int getUcePublishState(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUcePublishState(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public boolean isUceSettingEnabled(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUceSettingEnabled(subId, callingPackage, callingFeatureId);
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

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void setUceSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUceSettingEnabled(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void registerUcePublishStateCallback(int subId, IRcsUcePublishStateCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerUcePublishStateCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void unregisterUcePublishStateCallback(int subId, IRcsUcePublishStateCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterUcePublishStateCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public boolean isSipDelegateSupported(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSipDelegateSupported(subId);
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

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void createSipDelegate(int subId, DelegateRequest request, String packageName, ISipDelegateConnectionStateCallback delegateState, ISipDelegateMessageCallback delegateMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    IBinder iBinder = null;
                    _data.writeStrongBinder(delegateState != null ? delegateState.asBinder() : null);
                    if (delegateMessage != null) {
                        iBinder = delegateMessage.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createSipDelegate(subId, request, packageName, delegateState, delegateMessage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void destroySipDelegate(int subId, ISipDelegate connection, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(connection != null ? connection.asBinder() : null);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().destroySipDelegate(subId, connection, reason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void triggerNetworkRegistration(int subId, ISipDelegate connection, int sipCode, String sipReason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(connection != null ? connection.asBinder() : null);
                    _data.writeInt(sipCode);
                    _data.writeString(sipReason);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().triggerNetworkRegistration(subId, connection, sipCode, sipReason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void registerRcsFeatureCallback(int slotId, IImsServiceFeatureCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerRcsFeatureCallback(slotId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRcsController
            public void unregisterImsFeatureCallback(IImsServiceFeatureCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRcsController.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterImsFeatureCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsRcsController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImsRcsController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
