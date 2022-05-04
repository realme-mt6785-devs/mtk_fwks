package android.window;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.audio.common.AudioFormat;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallback;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControlViewHost;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.R;
import com.android.internal.policy.DecorView;
import com.android.internal.util.ContrastColorUtil;
import java.time.Duration;
import java.time.Instant;
/* loaded from: classes3.dex */
public final class SplashScreenView extends FrameLayout {
    private static final int LIGHT_BARS_MASK = 24;
    private static final int WINDOW_FLAG_MASK = -1946157056;
    private int mAppWindowFlags;
    private View mBrandingImageView;
    private RemoteCallback mClientCallback;
    private boolean mDecorFitsSystemWindows;
    private boolean mHasRemoved;
    private Activity mHostActivity;
    private Duration mIconAnimationDuration;
    private Instant mIconAnimationStart;
    private View mIconView;
    private int mInitBackgroundColor;
    private boolean mIsCopied;
    private int mNavigationBarColor;
    private boolean mNavigationContrastEnforced;
    private boolean mNotCopyable;
    private Bitmap mParceledBrandingBitmap;
    private Bitmap mParceledIconBackgroundBitmap;
    private Bitmap mParceledIconBitmap;
    private int mStatusBarColor;
    private boolean mStatusContrastEnforced;
    private SurfaceControlViewHost mSurfaceHost;
    private SurfaceControlViewHost.SurfacePackage mSurfacePackage;
    private SurfaceControlViewHost.SurfacePackage mSurfacePackageCopy;
    private SurfaceView mSurfaceView;
    private int mSystemBarsAppearance;
    private Window mWindow;
    private static final String TAG = SplashScreenView.class.getSimpleName();
    private static final boolean DEBUG = Build.IS_DEBUGGABLE;

    /* loaded from: classes3.dex */
    public interface IconAnimateListener {
        boolean prepareAnimate(long j, Runnable runnable);
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int mBackgroundColor;
        private Drawable mBrandingDrawable;
        private int mBrandingImageHeight;
        private int mBrandingImageWidth;
        private RemoteCallback mClientCallback;
        private final Context mContext;
        private Duration mIconAnimationDuration;
        private Instant mIconAnimationStart;
        private Drawable mIconBackground;
        private Drawable mIconDrawable;
        private int mIconSize;
        private Drawable mOverlayDrawable;
        private Bitmap mParceledBrandingBitmap;
        private Bitmap mParceledIconBackgroundBitmap;
        private Bitmap mParceledIconBitmap;
        private SurfaceControlViewHost.SurfacePackage mSurfacePackage;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder createFromParcel(SplashScreenViewParcelable parcelable) {
            this.mIconSize = parcelable.getIconSize();
            this.mBackgroundColor = parcelable.getBackgroundColor();
            SurfaceControlViewHost.SurfacePackage surfacePackage = parcelable.mSurfacePackage;
            this.mSurfacePackage = surfacePackage;
            if (surfacePackage == null && parcelable.mIconBitmap != null) {
                this.mIconDrawable = new BitmapDrawable(this.mContext.getResources(), parcelable.mIconBitmap);
                this.mParceledIconBitmap = parcelable.mIconBitmap;
            }
            if (parcelable.mIconBackground != null) {
                this.mIconBackground = new BitmapDrawable(this.mContext.getResources(), parcelable.mIconBackground);
                this.mParceledIconBackgroundBitmap = parcelable.mIconBackground;
            }
            if (parcelable.mBrandingBitmap != null) {
                setBrandingDrawable(new BitmapDrawable(this.mContext.getResources(), parcelable.mBrandingBitmap), parcelable.mBrandingWidth, parcelable.mBrandingHeight);
                this.mParceledBrandingBitmap = parcelable.mBrandingBitmap;
            }
            this.mIconAnimationStart = Instant.ofEpochMilli(parcelable.mIconAnimationStartMillis);
            this.mIconAnimationDuration = Duration.ofMillis(parcelable.mIconAnimationDurationMillis);
            this.mClientCallback = parcelable.mClientCallback;
            if (SplashScreenView.DEBUG) {
                Log.d(SplashScreenView.TAG, String.format("Building from parcel drawable: %s", this.mIconDrawable));
            }
            return this;
        }

        public Builder setIconSize(int iconSize) {
            this.mIconSize = iconSize;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.mBackgroundColor = backgroundColor;
            return this;
        }

