package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IMMSdkService extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IMMSdkService";

    int connectFeatureManager(BinderHolder binderHolder) throws RemoteException;

    int existCallbackClient() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IMMSdkService {
        @Override // com.mediatek.mmsdk.IMMSdkService
        public int connectFeatureManager(BinderHolder featureManager) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IMMSdkService
        public int existCallbackClient() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IMMSdkService {
        static final int TRANSACTION_connectFeatureManager = 1;
        static final int TRANSACTION_existCallbackClient = 2;

        public Stub() {
            attachInterface(this, IMMSdkService.DESCRIPTOR);
        }

        public static IMMSdkService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IMMSdkService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IMMSdkService)) {
                return new Proxy(obj);
            }
            return (IMMSdkService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IMMSdkService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IMMSdkService.DESCRIPTOR);
                            BinderHolder _arg0 = new BinderHolder();
                            int _result = connectFeatureManager(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            reply.writeInt(1);
                            _arg0.writeToParcel(reply, 1);
                            return true;
                        case 2:
                            data.enforceInterface(IMMSdkService.DESCRIPTOR);
                            int _result2 = existCallbackClient();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IMMSdkService {
            public static IMMSdkService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMMSdkService.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IMMSdkService
            public int connectFeatureManager(BinderHolder featureManager) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMMSdkService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectFeatureManager(featureManager);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    if (_reply.readInt() != 0) {
                        featureManager.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IMMSdkService
            public int existCallbackClient() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IMMSdkService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().existCallbackClient();
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

        public static boolean setDefaultImpl(IMMSdkService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMMSdkService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
