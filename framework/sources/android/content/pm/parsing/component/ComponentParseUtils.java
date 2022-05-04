package android.content.pm.parsing.component;

import android.content.Intent;
import android.content.pm.PackageUserState;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingPackageUtils;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ComponentParseUtils {
    public static boolean isImplicitlyExposedIntent(ParsedIntentInfo intentInfo) {
        return intentInfo.hasCategory(Intent.CATEGORY_BROWSABLE) || intentInfo.hasAction(Intent.ACTION_SEND) || intentInfo.hasAction(Intent.ACTION_SENDTO) || intentInfo.hasAction(Intent.ACTION_SEND_MULTIPLE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <Component extends ParsedComponent> ParseResult<Component> parseAllMetaData(ParsingPackage pkg, Resources res, XmlResourceParser parser, String tag, Component component, ParseInput input) throws XmlPullParserException, IOException {
        ParseResult result;
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1 || (type == 3 && parser.getDepth() <= depth)) {
                break;
            } else if (type == 2) {
                if ("meta-data".equals(parser.getName())) {
                    result = ParsedComponentUtils.addMetaData(component, pkg, res, parser, input);
                } else {
                    result = ParsingUtils.unknownTag(tag, pkg, parser, input);
                }
                if (result.isError()) {
                    return input.error(result);
                }
            }
        }
        return input.success(component);
    }

    public static ParseResult<String> buildProcessName(String pkg, String defProc, CharSequence procSeq, int flags, String[] separateProcesses, ParseInput input) {
        if ((flags & 2) == 0 || "system".contentEquals(procSeq)) {
            if (separateProcesses != null) {
                for (int i = separateProcesses.length - 1; i >= 0; i--) {
                    String sp = separateProcesses[i];
                    if (sp.equals(pkg) || sp.equals(defProc) || sp.contentEquals(procSeq)) {
                        return input.success(pkg);
                    }
                }
            }
            if (procSeq == null || procSeq.length() <= 0) {
                ParseResult<String> nameResult = input.success(defProc);
                return nameResult;
            }
            ParseResult<String> nameResult2 = buildCompoundName(pkg, procSeq, "process", input);
            return input.success(TextUtils.safeIntern(nameResult2.getResult()));
        }
        return input.success(defProc != null ? defProc : pkg);
    }

    public static ParseResult<String> buildTaskAffinityName(String pkg, String defProc, CharSequence procSeq, ParseInput input) {
        if (procSeq == null) {
            return input.success(defProc);
        }
        if (procSeq.length() <= 0) {
            return input.success(null);
        }
        return buildCompoundName(pkg, procSeq, "taskAffinity", input);
    }

    public static ParseResult<String> buildCompoundName(String pkg, CharSequence procSeq, String type, ParseInput input) {
        String proc = procSeq.toString();
        char c = proc.charAt(0);
        if (pkg == null || c != ':') {
            if (!"system".equals(proc)) {
                ParseResult<?> nameResult = ParsingPackageUtils.validateName(input, proc, true, false);
                if (nameResult.isError()) {
                    return input.error("Invalid " + type + " name " + proc + " in package " + pkg + ": " + nameResult.getErrorMessage());
                }
            }
            return input.success(proc);
        } else if (proc.length() < 2) {
            return input.error("Bad " + type + " name " + proc + " in package " + pkg + ": must be at least two characters");
        } else {
            String subName = proc.substring(1);
            ParseResult<?> nameResult2 = ParsingPackageUtils.validateName(input, subName, false, false);
            if (nameResult2.isError()) {
                return input.error("Invalid " + type + " name " + proc + " in package " + pkg + ": " + nameResult2.getErrorMessage());
            }
            return input.success(pkg + proc);
        }
    }

    public static int flag(int flag, int attribute, TypedArray typedArray) {
        if (typedArray.getBoolean(attribute, false)) {
            return flag;
        }
        return 0;
    }

    public static int flag(int flag, int attribute, boolean defaultValue, TypedArray typedArray) {
        if (typedArray.getBoolean(attribute, defaultValue)) {
            return flag;
        }
        return 0;
    }

    public static CharSequence getNonLocalizedLabel(ParsedComponent component) {
        return component.nonLocalizedLabel;
    }

    public static int getIcon(ParsedComponent component) {
        return component.icon;
    }

    public static boolean isMatch(PackageUserState state, boolean isSystem, boolean isPackageEnabled, ParsedMainComponent component, int flags) {
        return state.isMatch(isSystem, isPackageEnabled, component.isEnabled(), component.isDirectBootAware(), component.getName(), flags);
    }

    public static boolean isEnabled(PackageUserState state, boolean isPackageEnabled, ParsedMainComponent parsedComponent, int flags) {
        return state.isEnabled(isPackageEnabled, parsedComponent.isEnabled(), parsedComponent.getName(), flags);
    }
}
