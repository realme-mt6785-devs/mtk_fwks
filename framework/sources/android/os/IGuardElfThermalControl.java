package android.os;
/* loaded from: classes2.dex */
public interface IGuardElfThermalControl extends IInterface {
    public static final String DESCRIPTOR = "android.os.IGuardElfThermalControl";

    int getBattPPSChgIng() throws RemoteException;

    int getBattPPSChgPower() throws RemoteException;

    float getBeginDecimal() throws RemoteException;

    int getChargerTechnology() throws RemoteException;

    int getCustomSelectChgMode() throws RemoteException;

    float getEndDecimal() throws RemoteException;

    int getPsyBatteryHmac() throws RemoteException;

    int getPsyBatteryNotify() throws RemoteException;

    int getPsyBatteryRm() throws RemoteException;

    int getPsyOtgOnline() throws RemoteException;

    int getSmartChgMode() throws RemoteException;

    int getWiredOtgOnline() throws RemoteException;

    int getWirelessAdapterPower() throws RemoteException;

    String getWirelessTXEnable() throws RemoteException;

    int getWirelessUserSleepMode() throws RemoteException;

    boolean isCameraOn() throws RemoteException;

    void setChargeLevel(String str, String str2) throws RemoteException;

    int setCustomSelectChgMode(int i, boolean z) throws RemoteException;

    void setPsyMmiChgEn(String str) throws RemoteException;

    int setSmartChgMode(String str) throws RemoteException;

    void setWirelessTXEnable(String str) throws RemoteException;

