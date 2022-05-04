package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.mediatek.mmsdk.IEffectListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface IEffectHalClient extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IEffectHalClient";

    int abort(BaseParameters baseParameters) throws RemoteException;

    int addInputParameter(int i, BaseParameters baseParameters, long j, boolean z) throws RemoteException;

    int addOutputParameter(int i, BaseParameters baseParameters, long j, boolean z) throws RemoteException;

    int configure() throws RemoteException;

    int dequeueAndQueueBuf(long j) throws RemoteException;

    int getCaptureRequirement(BaseParameters baseParameters, List<BaseParameters> list) throws RemoteException;

    int getInputSurfaces(List<Surface> list) throws RemoteException;

    boolean getInputsyncMode(int i) throws RemoteException;

    int getNameVersion(EffectHalVersion effectHalVersion) throws RemoteException;

    boolean getOutputsyncMode(int i) throws RemoteException;

    int init() throws RemoteException;

    int prepare() throws RemoteException;

    int release() throws RemoteException;

    int setBaseParameter(BaseParameters baseParameters) throws RemoteException;

    int setEffectListener(IEffectListener iEffectListener) throws RemoteException;

    int setInputsyncMode(int i, boolean z) throws RemoteException;

    int setOutputSurfaces(List<Surface> list, List<BaseParameters> list2) throws RemoteException;

    int setOutputsyncMode(int i, boolean z) throws RemoteException;

    int setParameter(String str, String str2) throws RemoteException;

    int setParameters(BaseParameters baseParameters) throws RemoteException;

    long start() throws RemoteException;

    int unconfigure() throws RemoteException;

    int uninit() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IEffectHalClient {
        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int init() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int uninit() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int configure() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int unconfigure() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public long start() throws RemoteException {
            return 0L;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int abort(BaseParameters effectParameter) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int getNameVersion(EffectHalVersion version) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int setEffectListener(IEffectListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int setParameter(String key, String paramValue) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int setParameters(BaseParameters parameter) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int getCaptureRequirement(BaseParameters effectParameter, List<BaseParameters> requirement) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int prepare() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int release() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int getInputSurfaces(List<Surface> input) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int setOutputSurfaces(List<Surface> output, List<BaseParameters> parameters) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int addInputParameter(int index, BaseParameters parameter, long timestamp, boolean repeat) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int addOutputParameter(int index, BaseParameters parameter, long timestamp, boolean repeat) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int setInputsyncMode(int index, boolean sync) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public boolean getInputsyncMode(int index) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int setOutputsyncMode(int index, boolean sync) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public boolean getOutputsyncMode(int index) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int setBaseParameter(BaseParameters parameters) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHalClient
        public int dequeueAndQueueBuf(long timestamp) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IEffectHalClient {
        static final int TRANSACTION_abort = 6;
        static final int TRANSACTION_addInputParameter = 16;
        static final int TRANSACTION_addOutputParameter = 17;
        static final int TRANSACTION_configure = 3;
        static final int TRANSACTION_dequeueAndQueueBuf = 23;
        static final int TRANSACTION_getCaptureRequirement = 11;
        static final int TRANSACTION_getInputSurfaces = 14;
        static final int TRANSACTION_getInputsyncMode = 19;
        static final int TRANSACTION_getNameVersion = 7;
        static final int TRANSACTION_getOutputsyncMode = 21;
        static final int TRANSACTION_init = 1;
        static final int TRANSACTION_prepare = 12;
        static final int TRANSACTION_release = 13;
        static final int TRANSACTION_setBaseParameter = 22;
        static final int TRANSACTION_setEffectListener = 8;
        static final int TRANSACTION_setInputsyncMode = 18;
        static final int TRANSACTION_setOutputSurfaces = 15;
        static final int TRANSACTION_setOutputsyncMode = 20;
        static final int TRANSACTION_setParameter = 9;
        static final int TRANSACTION_setParameters = 10;
        static final int TRANSACTION_start = 5;
        static final int TRANSACTION_unconfigure = 4;
        static final int TRANSACTION_uninit = 2;

        public Stub() {
            attachInterface(this, IEffectHalClient.DESCRIPTOR);
        }

        public static IEffectHalClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEffectHalClient.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEffectHalClient)) {
                return new Proxy(obj);
            }
            return (IEffectHalClient) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BaseParameters _arg0;
            BaseParameters _arg02;
            BaseParameters _arg03;
            BaseParameters _arg1;
            boolean _arg3;
            BaseParameters _arg12;
            boolean _arg32;
            BaseParameters _arg04;
            switch (code) {
                case 1598968902:
                    reply.writeString(IEffectHalClient.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg13 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _result = init();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _result2 = uninit();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _result3 = configure();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _result4 = unconfigure();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            long _result5 = start();
                            reply.writeNoException();
                            reply.writeLong(_result5);
                            return true;
                        case 6:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _result6 = abort(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 7:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            EffectHalVersion _arg05 = new EffectHalVersion();
                            int _result7 = getNameVersion(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            reply.writeInt(1);
                            _arg05.writeToParcel(reply, 1);
                            return true;
                        case 8:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            IEffectListener _arg06 = IEffectListener.Stub.asInterface(data.readStrongBinder());
                            int _result8 = setEffectListener(_arg06);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 9:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _result9 = setParameter(_arg07, data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 10:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _result10 = setParameters(_arg02);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 11:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            ArrayList arrayList = new ArrayList();
                            int _result11 = getCaptureRequirement(_arg03, arrayList);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            reply.writeTypedList(arrayList);
                            return true;
                        case 12:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _result12 = prepare();
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 13:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _result13 = release();
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 14:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            ArrayList arrayList2 = new ArrayList();
                            int _result14 = getInputSurfaces(arrayList2);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            reply.writeTypedList(arrayList2);
                            return true;
                        case 15:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            List<Surface> _arg08 = data.createTypedArrayList(Surface.CREATOR);
                            int _result15 = setOutputSurfaces(_arg08, data.createTypedArrayList(BaseParameters.CREATOR));
                            reply.writeNoException();
                            reply.writeInt(_result15);
                            return true;
                        case 16:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            long _arg2 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            } else {
                                _arg3 = false;
                            }
                            int _result16 = addInputParameter(_arg09, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        case 17:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            long _arg22 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg32 = true;
                            } else {
                                _arg32 = false;
                            }
                            int _result17 = addOutputParameter(_arg010, _arg12, _arg22, _arg32);
                            reply.writeNoException();
                            reply.writeInt(_result17);
                            return true;
                        case TRANSACTION_setInputsyncMode /* 18 */:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = true;
                            }
                            int _result18 = setInputsyncMode(_arg011, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result18);
                            return true;
                        case TRANSACTION_getInputsyncMode /* 19 */:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _arg012 = data.readInt();
                            boolean inputsyncMode = getInputsyncMode(_arg012);
                            reply.writeNoException();
                            reply.writeInt(inputsyncMode ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _arg013 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = true;
                            }
                            int _result19 = setOutputsyncMode(_arg013, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result19);
                            return true;
                        case TRANSACTION_getOutputsyncMode /* 21 */:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            int _arg014 = data.readInt();
                            boolean outputsyncMode = getOutputsyncMode(_arg014);
                            reply.writeNoException();
                            reply.writeInt(outputsyncMode ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            int _result20 = setBaseParameter(_arg04);
                            reply.writeNoException();
                            reply.writeInt(_result20);
                            return true;
                        case 23:
                            data.enforceInterface(IEffectHalClient.DESCRIPTOR);
                            long _arg015 = data.readLong();
                            int _result21 = dequeueAndQueueBuf(_arg015);
                            reply.writeNoException();
                            reply.writeInt(_result21);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IEffectHalClient {
            public static IEffectHalClient sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEffectHalClient.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int init() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().init();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int uninit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().uninit();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int configure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().configure();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int unconfigure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unconfigure();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public long start() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().start();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int abort(BaseParameters effectParameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    if (effectParameter != null) {
                        _data.writeInt(1);
                        effectParameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().abort(effectParameter);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int getNameVersion(EffectHalVersion version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNameVersion(version);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    if (_reply.readInt() != 0) {
                        version.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int setEffectListener(IEffectListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEffectListener(listener);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int setParameter(String key, String paramValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeString(paramValue);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameter(key, paramValue);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int setParameters(BaseParameters parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameters(parameter);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int getCaptureRequirement(BaseParameters effectParameter, List<BaseParameters> requirement) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    if (effectParameter != null) {
                        _data.writeInt(1);
                        effectParameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCaptureRequirement(effectParameter, requirement);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readTypedList(requirement, BaseParameters.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int prepare() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().prepare();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int release() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().release();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int getInputSurfaces(List<Surface> input) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputSurfaces(input);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readTypedList(input, Surface.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int setOutputSurfaces(List<Surface> output, List<BaseParameters> parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeTypedList(output);
                    _data.writeTypedList(parameters);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOutputSurfaces(output, parameters);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int addInputParameter(int index, BaseParameters parameter, long timestamp, boolean repeat) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeInt(index);
                    int i = 1;
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(timestamp);
                    if (!repeat) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addInputParameter(index, parameter, timestamp, repeat);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int addOutputParameter(int index, BaseParameters parameter, long timestamp, boolean repeat) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeInt(index);
                    int i = 1;
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(timestamp);
                    if (!repeat) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addOutputParameter(index, parameter, timestamp, repeat);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int setInputsyncMode(int index, boolean sync) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeInt(index);
                    _data.writeInt(sync ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setInputsyncMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setInputsyncMode(index, sync);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public boolean getInputsyncMode(int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeInt(index);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getInputsyncMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputsyncMode(index);
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

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int setOutputsyncMode(int index, boolean sync) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeInt(index);
                    _data.writeInt(sync ? 1 : 0);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOutputsyncMode(index, sync);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public boolean getOutputsyncMode(int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeInt(index);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getOutputsyncMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOutputsyncMode(index);
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

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int setBaseParameter(BaseParameters parameters) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBaseParameter(parameters);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHalClient
            public int dequeueAndQueueBuf(long timestamp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHalClient.DESCRIPTOR);
                    _data.writeLong(timestamp);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dequeueAndQueueBuf(timestamp);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEffectHalClient impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEffectHalClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
