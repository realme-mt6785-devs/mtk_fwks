package com.oplus.thermalcontrol;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IThermalStatusListener extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.thermalcontrol.IThermalStatusListener";

    void empty1() throws RemoteException;

    void empty2() throws RemoteException;

    void notifyThermalBroadCast(int i, int i2) throws RemoteException;

    void notifyThermalStatus(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IThermalStatusListener {
        @Override // com.oplus.thermalcontrol.IThermalStatusListener
        public void empty1() throws RemoteException {
        }

        @Override // com.oplus.thermalcontrol.IThermalStatusListener
        public void empty2() throws RemoteException {
        }

        @Override // com.oplus.thermalcontrol.IThermalStatusListener
        public void notifyThermalStatus(int status) throws RemoteException {
        }

        @Override // com.oplus.thermalcontrol.IThermalStatusListener
        public void notifyThermalBroadCast(int level, int temp) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IThermalStatusListener {
        static final int TRANSACTION_empty1 = 1;
        static final int TRANSACTION_empty2 = 2;
        static final int TRANSACTION_notifyThermalBroadCast = 4;
        static final int TRANSACTION_notifyThermalStatus = 3;

        public Stub() {
            attachInterface(this, IThermalStatusListener.DESCRIPTOR);
        }

        public static IThermalStatusListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IThermalStatusListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IThermalStatusListener)) {
                return new Proxy(obj);
            }
            return (IThermalStatusListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "empty1";
                case 2:
                    return "empty2";
                case 3:
                    return "notifyThermalStatus";
                case 4:
                    return "notifyThermalBroadCast";
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
                    reply.writeString(IThermalStatusListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IThermalStatusListener.DESCRIPTOR);
                            empty1();
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IThermalStatusListener.DESCRIPTOR);
                            empty2();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IThermalStatusListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            notifyThermalStatus(_arg0);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IThermalStatusListener.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg1 = data.readInt();
                            notifyThermalBroadCast(_arg02, _arg1);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IThermalStatusListener {
            public static IThermalStatusListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IThermalStatusListener.DESCRIPTOR;
            }

            @Override // com.oplus.thermalcontrol.IThermalStatusListener
            public void empty1() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IThermalStatusListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().empty1();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.thermalcontrol.IThermalStatusListener
            public void empty2() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IThermalStatusListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().empty2();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.thermalcontrol.IThermalStatusListener
            public void notifyThermalStatus(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IThermalStatusListener.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyThermalStatus(status);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.thermalcontrol.IThermalStatusListener
            public void notifyThermalBroadCast(int level, int temp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IThermalStatusListener.DESCRIPTOR);
                    _data.writeInt(level);
                    _data.writeInt(temp);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyThermalBroadCast(level, temp);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IThermalStatusListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IThermalStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
