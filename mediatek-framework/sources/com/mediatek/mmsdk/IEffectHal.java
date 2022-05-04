package com.mediatek.mmsdk;

import android.graphics.GraphicBuffer;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.mediatek.mmsdk.IEffectListener;
/* loaded from: classes.dex */
public interface IEffectHal extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IEffectHal";

    int abort(BaseParameters baseParameters) throws RemoteException;

    int addInputFrame(GraphicBuffer graphicBuffer, BaseParameters baseParameters) throws RemoteException;

    int addOutputFrame(GraphicBuffer graphicBuffer, BaseParameters baseParameters) throws RemoteException;

    int configure() throws RemoteException;

    int getCaptureRequirement(BaseParameters baseParameters) throws RemoteException;

    int getNameVersion(EffectHalVersion effectHalVersion) throws RemoteException;

    int init() throws RemoteException;

    int prepare() throws RemoteException;

    int release() throws RemoteException;

    int setEffectListener(IEffectListener iEffectListener) throws RemoteException;

    int setParameter(String str, String str2) throws RemoteException;

    long start() throws RemoteException;

    int unconfigure() throws RemoteException;

    int uninit() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IEffectHal {
        @Override // com.mediatek.mmsdk.IEffectHal
        public int init() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int uninit() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int configure() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int unconfigure() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public long start() throws RemoteException {
            return 0L;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int abort(BaseParameters effectParameter) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int getNameVersion(EffectHalVersion version) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int setEffectListener(IEffectListener listener) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int setParameter(String key, String value) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int getCaptureRequirement(BaseParameters requirement) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int prepare() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int release() throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int addInputFrame(GraphicBuffer frame, BaseParameters parameter) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IEffectHal
        public int addOutputFrame(GraphicBuffer frame, BaseParameters parameter) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IEffectHal {
        static final int TRANSACTION_abort = 6;
        static final int TRANSACTION_addInputFrame = 13;
        static final int TRANSACTION_addOutputFrame = 14;
        static final int TRANSACTION_configure = 3;
        static final int TRANSACTION_getCaptureRequirement = 10;
        static final int TRANSACTION_getNameVersion = 7;
        static final int TRANSACTION_init = 1;
        static final int TRANSACTION_prepare = 11;
        static final int TRANSACTION_release = 12;
        static final int TRANSACTION_setEffectListener = 8;
        static final int TRANSACTION_setParameter = 9;
        static final int TRANSACTION_start = 5;
        static final int TRANSACTION_unconfigure = 4;
        static final int TRANSACTION_uninit = 2;

        public Stub() {
            attachInterface(this, IEffectHal.DESCRIPTOR);
        }

        public static IEffectHal asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEffectHal.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEffectHal)) {
                return new Proxy(obj);
            }
            return (IEffectHal) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BaseParameters _arg0;
            GraphicBuffer _arg02;
            BaseParameters _arg1;
            GraphicBuffer _arg03;
            BaseParameters _arg12;
            switch (code) {
                case 1598968902:
                    reply.writeString(IEffectHal.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            int _result = init();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            int _result2 = uninit();
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            int _result3 = configure();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            int _result4 = unconfigure();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            long _result5 = start();
                            reply.writeNoException();
                            reply.writeLong(_result5);
                            return true;
                        case 6:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
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
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            EffectHalVersion _arg04 = new EffectHalVersion();
                            int _result7 = getNameVersion(_arg04);
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            reply.writeInt(1);
                            _arg04.writeToParcel(reply, 1);
                            return true;
                        case 8:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            IEffectListener _arg05 = IEffectListener.Stub.asInterface(data.readStrongBinder());
                            int _result8 = setEffectListener(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 9:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            String _arg06 = data.readString();
                            String _arg13 = data.readString();
                            int _result9 = setParameter(_arg06, _arg13);
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 10:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            BaseParameters _arg07 = new BaseParameters();
                            int _result10 = getCaptureRequirement(_arg07);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            reply.writeInt(1);
                            _arg07.writeToParcel(reply, 1);
                            return true;
                        case 11:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            int _result11 = prepare();
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 12:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            int _result12 = release();
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 13:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = (GraphicBuffer) GraphicBuffer.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _result13 = addInputFrame(_arg02, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 14:
                            data.enforceInterface(IEffectHal.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = (GraphicBuffer) GraphicBuffer.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = BaseParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            int _result14 = addOutputFrame(_arg03, _arg12);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IEffectHal {
            public static IEffectHal sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEffectHal.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IEffectHal
            public int init() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int uninit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int configure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int unconfigure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public long start() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int abort(BaseParameters effectParameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int getNameVersion(EffectHalVersion version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int setEffectListener(IEffectListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int setParameter(String key, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameter(key, value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHal
            public int getCaptureRequirement(BaseParameters requirement) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCaptureRequirement(requirement);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    if (_reply.readInt() != 0) {
                        requirement.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHal
            public int prepare() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int release() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
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

            @Override // com.mediatek.mmsdk.IEffectHal
            public int addInputFrame(GraphicBuffer frame, BaseParameters parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
                    if (frame != null) {
                        _data.writeInt(1);
                        frame.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addInputFrame(frame, parameter);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IEffectHal
            public int addOutputFrame(GraphicBuffer frame, BaseParameters parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEffectHal.DESCRIPTOR);
                    if (frame != null) {
                        _data.writeInt(1);
                        frame.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (parameter != null) {
                        _data.writeInt(1);
                        parameter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addOutputFrame(frame, parameter);
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

        public static boolean setDefaultImpl(IEffectHal impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEffectHal getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
