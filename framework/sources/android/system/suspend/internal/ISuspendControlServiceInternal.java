package android.system.suspend.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ISuspendControlServiceInternal extends IInterface {
    public static final String DESCRIPTOR = "android$system$suspend$internal$ISuspendControlServiceInternal".replace('$', '.');

    boolean enableAutosuspend() throws RemoteException;

    boolean forceSuspend() throws RemoteException;

    SuspendInfo getSuspendStats() throws RemoteException;

    WakeLockInfo[] getWakeLockStats() throws RemoteException;

    WakeupInfo[] getWakeupStats() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISuspendControlServiceInternal {
        @Override // android.system.suspend.internal.ISuspendControlServiceInternal
        public boolean enableAutosuspend() throws RemoteException {
            return false;
        }

        @Override // android.system.suspend.internal.ISuspendControlServiceInternal
        public boolean forceSuspend() throws RemoteException {
            return false;
        }

        @Override // android.system.suspend.internal.ISuspendControlServiceInternal
        public WakeLockInfo[] getWakeLockStats() throws RemoteException {
            return null;
        }

        @Override // android.system.suspend.internal.ISuspendControlServiceInternal
        public WakeupInfo[] getWakeupStats() throws RemoteException {
            return null;
        }

        @Override // android.system.suspend.internal.ISuspendControlServiceInternal
        public SuspendInfo getSuspendStats() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISuspendControlServiceInternal {
        static final int TRANSACTION_enableAutosuspend = 1;
        static final int TRANSACTION_forceSuspend = 2;
        static final int TRANSACTION_getSuspendStats = 5;
        static final int TRANSACTION_getWakeLockStats = 3;
        static final int TRANSACTION_getWakeupStats = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISuspendControlServiceInternal asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISuspendControlServiceInternal)) {
                return new Proxy(obj);
            }
            return (ISuspendControlServiceInternal) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            boolean enableAutosuspend = enableAutosuspend();
                            reply.writeNoException();
                            reply.writeInt(enableAutosuspend ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            boolean forceSuspend = forceSuspend();
                            reply.writeNoException();
                            reply.writeInt(forceSuspend ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            WakeLockInfo[] _result = getWakeLockStats();
                            reply.writeNoException();
                            reply.writeTypedArray(_result, 1);
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            WakeupInfo[] _result2 = getWakeupStats();
                            reply.writeNoException();
                            reply.writeTypedArray(_result2, 1);
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            SuspendInfo _result3 = getSuspendStats();
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISuspendControlServiceInternal {
            public static ISuspendControlServiceInternal sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.system.suspend.internal.ISuspendControlServiceInternal
            public boolean enableAutosuspend() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableAutosuspend();
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

            @Override // android.system.suspend.internal.ISuspendControlServiceInternal
            public boolean forceSuspend() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().forceSuspend();
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

            @Override // android.system.suspend.internal.ISuspendControlServiceInternal
            public WakeLockInfo[] getWakeLockStats() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWakeLockStats();
                    }
                    _reply.readException();
                    WakeLockInfo[] _result = (WakeLockInfo[]) _reply.createTypedArray(WakeLockInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.suspend.internal.ISuspendControlServiceInternal
            public WakeupInfo[] getWakeupStats() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWakeupStats();
                    }
                    _reply.readException();
                    WakeupInfo[] _result = (WakeupInfo[]) _reply.createTypedArray(WakeupInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.suspend.internal.ISuspendControlServiceInternal
            public SuspendInfo getSuspendStats() throws RemoteException {
                SuspendInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSuspendStats();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = SuspendInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISuspendControlServiceInternal impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISuspendControlServiceInternal getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
