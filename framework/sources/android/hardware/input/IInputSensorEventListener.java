package android.hardware.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IInputSensorEventListener extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.input.IInputSensorEventListener";

    void onInputSensorAccuracyChanged(int i, int i2, int i3) throws RemoteException;

    void onInputSensorChanged(int i, int i2, int i3, long j, float[] fArr) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IInputSensorEventListener {
        @Override // android.hardware.input.IInputSensorEventListener
        public void onInputSensorChanged(int deviceId, int sensorId, int accuracy, long timestamp, float[] values) throws RemoteException {
        }

        @Override // android.hardware.input.IInputSensorEventListener
        public void onInputSensorAccuracyChanged(int deviceId, int sensorId, int accuracy) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInputSensorEventListener {
        static final int TRANSACTION_onInputSensorAccuracyChanged = 2;
        static final int TRANSACTION_onInputSensorChanged = 1;

        public Stub() {
            attachInterface(this, IInputSensorEventListener.DESCRIPTOR);
        }

        public static IInputSensorEventListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInputSensorEventListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInputSensorEventListener)) {
                return new Proxy(obj);
            }
            return (IInputSensorEventListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onInputSensorChanged";
                case 2:
                    return "onInputSensorAccuracyChanged";
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
                    reply.writeString(IInputSensorEventListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInputSensorEventListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            long _arg3 = data.readLong();
                            float[] _arg4 = data.createFloatArray();
                            onInputSensorChanged(_arg0, _arg1, _arg2, _arg3, _arg4);
                            return true;
                        case 2:
                            data.enforceInterface(IInputSensorEventListener.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg12 = data.readInt();
                            int _arg22 = data.readInt();
                            onInputSensorAccuracyChanged(_arg02, _arg12, _arg22);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IInputSensorEventListener {
            public static IInputSensorEventListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInputSensorEventListener.DESCRIPTOR;
            }

            @Override // android.hardware.input.IInputSensorEventListener
            public void onInputSensorChanged(int deviceId, int sensorId, int accuracy, long timestamp, float[] values) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputSensorEventListener.DESCRIPTOR);
                    try {
                        _data.writeInt(deviceId);
                    } catch (Throwable th2) {
                        th = th2;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeInt(sensorId);
                    try {
                        _data.writeInt(accuracy);
                        try {
                            _data.writeLong(timestamp);
                            try {
                                _data.writeFloatArray(values);
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
                try {
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onInputSensorChanged(deviceId, sensorId, accuracy, timestamp, values);
                    _data.recycle();
                } catch (Throwable th8) {
                    th = th8;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.input.IInputSensorEventListener
            public void onInputSensorAccuracyChanged(int deviceId, int sensorId, int accuracy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInputSensorEventListener.DESCRIPTOR);
                    _data.writeInt(deviceId);
                    _data.writeInt(sensorId);
                    _data.writeInt(accuracy);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInputSensorAccuracyChanged(deviceId, sensorId, accuracy);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputSensorEventListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInputSensorEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
