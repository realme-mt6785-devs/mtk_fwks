package com.mediatek.bt;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes4.dex */
public final class BluetoothMcsSearchRequest implements Parcelable {
    public static final Parcelable.Creator<BluetoothMcsSearchRequest> CREATOR = new Parcelable.Creator<BluetoothMcsSearchRequest>() { // from class: com.mediatek.bt.BluetoothMcsSearchRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMcsSearchRequest createFromParcel(Parcel in) {
            return new BluetoothMcsSearchRequest(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothMcsSearchRequest[] newArray(int size) {
            return new BluetoothMcsSearchRequest[size];
        }
    };
    private int opcode;
    private String string_arg;

    /* loaded from: classes4.dex */
    public static final class Opcodes {
        public static final int ALBUM_NAME = 3;
        public static final int ARTIST_NAME = 2;
        public static final int EARLIEST_YEAR = 5;
        public static final int GENRE = 7;
        public static final int GROUP_NAME = 4;
        public static final int LATEST_YEAR = 6;
        public static final int ONLY_GROUPS = 9;
        public static final int ONLY_TRACKS = 8;
        public static final int TRACK_NAME = 1;

        private Opcodes() {
        }
    }

    /* loaded from: classes4.dex */
    public static final class Results {
        public static final int FAILURE = 2;
        public static final int SUCCESS = 1;

        private Results() {
        }
    }

    BluetoothMcsSearchRequest(Parcel in) {
        int readInt = in.readInt();
        this.opcode = readInt;
        if (readInt == 0) {
            return;
        }
        if (readInt <= 7) {
            this.string_arg = in.readString();
        } else {
            this.string_arg = null;
        }
    }

    public int getOpcode() {
        return this.opcode;
    }

    public String getStringArg() {
        return this.string_arg;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.opcode);
        if (this.opcode <= 7) {
            dest.writeString(this.string_arg);
        }
    }
}
