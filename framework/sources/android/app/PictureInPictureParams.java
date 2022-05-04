package android.app;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Rational;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PictureInPictureParams implements Parcelable {
    public static final Parcelable.Creator<PictureInPictureParams> CREATOR = new Parcelable.Creator<PictureInPictureParams>() { // from class: android.app.PictureInPictureParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PictureInPictureParams createFromParcel(Parcel in) {
            return new PictureInPictureParams(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PictureInPictureParams[] newArray(int size) {
            return new PictureInPictureParams[size];
        }
    };
    private Rational mAspectRatio;
    private Boolean mAutoEnterEnabled;
    private Boolean mSeamlessResizeEnabled;
    private Rect mSourceRectHint;
    private List<RemoteAction> mUserActions;

    /* loaded from: classes.dex */
    public static class Builder {
        private Rational mAspectRatio;
        private Boolean mAutoEnterEnabled;
        private Boolean mSeamlessResizeEnabled;
        private Rect mSourceRectHint;
        private List<RemoteAction> mUserActions;

        public Builder setAspectRatio(Rational aspectRatio) {
            this.mAspectRatio = aspectRatio;
            return this;
        }

        public Builder setActions(List<RemoteAction> actions) {
            if (this.mUserActions != null) {
                this.mUserActions = null;
            }
            if (actions != null) {
                this.mUserActions = new ArrayList(actions);
            }
            return this;
        }

        public Builder setSourceRectHint(Rect launchBounds) {
            if (launchBounds == null) {
                this.mSourceRectHint = null;
            } else {
                this.mSourceRectHint = new Rect(launchBounds);
            }
            return this;
        }

        public Builder setAutoEnterEnabled(boolean autoEnterEnabled) {
            this.mAutoEnterEnabled = Boolean.valueOf(autoEnterEnabled);
            return this;
        }

        public Builder setSeamlessResizeEnabled(boolean seamlessResizeEnabled) {
            this.mSeamlessResizeEnabled = Boolean.valueOf(seamlessResizeEnabled);
            return this;
        }

        public PictureInPictureParams build() {
            PictureInPictureParams params = new PictureInPictureParams(this.mAspectRatio, this.mUserActions, this.mSourceRectHint, this.mAutoEnterEnabled, this.mSeamlessResizeEnabled);
            return params;
        }
    }

    PictureInPictureParams() {
    }

    PictureInPictureParams(Parcel in) {
        if (in.readInt() != 0) {
            this.mAspectRatio = new Rational(in.readInt(), in.readInt());
        }
        if (in.readInt() != 0) {
            ArrayList arrayList = new ArrayList();
            this.mUserActions = arrayList;
            in.readTypedList(arrayList, RemoteAction.CREATOR);
        }
        if (in.readInt() != 0) {
            this.mSourceRectHint = Rect.CREATOR.createFromParcel(in);
        }
        if (in.readInt() != 0) {
            this.mAutoEnterEnabled = Boolean.valueOf(in.readBoolean());
        }
        if (in.readInt() != 0) {
            this.mSeamlessResizeEnabled = Boolean.valueOf(in.readBoolean());
        }
    }

    PictureInPictureParams(Rational aspectRatio, List<RemoteAction> actions, Rect sourceRectHint, Boolean autoEnterEnabled, Boolean seamlessResizeEnabled) {
        this.mAspectRatio = aspectRatio;
        this.mUserActions = actions;
        this.mSourceRectHint = sourceRectHint;
        this.mAutoEnterEnabled = autoEnterEnabled;
        this.mSeamlessResizeEnabled = seamlessResizeEnabled;
    }

    public PictureInPictureParams(PictureInPictureParams other) {
        this(other.mAspectRatio, other.mUserActions, other.hasSourceBoundsHint() ? new Rect(other.getSourceRectHint()) : null, other.mAutoEnterEnabled, other.mSeamlessResizeEnabled);
    }

    public void copyOnlySet(PictureInPictureParams otherArgs) {
        if (otherArgs.hasSetAspectRatio()) {
            this.mAspectRatio = otherArgs.mAspectRatio;
        }
        if (otherArgs.hasSetActions()) {
            this.mUserActions = otherArgs.mUserActions;
        }
        if (otherArgs.hasSourceBoundsHint()) {
            this.mSourceRectHint = new Rect(otherArgs.getSourceRectHint());
        }
        Boolean bool = otherArgs.mAutoEnterEnabled;
        if (bool != null) {
            this.mAutoEnterEnabled = bool;
        }
        Boolean bool2 = otherArgs.mSeamlessResizeEnabled;
        if (bool2 != null) {
            this.mSeamlessResizeEnabled = bool2;
        }
    }

    public float getAspectRatio() {
        Rational rational = this.mAspectRatio;
        if (rational != null) {
            return rational.floatValue();
        }
        return 0.0f;
    }

    public Rational getAspectRatioRational() {
        return this.mAspectRatio;
    }

    public boolean hasSetAspectRatio() {
        return this.mAspectRatio != null;
    }

    public List<RemoteAction> getActions() {
        return this.mUserActions;
    }

    public boolean hasSetActions() {
        return this.mUserActions != null;
    }

    public void truncateActions(int size) {
        if (hasSetActions()) {
            List<RemoteAction> list = this.mUserActions;
            this.mUserActions = list.subList(0, Math.min(list.size(), size));
        }
    }

    public Rect getSourceRectHint() {
        return this.mSourceRectHint;
    }

    public boolean hasSourceBoundsHint() {
        Rect rect = this.mSourceRectHint;
        return rect != null && !rect.isEmpty();
    }

    public boolean isAutoEnterEnabled() {
        Boolean bool = this.mAutoEnterEnabled;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isSeamlessResizeEnabled() {
        Boolean bool = this.mSeamlessResizeEnabled;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean empty() {
        return !hasSourceBoundsHint() && !hasSetActions() && !hasSetAspectRatio() && this.mAutoEnterEnabled != null && this.mSeamlessResizeEnabled != null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PictureInPictureParams)) {
            return false;
        }
        PictureInPictureParams that = (PictureInPictureParams) o;
        return Objects.equals(this.mAutoEnterEnabled, that.mAutoEnterEnabled) && Objects.equals(this.mSeamlessResizeEnabled, that.mSeamlessResizeEnabled) && Objects.equals(this.mAspectRatio, that.mAspectRatio) && Objects.equals(this.mUserActions, that.mUserActions) && Objects.equals(this.mSourceRectHint, that.mSourceRectHint);
    }

    public int hashCode() {
        return Objects.hash(this.mAspectRatio, this.mUserActions, this.mSourceRectHint, this.mAutoEnterEnabled, this.mSeamlessResizeEnabled);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        if (this.mAspectRatio != null) {
            out.writeInt(1);
            out.writeInt(this.mAspectRatio.getNumerator());
            out.writeInt(this.mAspectRatio.getDenominator());
        } else {
            out.writeInt(0);
        }
        if (this.mUserActions != null) {
            out.writeInt(1);
            out.writeTypedList(this.mUserActions, 0);
        } else {
            out.writeInt(0);
        }
        if (this.mSourceRectHint != null) {
            out.writeInt(1);
            this.mSourceRectHint.writeToParcel(out, 0);
        } else {
            out.writeInt(0);
        }
        if (this.mAutoEnterEnabled != null) {
            out.writeInt(1);
            out.writeBoolean(this.mAutoEnterEnabled.booleanValue());
        } else {
            out.writeInt(0);
        }
        if (this.mSeamlessResizeEnabled != null) {
            out.writeInt(1);
            out.writeBoolean(this.mSeamlessResizeEnabled.booleanValue());
            return;
        }
        out.writeInt(0);
    }

    public String toString() {
        return "PictureInPictureParams( aspectRatio=" + getAspectRatioRational() + " sourceRectHint=" + getSourceRectHint() + " hasSetActions=" + hasSetActions() + " isAutoPipEnabled=" + isAutoEnterEnabled() + " isSeamlessResizeEnabled=" + isSeamlessResizeEnabled() + ")";
    }
}
