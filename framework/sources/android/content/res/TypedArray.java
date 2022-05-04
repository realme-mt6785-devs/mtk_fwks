package android.content.res;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.XmlBlock;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.android.internal.util.XmlUtils;
import dalvik.system.VMRuntime;
import java.util.Arrays;
/* loaded from: classes.dex */
public class TypedArray implements AutoCloseable {
    static final int STYLE_ASSET_COOKIE = 2;
    static final int STYLE_CHANGING_CONFIGURATIONS = 4;
    static final int STYLE_DATA = 1;
    static final int STYLE_DENSITY = 5;
    static final int STYLE_NUM_ENTRIES = 7;
    static final int STYLE_RESOURCE_ID = 3;
    static final int STYLE_SOURCE_RESOURCE_ID = 6;
    static final int STYLE_TYPE = 0;
    private AssetManager mAssets;
    int[] mData;
    long mDataAddress;
    int[] mIndices;
    long mIndicesAddress;
    int mLength;
    private DisplayMetrics mMetrics;
    private boolean mRecycled;
    private final Resources mResources;
    Resources.Theme mTheme;
    TypedValue mValue = new TypedValue();
    XmlBlock.Parser mXml;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TypedArray obtain(Resources res, int len) {
        TypedArray attrs = res.mTypedArrayPool.acquire();
        if (attrs == null) {
            attrs = new TypedArray(res);
        }
        attrs.mRecycled = false;
        attrs.mAssets = res.getAssets();
        attrs.mMetrics = res.getDisplayMetrics();
        attrs.resize(len);
        return attrs;
    }

    private void resize(int len) {
        this.mLength = len;
        int dataLen = len * 7;
        int indicesLen = len + 1;
        VMRuntime runtime = VMRuntime.getRuntime();
        if (this.mDataAddress == 0 || this.mData.length < dataLen) {
            int[] iArr = (int[]) runtime.newNonMovableArray(Integer.TYPE, dataLen);
            this.mData = iArr;
            this.mDataAddress = runtime.addressOf(iArr);
            int[] iArr2 = (int[]) runtime.newNonMovableArray(Integer.TYPE, indicesLen);
            this.mIndices = iArr2;
            this.mIndicesAddress = runtime.addressOf(iArr2);
        }
    }

