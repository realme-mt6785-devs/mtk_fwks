package android.net;

import android.annotation.SystemApi;
import android.net.NetworkStats;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.SparseBooleanArray;
import com.android.internal.util.ArrayUtils;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import libcore.util.EmptyArray;
@SystemApi
/* loaded from: classes2.dex */
public final class NetworkStats implements Parcelable {
    private static final String CLATD_INTERFACE_PREFIX = "v4-";
    public static final int DEFAULT_NETWORK_ALL = -1;
    public static final int DEFAULT_NETWORK_NO = 0;
    public static final int DEFAULT_NETWORK_YES = 1;
    public static final String IFACE_VT = "vt_data0";
    private static final int IPV4V6_HEADER_DELTA = 20;
    public static final int METERED_ALL = -1;
    public static final int METERED_NO = 0;
    public static final int METERED_YES = 1;
    public static final int ROAMING_ALL = -1;
    public static final int ROAMING_NO = 0;
    public static final int ROAMING_YES = 1;
    public static final int SET_ALL = -1;
    public static final int SET_DBG_VPN_IN = 1001;
    public static final int SET_DBG_VPN_OUT = 1002;
    public static final int SET_DEBUG_START = 1000;
    public static final int SET_DEFAULT = 0;
    public static final int SET_FOREGROUND = 1;
    public static final int STATS_PER_IFACE = 0;
    public static final int STATS_PER_UID = 1;
    private static final String TAG = "NetworkStats";
    public static final int TAG_ALL = -1;
    public static final int TAG_NONE = 0;
    public static final int UID_ALL = -1;
    public static final int UID_TETHERING = -5;
    private int capacity;
    private int[] defaultNetwork;
    private long elapsedRealtime;
    private String[] iface;
    private int[] metered;
    private long[] operations;
    private int[] roaming;
    private long[] rxBytes;
    private long[] rxPackets;
    private int[] set;
    private int size;
    private int[] tag;
    private long[] txBytes;
    private long[] txPackets;
    private int[] uid;
    public static final String IFACE_ALL = null;
    public static final String[] INTERFACES_ALL = null;
    public static final Parcelable.Creator<NetworkStats> CREATOR = new Parcelable.Creator<NetworkStats>() { // from class: android.net.NetworkStats.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkStats createFromParcel(Parcel in) {
            return new NetworkStats(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkStats[] newArray(int size) {
            return new NetworkStats[size];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DefaultNetwork {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Meteredness {
    }

    /* loaded from: classes2.dex */
    public interface NonMonotonicObserver<C> {
        void foundNonMonotonic(NetworkStats networkStats, int i, NetworkStats networkStats2, int i2, C c);

        void foundNonMonotonic(NetworkStats networkStats, int i, C c);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Roaming {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface State {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface StatsType {
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public static class Entry {
        public int defaultNetwork;
        public String iface;
        public int metered;
        public long operations;
        public int roaming;
        public long rxBytes;
        public long rxPackets;
        public int set;
        public int tag;
        public long txBytes;
        public long txPackets;
        public int uid;

        public Entry() {
            this(NetworkStats.IFACE_ALL, -1, 0, 0, 0L, 0L, 0L, 0L, 0L);
        }

        public Entry(long rxBytes, long rxPackets, long txBytes, long txPackets, long operations) {
            this(NetworkStats.IFACE_ALL, -1, 0, 0, rxBytes, rxPackets, txBytes, txPackets, operations);
        }

        public Entry(String iface, int uid, int set, int tag, long rxBytes, long rxPackets, long txBytes, long txPackets, long operations) {
            this(iface, uid, set, tag, 0, 0, 0, rxBytes, rxPackets, txBytes, txPackets, operations);
        }

        public Entry(String iface, int uid, int set, int tag, int metered, int roaming, int defaultNetwork, long rxBytes, long rxPackets, long txBytes, long txPackets, long operations) {
            this.iface = iface;
            this.uid = uid;
            this.set = set;
            this.tag = tag;
            this.metered = metered;
            this.roaming = roaming;
            this.defaultNetwork = defaultNetwork;
            this.rxBytes = rxBytes;
            this.rxPackets = rxPackets;
            this.txBytes = txBytes;
            this.txPackets = txPackets;
            this.operations = operations;
        }

        public boolean isNegative() {
            return this.rxBytes < 0 || this.rxPackets < 0 || this.txBytes < 0 || this.txPackets < 0 || this.operations < 0;
        }

        public boolean isEmpty() {
            return this.rxBytes == 0 && this.rxPackets == 0 && this.txBytes == 0 && this.txPackets == 0 && this.operations == 0;
        }

        public void add(Entry another) {
            this.rxBytes += another.rxBytes;
            this.rxPackets += another.rxPackets;
            this.txBytes += another.txBytes;
            this.txPackets += another.txPackets;
            this.operations += another.operations;
        }

        public String toString() {
            return "iface=" + this.iface + " uid=" + this.uid + " set=" + NetworkStats.setToString(this.set) + " tag=" + NetworkStats.tagToString(this.tag) + " metered=" + NetworkStats.meteredToString(this.metered) + " roaming=" + NetworkStats.roamingToString(this.roaming) + " defaultNetwork=" + NetworkStats.defaultNetworkToString(this.defaultNetwork) + " rxBytes=" + this.rxBytes + " rxPackets=" + this.rxPackets + " txBytes=" + this.txBytes + " txPackets=" + this.txPackets + " operations=" + this.operations;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry e = (Entry) o;
            return this.uid == e.uid && this.set == e.set && this.tag == e.tag && this.metered == e.metered && this.roaming == e.roaming && this.defaultNetwork == e.defaultNetwork && this.rxBytes == e.rxBytes && this.rxPackets == e.rxPackets && this.txBytes == e.txBytes && this.txPackets == e.txPackets && this.operations == e.operations && this.iface.equals(e.iface);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.uid), Integer.valueOf(this.set), Integer.valueOf(this.tag), Integer.valueOf(this.metered), Integer.valueOf(this.roaming), Integer.valueOf(this.defaultNetwork), this.iface);
        }
    }

    public NetworkStats(long elapsedRealtime, int initialSize) {
        this.elapsedRealtime = elapsedRealtime;
        this.size = 0;
        if (initialSize > 0) {
            this.capacity = initialSize;
            this.iface = new String[initialSize];
            this.uid = new int[initialSize];
            this.set = new int[initialSize];
            this.tag = new int[initialSize];
            this.metered = new int[initialSize];
            this.roaming = new int[initialSize];
            this.defaultNetwork = new int[initialSize];
            this.rxBytes = new long[initialSize];
            this.rxPackets = new long[initialSize];
            this.txBytes = new long[initialSize];
            this.txPackets = new long[initialSize];
            this.operations = new long[initialSize];
            return;
        }
        clear();
    }

    public NetworkStats(Parcel parcel) {
        this.elapsedRealtime = parcel.readLong();
        this.size = parcel.readInt();
        this.capacity = parcel.readInt();
        this.iface = parcel.createStringArray();
        this.uid = parcel.createIntArray();
        this.set = parcel.createIntArray();
        this.tag = parcel.createIntArray();
        this.metered = parcel.createIntArray();
        this.roaming = parcel.createIntArray();
        this.defaultNetwork = parcel.createIntArray();
        this.rxBytes = parcel.createLongArray();
        this.rxPackets = parcel.createLongArray();
        this.txBytes = parcel.createLongArray();
        this.txPackets = parcel.createLongArray();
        this.operations = parcel.createLongArray();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.elapsedRealtime);
        dest.writeInt(this.size);
        dest.writeInt(this.capacity);
        dest.writeStringArray(this.iface);
        dest.writeIntArray(this.uid);
        dest.writeIntArray(this.set);
        dest.writeIntArray(this.tag);
        dest.writeIntArray(this.metered);
        dest.writeIntArray(this.roaming);
        dest.writeIntArray(this.defaultNetwork);
        dest.writeLongArray(this.rxBytes);
        dest.writeLongArray(this.rxPackets);
        dest.writeLongArray(this.txBytes);
        dest.writeLongArray(this.txPackets);
        dest.writeLongArray(this.operations);
    }

    public NetworkStats clone() {
        NetworkStats clone = new NetworkStats(this.elapsedRealtime, this.size);
        Entry entry = null;
        for (int i = 0; i < this.size; i++) {
            entry = getValues(i, entry);
            clone.insertEntry(entry);
        }
        return clone;
    }

    public void clear() {
        this.capacity = 0;
        this.iface = EmptyArray.STRING;
        this.uid = EmptyArray.INT;
        this.set = EmptyArray.INT;
        this.tag = EmptyArray.INT;
        this.metered = EmptyArray.INT;
        this.roaming = EmptyArray.INT;
        this.defaultNetwork = EmptyArray.INT;
        this.rxBytes = EmptyArray.LONG;
        this.rxPackets = EmptyArray.LONG;
        this.txBytes = EmptyArray.LONG;
        this.txPackets = EmptyArray.LONG;
        this.operations = EmptyArray.LONG;
    }

    public NetworkStats insertEntry(String iface, long rxBytes, long rxPackets, long txBytes, long txPackets) {
        return insertEntry(iface, -1, 0, 0, rxBytes, rxPackets, txBytes, txPackets, 0L);
    }

    public NetworkStats insertEntry(String iface, int uid, int set, int tag, long rxBytes, long rxPackets, long txBytes, long txPackets, long operations) {
        return insertEntry(new Entry(iface, uid, set, tag, rxBytes, rxPackets, txBytes, txPackets, operations));
    }

    public NetworkStats insertEntry(String iface, int uid, int set, int tag, int metered, int roaming, int defaultNetwork, long rxBytes, long rxPackets, long txBytes, long txPackets, long operations) {
        return insertEntry(new Entry(iface, uid, set, tag, metered, roaming, defaultNetwork, rxBytes, rxPackets, txBytes, txPackets, operations));
    }

    public NetworkStats insertEntry(Entry entry) {
        int i = this.size;
        if (i >= this.capacity) {
            int newLength = (Math.max(i, 10) * 3) / 2;
            this.iface = (String[]) Arrays.copyOf(this.iface, newLength);
            this.uid = Arrays.copyOf(this.uid, newLength);
            this.set = Arrays.copyOf(this.set, newLength);
            this.tag = Arrays.copyOf(this.tag, newLength);
            this.metered = Arrays.copyOf(this.metered, newLength);
            this.roaming = Arrays.copyOf(this.roaming, newLength);
            this.defaultNetwork = Arrays.copyOf(this.defaultNetwork, newLength);
            this.rxBytes = Arrays.copyOf(this.rxBytes, newLength);
            this.rxPackets = Arrays.copyOf(this.rxPackets, newLength);
            this.txBytes = Arrays.copyOf(this.txBytes, newLength);
            this.txPackets = Arrays.copyOf(this.txPackets, newLength);
            this.operations = Arrays.copyOf(this.operations, newLength);
            this.capacity = newLength;
        }
        setValues(this.size, entry);
        this.size++;
        return this;
    }

    private void setValues(int i, Entry entry) {
        this.iface[i] = entry.iface;
        this.uid[i] = entry.uid;
        this.set[i] = entry.set;
        this.tag[i] = entry.tag;
        this.metered[i] = entry.metered;
        this.roaming[i] = entry.roaming;
        this.defaultNetwork[i] = entry.defaultNetwork;
        this.rxBytes[i] = entry.rxBytes;
        this.rxPackets[i] = entry.rxPackets;
        this.txBytes[i] = entry.txBytes;
        this.txPackets[i] = entry.txPackets;
        this.operations[i] = entry.operations;
    }

    public Entry getValues(int i, Entry recycle) {
        Entry entry = recycle != null ? recycle : new Entry();
        entry.iface = this.iface[i];
        entry.uid = this.uid[i];
        entry.set = this.set[i];
        entry.tag = this.tag[i];
        entry.metered = this.metered[i];
        entry.roaming = this.roaming[i];
        entry.defaultNetwork = this.defaultNetwork[i];
        entry.rxBytes = this.rxBytes[i];
        entry.rxPackets = this.rxPackets[i];
        entry.txBytes = this.txBytes[i];
        entry.txPackets = this.txPackets[i];
        entry.operations = this.operations[i];
        return entry;
    }

    private void maybeCopyEntry(int dest, int src) {
        if (dest != src) {
            String[] strArr = this.iface;
            strArr[dest] = strArr[src];
            int[] iArr = this.uid;
            iArr[dest] = iArr[src];
            int[] iArr2 = this.set;
            iArr2[dest] = iArr2[src];
            int[] iArr3 = this.tag;
            iArr3[dest] = iArr3[src];
            int[] iArr4 = this.metered;
            iArr4[dest] = iArr4[src];
            int[] iArr5 = this.roaming;
            iArr5[dest] = iArr5[src];
            int[] iArr6 = this.defaultNetwork;
            iArr6[dest] = iArr6[src];
            long[] jArr = this.rxBytes;
            jArr[dest] = jArr[src];
            long[] jArr2 = this.rxPackets;
            jArr2[dest] = jArr2[src];
            long[] jArr3 = this.txBytes;
            jArr3[dest] = jArr3[src];
            long[] jArr4 = this.txPackets;
            jArr4[dest] = jArr4[src];
            long[] jArr5 = this.operations;
            jArr5[dest] = jArr5[src];
        }
    }

    public long getElapsedRealtime() {
        return this.elapsedRealtime;
    }

    public void setElapsedRealtime(long time) {
        this.elapsedRealtime = time;
    }

    public long getElapsedRealtimeAge() {
        return SystemClock.elapsedRealtime() - this.elapsedRealtime;
    }

    public int size() {
        return this.size;
    }

    public int internalSize() {
        return this.capacity;
    }

    @Deprecated
    public NetworkStats combineValues(String iface, int uid, int tag, long rxBytes, long rxPackets, long txBytes, long txPackets, long operations) {
        return combineValues(iface, uid, 0, tag, rxBytes, rxPackets, txBytes, txPackets, operations);
    }

    public NetworkStats combineValues(String iface, int uid, int set, int tag, long rxBytes, long rxPackets, long txBytes, long txPackets, long operations) {
        return combineValues(new Entry(iface, uid, set, tag, rxBytes, rxPackets, txBytes, txPackets, operations));
    }

    public NetworkStats combineValues(Entry entry) {
        int i = findIndex(entry.iface, entry.uid, entry.set, entry.tag, entry.metered, entry.roaming, entry.defaultNetwork);
        if (i == -1) {
            insertEntry(entry);
        } else {
            long[] jArr = this.rxBytes;
            jArr[i] = jArr[i] + entry.rxBytes;
            long[] jArr2 = this.rxPackets;
            jArr2[i] = jArr2[i] + entry.rxPackets;
            long[] jArr3 = this.txBytes;
            jArr3[i] = jArr3[i] + entry.txBytes;
            long[] jArr4 = this.txPackets;
            jArr4[i] = jArr4[i] + entry.txPackets;
            long[] jArr5 = this.operations;
            jArr5[i] = jArr5[i] + entry.operations;
        }
        return this;
    }

    public NetworkStats addEntry(Entry entry) {
        return clone().combineValues(entry);
    }

    public NetworkStats add(NetworkStats another) {
        NetworkStats ret = clone();
        ret.combineAllValues(another);
        return ret;
    }

    public void combineAllValues(NetworkStats another) {
        Entry entry = null;
        for (int i = 0; i < another.size; i++) {
            entry = another.getValues(i, entry);
            combineValues(entry);
        }
    }

    public int findIndex(String iface, int uid, int set, int tag, int metered, int roaming, int defaultNetwork) {
        for (int i = 0; i < this.size; i++) {
            if (uid == this.uid[i] && set == this.set[i] && tag == this.tag[i] && metered == this.metered[i] && roaming == this.roaming[i] && defaultNetwork == this.defaultNetwork[i] && Objects.equals(iface, this.iface[i])) {
                return i;
            }
        }
        return -1;
    }

    public int findIndexHinted(String iface, int uid, int set, int tag, int metered, int roaming, int defaultNetwork, int hintIndex) {
        int i;
        int offset = 0;
        while (true) {
            int i2 = this.size;
            if (offset >= i2) {
                return -1;
            }
            int halfOffset = offset / 2;
            if (offset % 2 == 0) {
                i = (hintIndex + halfOffset) % i2;
            } else {
                i = (((i2 + hintIndex) - halfOffset) - 1) % i2;
            }
            if (uid == this.uid[i] && set == this.set[i] && tag == this.tag[i] && metered == this.metered[i] && roaming == this.roaming[i] && defaultNetwork == this.defaultNetwork[i] && Objects.equals(iface, this.iface[i])) {
                return i;
            }
            offset++;
        }
    }

    public void spliceOperationsFrom(NetworkStats stats) {
        for (int i = 0; i < this.size; i++) {
            int j = stats.findIndex(this.iface[i], this.uid[i], this.set[i], this.tag[i], this.metered[i], this.roaming[i], this.defaultNetwork[i]);
            if (j == -1) {
                this.operations[i] = 0;
            } else {
                this.operations[i] = stats.operations[j];
            }
        }
    }

    public String[] getUniqueIfaces() {
        String[] strArr;
        HashSet<String> ifaces = new HashSet<>();
        for (String iface : this.iface) {
            if (iface != IFACE_ALL) {
                ifaces.add(iface);
            }
        }
        return (String[]) ifaces.toArray(new String[ifaces.size()]);
    }

    public int[] getUniqueUids() {
        int[] iArr;
        SparseBooleanArray uids = new SparseBooleanArray();
        for (int uid : this.uid) {
            uids.put(uid, true);
        }
        int size = uids.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = uids.keyAt(i);
        }
        return result;
    }

    public long getTotalBytes() {
        Entry entry = getTotal(null);
        return entry.rxBytes + entry.txBytes;
    }

    public Entry getTotal(Entry recycle) {
        return getTotal(recycle, null, -1, false);
    }

    public Entry getTotal(Entry recycle, int limitUid) {
        return getTotal(recycle, null, limitUid, false);
    }

    public Entry getTotal(Entry recycle, HashSet<String> limitIface) {
        return getTotal(recycle, limitIface, -1, false);
    }

    public Entry getTotalIncludingTags(Entry recycle) {
        return getTotal(recycle, null, -1, true);
    }

    private Entry getTotal(Entry recycle, HashSet<String> limitIface, int limitUid, boolean includeTags) {
        boolean matchesUid;
        Entry entry = recycle != null ? recycle : new Entry();
        entry.iface = IFACE_ALL;
        entry.uid = limitUid;
        entry.set = -1;
        entry.tag = 0;
        entry.metered = -1;
        entry.roaming = -1;
        entry.defaultNetwork = -1;
        entry.rxBytes = 0L;
        entry.rxPackets = 0L;
        entry.txBytes = 0L;
        entry.txPackets = 0L;
        entry.operations = 0L;
        for (int i = 0; i < this.size; i++) {
            boolean matchesIface = true;
            if (limitUid == -1 || limitUid == this.uid[i]) {
                matchesUid = true;
            } else {
                matchesUid = false;
            }
            if (limitIface != null && !limitIface.contains(this.iface[i])) {
                matchesIface = false;
            }
            if (matchesUid && matchesIface && (this.tag[i] == 0 || includeTags)) {
                entry.rxBytes += this.rxBytes[i];
                entry.rxPackets += this.rxPackets[i];
                entry.txBytes += this.txBytes[i];
                entry.txPackets += this.txPackets[i];
                entry.operations += this.operations[i];
            }
        }
        return entry;
    }

    public long getTotalPackets() {
        long total = 0;
        for (int i = this.size - 1; i >= 0; i--) {
            total += this.rxPackets[i] + this.txPackets[i];
        }
        return total;
    }

    public NetworkStats subtract(NetworkStats right) {
        return subtract(this, right, null, null);
    }

    public static <C> NetworkStats subtract(NetworkStats left, NetworkStats right, NonMonotonicObserver<C> observer, C cookie) {
        return subtract(left, right, observer, cookie, null);
    }

    public static <C> NetworkStats subtract(NetworkStats left, NetworkStats right, NonMonotonicObserver<C> observer, C cookie, NetworkStats recycle) {
        long deltaRealtime;
        NetworkStats result;
        long deltaRealtime2;
        Entry entry;
        NetworkStats result2;
        int i;
        long j;
        NetworkStats networkStats = right;
        long deltaRealtime3 = left.elapsedRealtime - networkStats.elapsedRealtime;
        if (deltaRealtime3 < 0) {
            if (observer != null) {
                observer.foundNonMonotonic(left, -1, right, -1, cookie);
            }
            deltaRealtime = 0;
        } else {
            deltaRealtime = deltaRealtime3;
        }
        Entry entry2 = new Entry();
        if (recycle == null || recycle.capacity < left.size) {
            result = new NetworkStats(deltaRealtime, left.size);
        } else {
            recycle.size = 0;
            recycle.elapsedRealtime = deltaRealtime;
            result = recycle;
        }
        int i2 = 0;
        while (i2 < left.size) {
            entry2.iface = left.iface[i2];
            entry2.uid = left.uid[i2];
            entry2.set = left.set[i2];
            entry2.tag = left.tag[i2];
            entry2.metered = left.metered[i2];
            entry2.roaming = left.roaming[i2];
            entry2.defaultNetwork = left.defaultNetwork[i2];
            entry2.rxBytes = left.rxBytes[i2];
            entry2.rxPackets = left.rxPackets[i2];
            entry2.txBytes = left.txBytes[i2];
            entry2.txPackets = left.txPackets[i2];
            entry2.operations = left.operations[i2];
            int j2 = right.findIndexHinted(entry2.iface, entry2.uid, entry2.set, entry2.tag, entry2.metered, entry2.roaming, entry2.defaultNetwork, i2);
            if (j2 != -1) {
                entry2.rxBytes -= networkStats.rxBytes[j2];
                entry2.rxPackets -= networkStats.rxPackets[j2];
                entry2.txBytes -= networkStats.txBytes[j2];
                entry2.txPackets -= networkStats.txPackets[j2];
                entry2.operations -= networkStats.operations[j2];
            }
            if (entry2.isNegative()) {
                if (observer != null) {
                    i = i2;
                    result2 = result;
                    entry = entry2;
                    deltaRealtime2 = deltaRealtime;
                    observer.foundNonMonotonic(left, i, right, j2, cookie);
                } else {
                    i = i2;
                    entry = entry2;
                    deltaRealtime2 = deltaRealtime;
                    result2 = result;
                }
                j = 0;
                entry.rxBytes = Math.max(entry.rxBytes, 0L);
                entry.rxPackets = Math.max(entry.rxPackets, 0L);
                entry.txBytes = Math.max(entry.txBytes, 0L);
                entry.txPackets = Math.max(entry.txPackets, 0L);
                entry.operations = Math.max(entry.operations, 0L);
            } else {
                i = i2;
                entry = entry2;
                deltaRealtime2 = deltaRealtime;
                result2 = result;
                j = 0;
            }
            result2.insertEntry(entry);
            i2 = i + 1;
            networkStats = right;
            deltaRealtime = deltaRealtime2;
            result = result2;
            entry2 = entry;
        }
        return result;
    }

    public static void apply464xlatAdjustments(NetworkStats baseTraffic, NetworkStats stackedTraffic, Map<String, String> stackedIfaces) {
        Entry entry = null;
        for (int i = 0; i < stackedTraffic.size; i++) {
            entry = stackedTraffic.getValues(i, entry);
            if (!(entry == null || entry.iface == null || !entry.iface.startsWith(CLATD_INTERFACE_PREFIX))) {
                entry.rxBytes += entry.rxPackets * 20;
                entry.txBytes += entry.txPackets * 20;
                stackedTraffic.setValues(i, entry);
            }
        }
    }

    public void apply464xlatAdjustments(Map<String, String> stackedIfaces) {
        apply464xlatAdjustments(this, this, stackedIfaces);
    }

    public NetworkStats groupedByIface() {
        NetworkStats stats = new NetworkStats(this.elapsedRealtime, 10);
        Entry entry = new Entry();
        entry.uid = -1;
        entry.set = -1;
        entry.tag = 0;
        entry.metered = -1;
        entry.roaming = -1;
        entry.defaultNetwork = -1;
        entry.operations = 0L;
        for (int i = 0; i < this.size; i++) {
            if (this.tag[i] == 0) {
                entry.iface = this.iface[i];
                entry.rxBytes = this.rxBytes[i];
                entry.rxPackets = this.rxPackets[i];
                entry.txBytes = this.txBytes[i];
                entry.txPackets = this.txPackets[i];
                stats.combineValues(entry);
            }
        }
        return stats;
    }

    public NetworkStats groupedByUid() {
        NetworkStats stats = new NetworkStats(this.elapsedRealtime, 10);
        Entry entry = new Entry();
        entry.iface = IFACE_ALL;
        entry.set = -1;
        entry.tag = 0;
        entry.metered = -1;
        entry.roaming = -1;
        entry.defaultNetwork = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.tag[i] == 0) {
                entry.uid = this.uid[i];
                entry.rxBytes = this.rxBytes[i];
                entry.rxPackets = this.rxPackets[i];
                entry.txBytes = this.txBytes[i];
                entry.txPackets = this.txPackets[i];
                entry.operations = this.operations[i];
                stats.combineValues(entry);
            }
        }
        return stats;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$removeUids$0(int[] uids, Entry e) {
        return !ArrayUtils.contains(uids, e.uid);
    }

    public void removeUids(final int[] uids) {
        filter(new Predicate() { // from class: android.net.NetworkStats$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NetworkStats.lambda$removeUids$0(uids, (NetworkStats.Entry) obj);
            }
        });
    }

    public NetworkStats removeEmptyEntries() {
        NetworkStats ret = clone();
        ret.filter(NetworkStats$$ExternalSyntheticLambda3.INSTANCE);
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$removeEmptyEntries$1(Entry e) {
        return (e.rxBytes == 0 && e.rxPackets == 0 && e.txBytes == 0 && e.txPackets == 0 && e.operations == 0) ? false : true;
    }

    public void filter(final int limitUid, final String[] limitIfaces, final int limitTag) {
        if (limitUid != -1 || limitTag != -1 || limitIfaces != INTERFACES_ALL) {
            filter(new Predicate() { // from class: android.net.NetworkStats$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return NetworkStats.lambda$filter$2(limitUid, limitTag, limitIfaces, (NetworkStats.Entry) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$filter$2(int limitUid, int limitTag, String[] limitIfaces, Entry e) {
        return (limitUid == -1 || limitUid == e.uid) && (limitTag == -1 || limitTag == e.tag) && (limitIfaces == INTERFACES_ALL || ArrayUtils.contains(limitIfaces, e.iface));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$filterDebugEntries$3(Entry e) {
        return e.set < 1000;
    }

    public void filterDebugEntries() {
        filter(NetworkStats$$ExternalSyntheticLambda2.INSTANCE);
    }

    private void filter(Predicate<Entry> predicate) {
        Entry entry = new Entry();
        int nextOutputEntry = 0;
        for (int i = 0; i < this.size; i++) {
            entry = getValues(i, entry);
            if (predicate.test(entry)) {
                if (nextOutputEntry != i) {
                    setValues(nextOutputEntry, entry);
                }
                nextOutputEntry++;
            }
        }
        this.size = nextOutputEntry;
    }

    public void dump(String prefix, PrintWriter pw) {
        pw.print(prefix);
        pw.print("NetworkStats: elapsedRealtime=");
        pw.println(this.elapsedRealtime);
        for (int i = 0; i < this.size; i++) {
            pw.print(prefix);
            pw.print("  [");
            pw.print(i);
            pw.print("]");
            pw.print(" iface=");
            pw.print(this.iface[i]);
            pw.print(" uid=");
            pw.print(this.uid[i]);
            pw.print(" set=");
            pw.print(setToString(this.set[i]));
            pw.print(" tag=");
            pw.print(tagToString(this.tag[i]));
            pw.print(" metered=");
            pw.print(meteredToString(this.metered[i]));
            pw.print(" roaming=");
            pw.print(roamingToString(this.roaming[i]));
            pw.print(" defaultNetwork=");
            pw.print(defaultNetworkToString(this.defaultNetwork[i]));
            pw.print(" rxBytes=");
            pw.print(this.rxBytes[i]);
            pw.print(" rxPackets=");
            pw.print(this.rxPackets[i]);
            pw.print(" txBytes=");
            pw.print(this.txBytes[i]);
            pw.print(" txPackets=");
            pw.print(this.txPackets[i]);
            pw.print(" operations=");
            pw.println(this.operations[i]);
        }
    }

    public static String setToString(int set) {
        switch (set) {
            case -1:
                return "ALL";
            case 0:
                return "DEFAULT";
            case 1:
                return "FOREGROUND";
            case 1001:
                return "DBG_VPN_IN";
            case 1002:
                return "DBG_VPN_OUT";
            default:
                return "UNKNOWN";
        }
    }

    public static String setToCheckinString(int set) {
        switch (set) {
            case -1:
                return "all";
            case 0:
                return "def";
            case 1:
                return "fg";
            case 1001:
                return "vpnin";
            case 1002:
                return "vpnout";
            default:
                return "unk";
        }
    }

    public static boolean setMatches(int querySet, int dataSet) {
        if (querySet == dataSet) {
            return true;
        }
        return querySet == -1 && dataSet < 1000;
    }

    public static String tagToString(int tag) {
        return "0x" + Integer.toHexString(tag);
    }

    public static String meteredToString(int metered) {
        switch (metered) {
            case -1:
                return "ALL";
            case 0:
                return "NO";
            case 1:
                return "YES";
            default:
                return "UNKNOWN";
        }
    }

    public static String roamingToString(int roaming) {
        switch (roaming) {
            case -1:
                return "ALL";
            case 0:
                return "NO";
            case 1:
                return "YES";
            default:
                return "UNKNOWN";
        }
    }

    public static String defaultNetworkToString(int defaultNetwork) {
        switch (defaultNetwork) {
            case -1:
                return "ALL";
            case 0:
                return "NO";
            case 1:
                return "YES";
            default:
                return "UNKNOWN";
        }
    }

    public String toString() {
        CharArrayWriter writer = new CharArrayWriter();
        dump("", new PrintWriter(writer));
        return writer.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void migrateTun(int tunUid, String tunIface, List<String> underlyingIfaces) {
        Entry tunIfaceTotal = new Entry();
        Entry[] perInterfaceTotal = new Entry[underlyingIfaces.size()];
        Entry underlyingIfacesTotal = new Entry();
        for (int i = 0; i < perInterfaceTotal.length; i++) {
            perInterfaceTotal[i] = new Entry();
        }
        tunAdjustmentInit(tunUid, tunIface, underlyingIfaces, tunIfaceTotal, perInterfaceTotal, underlyingIfacesTotal);
        Entry[] moved = addTrafficToApplications(tunUid, tunIface, underlyingIfaces, tunIfaceTotal, perInterfaceTotal, underlyingIfacesTotal);
        deductTrafficFromVpnApp(tunUid, underlyingIfaces, moved);
    }

    private void tunAdjustmentInit(int tunUid, String tunIface, List<String> underlyingIfaces, Entry tunIfaceTotal, Entry[] perInterfaceTotal, Entry underlyingIfacesTotal) {
        Entry recycle = new Entry();
        for (int i = 0; i < this.size; i++) {
            getValues(i, recycle);
            if (recycle.uid == -1) {
                throw new IllegalStateException("Cannot adjust VPN accounting on an iface aggregated NetworkStats.");
            } else if (recycle.set == 1001 || recycle.set == 1002) {
                throw new IllegalStateException("Cannot adjust VPN accounting on a NetworkStats containing SET_DBG_VPN_*");
            } else {
                if (recycle.tag == 0) {
                    if (tunUid == 1000) {
                        if (tunIface.equals(recycle.iface)) {
                            tunIfaceTotal.add(recycle);
                            underlyingIfacesTotal.add(recycle);
                            if (perInterfaceTotal.length > 0) {
                                perInterfaceTotal[0].add(recycle);
                            }
                        }
                    } else if (recycle.uid == tunUid) {
                        int j = 0;
                        while (true) {
                            if (j >= underlyingIfaces.size()) {
                                break;
                            } else if (Objects.equals(underlyingIfaces.get(j), recycle.iface)) {
                                perInterfaceTotal[j].add(recycle);
                                underlyingIfacesTotal.add(recycle);
                                break;
                            } else {
                                j++;
                            }
                        }
                    } else if (tunIface.equals(recycle.iface)) {
                        tunIfaceTotal.add(recycle);
                    }
                }
            }
        }
    }

    private Entry[] addTrafficToApplications(int tunUid, String tunIface, List<String> underlyingIfaces, Entry tunIfaceTotal, Entry[] perInterfaceTotal, Entry underlyingIfacesTotal) {
        long totalTxBytes;
        long txPacketsAcrossUnderlyingIfaces;
        long totalTxPackets;
        long operationsAcrossUnderlyingIfaces;
        long totalRxBytes;
        int i = tunUid;
        Entry[] moved = new Entry[underlyingIfaces.size()];
        for (int i2 = 0; i2 < underlyingIfaces.size(); i2++) {
            moved[i2] = new Entry();
        }
        Entry tmpEntry = new Entry();
        int origSize = this.size;
        int i3 = 0;
        while (i3 < origSize) {
            if (Objects.equals(this.iface[i3], tunIface)) {
                int[] iArr = this.uid;
                if (iArr[i3] != i || i == 1000) {
                    tmpEntry.uid = iArr[i3];
                    tmpEntry.tag = this.tag[i3];
                    tmpEntry.metered = this.metered[i3];
                    tmpEntry.roaming = this.roaming[i3];
                    tmpEntry.defaultNetwork = this.defaultNetwork[i3];
                    long totalRxBytes2 = 0;
                    if (tunIfaceTotal.rxBytes > 0) {
                        long rxBytesAcrossUnderlyingIfaces = (underlyingIfacesTotal.rxBytes * this.rxBytes[i3]) / tunIfaceTotal.rxBytes;
                        totalRxBytes2 = Math.min(this.rxBytes[i3], rxBytesAcrossUnderlyingIfaces);
                    }
                    long totalRxPackets = 0;
                    if (tunIfaceTotal.rxPackets > 0) {
                        long rxPacketsAcrossUnderlyingIfaces = (underlyingIfacesTotal.rxPackets * this.rxPackets[i3]) / tunIfaceTotal.rxPackets;
                        totalRxPackets = Math.min(this.rxPackets[i3], rxPacketsAcrossUnderlyingIfaces);
                    }
                    long totalTxBytes2 = 0;
                    if (tunIfaceTotal.txBytes > 0) {
                        long totalTxBytes3 = tunIfaceTotal.txBytes;
                        long txBytesAcrossUnderlyingIfaces = (underlyingIfacesTotal.txBytes * this.txBytes[i3]) / totalTxBytes3;
                        totalTxBytes2 = Math.min(this.txBytes[i3], txBytesAcrossUnderlyingIfaces);
                    }
                    long totalTxPackets2 = tunIfaceTotal.txPackets;
                    if (totalTxPackets2 > 0) {
                        long j = underlyingIfacesTotal.txPackets * this.txPackets[i3];
                        totalTxBytes = totalTxBytes2;
                        long totalTxBytes4 = tunIfaceTotal.txPackets;
                        txPacketsAcrossUnderlyingIfaces = Math.min(this.txPackets[i3], j / totalTxBytes4);
                    } else {
                        totalTxBytes = totalTxBytes2;
                        txPacketsAcrossUnderlyingIfaces = 0;
                    }
                    long totalOperations = tunIfaceTotal.operations;
                    if (totalOperations > 0) {
                        long j2 = underlyingIfacesTotal.operations * this.operations[i3];
                        totalTxPackets = txPacketsAcrossUnderlyingIfaces;
                        long totalTxPackets3 = tunIfaceTotal.operations;
                        long operationsAcrossUnderlyingIfaces2 = j2 / totalTxPackets3;
                        operationsAcrossUnderlyingIfaces = Math.min(this.operations[i3], operationsAcrossUnderlyingIfaces2);
                    } else {
                        totalTxPackets = txPacketsAcrossUnderlyingIfaces;
                        operationsAcrossUnderlyingIfaces = 0;
                    }
                    int j3 = 0;
                    while (j3 < underlyingIfaces.size()) {
                        tmpEntry.iface = underlyingIfaces.get(j3);
                        tmpEntry.rxBytes = 0L;
                        tmpEntry.set = this.set[i3];
                        if (underlyingIfacesTotal.rxBytes > 0) {
                            long j4 = perInterfaceTotal[j3].rxBytes * totalRxBytes2;
                            totalRxBytes = totalRxBytes2;
                            long totalRxBytes3 = underlyingIfacesTotal.rxBytes;
                            tmpEntry.rxBytes = j4 / totalRxBytes3;
                        } else {
                            totalRxBytes = totalRxBytes2;
                        }
                        tmpEntry.rxPackets = 0L;
                        if (underlyingIfacesTotal.rxPackets > 0) {
                            tmpEntry.rxPackets = (perInterfaceTotal[j3].rxPackets * totalRxPackets) / underlyingIfacesTotal.rxPackets;
                        }
                        tmpEntry.txBytes = 0L;
                        if (underlyingIfacesTotal.txBytes > 0) {
                            tmpEntry.txBytes = (perInterfaceTotal[j3].txBytes * totalTxBytes) / underlyingIfacesTotal.txBytes;
                        }
                        tmpEntry.txPackets = 0L;
                        if (underlyingIfacesTotal.txPackets > 0) {
                            tmpEntry.txPackets = (totalTxPackets * perInterfaceTotal[j3].txPackets) / underlyingIfacesTotal.txPackets;
                        }
                        tmpEntry.operations = 0L;
                        if (underlyingIfacesTotal.operations > 0) {
                            tmpEntry.operations = (perInterfaceTotal[j3].operations * operationsAcrossUnderlyingIfaces) / underlyingIfacesTotal.operations;
                        }
                        combineValues(tmpEntry);
                        if (this.tag[i3] == 0) {
                            moved[j3].add(tmpEntry);
                            tmpEntry.set = 1001;
                            combineValues(tmpEntry);
                        }
                        j3++;
                        totalRxBytes2 = totalRxBytes;
                    }
                }
            }
            i3++;
            i = tunUid;
        }
        return moved;
    }

    private void deductTrafficFromVpnApp(int tunUid, List<String> underlyingIfaces, Entry[] moved) {
        if (tunUid != 1000) {
            for (int i = 0; i < underlyingIfaces.size(); i++) {
                moved[i].uid = tunUid;
                moved[i].set = 1002;
                moved[i].tag = 0;
                moved[i].iface = underlyingIfaces.get(i);
                moved[i].metered = -1;
                moved[i].roaming = -1;
                moved[i].defaultNetwork = -1;
                combineValues(moved[i]);
                int idxVpnBackground = findIndex(underlyingIfaces.get(i), tunUid, 0, 0, 0, 0, 0);
                if (idxVpnBackground != -1) {
                    tunSubtract(idxVpnBackground, this, moved[i]);
                }
                int idxVpnForeground = findIndex(underlyingIfaces.get(i), tunUid, 1, 0, 0, 0, 0);
                if (idxVpnForeground != -1) {
                    tunSubtract(idxVpnForeground, this, moved[i]);
                }
            }
        }
    }

    private static void tunSubtract(int i, NetworkStats left, Entry right) {
        long rxBytes = Math.min(left.rxBytes[i], right.rxBytes);
        long[] jArr = left.rxBytes;
        jArr[i] = jArr[i] - rxBytes;
        right.rxBytes -= rxBytes;
        long rxPackets = Math.min(left.rxPackets[i], right.rxPackets);
        long[] jArr2 = left.rxPackets;
        jArr2[i] = jArr2[i] - rxPackets;
        right.rxPackets -= rxPackets;
        long txBytes = Math.min(left.txBytes[i], right.txBytes);
        long[] jArr3 = left.txBytes;
        jArr3[i] = jArr3[i] - txBytes;
        right.txBytes -= txBytes;
        long txPackets = Math.min(left.txPackets[i], right.txPackets);
        long[] jArr4 = left.txPackets;
        jArr4[i] = jArr4[i] - txPackets;
        right.txPackets -= txPackets;
    }
}
