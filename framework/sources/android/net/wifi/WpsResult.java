package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class WpsResult implements Parcelable {
    public static final Parcelable.Creator<WpsResult> CREATOR = new Parcelable.Creator<WpsResult>() { // from class: android.net.wifi.WpsResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WpsResult createFromParcel(Parcel in) {
            WpsResult result = new WpsResult();
            result.status = Status.valueOf(in.readString());
            result.pin = in.readString();
            return result;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WpsResult[] newArray(int size) {
            return new WpsResult[size];
        }
    };
    public String pin;
    public Status status;

    /* loaded from: classes2.dex */
    public enum Status {
        SUCCESS,
        FAILURE,
        IN_PROGRESS
    }

    public WpsResult() {
        this.status = Status.FAILURE;
        this.pin = null;
    }

    public WpsResult(Status s) {
        this.status = s;
        this.pin = null;
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(" status: ");
        sbuf.append(this.status.toString());
        sbuf.append('\n');
        sbuf.append(" pin: ");
        sbuf.append(this.pin);
        sbuf.append("\n");
        return sbuf.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WpsResult(WpsResult source) {
        if (source != null) {
            this.status = source.status;
            this.pin = source.pin;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status.name());
        dest.writeString(this.pin);
    }
}
