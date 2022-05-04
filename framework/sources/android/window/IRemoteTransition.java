package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;
import android.window.IRemoteTransitionFinishedCallback;
/* loaded from: classes3.dex */
public interface IRemoteTransition extends IInterface {
    public static final String DESCRIPTOR = "android.window.IRemoteTransition";

    void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback) throws RemoteException;

    void startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRemoteTransition {
        @Override // android.window.IRemoteTransition
        public void startAnimation(IBinder token, TransitionInfo info, SurfaceControl.Transaction t, IRemoteTransitionFinishedCallback finishCallback) throws RemoteException {
        }

        @Override // android.window.IRemoteTransition
        public void mergeAnimation(IBinder transition, TransitionInfo info, SurfaceControl.Transaction t, IBinder mergeTarget, IRemoteTransitionFinishedCallback finishCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRemoteTransition {
        static final int TRANSACTION_mergeAnimation = 2;
        static final int TRANSACTION_startAnimation = 1;

        public Stub() {
            attachInterface(this, IRemoteTransition.DESCRIPTOR);
        }

        public static IRemoteTransition asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRemoteTransition.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteTransition)) {
                return new Proxy(obj);
            }
            return (IRemoteTransition) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startAnimation";
                case 2:
                    return "mergeAnimation";
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
            TransitionInfo _arg12;
            SurfaceControl.Transaction _arg22;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRemoteTransition.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRemoteTransition.DESCRIPTOR);
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
                            IRemoteTransitionFinishedCallback _arg3 = IRemoteTransitionFinishedCallback.Stub.asInterface(data.readStrongBinder());
                            startAnimation(_arg0, _arg1, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(IRemoteTransition.DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg12 = TransitionInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = SurfaceControl.Transaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            IBinder _arg32 = data.readStrongBinder();
                            IRemoteTransitionFinishedCallback _arg4 = IRemoteTransitionFinishedCallback.Stub.asInterface(data.readStrongBinder());
                            mergeAnimation(_arg02, _arg12, _arg22, _arg32, _arg4);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRemoteTransition {
            public static IRemoteTransition sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRemoteTransition.DESCRIPTOR;
            }

            @Override // android.window.IRemoteTransition
            public void startAnimation(IBinder token, TransitionInfo info, SurfaceControl.Transaction t, IRemoteTransitionFinishedCallback finishCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRemoteTransition.DESCRIPTOR);
                    _data.writeStrongBinder(token);
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
                    _data.writeStrongBinder(finishCallback != null ? finishCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startAnimation(token, info, t, finishCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.IRemoteTransition
            public void mergeAnimation(IBinder transition, TransitionInfo info, SurfaceControl.Transaction t, IBinder mergeTarget, IRemoteTransitionFinishedCallback finishCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRemoteTransition.DESCRIPTOR);
                    _data.writeStrongBinder(transition);
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
                    _data.writeStrongBinder(mergeTarget);
                    _data.writeStrongBinder(finishCallback != null ? finishCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mergeAnimation(transition, info, t, mergeTarget, finishCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteTransition impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRemoteTransition getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
