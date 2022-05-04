package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IDisplayWindowInsetsController extends IInterface {
    public static final String DESCRIPTOR = "android.view.IDisplayWindowInsetsController";

    void hideInsets(int i, boolean z) throws RemoteException;

    void insetsChanged(InsetsState insetsState) throws RemoteException;

    void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException;

    void showInsets(int i, boolean z) throws RemoteException;

    void topFocusedWindowChanged(String str) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDisplayWindowInsetsController {
        @Override // android.view.IDisplayWindowInsetsController
        public void topFocusedWindowChanged(String packageName) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void insetsChanged(InsetsState insetsState) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] activeControls) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void showInsets(int types, boolean fromIme) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void hideInsets(int types, boolean fromIme) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDisplayWindowInsetsController {
        static final int TRANSACTION_hideInsets = 5;
        static final int TRANSACTION_insetsChanged = 2;
        static final int TRANSACTION_insetsControlChanged = 3;
        static final int TRANSACTION_showInsets = 4;
        static final int TRANSACTION_topFocusedWindowChanged = 1;

        public Stub() {
            attachInterface(this, IDisplayWindowInsetsController.DESCRIPTOR);
        }

        public static IDisplayWindowInsetsController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDisplayWindowInsetsController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDisplayWindowInsetsController)) {
                return new Proxy(obj);
            }
            return (IDisplayWindowInsetsController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "topFocusedWindowChanged";
                case 2:
                    return "insetsChanged";
                case 3:
                    return "insetsControlChanged";
                case 4:
                    return "showInsets";
                case 5:
                    return "hideInsets";
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
            InsetsState _arg0;
            InsetsState _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDisplayWindowInsetsController.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDisplayWindowInsetsController.DESCRIPTOR);
                            String _arg03 = data.readString();
                            topFocusedWindowChanged(_arg03);
                            return true;
                        case 2:
                            data.enforceInterface(IDisplayWindowInsetsController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            insetsChanged(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IDisplayWindowInsetsController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            insetsControlChanged(_arg02, (InsetsSourceControl[]) data.createTypedArray(InsetsSourceControl.CREATOR));
                            return true;
                        case 4:
                            data.enforceInterface(IDisplayWindowInsetsController.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            showInsets(_arg04, _arg1);
                            return true;
                        case 5:
                            data.enforceInterface(IDisplayWindowInsetsController.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            hideInsets(_arg05, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDisplayWindowInsetsController {
            public static IDisplayWindowInsetsController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDisplayWindowInsetsController.DESCRIPTOR;
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void topFocusedWindowChanged(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowInsetsController.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().topFocusedWindowChanged(packageName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void insetsChanged(InsetsState insetsState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowInsetsController.DESCRIPTOR);
                    if (insetsState != null) {
                        _data.writeInt(1);
                        insetsState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().insetsChanged(insetsState);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] activeControls) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowInsetsController.DESCRIPTOR);
                    if (insetsState != null) {
                        _data.writeInt(1);
                        insetsState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(activeControls, 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().insetsControlChanged(insetsState, activeControls);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void showInsets(int types, boolean fromIme) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowInsetsController.DESCRIPTOR);
                    _data.writeInt(types);
                    _data.writeInt(fromIme ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showInsets(types, fromIme);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void hideInsets(int types, boolean fromIme) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayWindowInsetsController.DESCRIPTOR);
                    _data.writeInt(types);
                    _data.writeInt(fromIme ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideInsets(types, fromIme);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayWindowInsetsController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDisplayWindowInsetsController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
