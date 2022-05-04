package android.util;

import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int maxSize) {
        if (maxSize > 0) {
            this.maxSize = maxSize;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public void resize(int maxSize) {
        if (maxSize > 0) {
            synchronized (this) {
                this.maxSize = maxSize;
            }
            trimToSize(maxSize);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final V get(K key) {
        V mapValue;
        if (key != null) {
            synchronized (this) {
                V mapValue2 = this.map.get(key);
                if (mapValue2 != null) {
                    this.hitCount++;
                    return mapValue2;
                }
                this.missCount++;
                V createdValue = create(key);
                if (createdValue == null) {
                    return null;
                }
                synchronized (this) {
                    this.createCount++;
                    mapValue = this.map.put(key, createdValue);
                    if (mapValue != null) {
                        this.map.put(key, mapValue);
                    } else {
                        this.size += safeSizeOf(key, createdValue);
                    }
                }
                if (mapValue != null) {
                    entryRemoved(false, key, createdValue, mapValue);
                    return mapValue;
                }
                trimToSize(this.maxSize);
                return createdValue;
            }
        }
        throw new NullPointerException("key == null");
    }

    public final V put(K key, V value) {
        V previous;
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(key, value);
            previous = this.map.put(key, value);
            if (previous != null) {
                this.size -= safeSizeOf(key, previous);
            }
        }
        if (previous != null) {
            entryRemoved(false, key, previous, value);
        }
        trimToSize(this.maxSize);
        return previous;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0061, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void trimToSize(int r6) {
        /*
            r5 = this;
        L_0x0000:
            monitor-enter(r5)
            int r0 = r5.size     // Catch: all -> 0x0062
            if (r0 < 0) goto L_0x0043
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: all -> 0x0062
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0062
            if (r0 == 0) goto L_0x0011
            int r0 = r5.size     // Catch: all -> 0x0062
            if (r0 != 0) goto L_0x0043
        L_0x0011:
            int r0 = r5.size     // Catch: all -> 0x0062
            if (r0 > r6) goto L_0x0017
            monitor-exit(r5)     // Catch: all -> 0x0062
            goto L_0x0020
        L_0x0017:
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: all -> 0x0062
            java.util.Map$Entry r0 = r0.eldest()     // Catch: all -> 0x0062
            if (r0 != 0) goto L_0x0021
            monitor-exit(r5)     // Catch: all -> 0x0062
        L_0x0020:
            return
        L_0x0021:
            java.lang.Object r1 = r0.getKey()     // Catch: all -> 0x0062
            java.lang.Object r2 = r0.getValue()     // Catch: all -> 0x0062
            java.util.LinkedHashMap<K, V> r3 = r5.map     // Catch: all -> 0x0062
            r3.remove(r1)     // Catch: all -> 0x0062
            int r3 = r5.size     // Catch: all -> 0x0062
            int r4 = r5.safeSizeOf(r1, r2)     // Catch: all -> 0x0062
            int r3 = r3 - r4
            r5.size = r3     // Catch: all -> 0x0062
            int r3 = r5.evictionCount     // Catch: all -> 0x0062
            r4 = 1
            int r3 = r3 + r4
            r5.evictionCount = r3     // Catch: all -> 0x0062
            monitor-exit(r5)     // Catch: all -> 0x0062
            r0 = 0
            r5.entryRemoved(r4, r1, r2, r0)
            goto L_0x0000
        L_0x0043:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: all -> 0x0062
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: all -> 0x0062
            r1.<init>()     // Catch: all -> 0x0062
            java.lang.Class r2 = r5.getClass()     // Catch: all -> 0x0062
            java.lang.String r2 = r2.getName()     // Catch: all -> 0x0062
            r1.append(r2)     // Catch: all -> 0x0062
            java.lang.String r2 = ".sizeOf() is reporting inconsistent results!"
            r1.append(r2)     // Catch: all -> 0x0062
            java.lang.String r1 = r1.toString()     // Catch: all -> 0x0062
            r0.<init>(r1)     // Catch: all -> 0x0062
            throw r0     // Catch: all -> 0x0062
        L_0x0062:
            r0 = move-exception
            monitor-exit(r5)     // Catch: all -> 0x0062
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.LruCache.trimToSize(int):void");
    }

    public final V remove(K key) {
        V previous;
        if (key != null) {
            synchronized (this) {
                previous = this.map.remove(key);
                if (previous != null) {
                    this.size -= safeSizeOf(key, previous);
                }
            }
            if (previous != null) {
                entryRemoved(false, key, previous, null);
            }
            return previous;
        }
        throw new NullPointerException("key == null");
    }

    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
    }

    protected V create(K key) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Negative size: " + key + "=" + value);
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int hitPercent;
        int i = this.hitCount;
        int accesses = this.missCount + i;
        hitPercent = accesses != 0 ? (i * 100) / accesses : 0;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(hitPercent));
    }
}
