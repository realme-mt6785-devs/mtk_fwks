package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;
/* loaded from: classes3.dex */
public interface IDisplayAreaOrganizer extends IInterface {
    public static final String DESCRIPTOR = "android.window.IDisplayAreaOrganizer";

    void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl surfaceControl) throws RemoteException;

    void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) throws RemoteException;

    void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDisplayAreaOrganizer {
        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl leash) throws RemoteException {
        }

        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) throws RemoteException {
        }

        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDisplayAreaOrganizer {
        static final int TRANSACTION_onDisplayAreaAppeared = 1;
        static final int TRANSACTION_onDisplayAreaInfoChanged = 3;
        static final int TRANSACTION_onDisplayAreaVanished = 2;

        public Stub() {
            attachInterface(this, IDisplayAreaOrganizer.DESCRIPTOR);
        }

        public static IDisplayAreaOrganizer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDisplayAreaOrganizer.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDisplayAreaOrganizer)) {
                return new Proxy(obj);
            }
            return (IDisplayAreaOrganizer) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDisplayAreaAppeared";
                case 2:
                    return "onDisplayAreaVanished";
                case 3:
                    return "onDisplayAreaInfoChanged";
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
            DisplayAreaInfo _arg0;
            SurfaceControl _arg1;
            DisplayAreaInfo _arg02;
            DisplayAreaInfo _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDisplayAreaOrganizer.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDisplayAreaOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = DisplayAreaInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = SurfaceControl.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onDisplayAreaAppeared(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IDisplayAreaOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = DisplayAreaInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onDisplayAreaVanished(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IDisplayAreaOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = DisplayAreaInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onDisplayAreaInfoChanged(_arg03);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDisplayAreaOrganizer {
            public static IDisplayAreaOrganizer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDisplayAreaOrganizer.DESCRIPTOR;
            }

            @Override // android.window.IDisplayAreaOrganizer
            public void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl leash) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayAreaOrganizer.DESCRIPTOR);
                    if (displayAreaInfo != null) {
                        _data.writeInt(1);
                        displayAreaInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (leash != null) {
                        _data.writeInt(1);
                        leash.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayAreaAppeared(displayAreaInfo, leash);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.IDisplayAreaOrganizer
            public void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayAreaOrganizer.DESCRIPTOR);
                    if (displayAreaInfo != null) {
                        _data.writeInt(1);
                        displayAreaInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayAreaVanished(displayAreaInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.IDisplayAreaOrganizer
            public void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayAreaOrganizer.DESCRIPTOR);
                    if (displayAreaInfo != null) {
                        _data.writeInt(1);
                        displayAreaInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayAreaInfoChanged(displayAreaInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayAreaOrganizer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDisplayAreaOrganizer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
