package android.net.vcn;

import android.net.ipsec.ike.IkeTunnelConnectionParams;
import android.net.vcn.persistablebundleutils.TunnelConnectionParamsUtils;
import android.os.PersistableBundle;
import android.util.ArraySet;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import com.android.server.vcn.repackaged.util.PersistableBundleUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class VcnGatewayConnectionConfig {
    public static final Set<Integer> ALLOWED_CAPABILITIES;
    private static final int DEFAULT_MAX_MTU = 1500;
    private static final String EXPOSED_CAPABILITIES_KEY = "mExposedCapabilities";
    private static final String GATEWAY_CONNECTION_NAME_KEY = "mGatewayConnectionName";
    private static final String MAX_MTU_KEY = "mMaxMtu";
    private static final int MAX_RETRY_INTERVAL_COUNT = 10;
    static final int MIN_MTU_V6 = 1280;
    private static final String RETRY_INTERVAL_MS_KEY = "mRetryIntervalsMs";
    private static final String TUNNEL_CONNECTION_PARAMS_KEY = "mTunnelConnectionParams";
    private final SortedSet<Integer> mExposedCapabilities;
    private final String mGatewayConnectionName;
    private final int mMaxMtu;
    private final long[] mRetryIntervalsMs;
    private IkeTunnelConnectionParams mTunnelConnectionParams;
    private static final long MINIMUM_REPEATING_RETRY_INTERVAL_MS = TimeUnit.MINUTES.toMillis(15);
    private static final long[] DEFAULT_RETRY_INTERVALS_MS = {TimeUnit.SECONDS.toMillis(1), TimeUnit.SECONDS.toMillis(2), TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(30), TimeUnit.MINUTES.toMillis(1), TimeUnit.MINUTES.toMillis(5), TimeUnit.MINUTES.toMillis(15)};

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VcnSupportedCapability {
    }

    static {
        Set<Integer> allowedCaps = new ArraySet<>();
        allowedCaps.add(0);
        allowedCaps.add(1);
        allowedCaps.add(2);
        allowedCaps.add(3);
        allowedCaps.add(4);
        allowedCaps.add(5);
        allowedCaps.add(7);
        allowedCaps.add(8);
        allowedCaps.add(9);
        allowedCaps.add(10);
        allowedCaps.add(12);
        allowedCaps.add(23);
        ALLOWED_CAPABILITIES = Collections.unmodifiableSet(allowedCaps);
    }

    private VcnGatewayConnectionConfig(String gatewayConnectionName, IkeTunnelConnectionParams tunnelConnectionParams, Set<Integer> exposedCapabilities, long[] retryIntervalsMs, int maxMtu) {
        this.mGatewayConnectionName = gatewayConnectionName;
        this.mTunnelConnectionParams = tunnelConnectionParams;
        this.mExposedCapabilities = new TreeSet(exposedCapabilities);
        this.mRetryIntervalsMs = retryIntervalsMs;
        this.mMaxMtu = maxMtu;
        validate();
    }

    public VcnGatewayConnectionConfig(PersistableBundle in) {
        PersistableBundle tunnelConnectionParamsBundle = in.getPersistableBundle(TUNNEL_CONNECTION_PARAMS_KEY);
        Objects.requireNonNull(tunnelConnectionParamsBundle, "tunnelConnectionParamsBundle was null");
        PersistableBundle exposedCapsBundle = in.getPersistableBundle(EXPOSED_CAPABILITIES_KEY);
        this.mGatewayConnectionName = in.getString(GATEWAY_CONNECTION_NAME_KEY);
        this.mTunnelConnectionParams = TunnelConnectionParamsUtils.fromPersistableBundle(tunnelConnectionParamsBundle);
        this.mExposedCapabilities = new TreeSet(PersistableBundleUtils.toList(exposedCapsBundle, PersistableBundleUtils.INTEGER_DESERIALIZER));
        this.mRetryIntervalsMs = in.getLongArray(RETRY_INTERVAL_MS_KEY);
        this.mMaxMtu = in.getInt(MAX_MTU_KEY);
        validate();
    }

    private void validate() {
        Objects.requireNonNull(this.mGatewayConnectionName, "gatewayConnectionName was null");
        Objects.requireNonNull(this.mTunnelConnectionParams, "tunnel connection parameter was null");
        SortedSet<Integer> sortedSet = this.mExposedCapabilities;
        boolean z = true;
        Preconditions.checkArgument(sortedSet != null && !sortedSet.isEmpty(), "exposedCapsBundle was null or empty");
        for (Integer cap : getAllExposedCapabilities()) {
            checkValidCapability(cap.intValue());
        }
        Objects.requireNonNull(this.mRetryIntervalsMs, "retryIntervalsMs was null");
        validateRetryInterval(this.mRetryIntervalsMs);
        if (this.mMaxMtu < 1280) {
            z = false;
        }
        Preconditions.checkArgument(z, "maxMtu must be at least IPv6 min MTU (1280)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkValidCapability(int capability) {
        boolean contains = ALLOWED_CAPABILITIES.contains(Integer.valueOf(capability));
        Preconditions.checkArgument(contains, "NetworkCapability " + capability + "out of range");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateRetryInterval(long[] retryIntervalsMs) {
        Preconditions.checkArgument(retryIntervalsMs != null && retryIntervalsMs.length > 0 && retryIntervalsMs.length <= 10, "retryIntervalsMs was null, empty or exceed max interval count");
        long repeatingInterval = retryIntervalsMs[retryIntervalsMs.length - 1];
        if (repeatingInterval < MINIMUM_REPEATING_RETRY_INTERVAL_MS) {
            throw new IllegalArgumentException("Repeating retry interval was too short, must be a minimum of 15 minutes: " + repeatingInterval);
        }
    }

    public String getGatewayConnectionName() {
        return this.mGatewayConnectionName;
    }

    public IkeTunnelConnectionParams getTunnelConnectionParams() {
        return this.mTunnelConnectionParams;
    }

    public int[] getExposedCapabilities() {
        return ArrayUtils.convertToIntArray(new ArrayList(this.mExposedCapabilities));
    }

    @Deprecated
    public Set<Integer> getAllExposedCapabilities() {
        return Collections.unmodifiableSet(this.mExposedCapabilities);
    }

    public long[] getRetryIntervalsMillis() {
        long[] jArr = this.mRetryIntervalsMs;
        return Arrays.copyOf(jArr, jArr.length);
    }

    public int getMaxMtu() {
        return this.mMaxMtu;
    }

    public PersistableBundle toPersistableBundle() {
        PersistableBundle result = new PersistableBundle();
        PersistableBundle tunnelConnectionParamsBundle = TunnelConnectionParamsUtils.toPersistableBundle(this.mTunnelConnectionParams);
        PersistableBundle exposedCapsBundle = PersistableBundleUtils.fromList(new ArrayList(this.mExposedCapabilities), PersistableBundleUtils.INTEGER_SERIALIZER);
        result.putString(GATEWAY_CONNECTION_NAME_KEY, this.mGatewayConnectionName);
        result.putPersistableBundle(TUNNEL_CONNECTION_PARAMS_KEY, tunnelConnectionParamsBundle);
        result.putPersistableBundle(EXPOSED_CAPABILITIES_KEY, exposedCapsBundle);
        result.putLongArray(RETRY_INTERVAL_MS_KEY, this.mRetryIntervalsMs);
        result.putInt(MAX_MTU_KEY, this.mMaxMtu);
        return result;
    }

    public int hashCode() {
        return Objects.hash(this.mGatewayConnectionName, this.mTunnelConnectionParams, this.mExposedCapabilities, Integer.valueOf(Arrays.hashCode(this.mRetryIntervalsMs)), Integer.valueOf(this.mMaxMtu));
    }

    public boolean equals(Object other) {
        if (!(other instanceof VcnGatewayConnectionConfig)) {
            return false;
        }
        VcnGatewayConnectionConfig rhs = (VcnGatewayConnectionConfig) other;
        return this.mGatewayConnectionName.equals(rhs.mGatewayConnectionName) && this.mTunnelConnectionParams.equals(rhs.mTunnelConnectionParams) && this.mExposedCapabilities.equals(rhs.mExposedCapabilities) && Arrays.equals(this.mRetryIntervalsMs, rhs.mRetryIntervalsMs) && this.mMaxMtu == rhs.mMaxMtu;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private final String mGatewayConnectionName;
        private final IkeTunnelConnectionParams mTunnelConnectionParams;
        private final Set<Integer> mExposedCapabilities = new ArraySet();
        private long[] mRetryIntervalsMs = VcnGatewayConnectionConfig.DEFAULT_RETRY_INTERVALS_MS;
        private int mMaxMtu = 1500;

        public Builder(String gatewayConnectionName, IkeTunnelConnectionParams tunnelConnectionParams) {
            Objects.requireNonNull(gatewayConnectionName, "gatewayConnectionName was null");
            Objects.requireNonNull(tunnelConnectionParams, "tunnelConnectionParams was null");
            if (tunnelConnectionParams.getIkeSessionParams().hasIkeOption(2)) {
                this.mGatewayConnectionName = gatewayConnectionName;
                this.mTunnelConnectionParams = tunnelConnectionParams;
                return;
            }
            throw new IllegalArgumentException("MOBIKE must be configured for the provided IkeSessionParams");
        }

        public Builder addExposedCapability(int exposedCapability) {
            VcnGatewayConnectionConfig.checkValidCapability(exposedCapability);
            this.mExposedCapabilities.add(Integer.valueOf(exposedCapability));
            return this;
        }

        public Builder removeExposedCapability(int exposedCapability) {
            VcnGatewayConnectionConfig.checkValidCapability(exposedCapability);
            this.mExposedCapabilities.remove(Integer.valueOf(exposedCapability));
            return this;
        }

        public Builder setRetryIntervalsMillis(long[] retryIntervalsMs) {
            VcnGatewayConnectionConfig.validateRetryInterval(retryIntervalsMs);
            this.mRetryIntervalsMs = retryIntervalsMs;
            return this;
        }

        public Builder setMaxMtu(int maxMtu) {
            Preconditions.checkArgument(maxMtu >= 1280, "maxMtu must be at least IPv6 min MTU (1280)");
            this.mMaxMtu = maxMtu;
            return this;
        }

        public VcnGatewayConnectionConfig build() {
            return new VcnGatewayConnectionConfig(this.mGatewayConnectionName, this.mTunnelConnectionParams, this.mExposedCapabilities, this.mRetryIntervalsMs, this.mMaxMtu);
        }
    }
}
