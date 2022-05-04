package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;
/* loaded from: classes3.dex */
public interface ITransitionPlayer extends IInterface {
    public static final String DESCRIPTOR = "android.window.ITransitionPlayer";

    void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) throws RemoteException;

    void requestStartTransition(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITransitionPlayer {
        @Override // android.window.ITransitionPlayer
        public void onTransitionReady(IBinder transitionToken, TransitionInfo info, SurfaceControl.Transaction t, SurfaceControl.Transaction finishT) throws RemoteException {
        }

        @Override // android.window.ITransitionPlayer
        public void requestStartTransition(IBinder transitionToken, TransitionRequestInfo request) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITransitionPlayer {
        static final int TRANSACTION_onTransitionReady = 1;
        static final int TRANSACTION_requestStartTransition = 2;

        public Stub() {
            attachInterface(this, ITransitionPlayer.DESCRIPTOR);
        }

        public static ITransitionPlayer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITransitionPlayer.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITransitionPlayer)) {
                return new Proxy(obj);
            }
            return (ITransitionPlayer) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTransitionReady";
                case 2:
                    return "requestStartTransition";
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
            TransitionInfo _arg1;
            SurfaceControl.Transaction _arg2;
            SurfaceControl.Transaction _arg3;
            TransitionRequestInfo _arg12;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITransitionPlayer.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITransitionPlayer.DESCRIPTOR);
                            IBinder _arg0 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg1 = TransitionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = SurfaceControl.Transaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = SurfaceControl.Transaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            onTransitionReady(_arg0, _arg1, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(ITransitionPlayer.DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg12 = TransitionRequestInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            requestStartTransition(_arg02, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITransitionPlayer {
            public static ITransitionPlayer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITransitionPlayer.DESCRIPTOR;
            }

            @Override // android.window.ITransitionPlayer
            public void onTransitionReady(IBinder transitionToken, TransitionInfo info, SurfaceControl.Transaction t, SurfaceControl.Transaction finishT) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITransitionPlayer.DESCRIPTOR);
                    _data.writeStrongBinder(transitionToken);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (t != null) {
                        _data.writeInt(1);
                        t.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (finishT != null) {
                        _data.writeInt(1);
                        finishT.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTransitionReady(transitionToken, info, t, finishT);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITransitionPlayer
            public void requestStartTransition(IBinder transitionToken, TransitionRequestInfo request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITransitionPlayer.DESCRIPTOR);
                    _data.writeStrongBinder(transitionToken);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestStartTransition(transitionToken, request);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITransitionPlayer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITransitionPlayer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
