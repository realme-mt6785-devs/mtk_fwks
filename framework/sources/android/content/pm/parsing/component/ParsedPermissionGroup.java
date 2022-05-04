package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class ParsedPermissionGroup extends ParsedComponent {
    public static final Parcelable.Creator<ParsedPermissionGroup> CREATOR = new Parcelable.Creator<ParsedPermissionGroup>() { // from class: android.content.pm.parsing.component.ParsedPermissionGroup.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermissionGroup createFromParcel(Parcel source) {
            return new ParsedPermissionGroup(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedPermissionGroup[] newArray(int size) {
            return new ParsedPermissionGroup[size];
        }
    };
    int backgroundRequestDetailResourceId;
    int backgroundRequestResourceId;
    int priority;
    int requestDetailResourceId;
    int requestRes;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return "PermissionGroup{" + Integer.toHexString(System.identityHashCode(this)) + " " + getName() + "}";
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.requestDetailResourceId);
        dest.writeInt(this.backgroundRequestResourceId);
        dest.writeInt(this.backgroundRequestDetailResourceId);
        dest.writeInt(this.requestRes);
        dest.writeInt(this.priority);
    }

    public ParsedPermissionGroup() {
    }

    protected ParsedPermissionGroup(Parcel in) {
        super(in);
        this.requestDetailResourceId = in.readInt();
        this.backgroundRequestResourceId = in.readInt();
        this.backgroundRequestDetailResourceId = in.readInt();
        this.requestRes = in.readInt();
        this.priority = in.readInt();
    }

    public int getRequestDetailResourceId() {
        return this.requestDetailResourceId;
    }

    public int getBackgroundRequestResourceId() {
        return this.backgroundRequestResourceId;
    }

    public int getBackgroundRequestDetailResourceId() {
        return this.backgroundRequestDetailResourceId;
    }

    public int getRequestRes() {
        return this.requestRes;
    }

    public int getPriority() {
        return this.priority;
    }
}
