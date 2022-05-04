package android.view;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.android.internal.R;
import java.util.Arrays;
/* loaded from: classes3.dex */
public class RoundedCorners implements Parcelable {
    public static final int ROUNDED_CORNER_POSITION_LENGTH = 4;
    private static int sCachedDisplayHeight;
    private static int sCachedDisplayWidth;
    private static Pair<Integer, Integer> sCachedRadii;
    private static RoundedCorners sCachedRoundedCorners;
    public final RoundedCorner[] mRoundedCorners;
    public static final RoundedCorners NO_ROUNDED_CORNERS = new RoundedCorners(new RoundedCorner(0), new RoundedCorner(1), new RoundedCorner(2), new RoundedCorner(3));
    private static final Object CACHE_LOCK = new Object();
    public static final Parcelable.Creator<RoundedCorners> CREATOR = new Parcelable.Creator<RoundedCorners>() { // from class: android.view.RoundedCorners.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoundedCorners createFromParcel(Parcel in) {
            int variant = in.readInt();
            if (variant == 0) {
                return RoundedCorners.NO_ROUNDED_CORNERS;
            }
            RoundedCorner[] roundedCorners = new RoundedCorner[4];
            in.readTypedArray(roundedCorners, RoundedCorner.CREATOR);
            return new RoundedCorners(roundedCorners);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoundedCorners[] newArray(int size) {
            return new RoundedCorners[size];
        }
    };

    public RoundedCorners(RoundedCorner[] roundedCorners) {
        this.mRoundedCorners = roundedCorners;
    }

    public RoundedCorners(RoundedCorner topLeft, RoundedCorner topRight, RoundedCorner bottomRight, RoundedCorner bottomLeft) {
        this.mRoundedCorners = r0;
        RoundedCorner[] roundedCornerArr = {topLeft, topRight, bottomRight, bottomLeft};
    }

    public RoundedCorners(RoundedCorners roundedCorners) {
        this.mRoundedCorners = new RoundedCorner[4];
        for (int i = 0; i < 4; i++) {
            this.mRoundedCorners[i] = new RoundedCorner(roundedCorners.mRoundedCorners[i]);
        }
    }

    public static RoundedCorners fromResources(Resources res, int displayWidth, int displayHeight) {
        return fromRadii(loadRoundedCornerRadii(res), displayWidth, displayHeight);
    }

    public static RoundedCorners fromRadii(Pair<Integer, Integer> radii, int displayWidth, int displayHeight) {
        if (radii == null) {
            return null;
        }
        synchronized (CACHE_LOCK) {
            if (radii.equals(sCachedRadii) && sCachedDisplayWidth == displayWidth && sCachedDisplayHeight == displayHeight) {
                return sCachedRoundedCorners;
            }
            RoundedCorner[] roundedCorners = new RoundedCorner[4];
            int bottomRadius = 0;
            int topRadius = radii.first.intValue() > 0 ? radii.first.intValue() : 0;
            if (radii.second.intValue() > 0) {
                bottomRadius = radii.second.intValue();
            }
            int i = 0;
            while (i < 4) {
                roundedCorners[i] = createRoundedCorner(i, i <= 1 ? topRadius : bottomRadius, displayWidth, displayHeight);
                i++;
            }
            RoundedCorners result = new RoundedCorners(roundedCorners);
            synchronized (CACHE_LOCK) {
                sCachedDisplayWidth = displayWidth;
                sCachedDisplayHeight = displayHeight;
                sCachedRadii = radii;
                sCachedRoundedCorners = result;
            }
            return result;
        }
    }

    private static Pair<Integer, Integer> loadRoundedCornerRadii(Resources res) {
        int radiusDefault = res.getDimensionPixelSize(R.dimen.rounded_corner_radius);
        int radiusTop = res.getDimensionPixelSize(R.dimen.rounded_corner_radius_top);
        int radiusBottom = res.getDimensionPixelSize(R.dimen.rounded_corner_radius_bottom);
        if (radiusDefault == 0 && radiusTop == 0 && radiusBottom == 0) {
            return null;
        }
        Pair<Integer, Integer> radii = new Pair<>(Integer.valueOf(radiusTop > 0 ? radiusTop : radiusDefault), Integer.valueOf(radiusBottom > 0 ? radiusBottom : radiusDefault));
        return radii;
    }

    public RoundedCorners inset(int insetLeft, int insetTop, int insetRight, int insetBottom) {
        RoundedCorner[] roundedCorners = new RoundedCorner[4];
        for (int i = 0; i < 4; i++) {
            roundedCorners[i] = insetRoundedCorner(i, insetLeft, insetTop, insetRight, insetBottom);
        }
        return new RoundedCorners(roundedCorners);
    }

