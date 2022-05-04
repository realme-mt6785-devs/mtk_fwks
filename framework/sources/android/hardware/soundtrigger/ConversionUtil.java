package android.hardware.soundtrigger;

import android.hardware.soundtrigger.SoundTrigger;
import android.media.AudioFormat;
import android.media.audio.common.AudioConfig;
import android.media.soundtrigger_middleware.ConfidenceLevel;
import android.media.soundtrigger_middleware.ModelParameterRange;
import android.media.soundtrigger_middleware.Phrase;
import android.media.soundtrigger_middleware.PhraseRecognitionEvent;
import android.media.soundtrigger_middleware.PhraseRecognitionExtra;
import android.media.soundtrigger_middleware.PhraseSoundModel;
import android.media.soundtrigger_middleware.RecognitionConfig;
import android.media.soundtrigger_middleware.RecognitionEvent;
import android.media.soundtrigger_middleware.SoundModel;
import android.media.soundtrigger_middleware.SoundTriggerModuleDescriptor;
import android.media.soundtrigger_middleware.SoundTriggerModuleProperties;
import android.os.ParcelFileDescriptor;
import android.os.SharedMemory;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
/* loaded from: classes2.dex */
class ConversionUtil {
    ConversionUtil() {
    }

    public static SoundTrigger.ModuleProperties aidl2apiModuleDescriptor(SoundTriggerModuleDescriptor aidlDesc) {
        SoundTriggerModuleProperties properties = aidlDesc.properties;
        return new SoundTrigger.ModuleProperties(aidlDesc.handle, properties.implementor, properties.description, properties.uuid, properties.version, properties.supportedModelArch, properties.maxSoundModels, properties.maxKeyPhrases, properties.maxUsers, aidl2apiRecognitionModes(properties.recognitionModes), properties.captureTransition, properties.maxBufferMs, properties.concurrentCapture, properties.powerConsumptionMw, properties.triggerInEvent, aidl2apiAudioCapabilities(properties.audioCapabilities));
    }

    public static int aidl2apiRecognitionModes(int aidlModes) {
        int result = 0;
        if ((aidlModes & 1) != 0) {
            result = 0 | 1;
        }
        if ((aidlModes & 2) != 0) {
            result |= 2;
        }
        if ((aidlModes & 4) != 0) {
            result |= 4;
        }
        if ((aidlModes & 8) != 0) {
            return result | 8;
        }
        return result;
    }

    public static int api2aidlRecognitionModes(int apiModes) {
        int result = 0;
        if ((apiModes & 1) != 0) {
            result = 0 | 1;
        }
        if ((apiModes & 2) != 0) {
            result |= 2;
        }
        if ((apiModes & 4) != 0) {
            result |= 4;
        }
        if ((apiModes & 8) != 0) {
            return result | 8;
        }
        return result;
    }

    public static SoundModel api2aidlGenericSoundModel(SoundTrigger.GenericSoundModel apiModel) {
        return api2aidlSoundModel(apiModel);
    }

    public static SoundModel api2aidlSoundModel(SoundTrigger.SoundModel apiModel) {
        SoundModel aidlModel = new SoundModel();
        aidlModel.type = apiModel.getType();
        aidlModel.uuid = api2aidlUuid(apiModel.getUuid());
        aidlModel.vendorUuid = api2aidlUuid(apiModel.getVendorUuid());
        byte[] data = apiModel.getData();
        aidlModel.data = byteArrayToSharedMemory(data, "SoundTrigger SoundModel");
        aidlModel.dataSize = data.length;
        return aidlModel;
    }

    public static String api2aidlUuid(UUID apiUuid) {
        return apiUuid.toString();
    }

    public static PhraseSoundModel api2aidlPhraseSoundModel(SoundTrigger.KeyphraseSoundModel apiModel) {
        PhraseSoundModel aidlModel = new PhraseSoundModel();
        aidlModel.common = api2aidlSoundModel(apiModel);
        aidlModel.phrases = new Phrase[apiModel.getKeyphrases().length];
        for (int i = 0; i < apiModel.getKeyphrases().length; i++) {
            aidlModel.phrases[i] = api2aidlPhrase(apiModel.getKeyphrases()[i]);
        }
        return aidlModel;
    }

