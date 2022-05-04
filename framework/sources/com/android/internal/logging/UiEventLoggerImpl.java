package com.android.internal.logging;

import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.FrameworkStatsLog;
/* loaded from: classes4.dex */
public class UiEventLoggerImpl implements UiEventLogger {
    @Override // com.android.internal.logging.UiEventLogger
    public void log(UiEventLogger.UiEventEnum event) {
        log(event, 0, null);
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void log(UiEventLogger.UiEventEnum event, int uid, String packageName) {
        int eventID = event.getId();
        if (eventID > 0) {
            FrameworkStatsLog.write(90, eventID, uid, packageName, 0);
        }
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void logWithInstanceId(UiEventLogger.UiEventEnum event, int uid, String packageName, InstanceId instance) {
        int eventID = event.getId();
        if (eventID <= 0 || instance == null) {
            log(event, uid, packageName);
        } else {
            FrameworkStatsLog.write(90, eventID, uid, packageName, instance.getId());
        }
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void logWithPosition(UiEventLogger.UiEventEnum event, int uid, String packageName, int position) {
        int eventID = event.getId();
        if (eventID > 0) {
            FrameworkStatsLog.write(260, eventID, packageName, 0, position);
        }
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void logWithInstanceIdAndPosition(UiEventLogger.UiEventEnum event, int uid, String packageName, InstanceId instance, int position) {
        int eventID = event.getId();
        if (eventID <= 0 || instance == null) {
            logWithPosition(event, uid, packageName, position);
        } else {
            FrameworkStatsLog.write(260, eventID, packageName, instance.getId(), position);
        }
    }
}
