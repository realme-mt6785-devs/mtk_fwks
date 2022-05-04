package android.content.pm.verify.domain;

import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.Objects;
@SystemApi
/* loaded from: classes.dex */
public final class DomainOwner implements Parcelable {
    public static final Parcelable.Creator<DomainOwner> CREATOR = new Parcelable.Creator<DomainOwner>() { // from class: android.content.pm.verify.domain.DomainOwner.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainOwner[] newArray(int size) {
            return new DomainOwner[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainOwner createFromParcel(Parcel in) {
            return new DomainOwner(in);
        }
    };
    private final boolean mOverrideable;
    private final String mPackageName;

    public DomainOwner(String packageName, boolean overrideable) {
        this.mPackageName = packageName;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) packageName);
        this.mOverrideable = overrideable;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public boolean isOverrideable() {
        return this.mOverrideable;
    }

    public String toString() {
        return "DomainOwner { packageName = " + this.mPackageName + ", overrideable = " + this.mOverrideable + " }";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DomainOwner that = (DomainOwner) o;
        if (!Objects.equals(this.mPackageName, that.mPackageName) || this.mOverrideable != that.mOverrideable) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int _hash = (1 * 31) + Objects.hashCode(this.mPackageName);
        return (_hash * 31) + Boolean.hashCode(this.mOverrideable);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        byte flg = 0;
        if (this.mOverrideable) {
            flg = (byte) (0 | 2);
        }
        dest.writeByte(flg);
        dest.writeString(this.mPackageName);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    DomainOwner(Parcel in) {
        byte flg = in.readByte();
        boolean overrideable = (flg & 2) != 0;
        String packageName = in.readString();
        this.mPackageName = packageName;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) packageName);
        this.mOverrideable = overrideable;
    }

    @Deprecated
    private void __metadata() {
    }
}
