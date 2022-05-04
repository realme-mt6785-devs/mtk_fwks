package android.view.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import com.android.internal.R;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes3.dex */
public class TranslateAnimation extends Animation {
    protected float mFromXDelta;
    private int mFromXType;
    protected float mFromXValue;
    protected float mFromYDelta;
    private int mFromYType;
    protected float mFromYValue;
    protected float mToXDelta;
    private int mToXType;
    protected float mToXValue;
    protected float mToYDelta;
    private int mToYType;
    protected float mToYValue;
    public ITranslateAnimationExt mTranslateAnimationExt = (ITranslateAnimationExt) ExtLoader.type(ITranslateAnimationExt.class).base(this).create();

    public TranslateAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFromXType = 0;
        this.mToXType = 0;
        this.mFromYType = 0;
        this.mToYType = 0;
        this.mFromXValue = 0.0f;
        this.mToXValue = 0.0f;
        this.mFromYValue = 0.0f;
        this.mToYValue = 0.0f;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TranslateAnimation);
        Animation.Description d = Animation.Description.parseValue(a.peekValue(0));
        this.mFromXType = d.type;
        this.mFromXValue = d.value;
        Animation.Description d2 = Animation.Description.parseValue(a.peekValue(1));
        this.mToXType = d2.type;
        this.mToXValue = d2.value;
        Animation.Description d3 = Animation.Description.parseValue(a.peekValue(2));
        this.mFromYType = d3.type;
        this.mFromYValue = d3.value;
        Animation.Description d4 = Animation.Description.parseValue(a.peekValue(3));
        this.mToYType = d4.type;
        this.mToYValue = d4.value;
        a.recycle();
    }

    public TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        this.mFromXType = 0;
        this.mToXType = 0;
        this.mFromYType = 0;
        this.mToYType = 0;
        this.mFromXValue = 0.0f;
        this.mToXValue = 0.0f;
        this.mFromYValue = 0.0f;
        this.mToYValue = 0.0f;
        this.mFromXValue = fromXDelta;
        this.mToXValue = toXDelta;
        this.mFromYValue = fromYDelta;
        this.mToYValue = toYDelta;
        this.mFromXType = 0;
        this.mToXType = 0;
        this.mFromYType = 0;
        this.mToYType = 0;
    }

    public TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue) {
        this.mFromXType = 0;
        this.mToXType = 0;
        this.mFromYType = 0;
        this.mToYType = 0;
        this.mFromXValue = 0.0f;
        this.mToXValue = 0.0f;
        this.mFromYValue = 0.0f;
        this.mToYValue = 0.0f;
        this.mFromXValue = fromXValue;
        this.mToXValue = toXValue;
        this.mFromYValue = fromYValue;
        this.mToYValue = toYValue;
        this.mFromXType = fromXType;
        this.mToXType = toXType;
        this.mFromYType = fromYType;
        this.mToYType = toYType;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float dx = this.mFromXDelta;
        float dy = this.mFromYDelta;
        float f = this.mFromXDelta;
        float f2 = this.mToXDelta;
        if (f != f2) {
            dx = f + ((f2 - f) * interpolatedTime);
        }
        float f3 = this.mFromYDelta;
        float f4 = this.mToYDelta;
        if (f3 != f4) {
            dy = f3 + ((f4 - f3) * interpolatedTime);
        }
        t.getMatrix().setTranslate(dx, dy);
    }

    @Override // android.view.animation.Animation
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.mFromXDelta = resolveSize(this.mFromXType, this.mFromXValue, width, parentWidth);
        this.mToXDelta = resolveSize(this.mToXType, this.mToXValue, width, parentWidth);
        this.mFromYDelta = resolveSize(this.mFromYType, this.mFromYValue, height, parentHeight);
        this.mToYDelta = resolveSize(this.mToYType, this.mToYValue, height, parentHeight);
    }
}
