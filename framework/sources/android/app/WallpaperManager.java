package android.app;

import android.annotation.SystemApi;
import android.app.ILocalWallpaperColorConsumer;
import android.app.IWallpaperManagerCallback;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadSystemException;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemProperties;
import android.os.Trace;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManagerGlobal;
import com.android.internal.R;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import libcore.io.IoUtils;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes.dex */
public class WallpaperManager {
    public static final String ACTION_CHANGE_LIVE_WALLPAPER = "android.service.wallpaper.CHANGE_LIVE_WALLPAPER";
    public static final String ACTION_CROP_AND_SET_WALLPAPER = "android.service.wallpaper.CROP_AND_SET_WALLPAPER";
    public static final String ACTION_LIVE_WALLPAPER_CHOOSER = "android.service.wallpaper.LIVE_WALLPAPER_CHOOSER";
    public static final String COMMAND_DROP = "android.home.drop";
    public static final String COMMAND_GOING_TO_SLEEP = "android.wallpaper.goingtosleep";
    public static final String COMMAND_REAPPLY = "android.wallpaper.reapply";
    public static final String COMMAND_SECONDARY_TAP = "android.wallpaper.secondaryTap";
    public static final String COMMAND_TAP = "android.wallpaper.tap";
    public static final String COMMAND_WAKING_UP = "android.wallpaper.wakingup";
    public static final String EXTRA_LIVE_WALLPAPER_COMPONENT = "android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT";
    public static final String EXTRA_NEW_WALLPAPER_ID = "android.service.wallpaper.extra.ID";
    public static final int FLAG_LOCK = 2;
    public static final int FLAG_SYSTEM = 1;
    private static final String PROP_LOCK_WALLPAPER = "ro.config.lock_wallpaper";
    private static final String PROP_WALLPAPER = "ro.config.wallpaper";
    private static final String PROP_WALLPAPER_COMPONENT = "ro.config.wallpaper_component";
    private static final String WALLPAPER_CMF_PATH = "/wallpaper/image/";
    public static final String WALLPAPER_PREVIEW_META_DATA = "android.wallpaper.preview";
    private static Globals sGlobals;
    private final ColorManagementProxy mCmProxy;
    private final Context mContext;
    private float mWallpaperXStep;
    private float mWallpaperYStep;
    private final boolean mWcgEnabled;
    private static String TAG = "WallpaperManager";
    private static boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static final RectF LOCAL_COLOR_BOUNDS = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    private static final String VALUE_CMF_COLOR = SystemProperties.get("ro.boot.hardware.color");
    private static final Object sSync = new Object[0];
    public static IWallpaperManagerExt sWallpaperManagerExt = (IWallpaperManagerExt) ExtLoader.type(IWallpaperManagerExt.class).create();

    /* loaded from: classes.dex */
    public interface LocalWallpaperColorConsumer {
        void onColorsChanged(RectF rectF, WallpaperColors wallpaperColors);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SetWallpaperFlags {
    }

    /* loaded from: classes.dex */
    static class FastBitmapDrawable extends Drawable {
        private final Bitmap mBitmap;
        private int mDrawLeft;
        private int mDrawTop;
        private final int mHeight;
        private final Paint mPaint;
        private final int mWidth;

