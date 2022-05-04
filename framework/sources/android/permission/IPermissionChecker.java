package android.permission;

import android.content.AttributionSourceState;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IPermissionChecker extends IInterface {
    public static final String DESCRIPTOR = "android$permission$IPermissionChecker".replace('$', '.');
    public static final int PERMISSION_GRANTED = 0;
    public static final int PERMISSION_HARD_DENIED = 2;
    public static final int PERMISSION_SOFT_DENIED = 1;

    int checkOp(int i, AttributionSourceState attributionSourceState, String str, boolean z, boolean z2) throws RemoteException;

    int checkPermission(String str, AttributionSourceState attributionSourceState, String str2, boolean z, boolean z2, boolean z3, int i) throws RemoteException;

    void finishDataDelivery(int i, AttributionSourceState attributionSourceState, boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IPermissionChecker {
        @Override // android.permission.IPermissionChecker
        public int checkPermission(String permission, AttributionSourceState attributionSource, String message, boolean forDataDelivery, boolean startDataDelivery, boolean fromDatasource, int attributedOp) throws RemoteException {
            return 0;
        }

        @Override // android.permission.IPermissionChecker
        public void finishDataDelivery(int op, AttributionSourceState attributionSource, boolean fromDatasource) throws RemoteException {
        }

        @Override // android.permission.IPermissionChecker
        public int checkOp(int op, AttributionSourceState attributionSource, String message, boolean forDataDelivery, boolean startDataDelivery) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPermissionChecker {
        static final int TRANSACTION_checkOp = 3;
        static final int TRANSACTION_checkPermission = 1;
        static final int TRANSACTION_finishDataDelivery = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPermissionChecker asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPermissionChecker)) {
                return new Proxy(obj);
            }
            return (IPermissionChecker) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            AttributionSourceState _arg1;
            boolean _arg3;
            boolean _arg4;
            boolean _arg5;
            AttributionSourceState _arg12;
            AttributionSourceState _arg13;
            boolean _arg32;
            boolean _arg42;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    boolean _arg2 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = AttributionSourceState.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg22 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            } else {
                                _arg3 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = true;
                            } else {
                                _arg5 = false;
                            }
                            int _arg6 = data.readInt();
                            int _result = checkPermission(_arg0, _arg1, _arg22, _arg3, _arg4, _arg5, _arg6);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = AttributionSourceState.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            finishDataDelivery(_arg02, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSourceState.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            String _arg23 = data.readString();
                            if (data.readInt() != 0) {
                                _arg32 = true;
                            } else {
                                _arg32 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg42 = true;
                            } else {
                                _arg42 = false;
                            }
                            int _result2 = checkOp(_arg03, _arg13, _arg23, _arg32, _arg42);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPermissionChecker {
            public static IPermissionChecker sDefaultImpl;
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

            @Override // android.permission.IPermissionChecker
            public int checkPermission(String permission, AttributionSourceState attributionSource, String message, boolean forDataDelivery, boolean startDataDelivery, boolean fromDatasource, int attributedOp) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    try {
                        _data.writeString(permission);
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeString(message);
                            _data.writeInt(forDataDelivery ? 1 : 0);
                            _data.writeInt(startDataDelivery ? 1 : 0);
                            _data.writeInt(fromDatasource ? 1 : 0);
                            try {
                                _data.writeInt(attributedOp);
                                try {
                                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        int _result = _reply.readInt();
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    int checkPermission = Stub.getDefaultImpl().checkPermission(permission, attributionSource, message, forDataDelivery, startDataDelivery, fromDatasource, attributedOp);
                                    _reply.recycle();
                                    _data.recycle();
                                    return checkPermission;
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.permission.IPermissionChecker
            public void finishDataDelivery(int op, AttributionSourceState attributionSource, boolean fromDatasource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(op);
                    int i = 1;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!fromDatasource) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finishDataDelivery(op, attributionSource, fromDatasource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.permission.IPermissionChecker
            public int checkOp(int op, AttributionSourceState attributionSource, String message, boolean forDataDelivery, boolean startDataDelivery) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(op);
                    int i = 1;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(message);
                    _data.writeInt(forDataDelivery ? 1 : 0);
                    if (!startDataDelivery) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkOp(op, attributionSource, message, forDataDelivery, startDataDelivery);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPermissionChecker impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPermissionChecker getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
