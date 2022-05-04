package android.hardware.vibrator;

import android.hardware.vibrator.IVibratorCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IVibrator extends IInterface {
    public static final int CAP_ALWAYS_ON_CONTROL = 64;
    public static final int CAP_AMPLITUDE_CONTROL = 4;
    public static final int CAP_COMPOSE_EFFECTS = 32;
    public static final int CAP_COMPOSE_PWLE_EFFECTS = 1024;
    public static final int CAP_EXTERNAL_AMPLITUDE_CONTROL = 16;
    public static final int CAP_EXTERNAL_CONTROL = 8;
    public static final int CAP_FREQUENCY_CONTROL = 512;
    public static final int CAP_GET_Q_FACTOR = 256;
    public static final int CAP_GET_RESONANT_FREQUENCY = 128;
    public static final int CAP_ON_CALLBACK = 1;
    public static final int CAP_PERFORM_CALLBACK = 2;
    public static final String DESCRIPTOR = "android$hardware$vibrator$IVibrator".replace('$', '.');
    public static final String HASH = "ea8742d6993e1a82917da38b9938e537aa7fcb54";
    public static final int VERSION = 2;

    void alwaysOnDisable(int i) throws RemoteException;

    void alwaysOnEnable(int i, int i2, byte b) throws RemoteException;

    void compose(CompositeEffect[] compositeEffectArr, IVibratorCallback iVibratorCallback) throws RemoteException;

    void composePwle(PrimitivePwle[] primitivePwleArr, IVibratorCallback iVibratorCallback) throws RemoteException;

    float[] getBandwidthAmplitudeMap() throws RemoteException;

    int getCapabilities() throws RemoteException;

    int getCompositionDelayMax() throws RemoteException;

    int getCompositionSizeMax() throws RemoteException;

    float getFrequencyMinimum() throws RemoteException;

    float getFrequencyResolution() throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    int getPrimitiveDuration(int i) throws RemoteException;

    int getPwleCompositionSizeMax() throws RemoteException;

    int getPwlePrimitiveDurationMax() throws RemoteException;

    float getQFactor() throws RemoteException;

    float getResonantFrequency() throws RemoteException;

    int[] getSupportedAlwaysOnEffects() throws RemoteException;

    int[] getSupportedBraking() throws RemoteException;

    int[] getSupportedEffects() throws RemoteException;

    int[] getSupportedPrimitives() throws RemoteException;

    void off() throws RemoteException;

    void on(int i, IVibratorCallback iVibratorCallback) throws RemoteException;

    int perform(int i, byte b, IVibratorCallback iVibratorCallback) throws RemoteException;

    void setAmplitude(float f) throws RemoteException;

    void setExternalControl(boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IVibrator {
        @Override // android.hardware.vibrator.IVibrator
        public int getCapabilities() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public void off() throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public void on(int timeoutMs, IVibratorCallback callback) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public int perform(int effect, byte strength, IVibratorCallback callback) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public int[] getSupportedEffects() throws RemoteException {
            return null;
        }

        @Override // android.hardware.vibrator.IVibrator
        public void setAmplitude(float amplitude) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public void setExternalControl(boolean enabled) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public int getCompositionDelayMax() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public int getCompositionSizeMax() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public int[] getSupportedPrimitives() throws RemoteException {
            return null;
        }

        @Override // android.hardware.vibrator.IVibrator
        public int getPrimitiveDuration(int primitive) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public void compose(CompositeEffect[] composite, IVibratorCallback callback) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public int[] getSupportedAlwaysOnEffects() throws RemoteException {
            return null;
        }

        @Override // android.hardware.vibrator.IVibrator
        public void alwaysOnEnable(int id, int effect, byte strength) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public void alwaysOnDisable(int id) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public float getResonantFrequency() throws RemoteException {
            return 0.0f;
        }

        @Override // android.hardware.vibrator.IVibrator
        public float getQFactor() throws RemoteException {
            return 0.0f;
        }

        @Override // android.hardware.vibrator.IVibrator
        public float getFrequencyResolution() throws RemoteException {
            return 0.0f;
        }

        @Override // android.hardware.vibrator.IVibrator
        public float getFrequencyMinimum() throws RemoteException {
            return 0.0f;
        }

        @Override // android.hardware.vibrator.IVibrator
        public float[] getBandwidthAmplitudeMap() throws RemoteException {
            return null;
        }

        @Override // android.hardware.vibrator.IVibrator
        public int getPwlePrimitiveDurationMax() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public int getPwleCompositionSizeMax() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public int[] getSupportedBraking() throws RemoteException {
            return null;
        }

        @Override // android.hardware.vibrator.IVibrator
        public void composePwle(PrimitivePwle[] composite, IVibratorCallback callback) throws RemoteException {
        }

        @Override // android.hardware.vibrator.IVibrator
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.hardware.vibrator.IVibrator
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVibrator {
        static final int TRANSACTION_alwaysOnDisable = 15;
        static final int TRANSACTION_alwaysOnEnable = 14;
        static final int TRANSACTION_compose = 12;
        static final int TRANSACTION_composePwle = 24;
        static final int TRANSACTION_getBandwidthAmplitudeMap = 20;
        static final int TRANSACTION_getCapabilities = 1;
        static final int TRANSACTION_getCompositionDelayMax = 8;
        static final int TRANSACTION_getCompositionSizeMax = 9;
        static final int TRANSACTION_getFrequencyMinimum = 19;
        static final int TRANSACTION_getFrequencyResolution = 18;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_getPrimitiveDuration = 11;
        static final int TRANSACTION_getPwleCompositionSizeMax = 22;
        static final int TRANSACTION_getPwlePrimitiveDurationMax = 21;
        static final int TRANSACTION_getQFactor = 17;
        static final int TRANSACTION_getResonantFrequency = 16;
        static final int TRANSACTION_getSupportedAlwaysOnEffects = 13;
        static final int TRANSACTION_getSupportedBraking = 23;
        static final int TRANSACTION_getSupportedEffects = 5;
        static final int TRANSACTION_getSupportedPrimitives = 10;
        static final int TRANSACTION_off = 2;
        static final int TRANSACTION_on = 3;
        static final int TRANSACTION_perform = 4;
        static final int TRANSACTION_setAmplitude = 6;
        static final int TRANSACTION_setExternalControl = 7;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IVibrator asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVibrator)) {
                return new Proxy(obj);
            }
            return (IVibrator) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case 16777214:
                    data.enforceInterface(descriptor);
                    reply.writeNoException();
                    reply.writeString(getInterfaceHash());
                    return true;
                case 16777215:
                    data.enforceInterface(descriptor);
                    reply.writeNoException();
                    reply.writeInt(getInterfaceVersion());
                    return true;
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            int _result = getCapabilities();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            off();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg0 = data.readInt();
                            IVibratorCallback _arg1 = IVibratorCallback.Stub.asInterface(data.readStrongBinder());
                            on(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            int _arg02 = data.readInt();
                            byte _arg12 = data.readByte();
                            IVibratorCallback _arg2 = IVibratorCallback.Stub.asInterface(data.readStrongBinder());
                            int _result2 = perform(_arg02, _arg12, _arg2);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            int[] _result3 = getSupportedEffects();
                            reply.writeNoException();
                            reply.writeIntArray(_result3);
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            float _arg03 = data.readFloat();
                            setAmplitude(_arg03);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(descriptor);
                            boolean _arg04 = data.readInt() != 0;
                            setExternalControl(_arg04);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(descriptor);
                            int _result4 = getCompositionDelayMax();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 9:
                            data.enforceInterface(descriptor);
                            int _result5 = getCompositionSizeMax();
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            return true;
                        case 10:
                            data.enforceInterface(descriptor);
                            int[] _result6 = getSupportedPrimitives();
                            reply.writeNoException();
                            reply.writeIntArray(_result6);
                            return true;
                        case 11:
                            data.enforceInterface(descriptor);
                            int _arg05 = data.readInt();
                            int _result7 = getPrimitiveDuration(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 12:
                            data.enforceInterface(descriptor);
                            CompositeEffect[] _arg06 = (CompositeEffect[]) data.createTypedArray(CompositeEffect.CREATOR);
                            IVibratorCallback _arg13 = IVibratorCallback.Stub.asInterface(data.readStrongBinder());
                            compose(_arg06, _arg13);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(descriptor);
                            int[] _result8 = getSupportedAlwaysOnEffects();
                            reply.writeNoException();
                            reply.writeIntArray(_result8);
                            return true;
                        case 14:
                            data.enforceInterface(descriptor);
                            int _arg07 = data.readInt();
                            int _arg14 = data.readInt();
                            byte _arg22 = data.readByte();
                            alwaysOnEnable(_arg07, _arg14, _arg22);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(descriptor);
                            int _arg08 = data.readInt();
                            alwaysOnDisable(_arg08);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(descriptor);
                            float _result9 = getResonantFrequency();
                            reply.writeNoException();
                            reply.writeFloat(_result9);
                            return true;
                        case 17:
                            data.enforceInterface(descriptor);
                            float _result10 = getQFactor();
                            reply.writeNoException();
                            reply.writeFloat(_result10);
                            return true;
                        case 18:
                            data.enforceInterface(descriptor);
                            float _result11 = getFrequencyResolution();
                            reply.writeNoException();
                            reply.writeFloat(_result11);
                            return true;
                        case 19:
                            data.enforceInterface(descriptor);
                            float _result12 = getFrequencyMinimum();
                            reply.writeNoException();
                            reply.writeFloat(_result12);
                            return true;
                        case 20:
                            data.enforceInterface(descriptor);
                            float[] _result13 = getBandwidthAmplitudeMap();
                            reply.writeNoException();
                            reply.writeFloatArray(_result13);
                            return true;
                        case 21:
                            data.enforceInterface(descriptor);
                            int _result14 = getPwlePrimitiveDurationMax();
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 22:
                            data.enforceInterface(descriptor);
                            int _result15 = getPwleCompositionSizeMax();
                            reply.writeNoException();
                            reply.writeInt(_result15);
                            return true;
                        case 23:
                            data.enforceInterface(descriptor);
                            int[] _result16 = getSupportedBraking();
                            reply.writeNoException();
                            reply.writeIntArray(_result16);
                            return true;
                        case 24:
                            data.enforceInterface(descriptor);
                            PrimitivePwle[] _arg09 = (PrimitivePwle[]) data.createTypedArray(PrimitivePwle.CREATOR);
                            IVibratorCallback _arg15 = IVibratorCallback.Stub.asInterface(data.readStrongBinder());
                            composePwle(_arg09, _arg15);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVibrator {
            public static IVibrator sDefaultImpl;
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

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

            @Override // android.hardware.vibrator.IVibrator
            public int getCapabilities() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCapabilities();
                    } else {
                        throw new RemoteException("Method getCapabilities is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void off() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().off();
                    } else {
                        throw new RemoteException("Method off is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void on(int timeoutMs, IVibratorCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(timeoutMs);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().on(timeoutMs, callback);
                    } else {
                        throw new RemoteException("Method on is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int perform(int effect, byte strength, IVibratorCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(effect);
                    _data.writeByte(strength);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().perform(effect, strength, callback);
                    } else {
                        throw new RemoteException("Method perform is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int[] getSupportedEffects() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int[] _result = _reply.createIntArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedEffects();
                    } else {
                        throw new RemoteException("Method getSupportedEffects is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void setAmplitude(float amplitude) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeFloat(amplitude);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAmplitude(amplitude);
                    } else {
                        throw new RemoteException("Method setAmplitude is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void setExternalControl(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setExternalControl(enabled);
                    } else {
                        throw new RemoteException("Method setExternalControl is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int getCompositionDelayMax() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCompositionDelayMax();
                    } else {
                        throw new RemoteException("Method getCompositionDelayMax is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int getCompositionSizeMax() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCompositionSizeMax();
                    } else {
                        throw new RemoteException("Method getCompositionSizeMax is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int[] getSupportedPrimitives() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int[] _result = _reply.createIntArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedPrimitives();
                    } else {
                        throw new RemoteException("Method getSupportedPrimitives is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int getPrimitiveDuration(int primitive) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(primitive);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrimitiveDuration(primitive);
                    } else {
                        throw new RemoteException("Method getPrimitiveDuration is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void compose(CompositeEffect[] composite, IVibratorCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedArray(composite, 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().compose(composite, callback);
                    } else {
                        throw new RemoteException("Method compose is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int[] getSupportedAlwaysOnEffects() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int[] _result = _reply.createIntArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedAlwaysOnEffects();
                    } else {
                        throw new RemoteException("Method getSupportedAlwaysOnEffects is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void alwaysOnEnable(int id, int effect, byte strength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(id);
                    _data.writeInt(effect);
                    _data.writeByte(strength);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().alwaysOnEnable(id, effect, strength);
                    } else {
                        throw new RemoteException("Method alwaysOnEnable is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void alwaysOnDisable(int id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(id);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().alwaysOnDisable(id);
                    } else {
                        throw new RemoteException("Method alwaysOnDisable is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public float getResonantFrequency() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        float _result = _reply.readFloat();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getResonantFrequency();
                    } else {
                        throw new RemoteException("Method getResonantFrequency is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public float getQFactor() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        float _result = _reply.readFloat();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getQFactor();
                    } else {
                        throw new RemoteException("Method getQFactor is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public float getFrequencyResolution() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        float _result = _reply.readFloat();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFrequencyResolution();
                    } else {
                        throw new RemoteException("Method getFrequencyResolution is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public float getFrequencyMinimum() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        float _result = _reply.readFloat();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFrequencyMinimum();
                    } else {
                        throw new RemoteException("Method getFrequencyMinimum is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public float[] getBandwidthAmplitudeMap() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        float[] _result = _reply.createFloatArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBandwidthAmplitudeMap();
                    } else {
                        throw new RemoteException("Method getBandwidthAmplitudeMap is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int getPwlePrimitiveDurationMax() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPwlePrimitiveDurationMax();
                    } else {
                        throw new RemoteException("Method getPwlePrimitiveDurationMax is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int getPwleCompositionSizeMax() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int _result = _reply.readInt();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPwleCompositionSizeMax();
                    } else {
                        throw new RemoteException("Method getPwleCompositionSizeMax is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int[] getSupportedBraking() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        int[] _result = _reply.createIntArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedBraking();
                    } else {
                        throw new RemoteException("Method getSupportedBraking is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public void composePwle(PrimitivePwle[] composite, IVibratorCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedArray(composite, 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().composePwle(composite, callback);
                    } else {
                        throw new RemoteException("Method composePwle is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.vibrator.IVibrator
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        boolean _status = this.mRemote.transact(16777215, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceVersion();
                        }
                        reply.readException();
                        this.mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedVersion;
            }

            @Override // android.hardware.vibrator.IVibrator
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(this.mCachedHash)) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16777214, data, reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        reply.readException();
                        this.mCachedHash = reply.readString();
                        reply.recycle();
                        data.recycle();
                    } else {
                        String interfaceHash = Stub.getDefaultImpl().getInterfaceHash();
                        reply.recycle();
                        data.recycle();
                        return interfaceHash;
                    }
                }
                return this.mCachedHash;
            }
        }

        public static boolean setDefaultImpl(IVibrator impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVibrator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
