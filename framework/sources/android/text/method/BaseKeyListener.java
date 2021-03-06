package android.text.method;

import android.graphics.Paint;
import android.icu.lang.UCharacter;
import android.text.Editable;
import android.text.Emoji;
import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spanned;
import android.text.method.TextKeyListener;
import android.text.style.ReplacementSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
/* loaded from: classes3.dex */
public abstract class BaseKeyListener extends MetaKeyKeyListener implements KeyListener {
    private static final int CARRIAGE_RETURN = 13;
    private static final int LINE_FEED = 10;
    static final Object OLD_SEL_START = new NoCopySpan.Concrete();
    static Paint sCachedPaint = null;
    private final Object mLock = new Object();

    public boolean backspace(View view, Editable content, int keyCode, KeyEvent event) {
        return backspaceOrForwardDelete(view, content, keyCode, event, false);
    }

    public boolean forwardDelete(View view, Editable content, int keyCode, KeyEvent event) {
        return backspaceOrForwardDelete(view, content, keyCode, event, true);
    }

    private static boolean isVariationSelector(int codepoint) {
        return UCharacter.hasBinaryProperty(codepoint, 36);
    }

    private static int adjustReplacementSpan(CharSequence text, int offset, boolean moveToStart) {
        if (!(text instanceof Spanned)) {
            return offset;
        }
        ReplacementSpan[] spans = (ReplacementSpan[]) ((Spanned) text).getSpans(offset, offset, ReplacementSpan.class);
        for (int i = 0; i < spans.length; i++) {
            int start = ((Spanned) text).getSpanStart(spans[i]);
            int end = ((Spanned) text).getSpanEnd(spans[i]);
            if (start < offset && end > offset) {
                offset = moveToStart ? start : end;
            }
        }
        return offset;
    }

