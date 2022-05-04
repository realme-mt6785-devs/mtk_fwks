package android.view.autofill;

import android.app.assist.AssistStructure;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.autofill.IAutofillWindowPresenter;
import java.util.List;
/* loaded from: classes3.dex */
public interface IAugmentedAutofillManagerClient extends IInterface {
    public static final String DESCRIPTOR = "android.view.autofill.IAugmentedAutofillManagerClient";

    void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) throws RemoteException;

    Rect getViewCoordinates(AutofillId autofillId) throws RemoteException;

    AssistStructure.ViewNodeParcelable getViewNodeParcelable(AutofillId autofillId) throws RemoteException;

    boolean requestAutofill(int i, AutofillId autofillId) throws RemoteException;

    void requestHideFillUi(int i, AutofillId autofillId) throws RemoteException;

    void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IAugmentedAutofillManagerClient {
        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public Rect getViewCoordinates(AutofillId id) throws RemoteException {
            return null;
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public AssistStructure.ViewNodeParcelable getViewNodeParcelable(AutofillId id) throws RemoteException {
            return null;
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void autofill(int sessionId, List<AutofillId> ids, List<AutofillValue> values, boolean hideHighlight) throws RemoteException {
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void requestShowFillUi(int sessionId, AutofillId id, int width, int height, Rect anchorBounds, IAutofillWindowPresenter presenter) throws RemoteException {
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void requestHideFillUi(int sessionId, AutofillId id) throws RemoteException {
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public boolean requestAutofill(int sessionId, AutofillId id) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IAugmentedAutofillManagerClient {
        static final int TRANSACTION_autofill = 3;
        static final int TRANSACTION_getViewCoordinates = 1;
        static final int TRANSACTION_getViewNodeParcelable = 2;
        static final int TRANSACTION_requestAutofill = 6;
        static final int TRANSACTION_requestHideFillUi = 5;
        static final int TRANSACTION_requestShowFillUi = 4;

        public Stub() {
            attachInterface(this, IAugmentedAutofillManagerClient.DESCRIPTOR);
        }

        public static IAugmentedAutofillManagerClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAugmentedAutofillManagerClient.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAugmentedAutofillManagerClient)) {
                return new Proxy(obj);
            }
            return (IAugmentedAutofillManagerClient) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getViewCoordinates";
                case 2:
                    return "getViewNodeParcelable";
                case 3:
                    return "autofill";
                case 4:
                    return "requestShowFillUi";
                case 5:
                    return "requestHideFillUi";
                case 6:
                    return "requestAutofill";
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
            AutofillId _arg0;
            AutofillId _arg02;
            AutofillId _arg1;
            Rect _arg4;
            AutofillId _arg12;
            AutofillId _arg13;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IAugmentedAutofillManagerClient.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg3 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAugmentedAutofillManagerClient.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            Rect _result = getViewCoordinates(_arg0);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IAugmentedAutofillManagerClient.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            AssistStructure.ViewNodeParcelable _result2 = getViewNodeParcelable(_arg02);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(IAugmentedAutofillManagerClient.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            List<AutofillId> _arg14 = data.createTypedArrayList(AutofillId.CREATOR);
                            List<AutofillValue> _arg2 = data.createTypedArrayList(AutofillValue.CREATOR);
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            }
                            autofill(_arg03, _arg14, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IAugmentedAutofillManagerClient.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg22 = data.readInt();
                            int _arg32 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            IAutofillWindowPresenter _arg5 = IAutofillWindowPresenter.Stub.asInterface(data.readStrongBinder());
                            requestShowFillUi(_arg04, _arg1, _arg22, _arg32, _arg4, _arg5);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IAugmentedAutofillManagerClient.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            requestHideFillUi(_arg05, _arg12);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IAugmentedAutofillManagerClient.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            boolean requestAutofill = requestAutofill(_arg06, _arg13);
                            reply.writeNoException();
                            reply.writeInt(requestAutofill ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IAugmentedAutofillManagerClient {
            public static IAugmentedAutofillManagerClient sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAugmentedAutofillManagerClient.DESCRIPTOR;
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public Rect getViewCoordinates(AutofillId id) throws RemoteException {
                Rect _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillManagerClient.DESCRIPTOR);
                    if (id != null) {
                        _data.writeInt(1);
                        id.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getViewCoordinates(id);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Rect.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public AssistStructure.ViewNodeParcelable getViewNodeParcelable(AutofillId id) throws RemoteException {
                AssistStructure.ViewNodeParcelable _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillManagerClient.DESCRIPTOR);
                    if (id != null) {
                        _data.writeInt(1);
                        id.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getViewNodeParcelable(id);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AssistStructure.ViewNodeParcelable.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public void autofill(int sessionId, List<AutofillId> ids, List<AutofillValue> values, boolean hideHighlight) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillManagerClient.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    _data.writeTypedList(ids);
                    _data.writeTypedList(values);
                    _data.writeInt(hideHighlight ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().autofill(sessionId, ids, values, hideHighlight);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public void requestShowFillUi(int sessionId, AutofillId id, int width, int height, Rect anchorBounds, IAutofillWindowPresenter presenter) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillManagerClient.DESCRIPTOR);
                    try {
                        _data.writeInt(sessionId);
                        if (id != null) {
                            _data.writeInt(1);
                            id.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeInt(width);
                            try {
                                _data.writeInt(height);
                                if (anchorBounds != null) {
                                    _data.writeInt(1);
                                    anchorBounds.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                _data.writeStrongBinder(presenter != null ? presenter.asBinder() : null);
                                try {
                                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().requestShowFillUi(sessionId, id, width, height, anchorBounds, presenter);
                                    _reply.recycle();
                                    _data.recycle();
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public void requestHideFillUi(int sessionId, AutofillId id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillManagerClient.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    if (id != null) {
                        _data.writeInt(1);
                        id.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestHideFillUi(sessionId, id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.autofill.IAugmentedAutofillManagerClient
            public boolean requestAutofill(int sessionId, AutofillId id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillManagerClient.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _result = true;
                    if (id != null) {
                        _data.writeInt(1);
                        id.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestAutofill(sessionId, id);
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
        }

        public static boolean setDefaultImpl(IAugmentedAutofillManagerClient impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAugmentedAutofillManagerClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
