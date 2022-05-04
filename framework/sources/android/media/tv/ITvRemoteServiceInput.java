package android.media.tv;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ITvRemoteServiceInput extends IInterface {
    void clearInputBridge(IBinder iBinder) throws RemoteException;

    void closeInputBridge(IBinder iBinder) throws RemoteException;

    void openGamepadBridge(IBinder iBinder, String str) throws RemoteException;

    void openInputBridge(IBinder iBinder, String str, int i, int i2, int i3) throws RemoteException;

    void sendGamepadAxisValue(IBinder iBinder, int i, float f) throws RemoteException;

    void sendGamepadKeyDown(IBinder iBinder, int i) throws RemoteException;

    void sendGamepadKeyUp(IBinder iBinder, int i) throws RemoteException;

    void sendKeyDown(IBinder iBinder, int i) throws RemoteException;

    void sendKeyUp(IBinder iBinder, int i) throws RemoteException;

    void sendPointerDown(IBinder iBinder, int i, int i2, int i3) throws RemoteException;

    void sendPointerSync(IBinder iBinder) throws RemoteException;

    void sendPointerUp(IBinder iBinder, int i) throws RemoteException;

    void sendTimestamp(IBinder iBinder, long j) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ITvRemoteServiceInput {
        @Override // android.media.tv.ITvRemoteServiceInput
        public void openInputBridge(IBinder token, String name, int width, int height, int maxPointers) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void closeInputBridge(IBinder token) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void clearInputBridge(IBinder token) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendTimestamp(IBinder token, long timestamp) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendKeyDown(IBinder token, int keyCode) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendKeyUp(IBinder token, int keyCode) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendPointerDown(IBinder token, int pointerId, int x, int y) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendPointerUp(IBinder token, int pointerId) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendPointerSync(IBinder token) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void openGamepadBridge(IBinder token, String name) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendGamepadKeyDown(IBinder token, int keyCode) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendGamepadKeyUp(IBinder token, int keyCode) throws RemoteException {
        }

        @Override // android.media.tv.ITvRemoteServiceInput
        public void sendGamepadAxisValue(IBinder token, int axis, float value) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ITvRemoteServiceInput {
        public static final String DESCRIPTOR = "android.media.tv.ITvRemoteServiceInput";
        static final int TRANSACTION_clearInputBridge = 3;
        static final int TRANSACTION_closeInputBridge = 2;
        static final int TRANSACTION_openGamepadBridge = 10;
        static final int TRANSACTION_openInputBridge = 1;
        static final int TRANSACTION_sendGamepadAxisValue = 13;
        static final int TRANSACTION_sendGamepadKeyDown = 11;
        static final int TRANSACTION_sendGamepadKeyUp = 12;
        static final int TRANSACTION_sendKeyDown = 5;
        static final int TRANSACTION_sendKeyUp = 6;
        static final int TRANSACTION_sendPointerDown = 7;
        static final int TRANSACTION_sendPointerSync = 9;
        static final int TRANSACTION_sendPointerUp = 8;
        static final int TRANSACTION_sendTimestamp = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITvRemoteServiceInput asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITvRemoteServiceInput)) {
                return new Proxy(obj);
            }
            return (ITvRemoteServiceInput) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "openInputBridge";
                case 2:
                    return "closeInputBridge";
                case 3:
                    return "clearInputBridge";
                case 4:
                    return "sendTimestamp";
                case 5:
                    return "sendKeyDown";
                case 6:
                    return "sendKeyUp";
                case 7:
                    return "sendPointerDown";
                case 8:
                    return "sendPointerUp";
                case 9:
                    return "sendPointerSync";
                case 10:
                    return "openGamepadBridge";
                case 11:
                    return "sendGamepadKeyDown";
                case 12:
                    return "sendGamepadKeyUp";
                case 13:
                    return "sendGamepadAxisValue";
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
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg0 = data.readStrongBinder();
                            String _arg1 = data.readString();
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            int _arg4 = data.readInt();
                            openInputBridge(_arg0, _arg1, _arg2, _arg3, _arg4);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            closeInputBridge(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            clearInputBridge(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            long _arg12 = data.readLong();
                            sendTimestamp(_arg04, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg05 = data.readStrongBinder();
                            int _arg13 = data.readInt();
                            sendKeyDown(_arg05, _arg13);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg06 = data.readStrongBinder();
                            int _arg14 = data.readInt();
                            sendKeyUp(_arg06, _arg14);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg07 = data.readStrongBinder();
                            int _arg15 = data.readInt();
                            int _arg22 = data.readInt();
                            int _arg32 = data.readInt();
                            sendPointerDown(_arg07, _arg15, _arg22, _arg32);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg08 = data.readStrongBinder();
                            int _arg16 = data.readInt();
                            sendPointerUp(_arg08, _arg16);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg09 = data.readStrongBinder();
                            sendPointerSync(_arg09);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg010 = data.readStrongBinder();
                            String _arg17 = data.readString();
                            openGamepadBridge(_arg010, _arg17);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg011 = data.readStrongBinder();
                            int _arg18 = data.readInt();
                            sendGamepadKeyDown(_arg011, _arg18);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg012 = data.readStrongBinder();
                            int _arg19 = data.readInt();
                            sendGamepadKeyUp(_arg012, _arg19);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg013 = data.readStrongBinder();
                            int _arg110 = data.readInt();
                            float _arg23 = data.readFloat();
                            sendGamepadAxisValue(_arg013, _arg110, _arg23);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ITvRemoteServiceInput {
            public static ITvRemoteServiceInput sDefaultImpl;
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

            @Override // android.media.tv.ITvRemoteServiceInput
            public void openInputBridge(IBinder token, String name, int width, int height, int maxPointers) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(name);
                    _data.writeInt(width);
                    _data.writeInt(height);
                    _data.writeInt(maxPointers);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openInputBridge(token, name, width, height, maxPointers);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void closeInputBridge(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeInputBridge(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void clearInputBridge(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearInputBridge(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendTimestamp(IBinder token, long timestamp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeLong(timestamp);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendTimestamp(token, timestamp);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendKeyDown(IBinder token, int keyCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(keyCode);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendKeyDown(token, keyCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendKeyUp(IBinder token, int keyCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(keyCode);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendKeyUp(token, keyCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendPointerDown(IBinder token, int pointerId, int x, int y) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(pointerId);
                    _data.writeInt(x);
                    _data.writeInt(y);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendPointerDown(token, pointerId, x, y);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendPointerUp(IBinder token, int pointerId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(pointerId);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendPointerUp(token, pointerId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendPointerSync(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendPointerSync(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void openGamepadBridge(IBinder token, String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openGamepadBridge(token, name);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendGamepadKeyDown(IBinder token, int keyCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(keyCode);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendGamepadKeyDown(token, keyCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendGamepadKeyUp(IBinder token, int keyCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(keyCode);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendGamepadKeyUp(token, keyCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.tv.ITvRemoteServiceInput
            public void sendGamepadAxisValue(IBinder token, int axis, float value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(axis);
                    _data.writeFloat(value);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendGamepadAxisValue(token, axis, value);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvRemoteServiceInput impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITvRemoteServiceInput getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
