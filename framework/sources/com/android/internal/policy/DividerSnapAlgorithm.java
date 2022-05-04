package com.android.internal.policy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.view.DisplayInfo;
import com.android.internal.R;
import com.oplus.reflect.RefConstructor;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public class DividerSnapAlgorithm {
    private static final int MINIMAL_TASK_SIZE_DP = 300;
    private static final int MIN_DISMISS_VELOCITY_DP_PER_SECOND = 600;
    private static final int MIN_FLING_VELOCITY_DP_PER_SECOND = 400;
    private static final int SNAP_FIXED_RATIO = 1;
    private static final int SNAP_MODE_16_9 = 0;
    private static final int SNAP_MODE_MINIMIZED = 3;
    private static final int SNAP_ONLY_1_1 = 2;
    private final SnapTarget mDismissEndTarget;
    private final SnapTarget mDismissStartTarget;
    private final int mDisplayHeight;
    private final int mDisplayWidth;
    private final int mDividerSize;
    private IDividerSnapAlgorithmExt mDividerSnapAlgorithmExt;
    private final SnapTarget mFirstSplitTarget;
    private final float mFixedRatio;
    private final boolean mFreeSnapMode;
    private final Rect mInsets;
    private boolean mIsHorizontalDivision;
    private final SnapTarget mLastSplitTarget;
    private final SnapTarget mMiddleTarget;
    private final float mMinDismissVelocityPxPerSecond;
    private final float mMinFlingVelocityPxPerSecond;
    private final int mMinimalSizeResizableTask;
    private final int mMinimalTaskSize;
    private final int mSnapMode;
    private final ArrayList<SnapTarget> mTargets;
    private final int mTaskHeightInMinimizedMode;

    public static DividerSnapAlgorithm create(Context ctx, Rect insets) {
        DisplayInfo displayInfo = new DisplayInfo();
        ((DisplayManager) ctx.getSystemService(DisplayManager.class)).getDisplay(0).getDisplayInfo(displayInfo);
        int dividerWindowWidth = ctx.getResources().getDimensionPixelSize(R.dimen.docked_stack_divider_thickness);
        int dividerInsets = DockedDividerUtils.getDividerInsets(ctx.getResources());
        Resources resources = ctx.getResources();
        int i = displayInfo.logicalWidth;
        int i2 = displayInfo.logicalHeight;
        int i3 = dividerWindowWidth - (dividerInsets * 2);
        boolean z = true;
        if (ctx.getApplicationContext().getResources().getConfiguration().orientation != 1) {
            z = false;
        }
        return new DividerSnapAlgorithm(resources, i, i2, i3, z, insets);
    }

    public DividerSnapAlgorithm(Resources res, int displayWidth, int displayHeight, int dividerSize, boolean isHorizontalDivision, Rect insets) {
        this(res, displayWidth, displayHeight, dividerSize, isHorizontalDivision, insets, -1, false, true);
    }

    public DividerSnapAlgorithm(Resources res, int displayWidth, int displayHeight, int dividerSize, boolean isHorizontalDivision, Rect insets, int dockSide) {
        this(res, displayWidth, displayHeight, dividerSize, isHorizontalDivision, insets, dockSide, false, true);
    }

    public DividerSnapAlgorithm(Resources res, int displayWidth, int displayHeight, int dividerSize, boolean isHorizontalDivision, Rect insets, int dockSide, boolean isMinimizedMode, boolean isHomeResizable) {
        ArrayList<SnapTarget> arrayList = new ArrayList<>();
        this.mTargets = arrayList;
        Rect rect = new Rect();
        this.mInsets = rect;
        this.mMinFlingVelocityPxPerSecond = res.getDisplayMetrics().density * 400.0f;
        this.mMinDismissVelocityPxPerSecond = res.getDisplayMetrics().density * 600.0f;
        this.mDividerSize = dividerSize;
        this.mDisplayWidth = displayWidth;
        this.mDisplayHeight = displayHeight;
        this.mIsHorizontalDivision = isHorizontalDivision;
        rect.set(insets);
        RefConstructor<IDividerSnapAlgorithmExt> constructor = DividerSnapAlgorithmExtPlugin.constructor;
        if (constructor != null) {
            IDividerSnapAlgorithmExt newInstance = constructor.newInstance();
            this.mDividerSnapAlgorithmExt = newInstance;
            this.mSnapMode = newInstance.getSnapMode(res, isMinimizedMode);
            this.mFreeSnapMode = this.mDividerSnapAlgorithmExt.getFreeSnapMode(res);
        } else {
            this.mSnapMode = isMinimizedMode ? 3 : res.getInteger(R.integer.config_dockedStackDividerSnapMode);
            this.mFreeSnapMode = res.getBoolean(R.bool.config_dockedStackDividerFreeSnapMode);
        }
        this.mMinimalTaskSize = (int) (res.getDisplayMetrics().density * 300.0f);
        this.mFixedRatio = res.getFraction(R.fraction.docked_stack_divider_fixed_ratio, 1, 1);
        this.mMinimalSizeResizableTask = res.getDimensionPixelSize(R.dimen.default_minimal_size_resizable_task);
        this.mTaskHeightInMinimizedMode = isHomeResizable ? res.getDimensionPixelSize(R.dimen.task_height_of_minimized_mode) : 0;
        calculateTargets(isHorizontalDivision, dockSide);
        this.mFirstSplitTarget = arrayList.get(1);
        this.mLastSplitTarget = arrayList.get(arrayList.size() - 2);
        this.mDismissStartTarget = arrayList.get(0);
        this.mDismissEndTarget = arrayList.get(arrayList.size() - 1);
        SnapTarget snapTarget = arrayList.get(arrayList.size() / 2);
        this.mMiddleTarget = snapTarget;
        snapTarget.isMiddleTarget = true;
    }

    public boolean isSplitScreenFeasible() {
        int size;
        int statusBarSize = this.mInsets.top;
        int navBarSize = this.mIsHorizontalDivision ? this.mInsets.bottom : this.mInsets.right;
        if (this.mIsHorizontalDivision) {
            size = this.mDisplayHeight;
        } else {
            size = this.mDisplayWidth;
        }
        int availableSpace = ((size - navBarSize) - statusBarSize) - this.mDividerSize;
        return availableSpace / 2 >= this.mMinimalSizeResizableTask;
    }

    public SnapTarget calculateSnapTarget(int position, float velocity) {
        return calculateSnapTarget(position, velocity, true);
    }

    public SnapTarget calculateSnapTarget(int position, float velocity, boolean hardDismiss) {
        if (position < this.mFirstSplitTarget.position && velocity < (-this.mMinDismissVelocityPxPerSecond)) {
            return this.mDismissStartTarget;
        }
        if (position > this.mLastSplitTarget.position && velocity > this.mMinDismissVelocityPxPerSecond) {
            return this.mDismissEndTarget;
        }
        if (Math.abs(velocity) < this.mMinFlingVelocityPxPerSecond) {
            return snap(position, hardDismiss);
        }
        if (velocity < 0.0f) {
            return this.mFirstSplitTarget;
        }
        return this.mLastSplitTarget;
    }

    public SnapTarget calculateNonDismissingSnapTarget(int position) {
        SnapTarget target = snap(position, false);
        if (target == this.mDismissStartTarget) {
            return this.mFirstSplitTarget;
        }
        if (target == this.mDismissEndTarget) {
            return this.mLastSplitTarget;
        }
        return target;
    }

    public float calculateDismissingFraction(int position) {
        if (position < this.mFirstSplitTarget.position) {
            return 1.0f - ((position - getStartInset()) / (this.mFirstSplitTarget.position - getStartInset()));
        }
        if (position > this.mLastSplitTarget.position) {
            return (position - this.mLastSplitTarget.position) / ((this.mDismissEndTarget.position - this.mLastSplitTarget.position) - this.mDividerSize);
        }
        return 0.0f;
    }

    public SnapTarget getClosestDismissTarget(int position) {
        if (position < this.mFirstSplitTarget.position) {
            return this.mDismissStartTarget;
        }
        if (position > this.mLastSplitTarget.position) {
            return this.mDismissEndTarget;
        }
        if (position - this.mDismissStartTarget.position < this.mDismissEndTarget.position - position) {
            return this.mDismissStartTarget;
        }
        return this.mDismissEndTarget;
    }

    public SnapTarget getFirstSplitTarget() {
        return this.mFirstSplitTarget;
    }

    public SnapTarget getLastSplitTarget() {
        return this.mLastSplitTarget;
    }

    public SnapTarget getDismissStartTarget() {
        return this.mDismissStartTarget;
    }

    public SnapTarget getDismissEndTarget() {
        return this.mDismissEndTarget;
    }

    private int getStartInset() {
        if (this.mIsHorizontalDivision) {
            return this.mInsets.top;
        }
        return this.mInsets.left;
    }

    private int getEndInset() {
        if (this.mIsHorizontalDivision) {
            return this.mInsets.bottom;
        }
        return this.mInsets.right;
    }

    private boolean shouldApplyFreeSnapMode(int position) {
        return this.mFreeSnapMode && isFirstSplitTargetAvailable() && isLastSplitTargetAvailable() && this.mFirstSplitTarget.position < position && position < this.mLastSplitTarget.position;
    }

    private SnapTarget snap(int position, boolean hardDismiss) {
        if (shouldApplyFreeSnapMode(position)) {
            return new SnapTarget(position, position, 0);
        }
        int minIndex = -1;
        float minDistance = Float.MAX_VALUE;
        int size = this.mTargets.size();
        for (int i = 0; i < size; i++) {
            SnapTarget target = this.mTargets.get(i);
            float distance = Math.abs(position - target.position);
            if (hardDismiss) {
                distance /= target.distanceMultiplier;
            }
            if (distance < minDistance) {
                minIndex = i;
                minDistance = distance;
            }
        }
        return this.mTargets.get(minIndex);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x007f, code lost:
        if (r1.addMiddleTarget(r18.mTargets, r19, r18.mDisplayWidth, r18.mDisplayHeight, r18.mDividerSize) == false) goto L_0x0084;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void calculateTargets(boolean r19, int r20) {
        /*
            r18 = this;
            r0 = r18
            r7 = r19
            java.util.ArrayList<com.android.internal.policy.DividerSnapAlgorithm$SnapTarget> r1 = r0.mTargets
            r1.clear()
            if (r7 == 0) goto L_0x000e
            int r1 = r0.mDisplayHeight
            goto L_0x0010
        L_0x000e:
            int r1 = r0.mDisplayWidth
        L_0x0010:
            r8 = r1
            android.graphics.Rect r1 = r0.mInsets
            if (r7 == 0) goto L_0x0018
            int r1 = r1.bottom
            goto L_0x001a
        L_0x0018:
            int r1 = r1.right
        L_0x001a:
            r9 = r1
            int r1 = r0.mDividerSize
            int r1 = -r1
            r2 = 3
            r6 = r20
            if (r6 != r2) goto L_0x002a
            android.graphics.Rect r2 = r0.mInsets
            int r2 = r2.left
            int r1 = r1 + r2
            r5 = r1
            goto L_0x002b
        L_0x002a:
            r5 = r1
        L_0x002b:
            java.util.ArrayList<com.android.internal.policy.DividerSnapAlgorithm$SnapTarget> r1 = r0.mTargets
            com.android.internal.policy.DividerSnapAlgorithm$SnapTarget r2 = new com.android.internal.policy.DividerSnapAlgorithm$SnapTarget
            r3 = 1
            r4 = 1051931443(0x3eb33333, float:0.35)
            r2.<init>(r5, r5, r3, r4)
            r1.add(r2)
            int r1 = r0.mSnapMode
            switch(r1) {
                case 0: goto L_0x008e;
                case 1: goto L_0x0088;
                case 2: goto L_0x0068;
                case 3: goto L_0x0042;
                default: goto L_0x003e;
            }
        L_0x003e:
            r13 = r4
            r10 = r5
            goto L_0x00a3
        L_0x0042:
            com.android.internal.policy.IDividerSnapAlgorithmExt r10 = r0.mDividerSnapAlgorithmExt
            if (r10 == 0) goto L_0x0062
            java.util.ArrayList<com.android.internal.policy.DividerSnapAlgorithm$SnapTarget> r11 = r0.mTargets
            int r13 = r0.mDividerSize
            int r14 = r0.mTaskHeightInMinimizedMode
            android.graphics.Rect r15 = r0.mInsets
            int r1 = r0.mDisplayWidth
            int r2 = r0.mDisplayHeight
            r12 = r20
            r16 = r1
            r17 = r2
            boolean r1 = r10.addMinimizedTarget(r11, r12, r13, r14, r15, r16, r17)
            if (r1 != 0) goto L_0x005f
            goto L_0x0062
        L_0x005f:
            r13 = r4
            r10 = r5
            goto L_0x00a3
        L_0x0062:
            r18.addMinimizedTarget(r19, r20)
            r13 = r4
            r10 = r5
            goto L_0x00a3
        L_0x0068:
            com.android.internal.policy.IDividerSnapAlgorithmExt r1 = r0.mDividerSnapAlgorithmExt
            if (r1 == 0) goto L_0x0082
            java.util.ArrayList<com.android.internal.policy.DividerSnapAlgorithm$SnapTarget> r2 = r0.mTargets
            int r10 = r0.mDisplayWidth
            int r11 = r0.mDisplayHeight
            int r12 = r0.mDividerSize
            r3 = r19
            r13 = r4
            r4 = r10
            r10 = r5
            r5 = r11
            r6 = r12
            boolean r1 = r1.addMiddleTarget(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x00a3
            goto L_0x0084
        L_0x0082:
            r13 = r4
            r10 = r5
        L_0x0084:
            r18.addMiddleTarget(r19)
            goto L_0x00a3
        L_0x0088:
            r13 = r4
            r10 = r5
            r0.addFixedDivisionTargets(r7, r8)
            goto L_0x00a3
        L_0x008e:
            r13 = r4
            r10 = r5
            com.android.internal.policy.IDividerSnapAlgorithmExt r1 = r0.mDividerSnapAlgorithmExt
            if (r1 == 0) goto L_0x00a0
            java.util.ArrayList<com.android.internal.policy.DividerSnapAlgorithm$SnapTarget> r2 = r0.mTargets
            int r3 = r0.mDividerSize
            int r4 = r0.mMinimalTaskSize
            boolean r1 = r1.addRatioTargets(r2, r8, r3, r4)
            if (r1 != 0) goto L_0x00a3
        L_0x00a0:
            r0.addRatio16_9Targets(r7, r8)
        L_0x00a3:
            java.util.ArrayList<com.android.internal.policy.DividerSnapAlgorithm$SnapTarget> r1 = r0.mTargets
            com.android.internal.policy.DividerSnapAlgorithm$SnapTarget r2 = new com.android.internal.policy.DividerSnapAlgorithm$SnapTarget
            r3 = 2
            r2.<init>(r8, r8, r3, r13)
            r1.add(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.policy.DividerSnapAlgorithm.calculateTargets(boolean, int):void");
    }

    private void addNonDismissingTargets(boolean isHorizontalDivision, int topPosition, int bottomPosition, int dividerMax) {
        maybeAddTarget(topPosition, topPosition - getStartInset());
        addMiddleTarget(isHorizontalDivision);
        maybeAddTarget(bottomPosition, (dividerMax - getEndInset()) - (this.mDividerSize + bottomPosition));
    }

    private void addFixedDivisionTargets(boolean isHorizontalDivision, int dividerMax) {
        int end;
        Rect rect = this.mInsets;
        int start = isHorizontalDivision ? rect.top : rect.left;
        if (isHorizontalDivision) {
            end = this.mDisplayHeight - this.mInsets.bottom;
        } else {
            end = this.mDisplayWidth - this.mInsets.right;
        }
        int i = this.mDividerSize;
        int size = ((int) (this.mFixedRatio * (end - start))) - (i / 2);
        int topPosition = start + size;
        int bottomPosition = (end - size) - i;
        addNonDismissingTargets(isHorizontalDivision, topPosition, bottomPosition, dividerMax);
    }

    private void addRatio16_9Targets(boolean isHorizontalDivision, int dividerMax) {
        int end;
        int endOther;
        Rect rect = this.mInsets;
        int start = isHorizontalDivision ? rect.top : rect.left;
        if (isHorizontalDivision) {
            end = this.mDisplayHeight - this.mInsets.bottom;
        } else {
            end = this.mDisplayWidth - this.mInsets.right;
        }
        Rect rect2 = this.mInsets;
        int startOther = isHorizontalDivision ? rect2.left : rect2.top;
        if (isHorizontalDivision) {
            endOther = this.mDisplayWidth - this.mInsets.right;
        } else {
            endOther = this.mDisplayHeight - this.mInsets.bottom;
        }
        float size = (endOther - startOther) * 0.5625f;
        int sizeInt = (int) Math.floor(size);
        int topPosition = start + sizeInt;
        int bottomPosition = (end - sizeInt) - this.mDividerSize;
        if (topPosition - this.mInsets.top < this.mMinimalSizeResizableTask) {
            topPosition = this.mInsets.top + this.mMinimalSizeResizableTask;
        }
        if ((dividerMax - this.mInsets.bottom) - (this.mDividerSize + bottomPosition) < this.mMinimalSizeResizableTask) {
            bottomPosition = ((dividerMax - this.mInsets.bottom) - this.mDividerSize) - this.mMinimalSizeResizableTask;
        }
        addNonDismissingTargets(isHorizontalDivision, topPosition, bottomPosition, dividerMax);
    }

    private void maybeAddTarget(int position, int smallerSize) {
        if (smallerSize >= this.mMinimalSizeResizableTask) {
            this.mTargets.add(new SnapTarget(position, position, 0));
        }
    }

    private void addMiddleTarget(boolean isHorizontalDivision) {
        int position = DockedDividerUtils.calculateMiddlePosition(isHorizontalDivision, this.mInsets, this.mDisplayWidth, this.mDisplayHeight, this.mDividerSize);
        this.mTargets.add(new SnapTarget(position, position, 0));
    }

    private void addMinimizedTarget(boolean isHorizontalDivision, int dockedSide) {
        int position = this.mTaskHeightInMinimizedMode + this.mInsets.top;
        if (!isHorizontalDivision) {
            if (dockedSide == 1) {
                position += this.mInsets.left;
            } else if (dockedSide == 3) {
                position = ((this.mDisplayWidth - position) - this.mInsets.right) - this.mDividerSize;
            }
        }
        this.mTargets.add(new SnapTarget(position, position, 0));
    }

    public SnapTarget getMiddleTarget() {
        return this.mMiddleTarget;
    }

    public SnapTarget getNextTarget(SnapTarget snapTarget) {
        int index = this.mTargets.indexOf(snapTarget);
        if (index == -1 || index >= this.mTargets.size() - 1) {
            return snapTarget;
        }
        return this.mTargets.get(index + 1);
    }

    public SnapTarget getPreviousTarget(SnapTarget snapTarget) {
        int index = this.mTargets.indexOf(snapTarget);
        if (index == -1 || index <= 0) {
            return snapTarget;
        }
        return this.mTargets.get(index - 1);
    }

    public boolean showMiddleSplitTargetForAccessibility() {
        return this.mTargets.size() + (-2) > 1;
    }

    public boolean isFirstSplitTargetAvailable() {
        return this.mFirstSplitTarget != this.mMiddleTarget;
    }

    public boolean isLastSplitTargetAvailable() {
        return this.mLastSplitTarget != this.mMiddleTarget;
    }

    public SnapTarget cycleNonDismissTarget(SnapTarget snapTarget, int increment) {
        int index = this.mTargets.indexOf(snapTarget);
        if (index == -1) {
            return snapTarget;
        }
        ArrayList<SnapTarget> arrayList = this.mTargets;
        SnapTarget newTarget = arrayList.get(((arrayList.size() + index) + increment) % this.mTargets.size());
        if (newTarget == this.mDismissStartTarget) {
            return this.mLastSplitTarget;
        }
        if (newTarget == this.mDismissEndTarget) {
            return this.mFirstSplitTarget;
        }
        return newTarget;
    }

    /* loaded from: classes4.dex */
    public static class SnapTarget {
        public static final int FLAG_DISMISS_END = 2;
        public static final int FLAG_DISMISS_START = 1;
        public static final int FLAG_NONE = 0;
        private final float distanceMultiplier;
        public final int flag;
        public boolean isMiddleTarget;
        public final int position;
        public final int taskPosition;

        public SnapTarget(int position, int taskPosition, int flag) {
            this(position, taskPosition, flag, 1.0f);
        }

        public SnapTarget(int position, int taskPosition, int flag, float distanceMultiplier) {
            this.position = position;
            this.taskPosition = taskPosition;
            this.flag = flag;
            this.distanceMultiplier = distanceMultiplier;
        }
    }
}
