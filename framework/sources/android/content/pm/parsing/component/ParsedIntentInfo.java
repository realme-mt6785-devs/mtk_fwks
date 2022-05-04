package android.content.pm.parsing.component;

import android.content.IntentFilter;
import android.os.Parcel;
import android.util.Pair;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class ParsedIntentInfo extends IntentFilter {
    public static final Parceler PARCELER = new Parceler();
    boolean hasDefault;
    int icon;
    int labelRes;
    CharSequence nonLocalizedLabel;

    /* loaded from: classes.dex */
    public static class Parceler implements Parcelling<ParsedIntentInfo> {
        public void parcel(ParsedIntentInfo item, Parcel dest, int parcelFlags) {
            item.writeIntentInfoToParcel(dest, parcelFlags);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.android.internal.util.Parcelling
        public ParsedIntentInfo unparcel(Parcel source) {
            return new ParsedIntentInfo(source);
        }
    }

    /* loaded from: classes.dex */
    public static class ListParceler implements Parcelling<List<ParsedIntentInfo>> {
        public void parcel(List<ParsedIntentInfo> item, Parcel dest, int parcelFlags) {
            if (item == null) {
                dest.writeInt(-1);
                return;
            }
            int size = item.size();
            dest.writeInt(size);
            for (int index = 0; index < size; index++) {
                ParsedIntentInfo.PARCELER.parcel(item.get(index), dest, parcelFlags);
            }
        }

        @Override // com.android.internal.util.Parcelling
        public List<ParsedIntentInfo> unparcel(Parcel source) {
            int size = source.readInt();
            if (size == -1) {
                return null;
            }
            if (size == 0) {
                return new ArrayList(0);
            }
            ArrayList<ParsedIntentInfo> intentsList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                intentsList.add(ParsedIntentInfo.PARCELER.unparcel(source));
            }
            return intentsList;
        }
    }

    /* loaded from: classes.dex */
    public static class StringPairListParceler implements Parcelling<List<Pair<String, ParsedIntentInfo>>> {
        public void parcel(List<Pair<String, ParsedIntentInfo>> item, Parcel dest, int parcelFlags) {
            if (item == null) {
                dest.writeInt(-1);
                return;
            }
            int size = item.size();
            dest.writeInt(size);
            for (int index = 0; index < size; index++) {
                Pair<String, ParsedIntentInfo> pair = item.get(index);
                dest.writeString(pair.first);
                ParsedIntentInfo.PARCELER.parcel(pair.second, dest, parcelFlags);
            }
        }

        @Override // com.android.internal.util.Parcelling
        public List<Pair<String, ParsedIntentInfo>> unparcel(Parcel source) {
            int size = source.readInt();
            if (size == -1) {
                return null;
            }
            if (size == 0) {
                return new ArrayList(0);
            }
            List<Pair<String, ParsedIntentInfo>> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(Pair.create(source.readString(), ParsedIntentInfo.PARCELER.unparcel(source)));
            }
            return list;
        }
    }

    public ParsedIntentInfo() {
    }

    public ParsedIntentInfo(Parcel in) {
        super(in);
        this.hasDefault = in.readBoolean();
        this.labelRes = in.readInt();
        this.nonLocalizedLabel = in.readCharSequence();
        this.icon = in.readInt();
    }

    public void writeIntentInfoToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeBoolean(this.hasDefault);
        dest.writeInt(this.labelRes);
        dest.writeCharSequence(this.nonLocalizedLabel);
        dest.writeInt(this.icon);
    }

    public String toString() {
        return "ParsedIntentInfo{" + Integer.toHexString(System.identityHashCode(this)) + '}';
    }

    public boolean isHasDefault() {
        return this.hasDefault;
    }

    public int getLabelRes() {
        return this.labelRes;
    }

    public CharSequence getNonLocalizedLabel() {
        return this.nonLocalizedLabel;
    }

    public int getIcon() {
        return this.icon;
    }
}
