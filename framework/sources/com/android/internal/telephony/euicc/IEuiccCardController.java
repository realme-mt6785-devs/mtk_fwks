package com.android.internal.telephony.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.telephony.euicc.IAuthenticateServerCallback;
import com.android.internal.telephony.euicc.ICancelSessionCallback;
import com.android.internal.telephony.euicc.IDeleteProfileCallback;
import com.android.internal.telephony.euicc.IDisableProfileCallback;
import com.android.internal.telephony.euicc.IGetAllProfilesCallback;
import com.android.internal.telephony.euicc.IGetDefaultSmdpAddressCallback;
import com.android.internal.telephony.euicc.IGetEuiccChallengeCallback;
import com.android.internal.telephony.euicc.IGetEuiccInfo1Callback;
import com.android.internal.telephony.euicc.IGetEuiccInfo2Callback;
import com.android.internal.telephony.euicc.IGetProfileCallback;
import com.android.internal.telephony.euicc.IGetRulesAuthTableCallback;
import com.android.internal.telephony.euicc.IGetSmdsAddressCallback;
import com.android.internal.telephony.euicc.IListNotificationsCallback;
import com.android.internal.telephony.euicc.ILoadBoundProfilePackageCallback;
import com.android.internal.telephony.euicc.IPrepareDownloadCallback;
import com.android.internal.telephony.euicc.IRemoveNotificationFromListCallback;
import com.android.internal.telephony.euicc.IResetMemoryCallback;
import com.android.internal.telephony.euicc.IRetrieveNotificationCallback;
import com.android.internal.telephony.euicc.IRetrieveNotificationListCallback;
import com.android.internal.telephony.euicc.ISetDefaultSmdpAddressCallback;
import com.android.internal.telephony.euicc.ISetNicknameCallback;
import com.android.internal.telephony.euicc.ISwitchToProfileCallback;
/* loaded from: classes4.dex */
public interface IEuiccCardController extends IInterface {
    public static final String DESCRIPTOR = "com.android.internal.telephony.euicc.IEuiccCardController";

    void authenticateServer(String str, String str2, String str3, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IAuthenticateServerCallback iAuthenticateServerCallback) throws RemoteException;

    void cancelSession(String str, String str2, byte[] bArr, int i, ICancelSessionCallback iCancelSessionCallback) throws RemoteException;

    void deleteProfile(String str, String str2, String str3, IDeleteProfileCallback iDeleteProfileCallback) throws RemoteException;

    void disableProfile(String str, String str2, String str3, boolean z, IDisableProfileCallback iDisableProfileCallback) throws RemoteException;

    void getAllProfiles(String str, String str2, IGetAllProfilesCallback iGetAllProfilesCallback) throws RemoteException;

    void getDefaultSmdpAddress(String str, String str2, IGetDefaultSmdpAddressCallback iGetDefaultSmdpAddressCallback) throws RemoteException;

    void getEuiccChallenge(String str, String str2, IGetEuiccChallengeCallback iGetEuiccChallengeCallback) throws RemoteException;

    void getEuiccInfo1(String str, String str2, IGetEuiccInfo1Callback iGetEuiccInfo1Callback) throws RemoteException;

    void getEuiccInfo2(String str, String str2, IGetEuiccInfo2Callback iGetEuiccInfo2Callback) throws RemoteException;

    void getProfile(String str, String str2, String str3, IGetProfileCallback iGetProfileCallback) throws RemoteException;

    void getRulesAuthTable(String str, String str2, IGetRulesAuthTableCallback iGetRulesAuthTableCallback) throws RemoteException;

    void getSmdsAddress(String str, String str2, IGetSmdsAddressCallback iGetSmdsAddressCallback) throws RemoteException;

    void listNotifications(String str, String str2, int i, IListNotificationsCallback iListNotificationsCallback) throws RemoteException;

    void loadBoundProfilePackage(String str, String str2, byte[] bArr, ILoadBoundProfilePackageCallback iLoadBoundProfilePackageCallback) throws RemoteException;

