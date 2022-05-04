package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.mmsdk.IMemory;
/* loaded from: classes.dex */
public interface IImageTransformUser extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IImageTransformUser";

    boolean applyTransform(ImageInfo imageInfo, IMemory iMemory, ImageInfo imageInfo2, IMemory iMemory2) throws RemoteException;

    String getName() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IImageTransformUser {
        @Override // com.mediatek.mmsdk.IImageTransformUser
        public String getName() throws RemoteException {
            return null;
        }

        @Override // com.mediatek.mmsdk.IImageTransformUser
        public boolean applyTransform(ImageInfo rSrcImage, IMemory srcData, ImageInfo rDestImage, IMemory destData) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IImageTransformUser {
        static final int TRANSACTION_applyTransform = 2;
        static final int TRANSACTION_getName = 1;

        public Stub() {
            attachInterface(this, IImageTransformUser.DESCRIPTOR);
        }

        public static IImageTransformUser asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImageTransformUser.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImageTransformUser)) {
                return new Proxy(obj);
            }
            return (IImageTransformUser) iin;
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
                    reply.writeString(IImageTransformUser.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImageTransformUser.DESCRIPTOR);
                            String _result = getName();
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IImageTransformUser.DESCRIPTOR);
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
                            boolean applyTransform = applyTransform(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeInt(applyTransform ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IImageTransformUser {
            public static IImageTransformUser sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImageTransformUser.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IImageTransformUser
            public String getName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageTransformUser.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IImageTransformUser
            public boolean applyTransform(ImageInfo rSrcImage, IMemory srcData, ImageInfo rDestImage, IMemory destData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageTransformUser.DESCRIPTOR);
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
                        return Stub.getDefaultImpl().applyTransform(rSrcImage, srcData, rDestImage, destData);
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
        }

        public static boolean setDefaultImpl(IImageTransformUser impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImageTransformUser getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
