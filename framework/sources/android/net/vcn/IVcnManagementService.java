package android.net.vcn;

import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.vcn.IVcnStatusCallback;
import android.net.vcn.IVcnUnderlyingNetworkPolicyListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IVcnManagementService extends IInterface {
    public static final String DESCRIPTOR = "android.net.vcn.IVcnManagementService";

    void addVcnUnderlyingNetworkPolicyListener(IVcnUnderlyingNetworkPolicyListener iVcnUnderlyingNetworkPolicyListener) throws RemoteException;

    void clearVcnConfig(ParcelUuid parcelUuid, String str) throws RemoteException;

    List<ParcelUuid> getConfiguredSubscriptionGroups(String str) throws RemoteException;

    VcnUnderlyingNetworkPolicy getUnderlyingNetworkPolicy(NetworkCapabilities networkCapabilities, LinkProperties linkProperties) throws RemoteException;

    void registerVcnStatusCallback(ParcelUuid parcelUuid, IVcnStatusCallback iVcnStatusCallback, String str) throws RemoteException;

    void removeVcnUnderlyingNetworkPolicyListener(IVcnUnderlyingNetworkPolicyListener iVcnUnderlyingNetworkPolicyListener) throws RemoteException;

    void setVcnConfig(ParcelUuid parcelUuid, VcnConfig vcnConfig, String str) throws RemoteException;

    void unregisterVcnStatusCallback(IVcnStatusCallback iVcnStatusCallback) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IVcnManagementService {
        @Override // android.net.vcn.IVcnManagementService
        public void setVcnConfig(ParcelUuid subscriptionGroup, VcnConfig config, String opPkgName) throws RemoteException {
        }

        @Override // android.net.vcn.IVcnManagementService
        public void clearVcnConfig(ParcelUuid subscriptionGroup, String opPkgName) throws RemoteException {
        }

        @Override // android.net.vcn.IVcnManagementService
        public List<ParcelUuid> getConfiguredSubscriptionGroups(String opPkgName) throws RemoteException {
            return null;
        }

        @Override // android.net.vcn.IVcnManagementService
        public void addVcnUnderlyingNetworkPolicyListener(IVcnUnderlyingNetworkPolicyListener listener) throws RemoteException {
        }

        @Override // android.net.vcn.IVcnManagementService
        public void removeVcnUnderlyingNetworkPolicyListener(IVcnUnderlyingNetworkPolicyListener listener) throws RemoteException {
        }

        @Override // android.net.vcn.IVcnManagementService
        public VcnUnderlyingNetworkPolicy getUnderlyingNetworkPolicy(NetworkCapabilities nc, LinkProperties lp) throws RemoteException {
            return null;
        }

        @Override // android.net.vcn.IVcnManagementService
        public void registerVcnStatusCallback(ParcelUuid subscriptionGroup, IVcnStatusCallback callback, String opPkgName) throws RemoteException {
        }

        @Override // android.net.vcn.IVcnManagementService
        public void unregisterVcnStatusCallback(IVcnStatusCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVcnManagementService {
        static final int TRANSACTION_addVcnUnderlyingNetworkPolicyListener = 4;
        static final int TRANSACTION_clearVcnConfig = 2;
        static final int TRANSACTION_getConfiguredSubscriptionGroups = 3;
        static final int TRANSACTION_getUnderlyingNetworkPolicy = 6;
        static final int TRANSACTION_registerVcnStatusCallback = 7;
        static final int TRANSACTION_removeVcnUnderlyingNetworkPolicyListener = 5;
        static final int TRANSACTION_setVcnConfig = 1;
        static final int TRANSACTION_unregisterVcnStatusCallback = 8;

        public Stub() {
            attachInterface(this, IVcnManagementService.DESCRIPTOR);
        }

        public static IVcnManagementService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IVcnManagementService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IVcnManagementService)) {
                return new Proxy(obj);
            }
            return (IVcnManagementService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setVcnConfig";
                case 2:
                    return "clearVcnConfig";
                case 3:
                    return "getConfiguredSubscriptionGroups";
                case 4:
                    return "addVcnUnderlyingNetworkPolicyListener";
                case 5:
                    return "removeVcnUnderlyingNetworkPolicyListener";
                case 6:
                    return "getUnderlyingNetworkPolicy";
                case 7:
                    return "registerVcnStatusCallback";
                case 8:
                    return "unregisterVcnStatusCallback";
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
            ParcelUuid _arg0;
            VcnConfig _arg1;
            ParcelUuid _arg02;
            NetworkCapabilities _arg03;
            LinkProperties _arg12;
            ParcelUuid _arg04;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IVcnManagementService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = VcnConfig.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg2 = data.readString();
                            setVcnConfig(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _arg13 = data.readString();
                            clearVcnConfig(_arg02, _arg13);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            String _arg05 = data.readString();
                            List<ParcelUuid> _result = getConfiguredSubscriptionGroups(_arg05);
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 4:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            IVcnUnderlyingNetworkPolicyListener _arg06 = IVcnUnderlyingNetworkPolicyListener.Stub.asInterface(data.readStrongBinder());
                            addVcnUnderlyingNetworkPolicyListener(_arg06);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            IVcnUnderlyingNetworkPolicyListener _arg07 = IVcnUnderlyingNetworkPolicyListener.Stub.asInterface(data.readStrongBinder());
                            removeVcnUnderlyingNetworkPolicyListener(_arg07);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = (NetworkCapabilities) NetworkCapabilities.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            VcnUnderlyingNetworkPolicy _result2 = getUnderlyingNetworkPolicy(_arg03, _arg12);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 7:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            IVcnStatusCallback _arg14 = IVcnStatusCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg22 = data.readString();
                            registerVcnStatusCallback(_arg04, _arg14, _arg22);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IVcnManagementService.DESCRIPTOR);
                            IVcnStatusCallback _arg08 = IVcnStatusCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterVcnStatusCallback(_arg08);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVcnManagementService {
            public static IVcnManagementService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IVcnManagementService.DESCRIPTOR;
            }

            @Override // android.net.vcn.IVcnManagementService
            public void setVcnConfig(ParcelUuid subscriptionGroup, VcnConfig config, String opPkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    if (subscriptionGroup != null) {
                        _data.writeInt(1);
                        subscriptionGroup.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(opPkgName);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVcnConfig(subscriptionGroup, config, opPkgName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnManagementService
            public void clearVcnConfig(ParcelUuid subscriptionGroup, String opPkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    if (subscriptionGroup != null) {
                        _data.writeInt(1);
                        subscriptionGroup.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(opPkgName);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearVcnConfig(subscriptionGroup, opPkgName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnManagementService
            public List<ParcelUuid> getConfiguredSubscriptionGroups(String opPkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    _data.writeString(opPkgName);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfiguredSubscriptionGroups(opPkgName);
                    }
                    _reply.readException();
                    List<ParcelUuid> _result = _reply.createTypedArrayList(ParcelUuid.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnManagementService
            public void addVcnUnderlyingNetworkPolicyListener(IVcnUnderlyingNetworkPolicyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addVcnUnderlyingNetworkPolicyListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnManagementService
            public void removeVcnUnderlyingNetworkPolicyListener(IVcnUnderlyingNetworkPolicyListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeVcnUnderlyingNetworkPolicyListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnManagementService
            public VcnUnderlyingNetworkPolicy getUnderlyingNetworkPolicy(NetworkCapabilities nc, LinkProperties lp) throws RemoteException {
                VcnUnderlyingNetworkPolicy _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    if (nc != null) {
                        _data.writeInt(1);
                        nc.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (lp != null) {
                        _data.writeInt(1);
                        lp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnderlyingNetworkPolicy(nc, lp);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VcnUnderlyingNetworkPolicy.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnManagementService
            public void registerVcnStatusCallback(ParcelUuid subscriptionGroup, IVcnStatusCallback callback, String opPkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    if (subscriptionGroup != null) {
                        _data.writeInt(1);
                        subscriptionGroup.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(opPkgName);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerVcnStatusCallback(subscriptionGroup, callback, opPkgName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.vcn.IVcnManagementService
            public void unregisterVcnStatusCallback(IVcnStatusCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnManagementService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterVcnStatusCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVcnManagementService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVcnManagementService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