    void prepareDownload(String str, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, IPrepareDownloadCallback iPrepareDownloadCallback) throws RemoteException;

    void removeNotificationFromList(String str, String str2, int i, IRemoveNotificationFromListCallback iRemoveNotificationFromListCallback) throws RemoteException;

    void resetMemory(String str, String str2, int i, IResetMemoryCallback iResetMemoryCallback) throws RemoteException;

    void retrieveNotification(String str, String str2, int i, IRetrieveNotificationCallback iRetrieveNotificationCallback) throws RemoteException;

    void retrieveNotificationList(String str, String str2, int i, IRetrieveNotificationListCallback iRetrieveNotificationListCallback) throws RemoteException;

    void setDefaultSmdpAddress(String str, String str2, String str3, ISetDefaultSmdpAddressCallback iSetDefaultSmdpAddressCallback) throws RemoteException;

    void setNickname(String str, String str2, String str3, String str4, ISetNicknameCallback iSetNicknameCallback) throws RemoteException;

    void switchToProfile(String str, String str2, String str3, boolean z, ISwitchToProfileCallback iSwitchToProfileCallback) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IEuiccCardController {
        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getAllProfiles(String callingPackage, String cardId, IGetAllProfilesCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getProfile(String callingPackage, String cardId, String iccid, IGetProfileCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void disableProfile(String callingPackage, String cardId, String iccid, boolean refresh, IDisableProfileCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void switchToProfile(String callingPackage, String cardId, String iccid, boolean refresh, ISwitchToProfileCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void setNickname(String callingPackage, String cardId, String iccid, String nickname, ISetNicknameCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void deleteProfile(String callingPackage, String cardId, String iccid, IDeleteProfileCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void resetMemory(String callingPackage, String cardId, int options, IResetMemoryCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getDefaultSmdpAddress(String callingPackage, String cardId, IGetDefaultSmdpAddressCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getSmdsAddress(String callingPackage, String cardId, IGetSmdsAddressCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void setDefaultSmdpAddress(String callingPackage, String cardId, String address, ISetDefaultSmdpAddressCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getRulesAuthTable(String callingPackage, String cardId, IGetRulesAuthTableCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getEuiccChallenge(String callingPackage, String cardId, IGetEuiccChallengeCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getEuiccInfo1(String callingPackage, String cardId, IGetEuiccInfo1Callback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void getEuiccInfo2(String callingPackage, String cardId, IGetEuiccInfo2Callback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void authenticateServer(String callingPackage, String cardId, String matchingId, byte[] serverSigned1, byte[] serverSignature1, byte[] euiccCiPkIdToBeUsed, byte[] serverCertificatein, IAuthenticateServerCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void prepareDownload(String callingPackage, String cardId, byte[] hashCc, byte[] smdpSigned2, byte[] smdpSignature2, byte[] smdpCertificate, IPrepareDownloadCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void loadBoundProfilePackage(String callingPackage, String cardId, byte[] boundProfilePackage, ILoadBoundProfilePackageCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void cancelSession(String callingPackage, String cardId, byte[] transactionId, int reason, ICancelSessionCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void listNotifications(String callingPackage, String cardId, int events, IListNotificationsCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void retrieveNotificationList(String callingPackage, String cardId, int events, IRetrieveNotificationListCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void retrieveNotification(String callingPackage, String cardId, int seqNumber, IRetrieveNotificationCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.euicc.IEuiccCardController
        public void removeNotificationFromList(String callingPackage, String cardId, int seqNumber, IRemoveNotificationFromListCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IEuiccCardController {
        static final int TRANSACTION_authenticateServer = 15;
        static final int TRANSACTION_cancelSession = 18;
        static final int TRANSACTION_deleteProfile = 6;
        static final int TRANSACTION_disableProfile = 3;
        static final int TRANSACTION_getAllProfiles = 1;
        static final int TRANSACTION_getDefaultSmdpAddress = 8;
        static final int TRANSACTION_getEuiccChallenge = 12;
        static final int TRANSACTION_getEuiccInfo1 = 13;
        static final int TRANSACTION_getEuiccInfo2 = 14;
        static final int TRANSACTION_getProfile = 2;
        static final int TRANSACTION_getRulesAuthTable = 11;
        static final int TRANSACTION_getSmdsAddress = 9;
        static final int TRANSACTION_listNotifications = 19;
        static final int TRANSACTION_loadBoundProfilePackage = 17;
        static final int TRANSACTION_prepareDownload = 16;
        static final int TRANSACTION_removeNotificationFromList = 22;
        static final int TRANSACTION_resetMemory = 7;
        static final int TRANSACTION_retrieveNotification = 21;
        static final int TRANSACTION_retrieveNotificationList = 20;
        static final int TRANSACTION_setDefaultSmdpAddress = 10;
        static final int TRANSACTION_setNickname = 5;
        static final int TRANSACTION_switchToProfile = 4;

        public Stub() {
            attachInterface(this, IEuiccCardController.DESCRIPTOR);
        }

        public static IEuiccCardController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IEuiccCardController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IEuiccCardController)) {
                return new Proxy(obj);
            }
            return (IEuiccCardController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getAllProfiles";
                case 2:
                    return "getProfile";
                case 3:
                    return "disableProfile";
                case 4:
                    return "switchToProfile";
                case 5:
                    return "setNickname";
                case 6:
                    return "deleteProfile";
                case 7:
                    return "resetMemory";
                case 8:
                    return "getDefaultSmdpAddress";
                case 9:
                    return "getSmdsAddress";
                case 10:
                    return "setDefaultSmdpAddress";
                case 11:
                    return "getRulesAuthTable";
                case 12:
                    return "getEuiccChallenge";
                case 13:
                    return "getEuiccInfo1";
                case 14:
                    return "getEuiccInfo2";
                case 15:
                    return "authenticateServer";
                case 16:
                    return "prepareDownload";
                case 17:
                    return "loadBoundProfilePackage";
                case 18:
                    return "cancelSession";
                case 19:
                    return "listNotifications";
                case 20:
                    return "retrieveNotificationList";
                case 21:
                    return "retrieveNotification";
                case 22:
                    return "removeNotificationFromList";
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
                    reply.writeString(IEuiccCardController.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg0 = data.readString();
                            String _arg1 = data.readString();
                            IGetAllProfilesCallback _arg2 = IGetAllProfilesCallback.Stub.asInterface(data.readStrongBinder());
                            getAllProfiles(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg02 = data.readString();
                            String _arg12 = data.readString();
                            String _arg22 = data.readString();
                            IGetProfileCallback _arg3 = IGetProfileCallback.Stub.asInterface(data.readStrongBinder());
                            getProfile(_arg02, _arg12, _arg22, _arg3);
                            return true;
                        case 3:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg03 = data.readString();
                            String _arg13 = data.readString();
                            String _arg23 = data.readString();
                            boolean _arg32 = data.readInt() != 0;
                            IDisableProfileCallback _arg4 = IDisableProfileCallback.Stub.asInterface(data.readStrongBinder());
                            disableProfile(_arg03, _arg13, _arg23, _arg32, _arg4);
                            return true;
                        case 4:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg04 = data.readString();
                            String _arg14 = data.readString();
                            String _arg24 = data.readString();
                            boolean _arg33 = data.readInt() != 0;
                            ISwitchToProfileCallback _arg42 = ISwitchToProfileCallback.Stub.asInterface(data.readStrongBinder());
                            switchToProfile(_arg04, _arg14, _arg24, _arg33, _arg42);
                            return true;
                        case 5:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg05 = data.readString();
                            String _arg15 = data.readString();
                            String _arg25 = data.readString();
                            String _arg34 = data.readString();
                            ISetNicknameCallback _arg43 = ISetNicknameCallback.Stub.asInterface(data.readStrongBinder());
                            setNickname(_arg05, _arg15, _arg25, _arg34, _arg43);
                            return true;
                        case 6:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg06 = data.readString();
                            String _arg16 = data.readString();
                            String _arg26 = data.readString();
                            IDeleteProfileCallback _arg35 = IDeleteProfileCallback.Stub.asInterface(data.readStrongBinder());
                            deleteProfile(_arg06, _arg16, _arg26, _arg35);
                            return true;
                        case 7:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg07 = data.readString();
                            String _arg17 = data.readString();
                            int _arg27 = data.readInt();
                            IResetMemoryCallback _arg36 = IResetMemoryCallback.Stub.asInterface(data.readStrongBinder());
                            resetMemory(_arg07, _arg17, _arg27, _arg36);
                            return true;
                        case 8:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg08 = data.readString();
                            String _arg18 = data.readString();
                            IGetDefaultSmdpAddressCallback _arg28 = IGetDefaultSmdpAddressCallback.Stub.asInterface(data.readStrongBinder());
                            getDefaultSmdpAddress(_arg08, _arg18, _arg28);
                            return true;
                        case 9:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg09 = data.readString();
                            String _arg19 = data.readString();
                            IGetSmdsAddressCallback _arg29 = IGetSmdsAddressCallback.Stub.asInterface(data.readStrongBinder());
                            getSmdsAddress(_arg09, _arg19, _arg29);
                            return true;
                        case 10:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg010 = data.readString();
                            String _arg110 = data.readString();
                            String _arg210 = data.readString();
                            ISetDefaultSmdpAddressCallback _arg37 = ISetDefaultSmdpAddressCallback.Stub.asInterface(data.readStrongBinder());
                            setDefaultSmdpAddress(_arg010, _arg110, _arg210, _arg37);
                            return true;
                        case 11:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg011 = data.readString();
                            String _arg111 = data.readString();
                            IGetRulesAuthTableCallback _arg211 = IGetRulesAuthTableCallback.Stub.asInterface(data.readStrongBinder());
                            getRulesAuthTable(_arg011, _arg111, _arg211);
                            return true;
                        case 12:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg012 = data.readString();
                            String _arg112 = data.readString();
                            IGetEuiccChallengeCallback _arg212 = IGetEuiccChallengeCallback.Stub.asInterface(data.readStrongBinder());
                            getEuiccChallenge(_arg012, _arg112, _arg212);
                            return true;
                        case 13:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg013 = data.readString();
                            String _arg113 = data.readString();
                            IGetEuiccInfo1Callback _arg213 = IGetEuiccInfo1Callback.Stub.asInterface(data.readStrongBinder());
                            getEuiccInfo1(_arg013, _arg113, _arg213);
                            return true;
                        case 14:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg014 = data.readString();
                            String _arg114 = data.readString();
                            IGetEuiccInfo2Callback _arg214 = IGetEuiccInfo2Callback.Stub.asInterface(data.readStrongBinder());
                            getEuiccInfo2(_arg014, _arg114, _arg214);
                            return true;
                        case 15:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg015 = data.readString();
                            String _arg115 = data.readString();
                            String _arg215 = data.readString();
                            byte[] _arg38 = data.createByteArray();
                            byte[] _arg44 = data.createByteArray();
                            byte[] _arg5 = data.createByteArray();
                            byte[] _arg6 = data.createByteArray();
                            IAuthenticateServerCallback _arg7 = IAuthenticateServerCallback.Stub.asInterface(data.readStrongBinder());
                            authenticateServer(_arg015, _arg115, _arg215, _arg38, _arg44, _arg5, _arg6, _arg7);
                            return true;
                        case 16:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg016 = data.readString();
                            String _arg116 = data.readString();
                            byte[] _arg216 = data.createByteArray();
                            byte[] _arg39 = data.createByteArray();
                            byte[] _arg45 = data.createByteArray();
                            byte[] _arg52 = data.createByteArray();
                            IPrepareDownloadCallback _arg62 = IPrepareDownloadCallback.Stub.asInterface(data.readStrongBinder());
                            prepareDownload(_arg016, _arg116, _arg216, _arg39, _arg45, _arg52, _arg62);
                            return true;
                        case 17:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg017 = data.readString();
                            String _arg117 = data.readString();
                            byte[] _arg217 = data.createByteArray();
                            ILoadBoundProfilePackageCallback _arg310 = ILoadBoundProfilePackageCallback.Stub.asInterface(data.readStrongBinder());
                            loadBoundProfilePackage(_arg017, _arg117, _arg217, _arg310);
                            return true;
                        case 18:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg018 = data.readString();
                            String _arg118 = data.readString();
                            byte[] _arg218 = data.createByteArray();
                            int _arg311 = data.readInt();
                            ICancelSessionCallback _arg46 = ICancelSessionCallback.Stub.asInterface(data.readStrongBinder());
                            cancelSession(_arg018, _arg118, _arg218, _arg311, _arg46);
                            return true;
                        case 19:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg019 = data.readString();
                            String _arg119 = data.readString();
                            int _arg219 = data.readInt();
                            IListNotificationsCallback _arg312 = IListNotificationsCallback.Stub.asInterface(data.readStrongBinder());
                            listNotifications(_arg019, _arg119, _arg219, _arg312);
                            return true;
                        case 20:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg020 = data.readString();
                            String _arg120 = data.readString();
                            int _arg220 = data.readInt();
                            IRetrieveNotificationListCallback _arg313 = IRetrieveNotificationListCallback.Stub.asInterface(data.readStrongBinder());
                            retrieveNotificationList(_arg020, _arg120, _arg220, _arg313);
                            return true;
                        case 21:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg021 = data.readString();
                            String _arg121 = data.readString();
                            int _arg221 = data.readInt();
                            IRetrieveNotificationCallback _arg314 = IRetrieveNotificationCallback.Stub.asInterface(data.readStrongBinder());
                            retrieveNotification(_arg021, _arg121, _arg221, _arg314);
                            return true;
                        case 22:
                            data.enforceInterface(IEuiccCardController.DESCRIPTOR);
                            String _arg022 = data.readString();
                            String _arg122 = data.readString();
                            int _arg222 = data.readInt();
                            IRemoveNotificationFromListCallback _arg315 = IRemoveNotificationFromListCallback.Stub.asInterface(data.readStrongBinder());
                            removeNotificationFromList(_arg022, _arg122, _arg222, _arg315);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IEuiccCardController {
            public static IEuiccCardController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEuiccCardController.DESCRIPTOR;
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getAllProfiles(String callingPackage, String cardId, IGetAllProfilesCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAllProfiles(callingPackage, cardId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getProfile(String callingPackage, String cardId, String iccid, IGetProfileCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeString(iccid);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getProfile(callingPackage, cardId, iccid, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void disableProfile(String callingPackage, String cardId, String iccid, boolean refresh, IDisableProfileCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeString(iccid);
                    _data.writeInt(refresh ? 1 : 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableProfile(callingPackage, cardId, iccid, refresh, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void switchToProfile(String callingPackage, String cardId, String iccid, boolean refresh, ISwitchToProfileCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeString(iccid);
                    _data.writeInt(refresh ? 1 : 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchToProfile(callingPackage, cardId, iccid, refresh, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void setNickname(String callingPackage, String cardId, String iccid, String nickname, ISetNicknameCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeString(iccid);
                    _data.writeString(nickname);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNickname(callingPackage, cardId, iccid, nickname, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void deleteProfile(String callingPackage, String cardId, String iccid, IDeleteProfileCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeString(iccid);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteProfile(callingPackage, cardId, iccid, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void resetMemory(String callingPackage, String cardId, int options, IResetMemoryCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeInt(options);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetMemory(callingPackage, cardId, options, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getDefaultSmdpAddress(String callingPackage, String cardId, IGetDefaultSmdpAddressCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDefaultSmdpAddress(callingPackage, cardId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getSmdsAddress(String callingPackage, String cardId, IGetSmdsAddressCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getSmdsAddress(callingPackage, cardId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void setDefaultSmdpAddress(String callingPackage, String cardId, String address, ISetDefaultSmdpAddressCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeString(address);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDefaultSmdpAddress(callingPackage, cardId, address, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getRulesAuthTable(String callingPackage, String cardId, IGetRulesAuthTableCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getRulesAuthTable(callingPackage, cardId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getEuiccChallenge(String callingPackage, String cardId, IGetEuiccChallengeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getEuiccChallenge(callingPackage, cardId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getEuiccInfo1(String callingPackage, String cardId, IGetEuiccInfo1Callback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getEuiccInfo1(callingPackage, cardId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void getEuiccInfo2(String callingPackage, String cardId, IGetEuiccInfo2Callback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getEuiccInfo2(callingPackage, cardId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void authenticateServer(String callingPackage, String cardId, String matchingId, byte[] serverSigned1, byte[] serverSignature1, byte[] euiccCiPkIdToBeUsed, byte[] serverCertificatein, IAuthenticateServerCallback callback) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    try {
                        _data.writeString(callingPackage);
                        try {
                            _data.writeString(cardId);
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
                try {
                    _data.writeString(matchingId);
                    try {
                        _data.writeByteArray(serverSigned1);
                        try {
                            _data.writeByteArray(serverSignature1);
                            _data.writeByteArray(euiccCiPkIdToBeUsed);
                            _data.writeByteArray(serverCertificatein);
                            _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                            boolean _status = this.mRemote.transact(15, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().authenticateServer(callingPackage, cardId, matchingId, serverSigned1, serverSignature1, euiccCiPkIdToBeUsed, serverCertificatein, callback);
                            _data.recycle();
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

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void prepareDownload(String callingPackage, String cardId, byte[] hashCc, byte[] smdpSigned2, byte[] smdpSignature2, byte[] smdpCertificate, IPrepareDownloadCallback callback) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeString(callingPackage);
                    try {
                        _data.writeString(cardId);
                        try {
                            _data.writeByteArray(hashCc);
                            try {
                                _data.writeByteArray(smdpSigned2);
                                try {
                                    _data.writeByteArray(smdpSignature2);
                                    try {
                                        _data.writeByteArray(smdpCertificate);
                                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                                        boolean _status = this.mRemote.transact(16, _data, null, 1);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _data.recycle();
                                            return;
                                        }
                                        Stub.getDefaultImpl().prepareDownload(callingPackage, cardId, hashCc, smdpSigned2, smdpSignature2, smdpCertificate, callback);
                                        _data.recycle();
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
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void loadBoundProfilePackage(String callingPackage, String cardId, byte[] boundProfilePackage, ILoadBoundProfilePackageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeByteArray(boundProfilePackage);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().loadBoundProfilePackage(callingPackage, cardId, boundProfilePackage, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void cancelSession(String callingPackage, String cardId, byte[] transactionId, int reason, ICancelSessionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeByteArray(transactionId);
                    _data.writeInt(reason);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelSession(callingPackage, cardId, transactionId, reason, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void listNotifications(String callingPackage, String cardId, int events, IListNotificationsCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeInt(events);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().listNotifications(callingPackage, cardId, events, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void retrieveNotificationList(String callingPackage, String cardId, int events, IRetrieveNotificationListCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeInt(events);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retrieveNotificationList(callingPackage, cardId, events, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void retrieveNotification(String callingPackage, String cardId, int seqNumber, IRetrieveNotificationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeInt(seqNumber);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retrieveNotification(callingPackage, cardId, seqNumber, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.euicc.IEuiccCardController
            public void removeNotificationFromList(String callingPackage, String cardId, int seqNumber, IRemoveNotificationFromListCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IEuiccCardController.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(cardId);
                    _data.writeInt(seqNumber);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeNotificationFromList(callingPackage, cardId, seqNumber, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEuiccCardController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IEuiccCardController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