        public Builder setOverlayDrawable(Drawable drawable) {
            this.mOverlayDrawable = drawable;
            return this;
        }

        public Builder setCenterViewDrawable(Drawable drawable) {
            this.mIconDrawable = drawable;
            return this;
        }

        public Builder setIconBackground(Drawable iconBackground) {
            this.mIconBackground = iconBackground;
            return this;
        }

        public Builder setAnimationDurationMillis(int duration) {
            this.mIconAnimationDuration = Duration.ofMillis(duration);
            return this;
        }

        public Builder setBrandingDrawable(Drawable branding, int width, int height) {
            this.mBrandingDrawable = branding;
            this.mBrandingImageWidth = width;
            this.mBrandingImageHeight = height;
            return this;
        }

        public SplashScreenView build() {
            Trace.traceBegin(32L, "SplashScreenView#build");
            LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
            SplashScreenView view = (SplashScreenView) layoutInflater.inflate(R.layout.splash_screen_view, (ViewGroup) null, false);
            view.mInitBackgroundColor = this.mBackgroundColor;
            Drawable drawable = this.mOverlayDrawable;
            if (drawable != null) {
                view.setBackground(drawable);
            } else {
                view.setBackgroundColor(this.mBackgroundColor);
            }
            view.mClientCallback = this.mClientCallback;
            view.mBrandingImageView = view.findViewById(R.id.splashscreen_branding_view);
            if ((this.mIconDrawable instanceof IconAnimateListener) || this.mSurfacePackage != null) {
                view.mIconView = createSurfaceView(view);
                Drawable drawable2 = this.mIconDrawable;
                Duration duration = this.mIconAnimationDuration;
                view.initIconAnimation(drawable2, duration != null ? duration.toMillis() : 0L);
                view.mIconAnimationStart = this.mIconAnimationStart;
                view.mIconAnimationDuration = this.mIconAnimationDuration;
            } else if (this.mIconSize != 0) {
                ImageView imageView = (ImageView) view.findViewById(R.id.splashscreen_icon_view);
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.width = this.mIconSize;
                params.height = this.mIconSize;
                imageView.setLayoutParams(params);
                Drawable drawable3 = this.mIconDrawable;
                if (drawable3 != null) {
                    imageView.setImageDrawable(drawable3);
                }
                Drawable drawable4 = this.mIconBackground;
                if (drawable4 != null) {
                    imageView.setBackground(drawable4);
                }
                view.mIconView = imageView;
            }
            if (this.mOverlayDrawable != null || this.mIconDrawable == null) {
                view.setNotCopyable();
            }
            view.mParceledIconBackgroundBitmap = this.mParceledIconBackgroundBitmap;
            view.mParceledIconBitmap = this.mParceledIconBitmap;
            if (this.mBrandingImageHeight <= 0 || this.mBrandingImageWidth <= 0 || this.mBrandingDrawable == null) {
                view.mBrandingImageView.setVisibility(8);
            } else {
                ViewGroup.LayoutParams params2 = view.mBrandingImageView.getLayoutParams();
                params2.width = this.mBrandingImageWidth;
                params2.height = this.mBrandingImageHeight;
                view.mBrandingImageView.setLayoutParams(params2);
                view.mBrandingImageView.setBackground(this.mBrandingDrawable);
            }
            Bitmap bitmap = this.mParceledBrandingBitmap;
            if (bitmap != null) {
                view.mParceledBrandingBitmap = bitmap;
            }
            if (SplashScreenView.DEBUG) {
                String str = SplashScreenView.TAG;
                Log.d(str, "Build " + view + "\nIcon: view: " + view.mIconView + " drawable: " + this.mIconDrawable + " size: " + this.mIconSize + "\nBranding: view: " + view.mBrandingImageView + " drawable: " + this.mBrandingDrawable + " size w: " + this.mBrandingImageWidth + " h: " + this.mBrandingImageHeight);
            }
            Trace.traceEnd(32L);
            return view;
        }

