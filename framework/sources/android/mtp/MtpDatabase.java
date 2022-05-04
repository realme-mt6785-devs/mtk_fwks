package android.mtp;

import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.ApplicationMediaCapabilities;
import android.media.ExifInterface;
import android.media.MediaFormat;
import android.media.ThumbnailUtils;
import android.mtp.MtpStorageManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.storage.StorageVolume;
import android.provider.DeviceConfig;
import android.provider.MediaStore;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.collect.Sets;
import dalvik.system.CloseGuard;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
/* loaded from: classes2.dex */
public class MtpDatabase implements AutoCloseable {
    private static final int MAX_THUMB_SIZE = 204800;
    private static final String NO_MEDIA = ".nomedia";
    private static final String PATH_WHERE = "_data=?";
    private int mBatteryLevel;
    private int mBatteryScale;
    private final CloseGuard mCloseGuard;
    private final Context mContext;
    private SharedPreferences mDeviceProperties;
    private int mDeviceType;
    private String mHostType;
    private MtpStorageManager mManager;
    private final ContentProviderClient mMediaProvider;
    public IMtpDatabaseExt mMtpExt;
    private long mNativeContext;
    private MtpServer mServer;
    private static final String TAG = MtpDatabase.class.getSimpleName();
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static final int[] PLAYBACK_FORMATS = {12288, 12289, 12292, 12293, 12296, 12297, 12299, MtpConstants.FORMAT_EXIF_JPEG, MtpConstants.FORMAT_TIFF_EP, MtpConstants.FORMAT_BMP, MtpConstants.FORMAT_GIF, MtpConstants.FORMAT_JFIF, MtpConstants.FORMAT_PNG, MtpConstants.FORMAT_TIFF, MtpConstants.FORMAT_WMA, MtpConstants.FORMAT_OGG, MtpConstants.FORMAT_AAC, MtpConstants.FORMAT_MP4_CONTAINER, MtpConstants.FORMAT_MP2, MtpConstants.FORMAT_3GP_CONTAINER, MtpConstants.FORMAT_ABSTRACT_AV_PLAYLIST, MtpConstants.FORMAT_WPL_PLAYLIST, MtpConstants.FORMAT_M3U_PLAYLIST, MtpConstants.FORMAT_PLS_PLAYLIST, MtpConstants.FORMAT_XML_DOCUMENT, MtpConstants.FORMAT_FLAC, MtpConstants.FORMAT_DNG, MtpConstants.FORMAT_HEIF};
    private static final int[] FILE_PROPERTIES = {MtpConstants.PROPERTY_STORAGE_ID, MtpConstants.PROPERTY_OBJECT_FORMAT, MtpConstants.PROPERTY_PROTECTION_STATUS, MtpConstants.PROPERTY_OBJECT_SIZE, MtpConstants.PROPERTY_OBJECT_FILE_NAME, MtpConstants.PROPERTY_DATE_MODIFIED, MtpConstants.PROPERTY_PERSISTENT_UID, MtpConstants.PROPERTY_PARENT_OBJECT, MtpConstants.PROPERTY_NAME, MtpConstants.PROPERTY_DISPLAY_NAME, MtpConstants.PROPERTY_DATE_ADDED};
    private static final int[] AUDIO_PROPERTIES = {MtpConstants.PROPERTY_ARTIST, MtpConstants.PROPERTY_ALBUM_NAME, MtpConstants.PROPERTY_ALBUM_ARTIST, MtpConstants.PROPERTY_TRACK, MtpConstants.PROPERTY_ORIGINAL_RELEASE_DATE, MtpConstants.PROPERTY_DURATION, MtpConstants.PROPERTY_GENRE, MtpConstants.PROPERTY_COMPOSER, MtpConstants.PROPERTY_AUDIO_WAVE_CODEC, MtpConstants.PROPERTY_BITRATE_TYPE, MtpConstants.PROPERTY_AUDIO_BITRATE, MtpConstants.PROPERTY_NUMBER_OF_CHANNELS, MtpConstants.PROPERTY_SAMPLE_RATE};
    private static final int[] VIDEO_PROPERTIES = {MtpConstants.PROPERTY_ARTIST, MtpConstants.PROPERTY_ALBUM_NAME, MtpConstants.PROPERTY_DURATION, MtpConstants.PROPERTY_DESCRIPTION};
    private static final int[] IMAGE_PROPERTIES = {MtpConstants.PROPERTY_DESCRIPTION};
    private static final int[] DEVICE_PROPERTIES = {MtpConstants.DEVICE_PROPERTY_SYNCHRONIZATION_PARTNER, MtpConstants.DEVICE_PROPERTY_DEVICE_FRIENDLY_NAME, MtpConstants.DEVICE_PROPERTY_IMAGE_SIZE, MtpConstants.DEVICE_PROPERTY_BATTERY_LEVEL, MtpConstants.DEVICE_PROPERTY_PERCEIVED_DEVICE_TYPE, MtpConstants.DEVICE_PROPERTY_SESSION_INITIATOR_VERSION_INFO};
    private final AtomicBoolean mClosed = new AtomicBoolean();
    private final HashMap<String, MtpStorage> mStorageMap = new HashMap<>();
    private final SparseArray<MtpPropertyGroup> mPropertyGroupsByProperty = new SparseArray<>();
    private final SparseArray<MtpPropertyGroup> mPropertyGroupsByFormat = new SparseArray<>();
    private boolean mSkipThumbForHost = false;
    private BroadcastReceiver mBatteryReceiver = new BroadcastReceiver() { // from class: android.mtp.MtpDatabase.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                MtpDatabase.this.mBatteryScale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                int newLevel = intent.getIntExtra("level", 0);
                if (newLevel != MtpDatabase.this.mBatteryLevel) {
                    MtpDatabase.this.mBatteryLevel = newLevel;
                    if (MtpDatabase.this.mServer != null) {
                        MtpDatabase.this.mServer.sendDevicePropertyChanged(MtpConstants.DEVICE_PROPERTY_BATTERY_LEVEL);
                    }
                }
            }
        }
    };

    private final native void native_finalize();

    private final native void native_setup();

    static {
        System.loadLibrary("media_jni");
    }

    private int[] getSupportedObjectProperties(int format) {
        switch (format) {
            case 12296:
            case 12297:
            case MtpConstants.FORMAT_WMA /* 47361 */:
            case MtpConstants.FORMAT_OGG /* 47362 */:
            case MtpConstants.FORMAT_AAC /* 47363 */:
                return IntStream.concat(Arrays.stream(FILE_PROPERTIES), Arrays.stream(AUDIO_PROPERTIES)).toArray();
            case 12299:
            case MtpConstants.FORMAT_WMV /* 47489 */:
            case MtpConstants.FORMAT_3GP_CONTAINER /* 47492 */:
                return IntStream.concat(Arrays.stream(FILE_PROPERTIES), Arrays.stream(VIDEO_PROPERTIES)).toArray();
            case MtpConstants.FORMAT_EXIF_JPEG /* 14337 */:
            case MtpConstants.FORMAT_BMP /* 14340 */:
            case MtpConstants.FORMAT_GIF /* 14343 */:
            case MtpConstants.FORMAT_PNG /* 14347 */:
            case MtpConstants.FORMAT_DNG /* 14353 */:
            case MtpConstants.FORMAT_HEIF /* 14354 */:
                return IntStream.concat(Arrays.stream(FILE_PROPERTIES), Arrays.stream(IMAGE_PROPERTIES)).toArray();
            default:
                return FILE_PROPERTIES;
        }
    }

    public static Uri getObjectPropertiesUri(int format, String volumeName) {
        switch (format) {
            case 12296:
            case 12297:
            case MtpConstants.FORMAT_WMA /* 47361 */:
            case MtpConstants.FORMAT_OGG /* 47362 */:
            case MtpConstants.FORMAT_AAC /* 47363 */:
                return MediaStore.Audio.Media.getContentUri(volumeName);
            case 12299:
            case MtpConstants.FORMAT_WMV /* 47489 */:
            case MtpConstants.FORMAT_3GP_CONTAINER /* 47492 */:
                return MediaStore.Video.Media.getContentUri(volumeName);
            case MtpConstants.FORMAT_EXIF_JPEG /* 14337 */:
            case MtpConstants.FORMAT_BMP /* 14340 */:
            case MtpConstants.FORMAT_GIF /* 14343 */:
            case MtpConstants.FORMAT_PNG /* 14347 */:
            case MtpConstants.FORMAT_DNG /* 14353 */:
            case MtpConstants.FORMAT_HEIF /* 14354 */:
                return MediaStore.Images.Media.getContentUri(volumeName);
            default:
                return MediaStore.Files.getContentUri(volumeName);
        }
    }

    private int[] getSupportedDeviceProperties() {
        return DEVICE_PROPERTIES;
    }

    private int[] getSupportedPlaybackFormats() {
        return PLAYBACK_FORMATS;
    }

    private int[] getSupportedCaptureFormats() {
        return null;
    }

    public MtpDatabase(Context context, String[] subDirectories) {
        CloseGuard closeGuard = CloseGuard.get();
        this.mCloseGuard = closeGuard;
        this.mMtpExt = MtpDatabaseExtPlugin.getInstance.call(context);
        native_setup();
        Objects.requireNonNull(context);
        this.mContext = context;
        this.mMediaProvider = context.getContentResolver().acquireContentProviderClient(DeviceConfig.NAMESPACE_MEDIA);
        this.mManager = new MtpStorageManager(new MtpStorageManager.MtpNotifier() { // from class: android.mtp.MtpDatabase.2
            @Override // android.mtp.MtpStorageManager.MtpNotifier
            public void sendObjectAdded(int id) {
                if (MtpDatabase.this.mServer != null) {
                    MtpDatabase.this.mServer.sendObjectAdded(id);
                }
            }

            @Override // android.mtp.MtpStorageManager.MtpNotifier
            public void sendObjectRemoved(int id) {
                if (MtpDatabase.this.mServer != null) {
                    MtpDatabase.this.mServer.sendObjectRemoved(id);
                }
            }

            @Override // android.mtp.MtpStorageManager.MtpNotifier
            public void sendObjectInfoChanged(int id) {
                if (MtpDatabase.this.mServer != null) {
                    MtpDatabase.this.mServer.sendObjectInfoChanged(id);
                }
            }
        }, subDirectories == null ? null : Sets.newHashSet(subDirectories));
        initDeviceProperties(context);
        this.mDeviceType = SystemProperties.getInt("sys.usb.mtp.device_type", 0);
        closeGuard.open("close");
    }

    public void setServer(MtpServer server) {
        this.mServer = server;
        try {
            this.mContext.unregisterReceiver(this.mBatteryReceiver);
        } catch (IllegalArgumentException e) {
        }
        if (server != null) {
            this.mContext.registerReceiver(this.mBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mManager.close();
        this.mCloseGuard.close();
        if (this.mClosed.compareAndSet(false, true)) {
            ContentProviderClient contentProviderClient = this.mMediaProvider;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
            native_finalize();
        }
    }

    /* JADX WARN: Finally extract failed */
    protected void finalize() throws Throwable {
        try {
            CloseGuard closeGuard = this.mCloseGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
            }
            close();
            super.finalize();
            this.mMtpExt.quitSafely();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public void addStorage(StorageVolume storage) {
        MtpStorage mtpStorage = this.mManager.addMtpStorage(storage);
        this.mStorageMap.put(storage.getPath(), mtpStorage);
        MtpServer mtpServer = this.mServer;
        if (mtpServer != null) {
            mtpServer.addStorage(mtpStorage);
        }
    }

    public void removeStorage(StorageVolume storage) {
        MtpStorage mtpStorage = this.mStorageMap.get(storage.getPath());
        if (mtpStorage != null) {
            MtpServer mtpServer = this.mServer;
            if (mtpServer != null) {
                mtpServer.removeStorage(mtpStorage);
            }
            this.mManager.removeMtpStorage(mtpStorage);
            this.mStorageMap.remove(storage.getPath());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0078, code lost:
        if (r7 != null) goto L_0x0064;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
        r18.deleteDatabase("device-properties");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initDeviceProperties(android.content.Context r18) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            java.lang.String r3 = "device-properties"
            java.lang.String r4 = "device-properties"
            r5 = 0
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r4, r5)
            r1.mDeviceProperties = r0
            java.io.File r6 = r2.getDatabasePath(r4)
            boolean r0 = r6.exists()
            if (r0 == 0) goto L_0x008a
            r7 = 0
            r8 = 0
            r0 = 0
            android.database.sqlite.SQLiteDatabase r0 = r2.openOrCreateDatabase(r4, r5, r0)     // Catch: all -> 0x0068, Exception -> 0x006a
            r7 = r0
            if (r7 == 0) goto L_0x005d
            java.lang.String r10 = "properties"
            java.lang.String r0 = "_id"
            java.lang.String r9 = "code"
            java.lang.String r11 = "value"
            java.lang.String[] r11 = new java.lang.String[]{r0, r9, r11}     // Catch: all -> 0x0068, Exception -> 0x006a
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r7
            android.database.Cursor r0 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch: all -> 0x0068, Exception -> 0x006a
            r8 = r0
            if (r8 == 0) goto L_0x005d
            android.content.SharedPreferences r0 = r1.mDeviceProperties     // Catch: all -> 0x0068, Exception -> 0x006a
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch: all -> 0x0068, Exception -> 0x006a
        L_0x0045:
            boolean r9 = r8.moveToNext()     // Catch: all -> 0x0068, Exception -> 0x006a
            if (r9 == 0) goto L_0x005a
            r9 = 1
            java.lang.String r9 = r8.getString(r9)     // Catch: all -> 0x0068, Exception -> 0x006a
            r10 = 2
            java.lang.String r10 = r8.getString(r10)     // Catch: all -> 0x0068, Exception -> 0x006a
            r0.putString(r9, r10)     // Catch: all -> 0x0068, Exception -> 0x006a
            goto L_0x0045
        L_0x005a:
            r0.commit()     // Catch: all -> 0x0068, Exception -> 0x006a
        L_0x005d:
            if (r8 == 0) goto L_0x0062
            r8.close()
        L_0x0062:
            if (r7 == 0) goto L_0x007b
        L_0x0064:
            r7.close()
            goto L_0x007b
        L_0x0068:
            r0 = move-exception
            goto L_0x007f
        L_0x006a:
            r0 = move-exception
            java.lang.String r9 = android.mtp.MtpDatabase.TAG     // Catch: all -> 0x0068
            java.lang.String r10 = "failed to migrate device properties"
            android.util.Log.e(r9, r10, r0)     // Catch: all -> 0x0068
            if (r8 == 0) goto L_0x0078
            r8.close()
        L_0x0078:
            if (r7 == 0) goto L_0x007b
            goto L_0x0064
        L_0x007b:
            r2.deleteDatabase(r4)
            goto L_0x008a
        L_0x007f:
            if (r8 == 0) goto L_0x0084
            r8.close()
        L_0x0084:
            if (r7 == 0) goto L_0x0089
            r7.close()
        L_0x0089:
            throw r0
        L_0x008a:
            java.lang.String r0 = ""
            r1.mHostType = r0
            r1.mSkipThumbForHost = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpDatabase.initDeviceProperties(android.content.Context):void");
    }

    public int beginSendObject(String path, int format, int parent, int storageId) {
        MtpStorageManager mtpStorageManager = this.mManager;
        MtpStorageManager.MtpObject parentObj = parent == 0 ? mtpStorageManager.getStorageRoot(storageId) : mtpStorageManager.getObject(parent);
        if (parentObj == null) {
            return -1;
        }
        Path objPath = Paths.get(path, new String[0]);
        return this.mManager.beginSendObject(parentObj, objPath.getFileName().toString(), format);
    }

    private void endSendObject(int handle, boolean succeeded) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null || !this.mManager.endSendObject(obj, succeeded)) {
            Log.e(TAG, "Failed to successfully end send object");
        } else if (succeeded) {
            this.mMtpExt.rescanFile(obj.getPath().toString(), 0, 0);
        }
    }

    private void rescanFile(String path, int handle, int format) {
        MediaStore.scanFile(this.mContext.getContentResolver(), new File(path));
    }

    private int[] getObjectList(int storageID, int format, int parent) {
        List<MtpStorageManager.MtpObject> objs = this.mManager.getObjects(parent, format, storageID);
        if (objs == null) {
            return null;
        }
        int[] ret = new int[objs.size()];
        for (int i = 0; i < objs.size(); i++) {
            ret[i] = objs.get(i).getId();
        }
        return ret;
    }

    public int getNumObjects(int storageID, int format, int parent) {
        List<MtpStorageManager.MtpObject> objs = this.mManager.getObjects(parent, format, storageID);
        if (objs == null) {
            return -1;
        }
        return objs.size();
    }

    private MtpPropertyList getObjectPropertyList(int handle, int format, int property, int groupCode, int depth) {
        MtpPropertyGroup propertyGroup;
        int handle2 = handle;
        int format2 = format;
        if (property != 0) {
            int err = -1;
            int depth2 = depth;
            if (depth2 == -1 && (handle2 == 0 || handle2 == -1)) {
                handle2 = -1;
                depth2 = 0;
            }
            if (!(depth2 == 0 || depth2 == 1)) {
                return new MtpPropertyList(MtpConstants.RESPONSE_SPECIFICATION_BY_DEPTH_UNSUPPORTED);
            }
            List<MtpStorageManager.MtpObject> objs = null;
            MtpStorageManager.MtpObject thisObj = null;
            if (handle2 == -1) {
                objs = this.mManager.getObjects(0, format2, -1);
                if (objs == null) {
                    return new MtpPropertyList(8201);
                }
            } else if (handle2 != 0) {
                MtpStorageManager.MtpObject obj = this.mManager.getObject(handle2);
                if (obj == null) {
                    return new MtpPropertyList(8201);
                }
                if (obj.getFormat() == format2 || format2 == 0) {
                    thisObj = obj;
                }
            }
            if (handle2 == 0 || depth2 == 1) {
                if (handle2 == 0) {
                    handle2 = -1;
                }
                objs = this.mManager.getObjects(handle2, format2, -1);
                if (objs == null) {
                    return new MtpPropertyList(8201);
                }
            }
            if (objs == null) {
                objs = new ArrayList<>();
            }
            if (thisObj != null) {
                objs.add(thisObj);
            }
            MtpPropertyList ret = new MtpPropertyList(8193);
            for (MtpStorageManager.MtpObject obj2 : objs) {
                if (property == err) {
                    if (!(format2 != 0 || handle2 == 0 || handle2 == err)) {
                        format2 = obj2.getFormat();
                    }
                    propertyGroup = this.mPropertyGroupsByFormat.get(format2);
                    if (propertyGroup == null) {
                        propertyGroup = new MtpPropertyGroup(getSupportedObjectProperties(format2));
                        this.mPropertyGroupsByFormat.put(format2, propertyGroup);
                    }
                } else {
                    propertyGroup = this.mPropertyGroupsByProperty.get(property);
                    if (propertyGroup == null) {
                        int[] propertyList = {property};
                        propertyGroup = new MtpPropertyGroup(propertyList);
                        this.mPropertyGroupsByProperty.put(property, propertyGroup);
                    }
                }
                int err2 = propertyGroup.getPropertyList(this.mMediaProvider, obj2.getVolumeName(), obj2, ret);
                if (err2 != 8193) {
                    return new MtpPropertyList(err2);
                }
                err = -1;
            }
            return ret;
        } else if (groupCode == 0) {
            return new MtpPropertyList(8198);
        } else {
            return new MtpPropertyList(MtpConstants.RESPONSE_SPECIFICATION_BY_GROUP_UNSUPPORTED);
        }
    }

    private int renameFile(int handle, String newName) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null) {
            return 8201;
        }
        Path oldPath = obj.getPath();
        if (!this.mManager.beginRenameObject(obj, newName)) {
            return 8194;
        }
        Path newPath = obj.getPath();
        boolean success = oldPath.toFile().renameTo(newPath.toFile());
        try {
            Os.access(oldPath.toString(), OsConstants.F_OK);
            Os.access(newPath.toString(), OsConstants.F_OK);
        } catch (ErrnoException e) {
        }
        if (!this.mManager.endRenameObject(obj, oldPath.getFileName().toString(), success)) {
            Log.e(TAG, "Failed to end rename object");
        }
        if (!success) {
            return 8194;
        }
        this.mMtpExt.rescanFile(oldPath.toString(), 0, 0);
        this.mMtpExt.rescanFile(newPath.toString(), 0, 0);
        return 8193;
    }

    private int beginMoveObject(int handle, int newParent, int newStorage) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        MtpStorageManager.MtpObject parent = newParent == 0 ? this.mManager.getStorageRoot(newStorage) : this.mManager.getObject(newParent);
        if (obj == null || parent == null) {
            return 8201;
        }
        boolean allowed = this.mManager.beginMoveObject(obj, parent);
        return allowed ? 8193 : 8194;
    }

    private void endMoveObject(int oldParent, int newParent, int oldStorage, int newStorage, int objId, boolean success) {
        MtpStorageManager.MtpObject oldParentObj = oldParent == 0 ? this.mManager.getStorageRoot(oldStorage) : this.mManager.getObject(oldParent);
        MtpStorageManager.MtpObject newParentObj = newParent == 0 ? this.mManager.getStorageRoot(newStorage) : this.mManager.getObject(newParent);
        MtpStorageManager.MtpObject obj = this.mManager.getObject(objId);
        String name = obj.getName();
        if (newParentObj == null || oldParentObj == null || !this.mManager.endMoveObject(oldParentObj, newParentObj, name, success)) {
            Log.e(TAG, "Failed to end move object");
            return;
        }
        MtpStorageManager.MtpObject obj2 = this.mManager.getObject(objId);
        if (success && obj2 != null) {
            Path path = newParentObj.getPath().resolve(name);
            Path oldPath = oldParentObj.getPath().resolve(name);
            this.mMtpExt.rescanFile(oldPath.toString(), 0, 0);
            this.mMtpExt.rescanFile(path.toString(), 0, 0);
        }
    }

    private int beginCopyObject(int handle, int newParent, int newStorage) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        MtpStorageManager.MtpObject parent = newParent == 0 ? this.mManager.getStorageRoot(newStorage) : this.mManager.getObject(newParent);
        if (obj == null || parent == null) {
            return 8201;
        }
        return this.mManager.beginCopyObject(obj, parent);
    }

    private void endCopyObject(int handle, boolean success) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null || !this.mManager.endCopyObject(obj, success)) {
            Log.e(TAG, "Failed to end copy object");
        } else if (success) {
            this.mMtpExt.rescanFile(obj.getPath().toString(), 0, 0);
        }
    }

    private static void updateMediaStore(Context context, File file) {
        ContentResolver resolver = context.getContentResolver();
        if (file.isDirectory() || !file.getName().toLowerCase(Locale.ROOT).endsWith(NO_MEDIA)) {
            MediaStore.scanFile(resolver, file);
        } else {
            MediaStore.scanFile(resolver, file.getParentFile());
        }
    }

    private int setObjectProperty(int handle, int property, long intValue, String stringValue) {
        switch (property) {
            case MtpConstants.PROPERTY_OBJECT_FILE_NAME /* 56327 */:
                return renameFile(handle, stringValue);
            default:
                return MtpConstants.RESPONSE_OBJECT_PROP_NOT_SUPPORTED;
        }
    }

    private int getDeviceProperty(int property, long[] outIntValue, char[] outStringValue) {
        switch (property) {
            case MtpConstants.DEVICE_PROPERTY_BATTERY_LEVEL /* 20481 */:
                outIntValue[0] = this.mBatteryLevel;
                outIntValue[1] = this.mBatteryScale;
                return 8193;
            case MtpConstants.DEVICE_PROPERTY_IMAGE_SIZE /* 20483 */:
                Display display = ((WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                int width = display.getMaximumSizeDimension();
                int height = display.getMaximumSizeDimension();
                String imageSize = Integer.toString(width) + "x" + Integer.toString(height);
                imageSize.getChars(0, imageSize.length(), outStringValue, 0);
                outStringValue[imageSize.length()] = 0;
                return 8193;
            case MtpConstants.DEVICE_PROPERTY_SYNCHRONIZATION_PARTNER /* 54273 */:
            case MtpConstants.DEVICE_PROPERTY_DEVICE_FRIENDLY_NAME /* 54274 */:
                String value = this.mDeviceProperties.getString(Integer.toString(property), "");
                int length = value.length();
                if (length > 255) {
                    length = 255;
                }
                value.getChars(0, length, outStringValue, 0);
                outStringValue[length] = 0;
                this.mMtpExt.getMtpDeviceProperty(length, property, outStringValue);
                return 8193;
            case MtpConstants.DEVICE_PROPERTY_SESSION_INITIATOR_VERSION_INFO /* 54278 */:
                String value2 = this.mHostType;
                int length2 = value2.length();
                if (length2 > 255) {
                    length2 = 255;
                }
                value2.getChars(0, length2, outStringValue, 0);
                outStringValue[length2] = 0;
                return 8193;
            case MtpConstants.DEVICE_PROPERTY_PERCEIVED_DEVICE_TYPE /* 54279 */:
                outIntValue[0] = this.mDeviceType;
                return 8193;
            default:
                return 8202;
        }
    }

    private int setDeviceProperty(int property, long intValue, String stringValue) {
        switch (property) {
            case MtpConstants.DEVICE_PROPERTY_SYNCHRONIZATION_PARTNER /* 54273 */:
            case MtpConstants.DEVICE_PROPERTY_DEVICE_FRIENDLY_NAME /* 54274 */:
                SharedPreferences.Editor e = this.mDeviceProperties.edit();
                e.putString(Integer.toString(property), stringValue);
                if (e.commit()) {
                    return 8193;
                }
                return 8194;
            case MtpConstants.DEVICE_PROPERTY_SESSION_INITIATOR_VERSION_INFO /* 54278 */:
                this.mHostType = stringValue;
                if (stringValue.startsWith("Android/")) {
                    String str = TAG;
                    Log.d(str, "setDeviceProperty." + Integer.toHexString(property) + "=" + stringValue);
                    this.mSkipThumbForHost = true;
                }
                return 8193;
            default:
                return 8202;
        }
    }

    private boolean getObjectInfo(int handle, int[] outStorageFormatParent, char[] outName, long[] outCreatedModified) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null) {
            return false;
        }
        outStorageFormatParent[0] = obj.getStorageId();
        outStorageFormatParent[1] = obj.getFormat();
        outStorageFormatParent[2] = obj.getParent().isRoot() ? 0 : obj.getParent().getId();
        int nameLen = Integer.min(obj.getName().length(), 255);
        obj.getName().getChars(0, nameLen, outName, 0);
        outName[nameLen] = 0;
        outCreatedModified[0] = obj.getModifiedTime();
        outCreatedModified[1] = obj.getModifiedTime();
        return true;
    }

    private int getObjectFilePath(int handle, char[] outFilePath, long[] outFileLengthFormat) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null) {
            return 8201;
        }
        String path = obj.getPath().toString();
        int pathLen = Integer.min(path.length(), 4096);
        path.getChars(0, pathLen, outFilePath, 0);
        outFilePath[pathLen] = 0;
        outFileLengthFormat[0] = obj.getSize();
        outFileLengthFormat[1] = obj.getFormat();
        return 8193;
    }

    private int openFilePath(String path, boolean transcode) {
        Uri uri = MediaStore.scanFile(this.mContext.getContentResolver(), new File(path));
        if (uri == null) {
            String str = TAG;
            Log.i(str, "Failed to obtain URI for openFile with transcode support: " + path);
            return -1;
        }
        try {
            String str2 = TAG;
            Log.i(str2, "openFile with transcode support: " + path);
            Bundle bundle = new Bundle();
            if (transcode) {
                bundle.putParcelable("android.provider.extra.MEDIA_CAPABILITIES", new ApplicationMediaCapabilities.Builder().addUnsupportedVideoMimeType(MediaFormat.MIMETYPE_VIDEO_HEVC).build());
            } else {
                bundle.putParcelable("android.provider.extra.MEDIA_CAPABILITIES", new ApplicationMediaCapabilities.Builder().addSupportedVideoMimeType(MediaFormat.MIMETYPE_VIDEO_HEVC).build());
            }
            return this.mMediaProvider.openTypedAssetFileDescriptor(uri, "*/*", bundle).getParcelFileDescriptor().detachFd();
        } catch (RemoteException | FileNotFoundException e) {
            String str3 = TAG;
            Log.w(str3, "Failed to openFile with transcode support: " + path, e);
            return -1;
        }
    }

    private int getObjectFormat(int handle) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null) {
            return -1;
        }
        return obj.getFormat();
    }

    private byte[] getThumbnailProcess(String path, Bitmap bitmap) {
        try {
            if (bitmap == null) {
                Log.d(TAG, "getThumbnailProcess: Fail to generate thumbnail. Probably unsupported or corrupted image");
                return null;
            }
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);
            if (byteStream.size() > MAX_THUMB_SIZE) {
                String str = TAG;
                Log.w(str, "getThumbnailProcess: size=" + byteStream.size());
                return null;
            }
            byte[] byteArray = byteStream.toByteArray();
            return byteArray;
        } catch (OutOfMemoryError oomEx) {
            String str2 = TAG;
            Log.w(str2, "OutOfMemoryError:" + oomEx);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean getThumbnailInfo(int handle, long[] outLongs) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null) {
            return false;
        }
        String path = obj.getPath().toString();
        switch (obj.getFormat()) {
            case MtpConstants.FORMAT_EXIF_JPEG /* 14337 */:
            case MtpConstants.FORMAT_JFIF /* 14344 */:
            case MtpConstants.FORMAT_HEIF /* 14354 */:
                try {
                    ExifInterface exif = new ExifInterface(path);
                    long[] thumbOffsetAndSize = exif.getThumbnailRange();
                    outLongs[0] = thumbOffsetAndSize != null ? thumbOffsetAndSize[1] : 0L;
                    outLongs[1] = exif.getAttributeInt(ExifInterface.TAG_PIXEL_X_DIMENSION, 0);
                    outLongs[2] = exif.getAttributeInt(ExifInterface.TAG_PIXEL_Y_DIMENSION, 0);
                    if (this.mSkipThumbForHost) {
                        Log.d(TAG, "getThumbnailInfo: Skip runtime thumbnail.");
                        return true;
                    } else if (exif.getThumbnailRange() != null) {
                        if (outLongs[0] == 0 || outLongs[1] == 0 || outLongs[2] == 0) {
                            String str = TAG;
                            Log.d(str, "getThumbnailInfo: check thumb info:" + thumbOffsetAndSize[0] + "," + thumbOffsetAndSize[1] + "," + outLongs[1] + "," + outLongs[2]);
                        }
                        return true;
                    }
                } catch (IOException e) {
                    break;
                }
                break;
            case MtpConstants.FORMAT_BMP /* 14340 */:
            case MtpConstants.FORMAT_GIF /* 14343 */:
            case MtpConstants.FORMAT_PNG /* 14347 */:
                break;
            default:
                return false;
        }
        outLongs[0] = 204800;
        outLongs[1] = 320;
        outLongs[2] = 240;
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public byte[] getThumbnailData(int handle) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null) {
            return null;
        }
        String path = obj.getPath().toString();
        switch (obj.getFormat()) {
            case MtpConstants.FORMAT_EXIF_JPEG /* 14337 */:
            case MtpConstants.FORMAT_JFIF /* 14344 */:
            case MtpConstants.FORMAT_HEIF /* 14354 */:
                try {
                    ExifInterface exif = new ExifInterface(path);
                    if (this.mSkipThumbForHost) {
                        Log.d(TAG, "getThumbnailData: Skip runtime thumbnail.");
                        return exif.getThumbnail();
                    } else if (exif.getThumbnailRange() != null) {
                        return exif.getThumbnail();
                    }
                } catch (IOException e) {
                    break;
                }
                break;
            case MtpConstants.FORMAT_BMP /* 14340 */:
            case MtpConstants.FORMAT_GIF /* 14343 */:
            case MtpConstants.FORMAT_PNG /* 14347 */:
                break;
            default:
                return null;
        }
        Bitmap bitmap = ThumbnailUtils.createImageThumbnail(path, 1);
        byte[] byteArray = getThumbnailProcess(path, bitmap);
        return byteArray;
    }

    private int beginDeleteObject(int handle) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj == null) {
            return 8201;
        }
        if (!this.mManager.beginRemoveObject(obj)) {
            return 8194;
        }
        return 8193;
    }

    private void endDeleteObject(int handle, boolean success) {
        MtpStorageManager.MtpObject obj = this.mManager.getObject(handle);
        if (obj != null) {
            if (!this.mManager.endRemoveObject(obj, success)) {
                Log.e(TAG, "Failed to end remove object");
            }
            if (success) {
                deleteFromMedia(obj, obj.getPath(), obj.isDir());
            }
        }
    }

    private void deleteFromMedia(MtpStorageManager.MtpObject obj, Path path, boolean isDir) {
        Uri objectsUri = MediaStore.Files.getContentUri(obj.getVolumeName());
        if (isDir) {
            try {
                ContentProviderClient contentProviderClient = this.mMediaProvider;
                contentProviderClient.delete(objectsUri, "_data LIKE ?1 AND lower(substr(_data,1,?2))=lower(?3)", new String[]{path + "/%", Integer.toString(path.toString().length() + 1), path.toString() + "/"});
            } catch (Exception e) {
                String str = TAG;
                Log.d(str, "Failed to delete " + path + " from MediaProvider");
                return;
            }
        }
        String[] whereArgs = {path.toString()};
        if (this.mMediaProvider.delete(objectsUri, PATH_WHERE, whereArgs) == 0) {
            String str2 = TAG;
            Log.i(str2, "MediaProvider didn't delete " + path);
        }
        this.mMtpExt.rescanFile(path.toString(), 0, 0);
    }

    private int[] getObjectReferences(int handle) {
        return null;
    }

    private int setObjectReferences(int handle, int[] references) {
        return 8197;
    }
}
