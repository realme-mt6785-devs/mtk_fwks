package android.operator;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IOplusOperatorManager extends IInterface {
    public static final String DESCRIPTOR = "android.operator.IOplusOperatorManager";

    Map getConfigMap(Bundle bundle) throws RemoteException;

    List<String> getCotaAppPackageNameList() throws RemoteException;

    int getCotaMountState(String str) throws RemoteException;

    void grantCustomizedRuntimePermissions() throws RemoteException;

    boolean isInSimTriggeredSystemBlackList(String str) throws RemoteException;

    void mountCotaImage(Bundle bundle) throws RemoteException;

    void notifyCotaMounted() throws RemoteException;

    void notifyRegionSwitch(Bundle bundle) throws RemoteException;

    void notifySimSwitch(Bundle bundle) throws RemoteException;

    void notifySmartCustomizationStart() throws RemoteException;

    void testAidl() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IOplusOperatorManager {
        @Override // android.operator.IOplusOperatorManager
        public void testAidl() throws RemoteException {
        }

        @Override // android.operator.IOplusOperatorManager
        public Map getConfigMap(Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // android.operator.IOplusOperatorManager
        public void grantCustomizedRuntimePermissions() throws RemoteException {
        }

        @Override // android.operator.IOplusOperatorManager
        public void notifySmartCustomizationStart() throws RemoteException {
        }

        @Override // android.operator.IOplusOperatorManager
        public boolean isInSimTriggeredSystemBlackList(String pkgName) throws RemoteException {
            return false;
        }

        @Override // android.operator.IOplusOperatorManager
        public void notifySimSwitch(Bundle data) throws RemoteException {
        }

        @Override // android.operator.IOplusOperatorManager
        public void notifyRegionSwitch(Bundle data) throws RemoteException {
        }

        @Override // android.operator.IOplusOperatorManager
        public void mountCotaImage(Bundle data) throws RemoteException {
        }

        @Override // android.operator.IOplusOperatorManager
        public void notifyCotaMounted() throws RemoteException {
        }

        @Override // android.operator.IOplusOperatorManager
        public int getCotaMountState(String imagePath) throws RemoteException {
            return 0;
        }

        @Override // android.operator.IOplusOperatorManager
        public List<String> getCotaAppPackageNameList() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOplusOperatorManager {
        static final int TRANSACTION_getConfigMap = 2;
        static final int TRANSACTION_getCotaAppPackageNameList = 11;
        static final int TRANSACTION_getCotaMountState = 10;
        static final int TRANSACTION_grantCustomizedRuntimePermissions = 3;
        static final int TRANSACTION_isInSimTriggeredSystemBlackList = 5;
        static final int TRANSACTION_mountCotaImage = 8;
        static final int TRANSACTION_notifyCotaMounted = 9;
        static final int TRANSACTION_notifyRegionSwitch = 7;
        static final int TRANSACTION_notifySimSwitch = 6;
        static final int TRANSACTION_notifySmartCustomizationStart = 4;
        static final int TRANSACTION_testAidl = 1;

        public Stub() {
            attachInterface(this, IOplusOperatorManager.DESCRIPTOR);
        }

        public static IOplusOperatorManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusOperatorManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusOperatorManager)) {
                return new Proxy(obj);
            }
            return (IOplusOperatorManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "testAidl";
                case 2:
                    return "getConfigMap";
                case 3:
                    return "grantCustomizedRuntimePermissions";
                case 4:
                    return "notifySmartCustomizationStart";
                case 5:
                    return "isInSimTriggeredSystemBlackList";
                case 6:
                    return "notifySimSwitch";
                case 7:
                    return "notifyRegionSwitch";
                case 8:
                    return "mountCotaImage";
                case 9:
                    return "notifyCotaMounted";
                case 10:
                    return "getCotaMountState";
                case 11:
                    return "getCotaAppPackageNameList";
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
            Bundle _arg0;
            Bundle _arg02;
            Bundle _arg03;
            Bundle _arg04;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusOperatorManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            testAidl();
                            return true;
                        case 2:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            Map _result = getConfigMap(_arg0);
                            reply.writeNoException();
                            reply.writeMap(_result);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            grantCustomizedRuntimePermissions();
                            return true;
                        case 4:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            notifySmartCustomizationStart();
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            String _arg05 = data.readString();
                            boolean isInSimTriggeredSystemBlackList = isInSimTriggeredSystemBlackList(_arg05);
                            reply.writeNoException();
                            reply.writeInt(isInSimTriggeredSystemBlackList ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            notifySimSwitch(_arg02);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            notifyRegionSwitch(_arg03);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            mountCotaImage(_arg04);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            notifyCotaMounted();
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            String _arg06 = data.readString();
                            int _result2 = getCotaMountState(_arg06);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 11:
                            data.enforceInterface(IOplusOperatorManager.DESCRIPTOR);
                            List<String> _result3 = getCotaAppPackageNameList();
                            reply.writeNoException();
                            reply.writeStringList(_result3);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOplusOperatorManager {
            public static IOplusOperatorManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusOperatorManager.DESCRIPTOR;
            }

            @Override // android.operator.IOplusOperatorManager
            public void testAidl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().testAidl();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public Map getConfigMap(Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigMap(bundle);
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public void grantCustomizedRuntimePermissions() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantCustomizedRuntimePermissions();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public void notifySmartCustomizationStart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifySmartCustomizationStart();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public boolean isInSimTriggeredSystemBlackList(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInSimTriggeredSystemBlackList(pkgName);
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

            @Override // android.operator.IOplusOperatorManager
            public void notifySimSwitch(Bundle data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifySimSwitch(data);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public void notifyRegionSwitch(Bundle data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyRegionSwitch(data);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public void mountCotaImage(Bundle data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().mountCotaImage(data);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public void notifyCotaMounted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyCotaMounted();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public int getCotaMountState(String imagePath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    _data.writeString(imagePath);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCotaMountState(imagePath);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.operator.IOplusOperatorManager
            public List<String> getCotaAppPackageNameList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusOperatorManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCotaAppPackageNameList();
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

        public static boolean setDefaultImpl(IOplusOperatorManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusOperatorManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
