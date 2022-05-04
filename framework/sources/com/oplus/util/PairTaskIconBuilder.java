package com.oplus.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfiguration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.IAdaptiveIconDrawableExt;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.Log;
import com.oplus.multiapp.OplusMultiAppManager;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public class PairTaskIconBuilder {
    public static final int ART_PLUS_BIT_LENGTH = 4;
    public static final int DARKMODE_ICON_BIT_LENGTH = 1;
    public static final int DARKMODE_ICON_TRANSLATE_BIT_LENGTH = 61;
    private static final boolean DEBUG = false;
    public static final int FOREGROUND_SIZE_BIT_LENGTH = 16;
    public static final int FOREIGN_BIT_LENGTH = 4;
    public static final int ICON_RADIUS_BIT_LENGTH = 12;
    public static final int ICON_RADIUS_PX_BIT_LENGTH = 1;
    public static final int ICON_SHAPE_BIT_LENGTH = 4;
    public static final int ICON_SIZE_BIT_LENGTH = 16;
    private static final int ICON_WIDTH_DP = 108;
    private static final String TAG = "PairTaskIconBuilder";
    public static final int THEME_BIT_LENGTH = 4;
    public static final int THEME_CUSTOM = 3;
    public static final int THEME_MATERIAL = 1;
    public static final int THEME_PEBBLE = 4;
    public static final int THEME_RECTANGLE = 2;
    private Field mAdaptiveIconDrawableExt;
    private Context mContext;
    private int mIconHeight;
    private int mIconWidth;
    private boolean mIsArtPlusOn;
    private Long mUxIconConfig;
    private int mUxTheme;
    private String mTitle = "";
    private Icon mIcon = null;

    public PairTaskIconBuilder(Context context) {
        this.mContext = context;
        float density = context.getResources().getDisplayMetrics().density;
        int i = (int) (108.0f * density);
        this.mIconWidth = i;
        this.mIconHeight = i;
        Long valueOf = Long.valueOf(getUxIconConfig(context.getResources().getConfiguration()));
        this.mUxIconConfig = valueOf;
        Long valueOf2 = Long.valueOf(valueOf.longValue() >> 4);
        this.mUxIconConfig = valueOf2;
        this.mUxTheme = valueOf2.intValue() & 15;
        Long valueOf3 = Long.valueOf(this.mUxIconConfig.longValue() >> 4);
        this.mUxIconConfig = valueOf3;
        this.mIsArtPlusOn = (valueOf3.intValue() & 15) != 1 ? false : true;
    }

    private static long getUxIconConfig(Configuration configuration) {
        try {
            OplusBaseConfiguration baseConfiguration = typeCasting(configuration);
            if (baseConfiguration == null) {
                return -1L;
            }
            long result = baseConfiguration.mOplusExtraConfiguration.mUxIconConfig;
            return result;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return -1L;
        }
    }

    private static OplusBaseConfiguration typeCasting(Configuration configuration) {
        return (OplusBaseConfiguration) OplusTypeCastingHelper.typeCasting(OplusBaseConfiguration.class, configuration);
    }

    private static Class<?> findClass(String clazz) {
        try {
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTaskIcon(String pkgName1, int userId1, String pkgName2, int userId2) {
        String applicationName1 = getApplicationName(pkgName1, userId1);
        String applicationName2 = getApplicationName(pkgName2, userId2);
        Bitmap bmp = makeIcon(getApplicationIcon(pkgName1), pkgName1, getApplicationIcon(pkgName2), pkgName2);
        if (bmp != null) {
            this.mIcon = Icon.createWithBitmap(bmp);
        }
        if (!TextUtils.isEmpty(applicationName1) && !TextUtils.isEmpty(applicationName2)) {
            this.mTitle = applicationName1 + "|" + applicationName2;
        }
    }

    public Icon getIcon() {
        return this.mIcon;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Bitmap makeIcon(Drawable drawable1, String pkgName1, Drawable drawable2, String pkgName2) {
        if (!(drawable1 == null || drawable2 == null)) {
            Bitmap roundBitmap1 = toRoundBitmap(drawable1, pkgName1);
            Bitmap roundBitmap2 = toRoundBitmap(drawable2, pkgName2);
            if (roundBitmap1 == null || roundBitmap2 == null) {
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            RectF rect1 = new RectF(0.0f, 0.0f, this.mIconWidth * 0.75f, this.mIconHeight * 0.75f);
            canvas.drawBitmap(roundBitmap1, (Rect) null, rect1, paint);
            int i = this.mIconWidth;
            int i2 = this.mIconHeight;
            RectF rect2 = new RectF(i * 0.25f, i2 * 0.25f, i, i2);
            canvas.drawBitmap(roundBitmap2, (Rect) null, rect2, paint);
            return createBitmap;
        }
        Log.d(TAG, " makeIcon; Some parameters are null, return!");
        return null;
    }

    private Bitmap toRoundBitmap(Drawable drawable, String name) {
        if (drawable == null) {
            Log.d(TAG, " toRoundBitmap; Some parameters are null, return!");
            return null;
        }
        boolean isMaterialTheme = this.mUxTheme == 1;
        if (drawable instanceof AdaptiveIconDrawable) {
            float scaleF = 1.0f;
            try {
                IAdaptiveIconDrawableExt adaptiveIconDrawableExt = getAdaptiveIconDrawableExt((AdaptiveIconDrawable) drawable);
                if (adaptiveIconDrawableExt != null) {
                    scaleF = adaptiveIconDrawableExt.getForegroundScalePercent();
                }
                if (scaleF > 1.0f) {
                    scaleF = 1.5f;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, name + " toRoundBitmap; Current IconConfig: theme=" + this.mUxTheme + " isArtPlusOn=" + this.mIsArtPlusOn + " scaleF=" + scaleF);
            Bitmap foreground = drawableToBitmap(((AdaptiveIconDrawable) drawable).getForeground());
            Bitmap background = drawableToBitmap(((AdaptiveIconDrawable) drawable).getBackground());
            if (foreground == null && background == null) {
                Log.d(TAG, "IconDrawable's foreground and background are null.");
                return null;
            } else if (isMaterialTheme && background != null && foreground == null) {
                Bitmap roundBitmap = assembleForegroundAndBackground(background, toRoundBitmapInternal(getDefaultBackground()), true);
                return roundBitmap;
            } else if (foreground == null || background == null) {
                if (foreground != null) {
                    Bitmap roundBitmap2 = toRoundBitmapInternal(foreground);
                    return roundBitmap2;
                }
                Bitmap roundBitmap3 = toRoundBitmapInternal(background);
                return roundBitmap3;
            } else if (!this.mIsArtPlusOn) {
                Bitmap roundBitmap4 = assembleForegroundAndBackground(toRoundBitmapInternal(scaleBitmap(foreground, scaleF)), toRoundBitmapInternal(scaleBitmap(background, 1.0f)), this.mIsArtPlusOn);
                return roundBitmap4;
            } else {
                Bitmap roundBitmap5 = assembleForegroundAndBackground(foreground, toRoundBitmapInternal(background), this.mIsArtPlusOn);
                return roundBitmap5;
            }
        } else {
            Log.d(TAG, " toRoundBitmap; Drawable is not adaptive, return raw drawable!");
            Bitmap roundBitmap6 = drawableToBitmap(drawable);
            return roundBitmap6;
        }
    }

    private Bitmap getDefaultBackground() {
        Bitmap defaultBackground = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, Bitmap.Config.ARGB_8888);
        defaultBackground.eraseColor(-1);
        return defaultBackground;
    }

    private Bitmap toRoundBitmapInternal(Bitmap bm) {
        int r;
        if (bm == null) {
            Log.d(TAG, " toRoundBitmapInternal; Some parameters are null, return!");
            return null;
        }
        int i = this.mIconWidth;
        int i2 = this.mIconHeight;
        if (i > i2) {
            r = this.mIconHeight;
        } else {
            r = this.mIconWidth;
        }
        Bitmap backgroundBmp = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rect = new RectF(0.0f, 0.0f, r, r);
        canvas.drawRoundRect(rect, r / 2, r / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bm, (Rect) null, rect, paint);
        return backgroundBmp;
    }

    private Bitmap assembleForegroundAndBackground(Bitmap foreground, Bitmap background, boolean scaleForeground) {
        if (foreground == null || background == null) {
            Log.d(TAG, " assembleForegroundAndBackground; Some parameters are null, return!");
            return null;
        }
        Bitmap assembledBmp = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(assembledBmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(background, (Rect) null, new RectF(0.0f, 0.0f, this.mIconWidth, this.mIconHeight), paint);
        Matrix matrix = new Matrix();
        if (scaleForeground) {
            matrix.setScale(0.7f, 0.7f, this.mIconWidth / 2, this.mIconHeight / 2);
        }
        canvas.drawBitmap(foreground, matrix, paint);
        return assembledBmp;
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            Log.d(TAG, " drawableToBitmap; Some parameters are null, return!");
            return null;
        }
        Bitmap.Config config = drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, this.mIconWidth, this.mIconHeight);
        drawable.draw(canvas);
        return bitmap;
    }

    private Bitmap scaleBitmap(Bitmap bitmap, float scale) {
        if (bitmap == null) {
            return null;
        }
        Bitmap scaledBitmap = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(scaledBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale, this.mIconWidth / 2, this.mIconHeight / 2);
        canvas.drawBitmap(bitmap, matrix, paint);
        return scaledBitmap;
    }

    private String getApplicationName(String pkg, int userId) {
        try {
            if (userId == 999) {
                return OplusMultiAppManager.getInstance().getMultiAppAlias(pkg);
            }
            PackageManager pm = this.mContext.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfoAsUser(pkg, 0, ActivityManager.getCurrentUser());
            return pm.getApplicationLabel(info).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Drawable getApplicationIcon(String pkg) {
        try {
            PackageManager pm = this.mContext.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfoAsUser(pkg, 0, ActivityManager.getCurrentUser());
            return info.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private IAdaptiveIconDrawableExt getAdaptiveIconDrawableExt(AdaptiveIconDrawable drawable) {
        if (this.mAdaptiveIconDrawableExt == null) {
            try {
                Class<?> clazz = findClass("android.graphics.drawable.AdaptiveIconDrawable");
                if (clazz != null) {
                    Field declaredField = clazz.getDeclaredField("mIconDrawableExt");
                    this.mAdaptiveIconDrawableExt = declaredField;
                    declaredField.setAccessible(true);
                }
            } catch (Exception e) {
            }
        }
        Field field = this.mAdaptiveIconDrawableExt;
        if (field == null) {
            return null;
        }
        try {
            return (IAdaptiveIconDrawableExt) field.get(drawable);
        } catch (Exception e2) {
            return null;
        }
    }
}
