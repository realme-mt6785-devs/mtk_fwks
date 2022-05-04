package com.mediatek.gnssdebugreport;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.gnssdebugreport.IDebugReportCallback;
/* loaded from: classes.dex */
public interface IGnssDebugReportService extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.gnssdebugreport.IGnssDebugReportService";

    void addListener(IDebugReportCallback iDebugReportCallback) throws RemoteException;

    void removeListener(IDebugReportCallback iDebugReportCallback) throws RemoteException;

    boolean startDebug(IDebugReportCallback iDebugReportCallback) throws RemoteException;

    boolean stopDebug(IDebugReportCallback iDebugReportCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IGnssDebugReportService {
        @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
        public boolean startDebug(IDebugReportCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
        public boolean stopDebug(IDebugReportCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
        public void addListener(IDebugReportCallback callback) throws RemoteException {
        }

        @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
        public void removeListener(IDebugReportCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IGnssDebugReportService {
        static final int TRANSACTION_addListener = 3;
        static final int TRANSACTION_removeListener = 4;
        static final int TRANSACTION_startDebug = 1;
        static final int TRANSACTION_stopDebug = 2;

        public Stub() {
            attachInterface(this, IGnssDebugReportService.DESCRIPTOR);
        }

        public static IGnssDebugReportService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGnssDebugReportService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGnssDebugReportService)) {
                return new Proxy(obj);
            }
            return (IGnssDebugReportService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IGnssDebugReportService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGnssDebugReportService.DESCRIPTOR);
                            IDebugReportCallback _arg0 = IDebugReportCallback.Stub.asInterface(data.readStrongBinder());
                            boolean startDebug = startDebug(_arg0);
                            reply.writeNoException();
                            reply.writeInt(startDebug ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IGnssDebugReportService.DESCRIPTOR);
                            IDebugReportCallback _arg02 = IDebugReportCallback.Stub.asInterface(data.readStrongBinder());
                            boolean stopDebug = stopDebug(_arg02);
                            reply.writeNoException();
                            reply.writeInt(stopDebug ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IGnssDebugReportService.DESCRIPTOR);
                            IDebugReportCallback _arg03 = IDebugReportCallback.Stub.asInterface(data.readStrongBinder());
                            addListener(_arg03);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IGnssDebugReportService.DESCRIPTOR);
                            IDebugReportCallback _arg04 = IDebugReportCallback.Stub.asInterface(data.readStrongBinder());
                            removeListener(_arg04);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IGnssDebugReportService {
            public static IGnssDebugReportService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGnssDebugReportService.DESCRIPTOR;
            }

            @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
            public boolean startDebug(IDebugReportCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGnssDebugReportService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDebug(callback);
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

            @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
            public boolean stopDebug(IDebugReportCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGnssDebugReportService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopDebug(callback);
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

            @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
            public void addListener(IDebugReportCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGnssDebugReportService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addListener(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.gnssdebugreport.IGnssDebugReportService
            public void removeListener(IDebugReportCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGnssDebugReportService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeListener(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGnssDebugReportService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGnssDebugReportService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
