package android.view;

import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextUtils;
import android.util.Log;
import android.util.PathParser;
import java.util.Objects;
/* loaded from: classes3.dex */
public class CutoutSpecification {
    private static final String BIND_LEFT_CUTOUT_MARKER = "@bind_left_cutout";
    private static final String BIND_RIGHT_CUTOUT_MARKER = "@bind_right_cutout";
    private static final String BOTTOM_MARKER = "@bottom";
    private static final String CENTER_VERTICAL_MARKER = "@center_vertical";
    private static final String CUTOUT_MARKER = "@cutout";
    private static final boolean DEBUG = false;
    private static final String DP_MARKER = "@dp";
    private static final String LEFT_MARKER = "@left";
    private static final char MARKER_START_CHAR = '@';
    private static final int MINIMAL_ACCEPTABLE_PATH_LENGTH = "H1V1Z".length();
    private static final String RIGHT_MARKER = "@right";
    private static final String TAG = "CutoutSpecification";
    private final Rect mBottomBound;
    private final Insets mInsets;
    private final Rect mLeftBound;
    private final Path mPath;
    private final Rect mRightBound;
    private final Rect mTopBound;

    private CutoutSpecification(Parser parser) {
        this.mPath = parser.mPath;
        this.mLeftBound = parser.mLeftBound;
        this.mTopBound = parser.mTopBound;
        this.mRightBound = parser.mRightBound;
        this.mBottomBound = parser.mBottomBound;
        this.mInsets = parser.mInsets;
    }

    public Path getPath() {
        return this.mPath;
    }

    public Rect getLeftBound() {
        return this.mLeftBound;
    }

    public Rect getTopBound() {
        return this.mTopBound;
    }

    public Rect getRightBound() {
        return this.mRightBound;
    }

    public Rect getBottomBound() {
        return this.mBottomBound;
    }

