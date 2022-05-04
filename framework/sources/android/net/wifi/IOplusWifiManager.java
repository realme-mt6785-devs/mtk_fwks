package android.net.wifi;

import android.os.RemoteException;
import java.util.List;
import oplus.net.wifi.HotspotClient;
/* loaded from: classes2.dex */
public interface IOplusWifiManager {
    public static final String ACTION_OPLUS_NETWORK_CONDITIONS_MEASURED = "android.net.conn.OPLUS_NETWORK_CONDITIONS_MEASURED";
    public static final int AUTOCONNECT_PORTAL_LOGIN = 2;
    public static final String DESCRIPTOR = "android.net.wifi.IOplusWifiManager";
    public static final String IOT_CONNECT_SCAN_RESULTS_AVAILABLE_ACTION = "android.net.iot.connect.wifi.SCAN_RESULTS";
    public static final int MANUCONNECT_PORTAL_LOGIN = 1;
    public static final int OPLUS_CALL_TRANSACTION_INDEX = 10000;
    public static final int OPLUS_FIRST_CALL_TRANSACTION = 10001;
    public static final int OTHER_LOGIN = 0;
    public static final int TRANSACTION_StopSnifferWithUdp = 10135;
    public static final int TRANSACTION_addActionListener = 10106;
    public static final int TRANSACTION_addAuthResult = 10080;
    public static final int TRANSACTION_checkExandsCAExist = 10027;
    public static final int TRANSACTION_checkExandsXMLCAExpired = 10028;
    public static final int TRANSACTION_checkExandsXMLExist = 10026;
    public static final int TRANSACTION_checkExandsXWifiConfigExpired = 10029;
    public static final int TRANSACTION_checkFWKSupplicantScanBusy = 10092;
    public static final int TRANSACTION_checkFWKSupportPasspoint = 10037;
    public static final int TRANSACTION_checkInternalPasspointPresetProvider = 10036;
    public static final int TRANSACTION_checkPasspointCAExist = 10031;
    public static final int TRANSACTION_checkPasspointProfileExpired = 10033;
    public static final int TRANSACTION_checkPasspointXMLCAExpired = 10032;
    public static final int TRANSACTION_checkPasspointXMLExist = 10030;
    public static final int TRANSACTION_clearWifiOCloudData = 10017;
    public static final int TRANSACTION_connectInternal = 10103;
    public static final int TRANSACTION_dealWithCloudBackupResult = 10051;
    public static final int TRANSACTION_dealWithCloudRecoveryData = 10052;
    public static final int TRANSACTION_disableDualSta = 10014;
    public static final int TRANSACTION_enable5g160MSoftAp = 10095;
    public static final int TRANSACTION_enableDualSta = 10012;
    public static final int TRANSACTION_enableDualStaByForce = 10013;
    public static final int TRANSACTION_getAllDualStaApps = 10011;
    public static final int TRANSACTION_getAllScanStatisticsList = 10111;
    public static final int TRANSACTION_getAllSlaAcceleratedApps = 10010;
    public static final int TRANSACTION_getAllSlaAppsAndStates = 10009;
    public static final int TRANSACTION_getAllowedHotspotClients = 10056;
    public static final int TRANSACTION_getAvoidChannels = 10076;
    public static final int TRANSACTION_getBlockedHotspotClients = 10055;
    public static final int TRANSACTION_getCandiateNetwork = 10104;
    public static final int TRANSACTION_getConnectedHotspotClients = 10057;
    public static final int TRANSACTION_getCurNetworkState = 10105;
    public static final int TRANSACTION_getCurrentNetwork = 10107;
    public static final int TRANSACTION_getDBSCapacity = 10073;
    public static final int TRANSACTION_getDualStaReadyState = 10078;
    public static final int TRANSACTION_getDualStaSwitchDefault = 10082;
    public static final int TRANSACTION_getFTMState = 10069;
    public static final int TRANSACTION_getHSRelease = 10040;
    public static final int TRANSACTION_getIOTConnectScanResults = 10088;
    public static final int TRANSACTION_getMatchingWifiConfig = 10025;
    public static final int TRANSACTION_getNetStateInfo = 10128;
    public static final int TRANSACTION_getOplusScanResults = 10096;
    public static final int TRANSACTION_getOplusSta2ConnectionInfo = 10015;
    public static final int TRANSACTION_getOplusSta2CurConfigKey = 10016;
    public static final int TRANSACTION_getOplusSupportedFeatures = 10002;
    public static final int TRANSACTION_getOplusWifiTransactionHelperMessenger = 10044;
    public static final int TRANSACTION_getPHYCapacity = 10074;
    public static final int TRANSACTION_getPasspointCertifiedState = 10084;
    public static final int TRANSACTION_getPasspointUserName = 10039;
    public static final int TRANSACTION_getPortalLoginType = 10024;
    public static final int TRANSACTION_getScanStatisticsList = 10110;
    public static final int TRANSACTION_getSlaAppState = 10008;
    public static final int TRANSACTION_getSlaWorkMode = 10100;
    public static final int TRANSACTION_getSnifferState = 10066;
    public static final int TRANSACTION_getSupportedChannels = 10075;
    public static final int TRANSACTION_getWifiOCloudData = 10018;
    public static final int TRANSACTION_getWifiRestrictionList = 10060;
    public static final int TRANSACTION_getWifiSharingConfiguration = 10005;
    public static final int TRANSACTION_hasOCloudDirtyData = 10022;
    public static final int TRANSACTION_iotConnectScanBusy = 10091;
    public static final int TRANSACTION_isInSnifferMode = 10137;
    public static final int TRANSACTION_isP2p5GSupported = 10045;
    public static final int TRANSACTION_isSoftap5GSupported = 10046;
    public static final int TRANSACTION_isSubwifiManuconnect = 10109;
    public static final int TRANSACTION_isSupport5g160MSoftAp = 10093;
    public static final int TRANSACTION_isSupport5g160MStaForPhoneClone = 10094;
    public static final int TRANSACTION_isSupportSnifferCaptureWithUdp = 10131;
    public static final int TRANSACTION_isWifiAutoConnectionDisabled = 10063;
    public static final int TRANSACTION_keepSnifferMode = 10136;
    public static final int TRANSACTION_manualForgetStatistics = 10034;
    public static final int TRANSACTION_notifyGameHighTemperature = 10083;
    public static final int TRANSACTION_notifyGameInfoJsonStr = 10085;
    public static final int TRANSACTION_notifyGameLatency = 10077;
    public static final int TRANSACTION_notifyGameModeState = 10058;
    public static final int TRANSACTION_notifyMeetingWorkingState = 10112;
    public static final int TRANSACTION_passpointANQPScanResults = 10035;
    public static final int TRANSACTION_releaseDualStaNetwork = 10125;
    public static final int TRANSACTION_removeFromRestrictionList = 10061;
    public static final int TRANSACTION_removeHeIeFromProbeRequest = 10071;
    public static final int TRANSACTION_removeNetworkByGlobalId = 10020;
    public static final int TRANSACTION_requestDualStaNetwork = 10124;
    public static final int TRANSACTION_requestToEnableSta2ByAPP = 10079;
    public static final int TRANSACTION_requestToReleaseSta2ByAPP = 10081;
    public static final int TRANSACTION_resumeFWKPeriodicScan = 10090;
    public static final int TRANSACTION_sendFTMCommand = 10070;
    public static final int TRANSACTION_sendIOTConnectProbeReq = 10087;
    public static final int TRANSACTION_setAllowedHotspotClients = 10054;
    public static final int TRANSACTION_setBlockedHotspotClients = 10053;
    public static final int TRANSACTION_setDirtyFlag = 10021;
    public static final int TRANSACTION_setLogDump = 10123;
    public static final int TRANSACTION_setLogOff = 10122;
    public static final int TRANSACTION_setLogOn = 10121;
    public static final int TRANSACTION_setNetworkCaptiveState = 10023;
    public static final int TRANSACTION_setP2pPowerSave = 10072;
    public static final int TRANSACTION_setPasspointCertifiedState = 10038;
    public static final int TRANSACTION_setSlaAppState = 10007;
    public static final int TRANSACTION_setSnifferParamWithUdp = 10133;
    public static final int TRANSACTION_setToWifiSnifferMode = 10132;
    public static final int TRANSACTION_setWifiAssistantPolicies = 10127;
    public static final int TRANSACTION_setWifiAutoConnectionDisabled = 10062;
    public static final int TRANSACTION_setWifiClosedByUser = 10043;
    public static final int TRANSACTION_setWifiEnabledOnlyForTest = 10138;
    public static final int TRANSACTION_setWifiRestrictionList = 10059;
    public static final int TRANSACTION_setWifiSharingConfiguration = 10006;
    public static final int TRANSACTION_startFTMMode = 10067;
    public static final int TRANSACTION_startRxSensTest = 10041;
    public static final int TRANSACTION_startSnifferMode = 10064;
    public static final int TRANSACTION_startSnifferWithUdp = 10134;
    public static final int TRANSACTION_startWifiSharing = 10003;
    public static final int TRANSACTION_stopFTMMode = 10068;
    public static final int TRANSACTION_stopRxSensTest = 10042;
    public static final int TRANSACTION_stopSnifferMode = 10065;
    public static final int TRANSACTION_stopWifiSharing = 10004;
    public static final int TRANSACTION_suspendFWKPeriodicScan = 10089;
    public static final int TRANSACTION_tryToRestoreWifiSetting = 10126;
    public static final int TRANSACTION_updateGlobalId = 10019;

