package com.oplus.eventhub.sdk;

import com.oplus.eventhub.sdk.aidl.DeviceEventResult;
import com.oplus.eventhub.sdk.aidl.IEventCallback;
/* loaded from: classes4.dex */
public abstract class EventCallback extends IEventCallback.Stub {
    @Override // com.oplus.eventhub.sdk.aidl.IEventCallback
    public abstract void onEventStateChanged(DeviceEventResult deviceEventResult);
}
