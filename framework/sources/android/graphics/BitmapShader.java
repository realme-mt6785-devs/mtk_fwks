package android.graphics;

import android.graphics.Shader;
/* loaded from: classes.dex */
public class BitmapShader extends Shader {
    Bitmap mBitmap;
    private boolean mFilterFromPaint;
    private int mTileX;
    private int mTileY;

    private static native long nativeCreate(long j, long j2, int i, int i2, boolean z);

    public BitmapShader(Bitmap bitmap, Shader.TileMode tileX, Shader.TileMode tileY) {
        this(bitmap, tileX.nativeInt, tileY.nativeInt);
    }

    private BitmapShader(Bitmap bitmap, int tileX, int tileY) {
        if (bitmap != null) {
            this.mBitmap = bitmap;
            this.mTileX = tileX;
            this.mTileY = tileY;
            this.mFilterFromPaint = false;
            return;
        }
        throw new IllegalArgumentException("Bitmap must be non-null");
    }

    @Override // android.graphics.Shader
    protected long createNativeInstance(long nativeMatrix, boolean filterFromPaint) {
        this.mFilterFromPaint = filterFromPaint;
        return nativeCreate(nativeMatrix, this.mBitmap.getNativeInstance(), this.mTileX, this.mTileY, this.mFilterFromPaint);
    }

    @Override // android.graphics.Shader
    protected boolean shouldDiscardNativeInstance(boolean filterFromPaint) {
        return this.mFilterFromPaint != filterFromPaint;
    }
}
