package android.content.pm.parsing.component;

import android.content.pm.PackageManager;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingPackageUtils;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ParsedComponentUtils {
    ParsedComponentUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <Component extends ParsedComponent> ParseResult<Component> parseComponent(Component component, String tag, ParsingPackage pkg, TypedArray array, boolean useRoundIcon, ParseInput input, int bannerAttr, Integer descriptionAttr, int iconAttr, int labelAttr, int logoAttr, int nameAttr, int roundIconAttr) {
        String name = array.getNonConfigurationString(nameAttr, 0);
        if (TextUtils.isEmpty(name)) {
            return input.error(tag + " does not specify android:name");
        }
        String packageName = pkg.getPackageName();
        String className = ParsingUtils.buildClassName(packageName, name);
        if (PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(className)) {
            return input.error(tag + " invalid android:name");
        }
        component.setName(className);
        component.setPackageName(packageName);
        int roundIconVal = useRoundIcon ? array.getResourceId(roundIconAttr, 0) : 0;
        if (roundIconVal != 0) {
            component.icon = roundIconVal;
            component.nonLocalizedLabel = null;
        } else {
            int iconVal = array.getResourceId(iconAttr, 0);
            if (iconVal != 0) {
                component.icon = iconVal;
                component.nonLocalizedLabel = null;
            }
        }
        int logoVal = array.getResourceId(logoAttr, 0);
        if (logoVal != 0) {
            component.logo = logoVal;
        }
        int bannerVal = array.getResourceId(bannerAttr, 0);
        if (bannerVal != 0) {
            component.banner = bannerVal;
        }
        if (descriptionAttr != null) {
            component.descriptionRes = array.getResourceId(descriptionAttr.intValue(), 0);
        }
        TypedValue v = array.peekValue(labelAttr);
        if (v != null) {
            component.labelRes = v.resourceId;
            if (v.resourceId == 0) {
                component.nonLocalizedLabel = v.coerceToString();
            }
        }
        return input.success(component);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParseResult<Bundle> addMetaData(ParsedComponent component, ParsingPackage pkg, Resources resources, XmlResourceParser parser, ParseInput input) {
        ParseResult<PackageManager.Property> result = ParsingPackageUtils.parseMetaData(pkg, component, resources, parser, "<meta-data>", input);
        if (result.isError()) {
            return input.error(result);
        }
        PackageManager.Property property = result.getResult();
        if (property != null) {
            component.metaData = property.toBundle(component.metaData);
        }
        return input.success(component.metaData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParseResult<PackageManager.Property> addProperty(ParsedComponent component, ParsingPackage pkg, Resources resources, XmlResourceParser parser, ParseInput input) {
        ParseResult<PackageManager.Property> result = ParsingPackageUtils.parseMetaData(pkg, component, resources, parser, "<property>", input);
        if (result.isError()) {
            return input.error(result);
        }
        PackageManager.Property property = result.getResult();
        if (property != null) {
            component.addProperty(property);
        }
        return input.success(property);
    }
}
