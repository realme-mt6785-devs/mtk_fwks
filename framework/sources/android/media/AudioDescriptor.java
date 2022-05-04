package android.media;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public class AudioDescriptor {
    public static final int STANDARD_EDID = 1;
    public static final int STANDARD_NONE = 0;
    private final byte[] mDescriptor;
    private final int mEncapsulationType;
    private final int mStandard;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AudioDescriptorStandard {
    }

    AudioDescriptor(int standard, int encapsulationType, byte[] descriptor) {
        this.mStandard = standard;
        this.mEncapsulationType = encapsulationType;
        this.mDescriptor = descriptor;
    }

    public int getStandard() {
        return this.mStandard;
    }

    public byte[] getDescriptor() {
        return this.mDescriptor;
    }

    public int getEncapsulationType() {
        return this.mEncapsulationType;
    }
}
