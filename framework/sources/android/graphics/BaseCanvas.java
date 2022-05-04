package android.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.IBaseCanvasExt;
import android.graphics.PorterDuff;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;
import android.text.GraphicsOperations;
import android.text.MeasuredParagraph;
import android.text.PrecomputedText;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class BaseCanvas {
    protected long mNativeCanvasWrapper;
    protected int mScreenDensity = 0;
    protected int mDensity = 0;
    private boolean mAllowHwBitmapsInSwMode = false;
    public IBaseCanvasExt mBaseCanvasExt = BaseCanvasExtPlugin.constructor.newInstance();

    private static native void nDrawArc(long j, float f, float f2, float f3, float f4, float f5, float f6, boolean z, long j2);

    private static native void nDrawBitmap(long j, long j2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, long j3, int i, int i2);

    private static native void nDrawBitmap(long j, long j2, float f, float f2, long j3, int i, int i2, int i3);

    private static native void nDrawBitmap(long j, int[] iArr, int i, int i2, float f, float f2, int i3, int i4, boolean z, long j2);

    private static native void nDrawBitmapMatrix(long j, long j2, long j3, long j4);

    private static native void nDrawBitmapMesh(long j, long j2, int i, int i2, float[] fArr, int i3, int[] iArr, int i4, long j3);

    private static native void nDrawCircle(long j, float f, float f2, float f3, long j2);

    private static native void nDrawColor(long j, int i, int i2);

    private static native void nDrawColor(long j, long j2, long j3, int i);

    private static native void nDrawDoubleRoundRect(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, long j2);

    private static native void nDrawDoubleRoundRect(long j, float f, float f2, float f3, float f4, float[] fArr, float f5, float f6, float f7, float f8, float[] fArr2, long j2);

    private static native void nDrawGlyphs(long j, int[] iArr, float[] fArr, int i, int i2, int i3, long j2, long j3);

    private static native void nDrawLine(long j, float f, float f2, float f3, float f4, long j2);

    private static native void nDrawLines(long j, float[] fArr, int i, int i2, long j2);

    private static native void nDrawNinePatch(long j, long j2, long j3, float f, float f2, float f3, float f4, long j4, int i, int i2);

    private static native void nDrawOval(long j, float f, float f2, float f3, float f4, long j2);

    private static native void nDrawPaint(long j, long j2);

    private static native void nDrawPath(long j, long j2, long j3);

    private static native void nDrawPoint(long j, float f, float f2, long j2);

    private static native void nDrawPoints(long j, float[] fArr, int i, int i2, long j2);

    private static native void nDrawRect(long j, float f, float f2, float f3, float f4, long j2);

    private static native void nDrawRegion(long j, long j2, long j3);

    private static native void nDrawRoundRect(long j, float f, float f2, float f3, float f4, float f5, float f6, long j2);

    private static native void nDrawText(long j, String str, int i, int i2, float f, float f2, int i3, long j2);

    private static native void nDrawText(long j, char[] cArr, int i, int i2, float f, float f2, int i3, long j2);

    private static native void nDrawTextOnPath(long j, String str, long j2, float f, float f2, int i, long j3);

    private static native void nDrawTextOnPath(long j, char[] cArr, int i, int i2, long j2, float f, float f2, int i3, long j3);

    private static native void nDrawTextRun(long j, String str, int i, int i2, int i3, int i4, float f, float f2, boolean z, long j2);

    private static native void nDrawTextRun(long j, char[] cArr, int i, int i2, int i3, int i4, float f, float f2, boolean z, long j2, long j3);

    private static native void nDrawVertices(long j, int i, int i2, float[] fArr, int i3, float[] fArr2, int i4, int[] iArr, int i5, short[] sArr, int i6, int i7, long j2);

    private static native void nPunchHole(long j, float f, float f2, float f3, float f4, float f5, float f6);

    /* JADX INFO: Access modifiers changed from: protected */
    public void throwIfCannotDraw(Bitmap bitmap) {
        if (bitmap.isRecycled()) {
            throw new RuntimeException("Canvas: trying to use a recycled bitmap " + bitmap);
        } else if (bitmap.isPremultiplied() || bitmap.getConfig() != Bitmap.Config.ARGB_8888 || !bitmap.hasAlpha()) {
            throwIfHwBitmapInSwMode(bitmap);
        } else {
            throw new RuntimeException("Canvas: trying to use a non-premultiplied bitmap " + bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final void checkRange(int length, int offset, int count) {
        if ((offset | count) < 0 || offset + count > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean isHardwareAccelerated() {
        return false;
    }

    public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(left, top, right, bottom));
        nDrawArc(this.mNativeCanvasWrapper, left, top, right, bottom, startAngle, sweepAngle, useCenter, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        drawArc(oval.left, oval.top, oval.right, oval.bottom, startAngle, sweepAngle, useCenter, paint);
    }

    public void drawARGB(int a, int r, int g, int b) {
        drawColor(Color.argb(a, r, g, b));
    }

    public void drawBitmap(Bitmap bitmap, float left, float top, Paint paint) {
        Paint paint2 = paint;
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint2);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeBitmap(paint2, bitmap, iBaseCanvasExt.getRectF(bitmap.getWidth(), bitmap.getHeight()));
        if (entity != null && entity.isDarkMode) {
            paint2 = entity.newPaint;
        }
        nDrawBitmap(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), left, top, paint2 != null ? paint2.getNativeInstance() : 0L, this.mDensity, this.mScreenDensity, bitmap.mDensity);
        this.mBaseCanvasExt.resetEntity(entity, paint2);
    }

    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeBitmap(paint, bitmap, iBaseCanvasExt.getRectF(bitmap.getWidth(), bitmap.getHeight()));
        if (entity != null && entity.isDarkMode) {
            paint = entity.newPaint;
        }
        nDrawBitmapMatrix(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), matrix.ni(), paint != null ? paint.getNativeInstance() : 0L);
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint) {
        int right;
        int bottom;
        int top;
        int left;
        Paint paint2 = paint;
        if (dst != null) {
            throwIfCannotDraw(bitmap);
            throwIfHasHwBitmapInSwMode(paint2);
            IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
            IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeBitmap(paint2, bitmap, iBaseCanvasExt.getRectF(dst));
            if (entity != null && entity.isDarkMode) {
                paint2 = entity.newPaint;
            }
            long nativePaint = paint2 == null ? 0L : paint2.getNativeInstance();
            if (src == null) {
                left = 0;
                top = 0;
                int right2 = bitmap.getWidth();
                right = right2;
                bottom = bitmap.getHeight();
            } else {
                left = src.left;
                int right3 = src.right;
                top = src.top;
                right = right3;
                bottom = src.bottom;
            }
            int top2 = dst.left;
            int bottom2 = dst.top;
            nDrawBitmap(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), left, top, right, bottom, top2, bottom2, dst.right, dst.bottom, nativePaint, this.mScreenDensity, bitmap.mDensity);
            this.mBaseCanvasExt.resetEntity(entity, paint2);
            return;
        }
        throw new NullPointerException();
    }

    public void drawBitmap(Bitmap bitmap, Rect src, RectF dst, Paint paint) {
        float bottom;
        float right;
        float top;
        float left;
        Paint paint2 = paint;
        if (dst != null) {
            throwIfCannotDraw(bitmap);
            throwIfHasHwBitmapInSwMode(paint2);
            IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeBitmap(paint2, bitmap, dst);
            if (entity != null && entity.isDarkMode) {
                paint2 = entity.newPaint;
            }
            long nativePaint = paint2 == null ? 0L : paint2.getNativeInstance();
            if (src == null) {
                left = 0.0f;
                top = 0.0f;
                float right2 = bitmap.getWidth();
                bottom = bitmap.getHeight();
                right = right2;
            } else {
                left = src.left;
                float right3 = src.right;
                top = src.top;
                bottom = src.bottom;
                right = right3;
            }
            nDrawBitmap(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), left, top, right, bottom, dst.left, dst.top, dst.right, dst.bottom, nativePaint, this.mScreenDensity, bitmap.mDensity);
            this.mBaseCanvasExt.resetEntity(entity, paint2);
            return;
        }
        throw new NullPointerException();
    }

    @Deprecated
    public void drawBitmap(int[] colors, int offset, int stride, float x, float y, int width, int height, boolean hasAlpha, Paint paint) {
        if (width < 0) {
            throw new IllegalArgumentException("width must be >= 0");
        } else if (height < 0) {
            throw new IllegalArgumentException("height must be >= 0");
        } else if (Math.abs(stride) >= width) {
            int lastScanline = offset + ((height - 1) * stride);
            int length = colors.length;
            if (offset < 0 || offset + width > length || lastScanline < 0 || lastScanline + width > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            throwIfHasHwBitmapInSwMode(paint);
            if (!(width == 0 || height == 0)) {
                nDrawBitmap(this.mNativeCanvasWrapper, this.mBaseCanvasExt.changeColors(colors), offset, stride, x, y, width, height, hasAlpha, paint != null ? paint.getNativeInstance() : 0L);
            }
        } else {
            throw new IllegalArgumentException("abs(stride) must be >= width");
        }
    }

    @Deprecated
    public void drawBitmap(int[] colors, int offset, int stride, int x, int y, int width, int height, boolean hasAlpha, Paint paint) {
        drawBitmap(colors, offset, stride, x, y, width, height, hasAlpha, paint);
    }

    public void drawBitmapMesh(Bitmap bitmap, int meshWidth, int meshHeight, float[] verts, int vertOffset, int[] colors, int colorOffset, Paint paint) {
        if ((meshWidth | meshHeight | vertOffset | colorOffset) >= 0) {
            throwIfHasHwBitmapInSwMode(paint);
            if (meshWidth != 0 && meshHeight != 0) {
                int count = (meshWidth + 1) * (meshHeight + 1);
                checkRange(verts.length, vertOffset, count * 2);
                if (colors != null) {
                    checkRange(colors.length, colorOffset, count);
                }
                IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
                IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeBitmap(paint, bitmap, iBaseCanvasExt.getRectF(bitmap.getWidth(), bitmap.getHeight()));
                Paint paint2 = (entity == null || !entity.isDarkMode) ? paint : entity.newPaint;
                nDrawBitmapMesh(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), meshWidth, meshHeight, verts, vertOffset, colors, colorOffset, paint2 != null ? paint2.getNativeInstance() : 0L);
                this.mBaseCanvasExt.resetEntity(entity, paint2);
                return;
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void drawCircle(float cx, float cy, float radius, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(radius * 2.0f, 2.0f * radius));
        nDrawCircle(this.mNativeCanvasWrapper, cx, cy, radius, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawColor(int color) {
        nDrawColor(this.mNativeCanvasWrapper, this.mBaseCanvasExt.changeColor(color), BlendMode.SRC_OVER.getXfermode().porterDuffMode);
    }

    public void drawColor(int color, PorterDuff.Mode mode) {
        nDrawColor(this.mNativeCanvasWrapper, this.mBaseCanvasExt.changeColor(color), mode.nativeInt);
    }

    public void drawColor(int color, BlendMode mode) {
        nDrawColor(this.mNativeCanvasWrapper, color, mode.getXfermode().porterDuffMode);
    }

    public void drawColor(long color, BlendMode mode) {
        ColorSpace cs = Color.colorSpace(color);
        nDrawColor(this.mNativeCanvasWrapper, cs.getNativeInstance(), color, mode.getXfermode().porterDuffMode);
    }

    public void drawLine(float startX, float startY, float stopX, float stopY, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(startX, startY, stopX, stopY));
        nDrawLine(this.mNativeCanvasWrapper, startX, startY, stopX, stopY, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawLines(float[] pts, int offset, int count, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeArea(paint, null);
        nDrawLines(this.mNativeCanvasWrapper, pts, offset, count, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawLines(float[] pts, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        drawLines(pts, 0, pts.length, paint);
    }

    public void drawOval(float left, float top, float right, float bottom, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(left, top, right, bottom));
        nDrawOval(this.mNativeCanvasWrapper, left, top, right, bottom, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawOval(RectF oval, Paint paint) {
        if (oval != null) {
            throwIfHasHwBitmapInSwMode(paint);
            drawOval(oval.left, oval.top, oval.right, oval.bottom, paint);
            return;
        }
        throw new NullPointerException();
    }

    public void drawPaint(Paint paint) {
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(iBaseCanvasExt.getWidth(), this.mBaseCanvasExt.getHeight()));
        nDrawPaint(this.mNativeCanvasWrapper, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawPatch(NinePatch patch, Rect dst, Paint paint) {
        Paint paint2 = paint;
        Bitmap bitmap = patch.getBitmap();
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint2);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changePatch(patch, paint2, iBaseCanvasExt.getRectF(dst));
        if (entity != null && entity.isDarkMode) {
            paint2 = entity.newPaint;
        }
        long nativePaint = paint2 == null ? 0L : paint2.getNativeInstance();
        nDrawNinePatch(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), patch.mNativeChunk, dst.left, dst.top, dst.right, dst.bottom, nativePaint, this.mDensity, patch.getDensity());
        this.mBaseCanvasExt.resetEntity(entity, paint2);
    }

    public void drawPatch(NinePatch patch, RectF dst, Paint paint) {
        Paint paint2 = paint;
        Bitmap bitmap = patch.getBitmap();
        throwIfCannotDraw(bitmap);
        throwIfHasHwBitmapInSwMode(paint2);
        IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changePatch(patch, paint2, dst);
        if (entity != null && entity.isDarkMode) {
            paint2 = entity.newPaint;
        }
        long nativePaint = paint2 == null ? 0L : paint2.getNativeInstance();
        nDrawNinePatch(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), patch.mNativeChunk, dst.left, dst.top, dst.right, dst.bottom, nativePaint, this.mDensity, patch.getDensity());
        this.mBaseCanvasExt.resetEntity(entity, paint2);
    }

    public void drawPath(Path path, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(iBaseCanvasExt.getWidth(), this.mBaseCanvasExt.getHeight()), path);
        if (!path.isSimplePath || path.rects == null) {
            nDrawPath(this.mNativeCanvasWrapper, path.readOnlyNI(), paint.getNativeInstance());
        } else {
            nDrawRegion(this.mNativeCanvasWrapper, path.rects.mNativeRegion, paint.getNativeInstance());
        }
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawPoint(float x, float y, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeArea(paint, null);
        nDrawPoint(this.mNativeCanvasWrapper, x, y, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawPoints(float[] pts, int offset, int count, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeArea(paint, null);
        nDrawPoints(this.mNativeCanvasWrapper, pts, offset, count, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawPoints(float[] pts, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        drawPoints(pts, 0, pts.length, paint);
    }

    @Deprecated
    public void drawPosText(char[] text, int index, int count, float[] pos, Paint paint) {
        if (index < 0 || index + count > text.length || count * 2 > pos.length) {
            throw new IndexOutOfBoundsException();
        }
        throwIfHasHwBitmapInSwMode(paint);
        for (int i = 0; i < count; i++) {
            drawText(text, index + i, 1, pos[i * 2], pos[(i * 2) + 1], paint);
        }
    }

    @Deprecated
    public void drawPosText(String text, float[] pos, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        drawPosText(text.toCharArray(), 0, text.length(), pos, paint);
    }

    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(left, top, right, bottom));
        nDrawRect(this.mNativeCanvasWrapper, left, top, right, bottom, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawRect(Rect r, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        drawRect(r.left, r.top, r.right, r.bottom, paint);
    }

    public void drawRect(RectF rect, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeArea(paint, rect);
        nDrawRect(this.mNativeCanvasWrapper, rect.left, rect.top, rect.right, rect.bottom, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawRGB(int r, int g, int b) {
        drawColor(Color.rgb(r, g, b));
    }

    public void drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt iBaseCanvasExt = this.mBaseCanvasExt;
        IBaseCanvasExt.Entity entity = iBaseCanvasExt.changeArea(paint, iBaseCanvasExt.getRectF(left, top, right, bottom));
        nDrawRoundRect(this.mNativeCanvasWrapper, left, top, right, bottom, rx, ry, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawRoundRect(RectF rect, float rx, float ry, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        drawRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, paint);
    }

    public void drawDoubleRoundRect(RectF outer, float outerRx, float outerRy, RectF inner, float innerRx, float innerRy, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        float outerLeft = outer.left;
        float outerTop = outer.top;
        float outerRight = outer.right;
        float outerBottom = outer.bottom;
        float innerLeft = inner.left;
        float innerTop = inner.top;
        float innerRight = inner.right;
        float innerBottom = inner.bottom;
        nDrawDoubleRoundRect(this.mNativeCanvasWrapper, outerLeft, outerTop, outerRight, outerBottom, outerRx, outerRy, innerLeft, innerTop, innerRight, innerBottom, innerRx, innerRy, paint.getNativeInstance());
    }

    public void drawDoubleRoundRect(RectF outer, float[] outerRadii, RectF inner, float[] innerRadii, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        if (innerRadii == null || outerRadii == null || innerRadii.length != 8 || outerRadii.length != 8) {
            throw new IllegalArgumentException("Both inner and outer radii arrays must contain exactly 8 values");
        }
        float outerLeft = outer.left;
        float outerTop = outer.top;
        float outerRight = outer.right;
        float outerBottom = outer.bottom;
        float innerLeft = inner.left;
        float innerTop = inner.top;
        float innerRight = inner.right;
        float innerBottom = inner.bottom;
        nDrawDoubleRoundRect(this.mNativeCanvasWrapper, outerLeft, outerTop, outerRight, outerBottom, outerRadii, innerLeft, innerTop, innerRight, innerBottom, innerRadii, paint.getNativeInstance());
    }

    public void drawGlyphs(int[] glyphIds, int glyphIdOffset, float[] positions, int positionOffset, int glyphCount, Font font, Paint paint) {
        Objects.requireNonNull(glyphIds, "glyphIds must not be null.");
        Objects.requireNonNull(positions, "positions must not be null.");
        Objects.requireNonNull(font, "font must not be null.");
        Objects.requireNonNull(paint, "paint must not be null.");
        Preconditions.checkArgumentNonnegative(glyphCount);
        if (glyphIdOffset < 0 || glyphIdOffset + glyphCount > glyphIds.length) {
            throw new IndexOutOfBoundsException("glyphIds must have at least " + (glyphIdOffset + glyphCount) + " of elements");
        } else if (positionOffset < 0 || positionOffset + (glyphCount * 2) > positions.length) {
            throw new IndexOutOfBoundsException("positions must have at least " + (positionOffset + (glyphCount * 2)) + " of elements");
        } else {
            nDrawGlyphs(this.mNativeCanvasWrapper, glyphIds, positions, glyphIdOffset, positionOffset, glyphCount, font.getNativePtr(), paint.getNativeInstance());
        }
    }

    public void drawText(char[] text, int index, int count, float x, float y, Paint paint) {
        if ((index | count | (index + count) | ((text.length - index) - count)) >= 0) {
            throwIfHasHwBitmapInSwMode(paint);
            IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeText(paint);
            nDrawText(this.mNativeCanvasWrapper, text, index, count, x, y, paint.mBidiFlags, paint.getNativeInstance());
            this.mBaseCanvasExt.resetEntity(entity, paint);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public void drawText(CharSequence text, int start, int end, float x, float y, Paint paint) {
        Paint paint2;
        IBaseCanvasExt.Entity entity;
        if ((start | end | (end - start) | (text.length() - end)) >= 0) {
            throwIfHasHwBitmapInSwMode(paint);
            IBaseCanvasExt.Entity entity2 = this.mBaseCanvasExt.changeText(paint);
            if ((text instanceof String) || (text instanceof SpannedString) || (text instanceof SpannableString)) {
                entity = entity2;
                paint2 = paint;
                nDrawText(this.mNativeCanvasWrapper, text.toString(), start, end, x, y, paint.mBidiFlags, paint.getNativeInstance());
            } else if (text instanceof GraphicsOperations) {
                ((GraphicsOperations) text).drawText(this, start, end, x, y, paint);
                entity = entity2;
                paint2 = paint;
            } else {
                char[] buf = TemporaryBuffer.obtain(end - start);
                TextUtils.getChars(text, start, end, buf, 0);
                nDrawText(this.mNativeCanvasWrapper, buf, 0, end - start, x, y, paint.mBidiFlags, paint.getNativeInstance());
                TemporaryBuffer.recycle(buf);
                entity = entity2;
                paint2 = paint;
            }
            this.mBaseCanvasExt.resetEntity(entity, paint2);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public void drawText(String text, float x, float y, Paint paint) {
        throwIfHasHwBitmapInSwMode(paint);
        IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeText(paint);
        nDrawText(this.mNativeCanvasWrapper, text, 0, text.length(), x, y, paint.mBidiFlags, paint.getNativeInstance());
        this.mBaseCanvasExt.resetEntity(entity, paint);
    }

    public void drawText(String text, int start, int end, float x, float y, Paint paint) {
        if ((start | end | (end - start) | (text.length() - end)) >= 0) {
            throwIfHasHwBitmapInSwMode(paint);
            IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeText(paint);
            nDrawText(this.mNativeCanvasWrapper, text, start, end, x, y, paint.mBidiFlags, paint.getNativeInstance());
            this.mBaseCanvasExt.resetEntity(entity, paint);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public void drawTextOnPath(char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint) {
        if (index >= 0 && index + count <= text.length) {
            throwIfHasHwBitmapInSwMode(paint);
            IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeText(paint);
            nDrawTextOnPath(this.mNativeCanvasWrapper, text, index, count, path.readOnlyNI(), hOffset, vOffset, paint.mBidiFlags, paint.getNativeInstance());
            this.mBaseCanvasExt.resetEntity(entity, paint);
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint) {
        if (text.length() > 0) {
            throwIfHasHwBitmapInSwMode(paint);
            IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeText(paint);
            nDrawTextOnPath(this.mNativeCanvasWrapper, text, path.readOnlyNI(), hOffset, vOffset, paint.mBidiFlags, paint.getNativeInstance());
            this.mBaseCanvasExt.resetEntity(entity, paint);
        }
    }

    public void drawTextRun(char[] text, int index, int count, int contextIndex, int contextCount, float x, float y, boolean isRtl, Paint paint) {
        if (text == null) {
            throw new NullPointerException("text is null");
        } else if (paint == null) {
            throw new NullPointerException("paint is null");
        } else if ((index | count | contextIndex | contextCount | (index - contextIndex) | ((contextIndex + contextCount) - (index + count)) | (text.length - (contextIndex + contextCount))) >= 0) {
            throwIfHasHwBitmapInSwMode(paint);
            IBaseCanvasExt.Entity entity = this.mBaseCanvasExt.changeText(paint);
            nDrawTextRun(this.mNativeCanvasWrapper, text, index, count, contextIndex, contextCount, x, y, isRtl, paint.getNativeInstance(), 0L);
            this.mBaseCanvasExt.resetEntity(entity, paint);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void drawTextRun(CharSequence text, int start, int end, int contextStart, int contextEnd, float x, float y, boolean isRtl, Paint paint) {
        IBaseCanvasExt.Entity entity;
        if (text == null) {
            throw new NullPointerException("text is null");
        } else if (paint == null) {
            throw new NullPointerException("paint is null");
        } else if ((start | end | contextStart | contextEnd | (start - contextStart) | (end - start) | (contextEnd - end) | (text.length() - contextEnd)) >= 0) {
            throwIfHasHwBitmapInSwMode(paint);
            IBaseCanvasExt.Entity entity2 = this.mBaseCanvasExt.changeText(paint);
            if ((text instanceof String) || (text instanceof SpannedString)) {
                entity = entity2;
            } else if (text instanceof SpannableString) {
                entity = entity2;
            } else {
                if (text instanceof GraphicsOperations) {
                    entity = entity2;
                    ((GraphicsOperations) text).drawTextRun(this, start, end, contextStart, contextEnd, x, y, isRtl, paint);
                } else {
                    entity = entity2;
                    if (text instanceof PrecomputedText) {
                        PrecomputedText pt = (PrecomputedText) text;
                        int paraIndex = pt.findParaIndex(start);
                        if (end <= pt.getParagraphEnd(paraIndex)) {
                            int paraStart = pt.getParagraphStart(paraIndex);
                            MeasuredParagraph mp = pt.getMeasuredParagraph(paraIndex);
                            drawTextRun(mp.getMeasuredText(), start - paraStart, end - paraStart, contextStart - paraStart, contextEnd - paraStart, x, y, isRtl, paint);
                            return;
                        }
                    }
                    int contextLen = contextEnd - contextStart;
                    int len = end - start;
                    char[] buf = TemporaryBuffer.obtain(contextLen);
                    TextUtils.getChars(text, contextStart, contextEnd, buf, 0);
                    nDrawTextRun(this.mNativeCanvasWrapper, buf, start - contextStart, len, 0, contextLen, x, y, isRtl, paint.getNativeInstance(), 0L);
                    TemporaryBuffer.recycle(buf);
                }
                this.mBaseCanvasExt.resetEntity(entity, paint);
            }
            nDrawTextRun(this.mNativeCanvasWrapper, text.toString(), start, end, contextStart, contextEnd, x, y, isRtl, paint.getNativeInstance());
            this.mBaseCanvasExt.resetEntity(entity, paint);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void drawTextRun(MeasuredText measuredText, int start, int end, int contextStart, int contextEnd, float x, float y, boolean isRtl, Paint paint) {
        nDrawTextRun(this.mNativeCanvasWrapper, measuredText.getChars(), start, end - start, contextStart, contextEnd - contextStart, x, y, isRtl, paint.getNativeInstance(), measuredText.getNativePtr());
    }

    public void drawVertices(Canvas.VertexMode mode, int vertexCount, float[] verts, int vertOffset, float[] texs, int texOffset, int[] colors, int colorOffset, short[] indices, int indexOffset, int indexCount, Paint paint) {
        checkRange(verts.length, vertOffset, vertexCount);
        if (texs != null) {
            checkRange(texs.length, texOffset, vertexCount);
        }
        if (colors != null) {
            checkRange(colors.length, colorOffset, vertexCount / 2);
        }
        if (indices != null) {
            checkRange(indices.length, indexOffset, indexCount);
        }
        throwIfHasHwBitmapInSwMode(paint);
        nDrawVertices(this.mNativeCanvasWrapper, mode.nativeInt, vertexCount, verts, vertOffset, texs, texOffset, colors, colorOffset, indices, indexOffset, indexCount, paint.getNativeInstance());
    }

    public void punchHole(float left, float top, float right, float bottom, float rx, float ry) {
        nPunchHole(this.mNativeCanvasWrapper, left, top, right, bottom, rx, ry);
    }

    public void setHwBitmapsInSwModeEnabled(boolean enabled) {
        this.mAllowHwBitmapsInSwMode = enabled;
    }

    public boolean isHwBitmapsInSwModeEnabled() {
        return this.mAllowHwBitmapsInSwMode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onHwBitmapInSwMode() {
        if (!this.mAllowHwBitmapsInSwMode) {
            throw new IllegalArgumentException("Software rendering doesn't support hardware bitmaps");
        }
    }

    private void throwIfHwBitmapInSwMode(Bitmap bitmap) {
        if (!isHardwareAccelerated() && bitmap.getConfig() == Bitmap.Config.HARDWARE) {
            onHwBitmapInSwMode();
        }
    }

    private void throwIfHasHwBitmapInSwMode(Paint p) {
        if (!isHardwareAccelerated() && p != null) {
            throwIfHasHwBitmapInSwMode(p.getShader());
        }
    }

    private void throwIfHasHwBitmapInSwMode(Shader shader) {
        if (shader != null) {
            if (shader instanceof BitmapShader) {
                throwIfHwBitmapInSwMode(((BitmapShader) shader).mBitmap);
            }
            if (shader instanceof ComposeShader) {
                throwIfHasHwBitmapInSwMode(((ComposeShader) shader).mShaderA);
                throwIfHasHwBitmapInSwMode(((ComposeShader) shader).mShaderB);
            }
        }
    }
}
