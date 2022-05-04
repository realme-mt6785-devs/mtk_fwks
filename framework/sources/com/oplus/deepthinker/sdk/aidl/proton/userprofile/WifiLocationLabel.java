package com.oplus.deepthinker.sdk.aidl.proton.userprofile;

import java.util.HashSet;
import java.util.Set;
/* loaded from: classes4.dex */
public class WifiLocationLabel {
    private double mAccuracy;
    private int mClusterPointsNum;
    private double mLatitude;
    private double mLongitude;
    private int mRadius;
    private int mSurvivalTime;
    private Set<String> mBssidSet = new HashSet();
    private Set<String> mSsidSet = new HashSet();
    private Set<String> mConfigName = new HashSet();

    public WifiLocationLabel(double mLongitude, double mLatitude, int mRadius, int mClusterPointsNum, double mAccuracy, Set<String> mBssidSet, Set<String> mSsidSet, Set<String> mConfigName) {
        this.mLongitude = mLongitude;
        this.mLatitude = mLatitude;
        this.mRadius = mRadius;
        this.mClusterPointsNum = mClusterPointsNum;
        this.mAccuracy = mAccuracy;
        this.mBssidSet.addAll(mBssidSet);
        this.mSsidSet.addAll(mSsidSet);
        this.mConfigName.addAll(mConfigName);
    }

    public String toString() {
        return "WifiLocationLabel{mLongitude=" + this.mLongitude + ", mLatitude=" + this.mLatitude + ", mRadius=" + this.mRadius + ", mClusterPointsNum=" + this.mClusterPointsNum + ", mAccuracy=" + this.mAccuracy + ", mSsidSet=" + this.mSsidSet.toString() + ", mBssidSet=" + this.mBssidSet.toString() + ", mConfigName=" + this.mConfigName.toString() + ", mSurvivalTime=" + this.mSurvivalTime + '}';
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public int getRadius() {
        return this.mRadius;
    }

    public int getClusterPointsNum() {
        return this.mClusterPointsNum;
    }

    public void setClusterPointsNum(int clusterPointsNum) {
        this.mClusterPointsNum = clusterPointsNum;
    }

    public double getAccuracy() {
        return this.mAccuracy;
    }

    public void setAccuracy(double accuracy) {
        this.mAccuracy = accuracy;
    }

    public Set<String> getBssidSet() {
        return this.mBssidSet;
    }

    public void setBssidSet(Set<String> mBssidSet) {
        this.mBssidSet = mBssidSet;
    }

    public Set<String> getSsidSet() {
        return this.mSsidSet;
    }

    public void setSsidSet(Set<String> mSsidSet) {
        this.mSsidSet = mSsidSet;
    }

    public Set<String> getConfigName() {
        return this.mConfigName;
    }

    public void setConfigName(Set<String> mConfigName) {
        this.mConfigName = mConfigName;
    }

    public int getSurvivalTime() {
        return this.mSurvivalTime;
    }

    public void setSurvivalTime(int mSurvivalTime) {
        this.mSurvivalTime = mSurvivalTime;
    }
}
