package com.mediatek.mmsdk.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.mediatek.mmsdk.BaseParameters;
import java.util.List;
/* loaded from: classes.dex */
public interface ICallbackClient extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.callback.ICallbackClient";

    int setOutputSurfaces(List<Surface> list, List<BaseParameters> list2) throws RemoteException;

    long start() throws RemoteException;

    long stop() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICallbackClient {
        @Override // com.mediatek.mmsdk.callback.ICallbackClient
        public long start() throws RemoteException {
            return 0L;
        }

        @Override // com.mediatek.mmsdk.callback.ICallbackClient
        public long stop() throws RemoteException {
            return 0L;
        }

        @Override // com.mediatek.mmsdk.callback.ICallbackClient
        public int setOutputSurfaces(List<Surface> output, List<BaseParameters> parameters) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICallbackClient {
        static final int TRANSACTION_setOutputSurfaces = 3;
        static final int TRANSACTION_start = 1;
        static final int TRANSACTION_stop = 2;

        public Stub() {
            attachInterface(this, ICallbackClient.DESCRIPTOR);
        }

        public static ICallbackClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICallbackClient.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICallbackClient)) {
                return new Proxy(obj);
            }
            return (ICallbackClient) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(ICallbackClient.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICallbackClient.DESCRIPTOR);
                            long _result = start();
                            reply.writeNoException();
                            reply.writeLong(_result);
                            return true;
                        case 2:
                            data.enforceInterface(ICallbackClient.DESCRIPTOR);
                            long _result2 = stop();
                            reply.writeNoException();
                            reply.writeLong(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(ICallbackClient.DESCRIPTOR);
                            List<Surface> _arg0 = data.createTypedArrayList(Surface.CREATOR);
                            List<BaseParameters> _arg1 = data.createTypedArrayList(BaseParameters.CREATOR);
                            int _result3 = setOutputSurfaces(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICallbackClient {
            public static ICallbackClient sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICallbackClient.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.callback.ICallbackClient
            public long start() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallbackClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().start();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.callback.ICallbackClient
            public long stop() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallbackClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stop();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.callback.ICallbackClient
            public int setOutputSurfaces(List<Surface> output, List<BaseParameters> parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallbackClient.DESCRIPTOR);
                    _data.writeTypedList(output);
                    _data.writeTypedList(parameters);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOutputSurfaces(output, parameters);
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

        public static boolean setDefaultImpl(ICallbackClient impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICallbackClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
