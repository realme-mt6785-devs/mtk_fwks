package android.util.jar;

import android.util.jar.StrictJarManifest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class StrictJarManifestReader {
    private final byte[] buf;
    private final int endOfMainSection;
    private Attributes.Name name;
    private int pos;
    private String value;
    private final HashMap<String, Attributes.Name> attributeNameCache = new HashMap<>();
    private final ByteArrayOutputStream valueBuffer = new ByteArrayOutputStream(80);
    private int consecutiveLineBreaks = 0;

    public StrictJarManifestReader(byte[] buf, Attributes main) throws IOException {
        this.buf = buf;
        while (readHeader()) {
            main.put(this.name, this.value);
        }
        this.endOfMainSection = this.pos;
    }

    public void readEntries(Map<String, Attributes> entries, Map<String, StrictJarManifest.Chunk> chunks) throws IOException {
        int mark = this.pos;
        while (readHeader()) {
            if (StrictJarManifest.ATTRIBUTE_NAME_NAME.equals(this.name)) {
                String entryNameValue = this.value;
                Attributes entry = entries.get(entryNameValue);
                if (entry == null) {
                    entry = new Attributes(12);
                }
                while (readHeader()) {
                    entry.put(this.name, this.value);
                }
                if (chunks != null) {
                    if (chunks.get(entryNameValue) == null) {
                        chunks.put(entryNameValue, new StrictJarManifest.Chunk(mark, this.pos));
                        mark = this.pos;
                    } else {
                        throw new IOException("A jar verifier does not support more than one entry with the same name");
                    }
                }
                entries.put(entryNameValue, entry);
            } else {
                throw new IOException("Entry is not named");
            }
        }
    }

    public int getEndOfMainSection() {
        return this.endOfMainSection;
    }

    private boolean readHeader() throws IOException {
        if (this.consecutiveLineBreaks > 1) {
            this.consecutiveLineBreaks = 0;
            return false;
        }
        readName();
        this.consecutiveLineBreaks = 0;
        readValue();
        return this.consecutiveLineBreaks > 0;
    }

    private void readName() throws IOException {
        int i;
        byte[] bArr;
        int i2;
        int mark = this.pos;
        do {
            i = this.pos;
            bArr = this.buf;
            if (i < bArr.length) {
                i2 = i + 1;
                this.pos = i2;
            } else {
                return;
            }
        } while (bArr[i] != 58);
        String nameString = new String(bArr, mark, (i2 - mark) - 1, StandardCharsets.US_ASCII);
        byte[] bArr2 = this.buf;
        int i3 = this.pos;
        this.pos = i3 + 1;
        if (bArr2[i3] == 32) {
            try {
                Attributes.Name name = this.attributeNameCache.get(nameString);
                this.name = name;
                if (name == null) {
                    Attributes.Name name2 = new Attributes.Name(nameString);
                    this.name = name2;
                    this.attributeNameCache.put(nameString, name2);
                }
            } catch (IllegalArgumentException e) {
                throw new IOException(e.getMessage());
            }
        } else {
            throw new IOException(String.format("Invalid value for attribute '%s'", nameString));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0051, code lost:
        r8.valueBuffer.write(r4, r1, r2 - r1);
        r8.value = r8.valueBuffer.toString(java.nio.charset.StandardCharsets.UTF_8.name());
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readValue() throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r1 = r8.pos
            int r2 = r8.pos
            java.io.ByteArrayOutputStream r3 = r8.valueBuffer
            r3.reset()
        L_0x000a:
            int r3 = r8.pos
            byte[] r4 = r8.buf
            int r5 = r4.length
            if (r3 >= r5) goto L_0x0051
            int r5 = r3 + 1
            r8.pos = r5
            r3 = r4[r3]
            r6 = 1
            switch(r3) {
                case 0: goto L_0x003e;
                case 10: goto L_0x0034;
                case 13: goto L_0x002d;
                case 32: goto L_0x001c;
                default: goto L_0x001b;
            }
        L_0x001b:
            goto L_0x0046
        L_0x001c:
            int r7 = r8.consecutiveLineBreaks
            if (r7 != r6) goto L_0x0046
            java.io.ByteArrayOutputStream r5 = r8.valueBuffer
            int r6 = r2 - r1
            r5.write(r4, r1, r6)
            int r1 = r8.pos
            r4 = 0
            r8.consecutiveLineBreaks = r4
            goto L_0x000a
        L_0x002d:
            r0 = 1
            int r4 = r8.consecutiveLineBreaks
            int r4 = r4 + r6
            r8.consecutiveLineBreaks = r4
            goto L_0x000a
        L_0x0034:
            if (r0 == 0) goto L_0x0038
            r0 = 0
            goto L_0x000a
        L_0x0038:
            int r4 = r8.consecutiveLineBreaks
            int r4 = r4 + r6
            r8.consecutiveLineBreaks = r4
            goto L_0x000a
        L_0x003e:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r5 = "NUL character in a manifest"
            r4.<init>(r5)
            throw r4
        L_0x0046:
            int r7 = r8.consecutiveLineBreaks
            if (r7 < r6) goto L_0x004e
            int r5 = r5 - r6
            r8.pos = r5
            goto L_0x0051
        L_0x004e:
            int r2 = r8.pos
            goto L_0x000a
        L_0x0051:
            java.io.ByteArrayOutputStream r3 = r8.valueBuffer
            int r5 = r2 - r1
            r3.write(r4, r1, r5)
            java.io.ByteArrayOutputStream r3 = r8.valueBuffer
            java.nio.charset.Charset r4 = java.nio.charset.StandardCharsets.UTF_8
            java.lang.String r4 = r4.name()
            java.lang.String r3 = r3.toString(r4)
            r8.value = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.jar.StrictJarManifestReader.readValue():void");
    }
}
