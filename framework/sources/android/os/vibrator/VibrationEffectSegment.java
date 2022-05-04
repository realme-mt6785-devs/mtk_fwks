package android.os.vibrator;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public abstract class VibrationEffectSegment implements Parcelable {
    public static final Parcelable.Creator<VibrationEffectSegment> CREATOR = new Parcelable.Creator<VibrationEffectSegment>() { // from class: android.os.vibrator.VibrationEffectSegment.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibrationEffectSegment createFromParcel(Parcel in) {
            switch (in.readInt()) {
                case 1:
                    return new PrebakedSegment(in);
                case 2:
                    return new PrimitiveSegment(in);
                case 3:
                    return new StepSegment(in);
                case 4:
                    return new RampSegment(in);
                default:
                    throw new IllegalStateException("Unexpected vibration event type token in parcel.");
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibrationEffectSegment[] newArray(int size) {
            return new VibrationEffectSegment[size];
        }
    };
    static final int PARCEL_TOKEN_PREBAKED = 1;
    static final int PARCEL_TOKEN_PRIMITIVE = 2;
    static final int PARCEL_TOKEN_RAMP = 4;
    static final int PARCEL_TOKEN_STEP = 3;

    public abstract <T extends VibrationEffectSegment> T applyEffectStrength(int i);

    public abstract long getDuration();

    public abstract boolean hasNonZeroAmplitude();

    public abstract <T extends VibrationEffectSegment> T resolve(int i);

    public abstract <T extends VibrationEffectSegment> T scale(float f);

    public abstract void validate();
}
