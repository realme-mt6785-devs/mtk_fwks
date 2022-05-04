package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.telephony.ims.RcsClientConfiguration;
import android.telephony.ims.aidl.IImsConfigCallback;
import android.telephony.ims.aidl.IRcsConfigCallback;
/* loaded from: classes3.dex */
public interface IImsConfig extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsConfig";

    void addImsConfigCallback(IImsConfigCallback iImsConfigCallback) throws RemoteException;

    void addRcsConfigCallback(IRcsConfigCallback iRcsConfigCallback) throws RemoteException;

    int getConfigInt(int i) throws RemoteException;

    String getConfigString(int i) throws RemoteException;

    void notifyIntImsConfigChanged(int i, int i2) throws RemoteException;

    void notifyRcsAutoConfigurationReceived(byte[] bArr, boolean z) throws RemoteException;

    void notifyRcsAutoConfigurationRemoved() throws RemoteException;

    void notifyStringImsConfigChanged(int i, String str) throws RemoteException;

    void removeImsConfigCallback(IImsConfigCallback iImsConfigCallback) throws RemoteException;

    void removeRcsConfigCallback(IRcsConfigCallback iRcsConfigCallback) throws RemoteException;

    int setConfigInt(int i, int i2) throws RemoteException;

    int setConfigString(int i, String str) throws RemoteException;

    void setRcsClientConfiguration(RcsClientConfiguration rcsClientConfiguration) throws RemoteException;

    void triggerRcsReconfiguration() throws RemoteException;

    void updateImsCarrierConfigs(PersistableBundle persistableBundle) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IImsConfig {
        @Override // android.telephony.ims.aidl.IImsConfig
        public void addImsConfigCallback(IImsConfigCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void removeImsConfigCallback(IImsConfigCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public int getConfigInt(int item) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public String getConfigString(int item) throws RemoteException {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public int setConfigInt(int item, int value) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public int setConfigString(int item, String value) throws RemoteException {
            return 0;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void updateImsCarrierConfigs(PersistableBundle bundle) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyRcsAutoConfigurationReceived(byte[] config, boolean isCompressed) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyRcsAutoConfigurationRemoved() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void addRcsConfigCallback(IRcsConfigCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void removeRcsConfigCallback(IRcsConfigCallback c) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void triggerRcsReconfiguration() throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void setRcsClientConfiguration(RcsClientConfiguration rcc) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyIntImsConfigChanged(int item, int value) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyStringImsConfigChanged(int item, String value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IImsConfig {
        static final int TRANSACTION_addImsConfigCallback = 1;
        static final int TRANSACTION_addRcsConfigCallback = 10;
        static final int TRANSACTION_getConfigInt = 3;
        static final int TRANSACTION_getConfigString = 4;
        static final int TRANSACTION_notifyIntImsConfigChanged = 14;
        static final int TRANSACTION_notifyRcsAutoConfigurationReceived = 8;
        static final int TRANSACTION_notifyRcsAutoConfigurationRemoved = 9;
        static final int TRANSACTION_notifyStringImsConfigChanged = 15;
        static final int TRANSACTION_removeImsConfigCallback = 2;
        static final int TRANSACTION_removeRcsConfigCallback = 11;
        static final int TRANSACTION_setConfigInt = 5;
        static final int TRANSACTION_setConfigString = 6;
        static final int TRANSACTION_setRcsClientConfiguration = 13;
        static final int TRANSACTION_triggerRcsReconfiguration = 12;
        static final int TRANSACTION_updateImsCarrierConfigs = 7;

        public Stub() {
            attachInterface(this, IImsConfig.DESCRIPTOR);
        }

        public static IImsConfig asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImsConfig.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImsConfig)) {
                return new Proxy(obj);
            }
            return (IImsConfig) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addImsConfigCallback";
                case 2:
                    return "removeImsConfigCallback";
                case 3:
                    return "getConfigInt";
                case 4:
                    return "getConfigString";
                case 5:
                    return "setConfigInt";
                case 6:
                    return "setConfigString";
                case 7:
                    return "updateImsCarrierConfigs";
                case 8:
                    return "notifyRcsAutoConfigurationReceived";
                case 9:
                    return "notifyRcsAutoConfigurationRemoved";
                case 10:
                    return "addRcsConfigCallback";
                case 11:
                    return "removeRcsConfigCallback";
                case 12:
                    return "triggerRcsReconfiguration";
                case 13:
                    return "setRcsClientConfiguration";
                case 14:
                    return "notifyIntImsConfigChanged";
                case 15:
                    return "notifyStringImsConfigChanged";
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
            PersistableBundle _arg0;
            RcsClientConfiguration _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IImsConfig.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            IImsConfigCallback _arg03 = IImsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            addImsConfigCallback(_arg03);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            IImsConfigCallback _arg04 = IImsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            removeImsConfigCallback(_arg04);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _result = getConfigInt(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 4:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            String _result2 = getConfigString(_arg06);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg1 = data.readInt();
                            int _result3 = setConfigInt(_arg07, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 6:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            String _arg12 = data.readString();
                            int _result4 = setConfigString(_arg08, _arg12);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            updateImsCarrierConfigs(_arg0);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            byte[] _arg09 = data.createByteArray();
                            boolean _arg13 = data.readInt() != 0;
                            notifyRcsAutoConfigurationReceived(_arg09, _arg13);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            notifyRcsAutoConfigurationRemoved();
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            IRcsConfigCallback _arg010 = IRcsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            addRcsConfigCallback(_arg010);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            IRcsConfigCallback _arg011 = IRcsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            removeRcsConfigCallback(_arg011);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            triggerRcsReconfiguration();
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = RcsClientConfiguration.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            setRcsClientConfiguration(_arg02);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg14 = data.readInt();
                            notifyIntImsConfigChanged(_arg012, _arg14);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(IImsConfig.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            String _arg15 = data.readString();
                            notifyStringImsConfigChanged(_arg013, _arg15);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IImsConfig {
            public static IImsConfig sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImsConfig.DESCRIPTOR;
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void addImsConfigCallback(IImsConfigCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addImsConfigCallback(c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void removeImsConfigCallback(IImsConfigCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeImsConfigCallback(c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public int getConfigInt(int item) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeInt(item);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigInt(item);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public String getConfigString(int item) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeInt(item);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigString(item);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public int setConfigInt(int item, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeInt(item);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConfigInt(item, value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public int setConfigString(int item, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeInt(item);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConfigString(item, value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void updateImsCarrierConfigs(PersistableBundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateImsCarrierConfigs(bundle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void notifyRcsAutoConfigurationReceived(byte[] config, boolean isCompressed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeByteArray(config);
                    _data.writeInt(isCompressed ? 1 : 0);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyRcsAutoConfigurationReceived(config, isCompressed);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void notifyRcsAutoConfigurationRemoved() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyRcsAutoConfigurationRemoved();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void addRcsConfigCallback(IRcsConfigCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addRcsConfigCallback(c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void removeRcsConfigCallback(IRcsConfigCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeRcsConfigCallback(c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void triggerRcsReconfiguration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().triggerRcsReconfiguration();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void setRcsClientConfiguration(RcsClientConfiguration rcc) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    if (rcc != null) {
                        _data.writeInt(1);
                        rcc.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRcsClientConfiguration(rcc);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void notifyIntImsConfigChanged(int item, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeInt(item);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyIntImsConfigChanged(item, value);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfig
            public void notifyStringImsConfigChanged(int item, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImsConfig.DESCRIPTOR);
                    _data.writeInt(item);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyStringImsConfigChanged(item, value);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsConfig impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImsConfig getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
