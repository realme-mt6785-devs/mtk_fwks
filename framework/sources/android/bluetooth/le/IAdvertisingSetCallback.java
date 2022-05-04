package android.bluetooth.le;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IAdvertisingSetCallback extends IInterface {
    void onAdvertisingDataSet(int i, int i2) throws RemoteException;

    void onAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException;

    void onAdvertisingParametersUpdated(int i, int i2, int i3) throws RemoteException;

    void onAdvertisingSetStarted(int i, int i2, int i3) throws RemoteException;

    void onAdvertisingSetStopped(int i) throws RemoteException;

    void onOwnAddressRead(int i, int i2, String str) throws RemoteException;

    void onPeriodicAdvertisingDataSet(int i, int i2) throws RemoteException;

    void onPeriodicAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException;

    void onPeriodicAdvertisingParametersUpdated(int i, int i2) throws RemoteException;

    void onScanResponseDataSet(int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAdvertisingSetCallback {
        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingSetStarted(int advertiserId, int tx_power, int status) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onOwnAddressRead(int advertiserId, int addressType, String address) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingSetStopped(int advertiserId) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingEnabled(int advertiserId, boolean enable, int status) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingDataSet(int advertiserId, int status) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onScanResponseDataSet(int advertiserId, int status) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingParametersUpdated(int advertiserId, int tx_power, int status) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onPeriodicAdvertisingParametersUpdated(int advertiserId, int status) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onPeriodicAdvertisingDataSet(int advertiserId, int status) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onPeriodicAdvertisingEnabled(int advertiserId, boolean enable, int status) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAdvertisingSetCallback {
        public static final String DESCRIPTOR = "android.bluetooth.le.IAdvertisingSetCallback";
        static final int TRANSACTION_onAdvertisingDataSet = 5;
        static final int TRANSACTION_onAdvertisingEnabled = 4;
        static final int TRANSACTION_onAdvertisingParametersUpdated = 7;
        static final int TRANSACTION_onAdvertisingSetStarted = 1;
        static final int TRANSACTION_onAdvertisingSetStopped = 3;
        static final int TRANSACTION_onOwnAddressRead = 2;
        static final int TRANSACTION_onPeriodicAdvertisingDataSet = 9;
        static final int TRANSACTION_onPeriodicAdvertisingEnabled = 10;
        static final int TRANSACTION_onPeriodicAdvertisingParametersUpdated = 8;
        static final int TRANSACTION_onScanResponseDataSet = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAdvertisingSetCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAdvertisingSetCallback)) {
                return new Proxy(obj);
            }
            return (IAdvertisingSetCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAdvertisingSetStarted";
                case 2:
                    return "onOwnAddressRead";
                case 3:
                    return "onAdvertisingSetStopped";
                case 4:
                    return "onAdvertisingEnabled";
                case 5:
                    return "onAdvertisingDataSet";
                case 6:
                    return "onScanResponseDataSet";
                case 7:
                    return "onAdvertisingParametersUpdated";
                case 8:
                    return "onPeriodicAdvertisingParametersUpdated";
                case 9:
                    return "onPeriodicAdvertisingDataSet";
                case 10:
                    return "onPeriodicAdvertisingEnabled";
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
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg12 = data.readInt();
                            int _arg2 = data.readInt();
                            onAdvertisingSetStarted(_arg0, _arg12, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg13 = data.readInt();
                            String _arg22 = data.readString();
                            onOwnAddressRead(_arg02, _arg13, _arg22);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            onAdvertisingSetStopped(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            int _arg23 = data.readInt();
                            onAdvertisingEnabled(_arg04, _arg1, _arg23);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onAdvertisingDataSet(_arg05, data.readInt());
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            onScanResponseDataSet(_arg06, data.readInt());
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg14 = data.readInt();
                            int _arg24 = data.readInt();
                            onAdvertisingParametersUpdated(_arg07, _arg14, _arg24);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            onPeriodicAdvertisingParametersUpdated(_arg08, data.readInt());
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            onPeriodicAdvertisingDataSet(_arg09, data.readInt());
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            int _arg25 = data.readInt();
                            onPeriodicAdvertisingEnabled(_arg010, _arg1, _arg25);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAdvertisingSetCallback {
            public static IAdvertisingSetCallback sDefaultImpl;
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

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingSetStarted(int advertiserId, int tx_power, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(tx_power);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdvertisingSetStarted(advertiserId, tx_power, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onOwnAddressRead(int advertiserId, int addressType, String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(addressType);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOwnAddressRead(advertiserId, addressType, address);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingSetStopped(int advertiserId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdvertisingSetStopped(advertiserId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingEnabled(int advertiserId, boolean enable, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(enable ? 1 : 0);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdvertisingEnabled(advertiserId, enable, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingDataSet(int advertiserId, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdvertisingDataSet(advertiserId, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onScanResponseDataSet(int advertiserId, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScanResponseDataSet(advertiserId, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingParametersUpdated(int advertiserId, int tx_power, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(tx_power);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdvertisingParametersUpdated(advertiserId, tx_power, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onPeriodicAdvertisingParametersUpdated(int advertiserId, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPeriodicAdvertisingParametersUpdated(advertiserId, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onPeriodicAdvertisingDataSet(int advertiserId, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPeriodicAdvertisingDataSet(advertiserId, status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onPeriodicAdvertisingEnabled(int advertiserId, boolean enable, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(enable ? 1 : 0);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPeriodicAdvertisingEnabled(advertiserId, enable, status);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAdvertisingSetCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAdvertisingSetCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