    public int length() {
        if (!this.mRecycled) {
            return this.mLength;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getIndexCount() {
        if (!this.mRecycled) {
            return this.mIndices[0];
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getIndex(int at) {
        if (!this.mRecycled) {
            return this.mIndices[at + 1];
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public Resources getResources() {
        if (!this.mRecycled) {
            return this.mResources;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public CharSequence getText(int index) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return null;
            }
            if (type == 3) {
                return loadStringValueAt(index2);
            }
            TypedValue v = this.mValue;
            if (getValueAt(index2, v)) {
                return v.coerceToString();
            }
            throw new RuntimeException("getText of bad type: 0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public String getString(int index) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return null;
            }
            if (type == 3) {
                return loadStringValueAt(index2).toString();
            }
            TypedValue v = this.mValue;
            if (getValueAt(index2, v)) {
                CharSequence cs = v.coerceToString();
                if (cs != null) {
                    return cs.toString();
                }
                return null;
            }
            throw new RuntimeException("getString of bad type: 0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public String getNonResourceString(int index) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type != 3) {
                return null;
            }
            int cookie = data[index2 + 2];
            if (cookie < 0) {
                return this.mXml.getPooledString(data[index2 + 1]).toString();
            }
            return null;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public String getNonConfigurationString(int index, int allowedChangingConfigs) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            int changingConfigs = ActivityInfo.activityInfoConfigNativeToJava(data[index2 + 4]);
            if (((~allowedChangingConfigs) & changingConfigs) != 0 || type == 0) {
                return null;
            }
            if (type == 3) {
                return loadStringValueAt(index2).toString();
            }
            TypedValue v = this.mValue;
            if (getValueAt(index2, v)) {
                CharSequence cs = v.coerceToString();
                if (cs != null) {
                    return cs.toString();
                }
                return null;
            }
            throw new RuntimeException("getNonConfigurationString of bad type: 0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public boolean getBoolean(int index, boolean defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type >= 16 && type <= 31) {
                return data[index2 + 1] != 0;
            }
            TypedValue v = this.mValue;
            if (getValueAt(index2, v)) {
                StrictMode.noteResourceMismatch(v);
                return XmlUtils.convertValueToBoolean(v.coerceToString(), defValue);
            }
            throw new RuntimeException("getBoolean of bad type: 0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getInt(int index, int defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type >= 16 && type <= 31) {
                return data[index2 + 1];
            }
            TypedValue v = this.mValue;
            if (getValueAt(index2, v)) {
                StrictMode.noteResourceMismatch(v);
                return XmlUtils.convertValueToInt(v.coerceToString(), defValue);
            }
            throw new RuntimeException("getInt of bad type: 0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public float getFloat(int index, float defValue) {
        CharSequence str;
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type == 4) {
                return Float.intBitsToFloat(data[index2 + 1]);
            }
            if (type >= 16 && type <= 31) {
                return data[index2 + 1];
            }
            TypedValue v = this.mValue;
            if (!getValueAt(index2, v) || (str = v.coerceToString()) == null) {
                throw new RuntimeException("getFloat of bad type: 0x" + Integer.toHexString(type));
            }
            StrictMode.noteResourceMismatch(v);
            return Float.parseFloat(str.toString());
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getColor(int index, int defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type >= 16 && type <= 31) {
                return data[index2 + 1];
            }
            if (type == 3) {
                TypedValue value = this.mValue;
                if (!getValueAt(index2, value)) {
                    return defValue;
                }
                ColorStateList csl = this.mResources.loadColorStateList(value, value.resourceId, this.mTheme);
                return csl.getDefaultColor();
            } else if (type == 2) {
                TypedValue value2 = this.mValue;
                getValueAt(index2, value2);
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value2);
            } else {
                throw new UnsupportedOperationException("Can't convert value at index " + index + " to color: type=0x" + Integer.toHexString(type));
            }
        } else {
            throw new RuntimeException("Cannot make calls to a recycled instance!");
        }
    }

    public ComplexColor getComplexColor(int index) {
        if (!this.mRecycled) {
            TypedValue value = this.mValue;
            if (!getValueAt(index * 7, value)) {
                return null;
            }
            if (value.type != 2) {
                return this.mResources.loadComplexColor(value, value.resourceId, this.mTheme);
            }
            throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public ColorStateList getColorStateList(int index) {
        if (!this.mRecycled) {
            TypedValue value = this.mValue;
            if (!getValueAt(index * 7, value)) {
                return null;
            }
            if (value.type != 2) {
                return this.mResources.loadColorStateList(value, value.resourceId, this.mTheme);
            }
            throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getInteger(int index, int defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type >= 16 && type <= 31) {
                return data[index2 + 1];
            }
            if (type == 2) {
                TypedValue value = this.mValue;
                getValueAt(index2, value);
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
            }
            throw new UnsupportedOperationException("Can't convert value at index " + index + " to integer: type=0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public float getDimension(int index, float defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type == 5) {
                return TypedValue.complexToDimension(data[index2 + 1], this.mMetrics);
            }
            if (type == 2) {
                TypedValue value = this.mValue;
                getValueAt(index2, value);
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
            }
            throw new UnsupportedOperationException("Can't convert value at index " + index + " to dimension: type=0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getDimensionPixelOffset(int index, int defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type == 5) {
                return TypedValue.complexToDimensionPixelOffset(data[index2 + 1], this.mMetrics);
            }
            if (type == 2) {
                TypedValue value = this.mValue;
                getValueAt(index2, value);
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
            }
            throw new UnsupportedOperationException("Can't convert value at index " + index + " to dimension: type=0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getDimensionPixelSize(int index, int defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type == 5) {
                return TypedValue.complexToDimensionPixelSize(data[index2 + 1], this.mMetrics);
            }
            if (type == 2) {
                TypedValue value = this.mValue;
                getValueAt(index2, value);
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
            }
            throw new UnsupportedOperationException("Can't convert value at index " + index + " to dimension: type=0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getLayoutDimension(int index, String name) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type >= 16 && type <= 31) {
                return data[index2 + 1];
            }
            if (type == 5) {
                return TypedValue.complexToDimensionPixelSize(data[index2 + 1], this.mMetrics);
            }
            if (type == 2) {
                TypedValue value = this.mValue;
                getValueAt(index2, value);
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
            }
            throw new UnsupportedOperationException(getPositionDescription() + ": You must supply a " + name + " attribute.");
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getLayoutDimension(int index, int defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type >= 16 && type <= 31) {
                return data[index2 + 1];
            }
            if (type == 5) {
                return TypedValue.complexToDimensionPixelSize(data[index2 + 1], this.mMetrics);
            }
            return defValue;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public float getFraction(int index, int base, int pbase, float defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            if (type == 0) {
                return defValue;
            }
            if (type == 6) {
                return TypedValue.complexToFraction(data[index2 + 1], base, pbase);
            }
            if (type == 2) {
                TypedValue value = this.mValue;
                getValueAt(index2, value);
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
            }
            throw new UnsupportedOperationException("Can't convert value at index " + index + " to fraction: type=0x" + Integer.toHexString(type));
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getResourceId(int index, int defValue) {
        int resid;
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            if (data[index2 + 0] == 0 || (resid = data[index2 + 3]) == 0) {
                return defValue;
            }
            return resid;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getThemeAttributeId(int index, int defValue) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            if (data[index2 + 0] == 2) {
                return data[index2 + 1];
            }
            return defValue;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public Drawable getDrawable(int index) {
        return getDrawableForDensity(index, 0);
    }

    public Drawable getDrawableForDensity(int index, int density) {
        if (!this.mRecycled) {
            TypedValue value = this.mValue;
            if (!getValueAt(index * 7, value)) {
                return null;
            }
            if (value.type != 2) {
                if (density > 0) {
                    this.mResources.getValueForDensity(value.resourceId, density, value, true);
                }
                return this.mResources.loadDrawable(value, value.resourceId, density, this.mTheme);
            }
            throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public Typeface getFont(int index) {
        if (!this.mRecycled) {
            TypedValue value = this.mValue;
            if (!getValueAt(index * 7, value)) {
                return null;
            }
            if (value.type != 2) {
                return this.mResources.getFont(value, value.resourceId);
            }
            throw new UnsupportedOperationException("Failed to resolve attribute at index " + index + ": " + value);
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public CharSequence[] getTextArray(int index) {
        if (!this.mRecycled) {
            TypedValue value = this.mValue;
            if (getValueAt(index * 7, value)) {
                return this.mResources.getTextArray(value.resourceId);
            }
            return null;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public boolean getValue(int index, TypedValue outValue) {
        if (!this.mRecycled) {
            return getValueAt(index * 7, outValue);
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getType(int index) {
        if (!this.mRecycled) {
            return this.mData[(index * 7) + 0];
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getSourceResourceId(int index, int defaultValue) {
        if (!this.mRecycled) {
            int resid = this.mData[(index * 7) + 6];
            if (resid != 0) {
                return resid;
            }
            return defaultValue;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public boolean hasValue(int index) {
        if (!this.mRecycled) {
            int[] data = this.mData;
            int type = data[(index * 7) + 0];
            return type != 0;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public boolean hasValueOrEmpty(int index) {
        if (!this.mRecycled) {
            int index2 = index * 7;
            int[] data = this.mData;
            int type = data[index2 + 0];
            return type != 0 || data[index2 + 1] == 1;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public TypedValue peekValue(int index) {
        if (!this.mRecycled) {
            TypedValue value = this.mValue;
            if (getValueAt(index * 7, value)) {
                return value;
            }
            return null;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public String getPositionDescription() {
        if (!this.mRecycled) {
            XmlBlock.Parser parser = this.mXml;
            return parser != null ? parser.getPositionDescription() : "<internal>";
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public void recycle() {
        if (!this.mRecycled) {
            this.mRecycled = true;
            this.mXml = null;
            this.mTheme = null;
            this.mAssets = null;
            this.mResources.mTypedArrayPool.release(this);
            return;
        }
        throw new RuntimeException(toString() + " recycled twice!");
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        recycle();
    }

    public int[] extractThemeAttrs() {
        return extractThemeAttrs(null);
    }

    public int[] extractThemeAttrs(int[] scrap) {
        if (!this.mRecycled) {
            int[] attrs = null;
            int[] data = this.mData;
            int N = length();
            for (int i = 0; i < N; i++) {
                int index = i * 7;
                if (data[index + 0] == 2) {
                    data[index + 0] = 0;
                    int attr = data[index + 1];
                    if (attr != 0) {
                        if (attrs == null) {
                            if (scrap == null || scrap.length != N) {
                                attrs = new int[N];
                            } else {
                                attrs = scrap;
                                Arrays.fill(attrs, 0);
                            }
                        }
                        attrs[i] = attr;
                    }
                }
            }
            return attrs;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    public int getChangingConfigurations() {
        if (!this.mRecycled) {
            int changingConfig = 0;
            int[] data = this.mData;
            int N = length();
            for (int i = 0; i < N; i++) {
                int index = i * 7;
                int type = data[index + 0];
                if (type != 0) {
                    changingConfig |= ActivityInfo.activityInfoConfigNativeToJava(data[index + 4]);
                }
            }
            return changingConfig;
        }
        throw new RuntimeException("Cannot make calls to a recycled instance!");
    }

    private boolean getValueAt(int index, TypedValue outValue) {
        int[] data = this.mData;
        int type = data[index + 0];
        if (type == 0) {
            return false;
        }
        outValue.type = type;
        outValue.data = data[index + 1];
        outValue.assetCookie = data[index + 2];
        outValue.resourceId = data[index + 3];
        outValue.changingConfigurations = ActivityInfo.activityInfoConfigNativeToJava(data[index + 4]);
        outValue.density = data[index + 5];
        outValue.string = type == 3 ? loadStringValueAt(index) : null;
        outValue.sourceResourceId = data[index + 6];
        return true;
    }

    private CharSequence loadStringValueAt(int index) {
        int[] data = this.mData;
        int cookie = data[index + 2];
        if (cookie >= 0) {
            return this.mAssets.getPooledStringForCookie(cookie, data[index + 1]);
        }
        XmlBlock.Parser parser = this.mXml;
        if (parser != null) {
            return parser.getPooledString(data[index + 1]);
        }
        return null;
    }

    protected TypedArray(Resources resources) {
        this.mResources = resources;
        this.mMetrics = resources.getDisplayMetrics();
        this.mAssets = resources.getAssets();
    }

    public String toString() {
        return Arrays.toString(this.mData);
    }
}
