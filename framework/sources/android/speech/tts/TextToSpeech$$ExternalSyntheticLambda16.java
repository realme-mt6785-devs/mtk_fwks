package android.speech.tts;

import android.speech.tts.TextToSpeech;
/* loaded from: classes3.dex */
public final /* synthetic */ class TextToSpeech$$ExternalSyntheticLambda16 implements TextToSpeech.Action {
    public static final /* synthetic */ TextToSpeech$$ExternalSyntheticLambda16 INSTANCE = new TextToSpeech$$ExternalSyntheticLambda16();

    private /* synthetic */ TextToSpeech$$ExternalSyntheticLambda16() {
    }

    @Override // android.speech.tts.TextToSpeech.Action
    public final Object run(ITextToSpeechService iTextToSpeechService) {
        Boolean valueOf;
        valueOf = Boolean.valueOf(iTextToSpeechService.isSpeaking());
        return valueOf;
    }
}
