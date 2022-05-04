package android.speech.tts;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ITextToSpeechCallback extends IInterface {
    void onAudioAvailable(String str, byte[] bArr) throws RemoteException;

    void onBeginSynthesis(String str, int i, int i2, int i3) throws RemoteException;

    void onError(String str, int i) throws RemoteException;

    void onRangeStart(String str, int i, int i2, int i3) throws RemoteException;

    void onStart(String str) throws RemoteException;

    void onStop(String str, boolean z) throws RemoteException;

    void onSuccess(String str) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITextToSpeechCallback {
        @Override // android.speech.tts.ITextToSpeechCallback
        public void onStart(String utteranceId) throws RemoteException {
        }

        @Override // android.speech.tts.ITextToSpeechCallback
        public void onSuccess(String utteranceId) throws RemoteException {
        }

        @Override // android.speech.tts.ITextToSpeechCallback
        public void onStop(String utteranceId, boolean isStarted) throws RemoteException {
        }

        @Override // android.speech.tts.ITextToSpeechCallback
        public void onError(String utteranceId, int errorCode) throws RemoteException {
        }

        @Override // android.speech.tts.ITextToSpeechCallback
        public void onBeginSynthesis(String utteranceId, int sampleRateInHz, int audioFormat, int channelCount) throws RemoteException {
        }

        @Override // android.speech.tts.ITextToSpeechCallback
        public void onAudioAvailable(String utteranceId, byte[] audio) throws RemoteException {
        }

        @Override // android.speech.tts.ITextToSpeechCallback
        public void onRangeStart(String utteranceId, int start, int end, int frame) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITextToSpeechCallback {
        public static final String DESCRIPTOR = "android.speech.tts.ITextToSpeechCallback";
        static final int TRANSACTION_onAudioAvailable = 6;
        static final int TRANSACTION_onBeginSynthesis = 5;
        static final int TRANSACTION_onError = 4;
        static final int TRANSACTION_onRangeStart = 7;
        static final int TRANSACTION_onStart = 1;
        static final int TRANSACTION_onStop = 3;
        static final int TRANSACTION_onSuccess = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITextToSpeechCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITextToSpeechCallback)) {
                return new Proxy(obj);
            }
            return (ITextToSpeechCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onStart";
                case 2:
                    return "onSuccess";
                case 3:
                    return "onStop";
                case 4:
                    return "onError";
                case 5:
                    return "onBeginSynthesis";
                case 6:
                    return "onAudioAvailable";
                case 7:
                    return "onRangeStart";
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
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            onStart(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg02 = data.readString();
                            onSuccess(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            boolean _arg1 = data.readInt() != 0;
                            onStop(_arg03, _arg1);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            int _arg12 = data.readInt();
                            onError(_arg04, _arg12);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            onBeginSynthesis(_arg05, _arg13, _arg2, _arg3);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            byte[] _arg14 = data.createByteArray();
                            onAudioAvailable(_arg06, _arg14);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg15 = data.readInt();
                            int _arg22 = data.readInt();
                            int _arg32 = data.readInt();
                            onRangeStart(_arg07, _arg15, _arg22, _arg32);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITextToSpeechCallback {
            public static ITextToSpeechCallback sDefaultImpl;
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

            @Override // android.speech.tts.ITextToSpeechCallback
            public void onStart(String utteranceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(utteranceId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStart(utteranceId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.tts.ITextToSpeechCallback
            public void onSuccess(String utteranceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(utteranceId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess(utteranceId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.tts.ITextToSpeechCallback
            public void onStop(String utteranceId, boolean isStarted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(utteranceId);
                    _data.writeInt(isStarted ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStop(utteranceId, isStarted);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.tts.ITextToSpeechCallback
            public void onError(String utteranceId, int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(utteranceId);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(utteranceId, errorCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.tts.ITextToSpeechCallback
            public void onBeginSynthesis(String utteranceId, int sampleRateInHz, int audioFormat, int channelCount) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(utteranceId);
                    _data.writeInt(sampleRateInHz);
                    _data.writeInt(audioFormat);
                    _data.writeInt(channelCount);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBeginSynthesis(utteranceId, sampleRateInHz, audioFormat, channelCount);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.tts.ITextToSpeechCallback
            public void onAudioAvailable(String utteranceId, byte[] audio) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(utteranceId);
                    _data.writeByteArray(audio);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAudioAvailable(utteranceId, audio);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.tts.ITextToSpeechCallback
            public void onRangeStart(String utteranceId, int start, int end, int frame) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(utteranceId);
                    _data.writeInt(start);
                    _data.writeInt(end);
                    _data.writeInt(frame);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRangeStart(utteranceId, start, end, frame);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITextToSpeechCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITextToSpeechCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
