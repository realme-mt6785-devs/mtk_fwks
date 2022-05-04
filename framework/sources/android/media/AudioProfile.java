package android.media;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.stream.Collectors;
/* loaded from: classes2.dex */
public class AudioProfile {
    public static final int AUDIO_ENCAPSULATION_TYPE_IEC61937 = 1;
    public static final int AUDIO_ENCAPSULATION_TYPE_NONE = 0;
    private final int[] mChannelIndexMasks;
    private final int[] mChannelMasks;
    private final int mEncapsulationType;
    private final int mFormat;
    private final int[] mSamplingRates;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface EncapsulationType {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioProfile(int format, int[] samplingRates, int[] channelMasks, int[] channelIndexMasks, int encapsulationType) {
        this.mFormat = format;
        this.mSamplingRates = samplingRates;
        this.mChannelMasks = channelMasks;
        this.mChannelIndexMasks = channelIndexMasks;
        this.mEncapsulationType = encapsulationType;
    }

    public int getFormat() {
        return this.mFormat;
    }

    public int[] getChannelMasks() {
        return this.mChannelMasks;
    }

    public int[] getChannelIndexMasks() {
        return this.mChannelIndexMasks;
    }

    public int[] getSampleRates() {
        return this.mSamplingRates;
    }

    public int getEncapsulationType() {
        return this.mEncapsulationType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append(AudioFormat.toLogFriendlyEncoding(this.mFormat));
        int[] iArr = this.mSamplingRates;
        if (iArr != null && iArr.length > 0) {
            sb.append(", sampling rates=");
            sb.append(Arrays.toString(this.mSamplingRates));
        }
        int[] iArr2 = this.mChannelMasks;
        if (iArr2 != null && iArr2.length > 0) {
            sb.append(", channel masks=");
            sb.append(toHexString(this.mChannelMasks));
        }
        int[] iArr3 = this.mChannelIndexMasks;
        if (iArr3 != null && iArr3.length > 0) {
            sb.append(", channel index masks=");
            sb.append(Arrays.toString(this.mChannelIndexMasks));
        }
        sb.append("}");
        return sb.toString();
    }

    private static String toHexString(int[] ints) {
        if (ints == null || ints.length == 0) {
            return "";
        }
        return (String) Arrays.stream(ints).mapToObj(AudioProfile$$ExternalSyntheticLambda0.INSTANCE).collect(Collectors.joining(", "));
    }
}
