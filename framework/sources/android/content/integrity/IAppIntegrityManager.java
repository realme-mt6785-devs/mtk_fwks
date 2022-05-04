package android.content.integrity;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IAppIntegrityManager extends IInterface {
    public static final String DESCRIPTOR = "android.content.integrity.IAppIntegrityManager";

    String getCurrentRuleSetProvider() throws RemoteException;

    String getCurrentRuleSetVersion() throws RemoteException;

    ParceledListSlice<Rule> getCurrentRules() throws RemoteException;

    List<String> getWhitelistedRuleProviders() throws RemoteException;

    void updateRuleSet(String str, ParceledListSlice<Rule> parceledListSlice, IntentSender intentSender) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAppIntegrityManager {
        @Override // android.content.integrity.IAppIntegrityManager
        public void updateRuleSet(String version, ParceledListSlice<Rule> rules, IntentSender statusReceiver) throws RemoteException {
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public String getCurrentRuleSetVersion() throws RemoteException {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public String getCurrentRuleSetProvider() throws RemoteException {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public ParceledListSlice<Rule> getCurrentRules() throws RemoteException {
            return null;
        }

        @Override // android.content.integrity.IAppIntegrityManager
        public List<String> getWhitelistedRuleProviders() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAppIntegrityManager {
        static final int TRANSACTION_getCurrentRuleSetProvider = 3;
        static final int TRANSACTION_getCurrentRuleSetVersion = 2;
        static final int TRANSACTION_getCurrentRules = 4;
        static final int TRANSACTION_getWhitelistedRuleProviders = 5;
        static final int TRANSACTION_updateRuleSet = 1;

        public Stub() {
            attachInterface(this, IAppIntegrityManager.DESCRIPTOR);
        }

        public static IAppIntegrityManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAppIntegrityManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAppIntegrityManager)) {
                return new Proxy(obj);
            }
            return (IAppIntegrityManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "updateRuleSet";
                case 2:
                    return "getCurrentRuleSetVersion";
                case 3:
                    return "getCurrentRuleSetProvider";
                case 4:
                    return "getCurrentRules";
                case 5:
                    return "getWhitelistedRuleProviders";
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
            ParceledListSlice _arg1;
            IntentSender _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IAppIntegrityManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAppIntegrityManager.DESCRIPTOR);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = IntentSender.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            updateRuleSet(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IAppIntegrityManager.DESCRIPTOR);
                            String _result = getCurrentRuleSetVersion();
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 3:
                            data.enforceInterface(IAppIntegrityManager.DESCRIPTOR);
                            String _result2 = getCurrentRuleSetProvider();
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 4:
                            data.enforceInterface(IAppIntegrityManager.DESCRIPTOR);
                            ParceledListSlice<Rule> _result3 = getCurrentRules();
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(IAppIntegrityManager.DESCRIPTOR);
                            List<String> _result4 = getWhitelistedRuleProviders();
                            reply.writeNoException();
                            reply.writeStringList(_result4);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAppIntegrityManager {
            public static IAppIntegrityManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAppIntegrityManager.DESCRIPTOR;
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public void updateRuleSet(String version, ParceledListSlice<Rule> rules, IntentSender statusReceiver) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppIntegrityManager.DESCRIPTOR);
                    _data.writeString(version);
                    if (rules != null) {
                        _data.writeInt(1);
                        rules.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (statusReceiver != null) {
                        _data.writeInt(1);
                        statusReceiver.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateRuleSet(version, rules, statusReceiver);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public String getCurrentRuleSetVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppIntegrityManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentRuleSetVersion();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public String getCurrentRuleSetProvider() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppIntegrityManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentRuleSetProvider();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public ParceledListSlice<Rule> getCurrentRules() throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppIntegrityManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentRules();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.content.integrity.IAppIntegrityManager
            public List<String> getWhitelistedRuleProviders() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppIntegrityManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWhitelistedRuleProviders();
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

        public static boolean setDefaultImpl(IAppIntegrityManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAppIntegrityManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
