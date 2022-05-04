package android.location;

import android.util.SparseArray;
import java.util.Iterator;
import java.util.NoSuchElementException;
@Deprecated
/* loaded from: classes2.dex */
public final class GpsStatus {
    private static final int BEIDOU_SVID_OFFSET = 200;
    private static final int GLONASS_SVID_OFFSET = 64;
    public static final int GPS_EVENT_FIRST_FIX = 3;
    public static final int GPS_EVENT_SATELLITE_STATUS = 4;
    public static final int GPS_EVENT_STARTED = 1;
    public static final int GPS_EVENT_STOPPED = 2;
    private static final int MAX_SATELLITES = 255;
    private static final int SBAS_SVID_OFFSET = -87;
    private int mTimeToFirstFix;
    private final SparseArray<GpsSatellite> mSatellites = new SparseArray<>();
    private Iterable<GpsSatellite> mSatelliteList = new Iterable() { // from class: android.location.GpsStatus$$ExternalSyntheticLambda0
        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return GpsStatus.this.lambda$new$0$GpsStatus();
        }
    };

    @Deprecated
    /* loaded from: classes2.dex */
    public interface Listener {
        void onGpsStatusChanged(int i);
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public interface NmeaListener {
        void onNmeaReceived(long j, String str);
    }

    /* loaded from: classes2.dex */
    private final class SatelliteIterator implements Iterator<GpsSatellite> {
        private int mIndex = 0;
        private final int mSatellitesCount;

        SatelliteIterator() {
            this.mSatellitesCount = GpsStatus.this.mSatellites.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.mIndex < this.mSatellitesCount) {
                GpsSatellite satellite = (GpsSatellite) GpsStatus.this.mSatellites.valueAt(this.mIndex);
                if (satellite.mValid) {
                    return true;
                }
                this.mIndex++;
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public GpsSatellite next() {
            while (this.mIndex < this.mSatellitesCount) {
                GpsSatellite satellite = (GpsSatellite) GpsStatus.this.mSatellites.valueAt(this.mIndex);
                this.mIndex++;
                if (satellite.mValid) {
                    return satellite;
                }
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public /* synthetic */ Iterator lambda$new$0$GpsStatus() {
        return new SatelliteIterator();
    }

    public static GpsStatus create(GnssStatus gnssStatus, int timeToFirstFix) {
        GpsStatus status = new GpsStatus();
        status.setStatus(gnssStatus, timeToFirstFix);
        return status;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GpsStatus createEmpty() {
        return new GpsStatus();
    }

    private GpsStatus() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatus(GnssStatus status, int timeToFirstFix) {
        for (int i = 0; i < this.mSatellites.size(); i++) {
            this.mSatellites.valueAt(i).mValid = false;
        }
        this.mTimeToFirstFix = timeToFirstFix;
        for (int i2 = 0; i2 < status.getSatelliteCount(); i2++) {
            int constellationType = status.getConstellationType(i2);
            int prn = status.getSvid(i2);
            if (constellationType == 3) {
                prn += 64;
            } else if (constellationType == 5) {
                prn += 200;
            } else if (constellationType == 2) {
                prn += SBAS_SVID_OFFSET;
            } else if (!(constellationType == 1 || constellationType == 4)) {
            }
            if (prn > 0 && prn <= 255) {
                GpsSatellite satellite = this.mSatellites.get(prn);
                if (satellite == null) {
                    satellite = new GpsSatellite(prn);
                    this.mSatellites.put(prn, satellite);
                }
                satellite.mValid = true;
                satellite.mSnr = status.getCn0DbHz(i2);
                satellite.mElevation = status.getElevationDegrees(i2);
                satellite.mAzimuth = status.getAzimuthDegrees(i2);
                satellite.mHasEphemeris = status.hasEphemerisData(i2);
                satellite.mHasAlmanac = status.hasAlmanacData(i2);
                satellite.mUsedInFix = status.usedInFix(i2);
            }
        }
    }

    public int getTimeToFirstFix() {
        return this.mTimeToFirstFix;
    }

    public Iterable<GpsSatellite> getSatellites() {
        return this.mSatelliteList;
    }

    public int getMaxSatellites() {
        return this.mSatellites.size();
    }
}
