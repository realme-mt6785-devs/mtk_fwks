package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerUtils;
@SystemApi
/* loaded from: classes2.dex */
public abstract class SectionSettings extends Settings {
    final boolean mCrcEnabled;
    final boolean mIsRaw;
    final boolean mIsRepeat;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SectionSettings(int mainType, boolean crcEnabled, boolean isRepeat, boolean isRaw) {
        super(TunerUtils.getFilterSubtype(mainType, 1));
        this.mCrcEnabled = crcEnabled;
        this.mIsRepeat = isRepeat;
        this.mIsRaw = isRaw;
    }

    public boolean isCrcEnabled() {
        return this.mCrcEnabled;
    }

    public boolean isRepeat() {
        return this.mIsRepeat;
    }

    public boolean isRaw() {
        return this.mIsRaw;
    }

    /* loaded from: classes2.dex */
    public static abstract class Builder<T extends Builder<T>> {
        boolean mCrcEnabled;
        boolean mIsRaw;
        boolean mIsRepeat;
        final int mMainType;

        abstract T self();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(int mainType) {
            this.mMainType = mainType;
        }

        public T setCrcEnabled(boolean crcEnabled) {
            this.mCrcEnabled = crcEnabled;
            return self();
        }

        public T setRepeat(boolean isRepeat) {
            this.mIsRepeat = isRepeat;
            return self();
        }

        public T setRaw(boolean isRaw) {
            this.mIsRaw = isRaw;
            return self();
        }
    }
}
