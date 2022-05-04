package android.graphics.text;

import android.graphics.Paint;
import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import libcore.util.NativeAllocationRegistry;
/* loaded from: classes.dex */
public class MeasuredText {
    private char[] mChars;
    private boolean mComputeHyphenation;
    private boolean mComputeLayout;
    private long mNativePtr;

    private static native void nGetBounds(long j, char[] cArr, int i, int i2, Rect rect);

    @CriticalNative
    private static native float nGetCharWidthAt(long j, int i);

    @CriticalNative
    private static native int nGetMemoryUsage(long j);

    /* JADX INFO: Access modifiers changed from: private */
    @CriticalNative
    public static native long nGetReleaseFunc();

    @CriticalNative
    private static native float nGetWidth(long j, int i, int i2);

    private MeasuredText(long ptr, char[] chars, boolean computeHyphenation, boolean computeLayout) {
        this.mNativePtr = ptr;
        this.mChars = chars;
        this.mComputeHyphenation = computeHyphenation;
        this.mComputeLayout = computeLayout;
    }

    public char[] getChars() {
        return this.mChars;
    }

    public float getWidth(int start, int end) {
        Preconditions.checkArgument(start >= 0 && start <= this.mChars.length, "start(%d) must be 0 <= start <= %d", Integer.valueOf(start), Integer.valueOf(this.mChars.length));
        Preconditions.checkArgument(end >= 0 && end <= this.mChars.length, "end(%d) must be 0 <= end <= %d", Integer.valueOf(end), Integer.valueOf(this.mChars.length));
        Preconditions.checkArgument(start <= end, "start(%d) is larger than end(%d)", Integer.valueOf(start), Integer.valueOf(end));
        return nGetWidth(this.mNativePtr, start, end);
    }

    public int getMemoryUsage() {
        return nGetMemoryUsage(this.mNativePtr);
    }

    public void getBounds(int start, int end, Rect rect) {
        Preconditions.checkArgument(start >= 0 && start <= this.mChars.length, "start(%d) must be 0 <= start <= %d", Integer.valueOf(start), Integer.valueOf(this.mChars.length));
        Preconditions.checkArgument(end >= 0 && end <= this.mChars.length, "end(%d) must be 0 <= end <= %d", Integer.valueOf(end), Integer.valueOf(this.mChars.length));
        Preconditions.checkArgument(start <= end, "start(%d) is larger than end(%d)", Integer.valueOf(start), Integer.valueOf(end));
        Preconditions.checkNotNull(rect);
        nGetBounds(this.mNativePtr, this.mChars, start, end, rect);
    }

    public float getCharWidthAt(int offset) {
        boolean z = offset >= 0 && offset < this.mChars.length;
        Preconditions.checkArgument(z, "offset(%d) is larger than text length %d" + offset, Integer.valueOf(this.mChars.length));
        return nGetCharWidthAt(this.mNativePtr, offset);
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(MeasuredText.class.getClassLoader(), MeasuredText.nGetReleaseFunc());
        private boolean mComputeHyphenation;
        private boolean mComputeLayout;
        private int mCurrentOffset;
        private MeasuredText mHintMt;
        private long mNativePtr;
        private final char[] mText;

        private static native void nAddReplacementRun(long j, long j2, int i, int i2, float f);

        private static native void nAddStyleRun(long j, long j2, int i, int i2, boolean z);

        private static native long nBuildMeasuredText(long j, long j2, char[] cArr, boolean z, boolean z2);

        private static native void nFreeBuilder(long j);

        private static native long nInitBuilder();

        public Builder(char[] text) {
            this.mComputeHyphenation = false;
            this.mComputeLayout = true;
            this.mCurrentOffset = 0;
            this.mHintMt = null;
            Preconditions.checkNotNull(text);
            this.mText = text;
            this.mNativePtr = nInitBuilder();
        }

        public Builder(MeasuredText text) {
            this.mComputeHyphenation = false;
            this.mComputeLayout = true;
            this.mCurrentOffset = 0;
            this.mHintMt = null;
            Preconditions.checkNotNull(text);
            this.mText = text.mChars;
            this.mNativePtr = nInitBuilder();
            if (text.mComputeLayout) {
                this.mComputeHyphenation = text.mComputeHyphenation;
                this.mComputeLayout = text.mComputeLayout;
                this.mHintMt = text;
                return;
            }
            throw new IllegalArgumentException("The input MeasuredText must not be created with setComputeLayout(false).");
        }

        public Builder appendStyleRun(Paint paint, int length, boolean isRtl) {
            Preconditions.checkNotNull(paint);
            boolean z = true;
            Preconditions.checkArgument(length > 0, "length can not be negative");
            int end = this.mCurrentOffset + length;
            if (end > this.mText.length) {
                z = false;
            }
            Preconditions.checkArgument(z, "Style exceeds the text length");
            nAddStyleRun(this.mNativePtr, paint.getNativeInstance(), this.mCurrentOffset, end, isRtl);
            this.mCurrentOffset = end;
            return this;
        }

        public Builder appendReplacementRun(Paint paint, int length, float width) {
            boolean z = true;
            Preconditions.checkArgument(length > 0, "length can not be negative");
            int end = this.mCurrentOffset + length;
            if (end > this.mText.length) {
                z = false;
            }
            Preconditions.checkArgument(z, "Replacement exceeds the text length");
            nAddReplacementRun(this.mNativePtr, paint.getNativeInstance(), this.mCurrentOffset, end, width);
            this.mCurrentOffset = end;
            return this;
        }

        public Builder setComputeHyphenation(boolean computeHyphenation) {
            this.mComputeHyphenation = computeHyphenation;
            return this;
        }

        public Builder setComputeLayout(boolean computeLayout) {
            this.mComputeLayout = computeLayout;
            return this;
        }

        public MeasuredText build() {
            ensureNativePtrNoReuse();
            if (this.mCurrentOffset == this.mText.length) {
                MeasuredText measuredText = this.mHintMt;
                if (measuredText == null || measuredText.mComputeHyphenation == this.mComputeHyphenation) {
                    try {
                        MeasuredText measuredText2 = this.mHintMt;
                        long hintPtr = measuredText2 == null ? 0L : measuredText2.getNativePtr();
                        long ptr = nBuildMeasuredText(this.mNativePtr, hintPtr, this.mText, this.mComputeHyphenation, this.mComputeLayout);
                        MeasuredText res = new MeasuredText(ptr, this.mText, this.mComputeHyphenation, this.mComputeLayout);
                        sRegistry.registerNativeAllocation(res, ptr);
                        return res;
                    } finally {
                        nFreeBuilder(this.mNativePtr);
                        this.mNativePtr = 0L;
                    }
                } else {
                    throw new IllegalArgumentException("The hyphenation configuration is different from given hint MeasuredText");
                }
            } else {
                throw new IllegalStateException("Style info has not been provided for all text.");
            }
        }

        private void ensureNativePtrNoReuse() {
            if (this.mNativePtr == 0) {
                throw new IllegalStateException("Builder can not be reused.");
            }
        }
    }
}
