package com.android.internal.policy;

import android.content.res.Resources;
import android.graphics.Rect;
import com.android.internal.R;
import com.android.internal.policy.DividerSnapAlgorithm;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public interface IDividerSnapAlgorithmExt {
    public static final int SNAP_MODE_MINIMIZED = 3;

    default boolean hasFolderFeature() {
        return false;
    }

    default int getSnapMode(Resources res, boolean isMinimizedMode) {
        if (isMinimizedMode) {
            return 3;
        }
        return res.getInteger(R.integer.config_dockedStackDividerSnapMode);
    }

    default boolean getFreeSnapMode(Resources res) {
        return res.getBoolean(R.bool.config_dockedStackDividerFreeSnapMode);
    }

    default boolean addRatioTargets(ArrayList<DividerSnapAlgorithm.SnapTarget> targets, int dividerMax, int dividerSize, int minimalTaskSize) {
        return false;
    }

    default boolean addMinimizedTarget(ArrayList<DividerSnapAlgorithm.SnapTarget> targets, int dockedSide, int dividerSize) {
        return false;
    }

    default boolean addMinimizedTarget(ArrayList<DividerSnapAlgorithm.SnapTarget> targets, int dockedSide, int dividerSize, int taskHeightInMinimized, Rect insets, int displayWidth, int displayHeight) {
        return false;
    }

    default boolean addMiddleTarget(ArrayList<DividerSnapAlgorithm.SnapTarget> targets, boolean isHorizontalDivision, int displayWidth, int displayHeight, int dividerSize) {
        return false;
    }
}
