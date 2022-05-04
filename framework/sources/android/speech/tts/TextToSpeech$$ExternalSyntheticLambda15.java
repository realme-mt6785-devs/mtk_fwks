package android.speech.tts;

import android.speech.tts.TextToSpeech;
/* loaded from: classes3.dex */
public final /* synthetic */ class TextToSpeech$$ExternalSyntheticLambda15 implements TextToSpeech.Action {
    public static final /* synthetic */ TextToSpeech$$ExternalSyntheticLambda15 INSTANCE = new TextToSpeech$$ExternalSyntheticLambda15();

    private /* synthetic */ TextToSpeech$$ExternalSyntheticLambda15() {
    }

    @Override // android.speech.tts.TextToSpeech.Action
    public final Object run(ITextToSpeechService iTextToSpeechService) {
        return iTextToSpeechService.getVoices();
    }
}
