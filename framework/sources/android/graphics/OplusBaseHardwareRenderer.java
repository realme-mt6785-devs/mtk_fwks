package android.graphics;
/* loaded from: classes.dex */
public abstract class OplusBaseHardwareRenderer {
    private float mDialogBgMaxL = -1.0f;
    private float mBackgroundMaxL = -1.0f;
    private float mForegroundMinL = -1.0f;
    private int mViewCount = 0;

    public long getNativeProxy() {
        return -1L;
    }

    public float getDarkModeDialogMaxBgMaxL() {
        return this.mDialogBgMaxL;
    }

    public boolean setForceDarkArgs(float dialogBgMaxL, float backgroundMaxL, float foregroundMinL) {
        return false;
    }

    public boolean setUsageForceDarkArgs(float dialogBgMaxL, float backgroundMaxL, float foregroundMinL) {
        long nativeProxy = getNativeProxy();
        if (nativeProxy == -1) {
            return false;
        }
        if (this.mDialogBgMaxL == dialogBgMaxL && this.mBackgroundMaxL == backgroundMaxL && this.mForegroundMinL == foregroundMinL) {
            return false;
        }
        this.mDialogBgMaxL = dialogBgMaxL;
        this.mBackgroundMaxL = backgroundMaxL;
        this.mForegroundMinL = foregroundMinL;
        return true;
    }

    public void addView() {
        this.mViewCount++;
    }

    public int getViewCount() {
        return this.mViewCount;
    }

    public void resetViewCount() {
        this.mViewCount = 0;
    }
}
