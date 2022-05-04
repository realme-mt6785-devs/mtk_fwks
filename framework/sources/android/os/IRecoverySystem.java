package android.os;

import android.content.IntentSender;
import android.os.IRecoverySystemProgressListener;
/* loaded from: classes2.dex */
public interface IRecoverySystem extends IInterface {
    boolean allocateSpaceForUpdate(String str) throws RemoteException;

    boolean clearBcb() throws RemoteException;

    boolean clearLskf(String str) throws RemoteException;

    boolean isLskfCaptured(String str) throws RemoteException;

    void rebootRecoveryWithCommand(String str) throws RemoteException;

    int rebootWithLskf(String str, String str2, boolean z) throws RemoteException;

    int rebootWithLskfAssumeSlotSwitch(String str, String str2) throws RemoteException;

    boolean requestLskf(String str, IntentSender intentSender) throws RemoteException;

    boolean setupBcb(String str) throws RemoteException;

    boolean uncrypt(String str, IRecoverySystemProgressListener iRecoverySystemProgressListener) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IRecoverySystem {
        @Override // android.os.IRecoverySystem
        public boolean allocateSpaceForUpdate(String packageFilePath) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean uncrypt(String packageFile, IRecoverySystemProgressListener listener) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean setupBcb(String command) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean clearBcb() throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public void rebootRecoveryWithCommand(String command) throws RemoteException {
        }

        @Override // android.os.IRecoverySystem
        public boolean requestLskf(String packageName, IntentSender sender) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean clearLskf(String packageName) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public boolean isLskfCaptured(String packageName) throws RemoteException {
            return false;
        }

        @Override // android.os.IRecoverySystem
        public int rebootWithLskfAssumeSlotSwitch(String packageName, String reason) throws RemoteException {
            return 0;
        }

        @Override // android.os.IRecoverySystem
        public int rebootWithLskf(String packageName, String reason, boolean slotSwitch) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IRecoverySystem {
        public static final String DESCRIPTOR = "android.os.IRecoverySystem";
        static final int TRANSACTION_allocateSpaceForUpdate = 1;
        static final int TRANSACTION_clearBcb = 4;
        static final int TRANSACTION_clearLskf = 7;
        static final int TRANSACTION_isLskfCaptured = 8;
        static final int TRANSACTION_rebootRecoveryWithCommand = 5;
        static final int TRANSACTION_rebootWithLskf = 10;
        static final int TRANSACTION_rebootWithLskfAssumeSlotSwitch = 9;
        static final int TRANSACTION_requestLskf = 6;
        static final int TRANSACTION_setupBcb = 3;
        static final int TRANSACTION_uncrypt = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecoverySystem asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRecoverySystem)) {
                return new Proxy(obj);
            }
            return (IRecoverySystem) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "allocateSpaceForUpdate";
                case 2:
                    return "uncrypt";
                case 3:
                    return "setupBcb";
                case 4:
                    return "clearBcb";
                case 5:
                    return "rebootRecoveryWithCommand";
                case 6:
                    return "requestLskf";
                case 7:
                    return "clearLskf";
                case 8:
                    return "isLskfCaptured";
                case 9:
                    return "rebootWithLskfAssumeSlotSwitch";
                case 10:
                    return "rebootWithLskf";
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
            IntentSender _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            boolean allocateSpaceForUpdate = allocateSpaceForUpdate(_arg0);
                            reply.writeNoException();
                            reply.writeInt(allocateSpaceForUpdate ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            IRecoverySystemProgressListener _arg12 = IRecoverySystemProgressListener.Stub.asInterface(data.readStrongBinder());
                            boolean uncrypt = uncrypt(_arg02, _arg12);
                            reply.writeNoException();
                            reply.writeInt(uncrypt ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            boolean z = setupBcb(_arg03);
                            reply.writeNoException();
                            reply.writeInt(z ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            boolean clearBcb = clearBcb();
                            reply.writeNoException();
                            reply.writeInt(clearBcb ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            rebootRecoveryWithCommand(_arg04);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            boolean requestLskf = requestLskf(_arg05, _arg1);
                            reply.writeNoException();
                            reply.writeInt(requestLskf ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            boolean clearLskf = clearLskf(_arg06);
                            reply.writeNoException();
                            reply.writeInt(clearLskf ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            boolean isLskfCaptured = isLskfCaptured(_arg07);
                            reply.writeNoException();
                            reply.writeInt(isLskfCaptured ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            String _arg13 = data.readString();
                            int _result = rebootWithLskfAssumeSlotSwitch(_arg08, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _arg14 = data.readString();
                            boolean _arg2 = data.readInt() != 0;
                            int _result2 = rebootWithLskf(_arg09, _arg14, _arg2);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IRecoverySystem {
            public static IRecoverySystem sDefaultImpl;
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

            @Override // android.os.IRecoverySystem
            public boolean allocateSpaceForUpdate(String packageFilePath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageFilePath);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().allocateSpaceForUpdate(packageFilePath);
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

            @Override // android.os.IRecoverySystem
            public boolean uncrypt(String packageFile, IRecoverySystemProgressListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageFile);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().uncrypt(packageFile, listener);
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

            @Override // android.os.IRecoverySystem
            public boolean setupBcb(String command) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(command);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setupBcb(command);
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

            @Override // android.os.IRecoverySystem
            public boolean clearBcb() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearBcb();
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

            @Override // android.os.IRecoverySystem
            public void rebootRecoveryWithCommand(String command) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(command);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().rebootRecoveryWithCommand(command);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public boolean requestLskf(String packageName, IntentSender sender) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = true;
                    if (sender != null) {
                        _data.writeInt(1);
                        sender.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestLskf(packageName, sender);
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

            @Override // android.os.IRecoverySystem
            public boolean clearLskf(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearLskf(packageName);
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

            @Override // android.os.IRecoverySystem
            public boolean isLskfCaptured(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLskfCaptured(packageName);
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

            @Override // android.os.IRecoverySystem
            public int rebootWithLskfAssumeSlotSwitch(String packageName, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().rebootWithLskfAssumeSlotSwitch(packageName, reason);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IRecoverySystem
            public int rebootWithLskf(String packageName, String reason, boolean slotSwitch) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(reason);
                    _data.writeInt(slotSwitch ? 1 : 0);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().rebootWithLskf(packageName, reason, slotSwitch);
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

        public static boolean setDefaultImpl(IRecoverySystem impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRecoverySystem getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
