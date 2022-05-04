package android.app.contentsuggestions;

import android.app.contentsuggestions.IClassificationsCallback;
import android.app.contentsuggestions.ISelectionsCallback;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.os.IResultReceiver;
/* loaded from: classes.dex */
public interface IContentSuggestionsManager extends IInterface {
    public static final String DESCRIPTOR = "android.app.contentsuggestions.IContentSuggestionsManager";

    void classifyContentSelections(int i, ClassificationsRequest classificationsRequest, IClassificationsCallback iClassificationsCallback) throws RemoteException;

    void isEnabled(int i, IResultReceiver iResultReceiver) throws RemoteException;

    void notifyInteraction(int i, String str, Bundle bundle) throws RemoteException;

    void provideContextBitmap(int i, Bitmap bitmap, Bundle bundle) throws RemoteException;

    void provideContextImage(int i, int i2, Bundle bundle) throws RemoteException;

    void resetTemporaryService(int i) throws RemoteException;

    void setDefaultServiceEnabled(int i, boolean z) throws RemoteException;

    void setTemporaryService(int i, String str, int i2) throws RemoteException;

    void suggestContentSelections(int i, SelectionsRequest selectionsRequest, ISelectionsCallback iSelectionsCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IContentSuggestionsManager {
        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void provideContextImage(int userId, int taskId, Bundle imageContextRequestExtras) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void provideContextBitmap(int userId, Bitmap bitmap, Bundle imageContextRequestExtras) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void suggestContentSelections(int userId, SelectionsRequest request, ISelectionsCallback callback) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void classifyContentSelections(int userId, ClassificationsRequest request, IClassificationsCallback callback) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void notifyInteraction(int userId, String requestId, Bundle interaction) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void isEnabled(int userId, IResultReceiver receiver) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void resetTemporaryService(int userId) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void setTemporaryService(int userId, String serviceName, int duration) throws RemoteException {
        }

        @Override // android.app.contentsuggestions.IContentSuggestionsManager
        public void setDefaultServiceEnabled(int userId, boolean enabled) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IContentSuggestionsManager {
        static final int TRANSACTION_classifyContentSelections = 4;
        static final int TRANSACTION_isEnabled = 6;
        static final int TRANSACTION_notifyInteraction = 5;
        static final int TRANSACTION_provideContextBitmap = 2;
        static final int TRANSACTION_provideContextImage = 1;
        static final int TRANSACTION_resetTemporaryService = 7;
        static final int TRANSACTION_setDefaultServiceEnabled = 9;
        static final int TRANSACTION_setTemporaryService = 8;
        static final int TRANSACTION_suggestContentSelections = 3;

        public Stub() {
            attachInterface(this, IContentSuggestionsManager.DESCRIPTOR);
        }

        public static IContentSuggestionsManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IContentSuggestionsManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IContentSuggestionsManager)) {
                return new Proxy(obj);
            }
            return (IContentSuggestionsManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "provideContextImage";
                case 2:
                    return "provideContextBitmap";
                case 3:
                    return "suggestContentSelections";
                case 4:
                    return "classifyContentSelections";
                case 5:
                    return "notifyInteraction";
                case 6:
                    return "isEnabled";
                case 7:
                    return "resetTemporaryService";
                case 8:
                    return "setTemporaryService";
                case 9:
                    return "setDefaultServiceEnabled";
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
            Bundle _arg2;
            Bitmap _arg1;
            Bundle _arg22;
            SelectionsRequest _arg12;
            ClassificationsRequest _arg13;
            Bundle _arg23;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IContentSuggestionsManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg14 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            provideContextImage(_arg0, _arg14, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Bitmap.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            provideContextBitmap(_arg02, _arg1, _arg22);
                            return true;
                        case 3:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = SelectionsRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            ISelectionsCallback _arg24 = ISelectionsCallback.Stub.asInterface(data.readStrongBinder());
                            suggestContentSelections(_arg03, _arg12, _arg24);
                            return true;
                        case 4:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = ClassificationsRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            IClassificationsCallback _arg25 = IClassificationsCallback.Stub.asInterface(data.readStrongBinder());
                            classifyContentSelections(_arg04, _arg13, _arg25);
                            return true;
                        case 5:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            String _arg15 = data.readString();
                            if (data.readInt() != 0) {
                                _arg23 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            notifyInteraction(_arg05, _arg15, _arg23);
                            return true;
                        case 6:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            IResultReceiver _arg16 = IResultReceiver.Stub.asInterface(data.readStrongBinder());
                            isEnabled(_arg06, _arg16);
                            return true;
                        case 7:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            resetTemporaryService(_arg07);
                            return true;
                        case 8:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            String _arg17 = data.readString();
                            int _arg26 = data.readInt();
                            setTemporaryService(_arg08, _arg17, _arg26);
                            return true;
                        case 9:
                            data.enforceInterface(IContentSuggestionsManager.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            boolean _arg18 = data.readInt() != 0;
                            setDefaultServiceEnabled(_arg09, _arg18);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IContentSuggestionsManager {
            public static IContentSuggestionsManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IContentSuggestionsManager.DESCRIPTOR;
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void provideContextImage(int userId, int taskId, Bundle imageContextRequestExtras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(taskId);
                    if (imageContextRequestExtras != null) {
                        _data.writeInt(1);
                        imageContextRequestExtras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().provideContextImage(userId, taskId, imageContextRequestExtras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void provideContextBitmap(int userId, Bitmap bitmap, Bundle imageContextRequestExtras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (bitmap != null) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (imageContextRequestExtras != null) {
                        _data.writeInt(1);
                        imageContextRequestExtras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().provideContextBitmap(userId, bitmap, imageContextRequestExtras);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void suggestContentSelections(int userId, SelectionsRequest request, ISelectionsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suggestContentSelections(userId, request, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void classifyContentSelections(int userId, ClassificationsRequest request, IClassificationsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().classifyContentSelections(userId, request, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void notifyInteraction(int userId, String requestId, Bundle interaction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(requestId);
                    if (interaction != null) {
                        _data.writeInt(1);
                        interaction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyInteraction(userId, requestId, interaction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void isEnabled(int userId, IResultReceiver receiver) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isEnabled(userId, receiver);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void resetTemporaryService(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetTemporaryService(userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void setTemporaryService(int userId, String serviceName, int duration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeString(serviceName);
                    _data.writeInt(duration);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTemporaryService(userId, serviceName, duration);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.contentsuggestions.IContentSuggestionsManager
            public void setDefaultServiceEnabled(int userId, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IContentSuggestionsManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultServiceEnabled(userId, enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentSuggestionsManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IContentSuggestionsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
