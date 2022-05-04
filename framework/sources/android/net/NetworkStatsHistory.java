package android.net;

import android.net.NetworkStats;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.MathUtils;
import android.util.proto.ProtoOutputStream;
import com.android.internal.net.NetworkUtilsInternal;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.IndentingPrintWriter;
import java.io.CharArrayWriter;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.util.Arrays;
import java.util.Random;
import libcore.util.EmptyArray;
/* loaded from: classes2.dex */
public class NetworkStatsHistory implements Parcelable {
    public static final Parcelable.Creator<NetworkStatsHistory> CREATOR = new Parcelable.Creator<NetworkStatsHistory>() { // from class: android.net.NetworkStatsHistory.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkStatsHistory createFromParcel(Parcel in) {
            return new NetworkStatsHistory(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkStatsHistory[] newArray(int size) {
            return new NetworkStatsHistory[size];
        }
    };
    public static final int FIELD_ACTIVE_TIME = 1;
    public static final int FIELD_ALL = -1;
    public static final int FIELD_OPERATIONS = 32;
    public static final int FIELD_RX_BYTES = 2;
    public static final int FIELD_RX_PACKETS = 4;
    public static final int FIELD_TX_BYTES = 8;
    public static final int FIELD_TX_PACKETS = 16;
    private static final int VERSION_ADD_ACTIVE = 3;
    private static final int VERSION_ADD_PACKETS = 2;
    private static final int VERSION_INIT = 1;
    private long[] activeTime;
    private int bucketCount;
    private long bucketDuration;
    private long[] bucketStart;
    private long[] operations;
    private long[] rxBytes;
    private long[] rxPackets;
    private long totalBytes;
    private long[] txBytes;
    private long[] txPackets;

    /* loaded from: classes2.dex */
    public static class Entry {
        public static final long UNKNOWN = -1;
        public long activeTime;
        public long bucketDuration;
        public long bucketStart;
        public long operations;
        public long rxBytes;
        public long rxPackets;
        public long txBytes;
        public long txPackets;
    }

    public NetworkStatsHistory(long bucketDuration) {
        this(bucketDuration, 10, -1);
    }

    public NetworkStatsHistory(long bucketDuration, int initialSize) {
        this(bucketDuration, initialSize, -1);
    }

    public NetworkStatsHistory(long bucketDuration, int initialSize, int fields) {
        this.bucketDuration = bucketDuration;
        this.bucketStart = new long[initialSize];
        if ((fields & 1) != 0) {
            this.activeTime = new long[initialSize];
        }
        if ((fields & 2) != 0) {
            this.rxBytes = new long[initialSize];
        }
        if ((fields & 4) != 0) {
            this.rxPackets = new long[initialSize];
        }
        if ((fields & 8) != 0) {
            this.txBytes = new long[initialSize];
        }
        if ((fields & 16) != 0) {
            this.txPackets = new long[initialSize];
        }
        if ((fields & 32) != 0) {
            this.operations = new long[initialSize];
        }
        this.bucketCount = 0;
        this.totalBytes = 0L;
    }

    public NetworkStatsHistory(NetworkStatsHistory existing, long bucketDuration) {
        this(bucketDuration, existing.estimateResizeBuckets(bucketDuration));
        recordEntireHistory(existing);
    }

    public NetworkStatsHistory(Parcel in) {
        this.bucketDuration = in.readLong();
        this.bucketStart = ParcelUtils.readLongArray(in);
        this.activeTime = ParcelUtils.readLongArray(in);
        this.rxBytes = ParcelUtils.readLongArray(in);
        this.rxPackets = ParcelUtils.readLongArray(in);
        this.txBytes = ParcelUtils.readLongArray(in);
        this.txPackets = ParcelUtils.readLongArray(in);
        this.operations = ParcelUtils.readLongArray(in);
        this.bucketCount = this.bucketStart.length;
        this.totalBytes = in.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(this.bucketDuration);
        ParcelUtils.writeLongArray(out, this.bucketStart, this.bucketCount);
        ParcelUtils.writeLongArray(out, this.activeTime, this.bucketCount);
        ParcelUtils.writeLongArray(out, this.rxBytes, this.bucketCount);
        ParcelUtils.writeLongArray(out, this.rxPackets, this.bucketCount);
        ParcelUtils.writeLongArray(out, this.txBytes, this.bucketCount);
        ParcelUtils.writeLongArray(out, this.txPackets, this.bucketCount);
        ParcelUtils.writeLongArray(out, this.operations, this.bucketCount);
        out.writeLong(this.totalBytes);
    }

    public NetworkStatsHistory(DataInput in) throws IOException {
        int version = in.readInt();
        switch (version) {
            case 1:
                this.bucketDuration = in.readLong();
                this.bucketStart = DataStreamUtils.readFullLongArray(in);
                this.rxBytes = DataStreamUtils.readFullLongArray(in);
                this.rxPackets = new long[this.bucketStart.length];
                this.txBytes = DataStreamUtils.readFullLongArray(in);
                long[] jArr = this.bucketStart;
                this.txPackets = new long[jArr.length];
                this.operations = new long[jArr.length];
                this.bucketCount = jArr.length;
                this.totalBytes = ArrayUtils.total(this.rxBytes) + ArrayUtils.total(this.txBytes);
                break;
            case 2:
            case 3:
                this.bucketDuration = in.readLong();
                long[] readVarLongArray = DataStreamUtils.readVarLongArray(in);
                this.bucketStart = readVarLongArray;
                this.activeTime = version >= 3 ? DataStreamUtils.readVarLongArray(in) : new long[readVarLongArray.length];
                this.rxBytes = DataStreamUtils.readVarLongArray(in);
                this.rxPackets = DataStreamUtils.readVarLongArray(in);
                this.txBytes = DataStreamUtils.readVarLongArray(in);
                this.txPackets = DataStreamUtils.readVarLongArray(in);
                this.operations = DataStreamUtils.readVarLongArray(in);
                this.bucketCount = this.bucketStart.length;
                this.totalBytes = ArrayUtils.total(this.rxBytes) + ArrayUtils.total(this.txBytes);
                break;
            default:
                throw new ProtocolException("unexpected version: " + version);
        }
        int length = this.bucketStart.length;
        int i = this.bucketCount;
        if (length != i || this.rxBytes.length != i || this.rxPackets.length != i || this.txBytes.length != i || this.txPackets.length != i || this.operations.length != i) {
            throw new ProtocolException("Mismatched history lengths");
        }
    }

    public void writeToStream(DataOutput out) throws IOException {
        out.writeInt(3);
        out.writeLong(this.bucketDuration);
        DataStreamUtils.writeVarLongArray(out, this.bucketStart, this.bucketCount);
        DataStreamUtils.writeVarLongArray(out, this.activeTime, this.bucketCount);
        DataStreamUtils.writeVarLongArray(out, this.rxBytes, this.bucketCount);
        DataStreamUtils.writeVarLongArray(out, this.rxPackets, this.bucketCount);
        DataStreamUtils.writeVarLongArray(out, this.txBytes, this.bucketCount);
        DataStreamUtils.writeVarLongArray(out, this.txPackets, this.bucketCount);
        DataStreamUtils.writeVarLongArray(out, this.operations, this.bucketCount);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int size() {
        return this.bucketCount;
    }

    public long getBucketDuration() {
        return this.bucketDuration;
    }

    public long getStart() {
        if (this.bucketCount > 0) {
            return this.bucketStart[0];
        }
        return Long.MAX_VALUE;
    }

    public long getEnd() {
        int i = this.bucketCount;
        if (i > 0) {
            return this.bucketStart[i - 1] + this.bucketDuration;
        }
        return Long.MIN_VALUE;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public int getIndexBefore(long time) {
        int index;
        int index2 = Arrays.binarySearch(this.bucketStart, 0, this.bucketCount, time);
        if (index2 < 0) {
            index = (~index2) - 1;
        } else {
            index = index2 - 1;
        }
        return MathUtils.constrain(index, 0, this.bucketCount - 1);
    }

    public int getIndexAfter(long time) {
        int index;
        int index2 = Arrays.binarySearch(this.bucketStart, 0, this.bucketCount, time);
        if (index2 < 0) {
            index = ~index2;
        } else {
            index = index2 + 1;
        }
        return MathUtils.constrain(index, 0, this.bucketCount - 1);
    }

    public Entry getValues(int i, Entry recycle) {
        Entry entry = recycle != null ? recycle : new Entry();
        entry.bucketStart = this.bucketStart[i];
        entry.bucketDuration = this.bucketDuration;
        entry.activeTime = getLong(this.activeTime, i, -1L);
        entry.rxBytes = getLong(this.rxBytes, i, -1L);
        entry.rxPackets = getLong(this.rxPackets, i, -1L);
        entry.txBytes = getLong(this.txBytes, i, -1L);
        entry.txPackets = getLong(this.txPackets, i, -1L);
        entry.operations = getLong(this.operations, i, -1L);
        return entry;
    }

    public void setValues(int i, Entry entry) {
        long[] jArr = this.rxBytes;
        if (jArr != null) {
            this.totalBytes -= jArr[i];
        }
        long[] jArr2 = this.txBytes;
        if (jArr2 != null) {
            this.totalBytes -= jArr2[i];
        }
        this.bucketStart[i] = entry.bucketStart;
        setLong(this.activeTime, i, entry.activeTime);
        setLong(this.rxBytes, i, entry.rxBytes);
        setLong(this.rxPackets, i, entry.rxPackets);
        setLong(this.txBytes, i, entry.txBytes);
        setLong(this.txPackets, i, entry.txPackets);
        setLong(this.operations, i, entry.operations);
        long[] jArr3 = this.rxBytes;
        if (jArr3 != null) {
            this.totalBytes += jArr3[i];
        }
        long[] jArr4 = this.txBytes;
        if (jArr4 != null) {
            this.totalBytes += jArr4[i];
        }
    }

    @Deprecated
    public void recordData(long start, long end, long rxBytes, long txBytes) {
        recordData(start, end, new NetworkStats.Entry(NetworkStats.IFACE_ALL, -1, 0, 0, rxBytes, 0L, txBytes, 0L, 0L));
    }

    public void recordData(long start, long end, NetworkStats.Entry entry) {
        int i;
        long j = start;
        long j2 = end;
        long rxBytes = entry.rxBytes;
        long rxPackets = entry.rxPackets;
        long txBytes = entry.txBytes;
        long txPackets = entry.txPackets;
        long operations = entry.operations;
        if (entry.isNegative()) {
            throw new IllegalArgumentException("tried recording negative data");
        } else if (!entry.isEmpty()) {
            ensureBuckets(start, end);
            long duration = j2 - j;
            int startIndex = getIndexAfter(j2);
            long txPackets2 = txPackets;
            long operations2 = operations;
            long duration2 = duration;
            int i2 = startIndex;
            long fracRxPackets = rxPackets;
            long txBytes2 = txBytes;
            while (i2 >= 0) {
                long curStart = this.bucketStart[i2];
                long curEnd = this.bucketDuration + curStart;
                if (curEnd < j) {
                    break;
                }
                if (curStart <= j2) {
                    long overlap = Math.min(curEnd, j2) - Math.max(curStart, j);
                    if (overlap > 0) {
                        long fracRxBytes = NetworkUtilsInternal.multiplySafeByRational(rxBytes, overlap, duration2);
                        i = i2;
                        long fracRxPackets2 = NetworkUtilsInternal.multiplySafeByRational(fracRxPackets, overlap, duration2);
                        long fracTxBytes = NetworkUtilsInternal.multiplySafeByRational(txBytes2, overlap, duration2);
                        long fracTxPackets = NetworkUtilsInternal.multiplySafeByRational(txPackets2, overlap, duration2);
                        long fracOperations = NetworkUtilsInternal.multiplySafeByRational(operations2, overlap, duration2);
                        addLong(this.activeTime, i, overlap);
                        addLong(this.rxBytes, i, fracRxBytes);
                        rxBytes -= fracRxBytes;
                        addLong(this.rxPackets, i, fracRxPackets2);
                        long rxPackets2 = fracRxPackets - fracRxPackets2;
                        addLong(this.txBytes, i, fracTxBytes);
                        txBytes2 -= fracTxBytes;
                        addLong(this.txPackets, i, fracTxPackets);
                        txPackets2 -= fracTxPackets;
                        addLong(this.operations, i, fracOperations);
                        operations2 -= fracOperations;
                        duration2 -= overlap;
                        fracRxPackets = rxPackets2;
                        i2 = i - 1;
                        j = start;
                        j2 = end;
                    }
                }
                i = i2;
                i2 = i - 1;
                j = start;
                j2 = end;
            }
            this.totalBytes += entry.rxBytes + entry.txBytes;
        }
    }

    public void recordEntireHistory(NetworkStatsHistory input) {
        recordHistory(input, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public void recordHistory(NetworkStatsHistory input, long start, long end) {
        NetworkStats.Entry entry = new NetworkStats.Entry(NetworkStats.IFACE_ALL, -1, 0, 0, 0L, 0L, 0L, 0L, 0L);
        for (int i = 0; i < input.bucketCount; i++) {
            long bucketStart = input.bucketStart[i];
            long bucketEnd = input.bucketDuration + bucketStart;
            if (bucketStart >= start && bucketEnd <= end) {
                entry.rxBytes = NetworkStatsHistoryExtPlugin.hookRecordHistory.call(Long.valueOf(getLong(input.rxBytes, i, 0L)), 0L).longValue();
                entry.rxPackets = NetworkStatsHistoryExtPlugin.hookRecordHistory.call(Long.valueOf(getLong(input.rxPackets, i, 0L)), 0L).longValue();
                entry.txBytes = NetworkStatsHistoryExtPlugin.hookRecordHistory.call(Long.valueOf(getLong(input.txBytes, i, 0L)), 0L).longValue();
                entry.txPackets = NetworkStatsHistoryExtPlugin.hookRecordHistory.call(Long.valueOf(getLong(input.txPackets, i, 0L)), 0L).longValue();
                entry.operations = NetworkStatsHistoryExtPlugin.hookRecordHistory.call(Long.valueOf(getLong(input.operations, i, 0L)), 0L).longValue();
                recordData(bucketStart, bucketEnd, entry);
            }
        }
    }

    private void ensureBuckets(long start, long end) {
        long j = this.bucketDuration;
        long end2 = end + ((j - (end % j)) % j);
        long now = start - (start % j);
        while (now < end2) {
            int index = Arrays.binarySearch(this.bucketStart, 0, this.bucketCount, now);
            if (index < 0) {
                insertBucket(~index, now);
            }
            now += this.bucketDuration;
        }
    }

    private void insertBucket(int index, long start) {
        int i = this.bucketCount;
        long[] jArr = this.bucketStart;
        if (i >= jArr.length) {
            int newLength = (Math.max(jArr.length, 10) * 3) / 2;
            this.bucketStart = Arrays.copyOf(this.bucketStart, newLength);
            long[] jArr2 = this.activeTime;
            if (jArr2 != null) {
                this.activeTime = Arrays.copyOf(jArr2, newLength);
            }
            long[] jArr3 = this.rxBytes;
            if (jArr3 != null) {
                this.rxBytes = Arrays.copyOf(jArr3, newLength);
            }
            long[] jArr4 = this.rxPackets;
            if (jArr4 != null) {
                this.rxPackets = Arrays.copyOf(jArr4, newLength);
            }
            long[] jArr5 = this.txBytes;
            if (jArr5 != null) {
                this.txBytes = Arrays.copyOf(jArr5, newLength);
            }
            long[] jArr6 = this.txPackets;
            if (jArr6 != null) {
                this.txPackets = Arrays.copyOf(jArr6, newLength);
            }
            long[] jArr7 = this.operations;
            if (jArr7 != null) {
                this.operations = Arrays.copyOf(jArr7, newLength);
            }
        }
        int newLength2 = this.bucketCount;
        if (index < newLength2) {
            int dstPos = index + 1;
            int length = newLength2 - index;
            long[] jArr8 = this.bucketStart;
            System.arraycopy(jArr8, index, jArr8, dstPos, length);
            long[] jArr9 = this.activeTime;
            if (jArr9 != null) {
                System.arraycopy(jArr9, index, jArr9, dstPos, length);
            }
            long[] jArr10 = this.rxBytes;
            if (jArr10 != null) {
                System.arraycopy(jArr10, index, jArr10, dstPos, length);
            }
            long[] jArr11 = this.rxPackets;
            if (jArr11 != null) {
                System.arraycopy(jArr11, index, jArr11, dstPos, length);
            }
            long[] jArr12 = this.txBytes;
            if (jArr12 != null) {
                System.arraycopy(jArr12, index, jArr12, dstPos, length);
            }
            long[] jArr13 = this.txPackets;
            if (jArr13 != null) {
                System.arraycopy(jArr13, index, jArr13, dstPos, length);
            }
            long[] jArr14 = this.operations;
            if (jArr14 != null) {
                System.arraycopy(jArr14, index, jArr14, dstPos, length);
            }
        }
        this.bucketStart[index] = start;
        setLong(this.activeTime, index, 0L);
        setLong(this.rxBytes, index, 0L);
        setLong(this.rxPackets, index, 0L);
        setLong(this.txBytes, index, 0L);
        setLong(this.txPackets, index, 0L);
        setLong(this.operations, index, 0L);
        this.bucketCount++;
    }

    public void clear() {
        this.bucketStart = EmptyArray.LONG;
        if (this.activeTime != null) {
            this.activeTime = EmptyArray.LONG;
        }
        if (this.rxBytes != null) {
            this.rxBytes = EmptyArray.LONG;
        }
        if (this.rxPackets != null) {
            this.rxPackets = EmptyArray.LONG;
        }
        if (this.txBytes != null) {
            this.txBytes = EmptyArray.LONG;
        }
        if (this.txPackets != null) {
            this.txPackets = EmptyArray.LONG;
        }
        if (this.operations != null) {
            this.operations = EmptyArray.LONG;
        }
        this.bucketCount = 0;
        this.totalBytes = 0L;
    }

    @Deprecated
    public void removeBucketsBefore(long cutoff) {
        int i = 0;
        while (i < this.bucketCount) {
            long curStart = this.bucketStart[i];
            long curEnd = this.bucketDuration + curStart;
            if (curEnd > cutoff) {
                break;
            }
            i++;
        }
        if (i > 0) {
            long[] jArr = this.bucketStart;
            int length = jArr.length;
            this.bucketStart = Arrays.copyOfRange(jArr, i, length);
            long[] jArr2 = this.activeTime;
            if (jArr2 != null) {
                this.activeTime = Arrays.copyOfRange(jArr2, i, length);
            }
            long[] jArr3 = this.rxBytes;
            if (jArr3 != null) {
                this.rxBytes = Arrays.copyOfRange(jArr3, i, length);
            }
            long[] jArr4 = this.rxPackets;
            if (jArr4 != null) {
                this.rxPackets = Arrays.copyOfRange(jArr4, i, length);
            }
            long[] jArr5 = this.txBytes;
            if (jArr5 != null) {
                this.txBytes = Arrays.copyOfRange(jArr5, i, length);
            }
            long[] jArr6 = this.txPackets;
            if (jArr6 != null) {
                this.txPackets = Arrays.copyOfRange(jArr6, i, length);
            }
            long[] jArr7 = this.operations;
            if (jArr7 != null) {
                this.operations = Arrays.copyOfRange(jArr7, i, length);
            }
            this.bucketCount -= i;
        }
    }

    public Entry getValues(long start, long end, Entry recycle) {
        return getValues(start, end, Long.MAX_VALUE, recycle);
    }

    public Entry getValues(long start, long end, long now, Entry recycle) {
        Entry entry = recycle != null ? recycle : new Entry();
        entry.bucketDuration = end - start;
        entry.bucketStart = start;
        long j = -1;
        long j2 = 0;
        entry.activeTime = this.activeTime != null ? 0L : -1L;
        entry.rxBytes = this.rxBytes != null ? 0L : -1L;
        entry.rxPackets = this.rxPackets != null ? 0L : -1L;
        entry.txBytes = this.txBytes != null ? 0L : -1L;
        entry.txPackets = this.txPackets != null ? 0L : -1L;
        if (this.operations != null) {
            j = 0;
        }
        entry.operations = j;
        int startIndex = getIndexAfter(end);
        int i = startIndex;
        while (i >= 0) {
            long curStart = this.bucketStart[i];
            long curEnd = this.bucketDuration + curStart;
            if (curEnd <= start) {
                break;
            }
            if (curStart < end) {
                if (curEnd > now) {
                    curEnd = now;
                }
                long bucketSpan = curEnd - curStart;
                if (bucketSpan > j2) {
                    long overlapEnd = curEnd < end ? curEnd : end;
                    long overlapStart = curStart > start ? curStart : start;
                    long overlap = overlapEnd - overlapStart;
                    if (overlap > j2) {
                        if (this.activeTime != null) {
                            entry.activeTime += NetworkUtilsInternal.multiplySafeByRational(this.activeTime[i], overlap, bucketSpan);
                        }
                        if (this.rxBytes != null) {
                            entry.rxBytes += NetworkUtilsInternal.multiplySafeByRational(this.rxBytes[i], overlap, bucketSpan);
                        }
                        if (this.rxPackets != null) {
                            entry.rxPackets += NetworkUtilsInternal.multiplySafeByRational(this.rxPackets[i], overlap, bucketSpan);
                        }
                        if (this.txBytes != null) {
                            entry.txBytes += NetworkUtilsInternal.multiplySafeByRational(this.txBytes[i], overlap, bucketSpan);
                        }
                        if (this.txPackets != null) {
                            entry.txPackets += NetworkUtilsInternal.multiplySafeByRational(this.txPackets[i], overlap, bucketSpan);
                        }
                        if (this.operations != null) {
                            entry.operations += NetworkUtilsInternal.multiplySafeByRational(this.operations[i], overlap, bucketSpan);
                        }
                    }
                }
            }
            i--;
            j2 = 0;
        }
        return entry;
    }

    @Deprecated
    public void generateRandom(long start, long end, long bytes) {
        Random r = new Random();
        float fractionRx = r.nextFloat();
        long rxBytes = ((float) bytes) * fractionRx;
        long txBytes = ((float) bytes) * (1.0f - fractionRx);
        long rxPackets = rxBytes / 1024;
        long txPackets = txBytes / 1024;
        long operations = rxBytes / 2048;
        generateRandom(start, end, rxBytes, rxPackets, txBytes, txPackets, operations, r);
    }

    @Deprecated
    public void generateRandom(long start, long end, long rxBytes, long rxPackets, long txBytes, long txPackets, long operations, Random r) {
        long j = end;
        ensureBuckets(start, end);
        NetworkStats.Entry entry = new NetworkStats.Entry(NetworkStats.IFACE_ALL, -1, 0, 0, 0L, 0L, 0L, 0L, 0L);
        long rxBytes2 = rxBytes;
        long rxPackets2 = rxPackets;
        long txBytes2 = txBytes;
        long txPackets2 = txPackets;
        long operations2 = operations;
        while (true) {
            if (rxBytes2 > 1024 || rxPackets2 > 128 || txBytes2 > 1024 || txPackets2 > 128 || operations2 > 32) {
                long curStart = randomLong(r, start, j);
                long curEnd = curStart + randomLong(r, 0L, (j - curStart) / 2);
                long curEnd2 = randomLong(r, 0L, rxBytes2);
                entry.rxBytes = curEnd2;
                entry.rxPackets = randomLong(r, 0L, rxPackets2);
                entry.txBytes = randomLong(r, 0L, txBytes2);
                entry.txPackets = randomLong(r, 0L, txPackets2);
                entry.operations = randomLong(r, 0L, operations2);
                rxBytes2 -= entry.rxBytes;
                rxPackets2 -= entry.rxPackets;
                txBytes2 -= entry.txBytes;
                txPackets2 -= entry.txPackets;
                operations2 -= entry.operations;
                recordData(curStart, curEnd, entry);
                j = end;
            } else {
                return;
            }
        }
    }

    public static long randomLong(Random r, long start, long end) {
        return ((float) start) + (r.nextFloat() * ((float) (end - start)));
    }

    public boolean intersects(long start, long end) {
        long dataStart = getStart();
        long dataEnd = getEnd();
        if (start >= dataStart && start <= dataEnd) {
            return true;
        }
        if (end >= dataStart && end <= dataEnd) {
            return true;
        }
        if (dataStart < start || dataStart > end) {
            return dataEnd >= start && dataEnd <= end;
        }
        return true;
    }

    public void dump(IndentingPrintWriter pw, boolean fullHistory) {
        pw.print("NetworkStatsHistory: bucketDuration=");
        pw.println(this.bucketDuration / 1000);
        pw.increaseIndent();
        int start = 0;
        if (!fullHistory) {
            start = Math.max(0, this.bucketCount - 32);
        }
        if (start > 0) {
            pw.print("(omitting ");
            pw.print(start);
            pw.println(" buckets)");
        }
        for (int i = start; i < this.bucketCount; i++) {
            pw.print("st=");
            pw.print(this.bucketStart[i] / 1000);
            if (this.rxBytes != null) {
                pw.print(" rb=");
                pw.print(this.rxBytes[i]);
            }
            if (this.rxPackets != null) {
                pw.print(" rp=");
                pw.print(this.rxPackets[i]);
            }
            if (this.txBytes != null) {
                pw.print(" tb=");
                pw.print(this.txBytes[i]);
            }
            if (this.txPackets != null) {
                pw.print(" tp=");
                pw.print(this.txPackets[i]);
            }
            if (this.operations != null) {
                pw.print(" op=");
                pw.print(this.operations[i]);
            }
            pw.println();
        }
        pw.decreaseIndent();
    }

    public void dumpCheckin(PrintWriter pw) {
        pw.print("d,");
        pw.print(this.bucketDuration / 1000);
        pw.println();
        for (int i = 0; i < this.bucketCount; i++) {
            pw.print("b,");
            pw.print(this.bucketStart[i] / 1000);
            pw.print(',');
            long[] jArr = this.rxBytes;
            if (jArr != null) {
                pw.print(jArr[i]);
            } else {
                pw.print("*");
            }
            pw.print(',');
            long[] jArr2 = this.rxPackets;
            if (jArr2 != null) {
                pw.print(jArr2[i]);
            } else {
                pw.print("*");
            }
            pw.print(',');
            long[] jArr3 = this.txBytes;
            if (jArr3 != null) {
                pw.print(jArr3[i]);
            } else {
                pw.print("*");
            }
            pw.print(',');
            long[] jArr4 = this.txPackets;
            if (jArr4 != null) {
                pw.print(jArr4[i]);
            } else {
                pw.print("*");
            }
            pw.print(',');
            long[] jArr5 = this.operations;
            if (jArr5 != null) {
                pw.print(jArr5[i]);
            } else {
                pw.print("*");
            }
            pw.println();
        }
    }

    public void dumpDebug(ProtoOutputStream proto, long tag) {
        long start = proto.start(tag);
        proto.write(1112396529665L, this.bucketDuration);
        for (int i = 0; i < this.bucketCount; i++) {
            long startBucket = proto.start(2246267895810L);
            proto.write(1112396529665L, this.bucketStart[i]);
            dumpDebug(proto, 1112396529666L, this.rxBytes, i);
            dumpDebug(proto, 1112396529667L, this.rxPackets, i);
            dumpDebug(proto, 1112396529668L, this.txBytes, i);
            dumpDebug(proto, 1112396529669L, this.txPackets, i);
            dumpDebug(proto, 1112396529670L, this.operations, i);
            proto.end(startBucket);
        }
        proto.end(start);
    }

    private static void dumpDebug(ProtoOutputStream proto, long tag, long[] array, int index) {
        if (array != null) {
            proto.write(tag, array[index]);
        }
    }

    public String toString() {
        CharArrayWriter writer = new CharArrayWriter();
        dump(new IndentingPrintWriter(writer, "  "), false);
        return writer.toString();
    }

    private static long getLong(long[] array, int i, long value) {
        return array != null ? array[i] : value;
    }

    private static void setLong(long[] array, int i, long value) {
        if (array != null) {
            array[i] = value;
        }
    }

    private static void addLong(long[] array, int i, long value) {
        if (array != null && i < array.length) {
            array[i] = array[i] + value;
        }
    }

    public int estimateResizeBuckets(long newBucketDuration) {
        return (int) ((size() * getBucketDuration()) / newBucketDuration);
    }

    /* loaded from: classes2.dex */
    public static class DataStreamUtils {
        @Deprecated
        public static long[] readFullLongArray(DataInput in) throws IOException {
            int size = in.readInt();
            if (size >= 0) {
                long[] values = new long[size];
                for (int i = 0; i < values.length; i++) {
                    values[i] = in.readLong();
                }
                return values;
            }
            throw new ProtocolException("negative array size");
        }

        public static long readVarLong(DataInput in) throws IOException {
            long result = 0;
            for (int shift = 0; shift < 64; shift += 7) {
                byte b = in.readByte();
                result |= (b & Byte.MAX_VALUE) << shift;
                if ((b & 128) == 0) {
                    return result;
                }
            }
            throw new ProtocolException("malformed long");
        }

        public static void writeVarLong(DataOutput out, long value) throws IOException {
            while (((-128) & value) != 0) {
                out.writeByte((((int) value) & 127) | 128);
                value >>>= 7;
            }
            out.writeByte((int) value);
        }

        public static long[] readVarLongArray(DataInput in) throws IOException {
            int size = in.readInt();
            if (size == -1) {
                return null;
            }
            if (size >= 0) {
                long[] values = new long[size];
                for (int i = 0; i < values.length; i++) {
                    values[i] = readVarLong(in);
                }
                return values;
            }
            throw new ProtocolException("negative array size");
        }

        public static void writeVarLongArray(DataOutput out, long[] values, int size) throws IOException {
            if (values == null) {
                out.writeInt(-1);
            } else if (size <= values.length) {
                out.writeInt(size);
                for (int i = 0; i < size; i++) {
                    writeVarLong(out, values[i]);
                }
            } else {
                throw new IllegalArgumentException("size larger than length");
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ParcelUtils {
        public static long[] readLongArray(Parcel in) {
            int size = in.readInt();
            if (size == -1) {
                return null;
            }
            long[] values = new long[size];
            for (int i = 0; i < values.length; i++) {
                values[i] = in.readLong();
            }
            return values;
        }

        public static void writeLongArray(Parcel out, long[] values, int size) {
            if (values == null) {
                out.writeInt(-1);
            } else if (size <= values.length) {
                out.writeInt(size);
                for (int i = 0; i < size; i++) {
                    out.writeLong(values[i]);
                }
            } else {
                throw new IllegalArgumentException("size larger than length");
            }
        }
    }
}
