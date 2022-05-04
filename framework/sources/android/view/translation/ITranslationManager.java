package android.view.translation;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.view.autofill.AutofillId;
import com.android.internal.os.IResultReceiver;
import java.util.List;
/* loaded from: classes3.dex */
public interface ITranslationManager extends IInterface {
    public static final String DESCRIPTOR = "android.view.translation.ITranslationManager";

    void getServiceSettingsActivity(IResultReceiver iResultReceiver, int i) throws RemoteException;

    void onSessionCreated(TranslationContext translationContext, int i, IResultReceiver iResultReceiver, int i2) throws RemoteException;

    void onTranslationCapabilitiesRequest(int i, int i2, ResultReceiver resultReceiver, int i3) throws RemoteException;

    void onTranslationFinished(boolean z, IBinder iBinder, ComponentName componentName, int i) throws RemoteException;

    void registerTranslationCapabilityCallback(IRemoteCallback iRemoteCallback, int i) throws RemoteException;

    void registerUiTranslationStateCallback(IRemoteCallback iRemoteCallback, int i) throws RemoteException;

    void unregisterTranslationCapabilityCallback(IRemoteCallback iRemoteCallback, int i) throws RemoteException;

    void unregisterUiTranslationStateCallback(IRemoteCallback iRemoteCallback, int i) throws RemoteException;

