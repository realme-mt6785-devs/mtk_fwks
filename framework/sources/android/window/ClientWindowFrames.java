package android.window;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public class ClientWindowFrames implements Parcelable {
    public static final Parcelable.Creator<ClientWindowFrames> CREATOR = new Parcelable.Creator<ClientWindowFrames>() { // from class: android.window.ClientWindowFrames.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClientWindowFrames createFromParcel(Parcel in) {
            return new ClientWindowFrames(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClientWindowFrames[] newArray(int size) {
            return new ClientWindowFrames[size];
        }
    };
    public final Rect backdropFrame;
    public final Rect displayFrame;
    public final Rect frame;

    public ClientWindowFrames() {
        this.frame = new Rect();
        this.displayFrame = new Rect();
        this.backdropFrame = new Rect();
    }

    public ClientWindowFrames(ClientWindowFrames other) {
        this.frame = new Rect(other.frame);
        this.displayFrame = new Rect(other.displayFrame);
        this.backdropFrame = new Rect(other.backdropFrame);
    }

    private ClientWindowFrames(Parcel in) {
        this.frame = Rect.CREATOR.createFromParcel(in);
        this.displayFrame = Rect.CREATOR.createFromParcel(in);
        this.backdropFrame = Rect.CREATOR.createFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.frame.readFromParcel(in);
        this.displayFrame.readFromParcel(in);
        this.backdropFrame.readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        this.frame.writeToParcel(dest, flags);
        this.displayFrame.writeToParcel(dest, flags);
        this.backdropFrame.writeToParcel(dest, flags);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        return "ClientWindowFrames{frame=" + this.frame.toShortString(sb) + " display=" + this.displayFrame.toShortString(sb) + " backdrop=" + this.backdropFrame.toShortString(sb) + "}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
