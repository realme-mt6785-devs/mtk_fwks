package com.android.server.wm.nano;

import com.android.framework.protobuf.nano.CodedInputByteBufferNano;
import com.android.framework.protobuf.nano.CodedOutputByteBufferNano;
import com.android.framework.protobuf.nano.InternalNano;
import com.android.framework.protobuf.nano.InvalidProtocolBufferNanoException;
import com.android.framework.protobuf.nano.MessageNano;
import com.android.framework.protobuf.nano.WireFormatNano;
import java.io.IOException;
/* loaded from: classes4.dex */
public interface WindowManagerProtos {

    /* loaded from: classes4.dex */
    public static final class TaskSnapshotProto extends MessageNano {
        private static volatile TaskSnapshotProto[] _emptyArray;
        public int appearance;
        public long id;
        public int insetBottom;
        public int insetLeft;
        public int insetRight;
        public int insetTop;
        public boolean isRealSnapshot;
        public boolean isTranslucent;
        public float legacyScale;
        public int orientation;
        public int rotation;
        public int systemUiVisibility;
        public int taskHeight;
        public int taskWidth;
        public String topActivityComponent;
        public int windowingMode;

        public static TaskSnapshotProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TaskSnapshotProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TaskSnapshotProto() {
            clear();
        }

        public TaskSnapshotProto clear() {
            this.orientation = 0;
            this.insetLeft = 0;
            this.insetTop = 0;
            this.insetRight = 0;
            this.insetBottom = 0;
            this.isRealSnapshot = false;
            this.windowingMode = 0;
            this.systemUiVisibility = 0;
            this.isTranslucent = false;
            this.topActivityComponent = "";
            this.legacyScale = 0.0f;
            this.id = 0L;
            this.rotation = 0;
            this.taskWidth = 0;
            this.taskHeight = 0;
            this.appearance = 0;
            this.cachedSize = -1;
            return this;
        }

        @Override // com.android.framework.protobuf.nano.MessageNano
        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int i = this.orientation;
            if (i != 0) {
                output.writeInt32(1, i);
            }
            int i2 = this.insetLeft;
            if (i2 != 0) {
                output.writeInt32(2, i2);
            }
            int i3 = this.insetTop;
            if (i3 != 0) {
                output.writeInt32(3, i3);
            }
            int i4 = this.insetRight;
            if (i4 != 0) {
                output.writeInt32(4, i4);
            }
            int i5 = this.insetBottom;
            if (i5 != 0) {
                output.writeInt32(5, i5);
            }
            boolean z = this.isRealSnapshot;
            if (z) {
                output.writeBool(6, z);
            }
            int i6 = this.windowingMode;
            if (i6 != 0) {
                output.writeInt32(7, i6);
            }
            int i7 = this.systemUiVisibility;
            if (i7 != 0) {
                output.writeInt32(8, i7);
            }
            boolean z2 = this.isTranslucent;
            if (z2) {
                output.writeBool(9, z2);
            }
            if (!this.topActivityComponent.equals("")) {
                output.writeString(10, this.topActivityComponent);
            }
            if (Float.floatToIntBits(this.legacyScale) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(11, this.legacyScale);
            }
            long j = this.id;
            if (j != 0) {
                output.writeInt64(12, j);
            }
            int i8 = this.rotation;
            if (i8 != 0) {
                output.writeInt32(13, i8);
            }
            int i9 = this.taskWidth;
            if (i9 != 0) {
                output.writeInt32(14, i9);
            }
            int i10 = this.taskHeight;
            if (i10 != 0) {
                output.writeInt32(15, i10);
            }
            int i11 = this.appearance;
            if (i11 != 0) {
                output.writeInt32(16, i11);
            }
            super.writeTo(output);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.android.framework.protobuf.nano.MessageNano
        public int computeSerializedSize() {
            int size = super.computeSerializedSize();
            int i = this.orientation;
            if (i != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, i);
            }
            int i2 = this.insetLeft;
            if (i2 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, i2);
            }
            int i3 = this.insetTop;
            if (i3 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, i3);
            }
            int i4 = this.insetRight;
            if (i4 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, i4);
            }
            int i5 = this.insetBottom;
            if (i5 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, i5);
            }
            boolean z = this.isRealSnapshot;
            if (z) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, z);
            }
            int i6 = this.windowingMode;
            if (i6 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, i6);
            }
            int i7 = this.systemUiVisibility;
            if (i7 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, i7);
            }
            boolean z2 = this.isTranslucent;
            if (z2) {
                size += CodedOutputByteBufferNano.computeBoolSize(9, z2);
            }
            if (!this.topActivityComponent.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.topActivityComponent);
            }
            if (Float.floatToIntBits(this.legacyScale) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(11, this.legacyScale);
            }
            long j = this.id;
            if (j != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(12, j);
            }
            int i8 = this.rotation;
            if (i8 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(13, i8);
            }
            int i9 = this.taskWidth;
            if (i9 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(14, i9);
            }
            int i10 = this.taskHeight;
            if (i10 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(15, i10);
            }
            int i11 = this.appearance;
            if (i11 != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(16, i11);
            }
            return size;
        }

        @Override // com.android.framework.protobuf.nano.MessageNano
        public TaskSnapshotProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        return this;
                    case 8:
                        this.orientation = input.readInt32();
                        break;
                    case 16:
                        this.insetLeft = input.readInt32();
                        break;
                    case 24:
                        this.insetTop = input.readInt32();
                        break;
                    case 32:
                        this.insetRight = input.readInt32();
                        break;
                    case 40:
                        this.insetBottom = input.readInt32();
                        break;
                    case 48:
                        this.isRealSnapshot = input.readBool();
                        break;
                    case 56:
                        this.windowingMode = input.readInt32();
                        break;
                    case 64:
                        this.systemUiVisibility = input.readInt32();
                        break;
                    case 72:
                        this.isTranslucent = input.readBool();
                        break;
                    case 82:
                        this.topActivityComponent = input.readString();
                        break;
                    case 93:
                        this.legacyScale = input.readFloat();
                        break;
                    case 96:
                        this.id = input.readInt64();
                        break;
                    case 104:
                        this.rotation = input.readInt32();
                        break;
                    case 112:
                        this.taskWidth = input.readInt32();
                        break;
                    case 120:
                        this.taskHeight = input.readInt32();
                        break;
                    case 128:
                        this.appearance = input.readInt32();
                        break;
                    default:
                        if (WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public static TaskSnapshotProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TaskSnapshotProto) MessageNano.mergeFrom(new TaskSnapshotProto(), data);
        }

        public static TaskSnapshotProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TaskSnapshotProto().mergeFrom(input);
        }
    }
}
