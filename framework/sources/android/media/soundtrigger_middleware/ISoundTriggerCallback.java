package android.media.soundtrigger_middleware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ISoundTriggerCallback extends IInterface {
    public static final String DESCRIPTOR = "android$media$soundtrigger_middleware$ISoundTriggerCallback".replace('$', '.');

    void onModuleDied() throws RemoteException;

    void onPhraseRecognition(int i, PhraseRecognitionEvent phraseRecognitionEvent) throws RemoteException;

    void onRecognition(int i, RecognitionEvent recognitionEvent) throws RemoteException;

    void onRecognitionAvailabilityChange(boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ISoundTriggerCallback {
        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onRecognition(int modelHandle, RecognitionEvent event) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onPhraseRecognition(int modelHandle, PhraseRecognitionEvent event) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onRecognitionAvailabilityChange(boolean available) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
        public void onModuleDied() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISoundTriggerCallback {
        static final int TRANSACTION_onModuleDied = 4;
        static final int TRANSACTION_onPhraseRecognition = 2;
        static final int TRANSACTION_onRecognition = 1;
        static final int TRANSACTION_onRecognitionAvailabilityChange = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISoundTriggerCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISoundTriggerCallback)) {
                return new Proxy(obj);
            }
            return (ISoundTriggerCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RecognitionEvent _arg1;
            PhraseRecognitionEvent _arg12;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = RecognitionEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onRecognition(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = PhraseRecognitionEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onPhraseRecognition(_arg02, _arg12);
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            boolean _arg03 = data.readInt() != 0;
                            onRecognitionAvailabilityChange(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            onModuleDied();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ISoundTriggerCallback {
            public static ISoundTriggerCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onRecognition(int modelHandle, RecognitionEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecognition(modelHandle, event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onPhraseRecognition(int modelHandle, PhraseRecognitionEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPhraseRecognition(modelHandle, event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onRecognitionAvailabilityChange(boolean available) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(available ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecognitionAvailabilityChange(available);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerCallback
            public void onModuleDied() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onModuleDied();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISoundTriggerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
