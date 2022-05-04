package com.android.internal.app.procstats;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Pair;
import android.util.Slog;
import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;
import com.android.internal.accessibility.common.ShortcutConstants;
import com.android.internal.app.procstats.ProcessStats;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class AssociationState {
    private static final boolean DEBUG = false;
    private static final String TAG = "ProcessStats";
    private static final boolean VALIDATE_TIMES = false;
    private final String mName;
    private final ProcessStats.PackageState mPackageState;
    private ProcessState mProc;
    private final String mProcessName;
    private final ProcessStats mProcessStats;
    final ArrayMap<SourceKey, SourceState> mSources = new ArrayMap<>();
    private int mTotalActiveCount;
    private long mTotalActiveDuration;
    private int mTotalActiveNesting;
    private long mTotalActiveStartUptime;
    private int mTotalCount;
    private long mTotalDuration;
    private int mTotalNesting;
    private long mTotalStartUptime;
    private static final SourceKey sTmpSourceKey = new SourceKey(0, (String) null, (String) null);
    static final Comparator<Pair<SourceKey, SourceDumpContainer>> ASSOCIATION_COMPARATOR = AssociationState$$ExternalSyntheticLambda0.INSTANCE;

    static /* synthetic */ int access$008(AssociationState x0) {
        int i = x0.mTotalActiveNesting;
        x0.mTotalActiveNesting = i + 1;
        return i;
    }

    static /* synthetic */ int access$010(AssociationState x0) {
        int i = x0.mTotalActiveNesting;
        x0.mTotalActiveNesting = i - 1;
        return i;
    }

    static /* synthetic */ int access$108(AssociationState x0) {
        int i = x0.mTotalActiveCount;
        x0.mTotalActiveCount = i + 1;
        return i;
    }

    static /* synthetic */ long access$314(AssociationState x0, long x1) {
        long j = x0.mTotalActiveDuration + x1;
        x0.mTotalActiveDuration = j;
        return j;
    }

    static /* synthetic */ int access$410(AssociationState x0) {
        int i = x0.mTotalNesting;
        x0.mTotalNesting = i - 1;
        return i;
    }

    static /* synthetic */ long access$514(AssociationState x0, long x1) {
        long j = x0.mTotalDuration + x1;
        x0.mTotalDuration = j;
        return j;
    }

    /* loaded from: classes4.dex */
    public static final class SourceState implements Parcelable {
        int mActiveCount;
        long mActiveDuration;
        DurationsTable mActiveDurations;
        int mActiveNesting;
        long mActiveStartUptime;
        private final AssociationState mAssociationState;
        private SourceState mCommonSourceState;
        int mCount;
        long mDuration;
        boolean mInTrackingList;
        final SourceKey mKey;
        int mNesting;
        private final ProcessStats mProcessStats;
        long mStartUptime;
        private final ProcessState mTargetProcess;
        long mTrackingUptime;
        int mProcStateSeq = -1;
        int mProcState = -1;
        int mActiveProcState = -1;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SourceState(ProcessStats processStats, AssociationState associationState, ProcessState targetProcess, SourceKey key) {
            this.mProcessStats = processStats;
            this.mAssociationState = associationState;
            this.mTargetProcess = targetProcess;
            this.mKey = key;
        }

        public AssociationState getAssociationState() {
            return this.mAssociationState;
        }

        public String getProcessName() {
            return this.mKey.mProcess;
        }

        public int getUid() {
            return this.mKey.mUid;
        }

        private SourceState getCommonSourceState(boolean createIfNeeded) {
            if (this.mCommonSourceState == null && createIfNeeded) {
                this.mCommonSourceState = this.mTargetProcess.getOrCreateSourceState(this.mKey);
            }
            return this.mCommonSourceState;
        }

        public void trackProcState(int procState, int seq, long now) {
            SourceState commonSource;
            int procState2 = ProcessState.PROCESS_STATE_TO_STATE[procState];
            if (seq != this.mProcStateSeq) {
                this.mProcStateSeq = seq;
                this.mProcState = procState2;
            } else if (procState2 < this.mProcState) {
                this.mProcState = procState2;
            }
            if (procState2 < 11 && !this.mInTrackingList) {
                this.mInTrackingList = true;
                this.mTrackingUptime = now;
                if (this.mAssociationState != null) {
                    this.mProcessStats.mTrackingAssociations.add(this);
                }
            }
            if (this.mAssociationState != null && (commonSource = getCommonSourceState(true)) != null) {
                commonSource.trackProcState(procState, seq, now);
            }
        }

        long start() {
            SourceState commonSource;
            long now = start(-1L);
            if (!(this.mAssociationState == null || (commonSource = getCommonSourceState(true)) == null)) {
                commonSource.start(now);
            }
            return now;
        }

        long start(long now) {
            int i = this.mNesting + 1;
            this.mNesting = i;
            if (i == 1) {
                if (now < 0) {
                    now = SystemClock.uptimeMillis();
                }
                this.mCount++;
                this.mStartUptime = now;
            }
            return now;
        }

        public void stop() {
            SourceState commonSource;
            long now = stop(-1L);
            if (this.mAssociationState != null && (commonSource = getCommonSourceState(false)) != null) {
                commonSource.stop(now);
            }
        }

        long stop(long now) {
            int i = this.mNesting - 1;
            this.mNesting = i;
            if (i == 0) {
                if (now < 0) {
                    now = SystemClock.uptimeMillis();
                }
                this.mDuration += now - this.mStartUptime;
                stopTracking(now);
            }
            return now;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void startActive(long now) {
            SourceState commonSource;
            boolean startActive = false;
            if (this.mInTrackingList) {
                if (this.mActiveStartUptime == 0) {
                    this.mActiveStartUptime = now;
                    this.mActiveNesting++;
                    this.mActiveCount++;
                    startActive = true;
                    AssociationState associationState = this.mAssociationState;
                    if (associationState != null) {
                        AssociationState.access$008(associationState);
                        if (this.mAssociationState.mTotalActiveNesting == 1) {
                            AssociationState.access$108(this.mAssociationState);
                            this.mAssociationState.mTotalActiveStartUptime = now;
                        }
                    }
                } else if (this.mAssociationState == null) {
                    this.mActiveNesting++;
                }
                int i = this.mActiveProcState;
                if (i != this.mProcState) {
                    if (i != -1) {
                        long addedDuration = (this.mActiveDuration + now) - this.mActiveStartUptime;
                        this.mActiveStartUptime = now;
                        if (this.mAssociationState != null) {
                            startActive = true;
                        }
                        if (addedDuration != 0) {
                            if (this.mActiveDurations == null) {
                                makeDurations();
                            }
                            this.mActiveDurations.addDuration(this.mActiveProcState, addedDuration);
                            this.mActiveDuration = 0L;
                        }
                    }
                    this.mActiveProcState = this.mProcState;
                }
            } else if (this.mAssociationState != null) {
                Slog.wtf("ProcessStats", "startActive while not tracking: " + this);
            }
            if (this.mAssociationState != null && (commonSource = getCommonSourceState(true)) != null && startActive) {
                commonSource.startActive(now);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void stopActive(long now) {
            SourceState commonSource;
            boolean stopActive = false;
            if (this.mActiveStartUptime != 0) {
                if (!this.mInTrackingList && this.mAssociationState != null) {
                    Slog.wtf("ProcessStats", "stopActive while not tracking: " + this);
                }
                stopActive = true;
                int i = this.mActiveNesting - 1;
                this.mActiveNesting = i;
                long addedDuration = now - this.mActiveStartUptime;
                long j = (this.mAssociationState != null || i == 0) ? 0L : now;
                this.mActiveStartUptime = j;
                if (j != 0) {
                    stopActive = false;
                }
                DurationsTable durationsTable = this.mActiveDurations;
                if (durationsTable != null) {
                    durationsTable.addDuration(this.mActiveProcState, addedDuration);
                } else {
                    this.mActiveDuration += addedDuration;
                }
                AssociationState associationState = this.mAssociationState;
                if (associationState != null) {
                    AssociationState.access$010(associationState);
                    if (this.mAssociationState.mTotalActiveNesting == 0) {
                        AssociationState associationState2 = this.mAssociationState;
                        AssociationState.access$314(associationState2, now - associationState2.mTotalActiveStartUptime);
                        this.mAssociationState.mTotalActiveStartUptime = 0L;
                    }
                }
            }
            if (this.mAssociationState != null && (commonSource = getCommonSourceState(false)) != null && stopActive) {
                commonSource.stopActive(now);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean stopActiveIfNecessary(int curSeq, long now) {
            if (this.mProcStateSeq == curSeq && this.mProcState < 11) {
                return false;
            }
            stopActive(now);
            stopTrackingProcState();
            return true;
        }

        private void stopTrackingProcState() {
            SourceState commonSource;
            this.mInTrackingList = false;
            this.mProcState = -1;
            if (this.mAssociationState != null && (commonSource = getCommonSourceState(false)) != null) {
                commonSource.stopTrackingProcState();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isInUse() {
            return this.mNesting > 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void resetSafely(long now) {
            SourceState commonSource;
            if (isInUse()) {
                this.mCount = 1;
                this.mStartUptime = now;
                this.mDuration = 0L;
                if (this.mActiveStartUptime > 0) {
                    this.mActiveCount = 1;
                    this.mActiveStartUptime = now;
                } else {
                    this.mActiveCount = 0;
                }
                this.mActiveDuration = 0L;
                this.mActiveDurations = null;
            }
            if (this.mAssociationState != null && (commonSource = getCommonSourceState(false)) != null) {
                commonSource.resetSafely(now);
                this.mCommonSourceState = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void commitStateTime(long nowUptime) {
            if (this.mNesting > 0) {
                this.mDuration += nowUptime - this.mStartUptime;
                this.mStartUptime = nowUptime;
            }
            long j = this.mActiveStartUptime;
            if (j > 0) {
                long addedDuration = nowUptime - j;
                this.mActiveStartUptime = nowUptime;
                DurationsTable durationsTable = this.mActiveDurations;
                if (durationsTable != null) {
                    durationsTable.addDuration(this.mActiveProcState, addedDuration);
                } else {
                    this.mActiveDuration += addedDuration;
                }
            }
        }

        void makeDurations() {
            this.mActiveDurations = new DurationsTable(this.mProcessStats.mTableData);
        }

        private void stopTracking(long now) {
            AssociationState associationState = this.mAssociationState;
            if (associationState != null) {
                AssociationState.access$410(associationState);
                if (this.mAssociationState.mTotalNesting == 0) {
                    AssociationState associationState2 = this.mAssociationState;
                    AssociationState.access$514(associationState2, now - associationState2.mTotalStartUptime);
                }
            }
            stopActive(now);
            if (this.mInTrackingList) {
                this.mInTrackingList = false;
                this.mProcState = -1;
                if (this.mAssociationState != null) {
                    ArrayList<SourceState> list = this.mProcessStats.mTrackingAssociations;
                    for (int i = list.size() - 1; i >= 0; i--) {
                        if (list.get(i) == this) {
                            list.remove(i);
                            return;
                        }
                    }
                    Slog.wtf("ProcessStats", "Stop tracking didn't find in tracking list: " + this);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void add(SourceState otherSrc) {
            this.mCount += otherSrc.mCount;
            this.mDuration += otherSrc.mDuration;
            this.mActiveCount += otherSrc.mActiveCount;
            long j = otherSrc.mActiveDuration;
            if (j != 0 || otherSrc.mActiveDurations != null) {
                DurationsTable durationsTable = this.mActiveDurations;
                if (durationsTable != null) {
                    DurationsTable durationsTable2 = otherSrc.mActiveDurations;
                    if (durationsTable2 != null) {
                        durationsTable.addDurations(durationsTable2);
                    } else {
                        durationsTable.addDuration(otherSrc.mActiveProcState, j);
                    }
                } else if (otherSrc.mActiveDurations != null) {
                    makeDurations();
                    this.mActiveDurations.addDurations(otherSrc.mActiveDurations);
                    long j2 = this.mActiveDuration;
                    if (j2 != 0) {
                        this.mActiveDurations.addDuration(this.mActiveProcState, j2);
                        this.mActiveDuration = 0L;
                        this.mActiveProcState = -1;
                    }
                } else {
                    long j3 = this.mActiveDuration;
                    if (j3 == 0) {
                        this.mActiveProcState = otherSrc.mActiveProcState;
                        this.mActiveDuration = j;
                    } else if (this.mActiveProcState == otherSrc.mActiveProcState) {
                        this.mActiveDuration = j3 + j;
                    } else {
                        makeDurations();
                        this.mActiveDurations.addDuration(this.mActiveProcState, this.mActiveDuration);
                        this.mActiveDurations.addDuration(otherSrc.mActiveProcState, otherSrc.mActiveDuration);
                        this.mActiveDuration = 0L;
                        this.mActiveProcState = -1;
                    }
                }
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.mCount);
            out.writeLong(this.mDuration);
            out.writeInt(this.mActiveCount);
            if (this.mActiveDurations != null) {
                out.writeInt(1);
                this.mActiveDurations.writeToParcel(out);
                return;
            }
            out.writeInt(0);
            out.writeInt(this.mActiveProcState);
            out.writeLong(this.mActiveDuration);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String readFromParcel(Parcel in) {
            this.mCount = in.readInt();
            this.mDuration = in.readLong();
            this.mActiveCount = in.readInt();
            if (in.readInt() != 0) {
                makeDurations();
                if (this.mActiveDurations.readFromParcel(in)) {
                    return null;
                }
                return "Duration table corrupt: " + this.mKey + " <- " + toString();
            }
            this.mActiveProcState = in.readInt();
            this.mActiveDuration = in.readLong();
            return null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("SourceState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" ");
            sb.append(this.mKey.mProcess);
            sb.append("/");
            sb.append(this.mKey.mUid);
            if (this.mProcState != -1) {
                sb.append(" ");
                sb.append(DumpUtils.STATE_NAMES[this.mProcState]);
                sb.append(" #");
                sb.append(this.mProcStateSeq);
            }
            sb.append("}");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class SourceDumpContainer {
        public long mActiveTime;
        public final SourceState mState;
        public long mTotalTime;

        public SourceDumpContainer(SourceState state) {
            this.mState = state;
        }
    }

    /* loaded from: classes4.dex */
    public static final class SourceKey {
        String mPackage;
        String mProcess;
        int mUid;

        SourceKey(int uid, String process, String pkg) {
            this.mUid = uid;
            this.mProcess = process;
            this.mPackage = pkg;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public SourceKey(ProcessStats stats, Parcel in, int parcelVersion) {
            this.mUid = in.readInt();
            this.mProcess = stats.readCommonString(in, parcelVersion);
            this.mPackage = stats.readCommonString(in, parcelVersion);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void writeToParcel(ProcessStats stats, Parcel out) {
            out.writeInt(this.mUid);
            stats.writeCommonString(out, this.mProcess);
            stats.writeCommonString(out, this.mPackage);
        }

        public boolean equals(Object o) {
            if (!(o instanceof SourceKey)) {
                return false;
            }
            SourceKey s = (SourceKey) o;
            return s.mUid == this.mUid && Objects.equals(s.mProcess, this.mProcess) && Objects.equals(s.mPackage, this.mPackage);
        }

        public int hashCode() {
            int hashCode = Integer.hashCode(this.mUid);
            String str = this.mProcess;
            int i = 0;
            int hashCode2 = hashCode ^ (str == null ? 0 : str.hashCode());
            String str2 = this.mPackage;
            if (str2 != null) {
                i = str2.hashCode() * 33;
            }
            return hashCode2 ^ i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("SourceKey{");
            UserHandle.formatUid(sb, this.mUid);
            sb.append(' ');
            sb.append(this.mProcess);
            sb.append(' ');
            sb.append(this.mPackage);
            sb.append('}');
            return sb.toString();
        }
    }

    public AssociationState(ProcessStats processStats, ProcessStats.PackageState packageState, String name, String processName, ProcessState proc) {
        this.mProcessStats = processStats;
        this.mPackageState = packageState;
        this.mName = name;
        this.mProcessName = processName;
        this.mProc = proc;
    }

    public int getUid() {
        return this.mPackageState.mUid;
    }

    public String getPackage() {
        return this.mPackageState.mPackageName;
    }

    public String getProcessName() {
        return this.mProcessName;
    }

    public String getName() {
        return this.mName;
    }

    public ProcessState getProcess() {
        return this.mProc;
    }

    public void setProcess(ProcessState proc) {
        this.mProc = proc;
    }

    public long getTotalDuration(long now) {
        return this.mTotalDuration + (this.mTotalNesting > 0 ? now - this.mTotalStartUptime : 0L);
    }

    public long getActiveDuration(long now) {
        return this.mTotalActiveDuration + (this.mTotalActiveNesting > 0 ? now - this.mTotalActiveStartUptime : 0L);
    }

    public SourceState startSource(int uid, String processName, String packageName) {
        SourceState src;
        SourceKey sourceKey = sTmpSourceKey;
        synchronized (sourceKey) {
            sourceKey.mUid = uid;
            sourceKey.mProcess = processName;
            sourceKey.mPackage = packageName;
            src = this.mSources.get(sourceKey);
        }
        if (src == null) {
            SourceKey key = new SourceKey(uid, processName, packageName);
            src = new SourceState(this.mProcessStats, this, this.mProc, key);
            this.mSources.put(key, src);
        }
        long now = src.start();
        if (now > 0) {
            int i = this.mTotalNesting + 1;
            this.mTotalNesting = i;
            if (i == 1) {
                this.mTotalCount++;
                this.mTotalStartUptime = now;
            }
        }
        return src;
    }

    public void add(AssociationState other) {
        this.mTotalCount += other.mTotalCount;
        long j = this.mTotalDuration;
        this.mTotalDuration += other.mTotalDuration;
        this.mTotalActiveCount += other.mTotalActiveCount;
        this.mTotalActiveDuration += other.mTotalActiveDuration;
        for (int isrc = other.mSources.size() - 1; isrc >= 0; isrc--) {
            SourceKey key = other.mSources.keyAt(isrc);
            SourceState otherSrc = other.mSources.valueAt(isrc);
            SourceState mySrc = this.mSources.get(key);
            if (mySrc == null) {
                mySrc = new SourceState(this.mProcessStats, this, this.mProc, key);
                this.mSources.put(key, mySrc);
            }
            mySrc.add(otherSrc);
        }
    }

    public boolean isInUse() {
        return this.mTotalNesting > 0;
    }

    public void resetSafely(long now) {
        if (!isInUse()) {
            this.mSources.clear();
            this.mTotalActiveCount = 0;
            this.mTotalCount = 0;
        } else {
            for (int isrc = this.mSources.size() - 1; isrc >= 0; isrc--) {
                SourceState src = this.mSources.valueAt(isrc);
                if (src.isInUse()) {
                    src.resetSafely(now);
                } else {
                    this.mSources.removeAt(isrc);
                }
            }
            this.mTotalCount = 1;
            this.mTotalStartUptime = now;
            if (this.mTotalActiveNesting > 0) {
                this.mTotalActiveCount = 1;
                this.mTotalActiveStartUptime = now;
            } else {
                this.mTotalActiveCount = 0;
            }
        }
        this.mTotalActiveDuration = 0L;
        this.mTotalDuration = 0L;
    }

    public void writeToParcel(ProcessStats stats, Parcel out, long nowUptime) {
        out.writeInt(this.mTotalCount);
        out.writeLong(this.mTotalDuration);
        out.writeInt(this.mTotalActiveCount);
        out.writeLong(this.mTotalActiveDuration);
        int NSRC = this.mSources.size();
        out.writeInt(NSRC);
        for (int isrc = 0; isrc < NSRC; isrc++) {
            SourceKey key = this.mSources.keyAt(isrc);
            SourceState src = this.mSources.valueAt(isrc);
            key.writeToParcel(stats, out);
            src.writeToParcel(out, 0);
        }
    }

    public String readFromParcel(ProcessStats stats, Parcel in, int parcelVersion) {
        this.mTotalCount = in.readInt();
        this.mTotalDuration = in.readLong();
        this.mTotalActiveCount = in.readInt();
        this.mTotalActiveDuration = in.readLong();
        int NSRC = in.readInt();
        if (NSRC < 0 || NSRC > 100000) {
            return "Association with bad src count: " + NSRC;
        }
        for (int isrc = 0; isrc < NSRC; isrc++) {
            SourceKey key = new SourceKey(stats, in, parcelVersion);
            SourceState src = new SourceState(this.mProcessStats, this, this.mProc, key);
            String errMsg = src.readFromParcel(in);
            if (errMsg != null) {
                return errMsg;
            }
            this.mSources.put(key, src);
        }
        return null;
    }

    public void commitStateTime(long nowUptime) {
        if (isInUse()) {
            for (int isrc = this.mSources.size() - 1; isrc >= 0; isrc--) {
                SourceState src = this.mSources.valueAt(isrc);
                src.commitStateTime(nowUptime);
            }
            int isrc2 = this.mTotalNesting;
            if (isrc2 > 0) {
                this.mTotalDuration += nowUptime - this.mTotalStartUptime;
                this.mTotalStartUptime = nowUptime;
            }
            if (this.mTotalActiveNesting > 0) {
                this.mTotalActiveDuration += nowUptime - this.mTotalActiveStartUptime;
                this.mTotalActiveStartUptime = nowUptime;
            }
        }
    }

    public boolean hasProcessOrPackage(String procName) {
        if (this.mProcessName.equals(procName)) {
            return true;
        }
        int NSRC = this.mSources.size();
        for (int isrc = 0; isrc < NSRC; isrc++) {
            SourceKey key = this.mSources.keyAt(isrc);
            if (procName.equals(key.mProcess) || procName.equals(key.mPackage)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(Pair o1, Pair o2) {
        int diff;
        if (((SourceDumpContainer) o1.second).mActiveTime != ((SourceDumpContainer) o2.second).mActiveTime) {
            return ((SourceDumpContainer) o1.second).mActiveTime > ((SourceDumpContainer) o2.second).mActiveTime ? -1 : 1;
        }
        if (((SourceDumpContainer) o1.second).mTotalTime != ((SourceDumpContainer) o2.second).mTotalTime) {
            return ((SourceDumpContainer) o1.second).mTotalTime > ((SourceDumpContainer) o2.second).mTotalTime ? -1 : 1;
        }
        if (((SourceKey) o1.first).mUid != ((SourceKey) o2.first).mUid) {
            return ((SourceKey) o1.first).mUid < ((SourceKey) o2.first).mUid ? -1 : 1;
        }
        if (((SourceKey) o1.first).mProcess == ((SourceKey) o2.first).mProcess || (diff = ((SourceKey) o1.first).mProcess.compareTo(((SourceKey) o2.first).mProcess)) == 0) {
            return 0;
        }
        return diff;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<Pair<SourceKey, SourceDumpContainer>> createSortedAssociations(long now, long totalTime, ArrayMap<SourceKey, SourceState> inSources) {
        long duration;
        int numOfSources = inSources.size();
        ArrayList<Pair<SourceKey, SourceDumpContainer>> sources = new ArrayList<>(numOfSources);
        for (int isrc = 0; isrc < numOfSources; isrc++) {
            SourceState src = inSources.valueAt(isrc);
            SourceDumpContainer cont = new SourceDumpContainer(src);
            long duration2 = src.mDuration;
            if (src.mNesting > 0) {
                duration = duration2 + (now - src.mStartUptime);
            } else {
                duration = duration2;
            }
            cont.mTotalTime = duration;
            cont.mActiveTime = dumpTime(null, null, src, totalTime, now, false, false);
            if (cont.mActiveTime < 0) {
                cont.mActiveTime = -cont.mActiveTime;
            }
            sources.add(new Pair<>(inSources.keyAt(isrc), cont));
        }
        Collections.sort(sources, ASSOCIATION_COMPARATOR);
        return sources;
    }

    public void dumpStats(PrintWriter pw, String prefix, String prefixInner, String headerPrefix, ArrayList<Pair<SourceKey, SourceDumpContainer>> sources, long now, long totalTime, String reqPackage, boolean dumpDetails, boolean dumpAll) {
        long totalDuration;
        String prefixInnerInner = prefixInner + "     ";
        long totalDuration2 = this.mTotalActiveDuration;
        if (this.mTotalActiveNesting > 0) {
            totalDuration2 += now - this.mTotalActiveStartUptime;
        }
        if (totalDuration2 > 0 || this.mTotalActiveCount != 0) {
            pw.print(prefix);
            pw.print("Active count ");
            pw.print(this.mTotalActiveCount);
            if (dumpAll) {
                pw.print(": ");
                TimeUtils.formatDuration(totalDuration2, pw);
                pw.print(" / ");
            } else {
                pw.print(": time ");
            }
            DumpUtils.printPercent(pw, totalDuration2 / totalTime);
            pw.println();
        }
        if (dumpAll && this.mTotalActiveNesting != 0) {
            pw.print(prefix);
            pw.print("mTotalActiveNesting=");
            pw.print(this.mTotalActiveNesting);
            pw.print(" mTotalActiveStartUptime=");
            TimeUtils.formatDuration(this.mTotalActiveStartUptime, now, pw);
            pw.println();
        }
        long totalDuration3 = this.mTotalDuration;
        if (this.mTotalNesting > 0) {
            totalDuration = totalDuration3 + (now - this.mTotalStartUptime);
        } else {
            totalDuration = totalDuration3;
        }
        if (totalDuration > 0 || this.mTotalCount != 0) {
            pw.print(prefix);
            pw.print("Total count ");
            pw.print(this.mTotalCount);
            if (dumpAll) {
                pw.print(": ");
                TimeUtils.formatDuration(totalDuration, pw);
                pw.print(" / ");
            } else {
                pw.print(": time ");
            }
            DumpUtils.printPercent(pw, totalDuration / totalTime);
            pw.println();
        }
        if (dumpAll && this.mTotalNesting != 0) {
            pw.print(prefix);
            pw.print("mTotalNesting=");
            pw.print(this.mTotalNesting);
            pw.print(" mTotalStartUptime=");
            TimeUtils.formatDuration(this.mTotalStartUptime, now, pw);
            pw.println();
        }
        dumpSources(pw, prefix, prefixInner, prefixInnerInner, sources, now, totalTime, reqPackage, dumpDetails, dumpAll);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01d9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void dumpSources(java.io.PrintWriter r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.util.ArrayList<android.util.Pair<com.android.internal.app.procstats.AssociationState.SourceKey, com.android.internal.app.procstats.AssociationState.SourceDumpContainer>> r25, long r26, long r28, java.lang.String r30, boolean r31, boolean r32) {
        /*
            Method dump skipped, instructions count: 485
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.procstats.AssociationState.dumpSources(java.io.PrintWriter, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, long, long, java.lang.String, boolean, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void dumpActiveDurationSummary(PrintWriter pw, SourceState src, long totalTime, long now, boolean dumpAll) {
        long duration = dumpTime(null, null, src, totalTime, now, false, false);
        boolean isRunning = duration < 0;
        if (isRunning) {
            duration = -duration;
        }
        if (dumpAll) {
            TimeUtils.formatDuration(duration, pw);
            pw.print(" / ");
        } else {
            pw.print("time ");
        }
        DumpUtils.printPercent(pw, duration / totalTime);
        if (src.mActiveStartUptime > 0) {
            pw.print(" (running)");
        }
        pw.println();
    }

    static long dumpTime(PrintWriter pw, String prefix, SourceState src, long overallTime, long now, boolean dumpDetails, boolean dumpAll) {
        long time;
        String running;
        long totalTime = 0;
        boolean isRunning = false;
        int iprocstate = 0;
        while (iprocstate < 16) {
            if (src.mActiveDurations != null) {
                time = src.mActiveDurations.getValueForId((byte) iprocstate);
            } else {
                time = src.mActiveProcState == iprocstate ? src.mActiveDuration : 0L;
            }
            if (src.mActiveStartUptime == 0 || src.mActiveProcState != iprocstate) {
                running = null;
            } else {
                running = " (running)";
                isRunning = true;
                time += now - src.mActiveStartUptime;
            }
            if (time != 0) {
                if (pw != null) {
                    pw.print(prefix);
                    pw.print(DumpUtils.STATE_LABELS[iprocstate]);
                    pw.print(": ");
                    if (dumpAll) {
                        TimeUtils.formatDuration(time, pw);
                        pw.print(" / ");
                    } else {
                        pw.print("time ");
                    }
                    DumpUtils.printPercent(pw, time / overallTime);
                    if (running != null) {
                        pw.print(running);
                    }
                    pw.println();
                }
                totalTime += time;
            }
            iprocstate++;
        }
        return isRunning ? -totalTime : totalTime;
    }

    public void dumpTimesCheckin(PrintWriter pw, String pkgName, int uid, long vers, String associationName, long now) {
        int NSRC;
        AssociationState associationState = this;
        int NSRC2 = associationState.mSources.size();
        int isrc = 0;
        while (isrc < NSRC2) {
            SourceKey key = associationState.mSources.keyAt(isrc);
            SourceState src = associationState.mSources.valueAt(isrc);
            pw.print("pkgasc");
            pw.print(",");
            pw.print(pkgName);
            pw.print(",");
            pw.print(uid);
            pw.print(",");
            pw.print(vers);
            pw.print(",");
            pw.print(associationName);
            pw.print(",");
            pw.print(key.mProcess);
            pw.print(",");
            pw.print(key.mUid);
            pw.print(",");
            pw.print(src.mCount);
            long duration = src.mDuration;
            if (src.mNesting > 0) {
                duration += now - src.mStartUptime;
            }
            pw.print(",");
            pw.print(duration);
            pw.print(",");
            pw.print(src.mActiveCount);
            long timeNow = src.mActiveStartUptime != 0 ? now - src.mActiveStartUptime : 0L;
            if (src.mActiveDurations != null) {
                int N = src.mActiveDurations.getKeyCount();
                int i = 0;
                while (i < N) {
                    int dkey = src.mActiveDurations.getKeyAt(i);
                    long duration2 = src.mActiveDurations.getValue(dkey);
                    if (dkey == src.mActiveProcState) {
                        duration2 += timeNow;
                    }
                    int procState = SparseMappingTable.getIdFromKey(dkey);
                    pw.print(",");
                    DumpUtils.printArrayEntry(pw, DumpUtils.STATE_TAGS, procState, 1);
                    pw.print(ShortcutConstants.SERVICES_SEPARATOR);
                    pw.print(duration2);
                    i++;
                    NSRC2 = NSRC2;
                    key = key;
                }
                NSRC = NSRC2;
            } else {
                NSRC = NSRC2;
                long duration3 = src.mActiveDuration + timeNow;
                if (duration3 != 0) {
                    pw.print(",");
                    DumpUtils.printArrayEntry(pw, DumpUtils.STATE_TAGS, src.mActiveProcState, 1);
                    pw.print(ShortcutConstants.SERVICES_SEPARATOR);
                    pw.print(duration3);
                }
            }
            pw.println();
            isrc++;
            associationState = this;
            NSRC2 = NSRC;
        }
    }

    public void dumpDebug(ProtoOutputStream proto, long fieldId, long now) {
        int isrc;
        int NSRC;
        long sourceToken;
        long sourceToken2;
        long j;
        int NSRC2;
        long j2 = now;
        long token = proto.start(fieldId);
        proto.write(1138166333441L, this.mName);
        proto.write(1120986464259L, this.mTotalCount);
        proto.write(1112396529668L, getTotalDuration(j2));
        int i = this.mTotalActiveCount;
        if (i != 0) {
            proto.write(1120986464261L, i);
            proto.write(1112396529670L, getActiveDuration(j2));
        }
        int NSRC3 = this.mSources.size();
        int isrc2 = 0;
        while (isrc2 < NSRC3) {
            SourceKey key = this.mSources.keyAt(isrc2);
            SourceState src = this.mSources.valueAt(isrc2);
            long sourceToken3 = proto.start(2246267895810L);
            proto.write(1138166333442L, key.mProcess);
            proto.write(1138166333447L, key.mPackage);
            proto.write(1120986464257L, key.mUid);
            proto.write(1120986464259L, src.mCount);
            long duration = src.mDuration;
            if (src.mNesting > 0) {
                isrc = isrc2;
                duration += j2 - src.mStartUptime;
            } else {
                isrc = isrc2;
            }
            proto.write(1112396529668L, duration);
            if (src.mActiveCount != 0) {
                NSRC = NSRC3;
                proto.write(1120986464261L, src.mActiveCount);
            } else {
                NSRC = NSRC3;
            }
            long timeNow = src.mActiveStartUptime != 0 ? j2 - src.mActiveStartUptime : 0L;
            if (src.mActiveDurations != null) {
                int N = src.mActiveDurations.getKeyCount();
                long sourceToken4 = sourceToken3;
                int i2 = 0;
                while (i2 < N) {
                    int dkey = src.mActiveDurations.getKeyAt(i2);
                    long duration2 = src.mActiveDurations.getValue(dkey);
                    if (dkey == src.mActiveProcState) {
                        duration2 += timeNow;
                    }
                    int procState = SparseMappingTable.getIdFromKey(dkey);
                    long stateToken = proto.start(2246267895814L);
                    DumpUtils.printProto(proto, 1159641169921L, DumpUtils.STATE_PROTO_ENUMS, procState, 1);
                    proto.write(1112396529666L, duration2);
                    proto.end(stateToken);
                    i2++;
                    N = N;
                    src = src;
                    sourceToken4 = sourceToken4;
                }
                NSRC2 = NSRC;
                sourceToken = sourceToken4;
                j = 1112396529668L;
                sourceToken2 = 1120986464261L;
            } else {
                sourceToken = sourceToken3;
                NSRC2 = NSRC;
                j = 1112396529668L;
                sourceToken2 = 1120986464261L;
                long duration3 = src.mActiveDuration + timeNow;
                if (duration3 != 0) {
                    long stateToken2 = proto.start(2246267895814L);
                    DumpUtils.printProto(proto, 1159641169921L, DumpUtils.STATE_PROTO_ENUMS, src.mActiveProcState, 1);
                    proto.write(1112396529666L, duration3);
                    proto.end(stateToken2);
                }
            }
            proto.end(sourceToken);
            isrc2 = isrc + 1;
            j2 = now;
            NSRC3 = NSRC2;
        }
        proto.end(token);
    }

    public String toString() {
        return "AssociationState{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.mName + " pkg=" + this.mPackageState.mPackageName + " proc=" + Integer.toHexString(System.identityHashCode(this.mProc)) + "}";
    }
}
