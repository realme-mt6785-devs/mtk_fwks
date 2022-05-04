package android.window;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public final class DisplayAreaInfo implements Parcelable {
    public static final Parcelable.Creator<DisplayAreaInfo> CREATOR = new Parcelable.Creator<DisplayAreaInfo>() { // from class: android.window.DisplayAreaInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayAreaInfo createFromParcel(Parcel in) {
            return new DisplayAreaInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayAreaInfo[] newArray(int size) {
            return new DisplayAreaInfo[size];
        }
    };
    public final Configuration configuration;
    public final int displayId;
    public final int featureId;
    public final WindowContainerToken token;

    public DisplayAreaInfo(WindowContainerToken token, int displayId, int featureId) {
        this.configuration = new Configuration();
        this.token = token;
        this.displayId = displayId;
        this.featureId = featureId;
    }

    private DisplayAreaInfo(Parcel in) {
        Configuration configuration = new Configuration();
        this.configuration = configuration;
        this.token = WindowContainerToken.CREATOR.createFromParcel(in);
        configuration.readFromParcel(in);
        this.displayId = in.readInt();
        this.featureId = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        this.token.writeToParcel(dest, flags);
        this.configuration.writeToParcel(dest, flags);
        dest.writeInt(this.displayId);
        dest.writeInt(this.featureId);
    }

    public String toString() {
        return "DisplayAreaInfo{token=" + this.token + " config=" + this.configuration + "}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
