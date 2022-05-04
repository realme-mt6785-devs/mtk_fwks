package android.media.session;

import android.media.session.MediaSession;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;
/* loaded from: classes2.dex */
public interface IOnMediaKeyEventDispatchedListener extends IInterface {
    public static final String DESCRIPTOR = "android.media.session.IOnMediaKeyEventDispatchedListener";

    void onMediaKeyEventDispatched(KeyEvent keyEvent, String str, MediaSession.Token token) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IOnMediaKeyEventDispatchedListener {
        @Override // android.media.session.IOnMediaKeyEventDispatchedListener
        public void onMediaKeyEventDispatched(KeyEvent event, String packageName, MediaSession.Token sessionToken) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOnMediaKeyEventDispatchedListener {
        static final int TRANSACTION_onMediaKeyEventDispatched = 1;

        public Stub() {
            attachInterface(this, IOnMediaKeyEventDispatchedListener.DESCRIPTOR);
        }

        public static IOnMediaKeyEventDispatchedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOnMediaKeyEventDispatchedListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOnMediaKeyEventDispatchedListener)) {
                return new Proxy(obj);
            }
            return (IOnMediaKeyEventDispatchedListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onMediaKeyEventDispatched";
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
            KeyEvent _arg0;
            MediaSession.Token _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOnMediaKeyEventDispatchedListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOnMediaKeyEventDispatchedListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = KeyEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg1 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = MediaSession.Token.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onMediaKeyEventDispatched(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOnMediaKeyEventDispatchedListener {
            public static IOnMediaKeyEventDispatchedListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnMediaKeyEventDispatchedListener.DESCRIPTOR;
            }

            @Override // android.media.session.IOnMediaKeyEventDispatchedListener
            public void onMediaKeyEventDispatched(KeyEvent event, String packageName, MediaSession.Token sessionToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOnMediaKeyEventDispatchedListener.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    if (sessionToken != null) {
                        _data.writeInt(1);
                        sessionToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaKeyEventDispatched(event, packageName, sessionToken);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOnMediaKeyEventDispatchedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOnMediaKeyEventDispatchedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
