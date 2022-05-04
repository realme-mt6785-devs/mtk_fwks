package android.permission;
/* loaded from: classes2.dex */
public final class PermGroupUsage {
    private final CharSequence mAttribution;
    private final boolean mIsActive;
    private final boolean mIsPhoneCall;
    private final long mLastAccess;
    private final String mPackageName;
    private final String mPermGroupName;
    private final int mUid;

    public PermGroupUsage(String packageName, int uid, String permGroupName, long lastAccess, boolean isActive, boolean isPhoneCall, CharSequence attribution) {
        this.mPackageName = packageName;
        this.mUid = uid;
        this.mPermGroupName = permGroupName;
        this.mLastAccess = lastAccess;
        this.mIsActive = isActive;
        this.mIsPhoneCall = isPhoneCall;
        this.mAttribution = attribution;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getUid() {
        return this.mUid;
    }

    public String getPermGroupName() {
        return this.mPermGroupName;
    }

    public long getLastAccess() {
        return this.mLastAccess;
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public boolean isPhoneCall() {
        return this.mIsPhoneCall;
    }

    public CharSequence getAttribution() {
        return this.mAttribution;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(this)) + " packageName: " + this.mPackageName + ", UID: " + this.mUid + ", permGroup: " + this.mPermGroupName + ", lastAccess: " + this.mLastAccess + ", isActive: " + this.mIsActive + ", attribution: " + ((Object) this.mAttribution);
    }
}
