package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ParsedMainComponentUtils {
    private static final String TAG = "PackageParsing";

    ParsedMainComponentUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <Component extends ParsedMainComponent> ParseResult<Component> parseMainComponent(Component component, String tag, String[] separateProcesses, ParsingPackage pkg, TypedArray array, int flags, boolean useRoundIcon, ParseInput input, int bannerAttr, int descriptionAttr, Integer directBootAwareAttr, Integer enabledAttr, int iconAttr, int labelAttr, int logoAttr, int nameAttr, Integer processAttr, int roundIconAttr, Integer splitNameAttr, Integer attributionTagsAttr) {
        String attributionTags;
        CharSequence processName;
        ParseResult<Component> result = ParsedComponentUtils.parseComponent(component, tag, pkg, array, useRoundIcon, input, bannerAttr, Integer.valueOf(descriptionAttr), iconAttr, labelAttr, logoAttr, nameAttr, roundIconAttr);
        if (result.isError()) {
            return result;
        }
        if (directBootAwareAttr != null) {
            component.directBootAware = array.getBoolean(directBootAwareAttr.intValue(), false);
            if (component.isDirectBootAware()) {
                pkg.setPartiallyDirectBootAware(true);
            }
        }
        if (enabledAttr != null) {
            component.enabled = array.getBoolean(enabledAttr.intValue(), true);
        }
        if (processAttr != null) {
            if (pkg.getTargetSdkVersion() >= 8) {
                processName = array.getNonConfigurationString(processAttr.intValue(), 1024);
            } else {
                processName = array.getNonResourceString(processAttr.intValue());
            }
            ParseResult<String> processNameResult = ComponentParseUtils.buildProcessName(pkg.getPackageName(), pkg.getProcessName(), processName, flags, separateProcesses, input);
            if (processNameResult.isError()) {
                return input.error(processNameResult);
            }
            component.setProcessName(processNameResult.getResult());
        }
        if (splitNameAttr != null) {
            component.splitName = array.getNonConfigurationString(splitNameAttr.intValue(), 0);
        }
        if (!(attributionTagsAttr == null || (attributionTags = array.getNonConfigurationString(attributionTagsAttr.intValue(), 0)) == null)) {
            component.attributionTags = attributionTags.split("\\|");
        }
        return input.success(component);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParseResult<ParsedIntentInfo> parseIntentFilter(ParsedMainComponent mainComponent, ParsingPackage pkg, Resources resources, XmlResourceParser parser, boolean visibleToEphemeral, boolean allowGlobs, boolean allowAutoVerify, boolean allowImplicitEphemeralVisibility, boolean failOnNoActions, ParseInput input) throws IOException, XmlPullParserException {
        int intentVisibility;
        ParseResult<ParsedIntentInfo> intentResult = ParsedIntentInfoUtils.parseIntentInfo(mainComponent.getName(), pkg, resources, parser, allowGlobs, allowAutoVerify, input);
        if (intentResult.isError()) {
            return input.error(intentResult);
        }
        ParsedIntentInfo intent = intentResult.getResult();
        int actionCount = intent.countActions();
        if (actionCount != 0 || !failOnNoActions) {
            if (visibleToEphemeral) {
                intentVisibility = 1;
            } else if (!allowImplicitEphemeralVisibility || !ComponentParseUtils.isImplicitlyExposedIntent(intent)) {
                intentVisibility = 0;
            } else {
                intentVisibility = 2;
            }
            intent.setVisibilityToInstantApp(intentVisibility);
            return input.success(intentResult.getResult());
        }
        Slog.w("PackageParsing", "No actions in " + parser.getName() + " at " + pkg.getBaseApkPath() + " " + parser.getPositionDescription());
        return input.success(null);
    }
}
