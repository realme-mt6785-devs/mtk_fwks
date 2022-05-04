package android.bluetooth;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SystemApi
/* loaded from: classes.dex */
public final class BufferConstraints implements Parcelable {
    public static final int BUFFER_CODEC_MAX_NUM = 32;
    public static final Parcelable.Creator<BufferConstraints> CREATOR = new Parcelable.Creator<BufferConstraints>() { // from class: android.bluetooth.BufferConstraints.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BufferConstraints createFromParcel(Parcel in) {
            return new BufferConstraints(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BufferConstraints[] newArray(int size) {
            return new BufferConstraints[size];
        }
    };
    private static final String TAG = "BufferConstraints";
    private List<BufferConstraint> mBufferConstraintList;
    private Map<Integer, BufferConstraint> mBufferConstraints;

    public BufferConstraints(List<BufferConstraint> bufferConstraintList) {
        this.mBufferConstraintList = new ArrayList(bufferConstraintList);
        this.mBufferConstraints = new HashMap();
        for (int i = 0; i < 32; i++) {
            this.mBufferConstraints.put(Integer.valueOf(i), bufferConstraintList.get(i));
        }
    }

    BufferConstraints(Parcel in) {
        this.mBufferConstraintList = new ArrayList();
        this.mBufferConstraints = new HashMap();
        in.readList(this.mBufferConstraintList, BufferConstraint.class.getClassLoader());
        for (int i = 0; i < this.mBufferConstraintList.size(); i++) {
            this.mBufferConstraints.put(Integer.valueOf(i), this.mBufferConstraintList.get(i));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeList(this.mBufferConstraintList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @SystemApi
    public BufferConstraint forCodec(int codec) {
        return this.mBufferConstraints.get(Integer.valueOf(codec));
    }
}
