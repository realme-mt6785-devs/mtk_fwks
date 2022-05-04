package oplus.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Slog;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusStatistics {
    private static final int APP_ID_FRAMEWORK = 20120;
    public static final int FLAG_SEND_TO_ATOM = 2;
    public static final int FLAG_SEND_TO_DCS = 1;
    private static final String IMPL_OPLUS = "com.oplus.util.OplusStatisticsImpl";
    private static final String TAG = "OplusStatistics--";
    private static volatile IOplusStatistics sInstance = null;

    /* loaded from: classes4.dex */
    public interface IOplusStatistics {
        public static final int FLAG_SEND_TO_ATOM = 2;
        public static final int FLAG_SEND_TO_DATA_CENTER = 1;

        void flush(Context context);

        void onCommon(Context context, List<EventData> list, int i);

        void onCommon(Context context, EventData eventData, int i);

        void onCommonSync(Context context, EventData eventData, int i);
    }

    public static void onCommon(Context context, String logTag, String eventId, Map<String, String> logMap, boolean uploadNow) {
        onCommon(context, (int) APP_ID_FRAMEWORK, logTag, eventId, logMap, uploadNow);
    }

    public static void onCommon(Context context, String appIdStr, String logTag, String eventId, Map<String, String> logMap, boolean uploadNow) {
        if (TextUtils.isEmpty(appIdStr)) {
            Slog.w(TAG, "onCommon: appId is null.");
            return;
        }
        int appId = -1;
        try {
            appId = Integer.parseInt(appIdStr);
        } catch (NumberFormatException e) {
            Slog.w(TAG, "onCommon: illegal appId=" + appIdStr);
        }
        if (appId != -1) {
            onCommon(context, appId, logTag, eventId, logMap, 1);
        }
    }

    public static void onCommon(Context context, int appId, String logTag, String eventId, Map<String, String> logMap, boolean uploadNow) {
        onCommon(context, appId, logTag, eventId, logMap, 1);
    }

    public static void onCommon(Context context, String logTag, String eventId, List<Map<String, String>> mapList, boolean uploadNow) {
        onCommon(context, (int) APP_ID_FRAMEWORK, logTag, eventId, mapList, uploadNow);
    }

    public static void onCommon(Context context, String appIdStr, String logTag, String eventId, List<Map<String, String>> mapList, boolean uploadNow) {
        if (TextUtils.isEmpty(appIdStr)) {
            Slog.w(TAG, "onCommon: appId is null.");
            return;
        }
        int appId = -1;
        try {
            appId = Integer.parseInt(appIdStr);
        } catch (NumberFormatException e) {
            Slog.w(TAG, "onCommon: illegal appId=" + appIdStr);
        }
        if (appId != -1) {
            onCommon(context, appId, logTag, eventId, mapList, uploadNow);
        }
    }

    public static void onCommon(Context context, int appId, String logTag, String eventId, List<Map<String, String>> mapList, boolean uploadNow) {
        List<EventData> dataList;
        if (mapList == null) {
            dataList = new ArrayList<>();
        } else {
            dataList = new ArrayList<>(mapList.size());
            for (Map<String, String> map : mapList) {
                EventData eventData = new EventData(appId, logTag, eventId);
                if (map == null) {
                    eventData.logMap = new HashMap();
                } else {
                    eventData.logMap = new HashMap(map);
                }
                dataList.add(eventData);
            }
        }
        getInstance().onCommon(context, dataList, 1);
    }

    public static void onCommon(Context context, String logTag, String eventId, Map<String, String> logMap, boolean uploadNow, int flagSendTo) {
        onCommon(context, (int) APP_ID_FRAMEWORK, logTag, eventId, logMap, flagSendTo);
    }

    public static void onCommonSync(Context context, String logTag, String eventId, Map<String, String> logMap, boolean upLoadNow) {
        EventData eventData = new EventData(APP_ID_FRAMEWORK, logTag, eventId);
        if (logMap == null) {
            eventData.logMap = new HashMap();
        } else {
            eventData.logMap = new HashMap(logMap);
        }
        getInstance().onCommonSync(context, eventData, 1);
    }

    private static void onCommon(Context context, int appId, String logTag, String eventId, Map<String, String> logMap, int flagSendTo) {
        EventData eventData = new EventData(appId, logTag, eventId);
        if (logMap == null) {
            eventData.logMap = new HashMap();
        } else {
            eventData.logMap = new HashMap(logMap);
        }
        getInstance().onCommon(context, eventData, flagSendTo);
    }

    private static IOplusStatistics getInstance() {
        if (sInstance == null) {
            synchronized (IOplusStatistics.class) {
                if (sInstance == null) {
                    sInstance = (IOplusStatistics) newInstance(IMPL_OPLUS);
                    if (sInstance == null) {
                        sInstance = new Dummy();
                    }
                }
            }
        }
        return sInstance;
    }

    static Object newInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor(new Class[0]);
            Object object = constructor.newInstance(new Object[0]);
            return object;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            Slog.e(TAG, "Reflect " + className + " exception" + e.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class Dummy implements IOplusStatistics {
        Dummy() {
        }

        @Override // oplus.util.OplusStatistics.IOplusStatistics
        public void onCommon(Context context, EventData data, int flagSendTo) {
            Slog.w(OplusStatistics.TAG, "Dummy onCommon");
        }

        @Override // oplus.util.OplusStatistics.IOplusStatistics
        public void onCommon(Context context, List<EventData> dataList, int flagSendTo) {
            Slog.w(OplusStatistics.TAG, "Dummy onCommon list");
        }

        @Override // oplus.util.OplusStatistics.IOplusStatistics
        public void onCommonSync(Context context, EventData data, int flagSendTo) {
            Slog.w(OplusStatistics.TAG, "Dummy onCommonSync");
        }

        @Override // oplus.util.OplusStatistics.IOplusStatistics
        public void flush(Context context) {
            Slog.w(OplusStatistics.TAG, "Dummy flush");
        }
    }

    /* loaded from: classes4.dex */
    public static class EventData {
        public int appId;
        public String eventId;
        public long eventTimeMs = System.currentTimeMillis();
        public Map<String, String> logMap;
        public String logTag;

        public EventData(int appId, String logTag, String eventId) {
            this.appId = appId;
            this.logTag = logTag;
            this.eventId = eventId;
        }

        public String toString() {
            return "{appId=" + this.appId + ", logTag=" + this.logTag + ", eventId=" + this.eventId + " }";
        }
    }
}
