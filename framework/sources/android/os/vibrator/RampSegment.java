package android.os.vibrator;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.VibrationEffect;
import com.android.internal.util.Preconditions;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class RampSegment extends VibrationEffectSegment {
    public static final Parcelable.Creator<RampSegment> CREATOR = new Parcelable.Creator<RampSegment>() { // from class: android.os.vibrator.RampSegment.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RampSegment createFromParcel(Parcel in) {
            in.readInt();
            return new RampSegment(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RampSegment[] newArray(int size) {
            return new RampSegment[size];
        }
    };
    private final int mDuration;
    private final float mEndAmplitude;
    private final float mEndFrequency;
    private final float mStartAmplitude;
    private final float mStartFrequency;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RampSegment(Parcel in) {
        this(in.readFloat(), in.readFloat(), in.readFloat(), in.readFloat(), in.readInt());
    }

    public RampSegment(float startAmplitude, float endAmplitude, float startFrequency, float endFrequency, int duration) {
        this.mStartAmplitude = startAmplitude;
        this.mEndAmplitude = endAmplitude;
        this.mStartFrequency = startFrequency;
        this.mEndFrequency = endFrequency;
        this.mDuration = duration;
    }

    public boolean equals(Object o) {
        if (!(o instanceof RampSegment)) {
            return false;
        }
        RampSegment other = (RampSegment) o;
        return Float.compare(this.mStartAmplitude, other.mStartAmplitude) == 0 && Float.compare(this.mEndAmplitude, other.mEndAmplitude) == 0 && Float.compare(this.mStartFrequency, other.mStartFrequency) == 0 && Float.compare(this.mEndFrequency, other.mEndFrequency) == 0 && this.mDuration == other.mDuration;
    }

    public float getStartAmplitude() {
        return this.mStartAmplitude;
    }

    public float getEndAmplitude() {
        return this.mEndAmplitude;
    }

    public float getStartFrequency() {
        return this.mStartFrequency;
    }

    public float getEndFrequency() {
        return this.mEndFrequency;
    }

    @Override // android.os.vibrator.VibrationEffectSegment
    public long getDuration() {
        return this.mDuration;
    }

    @Override // android.os.vibrator.VibrationEffectSegment
    public boolean hasNonZeroAmplitude() {
        return this.mStartAmplitude > 0.0f || this.mEndAmplitude > 0.0f;
    }

    @Override // android.os.vibrator.VibrationEffectSegment
    public void validate() {
        int i = this.mDuration;
        Preconditions.checkArgumentNonnegative(i, "Durations must all be >= 0, got " + this.mDuration);
        Preconditions.checkArgumentInRange(this.mStartAmplitude, 0.0f, 1.0f, "startAmplitude");
        Preconditions.checkArgumentInRange(this.mEndAmplitude, 0.0f, 1.0f, "endAmplitude");
    }

    @Override // android.os.vibrator.VibrationEffectSegment
    public RampSegment resolve(int defaultAmplitude) {
        return this;
    }

    @Override // android.os.vibrator.VibrationEffectSegment
    public RampSegment scale(float scaleFactor) {
        float newStartAmplitude = VibrationEffect.scale(this.mStartAmplitude, scaleFactor);
        float newEndAmplitude = VibrationEffect.scale(this.mEndAmplitude, scaleFactor);
        if (Float.compare(this.mStartAmplitude, newStartAmplitude) == 0 && Float.compare(this.mEndAmplitude, newEndAmplitude) == 0) {
            return this;
        }
        return new RampSegment(newStartAmplitude, newEndAmplitude, this.mStartFrequency, this.mEndFrequency, this.mDuration);
    }

    @Override // android.os.vibrator.VibrationEffectSegment
    public RampSegment applyEffectStrength(int effectStrength) {
        return this;
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(this.mStartAmplitude), Float.valueOf(this.mEndAmplitude), Float.valueOf(this.mStartFrequency), Float.valueOf(this.mEndFrequency), Integer.valueOf(this.mDuration));
    }

    public String toString() {
        return "Ramp{startAmplitude=" + this.mStartAmplitude + ", endAmplitude=" + this.mEndAmplitude + ", startFrequency=" + this.mStartFrequency + ", endFrequency=" + this.mEndFrequency + ", duration=" + this.mDuration + "}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(4);
        out.writeFloat(this.mStartAmplitude);
        out.writeFloat(this.mEndAmplitude);
        out.writeFloat(this.mStartFrequency);
        out.writeFloat(this.mEndFrequency);
        out.writeInt(this.mDuration);
    }
}
