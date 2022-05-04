package android.hardware.camera2.params;

import java.util.Objects;
/* loaded from: classes.dex */
public class MultiResolutionStreamInfo {
    private String mPhysicalCameraId;
    private int mStreamHeight;
    private int mStreamWidth;

    public MultiResolutionStreamInfo(int streamWidth, int streamHeight, String physicalCameraId) {
        if (streamWidth <= 0) {
            throw new IllegalArgumentException("Invalid stream width " + streamWidth);
        } else if (streamHeight > 0) {
            this.mStreamWidth = streamWidth;
            this.mStreamHeight = streamHeight;
            this.mPhysicalCameraId = physicalCameraId;
        } else {
            throw new IllegalArgumentException("Invalid stream height " + streamHeight);
        }
    }

    public int getWidth() {
        return this.mStreamWidth;
    }

    public int getHeight() {
        return this.mStreamHeight;
    }

    public String getPhysicalCameraId() {
        return this.mPhysicalCameraId;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MultiResolutionStreamInfo)) {
            return false;
        }
        MultiResolutionStreamInfo other = (MultiResolutionStreamInfo) obj;
        if (this.mStreamWidth == other.mStreamWidth && this.mStreamHeight == other.mStreamHeight && this.mPhysicalCameraId.equals(other.mPhysicalCameraId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mStreamWidth), Integer.valueOf(this.mStreamHeight), this.mPhysicalCameraId);
    }
}
