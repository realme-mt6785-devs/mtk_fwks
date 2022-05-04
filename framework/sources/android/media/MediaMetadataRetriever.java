package android.media;

import android.annotation.SystemApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetrics;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class MediaMetadataRetriever implements AutoCloseable {
    private static final int EMBEDDED_PICTURE_TYPE_ANY = 65535;
    public static final int METADATA_KEY_ALBUM = 1;
    public static final int METADATA_KEY_ALBUMARTIST = 13;
    public static final int METADATA_KEY_ARTIST = 2;
    public static final int METADATA_KEY_AUTHOR = 3;
    public static final int METADATA_KEY_BITRATE = 20;
    public static final int METADATA_KEY_BITS_PER_SAMPLE = 39;
    public static final int METADATA_KEY_CAPTURE_FRAMERATE = 25;
    public static final int METADATA_KEY_CD_TRACK_NUMBER = 0;
    public static final int METADATA_KEY_COLOR_RANGE = 37;
    public static final int METADATA_KEY_COLOR_STANDARD = 35;
    public static final int METADATA_KEY_COLOR_TRANSFER = 36;
    public static final int METADATA_KEY_COMPILATION = 15;
    public static final int METADATA_KEY_COMPOSER = 4;
    public static final int METADATA_KEY_DATE = 5;
    public static final int METADATA_KEY_DISC_NUMBER = 14;
    public static final int METADATA_KEY_DURATION = 9;
    public static final int METADATA_KEY_EXIF_LENGTH = 34;
    public static final int METADATA_KEY_EXIF_OFFSET = 33;
    public static final int METADATA_KEY_GENRE = 6;
    public static final int METADATA_KEY_HAS_AUDIO = 16;
    public static final int METADATA_KEY_HAS_IMAGE = 26;
    public static final int METADATA_KEY_HAS_VIDEO = 17;
    public static final int METADATA_KEY_IMAGE_COUNT = 27;
    public static final int METADATA_KEY_IMAGE_HEIGHT = 30;
    public static final int METADATA_KEY_IMAGE_PRIMARY = 28;
    public static final int METADATA_KEY_IMAGE_ROTATION = 31;
    public static final int METADATA_KEY_IMAGE_WIDTH = 29;
    public static final int METADATA_KEY_IS_DRM = 22;
    public static final int METADATA_KEY_LOCATION = 23;
    public static final int METADATA_KEY_MIMETYPE = 12;
    public static final int METADATA_KEY_NUM_TRACKS = 10;
    public static final int METADATA_KEY_SAMPLERATE = 38;
    public static final int METADATA_KEY_TIMED_TEXT_LANGUAGES = 21;
    public static final int METADATA_KEY_TITLE = 7;
    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public static final int METADATA_KEY_VIDEO_CODEC_MIME_TYPE = 40;
    public static final int METADATA_KEY_VIDEO_FRAME_COUNT = 32;
    public static final int METADATA_KEY_VIDEO_HEIGHT = 19;
    public static final int METADATA_KEY_VIDEO_ROTATION = 24;
    public static final int METADATA_KEY_VIDEO_WIDTH = 18;
    public static final int METADATA_KEY_WRITER = 11;
    public static final int METADATA_KEY_XMP_LENGTH = 42;
    public static final int METADATA_KEY_XMP_OFFSET = 41;
    public static final int METADATA_KEY_YEAR = 8;
    public static final int OPTION_CLOSEST = 3;
    public static final int OPTION_CLOSEST_SYNC = 2;
    public static final int OPTION_NEXT_SYNC = 1;
    public static final int OPTION_PREVIOUS_SYNC = 0;
    private static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Afro-Punk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    private static final String TAG = "MediaMetadataRetriever";
    private long mNativeContext;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Option {
    }

    private native List<Bitmap> _getFrameAtIndex(int i, int i2, BitmapParams bitmapParams);

    private native Bitmap _getFrameAtTime(long j, int i, int i2, int i3, BitmapParams bitmapParams);

    private native Bitmap _getImageAtIndex(int i, BitmapParams bitmapParams);

    private native void _setDataSource(MediaDataSource mediaDataSource) throws IllegalArgumentException;

    private native void _setDataSource(IBinder iBinder, String str, String[] strArr, String[] strArr2) throws IllegalArgumentException;

    private native void _setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IllegalArgumentException;

    private native byte[] getEmbeddedPicture(int i);

    private native String nativeExtractMetadata(int i);

    private final native void native_finalize();

    private static native void native_init();

    private native void native_setup();

    public native Bitmap getThumbnailImageAtIndex(int i, BitmapParams bitmapParams, int i2, int i3);

    public native void release();

    static {
        System.loadLibrary("media_jni");
        native_init();
    }

    public MediaMetadataRetriever() {
        native_setup();
    }

    public void setDataSource(String path) throws IllegalArgumentException {
        if (path != null) {
            Uri uri = Uri.parse(path);
            String scheme = uri.getScheme();
            if (ContentResolver.SCHEME_FILE.equals(scheme)) {
                path = uri.getPath();
            } else if (scheme != null) {
                setDataSource(path, new HashMap());
                return;
            }
            FileInputStream is = null;
            try {
                try {
                    try {
                        is = new FileInputStream(path);
                        FileDescriptor fd = is.getFD();
                        setDataSource(fd, 0L, 576460752303423487L);
                        try {
                            is.close();
                        } catch (Exception e) {
                        }
                    } catch (Throwable th) {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (Exception e2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    throw new IllegalArgumentException("couldn't open " + path);
                }
            } catch (FileNotFoundException e4) {
                throw new IllegalArgumentException(path + " does not exist");
            }
        } else {
            throw new IllegalArgumentException("null path");
        }
    }

    public void setDataSource(String uri, Map<String, String> headers) throws IllegalArgumentException {
        int i = 0;
        String[] keys = new String[headers.size()];
        String[] values = new String[headers.size()];
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            keys[i] = entry.getKey();
            values[i] = entry.getValue();
            i++;
        }
        _setDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(uri), uri, keys, values);
    }

    public void setDataSource(FileDescriptor fd, long offset, long length) throws IllegalArgumentException {
        try {
            ParcelFileDescriptor modernFd = FileUtils.convertToModernFd(fd);
            if (modernFd == null) {
                _setDataSource(fd, offset, length);
            } else {
                _setDataSource(modernFd.getFileDescriptor(), offset, length);
            }
            if (modernFd != null) {
                modernFd.close();
            }
        } catch (IOException e) {
            Log.w(TAG, "Ignoring IO error while setting data source", e);
        }
    }

    public void setDataSource(FileDescriptor fd) throws IllegalArgumentException {
        setDataSource(fd, 0L, 576460752303423487L);
    }

    public void setDataSource(Context context, Uri uri) throws IllegalArgumentException, SecurityException {
        if (uri != null) {
            String scheme = uri.getScheme();
            if (scheme == null || scheme.equals(ContentResolver.SCHEME_FILE)) {
                setDataSource(uri.getPath());
                return;
            }
            AssetFileDescriptor fd = null;
            try {
                ContentResolver resolver = context.getContentResolver();
                try {
                    boolean optimize = SystemProperties.getBoolean("fuse.sys.transcode_retriever_optimize", false);
                    Bundle opts = new Bundle();
                    opts.putBoolean("android.provider.extra.ACCEPT_ORIGINAL_MEDIA_FORMAT", true);
                    AssetFileDescriptor fd2 = optimize ? resolver.openTypedAssetFileDescriptor(uri, "*/*", opts) : resolver.openAssetFileDescriptor(uri, "r");
                    if (fd2 != null) {
                        FileDescriptor descriptor = fd2.getFileDescriptor();
                        if (descriptor.valid()) {
                            if (fd2.getDeclaredLength() < 0) {
                                setDataSource(descriptor);
                            } else {
                                setDataSource(descriptor, fd2.getStartOffset(), fd2.getDeclaredLength());
                            }
                            if (fd2 != null) {
                                try {
                                    fd2.close();
                                } catch (IOException e) {
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("got invalid FileDescriptor for " + uri);
                        }
                    } else {
                        throw new IllegalArgumentException("got null FileDescriptor for " + uri);
                    }
                } catch (FileNotFoundException e2) {
                    throw new IllegalArgumentException("could not access " + uri);
                }
            } catch (SecurityException e3) {
                if (0 != 0) {
                    try {
                        fd.close();
                    } catch (IOException e4) {
                    }
                }
                setDataSource(uri.toString());
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        fd.close();
                    } catch (IOException e5) {
                    }
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("null uri");
        }
    }

    public void setDataSource(MediaDataSource dataSource) throws IllegalArgumentException {
        _setDataSource(dataSource);
    }

    public String extractMetadata(int keyCode) {
        String meta = nativeExtractMetadata(keyCode);
        if (keyCode == 6) {
            return convertGenreTag(meta);
        }
        return meta;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x00cb, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String convertGenreTag(java.lang.String r9) {
        /*
            r8 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 0
            char r0 = r9.charAt(r0)
            boolean r0 = java.lang.Character.isDigit(r0)
            if (r0 == 0) goto L_0x0024
            int r0 = java.lang.Integer.parseInt(r9)     // Catch: NumberFormatException -> 0x0022
            if (r0 < 0) goto L_0x0021
            java.lang.String[] r2 = android.media.MediaMetadataRetriever.STANDARD_GENRES     // Catch: NumberFormatException -> 0x0022
            int r3 = r2.length     // Catch: NumberFormatException -> 0x0022
            if (r0 >= r3) goto L_0x0021
            r1 = r2[r0]     // Catch: NumberFormatException -> 0x0022
            return r1
        L_0x0021:
            goto L_0x0023
        L_0x0022:
            r0 = move-exception
        L_0x0023:
            return r1
        L_0x0024:
            r0 = 0
            r2 = 0
        L_0x0026:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0043
            if (r0 != 0) goto L_0x0034
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r0 = r3
        L_0x0034:
            int r3 = r0.length()
            if (r3 == 0) goto L_0x003f
            java.lang.String r3 = ", "
            r0.append(r3)
        L_0x003f:
            r0.append(r2)
            r2 = 0
        L_0x0043:
            boolean r3 = android.text.TextUtils.isEmpty(r9)
            if (r3 == 0) goto L_0x0058
            if (r0 == 0) goto L_0x0057
            int r3 = r0.length()
            if (r3 != 0) goto L_0x0053
            goto L_0x0057
        L_0x0053:
            java.lang.String r1 = r0.toString()
        L_0x0057:
            return r1
        L_0x0058:
            java.lang.String r3 = "(RX)"
            boolean r3 = r9.startsWith(r3)
            r4 = 4
            if (r3 == 0) goto L_0x0068
            java.lang.String r2 = "Remix"
            java.lang.String r9 = r9.substring(r4)
            goto L_0x0026
        L_0x0068:
            java.lang.String r3 = "(CR)"
            boolean r3 = r9.startsWith(r3)
            if (r3 == 0) goto L_0x0077
            java.lang.String r2 = "Cover"
            java.lang.String r9 = r9.substring(r4)
            goto L_0x0026
        L_0x0077:
            java.lang.String r3 = "(("
            boolean r3 = r9.startsWith(r3)
            r4 = -1
            r5 = 41
            r6 = 1
            if (r3 == 0) goto L_0x009d
            int r3 = r9.indexOf(r5)
            if (r3 != r4) goto L_0x0090
            java.lang.String r2 = r9.substring(r6)
            java.lang.String r9 = ""
            goto L_0x009c
        L_0x0090:
            int r4 = r3 + 1
            java.lang.String r2 = r9.substring(r6, r4)
            int r4 = r3 + 1
            java.lang.String r9 = r9.substring(r4)
        L_0x009c:
            goto L_0x0026
        L_0x009d:
            java.lang.String r3 = "("
            boolean r3 = r9.startsWith(r3)
            if (r3 == 0) goto L_0x00ce
            int r3 = r9.indexOf(r5)
            if (r3 != r4) goto L_0x00ac
            return r1
        L_0x00ac:
            java.lang.String r4 = r9.substring(r6, r3)
            java.lang.String r5 = r4.toString()     // Catch: NumberFormatException -> 0x00cc
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: NumberFormatException -> 0x00cc
            if (r5 < 0) goto L_0x00cb
            java.lang.String[] r6 = android.media.MediaMetadataRetriever.STANDARD_GENRES     // Catch: NumberFormatException -> 0x00cc
            int r7 = r6.length     // Catch: NumberFormatException -> 0x00cc
            if (r5 >= r7) goto L_0x00cb
            r6 = r6[r5]     // Catch: NumberFormatException -> 0x00cc
            r2 = r6
            int r5 = r3 + 1
            java.lang.String r9 = r9.substring(r5)
            goto L_0x0026
        L_0x00cb:
            return r1
        L_0x00cc:
            r5 = move-exception
            return r1
        L_0x00ce:
            r2 = r9
            java.lang.String r9 = ""
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.MediaMetadataRetriever.convertGenreTag(java.lang.String):java.lang.String");
    }

    public Bitmap getFrameAtTime(long timeUs, int option) {
        if (option >= 0 && option <= 3) {
            return _getFrameAtTime(timeUs, option, -1, -1, null);
        }
        throw new IllegalArgumentException("Unsupported option: " + option);
    }

    public Bitmap getFrameAtTime(long timeUs, int option, BitmapParams params) {
        if (option >= 0 && option <= 3) {
            return _getFrameAtTime(timeUs, option, -1, -1, params);
        }
        throw new IllegalArgumentException("Unsupported option: " + option);
    }

    public Bitmap getScaledFrameAtTime(long timeUs, int option, int dstWidth, int dstHeight) {
        validate(option, dstWidth, dstHeight);
        return _getFrameAtTime(timeUs, option, dstWidth, dstHeight, null);
    }

    public Bitmap getScaledFrameAtTime(long timeUs, int option, int dstWidth, int dstHeight, BitmapParams params) {
        validate(option, dstWidth, dstHeight);
        return _getFrameAtTime(timeUs, option, dstWidth, dstHeight, params);
    }

    private void validate(int option, int dstWidth, int dstHeight) {
        if (option < 0 || option > 3) {
            throw new IllegalArgumentException("Unsupported option: " + option);
        } else if (dstWidth <= 0) {
            throw new IllegalArgumentException("Invalid width: " + dstWidth);
        } else if (dstHeight <= 0) {
            throw new IllegalArgumentException("Invalid height: " + dstHeight);
        }
    }

    public Bitmap getFrameAtTime(long timeUs) {
        return getFrameAtTime(timeUs, 2);
    }

    public Bitmap getFrameAtTime() {
        return _getFrameAtTime(-1L, 2, -1, -1, null);
    }

    /* loaded from: classes2.dex */
    public static final class BitmapParams {
        private Bitmap.Config inPreferredConfig = Bitmap.Config.ARGB_8888;
        private Bitmap.Config outActualConfig = Bitmap.Config.ARGB_8888;

        public void setPreferredConfig(Bitmap.Config config) {
            if (config != null) {
                this.inPreferredConfig = config;
                return;
            }
            throw new IllegalArgumentException("preferred config can't be null");
        }

        public Bitmap.Config getPreferredConfig() {
            return this.inPreferredConfig;
        }

        public Bitmap.Config getActualConfig() {
            return this.outActualConfig;
        }
    }

    public Bitmap getFrameAtIndex(int frameIndex, BitmapParams params) {
        List<Bitmap> bitmaps = getFramesAtIndex(frameIndex, 1, params);
        return bitmaps.get(0);
    }

    public Bitmap getFrameAtIndex(int frameIndex) {
        List<Bitmap> bitmaps = getFramesAtIndex(frameIndex, 1);
        return bitmaps.get(0);
    }

    public List<Bitmap> getFramesAtIndex(int frameIndex, int numFrames, BitmapParams params) {
        return getFramesAtIndexInternal(frameIndex, numFrames, params);
    }

    public List<Bitmap> getFramesAtIndex(int frameIndex, int numFrames) {
        return getFramesAtIndexInternal(frameIndex, numFrames, null);
    }

    private List<Bitmap> getFramesAtIndexInternal(int frameIndex, int numFrames, BitmapParams params) {
        if (MediaMetrics.Value.YES.equals(extractMetadata(17))) {
            int frameCount = Integer.parseInt(extractMetadata(32));
            if (frameIndex >= 0 && numFrames >= 1 && frameIndex < frameCount && frameIndex <= frameCount - numFrames) {
                return _getFrameAtIndex(frameIndex, numFrames, params);
            }
            throw new IllegalArgumentException("Invalid frameIndex or numFrames: " + frameIndex + ", " + numFrames);
        }
        throw new IllegalStateException("Does not contail video or image sequences");
    }

    public Bitmap getImageAtIndex(int imageIndex, BitmapParams params) {
        return getImageAtIndexInternal(imageIndex, params);
    }

    public Bitmap getImageAtIndex(int imageIndex) {
        return getImageAtIndexInternal(imageIndex, null);
    }

    public Bitmap getPrimaryImage(BitmapParams params) {
        return getImageAtIndexInternal(-1, params);
    }

    public Bitmap getPrimaryImage() {
        return getImageAtIndexInternal(-1, null);
    }

    private Bitmap getImageAtIndexInternal(int imageIndex, BitmapParams params) {
        if (MediaMetrics.Value.YES.equals(extractMetadata(26))) {
            String imageCount = extractMetadata(27);
            if (imageIndex < Integer.parseInt(imageCount)) {
                return _getImageAtIndex(imageIndex, params);
            }
            throw new IllegalArgumentException("Invalid image index: " + imageCount);
        }
        throw new IllegalStateException("Does not contail still images");
    }

    public byte[] getEmbeddedPicture() {
        return getEmbeddedPicture(65535);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        release();
    }

    protected void finalize() throws Throwable {
        try {
            native_finalize();
        } finally {
            super.finalize();
        }
    }
}