    void updateUiTranslationState(int i, TranslationSpec translationSpec, TranslationSpec translationSpec2, List<AutofillId> list, IBinder iBinder, int i2, UiTranslationSpec uiTranslationSpec, int i3) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITranslationManager {
        @Override // android.view.translation.ITranslationManager
        public void onTranslationCapabilitiesRequest(int sourceFormat, int destFormat, ResultReceiver receiver, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void registerTranslationCapabilityCallback(IRemoteCallback callback, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void unregisterTranslationCapabilityCallback(IRemoteCallback callback, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void onSessionCreated(TranslationContext translationContext, int sessionId, IResultReceiver receiver, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void updateUiTranslationState(int state, TranslationSpec sourceSpec, TranslationSpec targetSpec, List<AutofillId> viewIds, IBinder token, int taskId, UiTranslationSpec uiTranslationSpec, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void registerUiTranslationStateCallback(IRemoteCallback callback, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void unregisterUiTranslationStateCallback(IRemoteCallback callback, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void getServiceSettingsActivity(IResultReceiver result, int userId) throws RemoteException {
        }

        @Override // android.view.translation.ITranslationManager
        public void onTranslationFinished(boolean activityDestroyed, IBinder token, ComponentName componentName, int userId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITranslationManager {
        static final int TRANSACTION_getServiceSettingsActivity = 8;
        static final int TRANSACTION_onSessionCreated = 4;
        static final int TRANSACTION_onTranslationCapabilitiesRequest = 1;
        static final int TRANSACTION_onTranslationFinished = 9;
        static final int TRANSACTION_registerTranslationCapabilityCallback = 2;
        static final int TRANSACTION_registerUiTranslationStateCallback = 6;
        static final int TRANSACTION_unregisterTranslationCapabilityCallback = 3;
        static final int TRANSACTION_unregisterUiTranslationStateCallback = 7;
        static final int TRANSACTION_updateUiTranslationState = 5;

        public Stub() {
            attachInterface(this, ITranslationManager.DESCRIPTOR);
        }

        public static ITranslationManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITranslationManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITranslationManager)) {
                return new Proxy(obj);
            }
            return (ITranslationManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTranslationCapabilitiesRequest";
                case 2:
                    return "registerTranslationCapabilityCallback";
                case 3:
                    return "unregisterTranslationCapabilityCallback";
                case 4:
                    return "onSessionCreated";
                case 5:
                    return "updateUiTranslationState";
                case 6:
                    return "registerUiTranslationStateCallback";
                case 7:
                    return "unregisterUiTranslationStateCallback";
                case 8:
                    return "getServiceSettingsActivity";
                case 9:
                    return "onTranslationFinished";
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
            ResultReceiver _arg2;
            TranslationContext _arg0;
            TranslationSpec _arg1;
            TranslationSpec _arg22;
            UiTranslationSpec _arg6;
            ComponentName _arg23;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITranslationManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg12 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = ResultReceiver.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _arg3 = data.readInt();
                            onTranslationCapabilitiesRequest(_arg02, _arg12, _arg2, _arg3);
                            return true;
                        case 2:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            IRemoteCallback _arg03 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg13 = data.readInt();
                            registerTranslationCapabilityCallback(_arg03, _arg13);
                            return true;
                        case 3:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            IRemoteCallback _arg04 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg14 = data.readInt();
                            unregisterTranslationCapabilityCallback(_arg04, _arg14);
                            return true;
                        case 4:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TranslationContext.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg15 = data.readInt();
                            IResultReceiver _arg24 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            int _arg32 = data.readInt();
                            onSessionCreated(_arg0, _arg15, _arg24, _arg32);
                            return true;
                        case 5:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = TranslationSpec.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = TranslationSpec.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            List<AutofillId> _arg33 = data.createTypedArrayList(AutofillId.CREATOR);
                            IBinder _arg4 = data.readStrongBinder();
                            int _arg5 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg6 = UiTranslationSpec.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            int _arg7 = data.readInt();
                            updateUiTranslationState(_arg05, _arg1, _arg22, _arg33, _arg4, _arg5, _arg6, _arg7);
                            return true;
                        case 6:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            IRemoteCallback _arg06 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg16 = data.readInt();
                            registerUiTranslationStateCallback(_arg06, _arg16);
                            return true;
                        case 7:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            IRemoteCallback _arg07 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg17 = data.readInt();
                            unregisterUiTranslationStateCallback(_arg07, _arg17);
                            return true;
                        case 8:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            IResultReceiver _arg08 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            int _arg18 = data.readInt();
                            getServiceSettingsActivity(_arg08, _arg18);
                            return true;
                        case 9:
                            data.enforceInterface(ITranslationManager.DESCRIPTOR);
                            boolean _arg09 = data.readInt() != 0;
                            IBinder _arg19 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg23 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            int _arg34 = data.readInt();
                            onTranslationFinished(_arg09, _arg19, _arg23, _arg34);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITranslationManager {
            public static ITranslationManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITranslationManager.DESCRIPTOR;
            }

            @Override // android.view.translation.ITranslationManager
            public void onTranslationCapabilitiesRequest(int sourceFormat, int destFormat, ResultReceiver receiver, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    _data.writeInt(sourceFormat);
                    _data.writeInt(destFormat);
                    if (receiver != null) {
                        _data.writeInt(1);
                        receiver.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTranslationCapabilitiesRequest(sourceFormat, destFormat, receiver, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void registerTranslationCapabilityCallback(IRemoteCallback callback, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTranslationCapabilityCallback(callback, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void unregisterTranslationCapabilityCallback(IRemoteCallback callback, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterTranslationCapabilityCallback(callback, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void onSessionCreated(TranslationContext translationContext, int sessionId, IResultReceiver receiver, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    if (translationContext != null) {
                        _data.writeInt(1);
                        translationContext.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sessionId);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSessionCreated(translationContext, sessionId, receiver, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void updateUiTranslationState(int state, TranslationSpec sourceSpec, TranslationSpec targetSpec, List<AutofillId> viewIds, IBinder token, int taskId, UiTranslationSpec uiTranslationSpec, int userId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    try {
                        _data.writeInt(state);
                        if (sourceSpec != null) {
                            _data.writeInt(1);
                            sourceSpec.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (targetSpec != null) {
                            _data.writeInt(1);
                            targetSpec.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeTypedList(viewIds);
                            _data.writeStrongBinder(token);
                            _data.writeInt(taskId);
                            if (uiTranslationSpec != null) {
                                _data.writeInt(1);
                                uiTranslationSpec.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            _data.writeInt(userId);
                            boolean _status = this.mRemote.transact(5, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().updateUiTranslationState(state, sourceSpec, targetSpec, viewIds, token, taskId, uiTranslationSpec, userId);
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
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void registerUiTranslationStateCallback(IRemoteCallback callback, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerUiTranslationStateCallback(callback, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void unregisterUiTranslationStateCallback(IRemoteCallback callback, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterUiTranslationStateCallback(callback, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void getServiceSettingsActivity(IResultReceiver result, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getServiceSettingsActivity(result, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.translation.ITranslationManager
            public void onTranslationFinished(boolean activityDestroyed, IBinder token, ComponentName componentName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITranslationManager.DESCRIPTOR);
                    _data.writeInt(activityDestroyed ? 1 : 0);
                    _data.writeStrongBinder(token);
                    if (componentName != null) {
                        _data.writeInt(1);
                        componentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTranslationFinished(activityDestroyed, token, componentName, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITranslationManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITranslationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
