package android.telephony.data;

import android.hardware.radio.V1_6.SliceInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.data.NetworkSliceInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class RouteSelectionDescriptor implements Parcelable {
    public static final Parcelable.Creator<RouteSelectionDescriptor> CREATOR = new Parcelable.Creator<RouteSelectionDescriptor>() { // from class: android.telephony.data.RouteSelectionDescriptor.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteSelectionDescriptor createFromParcel(Parcel source) {
            return new RouteSelectionDescriptor(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteSelectionDescriptor[] newArray(int size) {
            return new RouteSelectionDescriptor[size];
        }
    };
    public static final int MAX_ROUTE_PRECEDENCE = 255;
    public static final int MAX_ROUTE_SSC_MODE = 3;
    public static final int MIN_ROUTE_PRECEDENCE = 0;
    public static final int MIN_ROUTE_SSC_MODE = 1;
    public static final int ROUTE_SSC_MODE_1 = 1;
    public static final int ROUTE_SSC_MODE_2 = 2;
    public static final int ROUTE_SSC_MODE_3 = 3;
    public static final int SESSION_TYPE_IPV4 = 0;
    public static final int SESSION_TYPE_IPV4V6 = 2;
    public static final int SESSION_TYPE_IPV6 = 1;
    private final List<String> mDnn;
    private final int mPrecedence;
    private final int mSessionType;
    private final List<NetworkSliceInfo> mSliceInfo;
    private final int mSscMode;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface RouteSessionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface RouteSscMode {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RouteSelectionDescriptor(android.hardware.radio.V1_6.RouteSelectionDescriptor rsd) {
        this(rsd.precedence, rsd.sessionType.value(), rsd.sscMode.value(), rsd.sliceInfo, rsd.dnn);
    }

    public RouteSelectionDescriptor(int precedence, int sessionType, int sscMode, List<SliceInfo> sliceInfo, List<String> dnn) {
        this.mPrecedence = precedence;
        this.mSessionType = sessionType;
        this.mSscMode = sscMode;
        this.mSliceInfo = new ArrayList();
        for (SliceInfo si : sliceInfo) {
            this.mSliceInfo.add(sliceInfoBuilder(si));
        }
        ArrayList arrayList = new ArrayList();
        this.mDnn = arrayList;
        arrayList.addAll(dnn);
    }

    private NetworkSliceInfo sliceInfoBuilder(SliceInfo si) {
        NetworkSliceInfo.Builder builder = new NetworkSliceInfo.Builder().setSliceServiceType(si.sst).setMappedHplmnSliceServiceType(si.mappedHplmnSst);
        if (si.sliceDifferentiator != -1) {
            builder.setSliceDifferentiator(si.sliceDifferentiator).setMappedHplmnSliceDifferentiator(si.mappedHplmnSD);
        }
        return builder.build();
    }

    private RouteSelectionDescriptor(Parcel p) {
        this.mPrecedence = p.readInt();
        this.mSessionType = p.readInt();
        this.mSscMode = p.readInt();
        this.mSliceInfo = p.createTypedArrayList(NetworkSliceInfo.CREATOR);
        ArrayList arrayList = new ArrayList();
        this.mDnn = arrayList;
        p.readStringList(arrayList);
    }

    public int getPrecedence() {
        return this.mPrecedence;
    }

    public int getSessionType() {
        return this.mSessionType;
    }

    public int getSscMode() {
        return this.mSscMode;
    }

    public List<NetworkSliceInfo> getSliceInfo() {
        return this.mSliceInfo;
    }

    public List<String> getDataNetworkName() {
        return this.mDnn;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPrecedence);
        dest.writeInt(this.mSessionType);
        dest.writeInt(this.mSscMode);
        dest.writeTypedList(this.mSliceInfo, flags);
        dest.writeStringList(this.mDnn);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteSelectionDescriptor that = (RouteSelectionDescriptor) o;
        if (this.mPrecedence == that.mPrecedence && this.mSessionType == that.mSessionType && this.mSscMode == that.mSscMode && this.mSliceInfo.size() == that.mSliceInfo.size() && this.mSliceInfo.containsAll(that.mSliceInfo) && this.mDnn.size() == that.mDnn.size() && this.mDnn.containsAll(that.mDnn)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mPrecedence), Integer.valueOf(this.mSessionType), Integer.valueOf(this.mSscMode), this.mSliceInfo, this.mDnn);
    }

    public String toString() {
        return "{.precedence = " + this.mPrecedence + ", .sessionType = " + this.mSessionType + ", .sscMode = " + this.mSscMode + ", .sliceInfo = " + this.mSliceInfo + ", .dnn = " + this.mDnn + "}";
    }
}
