package android.uwb;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IUwbRangingCallbacks extends IInterface {
    public static final String DESCRIPTOR = "android.uwb.IUwbRangingCallbacks";

    void onRangingClosed(SessionHandle sessionHandle, int i, PersistableBundle persistableBundle) throws RemoteException;

    void onRangingOpenFailed(SessionHandle sessionHandle, int i, PersistableBundle persistableBundle) throws RemoteException;

    void onRangingOpened(SessionHandle sessionHandle) throws RemoteException;

    void onRangingReconfigureFailed(SessionHandle sessionHandle, int i, PersistableBundle persistableBundle) throws RemoteException;

    void onRangingReconfigured(SessionHandle sessionHandle, PersistableBundle persistableBundle) throws RemoteException;

    void onRangingResult(SessionHandle sessionHandle, RangingReport rangingReport) throws RemoteException;

    void onRangingStartFailed(SessionHandle sessionHandle, int i, PersistableBundle persistableBundle) throws RemoteException;

    void onRangingStarted(SessionHandle sessionHandle, PersistableBundle persistableBundle) throws RemoteException;

    void onRangingStopFailed(SessionHandle sessionHandle, int i, PersistableBundle persistableBundle) throws RemoteException;

    void onRangingStopped(SessionHandle sessionHandle, int i, PersistableBundle persistableBundle) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IUwbRangingCallbacks {
        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingOpened(SessionHandle sessionHandle) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingOpenFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingStarted(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingStartFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingReconfigured(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingReconfigureFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingStopped(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingStopFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingClosed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
        }

        @Override // android.uwb.IUwbRangingCallbacks
        public void onRangingResult(SessionHandle sessionHandle, RangingReport result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUwbRangingCallbacks {
        static final int TRANSACTION_onRangingClosed = 9;
        static final int TRANSACTION_onRangingOpenFailed = 2;
        static final int TRANSACTION_onRangingOpened = 1;
        static final int TRANSACTION_onRangingReconfigureFailed = 6;
        static final int TRANSACTION_onRangingReconfigured = 5;
        static final int TRANSACTION_onRangingResult = 10;
        static final int TRANSACTION_onRangingStartFailed = 4;
        static final int TRANSACTION_onRangingStarted = 3;
        static final int TRANSACTION_onRangingStopFailed = 8;
        static final int TRANSACTION_onRangingStopped = 7;

        public Stub() {
            attachInterface(this, IUwbRangingCallbacks.DESCRIPTOR);
        }

        public static IUwbRangingCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IUwbRangingCallbacks.DESCRIPTOR);
            if (iin == null || !(iin instanceof IUwbRangingCallbacks)) {
                return new Proxy(obj);
            }
            return (IUwbRangingCallbacks) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onRangingOpened";
                case 2:
                    return "onRangingOpenFailed";
                case 3:
                    return "onRangingStarted";
                case 4:
                    return "onRangingStartFailed";
                case 5:
                    return "onRangingReconfigured";
                case 6:
                    return "onRangingReconfigureFailed";
                case 7:
                    return "onRangingStopped";
                case 8:
                    return "onRangingStopFailed";
                case 9:
                    return "onRangingClosed";
                case 10:
                    return "onRangingResult";
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
            SessionHandle _arg0;
            SessionHandle _arg02;
            PersistableBundle _arg2;
            SessionHandle _arg03;
            PersistableBundle _arg1;
            SessionHandle _arg04;
            PersistableBundle _arg22;
            SessionHandle _arg05;
            PersistableBundle _arg12;
            SessionHandle _arg06;
            PersistableBundle _arg23;
            SessionHandle _arg07;
            PersistableBundle _arg24;
            SessionHandle _arg08;
            PersistableBundle _arg25;
            SessionHandle _arg09;
            PersistableBundle _arg26;
            SessionHandle _arg010;
            RangingReport _arg13;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IUwbRangingCallbacks.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onRangingOpened(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg14 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onRangingOpenFailed(_arg02, _arg14, _arg2);
                            return true;
                        case 3:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onRangingStarted(_arg03, _arg1);
                            return true;
                        case 4:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            int _arg15 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            onRangingStartFailed(_arg04, _arg15, _arg22);
                            return true;
                        case 5:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onRangingReconfigured(_arg05, _arg12);
                            return true;
                        case 6:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            int _arg16 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg23 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            onRangingReconfigureFailed(_arg06, _arg16, _arg23);
                            return true;
                        case 7:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            int _arg17 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg24 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            onRangingStopped(_arg07, _arg17, _arg24);
                            return true;
                        case 8:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            int _arg18 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg25 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            onRangingStopFailed(_arg08, _arg18, _arg25);
                            return true;
                        case 9:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            int _arg19 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg26 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg26 = null;
                            }
                            onRangingClosed(_arg09, _arg19, _arg26);
                            return true;
                        case 10:
                            data.enforceInterface(IUwbRangingCallbacks.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = SessionHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = RangingReport.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            onRangingResult(_arg010, _arg13);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUwbRangingCallbacks {
            public static IUwbRangingCallbacks sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IUwbRangingCallbacks.DESCRIPTOR;
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingOpened(SessionHandle sessionHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingOpened(sessionHandle);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingOpenFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingOpenFailed(sessionHandle, reason, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingStarted(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingStarted(sessionHandle, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingStartFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingStartFailed(sessionHandle, reason, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingReconfigured(SessionHandle sessionHandle, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
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
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingReconfigured(sessionHandle, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingReconfigureFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingReconfigureFailed(sessionHandle, reason, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingStopped(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingStopped(sessionHandle, reason, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingStopFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingStopFailed(sessionHandle, reason, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingClosed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(reason);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingClosed(sessionHandle, reason, parameters);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.uwb.IUwbRangingCallbacks
            public void onRangingResult(SessionHandle sessionHandle, RangingReport result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbRangingCallbacks.DESCRIPTOR);
                    if (sessionHandle != null) {
                        _data.writeInt(1);
                        sessionHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangingResult(sessionHandle, result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUwbRangingCallbacks impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IUwbRangingCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
