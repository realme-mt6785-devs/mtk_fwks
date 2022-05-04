package android.location;

import android.annotation.SystemApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.SettingsStringUtil;
import android.util.Printer;
import android.util.TimeUtils;
import com.android.internal.accessibility.common.ShortcutConstants;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class Location implements Parcelable {
    @SystemApi
    @Deprecated
    public static final String EXTRA_NO_GPS_LOCATION = "noGPSLocation";
    public static final int FORMAT_DEGREES = 0;
    public static final int FORMAT_MINUTES = 1;
    public static final int FORMAT_SECONDS = 2;
    private static final int HAS_ALTITUDE_MASK = 1;
    private static final int HAS_BEARING_ACCURACY_MASK = 128;
    private static final int HAS_BEARING_MASK = 4;
    private static final int HAS_ELAPSED_REALTIME_UNCERTAINTY_MASK = 256;
    private static final int HAS_HORIZONTAL_ACCURACY_MASK = 8;
    private static final int HAS_MOCK_PROVIDER_MASK = 16;
    private static final int HAS_SPEED_ACCURACY_MASK = 64;
    private static final int HAS_SPEED_MASK = 2;
    private static final int HAS_VERTICAL_ACCURACY_MASK = 32;
    private String mProvider;
    private static final ThreadLocal<BearingDistanceCache> sBearingDistanceCache = ThreadLocal.withInitial(Location$$ExternalSyntheticLambda0.INSTANCE);
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() { // from class: android.location.Location.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Location createFromParcel(Parcel in) {
            Location l = new Location(in.readString());
            l.mFieldsMask = in.readInt();
            l.mTime = in.readLong();
            l.mElapsedRealtimeNanos = in.readLong();
            if (l.hasElapsedRealtimeUncertaintyNanos()) {
                l.mElapsedRealtimeUncertaintyNanos = in.readDouble();
            }
            l.mLatitude = in.readDouble();
            l.mLongitude = in.readDouble();
            if (l.hasAltitude()) {
                l.mAltitude = in.readDouble();
            }
            if (l.hasSpeed()) {
                l.mSpeed = in.readFloat();
            }
            if (l.hasBearing()) {
                l.mBearing = in.readFloat();
            }
            if (l.hasAccuracy()) {
                l.mHorizontalAccuracyMeters = in.readFloat();
            }
            if (l.hasVerticalAccuracy()) {
                l.mVerticalAccuracyMeters = in.readFloat();
            }
            if (l.hasSpeedAccuracy()) {
                l.mSpeedAccuracyMetersPerSecond = in.readFloat();
            }
            if (l.hasBearingAccuracy()) {
                l.mBearingAccuracyDegrees = in.readFloat();
            }
            l.mExtras = Bundle.setDefusable(in.readBundle(), true);
            return l;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
    private long mTime = 0;
    private long mElapsedRealtimeNanos = 0;
    private double mElapsedRealtimeUncertaintyNanos = 0.0d;
    private double mLatitude = 0.0d;
    private double mLongitude = 0.0d;
    private double mAltitude = 0.0d;
    private float mSpeed = 0.0f;
    private float mBearing = 0.0f;
    private float mHorizontalAccuracyMeters = 0.0f;
    private float mVerticalAccuracyMeters = 0.0f;
    private float mSpeedAccuracyMetersPerSecond = 0.0f;
    private float mBearingAccuracyDegrees = 0.0f;
    private Bundle mExtras = null;
    private int mFieldsMask = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BearingDistanceCache lambda$static$0() {
        return new BearingDistanceCache();
    }

    public Location(String provider) {
        this.mProvider = provider;
    }

    public Location(Location l) {
        set(l);
    }

    public void set(Location l) {
        this.mProvider = l.mProvider;
        this.mTime = l.mTime;
        this.mElapsedRealtimeNanos = l.mElapsedRealtimeNanos;
        this.mElapsedRealtimeUncertaintyNanos = l.mElapsedRealtimeUncertaintyNanos;
        this.mFieldsMask = l.mFieldsMask;
        this.mLatitude = l.mLatitude;
        this.mLongitude = l.mLongitude;
        this.mAltitude = l.mAltitude;
        this.mSpeed = l.mSpeed;
        this.mBearing = l.mBearing;
        this.mHorizontalAccuracyMeters = l.mHorizontalAccuracyMeters;
        this.mVerticalAccuracyMeters = l.mVerticalAccuracyMeters;
        this.mSpeedAccuracyMetersPerSecond = l.mSpeedAccuracyMetersPerSecond;
        this.mBearingAccuracyDegrees = l.mBearingAccuracyDegrees;
        this.mExtras = l.mExtras == null ? null : new Bundle(l.mExtras);
    }

    public void reset() {
        this.mProvider = null;
        this.mTime = 0L;
        this.mElapsedRealtimeNanos = 0L;
        this.mElapsedRealtimeUncertaintyNanos = 0.0d;
        this.mFieldsMask = 0;
        this.mLatitude = 0.0d;
        this.mLongitude = 0.0d;
        this.mAltitude = 0.0d;
        this.mSpeed = 0.0f;
        this.mBearing = 0.0f;
        this.mHorizontalAccuracyMeters = 0.0f;
        this.mVerticalAccuracyMeters = 0.0f;
        this.mSpeedAccuracyMetersPerSecond = 0.0f;
        this.mBearingAccuracyDegrees = 0.0f;
        this.mExtras = null;
    }

    public static String convert(double coordinate, int outputType) {
        if (coordinate < -180.0d || coordinate > 180.0d || Double.isNaN(coordinate)) {
            throw new IllegalArgumentException("coordinate=" + coordinate);
        } else if (outputType == 0 || outputType == 1 || outputType == 2) {
            StringBuilder sb = new StringBuilder();
            if (coordinate < 0.0d) {
                sb.append('-');
                coordinate = -coordinate;
            }
            DecimalFormat df = new DecimalFormat("###.#####");
            if (outputType == 1 || outputType == 2) {
                int degrees = (int) Math.floor(coordinate);
                sb.append(degrees);
                sb.append(ShortcutConstants.SERVICES_SEPARATOR);
                coordinate = (coordinate - degrees) * 60.0d;
                if (outputType == 2) {
                    int minutes = (int) Math.floor(coordinate);
                    sb.append(minutes);
                    sb.append(ShortcutConstants.SERVICES_SEPARATOR);
                    coordinate = (coordinate - minutes) * 60.0d;
                }
            }
            sb.append(df.format(coordinate));
            return sb.toString();
        } else {
            throw new IllegalArgumentException("outputType=" + outputType);
        }
    }

    public static double convert(String coordinate) {
        boolean negative;
        String coordinate2;
        double min;
        if (coordinate != null) {
            if (coordinate.charAt(0) == '-') {
                negative = true;
                coordinate2 = coordinate.substring(1);
            } else {
                negative = false;
                coordinate2 = coordinate;
            }
            StringTokenizer st = new StringTokenizer(coordinate2, SettingsStringUtil.DELIMITER);
            int tokens = st.countTokens();
            if (tokens >= 1) {
                try {
                    String degrees = st.nextToken();
                    try {
                        if (tokens == 1) {
                            double val = Double.parseDouble(degrees);
                            return negative ? -val : val;
                        }
                        String minutes = st.nextToken();
                        int deg = Integer.parseInt(degrees);
                        double sec = 0.0d;
                        boolean secPresent = false;
                        if (st.hasMoreTokens()) {
                            min = Integer.parseInt(minutes);
                            String seconds = st.nextToken();
                            sec = Double.parseDouble(seconds);
                            secPresent = true;
                        } else {
                            min = Double.parseDouble(minutes);
                        }
                        boolean isNegative180 = negative && deg == 180 && min == 0.0d && sec == 0.0d;
                        try {
                            if (deg >= 0.0d && (deg <= 179 || isNegative180)) {
                                if (min >= 0.0d && min < 60.0d) {
                                    if (!secPresent || min <= 59.0d) {
                                        if (sec < 0.0d || sec >= 60.0d) {
                                            throw new IllegalArgumentException("coordinate=" + coordinate2);
                                        }
                                        double val2 = (((deg * 3600.0d) + (60.0d * min)) + sec) / 3600.0d;
                                        return negative ? -val2 : val2;
                                    }
                                }
                                throw new IllegalArgumentException("coordinate=" + coordinate2);
                            }
                            throw new IllegalArgumentException("coordinate=" + coordinate2);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("coordinate=" + coordinate2);
                        }
                    } catch (NumberFormatException e2) {
                    }
                } catch (NumberFormatException e3) {
                }
            } else {
                throw new IllegalArgumentException("coordinate=" + coordinate2);
            }
        } else {
            throw new NullPointerException("coordinate");
        }
    }

    private static void computeDistanceAndBearing(double lat1, double lon1, double lat2, double lon2, BearingDistanceCache results) {
        double lat22;
        double lon12;
        double sinAlpha;
        double lat12 = lat1 * 0.017453292519943295d;
        double cosSigma = lat2 * 0.017453292519943295d;
        double sinSigma = lon1 * 0.017453292519943295d;
        double lon22 = 0.017453292519943295d * lon2;
        double f = (6378137.0d - 6356752.3142d) / 6378137.0d;
        double aSqMinusBSqOverBSq = ((6378137.0d * 6378137.0d) - (6356752.3142d * 6356752.3142d)) / (6356752.3142d * 6356752.3142d);
        double L = lon22 - sinSigma;
        double A = 0.0d;
        double U1 = Math.atan((1.0d - f) * Math.tan(lat12));
        double U2 = Math.atan((1.0d - f) * Math.tan(cosSigma));
        double cosU1 = Math.cos(U1);
        double cosU2 = Math.cos(U2);
        double sinU1 = Math.sin(U1);
        double sinU2 = Math.sin(U2);
        double cosU1cosU2 = cosU1 * cosU2;
        double sinU1sinU2 = sinU1 * sinU2;
        double sigma = 0.0d;
        double deltaSigma = 0.0d;
        double cosLambda = 0.0d;
        double sinLambda = 0.0d;
        double lambda = L;
        int iter = 0;
        while (true) {
            if (iter >= 20) {
                lat22 = cosSigma;
                lon12 = sinSigma;
                break;
            }
            cosLambda = Math.cos(lambda);
            sinLambda = Math.sin(lambda);
            double t1 = cosU2 * sinLambda;
            double t2 = (cosU1 * sinU2) - ((sinU1 * cosU2) * cosLambda);
            double sinSqSigma = (t1 * t1) + (t2 * t2);
            lon12 = sinSigma;
            double sinSigma2 = Math.sqrt(sinSqSigma);
            lat22 = cosSigma;
            double lat23 = sinU1sinU2 + (cosU1cosU2 * cosLambda);
            sigma = Math.atan2(sinSigma2, lat23);
            double cos2SM = 0.0d;
            if (sinSigma2 == 0.0d) {
                sinAlpha = 0.0d;
            } else {
                sinAlpha = (cosU1cosU2 * sinLambda) / sinSigma2;
            }
            double cosSqAlpha = 1.0d - (sinAlpha * sinAlpha);
            if (cosSqAlpha != 0.0d) {
                cos2SM = lat23 - ((sinU1sinU2 * 2.0d) / cosSqAlpha);
            }
            double uSquared = cosSqAlpha * aSqMinusBSqOverBSq;
            A = ((uSquared / 16384.0d) * (((((320.0d - (175.0d * uSquared)) * uSquared) - 768.0d) * uSquared) + 4096.0d)) + 1.0d;
            double B = (uSquared / 1024.0d) * (((((74.0d - (47.0d * uSquared)) * uSquared) - 128.0d) * uSquared) + 256.0d);
            double C = (f / 16.0d) * cosSqAlpha * (((4.0d - (3.0d * cosSqAlpha)) * f) + 4.0d);
            double cos2SMSq = cos2SM * cos2SM;
            deltaSigma = B * sinSigma2 * (cos2SM + ((B / 4.0d) * ((((cos2SMSq * 2.0d) - 1.0d) * lat23) - ((((B / 6.0d) * cos2SM) * (((sinSigma2 * 4.0d) * sinSigma2) - 3.0d)) * ((4.0d * cos2SMSq) - 3.0d)))));
            lambda = L + ((1.0d - C) * f * sinAlpha * (sigma + (C * sinSigma2 * (cos2SM + (C * lat23 * (((2.0d * cos2SM) * cos2SM) - 1.0d))))));
            double delta = (lambda - lambda) / lambda;
            if (Math.abs(delta) < 1.0E-12d) {
                break;
            }
            iter++;
            sinSigma = lon12;
            cosSigma = lat22;
        }
        results.mDistance = (float) (6356752.3142d * A * (sigma - deltaSigma));
        float initialBearing = (float) Math.atan2(cosU2 * sinLambda, (cosU1 * sinU2) - ((sinU1 * cosU2) * cosLambda));
        results.mInitialBearing = (float) (initialBearing * 57.29577951308232d);
        float finalBearing = (float) Math.atan2(cosU1 * sinLambda, ((-sinU1) * cosU2) + (cosU1 * sinU2 * cosLambda));
        results.mFinalBearing = (float) (finalBearing * 57.29577951308232d);
        results.mLat1 = lat12;
        results.mLat2 = lat22;
        results.mLon1 = lon12;
        results.mLon2 = lon22;
    }

    public static void distanceBetween(double startLatitude, double startLongitude, double endLatitude, double endLongitude, float[] results) {
        if (results == null || results.length < 1) {
            throw new IllegalArgumentException("results is null or has length < 1");
        }
        BearingDistanceCache cache = sBearingDistanceCache.get();
        computeDistanceAndBearing(startLatitude, startLongitude, endLatitude, endLongitude, cache);
        results[0] = cache.mDistance;
        if (results.length > 1) {
            results[1] = cache.mInitialBearing;
            if (results.length > 2) {
                results[2] = cache.mFinalBearing;
            }
        }
    }

    public float distanceTo(Location dest) {
        BearingDistanceCache cache = sBearingDistanceCache.get();
        if (!(this.mLatitude == cache.mLat1 && this.mLongitude == cache.mLon1 && dest.mLatitude == cache.mLat2 && dest.mLongitude == cache.mLon2)) {
            computeDistanceAndBearing(this.mLatitude, this.mLongitude, dest.mLatitude, dest.mLongitude, cache);
        }
        return cache.mDistance;
    }

    public float bearingTo(Location dest) {
        BearingDistanceCache cache = sBearingDistanceCache.get();
        if (!(this.mLatitude == cache.mLat1 && this.mLongitude == cache.mLon1 && dest.mLatitude == cache.mLat2 && dest.mLongitude == cache.mLon2)) {
            computeDistanceAndBearing(this.mLatitude, this.mLongitude, dest.mLatitude, dest.mLongitude, cache);
        }
        return cache.mInitialBearing;
    }

    public String getProvider() {
        return this.mProvider;
    }

    public void setProvider(String provider) {
        this.mProvider = provider;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long time) {
        this.mTime = time;
    }

    public long getElapsedRealtimeNanos() {
        return this.mElapsedRealtimeNanos;
    }

    public long getElapsedRealtimeMillis() {
        return TimeUnit.NANOSECONDS.toMillis(getElapsedRealtimeNanos());
    }

    public long getElapsedRealtimeAgeMillis() {
        return getElapsedRealtimeAgeMillis(SystemClock.elapsedRealtime());
    }

    public long getElapsedRealtimeAgeMillis(long referenceRealtimeMs) {
        return referenceRealtimeMs - TimeUnit.NANOSECONDS.toMillis(this.mElapsedRealtimeNanos);
    }

    public void setElapsedRealtimeNanos(long time) {
        this.mElapsedRealtimeNanos = time;
    }

    public double getElapsedRealtimeUncertaintyNanos() {
        return this.mElapsedRealtimeUncertaintyNanos;
    }

    public void setElapsedRealtimeUncertaintyNanos(double time) {
        this.mElapsedRealtimeUncertaintyNanos = time;
        this.mFieldsMask |= 256;
    }

    public boolean hasElapsedRealtimeUncertaintyNanos() {
        return (this.mFieldsMask & 256) != 0;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public boolean hasAltitude() {
        return (this.mFieldsMask & 1) != 0;
    }

    public double getAltitude() {
        return this.mAltitude;
    }

    public void setAltitude(double altitude) {
        this.mAltitude = altitude;
        this.mFieldsMask |= 1;
    }

    @Deprecated
    public void removeAltitude() {
        this.mAltitude = 0.0d;
        this.mFieldsMask &= -2;
    }

    public boolean hasSpeed() {
        return (this.mFieldsMask & 2) != 0;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(float speed) {
        this.mSpeed = speed;
        this.mFieldsMask |= 2;
    }

    @Deprecated
    public void removeSpeed() {
        this.mSpeed = 0.0f;
        this.mFieldsMask &= -3;
    }

    public boolean hasBearing() {
        return (this.mFieldsMask & 4) != 0;
    }

    public float getBearing() {
        return this.mBearing;
    }

    public void setBearing(float bearing) {
        while (bearing < 0.0f) {
            bearing += 360.0f;
        }
        while (bearing >= 360.0f) {
            bearing -= 360.0f;
        }
        this.mBearing = bearing;
        this.mFieldsMask |= 4;
    }

    @Deprecated
    public void removeBearing() {
        this.mBearing = 0.0f;
        this.mFieldsMask &= -5;
    }

    public boolean hasAccuracy() {
        return (this.mFieldsMask & 8) != 0;
    }

    public float getAccuracy() {
        return this.mHorizontalAccuracyMeters;
    }

    public void setAccuracy(float horizontalAccuracy) {
        this.mHorizontalAccuracyMeters = horizontalAccuracy;
        this.mFieldsMask |= 8;
    }

    @Deprecated
    public void removeAccuracy() {
        this.mHorizontalAccuracyMeters = 0.0f;
        this.mFieldsMask &= -9;
    }

    public boolean hasVerticalAccuracy() {
        return (this.mFieldsMask & 32) != 0;
    }

    public float getVerticalAccuracyMeters() {
        return this.mVerticalAccuracyMeters;
    }

    public void setVerticalAccuracyMeters(float verticalAccuracyMeters) {
        this.mVerticalAccuracyMeters = verticalAccuracyMeters;
        this.mFieldsMask |= 32;
    }

    @Deprecated
    public void removeVerticalAccuracy() {
        this.mVerticalAccuracyMeters = 0.0f;
        this.mFieldsMask &= -33;
    }

    public boolean hasSpeedAccuracy() {
        return (this.mFieldsMask & 64) != 0;
    }

    public float getSpeedAccuracyMetersPerSecond() {
        return this.mSpeedAccuracyMetersPerSecond;
    }

    public void setSpeedAccuracyMetersPerSecond(float speedAccuracyMeterPerSecond) {
        this.mSpeedAccuracyMetersPerSecond = speedAccuracyMeterPerSecond;
        this.mFieldsMask |= 64;
    }

    @Deprecated
    public void removeSpeedAccuracy() {
        this.mSpeedAccuracyMetersPerSecond = 0.0f;
        this.mFieldsMask &= -65;
    }

    public boolean hasBearingAccuracy() {
        return (this.mFieldsMask & 128) != 0;
    }

    public float getBearingAccuracyDegrees() {
        return this.mBearingAccuracyDegrees;
    }

    public void setBearingAccuracyDegrees(float bearingAccuracyDegrees) {
        this.mBearingAccuracyDegrees = bearingAccuracyDegrees;
        this.mFieldsMask |= 128;
    }

    @Deprecated
    public void removeBearingAccuracy() {
        this.mBearingAccuracyDegrees = 0.0f;
        this.mFieldsMask &= -129;
    }

    @SystemApi
    public boolean isComplete() {
        return (this.mProvider == null || !hasAccuracy() || this.mTime == 0 || this.mElapsedRealtimeNanos == 0) ? false : true;
    }

    @SystemApi
    public void makeComplete() {
        if (this.mProvider == null) {
            this.mProvider = "?";
        }
        if (!hasAccuracy()) {
            this.mFieldsMask |= 8;
            this.mHorizontalAccuracyMeters = 100.0f;
        }
        if (this.mTime == 0) {
            this.mTime = System.currentTimeMillis();
        }
        if (this.mElapsedRealtimeNanos == 0) {
            this.mElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        }
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public void setExtras(Bundle extras) {
        this.mExtras = extras == null ? null : new Bundle(extras);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        if (this.mTime == location.mTime && this.mElapsedRealtimeNanos == location.mElapsedRealtimeNanos && hasElapsedRealtimeUncertaintyNanos() == location.hasElapsedRealtimeUncertaintyNanos() && ((!hasElapsedRealtimeUncertaintyNanos() || Double.compare(location.mElapsedRealtimeUncertaintyNanos, this.mElapsedRealtimeUncertaintyNanos) == 0) && Double.compare(location.mLatitude, this.mLatitude) == 0 && Double.compare(location.mLongitude, this.mLongitude) == 0 && hasAltitude() == location.hasAltitude() && ((!hasAltitude() || Double.compare(location.mAltitude, this.mAltitude) == 0) && hasSpeed() == location.hasSpeed() && ((!hasSpeed() || Float.compare(location.mSpeed, this.mSpeed) == 0) && hasBearing() == location.hasBearing() && ((!hasBearing() || Float.compare(location.mBearing, this.mBearing) == 0) && hasAccuracy() == location.hasAccuracy() && ((!hasAccuracy() || Float.compare(location.mHorizontalAccuracyMeters, this.mHorizontalAccuracyMeters) == 0) && hasVerticalAccuracy() == location.hasVerticalAccuracy() && ((!hasVerticalAccuracy() || Float.compare(location.mVerticalAccuracyMeters, this.mVerticalAccuracyMeters) == 0) && hasSpeedAccuracy() == location.hasSpeedAccuracy() && ((!hasSpeedAccuracy() || Float.compare(location.mSpeedAccuracyMetersPerSecond, this.mSpeedAccuracyMetersPerSecond) == 0) && hasBearingAccuracy() == location.hasBearingAccuracy() && ((!hasBearingAccuracy() || Float.compare(location.mBearingAccuracyDegrees, this.mBearingAccuracyDegrees) == 0) && Objects.equals(this.mProvider, location.mProvider) && areExtrasEqual(this.mExtras, location.mExtras)))))))))) {
            return true;
        }
        return false;
    }

    private static boolean areExtrasEqual(Bundle extras1, Bundle extras2) {
        if ((extras1 == null || extras1.isEmpty()) && (extras2 == null || extras2.isEmpty())) {
            return true;
        }
        if (extras1 == null || extras2 == null) {
            return false;
        }
        return extras1.kindofEquals(extras2);
    }

    public int hashCode() {
        return Objects.hash(this.mProvider, Long.valueOf(this.mElapsedRealtimeNanos), Double.valueOf(this.mLatitude), Double.valueOf(this.mLongitude));
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Location[");
        s.append(this.mProvider);
        if ("eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE)) {
            s.append(" ");
            s.append(String.format(Locale.ROOT, "%.6f,%.6f", Double.valueOf(this.mLatitude), Double.valueOf(this.mLongitude)));
        } else {
            s.append(" ");
            s.append(String.format(Locale.ROOT, "%.2f****,%.2f****", Double.valueOf(this.mLatitude), Double.valueOf(this.mLongitude)));
        }
        if (hasAccuracy()) {
            s.append(" hAcc=");
            s.append(this.mHorizontalAccuracyMeters);
        }
        s.append(" et=");
        TimeUtils.formatDuration(getElapsedRealtimeMillis(), s);
        if (hasAltitude()) {
            s.append(" alt=");
            s.append(this.mAltitude);
            if (hasVerticalAccuracy()) {
                s.append(" vAcc=");
                s.append(this.mVerticalAccuracyMeters);
            }
        }
        if (hasSpeed()) {
            s.append(" vel=");
            s.append(this.mSpeed);
            if (hasSpeedAccuracy()) {
                s.append(" sAcc=");
                s.append(this.mSpeedAccuracyMetersPerSecond);
            }
        }
        if (hasBearing()) {
            s.append(" bear=");
            s.append(this.mBearing);
            if (hasBearingAccuracy()) {
                s.append(" bAcc=");
                s.append(this.mBearingAccuracyDegrees);
            }
        }
        if (isMock()) {
            s.append(" mock");
        }
        if (this.mExtras != null) {
            if ("eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE)) {
                s.append(" {");
                s.append(this.mExtras);
                s.append('}');
            } else if (!LocationManager.NETWORK_PROVIDER.equals(this.mProvider)) {
                s.append(" {");
                s.append(this.mExtras);
                s.append('}');
            }
        }
        s.append(']');
        return s.toString();
    }

    public void dump(Printer pw, String prefix) {
        pw.println(prefix + toString());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mProvider);
        parcel.writeInt(this.mFieldsMask);
        parcel.writeLong(this.mTime);
        parcel.writeLong(this.mElapsedRealtimeNanos);
        if (hasElapsedRealtimeUncertaintyNanos()) {
            parcel.writeDouble(this.mElapsedRealtimeUncertaintyNanos);
        }
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
        if (hasAltitude()) {
            parcel.writeDouble(this.mAltitude);
        }
        if (hasSpeed()) {
            parcel.writeFloat(this.mSpeed);
        }
        if (hasBearing()) {
            parcel.writeFloat(this.mBearing);
        }
        if (hasAccuracy()) {
            parcel.writeFloat(this.mHorizontalAccuracyMeters);
        }
        if (hasVerticalAccuracy()) {
            parcel.writeFloat(this.mVerticalAccuracyMeters);
        }
        if (hasSpeedAccuracy()) {
            parcel.writeFloat(this.mSpeedAccuracyMetersPerSecond);
        }
        if (hasBearingAccuracy()) {
            parcel.writeFloat(this.mBearingAccuracyDegrees);
        }
        parcel.writeBundle(this.mExtras);
    }

    @Deprecated
    public boolean isFromMockProvider() {
        return isMock();
    }

    @SystemApi
    @Deprecated
    public void setIsFromMockProvider(boolean isFromMockProvider) {
        setMock(isFromMockProvider);
    }

    public boolean isMock() {
        return (this.mFieldsMask & 16) != 0;
    }

    public void setMock(boolean mock) {
        if (mock) {
            this.mFieldsMask |= 16;
        } else {
            this.mFieldsMask &= -17;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BearingDistanceCache {
        float mDistance;
        float mFinalBearing;
        float mInitialBearing;
        double mLat1;
        double mLat2;
        double mLon1;
        double mLon2;

        private BearingDistanceCache() {
            this.mLat1 = 0.0d;
            this.mLon1 = 0.0d;
            this.mLat2 = 0.0d;
            this.mLon2 = 0.0d;
            this.mDistance = 0.0f;
            this.mInitialBearing = 0.0f;
            this.mFinalBearing = 0.0f;
        }
    }
}
