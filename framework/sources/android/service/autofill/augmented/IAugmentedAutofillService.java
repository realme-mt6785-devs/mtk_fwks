package android.service.autofill.augmented;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.autofill.augmented.IFillCallback;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.InlineSuggestionsRequest;
/* loaded from: classes3.dex */
public interface IAugmentedAutofillService extends IInterface {
    public static final String DESCRIPTOR = "android.service.autofill.augmented.IAugmentedAutofillService";

    void onConnected(boolean z, boolean z2) throws RemoteException;

    void onDestroyAllFillWindowsRequest() throws RemoteException;

    void onDisconnected() throws RemoteException;

    void onFillRequest(int i, IBinder iBinder, int i2, ComponentName componentName, AutofillId autofillId, AutofillValue autofillValue, long j, InlineSuggestionsRequest inlineSuggestionsRequest, IFillCallback iFillCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IAugmentedAutofillService {
        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onConnected(boolean debug, boolean verbose) throws RemoteException {
        }

        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onDisconnected() throws RemoteException {
        }

        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onFillRequest(int sessionId, IBinder autofillManagerClient, int taskId, ComponentName activityComponent, AutofillId focusedId, AutofillValue focusedValue, long requestTime, InlineSuggestionsRequest inlineSuggestionsRequest, IFillCallback callback) throws RemoteException {
        }

        @Override // android.service.autofill.augmented.IAugmentedAutofillService
        public void onDestroyAllFillWindowsRequest() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IAugmentedAutofillService {
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onDestroyAllFillWindowsRequest = 4;
        static final int TRANSACTION_onDisconnected = 2;
        static final int TRANSACTION_onFillRequest = 3;

        public Stub() {
            attachInterface(this, IAugmentedAutofillService.DESCRIPTOR);
        }

        public static IAugmentedAutofillService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAugmentedAutofillService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAugmentedAutofillService)) {
                return new Proxy(obj);
            }
            return (IAugmentedAutofillService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onConnected";
                case 2:
                    return "onDisconnected";
                case 3:
                    return "onFillRequest";
                case 4:
                    return "onDestroyAllFillWindowsRequest";
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
            ComponentName _arg3;
            AutofillId _arg4;
            AutofillValue _arg5;
            InlineSuggestionsRequest _arg7;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IAugmentedAutofillService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAugmentedAutofillService.DESCRIPTOR);
                            boolean _arg1 = false;
                            boolean _arg0 = data.readInt() != 0;
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            onConnected(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IAugmentedAutofillService.DESCRIPTOR);
                            onDisconnected();
                            return true;
                        case 3:
                            data.enforceInterface(IAugmentedAutofillService.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            IBinder _arg12 = data.readStrongBinder();
                            int _arg2 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = AutofillId.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = AutofillValue.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            long _arg6 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg7 = InlineSuggestionsRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg7 = null;
                            }
                            IFillCallback _arg8 = IFillCallback.Stub.asInterface(data.readStrongBinder());
                            onFillRequest(_arg02, _arg12, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8);
                            return true;
                        case 4:
                            data.enforceInterface(IAugmentedAutofillService.DESCRIPTOR);
                            onDestroyAllFillWindowsRequest();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IAugmentedAutofillService {
            public static IAugmentedAutofillService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAugmentedAutofillService.DESCRIPTOR;
            }

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
            public void onConnected(boolean debug, boolean verbose) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillService.DESCRIPTOR);
                    int i = 0;
                    _data.writeInt(debug ? 1 : 0);
                    if (verbose) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnected(debug, verbose);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
            public void onDisconnected() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisconnected();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
            public void onFillRequest(int sessionId, IBinder autofillManagerClient, int taskId, ComponentName activityComponent, AutofillId focusedId, AutofillValue focusedValue, long requestTime, InlineSuggestionsRequest inlineSuggestionsRequest, IFillCallback callback) throws RemoteException {
                Parcel _data;
                Throwable th;
                Parcel _data2;
                Parcel _data3 = Parcel.obtain();
                try {
                    _data3.writeInterfaceToken(IAugmentedAutofillService.DESCRIPTOR);
                    _data3.writeInt(sessionId);
                    _data3.writeStrongBinder(autofillManagerClient);
                    _data3.writeInt(taskId);
                    if (activityComponent != null) {
                        try {
                            _data3.writeInt(1);
                            activityComponent.writeToParcel(_data3, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _data = _data3;
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data3.writeInt(0);
                    }
                    if (focusedId != null) {
                        _data3.writeInt(1);
                        focusedId.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (focusedValue != null) {
                        _data3.writeInt(1);
                        focusedValue.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeLong(requestTime);
                    if (inlineSuggestionsRequest != null) {
                        _data3.writeInt(1);
                        inlineSuggestionsRequest.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data3, null, 1);
                    if (_status) {
                        _data2 = _data3;
                    } else if (Stub.getDefaultImpl() != null) {
                        _data = _data3;
                        try {
                            Stub.getDefaultImpl().onFillRequest(sessionId, autofillManagerClient, taskId, activityComponent, focusedId, focusedValue, requestTime, inlineSuggestionsRequest, callback);
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

            @Override // android.service.autofill.augmented.IAugmentedAutofillService
            public void onDestroyAllFillWindowsRequest() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAugmentedAutofillService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDestroyAllFillWindowsRequest();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAugmentedAutofillService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAugmentedAutofillService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
