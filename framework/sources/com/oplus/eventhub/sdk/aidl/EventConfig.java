package com.oplus.eventhub.sdk.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes4.dex */
public class EventConfig implements Parcelable {
    public static final Parcelable.Creator<EventConfig> CREATOR = new Parcelable.Creator<EventConfig>() { // from class: com.oplus.eventhub.sdk.aidl.EventConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventConfig createFromParcel(Parcel in) {
            return new EventConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventConfig[] newArray(int size) {
            return new EventConfig[size];
        }
    };
    private Set<Event> mEventSet = new HashSet();

    public EventConfig(HashSet<Event> events) {
        if (events != null && !events.isEmpty()) {
            this.mEventSet.addAll(events);
        }
    }

    public Set<Event> getEventSet() {
        if (this.mEventSet == null) {
            this.mEventSet = new HashSet();
        }
        return this.mEventSet;
    }

    public Set<Integer> getAllEvents() {
        HashSet<Integer> hashSet = new HashSet<>();
        Set<Event> set = this.mEventSet;
        if (set != null && !set.isEmpty()) {
            for (Event event : this.mEventSet) {
                hashSet.add(Integer.valueOf(event.getEventType()));
            }
        }
        return hashSet;
    }

    public void addEvent(Event event) {
        if (this.mEventSet == null) {
            this.mEventSet = new HashSet();
        }
        this.mEventSet.add(event);
    }

    public String toString() {
        Set<Event> set = this.mEventSet;
        if (set == null || set.isEmpty()) {
            return "EventConfig empty.";
        }
        return "EventConfig{mEventSet=" + Arrays.toString(this.mEventSet.toArray()) + '}';
    }

    public EventConfig(Parcel in) {
        ClassLoader loader = EventConfig.class.getClassLoader();
        List<Event> list = new ArrayList<>();
        in.readList(list, loader);
        if (!list.isEmpty()) {
            this.mEventSet.addAll(list);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        if (this.mEventSet != null) {
            dest.writeList(new ArrayList(this.mEventSet));
        }
    }
}
