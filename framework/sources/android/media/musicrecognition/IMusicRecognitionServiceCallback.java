package android.media.musicrecognition;

import android.media.MediaMetadata;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IMusicRecognitionServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "android.media.musicrecognition.IMusicRecognitionServiceCallback";

    void onRecognitionFailed(int i) throws RemoteException;

    void onRecognitionSucceeded(MediaMetadata mediaMetadata, Bundle bundle) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMusicRecognitionServiceCallback {
        @Override // android.media.musicrecognition.IMusicRecognitionServiceCallback
        public void onRecognitionSucceeded(MediaMetadata result, Bundle extras) throws RemoteException {
        }

        @Override // android.media.musicrecognition.IMusicRecognitionServiceCallback
        public void onRecognitionFailed(int failureCode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMusicRecognitionServiceCallback {
        static final int TRANSACTION_onRecognitionFailed = 2;
        static final int TRANSACTION_onRecognitionSucceeded = 1;

        public Stub() {
            attachInterface(this, IMusicRecognitionServiceCallback.DESCRIPTOR);
        }

        public static IMusicRecognitionServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMusicRecognitionServiceCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMusicRecognitionServiceCallback)) {
                return new Proxy(obj);
            }
            return (IMusicRecognitionServiceCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onRecognitionSucceeded";
                case 2:
                    return "onRecognitionFailed";
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
            MediaMetadata _arg0;
            Bundle _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMusicRecognitionServiceCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMusicRecognitionServiceCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = MediaMetadata.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onRecognitionSucceeded(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IMusicRecognitionServiceCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onRecognitionFailed(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMusicRecognitionServiceCallback {
            public static IMusicRecognitionServiceCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMusicRecognitionServiceCallback.DESCRIPTOR;
            }

            @Override // android.media.musicrecognition.IMusicRecognitionServiceCallback
            public void onRecognitionSucceeded(MediaMetadata result, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMusicRecognitionServiceCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecognitionSucceeded(result, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.musicrecognition.IMusicRecognitionServiceCallback
            public void onRecognitionFailed(int failureCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMusicRecognitionServiceCallback.DESCRIPTOR);
                    _data.writeInt(failureCode);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecognitionFailed(failureCode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicRecognitionServiceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMusicRecognitionServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
