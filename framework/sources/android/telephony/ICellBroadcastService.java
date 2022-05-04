package android.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.telephony.cdma.CdmaSmsCbProgramData;
import android.text.TextUtils;
import java.util.List;
/* loaded from: classes3.dex */
public interface ICellBroadcastService extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.ICellBroadcastService";

    CharSequence getCellBroadcastAreaInfo(int i) throws RemoteException;

    void handleCdmaCellBroadcastSms(int i, byte[] bArr, int i2) throws RemoteException;

    void handleCdmaScpMessage(int i, List<CdmaSmsCbProgramData> list, String str, RemoteCallback remoteCallback) throws RemoteException;

    void handleGsmCellBroadcastSms(int i, byte[] bArr) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ICellBroadcastService {
        @Override // android.telephony.ICellBroadcastService
        public void handleGsmCellBroadcastSms(int slotId, byte[] message) throws RemoteException {
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleCdmaCellBroadcastSms(int slotId, byte[] bearerData, int serviceCategory) throws RemoteException {
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleCdmaScpMessage(int slotId, List<CdmaSmsCbProgramData> programData, String originatingAddress, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.telephony.ICellBroadcastService
        public CharSequence getCellBroadcastAreaInfo(int slotIndex) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ICellBroadcastService {
        static final int TRANSACTION_getCellBroadcastAreaInfo = 4;
        static final int TRANSACTION_handleCdmaCellBroadcastSms = 2;
        static final int TRANSACTION_handleCdmaScpMessage = 3;
        static final int TRANSACTION_handleGsmCellBroadcastSms = 1;

        public Stub() {
            attachInterface(this, ICellBroadcastService.DESCRIPTOR);
        }

        public static ICellBroadcastService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICellBroadcastService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICellBroadcastService)) {
                return new Proxy(obj);
            }
            return (ICellBroadcastService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "handleGsmCellBroadcastSms";
                case 2:
                    return "handleCdmaCellBroadcastSms";
                case 3:
                    return "handleCdmaScpMessage";
                case 4:
                    return "getCellBroadcastAreaInfo";
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
            RemoteCallback _arg3;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ICellBroadcastService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICellBroadcastService.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            byte[] _arg1 = data.createByteArray();
                            handleGsmCellBroadcastSms(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(ICellBroadcastService.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            byte[] _arg12 = data.createByteArray();
                            int _arg2 = data.readInt();
                            handleCdmaCellBroadcastSms(_arg02, _arg12, _arg2);
                            return true;
                        case 3:
                            data.enforceInterface(ICellBroadcastService.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            List<CdmaSmsCbProgramData> _arg13 = data.createTypedArrayList(CdmaSmsCbProgramData.CREATOR);
                            String _arg22 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            handleCdmaScpMessage(_arg03, _arg13, _arg22, _arg3);
                            return true;
                        case 4:
                            data.enforceInterface(ICellBroadcastService.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            CharSequence _result = getCellBroadcastAreaInfo(_arg04);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                TextUtils.writeToParcel(_result, reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ICellBroadcastService {
            public static ICellBroadcastService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICellBroadcastService.DESCRIPTOR;
            }

            @Override // android.telephony.ICellBroadcastService
            public void handleGsmCellBroadcastSms(int slotId, byte[] message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICellBroadcastService.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeByteArray(message);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleGsmCellBroadcastSms(slotId, message);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ICellBroadcastService
            public void handleCdmaCellBroadcastSms(int slotId, byte[] bearerData, int serviceCategory) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICellBroadcastService.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeByteArray(bearerData);
                    _data.writeInt(serviceCategory);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleCdmaCellBroadcastSms(slotId, bearerData, serviceCategory);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ICellBroadcastService
            public void handleCdmaScpMessage(int slotId, List<CdmaSmsCbProgramData> programData, String originatingAddress, RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICellBroadcastService.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeTypedList(programData);
                    _data.writeString(originatingAddress);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handleCdmaScpMessage(slotId, programData, originatingAddress, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.telephony.ICellBroadcastService
            public CharSequence getCellBroadcastAreaInfo(int slotIndex) throws RemoteException {
                CharSequence _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICellBroadcastService.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCellBroadcastAreaInfo(slotIndex);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICellBroadcastService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICellBroadcastService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
