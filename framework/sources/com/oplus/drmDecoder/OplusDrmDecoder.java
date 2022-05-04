package com.oplus.drmDecoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.android.internal.widget.MessagingMessage;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import libcore.io.IoBridge;
/* loaded from: classes4.dex */
public class OplusDrmDecoder {
    private static final int HEADER_BUFFER_SIZE = 128;
    private static final String TAG = "OplusDrmDecoder";
    private static boolean sIsOmaDrmEnabled;

    static {
        sIsOmaDrmEnabled = false;
        sIsOmaDrmEnabled = false;
    }

    public static Bitmap decodeDrmImageIfNeeded(byte[] header, InputStream left, BitmapFactory.Options opts) {
        StringBuilder sb;
        if (!sIsOmaDrmEnabled) {
            return null;
        }
        if (opts != null && opts.inJustDecodeBounds && opts.outWidth > 0 && opts.outHeight > 0) {
            return null;
        }
        Log.d(TAG, "decodeDrmImageIfNeeded with stream");
        if (header == null || !isDrmFile(header)) {
            return null;
        }
        Bitmap bm = null;
        OmaDrmInputStream is = null;
        try {
            try {
                is = new OmaDrmInputStream(left, true);
                bm = BitmapFactory.decodeStream(is, null, opts);
                try {
                    is.close();
                } catch (IOException e) {
                    e = e;
                    sb = new StringBuilder();
                    sb.append("Unable to close drm file: ");
                    sb.append(e);
                    Log.e(TAG, sb.toString());
                    return bm;
                }
            } catch (Exception e2) {
                Log.e(TAG, "Error while getBitmap! " + e2);
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e3) {
                        e = e3;
                        sb = new StringBuilder();
                        sb.append("Unable to close drm file: ");
                        sb.append(e);
                        Log.e(TAG, sb.toString());
                        return bm;
                    }
                }
            }
            return bm;
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e4) {
                    Log.e(TAG, "Unable to close drm file: " + e4);
                }
            }
            throw th;
        }
    }

    public static Bitmap decodeDrmImageIfNeeded(FileDescriptor fd, BitmapFactory.Options opts) {
        StringBuilder sb;
        boolean isError = false;
        if (!sIsOmaDrmEnabled) {
            return null;
        }
        if (opts != null && opts.inJustDecodeBounds && opts.outWidth > 0 && opts.outHeight > 0) {
            return null;
        }
        Log.d(TAG, "decodeDrmImageIfNeeded with fd");
        long offset = -1;
        try {
            try {
                offset = Os.lseek(fd, 0L, OsConstants.SEEK_CUR);
                Os.lseek(fd, 0L, OsConstants.SEEK_SET);
                byte[] header = new byte[128];
                int readSize = IoBridge.read(fd, header, 0, 128);
                if (readSize == 128) {
                    if (isDrmFile(header)) {
                        if (offset != -1) {
                            try {
                                Os.lseek(fd, offset, OsConstants.SEEK_SET);
                            } catch (ErrnoException errno1) {
                                Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno1);
                                isError = true;
                            }
                        }
                        if (isError) {
                            return null;
                        }
                        Bitmap bm = null;
                        OmaDrmInputStream is = null;
                        try {
                            try {
                                is = new OmaDrmInputStream(fd, true);
                                Log.d(TAG, "pass OmaDrmInputStream + " + is);
                                bm = BitmapFactory.decodeStream(is, null, opts);
                                Log.d(TAG, "OmaDrmInputStream bm is + " + bm);
                            } catch (Exception e) {
                                Log.e(TAG, "Error while getBitmap! " + e);
                                if (is != null) {
                                    try {
                                        is.close();
                                    } catch (IOException e2) {
                                        e = e2;
                                        sb = new StringBuilder();
                                        sb.append("Unable to close drm file: ");
                                        sb.append(e);
                                        Log.e(TAG, sb.toString());
                                        return bm;
                                    }
                                }
                            }
                            try {
                                is.close();
                            } catch (IOException e3) {
                                e = e3;
                                sb = new StringBuilder();
                                sb.append("Unable to close drm file: ");
                                sb.append(e);
                                Log.e(TAG, sb.toString());
                                return bm;
                            }
                            return bm;
                        } catch (Throwable th) {
                            if (is != null) {
                                try {
                                    is.close();
                                } catch (IOException e4) {
                                    Log.e(TAG, "Unable to close drm file: " + e4);
                                }
                            }
                            throw th;
                        }
                    }
                }
                if (offset != -1) {
                    try {
                        Os.lseek(fd, offset, OsConstants.SEEK_SET);
                    } catch (ErrnoException errno12) {
                        Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno12);
                    }
                }
                return null;
            } catch (Throwable th2) {
                if (offset != -1) {
                    try {
                        Os.lseek(fd, offset, OsConstants.SEEK_SET);
                    } catch (ErrnoException errno13) {
                        Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno13);
                    }
                }
                throw th2;
            }
        } catch (ErrnoException errno) {
            Log.e(TAG, "decodeDrmImageIfNeeded seek fd to beginning with ", errno);
            if (offset != -1) {
                try {
                    Os.lseek(fd, offset, OsConstants.SEEK_SET);
                } catch (ErrnoException errno14) {
                    Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno14);
                }
            }
            return null;
        } catch (IOException e5) {
            Log.e(TAG, "decodeDrmImageIfNeeded get header with ", e5);
            if (offset != -1) {
                try {
                    Os.lseek(fd, offset, OsConstants.SEEK_SET);
                } catch (ErrnoException errno15) {
                    Log.e(TAG, "decodeDrmImageIfNeeded seek fd to initial offset with ", errno15);
                }
            }
            return null;
        }
    }

    public static Bitmap decodeDrmImageIfNeeded(byte[] data, BitmapFactory.Options opts) {
        if (!sIsOmaDrmEnabled) {
            return null;
        }
        if (opts != null && opts.inJustDecodeBounds && opts.outWidth > 0 && opts.outHeight > 0) {
            return null;
        }
        Log.d(TAG, "decodeDrmImageIfNeeded with data");
        isDrmFile(data);
        return null;
    }

    private static boolean isDrmFile(byte[] header) {
        if (header == null || header.length < 128) {
            return false;
        }
        String fwlk_magic = new String(header, 0, 8);
        if (fwlk_magic.startsWith("FWLK")) {
            String image_magic = new String(header, 8, 6);
            if (image_magic.startsWith(MessagingMessage.IMAGE_MIME_TYPE_PREFIX)) {
                Log.v(TAG, "isDrmFile: this is a fwlk file: " + fwlk_magic);
                return true;
            }
        }
        return false;
    }
}
