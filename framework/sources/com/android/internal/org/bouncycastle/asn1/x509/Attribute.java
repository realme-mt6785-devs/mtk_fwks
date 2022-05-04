package com.android.internal.org.bouncycastle.asn1.x509;

import com.android.internal.org.bouncycastle.asn1.ASN1Encodable;
import com.android.internal.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.internal.org.bouncycastle.asn1.ASN1Object;
import com.android.internal.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.internal.org.bouncycastle.asn1.ASN1Primitive;
import com.android.internal.org.bouncycastle.asn1.ASN1Sequence;
import com.android.internal.org.bouncycastle.asn1.ASN1Set;
import com.android.internal.org.bouncycastle.asn1.DERSequence;
/* loaded from: classes4.dex */
public class Attribute extends ASN1Object {
    private ASN1ObjectIdentifier attrType;
    private ASN1Set attrValues;

    public static Attribute getInstance(Object o) {
        if (o instanceof Attribute) {
            return (Attribute) o;
        }
        if (o != null) {
            return new Attribute(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    private Attribute(ASN1Sequence seq) {
        if (seq.size() == 2) {
            this.attrType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
            this.attrValues = ASN1Set.getInstance(seq.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + seq.size());
    }

    public Attribute(ASN1ObjectIdentifier attrType, ASN1Set attrValues) {
        this.attrType = attrType;
        this.attrValues = attrValues;
    }

    public ASN1ObjectIdentifier getAttrType() {
        return new ASN1ObjectIdentifier(this.attrType.getId());
    }

    public ASN1Encodable[] getAttributeValues() {
        return this.attrValues.toArray();
    }

    public ASN1Set getAttrValues() {
        return this.attrValues;
    }

    @Override // com.android.internal.org.bouncycastle.asn1.ASN1Object, com.android.internal.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector(2);
        v.add(this.attrType);
        v.add(this.attrValues);
        return new DERSequence(v);
    }
}