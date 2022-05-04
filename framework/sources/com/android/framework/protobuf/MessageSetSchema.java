package com.android.framework.protobuf;

import com.android.framework.protobuf.FieldSet;
import com.android.framework.protobuf.LazyField;
import com.android.framework.protobuf.WireFormat;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes3.dex */
final class MessageSetSchema<T> implements Schema<T> {
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite defaultInstance) {
        this.unknownFieldSchema = unknownFieldSchema;
        this.hasExtensions = extensionSchema.hasExtensions(defaultInstance);
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> MessageSetSchema<T> newSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite defaultInstance) {
        return new MessageSetSchema<>(unknownFieldSchema, extensionSchema, defaultInstance);
    }

    @Override // com.android.framework.protobuf.Schema
    public T newInstance() {
        return (T) this.defaultInstance.newBuilderForType().buildPartial();
    }

    @Override // com.android.framework.protobuf.Schema
    public boolean equals(T message, T other) {
        Object messageUnknown = this.unknownFieldSchema.getFromMessage(message);
        Object otherUnknown = this.unknownFieldSchema.getFromMessage(other);
        if (!messageUnknown.equals(otherUnknown)) {
            return false;
        }
        if (!this.hasExtensions) {
            return true;
        }
        FieldSet<?> messageExtensions = this.extensionSchema.getExtensions(message);
        FieldSet<?> otherExtensions = this.extensionSchema.getExtensions(other);
        return messageExtensions.equals(otherExtensions);
    }

    @Override // com.android.framework.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (!this.hasExtensions) {
            return hashCode;
        }
        FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
        return (hashCode * 53) + extensions.hashCode();
    }

    @Override // com.android.framework.protobuf.Schema
    public void mergeFrom(T message, T other) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    @Override // com.android.framework.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
        Iterator<?> iterator = extensions.iterator();
        while (iterator.hasNext()) {
            Map.Entry<?, Object> extension = iterator.next();
            FieldSet.FieldDescriptorLite<?> fd = (FieldSet.FieldDescriptorLite) extension.getKey();
            if (fd.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fd.isRepeated() || fd.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (extension instanceof LazyField.LazyEntry) {
                writer.writeMessageSetItem(fd.getNumber(), ((LazyField.LazyEntry) extension).getField().toByteString());
            } else {
                writer.writeMessageSetItem(fd.getNumber(), extension.getValue());
            }
        }
        writeUnknownFieldsHelper(this.unknownFieldSchema, message, writer);
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, T message, Writer writer) throws IOException {
        unknownFieldSchema.writeAsMessageSetTo(unknownFieldSchema.getFromMessage(message), writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ee A[SYNTHETIC] */
    @Override // com.android.framework.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void mergeFrom(T r18, byte[] r19, int r20, int r21, com.android.framework.protobuf.ArrayDecoders.Registers r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.framework.protobuf.MessageSetSchema.mergeFrom(java.lang.Object, byte[], int, int, com.android.framework.protobuf.ArrayDecoders$Registers):void");
    }

    @Override // com.android.framework.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        UB unknownFields = unknownFieldSchema.getBuilderFromMessage(message);
        FieldSet<ET> extensions = extensionSchema.getMutableExtensions(message);
        do {
            try {
                int number = reader.getFieldNumber();
                if (number == Integer.MAX_VALUE) {
                    return;
                }
            } finally {
                unknownFieldSchema.setBuilderToMessage(message, unknownFields);
            }
        } while (parseMessageSetItemOrUnknownField(reader, extensionRegistry, extensionSchema, extensions, unknownFieldSchema, unknownFields));
    }

    @Override // com.android.framework.protobuf.Schema
    public void makeImmutable(T message) {
        this.unknownFieldSchema.makeImmutable(message);
        this.extensionSchema.makeImmutable(message);
    }

    /* JADX WARN: Incorrect condition in loop: B:16:0x0034 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private <UT, UB, ET extends com.android.framework.protobuf.FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(com.android.framework.protobuf.Reader r9, com.android.framework.protobuf.ExtensionRegistryLite r10, com.android.framework.protobuf.ExtensionSchema<ET> r11, com.android.framework.protobuf.FieldSet<ET> r12, com.android.framework.protobuf.UnknownFieldSchema<UT, UB> r13, UB r14) throws java.io.IOException {
        /*
            r8 = this;
            int r0 = r9.getTag()
            int r1 = com.android.framework.protobuf.WireFormat.MESSAGE_SET_ITEM_TAG
            r2 = 1
            if (r0 == r1) goto L_0x002a
            int r1 = com.android.framework.protobuf.WireFormat.getTagWireType(r0)
            r3 = 2
            if (r1 != r3) goto L_0x0025
            com.android.framework.protobuf.MessageLite r1 = r8.defaultInstance
            int r3 = com.android.framework.protobuf.WireFormat.getTagFieldNumber(r0)
            java.lang.Object r1 = r11.findExtensionByNumber(r10, r1, r3)
            if (r1 == 0) goto L_0x0020
            r11.parseLengthPrefixedMessageSetItem(r9, r1, r10, r12)
            return r2
        L_0x0020:
            boolean r2 = r13.mergeOneFieldFrom(r14, r9)
            return r2
        L_0x0025:
            boolean r1 = r9.skipField()
            return r1
        L_0x002a:
            r1 = 0
            r3 = 0
            r4 = 0
        L_0x002d:
            int r5 = r9.getFieldNumber()
            r6 = 2147483647(0x7fffffff, float:NaN)
            if (r5 != r6) goto L_0x0037
            goto L_0x0060
        L_0x0037:
            int r6 = r9.getTag()
            int r7 = com.android.framework.protobuf.WireFormat.MESSAGE_SET_TYPE_ID_TAG
            if (r6 != r7) goto L_0x004a
            int r1 = r9.readUInt32()
            com.android.framework.protobuf.MessageLite r7 = r8.defaultInstance
            java.lang.Object r4 = r11.findExtensionByNumber(r10, r7, r1)
            goto L_0x002d
        L_0x004a:
            int r7 = com.android.framework.protobuf.WireFormat.MESSAGE_SET_MESSAGE_TAG
            if (r6 != r7) goto L_0x0059
            if (r4 == 0) goto L_0x0054
            r11.parseLengthPrefixedMessageSetItem(r9, r4, r10, r12)
            goto L_0x002d
        L_0x0054:
            com.android.framework.protobuf.ByteString r3 = r9.readBytes()
            goto L_0x002d
        L_0x0059:
            boolean r7 = r9.skipField()
            if (r7 != 0) goto L_0x0079
        L_0x0060:
            int r5 = r9.getTag()
            int r6 = com.android.framework.protobuf.WireFormat.MESSAGE_SET_ITEM_END_TAG
            if (r5 != r6) goto L_0x0074
            if (r3 == 0) goto L_0x0073
            if (r4 == 0) goto L_0x0070
            r11.parseMessageSetItem(r3, r4, r10, r12)
            goto L_0x0073
        L_0x0070:
            r13.addLengthDelimited(r14, r1, r3)
        L_0x0073:
            return r2
        L_0x0074:
            com.android.framework.protobuf.InvalidProtocolBufferException r2 = com.android.framework.protobuf.InvalidProtocolBufferException.invalidEndTag()
            throw r2
        L_0x0079:
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.framework.protobuf.MessageSetSchema.parseMessageSetItemOrUnknownField(com.android.framework.protobuf.Reader, com.android.framework.protobuf.ExtensionRegistryLite, com.android.framework.protobuf.ExtensionSchema, com.android.framework.protobuf.FieldSet, com.android.framework.protobuf.UnknownFieldSchema, java.lang.Object):boolean");
    }

    @Override // com.android.framework.protobuf.Schema
    public final boolean isInitialized(T message) {
        FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
        return extensions.isInitialized();
    }

    @Override // com.android.framework.protobuf.Schema
    public int getSerializedSize(T message) {
        int size = 0 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        if (this.hasExtensions) {
            return size + this.extensionSchema.getExtensions(message).getMessageSetSerializedSize();
        }
        return size;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        UT unknowns = schema.getFromMessage(message);
        return schema.getSerializedSizeAsMessageSet(unknowns);
    }
}
