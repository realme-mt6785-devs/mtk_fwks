package android.common;

import android.common.OplusFeatureList;
import android.content.Context;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
/* loaded from: classes.dex */
public class OplusFeatureManager {
    private static final String TAG = "OplusFeatureManager";
    private static OplusFeatureManager sInstance = null;
    private static final HashMap<String, Boolean> sFeatureSwitchMap = new HashMap<>();
    private static final HashMap<String, Boolean> sFeatureTraceMap = new HashMap<>();
    private static final boolean[] mFeatureDisable = new boolean[OplusFeatureList.OplusIndex.End.ordinal()];
    private static final boolean[] mFeatureTraing = new boolean[OplusFeatureList.OplusIndex.End.ordinal()];

    public OplusFeatureManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusFeatureManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusFeatureManager();
                }
            }
        }
        return sInstance;
    }

    public static void init(Context context) {
    }

    public static <T extends IOplusCommonFeature> boolean isSupport(T def) {
        int index = getIndex(def);
        boolean[] zArr = mFeatureDisable;
        boolean disable = zArr[index];
        if (disable) {
            synchronized (def.getDefault()) {
                disable = zArr[index];
            }
        }
        return !disable;
    }

    public static <T extends IOplusCommonFeature> boolean isTracing(T def) {
        int index = getIndex(def);
        boolean[] zArr = mFeatureTraing;
        boolean enable = zArr[index];
        if (enable) {
            synchronized (def.getDefault()) {
                enable = zArr[index];
            }
        }
        return enable;
    }

    public static boolean isSupport(String name) {
        synchronized (sFeatureSwitchMap) {
        }
        return true;
    }

    public static boolean isTracing(String name) {
        synchronized (sFeatureTraceMap) {
        }
        return false;
    }

    public static <T extends IOplusCommonFeature> T getTraceMonitor(T real) {
        if (real != null) {
            OplusFeatureList.OplusIndex index = real.index();
            if (!isTracing(real)) {
                return real;
            }
            InvocationHandler handler = new DynamicProxy(real, index.name());
            Class<T> cls = (Class) real.getDefault().getClass().getGenericInterfaces()[0];
            T proxy = (T) ((IOplusCommonFeature) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{cls}, handler));
            return proxy;
        }
        throw new IllegalArgumentException("params must not be null");
    }

    /* loaded from: classes.dex */
    private static class DynamicProxy implements InvocationHandler {
        private String feature;
        private Object realObj;

        public DynamicProxy(Object object, String f) {
            this.realObj = object;
            this.feature = f;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            Object result = method.invoke(this.realObj, objects);
            return result;
        }
    }

    private static <T extends IOplusCommonFeature> int getIndex(T service) {
        if (service != null) {
            int index = service.index().ordinal();
            boolean[] zArr = mFeatureDisable;
            if (index < zArr.length) {
                return index;
            }
            throw new IllegalAccessError("index = " + index + " size = " + zArr.length);
        }
        throw new IllegalArgumentException("dummy must not be null");
    }
}
