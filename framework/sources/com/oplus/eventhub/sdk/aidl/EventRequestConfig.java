package com.oplus.eventhub.sdk.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes4.dex */
public class EventRequestConfig implements Parcelable {
    public static final Parcelable.Creator<EventRequestConfig> CREATOR = new Parcelable.Creator<EventRequestConfig>() { // from class: com.oplus.eventhub.sdk.aidl.EventRequestConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventRequestConfig createFromParcel(Parcel in) {
            return new EventRequestConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventRequestConfig[] newArray(int size) {
            return new EventRequestConfig[size];
        }
    };
    private ArraySet<DeviceEvent> mDeviceEventSet;

    public EventRequestConfig(ArraySet<DeviceEvent> events) {
        this.mDeviceEventSet = new ArraySet<>();
        if (events != null && !events.isEmpty()) {
            this.mDeviceEventSet.addAll((ArraySet<? extends DeviceEvent>) events);
        }
    }

    public ArraySet<DeviceEvent> getDeviceEventSet() {
        if (this.mDeviceEventSet == null) {
            this.mDeviceEventSet = new ArraySet<>();
        }
        return this.mDeviceEventSet;
    }

    public HashSet<Integer> getAllEvents() {
        HashSet<Integer> hashSet = new HashSet<>();
        ArraySet<DeviceEvent> arraySet = this.mDeviceEventSet;
        if (arraySet != null && !arraySet.isEmpty()) {
            Iterator<DeviceEvent> it = this.mDeviceEventSet.iterator();
            while (it.hasNext()) {
                DeviceEvent event = it.next();
                hashSet.add(Integer.valueOf(event.getEventType()));
            }
        }
        return hashSet;
    }

    public EventRequestConfig(Parcel in) {
        ClassLoader loader = EventRequestConfig.class.getClassLoader();
        this.mDeviceEventSet = in.readArraySet(loader);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        dest.writeArraySet(this.mDeviceEventSet);
    }
}
