package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.internal.util.Parcelling;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public class ParsedPermission extends ParsedComponent {
    String backgroundPermission;
    private String group;
    Set<String> knownCerts;
    private ParsedPermissionGroup parsedPermissionGroup;
    int protectionLevel;
    int requestRes;
    boolean tree;
    private static Parcelling.BuiltIn.ForStringSet sForStringSet = (Parcelling.BuiltIn.ForStringSet) Parcelling.Cache.getOrCreate(Parcelling.BuiltIn.ForStringSet.class);
    public static final Parcelable.Creator<ParsedPermission> CREATOR = new Parcelable.Creator<ParsedPermission>() { // from class: android.content.pm.parsing.component.ParsedPermission.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermission createFromParcel(Parcel source) {
            return new ParsedPermission(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermission[] newArray(int size) {
            return new ParsedPermission[size];
        }
    };

    public ParsedPermission() {
    }

    public ParsedPermission(ParsedPermission other) {
        super(other);
        this.backgroundPermission = other.backgroundPermission;
        this.group = other.group;
        this.requestRes = other.requestRes;
        this.protectionLevel = other.protectionLevel;
        this.tree = other.tree;
        this.parsedPermissionGroup = other.parsedPermissionGroup;
    }

    public ParsedPermission setGroup(String group) {
        this.group = TextUtils.safeIntern(group);
        return this;
    }

    public ParsedPermission setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public boolean isRuntime() {
        return getProtection() == 1;
    }

    public boolean isAppOp() {
        return (this.protectionLevel & 64) != 0;
    }

    public int getProtection() {
        return this.protectionLevel & 15;
    }

    public int getProtectionFlags() {
        return this.protectionLevel & (-16);
    }

    public Set<String> getKnownCerts() {
        return this.knownCerts;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setKnownCert(String knownCert) {
        this.knownCerts = Set.of(knownCert.toUpperCase(Locale.US));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setKnownCerts(String[] knownCerts) {
        this.knownCerts = new ArraySet();
        for (String knownCert : knownCerts) {
            this.knownCerts.add(knownCert.toUpperCase(Locale.US));
        }
    }

    public int calculateFootprint() {
        int size = getName().length();
        if (getNonLocalizedLabel() != null) {
            return size + getNonLocalizedLabel().length();
        }
        return size;
    }

    public String toString() {
        return "Permission{" + Integer.toHexString(System.identityHashCode(this)) + " " + getName() + "}";
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.backgroundPermission);
        dest.writeString(this.group);
        dest.writeInt(this.requestRes);
        dest.writeInt(this.protectionLevel);
        dest.writeBoolean(this.tree);
        dest.writeParcelable(this.parsedPermissionGroup, flags);
        sForStringSet.parcel(this.knownCerts, dest, flags);
    }

    protected ParsedPermission(Parcel in) {
        super(in);
        ClassLoader boot = Object.class.getClassLoader();
        this.backgroundPermission = in.readString();
        this.group = in.readString();
        this.requestRes = in.readInt();
        this.protectionLevel = in.readInt();
        this.tree = in.readBoolean();
        this.parsedPermissionGroup = (ParsedPermissionGroup) in.readParcelable(boot);
        this.knownCerts = sForStringSet.unparcel(in);
    }

    public String getBackgroundPermission() {
        return this.backgroundPermission;
    }

    public String getGroup() {
        return this.group;
    }

    public int getRequestRes() {
        return this.requestRes;
    }

    public int getProtectionLevel() {
        return this.protectionLevel;
    }

    public boolean isTree() {
        return this.tree;
    }

    public ParsedPermissionGroup getParsedPermissionGroup() {
        return this.parsedPermissionGroup;
    }

    public ParsedPermission setProtectionLevel(int value) {
        this.protectionLevel = value;
        return this;
    }

    public ParsedPermission setParsedPermissionGroup(ParsedPermissionGroup value) {
        this.parsedPermissionGroup = value;
        return this;
    }
}
