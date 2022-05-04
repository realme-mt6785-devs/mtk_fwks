package com.android.internal.app;

import android.content.ComponentName;
import android.hardware.soundtrigger.IRecognitionStatusCallback;
import android.hardware.soundtrigger.SoundTrigger;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface ISoundTriggerSession extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.app.ISoundTriggerSession";

    void deleteSoundModel(ParcelUuid parcelUuid) throws RemoteException;

    int getModelState(ParcelUuid parcelUuid) throws RemoteException;

    SoundTrigger.ModuleProperties getModuleProperties() throws RemoteException;

    int getParameter(ParcelUuid parcelUuid, int i) throws RemoteException;

    SoundTrigger.GenericSoundModel getSoundModel(ParcelUuid parcelUuid) throws RemoteException;

    boolean isRecognitionActive(ParcelUuid parcelUuid) throws RemoteException;

    int loadGenericSoundModel(SoundTrigger.GenericSoundModel genericSoundModel) throws RemoteException;

    int loadKeyphraseSoundModel(SoundTrigger.KeyphraseSoundModel keyphraseSoundModel) throws RemoteException;

    SoundTrigger.ModelParamRange queryParameter(ParcelUuid parcelUuid, int i) throws RemoteException;

    int setParameter(ParcelUuid parcelUuid, int i, int i2) throws RemoteException;

    int startRecognition(ParcelUuid parcelUuid, IRecognitionStatusCallback iRecognitionStatusCallback, SoundTrigger.RecognitionConfig recognitionConfig, boolean z) throws RemoteException;

    int startRecognitionForService(ParcelUuid parcelUuid, Bundle bundle, ComponentName componentName, SoundTrigger.RecognitionConfig recognitionConfig) throws RemoteException;

    int stopRecognition(ParcelUuid parcelUuid, IRecognitionStatusCallback iRecognitionStatusCallback) throws RemoteException;

    int stopRecognitionForService(ParcelUuid parcelUuid) throws RemoteException;

    int unloadSoundModel(ParcelUuid parcelUuid) throws RemoteException;

    void updateSoundModel(SoundTrigger.GenericSoundModel genericSoundModel) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ISoundTriggerSession {
        @Override // com.android.internal.app.ISoundTriggerSession
        public SoundTrigger.GenericSoundModel getSoundModel(ParcelUuid soundModelId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public void updateSoundModel(SoundTrigger.GenericSoundModel soundModel) throws RemoteException {
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public void deleteSoundModel(ParcelUuid soundModelId) throws RemoteException {
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int startRecognition(ParcelUuid soundModelId, IRecognitionStatusCallback callback, SoundTrigger.RecognitionConfig config, boolean runInBatterySaver) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int stopRecognition(ParcelUuid soundModelId, IRecognitionStatusCallback callback) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int loadGenericSoundModel(SoundTrigger.GenericSoundModel soundModel) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int loadKeyphraseSoundModel(SoundTrigger.KeyphraseSoundModel soundModel) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int startRecognitionForService(ParcelUuid soundModelId, Bundle params, ComponentName callbackIntent, SoundTrigger.RecognitionConfig config) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int stopRecognitionForService(ParcelUuid soundModelId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int unloadSoundModel(ParcelUuid soundModelId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public boolean isRecognitionActive(ParcelUuid parcelUuid) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int getModelState(ParcelUuid soundModelId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public SoundTrigger.ModuleProperties getModuleProperties() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int setParameter(ParcelUuid soundModelId, int modelParam, int value) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public int getParameter(ParcelUuid soundModelId, int modelParam) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.ISoundTriggerSession
        public SoundTrigger.ModelParamRange queryParameter(ParcelUuid soundModelId, int modelParam) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ISoundTriggerSession {
        static final int TRANSACTION_deleteSoundModel = 3;
        static final int TRANSACTION_getModelState = 12;
        static final int TRANSACTION_getModuleProperties = 13;
        static final int TRANSACTION_getParameter = 15;
        static final int TRANSACTION_getSoundModel = 1;
        static final int TRANSACTION_isRecognitionActive = 11;
        static final int TRANSACTION_loadGenericSoundModel = 6;
        static final int TRANSACTION_loadKeyphraseSoundModel = 7;
        static final int TRANSACTION_queryParameter = 16;
        static final int TRANSACTION_setParameter = 14;
        static final int TRANSACTION_startRecognition = 4;
        static final int TRANSACTION_startRecognitionForService = 8;
        static final int TRANSACTION_stopRecognition = 5;
        static final int TRANSACTION_stopRecognitionForService = 9;
        static final int TRANSACTION_unloadSoundModel = 10;
        static final int TRANSACTION_updateSoundModel = 2;

        public Stub() {
            attachInterface(this, ISoundTriggerSession.DESCRIPTOR);
        }

        public static ISoundTriggerSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISoundTriggerSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISoundTriggerSession)) {
                return new Proxy(obj);
            }
            return (ISoundTriggerSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getSoundModel";
                case 2:
                    return "updateSoundModel";
                case 3:
                    return "deleteSoundModel";
                case 4:
                    return "startRecognition";
                case 5:
                    return "stopRecognition";
                case 6:
                    return "loadGenericSoundModel";
                case 7:
                    return "loadKeyphraseSoundModel";
                case 8:
                    return "startRecognitionForService";
                case 9:
                    return "stopRecognitionForService";
                case 10:
                    return "unloadSoundModel";
                case 11:
                    return "isRecognitionActive";
                case 12:
                    return "getModelState";
                case 13:
                    return "getModuleProperties";
                case 14:
                    return "setParameter";
                case 15:
                    return "getParameter";
                case 16:
                    return "queryParameter";
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
            ParcelUuid _arg0;
            SoundTrigger.GenericSoundModel _arg02;
            ParcelUuid _arg03;
            ParcelUuid _arg04;
            SoundTrigger.RecognitionConfig _arg2;
            ParcelUuid _arg05;
            SoundTrigger.GenericSoundModel _arg06;
            SoundTrigger.KeyphraseSoundModel _arg07;
            ParcelUuid _arg08;
            Bundle _arg1;
            ComponentName _arg22;
            SoundTrigger.RecognitionConfig _arg3;
            ParcelUuid _arg09;
            ParcelUuid _arg010;
            ParcelUuid _arg011;
            ParcelUuid _arg012;
            ParcelUuid _arg013;
            ParcelUuid _arg014;
            ParcelUuid _arg015;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISoundTriggerSession.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg32 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            SoundTrigger.GenericSoundModel _result = getSoundModel(_arg0);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SoundTrigger.GenericSoundModel.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            updateSoundModel(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            deleteSoundModel(_arg03);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            IRecognitionStatusCallback _arg12 = IRecognitionStatusCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg2 = SoundTrigger.RecognitionConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = true;
                            }
                            int _result2 = startRecognition(_arg04, _arg12, _arg2, _arg32);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            IRecognitionStatusCallback _arg13 = IRecognitionStatusCallback.Stub.asInterface(data.readStrongBinder());
                            int _result3 = stopRecognition(_arg05, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 6:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = SoundTrigger.GenericSoundModel.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            int _result4 = loadGenericSoundModel(_arg06);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = SoundTrigger.KeyphraseSoundModel.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            int _result5 = loadKeyphraseSoundModel(_arg07);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 8:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = SoundTrigger.RecognitionConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            int _result6 = startRecognitionForService(_arg08, _arg1, _arg22, _arg3);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 9:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            int _result7 = stopRecognitionForService(_arg09);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 10:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            int _result8 = unloadSoundModel(_arg010);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 11:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg011 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            boolean isRecognitionActive = isRecognitionActive(_arg011);
                            reply.writeNoException();
                            reply.writeInt(isRecognitionActive ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg012 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg012 = null;
                            }
                            int _result9 = getModelState(_arg012);
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 13:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            SoundTrigger.ModuleProperties _result10 = getModuleProperties();
                            reply.writeNoException();
                            if (_result10 != null) {
                                reply.writeInt(1);
                                _result10.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 14:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg013 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg013 = null;
                            }
                            int _arg14 = data.readInt();
                            int _arg23 = data.readInt();
                            int _result11 = setParameter(_arg013, _arg14, _arg23);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 15:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg014 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg014 = null;
                            }
                            int _arg15 = data.readInt();
                            int _result12 = getParameter(_arg014, _arg15);
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 16:
                            data.enforceInterface(ISoundTriggerSession.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg015 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg015 = null;
                            }
                            int _arg16 = data.readInt();
                            SoundTrigger.ModelParamRange _result13 = queryParameter(_arg015, _arg16);
                            reply.writeNoException();
                            if (_result13 != null) {
                                reply.writeInt(1);
                                _result13.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ISoundTriggerSession {
            public static ISoundTriggerSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISoundTriggerSession.DESCRIPTOR;
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public SoundTrigger.GenericSoundModel getSoundModel(ParcelUuid soundModelId) throws RemoteException {
                SoundTrigger.GenericSoundModel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSoundModel(soundModelId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = SoundTrigger.GenericSoundModel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public void updateSoundModel(SoundTrigger.GenericSoundModel soundModel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModel != null) {
                        _data.writeInt(1);
                        soundModel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateSoundModel(soundModel);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public void deleteSoundModel(ParcelUuid soundModelId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteSoundModel(soundModelId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int startRecognition(ParcelUuid soundModelId, IRecognitionStatusCallback callback, SoundTrigger.RecognitionConfig config, boolean runInBatterySaver) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    int i = 1;
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!runInBatterySaver) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startRecognition(soundModelId, callback, config, runInBatterySaver);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int stopRecognition(ParcelUuid soundModelId, IRecognitionStatusCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopRecognition(soundModelId, callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int loadGenericSoundModel(SoundTrigger.GenericSoundModel soundModel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModel != null) {
                        _data.writeInt(1);
                        soundModel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadGenericSoundModel(soundModel);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int loadKeyphraseSoundModel(SoundTrigger.KeyphraseSoundModel soundModel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModel != null) {
                        _data.writeInt(1);
                        soundModel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadKeyphraseSoundModel(soundModel);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int startRecognitionForService(ParcelUuid soundModelId, Bundle params, ComponentName callbackIntent, SoundTrigger.RecognitionConfig config) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (params != null) {
                        _data.writeInt(1);
                        params.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (callbackIntent != null) {
                        _data.writeInt(1);
                        callbackIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startRecognitionForService(soundModelId, params, callbackIntent, config);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int stopRecognitionForService(ParcelUuid soundModelId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopRecognitionForService(soundModelId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int unloadSoundModel(ParcelUuid soundModelId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unloadSoundModel(soundModelId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public boolean isRecognitionActive(ParcelUuid parcelUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    boolean _result = true;
                    if (parcelUuid != null) {
                        _data.writeInt(1);
                        parcelUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRecognitionActive(parcelUuid);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int getModelState(ParcelUuid soundModelId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModelState(soundModelId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public SoundTrigger.ModuleProperties getModuleProperties() throws RemoteException {
                SoundTrigger.ModuleProperties _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModuleProperties();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = SoundTrigger.ModuleProperties.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int setParameter(ParcelUuid soundModelId, int modelParam, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(modelParam);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameter(soundModelId, modelParam, value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public int getParameter(ParcelUuid soundModelId, int modelParam) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(modelParam);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParameter(soundModelId, modelParam);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.ISoundTriggerSession
            public SoundTrigger.ModelParamRange queryParameter(ParcelUuid soundModelId, int modelParam) throws RemoteException {
                SoundTrigger.ModelParamRange _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISoundTriggerSession.DESCRIPTOR);
                    if (soundModelId != null) {
                        _data.writeInt(1);
                        soundModelId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(modelParam);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryParameter(soundModelId, modelParam);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = SoundTrigger.ModelParamRange.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISoundTriggerSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
