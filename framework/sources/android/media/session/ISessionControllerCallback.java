package android.media.session;

import android.content.pm.ParceledListSlice;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
/* loaded from: classes2.dex */
public interface ISessionControllerCallback extends IInterface {
    void onEvent(String str, Bundle bundle) throws RemoteException;

    void onExtrasChanged(Bundle bundle) throws RemoteException;

    void onMetadataChanged(MediaMetadata mediaMetadata) throws RemoteException;

    void onPlaybackStateChanged(PlaybackState playbackState) throws RemoteException;

    void onQueueChanged(ParceledListSlice parceledListSlice) throws RemoteException;

    void onQueueTitleChanged(CharSequence charSequence) throws RemoteException;

    void onSessionDestroyed() throws RemoteException;

    void onVolumeInfoChanged(MediaController.PlaybackInfo playbackInfo) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ISessionControllerCallback {
        @Override // android.media.session.ISessionControllerCallback
        public void onEvent(String event, Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionControllerCallback
        public void onSessionDestroyed() throws RemoteException {
        }

        @Override // android.media.session.ISessionControllerCallback
        public void onPlaybackStateChanged(PlaybackState state) throws RemoteException {
        }

        @Override // android.media.session.ISessionControllerCallback
        public void onMetadataChanged(MediaMetadata metadata) throws RemoteException {
        }

        @Override // android.media.session.ISessionControllerCallback
        public void onQueueChanged(ParceledListSlice queue) throws RemoteException {
        }

        @Override // android.media.session.ISessionControllerCallback
        public void onQueueTitleChanged(CharSequence title) throws RemoteException {
        }

        @Override // android.media.session.ISessionControllerCallback
        public void onExtrasChanged(Bundle extras) throws RemoteException {
        }

        @Override // android.media.session.ISessionControllerCallback
        public void onVolumeInfoChanged(MediaController.PlaybackInfo info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISessionControllerCallback {
        public static final String DESCRIPTOR = "android.media.session.ISessionControllerCallback";
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onExtrasChanged = 7;
        static final int TRANSACTION_onMetadataChanged = 4;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onQueueChanged = 5;
        static final int TRANSACTION_onQueueTitleChanged = 6;
        static final int TRANSACTION_onSessionDestroyed = 2;
        static final int TRANSACTION_onVolumeInfoChanged = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISessionControllerCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISessionControllerCallback)) {
                return new Proxy(obj);
            }
            return (ISessionControllerCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onEvent";
                case 2:
                    return "onSessionDestroyed";
                case 3:
                    return "onPlaybackStateChanged";
                case 4:
                    return "onMetadataChanged";
                case 5:
                    return "onQueueChanged";
                case 6:
                    return "onQueueTitleChanged";
                case 7:
                    return "onExtrasChanged";
                case 8:
                    return "onVolumeInfoChanged";
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
            Bundle _arg1;
            PlaybackState _arg0;
            MediaMetadata _arg02;
            ParceledListSlice _arg03;
            CharSequence _arg04;
            Bundle _arg05;
            MediaController.PlaybackInfo _arg06;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onEvent(_arg07, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            onSessionDestroyed();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PlaybackState.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onPlaybackStateChanged(_arg0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = MediaMetadata.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onMetadataChanged(_arg02);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onQueueChanged(_arg03);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            onQueueTitleChanged(_arg04);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            onExtrasChanged(_arg05);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = MediaController.PlaybackInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            onVolumeInfoChanged(_arg06);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ISessionControllerCallback {
            public static ISessionControllerCallback sDefaultImpl;
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

            @Override // android.media.session.ISessionControllerCallback
            public void onEvent(String event, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(event);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onEvent(event, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionControllerCallback
            public void onSessionDestroyed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSessionDestroyed();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionControllerCallback
            public void onPlaybackStateChanged(PlaybackState state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlaybackStateChanged(state);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionControllerCallback
            public void onMetadataChanged(MediaMetadata metadata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metadata != null) {
                        _data.writeInt(1);
                        metadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMetadataChanged(metadata);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionControllerCallback
            public void onQueueChanged(ParceledListSlice queue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (queue != null) {
                        _data.writeInt(1);
                        queue.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQueueChanged(queue);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionControllerCallback
            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (title != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(title, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQueueTitleChanged(title);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionControllerCallback
            public void onExtrasChanged(Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onExtrasChanged(extras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.session.ISessionControllerCallback
            public void onVolumeInfoChanged(MediaController.PlaybackInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVolumeInfoChanged(info);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISessionControllerCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISessionControllerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
