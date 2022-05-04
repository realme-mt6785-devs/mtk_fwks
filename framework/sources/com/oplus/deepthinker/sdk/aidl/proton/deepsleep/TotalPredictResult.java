package com.oplus.deepthinker.sdk.aidl.proton.deepsleep;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public class TotalPredictResult implements Parcelable {
    public static final Parcelable.Creator<TotalPredictResult> CREATOR = new Parcelable.Creator<TotalPredictResult>() { // from class: com.oplus.deepthinker.sdk.aidl.proton.deepsleep.TotalPredictResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TotalPredictResult createFromParcel(Parcel parcel) {
            TotalPredictResult result = new TotalPredictResult(null, null, null, null);
            if (parcel.readString() != null) {
                result.setSleepCluster(DeepSleepCluster.CREATOR.createFromParcel(parcel));
            }
            if (parcel.readString() != null) {
                result.setWakeCluster(DeepSleepCluster.CREATOR.createFromParcel(parcel));
            }
            if (parcel.readString() != null) {
                result.setOptimalSleepConfig(TrainConfig.CREATOR.createFromParcel(parcel));
            }
            if (parcel.readString() != null) {
                result.setOptimalWakeConfig(TrainConfig.CREATOR.createFromParcel(parcel));
            }
            return result;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TotalPredictResult[] newArray(int size) {
            return new TotalPredictResult[size];
        }
    };
    private static final String NULL = "null";
    private static final String TAG = "TotalPredictResult";
    private TrainConfig mOptimalSleepConfig;
    private TrainConfig mOptimalWakeConfig;
    private DeepSleepCluster mSleepCluster;
    private DeepSleepCluster mWakeCluster;

    public TotalPredictResult(DeepSleepCluster sleepCluster, DeepSleepCluster wakeCluster) {
        this.mSleepCluster = null;
        this.mWakeCluster = null;
        this.mOptimalSleepConfig = null;
        this.mOptimalWakeConfig = null;
        this.mSleepCluster = sleepCluster;
        this.mWakeCluster = wakeCluster;
    }

    public TotalPredictResult(DeepSleepCluster sleepCluster, DeepSleepCluster wakeCluster, TrainConfig sleepConfig, TrainConfig wakeConfig) {
        this.mSleepCluster = null;
        this.mWakeCluster = null;
        this.mOptimalSleepConfig = null;
        this.mOptimalWakeConfig = null;
        this.mSleepCluster = sleepCluster;
        this.mWakeCluster = wakeCluster;
        this.mOptimalSleepConfig = sleepConfig;
        this.mOptimalWakeConfig = wakeConfig;
    }

    public DeepSleepCluster getSleepCluster() {
        return this.mSleepCluster;
    }

    public void setSleepCluster(DeepSleepCluster sleepCluster) {
        this.mSleepCluster = sleepCluster;
    }

    public DeepSleepCluster getWakeCluster() {
        return this.mWakeCluster;
    }

    public void setWakeCluster(DeepSleepCluster wakeCluster) {
        this.mWakeCluster = wakeCluster;
    }

    public void setOptimalSleepConfig(TrainConfig trainConfig) {
        this.mOptimalSleepConfig = trainConfig;
    }

    public void setOptimalWakeConfig(TrainConfig trainConfig) {
        this.mOptimalWakeConfig = trainConfig;
    }

    public TrainConfig getOptimalSleepConfig() {
        return this.mOptimalSleepConfig;
    }

    public TrainConfig getOptimalWakeConfig() {
        return this.mOptimalWakeConfig;
    }

    public String toString() {
        DeepSleepCluster deepSleepCluster = this.mSleepCluster;
        String wakeConfig = NULL;
        String sleepString = deepSleepCluster != null ? deepSleepCluster.toString() : wakeConfig;
        DeepSleepCluster deepSleepCluster2 = this.mWakeCluster;
        String wakeString = deepSleepCluster2 != null ? deepSleepCluster2.toString() : wakeConfig;
        TrainConfig trainConfig = this.mOptimalSleepConfig;
        String sleepConfig = trainConfig != null ? trainConfig.toString() : wakeConfig;
        TrainConfig trainConfig2 = this.mOptimalWakeConfig;
        if (trainConfig2 != null) {
            wakeConfig = trainConfig2.toString();
        }
        return String.format("mSleepCluster=%s,mSleepConfig=%s,mWakeCluster=%s,mWakeConfig=%s", sleepString, sleepConfig, wakeString, wakeConfig);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.mSleepCluster == null) {
            parcel.writeString(null);
        } else {
            parcel.writeString("com.oplus.deepthinker.sdk.app.aidl.proton.deepsleep.DeepSleepCluster");
            this.mSleepCluster.writeToParcel(parcel, i);
        }
        if (this.mWakeCluster == null) {
            parcel.writeString(null);
        } else {
            parcel.writeString("com.oplus.deepthinker.sdk.app.aidl.proton.deepsleep.DeepSleepCluster");
            this.mWakeCluster.writeToParcel(parcel, i);
        }
        if (this.mOptimalSleepConfig == null) {
            parcel.writeString(null);
        } else {
            parcel.writeString("com.oplus.deepthinker.sdk.app.aidl.proton.deepsleep.TrainConfig");
            this.mOptimalSleepConfig.writeToParcel(parcel, i);
        }
        if (this.mOptimalWakeConfig == null) {
            parcel.writeString(null);
            return;
        }
        parcel.writeString("com.oplus.deepthinker.sdk.app.aidl.proton.deepsleep.TrainConfig");
        this.mOptimalWakeConfig.writeToParcel(parcel, i);
    }
}
