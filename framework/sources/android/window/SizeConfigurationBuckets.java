package android.window;

import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;
import java.util.Arrays;
/* loaded from: classes3.dex */
public final class SizeConfigurationBuckets implements Parcelable {
    public static final Parcelable.Creator<SizeConfigurationBuckets> CREATOR = new Parcelable.Creator<SizeConfigurationBuckets>() { // from class: android.window.SizeConfigurationBuckets.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SizeConfigurationBuckets[] newArray(int size) {
            return new SizeConfigurationBuckets[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SizeConfigurationBuckets createFromParcel(Parcel in) {
            return new SizeConfigurationBuckets(in);
        }
    };
    private final int[] mHorizontal;
    private final int[] mSmallest;
    private final int[] mVertical;

    public SizeConfigurationBuckets(Configuration[] sizeConfigurations) {
        SparseIntArray horizontal = new SparseIntArray();
        SparseIntArray vertical = new SparseIntArray();
        SparseIntArray smallest = new SparseIntArray();
        for (int i = sizeConfigurations.length - 1; i >= 0; i--) {
            Configuration config = sizeConfigurations[i];
            if (config.screenHeightDp != 0) {
                vertical.put(config.screenHeightDp, 0);
            }
            if (config.screenWidthDp != 0) {
                horizontal.put(config.screenWidthDp, 0);
            }
            if (config.smallestScreenWidthDp != 0) {
                smallest.put(config.smallestScreenWidthDp, 0);
            }
        }
        this.mHorizontal = horizontal.copyKeys();
        this.mVertical = vertical.copyKeys();
        this.mSmallest = smallest.copyKeys();
    }

    public static int filterDiff(int diff, Configuration oldConfig, Configuration newConfig, SizeConfigurationBuckets buckets) {
        if (buckets == null) {
            return diff & (-3073);
        }
        if ((diff & 1024) != 0) {
            boolean crosses = buckets.crossesHorizontalSizeThreshold(oldConfig.screenWidthDp, newConfig.screenWidthDp) || buckets.crossesVerticalSizeThreshold(oldConfig.screenHeightDp, newConfig.screenHeightDp);
            if (!crosses) {
                diff &= -1025;
            }
        }
        if ((diff & 2048) == 0) {
            return diff;
        }
        int oldSmallest = oldConfig.smallestScreenWidthDp;
        int newSmallest = newConfig.smallestScreenWidthDp;
        if (!buckets.crossesSmallestSizeThreshold(oldSmallest, newSmallest)) {
            return diff & (-2049);
        }
        return diff;
    }

    private boolean crossesHorizontalSizeThreshold(int firstDp, int secondDp) {
        return crossesSizeThreshold(this.mHorizontal, firstDp, secondDp);
    }

    private boolean crossesVerticalSizeThreshold(int firstDp, int secondDp) {
        return crossesSizeThreshold(this.mVertical, firstDp, secondDp);
    }

    private boolean crossesSmallestSizeThreshold(int firstDp, int secondDp) {
        return crossesSizeThreshold(this.mSmallest, firstDp, secondDp);
    }

    private static boolean crossesSizeThreshold(int[] thresholds, int firstDp, int secondDp) {
        if (thresholds == null) {
            return false;
        }
        for (int i = thresholds.length - 1; i >= 0; i--) {
            int threshold = thresholds[i];
            if ((firstDp < threshold && secondDp >= threshold) || (firstDp >= threshold && secondDp < threshold)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return Arrays.toString(this.mHorizontal) + " " + Arrays.toString(this.mVertical) + " " + Arrays.toString(this.mSmallest);
    }

    public SizeConfigurationBuckets(int[] horizontal, int[] vertical, int[] smallest) {
        this.mHorizontal = horizontal;
        this.mVertical = vertical;
        this.mSmallest = smallest;
    }

    public int[] getHorizontal() {
        return this.mHorizontal;
    }

    public int[] getVertical() {
        return this.mVertical;
    }

    public int[] getSmallest() {
        return this.mSmallest;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        byte flg = 0;
        if (this.mHorizontal != null) {
            flg = (byte) (0 | 1);
        }
        if (this.mVertical != null) {
            flg = (byte) (flg | 2);
        }
        if (this.mSmallest != null) {
            flg = (byte) (flg | 4);
        }
        dest.writeByte(flg);
        int[] iArr = this.mHorizontal;
        if (iArr != null) {
            dest.writeIntArray(iArr);
        }
        int[] iArr2 = this.mVertical;
        if (iArr2 != null) {
            dest.writeIntArray(iArr2);
        }
        int[] iArr3 = this.mSmallest;
        if (iArr3 != null) {
            dest.writeIntArray(iArr3);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    SizeConfigurationBuckets(Parcel in) {
        byte flg = in.readByte();
        int[] smallest = null;
        int[] horizontal = (flg & 1) == 0 ? null : in.createIntArray();
        int[] vertical = (flg & 2) == 0 ? null : in.createIntArray();
        smallest = (flg & 4) != 0 ? in.createIntArray() : smallest;
        this.mHorizontal = horizontal;
        this.mVertical = vertical;
        this.mSmallest = smallest;
    }

    @Deprecated
    private void __metadata() {
    }
}