        private SurfaceView createSurfaceView(SplashScreenView view) {
            SurfaceView surfaceView = new SurfaceView(view.getContext());
            surfaceView.setPadding(0, 0, 0, 0);
            surfaceView.setBackground(this.mIconBackground);
            if (this.mSurfacePackage == null) {
                if (SplashScreenView.DEBUG) {
                    String str = SplashScreenView.TAG;
                    Log.d(str, "SurfaceControlViewHost created on thread " + Thread.currentThread().getId());
                }
                Context context = this.mContext;
                SurfaceControlViewHost viewHost = new SurfaceControlViewHost(context, context.getDisplay(), surfaceView.getHostToken());
                ImageView imageView = new ImageView(this.mContext);
                imageView.setBackground(this.mIconDrawable);
                int i = this.mIconSize;
                viewHost.setView(imageView, i, i);
                SurfaceControlViewHost.SurfacePackage surfacePackage = viewHost.getSurfacePackage();
                surfaceView.setChildSurfacePackage(surfacePackage);
                view.mSurfacePackage = surfacePackage;
                view.mSurfaceHost = viewHost;
                view.mSurfacePackageCopy = new SurfaceControlViewHost.SurfacePackage(surfacePackage);
            } else {
                if (SplashScreenView.DEBUG) {
                    Log.d(SplashScreenView.TAG, "Using copy of SurfacePackage in the client");
                }
                view.mSurfacePackage = this.mSurfacePackage;
            }
            int i2 = this.mIconSize;
            if (i2 != 0) {
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(i2, i2);
                lp.gravity = 17;
                surfaceView.setLayoutParams(lp);
                if (SplashScreenView.DEBUG) {
                    String str2 = SplashScreenView.TAG;
                    Log.d(str2, "Icon size " + this.mIconSize);
                }
            }
            surfaceView.setUseAlpha();
            surfaceView.setZOrderOnTop(true);
            surfaceView.getHolder().setFormat(-3);
            view.addView(surfaceView);
            view.mSurfaceView = surfaceView;
            return surfaceView;
        }
    }

    public SplashScreenView(Context context) {
        super(context);
    }

    public SplashScreenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setNotCopyable() {
        this.mNotCopyable = true;
    }

    public boolean isCopyable() {
        return !this.mNotCopyable;
    }

    public void onCopied() {
        this.mIsCopied = true;
        if (this.mSurfaceView != null) {
            if (DEBUG) {
                Log.d(TAG, "Setting SurfaceView's SurfacePackage to null.");
            }
            this.mSurfacePackage.release();
            this.mSurfacePackage = null;
        }
    }

    public SurfaceControlViewHost getSurfaceHost() {
        return this.mSurfaceHost;
    }

