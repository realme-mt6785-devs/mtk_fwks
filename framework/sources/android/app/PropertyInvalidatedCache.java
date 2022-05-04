package android.app;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import com.android.internal.util.FastPrintWriter;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public abstract class PropertyInvalidatedCache<Query, Result> {
    private static final boolean DEBUG = false;
    private static final boolean DETAILED = false;
    private static final int NONCE_CORKED = 2;
    private static final int NONCE_DISABLED = 1;
    private static final int NONCE_RESERVED = 3;
    private static final int NONCE_UNSET = 0;
    private static final String TAG = "PropertyInvalidatedCache";
    private static final boolean VERIFY = false;
    private final LinkedHashMap<Query, Result> mCache;
    private final String mCacheName;
    private long mClears;
    private boolean mDisabled;
    private long mHighWaterMark;
    private long mHits;
    private long mLastSeenNonce;
    private final Object mLock;
    private final int mMaxEntries;
    private long mMissOverflow;
    private long mMisses;
    private volatile SystemProperties.Handle mPropertyHandle;
    private final String mPropertyName;
    private long[] mSkips;
    private static final String[] sNonceName = {"unset", "disabled", "corked"};
    private static final HashMap<String, Long> sInvalidates = new HashMap<>();
    private static final HashMap<String, Long> sCorkedInvalidates = new HashMap<>();
    private static boolean sEnabled = true;
    private static final Object sCorkLock = new Object();
    private static final HashMap<String, Integer> sCorks = new HashMap<>();
    private static final HashSet<String> sDisabledKeys = new HashSet<>();
    private static final WeakHashMap<PropertyInvalidatedCache, Void> sCaches = new WeakHashMap<>();

    protected abstract Result recompute(Query query);

    static /* synthetic */ long access$108(PropertyInvalidatedCache x0) {
        long j = x0.mMissOverflow;
        x0.mMissOverflow = 1 + j;
        return j;
    }

    public PropertyInvalidatedCache(int maxEntries, String propertyName) {
        this(maxEntries, propertyName, propertyName);
    }

    public PropertyInvalidatedCache(final int maxEntries, String propertyName, String cacheName) {
        this.mHits = 0L;
        this.mMisses = 0L;
        this.mSkips = new long[]{0, 0, 0};
        this.mMissOverflow = 0L;
        this.mHighWaterMark = 0L;
        this.mClears = 0L;
        this.mLock = new Object();
        this.mLastSeenNonce = 0L;
        this.mDisabled = false;
        this.mPropertyName = propertyName;
        this.mCacheName = cacheName;
        this.mMaxEntries = maxEntries;
        this.mCache = new LinkedHashMap<Query, Result>(2, 0.75f, true) { // from class: android.app.PropertyInvalidatedCache.1
            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry eldest) {
                int size = size();
                if (size > PropertyInvalidatedCache.this.mHighWaterMark) {
                    PropertyInvalidatedCache.this.mHighWaterMark = size;
                }
                if (size <= maxEntries) {
                    return false;
                }
                PropertyInvalidatedCache.access$108(PropertyInvalidatedCache.this);
                return true;
            }
        };
        synchronized (sCorkLock) {
            sCaches.put(this, null);
            if (sDisabledKeys.contains(cacheName)) {
                disableInstance();
            }
        }
    }

    public final void clear() {
        synchronized (this.mLock) {
            this.mCache.clear();
            this.mClears++;
        }
    }

    protected boolean bypass(Query query) {
        return false;
    }

    protected boolean debugCompareQueryResults(Result cachedResult, Result fetchedResult) {
        if (fetchedResult != null) {
            return Objects.equals(cachedResult, fetchedResult);
        }
        return true;
    }

    protected Result refresh(Result oldResult, Query query) {
        return oldResult;
    }

    private long getCurrentNonce() {
        SystemProperties.Handle handle = this.mPropertyHandle;
        if (handle == null) {
            handle = SystemProperties.find(this.mPropertyName);
            if (handle == null) {
                return 0L;
            }
            this.mPropertyHandle = handle;
        }
        return handle.getLong(0L);
    }

    public final void disableInstance() {
        synchronized (this.mLock) {
            this.mDisabled = true;
            clear();
        }
    }

    public static final void disableLocal(String name) {
        synchronized (sCorkLock) {
            sDisabledKeys.add(name);
            for (PropertyInvalidatedCache cache : sCaches.keySet()) {
                if (name.equals(cache.mCacheName)) {
                    cache.disableInstance();
                }
            }
        }
    }

    public final void disableLocal() {
        disableLocal(this.mCacheName);
    }

    public final boolean isDisabledLocal() {
        return this.mDisabled || !sEnabled;
    }

    public Result query(Query query) {
        Result cachedResult;
        long currentNonce = !isDisabledLocal() ? getCurrentNonce() : 1L;
        while (currentNonce != 1 && currentNonce != 0 && currentNonce != 2 && !bypass(query)) {
            synchronized (this.mLock) {
                if (currentNonce == this.mLastSeenNonce) {
                    cachedResult = this.mCache.get(query);
                    if (cachedResult != null) {
                        this.mHits++;
                    }
                } else {
                    clear();
                    this.mLastSeenNonce = currentNonce;
                    cachedResult = null;
                }
            }
            if (cachedResult != null) {
                Result refreshedResult = refresh(cachedResult, query);
                if (refreshedResult == cachedResult) {
                    return maybeCheckConsistency(query, cachedResult);
                }
                long afterRefreshNonce = getCurrentNonce();
                if (currentNonce != afterRefreshNonce) {
                    currentNonce = afterRefreshNonce;
                } else {
                    synchronized (this.mLock) {
                        if (currentNonce == this.mLastSeenNonce) {
                            if (refreshedResult == null) {
                                this.mCache.remove(query);
                            } else {
                                this.mCache.put(query, refreshedResult);
                            }
                        }
                    }
                    return maybeCheckConsistency(query, refreshedResult);
                }
            } else {
                Result result = recompute(query);
                synchronized (this.mLock) {
                    if (this.mLastSeenNonce == currentNonce && result != null) {
                        this.mCache.put(query, result);
                    }
                    this.mMisses++;
                }
                return maybeCheckConsistency(query, result);
            }
        }
        if (!this.mDisabled) {
            synchronized (this.mLock) {
                long[] jArr = this.mSkips;
                int i = (int) currentNonce;
                jArr[i] = jArr[i] + 1;
            }
        }
        return recompute(query);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class NoPreloadHolder {
        private static final AtomicLong sNextNonce = new AtomicLong(new Random().nextLong());

        private NoPreloadHolder() {
        }

        public static long next() {
            return sNextNonce.getAndIncrement();
        }
    }

    public final void disableSystemWide() {
        disableSystemWide(this.mPropertyName);
    }

    public static void disableSystemWide(String name) {
        if (sEnabled) {
            SystemProperties.set(name, Long.toString(1L));
        }
    }

    public final void invalidateCache() {
        invalidateCache(this.mPropertyName);
    }

    public static void invalidateCache(String name) {
        if (sEnabled) {
            synchronized (sCorkLock) {
                Integer numberCorks = sCorks.get(name);
                if (numberCorks == null || numberCorks.intValue() <= 0) {
                    invalidateCacheLocked(name);
                    return;
                }
                HashMap<String, Long> hashMap = sCorkedInvalidates;
                long count = hashMap.getOrDefault(name, 0L).longValue();
                hashMap.put(name, Long.valueOf(1 + count));
            }
        }
    }

    private static void invalidateCacheLocked(String name) {
        long newValue;
        long nonce = SystemProperties.getLong(name, 0L);
        if (nonce != 1) {
            do {
                newValue = NoPreloadHolder.next();
                if (newValue < 0) {
                    break;
                }
            } while (newValue < 3);
            String newValueString = Long.toString(newValue);
            try {
                SystemProperties.set(name, newValueString);
            } catch (RuntimeException e) {
                Log.d(TAG, "set property fail, name:" + name);
            }
            HashMap<String, Long> hashMap = sInvalidates;
            long invalidateCount = hashMap.getOrDefault(name, 0L).longValue();
            hashMap.put(name, Long.valueOf(1 + invalidateCount));
        }
    }

    public static void corkInvalidations(String name) {
        if (sEnabled) {
            synchronized (sCorkLock) {
                int numberCorks = sCorks.getOrDefault(name, 0).intValue();
                if (numberCorks == 0) {
                    long nonce = SystemProperties.getLong(name, 0L);
                    if (!(nonce == 0 || nonce == 1)) {
                        try {
                            SystemProperties.set(name, Long.toString(2L));
                        } catch (RuntimeException e) {
                            Log.d(TAG, "set property fail, name:" + name);
                        }
                    }
                } else {
                    HashMap<String, Long> hashMap = sCorkedInvalidates;
                    long count = hashMap.getOrDefault(name, 0L).longValue();
                    hashMap.put(name, Long.valueOf(1 + count));
                }
                sCorks.put(name, Integer.valueOf(numberCorks + 1));
            }
        }
    }

    public static void uncorkInvalidations(String name) {
        if (sEnabled) {
            synchronized (sCorkLock) {
                HashMap<String, Integer> hashMap = sCorks;
                int numberCorks = hashMap.getOrDefault(name, 0).intValue();
                if (numberCorks < 1) {
                    throw new AssertionError("cork underflow: " + name);
                } else if (numberCorks == 1) {
                    hashMap.remove(name);
                    invalidateCacheLocked(name);
                } else {
                    hashMap.put(name, Integer.valueOf(numberCorks - 1));
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class AutoCorker {
        public static final int DEFAULT_AUTO_CORK_DELAY_MS = 50;
        private final int mAutoCorkDelayMs;
        private Handler mHandler;
        private final Object mLock;
        private final String mPropertyName;
        private long mUncorkDeadlineMs;

        public AutoCorker(String propertyName) {
            this(propertyName, 50);
        }

        public AutoCorker(String propertyName, int autoCorkDelayMs) {
            this.mLock = new Object();
            this.mUncorkDeadlineMs = -1L;
            this.mPropertyName = propertyName;
            this.mAutoCorkDelayMs = autoCorkDelayMs;
        }

        public void autoCork() {
            if (Looper.getMainLooper() == null) {
                PropertyInvalidatedCache.invalidateCache(this.mPropertyName);
                return;
            }
            synchronized (this.mLock) {
                boolean alreadyQueued = this.mUncorkDeadlineMs >= 0;
                this.mUncorkDeadlineMs = SystemClock.uptimeMillis() + this.mAutoCorkDelayMs;
                if (!alreadyQueued) {
                    PropertyInvalidatedCache.corkInvalidations(this.mPropertyName);
                    getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
                } else {
                    long count = ((Long) PropertyInvalidatedCache.sCorkedInvalidates.getOrDefault(this.mPropertyName, 0L)).longValue();
                    PropertyInvalidatedCache.sCorkedInvalidates.put(this.mPropertyName, Long.valueOf(1 + count));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleMessage(Message msg) {
            synchronized (this.mLock) {
                if (this.mUncorkDeadlineMs >= 0) {
                    long nowMs = SystemClock.uptimeMillis();
                    if (this.mUncorkDeadlineMs > nowMs) {
                        this.mUncorkDeadlineMs = this.mAutoCorkDelayMs + nowMs;
                        getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
                        return;
                    }
                    this.mUncorkDeadlineMs = -1L;
                    PropertyInvalidatedCache.uncorkInvalidations(this.mPropertyName);
                }
            }
        }

        private Handler getHandlerLocked() {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper()) { // from class: android.app.PropertyInvalidatedCache.AutoCorker.1
                    @Override // android.os.Handler
                    public void handleMessage(Message msg) {
                        AutoCorker.this.handleMessage(msg);
                    }
                };
            }
            return this.mHandler;
        }
    }

    protected Result maybeCheckConsistency(Query query, Result proposedResult) {
        return proposedResult;
    }

    public String cacheName() {
        return this.mCacheName;
    }

    public String queryToString(Query query) {
        return Objects.toString(query);
    }

    public static void disableForTestMode() {
        Log.d(TAG, "disabling all caches in the process");
        sEnabled = false;
    }

    public boolean getDisabledState() {
        return isDisabledLocal();
    }

    public static ArrayList<PropertyInvalidatedCache> getActiveCaches() {
        ArrayList<PropertyInvalidatedCache> arrayList;
        synchronized (sCorkLock) {
            arrayList = new ArrayList<>(sCaches.keySet());
        }
        return arrayList;
    }

    public static ArrayList<Map.Entry<String, Integer>> getActiveCorks() {
        ArrayList<Map.Entry<String, Integer>> arrayList;
        synchronized (sCorkLock) {
            arrayList = new ArrayList<>(sCorks.entrySet());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2, types: [long] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void dumpContents(java.io.PrintWriter r19, java.lang.String[] r20) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.PropertyInvalidatedCache.dumpContents(java.io.PrintWriter, java.lang.String[]):void");
    }

    public static void dumpCacheInfo(FileDescriptor fd, String[] args) {
        ArrayList<PropertyInvalidatedCache> activeCaches;
        try {
            FileOutputStream fout = new FileOutputStream(fd);
            PrintWriter pw = new FastPrintWriter(fout);
            if (!sEnabled) {
                pw.println("  Caching is disabled in this process.");
                pw.close();
                fout.close();
                return;
            }
            synchronized (sCorkLock) {
                activeCaches = getActiveCaches();
                ArrayList<Map.Entry<String, Integer>> activeCorks = getActiveCorks();
                if (activeCorks.size() > 0) {
                    pw.println("  Corking Status:");
                    for (int i = 0; i < activeCorks.size(); i++) {
                        Map.Entry<String, Integer> entry = activeCorks.get(i);
                        pw.println(String.format("    Property Name: %s Count: %d", entry.getKey(), entry.getValue()));
                    }
                }
            }
            for (int i2 = 0; i2 < activeCaches.size(); i2++) {
                PropertyInvalidatedCache currentCache = activeCaches.get(i2);
                currentCache.dumpContents(pw, args);
                pw.flush();
            }
            pw.close();
            fout.close();
        } catch (IOException e) {
            Log.e(TAG, "Failed to dump PropertyInvalidatedCache instances");
        }
    }
}
