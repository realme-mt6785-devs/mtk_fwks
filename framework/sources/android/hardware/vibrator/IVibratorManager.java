package android.hardware.vibrator;

import android.hardware.vibrator.IVibrator;
import android.hardware.vibrator.IVibratorCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IVibratorManager extends IInterface {
    public static final int CAP_MIXED_TRIGGER_COMPOSE = 64;
    public static final int CAP_MIXED_TRIGGER_ON = 16;
    public static final int CAP_MIXED_TRIGGER_PERFORM = 32;
    public static final int CAP_PREPARE_COMPOSE = 8;
    public static final int CAP_PREPARE_ON = 2;
    public static final int CAP_PREPARE_PERFORM = 4;
    public static final int CAP_SYNC = 1;
    public static final int CAP_TRIGGER_CALLBACK = 128;
    public static final String DESCRIPTOR = "android$hardware$vibrator$IVibratorManager".replace('$', '.');
    public static final String HASH = "ea8742d6993e1a82917da38b9938e537aa7fcb54";
    public static final int VERSION = 2;

    void cancelSynced() throws RemoteException;

    int getCapabilities() throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    IVibrator getVibrator(int i) throws RemoteException;

    int[] getVibratorIds() throws RemoteException;

    void prepareSynced(int[] iArr) throws RemoteException;

    void triggerSynced(IVibratorCallback iVibratorCallback) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IVibratorManager {
        @Override // android.hardware.vibrator.IVibratorManager
        public int getCapabilities() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibratorManager
        public int[] getVibratorIds() throws RemoteException {
            return null;
        }

        @Override // android.hardware.vibrator.IVibratorManager
        public IVibrator getVibrator(int vibratorId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.vibrator.IVibratorManager
        public void prepareSynced(int[] vibratorIds) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibratorManager
        public void triggerSynced(IVibratorCallback callback) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibratorManager
        public void cancelSynced() throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibratorManager
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibratorManager
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVibratorManager {
        static final int TRANSACTION_cancelSynced = 6;
        static final int TRANSACTION_getCapabilities = 1;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_getVibrator = 3;
        static final int TRANSACTION_getVibratorIds = 2;
        static final int TRANSACTION_prepareSynced = 4;
        static final int TRANSACTION_triggerSynced = 5;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IVibratorManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVibratorManager)) {
                return new Proxy(obj);
            }
            return (IVibratorManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case 16777214:
                    data.enforceInterface(descriptor);
                    reply.writeNoException();
                    reply.writeString(getInterfaceHash());
                    return true;
                case 16777215:
                    data.enforceInterface(descriptor);
                    reply.writeNoException();
                    reply.writeInt(getInterfaceVersion());
                    return true;
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            int _result = getCapabilities();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            int[] _result2 = getVibratorIds();
                            reply.writeNoException();
                            reply.writeIntArray(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg0 = data.readInt();
                            IVibrator _result3 = getVibrator(_arg0);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result3 != null ? _result3.asBinder() : null);
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            int[] _arg02 = data.createIntArray();
                            prepareSynced(_arg02);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            IVibratorCallback _arg03 = IVibratorCallback.Stub.asInterface(data.readStrongBinder());
                            triggerSynced(_arg03);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            cancelSynced();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVibratorManager {
            public static IVibratorManager sDefaultImpl;
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public int getCapabilities() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCapabilities();
                    } else {
                        throw new RemoteException("Method getCapabilities is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public int[] getVibratorIds() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int[] _result = _reply.createIntArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVibratorIds();
                    } else {
                        throw new RemoteException("Method getVibratorIds is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public IVibrator getVibrator(int vibratorId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(vibratorId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        IVibrator _result = IVibrator.Stub.asInterface(_reply.readStrongBinder());
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVibrator(vibratorId);
                    } else {
                        throw new RemoteException("Method getVibrator is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public void prepareSynced(int[] vibratorIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeIntArray(vibratorIds);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepareSynced(vibratorIds);
                    } else {
                        throw new RemoteException("Method prepareSynced is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public void triggerSynced(IVibratorCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().triggerSynced(callback);
                    } else {
                        throw new RemoteException("Method triggerSynced is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public void cancelSynced() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelSynced();
                    } else {
                        throw new RemoteException("Method cancelSynced is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        boolean _status = this.mRemote.transact(16777215, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceVersion();
                        }
                        reply.readException();
                        this.mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedVersion;
            }

            @Override // android.hardware.vibrator.IVibratorManager
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(this.mCachedHash)) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16777214, data, reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        reply.readException();
                        this.mCachedHash = reply.readString();
                        reply.recycle();
                        data.recycle();
                    } else {
                        String interfaceHash = Stub.getDefaultImpl().getInterfaceHash();
                        reply.recycle();
                        data.recycle();
                        return interfaceHash;
                    }
                }
                return this.mCachedHash;
            }
        }

        public static boolean setDefaultImpl(IVibratorManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVibratorManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
