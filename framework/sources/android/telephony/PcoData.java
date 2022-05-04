package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public class PcoData implements Parcelable {
    public static final Parcelable.Creator<PcoData> CREATOR = new Parcelable.Creator() { // from class: android.telephony.PcoData.1
        @Override // android.os.Parcelable.Creator
        public PcoData createFromParcel(Parcel in) {
            return new PcoData(in);
        }

        @Override // android.os.Parcelable.Creator
        public PcoData[] newArray(int size) {
            return new PcoData[size];
        }
    };
    public final String bearerProto;
    public final int cid;
    public final byte[] contents;
    public final int pcoId;

    public PcoData(int cid, String bearerProto, int pcoId, byte[] contents) {
        this.cid = cid;
        this.bearerProto = bearerProto;
        this.pcoId = pcoId;
        this.contents = contents;
    }

    public PcoData(Parcel in) {
        this.cid = in.readInt();
        this.bearerProto = in.readString();
        this.pcoId = in.readInt();
        this.contents = in.createByteArray();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.cid);
        out.writeString(this.bearerProto);
        out.writeInt(this.pcoId);
        out.writeByteArray(this.contents);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PcoData(" + this.cid + ", " + this.bearerProto + ", " + this.pcoId + ", contents[" + this.contents.length + "])";
    }
}
