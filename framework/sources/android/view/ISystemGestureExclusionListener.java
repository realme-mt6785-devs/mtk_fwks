package android.view;

import android.graphics.Region;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ISystemGestureExclusionListener extends IInterface {
    public static final String DESCRIPTOR = "android.view.ISystemGestureExclusionListener";

    void onSystemGestureExclusionChanged(int i, Region region, Region region2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISystemGestureExclusionListener {
        @Override // android.view.ISystemGestureExclusionListener
        public void onSystemGestureExclusionChanged(int displayId, Region systemGestureExclusion, Region systemGestureExclusionUnrestricted) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISystemGestureExclusionListener {
        static final int TRANSACTION_onSystemGestureExclusionChanged = 1;

        public Stub() {
            attachInterface(this, ISystemGestureExclusionListener.DESCRIPTOR);
        }

        public static ISystemGestureExclusionListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISystemGestureExclusionListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISystemGestureExclusionListener)) {
                return new Proxy(obj);
            }
            return (ISystemGestureExclusionListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onSystemGestureExclusionChanged";
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
            Region _arg1;
            Region _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISystemGestureExclusionListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISystemGestureExclusionListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Region.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = Region.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onSystemGestureExclusionChanged(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISystemGestureExclusionListener {
            public static ISystemGestureExclusionListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISystemGestureExclusionListener.DESCRIPTOR;
            }

            @Override // android.view.ISystemGestureExclusionListener
            public void onSystemGestureExclusionChanged(int displayId, Region systemGestureExclusion, Region systemGestureExclusionUnrestricted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISystemGestureExclusionListener.DESCRIPTOR);
                    _data.writeInt(displayId);
                    if (systemGestureExclusion != null) {
                        _data.writeInt(1);
                        systemGestureExclusion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (systemGestureExclusionUnrestricted != null) {
                        _data.writeInt(1);
                        systemGestureExclusionUnrestricted.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSystemGestureExclusionChanged(displayId, systemGestureExclusion, systemGestureExclusionUnrestricted);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISystemGestureExclusionListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISystemGestureExclusionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
