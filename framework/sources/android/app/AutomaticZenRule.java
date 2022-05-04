package android.app;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.service.notification.ZenPolicy;
import java.util.Objects;
/* loaded from: classes.dex */
public final class AutomaticZenRule implements Parcelable {
    public static final Parcelable.Creator<AutomaticZenRule> CREATOR = new Parcelable.Creator<AutomaticZenRule>() { // from class: android.app.AutomaticZenRule.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutomaticZenRule createFromParcel(Parcel source) {
            return new AutomaticZenRule(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutomaticZenRule[] newArray(int size) {
            return new AutomaticZenRule[size];
        }
    };
    private static final int DISABLED = 0;
    private static final int ENABLED = 1;
    private Uri conditionId;
    private ComponentName configurationActivity;
    private long creationTime;
    private boolean enabled;
    private int interruptionFilter;
    private boolean mModified;
    private String mPkg;
    private ZenPolicy mZenPolicy;
    private String name;
    private ComponentName owner;

    @Deprecated
    public AutomaticZenRule(String name, ComponentName owner, Uri conditionId, int interruptionFilter, boolean enabled) {
        this(name, owner, null, conditionId, null, interruptionFilter, enabled);
    }

    public AutomaticZenRule(String name, ComponentName owner, ComponentName configurationActivity, Uri conditionId, ZenPolicy policy, int interruptionFilter, boolean enabled) {
        this.enabled = false;
        this.mModified = false;
        this.name = name;
        this.owner = owner;
        this.configurationActivity = configurationActivity;
        this.conditionId = conditionId;
        this.interruptionFilter = interruptionFilter;
        this.enabled = enabled;
        this.mZenPolicy = policy;
    }

    public AutomaticZenRule(String name, ComponentName owner, ComponentName configurationActivity, Uri conditionId, ZenPolicy policy, int interruptionFilter, boolean enabled, long creationTime) {
        this(name, owner, configurationActivity, conditionId, policy, interruptionFilter, enabled);
        this.creationTime = creationTime;
    }

    public AutomaticZenRule(Parcel source) {
        boolean z = false;
        this.enabled = false;
        this.mModified = false;
        this.enabled = source.readInt() == 1;
        if (source.readInt() == 1) {
            this.name = source.readString();
        }
        this.interruptionFilter = source.readInt();
        this.conditionId = (Uri) source.readParcelable(null);
        this.owner = (ComponentName) source.readParcelable(null);
        this.configurationActivity = (ComponentName) source.readParcelable(null);
        this.creationTime = source.readLong();
        this.mZenPolicy = (ZenPolicy) source.readParcelable(null);
        this.mModified = source.readInt() == 1 ? true : z;
        this.mPkg = source.readString();
    }

    public ComponentName getOwner() {
        return this.owner;
    }

    public ComponentName getConfigurationActivity() {
        return this.configurationActivity;
    }

    public Uri getConditionId() {
        return this.conditionId;
    }

    public int getInterruptionFilter() {
        return this.interruptionFilter;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isModified() {
        return this.mModified;
    }

    public ZenPolicy getZenPolicy() {
        ZenPolicy zenPolicy = this.mZenPolicy;
        if (zenPolicy == null) {
            return null;
        }
        return zenPolicy.copy();
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public void setConditionId(Uri conditionId) {
        this.conditionId = conditionId;
    }

    public void setInterruptionFilter(int interruptionFilter) {
        this.interruptionFilter = interruptionFilter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setModified(boolean modified) {
        this.mModified = modified;
    }

    public void setZenPolicy(ZenPolicy zenPolicy) {
        this.mZenPolicy = zenPolicy == null ? null : zenPolicy.copy();
    }

    public void setConfigurationActivity(ComponentName componentName) {
        this.configurationActivity = componentName;
    }

    public void setPackageName(String pkgName) {
        this.mPkg = pkgName;
    }

    public String getPackageName() {
        return this.mPkg;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.enabled ? 1 : 0);
        if (this.name != null) {
            dest.writeInt(1);
            dest.writeString(this.name);
        } else {
            dest.writeInt(0);
        }
        dest.writeInt(this.interruptionFilter);
        dest.writeParcelable(this.conditionId, 0);
        dest.writeParcelable(this.owner, 0);
        dest.writeParcelable(this.configurationActivity, 0);
        dest.writeLong(this.creationTime);
        dest.writeParcelable(this.mZenPolicy, 0);
        dest.writeInt(this.mModified ? 1 : 0);
        dest.writeString(this.mPkg);
    }

    public String toString() {
        return AutomaticZenRule.class.getSimpleName() + "[enabled=" + this.enabled + ",name=" + this.name + ",interruptionFilter=" + this.interruptionFilter + ",pkg=" + this.mPkg + ",conditionId=" + this.conditionId + ",owner=" + this.owner + ",configActivity=" + this.configurationActivity + ",creationTime=" + this.creationTime + ",mZenPolicy=" + this.mZenPolicy + ']';
    }

    public boolean equals(Object o) {
        if (!(o instanceof AutomaticZenRule)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        AutomaticZenRule other = (AutomaticZenRule) o;
        return other.enabled == this.enabled && other.mModified == this.mModified && Objects.equals(other.name, this.name) && other.interruptionFilter == this.interruptionFilter && Objects.equals(other.conditionId, this.conditionId) && Objects.equals(other.owner, this.owner) && Objects.equals(other.mZenPolicy, this.mZenPolicy) && Objects.equals(other.configurationActivity, this.configurationActivity) && Objects.equals(other.mPkg, this.mPkg) && other.creationTime == this.creationTime;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.enabled), this.name, Integer.valueOf(this.interruptionFilter), this.conditionId, this.owner, this.configurationActivity, this.mZenPolicy, Boolean.valueOf(this.mModified), Long.valueOf(this.creationTime), this.mPkg);
    }
}
