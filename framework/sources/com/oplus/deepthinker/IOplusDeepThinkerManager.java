package com.oplus.deepthinker;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.os.Bundle;
import android.os.Handler;
import com.oplus.deepthinker.sdk.aidl.proton.appactionpredict.PredictAABResult;
import com.oplus.deepthinker.sdk.aidl.proton.appactionpredict.PredictResult;
import com.oplus.deepthinker.sdk.aidl.proton.deepsleep.DeepSleepPredictResult;
import com.oplus.deepthinker.sdk.aidl.proton.deepsleep.SleepRecord;
import com.oplus.deepthinker.sdk.aidl.proton.deepsleep.TotalPredictResult;
import com.oplus.deepthinker.sdk.aidl.proton.userprofile.WifiLocationLabel;
import com.oplus.eventhub.sdk.aidl.Event;
import com.oplus.eventhub.sdk.aidl.EventConfig;
import com.oplus.eventhub.sdk.aidl.EventRequestConfig;
import com.oplus.eventhub.sdk.aidl.IEventCallback;
import com.oplus.eventhub.sdk.aidl.TriggerEvent;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public interface IOplusDeepThinkerManager extends IOplusCommonFeature {
    public static final IOplusDeepThinkerManager DEFAULT = new IOplusDeepThinkerManager() { // from class: com.oplus.deepthinker.IOplusDeepThinkerManager.1
    };

    @Override // android.common.IOplusCommonFeature
    default IOplusDeepThinkerManager getDefault() {
        return DEFAULT;
    }

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusDeepThinkerManager;
    }

    default void registerServiceStateObserver(ServiceStateObserver observer) {
    }

    default int getAlgorithmPlatformVersion() {
        return -1;
    }

    default PredictAABResult getPredictAABResult() {
        return null;
    }

    default List<PredictResult> getAppPredictResultMap(String callerName) {
        return null;
    }

    default PredictResult getAppPredictResult(String callerName) {
        return null;
    }

    default DeepSleepPredictResult getDeepSleepPredictResult() {
        return null;
    }

    default SleepRecord getLastDeepSleepRecord() {
        return null;
    }

    default TotalPredictResult getDeepSleepTotalPredictResult() {
        return null;
    }

    default DeepSleepPredictResult getPredictResultWithFeedBack() {
        return null;
    }

    default int getAppType(String packageName) {
        return -1;
    }

    default Map getAppTypeMap(List<String> packageNameList) {
        return null;
    }

    default void triggerHookEvent(TriggerEvent triggerEvent) {
    }

    default void triggerHookEvent(int eventType, int uid, String pkgName, Bundle extra) {
    }

    default void triggerHookEventAsync(Handler handler, int eventID, int uid, String pkg, Bundle extra) {
    }

    @Deprecated
    default boolean registerCallback(IEventCallback callback, EventRequestConfig config) {
        return false;
    }

    @Deprecated
    default boolean unregisterCallback(IEventCallback callback) {
        return false;
    }

    default int registerEventCallback(IEventCallback callback, EventConfig config) {
        return 0;
    }

    default int unregisterEventCallback(IEventCallback callback) {
        return 0;
    }

    default List<Event> getAvailableEvent() {
        return null;
    }

    default boolean isAvailableEvent(Event event) {
        return false;
    }

    @Deprecated
    default Bundle call(Bundle request) {
        return null;
    }

    default List<String> getAppQueueSortedByTime() {
        return null;
    }

    default List<String> getAppQueueSortedByCount() {
        return null;
    }

    default List<String> getAppQueueSortedByComplex() {
        return null;
    }

    default List<String> getSmartGpsBssidList() {
        return null;
    }

    default int getInOutDoorState() {
        return 0;
    }

    default int getInOutDoorState(Bundle args) {
        return 0;
    }

    default void run(Runnable runnable) {
    }

    default List<WifiLocationLabel> getWifiLocationLabels() {
        return null;
    }

    default boolean isApplicationEnable() {
        return false;
    }
}
