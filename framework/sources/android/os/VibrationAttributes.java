package android.os;

import android.media.AudioAttributes;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.vibrator.PrebakedSegment;
import android.os.vibrator.VibrationEffectSegment;
import android.util.Slog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class VibrationAttributes implements Parcelable {
    public static final Parcelable.Creator<VibrationAttributes> CREATOR = new Parcelable.Creator<VibrationAttributes>() { // from class: android.os.VibrationAttributes.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibrationAttributes createFromParcel(Parcel p) {
            return new VibrationAttributes(p);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibrationAttributes[] newArray(int size) {
            return new VibrationAttributes[size];
        }
    };
    public static final int FLAG_ALL_SUPPORTED = 1;
    public static final int FLAG_BYPASS_INTERRUPTION_POLICY = 1;
    private static final long MAX_HAPTIC_FEEDBACK_DURATION = 5000;
    private static final String TAG = "VibrationAttributes";
    public static final int USAGE_ALARM = 17;
    public static final int USAGE_CLASS_ALARM = 1;
    public static final int USAGE_CLASS_FEEDBACK = 2;
    public static final int USAGE_CLASS_MASK = 15;
    public static final int USAGE_CLASS_UNKNOWN = 0;
    public static final int USAGE_COMMUNICATION_REQUEST = 65;
    public static final int USAGE_FILTER_MATCH_ALL = -1;
    public static final int USAGE_HARDWARE_FEEDBACK = 50;
    public static final int USAGE_NOTIFICATION = 49;
    public static final int USAGE_PHYSICAL_EMULATION = 34;
    public static final int USAGE_RINGTONE = 33;
    public static final int USAGE_TOUCH = 18;
    public static final int USAGE_UNKNOWN = 0;
    private final int mFlags;
    private final int mOriginalAudioUsage;
    private final int mUsage;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Flag {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Usage {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface UsageClass {
    }

    private VibrationAttributes(int usage, int audioUsage, int flags) {
        this.mUsage = usage;
        this.mOriginalAudioUsage = audioUsage;
        this.mFlags = flags & 1;
    }

    public int getUsageClass() {
        return this.mUsage & 15;
    }

    public int getUsage() {
        return this.mUsage;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean isFlagSet(int flag) {
        return (this.mFlags & flag) > 0;
    }

    public int getAudioUsage() {
        int i = this.mOriginalAudioUsage;
        if (i != 0) {
            return i;
        }
        switch (this.mUsage) {
            case 17:
                return 4;
            case 18:
                return 13;
            case 33:
                return 6;
            case 49:
                return 5;
            case 65:
                return 7;
            default:
                return 0;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mUsage);
        dest.writeInt(this.mOriginalAudioUsage);
        dest.writeInt(this.mFlags);
    }

    private VibrationAttributes(Parcel src) {
        this.mUsage = src.readInt();
        this.mOriginalAudioUsage = src.readInt();
        this.mFlags = src.readInt();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VibrationAttributes rhs = (VibrationAttributes) o;
        if (this.mUsage == rhs.mUsage && this.mOriginalAudioUsage == rhs.mOriginalAudioUsage && this.mFlags == rhs.mFlags) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mUsage), Integer.valueOf(this.mOriginalAudioUsage), Integer.valueOf(this.mFlags));
    }

    public String toString() {
        return "VibrationAttributes: Usage=" + usageToString() + " Audio Usage= " + AudioAttributes.usageToString(this.mOriginalAudioUsage) + " Flags=" + this.mFlags;
    }

    public String usageToString() {
        return usageToString(this.mUsage);
    }

    public static String usageToString(int usage) {
        switch (usage) {
            case 0:
                return "UNKNOWN";
            case 17:
                return "ALARM";
            case 18:
                return "TOUCH";
            case 33:
                return "RIGNTONE";
            case 34:
                return "PHYSICAL_EMULATION";
            case 49:
                return "NOTIFICATION";
            case 50:
                return "HARDWARE_FEEDBACK";
            case 65:
                return "COMMUNICATION_REQUEST";
            default:
                return "unknown usage " + usage;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private int mFlags;
        private int mOriginalAudioUsage;
        private int mUsage;

        public Builder() {
            this.mUsage = 0;
            this.mOriginalAudioUsage = 0;
            this.mFlags = 0;
        }

        public Builder(VibrationAttributes vib) {
            this.mUsage = 0;
            this.mOriginalAudioUsage = 0;
            this.mFlags = 0;
            if (vib != null) {
                this.mUsage = vib.mUsage;
                this.mOriginalAudioUsage = vib.mOriginalAudioUsage;
                this.mFlags = vib.mFlags;
            }
        }

        public Builder(AudioAttributes audio, VibrationEffect effect) {
            this.mUsage = 0;
            this.mOriginalAudioUsage = 0;
            this.mFlags = 0;
            setUsage(audio);
            setFlags(audio);
            applyHapticFeedbackHeuristics(effect);
        }

        private void applyHapticFeedbackHeuristics(VibrationEffect effect) {
            if (effect != null) {
                PrebakedSegment prebaked = extractPrebakedSegment(effect);
                if (this.mUsage == 0 && prebaked != null) {
                    switch (prebaked.getEffectId()) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 21:
                            this.mUsage = 18;
                            break;
                        default:
                            Slog.w(VibrationAttributes.TAG, "Unknown prebaked vibration effect, assuming it isn't haptic feedback");
                            break;
                    }
                }
                long duration = effect.getDuration();
                if (this.mUsage == 0 && duration >= 0 && duration < 5000) {
                    this.mUsage = 18;
                }
            }
        }

        private PrebakedSegment extractPrebakedSegment(VibrationEffect effect) {
            if (!(effect instanceof VibrationEffect.Composed)) {
                return null;
            }
            VibrationEffect.Composed composed = (VibrationEffect.Composed) effect;
            if (composed.getSegments().size() != 1) {
                return null;
            }
            VibrationEffectSegment segment = composed.getSegments().get(0);
            if (segment instanceof PrebakedSegment) {
                return (PrebakedSegment) segment;
            }
            return null;
        }

        private void setUsage(AudioAttributes audio) {
            this.mOriginalAudioUsage = audio.getUsage();
            switch (audio.getUsage()) {
                case 4:
                    this.mUsage = 17;
                    return;
                case 5:
                case 8:
                case 9:
                case 10:
                    this.mUsage = 49;
                    return;
                case 6:
                    this.mUsage = 33;
                    return;
                case 7:
                case 11:
                    this.mUsage = 65;
                    return;
                case 12:
                default:
                    this.mUsage = 0;
                    return;
                case 13:
                    this.mUsage = 18;
                    return;
            }
        }

        private void setFlags(AudioAttributes audio) {
            if ((audio.getAllFlags() & 64) != 0) {
                this.mFlags |= 1;
            }
        }

        public VibrationAttributes build() {
            VibrationAttributes ans = new VibrationAttributes(this.mUsage, this.mOriginalAudioUsage, this.mFlags);
            return ans;
        }

        public Builder setUsage(int usage) {
            this.mOriginalAudioUsage = 0;
            this.mUsage = usage;
            return this;
        }

        public Builder setFlags(int flags, int mask) {
            int mask2 = mask & 1;
            this.mFlags = (this.mFlags & (~mask2)) | (flags & mask2);
            return this;
        }
    }
}
