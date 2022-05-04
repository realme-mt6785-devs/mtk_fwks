package com.oplus.vrr;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes4.dex */
public interface IOPlusRefreshRate extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.vrr.IOPlusRefreshRate";

    int findDisplayModeIdByPolicy(int i, int i2, int i3) throws RemoteException;

    List<String> getList(int i) throws RemoteException;

    int getModeType(int i) throws RemoteException;

    float getPreferredFrameRate(String str, String str2) throws RemoteException;

    int getRefreshRatePolicy(float f) throws RemoteException;

    boolean hasFlickerRisk() throws RemoteException;

    boolean isGameAccelerationScene() throws RemoteException;

    boolean isOAMode() throws RemoteException;

    boolean isWhiteListGame(String str) throws RemoteException;

    void notifyBrightnessChange(float f) throws RemoteException;

    void notifyNitsChange(float f) throws RemoteException;

    void screenStateChange(int i) throws RemoteException;

    boolean setDisplayFrameRateControl(float f, String str, int i, int i2) throws RemoteException;

    void setExternalRefreshRateStatus(int i) throws RemoteException;

    boolean setFrameRate(float f, String str, String str2, int i) throws RemoteException;

    boolean setFrameRateTargetControl(float f, String str, boolean z, String str2) throws RemoteException;

    void setLowFreqVideo(boolean z) throws RemoteException;

    void setRefreshRatePolicy(int i, float f, int i2, boolean z) throws RemoteException;

    void setTgpaGameData(Bundle bundle) throws RemoteException;

    void updateAccelerationPkgName(String str, int i, int i2) throws RemoteException;

    void updateDisplayModes(long j) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOPlusRefreshRate {
        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void setExternalRefreshRateStatus(int status) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void updateDisplayModes(long physicalDisplayId) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public int findDisplayModeIdByPolicy(int policy, int displayId, int baseModeId) throws RemoteException {
            return 0;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void screenStateChange(int state) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void notifyNitsChange(float nits) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void notifyBrightnessChange(float brightness) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void setRefreshRatePolicy(int displayId, float rate, int policy, boolean statusOn) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public int getRefreshRatePolicy(float rate) throws RemoteException {
            return 0;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public boolean isOAMode() throws RemoteException {
            return false;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public int getModeType(int modeId) throws RemoteException {
            return 0;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void setLowFreqVideo(boolean enable) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public float getPreferredFrameRate(String pkgName, String winName) throws RemoteException {
            return 0.0f;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public boolean hasFlickerRisk() throws RemoteException {
            return false;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void updateAccelerationPkgName(String pkgName, int targetFps, int currentFps) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public void setTgpaGameData(Bundle bundle) throws RemoteException {
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public boolean isGameAccelerationScene() throws RemoteException {
            return false;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public boolean isWhiteListGame(String pkgName) throws RemoteException {
            return false;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public List<String> getList(int listType) throws RemoteException {
            return null;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public boolean setFrameRate(float frameRate, String windowTile, String pkgName, int pid) throws RemoteException {
            return false;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public boolean setFrameRateTargetControl(float frameRate, String pkgName, boolean strictMode, String keyDescription) throws RemoteException {
            return false;
        }

        @Override // com.oplus.vrr.IOPlusRefreshRate
        public boolean setDisplayFrameRateControl(float frameRate, String displayName, int layerStack, int flags) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOPlusRefreshRate {
        static final int TRANSACTION_findDisplayModeIdByPolicy = 3;
        static final int TRANSACTION_getList = 18;
        static final int TRANSACTION_getModeType = 10;
        static final int TRANSACTION_getPreferredFrameRate = 12;
        static final int TRANSACTION_getRefreshRatePolicy = 8;
        static final int TRANSACTION_hasFlickerRisk = 13;
        static final int TRANSACTION_isGameAccelerationScene = 16;
        static final int TRANSACTION_isOAMode = 9;
        static final int TRANSACTION_isWhiteListGame = 17;
        static final int TRANSACTION_notifyBrightnessChange = 6;
        static final int TRANSACTION_notifyNitsChange = 5;
        static final int TRANSACTION_screenStateChange = 4;
        static final int TRANSACTION_setDisplayFrameRateControl = 21;
        static final int TRANSACTION_setExternalRefreshRateStatus = 1;
        static final int TRANSACTION_setFrameRate = 19;
        static final int TRANSACTION_setFrameRateTargetControl = 20;
        static final int TRANSACTION_setLowFreqVideo = 11;
        static final int TRANSACTION_setRefreshRatePolicy = 7;
        static final int TRANSACTION_setTgpaGameData = 15;
        static final int TRANSACTION_updateAccelerationPkgName = 14;
        static final int TRANSACTION_updateDisplayModes = 2;

        public Stub() {
            attachInterface(this, IOPlusRefreshRate.DESCRIPTOR);
        }

        public static IOPlusRefreshRate asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOPlusRefreshRate.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOPlusRefreshRate)) {
                return new Proxy(obj);
            }
            return (IOPlusRefreshRate) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setExternalRefreshRateStatus";
                case 2:
                    return "updateDisplayModes";
                case 3:
                    return "findDisplayModeIdByPolicy";
                case 4:
                    return "screenStateChange";
                case 5:
                    return "notifyNitsChange";
                case 6:
                    return "notifyBrightnessChange";
                case 7:
                    return "setRefreshRatePolicy";
                case 8:
                    return "getRefreshRatePolicy";
                case 9:
                    return "isOAMode";
                case 10:
                    return "getModeType";
                case 11:
                    return "setLowFreqVideo";
                case 12:
                    return "getPreferredFrameRate";
                case 13:
                    return "hasFlickerRisk";
                case 14:
                    return "updateAccelerationPkgName";
                case 15:
                    return "setTgpaGameData";
                case 16:
                    return "isGameAccelerationScene";
                case 17:
                    return "isWhiteListGame";
                case 18:
                    return "getList";
                case 19:
                    return "setFrameRate";
                case 20:
                    return "setFrameRateTargetControl";
                case 21:
                    return "setDisplayFrameRateControl";
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
            Bundle _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOPlusRefreshRate.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg2 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            setExternalRefreshRateStatus(_arg02);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            long _arg03 = data.readLong();
                            updateDisplayModes(_arg03);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg1 = data.readInt();
                            int _result = findDisplayModeIdByPolicy(_arg04, _arg1, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 4:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            screenStateChange(_arg05);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            float _arg06 = data.readFloat();
                            notifyNitsChange(_arg06);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            float _arg07 = data.readFloat();
                            notifyBrightnessChange(_arg07);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            float _arg12 = data.readFloat();
                            int _arg22 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            setRefreshRatePolicy(_arg08, _arg12, _arg22, _arg2);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            float _arg09 = data.readFloat();
                            int _result2 = getRefreshRatePolicy(_arg09);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 9:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            boolean isOAMode = isOAMode();
                            reply.writeNoException();
                            reply.writeInt(isOAMode ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _result3 = getModeType(_arg010);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 11:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            setLowFreqVideo(_arg2);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            String _arg011 = data.readString();
                            String _arg13 = data.readString();
                            float _result4 = getPreferredFrameRate(_arg011, _arg13);
                            reply.writeNoException();
                            reply.writeFloat(_result4);
                            return true;
                        case 13:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            boolean hasFlickerRisk = hasFlickerRisk();
                            reply.writeNoException();
                            reply.writeInt(hasFlickerRisk ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            String _arg012 = data.readString();
                            int _arg14 = data.readInt();
                            updateAccelerationPkgName(_arg012, _arg14, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            setTgpaGameData(_arg0);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            boolean isGameAccelerationScene = isGameAccelerationScene();
                            reply.writeNoException();
                            reply.writeInt(isGameAccelerationScene ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            String _arg013 = data.readString();
                            boolean isWhiteListGame = isWhiteListGame(_arg013);
                            reply.writeNoException();
                            reply.writeInt(isWhiteListGame ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            List<String> _result5 = getList(_arg014);
                            reply.writeNoException();
                            reply.writeStringList(_result5);
                            return true;
                        case 19:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            float _arg015 = data.readFloat();
                            String _arg15 = data.readString();
                            String _arg23 = data.readString();
                            int _arg3 = data.readInt();
                            boolean frameRate = setFrameRate(_arg015, _arg15, _arg23, _arg3);
                            reply.writeNoException();
                            reply.writeInt(frameRate ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            float _arg016 = data.readFloat();
                            String _arg16 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            }
                            String _arg32 = data.readString();
                            boolean frameRateTargetControl = setFrameRateTargetControl(_arg016, _arg16, _arg2, _arg32);
                            reply.writeNoException();
                            reply.writeInt(frameRateTargetControl ? 1 : 0);
                            return true;
                        case 21:
                            data.enforceInterface(IOPlusRefreshRate.DESCRIPTOR);
                            float _arg017 = data.readFloat();
                            String _arg17 = data.readString();
                            int _arg24 = data.readInt();
                            int _arg33 = data.readInt();
                            boolean displayFrameRateControl = setDisplayFrameRateControl(_arg017, _arg17, _arg24, _arg33);
                            reply.writeNoException();
                            reply.writeInt(displayFrameRateControl ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOPlusRefreshRate {
            public static IOPlusRefreshRate sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOPlusRefreshRate.DESCRIPTOR;
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void setExternalRefreshRateStatus(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setExternalRefreshRateStatus(status);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void updateDisplayModes(long physicalDisplayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeLong(physicalDisplayId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateDisplayModes(physicalDisplayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public int findDisplayModeIdByPolicy(int policy, int displayId, int baseModeId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeInt(policy);
                    _data.writeInt(displayId);
                    _data.writeInt(baseModeId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findDisplayModeIdByPolicy(policy, displayId, baseModeId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void screenStateChange(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().screenStateChange(state);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void notifyNitsChange(float nits) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeFloat(nits);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyNitsChange(nits);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void notifyBrightnessChange(float brightness) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeFloat(brightness);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyBrightnessChange(brightness);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void setRefreshRatePolicy(int displayId, float rate, int policy, boolean statusOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeFloat(rate);
                    _data.writeInt(policy);
                    _data.writeInt(statusOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRefreshRatePolicy(displayId, rate, policy, statusOn);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public int getRefreshRatePolicy(float rate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeFloat(rate);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRefreshRatePolicy(rate);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public boolean isOAMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOAMode();
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

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public int getModeType(int modeId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeInt(modeId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModeType(modeId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void setLowFreqVideo(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLowFreqVideo(enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public float getPreferredFrameRate(String pkgName, String winName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeString(pkgName);
                    _data.writeString(winName);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredFrameRate(pkgName, winName);
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public boolean hasFlickerRisk() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasFlickerRisk();
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

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void updateAccelerationPkgName(String pkgName, int targetFps, int currentFps) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeString(pkgName);
                    _data.writeInt(targetFps);
                    _data.writeInt(currentFps);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateAccelerationPkgName(pkgName, targetFps, currentFps);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public void setTgpaGameData(Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTgpaGameData(bundle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public boolean isGameAccelerationScene() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGameAccelerationScene();
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

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public boolean isWhiteListGame(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWhiteListGame(pkgName);
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

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public List<String> getList(int listType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeInt(listType);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getList(listType);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public boolean setFrameRate(float frameRate, String windowTile, String pkgName, int pid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeFloat(frameRate);
                    _data.writeString(windowTile);
                    _data.writeString(pkgName);
                    _data.writeInt(pid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFrameRate(frameRate, windowTile, pkgName, pid);
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

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public boolean setFrameRateTargetControl(float frameRate, String pkgName, boolean strictMode, String keyDescription) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeFloat(frameRate);
                    _data.writeString(pkgName);
                    boolean _result = true;
                    _data.writeInt(strictMode ? 1 : 0);
                    _data.writeString(keyDescription);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFrameRateTargetControl(frameRate, pkgName, strictMode, keyDescription);
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

            @Override // com.oplus.vrr.IOPlusRefreshRate
            public boolean setDisplayFrameRateControl(float frameRate, String displayName, int layerStack, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOPlusRefreshRate.DESCRIPTOR);
                    _data.writeFloat(frameRate);
                    _data.writeString(displayName);
                    _data.writeInt(layerStack);
                    _data.writeInt(flags);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDisplayFrameRateControl(frameRate, displayName, layerStack, flags);
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

        public static boolean setDefaultImpl(IOPlusRefreshRate impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOPlusRefreshRate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
