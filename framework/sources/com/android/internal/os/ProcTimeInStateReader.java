package com.android.internal.os;

import android.os.Process;
import java.io.IOException;
import java.nio.file.Path;
/* loaded from: classes4.dex */
public class ProcTimeInStateReader {
    private static final String TAG = "ProcTimeInStateReader";
    private long[] mFrequenciesKhz;
    private int[] mTimeInStateTimeFormat;
    private static final int[] TIME_IN_STATE_LINE_FREQUENCY_FORMAT = {8224, 10};
    private static final int[] TIME_IN_STATE_LINE_TIME_FORMAT = {32, 8202};
    private static final int[] TIME_IN_STATE_HEADER_LINE_FORMAT = {10};

    public ProcTimeInStateReader(Path initialTimeInStateFile) throws IOException {
        initializeTimeInStateFormat(initialTimeInStateFile);
    }

    public long[] getUsageTimesMillis(Path timeInStatePath) {
        long[] readLongs = new long[this.mFrequenciesKhz.length];
        boolean readSuccess = Process.readProcFile(timeInStatePath.toString(), this.mTimeInStateTimeFormat, null, readLongs, null);
        if (!readSuccess) {
            return null;
        }
        for (int i = 0; i < readLongs.length; i++) {
            readLongs[i] = readLongs[i] * 10;
        }
        return readLongs;
    }

    public long[] getFrequenciesKhz() {
        return this.mFrequenciesKhz;
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0014 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initializeTimeInStateFormat(java.nio.file.Path r13) throws java.io.IOException {
        /*
            r12 = this;
            byte[] r7 = java.nio.file.Files.readAllBytes(r13)
            android.util.IntArray r0 = new android.util.IntArray
            r0.<init>()
            r8 = r0
            android.util.IntArray r0 = new android.util.IntArray
            r0.<init>()
            r9 = r0
            r0 = 0
            r1 = 0
            r10 = r0
        L_0x0013:
            int r0 = r7.length
            if (r1 >= r0) goto L_0x0042
            r0 = r7[r1]
            boolean r0 = java.lang.Character.isDigit(r0)
            if (r0 != 0) goto L_0x0027
            int[] r0 = com.android.internal.os.ProcTimeInStateReader.TIME_IN_STATE_HEADER_LINE_FORMAT
            r8.addAll(r0)
            r9.addAll(r0)
            goto L_0x0033
        L_0x0027:
            int[] r0 = com.android.internal.os.ProcTimeInStateReader.TIME_IN_STATE_LINE_FREQUENCY_FORMAT
            r8.addAll(r0)
            int[] r0 = com.android.internal.os.ProcTimeInStateReader.TIME_IN_STATE_LINE_TIME_FORMAT
            r9.addAll(r0)
            int r10 = r10 + 1
        L_0x0033:
            int r0 = r7.length
            if (r1 >= r0) goto L_0x003f
            r0 = r7[r1]
            r2 = 10
            if (r0 == r2) goto L_0x003f
            int r1 = r1 + 1
            goto L_0x0033
        L_0x003f:
            int r1 = r1 + 1
            goto L_0x0013
        L_0x0042:
            if (r10 == 0) goto L_0x0067
            long[] r11 = new long[r10]
            r1 = 0
            int r2 = r7.length
            int[] r3 = r8.toArray()
            r4 = 0
            r6 = 0
            r0 = r7
            r5 = r11
            boolean r0 = android.os.Process.parseProcLine(r0, r1, r2, r3, r4, r5, r6)
            if (r0 == 0) goto L_0x005f
            int[] r1 = r9.toArray()
            r12.mTimeInStateTimeFormat = r1
            r12.mFrequenciesKhz = r11
            return
        L_0x005f:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Failed to parse time_in_state file"
            r1.<init>(r2)
            throw r1
        L_0x0067:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Empty time_in_state file"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.ProcTimeInStateReader.initializeTimeInStateFormat(java.nio.file.Path):void");
    }
}
