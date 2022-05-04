package android.text;

import android.graphics.Paint;
/* loaded from: classes3.dex */
public class TextPaint extends Paint {
    public int baselineShift;
    public int bgColor;
    public int[] drawableState;
    public int linkColor;
    public float underlineThickness;
    public float density = 1.0f;
    public int underlineColor = 0;

    public TextPaint() {
    }

    public TextPaint(int flags) {
        super(flags);
    }

    public TextPaint(Paint p) {
        super(p);
    }

    public void set(TextPaint tp) {
        super.set((Paint) tp);
        this.bgColor = tp.bgColor;
        this.baselineShift = tp.baselineShift;
        this.linkColor = tp.linkColor;
        this.drawableState = tp.drawableState;
        this.density = tp.density;
        this.underlineColor = tp.underlineColor;
        this.underlineThickness = tp.underlineThickness;
    }

    public void setUnderlineText(int color, float thickness) {
        this.underlineColor = color;
        this.underlineThickness = thickness;
    }

    @Override // android.graphics.Paint
    public float getUnderlineThickness() {
        if (this.underlineColor != 0) {
            return this.underlineThickness;
        }
        return super.getUnderlineThickness();
    }
}
