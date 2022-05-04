package android.window;

import android.app.WindowConfiguration;
import android.os.Parcel;
import android.os.Parcelable;
import android.window.TransitionInfo;
/* loaded from: classes3.dex */
public final class TransitionFilter implements Parcelable {
    public static final Parcelable.Creator<TransitionFilter> CREATOR = new Parcelable.Creator<TransitionFilter>() { // from class: android.window.TransitionFilter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransitionFilter createFromParcel(Parcel in) {
            return new TransitionFilter(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransitionFilter[] newArray(int size) {
            return new TransitionFilter[size];
        }
    };
    public Requirement[] mRequirements;
    public int[] mTypeSet;

    public TransitionFilter() {
        this.mTypeSet = null;
        this.mRequirements = null;
    }

    private TransitionFilter(Parcel in) {
        this.mTypeSet = null;
        this.mRequirements = null;
        this.mTypeSet = in.createIntArray();
        this.mRequirements = (Requirement[]) in.createTypedArray(Requirement.CREATOR);
    }

    public boolean matches(TransitionInfo info) {
        if (this.mTypeSet != null) {
            boolean typePass = false;
            int i = 0;
            while (true) {
                if (i >= this.mTypeSet.length) {
                    break;
                } else if (info.getType() == this.mTypeSet[i]) {
                    typePass = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!typePass) {
                return false;
            }
        }
        if (this.mRequirements == null) {
            return true;
        }
        int i2 = 0;
        while (true) {
            Requirement[] requirementArr = this.mRequirements;
            if (i2 >= requirementArr.length) {
                return true;
            }
            if (!requirementArr[i2].matches(info)) {
                return false;
            }
            i2++;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.mTypeSet);
        dest.writeTypedArray(this.mRequirements, flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{types=[");
        if (this.mTypeSet != null) {
            int i = 0;
            while (i < this.mTypeSet.length) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(i == 0 ? "" : ",");
                sb2.append(this.mTypeSet[i]);
                sb.append(sb2.toString());
                i++;
            }
        }
        sb.append("] checks=[");
        if (this.mRequirements != null) {
            int i2 = 0;
            while (i2 < this.mRequirements.length) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(i2 == 0 ? "" : ",");
                sb3.append(this.mRequirements[i2]);
                sb.append(sb3.toString());
                i2++;
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    /* loaded from: classes3.dex */
    public static final class Requirement implements Parcelable {
        public static final Parcelable.Creator<Requirement> CREATOR = new Parcelable.Creator<Requirement>() { // from class: android.window.TransitionFilter.Requirement.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Requirement createFromParcel(Parcel in) {
                return new Requirement(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Requirement[] newArray(int size) {
                return new Requirement[size];
            }
        };
        public int mActivityType;
        public int[] mModes;

        public Requirement() {
            this.mActivityType = 0;
            this.mModes = null;
        }

        private Requirement(Parcel in) {
            this.mActivityType = 0;
            this.mModes = null;
            this.mActivityType = in.readInt();
            this.mModes = in.createIntArray();
        }

        boolean matches(TransitionInfo info) {
            for (int i = info.getChanges().size() - 1; i >= 0; i--) {
                TransitionInfo.Change change = info.getChanges().get(i);
                if (TransitionInfo.isIndependent(change, info) && (this.mActivityType == 0 || (change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == this.mActivityType))) {
                    if (this.mModes != null) {
                        boolean pass = false;
                        int m = 0;
                        while (true) {
                            int[] iArr = this.mModes;
                            if (m >= iArr.length) {
                                break;
                            } else if (iArr[m] == change.getMode()) {
                                pass = true;
                                break;
                            } else {
                                m++;
                            }
                        }
                        if (!pass) {
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        boolean matches(TransitionRequestInfo request) {
            if (this.mActivityType == 0) {
                return true;
            }
            return request.getTriggerTask() != null && request.getTriggerTask().getActivityType() == this.mActivityType;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mActivityType);
            dest.writeIntArray(this.mModes);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            StringBuilder out = new StringBuilder();
            out.append("{atype=" + WindowConfiguration.activityTypeToString(this.mActivityType));
            out.append(" modes=[");
            if (this.mModes != null) {
                int i = 0;
                while (i < this.mModes.length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(i == 0 ? "" : ",");
                    sb.append(TransitionInfo.modeToString(this.mModes[i]));
                    out.append(sb.toString());
                    i++;
                }
            }
            out.append("]}");
            return out.toString();
        }
    }
}
