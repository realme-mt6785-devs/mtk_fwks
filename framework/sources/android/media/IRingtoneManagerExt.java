package android.media;

import android.content.Context;
import android.net.Uri;
import android.util.Pair;
/* loaded from: classes2.dex */
public interface IRingtoneManagerExt {
    Pair<String, String> getRingtoneTitlePath(Context context, Uri uri);

    void putSettingRingCacheTitleAndPath(Context context, int i, String str, String str2);

    default boolean isComponentVersionChange(Context context) {
        return false;
    }

    default boolean isCustomDefaultRingtoneNeeded(Context context) {
        return false;
    }

    default void clearDefaultRingtonesHistory(Context context) {
    }

    default void setRingtonesUri(Context context, int type, Uri ringtoneUri) {
    }

    default void setRingtoneUriAgainIfNeeded(Context context, String filename, Uri baseuri, int type) {
    }

    default void updateRingtoneUriIfNeeded(Context context, int type, String setting) {
    }

    default void hookforMediaProviderCustomized(Context context) {
    }

    default boolean needChangeCarrierRingtone(Context context) {
        return false;
    }

    default String getCarrierDefaultRingtoneFilename(Context context) {
        return null;
    }

    default boolean isUseUSKUDefaultRingtone(int type) {
        return false;
    }

    default void setOplusDefaultRingtoneUriIfNotSet(Context context, int type, String filename, String whichAudio) {
    }
}
