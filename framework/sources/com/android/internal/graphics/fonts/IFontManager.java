package com.android.internal.graphics.fonts;

import android.graphics.fonts.FontUpdateRequest;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.FontConfig;
import java.util.List;
/* loaded from: classes4.dex */
public interface IFontManager extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.graphics.fonts.IFontManager";

    FontConfig getFontConfig() throws RemoteException;

    int updateFontFamily(List<FontUpdateRequest> list, int i) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IFontManager {
        @Override // com.android.internal.graphics.fonts.IFontManager
        public FontConfig getFontConfig() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.graphics.fonts.IFontManager
        public int updateFontFamily(List<FontUpdateRequest> request, int baseVersion) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IFontManager {
        static final int TRANSACTION_getFontConfig = 1;
        static final int TRANSACTION_updateFontFamily = 2;

        public Stub() {
            attachInterface(this, IFontManager.DESCRIPTOR);
        }

        public static IFontManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IFontManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IFontManager)) {
                return new Proxy(obj);
            }
            return (IFontManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getFontConfig";
                case 2:
                    return "updateFontFamily";
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
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IFontManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IFontManager.DESCRIPTOR);
                            FontConfig _result = getFontConfig();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IFontManager.DESCRIPTOR);
                            List<FontUpdateRequest> _arg0 = data.createTypedArrayList(FontUpdateRequest.CREATOR);
                            int _arg1 = data.readInt();
                            int _result2 = updateFontFamily(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IFontManager {
            public static IFontManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IFontManager.DESCRIPTOR;
            }

            @Override // com.android.internal.graphics.fonts.IFontManager
            public FontConfig getFontConfig() throws RemoteException {
                FontConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFontManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFontConfig();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = FontConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.graphics.fonts.IFontManager
            public int updateFontFamily(List<FontUpdateRequest> request, int baseVersion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFontManager.DESCRIPTOR);
                    _data.writeTypedList(request);
                    _data.writeInt(baseVersion);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateFontFamily(request, baseVersion);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFontManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IFontManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
