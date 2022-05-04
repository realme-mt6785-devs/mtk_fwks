package com.mediatek.duraspeed.suppress;

import android.content.Context;
/* loaded from: classes.dex */
public interface ISuppressAction {
    void addToSuppressRestartList(Context context, String str);

    boolean notRemoveAlarm(String str);

    boolean onBeforeStartProcessForStaticReceiver(String str);

    String onReadyToStartComponent(Context context, String str, int i, String str2, String str3);
}
