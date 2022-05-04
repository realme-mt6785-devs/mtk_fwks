package android.speech.tts;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.speech.tts.ITextToSpeechSessionCallback;
/* loaded from: classes3.dex */
public interface ITextToSpeechManager extends IInterface {
    public static final String DESCRIPTOR = "android.speech.tts.ITextToSpeechManager";

    void createSession(String str, ITextToSpeechSessionCallback iTextToSpeechSessionCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITextToSpeechManager {
        @Override // android.speech.tts.ITextToSpeechManager
        public void createSession(String engine, ITextToSpeechSessionCallback managerCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITextToSpeechManager {
        static final int TRANSACTION_createSession = 1;

        public Stub() {
            attachInterface(this, ITextToSpeechManager.DESCRIPTOR);
        }

        public static ITextToSpeechManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITextToSpeechManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITextToSpeechManager)) {
                return new Proxy(obj);
            }
            return (ITextToSpeechManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createSession";
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
                    reply.writeString(ITextToSpeechManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITextToSpeechManager.DESCRIPTOR);
                            String _arg0 = data.readString();
                            ITextToSpeechSessionCallback _arg1 = ITextToSpeechSessionCallback.Stub.asInterface(data.readStrongBinder());
                            createSession(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITextToSpeechManager {
            public static ITextToSpeechManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITextToSpeechManager.DESCRIPTOR;
            }

            @Override // android.speech.tts.ITextToSpeechManager
            public void createSession(String engine, ITextToSpeechSessionCallback managerCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITextToSpeechManager.DESCRIPTOR);
                    _data.writeString(engine);
                    _data.writeStrongBinder(managerCallback != null ? managerCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createSession(engine, managerCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITextToSpeechManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITextToSpeechManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
