package android.debug;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.internal.R;
import com.android.internal.notification.SystemNotificationChannels;
/* loaded from: classes.dex */
public final class AdbNotifications {
    private static final String ADB_NOTIFICATION_CHANNEL_ID_TV = "usbdevicemanager.adb.tv";

    public static Notification createNotification(Context context, byte transportType) {
        int messageId;
        int titleId;
        PendingIntent pIntent;
        Resources resources = context.getResources();
        if (transportType == 0) {
            titleId = 17039626;
            messageId = 17039625;
        } else if (transportType == 1) {
            titleId = 17039629;
            messageId = 17039628;
        } else {
            throw new IllegalArgumentException("createNotification called with unknown transport type=" + ((int) transportType));
        }
        CharSequence title = resources.getText(titleId);
        CharSequence message = resources.getText(messageId);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        intent.addFlags(268468224);
        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, 1048576);
        if (resolveInfo != null) {
            intent.setPackage(resolveInfo.activityInfo.packageName);
            PendingIntent pIntent2 = PendingIntent.getActivityAsUser(context, 0, intent, 67108864, null, UserHandle.CURRENT);
            pIntent = pIntent2;
        } else {
            pIntent = null;
        }
        return new Notification.Builder(context, SystemNotificationChannels.DEVELOPER_IMPORTANT).setSmallIcon(R.drawable.stat_sys_adb).setWhen(0L).setOngoing(true).setTicker(title).setDefaults(0).setColor(context.getColor(17170460)).setContentTitle(title).setContentText(message).setContentIntent(pIntent).setVisibility(1).extend(new Notification.TvExtender().setChannelId(ADB_NOTIFICATION_CHANNEL_ID_TV)).build();
    }
}
