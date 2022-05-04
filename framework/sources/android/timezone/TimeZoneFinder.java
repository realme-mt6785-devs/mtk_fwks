package android.timezone;

import com.android.i18n.timezone.CountryTimeZones;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class TimeZoneFinder {
    private static TimeZoneFinder sInstance;
    private static final Object sLock = new Object();
    private final com.android.i18n.timezone.TimeZoneFinder mDelegate;

    public static TimeZoneFinder getInstance() {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new TimeZoneFinder(com.android.i18n.timezone.TimeZoneFinder.getInstance());
            }
        }
        return sInstance;
    }

    private TimeZoneFinder(com.android.i18n.timezone.TimeZoneFinder delegate) {
        Objects.requireNonNull(delegate);
        this.mDelegate = delegate;
    }

    public String getIanaVersion() {
        return this.mDelegate.getIanaVersion();
    }

    public CountryTimeZones lookupCountryTimeZones(String countryIso) {
        CountryTimeZones delegate = this.mDelegate.lookupCountryTimeZones(countryIso);
        if (delegate == null) {
            return null;
        }
        return new CountryTimeZones(delegate);
    }
}
