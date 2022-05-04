package com.android.internal.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes4.dex */
public interface IVoiceActionCheckCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.app.IVoiceActionCheckCallback";

    void onComplete(List<String> list) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IVoiceActionCheckCallback {
        @Override // com.android.internal.app.IVoiceActionCheckCallback
        public void onComplete(List<String> voiceActions) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IVoiceActionCheckCallback {
        static final int TRANSACTION_onComplete = 1;

        public Stub() {
            attachInterface(this, IVoiceActionCheckCallback.DESCRIPTOR);
        }

        public static IVoiceActionCheckCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IVoiceActionCheckCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IVoiceActionCheckCallback)) {
                return new Proxy(obj);
            }
            return (IVoiceActionCheckCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onComplete";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IVoiceActionCheckCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IVoiceActionCheckCallback.DESCRIPTOR);
                            List<String> _arg0 = data.createStringArrayList();
                            onComplete(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IVoiceActionCheckCallback {
            public static IVoiceActionCheckCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IVoiceActionCheckCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.app.IVoiceActionCheckCallback
            public void onComplete(List<String> voiceActions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVoiceActionCheckCallback.DESCRIPTOR);
                    _data.writeStringList(voiceActions);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(voiceActions);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoiceActionCheckCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVoiceActionCheckCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
