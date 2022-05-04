package android.location.provider;

import android.annotation.SystemApi;
import android.content.Context;
import android.location.Location;
import android.location.provider.ILocationProvider;
import android.location.provider.LocationProviderBase;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public abstract class LocationProviderBase {
    public static final String ACTION_FUSED_PROVIDER = "com.android.location.service.FusedLocationProvider";
    public static final String ACTION_NETWORK_PROVIDER = "com.android.location.service.v3.NetworkLocationProvider";
    final String mAttributionTag;
    volatile ProviderProperties mProperties;
    final String mTag;
    final IBinder mBinder = new Service();
    volatile ILocationProviderManager mManager = null;
    volatile boolean mAllowed = true;

    /* loaded from: classes2.dex */
    public interface OnFlushCompleteCallback {
        void onFlushComplete();
    }

    public abstract void onFlush(OnFlushCompleteCallback onFlushCompleteCallback);

    public abstract void onSendExtraCommand(String str, Bundle bundle);

    public abstract void onSetRequest(ProviderRequest providerRequest);

    public LocationProviderBase(Context context, String tag, ProviderProperties properties) {
        this.mTag = tag;
        this.mAttributionTag = context.getAttributionTag();
        Objects.requireNonNull(properties);
        this.mProperties = properties;
    }

    public final IBinder getBinder() {
        return this.mBinder;
    }

    public void setAllowed(boolean allowed) {
        synchronized (this.mBinder) {
            if (this.mAllowed != allowed) {
                this.mAllowed = allowed;
                ILocationProviderManager manager = this.mManager;
                if (manager != null) {
                    try {
                        manager.onSetAllowed(this.mAllowed);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    } catch (RuntimeException e2) {
                        Log.w(this.mTag, e2);
                    }
                }
            }
        }
    }

    public boolean isAllowed() {
        return this.mAllowed;
    }

    public void setProperties(ProviderProperties properties) {
        synchronized (this.mBinder) {
            Objects.requireNonNull(properties);
            this.mProperties = properties;
        }
        ILocationProviderManager manager = this.mManager;
        if (manager != null) {
            try {
                manager.onSetProperties(this.mProperties);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                Log.w(this.mTag, e2);
            }
        }
    }

    public ProviderProperties getProperties() {
        return this.mProperties;
    }

    public void reportLocation(Location location) {
        ILocationProviderManager manager = this.mManager;
        if (manager != null) {
            try {
                manager.onReportLocation(stripExtras(location));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                Log.w(this.mTag, e2);
            }
        }
    }

    public void reportLocations(List<Location> locations) {
        ILocationProviderManager manager = this.mManager;
        if (manager != null) {
            try {
                manager.onReportLocations(stripExtras(locations));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                Log.w(this.mTag, e2);
            }
        }
    }

    private static Location stripExtras(Location location) {
        Bundle extras = location.getExtras();
        if (extras != null && (extras.containsKey(Location.EXTRA_NO_GPS_LOCATION) || extras.containsKey("indoorProbability") || extras.containsKey("coarseLocation"))) {
            location = new Location(location);
            Bundle extras2 = location.getExtras();
            extras2.remove(Location.EXTRA_NO_GPS_LOCATION);
            extras2.remove("indoorProbability");
            extras2.remove("coarseLocation");
            if (extras2.isEmpty()) {
                location.setExtras(null);
            }
        }
        return location;
    }

    private static List<Location> stripExtras(List<Location> locations) {
        List<Location> mapped = locations;
        int size = locations.size();
        int i = 0;
        for (Location location : locations) {
            Location newLocation = stripExtras(location);
            if (mapped != locations) {
                mapped.add(newLocation);
            } else if (newLocation != location) {
                mapped = new ArrayList<>(size);
                int j = 0;
                for (Location copiedLocation : locations) {
                    if (j >= i) {
                        break;
                    }
                    mapped.add(copiedLocation);
                    j++;
                }
                mapped.add(newLocation);
            }
            i++;
        }
        return mapped;
    }

    /* loaded from: classes2.dex */
    private final class Service extends ILocationProvider.Stub {
        Service() {
        }

        @Override // android.location.provider.ILocationProvider
        public void setLocationProviderManager(ILocationProviderManager manager) {
            synchronized (LocationProviderBase.this.mBinder) {
                try {
                    manager.onInitialize(LocationProviderBase.this.mAllowed, LocationProviderBase.this.mProperties, LocationProviderBase.this.mAttributionTag);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                } catch (RuntimeException e2) {
                    Log.w(LocationProviderBase.this.mTag, e2);
                }
                LocationProviderBase.this.mManager = manager;
            }
        }

        @Override // android.location.provider.ILocationProvider
        public void setRequest(ProviderRequest request) {
            LocationProviderBase.this.onSetRequest(request);
        }

        @Override // android.location.provider.ILocationProvider
        public void flush() {
            LocationProviderBase.this.onFlush(new OnFlushCompleteCallback() { // from class: android.location.provider.LocationProviderBase$Service$$ExternalSyntheticLambda0
                @Override // android.location.provider.LocationProviderBase.OnFlushCompleteCallback
                public final void onFlushComplete() {
                    LocationProviderBase.Service.this.onFlushComplete();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onFlushComplete() {
            ILocationProviderManager manager = LocationProviderBase.this.mManager;
            if (manager != null) {
                try {
                    manager.onFlushComplete();
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                } catch (RuntimeException e2) {
                    Log.w(LocationProviderBase.this.mTag, e2);
                }
            }
        }

        @Override // android.location.provider.ILocationProvider
        public void sendExtraCommand(String command, Bundle extras) {
            LocationProviderBase.this.onSendExtraCommand(command, extras);
        }
    }
}
