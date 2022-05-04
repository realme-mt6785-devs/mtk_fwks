package com.android.internal.org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes4.dex */
public class DERSet extends ASN1Set {
    private int bodyLength = -1;

    public static DERSet convert(ASN1Set set) {
        return (DERSet) set.toDERObject();
    }

    public DERSet() {
    }

    public DERSet(ASN1Encodable element) {
        super(element);
    }

    public DERSet(ASN1EncodableVector elementVector) {
        super(elementVector, true);
    }

    public DERSet(ASN1Encodable[] elements) {
        super(elements, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERSet(boolean isSorted, ASN1Encodable[] elements) {
        super(checkSorted(isSorted), elements);
    }

    private int getBodyLength() throws IOException {
        if (this.bodyLength < 0) {
            int count = this.elements.length;
            int totalLength = 0;
            for (int i = 0; i < count; i++) {
                ASN1Primitive derObject = this.elements[i].toASN1Primitive().toDERObject();
                totalLength += derObject.encodedLength();
            }
            this.bodyLength = totalLength;
        }
        int count2 = this.bodyLength;
        return count2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() throws IOException {
        int length = getBodyLength();
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.org.bouncycastle.asn1.ASN1Set, com.android.internal.org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream out, boolean withTag) throws IOException {
        if (withTag) {
            out.write(49);
        }
        DEROutputStream derOut = out.getDERSubStream();
        int count = this.elements.length;
        if (this.bodyLength >= 0 || count > 16) {
            int totalLength = getBodyLength();
            out.writeLength(totalLength);
            for (int i = 0; i < count; i++) {
                this.elements[i].toASN1Primitive().toDERObject().encode(derOut, true);
            }
            return;
        }
        int totalLength2 = 0;
        ASN1Primitive[] derObjects = new ASN1Primitive[count];
        for (int i2 = 0; i2 < count; i2++) {
            ASN1Primitive derObject = this.elements[i2].toASN1Primitive().toDERObject();
            derObjects[i2] = derObject;
            totalLength2 += derObject.encodedLength();
        }
        this.bodyLength = totalLength2;
        out.writeLength(totalLength2);
        for (int i3 = 0; i3 < count; i3++) {
            derObjects[i3].encode(derOut, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.org.bouncycastle.asn1.ASN1Set, com.android.internal.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return this.isSorted ? this : super.toDERObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.org.bouncycastle.asn1.ASN1Set, com.android.internal.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return this;
    }

    private static boolean checkSorted(boolean isSorted) {
        if (isSorted) {
            return isSorted;
        }
        throw new IllegalStateException("DERSet elements should always be in sorted order");
    }
}
