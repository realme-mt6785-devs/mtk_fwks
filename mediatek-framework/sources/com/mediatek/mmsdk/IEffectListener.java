package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.mmsdk.IEffectHalClient;
/* loaded from: classes.dex */
public interface IEffectListener extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IEffectListener";

    void onAborted(IEffectHalClient iEffectHalClient, BaseParameters baseParameters) throws RemoteException;

    void onCompleted(IEffectHalClient iEffectHalClient, BaseParameters baseParameters, long j) throws RemoteException;

    void onFailed(IEffectHalClient iEffectHalClient, BaseParameters baseParameters) throws RemoteException;

    void onInputFrameProcessed(IEffectHalClient iEffectHalClient, BaseParameters baseParameters, BaseParameters baseParameters2) throws RemoteException;

    void onOutputFrameProcessed(IEffectHalClient iEffectHalClient, BaseParameters baseParameters, BaseParameters baseParameters2) throws RemoteException;

    void onPrepared(IEffectHalClient iEffectHalClient, BaseParameters baseParameters) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IEffectListener {
        @Override // com.mediatek.mmsdk.IEffectListener
        public void onPrepared(IEffectHalClient effect, BaseParameters result) throws RemoteException {
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onInputFrameProcessed(IEffectHalClient effect, BaseParameters parameter, BaseParameters partialResult) throws RemoteException {
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onOutputFrameProcessed(IEffectHalClient effect, BaseParameters parameter, BaseParameters partialResult) throws RemoteException {
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onCompleted(IEffectHalClient effect, BaseParameters partialResult, long uid) throws RemoteException {
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onAborted(IEffectHalClient effect, BaseParameters result) throws RemoteException {
        }

        @Override // com.mediatek.mmsdk.IEffectListener
        public void onFailed(IEffectHalClient effect, BaseParameters result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IEffectListener {
        static final int TRANSACTION_onAborted = 5;
        static final int TRANSACTION_onCompleted = 4;
        static final int TRANSACTION_onFailed = 6;
        static final int TRANSACTION_onInputFrameProcessed = 2;
        static final int TRANSACTION_onOutputFrameProcessed = 3;
        static final int TRANSACTION_onPrepared = 1;

        public Stub() {
            attachInterface(this, IEffectListener.DESCRIPTOR);
        }

        public static IEffectListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEffectListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEffectListener)) {
                return new Proxy(obj);
            }
            return (IEffectListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BaseParameters _arg1;
            BaseParameters _arg12;
            BaseParameters _arg2;
            BaseParameters _arg13;
            BaseParameters _arg22;
            BaseParameters _arg14;
            BaseParameters _arg15;
            BaseParameters _arg16;
            switch (code) {
                case 1598968902:
                    reply.writeString(IEffectListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEffectListener.DESCRIPTOR);
                            IEffectHalClient _arg0 = IEffectHalClient.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onPrepared(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IEffectListener.DESCRIPTOR);
                            IEffectHalClient _arg02 = IEffectHalClient.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg12 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onInputFrameProcessed(_arg02, _arg12, _arg2);
                            return true;
                        case 3:
                            data.enforceInterface(IEffectListener.DESCRIPTOR);
                            IEffectHalClient _arg03 = IEffectHalClient.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg13 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            onOutputFrameProcessed(_arg03, _arg13, _arg22);
                            return true;
                        case 4:
                            data.enforceInterface(IEffectListener.DESCRIPTOR);
                            IEffectHalClient _arg04 = IEffectHalClient.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg14 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            long _arg23 = data.readLong();
                            onCompleted(_arg04, _arg14, _arg23);
                            return true;
                        case 5:
                            data.enforceInterface(IEffectListener.DESCRIPTOR);
                            IEffectHalClient _arg05 = IEffectHalClient.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg15 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            onAborted(_arg05, _arg15);
                            return true;
                        case 6:
                            data.enforceInterface(IEffectListener.DESCRIPTOR);
                            IEffectHalClient _arg06 = IEffectHalClient.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg16 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            onFailed(_arg06, _arg16);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IEffectListener {
            public static IEffectListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEffectListener.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IEffectListener
            public void onPrepared(IEffectHalClient effect, BaseParameters result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectListener.DESCRIPTOR);
                    _data.writeStrongBinder(effect != null ? effect.asBinder() : null);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPrepared(effect, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectListener
            public void onInputFrameProcessed(IEffectHalClient effect, BaseParameters parameter, BaseParameters partialResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectListener.DESCRIPTOR);
                    _data.writeStrongBinder(effect != null ? effect.asBinder() : null);
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (partialResult != null) {
                        _data.writeInt(1);
                        partialResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputFrameProcessed(effect, parameter, partialResult);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectListener
            public void onOutputFrameProcessed(IEffectHalClient effect, BaseParameters parameter, BaseParameters partialResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectListener.DESCRIPTOR);
                    _data.writeStrongBinder(effect != null ? effect.asBinder() : null);
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (partialResult != null) {
                        _data.writeInt(1);
                        partialResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOutputFrameProcessed(effect, parameter, partialResult);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectListener
            public void onCompleted(IEffectHalClient effect, BaseParameters partialResult, long uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectListener.DESCRIPTOR);
                    _data.writeStrongBinder(effect != null ? effect.asBinder() : null);
                    if (partialResult != null) {
                        _data.writeInt(1);
                        partialResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(uid);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCompleted(effect, partialResult, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectListener
            public void onAborted(IEffectHalClient effect, BaseParameters result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectListener.DESCRIPTOR);
                    _data.writeStrongBinder(effect != null ? effect.asBinder() : null);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAborted(effect, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectListener
            public void onFailed(IEffectHalClient effect, BaseParameters result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectListener.DESCRIPTOR);
                    _data.writeStrongBinder(effect != null ? effect.asBinder() : null);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFailed(effect, result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEffectListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEffectListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
