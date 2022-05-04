package android.view;

import android.graphics.Insets;
import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class WindowInsets {
    public static final WindowInsets CONSUMED = new WindowInsets(null, null, false, false, null);
    private final boolean mAlwaysConsumeSystemBars;
    private final boolean mCompatIgnoreVisibility;
    private final int mCompatInsetsTypes;
    private final DisplayCutout mDisplayCutout;
    private final boolean mDisplayCutoutConsumed;
    private final boolean mIsRound;
    private final PrivacyIndicatorBounds mPrivacyIndicatorBounds;
    private final RoundedCorners mRoundedCorners;
    private final boolean mStableInsetsConsumed;
    private final boolean mSystemWindowInsetsConsumed;
    private Rect mTempRect;
    private final Insets[] mTypeInsetsMap;
    private final Insets[] mTypeMaxInsetsMap;
    private final boolean[] mTypeVisibilityMap;

    @Deprecated
    public WindowInsets(Rect systemWindowInsetsRect, Rect stableInsetsRect, boolean isRound, boolean alwaysConsumeSystemBars, DisplayCutout displayCutout) {
        this(createCompatTypeMap(systemWindowInsetsRect), createCompatTypeMap(stableInsetsRect), createCompatVisibilityMap(createCompatTypeMap(systemWindowInsetsRect)), isRound, alwaysConsumeSystemBars, displayCutout, null, null, Type.systemBars(), false);
    }

    public WindowInsets(Insets[] typeInsetsMap, Insets[] typeMaxInsetsMap, boolean[] typeVisibilityMap, boolean isRound, boolean alwaysConsumeSystemBars, DisplayCutout displayCutout, RoundedCorners roundedCorners, PrivacyIndicatorBounds privacyIndicatorBounds, int compatInsetsTypes, boolean compatIgnoreVisibility) {
        Insets[] insetsArr;
        Insets[] insetsArr2;
        boolean z = true;
        boolean z2 = typeInsetsMap == null;
        this.mSystemWindowInsetsConsumed = z2;
        if (z2) {
            insetsArr = new Insets[9];
        } else {
            insetsArr = (Insets[]) typeInsetsMap.clone();
        }
        this.mTypeInsetsMap = insetsArr;
        boolean z3 = typeMaxInsetsMap == null;
        this.mStableInsetsConsumed = z3;
        if (z3) {
            insetsArr2 = new Insets[9];
        } else {
            insetsArr2 = (Insets[]) typeMaxInsetsMap.clone();
        }
        this.mTypeMaxInsetsMap = insetsArr2;
        this.mTypeVisibilityMap = typeVisibilityMap;
        this.mIsRound = isRound;
        this.mAlwaysConsumeSystemBars = alwaysConsumeSystemBars;
        this.mCompatInsetsTypes = compatInsetsTypes;
        this.mCompatIgnoreVisibility = compatIgnoreVisibility;
        z = displayCutout != null ? false : z;
        this.mDisplayCutoutConsumed = z;
        this.mDisplayCutout = (z || displayCutout.isEmpty()) ? null : displayCutout;
        this.mRoundedCorners = roundedCorners;
        this.mPrivacyIndicatorBounds = privacyIndicatorBounds;
    }

    public WindowInsets(WindowInsets src) {
        this(src.mSystemWindowInsetsConsumed ? null : src.mTypeInsetsMap, !src.mStableInsetsConsumed ? src.mTypeMaxInsetsMap : null, src.mTypeVisibilityMap, src.mIsRound, src.mAlwaysConsumeSystemBars, displayCutoutCopyConstructorArgument(src), src.mRoundedCorners, src.mPrivacyIndicatorBounds, src.mCompatInsetsTypes, src.mCompatIgnoreVisibility);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DisplayCutout displayCutoutCopyConstructorArgument(WindowInsets w) {
        if (w.mDisplayCutoutConsumed) {
            return null;
        }
        DisplayCutout displayCutout = w.mDisplayCutout;
        if (displayCutout == null) {
            return DisplayCutout.NO_CUTOUT;
        }
        return displayCutout;
    }

    static Insets getInsets(Insets[] typeInsetsMap, int typeMask) {
        Insets insets;
        Insets result = null;
        for (int i = 1; i <= 256; i <<= 1) {
            if (!((typeMask & i) == 0 || (insets = typeInsetsMap[Type.indexOf(i)]) == null)) {
                if (result == null) {
                    result = insets;
                } else {
                    result = Insets.max(result, insets);
                }
            }
        }
        return result == null ? Insets.NONE : result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setInsets(Insets[] typeInsetsMap, int typeMask, Insets insets) {
        for (int i = 1; i <= 256; i <<= 1) {
            if ((typeMask & i) != 0) {
                typeInsetsMap[Type.indexOf(i)] = insets;
            }
        }
    }

    public WindowInsets(Rect systemWindowInsets) {
        this(createCompatTypeMap(systemWindowInsets), null, new boolean[9], false, false, null, null, null, Type.systemBars(), false);
    }

    private static Insets[] createCompatTypeMap(Rect insets) {
        if (insets == null) {
            return null;
        }
        Insets[] typeInsetsMap = new Insets[9];
        assignCompatInsets(typeInsetsMap, insets);
        return typeInsetsMap;
    }

    public static void assignCompatInsets(Insets[] typeInsetsMap, Rect insets) {
        typeInsetsMap[Type.indexOf(1)] = Insets.of(0, insets.top, 0, 0);
        typeInsetsMap[Type.indexOf(2)] = Insets.of(insets.left, 0, insets.right, insets.bottom);
    }

    private static boolean[] createCompatVisibilityMap(Insets[] typeInsetsMap) {
        boolean[] typeVisibilityMap = new boolean[9];
        if (typeInsetsMap == null) {
            return typeVisibilityMap;
        }
        for (int i = 1; i <= 256; i <<= 1) {
            int index = Type.indexOf(i);
            if (!Insets.NONE.equals(typeInsetsMap[index])) {
                typeVisibilityMap[index] = true;
            }
        }
        return typeVisibilityMap;
    }

    @Deprecated
    public Rect getSystemWindowInsetsAsRect() {
        if (this.mTempRect == null) {
            this.mTempRect = new Rect();
        }
        Insets insets = getSystemWindowInsets();
        this.mTempRect.set(insets.left, insets.top, insets.right, insets.bottom);
        return this.mTempRect;
    }

    @Deprecated
    public Insets getSystemWindowInsets() {
        Insets result;
        if (this.mCompatIgnoreVisibility) {
            result = getInsetsIgnoringVisibility(this.mCompatInsetsTypes & (~Type.ime()));
        } else {
            result = getInsets(this.mCompatInsetsTypes);
        }
        if ((this.mCompatInsetsTypes & Type.ime()) == 0 || !this.mCompatIgnoreVisibility) {
            return result;
        }
        return Insets.max(result, getInsets(Type.ime()));
    }

    public Insets getInsets(int typeMask) {
        return getInsets(this.mTypeInsetsMap, typeMask);
    }

    public Insets getInsetsIgnoringVisibility(int typeMask) {
        if ((typeMask & 8) == 0) {
            return getInsets(this.mTypeMaxInsetsMap, typeMask);
        }
        throw new IllegalArgumentException("Unable to query the maximum insets for IME");
    }

    public boolean isVisible(int typeMask) {
        for (int i = 1; i <= 256; i <<= 1) {
            if ((typeMask & i) != 0 && !this.mTypeVisibilityMap[Type.indexOf(i)]) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return getSystemWindowInsets().left;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return getSystemWindowInsets().top;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return getSystemWindowInsets().right;
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return getSystemWindowInsets().bottom;
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !getSystemWindowInsets().equals(Insets.NONE);
    }

    public boolean hasInsets() {
        return !getInsets(this.mTypeInsetsMap, Type.all()).equals(Insets.NONE) || !getInsets(this.mTypeMaxInsetsMap, Type.all()).equals(Insets.NONE) || this.mDisplayCutout != null || this.mRoundedCorners != null;
    }

    public DisplayCutout getDisplayCutout() {
        return this.mDisplayCutout;
    }

    public RoundedCorner getRoundedCorner(int position) {
        RoundedCorners roundedCorners = this.mRoundedCorners;
        if (roundedCorners == null) {
            return null;
        }
        return roundedCorners.getRoundedCorner(position);
    }

    public Rect getPrivacyIndicatorBounds() {
        PrivacyIndicatorBounds privacyIndicatorBounds = this.mPrivacyIndicatorBounds;
        if (privacyIndicatorBounds == null) {
            return null;
        }
        return privacyIndicatorBounds.getStaticPrivacyIndicatorBounds();
    }

    @Deprecated
    public WindowInsets consumeDisplayCutout() {
        return new WindowInsets(this.mSystemWindowInsetsConsumed ? null : this.mTypeInsetsMap, this.mStableInsetsConsumed ? null : this.mTypeMaxInsetsMap, this.mTypeVisibilityMap, this.mIsRound, this.mAlwaysConsumeSystemBars, null, this.mRoundedCorners, this.mPrivacyIndicatorBounds, this.mCompatInsetsTypes, this.mCompatIgnoreVisibility);
    }

    public boolean isConsumed() {
        return this.mSystemWindowInsetsConsumed && this.mStableInsetsConsumed && this.mDisplayCutoutConsumed;
    }

    public boolean isRound() {
        return this.mIsRound;
    }

    @Deprecated
    public WindowInsets consumeSystemWindowInsets() {
        return new WindowInsets(null, null, this.mTypeVisibilityMap, this.mIsRound, this.mAlwaysConsumeSystemBars, displayCutoutCopyConstructorArgument(this), this.mRoundedCorners, this.mPrivacyIndicatorBounds, this.mCompatInsetsTypes, this.mCompatIgnoreVisibility);
    }

    @Deprecated
    public WindowInsets replaceSystemWindowInsets(int left, int top, int right, int bottom) {
        if (this.mSystemWindowInsetsConsumed) {
            return this;
        }
        return new Builder(this).setSystemWindowInsets(Insets.of(left, top, right, bottom)).build();
    }

    @Deprecated
    public WindowInsets replaceSystemWindowInsets(Rect systemWindowInsets) {
        return replaceSystemWindowInsets(systemWindowInsets.left, systemWindowInsets.top, systemWindowInsets.right, systemWindowInsets.bottom);
    }

    @Deprecated
    public Insets getStableInsets() {
        return getInsets(this.mTypeMaxInsetsMap, this.mCompatInsetsTypes);
    }

    @Deprecated
    public int getStableInsetTop() {
        return getStableInsets().top;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return getStableInsets().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return getStableInsets().right;
    }

    @Deprecated
    public int getStableInsetBottom() {
        return getStableInsets().bottom;
    }

    @Deprecated
    public boolean hasStableInsets() {
        return !getStableInsets().equals(Insets.NONE);
    }

    @Deprecated
    public Insets getSystemGestureInsets() {
        return getInsets(this.mTypeInsetsMap, 16);
    }

    @Deprecated
    public Insets getMandatorySystemGestureInsets() {
        return getInsets(this.mTypeInsetsMap, 32);
    }

    @Deprecated
    public Insets getTappableElementInsets() {
        return getInsets(this.mTypeInsetsMap, 64);
    }

    @Deprecated
    public WindowInsets consumeStableInsets() {
        return this;
    }

    public boolean shouldAlwaysConsumeSystemBars() {
        return this.mAlwaysConsumeSystemBars;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("WindowInsets{\n    ");
        for (int i = 0; i < 9; i++) {
            Insets insets = this.mTypeInsetsMap[i];
            Insets maxInsets = this.mTypeMaxInsetsMap[i];
            boolean visible = this.mTypeVisibilityMap[i];
            if (!Insets.NONE.equals(insets) || !Insets.NONE.equals(maxInsets) || visible) {
                result.append(Type.toString(1 << i));
                result.append("=");
                result.append(insets);
                result.append(" max=");
                result.append(maxInsets);
                result.append(" vis=");
                result.append(visible);
                result.append("\n    ");
            }
        }
        String str = "";
        result.append(this.mDisplayCutout != null ? "cutout=" + this.mDisplayCutout : str);
        result.append("\n    ");
        result.append(this.mRoundedCorners != null ? "roundedCorners=" + this.mRoundedCorners : str);
        result.append("\n    ");
        result.append(this.mPrivacyIndicatorBounds != null ? "privacyIndicatorBounds=" + this.mPrivacyIndicatorBounds : str);
        result.append("\n    ");
        if (isRound()) {
            str = "round";
        }
        result.append(str);
        result.append("}");
        return result.toString();
    }

    @Deprecated
    public WindowInsets inset(Rect r) {
        return inset(r.left, r.top, r.right, r.bottom);
    }

    public WindowInsets inset(Insets insets) {
        Objects.requireNonNull(insets);
        return inset(insets.left, insets.top, insets.right, insets.bottom);
    }

    public WindowInsets inset(int left, int top, int right, int bottom) {
        Preconditions.checkArgumentNonnegative(left);
        Preconditions.checkArgumentNonnegative(top);
        Preconditions.checkArgumentNonnegative(right);
        Preconditions.checkArgumentNonnegative(bottom);
        return insetUnchecked(left, top, right, bottom);
    }

    public WindowInsets insetUnchecked(int left, int top, int right, int bottom) {
        Insets[] insetsArr;
        Insets[] insetsArr2;
        DisplayCutout displayCutout;
        RoundedCorners roundedCorners;
        PrivacyIndicatorBounds privacyIndicatorBounds;
        if (this.mSystemWindowInsetsConsumed) {
            insetsArr = null;
        } else {
            insetsArr = insetInsets(this.mTypeInsetsMap, left, top, right, bottom);
        }
        if (this.mStableInsetsConsumed) {
            insetsArr2 = null;
        } else {
            insetsArr2 = insetInsets(this.mTypeMaxInsetsMap, left, top, right, bottom);
        }
        boolean[] zArr = this.mTypeVisibilityMap;
        boolean z = this.mIsRound;
        boolean z2 = this.mAlwaysConsumeSystemBars;
        if (this.mDisplayCutoutConsumed) {
            displayCutout = null;
        } else {
            DisplayCutout displayCutout2 = this.mDisplayCutout;
            if (displayCutout2 == null) {
                displayCutout = DisplayCutout.NO_CUTOUT;
            } else {
                displayCutout = displayCutout2.inset(left, top, right, bottom);
            }
        }
        RoundedCorners roundedCorners2 = this.mRoundedCorners;
        if (roundedCorners2 == null) {
            roundedCorners = RoundedCorners.NO_ROUNDED_CORNERS;
        } else {
            roundedCorners = roundedCorners2.inset(left, top, right, bottom);
        }
        PrivacyIndicatorBounds privacyIndicatorBounds2 = this.mPrivacyIndicatorBounds;
        if (privacyIndicatorBounds2 == null) {
            privacyIndicatorBounds = null;
        } else {
            privacyIndicatorBounds = privacyIndicatorBounds2.inset(left, top, right, bottom);
        }
        return new WindowInsets(insetsArr, insetsArr2, zArr, z, z2, displayCutout, roundedCorners, privacyIndicatorBounds, this.mCompatInsetsTypes, this.mCompatIgnoreVisibility);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof WindowInsets)) {
            return false;
        }
        WindowInsets that = (WindowInsets) o;
        if (this.mIsRound != that.mIsRound || this.mAlwaysConsumeSystemBars != that.mAlwaysConsumeSystemBars || this.mSystemWindowInsetsConsumed != that.mSystemWindowInsetsConsumed || this.mStableInsetsConsumed != that.mStableInsetsConsumed || this.mDisplayCutoutConsumed != that.mDisplayCutoutConsumed || !Arrays.equals(this.mTypeInsetsMap, that.mTypeInsetsMap) || !Arrays.equals(this.mTypeMaxInsetsMap, that.mTypeMaxInsetsMap) || !Arrays.equals(this.mTypeVisibilityMap, that.mTypeVisibilityMap) || !Objects.equals(this.mDisplayCutout, that.mDisplayCutout) || !Objects.equals(this.mRoundedCorners, that.mRoundedCorners) || !Objects.equals(this.mPrivacyIndicatorBounds, that.mPrivacyIndicatorBounds)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(Arrays.hashCode(this.mTypeInsetsMap)), Integer.valueOf(Arrays.hashCode(this.mTypeMaxInsetsMap)), Integer.valueOf(Arrays.hashCode(this.mTypeVisibilityMap)), Boolean.valueOf(this.mIsRound), this.mDisplayCutout, this.mRoundedCorners, Boolean.valueOf(this.mAlwaysConsumeSystemBars), Boolean.valueOf(this.mSystemWindowInsetsConsumed), Boolean.valueOf(this.mStableInsetsConsumed), Boolean.valueOf(this.mDisplayCutoutConsumed), this.mPrivacyIndicatorBounds);
    }

    private static Insets[] insetInsets(Insets[] typeInsetsMap, int left, int top, int right, int bottom) {
        Insets insetInsets;
        boolean cloned = false;
        for (int i = 0; i < 9; i++) {
            Insets insets = typeInsetsMap[i];
            if (!(insets == null || (insetInsets = insetInsets(insets, left, top, right, bottom)) == insets)) {
                if (!cloned) {
                    typeInsetsMap = (Insets[]) typeInsetsMap.clone();
                    cloned = true;
                }
                typeInsetsMap[i] = insetInsets;
            }
        }
        return typeInsetsMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Insets insetInsets(Insets insets, int left, int top, int right, int bottom) {
        int newLeft = Math.max(0, insets.left - left);
        int newTop = Math.max(0, insets.top - top);
        int newRight = Math.max(0, insets.right - right);
        int newBottom = Math.max(0, insets.bottom - bottom);
        if (newLeft == left && newTop == top && newRight == right && newBottom == bottom) {
            return insets;
        }
        return Insets.of(newLeft, newTop, newRight, newBottom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSystemWindowInsetsConsumed() {
        return this.mSystemWindowInsetsConsumed;
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private boolean mAlwaysConsumeSystemBars;
        private DisplayCutout mDisplayCutout;
        private boolean mIsRound;
        private PrivacyIndicatorBounds mPrivacyIndicatorBounds;
        private RoundedCorners mRoundedCorners;
        private boolean mStableInsetsConsumed;
        private boolean mSystemInsetsConsumed;
        private final Insets[] mTypeInsetsMap;
        private final Insets[] mTypeMaxInsetsMap;
        private final boolean[] mTypeVisibilityMap;

        public Builder() {
            this.mSystemInsetsConsumed = true;
            this.mStableInsetsConsumed = true;
            this.mRoundedCorners = RoundedCorners.NO_ROUNDED_CORNERS;
            this.mPrivacyIndicatorBounds = new PrivacyIndicatorBounds();
            this.mTypeInsetsMap = new Insets[9];
            this.mTypeMaxInsetsMap = new Insets[9];
            this.mTypeVisibilityMap = new boolean[9];
        }

        public Builder(WindowInsets insets) {
            this.mSystemInsetsConsumed = true;
            this.mStableInsetsConsumed = true;
            this.mRoundedCorners = RoundedCorners.NO_ROUNDED_CORNERS;
            this.mPrivacyIndicatorBounds = new PrivacyIndicatorBounds();
            this.mTypeInsetsMap = (Insets[]) insets.mTypeInsetsMap.clone();
            this.mTypeMaxInsetsMap = (Insets[]) insets.mTypeMaxInsetsMap.clone();
            this.mTypeVisibilityMap = (boolean[]) insets.mTypeVisibilityMap.clone();
            this.mSystemInsetsConsumed = insets.mSystemWindowInsetsConsumed;
            this.mStableInsetsConsumed = insets.mStableInsetsConsumed;
            this.mDisplayCutout = WindowInsets.displayCutoutCopyConstructorArgument(insets);
            this.mRoundedCorners = insets.mRoundedCorners;
            this.mIsRound = insets.mIsRound;
            this.mAlwaysConsumeSystemBars = insets.mAlwaysConsumeSystemBars;
            this.mPrivacyIndicatorBounds = insets.mPrivacyIndicatorBounds;
        }

        @Deprecated
        public Builder setSystemWindowInsets(Insets systemWindowInsets) {
            Preconditions.checkNotNull(systemWindowInsets);
            WindowInsets.assignCompatInsets(this.mTypeInsetsMap, systemWindowInsets.toRect());
            this.mSystemInsetsConsumed = false;
            return this;
        }

        @Deprecated
        public Builder setSystemGestureInsets(Insets insets) {
            WindowInsets.setInsets(this.mTypeInsetsMap, 16, insets);
            return this;
        }

        @Deprecated
        public Builder setMandatorySystemGestureInsets(Insets insets) {
            WindowInsets.setInsets(this.mTypeInsetsMap, 32, insets);
            return this;
        }

        @Deprecated
        public Builder setTappableElementInsets(Insets insets) {
            WindowInsets.setInsets(this.mTypeInsetsMap, 64, insets);
            return this;
        }

        public Builder setInsets(int typeMask, Insets insets) {
            Preconditions.checkNotNull(insets);
            WindowInsets.setInsets(this.mTypeInsetsMap, typeMask, insets);
            this.mSystemInsetsConsumed = false;
            return this;
        }

        public Builder setInsetsIgnoringVisibility(int typeMask, Insets insets) throws IllegalArgumentException {
            if (typeMask != 8) {
                Preconditions.checkNotNull(insets);
                WindowInsets.setInsets(this.mTypeMaxInsetsMap, typeMask, insets);
                this.mStableInsetsConsumed = false;
                return this;
            }
            throw new IllegalArgumentException("Maximum inset not available for IME");
        }

        public Builder setVisible(int typeMask, boolean visible) {
            for (int i = 1; i <= 256; i <<= 1) {
                if ((typeMask & i) != 0) {
                    this.mTypeVisibilityMap[Type.indexOf(i)] = visible;
                }
            }
            return this;
        }

        @Deprecated
        public Builder setStableInsets(Insets stableInsets) {
            Preconditions.checkNotNull(stableInsets);
            WindowInsets.assignCompatInsets(this.mTypeMaxInsetsMap, stableInsets.toRect());
            this.mStableInsetsConsumed = false;
            return this;
        }

        public Builder setDisplayCutout(DisplayCutout displayCutout) {
            DisplayCutout displayCutout2 = displayCutout != null ? displayCutout : DisplayCutout.NO_CUTOUT;
            this.mDisplayCutout = displayCutout2;
            if (!displayCutout2.isEmpty()) {
                Insets safeInsets = Insets.of(this.mDisplayCutout.getSafeInsets());
                int index = Type.indexOf(128);
                this.mTypeInsetsMap[index] = safeInsets;
                this.mTypeMaxInsetsMap[index] = safeInsets;
                this.mTypeVisibilityMap[index] = true;
            }
            return this;
        }

        public Builder setRoundedCorners(RoundedCorners roundedCorners) {
            this.mRoundedCorners = roundedCorners != null ? roundedCorners : RoundedCorners.NO_ROUNDED_CORNERS;
            return this;
        }

        public Builder setRoundedCorner(int position, RoundedCorner roundedCorner) {
            this.mRoundedCorners.setRoundedCorner(position, roundedCorner);
            return this;
        }

        public Builder setPrivacyIndicatorBounds(PrivacyIndicatorBounds bounds) {
            this.mPrivacyIndicatorBounds = bounds;
            return this;
        }

        public Builder setPrivacyIndicatorBounds(Rect bounds) {
            Rect[] boundsArr = {bounds, bounds, bounds, bounds};
            this.mPrivacyIndicatorBounds = new PrivacyIndicatorBounds(boundsArr, 0);
            return this;
        }

        public Builder setRound(boolean round) {
            this.mIsRound = round;
            return this;
        }

        public Builder setAlwaysConsumeSystemBars(boolean alwaysConsumeSystemBars) {
            this.mAlwaysConsumeSystemBars = alwaysConsumeSystemBars;
            return this;
        }

        public WindowInsets build() {
            return new WindowInsets(this.mSystemInsetsConsumed ? null : this.mTypeInsetsMap, this.mStableInsetsConsumed ? null : this.mTypeMaxInsetsMap, this.mTypeVisibilityMap, this.mIsRound, this.mAlwaysConsumeSystemBars, this.mDisplayCutout, this.mRoundedCorners, this.mPrivacyIndicatorBounds, Type.systemBars(), false);
        }
    }

    /* loaded from: classes3.dex */
    public static final class Type {
        static final int CAPTION_BAR = 4;
        static final int DISPLAY_CUTOUT = 128;
        static final int FIRST = 1;
        static final int IME = 8;
        static final int LAST = 256;
        static final int MANDATORY_SYSTEM_GESTURES = 32;
        static final int NAVIGATION_BARS = 2;
        static final int SIZE = 9;
        static final int STATUS_BARS = 1;
        static final int SYSTEM_GESTURES = 16;
        static final int TAPPABLE_ELEMENT = 64;
        static final int WINDOW_DECOR = 256;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface InsetsType {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int indexOf(int type) {
            switch (type) {
                case 1:
                    return 0;
                case 2:
                    return 1;
                case 4:
                    return 2;
                case 8:
                    return 3;
                case 16:
                    return 4;
                case 32:
                    return 5;
                case 64:
                    return 6;
                case 128:
                    return 7;
                case 256:
                    return 8;
                default:
                    throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + type);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static String toString(int types) {
            StringBuilder result = new StringBuilder();
            if ((types & 1) != 0) {
                result.append("statusBars |");
            }
            if ((types & 2) != 0) {
                result.append("navigationBars |");
            }
            if ((types & 4) != 0) {
                result.append("captionBar |");
            }
            if ((types & 8) != 0) {
                result.append("ime |");
            }
            if ((types & 16) != 0) {
                result.append("systemGestures |");
            }
            if ((types & 32) != 0) {
                result.append("mandatorySystemGestures |");
            }
            if ((types & 64) != 0) {
                result.append("tappableElement |");
            }
            if ((types & 128) != 0) {
                result.append("displayCutout |");
            }
            if ((types & 256) != 0) {
                result.append("windowDecor |");
            }
            if (result.length() > 0) {
                result.delete(result.length() - 2, result.length());
            }
            return result.toString();
        }

        private Type() {
        }

        public static int statusBars() {
            return 1;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int captionBar() {
            return 4;
        }

        public static int ime() {
            return 8;
        }

        public static int systemGestures() {
            return 16;
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int tappableElement() {
            return 64;
        }

        public static int displayCutout() {
            return 128;
        }

        public static int systemBars() {
            return 7;
        }

        public static int all() {
            return -1;
        }

        public static boolean isVisibleInsetsType(int type, int softInputModeFlags) {
            int softInputMode = softInputModeFlags & 240;
            return ((systemBars() & type) == 0 && (softInputMode == 48 || (ime() & type) == 0)) ? false : true;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Side {
        public static final int BOTTOM = 8;
        public static final int LEFT = 1;
        public static final int RIGHT = 4;
        public static final int TOP = 2;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface InsetsSide {
        }

        private Side() {
        }

        public static int all() {
            return 15;
        }
    }
}
