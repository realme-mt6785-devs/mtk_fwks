package android.uwb;

import android.content.AttributionSource;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.uwb.IUwbAdapterStateCallbacks;
import android.uwb.IUwbRangingCallbacks;
/* loaded from: classes3.dex */
public interface IUwbAdapter extends IInterface {
    public static final String DESCRIPTOR = "android.uwb.IUwbAdapter";
    public static final int RANGING_SESSION_CLOSE_THRESHOLD_MS = 3000;
    public static final int RANGING_SESSION_OPEN_THRESHOLD_MS = 3000;
    public static final int RANGING_SESSION_START_THRESHOLD_MS = 3000;

    void closeRanging(SessionHandle sessionHandle) throws RemoteException;

    int getAdapterState() throws RemoteException;

    PersistableBundle getSpecificationInfo() throws RemoteException;

    long getTimestampResolutionNanos() throws RemoteException;

    void openRanging(AttributionSource attributionSource, SessionHandle sessionHandle, IUwbRangingCallbacks iUwbRangingCallbacks, PersistableBundle persistableBundle) throws RemoteException;

    void reconfigureRanging(SessionHandle sessionHandle, PersistableBundle persistableBundle) throws RemoteException;

    void registerAdapterStateCallbacks(IUwbAdapterStateCallbacks iUwbAdapterStateCallbacks) throws RemoteException;

    void setEnabled(boolean z) throws RemoteException;

    void startRanging(SessionHandle sessionHandle, PersistableBundle persistableBundle) throws RemoteException;

    void stopRanging(SessionHandle sessionHandle) throws RemoteException;

    void unregisterAdapterStateCallbacks(IUwbAdapterStateCallbacks iUwbAdapterStateCallbacks) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IUwbAdapter {
        @Override // android.uwb.IUwbAdapter
        public void registerAdapterStateCallbacks(IUwbAdapterStateCallbacks adapterStateCallbacks) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public void unregisterAdapterStateCallbacks(IUwbAdapterStateCallbacks callbacks) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public long getTimestampResolutionNanos() throws RemoteException {
            return 0L;
        }

        @Override // android.uwb.IUwbAdapter
        public PersistableBundle getSpecificationInfo() throws RemoteException {
            return null;
        }

        @Override // android.uwb.IUwbAdapter
        public void openRanging(AttributionSource attributionSource, SessionHandle sessionHandle, IUwbRangingCallbacks rangingCallbacks, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public void startRanging(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public void reconfigureRanging(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public void stopRanging(SessionHandle sessionHandle) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public void closeRanging(SessionHandle sessionHandle) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public void setEnabled(boolean enabled) throws RemoteException {
        }

        @Override // android.uwb.IUwbAdapter
        public int getAdapterState() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUwbAdapter {
        static final int TRANSACTION_closeRanging = 9;
        static final int TRANSACTION_getAdapterState = 11;
        static final int TRANSACTION_getSpecificationInfo = 4;
        static final int TRANSACTION_getTimestampResolutionNanos = 3;
        static final int TRANSACTION_openRanging = 5;
        static final int TRANSACTION_reconfigureRanging = 7;
        static final int TRANSACTION_registerAdapterStateCallbacks = 1;
        static final int TRANSACTION_setEnabled = 10;
        static final int TRANSACTION_startRanging = 6;
        static final int TRANSACTION_stopRanging = 8;
        static final int TRANSACTION_unregisterAdapterStateCallbacks = 2;

        public Stub() {
            attachInterface(this, IUwbAdapter.DESCRIPTOR);
        }

        public static IUwbAdapter asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IUwbAdapter.DESCRIPTOR);
            if (iin == null || !(iin instanceof IUwbAdapter)) {
                return new Proxy(obj);
            }
            return (IUwbAdapter) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerAdapterStateCallbacks";
                case 2:
                    return "unregisterAdapterStateCallbacks";
                case 3:
                    return "getTimestampResolutionNanos";
                case 4:
                    return "getSpecificationInfo";
                case 5:
                    return "openRanging";
                case 6:
                    return "startRanging";
                case 7:
                    return "reconfigureRanging";
                case 8:
                    return "stopRanging";
                case 9:
                    return "closeRanging";
                case 10:
                    return "setEnabled";
                case 11:
                    return "getAdapterState";
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
            AttributionSource _arg0;
            SessionHandle _arg1;
            PersistableBundle _arg3;
            SessionHandle _arg02;
            PersistableBundle _arg12;
            SessionHandle _arg03;
            PersistableBundle _arg13;
            SessionHandle _arg04;
            SessionHandle _arg05;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IUwbAdapter.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg06 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            IUwbAdapterStateCallbacks _arg07 = IUwbAdapterStateCallbacks.Stub.asInterface(data.readStrongBinder());
                            registerAdapterStateCallbacks(_arg07);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            IUwbAdapterStateCallbacks _arg08 = IUwbAdapterStateCallbacks.Stub.asInterface(data.readStrongBinder());
                            unregisterAdapterStateCallbacks(_arg08);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            long _result = getTimestampResolutionNanos();
                            reply.writeNoException();
                            reply.writeLong(_result);
                            return true;
                        case 4:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            PersistableBundle _result2 = getSpecificationInfo();
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IUwbRangingCallbacks _arg2 = IUwbRangingCallbacks.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg3 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            openRanging(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            startRanging(_arg02, _arg12);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            reconfigureRanging(_arg03, _arg13);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            stopRanging(_arg04);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            closeRanging(_arg05);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = true;
                            }
                            setEnabled(_arg06);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IUwbAdapter.DESCRIPTOR);
                            int _result3 = getAdapterState();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUwbAdapter {
            public static IUwbAdapter sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IUwbAdapter.DESCRIPTOR;
            }

            @Override // android.uwb.IUwbAdapter
            public void registerAdapterStateCallbacks(IUwbAdapterStateCallbacks adapterStateCallbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    _data.writeStrongBinder(adapterStateCallbacks != null ? adapterStateCallbacks.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerAdapterStateCallbacks(adapterStateCallbacks);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public void unregisterAdapterStateCallbacks(IUwbAdapterStateCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterAdapterStateCallbacks(callbacks);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public long getTimestampResolutionNanos() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTimestampResolutionNanos();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public PersistableBundle getSpecificationInfo() throws RemoteException {
                PersistableBundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpecificationInfo();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PersistableBundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public void openRanging(AttributionSource attributionSource, SessionHandle sessionHandle, IUwbRangingCallbacks rangingCallbacks, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(rangingCallbacks != null ? rangingCallbacks.asBinder() : null);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().openRanging(attributionSource, sessionHandle, rangingCallbacks, parameters);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public void startRanging(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startRanging(sessionHandle, parameters);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public void reconfigureRanging(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reconfigureRanging(sessionHandle, parameters);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public void stopRanging(SessionHandle sessionHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopRanging(sessionHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public void closeRanging(SessionHandle sessionHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().closeRanging(sessionHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public void setEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setEnabled(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbAdapter
            public int getAdapterState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapter.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdapterState();
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

        public static boolean setDefaultImpl(IUwbAdapter impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IUwbAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
