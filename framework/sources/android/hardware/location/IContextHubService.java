package android.hardware.location;

import android.app.PendingIntent;
import android.hardware.location.IContextHubCallback;
import android.hardware.location.IContextHubClient;
import android.hardware.location.IContextHubClientCallback;
import android.hardware.location.IContextHubTransactionCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IContextHubService extends IInterface {
    IContextHubClient createClient(int i, IContextHubClientCallback iContextHubClientCallback, String str, String str2) throws RemoteException;

    IContextHubClient createPendingIntentClient(int i, PendingIntent pendingIntent, long j, String str) throws RemoteException;

    void disableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException;

    void enableNanoApp(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException;

    int[] findNanoAppOnHub(int i, NanoAppFilter nanoAppFilter) throws RemoteException;

    int[] getContextHubHandles() throws RemoteException;

    ContextHubInfo getContextHubInfo(int i) throws RemoteException;

    List<ContextHubInfo> getContextHubs() throws RemoteException;

    NanoAppInstanceInfo getNanoAppInstanceInfo(int i) throws RemoteException;

    int loadNanoApp(int i, NanoApp nanoApp) throws RemoteException;

    void loadNanoAppOnHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, NanoAppBinary nanoAppBinary) throws RemoteException;

    void queryNanoApps(int i, IContextHubTransactionCallback iContextHubTransactionCallback) throws RemoteException;

    int registerCallback(IContextHubCallback iContextHubCallback) throws RemoteException;

    int sendMessage(int i, int i2, ContextHubMessage contextHubMessage) throws RemoteException;

    int unloadNanoApp(int i) throws RemoteException;

    void unloadNanoAppFromHub(int i, IContextHubTransactionCallback iContextHubTransactionCallback, long j) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IContextHubService {
        @Override // android.hardware.location.IContextHubService
        public int registerCallback(IContextHubCallback callback) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public int[] getContextHubHandles() throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public ContextHubInfo getContextHubInfo(int contextHubHandle) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public int loadNanoApp(int contextHubHandle, NanoApp nanoApp) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public int unloadNanoApp(int nanoAppHandle) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public NanoAppInstanceInfo getNanoAppInstanceInfo(int nanoAppHandle) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public int[] findNanoAppOnHub(int contextHubHandle, NanoAppFilter filter) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public int sendMessage(int contextHubHandle, int nanoAppHandle, ContextHubMessage msg) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.location.IContextHubService
        public IContextHubClient createClient(int contextHubId, IContextHubClientCallback client, String attributionTag, String packageName) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public IContextHubClient createPendingIntentClient(int contextHubId, PendingIntent pendingIntent, long nanoAppId, String attributionTag) throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public List<ContextHubInfo> getContextHubs() throws RemoteException {
            return null;
        }

        @Override // android.hardware.location.IContextHubService
        public void loadNanoAppOnHub(int contextHubId, IContextHubTransactionCallback transactionCallback, NanoAppBinary nanoAppBinary) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public void unloadNanoAppFromHub(int contextHubId, IContextHubTransactionCallback transactionCallback, long nanoAppId) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public void enableNanoApp(int contextHubId, IContextHubTransactionCallback transactionCallback, long nanoAppId) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public void disableNanoApp(int contextHubId, IContextHubTransactionCallback transactionCallback, long nanoAppId) throws RemoteException {
        }

        @Override // android.hardware.location.IContextHubService
        public void queryNanoApps(int contextHubId, IContextHubTransactionCallback transactionCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IContextHubService {
        public static final String DESCRIPTOR = "android.hardware.location.IContextHubService";
        static final int TRANSACTION_createClient = 9;
        static final int TRANSACTION_createPendingIntentClient = 10;
        static final int TRANSACTION_disableNanoApp = 15;
        static final int TRANSACTION_enableNanoApp = 14;
        static final int TRANSACTION_findNanoAppOnHub = 7;
        static final int TRANSACTION_getContextHubHandles = 2;
        static final int TRANSACTION_getContextHubInfo = 3;
        static final int TRANSACTION_getContextHubs = 11;
        static final int TRANSACTION_getNanoAppInstanceInfo = 6;
        static final int TRANSACTION_loadNanoApp = 4;
        static final int TRANSACTION_loadNanoAppOnHub = 12;
        static final int TRANSACTION_queryNanoApps = 16;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_sendMessage = 8;
        static final int TRANSACTION_unloadNanoApp = 5;
        static final int TRANSACTION_unloadNanoAppFromHub = 13;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContextHubService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IContextHubService)) {
                return new Proxy(obj);
            }
            return (IContextHubService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerCallback";
                case 2:
                    return "getContextHubHandles";
                case 3:
                    return "getContextHubInfo";
                case 4:
                    return "loadNanoApp";
                case 5:
                    return "unloadNanoApp";
                case 6:
                    return "getNanoAppInstanceInfo";
                case 7:
                    return "findNanoAppOnHub";
                case 8:
                    return "sendMessage";
                case 9:
                    return "createClient";
                case 10:
                    return "createPendingIntentClient";
                case 11:
                    return "getContextHubs";
                case 12:
                    return "loadNanoAppOnHub";
                case 13:
                    return "unloadNanoAppFromHub";
                case 14:
                    return "enableNanoApp";
                case 15:
                    return "disableNanoApp";
                case 16:
                    return "queryNanoApps";
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
            NanoApp _arg1;
            NanoAppFilter _arg12;
            ContextHubMessage _arg2;
            PendingIntent _arg13;
            NanoAppBinary _arg22;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            IContextHubCallback _arg0 = IContextHubCallback.Stub.asInterface(data.readStrongBinder());
                            int _result = registerCallback(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _result2 = getContextHubHandles();
                            reply.writeNoException();
                            reply.writeIntArray(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            ContextHubInfo _result3 = getContextHubInfo(_arg02);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = NanoApp.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _result4 = loadNanoApp(_arg03, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _result5 = unloadNanoApp(_arg04);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            NanoAppInstanceInfo _result6 = getNanoAppInstanceInfo(_arg05);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = NanoAppFilter.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            int[] _result7 = findNanoAppOnHub(_arg06, _arg12);
                            reply.writeNoException();
                            reply.writeIntArray(_result7);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg14 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = ContextHubMessage.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _result8 = sendMessage(_arg07, _arg14, _arg2);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            IContextHubClientCallback _arg15 = IContextHubClientCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg23 = data.readString();
                            String _arg3 = data.readString();
                            IContextHubClient _result9 = createClient(_arg08, _arg15, _arg23, _arg3);
                            reply.writeNoException();
                            if (_result9 != null) {
                                iBinder = _result9.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            long _arg24 = data.readLong();
                            String _arg32 = data.readString();
                            IContextHubClient _result10 = createPendingIntentClient(_arg09, _arg13, _arg24, _arg32);
                            reply.writeNoException();
                            if (_result10 != null) {
                                iBinder = _result10.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            List<ContextHubInfo> _result11 = getContextHubs();
                            reply.writeNoException();
                            reply.writeTypedList(_result11);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            IContextHubTransactionCallback _arg16 = IContextHubTransactionCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg22 = NanoAppBinary.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            loadNanoAppOnHub(_arg010, _arg16, _arg22);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            IContextHubTransactionCallback _arg17 = IContextHubTransactionCallback.Stub.asInterface(data.readStrongBinder());
                            long _arg25 = data.readLong();
                            unloadNanoAppFromHub(_arg011, _arg17, _arg25);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            IContextHubTransactionCallback _arg18 = IContextHubTransactionCallback.Stub.asInterface(data.readStrongBinder());
                            long _arg26 = data.readLong();
                            enableNanoApp(_arg012, _arg18, _arg26);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            IContextHubTransactionCallback _arg19 = IContextHubTransactionCallback.Stub.asInterface(data.readStrongBinder());
                            long _arg27 = data.readLong();
                            disableNanoApp(_arg013, _arg19, _arg27);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            IContextHubTransactionCallback _arg110 = IContextHubTransactionCallback.Stub.asInterface(data.readStrongBinder());
                            queryNanoApps(_arg014, _arg110);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IContextHubService {
            public static IContextHubService sDefaultImpl;
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

            @Override // android.hardware.location.IContextHubService
            public int registerCallback(IContextHubCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int[] getContextHubHandles() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContextHubHandles();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public ContextHubInfo getContextHubInfo(int contextHubHandle) throws RemoteException {
                ContextHubInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubHandle);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContextHubInfo(contextHubHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ContextHubInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int loadNanoApp(int contextHubHandle, NanoApp nanoApp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubHandle);
                    if (nanoApp != null) {
                        _data.writeInt(1);
                        nanoApp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadNanoApp(contextHubHandle, nanoApp);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int unloadNanoApp(int nanoAppHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nanoAppHandle);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unloadNanoApp(nanoAppHandle);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public NanoAppInstanceInfo getNanoAppInstanceInfo(int nanoAppHandle) throws RemoteException {
                NanoAppInstanceInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nanoAppHandle);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNanoAppInstanceInfo(nanoAppHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NanoAppInstanceInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int[] findNanoAppOnHub(int contextHubHandle, NanoAppFilter filter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubHandle);
                    if (filter != null) {
                        _data.writeInt(1);
                        filter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findNanoAppOnHub(contextHubHandle, filter);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public int sendMessage(int contextHubHandle, int nanoAppHandle, ContextHubMessage msg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubHandle);
                    _data.writeInt(nanoAppHandle);
                    if (msg != null) {
                        _data.writeInt(1);
                        msg.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendMessage(contextHubHandle, nanoAppHandle, msg);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public IContextHubClient createClient(int contextHubId, IContextHubClientCallback client, String attributionTag, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubId);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeString(attributionTag);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createClient(contextHubId, client, attributionTag, packageName);
                    }
                    _reply.readException();
                    IContextHubClient _result = IContextHubClient.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public IContextHubClient createPendingIntentClient(int contextHubId, PendingIntent pendingIntent, long nanoAppId, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubId);
                    if (pendingIntent != null) {
                        _data.writeInt(1);
                        pendingIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(nanoAppId);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createPendingIntentClient(contextHubId, pendingIntent, nanoAppId, attributionTag);
                    }
                    _reply.readException();
                    IContextHubClient _result = IContextHubClient.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public List<ContextHubInfo> getContextHubs() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContextHubs();
                    }
                    _reply.readException();
                    List<ContextHubInfo> _result = _reply.createTypedArrayList(ContextHubInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void loadNanoAppOnHub(int contextHubId, IContextHubTransactionCallback transactionCallback, NanoAppBinary nanoAppBinary) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubId);
                    _data.writeStrongBinder(transactionCallback != null ? transactionCallback.asBinder() : null);
                    if (nanoAppBinary != null) {
                        _data.writeInt(1);
                        nanoAppBinary.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().loadNanoAppOnHub(contextHubId, transactionCallback, nanoAppBinary);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void unloadNanoAppFromHub(int contextHubId, IContextHubTransactionCallback transactionCallback, long nanoAppId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubId);
                    _data.writeStrongBinder(transactionCallback != null ? transactionCallback.asBinder() : null);
                    _data.writeLong(nanoAppId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unloadNanoAppFromHub(contextHubId, transactionCallback, nanoAppId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void enableNanoApp(int contextHubId, IContextHubTransactionCallback transactionCallback, long nanoAppId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubId);
                    _data.writeStrongBinder(transactionCallback != null ? transactionCallback.asBinder() : null);
                    _data.writeLong(nanoAppId);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableNanoApp(contextHubId, transactionCallback, nanoAppId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void disableNanoApp(int contextHubId, IContextHubTransactionCallback transactionCallback, long nanoAppId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubId);
                    _data.writeStrongBinder(transactionCallback != null ? transactionCallback.asBinder() : null);
                    _data.writeLong(nanoAppId);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableNanoApp(contextHubId, transactionCallback, nanoAppId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.location.IContextHubService
            public void queryNanoApps(int contextHubId, IContextHubTransactionCallback transactionCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(contextHubId);
                    _data.writeStrongBinder(transactionCallback != null ? transactionCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().queryNanoApps(contextHubId, transactionCallback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContextHubService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IContextHubService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
