package com.mediatek.net.connectivity;

import android.net.INetdEventCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IMtkIpConnectivityMetrics extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.net.connectivity.IMtkIpConnectivityMetrics";

    boolean registerMtkNetdEventCallback(INetdEventCallback iNetdEventCallback) throws RemoteException;

    boolean registerMtkSocketEventCallback(INetdEventCallback iNetdEventCallback) throws RemoteException;

    void setSpeedDownload(int i) throws RemoteException;

    void startMonitorProcessWithUid(int i) throws RemoteException;

    void startMonitorProcessWithUidArray(int[] iArr) throws RemoteException;

    void stopMonitorProcessWithUid(int i) throws RemoteException;

    void stopMonitorProcessWithUidArray(int[] iArr) throws RemoteException;

    boolean unregisterMtkNetdEventCallback() throws RemoteException;

    boolean unregisterMtkSocketEventCallback() throws RemoteException;

    void updateCtaAppStatus(int i, boolean z) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IMtkIpConnectivityMetrics {
        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean registerMtkNetdEventCallback(INetdEventCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean unregisterMtkNetdEventCallback() throws RemoteException {
            return false;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean registerMtkSocketEventCallback(INetdEventCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public boolean unregisterMtkSocketEventCallback() throws RemoteException {
            return false;
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void updateCtaAppStatus(int uid, boolean isNotified) throws RemoteException {
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void setSpeedDownload(int timeoutMs) throws RemoteException {
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void startMonitorProcessWithUidArray(int[] uidArray) throws RemoteException {
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void startMonitorProcessWithUid(int uid) throws RemoteException {
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void stopMonitorProcessWithUidArray(int[] uidArray) throws RemoteException {
        }

        @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
        public void stopMonitorProcessWithUid(int uid) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMtkIpConnectivityMetrics {
        static final int TRANSACTION_registerMtkNetdEventCallback = 1;
        static final int TRANSACTION_registerMtkSocketEventCallback = 3;
        static final int TRANSACTION_setSpeedDownload = 6;
        static final int TRANSACTION_startMonitorProcessWithUid = 8;
        static final int TRANSACTION_startMonitorProcessWithUidArray = 7;
        static final int TRANSACTION_stopMonitorProcessWithUid = 10;
        static final int TRANSACTION_stopMonitorProcessWithUidArray = 9;
        static final int TRANSACTION_unregisterMtkNetdEventCallback = 2;
        static final int TRANSACTION_unregisterMtkSocketEventCallback = 4;
        static final int TRANSACTION_updateCtaAppStatus = 5;

        public Stub() {
            attachInterface(this, IMtkIpConnectivityMetrics.DESCRIPTOR);
        }

        public static IMtkIpConnectivityMetrics asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMtkIpConnectivityMetrics)) {
                return new Proxy(obj);
            }
            return (IMtkIpConnectivityMetrics) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            INetdEventCallback _arg0 = INetdEventCallback.Stub.asInterface(data.readStrongBinder());
                            boolean registerMtkNetdEventCallback = registerMtkNetdEventCallback(_arg0);
                            reply.writeNoException();
                            reply.writeInt(registerMtkNetdEventCallback ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            boolean unregisterMtkNetdEventCallback = unregisterMtkNetdEventCallback();
                            reply.writeNoException();
                            reply.writeInt(unregisterMtkNetdEventCallback ? 1 : 0);
                            return true;
                        case TRANSACTION_registerMtkSocketEventCallback /* 3 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            INetdEventCallback _arg02 = INetdEventCallback.Stub.asInterface(data.readStrongBinder());
                            boolean registerMtkSocketEventCallback = registerMtkSocketEventCallback(_arg02);
                            reply.writeNoException();
                            reply.writeInt(registerMtkSocketEventCallback ? 1 : 0);
                            return true;
                        case TRANSACTION_unregisterMtkSocketEventCallback /* 4 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            boolean unregisterMtkSocketEventCallback = unregisterMtkSocketEventCallback();
                            reply.writeNoException();
                            reply.writeInt(unregisterMtkSocketEventCallback ? 1 : 0);
                            return true;
                        case TRANSACTION_updateCtaAppStatus /* 5 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            boolean _arg1 = data.readInt() != 0;
                            updateCtaAppStatus(_arg03, _arg1);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_setSpeedDownload /* 6 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            setSpeedDownload(_arg04);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_startMonitorProcessWithUidArray /* 7 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            int[] _arg05 = data.createIntArray();
                            startMonitorProcessWithUidArray(_arg05);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_startMonitorProcessWithUid /* 8 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            startMonitorProcessWithUid(_arg06);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_stopMonitorProcessWithUidArray /* 9 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            int[] _arg07 = data.createIntArray();
                            stopMonitorProcessWithUidArray(_arg07);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_stopMonitorProcessWithUid /* 10 */:
                            data.enforceInterface(IMtkIpConnectivityMetrics.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            stopMonitorProcessWithUid(_arg08);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IMtkIpConnectivityMetrics {
            public static IMtkIpConnectivityMetrics sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMtkIpConnectivityMetrics.DESCRIPTOR;
            }

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public boolean registerMtkNetdEventCallback(INetdEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMtkNetdEventCallback(callback);
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

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public boolean unregisterMtkNetdEventCallback() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterMtkNetdEventCallback();
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

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public boolean registerMtkSocketEventCallback(INetdEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerMtkSocketEventCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMtkSocketEventCallback(callback);
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

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public boolean unregisterMtkSocketEventCallback() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterMtkSocketEventCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterMtkSocketEventCallback();
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

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public void updateCtaAppStatus(int uid, boolean isNotified) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeInt(isNotified ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_updateCtaAppStatus, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateCtaAppStatus(uid, isNotified);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public void setSpeedDownload(int timeoutMs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeInt(timeoutMs);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setSpeedDownload, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSpeedDownload(timeoutMs);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public void startMonitorProcessWithUidArray(int[] uidArray) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeIntArray(uidArray);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startMonitorProcessWithUidArray, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startMonitorProcessWithUidArray(uidArray);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public void startMonitorProcessWithUid(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startMonitorProcessWithUid, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startMonitorProcessWithUid(uid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public void stopMonitorProcessWithUidArray(int[] uidArray) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeIntArray(uidArray);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_stopMonitorProcessWithUidArray, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopMonitorProcessWithUidArray(uidArray);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.net.connectivity.IMtkIpConnectivityMetrics
            public void stopMonitorProcessWithUid(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMtkIpConnectivityMetrics.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_stopMonitorProcessWithUid, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopMonitorProcessWithUid(uid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMtkIpConnectivityMetrics impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMtkIpConnectivityMetrics getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
