package android.view.contentcapture;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.contentcapture.IContentCaptureOptionsCallback;
import android.view.contentcapture.IDataShareWriteAdapter;
import com.android.internal.os.IResultReceiver;
/* loaded from: classes3.dex */
public interface IContentCaptureManager extends IInterface {
    public static final String DESCRIPTOR = "android.view.contentcapture.IContentCaptureManager";

    void finishSession(int i) throws RemoteException;

    void getContentCaptureConditions(String str, IResultReceiver iResultReceiver) throws RemoteException;

    void getServiceComponentName(IResultReceiver iResultReceiver) throws RemoteException;

    void getServiceSettingsActivity(IResultReceiver iResultReceiver) throws RemoteException;

    void isContentCaptureFeatureEnabled(IResultReceiver iResultReceiver) throws RemoteException;

    void registerContentCaptureOptionsCallback(String str, IContentCaptureOptionsCallback iContentCaptureOptionsCallback) throws RemoteException;

    void removeData(DataRemovalRequest dataRemovalRequest) throws RemoteException;

    void resetTemporaryService(int i) throws RemoteException;

    void setDefaultServiceEnabled(int i, boolean z) throws RemoteException;

    void setTemporaryService(int i, String str, int i2) throws RemoteException;

    void shareData(DataShareRequest dataShareRequest, IDataShareWriteAdapter iDataShareWriteAdapter) throws RemoteException;

