package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscriber;
import android.service.controls.actions.ControlActionWrapper;
import java.util.List;
/* loaded from: classes3.dex */
public interface IControlsProvider extends IInterface {
    public static final String DESCRIPTOR = "android.service.controls.IControlsProvider";

    void action(String str, ControlActionWrapper controlActionWrapper, IControlsActionCallback iControlsActionCallback) throws RemoteException;

    void load(IControlsSubscriber iControlsSubscriber) throws RemoteException;

    void loadSuggested(IControlsSubscriber iControlsSubscriber) throws RemoteException;

    void subscribe(List<String> list, IControlsSubscriber iControlsSubscriber) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IControlsProvider {
        @Override // android.service.controls.IControlsProvider
        public void load(IControlsSubscriber subscriber) throws RemoteException {
        }

        @Override // android.service.controls.IControlsProvider
        public void loadSuggested(IControlsSubscriber subscriber) throws RemoteException {
        }

        @Override // android.service.controls.IControlsProvider
        public void subscribe(List<String> controlIds, IControlsSubscriber subscriber) throws RemoteException {
        }

        @Override // android.service.controls.IControlsProvider
        public void action(String controlId, ControlActionWrapper action, IControlsActionCallback cb) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IControlsProvider {
        static final int TRANSACTION_action = 4;
        static final int TRANSACTION_load = 1;
        static final int TRANSACTION_loadSuggested = 2;
        static final int TRANSACTION_subscribe = 3;

        public Stub() {
            attachInterface(this, IControlsProvider.DESCRIPTOR);
        }

        public static IControlsProvider asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IControlsProvider.DESCRIPTOR);
            if (iin == null || !(iin instanceof IControlsProvider)) {
                return new Proxy(obj);
            }
            return (IControlsProvider) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "load";
                case 2:
                    return "loadSuggested";
                case 3:
                    return "subscribe";
                case 4:
                    return "action";
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
            ControlActionWrapper _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IControlsProvider.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IControlsProvider.DESCRIPTOR);
                            IControlsSubscriber _arg0 = IControlsSubscriber.Stub.asInterface(data.readStrongBinder());
                            load(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IControlsProvider.DESCRIPTOR);
                            IControlsSubscriber _arg02 = IControlsSubscriber.Stub.asInterface(data.readStrongBinder());
                            loadSuggested(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IControlsProvider.DESCRIPTOR);
                            List<String> _arg03 = data.createStringArrayList();
                            IControlsSubscriber _arg12 = IControlsSubscriber.Stub.asInterface(data.readStrongBinder());
                            subscribe(_arg03, _arg12);
                            return true;
                        case 4:
                            data.enforceInterface(IControlsProvider.DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ControlActionWrapper.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            IControlsActionCallback _arg2 = IControlsActionCallback.Stub.asInterface(data.readStrongBinder());
                            action(_arg04, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IControlsProvider {
            public static IControlsProvider sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IControlsProvider.DESCRIPTOR;
            }

            @Override // android.service.controls.IControlsProvider
            public void load(IControlsSubscriber subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsProvider.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().load(subscriber);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.controls.IControlsProvider
            public void loadSuggested(IControlsSubscriber subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsProvider.DESCRIPTOR);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().loadSuggested(subscriber);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.controls.IControlsProvider
            public void subscribe(List<String> controlIds, IControlsSubscriber subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsProvider.DESCRIPTOR);
                    _data.writeStringList(controlIds);
                    _data.writeStrongBinder(subscriber != null ? subscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribe(controlIds, subscriber);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.controls.IControlsProvider
            public void action(String controlId, ControlActionWrapper action, IControlsActionCallback cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IControlsProvider.DESCRIPTOR);
                    _data.writeString(controlId);
                    if (action != null) {
                        _data.writeInt(1);
                        action.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().action(controlId, action, cb);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IControlsProvider impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IControlsProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