    private RoundedCorner insetRoundedCorner(int position, int insetLeft, int insetTop, int insetRight, int insetBottom) {
        if (this.mRoundedCorners[position].isEmpty()) {
            return new RoundedCorner(position);
        }
        int radius = this.mRoundedCorners[position].getRadius();
        Point center = this.mRoundedCorners[position].getCenter();
        boolean hasRoundedCorner = true;
        int i = 0;
        switch (position) {
            case 0:
                if (radius <= insetTop || radius <= insetLeft) {
                    hasRoundedCorner = false;
                    break;
                }
            case 1:
                if (radius <= insetTop || radius <= insetRight) {
                    hasRoundedCorner = false;
                    break;
                }
            case 2:
                if (radius <= insetBottom || radius <= insetRight) {
                    hasRoundedCorner = false;
                    break;
                }
            case 3:
                if (radius <= insetBottom || radius <= insetLeft) {
                    hasRoundedCorner = false;
                    break;
                }
            default:
                throw new IllegalArgumentException("The position is not one of the RoundedCornerPosition =" + position);
        }
        int i2 = hasRoundedCorner ? center.x - insetLeft : 0;
        if (hasRoundedCorner) {
            i = center.y - insetTop;
        }
        return new RoundedCorner(position, radius, i2, i);
    }

    public RoundedCorner getRoundedCorner(int position) {
        if (this.mRoundedCorners[position].isEmpty()) {
            return null;
        }
        return new RoundedCorner(this.mRoundedCorners[position]);
    }

    public void setRoundedCorner(int position, RoundedCorner roundedCorner) {
        this.mRoundedCorners[position] = roundedCorner == null ? new RoundedCorner(position) : roundedCorner;
    }

    public RoundedCorner[] getAllRoundedCorners() {
        RoundedCorner[] roundedCorners = new RoundedCorner[4];
        for (int i = 0; i < 4; i++) {
            roundedCorners[i] = new RoundedCorner(roundedCorners[i]);
        }
        return roundedCorners;
    }

    public RoundedCorners scale(float scale) {
        if (scale == 1.0f) {
            return this;
        }
        RoundedCorner[] roundedCorners = new RoundedCorner[4];
        for (int i = 0; i < 4; i++) {
            RoundedCorner roundedCorner = this.mRoundedCorners[i];
            roundedCorners[i] = new RoundedCorner(i, (int) (roundedCorner.getRadius() * scale), (int) (roundedCorner.getCenter().x * scale), (int) (roundedCorner.getCenter().y * scale));
        }
        return new RoundedCorners(roundedCorners);
    }

    public RoundedCorners rotate(int rotation, int initialDisplayWidth, int initialDisplayHeight) {
        if (rotation == 0) {
            return this;
        }
        boolean isSizeFlipped = true;
        if (!(rotation == 1 || rotation == 3)) {
            isSizeFlipped = false;
        }
        RoundedCorner[] newCorners = new RoundedCorner[4];
        for (int i = 0; i < this.mRoundedCorners.length; i++) {
            int newPosistion = getRotatedIndex(i, rotation);
            newCorners[newPosistion] = createRoundedCorner(newPosistion, this.mRoundedCorners[i].getRadius(), isSizeFlipped ? initialDisplayHeight : initialDisplayWidth, isSizeFlipped ? initialDisplayWidth : initialDisplayHeight);
        }
        return new RoundedCorners(newCorners);
    }

    private static RoundedCorner createRoundedCorner(int position, int radius, int displayWidth, int displayHeight) {
        int i = 0;
        switch (position) {
            case 0:
                return new RoundedCorner(0, radius, radius > 0 ? radius : 0, radius > 0 ? radius : 0);
            case 1:
                int i2 = radius > 0 ? displayWidth - radius : 0;
                if (radius > 0) {
                    i = radius;
                }
                return new RoundedCorner(1, radius, i2, i);
            case 2:
                int i3 = radius > 0 ? displayWidth - radius : 0;
                if (radius > 0) {
                    i = displayHeight - radius;
                }
                return new RoundedCorner(2, radius, i3, i);
            case 3:
                int i4 = radius > 0 ? radius : 0;
                if (radius > 0) {
                    i = displayHeight - radius;
                }
                return new RoundedCorner(3, radius, i4, i);
            default:
                throw new IllegalArgumentException("The position is not one of the RoundedCornerPosition =" + position);
        }
    }

    private static int getRotatedIndex(int position, int rotation) {
        return ((position - rotation) + 4) % 4;
    }

    public int hashCode() {
        RoundedCorner[] roundedCornerArr;
        int result = 0;
        for (RoundedCorner roundedCorner : this.mRoundedCorners) {
            result = (result * 31) + roundedCorner.hashCode();
        }
        return result;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RoundedCorners)) {
            return false;
        }
        RoundedCorners r = (RoundedCorners) o;
        return Arrays.deepEquals(this.mRoundedCorners, r.mRoundedCorners);
    }

    public String toString() {
        return "RoundedCorners{" + Arrays.toString(this.mRoundedCorners) + "}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (equals(NO_ROUNDED_CORNERS)) {
            dest.writeInt(0);
            return;
        }
        dest.writeInt(1);
        dest.writeTypedArray(this.mRoundedCorners, flags);
    }
}
