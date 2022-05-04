package com.oplus.content;

import android.app.PropertyInvalidatedCache;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import com.oplus.annotation.OplusFeature;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes4.dex */
public class OplusFeatureCache {
    public static final int ALL_TYPE = 6;
    private static final String TAG = "OplusFeatureCache";
    private static final ArrayList<String> READONLYFEATURES = new ArrayList<>();
    private static final ArrayList<String> MEMORYFEATURES = new ArrayList<>();
    private static final ArrayList<String> PERSISTFEATURES = new ArrayList<>();
    private static final ArrayList<String> READONLYNATIVEFEATURES = new ArrayList<>();
    private static final ArrayList<String> MEMORYNATIVEFEATURES = new ArrayList<>();
    private static final ArrayList<String> PERSISTNATIVEFEATURES = new ArrayList<>();
    private static final PropertyInvalidatedCache<HasOplusFeatureQuery, Boolean> sReadonlyFeatureCache = new PropertyInvalidatedCache<HasOplusFeatureQuery, Boolean>(256, "sys.cache.has_feature_readonly") { // from class: com.oplus.content.OplusFeatureCache.1
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(HasOplusFeatureQuery query) {
            try {
                return Boolean.valueOf(OplusFeatureConfigManager.getInstance().hasFeatureIPC(query.name));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };
    private static final PropertyInvalidatedCache<HasOplusFeatureQuery, Boolean> sPersistFeatureCache = new PropertyInvalidatedCache<HasOplusFeatureQuery, Boolean>(256, "sys.cache.has_feature_persist") { // from class: com.oplus.content.OplusFeatureCache.2
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(HasOplusFeatureQuery query) {
            try {
                return Boolean.valueOf(OplusFeatureConfigManager.getInstance().hasFeatureIPC(query.name));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };
    private static final PropertyInvalidatedCache<HasOplusFeatureQuery, Boolean> sMemoryFeatureCache = new PropertyInvalidatedCache<HasOplusFeatureQuery, Boolean>(256, "sys.cache.has_feature_memory") { // from class: com.oplus.content.OplusFeatureCache.3
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(HasOplusFeatureQuery query) {
            try {
                return Boolean.valueOf(OplusFeatureConfigManager.getInstance().hasFeatureIPC(query.name));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };

    public OplusFeatureCache() {
        init();
    }

    private void init() {
        StringBuilder sb;
        long endTime;
        long startTime = System.currentTimeMillis();
        try {
            try {
                Field[] fields = IOplusFeatureConfigList.class.getDeclaredFields();
                for (Field field : fields) {
                    boolean isAnnotation = field.isAnnotationPresent(OplusFeature.class);
                    if (isAnnotation) {
                        String featureName = (String) field.get(null);
                        OplusFeature annotation = (OplusFeature) field.getDeclaredAnnotation(OplusFeature.class);
                        if (annotation != null) {
                            switch (AnonymousClass4.$SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType[annotation.value().ordinal()]) {
                                case 1:
                                    READONLYFEATURES.add(featureName);
                                    continue;
                                case 2:
                                    MEMORYFEATURES.add(featureName);
                                    continue;
                                case 3:
                                    PERSISTFEATURES.add(featureName);
                                    continue;
                                case 4:
                                    READONLYNATIVEFEATURES.add(featureName);
                                    continue;
                                case 5:
                                    MEMORYNATIVEFEATURES.add(featureName);
                                    continue;
                                case 6:
                                    PERSISTNATIVEFEATURES.add(featureName);
                                    continue;
                                default:
                                    Log.i(TAG, "Unknow type = " + annotation.value());
                                    continue;
                            }
                        }
                    }
                }
                endTime = System.currentTimeMillis();
                sb = new StringBuilder();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                endTime = System.currentTimeMillis();
                sb = new StringBuilder();
            }
            sb.append("Milliseconds spent on init(): ");
            sb.append(endTime - startTime);
            Log.i(TAG, sb.toString());
        } catch (Throwable th) {
            long endTime2 = System.currentTimeMillis();
            Log.i(TAG, "Milliseconds spent on init(): " + (endTime2 - startTime));
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.oplus.content.OplusFeatureCache$4  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType;

        static {
            int[] iArr = new int[OplusFeature.OplusFeatureType.values().length];
            $SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType = iArr;
            try {
                iArr[OplusFeature.OplusFeatureType.READONLY_FEATURE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType[OplusFeature.OplusFeatureType.MEMORY_FEATURE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType[OplusFeature.OplusFeatureType.PERSIST_FEATURE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType[OplusFeature.OplusFeatureType.READONLY_NATIVE_FEATURE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType[OplusFeature.OplusFeatureType.MEMORY_NATIVE_FEATURE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$oplus$annotation$OplusFeature$OplusFeatureType[OplusFeature.OplusFeatureType.PERSIST_NATIVE_FEATURE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class HasOplusFeatureQuery {
        public final String name;
        public final int type;

        public HasOplusFeatureQuery(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String toString() {
            return String.format("HasOplusFeatureQuery(name=\"%s\", type=%d)", this.name, Integer.valueOf(this.type));
        }

        public boolean equals(Object o) {
            if (!(o instanceof HasOplusFeatureQuery)) {
                return false;
            }
            HasOplusFeatureQuery r = (HasOplusFeatureQuery) o;
            return Objects.equals(this.name, r.name) && this.type == r.type;
        }

        public int hashCode() {
            return Objects.hashCode(this.name);
        }
    }

    public boolean query(String name) {
        if (MEMORYFEATURES.contains(name)) {
            return sMemoryFeatureCache.query(new HasOplusFeatureQuery(name, OplusFeature.OplusFeatureType.MEMORY_FEATURE.ordinal())).booleanValue();
        }
        if (PERSISTFEATURES.contains(name)) {
            return sPersistFeatureCache.query(new HasOplusFeatureQuery(name, OplusFeature.OplusFeatureType.PERSIST_FEATURE.ordinal())).booleanValue();
        }
        if (READONLYFEATURES.contains(name)) {
            return sReadonlyFeatureCache.query(new HasOplusFeatureQuery(name, OplusFeature.OplusFeatureType.READONLY_FEATURE.ordinal())).booleanValue();
        }
        if (isNativeFeature(name)) {
            return SystemProperties.getBoolean(name, false);
        }
        Log.e(TAG, "invalid oplus feature " + name);
        return false;
    }

    private boolean isNativeFeature(String name) {
        if (MEMORYNATIVEFEATURES.contains(name) || READONLYNATIVEFEATURES.contains(name) || PERSISTNATIVEFEATURES.contains(name)) {
            return true;
        }
        return false;
    }

    public static void disableOplusFeatureCache(int featureType) {
        if (featureType == OplusFeature.OplusFeatureType.MEMORY_FEATURE.ordinal()) {
            sMemoryFeatureCache.disableLocal();
        } else if (featureType == OplusFeature.OplusFeatureType.PERSIST_FEATURE.ordinal()) {
            sPersistFeatureCache.disableLocal();
        } else if (featureType == OplusFeature.OplusFeatureType.READONLY_FEATURE.ordinal()) {
            sReadonlyFeatureCache.disableLocal();
        } else if (featureType == 6) {
            sMemoryFeatureCache.disableLocal();
            sPersistFeatureCache.disableLocal();
            sReadonlyFeatureCache.disableLocal();
        }
    }

    public static void invalidateOplusFeatureCache(int featureType) {
        if (featureType == OplusFeature.OplusFeatureType.MEMORY_FEATURE.ordinal()) {
            sMemoryFeatureCache.invalidateCache();
        } else if (featureType == OplusFeature.OplusFeatureType.PERSIST_FEATURE.ordinal()) {
            sPersistFeatureCache.invalidateCache();
        } else if (featureType == OplusFeature.OplusFeatureType.READONLY_FEATURE.ordinal()) {
            sReadonlyFeatureCache.invalidateCache();
        } else if (featureType == 6) {
            sMemoryFeatureCache.invalidateCache();
            sPersistFeatureCache.invalidateCache();
            sReadonlyFeatureCache.invalidateCache();
        }
    }
}