    public Rect getSafeInset() {
        return this.mInsets.toRect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int decideWhichEdge(boolean isTopEdgeShortEdge, boolean isShortEdge, boolean isStart) {
        return isTopEdgeShortEdge ? isShortEdge ? isStart ? 48 : 80 : isStart ? 3 : 5 : isShortEdge ? isStart ? 3 : 5 : isStart ? 48 : 80;
    }

    /* loaded from: classes3.dex */
    public static class Parser {
        private Rect mBottomBound;
        private final float mDensity;
        private final int mDisplayHeight;
        private final int mDisplayWidth;
        private boolean mInDp;
        private Insets mInsets;
        private boolean mIsCloserToStartSide;
        private final boolean mIsShortEdgeOnTop;
        private boolean mIsTouchShortEdgeEnd;
        private boolean mIsTouchShortEdgeStart;
        private Rect mLeftBound;
        private Path mPath;
        private Rect mRightBound;
        private int mSafeInsetBottom;
        private int mSafeInsetLeft;
        private int mSafeInsetRight;
        private int mSafeInsetTop;
        private Rect mTopBound;
        private final Rect mTmpRect = new Rect();
        private final RectF mTmpRectF = new RectF();
        private boolean mPositionFromLeft = false;
        private boolean mPositionFromRight = false;
        private boolean mPositionFromBottom = false;
        private boolean mPositionFromCenterVertical = false;
        private boolean mBindLeftCutout = false;
        private boolean mBindRightCutout = false;
        private boolean mBindBottomCutout = false;
        private final Matrix mMatrix = new Matrix();

        public Parser(float density, int displayWidth, int displayHeight) {
            boolean z = false;
            this.mDensity = density;
            this.mDisplayWidth = displayWidth;
            this.mDisplayHeight = displayHeight;
            this.mIsShortEdgeOnTop = displayWidth < displayHeight ? true : z;
        }

        private void computeBoundsRectAndAddToRegion(Path p, Region inoutRegion, Rect inoutRect) {
            this.mTmpRectF.setEmpty();
            p.computeBounds(this.mTmpRectF, false);
            this.mTmpRectF.round(inoutRect);
            inoutRegion.op(inoutRect, Region.Op.UNION);
        }

        private void resetStatus(StringBuilder sb) {
            sb.setLength(0);
            this.mPositionFromBottom = false;
            this.mPositionFromLeft = false;
            this.mPositionFromRight = false;
            this.mPositionFromCenterVertical = false;
            this.mBindLeftCutout = false;
            this.mBindRightCutout = false;
            this.mBindBottomCutout = false;
        }

        private void translateMatrix() {
            float offsetX;
            float offsetY;
            if (this.mPositionFromRight) {
                offsetX = this.mDisplayWidth;
            } else if (this.mPositionFromLeft) {
                offsetX = 0.0f;
            } else {
                offsetX = this.mDisplayWidth / 2.0f;
            }
            if (this.mPositionFromBottom) {
                offsetY = this.mDisplayHeight;
            } else if (this.mPositionFromCenterVertical) {
                offsetY = this.mDisplayHeight / 2.0f;
            } else {
                offsetY = 0.0f;
            }
            this.mMatrix.reset();
            if (this.mInDp) {
                Matrix matrix = this.mMatrix;
                float f = this.mDensity;
                matrix.postScale(f, f);
            }
            this.mMatrix.postTranslate(offsetX, offsetY);
        }

        private int computeSafeInsets(int gravity, Rect rect) {
            if (gravity == 3 && rect.right > 0 && rect.right < this.mDisplayWidth) {
                return rect.right;
            }
            if (gravity == 48 && rect.bottom > 0 && rect.bottom < this.mDisplayHeight) {
                return rect.bottom;
            }
            if (gravity == 5 && rect.left > 0) {
                int i = rect.left;
                int i2 = this.mDisplayWidth;
                if (i < i2) {
                    return i2 - rect.left;
                }
            }
            if (gravity != 80 || rect.top <= 0) {
                return 0;
            }
            int i3 = rect.top;
            int i4 = this.mDisplayHeight;
            if (i3 < i4) {
                return i4 - rect.top;
            }
            return 0;
        }

        private void setSafeInset(int gravity, int inset) {
            if (gravity == 3) {
                this.mSafeInsetLeft = inset;
            } else if (gravity == 48) {
                this.mSafeInsetTop = inset;
            } else if (gravity == 5) {
                this.mSafeInsetRight = inset;
            } else if (gravity == 80) {
                this.mSafeInsetBottom = inset;
            }
        }

        private int getSafeInset(int gravity) {
            if (gravity == 3) {
                return this.mSafeInsetLeft;
            }
            if (gravity == 48) {
                return this.mSafeInsetTop;
            }
            if (gravity == 5) {
                return this.mSafeInsetRight;
            }
            if (gravity == 80) {
                return this.mSafeInsetBottom;
            }
            return 0;
        }

        private Rect onSetEdgeCutout(boolean isStart, boolean isShortEdge, Rect rect) {
            int gravity;
            if (isShortEdge) {
                gravity = CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, true, isStart);
            } else {
                boolean z = this.mIsTouchShortEdgeStart;
                gravity = (!z || !this.mIsTouchShortEdgeEnd) ? (z || this.mIsTouchShortEdgeEnd) ? CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, true, this.mIsCloserToStartSide) : CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, isShortEdge, isStart) : CutoutSpecification.decideWhichEdge(this.mIsShortEdgeOnTop, false, isStart);
            }
            int oldSafeInset = getSafeInset(gravity);
            int newSafeInset = computeSafeInsets(gravity, rect);
            if (oldSafeInset < newSafeInset) {
                setSafeInset(gravity, newSafeInset);
            }
            return new Rect(rect);
        }

        private void setEdgeCutout(Path newPath) {
            boolean z = this.mBindRightCutout;
            if (!z || this.mRightBound != null) {
                boolean z2 = this.mBindLeftCutout;
                if (!z2 || this.mLeftBound != null) {
                    boolean z3 = this.mBindBottomCutout;
                    if (z3 && this.mBottomBound == null) {
                        this.mBottomBound = onSetEdgeCutout(false, this.mIsShortEdgeOnTop, this.mTmpRect);
                    } else if (!z3 && !z2 && !z && this.mTopBound == null) {
                        this.mTopBound = onSetEdgeCutout(true, this.mIsShortEdgeOnTop, this.mTmpRect);
                    } else {
                        return;
                    }
                } else {
                    this.mLeftBound = onSetEdgeCutout(true, !this.mIsShortEdgeOnTop, this.mTmpRect);
                }
            } else {
                this.mRightBound = onSetEdgeCutout(false, !this.mIsShortEdgeOnTop, this.mTmpRect);
            }
            Path path = this.mPath;
            if (path != null) {
                path.addPath(newPath);
            } else {
                this.mPath = newPath;
            }
        }

        private void parseSvgPathSpec(Region region, String spec) {
            if (TextUtils.length(spec) < CutoutSpecification.MINIMAL_ACCEPTABLE_PATH_LENGTH) {
                Log.e(CutoutSpecification.TAG, "According to SVG definition, it shouldn't happen");
                return;
            }
            spec.trim();
            translateMatrix();
            Path newPath = PathParser.createPathFromPathData(spec);
            newPath.transform(this.mMatrix);
            computeBoundsRectAndAddToRegion(newPath, region, this.mTmpRect);
            if (!this.mTmpRect.isEmpty()) {
                boolean z = true;
                if (this.mIsShortEdgeOnTop) {
                    this.mIsTouchShortEdgeStart = this.mTmpRect.top <= 0;
                    this.mIsTouchShortEdgeEnd = this.mTmpRect.bottom >= this.mDisplayHeight;
                    if (this.mTmpRect.centerY() >= this.mDisplayHeight / 2) {
                        z = false;
                    }
                    this.mIsCloserToStartSide = z;
                } else {
                    this.mIsTouchShortEdgeStart = this.mTmpRect.left <= 0;
                    this.mIsTouchShortEdgeEnd = this.mTmpRect.right >= this.mDisplayWidth;
                    if (this.mTmpRect.centerX() >= this.mDisplayWidth / 2) {
                        z = false;
                    }
                    this.mIsCloserToStartSide = z;
                }
                setEdgeCutout(newPath);
            }
        }

        private void parseSpecWithoutDp(String specWithoutDp) {
            int currentIndex;
            Region region = Region.obtain();
            StringBuilder sb = null;
            int lastIndex = 0;
            while (true) {
                int currentIndex2 = specWithoutDp.indexOf(64, lastIndex);
                if (currentIndex2 == -1) {
                    break;
                }
                if (sb == null) {
                    sb = new StringBuilder(specWithoutDp.length());
                }
                sb.append((CharSequence) specWithoutDp, lastIndex, currentIndex2);
                if (specWithoutDp.startsWith(CutoutSpecification.LEFT_MARKER, currentIndex2)) {
                    if (!this.mPositionFromRight) {
                        this.mPositionFromLeft = true;
                    }
                    currentIndex = currentIndex2 + CutoutSpecification.LEFT_MARKER.length();
                } else if (specWithoutDp.startsWith(CutoutSpecification.RIGHT_MARKER, currentIndex2)) {
                    if (!this.mPositionFromLeft) {
                        this.mPositionFromRight = true;
                    }
                    currentIndex = currentIndex2 + CutoutSpecification.RIGHT_MARKER.length();
                } else if (specWithoutDp.startsWith(CutoutSpecification.BOTTOM_MARKER, currentIndex2)) {
                    parseSvgPathSpec(region, sb.toString());
                    currentIndex = currentIndex2 + CutoutSpecification.BOTTOM_MARKER.length();
                    resetStatus(sb);
                    this.mBindBottomCutout = true;
                    this.mPositionFromBottom = true;
                } else if (specWithoutDp.startsWith(CutoutSpecification.CENTER_VERTICAL_MARKER, currentIndex2)) {
                    parseSvgPathSpec(region, sb.toString());
                    currentIndex = currentIndex2 + CutoutSpecification.CENTER_VERTICAL_MARKER.length();
                    resetStatus(sb);
                    this.mPositionFromCenterVertical = true;
                } else if (specWithoutDp.startsWith(CutoutSpecification.CUTOUT_MARKER, currentIndex2)) {
                    parseSvgPathSpec(region, sb.toString());
                    currentIndex = currentIndex2 + CutoutSpecification.CUTOUT_MARKER.length();
                    resetStatus(sb);
                } else if (specWithoutDp.startsWith(CutoutSpecification.BIND_LEFT_CUTOUT_MARKER, currentIndex2)) {
                    this.mBindBottomCutout = false;
                    this.mBindRightCutout = false;
                    this.mBindLeftCutout = true;
                    currentIndex = currentIndex2 + CutoutSpecification.BIND_LEFT_CUTOUT_MARKER.length();
                } else if (specWithoutDp.startsWith(CutoutSpecification.BIND_RIGHT_CUTOUT_MARKER, currentIndex2)) {
                    this.mBindBottomCutout = false;
                    this.mBindLeftCutout = false;
                    this.mBindRightCutout = true;
                    currentIndex = currentIndex2 + CutoutSpecification.BIND_RIGHT_CUTOUT_MARKER.length();
                } else {
                    currentIndex = currentIndex2 + 1;
                }
                lastIndex = currentIndex;
            }
            if (sb == null) {
                parseSvgPathSpec(region, specWithoutDp);
            } else {
                sb.append((CharSequence) specWithoutDp, lastIndex, specWithoutDp.length());
                parseSvgPathSpec(region, sb.toString());
            }
            region.recycle();
        }

        public CutoutSpecification parse(String originalSpec) {
            String spec;
            Objects.requireNonNull(originalSpec);
            int dpIndex = originalSpec.lastIndexOf(CutoutSpecification.DP_MARKER);
            this.mInDp = dpIndex != -1;
            if (dpIndex != -1) {
                spec = originalSpec.substring(0, dpIndex) + originalSpec.substring(CutoutSpecification.DP_MARKER.length() + dpIndex);
            } else {
                spec = originalSpec;
            }
            parseSpecWithoutDp(spec);
            this.mInsets = Insets.of(this.mSafeInsetLeft, this.mSafeInsetTop, this.mSafeInsetRight, this.mSafeInsetBottom);
            return new CutoutSpecification(this);
        }
    }
}
