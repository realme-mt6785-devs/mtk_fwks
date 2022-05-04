package android.service.voice;

import android.content.ContentCaptureOptions;
import android.hardware.soundtrigger.SoundTrigger;
import android.media.AudioFormat;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.service.voice.IDspHotwordDetectionCallback;
import android.view.contentcapture.IContentCaptureManager;
/* loaded from: classes3.dex */
public interface IHotwordDetectionService extends IInterface {
    public static final String DESCRIPTOR = "android.service.voice.IHotwordDetectionService";

    void detectFromDspSource(SoundTrigger.KeyphraseRecognitionEvent keyphraseRecognitionEvent, AudioFormat audioFormat, long j, IDspHotwordDetectionCallback iDspHotwordDetectionCallback) throws RemoteException;

    void detectFromMicrophoneSource(ParcelFileDescriptor parcelFileDescriptor, int i, AudioFormat audioFormat, PersistableBundle persistableBundle, IDspHotwordDetectionCallback iDspHotwordDetectionCallback) throws RemoteException;

    void ping(IRemoteCallback iRemoteCallback) throws RemoteException;

    void stopDetection() throws RemoteException;

    void updateAudioFlinger(IBinder iBinder) throws RemoteException;

    void updateContentCaptureManager(IContentCaptureManager iContentCaptureManager, ContentCaptureOptions contentCaptureOptions) throws RemoteException;

