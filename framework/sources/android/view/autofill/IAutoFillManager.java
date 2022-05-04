package android.view.autofill;

import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.UserData;
import android.view.autofill.IAutoFillManagerClient;
import com.android.internal.os.IResultReceiver;
import java.util.List;
/* loaded from: classes3.dex */
public interface IAutoFillManager extends IInterface {
    void addClient(IAutoFillManagerClient iAutoFillManagerClient, ComponentName componentName, int i, IResultReceiver iResultReceiver) throws RemoteException;

    void cancelSession(int i, int i2) throws RemoteException;

    void disableOwnedAutofillServices(int i) throws RemoteException;

    void finishSession(int i, int i2) throws RemoteException;

    void getAutofillServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException;

    void getAvailableFieldClassificationAlgorithms(IResultReceiver iResultReceiver) throws RemoteException;

    void getDefaultFieldClassificationAlgorithm(IResultReceiver iResultReceiver) throws RemoteException;

    void getFillEventHistory(IResultReceiver iResultReceiver) throws RemoteException;

    void getUserData(IResultReceiver iResultReceiver) throws RemoteException;

    void getUserDataId(IResultReceiver iResultReceiver) throws RemoteException;

    void isFieldClassificationEnabled(IResultReceiver iResultReceiver) throws RemoteException;

    void isServiceEnabled(int i, String str, IResultReceiver iResultReceiver) throws RemoteException;

    void isServiceSupported(int i, IResultReceiver iResultReceiver) throws RemoteException;

    void onPendingSaveUi(int i, IBinder iBinder) throws RemoteException;

    void removeClient(IAutoFillManagerClient iAutoFillManagerClient, int i) throws RemoteException;

    void restoreSession(int i, IBinder iBinder, IBinder iBinder2, IResultReceiver iResultReceiver) throws RemoteException;

    void setAugmentedAutofillWhitelist(List<String> list, List<ComponentName> list2, IResultReceiver iResultReceiver) throws RemoteException;

    void setAuthenticationResult(Bundle bundle, int i, int i2, int i3) throws RemoteException;

    void setAutofillFailure(int i, List<AutofillId> list, int i2) throws RemoteException;

    void setHasCallback(int i, int i2, boolean z) throws RemoteException;

    void setUserData(UserData userData) throws RemoteException;

    void startSession(IBinder iBinder, IBinder iBinder2, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i, boolean z, int i2, ComponentName componentName, boolean z2, IResultReceiver iResultReceiver) throws RemoteException;

