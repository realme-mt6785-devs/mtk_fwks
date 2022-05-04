package android.security.identity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.identity.ICredential;
import android.security.identity.IWritableCredential;
/* loaded from: classes2.dex */
public interface ICredentialStore extends IInterface {
    public static final String DESCRIPTOR = "android.security.identity.ICredentialStore";
    public static final int ERROR_ALREADY_PERSONALIZED = 2;
    public static final int ERROR_AUTHENTICATION_KEY_NOT_FOUND = 9;
    public static final int ERROR_CIPHER_SUITE_NOT_SUPPORTED = 4;
    public static final int ERROR_DOCUMENT_TYPE_NOT_SUPPORTED = 8;
    public static final int ERROR_EPHEMERAL_PUBLIC_KEY_NOT_FOUND = 5;
    public static final int ERROR_GENERIC = 1;
    public static final int ERROR_INVALID_ITEMS_REQUEST_MESSAGE = 10;
    public static final int ERROR_INVALID_READER_SIGNATURE = 7;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NOT_SUPPORTED = 12;
    public static final int ERROR_NO_AUTHENTICATION_KEY_AVAILABLE = 6;
    public static final int ERROR_NO_SUCH_CREDENTIAL = 3;
    public static final int ERROR_SESSION_TRANSCRIPT_MISMATCH = 11;

    IWritableCredential createCredential(String str, String str2) throws RemoteException;

    ICredential getCredentialByName(String str, int i) throws RemoteException;

    SecurityHardwareInfoParcel getSecurityHardwareInfo() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ICredentialStore {
        @Override // android.security.identity.ICredentialStore
        public SecurityHardwareInfoParcel getSecurityHardwareInfo() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredentialStore
        public IWritableCredential createCredential(String credentialName, String docType) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredentialStore
        public ICredential getCredentialByName(String credentialName, int cipherSuite) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICredentialStore {
        static final int TRANSACTION_createCredential = 2;
        static final int TRANSACTION_getCredentialByName = 3;
        static final int TRANSACTION_getSecurityHardwareInfo = 1;

        public Stub() {
            attachInterface(this, ICredentialStore.DESCRIPTOR);
        }

        public static ICredentialStore asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICredentialStore.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICredentialStore)) {
                return new Proxy(obj);
            }
            return (ICredentialStore) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getSecurityHardwareInfo";
                case 2:
                    return "createCredential";
                case 3:
                    return "getCredentialByName";
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
                    reply.writeString(ICredentialStore.DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICredentialStore.DESCRIPTOR);
                            SecurityHardwareInfoParcel _result = getSecurityHardwareInfo();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(ICredentialStore.DESCRIPTOR);
                            String _arg0 = data.readString();
                            String _arg1 = data.readString();
                            IWritableCredential _result2 = createCredential(_arg0, _arg1);
                            reply.writeNoException();
                            if (_result2 != null) {
                                iBinder = _result2.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 3:
                            data.enforceInterface(ICredentialStore.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg12 = data.readInt();
                            ICredential _result3 = getCredentialByName(_arg02, _arg12);
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ICredentialStore {
            public static ICredentialStore sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICredentialStore.DESCRIPTOR;
            }

            @Override // android.security.identity.ICredentialStore
            public SecurityHardwareInfoParcel getSecurityHardwareInfo() throws RemoteException {
                SecurityHardwareInfoParcel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredentialStore.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSecurityHardwareInfo();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = SecurityHardwareInfoParcel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredentialStore
            public IWritableCredential createCredential(String credentialName, String docType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredentialStore.DESCRIPTOR);
                    _data.writeString(credentialName);
                    _data.writeString(docType);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createCredential(credentialName, docType);
                    }
                    _reply.readException();
                    IWritableCredential _result = IWritableCredential.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredentialStore
            public ICredential getCredentialByName(String credentialName, int cipherSuite) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredentialStore.DESCRIPTOR);
                    _data.writeString(credentialName);
                    _data.writeInt(cipherSuite);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialByName(credentialName, cipherSuite);
                    }
                    _reply.readException();
                    ICredential _result = ICredential.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICredentialStore impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICredentialStore getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
