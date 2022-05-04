package com.android.internal.app;

import android.hardware.soundtrigger.SoundTrigger;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.app.IHotwordRecognitionStatusCallback;
/* loaded from: classes4.dex */
public interface IVoiceInteractionSoundTriggerSession extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractionSoundTriggerSession";

    SoundTrigger.ModuleProperties getDspModuleProperties() throws RemoteException;

    int getParameter(int i, int i2) throws RemoteException;

    SoundTrigger.ModelParamRange queryParameter(int i, int i2) throws RemoteException;

    int setParameter(int i, int i2, int i3) throws RemoteException;

    int startRecognition(int i, String str, IHotwordRecognitionStatusCallback iHotwordRecognitionStatusCallback, SoundTrigger.RecognitionConfig recognitionConfig, boolean z) throws RemoteException;

    int stopRecognition(int i, IHotwordRecognitionStatusCallback iHotwordRecognitionStatusCallback) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IVoiceInteractionSoundTriggerSession {
        @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
        public SoundTrigger.ModuleProperties getDspModuleProperties() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
        public int startRecognition(int keyphraseId, String bcp47Locale, IHotwordRecognitionStatusCallback callback, SoundTrigger.RecognitionConfig recognitionConfig, boolean runInBatterySaver) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
        public int stopRecognition(int keyphraseId, IHotwordRecognitionStatusCallback callback) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
        public int setParameter(int keyphraseId, int modelParam, int value) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
        public int getParameter(int keyphraseId, int modelParam) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
        public SoundTrigger.ModelParamRange queryParameter(int keyphraseId, int modelParam) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IVoiceInteractionSoundTriggerSession {
        static final int TRANSACTION_getDspModuleProperties = 1;
        static final int TRANSACTION_getParameter = 5;
        static final int TRANSACTION_queryParameter = 6;
        static final int TRANSACTION_setParameter = 4;
        static final int TRANSACTION_startRecognition = 2;
        static final int TRANSACTION_stopRecognition = 3;

        public Stub() {
            attachInterface(this, IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
        }

        public static IVoiceInteractionSoundTriggerSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof IVoiceInteractionSoundTriggerSession)) {
                return new Proxy(obj);
            }
            return (IVoiceInteractionSoundTriggerSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getDspModuleProperties";
                case 2:
                    return "startRecognition";
                case 3:
                    return "stopRecognition";
                case 4:
                    return "setParameter";
                case 5:
                    return "getParameter";
                case 6:
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
            SoundTrigger.RecognitionConfig _arg3;
            boolean _arg4;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                            SoundTrigger.ModuleProperties _result = getDspModuleProperties();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            String _arg1 = data.readString();
                            IHotwordRecognitionStatusCallback _arg2 = IHotwordRecognitionStatusCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg3 = SoundTrigger.RecognitionConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            int _result2 = startRecognition(_arg0, _arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            IHotwordRecognitionStatusCallback _arg12 = IHotwordRecognitionStatusCallback.Stub.asInterface(data.readStrongBinder());
                            int _result3 = stopRecognition(_arg02, _arg12);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _arg13 = data.readInt();
                            int _arg22 = data.readInt();
                            int _result4 = setParameter(_arg03, _arg13, _arg22);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg14 = data.readInt();
                            int _result5 = getParameter(_arg04, _arg14);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 6:
                            data.enforceInterface(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _arg15 = data.readInt();
                            SoundTrigger.ModelParamRange _result6 = queryParameter(_arg05, _arg15);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
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
        public static class Proxy implements IVoiceInteractionSoundTriggerSession {
            public static IVoiceInteractionSoundTriggerSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IVoiceInteractionSoundTriggerSession.DESCRIPTOR;
            }

            @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
            public SoundTrigger.ModuleProperties getDspModuleProperties() throws RemoteException {
                SoundTrigger.ModuleProperties _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDspModuleProperties();
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

            @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
            public int startRecognition(int keyphraseId, String bcp47Locale, IHotwordRecognitionStatusCallback callback, SoundTrigger.RecognitionConfig recognitionConfig, boolean runInBatterySaver) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                    _data.writeInt(keyphraseId);
                    _data.writeString(bcp47Locale);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    int i = 1;
                    if (recognitionConfig != null) {
                        _data.writeInt(1);
                        recognitionConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!runInBatterySaver) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startRecognition(keyphraseId, bcp47Locale, callback, recognitionConfig, runInBatterySaver);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
            public int stopRecognition(int keyphraseId, IHotwordRecognitionStatusCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                    _data.writeInt(keyphraseId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopRecognition(keyphraseId, callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
            public int setParameter(int keyphraseId, int modelParam, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                    _data.writeInt(keyphraseId);
                    _data.writeInt(modelParam);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameter(keyphraseId, modelParam, value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
            public int getParameter(int keyphraseId, int modelParam) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                    _data.writeInt(keyphraseId);
                    _data.writeInt(modelParam);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParameter(keyphraseId, modelParam);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IVoiceInteractionSoundTriggerSession
            public SoundTrigger.ModelParamRange queryParameter(int keyphraseId, int modelParam) throws RemoteException {
                SoundTrigger.ModelParamRange _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVoiceInteractionSoundTriggerSession.DESCRIPTOR);
                    _data.writeInt(keyphraseId);
                    _data.writeInt(modelParam);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryParameter(keyphraseId, modelParam);
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

        public static boolean setDefaultImpl(IVoiceInteractionSoundTriggerSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVoiceInteractionSoundTriggerSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
