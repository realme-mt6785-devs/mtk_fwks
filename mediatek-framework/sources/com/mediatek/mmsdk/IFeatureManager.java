package com.mediatek.mmsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IFeatureManager extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.mmsdk.IFeatureManager";

    int getEffectFactory(BinderHolder binderHolder) throws RemoteException;

    String getParameter(String str) throws RemoteException;

    int setParameter(String str, String str2) throws RemoteException;

    int setUp(EffectHalVersion effectHalVersion) throws RemoteException;

    int tearDown(EffectHalVersion effectHalVersion) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IFeatureManager {
        @Override // com.mediatek.mmsdk.IFeatureManager
        public int setParameter(String key, String value) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IFeatureManager
        public String getParameter(String key) throws RemoteException {
            return null;
        }

        @Override // com.mediatek.mmsdk.IFeatureManager
        public int setUp(EffectHalVersion version) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IFeatureManager
        public int tearDown(EffectHalVersion version) throws RemoteException {
            return 0;
        }

        @Override // com.mediatek.mmsdk.IFeatureManager
        public int getEffectFactory(BinderHolder effectFactory) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IFeatureManager {
        static final int TRANSACTION_getEffectFactory = 5;
        static final int TRANSACTION_getParameter = 2;
        static final int TRANSACTION_setParameter = 1;
        static final int TRANSACTION_setUp = 3;
        static final int TRANSACTION_tearDown = 4;

        public Stub() {
            attachInterface(this, IFeatureManager.DESCRIPTOR);
        }

        public static IFeatureManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IFeatureManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IFeatureManager)) {
                return new Proxy(obj);
            }
            return (IFeatureManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            EffectHalVersion _arg0;
            EffectHalVersion _arg02;
            switch (code) {
                case 1598968902:
                    reply.writeString(IFeatureManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IFeatureManager.DESCRIPTOR);
                            String _arg03 = data.readString();
                            String _arg1 = data.readString();
                            int _result = setParameter(_arg03, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IFeatureManager.DESCRIPTOR);
                            String _arg04 = data.readString();
                            String _result2 = getParameter(_arg04);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IFeatureManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = EffectHalVersion.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _result3 = setUp(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IFeatureManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = EffectHalVersion.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _result4 = tearDown(_arg02);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IFeatureManager.DESCRIPTOR);
                            BinderHolder _arg05 = new BinderHolder();
                            int _result5 = getEffectFactory(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result5);
                            reply.writeInt(1);
                            _arg05.writeToParcel(reply, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IFeatureManager {
            public static IFeatureManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IFeatureManager.DESCRIPTOR;
            }

            @Override // com.mediatek.mmsdk.IFeatureManager
            public int setParameter(String key, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFeatureManager.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
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

            @Override // com.mediatek.mmsdk.IFeatureManager
            public String getParameter(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFeatureManager.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParameter(key);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IFeatureManager
            public int setUp(EffectHalVersion version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFeatureManager.DESCRIPTOR);
                    if (version != null) {
                        _data.writeInt(1);
                        version.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUp(version);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IFeatureManager
            public int tearDown(EffectHalVersion version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFeatureManager.DESCRIPTOR);
                    if (version != null) {
                        _data.writeInt(1);
                        version.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tearDown(version);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.mmsdk.IFeatureManager
            public int getEffectFactory(BinderHolder effectFactory) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFeatureManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEffectFactory(effectFactory);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    if (_reply.readInt() != 0) {
                        effectFactory.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFeatureManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IFeatureManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
