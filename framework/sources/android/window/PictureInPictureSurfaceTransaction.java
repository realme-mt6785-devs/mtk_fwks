package android.window;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.SurfaceControl;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class PictureInPictureSurfaceTransaction implements Parcelable {
    public static final Parcelable.Creator<PictureInPictureSurfaceTransaction> CREATOR = new Parcelable.Creator<PictureInPictureSurfaceTransaction>() { // from class: android.window.PictureInPictureSurfaceTransaction.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PictureInPictureSurfaceTransaction createFromParcel(Parcel in) {
            return new PictureInPictureSurfaceTransaction(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PictureInPictureSurfaceTransaction[] newArray(int size) {
            return new PictureInPictureSurfaceTransaction[size];
        }
    };
    public final float mCornerRadius;
    public final float[] mFloat9;
    public final float mPositionX;
    public final float mPositionY;
    public final float mRotation;
    private final Rect mWindowCrop;

    public PictureInPictureSurfaceTransaction(Parcel in) {
        Rect rect = new Rect();
        this.mWindowCrop = rect;
        this.mPositionX = in.readFloat();
        this.mPositionY = in.readFloat();
        float[] fArr = new float[9];
        this.mFloat9 = fArr;
        in.readFloatArray(fArr);
        this.mRotation = in.readFloat();
        this.mCornerRadius = in.readFloat();
        Rect rect2 = (Rect) in.readTypedObject(Rect.CREATOR);
        Objects.requireNonNull(rect2);
        rect.set(rect2);
    }

    public PictureInPictureSurfaceTransaction(float positionX, float positionY, float[] float9, float rotation, float cornerRadius, Rect windowCrop) {
        Rect rect = new Rect();
        this.mWindowCrop = rect;
        this.mPositionX = positionX;
        this.mPositionY = positionY;
        this.mFloat9 = Arrays.copyOf(float9, 9);
        this.mRotation = rotation;
        this.mCornerRadius = cornerRadius;
        if (windowCrop != null) {
            rect.set(windowCrop);
        }
    }

    public PictureInPictureSurfaceTransaction(PictureInPictureSurfaceTransaction other) {
        this(other.mPositionX, other.mPositionY, other.mFloat9, other.mRotation, other.mCornerRadius, other.mWindowCrop);
    }

    public Matrix getMatrix() {
        Matrix matrix = new Matrix();
        matrix.setValues(this.mFloat9);
        return matrix;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PictureInPictureSurfaceTransaction)) {
            return false;
        }
        PictureInPictureSurfaceTransaction that = (PictureInPictureSurfaceTransaction) o;
        return Objects.equals(Float.valueOf(this.mPositionX), Float.valueOf(that.mPositionX)) && Objects.equals(Float.valueOf(this.mPositionY), Float.valueOf(that.mPositionY)) && Arrays.equals(this.mFloat9, that.mFloat9) && Objects.equals(Float.valueOf(this.mRotation), Float.valueOf(that.mRotation)) && Objects.equals(Float.valueOf(this.mCornerRadius), Float.valueOf(that.mCornerRadius)) && Objects.equals(this.mWindowCrop, that.mWindowCrop);
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(this.mPositionX), Float.valueOf(this.mPositionY), Integer.valueOf(Arrays.hashCode(this.mFloat9)), Float.valueOf(this.mRotation), Float.valueOf(this.mCornerRadius), this.mWindowCrop);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeFloat(this.mPositionX);
        out.writeFloat(this.mPositionY);
        out.writeFloatArray(this.mFloat9);
        out.writeFloat(this.mRotation);
        out.writeFloat(this.mCornerRadius);
        out.writeTypedObject(this.mWindowCrop, 0);
    }

    public String toString() {
        Matrix matrix = getMatrix();
        return "PictureInPictureSurfaceTransaction( posX=" + this.mPositionX + " posY=" + this.mPositionY + " matrix=" + matrix.toShortString() + " rotation=" + this.mRotation + " cornerRadius=" + this.mCornerRadius + " crop=" + this.mWindowCrop + ")";
    }

    public static void apply(PictureInPictureSurfaceTransaction surfaceTransaction, SurfaceControl surfaceControl, SurfaceControl.Transaction tx) {
        Matrix matrix = surfaceTransaction.getMatrix();
        tx.setMatrix(surfaceControl, matrix, new float[9]).setPosition(surfaceControl, surfaceTransaction.mPositionX, surfaceTransaction.mPositionY).setWindowCrop(surfaceControl, surfaceTransaction.mWindowCrop).setCornerRadius(surfaceControl, surfaceTransaction.mCornerRadius);
    }
}
