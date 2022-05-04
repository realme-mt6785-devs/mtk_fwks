package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telecom.BluetoothCallQualityReport;
import android.telecom.CallAudioState;
import android.telecom.DisconnectCause;
import android.telecom.ParcelableCall;
import android.telephony.CallQuality;
import com.android.internal.telecom.ICallDiagnosticServiceAdapter;
/* loaded from: classes4.dex */
public interface ICallDiagnosticService extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telecom.ICallDiagnosticService";

    void callQualityChanged(String str, CallQuality callQuality) throws RemoteException;

    void initializeDiagnosticCall(ParcelableCall parcelableCall) throws RemoteException;

    void notifyCallDisconnected(String str, DisconnectCause disconnectCause) throws RemoteException;

    void receiveBluetoothCallQualityReport(BluetoothCallQualityReport bluetoothCallQualityReport) throws RemoteException;

    void receiveDeviceToDeviceMessage(String str, int i, int i2) throws RemoteException;

    void removeDiagnosticCall(String str) throws RemoteException;

    void setAdapter(ICallDiagnosticServiceAdapter iCallDiagnosticServiceAdapter) throws RemoteException;

    void updateCall(ParcelableCall parcelableCall) throws RemoteException;

    void updateCallAudioState(CallAudioState callAudioState) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ICallDiagnosticService {
        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void setAdapter(ICallDiagnosticServiceAdapter adapter) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void initializeDiagnosticCall(ParcelableCall call) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void updateCall(ParcelableCall call) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void updateCallAudioState(CallAudioState callAudioState) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void removeDiagnosticCall(String callId) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void receiveDeviceToDeviceMessage(String callId, int message, int value) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void callQualityChanged(String callId, CallQuality callQuality) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void receiveBluetoothCallQualityReport(BluetoothCallQualityReport qualityReport) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticService
        public void notifyCallDisconnected(String callId, DisconnectCause disconnectCause) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ICallDiagnosticService {
        static final int TRANSACTION_callQualityChanged = 7;
        static final int TRANSACTION_initializeDiagnosticCall = 2;
        static final int TRANSACTION_notifyCallDisconnected = 9;
        static final int TRANSACTION_receiveBluetoothCallQualityReport = 8;
        static final int TRANSACTION_receiveDeviceToDeviceMessage = 6;
        static final int TRANSACTION_removeDiagnosticCall = 5;
        static final int TRANSACTION_setAdapter = 1;
        static final int TRANSACTION_updateCall = 3;
        static final int TRANSACTION_updateCallAudioState = 4;

        public Stub() {
            attachInterface(this, ICallDiagnosticService.DESCRIPTOR);
        }

        public static ICallDiagnosticService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICallDiagnosticService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICallDiagnosticService)) {
                return new Proxy(obj);
            }
            return (ICallDiagnosticService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "setAdapter";
                case 2:
                    return "initializeDiagnosticCall";
                case 3:
                    return "updateCall";
                case 4:
                    return "updateCallAudioState";
                case 5:
                    return "removeDiagnosticCall";
                case 6:
                    return "receiveDeviceToDeviceMessage";
                case 7:
                    return "callQualityChanged";
                case 8:
                    return "receiveBluetoothCallQualityReport";
                case 9:
                    return "notifyCallDisconnected";
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
            ParcelableCall _arg0;
            ParcelableCall _arg02;
            CallAudioState _arg03;
            CallQuality _arg1;
            BluetoothCallQualityReport _arg04;
            DisconnectCause _arg12;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ICallDiagnosticService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            ICallDiagnosticServiceAdapter _arg05 = ICallDiagnosticServiceAdapter.Stub.asInterface(data.readStrongBinder());
                            setAdapter(_arg05);
                            return true;
                        case 2:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelableCall.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            initializeDiagnosticCall(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ParcelableCall.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            updateCall(_arg02);
                            return true;
                        case 4:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = CallAudioState.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            updateCallAudioState(_arg03);
                            return true;
                        case 5:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            String _arg06 = data.readString();
                            removeDiagnosticCall(_arg06);
                            return true;
                        case 6:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            String _arg07 = data.readString();
                            int _arg13 = data.readInt();
                            int _arg2 = data.readInt();
                            receiveDeviceToDeviceMessage(_arg07, _arg13, _arg2);
                            return true;
                        case 7:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            String _arg08 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = CallQuality.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            callQualityChanged(_arg08, _arg1);
                            return true;
                        case 8:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = BluetoothCallQualityReport.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            receiveBluetoothCallQualityReport(_arg04);
                            return true;
                        case 9:
                            data.enforceInterface(ICallDiagnosticService.DESCRIPTOR);
                            String _arg09 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = DisconnectCause.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            notifyCallDisconnected(_arg09, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ICallDiagnosticService {
            public static ICallDiagnosticService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICallDiagnosticService.DESCRIPTOR;
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void setAdapter(ICallDiagnosticServiceAdapter adapter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    _data.writeStrongBinder(adapter != null ? adapter.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAdapter(adapter);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void initializeDiagnosticCall(ParcelableCall call) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    if (call != null) {
                        _data.writeInt(1);
                        call.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initializeDiagnosticCall(call);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void updateCall(ParcelableCall call) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    if (call != null) {
                        _data.writeInt(1);
                        call.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCall(call);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void updateCallAudioState(CallAudioState callAudioState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    if (callAudioState != null) {
                        _data.writeInt(1);
                        callAudioState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCallAudioState(callAudioState);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void removeDiagnosticCall(String callId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    _data.writeString(callId);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDiagnosticCall(callId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void receiveDeviceToDeviceMessage(String callId, int message, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(message);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().receiveDeviceToDeviceMessage(callId, message, value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void callQualityChanged(String callId, CallQuality callQuality) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    _data.writeString(callId);
                    if (callQuality != null) {
                        _data.writeInt(1);
                        callQuality.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callQualityChanged(callId, callQuality);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void receiveBluetoothCallQualityReport(BluetoothCallQualityReport qualityReport) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    if (qualityReport != null) {
                        _data.writeInt(1);
                        qualityReport.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().receiveBluetoothCallQualityReport(qualityReport);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticService
            public void notifyCallDisconnected(String callId, DisconnectCause disconnectCause) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticService.DESCRIPTOR);
                    _data.writeString(callId);
                    if (disconnectCause != null) {
                        _data.writeInt(1);
                        disconnectCause.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCallDisconnected(callId, disconnectCause);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICallDiagnosticService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICallDiagnosticService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
