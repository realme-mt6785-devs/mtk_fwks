package android.service.voice;

import android.media.AudioFormat;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IMicrophoneHotwordDetectionVoiceInteractionCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.voice.IMicrophoneHotwordDetectionVoiceInteractionCallback";

    void onDetected(HotwordDetectedResult hotwordDetectedResult, AudioFormat audioFormat, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IMicrophoneHotwordDetectionVoiceInteractionCallback {
        @Override // android.service.voice.IMicrophoneHotwordDetectionVoiceInteractionCallback
        public void onDetected(HotwordDetectedResult hotwordDetectedResult, AudioFormat audioFormat, ParcelFileDescriptor audioStream) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IMicrophoneHotwordDetectionVoiceInteractionCallback {
        static final int TRANSACTION_onDetected = 1;

        public Stub() {
            attachInterface(this, IMicrophoneHotwordDetectionVoiceInteractionCallback.DESCRIPTOR);
        }

        public static IMicrophoneHotwordDetectionVoiceInteractionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMicrophoneHotwordDetectionVoiceInteractionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMicrophoneHotwordDetectionVoiceInteractionCallback)) {
                return new Proxy(obj);
            }
            return (IMicrophoneHotwordDetectionVoiceInteractionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDetected";
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
            HotwordDetectedResult _arg0;
            AudioFormat _arg1;
            ParcelFileDescriptor _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IMicrophoneHotwordDetectionVoiceInteractionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMicrophoneHotwordDetectionVoiceInteractionCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = HotwordDetectedResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = AudioFormat.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onDetected(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IMicrophoneHotwordDetectionVoiceInteractionCallback {
            public static IMicrophoneHotwordDetectionVoiceInteractionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMicrophoneHotwordDetectionVoiceInteractionCallback.DESCRIPTOR;
            }

            @Override // android.service.voice.IMicrophoneHotwordDetectionVoiceInteractionCallback
            public void onDetected(HotwordDetectedResult hotwordDetectedResult, AudioFormat audioFormat, ParcelFileDescriptor audioStream) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMicrophoneHotwordDetectionVoiceInteractionCallback.DESCRIPTOR);
                    if (hotwordDetectedResult != null) {
                        _data.writeInt(1);
                        hotwordDetectedResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (audioFormat != null) {
                        _data.writeInt(1);
                        audioFormat.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (audioStream != null) {
                        _data.writeInt(1);
                        audioStream.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDetected(hotwordDetectedResult, audioFormat, audioStream);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMicrophoneHotwordDetectionVoiceInteractionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMicrophoneHotwordDetectionVoiceInteractionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
