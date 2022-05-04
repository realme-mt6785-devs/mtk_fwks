package android.location;

import android.content.Context;
import android.location.IGeocodeListener;
import android.location.ILocationManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class Geocoder {
    private static final long TIMEOUT_MS = 60000;
    private final GeocoderParams mParams;
    private final ILocationManager mService;

    public static boolean isPresent() {
        IBinder b = ServiceManager.getService("location");
        ILocationManager lm = ILocationManager.Stub.asInterface(b);
        try {
            return lm.geocoderIsPresent();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Geocoder(Context context, Locale locale) {
        this.mParams = new GeocoderParams(context, locale);
        this.mService = ILocationManager.Stub.asInterface(ServiceManager.getService("location"));
    }

    public Geocoder(Context context) {
        this(context, Locale.getDefault());
    }

    public List<Address> getFromLocation(double latitude, double longitude, int maxResults) throws IOException {
        Preconditions.checkArgumentInRange(latitude, -90.0d, 90.0d, "latitude");
        Preconditions.checkArgumentInRange(longitude, -180.0d, 180.0d, "longitude");
        try {
            GeocodeListener listener = new GeocodeListener();
            this.mService.getFromLocation(latitude, longitude, maxResults, this.mParams, listener);
            return listener.getResults();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<Address> getFromLocationName(String locationName, int maxResults) throws IOException {
        return getFromLocationName(locationName, maxResults, 0.0d, 0.0d, 0.0d, 0.0d);
    }

    public List<Address> getFromLocationName(String locationName, int maxResults, double lowerLeftLatitude, double lowerLeftLongitude, double upperRightLatitude, double upperRightLongitude) throws IOException {
        Preconditions.checkArgument(locationName != null);
        Preconditions.checkArgumentInRange(lowerLeftLatitude, -90.0d, 90.0d, "lowerLeftLatitude");
        Preconditions.checkArgumentInRange(lowerLeftLongitude, -180.0d, 180.0d, "lowerLeftLongitude");
        Preconditions.checkArgumentInRange(upperRightLatitude, -90.0d, 90.0d, "upperRightLatitude");
        Preconditions.checkArgumentInRange(upperRightLongitude, -180.0d, 180.0d, "upperRightLongitude");
        try {
            GeocodeListener listener = new GeocodeListener();
            this.mService.getFromLocationName(locationName, lowerLeftLatitude, lowerLeftLongitude, upperRightLatitude, upperRightLongitude, maxResults, this.mParams, listener);
            return listener.getResults();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class GeocodeListener extends IGeocodeListener.Stub {
        private final CountDownLatch mLatch = new CountDownLatch(1);
        private String mError = null;
        private List<Address> mResults = Collections.emptyList();

        GeocodeListener() {
        }

        @Override // android.location.IGeocodeListener
        public void onResults(String error, List<Address> results) {
            this.mError = error;
            this.mResults = results;
            this.mLatch.countDown();
        }

        public List<Address> getResults() throws IOException {
            try {
                if (!this.mLatch.await(60000L, TimeUnit.MILLISECONDS)) {
                    this.mError = "Service not Available";
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (this.mError == null) {
                return this.mResults;
            }
            throw new IOException(this.mError);
        }
    }
}