        private FastBitmapDrawable(Bitmap bitmap) {
            this.mBitmap = bitmap;
            int width = bitmap.getWidth();
            this.mWidth = width;
            int height = bitmap.getHeight();
            this.mHeight = height;
            setBounds(0, 0, width, height);
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.drawBitmap(this.mBitmap, this.mDrawLeft, this.mDrawTop, this.mPaint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -1;
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int left, int top, int right, int bottom) {
            this.mDrawLeft = (((right - left) - this.mWidth) / 2) + left;
            this.mDrawTop = (((bottom - top) - this.mHeight) / 2) + top;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean dither) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean filter) {
            throw new UnsupportedOperationException("Not supported with this drawable");
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mHeight;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return this.mWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return this.mHeight;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Globals extends IWallpaperManagerCallback.Stub {
        private Bitmap mCachedWallpaper;
        private int mCachedWallpaperUserId;
        private boolean mColorCallbackRegistered;
        private Bitmap mDefaultWallpaper;
        private Handler mMainLooperHandler;
        private final IWallpaperManager mService;
        private final ArrayList<Pair<OnColorsChangedListener, Handler>> mColorListeners = new ArrayList<>();
        private ArrayMap<RectF, ArraySet<LocalWallpaperColorConsumer>> mLocalColorAreas = new ArrayMap<>();
        private ILocalWallpaperColorConsumer mLocalColorCallback = new ILocalWallpaperColorConsumer.Stub() { // from class: android.app.WallpaperManager.Globals.1
            @Override // android.app.ILocalWallpaperColorConsumer
            public void onColorsChanged(RectF area, WallpaperColors colors) {
                ArraySet<LocalWallpaperColorConsumer> callbacks = (ArraySet) Globals.this.mLocalColorAreas.get(area);
                if (callbacks != null) {
                    Iterator<LocalWallpaperColorConsumer> it = callbacks.iterator();
                    while (it.hasNext()) {
                        LocalWallpaperColorConsumer callback = it.next();
                        callback.onColorsChanged(area, colors);
                    }
                }
            }
        };

        Globals(IWallpaperManager service, Looper looper) {
            this.mService = service;
            this.mMainLooperHandler = new Handler(looper);
            forgetLoadedWallpaper();
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperChanged() {
            forgetLoadedWallpaper();
        }

        public void addOnColorsChangedListener(OnColorsChangedListener callback, Handler handler, int userId, int displayId) {
            synchronized (this) {
                if (!this.mColorCallbackRegistered) {
                    try {
                        this.mService.registerWallpaperColorsCallback(this, userId, displayId);
                        this.mColorCallbackRegistered = true;
                    } catch (RemoteException e) {
                        Log.w(WallpaperManager.TAG, "Can't register for color updates", e);
                    }
                }
                this.mColorListeners.add(new Pair<>(callback, handler));
            }
        }

        public void addOnColorsChangedListener(LocalWallpaperColorConsumer callback, List<RectF> regions, int which, int userId, int displayId) {
            for (RectF area : regions) {
                ArraySet<LocalWallpaperColorConsumer> callbacks = this.mLocalColorAreas.get(area);
                if (callbacks == null) {
                    callbacks = new ArraySet<>();
                    this.mLocalColorAreas.put(area, callbacks);
                }
                callbacks.add(callback);
            }
            try {
                this.mService.addOnLocalColorsChangedListener(this.mLocalColorCallback, regions, which, userId, displayId);
            } catch (RemoteException e) {
                Log.e(WallpaperManager.TAG, "Can't register for local color updates", e);
            }
        }

        public void removeOnColorsChangedListener(LocalWallpaperColorConsumer callback, int which, int userId, int displayId) {
            ArrayList<RectF> removeAreas = new ArrayList<>();
            for (RectF area : this.mLocalColorAreas.keySet()) {
                ArraySet<LocalWallpaperColorConsumer> callbacks = this.mLocalColorAreas.get(area);
                if (callbacks != null) {
                    callbacks.remove(callback);
                    if (callbacks.size() == 0) {
                        this.mLocalColorAreas.remove(area);
                        removeAreas.add(area);
                    }
                }
            }
            try {
                if (removeAreas.size() > 0) {
                    this.mService.removeOnLocalColorsChangedListener(this.mLocalColorCallback, removeAreas, which, userId, displayId);
                }
            } catch (RemoteException e) {
                Log.e(WallpaperManager.TAG, "Can't unregister for local color updates", e);
            }
        }

        public void removeOnColorsChangedListener(final OnColorsChangedListener callback, int userId, int displayId) {
            synchronized (this) {
                this.mColorListeners.removeIf(new Predicate() { // from class: android.app.WallpaperManager$Globals$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WallpaperManager.Globals.lambda$removeOnColorsChangedListener$0(WallpaperManager.OnColorsChangedListener.this, (Pair) obj);
                    }
                });
                if (this.mColorListeners.size() == 0 && this.mColorCallbackRegistered) {
                    this.mColorCallbackRegistered = false;
                    try {
                        this.mService.unregisterWallpaperColorsCallback(this, userId, displayId);
                    } catch (RemoteException e) {
                        Log.w(WallpaperManager.TAG, "Can't unregister color updates", e);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$removeOnColorsChangedListener$0(OnColorsChangedListener callback, Pair pair) {
            return pair.first == callback;
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperColorsChanged(final WallpaperColors colors, final int which, final int userId) {
            Handler handler;
            synchronized (this) {
                Iterator<Pair<OnColorsChangedListener, Handler>> it = this.mColorListeners.iterator();
                while (it.hasNext()) {
                    final Pair<OnColorsChangedListener, Handler> listener = it.next();
                    Handler handler2 = listener.second;
                    if (listener.second == null) {
                        Handler handler3 = this.mMainLooperHandler;
                        handler = handler3;
                    } else {
                        handler = handler2;
                    }
                    handler.post(new Runnable() { // from class: android.app.WallpaperManager$Globals$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            WallpaperManager.Globals.this.lambda$onWallpaperColorsChanged$1$WallpaperManager$Globals(listener, colors, which, userId);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onWallpaperColorsChanged$1$WallpaperManager$Globals(Pair listener, WallpaperColors colors, int which, int userId) {
            boolean stillExists;
            synchronized (WallpaperManager.sGlobals) {
                stillExists = this.mColorListeners.contains(listener);
            }
            if (stillExists) {
                ((OnColorsChangedListener) listener.first).onColorsChanged(colors, which, userId);
            }
        }

        WallpaperColors getWallpaperColors(int which, int userId, int displayId) {
            if (which == 2 || which == 1) {
                try {
                    return this.mService.getWallpaperColors(which, userId, displayId);
                } catch (RemoteException e) {
                    return null;
                }
            } else {
                throw new IllegalArgumentException("Must request colors for exactly one kind of wallpaper");
            }
        }

        public Bitmap peekWallpaperBitmap(Context context, boolean returnDefault, int which, ColorManagementProxy cmProxy) {
            try {
                Trace.traceBegin(8L, "WallpaperManager.Globals.peekWallpaperBitmap");
                return peekWallpaperBitmap(context, returnDefault, which, context.getUserId(), false, cmProxy);
            } finally {
                Trace.traceEnd(8L);
            }
        }

        public Bitmap peekWallpaperBitmap(Context context, boolean returnDefault, int which, int userId, boolean hardware, ColorManagementProxy cmProxy) {
            IWallpaperManager iWallpaperManager = this.mService;
            if (iWallpaperManager != null) {
                try {
                    if (!iWallpaperManager.isWallpaperSupported(context.getOpPackageName())) {
                        return null;
                    }
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            synchronized (this) {
                Bitmap bitmap = this.mCachedWallpaper;
                if (bitmap == null || this.mCachedWallpaperUserId != userId || bitmap.isRecycled()) {
                    this.mCachedWallpaper = null;
                    this.mCachedWallpaperUserId = 0;
                    try {
                        try {
                            this.mCachedWallpaper = getCurrentWallpaperLocked(context, userId, hardware, cmProxy);
                            this.mCachedWallpaperUserId = userId;
                        } catch (SecurityException e2) {
                            if (context.getApplicationInfo().targetSdkVersion < 27) {
                                Log.w(WallpaperManager.TAG, "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
                            } else {
                                throw e2;
                            }
                        }
                    } catch (OutOfMemoryError e3) {
                        String str = WallpaperManager.TAG;
                        Log.w(str, "Out of memory loading the current wallpaper: " + e3);
                    }
                    Bitmap bitmap2 = this.mCachedWallpaper;
                    if (bitmap2 != null) {
                        return bitmap2;
                    }
                    if (!returnDefault) {
                        return null;
                    }
                    Bitmap defaultWallpaper = this.mDefaultWallpaper;
                    if (defaultWallpaper != null) {
                        return defaultWallpaper;
                    }
                    Bitmap defaultWallpaper2 = getDefaultWallpaper(context, which);
                    synchronized (this) {
                        this.mDefaultWallpaper = defaultWallpaper2;
                    }
                    return defaultWallpaper2;
                }
                return this.mCachedWallpaper;
            }
        }

        void forgetLoadedWallpaper() {
            synchronized (this) {
                this.mCachedWallpaper = null;
                this.mCachedWallpaperUserId = 0;
                this.mDefaultWallpaper = null;
            }
        }

        private Bitmap getCurrentWallpaperLocked(Context context, int userId, final boolean hardware, final ColorManagementProxy cmProxy) {
            if (this.mService == null) {
                Log.w(WallpaperManager.TAG, "WallpaperService not running");
                return null;
            }
            try {
                Bundle params = new Bundle();
                ParcelFileDescriptor pfd = this.mService.getWallpaperWithFeature(context.getOpPackageName(), context.getAttributionTag(), this, 1, params, userId);
                if (pfd != null) {
                    try {
                        BufferedInputStream bis = new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(pfd));
                        try {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            while (true) {
                                int data = bis.read();
                                if (data != -1) {
                                    baos.write(data);
                                } else {
                                    ImageDecoder.Source src = ImageDecoder.createSource(baos.toByteArray());
                                    Bitmap decodeBitmap = ImageDecoder.decodeBitmap(src, new ImageDecoder.OnHeaderDecodedListener() { // from class: android.app.WallpaperManager$Globals$$ExternalSyntheticLambda0
                                        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                                        public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                                            WallpaperManager.Globals.lambda$getCurrentWallpaperLocked$2(hardware, cmProxy, imageDecoder, imageInfo, source);
                                        }
                                    });
                                    bis.close();
                                    return decodeBitmap;
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                bis.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (IOException | OutOfMemoryError e) {
                        Log.w(WallpaperManager.TAG, "Can't decode file", e);
                    }
                }
                return null;
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$getCurrentWallpaperLocked$2(boolean hardware, ColorManagementProxy cmProxy, ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source source) {
            decoder.setMutableRequired(!hardware);
            if (cmProxy != null) {
                cmProxy.doColorManagement(decoder, info);
            }
        }

        private Bitmap getDefaultWallpaper(Context context, int which) {
            InputStream is = WallpaperManager.openDefaultWallpaper(context, which);
            if (is != null) {
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    return BitmapFactory.decodeStream(is, null, options);
                } catch (OutOfMemoryError e) {
                    Log.w(WallpaperManager.TAG, "Can't decode stream", e);
                } finally {
                    IoUtils.closeQuietly(is);
                }
            }
            return null;
        }
    }

    static void initGlobals(IWallpaperManager service, Looper looper) {
        synchronized (sSync) {
            if (sGlobals == null) {
                sGlobals = new Globals(service, looper);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WallpaperManager(IWallpaperManager service, Context context, Handler handler) {
        this.mWallpaperXStep = -1.0f;
        this.mWallpaperYStep = -1.0f;
        this.mContext = context;
        if (service != null) {
            initGlobals(service, context.getMainLooper());
        }
        this.mWcgEnabled = context.getResources().getConfiguration().isScreenWideColorGamut() && context.getResources().getBoolean(R.bool.config_enableWcgMode);
        this.mCmProxy = new ColorManagementProxy(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WallpaperManager() {
        this.mWallpaperXStep = -1.0f;
        this.mWallpaperYStep = -1.0f;
        this.mContext = null;
        this.mCmProxy = null;
        this.mWcgEnabled = false;
    }

    public static WallpaperManager getInstance(Context context) {
        return (WallpaperManager) context.getSystemService(Context.WALLPAPER_SERVICE);
    }

    public IWallpaperManager getIWallpaperManager() {
        return sGlobals.mService;
    }

    public boolean shouldEnableWideColorGamut() {
        return this.mWcgEnabled;
    }

    public Drawable getDrawable() {
        ColorManagementProxy cmProxy = getColorManagementProxy();
        Bitmap bm = sGlobals.peekWallpaperBitmap(this.mContext, true, 1, cmProxy);
        if (bm == null) {
            return null;
        }
        Drawable dr = new BitmapDrawable(this.mContext.getResources(), bm);
        dr.setDither(false);
        return dr;
    }

    public Drawable getBuiltInDrawable() {
        return getBuiltInDrawable(0, 0, false, 0.0f, 0.0f, 1);
    }

    public Drawable getBuiltInDrawable(int which) {
        return getBuiltInDrawable(0, 0, false, 0.0f, 0.0f, which);
    }

    public Drawable getBuiltInDrawable(int outWidth, int outHeight, boolean scaleToFit, float horizontalAlignment, float verticalAlignment) {
        return getBuiltInDrawable(outWidth, outHeight, scaleToFit, horizontalAlignment, verticalAlignment, 1);
    }

    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.graphics.Rect, android.graphics.BitmapFactory$Options] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.drawable.Drawable getBuiltInDrawable(int r20, int r21, boolean r22, float r23, float r24, int r25) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.WallpaperManager.getBuiltInDrawable(int, int, boolean, float, float, int):android.graphics.drawable.Drawable");
    }

    private static RectF getMaxCropRect(int inWidth, int inHeight, int outWidth, int outHeight, float horizontalAlignment, float verticalAlignment) {
        RectF cropRect = new RectF();
        if (inWidth / inHeight > outWidth / outHeight) {
            cropRect.top = 0.0f;
            cropRect.bottom = inHeight;
            float cropWidth = outWidth * (inHeight / outHeight);
            cropRect.left = (inWidth - cropWidth) * horizontalAlignment;
            cropRect.right = cropRect.left + cropWidth;
        } else {
            cropRect.left = 0.0f;
            cropRect.right = inWidth;
            float cropHeight = outHeight * (inWidth / outWidth);
            cropRect.top = (inHeight - cropHeight) * verticalAlignment;
            cropRect.bottom = cropRect.top + cropHeight;
        }
        return cropRect;
    }

    public Drawable peekDrawable() {
        ColorManagementProxy cmProxy = getColorManagementProxy();
        Bitmap bm = sGlobals.peekWallpaperBitmap(this.mContext, false, 1, cmProxy);
        if (bm == null) {
            return null;
        }
        Drawable dr = new BitmapDrawable(this.mContext.getResources(), bm);
        dr.setDither(false);
        return dr;
    }

    public Drawable getFastDrawable() {
        ColorManagementProxy cmProxy = getColorManagementProxy();
        Bitmap bm = sGlobals.peekWallpaperBitmap(this.mContext, true, 1, cmProxy);
        if (bm != null) {
            return new FastBitmapDrawable(bm);
        }
        return null;
    }

    public Drawable peekFastDrawable() {
        ColorManagementProxy cmProxy = getColorManagementProxy();
        Bitmap bm = sGlobals.peekWallpaperBitmap(this.mContext, false, 1, cmProxy);
        if (bm != null) {
            return new FastBitmapDrawable(bm);
        }
        return null;
    }

    public boolean wallpaperSupportsWcg(int which) {
        ColorManagementProxy cmProxy;
        Bitmap bitmap;
        return (!shouldEnableWideColorGamut() || (bitmap = sGlobals.peekWallpaperBitmap(this.mContext, false, which, (cmProxy = getColorManagementProxy()))) == null || bitmap.getColorSpace() == null || bitmap.getColorSpace() == ColorSpace.get(ColorSpace.Named.SRGB) || !cmProxy.isSupportedColorSpace(bitmap.getColorSpace())) ? false : true;
    }

    public Bitmap getBitmap() {
        return getBitmap(false);
    }

    public Bitmap getBitmap(boolean hardware) {
        return getBitmapAsUser(this.mContext.getUserId(), hardware);
    }

    public Bitmap getBitmapAsUser(int userId, boolean hardware) {
        ColorManagementProxy cmProxy = getColorManagementProxy();
        try {
            Trace.traceBegin(8L, "WallpaperManager.Globals.peekWallpaperBitmap");
            return sGlobals.peekWallpaperBitmap(this.mContext, true, 1, userId, hardware, cmProxy);
        } finally {
            Trace.traceEnd(8L);
        }
    }

    public ParcelFileDescriptor getWallpaperFile(int which) {
        return getWallpaperFile(which, this.mContext.getUserId());
    }

    public void addOnColorsChangedListener(OnColorsChangedListener listener, Handler handler) {
        addOnColorsChangedListener(listener, handler, this.mContext.getUserId());
    }

    public void addOnColorsChangedListener(OnColorsChangedListener listener, Handler handler, int userId) {
        sGlobals.addOnColorsChangedListener(listener, handler, userId, this.mContext.getDisplayId());
    }

    public void removeOnColorsChangedListener(OnColorsChangedListener callback) {
        removeOnColorsChangedListener(callback, this.mContext.getUserId());
    }

    public void removeOnColorsChangedListener(OnColorsChangedListener callback, int userId) {
        sGlobals.removeOnColorsChangedListener(callback, userId, this.mContext.getDisplayId());
    }

    public WallpaperColors getWallpaperColors(int which) {
        return getWallpaperColors(which, this.mContext.getUserId());
    }

    public WallpaperColors getWallpaperColors(int which, int userId) {
        StrictMode.assertUiContext(this.mContext, "getWallpaperColors");
        return sGlobals.getWallpaperColors(which, userId, this.mContext.getDisplayId());
    }

    public void addOnColorsChangedListener(LocalWallpaperColorConsumer callback, List<RectF> regions) throws IllegalArgumentException {
        for (RectF region : regions) {
            RectF rectF = LOCAL_COLOR_BOUNDS;
            if (!rectF.contains(region)) {
                throw new IllegalArgumentException("Regions must be within bounds " + rectF);
            }
        }
        sGlobals.addOnColorsChangedListener(callback, regions, 1, this.mContext.getUserId(), this.mContext.getDisplayId());
    }

    public void removeOnColorsChangedListener(LocalWallpaperColorConsumer callback) {
        sGlobals.removeOnColorsChangedListener(callback, 1, this.mContext.getUserId(), this.mContext.getDisplayId());
    }

    public ParcelFileDescriptor getWallpaperFile(int which, int userId) {
        if (which != 1 && which != 2) {
            throw new IllegalArgumentException("Must request exactly one kind of wallpaper");
        } else if (sGlobals.mService != null) {
            try {
                Bundle outParams = new Bundle();
                return sGlobals.mService.getWallpaperWithFeature(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), null, which, outParams, userId);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (SecurityException e2) {
                if (this.mContext.getApplicationInfo().targetSdkVersion < 27) {
                    Log.w(TAG, "No permission to access wallpaper, suppressing exception to avoid crashing legacy app.");
                    return null;
                }
                throw e2;
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public void forgetLoadedWallpaper() {
        sGlobals.forgetLoadedWallpaper();
    }

    public WallpaperInfo getWallpaperInfo() {
        return getWallpaperInfo(this.mContext.getUserId());
    }

    public WallpaperInfo getWallpaperInfo(int userId) {
        try {
            if (sGlobals.mService != null) {
                return sGlobals.mService.getWallpaperInfo(userId);
            }
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getWallpaperId(int which) {
        return getWallpaperIdForUser(which, this.mContext.getUserId());
    }

    public int getWallpaperIdForUser(int which, int userId) {
        try {
            if (sGlobals.mService != null) {
                return sGlobals.mService.getWallpaperIdForUser(which, userId);
            }
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Intent getCropAndSetWallpaperIntent(Uri imageUri) {
        if (imageUri == null) {
            throw new IllegalArgumentException("Image URI must not be null");
        } else if ("content".equals(imageUri.getScheme())) {
            PackageManager packageManager = this.mContext.getPackageManager();
            Intent cropAndSetWallpaperIntent = new Intent(ACTION_CROP_AND_SET_WALLPAPER, imageUri);
            cropAndSetWallpaperIntent.addFlags(1);
            Intent homeIntent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME);
            ResolveInfo resolvedHome = packageManager.resolveActivity(homeIntent, 65536);
            if (resolvedHome != null) {
                cropAndSetWallpaperIntent.setPackage(resolvedHome.activityInfo.packageName);
                List<ResolveInfo> cropAppList = packageManager.queryIntentActivities(cropAndSetWallpaperIntent, 0);
                if (cropAppList.size() > 0) {
                    return cropAndSetWallpaperIntent;
                }
            }
            String cropperPackage = this.mContext.getString(R.string.config_wallpaperCropperPackage);
            cropAndSetWallpaperIntent.setPackage(cropperPackage);
            List<ResolveInfo> cropAppList2 = packageManager.queryIntentActivities(cropAndSetWallpaperIntent, 0);
            if (cropAppList2.size() > 0) {
                return cropAndSetWallpaperIntent;
            }
            throw new IllegalArgumentException("Cannot use passed URI to set wallpaper; check that the type returned by ContentProvider matches image/*");
        } else {
            throw new IllegalArgumentException("Image URI must be of the content scheme type");
        }
    }

    public void setResource(int resid) throws IOException {
        setResource(resid, 3);
    }

    public int setResource(int resid, int which) throws IOException {
        if (sGlobals.mService != null) {
            Bundle result = new Bundle();
            WallpaperSetCompletion completion = new WallpaperSetCompletion();
            try {
                Resources resources = this.mContext.getResources();
                IWallpaperManager iWallpaperManager = sGlobals.mService;
                ParcelFileDescriptor fd = iWallpaperManager.setWallpaper("res:" + resources.getResourceName(resid), this.mContext.getOpPackageName(), null, false, result, which, completion, this.mContext.getUserId());
                if (fd != null) {
                    FileOutputStream fos = new ParcelFileDescriptor.AutoCloseOutputStream(fd);
                    copyStreamToWallpaperFile(resources.openRawResource(resid), fos);
                    fos.close();
                    completion.waitForCompletion();
                    IoUtils.closeQuietly(fos);
                }
                return result.getInt(EXTRA_NEW_WALLPAPER_ID, 0);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public void setBitmap(Bitmap bitmap) throws IOException {
        setBitmap(bitmap, null, true);
    }

    public int setBitmap(Bitmap fullImage, Rect visibleCropHint, boolean allowBackup) throws IOException {
        return setBitmap(fullImage, visibleCropHint, allowBackup, 3);
    }

    public int setBitmap(Bitmap fullImage, Rect visibleCropHint, boolean allowBackup, int which) throws IOException {
        return setBitmap(fullImage, visibleCropHint, allowBackup, which, this.mContext.getUserId());
    }

    public int setBitmap(Bitmap fullImage, Rect visibleCropHint, boolean allowBackup, int which, int userId) throws IOException {
        validateRect(visibleCropHint);
        if (sGlobals.mService != null) {
            Bundle result = new Bundle();
            WallpaperSetCompletion completion = new WallpaperSetCompletion();
            try {
                ParcelFileDescriptor fd = sGlobals.mService.setWallpaper(null, this.mContext.getOpPackageName(), visibleCropHint, allowBackup, result, which, completion, userId);
                if (fd != null) {
                    FileOutputStream fos = new ParcelFileDescriptor.AutoCloseOutputStream(fd);
                    fullImage.compress(Bitmap.CompressFormat.PNG, 90, fos);
                    fos.close();
                    completion.waitForCompletion();
                    IoUtils.closeQuietly(fos);
                }
                return result.getInt(EXTRA_NEW_WALLPAPER_ID, 0);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    private final void validateRect(Rect rect) {
        if (rect != null && rect.isEmpty()) {
            throw new IllegalArgumentException("visibleCrop rectangle must be valid and non-empty");
        }
    }

    public void setStream(InputStream bitmapData) throws IOException {
        setStream(bitmapData, null, true);
    }

    private void copyStreamToWallpaperFile(InputStream data, FileOutputStream fos) throws IOException {
        FileUtils.copy(data, fos);
    }

    public int setStream(InputStream bitmapData, Rect visibleCropHint, boolean allowBackup) throws IOException {
        return setStream(bitmapData, visibleCropHint, allowBackup, 3);
    }

    public int setStream(InputStream bitmapData, Rect visibleCropHint, boolean allowBackup, int which) throws IOException {
        validateRect(visibleCropHint);
        if (sGlobals.mService != null) {
            Bundle result = new Bundle();
            WallpaperSetCompletion completion = new WallpaperSetCompletion();
            try {
                ParcelFileDescriptor fd = sGlobals.mService.setWallpaper(null, this.mContext.getOpPackageName(), visibleCropHint, allowBackup, result, which, completion, this.mContext.getUserId());
                if (fd != null) {
                    FileOutputStream fos = new ParcelFileDescriptor.AutoCloseOutputStream(fd);
                    copyStreamToWallpaperFile(bitmapData, fos);
                    fos.close();
                    completion.waitForCompletion();
                    IoUtils.closeQuietly(fos);
                }
                return result.getInt(EXTRA_NEW_WALLPAPER_ID, 0);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public boolean hasResourceWallpaper(int resid) {
        if (sGlobals.mService != null) {
            try {
                Resources resources = this.mContext.getResources();
                String name = "res:" + resources.getResourceName(resid);
                return sGlobals.mService.hasNamedWallpaper(name);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public int getDesiredMinimumWidth() {
        StrictMode.assertUiContext(this.mContext, "getDesiredMinimumWidth");
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.getWidthHint(this.mContext.getDisplayId());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public int getDesiredMinimumHeight() {
        StrictMode.assertUiContext(this.mContext, "getDesiredMinimumHeight");
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.getHeightHint(this.mContext.getDisplayId());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public void suggestDesiredDimensions(int minimumWidth, int minimumHeight) {
        int maximumTextureSize;
        StrictMode.assertUiContext(this.mContext, "suggestDesiredDimensions");
        try {
            maximumTextureSize = SystemProperties.getInt("sys.max_texture_size", 0);
        } catch (Exception e) {
            maximumTextureSize = 0;
        }
        if (maximumTextureSize > 0 && (minimumWidth > maximumTextureSize || minimumHeight > maximumTextureSize)) {
            float aspect = minimumHeight / minimumWidth;
            if (minimumWidth > minimumHeight) {
                minimumWidth = maximumTextureSize;
                minimumHeight = (int) ((minimumWidth * aspect) + 0.5d);
            } else {
                minimumHeight = maximumTextureSize;
                minimumWidth = (int) ((minimumHeight / aspect) + 0.5d);
            }
        }
        try {
            if (sGlobals.mService != null) {
                sGlobals.mService.setDimensionHints(minimumWidth, minimumHeight, this.mContext.getOpPackageName(), this.mContext.getDisplayId());
            } else {
                Log.w(TAG, "WallpaperService not running");
                throw new RuntimeException(new DeadSystemException());
            }
        } catch (RemoteException e2) {
            throw e2.rethrowFromSystemServer();
        }
    }

    public void setDisplayPadding(Rect padding) {
        StrictMode.assertUiContext(this.mContext, "setDisplayPadding");
        try {
            if (sGlobals.mService != null) {
                sGlobals.mService.setDisplayPadding(padding, this.mContext.getOpPackageName(), this.mContext.getDisplayId());
            } else {
                Log.w(TAG, "WallpaperService not running");
                throw new RuntimeException(new DeadSystemException());
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setDisplayOffset(IBinder windowToken, int x, int y) {
        try {
            WindowManagerGlobal.getWindowSession().setWallpaperDisplayOffset(windowToken, x, y);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void clearWallpaper() {
        clearWallpaper(1, this.mContext.getUserId());
        clearWallpaper(2, this.mContext.getUserId());
    }

    @SystemApi
    public void clearWallpaper(int which, int userId) {
        if (sGlobals.mService != null) {
            try {
                sGlobals.mService.clearWallpaper(this.mContext.getOpPackageName(), which, userId);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    @SystemApi
    public boolean setWallpaperComponent(ComponentName name) {
        return setWallpaperComponent(name, this.mContext.getUserId());
    }

    public boolean setWallpaperComponent(ComponentName name, int userId) {
        if (sGlobals.mService != null) {
            try {
                sGlobals.mService.setWallpaperComponentChecked(name, this.mContext.getOpPackageName(), userId);
                return true;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public void setWallpaperOffsets(IBinder windowToken, float xOffset, float yOffset) {
        try {
            WindowManagerGlobal.getWindowSession().setWallpaperPosition(windowToken, xOffset, yOffset, this.mWallpaperXStep, this.mWallpaperYStep);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setWallpaperOffsetSteps(float xStep, float yStep) {
        this.mWallpaperXStep = xStep;
        this.mWallpaperYStep = yStep;
    }

    public void sendWallpaperCommand(IBinder windowToken, String action, int x, int y, int z, Bundle extras) {
        try {
            WindowManagerGlobal.getWindowSession().sendWallpaperCommand(windowToken, action, x, y, z, extras, false);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setWallpaperZoomOut(IBinder windowToken, float zoom) {
        if (zoom < 0.0f || zoom > 1.0f) {
            throw new IllegalArgumentException("zoom must be between 0 and 1: " + zoom);
        } else if (windowToken != null) {
            if (DEBUG) {
                String str = TAG;
                Log.d(str, "setWallpaperZoomOut: zoom = " + zoom + " , called by " + this.mContext.getOpPackageName());
            }
            try {
                WindowManagerGlobal.getWindowSession().setWallpaperZoomOut(windowToken, zoom);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalArgumentException("windowToken must not be null");
        }
    }

    public boolean isWallpaperSupported() {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.isWallpaperSupported(this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public boolean isSetWallpaperAllowed() {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.isSetWallpaperAllowed(this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public void clearWallpaperOffsets(IBinder windowToken) {
        try {
            WindowManagerGlobal.getWindowSession().setWallpaperPosition(windowToken, -1.0f, -1.0f, -1.0f, -1.0f);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void clear() throws IOException {
        setStream(openDefaultWallpaper(this.mContext, 1), null, false);
    }

    public void clear(int which) throws IOException {
        if ((which & 1) != 0) {
            clear();
        }
        if ((which & 2) != 0) {
            clearWallpaper(2, this.mContext.getUserId());
        }
    }

    public static InputStream openDefaultWallpaper(Context context, int which) {
        InputStream is = sWallpaperManagerExt.openDefaultWallpaper(context, which);
        if (is != null) {
            return is;
        }
        if (which == 2) {
            return null;
        }
        String path = SystemProperties.get(PROP_WALLPAPER);
        InputStream wallpaperInputStream = getWallpaperInputStream(path);
        if (wallpaperInputStream != null) {
            return wallpaperInputStream;
        }
        String cmfPath = getCmfWallpaperPath();
        InputStream cmfWallpaperInputStream = getWallpaperInputStream(cmfPath);
        if (cmfWallpaperInputStream != null) {
            return cmfWallpaperInputStream;
        }
        try {
            return context.getResources().openRawResource(R.drawable.default_wallpaper);
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    private static InputStream getWallpaperInputStream(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (IOException e) {
            return null;
        }
    }

    private static String getCmfWallpaperPath() {
        return Environment.getProductDirectory() + WALLPAPER_CMF_PATH + "default_wallpaper_" + VALUE_CMF_COLOR;
    }

    public static ComponentName getDefaultWallpaperComponent(Context context) {
        ComponentName cn = null;
        String flat = SystemProperties.get(PROP_WALLPAPER_COMPONENT);
        if (!TextUtils.isEmpty(flat)) {
            cn = ComponentName.unflattenFromString(flat);
        }
        if (cn == null) {
            String flat2 = context.getString(R.string.default_wallpaper_component);
            if (!TextUtils.isEmpty(flat2)) {
                cn = ComponentName.unflattenFromString(flat2);
            }
        }
        if (cn == null) {
            cn = sWallpaperManagerExt.getDefaultWallpaperComponent(context);
        }
        String str = TAG;
        Log.i(str, "getDefaultWallpaperComponent: Default wallpaper component is " + cn);
        if (cn == null) {
            return cn;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo(cn.getPackageName(), 786432);
            return cn;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public boolean setLockWallpaperCallback(IWallpaperManagerCallback callback) {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.setLockWallpaperCallback(callback);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public boolean isWallpaperBackupEligible(int which) {
        if (sGlobals.mService != null) {
            try {
                return sGlobals.mService.isWallpaperBackupEligible(which, this.mContext.getUserId());
            } catch (RemoteException e) {
                String str = TAG;
                Log.e(str, "Exception querying wallpaper backup eligibility: " + e.getMessage());
                return false;
            }
        } else {
            Log.w(TAG, "WallpaperService not running");
            throw new RuntimeException(new DeadSystemException());
        }
    }

    public ColorManagementProxy getColorManagementProxy() {
        return this.mCmProxy;
    }

    /* loaded from: classes.dex */
    public static class ColorManagementProxy {
        private final Set<ColorSpace> mSupportedColorSpaces;

        public ColorManagementProxy(Context context) {
            HashSet hashSet = new HashSet();
            this.mSupportedColorSpaces = hashSet;
            Display display = context.getDisplayNoVerify();
            if (display != null) {
                hashSet.addAll(Arrays.asList(display.getSupportedWideColorGamut()));
            }
        }

        public Set<ColorSpace> getSupportedColorSpaces() {
            return this.mSupportedColorSpaces;
        }

        boolean isSupportedColorSpace(ColorSpace colorSpace) {
            return colorSpace != null && (colorSpace == ColorSpace.get(ColorSpace.Named.SRGB) || getSupportedColorSpaces().contains(colorSpace));
        }

        void doColorManagement(ImageDecoder decoder, ImageDecoder.ImageInfo info) {
            if (!isSupportedColorSpace(info.getColorSpace())) {
                decoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
                String str = WallpaperManager.TAG;
                Log.w(str, "Not supported color space: " + info.getColorSpace());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WallpaperSetCompletion extends IWallpaperManagerCallback.Stub {
        final CountDownLatch mLatch = new CountDownLatch(1);

        public WallpaperSetCompletion() {
        }

        public void waitForCompletion() {
            try {
                this.mLatch.await(30L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
            }
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperChanged() throws RemoteException {
            this.mLatch.countDown();
        }

        @Override // android.app.IWallpaperManagerCallback
        public void onWallpaperColorsChanged(WallpaperColors colors, int which, int userId) throws RemoteException {
            WallpaperManager.sGlobals.onWallpaperColorsChanged(colors, which, userId);
        }
    }

    /* loaded from: classes.dex */
    public interface OnColorsChangedListener {
        void onColorsChanged(WallpaperColors wallpaperColors, int i);

        default void onColorsChanged(WallpaperColors colors, int which, int userId) {
            onColorsChanged(colors, which);
        }
    }
}
