package com.oplus.drmDecoder;

import android.media.MediaDrmException;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes4.dex */
public class OmaDrmInputStream extends InputStream {
    private static final int DRM_DUMP_BYTE_SIZE = 4096;
    private static final boolean IS_DRM_USES_REDIRECTED_INPUT_STREAM = true;
    private static final String TAG = "OmaDrmInputStream";
    private static final int UNIQUE_ID_NOT_INITIALIZED = -1;
    private static int sSeqUniqueId = 0;
    private boolean mConsumeRights;
    private String mDumpFilePath;
    private FileDescriptor mFD;
    private long mFileSize;
    FileInputStream mRedirectedInputStream = null;
    private int mUniqueId;
    private File tmpFile;

    private static native int _closeOmaDrmInputStream(int i);

    private static native long _fileSizeOmaDrmInputStream(int i);

    private static native Object _getOmaDrmInfoOmaDrmInputStream(int i);

    private static native int _initializeOmaDrmInputStream(int i, FileDescriptor fileDescriptor, boolean z);

    private static native int _readOmaDrmInputStream(int i, byte[] bArr, int i2, int i3);

    public OmaDrmInputStream(InputStream is, boolean consumeRights) throws FileNotFoundException, MediaDrmException {
        this.mUniqueId = -1;
        this.mFD = null;
        this.mConsumeRights = consumeRights;
        try {
            if (is instanceof FileInputStream) {
                this.mFD = ((FileInputStream) is).getFD();
            }
        } catch (IOException e) {
            Log.e(TAG, "OmaDrmInputStream getFd fail ", e);
        }
        if (this.mFD != null) {
            int generateUniqueId = generateUniqueId();
            this.mUniqueId = generateUniqueId;
            int result = initializeInputStream(generateUniqueId, this.mFD, this.mConsumeRights);
            if (result < 0) {
                try {
                    close();
                } catch (IOException e2) {
                    Log.e(TAG, "Eception while closing OmaDrmInputStream. " + e2);
                }
                throw new MediaDrmException("MediaDrmException! May Rights invalid!");
            }
        }
        this.mFileSize = getFileSizeFromOmaDrmInputStream(this.mUniqueId);
        prepareRedirectedInputStream();
    }

    public OmaDrmInputStream(FileDescriptor fd, boolean consumeRights) throws FileNotFoundException, MediaDrmException {
        this.mUniqueId = -1;
        this.mFD = null;
        this.mConsumeRights = consumeRights;
        this.mFD = fd;
        if (fd != null) {
            int generateUniqueId = generateUniqueId();
            this.mUniqueId = generateUniqueId;
            int result = initializeInputStream(generateUniqueId, this.mFD, this.mConsumeRights);
            if (result < 0) {
                try {
                    close();
                } catch (IOException e) {
                    Log.e(TAG, "Eception while closing OmaDrmInputStream. " + e);
                }
                throw new MediaDrmException("MediaDrmException! May Rights invalid!");
            }
        }
        int result2 = this.mUniqueId;
        this.mFileSize = getFileSizeFromOmaDrmInputStream(result2);
        prepareRedirectedInputStream();
    }

    private void prepareRedirectedInputStream() {
        try {
            this.mRedirectedInputStream = new FileInputStream(new File(creadeDrmDumpFile()));
        } catch (FileNotFoundException e) {
            Log.e(TAG, "OmaDrmInputStream mRedirectedInputStream failed");
            e.printStackTrace();
        }
    }

    private void deleteDrmDumpFile() {
        if (this.mDumpFilePath != null) {
            File file = new File(this.mDumpFilePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public File getTmpFile() {
        return this.tmpFile;
    }

    private String creadeDrmDumpFile() {
        byte[] bytes = new byte[4096];
        int offset = 0;
        FileOutputStream fos = null;
        try {
            try {
                File file = new File("/storage/emulated/0/.drm" + System.currentTimeMillis() + ".jpg");
                if (file.exists()) {
                    file.delete();
                }
                Log.d(TAG, file.getAbsolutePath());
                file.createNewFile();
                this.tmpFile = file;
                this.mDumpFilePath = file.getAbsolutePath();
                fos = new FileOutputStream(file);
                Log.d(TAG, "createDrmDumpFIle mDumpFIlePath = " + this.mDumpFilePath);
                while (true) {
                    int length = readFromOmaDrmInputStream(this.mUniqueId, bytes, 4096, offset);
                    if (length > 0) {
                        fos.write(bytes, 0, length);
                        offset += length;
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                fos.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.w(TAG, "createDrmDumpFIle fail");
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            return this.mDumpFilePath;
        } catch (Throwable th) {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    private synchronized int generateUniqueId() {
        int id;
        id = sSeqUniqueId;
        int i = id + 1;
        sSeqUniqueId = i;
        if (i >= Integer.MAX_VALUE) {
            sSeqUniqueId = 0;
        }
        return id;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.mUniqueId != -1) {
            byte[] bArr = new byte[1];
            int result = this.mRedirectedInputStream.read();
            return result;
        }
        throw new IOException("DrmInputStream has not been initialized! mUniqueId = " + this.mUniqueId);
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int offset, int numBytes) throws IOException {
        if (this.mUniqueId != -1) {
            int length = this.mRedirectedInputStream.read(buffer, offset, numBytes);
            return length;
        }
        throw new IOException("DrmInputStream has not been initialized! mUniqueId = " + this.mUniqueId);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        FileInputStream fileInputStream = this.mRedirectedInputStream;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                Log.w(TAG, "Close InputStream failed.");
            }
        }
        releaseInputStream(this.mUniqueId);
        deleteDrmDumpFile();
    }

    public long size() {
        return this.mFileSize;
    }

    public static int initializeInputStream(int uniqueId, FileDescriptor fd, boolean consumeRights) {
        return _initializeOmaDrmInputStream(uniqueId, fd, consumeRights);
    }

    public static int releaseInputStream(int uniqueId) {
        return _closeOmaDrmInputStream(uniqueId);
    }

    public static int readFromOmaDrmInputStream(int uniqueId, byte[] buffer, int numBytes, int offset) {
        return _readOmaDrmInputStream(uniqueId, buffer, numBytes, offset);
    }

    public static long getFileSizeFromOmaDrmInputStream(int uniqueId) {
        return _fileSizeOmaDrmInputStream(uniqueId);
    }

    public static Object getOmaDrmInfoOmaDrmInputStream(int uniqueId) {
        return _getOmaDrmInfoOmaDrmInputStream(uniqueId);
    }
}
