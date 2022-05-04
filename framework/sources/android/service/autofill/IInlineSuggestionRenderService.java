package android.service.autofill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.service.autofill.IInlineSuggestionUiCallback;
/* loaded from: classes3.dex */
public interface IInlineSuggestionRenderService extends IInterface {
    public static final String DESCRIPTOR = "android.service.autofill.IInlineSuggestionRenderService";

    void destroySuggestionViews(int i, int i2) throws RemoteException;

    void getInlineSuggestionsRendererInfo(RemoteCallback remoteCallback) throws RemoteException;

    void renderSuggestion(IInlineSuggestionUiCallback iInlineSuggestionUiCallback, InlinePresentation inlinePresentation, int i, int i2, IBinder iBinder, int i3, int i4, int i5) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IInlineSuggestionRenderService {
        @Override // android.service.autofill.IInlineSuggestionRenderService
        public void renderSuggestion(IInlineSuggestionUiCallback callback, InlinePresentation presentation, int width, int height, IBinder hostInputToken, int displayId, int userId, int sessionId) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionRenderService
        public void getInlineSuggestionsRendererInfo(RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.autofill.IInlineSuggestionRenderService
        public void destroySuggestionViews(int userId, int sessionId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IInlineSuggestionRenderService {
        static final int TRANSACTION_destroySuggestionViews = 3;
        static final int TRANSACTION_getInlineSuggestionsRendererInfo = 2;
        static final int TRANSACTION_renderSuggestion = 1;

        public Stub() {
            attachInterface(this, IInlineSuggestionRenderService.DESCRIPTOR);
        }

        public static IInlineSuggestionRenderService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInlineSuggestionRenderService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInlineSuggestionRenderService)) {
                return new Proxy(obj);
            }
            return (IInlineSuggestionRenderService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "renderSuggestion";
                case 2:
                    return "getInlineSuggestionsRendererInfo";
                case 3:
                    return "destroySuggestionViews";
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
            InlinePresentation _arg1;
            RemoteCallback _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IInlineSuggestionRenderService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInlineSuggestionRenderService.DESCRIPTOR);
                            IInlineSuggestionUiCallback _arg02 = IInlineSuggestionUiCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = InlinePresentation.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            int _arg2 = data.readInt();
                            int _arg3 = data.readInt();
                            IBinder _arg4 = data.readStrongBinder();
                            int _arg5 = data.readInt();
                            int _arg6 = data.readInt();
                            int _arg7 = data.readInt();
                            renderSuggestion(_arg02, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
                            return true;
                        case 2:
                            data.enforceInterface(IInlineSuggestionRenderService.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            getInlineSuggestionsRendererInfo(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(IInlineSuggestionRenderService.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            int _arg12 = data.readInt();
                            destroySuggestionViews(_arg03, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IInlineSuggestionRenderService {
            public static IInlineSuggestionRenderService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInlineSuggestionRenderService.DESCRIPTOR;
            }

            @Override // android.service.autofill.IInlineSuggestionRenderService
            public void renderSuggestion(IInlineSuggestionUiCallback callback, InlinePresentation presentation, int width, int height, IBinder hostInputToken, int displayId, int userId, int sessionId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionRenderService.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (presentation != null) {
                        _data.writeInt(1);
                        presentation.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(width);
                        try {
                            _data.writeInt(height);
                            try {
                                _data.writeStrongBinder(hostInputToken);
                                try {
                                    _data.writeInt(displayId);
                                    _data.writeInt(userId);
                                    _data.writeInt(sessionId);
                                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().renderSuggestion(callback, presentation, width, height, hostInputToken, displayId, userId, sessionId);
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
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.service.autofill.IInlineSuggestionRenderService
            public void getInlineSuggestionsRendererInfo(RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionRenderService.DESCRIPTOR);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getInlineSuggestionsRendererInfo(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.autofill.IInlineSuggestionRenderService
            public void destroySuggestionViews(int userId, int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInlineSuggestionRenderService.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(sessionId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroySuggestionViews(userId, sessionId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInlineSuggestionRenderService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInlineSuggestionRenderService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
