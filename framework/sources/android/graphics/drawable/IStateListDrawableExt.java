package android.graphics.drawable;

import android.content.res.IResourcesImplExt;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public interface IStateListDrawableExt {
    default Drawable hookInflateChildElements(Drawable dr, IResourcesImplExt resImpExt, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme, Resources r, TypedArray typedArray) {
        return dr;
    }
}
