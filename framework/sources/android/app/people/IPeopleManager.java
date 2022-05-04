package android.app.people;

import android.app.people.IConversationListener;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IPeopleManager extends IInterface {
    public static final String DESCRIPTOR = "android.app.people.IPeopleManager";

    void addOrUpdateStatus(String str, int i, String str2, ConversationStatus conversationStatus) throws RemoteException;

    void clearStatus(String str, int i, String str2, String str3) throws RemoteException;

    void clearStatuses(String str, int i, String str2) throws RemoteException;

    ConversationChannel getConversation(String str, int i, String str2) throws RemoteException;

    long getLastInteraction(String str, int i, String str2) throws RemoteException;

    ParceledListSlice getRecentConversations() throws RemoteException;

    ParceledListSlice getStatuses(String str, int i, String str2) throws RemoteException;

    boolean isConversation(String str, int i, String str2) throws RemoteException;

    void registerConversationListener(String str, int i, String str2, IConversationListener iConversationListener) throws RemoteException;

    void removeAllRecentConversations() throws RemoteException;

    void removeRecentConversation(String str, int i, String str2) throws RemoteException;

    void unregisterConversationListener(IConversationListener iConversationListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPeopleManager {
        @Override // android.app.people.IPeopleManager
        public ConversationChannel getConversation(String packageName, int userId, String shortcutId) throws RemoteException {
            return null;
        }

        @Override // android.app.people.IPeopleManager
        public ParceledListSlice getRecentConversations() throws RemoteException {
            return null;
        }

        @Override // android.app.people.IPeopleManager
        public void removeRecentConversation(String packageName, int userId, String shortcutId) throws RemoteException {
        }

        @Override // android.app.people.IPeopleManager
        public void removeAllRecentConversations() throws RemoteException {
        }

        @Override // android.app.people.IPeopleManager
        public boolean isConversation(String packageName, int userId, String shortcutId) throws RemoteException {
            return false;
        }

        @Override // android.app.people.IPeopleManager
        public long getLastInteraction(String packageName, int userId, String shortcutId) throws RemoteException {
            return 0L;
        }

        @Override // android.app.people.IPeopleManager
        public void addOrUpdateStatus(String packageName, int userId, String conversationId, ConversationStatus status) throws RemoteException {
        }

        @Override // android.app.people.IPeopleManager
        public void clearStatus(String packageName, int userId, String conversationId, String statusId) throws RemoteException {
        }

        @Override // android.app.people.IPeopleManager
        public void clearStatuses(String packageName, int userId, String conversationId) throws RemoteException {
        }

        @Override // android.app.people.IPeopleManager
        public ParceledListSlice getStatuses(String packageName, int userId, String conversationId) throws RemoteException {
            return null;
        }

        @Override // android.app.people.IPeopleManager
        public void registerConversationListener(String packageName, int userId, String shortcutId, IConversationListener callback) throws RemoteException {
        }

        @Override // android.app.people.IPeopleManager
        public void unregisterConversationListener(IConversationListener callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPeopleManager {
        static final int TRANSACTION_addOrUpdateStatus = 7;
        static final int TRANSACTION_clearStatus = 8;
        static final int TRANSACTION_clearStatuses = 9;
        static final int TRANSACTION_getConversation = 1;
        static final int TRANSACTION_getLastInteraction = 6;
        static final int TRANSACTION_getRecentConversations = 2;
        static final int TRANSACTION_getStatuses = 10;
        static final int TRANSACTION_isConversation = 5;
        static final int TRANSACTION_registerConversationListener = 11;
        static final int TRANSACTION_removeAllRecentConversations = 4;
        static final int TRANSACTION_removeRecentConversation = 3;
        static final int TRANSACTION_unregisterConversationListener = 12;

        public Stub() {
            attachInterface(this, IPeopleManager.DESCRIPTOR);
        }

        public static IPeopleManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPeopleManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPeopleManager)) {
                return new Proxy(obj);
            }
            return (IPeopleManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getConversation";
                case 2:
                    return "getRecentConversations";
                case 3:
                    return "removeRecentConversation";
                case 4:
                    return "removeAllRecentConversations";
                case 5:
                    return "isConversation";
                case 6:
                    return "getLastInteraction";
                case 7:
                    return "addOrUpdateStatus";
                case 8:
                    return "clearStatus";
                case 9:
                    return "clearStatuses";
                case 10:
                    return "getStatuses";
                case 11:
                    return "registerConversationListener";
                case 12:
                    return "unregisterConversationListener";
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
            ConversationStatus _arg3;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPeopleManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _arg1 = data.readInt();
                            String _arg2 = data.readString();
                            ConversationChannel _result = getConversation(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            ParceledListSlice _result2 = getRecentConversations();
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg12 = data.readInt();
                            String _arg22 = data.readString();
                            removeRecentConversation(_arg02, _arg12, _arg22);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            removeAllRecentConversations();
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg13 = data.readInt();
                            String _arg23 = data.readString();
                            boolean isConversation = isConversation(_arg03, _arg13, _arg23);
                            reply.writeNoException();
                            reply.writeInt(isConversation ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg04 = data.readString();
                            int _arg14 = data.readInt();
                            String _arg24 = data.readString();
                            long _result3 = getLastInteraction(_arg04, _arg14, _arg24);
                            reply.writeNoException();
                            reply.writeLong(_result3);
                            return true;
                        case 7:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg15 = data.readInt();
                            String _arg25 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = ConversationStatus.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            addOrUpdateStatus(_arg05, _arg15, _arg25, _arg3);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg06 = data.readString();
                            int _arg16 = data.readInt();
                            String _arg26 = data.readString();
                            String _arg32 = data.readString();
                            clearStatus(_arg06, _arg16, _arg26, _arg32);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg17 = data.readInt();
                            String _arg27 = data.readString();
                            clearStatuses(_arg07, _arg17, _arg27);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg08 = data.readString();
                            int _arg18 = data.readInt();
                            String _arg28 = data.readString();
                            ParceledListSlice _result4 = getStatuses(_arg08, _arg18, _arg28);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            String _arg09 = data.readString();
                            int _arg19 = data.readInt();
                            String _arg29 = data.readString();
                            IConversationListener _arg33 = IConversationListener.Stub.asInterface(data.readStrongBinder());
                            registerConversationListener(_arg09, _arg19, _arg29, _arg33);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IPeopleManager.DESCRIPTOR);
                            IConversationListener _arg010 = IConversationListener.Stub.asInterface(data.readStrongBinder());
                            unregisterConversationListener(_arg010);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPeopleManager {
            public static IPeopleManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPeopleManager.DESCRIPTOR;
            }

            @Override // android.app.people.IPeopleManager
            public ConversationChannel getConversation(String packageName, int userId, String shortcutId) throws RemoteException {
                ConversationChannel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(shortcutId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConversation(packageName, userId, shortcutId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ConversationChannel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public ParceledListSlice getRecentConversations() throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecentConversations();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public void removeRecentConversation(String packageName, int userId, String shortcutId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(shortcutId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeRecentConversation(packageName, userId, shortcutId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public void removeAllRecentConversations() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeAllRecentConversations();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public boolean isConversation(String packageName, int userId, String shortcutId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(shortcutId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConversation(packageName, userId, shortcutId);
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

            @Override // android.app.people.IPeopleManager
            public long getLastInteraction(String packageName, int userId, String shortcutId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(shortcutId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastInteraction(packageName, userId, shortcutId);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public void addOrUpdateStatus(String packageName, int userId, String conversationId, ConversationStatus status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(conversationId);
                    if (status != null) {
                        _data.writeInt(1);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addOrUpdateStatus(packageName, userId, conversationId, status);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public void clearStatus(String packageName, int userId, String conversationId, String statusId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(conversationId);
                    _data.writeString(statusId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearStatus(packageName, userId, conversationId, statusId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public void clearStatuses(String packageName, int userId, String conversationId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(conversationId);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearStatuses(packageName, userId, conversationId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public ParceledListSlice getStatuses(String packageName, int userId, String conversationId) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(conversationId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatuses(packageName, userId, conversationId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public void registerConversationListener(String packageName, int userId, String shortcutId, IConversationListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeString(shortcutId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerConversationListener(packageName, userId, shortcutId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.people.IPeopleManager
            public void unregisterConversationListener(IConversationListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPeopleManager.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterConversationListener(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPeopleManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPeopleManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
