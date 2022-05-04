package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class InstallSourceInfo implements Parcelable {
    public static final Parcelable.Creator<InstallSourceInfo> CREATOR = new Parcelable.Creator<InstallSourceInfo>() { // from class: android.content.pm.InstallSourceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallSourceInfo createFromParcel(Parcel source) {
            return new InstallSourceInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallSourceInfo[] newArray(int size) {
            return new InstallSourceInfo[size];
        }
    };
    private final String mInitiatingPackageName;
    private final SigningInfo mInitiatingPackageSigningInfo;
    private final String mInstallingPackageName;
    private final String mOriginatingPackageName;

    public InstallSourceInfo(String initiatingPackageName, SigningInfo initiatingPackageSigningInfo, String originatingPackageName, String installingPackageName) {
        this.mInitiatingPackageName = initiatingPackageName;
        this.mInitiatingPackageSigningInfo = initiatingPackageSigningInfo;
        this.mOriginatingPackageName = originatingPackageName;
        this.mInstallingPackageName = installingPackageName;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        SigningInfo signingInfo = this.mInitiatingPackageSigningInfo;
        if (signingInfo == null) {
            return 0;
        }
        return signingInfo.describeContents();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mInitiatingPackageName);
        dest.writeParcelable(this.mInitiatingPackageSigningInfo, flags);
        dest.writeString(this.mOriginatingPackageName);
        dest.writeString(this.mInstallingPackageName);
    }

    private InstallSourceInfo(Parcel source) {
        this.mInitiatingPackageName = source.readString();
        this.mInitiatingPackageSigningInfo = (SigningInfo) source.readParcelable(SigningInfo.class.getClassLoader());
        this.mOriginatingPackageName = source.readString();
        this.mInstallingPackageName = source.readString();
    }

    public String getInitiatingPackageName() {
        return this.mInitiatingPackageName;
    }

    public SigningInfo getInitiatingPackageSigningInfo() {
        return this.mInitiatingPackageSigningInfo;
    }

    public String getOriginatingPackageName() {
        return this.mOriginatingPackageName;
    }

    public String getInstallingPackageName() {
        return this.mInstallingPackageName;
    }
}
