package com.mediatek.advcam;

import android.hardware.camera2.CaptureRequest;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IAdvCamService extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.advcam.IAdvCamService";

    int setConfigureParam(int i, CaptureRequest captureRequest) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAdvCamService {
        @Override // com.mediatek.advcam.IAdvCamService
        public int setConfigureParam(int openId, CaptureRequest request) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAdvCamService {
        static final int TRANSACTION_setConfigureParam = 1;

        public Stub() {
            attachInterface(this, IAdvCamService.DESCRIPTOR);
        }

        public static IAdvCamService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAdvCamService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAdvCamService)) {
                return new Proxy(obj);
            }
            return (IAdvCamService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CaptureRequest _arg1;
            switch (code) {
                case 1598968902:
                    reply.writeString(IAdvCamService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAdvCamService.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = (CaptureRequest) CaptureRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _result = setConfigureParam(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAdvCamService {
            public static IAdvCamService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAdvCamService.DESCRIPTOR;
            }

            @Override // com.mediatek.advcam.IAdvCamService
            public int setConfigureParam(int openId, CaptureRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAdvCamService.DESCRIPTOR);
                    _data.writeInt(openId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConfigureParam(openId, request);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAdvCamService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAdvCamService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
