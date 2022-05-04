package android.hardware.display;

import android.hardware.Camera;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public final class BrightnessInfo implements Parcelable {
    public static final Parcelable.Creator<BrightnessInfo> CREATOR = new Parcelable.Creator<BrightnessInfo>() { // from class: android.hardware.display.BrightnessInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BrightnessInfo createFromParcel(Parcel source) {
            return new BrightnessInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BrightnessInfo[] newArray(int size) {
            return new BrightnessInfo[size];
        }
    };
    public static final int HIGH_BRIGHTNESS_MODE_HDR = 2;
    public static final int HIGH_BRIGHTNESS_MODE_OFF = 0;
    public static final int HIGH_BRIGHTNESS_MODE_SUNLIGHT = 1;
    public final float brightness;
    public final float brightnessMaximum;
    public final float brightnessMinimum;
    public final int highBrightnessMode;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface HighBrightnessMode {
    }

    public BrightnessInfo(float brightness, float brightnessMinimum, float brightnessMaximum, int highBrightnessMode) {
        this.brightness = brightness;
        this.brightnessMinimum = brightnessMinimum;
        this.brightnessMaximum = brightnessMaximum;
        this.highBrightnessMode = highBrightnessMode;
    }

    public static String hbmToString(int highBrightnessMode) {
        switch (highBrightnessMode) {
            case 0:
                return "off";
            case 1:
                return "sunlight";
            case 2:
                return Camera.Parameters.SCENE_MODE_HDR;
            default:
                return "invalid";
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.brightness);
        dest.writeFloat(this.brightnessMinimum);
        dest.writeFloat(this.brightnessMaximum);
        dest.writeInt(this.highBrightnessMode);
    }

    private BrightnessInfo(Parcel source) {
        this.brightness = source.readFloat();
        this.brightnessMinimum = source.readFloat();
        this.brightnessMaximum = source.readFloat();
        this.highBrightnessMode = source.readInt();
    }
}
