package com.oplus.miragewindow;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusMirageDisplayObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.miragewindow.IOplusMirageDisplayObserver";

    void onMirageDisplayCastFailed(int i) throws RemoteException;

    void onMirageDisplayCastSuccess(OplusMirageDisplayCastInfo oplusMirageDisplayCastInfo, int i) throws RemoteException;

    void onMirageDisplayConfigChanged(OplusMirageDisplayCastInfo oplusMirageDisplayCastInfo, int i) throws RemoteException;

    void onMirageDisplayExit(int i) throws RemoteException;

    void onMirageDisplayToastEvent(int i, int i2, Bundle bundle) throws RemoteException;

    void onMirageDisplayTopActivityUidChanged(int i, int i2) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusMirageDisplayObserver {
        @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
        public void onMirageDisplayCastSuccess(OplusMirageDisplayCastInfo info, int sessionId) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
        public void onMirageDisplayCastFailed(int sessionId) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
        public void onMirageDisplayExit(int sessionId) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
        public void onMirageDisplayConfigChanged(OplusMirageDisplayCastInfo info, int sessionId) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
        public void onMirageDisplayTopActivityUidChanged(int uid, int sessionId) throws RemoteException {
        }

        @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
        public void onMirageDisplayToastEvent(int eventId, int displayId, Bundle extention) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusMirageDisplayObserver {
        static final int TRANSACTION_onMirageDisplayCastFailed = 2;
        static final int TRANSACTION_onMirageDisplayCastSuccess = 1;
        static final int TRANSACTION_onMirageDisplayConfigChanged = 4;
        static final int TRANSACTION_onMirageDisplayExit = 3;
        static final int TRANSACTION_onMirageDisplayToastEvent = 6;
        static final int TRANSACTION_onMirageDisplayTopActivityUidChanged = 5;

        public Stub() {
            attachInterface(this, IOplusMirageDisplayObserver.DESCRIPTOR);
        }

        public static IOplusMirageDisplayObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusMirageDisplayObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusMirageDisplayObserver)) {
                return new Proxy(obj);
            }
            return (IOplusMirageDisplayObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onMirageDisplayCastSuccess";
                case 2:
                    return "onMirageDisplayCastFailed";
                case 3:
                    return "onMirageDisplayExit";
                case 4:
                    return "onMirageDisplayConfigChanged";
                case 5:
                    return "onMirageDisplayTopActivityUidChanged";
                case 6:
                    return "onMirageDisplayToastEvent";
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
            OplusMirageDisplayCastInfo _arg0;
            OplusMirageDisplayCastInfo _arg02;
            Bundle _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusMirageDisplayObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusMirageDisplayObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusMirageDisplayCastInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            onMirageDisplayCastSuccess(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusMirageDisplayObserver.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onMirageDisplayCastFailed(_arg03);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusMirageDisplayObserver.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            onMirageDisplayExit(_arg04);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusMirageDisplayObserver.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = OplusMirageDisplayCastInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg12 = data.readInt();
                            onMirageDisplayConfigChanged(_arg02, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusMirageDisplayObserver.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _arg13 = data.readInt();
                            onMirageDisplayTopActivityUidChanged(_arg05, _arg13);
                            return true;
                        case 6:
                            data.enforceInterface(IOplusMirageDisplayObserver.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg14 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onMirageDisplayToastEvent(_arg06, _arg14, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusMirageDisplayObserver {
            public static IOplusMirageDisplayObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusMirageDisplayObserver.DESCRIPTOR;
            }

            @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
            public void onMirageDisplayCastSuccess(OplusMirageDisplayCastInfo info, int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageDisplayObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageDisplayCastSuccess(info, sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
            public void onMirageDisplayCastFailed(int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageDisplayObserver.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageDisplayCastFailed(sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
            public void onMirageDisplayExit(int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageDisplayObserver.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageDisplayExit(sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
            public void onMirageDisplayConfigChanged(OplusMirageDisplayCastInfo info, int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageDisplayObserver.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageDisplayConfigChanged(info, sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
            public void onMirageDisplayTopActivityUidChanged(int uid, int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageDisplayObserver.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageDisplayTopActivityUidChanged(uid, sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.miragewindow.IOplusMirageDisplayObserver
            public void onMirageDisplayToastEvent(int eventId, int displayId, Bundle extention) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusMirageDisplayObserver.DESCRIPTOR);
                    _data.writeInt(eventId);
                    _data.writeInt(displayId);
                    if (extention != null) {
                        _data.writeInt(1);
                        extention.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMirageDisplayToastEvent(eventId, displayId, extention);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusMirageDisplayObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusMirageDisplayObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
