package android.util;
/* loaded from: classes3.dex */
public enum DataUnit {
    KILOBYTES { // from class: android.util.DataUnit.1
        @Override // android.util.DataUnit
        public long toBytes(long v) {
            return 1000 * v;
        }
    },
    MEGABYTES { // from class: android.util.DataUnit.2
        @Override // android.util.DataUnit
        public long toBytes(long v) {
            return TimeUtils.NANOS_PER_MS * v;
        }
    },
    GIGABYTES { // from class: android.util.DataUnit.3
        @Override // android.util.DataUnit
        public long toBytes(long v) {
            return 1000000000 * v;
        }
    },
    KIBIBYTES { // from class: android.util.DataUnit.4
        @Override // android.util.DataUnit
        public long toBytes(long v) {
            return 1024 * v;
        }
    },
    MEBIBYTES { // from class: android.util.DataUnit.5
        @Override // android.util.DataUnit
        public long toBytes(long v) {
            return 1048576 * v;
        }
    },
    GIBIBYTES { // from class: android.util.DataUnit.6
        @Override // android.util.DataUnit
        public long toBytes(long v) {
            return 1073741824 * v;
        }
    };

    public long toBytes(long v) {
        throw new AbstractMethodError();
    }
}
