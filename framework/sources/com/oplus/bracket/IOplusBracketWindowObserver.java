package com.oplus.bracket;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oplus.bracket.IOplusBracketModeChangedListener;
/* loaded from: classes4.dex */
public interface IOplusBracketWindowObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.bracket.IOplusBracketWindowObserver";

    void onSurfaceViewChange(IOplusBracketModeChangedListener iOplusBracketModeChangedListener, int i, Rect rect) throws RemoteException;

    void onSurfaceViewShow(IOplusBracketModeChangedListener iOplusBracketModeChangedListener, int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusBracketWindowObserver {
        @Override // com.oplus.bracket.IOplusBracketWindowObserver
        public void onSurfaceViewShow(IOplusBracketModeChangedListener oplusBracketModeChangedListener, int info) throws RemoteException {
        }

        @Override // com.oplus.bracket.IOplusBracketWindowObserver
        public void onSurfaceViewChange(IOplusBracketModeChangedListener oplusBracketModeChangedListener, int info, Rect rect) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusBracketWindowObserver {
        static final int TRANSACTION_onSurfaceViewChange = 2;
        static final int TRANSACTION_onSurfaceViewShow = 1;

        public Stub() {
            attachInterface(this, IOplusBracketWindowObserver.DESCRIPTOR);
        }

        public static IOplusBracketWindowObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusBracketWindowObserver.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusBracketWindowObserver)) {
                return new Proxy(obj);
            }
            return (IOplusBracketWindowObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onSurfaceViewShow";
                case 2:
                    return "onSurfaceViewChange";
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
            Rect _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusBracketWindowObserver.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusBracketWindowObserver.DESCRIPTOR);
                            IOplusBracketModeChangedListener _arg0 = IOplusBracketModeChangedListener.Stub.asInterface(data.readStrongBinder());
                            int _arg1 = data.readInt();
                            onSurfaceViewShow(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusBracketWindowObserver.DESCRIPTOR);
                            IOplusBracketModeChangedListener _arg02 = IOplusBracketModeChangedListener.Stub.asInterface(data.readStrongBinder());
                            int _arg12 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onSurfaceViewChange(_arg02, _arg12, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusBracketWindowObserver {
            public static IOplusBracketWindowObserver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusBracketWindowObserver.DESCRIPTOR;
            }

            @Override // com.oplus.bracket.IOplusBracketWindowObserver
            public void onSurfaceViewShow(IOplusBracketModeChangedListener oplusBracketModeChangedListener, int info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusBracketWindowObserver.DESCRIPTOR);
                    _data.writeStrongBinder(oplusBracketModeChangedListener != null ? oplusBracketModeChangedListener.asBinder() : null);
                    _data.writeInt(info);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSurfaceViewShow(oplusBracketModeChangedListener, info);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.bracket.IOplusBracketWindowObserver
            public void onSurfaceViewChange(IOplusBracketModeChangedListener oplusBracketModeChangedListener, int info, Rect rect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusBracketWindowObserver.DESCRIPTOR);
                    _data.writeStrongBinder(oplusBracketModeChangedListener != null ? oplusBracketModeChangedListener.asBinder() : null);
                    _data.writeInt(info);
                    if (rect != null) {
                        _data.writeInt(1);
                        rect.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSurfaceViewChange(oplusBracketModeChangedListener, info, rect);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusBracketWindowObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusBracketWindowObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
