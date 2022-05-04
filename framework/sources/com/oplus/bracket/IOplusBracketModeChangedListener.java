package com.oplus.bracket;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oplus.bracket.IOplusBracketWindowObserver;
/* loaded from: classes4.dex */
public interface IOplusBracketModeChangedListener extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.bracket.IOplusBracketModeChangedListener";

    void onBindService(IOplusBracketWindowObserver iOplusBracketWindowObserver) throws RemoteException;

    void onBracketModeChanged(int i) throws RemoteException;

    void onBracketRegionChange(Rect rect) throws RemoteException;

    void onUnBindService(int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusBracketModeChangedListener {
        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onBracketModeChanged(int type) throws RemoteException {
        }

        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onBracketRegionChange(Rect rect) throws RemoteException {
        }

        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onBindService(IOplusBracketWindowObserver oplusBracketWindowObserver) throws RemoteException {
        }

        @Override // com.oplus.bracket.IOplusBracketModeChangedListener
        public void onUnBindService(int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusBracketModeChangedListener {
        static final int TRANSACTION_onBindService = 3;
        static final int TRANSACTION_onBracketModeChanged = 1;
        static final int TRANSACTION_onBracketRegionChange = 2;
        static final int TRANSACTION_onUnBindService = 4;

        public Stub() {
            attachInterface(this, IOplusBracketModeChangedListener.DESCRIPTOR);
        }

        public static IOplusBracketModeChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusBracketModeChangedListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusBracketModeChangedListener)) {
                return new Proxy(obj);
            }
            return (IOplusBracketModeChangedListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onBracketModeChanged";
                case 2:
                    return "onBracketRegionChange";
                case 3:
                    return "onBindService";
                case 4:
                    return "onUnBindService";
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
            Rect _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusBracketModeChangedListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusBracketModeChangedListener.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onBracketModeChanged(_arg02);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusBracketModeChangedListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onBracketRegionChange(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusBracketModeChangedListener.DESCRIPTOR);
                            IOplusBracketWindowObserver _arg03 = IOplusBracketWindowObserver.Stub.asInterface(data.readStrongBinder());
                            onBindService(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusBracketModeChangedListener.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            onUnBindService(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusBracketModeChangedListener {
            public static IOplusBracketModeChangedListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusBracketModeChangedListener.DESCRIPTOR;
            }

            @Override // com.oplus.bracket.IOplusBracketModeChangedListener
            public void onBracketModeChanged(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusBracketModeChangedListener.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBracketModeChanged(type);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.bracket.IOplusBracketModeChangedListener
            public void onBracketRegionChange(Rect rect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusBracketModeChangedListener.DESCRIPTOR);
                    if (rect != null) {
                        _data.writeInt(1);
                        rect.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBracketRegionChange(rect);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.bracket.IOplusBracketModeChangedListener
            public void onBindService(IOplusBracketWindowObserver oplusBracketWindowObserver) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusBracketModeChangedListener.DESCRIPTOR);
                    _data.writeStrongBinder(oplusBracketWindowObserver != null ? oplusBracketWindowObserver.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBindService(oplusBracketWindowObserver);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.bracket.IOplusBracketModeChangedListener
            public void onUnBindService(int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusBracketModeChangedListener.DESCRIPTOR);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnBindService(reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusBracketModeChangedListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusBracketModeChangedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