    private static int getOffsetForBackspaceKey(CharSequence text, int offset) {
        if (offset <= 1) {
            return 0;
        }
        int STATE_LF = 1;
        int STATE_BEFORE_KEYCAP = 2;
        int deleteCharCount = 0;
        int lastSeenVSCharCount = 0;
        int state = 0;
        int tmpOffset = offset;
        while (true) {
            int codePoint = Character.codePointBefore(text, tmpOffset);
            tmpOffset -= Character.charCount(codePoint);
            switch (state) {
                case 0:
                    int deleteCharCount2 = Character.charCount(codePoint);
                    deleteCharCount = deleteCharCount2;
                    if (codePoint != 10) {
                        if (!isVariationSelector(codePoint)) {
                            if (!Emoji.isRegionalIndicatorSymbol(codePoint)) {
                                if (!Emoji.isEmojiModifier(codePoint)) {
                                    if (codePoint != Emoji.COMBINING_ENCLOSING_KEYCAP) {
                                        if (!Emoji.isEmoji(codePoint)) {
                                            if (codePoint != Emoji.CANCEL_TAG) {
                                                state = 13;
                                                break;
                                            } else {
                                                state = 12;
                                                break;
                                            }
                                        } else {
                                            state = 7;
                                            break;
                                        }
                                    } else {
                                        state = 2;
                                        break;
                                    }
                                } else {
                                    state = 4;
                                    break;
                                }
                            } else {
                                state = 10;
                                break;
                            }
                        } else {
                            state = 6;
                            break;
                        }
                    } else {
                        state = 1;
                        break;
                    }
                case 1:
                    if (codePoint == 13) {
                        deleteCharCount++;
                    }
                    state = 13;
                    break;
                case 2:
                    if (!isVariationSelector(codePoint)) {
                        if (Emoji.isKeycapBase(codePoint)) {
                            deleteCharCount += Character.charCount(codePoint);
                        }
                        state = 13;
                        break;
                    } else {
                        int lastSeenVSCharCount2 = Character.charCount(codePoint);
                        state = 3;
                        lastSeenVSCharCount = lastSeenVSCharCount2;
                        break;
                    }
                case 3:
                    if (Emoji.isKeycapBase(codePoint)) {
                        deleteCharCount += lastSeenVSCharCount + Character.charCount(codePoint);
                    }
                    state = 13;
                    break;
                case 4:
                    if (!isVariationSelector(codePoint)) {
                        if (Emoji.isEmojiModifierBase(codePoint)) {
                            deleteCharCount += Character.charCount(codePoint);
                        }
                        state = 13;
                        break;
                    } else {
                        int lastSeenVSCharCount3 = Character.charCount(codePoint);
                        state = 5;
                        lastSeenVSCharCount = lastSeenVSCharCount3;
                        break;
                    }
                case 5:
                    if (Emoji.isEmojiModifierBase(codePoint)) {
                        deleteCharCount += lastSeenVSCharCount + Character.charCount(codePoint);
                    }
                    state = 13;
                    break;
                case 6:
                    if (!Emoji.isEmoji(codePoint)) {
                        if (!isVariationSelector(codePoint) && UCharacter.getCombiningClass(codePoint) == 0) {
                            deleteCharCount += Character.charCount(codePoint);
                        }
                        state = 13;
                        break;
                    } else {
                        deleteCharCount += Character.charCount(codePoint);
                        state = 7;
                        break;
                    }
                    break;
                case 7:
                    if (codePoint != Emoji.ZERO_WIDTH_JOINER) {
                        state = 13;
                        break;
                    } else {
                        state = 8;
                        break;
                    }
                case 8:
                    if (!Emoji.isEmoji(codePoint)) {
                        if (!isVariationSelector(codePoint)) {
                            state = 13;
                            break;
                        } else {
                            lastSeenVSCharCount = Character.charCount(codePoint);
                            state = 9;
                            break;
                        }
                    } else {
                        deleteCharCount += Character.charCount(codePoint) + 1;
                        state = Emoji.isEmojiModifier(codePoint) ? 4 : 7;
                        break;
                    }
                case 9:
                    if (!Emoji.isEmoji(codePoint)) {
                        state = 13;
                        break;
                    } else {
                        deleteCharCount += lastSeenVSCharCount + 1 + Character.charCount(codePoint);
                        lastSeenVSCharCount = 0;
                        state = 7;
                        break;
                    }
                case 10:
                    if (!Emoji.isRegionalIndicatorSymbol(codePoint)) {
                        state = 13;
                        break;
                    } else {
                        deleteCharCount += 2;
                        state = 11;
                        break;
                    }
                case 11:
                    if (!Emoji.isRegionalIndicatorSymbol(codePoint)) {
                        state = 13;
                        break;
                    } else {
                        deleteCharCount -= 2;
                        state = 10;
                        break;
                    }
                case 12:
                    if (!Emoji.isTagSpecChar(codePoint)) {
                        if (!Emoji.isEmoji(codePoint)) {
                            deleteCharCount = 2;
                            state = 13;
                            break;
                        } else {
                            deleteCharCount += Character.charCount(codePoint);
                            state = 13;
                            break;
                        }
                    } else {
                        deleteCharCount += 2;
                        break;
                    }
                default:
                    throw new IllegalArgumentException("state " + state + " is unknown");
            }
            if (tmpOffset > 0 && state != 13) {
                STATE_LF = STATE_LF;
                STATE_BEFORE_KEYCAP = STATE_BEFORE_KEYCAP;
            }
        }
        return adjustReplacementSpan(text, offset - deleteCharCount, true);
    }

    private static int getOffsetForForwardDeleteKey(CharSequence text, int offset, Paint paint) {
        int len = text.length();
        if (offset >= len - 1) {
            return len;
        }
        return adjustReplacementSpan(text, paint.getTextRunCursor(text, offset, len, false, offset, 0), false);
    }

    private boolean backspaceOrForwardDelete(View view, Editable content, int keyCode, KeyEvent event, boolean isForwardDelete) {
        int end;
        Paint paint;
        Paint paint2;
        if (!KeyEvent.metaStateHasNoModifiers(event.getMetaState() & (-28916))) {
            return false;
        }
        if (deleteSelection(view, content)) {
            return true;
        }
        boolean isCtrlActive = (event.getMetaState() & 4096) != 0;
        boolean isShiftActive = getMetaState(content, 1, event) == 1;
        boolean isAltActive = getMetaState(content, 2, event) == 1;
        if (isCtrlActive) {
            if (isAltActive || isShiftActive) {
                return false;
            }
            return deleteUntilWordBoundary(view, content, isForwardDelete);
        } else if (isAltActive && deleteLine(view, content)) {
            return true;
        } else {
            int start = Selection.getSelectionEnd(content);
            if (isForwardDelete) {
                if (view instanceof TextView) {
                    paint = ((TextView) view).getPaint();
                } else {
                    synchronized (this.mLock) {
                        if (sCachedPaint == null) {
                            sCachedPaint = new Paint();
                        }
                        paint2 = sCachedPaint;
                    }
                    paint = paint2;
                }
                end = getOffsetForForwardDeleteKey(content, start, paint);
            } else {
                end = getOffsetForBackspaceKey(content, start);
            }
            if (start == end) {
                return false;
            }
            content.delete(Math.min(start, end), Math.max(start, end));
            return true;
        }
    }

