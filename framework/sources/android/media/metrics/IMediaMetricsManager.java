package android.media.metrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IMediaMetricsManager extends IInterface {
    public static final String DESCRIPTOR = "android.media.metrics.IMediaMetricsManager";

    String getPlaybackSessionId(int i) throws RemoteException;

    String getRecordingSessionId(int i) throws RemoteException;

    void reportNetworkEvent(String str, NetworkEvent networkEvent, int i) throws RemoteException;

    void reportPlaybackErrorEvent(String str, PlaybackErrorEvent playbackErrorEvent, int i) throws RemoteException;

    void reportPlaybackMetrics(String str, PlaybackMetrics playbackMetrics, int i) throws RemoteException;

    void reportPlaybackStateEvent(String str, PlaybackStateEvent playbackStateEvent, int i) throws RemoteException;

    void reportTrackChangeEvent(String str, TrackChangeEvent trackChangeEvent, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMediaMetricsManager {
        @Override // android.media.metrics.IMediaMetricsManager
        public void reportPlaybackMetrics(String sessionId, PlaybackMetrics metrics, int userId) throws RemoteException {
        }

        @Override // android.media.metrics.IMediaMetricsManager
        public String getPlaybackSessionId(int userId) throws RemoteException {
            return null;
        }

        @Override // android.media.metrics.IMediaMetricsManager
        public String getRecordingSessionId(int userId) throws RemoteException {
            return null;
        }

        @Override // android.media.metrics.IMediaMetricsManager
        public void reportNetworkEvent(String sessionId, NetworkEvent event, int userId) throws RemoteException {
        }

        @Override // android.media.metrics.IMediaMetricsManager
        public void reportPlaybackErrorEvent(String sessionId, PlaybackErrorEvent event, int userId) throws RemoteException {
        }

        @Override // android.media.metrics.IMediaMetricsManager
        public void reportPlaybackStateEvent(String sessionId, PlaybackStateEvent event, int userId) throws RemoteException {
        }

        @Override // android.media.metrics.IMediaMetricsManager
        public void reportTrackChangeEvent(String sessionId, TrackChangeEvent event, int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaMetricsManager {
        static final int TRANSACTION_getPlaybackSessionId = 2;
        static final int TRANSACTION_getRecordingSessionId = 3;
        static final int TRANSACTION_reportNetworkEvent = 4;
        static final int TRANSACTION_reportPlaybackErrorEvent = 5;
        static final int TRANSACTION_reportPlaybackMetrics = 1;
        static final int TRANSACTION_reportPlaybackStateEvent = 6;
        static final int TRANSACTION_reportTrackChangeEvent = 7;

        public Stub() {
            attachInterface(this, IMediaMetricsManager.DESCRIPTOR);
        }

        public static IMediaMetricsManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMediaMetricsManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaMetricsManager)) {
                return new Proxy(obj);
            }
            return (IMediaMetricsManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "reportPlaybackMetrics";
                case 2:
                    return "getPlaybackSessionId";
                case 3:
                    return "getRecordingSessionId";
                case 4:
                    return "reportNetworkEvent";
                case 5:
                    return "reportPlaybackErrorEvent";
                case 6:
                    return "reportPlaybackStateEvent";
                case 7:
                    return "reportTrackChangeEvent";
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
            PlaybackMetrics _arg1;
            NetworkEvent _arg12;
            PlaybackErrorEvent _arg13;
            PlaybackStateEvent _arg14;
            TrackChangeEvent _arg15;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMediaMetricsManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMediaMetricsManager.DESCRIPTOR);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = PlaybackMetrics.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg2 = data.readInt();
                            reportPlaybackMetrics(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IMediaMetricsManager.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _result = getPlaybackSessionId(_arg02);
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 3:
                            data.enforceInterface(IMediaMetricsManager.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            String _result2 = getRecordingSessionId(_arg03);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 4:
                            data.enforceInterface(IMediaMetricsManager.DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = NetworkEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            int _arg22 = data.readInt();
                            reportNetworkEvent(_arg04, _arg12, _arg22);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IMediaMetricsManager.DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = PlaybackErrorEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            int _arg23 = data.readInt();
                            reportPlaybackErrorEvent(_arg05, _arg13, _arg23);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IMediaMetricsManager.DESCRIPTOR);
                            String _arg06 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = PlaybackStateEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            int _arg24 = data.readInt();
                            reportPlaybackStateEvent(_arg06, _arg14, _arg24);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IMediaMetricsManager.DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = TrackChangeEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            int _arg25 = data.readInt();
                            reportTrackChangeEvent(_arg07, _arg15, _arg25);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaMetricsManager {
            public static IMediaMetricsManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaMetricsManager.DESCRIPTOR;
            }

            @Override // android.media.metrics.IMediaMetricsManager
            public void reportPlaybackMetrics(String sessionId, PlaybackMetrics metrics, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaMetricsManager.DESCRIPTOR);
                    _data.writeString(sessionId);
                    if (metrics != null) {
                        _data.writeInt(1);
                        metrics.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportPlaybackMetrics(sessionId, metrics, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.metrics.IMediaMetricsManager
            public String getPlaybackSessionId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaMetricsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlaybackSessionId(userId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.metrics.IMediaMetricsManager
            public String getRecordingSessionId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaMetricsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecordingSessionId(userId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.metrics.IMediaMetricsManager
            public void reportNetworkEvent(String sessionId, NetworkEvent event, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaMetricsManager.DESCRIPTOR);
                    _data.writeString(sessionId);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportNetworkEvent(sessionId, event, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.metrics.IMediaMetricsManager
            public void reportPlaybackErrorEvent(String sessionId, PlaybackErrorEvent event, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaMetricsManager.DESCRIPTOR);
                    _data.writeString(sessionId);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportPlaybackErrorEvent(sessionId, event, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.metrics.IMediaMetricsManager
            public void reportPlaybackStateEvent(String sessionId, PlaybackStateEvent event, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaMetricsManager.DESCRIPTOR);
                    _data.writeString(sessionId);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportPlaybackStateEvent(sessionId, event, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.metrics.IMediaMetricsManager
            public void reportTrackChangeEvent(String sessionId, TrackChangeEvent event, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMediaMetricsManager.DESCRIPTOR);
                    _data.writeString(sessionId);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportTrackChangeEvent(sessionId, event, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaMetricsManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMediaMetricsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
