package android.content.pm.parsing.component;

import android.Manifest;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.ParsingUtils;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.ArraySet;
import com.android.internal.R;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ParsedProcessUtils {
    /* JADX WARN: Finally extract failed */
    private static ParseResult<Set<String>> parseDenyPermission(Set<String> perms, Resources res, XmlResourceParser parser, ParseInput input) throws IOException, XmlPullParserException {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestDenyPermission);
        try {
            String perm = sa.getNonConfigurationString(0, 0);
            if (perm != null && perm.equals(Manifest.permission.INTERNET)) {
                perms = CollectionUtils.add(perms, perm);
            }
            sa.recycle();
            XmlUtils.skipCurrentTag(parser);
            return input.success(perms);
        } catch (Throwable th) {
            sa.recycle();
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    private static ParseResult<Set<String>> parseAllowPermission(Set<String> perms, Resources res, XmlResourceParser parser, ParseInput input) throws IOException, XmlPullParserException {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestAllowPermission);
        try {
            String perm = sa.getNonConfigurationString(0, 0);
            if (perm != null && perm.equals(Manifest.permission.INTERNET)) {
                perms = CollectionUtils.remove(perms, perm);
            }
            sa.recycle();
            XmlUtils.skipCurrentTag(parser);
            return input.success(perms);
        } catch (Throwable th) {
            sa.recycle();
            throw th;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static ParseResult<ParsedProcess> parseProcess(Set<String> perms, String[] separateProcesses, ParsingPackage pkg, Resources res, XmlResourceParser parser, int flags, ParseInput input) throws IOException, XmlPullParserException {
        boolean z;
        ParseResult<?> result;
        ParseResult<?> result2;
        ParsedProcess proc = new ParsedProcess();
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestProcess);
        if (perms != null) {
            try {
                proc.deniedPermissions = new ArraySet(perms);
            } catch (Throwable th) {
                th = th;
                sa.recycle();
                throw th;
            }
        }
        proc.name = sa.getNonConfigurationString(0, 0);
        ParseResult<String> processNameResult = ComponentParseUtils.buildProcessName(pkg.getPackageName(), pkg.getPackageName(), proc.name, flags, separateProcesses, input);
        if (processNameResult.isError()) {
            ParseResult<ParsedProcess> error = input.error(processNameResult);
            sa.recycle();
            return error;
        }
        proc.name = processNameResult.getResult();
        if (proc.name != null && proc.name.length() > 0) {
            proc.gwpAsanMode = sa.getInt(1, -1);
            proc.memtagMode = sa.getInt(2, -1);
            if (sa.hasValue(3)) {
                Boolean v = Boolean.valueOf(sa.getBoolean(3, false));
                proc.nativeHeapZeroInitialized = v.booleanValue() ? 1 : 0;
            }
            sa.recycle();
            int innerDepth = parser.getDepth();
            while (true) {
                int type = parser.next();
                if (type != 1 && (type != 3 || parser.getDepth() > innerDepth)) {
                    if (!(type == 3 || type == 4)) {
                        String tagName = parser.getName();
                        switch (tagName.hashCode()) {
                            case -1239165229:
                                if (tagName.equals("allow-permission")) {
                                    z = true;
                                    break;
                                }
                                z = true;
                                break;
                            case 1658008624:
                                if (tagName.equals("deny-permission")) {
                                    z = false;
                                    break;
                                }
                                z = true;
                                break;
                            default:
                                z = true;
                                break;
                        }
                        switch (z) {
                            case false:
                                ParseResult<?> denyResult = parseDenyPermission(proc.deniedPermissions, res, parser, input);
                                result2 = denyResult;
                                if (denyResult.isSuccess()) {
                                    proc.deniedPermissions = denyResult.getResult();
                                }
                                result = result2;
                                break;
                            case true:
                                ParseResult<?> allowResult = parseAllowPermission(proc.deniedPermissions, res, parser, input);
                                result2 = allowResult;
                                if (allowResult.isSuccess()) {
                                    proc.deniedPermissions = allowResult.getResult();
                                }
                                result = result2;
                                break;
                            default:
                                result = ParsingUtils.unknownTag("<process>", pkg, parser, input);
                                break;
                        }
                        if (result.isError()) {
                            return input.error(result);
                        }
                    }
                }
            }
            return input.success(proc);
        }
        try {
            ParseResult<ParsedProcess> error2 = input.error("<process> does not specify android:process");
            sa.recycle();
            return error2;
        } catch (Throwable th2) {
            th = th2;
            sa.recycle();
            throw th;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r14.equals("allow-permission") != false) goto L_0x005c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00fc, code lost:
        return r20.success(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.pm.parsing.result.ParseResult<android.util.ArrayMap<java.lang.String, android.content.pm.parsing.component.ParsedProcess>> parseProcesses(java.lang.String[] r15, android.content.pm.parsing.ParsingPackage r16, android.content.res.Resources r17, android.content.res.XmlResourceParser r18, int r19, android.content.pm.parsing.result.ParseInput r20) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            Method dump skipped, instructions count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedProcessUtils.parseProcesses(java.lang.String[], android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }
}
