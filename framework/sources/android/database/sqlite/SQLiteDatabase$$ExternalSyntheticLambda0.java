package android.database.sqlite;

import java.io.File;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class SQLiteDatabase$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ SQLiteDatabase$$ExternalSyntheticLambda0 INSTANCE = new SQLiteDatabase$$ExternalSyntheticLambda0();

    private /* synthetic */ SQLiteDatabase$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((File) obj).getName().compareTo(((File) obj2).getName());
        return compareTo;
    }
}
