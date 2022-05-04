package android.mtp;

import android.hardware.camera2.impl.CameraDeviceImpl$9$$ExternalSyntheticLambda0;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
class MtpPropertyList {
    private int mCode;
    private List<Integer> mObjectHandles = new ArrayList();
    private List<Integer> mPropertyCodes = new ArrayList();
    private List<Integer> mDataTypes = new ArrayList();
    private List<Long> mLongValues = new ArrayList();
    private List<String> mStringValues = new ArrayList();

    public MtpPropertyList(int code) {
        this.mCode = code;
    }

    public void append(int handle, int property, int type, long value) {
        this.mObjectHandles.add(Integer.valueOf(handle));
        this.mPropertyCodes.add(Integer.valueOf(property));
        this.mDataTypes.add(Integer.valueOf(type));
        this.mLongValues.add(Long.valueOf(value));
        this.mStringValues.add(null);
    }

    public void append(int handle, int property, String value) {
        this.mObjectHandles.add(Integer.valueOf(handle));
        this.mPropertyCodes.add(Integer.valueOf(property));
        this.mDataTypes.add(65535);
        this.mStringValues.add(value);
        this.mLongValues.add(0L);
    }

    public int getCode() {
        return this.mCode;
    }

    public int getCount() {
        return this.mObjectHandles.size();
    }

    public int[] getObjectHandles() {
        return this.mObjectHandles.stream().mapToInt(CameraDeviceImpl$9$$ExternalSyntheticLambda0.INSTANCE).toArray();
    }

    public int[] getPropertyCodes() {
        return this.mPropertyCodes.stream().mapToInt(CameraDeviceImpl$9$$ExternalSyntheticLambda0.INSTANCE).toArray();
    }

    public int[] getDataTypes() {
        return this.mDataTypes.stream().mapToInt(CameraDeviceImpl$9$$ExternalSyntheticLambda0.INSTANCE).toArray();
    }

    public long[] getLongValues() {
        return this.mLongValues.stream().mapToLong(MtpPropertyList$$ExternalSyntheticLambda0.INSTANCE).toArray();
    }

    public String[] getStringValues() {
        return (String[]) this.mStringValues.toArray(new String[0]);
    }
}
