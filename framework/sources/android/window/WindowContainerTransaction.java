package android.window;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.view.SurfaceControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public final class WindowContainerTransaction implements Parcelable {
    public static final Parcelable.Creator<WindowContainerTransaction> CREATOR = new Parcelable.Creator<WindowContainerTransaction>() { // from class: android.window.WindowContainerTransaction.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowContainerTransaction createFromParcel(Parcel in) {
            return new WindowContainerTransaction(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowContainerTransaction[] newArray(int size) {
            return new WindowContainerTransaction[size];
        }
    };
    private final ArrayMap<IBinder, Change> mChanges;
    private final ArrayList<HierarchyOp> mHierarchyOps;

    public WindowContainerTransaction() {
        this.mChanges = new ArrayMap<>();
        this.mHierarchyOps = new ArrayList<>();
    }

    private WindowContainerTransaction(Parcel in) {
        ArrayMap<IBinder, Change> arrayMap = new ArrayMap<>();
        this.mChanges = arrayMap;
        ArrayList<HierarchyOp> arrayList = new ArrayList<>();
        this.mHierarchyOps = arrayList;
        in.readMap(arrayMap, null);
        in.readList(arrayList, null);
    }

    private Change getOrCreateChange(IBinder token) {
        Change out = this.mChanges.get(token);
        if (out != null) {
            return out;
        }
        Change out2 = new Change();
        this.mChanges.put(token, out2);
        return out2;
    }

    public WindowContainerTransaction setBounds(WindowContainerToken container, Rect bounds) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mConfiguration.windowConfiguration.setBounds(bounds);
        Change.access$176(chg, 536870912);
        Change.access$276(chg, 1);
        return this;
    }

    public WindowContainerTransaction setAppBounds(WindowContainerToken container, Rect appBounds) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mConfiguration.windowConfiguration.setAppBounds(appBounds);
        Change.access$176(chg, 536870912);
        Change.access$276(chg, 2);
        return this;
    }

    public WindowContainerTransaction setScreenSizeDp(WindowContainerToken container, int w, int h) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mConfiguration.screenWidthDp = w;
        chg.mConfiguration.screenHeightDp = h;
        Change.access$176(chg, 1024);
        return this;
    }

    public WindowContainerTransaction scheduleFinishEnterPip(WindowContainerToken container, Rect bounds) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mPinnedBounds = new Rect(bounds);
        Change.access$476(chg, 4);
        return this;
    }

    public WindowContainerTransaction setBoundsChangeTransaction(WindowContainerToken container, SurfaceControl.Transaction t) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mBoundsChangeTransaction = t;
        Change.access$476(chg, 2);
        return this;
    }

    public WindowContainerTransaction setBoundsChangeTransaction(WindowContainerToken task, Rect surfaceBounds) {
        Change chg = getOrCreateChange(task.asBinder());
        if (chg.mBoundsChangeSurfaceBounds == null) {
            chg.mBoundsChangeSurfaceBounds = new Rect();
        }
        chg.mBoundsChangeSurfaceBounds.set(surfaceBounds);
        Change.access$476(chg, 16);
        return this;
    }

    public WindowContainerTransaction setActivityWindowingMode(WindowContainerToken container, int windowingMode) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mActivityWindowingMode = windowingMode;
        return this;
    }

    public WindowContainerTransaction setWindowingMode(WindowContainerToken container, int windowingMode) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mWindowingMode = windowingMode;
        return this;
    }

    public WindowContainerTransaction setFocusable(WindowContainerToken container, boolean focusable) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mFocusable = focusable;
        Change.access$476(chg, 1);
        return this;
    }

    public WindowContainerTransaction setHidden(WindowContainerToken container, boolean hidden) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mHidden = hidden;
        Change.access$476(chg, 8);
        return this;
    }

    public WindowContainerTransaction setSmallestScreenWidthDp(WindowContainerToken container, int widthDp) {
        Change cfg = getOrCreateChange(container.asBinder());
        cfg.mConfiguration.smallestScreenWidthDp = widthDp;
        Change.access$176(cfg, 2048);
        return this;
    }

    public WindowContainerTransaction setIgnoreOrientationRequest(WindowContainerToken container, boolean ignoreOrientationRequest) {
        Change chg = getOrCreateChange(container.asBinder());
        chg.mIgnoreOrientationRequest = ignoreOrientationRequest;
        Change.access$476(chg, 32);
        return this;
    }

    public WindowContainerTransaction reparent(WindowContainerToken child, WindowContainerToken parent, boolean onTop) {
        this.mHierarchyOps.add(HierarchyOp.createForReparent(child.asBinder(), parent == null ? null : parent.asBinder(), onTop));
        return this;
    }

    public WindowContainerTransaction reorder(WindowContainerToken child, boolean onTop) {
        this.mHierarchyOps.add(HierarchyOp.createForReorder(child.asBinder(), onTop));
        return this;
    }

    public WindowContainerTransaction reparentTasks(WindowContainerToken currentParent, WindowContainerToken newParent, int[] windowingModes, int[] activityTypes, boolean onTop) {
        ArrayList<HierarchyOp> arrayList = this.mHierarchyOps;
        IBinder iBinder = null;
        IBinder asBinder = currentParent != null ? currentParent.asBinder() : null;
        if (newParent != null) {
            iBinder = newParent.asBinder();
        }
        arrayList.add(HierarchyOp.createForChildrenTasksReparent(asBinder, iBinder, windowingModes, activityTypes, onTop));
        return this;
    }

    public WindowContainerTransaction setLaunchRoot(WindowContainerToken container, int[] windowingModes, int[] activityTypes) {
        this.mHierarchyOps.add(HierarchyOp.createForSetLaunchRoot(container.asBinder(), windowingModes, activityTypes));
        return this;
    }

    public WindowContainerTransaction setAdjacentRoots(WindowContainerToken root1, WindowContainerToken root2) {
        this.mHierarchyOps.add(HierarchyOp.createForAdjacentRoots(root1.asBinder(), root2.asBinder()));
        return this;
    }

    public WindowContainerTransaction setLaunchAdjacentFlagRoot(WindowContainerToken container) {
        this.mHierarchyOps.add(HierarchyOp.createForSetLaunchAdjacentFlagRoot(container.asBinder(), false));
        return this;
    }

    public WindowContainerTransaction clearLaunchAdjacentFlagRoot(WindowContainerToken container) {
        this.mHierarchyOps.add(HierarchyOp.createForSetLaunchAdjacentFlagRoot(container.asBinder(), true));
        return this;
    }

    public WindowContainerTransaction startTask(int taskId, Bundle options) {
        this.mHierarchyOps.add(HierarchyOp.createForTaskLaunch(taskId, options));
        return this;
    }

    public void merge(WindowContainerTransaction other, boolean transfer) {
        int n = other.mChanges.size();
        for (int i = 0; i < n; i++) {
            IBinder key = other.mChanges.keyAt(i);
            Change existing = this.mChanges.get(key);
            if (existing == null) {
                existing = new Change();
                this.mChanges.put(key, existing);
            }
            existing.merge(other.mChanges.valueAt(i), transfer);
        }
        int n2 = other.mHierarchyOps.size();
        for (int i2 = 0; i2 < n2; i2++) {
            this.mHierarchyOps.add(transfer ? other.mHierarchyOps.get(i2) : new HierarchyOp(other.mHierarchyOps.get(i2)));
        }
    }

    public boolean isEmpty() {
        return this.mChanges.isEmpty() && this.mHierarchyOps.isEmpty();
    }

    public Map<IBinder, Change> getChanges() {
        return this.mChanges;
    }

    public List<HierarchyOp> getHierarchyOps() {
        return this.mHierarchyOps;
    }

    public String toString() {
        return "WindowContainerTransaction { changes = " + this.mChanges + " hops = " + this.mHierarchyOps + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(this.mChanges);
        dest.writeList(this.mHierarchyOps);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* loaded from: classes3.dex */
    public static class Change implements Parcelable {
        public static final int CHANGE_BOUNDS_TRANSACTION = 2;
        public static final int CHANGE_BOUNDS_TRANSACTION_RECT = 16;
        public static final int CHANGE_FOCUSABLE = 1;
        public static final int CHANGE_HIDDEN = 8;
        public static final int CHANGE_IGNORE_ORIENTATION_REQUEST = 32;
        public static final int CHANGE_PIP_CALLBACK = 4;
        public static final Parcelable.Creator<Change> CREATOR = new Parcelable.Creator<Change>() { // from class: android.window.WindowContainerTransaction.Change.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Change createFromParcel(Parcel in) {
                return new Change(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Change[] newArray(int size) {
                return new Change[size];
            }
        };
        private int mActivityWindowingMode;
        private Rect mBoundsChangeSurfaceBounds;
        private SurfaceControl.Transaction mBoundsChangeTransaction;
        private int mChangeMask;
        private int mConfigSetMask;
        private final Configuration mConfiguration;
        private boolean mFocusable;
        private boolean mHidden;
        private boolean mIgnoreOrientationRequest;
        private Rect mPinnedBounds;
        private int mWindowSetMask;
        private int mWindowingMode;

        static /* synthetic */ int access$176(Change x0, int x1) {
            int i = x0.mConfigSetMask | x1;
            x0.mConfigSetMask = i;
            return i;
        }

        static /* synthetic */ int access$276(Change x0, int x1) {
            int i = x0.mWindowSetMask | x1;
            x0.mWindowSetMask = i;
            return i;
        }

        static /* synthetic */ int access$476(Change x0, int x1) {
            int i = x0.mChangeMask | x1;
            x0.mChangeMask = i;
            return i;
        }

        public Change() {
            this.mConfiguration = new Configuration();
            this.mFocusable = true;
            this.mHidden = false;
            this.mIgnoreOrientationRequest = false;
            this.mChangeMask = 0;
            this.mConfigSetMask = 0;
            this.mWindowSetMask = 0;
            this.mPinnedBounds = null;
            this.mBoundsChangeTransaction = null;
            this.mBoundsChangeSurfaceBounds = null;
            this.mActivityWindowingMode = -1;
            this.mWindowingMode = -1;
        }

        protected Change(Parcel in) {
            Configuration configuration = new Configuration();
            this.mConfiguration = configuration;
            this.mFocusable = true;
            this.mHidden = false;
            this.mIgnoreOrientationRequest = false;
            this.mChangeMask = 0;
            this.mConfigSetMask = 0;
            this.mWindowSetMask = 0;
            this.mPinnedBounds = null;
            this.mBoundsChangeTransaction = null;
            this.mBoundsChangeSurfaceBounds = null;
            this.mActivityWindowingMode = -1;
            this.mWindowingMode = -1;
            configuration.readFromParcel(in);
            this.mFocusable = in.readBoolean();
            this.mHidden = in.readBoolean();
            this.mIgnoreOrientationRequest = in.readBoolean();
            this.mChangeMask = in.readInt();
            this.mConfigSetMask = in.readInt();
            this.mWindowSetMask = in.readInt();
            if ((this.mChangeMask & 4) != 0) {
                Rect rect = new Rect();
                this.mPinnedBounds = rect;
                rect.readFromParcel(in);
            }
            if ((this.mChangeMask & 2) != 0) {
                this.mBoundsChangeTransaction = SurfaceControl.Transaction.CREATOR.createFromParcel(in);
            }
            if ((this.mChangeMask & 16) != 0) {
                Rect rect2 = new Rect();
                this.mBoundsChangeSurfaceBounds = rect2;
                rect2.readFromParcel(in);
            }
            this.mWindowingMode = in.readInt();
            this.mActivityWindowingMode = in.readInt();
        }

        public void merge(Change other, boolean transfer) {
            this.mConfiguration.setTo(other.mConfiguration, other.mConfigSetMask, other.mWindowSetMask);
            this.mConfigSetMask |= other.mConfigSetMask;
            this.mWindowSetMask |= other.mWindowSetMask;
            int i = other.mChangeMask;
            if ((i & 1) != 0) {
                this.mFocusable = other.mFocusable;
            }
            if (transfer && (i & 2) != 0) {
                this.mBoundsChangeTransaction = other.mBoundsChangeTransaction;
                other.mBoundsChangeTransaction = null;
            }
            if ((i & 4) != 0) {
                this.mPinnedBounds = transfer ? other.mPinnedBounds : new Rect(other.mPinnedBounds);
            }
            int i2 = other.mChangeMask;
            if ((i2 & 8) != 0) {
                this.mHidden = other.mHidden;
            }
            if ((i2 & 32) != 0) {
                this.mIgnoreOrientationRequest = other.mIgnoreOrientationRequest;
            }
            this.mChangeMask = i2 | this.mChangeMask;
            int i3 = other.mActivityWindowingMode;
            if (i3 >= 0) {
                this.mActivityWindowingMode = i3;
            }
            int i4 = other.mWindowingMode;
            if (i4 >= 0) {
                this.mWindowingMode = i4;
            }
            Rect rect = other.mBoundsChangeSurfaceBounds;
            if (rect != null) {
                if (!transfer) {
                    rect = new Rect(other.mBoundsChangeSurfaceBounds);
                }
                this.mBoundsChangeSurfaceBounds = rect;
            }
        }

        public int getWindowingMode() {
            return this.mWindowingMode;
        }

        public int getActivityWindowingMode() {
            return this.mActivityWindowingMode;
        }

        public Configuration getConfiguration() {
            return this.mConfiguration;
        }

        public boolean getFocusable() {
            if ((this.mChangeMask & 1) != 0) {
                return this.mFocusable;
            }
            throw new RuntimeException("Focusable not set. check CHANGE_FOCUSABLE first");
        }

        public boolean getHidden() {
            if ((this.mChangeMask & 8) != 0) {
                return this.mHidden;
            }
            throw new RuntimeException("Hidden not set. check CHANGE_HIDDEN first");
        }

        public boolean getIgnoreOrientationRequest() {
            if ((this.mChangeMask & 32) != 0) {
                return this.mIgnoreOrientationRequest;
            }
            throw new RuntimeException("IgnoreOrientationRequest not set. Check CHANGE_IGNORE_ORIENTATION_REQUEST first");
        }

        public int getChangeMask() {
            return this.mChangeMask;
        }

        public int getConfigSetMask() {
            return this.mConfigSetMask;
        }

        public int getWindowSetMask() {
            return this.mWindowSetMask;
        }

        public Rect getEnterPipBounds() {
            return this.mPinnedBounds;
        }

        public SurfaceControl.Transaction getBoundsChangeTransaction() {
            return this.mBoundsChangeTransaction;
        }

        public Rect getBoundsChangeSurfaceBounds() {
            return this.mBoundsChangeSurfaceBounds;
        }

        public String toString() {
            int i = this.mConfigSetMask;
            boolean changesSss = false;
            boolean changesBounds = ((i & 536870912) == 0 || (this.mWindowSetMask & 1) == 0) ? false : true;
            boolean changesAppBounds = ((536870912 & i) == 0 || (this.mWindowSetMask & 2) == 0) ? false : true;
            boolean changesSs = (i & 1024) != 0;
            if ((i & 2048) != 0) {
                changesSss = true;
            }
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            if (changesBounds) {
                sb.append("bounds:" + this.mConfiguration.windowConfiguration.getBounds() + ",");
            }
            if (changesAppBounds) {
                sb.append("appbounds:" + this.mConfiguration.windowConfiguration.getAppBounds() + ",");
            }
            if (changesSss) {
                sb.append("ssw:" + this.mConfiguration.smallestScreenWidthDp + ",");
            }
            if (changesSs) {
                sb.append("sw/h:" + this.mConfiguration.screenWidthDp + "x" + this.mConfiguration.screenHeightDp + ",");
            }
            if ((1 & this.mChangeMask) != 0) {
                sb.append("focusable:" + this.mFocusable + ",");
            }
            if (this.mBoundsChangeTransaction != null) {
                sb.append("hasBoundsTransaction,");
            }
            if ((this.mChangeMask & 32) != 0) {
                sb.append("ignoreOrientationRequest:" + this.mIgnoreOrientationRequest + ",");
            }
            sb.append("}");
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            this.mConfiguration.writeToParcel(dest, flags);
            dest.writeBoolean(this.mFocusable);
            dest.writeBoolean(this.mHidden);
            dest.writeBoolean(this.mIgnoreOrientationRequest);
            dest.writeInt(this.mChangeMask);
            dest.writeInt(this.mConfigSetMask);
            dest.writeInt(this.mWindowSetMask);
            Rect rect = this.mPinnedBounds;
            if (rect != null) {
                rect.writeToParcel(dest, flags);
            }
            SurfaceControl.Transaction transaction = this.mBoundsChangeTransaction;
            if (transaction != null) {
                transaction.writeToParcel(dest, flags);
            }
            Rect rect2 = this.mBoundsChangeSurfaceBounds;
            if (rect2 != null) {
                rect2.writeToParcel(dest, flags);
            }
            dest.writeInt(this.mWindowingMode);
            dest.writeInt(this.mActivityWindowingMode);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    /* loaded from: classes3.dex */
    public static class HierarchyOp implements Parcelable {
        public static final Parcelable.Creator<HierarchyOp> CREATOR = new Parcelable.Creator<HierarchyOp>() { // from class: android.window.WindowContainerTransaction.HierarchyOp.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HierarchyOp createFromParcel(Parcel in) {
                return new HierarchyOp(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HierarchyOp[] newArray(int size) {
                return new HierarchyOp[size];
            }
        };
        public static final int HIERARCHY_OP_TYPE_CHILDREN_TASKS_REPARENT = 2;
        public static final int HIERARCHY_OP_TYPE_LAUNCH_TASK = 5;
        public static final int HIERARCHY_OP_TYPE_REORDER = 1;
        public static final int HIERARCHY_OP_TYPE_REPARENT = 0;
        public static final int HIERARCHY_OP_TYPE_SET_ADJACENT_ROOTS = 4;
        public static final int HIERARCHY_OP_TYPE_SET_LAUNCH_ADJACENT_FLAG_ROOT = 6;
        public static final int HIERARCHY_OP_TYPE_SET_LAUNCH_ROOT = 3;
        public static final String LAUNCH_KEY_TASK_ID = "android:transaction.hop.taskId";
        private final int[] mActivityTypes;
        private final IBinder mContainer;
        private final Bundle mLaunchOptions;
        private final IBinder mReparent;
        private final boolean mToTop;
        private final int mType;
        private final int[] mWindowingModes;

        public static HierarchyOp createForReparent(IBinder container, IBinder reparent, boolean toTop) {
            return new HierarchyOp(0, container, reparent, null, null, toTop, null);
        }

        public static HierarchyOp createForReorder(IBinder container, boolean toTop) {
            return new HierarchyOp(1, container, container, null, null, toTop, null);
        }

        public static HierarchyOp createForChildrenTasksReparent(IBinder currentParent, IBinder newParent, int[] windowingModes, int[] activityTypes, boolean onTop) {
            return new HierarchyOp(2, currentParent, newParent, windowingModes, activityTypes, onTop, null);
        }

        public static HierarchyOp createForSetLaunchRoot(IBinder container, int[] windowingModes, int[] activityTypes) {
            return new HierarchyOp(3, container, null, windowingModes, activityTypes, false, null);
        }

        public static HierarchyOp createForAdjacentRoots(IBinder root1, IBinder root2) {
            return new HierarchyOp(4, root1, root2, null, null, false, null);
        }

        public static HierarchyOp createForTaskLaunch(int taskId, Bundle options) {
            Bundle fullOptions = options == null ? new Bundle() : options;
            fullOptions.putInt(LAUNCH_KEY_TASK_ID, taskId);
            return new HierarchyOp(5, null, null, null, null, true, fullOptions);
        }

        public static HierarchyOp createForSetLaunchAdjacentFlagRoot(IBinder container, boolean clearRoot) {
            return new HierarchyOp(6, container, null, null, null, clearRoot, null);
        }

        private HierarchyOp(int type, IBinder container, IBinder reparent, int[] windowingModes, int[] activityTypes, boolean toTop, Bundle launchOptions) {
            int[] iArr;
            this.mType = type;
            this.mContainer = container;
            this.mReparent = reparent;
            int[] iArr2 = null;
            if (windowingModes != null) {
                iArr = Arrays.copyOf(windowingModes, windowingModes.length);
            } else {
                iArr = null;
            }
            this.mWindowingModes = iArr;
            this.mActivityTypes = activityTypes != null ? Arrays.copyOf(activityTypes, activityTypes.length) : iArr2;
            this.mToTop = toTop;
            this.mLaunchOptions = launchOptions;
        }

        public HierarchyOp(HierarchyOp copy) {
            this.mType = copy.mType;
            this.mContainer = copy.mContainer;
            this.mReparent = copy.mReparent;
            this.mToTop = copy.mToTop;
            this.mWindowingModes = copy.mWindowingModes;
            this.mActivityTypes = copy.mActivityTypes;
            this.mLaunchOptions = copy.mLaunchOptions;
        }

        protected HierarchyOp(Parcel in) {
            this.mType = in.readInt();
            this.mContainer = in.readStrongBinder();
            this.mReparent = in.readStrongBinder();
            this.mToTop = in.readBoolean();
            this.mWindowingModes = in.createIntArray();
            this.mActivityTypes = in.createIntArray();
            this.mLaunchOptions = in.readBundle();
        }

        public int getType() {
            return this.mType;
        }

        public boolean isReparent() {
            return this.mType == 0;
        }

        public IBinder getNewParent() {
            return this.mReparent;
        }

        public IBinder getContainer() {
            return this.mContainer;
        }

        public IBinder getAdjacentRoot() {
            return this.mReparent;
        }

        public boolean getToTop() {
            return this.mToTop;
        }

        public int[] getWindowingModes() {
            return this.mWindowingModes;
        }

        public int[] getActivityTypes() {
            return this.mActivityTypes;
        }

        public Bundle getLaunchOptions() {
            return this.mLaunchOptions;
        }

        public String toString() {
            switch (this.mType) {
                case 0:
                    StringBuilder sb = new StringBuilder();
                    sb.append("{reparent: ");
                    sb.append(this.mContainer);
                    sb.append(" to ");
                    sb.append(this.mToTop ? "top of " : "bottom of ");
                    sb.append(this.mReparent);
                    sb.append("}");
                    return sb.toString();
                case 1:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("{reorder: ");
                    sb2.append(this.mContainer);
                    sb2.append(" to ");
                    sb2.append(this.mToTop ? "top" : "bottom");
                    sb2.append("}");
                    return sb2.toString();
                case 2:
                    return "{ChildrenTasksReparent: from=" + this.mContainer + " to=" + this.mReparent + " mToTop=" + this.mToTop + " mWindowingMode=" + this.mWindowingModes + " mActivityType=" + this.mActivityTypes + "}";
                case 3:
                    return "{SetLaunchRoot: container=" + this.mContainer + " mWindowingMode=" + this.mWindowingModes + " mActivityType=" + this.mActivityTypes + "}";
                case 4:
                    return "{SetAdjacentRoot: container=" + this.mContainer + " adjacentRoot=" + this.mReparent + "}";
                case 5:
                    return "{LaunchTask: " + this.mLaunchOptions + "}";
                case 6:
                    return "{SetAdjacentFlagRoot: container=" + this.mContainer + " clearRoot=" + this.mToTop + "}";
                default:
                    return "{mType=" + this.mType + " container=" + this.mContainer + " reparent=" + this.mReparent + " mToTop=" + this.mToTop + " mWindowingMode=" + this.mWindowingModes + " mActivityType=" + this.mActivityTypes + "}";
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mType);
            dest.writeStrongBinder(this.mContainer);
            dest.writeStrongBinder(this.mReparent);
            dest.writeBoolean(this.mToTop);
            dest.writeIntArray(this.mWindowingModes);
            dest.writeIntArray(this.mActivityTypes);
            dest.writeBundle(this.mLaunchOptions);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }
}
