package android.speech;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IRecognitionListener extends IInterface {
    void onBeginningOfSpeech() throws RemoteException;

    void onBufferReceived(byte[] bArr) throws RemoteException;

    void onEndOfSpeech() throws RemoteException;

    void onError(int i) throws RemoteException;

    void onEvent(int i, Bundle bundle) throws RemoteException;

    void onPartialResults(Bundle bundle) throws RemoteException;

    void onReadyForSpeech(Bundle bundle) throws RemoteException;

    void onResults(Bundle bundle) throws RemoteException;

    void onRmsChanged(float f) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRecognitionListener {
        @Override // android.speech.IRecognitionListener
        public void onReadyForSpeech(Bundle params) throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onBeginningOfSpeech() throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onRmsChanged(float rmsdB) throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onBufferReceived(byte[] buffer) throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onEndOfSpeech() throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onError(int error) throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onResults(Bundle results) throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onPartialResults(Bundle results) throws RemoteException {
        }

        @Override // android.speech.IRecognitionListener
        public void onEvent(int eventType, Bundle params) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRecognitionListener {
        public static final String DESCRIPTOR = "android.speech.IRecognitionListener";
        static final int TRANSACTION_onBeginningOfSpeech = 2;
        static final int TRANSACTION_onBufferReceived = 4;
        static final int TRANSACTION_onEndOfSpeech = 5;
        static final int TRANSACTION_onError = 6;
        static final int TRANSACTION_onEvent = 9;
        static final int TRANSACTION_onPartialResults = 8;
        static final int TRANSACTION_onReadyForSpeech = 1;
        static final int TRANSACTION_onResults = 7;
        static final int TRANSACTION_onRmsChanged = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecognitionListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRecognitionListener)) {
                return new Proxy(obj);
            }
            return (IRecognitionListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onReadyForSpeech";
                case 2:
                    return "onBeginningOfSpeech";
                case 3:
                    return "onRmsChanged";
                case 4:
                    return "onBufferReceived";
                case 5:
                    return "onEndOfSpeech";
                case 6:
                    return "onError";
                case 7:
                    return "onResults";
                case 8:
                    return "onPartialResults";
                case 9:
                    return "onEvent";
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
            Bundle _arg0;
            Bundle _arg02;
            Bundle _arg03;
            Bundle _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onReadyForSpeech(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            onBeginningOfSpeech();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            float _arg04 = data.readFloat();
                            onRmsChanged(_arg04);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _arg05 = data.createByteArray();
                            onBufferReceived(_arg05);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            onEndOfSpeech();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            onError(_arg06);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onResults(_arg02);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onPartialResults(_arg03);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onEvent(_arg07, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRecognitionListener {
            public static IRecognitionListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.speech.IRecognitionListener
            public void onReadyForSpeech(Bundle params) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReadyForSpeech(params);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onBeginningOfSpeech() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBeginningOfSpeech();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onRmsChanged(float rmsdB) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(rmsdB);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRmsChanged(rmsdB);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onBufferReceived(byte[] buffer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(buffer);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBufferReceived(buffer);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onEndOfSpeech() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEndOfSpeech();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onError(int error) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(error);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(error);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onResults(Bundle results) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (results != null) {
                        _data.writeInt(1);
                        results.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResults(results);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onPartialResults(Bundle results) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (results != null) {
                        _data.writeInt(1);
                        results.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPartialResults(results);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionListener
            public void onEvent(int eventType, Bundle params) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(eventType);
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEvent(eventType, params);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecognitionListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRecognitionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
