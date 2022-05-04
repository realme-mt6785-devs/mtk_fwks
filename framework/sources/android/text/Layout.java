package android.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.text.style.AlignmentSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineBackgroundSpan;
import android.text.style.ParagraphStyle;
import android.text.style.ReplacementSpan;
import android.text.style.TabStopSpan;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
/* loaded from: classes3.dex */
public abstract class Layout {
    public static final int BREAK_STRATEGY_BALANCED = 2;
    public static final int BREAK_STRATEGY_HIGH_QUALITY = 1;
    public static final int BREAK_STRATEGY_SIMPLE = 0;
    public static final float DEFAULT_LINESPACING_ADDITION = 0.0f;
    public static final float DEFAULT_LINESPACING_MULTIPLIER = 1.0f;
    public static final int DIR_LEFT_TO_RIGHT = 1;
    static final int DIR_REQUEST_DEFAULT_LTR = 2;
    static final int DIR_REQUEST_DEFAULT_RTL = -2;
    static final int DIR_REQUEST_LTR = 1;
    static final int DIR_REQUEST_RTL = -1;
    public static final int DIR_RIGHT_TO_LEFT = -1;
    public static final int HYPHENATION_FREQUENCY_FULL = 2;
    public static final int HYPHENATION_FREQUENCY_NONE = 0;
    public static final int HYPHENATION_FREQUENCY_NORMAL = 1;
    public static final int JUSTIFICATION_MODE_INTER_WORD = 1;
    public static final int JUSTIFICATION_MODE_NONE = 0;
    static final int RUN_LEVEL_MASK = 63;
    static final int RUN_LEVEL_SHIFT = 26;
    static final int RUN_RTL_FLAG = 67108864;
    private static final float TAB_INCREMENT = 20.0f;
    public static final int TEXT_SELECTION_LAYOUT_LEFT_TO_RIGHT = 1;
    public static final int TEXT_SELECTION_LAYOUT_RIGHT_TO_LEFT = 0;
    private Alignment mAlignment;
    private int mJustificationMode;
    private SpanSet<LineBackgroundSpan> mLineBackgroundSpans;
    private TextPaint mPaint;
    private float mSpacingAdd;
    private float mSpacingMult;
    private boolean mSpannedText;
    private CharSequence mText;
    private TextDirectionHeuristic mTextDir;
    private int mWidth;
    private TextPaint mWorkPaint;
    private static final ParagraphStyle[] NO_PARA_SPANS = (ParagraphStyle[]) ArrayUtils.emptyArray(ParagraphStyle.class);
    private static final Rect sTempRect = new Rect();
    static final int RUN_LENGTH_MASK = 67108863;
    public static final Directions DIRS_ALL_LEFT_TO_RIGHT = new Directions(new int[]{0, RUN_LENGTH_MASK});
    public static final Directions DIRS_ALL_RIGHT_TO_LEFT = new Directions(new int[]{0, 134217727});

