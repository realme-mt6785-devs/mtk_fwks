package com.android.internal.os;

import com.android.internal.util.ProcFileReader;
import java.io.FileInputStream;
import java.io.IOException;
import libcore.io.IoUtils;
/* loaded from: classes4.dex */
public class ProcLocksReader {
    private final String mPath;

    public ProcLocksReader() {
        this.mPath = "/proc/locks";
    }

    public ProcLocksReader(String path) {
        this.mPath = path;
    }

    public boolean hasFileLocks(int pid) throws Exception {
        ProcFileReader reader = null;
        long last = -1;
        try {
            try {
                reader = new ProcFileReader(new FileInputStream(this.mPath));
                while (reader.hasMoreData()) {
                    long id = reader.nextLong(true);
                    if (id == last) {
                        reader.finishLine();
                    } else {
                        reader.nextIgnored();
                        reader.nextIgnored();
                        reader.nextIgnored();
                        int owner = reader.nextInt();
                        if (owner == pid) {
                            return true;
                        }
                        reader.finishLine();
                        last = id;
                    }
                }
                IoUtils.closeQuietly(reader);
                return false;
            } catch (IOException e) {
                throw new Exception("Exception parsing /proc/locks");
            }
        } finally {
            IoUtils.closeQuietly(reader);
        }
    }
}
