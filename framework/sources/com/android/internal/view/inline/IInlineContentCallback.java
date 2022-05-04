package com.android.internal.view.inline;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;
/* loaded from: classes4.dex */
public interface IInlineContentCallback extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.view.inline.IInlineContentCallback";

    void onClick() throws RemoteException;

    void onContent(SurfaceControlViewHost.SurfacePackage surfacePackage, int i, int i2) throws RemoteException;

    void onLongClick() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IInlineContentCallback {
        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onContent(SurfaceControlViewHost.SurfacePackage content, int width, int height) throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onClick() throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentCallback
        public void onLongClick() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IInlineContentCallback {
        static final int TRANSACTION_onClick = 2;
        static final int TRANSACTION_onContent = 1;
        static final int TRANSACTION_onLongClick = 3;

        public Stub() {
            attachInterface(this, IInlineContentCallback.DESCRIPTOR);
        }

        public static IInlineContentCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInlineContentCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInlineContentCallback)) {
                return new Proxy(obj);
            }
            return (IInlineContentCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onContent";
                case 2:
                    return "onClick";
                case 3:
                    return "onLongClick";
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
            SurfaceControlViewHost.SurfacePackage _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IInlineContentCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInlineContentCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            onContent(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IInlineContentCallback.DESCRIPTOR);
                            onClick();
                            return true;
                        case 3:
                            data.enforceInterface(IInlineContentCallback.DESCRIPTOR);
                            onLongClick();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IInlineContentCallback {
            public static IInlineContentCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInlineContentCallback.DESCRIPTOR;
            }

            @Override // com.android.internal.view.inline.IInlineContentCallback
            public void onContent(SurfaceControlViewHost.SurfacePackage content, int width, int height) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineContentCallback.DESCRIPTOR);
                    if (content != null) {
                        _data.writeInt(1);
                        content.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(width);
                    _data.writeInt(height);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onContent(content, width, height);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentCallback
            public void onClick() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineContentCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onClick();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentCallback
            public void onLongClick() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineContentCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLongClick();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineContentCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInlineContentCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
