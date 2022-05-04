package android.hardware;

import android.content.Context;
/* loaded from: classes.dex */
public interface ISystemSensorManagerExt {
    default boolean registerListenerImplHook(Context context, SensorEventListener listener, Sensor sensor, int delayUs) {
        return false;
    }

    default boolean unregisterListenerImplHook(SensorEventListener listener, Sensor sensor) {
        return false;
    }

    default boolean blockDispatchEvent(SensorEventListener listener, Sensor sensor, SensorEvent events) {
        return false;
    }

    default boolean needToAdjustEvent(SensorEventListener listener, Sensor sensor, SensorEvent events) {
        return false;
    }

    default SensorEvent dispatchEventDataAdjust(SensorEventListener listener, Sensor sensor, SensorEvent events) {
        SensorEvent defaultEvent = new SensorEvent(events.values.length);
        return defaultEvent;
    }

    default SensorEvent dispatchSensorEventHook(SensorEventListener listener, Sensor sensor, SensorEvent events, boolean inMirage, int cmdArgs, Context context) {
        return events;
    }

    default void dispatchSensorEventEnd(int res, int handle, float[] values, int accuracy, int type, int cmdArgs, String client) {
    }
}
