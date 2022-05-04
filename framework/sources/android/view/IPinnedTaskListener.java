package android.view;

import android.app.RemoteAction;
import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IPinnedTaskListener extends IInterface {
    public static final String DESCRIPTOR = "android.view.IPinnedTaskListener";

    void onActionsChanged(ParceledListSlice<RemoteAction> parceledListSlice) throws RemoteException;

    void onActivityHidden(ComponentName componentName) throws RemoteException;

    void onAspectRatioChanged(float f) throws RemoteException;

    void onImeVisibilityChanged(boolean z, int i) throws RemoteException;

    void onMovementBoundsChanged(boolean z) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IPinnedTaskListener {
        @Override // android.view.IPinnedTaskListener
        public void onMovementBoundsChanged(boolean fromImeAdjustment) throws RemoteException {
        }

        @Override // android.view.IPinnedTaskListener
        public void onImeVisibilityChanged(boolean imeVisible, int imeHeight) throws RemoteException {
        }

        @Override // android.view.IPinnedTaskListener
        public void onActionsChanged(ParceledListSlice<RemoteAction> actions) throws RemoteException {
        }

        @Override // android.view.IPinnedTaskListener
        public void onActivityHidden(ComponentName componentName) throws RemoteException {
        }

        @Override // android.view.IPinnedTaskListener
        public void onAspectRatioChanged(float aspectRatio) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IPinnedTaskListener {
        static final int TRANSACTION_onActionsChanged = 3;
        static final int TRANSACTION_onActivityHidden = 4;
        static final int TRANSACTION_onAspectRatioChanged = 5;
        static final int TRANSACTION_onImeVisibilityChanged = 2;
        static final int TRANSACTION_onMovementBoundsChanged = 1;

        public Stub() {
            attachInterface(this, IPinnedTaskListener.DESCRIPTOR);
        }

        public static IPinnedTaskListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPinnedTaskListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPinnedTaskListener)) {
                return new Proxy(obj);
            }
            return (IPinnedTaskListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onMovementBoundsChanged";
                case 2:
                    return "onImeVisibilityChanged";
                case 3:
                    return "onActionsChanged";
                case 4:
                    return "onActivityHidden";
                case 5:
                    return "onAspectRatioChanged";
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
            ParceledListSlice _arg0;
            ComponentName _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPinnedTaskListener.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg03 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPinnedTaskListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            onMovementBoundsChanged(_arg03);
                            return true;
                        case 2:
                            data.enforceInterface(IPinnedTaskListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            }
                            int _arg1 = data.readInt();
                            onImeVisibilityChanged(_arg03, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IPinnedTaskListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onActionsChanged(_arg0);
                            return true;
                        case 4:
                            data.enforceInterface(IPinnedTaskListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onActivityHidden(_arg02);
                            return true;
                        case 5:
                            data.enforceInterface(IPinnedTaskListener.DESCRIPTOR);
                            onAspectRatioChanged(data.readFloat());
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IPinnedTaskListener {
            public static IPinnedTaskListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPinnedTaskListener.DESCRIPTOR;
            }

            @Override // android.view.IPinnedTaskListener
            public void onMovementBoundsChanged(boolean fromImeAdjustment) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPinnedTaskListener.DESCRIPTOR);
                    _data.writeInt(fromImeAdjustment ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMovementBoundsChanged(fromImeAdjustment);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IPinnedTaskListener
            public void onImeVisibilityChanged(boolean imeVisible, int imeHeight) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPinnedTaskListener.DESCRIPTOR);
                    _data.writeInt(imeVisible ? 1 : 0);
                    _data.writeInt(imeHeight);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onImeVisibilityChanged(imeVisible, imeHeight);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IPinnedTaskListener
            public void onActionsChanged(ParceledListSlice<RemoteAction> actions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPinnedTaskListener.DESCRIPTOR);
                    if (actions != null) {
                        _data.writeInt(1);
                        actions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActionsChanged(actions);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IPinnedTaskListener
            public void onActivityHidden(ComponentName componentName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPinnedTaskListener.DESCRIPTOR);
                    if (componentName != null) {
                        _data.writeInt(1);
                        componentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityHidden(componentName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IPinnedTaskListener
            public void onAspectRatioChanged(float aspectRatio) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPinnedTaskListener.DESCRIPTOR);
                    _data.writeFloat(aspectRatio);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAspectRatioChanged(aspectRatio);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPinnedTaskListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPinnedTaskListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
