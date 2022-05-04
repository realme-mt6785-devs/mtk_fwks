package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IHdmiCecVolumeControlFeatureListener extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener";

    void onHdmiCecVolumeControlFeature(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IHdmiCecVolumeControlFeatureListener {
        @Override // android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener
        public void onHdmiCecVolumeControlFeature(int hdmiCecVolumeControl) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IHdmiCecVolumeControlFeatureListener {
        static final int TRANSACTION_onHdmiCecVolumeControlFeature = 1;

        public Stub() {
            attachInterface(this, IHdmiCecVolumeControlFeatureListener.DESCRIPTOR);
        }

        public static IHdmiCecVolumeControlFeatureListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IHdmiCecVolumeControlFeatureListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IHdmiCecVolumeControlFeatureListener)) {
                return new Proxy(obj);
            }
            return (IHdmiCecVolumeControlFeatureListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onHdmiCecVolumeControlFeature";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IHdmiCecVolumeControlFeatureListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IHdmiCecVolumeControlFeatureListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onHdmiCecVolumeControlFeature(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IHdmiCecVolumeControlFeatureListener {
            public static IHdmiCecVolumeControlFeatureListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IHdmiCecVolumeControlFeatureListener.DESCRIPTOR;
            }

            @Override // android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener
            public void onHdmiCecVolumeControlFeature(int hdmiCecVolumeControl) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHdmiCecVolumeControlFeatureListener.DESCRIPTOR);
                    _data.writeInt(hdmiCecVolumeControl);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHdmiCecVolumeControlFeature(hdmiCecVolumeControl);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHdmiCecVolumeControlFeatureListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IHdmiCecVolumeControlFeatureListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
