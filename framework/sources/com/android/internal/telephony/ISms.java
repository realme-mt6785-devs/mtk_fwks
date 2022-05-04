package com.android.internal.telephony;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes4.dex */
public interface ISms extends IInterface {
    int checkSmsShortCodeDestination(int i, String str, String str2, String str3, String str4) throws RemoteException;

    boolean copyMessageToIccEfForSubscriber(int i, String str, int i2, byte[] bArr, byte[] bArr2) throws RemoteException;

    String createAppSpecificSmsToken(int i, String str, PendingIntent pendingIntent) throws RemoteException;

    String createAppSpecificSmsTokenWithPackageInfo(int i, String str, String str2, PendingIntent pendingIntent) throws RemoteException;

    boolean disableCellBroadcastForSubscriber(int i, int i2, int i3) throws RemoteException;

    boolean disableCellBroadcastRangeForSubscriber(int i, int i2, int i3, int i4) throws RemoteException;

    boolean enableCellBroadcastForSubscriber(int i, int i2, int i3) throws RemoteException;

    boolean enableCellBroadcastRangeForSubscriber(int i, int i2, int i3, int i4) throws RemoteException;

    List<SmsRawData> getAllMessagesFromIccEfForSubscriber(int i, String str) throws RemoteException;

    Bundle getCarrierConfigValuesForSubscriber(int i) throws RemoteException;

    String getImsSmsFormatForSubscriber(int i) throws RemoteException;

    int getPreferredSmsSubscription() throws RemoteException;

    int getPremiumSmsPermission(String str) throws RemoteException;

    int getPremiumSmsPermissionForSubscriber(int i, String str) throws RemoteException;

    int getSmsCapacityOnIccForSubscriber(int i) throws RemoteException;

    String getSmscAddressFromIccEfForSubscriber(int i, String str) throws RemoteException;

    void injectSmsPduForSubscriber(int i, byte[] bArr, String str, PendingIntent pendingIntent) throws RemoteException;

    boolean isImsSmsSupportedForSubscriber(int i) throws RemoteException;

    boolean isSMSPromptEnabled() throws RemoteException;

    boolean isSmsSimPickActivityNeeded(int i) throws RemoteException;

    boolean resetAllCellBroadcastRanges(int i) throws RemoteException;

    void sendDataForSubscriber(int i, String str, String str2, String str3, String str4, int i2, byte[] bArr, PendingIntent pendingIntent, PendingIntent pendingIntent2) throws RemoteException;

    void sendMultipartTextForSubscriber(int i, String str, String str2, String str3, String str4, List<String> list, List<PendingIntent> list2, List<PendingIntent> list3, boolean z, long j) throws RemoteException;

    void sendMultipartTextForSubscriberWithOptions(int i, String str, String str2, String str3, String str4, List<String> list, List<PendingIntent> list2, List<PendingIntent> list3, boolean z, int i2, boolean z2, int i3) throws RemoteException;

    void sendStoredMultipartText(int i, String str, String str2, Uri uri, String str3, List<PendingIntent> list, List<PendingIntent> list2) throws RemoteException;

    void sendStoredText(int i, String str, String str2, Uri uri, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2) throws RemoteException;

    void sendTextForSubscriber(int i, String str, String str2, String str3, String str4, String str5, PendingIntent pendingIntent, PendingIntent pendingIntent2, boolean z, long j) throws RemoteException;

    void sendTextForSubscriberWithOptions(int i, String str, String str2, String str3, String str4, String str5, PendingIntent pendingIntent, PendingIntent pendingIntent2, boolean z, int i2, boolean z2, int i3) throws RemoteException;

    void setPremiumSmsPermission(String str, int i) throws RemoteException;

    void setPremiumSmsPermissionForSubscriber(int i, String str, int i2) throws RemoteException;

    boolean setSmscAddressOnIccEfForSubscriber(String str, int i, String str2) throws RemoteException;

