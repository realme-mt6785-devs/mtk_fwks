package android.media.musicrecognition;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IMusicRecognitionManager extends IInterface {
    public static final String DESCRIPTOR = "android.media.musicrecognition.IMusicRecognitionManager";

    void beginRecognition(RecognitionRequest recognitionRequest, IBinder iBinder) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMusicRecognitionManager {
        @Override // android.media.musicrecognition.IMusicRecognitionManager
        public void beginRecognition(RecognitionRequest recognitionRequest, IBinder callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMusicRecognitionManager {
        static final int TRANSACTION_beginRecognition = 1;

        public Stub() {
            attachInterface(this, IMusicRecognitionManager.DESCRIPTOR);
        }

        public static IMusicRecognitionManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMusicRecognitionManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMusicRecognitionManager)) {
                return new Proxy(obj);
            }
            return (IMusicRecognitionManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "beginRecognition";
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
            RecognitionRequest _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMusicRecognitionManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMusicRecognitionManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RecognitionRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IBinder _arg1 = data.readStrongBinder();
                            beginRecognition(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMusicRecognitionManager {
            public static IMusicRecognitionManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMusicRecognitionManager.DESCRIPTOR;
            }

            @Override // android.media.musicrecognition.IMusicRecognitionManager
            public void beginRecognition(RecognitionRequest recognitionRequest, IBinder callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMusicRecognitionManager.DESCRIPTOR);
                    if (recognitionRequest != null) {
                        _data.writeInt(1);
                        recognitionRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().beginRecognition(recognitionRequest, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicRecognitionManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMusicRecognitionManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
