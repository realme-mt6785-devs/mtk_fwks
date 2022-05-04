package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.mmsdk.IEffectUser;
/* loaded from: classes.dex */
public interface IEffectUpdateListener extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IEffectUpdateListener";

    void onEffectUpdated(IEffectUser iEffectUser, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IEffectUpdateListener {
        @Override // com.mediatek.mmsdk.IEffectUpdateListener
        public void onEffectUpdated(IEffectUser effect, int info) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IEffectUpdateListener {
        static final int TRANSACTION_onEffectUpdated = 1;

        public Stub() {
            attachInterface(this, IEffectUpdateListener.DESCRIPTOR);
        }

        public static IEffectUpdateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEffectUpdateListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEffectUpdateListener)) {
                return new Proxy(obj);
            }
            return (IEffectUpdateListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IEffectUpdateListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEffectUpdateListener.DESCRIPTOR);
                            IEffectUser _arg0 = IEffectUser.Stub.asInterface(data.readStrongBinder());
                            int _arg1 = data.readInt();
                            onEffectUpdated(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IEffectUpdateListener {
            public static IEffectUpdateListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEffectUpdateListener.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IEffectUpdateListener
            public void onEffectUpdated(IEffectUser effect, int info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectUpdateListener.DESCRIPTOR);
                    _data.writeStrongBinder(effect != null ? effect.asBinder() : null);
                    _data.writeInt(info);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onEffectUpdated(effect, info);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEffectUpdateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEffectUpdateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