    boolean updateMessageOnIccEfForSubscriber(int i, String str, int i2, int i3, byte[] bArr) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ISms {
        @Override // com.android.internal.telephony.ISms
        public List<SmsRawData> getAllMessagesFromIccEfForSubscriber(int subId, String callingPkg) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean updateMessageOnIccEfForSubscriber(int subId, String callingPkg, int messageIndex, int newStatus, byte[] pdu) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean copyMessageToIccEfForSubscriber(int subId, String callingPkg, int status, byte[] pdu, byte[] smsc) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public void sendDataForSubscriber(int subId, String callingPkg, String callingattributionTag, String destAddr, String scAddr, int destPort, byte[] data, PendingIntent sentIntent, PendingIntent deliveryIntent) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public void sendTextForSubscriber(int subId, String callingPkg, String callingAttributionTag, String destAddr, String scAddr, String text, PendingIntent sentIntent, PendingIntent deliveryIntent, boolean persistMessageForNonDefaultSmsApp, long messageId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public void sendTextForSubscriberWithOptions(int subId, String callingPkg, String callingAttributionTag, String destAddr, String scAddr, String text, PendingIntent sentIntent, PendingIntent deliveryIntent, boolean persistMessageForNonDefaultSmsApp, int priority, boolean expectMore, int validityPeriod) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public void injectSmsPduForSubscriber(int subId, byte[] pdu, String format, PendingIntent receivedIntent) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public void sendMultipartTextForSubscriber(int subId, String callingPkg, String callingAttributionTag, String destinationAddress, String scAddress, List<String> parts, List<PendingIntent> sentIntents, List<PendingIntent> deliveryIntents, boolean persistMessageForNonDefaultSmsApp, long messageId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public void sendMultipartTextForSubscriberWithOptions(int subId, String callingPkg, String callingAttributionTag, String destinationAddress, String scAddress, List<String> parts, List<PendingIntent> sentIntents, List<PendingIntent> deliveryIntents, boolean persistMessageForNonDefaultSmsApp, int priority, boolean expectMore, int validityPeriod) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public boolean enableCellBroadcastForSubscriber(int subId, int messageIdentifier, int ranType) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean disableCellBroadcastForSubscriber(int subId, int messageIdentifier, int ranType) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean enableCellBroadcastRangeForSubscriber(int subId, int startMessageId, int endMessageId, int ranType) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean disableCellBroadcastRangeForSubscriber(int subId, int startMessageId, int endMessageId, int ranType) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public int getPremiumSmsPermission(String packageName) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISms
        public int getPremiumSmsPermissionForSubscriber(int subId, String packageName) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISms
        public void setPremiumSmsPermission(String packageName, int permission) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public void setPremiumSmsPermissionForSubscriber(int subId, String packageName, int permission) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public boolean isImsSmsSupportedForSubscriber(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean isSmsSimPickActivityNeeded(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public int getPreferredSmsSubscription() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISms
        public String getImsSmsFormatForSubscriber(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean isSMSPromptEnabled() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public void sendStoredText(int subId, String callingPkg, String callingAttributionTag, Uri messageUri, String scAddress, PendingIntent sentIntent, PendingIntent deliveryIntent) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public void sendStoredMultipartText(int subId, String callingPkg, String callingAttributeTag, Uri messageUri, String scAddress, List<PendingIntent> sentIntents, List<PendingIntent> deliveryIntents) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ISms
        public Bundle getCarrierConfigValuesForSubscriber(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISms
        public String createAppSpecificSmsToken(int subId, String callingPkg, PendingIntent intent) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISms
        public String createAppSpecificSmsTokenWithPackageInfo(int subId, String callingPkg, String prefixes, PendingIntent intent) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISms
        public int checkSmsShortCodeDestination(int subId, String callingApk, String callingFeatureId, String destAddress, String countryIso) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISms
        public String getSmscAddressFromIccEfForSubscriber(int subId, String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean setSmscAddressOnIccEfForSubscriber(String smsc, int subId, String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ISms
        public int getSmsCapacityOnIccForSubscriber(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ISms
        public boolean resetAllCellBroadcastRanges(int subId) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ISms {
        public static final String DESCRIPTOR = "com.android.internal.telephony.ISms";
        static final int TRANSACTION_checkSmsShortCodeDestination = 28;
        static final int TRANSACTION_copyMessageToIccEfForSubscriber = 3;
        static final int TRANSACTION_createAppSpecificSmsToken = 26;
        static final int TRANSACTION_createAppSpecificSmsTokenWithPackageInfo = 27;
        static final int TRANSACTION_disableCellBroadcastForSubscriber = 11;
        static final int TRANSACTION_disableCellBroadcastRangeForSubscriber = 13;
        static final int TRANSACTION_enableCellBroadcastForSubscriber = 10;
        static final int TRANSACTION_enableCellBroadcastRangeForSubscriber = 12;
        static final int TRANSACTION_getAllMessagesFromIccEfForSubscriber = 1;
        static final int TRANSACTION_getCarrierConfigValuesForSubscriber = 25;
        static final int TRANSACTION_getImsSmsFormatForSubscriber = 21;
        static final int TRANSACTION_getPreferredSmsSubscription = 20;
        static final int TRANSACTION_getPremiumSmsPermission = 14;
        static final int TRANSACTION_getPremiumSmsPermissionForSubscriber = 15;
        static final int TRANSACTION_getSmsCapacityOnIccForSubscriber = 31;
        static final int TRANSACTION_getSmscAddressFromIccEfForSubscriber = 29;
        static final int TRANSACTION_injectSmsPduForSubscriber = 7;
        static final int TRANSACTION_isImsSmsSupportedForSubscriber = 18;
        static final int TRANSACTION_isSMSPromptEnabled = 22;
        static final int TRANSACTION_isSmsSimPickActivityNeeded = 19;
        static final int TRANSACTION_resetAllCellBroadcastRanges = 32;
        static final int TRANSACTION_sendDataForSubscriber = 4;
        static final int TRANSACTION_sendMultipartTextForSubscriber = 8;
        static final int TRANSACTION_sendMultipartTextForSubscriberWithOptions = 9;
        static final int TRANSACTION_sendStoredMultipartText = 24;
        static final int TRANSACTION_sendStoredText = 23;
        static final int TRANSACTION_sendTextForSubscriber = 5;
        static final int TRANSACTION_sendTextForSubscriberWithOptions = 6;
        static final int TRANSACTION_setPremiumSmsPermission = 16;
        static final int TRANSACTION_setPremiumSmsPermissionForSubscriber = 17;
        static final int TRANSACTION_setSmscAddressOnIccEfForSubscriber = 30;
        static final int TRANSACTION_updateMessageOnIccEfForSubscriber = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISms asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISms)) {
                return new Proxy(obj);
            }
            return (ISms) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getAllMessagesFromIccEfForSubscriber";
                case 2:
                    return "updateMessageOnIccEfForSubscriber";
                case 3:
                    return "copyMessageToIccEfForSubscriber";
                case 4:
                    return "sendDataForSubscriber";
                case 5:
                    return "sendTextForSubscriber";
                case 6:
                    return "sendTextForSubscriberWithOptions";
                case 7:
                    return "injectSmsPduForSubscriber";
                case 8:
                    return "sendMultipartTextForSubscriber";
                case 9:
                    return "sendMultipartTextForSubscriberWithOptions";
                case 10:
                    return "enableCellBroadcastForSubscriber";
                case 11:
                    return "disableCellBroadcastForSubscriber";
                case 12:
                    return "enableCellBroadcastRangeForSubscriber";
                case 13:
                    return "disableCellBroadcastRangeForSubscriber";
                case 14:
                    return "getPremiumSmsPermission";
                case 15:
                    return "getPremiumSmsPermissionForSubscriber";
                case 16:
                    return "setPremiumSmsPermission";
                case 17:
                    return "setPremiumSmsPermissionForSubscriber";
                case 18:
                    return "isImsSmsSupportedForSubscriber";
                case 19:
                    return "isSmsSimPickActivityNeeded";
                case 20:
                    return "getPreferredSmsSubscription";
                case 21:
                    return "getImsSmsFormatForSubscriber";
                case 22:
                    return "isSMSPromptEnabled";
                case 23:
                    return "sendStoredText";
                case 24:
                    return "sendStoredMultipartText";
                case 25:
                    return "getCarrierConfigValuesForSubscriber";
                case 26:
                    return "createAppSpecificSmsToken";
                case 27:
                    return "createAppSpecificSmsTokenWithPackageInfo";
                case 28:
                    return "checkSmsShortCodeDestination";
                case 29:
                    return "getSmscAddressFromIccEfForSubscriber";
                case 30:
                    return "setSmscAddressOnIccEfForSubscriber";
                case 31:
                    return "getSmsCapacityOnIccForSubscriber";
                case 32:
                    return "resetAllCellBroadcastRanges";
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
            PendingIntent _arg7;
            PendingIntent _arg8;
            PendingIntent _arg6;
            PendingIntent _arg72;
            boolean _arg82;
            PendingIntent _arg62;
            PendingIntent _arg73;
            boolean _arg83;
            boolean _arg10;
            PendingIntent _arg3;
            boolean _arg84;
            boolean _arg85;
            boolean _arg102;
            Uri _arg32;
            PendingIntent _arg5;
            PendingIntent _arg63;
            Uri _arg33;
            PendingIntent _arg2;
            PendingIntent _arg34;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0 = data.readInt();
                            String _arg1 = data.readString();
                            List<SmsRawData> _result = getAllMessagesFromIccEfForSubscriber(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            String _arg12 = data.readString();
                            int _arg22 = data.readInt();
                            int _arg35 = data.readInt();
                            byte[] _arg4 = data.createByteArray();
                            boolean updateMessageOnIccEfForSubscriber = updateMessageOnIccEfForSubscriber(_arg02, _arg12, _arg22, _arg35, _arg4);
                            reply.writeNoException();
                            reply.writeInt(updateMessageOnIccEfForSubscriber ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg03 = data.readInt();
                            String _arg13 = data.readString();
                            int _arg23 = data.readInt();
                            byte[] _arg36 = data.createByteArray();
                            byte[] _arg42 = data.createByteArray();
                            boolean copyMessageToIccEfForSubscriber = copyMessageToIccEfForSubscriber(_arg03, _arg13, _arg23, _arg36, _arg42);
                            reply.writeNoException();
                            reply.writeInt(copyMessageToIccEfForSubscriber ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg04 = data.readInt();
                            String _arg14 = data.readString();
                            String _arg24 = data.readString();
                            String _arg37 = data.readString();
                            String _arg43 = data.readString();
                            int _arg52 = data.readInt();
                            byte[] _arg64 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg7 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg7 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg8 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg8 = null;
                            }
                            sendDataForSubscriber(_arg04, _arg14, _arg24, _arg37, _arg43, _arg52, _arg64, _arg7, _arg8);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            String _arg15 = data.readString();
                            String _arg25 = data.readString();
                            String _arg38 = data.readString();
                            String _arg44 = data.readString();
                            String _arg53 = data.readString();
                            if (data.readInt() != 0) {
                                _arg6 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg72 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg72 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg82 = true;
                            } else {
                                _arg82 = false;
                            }
                            long _arg9 = data.readLong();
                            sendTextForSubscriber(_arg05, _arg15, _arg25, _arg38, _arg44, _arg53, _arg6, _arg72, _arg82, _arg9);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg06 = data.readInt();
                            String _arg16 = data.readString();
                            String _arg26 = data.readString();
                            String _arg39 = data.readString();
                            String _arg45 = data.readString();
                            String _arg54 = data.readString();
                            if (data.readInt() != 0) {
                                _arg62 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg62 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg73 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg73 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg83 = true;
                            } else {
                                _arg83 = false;
                            }
                            int _arg92 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg10 = true;
                            } else {
                                _arg10 = false;
                            }
                            int _arg11 = data.readInt();
                            sendTextForSubscriberWithOptions(_arg06, _arg16, _arg26, _arg39, _arg45, _arg54, _arg62, _arg73, _arg83, _arg92, _arg10, _arg11);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg07 = data.readInt();
                            byte[] _arg17 = data.createByteArray();
                            String _arg27 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            injectSmsPduForSubscriber(_arg07, _arg17, _arg27, _arg3);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg08 = data.readInt();
                            String _arg18 = data.readString();
                            String _arg28 = data.readString();
                            String _arg310 = data.readString();
                            String _arg46 = data.readString();
                            List<String> _arg55 = data.createStringArrayList();
                            List<PendingIntent> _arg65 = data.createTypedArrayList(PendingIntent.CREATOR);
                            List<PendingIntent> _arg74 = data.createTypedArrayList(PendingIntent.CREATOR);
                            if (data.readInt() != 0) {
                                _arg84 = true;
                            } else {
                                _arg84 = false;
                            }
                            long _arg93 = data.readLong();
                            sendMultipartTextForSubscriber(_arg08, _arg18, _arg28, _arg310, _arg46, _arg55, _arg65, _arg74, _arg84, _arg93);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            String _arg19 = data.readString();
                            String _arg29 = data.readString();
                            String _arg311 = data.readString();
                            String _arg47 = data.readString();
                            List<String> _arg56 = data.createStringArrayList();
                            List<PendingIntent> _arg66 = data.createTypedArrayList(PendingIntent.CREATOR);
                            List<PendingIntent> _arg75 = data.createTypedArrayList(PendingIntent.CREATOR);
                            if (data.readInt() != 0) {
                                _arg85 = true;
                            } else {
                                _arg85 = false;
                            }
                            int _arg94 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg102 = true;
                            } else {
                                _arg102 = false;
                            }
                            int _arg112 = data.readInt();
                            sendMultipartTextForSubscriberWithOptions(_arg09, _arg19, _arg29, _arg311, _arg47, _arg56, _arg66, _arg75, _arg85, _arg94, _arg102, _arg112);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            int _arg110 = data.readInt();
                            int _arg210 = data.readInt();
                            boolean enableCellBroadcastForSubscriber = enableCellBroadcastForSubscriber(_arg010, _arg110, _arg210);
                            reply.writeNoException();
                            reply.writeInt(enableCellBroadcastForSubscriber ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _arg111 = data.readInt();
                            int _arg211 = data.readInt();
                            boolean disableCellBroadcastForSubscriber = disableCellBroadcastForSubscriber(_arg011, _arg111, _arg211);
                            reply.writeNoException();
                            reply.writeInt(disableCellBroadcastForSubscriber ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg113 = data.readInt();
                            int _arg212 = data.readInt();
                            int _arg312 = data.readInt();
                            boolean enableCellBroadcastRangeForSubscriber = enableCellBroadcastRangeForSubscriber(_arg012, _arg113, _arg212, _arg312);
                            reply.writeNoException();
                            reply.writeInt(enableCellBroadcastRangeForSubscriber ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            int _arg114 = data.readInt();
                            int _arg213 = data.readInt();
                            int _arg313 = data.readInt();
                            boolean disableCellBroadcastRangeForSubscriber = disableCellBroadcastRangeForSubscriber(_arg013, _arg114, _arg213, _arg313);
                            reply.writeNoException();
                            reply.writeInt(disableCellBroadcastRangeForSubscriber ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            int _result2 = getPremiumSmsPermission(_arg014);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            String _arg115 = data.readString();
                            int _result3 = getPremiumSmsPermissionForSubscriber(_arg015, _arg115);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg016 = data.readString();
                            int _arg116 = data.readInt();
                            setPremiumSmsPermission(_arg016, _arg116);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            String _arg117 = data.readString();
                            int _arg214 = data.readInt();
                            setPremiumSmsPermissionForSubscriber(_arg017, _arg117, _arg214);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            boolean isImsSmsSupportedForSubscriber = isImsSmsSupportedForSubscriber(_arg018);
                            reply.writeNoException();
                            reply.writeInt(isImsSmsSupportedForSubscriber ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            boolean isSmsSimPickActivityNeeded = isSmsSimPickActivityNeeded(_arg019);
                            reply.writeNoException();
                            reply.writeInt(isSmsSimPickActivityNeeded ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            int _result4 = getPreferredSmsSubscription();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            String _result5 = getImsSmsFormatForSubscriber(_arg020);
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isSMSPromptEnabled = isSMSPromptEnabled();
                            reply.writeNoException();
                            reply.writeInt(isSMSPromptEnabled ? 1 : 0);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            String _arg118 = data.readString();
                            String _arg215 = data.readString();
                            if (data.readInt() != 0) {
                                _arg32 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            String _arg48 = data.readString();
                            if (data.readInt() != 0) {
                                _arg5 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg63 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg63 = null;
                            }
                            sendStoredText(_arg021, _arg118, _arg215, _arg32, _arg48, _arg5, _arg63);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg022 = data.readInt();
                            String _arg119 = data.readString();
                            String _arg216 = data.readString();
                            if (data.readInt() != 0) {
                                _arg33 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            String _arg49 = data.readString();
                            List<PendingIntent> _arg57 = data.createTypedArrayList(PendingIntent.CREATOR);
                            List<PendingIntent> _arg67 = data.createTypedArrayList(PendingIntent.CREATOR);
                            sendStoredMultipartText(_arg022, _arg119, _arg216, _arg33, _arg49, _arg57, _arg67);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg023 = data.readInt();
                            Bundle _result6 = getCarrierConfigValuesForSubscriber(_arg023);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg024 = data.readInt();
                            String _arg120 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            String _result7 = createAppSpecificSmsToken(_arg024, _arg120, _arg2);
                            reply.writeNoException();
                            reply.writeString(_result7);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            String _arg121 = data.readString();
                            String _arg217 = data.readString();
                            if (data.readInt() != 0) {
                                _arg34 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg34 = null;
                            }
                            String _result8 = createAppSpecificSmsTokenWithPackageInfo(_arg025, _arg121, _arg217, _arg34);
                            reply.writeNoException();
                            reply.writeString(_result8);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            String _arg122 = data.readString();
                            String _arg218 = data.readString();
                            String _arg314 = data.readString();
                            String _arg410 = data.readString();
                            int _result9 = checkSmsShortCodeDestination(_arg026, _arg122, _arg218, _arg314, _arg410);
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            String _arg123 = data.readString();
                            String _result10 = getSmscAddressFromIccEfForSubscriber(_arg027, _arg123);
                            reply.writeNoException();
                            reply.writeString(_result10);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            int _arg124 = data.readInt();
                            String _arg219 = data.readString();
                            boolean smscAddressOnIccEfForSubscriber = setSmscAddressOnIccEfForSubscriber(_arg028, _arg124, _arg219);
                            reply.writeNoException();
                            reply.writeInt(smscAddressOnIccEfForSubscriber ? 1 : 0);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            int _result11 = getSmsCapacityOnIccForSubscriber(_arg029);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg030 = data.readInt();
                            boolean resetAllCellBroadcastRanges = resetAllCellBroadcastRanges(_arg030);
                            reply.writeNoException();
                            reply.writeInt(resetAllCellBroadcastRanges ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ISms {
            public static ISms sDefaultImpl;
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

            @Override // com.android.internal.telephony.ISms
            public List<SmsRawData> getAllMessagesFromIccEfForSubscriber(int subId, String callingPkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPkg);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllMessagesFromIccEfForSubscriber(subId, callingPkg);
                    }
                    _reply.readException();
                    List<SmsRawData> _result = _reply.createTypedArrayList(SmsRawData.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public boolean updateMessageOnIccEfForSubscriber(int subId, String callingPkg, int messageIndex, int newStatus, byte[] pdu) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(callingPkg);
                    try {
                        _data.writeInt(messageIndex);
                        try {
                            _data.writeInt(newStatus);
                            try {
                                _data.writeByteArray(pdu);
                                try {
                                    boolean _result = false;
                                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = true;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    boolean updateMessageOnIccEfForSubscriber = Stub.getDefaultImpl().updateMessageOnIccEfForSubscriber(subId, callingPkg, messageIndex, newStatus, pdu);
                                    _reply.recycle();
                                    _data.recycle();
                                    return updateMessageOnIccEfForSubscriber;
                                } catch (Throwable th4) {
                                    th = th4;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ISms
            public boolean copyMessageToIccEfForSubscriber(int subId, String callingPkg, int status, byte[] pdu, byte[] smsc) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(callingPkg);
                    try {
                        _data.writeInt(status);
                        try {
                            _data.writeByteArray(pdu);
                            try {
                                _data.writeByteArray(smsc);
                                try {
                                    boolean _result = false;
                                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = true;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    boolean copyMessageToIccEfForSubscriber = Stub.getDefaultImpl().copyMessageToIccEfForSubscriber(subId, callingPkg, status, pdu, smsc);
                                    _reply.recycle();
                                    _data.recycle();
                                    return copyMessageToIccEfForSubscriber;
                                } catch (Throwable th4) {
                                    th = th4;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ISms
            public void sendDataForSubscriber(int subId, String callingPkg, String callingattributionTag, String destAddr, String scAddr, int destPort, byte[] data, PendingIntent sentIntent, PendingIntent deliveryIntent) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        _data.writeString(callingPkg);
                        _data.writeString(callingattributionTag);
                        _data.writeString(destAddr);
                        _data.writeString(scAddr);
                        _data.writeInt(destPort);
                        _data.writeByteArray(data);
                        if (sentIntent != null) {
                            _data.writeInt(1);
                            sentIntent.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (deliveryIntent != null) {
                            _data.writeInt(1);
                            deliveryIntent.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().sendDataForSubscriber(subId, callingPkg, callingattributionTag, destAddr, scAddr, destPort, data, sentIntent, deliveryIntent);
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

            @Override // com.android.internal.telephony.ISms
            public void sendTextForSubscriber(int subId, String callingPkg, String callingAttributionTag, String destAddr, String scAddr, String text, PendingIntent sentIntent, PendingIntent deliveryIntent, boolean persistMessageForNonDefaultSmsApp, long messageId) throws RemoteException {
                Parcel _reply;
                Throwable th;
                boolean _status;
                Parcel _reply2;
                Parcel _data = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPkg);
                    _data.writeString(callingAttributionTag);
                    _data.writeString(destAddr);
                    _data.writeString(scAddr);
                    _data.writeString(text);
                    int i = 1;
                    if (sentIntent != null) {
                        try {
                            _data.writeInt(1);
                            sentIntent.writeToParcel(_data, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data.writeInt(0);
                    }
                    if (deliveryIntent != null) {
                        _data.writeInt(1);
                        deliveryIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!persistMessageForNonDefaultSmsApp) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeLong(messageId);
                    _status = this.mRemote.transact(5, _data, _reply3, 0);
                } catch (Throwable th3) {
                    th = th3;
                    _reply = _reply3;
                }
                try {
                    if (_status) {
                        _reply2 = _reply3;
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendTextForSubscriber(subId, callingPkg, callingAttributionTag, destAddr, scAddr, text, sentIntent, deliveryIntent, persistMessageForNonDefaultSmsApp, messageId);
                        _reply3.recycle();
                        _data.recycle();
                        return;
                    } else {
                        _reply2 = _reply3;
                    }
                    _reply2.readException();
                    _reply2.recycle();
                    _data.recycle();
                } catch (Throwable th4) {
                    th = th4;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ISms
            public void sendTextForSubscriberWithOptions(int subId, String callingPkg, String callingAttributionTag, String destAddr, String scAddr, String text, PendingIntent sentIntent, PendingIntent deliveryIntent, boolean persistMessageForNonDefaultSmsApp, int priority, boolean expectMore, int validityPeriod) throws RemoteException {
                Parcel _data;
                Parcel _reply;
                Throwable th;
                Parcel _data2;
                Parcel _reply2;
                Parcel _data3 = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data3.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data3.writeInt(subId);
                    _data3.writeString(callingPkg);
                    _data3.writeString(callingAttributionTag);
                    _data3.writeString(destAddr);
                    _data3.writeString(scAddr);
                    _data3.writeString(text);
                    int i = 1;
                    if (sentIntent != null) {
                        try {
                            _data3.writeInt(1);
                            sentIntent.writeToParcel(_data3, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _data = _data3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data3.writeInt(0);
                    }
                    if (deliveryIntent != null) {
                        _data3.writeInt(1);
                        deliveryIntent.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeInt(persistMessageForNonDefaultSmsApp ? 1 : 0);
                    _data3.writeInt(priority);
                    if (!expectMore) {
                        i = 0;
                    }
                    _data3.writeInt(i);
                    _data3.writeInt(validityPeriod);
                    boolean _status = this.mRemote.transact(6, _data3, _reply3, 0);
                    try {
                        if (_status) {
                            _reply2 = _reply3;
                            _data2 = _data3;
                        } else if (Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().sendTextForSubscriberWithOptions(subId, callingPkg, callingAttributionTag, destAddr, scAddr, text, sentIntent, deliveryIntent, persistMessageForNonDefaultSmsApp, priority, expectMore, validityPeriod);
                            _reply3.recycle();
                            _data3.recycle();
                            return;
                        } else {
                            _reply2 = _reply3;
                            _data2 = _data3;
                        }
                        _reply2.readException();
                        _reply2.recycle();
                        _data2.recycle();
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    _reply = _reply3;
                    _data = _data3;
                }
            }

            @Override // com.android.internal.telephony.ISms
            public void injectSmsPduForSubscriber(int subId, byte[] pdu, String format, PendingIntent receivedIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeByteArray(pdu);
                    _data.writeString(format);
                    if (receivedIntent != null) {
                        _data.writeInt(1);
                        receivedIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().injectSmsPduForSubscriber(subId, pdu, format, receivedIntent);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public void sendMultipartTextForSubscriber(int subId, String callingPkg, String callingAttributionTag, String destinationAddress, String scAddress, List<String> parts, List<PendingIntent> sentIntents, List<PendingIntent> deliveryIntents, boolean persistMessageForNonDefaultSmsApp, long messageId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        _data.writeString(callingPkg);
                        _data.writeString(callingAttributionTag);
                        _data.writeString(destinationAddress);
                        _data.writeString(scAddress);
                        _data.writeStringList(parts);
                        _data.writeTypedList(sentIntents);
                        _data.writeTypedList(deliveryIntents);
                        _data.writeInt(persistMessageForNonDefaultSmsApp ? 1 : 0);
                        _data.writeLong(messageId);
                        boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().sendMultipartTextForSubscriber(subId, callingPkg, callingAttributionTag, destinationAddress, scAddress, parts, sentIntents, deliveryIntents, persistMessageForNonDefaultSmsApp, messageId);
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

            @Override // com.android.internal.telephony.ISms
            public void sendMultipartTextForSubscriberWithOptions(int subId, String callingPkg, String callingAttributionTag, String destinationAddress, String scAddress, List<String> parts, List<PendingIntent> sentIntents, List<PendingIntent> deliveryIntents, boolean persistMessageForNonDefaultSmsApp, int priority, boolean expectMore, int validityPeriod) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPkg);
                    _data.writeString(callingAttributionTag);
                    _data.writeString(destinationAddress);
                    _data.writeString(scAddress);
                    _data.writeStringList(parts);
                    _data.writeTypedList(sentIntents);
                    _data.writeTypedList(deliveryIntents);
                    int i = 1;
                    _data.writeInt(persistMessageForNonDefaultSmsApp ? 1 : 0);
                    _data.writeInt(priority);
                    if (!expectMore) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeInt(validityPeriod);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().sendMultipartTextForSubscriberWithOptions(subId, callingPkg, callingAttributionTag, destinationAddress, scAddress, parts, sentIntents, deliveryIntents, persistMessageForNonDefaultSmsApp, priority, expectMore, validityPeriod);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public boolean enableCellBroadcastForSubscriber(int subId, int messageIdentifier, int ranType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(messageIdentifier);
                    _data.writeInt(ranType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableCellBroadcastForSubscriber(subId, messageIdentifier, ranType);
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

            @Override // com.android.internal.telephony.ISms
            public boolean disableCellBroadcastForSubscriber(int subId, int messageIdentifier, int ranType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(messageIdentifier);
                    _data.writeInt(ranType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableCellBroadcastForSubscriber(subId, messageIdentifier, ranType);
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

            @Override // com.android.internal.telephony.ISms
            public boolean enableCellBroadcastRangeForSubscriber(int subId, int startMessageId, int endMessageId, int ranType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(startMessageId);
                    _data.writeInt(endMessageId);
                    _data.writeInt(ranType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableCellBroadcastRangeForSubscriber(subId, startMessageId, endMessageId, ranType);
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

            @Override // com.android.internal.telephony.ISms
            public boolean disableCellBroadcastRangeForSubscriber(int subId, int startMessageId, int endMessageId, int ranType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(startMessageId);
                    _data.writeInt(endMessageId);
                    _data.writeInt(ranType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableCellBroadcastRangeForSubscriber(subId, startMessageId, endMessageId, ranType);
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

            @Override // com.android.internal.telephony.ISms
            public int getPremiumSmsPermission(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPremiumSmsPermission(packageName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public int getPremiumSmsPermissionForSubscriber(int subId, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPremiumSmsPermissionForSubscriber(subId, packageName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public void setPremiumSmsPermission(String packageName, int permission) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(permission);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPremiumSmsPermission(packageName, permission);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public void setPremiumSmsPermissionForSubscriber(int subId, String packageName, int permission) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(packageName);
                    _data.writeInt(permission);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPremiumSmsPermissionForSubscriber(subId, packageName, permission);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public boolean isImsSmsSupportedForSubscriber(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isImsSmsSupportedForSubscriber(subId);
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

            @Override // com.android.internal.telephony.ISms
            public boolean isSmsSimPickActivityNeeded(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSmsSimPickActivityNeeded(subId);
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

            @Override // com.android.internal.telephony.ISms
            public int getPreferredSmsSubscription() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredSmsSubscription();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public String getImsSmsFormatForSubscriber(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsSmsFormatForSubscriber(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public boolean isSMSPromptEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSMSPromptEnabled();
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

            @Override // com.android.internal.telephony.ISms
            public void sendStoredText(int subId, String callingPkg, String callingAttributionTag, Uri messageUri, String scAddress, PendingIntent sentIntent, PendingIntent deliveryIntent) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        try {
                            _data.writeString(callingPkg);
                            _data.writeString(callingAttributionTag);
                            if (messageUri != null) {
                                _data.writeInt(1);
                                messageUri.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            _data.writeString(scAddress);
                            if (sentIntent != null) {
                                _data.writeInt(1);
                                sentIntent.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            if (deliveryIntent != null) {
                                _data.writeInt(1);
                                deliveryIntent.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().sendStoredText(subId, callingPkg, callingAttributionTag, messageUri, scAddress, sentIntent, deliveryIntent);
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // com.android.internal.telephony.ISms
            public void sendStoredMultipartText(int subId, String callingPkg, String callingAttributeTag, Uri messageUri, String scAddress, List<PendingIntent> sentIntents, List<PendingIntent> deliveryIntents) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        try {
                            _data.writeString(callingPkg);
                            try {
                                _data.writeString(callingAttributeTag);
                                if (messageUri != null) {
                                    _data.writeInt(1);
                                    messageUri.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                try {
                                    _data.writeString(scAddress);
                                    _data.writeTypedList(sentIntents);
                                    _data.writeTypedList(deliveryIntents);
                                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().sendStoredMultipartText(subId, callingPkg, callingAttributeTag, messageUri, scAddress, sentIntents, deliveryIntents);
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

            @Override // com.android.internal.telephony.ISms
            public Bundle getCarrierConfigValuesForSubscriber(int subId) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierConfigValuesForSubscriber(subId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public String createAppSpecificSmsToken(int subId, String callingPkg, PendingIntent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPkg);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createAppSpecificSmsToken(subId, callingPkg, intent);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public String createAppSpecificSmsTokenWithPackageInfo(int subId, String callingPkg, String prefixes, PendingIntent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPkg);
                    _data.writeString(prefixes);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createAppSpecificSmsTokenWithPackageInfo(subId, callingPkg, prefixes, intent);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public int checkSmsShortCodeDestination(int subId, String callingApk, String callingFeatureId, String destAddress, String countryIso) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingApk);
                    _data.writeString(callingFeatureId);
                    _data.writeString(destAddress);
                    _data.writeString(countryIso);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkSmsShortCodeDestination(subId, callingApk, callingFeatureId, destAddress, countryIso);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public String getSmscAddressFromIccEfForSubscriber(int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmscAddressFromIccEfForSubscriber(subId, callingPackage);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public boolean setSmscAddressOnIccEfForSubscriber(String smsc, int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(smsc);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSmscAddressOnIccEfForSubscriber(smsc, subId, callingPackage);
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

            @Override // com.android.internal.telephony.ISms
            public int getSmsCapacityOnIccForSubscriber(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmsCapacityOnIccForSubscriber(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ISms
            public boolean resetAllCellBroadcastRanges(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetAllCellBroadcastRanges(subId);
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

        public static boolean setDefaultImpl(ISms impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISms getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
