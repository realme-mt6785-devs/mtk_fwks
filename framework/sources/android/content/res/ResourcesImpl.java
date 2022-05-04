package android.content.res;

import android.animation.Animator;
import android.animation.StateListAnimator;
import android.common.OplusExtPluginFactory;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.FontResourcesParser;
import android.content.res.Resources;
import android.content.res.XmlBlock;
import android.graphics.ImageDecoder;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.icu.text.PluralRules;
import android.os.LocaleList;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.DisplayAdjustments;
import com.android.internal.util.GrowingArrayUtils;
import com.mediatek.res.ResOptExtFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import libcore.util.NativeAllocationRegistry;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ResourcesImpl {
    private static final boolean DEBUG_CONFIG = false;
    private static final boolean DEBUG_LOAD = false;
    private static final int ID_OTHER = 16777220;
    static final String TAG = "Resources";
    private static final boolean TRACE_FOR_MISS_PRELOAD = false;
    private static final boolean TRACE_FOR_PRELOAD = false;
    private static final int XML_BLOCK_CACHE_SIZE = 4;
    private static boolean sPreloaded;
    private static final LongSparseArray<Drawable.ConstantState>[] sPreloadedDrawables;
    final AssetManager mAssets;
    private final Configuration mConfiguration;
    private final DisplayAdjustments mDisplayAdjustments;
    private final DisplayMetrics mMetrics;
    private PluralRules mPluralRule;
    private boolean mPreloading;
    public IResourcesImplExt mResourcesImplExt;
    private static final Object sSync = new Object();
    private static final LongSparseArray<Drawable.ConstantState> sPreloadedColorDrawables = new LongSparseArray<>();
    private static final LongSparseArray<ConstantState<ComplexColor>> sPreloadedComplexColors = new LongSparseArray<>();
    private static Context mAppContext = null;
    private static final NativeAllocationRegistry sThemeRegistry = NativeAllocationRegistry.createMalloced(ResourcesImpl.class.getClassLoader(), AssetManager.getThemeFreeFunction());
    private final Object mAccessLock = new Object();
    private final Configuration mTmpConfig = new Configuration();
    private final DrawableCache mDrawableCache = new DrawableCache();
    private final DrawableCache mColorDrawableCache = new DrawableCache();
    private final ConfigurationBoundResourceCache<ComplexColor> mComplexColorCache = new ConfigurationBoundResourceCache<>();
    private final ConfigurationBoundResourceCache<Animator> mAnimatorCache = new ConfigurationBoundResourceCache<>();
    private final ConfigurationBoundResourceCache<StateListAnimator> mStateListAnimatorCache = new ConfigurationBoundResourceCache<>();
    private final ThreadLocal<LookupStack> mLookupStack = ThreadLocal.withInitial(ResourcesImpl$$ExternalSyntheticLambda1.INSTANCE);
    private int mLastCachedXmlBlockIndex = -1;
    private final int[] mCachedXmlBlockCookies = new int[4];
    private final String[] mCachedXmlBlockFiles = new String[4];
    private final XmlBlock[] mCachedXmlBlocks = new XmlBlock[4];

    static {
        sPreloadedDrawables = r0;
        LongSparseArray<Drawable.ConstantState>[] longSparseArrayArr = {new LongSparseArray<>(), new LongSparseArray<>()};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LookupStack lambda$new$0() {
        return new LookupStack();
    }

    public ResourcesImpl(AssetManager assets, DisplayMetrics metrics, Configuration config, DisplayAdjustments displayAdjustments) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mMetrics = displayMetrics;
        Configuration configuration = new Configuration();
        this.mConfiguration = configuration;
        this.mResourcesImplExt = IResourcesImplExt.DEFAULT;
        this.mAssets = assets;
        displayMetrics.setToDefaults();
        this.mDisplayAdjustments = displayAdjustments;
        configuration.setToDefaults();
        this.mResourcesImplExt = (IResourcesImplExt) OplusExtPluginFactory.getInstance().getFeature(IResourcesImplExt.DEFAULT, this);
        updateConfiguration(config, metrics, displayAdjustments.getCompatibilityInfo());
    }

    public DisplayAdjustments getDisplayAdjustments() {
        return this.mDisplayAdjustments;
    }

    public AssetManager getAssets() {
        return this.mAssets;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics adjustMetrics = this.mResourcesImplExt.getCompactWindowMetrics(this, this.mMetrics);
        if (adjustMetrics != null) {
            return adjustMetrics;
        }
        return this.mMetrics;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Configuration[] getSizeConfigurations() {
        return this.mAssets.getSizeConfigurations();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompatibilityInfo getCompatibilityInfo() {
        return this.mDisplayAdjustments.getCompatibilityInfo();
    }

    private PluralRules getPluralRule() {
        PluralRules pluralRules;
        synchronized (sSync) {
            if (this.mPluralRule == null) {
                this.mPluralRule = PluralRules.forLocale(this.mConfiguration.getLocales().get(0));
            }
            pluralRules = this.mPluralRule;
        }
        return pluralRules;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getValue(int id, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {
        boolean found = this.mAssets.getResourceValue(id, 0, outValue, resolveRefs);
        if (found) {
            this.mResourcesImplExt.getExValue(id, outValue, resolveRefs);
            return;
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getValueForDensity(int id, int density, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {
        boolean found = this.mAssets.getResourceValue(id, density, outValue, resolveRefs);
        if (found) {
            this.mResourcesImplExt.getExValue(id, outValue, resolveRefs);
            return;
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(id));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getValue(String name, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {
        int id = getIdentifier(name, "string", null);
        if (id != 0) {
            getValue(id, outValue, resolveRefs);
            return;
        }
        throw new Resources.NotFoundException("String resource name " + name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getIdentifier(String name, String defType, String defPackage) {
        if (name != null) {
            try {
                return Integer.parseInt(name);
            } catch (Exception e) {
                return this.mAssets.getResourceIdentifier(name, defType, defPackage);
            }
        } else {
            throw new NullPointerException("name is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getResourceName(int resid) throws Resources.NotFoundException {
        String str = this.mAssets.getResourceName(resid);
        if (str != null) {
            return str;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(resid));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getResourcePackageName(int resid) throws Resources.NotFoundException {
        String str = this.mAssets.getResourcePackageName(resid);
        if (str != null) {
            return str;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(resid));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getResourceTypeName(int resid) throws Resources.NotFoundException {
        String str = this.mAssets.getResourceTypeName(resid);
        if (str != null) {
            return str;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(resid));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getResourceEntryName(int resid) throws Resources.NotFoundException {
        String str = this.mAssets.getResourceEntryName(resid);
        if (str != null) {
            return str;
        }
        throw new Resources.NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(resid));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getLastResourceResolution() throws Resources.NotFoundException {
        String str = this.mAssets.getLastResourceResolution();
        if (str != null) {
            return str;
        }
        throw new Resources.NotFoundException("Associated AssetManager hasn't resolved a resource");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence getQuantityText(int id, int quantity) throws Resources.NotFoundException {
        PluralRules rule = getPluralRule();
        CharSequence res = this.mAssets.getResourceBagText(id, attrForQuantityCode(rule.select(quantity)));
        if (res != null) {
            return res;
        }
        CharSequence res2 = this.mAssets.getResourceBagText(id, ID_OTHER);
        if (res2 != null) {
            return res2;
        }
        throw new Resources.NotFoundException("Plural resource ID #0x" + Integer.toHexString(id) + " quantity=" + quantity + " item=" + rule.select(quantity));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int attrForQuantityCode(String quantityCode) {
        char c;
        switch (quantityCode.hashCode()) {
            case 101272:
                if (quantityCode.equals("few")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 110182:
                if (quantityCode.equals("one")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 115276:
                if (quantityCode.equals("two")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3343967:
                if (quantityCode.equals("many")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3735208:
                if (quantityCode.equals("zero")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 16777221;
            case 1:
                return 16777222;
            case 2:
                return 16777223;
            case 3:
                return 16777224;
            case 4:
                return 16777225;
            default:
                return ID_OTHER;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssetFileDescriptor openRawResourceFd(int id, TypedValue tempValue) throws Resources.NotFoundException {
        getValue(id, tempValue, true);
        try {
            return this.mAssets.openNonAssetFd(tempValue.assetCookie, tempValue.string.toString());
        } catch (Exception e) {
            throw new Resources.NotFoundException("File " + tempValue.string.toString() + " from resource ID #0x" + Integer.toHexString(id), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputStream openRawResource(int id, TypedValue value) throws Resources.NotFoundException {
        getValue(id, value, true);
        InputStream input = this.mResourcesImplExt.openThemeRawResource(id, value);
        if (input != null) {
            return input;
        }
        try {
            return this.mAssets.openNonAsset(value.assetCookie, value.string.toString(), 2);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("File ");
            sb.append(value.string == null ? "(null)" : value.string.toString());
            sb.append(" from resource ID #0x");
            sb.append(Integer.toHexString(id));
            Resources.NotFoundException rnf = new Resources.NotFoundException(sb.toString());
            rnf.initCause(e);
            throw rnf;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigurationBoundResourceCache<Animator> getAnimatorCache() {
        return this.mAnimatorCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigurationBoundResourceCache<StateListAnimator> getStateListAnimatorCache() {
        return this.mStateListAnimatorCache;
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x0182 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateConfiguration(android.content.res.Configuration r37, android.util.DisplayMetrics r38, android.content.res.CompatibilityInfo r39) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.res.ResourcesImpl.updateConfiguration(android.content.res.Configuration, android.util.DisplayMetrics, android.content.res.CompatibilityInfo):void");
    }

    public int calcConfigChanges(Configuration config) {
        if (config == null) {
            return -1;
        }
        this.mTmpConfig.setTo(config);
        int density = config.densityDpi;
        if (density == 0) {
            density = this.mMetrics.noncompatDensityDpi;
        }
        this.mDisplayAdjustments.getCompatibilityInfo().applyToConfiguration(density, this.mTmpConfig);
        if (this.mTmpConfig.getLocales().isEmpty()) {
            this.mTmpConfig.setLocales(LocaleList.getDefault());
        }
        return this.mConfiguration.updateFrom(this.mTmpConfig);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
        if (r3.equals("id") != false) goto L_0x003d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String adjustLanguageTag(java.lang.String r6) {
        /*
            r0 = 45
            int r0 = r6.indexOf(r0)
            r1 = 0
            r2 = -1
            if (r0 != r2) goto L_0x000e
            r3 = r6
            java.lang.String r4 = ""
            goto L_0x0016
        L_0x000e:
            java.lang.String r3 = r6.substring(r1, r0)
            java.lang.String r4 = r6.substring(r0)
        L_0x0016:
            int r5 = r3.hashCode()
            switch(r5) {
                case 3325: goto L_0x0032;
                case 3355: goto L_0x0029;
                case 3856: goto L_0x001e;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x003c
        L_0x001e:
            java.lang.String r1 = "yi"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x001d
            r1 = 1
            goto L_0x003d
        L_0x0029:
            java.lang.String r5 = "id"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x001d
            goto L_0x003d
        L_0x0032:
            java.lang.String r1 = "he"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x001d
            r1 = 2
            goto L_0x003d
        L_0x003c:
            r1 = r2
        L_0x003d:
            switch(r1) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0045;
                case 2: goto L_0x0042;
                default: goto L_0x0040;
            }
        L_0x0040:
            r1 = r3
            goto L_0x004b
        L_0x0042:
            java.lang.String r1 = "iw"
            goto L_0x004b
        L_0x0045:
            java.lang.String r1 = "ji"
            goto L_0x004b
        L_0x0048:
            java.lang.String r1 = "in"
        L_0x004b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.res.ResourcesImpl.adjustLanguageTag(java.lang.String):java.lang.String");
    }

    public void flushLayoutCache() {
        synchronized (this.mCachedXmlBlocks) {
            Arrays.fill(this.mCachedXmlBlockCookies, 0);
            Arrays.fill(this.mCachedXmlBlockFiles, (Object) null);
            XmlBlock[] cachedXmlBlocks = this.mCachedXmlBlocks;
            for (int i = 0; i < 4; i++) {
                XmlBlock oldBlock = cachedXmlBlocks[i];
                if (oldBlock != null) {
                    oldBlock.close();
                }
            }
            Arrays.fill(cachedXmlBlocks, (Object) null);
        }
    }

    public void clearAllCaches() {
        synchronized (this.mAccessLock) {
            this.mDrawableCache.clear();
            this.mColorDrawableCache.clear();
            this.mComplexColorCache.clear();
            this.mAnimatorCache.clear();
            this.mStateListAnimatorCache.clear();
            flushLayoutCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Drawable loadDrawable(Resources wrapper, TypedValue value, int id, int density, Resources.Theme theme) throws Resources.NotFoundException {
        int i;
        Exception e;
        String name;
        boolean isColorDrawable;
        DrawableCache caches;
        long key;
        Drawable.ConstantState cs;
        Drawable dr;
        boolean needsNewDrawableAfterCache;
        Drawable dr2;
        Drawable dr3;
        Drawable.ConstantState state;
        boolean canApplyTheme = true;
        boolean useCache = density == 0 || value.density == this.mMetrics.densityDpi;
        if (density > 0 && value.density > 0 && value.density != 65535) {
            if (value.density == density) {
                value.density = this.mMetrics.densityDpi;
            } else {
                value.density = (value.density * this.mMetrics.densityDpi) / density;
            }
        }
        try {
            if (value.type < 28 || value.type > 31) {
                key = (value.assetCookie << 32) | value.data;
                isColorDrawable = false;
                caches = this.mDrawableCache;
            } else {
                DrawableCache caches2 = this.mColorDrawableCache;
                key = value.data;
                isColorDrawable = true;
                caches = caches2;
            }
            if (!this.mPreloading && useCache) {
                Drawable cachedDrawable = caches.getInstance(key, wrapper, theme);
                if (cachedDrawable != null) {
                    cachedDrawable.setChangingConfigurations(value.changingConfigurations);
                    return cachedDrawable;
                }
                synchronized (this.mAccessLock) {
                    Drawable asyncResDrawable = ResOptExtFactory.getInstance().makeResOptExt().getCachedDrawable(wrapper, key, id);
                    if (asyncResDrawable != null) {
                        return asyncResDrawable;
                    }
                }
            }
            if (isColorDrawable) {
                cs = sPreloadedColorDrawables.get(key);
            } else {
                cs = sPreloadedDrawables[this.mConfiguration.getLayoutDirection()].get(key);
            }
            if (cs != null) {
                dr = cs.newDrawable(wrapper);
            } else if (isColorDrawable) {
                dr = new ColorDrawable(value.data);
            } else {
                dr = loadDrawableForCookie(wrapper, value, id, density);
            }
            if (dr instanceof DrawableContainer) {
                needsNewDrawableAfterCache = true;
            } else {
                needsNewDrawableAfterCache = false;
            }
            if (dr == null || !dr.canApplyTheme()) {
                canApplyTheme = false;
            }
            if (!canApplyTheme || theme == null) {
                dr2 = dr;
            } else {
                Drawable dr4 = dr.mutate();
                dr4.applyTheme(theme);
                dr4.clearMutated();
                dr2 = dr4;
            }
            if (dr2 != null) {
                dr2.setChangingConfigurations(value.changingConfigurations);
                if (useCache) {
                    dr3 = dr2;
                    i = 0;
                    try {
                        cacheDrawable(value, isColorDrawable, caches, theme, canApplyTheme, key, dr3);
                        if (needsNewDrawableAfterCache && (state = dr3.getConstantState()) != null) {
                            return state.newDrawable(wrapper);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            name = getResourceName(id);
                        } catch (Resources.NotFoundException e3) {
                            name = "(missing name)";
                        }
                        Resources.NotFoundException nfe = new Resources.NotFoundException("Drawable " + name + " with resource ID #0x" + Integer.toHexString(id), e);
                        nfe.setStackTrace(new StackTraceElement[i]);
                        throw nfe;
                    }
                } else {
                    dr3 = dr2;
                }
            } else {
                dr3 = dr2;
            }
            return dr3;
        } catch (Exception e4) {
            e = e4;
            i = 0;
        }
    }

    private void cacheDrawable(TypedValue value, boolean isColorDrawable, DrawableCache caches, Resources.Theme theme, boolean usesTheme, long key, Drawable dr) {
        Drawable.ConstantState cs = dr.getConstantState();
        if (cs != null) {
            if (this.mPreloading) {
                int changingConfigs = cs.getChangingConfigurations();
                if (isColorDrawable) {
                    if (verifyPreloadConfig(changingConfigs, 0, value.resourceId, "drawable")) {
                        sPreloadedColorDrawables.put(key, cs);
                    }
                } else if (!verifyPreloadConfig(changingConfigs, 8192, value.resourceId, "drawable")) {
                } else {
                    if ((changingConfigs & 8192) == 0) {
                        LongSparseArray<Drawable.ConstantState>[] longSparseArrayArr = sPreloadedDrawables;
                        longSparseArrayArr[0].put(key, cs);
                        longSparseArrayArr[1].put(key, cs);
                        return;
                    }
                    sPreloadedDrawables[this.mConfiguration.getLayoutDirection()].put(key, cs);
                }
            } else {
                synchronized (this.mAccessLock) {
                    caches.put(key, theme, cs, usesTheme);
                    if (!isColorDrawable) {
                        ResOptExtFactory.getInstance().makeResOptExt().putCacheList(key, dr, value.resourceId, mAppContext);
                    }
                }
            }
        }
    }

    private boolean verifyPreloadConfig(int changingConfigurations, int allowVarying, int resourceId, String name) {
        String resName;
        if (((-1073745921) & changingConfigurations & (~allowVarying)) == 0) {
            return true;
        }
        try {
            resName = getResourceName(resourceId);
        } catch (Resources.NotFoundException e) {
            resName = "?";
        }
        Log.w(TAG, "Preloaded " + name + " resource #0x" + Integer.toHexString(resourceId) + " (" + resName + ") that varies with configuration!!");
        return false;
    }

    private Drawable decodeImageDrawable(AssetManager.AssetInputStream ais, Resources wrapper, TypedValue value) {
        ImageDecoder.Source src = new ImageDecoder.AssetInputStreamSource(ais, wrapper, value);
        try {
            return ImageDecoder.decodeDrawable(src, ResourcesImpl$$ExternalSyntheticLambda0.INSTANCE);
        } catch (IOException e) {
            return null;
        }
    }

    private Drawable loadDrawableForCookie(Resources wrapper, TypedValue value, int id, int density) {
        if (value.string != null) {
            String file = value.string.toString();
            Trace.traceBegin(8192L, file);
            LookupStack stack = this.mLookupStack.get();
            try {
                if (!stack.contains(id)) {
                    stack.push(id);
                    Drawable dr = this.mResourcesImplExt.loadOverlayDrawable(wrapper, value, id);
                    if (dr == null) {
                        if (file.endsWith(".xml")) {
                            String typeName = getResourceTypeName(id);
                            if (typeName == null || !typeName.equals("color")) {
                                dr = loadXmlDrawable(wrapper, value, id, density, file);
                            } else {
                                dr = loadColorOrXmlDrawable(wrapper, value, id, density, file);
                            }
                        } else {
                            InputStream is = this.mAssets.openNonAsset(value.assetCookie, file, 2);
                            AssetManager.AssetInputStream ais = (AssetManager.AssetInputStream) is;
                            dr = decodeImageDrawable(ais, wrapper, value);
                        }
                    }
                    stack.pop();
                    Trace.traceEnd(8192L);
                    return dr;
                }
                throw new Exception("Recursive reference in drawable");
            } catch (Exception | StackOverflowError e) {
                Trace.traceEnd(8192L);
                Resources.NotFoundException rnf = new Resources.NotFoundException("File " + file + " from drawable resource ID #0x" + Integer.toHexString(id));
                rnf.initCause(e);
                throw rnf;
            }
        } else {
            throw new Resources.NotFoundException("Resource \"" + getResourceName(id) + "\" (" + Integer.toHexString(id) + ") is not a Drawable (color or path): " + value);
        }
    }

    private Drawable loadColorOrXmlDrawable(Resources wrapper, TypedValue value, int id, int density, String file) {
        try {
            ColorStateList csl = loadColorStateList(wrapper, value, id, null);
            return new ColorStateListDrawable(csl);
        } catch (Resources.NotFoundException originalException) {
            try {
                return loadXmlDrawable(wrapper, value, id, density, file);
            } catch (Exception e) {
                throw originalException;
            }
        }
    }

    private Drawable loadXmlDrawable(Resources wrapper, TypedValue value, int id, int density, String file) throws IOException, XmlPullParserException {
        XmlResourceParser rp = loadXmlResourceParser(file, id, value.assetCookie, "drawable");
        try {
            Drawable createFromXmlForDensity = Drawable.createFromXmlForDensity(wrapper, rp, density, null);
            if (rp != null) {
                rp.close();
            }
            return createFromXmlForDensity;
        } catch (Throwable th) {
            if (rp != null) {
                try {
                    rp.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public Typeface loadFont(Resources wrapper, TypedValue value, int id) {
        if (value.string != null) {
            String file = value.string.toString();
            if (!file.startsWith("res/")) {
                return null;
            }
            Typeface cached = Typeface.findFromCache(this.mAssets, file);
            if (cached != null) {
                return cached;
            }
            Trace.traceBegin(8192L, file);
            try {
                try {
                    if (!file.endsWith("xml")) {
                        return new Typeface.Builder(this.mAssets, file, false, value.assetCookie).build();
                    }
                    XmlResourceParser rp = loadXmlResourceParser(file, id, value.assetCookie, Context.FONT_SERVICE);
                    FontResourcesParser.FamilyResourceEntry familyEntry = FontResourcesParser.parse(rp, wrapper);
                    if (familyEntry == null) {
                        return null;
                    }
                    return Typeface.createFromResources(familyEntry, this.mAssets, file);
                } catch (IOException e) {
                    Log.e(TAG, "Failed to read xml resource " + file, e);
                    return null;
                } catch (XmlPullParserException e2) {
                    Log.e(TAG, "Failed to parse xml resource " + file, e2);
                    return null;
                }
            } finally {
                Trace.traceEnd(8192L);
            }
        } else {
            throw new Resources.NotFoundException("Resource \"" + getResourceName(id) + "\" (" + Integer.toHexString(id) + ") is not a Font: " + value);
        }
    }

    private ComplexColor loadComplexColorFromName(Resources wrapper, Resources.Theme theme, TypedValue value, int id) {
        long key = (value.assetCookie << 32) | value.data;
        ConfigurationBoundResourceCache<ComplexColor> cache = this.mComplexColorCache;
        ComplexColor complexColor = cache.getInstance(key, wrapper, theme);
        if (complexColor != null) {
            return complexColor;
        }
        LongSparseArray<ConstantState<ComplexColor>> longSparseArray = sPreloadedComplexColors;
        ConstantState<ComplexColor> factory = longSparseArray.get(key);
        if (factory != null) {
            complexColor = factory.newInstance(wrapper, theme);
        }
        if (complexColor == null) {
            complexColor = loadComplexColorForCookie(wrapper, value, id, theme);
        }
        if (complexColor != null) {
            complexColor.setBaseChangingConfigurations(value.changingConfigurations);
            if (!this.mPreloading) {
                cache.put(key, theme, complexColor.getConstantState());
            } else if (verifyPreloadConfig(complexColor.getChangingConfigurations(), 0, value.resourceId, "color")) {
                longSparseArray.put(key, complexColor.getConstantState());
            }
        }
        return complexColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComplexColor loadComplexColor(Resources wrapper, TypedValue value, int id, Resources.Theme theme) {
        long key = (value.assetCookie << 32) | value.data;
        if (value.type >= 28 && value.type <= 31) {
            return getColorStateListFromInt(value, key);
        }
        String file = value.string.toString();
        if (file.endsWith(".xml")) {
            try {
                ComplexColor complexColor = loadComplexColorFromName(wrapper, theme, value, id);
                return complexColor;
            } catch (Exception e) {
                Resources.NotFoundException rnf = new Resources.NotFoundException("File " + file + " from complex color resource ID #0x" + Integer.toHexString(id));
                rnf.initCause(e);
                throw rnf;
            }
        } else {
            throw new Resources.NotFoundException("File " + file + " from drawable resource ID #0x" + Integer.toHexString(id) + ": .xml extension required");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList loadColorStateList(Resources wrapper, TypedValue value, int id, Resources.Theme theme) throws Resources.NotFoundException {
        long key = (value.assetCookie << 32) | value.data;
        if (value.type >= 28 && value.type <= 31) {
            return getColorStateListFromInt(value, key);
        }
        ComplexColor complexColor = loadComplexColorFromName(wrapper, theme, value, id);
        if (complexColor != null && (complexColor instanceof ColorStateList)) {
            return (ColorStateList) complexColor;
        }
        throw new Resources.NotFoundException("Can't find ColorStateList from drawable resource ID #0x" + Integer.toHexString(id));
    }

    private ColorStateList getColorStateListFromInt(TypedValue value, long key) {
        LongSparseArray<ConstantState<ComplexColor>> longSparseArray = sPreloadedComplexColors;
        ConstantState<ComplexColor> factory = longSparseArray.get(key);
        if (factory != null) {
            return (ColorStateList) factory.newInstance();
        }
        ColorStateList csl = ColorStateList.valueOf(value.data);
        if (this.mPreloading && verifyPreloadConfig(value.changingConfigurations, 0, value.resourceId, "color")) {
            longSparseArray.put(key, csl.getConstantState());
        }
        return csl;
    }

    private ComplexColor loadComplexColorForCookie(Resources wrapper, TypedValue value, int id, Resources.Theme theme) {
        int type;
        if (value.string != null) {
            String file = value.string.toString();
            ComplexColor complexColor = null;
            Trace.traceBegin(8192L, file);
            if (file.endsWith(".xml")) {
                try {
                    XmlResourceParser parser = loadXmlResourceParser(file, id, value.assetCookie, "ComplexColor");
                    AttributeSet attrs = Xml.asAttributeSet(parser);
                    while (true) {
                        type = parser.next();
                        if (type == 2 || type == 1) {
                            break;
                        }
                    }
                    if (type == 2) {
                        String name = parser.getName();
                        if (name.equals("gradient")) {
                            complexColor = GradientColor.createFromXmlInner(wrapper, parser, attrs, theme);
                        } else if (name.equals("selector")) {
                            complexColor = ColorStateList.createFromXmlInner(wrapper, parser, attrs, theme);
                        }
                        parser.close();
                        Trace.traceEnd(8192L);
                        return complexColor;
                    }
                    throw new XmlPullParserException("No start tag found");
                } catch (Exception e) {
                    Trace.traceEnd(8192L);
                    Resources.NotFoundException rnf = new Resources.NotFoundException("File " + file + " from ComplexColor resource ID #0x" + Integer.toHexString(id));
                    rnf.initCause(e);
                    throw rnf;
                }
            } else {
                Trace.traceEnd(8192L);
                throw new Resources.NotFoundException("File " + file + " from drawable resource ID #0x" + Integer.toHexString(id) + ": .xml extension required");
            }
        } else {
            throw new UnsupportedOperationException("Can't convert to ComplexColor: type=0x" + value.type);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XmlResourceParser loadXmlResourceParser(String file, int id, int assetCookie, String type) throws Resources.NotFoundException {
        if (id != 0) {
            try {
                synchronized (this.mCachedXmlBlocks) {
                    int[] cachedXmlBlockCookies = this.mCachedXmlBlockCookies;
                    String[] cachedXmlBlockFiles = this.mCachedXmlBlockFiles;
                    XmlBlock[] cachedXmlBlocks = this.mCachedXmlBlocks;
                    int num = cachedXmlBlockFiles.length;
                    for (int i = 0; i < num; i++) {
                        if (cachedXmlBlockCookies[i] == assetCookie && cachedXmlBlockFiles[i] != null && cachedXmlBlockFiles[i].equals(file)) {
                            return cachedXmlBlocks[i].newParser(id);
                        }
                    }
                    XmlBlock block = this.mAssets.openXmlBlockAsset(assetCookie, file);
                    if (block != null) {
                        int pos = (this.mLastCachedXmlBlockIndex + 1) % num;
                        this.mLastCachedXmlBlockIndex = pos;
                        XmlBlock oldBlock = cachedXmlBlocks[pos];
                        if (oldBlock != null) {
                            oldBlock.close();
                        }
                        cachedXmlBlockCookies[pos] = assetCookie;
                        cachedXmlBlockFiles[pos] = file;
                        cachedXmlBlocks[pos] = block;
                        return block.newParser(id);
                    }
                }
            } catch (Exception e) {
                Resources.NotFoundException rnf = new Resources.NotFoundException("File " + file + " from xml type " + type + " resource ID #0x" + Integer.toHexString(id));
                rnf.initCause(e);
                throw rnf;
            }
        }
        throw new Resources.NotFoundException("File " + file + " from xml type " + type + " resource ID #0x" + Integer.toHexString(id));
    }

    public final void startPreloading() {
        synchronized (sSync) {
            if (!sPreloaded) {
                sPreloaded = true;
                this.mPreloading = true;
                this.mConfiguration.densityDpi = DisplayMetrics.DENSITY_DEVICE;
                updateConfiguration(null, null, null);
            } else {
                throw new IllegalStateException("Resources already preloaded");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void finishPreloading() {
        if (this.mPreloading) {
            this.mPreloading = false;
            flushLayoutCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getAttributeSetSourceResId(AttributeSet set) {
        if (set == null || !(set instanceof XmlBlock.Parser)) {
            return 0;
        }
        return ((XmlBlock.Parser) set).getSourceResId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LongSparseArray<Drawable.ConstantState> getPreloadedDrawables() {
        return sPreloadedDrawables[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThemeImpl newThemeImpl() {
        return new ThemeImpl();
    }

    /* loaded from: classes.dex */
    public class ThemeImpl {
        private AssetManager mAssets;
        private final long mTheme;
        private final Resources.ThemeKey mKey = new Resources.ThemeKey();
        private int mThemeResId = 0;

        ThemeImpl() {
            AssetManager assetManager = ResourcesImpl.this.mAssets;
            this.mAssets = assetManager;
            long createTheme = assetManager.createTheme();
            this.mTheme = createTheme;
            ResourcesImpl.sThemeRegistry.registerNativeAllocation(this, createTheme);
        }

        protected void finalize() throws Throwable {
            super.finalize();
            this.mAssets.releaseTheme(this.mTheme);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Resources.ThemeKey getKey() {
            return this.mKey;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getNativeTheme() {
            return this.mTheme;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getAppliedStyleResId() {
            return this.mThemeResId;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void applyStyle(int resId, boolean force) {
            this.mAssets.applyStyleToTheme(this.mTheme, resId, force);
            this.mThemeResId = resId;
            this.mKey.append(resId, force);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setTo(ThemeImpl other) {
            this.mAssets.setThemeTo(this.mTheme, other.mAssets, other.mTheme);
            this.mThemeResId = other.mThemeResId;
            this.mKey.setTo(other.getKey());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public TypedArray obtainStyledAttributes(Resources.Theme wrapper, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes) {
            int len = attrs.length;
            TypedArray array = TypedArray.obtain(wrapper.getResources(), len);
            XmlBlock.Parser parser = (XmlBlock.Parser) set;
            this.mAssets.applyStyle(this.mTheme, defStyleAttr, defStyleRes, parser, attrs, array.mDataAddress, array.mIndicesAddress);
            array.mTheme = wrapper;
            array.mXml = parser;
            return ResourcesImpl.this.mResourcesImplExt.replaceTypedArray(array);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public TypedArray resolveAttributes(Resources.Theme wrapper, int[] values, int[] attrs) {
            int len = attrs.length;
            if (values == null || len != values.length) {
                throw new IllegalArgumentException("Base attribute values must the same length as attrs");
            }
            TypedArray array = TypedArray.obtain(wrapper.getResources(), len);
            this.mAssets.resolveAttrs(this.mTheme, 0, 0, values, attrs, array.mData, array.mIndices);
            array.mTheme = wrapper;
            array.mXml = null;
            return ResourcesImpl.this.mResourcesImplExt.replaceTypedArray(array);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean resolveAttribute(int resid, TypedValue outValue, boolean resolveRefs) {
            return this.mAssets.getThemeValue(this.mTheme, resid, outValue, resolveRefs);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int[] getAllAttributes() {
            return this.mAssets.getStyleAttributes(getAppliedStyleResId());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getChangingConfigurations() {
            int nativeChangingConfig = AssetManager.nativeThemeGetChangingConfigurations(this.mTheme);
            return ActivityInfo.activityInfoConfigNativeToJava(nativeChangingConfig);
        }

        public void dump(int priority, String tag, String prefix) {
            this.mAssets.dumpTheme(this.mTheme, priority, tag, prefix);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String[] getTheme() {
            int n = this.mKey.mCount;
            String[] themes = new String[n * 2];
            int i = 0;
            int j = n - 1;
            while (i < themes.length) {
                int resId = this.mKey.mResId[j];
                boolean forced = this.mKey.mForce[j];
                try {
                    themes[i] = ResourcesImpl.this.getResourceName(resId);
                } catch (Resources.NotFoundException e) {
                    themes[i] = Integer.toHexString(i);
                }
                themes[i + 1] = forced ? "forced" : "not forced";
                i += 2;
                j--;
            }
            return themes;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void rebase() {
            rebase(this.mAssets);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void rebase(AssetManager newAssets) {
            this.mAssets = this.mAssets.rebaseTheme(this.mTheme, newAssets, this.mKey.mResId, this.mKey.mForce, this.mKey.mCount);
        }

        public int[] getAttributeResolutionStack(int defStyleAttr, int defStyleRes, int explicitStyleRes) {
            return this.mAssets.getAttributeResolutionStack(this.mTheme, defStyleAttr, defStyleRes, explicitStyleRes);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LookupStack {
        private int[] mIds;
        private int mSize;

        private LookupStack() {
            this.mIds = new int[4];
            this.mSize = 0;
        }

        public void push(int id) {
            this.mIds = GrowingArrayUtils.append(this.mIds, this.mSize, id);
            this.mSize++;
        }

        public boolean contains(int id) {
            for (int i = 0; i < this.mSize; i++) {
                if (this.mIds[i] == id) {
                    return true;
                }
            }
            return false;
        }

        public void pop() {
            this.mSize--;
        }
    }

    public void setAppContext(Context context) {
        mAppContext = context;
    }
}
