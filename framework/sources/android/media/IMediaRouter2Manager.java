package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IMediaRouter2Manager extends IInterface {
    public static final String DESCRIPTOR = "android.media.IMediaRouter2Manager";

    void notifyPreferredFeaturesChanged(String str, List<String> list) throws RemoteException;

    void notifyRequestFailed(int i, int i2) throws RemoteException;

    void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException;

    void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionUpdated(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMediaRouter2Manager {
        @Override // android.media.IMediaRouter2Manager
        public void notifySessionCreated(int requestId, RoutingSessionInfo session) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionUpdated(RoutingSessionInfo session) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifySessionReleased(RoutingSessionInfo session) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyPreferredFeaturesChanged(String packageName, List<String> preferredFeatures) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesAdded(List<MediaRoute2Info> routes) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesRemoved(List<MediaRoute2Info> routes) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRoutesChanged(List<MediaRoute2Info> routes) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2Manager
        public void notifyRequestFailed(int requestId, int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaRouter2Manager {
        static final int TRANSACTION_notifyPreferredFeaturesChanged = 4;
        static final int TRANSACTION_notifyRequestFailed = 8;
        static final int TRANSACTION_notifyRoutesAdded = 5;
        static final int TRANSACTION_notifyRoutesChanged = 7;
        static final int TRANSACTION_notifyRoutesRemoved = 6;
        static final int TRANSACTION_notifySessionCreated = 1;
        static final int TRANSACTION_notifySessionReleased = 3;
        static final int TRANSACTION_notifySessionUpdated = 2;

        public Stub() {
            attachInterface(this, IMediaRouter2Manager.DESCRIPTOR);
        }

        public static IMediaRouter2Manager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMediaRouter2Manager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaRouter2Manager)) {
                return new Proxy(obj);
            }
            return (IMediaRouter2Manager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifySessionCreated";
                case 2:
                    return "notifySessionUpdated";
                case 3:
                    return "notifySessionReleased";
                case 4:
                    return "notifyPreferredFeaturesChanged";
                case 5:
                    return "notifyRoutesAdded";
                case 6:
                    return "notifyRoutesRemoved";
                case 7:
                    return "notifyRoutesChanged";
                case 8:
                    return "notifyRequestFailed";
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
            RoutingSessionInfo _arg1;
            RoutingSessionInfo _arg0;
            RoutingSessionInfo _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMediaRouter2Manager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            notifySessionCreated(_arg03, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            notifySessionUpdated(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            notifySessionReleased(_arg02);
                            return true;
                        case 4:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            String _arg04 = data.readString();
                            List<String> _arg12 = data.createStringArrayList();
                            notifyPreferredFeaturesChanged(_arg04, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            List<MediaRoute2Info> _arg05 = data.createTypedArrayList(MediaRoute2Info.CREATOR);
                            notifyRoutesAdded(_arg05);
                            return true;
                        case 6:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            List<MediaRoute2Info> _arg06 = data.createTypedArrayList(MediaRoute2Info.CREATOR);
                            notifyRoutesRemoved(_arg06);
                            return true;
                        case 7:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            List<MediaRoute2Info> _arg07 = data.createTypedArrayList(MediaRoute2Info.CREATOR);
                            notifyRoutesChanged(_arg07);
                            return true;
                        case 8:
                            data.enforceInterface(IMediaRouter2Manager.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg13 = data.readInt();
                            notifyRequestFailed(_arg08, _arg13);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaRouter2Manager {
            public static IMediaRouter2Manager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaRouter2Manager.DESCRIPTOR;
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifySessionCreated(int requestId, RoutingSessionInfo session) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (session != null) {
                        _data.writeInt(1);
                        session.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionCreated(requestId, session);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifySessionUpdated(RoutingSessionInfo session) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    if (session != null) {
                        _data.writeInt(1);
                        session.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionUpdated(session);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifySessionReleased(RoutingSessionInfo session) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    if (session != null) {
                        _data.writeInt(1);
                        session.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionReleased(session);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyPreferredFeaturesChanged(String packageName, List<String> preferredFeatures) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStringList(preferredFeatures);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPreferredFeaturesChanged(packageName, preferredFeatures);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRoutesAdded(List<MediaRoute2Info> routes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    _data.writeTypedList(routes);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRoutesAdded(routes);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRoutesRemoved(List<MediaRoute2Info> routes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    _data.writeTypedList(routes);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRoutesRemoved(routes);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRoutesChanged(List<MediaRoute2Info> routes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    _data.writeTypedList(routes);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRoutesChanged(routes);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2Manager
            public void notifyRequestFailed(int requestId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2Manager.DESCRIPTOR);
                    _data.writeInt(requestId);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRequestFailed(requestId, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouter2Manager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMediaRouter2Manager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
