package com.mediatek.server.anr;

import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.ServiceManager;
import com.android.server.am.ActivityManagerService;
import com.android.server.am.ProcessErrorStateRecord;
import com.android.server.am.ProcessRecord;
import java.util.UUID;
/* loaded from: classes.dex */
public class AnrManagerImpl extends AnrManager {
    private AnrManagerService mService = AnrManagerService.getInstance();

    /* JADX WARN: Type inference failed for: r0v0, types: [com.mediatek.server.anr.AnrManagerService, android.os.IBinder] */
    public void AddAnrManagerService() {
        ServiceManager.addService("anrmanager", (IBinder) this.mService, true);
    }

    public void startAnrManagerService(int pid) {
        this.mService.startAnrManagerService(pid);
    }

    public boolean isAnrDeferrable() {
        return this.mService.isAnrDeferrable();
    }

    public boolean delayMessage(Handler mHandler, Message msg, int msgId, int time) {
        if (!isAnrDeferrable()) {
            return false;
        }
        Message nmsg = mHandler.obtainMessage(msgId);
        nmsg.obj = msg.obj;
        mHandler.sendMessageDelayed(nmsg, time);
        return true;
    }

    public void writeEvent(int event) {
        this.mService.writeEvent(event);
    }

    public void sendBroadcastMonitorMessage(long timeoutTime, long timeoutPeriod) {
        this.mService.sendBroadcastMonitorMessage(timeoutTime, timeoutPeriod);
    }

    public void removeBroadcastMonitorMessage() {
        this.mService.removeBroadcastMonitorMessage();
    }

    public void sendServiceMonitorMessage() {
        this.mService.sendServiceMonitorMessage();
    }

    public void removeServiceMonitorMessage() {
        this.mService.removeServiceMonitorMessage();
    }

    public boolean startAnrDump(ActivityManagerService service, ProcessErrorStateRecord processESR, String activityShortComponentName, ApplicationInfo apInfo, String parentShortComponentName, ProcessRecord parentProcess, boolean aboveSystem, String annotation, boolean showBackground, long anrTime, boolean onlyDumpSelf, UUID uuid) {
        try {
            return this.mService.startAnrDump(service, processESR, activityShortComponentName, apInfo, parentShortComponentName, parentProcess, aboveSystem, annotation, showBackground, anrTime, onlyDumpSelf, uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
