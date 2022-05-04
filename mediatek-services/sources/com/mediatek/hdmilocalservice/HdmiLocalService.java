package com.mediatek.hdmilocalservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.os.UEventObserver;
import android.os.UserHandle;
import android.util.Slog;
import com.android.server.SystemService;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/* loaded from: classes.dex */
public class HdmiLocalService extends SystemService {
    private static final boolean HDMI_TB_SUPPORT = !"".equals(SystemProperties.get("ro.vendor.mtk_tb_hdmi"));
    private final String TAG = "HdmiLocalService";
    private Context mContext;
    private HdmiObserver mHdmiObserver;

    public HdmiLocalService(Context context) {
        super(context);
        this.mContext = context;
    }

    public void onStart() {
        Slog.d("HdmiLocalService", "Start HdmiLocalService");
    }

    public void onBootPhase(int phase) {
        if (phase == 1000) {
            Slog.d("HdmiLocalService", "Do something in this phase(1000)");
            if (HDMI_TB_SUPPORT && this.mHdmiObserver == null) {
                HdmiObserver hdmiObserver = new HdmiObserver(this.mContext);
                this.mHdmiObserver = hdmiObserver;
                hdmiObserver.startObserve();
            }
        }
    }

    /* loaded from: classes.dex */
    private class HdmiObserver extends UEventObserver {
        private static final String HDMI_NAME_PATH = "/sys/class/switch/hdmi/name";
        private static final String HDMI_NOTIFICATION_CHANNEL_ID = "hdmi_notification_channel";
        private static final String HDMI_NOTIFICATION_NAME = "HDMI";
        private static final String HDMI_STATE_PATH = "/sys/class/switch/hdmi/state";
        private static final String HDMI_UEVENT_MATCH = "DEVPATH=/devices/virtual/switch/hdmi";
        private static final int MSG_HDMI_PLUG_IN = 10;
        private static final int MSG_HDMI_PLUG_OUT = 11;
        private static final String TAG = "HdmiLocalService.HdmiObserver";
        private final Context mCxt;
        private String mHdmiName;
        private int mHdmiState;
        private int mPrevHdmiState;
        private final PowerManager.WakeLock mWakeLock;

        public HdmiObserver(Context context) {
            this.mCxt = context;
            PowerManager pm = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock newWakeLock = pm.newWakeLock(26, "HdmiObserver");
            this.mWakeLock = newWakeLock;
            newWakeLock.setReferenceCounted(false);
            init();
        }

        public void startObserve() {
            startObserving(HDMI_UEVENT_MATCH);
        }

        public void stopObserve() {
            stopObserving();
        }

        public void onUEvent(UEventObserver.UEvent event) {
            Slog.d(TAG, "HdmiObserver: onUEvent: " + event.toString());
            String name = event.get("SWITCH_NAME");
            int state = 0;
            try {
                state = Integer.parseInt(event.get("SWITCH_STATE"));
            } catch (NumberFormatException e) {
                Slog.w(TAG, "HdmiObserver: Could not parse switch state from event " + event);
            }
            Slog.d(TAG, "HdmiObserver.onUEvent(), name=" + name + ", state=" + state);
            update(name, state);
        }

        private void init() {
            String str = this.mHdmiName;
            int newState = this.mHdmiState;
            this.mPrevHdmiState = this.mHdmiState;
            String newName = getContentFromFile(HDMI_NAME_PATH);
            try {
                String mState = getContentFromFile(HDMI_STATE_PATH);
                if (mState != null) {
                    newState = Integer.parseInt(mState);
                }
                update(newName, newState);
            } catch (NullPointerException | NumberFormatException e) {
                Slog.w(TAG, "HDMI state fail");
            }
        }

        private String getContentFromFile(String filePath) {
            StringBuilder sb;
            char[] buffer = new char[1024];
            FileReader reader = null;
            String content = null;
            try {
                try {
                    try {
                        reader = new FileReader(filePath);
                        int len = reader.read(buffer, 0, buffer.length);
                        content = String.valueOf(buffer, 0, len).trim();
                        Slog.d(TAG, filePath + " content is " + content);
                    } catch (Throwable th) {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                Slog.w(TAG, "close reader fail: " + e.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    Slog.w(TAG, "IO exception when read file " + filePath);
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e3) {
                            e = e3;
                            sb = new StringBuilder();
                            sb.append("close reader fail: ");
                            sb.append(e.getMessage());
                            Slog.w(TAG, sb.toString());
                            return content;
                        }
                    }
                }
                try {
                    reader.close();
                } catch (IOException e4) {
                    e = e4;
                    sb = new StringBuilder();
                    sb.append("close reader fail: ");
                    sb.append(e.getMessage());
                    Slog.w(TAG, sb.toString());
                    return content;
                }
            } catch (FileNotFoundException e5) {
                Slog.w(TAG, "can't find file " + filePath);
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e6) {
                        e = e6;
                        sb = new StringBuilder();
                        sb.append("close reader fail: ");
                        sb.append(e.getMessage());
                        Slog.w(TAG, sb.toString());
                        return content;
                    }
                }
            } catch (IndexOutOfBoundsException e7) {
                Slog.w(TAG, "index exception: " + e7.getMessage());
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e8) {
                        e = e8;
                        sb = new StringBuilder();
                        sb.append("close reader fail: ");
                        sb.append(e.getMessage());
                        Slog.w(TAG, sb.toString());
                        return content;
                    }
                }
            }
            return content;
        }

        private void update(String newName, int newState) {
            Slog.d(TAG, "HDMIOberver.update(), oldState=" + this.mHdmiState + ", newState=" + newState);
            int i = this.mHdmiState;
            int i2 = newState | i;
            this.mHdmiName = newName;
            this.mPrevHdmiState = i;
            this.mHdmiState = newState;
            if (newState == 0) {
                this.mWakeLock.release();
                handleNotification(false);
                Slog.d(TAG, "HDMIOberver.update(), release");
                return;
            }
            this.mWakeLock.acquire();
            handleNotification(true);
            Slog.d(TAG, "HDMIOberver.update(), acquire");
        }

        private void handleNotification(boolean showNoti) {
            NotificationManager notificationManager = (NotificationManager) this.mCxt.getSystemService("notification");
            if (notificationManager == null) {
                Slog.w(TAG, "Fail to get NotificationManager");
            } else if (showNoti) {
                Slog.d(TAG, "Show notification now");
                notificationManager.createNotificationChannel(new NotificationChannel(HDMI_NOTIFICATION_CHANNEL_ID, HDMI_NOTIFICATION_NAME, 2));
                Notification notification = new Notification.Builder(this.mCxt, HDMI_NOTIFICATION_CHANNEL_ID).build();
                String titleStr = this.mCxt.getResources().getString(134545619);
                String contentStr = this.mCxt.getResources().getString(134545618);
                notification.icon = 134348898;
                notification.tickerText = titleStr;
                notification.flags = 35;
                Intent intent = Intent.makeRestartActivityTask(new ComponentName("com.android.settings", "com.android.settings.HdmiSettings"));
                PendingIntent pendingIntent = PendingIntent.getActivityAsUser(this.mCxt, 0, intent, 67108864, null, UserHandle.CURRENT);
                notification.setLatestEventInfo(this.mCxt, titleStr, contentStr, pendingIntent);
                notificationManager.notifyAsUser(null, 134348898, notification, UserHandle.CURRENT);
            } else {
                Slog.d(TAG, "Clear notification now");
                notificationManager.cancelAsUser(null, 134348898, UserHandle.CURRENT);
            }
        }
    }
}
