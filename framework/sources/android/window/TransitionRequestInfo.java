package android.window;

import android.app.ActivityManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.WindowManager;
import android.window.IRemoteTransition;
import com.android.internal.util.AnnotationValidations;
import java.lang.annotation.Annotation;
/* loaded from: classes3.dex */
public final class TransitionRequestInfo implements Parcelable {
    public static final Parcelable.Creator<TransitionRequestInfo> CREATOR = new Parcelable.Creator<TransitionRequestInfo>() { // from class: android.window.TransitionRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransitionRequestInfo[] newArray(int size) {
            return new TransitionRequestInfo[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransitionRequestInfo createFromParcel(Parcel in) {
            return new TransitionRequestInfo(in);
        }
    };
    private IRemoteTransition mRemoteTransition;
    private ActivityManager.RunningTaskInfo mTriggerTask;
    private final int mType;

    public TransitionRequestInfo(int type, ActivityManager.RunningTaskInfo triggerTask, IRemoteTransition remoteTransition) {
        this.mType = type;
        AnnotationValidations.validate((Class<? extends Annotation>) WindowManager.TransitionType.class, (Annotation) null, type);
        this.mTriggerTask = triggerTask;
        this.mRemoteTransition = remoteTransition;
    }

    public int getType() {
        return this.mType;
    }

    public ActivityManager.RunningTaskInfo getTriggerTask() {
        return this.mTriggerTask;
    }

    public IRemoteTransition getRemoteTransition() {
        return this.mRemoteTransition;
    }

    public TransitionRequestInfo setTriggerTask(ActivityManager.RunningTaskInfo value) {
        this.mTriggerTask = value;
        return this;
    }

    public TransitionRequestInfo setRemoteTransition(IRemoteTransition value) {
        this.mRemoteTransition = value;
        return this;
    }

    public String toString() {
        return "TransitionRequestInfo { type = " + this.mType + ", triggerTask = " + this.mTriggerTask + ", remoteTransition = " + this.mRemoteTransition + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        byte flg = 0;
        if (this.mTriggerTask != null) {
            flg = (byte) (0 | 2);
        }
        if (this.mRemoteTransition != null) {
            flg = (byte) (flg | 4);
        }
        dest.writeByte(flg);
        dest.writeInt(this.mType);
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTriggerTask;
        if (runningTaskInfo != null) {
            dest.writeTypedObject(runningTaskInfo, flags);
        }
        IRemoteTransition iRemoteTransition = this.mRemoteTransition;
        if (iRemoteTransition != null) {
            dest.writeStrongInterface(iRemoteTransition);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    TransitionRequestInfo(Parcel in) {
        byte flg = in.readByte();
        int type = in.readInt();
        ActivityManager.RunningTaskInfo triggerTask = (flg & 2) == 0 ? null : (ActivityManager.RunningTaskInfo) in.readTypedObject(ActivityManager.RunningTaskInfo.CREATOR);
        IRemoteTransition remoteTransition = (flg & 4) == 0 ? null : IRemoteTransition.Stub.asInterface(in.readStrongBinder());
        this.mType = type;
        AnnotationValidations.validate((Class<? extends Annotation>) WindowManager.TransitionType.class, (Annotation) null, type);
        this.mTriggerTask = triggerTask;
        this.mRemoteTransition = remoteTransition;
    }

    @Deprecated
    private void __metadata() {
    }
}
