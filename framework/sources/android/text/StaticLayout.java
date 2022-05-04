package android.text;

import android.graphics.Paint;
import android.text.Layout;
import android.text.TextUtils;
import android.text.style.LineHeightSpan;
import android.util.Log;
import android.util.Pools;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes3.dex */
public class StaticLayout extends Layout {
    private static final char CHAR_NEW_LINE = '\n';
    private static final int COLUMNS_ELLIPSIZE = 7;
    private static final int COLUMNS_NORMAL = 5;
    private static final int DEFAULT_MAX_LINE_HEIGHT = -1;
    private static final int DESCENT = 2;
    private static final int DIR = 0;
    private static final int DIR_SHIFT = 30;
    private static final int ELLIPSIS_COUNT = 6;
    private static final int ELLIPSIS_START = 5;
    private static final int END_HYPHEN_MASK = 7;
    private static final int EXTRA = 3;
    private static final double EXTRA_ROUNDING = 0.5d;
    private static final int HYPHEN = 4;
    private static final int HYPHEN_MASK = 255;
    private static final int START = 0;
    private static final int START_HYPHEN_BITS_SHIFT = 3;
    private static final int START_HYPHEN_MASK = 24;
    private static final int START_MASK = 536870911;
    private static final int TAB = 0;
    private static final float TAB_INCREMENT = 20.0f;
    private static final int TAB_MASK = 536870912;
    static final String TAG = "StaticLayout";
    private static final int TOP = 1;
    private int mBottomPadding;
    private int mColumns;
    private boolean mEllipsized;
    private int mEllipsizedWidth;
    private int[] mLeftIndents;
    private int mLineCount;
    private Layout.Directions[] mLineDirections;
    private int[] mLines;
    private int mMaxLineHeight;
    private int mMaximumVisibleLineCount;
    private int[] mRightIndents;
    public IStaticLayoutExt mStaticLayoutExt;
    private int mTopPadding;

    /* loaded from: classes3.dex */
    public static final class Builder {
        private static final Pools.SynchronizedPool<Builder> sPool = new Pools.SynchronizedPool<>(3);
        private boolean mAddLastLineLineSpacing;
        private Layout.Alignment mAlignment;
        private int mBreakStrategy;
        private TextUtils.TruncateAt mEllipsize;
        private int mEllipsizedWidth;
        private int mEnd;
        private boolean mFallbackLineSpacing;
        private final Paint.FontMetricsInt mFontMetricsInt = new Paint.FontMetricsInt();
        private int mHyphenationFrequency;
        private boolean mIncludePad;
        private int mJustificationMode;
        private int[] mLeftIndents;
        private int mMaxLines;
        private TextPaint mPaint;
        private int[] mRightIndents;
        private float mSpacingAdd;
        private float mSpacingMult;
        private int mStart;
        private CharSequence mText;
        private TextDirectionHeuristic mTextDir;
        private int mWidth;

        private Builder() {
        }

