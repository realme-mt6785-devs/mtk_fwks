package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerUtils;
@SystemApi
/* loaded from: classes2.dex */
public class DownloadSettings extends Settings {
    private final int mDownloadId;

    private DownloadSettings(int mainType, int downloadId) {
        super(TunerUtils.getFilterSubtype(mainType, 5));
        this.mDownloadId = downloadId;
    }

    public int getDownloadId() {
        return this.mDownloadId;
    }

    public static Builder builder(int mainType) {
        return new Builder(mainType);
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mDownloadId;
        private final int mMainType;

        private Builder(int mainType) {
            this.mMainType = mainType;
        }

        public Builder setDownloadId(int downloadId) {
            this.mDownloadId = downloadId;
            return this;
        }

        public DownloadSettings build() {
            return new DownloadSettings(this.mMainType, this.mDownloadId);
        }
    }
}
