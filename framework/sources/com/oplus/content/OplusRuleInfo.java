package com.oplus.content;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.util.PrintWriterPrinter;
import android.util.Slog;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
/* loaded from: classes4.dex */
public final class OplusRuleInfo implements Parcelable {
    private static final String BLACK_STR = "black";
    private static final int CHOICE_BLACK = 1;
    private static final int CHOICE_INVALID = -1;
    private static final int CHOICE_WHITE = 0;
    public static final Parcelable.Creator<OplusRuleInfo> CREATOR = new Parcelable.Creator<OplusRuleInfo>() { // from class: com.oplus.content.OplusRuleInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusRuleInfo createFromParcel(Parcel source) {
            return new OplusRuleInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OplusRuleInfo[] newArray(int size) {
            return new OplusRuleInfo[size];
        }
    };
    private static boolean DEBUG = false;
    private static boolean DEBUG_PANIC = false;
    private static final String INVALID_STR = "invalid";
    private static final String NAME_STR = "name";
    private static final String TAG = "CII_OplusRuleInfo";
    private static final String TAG_INTENT_FILETER = "intent-filter";
    private static final String TAG_SOURCE_PKG_BLACK = "source-pkg-black";
    private static final String TAG_SOURCE_PKG_CHOICE = "source-pkg-choice";
    private static final String TAG_SOURCE_PKG_WHITE = "source-pkg-white";
    private static final String TAG_TARGET_CPN = "target-cpn";
    private static final String WHITE_STR = "white";
    public IntentFilter mIntentFilter;
    public List<String> mSourcePkgBlackList;
    public int mSourcePkgChoice;
    public List<String> mSourcePkgWhiteList;
    public List<String> mTargetComponentList;

    static {
        boolean z = SystemProperties.getBoolean("persist.sys.assert.panic", false);
        DEBUG_PANIC = z;
        DEBUG = z;
    }

    public static void setDebugEnable(boolean enable) {
        DEBUG = enable;
    }

    private static String getStringByChoice(int choice) {
        switch (choice) {
            case -1:
                return INVALID_STR;
            case 0:
                return WHITE_STR;
            case 1:
                return BLACK_STR;
            default:
                return INVALID_STR;
        }
    }

    private static int getIntByChoice(String choice) {
        if (WHITE_STR.equals(choice)) {
            return 0;
        }
        if (BLACK_STR.equals(choice)) {
            return 1;
        }
        INVALID_STR.equals(choice);
        return -1;
    }

    public OplusRuleInfo() {
        this.mSourcePkgChoice = -1;
        this.mTargetComponentList = new ArrayList();
        this.mSourcePkgWhiteList = new ArrayList();
        this.mSourcePkgBlackList = new ArrayList();
    }

    public OplusRuleInfo(OplusRuleInfo cri) {
        this.mSourcePkgChoice = -1;
        if (cri != null) {
            this.mTargetComponentList = new ArrayList(cri.mTargetComponentList);
            this.mSourcePkgChoice = cri.mSourcePkgChoice;
            this.mSourcePkgWhiteList = new ArrayList(cri.mSourcePkgWhiteList);
            this.mSourcePkgBlackList = new ArrayList(cri.mSourcePkgBlackList);
            this.mIntentFilter = new IntentFilter(cri.mIntentFilter);
        }
    }

    public OplusRuleInfo(List<String> targetCpnList, int choice, List<String> sourcePkgWhiteList, List<String> sourcePkgBlackList, IntentFilter intentfilter) {
        this.mSourcePkgChoice = -1;
        this.mTargetComponentList = targetCpnList;
        this.mSourcePkgChoice = choice;
        this.mSourcePkgWhiteList = sourcePkgWhiteList;
        this.mSourcePkgBlackList = sourcePkgBlackList;
        this.mIntentFilter = intentfilter;
    }

    public boolean needIntercept(String cpn, String callingPkg, Intent intent) {
        boolean intercept = matchTargetCpn(cpn) && matchSourcePkg(callingPkg) && matchIntent(intent);
        if (DEBUG) {
            Slog.i(TAG, "needIntercept intercept = " + intercept);
        }
        return intercept;
    }

    private boolean matchTargetCpn(String cpn) {
        boolean match = false;
        List<String> list = this.mTargetComponentList;
        if (list != null && list.contains(cpn)) {
            match = true;
        }
        if (DEBUG) {
            Slog.i(TAG, "matchTargetCpn cpn = " + cpn + "match = " + match);
        }
        return match;
    }

    private boolean matchSourcePkg(String callingPkg) {
        boolean match = false;
        int i = this.mSourcePkgChoice;
        if (i == 0) {
            List<String> list = this.mSourcePkgWhiteList;
            if (list != null && list.contains(callingPkg)) {
                match = true;
            }
        } else if (i == 1) {
            List<String> list2 = this.mSourcePkgBlackList;
            if (list2 != null && !list2.contains(callingPkg)) {
                match = true;
            }
        } else {
            match = false;
        }
        if (DEBUG) {
            Slog.i(TAG, "matchSourcePkg callingPkg = " + callingPkg + " choice = " + getStringByChoice(this.mSourcePkgChoice) + " match = " + match);
        }
        return match;
    }

