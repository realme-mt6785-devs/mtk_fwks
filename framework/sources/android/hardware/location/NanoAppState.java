package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
@SystemApi
/* loaded from: classes2.dex */
public final class NanoAppState implements Parcelable {
    public static final Parcelable.Creator<NanoAppState> CREATOR = new Parcelable.Creator<NanoAppState>() { // from class: android.hardware.location.NanoAppState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NanoAppState createFromParcel(Parcel in) {
            return new NanoAppState(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NanoAppState[] newArray(int size) {
            return new NanoAppState[size];
        }
    };
    private boolean mIsEnabled;
    private long mNanoAppId;
    private List<String> mNanoAppPermissions;
    private int mNanoAppVersion;

    public NanoAppState(long nanoAppId, int appVersion, boolean enabled) {
        this.mNanoAppId = nanoAppId;
        this.mNanoAppVersion = appVersion;
        this.mIsEnabled = enabled;
        this.mNanoAppPermissions = new ArrayList();
    }

    public NanoAppState(long nanoAppId, int appVersion, boolean enabled, List<String> nanoAppPermissions) {
        this.mNanoAppId = nanoAppId;
        this.mNanoAppVersion = appVersion;
        this.mIsEnabled = enabled;
        this.mNanoAppPermissions = nanoAppPermissions;
    }

    public long getNanoAppId() {
        return this.mNanoAppId;
    }

    public long getNanoAppVersion() {
        return this.mNanoAppVersion;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public List<String> getNanoAppPermissions() {
        return this.mNanoAppPermissions;
    }

    private NanoAppState(Parcel in) {
        this.mNanoAppId = in.readLong();
        this.mNanoAppVersion = in.readInt();
        this.mIsEnabled = in.readInt() != 1 ? false : true;
        ArrayList arrayList = new ArrayList();
        this.mNanoAppPermissions = arrayList;
        in.readStringList(arrayList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(this.mNanoAppId);
        out.writeInt(this.mNanoAppVersion);
        out.writeInt(this.mIsEnabled ? 1 : 0);
        out.writeStringList(this.mNanoAppPermissions);
    }
}
