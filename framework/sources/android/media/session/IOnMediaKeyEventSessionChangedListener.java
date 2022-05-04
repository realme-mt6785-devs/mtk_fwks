package android.media.session;

import android.media.session.MediaSession;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IOnMediaKeyEventSessionChangedListener extends IInterface {
    public static final String DESCRIPTOR = "android.media.session.IOnMediaKeyEventSessionChangedListener";

    void onMediaKeyEventSessionChanged(String str, MediaSession.Token token) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IOnMediaKeyEventSessionChangedListener {
        @Override // android.media.session.IOnMediaKeyEventSessionChangedListener
        public void onMediaKeyEventSessionChanged(String packageName, MediaSession.Token mediaKeyEventSessionToken) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOnMediaKeyEventSessionChangedListener {
        static final int TRANSACTION_onMediaKeyEventSessionChanged = 1;

        public Stub() {
            attachInterface(this, IOnMediaKeyEventSessionChangedListener.DESCRIPTOR);
        }

        public static IOnMediaKeyEventSessionChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOnMediaKeyEventSessionChangedListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOnMediaKeyEventSessionChangedListener)) {
                return new Proxy(obj);
            }
            return (IOnMediaKeyEventSessionChangedListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onMediaKeyEventSessionChanged";
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
            MediaSession.Token _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOnMediaKeyEventSessionChangedListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOnMediaKeyEventSessionChangedListener.DESCRIPTOR);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = MediaSession.Token.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onMediaKeyEventSessionChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOnMediaKeyEventSessionChangedListener {
            public static IOnMediaKeyEventSessionChangedListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnMediaKeyEventSessionChangedListener.DESCRIPTOR;
            }

            @Override // android.media.session.IOnMediaKeyEventSessionChangedListener
            public void onMediaKeyEventSessionChanged(String packageName, MediaSession.Token mediaKeyEventSessionToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOnMediaKeyEventSessionChangedListener.DESCRIPTOR);
                    _data.writeString(packageName);
                    if (mediaKeyEventSessionToken != null) {
                        _data.writeInt(1);
                        mediaKeyEventSessionToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaKeyEventSessionChanged(packageName, mediaKeyEventSessionToken);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOnMediaKeyEventSessionChangedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOnMediaKeyEventSessionChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
