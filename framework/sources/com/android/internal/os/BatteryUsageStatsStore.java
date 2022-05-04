package com.android.internal.os;

import android.content.Context;
import android.os.BatteryUsageStats;
import android.os.BatteryUsageStatsQuery;
import android.os.Handler;
import android.util.AtomicFile;
import android.util.LongArray;
import android.util.Slog;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import android.util.Xml;
import com.android.internal.os.BatteryStatsImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes4.dex */
public class BatteryUsageStatsStore {
    private static final String BATTERY_USAGE_STATS_BEFORE_RESET_TIMESTAMP_PROPERTY = "BATTERY_USAGE_STATS_BEFORE_RESET_TIMESTAMP";
    private static final String BATTERY_USAGE_STATS_DIR = "battery-usage-stats";
    private static final List<BatteryUsageStatsQuery> BATTERY_USAGE_STATS_QUERY = List.of(new BatteryUsageStatsQuery.Builder().setMaxStatsAgeMs(0).includePowerModels().build());
    private static final String CONFIG_FILENAME = "config";
    private static final String DIR_LOCK_FILENAME = ".lock";
    private static final long MAX_BATTERY_STATS_SNAPSHOT_STORAGE_BYTES = 102400;
    private static final String SNAPSHOT_FILE_EXTENSION = ".bus";
    private static final String TAG = "BatteryUsageStatsStore";
    private final BatteryStatsImpl mBatteryStats;
    private final BatteryUsageStatsProvider mBatteryUsageStatsProvider;
    private final AtomicFile mConfigFile;
    private final Context mContext;
    private final Handler mHandler;
    private final File mLockFile;
    private final long mMaxStorageBytes;
    private final File mStoreDir;
    private boolean mSystemReady;

    public BatteryUsageStatsStore(Context context, BatteryStatsImpl stats, File systemDir, Handler handler) {
        this(context, stats, systemDir, handler, MAX_BATTERY_STATS_SNAPSHOT_STORAGE_BYTES);
    }

    public BatteryUsageStatsStore(Context context, BatteryStatsImpl batteryStats, File systemDir, Handler handler, long maxStorageBytes) {
        this.mContext = context;
        this.mBatteryStats = batteryStats;
        File file = new File(systemDir, BATTERY_USAGE_STATS_DIR);
        this.mStoreDir = file;
        this.mLockFile = new File(file, DIR_LOCK_FILENAME);
        this.mConfigFile = new AtomicFile(new File(file, CONFIG_FILENAME));
        this.mHandler = handler;
        this.mMaxStorageBytes = maxStorageBytes;
        batteryStats.setBatteryResetListener(new BatteryStatsImpl.BatteryResetListener() { // from class: com.android.internal.os.BatteryUsageStatsStore$$ExternalSyntheticLambda0
            @Override // com.android.internal.os.BatteryStatsImpl.BatteryResetListener
            public final void prepareForBatteryStatsReset(int i) {
                BatteryUsageStatsStore.this.prepareForBatteryStatsReset(i);
            }
        });
        this.mBatteryUsageStatsProvider = new BatteryUsageStatsProvider(context, batteryStats);
    }

