package android.hardware.camera2.extension;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
/* loaded from: classes.dex */
public interface IPreviewImageProcessorImpl extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.IPreviewImageProcessorImpl";

    void onImageFormatUpdate(int i) throws RemoteException;

    void onOutputSurface(Surface surface, int i) throws RemoteException;

    void onResolutionUpdate(Size size) throws RemoteException;

    void process(ParcelImage parcelImage, CameraMetadataNative cameraMetadataNative, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPreviewImageProcessorImpl {
        @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
        public void onOutputSurface(Surface surface, int imageFormat) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
        public void onResolutionUpdate(Size size) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
        public void onImageFormatUpdate(int imageFormat) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
        public void process(ParcelImage image, CameraMetadataNative result, int sequenceId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPreviewImageProcessorImpl {
        static final int TRANSACTION_onImageFormatUpdate = 3;
        static final int TRANSACTION_onOutputSurface = 1;
        static final int TRANSACTION_onResolutionUpdate = 2;
        static final int TRANSACTION_process = 4;

        public Stub() {
            attachInterface(this, IPreviewImageProcessorImpl.DESCRIPTOR);
        }

        public static IPreviewImageProcessorImpl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPreviewImageProcessorImpl.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPreviewImageProcessorImpl)) {
                return new Proxy(obj);
            }
            return (IPreviewImageProcessorImpl) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onOutputSurface";
                case 2:
                    return "onResolutionUpdate";
                case 3:
                    return "onImageFormatUpdate";
                case 4:
                    return "process";
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
            Surface _arg0;
            Size _arg02;
            ParcelImage _arg03;
            CameraMetadataNative _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPreviewImageProcessorImpl.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPreviewImageProcessorImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Surface.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg12 = data.readInt();
                            onOutputSurface(_arg0, _arg12);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IPreviewImageProcessorImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Size.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onResolutionUpdate(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IPreviewImageProcessorImpl.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            onImageFormatUpdate(_arg04);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IPreviewImageProcessorImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ParcelImage.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = CameraMetadataNative.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg2 = data.readInt();
                            process(_arg03, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPreviewImageProcessorImpl {
            public static IPreviewImageProcessorImpl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPreviewImageProcessorImpl.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
            public void onOutputSurface(Surface surface, int imageFormat) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPreviewImageProcessorImpl.DESCRIPTOR);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(imageFormat);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onOutputSurface(surface, imageFormat);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
            public void onResolutionUpdate(Size size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPreviewImageProcessorImpl.DESCRIPTOR);
                    if (size != null) {
                        _data.writeInt(1);
                        size.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onResolutionUpdate(size);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
            public void onImageFormatUpdate(int imageFormat) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPreviewImageProcessorImpl.DESCRIPTOR);
                    _data.writeInt(imageFormat);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onImageFormatUpdate(imageFormat);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IPreviewImageProcessorImpl
            public void process(ParcelImage image, CameraMetadataNative result, int sequenceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPreviewImageProcessorImpl.DESCRIPTOR);
                    if (image != null) {
                        _data.writeInt(1);
                        image.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sequenceId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().process(image, result, sequenceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPreviewImageProcessorImpl impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPreviewImageProcessorImpl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
