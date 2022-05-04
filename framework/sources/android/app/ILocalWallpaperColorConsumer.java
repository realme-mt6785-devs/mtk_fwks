package android.app;

import android.graphics.RectF;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ILocalWallpaperColorConsumer extends IInterface {
    public static final String DESCRIPTOR = "android.app.ILocalWallpaperColorConsumer";

    void onColorsChanged(RectF rectF, WallpaperColors wallpaperColors) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ILocalWallpaperColorConsumer {
        @Override // android.app.ILocalWallpaperColorConsumer
        public void onColorsChanged(RectF area, WallpaperColors colors) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ILocalWallpaperColorConsumer {
        static final int TRANSACTION_onColorsChanged = 1;

        public Stub() {
            attachInterface(this, ILocalWallpaperColorConsumer.DESCRIPTOR);
        }

        public static ILocalWallpaperColorConsumer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ILocalWallpaperColorConsumer.DESCRIPTOR);
            if (iin == null || !(iin instanceof ILocalWallpaperColorConsumer)) {
                return new Proxy(obj);
            }
            return (ILocalWallpaperColorConsumer) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onColorsChanged";
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
            RectF _arg0;
            WallpaperColors _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ILocalWallpaperColorConsumer.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ILocalWallpaperColorConsumer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RectF.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = WallpaperColors.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onColorsChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ILocalWallpaperColorConsumer {
            public static ILocalWallpaperColorConsumer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ILocalWallpaperColorConsumer.DESCRIPTOR;
            }

            @Override // android.app.ILocalWallpaperColorConsumer
            public void onColorsChanged(RectF area, WallpaperColors colors) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILocalWallpaperColorConsumer.DESCRIPTOR);
                    if (area != null) {
                        _data.writeInt(1);
                        area.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (colors != null) {
                        _data.writeInt(1);
                        colors.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onColorsChanged(area, colors);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocalWallpaperColorConsumer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILocalWallpaperColorConsumer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