    public void onSystemReady() {
        this.mSystemReady = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareForBatteryStatsReset(int resetReason) {
        if (resetReason != 1 && this.mSystemReady) {
            final List<BatteryUsageStats> stats = this.mBatteryUsageStatsProvider.getBatteryUsageStats(BATTERY_USAGE_STATS_QUERY);
            if (stats.isEmpty()) {
                Slog.wtf(TAG, "No battery usage stats generated");
            } else {
                this.mHandler.post(new Runnable() { // from class: com.android.internal.os.BatteryUsageStatsStore$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BatteryUsageStatsStore.this.lambda$prepareForBatteryStatsReset$0$BatteryUsageStatsStore(stats);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$prepareForBatteryStatsReset$0$BatteryUsageStatsStore(List stats) {
        storeBatteryUsageStats((BatteryUsageStats) stats.get(0));
    }

    private void storeBatteryUsageStats(BatteryUsageStats stats) {
        try {
            FileLock lock = lockSnapshotDirectory();
            if (this.mStoreDir.exists() || this.mStoreDir.mkdirs()) {
                File file = makeSnapshotFilename(stats.getStatsEndTimestamp());
                try {
                    writeXmlFileLocked(stats, file);
                } catch (Exception e) {
                    Slog.e(TAG, "Cannot save battery usage stats", e);
                }
                removeOldSnapshotsLocked();
                if (lock != null) {
                    lock.close();
                    return;
                }
                return;
            }
            Slog.e(TAG, "Could not create a directory for battery usage stats snapshots");
            if (lock != null) {
                lock.close();
            }
        } catch (IOException e2) {
            Slog.e(TAG, "Cannot lock battery usage stats directory", e2);
        }
    }

    public long[] listBatteryUsageStatsTimestamps() {
        File[] listFiles;
        LongArray timestamps = new LongArray(100);
        try {
            FileLock lock = lockSnapshotDirectory();
            for (File file : this.mStoreDir.listFiles()) {
                String fileName = file.getName();
                if (fileName.endsWith(SNAPSHOT_FILE_EXTENSION)) {
                    try {
                        String fileNameWithoutExtension = fileName.substring(0, fileName.length() - SNAPSHOT_FILE_EXTENSION.length());
                        timestamps.add(Long.parseLong(fileNameWithoutExtension));
                    } catch (NumberFormatException e) {
                        Slog.wtf(TAG, "Invalid format of BatteryUsageStats snapshot file name: " + fileName);
                    }
                }
            }
            if (lock != null) {
                lock.close();
            }
        } catch (IOException e2) {
            Slog.e(TAG, "Cannot lock battery usage stats directory", e2);
        }
        return timestamps.toArray();
    }

    public BatteryUsageStats loadBatteryUsageStats(long timestamp) {
        try {
            FileLock lock = lockSnapshotDirectory();
            File file = makeSnapshotFilename(timestamp);
            try {
                BatteryUsageStats readXmlFileLocked = readXmlFileLocked(file);
                if (lock != null) {
                    lock.close();
                }
                return readXmlFileLocked;
            } catch (Exception e) {
                Slog.e(TAG, "Cannot read battery usage stats", e);
                if (lock == null) {
                    return null;
                }
                lock.close();
                return null;
            }
        } catch (IOException e2) {
            Slog.e(TAG, "Cannot lock battery usage stats directory", e2);
            return null;
        }
    }

    public void setLastBatteryUsageStatsBeforeResetAtomPullTimestamp(long timestamp) {
        Properties props = new Properties();
        try {
            FileLock lock = lockSnapshotDirectory();
            try {
                InputStream in = this.mConfigFile.openRead();
                try {
                    props.load(in);
                    if (in != null) {
                        in.close();
                    }
                } catch (Throwable th) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException e) {
                Slog.e(TAG, "Cannot load config file " + this.mConfigFile, e);
            }
            props.put(BATTERY_USAGE_STATS_BEFORE_RESET_TIMESTAMP_PROPERTY, String.valueOf(timestamp));
            FileOutputStream out = null;
            try {
                out = this.mConfigFile.startWrite();
                props.store(out, "Statsd atom pull timestamps");
                this.mConfigFile.finishWrite(out);
            } catch (IOException e2) {
                this.mConfigFile.failWrite(out);
                Slog.e(TAG, "Cannot save config file " + this.mConfigFile, e2);
            }
            if (lock != null) {
                lock.close();
            }
        } catch (IOException e3) {
            Slog.e(TAG, "Cannot lock battery usage stats directory", e3);
        }
    }

    public long getLastBatteryUsageStatsBeforeResetAtomPullTimestamp() {
        InputStream in;
        Properties props = new Properties();
        try {
            FileLock lock = lockSnapshotDirectory();
            try {
                in = this.mConfigFile.openRead();
            } catch (IOException e) {
                Slog.e(TAG, "Cannot load config file " + this.mConfigFile, e);
            }
            try {
                props.load(in);
                if (in != null) {
                    in.close();
                }
                if (lock != null) {
                    lock.close();
                }
            } catch (Throwable th) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            Slog.e(TAG, "Cannot lock battery usage stats directory", e2);
        }
        return Long.parseLong(props.getProperty(BATTERY_USAGE_STATS_BEFORE_RESET_TIMESTAMP_PROPERTY, "0"));
    }

    private FileLock lockSnapshotDirectory() throws IOException {
        this.mLockFile.getParentFile().mkdirs();
        this.mLockFile.createNewFile();
        return FileChannel.open(this.mLockFile.toPath(), StandardOpenOption.WRITE).lock();
    }

    private File makeSnapshotFilename(long statsEndTimestamp) {
        File file = this.mStoreDir;
        return new File(file, String.format(Locale.ENGLISH, "%019d", Long.valueOf(statsEndTimestamp)) + SNAPSHOT_FILE_EXTENSION);
    }

    private void writeXmlFileLocked(BatteryUsageStats stats, File file) throws IOException {
        OutputStream out = new FileOutputStream(file);
        try {
            TypedXmlSerializer serializer = Xml.newBinarySerializer();
            serializer.setOutput(out, StandardCharsets.UTF_8.name());
            serializer.startDocument(null, true);
            stats.writeXml(serializer);
            serializer.endDocument();
            out.close();
        } catch (Throwable th) {
            try {
                out.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private BatteryUsageStats readXmlFileLocked(File file) throws IOException, XmlPullParserException {
        InputStream in = new FileInputStream(file);
        try {
            TypedXmlPullParser parser = Xml.newBinaryPullParser();
            parser.setInput(in, StandardCharsets.UTF_8.name());
            BatteryUsageStats createFromXml = BatteryUsageStats.createFromXml(parser);
            in.close();
            return createFromXml;
        } catch (Throwable th) {
            try {
                in.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void removeOldSnapshotsLocked() {
        File[] listFiles;
        Map.Entry<File, Long> entry;
        long totalSize = 0;
        TreeMap<File, Long> mFileSizes = new TreeMap<>();
        for (File file : this.mStoreDir.listFiles()) {
            long fileSize = file.length();
            totalSize += fileSize;
            if (file.getName().endsWith(SNAPSHOT_FILE_EXTENSION)) {
                mFileSizes.put(file, Long.valueOf(fileSize));
            }
        }
        while (totalSize > this.mMaxStorageBytes && (entry = mFileSizes.firstEntry()) != null) {
            File file2 = entry.getKey();
            if (!file2.delete()) {
                Slog.e(TAG, "Cannot delete battery usage stats " + file2);
            }
            totalSize -= entry.getValue().longValue();
            mFileSizes.remove(file2);
        }
    }
}
