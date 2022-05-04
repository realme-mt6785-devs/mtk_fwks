package com.oplus;

import android.content.Context;
import android.net.Uri;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.oplus.romupdate.RomUpdateObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/* loaded from: classes4.dex */
public class OplusRomUpdateHelper {
    public static final String BROADCAST_ACTION_ROM_UPDATE_CONFIG_SUCCES = "oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS";
    private static final String COLUMN_NAME_1 = "version";
    private static final String COLUMN_NAME_2 = "xml";
    private static final String OPLUS_COMPONENT_SAFE_PERMISSION = "oplus.permission.OPLUS_COMPONENT_SAFE";
    public static final String ROM_UPDATE_CONFIG_LIST = "ROM_UPDATE_CONFIG_LIST";
    public static final String TAG = "OplusRomUpdateHelper";
    public Context mContext;
    private String mDataFilePath;
    private String mFilterName;
    private String mSystemFilePath;
    private UpdateInfo mUpdateInfo1;
    private UpdateInfo mUpdateInfo2;
    private boolean which2use = true;
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static final Uri CONTENT_URI_WHITE_LIST = Uri.parse("content://com.oplus.romupdate.provider.db/update_list");

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class UpdateInfo {
        protected long mVersion = -1;

        protected UpdateInfo() {
        }

        public void parseContentFromXML(String content) {
        }

        public boolean clone(UpdateInfo other) {
            return false;
        }

        public boolean insert(int type, String verifyStr) {
            return false;
        }

        public void clear() {
        }

        public void dump() {
        }

        public long getVersion() {
            return this.mVersion;
        }

        public boolean updateToLowerVersion(String newContent) {
            return false;
        }
    }

    public OplusRomUpdateHelper(Context context, String filterName, String systemFile, String dataFile) {
        this.mFilterName = "";
        this.mSystemFilePath = "";
        this.mDataFilePath = "";
        this.mContext = null;
        this.mContext = context;
        this.mFilterName = filterName;
        this.mSystemFilePath = systemFile;
        this.mDataFilePath = dataFile;
    }

    public void init() {
        if (this.mDataFilePath != null && this.mSystemFilePath != null) {
            File file = new File(this.mDataFilePath);
            if (!file.exists()) {
                file = new File(this.mSystemFilePath);
                if (!file.exists()) {
                    return;
                }
            }
            parseContentFromXML(readFromFile(file));
        }
    }

    protected void setUpdateInfo(UpdateInfo updateInfo1, UpdateInfo updateInfo2) {
        this.mUpdateInfo1 = updateInfo1;
        this.mUpdateInfo2 = updateInfo2;
    }

