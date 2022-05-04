package android.media;

import android.content.Context;
/* loaded from: classes2.dex */
public interface IAudioManagerExt {
    boolean adjustStreamVolumePermission(Context context, int i, int i2);

    boolean getDebugLog();

    String getParametersForCommonExtends(String str);

    int oplusGetStreamMaxVolume(Context context, int i);

    int oplusGetStreamVolume(Context context, int i);

    boolean setBluetoothScoOnPermission();

    boolean setMicrophoneMutePermission(Context context);

    boolean setParametersForCommonExtends(String str);

    boolean setParametersPermission(Context context, String str);

    boolean setRingerModePermission(Context context, int i);

    boolean setStreamVolumePermission(Context context, int i);
}
