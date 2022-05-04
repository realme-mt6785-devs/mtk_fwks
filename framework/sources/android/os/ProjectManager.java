package android.os;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import vendor.oplus.hardware.stability.oplus_project.V1_0.IOplusProject;
/* loaded from: classes2.dex */
public class ProjectManager {
    public static final int PMIC_ID_MAX = 7;
    public static final int PMIC_ID_MIN = 0;
    private static final String TAG = "ProjectManager";
    public static final int THEIA_NODE_ID_BLACK_SWITCH = 3;
    public static final int THEIA_NODE_ID_BRIGHT_SWITCH = 4;
    public static final int THEIA_NODE_ID_MONITOR_PARAM = 1;
    public static final int THEIA_NODE_ID_POWERKEY_REPORT = 2;
    private static IOplusProject sProjectService = null;

    private static IOplusProject getProjectService() {
        if (sProjectService == null) {
            try {
                sProjectService = IOplusProject.getService();
                Log.i(TAG, "GetProject() : " + getProject() + ", GetPcbVersion() : " + getPcbVersion() + ", GetSerialID() : " + getSerialID() + ", GetOperatorName() : " + getOperatorName() + ", GetRFType() : " + getRFType() + ", GetEngVersion() : " + getEngVersion());
            } catch (Exception e) {
                Log.e(TAG, "Failed to get oplus project hal service", e);
            }
        }
        return sProjectService;
    }

    public static int getProject() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_project();
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "get_project() failed.", e);
            return 0;
        }
    }

    public static int getPcbVersion() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_pcb_version();
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "get_pcb_version() failed.", e);
            return 0;
        }
    }

    public static String getSerialID() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_serial_ID();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get_serial_ID() failed.", e);
            return null;
        }
    }

    public static int getOperatorName() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_operator_name();
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "get_operator_name() failed.", e);
            return 0;
        }
    }

    public static int getRFType() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_rf_type();
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "get_rf_type() failed.", e);
            return 0;
        }
    }

    public static int getEngVersion() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_eng_version();
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "get_eng_version() failed.", e);
            return 0;
        }
    }

    public static String getPoffReason() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_poff_reason();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get_poff_reason() failed.", e);
            return null;
        }
    }

    public static String getPonReason() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_pon_reason();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get_pon_reason() failed.", e);
            return null;
        }
    }

    public static String getOcpReason() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_ocp();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get_ocp() failed.", e);
            return null;
        }
    }

    public static int getFtmMode() {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.get_ftmmode();
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "get_ftmmode()() failed.", e);
            return -1;
        }
    }

    public static String readTheiaNode(int theiaNodeId) {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.read_theia_node(theiaNodeId);
            }
            return "";
        } catch (RemoteException e) {
            Log.e(TAG, "read_theia_proc() failed.", e);
            return "";
        }
    }

    public static boolean writeTheiaNode(int theiaNodeId, String data) {
        try {
            IOplusProject service = getProjectService();
            if (service == null) {
                return false;
            }
            boolean result = service.write_theia_node(theiaNodeId, data);
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read_theia_proc() failed.", e);
            return false;
        }
    }

    public static boolean setShutdownDetect(String data) {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.set_shutdown_detect(data);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "setShutdownDetect() failed.", e);
            return false;
        }
    }

    public static boolean setOpBoot(String data) {
        try {
            IOplusProject service = getProjectService();
            if (service != null) {
                return service.set_opboot(data);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "set_opboot() failed.", e);
            return false;
        }
    }

    public static String getHungtask() {
        try {
            IOplusProject service = getProjectService();
            if (service == null || service.get_hungtask().equals("no_task")) {
                return null;
            }
            return service.get_hungtask();
        } catch (RemoteException e) {
            Log.e(TAG, "get_hungtask() failed.", e);
            return null;
        }
    }

    public static Map<String, String> getPmicStatus() {
        IOplusProject service;
        Map<String, String> mPmicStatusMap = new HashMap<>();
        try {
            IOplusProject service2 = getProjectService();
            if (service2 != null) {
                int pmicid = 0;
                while (pmicid >= 0 && pmicid <= 7) {
                    String mPoffStr = service2.get_opluspoff_reason(pmicid);
                    String mPonStr = service2.get_opluspon_reason(pmicid);
                    String mOcpStr = service2.get_oplusocp_status(pmicid);
                    if (!"NULL".equals(mPoffStr)) {
                        String[] mPMICPoffReason = mPoffStr.split("\\|");
                        String mPMICId = "PMIC" + mPMICPoffReason[1];
                        String mPMICL1Poffcode = mPMICPoffReason[2];
                        String mPMICL2Poffcode = mPMICPoffReason[3];
                        String mPMICPoffStr = mPMICPoffReason[4];
                        mPmicStatusMap.put(mPMICId + "_L1_poffcode", mPMICL1Poffcode);
                        mPmicStatusMap.put(mPMICId + "_L2_poffcode", mPMICL2Poffcode);
                        StringBuilder sb = new StringBuilder();
                        sb.append(mPMICId);
                        service = service2;
                        sb.append("_poff");
                        mPmicStatusMap.put(sb.toString(), mPMICPoffStr);
                        Log.v(TAG, mPMICId + "_L1_poffcode=" + mPMICL1Poffcode + " " + mPMICId + "_L2_poffcode=" + mPMICL2Poffcode + " " + mPMICId + "_poff=" + mPMICPoffStr);
                    } else {
                        service = service2;
                    }
                    if (!"NULL".equals(mPonStr)) {
                        String[] mPMICPonReason = mPonStr.split("\\|");
                        String mPMICId2 = "PMIC" + mPMICPonReason[1];
                        String mPMICPoncode = mPMICPonReason[2];
                        String mPMICPonStr = mPMICPonReason[3];
                        mPmicStatusMap.put(mPMICId2 + "_poncode", mPMICPoncode);
                        mPmicStatusMap.put(mPMICId2 + "_pon", mPMICPonStr);
                        Log.v(TAG, mPMICId2 + "_poncode=" + mPMICPoncode + " " + mPMICId2 + "_pon=" + mPMICPonStr);
                    }
                    if (!"NULL".equals(mOcpStr)) {
                        String[] mPMICOcpStatus = mOcpStr.split("\\|");
                        String mPMICId3 = "PMIC" + mPMICOcpStatus[1];
                        String mOcpStatus = mPMICOcpStatus[2];
                        mPmicStatusMap.put(mPMICId3 + "_ocp", mOcpStatus);
                        Log.v(TAG, mPMICId3 + "_ocp=" + mOcpStatus);
                    }
                    pmicid++;
                    service2 = service;
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "get pmic status() failed.", e);
        }
        return mPmicStatusMap;
    }
}
