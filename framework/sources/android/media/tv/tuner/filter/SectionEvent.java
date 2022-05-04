package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
@SystemApi
/* loaded from: classes2.dex */
public class SectionEvent extends FilterEvent {
    private final int mDataLength;
    private final int mSectionNum;
    private final int mTableId;
    private final int mVersion;

    private SectionEvent(int tableId, int version, int sectionNum, int dataLength) {
        this.mTableId = tableId;
        this.mVersion = version;
        this.mSectionNum = sectionNum;
        this.mDataLength = dataLength;
    }

    public int getTableId() {
        return this.mTableId;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public int getSectionNumber() {
        return this.mSectionNum;
    }

    public int getDataLength() {
        return this.mDataLength;
    }
}
