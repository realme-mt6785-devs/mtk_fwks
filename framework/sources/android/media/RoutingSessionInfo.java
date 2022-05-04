package android.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class RoutingSessionInfo implements Parcelable {
    public static final Parcelable.Creator<RoutingSessionInfo> CREATOR = new Parcelable.Creator<RoutingSessionInfo>() { // from class: android.media.RoutingSessionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutingSessionInfo createFromParcel(Parcel in) {
            return new RoutingSessionInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutingSessionInfo[] newArray(int size) {
            return new RoutingSessionInfo[size];
        }
    };
    private static final String TAG = "RoutingSessionInfo";
    final String mClientPackageName;
    final Bundle mControlHints;
    final List<String> mDeselectableRoutes;
    final String mId;
    final boolean mIsSystemSession;
    final CharSequence mName;
    final String mOwnerPackageName;
    final String mProviderId;
    final List<String> mSelectableRoutes;
    final List<String> mSelectedRoutes;
    final List<String> mTransferableRoutes;
    final int mVolume;
    final int mVolumeHandling;
    final int mVolumeMax;

    RoutingSessionInfo(Builder builder) {
        Objects.requireNonNull(builder, "builder must not be null.");
        this.mId = builder.mId;
        this.mName = builder.mName;
        this.mOwnerPackageName = builder.mOwnerPackageName;
        this.mClientPackageName = builder.mClientPackageName;
        this.mProviderId = builder.mProviderId;
        this.mSelectedRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mSelectedRoutes));
        this.mSelectableRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mSelectableRoutes));
        this.mDeselectableRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mDeselectableRoutes));
        this.mTransferableRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mTransferableRoutes));
        this.mVolumeHandling = builder.mVolumeHandling;
        this.mVolumeMax = builder.mVolumeMax;
        this.mVolume = builder.mVolume;
        this.mControlHints = builder.mControlHints;
        this.mIsSystemSession = builder.mIsSystemSession;
    }

    RoutingSessionInfo(Parcel src) {
        Objects.requireNonNull(src, "src must not be null.");
        this.mId = ensureString(src.readString());
        this.mName = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(src);
        this.mOwnerPackageName = src.readString();
        this.mClientPackageName = ensureString(src.readString());
        this.mProviderId = src.readString();
        this.mSelectedRoutes = ensureList(src.createStringArrayList());
        this.mSelectableRoutes = ensureList(src.createStringArrayList());
        this.mDeselectableRoutes = ensureList(src.createStringArrayList());
        this.mTransferableRoutes = ensureList(src.createStringArrayList());
        this.mVolumeHandling = src.readInt();
        this.mVolumeMax = src.readInt();
        this.mVolume = src.readInt();
        this.mControlHints = src.readBundle();
        this.mIsSystemSession = src.readBoolean();
    }

    private static String ensureString(String str) {
        return str != null ? str : "";
    }

    private static <T> List<T> ensureList(List<? extends T> list) {
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
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

    public String getOriginalId() {
        return this.mId;
    }

    public String getOwnerPackageName() {
        return this.mOwnerPackageName;
    }

    public String getClientPackageName() {
        return this.mClientPackageName;
    }

    public String getProviderId() {
        return this.mProviderId;
    }

    public List<String> getSelectedRoutes() {
        return this.mSelectedRoutes;
    }

    public List<String> getSelectableRoutes() {
        return this.mSelectableRoutes;
    }

    public List<String> getDeselectableRoutes() {
        return this.mDeselectableRoutes;
    }

    public List<String> getTransferableRoutes() {
        return this.mTransferableRoutes;
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

    public Bundle getControlHints() {
        return this.mControlHints;
    }

    public boolean isSystemSession() {
        return this.mIsSystemSession;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeCharSequence(this.mName);
        dest.writeString(this.mOwnerPackageName);
        dest.writeString(this.mClientPackageName);
        dest.writeString(this.mProviderId);
        dest.writeStringList(this.mSelectedRoutes);
        dest.writeStringList(this.mSelectableRoutes);
        dest.writeStringList(this.mDeselectableRoutes);
        dest.writeStringList(this.mTransferableRoutes);
        dest.writeInt(this.mVolumeHandling);
        dest.writeInt(this.mVolumeMax);
        dest.writeInt(this.mVolume);
        dest.writeBundle(this.mControlHints);
        dest.writeBoolean(this.mIsSystemSession);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoutingSessionInfo)) {
            return false;
        }
        RoutingSessionInfo other = (RoutingSessionInfo) obj;
        return Objects.equals(this.mId, other.mId) && Objects.equals(this.mName, other.mName) && Objects.equals(this.mOwnerPackageName, other.mOwnerPackageName) && Objects.equals(this.mClientPackageName, other.mClientPackageName) && Objects.equals(this.mProviderId, other.mProviderId) && Objects.equals(this.mSelectedRoutes, other.mSelectedRoutes) && Objects.equals(this.mSelectableRoutes, other.mSelectableRoutes) && Objects.equals(this.mDeselectableRoutes, other.mDeselectableRoutes) && Objects.equals(this.mTransferableRoutes, other.mTransferableRoutes) && this.mVolumeHandling == other.mVolumeHandling && this.mVolumeMax == other.mVolumeMax && this.mVolume == other.mVolume;
    }

    public int hashCode() {
        return Objects.hash(this.mId, this.mName, this.mOwnerPackageName, this.mClientPackageName, this.mProviderId, this.mSelectedRoutes, this.mSelectableRoutes, this.mDeselectableRoutes, this.mTransferableRoutes, Integer.valueOf(this.mVolumeMax), Integer.valueOf(this.mVolumeHandling), Integer.valueOf(this.mVolume));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RoutingSessionInfo{ ");
        sb.append("sessionId=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", clientPackageName=");
        sb.append(getClientPackageName());
        sb.append(", selectedRoutes={");
        sb.append(String.join(",", getSelectedRoutes()));
        sb.append("}");
        sb.append(", selectableRoutes={");
        sb.append(String.join(",", getSelectableRoutes()));
        sb.append("}");
        sb.append(", deselectableRoutes={");
        sb.append(String.join(",", getDeselectableRoutes()));
        sb.append("}");
        sb.append(", transferableRoutes={");
        sb.append(String.join(",", getTransferableRoutes()));
        sb.append("}");
        sb.append(", volumeHandling=");
        sb.append(getVolumeHandling());
        sb.append(", volumeMax=");
        sb.append(getVolumeMax());
        sb.append(", volume=");
        sb.append(getVolume());
        StringBuilder result = sb.append(" }");
        return result.toString();
    }

    private List<String> convertToUniqueRouteIds(List<String> routeIds) {
        if (routeIds == null) {
            Log.w(TAG, "routeIds is null. Returning an empty list");
            return Collections.emptyList();
        } else if (this.mProviderId == null) {
            return routeIds;
        } else {
            List<String> result = new ArrayList<>();
            for (String routeId : routeIds) {
                result.add(MediaRouter2Utils.toUniqueId(this.mProviderId, routeId));
            }
            return result;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        String mClientPackageName;
        Bundle mControlHints;
        final List<String> mDeselectableRoutes;
        final String mId;
        boolean mIsSystemSession;
        CharSequence mName;
        String mOwnerPackageName;
        String mProviderId;
        final List<String> mSelectableRoutes;
        final List<String> mSelectedRoutes;
        final List<String> mTransferableRoutes;
        int mVolume;
        int mVolumeHandling;
        int mVolumeMax;

        public Builder(String id, String clientPackageName) {
            this.mVolumeHandling = 0;
            if (!TextUtils.isEmpty(id)) {
                this.mId = id;
                Objects.requireNonNull(clientPackageName, "clientPackageName must not be null");
                this.mClientPackageName = clientPackageName;
                this.mSelectedRoutes = new ArrayList();
                this.mSelectableRoutes = new ArrayList();
                this.mDeselectableRoutes = new ArrayList();
                this.mTransferableRoutes = new ArrayList();
                return;
            }
            throw new IllegalArgumentException("id must not be empty");
        }

        public Builder(RoutingSessionInfo sessionInfo) {
            this.mVolumeHandling = 0;
            Objects.requireNonNull(sessionInfo, "sessionInfo must not be null");
            this.mId = sessionInfo.mId;
            this.mName = sessionInfo.mName;
            this.mClientPackageName = sessionInfo.mClientPackageName;
            this.mProviderId = sessionInfo.mProviderId;
            ArrayList arrayList = new ArrayList(sessionInfo.mSelectedRoutes);
            this.mSelectedRoutes = arrayList;
            ArrayList arrayList2 = new ArrayList(sessionInfo.mSelectableRoutes);
            this.mSelectableRoutes = arrayList2;
            ArrayList arrayList3 = new ArrayList(sessionInfo.mDeselectableRoutes);
            this.mDeselectableRoutes = arrayList3;
            ArrayList arrayList4 = new ArrayList(sessionInfo.mTransferableRoutes);
            this.mTransferableRoutes = arrayList4;
            if (this.mProviderId != null) {
                arrayList.replaceAll(RoutingSessionInfo$Builder$$ExternalSyntheticLambda0.INSTANCE);
                arrayList2.replaceAll(RoutingSessionInfo$Builder$$ExternalSyntheticLambda0.INSTANCE);
                arrayList3.replaceAll(RoutingSessionInfo$Builder$$ExternalSyntheticLambda0.INSTANCE);
                arrayList4.replaceAll(RoutingSessionInfo$Builder$$ExternalSyntheticLambda0.INSTANCE);
            }
            this.mVolumeHandling = sessionInfo.mVolumeHandling;
            this.mVolumeMax = sessionInfo.mVolumeMax;
            this.mVolume = sessionInfo.mVolume;
            this.mControlHints = sessionInfo.mControlHints;
            this.mIsSystemSession = sessionInfo.mIsSystemSession;
        }

        public Builder setName(CharSequence name) {
            this.mName = name;
            return this;
        }

        public Builder setOwnerPackageName(String packageName) {
            this.mOwnerPackageName = packageName;
            return this;
        }

        public Builder setClientPackageName(String packageName) {
            this.mClientPackageName = packageName;
            return this;
        }

        public Builder setProviderId(String providerId) {
            if (!TextUtils.isEmpty(providerId)) {
                this.mProviderId = providerId;
                return this;
            }
            throw new IllegalArgumentException("providerId must not be empty");
        }

        public Builder clearSelectedRoutes() {
            this.mSelectedRoutes.clear();
            return this;
        }

        public Builder addSelectedRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mSelectedRoutes.add(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
        }

        public Builder removeSelectedRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mSelectedRoutes.remove(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
        }

        public Builder clearSelectableRoutes() {
            this.mSelectableRoutes.clear();
            return this;
        }

        public Builder addSelectableRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mSelectableRoutes.add(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
        }

        public Builder removeSelectableRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mSelectableRoutes.remove(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
        }

        public Builder clearDeselectableRoutes() {
            this.mDeselectableRoutes.clear();
            return this;
        }

        public Builder addDeselectableRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mDeselectableRoutes.add(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
        }

        public Builder removeDeselectableRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mDeselectableRoutes.remove(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
        }

        public Builder clearTransferableRoutes() {
            this.mTransferableRoutes.clear();
            return this;
        }

        public Builder addTransferableRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mTransferableRoutes.add(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
        }

        public Builder removeTransferableRoute(String routeId) {
            if (!TextUtils.isEmpty(routeId)) {
                this.mTransferableRoutes.remove(routeId);
                return this;
            }
            throw new IllegalArgumentException("routeId must not be empty");
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

        public Builder setControlHints(Bundle controlHints) {
            this.mControlHints = controlHints;
            return this;
        }

        public Builder setSystemSession(boolean isSystemSession) {
            this.mIsSystemSession = isSystemSession;
            return this;
        }

        public RoutingSessionInfo build() {
            if (!this.mSelectedRoutes.isEmpty()) {
                return new RoutingSessionInfo(this);
            }
            throw new IllegalArgumentException("selectedRoutes must not be empty");
        }
    }
}
