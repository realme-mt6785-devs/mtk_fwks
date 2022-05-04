package android.app;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.LocusId;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.window.TaskSnapshot;
import android.window.WindowContainerToken;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public class TaskInfo {
    private static final String TAG = "TaskInfo";
    public ComponentName baseActivity;
    public Intent baseIntent;
    public Rect displayCutoutInsets;
    public int displayId;
    public boolean isFocused;
    public boolean isResizeable;
    public boolean isRunning;
    public boolean isVisible;
    public long lastActiveTime;
    public LocusId mTopActivityLocusId;
    public int numActivities;
    public ComponentName origActivity;
    public int parentTaskId;
    public PictureInPictureParams pictureInPictureParams;
    public Point positionInParent;
    public ComponentName realActivity;
    public int resizeMode;
    public boolean supportsMultiWindow;
    public boolean supportsSplitScreenMultiWindow;
    public ActivityManager.TaskDescription taskDescription;
    public int taskId;
    public WindowContainerToken token;
    public ComponentName topActivity;
    public boolean topActivityInSizeCompat;
    public ActivityInfo topActivityInfo;
    public int topActivityType;
    public int userId;
    public final Configuration configuration = new Configuration();
    public ArrayList<IBinder> launchCookies = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaskInfo() {
    }

    private TaskInfo(Parcel source) {
        readFromParcel(source);
    }

    public TaskSnapshot getTaskSnapshot(boolean isLowResolution) {
        try {
            return ActivityTaskManager.getService().getTaskSnapshot(this.taskId, isLowResolution);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get task snapshot, taskId=" + this.taskId, e);
            return null;
        }
    }

    public WindowContainerToken getToken() {
        return this.token;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public PictureInPictureParams getPictureInPictureParams() {
        return this.pictureInPictureParams;
    }

    public int getWindowingMode() {
        return this.configuration.windowConfiguration.getWindowingMode();
    }

    public int getActivityType() {
        return this.configuration.windowConfiguration.getActivityType();
    }

    public void addLaunchCookie(IBinder cookie) {
        if (cookie != null && !this.launchCookies.contains(cookie)) {
            this.launchCookies.add(cookie);
        }
    }

    public boolean containsLaunchCookie(IBinder cookie) {
        return this.launchCookies.contains(cookie);
    }

    public int getParentTaskId() {
        return this.parentTaskId;
    }

    public boolean hasParentTask() {
        return this.parentTaskId != -1;
    }

    public boolean equalsForTaskOrganizer(TaskInfo that) {
        return that != null && this.topActivityType == that.topActivityType && this.isResizeable == that.isResizeable && this.supportsMultiWindow == that.supportsMultiWindow && Objects.equals(this.positionInParent, that.positionInParent) && Objects.equals(this.pictureInPictureParams, that.pictureInPictureParams) && Objects.equals(this.displayCutoutInsets, that.displayCutoutInsets) && getWindowingMode() == that.getWindowingMode() && Objects.equals(this.taskDescription, that.taskDescription) && this.isFocused == that.isFocused && this.isVisible == that.isVisible;
    }

    public boolean equalsForSizeCompat(TaskInfo that) {
        boolean z;
        if (that == null || this.displayId != that.displayId || this.taskId != that.taskId || (z = this.topActivityInSizeCompat) != that.topActivityInSizeCompat) {
            return false;
        }
        if (z && !this.configuration.windowConfiguration.getBounds().equals(that.configuration.windowConfiguration.getBounds())) {
            return false;
        }
        if (!this.topActivityInSizeCompat || this.configuration.getLayoutDirection() == that.configuration.getLayoutDirection()) {
            return !this.topActivityInSizeCompat || this.isVisible == that.isVisible;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readFromParcel(Parcel source) {
        this.userId = source.readInt();
        this.taskId = source.readInt();
        this.displayId = source.readInt();
        this.isRunning = source.readBoolean();
        this.baseIntent = (Intent) source.readTypedObject(Intent.CREATOR);
        this.baseActivity = ComponentName.readFromParcel(source);
        this.topActivity = ComponentName.readFromParcel(source);
        this.origActivity = ComponentName.readFromParcel(source);
        this.realActivity = ComponentName.readFromParcel(source);
        this.numActivities = source.readInt();
        this.lastActiveTime = source.readLong();
        this.taskDescription = (ActivityManager.TaskDescription) source.readTypedObject(ActivityManager.TaskDescription.CREATOR);
        this.supportsSplitScreenMultiWindow = source.readBoolean();
        this.supportsMultiWindow = source.readBoolean();
        this.resizeMode = source.readInt();
        this.configuration.readFromParcel(source);
        this.token = WindowContainerToken.CREATOR.createFromParcel(source);
        this.topActivityType = source.readInt();
        this.pictureInPictureParams = (PictureInPictureParams) source.readTypedObject(PictureInPictureParams.CREATOR);
        this.displayCutoutInsets = (Rect) source.readTypedObject(Rect.CREATOR);
        this.topActivityInfo = (ActivityInfo) source.readTypedObject(ActivityInfo.CREATOR);
        this.isResizeable = source.readBoolean();
        source.readBinderList(this.launchCookies);
        this.positionInParent = (Point) source.readTypedObject(Point.CREATOR);
        this.parentTaskId = source.readInt();
        this.isFocused = source.readBoolean();
        this.isVisible = source.readBoolean();
        this.topActivityInSizeCompat = source.readBoolean();
        this.mTopActivityLocusId = (LocusId) source.readTypedObject(LocusId.CREATOR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeInt(this.taskId);
        dest.writeInt(this.displayId);
        dest.writeBoolean(this.isRunning);
        dest.writeTypedObject(this.baseIntent, 0);
        ComponentName.writeToParcel(this.baseActivity, dest);
        ComponentName.writeToParcel(this.topActivity, dest);
        ComponentName.writeToParcel(this.origActivity, dest);
        ComponentName.writeToParcel(this.realActivity, dest);
        dest.writeInt(this.numActivities);
        dest.writeLong(this.lastActiveTime);
        dest.writeTypedObject(this.taskDescription, flags);
        dest.writeBoolean(this.supportsSplitScreenMultiWindow);
        dest.writeBoolean(this.supportsMultiWindow);
        dest.writeInt(this.resizeMode);
        this.configuration.writeToParcel(dest, flags);
        this.token.writeToParcel(dest, flags);
        dest.writeInt(this.topActivityType);
        dest.writeTypedObject(this.pictureInPictureParams, flags);
        dest.writeTypedObject(this.displayCutoutInsets, flags);
        dest.writeTypedObject(this.topActivityInfo, flags);
        dest.writeBoolean(this.isResizeable);
        dest.writeBinderList(this.launchCookies);
        dest.writeTypedObject(this.positionInParent, flags);
        dest.writeInt(this.parentTaskId);
        dest.writeBoolean(this.isFocused);
        dest.writeBoolean(this.isVisible);
        dest.writeBoolean(this.topActivityInSizeCompat);
        dest.writeTypedObject(this.mTopActivityLocusId, flags);
    }

    public String toString() {
        return "TaskInfo{userId=" + this.userId + " taskId=" + this.taskId + " displayId=" + this.displayId + " isRunning=" + this.isRunning + " baseIntent=" + this.baseIntent + " baseActivity=" + this.baseActivity + " topActivity=" + this.topActivity + " origActivity=" + this.origActivity + " realActivity=" + this.realActivity + " numActivities=" + this.numActivities + " lastActiveTime=" + this.lastActiveTime + " supportsSplitScreenMultiWindow=" + this.supportsSplitScreenMultiWindow + " supportsMultiWindow=" + this.supportsMultiWindow + " resizeMode=" + this.resizeMode + " isResizeable=" + this.isResizeable + " token=" + this.token + " topActivityType=" + this.topActivityType + " pictureInPictureParams=" + this.pictureInPictureParams + " displayCutoutSafeInsets=" + this.displayCutoutInsets + " topActivityInfo=" + this.topActivityInfo + " launchCookies=" + this.launchCookies + " positionInParent=" + this.positionInParent + " parentTaskId=" + this.parentTaskId + " isFocused=" + this.isFocused + " isVisible=" + this.isVisible + " topActivityInSizeCompat=" + this.topActivityInSizeCompat + " locusId= " + this.mTopActivityLocusId + "}";
    }
}
