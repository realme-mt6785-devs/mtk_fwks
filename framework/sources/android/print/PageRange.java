package android.print;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public final class PageRange implements Parcelable {
    public static final PageRange ALL_PAGES;
    public static final PageRange[] ALL_PAGES_ARRAY;
    public static final Parcelable.Creator<PageRange> CREATOR = new Parcelable.Creator<PageRange>() { // from class: android.print.PageRange.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PageRange createFromParcel(Parcel parcel) {
            return new PageRange(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PageRange[] newArray(int size) {
            return new PageRange[size];
        }
    };
    private final int mEnd;
    private final int mStart;

    static {
        PageRange pageRange = new PageRange(0, Integer.MAX_VALUE);
        ALL_PAGES = pageRange;
        ALL_PAGES_ARRAY = new PageRange[]{pageRange};
    }

    public PageRange(int start, int end) {
        if (start < 0) {
            throw new IllegalArgumentException("start cannot be less than zero.");
        } else if (end < 0) {
            throw new IllegalArgumentException("end cannot be less than zero.");
        } else if (start <= end) {
            this.mStart = start;
            this.mEnd = end;
        } else {
            throw new IllegalArgumentException("start must be lesser than end.");
        }
    }

    private PageRange(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt());
    }

    public int getStart() {
        return this.mStart;
    }

    public int getEnd() {
        return this.mEnd;
    }

    public boolean contains(int pageIndex) {
        return pageIndex >= this.mStart && pageIndex <= this.mEnd;
    }

    public int getSize() {
        return (this.mEnd - this.mStart) + 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mStart);
        parcel.writeInt(this.mEnd);
    }

    public int hashCode() {
        int result = (1 * 31) + this.mEnd;
        return (result * 31) + this.mStart;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PageRange other = (PageRange) obj;
        if (this.mEnd == other.mEnd && this.mStart == other.mStart) {
            return true;
        }
        return false;
    }

    public String toString() {
        if (this.mStart == 0 && this.mEnd == Integer.MAX_VALUE) {
            return "PageRange[<all pages>]";
        }
        return "PageRange[" + this.mStart + " - " + this.mEnd + "]";
    }
}
