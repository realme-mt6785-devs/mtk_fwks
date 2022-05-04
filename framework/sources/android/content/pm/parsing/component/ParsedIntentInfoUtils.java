package android.content.pm.parsing.component;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingPackageUtils;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.nfc.cardemulation.CardEmulation;
import android.util.TypedValue;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ParsedIntentInfoUtils {
    public static final boolean DEBUG = false;
    private static final String TAG = "PackageParsing";

    /* JADX WARN: Finally extract failed */
    public static ParseResult<ParsedIntentInfo> parseIntentInfo(String className, ParsingPackage pkg, Resources res, XmlResourceParser parser, boolean allowGlobs, boolean allowAutoVerify, ParseInput input) throws XmlPullParserException, IOException {
        ParseResult result;
        ParsedIntentInfo intentInfo = new ParsedIntentInfo();
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestIntentFilter);
        int i = 2;
        int i2 = 0;
        try {
            intentInfo.setPriority(sa.getInt(2, 0));
            int i3 = 3;
            intentInfo.setOrder(sa.getInt(3, 0));
            TypedValue v = sa.peekValue(0);
            if (v != null) {
                intentInfo.labelRes = v.resourceId;
                if (v.resourceId == 0) {
                    intentInfo.nonLocalizedLabel = v.coerceToString();
                }
            }
            if (ParsingPackageUtils.sUseRoundIcon) {
                intentInfo.icon = sa.getResourceId(7, 0);
            }
            if (intentInfo.icon == 0) {
                intentInfo.icon = sa.getResourceId(1, 0);
            }
            if (allowAutoVerify) {
                intentInfo.setAutoVerify(sa.getBoolean(6, false));
            }
            sa.recycle();
            int depth = parser.getDepth();
            while (true) {
                int type = parser.next();
                if (type != 1 && (type != i3 || parser.getDepth() > depth)) {
                    if (type == i) {
                        String nodeName = parser.getName();
                        int i4 = -1;
                        switch (nodeName.hashCode()) {
                            case -1422950858:
                                if (nodeName.equals("action")) {
                                    i4 = i2;
                                    break;
                                }
                                break;
                            case 3076010:
                                if (nodeName.equals("data")) {
                                    i4 = i;
                                    break;
                                }
                                break;
                            case 50511102:
                                if (nodeName.equals(CardEmulation.EXTRA_CATEGORY)) {
                                    i4 = 1;
                                    break;
                                }
                                break;
                        }
                        switch (i4) {
                            case 0:
                                String value = parser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
                                if (value != null) {
                                    if (!value.isEmpty()) {
                                        intentInfo.addAction(value);
                                        result = input.success(null);
                                        break;
                                    } else {
                                        intentInfo.addAction(value);
                                        result = input.deferError("No value supplied for <android:name>", ParseInput.DeferredError.EMPTY_INTENT_ACTION_CATEGORY);
                                        break;
                                    }
                                } else {
                                    result = input.error("No value supplied for <android:name>");
                                    break;
                                }
                            case 1:
                                String value2 = parser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
                                if (value2 != null) {
                                    if (!value2.isEmpty()) {
                                        intentInfo.addCategory(value2);
                                        result = input.success(null);
                                        break;
                                    } else {
                                        intentInfo.addCategory(value2);
                                        result = input.deferError("No value supplied for <android:name>", ParseInput.DeferredError.EMPTY_INTENT_ACTION_CATEGORY);
                                        break;
                                    }
                                } else {
                                    result = input.error("No value supplied for <android:name>");
                                    break;
                                }
                            case 2:
                                result = parseData(intentInfo, res, parser, allowGlobs, input);
                                break;
                            default:
                                result = ParsingUtils.unknownTag("<intent-filter>", pkg, parser, input);
                                break;
                        }
                        if (result.isError()) {
                            return input.error(result);
                        }
                        i = 2;
                        i2 = 0;
                        i3 = 3;
                    }
                }
            }
            intentInfo.hasDefault = intentInfo.hasCategory(Intent.CATEGORY_DEFAULT);
            return input.success(intentInfo);
        } catch (Throwable th) {
            sa.recycle();
            throw th;
        }
    }

    private static ParseResult<ParsedIntentInfo> parseData(ParsedIntentInfo intentInfo, Resources resources, XmlResourceParser parser, boolean allowGlobs, ParseInput input) {
        TypedArray sa = resources.obtainAttributes(parser, R.styleable.AndroidManifestData);
        try {
            String str = sa.getNonConfigurationString(0, 0);
            if (str != null) {
                intentInfo.addDataType(str);
            }
            String str2 = sa.getNonConfigurationString(10, 0);
            if (str2 != null) {
                intentInfo.addMimeGroup(str2);
            }
            String str3 = sa.getNonConfigurationString(1, 0);
            if (str3 != null) {
                intentInfo.addDataScheme(str3);
            }
            String str4 = sa.getNonConfigurationString(7, 0);
            if (str4 != null) {
                intentInfo.addDataSchemeSpecificPart(str4, 0);
            }
            String str5 = sa.getNonConfigurationString(8, 0);
            if (str5 != null) {
                intentInfo.addDataSchemeSpecificPart(str5, 1);
            }
            String str6 = sa.getNonConfigurationString(9, 0);
            if (str6 != null) {
                if (!allowGlobs) {
                    return input.error("sspPattern not allowed here; ssp must be literal");
                }
                intentInfo.addDataSchemeSpecificPart(str6, 2);
            }
            String str7 = sa.getNonConfigurationString(14, 0);
            if (str7 != null) {
                if (!allowGlobs) {
                    return input.error("sspAdvancedPattern not allowed here; ssp must be literal");
                }
                intentInfo.addDataSchemeSpecificPart(str7, 3);
            }
            String str8 = sa.getNonConfigurationString(12, 0);
            if (str8 != null) {
                intentInfo.addDataSchemeSpecificPart(str8, 4);
            }
            String host = sa.getNonConfigurationString(2, 0);
            String port = sa.getNonConfigurationString(3, 0);
            if (host != null) {
                intentInfo.addDataAuthority(host, port);
            }
            String str9 = sa.getNonConfigurationString(4, 0);
            if (str9 != null) {
                intentInfo.addDataPath(str9, 0);
            }
            String str10 = sa.getNonConfigurationString(5, 0);
            if (str10 != null) {
                intentInfo.addDataPath(str10, 1);
            }
            String str11 = sa.getNonConfigurationString(6, 0);
            if (str11 != null) {
                if (!allowGlobs) {
                    return input.error("pathPattern not allowed here; path must be literal");
                }
                intentInfo.addDataPath(str11, 2);
            }
            String str12 = sa.getNonConfigurationString(13, 0);
            if (str12 != null) {
                if (!allowGlobs) {
                    return input.error("pathAdvancedPattern not allowed here; path must be literal");
                }
                intentInfo.addDataPath(str12, 3);
            }
            String str13 = sa.getNonConfigurationString(11, 0);
            if (str13 != null) {
                intentInfo.addDataPath(str13, 4);
            }
            return input.success(null);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            return input.error(e.toString());
        } finally {
            sa.recycle();
        }
    }
}
