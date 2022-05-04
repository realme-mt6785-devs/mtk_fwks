package com.oplus.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes4.dex */
public @interface OplusFeature {

    /* loaded from: classes4.dex */
    public enum OplusFeatureType {
        READONLY_FEATURE,
        MEMORY_FEATURE,
        PERSIST_FEATURE,
        READONLY_NATIVE_FEATURE,
        MEMORY_NATIVE_FEATURE,
        PERSIST_NATIVE_FEATURE
    }

    OplusFeatureType value();
}
