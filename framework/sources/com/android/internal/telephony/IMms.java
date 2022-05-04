package com.android.internal.telephony;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IMms extends IInterface {
    Uri addMultimediaMessageDraft(String str, Uri uri) throws RemoteException;

    Uri addTextMessageDraft(String str, String str2, String str3) throws RemoteException;

    boolean archiveStoredConversation(String str, long j, boolean z) throws RemoteException;

    boolean deleteStoredConversation(String str, long j) throws RemoteException;

    boolean deleteStoredMessage(String str, Uri uri) throws RemoteException;

    void downloadMessage(int i, String str, String str2, Uri uri, Bundle bundle, PendingIntent pendingIntent, long j) throws RemoteException;

    boolean getAutoPersisting() throws RemoteException;

    Uri importMultimediaMessage(String str, Uri uri, String str2, long j, boolean z, boolean z2) throws RemoteException;

    Uri importTextMessage(String str, String str2, int i, String str3, long j, boolean z, boolean z2) throws RemoteException;

    void sendMessage(int i, String str, Uri uri, String str2, Bundle bundle, PendingIntent pendingIntent, long j) throws RemoteException;

    void sendStoredMessage(int i, String str, Uri uri, Bundle bundle, PendingIntent pendingIntent) throws RemoteException;

    void setAutoPersisting(String str, boolean z) throws RemoteException;

    boolean updateStoredMessageStatus(String str, Uri uri, ContentValues contentValues) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IMms {
        @Override // com.android.internal.telephony.IMms
        public void sendMessage(int subId, String callingPkg, Uri contentUri, String locationUrl, Bundle configOverrides, PendingIntent sentIntent, long messageId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IMms
        public void downloadMessage(int subId, String callingPkg, String locationUrl, Uri contentUri, Bundle configOverrides, PendingIntent downloadedIntent, long messageId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IMms
        public Uri importTextMessage(String callingPkg, String address, int type, String text, long timestampMillis, boolean seen, boolean read) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IMms
        public Uri importMultimediaMessage(String callingPkg, Uri contentUri, String messageId, long timestampSecs, boolean seen, boolean read) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IMms
        public boolean deleteStoredMessage(String callingPkg, Uri messageUri) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.IMms
        public boolean deleteStoredConversation(String callingPkg, long conversationId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.IMms
        public boolean updateStoredMessageStatus(String callingPkg, Uri messageUri, ContentValues statusValues) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.IMms
        public boolean archiveStoredConversation(String callingPkg, long conversationId, boolean archived) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.IMms
        public Uri addTextMessageDraft(String callingPkg, String address, String text) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IMms
        public Uri addMultimediaMessageDraft(String callingPkg, Uri contentUri) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IMms
        public void sendStoredMessage(int subId, String callingPkg, Uri messageUri, Bundle configOverrides, PendingIntent sentIntent) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IMms
        public void setAutoPersisting(String callingPkg, boolean enabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IMms
        public boolean getAutoPersisting() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IMms {
        public static final String DESCRIPTOR = "com.android.internal.telephony.IMms";
        static final int TRANSACTION_addMultimediaMessageDraft = 10;
        static final int TRANSACTION_addTextMessageDraft = 9;
        static final int TRANSACTION_archiveStoredConversation = 8;
        static final int TRANSACTION_deleteStoredConversation = 6;
        static final int TRANSACTION_deleteStoredMessage = 5;
        static final int TRANSACTION_downloadMessage = 2;
        static final int TRANSACTION_getAutoPersisting = 13;
        static final int TRANSACTION_importMultimediaMessage = 4;
        static final int TRANSACTION_importTextMessage = 3;
        static final int TRANSACTION_sendMessage = 1;
        static final int TRANSACTION_sendStoredMessage = 11;
        static final int TRANSACTION_setAutoPersisting = 12;
        static final int TRANSACTION_updateStoredMessageStatus = 7;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMms asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMms)) {
                return new Proxy(obj);
            }
            return (IMms) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "sendMessage";
                case 2:
                    return "downloadMessage";
                case 3:
                    return "importTextMessage";
                case 4:
                    return "importMultimediaMessage";
                case 5:
                    return "deleteStoredMessage";
                case 6:
                    return "deleteStoredConversation";
                case 7:
                    return "updateStoredMessageStatus";
                case 8:
                    return "archiveStoredConversation";
                case 9:
                    return "addTextMessageDraft";
                case 10:
                    return "addMultimediaMessageDraft";
                case 11:
                    return "sendStoredMessage";
                case 12:
                    return "setAutoPersisting";
                case 13:
                    return "getAutoPersisting";
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
            Uri _arg2;
            Bundle _arg4;
            PendingIntent _arg5;
            Uri _arg3;
            Bundle _arg42;
            PendingIntent _arg52;
            boolean _arg53;
            boolean _arg6;
            Uri _arg1;
            boolean _arg43;
            boolean _arg54;
            Uri _arg12;
            Uri _arg13;
            ContentValues _arg22;
            Uri _arg14;
            Uri _arg23;
            Bundle _arg32;
            PendingIntent _arg44;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg15 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0 = data.readInt();
                            String _arg16 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            String _arg33 = data.readString();
                            if (data.readInt() != 0) {
                                _arg4 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            long _arg62 = data.readLong();
                            sendMessage(_arg0, _arg16, _arg2, _arg33, _arg4, _arg5, _arg62);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _arg17 = data.readString();
                            String _arg24 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg42 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg52 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg52 = null;
                            }
                            long _arg63 = data.readLong();
                            downloadMessage(_arg02, _arg17, _arg24, _arg3, _arg42, _arg52, _arg63);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            String _arg18 = data.readString();
                            int _arg25 = data.readInt();
                            String _arg34 = data.readString();
                            long _arg45 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg53 = true;
                            } else {
                                _arg53 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg6 = true;
                            } else {
                                _arg6 = false;
                            }
                            Uri _result = importTextMessage(_arg03, _arg18, _arg25, _arg34, _arg45, _arg53, _arg6);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg26 = data.readString();
                            long _arg35 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg43 = true;
                            } else {
                                _arg43 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg54 = true;
                            } else {
                                _arg54 = false;
                            }
                            Uri _result2 = importMultimediaMessage(_arg04, _arg1, _arg26, _arg35, _arg43, _arg54);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            boolean deleteStoredMessage = deleteStoredMessage(_arg05, _arg12);
                            reply.writeNoException();
                            reply.writeInt(deleteStoredMessage ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            boolean deleteStoredConversation = deleteStoredConversation(_arg06, data.readLong());
                            reply.writeNoException();
                            reply.writeInt(deleteStoredConversation ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = ContentValues.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            boolean updateStoredMessageStatus = updateStoredMessageStatus(_arg07, _arg13, _arg22);
                            reply.writeNoException();
                            reply.writeInt(updateStoredMessageStatus ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            long _arg19 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            boolean archiveStoredConversation = archiveStoredConversation(_arg08, _arg19, _arg15);
                            reply.writeNoException();
                            reply.writeInt(archiveStoredConversation ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _arg110 = data.readString();
                            String _arg27 = data.readString();
                            Uri _result3 = addTextMessageDraft(_arg09, _arg110, _arg27);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            Uri _result4 = addMultimediaMessageDraft(_arg010, _arg14);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            String _arg111 = data.readString();
                            if (data.readInt() != 0) {
                                _arg23 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg44 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg44 = null;
                            }
                            sendStoredMessage(_arg011, _arg111, _arg23, _arg32, _arg44);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = true;
                            }
                            setAutoPersisting(_arg012, _arg15);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            boolean autoPersisting = getAutoPersisting();
                            reply.writeNoException();
                            reply.writeInt(autoPersisting ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IMms {
            public static IMms sDefaultImpl;
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

            @Override // com.android.internal.telephony.IMms
            public void sendMessage(int subId, String callingPkg, Uri contentUri, String locationUrl, Bundle configOverrides, PendingIntent sentIntent, long messageId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        _data.writeString(callingPkg);
                        if (contentUri != null) {
                            _data.writeInt(1);
                            contentUri.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeString(locationUrl);
                        if (configOverrides != null) {
                            _data.writeInt(1);
                            configOverrides.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (sentIntent != null) {
                            _data.writeInt(1);
                            sentIntent.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeLong(messageId);
                        boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().sendMessage(subId, callingPkg, contentUri, locationUrl, configOverrides, sentIntent, messageId);
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
            }

            @Override // com.android.internal.telephony.IMms
            public void downloadMessage(int subId, String callingPkg, String locationUrl, Uri contentUri, Bundle configOverrides, PendingIntent downloadedIntent, long messageId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        _data.writeString(callingPkg);
                        _data.writeString(locationUrl);
                        if (contentUri != null) {
                            _data.writeInt(1);
                            contentUri.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (configOverrides != null) {
                            _data.writeInt(1);
                            configOverrides.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (downloadedIntent != null) {
                            _data.writeInt(1);
                            downloadedIntent.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeLong(messageId);
                        boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().downloadMessage(subId, callingPkg, locationUrl, contentUri, configOverrides, downloadedIntent, messageId);
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
            }

            @Override // com.android.internal.telephony.IMms
            public Uri importTextMessage(String callingPkg, String address, int type, String text, long timestampMillis, boolean seen, boolean read) throws RemoteException {
                Throwable th;
                Uri _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(callingPkg);
                        try {
                            _data.writeString(address);
                            try {
                                _data.writeInt(type);
                                try {
                                    _data.writeString(text);
                                    _data.writeLong(timestampMillis);
                                    int i = 1;
                                    _data.writeInt(seen ? 1 : 0);
                                    if (!read) {
                                        i = 0;
                                    }
                                    _data.writeInt(i);
                                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = Uri.CREATOR.createFromParcel(_reply);
                                        } else {
                                            _result = null;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    Uri importTextMessage = Stub.getDefaultImpl().importTextMessage(callingPkg, address, type, text, timestampMillis, seen, read);
                                    _reply.recycle();
                                    _data.recycle();
                                    return importTextMessage;
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
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

            @Override // com.android.internal.telephony.IMms
            public Uri importMultimediaMessage(String callingPkg, Uri contentUri, String messageId, long timestampSecs, boolean seen, boolean read) throws RemoteException {
                Throwable th;
                Uri _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(callingPkg);
                        int i = 1;
                        if (contentUri != null) {
                            _data.writeInt(1);
                            contentUri.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeString(messageId);
                            try {
                                _data.writeLong(timestampSecs);
                                _data.writeInt(seen ? 1 : 0);
                                if (!read) {
                                    i = 0;
                                }
                                _data.writeInt(i);
                                boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() != 0) {
                                        _result = Uri.CREATOR.createFromParcel(_reply);
                                    } else {
                                        _result = null;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                Uri importMultimediaMessage = Stub.getDefaultImpl().importMultimediaMessage(callingPkg, contentUri, messageId, timestampSecs, seen, read);
                                _reply.recycle();
                                _data.recycle();
                                return importMultimediaMessage;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // com.android.internal.telephony.IMms
            public boolean deleteStoredMessage(String callingPkg, Uri messageUri) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    boolean _result = true;
                    if (messageUri != null) {
                        _data.writeInt(1);
                        messageUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteStoredMessage(callingPkg, messageUri);
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

            @Override // com.android.internal.telephony.IMms
            public boolean deleteStoredConversation(String callingPkg, long conversationId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeLong(conversationId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteStoredConversation(callingPkg, conversationId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.IMms
            public boolean updateStoredMessageStatus(String callingPkg, Uri messageUri, ContentValues statusValues) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    boolean _result = true;
                    if (messageUri != null) {
                        _data.writeInt(1);
                        messageUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (statusValues != null) {
                        _data.writeInt(1);
                        statusValues.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateStoredMessageStatus(callingPkg, messageUri, statusValues);
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

            @Override // com.android.internal.telephony.IMms
            public boolean archiveStoredConversation(String callingPkg, long conversationId, boolean archived) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeLong(conversationId);
                    boolean _result = true;
                    _data.writeInt(archived ? 1 : 0);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().archiveStoredConversation(callingPkg, conversationId, archived);
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

            @Override // com.android.internal.telephony.IMms
            public Uri addTextMessageDraft(String callingPkg, String address, String text) throws RemoteException {
                Uri _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(address);
                    _data.writeString(text);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addTextMessageDraft(callingPkg, address, text);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Uri.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.IMms
            public Uri addMultimediaMessageDraft(String callingPkg, Uri contentUri) throws RemoteException {
                Uri _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    if (contentUri != null) {
                        _data.writeInt(1);
                        contentUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addMultimediaMessageDraft(callingPkg, contentUri);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Uri.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.IMms
            public void sendStoredMessage(int subId, String callingPkg, Uri messageUri, Bundle configOverrides, PendingIntent sentIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPkg);
                    if (messageUri != null) {
                        _data.writeInt(1);
                        messageUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (configOverrides != null) {
                        _data.writeInt(1);
                        configOverrides.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (sentIntent != null) {
                        _data.writeInt(1);
                        sentIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().sendStoredMessage(subId, callingPkg, messageUri, configOverrides, sentIntent);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.IMms
            public void setAutoPersisting(String callingPkg, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAutoPersisting(callingPkg, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.IMms
            public boolean getAutoPersisting() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoPersisting();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMms impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMms getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
