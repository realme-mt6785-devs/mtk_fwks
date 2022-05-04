package android.accounts;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class AuthenticatorDescription implements Parcelable {
    public static final Parcelable.Creator<AuthenticatorDescription> CREATOR = new Parcelable.Creator<AuthenticatorDescription>() { // from class: android.accounts.AuthenticatorDescription.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthenticatorDescription createFromParcel(Parcel source) {
            return new AuthenticatorDescription(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthenticatorDescription[] newArray(int size) {
            return new AuthenticatorDescription[size];
        }
    };
    public final int accountPreferencesId;
    public final boolean customTokens;
    public final int iconId;
    public final int labelId;
    public final String packageName;
    public final int smallIconId;
    public final String type;

    public AuthenticatorDescription(String type, String packageName, int labelId, int iconId, int smallIconId, int prefId, boolean customTokens) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        } else if (packageName != null) {
            this.type = type;
            this.packageName = packageName;
            this.labelId = labelId;
            this.iconId = iconId;
            this.smallIconId = smallIconId;
            this.accountPreferencesId = prefId;
            this.customTokens = customTokens;
        } else {
            throw new IllegalArgumentException("packageName cannot be null");
        }
    }

    public AuthenticatorDescription(String type, String packageName, int labelId, int iconId, int smallIconId, int prefId) {
        this(type, packageName, labelId, iconId, smallIconId, prefId, false);
    }

    public static AuthenticatorDescription newKey(String type) {
        if (type != null) {
            return new AuthenticatorDescription(type);
        }
        throw new IllegalArgumentException("type cannot be null");
    }

    private AuthenticatorDescription(String type) {
        this.type = type;
        this.packageName = null;
        this.labelId = 0;
        this.iconId = 0;
        this.smallIconId = 0;
        this.accountPreferencesId = 0;
        this.customTokens = false;
    }

    private AuthenticatorDescription(Parcel source) {
        this.type = source.readString();
        this.packageName = source.readString();
        this.labelId = source.readInt();
        this.iconId = source.readInt();
        this.smallIconId = source.readInt();
        this.accountPreferencesId = source.readInt();
        this.customTokens = source.readByte() != 1 ? false : true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuthenticatorDescription)) {
            return false;
        }
        AuthenticatorDescription other = (AuthenticatorDescription) o;
        return this.type.equals(other.type);
    }

    public String toString() {
        return "AuthenticatorDescription {type=" + this.type + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.packageName);
        dest.writeInt(this.labelId);
        dest.writeInt(this.iconId);
        dest.writeInt(this.smallIconId);
        dest.writeInt(this.accountPreferencesId);
        dest.writeByte(this.customTokens ? (byte) 1 : (byte) 0);
    }
}
