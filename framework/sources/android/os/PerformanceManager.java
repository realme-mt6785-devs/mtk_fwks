package android.os;

import android.os.IHwBinder;
import android.util.Log;
import vendor.oplus.hardware.performance.V1_0.IPerformance;
import vendor.oplus.hardware.performance.V1_0.ProcReqHal;
/* loaded from: classes2.dex */
public class PerformanceManager {
    private static final String TAG = "PerformanceManager";
    private static IPerformance mPerformanceService = null;
    private static IHwBinder.DeathRecipient sPerfServiceDeathRecipient = new IHwBinder.DeathRecipient() { // from class: android.os.PerformanceManager.1
        @Override // android.os.IHwBinder.DeathRecipient
        public void serviceDied(long cookie) {
            Log.e(PerformanceManager.TAG, "Emmm, I died :(");
            IPerformance unused = PerformanceManager.mPerformanceService = null;
        }
    };

    private static IPerformance getPerformanceService() {
        if (mPerformanceService == null) {
            try {
                IPerformance service = IPerformance.getService();
                mPerformanceService = service;
                service.asBinder().linkToDeath(sPerfServiceDeathRecipient, 0L);
            } catch (Exception e) {
                Log.e(TAG, "Failed to get performance hal service", e);
            }
        }
        return mPerformanceService;
    }

