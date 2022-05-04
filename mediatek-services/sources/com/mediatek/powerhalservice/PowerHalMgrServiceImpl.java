package com.mediatek.powerhalservice;

import android.content.Context;
import android.os.Binder;
import android.os.IRemoteCallback;
import android.util.Log;
import com.mediatek.boostframework.Performance;
import com.mediatek.powerhalmgr.DupLinkInfo;
import com.mediatek.powerhalmgr.IPowerHalMgr;
import com.mediatek.powerhalwrapper.PowerHalWrapper;
/* loaded from: classes.dex */
public class PowerHalMgrServiceImpl extends IPowerHalMgr.Stub {
    private final String TAG = "PowerHalMgrServiceImpl";
    private PowerHalWifiMonitor mPowerHalWifiMonitor;
    private static PowerHalWrapper mPowerHalWrap = null;
    private static int mhandle = 0;
    private static Performance mPerformance = new Performance();

    public PowerHalMgrServiceImpl(Context context) {
        this.mPowerHalWifiMonitor = null;
        mPowerHalWrap = PowerHalWrapper.getInstance();
        this.mPowerHalWifiMonitor = new PowerHalWifiMonitor(context);
    }

    public int scnReg() {
        loge("scnReg not supported");
        return 0;
    }

    public void scnConfig(int handle, int cmd, int param_1, int param_2, int param_3, int param_4) {
        loge("scnConfig not supported");
    }

    public void scnUnreg(int handle) {
        loge("scnUnreg not supported");
    }

    public void scnEnable(int handle, int timeout) {
        loge("scnEnable not supported");
    }

    public void scnDisable(int handle) {
        loge("scnDisable not supported");
    }

    public void scnUltraCfg(int handle, int ultracmd, int param_1, int param_2, int param_3, int param_4) {
        loge("scnUltraCfg not supported");
    }

    public void mtkPowerHint(int hint, int data) {
        mPowerHalWrap.mtkPowerHint(hint, data);
    }

    public void mtkCusPowerHint(int hint, int data) {
        mPowerHalWrap.mtkCusPowerHint(hint, data);
    }

    public void getCpuCap() {
        loge("getCpuCap not supported");
    }

    public void getGpuCap() {
        loge("getGpuCap not supported");
    }

    public void getGpuRTInfo() {
        loge("getGpuRTInfo not supported");
    }

    public void getCpuRTInfo() {
        loge("getCpuRTInfo not supported");
    }

    public void UpdateManagementPkt(int type, String packet) {
        mPowerHalWrap.UpdateManagementPkt(type, packet);
    }

    public void setForegroundSports() {
        loge("setForegroundSports not supported");
    }

    public void setSysInfo(int type, String data) {
        loge("setSysInfo not supported");
    }

    private boolean checkDppPermission() {
        int uid = Binder.getCallingUid();
        if (mPowerHalWrap.getRildCap(uid)) {
            return true;
        }
        logd("checkDppPermission(), no permission");
        return false;
    }

    public boolean startDuplicatePacketPrediction() {
        loge("startDuplicatePacketPrediction not supported");
        return false;
    }

    public boolean stopDuplicatePacketPrediction() {
        loge("stopDuplicatePacketPrediction not supported");
        return false;
    }

    public boolean isDupPacketPredictionStarted() {
        loge("isDupPacketPredictionStarted not supported");
        return false;
    }

    public boolean registerDuplicatePacketPredictionEvent(IRemoteCallback listener) {
        loge("registerDuplicatePacketPredictionEvent not supported");
        return false;
    }

    public boolean unregisterDuplicatePacketPredictionEvent(IRemoteCallback listener) {
        loge("unregisterDuplicatePacketPredictionEvent not supported");
        return false;
    }

    public boolean updateMultiDuplicatePacketLink(DupLinkInfo[] linkList) {
        loge("updateMultiDuplicatePacketLink not supported");
        return false;
    }

    public void setPredictInfo(String pack_name, int uid) {
        String data = pack_name + " " + uid;
        logd("setPredictInfo:" + data);
        mPowerHalWrap.setSysInfoAsync(7, data);
    }

