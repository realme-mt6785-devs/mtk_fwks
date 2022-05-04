package com.mediatek.bt;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public final class BluetoothMcsMediaControlRequest implements Parcelable {
    public static final Parcelable.Creator<BluetoothMcsMediaControlRequest> CREATOR = new Parcelable.Creator<BluetoothMcsMediaControlRequest>() { // from class: com.mediatek.bt.BluetoothMcsMediaControlRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMcsMediaControlRequest createFromParcel(Parcel in) {
            return new BluetoothMcsMediaControlRequest(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMcsMediaControlRequest[] newArray(int size) {
            return new BluetoothMcsMediaControlRequest[size];
        }
    };
    private Integer int_arg;
    private int opcode;

    /* loaded from: classes4.dex */
    public static final class SupportedOpcodes {
        public static final int FAST_FORWARD = 8;
        public static final int FAST_REWIND = 4;
        public static final int FIRST_GROUP = 262144;
        public static final int FIRST_SEGMENT = 256;
        public static final int FIRST_TRACK = 8192;
        public static final int GOTO_GROUP = 1048576;
        public static final int GOTO_SEGMENT = 1024;
        public static final int GOTO_TRACK = 32768;
        public static final int LAST_GROUP = 524288;
        public static final int LAST_SEGMENT = 512;
        public static final int LAST_TRACK = 16384;
        public static final int MOVE_RELATIVE = 32;
        public static final int NEXT_GROUP = 131072;
        public static final int NEXT_SEGMENT = 128;
        public static final int NEXT_TRACK = 4096;
        public static final int NONE = 0;
        public static final int PAUSE = 2;
        public static final int PLAY = 1;
        public static final int PREVIOUS_GROUP = 65536;
        public static final int PREVIOUS_SEGMENT = 64;
        public static final int PREVIOUS_TRACK = 2048;
        public static final int STOP = 16;

        private SupportedOpcodes() {
        }
    }

    /* loaded from: classes4.dex */
    public static final class Opcodes {
        public static final int FAST_FORWARD = 4;
        public static final int FAST_REWIND = 3;
        public static final int FIRST_GROUP = 66;
        public static final int FIRST_SEGMENT = 34;
        public static final int FIRST_TRACK = 50;
        public static final int GOTO_GROUP = 68;
        public static final int GOTO_SEGMENT = 36;
        public static final int GOTO_TRACK = 52;
        public static final int LAST_GROUP = 67;
        public static final int LAST_SEGMENT = 35;
        public static final int LAST_TRACK = 51;
        public static final int MOVE_RELATIVE = 16;
        public static final int NEXT_GROUP = 65;
        public static final int NEXT_SEGMENT = 33;
        public static final int NEXT_TRACK = 49;
        public static final int PAUSE = 2;
        public static final int PLAY = 1;
        public static final int PREVIOUS_GROUP = 64;
        public static final int PREVIOUS_SEGMENT = 32;
        public static final int PREVIOUS_TRACK = 48;
        public static final int STOP = 5;

        private Opcodes() {
        }
    }

    /* loaded from: classes4.dex */
    public static final class Results {
        public static final int COMMAND_CANNOT_BE_COMPLETED = 4;
        public static final int MEDIA_PLAYER_INACTIVE = 3;
        public static final int OPCODE_NOT_SUPPORTED = 2;
        public static final int SUCCESS = 1;

        private Results() {
        }
    }

    public BluetoothMcsMediaControlRequest(int opcode, int arg) {
        this.opcode = opcode;
        this.int_arg = Integer.valueOf(arg);
    }

    BluetoothMcsMediaControlRequest(Parcel in) {
        int readInt = in.readInt();
        this.opcode = readInt;
        if (readInt == 16 || readInt == 36 || readInt == 52 || readInt == 68) {
            this.int_arg = Integer.valueOf(in.readInt());
        } else {
            this.int_arg = null;
        }
    }

    public int getOpcode() {
        return this.opcode;
    }

    public int getIntArg() {
        return this.int_arg.intValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.opcode);
        Integer num = this.int_arg;
        if (num != null) {
            dest.writeInt(num.intValue());
        }
    }
}
