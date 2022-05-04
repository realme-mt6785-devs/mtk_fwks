package com.mediatek.gnssdebugreport;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDebugReportCallback extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.gnssdebugreport.IDebugReportCallback";

    void onDebugReportAvailable(Bundle bundle) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDebugReportCallback {
        @Override // com.mediatek.gnssdebugreport.IDebugReportCallback
        public void onDebugReportAvailable(Bundle debugReport) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDebugReportCallback {
        static final int TRANSACTION_onDebugReportAvailable = 1;

        public Stub() {
            attachInterface(this, IDebugReportCallback.DESCRIPTOR);
        }

        public static IDebugReportCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDebugReportCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDebugReportCallback)) {
                return new Proxy(obj);
            }
            return (IDebugReportCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg0;
            switch (code) {
                case 1598968902:
                    reply.writeString(IDebugReportCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDebugReportCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onDebugReportAvailable(_arg0);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDebugReportCallback {
            public static IDebugReportCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDebugReportCallback.DESCRIPTOR;
            }

            @Override // com.mediatek.gnssdebugreport.IDebugReportCallback
            public void onDebugReportAvailable(Bundle debugReport) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDebugReportCallback.DESCRIPTOR);
                    if (debugReport != null) {
                        _data.writeInt(1);
                        debugReport.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onDebugReportAvailable(debugReport);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDebugReportCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDebugReportCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
