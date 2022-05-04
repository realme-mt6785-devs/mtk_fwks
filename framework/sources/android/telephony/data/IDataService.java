package android.telephony.data;

import android.net.LinkProperties;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.data.IDataServiceCallback;
import java.util.List;
/* loaded from: classes3.dex */
public interface IDataService extends IInterface {
    void cancelHandover(int i, int i2, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void createDataServiceProvider(int i) throws RemoteException;

    void deactivateDataCall(int i, int i2, int i3, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void registerForDataCallListChanged(int i, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void registerForUnthrottleApn(int i, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void removeDataServiceProvider(int i) throws RemoteException;

    void requestDataCallList(int i, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void setDataProfile(int i, List<DataProfile> list, boolean z, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void setInitialAttachApn(int i, DataProfile dataProfile, boolean z, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void setupDataCall(int i, int i2, DataProfile dataProfile, boolean z, boolean z2, int i3, LinkProperties linkProperties, int i4, NetworkSliceInfo networkSliceInfo, TrafficDescriptor trafficDescriptor, boolean z3, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void startHandover(int i, int i2, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void unregisterForDataCallListChanged(int i, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    void unregisterForUnthrottleApn(int i, IDataServiceCallback iDataServiceCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDataService {
        @Override // android.telephony.data.IDataService
        public void createDataServiceProvider(int slotId) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void removeDataServiceProvider(int slotId) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void setupDataCall(int slotId, int accessNetwork, DataProfile dataProfile, boolean isRoaming, boolean allowRoaming, int reason, LinkProperties linkProperties, int pduSessionId, NetworkSliceInfo sliceInfo, TrafficDescriptor trafficDescriptor, boolean matchAllRuleAllowed, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void deactivateDataCall(int slotId, int cid, int reason, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void setInitialAttachApn(int slotId, DataProfile dataProfile, boolean isRoaming, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void setDataProfile(int slotId, List<DataProfile> dps, boolean isRoaming, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void requestDataCallList(int slotId, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void registerForDataCallListChanged(int slotId, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void unregisterForDataCallListChanged(int slotId, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void startHandover(int slotId, int cid, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void cancelHandover(int slotId, int cid, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void registerForUnthrottleApn(int slotIndex, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.telephony.data.IDataService
        public void unregisterForUnthrottleApn(int slotIndex, IDataServiceCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDataService {
        public static final String DESCRIPTOR = "android.telephony.data.IDataService";
        static final int TRANSACTION_cancelHandover = 11;
        static final int TRANSACTION_createDataServiceProvider = 1;
        static final int TRANSACTION_deactivateDataCall = 4;
        static final int TRANSACTION_registerForDataCallListChanged = 8;
        static final int TRANSACTION_registerForUnthrottleApn = 12;
        static final int TRANSACTION_removeDataServiceProvider = 2;
        static final int TRANSACTION_requestDataCallList = 7;
        static final int TRANSACTION_setDataProfile = 6;
        static final int TRANSACTION_setInitialAttachApn = 5;
        static final int TRANSACTION_setupDataCall = 3;
        static final int TRANSACTION_startHandover = 10;
        static final int TRANSACTION_unregisterForDataCallListChanged = 9;
        static final int TRANSACTION_unregisterForUnthrottleApn = 13;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDataService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDataService)) {
                return new Proxy(obj);
            }
            return (IDataService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createDataServiceProvider";
                case 2:
                    return "removeDataServiceProvider";
                case 3:
                    return "setupDataCall";
                case 4:
                    return "deactivateDataCall";
                case 5:
                    return "setInitialAttachApn";
                case 6:
                    return "setDataProfile";
                case 7:
                    return "requestDataCallList";
                case 8:
                    return "registerForDataCallListChanged";
                case 9:
                    return "unregisterForDataCallListChanged";
                case 10:
                    return "startHandover";
                case 11:
                    return "cancelHandover";
                case 12:
                    return "registerForUnthrottleApn";
                case 13:
                    return "unregisterForUnthrottleApn";
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
            DataProfile _arg2;
            LinkProperties _arg6;
            NetworkSliceInfo _arg8;
            TrafficDescriptor _arg9;
            DataProfile _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg22 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0 = data.readInt();
                            createDataServiceProvider(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            removeDataServiceProvider(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _arg12 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = DataProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean _arg3 = data.readInt() != 0;
                            boolean _arg4 = data.readInt() != 0;
                            int _arg5 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg6 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            int _arg7 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg8 = NetworkSliceInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg8 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg9 = TrafficDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg9 = null;
                            }
                            boolean _arg10 = data.readInt() != 0;
                            IDataServiceCallback _arg11 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            setupDataCall(_arg03, _arg12, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10, _arg11);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            int _arg13 = data.readInt();
                            int _arg23 = data.readInt();
                            IDataServiceCallback _arg32 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            deactivateDataCall(_arg04, _arg13, _arg23, _arg32);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = DataProfile.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = true;
                            }
                            IDataServiceCallback _arg33 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            setInitialAttachApn(_arg05, _arg1, _arg22, _arg33);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            List<DataProfile> _arg14 = data.createTypedArrayList(DataProfile.CREATOR);
                            if (data.readInt() != 0) {
                                _arg22 = true;
                            }
                            IDataServiceCallback _arg34 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            setDataProfile(_arg06, _arg14, _arg22, _arg34);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            IDataServiceCallback _arg15 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            requestDataCallList(_arg07, _arg15);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            IDataServiceCallback _arg16 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            registerForDataCallListChanged(_arg08, _arg16);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            IDataServiceCallback _arg17 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterForDataCallListChanged(_arg09, _arg17);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg18 = data.readInt();
                            startHandover(_arg010, _arg18, IDataServiceCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _arg19 = data.readInt();
                            cancelHandover(_arg011, _arg19, IDataServiceCallback.Stub.asInterface(data.readStrongBinder()));
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            IDataServiceCallback _arg110 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            registerForUnthrottleApn(_arg012, _arg110);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            IDataServiceCallback _arg111 = IDataServiceCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterForUnthrottleApn(_arg013, _arg111);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDataService {
            public static IDataService sDefaultImpl;
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

            @Override // android.telephony.data.IDataService
            public void createDataServiceProvider(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createDataServiceProvider(slotId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void removeDataServiceProvider(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDataServiceProvider(slotId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void setupDataCall(int slotId, int accessNetwork, DataProfile dataProfile, boolean isRoaming, boolean allowRoaming, int reason, LinkProperties linkProperties, int pduSessionId, NetworkSliceInfo sliceInfo, TrafficDescriptor trafficDescriptor, boolean matchAllRuleAllowed, IDataServiceCallback callback) throws RemoteException {
                Parcel _data;
                Throwable th;
                Parcel _data2;
                Parcel _data3 = Parcel.obtain();
                try {
                    _data3.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data3.writeInt(slotId);
                    _data3.writeInt(accessNetwork);
                    int i = 0;
                    if (dataProfile != null) {
                        try {
                            _data3.writeInt(1);
                            dataProfile.writeToParcel(_data3, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _data = _data3;
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeInt(isRoaming ? 1 : 0);
                    _data3.writeInt(allowRoaming ? 1 : 0);
                    _data3.writeInt(reason);
                    if (linkProperties != null) {
                        _data3.writeInt(1);
                        linkProperties.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeInt(pduSessionId);
                    if (sliceInfo != null) {
                        _data3.writeInt(1);
                        sliceInfo.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (trafficDescriptor != null) {
                        _data3.writeInt(1);
                        trafficDescriptor.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (matchAllRuleAllowed) {
                        i = 1;
                    }
                    _data3.writeInt(i);
                    _data3.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data3, null, 1);
                    if (_status) {
                        _data2 = _data3;
                    } else if (Stub.getDefaultImpl() != null) {
                        _data = _data3;
                        try {
                            Stub.getDefaultImpl().setupDataCall(slotId, accessNetwork, dataProfile, isRoaming, allowRoaming, reason, linkProperties, pduSessionId, sliceInfo, trafficDescriptor, matchAllRuleAllowed, callback);
                            _data.recycle();
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data2 = _data3;
                    }
                    _data2.recycle();
                } catch (Throwable th4) {
                    th = th4;
                    _data = _data3;
                }
            }

            @Override // android.telephony.data.IDataService
            public void deactivateDataCall(int slotId, int cid, int reason, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(cid);
                    _data.writeInt(reason);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deactivateDataCall(slotId, cid, reason, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void setInitialAttachApn(int slotId, DataProfile dataProfile, boolean isRoaming, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    int i = 0;
                    if (dataProfile != null) {
                        _data.writeInt(1);
                        dataProfile.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (isRoaming) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInitialAttachApn(slotId, dataProfile, isRoaming, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void setDataProfile(int slotId, List<DataProfile> dps, boolean isRoaming, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeTypedList(dps);
                    _data.writeInt(isRoaming ? 1 : 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDataProfile(slotId, dps, isRoaming, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void requestDataCallList(int slotId, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestDataCallList(slotId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void registerForDataCallListChanged(int slotId, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerForDataCallListChanged(slotId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void unregisterForDataCallListChanged(int slotId, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterForDataCallListChanged(slotId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void startHandover(int slotId, int cid, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(cid);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startHandover(slotId, cid, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void cancelHandover(int slotId, int cid, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(cid);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelHandover(slotId, cid, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void registerForUnthrottleApn(int slotIndex, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerForUnthrottleApn(slotIndex, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.data.IDataService
            public void unregisterForUnthrottleApn(int slotIndex, IDataServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterForUnthrottleApn(slotIndex, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDataService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
