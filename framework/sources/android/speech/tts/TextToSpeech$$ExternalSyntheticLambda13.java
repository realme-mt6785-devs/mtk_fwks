package android.speech.tts;

import android.speech.tts.TextToSpeech;
/* loaded from: classes3.dex */
public final /* synthetic */ class TextToSpeech$$ExternalSyntheticLambda13 implements TextToSpeech.Action {
    public static final /* synthetic */ TextToSpeech$$ExternalSyntheticLambda13 INSTANCE = new TextToSpeech$$ExternalSyntheticLambda13();

    private /* synthetic */ TextToSpeech$$ExternalSyntheticLambda13() {
    }

    @Override // android.speech.tts.TextToSpeech.Action
    public final Object run(ITextToSpeechService iTextToSpeechService) {
        return iTextToSpeechService.getClientDefaultLanguage();
    }
}