    @Override // android.view.View
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null) {
            surfaceView.setAlpha(surfaceView.getAlpha() * alpha);
        }
    }

    public Duration getIconAnimationDuration() {
        return this.mIconAnimationDuration;
    }

    public Instant getIconAnimationStart() {
        return this.mIconAnimationStart;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transferSurface() {
        SurfaceControlViewHost.SurfacePackage surfacePackage = this.mSurfacePackage;
        if (surfacePackage != null) {
            if (DEBUG) {
                surfacePackage.getSurfaceControl().addOnReparentListener(SplashScreenView$$ExternalSyntheticLambda0.INSTANCE);
                String str = TAG;
                Log.d(str, "Transferring surface " + this.mSurfaceView.toString());
            }
            this.mSurfaceView.setChildSurfacePackage(this.mSurfacePackage);
        }
    }

    void initIconAnimation(Drawable iconDrawable, long duration) {
        if (iconDrawable instanceof IconAnimateListener) {
            IconAnimateListener aniDrawable = (IconAnimateListener) iconDrawable;
            aniDrawable.prepareAnimate(duration, new Runnable() { // from class: android.window.SplashScreenView$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    SplashScreenView.this.animationStartCallback();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animationStartCallback() {
        this.mIconAnimationStart = Instant.now();
    }

    public void remove() {
        if (!this.mHasRemoved) {
            setVisibility(8);
            if (this.mParceledIconBitmap != null) {
                View view = this.mIconView;
                if (view instanceof ImageView) {
                    ((ImageView) view).setImageDrawable(null);
                } else if (view != null) {
                    view.setBackground(null);
                }
                this.mParceledIconBitmap.recycle();
                this.mParceledIconBitmap = null;
            }
            if (this.mParceledBrandingBitmap != null) {
                this.mBrandingImageView.setBackground(null);
                this.mParceledBrandingBitmap.recycle();
                this.mParceledBrandingBitmap = null;
            }
            if (this.mParceledIconBackgroundBitmap != null) {
                View view2 = this.mIconView;
                if (view2 != null) {
                    view2.setBackground(null);
                }
                this.mParceledIconBackgroundBitmap.recycle();
                this.mParceledIconBackgroundBitmap = null;
            }
            Window window = this.mWindow;
            if (window != null) {
                DecorView decorView = (DecorView) window.peekDecorView();
                if (DEBUG) {
                    Log.d(TAG, "remove starting view");
                }
                if (decorView != null) {
                    decorView.removeView(this);
                }
                restoreSystemUIColors();
                this.mWindow = null;
            }
            Activity activity = this.mHostActivity;
            if (activity != null) {
                activity.setSplashScreenView(null);
                this.mHostActivity = null;
            }
            this.mHasRemoved = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        releaseAnimationSurfaceHost();
    }

    private void releaseAnimationSurfaceHost() {
        SurfaceControlViewHost surfaceControlViewHost = this.mSurfaceHost;
        if (surfaceControlViewHost != null && !this.mIsCopied) {
            final SurfaceControlViewHost finalSurfaceHost = this.mSurfaceHost;
            this.mSurfaceHost = null;
            finalSurfaceHost.getView().post(new Runnable() { // from class: android.window.SplashScreenView$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SplashScreenView.lambda$releaseAnimationSurfaceHost$1(SurfaceControlViewHost.this);
                }
            });
        } else if (this.mSurfacePackage != null && surfaceControlViewHost == null) {
            this.mSurfacePackage = null;
            this.mClientCallback.sendResult(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$releaseAnimationSurfaceHost$1(SurfaceControlViewHost finalSurfaceHost) {
        if (DEBUG) {
            String str = TAG;
            Log.d(str, "Shell removed splash screen. Releasing SurfaceControlViewHost on thread #" + Thread.currentThread().getId());
        }
        finalSurfaceHost.release();
    }

    public void attachHostActivityAndSetSystemUIColors(Activity activity, Window window) {
        activity.setSplashScreenView(this);
        this.mHostActivity = activity;
        this.mWindow = window;
        WindowManager.LayoutParams attr = window.getAttributes();
        this.mAppWindowFlags = attr.flags;
        this.mStatusBarColor = window.getStatusBarColor();
        this.mNavigationBarColor = window.getNavigationBarColor();
        this.mSystemBarsAppearance = window.getInsetsController().getSystemBarsAppearance();
        this.mNavigationContrastEnforced = window.isNavigationBarContrastEnforced();
        this.mStatusContrastEnforced = window.isStatusBarContrastEnforced();
        this.mDecorFitsSystemWindows = window.decorFitsSystemWindows();
        applySystemBarsContrastColor(window.getInsetsController(), this.mInitBackgroundColor);
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(AudioFormat.DTS_HD);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
        window.setDecorFitsSystemWindows(false);
        window.setStatusBarContrastEnforced(false);
        window.setNavigationBarContrastEnforced(false);
    }

    private void restoreSystemUIColors() {
        this.mWindow.setFlags(this.mAppWindowFlags, WINDOW_FLAG_MASK);
        this.mWindow.setStatusBarColor(this.mStatusBarColor);
        this.mWindow.setNavigationBarColor(this.mNavigationBarColor);
        this.mWindow.getInsetsController().setSystemBarsAppearance(this.mSystemBarsAppearance, 24);
        this.mWindow.setDecorFitsSystemWindows(this.mDecorFitsSystemWindows);
        this.mWindow.setStatusBarContrastEnforced(this.mStatusContrastEnforced);
        this.mWindow.setNavigationBarContrastEnforced(this.mNavigationContrastEnforced);
    }

    public static void applySystemBarsContrastColor(WindowInsetsController windowInsetsController, int backgroundColor) {
        int lightBarAppearance = ContrastColorUtil.isColorLight(backgroundColor) ? 24 : 0;
        windowInsetsController.setSystemBarsAppearance(lightBarAppearance, 24);
    }

    public View getIconView() {
        return this.mIconView;
    }

    public View getBrandingView() {
        return this.mBrandingImageView;
    }

    public int getInitBackgroundColor() {
        return this.mInitBackgroundColor;
    }

    /* loaded from: classes3.dex */
    public static class SplashScreenViewParcelable implements Parcelable {
        public static final Parcelable.Creator<SplashScreenViewParcelable> CREATOR = new Parcelable.Creator<SplashScreenViewParcelable>() { // from class: android.window.SplashScreenView.SplashScreenViewParcelable.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SplashScreenViewParcelable createFromParcel(Parcel source) {
                return new SplashScreenViewParcelable(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SplashScreenViewParcelable[] newArray(int size) {
                return new SplashScreenViewParcelable[size];
            }
        };
        private int mBackgroundColor;
        private Bitmap mBrandingBitmap;
        private int mBrandingHeight;
        private int mBrandingWidth;
        private RemoteCallback mClientCallback;
        private long mIconAnimationDurationMillis;
        private long mIconAnimationStartMillis;
        private Bitmap mIconBackground;
        private Bitmap mIconBitmap;
        private int mIconSize;
        private SurfaceControlViewHost.SurfacePackage mSurfacePackage;

        public SplashScreenViewParcelable(SplashScreenView view) {
            this.mIconBitmap = null;
            this.mIconSize = view.mIconView.getWidth();
            this.mBackgroundColor = view.getInitBackgroundColor();
            this.mIconBackground = copyDrawable(view.getIconView().getBackground());
            SurfaceControlViewHost.SurfacePackage surfacePackage = view.mSurfacePackageCopy;
            this.mSurfacePackage = surfacePackage;
            if (surfacePackage == null) {
                this.mIconBitmap = copyDrawable(((ImageView) view.getIconView()).getDrawable());
            }
            this.mBrandingBitmap = copyDrawable(view.getBrandingView().getBackground());
            ViewGroup.LayoutParams params = view.getBrandingView().getLayoutParams();
            this.mBrandingWidth = params.width;
            this.mBrandingHeight = params.height;
            if (view.getIconAnimationStart() != null) {
                this.mIconAnimationStartMillis = view.getIconAnimationStart().toEpochMilli();
            }
            if (view.getIconAnimationDuration() != null) {
                this.mIconAnimationDurationMillis = view.getIconAnimationDuration().toMillis();
            }
        }

        private Bitmap copyDrawable(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            Rect initialBounds = drawable.copyBounds();
            int width = initialBounds.width();
            int height = initialBounds.height();
            Bitmap snapshot = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas bmpCanvas = new Canvas(snapshot);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(bmpCanvas);
            Bitmap copyBitmap = snapshot.createAshmemBitmap();
            snapshot.recycle();
            return copyBitmap;
        }

        private SplashScreenViewParcelable(Parcel source) {
            this.mIconBitmap = null;
            readParcel(source);
        }

        private void readParcel(Parcel source) {
            this.mIconSize = source.readInt();
            this.mBackgroundColor = source.readInt();
            this.mIconBitmap = (Bitmap) source.readTypedObject(Bitmap.CREATOR);
            this.mBrandingWidth = source.readInt();
            this.mBrandingHeight = source.readInt();
            this.mBrandingBitmap = (Bitmap) source.readTypedObject(Bitmap.CREATOR);
            this.mIconAnimationStartMillis = source.readLong();
            this.mIconAnimationDurationMillis = source.readLong();
            this.mIconBackground = (Bitmap) source.readTypedObject(Bitmap.CREATOR);
            this.mSurfacePackage = (SurfaceControlViewHost.SurfacePackage) source.readTypedObject(SurfaceControlViewHost.SurfacePackage.CREATOR);
            this.mClientCallback = (RemoteCallback) source.readTypedObject(RemoteCallback.CREATOR);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mIconSize);
            dest.writeInt(this.mBackgroundColor);
            dest.writeTypedObject(this.mIconBitmap, flags);
            dest.writeInt(this.mBrandingWidth);
            dest.writeInt(this.mBrandingHeight);
            dest.writeTypedObject(this.mBrandingBitmap, flags);
            dest.writeLong(this.mIconAnimationStartMillis);
            dest.writeLong(this.mIconAnimationDurationMillis);
            dest.writeTypedObject(this.mIconBackground, flags);
            dest.writeTypedObject(this.mSurfacePackage, flags);
            dest.writeTypedObject(this.mClientCallback, flags);
        }

        public void clearIfNeeded() {
            Bitmap bitmap = this.mIconBitmap;
            if (bitmap != null) {
                bitmap.recycle();
                this.mIconBitmap = null;
            }
            Bitmap bitmap2 = this.mBrandingBitmap;
            if (bitmap2 != null) {
                bitmap2.recycle();
                this.mBrandingBitmap = null;
            }
        }

        int getIconSize() {
            return this.mIconSize;
        }

        int getBackgroundColor() {
            return this.mBackgroundColor;
        }

        public void setClientCallback(RemoteCallback clientCallback) {
            this.mClientCallback = clientCallback;
        }
    }
}
