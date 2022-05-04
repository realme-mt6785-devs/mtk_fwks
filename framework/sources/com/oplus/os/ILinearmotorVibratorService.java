package com.oplus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface ILinearmotorVibratorService extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.os.ILinearmotorVibratorService";

    void cancelVibrate(WaveformEffect waveformEffect, IBinder iBinder) throws RemoteException;

    int getSettingsNotificationEffectStrength() throws RemoteException;

    int getSettingsRingtoneEffectStrength() throws RemoteException;

    int getSettingsTouchEffectStrength() throws RemoteException;

    int getVibratorStatus() throws RemoteException;

    int getVibratorTouchStyle() throws RemoteException;

    void setVibratorStrength(int i) throws RemoteException;

    void setVibratorTouchStyle(int i) throws RemoteException;

    void vibrate(int i, String str, WaveformEffect waveformEffect, IBinder iBinder) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ILinearmotorVibratorService {
        @Override // com.oplus.os.ILinearmotorVibratorService
        public void vibrate(int uid, String opPkg, WaveformEffect we, IBinder token) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public void cancelVibrate(WaveformEffect we, IBinder token) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getVibratorStatus() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public void setVibratorStrength(int strength) throws RemoteException {
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getSettingsTouchEffectStrength() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getSettingsRingtoneEffectStrength() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getSettingsNotificationEffectStrength() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public int getVibratorTouchStyle() throws RemoteException {
            return 0;
        }

        @Override // com.oplus.os.ILinearmotorVibratorService
        public void setVibratorTouchStyle(int style) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ILinearmotorVibratorService {
        static final int TRANSACTION_cancelVibrate = 2;
        static final int TRANSACTION_getSettingsNotificationEffectStrength = 7;
        static final int TRANSACTION_getSettingsRingtoneEffectStrength = 6;
        static final int TRANSACTION_getSettingsTouchEffectStrength = 5;
        static final int TRANSACTION_getVibratorStatus = 3;
        static final int TRANSACTION_getVibratorTouchStyle = 8;
        static final int TRANSACTION_setVibratorStrength = 4;
        static final int TRANSACTION_setVibratorTouchStyle = 9;
        static final int TRANSACTION_vibrate = 1;

        public Stub() {
            attachInterface(this, ILinearmotorVibratorService.DESCRIPTOR);
        }

        public static ILinearmotorVibratorService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ILinearmotorVibratorService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ILinearmotorVibratorService)) {
                return new Proxy(obj);
            }
            return (ILinearmotorVibratorService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "vibrate";
                case 2:
                    return "cancelVibrate";
                case 3:
                    return "getVibratorStatus";
                case 4:
                    return "setVibratorStrength";
                case 5:
                    return "getSettingsTouchEffectStrength";
                case 6:
                    return "getSettingsRingtoneEffectStrength";
                case 7:
                    return "getSettingsNotificationEffectStrength";
                case 8:
                    return "getVibratorTouchStyle";
                case 9:
                    return "setVibratorTouchStyle";
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
            WaveformEffect _arg2;
            WaveformEffect _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ILinearmotorVibratorService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _arg1 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = WaveformEffect.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            IBinder _arg3 = data.readStrongBinder();
                            vibrate(_arg02, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = WaveformEffect.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IBinder _arg12 = data.readStrongBinder();
                            cancelVibrate(_arg0, _arg12);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _result = getVibratorStatus();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 4:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            setVibratorStrength(_arg03);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _result2 = getSettingsTouchEffectStrength();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 6:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _result3 = getSettingsRingtoneEffectStrength();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 7:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _result4 = getSettingsNotificationEffectStrength();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 8:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _result5 = getVibratorTouchStyle();
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 9:
                            data.enforceInterface(ILinearmotorVibratorService.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            setVibratorTouchStyle(_arg04);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ILinearmotorVibratorService {
            public static ILinearmotorVibratorService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ILinearmotorVibratorService.DESCRIPTOR;
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void vibrate(int uid, String opPkg, WaveformEffect we, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeString(opPkg);
                    if (we != null) {
                        _data.writeInt(1);
                        we.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().vibrate(uid, opPkg, we, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void cancelVibrate(WaveformEffect we, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    if (we != null) {
                        _data.writeInt(1);
                        we.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelVibrate(we, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getVibratorStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVibratorStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void setVibratorStrength(int strength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeInt(strength);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVibratorStrength(strength);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getSettingsTouchEffectStrength() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsTouchEffectStrength();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getSettingsRingtoneEffectStrength() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsRingtoneEffectStrength();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getSettingsNotificationEffectStrength() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsNotificationEffectStrength();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public int getVibratorTouchStyle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVibratorTouchStyle();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.os.ILinearmotorVibratorService
            public void setVibratorTouchStyle(int style) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILinearmotorVibratorService.DESCRIPTOR);
                    _data.writeInt(style);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVibratorTouchStyle(style);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILinearmotorVibratorService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILinearmotorVibratorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
