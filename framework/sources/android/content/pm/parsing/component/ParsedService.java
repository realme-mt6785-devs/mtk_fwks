package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
/* loaded from: classes.dex */
public class ParsedService extends ParsedMainComponent {
    public static final Parcelable.Creator<ParsedService> CREATOR = new Parcelable.Creator<ParsedService>() { // from class: android.content.pm.parsing.component.ParsedService.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedService createFromParcel(Parcel source) {
            return new ParsedService(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedService[] newArray(int size) {
            return new ParsedService[size];
        }
    };
    int foregroundServiceType;
    private String permission;

    public ParsedService(ParsedService other) {
        super(other);
        this.foregroundServiceType = other.foregroundServiceType;
        this.permission = other.permission;
    }

    public ParsedMainComponent setPermission(String permission) {
        this.permission = TextUtils.isEmpty(permission) ? null : permission.intern();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Service{");
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
        dest.writeInt(this.foregroundServiceType);
        ParsingPackageImpl.sForInternedString.parcel(this.permission, dest, flags);
    }

    public ParsedService() {
    }

    protected ParsedService(Parcel in) {
        super(in);
        this.foregroundServiceType = in.readInt();
        this.permission = ParsingPackageImpl.sForInternedString.unparcel(in);
    }

    public int getForegroundServiceType() {
        return this.foregroundServiceType;
    }

    public String getPermission() {
        return this.permission;
    }
}