    private boolean matchIntent(Intent intent) {
        boolean matchAction = true;
        boolean matchScheme = true;
        IntentFilter intentFilter = this.mIntentFilter;
        if (!(intentFilter == null || intent == null)) {
            if (intentFilter.countActions() > 0) {
                matchAction = this.mIntentFilter.matchAction(intent.getAction());
            }
            if (this.mIntentFilter.countDataSchemes() > 0) {
                matchScheme = intent.getData() != null ? this.mIntentFilter.hasDataScheme(intent.getData().getScheme()) : false;
            }
        }
        boolean match = matchAction && matchScheme;
        if (DEBUG) {
            Slog.i(TAG, "matchIntent matchAction = " + matchAction + " matchScheme = " + matchScheme);
        }
        return match;
    }

    public OplusRuleInfo(Parcel source) {
        this.mSourcePkgChoice = -1;
        int length = source.readInt();
        this.mTargetComponentList = new ArrayList();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                this.mTargetComponentList.add(source.readString());
            }
        }
        int i2 = source.readInt();
        this.mSourcePkgChoice = i2;
        int length2 = source.readInt();
        this.mSourcePkgWhiteList = new ArrayList();
        if (length2 > 0) {
            for (int i3 = 0; i3 < length2; i3++) {
                this.mSourcePkgWhiteList.add(source.readString());
            }
        }
        int length3 = source.readInt();
        this.mSourcePkgBlackList = new ArrayList();
        if (length3 > 0) {
            for (int i4 = 0; i4 < length3; i4++) {
                this.mSourcePkgBlackList.add(source.readString());
            }
        }
        int i5 = source.readInt();
        if (i5 != 0) {
            this.mIntentFilter = IntentFilter.CREATOR.createFromParcel(source);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        List<String> list = this.mTargetComponentList;
        if (list != null) {
            int N = list.size();
            dest.writeInt(N);
            for (int i = 0; i < N; i++) {
                dest.writeString(this.mTargetComponentList.get(i));
            }
        } else {
            dest.writeInt(0);
        }
        dest.writeInt(this.mSourcePkgChoice);
        List<String> list2 = this.mSourcePkgWhiteList;
        if (list2 != null) {
            int N2 = list2.size();
            dest.writeInt(N2);
            for (int i2 = 0; i2 < N2; i2++) {
                dest.writeString(this.mSourcePkgWhiteList.get(i2));
            }
        } else {
            dest.writeInt(0);
        }
        List<String> list3 = this.mSourcePkgBlackList;
        if (list3 != null) {
            int N3 = list3.size();
            dest.writeInt(N3);
            for (int i3 = 0; i3 < N3; i3++) {
                dest.writeString(this.mSourcePkgBlackList.get(i3));
            }
        } else {
            dest.writeInt(0);
        }
        if (this.mIntentFilter != null) {
            dest.writeInt(1);
            this.mIntentFilter.writeToParcel(dest, flags);
            return;
        }
        dest.writeInt(0);
    }

    public void writeToXml(XmlSerializer serializer) throws IOException {
        List<String> list = this.mTargetComponentList;
        if (list != null && list.size() > 0) {
            for (String cpn : this.mTargetComponentList) {
                serializer.startTag(null, TAG_TARGET_CPN);
                serializer.attribute(null, "name", cpn);
                serializer.endTag(null, TAG_TARGET_CPN);
            }
        }
        serializer.startTag(null, TAG_SOURCE_PKG_CHOICE);
        serializer.attribute(null, "name", getStringByChoice(this.mSourcePkgChoice));
        serializer.endTag(null, TAG_SOURCE_PKG_CHOICE);
        List<String> list2 = this.mSourcePkgWhiteList;
        if (list2 != null && list2.size() > 0) {
            for (String pkgName : this.mSourcePkgWhiteList) {
                serializer.startTag(null, TAG_SOURCE_PKG_WHITE);
                serializer.attribute(null, "name", pkgName);
                serializer.endTag(null, TAG_SOURCE_PKG_WHITE);
            }
        }
        List<String> list3 = this.mSourcePkgBlackList;
        if (list3 != null && list3.size() > 0) {
            for (String pkgName2 : this.mSourcePkgBlackList) {
                serializer.startTag(null, TAG_SOURCE_PKG_BLACK);
                serializer.attribute(null, "name", pkgName2);
                serializer.endTag(null, TAG_SOURCE_PKG_BLACK);
            }
        }
        if (this.mIntentFilter != null) {
            serializer.startTag(null, TAG_INTENT_FILETER);
            this.mIntentFilter.writeToXml(serializer);
            serializer.endTag(null, TAG_INTENT_FILETER);
        }
    }

    public void readFromXml(XmlPullParser parser) throws XmlPullParserException, IOException {
        int outerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1) {
                return;
            }
            if (type == 3 && parser.getDepth() <= outerDepth) {
                return;
            }
            if (!(type == 3 || type == 4)) {
                String tagName = parser.getName();
                if (DEBUG) {
                    Slog.i(TAG, "readFromXml tagName = " + tagName);
                }
                if (tagName.equals(TAG_TARGET_CPN)) {
                    String name = parser.getAttributeValue(null, "name");
                    addTargetCpn(name);
                } else if (tagName.equals(TAG_SOURCE_PKG_CHOICE)) {
                    String name2 = parser.getAttributeValue(null, "name");
                    setSourcePkgChoice(name2);
                } else if (tagName.equals(TAG_SOURCE_PKG_WHITE)) {
                    String name3 = parser.getAttributeValue(null, "name");
                    addSourcePkgWhite(name3);
                } else if (tagName.equals(TAG_SOURCE_PKG_BLACK)) {
                    String name4 = parser.getAttributeValue(null, "name");
                    addSourcePkgBlack(name4);
                } else if (tagName.equals(TAG_INTENT_FILETER)) {
                    IntentFilter filter = new IntentFilter();
                    filter.readFromXml(parser);
                    setIntentFilter(filter);
                } else {
                    Slog.e(TAG, "Unknown tag parsing OplusRuleInfo : " + tagName);
                    XmlUtils.skipCurrentTag(parser);
                }
            }
        }
    }

    private void addTargetCpn(String cpn) {
        if (DEBUG) {
            Slog.i(TAG, "addTargetCpn cpn = " + cpn);
        }
        if (this.mTargetComponentList == null) {
            this.mTargetComponentList = new ArrayList();
        }
        if (!this.mTargetComponentList.contains(cpn)) {
            this.mTargetComponentList.add(cpn);
        }
    }

    private void setSourcePkgChoice(String choice) {
        if (DEBUG) {
            Slog.i(TAG, "setSourcePkgChoice choice = " + choice);
        }
        this.mSourcePkgChoice = getIntByChoice(choice);
    }

    private void addSourcePkgWhite(String name) {
        if (DEBUG) {
            Slog.i(TAG, "addSourcePkgWhite name = " + name);
        }
        if (this.mSourcePkgWhiteList == null) {
            this.mSourcePkgWhiteList = new ArrayList();
        }
        if (!this.mSourcePkgWhiteList.contains(name)) {
            this.mSourcePkgWhiteList.add(name);
        }
    }

    private void addSourcePkgBlack(String name) {
        if (DEBUG) {
            Slog.i(TAG, "addSourcePkgBlack name = " + name);
        }
        if (this.mSourcePkgBlackList == null) {
            this.mSourcePkgBlackList = new ArrayList();
        }
        if (!this.mSourcePkgBlackList.contains(name)) {
            this.mSourcePkgBlackList.add(name);
        }
    }

    private void setIntentFilter(IntentFilter filter) {
        if (DEBUG) {
            Slog.i(TAG, "setIntentFilter filter = " + filter);
        }
        this.mIntentFilter = filter;
    }

    public String toString() {
        StringBuilder b = new StringBuilder(128);
        toShortString(b);
        return b.toString();
    }

    private void toShortString(StringBuilder b) {
        if (this.mTargetComponentList != null) {
            b.append(" targetcpn = [");
            for (String name : this.mTargetComponentList) {
                b.append(" " + name + " ");
            }
            b.append("] ");
        }
        b.append(" choice = " + getStringByChoice(this.mSourcePkgChoice));
        if (this.mSourcePkgWhiteList != null) {
            b.append(" sourcewhite = [");
            for (String name2 : this.mSourcePkgWhiteList) {
                b.append(" " + name2 + " ");
            }
            b.append("] ");
        }
        if (this.mSourcePkgBlackList != null) {
            b.append(" sourceblack = [");
            for (String name3 : this.mSourcePkgBlackList) {
                b.append(" " + name3 + " ");
            }
            b.append("] ");
        }
        IntentFilter intentFilter = this.mIntentFilter;
        if (intentFilter != null) {
            b.append(intentFilter.toString());
        }
    }

    public void dump(PrintWriter pw, String prefix) {
        pw.print(prefix);
        pw.println("DEBUG = " + DEBUG);
        List<String> list = this.mTargetComponentList;
        if (list != null) {
            for (String name : list) {
                pw.print(prefix);
                pw.println("targetCpn = " + name);
            }
        }
        pw.print(prefix);
        pw.println("Choice = " + getStringByChoice(this.mSourcePkgChoice));
        List<String> list2 = this.mSourcePkgWhiteList;
        if (list2 != null) {
            for (String name2 : list2) {
                pw.print(prefix);
                pw.println("sourcePkgWhite = " + name2);
            }
        }
        List<String> list3 = this.mSourcePkgBlackList;
        if (list3 != null) {
            for (String name3 : list3) {
                pw.print(prefix);
                pw.println("sourcePkgBlack = " + name3);
            }
        }
        pw.print(prefix);
        pw.println("Intent-Filter:");
        IntentFilter intentFilter = this.mIntentFilter;
        if (intentFilter != null) {
            PrintWriterPrinter printWriterPrinter = new PrintWriterPrinter(pw);
            intentFilter.dump(printWriterPrinter, prefix + "  ");
        }
    }
}