    boolean checkFWKSupplicantScanBusy() throws RemoteException;

    boolean checkFWKSupportPasspoint() throws RemoteException;

    boolean checkInternalPasspointPresetProvider(String str) throws RemoteException;

    boolean checkPasspointCAExist(String str) throws RemoteException;

    boolean checkPasspointXMLCAExpired(String str) throws RemoteException;

    void clearWifiOCloudData(boolean z) throws RemoteException;

    void dealWithCloudBackupResult(List<String> list, String str) throws RemoteException;

    void dealWithCloudRecoveryData(List<String> list, String str) throws RemoteException;

    void disableDualSta() throws RemoteException;

    int enableDualSta() throws RemoteException;

    int enableDualStaByForce(boolean z) throws RemoteException;

    String[] getAllDualStaApps() throws RemoteException;

    List<OplusScanStatistics> getAllScanStatisticsList(int i) throws RemoteException;

    String[] getAllSlaAcceleratedApps() throws RemoteException;

    String[] getAllSlaAppsAndStates() throws RemoteException;

    List<HotspotClient> getAllowedHotspotClients() throws RemoteException;

    int[] getAvoidChannels() throws RemoteException;

    List<HotspotClient> getBlockedHotspotClients() throws RemoteException;

    List<HotspotClient> getConnectedHotspotClients() throws RemoteException;

