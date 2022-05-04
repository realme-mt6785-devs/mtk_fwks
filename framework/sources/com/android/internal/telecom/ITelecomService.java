package com.android.internal.telecom;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomAnalytics;
import java.util.List;
/* loaded from: classes4.dex */
public interface ITelecomService extends IInterface {
    void acceptHandover(Uri uri, int i, PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    void acceptRingingCall(String str) throws RemoteException;

    void acceptRingingCallWithVideoState(String str, int i) throws RemoteException;

    void addNewIncomingCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException;

    void addNewIncomingConference(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException;

    void addNewOutgoingCall(Intent intent) throws RemoteException;

    void addNewUnknownCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) throws RemoteException;

    void addOrRemoveTestCallCompanionApp(String str, boolean z) throws RemoteException;

    void cancelMissedCallsNotification(String str) throws RemoteException;

    void cleanupStuckCalls() throws RemoteException;

    void clearAccounts(String str) throws RemoteException;

    Intent createLaunchEmergencyDialerIntent(String str) throws RemoteException;

    Intent createManageBlockedNumbersIntent() throws RemoteException;

    TelecomAnalytics dumpCallAnalytics() throws RemoteException;

    boolean enablePhoneAccount(PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException;

    boolean endCall(String str) throws RemoteException;

    Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str) throws RemoteException;

    List<PhoneAccountHandle> getAllPhoneAccountHandles() throws RemoteException;

    List<PhoneAccount> getAllPhoneAccounts() throws RemoteException;

    int getAllPhoneAccountsCount() throws RemoteException;

    List<PhoneAccountHandle> getCallCapablePhoneAccounts(boolean z, String str, String str2) throws RemoteException;

    int getCallState() throws RemoteException;

    int getCallStateUsingPackage(String str, String str2) throws RemoteException;

    int getCurrentTtyMode(String str, String str2) throws RemoteException;

    String getDefaultDialerPackage() throws RemoteException;

    String getDefaultDialerPackageForUser(int i) throws RemoteException;

    PhoneAccountHandle getDefaultOutgoingPhoneAccount(String str, String str2, String str3) throws RemoteException;

    ComponentName getDefaultPhoneApp() throws RemoteException;

    String getLine1Number(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    PhoneAccount getPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str) throws RemoteException;

    List<PhoneAccountHandle> getPhoneAccountsForPackage(String str) throws RemoteException;

    List<PhoneAccountHandle> getPhoneAccountsSupportingScheme(String str, String str2) throws RemoteException;

    List<PhoneAccountHandle> getSelfManagedPhoneAccounts(String str, String str2) throws RemoteException;

    PhoneAccountHandle getSimCallManager(int i) throws RemoteException;

    PhoneAccountHandle getSimCallManagerForUser(int i) throws RemoteException;

    String getSystemDialerPackage() throws RemoteException;

    PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(String str) throws RemoteException;

    String getVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    void handleCallIntent(Intent intent, String str) throws RemoteException;

    boolean handlePinMmi(String str, String str2) throws RemoteException;

    boolean handlePinMmiForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    boolean hasManageOngoingCallsPermission(String str) throws RemoteException;

    boolean isInCall(String str, String str2) throws RemoteException;

    boolean isInEmergencyCall() throws RemoteException;

    boolean isInManagedCall(String str, String str2) throws RemoteException;

    boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean isRinging(String str) throws RemoteException;

    boolean isTtySupported(String str, String str2) throws RemoteException;

    boolean isVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2, String str3) throws RemoteException;

    void oplusCancelMissedCallsNotification(String str, Bundle bundle) throws RemoteException;

    void placeCall(Uri uri, Bundle bundle, String str, String str2) throws RemoteException;

    void registerPhoneAccount(PhoneAccount phoneAccount) throws RemoteException;

    void resetCarMode() throws RemoteException;

    boolean setDefaultDialer(String str) throws RemoteException;

    void setSystemDialer(ComponentName componentName) throws RemoteException;

    void setTestCallDiagnosticService(String str) throws RemoteException;

    void setTestDefaultCallRedirectionApp(String str) throws RemoteException;

    void setTestDefaultCallScreeningApp(String str) throws RemoteException;

    void setTestDefaultDialer(String str) throws RemoteException;

    void setTestEmergencyPhoneAccountPackageNameFilter(String str) throws RemoteException;

    void setTestPhoneAcctSuggestionComponent(String str) throws RemoteException;

    void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    void showInCallScreen(boolean z, String str, String str2) throws RemoteException;

    void silenceRinger(String str) throws RemoteException;

    void startConference(List<Uri> list, Bundle bundle, String str) throws RemoteException;

    void stopBlockSuppression() throws RemoteException;

