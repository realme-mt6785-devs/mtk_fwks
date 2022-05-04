package android.graphics.fonts;

import android.text.FontConfig;
/* loaded from: classes.dex */
public interface ISystemFontExt {
    default String getSystemFontConfig(String fonConfig) {
        return fonConfig;
    }

    default String getOemCustomizationConfigXml(String oemXml) {
        return oemXml;
    }

    default String getOemCustomizationFilePath(String oemPath) {
        return oemPath;
    }

    default boolean isFlipfontUsed() {
        return false;
    }

    default FontConfig.FontFamily getIndividualFontFamily() {
        return null;
    }

    default FontConfig apendIndividualFontFamily(FontConfig fontconfig) {
        return fontconfig;
    }
}
