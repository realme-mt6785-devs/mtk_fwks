package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.mmsdk.IEffectUpdateListener;
import com.mediatek.mmsdk.IMemory;
/* loaded from: classes.dex */
public interface IEffectUser extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IEffectUser";

    boolean apply(ImageInfo imageInfo, IMemory iMemory, ImageInfo imageInfo2, IMemory iMemory2) throws RemoteException;

    String getName() throws RemoteException;

    boolean release() throws RemoteException;

    boolean setParameter(String str, int i) throws RemoteException;

    void setUpdateListener(IEffectUpdateListener iEffectUpdateListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IEffectUser {
        @Override // com.mediatek.mmsdk.IEffectUser
        public String getName() throws RemoteException {
            return null;
        }

        @Override // com.mediatek.mmsdk.IEffectUser
        public boolean apply(ImageInfo rSrcImage, IMemory srcData, ImageInfo rDestImage, IMemory destData) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.mmsdk.IEffectUser
        public boolean setParameter(String parameterKey, int value) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.mmsdk.IEffectUser
        public void setUpdateListener(IEffectUpdateListener listener) throws RemoteException {
        }

        @Override // com.mediatek.mmsdk.IEffectUser
        public boolean release() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IEffectUser {
        static final int TRANSACTION_apply = 2;
        static final int TRANSACTION_getName = 1;
        static final int TRANSACTION_release = 5;
        static final int TRANSACTION_setParameter = 3;
        static final int TRANSACTION_setUpdateListener = 4;

        public Stub() {
            attachInterface(this, IEffectUser.DESCRIPTOR);
        }

        public static IEffectUser asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEffectUser.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEffectUser)) {
                return new Proxy(obj);
            }
            return (IEffectUser) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ImageInfo _arg0;
            ImageInfo _arg2;
            switch (code) {
                case 1598968902:
                    reply.writeString(IEffectUser.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEffectUser.DESCRIPTOR);
                            String _result = getName();
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IEffectUser.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ImageInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IMemory _arg1 = IMemory.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg2 = ImageInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            IMemory _arg3 = IMemory.Stub.asInterface(data.readStrongBinder());
                            boolean apply = apply(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeInt(apply ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(IEffectUser.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg12 = data.readInt();
                            boolean parameter = setParameter(_arg02, _arg12);
                            reply.writeNoException();
                            reply.writeInt(parameter ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IEffectUser.DESCRIPTOR);
                            IEffectUpdateListener _arg03 = IEffectUpdateListener.Stub.asInterface(data.readStrongBinder());
                            setUpdateListener(_arg03);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IEffectUser.DESCRIPTOR);
                            boolean release = release();
                            reply.writeNoException();
                            reply.writeInt(release ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IEffectUser {
            public static IEffectUser sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEffectUser.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IEffectUser
            public String getName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectUser.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectUser
            public boolean apply(ImageInfo rSrcImage, IMemory srcData, ImageInfo rDestImage, IMemory destData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectUser.DESCRIPTOR);
                    boolean _result = true;
                    if (rSrcImage != null) {
                        _data.writeInt(1);
                        rSrcImage.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    IBinder iBinder = null;
                    _data.writeStrongBinder(srcData != null ? srcData.asBinder() : null);
                    if (rDestImage != null) {
                        _data.writeInt(1);
                        rDestImage.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (destData != null) {
                        iBinder = destData.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().apply(rSrcImage, srcData, rDestImage, destData);
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

            @Override // com.mediatek.mmsdk.IEffectUser
            public boolean setParameter(String parameterKey, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectUser.DESCRIPTOR);
                    _data.writeString(parameterKey);
                    _data.writeInt(value);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameter(parameterKey, value);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectUser
            public void setUpdateListener(IEffectUpdateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectUser.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUpdateListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectUser
            public boolean release() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectUser.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().release();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEffectUser impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEffectUser getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
