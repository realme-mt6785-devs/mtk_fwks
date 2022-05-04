package android.uwb;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class SessionHandle implements Parcelable {
    public static final Parcelable.Creator<SessionHandle> CREATOR = new Parcelable.Creator<SessionHandle>() { // from class: android.uwb.SessionHandle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionHandle createFromParcel(Parcel in) {
            return new SessionHandle(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SessionHandle[] newArray(int size) {
            return new SessionHandle[size];
        }
    };
    private final int mId;

    public SessionHandle(int id) {
        this.mId = id;
    }

    protected SessionHandle(Parcel in) {
        this.mId = in.readInt();
    }

    public int getId() {
        return this.mId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionHandle)) {
            return false;
        }
        SessionHandle other = (SessionHandle) obj;
        return this.mId == other.mId;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mId));
    }

    public String toString() {
        return "SessionHandle [id=" + this.mId + "]";
    }
}
