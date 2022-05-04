package android.webkit;

import android.annotation.SystemApi;
import android.graphics.Bitmap;
/* loaded from: classes3.dex */
public abstract class WebHistoryItem implements Cloneable {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract WebHistoryItem clone();

    public abstract Bitmap getFavicon();

    @SystemApi
    @Deprecated
    public abstract int getId();

    public abstract String getOriginalUrl();

    public abstract String getTitle();

    public abstract String getUrl();
}
