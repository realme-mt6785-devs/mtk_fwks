package android.util.imetracing;

import android.util.proto.ProtoOutputStream;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.SurroundingText;
/* loaded from: classes3.dex */
public class InputConnectionHelper {
    public static final boolean DUMP_TEXT = false;
    static final String TAG = "InputConnectionHelper";

    private InputConnectionHelper() {
    }

    public static ProtoOutputStream buildGetTextAfterCursorProto(int length, int flags, CharSequence result) {
        ProtoOutputStream proto = new ProtoOutputStream();
        long token = proto.start(1146756268034L);
        proto.write(1120986464257L, length);
        proto.write(1120986464258L, flags);
        if (result == null) {
            proto.write(1138166333443L, "null result");
        }
        proto.end(token);
        return proto;
    }

    public static ProtoOutputStream buildGetTextBeforeCursorProto(int length, int flags, CharSequence result) {
        ProtoOutputStream proto = new ProtoOutputStream();
        long token = proto.start(1146756268033L);
        proto.write(1120986464257L, length);
        proto.write(1120986464258L, flags);
        if (result == null) {
            proto.write(1138166333443L, "null result");
        }
        proto.end(token);
        return proto;
    }

    public static ProtoOutputStream buildGetSelectedTextProto(int flags, CharSequence result) {
        ProtoOutputStream proto = new ProtoOutputStream();
        long token = proto.start(1146756268035L);
        proto.write(1120986464257L, flags);
        if (result == null) {
            proto.write(1138166333442L, "null result");
        }
        proto.end(token);
        return proto;
    }

    public static ProtoOutputStream buildGetSurroundingTextProto(int beforeLength, int afterLength, int flags, SurroundingText result) {
        ProtoOutputStream proto = new ProtoOutputStream();
        long token = proto.start(1146756268036L);
        proto.write(1120986464257L, beforeLength);
        proto.write(1120986464258L, afterLength);
        proto.write(1120986464259L, flags);
        if (result == null) {
            long token_result = proto.start(1146756268036L);
            proto.write(1138166333441L, "null result");
            proto.end(token_result);
        }
        proto.end(token);
        return proto;
    }

    public static ProtoOutputStream buildGetCursorCapsModeProto(int reqModes, int result) {
        ProtoOutputStream proto = new ProtoOutputStream();
        long token = proto.start(1146756268037L);
        proto.write(1120986464257L, reqModes);
        proto.end(token);
        return proto;
    }

    public static ProtoOutputStream buildGetExtractedTextProto(ExtractedTextRequest request, int flags, ExtractedText result) {
        ProtoOutputStream proto = new ProtoOutputStream();
        long token = proto.start(1146756268038L);
        long token_request = proto.start(1146756268033L);
        proto.write(1120986464257L, request.token);
        proto.write(1120986464258L, request.flags);
        proto.write(1120986464259L, request.hintMaxLines);
        proto.write(1120986464260L, request.hintMaxChars);
        proto.end(token_request);
        proto.write(1120986464258L, flags);
        if (result == null) {
            proto.write(1138166333443L, "null result");
        }
        proto.end(token);
        return proto;
    }
}
