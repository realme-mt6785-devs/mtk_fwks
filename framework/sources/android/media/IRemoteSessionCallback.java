package android.media;

import android.media.session.MediaSession;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IRemoteSessionCallback extends IInterface {
    public static final String DESCRIPTOR = "android.media.IRemoteSessionCallback";

    void onSessionChanged(MediaSession.Token token) throws RemoteException;

    void onVolumeChanged(MediaSession.Token token, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IRemoteSessionCallback {
        @Override // android.media.IRemoteSessionCallback
        public void onVolumeChanged(MediaSession.Token sessionToken, int flags) throws RemoteException {
        }

        @Override // android.media.IRemoteSessionCallback
        public void onSessionChanged(MediaSession.Token sessionToken) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IRemoteSessionCallback {
        static final int TRANSACTION_onSessionChanged = 2;
        static final int TRANSACTION_onVolumeChanged = 1;

        public Stub() {
            attachInterface(this, IRemoteSessionCallback.DESCRIPTOR);
        }

        public static IRemoteSessionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRemoteSessionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteSessionCallback)) {
                return new Proxy(obj);
            }
            return (IRemoteSessionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onVolumeChanged";
                case 2:
                    return "onSessionChanged";
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
            MediaSession.Token _arg0;
            MediaSession.Token _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRemoteSessionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRemoteSessionCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = MediaSession.Token.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            onVolumeChanged(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IRemoteSessionCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = MediaSession.Token.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onSessionChanged(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IRemoteSessionCallback {
            public static IRemoteSessionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRemoteSessionCallback.DESCRIPTOR;
            }

            @Override // android.media.IRemoteSessionCallback
            public void onVolumeChanged(MediaSession.Token sessionToken, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRemoteSessionCallback.DESCRIPTOR);
                    if (sessionToken != null) {
                        _data.writeInt(1);
                        sessionToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVolumeChanged(sessionToken, flags);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IRemoteSessionCallback
            public void onSessionChanged(MediaSession.Token sessionToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRemoteSessionCallback.DESCRIPTOR);
                    if (sessionToken != null) {
                        _data.writeInt(1);
                        sessionToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSessionChanged(sessionToken);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteSessionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRemoteSessionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
