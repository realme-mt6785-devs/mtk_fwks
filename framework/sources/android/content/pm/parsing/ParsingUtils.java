package android.content.pm.parsing;

import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ParsingUtils {
    public static final String ANDROID_RES_NAMESPACE = "http://schemas.android.com/apk/res/android";
    public static final int DEFAULT_MIN_SDK_VERSION = 1;
    public static final int DEFAULT_TARGET_SDK_VERSION = 0;
    public static final String TAG = "PackageParsing";

    public static String buildClassName(String pkg, CharSequence clsSeq) {
        if (clsSeq == null || clsSeq.length() <= 0) {
            return null;
        }
        String cls = clsSeq.toString();
        char c = cls.charAt(0);
        if (c == '.') {
            return pkg + cls;
        } else if (cls.indexOf(46) >= 0) {
            return cls;
        } else {
            return pkg + '.' + cls;
        }
    }

    public static ParseResult unknownTag(String parentTag, ParsingPackage pkg, XmlResourceParser parser, ParseInput input) throws IOException, XmlPullParserException {
        Slog.w(TAG, "Unknown element under " + parentTag + ": " + parser.getName() + " at " + pkg.getBaseApkPath() + " " + parser.getPositionDescription());
        XmlUtils.skipCurrentTag(parser);
        return input.success(null);
    }
}
