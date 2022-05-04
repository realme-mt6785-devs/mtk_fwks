package android.view;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public class MagnificationSpec implements Parcelable {
    public static final Parcelable.Creator<MagnificationSpec> CREATOR = new Parcelable.Creator<MagnificationSpec>() { // from class: android.view.MagnificationSpec.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MagnificationSpec[] newArray(int size) {
            return new MagnificationSpec[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MagnificationSpec createFromParcel(Parcel parcel) {
            MagnificationSpec spec = new MagnificationSpec();
            spec.initFromParcel(parcel);
            return spec;
        }
    };
    public float offsetX;
    public float offsetY;
    public float scale = 1.0f;

    public void initialize(float scale, float offsetX, float offsetY) {
        if (scale >= 1.0f) {
            this.scale = scale;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            return;
        }
        throw new IllegalArgumentException("Scale must be greater than or equal to one!");
    }

    public boolean isNop() {
        return this.scale == 1.0f && this.offsetX == 0.0f && this.offsetY == 0.0f;
    }

    public void clear() {
        this.scale = 1.0f;
        this.offsetX = 0.0f;
        this.offsetY = 0.0f;
    }

    public void setTo(MagnificationSpec other) {
        this.scale = other.scale;
        this.offsetX = other.offsetX;
        this.offsetY = other.offsetY;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeFloat(this.scale);
        parcel.writeFloat(this.offsetX);
        parcel.writeFloat(this.offsetY);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        MagnificationSpec s = (MagnificationSpec) other;
        if (this.scale == s.scale && this.offsetX == s.offsetX && this.offsetY == s.offsetY) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        float f = this.scale;
        int i = 0;
        int result = f != 0.0f ? Float.floatToIntBits(f) : 0;
        int i2 = result * 31;
        float f2 = this.offsetX;
        int result2 = i2 + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
        int result3 = result2 * 31;
        float f3 = this.offsetY;
        if (f3 != 0.0f) {
            i = Float.floatToIntBits(f3);
        }
        return result3 + i;
    }

    public String toString() {
        return "<scale:" + Float.toString(this.scale) + ",offsetX:" + Float.toString(this.offsetX) + ",offsetY:" + Float.toString(this.offsetY) + ">";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFromParcel(Parcel parcel) {
        this.scale = parcel.readFloat();
        this.offsetX = parcel.readFloat();
        this.offsetY = parcel.readFloat();
    }
}
