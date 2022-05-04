package android.hardware.fingerprint;

import android.hardware.biometrics.BiometricAuthenticator;
import android.os.Parcel;
import android.os.Parcelable;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes.dex */
public final class Fingerprint extends BiometricAuthenticator.Identifier {
    public static final Parcelable.Creator<Fingerprint> CREATOR = new Parcelable.Creator<Fingerprint>() { // from class: android.hardware.fingerprint.Fingerprint.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Fingerprint createFromParcel(Parcel in) {
            return new Fingerprint(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Fingerprint[] newArray(int size) {
            return new Fingerprint[size];
        }
    };
    public IFingerprintExt mFingerprintExt;
    private int mGroupId;

    public Fingerprint(CharSequence name, int groupId, int fingerId, long deviceId) {
        super(name, fingerId, deviceId);
        IFingerprintExt iFingerprintExt = (IFingerprintExt) ExtLoader.type(IFingerprintExt.class).base(this).create();
        this.mFingerprintExt = iFingerprintExt;
        this.mGroupId = groupId;
        iFingerprintExt.init(groupId);
    }

    public Fingerprint(CharSequence name, int fingerId, long deviceId) {
        super(name, fingerId, deviceId);
        IFingerprintExt iFingerprintExt = (IFingerprintExt) ExtLoader.type(IFingerprintExt.class).base(this).create();
        this.mFingerprintExt = iFingerprintExt;
        iFingerprintExt.init(-1);
    }

    private Fingerprint(Parcel in) {
        super(in.readString(), in.readInt(), in.readLong());
        this.mFingerprintExt = (IFingerprintExt) ExtLoader.type(IFingerprintExt.class).base(this).create();
        int readInt = in.readInt();
        this.mGroupId = readInt;
        this.mFingerprintExt.init(readInt);
        this.mFingerprintExt.readFromParcel(in);
    }

    public int getGroupId() {
        return this.mGroupId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getName().toString());
        out.writeInt(getBiometricId());
        out.writeLong(getDeviceId());
        out.writeInt(this.mGroupId);
        this.mFingerprintExt.writeToParcel(out, flags);
    }
}
