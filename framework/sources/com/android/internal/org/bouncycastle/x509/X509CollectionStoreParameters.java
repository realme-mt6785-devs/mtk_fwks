package com.android.internal.org.bouncycastle.x509;

import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes4.dex */
public class X509CollectionStoreParameters implements X509StoreParameters {
    private Collection collection;

    public X509CollectionStoreParameters(Collection collection) {
        if (collection != null) {
            this.collection = collection;
            return;
        }
        throw new NullPointerException("collection cannot be null");
    }

    public Object clone() {
        return new X509CollectionStoreParameters(this.collection);
    }

    public Collection getCollection() {
        return new ArrayList(this.collection);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("X509CollectionStoreParameters: [\n");
        sb.append("  collection: " + this.collection + "\n");
        sb.append("]");
        return sb.toString();
    }
}
