package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class ParsedUsesPermission implements Parcelable {
    public static final Parcelable.Creator<ParsedUsesPermission> CREATOR = new Parcelable.Creator<ParsedUsesPermission>() { // from class: android.content.pm.parsing.component.ParsedUsesPermission.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedUsesPermission[] newArray(int size) {
            return new ParsedUsesPermission[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedUsesPermission createFromParcel(Parcel in) {
            return new ParsedUsesPermission(in);
        }
    };
    public static final int FLAG_NEVER_FOR_LOCATION = 65536;
    public String name;
    public int usesPermissionFlags;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface UsesPermissionFlags {
    }

    public ParsedUsesPermission(String name, int usesPermissionFlags) {
        this.name = name.intern();
        this.usesPermissionFlags = usesPermissionFlags;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ParsingPackageImpl.sForInternedString.parcel(this.name, dest, flags);
        dest.writeInt(this.usesPermissionFlags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ParsedUsesPermission(Parcel in) {
        this.name = ParsingPackageImpl.sForInternedString.unparcel(in);
        this.usesPermissionFlags = in.readInt();
    }
}