    public static void disableMultiThreadOptimize() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.disableMultiThreadOptimize();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "disable multi thread optimization failed.", e);
        }
    }

    public static void enableMultiThreadOptimize() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.enableMultiThreadOptimize();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "enable multi thread optimization failed.", e);
        }
    }

    public static String getHICpuLoading() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHICpuLoading();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "getHICpuLoading failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIEmcdrvIowait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIEmcdrvIowait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "getHIEmcdrvIowait failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIIowaitHung() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIIowaitHung();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "getHIIowaitHung failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHISchedLatency() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHISchedLatency();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "getHISchedLatency failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIFsyncWait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIFsyncWait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get fync wait info failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIIowait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIIowait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get iowait info failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIUfsFeature() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIUfsFeature();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get ufs feature failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIIonWait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIIonWait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get ion wait info failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIAllocWait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIAllocWait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get alloc wait info failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIDState() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIDState();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get DState info failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIKswapdLoading() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIKswapdLoading();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get kswapd loading info failed.", e);
            return null;
        }
    }

    public static ProcReqHal getHIScmCall() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHIScmCall();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get scm_call info failed.", e);
            return null;
        }
    }

    public static String getHICpuInfo() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getHICpuInfo();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get scm_call info failed.", e);
            return null;
        }
    }

    public static void setSchedAssistScene(String sceneId) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.setSchedAssistScene(sceneId);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "failed to set sched assist scene with scene_id=" + sceneId, e);
        }
    }

    public static void setSchedAssistImptTask(String imptInfo) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.setSchedAssistImptTask(imptInfo);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "failed to set sched assist impt task with info: " + imptInfo, e);
        }
    }

    public static void setSlideboost(String boost) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.setSlideboost(boost);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "failed to set slb with boost value=%d" + boost, e);
        }
    }

    public static void setFrameRate(String frameRate) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.setFrameRate(frameRate);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "failed to set frame rate with rate=" + frameRate, e);
        }
    }

    public static void setFgUids(String fgUid) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.setFgUids(fgUid);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "failed to set fg uid with uid=" + fgUid, e);
        }
    }

    public static int setProcessReclaim(String info) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.setProcessReclaim(info);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "failed to set process recalim with uid=" + info, e);
            return 0;
        }
    }

    public static int getKernelVersion() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getKernelVersion();
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "failed to get kernel version.", e);
            return -1;
        }
    }

    public static int existMemMonitor() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.existMemMonitor();
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "memmonitor is not exist", e);
            return 0;
        }
    }

    public static int writeUxState(String uxState, String pid, String tid) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.writeUxState(uxState, pid, tid);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "write ux state failed. pid is " + pid + " tid is " + tid + "ux is " + uxState, e);
            return 0;
        }
    }

    public static void enableTaskPlacementDecision() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.enableTaskPlacementDecision();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "enable TPD failed.", e);
        }
    }

    public static void disableTaskPlacementDecision() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.disableTaskPlacementDecision();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "disable TPD failed.", e);
        }
    }

    public static void setTpdSerialParams(String cmds) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.setTpdSerialParams(cmds);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "set tpd_cmds failed.", e);
        }
    }

    public static void setTpdID(String param) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.setTpdID(param);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "set tpd_id failed.", e);
        }
    }

    public static void enableKmallocDebug() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.enableKmallocDebug();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "enable Kmalloc debug failed", e);
        }
    }

    public static void disableKmallocDebug() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.disableKmallocDebug();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "disable Kmalloc debug failed", e);
        }
    }

    public static void enableProcessReclaim() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.enableProcessReclaim();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "eable process reclaim failed", e);
        }
    }

    public static void disableProcessReclaim() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.disableProcessReclaim();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "disable process recalim failed", e);
        }
    }

    public static void enableVmallocDebug() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.enableVmallocDebug();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "disable vmalloc debug failed", e);
        }
    }

    public static void disableVmallocDebug() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.disableVmallocDebug();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "disable vmalloc debug failed", e);
        }
    }

    public static int writeKmallocDebugCreate(int param) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.writeKmallocDebugCreate(param);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "write Kmallocdebug info failed.", e);
            return 0;
        }
    }

    public static String readKmallocDebugCreate() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.readKmallocDebugCreate();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read Kmallocdebug info failed.", e);
            return null;
        }
    }

    public static String getSchedlatency() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getschedlatency();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read schedlatency info failed.", e);
            return null;
        }
    }

    public static String getIowait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getiowait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read getiowait info failed.", e);
            return null;
        }
    }

    public static String getFsyncwait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getfsyncwait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read getfsyncwait info failed.", e);
            return null;
        }
    }

    public static String getIonwait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getionwait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read getionwait info failed.", e);
            return null;
        }
    }

    public static String getAllocwait() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getallocwait();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read getallocwait info failed.", e);
            return null;
        }
    }

    public static String getDstate() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getdstate();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read getdstate info failed.", e);
            return null;
        }
    }

    public static String readKmallocOrigin() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getKmallocOrigin();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get KmallocOrigin info failed.", e);
            return null;
        }
    }

    public static String readKmallocUsed() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getKmallocUsed();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get KmallocUsed info failed.", e);
            return null;
        }
    }

    public static int writeVaFeature(int param) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.writeVaFeature(param);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "write writeVaFeature info failed.", e);
            return 0;
        }
    }

    public static String readVaFeature() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.readVaFeature();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "read readVaFeature info failed.", e);
            return null;
        }
    }

    public static String readVmallocDebug() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getVmallocDebug();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get VmallocDebug info failed.", e);
            return null;
        }
    }

    public static String readVmallocUsed() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getVmallocUsed();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get VmallocUsed info failed.", e);
            return null;
        }
    }

    public static int writeMemleakDetectThread(int param) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.writeMemleakDetectThread(param);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "write writeMemleakDetectThread info failed.", e);
            return 0;
        }
    }

    public static String readMemleakDetectThread() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.readMemleakDetectThread();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "write readMemleakDetectThread info failed.", e);
            return null;
        }
    }

    public static String readVmallocHashCal() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getVmallocHashCal();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get VmallocHashCal info failed.", e);
            return null;
        }
    }

    public static String getMemMonitor() {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.getMemMonitor();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "get mem monitor info failed.", e);
            return null;
        }
    }

    public static int writeMemMonitor(String buffer) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                return service.writeMemMonitor(buffer);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "write mem monitor failed.", e);
            return 0;
        }
    }

    public static String readJankCpuInfo() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankCpuInfo();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read cpu_info failed.", e);
            return null;
        }
    }

    public static String readJankCpuInfoSig() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankCpuInfoSig();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read cpu_info_sig failed.", e);
            return null;
        }
    }

    public static String readJankCpuLoad() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankCpuLoad();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read cpu_load failed.", e);
            return null;
        }
    }

    public static String readJankCpuLoad32() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankCpuLoad32();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read cpu_load32 failed.", e);
            return null;
        }
    }

    public static String readJankCpuLoad32Scale() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankCpuLoad32Scale();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read cpu_load32_scale failed.", e);
            return null;
        }
    }

    public static String readJankCpuIndicator() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankCpuIndicator();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read load_indicator failed.", e);
            return null;
        }
    }

    public static String readJankTaskTrackByPid(int pid) {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankTaskTrackByPid(pid);
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "readJankTaskTrackByPid failed.", e);
            return null;
        }
    }

    public static String readJankTaskTrack() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankTaskTrack();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read task_track failed.", e);
            return null;
        }
    }

    public static void writeJankTaskTrackEnable(boolean enable) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.writeJankTaskTrackEnable(enable);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "write task_track_enable failed.", e);
        }
    }

    public static String readJankVersion() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readJankVersion();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "read version failed.", e);
            return null;
        }
    }

    public static boolean isJankTaskTrackEnable() {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return false;
            }
            boolean result = service.isJankTaskTrackEnable();
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "isJankTaskTrackEnable failed.", e);
            return false;
        }
    }

    public static String readUxTaskTrack(int uPid, int rPid) {
        try {
            IPerformance service = getPerformanceService();
            if (service == null) {
                return null;
            }
            String result = service.readUxTaskTrack(uPid, rPid);
            return result;
        } catch (RemoteException e) {
            Log.e(TAG, "readUxTaskTrack failed.", e);
            return null;
        }
    }

    public static void addTaskTrackPid(int group, int pid, boolean clear) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.addTaskTrackPid(group, pid, clear);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "addTaskTrackPid failed.", e);
        }
    }

    public static void removeTaskTrackPid(int type, int pid) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.removeTaskTrackPid(type, pid);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "removeTaskTrackPid failed.", e);
        }
    }

    public static void clearTaskTrackGroup(int group) {
        try {
            IPerformance service = getPerformanceService();
            if (service != null) {
                service.clearTaskTrackGroup(group);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "clearTaskTrackGroup failed.", e);
        }
    }
}
