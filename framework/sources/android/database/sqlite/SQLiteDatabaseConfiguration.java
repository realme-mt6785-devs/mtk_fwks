package android.database.sqlite;

import android.util.ArrayMap;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public final class SQLiteDatabaseConfiguration {
    private static final Pattern EMAIL_IN_DB_PATTERN = Pattern.compile("[\\w\\.\\-]+@[\\w\\.\\-]+");
    public static final String MEMORY_DB_PATH = ":memory:";
    public boolean foreignKeyConstraintsEnabled;
    public String journalMode;
    public final String label;
    public Locale locale;
    public int maxSqlCacheSize;
    public int openFlags;
    public final String path;
    public String syncMode;
    public final ArrayMap<String, UnaryOperator<String>> customScalarFunctions = new ArrayMap<>();
    public final ArrayMap<String, BinaryOperator<String>> customAggregateFunctions = new ArrayMap<>();
    public final ArrayList<Pair<String, Object[]>> perConnectionSql = new ArrayList<>();
    public int lookasideSlotSize = -1;
    public int lookasideSlotCount = -1;
    public long idleConnectionTimeoutMs = Long.MAX_VALUE;

    public SQLiteDatabaseConfiguration(String path, int openFlags) {
        if (path != null) {
            this.path = path;
            this.label = stripPathForLogs(path);
            this.openFlags = openFlags;
            this.maxSqlCacheSize = 25;
            this.locale = Locale.getDefault();
            return;
        }
        throw new IllegalArgumentException("path must not be null.");
    }

    public SQLiteDatabaseConfiguration(SQLiteDatabaseConfiguration other) {
        if (other != null) {
            this.path = other.path;
            this.label = other.label;
            updateParametersFrom(other);
            return;
        }
        throw new IllegalArgumentException("other must not be null.");
    }

    public void updateParametersFrom(SQLiteDatabaseConfiguration other) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null.");
        } else if (this.path.equals(other.path)) {
            this.openFlags = other.openFlags;
            this.maxSqlCacheSize = other.maxSqlCacheSize;
            this.locale = other.locale;
            this.foreignKeyConstraintsEnabled = other.foreignKeyConstraintsEnabled;
            this.customScalarFunctions.clear();
            this.customScalarFunctions.putAll((ArrayMap<? extends String, ? extends UnaryOperator<String>>) other.customScalarFunctions);
            this.customAggregateFunctions.clear();
            this.customAggregateFunctions.putAll((ArrayMap<? extends String, ? extends BinaryOperator<String>>) other.customAggregateFunctions);
            this.perConnectionSql.clear();
            this.perConnectionSql.addAll(other.perConnectionSql);
            this.lookasideSlotSize = other.lookasideSlotSize;
            this.lookasideSlotCount = other.lookasideSlotCount;
            this.idleConnectionTimeoutMs = other.idleConnectionTimeoutMs;
            this.journalMode = other.journalMode;
            this.syncMode = other.syncMode;
        } else {
            throw new IllegalArgumentException("other configuration must refer to the same database.");
        }
    }

    public boolean isInMemoryDb() {
        return this.path.equalsIgnoreCase(MEMORY_DB_PATH);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLegacyCompatibilityWalEnabled() {
        return this.journalMode == null && this.syncMode == null && (this.openFlags & Integer.MIN_VALUE) != 0;
    }

    private static String stripPathForLogs(String path) {
        if (path.indexOf(64) == -1) {
            return path;
        }
        return EMAIL_IN_DB_PATTERN.matcher(path).replaceAll("XX@YY");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLookasideConfigSet() {
        return this.lookasideSlotCount >= 0 && this.lookasideSlotSize >= 0;
    }
}
