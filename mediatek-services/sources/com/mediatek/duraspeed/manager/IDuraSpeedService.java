package com.mediatek.duraspeed.manager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IDuraSpeedService extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.duraspeed.manager.IDuraSpeedService";

    List<String> getPlatformWhitelist() throws RemoteException;

    List<String> getRestrictList() throws RemoteException;

    void setAppWhitelist(List<String> list) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDuraSpeedService {
        @Override // com.mediatek.duraspeed.manager.IDuraSpeedService
        public List<String> getPlatformWhitelist() throws RemoteException {
            return null;
        }

        @Override // com.mediatek.duraspeed.manager.IDuraSpeedService
        public void setAppWhitelist(List<String> appWhitelist) throws RemoteException {
        }

        @Override // com.mediatek.duraspeed.manager.IDuraSpeedService
        public List<String> getRestrictList() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDuraSpeedService {
        static final int TRANSACTION_getPlatformWhitelist = 1;
        static final int TRANSACTION_getRestrictList = 3;
        static final int TRANSACTION_setAppWhitelist = 2;

        public Stub() {
            attachInterface(this, IDuraSpeedService.DESCRIPTOR);
        }

        public static IDuraSpeedService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDuraSpeedService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDuraSpeedService)) {
                return new Proxy(obj);
            }
            return (IDuraSpeedService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IDuraSpeedService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDuraSpeedService.DESCRIPTOR);
                            List<String> _result = getPlatformWhitelist();
                            reply.writeNoException();
                            reply.writeStringList(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IDuraSpeedService.DESCRIPTOR);
                            List<String> _arg0 = data.createStringArrayList();
                            setAppWhitelist(_arg0);
                            reply.writeNoException();
                            return true;
                        case TRANSACTION_getRestrictList /* 3 */:
                            data.enforceInterface(IDuraSpeedService.DESCRIPTOR);
                            List<String> _result2 = getRestrictList();
                            reply.writeNoException();
                            reply.writeStringList(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDuraSpeedService {
            public static IDuraSpeedService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDuraSpeedService.DESCRIPTOR;
            }

            @Override // com.mediatek.duraspeed.manager.IDuraSpeedService
            public List<String> getPlatformWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDuraSpeedService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlatformWhitelist();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.duraspeed.manager.IDuraSpeedService
            public void setAppWhitelist(List<String> appWhitelist) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDuraSpeedService.DESCRIPTOR);
                    _data.writeStringList(appWhitelist);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAppWhitelist(appWhitelist);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.duraspeed.manager.IDuraSpeedService
            public List<String> getRestrictList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDuraSpeedService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRestrictList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRestrictList();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDuraSpeedService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDuraSpeedService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