    public boolean setPriorityByUid(int action, int uid) {
        log("setPriorityByUid:" + action + uid);
        if (action == 1) {
            String data = "SET " + uid;
            mPowerHalWrap.setSysInfoAsync(15, data);
            return true;
        } else if (action == 2) {
            String data2 = "CLEAR " + uid;
            mPowerHalWrap.setSysInfoAsync(15, data2);
            return true;
        } else {
            loge("[setPriorityByLinkinfo] invalide action" + action);
            return false;
        }
    }

    public boolean setPriorityByLinkinfo(int action, DupLinkInfo linkinfo) {
        String data;
        if (linkinfo == null) {
            return false;
        }
        if (!PowerHalAddressUtils.isIpPairValid(linkinfo.getSrcIp(), linkinfo.getDstIp(), linkinfo.getSrcPort(), linkinfo.getDstPort())) {
            loge("[setPriorityByLinkinfo] invalide input paremeters");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        if (action == 1) {
            sb.append("SET ");
        } else if (action == 2) {
            sb.append("CLEAR ");
        } else {
            loge("[setPriorityByLinkinfo] invalide action");
            return false;
        }
        String strSrcPort = linkinfo.getSrcPort() == -1 ? "none" : Integer.toString(linkinfo.getSrcPort());
        String dstSrcPort = linkinfo.getDstPort() == -1 ? "none" : Integer.toString(linkinfo.getDstPort());
        String data2 = linkinfo.getSrcIp() + " " + strSrcPort + " " + linkinfo.getDstIp() + " " + dstSrcPort + " ";
        if (linkinfo.getProto() == 1) {
            data = data2 + "TCP";
        } else if (linkinfo.getProto() == 2) {
            data = data2 + "UDP";
        } else if (linkinfo.getProto() == -1) {
            data = data2 + "none";
        } else {
            loge("[setPriorityByLinkinfo] unknown protocol:" + linkinfo.getProto());
            return false;
        }
        sb.append(data);
        String Linkcmd = sb.toString();
        log("setPriorityByLinkinfo:" + Linkcmd);
        mPowerHalWrap.setSysInfoAsync(16, Linkcmd);
        return true;
    }

    public boolean flushPriorityRules(int type) {
        log("flushPriorityRules:" + type);
        if (type == 1) {
            mPowerHalWrap.setSysInfoAsync(17, "UID");
            return true;
        } else if (type == 2) {
            mPowerHalWrap.setSysInfoAsync(17, "LINKINFO");
            return true;
        } else if (type == 3) {
            mPowerHalWrap.setSysInfoAsync(17, "ALL");
            return true;
        } else {
            loge("[flushPriorityRules] unknown type:" + type);
            return false;
        }
    }

    public int perfLockAcquire(int handle, int duration, int[] list) {
        if (list.length % 2 != 0) {
            return -1;
        }
        logd("perfLockAcquire hdl:" + handle + " dur:" + duration + " len:" + list.length);
        for (int i = 0; i < list.length; i += 2) {
            logd("perfLockAcquire " + i + " id:" + Integer.toHexString(list[i]) + " value:" + list[i + 1]);
        }
        return mPowerHalWrap.perfLockAcquire(handle, duration, list);
    }

    public void perfLockRelease(int handle) {
        mPowerHalWrap.perfLockRelease(handle);
    }

    public int querySysInfo(int cmd, int param) {
        return mPowerHalWrap.querySysInfo(cmd, param);
    }

    public int perfCusLockHint(int hint, int duration) {
        return mPowerHalWrap.perfCusLockHint(hint, duration);
    }

    public int setSysInfoSync(int type, String data) {
        loge("setSysInfoSync not supported");
        return 0;
    }

    private void log(String info) {
        Log.i("PowerHalMgrServiceImpl", info + " ");
    }

    private void logd(String info) {
        Log.d("PowerHalMgrServiceImpl", info + " ");
    }

    private void loge(String info) {
        Log.e("PowerHalMgrServiceImpl", "ERR: " + info + " ");
    }
}
