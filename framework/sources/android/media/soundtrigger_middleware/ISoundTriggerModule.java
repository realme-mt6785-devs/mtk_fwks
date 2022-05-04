package android.media.soundtrigger_middleware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ISoundTriggerModule extends IInterface {
    public static final String DESCRIPTOR = "android$media$soundtrigger_middleware$ISoundTriggerModule".replace('$', '.');

    void detach() throws RemoteException;

    void forceRecognitionEvent(int i) throws RemoteException;

    int getModelParameter(int i, int i2) throws RemoteException;

    int loadModel(SoundModel soundModel) throws RemoteException;

    int loadPhraseModel(PhraseSoundModel phraseSoundModel) throws RemoteException;

    ModelParameterRange queryModelParameterSupport(int i, int i2) throws RemoteException;

    void setModelParameter(int i, int i2, int i3) throws RemoteException;

    void startRecognition(int i, RecognitionConfig recognitionConfig) throws RemoteException;

    void stopRecognition(int i) throws RemoteException;

    void unloadModel(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ISoundTriggerModule {
        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public int loadModel(SoundModel model) throws RemoteException {
            return 0;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public int loadPhraseModel(PhraseSoundModel model) throws RemoteException {
            return 0;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void unloadModel(int modelHandle) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void startRecognition(int modelHandle, RecognitionConfig config) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void stopRecognition(int modelHandle) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void forceRecognitionEvent(int modelHandle) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void setModelParameter(int modelHandle, int modelParam, int value) throws RemoteException {
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public int getModelParameter(int modelHandle, int modelParam) throws RemoteException {
            return 0;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public ModelParameterRange queryModelParameterSupport(int modelHandle, int modelParam) throws RemoteException {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
        public void detach() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISoundTriggerModule {
        static final int TRANSACTION_detach = 10;
        static final int TRANSACTION_forceRecognitionEvent = 6;
        static final int TRANSACTION_getModelParameter = 8;
        static final int TRANSACTION_loadModel = 1;
        static final int TRANSACTION_loadPhraseModel = 2;
        static final int TRANSACTION_queryModelParameterSupport = 9;
        static final int TRANSACTION_setModelParameter = 7;
        static final int TRANSACTION_startRecognition = 4;
        static final int TRANSACTION_stopRecognition = 5;
        static final int TRANSACTION_unloadModel = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISoundTriggerModule asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISoundTriggerModule)) {
                return new Proxy(obj);
            }
            return (ISoundTriggerModule) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            SoundModel _arg0;
            PhraseSoundModel _arg02;
            RecognitionConfig _arg1;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = SoundModel.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _result = loadModel(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg02 = PhraseSoundModel.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _result2 = loadPhraseModel(_arg02);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg03 = data.readInt();
                            unloadModel(_arg03);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = RecognitionConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            startRecognition(_arg04, _arg1);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            int _arg05 = data.readInt();
                            stopRecognition(_arg05);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            int _arg06 = data.readInt();
                            forceRecognitionEvent(_arg06);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(descriptor);
                            int _arg07 = data.readInt();
                            int _arg12 = data.readInt();
                            int _arg2 = data.readInt();
                            setModelParameter(_arg07, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(descriptor);
                            int _arg08 = data.readInt();
                            int _arg13 = data.readInt();
                            int _result3 = getModelParameter(_arg08, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 9:
                            data.enforceInterface(descriptor);
                            int _arg09 = data.readInt();
                            int _arg14 = data.readInt();
                            ModelParameterRange _result4 = queryModelParameterSupport(_arg09, _arg14);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 10:
                            data.enforceInterface(descriptor);
                            detach();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ISoundTriggerModule {
            public static ISoundTriggerModule sDefaultImpl;
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

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public int loadModel(SoundModel model) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (model != null) {
                        _data.writeInt(1);
                        model.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadModel(model);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public int loadPhraseModel(PhraseSoundModel model) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (model != null) {
                        _data.writeInt(1);
                        model.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadPhraseModel(model);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void unloadModel(int modelHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unloadModel(modelHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void startRecognition(int modelHandle, RecognitionConfig config) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startRecognition(modelHandle, config);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void stopRecognition(int modelHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopRecognition(modelHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void forceRecognitionEvent(int modelHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().forceRecognitionEvent(modelHandle);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void setModelParameter(int modelHandle, int modelParam, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    _data.writeInt(modelParam);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setModelParameter(modelHandle, modelParam, value);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public int getModelParameter(int modelHandle, int modelParam) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    _data.writeInt(modelParam);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModelParameter(modelHandle, modelParam);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public ModelParameterRange queryModelParameterSupport(int modelHandle, int modelParam) throws RemoteException {
                ModelParameterRange _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(modelHandle);
                    _data.writeInt(modelParam);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryModelParameterSupport(modelHandle, modelParam);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ModelParameterRange.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerModule
            public void detach() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().detach();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerModule impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISoundTriggerModule getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
