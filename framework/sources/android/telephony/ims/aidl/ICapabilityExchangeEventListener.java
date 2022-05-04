package android.telephony.ims.aidl;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.aidl.IOptionsRequestCallback;
import java.util.List;
/* loaded from: classes3.dex */
public interface ICapabilityExchangeEventListener extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.ICapabilityExchangeEventListener";

    void onRemoteCapabilityRequest(Uri uri, List<String> list, IOptionsRequestCallback iOptionsRequestCallback) throws RemoteException;

    void onRequestPublishCapabilities(int i) throws RemoteException;

    void onUnpublish() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ICapabilityExchangeEventListener {
        @Override // android.telephony.ims.aidl.ICapabilityExchangeEventListener
        public void onRequestPublishCapabilities(int publishTriggerType) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ICapabilityExchangeEventListener
        public void onUnpublish() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.ICapabilityExchangeEventListener
        public void onRemoteCapabilityRequest(Uri contactUri, List<String> remoteCapabilities, IOptionsRequestCallback cb) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ICapabilityExchangeEventListener {
        static final int TRANSACTION_onRemoteCapabilityRequest = 3;
        static final int TRANSACTION_onRequestPublishCapabilities = 1;
        static final int TRANSACTION_onUnpublish = 2;

        public Stub() {
            attachInterface(this, ICapabilityExchangeEventListener.DESCRIPTOR);
        }

        public static ICapabilityExchangeEventListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICapabilityExchangeEventListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICapabilityExchangeEventListener)) {
                return new Proxy(obj);
            }
            return (ICapabilityExchangeEventListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onRequestPublishCapabilities";
                case 2:
                    return "onUnpublish";
                case 3:
                    return "onRemoteCapabilityRequest";
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
            Uri _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ICapabilityExchangeEventListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICapabilityExchangeEventListener.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onRequestPublishCapabilities(_arg02);
                            return true;
                        case 2:
                            data.enforceInterface(ICapabilityExchangeEventListener.DESCRIPTOR);
                            onUnpublish();
                            return true;
                        case 3:
                            data.enforceInterface(ICapabilityExchangeEventListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            List<String> _arg1 = data.createStringArrayList();
                            IOptionsRequestCallback _arg2 = IOptionsRequestCallback.Stub.asInterface(data.readStrongBinder());
                            onRemoteCapabilityRequest(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ICapabilityExchangeEventListener {
            public static ICapabilityExchangeEventListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICapabilityExchangeEventListener.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.ICapabilityExchangeEventListener
            public void onRequestPublishCapabilities(int publishTriggerType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICapabilityExchangeEventListener.DESCRIPTOR);
                    _data.writeInt(publishTriggerType);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestPublishCapabilities(publishTriggerType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ICapabilityExchangeEventListener
            public void onUnpublish() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICapabilityExchangeEventListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnpublish();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.ICapabilityExchangeEventListener
            public void onRemoteCapabilityRequest(Uri contactUri, List<String> remoteCapabilities, IOptionsRequestCallback cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICapabilityExchangeEventListener.DESCRIPTOR);
                    if (contactUri != null) {
                        _data.writeInt(1);
                        contactUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(remoteCapabilities);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRemoteCapabilityRequest(contactUri, remoteCapabilities, cb);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICapabilityExchangeEventListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICapabilityExchangeEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
