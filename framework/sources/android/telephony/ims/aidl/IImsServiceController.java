package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsMmTelFeature;
import android.telephony.ims.aidl.IImsRcsFeature;
import android.telephony.ims.aidl.IImsRegistration;
import android.telephony.ims.aidl.IImsServiceControllerListener;
import android.telephony.ims.aidl.ISipTransport;
import android.telephony.ims.stub.ImsFeatureConfiguration;
import com.android.ims.internal.IImsFeatureStatusCallback;
/* loaded from: classes3.dex */
public interface IImsServiceController extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsServiceController";

    void addFeatureStatusCallback(int i, int i2, IImsFeatureStatusCallback iImsFeatureStatusCallback) throws RemoteException;

    IImsMmTelFeature createMmTelFeature(int i) throws RemoteException;

    IImsRcsFeature createRcsFeature(int i) throws RemoteException;

    void disableIms(int i) throws RemoteException;

    void enableIms(int i) throws RemoteException;

    IImsConfig getConfig(int i) throws RemoteException;

    long getImsServiceCapabilities() throws RemoteException;

    IImsRegistration getRegistration(int i) throws RemoteException;

    ISipTransport getSipTransport(int i) throws RemoteException;

    void notifyImsServiceReadyForFeatureCreation() throws RemoteException;

    ImsFeatureConfiguration querySupportedImsFeatures() throws RemoteException;

    void removeFeatureStatusCallback(int i, int i2, IImsFeatureStatusCallback iImsFeatureStatusCallback) throws RemoteException;

    void removeImsFeature(int i, int i2) throws RemoteException;

    void setListener(IImsServiceControllerListener iImsServiceControllerListener) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IImsServiceController {
        @Override // android.telephony.ims.aidl.IImsServiceController
        public void setListener(IImsServiceControllerListener l) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsMmTelFeature createMmTelFeature(int slotId) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsRcsFeature createRcsFeature(int slotId) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public ImsFeatureConfiguration querySupportedImsFeatures() throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public long getImsServiceCapabilities() throws RemoteException {
            return 0L;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void addFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void removeFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void notifyImsServiceReadyForFeatureCreation() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void removeImsFeature(int slotId, int featureType) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsConfig getConfig(int slotId) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsRegistration getRegistration(int slotId) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public ISipTransport getSipTransport(int slotId) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void enableIms(int slotId) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void disableIms(int slotId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IImsServiceController {
        static final int TRANSACTION_addFeatureStatusCallback = 6;
        static final int TRANSACTION_createMmTelFeature = 2;
        static final int TRANSACTION_createRcsFeature = 3;
        static final int TRANSACTION_disableIms = 14;
        static final int TRANSACTION_enableIms = 13;
        static final int TRANSACTION_getConfig = 10;
        static final int TRANSACTION_getImsServiceCapabilities = 5;
        static final int TRANSACTION_getRegistration = 11;
        static final int TRANSACTION_getSipTransport = 12;
        static final int TRANSACTION_notifyImsServiceReadyForFeatureCreation = 8;
        static final int TRANSACTION_querySupportedImsFeatures = 4;
        static final int TRANSACTION_removeFeatureStatusCallback = 7;
        static final int TRANSACTION_removeImsFeature = 9;
        static final int TRANSACTION_setListener = 1;

        public Stub() {
            attachInterface(this, IImsServiceController.DESCRIPTOR);
        }

        public static IImsServiceController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImsServiceController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImsServiceController)) {
                return new Proxy(obj);
            }
            return (IImsServiceController) iin;
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
                    return "createMmTelFeature";
                case 3:
                    return "createRcsFeature";
                case 4:
                    return "querySupportedImsFeatures";
                case 5:
                    return "getImsServiceCapabilities";
                case 6:
                    return "addFeatureStatusCallback";
                case 7:
                    return "removeFeatureStatusCallback";
                case 8:
                    return "notifyImsServiceReadyForFeatureCreation";
                case 9:
                    return "removeImsFeature";
                case 10:
                    return "getConfig";
                case 11:
                    return "getRegistration";
                case 12:
                    return "getSipTransport";
                case 13:
                    return "enableIms";
                case 14:
                    return "disableIms";
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
                    reply.writeString(IImsServiceController.DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            IImsServiceControllerListener _arg0 = IImsServiceControllerListener.Stub.asInterface(data.readStrongBinder());
                            setListener(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            IImsMmTelFeature _result = createMmTelFeature(_arg02);
                            reply.writeNoException();
                            if (_result != null) {
                                iBinder = _result.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 3:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            IImsRcsFeature _result2 = createRcsFeature(_arg03);
                            reply.writeNoException();
                            if (_result2 != null) {
                                iBinder = _result2.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 4:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            ImsFeatureConfiguration _result3 = querySupportedImsFeatures();
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            long _result4 = getImsServiceCapabilities();
                            reply.writeNoException();
                            reply.writeLong(_result4);
                            return true;
                        case 6:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg1 = data.readInt();
                            IImsFeatureStatusCallback _arg2 = IImsFeatureStatusCallback.Stub.asInterface(data.readStrongBinder());
                            addFeatureStatusCallback(_arg04, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _arg12 = data.readInt();
                            IImsFeatureStatusCallback _arg22 = IImsFeatureStatusCallback.Stub.asInterface(data.readStrongBinder());
                            removeFeatureStatusCallback(_arg05, _arg12, _arg22);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            notifyImsServiceReadyForFeatureCreation();
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg13 = data.readInt();
                            removeImsFeature(_arg06, _arg13);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            IImsConfig _result5 = getConfig(_arg07);
                            reply.writeNoException();
                            if (_result5 != null) {
                                iBinder = _result5.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 11:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            IImsRegistration _result6 = getRegistration(_arg08);
                            reply.writeNoException();
                            if (_result6 != null) {
                                iBinder = _result6.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 12:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            ISipTransport _result7 = getSipTransport(_arg09);
                            reply.writeNoException();
                            if (_result7 != null) {
                                iBinder = _result7.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 13:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            enableIms(_arg010);
                            return true;
                        case 14:
                            data.enforceInterface(IImsServiceController.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            disableIms(_arg011);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IImsServiceController {
            public static IImsServiceController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImsServiceController.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public void setListener(IImsServiceControllerListener l) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
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

            @Override // android.telephony.ims.aidl.IImsServiceController
            public IImsMmTelFeature createMmTelFeature(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createMmTelFeature(slotId);
                    }
                    _reply.readException();
                    IImsMmTelFeature _result = IImsMmTelFeature.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public IImsRcsFeature createRcsFeature(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createRcsFeature(slotId);
                    }
                    _reply.readException();
                    IImsRcsFeature _result = IImsRcsFeature.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public ImsFeatureConfiguration querySupportedImsFeatures() throws RemoteException {
                ImsFeatureConfiguration _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().querySupportedImsFeatures();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ImsFeatureConfiguration.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public long getImsServiceCapabilities() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsServiceCapabilities();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public void addFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(featureType);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addFeatureStatusCallback(slotId, featureType, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public void removeFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(featureType);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeFeatureStatusCallback(slotId, featureType, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public void notifyImsServiceReadyForFeatureCreation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyImsServiceReadyForFeatureCreation();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public void removeImsFeature(int slotId, int featureType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(featureType);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeImsFeature(slotId, featureType);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public IImsConfig getConfig(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfig(slotId);
                    }
                    _reply.readException();
                    IImsConfig _result = IImsConfig.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public IImsRegistration getRegistration(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRegistration(slotId);
                    }
                    _reply.readException();
                    IImsRegistration _result = IImsRegistration.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public ISipTransport getSipTransport(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSipTransport(slotId);
                    }
                    _reply.readException();
                    ISipTransport _result = ISipTransport.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public void enableIms(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableIms(slotId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsServiceController
            public void disableIms(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsServiceController.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableIms(slotId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsServiceController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImsServiceController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
