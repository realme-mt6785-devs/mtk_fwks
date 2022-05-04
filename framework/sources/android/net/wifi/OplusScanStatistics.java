package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class OplusScanStatistics implements Parcelable {
    public static final Parcelable.Creator<OplusScanStatistics> CREATOR = new Parcelable.Creator<OplusScanStatistics>() { // from class: android.net.wifi.OplusScanStatistics.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusScanStatistics createFromParcel(Parcel in) {
            OplusScanStatistics stat = new OplusScanStatistics();
            stat.packageName = in.readString();
            stat.packageUid = in.readInt();
            stat.requestTimestamp = in.readLong();
            stat.requestStatus = in.readBoolean();
            stat.resultTimestamp = in.readLong();
            stat.resultStatus = in.readBoolean();
            stat.resultSize = in.readInt();
            return stat;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusScanStatistics[] newArray(int size) {
            return new OplusScanStatistics[size];
        }
    };
    public String packageName;
    public int packageUid;
    public boolean requestStatus;
    public long requestTimestamp;
    public int resultSize;
    public boolean resultStatus;
    public long resultTimestamp;

    public OplusScanStatistics() {
        this.packageName = null;
        this.packageUid = -1;
        this.requestTimestamp = 0L;
        this.requestStatus = false;
        this.resultTimestamp = 0L;
        this.resultStatus = false;
        this.resultSize = 0;
    }

    public OplusScanStatistics(OplusScanStatistics source) {
        if (source != null) {
            this.packageName = source.packageName;
            this.packageUid = source.packageUid;
            this.requestTimestamp = source.requestTimestamp;
            this.requestStatus = source.requestStatus;
            this.resultTimestamp = source.resultTimestamp;
            this.resultStatus = source.resultStatus;
            this.resultSize = source.resultSize;
        }
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(" uid:");
        sbuf.append(this.packageUid);
        sbuf.append("  request time:");
        sbuf.append(this.requestTimestamp);
        sbuf.append("  request status:");
        sbuf.append(this.requestStatus);
        sbuf.append("  result time:");
        sbuf.append(this.resultTimestamp);
        sbuf.append("  result status:");
        sbuf.append(this.resultStatus);
        sbuf.append("  result size:");
        sbuf.append(this.resultSize);
        return sbuf.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageName);
        dest.writeInt(this.packageUid);
        dest.writeLong(this.requestTimestamp);
        dest.writeBoolean(this.requestStatus);
        dest.writeLong(this.resultTimestamp);
        dest.writeBoolean(this.resultStatus);
        dest.writeInt(this.resultSize);
    }
}
