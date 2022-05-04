package com.android.internal.os;
/* loaded from: classes4.dex */
public final class DmabufInfoReader {
    public static native ProcessDmabuf getProcessStats(int i);

    private DmabufInfoReader() {
    }

    /* loaded from: classes4.dex */
    public static final class ProcessDmabuf {
        public final int mappedBuffersCount;
        public final int mappedSizeKb;
        public final int retainedBuffersCount;
        public final int retainedSizeKb;

        ProcessDmabuf(int retainedSizeKb, int retainedBuffersCount, int mappedSizeKb, int mappedBuffersCount) {
            this.retainedSizeKb = retainedSizeKb;
            this.retainedBuffersCount = retainedBuffersCount;
            this.mappedSizeKb = mappedSizeKb;
            this.mappedBuffersCount = mappedBuffersCount;
        }
    }
}
