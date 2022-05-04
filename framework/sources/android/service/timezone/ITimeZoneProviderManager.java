package android.service.timezone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ITimeZoneProviderManager extends IInterface {
    public static final String DESCRIPTOR = "android.service.timezone.ITimeZoneProviderManager";

    void onTimeZoneProviderPermanentFailure(String str) throws RemoteException;

    void onTimeZoneProviderSuggestion(TimeZoneProviderSuggestion timeZoneProviderSuggestion) throws RemoteException;

    void onTimeZoneProviderUncertain() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITimeZoneProviderManager {
        @Override // android.service.timezone.ITimeZoneProviderManager
        public void onTimeZoneProviderSuggestion(TimeZoneProviderSuggestion timeZoneProviderSuggestion) throws RemoteException {
        }

        @Override // android.service.timezone.ITimeZoneProviderManager
        public void onTimeZoneProviderUncertain() throws RemoteException {
        }

        @Override // android.service.timezone.ITimeZoneProviderManager
        public void onTimeZoneProviderPermanentFailure(String failureReason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITimeZoneProviderManager {
        static final int TRANSACTION_onTimeZoneProviderPermanentFailure = 3;
        static final int TRANSACTION_onTimeZoneProviderSuggestion = 1;
        static final int TRANSACTION_onTimeZoneProviderUncertain = 2;

        public Stub() {
            attachInterface(this, ITimeZoneProviderManager.DESCRIPTOR);
        }

        public static ITimeZoneProviderManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITimeZoneProviderManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITimeZoneProviderManager)) {
                return new Proxy(obj);
            }
            return (ITimeZoneProviderManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTimeZoneProviderSuggestion";
                case 2:
                    return "onTimeZoneProviderUncertain";
                case 3:
                    return "onTimeZoneProviderPermanentFailure";
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
            TimeZoneProviderSuggestion _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITimeZoneProviderManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITimeZoneProviderManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TimeZoneProviderSuggestion.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onTimeZoneProviderSuggestion(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ITimeZoneProviderManager.DESCRIPTOR);
                            onTimeZoneProviderUncertain();
                            return true;
                        case 3:
                            data.enforceInterface(ITimeZoneProviderManager.DESCRIPTOR);
                            String _arg02 = data.readString();
                            onTimeZoneProviderPermanentFailure(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITimeZoneProviderManager {
            public static ITimeZoneProviderManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITimeZoneProviderManager.DESCRIPTOR;
            }

            @Override // android.service.timezone.ITimeZoneProviderManager
            public void onTimeZoneProviderSuggestion(TimeZoneProviderSuggestion timeZoneProviderSuggestion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneProviderManager.DESCRIPTOR);
                    if (timeZoneProviderSuggestion != null) {
                        _data.writeInt(1);
                        timeZoneProviderSuggestion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTimeZoneProviderSuggestion(timeZoneProviderSuggestion);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.timezone.ITimeZoneProviderManager
            public void onTimeZoneProviderUncertain() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneProviderManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTimeZoneProviderUncertain();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.timezone.ITimeZoneProviderManager
            public void onTimeZoneProviderPermanentFailure(String failureReason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITimeZoneProviderManager.DESCRIPTOR);
                    _data.writeString(failureReason);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTimeZoneProviderPermanentFailure(failureReason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeZoneProviderManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITimeZoneProviderManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
