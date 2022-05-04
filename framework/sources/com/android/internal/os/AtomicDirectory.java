package com.android.internal.os;

import android.os.FileUtils;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes4.dex */
public final class AtomicDirectory {
    private static final String LOG_TAG = AtomicDirectory.class.getSimpleName();
    private final File mBackupDirectory;
    private final File mBaseDirectory;
    private final ArrayMap<File, FileOutputStream> mOpenFiles = new ArrayMap<>();

    public AtomicDirectory(File baseDirectory) {
        Preconditions.checkNotNull(baseDirectory, "baseDirectory cannot be null");
        this.mBaseDirectory = baseDirectory;
        this.mBackupDirectory = new File(baseDirectory.getPath() + "_bak");
    }

    public File getBackupDirectory() {
        return this.mBackupDirectory;
    }

    public File startRead() throws IOException {
        restore();
        ensureBaseDirectory();
        return this.mBaseDirectory;
    }

    public void finishRead() {
    }

    public File startWrite() throws IOException {
        backup();
        ensureBaseDirectory();
        return this.mBaseDirectory;
    }

    public FileOutputStream openWrite(File file) throws IOException {
        if (file.isDirectory() || !file.getParentFile().equals(this.mBaseDirectory)) {
            throw new IllegalArgumentException("Must be a file in " + this.mBaseDirectory);
        } else if (!this.mOpenFiles.containsKey(file)) {
            FileOutputStream destination = new FileOutputStream(file);
            this.mOpenFiles.put(file, destination);
            return destination;
        } else {
            throw new IllegalArgumentException("Already open file " + file.getAbsolutePath());
        }
    }

    public void closeWrite(FileOutputStream destination) {
        int indexOfValue = this.mOpenFiles.indexOfValue(destination);
        if (indexOfValue >= 0) {
            this.mOpenFiles.removeAt(indexOfValue);
            FileUtils.sync(destination);
            FileUtils.closeQuietly(destination);
            return;
        }
        throw new IllegalArgumentException("Unknown file stream " + destination);
    }

    public void failWrite(FileOutputStream destination) {
        int indexOfValue = this.mOpenFiles.indexOfValue(destination);
        if (indexOfValue >= 0) {
            this.mOpenFiles.removeAt(indexOfValue);
            FileUtils.closeQuietly(destination);
            return;
        }
        throw new IllegalArgumentException("Unknown file stream " + destination);
    }

    public void finishWrite() {
        throwIfSomeFilesOpen();
        syncDirectory(this.mBaseDirectory);
        syncParentDirectory();
        deleteDirectory(this.mBackupDirectory);
        syncParentDirectory();
    }

    public void failWrite() {
        throwIfSomeFilesOpen();
        try {
            restore();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to restore in failWrite()", e);
        }
    }

    public boolean exists() {
        return this.mBaseDirectory.exists() || this.mBackupDirectory.exists();
    }

    public void delete() {
        boolean deleted = false;
        if (this.mBaseDirectory.exists()) {
            deleted = false | deleteDirectory(this.mBaseDirectory);
        }
        if (this.mBackupDirectory.exists()) {
            deleted |= deleteDirectory(this.mBackupDirectory);
        }
        if (deleted) {
            syncParentDirectory();
        }
    }

    private void ensureBaseDirectory() throws IOException {
        if (!this.mBaseDirectory.exists()) {
            if (this.mBaseDirectory.mkdirs()) {
                FileUtils.setPermissions(this.mBaseDirectory.getPath(), 505, -1, -1);
                return;
            }
            throw new IOException("Failed to create directory " + this.mBaseDirectory);
        }
    }

    private void throwIfSomeFilesOpen() {
        if (!this.mOpenFiles.isEmpty()) {
            throw new IllegalStateException("Unclosed files: " + Arrays.toString(this.mOpenFiles.keySet().toArray()));
        }
    }

    private void backup() throws IOException {
        if (this.mBaseDirectory.exists()) {
            if (this.mBackupDirectory.exists()) {
                deleteDirectory(this.mBackupDirectory);
            }
            if (this.mBaseDirectory.renameTo(this.mBackupDirectory)) {
                syncParentDirectory();
                return;
            }
            throw new IOException("Failed to backup " + this.mBaseDirectory + " to " + this.mBackupDirectory);
        }
    }

    private void restore() throws IOException {
        if (this.mBackupDirectory.exists()) {
            if (this.mBaseDirectory.exists()) {
                deleteDirectory(this.mBaseDirectory);
            }
            if (this.mBackupDirectory.renameTo(this.mBaseDirectory)) {
                syncParentDirectory();
                return;
            }
            throw new IOException("Failed to restore " + this.mBackupDirectory + " to " + this.mBaseDirectory);
        }
    }

    private static boolean deleteDirectory(File directory) {
        return FileUtils.deleteContentsAndDir(directory);
    }

    private void syncParentDirectory() {
        syncDirectory(this.mBaseDirectory.getParentFile());
    }

    private static void syncDirectory(File directory) {
        FileDescriptor fd;
        String path = directory.getAbsolutePath();
        try {
            try {
                fd = Os.open(path, OsConstants.O_RDONLY, 0);
                try {
                    Os.fsync(fd);
                } catch (ErrnoException e) {
                    String str = LOG_TAG;
                    Log.e(str, "Failed to fsync " + path, e);
                }
            } finally {
                FileUtils.closeQuietly(fd);
            }
        } catch (ErrnoException e2) {
            String str2 = LOG_TAG;
            Log.e(str2, "Failed to open " + path, e2);
        }
    }
}