    public static Phrase api2aidlPhrase(SoundTrigger.Keyphrase apiPhrase) {
        Phrase aidlPhrase = new Phrase();
        aidlPhrase.id = apiPhrase.getId();
        aidlPhrase.recognitionModes = api2aidlRecognitionModes(apiPhrase.getRecognitionModes());
        aidlPhrase.users = Arrays.copyOf(apiPhrase.getUsers(), apiPhrase.getUsers().length);
        aidlPhrase.locale = apiPhrase.getLocale().toLanguageTag();
        aidlPhrase.text = apiPhrase.getText();
        return aidlPhrase;
    }

    public static RecognitionConfig api2aidlRecognitionConfig(SoundTrigger.RecognitionConfig apiConfig) {
        RecognitionConfig aidlConfig = new RecognitionConfig();
        aidlConfig.captureRequested = apiConfig.captureRequested;
        aidlConfig.phraseRecognitionExtras = new PhraseRecognitionExtra[apiConfig.keyphrases.length];
        for (int i = 0; i < apiConfig.keyphrases.length; i++) {
            aidlConfig.phraseRecognitionExtras[i] = api2aidlPhraseRecognitionExtra(apiConfig.keyphrases[i]);
        }
        aidlConfig.data = Arrays.copyOf(apiConfig.data, apiConfig.data.length);
        aidlConfig.audioCapabilities = api2aidlAudioCapabilities(apiConfig.audioCapabilities);
        return aidlConfig;
    }

    public static PhraseRecognitionExtra api2aidlPhraseRecognitionExtra(SoundTrigger.KeyphraseRecognitionExtra apiExtra) {
        PhraseRecognitionExtra aidlExtra = new PhraseRecognitionExtra();
        aidlExtra.id = apiExtra.id;
        aidlExtra.recognitionModes = api2aidlRecognitionModes(apiExtra.recognitionModes);
        aidlExtra.confidenceLevel = apiExtra.coarseConfidenceLevel;
        aidlExtra.levels = new ConfidenceLevel[apiExtra.confidenceLevels.length];
        for (int i = 0; i < apiExtra.confidenceLevels.length; i++) {
            aidlExtra.levels[i] = api2aidlConfidenceLevel(apiExtra.confidenceLevels[i]);
        }
        return aidlExtra;
    }

    public static SoundTrigger.KeyphraseRecognitionExtra aidl2apiPhraseRecognitionExtra(PhraseRecognitionExtra aidlExtra) {
        SoundTrigger.ConfidenceLevel[] apiLevels = new SoundTrigger.ConfidenceLevel[aidlExtra.levels.length];
        for (int i = 0; i < aidlExtra.levels.length; i++) {
            apiLevels[i] = aidl2apiConfidenceLevel(aidlExtra.levels[i]);
        }
        return new SoundTrigger.KeyphraseRecognitionExtra(aidlExtra.id, aidl2apiRecognitionModes(aidlExtra.recognitionModes), aidlExtra.confidenceLevel, apiLevels);
    }

    public static ConfidenceLevel api2aidlConfidenceLevel(SoundTrigger.ConfidenceLevel apiLevel) {
        ConfidenceLevel aidlLevel = new ConfidenceLevel();
        aidlLevel.levelPercent = apiLevel.confidenceLevel;
        aidlLevel.userId = apiLevel.userId;
        return aidlLevel;
    }

    public static SoundTrigger.ConfidenceLevel aidl2apiConfidenceLevel(ConfidenceLevel apiLevel) {
        return new SoundTrigger.ConfidenceLevel(apiLevel.userId, apiLevel.levelPercent);
    }

    public static SoundTrigger.RecognitionEvent aidl2apiRecognitionEvent(int modelHandle, RecognitionEvent aidlEvent) {
        AudioFormat audioFormat = aidl2apiAudioFormatWithDefault(aidlEvent.audioConfig);
        return new SoundTrigger.GenericRecognitionEvent(aidlEvent.status, modelHandle, aidlEvent.captureAvailable, aidlEvent.captureSession, aidlEvent.captureDelayMs, aidlEvent.capturePreambleMs, aidlEvent.triggerInData, audioFormat, aidlEvent.data);
    }

