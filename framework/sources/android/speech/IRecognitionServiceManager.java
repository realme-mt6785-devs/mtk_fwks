package android.speech;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.speech.IRecognitionServiceManagerCallback;
/* loaded from: classes3.dex */
public interface IRecognitionServiceManager extends IInterface {
    public static final String DESCRIPTOR = "android.speech.IRecognitionServiceManager";

    void createSession(ComponentName componentName, IBinder iBinder, boolean z, IRecognitionServiceManagerCallback iRecognitionServiceManagerCallback) throws RemoteException;

    void setTemporaryComponent(ComponentName componentName) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRecognitionServiceManager {
        @Override // android.speech.IRecognitionServiceManager
        public void createSession(ComponentName componentName, IBinder clientToken, boolean onDevice, IRecognitionServiceManagerCallback callback) throws RemoteException {
        }

        @Override // android.speech.IRecognitionServiceManager
        public void setTemporaryComponent(ComponentName componentName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRecognitionServiceManager {
        static final int TRANSACTION_createSession = 1;
        static final int TRANSACTION_setTemporaryComponent = 2;

        public Stub() {
            attachInterface(this, IRecognitionServiceManager.DESCRIPTOR);
        }

        public static IRecognitionServiceManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRecognitionServiceManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRecognitionServiceManager)) {
                return new Proxy(obj);
            }
            return (IRecognitionServiceManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createSession";
                case 2:
                    return "setTemporaryComponent";
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
            ComponentName _arg0;
            ComponentName _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRecognitionServiceManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRecognitionServiceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IBinder _arg1 = data.readStrongBinder();
                            boolean _arg2 = data.readInt() != 0;
                            IRecognitionServiceManagerCallback _arg3 = IRecognitionServiceManagerCallback.Stub.asInterface(data.readStrongBinder());
                            createSession(_arg0, _arg1, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(IRecognitionServiceManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            setTemporaryComponent(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRecognitionServiceManager {
            public static IRecognitionServiceManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRecognitionServiceManager.DESCRIPTOR;
            }

            @Override // android.speech.IRecognitionServiceManager
            public void createSession(ComponentName componentName, IBinder clientToken, boolean onDevice, IRecognitionServiceManagerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRecognitionServiceManager.DESCRIPTOR);
                    int i = 0;
                    if (componentName != null) {
                        _data.writeInt(1);
                        componentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(clientToken);
                    if (onDevice) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createSession(componentName, clientToken, onDevice, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.speech.IRecognitionServiceManager
            public void setTemporaryComponent(ComponentName componentName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRecognitionServiceManager.DESCRIPTOR);
                    if (componentName != null) {
                        _data.writeInt(1);
                        componentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTemporaryComponent(componentName);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecognitionServiceManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRecognitionServiceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
