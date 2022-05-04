package android.app.assist;

import android.annotation.SystemApi;
import android.os.IBinder;
import android.os.Parcel;
@SystemApi
/* loaded from: classes.dex */
public class ActivityId {
    private final IBinder mActivityId;
    private final int mTaskId;

    public ActivityId(int taskId, IBinder activityId) {
        this.mTaskId = taskId;
        this.mActivityId = activityId;
    }

    public ActivityId(Parcel source) {
        this.mTaskId = source.readInt();
        this.mActivityId = source.readStrongBinder();
    }

    public int getTaskId() {
        return this.mTaskId;
    }

    public IBinder getToken() {
        return this.mActivityId;
    }

    public void writeToParcel(Parcel dest, int parcelableFlags) {
        dest.writeInt(this.mTaskId);
        dest.writeStrongBinder(this.mActivityId);
    }

    public String toString() {
        return "ActivityId { taskId = " + this.mTaskId + ", activityId = " + this.mActivityId + " }";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActivityId that = (ActivityId) o;
        if (this.mTaskId != that.mTaskId) {
            return false;
        }
        IBinder iBinder = this.mActivityId;
        if (iBinder != null) {
            return iBinder.equals(that.mActivityId);
        }
        if (that.mActivityId == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.mTaskId;
        int i = result * 31;
        IBinder iBinder = this.mActivityId;
        int result2 = i + (iBinder != null ? iBinder.hashCode() : 0);
        return result2;
    }
}
