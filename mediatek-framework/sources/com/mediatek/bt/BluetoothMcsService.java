package com.mediatek.bt;
/* loaded from: classes.dex */
public class BluetoothMcsService {
    public static final long TRACK_DURATION_UNAVAILABLE = 42949672950L;
    public static final long TRACK_POSITION_UNAVAILABLE = 42949672950L;

    private BluetoothMcsService() {
    }

    /* loaded from: classes.dex */
    public static final class ServiceFeature {
        public static final long ALL_MANDATORY_SERVICE_FEATURES = 2162809;
        public static final long CONTENT_CONTROL_ID = 2097152;
        public static final long CURRENT_GROUP_OBJ_ID = 4096;
        public static final long CURRENT_GROUP_OBJ_ID_NOTIFY = 17592186044416L;
        public static final long CURRENT_TRACK_OBJ_ID = 1024;
        public static final long CURRENT_TRACK_OBJ_ID_NOTIFY = 4398046511104L;
        public static final long CURRENT_TRACK_SEGMENT_OBJ_ID = 512;
        public static final long MEDIA_CONTROL_POINT = 131072;
        public static final long MEDIA_CONTROL_POINT_OPCODES_SUPPORTED = 262144;
        public static final long MEDIA_CONTROL_POINT_OPCODES_SUPPORTED_NOTIFY = 1125899906842624L;
        public static final long MEDIA_STATE = 65536;
        public static final long NEXT_TRACK_OBJ_ID = 2048;
        public static final long NEXT_TRACK_OBJ_ID_NOTIFY = 8796093022208L;
        public static final long PARENT_GROUP_OBJ_ID = 8192;
        public static final long PARENT_GROUP_OBJ_ID_NOTIFY = 35184372088832L;
        public static final long PLAYBACK_SPEED = 128;
        public static final long PLAYBACK_SPEED_NOTIFY = 549755813888L;
        public static final long PLAYER_ICON_OBJ_ID = 2;
        public static final long PLAYER_ICON_URL = 4;
        public static final long PLAYER_NAME = 1;
        public static final long PLAYER_NAME_NOTIFY = 4294967296L;
        public static final long PLAYING_ORDER = 16384;
        public static final long PLAYING_ORDER_NOTIFY = 70368744177664L;
        public static final long PLAYING_ORDER_SUPPORTED = 32768;
        public static final long SEARCH_CONTROL_POINT = 1048576;
        public static final long SEARCH_RESULT_OBJ_ID = 524288;
        public static final long SEEKING_SPEED = 256;
        public static final long SEEKING_SPEED_NOTIFY = 1099511627776L;
        public static final long TRACK_CHANGED = 8;
        public static final long TRACK_DURATION = 32;
        public static final long TRACK_DURATION_NOTIFY = 137438953472L;
        public static final long TRACK_POSITION = 64;
        public static final long TRACK_POSITION_NOTIFY = 274877906944L;
        public static final long TRACK_TITLE = 16;
        public static final long TRACK_TITLE_NOTIFY = 68719476736L;

        private ServiceFeature() {
        }
    }

    /* loaded from: classes.dex */
    public static final class ServiceStatus {
        public static final int INVALID_FEATURE_FLAGS = 1;
        public static final int OK = 0;
        public static final int SERVICE_DIED = 2;
        public static final int SERVICE_UNAVAILABLE = 3;

        private ServiceStatus() {
        }
    }

    /* loaded from: classes.dex */
    public static final class PlayerStateField {
        public static final int ICON_OBJ_ID = 7;
        public static final int ICON_URL = 6;
        public static final int OPCODES_SUPPORTED = 9;
        public static final int PLAYBACK_SPEED = 1;
        public static final int PLAYBACK_STATE = 0;
        public static final int PLAYER_NAME = 5;
        public static final int PLAYING_ORDER = 3;
        public static final int PLAYING_ORDER_SUPPORTED = 8;
        public static final int SEEKING_SPEED = 2;
        public static final int TRACK_DURATION = 11;
        public static final int TRACK_POSITION = 4;
        public static final int TRACK_TITLE = 10;

        private PlayerStateField() {
        }
    }

    /* loaded from: classes.dex */
    public static final class ObjectIds {
        public static final int CURRENT_GROUP_OBJ_ID = 4096;
        public static final int CURRENT_TRACK_OBJ_ID = 1024;
        public static final int CURRENT_TRACK_SEGMENT_OBJ_ID = 512;
        public static final int NEXT_TRACK_OBJ_ID = 2048;
        public static final int PARENT_GROUP_OBJ_ID = 8192;
        public static final int PLAYER_ICON_OBJ_ID = 2;
        public static final int SEARCH_RESULT_OBJ_ID = 524288;

        private ObjectIds() {
        }
    }

    /* loaded from: classes.dex */
    public static final class PlaybackState {
        public static final int INACTIVE = 0;
        public static final int PAUSED = 2;
        public static final int PLAYING = 1;
        public static final int SEEKING = 3;
        public static final int STATE_MAX = 3;
        public static final int STATE_MIN = 0;

        private PlaybackState() {
        }
    }

    /* loaded from: classes.dex */
    public static final class PlayingOrder {
        public static final int IN_ORDER_ONCE = 3;
        public static final int IN_ORDER_REPEAT = 4;
        public static final int NEWEST_ONCE = 7;
        public static final int NEWEST_REPEAT = 8;
        public static final int OLDEST_ONCE = 5;
        public static final int OLDEST_REPEAT = 6;
        public static final int SHUFFLE_ONCE = 9;
        public static final int SHUFFLE_REPEAT = 10;
        public static final int SINGLE_ONCE = 1;
        public static final int SINGLE_REPEAT = 2;

        private PlayingOrder() {
        }
    }

    /* loaded from: classes.dex */
    public static final class SupportedPlayingOrder {
        public static final int IN_ORDER_ONCE = 4;
        public static final int IN_ORDER_REPEAT = 8;
        public static final int NEWEST_ONCE = 64;
        public static final int NEWEST_REPEAT = 128;
        public static final int OLDEST_ONCE = 16;
        public static final int OLDEST_REPEAT = 32;
        public static final int SHUFFLE_ONCE = 256;
        public static final int SHUFFLE_REPEAT = 512;
        public static final int SINGLE_ONCE = 1;
        public static final int SINGLE_REPEAT = 2;

        private SupportedPlayingOrder() {
        }
    }
}
