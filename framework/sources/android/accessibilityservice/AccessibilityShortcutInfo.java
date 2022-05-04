package android.accessibilityservice;

import android.accessibilityservice.util.AccessibilityUtils;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class AccessibilityShortcutInfo {
    public static final String META_DATA = "android.accessibilityshortcut.target";
    private static final String TAG_ACCESSIBILITY_SHORTCUT = "accessibility-shortcut-target";
    private final ActivityInfo mActivityInfo;
    private final int mAnimatedImageRes;
    private final ComponentName mComponentName;
    private final int mDescriptionResId;
    private final int mHtmlDescriptionRes;
    private String mSettingsActivityName;
    private final int mSummaryResId;

    public AccessibilityShortcutInfo(Context context, ActivityInfo activityInfo) throws XmlPullParserException, IOException {
        PackageManager packageManager = context.getPackageManager();
        this.mComponentName = activityInfo.getComponentName();
        this.mActivityInfo = activityInfo;
        try {
            XmlResourceParser parser = activityInfo.loadXmlMetaData(packageManager, META_DATA);
            if (parser != null) {
                for (int type = 0; type != 1 && type != 2; type = parser.next()) {
                }
                String nodeName = parser.getName();
                if (TAG_ACCESSIBILITY_SHORTCUT.equals(nodeName)) {
                    AttributeSet allAttributes = Xml.asAttributeSet(parser);
                    Resources resources = packageManager.getResourcesForApplication(this.mActivityInfo.applicationInfo);
                    TypedArray asAttributes = resources.obtainAttributes(allAttributes, R.styleable.AccessibilityShortcutTarget);
                    int resourceId = asAttributes.getResourceId(0, 0);
                    this.mDescriptionResId = resourceId;
                    int resourceId2 = asAttributes.getResourceId(1, 0);
                    this.mSummaryResId = resourceId2;
                    this.mAnimatedImageRes = asAttributes.getResourceId(3, 0);
                    int resourceId3 = asAttributes.getResourceId(4, 0);
                    this.mHtmlDescriptionRes = resourceId3;
                    this.mSettingsActivityName = asAttributes.getString(2);
                    asAttributes.recycle();
                    if ((resourceId == 0 && resourceId3 == 0) || resourceId2 == 0) {
                        throw new XmlPullParserException("No description or summary in meta-data");
                    } else if (parser != null) {
                        parser.close();
                    }
                } else {
                    throw new XmlPullParserException("Meta-data does not start withaccessibility-shortcut-target tag");
                }
            } else {
                throw new XmlPullParserException("Meta-data accessibility-shortcut-target does not exist");
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new XmlPullParserException("Unable to create context for: " + this.mActivityInfo.packageName);
        }
    }

    public ActivityInfo getActivityInfo() {
        return this.mActivityInfo;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public String loadSummary(PackageManager packageManager) {
        return loadResourceString(packageManager, this.mActivityInfo, this.mSummaryResId);
    }

    public String loadDescription(PackageManager packageManager) {
        return loadResourceString(packageManager, this.mActivityInfo, this.mDescriptionResId);
    }

    public int getAnimatedImageRes() {
        return this.mAnimatedImageRes;
    }

    public Drawable loadAnimatedImage(Context context) {
        if (this.mAnimatedImageRes == 0) {
            return null;
        }
        return AccessibilityUtils.loadSafeAnimatedImage(context, this.mActivityInfo.applicationInfo, this.mAnimatedImageRes);
    }

    public String loadHtmlDescription(PackageManager packageManager) {
        String htmlDescription = loadResourceString(packageManager, this.mActivityInfo, this.mHtmlDescriptionRes);
        if (htmlDescription != null) {
            return AccessibilityUtils.getFilteredHtmlText(htmlDescription);
        }
        return null;
    }

    public String getSettingsActivityName() {
        return this.mSettingsActivityName;
    }

    private String loadResourceString(PackageManager packageManager, ActivityInfo activityInfo, int resId) {
        CharSequence text;
        if (resId == 0 || (text = packageManager.getText(activityInfo.packageName, resId, activityInfo.applicationInfo)) == null) {
            return null;
        }
        return text.toString().trim();
    }

    public int hashCode() {
        ComponentName componentName = this.mComponentName;
        return (componentName == null ? 0 : componentName.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityShortcutInfo other = (AccessibilityShortcutInfo) obj;
        ComponentName componentName = this.mComponentName;
        if (componentName == null) {
            if (other.mComponentName != null) {
                return false;
            }
        } else if (!componentName.equals(other.mComponentName)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "AccessibilityShortcutInfo[activityInfo: " + this.mActivityInfo + "]";
    }
}
