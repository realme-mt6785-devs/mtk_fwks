package android.media;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefLong;
import com.oplus.reflect.RefMethod;
import com.oplus.reflect.RefObject;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class OplusMirrorMediaHTTPConnection {
    public static Class<?> TYPE = RefClass.load(OplusMirrorMediaHTTPConnection.class, MediaHTTPConnection.class);
    public static RefLong mCurrentOffset;
    public static RefObject<InputStream> mInputStream;
    @MethodParams({long.class})
    public static RefMethod<Void> seekTo;
}
