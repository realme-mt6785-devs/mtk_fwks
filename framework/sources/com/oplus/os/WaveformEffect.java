package com.oplus.os;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Slog;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class WaveformEffect implements Parcelable {
    public static final Parcelable.Creator<WaveformEffect> CREATOR = new Parcelable.Creator<WaveformEffect>() { // from class: com.oplus.os.WaveformEffect.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WaveformEffect createFromParcel(Parcel p) {
            return new WaveformEffect(p);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WaveformEffect[] newArray(int size) {
            return new WaveformEffect[size];
        }
    };
    public static final int EFFECT_AFGAME_DOUBLE_KILL = 76;
    public static final int EFFECT_AFGAME_NORMAL_KILL = 75;
    public static final int EFFECT_AFGAME_PANTA_KILL = 79;
    public static final int EFFECT_AFGAME_TRIPLE_KILL = 77;
    public static final int EFFECT_AFGAME_ULTRA_KILL = 78;
    public static final int EFFECT_ALARM_ALARM_CLOCK = 238;
    private static final int EFFECT_ALARM_ALARM_CLOCK_DURATION = 2115;
    public static final int EFFECT_ALARM_BEEP = 239;
    private static final int EFFECT_ALARM_BEEP_DURATION = 2373;
    public static final int EFFECT_ALARM_BREEZE = 240;
    private static final int EFFECT_ALARM_BREEZE_DURATION = 32511;
    public static final int EFFECT_ALARM_CLOUDSCAPE = 341;
    private static final int EFFECT_ALARM_CLOUDSCAPE_DURATION = 29278;
    public static final int EFFECT_ALARM_DAWN = 241;
    private static final int EFFECT_ALARM_DAWN_DURATION = 35086;
    public static final int EFFECT_ALARM_DREAM = 242;
    private static final int EFFECT_ALARM_DREAM_DURATION = 38500;
    public static final int EFFECT_ALARM_FLUTTERING = 243;
    private static final int EFFECT_ALARM_FLUTTERING_DURATION = 22410;
    public static final int EFFECT_ALARM_FLYER = 244;
    private static final int EFFECT_ALARM_FLYER_DURATION = 20108;
    public static final int EFFECT_ALARM_GOODENERGY = 342;
    private static final int EFFECT_ALARM_GOODENERGY_DURATION = 27804;
    public static final int EFFECT_ALARM_INTERESTING = 245;
    private static final int EFFECT_ALARM_INTERESTING_DURATION = 10584;
    public static final int EFFECT_ALARM_IN_GAME_ALARM = 364;
    private static final int EFFECT_ALARM_IN_GAME_ALARM_DURATION = 21968;
    public static final int EFFECT_ALARM_LEISURELY = 246;
    private static final int EFFECT_ALARM_LEISURELY_DURATION = 18442;
    public static final int EFFECT_ALARM_MEMORY = 247;
    private static final int EFFECT_ALARM_MEMORY_DURATION = 27611;
    public static final int EFFECT_ALARM_RELIEVED = 248;
    private static final int EFFECT_ALARM_RELIEVED_DURATION = 26404;
    public static final int EFFECT_ALARM_RIPPLE = 249;
    private static final int EFFECT_ALARM_RIPPLE_DURATION = 7590;
    public static final int EFFECT_ALARM_SLOWLY = 250;
    private static final int EFFECT_ALARM_SLOWLY_DURATION = 50077;
    public static final int EFFECT_ALARM_SPRING = 251;
    private static final int EFFECT_ALARM_SPRING_DURATION = 24457;
    public static final int EFFECT_ALARM_STARS = 252;
    private static final int EFFECT_ALARM_STARS_DURATION = 22488;
    public static final int EFFECT_ALARM_SURGING = 253;
    private static final int EFFECT_ALARM_SURGING_DURATION = 12847;
    public static final int EFFECT_ALARM_TACTFULLY = 254;
    private static final int EFFECT_ALARM_TACTFULLY_DURATION = 20299;
    public static final int EFFECT_ALARM_THE_WIND = 255;
    private static final int EFFECT_ALARM_THE_WIND_DURATION = 23264;
    public static final int EFFECT_ALARM_WAKE_UP_SAMURAI = 363;
    private static final int EFFECT_ALARM_WAKE_UP_SAMURAI_DURATION = 44094;
    public static final int EFFECT_ALARM_WALKING_IN_THE_RAIN = 256;
    private static final int EFFECT_ALARM_WALKING_IN_THE_RAIN_DURATION = 18551;
    public static final int EFFECT_ALARM_WEATHER_CLOUDY = 145;
    public static final int EFFECT_ALARM_WEATHER_DEFAULT = 147;
    public static final int EFFECT_ALARM_WEATHER_RAIN = 151;
    public static final int EFFECT_ALARM_WEATHER_SMOG = 149;
    public static final int EFFECT_ALARM_WEATHER_SNOW = 150;
    public static final int EFFECT_ALARM_WEATHER_SUNNY = 148;
    public static final int EFFECT_ALARM_WEATHER_THUNDERSTORM = 146;
    public static final int EFFECT_ALARM_WEATHER_WIND = 144;
    public static final int EFFECT_ALERTSLIDER_DOWN = 308;
    private static final int EFFECT_ALERTSLIDER_DOWN_DURATION = 260;
    public static final int EFFECT_ARTIST_ALARM = 313;
    private static final int EFFECT_ARTIST_ALARM_DURATION = 15686;
    public static final int EFFECT_ARTIST_NOTIFICATION = 310;
    private static final int EFFECT_ARTIST_NOTIFICATION_DURATION = 2733;
    public static final int EFFECT_ARTIST_RINGTONE = 311;
    private static final int EFFECT_ARTIST_RINGTONE_DURATION = 20695;
    public static final int EFFECT_ARTIST_TEXT = 312;
    private static final int EFFECT_ARTIST_TEXT_DURATION = 4767;
    public static final int EFFECT_CANYON_CALL = 351;
    private static final int EFFECT_CANYON_CALL_DURATION = 16417;
    public static final int EFFECT_CHASE = 269;
    private static final int EFFECT_CHASE_DURATION = 52612;
    public static final int EFFECT_CLIMBER = 268;
    private static final int EFFECT_CLIMBER_DURATION = 52151;
    public static final int EFFECT_CUSTOMIZED_ATTACH_TO_MIDDLE = 73;
    public static final int EFFECT_CUSTOMIZED_BREATHE_SPREAD_OUT = 74;
    public static final int EFFECT_CUSTOMIZED_CONFLICT = 52;
    public static final int EFFECT_CUSTOMIZED_CONVERGE = 51;
    public static final int EFFECT_CUSTOMIZED_LONG_VIBRATE = 70;
    public static final int EFFECT_CUSTOMIZED_RUSH_LEFT_TO_RIGHT = 53;
    private static final int EFFECT_CUSTOMIZED_RUSH_LEFT_TO_RIGHT_DURATION = 110;
    public static final int EFFECT_CUSTOMIZED_SPREAD_OUT = 50;
    public static final int EFFECT_CUSTOMIZED_STRONG_GRANULAR = 69;
    private static final int EFFECT_CUSTOMIZED_STRONG_GRANULAR_DURATION = 45;
    public static final int EFFECT_CUSTOMIZED_STRONG_ONE_SENCOND = 72;
    public static final int EFFECT_CUSTOMIZED_STRONG_POINTFOUR_SENCOND = 71;
    public static final int EFFECT_CUSTOMIZED_THREE_DIMENSION_TOUCH = 49;
    private static final int EFFECT_CUSTOMIZED_THREE_DIMENSION_TOUCH_DURATION = 80;
    public static final int EFFECT_CUSTOMIZED_WEAK_GRANULAR = 68;
    private static final int EFFECT_CUSTOMIZED_WEAK_GRANULAR_DURATION = 30;
    public static final int EFFECT_EMULATION_KEYBOARD_DOWN = 302;
    private static final int EFFECT_EMULATION_KEYBOARD_DOWN_DURATION = 35;
    public static final int EFFECT_EMULATION_KEYBOARD_UP = 303;
    private static final int EFFECT_EMULATION_KEYBOARD_UP_DURATION = 11;
    public static final int EFFECT_INVALID = -1;
    public static final int EFFECT_MODERATE_SHORT_VIBRATE_ONCE = 2;
    private static final int EFFECT_MODERATE_SHORT_VIBRATE_ONCE_DURATION = 110;
    public static final int EFFECT_MODERATE_SHORT_VIBRATE_TRIPLE = 3;
    public static final int EFFECT_MODERATE_SHORT_VIBRATE_TWICE = 3;
    public static final int EFFECT_MYSTIC_STORE = 352;
    private static final int EFFECT_MYSTIC_STORE_DURATION = 2755;
    public static final int EFFECT_NOTIFICATION_ALLAY = 214;
    private static final int EFFECT_NOTIFICATION_ALLAY_DURATION = 451;
    public static final int EFFECT_NOTIFICATION_ALLUSION = 215;
    private static final int EFFECT_NOTIFICATION_ALLUSION_DURATION = 960;
    public static final int EFFECT_NOTIFICATION_AMIABLE = 216;
    private static final int EFFECT_NOTIFICATION_AMIABLE_DURATION = 1622;
    public static final int EFFECT_NOTIFICATION_AUDITION = 366;
    private static final int EFFECT_NOTIFICATION_AUDITION_DURATION = 301;
    public static final int EFFECT_NOTIFICATION_BEATING = 334;
    private static final int EFFECT_NOTIFICATION_BEATING_DURATION = 3007;
    public static final int EFFECT_NOTIFICATION_BLARE = 217;
    private static final int EFFECT_NOTIFICATION_BLARE_DURATION = 1388;
    public static final int EFFECT_NOTIFICATION_BLINK = 343;
    private static final int EFFECT_NOTIFICATION_BLINK_DURATION = 331;
    public static final int EFFECT_NOTIFICATION_BLISSFUL = 218;
    private static final int EFFECT_NOTIFICATION_BLISSFUL_DURATION = 2099;
    public static final int EFFECT_NOTIFICATION_BLOCK = 338;
    private static final int EFFECT_NOTIFICATION_BLOCK_DURATION = 436;
    public static final int EFFECT_NOTIFICATION_BOWLBELL = 330;
    private static final int EFFECT_NOTIFICATION_BOWLBELL_DURATION = 4115;
    public static final int EFFECT_NOTIFICATION_BRISK = 219;
    private static final int EFFECT_NOTIFICATION_BRISK_DURATION = 570;
    public static final int EFFECT_NOTIFICATION_BUBBLE = 220;
    private static final int EFFECT_NOTIFICATION_BUBBLE_DURATION = 127;
    public static final int EFFECT_NOTIFICATION_CHEERFUL = 221;
    private static final int EFFECT_NOTIFICATION_CHEERFUL_DURATION = 544;
    public static final int EFFECT_NOTIFICATION_CIRCLE = 337;
    private static final int EFFECT_NOTIFICATION_CIRCLE_DURATION = 3648;
    public static final int EFFECT_NOTIFICATION_CLEAR = 222;
    private static final int EFFECT_NOTIFICATION_CLEAR_DURATION = 457;
    public static final int EFFECT_NOTIFICATION_COMELY = 223;
    private static final int EFFECT_NOTIFICATION_COMELY_DURATION = 3024;
    public static final int EFFECT_NOTIFICATION_COZY = 224;
    private static final int EFFECT_NOTIFICATION_COZY_DURATION = 2303;
    public static final int EFFECT_NOTIFICATION_CRYSTALCLEAR = 129;
    public static final int EFFECT_NOTIFICATION_DING = 225;
    private static final int EFFECT_NOTIFICATION_DING_DURATION = 1697;
    public static final int EFFECT_NOTIFICATION_EFFERVESCE = 226;
    private static final int EFFECT_NOTIFICATION_EFFERVESCE_DURATION = 1506;
    public static final int EFFECT_NOTIFICATION_ELEGANT = 227;
    private static final int EFFECT_NOTIFICATION_ELEGANT_DURATION = 1893;
    public static final int EFFECT_NOTIFICATION_EMERGE = 131;
    public static final int EFFECT_NOTIFICATION_FREE = 228;
    private static final int EFFECT_NOTIFICATION_FREE_DURATION = 1416;
    public static final int EFFECT_NOTIFICATION_FUN = 139;
    public static final int EFFECT_NOTIFICATION_GRANULES = 143;
    public static final int EFFECT_NOTIFICATION_HALLUCINATION = 229;
    private static final int EFFECT_NOTIFICATION_HALLUCINATION_DURATION = 1401;
    public static final int EFFECT_NOTIFICATION_HARP = 133;
    public static final int EFFECT_NOTIFICATION_HEARTBEAT = 6;
    public static final int EFFECT_NOTIFICATION_HEY = 332;
    private static final int EFFECT_NOTIFICATION_HEY_DURATION = 3940;
    public static final int EFFECT_NOTIFICATION_INBOUND = 230;
    private static final int EFFECT_NOTIFICATION_INBOUND_DURATION = 1583;
    public static final int EFFECT_NOTIFICATION_INGENIOUS = 142;
    public static final int EFFECT_NOTIFICATION_INSTANT = 138;
    public static final int EFFECT_NOTIFICATION_IN_GAME_SMS = 365;
    private static final int EFFECT_NOTIFICATION_IN_GAME_SMS_DURATION = 300;
    public static final int EFFECT_NOTIFICATION_JOY = 136;
    public static final int EFFECT_NOTIFICATION_LIGHT = 231;
    private static final int EFFECT_NOTIFICATION_LIGHT_DURATION = 1100;
    public static final int EFFECT_NOTIFICATION_MEET = 232;
    private static final int EFFECT_NOTIFICATION_MEET_DURATION = 871;
    public static final int EFFECT_NOTIFICATION_NAIVETY = 233;
    private static final int EFFECT_NOTIFICATION_NAIVETY_DURATION = 1878;
    public static final int EFFECT_NOTIFICATION_NEWS = 333;
    private static final int EFFECT_NOTIFICATION_NEWS_DURATION = 3082;
    public static final int EFFECT_NOTIFICATION_ONEPLUS_TWINKLE = 237;
    private static final int EFFECT_NOTIFICATION_ONEPLUS_TWINKLE_DURATION = 2206;
    public static final int EFFECT_NOTIFICATION_OVERTONE = 134;
    public static final int EFFECT_NOTIFICATION_PERCUSSION = 135;
    public static final int EFFECT_NOTIFICATION_QUICKLY = 234;
    private static final int EFFECT_NOTIFICATION_QUICKLY_DURATION = 612;
    public static final int EFFECT_NOTIFICATION_RAPID = 8;
    public static final int EFFECT_NOTIFICATION_RECEIVE = 140;
    public static final int EFFECT_NOTIFICATION_REMIND = 7;
    public static final int EFFECT_NOTIFICATION_RHYTHM = 235;
    private static final int EFFECT_NOTIFICATION_RHYTHM_DURATION = 842;
    public static final int EFFECT_NOTIFICATION_RIPPLES = 132;
    public static final int EFFECT_NOTIFICATION_RISE = 331;
    private static final int EFFECT_NOTIFICATION_RISE_DURATION = 3044;
    public static final int EFFECT_NOTIFICATION_SHORT = 335;
    private static final int EFFECT_NOTIFICATION_SHORT_DURATION = 2155;
    public static final int EFFECT_NOTIFICATION_SIMPLE = 128;
    public static final int EFFECT_NOTIFICATION_SPLASH = 141;
    public static final int EFFECT_NOTIFICATION_STREAK = 5;
    public static final int EFFECT_NOTIFICATION_SURPRISE = 236;
    private static final int EFFECT_NOTIFICATION_SURPRISE_DURATION = 1067;
    public static final int EFFECT_NOTIFICATION_SYMPHONIC = 4;
    public static final int EFFECT_NOTIFICATION_TOP = 336;
    private static final int EFFECT_NOTIFICATION_TOP_DURATION = 2412;
    public static final int EFFECT_NOTIFICATION_TUNES = 130;
    public static final int EFFECT_NOTIFICATION_TWINKLE = 137;
    public static final int EFFECT_NOTIFICATION_WHOOPDOOP = 344;
    private static final int EFFECT_NOTIFICATION_WHOOPDOOP_DURATION = 386;
    public static final int EFFECT_NOTIFICATION_ZANZA = 339;
    private static final int EFFECT_NOTIFICATION_ZANZA_DURATION = 1460;
    public static final int EFFECT_OTHER_BIG_SCALE = 9;
    private static final int EFFECT_OTHER_BIG_SCALE_DURATION = 30;
    public static final int EFFECT_OTHER_BREATH_SIMULATION = 165;
    private static final int EFFECT_OTHER_BREATH_SIMULATION_DURATION = 3187;
    public static final int EFFECT_OTHER_BULB = 285;
    private static final int EFFECT_OTHER_BULB_DURATION = 121;
    public static final int EFFECT_OTHER_CHARGING_SIMULATION = 166;
    private static final int EFFECT_OTHER_CHARGING_SIMULATION_DURATION = 935;
    public static final int EFFECT_OTHER_CLOSE = 274;
    private static final int EFFECT_OTHER_CLOSE_DURATION = 16;
    public static final int EFFECT_OTHER_COMPATIBLE_1 = 278;
    private static final int EFFECT_OTHER_COMPATIBLE_1_DURATION = 16;
    public static final int EFFECT_OTHER_COMPATIBLE_2 = 279;
    private static final int EFFECT_OTHER_COMPATIBLE_2_DURATION = 16;
    public static final int EFFECT_OTHER_COMPLETE = 284;
    private static final int EFFECT_OTHER_COMPLETE_DURATION = 16;
    public static final int EFFECT_OTHER_ELASTICITY = 286;
    private static final int EFFECT_OTHER_ELASTICITY_DURATION = 121;
    public static final int EFFECT_OTHER_FINGERPRINT_CORRECT_EFFECT = 156;
    private static final int EFFECT_OTHER_FINGERPRINT_CORRECT_EFFECT_DURATION = 709;
    public static final int EFFECT_OTHER_HALF_LAP = 275;
    private static final int EFFECT_OTHER_HALF_LAP_DURATION = 35;
    public static final int EFFECT_OTHER_KEYBOARD_MEDIUM = 158;
    private static final int EFFECT_OTHER_KEYBOARD_MEDIUM_DURATION = 16;
    public static final int EFFECT_OTHER_KEYBOARD_STRONG = 159;
    private static final int EFFECT_OTHER_KEYBOARD_STRONG_DURATION = 16;
    public static final int EFFECT_OTHER_KEYBOARD_WEAK = 157;
    private static final int EFFECT_OTHER_KEYBOARD_WEAK_DURATION = 16;
    public static final int EFFECT_OTHER_SMALL_SCALE = 10;
    private static final int EFFECT_OTHER_SMALL_SCALE_DURATION = 15;
    public static final int EFFECT_OTHER_STRENGTH_LEVEL_BAR_EDGE = 154;
    private static final int EFFECT_OTHER_STRENGTH_LEVEL_BAR_EDGE_DURATION = 146;
    public static final int EFFECT_OTHER_STYLESWITCH = 280;
    private static final int EFFECT_OTHER_STYLESWITCH_DURATION = 6699;
    public static final int EFFECT_OTHER_STYLESWITCH_SOFT = 287;
    private static final int EFFECT_OTHER_STYLESWITCH_SOFT_DURATION = 6699;
    public static final int EFFECT_OTHER_SUSPENDBUTTON_BOTTOMOUT = 282;
    private static final int EFFECT_OTHER_SUSPENDBUTTON_BOTTOMOUT_DURATION = 16;
    public static final int EFFECT_OTHER_SUSPENDBUTTON_MENU = 283;
    private static final int EFFECT_OTHER_SUSPENDBUTTON_MENU_DURATION = 16;
    public static final int EFFECT_OTHER_THREEFINGERS_LONG = 270;
    private static final int EFFECT_OTHER_THREEFINGERS_LONG_DURATION = 16;
    public static final int EFFECT_OTHER_THREEFINGERS_SCREENSHOT = 272;
    private static final int EFFECT_OTHER_THREEFINGERS_SCREENSHOT_DURATION = 16;
    public static final int EFFECT_OTHER_THREEFINGERS_UP = 271;
    private static final int EFFECT_OTHER_THREEFINGERS_UP_DURATION = 16;
    public static final int EFFECT_OTHER_TWOFINGERS_DOWN = 276;
    private static final int EFFECT_OTHER_TWOFINGERS_DOWN_DURATION = 16;
    public static final int EFFECT_OTHER_TWOFINGERS_LONG = 277;
    private static final int EFFECT_OTHER_TWOFINGERS_LONG_DURATION = 16;
    public static final int EFFECT_OTHER_UNFOLD = 273;
    private static final int EFFECT_OTHER_UNFOLD_DURATION = 16;
    public static final int EFFECT_OTHER_VOICE_ASSISTANT = 167;
    private static final int EFFECT_OTHER_VOICE_ASSISTANT_DURATION = 151;
    public static final int EFFECT_OTHER_VOICE_LEVEL_BAR_EDGE = 153;
    private static final int EFFECT_OTHER_VOICE_LEVEL_BAR_EDGE_DURATION = 297;
    public static final int EFFECT_OTHER_WATERRIPPLE = 281;
    private static final int EFFECT_OTHER_WATERRIPPLE_DURATION = 100;
    public static final int EFFECT_PUBG_RIFLE = 11;
    private static final int EFFECT_PUBG_RIFLE_DURATION = 40;
    public static final int EFFECT_PUBG_SHORT_GUN = 12;
    private static final int EFFECT_PUBG_SHORT_GUN_DURATION = 40;
    public static final int EFFECT_PUBG_SNIPER = 13;
    private static final int EFFECT_PUBG_SNIPER_DURATION = 50;
    private static final int EFFECT_RAM_RAZER_CLICKY_PRESS_DURATION = 14;
    private static final int EFFECT_RAM_RAZER_LINEAR_PRESS_DURATION = 14;
    public static final int EFFECT_RAZER_CLICKY_PRESS = 288;
    public static final int EFFECT_RAZER_LINEAR_PRESS = 289;
    public static final int EFFECT_RINGTONE_ALACRITY = 180;
    private static final int EFFECT_RINGTONE_ALACRITY_DURATION = 13680;
    public static final int EFFECT_RINGTONE_AMENITY = 181;
    private static final int EFFECT_RINGTONE_AMENITY_DURATION = 15602;
    public static final int EFFECT_RINGTONE_BLISS = 106;
    public static final int EFFECT_RINGTONE_BLUES = 182;
    private static final int EFFECT_RINGTONE_BLUES_DURATION = 18563;
    public static final int EFFECT_RINGTONE_BOUNCE = 183;
    private static final int EFFECT_RINGTONE_BOUNCE_DURATION = 10769;
    public static final int EFFECT_RINGTONE_CALM = 100;
    public static final int EFFECT_RINGTONE_CHILDHOOD = 121;
    public static final int EFFECT_RINGTONE_CLASSIC = 108;
    public static final int EFFECT_RINGTONE_CLOUD = 185;
    private static final int EFFECT_RINGTONE_CLOUD_DURATION = 12505;
    public static final int EFFECT_RINGTONE_COMMUTE = 123;
    public static final int EFFECT_RINGTONE_CYCLOTRON = 186;
    private static final int EFFECT_RINGTONE_CYCLOTRON_DURATION = 16388;
    public static final int EFFECT_RINGTONE_DANCE = 110;
    public static final int EFFECT_RINGTONE_DAZZLE = 119;
    public static final int EFFECT_RINGTONE_DELIGHT = 107;
    public static final int EFFECT_RINGTONE_DISTINCT = 187;
    private static final int EFFECT_RINGTONE_DISTINCT_DURATION = 6466;
    public static final int EFFECT_RINGTONE_DREAM = 103;
    public static final int EFFECT_RINGTONE_DREAMJAZZ = 321;
    private static final int EFFECT_RINGTONE_DREAMJAZZ_DURATION = 21600;
    public static final int EFFECT_RINGTONE_DYNAMIC = 188;
    private static final int EFFECT_RINGTONE_DYNAMIC_DURATION = 19166;
    public static final int EFFECT_RINGTONE_EAGER = 209;
    private static final int EFFECT_RINGTONE_EAGER_DURATION = 24614;
    public static final int EFFECT_RINGTONE_EASTERN_TRANQUILITY = 329;
    private static final int EFFECT_RINGTONE_EASTERN_TRANQUILITY_DURATION = 23091;
    public static final int EFFECT_RINGTONE_EBULLITION = 210;
    private static final int EFFECT_RINGTONE_EBULLITION_DURATION = 16246;
    public static final int EFFECT_RINGTONE_ECHO = 189;
    private static final int EFFECT_RINGTONE_ECHO_DURATION = 15501;
    public static final int EFFECT_RINGTONE_ECLUB = 323;
    private static final int EFFECT_RINGTONE_ECLUB_DURATION = 18911;
    public static final int EFFECT_RINGTONE_EXPECT = 190;
    private static final int EFFECT_RINGTONE_EXPECT_DURATION = 7490;
    public static final int EFFECT_RINGTONE_FAIRVIEWS = 125;
    public static final int EFFECT_RINGTONE_FANATICAL = 191;
    private static final int EFFECT_RINGTONE_FANATICAL_DURATION = 23926;
    public static final int EFFECT_RINGTONE_FEELING = 345;
    private static final int EFFECT_RINGTONE_FEELING_DURATION = 19868;
    public static final int EFFECT_RINGTONE_FIREFLY = 112;
    public static final int EFFECT_RINGTONE_FRESHMORNING = 325;
    private static final int EFFECT_RINGTONE_FRESHMORNING_DURATION = 23810;
    public static final int EFFECT_RINGTONE_FRIENDSHIP = 211;
    private static final int EFFECT_RINGTONE_FRIENDSHIP_DURATION = 23824;
    public static final int EFFECT_RINGTONE_FUNK = 324;
    public static final int EFFECT_RINGTONE_FUNKY = 192;
    private static final int EFFECT_RINGTONE_FUNKY_DURATION = 8897;
    private static final int EFFECT_RINGTONE_FUNK_DURATION = 18778;
    public static final int EFFECT_RINGTONE_GAZINGOUT = 118;
    public static final int EFFECT_RINGTONE_GUITAR = 193;
    private static final int EFFECT_RINGTONE_GUITAR_DURATION = 16096;
    public static final int EFFECT_RINGTONE_HARPING = 194;
    private static final int EFFECT_RINGTONE_HARPING_DURATION = 13344;
    public static final int EFFECT_RINGTONE_HIGHLIGHT = 195;
    private static final int EFFECT_RINGTONE_HIGHLIGHT_DURATION = 6872;
    public static final int EFFECT_RINGTONE_HOLIDAY = 326;
    private static final int EFFECT_RINGTONE_HOLIDAY_DURATION = 24970;
    public static final int EFFECT_RINGTONE_HOUSEMUSIC = 322;
    private static final int EFFECT_RINGTONE_HOUSEMUSIC_DURATION = 19181;
    public static final int EFFECT_RINGTONE_INNOCENCE = 197;
    private static final int EFFECT_RINGTONE_INNOCENCE_DURATION = 20830;
    public static final int EFFECT_RINGTONE_IN_GAME_RINGTON = 361;
    private static final int EFFECT_RINGTONE_IN_GAME_RINGTON_DURATION = 15370;
    public static final int EFFECT_RINGTONE_JAZZ_LIFE = 212;
    private static final int EFFECT_RINGTONE_JAZZ_LIFE_DURATION = 20556;
    public static final int EFFECT_RINGTONE_JOURNEY = 198;
    private static final int EFFECT_RINGTONE_JOURNEY_DURATION = 24926;
    public static final int EFFECT_RINGTONE_JOYOUS = 199;
    private static final int EFFECT_RINGTONE_JOYOUS_DURATION = 5844;
    public static final int EFFECT_RINGTONE_LAKESIDE = 117;
    public static final int EFFECT_RINGTONE_LAZY = 200;
    private static final int EFFECT_RINGTONE_LAZY_DURATION = 18341;
    public static final int EFFECT_RINGTONE_LDYL = 196;
    private static final int EFFECT_RINGTONE_LDYL_DURATION = 16848;
    public static final int EFFECT_RINGTONE_LONGING = 105;
    public static final int EFFECT_RINGTONE_MARIMBA = 201;
    private static final int EFFECT_RINGTONE_MARIMBA_DURATION = 8234;
    public static final int EFFECT_RINGTONE_MEMORIES = 115;
    public static final int EFFECT_RINGTONE_MODERN = 328;
    private static final int EFFECT_RINGTONE_MODERN_DURATION = 24040;
    public static final int EFFECT_RINGTONE_MYSTICAL = 202;
    private static final int EFFECT_RINGTONE_MYSTICAL_DURATION = 18040;
    public static final int EFFECT_RINGTONE_NATURE = 346;
    private static final int EFFECT_RINGTONE_NATURE_DURATION = 17963;
    public static final int EFFECT_RINGTONE_NIGHT = 116;
    public static final int EFFECT_RINGTONE_NOSTALGIC = 101;
    public static final int EFFECT_RINGTONE_NOVIBRATE = 67;
    public static final int EFFECT_RINGTONE_OLD_TELEPHONE = 203;
    private static final int EFFECT_RINGTONE_OLD_TELEPHONE_DURATION = 6324;
    public static final int EFFECT_RINGTONE_ONEPLUS_CALM = 184;
    private static final int EFFECT_RINGTONE_ONEPLUS_CALM_DURATION = 6140;
    public static final int EFFECT_RINGTONE_ONEPLUS_TUNE = 204;
    private static final int EFFECT_RINGTONE_ONEPLUS_TUNE_DURATION = 14774;
    public static final int EFFECT_RINGTONE_OPTIMISTIC = 206;
    private static final int EFFECT_RINGTONE_OPTIMISTIC_DURATION = 19962;
    public static final int EFFECT_RINGTONE_PIANO = 207;
    private static final int EFFECT_RINGTONE_PIANO_DURATION = 22222;
    public static final int EFFECT_RINGTONE_PLAYPARK = 109;
    public static final int EFFECT_RINGTONE_PURE = 127;
    public static final int EFFECT_RINGTONE_REALME_ITSREALME = 82;
    public static final int EFFECT_RINGTONE_REALME_JINGLE = 81;
    public static final int EFFECT_RINGTONE_REALME_TUNE = 80;
    public static final int EFFECT_RINGTONE_RELAX = 120;
    public static final int EFFECT_RINGTONE_RHYTHM = 205;
    private static final int EFFECT_RINGTONE_RHYTHM_DURATION = 17807;
    public static final int EFFECT_RINGTONE_RM_ITSRM = 82;
    private static final int EFFECT_RINGTONE_RM_ITSRM_DURATION = 26157;
    public static final int EFFECT_RINGTONE_RM_JINGLE = 81;
    private static final int EFFECT_RINGTONE_RM_JINGLE_DURATION = 1731;
    public static final int EFFECT_RINGTONE_RM_TUNE = 80;
    private static final int EFFECT_RINGTONE_RM_TUNE_DURATION = 21551;
    public static final int EFFECT_RINGTONE_ROCK = 360;
    private static final int EFFECT_RINGTONE_ROCK_DURATION = 37155;
    public static final int EFFECT_RINGTONE_ROMANCE = 102;
    public static final int EFFECT_RINGTONE_SCHOOL = 122;
    public static final int EFFECT_RINGTONE_SILENCE = 114;
    public static final int EFFECT_RINGTONE_SOLITUDE = 126;
    public static final int EFFECT_RINGTONE_STARS = 113;
    public static final int EFFECT_RINGTONE_SUMMER = 124;
    public static final int EFFECT_RINGTONE_SUN_GLITTERING = 213;
    private static final int EFFECT_RINGTONE_SUN_GLITTERING_DURATION = 18391;
    public static final int EFFECT_RINGTONE_TEMPLESOUND = 320;
    private static final int EFFECT_RINGTONE_TEMPLESOUND_DURATION = 28818;
    public static final int EFFECT_RINGTONE_TRINKETS = 111;
    public static final int EFFECT_RINGTONE_T_JINGLE = 307;
    private static final int EFFECT_RINGTONE_T_JINGLE_DURATION = 2112;
    public static final int EFFECT_RINGTONE_VISIONS = 104;
    public static final int EFFECT_RINGTONE_WATERCICADE = 327;
    private static final int EFFECT_RINGTONE_WATERCICADE_DURATION = 17751;
    public static final int EFFECT_RINGTONE_WHIRL = 208;
    private static final int EFFECT_RINGTONE_WHIRL_DURATION = 11048;
    public static final int EFFECT_SGAME_DOUBLE_KILL = 55;
    public static final int EFFECT_SGAME_FIRST_BLOOD = 54;
    public static final int EFFECT_SGAME_GODLIKE = 62;
    public static final int EFFECT_SGAME_KILLING_SPREE = 59;
    public static final int EFFECT_SGAME_LEGENDARY = 63;
    public static final int EFFECT_SGAME_PANTA_KILL = 58;
    public static final int EFFECT_SGAME_RAMPAGE = 60;
    public static final int EFFECT_SGAME_TRIBLE_KILL = 56;
    public static final int EFFECT_SGAME_ULTRA_KILL = 57;
    public static final int EFFECT_SGAME_UNSTOPPABLE = 61;
    public static final int EFFECT_TELCEL_RIE = 350;
    private static final int EFFECT_TELCEL_RIE_DURATION = 58853;
    public static final int EFFECT_VIBRATE_WITH_GT = 314;
    private static final int EFFECT_VIBRATE_WITH_GT_DURATION = 1080;
    public static final int EFFECT_VIBRATE_WITH_RINGTONE = 64;
    public static final int EFFECT_WEAKEST_SHORT_VIBRATE_ONCE = 0;
    private static final int EFFECT_WEAKEST_SHORT_VIBRATE_ONCE_DURATION = 35;
    public static final int EFFECT_WEAK_EMULATION_KEYBOARD_DOWN = 304;
    private static final int EFFECT_WEAK_EMULATION_KEYBOARD_DOWN_DURATION = 35;
    public static final int EFFECT_WEAK_EMULATION_KEYBOARD_UP = 305;
    private static final int EFFECT_WEAK_EMULATION_KEYBOARD_UP_DURATION = 11;
    public static final int EFFECT_WEAK_SHORT_VIBRATE_ONCE = 1;
    private static final int EFFECT_WEAK_SHORT_VIBRATE_ONCE_DURATION = 45;
    public static final int EFFFCT_OTHER_STEPABLE_REGULATE = 152;
    private static final int EFFFCT_OTHER_STEPABLE_REGULATE_DURATION = 66;
    private static final int ERTP_INDEX_NOTIFICATION_INBOUND = 253;
    private static final ArrayMap RINGTONE_FILENAME_TITLE;
    private static final ArrayMap RINGTONE_TITLE_EFFECTS;
    private static final SparseIntArray RTPINDEX_EFFECTS;
    private static final int RTP_INDEX_AFGAME_DOUBLE_KILL = 101;
    private static final int RTP_INDEX_AFGAME_NORMAL_KILL = 100;
    private static final int RTP_INDEX_AFGAME_PANTA_KILL = 104;
    private static final int RTP_INDEX_AFGAME_TRIBLE_KILL = 102;
    private static final int RTP_INDEX_AFGAME_ULTRA_KILL = 103;
    private static final int RTP_INDEX_ALARM_ALARM_CLOCK = 262;
    private static final int RTP_INDEX_ALARM_BEEP = 263;
    private static final int RTP_INDEX_ALARM_BREEZE = 264;
    private static final int RTP_INDEX_ALARM_CLOUDSCAPE = 94;
    private static final int RTP_INDEX_ALARM_DAWN = 265;
    private static final int RTP_INDEX_ALARM_DREAM = 266;
    private static final int RTP_INDEX_ALARM_FLUTTERING = 267;
    private static final int RTP_INDEX_ALARM_FLYER = 268;
    private static final int RTP_INDEX_ALARM_INTERESTING = 269;
    private static final int RTP_INDEX_ALARM_LEISURELY = 270;
    private static final int RTP_INDEX_ALARM_MEMORY = 271;
    private static final int RTP_INDEX_ALARM_RELIEVED = 272;
    private static final int RTP_INDEX_ALARM_RIPPLE = 273;
    private static final int RTP_INDEX_ALARM_SLOWLY = 274;
    private static final int RTP_INDEX_ALARM_SPRING = 275;
    private static final int RTP_INDEX_ALARM_STARS = 276;
    private static final int RTP_INDEX_ALARM_SURGING = 277;
    private static final int RTP_INDEX_ALARM_TACTFULLY = 278;
    private static final int RTP_INDEX_ALARM_THE_WIND = 279;
    private static final int RTP_INDEX_ALARM_WALKING_IN_THE_RAIN = 280;
    private static final int RTP_INDEX_ALARM_WEATHER_CLOUDY = 143;
    private static final int RTP_INDEX_ALARM_WEATHER_DEFAULT = 145;
    private static final int RTP_INDEX_ALARM_WEATHER_RAIN = 149;
    private static final int RTP_INDEX_ALARM_WEATHER_SMOG = 147;
    private static final int RTP_INDEX_ALARM_WEATHER_SNOW = 148;
    private static final int RTP_INDEX_ALARM_WEATHER_SUNNY = 146;
    private static final int RTP_INDEX_ALARM_WEATHER_THUNDERSTORM = 144;
    private static final int RTP_INDEX_ALARM_WEATHER_WIND = 142;
    private static final int RTP_INDEX_ALERTSLIDER_DOWN = 308;
    private static final int RTP_INDEX_ARTIST_ALARM = 153;
    private static final int RTP_INDEX_ARTIST_NOTIFICATION = 150;
    private static final int RTP_INDEX_ARTIST_RINGTONE = 151;
    private static final int RTP_INDEX_ARTIST_TEXT = 152;
    private static final int RTP_INDEX_ATTACH_TO_MIDDLE = 54;
    private static final int RTP_INDEX_AUDITION = 301;
    private static final int RTP_INDEX_BREATHE_SPREAD_OUT = 55;
    private static final int RTP_INDEX_BREATH_SIMULATION = 118;
    private static final int RTP_INDEX_BULB = 186;
    private static final int RTP_INDEX_CANYON_CALL = 90;
    private static final int RTP_INDEX_CHARGING_SIMULATION = 108;
    private static final int RTP_INDEX_CHASE = 293;
    private static final int RTP_INDEX_CLIMBER = 292;
    private static final int RTP_INDEX_CLOSE = 175;
    private static final int RTP_INDEX_COMPATIBLE_1 = 179;
    private static final int RTP_INDEX_COMPATIBLE_2 = 180;
    private static final int RTP_INDEX_COMPLETE = 185;
    private static final int RTP_INDEX_EFFECT_ALARM_GOODENERGY = 95;
    private static final int RTP_INDEX_EFFECT_NOTIFICATION_BLINK = 96;
    private static final int RTP_INDEX_EFFECT_NOTIFICATION_WHOOPDOOP = 97;
    private static final int RTP_INDEX_EFFECT_RINGTONE_FEELING = 98;
    private static final int RTP_INDEX_EFFECT_RINGTONE_NATURE = 99;
    private static final int RTP_INDEX_ELASTICITY = 187;
    private static final int RTP_INDEX_EMULATION_KEYBOARD_DOWN = 302;
    private static final int RTP_INDEX_EMULATION_KEYBOARD_UP = 303;
    private static final int RTP_INDEX_ERROR_MESSAGE = 46;
    private static final int RTP_INDEX_FINGERPRINT_CORRECT_EFFECT = 109;
    private static final int RTP_INDEX_GAME_RINGTON = 297;
    private static final int RTP_INDEX_HALF_LAP = 176;
    private static final int RTP_INDEX_HEARTBEAT = 43;
    private static final int RTP_INDEX_IN_GAME_ALARM = 299;
    private static final int RTP_INDEX_IN_GAME_SMS = 300;
    private static final int RTP_INDEX_KEYBOARD_MEDIUM = 111;
    private static final int RTP_INDEX_KEYBOARD_STRONG = 112;
    private static final int RTP_INDEX_KEYBOARD_WEAK = 110;
    private static final int RTP_INDEX_LONG_VIBRATE = 56;
    private static final int RTP_INDEX_MYSTIC_STORE = 91;
    private static final int RTP_INDEX_NOTIFICATION_ALLAY = 237;
    private static final int RTP_INDEX_NOTIFICATION_ALLUSION = 238;
    private static final int RTP_INDEX_NOTIFICATION_AMIABLE = 239;
    private static final int RTP_INDEX_NOTIFICATION_BEATING = 89;
    private static final int RTP_INDEX_NOTIFICATION_BLARE = 240;
    private static final int RTP_INDEX_NOTIFICATION_BLISSFUL = 241;
    private static final int RTP_INDEX_NOTIFICATION_BLOCK = 82;
    private static final int RTP_INDEX_NOTIFICATION_BOWLBELL = 88;
    private static final int RTP_INDEX_NOTIFICATION_BRISK = 242;
    private static final int RTP_INDEX_NOTIFICATION_BUBBLE = 243;
    private static final int RTP_INDEX_NOTIFICATION_CHEERFUL = 244;
    private static final int RTP_INDEX_NOTIFICATION_CIRCLE = 80;
    private static final int RTP_INDEX_NOTIFICATION_CLEAR = 245;
    private static final int RTP_INDEX_NOTIFICATION_COMELY = 246;
    private static final int RTP_INDEX_NOTIFICATION_COZY = 247;
    private static final int RTP_INDEX_NOTIFICATION_CRYSTALCLEAR = 128;
    private static final int RTP_INDEX_NOTIFICATION_DING = 248;
    private static final int RTP_INDEX_NOTIFICATION_EFFERVESCE = 249;
    private static final int RTP_INDEX_NOTIFICATION_ELEGANT = 250;
    private static final int RTP_INDEX_NOTIFICATION_EMERGE = 123;
    private static final int RTP_INDEX_NOTIFICATION_FREE = 251;
    private static final int RTP_INDEX_NOTIFICATION_FUN = 13;
    private static final int RTP_INDEX_NOTIFICATION_GRANULES = 9;
    private static final int RTP_INDEX_NOTIFICATION_HALLUCINATION = 252;
    private static final int RTP_INDEX_NOTIFICATION_HARP = 131;
    private static final int RTP_INDEX_NOTIFICATION_HEY = 83;
    private static final int RTP_INDEX_NOTIFICATION_INGENIOUS = 12;
    private static final int RTP_INDEX_NOTIFICATION_INSTANT = 2;
    private static final int RTP_INDEX_NOTIFICATION_JOY = 129;
    private static final int RTP_INDEX_NOTIFICATION_LIGHT = 254;
    private static final int RTP_INDEX_NOTIFICATION_MEET = 255;
    private static final int RTP_INDEX_NOTIFICATION_NAIVETY = 256;
    private static final int RTP_INDEX_NOTIFICATION_NEWS = 85;
    private static final int RTP_INDEX_NOTIFICATION_ONEPLUS_TWINKLE = 260;
    private static final int RTP_INDEX_NOTIFICATION_OVERTONE = 132;
    private static final int RTP_INDEX_NOTIFICATION_PERCUSSION = 126;
    private static final int RTP_INDEX_NOTIFICATION_QUICKLY = 257;
    private static final int RTP_INDEX_NOTIFICATION_RECEIVE = 15;
    private static final int RTP_INDEX_NOTIFICATION_RHYTHM = 258;
    private static final int RTP_INDEX_NOTIFICATION_RIPPLES = 127;
    private static final int RTP_INDEX_NOTIFICATION_RISE = 81;
    private static final int RTP_INDEX_NOTIFICATION_SHORT = 87;
    private static final int RTP_INDEX_NOTIFICATION_SIMPLE = 133;
    private static final int RTP_INDEX_NOTIFICATION_SPLASH = 16;
    private static final int RTP_INDEX_NOTIFICATION_SURPRISE = 259;
    private static final int RTP_INDEX_NOTIFICATION_TOP = 86;
    private static final int RTP_INDEX_NOTIFICATION_TUNES = 125;
    private static final int RTP_INDEX_NOTIFICATION_TWINKLE = 130;
    private static final int RTP_INDEX_NOTIFICATION_ZANZA = 84;
    private static final int RTP_INDEX_RAPID = 45;
    private static final int RTP_INDEX_REMIND = 44;
    private static final int RTP_INDEX_RINGTONE_ALACRITY = 201;
    private static final int RTP_INDEX_RINGTONE_AMENITY = 202;
    private static final int RTP_INDEX_RINGTONE_BLISS = 138;
    private static final int RTP_INDEX_RINGTONE_BLUES = 203;
    private static final int RTP_INDEX_RINGTONE_BOUNCE = 204;
    private static final int RTP_INDEX_RINGTONE_CALM = 140;
    private static final int RTP_INDEX_RINGTONE_CHILDHOOD = 19;
    private static final int RTP_INDEX_RINGTONE_CLASSIC = 135;
    private static final int RTP_INDEX_RINGTONE_CLOUD = 206;
    private static final int RTP_INDEX_RINGTONE_COMMUTE = 20;
    private static final int RTP_INDEX_RINGTONE_CYCLOTRON = 207;
    private static final int RTP_INDEX_RINGTONE_DANCE = 30;
    private static final int RTP_INDEX_RINGTONE_DAZZLE = 28;
    private static final int RTP_INDEX_RINGTONE_DELIGHT = 141;
    private static final int RTP_INDEX_RINGTONE_DISTINCT = 208;
    private static final int RTP_INDEX_RINGTONE_DREAM = 139;
    private static final int RTP_INDEX_RINGTONE_DREAMJAZZ = 78;
    private static final int RTP_INDEX_RINGTONE_DYNAMIC = 209;
    private static final int RTP_INDEX_RINGTONE_EAGER = 232;
    private static final int RTP_INDEX_RINGTONE_EASTERN_TRANQUILITY = 71;
    private static final int RTP_INDEX_RINGTONE_EBULLITION = 233;
    private static final int RTP_INDEX_RINGTONE_ECHO = 210;
    private static final int RTP_INDEX_RINGTONE_ECLUB = 73;
    private static final int RTP_INDEX_RINGTONE_EXPECT = 211;
    private static final int RTP_INDEX_RINGTONE_FAIRVIEWS = 121;
    private static final int RTP_INDEX_RINGTONE_FANATICAL = 212;
    private static final int RTP_INDEX_RINGTONE_FIREFLY = 22;
    private static final int RTP_INDEX_RINGTONE_FRESHMORNING = 70;
    private static final int RTP_INDEX_RINGTONE_FRIENDSHIP = 234;
    private static final int RTP_INDEX_RINGTONE_FUNK = 75;
    private static final int RTP_INDEX_RINGTONE_FUNKY = 213;
    private static final int RTP_INDEX_RINGTONE_GAZINGOUT = 24;
    private static final int RTP_INDEX_RINGTONE_GUITAR = 214;
    private static final int RTP_INDEX_RINGTONE_HARPING = 215;
    private static final int RTP_INDEX_RINGTONE_HIGHLIGHT = 216;
    private static final int RTP_INDEX_RINGTONE_HOLIDAY = 74;
    private static final int RTP_INDEX_RINGTONE_HOUSEMUSIC = 76;
    private static final int RTP_INDEX_RINGTONE_INNOCENCE = 218;
    private static final int RTP_INDEX_RINGTONE_JAZZ_LIFE = 235;
    private static final int RTP_INDEX_RINGTONE_JOURNEY = 219;
    private static final int RTP_INDEX_RINGTONE_JOYOUS = 220;
    private static final int RTP_INDEX_RINGTONE_LAKESIDE = 25;
    private static final int RTP_INDEX_RINGTONE_LAZY = 221;
    private static final int RTP_INDEX_RINGTONE_LDYL = 217;
    private static final int RTP_INDEX_RINGTONE_LONGING = 124;
    private static final int RTP_INDEX_RINGTONE_MARIMBA = 222;
    private static final int RTP_INDEX_RINGTONE_MEMORIES = 27;
    private static final int RTP_INDEX_RINGTONE_MODERN = 79;
    private static final int RTP_INDEX_RINGTONE_MYSTICAL = 223;
    private static final int RTP_INDEX_RINGTONE_NIGHT = 29;
    private static final int RTP_INDEX_RINGTONE_NOSTALGIC = 134;
    private static final int RTP_INDEX_RINGTONE_OLD_TELEPHONE = 224;
    private static final int RTP_INDEX_RINGTONE_ONEPLUS_CALM = 205;
    private static final int RTP_INDEX_RINGTONE_ONEPLUS_TUNE = 225;
    private static final int RTP_INDEX_RINGTONE_OPTIMISTIC = 227;
    private static final int RTP_INDEX_RINGTONE_PIANO = 228;
    private static final int RTP_INDEX_RINGTONE_PLAYPARK = 31;
    private static final int RTP_INDEX_RINGTONE_PURE = 49;
    private static final int RTP_INDEX_RINGTONE_RELAX = 32;
    private static final int RTP_INDEX_RINGTONE_RHYTHM = 226;
    private static final int RTP_INDEX_RINGTONE_ROMANCE = 137;
    private static final int RTP_INDEX_RINGTONE_SCHOOL = 17;
    private static final int RTP_INDEX_RINGTONE_SILENCE = 35;
    private static final int RTP_INDEX_RINGTONE_SOLITUDE = 34;
    private static final int RTP_INDEX_RINGTONE_STARS = 36;
    private static final int RTP_INDEX_RINGTONE_SUMMER = 37;
    private static final int RTP_INDEX_RINGTONE_SUN_GLITTERING = 236;
    private static final int RTP_INDEX_RINGTONE_TEMPLESOUND = 77;
    private static final int RTP_INDEX_RINGTONE_TRINKETS = 38;
    private static final int RTP_INDEX_RINGTONE_T_JINGLE = 231;
    private static final int RTP_INDEX_RINGTONE_VISIONS = 136;
    private static final int RTP_INDEX_RINGTONE_WATERCICADE = 72;
    private static final int RTP_INDEX_RINGTONE_WHIRL = 229;
    private static final int RTP_INDEX_RM_ITSRM = 161;
    private static final int RTP_INDEX_RM_JINGLE = 163;
    private static final int RTP_INDEX_RM_TUNE = 162;
    private static final int RTP_INDEX_ROCK = 296;
    private static final int RTP_INDEX_SGAME_DOUBLE_KILL = 61;
    private static final int RTP_INDEX_SGAME_FIRST_BLOOD = 60;
    private static final int RTP_INDEX_SGAME_GODLIKE = 68;
    private static final int RTP_INDEX_SGAME_KILLING_SPREE = 65;
    private static final int RTP_INDEX_SGAME_LEGENDARY = 69;
    private static final int RTP_INDEX_SGAME_PANTA_KILL = 64;
    private static final int RTP_INDEX_SGAME_RAMPAGE = 66;
    private static final int RTP_INDEX_SGAME_TRIBLE_KILL = 62;
    private static final int RTP_INDEX_SGAME_ULTRA_KILL = 63;
    private static final int RTP_INDEX_SGAME_UNSTOPPABLE = 67;
    private static final int RTP_INDEX_SPREAD_OUT = 47;
    private static final int RTP_INDEX_STEPABLE_REGULATE = 105;
    private static final int RTP_INDEX_STREAK = 42;
    private static final int RTP_INDEX_STRENGTH_LEVEL_BAR_EDGE = 107;
    private static final int RTP_INDEX_STRONG_ONE_SENCOND = 58;
    private static final int RTP_INDEX_STRONG_POINTFOUR_SENCOND = 57;
    private static final int RTP_INDEX_STYLESWITCH = 181;
    private static final int RTP_INDEX_STYLESWITCH_SOFT = 188;
    private static final int RTP_INDEX_SUSPENDBUTTON_BOTTOMOUT = 183;
    private static final int RTP_INDEX_SUSPENDBUTTON_MENU = 184;
    private static final int RTP_INDEX_SYMPHONIC = 41;
    private static final int RTP_INDEX_TELCEL_RIE = 119;
    private static final int RTP_INDEX_THREEFINGERS_LONG = 171;
    private static final int RTP_INDEX_THREEFINGERS_SCREENSHOT = 173;
    private static final int RTP_INDEX_THREEFINGERS_UP = 172;
    private static final int RTP_INDEX_TWOFINGERS_DOWN = 177;
    private static final int RTP_INDEX_TWOFINGERS_LONG = 178;
    private static final int RTP_INDEX_UNFOLD = 174;
    private static final int RTP_INDEX_VIBRATE_GT = 170;
    private static final int RTP_INDEX_VOICE_ASSISTANT = 122;
    private static final int RTP_INDEX_VOICE_LEVEL_BAR_EDGE = 106;
    private static final int RTP_INDEX_WAKE_UP_SAMURAI = 298;
    private static final int RTP_INDEX_WATERRIPPLE = 182;
    private static final int RTP_INDEX_WEAK_EMULATION_KEYBOARD_DOWN = 304;
    private static final int RTP_INDEX_WEAK_EMULATION_KEYBOARD_UP = 305;
    public static final int SETTINGS_VALUE_EFFECT_STRONG = 2400;
    public static final int STRENGTH_LIGHT = 0;
    public static final int STRENGTH_MEDIUM = 1;
    public static final int STRENGTH_STRONG = 2;
    private static final String TAG = "WaveformEffect";
    private static final SparseIntArray WAVEFORMINDEX_EFFECTS;
    private static final SparseLongArray WAVEFORM_EFFECT_DURATION;
    private static final int WAVEFORM_INDEX_BIG_SCALE = 13;
    private static final SparseLongArray WAVEFORM_INDEX_DURATION;
    public static final int WAVEFORM_INDEX_INVALID = -1;
    private static final int WAVEFORM_INDEX_MODERATE_SHORT = 3;
    private static final int WAVEFORM_INDEX_PUBG_RIFLE = 10;
    private static final int WAVEFORM_INDEX_PUBG_SHORT_GUN = 11;
    private static final int WAVEFORM_INDEX_PUBG_SNIPER = 12;
    private static final int WAVEFORM_INDEX_RAZER_CLICKY_PRESS = 8;
    private static final int WAVEFORM_INDEX_RAZER_LINEAR_PRESS = 9;
    private static final int WAVEFORM_INDEX_RUSH_LEFT_TO_RIGHT = 5;
    private static final int WAVEFORM_INDEX_SMALL_SCALE = 14;
    private static final int WAVEFORM_INDEX_STRONG_GRANULAR = 6;
    private static final int WAVEFORM_INDEX_THREE_DIMENSION_TOUCH = 4;
    private static final int WAVEFORM_INDEX_WEAKEST_SHORT = 1;
    private static final int WAVEFORM_INDEX_WEAK_GRANULAR = 7;
    private static final int WAVEFORM_INDEX_WEAK_SHORT = 2;
    public static final int WAVEFORM_NODE_INVALID = -1;
    public static final int WAVEFORM_NODE_RAM = 1;
    public static final int WAVEFORM_NODE_RTP = 2;
    private boolean mAsynchronous;
    private boolean mEffectLoop;
    private int mEffectStrength;
    private int mEffectType;
    private boolean mIsRingtoneCustomized;
    private String mRingtoneFilePath;
    private int mRingtoneVibrateType;
    private boolean mStrengthSettingEnabled;
    private int mUsageHint;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        WAVEFORMINDEX_EFFECTS = sparseIntArray;
        sparseIntArray.put(0, 1);
        sparseIntArray.put(1, 2);
        sparseIntArray.put(2, 3);
        sparseIntArray.put(49, 4);
        sparseIntArray.put(53, 5);
        sparseIntArray.put(69, 6);
        sparseIntArray.put(68, 7);
        sparseIntArray.put(288, 8);
        sparseIntArray.put(289, 9);
        sparseIntArray.put(9, 13);
        sparseIntArray.put(10, 14);
        sparseIntArray.put(11, 10);
        sparseIntArray.put(12, 11);
        sparseIntArray.put(13, 12);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        RTPINDEX_EFFECTS = sparseIntArray2;
        sparseIntArray2.put(3, 46);
        sparseIntArray2.put(50, 47);
        sparseIntArray2.put(70, 56);
        sparseIntArray2.put(71, 57);
        sparseIntArray2.put(72, 58);
        sparseIntArray2.put(73, 54);
        sparseIntArray2.put(74, 55);
        sparseIntArray2.put(4, 41);
        sparseIntArray2.put(5, 42);
        sparseIntArray2.put(6, 43);
        sparseIntArray2.put(7, 44);
        sparseIntArray2.put(8, 45);
        sparseIntArray2.put(54, 60);
        sparseIntArray2.put(55, 61);
        sparseIntArray2.put(56, 62);
        sparseIntArray2.put(57, 63);
        sparseIntArray2.put(58, 64);
        sparseIntArray2.put(59, 65);
        sparseIntArray2.put(60, 66);
        sparseIntArray2.put(61, 67);
        sparseIntArray2.put(62, 68);
        sparseIntArray2.put(63, 69);
        sparseIntArray2.put(75, 100);
        sparseIntArray2.put(76, 101);
        sparseIntArray2.put(77, 102);
        sparseIntArray2.put(78, 103);
        sparseIntArray2.put(79, 104);
        sparseIntArray2.put(80, 162);
        sparseIntArray2.put(81, 163);
        sparseIntArray2.put(82, 161);
        sparseIntArray2.put(100, 140);
        sparseIntArray2.put(101, 134);
        sparseIntArray2.put(102, 137);
        sparseIntArray2.put(103, 139);
        sparseIntArray2.put(104, 136);
        sparseIntArray2.put(105, 124);
        sparseIntArray2.put(106, 138);
        sparseIntArray2.put(107, 141);
        sparseIntArray2.put(108, 135);
        sparseIntArray2.put(109, 31);
        sparseIntArray2.put(110, 30);
        sparseIntArray2.put(111, 38);
        sparseIntArray2.put(112, 22);
        sparseIntArray2.put(113, 36);
        sparseIntArray2.put(114, 35);
        sparseIntArray2.put(115, 27);
        sparseIntArray2.put(116, 29);
        sparseIntArray2.put(117, 25);
        sparseIntArray2.put(118, 24);
        sparseIntArray2.put(119, 28);
        sparseIntArray2.put(120, 32);
        sparseIntArray2.put(121, 19);
        sparseIntArray2.put(122, 17);
        sparseIntArray2.put(123, 20);
        sparseIntArray2.put(124, 37);
        sparseIntArray2.put(125, 121);
        sparseIntArray2.put(126, 34);
        sparseIntArray2.put(127, 49);
        sparseIntArray2.put(128, 133);
        sparseIntArray2.put(129, 128);
        sparseIntArray2.put(130, 125);
        sparseIntArray2.put(131, 123);
        sparseIntArray2.put(132, 127);
        sparseIntArray2.put(133, 131);
        sparseIntArray2.put(134, 132);
        sparseIntArray2.put(135, 126);
        sparseIntArray2.put(136, 129);
        sparseIntArray2.put(137, 130);
        sparseIntArray2.put(138, 2);
        sparseIntArray2.put(139, 13);
        sparseIntArray2.put(140, 15);
        sparseIntArray2.put(141, 16);
        sparseIntArray2.put(142, 12);
        sparseIntArray2.put(143, 9);
        sparseIntArray2.put(144, 142);
        sparseIntArray2.put(145, 143);
        sparseIntArray2.put(146, 144);
        sparseIntArray2.put(147, 145);
        sparseIntArray2.put(148, 146);
        sparseIntArray2.put(149, 147);
        sparseIntArray2.put(150, 148);
        sparseIntArray2.put(151, 149);
        sparseIntArray2.put(310, 150);
        sparseIntArray2.put(311, 151);
        sparseIntArray2.put(312, 152);
        sparseIntArray2.put(313, 153);
        sparseIntArray2.put(152, 105);
        sparseIntArray2.put(153, 106);
        sparseIntArray2.put(154, 107);
        sparseIntArray2.put(156, 109);
        sparseIntArray2.put(165, 118);
        sparseIntArray2.put(166, 108);
        sparseIntArray2.put(167, 122);
        sparseIntArray2.put(270, 171);
        sparseIntArray2.put(271, 172);
        sparseIntArray2.put(272, 173);
        sparseIntArray2.put(273, 174);
        sparseIntArray2.put(274, 175);
        sparseIntArray2.put(275, 176);
        sparseIntArray2.put(276, 177);
        sparseIntArray2.put(277, 178);
        sparseIntArray2.put(278, 179);
        sparseIntArray2.put(279, 180);
        sparseIntArray2.put(280, 181);
        sparseIntArray2.put(282, 183);
        sparseIntArray2.put(283, 184);
        sparseIntArray2.put(284, 185);
        sparseIntArray2.put(285, 186);
        sparseIntArray2.put(286, 187);
        sparseIntArray2.put(287, 188);
        sparseIntArray2.put(308, 308);
        sparseIntArray2.put(307, 231);
        sparseIntArray2.put(180, 201);
        sparseIntArray2.put(181, 202);
        sparseIntArray2.put(182, 203);
        sparseIntArray2.put(183, 204);
        sparseIntArray2.put(184, 205);
        sparseIntArray2.put(185, 206);
        sparseIntArray2.put(186, 207);
        sparseIntArray2.put(187, 208);
        sparseIntArray2.put(188, 209);
        sparseIntArray2.put(189, 210);
        sparseIntArray2.put(190, 211);
        sparseIntArray2.put(191, 212);
        sparseIntArray2.put(192, 213);
        sparseIntArray2.put(193, 214);
        sparseIntArray2.put(194, 215);
        sparseIntArray2.put(195, 216);
        sparseIntArray2.put(196, 217);
        sparseIntArray2.put(197, 218);
        sparseIntArray2.put(198, 219);
        sparseIntArray2.put(199, 220);
        sparseIntArray2.put(200, 221);
        sparseIntArray2.put(201, 222);
        sparseIntArray2.put(202, 223);
        sparseIntArray2.put(203, 224);
        sparseIntArray2.put(204, 225);
        sparseIntArray2.put(205, 226);
        sparseIntArray2.put(206, 227);
        sparseIntArray2.put(207, 228);
        sparseIntArray2.put(208, 229);
        sparseIntArray2.put(209, 232);
        sparseIntArray2.put(210, 233);
        sparseIntArray2.put(211, 234);
        sparseIntArray2.put(212, 235);
        sparseIntArray2.put(213, 236);
        sparseIntArray2.put(214, 237);
        sparseIntArray2.put(215, 238);
        sparseIntArray2.put(216, 239);
        sparseIntArray2.put(217, 240);
        sparseIntArray2.put(218, 241);
        sparseIntArray2.put(219, 242);
        sparseIntArray2.put(220, 243);
        sparseIntArray2.put(221, 244);
        sparseIntArray2.put(222, 245);
        sparseIntArray2.put(223, 246);
        sparseIntArray2.put(224, 247);
        sparseIntArray2.put(225, 248);
        sparseIntArray2.put(226, 249);
        sparseIntArray2.put(227, 250);
        sparseIntArray2.put(228, 251);
        sparseIntArray2.put(229, 252);
        sparseIntArray2.put(230, 253);
        sparseIntArray2.put(231, 254);
        sparseIntArray2.put(232, 255);
        sparseIntArray2.put(233, 256);
        sparseIntArray2.put(234, 257);
        sparseIntArray2.put(235, 258);
        sparseIntArray2.put(236, 259);
        sparseIntArray2.put(237, 260);
        sparseIntArray2.put(238, 262);
        sparseIntArray2.put(239, 263);
        sparseIntArray2.put(240, 264);
        sparseIntArray2.put(241, 265);
        sparseIntArray2.put(242, 266);
        sparseIntArray2.put(243, 267);
        sparseIntArray2.put(244, 268);
        sparseIntArray2.put(245, 269);
        sparseIntArray2.put(246, 270);
        sparseIntArray2.put(247, 271);
        sparseIntArray2.put(248, 272);
        sparseIntArray2.put(249, 273);
        sparseIntArray2.put(250, 274);
        sparseIntArray2.put(251, 275);
        sparseIntArray2.put(252, 276);
        sparseIntArray2.put(253, 277);
        sparseIntArray2.put(254, 278);
        sparseIntArray2.put(255, 279);
        sparseIntArray2.put(256, 280);
        sparseIntArray2.put(268, 292);
        sparseIntArray2.put(269, 293);
        sparseIntArray2.put(350, 119);
        sparseIntArray2.put(320, 77);
        sparseIntArray2.put(321, 78);
        sparseIntArray2.put(322, 76);
        sparseIntArray2.put(323, 73);
        sparseIntArray2.put(324, 75);
        sparseIntArray2.put(325, 70);
        sparseIntArray2.put(326, 74);
        sparseIntArray2.put(327, 72);
        sparseIntArray2.put(328, 79);
        sparseIntArray2.put(329, 71);
        sparseIntArray2.put(330, 88);
        sparseIntArray2.put(331, 81);
        sparseIntArray2.put(332, 83);
        sparseIntArray2.put(333, 85);
        sparseIntArray2.put(334, 89);
        sparseIntArray2.put(335, 87);
        sparseIntArray2.put(336, 86);
        sparseIntArray2.put(337, 80);
        sparseIntArray2.put(338, 82);
        sparseIntArray2.put(339, 84);
        sparseIntArray2.put(341, 94);
        sparseIntArray2.put(342, 95);
        sparseIntArray2.put(343, 96);
        sparseIntArray2.put(344, 97);
        sparseIntArray2.put(345, 98);
        sparseIntArray2.put(346, 99);
        sparseIntArray2.put(314, 170);
        sparseIntArray2.put(351, 90);
        sparseIntArray2.put(352, 91);
        sparseIntArray2.put(360, 296);
        sparseIntArray2.put(361, 297);
        sparseIntArray2.put(363, 298);
        sparseIntArray2.put(364, 299);
        sparseIntArray2.put(365, 300);
        sparseIntArray2.put(366, 301);
        sparseIntArray2.put(302, 302);
        sparseIntArray2.put(303, 303);
        sparseIntArray2.put(304, 304);
        sparseIntArray2.put(305, 305);
        sparseIntArray2.put(157, 110);
        sparseIntArray2.put(158, 111);
        sparseIntArray2.put(159, 112);
        SparseLongArray sparseLongArray = new SparseLongArray();
        WAVEFORM_INDEX_DURATION = sparseLongArray;
        sparseLongArray.put(1, 35L);
        sparseLongArray.put(2, 45L);
        sparseLongArray.put(3, 110L);
        sparseLongArray.put(4, 80L);
        sparseLongArray.put(5, 110L);
        sparseLongArray.put(6, 45L);
        sparseLongArray.put(7, 30L);
        sparseLongArray.put(110, 16L);
        sparseLongArray.put(111, 16L);
        sparseLongArray.put(112, 16L);
        ArrayMap arrayMap = new ArrayMap();
        RINGTONE_FILENAME_TITLE = arrayMap;
        arrayMap.put("ringtone_000", "ringtone_000");
        arrayMap.put("ringtone_0001", "ringtone_0001");
        arrayMap.put("ringtone_001", "Calm");
        arrayMap.put("ringtone_002", "Nostalgic");
        arrayMap.put("ringtone_003", "Romance");
        arrayMap.put("ringtone_004", "Dream");
        arrayMap.put("ringtone_005", "Visions");
        arrayMap.put("ringtone_006", "Longing");
        arrayMap.put("ringtone_007", "Bliss");
        arrayMap.put("ringtone_008", "Delight");
        arrayMap.put("ringtone_009", "Classic");
        arrayMap.put("ringtone_010", "Playpark");
        arrayMap.put("ringtone_011", "Dance");
        arrayMap.put("ringtone_012", "Trinkets");
        arrayMap.put("ringtone_013", "Firefly");
        arrayMap.put("ringtone_014", "Stars");
        arrayMap.put("ringtone_015", "Silence");
        arrayMap.put("ringtone_016", "Memories");
        arrayMap.put("ringtone_017", "Night");
        arrayMap.put("ringtone_018", "Lakeside");
        arrayMap.put("ringtone_019", "Gazingout");
        arrayMap.put("ringtone_020", "Dazzle");
        arrayMap.put("ringtone_021", "Relax");
        arrayMap.put("ringtone_022", "Childhood");
        arrayMap.put("ringtone_023", "School");
        arrayMap.put("ringtone_024", "Commute");
        arrayMap.put("ringtone_025", "Summer");
        arrayMap.put("ringtone_026", "Fairviews");
        arrayMap.put("ringtone_027", "Solitude");
        arrayMap.put("ringtone_028", "Pure");
        arrayMap.put("ringtone_wind", "Wind");
        arrayMap.put("ringtone_cloud", "Cloudy");
        arrayMap.put("ringtone_thunderstorm", "ThunderStorm");
        arrayMap.put("ringtone_weather_default", "WeatherDefault");
        arrayMap.put("ringtone_sun", "Sunny");
        arrayMap.put("ringtone_haze", "Haze");
        arrayMap.put("ringtone_snow", "Snow");
        arrayMap.put("ringtone_rain", "Rain");
        arrayMap.put("ringtone_master", "Master Ringtone");
        arrayMap.put("notification_master", "Master Notification");
        arrayMap.put("sms_master", "Master Text");
        arrayMap.put("alarm_master", "Master Alarm");
        arrayMap.put("notification_000", "notification_000");
        arrayMap.put("notification_001", "Simple");
        arrayMap.put("notification_002", "CrystalClear");
        arrayMap.put("notification_003", "Tunes");
        arrayMap.put("notification_004", "Emerge");
        arrayMap.put("notification_005", "Ripples");
        arrayMap.put("notification_006", "Harp");
        arrayMap.put("notification_007", "Overtone");
        arrayMap.put("notification_008", "Percussion");
        arrayMap.put("notification_009", "Joy");
        arrayMap.put("notification_010", "Twinkle");
        arrayMap.put("notification_011", "Instant");
        arrayMap.put("notification_012", "Fun");
        arrayMap.put("notification_013", "Receive");
        arrayMap.put("notification_014", "Splash");
        arrayMap.put("notification_015", "Ingenious");
        arrayMap.put("notification_016", "Granules");
        arrayMap.put("T-Jingle", "t_jingle_ringtone");
        arrayMap.put("Alacrity", "alacrity_ringtone");
        arrayMap.put("Amenity", "amenity_ringtone");
        arrayMap.put("Blues", "blues_ringtone");
        arrayMap.put("Bounce", "bounce_ringtone");
        arrayMap.put("Calm", "calm_ringtone");
        arrayMap.put("Cloud", "cloud_ringtone");
        arrayMap.put("Cyclotron", "cyclotron_ringtone");
        arrayMap.put("Distinct", "distinct_ringtone");
        arrayMap.put("Dynamic", "dynamic_ringtone");
        arrayMap.put("Echo", "echo_ringtone");
        arrayMap.put("Expect", "expect_ringtone");
        arrayMap.put("Fanatical", "fanatical_ringtone");
        arrayMap.put("Funky", "funky_ringtone");
        arrayMap.put("Guitar", "guitar_ringtone");
        arrayMap.put("Harping", "harping_ringtone");
        arrayMap.put("Highlight", "highlight_ringtone");
        arrayMap.put("Idyl", "idyl_ringtone");
        arrayMap.put("Innocence", "innocence_ringtone");
        arrayMap.put("Journey", "journey_ringtone");
        arrayMap.put("Joyous", "joyous_ringtone");
        arrayMap.put("Lazy", "lazy_ringtone");
        arrayMap.put("Marimba", "marimba_ringtone");
        arrayMap.put("Mystical", "mystical_ringtone");
        arrayMap.put("Old_telephone", "old_telephone_ringtone");
        arrayMap.put("OnePlus_tune", "oneplus_tune_ringtone");
        arrayMap.put("OnePlus_tune_rhythm", "oneplus_tune_rhythm_ringtone");
        arrayMap.put("Optimistic", "optimistic_ringtone");
        arrayMap.put("Piano", "piano_ringtone");
        arrayMap.put("Whirl", "whirl_ringtone");
        arrayMap.put("Eager", "eager_ringtone");
        arrayMap.put("Ebullition", "ebullition_ringtone");
        arrayMap.put("Friendship", "friendship_ringtone");
        arrayMap.put("Jazz_life", "jazz_life_ringtone");
        arrayMap.put("Sun_glittering", "sun_glittering_ringtone");
        arrayMap.put("Allay", "allay_notification");
        arrayMap.put("Allusion", "allusion_notification");
        arrayMap.put("Amiable", "amiable_notification");
        arrayMap.put("Blare", "blare_notification");
        arrayMap.put("Blissful", "blissful_notification");
        arrayMap.put("Brisk", "brisk_notification");
        arrayMap.put("Bubble", "bubble_notification");
        arrayMap.put("Cheerful", "cheerful_notification");
        arrayMap.put("Clear", "clear_notification");
        arrayMap.put("Comely", "comely_notification");
        arrayMap.put("Cozy", "cozy_notification");
        arrayMap.put("Ding", "ding_notification");
        arrayMap.put("Effervesce", "effervesce_notification");
        arrayMap.put("Elegant", "elegant_notification");
        arrayMap.put("Free", "free_notification");
        arrayMap.put("Hallucination", "hallucination_notification");
        arrayMap.put("Inbound", "inbound_notification");
        arrayMap.put("Light", "light_notification");
        arrayMap.put("Meet", "meet_notification");
        arrayMap.put("Naivety", "naivety_notification");
        arrayMap.put("Quickly", "quickly_notification");
        arrayMap.put("Rhythm", "rhythm_notification");
        arrayMap.put("Surprise", "surprise_notification");
        arrayMap.put("Twinkle", "twinkle_notification");
        arrayMap.put("Alarm_clock", "alarm_clock_alarm");
        arrayMap.put("Beep", "beep_alarm");
        arrayMap.put("Breeze", "breeze_alarm");
        arrayMap.put("Dawn", "dawn_alarm");
        arrayMap.put("Dream", "dream_alarm");
        arrayMap.put("Fluttering", "fluttering_alarm");
        arrayMap.put("Flyer", "flyer_alarm");
        arrayMap.put("Interesting", "interesting_alarm");
        arrayMap.put("Leisurely", "leisurely_alarm");
        arrayMap.put("Memory", "memory_alarm");
        arrayMap.put("Relieved", "relieved_alarm");
        arrayMap.put("Ripple", "ripple_alarm");
        arrayMap.put("Slowly", "slowly_alarm");
        arrayMap.put("Spring", "spring_alarm");
        arrayMap.put("Stars", "stars_alarm");
        arrayMap.put("Surging", "surging_alarm");
        arrayMap.put("Tactfully", "tactfully_alarm");
        arrayMap.put("The_wind", "the_wind_alarm");
        arrayMap.put("Chase", "chase_ringtone");
        arrayMap.put("Climber", "climber_ringtone");
        arrayMap.put("Walking_in_the_rain", "walking_in_the_rain_alarm");
        arrayMap.put("Ringtone_TelcelRíe", "Telcel_Rie");
        arrayMap.put("ringtone_029", "temple_sound");
        arrayMap.put("ringtone_030", "dream_jazz");
        arrayMap.put("ringtone_031", "house_music");
        arrayMap.put("ringtone_032", "e_club");
        arrayMap.put("ringtone_033", "funk");
        arrayMap.put("ringtone_034", "fresh_morning");
        arrayMap.put("ringtone_035", "holiday");
        arrayMap.put("ringtone_036", "water_cicade");
        arrayMap.put("ringtone_037", "modern");
        arrayMap.put("ringtone_038", "eastern_tranquility");
        arrayMap.put("notification_017", "bowl_bell");
        arrayMap.put("notification_018", "rise");
        arrayMap.put("notification_019", "hey");
        arrayMap.put("notification_020", "news");
        arrayMap.put("notification_021", "beating");
        arrayMap.put("notification_022", "short");
        arrayMap.put("notification_023", "top");
        arrayMap.put("notification_024", "circle");
        arrayMap.put("notification_025", "block");
        arrayMap.put("notification_026", "zanza");
        arrayMap.put("Cloudscape", "Cloudscape");
        arrayMap.put("Good_energy", "Goodenergy");
        arrayMap.put("Blink", "Blink");
        arrayMap.put("Whoop_doop", "Whoopdoop");
        arrayMap.put("OnePlus_new_feeling", "Feeling");
        arrayMap.put("Nature", "Nature");
        arrayMap.put("canyon_call", "canyon_call");
        arrayMap.put("mystic_store", "mystic_store");
        arrayMap.put("rock", "rock");
        arrayMap.put("in_game_ringtone", "in_game_ringtone");
        arrayMap.put("wake_up_samurai", "wake_up_samurai");
        arrayMap.put("in_game_alarm", "in_game_alarm");
        arrayMap.put("in_game_sms", "in_game_sms");
        arrayMap.put("audition", "audition");
        ArrayMap arrayMap2 = new ArrayMap();
        RINGTONE_TITLE_EFFECTS = arrayMap2;
        arrayMap2.put("Calm", 100);
        arrayMap2.put("Nostalgic", 101);
        arrayMap2.put("Romance", 102);
        arrayMap2.put("Dream", 103);
        arrayMap2.put("Visions", 104);
        arrayMap2.put("Longing", 105);
        arrayMap2.put("Bliss", 106);
        arrayMap2.put("Delight", 107);
        arrayMap2.put("Classic", 108);
        arrayMap2.put("Playpark", 109);
        arrayMap2.put("Dance", 110);
        arrayMap2.put("Trinkets", 111);
        arrayMap2.put("Firefly", 112);
        arrayMap2.put("Stars", 113);
        arrayMap2.put("Silence", 114);
        arrayMap2.put("Memories", 115);
        arrayMap2.put("Night", 116);
        arrayMap2.put("Lakeside", 117);
        arrayMap2.put("Gazingout", 118);
        arrayMap2.put("Dazzle", 119);
        arrayMap2.put("Relax", 120);
        arrayMap2.put("Childhood", 121);
        arrayMap2.put("School", 122);
        arrayMap2.put("Commute", 123);
        arrayMap2.put("Summer", 124);
        arrayMap2.put("Fairviews", 125);
        arrayMap2.put("Solitude", 126);
        arrayMap2.put("Pure", 127);
        arrayMap2.put("Simple", 128);
        arrayMap2.put("CrystalClear", 129);
        arrayMap2.put("Tunes", 130);
        arrayMap2.put("Emerge", 131);
        arrayMap2.put("Ripples", 132);
        arrayMap2.put("Harp", 133);
        arrayMap2.put("Overtone", 134);
        arrayMap2.put("Percussion", 135);
        arrayMap2.put("Joy", 136);
        arrayMap2.put("Twinkle", 137);
        arrayMap2.put("Instant", 138);
        arrayMap2.put("Fun", 139);
        arrayMap2.put("Receive", 140);
        arrayMap2.put("Splash", 141);
        arrayMap2.put("Ingenious", 142);
        arrayMap2.put("Granules", 143);
        arrayMap2.put("Wind", 144);
        arrayMap2.put("Cloudy", 145);
        arrayMap2.put("ThunderStorm", 146);
        arrayMap2.put("WeatherDefault", 147);
        arrayMap2.put("Sunny", 148);
        arrayMap2.put("Haze", 149);
        arrayMap2.put("Snow", 150);
        arrayMap2.put("Rain", 151);
        arrayMap2.put("ringtone_0001", 80);
        arrayMap2.put("notification_000", 81);
        arrayMap2.put("ringtone_000", 82);
        arrayMap2.put("realme Tune", 80);
        arrayMap2.put("realme Jingle", 81);
        arrayMap2.put("it's realme", 82);
        arrayMap2.put("Master Ringtone", 311);
        arrayMap2.put("Master Notification", 310);
        arrayMap2.put("Master Text", 312);
        arrayMap2.put("Master Alarm", 313);
        arrayMap2.put("t_jingle_ringtone", 307);
        arrayMap2.put("alacrity_ringtone", 180);
        arrayMap2.put("amenity_ringtone", 181);
        arrayMap2.put("blues_ringtone", 182);
        arrayMap2.put("bounce_ringtone", 183);
        arrayMap2.put("calm_ringtone", 184);
        arrayMap2.put("cloud_ringtone", 185);
        arrayMap2.put("cyclotron_ringtone", 186);
        arrayMap2.put("distinct_ringtone", 187);
        arrayMap2.put("dynamic_ringtone", 188);
        arrayMap2.put("echo_ringtone", 189);
        arrayMap2.put("expect_ringtone", 190);
        arrayMap2.put("fanatical_ringtone", 191);
        arrayMap2.put("funky_ringtone", 192);
        arrayMap2.put("guitar_ringtone", 193);
        arrayMap2.put("harping_ringtone", 194);
        arrayMap2.put("highlight_ringtone", 195);
        arrayMap2.put("idyl_ringtone", 196);
        arrayMap2.put("innocence_ringtone", 197);
        arrayMap2.put("journey_ringtone", 198);
        arrayMap2.put("joyous_ringtone", 199);
        arrayMap2.put("lazy_ringtone", 200);
        arrayMap2.put("marimba_ringtone", 201);
        arrayMap2.put("mystical_ringtone", 202);
        arrayMap2.put("old_telephone_ringtone", 203);
        arrayMap2.put("oneplus_tune_ringtone", 204);
        arrayMap2.put("oneplus_tune_rhythm_ringtone", 205);
        arrayMap2.put("optimistic_ringtone", 206);
        arrayMap2.put("piano_ringtone", 207);
        arrayMap2.put("whirl_ringtone", 208);
        arrayMap2.put("eager_ringtone", 209);
        arrayMap2.put("ebullition_ringtone", 210);
        arrayMap2.put("friendship_ringtone", 211);
        arrayMap2.put("jazz_life_ringtone", 212);
        arrayMap2.put("sun_glittering_ringtone", 213);
        arrayMap2.put("allay_notification", 214);
        arrayMap2.put("allusion_notification", 215);
        arrayMap2.put("amiable_notification", 216);
        arrayMap2.put("blare_notification", 217);
        arrayMap2.put("blissful_notification", 218);
        arrayMap2.put("brisk_notification", 219);
        arrayMap2.put("bubble_notification", 220);
        arrayMap2.put("cheerful_notification", 221);
        arrayMap2.put("clear_notification", 222);
        arrayMap2.put("comely_notification", 223);
        arrayMap2.put("cozy_notification", 224);
        arrayMap2.put("ding_notification", 225);
        arrayMap2.put("effervesce_notification", 226);
        arrayMap2.put("elegant_notification", 227);
        arrayMap2.put("free_notification", 228);
        arrayMap2.put("hallucination_notification", 229);
        arrayMap2.put("inbound_notification", 230);
        arrayMap2.put("light_notification", 231);
        arrayMap2.put("meet_notification", 232);
        arrayMap2.put("naivety_notification", 233);
        arrayMap2.put("quickly_notification", 234);
        arrayMap2.put("rhythm_notification", 235);
        arrayMap2.put("surprise_notification", 236);
        arrayMap2.put("twinkle_notification", 237);
        arrayMap2.put("alarm_clock_alarm", 238);
        arrayMap2.put("beep_alarm", 239);
        arrayMap2.put("breeze_alarm", 240);
        arrayMap2.put("dawn_alarm", 241);
        arrayMap2.put("dream_alarm", 242);
        arrayMap2.put("fluttering_alarm", 243);
        arrayMap2.put("flyer_alarm", 244);
        arrayMap2.put("interesting_alarm", 245);
        arrayMap2.put("leisurely_alarm", 246);
        arrayMap2.put("memory_alarm", 247);
        arrayMap2.put("relieved_alarm", 248);
        arrayMap2.put("ripple_alarm", 249);
        arrayMap2.put("slowly_alarm", 250);
        arrayMap2.put("spring_alarm", 251);
        arrayMap2.put("stars_alarm", 252);
        arrayMap2.put("surging_alarm", 253);
        arrayMap2.put("tactfully_alarm", 254);
        arrayMap2.put("the_wind_alarm", 255);
        arrayMap2.put("climber_ringtone", 268);
        arrayMap2.put("chase_ringtone", 269);
        arrayMap2.put("walking_in_the_rain_alarm", 256);
        arrayMap2.put("Telcel_Rie", 350);
        arrayMap2.put("temple_sound", 320);
        arrayMap2.put("dream_jazz", 321);
        arrayMap2.put("house_music", 322);
        arrayMap2.put("e_club", 323);
        arrayMap2.put("funk", 324);
        arrayMap2.put("fresh_morning", 325);
        arrayMap2.put("holiday", 326);
        arrayMap2.put("water_cicade", 327);
        arrayMap2.put("modern", 328);
        arrayMap2.put("eastern_tranquility", 329);
        arrayMap2.put("bowl_bell", 330);
        arrayMap2.put("rise", 331);
        arrayMap2.put("hey", 332);
        arrayMap2.put("news", 333);
        arrayMap2.put("beating", 334);
        arrayMap2.put("short", 335);
        arrayMap2.put("top", 336);
        arrayMap2.put("circle", 337);
        arrayMap2.put("block", 338);
        arrayMap2.put("zanza", 339);
        arrayMap2.put("Cloudscape", 341);
        arrayMap2.put("Goodenergy", 342);
        arrayMap2.put("Blink", 343);
        arrayMap2.put("Whoopdoop", 344);
        arrayMap2.put("Feeling", 345);
        arrayMap2.put("Nature", 346);
        arrayMap2.put("canyon_call", 351);
        arrayMap2.put("mystic_store", 352);
        arrayMap2.put("rock", 360);
        arrayMap2.put("in_game_ringtone", 361);
        arrayMap2.put("wake_up_samurai", 363);
        arrayMap2.put("in_game_alarm", 364);
        arrayMap2.put("in_game_sms", 365);
        arrayMap2.put("audition", 366);
        SparseLongArray sparseLongArray2 = new SparseLongArray();
        WAVEFORM_EFFECT_DURATION = sparseLongArray2;
        sparseLongArray2.put(0, 35L);
        sparseLongArray2.put(1, 45L);
        sparseLongArray2.put(2, 110L);
        sparseLongArray2.put(49, 80L);
        sparseLongArray2.put(53, 110L);
        sparseLongArray2.put(69, 45L);
        sparseLongArray2.put(68, 30L);
        sparseLongArray2.put(4, 2729L);
        sparseLongArray2.put(5, 2081L);
        sparseLongArray2.put(6, 3968L);
        sparseLongArray2.put(7, 6000L);
        sparseLongArray2.put(8, 4516L);
        sparseLongArray2.put(3, 480L);
        sparseLongArray2.put(50, 350L);
        sparseLongArray2.put(73, 200L);
        sparseLongArray2.put(74, 620L);
        sparseLongArray2.put(70, 350L);
        sparseLongArray2.put(51, 100L);
        sparseLongArray2.put(52, 60L);
        sparseLongArray2.put(71, 400L);
        sparseLongArray2.put(72, 1000L);
        sparseLongArray2.put(54, 220L);
        sparseLongArray2.put(55, 210L);
        sparseLongArray2.put(56, 410L);
        sparseLongArray2.put(57, 430L);
        sparseLongArray2.put(58, 790L);
        sparseLongArray2.put(59, 520L);
        sparseLongArray2.put(60, 470L);
        sparseLongArray2.put(61, 590L);
        sparseLongArray2.put(62, 630L);
        sparseLongArray2.put(63, 1020L);
        sparseLongArray2.put(75, 230L);
        sparseLongArray2.put(76, 290L);
        sparseLongArray2.put(77, 430L);
        sparseLongArray2.put(78, 610L);
        sparseLongArray2.put(79, 750L);
        sparseLongArray2.put(82, 26157L);
        sparseLongArray2.put(80, 21551L);
        sparseLongArray2.put(81, 1731L);
        sparseLongArray2.put(100, 31014L);
        sparseLongArray2.put(101, 26328L);
        sparseLongArray2.put(102, 26098L);
        sparseLongArray2.put(103, 20018L);
        sparseLongArray2.put(104, 28260L);
        sparseLongArray2.put(105, 14015L);
        sparseLongArray2.put(106, 26909L);
        sparseLongArray2.put(107, 20875L);
        sparseLongArray2.put(108, 1975L);
        sparseLongArray2.put(109, 17214L);
        sparseLongArray2.put(110, 18000L);
        sparseLongArray2.put(111, 18925L);
        sparseLongArray2.put(112, 11954L);
        sparseLongArray2.put(113, 21600L);
        sparseLongArray2.put(114, 26666L);
        sparseLongArray2.put(115, 22169L);
        sparseLongArray2.put(116, 9546L);
        sparseLongArray2.put(117, 16172L);
        sparseLongArray2.put(118, 12675L);
        sparseLongArray2.put(119, 17650L);
        sparseLongArray2.put(120, 8202L);
        sparseLongArray2.put(121, 18866L);
        sparseLongArray2.put(122, 10575L);
        sparseLongArray2.put(123, 26942L);
        sparseLongArray2.put(124, 15301L);
        sparseLongArray2.put(125, 32571L);
        sparseLongArray2.put(126, 27712L);
        sparseLongArray2.put(127, 10445L);
        sparseLongArray2.put(128, 1140L);
        sparseLongArray2.put(129, 382L);
        sparseLongArray2.put(130, 1036L);
        sparseLongArray2.put(131, 822L);
        sparseLongArray2.put(132, 879L);
        sparseLongArray2.put(133, 1928L);
        sparseLongArray2.put(134, 830L);
        sparseLongArray2.put(135, 941L);
        sparseLongArray2.put(136, 866L);
        sparseLongArray2.put(137, 1947L);
        sparseLongArray2.put(138, 1044L);
        sparseLongArray2.put(139, 1876L);
        sparseLongArray2.put(140, 3958L);
        sparseLongArray2.put(141, 2515L);
        sparseLongArray2.put(142, 877L);
        sparseLongArray2.put(143, 2387L);
        sparseLongArray2.put(144, 19226L);
        sparseLongArray2.put(145, 21036L);
        sparseLongArray2.put(146, 15967L);
        sparseLongArray2.put(147, 30336L);
        sparseLongArray2.put(148, 21434L);
        sparseLongArray2.put(149, 16759L);
        sparseLongArray2.put(150, 18201L);
        sparseLongArray2.put(151, 16533L);
        sparseLongArray2.put(310, 2733L);
        sparseLongArray2.put(311, 20695L);
        sparseLongArray2.put(312, 4767L);
        sparseLongArray2.put(313, 15686L);
        sparseLongArray2.put(152, 66L);
        sparseLongArray2.put(153, 297L);
        sparseLongArray2.put(154, 146L);
        sparseLongArray2.put(9, 30L);
        sparseLongArray2.put(10, 15L);
        sparseLongArray2.put(156, 709L);
        sparseLongArray2.put(165, 3187L);
        sparseLongArray2.put(166, 935L);
        sparseLongArray2.put(167, 151L);
        sparseLongArray2.put(270, 16L);
        sparseLongArray2.put(271, 16L);
        sparseLongArray2.put(272, 16L);
        sparseLongArray2.put(273, 16L);
        sparseLongArray2.put(274, 16L);
        sparseLongArray2.put(275, 35L);
        sparseLongArray2.put(276, 16L);
        sparseLongArray2.put(277, 16L);
        sparseLongArray2.put(278, 16L);
        sparseLongArray2.put(279, 16L);
        sparseLongArray2.put(280, 6699L);
        sparseLongArray2.put(281, 16L);
        sparseLongArray2.put(282, 100L);
        sparseLongArray2.put(283, 16L);
        sparseLongArray2.put(284, 16L);
        sparseLongArray2.put(285, 121L);
        sparseLongArray2.put(286, 121L);
        sparseLongArray2.put(287, 6699L);
        sparseLongArray2.put(11, 40L);
        sparseLongArray2.put(12, 40L);
        sparseLongArray2.put(13, 50L);
        sparseLongArray2.put(308, 260L);
        sparseLongArray2.put(307, 2112L);
        sparseLongArray2.put(180, 13680L);
        sparseLongArray2.put(181, 15602L);
        sparseLongArray2.put(182, 18563L);
        sparseLongArray2.put(183, 10769L);
        sparseLongArray2.put(184, 6140L);
        sparseLongArray2.put(185, 12505L);
        sparseLongArray2.put(186, 16388L);
        sparseLongArray2.put(187, 6466L);
        sparseLongArray2.put(188, 19166L);
        sparseLongArray2.put(189, 15501L);
        sparseLongArray2.put(190, 7490L);
        sparseLongArray2.put(191, 23926L);
        sparseLongArray2.put(192, 8897L);
        sparseLongArray2.put(193, 16096L);
        sparseLongArray2.put(194, 13344L);
        sparseLongArray2.put(195, 6872L);
        sparseLongArray2.put(196, 16848L);
        sparseLongArray2.put(197, 20830L);
        sparseLongArray2.put(198, 24926L);
        sparseLongArray2.put(199, 5844L);
        sparseLongArray2.put(200, 18341L);
        sparseLongArray2.put(201, 8234L);
        sparseLongArray2.put(202, 18040L);
        sparseLongArray2.put(203, 6324L);
        sparseLongArray2.put(204, 14774L);
        sparseLongArray2.put(205, 17807L);
        sparseLongArray2.put(206, 19962L);
        sparseLongArray2.put(207, 22222L);
        sparseLongArray2.put(208, 11048L);
        sparseLongArray2.put(209, 24614L);
        sparseLongArray2.put(210, 16246L);
        sparseLongArray2.put(211, 23824L);
        sparseLongArray2.put(212, 20556L);
        sparseLongArray2.put(213, 18391L);
        sparseLongArray2.put(214, 451L);
        sparseLongArray2.put(215, 960L);
        sparseLongArray2.put(216, 1622L);
        sparseLongArray2.put(217, 1388L);
        sparseLongArray2.put(218, 2099L);
        sparseLongArray2.put(219, 570L);
        sparseLongArray2.put(220, 127L);
        sparseLongArray2.put(221, 544L);
        sparseLongArray2.put(222, 457L);
        sparseLongArray2.put(223, 3024L);
        sparseLongArray2.put(224, 2303L);
        sparseLongArray2.put(225, 1697L);
        sparseLongArray2.put(226, 1506L);
        sparseLongArray2.put(227, 1893L);
        sparseLongArray2.put(228, 1416L);
        sparseLongArray2.put(229, 1401L);
        sparseLongArray2.put(230, 1583L);
        sparseLongArray2.put(231, 1100L);
        sparseLongArray2.put(232, 871L);
        sparseLongArray2.put(233, 1878L);
        sparseLongArray2.put(234, 612L);
        sparseLongArray2.put(235, 842L);
        sparseLongArray2.put(236, 1067L);
        sparseLongArray2.put(237, 2206L);
        sparseLongArray2.put(238, 2115L);
        sparseLongArray2.put(239, 2373L);
        sparseLongArray2.put(240, 32511L);
        sparseLongArray2.put(241, 35086L);
        sparseLongArray2.put(242, 38500L);
        sparseLongArray2.put(243, 22410L);
        sparseLongArray2.put(244, 20108L);
        sparseLongArray2.put(245, 10584L);
        sparseLongArray2.put(246, 18442L);
        sparseLongArray2.put(247, 27611L);
        sparseLongArray2.put(248, 26404L);
        sparseLongArray2.put(249, 7590L);
        sparseLongArray2.put(250, 50077L);
        sparseLongArray2.put(251, 24457L);
        sparseLongArray2.put(252, 22488L);
        sparseLongArray2.put(253, 12847L);
        sparseLongArray2.put(254, 20299L);
        sparseLongArray2.put(255, 23264L);
        sparseLongArray2.put(256, 18551L);
        sparseLongArray2.put(268, 52151L);
        sparseLongArray2.put(269, 52612L);
        sparseLongArray2.put(350, 58853L);
        sparseLongArray2.put(288, 14L);
        sparseLongArray2.put(289, 14L);
        sparseLongArray2.put(320, 28818L);
        sparseLongArray2.put(321, 21600L);
        sparseLongArray2.put(322, 19181L);
        sparseLongArray2.put(323, 18911L);
        sparseLongArray2.put(324, 18778L);
        sparseLongArray2.put(325, 23810L);
        sparseLongArray2.put(326, 24970L);
        sparseLongArray2.put(327, 17751L);
        sparseLongArray2.put(328, 24040L);
        sparseLongArray2.put(329, 23091L);
        sparseLongArray2.put(330, 4115L);
        sparseLongArray2.put(331, 3044L);
        sparseLongArray2.put(332, 3940L);
        sparseLongArray2.put(333, 3082L);
        sparseLongArray2.put(334, 3007L);
        sparseLongArray2.put(335, 2155L);
        sparseLongArray2.put(336, 2412L);
        sparseLongArray2.put(337, 3648L);
        sparseLongArray2.put(338, 436L);
        sparseLongArray2.put(339, 1460L);
        sparseLongArray2.put(341, 29278L);
        sparseLongArray2.put(342, 27804L);
        sparseLongArray2.put(343, 331L);
        sparseLongArray2.put(344, 386L);
        sparseLongArray2.put(345, 19868L);
        sparseLongArray2.put(346, 17963L);
        sparseLongArray2.put(314, 1080L);
        sparseLongArray2.put(351, 16417L);
        sparseLongArray2.put(352, 2755L);
        sparseLongArray2.put(360, 37155L);
        sparseLongArray2.put(361, 15370L);
        sparseLongArray2.put(363, 44094L);
        sparseLongArray2.put(364, 21968L);
        sparseLongArray2.put(365, 300L);
        sparseLongArray2.put(366, 301L);
        sparseLongArray2.put(302, 35L);
        sparseLongArray2.put(303, 11L);
        sparseLongArray2.put(304, 35L);
        sparseLongArray2.put(305, 11L);
        sparseLongArray2.put(157, 16L);
        sparseLongArray2.put(158, 16L);
        sparseLongArray2.put(159, 16L);
    }

    private WaveformEffect() {
        this.mEffectType = -1;
        this.mEffectStrength = 1;
        this.mEffectLoop = false;
        this.mRingtoneVibrateType = -1;
        this.mIsRingtoneCustomized = false;
        this.mRingtoneFilePath = "";
        this.mStrengthSettingEnabled = true;
        this.mAsynchronous = false;
        this.mUsageHint = 0;
    }

    public int getEffectType() {
        return this.mEffectType;
    }

    public int[] getWaveFormIndexArray() {
        int waveFormindex;
        SparseIntArray sparseIntArray = WAVEFORMINDEX_EFFECTS;
        if (sparseIntArray.indexOfKey(this.mEffectType) >= 0) {
            waveFormindex = sparseIntArray.get(this.mEffectType);
        } else {
            SparseIntArray sparseIntArray2 = RTPINDEX_EFFECTS;
            if (sparseIntArray2.indexOfKey(this.mEffectType) >= 0) {
                waveFormindex = sparseIntArray2.get(this.mEffectType);
            } else {
                waveFormindex = -1;
            }
        }
        int[] waveFormIndexArray = {waveFormindex};
        return waveFormIndexArray;
    }

    public int[] getWaveFormIndexArray(int effectType) {
        int waveFormindex;
        SparseIntArray sparseIntArray = WAVEFORMINDEX_EFFECTS;
        if (sparseIntArray.indexOfKey(effectType) >= 0) {
            waveFormindex = sparseIntArray.get(effectType);
        } else {
            SparseIntArray sparseIntArray2 = RTPINDEX_EFFECTS;
            if (sparseIntArray2.indexOfKey(effectType) >= 0) {
                waveFormindex = sparseIntArray2.get(effectType);
            } else {
                waveFormindex = -1;
            }
        }
        int[] waveFormIndexArray = {waveFormindex};
        Slog.d(TAG, "getWaveFormDurationArray effectType=" + effectType + " waveFormIndexArray=" + waveFormindex);
        return waveFormIndexArray;
    }

    public static int getWaveFormIndex(int effectType) {
        int waveFormindex;
        SparseIntArray sparseIntArray = WAVEFORMINDEX_EFFECTS;
        if (sparseIntArray.indexOfKey(effectType) >= 0) {
            waveFormindex = sparseIntArray.get(effectType);
        } else {
            SparseIntArray sparseIntArray2 = RTPINDEX_EFFECTS;
            if (sparseIntArray2.indexOfKey(effectType) >= 0) {
                waveFormindex = sparseIntArray2.get(effectType);
            } else {
                waveFormindex = -1;
            }
        }
        Slog.d(TAG, "getWaveFormDurationArray effectType=" + effectType + " waveFormIndexArray=" + waveFormindex);
        return waveFormindex;
    }

    public long[] getWaveFormDurationArray() {
        long duration = 0;
        SparseLongArray sparseLongArray = WAVEFORM_EFFECT_DURATION;
        if (sparseLongArray.indexOfKey(this.mEffectType) >= 0) {
            duration = sparseLongArray.get(this.mEffectType);
        }
        long[] durationArray = {duration};
        Slog.d(TAG, "getWaveFormDurationArray mEffectType=" + this.mEffectType + " duration=" + duration + " indexOfKey=" + sparseLongArray.indexOfKey(this.mEffectType));
        return durationArray;
    }

    public long[] getWaveFormDurationArray(int effectType) {
        long duration = 0;
        SparseLongArray sparseLongArray = WAVEFORM_EFFECT_DURATION;
        if (sparseLongArray.indexOfKey(effectType) >= 0) {
            duration = sparseLongArray.get(effectType);
        }
        Slog.d(TAG, "getWaveFormDurationArray effectType=" + effectType + " duration=" + duration);
        long[] durationArray = {duration};
        return durationArray;
    }

    public static long[] getWaveFormIndexDurationArray(int effectType) {
        long duration = 0;
        SparseLongArray sparseLongArray = WAVEFORM_INDEX_DURATION;
        if (sparseLongArray.indexOfKey(effectType) >= 0) {
            duration = sparseLongArray.get(effectType);
        }
        Slog.d(TAG, "getWaveFormIndexDurationArray effectType=" + effectType + " duration=" + duration);
        long[] durationArray = {duration};
        return durationArray;
    }

    public int getEffectStrength() {
        return this.mEffectStrength;
    }

    public boolean getEffectLoop() {
        return this.mEffectLoop;
    }

    public int getRingtoneVibrateType() {
        return this.mRingtoneVibrateType;
    }

    public boolean getIsRingtoneCustomized() {
        return this.mIsRingtoneCustomized;
    }

    public String getRingtoneFilePath() {
        return this.mRingtoneFilePath;
    }

    public boolean getStrengthSettingEnabled() {
        return this.mStrengthSettingEnabled;
    }

    public boolean getAsynchronous() {
        return this.mAsynchronous;
    }

    public int getUsageHint() {
        return this.mUsageHint;
    }

    public int getWaveFormNodeType() {
        if (this.mRingtoneVibrateType != -1) {
            return -1;
        }
        if (WAVEFORMINDEX_EFFECTS.indexOfKey(this.mEffectType) >= 0) {
            return 1;
        }
        return RTPINDEX_EFFECTS.indexOfKey(this.mEffectType) >= 0 ? 2 : -1;
    }

    public static String getRingtoneTitle(String ringtoneName) {
        ArrayMap arrayMap = RINGTONE_FILENAME_TITLE;
        if (!arrayMap.containsKey(ringtoneName)) {
            return "";
        }
        String ringtoneTitle = (String) arrayMap.get(ringtoneName);
        return ringtoneTitle;
    }

    public static boolean isRtpTouchWaveFormEffect(int type) {
        switch (type) {
            case 152:
            case 153:
            case 154:
            case 156:
            case 157:
            case 158:
            case 159:
            case 165:
            case 166:
            case 167:
            case 270:
            case 271:
            case 272:
            case 273:
            case 274:
            case 275:
            case 276:
            case 277:
            case 278:
            case 279:
            case 280:
            case 281:
            case 282:
            case 283:
            case 284:
            case 285:
            case 286:
            case 287:
            case 302:
            case 303:
            case 304:
            case 305:
                return true;
            default:
                return false;
        }
    }

    public static boolean isRtpIndexTouchWaveFormEffect(int type) {
        switch (type) {
            case 110:
            case 111:
            case 112:
            case 302:
            case 303:
            case 304:
            case 305:
                return true;
            default:
                return false;
        }
    }

    public static int getRingtoneWaveFormEffect(String ringtoneTitle) {
        ArrayMap arrayMap = RINGTONE_TITLE_EFFECTS;
        if (!arrayMap.containsKey(ringtoneTitle)) {
            return -1;
        }
        int ringtoneWaveFormEffect = ((Integer) arrayMap.get(ringtoneTitle)).intValue();
        return ringtoneWaveFormEffect;
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        private boolean mAsynchronous;
        private boolean mEffectLoop;
        private int mEffectStrength;
        private int mEffectType;
        private boolean mIsRingtoneCustomized;
        private String mRingtoneFilePath;
        private int mRingtoneVibrateType;
        private boolean mStrengthSettingEnabled;
        private int mUsageHint;

        public Builder() {
            this.mEffectType = -1;
            this.mEffectStrength = 1;
            this.mEffectLoop = false;
            this.mRingtoneVibrateType = -1;
            this.mIsRingtoneCustomized = false;
            this.mRingtoneFilePath = "";
            this.mStrengthSettingEnabled = true;
            this.mAsynchronous = false;
            this.mUsageHint = 0;
        }

        public Builder(WaveformEffect we) {
            this.mEffectType = -1;
            this.mEffectStrength = 1;
            this.mEffectLoop = false;
            this.mRingtoneVibrateType = -1;
            this.mIsRingtoneCustomized = false;
            this.mRingtoneFilePath = "";
            this.mStrengthSettingEnabled = true;
            this.mAsynchronous = false;
            this.mUsageHint = 0;
            this.mEffectType = we.mEffectType;
        }

        public WaveformEffect build() {
            WaveformEffect we = new WaveformEffect();
            we.mEffectType = this.mEffectType;
            we.mEffectStrength = this.mEffectStrength;
            we.mEffectLoop = this.mEffectLoop;
            we.mRingtoneVibrateType = this.mRingtoneVibrateType;
            we.mIsRingtoneCustomized = this.mIsRingtoneCustomized;
            we.mRingtoneFilePath = this.mRingtoneFilePath;
            we.mStrengthSettingEnabled = this.mStrengthSettingEnabled;
            we.mAsynchronous = this.mAsynchronous;
            we.mUsageHint = this.mUsageHint;
            return we;
        }

        public Builder setEffectType(int type) {
            switch (type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 132:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                case 149:
                case 150:
                case 151:
                case 152:
                case 153:
                case 154:
                case 156:
                case 157:
                case 158:
                case 159:
                case 165:
                case 166:
                case 167:
                case 180:
                case 181:
                case 182:
                case 183:
                case 184:
                case 185:
                case 186:
                case 187:
                case 188:
                case 189:
                case 190:
                case 191:
                case 192:
                case 193:
                case 194:
                case 195:
                case 196:
                case 197:
                case 198:
                case 199:
                case 200:
                case 201:
                case 202:
                case 203:
                case 204:
                case 205:
                case 206:
                case 207:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215:
                case 216:
                case 217:
                case 218:
                case 219:
                case 220:
                case 221:
                case 222:
                case 223:
                case 224:
                case 225:
                case 226:
                case 227:
                case 228:
                case 229:
                case 230:
                case 231:
                case 232:
                case 233:
                case 234:
                case 235:
                case 236:
                case 237:
                case 238:
                case 239:
                case 240:
                case 241:
                case 242:
                case 243:
                case 244:
                case 245:
                case 246:
                case 247:
                case 248:
                case 249:
                case 250:
                case 251:
                case 252:
                case 253:
                case 254:
                case 255:
                case 256:
                case 268:
                case 269:
                case 270:
                case 271:
                case 272:
                case 273:
                case 274:
                case 275:
                case 276:
                case 277:
                case 278:
                case 279:
                case 280:
                case 281:
                case 282:
                case 283:
                case 284:
                case 285:
                case 286:
                case 287:
                case 288:
                case 289:
                case 302:
                case 303:
                case 304:
                case 305:
                case 307:
                case 308:
                case 310:
                case 311:
                case 312:
                case 313:
                case 314:
                case 320:
                case 321:
                case 322:
                case 323:
                case 324:
                case 325:
                case 326:
                case 327:
                case 328:
                case 329:
                case 330:
                case 331:
                case 332:
                case 333:
                case 334:
                case 335:
                case 336:
                case 337:
                case 338:
                case 339:
                case 341:
                case 342:
                case 343:
                case 344:
                case 345:
                case 346:
                case 350:
                case 351:
                case 352:
                case 360:
                case 361:
                case 363:
                case 364:
                case 365:
                case 366:
                    this.mEffectType = type;
                    break;
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 64:
                case 65:
                case 66:
                case 67:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 155:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 168:
                case 169:
                case 170:
                case 171:
                case 172:
                case 173:
                case 174:
                case 175:
                case 176:
                case 177:
                case 178:
                case 179:
                case 257:
                case 258:
                case 259:
                case 260:
                case 261:
                case 262:
                case 263:
                case 264:
                case 265:
                case 266:
                case 267:
                case 290:
                case 291:
                case 292:
                case 293:
                case 294:
                case 295:
                case 296:
                case 297:
                case 298:
                case 299:
                case 300:
                case 301:
                case 306:
                case 309:
                case 315:
                case 316:
                case 317:
                case 318:
                case 319:
                case 340:
                case 347:
                case 348:
                case 349:
                case 353:
                case 354:
                case 355:
                case 356:
                case 357:
                case 358:
                case 359:
                case 362:
                default:
                    this.mEffectType = -1;
                    break;
            }
            return this;
        }

        public Builder setEffectStrength(int strength) {
            if (strength <= 2400) {
                this.mEffectStrength = strength;
            } else {
                this.mEffectStrength = 1;
            }
            return this;
        }

        public Builder setEffectLoop(boolean loop) {
            this.mEffectLoop = loop;
            return this;
        }

        public Builder setRingtoneVibrateType(int type) {
            switch (type) {
                case 1:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 64:
                case 67:
                case 152:
                case 153:
                case 154:
                case 156:
                case 165:
                case 166:
                case 167:
                case 270:
                case 271:
                case 272:
                case 273:
                case 274:
                case 275:
                case 276:
                case 277:
                case 278:
                case 279:
                case 280:
                case 281:
                case 282:
                case 283:
                case 284:
                case 285:
                case 286:
                case 287:
                    this.mRingtoneVibrateType = type;
                    break;
                default:
                    this.mRingtoneVibrateType = -1;
                    break;
            }
            return this;
        }

        public Builder setIsRingtoneCustomized(boolean customized) {
            this.mIsRingtoneCustomized = customized;
            return this;
        }

        public Builder setRingtoneFilePath(String path) {
            this.mRingtoneFilePath = path;
            return this;
        }

        public Builder setStrengthSettingEnabled(boolean enabled) {
            this.mStrengthSettingEnabled = enabled;
            return this;
        }

        public Builder setAsynchronous(boolean async) {
            this.mAsynchronous = async;
            return this;
        }

        public Builder setUsageHint(int usageHint) {
            this.mUsageHint = usageHint;
            return this;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mEffectType);
        dest.writeInt(this.mEffectStrength);
        dest.writeBoolean(this.mEffectLoop);
        dest.writeInt(this.mRingtoneVibrateType);
        dest.writeBoolean(this.mIsRingtoneCustomized);
        dest.writeString(this.mRingtoneFilePath);
        dest.writeBoolean(this.mStrengthSettingEnabled);
        dest.writeBoolean(this.mAsynchronous);
        dest.writeInt(this.mUsageHint);
    }

    private WaveformEffect(Parcel in) {
        this.mEffectType = -1;
        this.mEffectStrength = 1;
        this.mEffectLoop = false;
        this.mRingtoneVibrateType = -1;
        this.mIsRingtoneCustomized = false;
        this.mRingtoneFilePath = "";
        this.mStrengthSettingEnabled = true;
        this.mAsynchronous = false;
        this.mUsageHint = 0;
        this.mEffectType = in.readInt();
        this.mEffectStrength = in.readInt();
        this.mEffectLoop = in.readBoolean();
        this.mRingtoneVibrateType = in.readInt();
        this.mIsRingtoneCustomized = in.readBoolean();
        this.mRingtoneFilePath = in.readString();
        this.mStrengthSettingEnabled = in.readBoolean();
        this.mAsynchronous = in.readBoolean();
        this.mUsageHint = in.readInt();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaveformEffect that = (WaveformEffect) o;
        if (this.mEffectType == that.getEffectType() && this.mEffectStrength == that.getEffectStrength() && this.mEffectLoop == that.getEffectLoop() && this.mRingtoneVibrateType == that.getRingtoneVibrateType() && this.mIsRingtoneCustomized == that.getIsRingtoneCustomized() && this.mRingtoneFilePath == that.getRingtoneFilePath() && this.mStrengthSettingEnabled == that.getStrengthSettingEnabled() && this.mAsynchronous == that.getAsynchronous() && this.mUsageHint == that.getUsageHint()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[0]);
    }

    public String toString() {
        return "WaveformEffect { " + Integer.toHexString(System.identityHashCode(this)) + ", effectType=" + this.mEffectType + ", effectStrength=" + this.mEffectStrength + ", effectLoop=" + this.mEffectLoop + ", ringtoneVibrateType=" + this.mRingtoneVibrateType + ", isRingtoneCustomized=" + this.mIsRingtoneCustomized + ", ringtoneFilePath=" + this.mRingtoneFilePath + ", strengthSettingEnabled=" + this.mStrengthSettingEnabled + ", asynchronous=" + this.mAsynchronous + ", usageHint=" + this.mUsageHint + " }";
    }
}
