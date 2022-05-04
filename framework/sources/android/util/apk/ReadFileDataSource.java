package android.util.apk;

import android.system.ErrnoException;
import android.system.Os;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.DigestException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ReadFileDataSource implements DataSource {
    private static final int CHUNK_SIZE = 1048576;
    private final FileDescriptor mFd;
    private final long mFilePosition;
    private final long mSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadFileDataSource(FileDescriptor fd, long position, long size) {
        this.mFd = fd;
        this.mFilePosition = position;
        this.mSize = size;
    }

    @Override // android.util.apk.DataSource
    public long size() {
        return this.mSize;
    }

    @Override // android.util.apk.DataSource
    public void feedIntoDataDigester(DataDigester md, long offset, int size) throws IOException, DigestException {
        ErrnoException e;
        try {
            byte[] buffer = new byte[Math.min(size, 1048576)];
            long start = this.mFilePosition + offset;
            long end = start + size;
            long curSize = Math.min(size, 1048576);
            long pos = start;
            while (pos < end) {
                int readSize = Os.pread(this.mFd, buffer, 0, (int) curSize, pos);
                try {
                    md.consume(ByteBuffer.wrap(buffer, 0, readSize));
                    pos += readSize;
                    curSize = Math.min(end - pos, 1048576L);
                } catch (ErrnoException e2) {
                    e = e2;
                    throw new IOException(e);
                }
            }
        } catch (ErrnoException e3) {
            e = e3;
        }
    }
}