    public void initUpdateBroadcastReceiver() {
        if (!TextUtils.isEmpty(this.mFilterName)) {
            RomUpdateObserver.getInstance().register(this.mFilterName, new RomUpdateObserver.OnReceiveListener() { // from class: com.oplus.OplusRomUpdateHelper.1
                @Override // com.oplus.romupdate.RomUpdateObserver.OnReceiveListener
                public void onReceive(Context context) {
                    try {
                        if (OplusRomUpdateHelper.DEBUG) {
                            Log.d(OplusRomUpdateHelper.TAG, " onReceive " + OplusRomUpdateHelper.this.getFilterName());
                        }
                        OplusRomUpdateHelper.this.getUpdateFromProvider();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    protected UpdateInfo getUpdateInfo(boolean b) {
        return b ? this.which2use ? this.mUpdateInfo1 : this.mUpdateInfo2 : this.which2use ? this.mUpdateInfo2 : this.mUpdateInfo1;
    }

    private void setFlip() {
        this.which2use = !this.which2use;
    }

    private boolean saveToFile(String content, String filePath) {
        try {
            File file = new File(filePath);
            File parent = new File(file.getParent());
            if (!parent.isDirectory()) {
                parent.mkdirs();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getFilterName() {
        return this.mFilterName;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0070, code lost:
        if (r1 != null) goto L_0x008d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x008b, code lost:
        if (r1 == null) goto L_0x0091;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008d, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0091, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getDataFromProvider() {
        /*
            r12 = this;
            java.lang.String r0 = "OplusRomUpdateHelper"
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "version"
            java.lang.String r5 = "xml"
            java.lang.String[] r8 = new java.lang.String[]{r4, r5}
            android.content.Context r6 = r12.mContext     // Catch: all -> 0x0073, Exception -> 0x0075
            if (r6 != 0) goto L_0x001a
            if (r1 == 0) goto L_0x0019
            r1.close()
            r1 = 0
        L_0x0019:
            return r3
        L_0x001a:
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch: all -> 0x0073, Exception -> 0x0075
            android.net.Uri r7 = com.oplus.OplusRomUpdateHelper.CONTENT_URI_WHITE_LIST     // Catch: all -> 0x0073, Exception -> 0x0075
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: all -> 0x0073, Exception -> 0x0075
            r9.<init>()     // Catch: all -> 0x0073, Exception -> 0x0075
            java.lang.String r10 = "filtername=\""
            r9.append(r10)     // Catch: all -> 0x0073, Exception -> 0x0075
            java.lang.String r10 = r12.mFilterName     // Catch: all -> 0x0073, Exception -> 0x0075
            r9.append(r10)     // Catch: all -> 0x0073, Exception -> 0x0075
            java.lang.String r10 = "\""
            r9.append(r10)     // Catch: all -> 0x0073, Exception -> 0x0075
            java.lang.String r9 = r9.toString()     // Catch: all -> 0x0073, Exception -> 0x0075
            r10 = 0
            r11 = 0
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11)     // Catch: all -> 0x0073, Exception -> 0x0075
            r1 = r6
            if (r1 == 0) goto L_0x0070
            int r6 = r1.getCount()     // Catch: all -> 0x0073, Exception -> 0x0075
            if (r6 <= 0) goto L_0x0070
            int r4 = r1.getColumnIndex(r4)     // Catch: all -> 0x0073, Exception -> 0x0075
            int r5 = r1.getColumnIndex(r5)     // Catch: all -> 0x0073, Exception -> 0x0075
            r1.moveToNext()     // Catch: all -> 0x0073, Exception -> 0x0075
            int r6 = r1.getInt(r4)     // Catch: all -> 0x0073, Exception -> 0x0075
            r2 = r6
            java.lang.String r6 = r1.getString(r5)     // Catch: all -> 0x0073, Exception -> 0x0075
            r3 = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x0073, Exception -> 0x0075
            r6.<init>()     // Catch: all -> 0x0073, Exception -> 0x0075
            java.lang.String r7 = "White List updated, version = "
            r6.append(r7)     // Catch: all -> 0x0073, Exception -> 0x0075
            r6.append(r2)     // Catch: all -> 0x0073, Exception -> 0x0075
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x0073, Exception -> 0x0075
            android.util.Log.d(r0, r6)     // Catch: all -> 0x0073, Exception -> 0x0075
        L_0x0070:
            if (r1 == 0) goto L_0x0091
            goto L_0x008d
        L_0x0073:
            r0 = move-exception
            goto L_0x0092
        L_0x0075:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0073
            r5.<init>()     // Catch: all -> 0x0073
            java.lang.String r6 = "We can not get white list data from provider, because of "
            r5.append(r6)     // Catch: all -> 0x0073
            r5.append(r4)     // Catch: all -> 0x0073
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x0073
            android.util.Log.w(r0, r5)     // Catch: all -> 0x0073
            if (r1 == 0) goto L_0x0091
        L_0x008d:
            r1.close()
            r1 = 0
        L_0x0091:
            return r3
        L_0x0092:
            if (r1 == 0) goto L_0x0098
            r1.close()
            r1 = 0
        L_0x0098:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.OplusRomUpdateHelper.getDataFromProvider():java.lang.String");
    }

    public String readFromFile(File path) {
        if (path == null) {
            return "";
        }
        InputStream is = null;
        try {
            try {
                try {
                    try {
                        is = new FileInputStream(path);
                        BufferedReader in = new BufferedReader(new InputStreamReader(is));
                        StringBuffer buffer = new StringBuffer();
                        while (true) {
                            String line = in.readLine();
                            if (line == null) {
                                break;
                            }
                            buffer.append(line);
                        }
                        String stringBuffer = buffer.toString();
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return stringBuffer;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        if (is == null) {
                            return null;
                        }
                        is.close();
                        return null;
                    }
                } catch (FileNotFoundException e3) {
                    e3.printStackTrace();
                    if (is == null) {
                        return null;
                    }
                    is.close();
                    return null;
                }
            } catch (Throwable th) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    public void parseContentFromXML(String content) {
        if (getUpdateInfo(true) != null) {
            getUpdateInfo(true).parseContentFromXML(content);
        }
    }

    public void getUpdateFromProvider() {
        try {
            String tmp = getDataFromProvider();
            if (tmp == null) {
                if (DEBUG) {
                    Log.d(TAG, " getUpdateFromProvider data is null " + getFilterName());
                }
            } else if (!updateToLowerVersion(tmp)) {
                saveToFile(tmp, this.mDataFilePath);
                if (getUpdateInfo(false) != null) {
                    getUpdateInfo(false).parseContentFromXML(tmp);
                    setFlip();
                    getUpdateInfo(false).clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean updateToLowerVersion(String newContent) {
        UpdateInfo updateInfo = getUpdateInfo(true);
        if (updateInfo == null || !updateInfo.updateToLowerVersion(newContent)) {
            return false;
        }
        Log.d(TAG, "updateToLowerVersion true, " + updateInfo.hashCode());
        return true;
    }

    protected boolean insertValueInList(int type, String verifyStr) {
        if (!getUpdateInfo(false).clone(getUpdateInfo(true)) || !getUpdateInfo(false).insert(type, verifyStr)) {
            log("Failed to insert!");
            return false;
        }
        setFlip();
        getUpdateInfo(false).clear();
        return true;
    }

    public void dump() {
        log("which2use = " + this.which2use);
        this.mUpdateInfo1.dump();
        this.mUpdateInfo2.dump();
    }

    public void log(String log) {
        if (DEBUG) {
            Log.d(TAG, log);
        }
    }

    public void log(String log, Exception e) {
        if (DEBUG) {
            Log.d(TAG, log);
        }
    }
}
