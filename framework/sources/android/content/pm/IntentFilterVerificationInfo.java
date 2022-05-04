package android.content.pm;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.util.TypedXmlPullParser;
import android.util.TypedXmlSerializer;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;
@SystemApi
/* loaded from: classes.dex */
public final class IntentFilterVerificationInfo implements Parcelable {
    private static final String ATTR_DOMAIN_NAME = "name";
    private static final String ATTR_PACKAGE_NAME = "packageName";
    private static final String ATTR_STATUS = "status";
    private static final String TAG_DOMAIN = "domain";
    private ArraySet<String> mDomains;
    private String mPackageName;
    private int mStatus;
    private static final String TAG = IntentFilterVerificationInfo.class.getName();
    public static final Parcelable.Creator<IntentFilterVerificationInfo> CREATOR = new Parcelable.Creator<IntentFilterVerificationInfo>() { // from class: android.content.pm.IntentFilterVerificationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntentFilterVerificationInfo createFromParcel(Parcel source) {
            return new IntentFilterVerificationInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IntentFilterVerificationInfo[] newArray(int size) {
            return new IntentFilterVerificationInfo[size];
        }
    };

    public IntentFilterVerificationInfo() {
        this.mDomains = new ArraySet<>();
        this.mPackageName = null;
        this.mStatus = 0;
    }

    public IntentFilterVerificationInfo(String packageName, ArraySet<String> domains) {
        this.mDomains = new ArraySet<>();
        this.mPackageName = packageName;
        this.mDomains = domains;
        this.mStatus = 0;
    }

    public IntentFilterVerificationInfo(TypedXmlPullParser parser) throws IOException, XmlPullParserException {
        this.mDomains = new ArraySet<>();
        readFromXml(parser);
    }

    public IntentFilterVerificationInfo(Parcel source) {
        this.mDomains = new ArraySet<>();
        readFromParcel(source);
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int s) {
        if (s < 0 || s > 3) {
            String str = TAG;
            Log.w(str, "Trying to set a non supported status: " + s);
            return;
        }
        this.mStatus = s;
    }

    public Set<String> getDomains() {
        return this.mDomains;
    }

    public void setDomains(ArraySet<String> list) {
        this.mDomains = list;
    }

    public String getDomainsString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.mDomains.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    String getStringFromXml(TypedXmlPullParser parser, String attribute, String defaultValue) {
        String value = parser.getAttributeValue(null, attribute);
        if (value != null) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Missing element under ");
        String str = TAG;
        sb.append(str);
        sb.append(": ");
        sb.append(attribute);
        sb.append(" at ");
        sb.append(parser.getPositionDescription());
        String msg = sb.toString();
        Log.w(str, msg);
        return defaultValue;
    }

    int getIntFromXml(TypedXmlPullParser parser, String attribute, int defaultValue) {
        return parser.getAttributeInt(null, attribute, defaultValue);
    }

    public void readFromXml(TypedXmlPullParser parser) throws XmlPullParserException, IOException {
        String stringFromXml = getStringFromXml(parser, "packageName", null);
        this.mPackageName = stringFromXml;
        if (stringFromXml == null) {
            Log.e(TAG, "Package name cannot be null!");
        }
        int status = getIntFromXml(parser, "status", -1);
        if (status == -1) {
            String str = TAG;
            Log.e(str, "Unknown status value: " + status);
        }
        this.mStatus = status;
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
                if (tagName.equals("domain")) {
                    String name = getStringFromXml(parser, "name", null);
                    if (!TextUtils.isEmpty(name)) {
                        this.mDomains.add(name);
                    }
                } else {
                    String str2 = TAG;
                    Log.w(str2, "Unknown tag parsing IntentFilter: " + tagName);
                }
                XmlUtils.skipCurrentTag(parser);
            }
        }
    }

    public void writeToXml(TypedXmlSerializer serializer) throws IOException {
        serializer.attribute(null, "packageName", this.mPackageName);
        serializer.attributeInt(null, "status", this.mStatus);
        Iterator<String> it = this.mDomains.iterator();
        while (it.hasNext()) {
            String str = it.next();
            serializer.startTag(null, "domain");
            serializer.attribute(null, "name", str);
            serializer.endTag(null, "domain");
        }
    }

    public String getStatusString() {
        return getStatusStringFromValue(this.mStatus << 32);
    }

    public static String getStatusStringFromValue(long val) {
        StringBuilder sb = new StringBuilder();
        switch ((int) (val >> 32)) {
            case 1:
                sb.append("ask");
                break;
            case 2:
                sb.append("always : ");
                sb.append(Long.toHexString((-1) & val));
                break;
            case 3:
                sb.append("never");
                break;
            case 4:
                sb.append("always-ask");
                break;
            default:
                sb.append("undefined");
                break;
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel source) {
        this.mPackageName = source.readString();
        this.mStatus = source.readInt();
        ArrayList<String> list = new ArrayList<>();
        source.readStringList(list);
        this.mDomains.addAll(list);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPackageName);
        dest.writeInt(this.mStatus);
        dest.writeStringList(new ArrayList(this.mDomains));
    }
}
