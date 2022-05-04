package android.service.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.trust.ITrustAgentServiceCallback;
import java.util.List;
/* loaded from: classes3.dex */
public interface ITrustAgentService extends IInterface {
    void onConfigure(List<PersistableBundle> list, IBinder iBinder) throws RemoteException;

    void onDeviceLocked() throws RemoteException;

    void onDeviceUnlocked() throws RemoteException;

    void onEscrowTokenAdded(byte[] bArr, long j, UserHandle userHandle) throws RemoteException;

    void onEscrowTokenRemoved(long j, boolean z) throws RemoteException;

    void onTokenStateReceived(long j, int i) throws RemoteException;

    void onTrustTimeout() throws RemoteException;

    void onUnlockAttempt(boolean z) throws RemoteException;

    void onUnlockLockout(int i) throws RemoteException;

    void setCallback(ITrustAgentServiceCallback iTrustAgentServiceCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITrustAgentService {
        @Override // android.service.trust.ITrustAgentService
        public void onUnlockAttempt(boolean successful) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onUnlockLockout(int timeoutMs) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onTrustTimeout() throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onDeviceLocked() throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onDeviceUnlocked() throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onConfigure(List<PersistableBundle> options, IBinder token) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void setCallback(ITrustAgentServiceCallback callback) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onEscrowTokenAdded(byte[] token, long handle, UserHandle user) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onTokenStateReceived(long handle, int tokenState) throws RemoteException {
        }

        @Override // android.service.trust.ITrustAgentService
        public void onEscrowTokenRemoved(long handle, boolean successful) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITrustAgentService {
        public static final String DESCRIPTOR = "android.service.trust.ITrustAgentService";
        static final int TRANSACTION_onConfigure = 6;
        static final int TRANSACTION_onDeviceLocked = 4;
        static final int TRANSACTION_onDeviceUnlocked = 5;
        static final int TRANSACTION_onEscrowTokenAdded = 8;
        static final int TRANSACTION_onEscrowTokenRemoved = 10;
        static final int TRANSACTION_onTokenStateReceived = 9;
        static final int TRANSACTION_onTrustTimeout = 3;
        static final int TRANSACTION_onUnlockAttempt = 1;
        static final int TRANSACTION_onUnlockLockout = 2;
        static final int TRANSACTION_setCallback = 7;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITrustAgentService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITrustAgentService)) {
                return new Proxy(obj);
            }
            return (ITrustAgentService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onUnlockAttempt";
                case 2:
                    return "onUnlockLockout";
                case 3:
                    return "onTrustTimeout";
                case 4:
                    return "onDeviceLocked";
                case 5:
                    return "onDeviceUnlocked";
                case 6:
                    return "onConfigure";
                case 7:
                    return "setCallback";
                case 8:
                    return "onEscrowTokenAdded";
                case 9:
                    return "onTokenStateReceived";
                case 10:
                    return "onEscrowTokenRemoved";
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
            UserHandle _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            onUnlockAttempt(_arg1);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onUnlockLockout(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            onTrustTimeout();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            onDeviceLocked();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            onDeviceUnlocked();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            List<PersistableBundle> _arg02 = data.createTypedArrayList(PersistableBundle.CREATOR);
                            onConfigure(_arg02, data.readStrongBinder());
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            ITrustAgentServiceCallback _arg03 = ITrustAgentServiceCallback.Stub.asInterface(data.readStrongBinder());
                            setCallback(_arg03);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _arg04 = data.createByteArray();
                            long _arg12 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg2 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onEscrowTokenAdded(_arg04, _arg12, _arg2);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            long _arg05 = data.readLong();
                            onTokenStateReceived(_arg05, data.readInt());
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            long _arg06 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            onEscrowTokenRemoved(_arg06, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITrustAgentService {
            public static ITrustAgentService sDefaultImpl;
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

            @Override // android.service.trust.ITrustAgentService
            public void onUnlockAttempt(boolean successful) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(successful ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnlockAttempt(successful);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onUnlockLockout(int timeoutMs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(timeoutMs);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnlockLockout(timeoutMs);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onTrustTimeout() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTrustTimeout();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onDeviceLocked() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceLocked();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onDeviceUnlocked() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceUnlocked();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onConfigure(List<PersistableBundle> options, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(options);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConfigure(options, token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void setCallback(ITrustAgentServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onEscrowTokenAdded(byte[] token, long handle, UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(token);
                    _data.writeLong(handle);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEscrowTokenAdded(token, handle, user);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onTokenStateReceived(long handle, int tokenState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(tokenState);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTokenStateReceived(handle, tokenState);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.trust.ITrustAgentService
            public void onEscrowTokenRemoved(long handle, boolean successful) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(handle);
                    _data.writeInt(successful ? 1 : 0);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEscrowTokenRemoved(handle, successful);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITrustAgentService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITrustAgentService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
