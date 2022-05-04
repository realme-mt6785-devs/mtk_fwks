package android.view.accessibility;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IAccessibilityEmbeddedConnection extends IInterface {
    public static final String DESCRIPTOR = "android.view.accessibility.IAccessibilityEmbeddedConnection";

    IBinder associateEmbeddedHierarchy(IBinder iBinder, int i) throws RemoteException;

    void disassociateEmbeddedHierarchy() throws RemoteException;

    void setScreenMatrix(float[] fArr) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IAccessibilityEmbeddedConnection {
        @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
        public IBinder associateEmbeddedHierarchy(IBinder hostToken, int sourceId) throws RemoteException {
            return null;
        }

        @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
        public void disassociateEmbeddedHierarchy() throws RemoteException {
        }

        @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
        public void setScreenMatrix(float[] matrixValues) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IAccessibilityEmbeddedConnection {
        static final int TRANSACTION_associateEmbeddedHierarchy = 1;
        static final int TRANSACTION_disassociateEmbeddedHierarchy = 2;
        static final int TRANSACTION_setScreenMatrix = 3;

        public Stub() {
            attachInterface(this, IAccessibilityEmbeddedConnection.DESCRIPTOR);
        }

        public static IAccessibilityEmbeddedConnection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAccessibilityEmbeddedConnection.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAccessibilityEmbeddedConnection)) {
                return new Proxy(obj);
            }
            return (IAccessibilityEmbeddedConnection) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "associateEmbeddedHierarchy";
                case 2:
                    return "disassociateEmbeddedHierarchy";
                case 3:
                    return "setScreenMatrix";
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
                    reply.writeString(IAccessibilityEmbeddedConnection.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAccessibilityEmbeddedConnection.DESCRIPTOR);
                            IBinder _arg0 = data.readStrongBinder();
                            int _arg1 = data.readInt();
                            IBinder _result = associateEmbeddedHierarchy(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IAccessibilityEmbeddedConnection.DESCRIPTOR);
                            disassociateEmbeddedHierarchy();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IAccessibilityEmbeddedConnection.DESCRIPTOR);
                            float[] _arg02 = data.createFloatArray();
                            setScreenMatrix(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IAccessibilityEmbeddedConnection {
            public static IAccessibilityEmbeddedConnection sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAccessibilityEmbeddedConnection.DESCRIPTOR;
            }

            @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
            public IBinder associateEmbeddedHierarchy(IBinder hostToken, int sourceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAccessibilityEmbeddedConnection.DESCRIPTOR);
                    _data.writeStrongBinder(hostToken);
                    _data.writeInt(sourceId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().associateEmbeddedHierarchy(hostToken, sourceId);
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
            public void disassociateEmbeddedHierarchy() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAccessibilityEmbeddedConnection.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disassociateEmbeddedHierarchy();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.accessibility.IAccessibilityEmbeddedConnection
            public void setScreenMatrix(float[] matrixValues) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAccessibilityEmbeddedConnection.DESCRIPTOR);
                    _data.writeFloatArray(matrixValues);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setScreenMatrix(matrixValues);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAccessibilityEmbeddedConnection impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAccessibilityEmbeddedConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