    void updateSession(int i, AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i2, int i3, int i4) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IAutoFillManager {
        @Override // android.view.autofill.IAutoFillManager
        public void addClient(IAutoFillManagerClient client, ComponentName componentName, int userId, IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void removeClient(IAutoFillManagerClient client, int userId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void startSession(IBinder activityToken, IBinder appCallback, AutofillId autoFillId, Rect bounds, AutofillValue value, int userId, boolean hasCallback, int flags, ComponentName componentName, boolean compatMode, IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getFillEventHistory(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void restoreSession(int sessionId, IBinder activityToken, IBinder appCallback, IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void updateSession(int sessionId, AutofillId id, Rect bounds, AutofillValue value, int action, int flags, int userId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setAutofillFailure(int sessionId, List<AutofillId> ids, int userId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void finishSession(int sessionId, int userId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void cancelSession(int sessionId, int userId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setAuthenticationResult(Bundle data, int sessionId, int authenticationId, int userId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setHasCallback(int sessionId, int userId, boolean hasIt) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void disableOwnedAutofillServices(int userId) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void isServiceSupported(int userId, IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void isServiceEnabled(int userId, String packageName, IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void onPendingSaveUi(int operation, IBinder token) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getUserData(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getUserDataId(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setUserData(UserData userData) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void isFieldClassificationEnabled(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getAutofillServiceComponentName(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getAvailableFieldClassificationAlgorithms(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void getDefaultFieldClassificationAlgorithm(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.autofill.IAutoFillManager
        public void setAugmentedAutofillWhitelist(List<String> packages, List<ComponentName> activities, IResultReceiver result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IAutoFillManager {
        public static final String DESCRIPTOR = "android.view.autofill.IAutoFillManager";
        static final int TRANSACTION_addClient = 1;
        static final int TRANSACTION_cancelSession = 9;
        static final int TRANSACTION_disableOwnedAutofillServices = 12;
        static final int TRANSACTION_finishSession = 8;
        static final int TRANSACTION_getAutofillServiceComponentName = 20;
        static final int TRANSACTION_getAvailableFieldClassificationAlgorithms = 21;
        static final int TRANSACTION_getDefaultFieldClassificationAlgorithm = 22;
        static final int TRANSACTION_getFillEventHistory = 4;
        static final int TRANSACTION_getUserData = 16;
        static final int TRANSACTION_getUserDataId = 17;
        static final int TRANSACTION_isFieldClassificationEnabled = 19;
        static final int TRANSACTION_isServiceEnabled = 14;
        static final int TRANSACTION_isServiceSupported = 13;
        static final int TRANSACTION_onPendingSaveUi = 15;
        static final int TRANSACTION_removeClient = 2;
        static final int TRANSACTION_restoreSession = 5;
        static final int TRANSACTION_setAugmentedAutofillWhitelist = 23;
        static final int TRANSACTION_setAuthenticationResult = 10;
        static final int TRANSACTION_setAutofillFailure = 7;
        static final int TRANSACTION_setHasCallback = 11;
        static final int TRANSACTION_setUserData = 18;
        static final int TRANSACTION_startSession = 3;
        static final int TRANSACTION_updateSession = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAutoFillManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAutoFillManager)) {
                return new Proxy(obj);
            }
            return (IAutoFillManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addClient";
                case 2:
                    return "removeClient";
                case 3:
                    return "startSession";
                case 4:
                    return "getFillEventHistory";
                case 5:
                    return "restoreSession";
                case 6:
                    return "updateSession";
                case 7:
                    return "setAutofillFailure";
                case 8:
                    return "finishSession";
                case 9:
                    return "cancelSession";
                case 10:
                    return "setAuthenticationResult";
                case 11:
                    return "setHasCallback";
                case 12:
                    return "disableOwnedAutofillServices";
                case 13:
                    return "isServiceSupported";
                case 14:
                    return "isServiceEnabled";
                case 15:
                    return "onPendingSaveUi";
                case 16:
                    return "getUserData";
                case 17:
                    return "getUserDataId";
                case 18:
                    return "setUserData";
                case 19:
                    return "isFieldClassificationEnabled";
                case 20:
                    return "getAutofillServiceComponentName";
                case 21:
                    return "getAvailableFieldClassificationAlgorithms";
                case 22:
                    return "getDefaultFieldClassificationAlgorithm";
                case 23:
                    return "setAugmentedAutofillWhitelist";
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
            AutofillId _arg2;
            Rect _arg3;
            AutofillValue _arg4;
            ComponentName _arg8;
            AutofillId _arg12;
            Rect _arg22;
            AutofillValue _arg32;
            Bundle _arg0;
            UserData _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg23 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            IAutoFillManagerClient _arg03 = IAutoFillManagerClient.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg24 = data.readInt();
                            IResultReceiver _arg33 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            addClient(_arg03, _arg1, _arg24, _arg33);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IAutoFillManagerClient _arg04 = IAutoFillManagerClient.Stub.asInterface(data.readStrongBinder());
                            int _arg13 = data.readInt();
                            removeClient(_arg04, _arg13);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg05 = data.readStrongBinder();
                            IBinder _arg14 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg2 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = AutofillValue.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            int _arg5 = data.readInt();
                            boolean _arg6 = data.readInt() != 0;
                            int _arg7 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg8 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg8 = null;
                            }
                            boolean _arg9 = data.readInt() != 0;
                            IResultReceiver _arg10 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            startSession(_arg05, _arg14, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg06 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getFillEventHistory(_arg06);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            IBinder _arg15 = data.readStrongBinder();
                            IBinder _arg25 = data.readStrongBinder();
                            IResultReceiver _arg34 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            restoreSession(_arg07, _arg15, _arg25, _arg34);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = AutofillValue.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            int _arg42 = data.readInt();
                            int _arg52 = data.readInt();
                            int _arg62 = data.readInt();
                            updateSession(_arg08, _arg12, _arg22, _arg32, _arg42, _arg52, _arg62);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            List<AutofillId> _arg16 = data.createTypedArrayList(AutofillId.CREATOR);
                            int _arg26 = data.readInt();
                            setAutofillFailure(_arg09, _arg16, _arg26);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg17 = data.readInt();
                            finishSession(_arg010, _arg17);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _arg18 = data.readInt();
                            cancelSession(_arg011, _arg18);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg19 = data.readInt();
                            int _arg27 = data.readInt();
                            int _arg35 = data.readInt();
                            setAuthenticationResult(_arg0, _arg19, _arg27, _arg35);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg110 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg23 = true;
                            }
                            setHasCallback(_arg012, _arg110, _arg23);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            disableOwnedAutofillServices(_arg013);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            IResultReceiver _arg111 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            isServiceSupported(_arg014, _arg111);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            String _arg112 = data.readString();
                            IResultReceiver _arg28 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            isServiceEnabled(_arg015, _arg112, _arg28);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            IBinder _arg113 = data.readStrongBinder();
                            onPendingSaveUi(_arg016, _arg113);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg017 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getUserData(_arg017);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg018 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getUserDataId(_arg018);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = UserData.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            setUserData(_arg02);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg019 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            isFieldClassificationEnabled(_arg019);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg020 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getAutofillServiceComponentName(_arg020);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg021 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getAvailableFieldClassificationAlgorithms(_arg021);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            IResultReceiver _arg022 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getDefaultFieldClassificationAlgorithm(_arg022);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _arg023 = data.createStringArrayList();
                            List<ComponentName> _arg114 = data.createTypedArrayList(ComponentName.CREATOR);
                            IResultReceiver _arg29 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            setAugmentedAutofillWhitelist(_arg023, _arg114, _arg29);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IAutoFillManager {
            public static IAutoFillManager sDefaultImpl;
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

            @Override // android.view.autofill.IAutoFillManager
            public void addClient(IAutoFillManagerClient client, ComponentName componentName, int userId, IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    if (componentName != null) {
                        _data.writeInt(1);
                        componentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addClient(client, componentName, userId, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void removeClient(IAutoFillManagerClient client, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeClient(client, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void startSession(IBinder activityToken, IBinder appCallback, AutofillId autoFillId, Rect bounds, AutofillValue value, int userId, boolean hasCallback, int flags, ComponentName componentName, boolean compatMode, IResultReceiver result) throws RemoteException {
                Parcel _data;
                Throwable th;
                Parcel _data2;
                Parcel _data3 = Parcel.obtain();
                try {
                    _data3.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data3.writeStrongBinder(activityToken);
                    _data3.writeStrongBinder(appCallback);
                    int i = 0;
                    if (autoFillId != null) {
                        try {
                            _data3.writeInt(1);
                            autoFillId.writeToParcel(_data3, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _data = _data3;
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data3.writeInt(0);
                    }
                    if (bounds != null) {
                        _data3.writeInt(1);
                        bounds.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (value != null) {
                        _data3.writeInt(1);
                        value.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeInt(userId);
                    _data3.writeInt(hasCallback ? 1 : 0);
                    _data3.writeInt(flags);
                    if (componentName != null) {
                        _data3.writeInt(1);
                        componentName.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (compatMode) {
                        i = 1;
                    }
                    _data3.writeInt(i);
                    _data3.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data3, null, 1);
                    if (_status) {
                        _data2 = _data3;
                    } else if (Stub.getDefaultImpl() != null) {
                        _data = _data3;
                        try {
                            Stub.getDefaultImpl().startSession(activityToken, appCallback, autoFillId, bounds, value, userId, hasCallback, flags, componentName, compatMode, result);
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

            @Override // android.view.autofill.IAutoFillManager
            public void getFillEventHistory(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFillEventHistory(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void restoreSession(int sessionId, IBinder activityToken, IBinder appCallback, IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeStrongBinder(activityToken);
                    _data.writeStrongBinder(appCallback);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreSession(sessionId, activityToken, appCallback, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void updateSession(int sessionId, AutofillId id, Rect bounds, AutofillValue value, int action, int flags, int userId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(sessionId);
                        if (id != null) {
                            _data.writeInt(1);
                            id.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (bounds != null) {
                            _data.writeInt(1);
                            bounds.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (value != null) {
                            _data.writeInt(1);
                            value.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeInt(action);
                            try {
                                _data.writeInt(flags);
                                _data.writeInt(userId);
                                boolean _status = this.mRemote.transact(6, _data, null, 1);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().updateSession(sessionId, id, bounds, value, action, flags, userId);
                                _data.recycle();
                            } catch (Throwable th2) {
                                th = th2;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setAutofillFailure(int sessionId, List<AutofillId> ids, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeTypedList(ids);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAutofillFailure(sessionId, ids, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void finishSession(int sessionId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishSession(sessionId, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void cancelSession(int sessionId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelSession(sessionId, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setAuthenticationResult(Bundle data, int sessionId, int authenticationId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sessionId);
                    _data.writeInt(authenticationId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAuthenticationResult(data, sessionId, authenticationId, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setHasCallback(int sessionId, int userId, boolean hasIt) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeInt(userId);
                    _data.writeInt(hasIt ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHasCallback(sessionId, userId, hasIt);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void disableOwnedAutofillServices(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableOwnedAutofillServices(userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void isServiceSupported(int userId, IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isServiceSupported(userId, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void isServiceEnabled(int userId, String packageName, IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isServiceEnabled(userId, packageName, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void onPendingSaveUi(int operation, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(operation);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPendingSaveUi(operation, token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getUserData(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getUserData(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getUserDataId(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getUserDataId(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setUserData(UserData userData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userData != null) {
                        _data.writeInt(1);
                        userData.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUserData(userData);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void isFieldClassificationEnabled(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isFieldClassificationEnabled(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getAutofillServiceComponentName(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAutofillServiceComponentName(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getAvailableFieldClassificationAlgorithms(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAvailableFieldClassificationAlgorithms(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void getDefaultFieldClassificationAlgorithm(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDefaultFieldClassificationAlgorithm(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAutoFillManager
            public void setAugmentedAutofillWhitelist(List<String> packages, List<ComponentName> activities, IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringList(packages);
                    _data.writeTypedList(activities);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAugmentedAutofillWhitelist(packages, activities, result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutoFillManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAutoFillManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
