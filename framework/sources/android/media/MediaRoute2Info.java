package android.media;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class MediaRoute2Info implements Parcelable {
    public static final int CONNECTION_STATE_CONNECTED = 2;
    public static final int CONNECTION_STATE_CONNECTING = 1;
    public static final int CONNECTION_STATE_DISCONNECTED = 0;
    public static final Parcelable.Creator<MediaRoute2Info> CREATOR = new Parcelable.Creator<MediaRoute2Info>() { // from class: android.media.MediaRoute2Info.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaRoute2Info createFromParcel(Parcel in) {
            return new MediaRoute2Info(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaRoute2Info[] newArray(int size) {
            return new MediaRoute2Info[size];
        }
    };
    public static final String FEATURE_LIVE_AUDIO = "android.media.route.feature.LIVE_AUDIO";
    public static final String FEATURE_LIVE_VIDEO = "android.media.route.feature.LIVE_VIDEO";
    public static final String FEATURE_LOCAL_PLAYBACK = "android.media.route.feature.LOCAL_PLAYBACK";
    public static final String FEATURE_REMOTE_AUDIO_PLAYBACK = "android.media.route.feature.REMOTE_AUDIO_PLAYBACK";
    public static final String FEATURE_REMOTE_GROUP_PLAYBACK = "android.media.route.feature.REMOTE_GROUP_PLAYBACK";
    public static final String FEATURE_REMOTE_PLAYBACK = "android.media.route.feature.REMOTE_PLAYBACK";
    public static final String FEATURE_REMOTE_VIDEO_PLAYBACK = "android.media.route.feature.REMOTE_VIDEO_PLAYBACK";
    public static final int PLAYBACK_VOLUME_FIXED = 0;
    public static final int PLAYBACK_VOLUME_VARIABLE = 1;
    public static final int TYPE_BLUETOOTH_A2DP = 8;
    public static final int TYPE_BUILTIN_SPEAKER = 2;
    public static final int TYPE_DOCK = 13;
    public static final int TYPE_GROUP = 2000;
    public static final int TYPE_HDMI = 9;
    public static final int TYPE_HEARING_AID = 23;
    public static final int TYPE_REMOTE_SPEAKER = 1002;
    public static final int TYPE_REMOTE_TV = 1001;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_USB_ACCESSORY = 12;
    public static final int TYPE_USB_DEVICE = 11;
    public static final int TYPE_USB_HEADSET = 22;
    public static final int TYPE_WIRED_HEADPHONES = 4;
    public static final int TYPE_WIRED_HEADSET = 3;
    final String mAddress;
    final String mClientPackageName;
    final int mConnectionState;
    final CharSequence mDescription;
    final Bundle mExtras;
    final List<String> mFeatures;
    final Uri mIconUri;
    final String mId;
    final boolean mIsSystem;
    final CharSequence mName;
    final String mProviderId;
    final int mType;
    final int mVolume;
    final int mVolumeHandling;
    final int mVolumeMax;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ConnectionState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PlaybackVolume {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Type {
    }

    MediaRoute2Info(Builder builder) {
        this.mId = builder.mId;
        this.mName = builder.mName;
        this.mFeatures = builder.mFeatures;
        this.mType = builder.mType;
        this.mIsSystem = builder.mIsSystem;
        this.mIconUri = builder.mIconUri;
        this.mDescription = builder.mDescription;
        this.mConnectionState = builder.mConnectionState;
        this.mClientPackageName = builder.mClientPackageName;
        this.mVolumeHandling = builder.mVolumeHandling;
        this.mVolumeMax = builder.mVolumeMax;
        this.mVolume = builder.mVolume;
        this.mAddress = builder.mAddress;
        this.mExtras = builder.mExtras;
        this.mProviderId = builder.mProviderId;
    }

    MediaRoute2Info(Parcel in) {
        this.mId = in.readString();
        this.mName = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.mFeatures = in.createStringArrayList();
        this.mType = in.readInt();
        this.mIsSystem = in.readBoolean();
        this.mIconUri = (Uri) in.readParcelable(null);
        this.mDescription = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.mConnectionState = in.readInt();
        this.mClientPackageName = in.readString();
        this.mVolumeHandling = in.readInt();
        this.mVolumeMax = in.readInt();
        this.mVolume = in.readInt();
        this.mAddress = in.readString();
        this.mExtras = in.readBundle();
        this.mProviderId = in.readString();
    }

    public String getId() {
        String str = this.mProviderId;
        if (str != null) {
            return MediaRouter2Utils.toUniqueId(str, this.mId);
        }
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public List<String> getFeatures() {
        return this.mFeatures;
    }

    public int getType() {
        return this.mType;
    }

    public boolean isSystemRoute() {
        return this.mIsSystem;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public int getConnectionState() {
        return this.mConnectionState;
    }

    public String getClientPackageName() {
        return this.mClientPackageName;
    }

    public int getVolumeHandling() {
        return this.mVolumeHandling;
    }

    public int getVolumeMax() {
        return this.mVolumeMax;
    }

    public int getVolume() {
        return this.mVolume;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public Bundle getExtras() {
        if (this.mExtras == null) {
            return null;
        }
        return new Bundle(this.mExtras);
    }

    public String getOriginalId() {
        return this.mId;
    }

    public String getProviderId() {
        return this.mProviderId;
    }

    public boolean hasAnyFeatures(Collection<String> features) {
        Objects.requireNonNull(features, "features must not be null");
        for (String feature : features) {
            if (getFeatures().contains(feature)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(getId()) || TextUtils.isEmpty(getName()) || TextUtils.isEmpty(getProviderId())) {
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRoute2Info)) {
            return false;
        }
        MediaRoute2Info other = (MediaRoute2Info) obj;
        return Objects.equals(this.mId, other.mId) && Objects.equals(this.mName, other.mName) && Objects.equals(this.mFeatures, other.mFeatures) && this.mType == other.mType && this.mIsSystem == other.mIsSystem && Objects.equals(this.mIconUri, other.mIconUri) && Objects.equals(this.mDescription, other.mDescription) && this.mConnectionState == other.mConnectionState && Objects.equals(this.mClientPackageName, other.mClientPackageName) && this.mVolumeHandling == other.mVolumeHandling && this.mVolumeMax == other.mVolumeMax && this.mVolume == other.mVolume && Objects.equals(this.mAddress, other.mAddress) && Objects.equals(this.mProviderId, other.mProviderId);
    }

    public int hashCode() {
        return Objects.hash(this.mId, this.mName, this.mFeatures, Integer.valueOf(this.mType), Boolean.valueOf(this.mIsSystem), this.mIconUri, this.mDescription, Integer.valueOf(this.mConnectionState), this.mClientPackageName, Integer.valueOf(this.mVolumeHandling), Integer.valueOf(this.mVolumeMax), Integer.valueOf(this.mVolume), this.mAddress, this.mProviderId);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediaRoute2Info{ ");
        sb.append("id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", features=");
        sb.append(getFeatures());
        sb.append(", iconUri=");
        sb.append(getIconUri());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", connectionState=");
        sb.append(getConnectionState());
        sb.append(", clientPackageName=");
        sb.append(getClientPackageName());
        sb.append(", volumeHandling=");
        sb.append(getVolumeHandling());
        sb.append(", volumeMax=");
        sb.append(getVolumeMax());
        sb.append(", volume=");
        sb.append(getVolume());
        sb.append(", providerId=");
        sb.append(getProviderId());
        StringBuilder result = sb.append(" }");
        return result.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        TextUtils.writeToParcel(this.mName, dest, flags);
        dest.writeStringList(this.mFeatures);
        dest.writeInt(this.mType);
        dest.writeBoolean(this.mIsSystem);
        dest.writeParcelable(this.mIconUri, flags);
        TextUtils.writeToParcel(this.mDescription, dest, flags);
        dest.writeInt(this.mConnectionState);
        dest.writeString(this.mClientPackageName);
        dest.writeInt(this.mVolumeHandling);
        dest.writeInt(this.mVolumeMax);
        dest.writeInt(this.mVolume);
        dest.writeString(this.mAddress);
        dest.writeBundle(this.mExtras);
        dest.writeString(this.mProviderId);
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        String mAddress;
        String mClientPackageName;
        int mConnectionState;
        CharSequence mDescription;
        Bundle mExtras;
        final List<String> mFeatures;
        Uri mIconUri;
        final String mId;
        boolean mIsSystem;
        final CharSequence mName;
        String mProviderId;
        int mType;
        int mVolume;
        int mVolumeHandling;
        int mVolumeMax;

        public Builder(String id, CharSequence name) {
            this.mType = 0;
            this.mVolumeHandling = 0;
            if (TextUtils.isEmpty(id)) {
                throw new IllegalArgumentException("id must not be empty");
            } else if (!TextUtils.isEmpty(name)) {
                this.mId = id;
                this.mName = name;
                this.mFeatures = new ArrayList();
            } else {
                throw new IllegalArgumentException("name must not be empty");
            }
        }

        public Builder(MediaRoute2Info routeInfo) {
            this(routeInfo.mId, routeInfo);
        }

        public Builder(String id, MediaRoute2Info routeInfo) {
            this.mType = 0;
            this.mVolumeHandling = 0;
            if (!TextUtils.isEmpty(id)) {
                Objects.requireNonNull(routeInfo, "routeInfo must not be null");
                this.mId = id;
                this.mName = routeInfo.mName;
                this.mFeatures = new ArrayList(routeInfo.mFeatures);
                this.mType = routeInfo.mType;
                this.mIsSystem = routeInfo.mIsSystem;
                this.mIconUri = routeInfo.mIconUri;
                this.mDescription = routeInfo.mDescription;
                this.mConnectionState = routeInfo.mConnectionState;
                this.mClientPackageName = routeInfo.mClientPackageName;
                this.mVolumeHandling = routeInfo.mVolumeHandling;
                this.mVolumeMax = routeInfo.mVolumeMax;
                this.mVolume = routeInfo.mVolume;
                this.mAddress = routeInfo.mAddress;
                if (routeInfo.mExtras != null) {
                    this.mExtras = new Bundle(routeInfo.mExtras);
                }
                this.mProviderId = routeInfo.mProviderId;
                return;
            }
            throw new IllegalArgumentException("id must not be empty");
        }

        public Builder addFeature(String feature) {
            if (!TextUtils.isEmpty(feature)) {
                this.mFeatures.add(feature);
                return this;
            }
            throw new IllegalArgumentException("feature must not be null or empty");
        }

        public Builder addFeatures(Collection<String> features) {
            Objects.requireNonNull(features, "features must not be null");
            for (String feature : features) {
                addFeature(feature);
            }
            return this;
        }

        public Builder clearFeatures() {
            this.mFeatures.clear();
            return this;
        }

        public Builder setType(int type) {
            this.mType = type;
            return this;
        }

        public Builder setSystemRoute(boolean isSystem) {
            this.mIsSystem = isSystem;
            return this;
        }

        public Builder setIconUri(Uri iconUri) {
            this.mIconUri = iconUri;
            return this;
        }

        public Builder setDescription(CharSequence description) {
            this.mDescription = description;
            return this;
        }

        public Builder setConnectionState(int connectionState) {
            this.mConnectionState = connectionState;
            return this;
        }

        public Builder setClientPackageName(String packageName) {
            this.mClientPackageName = packageName;
            return this;
        }

        public Builder setVolumeHandling(int volumeHandling) {
            this.mVolumeHandling = volumeHandling;
            return this;
        }

        public Builder setVolumeMax(int volumeMax) {
            this.mVolumeMax = volumeMax;
            return this;
        }

        public Builder setVolume(int volume) {
            this.mVolume = volume;
            return this;
        }

        public Builder setAddress(String address) {
            this.mAddress = address;
            return this;
        }

        public Builder setExtras(Bundle extras) {
            if (extras == null) {
                this.mExtras = null;
                return this;
            }
            this.mExtras = new Bundle(extras);
            return this;
        }

        public Builder setProviderId(String providerId) {
            if (!TextUtils.isEmpty(providerId)) {
                this.mProviderId = providerId;
                return this;
            }
            throw new IllegalArgumentException("providerId must not be null or empty");
        }

        public MediaRoute2Info build() {
            if (!this.mFeatures.isEmpty()) {
                return new MediaRoute2Info(this);
            }
            throw new IllegalArgumentException("features must not be empty!");
        }
    }
}
