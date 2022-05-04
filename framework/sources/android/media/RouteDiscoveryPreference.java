package android.media;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
/* loaded from: classes2.dex */
public final class RouteDiscoveryPreference implements Parcelable {
    public static final Parcelable.Creator<RouteDiscoveryPreference> CREATOR = new Parcelable.Creator<RouteDiscoveryPreference>() { // from class: android.media.RouteDiscoveryPreference.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteDiscoveryPreference createFromParcel(Parcel in) {
            return new RouteDiscoveryPreference(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteDiscoveryPreference[] newArray(int size) {
            return new RouteDiscoveryPreference[size];
        }
    };
    @SystemApi
    public static final RouteDiscoveryPreference EMPTY = new Builder(Collections.emptyList(), false).build();
    private final Bundle mExtras;
    private final List<String> mPreferredFeatures;
    private final boolean mShouldPerformActiveScan;

    RouteDiscoveryPreference(Builder builder) {
        this.mPreferredFeatures = builder.mPreferredFeatures;
        this.mShouldPerformActiveScan = builder.mActiveScan;
        this.mExtras = builder.mExtras;
    }

    RouteDiscoveryPreference(Parcel in) {
        this.mPreferredFeatures = in.createStringArrayList();
        this.mShouldPerformActiveScan = in.readBoolean();
        this.mExtras = in.readBundle();
    }

    public List<String> getPreferredFeatures() {
        return this.mPreferredFeatures;
    }

    public boolean shouldPerformActiveScan() {
        return this.mShouldPerformActiveScan;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.mPreferredFeatures);
        dest.writeBoolean(this.mShouldPerformActiveScan);
        dest.writeBundle(this.mExtras);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RouteDiscoveryRequest{ ");
        sb.append("preferredFeatures={");
        sb.append(String.join(", ", this.mPreferredFeatures));
        sb.append("}");
        sb.append(", activeScan=");
        sb.append(this.mShouldPerformActiveScan);
        StringBuilder result = sb.append(" }");
        return result.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RouteDiscoveryPreference)) {
            return false;
        }
        RouteDiscoveryPreference other = (RouteDiscoveryPreference) o;
        return Objects.equals(this.mPreferredFeatures, other.mPreferredFeatures) && this.mShouldPerformActiveScan == other.mShouldPerformActiveScan;
    }

    public int hashCode() {
        return Objects.hash(this.mPreferredFeatures, Boolean.valueOf(this.mShouldPerformActiveScan));
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        boolean mActiveScan;
        Bundle mExtras;
        List<String> mPreferredFeatures;

        public Builder(List<String> preferredFeatures, boolean activeScan) {
            Objects.requireNonNull(preferredFeatures, "preferredFeatures must not be null");
            this.mPreferredFeatures = (List) preferredFeatures.stream().filter(RouteDiscoveryPreference$Builder$$ExternalSyntheticLambda0.INSTANCE).collect(Collectors.toList());
            this.mActiveScan = activeScan;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$new$0(String str) {
            return !TextUtils.isEmpty(str);
        }

        public Builder(RouteDiscoveryPreference preference) {
            Objects.requireNonNull(preference, "preference must not be null");
            this.mPreferredFeatures = preference.getPreferredFeatures();
            this.mActiveScan = preference.shouldPerformActiveScan();
            this.mExtras = preference.getExtras();
        }

        public Builder(Collection<RouteDiscoveryPreference> preferences) {
            Objects.requireNonNull(preferences, "preferences must not be null");
            Set<String> routeFeatureSet = new HashSet<>();
            this.mActiveScan = false;
            for (RouteDiscoveryPreference preference : preferences) {
                routeFeatureSet.addAll(preference.mPreferredFeatures);
                this.mActiveScan |= preference.mShouldPerformActiveScan;
            }
            this.mPreferredFeatures = new ArrayList(routeFeatureSet);
        }

        public Builder setPreferredFeatures(List<String> preferredFeatures) {
            Objects.requireNonNull(preferredFeatures, "preferredFeatures must not be null");
            this.mPreferredFeatures = (List) preferredFeatures.stream().filter(RouteDiscoveryPreference$Builder$$ExternalSyntheticLambda1.INSTANCE).collect(Collectors.toList());
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$setPreferredFeatures$1(String str) {
            return !TextUtils.isEmpty(str);
        }

        public Builder setShouldPerformActiveScan(boolean activeScan) {
            this.mActiveScan = activeScan;
            return this;
        }

        public Builder setExtras(Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        public RouteDiscoveryPreference build() {
            return new RouteDiscoveryPreference(this);
        }
    }
}
