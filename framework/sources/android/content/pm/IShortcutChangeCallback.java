package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;
/* loaded from: classes.dex */
public interface IShortcutChangeCallback extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.IShortcutChangeCallback";

    void onShortcutsAddedOrUpdated(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException;

    void onShortcutsRemoved(String str, List<ShortcutInfo> list, UserHandle userHandle) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IShortcutChangeCallback {
        @Override // android.content.pm.IShortcutChangeCallback
        public void onShortcutsAddedOrUpdated(String packageName, List<ShortcutInfo> shortcuts, UserHandle user) throws RemoteException {
        }

        @Override // android.content.pm.IShortcutChangeCallback
        public void onShortcutsRemoved(String packageName, List<ShortcutInfo> shortcuts, UserHandle user) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IShortcutChangeCallback {
        static final int TRANSACTION_onShortcutsAddedOrUpdated = 1;
        static final int TRANSACTION_onShortcutsRemoved = 2;

        public Stub() {
            attachInterface(this, IShortcutChangeCallback.DESCRIPTOR);
        }

        public static IShortcutChangeCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IShortcutChangeCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IShortcutChangeCallback)) {
                return new Proxy(obj);
            }
            return (IShortcutChangeCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onShortcutsAddedOrUpdated";
                case 2:
                    return "onShortcutsRemoved";
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
            UserHandle _arg2;
            UserHandle _arg22;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IShortcutChangeCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IShortcutChangeCallback.DESCRIPTOR);
                            String _arg0 = data.readString();
                            List<ShortcutInfo> _arg1 = data.createTypedArrayList(ShortcutInfo.CREATOR);
                            if (data.readInt() != 0) {
                                _arg2 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onShortcutsAddedOrUpdated(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IShortcutChangeCallback.DESCRIPTOR);
                            String _arg02 = data.readString();
                            List<ShortcutInfo> _arg12 = data.createTypedArrayList(ShortcutInfo.CREATOR);
                            if (data.readInt() != 0) {
                                _arg22 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            onShortcutsRemoved(_arg02, _arg12, _arg22);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IShortcutChangeCallback {
            public static IShortcutChangeCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IShortcutChangeCallback.DESCRIPTOR;
            }

            @Override // android.content.pm.IShortcutChangeCallback
            public void onShortcutsAddedOrUpdated(String packageName, List<ShortcutInfo> shortcuts, UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IShortcutChangeCallback.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeTypedList(shortcuts);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onShortcutsAddedOrUpdated(packageName, shortcuts, user);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.content.pm.IShortcutChangeCallback
            public void onShortcutsRemoved(String packageName, List<ShortcutInfo> shortcuts, UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IShortcutChangeCallback.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeTypedList(shortcuts);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onShortcutsRemoved(packageName, shortcuts, user);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IShortcutChangeCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IShortcutChangeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
