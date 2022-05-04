package android.nfc;

import android.content.ComponentName;
import android.nfc.cardemulation.AidGroup;
import android.nfc.cardemulation.ApduServiceInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface INfcCardEmulation extends IInterface {
    AidGroup getAidGroupForService(int i, ComponentName componentName, String str) throws RemoteException;

    ApduServiceInfo getPreferredPaymentService(int i) throws RemoteException;

    List<ApduServiceInfo> getServices(int i, String str) throws RemoteException;

    boolean isDefaultPaymentRegistered() throws RemoteException;

    boolean isDefaultServiceForAid(int i, ComponentName componentName, String str) throws RemoteException;

    boolean isDefaultServiceForCategory(int i, ComponentName componentName, String str) throws RemoteException;

    boolean registerAidGroupForService(int i, ComponentName componentName, AidGroup aidGroup) throws RemoteException;

    boolean removeAidGroupForService(int i, ComponentName componentName, String str) throws RemoteException;

    boolean setDefaultForNextTap(int i, ComponentName componentName) throws RemoteException;

    boolean setDefaultServiceForCategory(int i, ComponentName componentName, String str) throws RemoteException;

    boolean setOffHostForService(int i, ComponentName componentName, String str) throws RemoteException;

    boolean setPreferredService(ComponentName componentName) throws RemoteException;

    boolean supportsAidPrefixRegistration() throws RemoteException;

    boolean unsetOffHostForService(int i, ComponentName componentName) throws RemoteException;

    boolean unsetPreferredService() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements INfcCardEmulation {
        @Override // android.nfc.INfcCardEmulation
        public boolean isDefaultServiceForCategory(int userHandle, ComponentName service, String category) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean isDefaultServiceForAid(int userHandle, ComponentName service, String aid) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean setDefaultServiceForCategory(int userHandle, ComponentName service, String category) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean setDefaultForNextTap(int userHandle, ComponentName service) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean registerAidGroupForService(int userHandle, ComponentName service, AidGroup aidGroup) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean setOffHostForService(int userHandle, ComponentName service, String offHostSecureElement) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean unsetOffHostForService(int userHandle, ComponentName service) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public AidGroup getAidGroupForService(int userHandle, ComponentName service, String category) throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean removeAidGroupForService(int userHandle, ComponentName service, String category) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public List<ApduServiceInfo> getServices(int userHandle, String category) throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean setPreferredService(ComponentName service) throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean unsetPreferredService() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean supportsAidPrefixRegistration() throws RemoteException {
            return false;
        }

        @Override // android.nfc.INfcCardEmulation
        public ApduServiceInfo getPreferredPaymentService(int userHandle) throws RemoteException {
            return null;
        }

        @Override // android.nfc.INfcCardEmulation
        public boolean isDefaultPaymentRegistered() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements INfcCardEmulation {
        public static final String DESCRIPTOR = "android.nfc.INfcCardEmulation";
        static final int TRANSACTION_getAidGroupForService = 8;
        static final int TRANSACTION_getPreferredPaymentService = 14;
        static final int TRANSACTION_getServices = 10;
        static final int TRANSACTION_isDefaultPaymentRegistered = 15;
        static final int TRANSACTION_isDefaultServiceForAid = 2;
        static final int TRANSACTION_isDefaultServiceForCategory = 1;
        static final int TRANSACTION_registerAidGroupForService = 5;
        static final int TRANSACTION_removeAidGroupForService = 9;
        static final int TRANSACTION_setDefaultForNextTap = 4;
        static final int TRANSACTION_setDefaultServiceForCategory = 3;
        static final int TRANSACTION_setOffHostForService = 6;
        static final int TRANSACTION_setPreferredService = 11;
        static final int TRANSACTION_supportsAidPrefixRegistration = 13;
        static final int TRANSACTION_unsetOffHostForService = 7;
        static final int TRANSACTION_unsetPreferredService = 12;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfcCardEmulation asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof INfcCardEmulation)) {
                return new Proxy(obj);
            }
            return (INfcCardEmulation) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "isDefaultServiceForCategory";
                case 2:
                    return "isDefaultServiceForAid";
                case 3:
                    return "setDefaultServiceForCategory";
                case 4:
                    return "setDefaultForNextTap";
                case 5:
                    return "registerAidGroupForService";
                case 6:
                    return "setOffHostForService";
                case 7:
                    return "unsetOffHostForService";
                case 8:
                    return "getAidGroupForService";
                case 9:
                    return "removeAidGroupForService";
                case 10:
                    return "getServices";
                case 11:
                    return "setPreferredService";
                case 12:
                    return "unsetPreferredService";
                case 13:
                    return "supportsAidPrefixRegistration";
                case 14:
                    return "getPreferredPaymentService";
                case 15:
                    return "isDefaultPaymentRegistered";
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
            ComponentName _arg1;
            ComponentName _arg12;
            ComponentName _arg13;
            ComponentName _arg14;
            ComponentName _arg15;
            AidGroup _arg2;
            ComponentName _arg16;
            ComponentName _arg17;
            ComponentName _arg18;
            ComponentName _arg19;
            ComponentName _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg22 = data.readString();
                            boolean isDefaultServiceForCategory = isDefaultServiceForCategory(_arg02, _arg1, _arg22);
                            reply.writeNoException();
                            reply.writeInt(isDefaultServiceForCategory ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            String _arg23 = data.readString();
                            boolean isDefaultServiceForAid = isDefaultServiceForAid(_arg03, _arg12, _arg23);
                            reply.writeNoException();
                            reply.writeInt(isDefaultServiceForAid ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            String _arg24 = data.readString();
                            boolean defaultServiceForCategory = setDefaultServiceForCategory(_arg04, _arg13, _arg24);
                            reply.writeNoException();
                            reply.writeInt(defaultServiceForCategory ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            boolean defaultForNextTap = setDefaultForNextTap(_arg05, _arg14);
                            reply.writeNoException();
                            reply.writeInt(defaultForNextTap ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = AidGroup.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean registerAidGroupForService = registerAidGroupForService(_arg06, _arg15, _arg2);
                            reply.writeNoException();
                            reply.writeInt(registerAidGroupForService ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg16 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            String _arg25 = data.readString();
                            boolean offHostForService = setOffHostForService(_arg07, _arg16, _arg25);
                            reply.writeNoException();
                            reply.writeInt(offHostForService ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg17 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            boolean unsetOffHostForService = unsetOffHostForService(_arg08, _arg17);
                            reply.writeNoException();
                            reply.writeInt(unsetOffHostForService ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg18 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            String _arg26 = data.readString();
                            AidGroup _result = getAidGroupForService(_arg09, _arg18, _arg26);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg19 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            String _arg27 = data.readString();
                            boolean removeAidGroupForService = removeAidGroupForService(_arg010, _arg19, _arg27);
                            reply.writeNoException();
                            reply.writeInt(removeAidGroupForService ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            String _arg110 = data.readString();
                            List<ApduServiceInfo> _result2 = getServices(_arg011, _arg110);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean preferredService = setPreferredService(_arg0);
                            reply.writeNoException();
                            reply.writeInt(preferredService ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            boolean unsetPreferredService = unsetPreferredService();
                            reply.writeNoException();
                            reply.writeInt(unsetPreferredService ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            boolean supportsAidPrefixRegistration = supportsAidPrefixRegistration();
                            reply.writeNoException();
                            reply.writeInt(supportsAidPrefixRegistration ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            ApduServiceInfo _result3 = getPreferredPaymentService(_arg012);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isDefaultPaymentRegistered = isDefaultPaymentRegistered();
                            reply.writeNoException();
                            reply.writeInt(isDefaultPaymentRegistered ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements INfcCardEmulation {
            public static INfcCardEmulation sDefaultImpl;
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

            @Override // android.nfc.INfcCardEmulation
            public boolean isDefaultServiceForCategory(int userHandle, ComponentName service, String category) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(category);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDefaultServiceForCategory(userHandle, service, category);
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

            @Override // android.nfc.INfcCardEmulation
            public boolean isDefaultServiceForAid(int userHandle, ComponentName service, String aid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(aid);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDefaultServiceForAid(userHandle, service, aid);
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

            @Override // android.nfc.INfcCardEmulation
            public boolean setDefaultServiceForCategory(int userHandle, ComponentName service, String category) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(category);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefaultServiceForCategory(userHandle, service, category);
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

            @Override // android.nfc.INfcCardEmulation
            public boolean setDefaultForNextTap(int userHandle, ComponentName service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefaultForNextTap(userHandle, service);
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

            @Override // android.nfc.INfcCardEmulation
            public boolean registerAidGroupForService(int userHandle, ComponentName service, AidGroup aidGroup) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (aidGroup != null) {
                        _data.writeInt(1);
                        aidGroup.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAidGroupForService(userHandle, service, aidGroup);
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

            @Override // android.nfc.INfcCardEmulation
            public boolean setOffHostForService(int userHandle, ComponentName service, String offHostSecureElement) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(offHostSecureElement);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOffHostForService(userHandle, service, offHostSecureElement);
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

            @Override // android.nfc.INfcCardEmulation
            public boolean unsetOffHostForService(int userHandle, ComponentName service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unsetOffHostForService(userHandle, service);
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

            @Override // android.nfc.INfcCardEmulation
            public AidGroup getAidGroupForService(int userHandle, ComponentName service, String category) throws RemoteException {
                AidGroup _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(category);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAidGroupForService(userHandle, service, category);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AidGroup.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.nfc.INfcCardEmulation
            public boolean removeAidGroupForService(int userHandle, ComponentName service, String category) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(category);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAidGroupForService(userHandle, service, category);
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

            @Override // android.nfc.INfcCardEmulation
            public List<ApduServiceInfo> getServices(int userHandle, String category) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    _data.writeString(category);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServices(userHandle, category);
                    }
                    _reply.readException();
                    List<ApduServiceInfo> _result = _reply.createTypedArrayList(ApduServiceInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.nfc.INfcCardEmulation
            public boolean setPreferredService(ComponentName service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPreferredService(service);
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

            @Override // android.nfc.INfcCardEmulation
            public boolean unsetPreferredService() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unsetPreferredService();
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

            @Override // android.nfc.INfcCardEmulation
            public boolean supportsAidPrefixRegistration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supportsAidPrefixRegistration();
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

            @Override // android.nfc.INfcCardEmulation
            public ApduServiceInfo getPreferredPaymentService(int userHandle) throws RemoteException {
                ApduServiceInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userHandle);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredPaymentService(userHandle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ApduServiceInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.nfc.INfcCardEmulation
            public boolean isDefaultPaymentRegistered() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDefaultPaymentRegistered();
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
        }

        public static boolean setDefaultImpl(INfcCardEmulation impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INfcCardEmulation getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
