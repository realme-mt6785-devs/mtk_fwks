package android.media;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public final class EncoderProfiles {
    private List<AudioProfile> audioProfiles;
    private int durationSecs;
    private int fileFormat;
    private List<VideoProfile> videoProfiles;

    public int getDefaultDurationSeconds() {
        return this.durationSecs;
    }

    public int getRecommendedFileFormat() {
        return this.fileFormat;
    }

    /* loaded from: classes2.dex */
    public static final class VideoProfile {
        private int bitrate;
        private int codec;
        private int frameRate;
        private int height;
        private int profile;
        private int width;

        public int getCodec() {
            return this.codec;
        }

        public String getMediaType() {
            int i = this.codec;
            if (i == 1) {
                return "video/3gpp";
            }
            if (i == 2) {
                return MediaFormat.MIMETYPE_VIDEO_AVC;
            }
            if (i == 3) {
                return MediaFormat.MIMETYPE_VIDEO_MPEG4;
            }
            if (i == 4) {
                return MediaFormat.MIMETYPE_VIDEO_VP8;
            }
            if (i == 5) {
                return MediaFormat.MIMETYPE_VIDEO_HEVC;
            }
            throw new RuntimeException("Unknown codec");
        }

        public int getBitrate() {
            return this.bitrate;
        }

        public int getFrameRate() {
            return this.frameRate;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getProfile() {
            return this.profile;
        }

        VideoProfile(int codec, int width, int height, int frameRate, int bitrate, int profile) {
            this.codec = codec;
            this.width = width;
            this.height = height;
            this.frameRate = frameRate;
            this.bitrate = bitrate;
            this.profile = profile;
        }
    }

    public List<AudioProfile> getAudioProfiles() {
        return this.audioProfiles;
    }

    public List<VideoProfile> getVideoProfiles() {
        return this.videoProfiles;
    }

    /* loaded from: classes2.dex */
    public static final class AudioProfile {
        private int bitrate;
        private int channels;
        private int codec;
        private int profile;
        private int sampleRate;

        public int getCodec() {
            return this.codec;
        }

        public String getMediaType() {
            int i = this.codec;
            if (i == 1) {
                return "audio/3gpp";
            }
            if (i == 2) {
                return MediaFormat.MIMETYPE_AUDIO_AMR_WB;
            }
            if (i == 3 || i == 4 || i == 5) {
                return MediaFormat.MIMETYPE_AUDIO_AAC;
            }
            if (i == 6) {
                return MediaFormat.MIMETYPE_AUDIO_VORBIS;
            }
            if (i == 7) {
                return MediaFormat.MIMETYPE_AUDIO_OPUS;
            }
            throw new RuntimeException("Unknown codec");
        }

        public int getBitrate() {
            return this.bitrate;
        }

        public int getSampleRate() {
            return this.sampleRate;
        }

        public int getChannels() {
            return this.channels;
        }

        public int getProfile() {
            int i = this.codec;
            if (i == 3) {
                return 1;
            }
            if (i == 4) {
                return 5;
            }
            if (i == 5) {
                return 39;
            }
            return this.profile;
        }

        AudioProfile(int codec, int channels, int sampleRate, int bitrate, int profile) {
            this.codec = codec;
            this.channels = channels;
            this.sampleRate = sampleRate;
            this.bitrate = bitrate;
            this.profile = profile;
        }
    }

    EncoderProfiles(int duration, int fileFormat, VideoProfile[] videoProfiles, AudioProfile[] audioProfiles) {
        this.durationSecs = duration;
        this.fileFormat = fileFormat;
        this.videoProfiles = Collections.unmodifiableList(Arrays.asList(videoProfiles));
        this.audioProfiles = Collections.unmodifiableList(Arrays.asList(audioProfiles));
    }
}
