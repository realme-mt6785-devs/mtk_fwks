package android.security.apc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.apc.IConfirmationCallback;
/* loaded from: classes2.dex */
public interface IProtectedConfirmation extends IInterface {
    public static final String DESCRIPTOR = "android$security$apc$IProtectedConfirmation".replace('$', '.');
    public static final int FLAG_UI_OPTION_INVERTED = 1;
    public static final int FLAG_UI_OPTION_MAGNIFIED = 2;

    void cancelPrompt(IConfirmationCallback iConfirmationCallback) throws RemoteException;

    boolean isSupported() throws RemoteException;

    void presentPrompt(IConfirmationCallback iConfirmationCallback, String str, byte[] bArr, String str2, int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IProtectedConfirmation {
        @Override // android.security.apc.IProtectedConfirmation
        public void presentPrompt(IConfirmationCallback listener, String promptText, byte[] extraData, String locale, int uiOptionFlags) throws RemoteException {
        }

        @Override // android.security.apc.IProtectedConfirmation
        public void cancelPrompt(IConfirmationCallback listener) throws RemoteException {
        }

        @Override // android.security.apc.IProtectedConfirmation
        public boolean isSupported() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IProtectedConfirmation {
        static final int TRANSACTION_cancelPrompt = 2;
        static final int TRANSACTION_isSupported = 3;
        static final int TRANSACTION_presentPrompt = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProtectedConfirmation asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IProtectedConfirmation)) {
                return new Proxy(obj);
            }
            return (IProtectedConfirmation) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            IConfirmationCallback _arg0 = IConfirmationCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg1 = data.readString();
                            byte[] _arg2 = data.createByteArray();
                            String _arg3 = data.readString();
                            int _arg4 = data.readInt();
                            presentPrompt(_arg0, _arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            IConfirmationCallback _arg02 = IConfirmationCallback.Stub.asInterface(data.readStrongBinder());
                            cancelPrompt(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            boolean isSupported = isSupported();
                            reply.writeNoException();
                            reply.writeInt(isSupported ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IProtectedConfirmation {
            public static IProtectedConfirmation sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.security.apc.IProtectedConfirmation
            public void presentPrompt(IConfirmationCallback listener, String promptText, byte[] extraData, String locale, int uiOptionFlags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeString(promptText);
                    _data.writeByteArray(extraData);
                    _data.writeString(locale);
                    _data.writeInt(uiOptionFlags);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().presentPrompt(listener, promptText, extraData, locale, uiOptionFlags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.apc.IProtectedConfirmation
            public void cancelPrompt(IConfirmationCallback listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelPrompt(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.apc.IProtectedConfirmation
            public boolean isSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProtectedConfirmation impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IProtectedConfirmation getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
