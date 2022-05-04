package android.content.pm.parsing.component;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingPackageUtils;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.media.TtmlUtils;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ParsedActivityUtils {
    public static final boolean LOG_UNSAFE_BROADCASTS = false;
    private static final int RECREATE_ON_CONFIG_CHANGES_MASK = 3;
    public static final Set<String> SAFE_BROADCASTS;
    private static final String TAG = "PackageParsing";

    static {
        ArraySet arraySet = new ArraySet();
        SAFE_BROADCASTS = arraySet;
        arraySet.add(Intent.ACTION_BOOT_COMPLETED);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02b0 A[Catch: all -> 0x02b8, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x02b8, blocks: (B:36:0x0247, B:38:0x0258, B:40:0x025f, B:41:0x0266, B:43:0x026e, B:45:0x0275, B:53:0x02b0, B:60:0x02cc), top: B:86:0x0247 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02ba A[Catch: all -> 0x0306, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0306, blocks: (B:32:0x00e7, B:50:0x0288, B:51:0x0299, B:57:0x02ba), top: B:82:0x00e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x016f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.component.ParsedActivity> parseActivityOrReceiver(java.lang.String[] r32, android.content.pm.parsing.ParsingPackage r33, android.content.res.Resources r34, android.content.res.XmlResourceParser r35, int r36, boolean r37, android.content.pm.parsing.result.ParseInput r38) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 807
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedActivityUtils.parseActivityOrReceiver(java.lang.String[], android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int, boolean, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }

    public static ParseResult<ParsedActivity> parseActivityAlias(ParsingPackage pkg, Resources res, XmlResourceParser parser, boolean useRoundIcon, ParseInput input) throws XmlPullParserException, IOException {
        TypedArray sa;
        Throwable th;
        ParsedActivity target;
        TypedArray sa2 = res.obtainAttributes(parser, R.styleable.AndroidManifestActivityAlias);
        try {
            String targetActivity = sa2.getNonConfigurationString(7, 1024);
            try {
                if (targetActivity == null) {
                    ParseResult<ParsedActivity> error = input.error("<activity-alias> does not specify android:targetActivity");
                    sa2.recycle();
                    return error;
                }
                String packageName = pkg.getPackageName();
                String targetActivity2 = ParsingUtils.buildClassName(packageName, targetActivity);
                if (targetActivity2 == null) {
                    ParseResult<ParsedActivity> error2 = input.error("Empty class name in package " + packageName);
                    sa2.recycle();
                    return error2;
                }
                List<ParsedActivity> activities = pkg.getActivities();
                int activitiesSize = ArrayUtils.size(activities);
                int i = 0;
                while (true) {
                    if (i >= activitiesSize) {
                        target = null;
                        break;
                    }
                    ParsedActivity t = activities.get(i);
                    if (targetActivity2.equals(t.getName())) {
                        target = t;
                        break;
                    }
                    i++;
                }
                if (target == null) {
                    ParseResult<ParsedActivity> error3 = input.error("<activity-alias> target activity " + targetActivity2 + " not found in manifest with activities = " + pkg.getActivities() + ", parsedActivities = " + activities);
                    sa2.recycle();
                    return error3;
                }
                ParsedActivity activity = ParsedActivity.makeAlias(targetActivity2, target);
                String tag = "<" + parser.getName() + ">";
                sa = sa2;
                try {
                    ParseResult<ParsedActivity> result = ParsedMainComponentUtils.parseMainComponent(activity, tag, null, pkg, sa2, 0, useRoundIcon, input, 10, 6, null, 4, 1, 0, 8, 2, null, 11, null, 12);
                    if (result.isError()) {
                        sa.recycle();
                        return result;
                    }
                    boolean visibleToEphemeral = (activity.getFlags() & 1048576) != 0;
                    ParseResult<ParsedActivity> parseActivityOrAlias = parseActivityOrAlias(activity, pkg, tag, parser, res, sa, false, true, visibleToEphemeral, input, 9, 3, 5);
                    sa.recycle();
                    return parseActivityOrAlias;
                } catch (Throwable th2) {
                    th = th2;
                    sa.recycle();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                sa = sa2;
            }
        } catch (Throwable th4) {
            th = th4;
            sa = sa2;
        }
    }

    private static ParseResult<ParsedActivity> parseActivityOrAlias(ParsedActivity activity, ParsingPackage pkg, String tag, XmlResourceParser parser, Resources resources, TypedArray array, boolean isReceiver, boolean isAlias, boolean visibleToEphemeral, ParseInput input, int parentActivityNameAttr, int permissionAttr, int exportedAttr) throws IOException, XmlPullParserException {
        boolean hasIntentFilters;
        String launchMode;
        String permission;
        int depth;
        ParseResult result;
        ParsedIntentInfo intent;
        ParsedIntentInfo intent2;
        String parentActivityName = array.getNonConfigurationString(parentActivityNameAttr, 1024);
        if (parentActivityName != null) {
            String packageName = pkg.getPackageName();
            String parentClassName = ParsingUtils.buildClassName(packageName, parentActivityName);
            if (parentClassName == null) {
                Log.e("PackageParsing", "Activity " + activity.getName() + " specified invalid parentActivityName " + parentActivityName);
            } else {
                activity.setParentActivity(parentClassName);
            }
        }
        boolean z = false;
        String permission2 = array.getNonConfigurationString(permissionAttr, 0);
        if (isAlias) {
            activity.setPermission(permission2);
        } else {
            activity.setPermission(permission2 != null ? permission2 : pkg.getPermission());
        }
        boolean setExported = array.hasValue(exportedAttr);
        if (setExported) {
            activity.exported = array.getBoolean(exportedAttr, false);
        }
        int depth2 = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type != 1) {
                if (type == 3 && parser.getDepth() <= depth2) {
                    hasIntentFilters = z;
                    break;
                } else if (type == 2) {
                    if (parser.getName().equals("intent-filter")) {
                        depth = depth2;
                        permission = permission2;
                        z = z;
                        ParseResult intentResult = parseIntentFilter(pkg, activity, !isReceiver, visibleToEphemeral, resources, parser, input);
                        if (intentResult.isSuccess() && (intent2 = intentResult.getResult()) != null) {
                            activity.order = Math.max(intent2.getOrder(), activity.order);
                            activity.addIntent(intent2);
                        }
                        result = intentResult;
                    } else {
                        depth = depth2;
                        permission = permission2;
                        z = z;
                        if (parser.getName().equals("meta-data")) {
                            result = ParsedComponentUtils.addMetaData(activity, pkg, resources, parser, input);
                        } else if (parser.getName().equals("property")) {
                            result = ParsedComponentUtils.addProperty(activity, pkg, resources, parser, input);
                        } else if (!isReceiver && !isAlias && parser.getName().equals("preferred")) {
                            ParseResult intentResult2 = parseIntentFilter(pkg, activity, true, visibleToEphemeral, resources, parser, input);
                            if (intentResult2.isSuccess() && (intent = intentResult2.getResult()) != null) {
                                pkg.addPreferredActivityFilter(activity.getClassName(), intent);
                            }
                            result = intentResult2;
                        } else if (isReceiver || isAlias || !parser.getName().equals(TtmlUtils.TAG_LAYOUT)) {
                            result = ParsingUtils.unknownTag(tag, pkg, parser, input);
                        } else {
                            ParseResult layoutResult = parseActivityWindowLayout(resources, parser, input);
                            if (layoutResult.isSuccess()) {
                                activity.windowLayout = layoutResult.getResult();
                            }
                            result = layoutResult;
                        }
                    }
                    if (result.isError()) {
                        return input.error(result);
                    }
                    depth2 = depth;
                    permission2 = permission;
                }
            } else {
                hasIntentFilters = z;
                break;
            }
        }
        if (!isAlias && activity.launchMode != 4 && activity.metaData != null && activity.metaData.containsKey(ParsingPackageUtils.METADATA_ACTIVITY_LAUNCH_MODE) && (launchMode = activity.metaData.getString(ParsingPackageUtils.METADATA_ACTIVITY_LAUNCH_MODE)) != null && launchMode.equals("singleInstancePerTask")) {
            activity.launchMode = 4;
        }
        ParseResult<ActivityInfo.WindowLayout> layoutResult2 = resolveActivityWindowLayout(activity, input);
        if (layoutResult2.isError()) {
            return input.error(layoutResult2);
        }
        activity.windowLayout = layoutResult2.getResult();
        if (!setExported) {
            if (activity.getIntents().size() > 0) {
                hasIntentFilters = true;
            }
            if (hasIntentFilters) {
                ParseResult exportedCheckResult = input.deferError(activity.getName() + ": Targeting S+ (version 31 and above) requires that an explicit value for android:exported be defined when intent filters are present", ParseInput.DeferredError.MISSING_EXPORTED_FLAG);
                if (exportedCheckResult.isError()) {
                    return input.error(exportedCheckResult);
                }
            }
            activity.exported = hasIntentFilters;
        }
        return input.success(activity);
    }

    private static ParseResult<ParsedIntentInfo> parseIntentFilter(ParsingPackage pkg, ParsedActivity activity, boolean allowImplicitEphemeralVisibility, boolean visibleToEphemeral, Resources resources, XmlResourceParser parser, ParseInput input) throws IOException, XmlPullParserException {
        ParseResult<ParsedIntentInfo> result = ParsedMainComponentUtils.parseIntentFilter(activity, pkg, resources, parser, visibleToEphemeral, true, true, allowImplicitEphemeralVisibility, true, input);
        if (result.isError()) {
            return input.error(result);
        }
        ParsedIntentInfo intent = result.getResult();
        if (intent != null) {
            if (intent.isVisibleToInstantApp()) {
                activity.flags |= 1048576;
            }
            if (intent.isImplicitlyVisibleToInstantApp()) {
                activity.flags |= 2097152;
            }
        }
        return input.success(intent);
    }

    private static int getActivityResizeMode(ParsingPackage pkg, TypedArray sa, int screenOrientation) {
        Boolean resizeableActivity = pkg.getResizeableActivity();
        boolean z = true;
        if (sa.hasValue(40) || resizeableActivity != null) {
            if (resizeableActivity == null || !resizeableActivity.booleanValue()) {
                z = false;
            }
            if (sa.getBoolean(40, z)) {
                return 2;
            }
            return 0;
        } else if (pkg.isResizeableActivityViaSdkVersion()) {
            return 1;
        } else {
            if (ActivityInfo.isFixedOrientationPortrait(screenOrientation)) {
                return 6;
            }
            if (ActivityInfo.isFixedOrientationLandscape(screenOrientation)) {
                return 5;
            }
            if (screenOrientation == 14) {
                return 7;
            }
            return 4;
        }
    }

    private static ParseResult<ActivityInfo.WindowLayout> parseActivityWindowLayout(Resources res, AttributeSet attrs, ParseInput input) {
        Throwable th;
        TypedArray sw = res.obtainAttributes(attrs, R.styleable.AndroidManifestLayout);
        int width = -1;
        float widthFraction = -1.0f;
        int height = -1;
        float heightFraction = -1.0f;
        try {
            int widthType = sw.getType(3);
            if (widthType == 6) {
                widthFraction = sw.getFraction(3, 1, 1, -1.0f);
            } else if (widthType == 5) {
                width = sw.getDimensionPixelSize(3, -1);
            }
            int heightType = sw.getType(4);
            if (heightType == 6) {
                heightFraction = sw.getFraction(4, 1, 1, -1.0f);
            } else if (heightType == 5) {
                height = sw.getDimensionPixelSize(4, -1);
            }
            int gravity = sw.getInt(0, 17);
            int minWidth = sw.getDimensionPixelSize(1, -1);
            int minHeight = sw.getDimensionPixelSize(2, -1);
            String windowLayoutAffinity = sw.getNonConfigurationString(5, 0);
            ActivityInfo.WindowLayout windowLayout = new ActivityInfo.WindowLayout(width, widthFraction, height, heightFraction, gravity, minWidth, minHeight, windowLayoutAffinity);
            try {
                ParseResult<ActivityInfo.WindowLayout> success = input.success(windowLayout);
                sw.recycle();
                return success;
            } catch (Throwable th2) {
                th = th2;
                sw.recycle();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static ParseResult<ActivityInfo.WindowLayout> resolveActivityWindowLayout(ParsedActivity activity, ParseInput input) {
        if (activity.metaData == null || !activity.metaData.containsKey("android.activity_window_layout_affinity")) {
            return input.success(activity.windowLayout);
        }
        if (activity.windowLayout != null && activity.windowLayout.windowLayoutAffinity != null) {
            return input.success(activity.windowLayout);
        }
        String windowLayoutAffinity = activity.metaData.getString("android.activity_window_layout_affinity");
        ActivityInfo.WindowLayout layout = activity.windowLayout;
        if (layout == null) {
            layout = new ActivityInfo.WindowLayout(-1, -1.0f, -1, -1.0f, 0, -1, -1, windowLayoutAffinity);
        } else {
            layout.windowLayoutAffinity = windowLayoutAffinity;
        }
        return input.success(layout);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getActivityConfigChanges(int configChanges, int recreateOnConfigChanges) {
        return ((~recreateOnConfigChanges) & 3) | configChanges;
    }
}
