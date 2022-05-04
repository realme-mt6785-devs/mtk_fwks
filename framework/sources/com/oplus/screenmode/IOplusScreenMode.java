package com.oplus.screenmode;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oplus.screenmode.IOplusScreenModeCallback;
/* loaded from: classes4.dex */
public interface IOplusScreenMode extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.screenmode.IOplusScreenMode";

    void addCallback(int i, IOplusScreenModeCallback iOplusScreenModeCallback) throws RemoteException;

    void enterDCAndLowBrightnessMode(boolean z) throws RemoteException;

    void enterPSMode(boolean z) throws RemoteException;

    void enterPSModeOnRate(boolean z, int i) throws RemoteException;

    void enterPSModeOnRateWithToken(boolean z, int i, IBinder iBinder) throws RemoteException;

    String getDisableOverrideViewList(String str) throws RemoteException;

    boolean getGameList(Bundle bundle) throws RemoteException;

    boolean isDisplayCompat(String str, int i) throws RemoteException;

    void keepHighRefreshRate(int i) throws RemoteException;

    void overrideWindowRefreshRate(IBinder iBinder, int i) throws RemoteException;

    void remove(int i, IOplusScreenModeCallback iOplusScreenModeCallback) throws RemoteException;

    boolean requestGameRefreshRate(String str, int i) throws RemoteException;

    boolean requestRefreshRate(boolean z, int i) throws RemoteException;

    boolean requestRefreshRateWithToken(boolean z, int i, IBinder iBinder) throws RemoteException;

    void setClientRefreshRate(IBinder iBinder, int i) throws RemoteException;

    boolean setHighTemperatureStatus(int i, int i2) throws RemoteException;

    void setMemcWorkStatus(boolean z) throws RemoteException;

    boolean supportDisplayCompat(String str, int i) throws RemoteException;

    void updateFpsWhenDcChange(boolean z) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusScreenMode {
        @Override // com.oplus.screenmode.IOplusScreenMode
        public void addCallback(int index, IOplusScreenModeCallback callback) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void remove(int index, IOplusScreenModeCallback callback) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void setClientRefreshRate(IBinder token, int rate) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public boolean requestRefreshRate(boolean open, int rate) throws RemoteException {
            return false;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public boolean supportDisplayCompat(String pkg, int uid) throws RemoteException {
            return false;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public boolean setHighTemperatureStatus(int status, int rate) throws RemoteException {
            return false;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void enterDCAndLowBrightnessMode(boolean enter) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public boolean isDisplayCompat(String packageName, int uid) throws RemoteException {
            return false;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void enterPSMode(boolean enter) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void enterPSModeOnRate(boolean enter, int rate) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public boolean requestGameRefreshRate(String packageName, int rate) throws RemoteException {
            return false;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public boolean requestRefreshRateWithToken(boolean open, int rate, IBinder token) throws RemoteException {
            return false;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public boolean getGameList(Bundle outBundle) throws RemoteException {
            return false;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public String getDisableOverrideViewList(String key) throws RemoteException {
            return null;
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void enterPSModeOnRateWithToken(boolean open, int rate, IBinder token) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void overrideWindowRefreshRate(IBinder window, int refreshRateId) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void keepHighRefreshRate(int status) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void updateFpsWhenDcChange(boolean enter) throws RemoteException {
        }

        @Override // com.oplus.screenmode.IOplusScreenMode
        public void setMemcWorkStatus(boolean memc) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusScreenMode {
        static final int TRANSACTION_addCallback = 1;
        static final int TRANSACTION_enterDCAndLowBrightnessMode = 7;
        static final int TRANSACTION_enterPSMode = 9;
        static final int TRANSACTION_enterPSModeOnRate = 10;
        static final int TRANSACTION_enterPSModeOnRateWithToken = 15;
        static final int TRANSACTION_getDisableOverrideViewList = 14;
        static final int TRANSACTION_getGameList = 13;
        static final int TRANSACTION_isDisplayCompat = 8;
        static final int TRANSACTION_keepHighRefreshRate = 17;
        static final int TRANSACTION_overrideWindowRefreshRate = 16;
        static final int TRANSACTION_remove = 2;
        static final int TRANSACTION_requestGameRefreshRate = 11;
        static final int TRANSACTION_requestRefreshRate = 4;
        static final int TRANSACTION_requestRefreshRateWithToken = 12;
        static final int TRANSACTION_setClientRefreshRate = 3;
        static final int TRANSACTION_setHighTemperatureStatus = 6;
        static final int TRANSACTION_setMemcWorkStatus = 19;
        static final int TRANSACTION_supportDisplayCompat = 5;
        static final int TRANSACTION_updateFpsWhenDcChange = 18;

        public Stub() {
            attachInterface(this, IOplusScreenMode.DESCRIPTOR);
        }

        public static IOplusScreenMode asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusScreenMode.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusScreenMode)) {
                return new Proxy(obj);
            }
            return (IOplusScreenMode) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addCallback";
                case 2:
                    return "remove";
                case 3:
                    return "setClientRefreshRate";
                case 4:
                    return "requestRefreshRate";
                case 5:
                    return "supportDisplayCompat";
                case 6:
                    return "setHighTemperatureStatus";
                case 7:
                    return "enterDCAndLowBrightnessMode";
                case 8:
                    return "isDisplayCompat";
                case 9:
                    return "enterPSMode";
                case 10:
                    return "enterPSModeOnRate";
                case 11:
                    return "requestGameRefreshRate";
                case 12:
                    return "requestRefreshRateWithToken";
                case 13:
                    return "getGameList";
                case 14:
                    return "getDisableOverrideViewList";
                case 15:
                    return "enterPSModeOnRateWithToken";
                case 16:
                    return "overrideWindowRefreshRate";
                case 17:
                    return "keepHighRefreshRate";
                case 18:
                    return "updateFpsWhenDcChange";
                case 19:
                    return "setMemcWorkStatus";
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
                    reply.writeString(IOplusScreenMode.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg0 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            IOplusScreenModeCallback _arg1 = IOplusScreenModeCallback.Stub.asInterface(data.readStrongBinder());
                            addCallback(_arg02, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            IOplusScreenModeCallback _arg12 = IOplusScreenModeCallback.Stub.asInterface(data.readStrongBinder());
                            remove(_arg03, _arg12);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            int _arg13 = data.readInt();
                            setClientRefreshRate(_arg04, _arg13);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            int _arg14 = data.readInt();
                            boolean requestRefreshRate = requestRefreshRate(_arg0, _arg14);
                            reply.writeNoException();
                            reply.writeInt(requestRefreshRate ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg15 = data.readInt();
                            boolean supportDisplayCompat = supportDisplayCompat(_arg05, _arg15);
                            reply.writeNoException();
                            reply.writeInt(supportDisplayCompat ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg16 = data.readInt();
                            boolean highTemperatureStatus = setHighTemperatureStatus(_arg06, _arg16);
                            reply.writeNoException();
                            reply.writeInt(highTemperatureStatus ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            enterDCAndLowBrightnessMode(_arg0);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg17 = data.readInt();
                            boolean isDisplayCompat = isDisplayCompat(_arg07, _arg17);
                            reply.writeNoException();
                            reply.writeInt(isDisplayCompat ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            enterPSMode(_arg0);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            int _arg18 = data.readInt();
                            enterPSModeOnRate(_arg0, _arg18);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            String _arg08 = data.readString();
                            int _arg19 = data.readInt();
                            boolean requestGameRefreshRate = requestGameRefreshRate(_arg08, _arg19);
                            reply.writeNoException();
                            reply.writeInt(requestGameRefreshRate ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            int _arg110 = data.readInt();
                            IBinder _arg2 = data.readStrongBinder();
                            boolean requestRefreshRateWithToken = requestRefreshRateWithToken(_arg0, _arg110, _arg2);
                            reply.writeNoException();
                            reply.writeInt(requestRefreshRateWithToken ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            Bundle _arg09 = new Bundle();
                            boolean gameList = getGameList(_arg09);
                            reply.writeNoException();
                            reply.writeInt(gameList ? 1 : 0);
                            reply.writeInt(1);
                            _arg09.writeToParcel(reply, 1);
                            return true;
                        case 14:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            String _result = getDisableOverrideViewList(data.readString());
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 15:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            int _arg111 = data.readInt();
                            IBinder _arg22 = data.readStrongBinder();
                            enterPSModeOnRateWithToken(_arg0, _arg111, _arg22);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            IBinder _arg010 = data.readStrongBinder();
                            int _arg112 = data.readInt();
                            overrideWindowRefreshRate(_arg010, _arg112);
                            return true;
                        case 17:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            keepHighRefreshRate(data.readInt());
                            return true;
                        case 18:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            updateFpsWhenDcChange(_arg0);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(IOplusScreenMode.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            setMemcWorkStatus(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusScreenMode {
            public static IOplusScreenMode sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusScreenMode.DESCRIPTOR;
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void addCallback(int index, IOplusScreenModeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(index);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addCallback(index, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void remove(int index, IOplusScreenModeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(index);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().remove(index, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void setClientRefreshRate(IBinder token, int rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(rate);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setClientRefreshRate(token, rate);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public boolean requestRefreshRate(boolean open, int rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(open ? 1 : 0);
                    _data.writeInt(rate);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestRefreshRate(open, rate);
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

            @Override // com.oplus.screenmode.IOplusScreenMode
            public boolean supportDisplayCompat(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supportDisplayCompat(pkg, uid);
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

            @Override // com.oplus.screenmode.IOplusScreenMode
            public boolean setHighTemperatureStatus(int status, int rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(status);
                    _data.writeInt(rate);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setHighTemperatureStatus(status, rate);
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

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void enterDCAndLowBrightnessMode(boolean enter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(enter ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enterDCAndLowBrightnessMode(enter);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public boolean isDisplayCompat(String packageName, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDisplayCompat(packageName, uid);
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

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void enterPSMode(boolean enter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(enter ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enterPSMode(enter);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void enterPSModeOnRate(boolean enter, int rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(enter ? 1 : 0);
                    _data.writeInt(rate);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enterPSModeOnRate(enter, rate);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public boolean requestGameRefreshRate(String packageName, int rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(rate);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestGameRefreshRate(packageName, rate);
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

            @Override // com.oplus.screenmode.IOplusScreenMode
            public boolean requestRefreshRateWithToken(boolean open, int rate, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(open ? 1 : 0);
                    _data.writeInt(rate);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestRefreshRateWithToken(open, rate, token);
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

            @Override // com.oplus.screenmode.IOplusScreenMode
            public boolean getGameList(Bundle outBundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGameList(outBundle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    if (_reply.readInt() != 0) {
                        outBundle.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public String getDisableOverrideViewList(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisableOverrideViewList(key);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void enterPSModeOnRateWithToken(boolean open, int rate, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(open ? 1 : 0);
                    _data.writeInt(rate);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enterPSModeOnRateWithToken(open, rate, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void overrideWindowRefreshRate(IBinder window, int refreshRateId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeStrongBinder(window);
                    _data.writeInt(refreshRateId);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overrideWindowRefreshRate(window, refreshRateId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void keepHighRefreshRate(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().keepHighRefreshRate(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void updateFpsWhenDcChange(boolean enter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(enter ? 1 : 0);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateFpsWhenDcChange(enter);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.screenmode.IOplusScreenMode
            public void setMemcWorkStatus(boolean memc) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusScreenMode.DESCRIPTOR);
                    _data.writeInt(memc ? 1 : 0);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMemcWorkStatus(memc);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusScreenMode impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusScreenMode getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
