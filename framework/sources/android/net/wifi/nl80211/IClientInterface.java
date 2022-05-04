package android.net.wifi.nl80211;

import android.net.wifi.nl80211.ISendMgmtFrameEvent;
import android.net.wifi.nl80211.IWifiScannerImpl;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IClientInterface extends IInterface {
    public static final String DESCRIPTOR = "android.net.wifi.nl80211.IClientInterface";

    void SendMgmtFrame(byte[] bArr, ISendMgmtFrameEvent iSendMgmtFrameEvent, int i) throws RemoteException;

    String getInterfaceName() throws RemoteException;

    byte[] getMacAddress() throws RemoteException;

    int[] getPacketCounters() throws RemoteException;

    IWifiScannerImpl getWifiScannerImpl() throws RemoteException;

    int[] signalPoll() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IClientInterface {
        @Override // android.net.wifi.nl80211.IClientInterface
        public int[] getPacketCounters() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public int[] signalPoll() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public byte[] getMacAddress() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public String getInterfaceName() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public IWifiScannerImpl getWifiScannerImpl() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public void SendMgmtFrame(byte[] frame, ISendMgmtFrameEvent callback, int mcs) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IClientInterface {
        static final int TRANSACTION_SendMgmtFrame = 6;
        static final int TRANSACTION_getInterfaceName = 4;
        static final int TRANSACTION_getMacAddress = 3;
        static final int TRANSACTION_getPacketCounters = 1;
        static final int TRANSACTION_getWifiScannerImpl = 5;
        static final int TRANSACTION_signalPoll = 2;

        public Stub() {
            attachInterface(this, IClientInterface.DESCRIPTOR);
        }

        public static IClientInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IClientInterface.DESCRIPTOR);
            if (iin == null || !(iin instanceof IClientInterface)) {
                return new Proxy(obj);
            }
            return (IClientInterface) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getPacketCounters";
                case 2:
                    return "signalPoll";
                case 3:
                    return "getMacAddress";
                case 4:
                    return "getInterfaceName";
                case 5:
                    return "getWifiScannerImpl";
                case 6:
                    return "SendMgmtFrame";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IClientInterface.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IClientInterface.DESCRIPTOR);
                            int[] _result = getPacketCounters();
                            reply.writeNoException();
                            reply.writeIntArray(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IClientInterface.DESCRIPTOR);
                            int[] _result2 = signalPoll();
                            reply.writeNoException();
                            reply.writeIntArray(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IClientInterface.DESCRIPTOR);
                            byte[] _result3 = getMacAddress();
                            reply.writeNoException();
                            reply.writeByteArray(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IClientInterface.DESCRIPTOR);
                            String _result4 = getInterfaceName();
                            reply.writeNoException();
                            reply.writeString(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IClientInterface.DESCRIPTOR);
                            IWifiScannerImpl _result5 = getWifiScannerImpl();
                            reply.writeNoException();
                            reply.writeStrongBinder(_result5 != null ? _result5.asBinder() : null);
                            return true;
                        case 6:
                            data.enforceInterface(IClientInterface.DESCRIPTOR);
                            byte[] _arg0 = data.createByteArray();
                            ISendMgmtFrameEvent _arg1 = ISendMgmtFrameEvent.Stub.asInterface(data.readStrongBinder());
                            int _arg2 = data.readInt();
                            SendMgmtFrame(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IClientInterface {
            public static IClientInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IClientInterface.DESCRIPTOR;
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public int[] getPacketCounters() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IClientInterface.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPacketCounters();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public int[] signalPoll() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IClientInterface.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().signalPoll();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public byte[] getMacAddress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IClientInterface.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMacAddress();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public String getInterfaceName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IClientInterface.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterfaceName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public IWifiScannerImpl getWifiScannerImpl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IClientInterface.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWifiScannerImpl();
                    }
                    _reply.readException();
                    IWifiScannerImpl _result = IWifiScannerImpl.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public void SendMgmtFrame(byte[] frame, ISendMgmtFrameEvent callback, int mcs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IClientInterface.DESCRIPTOR);
                    _data.writeByteArray(frame);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(mcs);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().SendMgmtFrame(frame, callback, mcs);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IClientInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IClientInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
