package android.hardware.lights;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface ILightsManager extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.lights.ILightsManager";

    void closeSession(IBinder iBinder) throws RemoteException;

    LightState getLightState(int i) throws RemoteException;

    List<Light> getLights() throws RemoteException;

    void openSession(IBinder iBinder, int i) throws RemoteException;

    void setLightStates(IBinder iBinder, int[] iArr, LightState[] lightStateArr) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ILightsManager {
        @Override // android.hardware.lights.ILightsManager
        public List<Light> getLights() throws RemoteException {
            return null;
        }

        @Override // android.hardware.lights.ILightsManager
        public LightState getLightState(int lightId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.lights.ILightsManager
        public void openSession(IBinder sessionToken, int priority) throws RemoteException {
        }

        @Override // android.hardware.lights.ILightsManager
        public void closeSession(IBinder sessionToken) throws RemoteException {
        }

        @Override // android.hardware.lights.ILightsManager
        public void setLightStates(IBinder sessionToken, int[] lightIds, LightState[] states) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ILightsManager {
        static final int TRANSACTION_closeSession = 4;
        static final int TRANSACTION_getLightState = 2;
        static final int TRANSACTION_getLights = 1;
        static final int TRANSACTION_openSession = 3;
        static final int TRANSACTION_setLightStates = 5;

        public Stub() {
            attachInterface(this, ILightsManager.DESCRIPTOR);
        }

        public static ILightsManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ILightsManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ILightsManager)) {
                return new Proxy(obj);
            }
            return (ILightsManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getLights";
                case 2:
                    return "getLightState";
                case 3:
                    return "openSession";
                case 4:
                    return "closeSession";
                case 5:
                    return "setLightStates";
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
                    reply.writeString(ILightsManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ILightsManager.DESCRIPTOR);
                            List<Light> _result = getLights();
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 2:
                            data.enforceInterface(ILightsManager.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            LightState _result2 = getLightState(_arg0);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(ILightsManager.DESCRIPTOR);
                            IBinder _arg02 = data.readStrongBinder();
                            int _arg1 = data.readInt();
                            openSession(_arg02, _arg1);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ILightsManager.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            closeSession(_arg03);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ILightsManager.DESCRIPTOR);
                            IBinder _arg04 = data.readStrongBinder();
                            int[] _arg12 = data.createIntArray();
                            LightState[] _arg2 = (LightState[]) data.createTypedArray(LightState.CREATOR);
                            setLightStates(_arg04, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ILightsManager {
            public static ILightsManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ILightsManager.DESCRIPTOR;
            }

            @Override // android.hardware.lights.ILightsManager
            public List<Light> getLights() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILightsManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLights();
                    }
                    _reply.readException();
                    List<Light> _result = _reply.createTypedArrayList(Light.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public LightState getLightState(int lightId) throws RemoteException {
                LightState _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILightsManager.DESCRIPTOR);
                    _data.writeInt(lightId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLightState(lightId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LightState.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public void openSession(IBinder sessionToken, int priority) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILightsManager.DESCRIPTOR);
                    _data.writeStrongBinder(sessionToken);
                    _data.writeInt(priority);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().openSession(sessionToken, priority);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public void closeSession(IBinder sessionToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILightsManager.DESCRIPTOR);
                    _data.writeStrongBinder(sessionToken);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().closeSession(sessionToken);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.lights.ILightsManager
            public void setLightStates(IBinder sessionToken, int[] lightIds, LightState[] states) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ILightsManager.DESCRIPTOR);
                    _data.writeStrongBinder(sessionToken);
                    _data.writeIntArray(lightIds);
                    _data.writeTypedArray(states, 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLightStates(sessionToken, lightIds, states);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILightsManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILightsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
