package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IAudioModeDispatcher extends IInterface {
    public static final String DESCRIPTOR = "android.media.IAudioModeDispatcher";

    void dispatchAudioModeChanged(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IAudioModeDispatcher {
        @Override // android.media.IAudioModeDispatcher
        public void dispatchAudioModeChanged(int mode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IAudioModeDispatcher {
        static final int TRANSACTION_dispatchAudioModeChanged = 1;

        public Stub() {
            attachInterface(this, IAudioModeDispatcher.DESCRIPTOR);
        }

        public static IAudioModeDispatcher asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAudioModeDispatcher.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAudioModeDispatcher)) {
                return new Proxy(obj);
            }
            return (IAudioModeDispatcher) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "dispatchAudioModeChanged";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IAudioModeDispatcher.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAudioModeDispatcher.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            dispatchAudioModeChanged(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IAudioModeDispatcher {
            public static IAudioModeDispatcher sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAudioModeDispatcher.DESCRIPTOR;
            }

            @Override // android.media.IAudioModeDispatcher
            public void dispatchAudioModeChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAudioModeDispatcher.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchAudioModeChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAudioModeDispatcher impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAudioModeDispatcher getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
