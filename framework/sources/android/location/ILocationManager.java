package android.location;

import android.app.PendingIntent;
import android.location.IGeocodeListener;
import android.location.IGnssAntennaInfoListener;
import android.location.IGnssMeasurementsListener;
import android.location.IGnssNavigationMessageListener;
import android.location.IGnssNmeaListener;
import android.location.IGnssStatusListener;
import android.location.ILocationCallback;
import android.location.ILocationListener;
import android.location.provider.IProviderRequestListener;
import android.location.provider.ProviderProperties;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.PackageTagsList;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface ILocationManager extends IInterface {
    void addGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener, String str, String str2, String str3) throws RemoteException;

    void addGnssMeasurementsListener(GnssMeasurementRequest gnssMeasurementRequest, IGnssMeasurementsListener iGnssMeasurementsListener, String str, String str2, String str3) throws RemoteException;

    void addGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener, String str, String str2, String str3) throws RemoteException;

    void addProviderRequestListener(IProviderRequestListener iProviderRequestListener) throws RemoteException;

    void addTestProvider(String str, ProviderProperties providerProperties, List<String> list, String str2, String str3) throws RemoteException;

    void flushGnssBatch() throws RemoteException;

    boolean geocoderIsPresent() throws RemoteException;

    List<String> getAllProviders() throws RemoteException;

    String[] getBackgroundThrottlingWhitelist() throws RemoteException;

    String getBestProvider(Criteria criteria, boolean z) throws RemoteException;

    ICancellationSignal getCurrentLocation(String str, LocationRequest locationRequest, ILocationCallback iLocationCallback, String str2, String str3, String str4) throws RemoteException;

    String getExtraLocationControllerPackage() throws RemoteException;

    void getFromLocation(double d, double d2, int i, GeocoderParams geocoderParams, IGeocodeListener iGeocodeListener) throws RemoteException;

    void getFromLocationName(String str, double d, double d2, double d3, double d4, int i, GeocoderParams geocoderParams, IGeocodeListener iGeocodeListener) throws RemoteException;

    List<GnssAntennaInfo> getGnssAntennaInfos() throws RemoteException;

    int getGnssBatchSize() throws RemoteException;

    GnssCapabilities getGnssCapabilities() throws RemoteException;

    String getGnssHardwareModelName() throws RemoteException;

    LocationTime getGnssTimeMillis() throws RemoteException;

    int getGnssYearOfHardware() throws RemoteException;

    PackageTagsList getIgnoreSettingsAllowlist() throws RemoteException;

    Location getLastLocation(String str, LastLocationRequest lastLocationRequest, String str2, String str3) throws RemoteException;

    List<String> getProviderPackages(String str) throws RemoteException;

    ProviderProperties getProviderProperties(String str) throws RemoteException;

    List<String> getProviders(Criteria criteria, boolean z) throws RemoteException;

    boolean hasProvider(String str) throws RemoteException;

    void injectGnssMeasurementCorrections(GnssMeasurementCorrections gnssMeasurementCorrections) throws RemoteException;

    void injectLocation(Location location) throws RemoteException;

    boolean isAdasGnssLocationEnabledForUser(int i) throws RemoteException;

    boolean isExtraLocationControllerPackageEnabled() throws RemoteException;

    boolean isLocationEnabledForUser(int i) throws RemoteException;

    boolean isProviderEnabledForUser(String str, int i) throws RemoteException;

    boolean isProviderPackage(String str, String str2, String str3) throws RemoteException;

    void registerGnssNmeaCallback(IGnssNmeaListener iGnssNmeaListener, String str, String str2, String str3) throws RemoteException;

    void registerGnssStatusCallback(IGnssStatusListener iGnssStatusListener, String str, String str2, String str3) throws RemoteException;

    void registerLocationListener(String str, LocationRequest locationRequest, ILocationListener iLocationListener, String str2, String str3, String str4) throws RemoteException;

    void registerLocationPendingIntent(String str, LocationRequest locationRequest, PendingIntent pendingIntent, String str2, String str3) throws RemoteException;

    void removeGeofence(PendingIntent pendingIntent) throws RemoteException;

    void removeGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener) throws RemoteException;

    void removeGnssMeasurementsListener(IGnssMeasurementsListener iGnssMeasurementsListener) throws RemoteException;

    void removeGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener) throws RemoteException;

    void removeProviderRequestListener(IProviderRequestListener iProviderRequestListener) throws RemoteException;

    void removeTestProvider(String str, String str2, String str3) throws RemoteException;

    void requestGeofence(Geofence geofence, PendingIntent pendingIntent, String str, String str2) throws RemoteException;

    void requestListenerFlush(String str, ILocationListener iLocationListener, int i) throws RemoteException;

    void requestPendingIntentFlush(String str, PendingIntent pendingIntent, int i) throws RemoteException;

    void sendExtraCommand(String str, String str2, Bundle bundle) throws RemoteException;

    void setAdasGnssLocationEnabledForUser(boolean z, int i) throws RemoteException;

    void setExtraLocationControllerPackage(String str) throws RemoteException;

    void setExtraLocationControllerPackageEnabled(boolean z) throws RemoteException;

    void setLocationEnabledForUser(boolean z, int i) throws RemoteException;

    void setTestProviderEnabled(String str, boolean z, String str2, String str3) throws RemoteException;

    void setTestProviderLocation(String str, Location location, String str2, String str3) throws RemoteException;

    void startGnssBatch(long j, ILocationListener iLocationListener, String str, String str2, String str3) throws RemoteException;

    void stopGnssBatch() throws RemoteException;

    void unregisterGnssNmeaCallback(IGnssNmeaListener iGnssNmeaListener) throws RemoteException;

    void unregisterGnssStatusCallback(IGnssStatusListener iGnssStatusListener) throws RemoteException;

    void unregisterLocationListener(ILocationListener iLocationListener) throws RemoteException;

    void unregisterLocationPendingIntent(PendingIntent pendingIntent) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ILocationManager {
        @Override // android.location.ILocationManager
        public Location getLastLocation(String provider, LastLocationRequest request, String packageName, String attributionTag) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public ICancellationSignal getCurrentLocation(String provider, LocationRequest request, ILocationCallback callback, String packageName, String attributionTag, String listenerId) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public void registerLocationListener(String provider, LocationRequest request, ILocationListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void unregisterLocationListener(ILocationListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void registerLocationPendingIntent(String provider, LocationRequest request, PendingIntent pendingIntent, String packageName, String attributionTag) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void unregisterLocationPendingIntent(PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void injectLocation(Location location) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void requestListenerFlush(String provider, ILocationListener listener, int requestCode) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void requestPendingIntentFlush(String provider, PendingIntent pendingIntent, int requestCode) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void requestGeofence(Geofence geofence, PendingIntent intent, String packageName, String attributionTag) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGeofence(PendingIntent intent) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean geocoderIsPresent() throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void getFromLocation(double latitude, double longitude, int maxResults, GeocoderParams params, IGeocodeListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void getFromLocationName(String locationName, double lowerLeftLatitude, double lowerLeftLongitude, double upperRightLatitude, double upperRightLongitude, int maxResults, GeocoderParams params, IGeocodeListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public GnssCapabilities getGnssCapabilities() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public int getGnssYearOfHardware() throws RemoteException {
            return 0;
        }

        @Override // android.location.ILocationManager
        public String getGnssHardwareModelName() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public List<GnssAntennaInfo> getGnssAntennaInfos() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public void registerGnssStatusCallback(IGnssStatusListener callback, String packageName, String attributionTag, String listenerId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void unregisterGnssStatusCallback(IGnssStatusListener callback) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void registerGnssNmeaCallback(IGnssNmeaListener callback, String packageName, String attributionTag, String listenerId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void unregisterGnssNmeaCallback(IGnssNmeaListener callback) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void addGnssMeasurementsListener(GnssMeasurementRequest request, IGnssMeasurementsListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGnssMeasurementsListener(IGnssMeasurementsListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void injectGnssMeasurementCorrections(GnssMeasurementCorrections corrections) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void addGnssNavigationMessageListener(IGnssNavigationMessageListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGnssNavigationMessageListener(IGnssNavigationMessageListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void addGnssAntennaInfoListener(IGnssAntennaInfoListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGnssAntennaInfoListener(IGnssAntennaInfoListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void addProviderRequestListener(IProviderRequestListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeProviderRequestListener(IProviderRequestListener listener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public int getGnssBatchSize() throws RemoteException {
            return 0;
        }

        @Override // android.location.ILocationManager
        public void startGnssBatch(long periodNanos, ILocationListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void flushGnssBatch() throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void stopGnssBatch() throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean hasProvider(String provider) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public List<String> getAllProviders() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public List<String> getProviders(Criteria criteria, boolean enabledOnly) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public String getBestProvider(Criteria criteria, boolean enabledOnly) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public ProviderProperties getProviderProperties(String provider) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public boolean isProviderPackage(String provider, String packageName, String attributionTag) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public List<String> getProviderPackages(String provider) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public void setExtraLocationControllerPackage(String packageName) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public String getExtraLocationControllerPackage() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public void setExtraLocationControllerPackageEnabled(boolean enabled) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean isExtraLocationControllerPackageEnabled() throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean isProviderEnabledForUser(String provider, int userId) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean isLocationEnabledForUser(int userId) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void setLocationEnabledForUser(boolean enabled, int userId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean isAdasGnssLocationEnabledForUser(int userId) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void setAdasGnssLocationEnabledForUser(boolean enabled, int userId) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void addTestProvider(String name, ProviderProperties properties, List<String> locationTags, String packageName, String attributionTag) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeTestProvider(String provider, String packageName, String attributionTag) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void setTestProviderLocation(String provider, Location location, String packageName, String attributionTag) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void setTestProviderEnabled(String provider, boolean enabled, String packageName, String attributionTag) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public LocationTime getGnssTimeMillis() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public void sendExtraCommand(String provider, String command, Bundle extras) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public String[] getBackgroundThrottlingWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public PackageTagsList getIgnoreSettingsAllowlist() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ILocationManager {
        public static final String DESCRIPTOR = "android.location.ILocationManager";
        static final int TRANSACTION_addGnssAntennaInfoListener = 28;
        static final int TRANSACTION_addGnssMeasurementsListener = 23;
        static final int TRANSACTION_addGnssNavigationMessageListener = 26;
        static final int TRANSACTION_addProviderRequestListener = 30;
        static final int TRANSACTION_addTestProvider = 52;
        static final int TRANSACTION_flushGnssBatch = 34;
        static final int TRANSACTION_geocoderIsPresent = 12;
        static final int TRANSACTION_getAllProviders = 37;
        static final int TRANSACTION_getBackgroundThrottlingWhitelist = 58;
        static final int TRANSACTION_getBestProvider = 39;
        static final int TRANSACTION_getCurrentLocation = 2;
        static final int TRANSACTION_getExtraLocationControllerPackage = 44;
        static final int TRANSACTION_getFromLocation = 13;
        static final int TRANSACTION_getFromLocationName = 14;
        static final int TRANSACTION_getGnssAntennaInfos = 18;
        static final int TRANSACTION_getGnssBatchSize = 32;
        static final int TRANSACTION_getGnssCapabilities = 15;
        static final int TRANSACTION_getGnssHardwareModelName = 17;
        static final int TRANSACTION_getGnssTimeMillis = 56;
        static final int TRANSACTION_getGnssYearOfHardware = 16;
        static final int TRANSACTION_getIgnoreSettingsAllowlist = 59;
        static final int TRANSACTION_getLastLocation = 1;
        static final int TRANSACTION_getProviderPackages = 42;
        static final int TRANSACTION_getProviderProperties = 40;
        static final int TRANSACTION_getProviders = 38;
        static final int TRANSACTION_hasProvider = 36;
        static final int TRANSACTION_injectGnssMeasurementCorrections = 25;
        static final int TRANSACTION_injectLocation = 7;
        static final int TRANSACTION_isAdasGnssLocationEnabledForUser = 50;
        static final int TRANSACTION_isExtraLocationControllerPackageEnabled = 46;
        static final int TRANSACTION_isLocationEnabledForUser = 48;
        static final int TRANSACTION_isProviderEnabledForUser = 47;
        static final int TRANSACTION_isProviderPackage = 41;
        static final int TRANSACTION_registerGnssNmeaCallback = 21;
        static final int TRANSACTION_registerGnssStatusCallback = 19;
        static final int TRANSACTION_registerLocationListener = 3;
        static final int TRANSACTION_registerLocationPendingIntent = 5;
        static final int TRANSACTION_removeGeofence = 11;
        static final int TRANSACTION_removeGnssAntennaInfoListener = 29;
        static final int TRANSACTION_removeGnssMeasurementsListener = 24;
        static final int TRANSACTION_removeGnssNavigationMessageListener = 27;
        static final int TRANSACTION_removeProviderRequestListener = 31;
        static final int TRANSACTION_removeTestProvider = 53;
        static final int TRANSACTION_requestGeofence = 10;
        static final int TRANSACTION_requestListenerFlush = 8;
        static final int TRANSACTION_requestPendingIntentFlush = 9;
        static final int TRANSACTION_sendExtraCommand = 57;
        static final int TRANSACTION_setAdasGnssLocationEnabledForUser = 51;
        static final int TRANSACTION_setExtraLocationControllerPackage = 43;
        static final int TRANSACTION_setExtraLocationControllerPackageEnabled = 45;
        static final int TRANSACTION_setLocationEnabledForUser = 49;
        static final int TRANSACTION_setTestProviderEnabled = 55;
        static final int TRANSACTION_setTestProviderLocation = 54;
        static final int TRANSACTION_startGnssBatch = 33;
        static final int TRANSACTION_stopGnssBatch = 35;
        static final int TRANSACTION_unregisterGnssNmeaCallback = 22;
        static final int TRANSACTION_unregisterGnssStatusCallback = 20;
        static final int TRANSACTION_unregisterLocationListener = 4;
        static final int TRANSACTION_unregisterLocationPendingIntent = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILocationManager)) {
                return new Proxy(obj);
            }
            return (ILocationManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getLastLocation";
                case 2:
                    return "getCurrentLocation";
                case 3:
                    return "registerLocationListener";
                case 4:
                    return "unregisterLocationListener";
                case 5:
                    return "registerLocationPendingIntent";
                case 6:
                    return "unregisterLocationPendingIntent";
                case 7:
                    return "injectLocation";
                case 8:
                    return "requestListenerFlush";
                case 9:
                    return "requestPendingIntentFlush";
                case 10:
                    return "requestGeofence";
                case 11:
                    return "removeGeofence";
                case 12:
                    return "geocoderIsPresent";
                case 13:
                    return "getFromLocation";
                case 14:
                    return "getFromLocationName";
                case 15:
                    return "getGnssCapabilities";
                case 16:
                    return "getGnssYearOfHardware";
                case 17:
                    return "getGnssHardwareModelName";
                case 18:
                    return "getGnssAntennaInfos";
                case 19:
                    return "registerGnssStatusCallback";
                case 20:
                    return "unregisterGnssStatusCallback";
                case 21:
                    return "registerGnssNmeaCallback";
                case 22:
                    return "unregisterGnssNmeaCallback";
                case 23:
                    return "addGnssMeasurementsListener";
                case 24:
                    return "removeGnssMeasurementsListener";
                case 25:
                    return "injectGnssMeasurementCorrections";
                case 26:
                    return "addGnssNavigationMessageListener";
                case 27:
                    return "removeGnssNavigationMessageListener";
                case 28:
                    return "addGnssAntennaInfoListener";
                case 29:
                    return "removeGnssAntennaInfoListener";
                case 30:
                    return "addProviderRequestListener";
                case 31:
                    return "removeProviderRequestListener";
                case 32:
                    return "getGnssBatchSize";
                case 33:
                    return "startGnssBatch";
                case 34:
                    return "flushGnssBatch";
                case 35:
                    return "stopGnssBatch";
                case 36:
                    return "hasProvider";
                case 37:
                    return "getAllProviders";
                case 38:
                    return "getProviders";
                case 39:
                    return "getBestProvider";
                case 40:
                    return "getProviderProperties";
                case 41:
                    return "isProviderPackage";
                case 42:
                    return "getProviderPackages";
                case 43:
                    return "setExtraLocationControllerPackage";
                case 44:
                    return "getExtraLocationControllerPackage";
                case 45:
                    return "setExtraLocationControllerPackageEnabled";
                case 46:
                    return "isExtraLocationControllerPackageEnabled";
                case 47:
                    return "isProviderEnabledForUser";
                case 48:
                    return "isLocationEnabledForUser";
                case 49:
                    return "setLocationEnabledForUser";
                case 50:
                    return "isAdasGnssLocationEnabledForUser";
                case 51:
                    return "setAdasGnssLocationEnabledForUser";
                case 52:
                    return "addTestProvider";
                case 53:
                    return "removeTestProvider";
                case 54:
                    return "setTestProviderLocation";
                case 55:
                    return "setTestProviderEnabled";
                case 56:
                    return "getGnssTimeMillis";
                case 57:
                    return "sendExtraCommand";
                case 58:
                    return "getBackgroundThrottlingWhitelist";
                case 59:
                    return "getIgnoreSettingsAllowlist";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            LastLocationRequest _arg1;
            LocationRequest _arg12;
            LocationRequest _arg13;
            LocationRequest _arg14;
            PendingIntent _arg2;
            PendingIntent _arg0;
            Location _arg02;
            PendingIntent _arg15;
            Geofence _arg03;
            PendingIntent _arg16;
            PendingIntent _arg04;
            GeocoderParams _arg3;
            GeocoderParams _arg6;
            GnssMeasurementRequest _arg05;
            GnssMeasurementCorrections _arg06;
            Criteria _arg07;
            Criteria _arg08;
            ProviderProperties _arg17;
            Location _arg18;
            Bundle _arg22;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg19 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = LastLocationRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            String _arg23 = data.readString();
                            String _arg32 = data.readString();
                            Location _result = getLastLocation(_arg09, _arg1, _arg23, _arg32);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = LocationRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            ILocationCallback _arg24 = ILocationCallback.Stub.asInterface(data.readStrongBinder());
                            String _arg33 = data.readString();
                            String _arg4 = data.readString();
                            String _arg5 = data.readString();
                            ICancellationSignal _result2 = getCurrentLocation(_arg010, _arg12, _arg24, _arg33, _arg4, _arg5);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = LocationRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            ILocationListener _arg25 = ILocationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg34 = data.readString();
                            String _arg42 = data.readString();
                            String _arg52 = data.readString();
                            registerLocationListener(_arg011, _arg13, _arg25, _arg34, _arg42, _arg52);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            ILocationListener _arg012 = ILocationListener.Stub.asInterface(data.readStrongBinder());
                            unregisterLocationListener(_arg012);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            if (data.readInt() != 0) {
                                _arg14 = LocationRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            String _arg35 = data.readString();
                            String _arg43 = data.readString();
                            registerLocationPendingIntent(_arg013, _arg14, _arg2, _arg35, _arg43);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            unregisterLocationPendingIntent(_arg0);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Location.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            injectLocation(_arg02);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg014 = data.readString();
                            ILocationListener _arg110 = ILocationListener.Stub.asInterface(data.readStrongBinder());
                            int _arg26 = data.readInt();
                            requestListenerFlush(_arg014, _arg110, _arg26);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg015 = data.readString();
                            if (data.readInt() != 0) {
                                _arg15 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            int _arg27 = data.readInt();
                            requestPendingIntentFlush(_arg015, _arg15, _arg27);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = Geofence.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg16 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            String _arg28 = data.readString();
                            String _arg36 = data.readString();
                            requestGeofence(_arg03, _arg16, _arg28, _arg36);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            removeGeofence(_arg04);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            boolean geocoderIsPresent = geocoderIsPresent();
                            reply.writeNoException();
                            reply.writeInt(geocoderIsPresent ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            double _arg016 = data.readDouble();
                            double _arg111 = data.readDouble();
                            int _arg29 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = GeocoderParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            IGeocodeListener _arg44 = IGeocodeListener.Stub.asInterface(data.readStrongBinder());
                            getFromLocation(_arg016, _arg111, _arg29, _arg3, _arg44);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg017 = data.readString();
                            double _arg112 = data.readDouble();
                            double _arg210 = data.readDouble();
                            double _arg37 = data.readDouble();
                            double _arg45 = data.readDouble();
                            int _arg53 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg6 = GeocoderParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            IGeocodeListener _arg7 = IGeocodeListener.Stub.asInterface(data.readStrongBinder());
                            getFromLocationName(_arg017, _arg112, _arg210, _arg37, _arg45, _arg53, _arg6, _arg7);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            GnssCapabilities _result3 = getGnssCapabilities();
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            int _result4 = getGnssYearOfHardware();
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _result5 = getGnssHardwareModelName();
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            List<GnssAntennaInfo> _result6 = getGnssAntennaInfos();
                            reply.writeNoException();
                            reply.writeTypedList(_result6);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssStatusListener _arg018 = IGnssStatusListener.Stub.asInterface(data.readStrongBinder());
                            String _arg113 = data.readString();
                            String _arg211 = data.readString();
                            String _arg38 = data.readString();
                            registerGnssStatusCallback(_arg018, _arg113, _arg211, _arg38);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssStatusListener _arg019 = IGnssStatusListener.Stub.asInterface(data.readStrongBinder());
                            unregisterGnssStatusCallback(_arg019);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssNmeaListener _arg020 = IGnssNmeaListener.Stub.asInterface(data.readStrongBinder());
                            String _arg114 = data.readString();
                            String _arg212 = data.readString();
                            String _arg39 = data.readString();
                            registerGnssNmeaCallback(_arg020, _arg114, _arg212, _arg39);
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssNmeaListener _arg021 = IGnssNmeaListener.Stub.asInterface(data.readStrongBinder());
                            unregisterGnssNmeaCallback(_arg021);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = GnssMeasurementRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            IGnssMeasurementsListener _arg115 = IGnssMeasurementsListener.Stub.asInterface(data.readStrongBinder());
                            String _arg213 = data.readString();
                            String _arg310 = data.readString();
                            String _arg46 = data.readString();
                            addGnssMeasurementsListener(_arg05, _arg115, _arg213, _arg310, _arg46);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssMeasurementsListener _arg022 = IGnssMeasurementsListener.Stub.asInterface(data.readStrongBinder());
                            removeGnssMeasurementsListener(_arg022);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = GnssMeasurementCorrections.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            injectGnssMeasurementCorrections(_arg06);
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssNavigationMessageListener _arg023 = IGnssNavigationMessageListener.Stub.asInterface(data.readStrongBinder());
                            String _arg116 = data.readString();
                            String _arg214 = data.readString();
                            String _arg311 = data.readString();
                            addGnssNavigationMessageListener(_arg023, _arg116, _arg214, _arg311);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssNavigationMessageListener _arg024 = IGnssNavigationMessageListener.Stub.asInterface(data.readStrongBinder());
                            removeGnssNavigationMessageListener(_arg024);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssAntennaInfoListener _arg025 = IGnssAntennaInfoListener.Stub.asInterface(data.readStrongBinder());
                            String _arg117 = data.readString();
                            String _arg215 = data.readString();
                            String _arg312 = data.readString();
                            addGnssAntennaInfoListener(_arg025, _arg117, _arg215, _arg312);
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            IGnssAntennaInfoListener _arg026 = IGnssAntennaInfoListener.Stub.asInterface(data.readStrongBinder());
                            removeGnssAntennaInfoListener(_arg026);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            IProviderRequestListener _arg027 = IProviderRequestListener.Stub.asInterface(data.readStrongBinder());
                            addProviderRequestListener(_arg027);
                            reply.writeNoException();
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            IProviderRequestListener _arg028 = IProviderRequestListener.Stub.asInterface(data.readStrongBinder());
                            removeProviderRequestListener(_arg028);
                            reply.writeNoException();
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _result7 = getGnssBatchSize();
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            long _arg029 = data.readLong();
                            ILocationListener _arg118 = ILocationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg216 = data.readString();
                            String _arg313 = data.readString();
                            String _arg47 = data.readString();
                            startGnssBatch(_arg029, _arg118, _arg216, _arg313, _arg47);
                            reply.writeNoException();
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            flushGnssBatch();
                            reply.writeNoException();
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            stopGnssBatch();
                            reply.writeNoException();
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            boolean hasProvider = hasProvider(_arg030);
                            reply.writeNoException();
                            reply.writeInt(hasProvider ? 1 : 0);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _result8 = getAllProviders();
                            reply.writeNoException();
                            reply.writeStringList(_result8);
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = Criteria.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg19 = true;
                            }
                            List<String> _result9 = getProviders(_arg07, _arg19);
                            reply.writeNoException();
                            reply.writeStringList(_result9);
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = Criteria.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg19 = true;
                            }
                            String _result10 = getBestProvider(_arg08, _arg19);
                            reply.writeNoException();
                            reply.writeString(_result10);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg031 = data.readString();
                            ProviderProperties _result11 = getProviderProperties(_arg031);
                            reply.writeNoException();
                            if (_result11 != null) {
                                reply.writeInt(1);
                                _result11.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            String _arg119 = data.readString();
                            String _arg217 = data.readString();
                            boolean isProviderPackage = isProviderPackage(_arg032, _arg119, _arg217);
                            reply.writeNoException();
                            reply.writeInt(isProviderPackage ? 1 : 0);
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            List<String> _result12 = getProviderPackages(_arg033);
                            reply.writeNoException();
                            reply.writeStringList(_result12);
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg034 = data.readString();
                            setExtraLocationControllerPackage(_arg034);
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            String _result13 = getExtraLocationControllerPackage();
                            reply.writeNoException();
                            reply.writeString(_result13);
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg19 = true;
                            }
                            setExtraLocationControllerPackageEnabled(_arg19);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isExtraLocationControllerPackageEnabled = isExtraLocationControllerPackageEnabled();
                            reply.writeNoException();
                            reply.writeInt(isExtraLocationControllerPackageEnabled ? 1 : 0);
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            boolean isProviderEnabledForUser = isProviderEnabledForUser(_arg035, data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isProviderEnabledForUser ? 1 : 0);
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg036 = data.readInt();
                            boolean isLocationEnabledForUser = isLocationEnabledForUser(_arg036);
                            reply.writeNoException();
                            reply.writeInt(isLocationEnabledForUser ? 1 : 0);
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg19 = true;
                            }
                            setLocationEnabledForUser(_arg19, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg037 = data.readInt();
                            boolean isAdasGnssLocationEnabledForUser = isAdasGnssLocationEnabledForUser(_arg037);
                            reply.writeNoException();
                            reply.writeInt(isAdasGnssLocationEnabledForUser ? 1 : 0);
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg19 = true;
                            }
                            setAdasGnssLocationEnabledForUser(_arg19, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            if (data.readInt() != 0) {
                                _arg17 = ProviderProperties.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            List<String> _arg218 = data.createStringArrayList();
                            String _arg314 = data.readString();
                            String _arg48 = data.readString();
                            addTestProvider(_arg038, _arg17, _arg218, _arg314, _arg48);
                            reply.writeNoException();
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg039 = data.readString();
                            String _arg120 = data.readString();
                            String _arg219 = data.readString();
                            removeTestProvider(_arg039, _arg120, _arg219);
                            reply.writeNoException();
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg040 = data.readString();
                            if (data.readInt() != 0) {
                                _arg18 = Location.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            String _arg220 = data.readString();
                            String _arg315 = data.readString();
                            setTestProviderLocation(_arg040, _arg18, _arg220, _arg315);
                            reply.writeNoException();
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            if (data.readInt() != 0) {
                                _arg19 = true;
                            }
                            String _arg221 = data.readString();
                            String _arg316 = data.readString();
                            setTestProviderEnabled(_arg041, _arg19, _arg221, _arg316);
                            reply.writeNoException();
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            LocationTime _result14 = getGnssTimeMillis();
                            reply.writeNoException();
                            if (_result14 != null) {
                                reply.writeInt(1);
                                _result14.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            String _arg121 = data.readString();
                            if (data.readInt() != 0) {
                                _arg22 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            sendExtraCommand(_arg042, _arg121, _arg22);
                            reply.writeNoException();
                            if (_arg22 != null) {
                                reply.writeInt(1);
                                _arg22.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result15 = getBackgroundThrottlingWhitelist();
                            reply.writeNoException();
                            reply.writeStringArray(_result15);
                            return true;
                        case 59:
                            data.enforceInterface(DESCRIPTOR);
                            PackageTagsList _result16 = getIgnoreSettingsAllowlist();
                            reply.writeNoException();
                            if (_result16 != null) {
                                reply.writeInt(1);
                                _result16.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ILocationManager {
            public static ILocationManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.location.ILocationManager
            public Location getLastLocation(String provider, LastLocationRequest request, String packageName, String attributionTag) throws RemoteException {
                Location _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastLocation(provider, request, packageName, attributionTag);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Location.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public ICancellationSignal getCurrentLocation(String provider, LocationRequest request, ILocationCallback callback, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(provider);
                        if (request != null) {
                            _data.writeInt(1);
                            request.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        try {
                            _data.writeString(packageName);
                            try {
                                _data.writeString(attributionTag);
                                try {
                                    _data.writeString(listenerId);
                                    try {
                                        boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                                        if (_status || Stub.getDefaultImpl() == null) {
                                            _reply.readException();
                                            ICancellationSignal _result = ICancellationSignal.Stub.asInterface(_reply.readStrongBinder());
                                            _reply.recycle();
                                            _data.recycle();
                                            return _result;
                                        }
                                        ICancellationSignal currentLocation = Stub.getDefaultImpl().getCurrentLocation(provider, request, callback, packageName, attributionTag, listenerId);
                                        _reply.recycle();
                                        _data.recycle();
                                        return currentLocation;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        _reply.recycle();
                                        _data.recycle();
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.location.ILocationManager
            public void registerLocationListener(String provider, LocationRequest request, ILocationListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(provider);
                        if (request != null) {
                            _data.writeInt(1);
                            request.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(packageName);
                    try {
                        _data.writeString(attributionTag);
                        try {
                            _data.writeString(listenerId);
                            try {
                                boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().registerLocationListener(provider, request, listener, packageName, attributionTag, listenerId);
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th4) {
                                th = th4;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.location.ILocationManager
            public void unregisterLocationListener(ILocationListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterLocationListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void registerLocationPendingIntent(String provider, LocationRequest request, PendingIntent pendingIntent, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        _data.writeInt(1);
                        pendingIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerLocationPendingIntent(provider, request, pendingIntent, packageName, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void unregisterLocationPendingIntent(PendingIntent pendingIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        _data.writeInt(1);
                        pendingIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterLocationPendingIntent(pendingIntent);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void injectLocation(Location location) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (location != null) {
                        _data.writeInt(1);
                        location.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().injectLocation(location);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void requestListenerFlush(String provider, ILocationListener listener, int requestCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(requestCode);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestListenerFlush(provider, listener, requestCode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void requestPendingIntentFlush(String provider, PendingIntent pendingIntent, int requestCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    if (pendingIntent != null) {
                        _data.writeInt(1);
                        pendingIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(requestCode);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestPendingIntentFlush(provider, pendingIntent, requestCode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void requestGeofence(Geofence geofence, PendingIntent intent, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (geofence != null) {
                        _data.writeInt(1);
                        geofence.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestGeofence(geofence, intent, packageName, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGeofence(PendingIntent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeGeofence(intent);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean geocoderIsPresent() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().geocoderIsPresent();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void getFromLocation(double latitude, double longitude, int maxResults, GeocoderParams params, IGeocodeListener listener) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeDouble(latitude);
                    try {
                        _data.writeDouble(longitude);
                        _data.writeInt(maxResults);
                        if (params != null) {
                            _data.writeInt(1);
                            params.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                        boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().getFromLocation(latitude, longitude, maxResults, params, listener);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.location.ILocationManager
            public void getFromLocationName(String locationName, double lowerLeftLatitude, double lowerLeftLongitude, double upperRightLatitude, double upperRightLongitude, int maxResults, GeocoderParams params, IGeocodeListener listener) throws RemoteException {
                Parcel _reply;
                Throwable th;
                Parcel _reply2;
                Parcel _data = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(locationName);
                    _data.writeDouble(lowerLeftLatitude);
                    _data.writeDouble(lowerLeftLongitude);
                    _data.writeDouble(upperRightLatitude);
                    _data.writeDouble(upperRightLongitude);
                    _data.writeInt(maxResults);
                    if (params != null) {
                        try {
                            _data.writeInt(1);
                            params.writeToParcel(_data, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply3, 0);
                    try {
                        if (_status) {
                            _reply2 = _reply3;
                        } else if (Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().getFromLocationName(locationName, lowerLeftLatitude, lowerLeftLongitude, upperRightLatitude, upperRightLongitude, maxResults, params, listener);
                            _reply3.recycle();
                            _data.recycle();
                            return;
                        } else {
                            _reply2 = _reply3;
                        }
                        _reply2.readException();
                        _reply2.recycle();
                        _data.recycle();
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    _reply = _reply3;
                }
            }

            @Override // android.location.ILocationManager
            public GnssCapabilities getGnssCapabilities() throws RemoteException {
                GnssCapabilities _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssCapabilities();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GnssCapabilities.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public int getGnssYearOfHardware() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssYearOfHardware();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getGnssHardwareModelName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssHardwareModelName();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<GnssAntennaInfo> getGnssAntennaInfos() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssAntennaInfos();
                    }
                    _reply.readException();
                    List<GnssAntennaInfo> _result = _reply.createTypedArrayList(GnssAntennaInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void registerGnssStatusCallback(IGnssStatusListener callback, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    _data.writeString(listenerId);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerGnssStatusCallback(callback, packageName, attributionTag, listenerId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void unregisterGnssStatusCallback(IGnssStatusListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterGnssStatusCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void registerGnssNmeaCallback(IGnssNmeaListener callback, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    _data.writeString(listenerId);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerGnssNmeaCallback(callback, packageName, attributionTag, listenerId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void unregisterGnssNmeaCallback(IGnssNmeaListener callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterGnssNmeaCallback(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void addGnssMeasurementsListener(GnssMeasurementRequest request, IGnssMeasurementsListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    _data.writeString(listenerId);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addGnssMeasurementsListener(request, listener, packageName, attributionTag, listenerId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGnssMeasurementsListener(IGnssMeasurementsListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeGnssMeasurementsListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void injectGnssMeasurementCorrections(GnssMeasurementCorrections corrections) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (corrections != null) {
                        _data.writeInt(1);
                        corrections.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().injectGnssMeasurementCorrections(corrections);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void addGnssNavigationMessageListener(IGnssNavigationMessageListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    _data.writeString(listenerId);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addGnssNavigationMessageListener(listener, packageName, attributionTag, listenerId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGnssNavigationMessageListener(IGnssNavigationMessageListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeGnssNavigationMessageListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void addGnssAntennaInfoListener(IGnssAntennaInfoListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    _data.writeString(listenerId);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addGnssAntennaInfoListener(listener, packageName, attributionTag, listenerId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGnssAntennaInfoListener(IGnssAntennaInfoListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeGnssAntennaInfoListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void addProviderRequestListener(IProviderRequestListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addProviderRequestListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeProviderRequestListener(IProviderRequestListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeProviderRequestListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public int getGnssBatchSize() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssBatchSize();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void startGnssBatch(long periodNanos, ILocationListener listener, String packageName, String attributionTag, String listenerId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeLong(periodNanos);
                        _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(packageName);
                    try {
                        _data.writeString(attributionTag);
                        try {
                            _data.writeString(listenerId);
                            try {
                                boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().startGnssBatch(periodNanos, listener, packageName, attributionTag, listenerId);
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th4) {
                                th = th4;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.location.ILocationManager
            public void flushGnssBatch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().flushGnssBatch();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void stopGnssBatch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopGnssBatch();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean hasProvider(String provider) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasProvider(provider);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<String> getAllProviders() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllProviders();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<String> getProviders(Criteria criteria, boolean enabledOnly) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (criteria != null) {
                        _data.writeInt(1);
                        criteria.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!enabledOnly) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProviders(criteria, enabledOnly);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getBestProvider(Criteria criteria, boolean enabledOnly) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (criteria != null) {
                        _data.writeInt(1);
                        criteria.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!enabledOnly) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBestProvider(criteria, enabledOnly);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public ProviderProperties getProviderProperties(String provider) throws RemoteException {
                ProviderProperties _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProviderProperties(provider);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ProviderProperties.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isProviderPackage(String provider, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProviderPackage(provider, packageName, attributionTag);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<String> getProviderPackages(String provider) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProviderPackages(provider);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setExtraLocationControllerPackage(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setExtraLocationControllerPackage(packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getExtraLocationControllerPackage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExtraLocationControllerPackage();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setExtraLocationControllerPackageEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setExtraLocationControllerPackageEnabled(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isExtraLocationControllerPackageEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isExtraLocationControllerPackageEnabled();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isProviderEnabledForUser(String provider, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProviderEnabledForUser(provider, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isLocationEnabledForUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLocationEnabledForUser(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setLocationEnabledForUser(boolean enabled, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLocationEnabledForUser(enabled, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isAdasGnssLocationEnabledForUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAdasGnssLocationEnabledForUser(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setAdasGnssLocationEnabledForUser(boolean enabled, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAdasGnssLocationEnabledForUser(enabled, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void addTestProvider(String name, ProviderProperties properties, List<String> locationTags, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    if (properties != null) {
                        _data.writeInt(1);
                        properties.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(locationTags);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addTestProvider(name, properties, locationTags, packageName, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeTestProvider(String provider, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeTestProvider(provider, packageName, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setTestProviderLocation(String provider, Location location, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    if (location != null) {
                        _data.writeInt(1);
                        location.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestProviderLocation(provider, location, packageName, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setTestProviderEnabled(String provider, boolean enabled, String packageName, String attributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeString(packageName);
                    _data.writeString(attributionTag);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTestProviderEnabled(provider, enabled, packageName, attributionTag);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public LocationTime getGnssTimeMillis() throws RemoteException {
                LocationTime _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssTimeMillis();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LocationTime.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void sendExtraCommand(String provider, String command, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(provider);
                    _data.writeString(command);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            extras.readFromParcel(_reply);
                        }
                        return;
                    }
                    Stub.getDefaultImpl().sendExtraCommand(provider, command, extras);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String[] getBackgroundThrottlingWhitelist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackgroundThrottlingWhitelist();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public PackageTagsList getIgnoreSettingsAllowlist() throws RemoteException {
                PackageTagsList _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIgnoreSettingsAllowlist();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PackageTagsList.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ILocationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
