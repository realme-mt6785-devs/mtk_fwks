package com.android.internal.content;

import java.io.File;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class FileSystemProvider$$ExternalSyntheticLambda2 implements Predicate {
    public static final /* synthetic */ FileSystemProvider$$ExternalSyntheticLambda2 INSTANCE = new FileSystemProvider$$ExternalSyntheticLambda2();

    private /* synthetic */ FileSystemProvider$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return FileSystemProvider.lambda$queryChildDocumentsShowAll$0((File) obj);
    }
}
