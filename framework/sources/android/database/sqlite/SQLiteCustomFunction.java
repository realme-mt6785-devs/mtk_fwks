package android.database.sqlite;

import android.database.sqlite.SQLiteDatabase;
/* loaded from: classes.dex */
public final class SQLiteCustomFunction {
    public final SQLiteDatabase.CustomFunction callback;
    public final String name;
    public final int numArgs;

    public SQLiteCustomFunction(String name, int numArgs, SQLiteDatabase.CustomFunction callback) {
        if (name != null) {
            this.name = name;
            this.numArgs = numArgs;
            this.callback = callback;
            return;
        }
        throw new IllegalArgumentException("name must not be null.");
    }

    private void dispatchCallback(String[] args) {
        this.callback.callback(args);
    }
}
