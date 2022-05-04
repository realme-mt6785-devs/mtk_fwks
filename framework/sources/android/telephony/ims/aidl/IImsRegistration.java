package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ims.aidl.IImsRegistrationCallback;
/* loaded from: classes3.dex */
public interface IImsRegistration extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsRegistration";

    void addRegistrationCallback(IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    int getRegistrationTechnology() throws RemoteException;

    void removeRegistrationCallback(IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void triggerFullNetworkRegistration(int i, String str) throws RemoteException;

    void triggerSipDelegateDeregistration() throws RemoteException;

    void triggerUpdateSipDelegateRegistration() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IImsRegistration {
        @Override // android.telephony.ims.aidl.IImsRegistration
        public int getRegistrationTechnology() throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void addRegistrationCallback(IImsRegistrationCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void removeRegistrationCallback(IImsRegistrationCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void triggerFullNetworkRegistration(int sipCode, String sipReason) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void triggerUpdateSipDelegateRegistration() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void triggerSipDelegateDeregistration() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IImsRegistration {
        static final int TRANSACTION_addRegistrationCallback = 2;
        static final int TRANSACTION_getRegistrationTechnology = 1;
        static final int TRANSACTION_removeRegistrationCallback = 3;
        static final int TRANSACTION_triggerFullNetworkRegistration = 4;
        static final int TRANSACTION_triggerSipDelegateDeregistration = 6;
        static final int TRANSACTION_triggerUpdateSipDelegateRegistration = 5;

        public Stub() {
            attachInterface(this, IImsRegistration.DESCRIPTOR);
        }

        public static IImsRegistration asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImsRegistration.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImsRegistration)) {
                return new Proxy(obj);
            }
            return (IImsRegistration) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getRegistrationTechnology";
                case 2:
                    return "addRegistrationCallback";
                case 3:
                    return "removeRegistrationCallback";
                case 4:
                    return "triggerFullNetworkRegistration";
                case 5:
                    return "triggerUpdateSipDelegateRegistration";
                case 6:
                    return "triggerSipDelegateDeregistration";
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
                    reply.writeString(IImsRegistration.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImsRegistration.DESCRIPTOR);
                            int _result = getRegistrationTechnology();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IImsRegistration.DESCRIPTOR);
                            IImsRegistrationCallback _arg0 = IImsRegistrationCallback.Stub.asInterface(data.readStrongBinder());
                            addRegistrationCallback(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IImsRegistration.DESCRIPTOR);
                            IImsRegistrationCallback _arg02 = IImsRegistrationCallback.Stub.asInterface(data.readStrongBinder());
                            removeRegistrationCallback(_arg02);
                            return true;
                        case 4:
                            data.enforceInterface(IImsRegistration.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            String _arg1 = data.readString();
                            triggerFullNetworkRegistration(_arg03, _arg1);
                            return true;
                        case 5:
                            data.enforceInterface(IImsRegistration.DESCRIPTOR);
                            triggerUpdateSipDelegateRegistration();
                            return true;
                        case 6:
                            data.enforceInterface(IImsRegistration.DESCRIPTOR);
                            triggerSipDelegateDeregistration();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IImsRegistration {
            public static IImsRegistration sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImsRegistration.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IImsRegistration
            public int getRegistrationTechnology() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRegistration.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRegistrationTechnology();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRegistration
            public void addRegistrationCallback(IImsRegistrationCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRegistration.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addRegistrationCallback(c);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRegistration
            public void removeRegistrationCallback(IImsRegistrationCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRegistration.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeRegistrationCallback(c);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRegistration
            public void triggerFullNetworkRegistration(int sipCode, String sipReason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRegistration.DESCRIPTOR);
                    _data.writeInt(sipCode);
                    _data.writeString(sipReason);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().triggerFullNetworkRegistration(sipCode, sipReason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRegistration
            public void triggerUpdateSipDelegateRegistration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRegistration.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().triggerUpdateSipDelegateRegistration();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsRegistration
            public void triggerSipDelegateDeregistration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsRegistration.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().triggerSipDelegateDeregistration();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsRegistration impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImsRegistration getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
