package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDeviceProductInfoConstants extends IInterface {
    public static final int CONNECTION_TO_SINK_BUILT_IN = 1;
    public static final int CONNECTION_TO_SINK_DIRECT = 2;
    public static final int CONNECTION_TO_SINK_TRANSITIVE = 3;
    public static final int CONNECTION_TO_SINK_UNKNOWN = 0;
    public static final String DESCRIPTOR = "android.hardware.display.IDeviceProductInfoConstants";

    /* loaded from: classes.dex */
    public static class Default implements IDeviceProductInfoConstants {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDeviceProductInfoConstants {
        public Stub() {
            attachInterface(this, IDeviceProductInfoConstants.DESCRIPTOR);
        }

        public static IDeviceProductInfoConstants asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDeviceProductInfoConstants.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDeviceProductInfoConstants)) {
                return new Proxy(obj);
            }
            return (IDeviceProductInfoConstants) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            return null;
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IDeviceProductInfoConstants.DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IDeviceProductInfoConstants {
            public static IDeviceProductInfoConstants sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDeviceProductInfoConstants.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(IDeviceProductInfoConstants impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDeviceProductInfoConstants getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
