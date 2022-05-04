package com.android.internal.app;

import android.hardware.soundtrigger.SoundTrigger;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.voice.HotwordDetectedResult;
import android.service.voice.HotwordRejectedResult;
/* loaded from: classes4.dex */
public interface IHotwordRecognitionStatusCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.app.IHotwordRecognitionStatusCallback";

    void onError(int i) throws RemoteException;

    void onGenericSoundTriggerDetected(SoundTrigger.GenericRecognitionEvent genericRecognitionEvent) throws RemoteException;

    void onKeyphraseDetected(SoundTrigger.KeyphraseRecognitionEvent keyphraseRecognitionEvent, HotwordDetectedResult hotwordDetectedResult) throws RemoteException;

    void onProcessRestarted() throws RemoteException;

    void onRecognitionPaused() throws RemoteException;

    void onRecognitionResumed() throws RemoteException;

    void onRejected(HotwordRejectedResult hotwordRejectedResult) throws RemoteException;

    void onStatusReported(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IHotwordRecognitionStatusCallback {
        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onKeyphraseDetected(SoundTrigger.KeyphraseRecognitionEvent recognitionEvent, HotwordDetectedResult result) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onGenericSoundTriggerDetected(SoundTrigger.GenericRecognitionEvent recognitionEvent) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onRejected(HotwordRejectedResult result) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onError(int status) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onRecognitionPaused() throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onRecognitionResumed() throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onStatusReported(int status) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onProcessRestarted() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IHotwordRecognitionStatusCallback {
        static final int TRANSACTION_onError = 4;
        static final int TRANSACTION_onGenericSoundTriggerDetected = 2;
        static final int TRANSACTION_onKeyphraseDetected = 1;
        static final int TRANSACTION_onProcessRestarted = 8;
        static final int TRANSACTION_onRecognitionPaused = 5;
        static final int TRANSACTION_onRecognitionResumed = 6;
        static final int TRANSACTION_onRejected = 3;
        static final int TRANSACTION_onStatusReported = 7;

        public Stub() {
            attachInterface(this, IHotwordRecognitionStatusCallback.DESCRIPTOR);
        }

        public static IHotwordRecognitionStatusCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IHotwordRecognitionStatusCallback)) {
                return new Proxy(obj);
            }
            return (IHotwordRecognitionStatusCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onKeyphraseDetected";
                case 2:
                    return "onGenericSoundTriggerDetected";
                case 3:
                    return "onRejected";
                case 4:
                    return "onError";
                case 5:
                    return "onRecognitionPaused";
                case 6:
                    return "onRecognitionResumed";
                case 7:
                    return "onStatusReported";
                case 8:
                    return "onProcessRestarted";
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
            SoundTrigger.KeyphraseRecognitionEvent _arg0;
            HotwordDetectedResult _arg1;
            SoundTrigger.GenericRecognitionEvent _arg02;
            HotwordRejectedResult _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SoundTrigger.KeyphraseRecognitionEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = HotwordDetectedResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onKeyphraseDetected(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = SoundTrigger.GenericRecognitionEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onGenericSoundTriggerDetected(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = HotwordRejectedResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onRejected(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            onError(_arg04);
                            return true;
                        case 5:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            onRecognitionPaused();
                            return true;
                        case 6:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            onRecognitionResumed();
                            return true;
                        case 7:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            onStatusReported(_arg05);
                            return true;
                        case 8:
                            data.enforceInterface(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                            onProcessRestarted();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IHotwordRecognitionStatusCallback {
            public static IHotwordRecognitionStatusCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IHotwordRecognitionStatusCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onKeyphraseDetected(SoundTrigger.KeyphraseRecognitionEvent recognitionEvent, HotwordDetectedResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    if (recognitionEvent != null) {
                        _data.writeInt(1);
                        recognitionEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onKeyphraseDetected(recognitionEvent, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onGenericSoundTriggerDetected(SoundTrigger.GenericRecognitionEvent recognitionEvent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    if (recognitionEvent != null) {
                        _data.writeInt(1);
                        recognitionEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGenericSoundTriggerDetected(recognitionEvent);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onRejected(HotwordRejectedResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRejected(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onError(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onError(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onRecognitionPaused() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecognitionPaused();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onRecognitionResumed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecognitionResumed();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onStatusReported(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStatusReported(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
            public void onProcessRestarted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordRecognitionStatusCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProcessRestarted();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHotwordRecognitionStatusCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IHotwordRecognitionStatusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
