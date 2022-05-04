package android.net;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes2.dex */
public class NetworkStatsHistoryExtPlugin {
    public static Class<?> TYPE = RefClass.load(NetworkStatsHistoryExtPlugin.class, "android.net.NetworkStatsHistoryExtImpl");
    @MethodParams({long.class, long.class})
    public static RefStaticMethod<Long> hookRecordHistory;
}
