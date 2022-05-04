package android.content.pm;

import android.content.pm.PackageParser;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
/* loaded from: classes.dex */
public interface IPackageParserExt {
    default void hookSetMaxAspectRatio(PackageParser.Package owner, float maxAspectRatio) {
    }

    default void hookParseActivityAlias(PackageParser.Activity a, Resources res, XmlResourceParser parser, PackageParser.Activity target) {
    }
}
