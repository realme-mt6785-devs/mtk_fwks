package com.android.internal.app;

import android.media.permission.Identity;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.app.ISoundTriggerSession;
/* loaded from: classes4.dex */
public interface ISoundTriggerService extends IInterface {
    ISoundTriggerSession attachAsMiddleman(Identity identity, Identity identity2, IBinder iBinder) throws RemoteException;

    ISoundTriggerSession attachAsOriginator(Identity identity, IBinder iBinder) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ISoundTriggerService {
        @Override // com.android.internal.app.ISoundTriggerService
        public ISoundTriggerSession attachAsOriginator(Identity originatorIdentity, IBinder client) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.ISoundTriggerService
        public ISoundTriggerSession attachAsMiddleman(Identity middlemanIdentity, Identity originatorIdentity, IBinder client) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ISoundTriggerService {
        public static final String DESCRIPTOR = "com.android.internal.app.ISoundTriggerService";
        static final int TRANSACTION_attachAsMiddleman = 2;
        static final int TRANSACTION_attachAsOriginator = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISoundTriggerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISoundTriggerService)) {
                return new Proxy(obj);
            }
            return (ISoundTriggerService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "attachAsOriginator";
                case 2:
                    return "attachAsMiddleman";
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
            Identity _arg0;
            Identity _arg02;
            Identity _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IBinder _arg12 = data.readStrongBinder();
                            ISoundTriggerSession _result = attachAsOriginator(_arg0, _arg12);
                            reply.writeNoException();
                            if (_result != null) {
                                iBinder = _result.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IBinder _arg2 = data.readStrongBinder();
                            ISoundTriggerSession _result2 = attachAsMiddleman(_arg02, _arg1, _arg2);
                            reply.writeNoException();
                            if (_result2 != null) {
                                iBinder = _result2.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ISoundTriggerService {
            public static ISoundTriggerService sDefaultImpl;
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

            @Override // com.android.internal.app.ISoundTriggerService
            public ISoundTriggerSession attachAsOriginator(Identity originatorIdentity, IBinder client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (originatorIdentity != null) {
                        _data.writeInt(1);
                        originatorIdentity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(client);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attachAsOriginator(originatorIdentity, client);
                    }
                    _reply.readException();
                    ISoundTriggerSession _result = ISoundTriggerSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerService
            public ISoundTriggerSession attachAsMiddleman(Identity middlemanIdentity, Identity originatorIdentity, IBinder client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (middlemanIdentity != null) {
                        _data.writeInt(1);
                        middlemanIdentity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (originatorIdentity != null) {
                        _data.writeInt(1);
                        originatorIdentity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(client);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attachAsMiddleman(middlemanIdentity, originatorIdentity, client);
                    }
                    _reply.readException();
                    ISoundTriggerSession _result = ISoundTriggerSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISoundTriggerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
