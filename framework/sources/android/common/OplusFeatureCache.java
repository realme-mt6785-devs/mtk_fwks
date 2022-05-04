package android.common;

import android.common.OplusFeatureList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class OplusFeatureCache {
    private static final String TAG = "OplusFeatureCache";
    private static Object[] mFeatureCache = new Object[OplusFeatureList.OplusIndex.End.ordinal()];
    private static List<IOplusCommonFactory> sFactoryCache;

    static {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        sFactoryCache = copyOnWriteArrayList;
        copyOnWriteArrayList.add(OplusFrameworkFactory.getInstance());
    }

    public static <T extends IOplusCommonFeature> T get(T def) {
        int index = getIndex(def);
        T object = (T) ((IOplusCommonFeature) mFeatureCache[def.index().ordinal()]);
        if (object == null) {
            synchronized (def.getDefault()) {
                object = (T) ((IOplusCommonFeature) mFeatureCache[index]);
            }
        }
        return object != null ? object : def;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.common.IOplusCommonFeature] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [android.common.IOplusCommonFeature, T extends android.common.IOplusCommonFeature] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T extends android.common.IOplusCommonFeature> T getOrCreate(T r6, java.lang.Object... r7) {
        /*
            int r0 = getIndex(r6)
            java.lang.Object[] r1 = android.common.OplusFeatureCache.mFeatureCache
            android.common.OplusFeatureList$OplusIndex r2 = r6.index()
            int r2 = r2.ordinal()
            r1 = r1[r2]
            android.common.IOplusCommonFeature r1 = (android.common.IOplusCommonFeature) r1
            if (r1 != 0) goto L_0x004c
            android.common.IOplusCommonFeature r2 = r6.getDefault()
            monitor-enter(r2)
            java.lang.Object[] r3 = android.common.OplusFeatureCache.mFeatureCache     // Catch: all -> 0x0049
            r3 = r3[r0]     // Catch: all -> 0x0049
            android.common.IOplusCommonFeature r3 = (android.common.IOplusCommonFeature) r3     // Catch: all -> 0x0049
            r1 = r3
            if (r1 != 0) goto L_0x0047
            java.util.List<android.common.IOplusCommonFactory> r3 = android.common.OplusFeatureCache.sFactoryCache     // Catch: all -> 0x0049
            java.util.Iterator r3 = r3.iterator()     // Catch: all -> 0x0049
        L_0x0028:
            boolean r4 = r3.hasNext()     // Catch: all -> 0x0049
            if (r4 == 0) goto L_0x0047
            java.lang.Object r4 = r3.next()     // Catch: all -> 0x0049
            android.common.IOplusCommonFactory r4 = (android.common.IOplusCommonFactory) r4     // Catch: all -> 0x0049
            boolean r5 = r4.isValid(r0)     // Catch: all -> 0x0049
            if (r5 == 0) goto L_0x0046
            android.common.IOplusCommonFeature r5 = r4.getFeature(r6, r7)     // Catch: all -> 0x0049
            r1 = r5
            if (r1 == 0) goto L_0x0046
            set(r1)     // Catch: all -> 0x0049
            monitor-exit(r2)     // Catch: all -> 0x0049
            return r1
        L_0x0046:
            goto L_0x0028
        L_0x0047:
            monitor-exit(r2)     // Catch: all -> 0x0049
            goto L_0x004c
        L_0x0049:
            r3 = move-exception
            monitor-exit(r2)     // Catch: all -> 0x0049
            throw r3
        L_0x004c:
            if (r1 == 0) goto L_0x0050
            r2 = r1
            goto L_0x0051
        L_0x0050:
            r2 = r6
        L_0x0051:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.common.OplusFeatureCache.getOrCreate(android.common.IOplusCommonFeature, java.lang.Object[]):android.common.IOplusCommonFeature");
    }

    public static <T extends IOplusCommonFeature> void set(T impl) {
        int index = getIndex(impl);
        synchronized (impl.getDefault()) {
            mFeatureCache[index] = impl;
        }
    }

    public static <T extends IOplusCommonFactory> void addFactory(T factory) {
        if (factory != null) {
            sFactoryCache.add(factory);
        }
    }

    private static <T extends IOplusCommonFeature> int getIndex(T service) {
        if (service != null) {
            int index = service.index().ordinal();
            if (index < mFeatureCache.length) {
                return index;
            }
            throw new IllegalAccessError("index = " + index + " size = " + mFeatureCache.length);
        }
        throw new IllegalArgumentException("dummy must not be null");
    }
}
