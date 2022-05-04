package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
/* loaded from: classes4.dex */
public interface ICallDiagnosticServiceAdapter extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telecom.ICallDiagnosticServiceAdapter";

    void clearDiagnosticMessage(String str, int i) throws RemoteException;

    void displayDiagnosticMessage(String str, int i, CharSequence charSequence) throws RemoteException;

    void overrideDisconnectMessage(String str, CharSequence charSequence) throws RemoteException;

    void sendDeviceToDeviceMessage(String str, int i, int i2) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ICallDiagnosticServiceAdapter {
        @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
        public void displayDiagnosticMessage(String callId, int messageId, CharSequence message) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
        public void clearDiagnosticMessage(String callId, int messageId) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
        public void sendDeviceToDeviceMessage(String callId, int message, int value) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
        public void overrideDisconnectMessage(String callId, CharSequence message) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ICallDiagnosticServiceAdapter {
        static final int TRANSACTION_clearDiagnosticMessage = 2;
        static final int TRANSACTION_displayDiagnosticMessage = 1;
        static final int TRANSACTION_overrideDisconnectMessage = 4;
        static final int TRANSACTION_sendDeviceToDeviceMessage = 3;

        public Stub() {
            attachInterface(this, ICallDiagnosticServiceAdapter.DESCRIPTOR);
        }

        public static ICallDiagnosticServiceAdapter asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICallDiagnosticServiceAdapter.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICallDiagnosticServiceAdapter)) {
                return new Proxy(obj);
            }
            return (ICallDiagnosticServiceAdapter) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "displayDiagnosticMessage";
                case 2:
                    return "clearDiagnosticMessage";
                case 3:
                    return "sendDeviceToDeviceMessage";
                case 4:
                    return "overrideDisconnectMessage";
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
            CharSequence _arg2;
            CharSequence _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _arg12 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            displayDiagnosticMessage(_arg0, _arg12, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _arg13 = data.readInt();
                            clearDiagnosticMessage(_arg02, _arg13);
                            return true;
                        case 3:
                            data.enforceInterface(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                            String _arg03 = data.readString();
                            int _arg14 = data.readInt();
                            int _arg22 = data.readInt();
                            sendDeviceToDeviceMessage(_arg03, _arg14, _arg22);
                            return true;
                        case 4:
                            data.enforceInterface(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            overrideDisconnectMessage(_arg04, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ICallDiagnosticServiceAdapter {
            public static ICallDiagnosticServiceAdapter sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICallDiagnosticServiceAdapter.DESCRIPTOR;
            }

            @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
            public void displayDiagnosticMessage(String callId, int messageId, CharSequence message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(messageId);
                    if (message != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(message, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().displayDiagnosticMessage(callId, messageId, message);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
            public void clearDiagnosticMessage(String callId, int messageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(messageId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearDiagnosticMessage(callId, messageId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
            public void sendDeviceToDeviceMessage(String callId, int message, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                    _data.writeString(callId);
                    _data.writeInt(message);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendDeviceToDeviceMessage(callId, message, value);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ICallDiagnosticServiceAdapter
            public void overrideDisconnectMessage(String callId, CharSequence message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICallDiagnosticServiceAdapter.DESCRIPTOR);
                    _data.writeString(callId);
                    if (message != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(message, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overrideDisconnectMessage(callId, message);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICallDiagnosticServiceAdapter impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICallDiagnosticServiceAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