    void unregisterPhoneAccount(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    void waitOnHandlers() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ITelecomService {
        @Override // com.android.internal.telecom.ITelecomService
        public void showInCallScreen(boolean showDialpad, String callingPackage, String callingFeatureId) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getDefaultOutgoingPhoneAccount(String uriScheme, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle account) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getCallCapablePhoneAccounts(boolean includeDisabledAccounts, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getSelfManagedPhoneAccounts(String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getPhoneAccountsSupportingScheme(String uriScheme, String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getPhoneAccountsForPackage(String packageName) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccount getPhoneAccount(PhoneAccountHandle account, String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public int getAllPhoneAccountsCount() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccount> getAllPhoneAccounts() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public List<PhoneAccountHandle> getAllPhoneAccountHandles() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getSimCallManager(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public PhoneAccountHandle getSimCallManagerForUser(int userId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void registerPhoneAccount(PhoneAccount metadata) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void unregisterPhoneAccount(PhoneAccountHandle account) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void clearAccounts(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isVoiceMailNumber(PhoneAccountHandle accountHandle, String number, String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getVoiceMailNumber(PhoneAccountHandle accountHandle, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getLine1Number(PhoneAccountHandle accountHandle, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public ComponentName getDefaultPhoneApp() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getDefaultDialerPackage() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getDefaultDialerPackageForUser(int userId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public String getSystemDialerPackage() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public TelecomAnalytics dumpCallAnalytics() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void silenceRinger(String callingPackage) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isInCall(String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean hasManageOngoingCallsPermission(String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isInManagedCall(String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isRinging(String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public int getCallState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public int getCallStateUsingPackage(String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean endCall(String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void acceptRingingCall(String callingPackage) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void acceptRingingCallWithVideoState(String callingPackage, int videoState) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void cancelMissedCallsNotification(String callingPackage) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean handlePinMmi(String dialString, String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean handlePinMmiForPhoneAccount(PhoneAccountHandle accountHandle, String dialString, String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public Uri getAdnUriForPhoneAccount(PhoneAccountHandle accountHandle, String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isTtySupported(String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public int getCurrentTtyMode(String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addNewIncomingCall(PhoneAccountHandle phoneAccount, Bundle extras) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addNewIncomingConference(PhoneAccountHandle phoneAccount, Bundle extras) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addNewUnknownCall(PhoneAccountHandle phoneAccount, Bundle extras) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void startConference(List<Uri> participants, Bundle extras, String callingPackage) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void placeCall(Uri handle, Bundle extras, String callingPackage, String callingFeatureId) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean enablePhoneAccount(PhoneAccountHandle accountHandle, boolean isEnabled) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean setDefaultDialer(String packageName) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void stopBlockSuppression() throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public Intent createManageBlockedNumbersIntent() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public Intent createLaunchEmergencyDialerIntent(String number) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void waitOnHandlers() throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void acceptHandover(Uri srcAddr, int videoState, PhoneAccountHandle destAcct) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestEmergencyPhoneAccountPackageNameFilter(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public boolean isInEmergencyCall() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void handleCallIntent(Intent intent, String callingPackageProxy) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void cleanupStuckCalls() throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void resetCarMode() throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestDefaultCallRedirectionApp(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestPhoneAcctSuggestionComponent(String flattenedComponentName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestDefaultCallScreeningApp(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addOrRemoveTestCallCompanionApp(String packageName, boolean isAdded) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void addNewOutgoingCall(Intent intent) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void oplusCancelMissedCallsNotification(String callingPackage, Bundle extras) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setSystemDialer(ComponentName testComponentName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestDefaultDialer(String packageName) throws RemoteException {
        }

        @Override // com.android.internal.telecom.ITelecomService
        public void setTestCallDiagnosticService(String packageName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ITelecomService {
        public static final String DESCRIPTOR = "com.android.internal.telecom.ITelecomService";
        static final int TRANSACTION_acceptHandover = 55;
        static final int TRANSACTION_acceptRingingCall = 34;
        static final int TRANSACTION_acceptRingingCallWithVideoState = 35;
        static final int TRANSACTION_addNewIncomingCall = 42;
        static final int TRANSACTION_addNewIncomingConference = 43;
        static final int TRANSACTION_addNewOutgoingCall = 65;
        static final int TRANSACTION_addNewUnknownCall = 44;
        static final int TRANSACTION_addOrRemoveTestCallCompanionApp = 64;
        static final int TRANSACTION_cancelMissedCallsNotification = 36;
        static final int TRANSACTION_cleanupStuckCalls = 59;
        static final int TRANSACTION_clearAccounts = 17;
        static final int TRANSACTION_createLaunchEmergencyDialerIntent = 51;
        static final int TRANSACTION_createManageBlockedNumbersIntent = 50;
        static final int TRANSACTION_dumpCallAnalytics = 25;
        static final int TRANSACTION_enablePhoneAccount = 47;
        static final int TRANSACTION_endCall = 33;
        static final int TRANSACTION_getAdnUriForPhoneAccount = 39;
        static final int TRANSACTION_getAllPhoneAccountHandles = 12;
        static final int TRANSACTION_getAllPhoneAccounts = 11;
        static final int TRANSACTION_getAllPhoneAccountsCount = 10;
        static final int TRANSACTION_getCallCapablePhoneAccounts = 5;
        static final int TRANSACTION_getCallState = 31;
        static final int TRANSACTION_getCallStateUsingPackage = 32;
        static final int TRANSACTION_getCurrentTtyMode = 41;
        static final int TRANSACTION_getDefaultDialerPackage = 22;
        static final int TRANSACTION_getDefaultDialerPackageForUser = 23;
        static final int TRANSACTION_getDefaultOutgoingPhoneAccount = 2;
        static final int TRANSACTION_getDefaultPhoneApp = 21;
        static final int TRANSACTION_getLine1Number = 20;
        static final int TRANSACTION_getPhoneAccount = 9;
        static final int TRANSACTION_getPhoneAccountsForPackage = 8;
        static final int TRANSACTION_getPhoneAccountsSupportingScheme = 7;
        static final int TRANSACTION_getSelfManagedPhoneAccounts = 6;
        static final int TRANSACTION_getSimCallManager = 13;
        static final int TRANSACTION_getSimCallManagerForUser = 14;
        static final int TRANSACTION_getSystemDialerPackage = 24;
        static final int TRANSACTION_getUserSelectedOutgoingPhoneAccount = 3;
        static final int TRANSACTION_getVoiceMailNumber = 19;
        static final int TRANSACTION_handleCallIntent = 58;
        static final int TRANSACTION_handlePinMmi = 37;
        static final int TRANSACTION_handlePinMmiForPhoneAccount = 38;
        static final int TRANSACTION_hasManageOngoingCallsPermission = 28;
        static final int TRANSACTION_isInCall = 27;
        static final int TRANSACTION_isInEmergencyCall = 57;
        static final int TRANSACTION_isInManagedCall = 29;
        static final int TRANSACTION_isIncomingCallPermitted = 52;
        static final int TRANSACTION_isOutgoingCallPermitted = 53;
        static final int TRANSACTION_isRinging = 30;
        static final int TRANSACTION_isTtySupported = 40;
        static final int TRANSACTION_isVoiceMailNumber = 18;
        static final int TRANSACTION_oplusCancelMissedCallsNotification = 66;
        static final int TRANSACTION_placeCall = 46;
        static final int TRANSACTION_registerPhoneAccount = 15;
        static final int TRANSACTION_resetCarMode = 60;
        static final int TRANSACTION_setDefaultDialer = 48;
        static final int TRANSACTION_setSystemDialer = 67;
        static final int TRANSACTION_setTestCallDiagnosticService = 69;
        static final int TRANSACTION_setTestDefaultCallRedirectionApp = 61;
        static final int TRANSACTION_setTestDefaultCallScreeningApp = 63;
        static final int TRANSACTION_setTestDefaultDialer = 68;
        static final int TRANSACTION_setTestEmergencyPhoneAccountPackageNameFilter = 56;
        static final int TRANSACTION_setTestPhoneAcctSuggestionComponent = 62;
        static final int TRANSACTION_setUserSelectedOutgoingPhoneAccount = 4;
        static final int TRANSACTION_showInCallScreen = 1;
        static final int TRANSACTION_silenceRinger = 26;
        static final int TRANSACTION_startConference = 45;
        static final int TRANSACTION_stopBlockSuppression = 49;
        static final int TRANSACTION_unregisterPhoneAccount = 16;
        static final int TRANSACTION_waitOnHandlers = 54;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITelecomService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITelecomService)) {
                return new Proxy(obj);
            }
            return (ITelecomService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "showInCallScreen";
                case 2:
                    return "getDefaultOutgoingPhoneAccount";
                case 3:
                    return "getUserSelectedOutgoingPhoneAccount";
                case 4:
                    return "setUserSelectedOutgoingPhoneAccount";
                case 5:
                    return "getCallCapablePhoneAccounts";
                case 6:
                    return "getSelfManagedPhoneAccounts";
                case 7:
                    return "getPhoneAccountsSupportingScheme";
                case 8:
                    return "getPhoneAccountsForPackage";
                case 9:
                    return "getPhoneAccount";
                case 10:
                    return "getAllPhoneAccountsCount";
                case 11:
                    return "getAllPhoneAccounts";
                case 12:
                    return "getAllPhoneAccountHandles";
                case 13:
                    return "getSimCallManager";
                case 14:
                    return "getSimCallManagerForUser";
                case 15:
                    return "registerPhoneAccount";
                case 16:
                    return "unregisterPhoneAccount";
                case 17:
                    return "clearAccounts";
                case 18:
                    return "isVoiceMailNumber";
                case 19:
                    return "getVoiceMailNumber";
                case 20:
                    return "getLine1Number";
                case 21:
                    return "getDefaultPhoneApp";
                case 22:
                    return "getDefaultDialerPackage";
                case 23:
                    return "getDefaultDialerPackageForUser";
                case 24:
                    return "getSystemDialerPackage";
                case 25:
                    return "dumpCallAnalytics";
                case 26:
                    return "silenceRinger";
                case 27:
                    return "isInCall";
                case 28:
                    return "hasManageOngoingCallsPermission";
                case 29:
                    return "isInManagedCall";
                case 30:
                    return "isRinging";
                case 31:
                    return "getCallState";
                case 32:
                    return "getCallStateUsingPackage";
                case 33:
                    return "endCall";
                case 34:
                    return "acceptRingingCall";
                case 35:
                    return "acceptRingingCallWithVideoState";
                case 36:
                    return "cancelMissedCallsNotification";
                case 37:
                    return "handlePinMmi";
                case 38:
                    return "handlePinMmiForPhoneAccount";
                case 39:
                    return "getAdnUriForPhoneAccount";
                case 40:
                    return "isTtySupported";
                case 41:
                    return "getCurrentTtyMode";
                case 42:
                    return "addNewIncomingCall";
                case 43:
                    return "addNewIncomingConference";
                case 44:
                    return "addNewUnknownCall";
                case 45:
                    return "startConference";
                case 46:
                    return "placeCall";
                case 47:
                    return "enablePhoneAccount";
                case 48:
                    return "setDefaultDialer";
                case 49:
                    return "stopBlockSuppression";
                case 50:
                    return "createManageBlockedNumbersIntent";
                case 51:
                    return "createLaunchEmergencyDialerIntent";
                case 52:
                    return "isIncomingCallPermitted";
                case 53:
                    return "isOutgoingCallPermitted";
                case 54:
                    return "waitOnHandlers";
                case 55:
                    return "acceptHandover";
                case 56:
                    return "setTestEmergencyPhoneAccountPackageNameFilter";
                case 57:
                    return "isInEmergencyCall";
                case 58:
                    return "handleCallIntent";
                case 59:
                    return "cleanupStuckCalls";
                case 60:
                    return "resetCarMode";
                case 61:
                    return "setTestDefaultCallRedirectionApp";
                case 62:
                    return "setTestPhoneAcctSuggestionComponent";
                case 63:
                    return "setTestDefaultCallScreeningApp";
                case 64:
                    return "addOrRemoveTestCallCompanionApp";
                case 65:
                    return "addNewOutgoingCall";
                case 66:
                    return "oplusCancelMissedCallsNotification";
                case 67:
                    return "setSystemDialer";
                case 68:
                    return "setTestDefaultDialer";
                case 69:
                    return "setTestCallDiagnosticService";
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
            PhoneAccountHandle _arg0;
            PhoneAccountHandle _arg02;
            PhoneAccount _arg03;
            PhoneAccountHandle _arg04;
            PhoneAccountHandle _arg05;
            PhoneAccountHandle _arg06;
            PhoneAccountHandle _arg07;
            PhoneAccountHandle _arg08;
            PhoneAccountHandle _arg09;
            PhoneAccountHandle _arg010;
            Bundle _arg1;
            PhoneAccountHandle _arg011;
            Bundle _arg12;
            PhoneAccountHandle _arg012;
            Bundle _arg13;
            Bundle _arg14;
            Uri _arg013;
            Bundle _arg15;
            PhoneAccountHandle _arg014;
            PhoneAccountHandle _arg015;
            PhoneAccountHandle _arg016;
            Uri _arg017;
            PhoneAccountHandle _arg2;
            Intent _arg018;
            Intent _arg019;
            Bundle _arg16;
            ComponentName _arg020;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg17 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg17 = true;
                            }
                            String _arg18 = data.readString();
                            String _arg22 = data.readString();
                            showInCallScreen(_arg17, _arg18, _arg22);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            String _arg19 = data.readString();
                            String _arg23 = data.readString();
                            PhoneAccountHandle _result = getDefaultOutgoingPhoneAccount(_arg021, _arg19, _arg23);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            PhoneAccountHandle _result2 = getUserSelectedOutgoingPhoneAccount(_arg022);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            setUserSelectedOutgoingPhoneAccount(_arg0);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg17 = true;
                            }
                            String _arg110 = data.readString();
                            String _arg24 = data.readString();
                            List<PhoneAccountHandle> _result3 = getCallCapablePhoneAccounts(_arg17, _arg110, _arg24);
                            reply.writeNoException();
                            reply.writeTypedList(_result3);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            List<PhoneAccountHandle> _result4 = getSelfManagedPhoneAccounts(_arg023, data.readString());
                            reply.writeNoException();
                            reply.writeTypedList(_result4);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            List<PhoneAccountHandle> _result5 = getPhoneAccountsSupportingScheme(_arg024, data.readString());
                            reply.writeNoException();
                            reply.writeTypedList(_result5);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg025 = data.readString();
                            List<PhoneAccountHandle> _result6 = getPhoneAccountsForPackage(_arg025);
                            reply.writeNoException();
                            reply.writeTypedList(_result6);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            PhoneAccount _result7 = getPhoneAccount(_arg02, data.readString());
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _result8 = getAllPhoneAccountsCount();
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            List<PhoneAccount> _result9 = getAllPhoneAccounts();
                            reply.writeNoException();
                            reply.writeTypedList(_result9);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            List<PhoneAccountHandle> _result10 = getAllPhoneAccountHandles();
                            reply.writeNoException();
                            reply.writeTypedList(_result10);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            PhoneAccountHandle _result11 = getSimCallManager(_arg026);
                            reply.writeNoException();
                            if (_result11 != null) {
                                reply.writeInt(1);
                                _result11.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            PhoneAccountHandle _result12 = getSimCallManagerForUser(_arg027);
                            reply.writeNoException();
                            if (_result12 != null) {
                                reply.writeInt(1);
                                _result12.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = PhoneAccount.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            registerPhoneAccount(_arg03);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            unregisterPhoneAccount(_arg04);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            clearAccounts(_arg028);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            String _arg111 = data.readString();
                            String _arg25 = data.readString();
                            String _arg3 = data.readString();
                            boolean isVoiceMailNumber = isVoiceMailNumber(_arg05, _arg111, _arg25, _arg3);
                            reply.writeNoException();
                            reply.writeInt(isVoiceMailNumber ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            String _arg112 = data.readString();
                            String _arg26 = data.readString();
                            String _result13 = getVoiceMailNumber(_arg06, _arg112, _arg26);
                            reply.writeNoException();
                            reply.writeString(_result13);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            String _arg113 = data.readString();
                            String _arg27 = data.readString();
                            String _result14 = getLine1Number(_arg07, _arg113, _arg27);
                            reply.writeNoException();
                            reply.writeString(_result14);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            ComponentName _result15 = getDefaultPhoneApp();
                            reply.writeNoException();
                            if (_result15 != null) {
                                reply.writeInt(1);
                                _result15.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            String _result16 = getDefaultDialerPackage();
                            reply.writeNoException();
                            reply.writeString(_result16);
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            String _result17 = getDefaultDialerPackageForUser(_arg029);
                            reply.writeNoException();
                            reply.writeString(_result17);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            String _result18 = getSystemDialerPackage();
                            reply.writeNoException();
                            reply.writeString(_result18);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            TelecomAnalytics _result19 = dumpCallAnalytics();
                            reply.writeNoException();
                            if (_result19 != null) {
                                reply.writeInt(1);
                                _result19.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            silenceRinger(_arg030);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg031 = data.readString();
                            boolean isInCall = isInCall(_arg031, data.readString());
                            reply.writeNoException();
                            reply.writeInt(isInCall ? 1 : 0);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            boolean hasManageOngoingCallsPermission = hasManageOngoingCallsPermission(_arg032);
                            reply.writeNoException();
                            reply.writeInt(hasManageOngoingCallsPermission ? 1 : 0);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            boolean isInManagedCall = isInManagedCall(_arg033, data.readString());
                            reply.writeNoException();
                            reply.writeInt(isInManagedCall ? 1 : 0);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg034 = data.readString();
                            boolean isRinging = isRinging(_arg034);
                            reply.writeNoException();
                            reply.writeInt(isRinging ? 1 : 0);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            int _result20 = getCallState();
                            reply.writeNoException();
                            reply.writeInt(_result20);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            int _result21 = getCallStateUsingPackage(_arg035, data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result21);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            boolean endCall = endCall(_arg036);
                            reply.writeNoException();
                            reply.writeInt(endCall ? 1 : 0);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg037 = data.readString();
                            acceptRingingCall(_arg037);
                            reply.writeNoException();
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            acceptRingingCallWithVideoState(_arg038, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg039 = data.readString();
                            cancelMissedCallsNotification(_arg039);
                            reply.writeNoException();
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg040 = data.readString();
                            boolean handlePinMmi = handlePinMmi(_arg040, data.readString());
                            reply.writeNoException();
                            reply.writeInt(handlePinMmi ? 1 : 0);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            String _arg114 = data.readString();
                            String _arg28 = data.readString();
                            boolean handlePinMmiForPhoneAccount = handlePinMmiForPhoneAccount(_arg08, _arg114, _arg28);
                            reply.writeNoException();
                            reply.writeInt(handlePinMmiForPhoneAccount ? 1 : 0);
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            Uri _result22 = getAdnUriForPhoneAccount(_arg09, data.readString());
                            reply.writeNoException();
                            if (_result22 != null) {
                                reply.writeInt(1);
                                _result22.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            boolean isTtySupported = isTtySupported(_arg041, data.readString());
                            reply.writeNoException();
                            reply.writeInt(isTtySupported ? 1 : 0);
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            int _result23 = getCurrentTtyMode(_arg042, data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result23);
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            addNewIncomingCall(_arg010, _arg1);
                            reply.writeNoException();
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg011 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            addNewIncomingConference(_arg011, _arg12);
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg012 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg012 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            addNewUnknownCall(_arg012, _arg13);
                            reply.writeNoException();
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            List<Uri> _arg043 = data.createTypedArrayList(Uri.CREATOR);
                            if (data.readInt() != 0) {
                                _arg14 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            String _arg29 = data.readString();
                            startConference(_arg043, _arg14, _arg29);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg013 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg013 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg15 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            String _arg210 = data.readString();
                            String _arg32 = data.readString();
                            placeCall(_arg013, _arg15, _arg210, _arg32);
                            reply.writeNoException();
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg014 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg014 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg17 = true;
                            }
                            boolean enablePhoneAccount = enablePhoneAccount(_arg014, _arg17);
                            reply.writeNoException();
                            reply.writeInt(enablePhoneAccount ? 1 : 0);
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg044 = data.readString();
                            boolean defaultDialer = setDefaultDialer(_arg044);
                            reply.writeNoException();
                            reply.writeInt(defaultDialer ? 1 : 0);
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            stopBlockSuppression();
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            Intent _result24 = createManageBlockedNumbersIntent();
                            reply.writeNoException();
                            if (_result24 != null) {
                                reply.writeInt(1);
                                _result24.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg045 = data.readString();
                            Intent _result25 = createLaunchEmergencyDialerIntent(_arg045);
                            reply.writeNoException();
                            if (_result25 != null) {
                                reply.writeInt(1);
                                _result25.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg015 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg015 = null;
                            }
                            boolean isIncomingCallPermitted = isIncomingCallPermitted(_arg015);
                            reply.writeNoException();
                            reply.writeInt(isIncomingCallPermitted ? 1 : 0);
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg016 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg016 = null;
                            }
                            boolean isOutgoingCallPermitted = isOutgoingCallPermitted(_arg016);
                            reply.writeNoException();
                            reply.writeInt(isOutgoingCallPermitted ? 1 : 0);
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            waitOnHandlers();
                            reply.writeNoException();
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg017 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg017 = null;
                            }
                            int _arg115 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            acceptHandover(_arg017, _arg115, _arg2);
                            reply.writeNoException();
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg046 = data.readString();
                            setTestEmergencyPhoneAccountPackageNameFilter(_arg046);
                            reply.writeNoException();
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isInEmergencyCall = isInEmergencyCall();
                            reply.writeNoException();
                            reply.writeInt(isInEmergencyCall ? 1 : 0);
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg018 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg018 = null;
                            }
                            handleCallIntent(_arg018, data.readString());
                            reply.writeNoException();
                            return true;
                        case 59:
                            data.enforceInterface(DESCRIPTOR);
                            cleanupStuckCalls();
                            reply.writeNoException();
                            return true;
                        case 60:
                            data.enforceInterface(DESCRIPTOR);
                            resetCarMode();
                            reply.writeNoException();
                            return true;
                        case 61:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg047 = data.readString();
                            setTestDefaultCallRedirectionApp(_arg047);
                            reply.writeNoException();
                            return true;
                        case 62:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg048 = data.readString();
                            setTestPhoneAcctSuggestionComponent(_arg048);
                            reply.writeNoException();
                            return true;
                        case 63:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg049 = data.readString();
                            setTestDefaultCallScreeningApp(_arg049);
                            reply.writeNoException();
                            return true;
                        case 64:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg050 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = true;
                            }
                            addOrRemoveTestCallCompanionApp(_arg050, _arg17);
                            reply.writeNoException();
                            return true;
                        case 65:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg019 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg019 = null;
                            }
                            addNewOutgoingCall(_arg019);
                            reply.writeNoException();
                            return true;
                        case 66:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg051 = data.readString();
                            if (data.readInt() != 0) {
                                _arg16 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            oplusCancelMissedCallsNotification(_arg051, _arg16);
                            reply.writeNoException();
                            return true;
                        case 67:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg020 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg020 = null;
                            }
                            setSystemDialer(_arg020);
                            reply.writeNoException();
                            return true;
                        case 68:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg052 = data.readString();
                            setTestDefaultDialer(_arg052);
                            reply.writeNoException();
                            return true;
                        case 69:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg053 = data.readString();
                            setTestCallDiagnosticService(_arg053);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ITelecomService {
            public static ITelecomService sDefaultImpl;
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

            @Override // com.android.internal.telecom.ITelecomService
            public void showInCallScreen(boolean showDialpad, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(showDialpad ? 1 : 0);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().showInCallScreen(showDialpad, callingPackage, callingFeatureId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getDefaultOutgoingPhoneAccount(String uriScheme, String callingPackage, String callingFeatureId) throws RemoteException {
                PhoneAccountHandle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uriScheme);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultOutgoingPhoneAccount(uriScheme, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PhoneAccountHandle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(String callingPackage) throws RemoteException {
                PhoneAccountHandle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserSelectedOutgoingPhoneAccount(callingPackage);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PhoneAccountHandle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(1);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setUserSelectedOutgoingPhoneAccount(account);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getCallCapablePhoneAccounts(boolean includeDisabledAccounts, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(includeDisabledAccounts ? 1 : 0);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallCapablePhoneAccounts(includeDisabledAccounts, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    List<PhoneAccountHandle> _result = _reply.createTypedArrayList(PhoneAccountHandle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getSelfManagedPhoneAccounts(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSelfManagedPhoneAccounts(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    List<PhoneAccountHandle> _result = _reply.createTypedArrayList(PhoneAccountHandle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getPhoneAccountsSupportingScheme(String uriScheme, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uriScheme);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccountsSupportingScheme(uriScheme, callingPackage);
                    }
                    _reply.readException();
                    List<PhoneAccountHandle> _result = _reply.createTypedArrayList(PhoneAccountHandle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getPhoneAccountsForPackage(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccountsForPackage(packageName);
                    }
                    _reply.readException();
                    List<PhoneAccountHandle> _result = _reply.createTypedArrayList(PhoneAccountHandle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccount getPhoneAccount(PhoneAccountHandle account, String callingPackage) throws RemoteException {
                PhoneAccount _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(1);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccount(account, callingPackage);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PhoneAccount.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public int getAllPhoneAccountsCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPhoneAccountsCount();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccount> getAllPhoneAccounts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPhoneAccounts();
                    }
                    _reply.readException();
                    List<PhoneAccount> _result = _reply.createTypedArrayList(PhoneAccount.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public List<PhoneAccountHandle> getAllPhoneAccountHandles() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPhoneAccountHandles();
                    }
                    _reply.readException();
                    List<PhoneAccountHandle> _result = _reply.createTypedArrayList(PhoneAccountHandle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getSimCallManager(int subId) throws RemoteException {
                PhoneAccountHandle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimCallManager(subId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PhoneAccountHandle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public PhoneAccountHandle getSimCallManagerForUser(int userId) throws RemoteException {
                PhoneAccountHandle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimCallManagerForUser(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PhoneAccountHandle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void registerPhoneAccount(PhoneAccount metadata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metadata != null) {
                        _data.writeInt(1);
                        metadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerPhoneAccount(metadata);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void unregisterPhoneAccount(PhoneAccountHandle account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(1);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterPhoneAccount(account);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void clearAccounts(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearAccounts(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isVoiceMailNumber(PhoneAccountHandle accountHandle, String number, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (accountHandle != null) {
                        _data.writeInt(1);
                        accountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(number);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoiceMailNumber(accountHandle, number, callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telecom.ITelecomService
            public String getVoiceMailNumber(PhoneAccountHandle accountHandle, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accountHandle != null) {
                        _data.writeInt(1);
                        accountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMailNumber(accountHandle, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getLine1Number(PhoneAccountHandle accountHandle, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accountHandle != null) {
                        _data.writeInt(1);
                        accountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1Number(accountHandle, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public ComponentName getDefaultPhoneApp() throws RemoteException {
                ComponentName _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultPhoneApp();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ComponentName.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getDefaultDialerPackage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultDialerPackage();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getDefaultDialerPackageForUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultDialerPackageForUser(userId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public String getSystemDialerPackage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemDialerPackage();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public TelecomAnalytics dumpCallAnalytics() throws RemoteException {
                TelecomAnalytics _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpCallAnalytics();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TelecomAnalytics.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void silenceRinger(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().silenceRinger(callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isInCall(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInCall(callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telecom.ITelecomService
            public boolean hasManageOngoingCallsPermission(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasManageOngoingCallsPermission(callingPackage);
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

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isInManagedCall(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInManagedCall(callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isRinging(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRinging(callingPackage);
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

            @Override // com.android.internal.telecom.ITelecomService
            public int getCallState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public int getCallStateUsingPackage(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallStateUsingPackage(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean endCall(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().endCall(callingPackage);
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

            @Override // com.android.internal.telecom.ITelecomService
            public void acceptRingingCall(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().acceptRingingCall(callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void acceptRingingCallWithVideoState(String callingPackage, int videoState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeInt(videoState);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().acceptRingingCallWithVideoState(callingPackage, videoState);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void cancelMissedCallsNotification(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelMissedCallsNotification(callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean handlePinMmi(String dialString, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(dialString);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmi(dialString, callingPackage);
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

            @Override // com.android.internal.telecom.ITelecomService
            public boolean handlePinMmiForPhoneAccount(PhoneAccountHandle accountHandle, String dialString, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (accountHandle != null) {
                        _data.writeInt(1);
                        accountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(dialString);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmiForPhoneAccount(accountHandle, dialString, callingPackage);
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

            @Override // com.android.internal.telecom.ITelecomService
            public Uri getAdnUriForPhoneAccount(PhoneAccountHandle accountHandle, String callingPackage) throws RemoteException {
                Uri _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accountHandle != null) {
                        _data.writeInt(1);
                        accountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdnUriForPhoneAccount(accountHandle, callingPackage);
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

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isTtySupported(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTtySupported(callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telecom.ITelecomService
            public int getCurrentTtyMode(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTtyMode(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addNewIncomingCall(PhoneAccountHandle phoneAccount, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccount != null) {
                        _data.writeInt(1);
                        phoneAccount.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addNewIncomingCall(phoneAccount, extras);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addNewIncomingConference(PhoneAccountHandle phoneAccount, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccount != null) {
                        _data.writeInt(1);
                        phoneAccount.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addNewIncomingConference(phoneAccount, extras);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addNewUnknownCall(PhoneAccountHandle phoneAccount, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccount != null) {
                        _data.writeInt(1);
                        phoneAccount.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addNewUnknownCall(phoneAccount, extras);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void startConference(List<Uri> participants, Bundle extras, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(participants);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startConference(participants, extras, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void placeCall(Uri handle, Bundle extras, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().placeCall(handle, extras, callingPackage, callingFeatureId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean enablePhoneAccount(PhoneAccountHandle accountHandle, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (accountHandle != null) {
                        _data.writeInt(1);
                        accountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enablePhoneAccount(accountHandle, isEnabled);
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

            @Override // com.android.internal.telecom.ITelecomService
            public boolean setDefaultDialer(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDefaultDialer(packageName);
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

            @Override // com.android.internal.telecom.ITelecomService
            public void stopBlockSuppression() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopBlockSuppression();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public Intent createManageBlockedNumbersIntent() throws RemoteException {
                Intent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createManageBlockedNumbersIntent();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Intent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public Intent createLaunchEmergencyDialerIntent(String number) throws RemoteException {
                Intent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createLaunchEmergencyDialerIntent(number);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Intent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (phoneAccountHandle != null) {
                        _data.writeInt(1);
                        phoneAccountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isIncomingCallPermitted(phoneAccountHandle);
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

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (phoneAccountHandle != null) {
                        _data.writeInt(1);
                        phoneAccountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOutgoingCallPermitted(phoneAccountHandle);
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

            @Override // com.android.internal.telecom.ITelecomService
            public void waitOnHandlers() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().waitOnHandlers();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void acceptHandover(Uri srcAddr, int videoState, PhoneAccountHandle destAcct) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (srcAddr != null) {
                        _data.writeInt(1);
                        srcAddr.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(videoState);
                    if (destAcct != null) {
                        _data.writeInt(1);
                        destAcct.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().acceptHandover(srcAddr, videoState, destAcct);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestEmergencyPhoneAccountPackageNameFilter(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestEmergencyPhoneAccountPackageNameFilter(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public boolean isInEmergencyCall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInEmergencyCall();
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

            @Override // com.android.internal.telecom.ITelecomService
            public void handleCallIntent(Intent intent, String callingPackageProxy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackageProxy);
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().handleCallIntent(intent, callingPackageProxy);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void cleanupStuckCalls() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cleanupStuckCalls();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void resetCarMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetCarMode();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestDefaultCallRedirectionApp(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestDefaultCallRedirectionApp(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestPhoneAcctSuggestionComponent(String flattenedComponentName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(flattenedComponentName);
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestPhoneAcctSuggestionComponent(flattenedComponentName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestDefaultCallScreeningApp(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestDefaultCallScreeningApp(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addOrRemoveTestCallCompanionApp(String packageName, boolean isAdded) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(isAdded ? 1 : 0);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addOrRemoveTestCallCompanionApp(packageName, isAdded);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void addNewOutgoingCall(Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addNewOutgoingCall(intent);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void oplusCancelMissedCallsNotification(String callingPackage, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().oplusCancelMissedCallsNotification(callingPackage, extras);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setSystemDialer(ComponentName testComponentName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (testComponentName != null) {
                        _data.writeInt(1);
                        testComponentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSystemDialer(testComponentName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestDefaultDialer(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestDefaultDialer(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telecom.ITelecomService
            public void setTestCallDiagnosticService(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestCallDiagnosticService(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITelecomService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITelecomService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
