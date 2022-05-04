package com.oplus.deepthinker.sdk.aidl.proton.deepsleep;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class DeepSleepPredictResult implements Parcelable {
    public static final Parcelable.Creator<DeepSleepPredictResult> CREATOR = new Parcelable.Creator<DeepSleepPredictResult>() { // from class: com.oplus.deepthinker.sdk.aidl.proton.deepsleep.DeepSleepPredictResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeepSleepPredictResult createFromParcel(Parcel source) {
            DeepSleepPredictResult result = new DeepSleepPredictResult(null, null);
            String mResultTypeString = source.readString();
            if (mResultTypeString != null) {
                result.mResultType = PredictResultType.valueOf(mResultTypeString);
            }
            if (result.mDeepSleepClusterList == null) {
                result.mDeepSleepClusterList = new ArrayList();
            }
            source.readTypedList(result.mDeepSleepClusterList, DeepSleepCluster.CREATOR);
            return result;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeepSleepPredictResult[] newArray(int size) {
            return new DeepSleepPredictResult[size];
        }
    };
    private static final String TAG = "DeepSleepPredictResult";
    private List<DeepSleepCluster> mDeepSleepClusterList;
    private PredictResultType mResultType;

    public DeepSleepPredictResult(PredictResultType type, List<DeepSleepCluster> list) {
        this.mResultType = PredictResultType.PREDICT_RESULT_TYPE_UNKNOWN;
        this.mDeepSleepClusterList = null;
        this.mResultType = type;
        this.mDeepSleepClusterList = list;
    }

    public PredictResultType getResultType() {
        return this.mResultType;
    }

    public List<DeepSleepCluster> getDeepSleepClusterList() {
        return this.mDeepSleepClusterList;
    }

    public void setResultType(PredictResultType type) {
        this.mResultType = type;
    }

    public void setClusterList(List<DeepSleepCluster> clusterList) {
        this.mDeepSleepClusterList = clusterList;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("DeepSleepPredictResult:resultType=" + this.mResultType);
        List<DeepSleepCluster> list = this.mDeepSleepClusterList;
        if (list != null && list.size() > 0) {
            for (DeepSleepCluster cluster : this.mDeepSleepClusterList) {
                str.append(cluster.toString());
            }
        }
        return str.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        PredictResultType predictResultType = this.mResultType;
        String result = predictResultType == null ? null : predictResultType.name();
        dest.writeString(result);
        dest.writeTypedList(this.mDeepSleepClusterList);
    }
}
