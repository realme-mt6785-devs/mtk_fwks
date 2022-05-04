package com.android.internal.telephony;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.WorkSource;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.CallForwardingInfo;
import android.telephony.CarrierRestrictionRules;
import android.telephony.CellIdentity;
import android.telephony.CellInfo;
import android.telephony.ClientRequestStats;
import android.telephony.IBootstrapAuthenticationCallback;
import android.telephony.ICellInfoCallback;
import android.telephony.IccOpenLogicalChannelResponse;
import android.telephony.NeighboringCellInfo;
import android.telephony.NetworkScanRequest;
import android.telephony.PhoneCapability;
import android.telephony.PhoneNumberRange;
import android.telephony.RadioAccessSpecifier;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SignalStrengthUpdateRequest;
import android.telephony.TelephonyHistogram;
import android.telephony.ThermalMitigationRequest;
import android.telephony.UiccCardInfo;
import android.telephony.UiccSlotInfo;
import android.telephony.VisualVoicemailSmsFilterSettings;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.gba.UaSecurityProtocolIdentifier;
import android.telephony.ims.RcsClientConfiguration;
import android.telephony.ims.RcsContactUceCapability;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsConfigCallback;
import android.telephony.ims.aidl.IImsRegistration;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.telephony.ims.aidl.IRcsConfigCallback;
import com.android.ims.internal.IImsServiceFeatureCallback;
import com.android.internal.telephony.IBooleanConsumer;
import com.android.internal.telephony.ICallForwardingInfoCallback;
import com.android.internal.telephony.IIntegerConsumer;
import com.android.internal.telephony.INumberVerificationCallback;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public interface ITelephony extends IInterface {
    RcsContactUceCapability addUceRegistrationOverrideShell(int i, List<String> list) throws RemoteException;

    void bootstrapAuthenticationRequest(int i, int i2, Uri uri, UaSecurityProtocolIdentifier uaSecurityProtocolIdentifier, boolean z, IBootstrapAuthenticationCallback iBootstrapAuthenticationCallback) throws RemoteException;

    void cacheMmTelCapabilityProvisioning(int i, int i2, int i3, boolean z) throws RemoteException;

    void call(String str, String str2) throws RemoteException;

    boolean canChangeDtmfToneLength(int i, String str, String str2) throws RemoteException;

    boolean canConnectTo5GInDsdsMode() throws RemoteException;

    void carrierActionReportDefaultNetworkStatus(int i, boolean z) throws RemoteException;

    void carrierActionResetAll(int i) throws RemoteException;

    void carrierActionSetRadioEnabled(int i, boolean z) throws RemoteException;

    int changeIccLockPassword(int i, String str, String str2) throws RemoteException;

    int checkCarrierPrivilegesForPackage(int i, String str) throws RemoteException;

    int checkCarrierPrivilegesForPackageAnyPhone(String str) throws RemoteException;

    boolean clearCarrierImsServiceOverride(int i) throws RemoteException;

    void clearSignalStrengthUpdateRequest(int i, SignalStrengthUpdateRequest signalStrengthUpdateRequest, String str) throws RemoteException;

    RcsContactUceCapability clearUceRegistrationOverrideShell(int i) throws RemoteException;

    void dial(String str) throws RemoteException;

    boolean disableDataConnectivity() throws RemoteException;

    void disableIms(int i) throws RemoteException;

    void disableLocationUpdates() throws RemoteException;

    void disableVisualVoicemailSmsFilter(String str, int i) throws RemoteException;

    boolean doesSwitchMultiSimConfigTriggerReboot(int i, String str, String str2) throws RemoteException;

    boolean enableDataConnectivity() throws RemoteException;

    void enableIms(int i) throws RemoteException;

    void enableLocationUpdates() throws RemoteException;

    boolean enableModemForSlot(int i, boolean z) throws RemoteException;

    void enableVideoCalling(boolean z) throws RemoteException;

    void enableVisualVoicemailSmsFilter(String str, int i, VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) throws RemoteException;

    void enqueueSmsPickResult(String str, String str2, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void factoryReset(int i) throws RemoteException;

    int getActivePhoneType() throws RemoteException;

    int getActivePhoneTypeForSlot(int i) throws RemoteException;

    VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i) throws RemoteException;

    String getAidForAppType(int i, int i2) throws RemoteException;

    List<CellInfo> getAllCellInfo(String str, String str2) throws RemoteException;

    CarrierRestrictionRules getAllowedCarriers() throws RemoteException;

    int getAllowedNetworkTypesBitmask(int i) throws RemoteException;

    long getAllowedNetworkTypesForReason(int i, int i2) throws RemoteException;

    String getBoundGbaService(int i) throws RemoteException;

    String getBoundImsServicePackage(int i, boolean z, int i2) throws RemoteException;

    int getCallComposerStatus(int i) throws RemoteException;

    void getCallForwarding(int i, int i2, ICallForwardingInfoCallback iCallForwardingInfoCallback) throws RemoteException;

    int getCallState() throws RemoteException;

    int getCallStateForSubscription(int i, String str, String str2) throws RemoteException;

    void getCallWaitingStatus(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    String getCapabilityFromEab(String str) throws RemoteException;

    int getCardIdForDefaultEuicc(int i, String str) throws RemoteException;

    int getCarrierIdFromMccMnc(int i, String str, boolean z) throws RemoteException;

    int getCarrierIdListVersion(int i) throws RemoteException;

    List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int i) throws RemoteException;

    int getCarrierPrivilegeStatus(int i) throws RemoteException;

    int getCarrierPrivilegeStatusForUid(int i, int i2) throws RemoteException;

    boolean getCarrierSingleRegistrationEnabled(int i) throws RemoteException;

    int getCdmaEriIconIndex(String str, String str2) throws RemoteException;

    int getCdmaEriIconIndexForSubscriber(int i, String str, String str2) throws RemoteException;

    int getCdmaEriIconMode(String str, String str2) throws RemoteException;

    int getCdmaEriIconModeForSubscriber(int i, String str, String str2) throws RemoteException;

    String getCdmaEriText(String str, String str2) throws RemoteException;

    String getCdmaEriTextForSubscriber(int i, String str, String str2) throws RemoteException;

    String getCdmaMdn(int i) throws RemoteException;

    String getCdmaMin(int i) throws RemoteException;

    String getCdmaPrlVersion(int i) throws RemoteException;

    int getCdmaRoamingMode(int i) throws RemoteException;

    int getCdmaSubscriptionMode(int i) throws RemoteException;

    CellIdentity getCellLocation(String str, String str2) throws RemoteException;

    CellNetworkScanResult getCellNetworkScanResults(int i, String str, String str2) throws RemoteException;

    List<String> getCertsFromCarrierPrivilegeAccessRules(int i) throws RemoteException;

    List<ClientRequestStats> getClientRequestStats(String str, String str2, int i) throws RemoteException;

    String getContactFromEab(String str) throws RemoteException;

    String getCurrentPackageName() throws RemoteException;

    int getDataActivationState(int i, String str) throws RemoteException;

    int getDataActivity() throws RemoteException;

    int getDataActivityForSubId(int i) throws RemoteException;

    boolean getDataEnabled(int i) throws RemoteException;

    int getDataNetworkType(String str, String str2) throws RemoteException;

    int getDataNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException;

    int getDataState() throws RemoteException;

    int getDataStateForSubId(int i) throws RemoteException;

    @Deprecated
    String getDeviceId(String str) throws RemoteException;

    String getDeviceIdWithFeature(String str, String str2) throws RemoteException;

    boolean getDeviceSingleRegistrationEnabled() throws RemoteException;

    String getDeviceSoftwareVersionForSlot(int i, String str, String str2) throws RemoteException;

    boolean getDeviceUceEnabled() throws RemoteException;

    boolean getEmergencyCallbackMode(int i) throws RemoteException;

    int getEmergencyNumberDbVersion(int i) throws RemoteException;

    Map getEmergencyNumberList(String str, String str2) throws RemoteException;

    List<String> getEmergencyNumberListTestMode() throws RemoteException;

    List<String> getEquivalentHomePlmns(int i, String str, String str2) throws RemoteException;

    String getEsn(int i) throws RemoteException;

    String[] getForbiddenPlmns(int i, int i2, String str, String str2) throws RemoteException;

    int getGbaReleaseTime(int i) throws RemoteException;

    String getImeiForSlot(int i, String str, String str2) throws RemoteException;

    IImsConfig getImsConfig(int i, int i2) throws RemoteException;

    boolean getImsFeatureValidationOverride(int i) throws RemoteException;

    void getImsMmTelFeatureState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void getImsMmTelRegistrationState(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void getImsMmTelRegistrationTransportType(int i, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    int getImsProvisioningInt(int i, int i2) throws RemoteException;

    boolean getImsProvisioningStatusForCapability(int i, int i2, int i3) throws RemoteException;

    String getImsProvisioningString(int i, int i2) throws RemoteException;

    int getImsRegTechnologyForMmTel(int i) throws RemoteException;

    IImsRegistration getImsRegistration(int i, int i2) throws RemoteException;

    String getLastUcePidfXmlShell(int i) throws RemoteException;

    RcsContactUceCapability getLatestRcsContactUceCapabilityShell(int i) throws RemoteException;

    String getLine1AlphaTagForDisplay(int i, String str, String str2) throws RemoteException;

    String getLine1NumberForDisplay(int i, String str, String str2) throws RemoteException;

    int getLteOnCdmaMode(String str, String str2) throws RemoteException;

    int getLteOnCdmaModeForSubscriber(int i, String str, String str2) throws RemoteException;

    String getManualNetworkSelectionPlmn(int i) throws RemoteException;

    String getManufacturerCodeForSlot(int i) throws RemoteException;

    String getMeidForSlot(int i, String str, String str2) throws RemoteException;

    String[] getMergedImsisFromGroup(int i, String str) throws RemoteException;

    String[] getMergedSubscriberIds(int i, String str, String str2) throws RemoteException;

    String getMmsUAProfUrl(int i) throws RemoteException;

    String getMmsUserAgent(int i) throws RemoteException;

    String getMobileProvisioningUrl() throws RemoteException;

    List<NeighboringCellInfo> getNeighboringCellInfo(String str, String str2) throws RemoteException;

    String getNetworkCountryIsoForPhone(int i) throws RemoteException;

    int getNetworkSelectionMode(int i) throws RemoteException;

    int getNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException;

    int getNumberOfModemsWithSimultaneousDataConnections(int i, String str, String str2) throws RemoteException;

    List<String> getPackagesWithCarrierPrivileges(int i) throws RemoteException;

    List<String> getPackagesWithCarrierPrivilegesForAllPhones() throws RemoteException;

    String[] getPcscfAddress(String str, String str2, String str3) throws RemoteException;

    PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int i) throws RemoteException;

    PhoneCapability getPhoneCapability() throws RemoteException;

    int getRadioAccessFamily(int i, String str) throws RemoteException;

    int getRadioHalVersion() throws RemoteException;

    int getRadioPowerState(int i, String str, String str2) throws RemoteException;

    boolean getRcsProvisioningStatusForCapability(int i, int i2) throws RemoteException;

    boolean getRcsSingleRegistrationTestModeEnabled() throws RemoteException;

    ServiceState getServiceStateForSubscriber(int i, String str, String str2) throws RemoteException;

    SignalStrength getSignalStrength(int i) throws RemoteException;

    String getSimLocaleForSubscriber(int i) throws RemoteException;

    void getSlicingConfig(ResultReceiver resultReceiver) throws RemoteException;

    int[] getSlotsMapping() throws RemoteException;

    int getSubIdForPhoneAccount(PhoneAccount phoneAccount) throws RemoteException;

    int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle, String str, String str2) throws RemoteException;

    int getSubscriptionCarrierId(int i) throws RemoteException;

    String getSubscriptionCarrierName(int i) throws RemoteException;

    int getSubscriptionSpecificCarrierId(int i) throws RemoteException;

    String getSubscriptionSpecificCarrierName(int i) throws RemoteException;

    List<RadioAccessSpecifier> getSystemSelectionChannels(int i) throws RemoteException;

    List<TelephonyHistogram> getTelephonyHistograms() throws RemoteException;

    String getTypeAllocationCodeForSlot(int i) throws RemoteException;

    List<UiccCardInfo> getUiccCardsInfo(String str) throws RemoteException;

    UiccSlotInfo[] getUiccSlotsInfo() throws RemoteException;

    String getVisualVoicemailPackageName(String str, String str2, int i) throws RemoteException;

    Bundle getVisualVoicemailSettings(String str, int i) throws RemoteException;

    VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String str, int i) throws RemoteException;

    int getVoWiFiModeSetting(int i) throws RemoteException;

    int getVoWiFiRoamingModeSetting(int i) throws RemoteException;

    int getVoiceActivationState(int i, String str) throws RemoteException;

    int getVoiceMessageCountForSubscriber(int i, String str, String str2) throws RemoteException;

    int getVoiceNetworkTypeForSubscriber(int i, String str, String str2) throws RemoteException;

    Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean handlePinMmi(String str) throws RemoteException;

    boolean handlePinMmiForSubscriber(int i, String str) throws RemoteException;

    void handleUssdRequest(int i, String str, ResultReceiver resultReceiver) throws RemoteException;

    boolean hasIccCard() throws RemoteException;

    boolean hasIccCardUsingSlotIndex(int i) throws RemoteException;

    boolean iccCloseLogicalChannel(int i, int i2) throws RemoteException;

    boolean iccCloseLogicalChannelBySlot(int i, int i2) throws RemoteException;

    byte[] iccExchangeSimIO(int i, int i2, int i3, int i4, int i5, int i6, String str) throws RemoteException;

    IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String str, String str2, int i2) throws RemoteException;

    IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int i, String str, String str2, int i2) throws RemoteException;

    String iccTransmitApduBasicChannel(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws RemoteException;

    String iccTransmitApduBasicChannelBySlot(int i, String str, int i2, int i3, int i4, int i5, int i6, String str2) throws RemoteException;

    String iccTransmitApduLogicalChannel(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws RemoteException;

    String iccTransmitApduLogicalChannelBySlot(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) throws RemoteException;

    int invokeOemRilRequestRaw(byte[] bArr, byte[] bArr2) throws RemoteException;

    boolean isAdvancedCallingSettingEnabled(int i) throws RemoteException;

    boolean isApnMetered(int i, int i2) throws RemoteException;

    boolean isApplicationOnUicc(int i, int i2) throws RemoteException;

    boolean isAvailable(int i, int i2, int i3) throws RemoteException;

    boolean isCapable(int i, int i2, int i3) throws RemoteException;

    boolean isConcurrentVoiceAndDataAllowed(int i) throws RemoteException;

    boolean isCrossSimCallingEnabledByUser(int i) throws RemoteException;

    boolean isDataConnectivityPossible(int i) throws RemoteException;

    boolean isDataEnabled(int i) throws RemoteException;

    boolean isDataEnabledForApn(int i, int i2, String str) throws RemoteException;

    boolean isDataEnabledForReason(int i, int i2) throws RemoteException;

    boolean isDataRoamingEnabled(int i) throws RemoteException;

    boolean isEmergencyNumber(String str, boolean z) throws RemoteException;

    boolean isHearingAidCompatibilitySupported() throws RemoteException;

    boolean isIccLockEnabled(int i) throws RemoteException;

    boolean isImsRegistered(int i) throws RemoteException;

    boolean isInEmergencySmsMode() throws RemoteException;

    boolean isManualNetworkSelectionAllowed(int i) throws RemoteException;

    boolean isMmTelCapabilityProvisionedInCache(int i, int i2, int i3) throws RemoteException;

    void isMmTelCapabilitySupported(int i, IIntegerConsumer iIntegerConsumer, int i2, int i3) throws RemoteException;

    boolean isMobileDataPolicyEnabled(int i, int i2) throws RemoteException;

    boolean isModemEnabledForSlot(int i, String str, String str2) throws RemoteException;

    int isMultiSimSupported(String str, String str2) throws RemoteException;

    boolean isMvnoMatched(int i, int i2, String str) throws RemoteException;

    boolean isNrDualConnectivityEnabled(int i) throws RemoteException;

    boolean isRadioInterfaceCapabilitySupported(String str) throws RemoteException;

    @Deprecated
    boolean isRadioOn(String str) throws RemoteException;

    @Deprecated
    boolean isRadioOnForSubscriber(int i, String str) throws RemoteException;

    boolean isRadioOnForSubscriberWithFeature(int i, String str, String str2) throws RemoteException;

    boolean isRadioOnWithFeature(String str, String str2) throws RemoteException;

    boolean isRcsVolteSingleRegistrationCapable(int i) throws RemoteException;

    boolean isRttSupported(int i) throws RemoteException;

    boolean isTetheringApnRequiredForSubscriber(int i) throws RemoteException;

    boolean isTtyModeSupported() throws RemoteException;

    boolean isTtyOverVolteEnabled(int i) throws RemoteException;

    boolean isUserDataEnabled(int i) throws RemoteException;

    boolean isVideoCallingEnabled(String str, String str2) throws RemoteException;

    boolean isVideoTelephonyAvailable(int i) throws RemoteException;

    boolean isVoWiFiRoamingSettingEnabled(int i) throws RemoteException;

    boolean isVoWiFiSettingEnabled(int i) throws RemoteException;

    boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle) throws RemoteException;

    boolean isVtSettingEnabled(int i) throws RemoteException;

    boolean isWifiCallingAvailable(int i) throws RemoteException;

    boolean isWorldPhone(int i, String str, String str2) throws RemoteException;

    boolean needMobileRadioShutdown() throws RemoteException;

    boolean needsOtaServiceProvisioning() throws RemoteException;

    void notifyOtaEmergencyNumberDbInstalled() throws RemoteException;

    void notifyRcsAutoConfigurationReceived(int i, byte[] bArr, boolean z) throws RemoteException;

    String nvReadItem(int i) throws RemoteException;

    boolean nvWriteCdmaPrl(byte[] bArr) throws RemoteException;

    boolean nvWriteItem(int i, String str) throws RemoteException;

    int prepareForUnattendedReboot() throws RemoteException;

    boolean rebootModem(int i) throws RemoteException;

    void refreshUiccProfile(int i) throws RemoteException;

    void registerImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException;

    void registerImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void registerMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void registerMmTelFeatureCallback(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException;

    void registerRcsProvisioningCallback(int i, IRcsConfigCallback iRcsConfigCallback) throws RemoteException;

    int removeContactFromEab(int i, String str) throws RemoteException;

    RcsContactUceCapability removeUceRegistrationOverrideShell(int i, List<String> list) throws RemoteException;

    boolean removeUceRequestDisallowedStatus(int i) throws RemoteException;

    void requestCellInfoUpdate(int i, ICellInfoCallback iCellInfoCallback, String str, String str2) throws RemoteException;

    void requestCellInfoUpdateWithWorkSource(int i, ICellInfoCallback iCellInfoCallback, String str, String str2, WorkSource workSource) throws RemoteException;

    void requestModemActivityInfo(ResultReceiver resultReceiver) throws RemoteException;

    int requestNetworkScan(int i, NetworkScanRequest networkScanRequest, Messenger messenger, IBinder iBinder, String str, String str2) throws RemoteException;

    void requestNumberVerification(PhoneNumberRange phoneNumberRange, long j, INumberVerificationCallback iNumberVerificationCallback, String str) throws RemoteException;

    void requestUserActivityNotification() throws RemoteException;

    void resetIms(int i) throws RemoteException;

    boolean resetModemConfig(int i) throws RemoteException;

    void resetOtaEmergencyNumberDbFilePath() throws RemoteException;

    void sendDeviceToDeviceMessage(int i, int i2) throws RemoteException;

    void sendDialerSpecialCode(String str, String str2) throws RemoteException;

    String sendEnvelopeWithStatus(int i, String str) throws RemoteException;

    int sendThermalMitigationRequest(int i, ThermalMitigationRequest thermalMitigationRequest, String str) throws RemoteException;

    void sendVisualVoicemailSmsForSubscriber(String str, String str2, int i, String str3, int i2, String str4, PendingIntent pendingIntent) throws RemoteException;

    void setActiveDeviceToDeviceTransport(String str) throws RemoteException;

    void setAdvancedCallingSettingEnabled(int i, boolean z) throws RemoteException;

    int setAllowedCarriers(CarrierRestrictionRules carrierRestrictionRules) throws RemoteException;

    boolean setAllowedNetworkTypesForReason(int i, int i2, long j) throws RemoteException;

    void setAlwaysReportSignalStrength(int i, boolean z) throws RemoteException;

    boolean setBoundGbaServiceOverride(int i, String str) throws RemoteException;

    boolean setBoundImsServiceOverride(int i, boolean z, int[] iArr, String str) throws RemoteException;

    void setCallComposerStatus(int i, int i2) throws RemoteException;

    void setCallForwarding(int i, CallForwardingInfo callForwardingInfo, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void setCallWaitingStatus(int i, boolean z, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    boolean setCapabilitiesRequestTimeout(int i, long j) throws RemoteException;

    boolean setCarrierSingleRegistrationEnabledOverride(int i, String str) throws RemoteException;

    void setCarrierTestOverride(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) throws RemoteException;

    boolean setCdmaRoamingMode(int i, int i2) throws RemoteException;

    boolean setCdmaSubscriptionMode(int i, int i2) throws RemoteException;

    void setCellInfoListRate(int i) throws RemoteException;

    void setCepEnabled(boolean z) throws RemoteException;

    void setCrossSimCallingEnabled(int i, boolean z) throws RemoteException;

    void setDataActivationState(int i, int i2) throws RemoteException;

    void setDataEnabledForReason(int i, int i2, boolean z) throws RemoteException;

    void setDataRoamingEnabled(int i, boolean z) throws RemoteException;

    void setDeviceSingleRegistrationEnabledOverride(String str) throws RemoteException;

    void setDeviceToDeviceForceEnabled(boolean z) throws RemoteException;

    void setDeviceUceEnabled(boolean z) throws RemoteException;

    int setForbiddenPlmns(int i, int i2, List<String> list, String str, String str2) throws RemoteException;

    boolean setGbaReleaseTimeOverride(int i, int i2) throws RemoteException;

    int setIccLockEnabled(int i, boolean z, String str) throws RemoteException;

    boolean setImsFeatureValidationOverride(int i, String str) throws RemoteException;

    int setImsProvisioningInt(int i, int i2, int i3) throws RemoteException;

    void setImsProvisioningStatusForCapability(int i, int i2, int i3, boolean z) throws RemoteException;

    int setImsProvisioningString(int i, int i2, String str) throws RemoteException;

    void setImsRegistrationState(boolean z) throws RemoteException;

    boolean setLine1NumberForDisplayForSubscriber(int i, String str, String str2) throws RemoteException;

    void setMobileDataPolicyEnabled(int i, int i2, boolean z) throws RemoteException;

    void setMultiSimCarrierRestriction(boolean z) throws RemoteException;

    void setNetworkSelectionModeAutomatic(int i) throws RemoteException;

    boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorInfo, boolean z) throws RemoteException;

    int setNrDualConnectivityState(int i, int i2) throws RemoteException;

    boolean setOperatorBrandOverride(int i, String str) throws RemoteException;

    boolean setRadio(boolean z) throws RemoteException;

    boolean setRadioForSubscriber(int i, boolean z) throws RemoteException;

    boolean setRadioPower(boolean z) throws RemoteException;

    void setRcsClientConfiguration(int i, RcsClientConfiguration rcsClientConfiguration) throws RemoteException;

    void setRcsProvisioningStatusForCapability(int i, int i2, boolean z) throws RemoteException;

    void setRcsSingleRegistrationTestModeEnabled(boolean z) throws RemoteException;

    boolean setRoamingOverride(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws RemoteException;

    void setRttCapabilitySetting(int i, boolean z) throws RemoteException;

    void setSignalStrengthUpdateRequest(int i, SignalStrengthUpdateRequest signalStrengthUpdateRequest, String str) throws RemoteException;

    void setSimPowerStateForSlot(int i, int i2) throws RemoteException;

    void setSimPowerStateForSlotWithCallback(int i, int i2, IIntegerConsumer iIntegerConsumer) throws RemoteException;

    void setSystemSelectionChannels(List<RadioAccessSpecifier> list, int i, IBooleanConsumer iBooleanConsumer) throws RemoteException;

    void setVoWiFiModeSetting(int i, int i2) throws RemoteException;

    void setVoWiFiNonPersistent(int i, boolean z, int i2) throws RemoteException;

    void setVoWiFiRoamingModeSetting(int i, int i2) throws RemoteException;

    void setVoWiFiRoamingSettingEnabled(int i, boolean z) throws RemoteException;

    void setVoWiFiSettingEnabled(int i, boolean z) throws RemoteException;

    void setVoiceActivationState(int i, int i2) throws RemoteException;

    boolean setVoiceMailNumber(int i, String str, String str2) throws RemoteException;

    void setVoicemailRingtoneUri(String str, PhoneAccountHandle phoneAccountHandle, Uri uri) throws RemoteException;

    void setVoicemailVibrationEnabled(String str, PhoneAccountHandle phoneAccountHandle, boolean z) throws RemoteException;

    void setVtSettingEnabled(int i, boolean z) throws RemoteException;

    void shutdownMobileRadios() throws RemoteException;

    void startEmergencyCallbackMode() throws RemoteException;

    void stopNetworkScan(int i, int i2) throws RemoteException;

    boolean supplyPinForSubscriber(int i, String str) throws RemoteException;

    int[] supplyPinReportResultForSubscriber(int i, String str) throws RemoteException;

    boolean supplyPukForSubscriber(int i, String str, String str2) throws RemoteException;

    int[] supplyPukReportResultForSubscriber(int i, String str, String str2) throws RemoteException;

    void switchMultiSimConfig(int i) throws RemoteException;

    boolean switchSlots(int[] iArr) throws RemoteException;

    void toggleRadioOnOff() throws RemoteException;

    void toggleRadioOnOffForSubscriber(int i) throws RemoteException;

    void triggerRcsReconfiguration(int i) throws RemoteException;

    void unregisterImsFeatureCallback(IImsServiceFeatureCallback iImsServiceFeatureCallback) throws RemoteException;

    void unregisterImsProvisioningChangedCallback(int i, IImsConfigCallback iImsConfigCallback) throws RemoteException;

    void unregisterImsRegistrationCallback(int i, IImsRegistrationCallback iImsRegistrationCallback) throws RemoteException;

    void unregisterMmTelCapabilityCallback(int i, IImsCapabilityCallback iImsCapabilityCallback) throws RemoteException;

    void unregisterRcsProvisioningCallback(int i, IRcsConfigCallback iRcsConfigCallback) throws RemoteException;

    void updateEmergencyNumberListTestMode(int i, EmergencyNumber emergencyNumber) throws RemoteException;

    void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void updateServiceLocation() throws RemoteException;

    void updateServiceLocationWithPackageName(String str) throws RemoteException;

    void uploadCallComposerPicture(int i, String str, String str2, ParcelFileDescriptor parcelFileDescriptor, ResultReceiver resultReceiver) throws RemoteException;

    void userActivity() throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements ITelephony {
        @Override // com.android.internal.telephony.ITelephony
        public void dial(String number) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void call(String callingPackage, String number) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOn(String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOnWithFeature(String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOnForSubscriber(int subId, String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioOnForSubscriberWithFeature(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCallComposerStatus(int subId, int status) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCallComposerStatus(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean supplyPinForSubscriber(int subId, String pin) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean supplyPukForSubscriber(int subId, String puk, String pin) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int[] supplyPinReportResultForSubscriber(int subId, String pin) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int[] supplyPukReportResultForSubscriber(int subId, String puk, String pin) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean handlePinMmi(String dialString) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void handleUssdRequest(int subId, String ussdRequest, ResultReceiver wrappedCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean handlePinMmiForSubscriber(int subId, String dialString) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void toggleRadioOnOff() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void toggleRadioOnOffForSubscriber(int subId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRadio(boolean turnOn) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRadioForSubscriber(int subId, boolean turnOn) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRadioPower(boolean turnOn) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateServiceLocation() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateServiceLocationWithPackageName(String callingPkg) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableLocationUpdates() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableLocationUpdates() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean enableDataConnectivity() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean disableDataConnectivity() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataConnectivityPossible(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public CellIdentity getCellLocation(String callingPkg, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getNetworkCountryIsoForPhone(int phoneId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<NeighboringCellInfo> getNeighboringCellInfo(String callingPkg, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCallState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCallStateForSubscription(int subId, String callingPackage, String featureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataActivity() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataActivityForSubId(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataState() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataStateForSubId(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getActivePhoneType() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getActivePhoneTypeForSlot(int slotIndex) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconIndex(String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconIndexForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconMode(String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaEriIconModeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaEriText(String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaEriTextForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean needsOtaServiceProvisioning() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setVoiceMailNumber(int subId, String alphaTag, String number) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoiceActivationState(int subId, int activationState) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDataActivationState(int subId, int activationState) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoiceActivationState(int subId, String callingPackage) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataActivationState(int subId, String callingPackage) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoiceMessageCountForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isConcurrentVoiceAndDataAllowed(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public Bundle getVisualVoicemailSettings(String callingPackage, int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getVisualVoicemailPackageName(String callingPackage, String callingFeatureId, int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableVisualVoicemailSmsFilter(String callingPackage, int subId, VisualVoicemailSmsFilterSettings settings) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableVisualVoicemailSmsFilter(String callingPackage, int subId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String callingPackage, int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void sendVisualVoicemailSmsForSubscriber(String callingPackage, String callingAttributeTag, int subId, String number, int port, String text, PendingIntent sentIntent) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void sendDialerSpecialCode(String callingPackageName, String inputCode) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getNetworkTypeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataNetworkType(String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getDataNetworkTypeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoiceNetworkTypeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean hasIccCard() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean hasIccCardUsingSlotIndex(int slotIndex) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getLteOnCdmaMode(String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getLteOnCdmaModeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<CellInfo> getAllCellInfo(String callingPkg, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestCellInfoUpdate(int subId, ICellInfoCallback cb, String callingPkg, String callingFeatureId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestCellInfoUpdateWithWorkSource(int subId, ICellInfoCallback cb, String callingPkg, String callingFeatureId, WorkSource ws) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCellInfoListRate(int rateInMillis) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int slotIndex, String callingPackage, String AID, int p2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int subId, String callingPackage, String AID, int p2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean iccCloseLogicalChannelBySlot(int slotIndex, int channel) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean iccCloseLogicalChannel(int subId, int channel) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduLogicalChannelBySlot(int slotIndex, int channel, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduLogicalChannel(int subId, int channel, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduBasicChannelBySlot(int slotIndex, String callingPackage, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String iccTransmitApduBasicChannel(int subId, String callingPackage, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public byte[] iccExchangeSimIO(int subId, int fileID, int command, int p1, int p2, int p3, String filePath) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String sendEnvelopeWithStatus(int subId, String content) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String nvReadItem(int itemID) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean nvWriteItem(int itemID, String itemValue) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean nvWriteCdmaPrl(byte[] preferredRoamingList) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean resetModemConfig(int slotIndex) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean rebootModem(int slotIndex) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getAllowedNetworkTypesBitmask(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isTetheringApnRequiredForSubscriber(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableIms(int slotId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void disableIms(int slotId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void resetIms(int slotIndex) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerMmTelFeatureCallback(int slotId, IImsServiceFeatureCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterImsFeatureCallback(IImsServiceFeatureCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public IImsRegistration getImsRegistration(int slotId, int feature) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public IImsConfig getImsConfig(int slotId, int feature) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setBoundImsServiceOverride(int slotIndex, boolean isCarrierService, int[] featureTypes, String packageName) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean clearCarrierImsServiceOverride(int slotIndex) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getBoundImsServicePackage(int slotIndex, boolean isCarrierImsService, int featureType) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getImsMmTelFeatureState(int subId, IIntegerConsumer callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setNetworkSelectionModeAutomatic(int subId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public CellNetworkScanResult getCellNetworkScanResults(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int requestNetworkScan(int subId, NetworkScanRequest request, Messenger messenger, IBinder binder, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void stopNetworkScan(int subId, int scanId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setNetworkSelectionModeManual(int subId, OperatorInfo operatorInfo, boolean persisSelection) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public long getAllowedNetworkTypesForReason(int subId, int reason) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setAllowedNetworkTypesForReason(int subId, int reason, long allowedNetworkTypes) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getDataEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isUserDataEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDataEnabledForReason(int subId, int reason, boolean enable) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataEnabledForReason(int subId, int reason) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isManualNetworkSelectionAllowed(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setAlwaysReportSignalStrength(int subId, boolean isEnable) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getPcscfAddress(String apnType, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setImsRegistrationState(boolean registered) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaMdn(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaMin(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestNumberVerification(PhoneNumberRange range, long timeoutMillis, INumberVerificationCallback callback, String callingPackage) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierPrivilegeStatus(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierPrivilegeStatusForUid(int subId, int uid) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int checkCarrierPrivilegesForPackage(int subId, String pkgName) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int checkCarrierPrivilegesForPackageAnyPhone(String pkgName) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int phoneId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setLine1NumberForDisplayForSubscriber(int subId, String alphaTag, String number) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getLine1NumberForDisplay(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getLine1AlphaTagForDisplay(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getMergedSubscriberIds(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getMergedImsisFromGroup(int subId, String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setOperatorBrandOverride(int subId, String brand) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setRoamingOverride(int subId, List<String> gsmRoamingList, List<String> gsmNonRoamingList, List<String> cdmaRoamingList, List<String> cdmaNonRoamingList) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int invokeOemRilRequestRaw(byte[] oemReq, byte[] oemResp) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean needMobileRadioShutdown() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void shutdownMobileRadios() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getRadioAccessFamily(int phoneId, String callingPackage) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void uploadCallComposerPicture(int subscriptionId, String callingPackage, String contentType, ParcelFileDescriptor fd, ResultReceiver callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enableVideoCalling(boolean enable) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVideoCallingEnabled(String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean canChangeDtmfToneLength(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isWorldPhone(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isTtyModeSupported() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRttSupported(int subscriptionId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isHearingAidCompatibilitySupported() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isImsRegistered(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isWifiCallingAvailable(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVideoTelephonyAvailable(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getImsRegTechnologyForMmTel(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getDeviceId(String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getDeviceIdWithFeature(String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getImeiForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getTypeAllocationCodeForSlot(int slotIndex) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getMeidForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getManufacturerCodeForSlot(int slotIndex) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getDeviceSoftwareVersionForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubIdForPhoneAccount(PhoneAccount phoneAccount) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int subscriptionId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void factoryReset(int subId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getSimLocaleForSubscriber(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestModemActivityInfo(ResultReceiver result) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public ServiceState getServiceStateForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public Uri getVoicemailRingtoneUri(PhoneAccountHandle accountHandle) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoicemailRingtoneUri(String callingPackage, PhoneAccountHandle phoneAccountHandle, Uri uri) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVoicemailVibrationEnabled(PhoneAccountHandle accountHandle) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoicemailVibrationEnabled(String callingPackage, PhoneAccountHandle phoneAccountHandle, boolean enabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getPackagesWithCarrierPrivileges(int phoneId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getPackagesWithCarrierPrivilegesForAllPhones() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getAidForAppType(int subId, int appType) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getEsn(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCdmaPrlVersion(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<TelephonyHistogram> getTelephonyHistograms() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setAllowedCarriers(CarrierRestrictionRules carrierRestrictionRules) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public CarrierRestrictionRules getAllowedCarriers() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubscriptionCarrierId(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getSubscriptionCarrierName(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getSubscriptionSpecificCarrierId(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getSubscriptionSpecificCarrierName(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierIdFromMccMnc(int slotIndex, String mccmnc, boolean isSubscriptionMccMnc) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void carrierActionSetRadioEnabled(int subId, boolean enabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void carrierActionReportDefaultNetworkStatus(int subId, boolean report) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void carrierActionResetAll(int subId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getCallForwarding(int subId, int callForwardingReason, ICallForwardingInfoCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCallForwarding(int subId, CallForwardingInfo callForwardingInfo, IIntegerConsumer callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getCallWaitingStatus(int subId, IIntegerConsumer callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCallWaitingStatus(int subId, boolean enabled, IIntegerConsumer callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<ClientRequestStats> getClientRequestStats(String callingPackage, String callingFeatureId, int subid) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setSimPowerStateForSlot(int slotIndex, int state) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setSimPowerStateForSlotWithCallback(int slotIndex, int state, IIntegerConsumer callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String[] getForbiddenPlmns(int subId, int appType, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setForbiddenPlmns(int subId, int appType, List<String> fplmns, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getEmergencyCallbackMode(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public SignalStrength getSignalStrength(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCardIdForDefaultEuicc(int subId, String callingPackage) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<UiccCardInfo> getUiccCardsInfo(String callingPackage) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public UiccSlotInfo[] getUiccSlotsInfo() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean switchSlots(int[] physicalSlots) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataRoamingEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDataRoamingEnabled(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaRoamingMode(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCdmaRoamingMode(int subId, int mode) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCdmaSubscriptionMode(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCdmaSubscriptionMode(int subId, int mode) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCarrierTestOverride(int subId, String mccmnc, String imsi, String iccid, String gid1, String gid2, String plmn, String spn, String carrierPrivilegeRules, String apn) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getCarrierIdListVersion(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void refreshUiccProfile(int subId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getNumberOfModemsWithSimultaneousDataConnections(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getNetworkSelectionMode(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isInEmergencySmsMode() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getRadioPowerState(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getImsMmTelRegistrationState(int subId, IIntegerConsumer consumer) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getImsMmTelRegistrationTransportType(int subId, IIntegerConsumer consumer) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerMmTelCapabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterMmTelCapabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isCapable(int subId, int capability, int regTech) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isAvailable(int subId, int capability, int regTech) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void isMmTelCapabilitySupported(int subId, IIntegerConsumer callback, int capability, int transportType) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isAdvancedCallingSettingEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setAdvancedCallingSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVtSettingEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVtSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVoWiFiSettingEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isCrossSimCallingEnabledByUser(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCrossSimCallingEnabled(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isVoWiFiRoamingSettingEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiRoamingSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiNonPersistent(int subId, boolean isCapable, int mode) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoWiFiModeSetting(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiModeSetting(int subId, int mode) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getVoWiFiRoamingModeSetting(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setVoWiFiRoamingModeSetting(int subId, int mode) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setRttCapabilitySetting(int subId, boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isTtyOverVolteEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public Map getEmergencyNumberList(String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isEmergencyNumber(String number, boolean exactMatch) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getCertsFromCarrierPrivilegeAccessRules(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerImsProvisioningChangedCallback(int subId, IImsConfigCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterImsProvisioningChangedCallback(int subId, IImsConfigCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setImsProvisioningStatusForCapability(int subId, int capability, int tech, boolean isProvisioned) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getImsProvisioningStatusForCapability(int subId, int capability, int tech) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getRcsProvisioningStatusForCapability(int subId, int capability) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setRcsProvisioningStatusForCapability(int subId, int capability, boolean isProvisioned) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isMmTelCapabilityProvisionedInCache(int subId, int capability, int tech) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void cacheMmTelCapabilityProvisioning(int subId, int capability, int tech, boolean isProvisioned) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getImsProvisioningInt(int subId, int key) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getImsProvisioningString(int subId, int key) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setImsProvisioningInt(int subId, int key, int value) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setImsProvisioningString(int subId, int key, String value) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void startEmergencyCallbackMode() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateEmergencyNumberListTestMode(int action, EmergencyNumber num) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getEmergencyNumberListTestMode() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getEmergencyNumberDbVersion(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void notifyOtaEmergencyNumberDbInstalled() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor otaParcelFileDescriptor) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void resetOtaEmergencyNumberDbFilePath() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean enableModemForSlot(int slotIndex, boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setMultiSimCarrierRestriction(boolean isMultiSimCarrierRestricted) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public int isMultiSimSupported(String callingPackage, String callingFeatureId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void switchMultiSimConfig(int numOfSims) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean doesSwitchMultiSimConfigTriggerReboot(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int[] getSlotsMapping() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getRadioHalVersion() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCurrentPackageName() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isApplicationOnUicc(int subId, int appType) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isModemEnabledForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isDataEnabledForApn(int apnType, int subId, String callingPackage) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isApnMetered(int apnType, int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setSystemSelectionChannels(List<RadioAccessSpecifier> specifiers, int subId, IBooleanConsumer resultCallback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<RadioAccessSpecifier> getSystemSelectionChannels(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isMvnoMatched(int subId, int mvnoType, String mvnoMatchData) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void enqueueSmsPickResult(String callingPackage, String callingAttributeTag, IIntegerConsumer subIdResult) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getMmsUserAgent(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getMmsUAProfUrl(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setMobileDataPolicyEnabled(int subscriptionId, int policy, boolean enabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isMobileDataPolicyEnabled(int subscriptionId, int policy) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setCepEnabled(boolean isCepEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void notifyRcsAutoConfigurationReceived(int subId, byte[] config, boolean isCompressed) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isIccLockEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setIccLockEnabled(int subId, boolean enabled, String password) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int changeIccLockPassword(int subId, String oldPassword, String newPassword) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void requestUserActivityNotification() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void userActivity() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getManualNetworkSelectionPlmn(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean canConnectTo5GInDsdsMode() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public List<String> getEquivalentHomePlmns(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int setNrDualConnectivityState(int subId, int nrDualConnectivityState) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isNrDualConnectivityEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRadioInterfaceCapabilitySupported(String capability) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int sendThermalMitigationRequest(int subId, ThermalMitigationRequest thermalMitigationRequest, String callingPackage) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void bootstrapAuthenticationRequest(int subId, int appType, Uri nafUrl, UaSecurityProtocolIdentifier securityProtocol, boolean forceBootStrapping, IBootstrapAuthenticationCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setBoundGbaServiceOverride(int subId, String packageName) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getBoundGbaService(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setGbaReleaseTimeOverride(int subId, int interval) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int getGbaReleaseTime(int subId) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setRcsClientConfiguration(int subId, RcsClientConfiguration rcc) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean isRcsVolteSingleRegistrationCapable(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void registerRcsProvisioningCallback(int subId, IRcsConfigCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void unregisterRcsProvisioningCallback(int subId, IRcsConfigCallback callback) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void triggerRcsReconfiguration(int subId) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setRcsSingleRegistrationTestModeEnabled(boolean enabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getRcsSingleRegistrationTestModeEnabled() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDeviceSingleRegistrationEnabledOverride(String enabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getDeviceSingleRegistrationEnabled() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCarrierSingleRegistrationEnabledOverride(int subId, String enabled) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void sendDeviceToDeviceMessage(int message, int value) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setActiveDeviceToDeviceTransport(String transport) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDeviceToDeviceForceEnabled(boolean isForceEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getCarrierSingleRegistrationEnabled(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setImsFeatureValidationOverride(int subId, String enabled) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getImsFeatureValidationOverride(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getMobileProvisioningUrl() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int removeContactFromEab(int subId, String contacts) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getContactFromEab(String contact) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getCapabilityFromEab(String contact) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean getDeviceUceEnabled() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setDeviceUceEnabled(boolean isEnabled) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public RcsContactUceCapability addUceRegistrationOverrideShell(int subId, List<String> featureTags) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public RcsContactUceCapability removeUceRegistrationOverrideShell(int subId, List<String> featureTags) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public RcsContactUceCapability clearUceRegistrationOverrideShell(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public RcsContactUceCapability getLatestRcsContactUceCapabilityShell(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public String getLastUcePidfXmlShell(int subId) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean removeUceRequestDisallowedStatus(int subId) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean setCapabilitiesRequestTimeout(int subId, long timeoutAfterMs) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void setSignalStrengthUpdateRequest(int subId, SignalStrengthUpdateRequest request, String callingPackage) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public void clearSignalStrengthUpdateRequest(int subId, SignalStrengthUpdateRequest request, String callingPackage) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public PhoneCapability getPhoneCapability() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public int prepareForUnattendedReboot() throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void getSlicingConfig(ResultReceiver callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements ITelephony {
        public static final String DESCRIPTOR = "com.android.internal.telephony.ITelephony";
        static final int TRANSACTION_addUceRegistrationOverrideShell = 319;
        static final int TRANSACTION_bootstrapAuthenticationRequest = 292;
        static final int TRANSACTION_cacheMmTelCapabilityProvisioning = 246;
        static final int TRANSACTION_call = 2;
        static final int TRANSACTION_canChangeDtmfToneLength = 139;
        static final int TRANSACTION_canConnectTo5GInDsdsMode = 286;
        static final int TRANSACTION_carrierActionReportDefaultNetworkStatus = 180;
        static final int TRANSACTION_carrierActionResetAll = 181;
        static final int TRANSACTION_carrierActionSetRadioEnabled = 179;
        static final int TRANSACTION_changeIccLockPassword = 282;
        static final int TRANSACTION_checkCarrierPrivilegesForPackage = 122;
        static final int TRANSACTION_checkCarrierPrivilegesForPackageAnyPhone = 123;
        static final int TRANSACTION_clearCarrierImsServiceOverride = 98;
        static final int TRANSACTION_clearSignalStrengthUpdateRequest = 327;
        static final int TRANSACTION_clearUceRegistrationOverrideShell = 321;
        static final int TRANSACTION_dial = 1;
        static final int TRANSACTION_disableDataConnectivity = 26;
        static final int TRANSACTION_disableIms = 91;
        static final int TRANSACTION_disableLocationUpdates = 24;
        static final int TRANSACTION_disableVisualVoicemailSmsFilter = 56;
        static final int TRANSACTION_doesSwitchMultiSimConfigTriggerReboot = 262;
        static final int TRANSACTION_enableDataConnectivity = 25;
        static final int TRANSACTION_enableIms = 90;
        static final int TRANSACTION_enableLocationUpdates = 23;
        static final int TRANSACTION_enableModemForSlot = 258;
        static final int TRANSACTION_enableVideoCalling = 137;
        static final int TRANSACTION_enableVisualVoicemailSmsFilter = 55;
        static final int TRANSACTION_enqueueSmsPickResult = 273;
        static final int TRANSACTION_factoryReset = 158;
        static final int TRANSACTION_getActivePhoneType = 37;
        static final int TRANSACTION_getActivePhoneTypeForSlot = 38;
        static final int TRANSACTION_getActiveVisualVoicemailSmsFilterSettings = 58;
        static final int TRANSACTION_getAidForAppType = 168;
        static final int TRANSACTION_getAllCellInfo = 69;
        static final int TRANSACTION_getAllowedCarriers = 173;
        static final int TRANSACTION_getAllowedNetworkTypesBitmask = 88;
        static final int TRANSACTION_getAllowedNetworkTypesForReason = 106;
        static final int TRANSACTION_getBoundGbaService = 294;
        static final int TRANSACTION_getBoundImsServicePackage = 99;
        static final int TRANSACTION_getCallComposerStatus = 8;
        static final int TRANSACTION_getCallForwarding = 182;
        static final int TRANSACTION_getCallState = 31;
        static final int TRANSACTION_getCallStateForSubscription = 32;
        static final int TRANSACTION_getCallWaitingStatus = 184;
        static final int TRANSACTION_getCapabilityFromEab = 316;
        static final int TRANSACTION_getCardIdForDefaultEuicc = 193;
        static final int TRANSACTION_getCarrierIdFromMccMnc = 178;
        static final int TRANSACTION_getCarrierIdListVersion = 204;
        static final int TRANSACTION_getCarrierPackageNamesForIntentAndPhone = 124;
        static final int TRANSACTION_getCarrierPrivilegeStatus = 120;
        static final int TRANSACTION_getCarrierPrivilegeStatusForUid = 121;
        static final int TRANSACTION_getCarrierSingleRegistrationEnabled = 310;
        static final int TRANSACTION_getCdmaEriIconIndex = 39;
        static final int TRANSACTION_getCdmaEriIconIndexForSubscriber = 40;
        static final int TRANSACTION_getCdmaEriIconMode = 41;
        static final int TRANSACTION_getCdmaEriIconModeForSubscriber = 42;
        static final int TRANSACTION_getCdmaEriText = 43;
        static final int TRANSACTION_getCdmaEriTextForSubscriber = 44;
        static final int TRANSACTION_getCdmaMdn = 117;
        static final int TRANSACTION_getCdmaMin = 118;
        static final int TRANSACTION_getCdmaPrlVersion = 170;
        static final int TRANSACTION_getCdmaRoamingMode = 199;
        static final int TRANSACTION_getCdmaSubscriptionMode = 201;
        static final int TRANSACTION_getCellLocation = 28;
        static final int TRANSACTION_getCellNetworkScanResults = 102;
        static final int TRANSACTION_getCertsFromCarrierPrivilegeAccessRules = 238;
        static final int TRANSACTION_getClientRequestStats = 186;
        static final int TRANSACTION_getContactFromEab = 315;
        static final int TRANSACTION_getCurrentPackageName = 265;
        static final int TRANSACTION_getDataActivationState = 50;
        static final int TRANSACTION_getDataActivity = 33;
        static final int TRANSACTION_getDataActivityForSubId = 34;
        static final int TRANSACTION_getDataEnabled = 108;
        static final int TRANSACTION_getDataNetworkType = 62;
        static final int TRANSACTION_getDataNetworkTypeForSubscriber = 63;
        static final int TRANSACTION_getDataState = 35;
        static final int TRANSACTION_getDataStateForSubId = 36;
        static final int TRANSACTION_getDeviceId = 148;
        static final int TRANSACTION_getDeviceIdWithFeature = 149;
        static final int TRANSACTION_getDeviceSingleRegistrationEnabled = 305;
        static final int TRANSACTION_getDeviceSoftwareVersionForSlot = 154;
        static final int TRANSACTION_getDeviceUceEnabled = 317;
        static final int TRANSACTION_getEmergencyCallbackMode = 191;
        static final int TRANSACTION_getEmergencyNumberDbVersion = 254;
        static final int TRANSACTION_getEmergencyNumberList = 236;
        static final int TRANSACTION_getEmergencyNumberListTestMode = 253;
        static final int TRANSACTION_getEquivalentHomePlmns = 287;
        static final int TRANSACTION_getEsn = 169;
        static final int TRANSACTION_getForbiddenPlmns = 189;
        static final int TRANSACTION_getGbaReleaseTime = 296;
        static final int TRANSACTION_getImeiForSlot = 150;
        static final int TRANSACTION_getImsConfig = 96;
        static final int TRANSACTION_getImsFeatureValidationOverride = 312;
        static final int TRANSACTION_getImsMmTelFeatureState = 100;
        static final int TRANSACTION_getImsMmTelRegistrationState = 212;
        static final int TRANSACTION_getImsMmTelRegistrationTransportType = 213;
        static final int TRANSACTION_getImsProvisioningInt = 247;
        static final int TRANSACTION_getImsProvisioningStatusForCapability = 242;
        static final int TRANSACTION_getImsProvisioningString = 248;
        static final int TRANSACTION_getImsRegTechnologyForMmTel = 147;
        static final int TRANSACTION_getImsRegistration = 95;
        static final int TRANSACTION_getLastUcePidfXmlShell = 323;
        static final int TRANSACTION_getLatestRcsContactUceCapabilityShell = 322;
        static final int TRANSACTION_getLine1AlphaTagForDisplay = 127;
        static final int TRANSACTION_getLine1NumberForDisplay = 126;
        static final int TRANSACTION_getLteOnCdmaMode = 67;
        static final int TRANSACTION_getLteOnCdmaModeForSubscriber = 68;
        static final int TRANSACTION_getManualNetworkSelectionPlmn = 285;
        static final int TRANSACTION_getManufacturerCodeForSlot = 153;
        static final int TRANSACTION_getMeidForSlot = 152;
        static final int TRANSACTION_getMergedImsisFromGroup = 129;
        static final int TRANSACTION_getMergedSubscriberIds = 128;
        static final int TRANSACTION_getMmsUAProfUrl = 275;
        static final int TRANSACTION_getMmsUserAgent = 274;
        static final int TRANSACTION_getMobileProvisioningUrl = 313;
        static final int TRANSACTION_getNeighboringCellInfo = 30;
        static final int TRANSACTION_getNetworkCountryIsoForPhone = 29;
        static final int TRANSACTION_getNetworkSelectionMode = 207;
        static final int TRANSACTION_getNetworkTypeForSubscriber = 61;
        static final int TRANSACTION_getNumberOfModemsWithSimultaneousDataConnections = 206;
        static final int TRANSACTION_getPackagesWithCarrierPrivileges = 166;
        static final int TRANSACTION_getPackagesWithCarrierPrivilegesForAllPhones = 167;
        static final int TRANSACTION_getPcscfAddress = 115;
        static final int TRANSACTION_getPhoneAccountHandleForSubscriptionId = 157;
        static final int TRANSACTION_getPhoneCapability = 328;
        static final int TRANSACTION_getRadioAccessFamily = 135;
        static final int TRANSACTION_getRadioHalVersion = 264;
        static final int TRANSACTION_getRadioPowerState = 209;
        static final int TRANSACTION_getRcsProvisioningStatusForCapability = 243;
        static final int TRANSACTION_getRcsSingleRegistrationTestModeEnabled = 303;
        static final int TRANSACTION_getServiceStateForSubscriber = 161;
        static final int TRANSACTION_getSignalStrength = 192;
        static final int TRANSACTION_getSimLocaleForSubscriber = 159;
        static final int TRANSACTION_getSlicingConfig = 330;
        static final int TRANSACTION_getSlotsMapping = 263;
        static final int TRANSACTION_getSubIdForPhoneAccount = 155;
        static final int TRANSACTION_getSubIdForPhoneAccountHandle = 156;
        static final int TRANSACTION_getSubscriptionCarrierId = 174;
        static final int TRANSACTION_getSubscriptionCarrierName = 175;
        static final int TRANSACTION_getSubscriptionSpecificCarrierId = 176;
        static final int TRANSACTION_getSubscriptionSpecificCarrierName = 177;
        static final int TRANSACTION_getSystemSelectionChannels = 271;
        static final int TRANSACTION_getTelephonyHistograms = 171;
        static final int TRANSACTION_getTypeAllocationCodeForSlot = 151;
        static final int TRANSACTION_getUiccCardsInfo = 194;
        static final int TRANSACTION_getUiccSlotsInfo = 195;
        static final int TRANSACTION_getVisualVoicemailPackageName = 54;
        static final int TRANSACTION_getVisualVoicemailSettings = 53;
        static final int TRANSACTION_getVisualVoicemailSmsFilterSettings = 57;
        static final int TRANSACTION_getVoWiFiModeSetting = 230;
        static final int TRANSACTION_getVoWiFiRoamingModeSetting = 232;
        static final int TRANSACTION_getVoiceActivationState = 49;
        static final int TRANSACTION_getVoiceMessageCountForSubscriber = 51;
        static final int TRANSACTION_getVoiceNetworkTypeForSubscriber = 64;
        static final int TRANSACTION_getVoicemailRingtoneUri = 162;
        static final int TRANSACTION_handlePinMmi = 13;
        static final int TRANSACTION_handlePinMmiForSubscriber = 15;
        static final int TRANSACTION_handleUssdRequest = 14;
        static final int TRANSACTION_hasIccCard = 65;
        static final int TRANSACTION_hasIccCardUsingSlotIndex = 66;
        static final int TRANSACTION_iccCloseLogicalChannel = 76;
        static final int TRANSACTION_iccCloseLogicalChannelBySlot = 75;
        static final int TRANSACTION_iccExchangeSimIO = 81;
        static final int TRANSACTION_iccOpenLogicalChannel = 74;
        static final int TRANSACTION_iccOpenLogicalChannelBySlot = 73;
        static final int TRANSACTION_iccTransmitApduBasicChannel = 80;
        static final int TRANSACTION_iccTransmitApduBasicChannelBySlot = 79;
        static final int TRANSACTION_iccTransmitApduLogicalChannel = 78;
        static final int TRANSACTION_iccTransmitApduLogicalChannelBySlot = 77;
        static final int TRANSACTION_invokeOemRilRequestRaw = 132;
        static final int TRANSACTION_isAdvancedCallingSettingEnabled = 219;
        static final int TRANSACTION_isApnMetered = 269;
        static final int TRANSACTION_isApplicationOnUicc = 266;
        static final int TRANSACTION_isAvailable = 217;
        static final int TRANSACTION_isCapable = 216;
        static final int TRANSACTION_isConcurrentVoiceAndDataAllowed = 52;
        static final int TRANSACTION_isCrossSimCallingEnabledByUser = 225;
        static final int TRANSACTION_isDataConnectivityPossible = 27;
        static final int TRANSACTION_isDataEnabled = 110;
        static final int TRANSACTION_isDataEnabledForApn = 268;
        static final int TRANSACTION_isDataEnabledForReason = 112;
        static final int TRANSACTION_isDataRoamingEnabled = 197;
        static final int TRANSACTION_isEmergencyNumber = 237;
        static final int TRANSACTION_isHearingAidCompatibilitySupported = 143;
        static final int TRANSACTION_isIccLockEnabled = 280;
        static final int TRANSACTION_isImsRegistered = 144;
        static final int TRANSACTION_isInEmergencySmsMode = 208;
        static final int TRANSACTION_isManualNetworkSelectionAllowed = 113;
        static final int TRANSACTION_isMmTelCapabilityProvisionedInCache = 245;
        static final int TRANSACTION_isMmTelCapabilitySupported = 218;
        static final int TRANSACTION_isMobileDataPolicyEnabled = 277;
        static final int TRANSACTION_isModemEnabledForSlot = 267;
        static final int TRANSACTION_isMultiSimSupported = 260;
        static final int TRANSACTION_isMvnoMatched = 272;
        static final int TRANSACTION_isNrDualConnectivityEnabled = 289;
        static final int TRANSACTION_isRadioInterfaceCapabilitySupported = 290;
        static final int TRANSACTION_isRadioOn = 3;
        static final int TRANSACTION_isRadioOnForSubscriber = 5;
        static final int TRANSACTION_isRadioOnForSubscriberWithFeature = 6;
        static final int TRANSACTION_isRadioOnWithFeature = 4;
        static final int TRANSACTION_isRcsVolteSingleRegistrationCapable = 298;
        static final int TRANSACTION_isRttSupported = 142;
        static final int TRANSACTION_isTetheringApnRequiredForSubscriber = 89;
        static final int TRANSACTION_isTtyModeSupported = 141;
        static final int TRANSACTION_isTtyOverVolteEnabled = 235;
        static final int TRANSACTION_isUserDataEnabled = 109;
        static final int TRANSACTION_isVideoCallingEnabled = 138;
        static final int TRANSACTION_isVideoTelephonyAvailable = 146;
        static final int TRANSACTION_isVoWiFiRoamingSettingEnabled = 227;
        static final int TRANSACTION_isVoWiFiSettingEnabled = 223;
        static final int TRANSACTION_isVoicemailVibrationEnabled = 164;
        static final int TRANSACTION_isVtSettingEnabled = 221;
        static final int TRANSACTION_isWifiCallingAvailable = 145;
        static final int TRANSACTION_isWorldPhone = 140;
        static final int TRANSACTION_needMobileRadioShutdown = 133;
        static final int TRANSACTION_needsOtaServiceProvisioning = 45;
        static final int TRANSACTION_notifyOtaEmergencyNumberDbInstalled = 255;
        static final int TRANSACTION_notifyRcsAutoConfigurationReceived = 279;
        static final int TRANSACTION_nvReadItem = 83;
        static final int TRANSACTION_nvWriteCdmaPrl = 85;
        static final int TRANSACTION_nvWriteItem = 84;
        static final int TRANSACTION_prepareForUnattendedReboot = 329;
        static final int TRANSACTION_rebootModem = 87;
        static final int TRANSACTION_refreshUiccProfile = 205;
        static final int TRANSACTION_registerImsProvisioningChangedCallback = 239;
        static final int TRANSACTION_registerImsRegistrationCallback = 210;
        static final int TRANSACTION_registerMmTelCapabilityCallback = 214;
        static final int TRANSACTION_registerMmTelFeatureCallback = 93;
        static final int TRANSACTION_registerRcsProvisioningCallback = 299;
        static final int TRANSACTION_removeContactFromEab = 314;
        static final int TRANSACTION_removeUceRegistrationOverrideShell = 320;
        static final int TRANSACTION_removeUceRequestDisallowedStatus = 324;
        static final int TRANSACTION_requestCellInfoUpdate = 70;
        static final int TRANSACTION_requestCellInfoUpdateWithWorkSource = 71;
        static final int TRANSACTION_requestModemActivityInfo = 160;
        static final int TRANSACTION_requestNetworkScan = 103;
        static final int TRANSACTION_requestNumberVerification = 119;
        static final int TRANSACTION_requestUserActivityNotification = 283;
        static final int TRANSACTION_resetIms = 92;
        static final int TRANSACTION_resetModemConfig = 86;
        static final int TRANSACTION_resetOtaEmergencyNumberDbFilePath = 257;
        static final int TRANSACTION_sendDeviceToDeviceMessage = 307;
        static final int TRANSACTION_sendDialerSpecialCode = 60;
        static final int TRANSACTION_sendEnvelopeWithStatus = 82;
        static final int TRANSACTION_sendThermalMitigationRequest = 291;
        static final int TRANSACTION_sendVisualVoicemailSmsForSubscriber = 59;
        static final int TRANSACTION_setActiveDeviceToDeviceTransport = 308;
        static final int TRANSACTION_setAdvancedCallingSettingEnabled = 220;
        static final int TRANSACTION_setAllowedCarriers = 172;
        static final int TRANSACTION_setAllowedNetworkTypesForReason = 107;
        static final int TRANSACTION_setAlwaysReportSignalStrength = 114;
        static final int TRANSACTION_setBoundGbaServiceOverride = 293;
        static final int TRANSACTION_setBoundImsServiceOverride = 97;
        static final int TRANSACTION_setCallComposerStatus = 7;
        static final int TRANSACTION_setCallForwarding = 183;
        static final int TRANSACTION_setCallWaitingStatus = 185;
        static final int TRANSACTION_setCapabilitiesRequestTimeout = 325;
        static final int TRANSACTION_setCarrierSingleRegistrationEnabledOverride = 306;
        static final int TRANSACTION_setCarrierTestOverride = 203;
        static final int TRANSACTION_setCdmaRoamingMode = 200;
        static final int TRANSACTION_setCdmaSubscriptionMode = 202;
        static final int TRANSACTION_setCellInfoListRate = 72;
        static final int TRANSACTION_setCepEnabled = 278;
        static final int TRANSACTION_setCrossSimCallingEnabled = 226;
        static final int TRANSACTION_setDataActivationState = 48;
        static final int TRANSACTION_setDataEnabledForReason = 111;
        static final int TRANSACTION_setDataRoamingEnabled = 198;
        static final int TRANSACTION_setDeviceSingleRegistrationEnabledOverride = 304;
        static final int TRANSACTION_setDeviceToDeviceForceEnabled = 309;
        static final int TRANSACTION_setDeviceUceEnabled = 318;
        static final int TRANSACTION_setForbiddenPlmns = 190;
        static final int TRANSACTION_setGbaReleaseTimeOverride = 295;
        static final int TRANSACTION_setIccLockEnabled = 281;
        static final int TRANSACTION_setImsFeatureValidationOverride = 311;
        static final int TRANSACTION_setImsProvisioningInt = 249;
        static final int TRANSACTION_setImsProvisioningStatusForCapability = 241;
        static final int TRANSACTION_setImsProvisioningString = 250;
        static final int TRANSACTION_setImsRegistrationState = 116;
        static final int TRANSACTION_setLine1NumberForDisplayForSubscriber = 125;
        static final int TRANSACTION_setMobileDataPolicyEnabled = 276;
        static final int TRANSACTION_setMultiSimCarrierRestriction = 259;
        static final int TRANSACTION_setNetworkSelectionModeAutomatic = 101;
        static final int TRANSACTION_setNetworkSelectionModeManual = 105;
        static final int TRANSACTION_setNrDualConnectivityState = 288;
        static final int TRANSACTION_setOperatorBrandOverride = 130;
        static final int TRANSACTION_setRadio = 18;
        static final int TRANSACTION_setRadioForSubscriber = 19;
        static final int TRANSACTION_setRadioPower = 20;
        static final int TRANSACTION_setRcsClientConfiguration = 297;
        static final int TRANSACTION_setRcsProvisioningStatusForCapability = 244;
        static final int TRANSACTION_setRcsSingleRegistrationTestModeEnabled = 302;
        static final int TRANSACTION_setRoamingOverride = 131;
        static final int TRANSACTION_setRttCapabilitySetting = 234;
        static final int TRANSACTION_setSignalStrengthUpdateRequest = 326;
        static final int TRANSACTION_setSimPowerStateForSlot = 187;
        static final int TRANSACTION_setSimPowerStateForSlotWithCallback = 188;
        static final int TRANSACTION_setSystemSelectionChannels = 270;
        static final int TRANSACTION_setVoWiFiModeSetting = 231;
        static final int TRANSACTION_setVoWiFiNonPersistent = 229;
        static final int TRANSACTION_setVoWiFiRoamingModeSetting = 233;
        static final int TRANSACTION_setVoWiFiRoamingSettingEnabled = 228;
        static final int TRANSACTION_setVoWiFiSettingEnabled = 224;
        static final int TRANSACTION_setVoiceActivationState = 47;
        static final int TRANSACTION_setVoiceMailNumber = 46;
        static final int TRANSACTION_setVoicemailRingtoneUri = 163;
        static final int TRANSACTION_setVoicemailVibrationEnabled = 165;
        static final int TRANSACTION_setVtSettingEnabled = 222;
        static final int TRANSACTION_shutdownMobileRadios = 134;
        static final int TRANSACTION_startEmergencyCallbackMode = 251;
        static final int TRANSACTION_stopNetworkScan = 104;
        static final int TRANSACTION_supplyPinForSubscriber = 9;
        static final int TRANSACTION_supplyPinReportResultForSubscriber = 11;
        static final int TRANSACTION_supplyPukForSubscriber = 10;
        static final int TRANSACTION_supplyPukReportResultForSubscriber = 12;
        static final int TRANSACTION_switchMultiSimConfig = 261;
        static final int TRANSACTION_switchSlots = 196;
        static final int TRANSACTION_toggleRadioOnOff = 16;
        static final int TRANSACTION_toggleRadioOnOffForSubscriber = 17;
        static final int TRANSACTION_triggerRcsReconfiguration = 301;
        static final int TRANSACTION_unregisterImsFeatureCallback = 94;
        static final int TRANSACTION_unregisterImsProvisioningChangedCallback = 240;
        static final int TRANSACTION_unregisterImsRegistrationCallback = 211;
        static final int TRANSACTION_unregisterMmTelCapabilityCallback = 215;
        static final int TRANSACTION_unregisterRcsProvisioningCallback = 300;
        static final int TRANSACTION_updateEmergencyNumberListTestMode = 252;
        static final int TRANSACTION_updateOtaEmergencyNumberDbFilePath = 256;
        static final int TRANSACTION_updateServiceLocation = 21;
        static final int TRANSACTION_updateServiceLocationWithPackageName = 22;
        static final int TRANSACTION_uploadCallComposerPicture = 136;
        static final int TRANSACTION_userActivity = 284;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITelephony asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITelephony)) {
                return new Proxy(obj);
            }
            return (ITelephony) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "dial";
                case 2:
                    return "call";
                case 3:
                    return "isRadioOn";
                case 4:
                    return "isRadioOnWithFeature";
                case 5:
                    return "isRadioOnForSubscriber";
                case 6:
                    return "isRadioOnForSubscriberWithFeature";
                case 7:
                    return "setCallComposerStatus";
                case 8:
                    return "getCallComposerStatus";
                case 9:
                    return "supplyPinForSubscriber";
                case 10:
                    return "supplyPukForSubscriber";
                case 11:
                    return "supplyPinReportResultForSubscriber";
                case 12:
                    return "supplyPukReportResultForSubscriber";
                case 13:
                    return "handlePinMmi";
                case 14:
                    return "handleUssdRequest";
                case 15:
                    return "handlePinMmiForSubscriber";
                case 16:
                    return "toggleRadioOnOff";
                case 17:
                    return "toggleRadioOnOffForSubscriber";
                case 18:
                    return "setRadio";
                case 19:
                    return "setRadioForSubscriber";
                case 20:
                    return "setRadioPower";
                case 21:
                    return "updateServiceLocation";
                case 22:
                    return "updateServiceLocationWithPackageName";
                case 23:
                    return "enableLocationUpdates";
                case 24:
                    return "disableLocationUpdates";
                case 25:
                    return "enableDataConnectivity";
                case 26:
                    return "disableDataConnectivity";
                case 27:
                    return "isDataConnectivityPossible";
                case 28:
                    return "getCellLocation";
                case 29:
                    return "getNetworkCountryIsoForPhone";
                case 30:
                    return "getNeighboringCellInfo";
                case 31:
                    return "getCallState";
                case 32:
                    return "getCallStateForSubscription";
                case 33:
                    return "getDataActivity";
                case 34:
                    return "getDataActivityForSubId";
                case 35:
                    return "getDataState";
                case 36:
                    return "getDataStateForSubId";
                case 37:
                    return "getActivePhoneType";
                case 38:
                    return "getActivePhoneTypeForSlot";
                case 39:
                    return "getCdmaEriIconIndex";
                case 40:
                    return "getCdmaEriIconIndexForSubscriber";
                case 41:
                    return "getCdmaEriIconMode";
                case 42:
                    return "getCdmaEriIconModeForSubscriber";
                case 43:
                    return "getCdmaEriText";
                case 44:
                    return "getCdmaEriTextForSubscriber";
                case 45:
                    return "needsOtaServiceProvisioning";
                case 46:
                    return "setVoiceMailNumber";
                case 47:
                    return "setVoiceActivationState";
                case 48:
                    return "setDataActivationState";
                case 49:
                    return "getVoiceActivationState";
                case 50:
                    return "getDataActivationState";
                case 51:
                    return "getVoiceMessageCountForSubscriber";
                case 52:
                    return "isConcurrentVoiceAndDataAllowed";
                case 53:
                    return "getVisualVoicemailSettings";
                case 54:
                    return "getVisualVoicemailPackageName";
                case 55:
                    return "enableVisualVoicemailSmsFilter";
                case 56:
                    return "disableVisualVoicemailSmsFilter";
                case 57:
                    return "getVisualVoicemailSmsFilterSettings";
                case 58:
                    return "getActiveVisualVoicemailSmsFilterSettings";
                case 59:
                    return "sendVisualVoicemailSmsForSubscriber";
                case 60:
                    return "sendDialerSpecialCode";
                case 61:
                    return "getNetworkTypeForSubscriber";
                case 62:
                    return "getDataNetworkType";
                case 63:
                    return "getDataNetworkTypeForSubscriber";
                case 64:
                    return "getVoiceNetworkTypeForSubscriber";
                case 65:
                    return "hasIccCard";
                case 66:
                    return "hasIccCardUsingSlotIndex";
                case 67:
                    return "getLteOnCdmaMode";
                case 68:
                    return "getLteOnCdmaModeForSubscriber";
                case 69:
                    return "getAllCellInfo";
                case 70:
                    return "requestCellInfoUpdate";
                case 71:
                    return "requestCellInfoUpdateWithWorkSource";
                case 72:
                    return "setCellInfoListRate";
                case 73:
                    return "iccOpenLogicalChannelBySlot";
                case 74:
                    return "iccOpenLogicalChannel";
                case 75:
                    return "iccCloseLogicalChannelBySlot";
                case 76:
                    return "iccCloseLogicalChannel";
                case 77:
                    return "iccTransmitApduLogicalChannelBySlot";
                case 78:
                    return "iccTransmitApduLogicalChannel";
                case 79:
                    return "iccTransmitApduBasicChannelBySlot";
                case 80:
                    return "iccTransmitApduBasicChannel";
                case 81:
                    return "iccExchangeSimIO";
                case 82:
                    return "sendEnvelopeWithStatus";
                case 83:
                    return "nvReadItem";
                case 84:
                    return "nvWriteItem";
                case 85:
                    return "nvWriteCdmaPrl";
                case 86:
                    return "resetModemConfig";
                case 87:
                    return "rebootModem";
                case 88:
                    return "getAllowedNetworkTypesBitmask";
                case 89:
                    return "isTetheringApnRequiredForSubscriber";
                case 90:
                    return "enableIms";
                case 91:
                    return "disableIms";
                case 92:
                    return "resetIms";
                case 93:
                    return "registerMmTelFeatureCallback";
                case 94:
                    return "unregisterImsFeatureCallback";
                case 95:
                    return "getImsRegistration";
                case 96:
                    return "getImsConfig";
                case 97:
                    return "setBoundImsServiceOverride";
                case 98:
                    return "clearCarrierImsServiceOverride";
                case 99:
                    return "getBoundImsServicePackage";
                case 100:
                    return "getImsMmTelFeatureState";
                case 101:
                    return "setNetworkSelectionModeAutomatic";
                case 102:
                    return "getCellNetworkScanResults";
                case 103:
                    return "requestNetworkScan";
                case 104:
                    return "stopNetworkScan";
                case 105:
                    return "setNetworkSelectionModeManual";
                case 106:
                    return "getAllowedNetworkTypesForReason";
                case 107:
                    return "setAllowedNetworkTypesForReason";
                case 108:
                    return "getDataEnabled";
                case 109:
                    return "isUserDataEnabled";
                case 110:
                    return "isDataEnabled";
                case 111:
                    return "setDataEnabledForReason";
                case 112:
                    return "isDataEnabledForReason";
                case 113:
                    return "isManualNetworkSelectionAllowed";
                case 114:
                    return "setAlwaysReportSignalStrength";
                case 115:
                    return "getPcscfAddress";
                case 116:
                    return "setImsRegistrationState";
                case 117:
                    return "getCdmaMdn";
                case 118:
                    return "getCdmaMin";
                case 119:
                    return "requestNumberVerification";
                case 120:
                    return "getCarrierPrivilegeStatus";
                case 121:
                    return "getCarrierPrivilegeStatusForUid";
                case 122:
                    return "checkCarrierPrivilegesForPackage";
                case 123:
                    return "checkCarrierPrivilegesForPackageAnyPhone";
                case 124:
                    return "getCarrierPackageNamesForIntentAndPhone";
                case 125:
                    return "setLine1NumberForDisplayForSubscriber";
                case 126:
                    return "getLine1NumberForDisplay";
                case 127:
                    return "getLine1AlphaTagForDisplay";
                case 128:
                    return "getMergedSubscriberIds";
                case 129:
                    return "getMergedImsisFromGroup";
                case 130:
                    return "setOperatorBrandOverride";
                case 131:
                    return "setRoamingOverride";
                case 132:
                    return "invokeOemRilRequestRaw";
                case 133:
                    return "needMobileRadioShutdown";
                case 134:
                    return "shutdownMobileRadios";
                case 135:
                    return "getRadioAccessFamily";
                case 136:
                    return "uploadCallComposerPicture";
                case 137:
                    return "enableVideoCalling";
                case 138:
                    return "isVideoCallingEnabled";
                case 139:
                    return "canChangeDtmfToneLength";
                case 140:
                    return "isWorldPhone";
                case 141:
                    return "isTtyModeSupported";
                case 142:
                    return "isRttSupported";
                case 143:
                    return "isHearingAidCompatibilitySupported";
                case 144:
                    return "isImsRegistered";
                case 145:
                    return "isWifiCallingAvailable";
                case 146:
                    return "isVideoTelephonyAvailable";
                case 147:
                    return "getImsRegTechnologyForMmTel";
                case 148:
                    return "getDeviceId";
                case 149:
                    return "getDeviceIdWithFeature";
                case 150:
                    return "getImeiForSlot";
                case 151:
                    return "getTypeAllocationCodeForSlot";
                case 152:
                    return "getMeidForSlot";
                case 153:
                    return "getManufacturerCodeForSlot";
                case 154:
                    return "getDeviceSoftwareVersionForSlot";
                case 155:
                    return "getSubIdForPhoneAccount";
                case 156:
                    return "getSubIdForPhoneAccountHandle";
                case 157:
                    return "getPhoneAccountHandleForSubscriptionId";
                case 158:
                    return "factoryReset";
                case 159:
                    return "getSimLocaleForSubscriber";
                case 160:
                    return "requestModemActivityInfo";
                case 161:
                    return "getServiceStateForSubscriber";
                case 162:
                    return "getVoicemailRingtoneUri";
                case 163:
                    return "setVoicemailRingtoneUri";
                case 164:
                    return "isVoicemailVibrationEnabled";
                case 165:
                    return "setVoicemailVibrationEnabled";
                case 166:
                    return "getPackagesWithCarrierPrivileges";
                case 167:
                    return "getPackagesWithCarrierPrivilegesForAllPhones";
                case 168:
                    return "getAidForAppType";
                case 169:
                    return "getEsn";
                case 170:
                    return "getCdmaPrlVersion";
                case 171:
                    return "getTelephonyHistograms";
                case 172:
                    return "setAllowedCarriers";
                case 173:
                    return "getAllowedCarriers";
                case 174:
                    return "getSubscriptionCarrierId";
                case 175:
                    return "getSubscriptionCarrierName";
                case 176:
                    return "getSubscriptionSpecificCarrierId";
                case 177:
                    return "getSubscriptionSpecificCarrierName";
                case 178:
                    return "getCarrierIdFromMccMnc";
                case 179:
                    return "carrierActionSetRadioEnabled";
                case 180:
                    return "carrierActionReportDefaultNetworkStatus";
                case 181:
                    return "carrierActionResetAll";
                case 182:
                    return "getCallForwarding";
                case 183:
                    return "setCallForwarding";
                case 184:
                    return "getCallWaitingStatus";
                case 185:
                    return "setCallWaitingStatus";
                case 186:
                    return "getClientRequestStats";
                case 187:
                    return "setSimPowerStateForSlot";
                case 188:
                    return "setSimPowerStateForSlotWithCallback";
                case 189:
                    return "getForbiddenPlmns";
                case 190:
                    return "setForbiddenPlmns";
                case 191:
                    return "getEmergencyCallbackMode";
                case 192:
                    return "getSignalStrength";
                case 193:
                    return "getCardIdForDefaultEuicc";
                case 194:
                    return "getUiccCardsInfo";
                case 195:
                    return "getUiccSlotsInfo";
                case 196:
                    return "switchSlots";
                case 197:
                    return "isDataRoamingEnabled";
                case 198:
                    return "setDataRoamingEnabled";
                case 199:
                    return "getCdmaRoamingMode";
                case 200:
                    return "setCdmaRoamingMode";
                case 201:
                    return "getCdmaSubscriptionMode";
                case 202:
                    return "setCdmaSubscriptionMode";
                case 203:
                    return "setCarrierTestOverride";
                case 204:
                    return "getCarrierIdListVersion";
                case 205:
                    return "refreshUiccProfile";
                case 206:
                    return "getNumberOfModemsWithSimultaneousDataConnections";
                case 207:
                    return "getNetworkSelectionMode";
                case 208:
                    return "isInEmergencySmsMode";
                case 209:
                    return "getRadioPowerState";
                case 210:
                    return "registerImsRegistrationCallback";
                case 211:
                    return "unregisterImsRegistrationCallback";
                case 212:
                    return "getImsMmTelRegistrationState";
                case 213:
                    return "getImsMmTelRegistrationTransportType";
                case 214:
                    return "registerMmTelCapabilityCallback";
                case 215:
                    return "unregisterMmTelCapabilityCallback";
                case 216:
                    return "isCapable";
                case 217:
                    return "isAvailable";
                case 218:
                    return "isMmTelCapabilitySupported";
                case 219:
                    return "isAdvancedCallingSettingEnabled";
                case 220:
                    return "setAdvancedCallingSettingEnabled";
                case 221:
                    return "isVtSettingEnabled";
                case 222:
                    return "setVtSettingEnabled";
                case 223:
                    return "isVoWiFiSettingEnabled";
                case 224:
                    return "setVoWiFiSettingEnabled";
                case 225:
                    return "isCrossSimCallingEnabledByUser";
                case 226:
                    return "setCrossSimCallingEnabled";
                case 227:
                    return "isVoWiFiRoamingSettingEnabled";
                case 228:
                    return "setVoWiFiRoamingSettingEnabled";
                case 229:
                    return "setVoWiFiNonPersistent";
                case 230:
                    return "getVoWiFiModeSetting";
                case 231:
                    return "setVoWiFiModeSetting";
                case 232:
                    return "getVoWiFiRoamingModeSetting";
                case 233:
                    return "setVoWiFiRoamingModeSetting";
                case 234:
                    return "setRttCapabilitySetting";
                case 235:
                    return "isTtyOverVolteEnabled";
                case 236:
                    return "getEmergencyNumberList";
                case 237:
                    return "isEmergencyNumber";
                case 238:
                    return "getCertsFromCarrierPrivilegeAccessRules";
                case 239:
                    return "registerImsProvisioningChangedCallback";
                case 240:
                    return "unregisterImsProvisioningChangedCallback";
                case 241:
                    return "setImsProvisioningStatusForCapability";
                case 242:
                    return "getImsProvisioningStatusForCapability";
                case 243:
                    return "getRcsProvisioningStatusForCapability";
                case 244:
                    return "setRcsProvisioningStatusForCapability";
                case 245:
                    return "isMmTelCapabilityProvisionedInCache";
                case 246:
                    return "cacheMmTelCapabilityProvisioning";
                case 247:
                    return "getImsProvisioningInt";
                case 248:
                    return "getImsProvisioningString";
                case 249:
                    return "setImsProvisioningInt";
                case 250:
                    return "setImsProvisioningString";
                case 251:
                    return "startEmergencyCallbackMode";
                case 252:
                    return "updateEmergencyNumberListTestMode";
                case 253:
                    return "getEmergencyNumberListTestMode";
                case 254:
                    return "getEmergencyNumberDbVersion";
                case 255:
                    return "notifyOtaEmergencyNumberDbInstalled";
                case 256:
                    return "updateOtaEmergencyNumberDbFilePath";
                case 257:
                    return "resetOtaEmergencyNumberDbFilePath";
                case 258:
                    return "enableModemForSlot";
                case 259:
                    return "setMultiSimCarrierRestriction";
                case 260:
                    return "isMultiSimSupported";
                case 261:
                    return "switchMultiSimConfig";
                case 262:
                    return "doesSwitchMultiSimConfigTriggerReboot";
                case 263:
                    return "getSlotsMapping";
                case 264:
                    return "getRadioHalVersion";
                case 265:
                    return "getCurrentPackageName";
                case 266:
                    return "isApplicationOnUicc";
                case 267:
                    return "isModemEnabledForSlot";
                case 268:
                    return "isDataEnabledForApn";
                case 269:
                    return "isApnMetered";
                case 270:
                    return "setSystemSelectionChannels";
                case 271:
                    return "getSystemSelectionChannels";
                case 272:
                    return "isMvnoMatched";
                case 273:
                    return "enqueueSmsPickResult";
                case 274:
                    return "getMmsUserAgent";
                case 275:
                    return "getMmsUAProfUrl";
                case 276:
                    return "setMobileDataPolicyEnabled";
                case 277:
                    return "isMobileDataPolicyEnabled";
                case 278:
                    return "setCepEnabled";
                case 279:
                    return "notifyRcsAutoConfigurationReceived";
                case 280:
                    return "isIccLockEnabled";
                case 281:
                    return "setIccLockEnabled";
                case 282:
                    return "changeIccLockPassword";
                case 283:
                    return "requestUserActivityNotification";
                case 284:
                    return "userActivity";
                case 285:
                    return "getManualNetworkSelectionPlmn";
                case 286:
                    return "canConnectTo5GInDsdsMode";
                case 287:
                    return "getEquivalentHomePlmns";
                case 288:
                    return "setNrDualConnectivityState";
                case 289:
                    return "isNrDualConnectivityEnabled";
                case 290:
                    return "isRadioInterfaceCapabilitySupported";
                case 291:
                    return "sendThermalMitigationRequest";
                case 292:
                    return "bootstrapAuthenticationRequest";
                case 293:
                    return "setBoundGbaServiceOverride";
                case 294:
                    return "getBoundGbaService";
                case 295:
                    return "setGbaReleaseTimeOverride";
                case 296:
                    return "getGbaReleaseTime";
                case 297:
                    return "setRcsClientConfiguration";
                case 298:
                    return "isRcsVolteSingleRegistrationCapable";
                case 299:
                    return "registerRcsProvisioningCallback";
                case 300:
                    return "unregisterRcsProvisioningCallback";
                case 301:
                    return "triggerRcsReconfiguration";
                case 302:
                    return "setRcsSingleRegistrationTestModeEnabled";
                case 303:
                    return "getRcsSingleRegistrationTestModeEnabled";
                case 304:
                    return "setDeviceSingleRegistrationEnabledOverride";
                case 305:
                    return "getDeviceSingleRegistrationEnabled";
                case 306:
                    return "setCarrierSingleRegistrationEnabledOverride";
                case 307:
                    return "sendDeviceToDeviceMessage";
                case 308:
                    return "setActiveDeviceToDeviceTransport";
                case 309:
                    return "setDeviceToDeviceForceEnabled";
                case 310:
                    return "getCarrierSingleRegistrationEnabled";
                case 311:
                    return "setImsFeatureValidationOverride";
                case 312:
                    return "getImsFeatureValidationOverride";
                case 313:
                    return "getMobileProvisioningUrl";
                case 314:
                    return "removeContactFromEab";
                case 315:
                    return "getContactFromEab";
                case 316:
                    return "getCapabilityFromEab";
                case 317:
                    return "getDeviceUceEnabled";
                case 318:
                    return "setDeviceUceEnabled";
                case 319:
                    return "addUceRegistrationOverrideShell";
                case 320:
                    return "removeUceRegistrationOverrideShell";
                case 321:
                    return "clearUceRegistrationOverrideShell";
                case 322:
                    return "getLatestRcsContactUceCapabilityShell";
                case 323:
                    return "getLastUcePidfXmlShell";
                case 324:
                    return "removeUceRequestDisallowedStatus";
                case 325:
                    return "setCapabilitiesRequestTimeout";
                case 326:
                    return "setSignalStrengthUpdateRequest";
                case 327:
                    return "clearSignalStrengthUpdateRequest";
                case 328:
                    return "getPhoneCapability";
                case 329:
                    return "prepareForUnattendedReboot";
                case 330:
                    return "getSlicingConfig";
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
            VisualVoicemailSmsFilterSettings _arg22;
            OperatorInfo _arg1;
            Intent _arg0;
            byte[] _arg12;
            PhoneAccount _arg02;
            PhoneAccountHandle _arg03;
            ResultReceiver _arg04;
            PhoneAccountHandle _arg05;
            PhoneAccountHandle _arg06;
            CarrierRestrictionRules _arg07;
            EmergencyNumber _arg13;
            ParcelFileDescriptor _arg08;
            RcsClientConfiguration _arg14;
            ResultReceiver _arg09;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    boolean _arg010 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            dial(data.readString());
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            String _arg15 = data.readString();
                            call(_arg011, _arg15);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isRadioOn = isRadioOn(data.readString());
                            reply.writeNoException();
                            reply.writeInt(isRadioOn ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            String _arg16 = data.readString();
                            boolean isRadioOnWithFeature = isRadioOnWithFeature(_arg012, _arg16);
                            reply.writeNoException();
                            reply.writeInt(isRadioOnWithFeature ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            String _arg17 = data.readString();
                            boolean isRadioOnForSubscriber = isRadioOnForSubscriber(_arg013, _arg17);
                            reply.writeNoException();
                            reply.writeInt(isRadioOnForSubscriber ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            String _arg18 = data.readString();
                            String _arg23 = data.readString();
                            boolean isRadioOnForSubscriberWithFeature = isRadioOnForSubscriberWithFeature(_arg014, _arg18, _arg23);
                            reply.writeNoException();
                            reply.writeInt(isRadioOnForSubscriberWithFeature ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            int _arg19 = data.readInt();
                            setCallComposerStatus(_arg015, _arg19);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _result = getCallComposerStatus(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            String _arg110 = data.readString();
                            boolean supplyPinForSubscriber = supplyPinForSubscriber(_arg016, _arg110);
                            reply.writeNoException();
                            reply.writeInt(supplyPinForSubscriber ? 1 : 0);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            String _arg111 = data.readString();
                            String _arg24 = data.readString();
                            boolean supplyPukForSubscriber = supplyPukForSubscriber(_arg017, _arg111, _arg24);
                            reply.writeNoException();
                            reply.writeInt(supplyPukForSubscriber ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            String _arg112 = data.readString();
                            int[] _result2 = supplyPinReportResultForSubscriber(_arg018, _arg112);
                            reply.writeNoException();
                            reply.writeIntArray(_result2);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            String _arg113 = data.readString();
                            String _arg25 = data.readString();
                            int[] _result3 = supplyPukReportResultForSubscriber(_arg019, _arg113, _arg25);
                            reply.writeNoException();
                            reply.writeIntArray(_result3);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            boolean handlePinMmi = handlePinMmi(data.readString());
                            reply.writeNoException();
                            reply.writeInt(handlePinMmi ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            String _arg114 = data.readString();
                            if (data.readInt() != 0) {
                                _arg2 = ResultReceiver.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            handleUssdRequest(_arg020, _arg114, _arg2);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            String _arg115 = data.readString();
                            boolean handlePinMmiForSubscriber = handlePinMmiForSubscriber(_arg021, _arg115);
                            reply.writeNoException();
                            reply.writeInt(handlePinMmiForSubscriber ? 1 : 0);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            toggleRadioOnOff();
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            toggleRadioOnOffForSubscriber(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            boolean radio = setRadio(_arg010);
                            reply.writeNoException();
                            reply.writeInt(radio ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg022 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            boolean radioForSubscriber = setRadioForSubscriber(_arg022, _arg010);
                            reply.writeNoException();
                            reply.writeInt(radioForSubscriber ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            boolean radioPower = setRadioPower(_arg010);
                            reply.writeNoException();
                            reply.writeInt(radioPower ? 1 : 0);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            updateServiceLocation();
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            updateServiceLocationWithPackageName(data.readString());
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            enableLocationUpdates();
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            disableLocationUpdates();
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            boolean enableDataConnectivity = enableDataConnectivity();
                            reply.writeNoException();
                            reply.writeInt(enableDataConnectivity ? 1 : 0);
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            boolean disableDataConnectivity = disableDataConnectivity();
                            reply.writeNoException();
                            reply.writeInt(disableDataConnectivity ? 1 : 0);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isDataConnectivityPossible = isDataConnectivityPossible(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isDataConnectivityPossible ? 1 : 0);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            String _arg116 = data.readString();
                            CellIdentity _result4 = getCellLocation(_arg023, _arg116);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _result5 = getNetworkCountryIsoForPhone(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            String _arg117 = data.readString();
                            List<NeighboringCellInfo> _result6 = getNeighboringCellInfo(_arg024, _arg117);
                            reply.writeNoException();
                            reply.writeTypedList(_result6);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            int _result7 = getCallState();
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            String _arg118 = data.readString();
                            String _arg26 = data.readString();
                            int _result8 = getCallStateForSubscription(_arg025, _arg118, _arg26);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            int _result9 = getDataActivity();
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            int _result10 = getDataActivityForSubId(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            int _result11 = getDataState();
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            int _result12 = getDataStateForSubId(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            int _result13 = getActivePhoneType();
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            int _result14 = getActivePhoneTypeForSlot(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg026 = data.readString();
                            String _arg119 = data.readString();
                            int _result15 = getCdmaEriIconIndex(_arg026, _arg119);
                            reply.writeNoException();
                            reply.writeInt(_result15);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            String _arg120 = data.readString();
                            String _arg27 = data.readString();
                            int _result16 = getCdmaEriIconIndexForSubscriber(_arg027, _arg120, _arg27);
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            String _arg121 = data.readString();
                            int _result17 = getCdmaEriIconMode(_arg028, _arg121);
                            reply.writeNoException();
                            reply.writeInt(_result17);
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            String _arg122 = data.readString();
                            String _arg28 = data.readString();
                            int _result18 = getCdmaEriIconModeForSubscriber(_arg029, _arg122, _arg28);
                            reply.writeNoException();
                            reply.writeInt(_result18);
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            String _arg123 = data.readString();
                            String _result19 = getCdmaEriText(_arg030, _arg123);
                            reply.writeNoException();
                            reply.writeString(_result19);
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg031 = data.readInt();
                            String _arg124 = data.readString();
                            String _arg29 = data.readString();
                            String _result20 = getCdmaEriTextForSubscriber(_arg031, _arg124, _arg29);
                            reply.writeNoException();
                            reply.writeString(_result20);
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            boolean needsOtaServiceProvisioning = needsOtaServiceProvisioning();
                            reply.writeNoException();
                            reply.writeInt(needsOtaServiceProvisioning ? 1 : 0);
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg032 = data.readInt();
                            String _arg125 = data.readString();
                            String _arg210 = data.readString();
                            boolean voiceMailNumber = setVoiceMailNumber(_arg032, _arg125, _arg210);
                            reply.writeNoException();
                            reply.writeInt(voiceMailNumber ? 1 : 0);
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg033 = data.readInt();
                            int _arg126 = data.readInt();
                            setVoiceActivationState(_arg033, _arg126);
                            reply.writeNoException();
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg034 = data.readInt();
                            int _arg127 = data.readInt();
                            setDataActivationState(_arg034, _arg127);
                            reply.writeNoException();
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg035 = data.readInt();
                            String _arg128 = data.readString();
                            int _result21 = getVoiceActivationState(_arg035, _arg128);
                            reply.writeNoException();
                            reply.writeInt(_result21);
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg036 = data.readInt();
                            String _arg129 = data.readString();
                            int _result22 = getDataActivationState(_arg036, _arg129);
                            reply.writeNoException();
                            reply.writeInt(_result22);
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg037 = data.readInt();
                            String _arg130 = data.readString();
                            String _arg211 = data.readString();
                            int _result23 = getVoiceMessageCountForSubscriber(_arg037, _arg130, _arg211);
                            reply.writeNoException();
                            reply.writeInt(_result23);
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isConcurrentVoiceAndDataAllowed = isConcurrentVoiceAndDataAllowed(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isConcurrentVoiceAndDataAllowed ? 1 : 0);
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            int _arg131 = data.readInt();
                            Bundle _result24 = getVisualVoicemailSettings(_arg038, _arg131);
                            reply.writeNoException();
                            if (_result24 != null) {
                                reply.writeInt(1);
                                _result24.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg039 = data.readString();
                            String _arg132 = data.readString();
                            int _arg212 = data.readInt();
                            String _result25 = getVisualVoicemailPackageName(_arg039, _arg132, _arg212);
                            reply.writeNoException();
                            reply.writeString(_result25);
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg040 = data.readString();
                            int _arg133 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            enableVisualVoicemailSmsFilter(_arg040, _arg133, _arg22);
                            reply.writeNoException();
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            int _arg134 = data.readInt();
                            disableVisualVoicemailSmsFilter(_arg041, _arg134);
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            int _arg135 = data.readInt();
                            VisualVoicemailSmsFilterSettings _result26 = getVisualVoicemailSmsFilterSettings(_arg042, _arg135);
                            reply.writeNoException();
                            if (_result26 != null) {
                                reply.writeInt(1);
                                _result26.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            VisualVoicemailSmsFilterSettings _result27 = getActiveVisualVoicemailSmsFilterSettings(data.readInt());
                            reply.writeNoException();
                            if (_result27 != null) {
                                reply.writeInt(1);
                                _result27.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 59:
                            return onTransact$sendVisualVoicemailSmsForSubscriber$(data, reply);
                        case 60:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg043 = data.readString();
                            String _arg136 = data.readString();
                            sendDialerSpecialCode(_arg043, _arg136);
                            reply.writeNoException();
                            return true;
                        case 61:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg044 = data.readInt();
                            String _arg137 = data.readString();
                            String _arg213 = data.readString();
                            int _result28 = getNetworkTypeForSubscriber(_arg044, _arg137, _arg213);
                            reply.writeNoException();
                            reply.writeInt(_result28);
                            return true;
                        case 62:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg045 = data.readString();
                            String _arg138 = data.readString();
                            int _result29 = getDataNetworkType(_arg045, _arg138);
                            reply.writeNoException();
                            reply.writeInt(_result29);
                            return true;
                        case 63:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg046 = data.readInt();
                            String _arg139 = data.readString();
                            String _arg214 = data.readString();
                            int _result30 = getDataNetworkTypeForSubscriber(_arg046, _arg139, _arg214);
                            reply.writeNoException();
                            reply.writeInt(_result30);
                            return true;
                        case 64:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg047 = data.readInt();
                            String _arg140 = data.readString();
                            String _arg215 = data.readString();
                            int _result31 = getVoiceNetworkTypeForSubscriber(_arg047, _arg140, _arg215);
                            reply.writeNoException();
                            reply.writeInt(_result31);
                            return true;
                        case 65:
                            data.enforceInterface(DESCRIPTOR);
                            boolean hasIccCard = hasIccCard();
                            reply.writeNoException();
                            reply.writeInt(hasIccCard ? 1 : 0);
                            return true;
                        case 66:
                            data.enforceInterface(DESCRIPTOR);
                            boolean hasIccCardUsingSlotIndex = hasIccCardUsingSlotIndex(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(hasIccCardUsingSlotIndex ? 1 : 0);
                            return true;
                        case 67:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg048 = data.readString();
                            String _arg141 = data.readString();
                            int _result32 = getLteOnCdmaMode(_arg048, _arg141);
                            reply.writeNoException();
                            reply.writeInt(_result32);
                            return true;
                        case 68:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg049 = data.readInt();
                            String _arg142 = data.readString();
                            String _arg216 = data.readString();
                            int _result33 = getLteOnCdmaModeForSubscriber(_arg049, _arg142, _arg216);
                            reply.writeNoException();
                            reply.writeInt(_result33);
                            return true;
                        case 69:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg050 = data.readString();
                            String _arg143 = data.readString();
                            List<CellInfo> _result34 = getAllCellInfo(_arg050, _arg143);
                            reply.writeNoException();
                            reply.writeTypedList(_result34);
                            return true;
                        case 70:
                            return onTransact$requestCellInfoUpdate$(data, reply);
                        case 71:
                            return onTransact$requestCellInfoUpdateWithWorkSource$(data, reply);
                        case 72:
                            data.enforceInterface(DESCRIPTOR);
                            setCellInfoListRate(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 73:
                            return onTransact$iccOpenLogicalChannelBySlot$(data, reply);
                        case 74:
                            return onTransact$iccOpenLogicalChannel$(data, reply);
                        case 75:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg051 = data.readInt();
                            int _arg144 = data.readInt();
                            boolean iccCloseLogicalChannelBySlot = iccCloseLogicalChannelBySlot(_arg051, _arg144);
                            reply.writeNoException();
                            reply.writeInt(iccCloseLogicalChannelBySlot ? 1 : 0);
                            return true;
                        case 76:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg052 = data.readInt();
                            int _arg145 = data.readInt();
                            boolean iccCloseLogicalChannel = iccCloseLogicalChannel(_arg052, _arg145);
                            reply.writeNoException();
                            reply.writeInt(iccCloseLogicalChannel ? 1 : 0);
                            return true;
                        case 77:
                            return onTransact$iccTransmitApduLogicalChannelBySlot$(data, reply);
                        case 78:
                            return onTransact$iccTransmitApduLogicalChannel$(data, reply);
                        case 79:
                            return onTransact$iccTransmitApduBasicChannelBySlot$(data, reply);
                        case 80:
                            return onTransact$iccTransmitApduBasicChannel$(data, reply);
                        case 81:
                            return onTransact$iccExchangeSimIO$(data, reply);
                        case 82:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg053 = data.readInt();
                            String _arg146 = data.readString();
                            String _result35 = sendEnvelopeWithStatus(_arg053, _arg146);
                            reply.writeNoException();
                            reply.writeString(_result35);
                            return true;
                        case 83:
                            data.enforceInterface(DESCRIPTOR);
                            String _result36 = nvReadItem(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result36);
                            return true;
                        case 84:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg054 = data.readInt();
                            String _arg147 = data.readString();
                            boolean nvWriteItem = nvWriteItem(_arg054, _arg147);
                            reply.writeNoException();
                            reply.writeInt(nvWriteItem ? 1 : 0);
                            return true;
                        case 85:
                            data.enforceInterface(DESCRIPTOR);
                            boolean nvWriteCdmaPrl = nvWriteCdmaPrl(data.createByteArray());
                            reply.writeNoException();
                            reply.writeInt(nvWriteCdmaPrl ? 1 : 0);
                            return true;
                        case 86:
                            data.enforceInterface(DESCRIPTOR);
                            boolean resetModemConfig = resetModemConfig(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(resetModemConfig ? 1 : 0);
                            return true;
                        case 87:
                            data.enforceInterface(DESCRIPTOR);
                            boolean rebootModem = rebootModem(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(rebootModem ? 1 : 0);
                            return true;
                        case 88:
                            data.enforceInterface(DESCRIPTOR);
                            int _result37 = getAllowedNetworkTypesBitmask(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result37);
                            return true;
                        case 89:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isTetheringApnRequiredForSubscriber = isTetheringApnRequiredForSubscriber(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isTetheringApnRequiredForSubscriber ? 1 : 0);
                            return true;
                        case 90:
                            data.enforceInterface(DESCRIPTOR);
                            enableIms(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 91:
                            data.enforceInterface(DESCRIPTOR);
                            disableIms(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 92:
                            data.enforceInterface(DESCRIPTOR);
                            resetIms(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 93:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg055 = data.readInt();
                            IImsServiceFeatureCallback _arg148 = IImsServiceFeatureCallback.Stub.asInterface(data.readStrongBinder());
                            registerMmTelFeatureCallback(_arg055, _arg148);
                            reply.writeNoException();
                            return true;
                        case 94:
                            data.enforceInterface(DESCRIPTOR);
                            unregisterImsFeatureCallback(IImsServiceFeatureCallback.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 95:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg056 = data.readInt();
                            int _arg149 = data.readInt();
                            IImsRegistration _result38 = getImsRegistration(_arg056, _arg149);
                            reply.writeNoException();
                            if (_result38 != null) {
                                iBinder = _result38.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 96:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg057 = data.readInt();
                            int _arg150 = data.readInt();
                            IImsConfig _result39 = getImsConfig(_arg057, _arg150);
                            reply.writeNoException();
                            if (_result39 != null) {
                                iBinder = _result39.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 97:
                            return onTransact$setBoundImsServiceOverride$(data, reply);
                        case 98:
                            data.enforceInterface(DESCRIPTOR);
                            boolean clearCarrierImsServiceOverride = clearCarrierImsServiceOverride(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(clearCarrierImsServiceOverride ? 1 : 0);
                            return true;
                        case 99:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg058 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            int _arg217 = data.readInt();
                            String _result40 = getBoundImsServicePackage(_arg058, _arg010, _arg217);
                            reply.writeNoException();
                            reply.writeString(_result40);
                            return true;
                        case 100:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg059 = data.readInt();
                            IIntegerConsumer _arg151 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
                            getImsMmTelFeatureState(_arg059, _arg151);
                            reply.writeNoException();
                            return true;
                        case 101:
                            data.enforceInterface(DESCRIPTOR);
                            setNetworkSelectionModeAutomatic(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 102:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg060 = data.readInt();
                            String _arg152 = data.readString();
                            String _arg218 = data.readString();
                            CellNetworkScanResult _result41 = getCellNetworkScanResults(_arg060, _arg152, _arg218);
                            reply.writeNoException();
                            if (_result41 != null) {
                                reply.writeInt(1);
                                _result41.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 103:
                            return onTransact$requestNetworkScan$(data, reply);
                        case 104:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg061 = data.readInt();
                            int _arg153 = data.readInt();
                            stopNetworkScan(_arg061, _arg153);
                            reply.writeNoException();
                            return true;
                        case 105:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg062 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = OperatorInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            boolean networkSelectionModeManual = setNetworkSelectionModeManual(_arg062, _arg1, _arg010);
                            reply.writeNoException();
                            reply.writeInt(networkSelectionModeManual ? 1 : 0);
                            return true;
                        case 106:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg063 = data.readInt();
                            int _arg154 = data.readInt();
                            long _result42 = getAllowedNetworkTypesForReason(_arg063, _arg154);
                            reply.writeNoException();
                            reply.writeLong(_result42);
                            return true;
                        case 107:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg064 = data.readInt();
                            int _arg155 = data.readInt();
                            long _arg219 = data.readLong();
                            boolean allowedNetworkTypesForReason = setAllowedNetworkTypesForReason(_arg064, _arg155, _arg219);
                            reply.writeNoException();
                            reply.writeInt(allowedNetworkTypesForReason ? 1 : 0);
                            return true;
                        case 108:
                            data.enforceInterface(DESCRIPTOR);
                            boolean dataEnabled = getDataEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(dataEnabled ? 1 : 0);
                            return true;
                        case 109:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isUserDataEnabled = isUserDataEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isUserDataEnabled ? 1 : 0);
                            return true;
                        case 110:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isDataEnabled = isDataEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isDataEnabled ? 1 : 0);
                            return true;
                        case 111:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg065 = data.readInt();
                            int _arg156 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setDataEnabledForReason(_arg065, _arg156, _arg010);
                            reply.writeNoException();
                            return true;
                        case 112:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg066 = data.readInt();
                            int _arg157 = data.readInt();
                            boolean isDataEnabledForReason = isDataEnabledForReason(_arg066, _arg157);
                            reply.writeNoException();
                            reply.writeInt(isDataEnabledForReason ? 1 : 0);
                            return true;
                        case 113:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isManualNetworkSelectionAllowed = isManualNetworkSelectionAllowed(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isManualNetworkSelectionAllowed ? 1 : 0);
                            return true;
                        case 114:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg067 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setAlwaysReportSignalStrength(_arg067, _arg010);
                            reply.writeNoException();
                            return true;
                        case 115:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg068 = data.readString();
                            String _arg158 = data.readString();
                            String _arg220 = data.readString();
                            String[] _result43 = getPcscfAddress(_arg068, _arg158, _arg220);
                            reply.writeNoException();
                            reply.writeStringArray(_result43);
                            return true;
                        case 116:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setImsRegistrationState(_arg010);
                            reply.writeNoException();
                            return true;
                        case 117:
                            data.enforceInterface(DESCRIPTOR);
                            String _result44 = getCdmaMdn(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result44);
                            return true;
                        case 118:
                            data.enforceInterface(DESCRIPTOR);
                            String _result45 = getCdmaMin(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result45);
                            return true;
                        case 119:
                            return onTransact$requestNumberVerification$(data, reply);
                        case 120:
                            data.enforceInterface(DESCRIPTOR);
                            int _result46 = getCarrierPrivilegeStatus(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result46);
                            return true;
                        case 121:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg069 = data.readInt();
                            int _arg159 = data.readInt();
                            int _result47 = getCarrierPrivilegeStatusForUid(_arg069, _arg159);
                            reply.writeNoException();
                            reply.writeInt(_result47);
                            return true;
                        case 122:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg070 = data.readInt();
                            String _arg160 = data.readString();
                            int _result48 = checkCarrierPrivilegesForPackage(_arg070, _arg160);
                            reply.writeNoException();
                            reply.writeInt(_result48);
                            return true;
                        case 123:
                            data.enforceInterface(DESCRIPTOR);
                            int _result49 = checkCarrierPrivilegesForPackageAnyPhone(data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result49);
                            return true;
                        case 124:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Intent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg161 = data.readInt();
                            List<String> _result50 = getCarrierPackageNamesForIntentAndPhone(_arg0, _arg161);
                            reply.writeNoException();
                            reply.writeStringList(_result50);
                            return true;
                        case 125:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg071 = data.readInt();
                            String _arg162 = data.readString();
                            String _arg221 = data.readString();
                            boolean line1NumberForDisplayForSubscriber = setLine1NumberForDisplayForSubscriber(_arg071, _arg162, _arg221);
                            reply.writeNoException();
                            reply.writeInt(line1NumberForDisplayForSubscriber ? 1 : 0);
                            return true;
                        case 126:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg072 = data.readInt();
                            String _arg163 = data.readString();
                            String _arg222 = data.readString();
                            String _result51 = getLine1NumberForDisplay(_arg072, _arg163, _arg222);
                            reply.writeNoException();
                            reply.writeString(_result51);
                            return true;
                        case 127:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg073 = data.readInt();
                            String _arg164 = data.readString();
                            String _arg223 = data.readString();
                            String _result52 = getLine1AlphaTagForDisplay(_arg073, _arg164, _arg223);
                            reply.writeNoException();
                            reply.writeString(_result52);
                            return true;
                        case 128:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg074 = data.readInt();
                            String _arg165 = data.readString();
                            String _arg224 = data.readString();
                            String[] _result53 = getMergedSubscriberIds(_arg074, _arg165, _arg224);
                            reply.writeNoException();
                            reply.writeStringArray(_result53);
                            return true;
                        case 129:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg075 = data.readInt();
                            String _arg166 = data.readString();
                            String[] _result54 = getMergedImsisFromGroup(_arg075, _arg166);
                            reply.writeNoException();
                            reply.writeStringArray(_result54);
                            return true;
                        case 130:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg076 = data.readInt();
                            String _arg167 = data.readString();
                            boolean operatorBrandOverride = setOperatorBrandOverride(_arg076, _arg167);
                            reply.writeNoException();
                            reply.writeInt(operatorBrandOverride ? 1 : 0);
                            return true;
                        case 131:
                            return onTransact$setRoamingOverride$(data, reply);
                        case 132:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _arg077 = data.createByteArray();
                            int _arg1_length = data.readInt();
                            if (_arg1_length < 0) {
                                _arg12 = null;
                            } else {
                                _arg12 = new byte[_arg1_length];
                            }
                            int _result55 = invokeOemRilRequestRaw(_arg077, _arg12);
                            reply.writeNoException();
                            reply.writeInt(_result55);
                            reply.writeByteArray(_arg12);
                            return true;
                        case 133:
                            data.enforceInterface(DESCRIPTOR);
                            boolean needMobileRadioShutdown = needMobileRadioShutdown();
                            reply.writeNoException();
                            reply.writeInt(needMobileRadioShutdown ? 1 : 0);
                            return true;
                        case 134:
                            data.enforceInterface(DESCRIPTOR);
                            shutdownMobileRadios();
                            reply.writeNoException();
                            return true;
                        case 135:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg078 = data.readInt();
                            String _arg168 = data.readString();
                            int _result56 = getRadioAccessFamily(_arg078, _arg168);
                            reply.writeNoException();
                            reply.writeInt(_result56);
                            return true;
                        case 136:
                            return onTransact$uploadCallComposerPicture$(data, reply);
                        case 137:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            enableVideoCalling(_arg010);
                            reply.writeNoException();
                            return true;
                        case 138:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg079 = data.readString();
                            String _arg169 = data.readString();
                            boolean isVideoCallingEnabled = isVideoCallingEnabled(_arg079, _arg169);
                            reply.writeNoException();
                            reply.writeInt(isVideoCallingEnabled ? 1 : 0);
                            return true;
                        case 139:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg080 = data.readInt();
                            String _arg170 = data.readString();
                            String _arg225 = data.readString();
                            boolean canChangeDtmfToneLength = canChangeDtmfToneLength(_arg080, _arg170, _arg225);
                            reply.writeNoException();
                            reply.writeInt(canChangeDtmfToneLength ? 1 : 0);
                            return true;
                        case 140:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg081 = data.readInt();
                            String _arg171 = data.readString();
                            String _arg226 = data.readString();
                            boolean isWorldPhone = isWorldPhone(_arg081, _arg171, _arg226);
                            reply.writeNoException();
                            reply.writeInt(isWorldPhone ? 1 : 0);
                            return true;
                        case 141:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isTtyModeSupported = isTtyModeSupported();
                            reply.writeNoException();
                            reply.writeInt(isTtyModeSupported ? 1 : 0);
                            return true;
                        case 142:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isRttSupported = isRttSupported(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isRttSupported ? 1 : 0);
                            return true;
                        case 143:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isHearingAidCompatibilitySupported = isHearingAidCompatibilitySupported();
                            reply.writeNoException();
                            reply.writeInt(isHearingAidCompatibilitySupported ? 1 : 0);
                            return true;
                        case 144:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isImsRegistered = isImsRegistered(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isImsRegistered ? 1 : 0);
                            return true;
                        case 145:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isWifiCallingAvailable = isWifiCallingAvailable(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isWifiCallingAvailable ? 1 : 0);
                            return true;
                        case 146:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isVideoTelephonyAvailable = isVideoTelephonyAvailable(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isVideoTelephonyAvailable ? 1 : 0);
                            return true;
                        case 147:
                            data.enforceInterface(DESCRIPTOR);
                            int _result57 = getImsRegTechnologyForMmTel(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result57);
                            return true;
                        case 148:
                            data.enforceInterface(DESCRIPTOR);
                            String _result58 = getDeviceId(data.readString());
                            reply.writeNoException();
                            reply.writeString(_result58);
                            return true;
                        case 149:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg082 = data.readString();
                            String _arg172 = data.readString();
                            String _result59 = getDeviceIdWithFeature(_arg082, _arg172);
                            reply.writeNoException();
                            reply.writeString(_result59);
                            return true;
                        case 150:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg083 = data.readInt();
                            String _arg173 = data.readString();
                            String _arg227 = data.readString();
                            String _result60 = getImeiForSlot(_arg083, _arg173, _arg227);
                            reply.writeNoException();
                            reply.writeString(_result60);
                            return true;
                        case 151:
                            data.enforceInterface(DESCRIPTOR);
                            String _result61 = getTypeAllocationCodeForSlot(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result61);
                            return true;
                        case 152:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg084 = data.readInt();
                            String _arg174 = data.readString();
                            String _arg228 = data.readString();
                            String _result62 = getMeidForSlot(_arg084, _arg174, _arg228);
                            reply.writeNoException();
                            reply.writeString(_result62);
                            return true;
                        case 153:
                            data.enforceInterface(DESCRIPTOR);
                            String _result63 = getManufacturerCodeForSlot(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result63);
                            return true;
                        case 154:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg085 = data.readInt();
                            String _arg175 = data.readString();
                            String _arg229 = data.readString();
                            String _result64 = getDeviceSoftwareVersionForSlot(_arg085, _arg175, _arg229);
                            reply.writeNoException();
                            reply.writeString(_result64);
                            return true;
                        case 155:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = PhoneAccount.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int _result65 = getSubIdForPhoneAccount(_arg02);
                            reply.writeNoException();
                            reply.writeInt(_result65);
                            return true;
                        case 156:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            String _arg176 = data.readString();
                            String _arg230 = data.readString();
                            int _result66 = getSubIdForPhoneAccountHandle(_arg03, _arg176, _arg230);
                            reply.writeNoException();
                            reply.writeInt(_result66);
                            return true;
                        case 157:
                            data.enforceInterface(DESCRIPTOR);
                            PhoneAccountHandle _result67 = getPhoneAccountHandleForSubscriptionId(data.readInt());
                            reply.writeNoException();
                            if (_result67 != null) {
                                reply.writeInt(1);
                                _result67.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 158:
                            data.enforceInterface(DESCRIPTOR);
                            factoryReset(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 159:
                            data.enforceInterface(DESCRIPTOR);
                            String _result68 = getSimLocaleForSubscriber(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result68);
                            return true;
                        case 160:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ResultReceiver.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            requestModemActivityInfo(_arg04);
                            return true;
                        case 161:
                            return onTransact$getServiceStateForSubscriber$(data, reply);
                        case 162:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            Uri _result69 = getVoicemailRingtoneUri(_arg05);
                            reply.writeNoException();
                            if (_result69 != null) {
                                reply.writeInt(1);
                                _result69.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 163:
                            return onTransact$setVoicemailRingtoneUri$(data, reply);
                        case 164:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = PhoneAccountHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            boolean isVoicemailVibrationEnabled = isVoicemailVibrationEnabled(_arg06);
                            reply.writeNoException();
                            reply.writeInt(isVoicemailVibrationEnabled ? 1 : 0);
                            return true;
                        case 165:
                            return onTransact$setVoicemailVibrationEnabled$(data, reply);
                        case 166:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _result70 = getPackagesWithCarrierPrivileges(data.readInt());
                            reply.writeNoException();
                            reply.writeStringList(_result70);
                            return true;
                        case 167:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _result71 = getPackagesWithCarrierPrivilegesForAllPhones();
                            reply.writeNoException();
                            reply.writeStringList(_result71);
                            return true;
                        case 168:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg086 = data.readInt();
                            int _arg177 = data.readInt();
                            String _result72 = getAidForAppType(_arg086, _arg177);
                            reply.writeNoException();
                            reply.writeString(_result72);
                            return true;
                        case 169:
                            data.enforceInterface(DESCRIPTOR);
                            String _result73 = getEsn(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result73);
                            return true;
                        case 170:
                            data.enforceInterface(DESCRIPTOR);
                            String _result74 = getCdmaPrlVersion(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result74);
                            return true;
                        case 171:
                            data.enforceInterface(DESCRIPTOR);
                            List<TelephonyHistogram> _result75 = getTelephonyHistograms();
                            reply.writeNoException();
                            reply.writeTypedList(_result75);
                            return true;
                        case 172:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = CarrierRestrictionRules.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            int _result76 = setAllowedCarriers(_arg07);
                            reply.writeNoException();
                            reply.writeInt(_result76);
                            return true;
                        case 173:
                            data.enforceInterface(DESCRIPTOR);
                            CarrierRestrictionRules _result77 = getAllowedCarriers();
                            reply.writeNoException();
                            if (_result77 != null) {
                                reply.writeInt(1);
                                _result77.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 174:
                            data.enforceInterface(DESCRIPTOR);
                            int _result78 = getSubscriptionCarrierId(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result78);
                            return true;
                        case 175:
                            data.enforceInterface(DESCRIPTOR);
                            String _result79 = getSubscriptionCarrierName(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result79);
                            return true;
                        case 176:
                            data.enforceInterface(DESCRIPTOR);
                            int _result80 = getSubscriptionSpecificCarrierId(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result80);
                            return true;
                        case 177:
                            data.enforceInterface(DESCRIPTOR);
                            String _result81 = getSubscriptionSpecificCarrierName(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result81);
                            return true;
                        case 178:
                            return onTransact$getCarrierIdFromMccMnc$(data, reply);
                        case 179:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg087 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            carrierActionSetRadioEnabled(_arg087, _arg010);
                            reply.writeNoException();
                            return true;
                        case 180:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg088 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            carrierActionReportDefaultNetworkStatus(_arg088, _arg010);
                            reply.writeNoException();
                            return true;
                        case 181:
                            data.enforceInterface(DESCRIPTOR);
                            carrierActionResetAll(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 182:
                            return onTransact$getCallForwarding$(data, reply);
                        case 183:
                            return onTransact$setCallForwarding$(data, reply);
                        case 184:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg089 = data.readInt();
                            IIntegerConsumer _arg178 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
                            getCallWaitingStatus(_arg089, _arg178);
                            reply.writeNoException();
                            return true;
                        case 185:
                            return onTransact$setCallWaitingStatus$(data, reply);
                        case 186:
                            return onTransact$getClientRequestStats$(data, reply);
                        case 187:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg090 = data.readInt();
                            int _arg179 = data.readInt();
                            setSimPowerStateForSlot(_arg090, _arg179);
                            reply.writeNoException();
                            return true;
                        case 188:
                            return onTransact$setSimPowerStateForSlotWithCallback$(data, reply);
                        case 189:
                            return onTransact$getForbiddenPlmns$(data, reply);
                        case 190:
                            return onTransact$setForbiddenPlmns$(data, reply);
                        case 191:
                            data.enforceInterface(DESCRIPTOR);
                            boolean emergencyCallbackMode = getEmergencyCallbackMode(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(emergencyCallbackMode ? 1 : 0);
                            return true;
                        case 192:
                            data.enforceInterface(DESCRIPTOR);
                            SignalStrength _result82 = getSignalStrength(data.readInt());
                            reply.writeNoException();
                            if (_result82 != null) {
                                reply.writeInt(1);
                                _result82.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 193:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg091 = data.readInt();
                            String _arg180 = data.readString();
                            int _result83 = getCardIdForDefaultEuicc(_arg091, _arg180);
                            reply.writeNoException();
                            reply.writeInt(_result83);
                            return true;
                        case 194:
                            data.enforceInterface(DESCRIPTOR);
                            List<UiccCardInfo> _result84 = getUiccCardsInfo(data.readString());
                            reply.writeNoException();
                            reply.writeTypedList(_result84);
                            return true;
                        case 195:
                            data.enforceInterface(DESCRIPTOR);
                            UiccSlotInfo[] _result85 = getUiccSlotsInfo();
                            reply.writeNoException();
                            reply.writeTypedArray(_result85, 1);
                            return true;
                        case 196:
                            data.enforceInterface(DESCRIPTOR);
                            boolean switchSlots = switchSlots(data.createIntArray());
                            reply.writeNoException();
                            reply.writeInt(switchSlots ? 1 : 0);
                            return true;
                        case 197:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isDataRoamingEnabled = isDataRoamingEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isDataRoamingEnabled ? 1 : 0);
                            return true;
                        case 198:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg092 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setDataRoamingEnabled(_arg092, _arg010);
                            reply.writeNoException();
                            return true;
                        case 199:
                            data.enforceInterface(DESCRIPTOR);
                            int _result86 = getCdmaRoamingMode(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result86);
                            return true;
                        case 200:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg093 = data.readInt();
                            int _arg181 = data.readInt();
                            boolean cdmaRoamingMode = setCdmaRoamingMode(_arg093, _arg181);
                            reply.writeNoException();
                            reply.writeInt(cdmaRoamingMode ? 1 : 0);
                            return true;
                        case 201:
                            data.enforceInterface(DESCRIPTOR);
                            int _result87 = getCdmaSubscriptionMode(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result87);
                            return true;
                        case 202:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg094 = data.readInt();
                            int _arg182 = data.readInt();
                            boolean cdmaSubscriptionMode = setCdmaSubscriptionMode(_arg094, _arg182);
                            reply.writeNoException();
                            reply.writeInt(cdmaSubscriptionMode ? 1 : 0);
                            return true;
                        case 203:
                            return onTransact$setCarrierTestOverride$(data, reply);
                        case 204:
                            data.enforceInterface(DESCRIPTOR);
                            int _result88 = getCarrierIdListVersion(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result88);
                            return true;
                        case 205:
                            data.enforceInterface(DESCRIPTOR);
                            refreshUiccProfile(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 206:
                            return onTransact$getNumberOfModemsWithSimultaneousDataConnections$(data, reply);
                        case 207:
                            data.enforceInterface(DESCRIPTOR);
                            int _result89 = getNetworkSelectionMode(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result89);
                            return true;
                        case 208:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isInEmergencySmsMode = isInEmergencySmsMode();
                            reply.writeNoException();
                            reply.writeInt(isInEmergencySmsMode ? 1 : 0);
                            return true;
                        case 209:
                            return onTransact$getRadioPowerState$(data, reply);
                        case 210:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg095 = data.readInt();
                            IImsRegistrationCallback _arg183 = IImsRegistrationCallback.Stub.asInterface(data.readStrongBinder());
                            registerImsRegistrationCallback(_arg095, _arg183);
                            reply.writeNoException();
                            return true;
                        case 211:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg096 = data.readInt();
                            IImsRegistrationCallback _arg184 = IImsRegistrationCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterImsRegistrationCallback(_arg096, _arg184);
                            reply.writeNoException();
                            return true;
                        case 212:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg097 = data.readInt();
                            IIntegerConsumer _arg185 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
                            getImsMmTelRegistrationState(_arg097, _arg185);
                            reply.writeNoException();
                            return true;
                        case 213:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg098 = data.readInt();
                            IIntegerConsumer _arg186 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
                            getImsMmTelRegistrationTransportType(_arg098, _arg186);
                            reply.writeNoException();
                            return true;
                        case 214:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg099 = data.readInt();
                            IImsCapabilityCallback _arg187 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            registerMmTelCapabilityCallback(_arg099, _arg187);
                            reply.writeNoException();
                            return true;
                        case 215:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0100 = data.readInt();
                            IImsCapabilityCallback _arg188 = IImsCapabilityCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterMmTelCapabilityCallback(_arg0100, _arg188);
                            reply.writeNoException();
                            return true;
                        case 216:
                            return onTransact$isCapable$(data, reply);
                        case 217:
                            return onTransact$isAvailable$(data, reply);
                        case 218:
                            return onTransact$isMmTelCapabilitySupported$(data, reply);
                        case 219:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isAdvancedCallingSettingEnabled = isAdvancedCallingSettingEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isAdvancedCallingSettingEnabled ? 1 : 0);
                            return true;
                        case 220:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0101 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setAdvancedCallingSettingEnabled(_arg0101, _arg010);
                            reply.writeNoException();
                            return true;
                        case 221:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isVtSettingEnabled = isVtSettingEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isVtSettingEnabled ? 1 : 0);
                            return true;
                        case 222:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0102 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setVtSettingEnabled(_arg0102, _arg010);
                            reply.writeNoException();
                            return true;
                        case 223:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isVoWiFiSettingEnabled = isVoWiFiSettingEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isVoWiFiSettingEnabled ? 1 : 0);
                            return true;
                        case 224:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0103 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setVoWiFiSettingEnabled(_arg0103, _arg010);
                            reply.writeNoException();
                            return true;
                        case 225:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isCrossSimCallingEnabledByUser = isCrossSimCallingEnabledByUser(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isCrossSimCallingEnabledByUser ? 1 : 0);
                            return true;
                        case 226:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0104 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setCrossSimCallingEnabled(_arg0104, _arg010);
                            reply.writeNoException();
                            return true;
                        case 227:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isVoWiFiRoamingSettingEnabled = isVoWiFiRoamingSettingEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isVoWiFiRoamingSettingEnabled ? 1 : 0);
                            return true;
                        case 228:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0105 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setVoWiFiRoamingSettingEnabled(_arg0105, _arg010);
                            reply.writeNoException();
                            return true;
                        case 229:
                            return onTransact$setVoWiFiNonPersistent$(data, reply);
                        case 230:
                            data.enforceInterface(DESCRIPTOR);
                            int _result90 = getVoWiFiModeSetting(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result90);
                            return true;
                        case 231:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0106 = data.readInt();
                            int _arg189 = data.readInt();
                            setVoWiFiModeSetting(_arg0106, _arg189);
                            reply.writeNoException();
                            return true;
                        case 232:
                            data.enforceInterface(DESCRIPTOR);
                            int _result91 = getVoWiFiRoamingModeSetting(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result91);
                            return true;
                        case 233:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0107 = data.readInt();
                            int _arg190 = data.readInt();
                            setVoWiFiRoamingModeSetting(_arg0107, _arg190);
                            reply.writeNoException();
                            return true;
                        case 234:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0108 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setRttCapabilitySetting(_arg0108, _arg010);
                            reply.writeNoException();
                            return true;
                        case 235:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isTtyOverVolteEnabled = isTtyOverVolteEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isTtyOverVolteEnabled ? 1 : 0);
                            return true;
                        case 236:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0109 = data.readString();
                            String _arg191 = data.readString();
                            Map _result92 = getEmergencyNumberList(_arg0109, _arg191);
                            reply.writeNoException();
                            reply.writeMap(_result92);
                            return true;
                        case 237:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0110 = data.readString();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            boolean isEmergencyNumber = isEmergencyNumber(_arg0110, _arg010);
                            reply.writeNoException();
                            reply.writeInt(isEmergencyNumber ? 1 : 0);
                            return true;
                        case 238:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _result93 = getCertsFromCarrierPrivilegeAccessRules(data.readInt());
                            reply.writeNoException();
                            reply.writeStringList(_result93);
                            return true;
                        case 239:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0111 = data.readInt();
                            IImsConfigCallback _arg192 = IImsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            registerImsProvisioningChangedCallback(_arg0111, _arg192);
                            reply.writeNoException();
                            return true;
                        case 240:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0112 = data.readInt();
                            IImsConfigCallback _arg193 = IImsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterImsProvisioningChangedCallback(_arg0112, _arg193);
                            reply.writeNoException();
                            return true;
                        case 241:
                            return onTransact$setImsProvisioningStatusForCapability$(data, reply);
                        case 242:
                            return onTransact$getImsProvisioningStatusForCapability$(data, reply);
                        case 243:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0113 = data.readInt();
                            int _arg194 = data.readInt();
                            boolean rcsProvisioningStatusForCapability = getRcsProvisioningStatusForCapability(_arg0113, _arg194);
                            reply.writeNoException();
                            reply.writeInt(rcsProvisioningStatusForCapability ? 1 : 0);
                            return true;
                        case 244:
                            return onTransact$setRcsProvisioningStatusForCapability$(data, reply);
                        case 245:
                            return onTransact$isMmTelCapabilityProvisionedInCache$(data, reply);
                        case 246:
                            return onTransact$cacheMmTelCapabilityProvisioning$(data, reply);
                        case 247:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0114 = data.readInt();
                            int _arg195 = data.readInt();
                            int _result94 = getImsProvisioningInt(_arg0114, _arg195);
                            reply.writeNoException();
                            reply.writeInt(_result94);
                            return true;
                        case 248:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0115 = data.readInt();
                            int _arg196 = data.readInt();
                            String _result95 = getImsProvisioningString(_arg0115, _arg196);
                            reply.writeNoException();
                            reply.writeString(_result95);
                            return true;
                        case 249:
                            return onTransact$setImsProvisioningInt$(data, reply);
                        case 250:
                            return onTransact$setImsProvisioningString$(data, reply);
                        case 251:
                            data.enforceInterface(DESCRIPTOR);
                            startEmergencyCallbackMode();
                            reply.writeNoException();
                            return true;
                        case 252:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0116 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = EmergencyNumber.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            updateEmergencyNumberListTestMode(_arg0116, _arg13);
                            reply.writeNoException();
                            return true;
                        case 253:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _result96 = getEmergencyNumberListTestMode();
                            reply.writeNoException();
                            reply.writeStringList(_result96);
                            return true;
                        case 254:
                            data.enforceInterface(DESCRIPTOR);
                            int _result97 = getEmergencyNumberDbVersion(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result97);
                            return true;
                        case 255:
                            data.enforceInterface(DESCRIPTOR);
                            notifyOtaEmergencyNumberDbInstalled();
                            reply.writeNoException();
                            return true;
                        case 256:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            updateOtaEmergencyNumberDbFilePath(_arg08);
                            reply.writeNoException();
                            return true;
                        case 257:
                            data.enforceInterface(DESCRIPTOR);
                            resetOtaEmergencyNumberDbFilePath();
                            reply.writeNoException();
                            return true;
                        case 258:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0117 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            boolean enableModemForSlot = enableModemForSlot(_arg0117, _arg010);
                            reply.writeNoException();
                            reply.writeInt(enableModemForSlot ? 1 : 0);
                            return true;
                        case 259:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setMultiSimCarrierRestriction(_arg010);
                            reply.writeNoException();
                            return true;
                        case 260:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0118 = data.readString();
                            String _arg197 = data.readString();
                            int _result98 = isMultiSimSupported(_arg0118, _arg197);
                            reply.writeNoException();
                            reply.writeInt(_result98);
                            return true;
                        case 261:
                            data.enforceInterface(DESCRIPTOR);
                            switchMultiSimConfig(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 262:
                            return onTransact$doesSwitchMultiSimConfigTriggerReboot$(data, reply);
                        case 263:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _result99 = getSlotsMapping();
                            reply.writeNoException();
                            reply.writeIntArray(_result99);
                            return true;
                        case 264:
                            data.enforceInterface(DESCRIPTOR);
                            int _result100 = getRadioHalVersion();
                            reply.writeNoException();
                            reply.writeInt(_result100);
                            return true;
                        case 265:
                            data.enforceInterface(DESCRIPTOR);
                            String _result101 = getCurrentPackageName();
                            reply.writeNoException();
                            reply.writeString(_result101);
                            return true;
                        case 266:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0119 = data.readInt();
                            int _arg198 = data.readInt();
                            boolean isApplicationOnUicc = isApplicationOnUicc(_arg0119, _arg198);
                            reply.writeNoException();
                            reply.writeInt(isApplicationOnUicc ? 1 : 0);
                            return true;
                        case 267:
                            return onTransact$isModemEnabledForSlot$(data, reply);
                        case 268:
                            return onTransact$isDataEnabledForApn$(data, reply);
                        case 269:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0120 = data.readInt();
                            int _arg199 = data.readInt();
                            boolean isApnMetered = isApnMetered(_arg0120, _arg199);
                            reply.writeNoException();
                            reply.writeInt(isApnMetered ? 1 : 0);
                            return true;
                        case 270:
                            return onTransact$setSystemSelectionChannels$(data, reply);
                        case 271:
                            data.enforceInterface(DESCRIPTOR);
                            List<RadioAccessSpecifier> _result102 = getSystemSelectionChannels(data.readInt());
                            reply.writeNoException();
                            reply.writeTypedList(_result102);
                            return true;
                        case 272:
                            return onTransact$isMvnoMatched$(data, reply);
                        case 273:
                            return onTransact$enqueueSmsPickResult$(data, reply);
                        case 274:
                            data.enforceInterface(DESCRIPTOR);
                            String _result103 = getMmsUserAgent(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result103);
                            return true;
                        case 275:
                            data.enforceInterface(DESCRIPTOR);
                            String _result104 = getMmsUAProfUrl(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result104);
                            return true;
                        case 276:
                            return onTransact$setMobileDataPolicyEnabled$(data, reply);
                        case 277:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0121 = data.readInt();
                            int _arg1100 = data.readInt();
                            boolean isMobileDataPolicyEnabled = isMobileDataPolicyEnabled(_arg0121, _arg1100);
                            reply.writeNoException();
                            reply.writeInt(isMobileDataPolicyEnabled ? 1 : 0);
                            return true;
                        case 278:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setCepEnabled(_arg010);
                            return true;
                        case 279:
                            return onTransact$notifyRcsAutoConfigurationReceived$(data, reply);
                        case 280:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isIccLockEnabled = isIccLockEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isIccLockEnabled ? 1 : 0);
                            return true;
                        case 281:
                            return onTransact$setIccLockEnabled$(data, reply);
                        case 282:
                            return onTransact$changeIccLockPassword$(data, reply);
                        case 283:
                            data.enforceInterface(DESCRIPTOR);
                            requestUserActivityNotification();
                            return true;
                        case 284:
                            data.enforceInterface(DESCRIPTOR);
                            userActivity();
                            return true;
                        case 285:
                            data.enforceInterface(DESCRIPTOR);
                            String _result105 = getManualNetworkSelectionPlmn(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result105);
                            return true;
                        case 286:
                            data.enforceInterface(DESCRIPTOR);
                            boolean canConnectTo5GInDsdsMode = canConnectTo5GInDsdsMode();
                            reply.writeNoException();
                            reply.writeInt(canConnectTo5GInDsdsMode ? 1 : 0);
                            return true;
                        case 287:
                            return onTransact$getEquivalentHomePlmns$(data, reply);
                        case 288:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0122 = data.readInt();
                            int _arg1101 = data.readInt();
                            int _result106 = setNrDualConnectivityState(_arg0122, _arg1101);
                            reply.writeNoException();
                            reply.writeInt(_result106);
                            return true;
                        case 289:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isNrDualConnectivityEnabled = isNrDualConnectivityEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isNrDualConnectivityEnabled ? 1 : 0);
                            return true;
                        case 290:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isRadioInterfaceCapabilitySupported = isRadioInterfaceCapabilitySupported(data.readString());
                            reply.writeNoException();
                            reply.writeInt(isRadioInterfaceCapabilitySupported ? 1 : 0);
                            return true;
                        case 291:
                            return onTransact$sendThermalMitigationRequest$(data, reply);
                        case 292:
                            return onTransact$bootstrapAuthenticationRequest$(data, reply);
                        case 293:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0123 = data.readInt();
                            String _arg1102 = data.readString();
                            boolean boundGbaServiceOverride = setBoundGbaServiceOverride(_arg0123, _arg1102);
                            reply.writeNoException();
                            reply.writeInt(boundGbaServiceOverride ? 1 : 0);
                            return true;
                        case 294:
                            data.enforceInterface(DESCRIPTOR);
                            String _result107 = getBoundGbaService(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result107);
                            return true;
                        case 295:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0124 = data.readInt();
                            int _arg1103 = data.readInt();
                            boolean gbaReleaseTimeOverride = setGbaReleaseTimeOverride(_arg0124, _arg1103);
                            reply.writeNoException();
                            reply.writeInt(gbaReleaseTimeOverride ? 1 : 0);
                            return true;
                        case 296:
                            data.enforceInterface(DESCRIPTOR);
                            int _result108 = getGbaReleaseTime(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result108);
                            return true;
                        case 297:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0125 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = RcsClientConfiguration.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            setRcsClientConfiguration(_arg0125, _arg14);
                            reply.writeNoException();
                            return true;
                        case 298:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isRcsVolteSingleRegistrationCapable = isRcsVolteSingleRegistrationCapable(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isRcsVolteSingleRegistrationCapable ? 1 : 0);
                            return true;
                        case 299:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0126 = data.readInt();
                            IRcsConfigCallback _arg1104 = IRcsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            registerRcsProvisioningCallback(_arg0126, _arg1104);
                            reply.writeNoException();
                            return true;
                        case 300:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0127 = data.readInt();
                            IRcsConfigCallback _arg1105 = IRcsConfigCallback.Stub.asInterface(data.readStrongBinder());
                            unregisterRcsProvisioningCallback(_arg0127, _arg1105);
                            reply.writeNoException();
                            return true;
                        case 301:
                            data.enforceInterface(DESCRIPTOR);
                            triggerRcsReconfiguration(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 302:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setRcsSingleRegistrationTestModeEnabled(_arg010);
                            reply.writeNoException();
                            return true;
                        case 303:
                            data.enforceInterface(DESCRIPTOR);
                            boolean rcsSingleRegistrationTestModeEnabled = getRcsSingleRegistrationTestModeEnabled();
                            reply.writeNoException();
                            reply.writeInt(rcsSingleRegistrationTestModeEnabled ? 1 : 0);
                            return true;
                        case 304:
                            data.enforceInterface(DESCRIPTOR);
                            setDeviceSingleRegistrationEnabledOverride(data.readString());
                            reply.writeNoException();
                            return true;
                        case 305:
                            data.enforceInterface(DESCRIPTOR);
                            boolean deviceSingleRegistrationEnabled = getDeviceSingleRegistrationEnabled();
                            reply.writeNoException();
                            reply.writeInt(deviceSingleRegistrationEnabled ? 1 : 0);
                            return true;
                        case 306:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0128 = data.readInt();
                            String _arg1106 = data.readString();
                            boolean carrierSingleRegistrationEnabledOverride = setCarrierSingleRegistrationEnabledOverride(_arg0128, _arg1106);
                            reply.writeNoException();
                            reply.writeInt(carrierSingleRegistrationEnabledOverride ? 1 : 0);
                            return true;
                        case 307:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0129 = data.readInt();
                            int _arg1107 = data.readInt();
                            sendDeviceToDeviceMessage(_arg0129, _arg1107);
                            reply.writeNoException();
                            return true;
                        case 308:
                            data.enforceInterface(DESCRIPTOR);
                            setActiveDeviceToDeviceTransport(data.readString());
                            reply.writeNoException();
                            return true;
                        case 309:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setDeviceToDeviceForceEnabled(_arg010);
                            reply.writeNoException();
                            return true;
                        case 310:
                            data.enforceInterface(DESCRIPTOR);
                            boolean carrierSingleRegistrationEnabled = getCarrierSingleRegistrationEnabled(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(carrierSingleRegistrationEnabled ? 1 : 0);
                            return true;
                        case 311:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0130 = data.readInt();
                            String _arg1108 = data.readString();
                            boolean imsFeatureValidationOverride = setImsFeatureValidationOverride(_arg0130, _arg1108);
                            reply.writeNoException();
                            reply.writeInt(imsFeatureValidationOverride ? 1 : 0);
                            return true;
                        case 312:
                            data.enforceInterface(DESCRIPTOR);
                            boolean imsFeatureValidationOverride2 = getImsFeatureValidationOverride(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(imsFeatureValidationOverride2 ? 1 : 0);
                            return true;
                        case 313:
                            data.enforceInterface(DESCRIPTOR);
                            String _result109 = getMobileProvisioningUrl();
                            reply.writeNoException();
                            reply.writeString(_result109);
                            return true;
                        case 314:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0131 = data.readInt();
                            String _arg1109 = data.readString();
                            int _result110 = removeContactFromEab(_arg0131, _arg1109);
                            reply.writeNoException();
                            reply.writeInt(_result110);
                            return true;
                        case 315:
                            data.enforceInterface(DESCRIPTOR);
                            String _result111 = getContactFromEab(data.readString());
                            reply.writeNoException();
                            reply.writeString(_result111);
                            return true;
                        case 316:
                            data.enforceInterface(DESCRIPTOR);
                            String _result112 = getCapabilityFromEab(data.readString());
                            reply.writeNoException();
                            reply.writeString(_result112);
                            return true;
                        case 317:
                            data.enforceInterface(DESCRIPTOR);
                            boolean deviceUceEnabled = getDeviceUceEnabled();
                            reply.writeNoException();
                            reply.writeInt(deviceUceEnabled ? 1 : 0);
                            return true;
                        case 318:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = true;
                            }
                            setDeviceUceEnabled(_arg010);
                            reply.writeNoException();
                            return true;
                        case 319:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0132 = data.readInt();
                            List<String> _arg1110 = data.createStringArrayList();
                            RcsContactUceCapability _result113 = addUceRegistrationOverrideShell(_arg0132, _arg1110);
                            reply.writeNoException();
                            if (_result113 != null) {
                                reply.writeInt(1);
                                _result113.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 320:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0133 = data.readInt();
                            List<String> _arg1111 = data.createStringArrayList();
                            RcsContactUceCapability _result114 = removeUceRegistrationOverrideShell(_arg0133, _arg1111);
                            reply.writeNoException();
                            if (_result114 != null) {
                                reply.writeInt(1);
                                _result114.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 321:
                            data.enforceInterface(DESCRIPTOR);
                            RcsContactUceCapability _result115 = clearUceRegistrationOverrideShell(data.readInt());
                            reply.writeNoException();
                            if (_result115 != null) {
                                reply.writeInt(1);
                                _result115.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 322:
                            data.enforceInterface(DESCRIPTOR);
                            RcsContactUceCapability _result116 = getLatestRcsContactUceCapabilityShell(data.readInt());
                            reply.writeNoException();
                            if (_result116 != null) {
                                reply.writeInt(1);
                                _result116.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 323:
                            data.enforceInterface(DESCRIPTOR);
                            String _result117 = getLastUcePidfXmlShell(data.readInt());
                            reply.writeNoException();
                            reply.writeString(_result117);
                            return true;
                        case 324:
                            data.enforceInterface(DESCRIPTOR);
                            boolean removeUceRequestDisallowedStatus = removeUceRequestDisallowedStatus(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(removeUceRequestDisallowedStatus ? 1 : 0);
                            return true;
                        case 325:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0134 = data.readInt();
                            long _arg1112 = data.readLong();
                            boolean capabilitiesRequestTimeout = setCapabilitiesRequestTimeout(_arg0134, _arg1112);
                            reply.writeNoException();
                            reply.writeInt(capabilitiesRequestTimeout ? 1 : 0);
                            return true;
                        case 326:
                            return onTransact$setSignalStrengthUpdateRequest$(data, reply);
                        case 327:
                            return onTransact$clearSignalStrengthUpdateRequest$(data, reply);
                        case 328:
                            data.enforceInterface(DESCRIPTOR);
                            PhoneCapability _result118 = getPhoneCapability();
                            reply.writeNoException();
                            if (_result118 != null) {
                                reply.writeInt(1);
                                _result118.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 329:
                            data.enforceInterface(DESCRIPTOR);
                            int _result119 = prepareForUnattendedReboot();
                            reply.writeNoException();
                            reply.writeInt(_result119);
                            return true;
                        case 330:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = ResultReceiver.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            getSlicingConfig(_arg09);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements ITelephony {
            public static ITelephony sDefaultImpl;
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

            @Override // com.android.internal.telephony.ITelephony
            public void dial(String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().dial(number);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void call(String callingPackage, String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().call(callingPackage, number);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOn(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOn(callingPackage);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOnWithFeature(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOnWithFeature(callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOnForSubscriber(int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOnForSubscriber(subId, callingPackage);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioOnForSubscriberWithFeature(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioOnForSubscriberWithFeature(subId, callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setCallComposerStatus(int subId, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCallComposerStatus(subId, status);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCallComposerStatus(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallComposerStatus(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean supplyPinForSubscriber(int subId, String pin) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(pin);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPinForSubscriber(subId, pin);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean supplyPukForSubscriber(int subId, String puk, String pin) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(puk);
                    _data.writeString(pin);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPukForSubscriber(subId, puk, pin);
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

            @Override // com.android.internal.telephony.ITelephony
            public int[] supplyPinReportResultForSubscriber(int subId, String pin) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(pin);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPinReportResultForSubscriber(subId, pin);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int[] supplyPukReportResultForSubscriber(int subId, String puk, String pin) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(puk);
                    _data.writeString(pin);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supplyPukReportResultForSubscriber(subId, puk, pin);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean handlePinMmi(String dialString) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(dialString);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmi(dialString);
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

            @Override // com.android.internal.telephony.ITelephony
            public void handleUssdRequest(int subId, String ussdRequest, ResultReceiver wrappedCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(ussdRequest);
                    if (wrappedCallback != null) {
                        _data.writeInt(1);
                        wrappedCallback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().handleUssdRequest(subId, ussdRequest, wrappedCallback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean handlePinMmiForSubscriber(int subId, String dialString) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(dialString);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handlePinMmiForSubscriber(subId, dialString);
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

            @Override // com.android.internal.telephony.ITelephony
            public void toggleRadioOnOff() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().toggleRadioOnOff();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void toggleRadioOnOffForSubscriber(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().toggleRadioOnOffForSubscriber(subId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRadio(boolean turnOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(turnOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadio(turnOn);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRadioForSubscriber(int subId, boolean turnOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = true;
                    _data.writeInt(turnOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadioForSubscriber(subId, turnOn);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRadioPower(boolean turnOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(turnOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadioPower(turnOn);
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

            @Override // com.android.internal.telephony.ITelephony
            public void updateServiceLocation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateServiceLocation();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateServiceLocationWithPackageName(String callingPkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateServiceLocationWithPackageName(callingPkg);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableLocationUpdates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableLocationUpdates();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableLocationUpdates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableLocationUpdates();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean enableDataConnectivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableDataConnectivity();
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean disableDataConnectivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableDataConnectivity();
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataConnectivityPossible(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataConnectivityPossible(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public CellIdentity getCellLocation(String callingPkg, String callingFeatureId) throws RemoteException {
                CellIdentity _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCellLocation(callingPkg, callingFeatureId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CellIdentity.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getNetworkCountryIsoForPhone(int phoneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkCountryIsoForPhone(phoneId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<NeighboringCellInfo> getNeighboringCellInfo(String callingPkg, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNeighboringCellInfo(callingPkg, callingFeatureId);
                    }
                    _reply.readException();
                    List<NeighboringCellInfo> _result = _reply.createTypedArrayList(NeighboringCellInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
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

            @Override // com.android.internal.telephony.ITelephony
            public int getCallStateForSubscription(int subId, String callingPackage, String featureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(featureId);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallStateForSubscription(subId, callingPackage, featureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataActivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataActivity();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataActivityForSubId(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataActivityForSubId(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataStateForSubId(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataStateForSubId(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getActivePhoneType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePhoneType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getActivePhoneTypeForSlot(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivePhoneTypeForSlot(slotIndex);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconIndex(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconIndex(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconIndexForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconIndexForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconMode(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconMode(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaEriIconModeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriIconModeForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaEriText(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriText(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaEriTextForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaEriTextForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean needsOtaServiceProvisioning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().needsOtaServiceProvisioning();
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setVoiceMailNumber(int subId, String alphaTag, String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(alphaTag);
                    _data.writeString(number);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVoiceMailNumber(subId, alphaTag, number);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setVoiceActivationState(int subId, int activationState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(activationState);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoiceActivationState(subId, activationState);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setDataActivationState(int subId, int activationState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(activationState);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDataActivationState(subId, activationState);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoiceActivationState(int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceActivationState(subId, callingPackage);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataActivationState(int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataActivationState(subId, callingPackage);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoiceMessageCountForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMessageCountForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isConcurrentVoiceAndDataAllowed(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConcurrentVoiceAndDataAllowed(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public Bundle getVisualVoicemailSettings(String callingPackage, int subId) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVisualVoicemailSettings(callingPackage, subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public String getVisualVoicemailPackageName(String callingPackage, String callingFeatureId, int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVisualVoicemailPackageName(callingPackage, callingFeatureId, subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableVisualVoicemailSmsFilter(String callingPackage, int subId, VisualVoicemailSmsFilterSettings settings) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeInt(subId);
                    if (settings != null) {
                        _data.writeInt(1);
                        settings.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableVisualVoicemailSmsFilter(callingPackage, subId, settings);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableVisualVoicemailSmsFilter(String callingPackage, int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(56, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableVisualVoicemailSmsFilter(callingPackage, subId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String callingPackage, int subId) throws RemoteException {
                VisualVoicemailSmsFilterSettings _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVisualVoicemailSmsFilterSettings(callingPackage, subId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int subId) throws RemoteException {
                VisualVoicemailSmsFilterSettings _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveVisualVoicemailSmsFilterSettings(subId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void sendVisualVoicemailSmsForSubscriber(String callingPackage, String callingAttributeTag, int subId, String number, int port, String text, PendingIntent sentIntent) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(callingPackage);
                        try {
                            _data.writeString(callingAttributeTag);
                            try {
                                _data.writeInt(subId);
                                try {
                                    _data.writeString(number);
                                    _data.writeInt(port);
                                    _data.writeString(text);
                                    if (sentIntent != null) {
                                        _data.writeInt(1);
                                        sentIntent.writeToParcel(_data, 0);
                                    } else {
                                        _data.writeInt(0);
                                    }
                                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().sendVisualVoicemailSmsForSubscriber(callingPackage, callingAttributeTag, subId, number, port, text, sentIntent);
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

            @Override // com.android.internal.telephony.ITelephony
            public void sendDialerSpecialCode(String callingPackageName, String inputCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackageName);
                    _data.writeString(inputCode);
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().sendDialerSpecialCode(callingPackageName, inputCode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getNetworkTypeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkTypeForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataNetworkType(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataNetworkType(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getDataNetworkTypeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataNetworkTypeForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoiceNetworkTypeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceNetworkTypeForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean hasIccCard() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasIccCard();
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean hasIccCardUsingSlotIndex(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasIccCardUsingSlotIndex(slotIndex);
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

            @Override // com.android.internal.telephony.ITelephony
            public int getLteOnCdmaMode(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLteOnCdmaMode(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getLteOnCdmaModeForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLteOnCdmaModeForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<CellInfo> getAllCellInfo(String callingPkg, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllCellInfo(callingPkg, callingFeatureId);
                    }
                    _reply.readException();
                    List<CellInfo> _result = _reply.createTypedArrayList(CellInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestCellInfoUpdate(int subId, ICellInfoCallback cb, String callingPkg, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeString(callingPkg);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestCellInfoUpdate(subId, cb, callingPkg, callingFeatureId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestCellInfoUpdateWithWorkSource(int subId, ICellInfoCallback cb, String callingPkg, String callingFeatureId, WorkSource ws) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeString(callingPkg);
                    _data.writeString(callingFeatureId);
                    if (ws != null) {
                        _data.writeInt(1);
                        ws.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestCellInfoUpdateWithWorkSource(subId, cb, callingPkg, callingFeatureId, ws);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setCellInfoListRate(int rateInMillis) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(rateInMillis);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCellInfoListRate(rateInMillis);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int slotIndex, String callingPackage, String AID, int p2) throws RemoteException {
                IccOpenLogicalChannelResponse _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeString(callingPackage);
                    _data.writeString(AID);
                    _data.writeInt(p2);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccOpenLogicalChannelBySlot(slotIndex, callingPackage, AID, p2);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IccOpenLogicalChannelResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int subId, String callingPackage, String AID, int p2) throws RemoteException {
                IccOpenLogicalChannelResponse _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(AID);
                    _data.writeInt(p2);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccOpenLogicalChannel(subId, callingPackage, AID, p2);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = IccOpenLogicalChannelResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean iccCloseLogicalChannelBySlot(int slotIndex, int channel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeInt(channel);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccCloseLogicalChannelBySlot(slotIndex, channel);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean iccCloseLogicalChannel(int subId, int channel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(channel);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().iccCloseLogicalChannel(subId, channel);
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

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduLogicalChannelBySlot(int slotIndex, int channel, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(slotIndex);
                        try {
                            _data.writeInt(channel);
                            try {
                                _data.writeInt(cla);
                                try {
                                    _data.writeInt(instruction);
                                    _data.writeInt(p1);
                                    _data.writeInt(p2);
                                    _data.writeInt(p3);
                                    _data.writeString(data);
                                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        String _result = _reply.readString();
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    String iccTransmitApduLogicalChannelBySlot = Stub.getDefaultImpl().iccTransmitApduLogicalChannelBySlot(slotIndex, channel, cla, instruction, p1, p2, p3, data);
                                    _reply.recycle();
                                    _data.recycle();
                                    return iccTransmitApduLogicalChannelBySlot;
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

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduLogicalChannel(int subId, int channel, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        try {
                            _data.writeInt(channel);
                            try {
                                _data.writeInt(cla);
                                try {
                                    _data.writeInt(instruction);
                                    _data.writeInt(p1);
                                    _data.writeInt(p2);
                                    _data.writeInt(p3);
                                    _data.writeString(data);
                                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        String _result = _reply.readString();
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    String iccTransmitApduLogicalChannel = Stub.getDefaultImpl().iccTransmitApduLogicalChannel(subId, channel, cla, instruction, p1, p2, p3, data);
                                    _reply.recycle();
                                    _data.recycle();
                                    return iccTransmitApduLogicalChannel;
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

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduBasicChannelBySlot(int slotIndex, String callingPackage, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(slotIndex);
                        try {
                            _data.writeString(callingPackage);
                            try {
                                _data.writeInt(cla);
                                try {
                                    _data.writeInt(instruction);
                                    _data.writeInt(p1);
                                    _data.writeInt(p2);
                                    _data.writeInt(p3);
                                    _data.writeString(data);
                                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        String _result = _reply.readString();
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    String iccTransmitApduBasicChannelBySlot = Stub.getDefaultImpl().iccTransmitApduBasicChannelBySlot(slotIndex, callingPackage, cla, instruction, p1, p2, p3, data);
                                    _reply.recycle();
                                    _data.recycle();
                                    return iccTransmitApduBasicChannelBySlot;
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

            @Override // com.android.internal.telephony.ITelephony
            public String iccTransmitApduBasicChannel(int subId, String callingPackage, int cla, int instruction, int p1, int p2, int p3, String data) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        try {
                            _data.writeString(callingPackage);
                            try {
                                _data.writeInt(cla);
                                try {
                                    _data.writeInt(instruction);
                                    _data.writeInt(p1);
                                    _data.writeInt(p2);
                                    _data.writeInt(p3);
                                    _data.writeString(data);
                                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        String _result = _reply.readString();
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    String iccTransmitApduBasicChannel = Stub.getDefaultImpl().iccTransmitApduBasicChannel(subId, callingPackage, cla, instruction, p1, p2, p3, data);
                                    _reply.recycle();
                                    _data.recycle();
                                    return iccTransmitApduBasicChannel;
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

            @Override // com.android.internal.telephony.ITelephony
            public byte[] iccExchangeSimIO(int subId, int fileID, int command, int p1, int p2, int p3, String filePath) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        try {
                            _data.writeInt(fileID);
                            try {
                                _data.writeInt(command);
                                try {
                                    _data.writeInt(p1);
                                    try {
                                        _data.writeInt(p2);
                                        _data.writeInt(p3);
                                        _data.writeString(filePath);
                                        boolean _status = this.mRemote.transact(81, _data, _reply, 0);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _reply.readException();
                                            byte[] _result = _reply.createByteArray();
                                            _reply.recycle();
                                            _data.recycle();
                                            return _result;
                                        }
                                        byte[] iccExchangeSimIO = Stub.getDefaultImpl().iccExchangeSimIO(subId, fileID, command, p1, p2, p3, filePath);
                                        _reply.recycle();
                                        _data.recycle();
                                        return iccExchangeSimIO;
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String sendEnvelopeWithStatus(int subId, String content) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(content);
                    boolean _status = this.mRemote.transact(82, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendEnvelopeWithStatus(subId, content);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String nvReadItem(int itemID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(itemID);
                    boolean _status = this.mRemote.transact(83, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nvReadItem(itemID);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean nvWriteItem(int itemID, String itemValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(itemID);
                    _data.writeString(itemValue);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(84, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nvWriteItem(itemID, itemValue);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean nvWriteCdmaPrl(byte[] preferredRoamingList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(preferredRoamingList);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().nvWriteCdmaPrl(preferredRoamingList);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean resetModemConfig(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(86, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetModemConfig(slotIndex);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean rebootModem(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().rebootModem(slotIndex);
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

            @Override // com.android.internal.telephony.ITelephony
            public int getAllowedNetworkTypesBitmask(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNetworkTypesBitmask(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isTetheringApnRequiredForSubscriber(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTetheringApnRequiredForSubscriber(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void enableIms(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(90, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableIms(slotId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void disableIms(int slotId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableIms(slotId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void resetIms(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetIms(slotIndex);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void registerMmTelFeatureCallback(int slotId, IImsServiceFeatureCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(93, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerMmTelFeatureCallback(slotId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterImsFeatureCallback(IImsServiceFeatureCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterImsFeatureCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IImsRegistration getImsRegistration(int slotId, int feature) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(feature);
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsRegistration(slotId, feature);
                    }
                    _reply.readException();
                    IImsRegistration _result = IImsRegistration.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public IImsConfig getImsConfig(int slotId, int feature) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotId);
                    _data.writeInt(feature);
                    boolean _status = this.mRemote.transact(96, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsConfig(slotId, feature);
                    }
                    _reply.readException();
                    IImsConfig _result = IImsConfig.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setBoundImsServiceOverride(int slotIndex, boolean isCarrierService, int[] featureTypes, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _result = true;
                    _data.writeInt(isCarrierService ? 1 : 0);
                    _data.writeIntArray(featureTypes);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(97, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBoundImsServiceOverride(slotIndex, isCarrierService, featureTypes, packageName);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean clearCarrierImsServiceOverride(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(98, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearCarrierImsServiceOverride(slotIndex);
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

            @Override // com.android.internal.telephony.ITelephony
            public String getBoundImsServicePackage(int slotIndex, boolean isCarrierImsService, int featureType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeInt(isCarrierImsService ? 1 : 0);
                    _data.writeInt(featureType);
                    boolean _status = this.mRemote.transact(99, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBoundImsServicePackage(slotIndex, isCarrierImsService, featureType);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getImsMmTelFeatureState(int subId, IIntegerConsumer callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(100, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getImsMmTelFeatureState(subId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setNetworkSelectionModeAutomatic(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(101, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNetworkSelectionModeAutomatic(subId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public CellNetworkScanResult getCellNetworkScanResults(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                CellNetworkScanResult _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(102, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCellNetworkScanResults(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CellNetworkScanResult.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int requestNetworkScan(int subId, NetworkScanRequest request, Messenger messenger, IBinder binder, String callingPackage, String callingFeatureId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        if (request != null) {
                            _data.writeInt(1);
                            request.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (messenger != null) {
                            _data.writeInt(1);
                            messenger.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeStrongBinder(binder);
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
                try {
                    _data.writeString(callingPackage);
                    try {
                        _data.writeString(callingFeatureId);
                        boolean _status = this.mRemote.transact(103, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            int _result = _reply.readInt();
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        }
                        int requestNetworkScan = Stub.getDefaultImpl().requestNetworkScan(subId, request, messenger, binder, callingPackage, callingFeatureId);
                        _reply.recycle();
                        _data.recycle();
                        return requestNetworkScan;
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void stopNetworkScan(int subId, int scanId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(scanId);
                    boolean _status = this.mRemote.transact(104, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopNetworkScan(subId, scanId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setNetworkSelectionModeManual(int subId, OperatorInfo operatorInfo, boolean persisSelection) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = true;
                    if (operatorInfo != null) {
                        _data.writeInt(1);
                        operatorInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(persisSelection ? 1 : 0);
                    boolean _status = this.mRemote.transact(105, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNetworkSelectionModeManual(subId, operatorInfo, persisSelection);
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

            @Override // com.android.internal.telephony.ITelephony
            public long getAllowedNetworkTypesForReason(int subId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(106, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNetworkTypesForReason(subId, reason);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setAllowedNetworkTypesForReason(int subId, int reason, long allowedNetworkTypes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(reason);
                    _data.writeLong(allowedNetworkTypes);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(107, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAllowedNetworkTypesForReason(subId, reason, allowedNetworkTypes);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean getDataEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(108, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDataEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isUserDataEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(109, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isUserDataEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(110, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setDataEnabledForReason(int subId, int reason, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(reason);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(111, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDataEnabledForReason(subId, reason, enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataEnabledForReason(int subId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(reason);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(112, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataEnabledForReason(subId, reason);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isManualNetworkSelectionAllowed(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(113, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isManualNetworkSelectionAllowed(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setAlwaysReportSignalStrength(int subId, boolean isEnable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnable ? 1 : 0);
                    boolean _status = this.mRemote.transact(114, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAlwaysReportSignalStrength(subId, isEnable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getPcscfAddress(String apnType, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(apnType);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(115, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPcscfAddress(apnType, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setImsRegistrationState(boolean registered) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(registered ? 1 : 0);
                    boolean _status = this.mRemote.transact(116, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setImsRegistrationState(registered);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaMdn(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(117, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaMdn(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaMin(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(118, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaMin(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestNumberVerification(PhoneNumberRange range, long timeoutMillis, INumberVerificationCallback callback, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (range != null) {
                        _data.writeInt(1);
                        range.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(timeoutMillis);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(119, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestNumberVerification(range, timeoutMillis, callback, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierPrivilegeStatus(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(120, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierPrivilegeStatus(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierPrivilegeStatusForUid(int subId, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(121, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierPrivilegeStatusForUid(subId, uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int checkCarrierPrivilegesForPackage(int subId, String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(pkgName);
                    boolean _status = this.mRemote.transact(122, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkCarrierPrivilegesForPackage(subId, pkgName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int checkCarrierPrivilegesForPackageAnyPhone(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _status = this.mRemote.transact(123, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkCarrierPrivilegesForPackageAnyPhone(pkgName);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int phoneId) throws RemoteException {
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
                    _data.writeInt(phoneId);
                    boolean _status = this.mRemote.transact(124, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierPackageNamesForIntentAndPhone(intent, phoneId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setLine1NumberForDisplayForSubscriber(int subId, String alphaTag, String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(alphaTag);
                    _data.writeString(number);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(125, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLine1NumberForDisplayForSubscriber(subId, alphaTag, number);
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

            @Override // com.android.internal.telephony.ITelephony
            public String getLine1NumberForDisplay(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(126, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1NumberForDisplay(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getLine1AlphaTagForDisplay(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(127, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1AlphaTagForDisplay(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getMergedSubscriberIds(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(128, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMergedSubscriberIds(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getMergedImsisFromGroup(int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(129, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMergedImsisFromGroup(subId, callingPackage);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setOperatorBrandOverride(int subId, String brand) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(brand);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(130, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOperatorBrandOverride(subId, brand);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setRoamingOverride(int subId, List<String> gsmRoamingList, List<String> gsmNonRoamingList, List<String> cdmaRoamingList, List<String> cdmaNonRoamingList) throws RemoteException {
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
                    _data.writeStringList(gsmRoamingList);
                    try {
                        _data.writeStringList(gsmNonRoamingList);
                        try {
                            _data.writeStringList(cdmaRoamingList);
                            try {
                                _data.writeStringList(cdmaNonRoamingList);
                                try {
                                    boolean _result = false;
                                    boolean _status = this.mRemote.transact(131, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = true;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    }
                                    boolean roamingOverride = Stub.getDefaultImpl().setRoamingOverride(subId, gsmRoamingList, gsmNonRoamingList, cdmaRoamingList, cdmaNonRoamingList);
                                    _reply.recycle();
                                    _data.recycle();
                                    return roamingOverride;
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

            @Override // com.android.internal.telephony.ITelephony
            public int invokeOemRilRequestRaw(byte[] oemReq, byte[] oemResp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(oemReq);
                    if (oemResp == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(oemResp.length);
                    }
                    boolean _status = this.mRemote.transact(132, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().invokeOemRilRequestRaw(oemReq, oemResp);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(oemResp);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean needMobileRadioShutdown() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(133, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().needMobileRadioShutdown();
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

            @Override // com.android.internal.telephony.ITelephony
            public void shutdownMobileRadios() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(134, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().shutdownMobileRadios();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getRadioAccessFamily(int phoneId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(135, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioAccessFamily(phoneId, callingPackage);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void uploadCallComposerPicture(int subscriptionId, String callingPackage, String contentType, ParcelFileDescriptor fd, ResultReceiver callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subscriptionId);
                    _data.writeString(callingPackage);
                    _data.writeString(contentType);
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(136, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().uploadCallComposerPicture(subscriptionId, callingPackage, contentType, fd, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void enableVideoCalling(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(137, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableVideoCalling(enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVideoCallingEnabled(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(138, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVideoCallingEnabled(callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean canChangeDtmfToneLength(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(139, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canChangeDtmfToneLength(subId, callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isWorldPhone(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(140, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWorldPhone(subId, callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isTtyModeSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(141, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTtyModeSupported();
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRttSupported(int subscriptionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subscriptionId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(142, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRttSupported(subscriptionId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isHearingAidCompatibilitySupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(143, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHearingAidCompatibilitySupported();
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isImsRegistered(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(144, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isImsRegistered(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isWifiCallingAvailable(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(145, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWifiCallingAvailable(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVideoTelephonyAvailable(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(146, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVideoTelephonyAvailable(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public int getImsRegTechnologyForMmTel(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(147, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsRegTechnologyForMmTel(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getDeviceId(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(148, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceId(callingPackage);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getDeviceIdWithFeature(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(149, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceIdWithFeature(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getImeiForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(150, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImeiForSlot(slotIndex, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getTypeAllocationCodeForSlot(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _status = this.mRemote.transact(151, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTypeAllocationCodeForSlot(slotIndex);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getMeidForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(152, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMeidForSlot(slotIndex, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getManufacturerCodeForSlot(int slotIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _status = this.mRemote.transact(153, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getManufacturerCodeForSlot(slotIndex);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getDeviceSoftwareVersionForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(154, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceSoftwareVersionForSlot(slotIndex, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubIdForPhoneAccount(PhoneAccount phoneAccount) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(155, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubIdForPhoneAccount(phoneAccount);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneAccountHandle != null) {
                        _data.writeInt(1);
                        phoneAccountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(156, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubIdForPhoneAccountHandle(phoneAccountHandle, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int subscriptionId) throws RemoteException {
                PhoneAccountHandle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subscriptionId);
                    boolean _status = this.mRemote.transact(157, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneAccountHandleForSubscriptionId(subscriptionId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void factoryReset(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(158, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().factoryReset(subId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getSimLocaleForSubscriber(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(159, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimLocaleForSubscriber(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestModemActivityInfo(ResultReceiver result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(160, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestModemActivityInfo(result);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public ServiceState getServiceStateForSubscriber(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                ServiceState _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(161, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getServiceStateForSubscriber(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ServiceState.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public Uri getVoicemailRingtoneUri(PhoneAccountHandle accountHandle) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(162, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoicemailRingtoneUri(accountHandle);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setVoicemailRingtoneUri(String callingPackage, PhoneAccountHandle phoneAccountHandle, Uri uri) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    if (phoneAccountHandle != null) {
                        _data.writeInt(1);
                        phoneAccountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(163, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoicemailRingtoneUri(callingPackage, phoneAccountHandle, uri);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVoicemailVibrationEnabled(PhoneAccountHandle accountHandle) throws RemoteException {
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
                    boolean _status = this.mRemote.transact(164, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoicemailVibrationEnabled(accountHandle);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setVoicemailVibrationEnabled(String callingPackage, PhoneAccountHandle phoneAccountHandle, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    int i = 1;
                    if (phoneAccountHandle != null) {
                        _data.writeInt(1);
                        phoneAccountHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!enabled) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(165, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoicemailVibrationEnabled(callingPackage, phoneAccountHandle, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getPackagesWithCarrierPrivileges(int phoneId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    boolean _status = this.mRemote.transact(166, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesWithCarrierPrivileges(phoneId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getPackagesWithCarrierPrivilegesForAllPhones() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(167, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackagesWithCarrierPrivilegesForAllPhones();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getAidForAppType(int subId, int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(appType);
                    boolean _status = this.mRemote.transact(168, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAidForAppType(subId, appType);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getEsn(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(169, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEsn(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCdmaPrlVersion(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(170, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaPrlVersion(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<TelephonyHistogram> getTelephonyHistograms() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(171, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTelephonyHistograms();
                    }
                    _reply.readException();
                    List<TelephonyHistogram> _result = _reply.createTypedArrayList(TelephonyHistogram.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setAllowedCarriers(CarrierRestrictionRules carrierRestrictionRules) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (carrierRestrictionRules != null) {
                        _data.writeInt(1);
                        carrierRestrictionRules.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(172, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAllowedCarriers(carrierRestrictionRules);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public CarrierRestrictionRules getAllowedCarriers() throws RemoteException {
                CarrierRestrictionRules _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(173, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedCarriers();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarrierRestrictionRules.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubscriptionCarrierId(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(174, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionCarrierId(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getSubscriptionCarrierName(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(175, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionCarrierName(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getSubscriptionSpecificCarrierId(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(176, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionSpecificCarrierId(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getSubscriptionSpecificCarrierName(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(177, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriptionSpecificCarrierName(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierIdFromMccMnc(int slotIndex, String mccmnc, boolean isSubscriptionMccMnc) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeString(mccmnc);
                    _data.writeInt(isSubscriptionMccMnc ? 1 : 0);
                    boolean _status = this.mRemote.transact(178, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierIdFromMccMnc(slotIndex, mccmnc, isSubscriptionMccMnc);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void carrierActionSetRadioEnabled(int subId, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(179, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().carrierActionSetRadioEnabled(subId, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void carrierActionReportDefaultNetworkStatus(int subId, boolean report) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(report ? 1 : 0);
                    boolean _status = this.mRemote.transact(180, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().carrierActionReportDefaultNetworkStatus(subId, report);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void carrierActionResetAll(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(181, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().carrierActionResetAll(subId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getCallForwarding(int subId, int callForwardingReason, ICallForwardingInfoCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(callForwardingReason);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(182, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getCallForwarding(subId, callForwardingReason, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setCallForwarding(int subId, CallForwardingInfo callForwardingInfo, IIntegerConsumer callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (callForwardingInfo != null) {
                        _data.writeInt(1);
                        callForwardingInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(183, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCallForwarding(subId, callForwardingInfo, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getCallWaitingStatus(int subId, IIntegerConsumer callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(184, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getCallWaitingStatus(subId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setCallWaitingStatus(int subId, boolean enabled, IIntegerConsumer callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(185, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCallWaitingStatus(subId, enabled, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<ClientRequestStats> getClientRequestStats(String callingPackage, String callingFeatureId, int subid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    _data.writeInt(subid);
                    boolean _status = this.mRemote.transact(186, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getClientRequestStats(callingPackage, callingFeatureId, subid);
                    }
                    _reply.readException();
                    List<ClientRequestStats> _result = _reply.createTypedArrayList(ClientRequestStats.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setSimPowerStateForSlot(int slotIndex, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(187, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSimPowerStateForSlot(slotIndex, state);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setSimPowerStateForSlotWithCallback(int slotIndex, int state, IIntegerConsumer callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeInt(state);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(188, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSimPowerStateForSlotWithCallback(slotIndex, state, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String[] getForbiddenPlmns(int subId, int appType, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(appType);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(189, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getForbiddenPlmns(subId, appType, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setForbiddenPlmns(int subId, int appType, List<String> fplmns, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(appType);
                    _data.writeStringList(fplmns);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(190, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setForbiddenPlmns(subId, appType, fplmns, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getEmergencyCallbackMode(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(191, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyCallbackMode(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public SignalStrength getSignalStrength(int subId) throws RemoteException {
                SignalStrength _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(192, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSignalStrength(subId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = SignalStrength.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCardIdForDefaultEuicc(int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(193, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCardIdForDefaultEuicc(subId, callingPackage);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<UiccCardInfo> getUiccCardsInfo(String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(194, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUiccCardsInfo(callingPackage);
                    }
                    _reply.readException();
                    List<UiccCardInfo> _result = _reply.createTypedArrayList(UiccCardInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public UiccSlotInfo[] getUiccSlotsInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(195, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUiccSlotsInfo();
                    }
                    _reply.readException();
                    UiccSlotInfo[] _result = (UiccSlotInfo[]) _reply.createTypedArray(UiccSlotInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean switchSlots(int[] physicalSlots) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(physicalSlots);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(196, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchSlots(physicalSlots);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataRoamingEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(197, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataRoamingEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setDataRoamingEnabled(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(198, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDataRoamingEnabled(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaRoamingMode(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(199, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaRoamingMode(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCdmaRoamingMode(int subId, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(mode);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(200, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCdmaRoamingMode(subId, mode);
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

            @Override // com.android.internal.telephony.ITelephony
            public int getCdmaSubscriptionMode(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(201, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCdmaSubscriptionMode(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCdmaSubscriptionMode(int subId, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(mode);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(202, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCdmaSubscriptionMode(subId, mode);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setCarrierTestOverride(int subId, String mccmnc, String imsi, String iccid, String gid1, String gid2, String plmn, String spn, String carrierPrivilegeRules, String apn) throws RemoteException {
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
                    _data.writeString(mccmnc);
                    _data.writeString(imsi);
                    _data.writeString(iccid);
                    _data.writeString(gid1);
                    _data.writeString(gid2);
                    _data.writeString(plmn);
                    _data.writeString(spn);
                    _data.writeString(carrierPrivilegeRules);
                    _data.writeString(apn);
                    boolean _status = this.mRemote.transact(203, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setCarrierTestOverride(subId, mccmnc, imsi, iccid, gid1, gid2, plmn, spn, carrierPrivilegeRules, apn);
                    _reply.recycle();
                    _data.recycle();
                } catch (Throwable th4) {
                    th = th4;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getCarrierIdListVersion(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(204, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierIdListVersion(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void refreshUiccProfile(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(205, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().refreshUiccProfile(subId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getNumberOfModemsWithSimultaneousDataConnections(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(206, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumberOfModemsWithSimultaneousDataConnections(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getNetworkSelectionMode(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(207, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkSelectionMode(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isInEmergencySmsMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(208, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInEmergencySmsMode();
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

            @Override // com.android.internal.telephony.ITelephony
            public int getRadioPowerState(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(209, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioPowerState(slotIndex, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void registerImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(210, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerImsRegistrationCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterImsRegistrationCallback(int subId, IImsRegistrationCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(211, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterImsRegistrationCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getImsMmTelRegistrationState(int subId, IIntegerConsumer consumer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(consumer != null ? consumer.asBinder() : null);
                    boolean _status = this.mRemote.transact(212, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getImsMmTelRegistrationState(subId, consumer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getImsMmTelRegistrationTransportType(int subId, IIntegerConsumer consumer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(consumer != null ? consumer.asBinder() : null);
                    boolean _status = this.mRemote.transact(213, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getImsMmTelRegistrationTransportType(subId, consumer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void registerMmTelCapabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(214, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerMmTelCapabilityCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterMmTelCapabilityCallback(int subId, IImsCapabilityCallback c) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(c != null ? c.asBinder() : null);
                    boolean _status = this.mRemote.transact(215, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterMmTelCapabilityCallback(subId, c);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isCapable(int subId, int capability, int regTech) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(regTech);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(216, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCapable(subId, capability, regTech);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isAvailable(int subId, int capability, int regTech) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(regTech);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(217, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAvailable(subId, capability, regTech);
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

            @Override // com.android.internal.telephony.ITelephony
            public void isMmTelCapabilitySupported(int subId, IIntegerConsumer callback, int capability, int transportType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(capability);
                    _data.writeInt(transportType);
                    boolean _status = this.mRemote.transact(218, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().isMmTelCapabilitySupported(subId, callback, capability, transportType);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isAdvancedCallingSettingEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(219, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAdvancedCallingSettingEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setAdvancedCallingSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(220, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAdvancedCallingSettingEnabled(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVtSettingEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(221, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVtSettingEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setVtSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(222, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVtSettingEnabled(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVoWiFiSettingEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(223, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoWiFiSettingEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(224, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoWiFiSettingEnabled(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isCrossSimCallingEnabledByUser(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(225, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCrossSimCallingEnabledByUser(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setCrossSimCallingEnabled(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(226, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCrossSimCallingEnabled(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isVoWiFiRoamingSettingEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(227, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVoWiFiRoamingSettingEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiRoamingSettingEnabled(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(228, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoWiFiRoamingSettingEnabled(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiNonPersistent(int subId, boolean isCapable, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isCapable ? 1 : 0);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(229, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoWiFiNonPersistent(subId, isCapable, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoWiFiModeSetting(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(230, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoWiFiModeSetting(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiModeSetting(int subId, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(231, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoWiFiModeSetting(subId, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getVoWiFiRoamingModeSetting(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(232, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoWiFiRoamingModeSetting(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setVoWiFiRoamingModeSetting(int subId, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(233, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setVoWiFiRoamingModeSetting(subId, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setRttCapabilitySetting(int subId, boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(234, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRttCapabilitySetting(subId, isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isTtyOverVolteEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(235, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTtyOverVolteEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public Map getEmergencyNumberList(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(236, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyNumberList(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isEmergencyNumber(String number, boolean exactMatch) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    boolean _result = true;
                    _data.writeInt(exactMatch ? 1 : 0);
                    boolean _status = this.mRemote.transact(237, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEmergencyNumber(number, exactMatch);
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

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getCertsFromCarrierPrivilegeAccessRules(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(238, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCertsFromCarrierPrivilegeAccessRules(subId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void registerImsProvisioningChangedCallback(int subId, IImsConfigCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(239, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerImsProvisioningChangedCallback(subId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterImsProvisioningChangedCallback(int subId, IImsConfigCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(240, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterImsProvisioningChangedCallback(subId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setImsProvisioningStatusForCapability(int subId, int capability, int tech, boolean isProvisioned) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(tech);
                    _data.writeInt(isProvisioned ? 1 : 0);
                    boolean _status = this.mRemote.transact(241, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setImsProvisioningStatusForCapability(subId, capability, tech, isProvisioned);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getImsProvisioningStatusForCapability(int subId, int capability, int tech) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(tech);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(242, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsProvisioningStatusForCapability(subId, capability, tech);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean getRcsProvisioningStatusForCapability(int subId, int capability) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(243, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRcsProvisioningStatusForCapability(subId, capability);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setRcsProvisioningStatusForCapability(int subId, int capability, boolean isProvisioned) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(isProvisioned ? 1 : 0);
                    boolean _status = this.mRemote.transact(244, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRcsProvisioningStatusForCapability(subId, capability, isProvisioned);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isMmTelCapabilityProvisionedInCache(int subId, int capability, int tech) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(tech);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(245, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMmTelCapabilityProvisionedInCache(subId, capability, tech);
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

            @Override // com.android.internal.telephony.ITelephony
            public void cacheMmTelCapabilityProvisioning(int subId, int capability, int tech, boolean isProvisioned) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(capability);
                    _data.writeInt(tech);
                    _data.writeInt(isProvisioned ? 1 : 0);
                    boolean _status = this.mRemote.transact(246, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cacheMmTelCapabilityProvisioning(subId, capability, tech, isProvisioned);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getImsProvisioningInt(int subId, int key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(key);
                    boolean _status = this.mRemote.transact(247, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsProvisioningInt(subId, key);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getImsProvisioningString(int subId, int key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(key);
                    boolean _status = this.mRemote.transact(248, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsProvisioningString(subId, key);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setImsProvisioningInt(int subId, int key, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(key);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(249, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setImsProvisioningInt(subId, key, value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setImsProvisioningString(int subId, int key, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(key);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(250, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setImsProvisioningString(subId, key, value);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void startEmergencyCallbackMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(251, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startEmergencyCallbackMode();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateEmergencyNumberListTestMode(int action, EmergencyNumber num) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(action);
                    if (num != null) {
                        _data.writeInt(1);
                        num.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(252, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateEmergencyNumberListTestMode(action, num);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getEmergencyNumberListTestMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(253, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyNumberListTestMode();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getEmergencyNumberDbVersion(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(254, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmergencyNumberDbVersion(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void notifyOtaEmergencyNumberDbInstalled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(255, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyOtaEmergencyNumberDbInstalled();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor otaParcelFileDescriptor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (otaParcelFileDescriptor != null) {
                        _data.writeInt(1);
                        otaParcelFileDescriptor.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(256, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateOtaEmergencyNumberDbFilePath(otaParcelFileDescriptor);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void resetOtaEmergencyNumberDbFilePath() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(257, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().resetOtaEmergencyNumberDbFilePath();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean enableModemForSlot(int slotIndex, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    boolean _result = true;
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(258, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableModemForSlot(slotIndex, enable);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setMultiSimCarrierRestriction(boolean isMultiSimCarrierRestricted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isMultiSimCarrierRestricted ? 1 : 0);
                    boolean _status = this.mRemote.transact(259, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setMultiSimCarrierRestriction(isMultiSimCarrierRestricted);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int isMultiSimSupported(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(260, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMultiSimSupported(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void switchMultiSimConfig(int numOfSims) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(numOfSims);
                    boolean _status = this.mRemote.transact(261, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().switchMultiSimConfig(numOfSims);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean doesSwitchMultiSimConfigTriggerReboot(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(262, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doesSwitchMultiSimConfigTriggerReboot(subId, callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telephony.ITelephony
            public int[] getSlotsMapping() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(263, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSlotsMapping();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int getRadioHalVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(264, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioHalVersion();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCurrentPackageName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(265, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPackageName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isApplicationOnUicc(int subId, int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(appType);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(266, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isApplicationOnUicc(subId, appType);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isModemEnabledForSlot(int slotIndex, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slotIndex);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(267, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isModemEnabledForSlot(slotIndex, callingPackage, callingFeatureId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isDataEnabledForApn(int apnType, int subId, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(apnType);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(268, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataEnabledForApn(apnType, subId, callingPackage);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isApnMetered(int apnType, int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(apnType);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(269, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isApnMetered(apnType, subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setSystemSelectionChannels(List<RadioAccessSpecifier> specifiers, int subId, IBooleanConsumer resultCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(specifiers);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(resultCallback != null ? resultCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(270, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSystemSelectionChannels(specifiers, subId, resultCallback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public List<RadioAccessSpecifier> getSystemSelectionChannels(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(271, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemSelectionChannels(subId);
                    }
                    _reply.readException();
                    List<RadioAccessSpecifier> _result = _reply.createTypedArrayList(RadioAccessSpecifier.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isMvnoMatched(int subId, int mvnoType, String mvnoMatchData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(mvnoType);
                    _data.writeString(mvnoMatchData);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(272, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMvnoMatched(subId, mvnoType, mvnoMatchData);
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

            @Override // com.android.internal.telephony.ITelephony
            public void enqueueSmsPickResult(String callingPackage, String callingAttributeTag, IIntegerConsumer subIdResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingAttributeTag);
                    _data.writeStrongBinder(subIdResult != null ? subIdResult.asBinder() : null);
                    boolean _status = this.mRemote.transact(273, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enqueueSmsPickResult(callingPackage, callingAttributeTag, subIdResult);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getMmsUserAgent(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(274, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMmsUserAgent(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getMmsUAProfUrl(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(275, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMmsUAProfUrl(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setMobileDataPolicyEnabled(int subscriptionId, int policy, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subscriptionId);
                    _data.writeInt(policy);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(276, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setMobileDataPolicyEnabled(subscriptionId, policy, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isMobileDataPolicyEnabled(int subscriptionId, int policy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subscriptionId);
                    _data.writeInt(policy);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(277, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMobileDataPolicyEnabled(subscriptionId, policy);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setCepEnabled(boolean isCepEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isCepEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(278, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCepEnabled(isCepEnabled);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void notifyRcsAutoConfigurationReceived(int subId, byte[] config, boolean isCompressed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeByteArray(config);
                    _data.writeInt(isCompressed ? 1 : 0);
                    boolean _status = this.mRemote.transact(279, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().notifyRcsAutoConfigurationReceived(subId, config, isCompressed);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isIccLockEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(280, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isIccLockEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public int setIccLockEnabled(int subId, boolean enabled, String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeString(password);
                    boolean _status = this.mRemote.transact(281, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIccLockEnabled(subId, enabled, password);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int changeIccLockPassword(int subId, String oldPassword, String newPassword) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(oldPassword);
                    _data.writeString(newPassword);
                    boolean _status = this.mRemote.transact(282, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().changeIccLockPassword(subId, oldPassword, newPassword);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void requestUserActivityNotification() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(283, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUserActivityNotification();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void userActivity() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(284, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().userActivity();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getManualNetworkSelectionPlmn(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(285, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getManualNetworkSelectionPlmn(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean canConnectTo5GInDsdsMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(286, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canConnectTo5GInDsdsMode();
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

            @Override // com.android.internal.telephony.ITelephony
            public List<String> getEquivalentHomePlmns(int subId, String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(287, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEquivalentHomePlmns(subId, callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int setNrDualConnectivityState(int subId, int nrDualConnectivityState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(nrDualConnectivityState);
                    boolean _status = this.mRemote.transact(288, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNrDualConnectivityState(subId, nrDualConnectivityState);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isNrDualConnectivityEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(289, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNrDualConnectivityEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRadioInterfaceCapabilitySupported(String capability) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(capability);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(290, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRadioInterfaceCapabilitySupported(capability);
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

            @Override // com.android.internal.telephony.ITelephony
            public int sendThermalMitigationRequest(int subId, ThermalMitigationRequest thermalMitigationRequest, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (thermalMitigationRequest != null) {
                        _data.writeInt(1);
                        thermalMitigationRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(291, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendThermalMitigationRequest(subId, thermalMitigationRequest, callingPackage);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void bootstrapAuthenticationRequest(int subId, int appType, Uri nafUrl, UaSecurityProtocolIdentifier securityProtocol, boolean forceBootStrapping, IBootstrapAuthenticationCallback callback) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(subId);
                        try {
                            _data.writeInt(appType);
                            int i = 1;
                            if (nafUrl != null) {
                                _data.writeInt(1);
                                nafUrl.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            if (securityProtocol != null) {
                                _data.writeInt(1);
                                securityProtocol.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            if (!forceBootStrapping) {
                                i = 0;
                            }
                            _data.writeInt(i);
                            _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                            try {
                                boolean _status = this.mRemote.transact(292, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().bootstrapAuthenticationRequest(subId, appType, nafUrl, securityProtocol, forceBootStrapping, callback);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setBoundGbaServiceOverride(int subId, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(293, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBoundGbaServiceOverride(subId, packageName);
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

            @Override // com.android.internal.telephony.ITelephony
            public String getBoundGbaService(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(294, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBoundGbaService(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean setGbaReleaseTimeOverride(int subId, int interval) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(interval);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(295, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setGbaReleaseTimeOverride(subId, interval);
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

            @Override // com.android.internal.telephony.ITelephony
            public int getGbaReleaseTime(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(296, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGbaReleaseTime(subId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setRcsClientConfiguration(int subId, RcsClientConfiguration rcc) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (rcc != null) {
                        _data.writeInt(1);
                        rcc.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(297, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRcsClientConfiguration(subId, rcc);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean isRcsVolteSingleRegistrationCapable(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(298, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRcsVolteSingleRegistrationCapable(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public void registerRcsProvisioningCallback(int subId, IRcsConfigCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(299, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerRcsProvisioningCallback(subId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void unregisterRcsProvisioningCallback(int subId, IRcsConfigCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(300, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterRcsProvisioningCallback(subId, callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void triggerRcsReconfiguration(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(301, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().triggerRcsReconfiguration(subId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setRcsSingleRegistrationTestModeEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(302, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRcsSingleRegistrationTestModeEnabled(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getRcsSingleRegistrationTestModeEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(303, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRcsSingleRegistrationTestModeEnabled();
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

            @Override // com.android.internal.telephony.ITelephony
            public void setDeviceSingleRegistrationEnabledOverride(String enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(enabled);
                    boolean _status = this.mRemote.transact(304, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDeviceSingleRegistrationEnabledOverride(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getDeviceSingleRegistrationEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(305, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceSingleRegistrationEnabled();
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCarrierSingleRegistrationEnabledOverride(int subId, String enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(enabled);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(306, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCarrierSingleRegistrationEnabledOverride(subId, enabled);
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

            @Override // com.android.internal.telephony.ITelephony
            public void sendDeviceToDeviceMessage(int message, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(message);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(307, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().sendDeviceToDeviceMessage(message, value);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setActiveDeviceToDeviceTransport(String transport) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(transport);
                    boolean _status = this.mRemote.transact(308, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setActiveDeviceToDeviceTransport(transport);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void setDeviceToDeviceForceEnabled(boolean isForceEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isForceEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(309, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDeviceToDeviceForceEnabled(isForceEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getCarrierSingleRegistrationEnabled(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(310, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierSingleRegistrationEnabled(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setImsFeatureValidationOverride(int subId, String enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(enabled);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(311, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setImsFeatureValidationOverride(subId, enabled);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean getImsFeatureValidationOverride(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(312, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImsFeatureValidationOverride(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public String getMobileProvisioningUrl() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(313, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMobileProvisioningUrl();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int removeContactFromEab(int subId, String contacts) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(contacts);
                    boolean _status = this.mRemote.transact(314, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeContactFromEab(subId, contacts);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getContactFromEab(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    boolean _status = this.mRemote.transact(315, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContactFromEab(contact);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getCapabilityFromEab(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    boolean _status = this.mRemote.transact(316, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCapabilityFromEab(contact);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean getDeviceUceEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(317, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceUceEnabled();
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

            @Override // com.android.internal.telephony.ITelephony
            public void setDeviceUceEnabled(boolean isEnabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isEnabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(318, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDeviceUceEnabled(isEnabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public RcsContactUceCapability addUceRegistrationOverrideShell(int subId, List<String> featureTags) throws RemoteException {
                RcsContactUceCapability _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStringList(featureTags);
                    boolean _status = this.mRemote.transact(319, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addUceRegistrationOverrideShell(subId, featureTags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RcsContactUceCapability.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public RcsContactUceCapability removeUceRegistrationOverrideShell(int subId, List<String> featureTags) throws RemoteException {
                RcsContactUceCapability _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeStringList(featureTags);
                    boolean _status = this.mRemote.transact(320, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUceRegistrationOverrideShell(subId, featureTags);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RcsContactUceCapability.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public RcsContactUceCapability clearUceRegistrationOverrideShell(int subId) throws RemoteException {
                RcsContactUceCapability _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(321, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearUceRegistrationOverrideShell(subId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RcsContactUceCapability.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public RcsContactUceCapability getLatestRcsContactUceCapabilityShell(int subId) throws RemoteException {
                RcsContactUceCapability _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(322, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestRcsContactUceCapabilityShell(subId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RcsContactUceCapability.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public String getLastUcePidfXmlShell(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _status = this.mRemote.transact(323, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastUcePidfXmlShell(subId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean removeUceRequestDisallowedStatus(int subId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(324, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUceRequestDisallowedStatus(subId);
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

            @Override // com.android.internal.telephony.ITelephony
            public boolean setCapabilitiesRequestTimeout(int subId, long timeoutAfterMs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeLong(timeoutAfterMs);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(325, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCapabilitiesRequestTimeout(subId, timeoutAfterMs);
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

            @Override // com.android.internal.telephony.ITelephony
            public void setSignalStrengthUpdateRequest(int subId, SignalStrengthUpdateRequest request, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(326, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSignalStrengthUpdateRequest(subId, request, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void clearSignalStrengthUpdateRequest(int subId, SignalStrengthUpdateRequest request, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(callingPackage);
                    boolean _status = this.mRemote.transact(327, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearSignalStrengthUpdateRequest(subId, request, callingPackage);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public PhoneCapability getPhoneCapability() throws RemoteException {
                PhoneCapability _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(328, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhoneCapability();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PhoneCapability.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public int prepareForUnattendedReboot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(329, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().prepareForUnattendedReboot();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void getSlicingConfig(ResultReceiver callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(330, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getSlicingConfig(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        private boolean onTransact$sendVisualVoicemailSmsForSubscriber$(Parcel data, Parcel reply) throws RemoteException {
            PendingIntent _arg6;
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            String _arg1 = data.readString();
            int _arg2 = data.readInt();
            String _arg3 = data.readString();
            int _arg4 = data.readInt();
            String _arg5 = data.readString();
            if (data.readInt() != 0) {
                _arg6 = PendingIntent.CREATOR.createFromParcel(data);
            } else {
                _arg6 = null;
            }
            sendVisualVoicemailSmsForSubscriber(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$requestCellInfoUpdate$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            ICellInfoCallback _arg1 = ICellInfoCallback.Stub.asInterface(data.readStrongBinder());
            String _arg2 = data.readString();
            String _arg3 = data.readString();
            requestCellInfoUpdate(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$requestCellInfoUpdateWithWorkSource$(Parcel data, Parcel reply) throws RemoteException {
            WorkSource _arg4;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            ICellInfoCallback _arg1 = ICellInfoCallback.Stub.asInterface(data.readStrongBinder());
            String _arg2 = data.readString();
            String _arg3 = data.readString();
            if (data.readInt() != 0) {
                _arg4 = WorkSource.CREATOR.createFromParcel(data);
            } else {
                _arg4 = null;
            }
            requestCellInfoUpdateWithWorkSource(_arg0, _arg1, _arg2, _arg3, _arg4);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$iccOpenLogicalChannelBySlot$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            int _arg3 = data.readInt();
            IccOpenLogicalChannelResponse _result = iccOpenLogicalChannelBySlot(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            if (_result != null) {
                reply.writeInt(1);
                _result.writeToParcel(reply, 1);
            } else {
                reply.writeInt(0);
            }
            return true;
        }

        private boolean onTransact$iccOpenLogicalChannel$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            int _arg3 = data.readInt();
            IccOpenLogicalChannelResponse _result = iccOpenLogicalChannel(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            if (_result != null) {
                reply.writeInt(1);
                _result.writeToParcel(reply, 1);
            } else {
                reply.writeInt(0);
            }
            return true;
        }

        private boolean onTransact$iccTransmitApduLogicalChannelBySlot$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            int _arg3 = data.readInt();
            int _arg4 = data.readInt();
            int _arg5 = data.readInt();
            int _arg6 = data.readInt();
            String _arg7 = data.readString();
            String _result = iccTransmitApduLogicalChannelBySlot(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
            reply.writeNoException();
            reply.writeString(_result);
            return true;
        }

        private boolean onTransact$iccTransmitApduLogicalChannel$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            int _arg3 = data.readInt();
            int _arg4 = data.readInt();
            int _arg5 = data.readInt();
            int _arg6 = data.readInt();
            String _arg7 = data.readString();
            String _result = iccTransmitApduLogicalChannel(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
            reply.writeNoException();
            reply.writeString(_result);
            return true;
        }

        private boolean onTransact$iccTransmitApduBasicChannelBySlot$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            int _arg2 = data.readInt();
            int _arg3 = data.readInt();
            int _arg4 = data.readInt();
            int _arg5 = data.readInt();
            int _arg6 = data.readInt();
            String _arg7 = data.readString();
            String _result = iccTransmitApduBasicChannelBySlot(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
            reply.writeNoException();
            reply.writeString(_result);
            return true;
        }

        private boolean onTransact$iccTransmitApduBasicChannel$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            int _arg2 = data.readInt();
            int _arg3 = data.readInt();
            int _arg4 = data.readInt();
            int _arg5 = data.readInt();
            int _arg6 = data.readInt();
            String _arg7 = data.readString();
            String _result = iccTransmitApduBasicChannel(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
            reply.writeNoException();
            reply.writeString(_result);
            return true;
        }

        private boolean onTransact$iccExchangeSimIO$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            int _arg3 = data.readInt();
            int _arg4 = data.readInt();
            int _arg5 = data.readInt();
            String _arg6 = data.readString();
            byte[] _result = iccExchangeSimIO(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6);
            reply.writeNoException();
            reply.writeByteArray(_result);
            return true;
        }

        private boolean onTransact$setBoundImsServiceOverride$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            boolean _arg1 = data.readInt() != 0;
            int[] _arg2 = data.createIntArray();
            String _arg3 = data.readString();
            boolean boundImsServiceOverride = setBoundImsServiceOverride(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            reply.writeInt(boundImsServiceOverride ? 1 : 0);
            return true;
        }

        private boolean onTransact$requestNetworkScan$(Parcel data, Parcel reply) throws RemoteException {
            NetworkScanRequest _arg1;
            Messenger _arg2;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            if (data.readInt() != 0) {
                _arg1 = NetworkScanRequest.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            if (data.readInt() != 0) {
                _arg2 = Messenger.CREATOR.createFromParcel(data);
            } else {
                _arg2 = null;
            }
            IBinder _arg3 = data.readStrongBinder();
            String _arg4 = data.readString();
            String _arg5 = data.readString();
            int _result = requestNetworkScan(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$requestNumberVerification$(Parcel data, Parcel reply) throws RemoteException {
            PhoneNumberRange _arg0;
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                _arg0 = PhoneNumberRange.CREATOR.createFromParcel(data);
            } else {
                _arg0 = null;
            }
            long _arg1 = data.readLong();
            INumberVerificationCallback _arg2 = INumberVerificationCallback.Stub.asInterface(data.readStrongBinder());
            String _arg3 = data.readString();
            requestNumberVerification(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setRoamingOverride$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            List<String> _arg1 = data.createStringArrayList();
            List<String> _arg2 = data.createStringArrayList();
            List<String> _arg3 = data.createStringArrayList();
            List<String> _arg4 = data.createStringArrayList();
            boolean roamingOverride = setRoamingOverride(_arg0, _arg1, _arg2, _arg3, _arg4);
            reply.writeNoException();
            reply.writeInt(roamingOverride ? 1 : 0);
            return true;
        }

        private boolean onTransact$uploadCallComposerPicture$(Parcel data, Parcel reply) throws RemoteException {
            ParcelFileDescriptor _arg3;
            ResultReceiver _arg4;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            if (data.readInt() != 0) {
                _arg3 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
            } else {
                _arg3 = null;
            }
            if (data.readInt() != 0) {
                _arg4 = ResultReceiver.CREATOR.createFromParcel(data);
            } else {
                _arg4 = null;
            }
            uploadCallComposerPicture(_arg0, _arg1, _arg2, _arg3, _arg4);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$getServiceStateForSubscriber$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            ServiceState _result = getServiceStateForSubscriber(_arg0, _arg1, _arg2);
            reply.writeNoException();
            if (_result != null) {
                reply.writeInt(1);
                _result.writeToParcel(reply, 1);
            } else {
                reply.writeInt(0);
            }
            return true;
        }

        private boolean onTransact$setVoicemailRingtoneUri$(Parcel data, Parcel reply) throws RemoteException {
            PhoneAccountHandle _arg1;
            Uri _arg2;
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            if (data.readInt() != 0) {
                _arg1 = PhoneAccountHandle.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            if (data.readInt() != 0) {
                _arg2 = Uri.CREATOR.createFromParcel(data);
            } else {
                _arg2 = null;
            }
            setVoicemailRingtoneUri(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setVoicemailVibrationEnabled$(Parcel data, Parcel reply) throws RemoteException {
            PhoneAccountHandle _arg1;
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            if (data.readInt() != 0) {
                _arg1 = PhoneAccountHandle.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            boolean _arg2 = data.readInt() != 0;
            setVoicemailVibrationEnabled(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$getCarrierIdFromMccMnc$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            boolean _arg2 = data.readInt() != 0;
            int _result = getCarrierIdFromMccMnc(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$getCallForwarding$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            ICallForwardingInfoCallback _arg2 = ICallForwardingInfoCallback.Stub.asInterface(data.readStrongBinder());
            getCallForwarding(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setCallForwarding$(Parcel data, Parcel reply) throws RemoteException {
            CallForwardingInfo _arg1;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            if (data.readInt() != 0) {
                _arg1 = CallForwardingInfo.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            IIntegerConsumer _arg2 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
            setCallForwarding(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setCallWaitingStatus$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            boolean _arg1 = data.readInt() != 0;
            IIntegerConsumer _arg2 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
            setCallWaitingStatus(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$getClientRequestStats$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            String _arg1 = data.readString();
            int _arg2 = data.readInt();
            List<ClientRequestStats> _result = getClientRequestStats(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeTypedList(_result);
            return true;
        }

        private boolean onTransact$setSimPowerStateForSlotWithCallback$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            IIntegerConsumer _arg2 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
            setSimPowerStateForSlotWithCallback(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$getForbiddenPlmns$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            String _arg2 = data.readString();
            String _arg3 = data.readString();
            String[] _result = getForbiddenPlmns(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            reply.writeStringArray(_result);
            return true;
        }

        private boolean onTransact$setForbiddenPlmns$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            List<String> _arg2 = data.createStringArrayList();
            String _arg3 = data.readString();
            String _arg4 = data.readString();
            int _result = setForbiddenPlmns(_arg0, _arg1, _arg2, _arg3, _arg4);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$setCarrierTestOverride$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            String _arg3 = data.readString();
            String _arg4 = data.readString();
            String _arg5 = data.readString();
            String _arg6 = data.readString();
            String _arg7 = data.readString();
            String _arg8 = data.readString();
            String _arg9 = data.readString();
            setCarrierTestOverride(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$getNumberOfModemsWithSimultaneousDataConnections$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            int _result = getNumberOfModemsWithSimultaneousDataConnections(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$getRadioPowerState$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            int _result = getRadioPowerState(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$isCapable$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            boolean isCapable = isCapable(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(isCapable ? 1 : 0);
            return true;
        }

        private boolean onTransact$isAvailable$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            boolean isAvailable = isAvailable(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(isAvailable ? 1 : 0);
            return true;
        }

        private boolean onTransact$isMmTelCapabilitySupported$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            IIntegerConsumer _arg1 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
            int _arg2 = data.readInt();
            int _arg3 = data.readInt();
            isMmTelCapabilitySupported(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setVoWiFiNonPersistent$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            boolean _arg1 = data.readInt() != 0;
            int _arg2 = data.readInt();
            setVoWiFiNonPersistent(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setImsProvisioningStatusForCapability$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            boolean _arg3 = data.readInt() != 0;
            setImsProvisioningStatusForCapability(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$getImsProvisioningStatusForCapability$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            boolean imsProvisioningStatusForCapability = getImsProvisioningStatusForCapability(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(imsProvisioningStatusForCapability ? 1 : 0);
            return true;
        }

        private boolean onTransact$setRcsProvisioningStatusForCapability$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            boolean _arg2 = data.readInt() != 0;
            setRcsProvisioningStatusForCapability(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$isMmTelCapabilityProvisionedInCache$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            boolean isMmTelCapabilityProvisionedInCache = isMmTelCapabilityProvisionedInCache(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(isMmTelCapabilityProvisionedInCache ? 1 : 0);
            return true;
        }

        private boolean onTransact$cacheMmTelCapabilityProvisioning$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            boolean _arg3 = data.readInt() != 0;
            cacheMmTelCapabilityProvisioning(_arg0, _arg1, _arg2, _arg3);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setImsProvisioningInt$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            int _arg2 = data.readInt();
            int _result = setImsProvisioningInt(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$setImsProvisioningString$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            String _arg2 = data.readString();
            int _result = setImsProvisioningString(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$doesSwitchMultiSimConfigTriggerReboot$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            boolean doesSwitchMultiSimConfigTriggerReboot = doesSwitchMultiSimConfigTriggerReboot(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(doesSwitchMultiSimConfigTriggerReboot ? 1 : 0);
            return true;
        }

        private boolean onTransact$isModemEnabledForSlot$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            boolean isModemEnabledForSlot = isModemEnabledForSlot(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(isModemEnabledForSlot ? 1 : 0);
            return true;
        }

        private boolean onTransact$isDataEnabledForApn$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            String _arg2 = data.readString();
            boolean isDataEnabledForApn = isDataEnabledForApn(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(isDataEnabledForApn ? 1 : 0);
            return true;
        }

        private boolean onTransact$setSystemSelectionChannels$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            List<RadioAccessSpecifier> _arg0 = data.createTypedArrayList(RadioAccessSpecifier.CREATOR);
            int _arg1 = data.readInt();
            IBooleanConsumer _arg2 = IBooleanConsumer.Stub.asInterface(data.readStrongBinder());
            setSystemSelectionChannels(_arg0, _arg1, _arg2);
            return true;
        }

        private boolean onTransact$isMvnoMatched$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            String _arg2 = data.readString();
            boolean isMvnoMatched = isMvnoMatched(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(isMvnoMatched ? 1 : 0);
            return true;
        }

        private boolean onTransact$enqueueSmsPickResult$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            String _arg1 = data.readString();
            IIntegerConsumer _arg2 = IIntegerConsumer.Stub.asInterface(data.readStrongBinder());
            enqueueSmsPickResult(_arg0, _arg1, _arg2);
            return true;
        }

        private boolean onTransact$setMobileDataPolicyEnabled$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            boolean _arg2 = data.readInt() != 0;
            setMobileDataPolicyEnabled(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$notifyRcsAutoConfigurationReceived$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            byte[] _arg1 = data.createByteArray();
            boolean _arg2 = data.readInt() != 0;
            notifyRcsAutoConfigurationReceived(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setIccLockEnabled$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            boolean _arg1 = data.readInt() != 0;
            String _arg2 = data.readString();
            int _result = setIccLockEnabled(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$changeIccLockPassword$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            int _result = changeIccLockPassword(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$getEquivalentHomePlmns$(Parcel data, Parcel reply) throws RemoteException {
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            List<String> _result = getEquivalentHomePlmns(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeStringList(_result);
            return true;
        }

        private boolean onTransact$sendThermalMitigationRequest$(Parcel data, Parcel reply) throws RemoteException {
            ThermalMitigationRequest _arg1;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            if (data.readInt() != 0) {
                _arg1 = ThermalMitigationRequest.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            String _arg2 = data.readString();
            int _result = sendThermalMitigationRequest(_arg0, _arg1, _arg2);
            reply.writeNoException();
            reply.writeInt(_result);
            return true;
        }

        private boolean onTransact$bootstrapAuthenticationRequest$(Parcel data, Parcel reply) throws RemoteException {
            Uri _arg2;
            UaSecurityProtocolIdentifier _arg3;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            if (data.readInt() != 0) {
                _arg2 = Uri.CREATOR.createFromParcel(data);
            } else {
                _arg2 = null;
            }
            if (data.readInt() != 0) {
                _arg3 = UaSecurityProtocolIdentifier.CREATOR.createFromParcel(data);
            } else {
                _arg3 = null;
            }
            boolean _arg4 = data.readInt() != 0;
            IBootstrapAuthenticationCallback _arg5 = IBootstrapAuthenticationCallback.Stub.asInterface(data.readStrongBinder());
            bootstrapAuthenticationRequest(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$setSignalStrengthUpdateRequest$(Parcel data, Parcel reply) throws RemoteException {
            SignalStrengthUpdateRequest _arg1;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            if (data.readInt() != 0) {
                _arg1 = SignalStrengthUpdateRequest.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            String _arg2 = data.readString();
            setSignalStrengthUpdateRequest(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        private boolean onTransact$clearSignalStrengthUpdateRequest$(Parcel data, Parcel reply) throws RemoteException {
            SignalStrengthUpdateRequest _arg1;
            data.enforceInterface(DESCRIPTOR);
            int _arg0 = data.readInt();
            if (data.readInt() != 0) {
                _arg1 = SignalStrengthUpdateRequest.CREATOR.createFromParcel(data);
            } else {
                _arg1 = null;
            }
            String _arg2 = data.readString();
            clearSignalStrengthUpdateRequest(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
        }

        public static boolean setDefaultImpl(ITelephony impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITelephony getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
