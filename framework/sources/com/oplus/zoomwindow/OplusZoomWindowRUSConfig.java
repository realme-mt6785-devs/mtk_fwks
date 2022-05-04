package com.oplus.zoomwindow;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusZoomWindowRUSConfig implements Parcelable {
    public static final Parcelable.Creator<OplusZoomWindowRUSConfig> CREATOR = new Parcelable.Creator<OplusZoomWindowRUSConfig>() { // from class: com.oplus.zoomwindow.OplusZoomWindowRUSConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowRUSConfig createFromParcel(Parcel in) {
            return new OplusZoomWindowRUSConfig(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusZoomWindowRUSConfig[] newArray(int size) {
            return new OplusZoomWindowRUSConfig[size];
        }
    };
    private float mCornerRadius;
    private List<String> mCpnList;
    private List<String> mDismissZoomWindowPkgList;
    private List<String> mForcedRelaunchCpnList;
    private List<String> mForcedShowToastPkgList;
    private List<String> mNotShowToastPkgList;
    private boolean mOnlySupportPkgListSwitch;
    private OplusZoomWindowRegion mOplusZoomWindowRegion;
    private OplusZoomWindowSize mOplusZoomWindowSize;
    private List<String> mPkgList;
    private List<String> mReplyPkgList;
    private boolean mShowToastSwitch;
    private boolean mSupportStartZoomOnNewTaskSwitch;
    private List<String> mUnRelaunchCpnList;
    private List<String> mUnReusedCpnList;
    private List<String> mUnSupportCallerPkgList;
    private List<String> mUnSupportCpnList;
    private List<String> mUnSupportPkgList;
    private List<String> mUnusedZoomDisplayInfoPkgList;
    private int mVersion;
    private List<OplusZoomWindowSupportAppInfo> mVersionLimitPkgList;
    private List<OplusZoomWindowSupportAppInfo> mVersionLimitReplyPkgList;
    private boolean mZoomWindowSwitch;

    public int getVersion() {
        return this.mVersion;
    }

    public void setVersion(int version) {
        this.mVersion = version;
    }

    public boolean getZoomWindowSwitch() {
        return this.mZoomWindowSwitch;
    }

    public void setZoomWindowSwitch(boolean zoomWindowSwitch) {
        this.mZoomWindowSwitch = zoomWindowSwitch;
    }

    public boolean getShowToastSwitch() {
        return this.mShowToastSwitch;
    }

    public void setShowToastSwitch(boolean showToastSwitch) {
        this.mShowToastSwitch = showToastSwitch;
    }

    public boolean getOnlySupportPkgListSwitch() {
        return this.mOnlySupportPkgListSwitch;
    }

    public void setOnlySupportPkgListSwitch(boolean onlySupportPkgListSwitch) {
        this.mOnlySupportPkgListSwitch = onlySupportPkgListSwitch;
    }

    public boolean isSupportStartZoomOnNewTaskSwitch() {
        return this.mSupportStartZoomOnNewTaskSwitch;
    }

    public void setSupportStartZoomOnNewTaskSwitch(boolean mSupportStartZoomOnNewTaskSwitch) {
        this.mSupportStartZoomOnNewTaskSwitch = mSupportStartZoomOnNewTaskSwitch;
    }

    public List<String> getPkgList() {
        return this.mPkgList;
    }

    public void setPkgList(List<String> pkgList) {
        this.mPkgList = pkgList;
    }

    public List<String> getReplyPkgList() {
        return this.mReplyPkgList;
    }

    public void setReplyPkgList(List<String> replyPkgList) {
        this.mReplyPkgList = replyPkgList;
    }

    public List<String> getUnSupportPkgList() {
        return this.mUnSupportPkgList;
    }

    public void setUnSupportPkgList(List<String> unSupportPkgList) {
        this.mUnSupportPkgList = unSupportPkgList;
    }

    public List<String> getUnSupportCallerPkgList() {
        return this.mUnSupportCallerPkgList;
    }

    public void setUnSupportCallerPkgList(List<String> unSupportCallerPkgList) {
        this.mUnSupportCallerPkgList = unSupportCallerPkgList;
    }

    public List<String> getForcedShowToastPkgList() {
        return this.mForcedShowToastPkgList;
    }

    public void setForcedShowToastPkgList(List<String> forcedShowToastPkgList) {
        this.mForcedShowToastPkgList = forcedShowToastPkgList;
    }

    public List<String> getNotShowToastPkgList() {
        return this.mNotShowToastPkgList;
    }

    public void setNotShowToastPkgList(List<String> notShowToastPkgList) {
        this.mNotShowToastPkgList = notShowToastPkgList;
    }

    public List<String> getUnusedZoomDisplayInfoPkgList() {
        return this.mUnusedZoomDisplayInfoPkgList;
    }

    public void setUnusedZoomDisplayInfoPkgList(List<String> unusedZoomDisplayInfoPkgList) {
        this.mUnusedZoomDisplayInfoPkgList = unusedZoomDisplayInfoPkgList;
    }

    public List<String> getCpnList() {
        return this.mCpnList;
    }

    public void setCpnList(List<String> cpnList) {
        this.mCpnList = cpnList;
    }

    public List<String> getUnSupportCpnList() {
        return this.mUnSupportCpnList;
    }

    public void setUnSupportCpnList(List<String> unSupportCpnList) {
        this.mUnSupportCpnList = unSupportCpnList;
    }

    public List<String> getUnReusedCpnList() {
        return this.mUnReusedCpnList;
    }

    public void setUnReusedCpnList(List<String> unReusedCpnList) {
        this.mUnReusedCpnList = unReusedCpnList;
    }

    public List<String> getUnRelaunchCpnList() {
        return this.mUnRelaunchCpnList;
    }

    public void setUnRelaunchCpnList(List<String> unRelaunchCpnList) {
        this.mUnRelaunchCpnList = unRelaunchCpnList;
    }

    public List<String> getForcedRelaunchCpnList() {
        return this.mForcedRelaunchCpnList;
    }

    public void setForcedRelaunchCpnList(List<String> forcedRelaunchCpnList) {
        this.mForcedRelaunchCpnList = forcedRelaunchCpnList;
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.mCornerRadius = cornerRadius;
    }

    public OplusZoomWindowSize getOplusZoomWindowSize() {
        return this.mOplusZoomWindowSize;
    }

    public void setOplusZoomWindowSize(OplusZoomWindowSize oplusZoomWindowSize) {
        this.mOplusZoomWindowSize = oplusZoomWindowSize;
    }

    public OplusZoomWindowRegion getOplusZoomWindowRegion() {
        return this.mOplusZoomWindowRegion;
    }

    public void setOplusZoomWindowRegion(OplusZoomWindowRegion oplusZoomWindowRegion) {
        this.mOplusZoomWindowRegion = oplusZoomWindowRegion;
    }

    public List<OplusZoomWindowSupportAppInfo> getVersionLimitPkgList() {
        return this.mVersionLimitPkgList;
    }

    public void setVersionLimitPkgList(List<OplusZoomWindowSupportAppInfo> versionLimitPkgList) {
        this.mVersionLimitPkgList = versionLimitPkgList;
    }

    public List<OplusZoomWindowSupportAppInfo> getVersionLimitReplyPkgList() {
        return this.mVersionLimitReplyPkgList;
    }

    public void setVersionLimitReplyPkgList(List<OplusZoomWindowSupportAppInfo> versionLimitReplyPkgList) {
        this.mVersionLimitReplyPkgList = versionLimitReplyPkgList;
    }

    public List<String> getDismissZoomWindowPkgList() {
        return this.mDismissZoomWindowPkgList;
    }

    public void setDismissZoomWindowPkgList(List<String> dismissZoomWindowPkgList) {
        this.mDismissZoomWindowPkgList = dismissZoomWindowPkgList;
    }

    public OplusZoomWindowRUSConfig() {
        this.mPkgList = new ArrayList();
        this.mReplyPkgList = new ArrayList();
        this.mUnSupportPkgList = new ArrayList();
        this.mUnSupportCallerPkgList = new ArrayList();
        this.mForcedShowToastPkgList = new ArrayList();
        this.mNotShowToastPkgList = new ArrayList();
        this.mUnusedZoomDisplayInfoPkgList = new ArrayList();
        this.mCpnList = new ArrayList();
        this.mUnSupportCpnList = new ArrayList();
        this.mUnReusedCpnList = new ArrayList();
        this.mUnRelaunchCpnList = new ArrayList();
        this.mForcedRelaunchCpnList = new ArrayList();
        this.mOplusZoomWindowSize = new OplusZoomWindowSize();
        this.mOplusZoomWindowRegion = new OplusZoomWindowRegion();
        this.mVersionLimitPkgList = new ArrayList();
        this.mVersionLimitReplyPkgList = new ArrayList();
        this.mDismissZoomWindowPkgList = new ArrayList();
    }

    public OplusZoomWindowRUSConfig(Parcel in) {
        this.mPkgList = new ArrayList();
        this.mReplyPkgList = new ArrayList();
        this.mUnSupportPkgList = new ArrayList();
        this.mUnSupportCallerPkgList = new ArrayList();
        this.mForcedShowToastPkgList = new ArrayList();
        this.mNotShowToastPkgList = new ArrayList();
        this.mUnusedZoomDisplayInfoPkgList = new ArrayList();
        this.mCpnList = new ArrayList();
        this.mUnSupportCpnList = new ArrayList();
        this.mUnReusedCpnList = new ArrayList();
        this.mUnRelaunchCpnList = new ArrayList();
        this.mForcedRelaunchCpnList = new ArrayList();
        this.mOplusZoomWindowSize = new OplusZoomWindowSize();
        this.mOplusZoomWindowRegion = new OplusZoomWindowRegion();
        this.mVersionLimitPkgList = new ArrayList();
        this.mVersionLimitReplyPkgList = new ArrayList();
        this.mDismissZoomWindowPkgList = new ArrayList();
        this.mVersion = in.readInt();
        boolean z = true;
        this.mZoomWindowSwitch = in.readByte() != 0;
        this.mShowToastSwitch = in.readByte() != 0;
        this.mOnlySupportPkgListSwitch = in.readByte() != 0;
        this.mSupportStartZoomOnNewTaskSwitch = in.readByte() == 0 ? false : z;
        this.mPkgList = in.createStringArrayList();
        this.mReplyPkgList = in.createStringArrayList();
        this.mUnSupportPkgList = in.createStringArrayList();
        this.mUnSupportCallerPkgList = in.createStringArrayList();
        this.mForcedShowToastPkgList = in.createStringArrayList();
        this.mNotShowToastPkgList = in.createStringArrayList();
        this.mUnusedZoomDisplayInfoPkgList = in.createStringArrayList();
        this.mCpnList = in.createStringArrayList();
        this.mUnSupportCpnList = in.createStringArrayList();
        this.mUnReusedCpnList = in.createStringArrayList();
        this.mUnRelaunchCpnList = in.createStringArrayList();
        this.mForcedRelaunchCpnList = in.createStringArrayList();
        this.mCornerRadius = in.readFloat();
        this.mOplusZoomWindowSize = (OplusZoomWindowSize) in.readParcelable(OplusZoomWindowSize.class.getClassLoader());
        this.mOplusZoomWindowRegion = (OplusZoomWindowRegion) in.readParcelable(OplusZoomWindowRegion.class.getClassLoader());
        this.mVersionLimitPkgList = in.readParcelableList(this.mVersionLimitPkgList, OplusZoomWindowSupportAppInfo.class.getClassLoader());
        this.mVersionLimitReplyPkgList = in.readParcelableList(this.mVersionLimitReplyPkgList, OplusZoomWindowSupportAppInfo.class.getClassLoader());
        this.mDismissZoomWindowPkgList = in.createStringArrayList();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mVersion);
        dest.writeByte(this.mZoomWindowSwitch ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mShowToastSwitch ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mOnlySupportPkgListSwitch ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mSupportStartZoomOnNewTaskSwitch ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.mPkgList);
        dest.writeStringList(this.mReplyPkgList);
        dest.writeStringList(this.mUnSupportPkgList);
        dest.writeStringList(this.mUnSupportCallerPkgList);
        dest.writeStringList(this.mForcedShowToastPkgList);
        dest.writeStringList(this.mNotShowToastPkgList);
        dest.writeStringList(this.mUnusedZoomDisplayInfoPkgList);
        dest.writeStringList(this.mCpnList);
        dest.writeStringList(this.mUnSupportCpnList);
        dest.writeStringList(this.mUnReusedCpnList);
        dest.writeStringList(this.mUnRelaunchCpnList);
        dest.writeStringList(this.mForcedRelaunchCpnList);
        dest.writeFloat(this.mCornerRadius);
        dest.writeParcelable(this.mOplusZoomWindowSize, flags);
        dest.writeParcelable(this.mOplusZoomWindowRegion, flags);
        dest.writeParcelableList(this.mVersionLimitPkgList, flags);
        dest.writeParcelableList(this.mVersionLimitReplyPkgList, flags);
        dest.writeStringList(this.mDismissZoomWindowPkgList);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OplusZoomWindowRUSConfig{");
        sb.append("version = " + this.mVersion);
        sb.append("\nZoomWindowSwitch = " + this.mZoomWindowSwitch);
        sb.append("\nShowToastSwitch = " + this.mShowToastSwitch);
        sb.append("\nOnlySupportPkgListSwitch = " + this.mOnlySupportPkgListSwitch);
        sb.append("\nPkgList = " + this.mPkgList);
        sb.append("\nReplyPkgList = " + this.mReplyPkgList);
        sb.append("\nUnSupportPkgList = " + this.mUnSupportPkgList);
        sb.append("\nUnSupportCallerPkgList = " + this.mUnSupportCallerPkgList);
        sb.append("\nmSupportStartZoomOnNewTaskSwitch = " + this.mSupportStartZoomOnNewTaskSwitch);
        sb.append("\nForcedShowToastPkgList = " + this.mForcedShowToastPkgList);
        sb.append("\nNotShowToastPkgList = " + this.mNotShowToastPkgList);
        sb.append("\nUnusedZoomDisplayInfoPkgList = " + this.mUnusedZoomDisplayInfoPkgList);
        sb.append("\nCpnList = " + this.mCpnList);
        sb.append("\nUnSupportCpnList = " + this.mUnSupportCpnList);
        sb.append("\nUnReusedCpnList = " + this.mUnReusedCpnList);
        sb.append("\nUnRelaunchCpnList = " + this.mUnRelaunchCpnList);
        sb.append("\nForcedRelaunchCpnList = " + this.mForcedRelaunchCpnList);
        sb.append("\nCornerRadius=" + this.mCornerRadius);
        sb.append("\nZoom Window size =  = " + this.mOplusZoomWindowSize.toString());
        sb.append("\nRegion = " + this.mOplusZoomWindowRegion.toString());
        sb.append("\nVersionLimitPkgList = " + this.mVersionLimitPkgList);
        sb.append("\nVersionLimitReplyPkgList = " + this.mVersionLimitReplyPkgList);
        sb.append("\nDismissZoomWindowPkgList = " + this.mDismissZoomWindowPkgList);
        sb.append("}");
        return sb.toString();
    }
}
