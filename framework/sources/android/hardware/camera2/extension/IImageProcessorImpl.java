package android.hardware.camera2.extension;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IImageProcessorImpl extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.IImageProcessorImpl";

    void onNextImageAvailable(OutputConfigId outputConfigId, ParcelImage parcelImage, String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IImageProcessorImpl {
        @Override // android.hardware.camera2.extension.IImageProcessorImpl
        public void onNextImageAvailable(OutputConfigId outputConfigId, ParcelImage image, String physicalCameraId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IImageProcessorImpl {
        static final int TRANSACTION_onNextImageAvailable = 1;

        public Stub() {
            attachInterface(this, IImageProcessorImpl.DESCRIPTOR);
        }

        public static IImageProcessorImpl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImageProcessorImpl.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImageProcessorImpl)) {
                return new Proxy(obj);
            }
            return (IImageProcessorImpl) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onNextImageAvailable";
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
            OutputConfigId _arg0;
            ParcelImage _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IImageProcessorImpl.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImageProcessorImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OutputConfigId.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = ParcelImage.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg2 = data.readString();
                            onNextImageAvailable(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IImageProcessorImpl {
            public static IImageProcessorImpl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImageProcessorImpl.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.IImageProcessorImpl
            public void onNextImageAvailable(OutputConfigId outputConfigId, ParcelImage image, String physicalCameraId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageProcessorImpl.DESCRIPTOR);
                    if (outputConfigId != null) {
                        _data.writeInt(1);
                        outputConfigId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (image != null) {
                        _data.writeInt(1);
                        image.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(physicalCameraId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onNextImageAvailable(outputConfigId, image, physicalCameraId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImageProcessorImpl impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImageProcessorImpl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
