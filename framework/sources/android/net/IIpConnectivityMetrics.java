package android.net;

import android.net.INetdEventCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IIpConnectivityMetrics extends IInterface {
    boolean addNetdEventCallback(int i, INetdEventCallback iNetdEventCallback) throws RemoteException;

    void logDefaultNetworkEvent(Network network, int i, boolean z, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, Network network2, int i2, LinkProperties linkProperties2, NetworkCapabilities networkCapabilities2) throws RemoteException;

    void logDefaultNetworkValidity(boolean z) throws RemoteException;

    int logEvent(ConnectivityMetricsEvent connectivityMetricsEvent) throws RemoteException;

    boolean removeNetdEventCallback(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IIpConnectivityMetrics {
        @Override // android.net.IIpConnectivityMetrics
        public int logEvent(ConnectivityMetricsEvent event) throws RemoteException {
            return 0;
        }

        @Override // android.net.IIpConnectivityMetrics
        public void logDefaultNetworkValidity(boolean valid) throws RemoteException {
        }

        @Override // android.net.IIpConnectivityMetrics
        public void logDefaultNetworkEvent(Network defaultNetwork, int score, boolean validated, LinkProperties lp, NetworkCapabilities nc, Network previousDefaultNetwork, int previousScore, LinkProperties previousLp, NetworkCapabilities previousNc) throws RemoteException {
        }

        @Override // android.net.IIpConnectivityMetrics
        public boolean addNetdEventCallback(int callerType, INetdEventCallback callback) throws RemoteException {
            return false;
        }

        @Override // android.net.IIpConnectivityMetrics
        public boolean removeNetdEventCallback(int callerType) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IIpConnectivityMetrics {
        public static final String DESCRIPTOR = "android.net.IIpConnectivityMetrics";
        static final int TRANSACTION_addNetdEventCallback = 4;
        static final int TRANSACTION_logDefaultNetworkEvent = 3;
        static final int TRANSACTION_logDefaultNetworkValidity = 2;
        static final int TRANSACTION_logEvent = 1;
        static final int TRANSACTION_removeNetdEventCallback = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IIpConnectivityMetrics asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IIpConnectivityMetrics)) {
                return new Proxy(obj);
            }
            return (IIpConnectivityMetrics) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "logEvent";
                case 2:
                    return "logDefaultNetworkValidity";
                case 3:
                    return "logDefaultNetworkEvent";
                case 4:
                    return "addNetdEventCallback";
                case 5:
                    return "removeNetdEventCallback";
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
            ConnectivityMetricsEvent _arg0;
            Network _arg02;
            boolean _arg2;
            LinkProperties _arg3;
            NetworkCapabilities _arg4;
            Network _arg5;
            LinkProperties _arg7;
            NetworkCapabilities _arg8;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg03 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ConnectivityMetricsEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _result = logEvent(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            logDefaultNetworkValidity(_arg03);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = (Network) Network.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg1 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            } else {
                                _arg2 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = (NetworkCapabilities) NetworkCapabilities.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = (Network) Network.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            int _arg6 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg7 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                            } else {
                                _arg7 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg8 = (NetworkCapabilities) NetworkCapabilities.CREATOR.createFromParcel(data);
                            } else {
                                _arg8 = null;
                            }
                            logDefaultNetworkEvent(_arg02, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            INetdEventCallback _arg12 = INetdEventCallback.Stub.asInterface(data.readStrongBinder());
                            boolean addNetdEventCallback = addNetdEventCallback(_arg04, _arg12);
                            reply.writeNoException();
                            reply.writeInt(addNetdEventCallback ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            boolean removeNetdEventCallback = removeNetdEventCallback(_arg05);
                            reply.writeNoException();
                            reply.writeInt(removeNetdEventCallback ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IIpConnectivityMetrics {
            public static IIpConnectivityMetrics sDefaultImpl;
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

            @Override // android.net.IIpConnectivityMetrics
            public int logEvent(ConnectivityMetricsEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().logEvent(event);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpConnectivityMetrics
            public void logDefaultNetworkValidity(boolean valid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(valid ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().logDefaultNetworkValidity(valid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IIpConnectivityMetrics
            public void logDefaultNetworkEvent(Network defaultNetwork, int score, boolean validated, LinkProperties lp, NetworkCapabilities nc, Network previousDefaultNetwork, int previousScore, LinkProperties previousLp, NetworkCapabilities previousNc) throws RemoteException {
                Parcel _data;
                Parcel _reply;
                Throwable th;
                Parcel _data2;
                Parcel _reply2;
                Parcel _data3 = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data3.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (defaultNetwork != null) {
                        try {
                            _data3.writeInt(1);
                            defaultNetwork.writeToParcel(_data3, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _data = _data3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeInt(score);
                    _data3.writeInt(validated ? 1 : 0);
                    if (lp != null) {
                        _data3.writeInt(1);
                        lp.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (nc != null) {
                        _data3.writeInt(1);
                        nc.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (previousDefaultNetwork != null) {
                        _data3.writeInt(1);
                        previousDefaultNetwork.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeInt(previousScore);
                    if (previousLp != null) {
                        _data3.writeInt(1);
                        previousLp.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (previousNc != null) {
                        _data3.writeInt(1);
                        previousNc.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data3, _reply3, 0);
                    try {
                        if (_status) {
                            _reply2 = _reply3;
                            _data2 = _data3;
                        } else if (Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().logDefaultNetworkEvent(defaultNetwork, score, validated, lp, nc, previousDefaultNetwork, previousScore, previousLp, previousNc);
                            _reply3.recycle();
                            _data3.recycle();
                            return;
                        } else {
                            _reply2 = _reply3;
                            _data2 = _data3;
                        }
                        _reply2.readException();
                        _reply2.recycle();
                        _data2.recycle();
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    _reply = _reply3;
                    _data = _data3;
                }
            }

            @Override // android.net.IIpConnectivityMetrics
            public boolean addNetdEventCallback(int callerType, INetdEventCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(callerType);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addNetdEventCallback(callerType, callback);
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

            @Override // android.net.IIpConnectivityMetrics
            public boolean removeNetdEventCallback(int callerType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(callerType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeNetdEventCallback(callerType);
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
        }

        public static boolean setDefaultImpl(IIpConnectivityMetrics impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IIpConnectivityMetrics getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
