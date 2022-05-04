package android.os;

import android.os.Parcelable;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public abstract class CombinedVibration implements Parcelable {
    public static final Parcelable.Creator<CombinedVibration> CREATOR = new Parcelable.Creator<CombinedVibration>() { // from class: android.os.CombinedVibration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CombinedVibration createFromParcel(Parcel in) {
            int token = in.readInt();
            if (token == 1) {
                return new Mono(in);
            }
            if (token == 2) {
                return new Stereo(in);
            }
            if (token == 3) {
                return new Sequential(in);
            }
            throw new IllegalStateException("Unexpected combined vibration event type token in parcel.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CombinedVibration[] newArray(int size) {
            return new CombinedVibration[size];
        }
    };
    private static final int PARCEL_TOKEN_MONO = 1;
    private static final int PARCEL_TOKEN_SEQUENTIAL = 3;
    private static final int PARCEL_TOKEN_STEREO = 2;

    public abstract long getDuration();

    public abstract boolean hasVibrator(int i);

    public abstract void validate();

    CombinedVibration() {
    }

    public static CombinedVibration createParallel(VibrationEffect effect) {
        CombinedVibration combined = new Mono(effect);
        combined.validate();
        return combined;
    }

    public static ParallelCombination startParallel() {
        return new ParallelCombination();
    }

    public static SequentialCombination startSequential() {
        return new SequentialCombination();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* loaded from: classes2.dex */
    public static final class ParallelCombination {
        private final SparseArray<VibrationEffect> mEffects = new SparseArray<>();

        ParallelCombination() {
        }

        public ParallelCombination addVibrator(int vibratorId, VibrationEffect effect) {
            this.mEffects.put(vibratorId, effect);
            return this;
        }

        public CombinedVibration combine() {
            if (this.mEffects.size() != 0) {
                CombinedVibration combined = new Stereo(this.mEffects);
                combined.validate();
                return combined;
            }
            throw new IllegalStateException("Combination must have at least one element to combine.");
        }
    }

    /* loaded from: classes2.dex */
    public static final class SequentialCombination {
        private final ArrayList<CombinedVibration> mEffects = new ArrayList<>();
        private final ArrayList<Integer> mDelays = new ArrayList<>();

        SequentialCombination() {
        }

        public SequentialCombination addNext(int vibratorId, VibrationEffect effect) {
            return addNext(vibratorId, effect, 0);
        }

        public SequentialCombination addNext(int vibratorId, VibrationEffect effect, int delay) {
            return addNext(CombinedVibration.startParallel().addVibrator(vibratorId, effect).combine(), delay);
        }

        public SequentialCombination addNext(CombinedVibration effect) {
            return addNext(effect, 0);
        }

        public SequentialCombination addNext(CombinedVibration effect, int delay) {
            if (effect instanceof Sequential) {
                Sequential sequentialEffect = (Sequential) effect;
                int firstEffectIndex = this.mDelays.size();
                this.mEffects.addAll(sequentialEffect.getEffects());
                this.mDelays.addAll(sequentialEffect.getDelays());
                ArrayList<Integer> arrayList = this.mDelays;
                arrayList.set(firstEffectIndex, Integer.valueOf(arrayList.get(firstEffectIndex).intValue() + delay));
            } else {
                this.mEffects.add(effect);
                this.mDelays.add(Integer.valueOf(delay));
            }
            return this;
        }

        public CombinedVibration combine() {
            if (this.mEffects.size() != 0) {
                CombinedVibration combined = new Sequential(this.mEffects, this.mDelays);
                combined.validate();
                return combined;
            }
            throw new IllegalStateException("Combination must have at least one element to combine.");
        }
    }

    /* loaded from: classes2.dex */
    public static final class Mono extends CombinedVibration {
        public static final Parcelable.Creator<Mono> CREATOR = new Parcelable.Creator<Mono>() { // from class: android.os.CombinedVibration.Mono.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Mono createFromParcel(Parcel in) {
                in.readInt();
                return new Mono(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Mono[] newArray(int size) {
                return new Mono[size];
            }
        };
        private final VibrationEffect mEffect;

        Mono(Parcel in) {
            this.mEffect = VibrationEffect.CREATOR.createFromParcel(in);
        }

        Mono(VibrationEffect effect) {
            this.mEffect = effect;
        }

        public VibrationEffect getEffect() {
            return this.mEffect;
        }

        @Override // android.os.CombinedVibration
        public long getDuration() {
            return this.mEffect.getDuration();
        }

        @Override // android.os.CombinedVibration
        public void validate() {
            this.mEffect.validate();
        }

        @Override // android.os.CombinedVibration
        public boolean hasVibrator(int vibratorId) {
            return true;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Mono)) {
                return false;
            }
            Mono other = (Mono) o;
            return this.mEffect.equals(other.mEffect);
        }

        public int hashCode() {
            return Objects.hash(this.mEffect);
        }

        public String toString() {
            return "Mono{mEffect=" + this.mEffect + '}';
        }

        @Override // android.os.CombinedVibration, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(1);
            this.mEffect.writeToParcel(out, flags);
        }
    }

    /* loaded from: classes2.dex */
    public static final class Stereo extends CombinedVibration {
        public static final Parcelable.Creator<Stereo> CREATOR = new Parcelable.Creator<Stereo>() { // from class: android.os.CombinedVibration.Stereo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Stereo createFromParcel(Parcel in) {
                in.readInt();
                return new Stereo(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Stereo[] newArray(int size) {
                return new Stereo[size];
            }
        };
        private final SparseArray<VibrationEffect> mEffects;

        Stereo(Parcel in) {
            int size = in.readInt();
            this.mEffects = new SparseArray<>(size);
            for (int i = 0; i < size; i++) {
                int vibratorId = in.readInt();
                this.mEffects.put(vibratorId, VibrationEffect.CREATOR.createFromParcel(in));
            }
        }

        Stereo(SparseArray<VibrationEffect> effects) {
            this.mEffects = new SparseArray<>(effects.size());
            for (int i = 0; i < effects.size(); i++) {
                this.mEffects.put(effects.keyAt(i), effects.valueAt(i));
            }
        }

        public SparseArray<VibrationEffect> getEffects() {
            return this.mEffects;
        }

        @Override // android.os.CombinedVibration
        public long getDuration() {
            long maxDuration = Long.MIN_VALUE;
            boolean hasUnknownStep = false;
            for (int i = 0; i < this.mEffects.size(); i++) {
                long duration = this.mEffects.valueAt(i).getDuration();
                if (duration == Long.MAX_VALUE) {
                    return duration;
                }
                maxDuration = Math.max(maxDuration, duration);
                hasUnknownStep |= duration < 0;
            }
            if (hasUnknownStep) {
                return -1L;
            }
            return maxDuration;
        }

        @Override // android.os.CombinedVibration
        public void validate() {
            Preconditions.checkArgument(this.mEffects.size() > 0, "There should be at least one effect set for a combined effect");
            for (int i = 0; i < this.mEffects.size(); i++) {
                this.mEffects.valueAt(i).validate();
            }
        }

        @Override // android.os.CombinedVibration
        public boolean hasVibrator(int vibratorId) {
            return this.mEffects.indexOfKey(vibratorId) >= 0;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Stereo)) {
                return false;
            }
            Stereo other = (Stereo) o;
            if (this.mEffects.size() != other.mEffects.size()) {
                return false;
            }
            for (int i = 0; i < this.mEffects.size(); i++) {
                if (!this.mEffects.valueAt(i).equals(other.mEffects.get(this.mEffects.keyAt(i)))) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            return this.mEffects.contentHashCode();
        }

        public String toString() {
            return "Stereo{mEffects=" + this.mEffects + '}';
        }

        @Override // android.os.CombinedVibration, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(2);
            out.writeInt(this.mEffects.size());
            for (int i = 0; i < this.mEffects.size(); i++) {
                out.writeInt(this.mEffects.keyAt(i));
                this.mEffects.valueAt(i).writeToParcel(out, flags);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class Sequential extends CombinedVibration {
        public static final Parcelable.Creator<Sequential> CREATOR = new Parcelable.Creator<Sequential>() { // from class: android.os.CombinedVibration.Sequential.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Sequential createFromParcel(Parcel in) {
                in.readInt();
                return new Sequential(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Sequential[] newArray(int size) {
                return new Sequential[size];
            }
        };
        private final List<Integer> mDelays;
        private final List<CombinedVibration> mEffects;

        Sequential(Parcel in) {
            int size = in.readInt();
            this.mEffects = new ArrayList(size);
            this.mDelays = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                this.mDelays.add(Integer.valueOf(in.readInt()));
                this.mEffects.add(CombinedVibration.CREATOR.createFromParcel(in));
            }
        }

        Sequential(List<CombinedVibration> effects, List<Integer> delays) {
            this.mEffects = new ArrayList(effects);
            this.mDelays = new ArrayList(delays);
        }

        public List<CombinedVibration> getEffects() {
            return this.mEffects;
        }

        public List<Integer> getDelays() {
            return this.mDelays;
        }

        @Override // android.os.CombinedVibration
        public long getDuration() {
            boolean hasUnknownStep = false;
            long durations = 0;
            int effectCount = this.mEffects.size();
            for (int i = 0; i < effectCount; i++) {
                CombinedVibration effect = this.mEffects.get(i);
                long duration = effect.getDuration();
                if (duration == Long.MAX_VALUE) {
                    return duration;
                }
                durations += duration;
                hasUnknownStep |= duration < 0;
            }
            if (hasUnknownStep) {
                return -1L;
            }
            long delays = 0;
            for (int i2 = 0; i2 < effectCount; i2++) {
                delays += this.mDelays.get(i2).intValue();
            }
            return durations + delays;
        }

        @Override // android.os.CombinedVibration
        public void validate() {
            boolean z = false;
            Preconditions.checkArgument(this.mEffects.size() > 0, "There should be at least one effect set for a combined effect");
            if (this.mEffects.size() == this.mDelays.size()) {
                z = true;
            }
            Preconditions.checkArgument(z, "Effect and delays should have equal length");
            int effectCount = this.mEffects.size();
            for (int i = 0; i < effectCount; i++) {
                if (this.mDelays.get(i).intValue() < 0) {
                    throw new IllegalArgumentException("Delays must all be >= 0 (delays=" + this.mDelays + ")");
                }
            }
            for (int i2 = 0; i2 < effectCount; i2++) {
                CombinedVibration effect = this.mEffects.get(i2);
                if (!(effect instanceof Sequential)) {
                    effect.validate();
                } else {
                    throw new IllegalArgumentException("There should be no nested sequential effects in a combined effect");
                }
            }
        }

        @Override // android.os.CombinedVibration
        public boolean hasVibrator(int vibratorId) {
            int effectCount = this.mEffects.size();
            for (int i = 0; i < effectCount; i++) {
                if (this.mEffects.get(i).hasVibrator(vibratorId)) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Sequential)) {
                return false;
            }
            Sequential other = (Sequential) o;
            return this.mDelays.equals(other.mDelays) && this.mEffects.equals(other.mEffects);
        }

        public int hashCode() {
            return Objects.hash(this.mEffects, this.mDelays);
        }

        public String toString() {
            return "Sequential{mEffects=" + this.mEffects + ", mDelays=" + this.mDelays + '}';
        }

        @Override // android.os.CombinedVibration, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(3);
            out.writeInt(this.mEffects.size());
            for (int i = 0; i < this.mEffects.size(); i++) {
                out.writeInt(this.mDelays.get(i).intValue());
                this.mEffects.get(i).writeToParcel(out, flags);
            }
        }
    }
}
