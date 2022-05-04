package com.oplus.eventhub.sdk.aidl;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
/* loaded from: classes4.dex */
public class Event implements Parcelable {
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() { // from class: com.oplus.eventhub.sdk.aidl.Event.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    private static final String TAG = "Event";
    private int mEventType;
    private Bundle mExtra;

    public Event(int eventType, Bundle extra) {
        this.mEventType = eventType;
        this.mExtra = extra;
    }

    public int getEventType() {
        return this.mEventType;
    }

    public Bundle getExtra() {
        return this.mExtra;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Event)) {
            return false;
        }
        Event event = (Event) object;
        if (this.mEventType == event.getEventType()) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{mEventType=");
        sb.append(this.mEventType);
        sb.append(", mExtra=");
        Object obj = this.mExtra;
        if (obj == null) {
            obj = "null";
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mEventType)});
    }

    public Event(Parcel in) {
        this.mEventType = in.readInt();
        this.mExtra = in.readBundle(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.mEventType);
        dest.writeBundle(this.mExtra);
    }
}
