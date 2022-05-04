package android.nfc.cardemulation;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.util.Log;
/* loaded from: classes2.dex */
public class OplusBaseApduServiceInfo {
    private static final String TAG = "OplusBaseApduServiceInfo";
    protected int mServiceState = 2;
    byte[] mByteArrayBanner = {0};

    public void setUpData(int serviceState, byte[] banner) {
        this.mServiceState = serviceState;
        this.mByteArrayBanner = banner;
    }

    public boolean isServiceEnabled(String category) {
        int i;
        return !"other".equals(category) || (i = this.mServiceState) == 1 || i == 3;
    }

    public int setServiceState(String category, int state) {
        if (category != "other") {
            return 1;
        }
        this.mServiceState = state;
        return state;
    }

    public Bitmap getBitmapBanner() {
        if (this.mByteArrayBanner == null) {
            return null;
        }
        Log.d(TAG, "getBitmapBanner++");
        byte[] bArr = this.mByteArrayBanner;
        Bitmap bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        return bitmap;
    }

    public Drawable loadBanner(PackageManager pm, int bannerResourceId, String packageName) throws Resources.NotFoundException, PackageManager.NameNotFoundException {
        Resources res = pm.getResourcesForApplication(packageName);
        if (bannerResourceId == -1) {
            Drawable banner = new BitmapDrawable(getBitmapBanner());
            return banner;
        }
        Drawable banner2 = res.getDrawable(bannerResourceId);
        return banner2;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mServiceState);
        dest.writeByteArray(this.mByteArrayBanner);
    }

    public void readFromParcel(Parcel in) {
        this.mServiceState = in.readInt();
        this.mByteArrayBanner = in.createByteArray();
    }
}
