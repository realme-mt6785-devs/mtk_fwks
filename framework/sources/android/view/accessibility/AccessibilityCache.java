package android.view.accessibility;

import android.os.Build;
import android.util.ArraySet;
import android.util.Log;
import android.util.LongArray;
import android.util.LongSparseArray;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class AccessibilityCache {
    public static final int CACHE_CRITICAL_EVENTS_MASK = 4307005;
    private static final boolean CHECK_INTEGRITY;
    private static final boolean DEBUG;
    private static final String LOG_TAG = "AccessibilityCache";
    private static final boolean VERBOSE;
    private final AccessibilityNodeRefresher mAccessibilityNodeRefresher;
    private boolean mIsAllWindowsCached;
    private final Object mLock = new Object();
    private long mAccessibilityFocus = 2147483647L;
    private long mInputFocus = 2147483647L;
    private int mAccessibilityFocusedWindow = -1;
    private final SparseArray<SparseArray<AccessibilityWindowInfo>> mWindowCacheByDisplay = new SparseArray<>();
    private final SparseArray<LongSparseArray<AccessibilityNodeInfo>> mNodeCache = new SparseArray<>();
    private final SparseArray<AccessibilityWindowInfo> mTempWindowArray = new SparseArray<>();

    static {
        boolean z = true;
        DEBUG = Log.isLoggable(LOG_TAG, 3) && Build.IS_DEBUGGABLE;
        if (!Log.isLoggable(LOG_TAG, 2) || !Build.IS_DEBUGGABLE) {
            z = false;
        }
        VERBOSE = z;
        CHECK_INTEGRITY = Build.IS_ENG;
    }

    public AccessibilityCache(AccessibilityNodeRefresher nodeRefresher) {
        this.mAccessibilityNodeRefresher = nodeRefresher;
    }

    public void setWindowsOnAllDisplays(SparseArray<List<AccessibilityWindowInfo>> windowsOnAllDisplays) {
        synchronized (this.mLock) {
            if (DEBUG) {
                Log.i(LOG_TAG, "Set windows");
            }
            clearWindowCacheLocked();
            if (windowsOnAllDisplays != null) {
                int displayCounts = windowsOnAllDisplays.size();
                for (int i = 0; i < displayCounts; i++) {
                    List<AccessibilityWindowInfo> windowsOfDisplay = windowsOnAllDisplays.valueAt(i);
                    if (windowsOfDisplay != null) {
                        int displayId = windowsOnAllDisplays.keyAt(i);
                        int windowCount = windowsOfDisplay.size();
                        for (int j = 0; j < windowCount; j++) {
                            addWindowByDisplayLocked(displayId, windowsOfDisplay.get(j));
                        }
                    }
                }
                this.mIsAllWindowsCached = true;
            }
        }
    }

    public void addWindow(AccessibilityWindowInfo window) {
        synchronized (this.mLock) {
            if (DEBUG) {
                Log.i(LOG_TAG, "Caching window: " + window.getId() + " at display Id [ " + window.getDisplayId() + " ]");
            }
            addWindowByDisplayLocked(window.getDisplayId(), window);
        }
    }

    private void addWindowByDisplayLocked(int displayId, AccessibilityWindowInfo window) {
        SparseArray<AccessibilityWindowInfo> windows = this.mWindowCacheByDisplay.get(displayId);
        if (windows == null) {
            windows = new SparseArray<>();
            this.mWindowCacheByDisplay.put(displayId, windows);
        }
        int windowId = window.getId();
        windows.put(windowId, new AccessibilityWindowInfo(window));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void onAccessibilityEvent(AccessibilityEvent event) {
        boolean z;
        AccessibilityNodeInfo nodeToRefresh = null;
        synchronized (this.mLock) {
            z = DEBUG;
            if (z) {
                Log.i(LOG_TAG, "onAccessibilityEvent(" + event + ")");
            }
            int eventType = event.getEventType();
            switch (eventType) {
                case 1:
                case 4:
                case 16:
                case 8192:
                    nodeToRefresh = removeCachedNodeLocked(event.getWindowId(), event.getSourceNodeId());
                    break;
                case 8:
                    if (this.mInputFocus != 2147483647L) {
                        removeCachedNodeLocked(event.getWindowId(), this.mInputFocus);
                    }
                    this.mInputFocus = event.getSourceNodeId();
                    nodeToRefresh = removeCachedNodeLocked(event.getWindowId(), this.mInputFocus);
                    break;
                case 32:
                    clear();
                    break;
                case 2048:
                    synchronized (this.mLock) {
                        int windowId = event.getWindowId();
                        long sourceId = event.getSourceNodeId();
                        if ((event.getContentChangeTypes() & 1) != 0) {
                            clearSubTreeLocked(windowId, sourceId);
                        } else {
                            nodeToRefresh = removeCachedNodeLocked(windowId, sourceId);
                        }
                    }
                    break;
                case 4096:
                    clearSubTreeLocked(event.getWindowId(), event.getSourceNodeId());
                    break;
                case 32768:
                    long j = this.mAccessibilityFocus;
                    if (j != 2147483647L) {
                        removeCachedNodeLocked(this.mAccessibilityFocusedWindow, j);
                    }
                    this.mAccessibilityFocus = event.getSourceNodeId();
                    int windowId2 = event.getWindowId();
                    this.mAccessibilityFocusedWindow = windowId2;
                    nodeToRefresh = removeCachedNodeLocked(windowId2, this.mAccessibilityFocus);
                    break;
                case 65536:
                    if (this.mAccessibilityFocus == event.getSourceNodeId() && this.mAccessibilityFocusedWindow == event.getWindowId()) {
                        nodeToRefresh = removeCachedNodeLocked(this.mAccessibilityFocusedWindow, this.mAccessibilityFocus);
                        this.mAccessibilityFocus = 2147483647L;
                        this.mAccessibilityFocusedWindow = -1;
                        break;
                    }
                    break;
                case 4194304:
                    if (event.getWindowChanges() == 128) {
                        clearWindowCacheLocked();
                        break;
                    }
                    clear();
                    break;
            }
        }
        if (nodeToRefresh != null) {
            if (z) {
                Log.i(LOG_TAG, "Refreshing and re-adding cached node.");
            }
            if (this.mAccessibilityNodeRefresher.refreshNode(nodeToRefresh, true)) {
                add(nodeToRefresh);
            }
        }
        if (CHECK_INTEGRITY) {
            checkIntegrity();
        }
    }

    private AccessibilityNodeInfo removeCachedNodeLocked(int windowId, long sourceId) {
        AccessibilityNodeInfo cachedInfo;
        if (DEBUG) {
            Log.i(LOG_TAG, "Removing cached node.");
        }
        LongSparseArray<AccessibilityNodeInfo> nodes = this.mNodeCache.get(windowId);
        if (nodes == null || (cachedInfo = nodes.get(sourceId)) == null) {
            return null;
        }
        nodes.remove(sourceId);
        return cachedInfo;
    }

    public AccessibilityNodeInfo getNode(int windowId, long accessibilityNodeId) {
        synchronized (this.mLock) {
            LongSparseArray<AccessibilityNodeInfo> nodes = this.mNodeCache.get(windowId);
            if (nodes == null) {
                return null;
            }
            AccessibilityNodeInfo info = nodes.get(accessibilityNodeId);
            if (info != null) {
                info = new AccessibilityNodeInfo(info);
            }
            if (VERBOSE) {
                Log.i(LOG_TAG, "get(0x" + Long.toHexString(accessibilityNodeId) + ") = " + info);
            }
            return info;
        }
    }

    public SparseArray<List<AccessibilityWindowInfo>> getWindowsOnAllDisplays() {
        int windowCount;
        synchronized (this.mLock) {
            if (!this.mIsAllWindowsCached) {
                return null;
            }
            SparseArray<List<AccessibilityWindowInfo>> returnWindows = new SparseArray<>();
            int displayCounts = this.mWindowCacheByDisplay.size();
            if (displayCounts <= 0) {
                return null;
            }
            for (int i = 0; i < displayCounts; i++) {
                int displayId = this.mWindowCacheByDisplay.keyAt(i);
                SparseArray<AccessibilityWindowInfo> windowsOfDisplay = this.mWindowCacheByDisplay.valueAt(i);
                if (windowsOfDisplay != null && (windowCount = windowsOfDisplay.size()) > 0) {
                    SparseArray<AccessibilityWindowInfo> sortedWindows = this.mTempWindowArray;
                    sortedWindows.clear();
                    for (int j = 0; j < windowCount; j++) {
                        AccessibilityWindowInfo window = windowsOfDisplay.valueAt(j);
                        sortedWindows.put(window.getLayer(), window);
                    }
                    int sortedWindowCount = sortedWindows.size();
                    List<AccessibilityWindowInfo> windows = new ArrayList<>(sortedWindowCount);
                    for (int j2 = sortedWindowCount - 1; j2 >= 0; j2--) {
                        windows.add(new AccessibilityWindowInfo(sortedWindows.valueAt(j2)));
                        sortedWindows.removeAt(j2);
                    }
                    returnWindows.put(displayId, windows);
                }
            }
            return returnWindows;
        }
    }

    public AccessibilityWindowInfo getWindow(int windowId) {
        AccessibilityWindowInfo window;
        synchronized (this.mLock) {
            int displayCounts = this.mWindowCacheByDisplay.size();
            for (int i = 0; i < displayCounts; i++) {
                SparseArray<AccessibilityWindowInfo> windowsOfDisplay = this.mWindowCacheByDisplay.valueAt(i);
                if (!(windowsOfDisplay == null || (window = windowsOfDisplay.get(windowId)) == null)) {
                    return new AccessibilityWindowInfo(window);
                }
            }
            return null;
        }
    }

    public void add(AccessibilityNodeInfo info) {
        synchronized (this.mLock) {
            if (VERBOSE) {
                Log.i(LOG_TAG, "add(" + info + ")");
            }
            int windowId = info.getWindowId();
            LongSparseArray<AccessibilityNodeInfo> nodes = this.mNodeCache.get(windowId);
            if (nodes == null) {
                nodes = new LongSparseArray<>();
                this.mNodeCache.put(windowId, nodes);
            }
            long sourceId = info.getSourceNodeId();
            AccessibilityNodeInfo oldInfo = nodes.get(sourceId);
            if (oldInfo != null) {
                LongArray newChildrenIds = info.getChildNodeIds();
                int oldChildCount = oldInfo.getChildCount();
                for (int i = 0; i < oldChildCount; i++) {
                    long oldChildId = oldInfo.getChildId(i);
                    if (newChildrenIds == null || newChildrenIds.indexOf(oldChildId) < 0) {
                        clearSubTreeLocked(windowId, oldChildId);
                    }
                    if (nodes.get(sourceId) == null) {
                        clearNodesForWindowLocked(windowId);
                        return;
                    }
                }
                long oldParentId = oldInfo.getParentNodeId();
                if (info.getParentNodeId() != oldParentId) {
                    clearSubTreeLocked(windowId, oldParentId);
                }
            }
            AccessibilityNodeInfo clone = new AccessibilityNodeInfo(info);
            nodes.put(sourceId, clone);
            if (clone.isAccessibilityFocused()) {
                long j = this.mAccessibilityFocus;
                if (!(j == 2147483647L || j == sourceId)) {
                    removeCachedNodeLocked(windowId, j);
                }
                this.mAccessibilityFocus = sourceId;
                this.mAccessibilityFocusedWindow = windowId;
            } else if (this.mAccessibilityFocus == sourceId) {
                this.mAccessibilityFocus = 2147483647L;
                this.mAccessibilityFocusedWindow = -1;
            }
            if (clone.isFocused()) {
                this.mInputFocus = sourceId;
            }
        }
    }

    public void clear() {
        synchronized (this.mLock) {
            if (DEBUG) {
                Log.i(LOG_TAG, "clear()");
            }
            clearWindowCacheLocked();
            int nodesForWindowCount = this.mNodeCache.size();
            for (int i = nodesForWindowCount - 1; i >= 0; i--) {
                int windowId = this.mNodeCache.keyAt(i);
                clearNodesForWindowLocked(windowId);
            }
            this.mAccessibilityFocus = 2147483647L;
            this.mInputFocus = 2147483647L;
            this.mAccessibilityFocusedWindow = -1;
        }
    }

    private void clearWindowCacheLocked() {
        if (DEBUG) {
            Log.i(LOG_TAG, "clearWindowCacheLocked");
        }
        int displayCounts = this.mWindowCacheByDisplay.size();
        if (displayCounts > 0) {
            for (int i = displayCounts - 1; i >= 0; i--) {
                int displayId = this.mWindowCacheByDisplay.keyAt(i);
                SparseArray<AccessibilityWindowInfo> windows = this.mWindowCacheByDisplay.get(displayId);
                if (windows != null) {
                    windows.clear();
                }
                this.mWindowCacheByDisplay.remove(displayId);
            }
        }
        this.mIsAllWindowsCached = false;
    }

    private void clearNodesForWindowLocked(int windowId) {
        if (DEBUG) {
            Log.i(LOG_TAG, "clearNodesForWindowLocked(" + windowId + ")");
        }
        LongSparseArray<AccessibilityNodeInfo> nodes = this.mNodeCache.get(windowId);
        if (nodes != null) {
            this.mNodeCache.remove(windowId);
        }
    }

    private void clearSubTreeLocked(int windowId, long rootNodeId) {
        if (DEBUG) {
            Log.i(LOG_TAG, "Clearing cached subtree.");
        }
        LongSparseArray<AccessibilityNodeInfo> nodes = this.mNodeCache.get(windowId);
        if (nodes != null) {
            clearSubTreeRecursiveLocked(nodes, rootNodeId);
        }
    }

    private boolean clearSubTreeRecursiveLocked(LongSparseArray<AccessibilityNodeInfo> nodes, long rootNodeId) {
        AccessibilityNodeInfo current = nodes.get(rootNodeId);
        if (current == null) {
            clear();
            return true;
        }
        nodes.remove(rootNodeId);
        int childCount = current.getChildCount();
        for (int i = 0; i < childCount; i++) {
            long childNodeId = current.getChildId(i);
            if (clearSubTreeRecursiveLocked(nodes, childNodeId)) {
                return true;
            }
        }
        return false;
    }

    public void checkIntegrity() {
        AccessibilityWindowInfo activeWindow;
        int displayCounts;
        AccessibilityWindowInfo focusedWindow;
        AccessibilityWindowInfo activeWindow2;
        int displayCounts2;
        AccessibilityWindowInfo focusedWindow2;
        int childCount;
        AccessibilityNodeInfo inputFocus;
        int k;
        AccessibilityCache accessibilityCache = this;
        synchronized (accessibilityCache.mLock) {
            if (accessibilityCache.mWindowCacheByDisplay.size() > 0 || accessibilityCache.mNodeCache.size() != 0) {
                AccessibilityWindowInfo focusedWindow3 = null;
                AccessibilityWindowInfo activeWindow3 = null;
                int displayCounts3 = accessibilityCache.mWindowCacheByDisplay.size();
                for (int i = 0; i < displayCounts3; i++) {
                    SparseArray<AccessibilityWindowInfo> windowsOfDisplay = accessibilityCache.mWindowCacheByDisplay.valueAt(i);
                    if (windowsOfDisplay != null) {
                        int windowCount = windowsOfDisplay.size();
                        for (int j = 0; j < windowCount; j++) {
                            AccessibilityWindowInfo window = windowsOfDisplay.valueAt(j);
                            if (window.isActive()) {
                                if (activeWindow3 != null) {
                                    Log.e(LOG_TAG, "Duplicate active window:" + window);
                                } else {
                                    activeWindow3 = window;
                                }
                            }
                            if (window.isFocused()) {
                                if (focusedWindow3 != null) {
                                    Log.e(LOG_TAG, "Duplicate focused window:" + window);
                                } else {
                                    focusedWindow3 = window;
                                }
                            }
                        }
                    }
                }
                AccessibilityNodeInfo accessFocus = null;
                AccessibilityNodeInfo inputFocus2 = null;
                int nodesForWindowCount = accessibilityCache.mNodeCache.size();
                int i2 = 0;
                while (i2 < nodesForWindowCount) {
                    LongSparseArray<AccessibilityNodeInfo> nodes = accessibilityCache.mNodeCache.valueAt(i2);
                    if (nodes.size() <= 0) {
                        focusedWindow = focusedWindow3;
                        activeWindow = activeWindow3;
                        displayCounts = displayCounts3;
                    } else {
                        ArraySet<AccessibilityNodeInfo> seen = new ArraySet<>();
                        int windowId = accessibilityCache.mNodeCache.keyAt(i2);
                        int nodeCount = nodes.size();
                        int j2 = 0;
                        while (j2 < nodeCount) {
                            AccessibilityNodeInfo node = nodes.valueAt(j2);
                            if (!seen.add(node)) {
                                focusedWindow2 = focusedWindow3;
                                Log.e(LOG_TAG, "Duplicate node: " + node + " in window:" + windowId);
                                activeWindow2 = activeWindow3;
                                displayCounts2 = displayCounts3;
                            } else {
                                focusedWindow2 = focusedWindow3;
                                if (node.isAccessibilityFocused()) {
                                    if (accessFocus != null) {
                                        Log.e(LOG_TAG, "Duplicate accessibility focus:" + node + " in window:" + windowId);
                                    } else {
                                        accessFocus = node;
                                    }
                                }
                                if (node.isFocused()) {
                                    if (inputFocus2 != null) {
                                        Log.e(LOG_TAG, "Duplicate input focus: " + node + " in window:" + windowId);
                                    } else {
                                        inputFocus2 = node;
                                    }
                                }
                                AccessibilityNodeInfo nodeParent = nodes.get(node.getParentNodeId());
                                if (nodeParent != null) {
                                    int childCount2 = nodeParent.getChildCount();
                                    int k2 = 0;
                                    while (true) {
                                        if (k2 >= childCount2) {
                                            activeWindow2 = activeWindow3;
                                            displayCounts2 = displayCounts3;
                                            k = 0;
                                            break;
                                        }
                                        activeWindow2 = activeWindow3;
                                        displayCounts2 = displayCounts3;
                                        if (nodes.get(nodeParent.getChildId(k2)) == node) {
                                            k = 1;
                                            break;
                                        }
                                        k2++;
                                        displayCounts3 = displayCounts2;
                                        activeWindow3 = activeWindow2;
                                    }
                                    if (k == 0) {
                                        Log.e(LOG_TAG, "Invalid parent-child relation between parent: " + nodeParent + " and child: " + node);
                                    }
                                } else {
                                    activeWindow2 = activeWindow3;
                                    displayCounts2 = displayCounts3;
                                }
                                int childCount3 = node.getChildCount();
                                int k3 = 0;
                                while (k3 < childCount3) {
                                    AccessibilityNodeInfo child = nodes.get(node.getChildId(k3));
                                    if (child != null) {
                                        inputFocus = inputFocus2;
                                        AccessibilityNodeInfo parent = nodes.get(child.getParentNodeId());
                                        if (parent != node) {
                                            childCount = childCount3;
                                            Log.e(LOG_TAG, "Invalid child-parent relation between child: " + node + " and parent: " + nodeParent);
                                        } else {
                                            childCount = childCount3;
                                        }
                                    } else {
                                        childCount = childCount3;
                                        inputFocus = inputFocus2;
                                    }
                                    k3++;
                                    accessFocus = accessFocus;
                                    inputFocus2 = inputFocus;
                                    childCount3 = childCount;
                                }
                            }
                            j2++;
                            focusedWindow3 = focusedWindow2;
                            displayCounts3 = displayCounts2;
                            activeWindow3 = activeWindow2;
                        }
                        focusedWindow = focusedWindow3;
                        activeWindow = activeWindow3;
                        displayCounts = displayCounts3;
                    }
                    i2++;
                    accessibilityCache = this;
                    focusedWindow3 = focusedWindow;
                    displayCounts3 = displayCounts;
                    activeWindow3 = activeWindow;
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class AccessibilityNodeRefresher {
        public boolean refreshNode(AccessibilityNodeInfo info, boolean bypassCache) {
            return info.refresh(null, bypassCache);
        }

        public boolean refreshWindow(AccessibilityWindowInfo info) {
            return info.refresh();
        }
    }
}
