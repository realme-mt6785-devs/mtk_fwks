package android.window;

import android.app.ActivityManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.security.keystore.KeyProperties;
import android.telecom.Logging.Session;
import android.view.SurfaceControl;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public final class TransitionInfo implements Parcelable {
    public static final Parcelable.Creator<TransitionInfo> CREATOR = new Parcelable.Creator<TransitionInfo>() { // from class: android.window.TransitionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransitionInfo createFromParcel(Parcel in) {
            return new TransitionInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransitionInfo[] newArray(int size) {
            return new TransitionInfo[size];
        }
    };
    public static final int FLAG_FIRST_CUSTOM = 32;
    public static final int FLAG_IS_VOICE_INTERACTION = 16;
    public static final int FLAG_IS_WALLPAPER = 2;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_SHOW_WALLPAPER = 1;
    public static final int FLAG_STARTING_WINDOW_TRANSFER_RECIPIENT = 8;
    public static final int FLAG_TRANSLUCENT = 4;
    private final ArrayList<Change> mChanges;
    private final int mFlags;
    private SurfaceControl mRootLeash;
    private final Point mRootOffset;
    private final int mType;

    /* loaded from: classes3.dex */
    public @interface ChangeFlags {
    }

    /* loaded from: classes3.dex */
    public @interface TransitionMode {
    }

    public TransitionInfo(int type, int flags) {
        this.mChanges = new ArrayList<>();
        this.mRootOffset = new Point();
        this.mType = type;
        this.mFlags = flags;
    }

    private TransitionInfo(Parcel in) {
        ArrayList<Change> arrayList = new ArrayList<>();
        this.mChanges = arrayList;
        Point point = new Point();
        this.mRootOffset = point;
        this.mType = in.readInt();
        this.mFlags = in.readInt();
        in.readList(arrayList, null);
        SurfaceControl surfaceControl = new SurfaceControl();
        this.mRootLeash = surfaceControl;
        surfaceControl.readFromParcel(in);
        point.readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mType);
        dest.writeInt(this.mFlags);
        dest.writeList(this.mChanges);
        this.mRootLeash.writeToParcel(dest, flags);
        this.mRootOffset.writeToParcel(dest, flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setRootLeash(SurfaceControl leash, int offsetLeft, int offsetTop) {
        this.mRootLeash = leash;
        this.mRootOffset.set(offsetLeft, offsetTop);
    }

    public int getType() {
        return this.mType;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public SurfaceControl getRootLeash() {
        SurfaceControl surfaceControl = this.mRootLeash;
        if (surfaceControl != null) {
            return surfaceControl;
        }
        throw new IllegalStateException("Trying to get a leash which wasn't set");
    }

    public Point getRootOffset() {
        return this.mRootOffset;
    }

    public List<Change> getChanges() {
        return this.mChanges;
    }

    public Change getChange(WindowContainerToken token) {
        for (int i = this.mChanges.size() - 1; i >= 0; i--) {
            if (token.equals(this.mChanges.get(i).mContainer)) {
                return this.mChanges.get(i);
            }
        }
        return null;
    }

    public void addChange(Change change) {
        this.mChanges.add(change);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{t=" + WindowManager.transitTypeToString(this.mType) + " f=" + Integer.toHexString(this.mFlags) + " ro=" + this.mRootOffset + " c=[");
        for (int i = 0; i < this.mChanges.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(this.mChanges.get(i));
        }
        sb.append("]}");
        return sb.toString();
    }

    public static String modeToString(int mode) {
        switch (mode) {
            case 0:
                return KeyProperties.DIGEST_NONE;
            case 1:
                return "OPEN";
            case 2:
                return "CLOSE";
            case 3:
                return "SHOW";
            case 4:
                return "HIDE";
            case 5:
            default:
                return "<unknown:" + mode + ">";
            case 6:
                return "CHANGE";
        }
    }

    public static String flagsToString(int flags) {
        if (flags == 0) {
            return KeyProperties.DIGEST_NONE;
        }
        StringBuilder sb = new StringBuilder();
        if ((flags & 1) != 0) {
            sb.append("SHOW_WALLPAPER");
        }
        if ((flags & 2) != 0) {
            sb.append("IS_WALLPAPER");
        }
        String str = "";
        if ((flags & 4) != 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(sb.length() == 0 ? str : "|");
            sb2.append("TRANSLUCENT");
            sb.append(sb2.toString());
        }
        if ((flags & 8) != 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb.length() == 0 ? str : "|");
            sb3.append("STARTING_WINDOW_TRANSFER");
            sb.append(sb3.toString());
        }
        if ((flags & 16) != 0) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb.length() == 0 ? str : "|");
            sb4.append("IS_VOICE_INTERACTION");
            sb.append(sb4.toString());
        }
        if ((flags & 32) != 0) {
            StringBuilder sb5 = new StringBuilder();
            if (sb.length() != 0) {
                str = "|";
            }
            sb5.append(str);
            sb5.append("FIRST_CUSTOM");
            sb.append(sb5.toString());
        }
        return sb.toString();
    }

    public static boolean isIndependent(Change change, TransitionInfo info) {
        if (change.getParent() == null) {
            return true;
        }
        if (change.getMode() == 6) {
            return false;
        }
        Change parentChg = info.getChange(change.getParent());
        while (parentChg != null && parentChg.getMode() == 6) {
            if (parentChg.getParent() == null) {
                return true;
            }
            parentChg = info.getChange(parentChg.getParent());
        }
        return false;
    }

    /* loaded from: classes3.dex */
    public static final class Change implements Parcelable {
        public static final Parcelable.Creator<Change> CREATOR = new Parcelable.Creator<Change>() { // from class: android.window.TransitionInfo.Change.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Change createFromParcel(Parcel in) {
                return new Change(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Change[] newArray(int size) {
                return new Change[size];
            }
        };
        private final WindowContainerToken mContainer;
        private final Rect mEndAbsBounds;
        private final Point mEndRelOffset;
        private int mEndRotation;
        private int mFlags;
        private final SurfaceControl mLeash;
        private int mMode;
        private WindowContainerToken mParent;
        private final Rect mStartAbsBounds;
        private int mStartRotation;
        private ActivityManager.RunningTaskInfo mTaskInfo;

        public Change(WindowContainerToken container, SurfaceControl leash) {
            this.mMode = 0;
            this.mFlags = 0;
            this.mStartAbsBounds = new Rect();
            this.mEndAbsBounds = new Rect();
            this.mEndRelOffset = new Point();
            this.mTaskInfo = null;
            this.mStartRotation = -1;
            this.mEndRotation = -1;
            this.mContainer = container;
            this.mLeash = leash;
        }

        private Change(Parcel in) {
            this.mMode = 0;
            this.mFlags = 0;
            Rect rect = new Rect();
            this.mStartAbsBounds = rect;
            Rect rect2 = new Rect();
            this.mEndAbsBounds = rect2;
            Point point = new Point();
            this.mEndRelOffset = point;
            this.mTaskInfo = null;
            this.mStartRotation = -1;
            this.mEndRotation = -1;
            this.mContainer = (WindowContainerToken) in.readTypedObject(WindowContainerToken.CREATOR);
            this.mParent = (WindowContainerToken) in.readTypedObject(WindowContainerToken.CREATOR);
            SurfaceControl surfaceControl = new SurfaceControl();
            this.mLeash = surfaceControl;
            surfaceControl.readFromParcel(in);
            this.mMode = in.readInt();
            this.mFlags = in.readInt();
            rect.readFromParcel(in);
            rect2.readFromParcel(in);
            point.readFromParcel(in);
            this.mTaskInfo = (ActivityManager.RunningTaskInfo) in.readTypedObject(ActivityManager.RunningTaskInfo.CREATOR);
            this.mStartRotation = in.readInt();
            this.mEndRotation = in.readInt();
        }

        public void setParent(WindowContainerToken parent) {
            this.mParent = parent;
        }

        public void setMode(int mode) {
            this.mMode = mode;
        }

        public void setFlags(int flags) {
            this.mFlags = flags;
        }

        public void setStartAbsBounds(Rect rect) {
            this.mStartAbsBounds.set(rect);
        }

        public void setEndAbsBounds(Rect rect) {
            this.mEndAbsBounds.set(rect);
        }

        public void setEndRelOffset(int left, int top) {
            this.mEndRelOffset.set(left, top);
        }

        public void setTaskInfo(ActivityManager.RunningTaskInfo taskInfo) {
            this.mTaskInfo = taskInfo;
        }

        public void setRotation(int start, int end) {
            this.mStartRotation = start;
            this.mEndRotation = end;
        }

        public WindowContainerToken getContainer() {
            return this.mContainer;
        }

        public WindowContainerToken getParent() {
            return this.mParent;
        }

        public int getMode() {
            return this.mMode;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public Rect getStartAbsBounds() {
            return this.mStartAbsBounds;
        }

        public Rect getEndAbsBounds() {
            return this.mEndAbsBounds;
        }

        public Point getEndRelOffset() {
            return this.mEndRelOffset;
        }

        public SurfaceControl getLeash() {
            return this.mLeash;
        }

        public ActivityManager.RunningTaskInfo getTaskInfo() {
            return this.mTaskInfo;
        }

        public int getStartRotation() {
            return this.mStartRotation;
        }

        public int getEndRotation() {
            return this.mEndRotation;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedObject(this.mContainer, flags);
            dest.writeTypedObject(this.mParent, flags);
            this.mLeash.writeToParcel(dest, flags);
            dest.writeInt(this.mMode);
            dest.writeInt(this.mFlags);
            this.mStartAbsBounds.writeToParcel(dest, flags);
            this.mEndAbsBounds.writeToParcel(dest, flags);
            this.mEndRelOffset.writeToParcel(dest, flags);
            dest.writeTypedObject(this.mTaskInfo, flags);
            dest.writeInt(this.mStartRotation);
            dest.writeInt(this.mEndRotation);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "{" + this.mContainer + "(" + this.mParent + ") leash=" + this.mLeash + " m=" + TransitionInfo.modeToString(this.mMode) + " f=" + TransitionInfo.flagsToString(this.mFlags) + " sb=" + this.mStartAbsBounds + " eb=" + this.mEndAbsBounds + " eo=" + this.mEndRelOffset + " r=" + this.mStartRotation + Session.SUBSESSION_SEPARATION_CHAR + this.mEndRotation + "}";
        }
    }
}