    private boolean deleteUntilWordBoundary(View view, Editable content, boolean isForwardDelete) {
        int deleteTo;
        int deleteFrom;
        int currentCursorOffset = Selection.getSelectionStart(content);
        if (currentCursorOffset != Selection.getSelectionEnd(content)) {
            return false;
        }
        if ((!isForwardDelete && currentCursorOffset == 0) || (isForwardDelete && currentCursorOffset == content.length())) {
            return false;
        }
        WordIterator wordIterator = null;
        if (view instanceof TextView) {
            wordIterator = ((TextView) view).getWordIterator();
        }
        if (wordIterator == null) {
            wordIterator = new WordIterator();
        }
        if (isForwardDelete) {
            deleteFrom = currentCursorOffset;
            wordIterator.setCharSequence(content, deleteFrom, content.length());
            deleteTo = wordIterator.following(currentCursorOffset);
            if (deleteTo == -1) {
                deleteTo = content.length();
            }
        } else {
            deleteTo = currentCursorOffset;
            wordIterator.setCharSequence(content, 0, deleteTo);
            deleteFrom = wordIterator.preceding(currentCursorOffset);
            if (deleteFrom == -1) {
                deleteFrom = 0;
            }
        }
        content.delete(deleteFrom, deleteTo);
        return true;
    }

    private boolean deleteSelection(View view, Editable content) {
        int selectionStart = Selection.getSelectionStart(content);
        int selectionEnd = Selection.getSelectionEnd(content);
        if (selectionEnd < selectionStart) {
            selectionEnd = selectionStart;
            selectionStart = selectionEnd;
        }
        if (selectionStart == selectionEnd) {
            return false;
        }
        content.delete(selectionStart, selectionEnd);
        return true;
    }

    private boolean deleteLine(View view, Editable content) {
        Layout layout;
        int line;
        int start;
        int end;
        if (!(view instanceof TextView) || (layout = ((TextView) view).getLayout()) == null || (end = layout.getLineEnd(line)) == (start = layout.getLineStart((line = layout.getLineForOffset(Selection.getSelectionStart(content)))))) {
            return false;
        }
        content.delete(start, end);
        return true;
    }

    /* renamed from: android.text.method.BaseKeyListener$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$text$method$TextKeyListener$Capitalize;

        static {
            int[] iArr = new int[TextKeyListener.Capitalize.values().length];
            $SwitchMap$android$text$method$TextKeyListener$Capitalize = iArr;
            try {
                iArr[TextKeyListener.Capitalize.CHARACTERS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$text$method$TextKeyListener$Capitalize[TextKeyListener.Capitalize.WORDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$text$method$TextKeyListener$Capitalize[TextKeyListener.Capitalize.SENTENCES.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int makeTextContentType(TextKeyListener.Capitalize caps, boolean autoText) {
        int contentType = 1;
        switch (AnonymousClass1.$SwitchMap$android$text$method$TextKeyListener$Capitalize[caps.ordinal()]) {
            case 1:
                contentType = 1 | 4096;
                break;
            case 2:
                contentType = 1 | 8192;
                break;
            case 3:
                contentType = 1 | 16384;
                break;
        }
        if (autoText) {
            return contentType | 32768;
        }
        return contentType;
    }

    @Override // android.text.method.MetaKeyKeyListener, android.text.method.KeyListener
    public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
        boolean handled;
        switch (keyCode) {
            case 67:
                handled = backspace(view, content, keyCode, event);
                break;
            case 112:
                handled = forwardDelete(view, content, keyCode, event);
                break;
            default:
                handled = false;
                break;
        }
        if (!handled) {
            return super.onKeyDown(view, content, keyCode, event);
        }
        adjustMetaAfterKeypress(content);
        return true;
    }

    @Override // android.text.method.KeyListener
    public boolean onKeyOther(View view, Editable content, KeyEvent event) {
        if (event.getAction() != 2 || event.getKeyCode() != 0) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(content);
        int selectionEnd = Selection.getSelectionEnd(content);
        if (selectionEnd < selectionStart) {
            selectionEnd = selectionStart;
            selectionStart = selectionEnd;
        }
        CharSequence text = event.getCharacters();
        if (text == null) {
            return false;
        }
        content.replace(selectionStart, selectionEnd, text);
        return true;
    }
}
