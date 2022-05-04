package com.mediatek.boostfwk.utils;

import android.os.SystemProperties;
/* loaded from: classes.dex */
public final class Config {
    private static final String DISABLE_BOOST_FWK = "0";
    private static final String DISABLE_FRAME_IDENTIFY = "0";
    private static final String DISABLE_SCROLL_IDENTIFY = "0";
    private static final String ENABLE_BOOST_DISPLAY_60 = "0";
    private static final String ENABLE_ENHANCE_LOG = "1";
    public static final int FRAME_HINT_RESCUE_STRENGTH = 50;
    public static final int sSCROLLING_HINT_DURATION = 3000;
    private static final String ENHANCE_LOG_PROPERTY_NAME = "vendor.boostfwk.log.enable";
    private static final String SYS_PROP_ENABLE_LOG = SystemProperties.get(ENHANCE_LOG_PROPERTY_NAME);
    private static final String DISABLE_SCROLL_IDENTIFY_PROPERTY_NAME = "vendor.boostfwk.scrollidentify.option";
    private static final String SYS_PROP_DISABLE_SCROLL_IDENTIFY = SystemProperties.get(DISABLE_SCROLL_IDENTIFY_PROPERTY_NAME);
    private static final String DISABLE_FRAME_IDENTIFY_PROPERTY_NAME = "vendor.boostfwk.frameidentify.option";
    private static final String SYS_PROP_DISABLE_FRAME_IDENTIFY = SystemProperties.get(DISABLE_FRAME_IDENTIFY_PROPERTY_NAME);
    private static final String DISABLE_BOOST_FWK_PROPERTY_NAME = "vendor.boostfwk.option";
    private static final String SYS_PROP_DISABLE_BOOST_FWK = SystemProperties.get(DISABLE_BOOST_FWK_PROPERTY_NAME);
    private static final String ENABLE_BOOST_DISPLAY_60_PROPERTY_NAME = "vendor.boostfwk.display60";
    private static final String SYS_PROP_ENABLE_BOOST_DISPLAY_60 = SystemProperties.get(ENABLE_BOOST_DISPLAY_60_PROPERTY_NAME);

    public static boolean isBoostFwkLogEnable() {
        return ENABLE_ENHANCE_LOG.equals(SYS_PROP_ENABLE_LOG);
    }

    public static boolean isBoostFwkScrollIdentify() {
        return !"0".equals(SYS_PROP_DISABLE_SCROLL_IDENTIFY);
    }

    public static boolean disableFrameIdentify() {
        return "0".equals(SYS_PROP_DISABLE_FRAME_IDENTIFY);
    }

    public static boolean disableSBE() {
        return "0".equals(SYS_PROP_DISABLE_BOOST_FWK);
    }

    public static boolean isBoostFwkScrollIdentifyOn60hz() {
        return !"0".equals(SYS_PROP_ENABLE_BOOST_DISPLAY_60);
    }
}
