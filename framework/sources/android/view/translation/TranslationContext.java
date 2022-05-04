package android.view.translation;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.BitUtils;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes3.dex */
public final class TranslationContext implements Parcelable {
    public static final Parcelable.Creator<TranslationContext> CREATOR = new Parcelable.Creator<TranslationContext>() { // from class: android.view.translation.TranslationContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TranslationContext[] newArray(int size) {
            return new TranslationContext[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TranslationContext createFromParcel(Parcel in) {
            return new TranslationContext(in);
        }
    };
    public static final int FLAG_DEFINITIONS = 4;
    public static final int FLAG_LOW_LATENCY = 1;
    public static final int FLAG_TRANSLITERATION = 2;
    private final TranslationSpec mSourceSpec;
    private final TranslationSpec mTargetSpec;
    private final int mTranslationFlags;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface TranslationFlag {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int defaultTranslationFlags() {
        return 0;
    }

    /* loaded from: classes3.dex */
    static abstract class BaseBuilder {
        BaseBuilder() {
        }
    }

    public static String translationFlagToString(int value) {
        return BitUtils.flagsToString(value, TranslationContext$$ExternalSyntheticLambda0.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String singleTranslationFlagToString(int value) {
        switch (value) {
            case 1:
                return "FLAG_LOW_LATENCY";
            case 2:
                return "FLAG_TRANSLITERATION";
            case 3:
            default:
                return Integer.toHexString(value);
            case 4:
                return "FLAG_DEFINITIONS";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TranslationContext(TranslationSpec sourceSpec, TranslationSpec targetSpec, int translationFlags) {
        this.mSourceSpec = sourceSpec;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) sourceSpec);
        this.mTargetSpec = targetSpec;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) targetSpec);
        this.mTranslationFlags = translationFlags;
        Preconditions.checkFlagsArgument(translationFlags, 7);
    }

    public TranslationSpec getSourceSpec() {
        return this.mSourceSpec;
    }

    public TranslationSpec getTargetSpec() {
        return this.mTargetSpec;
    }

    public int getTranslationFlags() {
        return this.mTranslationFlags;
    }

    public String toString() {
        return "TranslationContext { sourceSpec = " + this.mSourceSpec + ", targetSpec = " + this.mTargetSpec + ", translationFlags = " + translationFlagToString(this.mTranslationFlags) + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedObject(this.mSourceSpec, flags);
        dest.writeTypedObject(this.mTargetSpec, flags);
        dest.writeInt(this.mTranslationFlags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    TranslationContext(Parcel in) {
        TranslationSpec sourceSpec = (TranslationSpec) in.readTypedObject(TranslationSpec.CREATOR);
        TranslationSpec targetSpec = (TranslationSpec) in.readTypedObject(TranslationSpec.CREATOR);
        int translationFlags = in.readInt();
        this.mSourceSpec = sourceSpec;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) sourceSpec);
        this.mTargetSpec = targetSpec;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) targetSpec);
        this.mTranslationFlags = translationFlags;
        Preconditions.checkFlagsArgument(translationFlags, 7);
    }

    /* loaded from: classes3.dex */
    public static final class Builder extends BaseBuilder {
        private long mBuilderFieldsSet = 0;
        private TranslationSpec mSourceSpec;
        private TranslationSpec mTargetSpec;
        private int mTranslationFlags;

        public Builder(TranslationSpec sourceSpec, TranslationSpec targetSpec) {
            this.mSourceSpec = sourceSpec;
            AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) sourceSpec);
            this.mTargetSpec = targetSpec;
            AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) targetSpec);
        }

        public Builder setTranslationFlags(int value) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 4;
            this.mTranslationFlags = value;
            return this;
        }

        public TranslationContext build() {
            checkNotUsed();
            long j = this.mBuilderFieldsSet | 8;
            this.mBuilderFieldsSet = j;
            if ((j & 4) == 0) {
                this.mTranslationFlags = TranslationContext.defaultTranslationFlags();
            }
            TranslationContext o = new TranslationContext(this.mSourceSpec, this.mTargetSpec, this.mTranslationFlags);
            return o;
        }

        private void checkNotUsed() {
            if ((this.mBuilderFieldsSet & 8) != 0) {
                throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
            }
        }
    }

    @Deprecated
    private void __metadata() {
    }
}