    /* loaded from: classes3.dex */
    public enum Alignment {
        ALIGN_NORMAL,
        ALIGN_OPPOSITE,
        ALIGN_CENTER,
        ALIGN_LEFT,
        ALIGN_RIGHT
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface BreakStrategy {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface Direction {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface HyphenationFrequency {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface JustificationMode {
    }

    @FunctionalInterface
    /* loaded from: classes3.dex */
    public interface SelectionRectangleConsumer {
        void accept(float f, float f2, float f3, float f4, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface TextSelectionLayout {
    }

    public abstract int getBottomPadding();

    public abstract int getEllipsisCount(int i);

    public abstract int getEllipsisStart(int i);

    public abstract boolean getLineContainsTab(int i);

    public abstract int getLineCount();

    public abstract int getLineDescent(int i);

    public abstract Directions getLineDirections(int i);

    public abstract int getLineStart(int i);

    public abstract int getLineTop(int i);

    public abstract int getParagraphDirection(int i);

    public abstract int getTopPadding();

    public static float getDesiredWidth(CharSequence source, TextPaint paint) {
        return getDesiredWidth(source, 0, source.length(), paint);
    }

    public static float getDesiredWidth(CharSequence source, int start, int end, TextPaint paint) {
        return getDesiredWidth(source, start, end, paint, TextDirectionHeuristics.FIRSTSTRONG_LTR);
    }

    public static float getDesiredWidth(CharSequence source, int start, int end, TextPaint paint, TextDirectionHeuristic textDir) {
        return getDesiredWidthWithLimit(source, start, end, paint, textDir, Float.MAX_VALUE);
    }

    public static float getDesiredWidthWithLimit(CharSequence source, int start, int end, TextPaint paint, TextDirectionHeuristic textDir, float upperLimit) {
        float need = 0.0f;
        int i = start;
        while (i <= end) {
            int next = TextUtils.indexOf(source, '\n', i, end);
            if (next < 0) {
                next = end;
            }
            float w = measurePara(paint, source, i, next, textDir);
            if (w > upperLimit) {
                return upperLimit;
            }
            if (w > need) {
                need = w;
            }
            i = next + 1;
        }
        return need;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Layout(CharSequence text, TextPaint paint, int width, Alignment align, float spacingMult, float spacingAdd) {
        this(text, paint, width, align, TextDirectionHeuristics.FIRSTSTRONG_LTR, spacingMult, spacingAdd);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Layout(CharSequence text, TextPaint paint, int width, Alignment align, TextDirectionHeuristic textDir, float spacingMult, float spacingAdd) {
        this.mWorkPaint = new TextPaint();
        this.mAlignment = Alignment.ALIGN_NORMAL;
        if (width >= 0) {
            if (paint != null) {
                paint.bgColor = 0;
                paint.baselineShift = 0;
            }
            this.mText = text;
            this.mPaint = paint;
            this.mWidth = width;
            this.mAlignment = align;
            this.mSpacingMult = spacingMult;
            this.mSpacingAdd = spacingAdd;
            this.mSpannedText = text instanceof Spanned;
            this.mTextDir = textDir;
            return;
        }
        throw new IllegalArgumentException("Layout: " + width + " < 0");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setJustificationMode(int justificationMode) {
        this.mJustificationMode = justificationMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceWith(CharSequence text, TextPaint paint, int width, Alignment align, float spacingmult, float spacingadd) {
        if (width >= 0) {
            this.mText = text;
            this.mPaint = paint;
            this.mWidth = width;
            this.mAlignment = align;
            this.mSpacingMult = spacingmult;
            this.mSpacingAdd = spacingadd;
            this.mSpannedText = text instanceof Spanned;
            return;
        }
        throw new IllegalArgumentException("Layout: " + width + " < 0");
    }

    public void draw(Canvas c) {
        draw(c, null, null, 0);
    }

    public void draw(Canvas canvas, Path highlight, Paint highlightPaint, int cursorOffsetVertical) {
        long lineRange = getLineRangeForDraw(canvas);
        int firstLine = TextUtils.unpackRangeStartFromLong(lineRange);
        int lastLine = TextUtils.unpackRangeEndFromLong(lineRange);
        if (lastLine >= 0) {
            drawBackground(canvas, highlight, highlightPaint, cursorOffsetVertical, firstLine, lastLine);
            drawText(canvas, firstLine, lastLine);
        }
    }

    private boolean isJustificationRequired(int lineNum) {
        int lineEnd;
        return (this.mJustificationMode == 0 || (lineEnd = getLineEnd(lineNum)) >= this.mText.length() || this.mText.charAt(lineEnd + (-1)) == '\n') ? false : true;
    }

    private float getJustifyWidth(int lineNum) {
        Alignment align;
        Alignment paraAlign = this.mAlignment;
        int left = 0;
        int right = this.mWidth;
        int dir = getParagraphDirection(lineNum);
        ParagraphStyle[] spans = NO_PARA_SPANS;
        if (this.mSpannedText) {
            Spanned sp = (Spanned) this.mText;
            int start = getLineStart(lineNum);
            boolean isFirstParaLine = start == 0 || this.mText.charAt(start + (-1)) == '\n';
            if (isFirstParaLine) {
                spans = (ParagraphStyle[]) getParagraphSpans(sp, start, sp.nextSpanTransition(start, this.mText.length(), ParagraphStyle.class), ParagraphStyle.class);
                int n = spans.length - 1;
                while (true) {
                    if (n < 0) {
                        break;
                    } else if (spans[n] instanceof AlignmentSpan) {
                        paraAlign = ((AlignmentSpan) spans[n]).getAlignment();
                        break;
                    } else {
                        n--;
                    }
                }
            }
            int spanEnd = spans.length;
            boolean useFirstLineMargin = isFirstParaLine;
            int n2 = 0;
            while (true) {
                if (n2 >= spanEnd) {
                    break;
                }
                if (spans[n2] instanceof LeadingMarginSpan.LeadingMarginSpan2) {
                    int count = ((LeadingMarginSpan.LeadingMarginSpan2) spans[n2]).getLeadingMarginLineCount();
                    int startLine = getLineForOffset(sp.getSpanStart(spans[n2]));
                    if (lineNum < startLine + count) {
                        useFirstLineMargin = true;
                        break;
                    }
                }
                n2++;
            }
            for (int n3 = 0; n3 < spanEnd; n3++) {
                if (spans[n3] instanceof LeadingMarginSpan) {
                    LeadingMarginSpan margin = (LeadingMarginSpan) spans[n3];
                    if (dir == -1) {
                        right -= margin.getLeadingMargin(useFirstLineMargin);
                    } else {
                        left += margin.getLeadingMargin(useFirstLineMargin);
                    }
                }
            }
        }
        if (paraAlign == Alignment.ALIGN_LEFT) {
            align = dir == 1 ? Alignment.ALIGN_NORMAL : Alignment.ALIGN_OPPOSITE;
        } else if (paraAlign == Alignment.ALIGN_RIGHT) {
            align = dir == 1 ? Alignment.ALIGN_OPPOSITE : Alignment.ALIGN_NORMAL;
        } else {
            align = paraAlign;
        }
        int indentWidth = align == Alignment.ALIGN_NORMAL ? dir == 1 ? getIndentAdjust(lineNum, Alignment.ALIGN_LEFT) : -getIndentAdjust(lineNum, Alignment.ALIGN_RIGHT) : align == Alignment.ALIGN_OPPOSITE ? dir == 1 ? -getIndentAdjust(lineNum, Alignment.ALIGN_RIGHT) : getIndentAdjust(lineNum, Alignment.ALIGN_LEFT) : getIndentAdjust(lineNum, Alignment.ALIGN_CENTER);
        return (right - left) - indentWidth;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00c4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x010f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x011d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void drawText(android.graphics.Canvas r44, int r45, int r46) {
        /*
            Method dump skipped, instructions count: 874
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.drawText(android.graphics.Canvas, int, int):void");
    }

    public void drawBackground(Canvas canvas, Path highlight, Paint highlightPaint, int cursorOffsetVertical, int firstLine, int lastLine) {
        int spanEnd;
        ParagraphStyle[] spans;
        int spansLength;
        if (this.mSpannedText) {
            if (this.mLineBackgroundSpans == null) {
                this.mLineBackgroundSpans = new SpanSet<>(LineBackgroundSpan.class);
            }
            Spanned buffer = (Spanned) this.mText;
            int textLength = buffer.length();
            this.mLineBackgroundSpans.init(buffer, 0, textLength);
            if (this.mLineBackgroundSpans.numberOfSpans > 0) {
                int previousLineBottom = getLineTop(firstLine);
                int previousLineEnd = getLineStart(firstLine);
                ParagraphStyle[] spans2 = NO_PARA_SPANS;
                int spansLength2 = 0;
                TextPaint paint = this.mPaint;
                int spanEnd2 = 0;
                int width = this.mWidth;
                int i = firstLine;
                while (i <= lastLine) {
                    int previousLineEnd2 = i + 1;
                    int end = getLineStart(previousLineEnd2);
                    int start = previousLineEnd;
                    int previousLineBottom2 = i + 1;
                    int lbottom = getLineTop(previousLineBottom2);
                    int lbaseline = lbottom - getLineDescent(i);
                    if (end >= spanEnd2) {
                        int spanEnd3 = this.mLineBackgroundSpans.getNextTransition(start, textLength);
                        int spansLength3 = 0;
                        if (start != end || start == 0) {
                            spanEnd = spanEnd3;
                            for (int spanEnd4 = 0; spanEnd4 < this.mLineBackgroundSpans.numberOfSpans; spanEnd4++) {
                                if (this.mLineBackgroundSpans.spanStarts[spanEnd4] < end && this.mLineBackgroundSpans.spanEnds[spanEnd4] > start) {
                                    spans2 = (ParagraphStyle[]) GrowingArrayUtils.append((LineBackgroundSpan[]) spans2, spansLength3, this.mLineBackgroundSpans.spans[spanEnd4]);
                                    spansLength3++;
                                }
                            }
                            spans = spans2;
                            spansLength = spansLength3;
                        } else {
                            spanEnd = spanEnd3;
                            spans = spans2;
                            spansLength = 0;
                        }
                    } else {
                        spanEnd = spanEnd2;
                        spansLength = spansLength2;
                        spans = spans2;
                    }
                    int n = 0;
                    while (n < spansLength) {
                        LineBackgroundSpan lineBackgroundSpan = (LineBackgroundSpan) spans[n];
                        lineBackgroundSpan.drawBackground(canvas, paint, 0, width, previousLineBottom, lbaseline, lbottom, buffer, start, end, i);
                        n++;
                        end = end;
                        start = start;
                        spansLength = spansLength;
                        i = i;
                        width = width;
                        paint = paint;
                        textLength = textLength;
                        buffer = buffer;
                    }
                    i++;
                    previousLineEnd = end;
                    spans2 = spans;
                    previousLineBottom = lbottom;
                    spanEnd2 = spanEnd;
                    spansLength2 = spansLength;
                }
            }
            this.mLineBackgroundSpans.recycle();
        }
        if (highlight != null) {
            if (cursorOffsetVertical != 0) {
                canvas.translate(0.0f, cursorOffsetVertical);
            }
            canvas.drawPath(highlight, highlightPaint);
            if (cursorOffsetVertical != 0) {
                canvas.translate(0.0f, -cursorOffsetVertical);
            }
        }
    }

    public long getLineRangeForDraw(Canvas canvas) {
        Rect rect = sTempRect;
        synchronized (rect) {
            if (!canvas.getClipBounds(rect)) {
                return TextUtils.packRangeInLong(0, -1);
            }
            int dtop = rect.top;
            int dbottom = rect.bottom;
            int top = Math.max(dtop, 0);
            int bottom = Math.min(getLineTop(getLineCount()), dbottom);
            return top >= bottom ? TextUtils.packRangeInLong(0, -1) : TextUtils.packRangeInLong(getLineForVertical(top), getLineForVertical(bottom));
        }
    }

    private int getLineStartPos(int line, int left, int right) {
        Alignment align = getParagraphAlignment(line);
        int dir = getParagraphDirection(line);
        if (align == Alignment.ALIGN_LEFT) {
            align = dir == 1 ? Alignment.ALIGN_NORMAL : Alignment.ALIGN_OPPOSITE;
        } else if (align == Alignment.ALIGN_RIGHT) {
            align = dir == 1 ? Alignment.ALIGN_OPPOSITE : Alignment.ALIGN_NORMAL;
        }
        if (align != Alignment.ALIGN_NORMAL) {
            TabStops tabStops = null;
            if (this.mSpannedText && getLineContainsTab(line)) {
                Spanned spanned = (Spanned) this.mText;
                int start = getLineStart(line);
                int spanEnd = spanned.nextSpanTransition(start, spanned.length(), TabStopSpan.class);
                TabStopSpan[] tabSpans = (TabStopSpan[]) getParagraphSpans(spanned, start, spanEnd, TabStopSpan.class);
                if (tabSpans.length > 0) {
                    tabStops = new TabStops(TAB_INCREMENT, tabSpans);
                }
            }
            int max = (int) getLineExtent(line, tabStops, false);
            if (align != Alignment.ALIGN_OPPOSITE) {
                int x = ((left + right) - (max & (-2))) >> (getIndentAdjust(line, Alignment.ALIGN_CENTER) + 1);
                return x;
            } else if (dir == 1) {
                int x2 = (right - max) + getIndentAdjust(line, Alignment.ALIGN_RIGHT);
                return x2;
            } else {
                int x3 = left - max;
                return x3 + getIndentAdjust(line, Alignment.ALIGN_LEFT);
            }
        } else if (dir == 1) {
            int x4 = getIndentAdjust(line, Alignment.ALIGN_LEFT) + left;
            return x4;
        } else {
            int x5 = getIndentAdjust(line, Alignment.ALIGN_RIGHT) + right;
            return x5;
        }
    }

    public final CharSequence getText() {
        return this.mText;
    }

    public final TextPaint getPaint() {
        return this.mPaint;
    }

    public final int getWidth() {
        return this.mWidth;
    }

    public int getEllipsizedWidth() {
        return this.mWidth;
    }

    public final void increaseWidthTo(int wid) {
        if (wid >= this.mWidth) {
            this.mWidth = wid;
            return;
        }
        throw new RuntimeException("attempted to reduce Layout width");
    }

    public int getHeight() {
        return getLineTop(getLineCount());
    }

    public int getHeight(boolean cap) {
        return getHeight();
    }

    public final Alignment getAlignment() {
        return this.mAlignment;
    }

    public final float getSpacingMultiplier() {
        return this.mSpacingMult;
    }

    public final float getSpacingAdd() {
        return this.mSpacingAdd;
    }

    public final TextDirectionHeuristic getTextDirectionHeuristic() {
        return this.mTextDir;
    }

    public int getLineBounds(int line, Rect bounds) {
        if (bounds != null) {
            bounds.left = 0;
            bounds.top = getLineTop(line);
            bounds.right = this.mWidth;
            bounds.bottom = getLineTop(line + 1);
        }
        return getLineBaseline(line);
    }

    public int getStartHyphenEdit(int line) {
        return 0;
    }

    public int getEndHyphenEdit(int line) {
        return 0;
    }

    public int getIndentAdjust(int line, Alignment alignment) {
        return 0;
    }

    public boolean isLevelBoundary(int offset) {
        int line = getLineForOffset(offset);
        Directions dirs = getLineDirections(line);
        if (dirs == DIRS_ALL_LEFT_TO_RIGHT || dirs == DIRS_ALL_RIGHT_TO_LEFT) {
            return false;
        }
        int[] runs = dirs.mDirections;
        int lineStart = getLineStart(line);
        int lineEnd = getLineEnd(line);
        if (offset == lineStart || offset == lineEnd) {
            int paraLevel = getParagraphDirection(line) == 1 ? 0 : 1;
            int runIndex = offset == lineStart ? 0 : runs.length - 2;
            return ((runs[runIndex + 1] >>> 26) & 63) != paraLevel;
        }
        int offset2 = offset - lineStart;
        for (int i = 0; i < runs.length; i += 2) {
            if (offset2 == runs[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean isRtlCharAt(int offset) {
        int line = getLineForOffset(offset);
        Directions dirs = getLineDirections(line);
        if (dirs == DIRS_ALL_LEFT_TO_RIGHT) {
            return false;
        }
        if (dirs == DIRS_ALL_RIGHT_TO_LEFT) {
            return true;
        }
        int[] runs = dirs.mDirections;
        int lineStart = getLineStart(line);
        for (int i = 0; i < runs.length; i += 2) {
            int start = runs[i] + lineStart;
            int limit = (runs[i + 1] & RUN_LENGTH_MASK) + start;
            if (offset >= start && offset < limit) {
                int level = (runs[i + 1] >>> 26) & 63;
                return (level & 1) != 0;
            }
        }
        return false;
    }

    public long getRunRange(int offset) {
        int line = getLineForOffset(offset);
        Directions dirs = getLineDirections(line);
        if (dirs == DIRS_ALL_LEFT_TO_RIGHT || dirs == DIRS_ALL_RIGHT_TO_LEFT) {
            return TextUtils.packRangeInLong(0, getLineEnd(line));
        }
        int[] runs = dirs.mDirections;
        int lineStart = getLineStart(line);
        for (int i = 0; i < runs.length; i += 2) {
            int start = runs[i] + lineStart;
            int limit = (runs[i + 1] & RUN_LENGTH_MASK) + start;
            if (offset >= start && offset < limit) {
                return TextUtils.packRangeInLong(start, limit);
            }
        }
        int i2 = getLineEnd(line);
        return TextUtils.packRangeInLong(0, i2);
    }

    public boolean primaryIsTrailingPrevious(int offset) {
        int line = getLineForOffset(offset);
        int lineStart = getLineStart(line);
        int lineEnd = getLineEnd(line);
        int[] runs = getLineDirections(line).mDirections;
        int levelAt = -1;
        int i = 0;
        while (true) {
            if (i >= runs.length) {
                break;
            }
            int start = runs[i] + lineStart;
            int limit = (runs[i + 1] & RUN_LENGTH_MASK) + start;
            if (limit > lineEnd) {
                limit = lineEnd;
            }
            if (offset < start || offset >= limit) {
                i += 2;
            } else if (offset > start) {
                return false;
            } else {
                levelAt = (runs[i + 1] >>> 26) & 63;
            }
        }
        if (levelAt == -1) {
            levelAt = getParagraphDirection(line) == 1 ? 0 : 1;
        }
        int levelBefore = -1;
        if (offset != lineStart) {
            int offset2 = offset - 1;
            int i2 = 0;
            while (true) {
                if (i2 < runs.length) {
                    int start2 = runs[i2] + lineStart;
                    int limit2 = (runs[i2 + 1] & RUN_LENGTH_MASK) + start2;
                    if (limit2 > lineEnd) {
                        limit2 = lineEnd;
                    }
                    if (offset2 >= start2 && offset2 < limit2) {
                        levelBefore = (runs[i2 + 1] >>> 26) & 63;
                        break;
                    }
                    i2 += 2;
                } else {
                    break;
                }
            }
        } else {
            levelBefore = getParagraphDirection(line) == 1 ? 0 : 1;
        }
        return levelBefore < levelAt;
    }

    public boolean[] primaryIsTrailingPreviousAllLineOffsets(int line) {
        byte b;
        int lineStart = getLineStart(line);
        int lineEnd = getLineEnd(line);
        int[] runs = getLineDirections(line).mDirections;
        boolean[] trailing = new boolean[(lineEnd - lineStart) + 1];
        byte[] level = new byte[(lineEnd - lineStart) + 1];
        for (int i = 0; i < runs.length; i += 2) {
            int start = runs[i] + lineStart;
            int limit = (runs[i + 1] & RUN_LENGTH_MASK) + start;
            if (limit > lineEnd) {
                limit = lineEnd;
            }
            if (limit != start) {
                level[(limit - lineStart) - 1] = (byte) ((runs[i + 1] >>> 26) & 63);
            }
        }
        for (int i2 = 0; i2 < runs.length; i2 += 2) {
            int start2 = runs[i2] + lineStart;
            byte currentLevel = (byte) ((runs[i2 + 1] >>> 26) & 63);
            int i3 = start2 - lineStart;
            boolean z = false;
            if (start2 == lineStart) {
                b = getParagraphDirection(line) == 1 ? (byte) 0 : (byte) 1;
            } else {
                b = level[(start2 - lineStart) - 1];
            }
            if (currentLevel > b) {
                z = true;
            }
            trailing[i3] = z;
        }
        return trailing;
    }

    public float getPrimaryHorizontal(int offset) {
        return getPrimaryHorizontal(offset, false);
    }

    public float getPrimaryHorizontal(int offset, boolean clamped) {
        boolean trailing = primaryIsTrailingPrevious(offset);
        return getHorizontal(offset, trailing, clamped);
    }

    public float getSecondaryHorizontal(int offset) {
        return getSecondaryHorizontal(offset, false);
    }

    public float getSecondaryHorizontal(int offset, boolean clamped) {
        boolean trailing = primaryIsTrailingPrevious(offset);
        return getHorizontal(offset, !trailing, clamped);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getHorizontal(int offset, boolean primary) {
        return primary ? getPrimaryHorizontal(offset) : getSecondaryHorizontal(offset);
    }

    private float getHorizontal(int offset, boolean trailing, boolean clamped) {
        int line = getLineForOffset(offset);
        return getHorizontal(offset, trailing, line, clamped);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float getHorizontal(int r22, boolean r23, int r24, boolean r25) {
        /*
            r21 = this;
            r0 = r21
            r1 = r24
            int r13 = r0.getLineStart(r1)
            int r14 = r0.getLineEnd(r1)
            int r15 = r0.getParagraphDirection(r1)
            boolean r16 = r0.getLineContainsTab(r1)
            android.text.Layout$Directions r17 = r0.getLineDirections(r1)
            r2 = 0
            if (r16 == 0) goto L_0x0039
            java.lang.CharSequence r3 = r0.mText
            boolean r4 = r3 instanceof android.text.Spanned
            if (r4 == 0) goto L_0x0039
            android.text.Spanned r3 = (android.text.Spanned) r3
            java.lang.Class<android.text.style.TabStopSpan> r4 = android.text.style.TabStopSpan.class
            java.lang.Object[] r3 = getParagraphSpans(r3, r13, r14, r4)
            android.text.style.TabStopSpan[] r3 = (android.text.style.TabStopSpan[]) r3
            int r4 = r3.length
            if (r4 <= 0) goto L_0x0039
            android.text.Layout$TabStops r4 = new android.text.Layout$TabStops
            r5 = 1101004800(0x41a00000, float:20.0)
            r4.<init>(r5, r3)
            r2 = r4
            r18 = r2
            goto L_0x003b
        L_0x0039:
            r18 = r2
        L_0x003b:
            android.text.TextLine r12 = android.text.TextLine.obtain()
            android.text.TextPaint r3 = r0.mPaint
            java.lang.CharSequence r4 = r0.mText
            int r11 = r0.getEllipsisStart(r1)
            int r2 = r0.getEllipsisStart(r1)
            int r5 = r0.getEllipsisCount(r1)
            int r19 = r2 + r5
            r2 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r8 = r17
            r9 = r16
            r10 = r18
            r20 = r14
            r14 = r12
            r12 = r19
            r2.set(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            int r2 = r22 - r13
            r3 = 0
            r4 = r23
            float r2 = r14.measure(r2, r4, r3)
            android.text.TextLine.recycle(r14)
            if (r25 == 0) goto L_0x0079
            int r3 = r0.mWidth
            float r5 = (float) r3
            int r5 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x0079
            float r2 = (float) r3
        L_0x0079:
            int r3 = r0.getParagraphLeft(r1)
            int r5 = r0.getParagraphRight(r1)
            int r6 = r0.getLineStartPos(r1, r3, r5)
            float r6 = (float) r6
            float r6 = r6 + r2
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getHorizontal(int, boolean, int, boolean):float");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a8 A[LOOP:2: B:26:0x00a5->B:28:0x00a8, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public float[] getLineHorizontals(int r21, boolean r22, boolean r23) {
        /*
            r20 = this;
            r0 = r20
            int r12 = r20.getLineStart(r21)
            int r13 = r20.getLineEnd(r21)
            int r14 = r20.getParagraphDirection(r21)
            boolean r15 = r20.getLineContainsTab(r21)
            android.text.Layout$Directions r16 = r20.getLineDirections(r21)
            r1 = 0
            if (r15 == 0) goto L_0x0037
            java.lang.CharSequence r2 = r0.mText
            boolean r3 = r2 instanceof android.text.Spanned
            if (r3 == 0) goto L_0x0037
            android.text.Spanned r2 = (android.text.Spanned) r2
            java.lang.Class<android.text.style.TabStopSpan> r3 = android.text.style.TabStopSpan.class
            java.lang.Object[] r2 = getParagraphSpans(r2, r12, r13, r3)
            android.text.style.TabStopSpan[] r2 = (android.text.style.TabStopSpan[]) r2
            int r3 = r2.length
            if (r3 <= 0) goto L_0x0037
            android.text.Layout$TabStops r3 = new android.text.Layout$TabStops
            r4 = 1101004800(0x41a00000, float:20.0)
            r3.<init>(r4, r2)
            r1 = r3
            r17 = r1
            goto L_0x0039
        L_0x0037:
            r17 = r1
        L_0x0039:
            android.text.TextLine r11 = android.text.TextLine.obtain()
            android.text.TextPaint r2 = r0.mPaint
            java.lang.CharSequence r3 = r0.mText
            int r10 = r20.getEllipsisStart(r21)
            int r1 = r20.getEllipsisStart(r21)
            int r4 = r20.getEllipsisCount(r21)
            int r18 = r1 + r4
            r1 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r16
            r8 = r15
            r9 = r17
            r19 = r14
            r14 = r11
            r11 = r18
            r1.set(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            boolean[] r1 = r20.primaryIsTrailingPreviousAllLineOffsets(r21)
            if (r23 != 0) goto L_0x0073
            r2 = 0
        L_0x0067:
            int r3 = r1.length
            if (r2 >= r3) goto L_0x0073
            boolean r3 = r1[r2]
            r3 = r3 ^ 1
            r1[r2] = r3
            int r2 = r2 + 1
            goto L_0x0067
        L_0x0073:
            r2 = 0
            float[] r2 = r14.measureAllOffsets(r1, r2)
            android.text.TextLine.recycle(r14)
            if (r22 == 0) goto L_0x0090
            r3 = 0
        L_0x007e:
            int r4 = r2.length
            if (r3 >= r4) goto L_0x0090
            r4 = r2[r3]
            int r5 = r0.mWidth
            float r6 = (float) r5
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x008d
            float r4 = (float) r5
            r2[r3] = r4
        L_0x008d:
            int r3 = r3 + 1
            goto L_0x007e
        L_0x0090:
            int r3 = r20.getParagraphLeft(r21)
            int r4 = r20.getParagraphRight(r21)
            r5 = r21
            int r6 = r0.getLineStartPos(r5, r3, r4)
            int r7 = r13 - r12
            int r7 = r7 + 1
            float[] r7 = new float[r7]
            r8 = 0
        L_0x00a5:
            int r9 = r7.length
            if (r8 >= r9) goto L_0x00b1
            float r9 = (float) r6
            r10 = r2[r8]
            float r9 = r9 + r10
            r7[r8] = r9
            int r8 = r8 + 1
            goto L_0x00a5
        L_0x00b1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getLineHorizontals(int, boolean, boolean):float[]");
    }

    public float getLineLeft(int line) {
        Alignment resultAlign;
        int dir = getParagraphDirection(line);
        Alignment align = getParagraphAlignment(line);
        if (align == null) {
            align = Alignment.ALIGN_CENTER;
        }
        switch (AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[align.ordinal()]) {
            case 1:
                if (dir != -1) {
                    resultAlign = Alignment.ALIGN_LEFT;
                    break;
                } else {
                    resultAlign = Alignment.ALIGN_RIGHT;
                    break;
                }
            case 2:
                if (dir != -1) {
                    resultAlign = Alignment.ALIGN_RIGHT;
                    break;
                } else {
                    resultAlign = Alignment.ALIGN_LEFT;
                    break;
                }
            case 3:
                resultAlign = Alignment.ALIGN_CENTER;
                break;
            case 4:
                resultAlign = Alignment.ALIGN_RIGHT;
                break;
            default:
                resultAlign = Alignment.ALIGN_LEFT;
                break;
        }
        switch (resultAlign) {
            case ALIGN_CENTER:
                int left = getParagraphLeft(line);
                float max = getLineMax(line);
                return (float) Math.floor(left + ((this.mWidth - max) / 2.0f));
            case ALIGN_RIGHT:
                return this.mWidth - getLineMax(line);
            default:
                return 0.0f;
        }
    }

    public float getLineRight(int line) {
        Alignment resultAlign;
        int dir = getParagraphDirection(line);
        Alignment align = getParagraphAlignment(line);
        if (align == null) {
            align = Alignment.ALIGN_CENTER;
        }
        switch (AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[align.ordinal()]) {
            case 1:
                if (dir != -1) {
                    resultAlign = Alignment.ALIGN_LEFT;
                    break;
                } else {
                    resultAlign = Alignment.ALIGN_RIGHT;
                    break;
                }
            case 2:
                if (dir != -1) {
                    resultAlign = Alignment.ALIGN_RIGHT;
                    break;
                } else {
                    resultAlign = Alignment.ALIGN_LEFT;
                    break;
                }
            case 3:
                resultAlign = Alignment.ALIGN_CENTER;
                break;
            case 4:
                resultAlign = Alignment.ALIGN_RIGHT;
                break;
            default:
                resultAlign = Alignment.ALIGN_LEFT;
                break;
        }
        switch (resultAlign) {
            case ALIGN_CENTER:
                int right = getParagraphRight(line);
                float max = getLineMax(line);
                return (float) Math.ceil(right - ((this.mWidth - max) / 2.0f));
            case ALIGN_RIGHT:
                return this.mWidth;
            default:
                return getLineMax(line);
        }
    }

    public float getLineMax(int line) {
        float margin = getParagraphLeadingMargin(line);
        float signedExtent = getLineExtent(line, false);
        return (signedExtent >= 0.0f ? signedExtent : -signedExtent) + margin;
    }

    public float getLineWidth(int line) {
        float margin = getParagraphLeadingMargin(line);
        float signedExtent = getLineExtent(line, true);
        return (signedExtent >= 0.0f ? signedExtent : -signedExtent) + margin;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float getLineExtent(int r22, boolean r23) {
        /*
            r21 = this;
            r0 = r21
            int r12 = r21.getLineStart(r22)
            if (r23 == 0) goto L_0x000d
            int r1 = r21.getLineEnd(r22)
            goto L_0x0011
        L_0x000d:
            int r1 = r21.getLineVisibleEnd(r22)
        L_0x0011:
            r13 = r1
            boolean r14 = r21.getLineContainsTab(r22)
            r1 = 0
            if (r14 == 0) goto L_0x0036
            java.lang.CharSequence r2 = r0.mText
            boolean r3 = r2 instanceof android.text.Spanned
            if (r3 == 0) goto L_0x0036
            android.text.Spanned r2 = (android.text.Spanned) r2
            java.lang.Class<android.text.style.TabStopSpan> r3 = android.text.style.TabStopSpan.class
            java.lang.Object[] r2 = getParagraphSpans(r2, r12, r13, r3)
            android.text.style.TabStopSpan[] r2 = (android.text.style.TabStopSpan[]) r2
            int r3 = r2.length
            if (r3 <= 0) goto L_0x0036
            android.text.Layout$TabStops r3 = new android.text.Layout$TabStops
            r4 = 1101004800(0x41a00000, float:20.0)
            r3.<init>(r4, r2)
            r1 = r3
            r15 = r1
            goto L_0x0037
        L_0x0036:
            r15 = r1
        L_0x0037:
            android.text.Layout$Directions r16 = r21.getLineDirections(r22)
            if (r16 != 0) goto L_0x003f
            r1 = 0
            return r1
        L_0x003f:
            int r17 = r21.getParagraphDirection(r22)
            android.text.TextLine r11 = android.text.TextLine.obtain()
            android.text.TextPaint r10 = r0.mWorkPaint
            android.text.TextPaint r1 = r0.mPaint
            r10.set(r1)
            int r1 = r21.getStartHyphenEdit(r22)
            r10.setStartHyphenEdit(r1)
            int r1 = r21.getEndHyphenEdit(r22)
            r10.setEndHyphenEdit(r1)
            java.lang.CharSequence r3 = r0.mText
            int r18 = r21.getEllipsisStart(r22)
            int r1 = r21.getEllipsisStart(r22)
            int r2 = r21.getEllipsisCount(r22)
            int r19 = r1 + r2
            r1 = r11
            r2 = r10
            r4 = r12
            r5 = r13
            r6 = r17
            r7 = r16
            r8 = r14
            r9 = r15
            r20 = r10
            r10 = r18
            r0 = r11
            r11 = r19
            r1.set(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            boolean r1 = r21.isJustificationRequired(r22)
            if (r1 == 0) goto L_0x008d
            float r1 = r21.getJustifyWidth(r22)
            r0.justify(r1)
        L_0x008d:
            r1 = 0
            float r1 = r0.metrics(r1)
            android.text.TextLine.recycle(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getLineExtent(int, boolean):float");
    }

    private float getLineExtent(int line, TabStops tabStops, boolean full) {
        int start = getLineStart(line);
        int end = full ? getLineEnd(line) : getLineVisibleEnd(line);
        boolean hasTabs = getLineContainsTab(line);
        Directions directions = getLineDirections(line);
        int dir = getParagraphDirection(line);
        TextLine tl = TextLine.obtain();
        TextPaint paint = this.mWorkPaint;
        paint.set(this.mPaint);
        paint.setStartHyphenEdit(getStartHyphenEdit(line));
        paint.setEndHyphenEdit(getEndHyphenEdit(line));
        tl.set(paint, this.mText, start, end, dir, directions, hasTabs, tabStops, getEllipsisStart(line), getEllipsisStart(line) + getEllipsisCount(line));
        if (isJustificationRequired(line)) {
            tl.justify(getJustifyWidth(line));
        }
        float width = tl.metrics(null);
        TextLine.recycle(tl);
        return width;
    }

    public int getLineForVertical(int vertical) {
        int high = getLineCount();
        int low = -1;
        while (high - low > 1) {
            int guess = (high + low) / 2;
            if (getLineTop(guess) > vertical) {
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

    public int getLineForOffset(int offset) {
        int high = getLineCount();
        int low = -1;
        while (high - low > 1) {
            int guess = (high + low) / 2;
            if (getLineStart(guess) > offset) {
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

    public int getOffsetForHorizontal(int line, float horiz) {
        return getOffsetForHorizontal(line, horiz, true);
    }

    public int getOffsetForHorizontal(int line, float horiz, boolean primary) {
        int max;
        Layout layout = this;
        int lineEndOffset = getLineEnd(line);
        int lineStartOffset = getLineStart(line);
        Directions dirs = getLineDirections(line);
        TextLine tl = TextLine.obtain();
        Directions dirs2 = dirs;
        tl.set(layout.mPaint, layout.mText, lineStartOffset, lineEndOffset, getParagraphDirection(line), dirs, false, null, getEllipsisStart(line), getEllipsisStart(line) + getEllipsisCount(line));
        HorizontalMeasurementProvider horizontal = new HorizontalMeasurementProvider(line, primary);
        int i = true;
        if (line == getLineCount() - 1) {
            max = lineEndOffset;
        } else {
            max = tl.getOffsetToLeftRightOf(lineEndOffset - lineStartOffset, !layout.isRtlCharAt(lineEndOffset - 1)) + lineStartOffset;
        }
        int best = lineStartOffset;
        float bestdist = Math.abs(horizontal.get(lineStartOffset) - horiz);
        int i2 = 0;
        while (i2 < dirs2.mDirections.length) {
            int here = dirs2.mDirections[i2] + lineStartOffset;
            int there = (dirs2.mDirections[i2 + 1] & RUN_LENGTH_MASK) + here;
            boolean isRtl = (dirs2.mDirections[i2 + 1] & 67108864) != 0 ? i : false;
            int swap = isRtl ? -1 : i;
            if (there > max) {
                there = max;
            }
            int high = (there - 1) + 1;
            int low = (here + 1) - 1;
            while (high - low > 1) {
                int guess = (high + low) / 2;
                int adguess = layout.getOffsetAtStartOf(guess);
                if (horizontal.get(adguess) * swap >= swap * horiz) {
                    high = guess;
                } else {
                    low = guess;
                }
                swap = swap;
                layout = this;
            }
            if (low < here + 1) {
                low = here + 1;
            }
            if (low < there) {
                int aft = tl.getOffsetToLeftRightOf(low - lineStartOffset, isRtl) + lineStartOffset;
                int swap2 = tl.getOffsetToLeftRightOf(aft - lineStartOffset, !isRtl);
                int low2 = swap2 + lineStartOffset;
                if (low2 >= here && low2 < there) {
                    float dist = Math.abs(horizontal.get(low2) - horiz);
                    if (aft < there) {
                        float other = Math.abs(horizontal.get(aft) - horiz);
                        if (other < dist) {
                            dist = other;
                            low2 = aft;
                        }
                    }
                    if (dist < bestdist) {
                        bestdist = dist;
                        best = low2;
                    }
                }
            }
            float dist2 = Math.abs(horizontal.get(here) - horiz);
            if (dist2 < bestdist) {
                bestdist = dist2;
                best = here;
            }
            i2 += 2;
            layout = this;
            dirs2 = dirs2;
            i = true;
        }
        if (Math.abs(horizontal.get(max) - horiz) <= bestdist) {
            best = max;
        }
        TextLine.recycle(tl);
        return best;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class HorizontalMeasurementProvider {
        private float[] mHorizontals;
        private final int mLine;
        private int mLineStartOffset;
        private final boolean mPrimary;

        HorizontalMeasurementProvider(int line, boolean primary) {
            this.mLine = line;
            this.mPrimary = primary;
            init();
        }

        private void init() {
            Directions dirs = Layout.this.getLineDirections(this.mLine);
            if (dirs != Layout.DIRS_ALL_LEFT_TO_RIGHT) {
                this.mHorizontals = Layout.this.getLineHorizontals(this.mLine, false, this.mPrimary);
                this.mLineStartOffset = Layout.this.getLineStart(this.mLine);
            }
        }

        float get(int offset) {
            int index = offset - this.mLineStartOffset;
            float[] fArr = this.mHorizontals;
            if (fArr == null || index < 0 || index >= fArr.length) {
                return Layout.this.getHorizontal(offset, this.mPrimary);
            }
            return fArr[index];
        }
    }

    public final int getLineEnd(int line) {
        return getLineStart(line + 1);
    }

    public int getLineVisibleEnd(int line) {
        return getLineVisibleEnd(line, getLineStart(line), getLineStart(line + 1));
    }

    private int getLineVisibleEnd(int line, int start, int end) {
        CharSequence text = this.mText;
        if (line == getLineCount() - 1) {
            return end;
        }
        while (end > start) {
            char ch = text.charAt(end - 1);
            if (ch == '\n') {
                return end - 1;
            }
            if (!TextLine.isLineEndSpace(ch)) {
                break;
            }
            end--;
        }
        return end;
    }

    public final int getLineBottom(int line) {
        return getLineTop(line + 1);
    }

    public final int getLineBottomWithoutSpacing(int line) {
        return getLineTop(line + 1) - getLineExtra(line);
    }

    public final int getLineBaseline(int line) {
        return getLineTop(line + 1) - getLineDescent(line);
    }

    public final int getLineAscent(int line) {
        return getLineTop(line) - (getLineTop(line + 1) - getLineDescent(line));
    }

    public int getLineExtra(int line) {
        return 0;
    }

    public int getOffsetToLeftOf(int offset) {
        return getOffsetToLeftRightOf(offset, true);
    }

    public int getOffsetToRightOf(int offset) {
        return getOffsetToLeftRightOf(offset, false);
    }

    private int getOffsetToLeftRightOf(int caret, boolean toLeft) {
        boolean toLeft2 = toLeft;
        int line = getLineForOffset(caret);
        int lineStart = getLineStart(line);
        int lineEnd = getLineEnd(line);
        int lineDir = getParagraphDirection(line);
        boolean lineChanged = false;
        boolean advance = false;
        if (toLeft2 == (lineDir == -1)) {
            advance = true;
        }
        if (advance) {
            if (caret == lineEnd) {
                if (line >= getLineCount() - 1) {
                    return caret;
                }
                lineChanged = true;
                line++;
            }
        } else if (caret == lineStart) {
            if (line <= 0) {
                return caret;
            }
            lineChanged = true;
            line--;
        }
        if (lineChanged) {
            lineStart = getLineStart(line);
            lineEnd = getLineEnd(line);
            int newDir = getParagraphDirection(line);
            if (newDir != lineDir) {
                toLeft2 = !toLeft2;
                lineDir = newDir;
            }
        }
        Directions directions = getLineDirections(line);
        TextLine tl = TextLine.obtain();
        tl.set(this.mPaint, this.mText, lineStart, lineEnd, lineDir, directions, false, null, getEllipsisStart(line), getEllipsisStart(line) + getEllipsisCount(line));
        int caret2 = tl.getOffsetToLeftRightOf(caret - lineStart, toLeft2) + lineStart;
        TextLine.recycle(tl);
        return caret2;
    }

    private int getOffsetAtStartOf(int offset) {
        char c1;
        if (offset == 0) {
            return 0;
        }
        CharSequence text = this.mText;
        char c = text.charAt(offset);
        if (c >= 56320 && c <= 57343 && (c1 = text.charAt(offset - 1)) >= 55296 && c1 <= 56319) {
            offset--;
        }
        if (this.mSpannedText) {
            ReplacementSpan[] spans = (ReplacementSpan[]) ((Spanned) text).getSpans(offset, offset, ReplacementSpan.class);
            for (int i = 0; i < spans.length; i++) {
                int start = ((Spanned) text).getSpanStart(spans[i]);
                int end = ((Spanned) text).getSpanEnd(spans[i]);
                if (start < offset && end > offset) {
                    offset = start;
                }
            }
        }
        return offset;
    }

    public boolean shouldClampCursor(int line) {
        switch (AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[getParagraphAlignment(line).ordinal()]) {
            case 1:
                return getParagraphDirection(line) > 0;
            case 5:
                return true;
            default:
                return false;
        }
    }

    public void getCursorPath(int point, Path dest, CharSequence editingBuffer) {
        dest.reset();
        int line = getLineForOffset(point);
        int top = getLineTop(line);
        int bottom = getLineBottomWithoutSpacing(line);
        boolean clamped = shouldClampCursor(line);
        float h1 = getPrimaryHorizontal(point, clamped) - 0.5f;
        int caps = TextKeyListener.getMetaState(editingBuffer, 1) | TextKeyListener.getMetaState(editingBuffer, 2048);
        int fn = TextKeyListener.getMetaState(editingBuffer, 2);
        int dist = 0;
        if (!(caps == 0 && fn == 0)) {
            dist = (bottom - top) >> 2;
            if (fn != 0) {
                top += dist;
            }
            if (caps != 0) {
                bottom -= dist;
            }
        }
        if (h1 < 0.5f) {
            h1 = 0.5f;
        }
        dest.moveTo(h1, top);
        dest.lineTo(h1, bottom);
        if (caps == 2) {
            dest.moveTo(h1, bottom);
            dest.lineTo(h1 - dist, bottom + dist);
            dest.lineTo(h1, bottom);
            dest.lineTo(dist + h1, bottom + dist);
        } else if (caps == 1) {
            dest.moveTo(h1, bottom);
            dest.lineTo(h1 - dist, bottom + dist);
            dest.moveTo(h1 - dist, (bottom + dist) - 0.5f);
            dest.lineTo(dist + h1, (bottom + dist) - 0.5f);
            dest.moveTo(dist + h1, bottom + dist);
            dest.lineTo(h1, bottom);
        }
        if (fn == 2) {
            dest.moveTo(h1, top);
            dest.lineTo(h1 - dist, top - dist);
            dest.lineTo(h1, top);
            dest.lineTo(dist + h1, top - dist);
        } else if (fn == 1) {
            dest.moveTo(h1, top);
            dest.lineTo(h1 - dist, top - dist);
            dest.moveTo(h1 - dist, (top - dist) + 0.5f);
            dest.lineTo(dist + h1, (top - dist) + 0.5f);
            dest.moveTo(dist + h1, top - dist);
            dest.lineTo(h1, top);
        }
    }

    private void addSelection(int line, int start, int end, int top, int bottom, SelectionRectangleConsumer consumer) {
        int layout;
        Layout layout2 = this;
        int i = line;
        int linestart = getLineStart(line);
        int lineend = getLineEnd(line);
        Directions dirs = getLineDirections(line);
        if (lineend > linestart && layout2.mText.charAt(lineend - 1) == '\n') {
            lineend--;
        }
        int i2 = 0;
        while (i2 < dirs.mDirections.length) {
            int here = dirs.mDirections[i2] + linestart;
            int there = (dirs.mDirections[i2 + 1] & RUN_LENGTH_MASK) + here;
            if (there > lineend) {
                there = lineend;
            }
            if (start <= there && end >= here) {
                int st = Math.max(start, here);
                int en = Math.min(end, there);
                if (st != en) {
                    float h1 = layout2.getHorizontal(st, false, i, false);
                    float h2 = layout2.getHorizontal(en, true, i, false);
                    float left = Math.min(h1, h2);
                    float right = Math.max(h1, h2);
                    if ((dirs.mDirections[i2 + 1] & 67108864) != 0) {
                        layout = 0;
                    } else {
                        layout = 1;
                    }
                    consumer.accept(left, top, right, bottom, layout);
                }
            }
            i2 += 2;
            layout2 = this;
            i = line;
        }
    }

    public void getSelectionPath(int start, int end, final Path dest) {
        dest.reset();
        getSelection(start, end, new SelectionRectangleConsumer() { // from class: android.text.Layout$$ExternalSyntheticLambda0
            @Override // android.text.Layout.SelectionRectangleConsumer
            public final void accept(float f, float f2, float f3, float f4, int i) {
                Path.this.addRect(f, f2, f3, f4, Path.Direction.CW);
            }
        });
    }

    public final void getSelection(int start, int end, SelectionRectangleConsumer consumer) {
        int end2;
        int start2;
        if (start != end) {
            if (end < start) {
                start2 = end;
                end2 = start;
            } else {
                start2 = start;
                end2 = end;
            }
            int startline = getLineForOffset(start2);
            int endline = getLineForOffset(end2);
            int top = getLineTop(startline);
            int bottom = getLineBottomWithoutSpacing(endline);
            if (startline == endline) {
                addSelection(startline, start2, end2, top, bottom, consumer);
                return;
            }
            float width = this.mWidth;
            addSelection(startline, start2, getLineEnd(startline), top, getLineBottom(startline), consumer);
            if (getParagraphDirection(startline) == -1) {
                consumer.accept(getLineLeft(startline), top, 0.0f, getLineBottom(startline), 0);
            } else {
                consumer.accept(getLineRight(startline), top, width, getLineBottom(startline), 1);
            }
            for (int i = startline + 1; i < endline; i++) {
                int top2 = getLineTop(i);
                int bottom2 = getLineBottom(i);
                if (getParagraphDirection(i) == -1) {
                    consumer.accept(0.0f, top2, width, bottom2, 0);
                } else {
                    consumer.accept(0.0f, top2, width, bottom2, 1);
                }
            }
            int top3 = getLineTop(endline);
            int bottom3 = getLineBottomWithoutSpacing(endline);
            addSelection(endline, getLineStart(endline), end2, top3, bottom3, consumer);
            if (getParagraphDirection(endline) == -1) {
                consumer.accept(width, top3, getLineRight(endline), bottom3, 0);
            } else {
                consumer.accept(0.0f, top3, getLineLeft(endline), bottom3, 1);
            }
        }
    }

    public final Alignment getParagraphAlignment(int line) {
        Alignment align = this.mAlignment;
        if (!this.mSpannedText) {
            return align;
        }
        Spanned sp = (Spanned) this.mText;
        AlignmentSpan[] spans = (AlignmentSpan[]) getParagraphSpans(sp, getLineStart(line), getLineEnd(line), AlignmentSpan.class);
        int spanLength = spans.length;
        if (spanLength > 0) {
            return spans[spanLength - 1].getAlignment();
        }
        return align;
    }

    public final int getParagraphLeft(int line) {
        int dir = getParagraphDirection(line);
        if (dir == -1 || !this.mSpannedText) {
            return 0;
        }
        return getParagraphLeadingMargin(line);
    }

    public final int getParagraphRight(int line) {
        int right = this.mWidth;
        int dir = getParagraphDirection(line);
        if (dir == 1 || !this.mSpannedText) {
            return right;
        }
        return right - getParagraphLeadingMargin(line);
    }

    private int getParagraphLeadingMargin(int line) {
        if (!this.mSpannedText) {
            return 0;
        }
        Spanned spanned = (Spanned) this.mText;
        int lineStart = getLineStart(line);
        int lineEnd = getLineEnd(line);
        int spanEnd = spanned.nextSpanTransition(lineStart, lineEnd, LeadingMarginSpan.class);
        LeadingMarginSpan[] spans = (LeadingMarginSpan[]) getParagraphSpans(spanned, lineStart, spanEnd, LeadingMarginSpan.class);
        if (spans.length == 0) {
            return 0;
        }
        int margin = 0;
        boolean useFirstLineMargin = lineStart == 0 || spanned.charAt(lineStart + (-1)) == '\n';
        for (int i = 0; i < spans.length; i++) {
            if (spans[i] instanceof LeadingMarginSpan.LeadingMarginSpan2) {
                int spStart = spanned.getSpanStart(spans[i]);
                int spanLine = getLineForOffset(spStart);
                int count = ((LeadingMarginSpan.LeadingMarginSpan2) spans[i]).getLeadingMarginLineCount();
                useFirstLineMargin |= line < spanLine + count;
            }
        }
        for (LeadingMarginSpan span : spans) {
            margin += span.getLeadingMargin(useFirstLineMargin);
        }
        return margin;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0069, code lost:
        if ((r24 instanceof android.text.Spanned) == false) goto L_0x0093;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
        r4 = (android.text.Spanned) r24;
        r5 = r4.nextSpanTransition(r25, r26, android.text.style.TabStopSpan.class);
        r0 = (android.text.style.TabStopSpan[]) getParagraphSpans(r4, r25, r5, android.text.style.TabStopSpan.class);
        r17 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0081, code lost:
        if (r0.length <= 0) goto L_0x008e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008c, code lost:
        r3 = new android.text.Layout.TabStops(android.text.Layout.TAB_INCREMENT, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
        r18 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
        r17 = true;
        r18 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static float measurePara(android.text.TextPaint r23, java.lang.CharSequence r24, int r25, int r26, android.text.TextDirectionHeuristic r27) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.measurePara(android.text.TextPaint, java.lang.CharSequence, int, int, android.text.TextDirectionHeuristic):float");
    }

    /* loaded from: classes3.dex */
    public static class TabStops {
        private float mIncrement;
        private int mNumStops;
        private float[] mStops;

        public TabStops(float increment, Object[] spans) {
            reset(increment, spans);
        }

        void reset(float increment, Object[] spans) {
            this.mIncrement = increment;
            int ns = 0;
            if (spans != null) {
                float[] stops = this.mStops;
                for (Object o : spans) {
                    if (o instanceof TabStopSpan) {
                        if (stops == null) {
                            stops = new float[10];
                        } else if (ns == stops.length) {
                            float[] nstops = new float[ns * 2];
                            for (int i = 0; i < ns; i++) {
                                nstops[i] = stops[i];
                            }
                            stops = nstops;
                        }
                        stops[ns] = ((TabStopSpan) o).getTabStop();
                        ns++;
                    }
                }
                if (ns > 1) {
                    Arrays.sort(stops, 0, ns);
                }
                if (stops != this.mStops) {
                    this.mStops = stops;
                }
            }
            this.mNumStops = ns;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public float nextTab(float h) {
            int ns = this.mNumStops;
            if (ns > 0) {
                float[] stops = this.mStops;
                for (int i = 0; i < ns; i++) {
                    float stop = stops[i];
                    if (stop > h) {
                        return stop;
                    }
                }
            }
            return nextDefaultStop(h, this.mIncrement);
        }

        public static float nextDefaultStop(float h, float inc) {
            return ((int) ((h + inc) / inc)) * inc;
        }
    }

    static float nextTab(CharSequence text, int start, int end, float h, Object[] tabs) {
        float nh = Float.MAX_VALUE;
        boolean alltabs = false;
        if (text instanceof Spanned) {
            if (tabs == null) {
                tabs = getParagraphSpans((Spanned) text, start, end, TabStopSpan.class);
                alltabs = true;
            }
            for (int i = 0; i < tabs.length; i++) {
                if (alltabs || (tabs[i] instanceof TabStopSpan)) {
                    int where = ((TabStopSpan) tabs[i]).getTabStop();
                    if (where < nh && where > h) {
                        nh = where;
                    }
                }
            }
            if (nh != Float.MAX_VALUE) {
                return nh;
            }
        }
        return ((int) ((h + TAB_INCREMENT) / TAB_INCREMENT)) * TAB_INCREMENT;
    }

    protected final boolean isSpanned() {
        return this.mSpannedText;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] getParagraphSpans(Spanned text, int start, int end, Class<T> type) {
        if (start == end && start > 0) {
            return (T[]) ArrayUtils.emptyArray(type);
        }
        if (text instanceof SpannableStringBuilder) {
            return (T[]) ((SpannableStringBuilder) text).getSpans(start, end, type, false);
        }
        return (T[]) text.getSpans(start, end, type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ellipsize(int start, int end, int line, char[] dest, int destoff, TextUtils.TruncateAt method) {
        char c;
        int ellipsisCount = getEllipsisCount(line);
        if (ellipsisCount != 0) {
            int ellipsisStart = getEllipsisStart(line);
            int lineStart = getLineStart(line);
            String ellipsisString = TextUtils.getEllipsisString(method);
            int ellipsisStringLen = ellipsisString.length();
            boolean useEllipsisString = ellipsisCount >= ellipsisStringLen;
            int min = Math.max(0, (start - ellipsisStart) - lineStart);
            int max = Math.min(ellipsisCount, (end - ellipsisStart) - lineStart);
            for (int i = min; i < max; i++) {
                if (!useEllipsisString || i >= ellipsisStringLen) {
                    c = 65279;
                } else {
                    c = ellipsisString.charAt(i);
                }
                int a = i + ellipsisStart + lineStart;
                dest[(destoff + a) - start] = c;
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class Directions {
        public int[] mDirections;

        public Directions(int[] dirs) {
            this.mDirections = dirs;
        }

        public int getRunCount() {
            return this.mDirections.length / 2;
        }

        public int getRunStart(int runIndex) {
            return this.mDirections[runIndex * 2];
        }

        public int getRunLength(int runIndex) {
            return this.mDirections[(runIndex * 2) + 1] & Layout.RUN_LENGTH_MASK;
        }

        public boolean isRunRtl(int runIndex) {
            return (this.mDirections[(runIndex * 2) + 1] & 67108864) != 0;
        }
    }

    /* loaded from: classes3.dex */
    static class Ellipsizer implements CharSequence, GetChars {
        Layout mLayout;
        TextUtils.TruncateAt mMethod;
        CharSequence mText;
        int mWidth;

        public Ellipsizer(CharSequence s) {
            this.mText = s;
        }

        @Override // java.lang.CharSequence
        public char charAt(int off) {
            char[] buf = TextUtils.obtain(1);
            getChars(off, off + 1, buf, 0);
            char ret = buf[0];
            TextUtils.recycle(buf);
            return ret;
        }

        @Override // android.text.GetChars
        public void getChars(int start, int end, char[] dest, int destoff) {
            int line1 = this.mLayout.getLineForOffset(start);
            int line2 = this.mLayout.getLineForOffset(end);
            TextUtils.getChars(this.mText, start, end, dest, destoff);
            for (int i = line1; i <= line2; i++) {
                this.mLayout.ellipsize(start, end, i, dest, destoff, this.mMethod);
            }
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.mText.length();
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int start, int end) {
            char[] s = new char[end - start];
            getChars(start, end, s, 0);
            return new String(s);
        }

        @Override // java.lang.CharSequence
        public String toString() {
            char[] s = new char[length()];
            getChars(0, length(), s, 0);
            return new String(s);
        }
    }

    /* loaded from: classes3.dex */
    static class SpannedEllipsizer extends Ellipsizer implements Spanned {
        private Spanned mSpanned;

        public SpannedEllipsizer(CharSequence display) {
            super(display);
            this.mSpanned = (Spanned) display;
        }

        @Override // android.text.Spanned
        public <T> T[] getSpans(int start, int end, Class<T> type) {
            return (T[]) this.mSpanned.getSpans(start, end, type);
        }

        @Override // android.text.Spanned
        public int getSpanStart(Object tag) {
            return this.mSpanned.getSpanStart(tag);
        }

        @Override // android.text.Spanned
        public int getSpanEnd(Object tag) {
            return this.mSpanned.getSpanEnd(tag);
        }

        @Override // android.text.Spanned
        public int getSpanFlags(Object tag) {
            return this.mSpanned.getSpanFlags(tag);
        }

        @Override // android.text.Spanned
        public int nextSpanTransition(int start, int limit, Class type) {
            return this.mSpanned.nextSpanTransition(start, limit, type);
        }

        @Override // android.text.Layout.Ellipsizer, java.lang.CharSequence
        public CharSequence subSequence(int start, int end) {
            char[] s = new char[end - start];
            getChars(start, end, s, 0);
            SpannableString ss = new SpannableString(new String(s));
            TextUtils.copySpansFrom(this.mSpanned, start, end, Object.class, ss, 0);
            return ss;
        }
    }
}
