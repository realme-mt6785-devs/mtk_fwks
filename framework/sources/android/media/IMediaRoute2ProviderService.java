package android.media;

import android.media.IMediaRoute2ProviderServiceCallback;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IMediaRoute2ProviderService extends IInterface {
    public static final String DESCRIPTOR = "android.media.IMediaRoute2ProviderService";

    void deselectRoute(long j, String str, String str2) throws RemoteException;

    void releaseSession(long j, String str) throws RemoteException;

    void requestCreateSession(long j, String str, String str2, Bundle bundle) throws RemoteException;

    void selectRoute(long j, String str, String str2) throws RemoteException;

    void setCallback(IMediaRoute2ProviderServiceCallback iMediaRoute2ProviderServiceCallback) throws RemoteException;

    void setRouteVolume(long j, String str, int i) throws RemoteException;

    void setSessionVolume(long j, String str, int i) throws RemoteException;

    void transferToRoute(long j, String str, String str2) throws RemoteException;

    void updateDiscoveryPreference(RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMediaRoute2ProviderService {
        @Override // android.media.IMediaRoute2ProviderService
        public void setCallback(IMediaRoute2ProviderServiceCallback callback) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void updateDiscoveryPreference(RouteDiscoveryPreference discoveryPreference) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setRouteVolume(long requestId, String routeId, int volume) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void requestCreateSession(long requestId, String packageName, String routeId, Bundle sessionHints) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void selectRoute(long requestId, String sessionId, String routeId) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void deselectRoute(long requestId, String sessionId, String routeId) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void transferToRoute(long requestId, String sessionId, String routeId) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void setSessionVolume(long requestId, String sessionId, int volume) throws RemoteException {
        }

        @Override // android.media.IMediaRoute2ProviderService
        public void releaseSession(long requestId, String sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaRoute2ProviderService {
        static final int TRANSACTION_deselectRoute = 6;
        static final int TRANSACTION_releaseSession = 9;
        static final int TRANSACTION_requestCreateSession = 4;
        static final int TRANSACTION_selectRoute = 5;
        static final int TRANSACTION_setCallback = 1;
        static final int TRANSACTION_setRouteVolume = 3;
        static final int TRANSACTION_setSessionVolume = 8;
        static final int TRANSACTION_transferToRoute = 7;
        static final int TRANSACTION_updateDiscoveryPreference = 2;

        public Stub() {
            attachInterface(this, IMediaRoute2ProviderService.DESCRIPTOR);
        }

        public static IMediaRoute2ProviderService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMediaRoute2ProviderService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaRoute2ProviderService)) {
                return new Proxy(obj);
            }
            return (IMediaRoute2ProviderService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setCallback";
                case 2:
                    return "updateDiscoveryPreference";
                case 3:
                    return "setRouteVolume";
                case 4:
                    return "requestCreateSession";
                case 5:
                    return "selectRoute";
                case 6:
                    return "deselectRoute";
                case 7:
                    return "transferToRoute";
                case 8:
                    return "setSessionVolume";
                case 9:
                    return "releaseSession";
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
            RouteDiscoveryPreference _arg0;
            Bundle _arg3;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMediaRoute2ProviderService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            IMediaRoute2ProviderServiceCallback _arg02 = IMediaRoute2ProviderServiceCallback.Stub.asInterface(data.readStrongBinder());
                            setCallback(_arg02);
                            return true;
                        case 2:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RouteDiscoveryPreference.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            updateDiscoveryPreference(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            long _arg03 = data.readLong();
                            String _arg1 = data.readString();
                            int _arg2 = data.readInt();
                            setRouteVolume(_arg03, _arg1, _arg2);
                            return true;
                        case 4:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            long _arg04 = data.readLong();
                            String _arg12 = data.readString();
                            String _arg22 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            requestCreateSession(_arg04, _arg12, _arg22, _arg3);
                            return true;
                        case 5:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            long _arg05 = data.readLong();
                            String _arg13 = data.readString();
                            String _arg23 = data.readString();
                            selectRoute(_arg05, _arg13, _arg23);
                            return true;
                        case 6:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            long _arg06 = data.readLong();
                            String _arg14 = data.readString();
                            String _arg24 = data.readString();
                            deselectRoute(_arg06, _arg14, _arg24);
                            return true;
                        case 7:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            long _arg07 = data.readLong();
                            String _arg15 = data.readString();
                            String _arg25 = data.readString();
                            transferToRoute(_arg07, _arg15, _arg25);
                            return true;
                        case 8:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            long _arg08 = data.readLong();
                            String _arg16 = data.readString();
                            int _arg26 = data.readInt();
                            setSessionVolume(_arg08, _arg16, _arg26);
                            return true;
                        case 9:
                            data.enforceInterface(IMediaRoute2ProviderService.DESCRIPTOR);
                            long _arg09 = data.readLong();
                            String _arg17 = data.readString();
                            releaseSession(_arg09, _arg17);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaRoute2ProviderService {
            public static IMediaRoute2ProviderService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaRoute2ProviderService.DESCRIPTOR;
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void setCallback(IMediaRoute2ProviderServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallback(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void updateDiscoveryPreference(RouteDiscoveryPreference discoveryPreference) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    if (discoveryPreference != null) {
                        _data.writeInt(1);
                        discoveryPreference.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateDiscoveryPreference(discoveryPreference);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void setRouteVolume(long requestId, String routeId, int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeString(routeId);
                    _data.writeInt(volume);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRouteVolume(requestId, routeId, volume);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void requestCreateSession(long requestId, String packageName, String routeId, Bundle sessionHints) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeString(packageName);
                    _data.writeString(routeId);
                    if (sessionHints != null) {
                        _data.writeInt(1);
                        sessionHints.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCreateSession(requestId, packageName, routeId, sessionHints);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void selectRoute(long requestId, String sessionId, String routeId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeString(sessionId);
                    _data.writeString(routeId);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().selectRoute(requestId, sessionId, routeId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void deselectRoute(long requestId, String sessionId, String routeId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeString(sessionId);
                    _data.writeString(routeId);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deselectRoute(requestId, sessionId, routeId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void transferToRoute(long requestId, String sessionId, String routeId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeString(sessionId);
                    _data.writeString(routeId);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().transferToRoute(requestId, sessionId, routeId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void setSessionVolume(long requestId, String sessionId, int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeString(sessionId);
                    _data.writeInt(volume);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSessionVolume(requestId, sessionId, volume);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRoute2ProviderService
            public void releaseSession(long requestId, String sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaRoute2ProviderService.DESCRIPTOR);
                    _data.writeLong(requestId);
                    _data.writeString(sessionId);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseSession(requestId, sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRoute2ProviderService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMediaRoute2ProviderService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
