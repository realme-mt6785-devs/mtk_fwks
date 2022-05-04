package android.hardware;

import android.hardware.ISensorPrivacyListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ISensorPrivacyManager extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.ISensorPrivacyManager";

    void addIndividualSensorPrivacyListener(int i, int i2, ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    void addSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    void addUserGlobalIndividualSensorPrivacyListener(int i, ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    boolean isIndividualSensorPrivacyEnabled(int i, int i2) throws RemoteException;

    boolean isSensorPrivacyEnabled() throws RemoteException;

    void removeIndividualSensorPrivacyListener(int i, ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    void removeSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    void removeUserGlobalIndividualSensorPrivacyListener(int i, ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    void setIndividualSensorPrivacy(int i, int i2, int i3, boolean z) throws RemoteException;

    void setIndividualSensorPrivacyForProfileGroup(int i, int i2, int i3, boolean z) throws RemoteException;

    void setSensorPrivacy(boolean z) throws RemoteException;

    void showSensorUseDialog(int i) throws RemoteException;

    boolean supportsSensorToggle(int i) throws RemoteException;

    void suppressIndividualSensorPrivacyReminders(int i, int i2, IBinder iBinder, boolean z) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISensorPrivacyManager {
        @Override // android.hardware.ISensorPrivacyManager
        public boolean supportsSensorToggle(int sensor) throws RemoteException {
            return false;
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void addSensorPrivacyListener(ISensorPrivacyListener listener) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void addIndividualSensorPrivacyListener(int userId, int sensor, ISensorPrivacyListener listener) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void removeSensorPrivacyListener(ISensorPrivacyListener listener) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void removeIndividualSensorPrivacyListener(int sensor, ISensorPrivacyListener listener) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public boolean isSensorPrivacyEnabled() throws RemoteException {
            return false;
        }

        @Override // android.hardware.ISensorPrivacyManager
        public boolean isIndividualSensorPrivacyEnabled(int userId, int sensor) throws RemoteException {
            return false;
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void setSensorPrivacy(boolean enable) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void setIndividualSensorPrivacy(int userId, int source, int sensor, boolean enable) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void setIndividualSensorPrivacyForProfileGroup(int userId, int source, int sensor, boolean enable) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void suppressIndividualSensorPrivacyReminders(int userId, int sensor, IBinder token, boolean suppress) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void addUserGlobalIndividualSensorPrivacyListener(int sensor, ISensorPrivacyListener listener) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void removeUserGlobalIndividualSensorPrivacyListener(int sensor, ISensorPrivacyListener listener) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void showSensorUseDialog(int sensor) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISensorPrivacyManager {
        static final int TRANSACTION_addIndividualSensorPrivacyListener = 3;
        static final int TRANSACTION_addSensorPrivacyListener = 2;
        static final int TRANSACTION_addUserGlobalIndividualSensorPrivacyListener = 12;
        static final int TRANSACTION_isIndividualSensorPrivacyEnabled = 7;
        static final int TRANSACTION_isSensorPrivacyEnabled = 6;
        static final int TRANSACTION_removeIndividualSensorPrivacyListener = 5;
        static final int TRANSACTION_removeSensorPrivacyListener = 4;
        static final int TRANSACTION_removeUserGlobalIndividualSensorPrivacyListener = 13;
        static final int TRANSACTION_setIndividualSensorPrivacy = 9;
        static final int TRANSACTION_setIndividualSensorPrivacyForProfileGroup = 10;
        static final int TRANSACTION_setSensorPrivacy = 8;
        static final int TRANSACTION_showSensorUseDialog = 14;
        static final int TRANSACTION_supportsSensorToggle = 1;
        static final int TRANSACTION_suppressIndividualSensorPrivacyReminders = 11;

        public Stub() {
            attachInterface(this, ISensorPrivacyManager.DESCRIPTOR);
        }

        public static ISensorPrivacyManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISensorPrivacyManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISensorPrivacyManager)) {
                return new Proxy(obj);
            }
            return (ISensorPrivacyManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "supportsSensorToggle";
                case 2:
                    return "addSensorPrivacyListener";
                case 3:
                    return "addIndividualSensorPrivacyListener";
                case 4:
                    return "removeSensorPrivacyListener";
                case 5:
                    return "removeIndividualSensorPrivacyListener";
                case 6:
                    return "isSensorPrivacyEnabled";
                case 7:
                    return "isIndividualSensorPrivacyEnabled";
                case 8:
                    return "setSensorPrivacy";
                case 9:
                    return "setIndividualSensorPrivacy";
                case 10:
                    return "setIndividualSensorPrivacyForProfileGroup";
                case 11:
                    return "suppressIndividualSensorPrivacyReminders";
                case 12:
                    return "addUserGlobalIndividualSensorPrivacyListener";
                case 13:
                    return "removeUserGlobalIndividualSensorPrivacyListener";
                case 14:
                    return "showSensorUseDialog";
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
                    reply.writeString(ISensorPrivacyManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg3 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            boolean supportsSensorToggle = supportsSensorToggle(_arg0);
                            reply.writeNoException();
                            reply.writeInt(supportsSensorToggle ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            ISensorPrivacyListener _arg02 = ISensorPrivacyListener.Stub.asInterface(data.readStrongBinder());
                            addSensorPrivacyListener(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _arg1 = data.readInt();
                            ISensorPrivacyListener _arg2 = ISensorPrivacyListener.Stub.asInterface(data.readStrongBinder());
                            addIndividualSensorPrivacyListener(_arg03, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            ISensorPrivacyListener _arg04 = ISensorPrivacyListener.Stub.asInterface(data.readStrongBinder());
                            removeSensorPrivacyListener(_arg04);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            ISensorPrivacyListener _arg12 = ISensorPrivacyListener.Stub.asInterface(data.readStrongBinder());
                            removeIndividualSensorPrivacyListener(_arg05, _arg12);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            boolean isSensorPrivacyEnabled = isSensorPrivacyEnabled();
                            reply.writeNoException();
                            reply.writeInt(isSensorPrivacyEnabled ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            int _arg13 = data.readInt();
                            boolean isIndividualSensorPrivacyEnabled = isIndividualSensorPrivacyEnabled(_arg06, _arg13);
                            reply.writeNoException();
                            reply.writeInt(isIndividualSensorPrivacyEnabled ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            }
                            setSensorPrivacy(_arg3);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg14 = data.readInt();
                            int _arg22 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            }
                            setIndividualSensorPrivacy(_arg07, _arg14, _arg22, _arg3);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg15 = data.readInt();
                            int _arg23 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            }
                            setIndividualSensorPrivacyForProfileGroup(_arg08, _arg15, _arg23, _arg3);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            int _arg16 = data.readInt();
                            IBinder _arg24 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            }
                            suppressIndividualSensorPrivacyReminders(_arg09, _arg16, _arg24, _arg3);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            ISensorPrivacyListener _arg17 = ISensorPrivacyListener.Stub.asInterface(data.readStrongBinder());
                            addUserGlobalIndividualSensorPrivacyListener(_arg010, _arg17);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            ISensorPrivacyListener _arg18 = ISensorPrivacyListener.Stub.asInterface(data.readStrongBinder());
                            removeUserGlobalIndividualSensorPrivacyListener(_arg011, _arg18);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(ISensorPrivacyManager.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            showSensorUseDialog(_arg012);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISensorPrivacyManager {
            public static ISensorPrivacyManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISensorPrivacyManager.DESCRIPTOR;
            }

            @Override // android.hardware.ISensorPrivacyManager
            public boolean supportsSensorToggle(int sensor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(sensor);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supportsSensorToggle(sensor);
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

            @Override // android.hardware.ISensorPrivacyManager
            public void addSensorPrivacyListener(ISensorPrivacyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addSensorPrivacyListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void addIndividualSensorPrivacyListener(int userId, int sensor, ISensorPrivacyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(sensor);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addIndividualSensorPrivacyListener(userId, sensor, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void removeSensorPrivacyListener(ISensorPrivacyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeSensorPrivacyListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void removeIndividualSensorPrivacyListener(int sensor, ISensorPrivacyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(sensor);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeIndividualSensorPrivacyListener(sensor, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public boolean isSensorPrivacyEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSensorPrivacyEnabled();
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

            @Override // android.hardware.ISensorPrivacyManager
            public boolean isIndividualSensorPrivacyEnabled(int userId, int sensor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(sensor);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isIndividualSensorPrivacyEnabled(userId, sensor);
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

            @Override // android.hardware.ISensorPrivacyManager
            public void setSensorPrivacy(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSensorPrivacy(enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void setIndividualSensorPrivacy(int userId, int source, int sensor, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(source);
                    _data.writeInt(sensor);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setIndividualSensorPrivacy(userId, source, sensor, enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void setIndividualSensorPrivacyForProfileGroup(int userId, int source, int sensor, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(source);
                    _data.writeInt(sensor);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setIndividualSensorPrivacyForProfileGroup(userId, source, sensor, enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void suppressIndividualSensorPrivacyReminders(int userId, int sensor, IBinder token, boolean suppress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(sensor);
                    _data.writeStrongBinder(token);
                    _data.writeInt(suppress ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().suppressIndividualSensorPrivacyReminders(userId, sensor, token, suppress);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void addUserGlobalIndividualSensorPrivacyListener(int sensor, ISensorPrivacyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(sensor);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addUserGlobalIndividualSensorPrivacyListener(sensor, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void removeUserGlobalIndividualSensorPrivacyListener(int sensor, ISensorPrivacyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(sensor);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeUserGlobalIndividualSensorPrivacyListener(sensor, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void showSensorUseDialog(int sensor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISensorPrivacyManager.DESCRIPTOR);
                    _data.writeInt(sensor);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().showSensorUseDialog(sensor);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISensorPrivacyManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISensorPrivacyManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
