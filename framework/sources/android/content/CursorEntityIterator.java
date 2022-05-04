package android.content;

import android.database.Cursor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public abstract class CursorEntityIterator implements EntityIterator {
    private final Cursor mCursor;
    private boolean mIsClosed = false;

    public abstract Entity getEntityAndIncrementCursor(Cursor cursor) throws RemoteException;

    public CursorEntityIterator(Cursor cursor) {
        this.mCursor = cursor;
        cursor.moveToFirst();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.mIsClosed) {
            return !this.mCursor.isAfterLast();
        }
        throw new IllegalStateException("calling hasNext() when the iterator is closed");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public Entity next() {
        if (this.mIsClosed) {
            throw new IllegalStateException("calling next() when the iterator is closed");
        } else if (hasNext()) {
            try {
                return getEntityAndIncrementCursor(this.mCursor);
            } catch (RemoteException e) {
                throw new RuntimeException("caught a remote exception, this process will die soon", e);
            }
        } else {
            throw new IllegalStateException("you may only call next() if hasNext() is true");
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove not supported by EntityIterators");
    }

    @Override // android.content.EntityIterator
    public final void reset() {
        if (!this.mIsClosed) {
            this.mCursor.moveToFirst();
            return;
        }
        throw new IllegalStateException("calling reset() when the iterator is closed");
    }

    @Override // android.content.EntityIterator
    public final void close() {
        if (!this.mIsClosed) {
            this.mIsClosed = true;
            this.mCursor.close();
            return;
        }
        throw new IllegalStateException("closing when already closed");
    }
}
