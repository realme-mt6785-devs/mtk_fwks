package com.oplus.eventhub.sdk.aidl;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class TriggerEvent implements Parcelable {
    public static final Parcelable.Creator<TriggerEvent> CREATOR = new Parcelable.Creator<TriggerEvent>() { // from class: com.oplus.eventhub.sdk.aidl.TriggerEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TriggerEvent createFromParcel(Parcel in) {
            return new TriggerEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TriggerEvent[] newArray(int size) {
            return new TriggerEvent[size];
        }
    };
    public static final String EXTRA_ELAPSED_TIME = "elapsed_real_time";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_UID = "uid";
    public static final String GAME_SCENE_ID = "scene_id";
    public static final int INPUT_ACTIVITY_PAUSED = 30;
    public static final int INPUT_ACTIVITY_RESUMED = 11;
    public static final int INPUT_CAMERA_START = 18;
    public static final int INPUT_CAMERA_STOP = 19;
    public static final int INPUT_FILE_DOWNLOAD_START = 31;
    public static final int INPUT_FILE_DOWNLOAD_STOP = 32;
    public static final int INPUT_GAME_SCENE = 37;
    public static final String INPUT_GPS_EXTRA_HASH = "location_receiver_hash";
    public static final int INPUT_GPS_OFF = 15;
    public static final int INPUT_GPS_ON = 14;
    public static final int INPUT_LOCATION_REQUEST_OFF = 13;
    public static final int INPUT_LOCATION_REQUEST_ON = 12;
    public static final int INPUT_NOTIFY_POSTED = 28;
    public static final int INPUT_NOTIFY_REMOVED = 29;
    public static final int INPUT_PROCESS_DIED = 22;
    public static final int INPUT_PROCESS_FRONT = 21;
    public static final int INPUT_SENSOR_START = 16;
    public static final int INPUT_SENSOR_STOP = 17;
    public static final int INPUT_VIDEO_START = 26;
    public static final int INPUT_VIDEO_STOP = 27;
    public static final int INPUT_WAKELOCK_ACQUIRED = 24;
    public static final int INPUT_WAKELOCK_RELEASED = 25;
    private int mEventId;
    private Bundle mExtraData;
    private int mPid;
    private String mPkgName;

    public TriggerEvent(int eventId, int pid, String pkgName, Bundle bundle) {
        this.mEventId = eventId;
        this.mPid = pid;
        this.mPkgName = pkgName;
        this.mExtraData = bundle;
    }

    public int getEventId() {
        return this.mEventId;
    }

    public int getPid() {
        return this.mPid;
    }

    public String getPkgName() {
        return this.mPkgName;
    }

    public Bundle getExtraData() {
        return this.mExtraData;
    }

    public void setExtraData(Bundle extraData) {
        this.mExtraData = extraData;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("TriggerEvent :");
        stringBuilder.append("\teventId is :");
        stringBuilder.append(this.mEventId);
        stringBuilder.append("\tpid is : ");
        stringBuilder.append(this.mPid);
        stringBuilder.append("\t\tpackageName is : ");
        stringBuilder.append(this.mPkgName);
        if (this.mExtraData != null) {
            stringBuilder.append("\tExtraData is : ");
            stringBuilder.append(this.mExtraData.toString());
        }
        return stringBuilder.toString();
    }

    public TriggerEvent(Parcel in) {
        this.mEventId = in.readInt();
        this.mPid = in.readInt();
        this.mPkgName = in.readString();
        this.mExtraData = in.readBundle(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.mEventId);
        dest.writeInt(this.mPid);
        dest.writeString(this.mPkgName);
        dest.writeBundle(this.mExtraData);
    }
}