    void startSession(IBinder iBinder, IBinder iBinder2, ComponentName componentName, int i, int i2, IResultReceiver iResultReceiver) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IContentCaptureManager {
        @Override // android.view.contentcapture.IContentCaptureManager
        public void startSession(IBinder activityToken, IBinder shareableActivityToken, ComponentName componentName, int sessionId, int flags, IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void finishSession(int sessionId) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void getServiceComponentName(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void removeData(DataRemovalRequest request) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void shareData(DataShareRequest request, IDataShareWriteAdapter adapter) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void isContentCaptureFeatureEnabled(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void getServiceSettingsActivity(IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void getContentCaptureConditions(String packageName, IResultReceiver result) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void resetTemporaryService(int userId) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void setTemporaryService(int userId, String serviceName, int duration) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void setDefaultServiceEnabled(int userId, boolean enabled) throws RemoteException {
        }

        @Override // android.view.contentcapture.IContentCaptureManager
        public void registerContentCaptureOptionsCallback(String packageName, IContentCaptureOptionsCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IContentCaptureManager {
        static final int TRANSACTION_finishSession = 2;
        static final int TRANSACTION_getContentCaptureConditions = 8;
        static final int TRANSACTION_getServiceComponentName = 3;
        static final int TRANSACTION_getServiceSettingsActivity = 7;
        static final int TRANSACTION_isContentCaptureFeatureEnabled = 6;
        static final int TRANSACTION_registerContentCaptureOptionsCallback = 12;
        static final int TRANSACTION_removeData = 4;
        static final int TRANSACTION_resetTemporaryService = 9;
        static final int TRANSACTION_setDefaultServiceEnabled = 11;
        static final int TRANSACTION_setTemporaryService = 10;
        static final int TRANSACTION_shareData = 5;
        static final int TRANSACTION_startSession = 1;

        public Stub() {
            attachInterface(this, IContentCaptureManager.DESCRIPTOR);
        }

        public static IContentCaptureManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IContentCaptureManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IContentCaptureManager)) {
                return new Proxy(obj);
            }
            return (IContentCaptureManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startSession";
                case 2:
                    return "finishSession";
                case 3:
                    return "getServiceComponentName";
                case 4:
                    return "removeData";
                case 5:
                    return "shareData";
                case 6:
                    return "isContentCaptureFeatureEnabled";
                case 7:
                    return "getServiceSettingsActivity";
                case 8:
                    return "getContentCaptureConditions";
                case 9:
                    return "resetTemporaryService";
                case 10:
                    return "setTemporaryService";
                case 11:
                    return "setDefaultServiceEnabled";
                case 12:
                    return "registerContentCaptureOptionsCallback";
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
            ComponentName _arg2;
            DataRemovalRequest _arg0;
            DataShareRequest _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IContentCaptureManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            IBinder _arg03 = data.readStrongBinder();
                            IBinder _arg1 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg2 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _arg3 = data.readInt();
                            int _arg4 = data.readInt();
                            IResultReceiver _arg5 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            startSession(_arg03, _arg1, _arg2, _arg3, _arg4, _arg5);
                            return true;
                        case 2:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            finishSession(_arg04);
                            return true;
                        case 3:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            IResultReceiver _arg05 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getServiceComponentName(_arg05);
                            return true;
                        case 4:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = DataRemovalRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            removeData(_arg0);
                            return true;
                        case 5:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = DataShareRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            IDataShareWriteAdapter _arg12 = IDataShareWriteAdapter.Stub.asInterface(data.readStrongBinder());
                            shareData(_arg02, _arg12);
                            return true;
                        case 6:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            IResultReceiver _arg06 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            isContentCaptureFeatureEnabled(_arg06);
                            return true;
                        case 7:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            IResultReceiver _arg07 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getServiceSettingsActivity(_arg07);
                            return true;
                        case 8:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            String _arg08 = data.readString();
                            IResultReceiver _arg13 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            getContentCaptureConditions(_arg08, _arg13);
                            return true;
                        case 9:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            resetTemporaryService(_arg09);
                            return true;
                        case 10:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            String _arg14 = data.readString();
                            int _arg22 = data.readInt();
                            setTemporaryService(_arg010, _arg14, _arg22);
                            return true;
                        case 11:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            int _arg011 = data.readInt();
                            boolean _arg15 = data.readInt() != 0;
                            setDefaultServiceEnabled(_arg011, _arg15);
                            return true;
                        case 12:
                            data.enforceInterface(IContentCaptureManager.DESCRIPTOR);
                            String _arg012 = data.readString();
                            IContentCaptureOptionsCallback _arg16 = IContentCaptureOptionsCallback.Stub.asInterface(data.readStrongBinder());
                            registerContentCaptureOptionsCallback(_arg012, _arg16);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IContentCaptureManager {
            public static IContentCaptureManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IContentCaptureManager.DESCRIPTOR;
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void startSession(IBinder activityToken, IBinder shareableActivityToken, ComponentName componentName, int sessionId, int flags, IResultReceiver result) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeStrongBinder(activityToken);
                    try {
                        _data.writeStrongBinder(shareableActivityToken);
                        if (componentName != null) {
                            _data.writeInt(1);
                            componentName.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeInt(sessionId);
                            try {
                                _data.writeInt(flags);
                                _data.writeStrongBinder(result != null ? result.asBinder() : null);
                                try {
                                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().startSession(activityToken, shareableActivityToken, componentName, sessionId, flags, result);
                                    _data.recycle();
                                } catch (Throwable th3) {
                                    th = th3;
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void finishSession(int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishSession(sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void getServiceComponentName(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getServiceComponentName(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void removeData(DataRemovalRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeData(request);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void shareData(DataShareRequest request, IDataShareWriteAdapter adapter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(adapter != null ? adapter.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().shareData(request, adapter);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void isContentCaptureFeatureEnabled(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isContentCaptureFeatureEnabled(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void getServiceSettingsActivity(IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getServiceSettingsActivity(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void getContentCaptureConditions(String packageName, IResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getContentCaptureConditions(packageName, result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void resetTemporaryService(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetTemporaryService(userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void setTemporaryService(int userId, String serviceName, int duration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(serviceName);
                    _data.writeInt(duration);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTemporaryService(userId, serviceName, duration);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void setDefaultServiceEnabled(int userId, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultServiceEnabled(userId, enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.contentcapture.IContentCaptureManager
            public void registerContentCaptureOptionsCallback(String packageName, IContentCaptureOptionsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentCaptureManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerContentCaptureOptionsCallback(packageName, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentCaptureManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IContentCaptureManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
