package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IColorDisplayManager extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.display.IColorDisplayManager";

    int getColorMode() throws RemoteException;

    int getNightDisplayAutoMode() throws RemoteException;

    int getNightDisplayAutoModeRaw() throws RemoteException;

    int getNightDisplayColorTemperature() throws RemoteException;

    Time getNightDisplayCustomEndTime() throws RemoteException;

    Time getNightDisplayCustomStartTime() throws RemoteException;

    float getReduceBrightColorsOffsetFactor() throws RemoteException;

    int getReduceBrightColorsStrength() throws RemoteException;

    int getTransformCapabilities() throws RemoteException;

    boolean isDeviceColorManaged() throws RemoteException;

    boolean isDisplayWhiteBalanceEnabled() throws RemoteException;

    boolean isNightDisplayActivated() throws RemoteException;

    boolean isReduceBrightColorsActivated() throws RemoteException;

    boolean isSaturationActivated() throws RemoteException;

    boolean setAppSaturationLevel(String str, int i) throws RemoteException;

    void setColorMode(int i) throws RemoteException;

    boolean setDisplayWhiteBalanceEnabled(boolean z) throws RemoteException;

    boolean setNightDisplayActivated(boolean z) throws RemoteException;

    boolean setNightDisplayAutoMode(int i) throws RemoteException;

    boolean setNightDisplayColorTemperature(int i) throws RemoteException;

    boolean setNightDisplayCustomEndTime(Time time) throws RemoteException;

    boolean setNightDisplayCustomStartTime(Time time) throws RemoteException;

    boolean setReduceBrightColorsActivated(boolean z) throws RemoteException;

    boolean setReduceBrightColorsStrength(int i) throws RemoteException;

    boolean setSaturationLevel(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IColorDisplayManager {
        @Override // android.hardware.display.IColorDisplayManager
        public boolean isDeviceColorManaged() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setSaturationLevel(int saturationLevel) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setAppSaturationLevel(String packageName, int saturationLevel) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isSaturationActivated() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getTransformCapabilities() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isNightDisplayActivated() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayActivated(boolean activated) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getNightDisplayColorTemperature() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayColorTemperature(int temperature) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getNightDisplayAutoMode() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getNightDisplayAutoModeRaw() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayAutoMode(int autoMode) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public Time getNightDisplayCustomStartTime() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayCustomStartTime(Time time) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public Time getNightDisplayCustomEndTime() throws RemoteException {
            return null;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setNightDisplayCustomEndTime(Time time) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getColorMode() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public void setColorMode(int colorMode) throws RemoteException {
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isDisplayWhiteBalanceEnabled() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setDisplayWhiteBalanceEnabled(boolean enabled) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean isReduceBrightColorsActivated() throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setReduceBrightColorsActivated(boolean activated) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public int getReduceBrightColorsStrength() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public boolean setReduceBrightColorsStrength(int strength) throws RemoteException {
            return false;
        }

        @Override // android.hardware.display.IColorDisplayManager
        public float getReduceBrightColorsOffsetFactor() throws RemoteException {
            return 0.0f;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IColorDisplayManager {
        static final int TRANSACTION_getColorMode = 17;
        static final int TRANSACTION_getNightDisplayAutoMode = 10;
        static final int TRANSACTION_getNightDisplayAutoModeRaw = 11;
        static final int TRANSACTION_getNightDisplayColorTemperature = 8;
        static final int TRANSACTION_getNightDisplayCustomEndTime = 15;
        static final int TRANSACTION_getNightDisplayCustomStartTime = 13;
        static final int TRANSACTION_getReduceBrightColorsOffsetFactor = 25;
        static final int TRANSACTION_getReduceBrightColorsStrength = 23;
        static final int TRANSACTION_getTransformCapabilities = 5;
        static final int TRANSACTION_isDeviceColorManaged = 1;
        static final int TRANSACTION_isDisplayWhiteBalanceEnabled = 19;
        static final int TRANSACTION_isNightDisplayActivated = 6;
        static final int TRANSACTION_isReduceBrightColorsActivated = 21;
        static final int TRANSACTION_isSaturationActivated = 4;
        static final int TRANSACTION_setAppSaturationLevel = 3;
        static final int TRANSACTION_setColorMode = 18;
        static final int TRANSACTION_setDisplayWhiteBalanceEnabled = 20;
        static final int TRANSACTION_setNightDisplayActivated = 7;
        static final int TRANSACTION_setNightDisplayAutoMode = 12;
        static final int TRANSACTION_setNightDisplayColorTemperature = 9;
        static final int TRANSACTION_setNightDisplayCustomEndTime = 16;
        static final int TRANSACTION_setNightDisplayCustomStartTime = 14;
        static final int TRANSACTION_setReduceBrightColorsActivated = 22;
        static final int TRANSACTION_setReduceBrightColorsStrength = 24;
        static final int TRANSACTION_setSaturationLevel = 2;

        public Stub() {
            attachInterface(this, IColorDisplayManager.DESCRIPTOR);
        }

        public static IColorDisplayManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IColorDisplayManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IColorDisplayManager)) {
                return new Proxy(obj);
            }
            return (IColorDisplayManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "isDeviceColorManaged";
                case 2:
                    return "setSaturationLevel";
                case 3:
                    return "setAppSaturationLevel";
                case 4:
                    return "isSaturationActivated";
                case 5:
                    return "getTransformCapabilities";
                case 6:
                    return "isNightDisplayActivated";
                case 7:
                    return "setNightDisplayActivated";
                case 8:
                    return "getNightDisplayColorTemperature";
                case 9:
                    return "setNightDisplayColorTemperature";
                case 10:
                    return "getNightDisplayAutoMode";
                case 11:
                    return "getNightDisplayAutoModeRaw";
                case 12:
                    return "setNightDisplayAutoMode";
                case 13:
                    return "getNightDisplayCustomStartTime";
                case 14:
                    return "setNightDisplayCustomStartTime";
                case 15:
                    return "getNightDisplayCustomEndTime";
                case 16:
                    return "setNightDisplayCustomEndTime";
                case 17:
                    return "getColorMode";
                case 18:
                    return "setColorMode";
                case 19:
                    return "isDisplayWhiteBalanceEnabled";
                case 20:
                    return "setDisplayWhiteBalanceEnabled";
                case 21:
                    return "isReduceBrightColorsActivated";
                case 22:
                    return "setReduceBrightColorsActivated";
                case 23:
                    return "getReduceBrightColorsStrength";
                case 24:
                    return "setReduceBrightColorsStrength";
                case 25:
                    return "getReduceBrightColorsOffsetFactor";
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
            Time _arg0;
            Time _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IColorDisplayManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg03 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean isDeviceColorManaged = isDeviceColorManaged();
                            reply.writeNoException();
                            reply.writeInt(isDeviceColorManaged ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean saturationLevel = setSaturationLevel(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(saturationLevel ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            String _arg04 = data.readString();
                            int _arg1 = data.readInt();
                            boolean appSaturationLevel = setAppSaturationLevel(_arg04, _arg1);
                            reply.writeNoException();
                            reply.writeInt(appSaturationLevel ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean isSaturationActivated = isSaturationActivated();
                            reply.writeNoException();
                            reply.writeInt(isSaturationActivated ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            int _result = getTransformCapabilities();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 6:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean isNightDisplayActivated = isNightDisplayActivated();
                            reply.writeNoException();
                            reply.writeInt(isNightDisplayActivated ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            boolean nightDisplayActivated = setNightDisplayActivated(_arg03);
                            reply.writeNoException();
                            reply.writeInt(nightDisplayActivated ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            int _result2 = getNightDisplayColorTemperature();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 9:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean nightDisplayColorTemperature = setNightDisplayColorTemperature(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(nightDisplayColorTemperature ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            int _result3 = getNightDisplayAutoMode();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 11:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            int _result4 = getNightDisplayAutoModeRaw();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 12:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean nightDisplayAutoMode = setNightDisplayAutoMode(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(nightDisplayAutoMode ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            Time _result5 = getNightDisplayCustomStartTime();
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 14:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Time.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean nightDisplayCustomStartTime = setNightDisplayCustomStartTime(_arg0);
                            reply.writeNoException();
                            reply.writeInt(nightDisplayCustomStartTime ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            Time _result6 = getNightDisplayCustomEndTime();
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 16:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Time.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            boolean nightDisplayCustomEndTime = setNightDisplayCustomEndTime(_arg02);
                            reply.writeNoException();
                            reply.writeInt(nightDisplayCustomEndTime ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            int _result7 = getColorMode();
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 18:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            setColorMode(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean isDisplayWhiteBalanceEnabled = isDisplayWhiteBalanceEnabled();
                            reply.writeNoException();
                            reply.writeInt(isDisplayWhiteBalanceEnabled ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            boolean displayWhiteBalanceEnabled = setDisplayWhiteBalanceEnabled(_arg03);
                            reply.writeNoException();
                            reply.writeInt(displayWhiteBalanceEnabled ? 1 : 0);
                            return true;
                        case 21:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean isReduceBrightColorsActivated = isReduceBrightColorsActivated();
                            reply.writeNoException();
                            reply.writeInt(isReduceBrightColorsActivated ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            boolean reduceBrightColorsActivated = setReduceBrightColorsActivated(_arg03);
                            reply.writeNoException();
                            reply.writeInt(reduceBrightColorsActivated ? 1 : 0);
                            return true;
                        case 23:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            int _result8 = getReduceBrightColorsStrength();
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 24:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            boolean reduceBrightColorsStrength = setReduceBrightColorsStrength(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(reduceBrightColorsStrength ? 1 : 0);
                            return true;
                        case 25:
                            data.enforceInterface(IColorDisplayManager.DESCRIPTOR);
                            float _result9 = getReduceBrightColorsOffsetFactor();
                            reply.writeNoException();
                            reply.writeFloat(_result9);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IColorDisplayManager {
            public static IColorDisplayManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IColorDisplayManager.DESCRIPTOR;
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isDeviceColorManaged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeviceColorManaged();
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

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setSaturationLevel(int saturationLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    _data.writeInt(saturationLevel);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSaturationLevel(saturationLevel);
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

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setAppSaturationLevel(String packageName, int saturationLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(saturationLevel);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAppSaturationLevel(packageName, saturationLevel);
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

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isSaturationActivated() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSaturationActivated();
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

            @Override // android.hardware.display.IColorDisplayManager
            public int getTransformCapabilities() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTransformCapabilities();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isNightDisplayActivated() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNightDisplayActivated();
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

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayActivated(boolean activated) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(activated ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayActivated(activated);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getNightDisplayColorTemperature() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayColorTemperature();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayColorTemperature(int temperature) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    _data.writeInt(temperature);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayColorTemperature(temperature);
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

            @Override // android.hardware.display.IColorDisplayManager
            public int getNightDisplayAutoMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayAutoMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getNightDisplayAutoModeRaw() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayAutoModeRaw();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayAutoMode(int autoMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    _data.writeInt(autoMode);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayAutoMode(autoMode);
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

            @Override // android.hardware.display.IColorDisplayManager
            public Time getNightDisplayCustomStartTime() throws RemoteException {
                Time _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayCustomStartTime();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Time.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayCustomStartTime(Time time) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = true;
                    if (time != null) {
                        _data.writeInt(1);
                        time.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayCustomStartTime(time);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public Time getNightDisplayCustomEndTime() throws RemoteException {
                Time _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNightDisplayCustomEndTime();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Time.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setNightDisplayCustomEndTime(Time time) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = true;
                    if (time != null) {
                        _data.writeInt(1);
                        time.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNightDisplayCustomEndTime(time);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getColorMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getColorMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public void setColorMode(int colorMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    _data.writeInt(colorMode);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setColorMode(colorMode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isDisplayWhiteBalanceEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDisplayWhiteBalanceEnabled();
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

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setDisplayWhiteBalanceEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDisplayWhiteBalanceEnabled(enabled);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean isReduceBrightColorsActivated() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isReduceBrightColorsActivated();
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

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setReduceBrightColorsActivated(boolean activated) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(activated ? 1 : 0);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setReduceBrightColorsActivated(activated);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public int getReduceBrightColorsStrength() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReduceBrightColorsStrength();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.display.IColorDisplayManager
            public boolean setReduceBrightColorsStrength(int strength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    _data.writeInt(strength);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setReduceBrightColorsStrength(strength);
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

            @Override // android.hardware.display.IColorDisplayManager
            public float getReduceBrightColorsOffsetFactor() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IColorDisplayManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReduceBrightColorsOffsetFactor();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IColorDisplayManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IColorDisplayManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
