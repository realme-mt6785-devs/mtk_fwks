package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class WifiDisplaySessionInfo implements Parcelable {
    public static final Parcelable.Creator<WifiDisplaySessionInfo> CREATOR = new Parcelable.Creator<WifiDisplaySessionInfo>() { // from class: android.hardware.display.WifiDisplaySessionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WifiDisplaySessionInfo createFromParcel(Parcel in) {
            boolean client = in.readInt() != 0;
            int session = in.readInt();
            String group = in.readString();
            String pp = in.readString();
            String ip = in.readString();
            return new WifiDisplaySessionInfo(client, session, group, pp, ip);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WifiDisplaySessionInfo[] newArray(int size) {
            return new WifiDisplaySessionInfo[size];
        }
    };
    private final boolean mClient;
    private final String mGroupId;
    private final String mIP;
    private final String mPassphrase;
    private final int mSessionId;

    public WifiDisplaySessionInfo() {
        this(true, 0, "", "", "");
    }

    public WifiDisplaySessionInfo(boolean client, int session, String group, String pp, String ip) {
        this.mClient = client;
        this.mSessionId = session;
        this.mGroupId = group;
        this.mPassphrase = pp;
        this.mIP = ip;
    }

    public boolean isClient() {
        return this.mClient;
    }

    public int getSessionId() {
        return this.mSessionId;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public String getPassphrase() {
        return this.mPassphrase;
    }

    public String getIP() {
        return this.mIP;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mClient ? 1 : 0);
        dest.writeInt(this.mSessionId);
        dest.writeString(this.mGroupId);
        dest.writeString(this.mPassphrase);
        dest.writeString(this.mIP);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WifiDisplaySessionInfo:\n    Client/Owner: ");
        sb.append(this.mClient ? "Client" : "Owner");
        sb.append("\n    GroupId: ");
        sb.append(this.mGroupId);
        sb.append("\n    Passphrase: ");
        sb.append(this.mPassphrase);
        sb.append("\n    SessionId: ");
        sb.append(this.mSessionId);
        sb.append("\n    IP Address: ");
        sb.append(this.mIP);
        return sb.toString();
    }
}
