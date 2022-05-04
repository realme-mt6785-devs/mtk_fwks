package com.android.internal.view.inline;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.view.inline.IInlineContentCallback;
/* loaded from: classes4.dex */
public interface IInlineContentProvider extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.view.inline.IInlineContentProvider";

    void onSurfacePackageReleased() throws RemoteException;

    void provideContent(int i, int i2, IInlineContentCallback iInlineContentCallback) throws RemoteException;

    void requestSurfacePackage() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IInlineContentProvider {
        @Override // com.android.internal.view.inline.IInlineContentProvider
        public void provideContent(int width, int height, IInlineContentCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentProvider
        public void requestSurfacePackage() throws RemoteException {
        }

        @Override // com.android.internal.view.inline.IInlineContentProvider
        public void onSurfacePackageReleased() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IInlineContentProvider {
        static final int TRANSACTION_onSurfacePackageReleased = 3;
        static final int TRANSACTION_provideContent = 1;
        static final int TRANSACTION_requestSurfacePackage = 2;

        public Stub() {
            attachInterface(this, IInlineContentProvider.DESCRIPTOR);
        }

        public static IInlineContentProvider asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInlineContentProvider.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInlineContentProvider)) {
                return new Proxy(obj);
            }
            return (IInlineContentProvider) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "provideContent";
                case 2:
                    return "requestSurfacePackage";
                case 3:
                    return "onSurfacePackageReleased";
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
                    reply.writeString(IInlineContentProvider.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInlineContentProvider.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            IInlineContentCallback _arg2 = IInlineContentCallback.Stub.asInterface(data.readStrongBinder());
                            provideContent(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IInlineContentProvider.DESCRIPTOR);
                            requestSurfacePackage();
                            return true;
                        case 3:
                            data.enforceInterface(IInlineContentProvider.DESCRIPTOR);
                            onSurfacePackageReleased();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IInlineContentProvider {
            public static IInlineContentProvider sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInlineContentProvider.DESCRIPTOR;
            }

            @Override // com.android.internal.view.inline.IInlineContentProvider
            public void provideContent(int width, int height, IInlineContentCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineContentProvider.DESCRIPTOR);
                    _data.writeInt(width);
                    _data.writeInt(height);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().provideContent(width, height, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentProvider
            public void requestSurfacePackage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineContentProvider.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestSurfacePackage();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.view.inline.IInlineContentProvider
            public void onSurfacePackageReleased() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineContentProvider.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSurfacePackageReleased();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineContentProvider impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInlineContentProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