        public static Builder obtain(CharSequence source, int start, int end, TextPaint paint, int width) {
            Builder b = sPool.acquire();
            if (b == null) {
                b = new Builder();
            }
            b.mText = source;
            b.mStart = start;
            b.mEnd = end;
            b.mPaint = paint;
            b.mWidth = width;
            b.mAlignment = Layout.Alignment.ALIGN_NORMAL;
            b.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            b.mSpacingMult = 1.0f;
            b.mSpacingAdd = 0.0f;
            b.mIncludePad = true;
            b.mFallbackLineSpacing = false;
            b.mEllipsizedWidth = width;
            b.mEllipsize = null;
            b.mMaxLines = Integer.MAX_VALUE;
            b.mBreakStrategy = 0;
            b.mHyphenationFrequency = 0;
            b.mJustificationMode = 0;
            return b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void recycle(Builder b) {
            b.mPaint = null;
            b.mText = null;
            b.mLeftIndents = null;
            b.mRightIndents = null;
            sPool.release(b);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void finish() {
            this.mText = null;
            this.mPaint = null;
            this.mLeftIndents = null;
            this.mRightIndents = null;
        }

        public Builder setText(CharSequence source) {
            return setText(source, 0, source.length());
        }

        public Builder setText(CharSequence source, int start, int end) {
            this.mText = source;
            this.mStart = start;
            this.mEnd = end;
            return this;
        }

        public Builder setPaint(TextPaint paint) {
            this.mPaint = paint;
            return this;
        }

        public Builder setWidth(int width) {
            this.mWidth = width;
            if (this.mEllipsize == null) {
                this.mEllipsizedWidth = width;
            }
            return this;
        }

        public Builder setAlignment(Layout.Alignment alignment) {
            this.mAlignment = alignment;
            return this;
        }

        public Builder setTextDirection(TextDirectionHeuristic textDir) {
            this.mTextDir = textDir;
            return this;
        }

        public Builder setLineSpacing(float spacingAdd, float spacingMult) {
            this.mSpacingAdd = spacingAdd;
            this.mSpacingMult = spacingMult;
            return this;
        }

        public Builder setIncludePad(boolean includePad) {
            this.mIncludePad = includePad;
            return this;
        }

        public Builder setUseLineSpacingFromFallbacks(boolean useLineSpacingFromFallbacks) {
            this.mFallbackLineSpacing = useLineSpacingFromFallbacks;
            return this;
        }

        public Builder setEllipsizedWidth(int ellipsizedWidth) {
            this.mEllipsizedWidth = ellipsizedWidth;
            return this;
        }

        public Builder setEllipsize(TextUtils.TruncateAt ellipsize) {
            this.mEllipsize = ellipsize;
            return this;
        }

        public Builder setMaxLines(int maxLines) {
            this.mMaxLines = maxLines;
            return this;
        }

        public Builder setBreakStrategy(int breakStrategy) {
            this.mBreakStrategy = breakStrategy;
            return this;
        }

        public Builder setHyphenationFrequency(int hyphenationFrequency) {
            this.mHyphenationFrequency = hyphenationFrequency;
            return this;
        }

        public Builder setIndents(int[] leftIndents, int[] rightIndents) {
            this.mLeftIndents = leftIndents;
            this.mRightIndents = rightIndents;
            return this;
        }

        public Builder setJustificationMode(int justificationMode) {
            this.mJustificationMode = justificationMode;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setAddLastLineLineSpacing(boolean value) {
            this.mAddLastLineLineSpacing = value;
            return this;
        }

        public StaticLayout build() {
            StaticLayout result = new StaticLayout(this);
            recycle(this);
            return result;
        }
    }

    @Deprecated
    public StaticLayout(CharSequence source, TextPaint paint, int width, Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad) {
        this(source, 0, source.length(), paint, width, align, spacingmult, spacingadd, includepad);
    }

    @Deprecated
    public StaticLayout(CharSequence source, int bufstart, int bufend, TextPaint paint, int outerwidth, Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad) {
        this(source, bufstart, bufend, paint, outerwidth, align, spacingmult, spacingadd, includepad, null, 0);
    }

    @Deprecated
    public StaticLayout(CharSequence source, int bufstart, int bufend, TextPaint paint, int outerwidth, Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad, TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
        this(source, bufstart, bufend, paint, outerwidth, align, TextDirectionHeuristics.FIRSTSTRONG_LTR, spacingmult, spacingadd, includepad, ellipsize, ellipsizedWidth, Integer.MAX_VALUE);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public StaticLayout(java.lang.CharSequence r15, int r16, int r17, android.text.TextPaint r18, int r19, android.text.Layout.Alignment r20, android.text.TextDirectionHeuristic r21, float r22, float r23, boolean r24, android.text.TextUtils.TruncateAt r25, int r26, int r27) {
        /*
            r14 = this;
            r8 = r14
            r9 = r15
            r10 = r25
            r11 = r26
            r12 = r27
            if (r10 != 0) goto L_0x000c
            r1 = r9
            goto L_0x001d
        L_0x000c:
            boolean r0 = r9 instanceof android.text.Spanned
            if (r0 == 0) goto L_0x0017
            android.text.Layout$SpannedEllipsizer r0 = new android.text.Layout$SpannedEllipsizer
            r0.<init>(r15)
            r1 = r0
            goto L_0x001d
        L_0x0017:
            android.text.Layout$Ellipsizer r0 = new android.text.Layout$Ellipsizer
            r0.<init>(r15)
            r1 = r0
        L_0x001d:
            r0 = r14
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = r22
            r7 = r23
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            java.lang.Class<android.text.IStaticLayoutExt> r0 = android.text.IStaticLayoutExt.class
            system.ext.loader.core.ExtLoader$ExtBuilder r0 = system.ext.loader.core.ExtLoader.type(r0)
            system.ext.loader.core.ExtLoader$ExtBuilder r0 = r0.base(r14)
            java.lang.Object r0 = r0.create()
            android.text.IStaticLayoutExt r0 = (android.text.IStaticLayoutExt) r0
            r8.mStaticLayoutExt = r0
            r0 = -1
            r8.mMaxLineHeight = r0
            r0 = 2147483647(0x7fffffff, float:NaN)
            r8.mMaximumVisibleLineCount = r0
            android.text.StaticLayout$Builder r0 = android.text.StaticLayout.Builder.obtain(r15, r16, r17, r18, r19)
            r1 = r20
            android.text.StaticLayout$Builder r0 = r0.setAlignment(r1)
            r2 = r21
            android.text.StaticLayout$Builder r0 = r0.setTextDirection(r2)
            r3 = r22
            r4 = r23
            android.text.StaticLayout$Builder r0 = r0.setLineSpacing(r4, r3)
            r5 = r24
            android.text.StaticLayout$Builder r0 = r0.setIncludePad(r5)
            android.text.StaticLayout$Builder r0 = r0.setEllipsizedWidth(r11)
            android.text.StaticLayout$Builder r0 = r0.setEllipsize(r10)
            android.text.StaticLayout$Builder r0 = r0.setMaxLines(r12)
            if (r10 == 0) goto L_0x0087
            java.lang.CharSequence r6 = r14.getText()
            android.text.Layout$Ellipsizer r6 = (android.text.Layout.Ellipsizer) r6
            r6.mLayout = r8
            r6.mWidth = r11
            r6.mMethod = r10
            r8.mEllipsizedWidth = r11
            r7 = 7
            r8.mColumns = r7
            r6 = r19
            goto L_0x008e
        L_0x0087:
            r6 = 5
            r8.mColumns = r6
            r6 = r19
            r8.mEllipsizedWidth = r6
        L_0x008e:
            java.lang.Class<android.text.Layout$Directions> r7 = android.text.Layout.Directions.class
            r13 = 2
            java.lang.Object[] r7 = com.android.internal.util.ArrayUtils.newUnpaddedArray(r7, r13)
            android.text.Layout$Directions[] r7 = (android.text.Layout.Directions[]) r7
            r8.mLineDirections = r7
            int r7 = r8.mColumns
            int r7 = r7 * r13
            int[] r7 = com.android.internal.util.ArrayUtils.newUnpaddedIntArray(r7)
            r8.mLines = r7
            r8.mMaximumVisibleLineCount = r12
            boolean r7 = android.text.StaticLayout.Builder.access$100(r0)
            boolean r13 = android.text.StaticLayout.Builder.access$100(r0)
            r14.generate(r0, r7, r13)
            android.text.StaticLayout.Builder.access$200(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.StaticLayout.<init>(java.lang.CharSequence, int, int, android.text.TextPaint, int, android.text.Layout$Alignment, android.text.TextDirectionHeuristic, float, float, boolean, android.text.TextUtils$TruncateAt, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StaticLayout(CharSequence text) {
        super(text, null, 0, null, 0.0f, 0.0f);
        this.mStaticLayoutExt = (IStaticLayoutExt) ExtLoader.type(IStaticLayoutExt.class).base(this).create();
        this.mMaxLineHeight = -1;
        this.mMaximumVisibleLineCount = Integer.MAX_VALUE;
        this.mColumns = 7;
        this.mLineDirections = (Layout.Directions[]) ArrayUtils.newUnpaddedArray(Layout.Directions.class, 2);
        this.mLines = ArrayUtils.newUnpaddedIntArray(this.mColumns * 2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private StaticLayout(android.text.StaticLayout.Builder r11) {
        /*
            r10 = this;
            android.text.TextUtils$TruncateAt r0 = android.text.StaticLayout.Builder.access$300(r11)
            if (r0 != 0) goto L_0x000c
            java.lang.CharSequence r0 = android.text.StaticLayout.Builder.access$400(r11)
            r3 = r0
            goto L_0x0029
        L_0x000c:
            java.lang.CharSequence r0 = android.text.StaticLayout.Builder.access$400(r11)
            boolean r0 = r0 instanceof android.text.Spanned
            if (r0 == 0) goto L_0x001f
            android.text.Layout$SpannedEllipsizer r0 = new android.text.Layout$SpannedEllipsizer
            java.lang.CharSequence r1 = android.text.StaticLayout.Builder.access$400(r11)
            r0.<init>(r1)
            r3 = r0
            goto L_0x0029
        L_0x001f:
            android.text.Layout$Ellipsizer r0 = new android.text.Layout$Ellipsizer
            java.lang.CharSequence r1 = android.text.StaticLayout.Builder.access$400(r11)
            r0.<init>(r1)
            r3 = r0
        L_0x0029:
            android.text.TextPaint r4 = android.text.StaticLayout.Builder.access$500(r11)
            int r5 = android.text.StaticLayout.Builder.access$600(r11)
            android.text.Layout$Alignment r6 = android.text.StaticLayout.Builder.access$700(r11)
            android.text.TextDirectionHeuristic r7 = android.text.StaticLayout.Builder.access$800(r11)
            float r8 = android.text.StaticLayout.Builder.access$900(r11)
            float r9 = android.text.StaticLayout.Builder.access$1000(r11)
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            java.lang.Class<android.text.IStaticLayoutExt> r0 = android.text.IStaticLayoutExt.class
            system.ext.loader.core.ExtLoader$ExtBuilder r0 = system.ext.loader.core.ExtLoader.type(r0)
            system.ext.loader.core.ExtLoader$ExtBuilder r0 = r0.base(r10)
            java.lang.Object r0 = r0.create()
            android.text.IStaticLayoutExt r0 = (android.text.IStaticLayoutExt) r0
            r10.mStaticLayoutExt = r0
            r1 = -1
            r10.mMaxLineHeight = r1
            r1 = 2147483647(0x7fffffff, float:NaN)
            r10.mMaximumVisibleLineCount = r1
            r0.setBuilderToTextJustificationHooks()
            android.text.TextUtils$TruncateAt r0 = android.text.StaticLayout.Builder.access$300(r11)
            if (r0 == 0) goto L_0x0086
            java.lang.CharSequence r0 = r10.getText()
            android.text.Layout$Ellipsizer r0 = (android.text.Layout.Ellipsizer) r0
            r0.mLayout = r10
            int r1 = android.text.StaticLayout.Builder.access$1100(r11)
            r0.mWidth = r1
            android.text.TextUtils$TruncateAt r1 = android.text.StaticLayout.Builder.access$300(r11)
            r0.mMethod = r1
            int r1 = android.text.StaticLayout.Builder.access$1100(r11)
            r10.mEllipsizedWidth = r1
            r1 = 7
            r10.mColumns = r1
            goto L_0x008f
        L_0x0086:
            r0 = 5
            r10.mColumns = r0
            int r0 = android.text.StaticLayout.Builder.access$600(r11)
            r10.mEllipsizedWidth = r0
        L_0x008f:
            java.lang.Class<android.text.Layout$Directions> r0 = android.text.Layout.Directions.class
            r1 = 2
            java.lang.Object[] r0 = com.android.internal.util.ArrayUtils.newUnpaddedArray(r0, r1)
            android.text.Layout$Directions[] r0 = (android.text.Layout.Directions[]) r0
            r10.mLineDirections = r0
            int r0 = r10.mColumns
            int r0 = r0 * r1
            int[] r0 = com.android.internal.util.ArrayUtils.newUnpaddedIntArray(r0)
            r10.mLines = r0
            int r0 = android.text.StaticLayout.Builder.access$1200(r11)
            r10.mMaximumVisibleLineCount = r0
            int[] r0 = android.text.StaticLayout.Builder.access$1300(r11)
            r10.mLeftIndents = r0
            int[] r0 = android.text.StaticLayout.Builder.access$1400(r11)
            r10.mRightIndents = r0
            int r0 = android.text.StaticLayout.Builder.access$1500(r11)
            r10.setJustificationMode(r0)
            boolean r0 = android.text.StaticLayout.Builder.access$100(r11)
            boolean r1 = android.text.StaticLayout.Builder.access$100(r11)
            r10.generate(r11, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.StaticLayout.<init>(android.text.StaticLayout$Builder):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x032d, code lost:
        if (r5 != android.text.TextUtils.TruncateAt.MARQUEE) goto L_0x0332;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x033d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x060b A[LOOP:2: B:48:0x017a->B:178:0x060b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x05f7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02e0 A[LOOP:6: B:96:0x02de->B:97:0x02e0, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void generate(android.text.StaticLayout.Builder r91, boolean r92, boolean r93) {
        /*
            Method dump skipped, instructions count: 1750
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.StaticLayout.generate(android.text.StaticLayout$Builder, boolean, boolean):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int out(CharSequence text, int start, int end, int above, int below, int top, int bottom, int v, float spacingmult, float spacingadd, LineHeightSpan[] chooseHt, int[] chooseHtv, Paint.FontMetricsInt fm, boolean hasTab, int hyphenEdit, boolean needMultiply, MeasuredParagraph measured, int bufEnd, boolean includePad, boolean trackPad, boolean addLastLineLineSpacing, char[] chs, int widthStart, TextUtils.TruncateAt ellipsize, float ellipsisWidth, float textWidth, TextPaint paint, boolean moreChars) {
        int[] lines;
        int bottom2;
        int top2;
        int below2;
        int above2;
        int j;
        int i;
        int i2;
        int i3;
        boolean firstLine;
        int i4;
        int i5;
        boolean lastCharIsNewLine;
        int extra;
        int i6;
        int j2;
        int want;
        boolean z;
        int j3 = this.mLineCount;
        int i7 = this.mColumns;
        int off = j3 * i7;
        int i8 = 1;
        int want2 = off + i7 + 1;
        int[] lines2 = this.mLines;
        int dir = measured.getParagraphDir();
        if (want2 >= lines2.length) {
            int[] grow = ArrayUtils.newUnpaddedIntArray(GrowingArrayUtils.growSize(want2));
            System.arraycopy(lines2, 0, grow, 0, lines2.length);
            this.mLines = grow;
            lines = grow;
        } else {
            lines = lines2;
        }
        if (j3 >= this.mLineDirections.length) {
            Layout.Directions[] grow2 = (Layout.Directions[]) ArrayUtils.newUnpaddedArray(Layout.Directions.class, GrowingArrayUtils.growSize(j3));
            Layout.Directions[] directionsArr = this.mLineDirections;
            System.arraycopy(directionsArr, 0, grow2, 0, directionsArr.length);
            this.mLineDirections = grow2;
        }
        if (chooseHt != null) {
            fm.ascent = above;
            fm.descent = below;
            fm.top = top;
            fm.bottom = bottom;
            int i9 = 0;
            while (i9 < chooseHt.length) {
                if (chooseHt[i9] instanceof LineHeightSpan.WithDensity) {
                    z = false;
                    want = want2;
                    i8 = i8;
                    j2 = j3;
                    ((LineHeightSpan.WithDensity) chooseHt[i9]).chooseHeight(text, start, end, chooseHtv[i9], v, fm, paint);
                } else {
                    want = want2;
                    i8 = i8;
                    j2 = j3;
                    z = false;
                    chooseHt[i9].chooseHeight(text, start, end, chooseHtv[i9], v, fm);
                }
                i9++;
                want2 = want;
                j3 = j2;
            }
            i2 = i8;
            j = j3;
            i = 0;
            above2 = fm.ascent;
            below2 = fm.descent;
            top2 = fm.top;
            bottom2 = fm.bottom;
        } else {
            i = 0;
            i2 = 1;
            j = j3;
            above2 = above;
            below2 = below;
            top2 = top;
            bottom2 = bottom;
        }
        int i10 = j == 0 ? i2 : i;
        int i11 = j + 1;
        int i12 = this.mMaximumVisibleLineCount;
        int i13 = i11 == i12 ? i2 : i;
        if (ellipsize != null) {
            boolean forceEllipsis = (!moreChars || this.mLineCount + i2 != i12) ? i : i2;
            if ((((((i12 != i2 || !moreChars) && (i10 == 0 || moreChars)) || ellipsize == TextUtils.TruncateAt.MARQUEE) && (i10 != 0 || ((i13 == 0 && moreChars) || ellipsize != TextUtils.TruncateAt.END))) ? i : i2) != 0) {
                i4 = widthStart;
                firstLine = i10;
                i3 = bufEnd;
                calculateEllipsis(start, end, measured, widthStart, ellipsisWidth, ellipsize, j, textWidth, paint, forceEllipsis);
            } else {
                i4 = widthStart;
                firstLine = i10;
                i3 = bufEnd;
                int[] iArr = this.mLines;
                int i14 = this.mColumns;
                iArr[(i14 * j) + 5] = i;
                iArr[(i14 * j) + 6] = i;
            }
        } else {
            i4 = widthStart;
            firstLine = i10;
            i3 = bufEnd;
        }
        if (this.mEllipsized) {
            lastCharIsNewLine = true;
            i5 = start;
        } else {
            if (i4 != i3 && i3 > 0) {
                if (text.charAt(i3 - 1) == '\n') {
                    i6 = 1;
                    if (end == i3 || i6 != 0) {
                        i5 = start;
                        if (i5 == i3 || i6 == 0) {
                            lastCharIsNewLine = false;
                        } else {
                            lastCharIsNewLine = true;
                        }
                    } else {
                        lastCharIsNewLine = true;
                        i5 = start;
                    }
                }
            }
            i6 = i;
            if (end == i3) {
            }
            i5 = start;
            if (i5 == i3) {
            }
            lastCharIsNewLine = false;
        }
        if (this.mStaticLayoutExt.lineShouldIncludeFontPad(firstLine, this)) {
            if (trackPad) {
                this.mTopPadding = top2 - above2;
            }
            if (includePad) {
                above2 = top2;
            }
        }
        if (lastCharIsNewLine) {
            if (trackPad) {
                this.mBottomPadding = bottom2 - below2;
            }
            if (includePad) {
                below2 = bottom2;
            }
        }
        if (this.mStaticLayoutExt.lineNeedMultiply(needMultiply, addLastLineLineSpacing, lastCharIsNewLine, this)) {
            double ex = ((below2 - above2) * (spacingmult - 1.0f)) + spacingadd;
            if (ex >= 0.0d) {
                extra = (int) (EXTRA_ROUNDING + ex);
            } else {
                extra = -((int) ((-ex) + EXTRA_ROUNDING));
            }
        } else {
            extra = 0;
        }
        lines[off + 0] = i5;
        lines[off + 1] = v;
        lines[off + 2] = below2 + extra;
        lines[off + 3] = extra;
        if (!this.mEllipsized && i13 != 0) {
            int maxLineBelow = includePad ? bottom2 : below2;
            this.mMaxLineHeight = v + (maxLineBelow - above2);
        }
        int maxLineBelow2 = below2 - above2;
        int v2 = v + maxLineBelow2 + extra;
        int i15 = this.mColumns;
        lines[off + i15 + 0] = end;
        lines[off + i15 + 1] = v2;
        int i16 = off + 0;
        int i17 = lines[i16];
        if (hasTab) {
            i = 536870912;
        }
        lines[i16] = i17 | i;
        lines[off + 4] = hyphenEdit;
        int i18 = off + 0;
        lines[i18] = lines[i18] | (dir << 30);
        this.mLineDirections[j] = measured.getDirections(i5 - i4, end - i4);
        this.mLineCount++;
        return v2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0055, code lost:
        if (r13 >= r10) goto L_0x006c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0061, code lost:
        if (r21.getCharWidthAt((r13 + r19) - r22) != 0.0f) goto L_0x006c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0063, code lost:
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ad, code lost:
        if (r15 >= r10) goto L_0x00c9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bb, code lost:
        if (r21.getCharWidthAt((r15 + r19) - r22) != 0.0f) goto L_0x00c9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00bd, code lost:
        r15 = r15 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void calculateEllipsis(int r19, int r20, android.text.MeasuredParagraph r21, int r22, float r23, android.text.TextUtils.TruncateAt r24, int r25, float r26, android.text.TextPaint r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.StaticLayout.calculateEllipsis(int, int, android.text.MeasuredParagraph, int, float, android.text.TextUtils$TruncateAt, int, float, android.text.TextPaint, boolean):void");
    }

    private float getTotalInsets(int line) {
        int totalIndent = 0;
        int[] iArr = this.mLeftIndents;
        if (iArr != null) {
            totalIndent = iArr[Math.min(line, iArr.length - 1)];
        }
        int[] iArr2 = this.mRightIndents;
        if (iArr2 != null) {
            totalIndent += iArr2[Math.min(line, iArr2.length - 1)];
        }
        return totalIndent;
    }

    @Override // android.text.Layout
    public int getLineForVertical(int vertical) {
        int high = this.mLineCount;
        int low = -1;
        int[] lines = this.mLines;
        while (high - low > 1) {
            int guess = (high + low) >> 1;
            if (lines[(this.mColumns * guess) + 1] > vertical) {
                high = guess;
            } else {
                low = guess;
            }
        }
        if (low < 0) {
            return 0;
        }
        return low;
    }

    @Override // android.text.Layout
    public int getLineCount() {
        return this.mLineCount;
    }

    @Override // android.text.Layout
    public int getLineTop(int line) {
        return this.mLines[(this.mColumns * line) + 1];
    }

    @Override // android.text.Layout
    public int getLineExtra(int line) {
        return this.mLines[(this.mColumns * line) + 3];
    }

    @Override // android.text.Layout
    public int getLineDescent(int line) {
        return this.mLines[(this.mColumns * line) + 2];
    }

    @Override // android.text.Layout
    public int getLineStart(int line) {
        return this.mLines[(this.mColumns * line) + 0] & 536870911;
    }

    @Override // android.text.Layout
    public int getParagraphDirection(int line) {
        return this.mLines[(this.mColumns * line) + 0] >> 30;
    }

    @Override // android.text.Layout
    public boolean getLineContainsTab(int line) {
        return (this.mLines[(this.mColumns * line) + 0] & 536870912) != 0;
    }

    @Override // android.text.Layout
    public final Layout.Directions getLineDirections(int line) {
        if (line <= getLineCount()) {
            return this.mLineDirections[line];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override // android.text.Layout
    public int getTopPadding() {
        return this.mTopPadding;
    }

    @Override // android.text.Layout
    public int getBottomPadding() {
        return this.mBottomPadding;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int packHyphenEdit(int start, int end) {
        return (start << 3) | end;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int unpackStartHyphenEdit(int packedHyphenEdit) {
        return (packedHyphenEdit & 24) >> 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int unpackEndHyphenEdit(int packedHyphenEdit) {
        return packedHyphenEdit & 7;
    }

    @Override // android.text.Layout
    public int getStartHyphenEdit(int lineNumber) {
        return unpackStartHyphenEdit(this.mLines[(this.mColumns * lineNumber) + 4] & 255);
    }

    @Override // android.text.Layout
    public int getEndHyphenEdit(int lineNumber) {
        return unpackEndHyphenEdit(this.mLines[(this.mColumns * lineNumber) + 4] & 255);
    }

    @Override // android.text.Layout
    public int getIndentAdjust(int line, Layout.Alignment align) {
        if (align == Layout.Alignment.ALIGN_LEFT) {
            int[] iArr = this.mLeftIndents;
            if (iArr == null) {
                return 0;
            }
            return iArr[Math.min(line, iArr.length - 1)];
        } else if (align == Layout.Alignment.ALIGN_RIGHT) {
            int[] iArr2 = this.mRightIndents;
            if (iArr2 == null) {
                return 0;
            }
            return -iArr2[Math.min(line, iArr2.length - 1)];
        } else if (align == Layout.Alignment.ALIGN_CENTER) {
            int left = 0;
            int[] iArr3 = this.mLeftIndents;
            if (iArr3 != null) {
                left = iArr3[Math.min(line, iArr3.length - 1)];
            }
            int right = 0;
            int[] iArr4 = this.mRightIndents;
            if (iArr4 != null) {
                right = iArr4[Math.min(line, iArr4.length - 1)];
            }
            return (left - right) >> 1;
        } else {
            throw new AssertionError("unhandled alignment " + align);
        }
    }

    @Override // android.text.Layout
    public int getEllipsisCount(int line) {
        int i = this.mColumns;
        if (i < 7) {
            return 0;
        }
        return this.mLines[(i * line) + 6];
    }

    @Override // android.text.Layout
    public int getEllipsisStart(int line) {
        int i = this.mColumns;
        if (i < 7) {
            return 0;
        }
        return this.mLines[(i * line) + 5];
    }

    @Override // android.text.Layout
    public int getEllipsizedWidth() {
        return this.mEllipsizedWidth;
    }

    @Override // android.text.Layout
    public int getHeight(boolean cap) {
        int i;
        if (cap && this.mLineCount > this.mMaximumVisibleLineCount && this.mMaxLineHeight == -1 && Log.isLoggable(TAG, 5)) {
            Log.w(TAG, "maxLineHeight should not be -1.  maxLines:" + this.mMaximumVisibleLineCount + " lineCount:" + this.mLineCount);
        }
        return (!cap || this.mLineCount <= this.mMaximumVisibleLineCount || (i = this.mMaxLineHeight) == -1) ? super.getHeight() : i;
    }

    /* loaded from: classes3.dex */
    static class LineBreaks {
        private static final int INITIAL_SIZE = 16;
        public int[] breaks = new int[16];
        public float[] widths = new float[16];
        public float[] ascents = new float[16];
        public float[] descents = new float[16];
        public int[] flags = new int[16];

        LineBreaks() {
        }
    }
}
