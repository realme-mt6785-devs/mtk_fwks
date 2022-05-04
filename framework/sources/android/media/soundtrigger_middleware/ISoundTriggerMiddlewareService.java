package android.media.soundtrigger_middleware;

import android.media.permission.Identity;
import android.media.soundtrigger_middleware.ISoundTriggerCallback;
import android.media.soundtrigger_middleware.ISoundTriggerModule;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ISoundTriggerMiddlewareService extends IInterface {
    public static final String DESCRIPTOR = "android$media$soundtrigger_middleware$ISoundTriggerMiddlewareService".replace('$', '.');

    ISoundTriggerModule attachAsMiddleman(int i, Identity identity, Identity identity2, ISoundTriggerCallback iSoundTriggerCallback) throws RemoteException;

    ISoundTriggerModule attachAsOriginator(int i, Identity identity, ISoundTriggerCallback iSoundTriggerCallback) throws RemoteException;

    SoundTriggerModuleDescriptor[] listModulesAsMiddleman(Identity identity, Identity identity2) throws RemoteException;

    SoundTriggerModuleDescriptor[] listModulesAsOriginator(Identity identity) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ISoundTriggerMiddlewareService {
        @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
        public SoundTriggerModuleDescriptor[] listModulesAsOriginator(Identity identity) throws RemoteException {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
        public SoundTriggerModuleDescriptor[] listModulesAsMiddleman(Identity middlemanIdentity, Identity originatorIdentity) throws RemoteException {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
        public ISoundTriggerModule attachAsOriginator(int handle, Identity identity, ISoundTriggerCallback callback) throws RemoteException {
            return null;
        }

        @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
        public ISoundTriggerModule attachAsMiddleman(int handle, Identity middlemanIdentity, Identity originatorIdentity, ISoundTriggerCallback callback) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISoundTriggerMiddlewareService {
        static final int TRANSACTION_attachAsMiddleman = 4;
        static final int TRANSACTION_attachAsOriginator = 3;
        static final int TRANSACTION_listModulesAsMiddleman = 2;
        static final int TRANSACTION_listModulesAsOriginator = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISoundTriggerMiddlewareService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISoundTriggerMiddlewareService)) {
                return new Proxy(obj);
            }
            return (ISoundTriggerMiddlewareService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Identity _arg0;
            Identity _arg02;
            Identity _arg1;
            Identity _arg12;
            Identity _arg13;
            Identity _arg2;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            SoundTriggerModuleDescriptor[] _result = listModulesAsOriginator(_arg0);
                            reply.writeNoException();
                            reply.writeTypedArray(_result, 1);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg02 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            SoundTriggerModuleDescriptor[] _result2 = listModulesAsMiddleman(_arg02, _arg1);
                            reply.writeNoException();
                            reply.writeTypedArray(_result2, 1);
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            ISoundTriggerCallback _arg22 = ISoundTriggerCallback.Stub.asInterface(data.readStrongBinder());
                            ISoundTriggerModule _result3 = attachAsOriginator(_arg03, _arg12, _arg22);
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = Identity.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            ISoundTriggerCallback _arg3 = ISoundTriggerCallback.Stub.asInterface(data.readStrongBinder());
                            ISoundTriggerModule _result4 = attachAsMiddleman(_arg04, _arg13, _arg2, _arg3);
                            reply.writeNoException();
                            if (_result4 != null) {
                                iBinder = _result4.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ISoundTriggerMiddlewareService {
            public static ISoundTriggerMiddlewareService sDefaultImpl;
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

            @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
            public SoundTriggerModuleDescriptor[] listModulesAsOriginator(Identity identity) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (identity != null) {
                        _data.writeInt(1);
                        identity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listModulesAsOriginator(identity);
                    }
                    _reply.readException();
                    SoundTriggerModuleDescriptor[] _result = (SoundTriggerModuleDescriptor[]) _reply.createTypedArray(SoundTriggerModuleDescriptor.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
            public SoundTriggerModuleDescriptor[] listModulesAsMiddleman(Identity middlemanIdentity, Identity originatorIdentity) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (middlemanIdentity != null) {
                        _data.writeInt(1);
                        middlemanIdentity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (originatorIdentity != null) {
                        _data.writeInt(1);
                        originatorIdentity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listModulesAsMiddleman(middlemanIdentity, originatorIdentity);
                    }
                    _reply.readException();
                    SoundTriggerModuleDescriptor[] _result = (SoundTriggerModuleDescriptor[]) _reply.createTypedArray(SoundTriggerModuleDescriptor.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
            public ISoundTriggerModule attachAsOriginator(int handle, Identity identity, ISoundTriggerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(handle);
                    if (identity != null) {
                        _data.writeInt(1);
                        identity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attachAsOriginator(handle, identity, callback);
                    }
                    _reply.readException();
                    ISoundTriggerModule _result = ISoundTriggerModule.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.soundtrigger_middleware.ISoundTriggerMiddlewareService
            public ISoundTriggerModule attachAsMiddleman(int handle, Identity middlemanIdentity, Identity originatorIdentity, ISoundTriggerCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(handle);
                    if (middlemanIdentity != null) {
                        _data.writeInt(1);
                        middlemanIdentity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (originatorIdentity != null) {
                        _data.writeInt(1);
                        originatorIdentity.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attachAsMiddleman(handle, middlemanIdentity, originatorIdentity, callback);
                    }
                    _reply.readException();
                    ISoundTriggerModule _result = ISoundTriggerModule.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISoundTriggerMiddlewareService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISoundTriggerMiddlewareService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
