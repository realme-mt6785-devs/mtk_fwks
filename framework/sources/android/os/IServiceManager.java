package android.os;

import android.os.IClientCallback;
import android.os.IServiceCallback;
/* loaded from: classes2.dex */
public interface IServiceManager extends IInterface {
    public static final String DESCRIPTOR = "android.os.IServiceManager";
    public static final int DUMP_FLAG_PRIORITY_ALL = 15;
    public static final int DUMP_FLAG_PRIORITY_CRITICAL = 1;
    public static final int DUMP_FLAG_PRIORITY_DEFAULT = 8;
    public static final int DUMP_FLAG_PRIORITY_HIGH = 2;
    public static final int DUMP_FLAG_PRIORITY_NORMAL = 4;
    public static final int DUMP_FLAG_PROTO = 16;

    void addService(String str, IBinder iBinder, boolean z, int i) throws RemoteException;

    IBinder checkService(String str) throws RemoteException;

    String[] getDeclaredInstances(String str) throws RemoteException;

    IBinder getService(String str) throws RemoteException;

    ServiceDebugInfo[] getServiceDebugInfo() throws RemoteException;

    boolean isDeclared(String str) throws RemoteException;

    String[] listServices(int i) throws RemoteException;

    void registerClientCallback(String str, IBinder iBinder, IClientCallback iClientCallback) throws RemoteException;

    void registerForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException;

    void tryUnregisterService(String str, IBinder iBinder) throws RemoteException;

    void unregisterForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException;

    String updatableViaApex(String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IServiceManager {
        @Override // android.os.IServiceManager
        public IBinder getService(String name) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public IBinder checkService(String name) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public void addService(String name, IBinder service, boolean allowIsolated, int dumpPriority) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public String[] listServices(int dumpPriority) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public void registerForNotifications(String name, IServiceCallback callback) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public void unregisterForNotifications(String name, IServiceCallback callback) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public boolean isDeclared(String name) throws RemoteException {
            return false;
        }

        @Override // android.os.IServiceManager
        public String[] getDeclaredInstances(String iface) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public String updatableViaApex(String name) throws RemoteException {
            return null;
        }

        @Override // android.os.IServiceManager
        public void registerClientCallback(String name, IBinder service, IClientCallback callback) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public void tryUnregisterService(String name, IBinder service) throws RemoteException {
        }

        @Override // android.os.IServiceManager
        public ServiceDebugInfo[] getServiceDebugInfo() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IServiceManager {
        static final int TRANSACTION_addService = 3;
        static final int TRANSACTION_checkService = 2;
        static final int TRANSACTION_getDeclaredInstances = 8;
        static final int TRANSACTION_getService = 1;
        static final int TRANSACTION_getServiceDebugInfo = 12;
        static final int TRANSACTION_isDeclared = 7;
        static final int TRANSACTION_listServices = 4;
        static final int TRANSACTION_registerClientCallback = 10;
        static final int TRANSACTION_registerForNotifications = 5;
        static final int TRANSACTION_tryUnregisterService = 11;
        static final int TRANSACTION_unregisterForNotifications = 6;
        static final int TRANSACTION_updatableViaApex = 9;

        public Stub() {
            attachInterface(this, IServiceManager.DESCRIPTOR);
        }

        public static IServiceManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IServiceManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IServiceManager)) {
                return new Proxy(obj);
            }
            return (IServiceManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getService";
                case 2:
                    return "checkService";
                case 3:
                    return "addService";
                case 4:
                    return "listServices";
                case 5:
                    return "registerForNotifications";
                case 6:
                    return "unregisterForNotifications";
                case 7:
                    return "isDeclared";
                case 8:
                    return "getDeclaredInstances";
                case 9:
                    return "updatableViaApex";
                case 10:
                    return "registerClientCallback";
                case 11:
                    return "tryUnregisterService";
                case 12:
                    return "getServiceDebugInfo";
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
                    reply.writeString(IServiceManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg0 = data.readString();
                            IBinder _result = getService(_arg0);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg02 = data.readString();
                            IBinder _result2 = checkService(_arg02);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg03 = data.readString();
                            IBinder _arg1 = data.readStrongBinder();
                            boolean _arg2 = data.readInt() != 0;
                            int _arg3 = data.readInt();
                            addService(_arg03, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            String[] _result3 = listServices(_arg04);
                            reply.writeNoException();
                            reply.writeStringArray(_result3);
                            return true;
                        case 5:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg05 = data.readString();
                            IServiceCallback _arg12 = IServiceCallback.Stub.asInterface(data.readStrongBinder());
                            registerForNotifications(_arg05, _arg12);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg06 = data.readString();
                            IServiceCallback _arg13 = IServiceCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterForNotifications(_arg06, _arg13);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg07 = data.readString();
                            boolean isDeclared = isDeclared(_arg07);
                            reply.writeNoException();
                            reply.writeInt(isDeclared ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg08 = data.readString();
                            String[] _result4 = getDeclaredInstances(_arg08);
                            reply.writeNoException();
                            reply.writeStringArray(_result4);
                            return true;
                        case 9:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _result5 = updatableViaApex(_arg09);
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        case 10:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg010 = data.readString();
                            IBinder _arg14 = data.readStrongBinder();
                            IClientCallback _arg22 = IClientCallback.Stub.asInterface(data.readStrongBinder());
                            registerClientCallback(_arg010, _arg14, _arg22);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            String _arg011 = data.readString();
                            IBinder _arg15 = data.readStrongBinder();
                            tryUnregisterService(_arg011, _arg15);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IServiceManager.DESCRIPTOR);
                            ServiceDebugInfo[] _result6 = getServiceDebugInfo();
                            reply.writeNoException();
                            reply.writeTypedArray(_result6, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IServiceManager {
            public static IServiceManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IServiceManager.DESCRIPTOR;
            }

            @Override // android.os.IServiceManager
            public IBinder getService(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getService(name);
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public IBinder checkService(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkService(name);
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void addService(String name, IBinder service, boolean allowIsolated, int dumpPriority) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeStrongBinder(service);
                    _data.writeInt(allowIsolated ? 1 : 0);
                    _data.writeInt(dumpPriority);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addService(name, service, allowIsolated, dumpPriority);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public String[] listServices(int dumpPriority) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeInt(dumpPriority);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listServices(dumpPriority);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void registerForNotifications(String name, IServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerForNotifications(name, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void unregisterForNotifications(String name, IServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterForNotifications(name, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public boolean isDeclared(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeclared(name);
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

            @Override // android.os.IServiceManager
            public String[] getDeclaredInstances(String iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeclaredInstances(iface);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public String updatableViaApex(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updatableViaApex(name);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void registerClientCallback(String name, IBinder service, IClientCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeStrongBinder(service);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerClientCallback(name, service, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public void tryUnregisterService(String name, IBinder service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeStrongBinder(service);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().tryUnregisterService(name, service);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IServiceManager
            public ServiceDebugInfo[] getServiceDebugInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IServiceManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServiceDebugInfo();
                    }
                    _reply.readException();
                    ServiceDebugInfo[] _result = (ServiceDebugInfo[]) _reply.createTypedArray(ServiceDebugInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IServiceManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IServiceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
