package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IMediaRoute2ProviderServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "android.media.IMediaRoute2ProviderServiceCallback";

    void notifyProviderUpdated(MediaRoute2ProviderInfo mediaRoute2ProviderInfo) throws RemoteException;

    void notifyRequestFailed(long j, int i) throws RemoteException;

    void notifySessionCreated(long j, RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionReleased(RoutingSessionInfo routingSessionInfo) throws RemoteException;

    void notifySessionsUpdated(List<RoutingSessionInfo> list) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMediaRoute2ProviderServiceCallback {
        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifyProviderUpdated(MediaRoute2ProviderInfo providerInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifySessionCreated(long requestId, RoutingSessionInfo sessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifySessionsUpdated(List<RoutingSessionInfo> sessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifySessionReleased(RoutingSessionInfo sessionInfo) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderServiceCallback
        public void notifyRequestFailed(long requestId, int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaRoute2ProviderServiceCallback {
        static final int TRANSACTION_notifyProviderUpdated = 1;
        static final int TRANSACTION_notifyRequestFailed = 5;
        static final int TRANSACTION_notifySessionCreated = 2;
        static final int TRANSACTION_notifySessionReleased = 4;
        static final int TRANSACTION_notifySessionsUpdated = 3;

        public Stub() {
            attachInterface(this, IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
        }

        public static IMediaRoute2ProviderServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaRoute2ProviderServiceCallback)) {
                return new Proxy(obj);
            }
            return (IMediaRoute2ProviderServiceCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifyProviderUpdated";
                case 2:
                    return "notifySessionCreated";
                case 3:
                    return "notifySessionsUpdated";
                case 4:
                    return "notifySessionReleased";
                case 5:
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
            MediaRoute2ProviderInfo _arg0;
            RoutingSessionInfo _arg1;
            RoutingSessionInfo _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = MediaRoute2ProviderInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            notifyProviderUpdated(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                            long _arg03 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg1 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            notifySessionCreated(_arg03, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                            List<RoutingSessionInfo> _arg04 = data.createTypedArrayList(RoutingSessionInfo.CREATOR);
                            notifySessionsUpdated(_arg04);
                            return true;
                        case 4:
                            data.enforceInterface(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = RoutingSessionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            notifySessionReleased(_arg02);
                            return true;
                        case 5:
                            data.enforceInterface(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                            long _arg05 = data.readLong();
                            int _arg12 = data.readInt();
                            notifyRequestFailed(_arg05, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaRoute2ProviderServiceCallback {
            public static IMediaRoute2ProviderServiceCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaRoute2ProviderServiceCallback.DESCRIPTOR;
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifyProviderUpdated(MediaRoute2ProviderInfo providerInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                    if (providerInfo != null) {
                        _data.writeInt(1);
                        providerInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyProviderUpdated(providerInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifySessionCreated(long requestId, RoutingSessionInfo sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                    _data.writeLong(requestId);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionCreated(requestId, sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifySessionsUpdated(List<RoutingSessionInfo> sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                    _data.writeTypedList(sessionInfo);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionsUpdated(sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifySessionReleased(RoutingSessionInfo sessionInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                    if (sessionInfo != null) {
                        _data.writeInt(1);
                        sessionInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySessionReleased(sessionInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderServiceCallback
            public void notifyRequestFailed(long requestId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderServiceCallback.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRequestFailed(requestId, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRoute2ProviderServiceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMediaRoute2ProviderServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
