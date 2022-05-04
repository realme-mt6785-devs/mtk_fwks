package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.DelegateRegistrationState;
import android.telephony.ims.FeatureTagState;
import android.telephony.ims.SipDelegateConfiguration;
import android.telephony.ims.SipDelegateImsConfiguration;
import android.telephony.ims.aidl.ISipDelegate;
import java.util.List;
/* loaded from: classes3.dex */
public interface ISipDelegateStateCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.ISipDelegateStateCallback";

    void onConfigurationChanged(SipDelegateConfiguration sipDelegateConfiguration) throws RemoteException;

    void onCreated(ISipDelegate iSipDelegate, List<FeatureTagState> list) throws RemoteException;

    void onDestroyed(int i) throws RemoteException;

    void onFeatureTagRegistrationChanged(DelegateRegistrationState delegateRegistrationState) throws RemoteException;

    void onImsConfigurationChanged(SipDelegateImsConfiguration sipDelegateImsConfiguration) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISipDelegateStateCallback {
        @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
        public void onCreated(ISipDelegate c, List<FeatureTagState> deniedFeatureTags) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
        public void onFeatureTagRegistrationChanged(DelegateRegistrationState registrationState) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
        public void onImsConfigurationChanged(SipDelegateImsConfiguration registeredSipConfig) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
        public void onConfigurationChanged(SipDelegateConfiguration registeredSipConfig) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
        public void onDestroyed(int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISipDelegateStateCallback {
        static final int TRANSACTION_onConfigurationChanged = 4;
        static final int TRANSACTION_onCreated = 1;
        static final int TRANSACTION_onDestroyed = 5;
        static final int TRANSACTION_onFeatureTagRegistrationChanged = 2;
        static final int TRANSACTION_onImsConfigurationChanged = 3;

        public Stub() {
            attachInterface(this, ISipDelegateStateCallback.DESCRIPTOR);
        }

        public static ISipDelegateStateCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISipDelegateStateCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISipDelegateStateCallback)) {
                return new Proxy(obj);
            }
            return (ISipDelegateStateCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCreated";
                case 2:
                    return "onFeatureTagRegistrationChanged";
                case 3:
                    return "onImsConfigurationChanged";
                case 4:
                    return "onConfigurationChanged";
                case 5:
                    return "onDestroyed";
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
            DelegateRegistrationState _arg0;
            SipDelegateImsConfiguration _arg02;
            SipDelegateConfiguration _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISipDelegateStateCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISipDelegateStateCallback.DESCRIPTOR);
                            ISipDelegate _arg04 = ISipDelegate.Stub.asInterface(data.readStrongBinder());
                            List<FeatureTagState> _arg1 = data.createTypedArrayList(FeatureTagState.CREATOR);
                            onCreated(_arg04, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(ISipDelegateStateCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = DelegateRegistrationState.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onFeatureTagRegistrationChanged(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(ISipDelegateStateCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SipDelegateImsConfiguration.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onImsConfigurationChanged(_arg02);
                            return true;
                        case 4:
                            data.enforceInterface(ISipDelegateStateCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = SipDelegateConfiguration.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onConfigurationChanged(_arg03);
                            return true;
                        case 5:
                            data.enforceInterface(ISipDelegateStateCallback.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onDestroyed(_arg05);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISipDelegateStateCallback {
            public static ISipDelegateStateCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISipDelegateStateCallback.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
            public void onCreated(ISipDelegate c, List<FeatureTagState> deniedFeatureTags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateStateCallback.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    _data.writeTypedList(deniedFeatureTags);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCreated(c, deniedFeatureTags);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
            public void onFeatureTagRegistrationChanged(DelegateRegistrationState registrationState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateStateCallback.DESCRIPTOR);
                    if (registrationState != null) {
                        _data.writeInt(1);
                        registrationState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFeatureTagRegistrationChanged(registrationState);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
            public void onImsConfigurationChanged(SipDelegateImsConfiguration registeredSipConfig) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateStateCallback.DESCRIPTOR);
                    if (registeredSipConfig != null) {
                        _data.writeInt(1);
                        registeredSipConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onImsConfigurationChanged(registeredSipConfig);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
            public void onConfigurationChanged(SipDelegateConfiguration registeredSipConfig) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateStateCallback.DESCRIPTOR);
                    if (registeredSipConfig != null) {
                        _data.writeInt(1);
                        registeredSipConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigurationChanged(registeredSipConfig);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ISipDelegateStateCallback
            public void onDestroyed(int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISipDelegateStateCallback.DESCRIPTOR);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDestroyed(reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISipDelegateStateCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISipDelegateStateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
