package android.content.pm.parsing.component;

import android.content.pm.PermissionInfo;
import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Slog;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ParsedPermissionUtils {
    private static final String TAG = "PackageParsing";

    public static ParseResult<ParsedPermission> parsePermission(ParsingPackage pkg, Resources res, XmlResourceParser parser, boolean useRoundIcon, ParseInput input) throws IOException, XmlPullParserException {
        TypedArray sa;
        Throwable th;
        int otherProtectionFlags;
        String packageName = pkg.getPackageName();
        ParsedPermission permission = new ParsedPermission();
        String tag = "<" + parser.getName() + ">";
        TypedArray sa2 = res.obtainAttributes(parser, R.styleable.AndroidManifestPermission);
        try {
            ParseResult<ParsedPermission> result = ParsedComponentUtils.parseComponent(permission, tag, pkg, sa2, useRoundIcon, input, 8, 5, 1, 0, 6, 2, 9);
            if (result.isError()) {
                sa2.recycle();
                return result;
            }
            if (sa2.hasValue(11)) {
                try {
                    if ("android".equals(packageName)) {
                        permission.backgroundPermission = sa2.getNonResourceString(11);
                    } else {
                        Slog.w("PackageParsing", packageName + " defines a background permission. Only the 'android' package can do that.");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    sa = sa2;
                    sa.recycle();
                    throw th;
                }
            }
            permission.setGroup(sa2.getNonResourceString(4));
            permission.requestRes = sa2.getResourceId(12, 0);
            permission.protectionLevel = sa2.getInt(3, 0);
            permission.flags = sa2.getInt(7, 0);
            int knownCertsResource = sa2.getResourceId(10, 0);
            if (knownCertsResource != 0) {
                String resourceType = res.getResourceTypeName(knownCertsResource);
                if (resourceType.equals("array")) {
                    String[] knownCerts = res.getStringArray(knownCertsResource);
                    if (knownCerts != null) {
                        permission.setKnownCerts(knownCerts);
                    }
                } else {
                    String knownCert = res.getString(knownCertsResource);
                    if (knownCert != null) {
                        permission.setKnownCert(knownCert);
                    }
                }
                if (permission.knownCerts == null) {
                    Slog.w("PackageParsing", packageName + " defines a knownSigner permission but the provided knownCerts resource is null");
                }
            } else {
                String knownCert2 = sa2.getString(10);
                if (knownCert2 != null) {
                    permission.setKnownCert(knownCert2);
                }
            }
            if (permission.isRuntime() && "android".equals(permission.getPackageName())) {
                if (!((permission.flags & 4) == 0 || (permission.flags & 8) == 0)) {
                    throw new IllegalStateException("Permission cannot be both soft and hard restricted: " + permission.getName());
                }
                sa2.recycle();
                permission.protectionLevel = PermissionInfo.fixProtectionLevel(permission.protectionLevel);
                otherProtectionFlags = permission.getProtectionFlags() & (-12353);
                if (!(otherProtectionFlags == 0 || permission.getProtection() == 2 || permission.getProtection() == 4)) {
                    return input.error("<permission> protectionLevel specifies a non-instant, non-appop, non-runtimeOnly flag but is not based on signature or internal type");
                }
                return ComponentParseUtils.parseAllMetaData(pkg, res, parser, tag, permission, input);
            }
            permission.flags &= -5;
            permission.flags &= -9;
            sa2.recycle();
            permission.protectionLevel = PermissionInfo.fixProtectionLevel(permission.protectionLevel);
            otherProtectionFlags = permission.getProtectionFlags() & (-12353);
            if (otherProtectionFlags == 0) {
                return input.error("<permission> protectionLevel specifies a non-instant, non-appop, non-runtimeOnly flag but is not based on signature or internal type");
            }
            return ComponentParseUtils.parseAllMetaData(pkg, res, parser, tag, permission, input);
        } catch (Throwable th3) {
            th = th3;
            sa = sa2;
        }
    }

    public static ParseResult<ParsedPermission> parsePermissionTree(ParsingPackage pkg, Resources res, XmlResourceParser parser, boolean useRoundIcon, ParseInput input) throws IOException, XmlPullParserException {
        int index;
        ParsedPermission permission = new ParsedPermission();
        String tag = "<" + parser.getName() + ">";
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestPermissionTree);
        try {
            ParseResult<ParsedPermission> result = ParsedComponentUtils.parseComponent(permission, tag, pkg, sa, useRoundIcon, input, 4, null, 1, 0, 3, 2, 5);
            if (result.isError()) {
                return result;
            }
            sa.recycle();
            int index2 = permission.getName().indexOf(46);
            if (index2 > 0) {
                index = permission.getName().indexOf(46, index2 + 1);
            } else {
                index = index2;
            }
            if (index < 0) {
                return input.error("<permission-tree> name has less than three segments: " + permission.getName());
            }
            permission.protectionLevel = 0;
            permission.tree = true;
            return ComponentParseUtils.parseAllMetaData(pkg, res, parser, tag, permission, input);
        } finally {
            sa.recycle();
        }
    }

    public static ParseResult<ParsedPermissionGroup> parsePermissionGroup(ParsingPackage pkg, Resources res, XmlResourceParser parser, boolean useRoundIcon, ParseInput input) throws IOException, XmlPullParserException {
        TypedArray sa;
        Throwable th;
        ParsedPermissionGroup permissionGroup = new ParsedPermissionGroup();
        String tag = "<" + parser.getName() + ">";
        TypedArray sa2 = res.obtainAttributes(parser, R.styleable.AndroidManifestPermissionGroup);
        try {
            try {
                ParseResult<ParsedPermissionGroup> result = ParsedComponentUtils.parseComponent(permissionGroup, tag, pkg, sa2, useRoundIcon, input, 7, 4, 1, 0, 5, 2, 8);
                if (result.isError()) {
                    sa2.recycle();
                    return result;
                }
                sa = sa2;
                try {
                    permissionGroup.requestDetailResourceId = sa.getResourceId(12, 0);
                    permissionGroup.backgroundRequestResourceId = sa.getResourceId(9, 0);
                    permissionGroup.backgroundRequestDetailResourceId = sa.getResourceId(10, 0);
                    permissionGroup.requestRes = sa.getResourceId(11, 0);
                    permissionGroup.flags = sa.getInt(6, 0);
                    permissionGroup.priority = sa.getInt(3, 0);
                    sa.recycle();
                    return ComponentParseUtils.parseAllMetaData(pkg, res, parser, tag, permissionGroup, input);
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
}
