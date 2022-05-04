package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IMediaRouter2 extends IInterface {
    public static final String DESCRIPTOR = "android.media.IMediaRouter2";

    void notifyRouterRegistered(List<MediaRoute2Info> list, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifyRoutesAdded(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesChanged(List<MediaRoute2Info> list) throws RemoteException;

    void notifyRoutesRemoved(List<MediaRoute2Info> list) throws RemoteException;

    void notifySessionCreated(int i, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionInfoChanged(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void requestCreateSessionByManager(long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMediaRouter2 {
        @Override // android.media.IMediaRouter2
        public void notifyRouterRegistered(List<MediaRoute2Info> currentRoutes, RoutingSessionInfo currentSystemSessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesAdded(List<MediaRoute2Info> routes) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesRemoved(List<MediaRoute2Info> routes) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifyRoutesChanged(List<MediaRoute2Info> routes) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionCreated(int requestId, RoutingSessionInfo sessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionInfoChanged(RoutingSessionInfo sessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void notifySessionReleased(RoutingSessionInfo sessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRouter2
        public void requestCreateSessionByManager(long uniqueRequestId, RoutingSessionInfo oldSession, MediaRoute2Info route) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaRouter2 {
        static final int TRANSACTION_notifyRouterRegistered = 1;
        static final int TRANSACTION_notifyRoutesAdded = 2;
        static final int TRANSACTION_notifyRoutesChanged = 4;
        static final int TRANSACTION_notifyRoutesRemoved = 3;
        static final int TRANSACTION_notifySessionCreated = 5;
        static final int TRANSACTION_notifySessionInfoChanged = 6;
        static final int TRANSACTION_notifySessionReleased = 7;
        static final int TRANSACTION_requestCreateSessionByManager = 8;

        public Stub() {
            attachInterface(this, IMediaRouter2.DESCRIPTOR);
        }

        public static IMediaRouter2 asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMediaRouter2.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaRouter2)) {
                return new Proxy(obj);
            }
            return (IMediaRouter2) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifyRouterRegistered";
                case 2:
                    return "notifyRoutesAdded";
                case 3:
                    return "notifyRoutesRemoved";
                case 4:
                    return "notifyRoutesChanged";
                case 5:
                    return "notifySessionCreated";
                case 6:
                    return "notifySessionInfoChanged";
                case 7:
                    return "notifySessionReleased";
                case 8:
                    return "requestCreateSessionByManager";
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
            RoutingSessionInfo _arg12;
            RoutingSessionInfo _arg0;
            RoutingSessionInfo _arg02;
            RoutingSessionInfo _arg13;
            MediaRoute2Info _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMediaRouter2.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            List<MediaRoute2Info> _arg03 = data.createTypedArrayList(MediaRoute2Info.CREATOR);
                            if (data.readInt() != 0) {
                                _arg1 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            notifyRouterRegistered(_arg03, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            List<MediaRoute2Info> _arg04 = data.createTypedArrayList(MediaRoute2Info.CREATOR);
                            notifyRoutesAdded(_arg04);
                            return true;
                        case 3:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            List<MediaRoute2Info> _arg05 = data.createTypedArrayList(MediaRoute2Info.CREATOR);
                            notifyRoutesRemoved(_arg05);
                            return true;
                        case 4:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            List<MediaRoute2Info> _arg06 = data.createTypedArrayList(MediaRoute2Info.CREATOR);
                            notifyRoutesChanged(_arg06);
                            return true;
                        case 5:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            notifySessionCreated(_arg07, _arg12);
                            return true;
                        case 6:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            notifySessionInfoChanged(_arg0);
                            return true;
                        case 7:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            notifySessionReleased(_arg02);
                            return true;
                        case 8:
                            data.enforceInterface(IMediaRouter2.DESCRIPTOR);
                            long _arg08 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg13 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = MediaRoute2Info.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            requestCreateSessionByManager(_arg08, _arg13, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaRouter2 {
            public static IMediaRouter2 sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaRouter2.DESCRIPTOR;
            }

            @Override // android.media.IMediaRouter2
            public void notifyRouterRegistered(List<MediaRoute2Info> currentRoutes, RoutingSessionInfo currentSystemSessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    _data.writeTypedList(currentRoutes);
                    if (currentSystemSessionInfo != null) {
                        _data.writeInt(1);
                        currentSystemSessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRouterRegistered(currentRoutes, currentSystemSessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifyRoutesAdded(List<MediaRoute2Info> routes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    _data.writeTypedList(routes);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRoutesAdded(routes);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifyRoutesRemoved(List<MediaRoute2Info> routes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    _data.writeTypedList(routes);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRoutesRemoved(routes);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifyRoutesChanged(List<MediaRoute2Info> routes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    _data.writeTypedList(routes);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRoutesChanged(routes);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifySessionCreated(int requestId, RoutingSessionInfo sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    _data.writeInt(requestId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionCreated(requestId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifySessionInfoChanged(RoutingSessionInfo sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionInfoChanged(sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void notifySessionReleased(RoutingSessionInfo sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionReleased(sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouter2
            public void requestCreateSessionByManager(long uniqueRequestId, RoutingSessionInfo oldSession, MediaRoute2Info route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRouter2.DESCRIPTOR);
                    _data.writeLong(uniqueRequestId);
                    if (oldSession != null) {
                        _data.writeInt(1);
                        oldSession.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCreateSessionByManager(uniqueRequestId, oldSession, route);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouter2 impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMediaRouter2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
