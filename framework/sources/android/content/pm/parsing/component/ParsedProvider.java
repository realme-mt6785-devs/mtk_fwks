package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.PathPermission;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class ParsedProvider extends ParsedMainComponent {
    public static final Parcelable.Creator<ParsedProvider> CREATOR = new Parcelable.Creator<ParsedProvider>() { // from class: android.content.pm.parsing.component.ParsedProvider.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedProvider createFromParcel(Parcel source) {
            return new ParsedProvider(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedProvider[] newArray(int size) {
            return new ParsedProvider[size];
        }
    };
    private String authority;
    boolean forceUriPermissions;
    boolean grantUriPermissions;
    int initOrder;
    boolean multiProcess;
    PathPermission[] pathPermissions;
    private String readPermission;
    boolean syncable;
    PatternMatcher[] uriPermissionPatterns;
    private String writePermission;

    public ParsedProvider(ParsedProvider other) {
        super(other);
        this.authority = other.authority;
        this.syncable = other.syncable;
        this.readPermission = other.readPermission;
        this.writePermission = other.writePermission;
        this.grantUriPermissions = other.grantUriPermissions;
        this.forceUriPermissions = other.forceUriPermissions;
        this.multiProcess = other.multiProcess;
        this.initOrder = other.initOrder;
        this.uriPermissionPatterns = other.uriPermissionPatterns;
        this.pathPermissions = other.pathPermissions;
    }

    public void setAuthority(String authority) {
        this.authority = TextUtils.safeIntern(authority);
    }

    public void setSyncable(boolean syncable) {
        this.syncable = syncable;
    }

    public void setReadPermission(String readPermission) {
        this.readPermission = TextUtils.isEmpty(readPermission) ? null : readPermission.intern();
    }

    public void setWritePermission(String writePermission) {
        this.writePermission = TextUtils.isEmpty(writePermission) ? null : writePermission.intern();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Provider{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ');
        ComponentName.appendShortString(sb, getPackageName(), getName());
        sb.append('}');
        return sb.toString();
    }

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.authority);
        dest.writeBoolean(this.syncable);
        ParsingPackageImpl.sForInternedString.parcel(this.readPermission, dest, flags);
        ParsingPackageImpl.sForInternedString.parcel(this.writePermission, dest, flags);
        dest.writeBoolean(this.grantUriPermissions);
        dest.writeBoolean(this.forceUriPermissions);
        dest.writeBoolean(this.multiProcess);
        dest.writeInt(this.initOrder);
        dest.writeTypedArray(this.uriPermissionPatterns, flags);
        dest.writeTypedArray(this.pathPermissions, flags);
    }

    public ParsedProvider() {
    }

    protected ParsedProvider(Parcel in) {
        super(in);
        this.authority = in.readString();
        this.syncable = in.readBoolean();
        this.readPermission = ParsingPackageImpl.sForInternedString.unparcel(in);
        this.writePermission = ParsingPackageImpl.sForInternedString.unparcel(in);
        this.grantUriPermissions = in.readBoolean();
        this.forceUriPermissions = in.readBoolean();
        this.multiProcess = in.readBoolean();
        this.initOrder = in.readInt();
        this.uriPermissionPatterns = (PatternMatcher[]) in.createTypedArray(PatternMatcher.CREATOR);
        this.pathPermissions = (PathPermission[]) in.createTypedArray(PathPermission.CREATOR);
    }

    public String getAuthority() {
        return this.authority;
    }

    public boolean isSyncable() {
        return this.syncable;
    }

    public String getReadPermission() {
        return this.readPermission;
    }

    public String getWritePermission() {
        return this.writePermission;
    }

    public boolean isGrantUriPermissions() {
        return this.grantUriPermissions;
    }

    public boolean isForceUriPermissions() {
        return this.forceUriPermissions;
    }

    public boolean isMultiProcess() {
        return this.multiProcess;
    }

    public int getInitOrder() {
        return this.initOrder;
    }

    public PatternMatcher[] getUriPermissionPatterns() {
        return this.uriPermissionPatterns;
    }

    public PathPermission[] getPathPermissions() {
        return this.pathPermissions;
    }
}