    void setWirelessUserSleepMode(String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IGuardElfThermalControl {
        @Override // android.os.IGuardElfThermalControl
        public void setChargeLevel(String data, String name) throws RemoteException {
        }

        @Override // android.os.IGuardElfThermalControl
        public boolean isCameraOn() throws RemoteException {
            return false;
        }

        @Override // android.os.IGuardElfThermalControl
        public float getBeginDecimal() throws RemoteException {
            return 0.0f;
        }

        @Override // android.os.IGuardElfThermalControl
        public float getEndDecimal() throws RemoteException {
            return 0.0f;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getPsyOtgOnline() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getPsyBatteryHmac() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getChargerTechnology() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public void setWirelessTXEnable(String data) throws RemoteException {
        }

        @Override // android.os.IGuardElfThermalControl
        public String getWirelessTXEnable() throws RemoteException {
            return null;
        }

        @Override // android.os.IGuardElfThermalControl
        public void setWirelessUserSleepMode(String data) throws RemoteException {
        }

        @Override // android.os.IGuardElfThermalControl
        public int getWirelessUserSleepMode() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public void setPsyMmiChgEn(String data) throws RemoteException {
        }

        @Override // android.os.IGuardElfThermalControl
        public int getPsyBatteryRm() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getWiredOtgOnline() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getPsyBatteryNotify() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getWirelessAdapterPower() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getSmartChgMode() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int setSmartChgMode(String data) throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getBattPPSChgIng() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getBattPPSChgPower() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int getCustomSelectChgMode() throws RemoteException {
            return 0;
        }

        @Override // android.os.IGuardElfThermalControl
        public int setCustomSelectChgMode(int mode, boolean enable) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IGuardElfThermalControl {
        static final int TRANSACTION_getBattPPSChgIng = 19;
        static final int TRANSACTION_getBattPPSChgPower = 20;
        static final int TRANSACTION_getBeginDecimal = 3;
        static final int TRANSACTION_getChargerTechnology = 7;
        static final int TRANSACTION_getCustomSelectChgMode = 21;
        static final int TRANSACTION_getEndDecimal = 4;
        static final int TRANSACTION_getPsyBatteryHmac = 6;
        static final int TRANSACTION_getPsyBatteryNotify = 15;
        static final int TRANSACTION_getPsyBatteryRm = 13;
        static final int TRANSACTION_getPsyOtgOnline = 5;
        static final int TRANSACTION_getSmartChgMode = 17;
        static final int TRANSACTION_getWiredOtgOnline = 14;
        static final int TRANSACTION_getWirelessAdapterPower = 16;
        static final int TRANSACTION_getWirelessTXEnable = 9;
        static final int TRANSACTION_getWirelessUserSleepMode = 11;
        static final int TRANSACTION_isCameraOn = 2;
        static final int TRANSACTION_setChargeLevel = 1;
        static final int TRANSACTION_setCustomSelectChgMode = 22;
        static final int TRANSACTION_setPsyMmiChgEn = 12;
        static final int TRANSACTION_setSmartChgMode = 18;
        static final int TRANSACTION_setWirelessTXEnable = 8;
        static final int TRANSACTION_setWirelessUserSleepMode = 10;

        public Stub() {
            attachInterface(this, IGuardElfThermalControl.DESCRIPTOR);
        }

        public static IGuardElfThermalControl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IGuardElfThermalControl.DESCRIPTOR);
            if (iin == null || !(iin instanceof IGuardElfThermalControl)) {
                return new Proxy(obj);
            }
            return (IGuardElfThermalControl) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setChargeLevel";
                case 2:
                    return "isCameraOn";
                case 3:
                    return "getBeginDecimal";
                case 4:
                    return "getEndDecimal";
                case 5:
                    return "getPsyOtgOnline";
                case 6:
                    return "getPsyBatteryHmac";
                case 7:
                    return "getChargerTechnology";
                case 8:
                    return "setWirelessTXEnable";
                case 9:
                    return "getWirelessTXEnable";
                case 10:
                    return "setWirelessUserSleepMode";
                case 11:
                    return "getWirelessUserSleepMode";
                case 12:
                    return "setPsyMmiChgEn";
                case 13:
                    return "getPsyBatteryRm";
                case 14:
                    return "getWiredOtgOnline";
                case 15:
                    return "getPsyBatteryNotify";
                case 16:
                    return "getWirelessAdapterPower";
                case 17:
                    return "getSmartChgMode";
                case 18:
                    return "setSmartChgMode";
                case 19:
                    return "getBattPPSChgIng";
                case 20:
                    return "getBattPPSChgPower";
                case 21:
                    return "getCustomSelectChgMode";
                case 22:
                    return "setCustomSelectChgMode";
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
                    reply.writeString(IGuardElfThermalControl.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            String _arg0 = data.readString();
                            String _arg1 = data.readString();
                            setChargeLevel(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            boolean isCameraOn = isCameraOn();
                            reply.writeNoException();
                            reply.writeInt(isCameraOn ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            float _result = getBeginDecimal();
                            reply.writeNoException();
                            reply.writeFloat(_result);
                            return true;
                        case 4:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            float _result2 = getEndDecimal();
                            reply.writeNoException();
                            reply.writeFloat(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result3 = getPsyOtgOnline();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 6:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result4 = getPsyBatteryHmac();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result5 = getChargerTechnology();
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 8:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            String _arg02 = data.readString();
                            setWirelessTXEnable(_arg02);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            String _result6 = getWirelessTXEnable();
                            reply.writeNoException();
                            reply.writeString(_result6);
                            return true;
                        case 10:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            String _arg03 = data.readString();
                            setWirelessUserSleepMode(_arg03);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result7 = getWirelessUserSleepMode();
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 12:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            String _arg04 = data.readString();
                            setPsyMmiChgEn(_arg04);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result8 = getPsyBatteryRm();
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 14:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result9 = getWiredOtgOnline();
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 15:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result10 = getPsyBatteryNotify();
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 16:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result11 = getWirelessAdapterPower();
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 17:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result12 = getSmartChgMode();
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 18:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _result13 = setSmartChgMode(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 19:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result14 = getBattPPSChgIng();
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 20:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result15 = getBattPPSChgPower();
                            reply.writeNoException();
                            reply.writeInt(_result15);
                            return true;
                        case 21:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _result16 = getCustomSelectChgMode();
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        case 22:
                            data.enforceInterface(IGuardElfThermalControl.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            boolean _arg12 = data.readInt() != 0;
                            int _result17 = setCustomSelectChgMode(_arg06, _arg12);
                            reply.writeNoException();
                            reply.writeInt(_result17);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IGuardElfThermalControl {
            public static IGuardElfThermalControl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IGuardElfThermalControl.DESCRIPTOR;
            }

            @Override // android.os.IGuardElfThermalControl
            public void setChargeLevel(String data, String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setChargeLevel(data, name);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public boolean isCameraOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCameraOn();
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

            @Override // android.os.IGuardElfThermalControl
            public float getBeginDecimal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBeginDecimal();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public float getEndDecimal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEndDecimal();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getPsyOtgOnline() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsyOtgOnline();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getPsyBatteryHmac() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsyBatteryHmac();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getChargerTechnology() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargerTechnology();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public void setWirelessTXEnable(String data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    _data.writeString(data);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setWirelessTXEnable(data);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public String getWirelessTXEnable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWirelessTXEnable();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public void setWirelessUserSleepMode(String data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    _data.writeString(data);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setWirelessUserSleepMode(data);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getWirelessUserSleepMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWirelessUserSleepMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public void setPsyMmiChgEn(String data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    _data.writeString(data);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPsyMmiChgEn(data);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getPsyBatteryRm() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsyBatteryRm();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getWiredOtgOnline() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWiredOtgOnline();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getPsyBatteryNotify() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsyBatteryNotify();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getWirelessAdapterPower() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWirelessAdapterPower();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getSmartChgMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmartChgMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int setSmartChgMode(String data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    _data.writeString(data);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSmartChgMode(data);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getBattPPSChgIng() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBattPPSChgIng();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getBattPPSChgPower() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBattPPSChgPower();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int getCustomSelectChgMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCustomSelectChgMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IGuardElfThermalControl
            public int setCustomSelectChgMode(int mode, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IGuardElfThermalControl.DESCRIPTOR);
                    _data.writeInt(mode);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCustomSelectChgMode(mode, enable);
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

        public static boolean setDefaultImpl(IGuardElfThermalControl impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGuardElfThermalControl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
