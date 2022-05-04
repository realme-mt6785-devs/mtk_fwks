package android.service.contentcapture;

import android.Manifest;
import android.app.AppGlobals;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Slog;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import java.io.PrintWriter;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes3.dex */
public final class ContentCaptureServiceInfo {
    private static final String TAG = ContentCaptureServiceInfo.class.getSimpleName();
    private static final String XML_TAG_SERVICE = "content-capture-service";
    private final ServiceInfo mServiceInfo;
    private final String mSettingsActivity;

    private static ServiceInfo getServiceInfoOrThrow(ComponentName comp, boolean isTemp, int userId) throws PackageManager.NameNotFoundException {
        int flags = 128;
        if (!isTemp) {
            flags = 128 | 1048576;
        }
        ServiceInfo si = null;
        try {
            si = AppGlobals.getPackageManager().getServiceInfo(comp, flags, userId);
        } catch (RemoteException e) {
        }
        if (si != null) {
            return si;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not get serviceInfo for ");
        sb.append(isTemp ? " (temp)" : "(default system)");
        sb.append(" ");
        sb.append(comp.flattenToShortString());
        throw new PackageManager.NameNotFoundException(sb.toString());
    }

    public ContentCaptureServiceInfo(Context context, ComponentName comp, boolean isTemporaryService, int userId) throws PackageManager.NameNotFoundException {
        this(context, getServiceInfoOrThrow(comp, isTemporaryService, userId));
    }

    private ContentCaptureServiceInfo(Context context, ServiceInfo si) {
        if (Manifest.permission.BIND_CONTENT_CAPTURE_SERVICE.equals(si.permission)) {
            this.mServiceInfo = si;
            XmlResourceParser parser = si.loadXmlMetaData(context.getPackageManager(), ContentCaptureService.SERVICE_META_DATA);
            if (parser == null) {
                this.mSettingsActivity = null;
                return;
            }
            String settingsActivity = null;
            try {
                Resources resources = context.getPackageManager().getResourcesForApplication(si.applicationInfo);
                for (int type = 0; type != 1 && type != 2; type = parser.next()) {
                }
                if (XML_TAG_SERVICE.equals(parser.getName())) {
                    AttributeSet allAttributes = Xml.asAttributeSet(parser);
                    TypedArray afsAttributes = resources.obtainAttributes(allAttributes, R.styleable.ContentCaptureService);
                    settingsActivity = afsAttributes.getString(0);
                    if (afsAttributes != null) {
                        afsAttributes.recycle();
                    }
                } else {
                    Log.e(TAG, "Meta-data does not start with content-capture-service tag");
                }
            } catch (PackageManager.NameNotFoundException | IOException | XmlPullParserException e) {
                Log.e(TAG, "Error parsing auto fill service meta-data", e);
            }
            this.mSettingsActivity = settingsActivity;
            return;
        }
        String str = TAG;
        Slog.w(str, "ContentCaptureService from '" + si.packageName + "' does not require permission " + Manifest.permission.BIND_CONTENT_CAPTURE_SERVICE);
        throw new SecurityException("Service does not require permission android.permission.BIND_CONTENT_CAPTURE_SERVICE");
    }

    public ServiceInfo getServiceInfo() {
        return this.mServiceInfo;
    }

    public String getSettingsActivity() {
        return this.mSettingsActivity;
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.mServiceInfo + ", settings:" + this.mSettingsActivity;
    }

    public void dump(String prefix, PrintWriter pw) {
        pw.print(prefix);
        pw.print("Component: ");
        pw.println(getServiceInfo().getComponentName());
        pw.print(prefix);
        pw.print("Settings: ");
        pw.println(this.mSettingsActivity);
    }
}
