package android.content;

import android.os.Parcel;
/* loaded from: classes.dex */
public class OplusBaseIntent {
    public static final int FLAG_RECEIVER_OPLUSQUEUE = 524288;
    public static final int FLAG_RECEIVER_QUEUE_PRIOR = 1048576;
    public static final int OPLUS_FLAG_ACTIVITY_KEEP_RESUM_WHEN_SLEEPING = 1073741824;
    public static final int OPLUS_FLAG_ACTIVITY_SECURE_POLICY = Integer.MIN_VALUE;
    public static final int OPLUS_FLAG_KEEP_CHOOSER_LABEL_FOR_MULTI_APP = 8192;
    public static final int OPLUS_FLAG_MULTI_APP_DIRECT_MULTI_APP = 4096;
    public static final int OPLUS_FLAG_MULTI_APP_SKIP_CHOOSER = 2048;
    public static final int OPLUS_FLAG_MUTIL_APP = 1024;
    public static final int OPLUS_FLAG_MUTIL_CHOOSER = 512;
    public static final int OPLUS_FLAG_RECEIVER_OPLUSQUEUE = 2;
    public static final int OPLUS_FLAG_RECEIVER_QUEUE_PRIOR = 1;
    int mCallingUid;
    int mIsForFreeForm;
    int mIsFromGameSpace;
    int mOplusFlags;
    int mOplusUserId;
    private int mPairLaunchWindowingMode;
    int mPid;
    int mStackId;
    int mStartFromOcar;
    int mUid;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OplusBaseIntent() {
        this.mOplusUserId = 0;
        this.mIsFromGameSpace = 0;
        this.mIsForFreeForm = 0;
        this.mStackId = 0;
        this.mPid = -1;
        this.mUid = -1;
        this.mCallingUid = -1;
        this.mStartFromOcar = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OplusBaseIntent(OplusBaseIntent o, int copyMode) {
        this.mOplusUserId = 0;
        this.mIsFromGameSpace = 0;
        this.mIsForFreeForm = 0;
        this.mStackId = 0;
        this.mPid = -1;
        this.mUid = -1;
        this.mCallingUid = -1;
        this.mStartFromOcar = 0;
        this.mOplusUserId = o.mOplusUserId;
        this.mIsFromGameSpace = o.mIsFromGameSpace;
        this.mIsForFreeForm = o.mIsForFreeForm;
        this.mStackId = o.mStackId;
        this.mOplusFlags = o.mOplusFlags;
        this.mCallingUid = o.mCallingUid;
        this.mStartFromOcar = o.mStartFromOcar;
        this.mPairLaunchWindowingMode = o.mPairLaunchWindowingMode;
    }

    public int fillIn(OplusBaseIntent other, int flags) {
        this.mOplusUserId = other.mOplusUserId;
        this.mIsFromGameSpace = other.mIsFromGameSpace;
        this.mIsForFreeForm = other.mIsForFreeForm;
        this.mStackId = other.mStackId;
        this.mOplusFlags |= other.mOplusFlags;
        this.mCallingUid = other.mCallingUid;
        this.mStartFromOcar = other.mStartFromOcar;
        this.mPairLaunchWindowingMode = other.mPairLaunchWindowingMode;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mOplusUserId);
        out.writeInt(this.mIsFromGameSpace);
        out.writeInt(this.mIsForFreeForm);
        out.writeInt(this.mStackId);
        out.writeInt(this.mOplusFlags);
        out.writeInt(this.mCallingUid);
        out.writeInt(this.mStartFromOcar);
        out.writeInt(this.mPairLaunchWindowingMode);
    }

    public void readFromParcel(Parcel in) {
        this.mOplusUserId = in.readInt();
        this.mIsFromGameSpace = in.readInt();
        this.mIsForFreeForm = in.readInt();
        this.mStackId = in.readInt();
        this.mOplusFlags = in.readInt();
        this.mCallingUid = in.readInt();
        this.mStartFromOcar = in.readInt();
        this.mPairLaunchWindowingMode = in.readInt();
    }

    public int getOplusUserId() {
        return this.mOplusUserId;
    }

    public void setOplusUserId(int oplusUserId) {
        this.mOplusUserId = oplusUserId;
    }

    public int getIsFromGameSpace() {
        return this.mIsFromGameSpace;
    }

    public void setIsFromGameSpace(int isFromGameSpace) {
        this.mIsFromGameSpace = isFromGameSpace;
    }

    public int getIsForFreeForm() {
        return this.mIsForFreeForm;
    }

    public void setIsForFreeForm(int isForFreeForm) {
        this.mIsForFreeForm = isForFreeForm;
    }

    public int getLaunchStackId() {
        return this.mStackId;
    }

    public void setLaunchStackId(int stackId) {
        this.mStackId = stackId;
    }

    public int getOplusFlags() {
        return this.mOplusFlags;
    }

    public void setOplusFlags(int oplusFlags) {
        this.mOplusFlags = oplusFlags;
    }

    public void addOplusFlags(int oplusFlags) {
        this.mOplusFlags |= oplusFlags;
    }

    public void removeOplusFlags(int oplusFlags) {
        this.mOplusFlags &= ~oplusFlags;
    }

    public void setPid(int pid) {
        this.mPid = pid;
    }

    public int getPid() {
        return this.mPid;
    }

    public void setUid(int uid) {
        this.mUid = uid;
    }

    public int getUid() {
        return this.mUid;
    }

    public void setCallingUid(int uid) {
        this.mCallingUid = uid;
    }

    public int getCallingUid() {
        return this.mCallingUid;
    }

    public int getStartFromOcar() {
        return this.mStartFromOcar;
    }

    public void setStartFromOcar(int startFromOcar) {
        this.mStartFromOcar = startFromOcar;
    }

    public void setPairLaunchWindowingMode(int windowingMode) {
        this.mPairLaunchWindowingMode = windowingMode;
    }

    public int getPairLaunchWindowingMode() {
        return this.mPairLaunchWindowingMode;
    }

    public String toString() {
        StringBuilder b = new StringBuilder(128);
        if (this.mOplusFlags != 0) {
            b.append(" oflg=0x");
            b.append(Integer.toHexString(this.mOplusFlags));
        }
        if (this.mOplusUserId != 0) {
            b.append(" ouserid=");
            b.append(this.mOplusUserId);
        }
        if (this.mIsForFreeForm != 0) {
            b.append(" freeform=");
            b.append(this.mIsForFreeForm);
        }
        if (this.mIsFromGameSpace != 0) {
            b.append(" gs=");
            b.append(this.mIsFromGameSpace);
        }
        if (this.mStackId != 0) {
            b.append(" stackid=");
            b.append(this.mStackId);
        }
        if (this.mCallingUid != -1) {
            b.append(" mCallingUid=");
            b.append(this.mCallingUid);
        }
        if (this.mStartFromOcar != 0) {
            b.append(" mStartFromOcar=");
            b.append(this.mStartFromOcar);
        }
        return b.toString();
    }
}
