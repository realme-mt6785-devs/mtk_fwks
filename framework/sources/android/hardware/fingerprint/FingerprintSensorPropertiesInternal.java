package android.hardware.fingerprint;

import android.hardware.biometrics.ComponentInfoInternal;
import android.hardware.biometrics.SensorPropertiesInternal;
import android.hardware.fingerprint.util.OplusFingerprintSupportUtilsExtPlugin;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.logging.nano.MetricsProto;
import java.util.List;
/* loaded from: classes.dex */
public class FingerprintSensorPropertiesInternal extends SensorPropertiesInternal {
    public static final Parcelable.Creator<FingerprintSensorPropertiesInternal> CREATOR = new Parcelable.Creator<FingerprintSensorPropertiesInternal>() { // from class: android.hardware.fingerprint.FingerprintSensorPropertiesInternal.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FingerprintSensorPropertiesInternal createFromParcel(Parcel in) {
            return new FingerprintSensorPropertiesInternal(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FingerprintSensorPropertiesInternal[] newArray(int size) {
            return new FingerprintSensorPropertiesInternal[size];
        }
    };
    public final int sensorLocationX;
    public final int sensorLocationY;
    public final int sensorRadius;
    public final int sensorType;

    public FingerprintSensorPropertiesInternal(int sensorId, int strength, int maxEnrollmentsPerUser, List<ComponentInfoInternal> componentInfo, int sensorType, boolean resetLockoutRequiresHardwareAuthToken, int sensorLocationX, int sensorLocationY, int sensorRadius) {
        super(sensorId, strength, maxEnrollmentsPerUser, componentInfo, resetLockoutRequiresHardwareAuthToken, false);
        int sensorType2 = OplusFingerprintSupportUtilsExtPlugin.getSupportSensorType.call(new Object[0]).intValue();
        this.sensorType = sensorType2;
        this.sensorLocationX = sensorLocationX;
        this.sensorLocationY = sensorLocationY;
        this.sensorRadius = sensorRadius;
    }

    public FingerprintSensorPropertiesInternal(int sensorId, int strength, int maxEnrollmentsPerUser, List<ComponentInfoInternal> componentInfo, int sensorType, boolean resetLockoutRequiresHardwareAuthToken) {
        this(sensorId, strength, maxEnrollmentsPerUser, componentInfo, sensorType, resetLockoutRequiresHardwareAuthToken, 540, MetricsProto.MetricsEvent.FIELD_TEXT_CLASSIFIER_SECOND_ENTITY_TYPE, 130);
    }

    protected FingerprintSensorPropertiesInternal(Parcel in) {
        super(in);
        this.sensorType = in.readInt();
        this.sensorLocationX = in.readInt();
        this.sensorLocationY = in.readInt();
        this.sensorRadius = in.readInt();
    }

    @Override // android.hardware.biometrics.SensorPropertiesInternal, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.hardware.biometrics.SensorPropertiesInternal, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.sensorType);
        dest.writeInt(this.sensorLocationX);
        dest.writeInt(this.sensorLocationY);
        dest.writeInt(this.sensorRadius);
    }

    public boolean isAnyUdfpsType() {
        switch (this.sensorType) {
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    public boolean isAnySidefpsType() {
        switch (this.sensorType) {
            case 4:
                return true;
            default:
                return false;
        }
    }

    @Override // android.hardware.biometrics.SensorPropertiesInternal
    public String toString() {
        return "ID: " + this.sensorId + ", Strength: " + this.sensorStrength + ", Type: " + this.sensorType;
    }
}
