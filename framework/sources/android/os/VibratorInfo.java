package android.os;

import android.os.Parcelable;
import android.os.VibrationEffect;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.util.MathUtils;
import android.util.Range;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public class VibratorInfo implements Parcelable {
    private static final String TAG = "VibratorInfo";
    private final long mCapabilities;
    private final int mCompositionSizeMax;
    private final FrequencyMapping mFrequencyMapping;
    private final int mId;
    private final int mPrimitiveDelayMax;
    private final int mPwlePrimitiveDurationMax;
    private final int mPwleSizeMax;
    private final float mQFactor;
    private final SparseBooleanArray mSupportedBraking;
    private final SparseBooleanArray mSupportedEffects;
    private final SparseIntArray mSupportedPrimitives;
    public static final VibratorInfo EMPTY_VIBRATOR_INFO = new Builder(-1).build();
    public static final Parcelable.Creator<VibratorInfo> CREATOR = new Parcelable.Creator<VibratorInfo>() { // from class: android.os.VibratorInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibratorInfo createFromParcel(Parcel in) {
            return new VibratorInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VibratorInfo[] newArray(int size) {
            return new VibratorInfo[size];
        }
    };

    VibratorInfo(Parcel in) {
        this.mId = in.readInt();
        this.mCapabilities = in.readLong();
        this.mSupportedEffects = in.readSparseBooleanArray();
        this.mSupportedBraking = in.readSparseBooleanArray();
        this.mSupportedPrimitives = in.readSparseIntArray();
        this.mPrimitiveDelayMax = in.readInt();
        this.mCompositionSizeMax = in.readInt();
        this.mPwlePrimitiveDurationMax = in.readInt();
        this.mPwleSizeMax = in.readInt();
        this.mQFactor = in.readFloat();
        this.mFrequencyMapping = (FrequencyMapping) in.readParcelable(VibratorInfo.class.getClassLoader());
    }

    public VibratorInfo(int id, long capabilities, SparseBooleanArray supportedEffects, SparseBooleanArray supportedBraking, SparseIntArray supportedPrimitives, int primitiveDelayMax, int compositionSizeMax, int pwlePrimitiveDurationMax, int pwleSizeMax, float qFactor, FrequencyMapping frequencyMapping) {
        this.mId = id;
        this.mCapabilities = capabilities;
        SparseBooleanArray sparseBooleanArray = null;
        this.mSupportedEffects = supportedEffects == null ? null : supportedEffects.clone();
        this.mSupportedBraking = supportedBraking != null ? supportedBraking.clone() : sparseBooleanArray;
        this.mSupportedPrimitives = supportedPrimitives.clone();
        this.mPrimitiveDelayMax = primitiveDelayMax;
        this.mCompositionSizeMax = compositionSizeMax;
        this.mPwlePrimitiveDurationMax = pwlePrimitiveDurationMax;
        this.mPwleSizeMax = pwleSizeMax;
        this.mQFactor = qFactor;
        this.mFrequencyMapping = frequencyMapping;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VibratorInfo(int id, int capabilities, VibratorInfo baseVibrator) {
        this(id, capabilities, baseVibrator.mSupportedEffects, baseVibrator.mSupportedBraking, baseVibrator.mSupportedPrimitives, baseVibrator.mPrimitiveDelayMax, baseVibrator.mCompositionSizeMax, baseVibrator.mPwlePrimitiveDurationMax, baseVibrator.mPwleSizeMax, baseVibrator.mQFactor, baseVibrator.mFrequencyMapping);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeLong(this.mCapabilities);
        dest.writeSparseBooleanArray(this.mSupportedEffects);
        dest.writeSparseBooleanArray(this.mSupportedBraking);
        dest.writeSparseIntArray(this.mSupportedPrimitives);
        dest.writeInt(this.mPrimitiveDelayMax);
        dest.writeInt(this.mCompositionSizeMax);
        dest.writeInt(this.mPwlePrimitiveDurationMax);
        dest.writeInt(this.mPwleSizeMax);
        dest.writeFloat(this.mQFactor);
        dest.writeParcelable(this.mFrequencyMapping, flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VibratorInfo)) {
            return false;
        }
        VibratorInfo that = (VibratorInfo) o;
        int supportedPrimitivesCount = this.mSupportedPrimitives.size();
        if (supportedPrimitivesCount != that.mSupportedPrimitives.size()) {
            return false;
        }
        for (int i = 0; i < supportedPrimitivesCount; i++) {
            if (!(this.mSupportedPrimitives.keyAt(i) == that.mSupportedPrimitives.keyAt(i) && this.mSupportedPrimitives.valueAt(i) == that.mSupportedPrimitives.valueAt(i))) {
                return false;
            }
        }
        int i2 = this.mId;
        return i2 == that.mId && this.mCapabilities == that.mCapabilities && this.mPrimitiveDelayMax == that.mPrimitiveDelayMax && this.mCompositionSizeMax == that.mCompositionSizeMax && this.mPwlePrimitiveDurationMax == that.mPwlePrimitiveDurationMax && this.mPwleSizeMax == that.mPwleSizeMax && Objects.equals(this.mSupportedEffects, that.mSupportedEffects) && Objects.equals(this.mSupportedBraking, that.mSupportedBraking) && Objects.equals(Float.valueOf(this.mQFactor), Float.valueOf(that.mQFactor)) && Objects.equals(this.mFrequencyMapping, that.mFrequencyMapping);
    }

    public int hashCode() {
        int hashCode = Objects.hash(Integer.valueOf(this.mId), Long.valueOf(this.mCapabilities), this.mSupportedEffects, this.mSupportedBraking, Float.valueOf(this.mQFactor), this.mFrequencyMapping);
        for (int i = 0; i < this.mSupportedPrimitives.size(); i++) {
            hashCode = (((hashCode * 31) + this.mSupportedPrimitives.keyAt(i)) * 31) + this.mSupportedPrimitives.valueAt(i);
        }
        return hashCode;
    }

    public String toString() {
        return "VibratorInfo{mId=" + this.mId + ", mCapabilities=" + Arrays.toString(getCapabilitiesNames()) + ", mCapabilities flags=" + Long.toBinaryString(this.mCapabilities) + ", mSupportedEffects=" + Arrays.toString(getSupportedEffectsNames()) + ", mSupportedBraking=" + Arrays.toString(getSupportedBrakingNames()) + ", mSupportedPrimitives=" + Arrays.toString(getSupportedPrimitivesNames()) + ", mPrimitiveDelayMax=" + this.mPrimitiveDelayMax + ", mCompositionSizeMax=" + this.mCompositionSizeMax + ", mPwlePrimitiveDurationMax=" + this.mPwlePrimitiveDurationMax + ", mPwleSizeMax=" + this.mPwleSizeMax + ", mQFactor=" + this.mQFactor + ", mFrequencyMapping=" + this.mFrequencyMapping + '}';
    }

    public int getId() {
        return this.mId;
    }

    public boolean hasAmplitudeControl() {
        return hasCapability(4L);
    }

    public int getDefaultBraking() {
        SparseBooleanArray sparseBooleanArray = this.mSupportedBraking;
        if (sparseBooleanArray == null) {
            return 0;
        }
        int size = sparseBooleanArray.size();
        for (int i = 0; i < size; i++) {
            if (this.mSupportedBraking.keyAt(i) != 0) {
                return this.mSupportedBraking.keyAt(i);
            }
        }
        return 0;
    }

    public int isEffectSupported(int effectId) {
        SparseBooleanArray sparseBooleanArray = this.mSupportedEffects;
        if (sparseBooleanArray == null) {
            return 0;
        }
        return sparseBooleanArray.get(effectId) ? 1 : 2;
    }

    public boolean isPrimitiveSupported(int primitiveId) {
        return hasCapability(32L) && this.mSupportedPrimitives.indexOfKey(primitiveId) >= 0;
    }

    public int getPrimitiveDuration(int primitiveId) {
        return this.mSupportedPrimitives.get(primitiveId);
    }

    public int getPrimitiveDelayMax() {
        return this.mPrimitiveDelayMax;
    }

    public int getCompositionSizeMax() {
        return this.mCompositionSizeMax;
    }

    public int getPwlePrimitiveDurationMax() {
        return this.mPwlePrimitiveDurationMax;
    }

    public int getPwleSizeMax() {
        return this.mPwleSizeMax;
    }

    public boolean hasCapability(long capability) {
        return (this.mCapabilities & capability) == capability;
    }

    public float getResonantFrequency() {
        return this.mFrequencyMapping.mResonantFrequencyHz;
    }

    public float getQFactor() {
        return this.mQFactor;
    }

    public Range<Float> getFrequencyRange() {
        return this.mFrequencyMapping.mRelativeFrequencyRange;
    }

    public float getMaxAmplitude(float relativeFrequency) {
        if (this.mFrequencyMapping.isEmpty()) {
            return Float.compare(relativeFrequency, 0.0f) == 0 ? 1.0f : 0.0f;
        }
        return this.mFrequencyMapping.getMaxAmplitude(relativeFrequency);
    }

    public float getAbsoluteFrequency(float relativeFrequency) {
        return this.mFrequencyMapping.toHertz(relativeFrequency);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getCapabilities() {
        return this.mCapabilities;
    }

    private String[] getCapabilitiesNames() {
        List<String> names = new ArrayList<>();
        if (hasCapability(1L)) {
            names.add("ON_CALLBACK");
        }
        if (hasCapability(2L)) {
            names.add("PERFORM_CALLBACK");
        }
        if (hasCapability(32L)) {
            names.add("COMPOSE_EFFECTS");
        }
        if (hasCapability(1024L)) {
            names.add("COMPOSE_PWLE_EFFECTS");
        }
        if (hasCapability(64L)) {
            names.add("ALWAYS_ON_CONTROL");
        }
        if (hasCapability(4L)) {
            names.add("AMPLITUDE_CONTROL");
        }
        if (hasCapability(512L)) {
            names.add("FREQUENCY_CONTROL");
        }
        if (hasCapability(8L)) {
            names.add("EXTERNAL_CONTROL");
        }
        if (hasCapability(16L)) {
            names.add("EXTERNAL_AMPLITUDE_CONTROL");
        }
        return (String[]) names.toArray(new String[names.size()]);
    }

    private String[] getSupportedEffectsNames() {
        SparseBooleanArray sparseBooleanArray = this.mSupportedEffects;
        if (sparseBooleanArray == null) {
            return new String[0];
        }
        String[] names = new String[sparseBooleanArray.size()];
        for (int i = 0; i < this.mSupportedEffects.size(); i++) {
            names[i] = VibrationEffect.effectIdToString(this.mSupportedEffects.keyAt(i));
        }
        return names;
    }

    private String[] getSupportedBrakingNames() {
        SparseBooleanArray sparseBooleanArray = this.mSupportedBraking;
        if (sparseBooleanArray == null) {
            return new String[0];
        }
        String[] names = new String[sparseBooleanArray.size()];
        for (int i = 0; i < this.mSupportedBraking.size(); i++) {
            switch (this.mSupportedBraking.keyAt(i)) {
                case 0:
                    names[i] = KeyProperties.DIGEST_NONE;
                    break;
                case 1:
                    names[i] = "CLAB";
                    break;
                default:
                    names[i] = Integer.toString(this.mSupportedBraking.keyAt(i));
                    break;
            }
        }
        return names;
    }

    private String[] getSupportedPrimitivesNames() {
        int supportedPrimitivesCount = this.mSupportedPrimitives.size();
        String[] names = new String[supportedPrimitivesCount];
        for (int i = 0; i < supportedPrimitivesCount; i++) {
            names[i] = VibrationEffect.Composition.primitiveToString(this.mSupportedPrimitives.keyAt(i));
        }
        return names;
    }

    /* loaded from: classes2.dex */
    public static final class FrequencyMapping implements Parcelable {
        public static final Parcelable.Creator<FrequencyMapping> CREATOR = new Parcelable.Creator<FrequencyMapping>() { // from class: android.os.VibratorInfo.FrequencyMapping.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FrequencyMapping createFromParcel(Parcel in) {
                return new FrequencyMapping(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FrequencyMapping[] newArray(int size) {
                return new FrequencyMapping[size];
            }
        };
        private final float mFrequencyResolutionHz;
        private final float[] mMaxAmplitudes;
        private final float mMinFrequencyHz;
        private final Range<Float> mRelativeFrequencyRange;
        private final float mResonantFrequencyHz;
        private final float mSuggestedSafeRangeHz;

        FrequencyMapping(Parcel in) {
            this(in.readFloat(), in.readFloat(), in.readFloat(), in.readFloat(), in.createFloatArray());
        }

        public FrequencyMapping(float minFrequencyHz, float resonantFrequencyHz, float frequencyResolutionHz, float suggestedSafeRangeHz, float[] maxAmplitudes) {
            this.mMinFrequencyHz = minFrequencyHz;
            this.mResonantFrequencyHz = resonantFrequencyHz;
            this.mFrequencyResolutionHz = frequencyResolutionHz;
            this.mSuggestedSafeRangeHz = suggestedSafeRangeHz;
            float[] fArr = new float[maxAmplitudes == null ? 0 : maxAmplitudes.length];
            this.mMaxAmplitudes = fArr;
            if (maxAmplitudes != null) {
                System.arraycopy(maxAmplitudes, 0, fArr, 0, maxAmplitudes.length);
            }
            float maxFrequencyHz = ((fArr.length - 1) * frequencyResolutionHz) + minFrequencyHz;
            if (Float.isNaN(resonantFrequencyHz) || Float.isNaN(minFrequencyHz) || Float.isNaN(frequencyResolutionHz) || Float.isNaN(suggestedSafeRangeHz) || resonantFrequencyHz < minFrequencyHz || resonantFrequencyHz > maxFrequencyHz) {
                this.mRelativeFrequencyRange = Range.create(Float.valueOf(0.0f), Float.valueOf(0.0f));
                return;
            }
            float safeDelta = MathUtils.min(suggestedSafeRangeHz / 2.0f, resonantFrequencyHz - minFrequencyHz, maxFrequencyHz - resonantFrequencyHz);
            this.mRelativeFrequencyRange = Range.create(Float.valueOf((minFrequencyHz - resonantFrequencyHz) / safeDelta), Float.valueOf((maxFrequencyHz - resonantFrequencyHz) / safeDelta));
        }

        public boolean isEmpty() {
            return Float.compare(this.mRelativeFrequencyRange.getLower().floatValue(), this.mRelativeFrequencyRange.getUpper().floatValue()) == 0;
        }

        public float toHertz(float relativeFrequency) {
            if (!this.mRelativeFrequencyRange.contains((Range<Float>) Float.valueOf(relativeFrequency))) {
                return Float.NaN;
            }
            float relativeMinFrequency = this.mRelativeFrequencyRange.getLower().floatValue();
            if (Float.compare(relativeMinFrequency, 0.0f) == 0) {
                return this.mResonantFrequencyHz;
            }
            float f = this.mMinFrequencyHz;
            float f2 = this.mResonantFrequencyHz;
            float shift = (f - f2) / relativeMinFrequency;
            return f2 + (relativeFrequency * shift);
        }

        public float getMaxAmplitude(float relativeFrequency) {
            float frequencyHz = toHertz(relativeFrequency);
            if (Float.isNaN(frequencyHz)) {
                return 0.0f;
            }
            float position = (frequencyHz - this.mMinFrequencyHz) / this.mFrequencyResolutionHz;
            int floorIndex = (int) Math.floor(position);
            int ceilIndex = (int) Math.ceil(position);
            if (floorIndex >= 0) {
                float[] fArr = this.mMaxAmplitudes;
                if (floorIndex < fArr.length) {
                    if (floorIndex == ceilIndex || ceilIndex >= fArr.length) {
                        return fArr[floorIndex];
                    }
                    return MathUtils.min(fArr[floorIndex], fArr[ceilIndex]);
                }
            }
            if (this.mMaxAmplitudes.length > 0) {
                Log.w(VibratorInfo.TAG, "Max amplitudes has " + this.mMaxAmplitudes.length + " entries and was expected to cover the frequency " + frequencyHz + " Hz when starting at min frequency of " + this.mMinFrequencyHz + " Hz with resolution of " + this.mFrequencyResolutionHz + " Hz.");
            }
            return 0.0f;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeFloat(this.mMinFrequencyHz);
            dest.writeFloat(this.mResonantFrequencyHz);
            dest.writeFloat(this.mFrequencyResolutionHz);
            dest.writeFloat(this.mSuggestedSafeRangeHz);
            dest.writeFloatArray(this.mMaxAmplitudes);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof FrequencyMapping)) {
                return false;
            }
            FrequencyMapping that = (FrequencyMapping) o;
            return Float.compare(this.mMinFrequencyHz, that.mMinFrequencyHz) == 0 && Float.compare(this.mResonantFrequencyHz, that.mResonantFrequencyHz) == 0 && Float.compare(this.mFrequencyResolutionHz, that.mFrequencyResolutionHz) == 0 && Float.compare(this.mSuggestedSafeRangeHz, that.mSuggestedSafeRangeHz) == 0 && Arrays.equals(this.mMaxAmplitudes, that.mMaxAmplitudes);
        }

        public int hashCode() {
            int hashCode = Objects.hash(Float.valueOf(this.mMinFrequencyHz), Float.valueOf(this.mFrequencyResolutionHz), Float.valueOf(this.mFrequencyResolutionHz), Float.valueOf(this.mSuggestedSafeRangeHz));
            return (hashCode * 31) + Arrays.hashCode(this.mMaxAmplitudes);
        }

        public String toString() {
            return "FrequencyMapping{mRelativeFrequencyRange=" + this.mRelativeFrequencyRange + ", mMinFrequency=" + this.mMinFrequencyHz + ", mResonantFrequency=" + this.mResonantFrequencyHz + ", mMaxFrequency=" + (this.mMinFrequencyHz + (this.mFrequencyResolutionHz * (this.mMaxAmplitudes.length - 1))) + ", mFrequencyResolution=" + this.mFrequencyResolutionHz + ", mSuggestedSafeRange=" + this.mSuggestedSafeRangeHz + ", mMaxAmplitudes count=" + this.mMaxAmplitudes.length + '}';
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private long mCapabilities;
        private int mCompositionSizeMax;
        private final int mId;
        private int mPrimitiveDelayMax;
        private int mPwlePrimitiveDurationMax;
        private int mPwleSizeMax;
        private SparseBooleanArray mSupportedBraking;
        private SparseBooleanArray mSupportedEffects;
        private SparseIntArray mSupportedPrimitives = new SparseIntArray();
        private float mQFactor = Float.NaN;
        private FrequencyMapping mFrequencyMapping = new FrequencyMapping(Float.NaN, Float.NaN, Float.NaN, Float.NaN, null);

        public Builder(int id) {
            this.mId = id;
        }

        public Builder setCapabilities(long capabilities) {
            this.mCapabilities = capabilities;
            return this;
        }

        public Builder setSupportedEffects(int... supportedEffects) {
            this.mSupportedEffects = toSparseBooleanArray(supportedEffects);
            return this;
        }

        public Builder setSupportedBraking(int... supportedBraking) {
            this.mSupportedBraking = toSparseBooleanArray(supportedBraking);
            return this;
        }

        public Builder setPwlePrimitiveDurationMax(int pwlePrimitiveDurationMax) {
            this.mPwlePrimitiveDurationMax = pwlePrimitiveDurationMax;
            return this;
        }

        public Builder setPwleSizeMax(int pwleSizeMax) {
            this.mPwleSizeMax = pwleSizeMax;
            return this;
        }

        public Builder setSupportedPrimitive(int primitiveId, int duration) {
            this.mSupportedPrimitives.put(primitiveId, duration);
            return this;
        }

        public Builder setPrimitiveDelayMax(int primitiveDelayMax) {
            this.mPrimitiveDelayMax = primitiveDelayMax;
            return this;
        }

        public Builder setCompositionSizeMax(int compositionSizeMax) {
            this.mCompositionSizeMax = compositionSizeMax;
            return this;
        }

        public Builder setQFactor(float qFactor) {
            this.mQFactor = qFactor;
            return this;
        }

        public Builder setFrequencyMapping(FrequencyMapping frequencyMapping) {
            this.mFrequencyMapping = frequencyMapping;
            return this;
        }

        public VibratorInfo build() {
            return new VibratorInfo(this.mId, this.mCapabilities, this.mSupportedEffects, this.mSupportedBraking, this.mSupportedPrimitives, this.mPrimitiveDelayMax, this.mCompositionSizeMax, this.mPwlePrimitiveDurationMax, this.mPwleSizeMax, this.mQFactor, this.mFrequencyMapping);
        }

        private static SparseBooleanArray toSparseBooleanArray(int[] supportedKeys) {
            if (supportedKeys == null) {
                return null;
            }
            SparseBooleanArray array = new SparseBooleanArray();
            for (int key : supportedKeys) {
                array.put(key, true);
            }
            return array;
        }
    }
}
