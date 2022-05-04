package android.media;

import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes2.dex */
public class RingtoneManagerExtPlugin {
    public static final String CALENDAR_REMINDER_SOUND = "calendar_sound";
    public static final String NOTIFICATION_SOUND_SIM2 = "notification_sim2";
    public static final String OPLUS_SMS_NOTIFICATION_SOUND = "oplus_customize_sms_notification_sound";
    public static final String RINGTONE_CACHE_PATH = "ringtone_cache_path";
    public static final String RINGTONE_CACHE_PATH_SIM2 = "ringtone_cache_path_sim2";
    public static final String RINGTONE_CACHE_SIM2 = "ringtone_cache_sim2";
    public static final String RINGTONE_CACHE_TITLE = "ringtone_cache_title";
    public static final String RINGTONE_CACHE_TITLE_SIM2 = "ringtone_cache_title_sim2";
    public static final String RINGTONE_SIM2 = "ringtone_sim2";
    public static Class<?> TYPE = RefClass.load(RingtoneManagerExtPlugin.class, "android.media.RingtoneManagerExtImpl");
    public static final int TYPE_ALARM = 4;
    public static final int TYPE_NOTIFICATION = 2;
    public static final int TYPE_NOTIFICATION_CALENDAR = 32;
    public static final int TYPE_NOTIFICATION_SIM2 = 16;
    public static final int TYPE_NOTIFICATION_SMS = 8;
    public static final int TYPE_RINGTONE = 1;
    public static final int TYPE_RINGTONE_SIM2 = 64;
    public static RefStaticMethod<IRingtoneManagerExt> getInstance;
}