    public static SoundTrigger.RecognitionEvent aidl2apiPhraseRecognitionEvent(int modelHandle, PhraseRecognitionEvent aidlEvent) {
        SoundTrigger.KeyphraseRecognitionExtra[] apiExtras = new SoundTrigger.KeyphraseRecognitionExtra[aidlEvent.phraseExtras.length];
        for (int i = 0; i < aidlEvent.phraseExtras.length; i++) {
            apiExtras[i] = aidl2apiPhraseRecognitionExtra(aidlEvent.phraseExtras[i]);
        }
        AudioFormat audioFormat = aidl2apiAudioFormatWithDefault(aidlEvent.common.audioConfig);
        return new SoundTrigger.KeyphraseRecognitionEvent(aidlEvent.common.status, modelHandle, aidlEvent.common.captureAvailable, aidlEvent.common.captureSession, aidlEvent.common.captureDelayMs, aidlEvent.common.capturePreambleMs, aidlEvent.common.triggerInData, audioFormat, aidlEvent.common.data, apiExtras);
    }

    public static AudioFormat aidl2apiAudioFormat(AudioConfig audioConfig) {
        AudioFormat.Builder apiBuilder = new AudioFormat.Builder();
        apiBuilder.setSampleRate(audioConfig.sampleRateHz);
        apiBuilder.setChannelMask(aidl2apiChannelInMask(audioConfig.channelMask));
        apiBuilder.setEncoding(aidl2apiEncoding(audioConfig.format));
        return apiBuilder.build();
    }

    public static AudioFormat aidl2apiAudioFormatWithDefault(AudioConfig audioConfig) {
        if (audioConfig != null) {
            return aidl2apiAudioFormat(audioConfig);
        }
        return new AudioFormat.Builder().build();
    }

    public static int aidl2apiEncoding(int aidlFormat) {
        switch (aidlFormat) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
            case 4:
            case 5:
            case 6:
                return 4;
            case 16777216:
                return 9;
            case 67108866:
                return 10;
            case 67108880:
                return 11;
            case 67109120:
                return 12;
            case 67109376:
                return 15;
            case 67109632:
                return 16;
            case 150994944:
                return 5;
            case android.media.audio.common.AudioFormat.E_AC3 /* 167772160 */:
                return 6;
            case 167772161:
                return 18;
            case android.media.audio.common.AudioFormat.DTS /* 184549376 */:
                return 7;
            case android.media.audio.common.AudioFormat.DTS_HD /* 201326592 */:
                return 8;
            case android.media.audio.common.AudioFormat.IEC61937 /* 218103808 */:
                return 13;
            case android.media.audio.common.AudioFormat.DOLBY_TRUEHD /* 234881024 */:
                return 14;
            case android.media.audio.common.AudioFormat.AC4 /* 570425344 */:
                return 17;
            case android.media.audio.common.AudioFormat.MAT /* 603979776 */:
            case 603979777:
            case 603979778:
            case 603979779:
                return 19;
            default:
                return 0;
        }
    }

    public static int api2aidlModelParameter(int apiParam) {
        switch (apiParam) {
            case 0:
                return 0;
            default:
                return -1;
        }
    }

    public static int aidl2apiChannelInMask(int aidlMask) {
        return aidlMask;
    }

    public static SoundTrigger.ModelParamRange aidl2apiModelParameterRange(ModelParameterRange aidlRange) {
        if (aidlRange == null) {
            return null;
        }
        return new SoundTrigger.ModelParamRange(aidlRange.minInclusive, aidlRange.maxInclusive);
    }

    public static int aidl2apiAudioCapabilities(int aidlCapabilities) {
        int result = 0;
        if ((aidlCapabilities & 1) != 0) {
            result = 0 | 1;
        }
        if ((aidlCapabilities & 2) != 0) {
            return result | 2;
        }
        return result;
    }

    public static int api2aidlAudioCapabilities(int apiCapabilities) {
        int result = 0;
        if ((apiCapabilities & 1) != 0) {
            result = 0 | 1;
        }
        if ((apiCapabilities & 2) != 0) {
            return result | 2;
        }
        return result;
    }

    private static ParcelFileDescriptor byteArrayToSharedMemory(byte[] data, String name) {
        if (data.length == 0) {
            return null;
        }
        try {
            SharedMemory shmem = SharedMemory.create(name != null ? name : "", data.length);
            ByteBuffer buffer = shmem.mapReadWrite();
            buffer.put(data);
            SharedMemory.unmap(buffer);
            ParcelFileDescriptor fd = shmem.getFdDup();
            shmem.close();
            return fd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