    int getDBSCapacity() throws RemoteException;

    int getFTMState() throws RemoteException;

    List<ScanResult> getIOTConnectScanResults() throws RemoteException;

    String getNetStateInfo(int i) throws RemoteException;

    WifiInfo getOplusSta2ConnectionInfo() throws RemoteException;

    String getOplusSta2CurConfigKey() throws RemoteException;

    long getOplusSupportedFeatures() throws RemoteException;

    int getPHYCapacity(int i) throws RemoteException;

    boolean getPasspointCertifiedState(String str) throws RemoteException;

    String getPasspointUserName(String str) throws RemoteException;

    int getPortalLoginType() throws RemoteException;

    List<OplusScanStatistics> getScanStatisticsList(String str, int i) throws RemoteException;

    boolean getSlaAppState(String str) throws RemoteException;

    int getSlaWorkMode() throws RemoteException;

    int getSnifferState() throws RemoteException;

    int[] getSupportedChannels(int i) throws RemoteException;

    List<String> getWifiOCloudData(boolean z) throws RemoteException;

    WifiConfiguration getWifiSharingConfiguration() throws RemoteException;

    boolean hasOCloudDirtyData() throws RemoteException;

    boolean iotConnectScanBusy() throws RemoteException;

    boolean isDualStaSupported() throws RemoteException;

    boolean isP2p5GSupported() throws RemoteException;

    boolean isSoftap5GSupported() throws RemoteException;

    void notifyGameHighTemperature(int i, String str) throws RemoteException;

    void notifyGameInfoJsonStr(String str) throws RemoteException;

    void notifyGameLatency(String str, String str2) throws RemoteException;

    void notifyGameModeState(boolean z, String str) throws RemoteException;

    void notifyMeetingWorkingState(boolean z, String str) throws RemoteException;

    List<ScanResult> passpointANQPScanResults(List<ScanResult> list) throws RemoteException;

    void releaseDualStaNetwork(String str) throws RemoteException;

    void removeHeIeFromProbeRequest(boolean z) throws RemoteException;

    void removeNetworkByGlobalId(String str, String str2, boolean z) throws RemoteException;

    void requestDualStaNetwork(String str) throws RemoteException;

    void resumeFWKPeriodicScan() throws RemoteException;

    int sendFTMCommand(String str) throws RemoteException;

    boolean sendIOTConnectProbeReq(String str, int[] iArr, String str2) throws RemoteException;

    void setAllowedHotspotClients(List<HotspotClient> list) throws RemoteException;

    void setBlockedHotspotClients(List<HotspotClient> list) throws RemoteException;

    void setDirtyFlag(String str, boolean z) throws RemoteException;

    void setLogDump() throws RemoteException;

    void setLogOff() throws RemoteException;

    void setLogOn(long j, String str) throws RemoteException;

    void setNetworkCaptiveState(boolean z) throws RemoteException;

    void setP2pPowerSave(boolean z) throws RemoteException;

    boolean setPasspointCertifiedState(String str) throws RemoteException;

    boolean setSlaAppState(String str, boolean z) throws RemoteException;

    boolean setWifiAssistantPolicies(int i) throws RemoteException;

    boolean setWifiClosedByUser(boolean z) throws RemoteException;

    boolean setWifiSharingConfiguration(WifiConfiguration wifiConfiguration) throws RemoteException;

    int startFTMMode() throws RemoteException;

    boolean startRxSensTest(WifiConfiguration wifiConfiguration, String str) throws RemoteException;

    int startSnifferMode(int i, int i2, int i3, int i4) throws RemoteException;

    boolean startWifiSharing(WifiConfiguration wifiConfiguration) throws RemoteException;

    int stopFTMMode() throws RemoteException;

    void stopRxSensTest() throws RemoteException;

    int stopSnifferMode() throws RemoteException;

    boolean stopWifiSharing() throws RemoteException;

    boolean suspendFWKPeriodicScan(int i) throws RemoteException;

    int tryToRestoreWifiSetting() throws RemoteException;

    void updateGlobalId(int i, String str) throws RemoteException;
}
