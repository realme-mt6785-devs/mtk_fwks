package android.app.backup;

import android.app.IBackupAgent;
import android.app.QueuedWork;
import android.app.backup.FullBackup;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.util.ArraySet;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public abstract class BackupAgent extends ContextWrapper {
    private static final boolean DEBUG = false;
    private static final int DEFAULT_OPERATION_TYPE = 0;
    public static final int FLAG_CLIENT_SIDE_ENCRYPTION_ENABLED = 1;
    public static final int FLAG_DEVICE_TO_DEVICE_TRANSFER = 2;
    public static final int FLAG_FAKE_CLIENT_SIDE_ENCRYPTION_ENABLED = Integer.MIN_VALUE;
    public static final int RESULT_ERROR = -1;
    public static final int RESULT_SUCCESS = 0;
    private static final String TAG = "BackupAgent";
    public static final int TYPE_DIRECTORY = 2;
    public static final int TYPE_EOF = 0;
    public static final int TYPE_FILE = 1;
    public static final int TYPE_SYMLINK = 3;
    private UserHandle mUser;
    Handler mHandler = null;
    private volatile int mOperationType = 0;
    private final IBinder mBinder = new BackupServiceBinder().asBinder();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BackupTransportFlags {
    }

    public abstract void onBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) throws IOException;

    public abstract void onRestore(BackupDataInput backupDataInput, int i, ParcelFileDescriptor parcelFileDescriptor) throws IOException;

    Handler getHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
        return this.mHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SharedPrefsSynchronizer implements Runnable {
        public final CountDownLatch mLatch = new CountDownLatch(1);

        SharedPrefsSynchronizer() {
        }

        @Override // java.lang.Runnable
        public void run() {
            QueuedWork.waitToFinish();
            this.mLatch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForSharedPrefs() {
        Handler h = getHandler();
        SharedPrefsSynchronizer s = new SharedPrefsSynchronizer();
        h.postAtFrontOfQueue(s);
        try {
            s.mLatch.await();
        } catch (InterruptedException e) {
        }
    }

    public BackupAgent() {
        super(null);
    }

    public void onCreate() {
    }

    public void onCreate(UserHandle user) {
        onCreate(user, 0);
    }

    public void onCreate(UserHandle user, int operationType) {
        onCreate();
        this.mUser = user;
        this.mOperationType = operationType;
    }

    public void onDestroy() {
    }

    public void onRestore(BackupDataInput data, long appVersionCode, ParcelFileDescriptor newState) throws IOException {
        onRestore(data, (int) appVersionCode, newState);
    }

    public void onRestore(BackupDataInput data, long appVersionCode, ParcelFileDescriptor newState, Set<String> excludedKeys) throws IOException {
        onRestore(data, appVersionCode, newState);
    }

    public void onFullBackup(FullBackupDataOutput data) throws IOException {
        String libDir;
        FullBackup.BackupScheme backupScheme = FullBackup.getBackupScheme(this, this.mOperationType);
        if (backupScheme.isFullBackupEnabled(data.getTransportFlags())) {
            try {
                IncludeExcludeRules includeExcludeRules = getIncludeExcludeRules(backupScheme);
                Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> manifestIncludeMap = includeExcludeRules.getIncludeMap();
                Set<FullBackup.BackupScheme.PathWithRequiredFlags> manifestExcludeSet = includeExcludeRules.getExcludeSet();
                String packageName = getPackageName();
                ApplicationInfo appInfo = getApplicationInfo();
                Context ceContext = createCredentialProtectedStorageContext();
                String rootDir = ceContext.getDataDir().getCanonicalPath();
                String filesDir = ceContext.getFilesDir().getCanonicalPath();
                String databaseDir = ceContext.getDatabasePath("foo").getParentFile().getCanonicalPath();
                String sharedPrefsDir = ceContext.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
                Context deContext = createDeviceProtectedStorageContext();
                String deviceRootDir = deContext.getDataDir().getCanonicalPath();
                String deviceFilesDir = deContext.getFilesDir().getCanonicalPath();
                String deviceDatabaseDir = deContext.getDatabasePath("foo").getParentFile().getCanonicalPath();
                String deviceSharedPrefsDir = deContext.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
                String deviceRootDir2 = appInfo.nativeLibraryDir;
                if (deviceRootDir2 != null) {
                    libDir = new File(appInfo.nativeLibraryDir).getCanonicalPath();
                } else {
                    libDir = null;
                }
                ArraySet<String> traversalExcludeSet = new ArraySet<>();
                traversalExcludeSet.add(filesDir);
                traversalExcludeSet.add(databaseDir);
                traversalExcludeSet.add(sharedPrefsDir);
                traversalExcludeSet.add(deviceFilesDir);
                traversalExcludeSet.add(deviceDatabaseDir);
                traversalExcludeSet.add(deviceSharedPrefsDir);
                if (libDir != null) {
                    traversalExcludeSet.add(libDir);
                }
                Set<String> extraExcludedDirs = getExtraExcludeDirsIfAny(ceContext);
                Set<String> extraExcludedDeviceDirs = getExtraExcludeDirsIfAny(deContext);
                traversalExcludeSet.addAll(extraExcludedDirs);
                traversalExcludeSet.addAll(extraExcludedDeviceDirs);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, "r", manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(rootDir);
                traversalExcludeSet.addAll(extraExcludedDirs);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_ROOT_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(deviceRootDir);
                traversalExcludeSet.addAll(extraExcludedDeviceDirs);
                traversalExcludeSet.remove(filesDir);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.FILES_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(filesDir);
                traversalExcludeSet.remove(deviceFilesDir);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_FILES_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(deviceFilesDir);
                traversalExcludeSet.remove(databaseDir);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DATABASE_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(databaseDir);
                traversalExcludeSet.remove(deviceDatabaseDir);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_DATABASE_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(deviceDatabaseDir);
                traversalExcludeSet.remove(sharedPrefsDir);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.SHAREDPREFS_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(sharedPrefsDir);
                traversalExcludeSet.remove(deviceSharedPrefsDir);
                applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.DEVICE_SHAREDPREFS_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                traversalExcludeSet.add(deviceSharedPrefsDir);
                if (Process.myUid() != 1000) {
                    File efLocation = getExternalFilesDir(null);
                    if (efLocation != null) {
                        applyXmlFiltersAndDoFullBackupForDomain(packageName, FullBackup.MANAGED_EXTERNAL_TREE_TOKEN, manifestIncludeMap, manifestExcludeSet, traversalExcludeSet, data);
                    }
                }
            } catch (IOException | XmlPullParserException e) {
                if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                    Log.v("BackupXmlParserLogging", "Exception trying to parse fullBackupContent xml file! Aborting full backup.", e);
                }
            }
        }
    }

    private Set<String> getExtraExcludeDirsIfAny(Context context) throws IOException {
        Set<String> excludedDirs = new HashSet<>();
        excludedDirs.add(context.getCacheDir().getCanonicalPath());
        excludedDirs.add(context.getCodeCacheDir().getCanonicalPath());
        excludedDirs.add(context.getNoBackupFilesDir().getCanonicalPath());
        return Collections.unmodifiableSet(excludedDirs);
    }

    public IncludeExcludeRules getIncludeExcludeRules(FullBackup.BackupScheme backupScheme) throws IOException, XmlPullParserException {
        Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> manifestIncludeMap = backupScheme.maybeParseAndGetCanonicalIncludePaths();
        ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> manifestExcludeSet = backupScheme.maybeParseAndGetCanonicalExcludePaths();
        return new IncludeExcludeRules(manifestIncludeMap, manifestExcludeSet);
    }

    public void onQuotaExceeded(long backupDataBytes, long quotaBytes) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBackupUserId() {
        UserHandle userHandle = this.mUser;
        return userHandle == null ? super.getUserId() : userHandle.getIdentifier();
    }

    private void applyXmlFiltersAndDoFullBackupForDomain(String packageName, String domainToken, Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> includeMap, Set<FullBackup.BackupScheme.PathWithRequiredFlags> filterSet, ArraySet<String> traversalExcludeSet, FullBackupDataOutput data) throws IOException {
        if (includeMap == null || includeMap.size() == 0) {
            fullBackupFileTree(packageName, domainToken, FullBackup.getBackupScheme(this, this.mOperationType).tokenToDirectoryPath(domainToken), filterSet, traversalExcludeSet, data);
        } else if (includeMap.get(domainToken) != null) {
            for (FullBackup.BackupScheme.PathWithRequiredFlags includeFile : includeMap.get(domainToken)) {
                if (areIncludeRequiredTransportFlagsSatisfied(includeFile.getRequiredFlags(), data.getTransportFlags())) {
                    fullBackupFileTree(packageName, domainToken, includeFile.getPath(), filterSet, traversalExcludeSet, data);
                }
            }
        }
    }

    private boolean areIncludeRequiredTransportFlagsSatisfied(int includeFlags, int transportFlags) {
        return (transportFlags & includeFlags) == includeFlags;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void fullBackupFile(java.io.File r30, android.app.backup.FullBackupDataOutput r31) {
        /*
            Method dump skipped, instructions count: 529
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.backup.BackupAgent.fullBackupFile(java.io.File, android.app.backup.FullBackupDataOutput):void");
    }

    protected final void fullBackupFileTree(String packageName, String domain, String startingPath, Set<FullBackup.BackupScheme.PathWithRequiredFlags> manifestExcludes, ArraySet<String> systemExcludes, FullBackupDataOutput output) {
        File file;
        File file2;
        ErrnoException e;
        StructStat stat;
        File[] contents;
        BackupAgent backupAgent = this;
        String domainPath = FullBackup.getBackupScheme(backupAgent, backupAgent.mOperationType).tokenToDirectoryPath(domain);
        if (domainPath != null) {
            File rootFile = new File(startingPath);
            if (rootFile.exists()) {
                LinkedList<File> scanQueue = new LinkedList<>();
                scanQueue.add(rootFile);
                while (scanQueue.size() > 0) {
                    File file3 = scanQueue.remove(0);
                    try {
                        stat = Os.lstat(file3.getPath());
                    } catch (ErrnoException e2) {
                        e = e2;
                        file2 = file3;
                    } catch (IOException e3) {
                        file = file3;
                    }
                    if (!OsConstants.S_ISREG(stat.st_mode)) {
                        try {
                        } catch (ErrnoException e4) {
                            e = e4;
                            file2 = file3;
                            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                                Log.v("BackupXmlParserLogging", "Error scanning file " + file2 + " : " + e);
                            }
                            backupAgent = this;
                        } catch (IOException e5) {
                            file = file3;
                            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                                Log.v("BackupXmlParserLogging", "Error canonicalizing path of " + file);
                            }
                            backupAgent = this;
                        }
                        if (!OsConstants.S_ISDIR(stat.st_mode)) {
                        }
                    }
                    String filePath = file3.getCanonicalPath();
                    if (manifestExcludes == null || !backupAgent.manifestExcludesContainFilePath(manifestExcludes, filePath)) {
                        if (systemExcludes == null || !systemExcludes.contains(filePath)) {
                            if (OsConstants.S_ISDIR(stat.st_mode) && (contents = file3.listFiles()) != null) {
                                for (File entry : contents) {
                                    scanQueue.add(0, entry);
                                }
                            }
                            FullBackup.backupToTar(packageName, domain, null, domainPath, filePath, output);
                            backupAgent = this;
                        }
                    }
                }
            }
        }
    }

    private boolean manifestExcludesContainFilePath(Set<FullBackup.BackupScheme.PathWithRequiredFlags> manifestExcludes, String filePath) {
        for (FullBackup.BackupScheme.PathWithRequiredFlags exclude : manifestExcludes) {
            String excludePath = exclude.getPath();
            if (excludePath != null && excludePath.equals(filePath)) {
                return true;
            }
        }
        return false;
    }

    public void onRestoreFile(ParcelFileDescriptor data, long size, File destination, int type, long mode, long mtime) throws IOException {
        boolean accept = isFileEligibleForRestore(destination);
        FullBackup.restoreFile(data, size, type, mode, mtime, accept ? destination : null);
    }

    private boolean isFileEligibleForRestore(File destination) throws IOException {
        FullBackup.BackupScheme bs = FullBackup.getBackupScheme(this, this.mOperationType);
        if (!bs.isFullRestoreEnabled()) {
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                Log.v("BackupXmlParserLogging", "onRestoreFile \"" + destination.getCanonicalPath() + "\" : fullBackupContent not enabled for " + getPackageName());
            }
            return false;
        }
        String destinationCanonicalPath = destination.getCanonicalPath();
        try {
            Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> includes = bs.maybeParseAndGetCanonicalIncludePaths();
            ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> excludes = bs.maybeParseAndGetCanonicalExcludePaths();
            if (excludes != null && BackupUtils.isFileSpecifiedInPathList(destination, excludes)) {
                if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                    Log.v("BackupXmlParserLogging", "onRestoreFile: \"" + destinationCanonicalPath + "\": listed in excludes; skipping.");
                }
                return false;
            } else if (includes == null || includes.isEmpty()) {
                return true;
            } else {
                boolean explicitlyIncluded = false;
                for (Set<FullBackup.BackupScheme.PathWithRequiredFlags> domainIncludes : includes.values()) {
                    explicitlyIncluded |= BackupUtils.isFileSpecifiedInPathList(destination, domainIncludes);
                    if (explicitlyIncluded) {
                        break;
                    }
                }
                if (explicitlyIncluded) {
                    return true;
                }
                if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                    Log.v("BackupXmlParserLogging", "onRestoreFile: Trying to restore \"" + destinationCanonicalPath + "\" but it isn't specified in the included files; skipping.");
                }
                return false;
            }
        } catch (XmlPullParserException e) {
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                Log.v("BackupXmlParserLogging", "onRestoreFile \"" + destinationCanonicalPath + "\" : Exception trying to parse fullBackupContent xml file! Aborting onRestoreFile.", e);
            }
            return false;
        }
    }

    protected void onRestoreFile(ParcelFileDescriptor data, long size, int type, String domain, String path, long mode, long mtime) throws IOException {
        long mode2;
        String basePath = FullBackup.getBackupScheme(this, this.mOperationType).tokenToDirectoryPath(domain);
        if (domain.equals(FullBackup.MANAGED_EXTERNAL_TREE_TOKEN)) {
            mode2 = -1;
        } else {
            mode2 = mode;
        }
        if (basePath != null) {
            File outFile = new File(basePath, path);
            String outPath = outFile.getCanonicalPath();
            if (outPath.startsWith(basePath + File.separatorChar)) {
                onRestoreFile(data, size, outFile, type, mode2, mtime);
                return;
            }
        }
        FullBackup.restoreFile(data, size, type, mode2, mtime, null);
    }

    public void onRestoreFinished() {
    }

    public final IBinder onBind() {
        return this.mBinder;
    }

    public void attach(Context context) {
        attachBaseContext(context);
    }

    /* loaded from: classes.dex */
    private class BackupServiceBinder extends IBackupAgent.Stub {
        private static final String TAG = "BackupServiceBinder";

        private BackupServiceBinder() {
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00c9  */
        @Override // android.app.IBackupAgent
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void doBackup(android.os.ParcelFileDescriptor r18, android.os.ParcelFileDescriptor r19, android.os.ParcelFileDescriptor r20, long r21, android.app.backup.IBackupCallback r23, int r24) throws android.os.RemoteException {
            /*
                r17 = this;
                r1 = r17
                r2 = r23
                java.lang.String r3 = ") threw"
                java.lang.String r4 = "onBackup ("
                java.lang.String r5 = "BackupServiceBinder"
                android.app.backup.BackupDataOutput r0 = new android.app.backup.BackupDataOutput
                java.io.FileDescriptor r6 = r19.getFileDescriptor()
                r7 = r21
                r9 = r24
                r0.<init>(r6, r7, r9)
                r6 = r0
                r10 = -1
                long r12 = android.os.Binder.clearCallingIdentity()
                android.app.backup.BackupAgent r0 = android.app.backup.BackupAgent.this     // Catch: all -> 0x0053, RuntimeException -> 0x005c, IOException -> 0x0084
                r14 = r18
                r15 = r20
                r0.onBackup(r14, r6, r15)     // Catch: all -> 0x004b, RuntimeException -> 0x004d, IOException -> 0x004f
                r3 = 0
                android.app.backup.BackupAgent r0 = android.app.backup.BackupAgent.this
                android.app.backup.BackupAgent.access$300(r0)
                android.os.Binder.restoreCallingIdentity(r12)
                r2.operationComplete(r3)     // Catch: RemoteException -> 0x0036
                goto L_0x0037
            L_0x0036:
                r0 = move-exception
            L_0x0037:
                int r0 = android.os.Binder.getCallingPid()
                int r5 = android.os.Process.myPid()
                if (r0 == r5) goto L_0x004a
                libcore.io.IoUtils.closeQuietly(r18)
                libcore.io.IoUtils.closeQuietly(r19)
                libcore.io.IoUtils.closeQuietly(r20)
            L_0x004a:
                return
            L_0x004b:
                r0 = move-exception
                goto L_0x0058
            L_0x004d:
                r0 = move-exception
                goto L_0x0061
            L_0x004f:
                r0 = move-exception
                r16 = r6
                goto L_0x008b
            L_0x0053:
                r0 = move-exception
                r14 = r18
                r15 = r20
            L_0x0058:
                r3 = r0
                r16 = r6
                goto L_0x00b2
            L_0x005c:
                r0 = move-exception
                r14 = r18
                r15 = r20
            L_0x0061:
                r16 = r6
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00b0
                r6.<init>()     // Catch: all -> 0x00b0
                r6.append(r4)     // Catch: all -> 0x00b0
                android.app.backup.BackupAgent r4 = android.app.backup.BackupAgent.this     // Catch: all -> 0x00b0
                java.lang.Class r4 = r4.getClass()     // Catch: all -> 0x00b0
                java.lang.String r4 = r4.getName()     // Catch: all -> 0x00b0
                r6.append(r4)     // Catch: all -> 0x00b0
                r6.append(r3)     // Catch: all -> 0x00b0
                java.lang.String r3 = r6.toString()     // Catch: all -> 0x00b0
                android.util.Log.d(r5, r3, r0)     // Catch: all -> 0x00b0
                throw r0     // Catch: all -> 0x00b0
            L_0x0084:
                r0 = move-exception
                r14 = r18
                r15 = r20
                r16 = r6
            L_0x008b:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00b0
                r6.<init>()     // Catch: all -> 0x00b0
                r6.append(r4)     // Catch: all -> 0x00b0
                android.app.backup.BackupAgent r4 = android.app.backup.BackupAgent.this     // Catch: all -> 0x00b0
                java.lang.Class r4 = r4.getClass()     // Catch: all -> 0x00b0
                java.lang.String r4 = r4.getName()     // Catch: all -> 0x00b0
                r6.append(r4)     // Catch: all -> 0x00b0
                r6.append(r3)     // Catch: all -> 0x00b0
                java.lang.String r3 = r6.toString()     // Catch: all -> 0x00b0
                android.util.Log.d(r5, r3, r0)     // Catch: all -> 0x00b0
                java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch: all -> 0x00b0
                r3.<init>(r0)     // Catch: all -> 0x00b0
                throw r3     // Catch: all -> 0x00b0
            L_0x00b0:
                r0 = move-exception
                r3 = r0
            L_0x00b2:
                android.app.backup.BackupAgent r0 = android.app.backup.BackupAgent.this
                android.app.backup.BackupAgent.access$300(r0)
                android.os.Binder.restoreCallingIdentity(r12)
                r2.operationComplete(r10)     // Catch: RemoteException -> 0x00be
                goto L_0x00bf
            L_0x00be:
                r0 = move-exception
            L_0x00bf:
                int r0 = android.os.Binder.getCallingPid()
                int r4 = android.os.Process.myPid()
                if (r0 == r4) goto L_0x00d2
                libcore.io.IoUtils.closeQuietly(r18)
                libcore.io.IoUtils.closeQuietly(r19)
                libcore.io.IoUtils.closeQuietly(r20)
            L_0x00d2:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.backup.BackupAgent.BackupServiceBinder.doBackup(android.os.ParcelFileDescriptor, android.os.ParcelFileDescriptor, android.os.ParcelFileDescriptor, long, android.app.backup.IBackupCallback, int):void");
        }

        @Override // android.app.IBackupAgent
        public void doRestore(ParcelFileDescriptor data, long appVersionCode, ParcelFileDescriptor newState, int token, IBackupManager callbackBinder) throws RemoteException {
            doRestoreInternal(data, appVersionCode, newState, token, callbackBinder, null);
        }

        @Override // android.app.IBackupAgent
        public void doRestoreWithExcludedKeys(ParcelFileDescriptor data, long appVersionCode, ParcelFileDescriptor newState, int token, IBackupManager callbackBinder, List<String> excludedKeys) throws RemoteException {
            doRestoreInternal(data, appVersionCode, newState, token, callbackBinder, excludedKeys);
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00e2  */
        /* JADX WARN: Type inference failed for: r4v0, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r4v10 */
        /* JADX WARN: Type inference failed for: r4v3 */
        /* JADX WARN: Unknown variable types count: 1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void doRestoreInternal(android.os.ParcelFileDescriptor r18, long r19, android.os.ParcelFileDescriptor r21, int r22, android.app.backup.IBackupManager r23, java.util.List<java.lang.String> r24) throws android.os.RemoteException {
            /*
                Method dump skipped, instructions count: 233
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.backup.BackupAgent.BackupServiceBinder.doRestoreInternal(android.os.ParcelFileDescriptor, long, android.os.ParcelFileDescriptor, int, android.app.backup.IBackupManager, java.util.List):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00f4  */
        @Override // android.app.IBackupAgent
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void doFullBackup(android.os.ParcelFileDescriptor r17, long r18, int r20, android.app.backup.IBackupManager r21, int r22) {
            /*
                Method dump skipped, instructions count: 248
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.backup.BackupAgent.BackupServiceBinder.doFullBackup(android.os.ParcelFileDescriptor, long, int, android.app.backup.IBackupManager, int):void");
        }

        @Override // android.app.IBackupAgent
        public void doMeasureFullBackup(long quotaBytes, int token, IBackupManager callbackBinder, int transportFlags) {
            FullBackupDataOutput measureOutput = new FullBackupDataOutput(quotaBytes, transportFlags);
            BackupAgent.this.waitForSharedPrefs();
            long ident = Binder.clearCallingIdentity();
            try {
                try {
                    BackupAgent.this.onFullBackup(measureOutput);
                    Binder.restoreCallingIdentity(ident);
                    try {
                        callbackBinder.opCompleteForUser(BackupAgent.this.getBackupUserId(), token, measureOutput.getSize());
                    } catch (RemoteException e) {
                    }
                } catch (IOException ex) {
                    Log.d(TAG, "onFullBackup[M] (" + BackupAgent.this.getClass().getName() + ") threw", ex);
                    throw new RuntimeException(ex);
                } catch (RuntimeException ex2) {
                    Log.d(TAG, "onFullBackup[M] (" + BackupAgent.this.getClass().getName() + ") threw", ex2);
                    throw ex2;
                }
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(ident);
                try {
                    callbackBinder.opCompleteForUser(BackupAgent.this.getBackupUserId(), token, measureOutput.getSize());
                } catch (RemoteException e2) {
                }
                throw th;
            }
        }

        @Override // android.app.IBackupAgent
        public void doRestoreFile(ParcelFileDescriptor data, long size, int type, String domain, String path, long mode, long mtime, int token, IBackupManager callbackBinder) throws RemoteException {
            long ident = Binder.clearCallingIdentity();
            try {
                try {
                    BackupAgent.this.onRestoreFile(data, size, type, domain, path, mode, mtime);
                    BackupAgent.this.waitForSharedPrefs();
                    BackupAgent.this.reloadSharedPreferences();
                    Binder.restoreCallingIdentity(ident);
                    try {
                        callbackBinder.opCompleteForUser(BackupAgent.this.getBackupUserId(), token, 0L);
                    } catch (RemoteException e) {
                    }
                    if (Binder.getCallingPid() != Process.myPid()) {
                        IoUtils.closeQuietly(data);
                    }
                } catch (IOException e2) {
                    Log.d(TAG, "onRestoreFile (" + BackupAgent.this.getClass().getName() + ") threw", e2);
                    throw new RuntimeException(e2);
                }
            } catch (Throwable th) {
                BackupAgent.this.waitForSharedPrefs();
                BackupAgent.this.reloadSharedPreferences();
                Binder.restoreCallingIdentity(ident);
                try {
                    callbackBinder.opCompleteForUser(BackupAgent.this.getBackupUserId(), token, 0L);
                } catch (RemoteException e3) {
                }
                if (Binder.getCallingPid() != Process.myPid()) {
                    IoUtils.closeQuietly(data);
                }
                throw th;
            }
        }

        @Override // android.app.IBackupAgent
        public void doRestoreFinished(int token, IBackupManager callbackBinder) {
            long ident = Binder.clearCallingIdentity();
            try {
                try {
                    BackupAgent.this.onRestoreFinished();
                    BackupAgent.this.waitForSharedPrefs();
                    Binder.restoreCallingIdentity(ident);
                    try {
                        callbackBinder.opCompleteForUser(BackupAgent.this.getBackupUserId(), token, 0L);
                    } catch (RemoteException e) {
                    }
                } catch (Throwable th) {
                    BackupAgent.this.waitForSharedPrefs();
                    Binder.restoreCallingIdentity(ident);
                    try {
                        callbackBinder.opCompleteForUser(BackupAgent.this.getBackupUserId(), token, 0L);
                    } catch (RemoteException e2) {
                    }
                    throw th;
                }
            } catch (Exception e3) {
                Log.d(TAG, "onRestoreFinished (" + BackupAgent.this.getClass().getName() + ") threw", e3);
                throw e3;
            }
        }

        @Override // android.app.IBackupAgent
        public void fail(String message) {
            BackupAgent.this.getHandler().post(new FailRunnable(message));
        }

        @Override // android.app.IBackupAgent
        public void doQuotaExceeded(long backupDataBytes, long quotaBytes, IBackupCallback callbackBinder) {
            long ident = Binder.clearCallingIdentity();
            try {
                try {
                    BackupAgent.this.onQuotaExceeded(backupDataBytes, quotaBytes);
                    BackupAgent.this.waitForSharedPrefs();
                    Binder.restoreCallingIdentity(ident);
                    try {
                        callbackBinder.operationComplete(0L);
                    } catch (RemoteException e) {
                    }
                } catch (Exception e2) {
                    Log.d(TAG, "onQuotaExceeded(" + BackupAgent.this.getClass().getName() + ") threw", e2);
                    throw e2;
                }
            } catch (Throwable th) {
                BackupAgent.this.waitForSharedPrefs();
                Binder.restoreCallingIdentity(ident);
                try {
                    callbackBinder.operationComplete(-1L);
                } catch (RemoteException e3) {
                }
                throw th;
            }
        }
    }

    /* loaded from: classes.dex */
    static class FailRunnable implements Runnable {
        private String mMessage;

        FailRunnable(String message) {
            this.mMessage = message;
        }

        @Override // java.lang.Runnable
        public void run() {
            throw new IllegalStateException(this.mMessage);
        }
    }

    /* loaded from: classes.dex */
    public static class IncludeExcludeRules {
        private final Set<FullBackup.BackupScheme.PathWithRequiredFlags> mManifestExcludeSet;
        private final Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> mManifestIncludeMap;

        public IncludeExcludeRules(Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> manifestIncludeMap, Set<FullBackup.BackupScheme.PathWithRequiredFlags> manifestExcludeSet) {
            this.mManifestIncludeMap = manifestIncludeMap;
            this.mManifestExcludeSet = manifestExcludeSet;
        }

        public static IncludeExcludeRules emptyRules() {
            return new IncludeExcludeRules(Collections.emptyMap(), new ArraySet());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> getIncludeMap() {
            return this.mManifestIncludeMap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Set<FullBackup.BackupScheme.PathWithRequiredFlags> getExcludeSet() {
            return this.mManifestExcludeSet;
        }

        public int hashCode() {
            return Objects.hash(this.mManifestIncludeMap, this.mManifestExcludeSet);
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            IncludeExcludeRules that = (IncludeExcludeRules) object;
            if (!Objects.equals(this.mManifestIncludeMap, that.mManifestIncludeMap) || !Objects.equals(this.mManifestExcludeSet, that.mManifestExcludeSet)) {
                return false;
            }
            return true;
        }
    }
}
