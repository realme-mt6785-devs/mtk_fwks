package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ParsedInstrumentationUtils {
    public static ParseResult<ParsedInstrumentation> parseInstrumentation(ParsingPackage pkg, Resources res, XmlResourceParser parser, boolean useRoundIcon, ParseInput input) throws IOException, XmlPullParserException {
        TypedArray sa;
        Throwable th;
        ParsedInstrumentation instrumentation = new ParsedInstrumentation();
        String tag = "<" + parser.getName() + ">";
        TypedArray sa2 = res.obtainAttributes(parser, R.styleable.AndroidManifestInstrumentation);
        try {
            ParseResult<ParsedInstrumentation> result = ParsedComponentUtils.parseComponent(instrumentation, tag, pkg, sa2, useRoundIcon, input, 7, null, 1, 0, 6, 2, 8);
            if (result.isError()) {
                sa2.recycle();
                return result;
            }
            sa = sa2;
            try {
                instrumentation.setTargetPackage(sa.getNonResourceString(3));
                instrumentation.setTargetProcesses(sa.getNonResourceString(9));
                instrumentation.handleProfiling = sa.getBoolean(4, false);
                instrumentation.functionalTest = sa.getBoolean(5, false);
                sa.recycle();
                return ComponentParseUtils.parseAllMetaData(pkg, res, parser, tag, instrumentation, input);
            } catch (Throwable th2) {
                th = th2;
                sa.recycle();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sa = sa2;
        }
    }
}
