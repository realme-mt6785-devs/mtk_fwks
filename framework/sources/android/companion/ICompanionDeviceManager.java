package android.companion;

import android.app.PendingIntent;
import android.companion.IFindDeviceCallback;
import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface ICompanionDeviceManager extends IInterface {
    void associate(AssociationRequest associationRequest, IFindDeviceCallback iFindDeviceCallback, String str) throws RemoteException;

    boolean canPairWithoutPrompt(String str, String str2, int i) throws RemoteException;

    void createAssociation(String str, String str2, int i, byte[] bArr) throws RemoteException;

    void disassociate(String str, String str2) throws RemoteException;

    List<String> getAssociations(String str, int i) throws RemoteException;

    List<Association> getAssociationsForUser(int i) throws RemoteException;

    boolean hasNotificationAccess(ComponentName componentName) throws RemoteException;

    boolean isDeviceAssociatedForWifiConnection(String str, String str2, int i) throws RemoteException;

    void registerDevicePresenceListenerService(String str, String str2) throws RemoteException;

    PendingIntent requestNotificationAccess(ComponentName componentName) throws RemoteException;

    void stopScan(AssociationRequest associationRequest, IFindDeviceCallback iFindDeviceCallback, String str) throws RemoteException;

    void unregisterDevicePresenceListenerService(String str, String str2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICompanionDeviceManager {
        @Override // android.companion.ICompanionDeviceManager
        public void associate(AssociationRequest request, IFindDeviceCallback callback, String callingPackage) throws RemoteException {
        }

        @Override // android.companion.ICompanionDeviceManager
        public void stopScan(AssociationRequest request, IFindDeviceCallback callback, String callingPackage) throws RemoteException {
        }

        @Override // android.companion.ICompanionDeviceManager
        public List<String> getAssociations(String callingPackage, int userId) throws RemoteException {
            return null;
        }

        @Override // android.companion.ICompanionDeviceManager
        public List<Association> getAssociationsForUser(int userId) throws RemoteException {
            return null;
        }

        @Override // android.companion.ICompanionDeviceManager
        public void disassociate(String deviceMacAddress, String callingPackage) throws RemoteException {
        }

        @Override // android.companion.ICompanionDeviceManager
        public boolean hasNotificationAccess(ComponentName component) throws RemoteException {
            return false;
        }

        @Override // android.companion.ICompanionDeviceManager
        public PendingIntent requestNotificationAccess(ComponentName component) throws RemoteException {
            return null;
        }

        @Override // android.companion.ICompanionDeviceManager
        public boolean isDeviceAssociatedForWifiConnection(String packageName, String macAddress, int userId) throws RemoteException {
            return false;
        }

        @Override // android.companion.ICompanionDeviceManager
        public void registerDevicePresenceListenerService(String packageName, String deviceAddress) throws RemoteException {
        }

        @Override // android.companion.ICompanionDeviceManager
        public void unregisterDevicePresenceListenerService(String packageName, String deviceAddress) throws RemoteException {
        }

        @Override // android.companion.ICompanionDeviceManager
        public boolean canPairWithoutPrompt(String packageName, String deviceMacAddress, int userId) throws RemoteException {
            return false;
        }

        @Override // android.companion.ICompanionDeviceManager
        public void createAssociation(String packageName, String macAddress, int userId, byte[] certificate) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICompanionDeviceManager {
        public static final String DESCRIPTOR = "android.companion.ICompanionDeviceManager";
        static final int TRANSACTION_associate = 1;
        static final int TRANSACTION_canPairWithoutPrompt = 11;
        static final int TRANSACTION_createAssociation = 12;
        static final int TRANSACTION_disassociate = 5;
        static final int TRANSACTION_getAssociations = 3;
        static final int TRANSACTION_getAssociationsForUser = 4;
        static final int TRANSACTION_hasNotificationAccess = 6;
        static final int TRANSACTION_isDeviceAssociatedForWifiConnection = 8;
        static final int TRANSACTION_registerDevicePresenceListenerService = 9;
        static final int TRANSACTION_requestNotificationAccess = 7;
        static final int TRANSACTION_stopScan = 2;
        static final int TRANSACTION_unregisterDevicePresenceListenerService = 10;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICompanionDeviceManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICompanionDeviceManager)) {
                return new Proxy(obj);
            }
            return (ICompanionDeviceManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "associate";
                case 2:
                    return "stopScan";
                case 3:
                    return "getAssociations";
                case 4:
                    return "getAssociationsForUser";
                case 5:
                    return "disassociate";
                case 6:
                    return "hasNotificationAccess";
                case 7:
                    return "requestNotificationAccess";
                case 8:
                    return "isDeviceAssociatedForWifiConnection";
                case 9:
                    return "registerDevicePresenceListenerService";
                case 10:
                    return "unregisterDevicePresenceListenerService";
                case 11:
                    return "canPairWithoutPrompt";
                case 12:
                    return "createAssociation";
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
            AssociationRequest _arg0;
            AssociationRequest _arg02;
            ComponentName _arg03;
            ComponentName _arg04;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = AssociationRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IFindDeviceCallback _arg1 = IFindDeviceCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg2 = data.readString();
                            associate(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = AssociationRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            IFindDeviceCallback _arg12 = IFindDeviceCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg22 = data.readString();
                            stopScan(_arg02, _arg12, _arg22);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg13 = data.readInt();
                            List<String> _result = getAssociations(_arg05, _arg13);
                            reply.writeNoException();
                            reply.writeStringList(_result);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            List<Association> _result2 = getAssociationsForUser(_arg06);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            String _arg14 = data.readString();
                            disassociate(_arg07, _arg14);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            boolean hasNotificationAccess = hasNotificationAccess(_arg03);
                            reply.writeNoException();
                            reply.writeInt(hasNotificationAccess ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            PendingIntent _result3 = requestNotificationAccess(_arg04);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            String _arg15 = data.readString();
                            int _arg23 = data.readInt();
                            boolean isDeviceAssociatedForWifiConnection = isDeviceAssociatedForWifiConnection(_arg08, _arg15, _arg23);
                            reply.writeNoException();
                            reply.writeInt(isDeviceAssociatedForWifiConnection ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _arg16 = data.readString();
                            registerDevicePresenceListenerService(_arg09, _arg16);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            String _arg17 = data.readString();
                            unregisterDevicePresenceListenerService(_arg010, _arg17);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            String _arg18 = data.readString();
                            int _arg24 = data.readInt();
                            boolean canPairWithoutPrompt = canPairWithoutPrompt(_arg011, _arg18, _arg24);
                            reply.writeNoException();
                            reply.writeInt(canPairWithoutPrompt ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            String _arg19 = data.readString();
                            int _arg25 = data.readInt();
                            byte[] _arg3 = data.createByteArray();
                            createAssociation(_arg012, _arg19, _arg25, _arg3);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICompanionDeviceManager {
            public static ICompanionDeviceManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.companion.ICompanionDeviceManager
            public void associate(AssociationRequest request, IFindDeviceCallback callback, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().associate(request, callback, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public void stopScan(AssociationRequest request, IFindDeviceCallback callback, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopScan(request, callback, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public List<String> getAssociations(String callingPackage, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAssociations(callingPackage, userId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public List<Association> getAssociationsForUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAssociationsForUser(userId);
                    }
                    _reply.readException();
                    List<Association> _result = _reply.createTypedArrayList(Association.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public void disassociate(String deviceMacAddress, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceMacAddress);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disassociate(deviceMacAddress, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public boolean hasNotificationAccess(ComponentName component) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasNotificationAccess(component);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public PendingIntent requestNotificationAccess(ComponentName component) throws RemoteException {
                PendingIntent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestNotificationAccess(component);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PendingIntent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public boolean isDeviceAssociatedForWifiConnection(String packageName, String macAddress, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(macAddress);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDeviceAssociatedForWifiConnection(packageName, macAddress, userId);
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

            @Override // android.companion.ICompanionDeviceManager
            public void registerDevicePresenceListenerService(String packageName, String deviceAddress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(deviceAddress);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerDevicePresenceListenerService(packageName, deviceAddress);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public void unregisterDevicePresenceListenerService(String packageName, String deviceAddress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(deviceAddress);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterDevicePresenceListenerService(packageName, deviceAddress);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.companion.ICompanionDeviceManager
            public boolean canPairWithoutPrompt(String packageName, String deviceMacAddress, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(deviceMacAddress);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canPairWithoutPrompt(packageName, deviceMacAddress, userId);
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

            @Override // android.companion.ICompanionDeviceManager
            public void createAssociation(String packageName, String macAddress, int userId, byte[] certificate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(macAddress);
                    _data.writeInt(userId);
                    _data.writeByteArray(certificate);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createAssociation(packageName, macAddress, userId, certificate);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICompanionDeviceManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICompanionDeviceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