    void updateState(PersistableBundle persistableBundle, SharedMemory sharedMemory, IRemoteCallback iRemoteCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IHotwordDetectionService {
        @Override // android.service.voice.IHotwordDetectionService
        public void detectFromDspSource(SoundTrigger.KeyphraseRecognitionEvent event, AudioFormat audioFormat, long timeoutMillis, IDspHotwordDetectionCallback callback) throws RemoteException {
        }

        @Override // android.service.voice.IHotwordDetectionService
        public void detectFromMicrophoneSource(ParcelFileDescriptor audioStream, int audioSource, AudioFormat audioFormat, PersistableBundle options, IDspHotwordDetectionCallback callback) throws RemoteException {
        }

        @Override // android.service.voice.IHotwordDetectionService
        public void updateState(PersistableBundle options, SharedMemory sharedMemory, IRemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.voice.IHotwordDetectionService
        public void updateAudioFlinger(IBinder audioFlinger) throws RemoteException {
        }

        @Override // android.service.voice.IHotwordDetectionService
        public void updateContentCaptureManager(IContentCaptureManager contentCaptureManager, ContentCaptureOptions options) throws RemoteException {
        }

        @Override // android.service.voice.IHotwordDetectionService
        public void ping(IRemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.voice.IHotwordDetectionService
        public void stopDetection() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IHotwordDetectionService {
        static final int TRANSACTION_detectFromDspSource = 1;
        static final int TRANSACTION_detectFromMicrophoneSource = 2;
        static final int TRANSACTION_ping = 6;
        static final int TRANSACTION_stopDetection = 7;
        static final int TRANSACTION_updateAudioFlinger = 4;
        static final int TRANSACTION_updateContentCaptureManager = 5;
        static final int TRANSACTION_updateState = 3;

        public Stub() {
            attachInterface(this, IHotwordDetectionService.DESCRIPTOR);
        }

        public static IHotwordDetectionService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IHotwordDetectionService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IHotwordDetectionService)) {
                return new Proxy(obj);
            }
            return (IHotwordDetectionService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "detectFromDspSource";
                case 2:
                    return "detectFromMicrophoneSource";
                case 3:
                    return "updateState";
                case 4:
                    return "updateAudioFlinger";
                case 5:
                    return "updateContentCaptureManager";
                case 6:
                    return "ping";
                case 7:
                    return "stopDetection";
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
            AudioFormat _arg1;
            ParcelFileDescriptor _arg02;
            AudioFormat _arg2;
            PersistableBundle _arg3;
            PersistableBundle _arg03;
            SharedMemory _arg12;
            ContentCaptureOptions _arg13;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IHotwordDetectionService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IHotwordDetectionService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SoundTrigger.KeyphraseRecognitionEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = AudioFormat.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            long _arg22 = data.readLong();
                            IDspHotwordDetectionCallback _arg32 = IDspHotwordDetectionCallback.Stub.asInterface(data.readStrongBinder());
                            detectFromDspSource(_arg0, _arg1, _arg22, _arg32);
                            return true;
                        case 2:
                            data.enforceInterface(IHotwordDetectionService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _arg14 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = AudioFormat.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            IDspHotwordDetectionCallback _arg4 = IDspHotwordDetectionCallback.Stub.asInterface(data.readStrongBinder());
                            detectFromMicrophoneSource(_arg02, _arg14, _arg2, _arg3, _arg4);
                            return true;
                        case 3:
                            data.enforceInterface(IHotwordDetectionService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = PersistableBundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = SharedMemory.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            IRemoteCallback _arg23 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            updateState(_arg03, _arg12, _arg23);
                            return true;
                        case 4:
                            data.enforceInterface(IHotwordDetectionService.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            updateAudioFlinger(_arg04);
                            return true;
                        case 5:
                            data.enforceInterface(IHotwordDetectionService.DESCRIPTOR);
                            IContentCaptureManager _arg05 = IContentCaptureManager.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg13 = ContentCaptureOptions.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            updateContentCaptureManager(_arg05, _arg13);
                            return true;
                        case 6:
                            data.enforceInterface(IHotwordDetectionService.DESCRIPTOR);
                            IRemoteCallback _arg06 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            ping(_arg06);
                            return true;
                        case 7:
                            data.enforceInterface(IHotwordDetectionService.DESCRIPTOR);
                            stopDetection();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IHotwordDetectionService {
            public static IHotwordDetectionService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IHotwordDetectionService.DESCRIPTOR;
            }

            @Override // android.service.voice.IHotwordDetectionService
            public void detectFromDspSource(SoundTrigger.KeyphraseRecognitionEvent event, AudioFormat audioFormat, long timeoutMillis, IDspHotwordDetectionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordDetectionService.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (audioFormat != null) {
                        _data.writeInt(1);
                        audioFormat.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(timeoutMillis);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().detectFromDspSource(event, audioFormat, timeoutMillis, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.voice.IHotwordDetectionService
            public void detectFromMicrophoneSource(ParcelFileDescriptor audioStream, int audioSource, AudioFormat audioFormat, PersistableBundle options, IDspHotwordDetectionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordDetectionService.DESCRIPTOR);
                    if (audioStream != null) {
                        _data.writeInt(1);
                        audioStream.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(audioSource);
                    if (audioFormat != null) {
                        _data.writeInt(1);
                        audioFormat.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().detectFromMicrophoneSource(audioStream, audioSource, audioFormat, options, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.voice.IHotwordDetectionService
            public void updateState(PersistableBundle options, SharedMemory sharedMemory, IRemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordDetectionService.DESCRIPTOR);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sharedMemory != null) {
                        _data.writeInt(1);
                        sharedMemory.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateState(options, sharedMemory, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.voice.IHotwordDetectionService
            public void updateAudioFlinger(IBinder audioFlinger) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordDetectionService.DESCRIPTOR);
                    _data.writeStrongBinder(audioFlinger);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateAudioFlinger(audioFlinger);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.voice.IHotwordDetectionService
            public void updateContentCaptureManager(IContentCaptureManager contentCaptureManager, ContentCaptureOptions options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordDetectionService.DESCRIPTOR);
                    _data.writeStrongBinder(contentCaptureManager != null ? contentCaptureManager.asBinder() : null);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateContentCaptureManager(contentCaptureManager, options);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.voice.IHotwordDetectionService
            public void ping(IRemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordDetectionService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ping(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.voice.IHotwordDetectionService
            public void stopDetection() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHotwordDetectionService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopDetection();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHotwordDetectionService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IHotwordDetectionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
