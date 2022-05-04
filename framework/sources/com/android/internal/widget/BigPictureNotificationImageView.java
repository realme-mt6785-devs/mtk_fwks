package com.android.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.RemotableViewMethod;
import android.widget.ImageView;
import android.widget.RemoteViews;
import java.io.IOException;
@RemoteViews.RemoteView
/* loaded from: classes4.dex */
public class BigPictureNotificationImageView extends ImageView {
    private static final String TAG = BigPictureNotificationImageView.class.getSimpleName();

    public BigPictureNotificationImageView(Context context) {
        super(context);
    }

    public BigPictureNotificationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigPictureNotificationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BigPictureNotificationImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override // android.widget.ImageView
    @RemotableViewMethod(asyncImpl = "setImageURIAsync")
    public void setImageURI(Uri uri) {
        setImageDrawable(loadImage(uri));
    }

    @Override // android.widget.ImageView
    public Runnable setImageURIAsync(Uri uri) {
        final Drawable drawable = loadImage(uri);
        return new Runnable() { // from class: com.android.internal.widget.BigPictureNotificationImageView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BigPictureNotificationImageView.this.lambda$setImageURIAsync$0$BigPictureNotificationImageView(drawable);
            }
        };
    }

    public /* synthetic */ void lambda$setImageURIAsync$0$BigPictureNotificationImageView(Drawable drawable) {
        setImageDrawable(drawable);
    }

    @Override // android.widget.ImageView
    @RemotableViewMethod(asyncImpl = "setImageIconAsync")
    public void setImageIcon(Icon icon) {
        setImageDrawable(loadImage(icon));
    }

    @Override // android.widget.ImageView
    public Runnable setImageIconAsync(Icon icon) {
        final Drawable drawable = loadImage(icon);
        return new Runnable() { // from class: com.android.internal.widget.BigPictureNotificationImageView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BigPictureNotificationImageView.this.lambda$setImageIconAsync$1$BigPictureNotificationImageView(drawable);
            }
        };
    }

    public /* synthetic */ void lambda$setImageIconAsync$1$BigPictureNotificationImageView(Drawable drawable) {
        setImageDrawable(drawable);
    }

    private Drawable loadImage(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return LocalImageResolver.resolveImage(uri, this.mContext);
        } catch (IOException ex) {
            String str = TAG;
            Log.d(str, "Resolve failed from " + uri, ex);
            return null;
        }
    }

    private Drawable loadImage(Icon icon) {
        if (icon == null) {
            return null;
        }
        try {
            return LocalImageResolver.resolveImage(icon, this.mContext);
        } catch (IOException ex) {
            String str = TAG;
            Log.d(str, "Resolve failed from " + icon, ex);
            return null;
        }
    }
}
